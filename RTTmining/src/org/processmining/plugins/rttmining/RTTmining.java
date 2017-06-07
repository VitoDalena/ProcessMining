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
		
        saveFile("rttgraph.xmi", graph.toXMI());
        saveFile("rttgraph.txt", graph.toString());
		if(settings.exportJson)
			saveFile("rttgraph.json", graph.toJson());
		
		return "XMI located at: ./rttgraph.xmi";
		
	}
	
	/*
	 * In questa variante del plugin
	 * prevediamo come input il file di output del CNMining in formato XML
	 * e non il grafo costruito dalla sua esecuzione
	 */
	@Plugin(
        name = "RTTmining1", 
        parameterLabels = {}, 
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
	public static String ProcessWithoutDependencies(UIPluginContext context) throws Exception {
		SettingsView settingsView = new SettingsView(context);
		// Abilita l'importazione della rete causale da log
		settingsView.causalnetFromLog();
		Settings settings = settingsView.show();
		
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
        
        saveFile("rttgraph.xmi", graph.toXMI());
        saveFile("rttgraph.txt", graph.toString());
		if(settings.exportJson)
			saveFile("rttgraph.json", graph.toJson());
		
		return "XMI located at: ./rttgraph.xmi";
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
        // Conversione degli input bindings
        this.convertInputBindings(graph);

        return graph;
    }

    private void convertOutputBindings(RTTgraph graph){
        for(FlexNode node: this.causalnet.getNodes()){
            Set<SetFlex> outputs = node.getOutputNodes();
            RTTnode current = graph.node(node.getLabel());

            if(outputs.size() > 1){
                // Aggiungi un branch
                RTTnode branchNode = new RTTnode("Branch"+node.getLabel());
                branchNode.branch();
                graph.add(branchNode);

                graph.add(new RTTedge(current, branchNode));
                current = branchNode;
            }

            for(SetFlex output: outputs){
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
    private void convertInputBindings(RTTgraph graph){
        for(FlexNode node: this.causalnet.getNodes()) {
            Set<SetFlex> inputs = node.getInputNodes();
            RTTnode current = graph.node(node.getLabel());

            for(SetFlex input: inputs) {
                RTTnode endNode = current;

                // Inserisci un join
                if (input.size() > 1) {
                    RTTnode joinNode = new RTTnode("Join" + current.name);
                    joinNode.join();
                    graph.add(joinNode);

                    graph.add(new RTTedge(joinNode, endNode));
                    endNode = joinNode;
                }

                // Modifica gli archi gli archi
                Iterator<FlexNode> i = input.iterator();
                while(i.hasNext()) {
                    FlexNode n = i.next();

                    for(RTTedge e: graph.edgesEndWith(current))
                    {
                        if(e.begin().name.contains(n.getLabel()))
                            e.end(endNode);
                    }
                }
            }

            // Branch join
            if(inputs.size() > 1){
                RTTnode branchNode = new RTTnode("Branch"+node.getLabel());
                branchNode.branch();
                branchNode = graph.add(branchNode);

                // Aggiorna tutti i collegamenti che puntavano
                // al nodo corrente, verso il branch
                for(RTTedge edge: graph.edgesEndWith(current)){
                    edge.end(branchNode);
                }

                // Collega il branch al nodo corrente
                graph.add(new RTTedge(branchNode, current));
            }
        }
    }
}
