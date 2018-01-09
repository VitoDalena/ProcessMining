package com.pmverification;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UMLGraph extends DirectedAcyclicGraph<com.pmverification.Node,Edge> {
    private List<com.pmverification.Node> nodelist;
    private List<Edge> edgelist;
    public UMLGraph(Class<? extends Edge> edgeClass,String graphxml,String owlfile) {
        super(edgeClass);
        File f = new File(graphxml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);
            //Output lists
            this.nodelist = new ArrayList<>();
            this.edgelist = new ArrayList<>();
            
            
            //Read all nodes
            NodeList nlist = doc.getElementsByTagName("node");
            for(int i=0; i < nlist.getLength(); i++ ){ 
                Node curnode = nlist.item(i);
                Element curelem = (Element) curnode;
                com.pmverification.Node mynode = new com.pmverification.Node();
                String nodeType = curelem.getAttribute("xmi:type");
                mynode.setType(nodeType);
                mynode.setId(curelem.getAttribute("xmi:id"));
                mynode.setName(curelem.getAttribute("name"));
                if (nodeType.equals("uml:JoinNode")){
                    JoinNode jnode = new JoinNode(mynode);
                    Element child = (Element)curelem.getElementsByTagName("joinSpec").item(0);
                    jnode.setJType(child.getAttribute("xmi:type"));
                    jnode.setJId(child.getAttribute("xmi:id"));
                    jnode.setJName("xmi:name");
                    jnode.setJValue("xmi:value");
                    nodelist.add(jnode);
                    this.addVertex(jnode);
                }else{
                    nodelist.add(mynode);
                    this.addVertex(mynode);
                }
            }
            //Read all edges
            NodeList elist = doc.getElementsByTagName("edge");
            for(int i = 0;i < elist.getLength();i++){
                Node curnode = elist.item(i);
                Element curelem = (Element) curnode;
                Edge myedge = new Edge();
                myedge.setType(curelem.getAttribute("xmi:type"));
                myedge.setId(curelem.getAttribute("xmi:id"));
                NodeList children = curelem.getElementsByTagName("guard");
                for(int y = 0;y<children.getLength();y++){
                    Node ch = children.item(y);
                    Element chelem = (Element) ch;
                    myedge.setGuard(new Guard(
                                        chelem.getAttribute("xmi:type"),
                                        chelem.getAttribute("xmi:id"),
                                        chelem.getAttribute("name"),
                                        chelem.getAttribute("vale")
                                        ));
                }
                children = curelem.getElementsByTagName("weight");
                for(int y = 0;y<children.getLength();y++){
                    Node ch = children.item(y);
                    Element chelem = (Element) ch;
                    myedge.setWeight(new Weight(
                                        chelem.getAttribute("xmi:type"),
                                        chelem.getAttribute("xmi:id"),
                                        chelem.getAttribute("name") 
                                         ));
                }
               
        
                
                String sourcenode =curelem.getAttribute("source");
                String targetnode = curelem.getAttribute("target");
                this.addEdge(nodelist.get(Integer.parseInt(sourcenode.substring(5))),nodelist.get(Integer.parseInt(targetnode.substring(5))),myedge);
                edgelist.add(myedge);
                
            }

        }catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();        
        }
        
        //OWL PARSING
        /*
        ---------------------------------
        */
        
        
    }

    public List<Edge> getEdgelist() { return edgelist; }

    public List<com.pmverification.Node> getNodelist() { return nodelist; }
}
