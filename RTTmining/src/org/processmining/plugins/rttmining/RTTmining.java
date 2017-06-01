package org.processmining.plugins.rttmining;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.flexiblemodel.Flex;

public class RTTmining {
			
	/*
	 * Queste notazioni specificano le informazioni di contesto
	 * del plugin, come parametri di input e output
	 * 
	 * Da notare che sono associate ad un metodo statico,
	 * che verrà richiamato all'esecuzione del plugin
	 */	
	
	@Plugin(
        name = "RTTmining Plugin", 
        parameterLabels = { "Log file", "Extended CausalNet" }, 
        returnLabels = { "Hello World" }, 
        returnTypes = { String.class }, 
        userAccessible = true, 
        help = "Produces XMI"
    )
    @UITopiaVariant(
        affiliation = "Process Mining with CSP", 
        author = "Riccardi, Tagliente, Tota", 
        email = "??"
    )
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static String Process(UIPluginContext context, XLog log, Flex causalnet) throws Exception {
		
		SettingsView settingsView = new SettingsView(context);
		Settings settings = settingsView.show();
		
		System.out.println(settings.logFilename);
		
		LogInspector logInspector = new LogInspector(log);
        FlexInspector flexInspector = new FlexInspector(causalnet);

        RTTmining mining = new RTTmining(logInspector, flexInspector);
        RTTgraph graph = mining.process();        

        saveFile("rttgraph.json", graph.toJson());
        saveFile("rttgraph.xmi", graph.toXMI());
        saveFile("rttgraph.txt", graph.toString());
		
		return "Hello RTTmining";
		
	}
	
	private static void saveFile(String filename, String content) throws Exception {
        System.out.println("Exporting File: " + filename + "...");
        File ec = new File(filename);
        if (ec.exists()) {
            ec.delete();
        }
        ec.createNewFile();
        try
        {
            Files.write(FileSystems.getDefault().getPath(
                    ".", new String[] { filename }),
                    content.getBytes(), new OpenOption[] {
                            StandardOpenOption.APPEND
                    }
            );
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
	
	private LogInspector log;
    private FlexInspector causalnet;

    public RTTmining(LogInspector log, FlexInspector causalnet){
        this.log = log;
        this.causalnet = causalnet;
    }

    public RTTgraph process(){
        // Inizializza il grafo inserendovi i nodi rappresentanti le attività
        RTTgraph graph = new RTTgraph();

        ArrayList<String> startActivities = this.causalnet.startActivities();
        ArrayList<String> endActivities = this.causalnet.endActivities();

        for (String activity: this.causalnet.activities()){
            RTTnode node = new RTTnode(activity);

            if(startActivities.contains(activity))
                node.initialNode();
            else if(endActivities.contains(activity))
                node.finalNode();

            graph.add(node);
        }

        // lista dei nodi che non devono essere più processati
        ArrayList<RTTnode> residui = findDirectEdges(graph);

        if(residui.size() == 0){
            // Siamo stati fortunati, non sono presenti diramazioni
            // o fork per attività parallele
            return graph;
        }

        // Invece, sono presenti diramazioni.
        // Occorre individuare i nodi di fork e branch.

        for(RTTnode node: residui){
            this.outgoingEdges(graph, node);
        }

        // Se sono stati aggiunti dei fork node
        // devo inserire dei join
        for(RTTnode node: nodesForIncomingProcessing(graph)){
            incomingEdges(graph, node);
        }

        // Verifica e correzione dei fork
        for(RTTnode forkNode: graph.nodesByType(RTTnode.ForkNode)){
            for(RTTnode node: graph.followers(forkNode))
                this.verifyParallel(graph, node);
            this.verifyFork(graph, forkNode);
        }

        // Verifico e correggo eventuali errori nell'inserimento dei join
        for(RTTnode joinNode: graph.nodesByType(RTTnode.JoinNode))
            this.verifyJoin(graph, joinNode);

        return graph;
    }

    /*
        Trova tutti gli archi diretti,
        li dove un nodo presenza un insieme di followers di cardinalità 1.
        Ritorna la lista di nodi ai quali non è stato possibile mappare gli
        archi outgoing, in quanto seguito da diramazioni o fork.
     */
    private ArrayList<RTTnode> findDirectEdges(RTTgraph graph) {
        ArrayList<RTTnode> result = new ArrayList<RTTnode>();

        for (RTTnode node : graph.nodes()) {
            ArrayList<String> followers = this.causalnet.followers(node.name);

            if (followers.size() == 1) {
                String activity = followers.get(0);

                RTTnode endNode = graph.node(activity);
                if (endNode == null)
                    continue;

                RTTedge edge = new RTTedge(node, endNode);
                graph.add(edge);
            }
            else if(followers.size() == 0){
                // Si tratta del nodo finale
                // vale come completato
            }
            else {
                // il nodo in esame non è stato mappato
                // inseriscilo tra i residui
                if (result.contains(node) == false)
                    result.add(node);
            }
        }

        return result;
    }

    /*
        Questo metodo si occupa di mappare gli archi in uscita per
        quei nodi ch presentano più di una alternativa.
        In particolare qui si vanno a mappare le diramazioni e i fork
     */
    private boolean outgoingEdges(RTTgraph graph, RTTnode node){
        ArrayList<String> followers = this.causalnet.followers(node.name);

        if(followers.size() <= 1)
            return false;

        PatternMap map = new PatternMap(this.log);
        BranchPattern pattern = map.ANDsplit(node.name);

        if(pattern != null){
            // cè un ANDsplit
            // dobbiamo verificare se è solo un AND o un OR -> (arco1, arco2, ..., arcoN, AND)
            ArrayList<String> andList = new ArrayList<String>();

            // Controllo che tutti i follower siano inclusi nel pattern
            for(String activity: followers){
                if(pattern.branches.contains(activity) && andList.contains(activity) == false)
                    andList.add(activity);
            }

            // Sono tutti in AND
            if(andList.size() == followers.size()){
                RTTnode forkNode = new RTTnode("Fork" + node.name);
                forkNode.fork();

                graph.add(forkNode);

                RTTedge firstEdge = new RTTedge(node, forkNode);
                graph.add(firstEdge);

                for(String activity: followers){
                    RTTnode endNode = graph.node(activity);

                    if(endNode == null)
                        continue;

                    RTTedge endEdge = new RTTedge(forkNode, endNode);
                    graph.add(endEdge);
                }
            }
            // Ho la casistica OR -> arco1, arco2,... arcoN, AND
            else {
                System.out.println("ORAND");
                // Aggiungo prima il nodo OR
                RTTnode branchNode = new RTTnode("Branch" + node.name);
                branchNode.branch();

                graph.add(branchNode);

                // Aggiungo il nodo Fork
                RTTnode forkNode = new RTTnode("Fork" + node.name);
                forkNode.fork();

                graph.add(forkNode);

                // Aggiungo l'arco che collega il nodo in esame con l'OR
                graph.add(new RTTedge(node, branchNode));
                // Collego l'OR con il fork
                graph.add(new RTTedge(branchNode, forkNode));

                for(String activity: followers){
                    RTTnode endNode = graph.node(activity);
                    if(endNode == null)
                        continue;

                    if(andList.contains(activity))
                        graph.add(new RTTedge(forkNode, endNode));
                    else graph.add(new RTTedge(branchNode, endNode));
                }
            }
        }
        else {
            RTTnode branchNode = new RTTnode("Branch" + node.name);
            branchNode.branch();

            graph.add(branchNode);

            RTTedge firstEdge = new RTTedge(node, branchNode);
            graph.add(firstEdge);

            for(String activity: followers){
                RTTnode endNode = graph.node(activity);

                if(endNode != null)
                    continue;

                RTTedge endEdge = new RTTedge(branchNode, endNode);
                graph.add(endEdge);
            }
        }

        return true;
    }

    private ArrayList<RTTnode> nodesForIncomingProcessing(RTTgraph graph){
        ArrayList<RTTnode> result = new ArrayList<RTTnode>();

        for(RTTnode node: graph.nodes()){
            if(result.contains(node) == false &&
                    (node.isType(RTTnode.FinalNode) || node.isType(RTTnode.Node)))
                result.add(node);
        }

        return result;
    }

    /*
        Qui ci occupiamo di andare a sincronizzare le operazioni
        di fork, chiudendole con dei join node
     */
    private void incomingEdges(RTTgraph graph, RTTnode node){
        ArrayList<RTTedge> predecessorsEdges = graph.edgesEndWith(node);

        if(predecessorsEdges.size() <= 1)
            return;

        PatternMap map = new PatternMap(this.log);
        BranchPattern pattern = map.ANDjoin(node.name);

        System.out.println("Branch at " + node.name);
        System.out.println(pattern);

        if(pattern == null){
            // TODO
            // cè un errore, come lo gestisco?
        }


        RTTnode joinNode = new RTTnode("Join" + node.name);
        joinNode.join();
        graph.add(joinNode);

        graph.add(new RTTedge(joinNode, node));

        for(int i = 0; i < predecessorsEdges.size(); i++){
            RTTedge edge = predecessorsEdges.get(i);

            if(pattern.branches.contains(edge.begin().name))
                edge.end(joinNode);

        }
    }

    // Verifico che il join node inserito sia corretto
    // altrimenti lo rimuovo
    private void verifyJoin(RTTgraph graph, RTTnode joinNode){
        ArrayList<RTTnode> predecessors = graph.predecessors(joinNode);
        if(predecessors.size() == 0){
            // ho inserito un join inutile
            // rimuovilo
            ArrayList<RTTedge> outgoing = graph.edgesStartWith(joinNode);
            graph.edges().remove(outgoing.get(0));

            graph.nodes().remove(joinNode);
            return;
        }
    }

    /*
        Fornito un nodo successivo ad un fork, verifico che ogni sua diramazione
        porti ad un join
     */
    private void verifyParallel(RTTgraph graph, RTTnode node){
        ArrayList<RTTnode> followers = graph.followers(node);

        if(followers.size() != 1)
            return;

        RTTnode follower = followers.get(0);
        if(follower.isType(RTTnode.Node) == false && follower.isType(RTTnode.FinalNode) == false)
            return;

        ArrayList<RTTnode> predecessors = graph.predecessors(follower);
        RTTnode joinNode = null;

        for(RTTnode n: predecessors){
            if(n.isType(RTTnode.JoinNode))
            {
                joinNode = n;
                break;
            }
        }

        System.out.println(joinNode);

        if(joinNode == null)
            return;

        // Se è presente un join
        // allora cambia la direzione dell'arco in uscita verso il join

        for(RTTedge outcoming: graph.edgesStartWith(node))
            outcoming.end(joinNode);

    }

    /*
        In alcuni casi puo capitare che un fork punti verso un nodo preceduto da un join
        In tal caso quel for occorre farlo precedere da un branch che punta verso il join
     */
    private void verifyFork(RTTgraph graph, RTTnode forkNode){
        ArrayList<RTTnode> followers = graph.followers(forkNode);

        RTTnode branchNode = null;

        // Devo assicurarmi di avere un solo branch prima del fork
        for(RTTnode node: graph.predecessors(forkNode)) {
            if (node.isType(RTTnode.BranchNode)) {
                branchNode = node;
                break;
            }
        }

        for(RTTnode follower: followers){
            for(RTTnode predecessor: graph.predecessors(follower)){
                if(predecessor.isType(RTTnode.JoinNode)){
                    // Ok il nodo a cui punto possiede un join
                    // esegui la modifica del grafo

                    if(branchNode == null){
                        branchNode = new RTTnode("Branch" + forkNode.name.replace("Fork", ""));
                        branchNode.branch();

                        graph.add(branchNode);

                        // rimuovi l'arco che collega il node precedente con il fork
                        RTTedge edge = graph.edgesEndWith(forkNode).get(0);

                        graph.add(new RTTedge(edge.begin(), branchNode));
                        graph.add(new RTTedge(branchNode, forkNode));
                        graph.edges().remove(edge);
                    }

                    graph.add(new RTTedge(branchNode, predecessor));

                    break;
                }
            }
        }
    }
}
