/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.EdgeMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.EndpointMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*     */ import edu.uci.ics.jung.io.graphml.GraphMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.GraphMetadata.EdgeDefault;
/*     */ import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.NodeMetadata;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*     */   public GraphElementParser(ParserContext<G, V, E> parserContext)
/*     */   {
/*  43 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */   public GraphMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  52 */       GraphMetadata graphMetadata = new GraphMetadata();
/*     */       
/*     */ 
/*  55 */       Iterator iterator = start.getAttributes();
/*  56 */       while (iterator.hasNext()) {
/*  57 */         Attribute attribute = (Attribute)iterator.next();
/*  58 */         String name = attribute.getName().getLocalPart();
/*  59 */         String value = attribute.getValue();
/*  60 */         if ((graphMetadata.getId() == null) && ("id".equals(name)))
/*     */         {
/*     */ 
/*  63 */           graphMetadata.setId(value);
/*  64 */         } else if ((graphMetadata.getEdgeDefault() == null) && ("edgedefault".equals(name)))
/*     */         {
/*     */ 
/*  67 */           graphMetadata.setEdgeDefault("directed".equals(value) ? GraphMetadata.EdgeDefault.DIRECTED : GraphMetadata.EdgeDefault.UNDIRECTED);
/*     */         }
/*     */         else
/*     */         {
/*  71 */           graphMetadata.setProperty(name, value);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  76 */       if (graphMetadata.getEdgeDefault() == null) {
/*  77 */         throw new GraphIOException("Element 'graph' is missing attribute 'edgedefault'");
/*     */       }
/*     */       
/*     */ 
/*  81 */       Map<String, V> idToVertexMap = new HashMap();
/*  82 */       Collection<EdgeMetadata> edgeMetadata = new LinkedList();
/*  83 */       Collection<HyperEdgeMetadata> hyperEdgeMetadata = new LinkedList();
/*     */       
/*  85 */       while (xmlEventReader.hasNext())
/*     */       {
/*  87 */         XMLEvent event = xmlEventReader.nextEvent();
/*  88 */         if (event.isStartElement()) {
/*  89 */           StartElement element = (StartElement)event;
/*     */           
/*  91 */           String name = element.getName().getLocalPart();
/*  92 */           if ("desc".equals(name))
/*     */           {
/*     */ 
/*  95 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*     */             
/*  97 */             graphMetadata.setDescription(desc);
/*     */           }
/*  99 */           else if ("data".equals(name))
/*     */           {
/*     */ 
/* 102 */             DataMetadata data = (DataMetadata)getParser(name).parse(xmlEventReader, element);
/*     */             
/* 104 */             graphMetadata.addData(data);
/*     */           }
/* 106 */           else if ("node".equals(name))
/*     */           {
/*     */ 
/* 109 */             NodeMetadata metadata = (NodeMetadata)getParser(name).parse(xmlEventReader, element);
/*     */             
/*     */ 
/*     */ 
/* 113 */             V vertex = getParserContext().createVertex(metadata);
/* 114 */             metadata.setVertex(vertex);
/* 115 */             idToVertexMap.put(metadata.getId(), vertex);
/*     */             
/*     */ 
/* 118 */             graphMetadata.addNodeMetadata(vertex, metadata);
/*     */           }
/* 120 */           else if ("edge".equals(name))
/*     */           {
/*     */ 
/* 123 */             EdgeMetadata metadata = (EdgeMetadata)getParser(name).parse(xmlEventReader, element);
/*     */             
/*     */ 
/*     */ 
/* 127 */             if (metadata.isDirected() == null) {
/* 128 */               metadata.setDirected(Boolean.valueOf(graphMetadata.getEdgeDefault() == GraphMetadata.EdgeDefault.DIRECTED));
/*     */             }
/*     */             
/*     */ 
/* 132 */             E edge = getParserContext().createEdge(metadata);
/* 133 */             edgeMetadata.add(metadata);
/* 134 */             metadata.setEdge(edge);
/*     */             
/*     */ 
/* 137 */             graphMetadata.addEdgeMetadata(edge, metadata);
/*     */           }
/* 139 */           else if ("hyperedge".equals(name))
/*     */           {
/*     */ 
/* 142 */             HyperEdgeMetadata metadata = (HyperEdgeMetadata)getParser(name).parse(xmlEventReader, element);
/*     */             
/*     */ 
/*     */ 
/* 146 */             E edge = getParserContext().createHyperEdge(metadata);
/* 147 */             hyperEdgeMetadata.add(metadata);
/* 148 */             metadata.setEdge(edge);
/*     */             
/*     */ 
/* 151 */             graphMetadata.addHyperEdgeMetadata(edge, metadata);
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 156 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*     */         
/* 160 */         if (event.isEndElement()) {
/* 161 */           EndElement end = (EndElement)event;
/* 162 */           verifyMatch(start, end);
/* 163 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 168 */       applyKeys(graphMetadata);
/*     */       
/*     */ 
/* 171 */       G graph = getParserContext().createGraph(graphMetadata);
/* 172 */       graphMetadata.setGraph(graph);
/*     */       
/*     */ 
/* 175 */       addVerticesToGraph(graph, idToVertexMap.values());
/*     */       
/*     */ 
/* 178 */       addEdgesToGraph(graph, edgeMetadata, idToVertexMap);
/* 179 */       addHyperEdgesToGraph(graph, hyperEdgeMetadata, idToVertexMap);
/*     */       
/* 181 */       return graphMetadata;
/*     */     }
/*     */     catch (Exception e) {
/* 184 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   private void addVerticesToGraph(G graph, Collection<V> vertices)
/*     */   {
/* 192 */     for (V vertex : vertices) {
/* 193 */       graph.addVertex(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void addEdgesToGraph(G graph, Collection<EdgeMetadata> metadata, Map<String, V> idToVertexMap)
/*     */     throws GraphIOException
/*     */   {
/* 201 */     for (EdgeMetadata emd : metadata)
/*     */     {
/*     */ 
/* 204 */       E edge = emd.getEdge();
/*     */       
/*     */ 
/* 207 */       V source = idToVertexMap.get(emd.getSource());
/* 208 */       V target = idToVertexMap.get(emd.getTarget());
/* 209 */       if ((source == null) || (target == null)) {
/* 210 */         throw new GraphIOException("edge references undefined source or target vertex. Source: " + emd.getSource() + ", Target: " + emd.getTarget());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 217 */       if ((graph instanceof Graph)) {
/* 218 */         ((Graph)graph).addEdge(edge, source, target, emd.isDirected().booleanValue() ? EdgeType.DIRECTED : EdgeType.UNDIRECTED);
/*     */       }
/*     */       else
/*     */       {
/* 222 */         graph.addEdge(edge, new Pair(source, target));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void addHyperEdgesToGraph(G graph, Collection<HyperEdgeMetadata> metadata, Map<String, V> idToVertexMap)
/*     */     throws GraphIOException
/*     */   {
/* 231 */     for (HyperEdgeMetadata emd : metadata)
/*     */     {
/*     */ 
/* 234 */       E edge = emd.getEdge();
/*     */       
/*     */ 
/* 237 */       List<V> verticies = new ArrayList();
/* 238 */       List<EndpointMetadata> endpoints = emd.getEndpoints();
/* 239 */       for (EndpointMetadata ep : endpoints) {
/* 240 */         V v = idToVertexMap.get(ep.getNode());
/* 241 */         if (v == null) {
/* 242 */           throw new GraphIOException("hyperedge references undefined vertex: " + ep.getNode());
/*     */         }
/*     */         
/*     */ 
/* 246 */         verticies.add(v);
/*     */       }
/*     */       
/*     */ 
/* 250 */       graph.addEdge(edge, verticies);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/GraphElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */