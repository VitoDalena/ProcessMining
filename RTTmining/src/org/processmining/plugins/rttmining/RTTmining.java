package org.processmining.plugins.rttmining;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.FlexNode;
import org.processmining.models.flexiblemodel.SetFlex;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;

public class RTTmining {
			
	/*
	 * Queste notazioni specificano le informazioni di contesto
	 * del plugin, come parametri di input e output
	 * 
	 * Da notare che sono associate ad un metodo statico,
	 * che verr√† richiamato all'esecuzione del plugin
	 */	
	
	@Plugin(
        name = "RTTmining", 
        parameterLabels = { "Extended CausalNet" }, 
        returnLabels = { "XMI" }, 
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
    public static String Process(UIPluginContext context, Flex causalnet) throws Exception {
		
		SettingsView settingsView = new SettingsView(context);
		Settings settings = settingsView.show();
		
		RTTmining mining = new RTTmining(causalnet);
		RTTgraph graph = mining.process();
		
        saveFile("rttgraph.uml", graph.toXMI());
        saveFile("rttgraph.txt", graph.toString());
		if(settings.exportJson)
			saveFile("rttgraph.json", graph.toJson());
		
		return graph.toXMI();
		
	}
	
	/*
	 * In questa variante del plugin
	 * prevediamo come input il file di output del CNMining in formato XML
	 * e non il grafo costruito dalla sua esecuzione
	 */
	@Plugin(
        name = "RTTminingBPMN version", 
        parameterLabels = { "Extended CausalNet" }, 
        returnLabels = { "XMI" }, 
        returnTypes = { String.class }, 
        userAccessible = true, 
        help = "Produces XMI"
    )
    @UITopiaVariant(
        affiliation = "Process Mining with CSP", 
        author = "Riccardi, Tagliente, Tota", 
        email = "??"
    )
	public static String ProcessWithoutDependencies(UIPluginContext context, Flex causalnet) throws Exception {
		SettingsView settingsView = new SettingsView(context);
		// Abilita l'importazione della rete causale da log
		//settingsView.causalnetFromLog();
		Settings settings = settingsView.show();
		
		/*
		if(settings.causalnetFilename.isEmpty()){
			return "Error! RTTmining needs an Extended CausalNet to precede!";
		}
		CNParser parser = new CNParser(settings.causalnetFilename);
        Flex causalnet = parser.parse();
        if( causalnet == null ){
        	return "Error found during " + settings.causalnetFilename + " parsing!";
        }        
        
        RTTmining mining = new RTTmining(causalnet);
		RTTgraph graph = mining.process();
		*/
		
		BPMNDiagram bpmn = Flex2BPMN.convert(causalnet);
		if(bpmn == null){
			return "Cannot convert CausalNet to BPMN";
		}
		
		RTTminingBPMN mining = new RTTminingBPMN(bpmn);
		RTTgraph graph = mining.process();
        
        saveFile("rttgraph.xmi", graph.toXMI());
        saveFile("rttgraph.txt", graph.toString());
		if(settings.exportJson)
			saveFile("rttgraph.json", graph.toJson());
		
		return graph.toXMI();
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
	
	private Flex causalnet;
    private FlexInspector inspector;

    public RTTmining(Flex diagram){
        this.causalnet = diagram;
        this.inspector = new FlexInspector(this.causalnet);
    }

    public RTTgraph process(){
        // Inizializza il grafo inserendovi i nodi rappresentanti le attivit‡
        RTTgraph graph = new RTTgraph();

        ArrayList<String> startActivities = this.inspector.startActivities();
        ArrayList<String> endActivities = this.inspector.endActivities();

        for (String activity: this.inspector.activities()){
            RTTnode node = new RTTnode(activity);

            if(startActivities.contains(activity))
                node.initialNode();
            else if(endActivities.contains(activity))
                node.finalNode();

            graph.add(node);
        }

        // Conversione degli output bindings
        this.convertOutputBindings(graph);
        System.out.println();
        // Conversione degli input bindings
        this.convertInputBindings(graph);

        System.out.println();
        this.fix(graph);

        return graph;
    }

    private void convertOutputBindings(RTTgraph graph){
        System.out.println("[RTTmining] computing otuput bindings...");

        for(FlexNode node: this.causalnet.getNodes()){
            Set<SetFlex> outputs = node.getOutputNodes();
            RTTnode current = graph.node(node.getLabel());

            if(outputs.size() > 1){
                // Aggiungi un branch
                RTTnode branchNode = new RTTnode("BranchOut"+node.getLabel());
                branchNode.branch();
                graph.add(branchNode);

                graph.add(new RTTedge(current, branchNode));
                current = branchNode;
            }

            for(SetFlex output: outputs){
                System.out.println(node.getLabel() + " -> " + output);

                RTTnode beginNode = current;

                // Inserisci un fork
                if(output.size() > 1){
                    RTTnode forkNode = new RTTnode("Fork"+current.name);
                    forkNode.fork();
                    graph.add(forkNode);

                    graph.add(new RTTedge(beginNode, forkNode));

                    beginNode = forkNode;
                }

                // Aggiungi gli archi
                Iterator<FlexNode> i = output.iterator();
                while(i.hasNext()){
                    FlexNode n = i.next();

                    RTTnode endNode = graph.node(n.getLabel());
                    if(endNode == null){
                        System.out.println("[Warning:convertOutputBindings] cannot find node: " + n.getLabel());
                        continue;
                    }

                    graph.add(new RTTedge(beginNode, endNode));
                }
            }
        }
    }

    /*
        In questo caso non posso semplicemente inserire gli archi,
        ma devo andare a modificare quelli precedentemente inseriti
        durante la fase di conversione degli output bindings
     */
    private void convertInputBindings(RTTgraph graph) {
        System.out.println("[RTTmining] computing input bindings...");

        for (FlexNode node : this.causalnet.getNodes()) {
            Set<SetFlex> inputs = node.getInputNodes();
            RTTnode current = graph.node(node.getLabel());

            if(inputs.size() > 1){
                // Aggiungi un branch
                RTTnode branchNode = new RTTnode("BranchIn"+node.getLabel());
                branchNode.branch();
                graph.add(branchNode);

                graph.add(new RTTedge(branchNode, current));
                current = branchNode;
            }

            for(SetFlex input: inputs) {
                System.out.println(input + " -> " + node.getLabel());

                RTTnode endNode = current;

                if(input.size() > 1){
                    RTTnode joinNode = new RTTnode("Join" + current.name);
                    joinNode.join();
                    joinNode = graph.add(joinNode);

                    graph.add(new RTTedge(joinNode, endNode));
                    endNode = joinNode;
                }

                // Aggiungi gli archi
                Iterator<FlexNode> i = input.iterator();
                while(i.hasNext()) {
                    FlexNode n = i.next();
                    for(RTTedge e: graph.edgesEndWith(graph.node(node.getLabel()))){
                        if(e.begin().name.contains(n.getLabel())) {
                        	
                        	if(e.begin().equals(endNode))
                                continue;
                            if(e.begin().name.contains("BranchIn") && endNode.name.contains("JoinBranch") &&
                                    endNode.name.contains(n.getLabel()))
                                continue;
                        	
                            System.out.println("[Fixing Edge] " + e.toString() + "...");
                            e.end(endNode);
                            System.out.println("[Fixed] " + e.toString());
                        }
                        else {
                            //System.out.println("[Fix Fail] " + e.toString());
                        }
                    }
                }
            }
        }
    }

    private void fix(RTTgraph graph){
        System.out.println("[RTTmining] fixing graph...");

        for(RTTnode node:graph.nodesByType(RTTnode.BranchNode)){
            ArrayList<RTTedge> incoming = graph.edgesEndWith(node);
            ArrayList<RTTedge> outcoming = graph.edgesStartWith(node);

            if(incoming.size() == 1 && outcoming.size() == 1){
                System.out.println("[Graph Fix] Deleting node " + node.toString());

                graph.add(new RTTedge(incoming.get(0).begin(), outcoming.get(0).end()));

                graph.nodes().remove(node);
                graph.edges().remove(incoming.get(0));
                graph.edges().remove(outcoming.get(0));
            }
        }

        for(RTTnode node:graph.nodesByType(RTTnode.JoinNode)){
            ArrayList<RTTedge> incoming = graph.edgesEndWith(node);
            ArrayList<RTTedge> outcoming = graph.edgesStartWith(node);

            if(incoming.size() == 1 && outcoming.size() == 1){
                System.out.println("[Graph Fix] Deleting node " + node.toString());

                graph.add(new RTTedge(incoming.get(0).begin(), outcoming.get(0).end()));

                graph.nodes().remove(node);
                graph.edges().remove(incoming.get(0));
                graph.edges().remove(outcoming.get(0));
            }
            else if(incoming.size() == 0 && outcoming.size() == 1){
                System.out.println("[Graph Fix] Deleting node " + node.toString());

                graph.nodes().remove(node);
                graph.edges().remove(outcoming.get(0));
            }
        }
    }
}
