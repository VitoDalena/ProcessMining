/*     */ package edu.uci.ics.jung.io.graphml;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphMetadata
/*     */   extends AbstractMetadata
/*     */ {
/*     */   private String id;
/*     */   private EdgeDefault edgeDefault;
/*     */   private String description;
/*     */   private Object graph;
/*     */   
/*     */   public static enum EdgeDefault
/*     */   {
/*  26 */     DIRECTED,  UNDIRECTED;
/*     */     
/*     */ 
/*     */     private EdgeDefault() {}
/*     */   }
/*     */   
/*     */ 
/*  33 */   private final Map<Object, NodeMetadata> nodes = new HashMap();
/*  34 */   private final Map<Object, EdgeMetadata> edges = new HashMap();
/*  35 */   private final Map<Object, HyperEdgeMetadata> hyperEdges = new HashMap();
/*     */   
/*     */   public String getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public EdgeDefault getEdgeDefault() {
/*  46 */     return this.edgeDefault;
/*     */   }
/*     */   
/*     */   public void setEdgeDefault(EdgeDefault edgeDefault) {
/*  50 */     this.edgeDefault = edgeDefault;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  54 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String desc) {
/*  58 */     this.description = desc;
/*     */   }
/*     */   
/*     */   public void addNodeMetadata(Object vertex, NodeMetadata metadata) {
/*  62 */     this.nodes.put(vertex, metadata);
/*     */   }
/*     */   
/*     */   public NodeMetadata getNodeMetadata(Object vertex) {
/*  66 */     return (NodeMetadata)this.nodes.get(vertex);
/*     */   }
/*     */   
/*     */   public Map<Object, NodeMetadata> getNodeMap() {
/*  70 */     return this.nodes;
/*     */   }
/*     */   
/*     */   public void addEdgeMetadata(Object edge, EdgeMetadata metadata) {
/*  74 */     this.edges.put(edge, metadata);
/*     */   }
/*     */   
/*     */   public EdgeMetadata getEdgeMetadata(Object edge) {
/*  78 */     return (EdgeMetadata)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public Map<Object, EdgeMetadata> getEdgeMap() {
/*  82 */     return this.edges;
/*     */   }
/*     */   
/*     */   public void addHyperEdgeMetadata(Object edge, HyperEdgeMetadata metadata) {
/*  86 */     this.hyperEdges.put(edge, metadata);
/*     */   }
/*     */   
/*     */   public HyperEdgeMetadata getHyperEdgeMetadata(Object edge) {
/*  90 */     return (HyperEdgeMetadata)this.hyperEdges.get(edge);
/*     */   }
/*     */   
/*     */   public Map<Object, HyperEdgeMetadata> getHyperEdgeMap() {
/*  94 */     return this.hyperEdges;
/*     */   }
/*     */   
/*     */   public Object getGraph() {
/*  98 */     return this.graph;
/*     */   }
/*     */   
/*     */   public void setGraph(Object graph) {
/* 102 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public Metadata.MetadataType getMetadataType() {
/* 106 */     return Metadata.MetadataType.GRAPH;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVertexProperty(Object vertex, String key)
/*     */     throws IllegalArgumentException
/*     */   {
/* 123 */     NodeMetadata metadata = getNodeMetadata(vertex);
/* 124 */     if (metadata == null) {
/* 125 */       throw new IllegalArgumentException("Metadata does not exist for provided vertex");
/*     */     }
/*     */     
/*     */ 
/* 129 */     return metadata.getProperty(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEdgeProperty(Object edge, String key)
/*     */     throws IllegalArgumentException
/*     */   {
/* 148 */     EdgeMetadata em = getEdgeMetadata(edge);
/* 149 */     if (em != null) {
/* 150 */       return em.getProperty(key);
/*     */     }
/*     */     
/*     */ 
/* 154 */     HyperEdgeMetadata hem = getHyperEdgeMetadata(edge);
/* 155 */     if (hem != null) {
/* 156 */       return hem.getProperty(key);
/*     */     }
/*     */     
/*     */ 
/* 160 */     throw new IllegalArgumentException("Metadata does not exist for provided edge");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/GraphMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */