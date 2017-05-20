/*     */ package edu.uci.ics.jung.io.graphml;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EdgeMetadata
/*     */   extends AbstractMetadata
/*     */ {
/*     */   private String id;
/*     */   
/*     */ 
/*     */   private Boolean directed;
/*     */   
/*     */ 
/*     */   private String source;
/*     */   
/*     */ 
/*     */   private String target;
/*     */   
/*     */ 
/*     */   private String sourcePort;
/*     */   
/*     */ 
/*     */   private String targetPort;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private Object edge;
/*     */   
/*     */ 
/*     */   public String getId()
/*     */   {
/*  32 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id)
/*     */   {
/*  37 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Boolean isDirected()
/*     */   {
/*  42 */     return this.directed;
/*     */   }
/*     */   
/*     */   public void setDirected(Boolean directed)
/*     */   {
/*  47 */     this.directed = directed;
/*     */   }
/*     */   
/*     */   public String getSource()
/*     */   {
/*  52 */     return this.source;
/*     */   }
/*     */   
/*     */   public void setSource(String source)
/*     */   {
/*  57 */     this.source = source;
/*     */   }
/*     */   
/*     */   public String getTarget()
/*     */   {
/*  62 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(String target)
/*     */   {
/*  67 */     this.target = target;
/*     */   }
/*     */   
/*     */   public String getSourcePort()
/*     */   {
/*  72 */     return this.sourcePort;
/*     */   }
/*     */   
/*     */   public void setSourcePort(String sourcePort)
/*     */   {
/*  77 */     this.sourcePort = sourcePort;
/*     */   }
/*     */   
/*     */   public String getTargetPort()
/*     */   {
/*  82 */     return this.targetPort;
/*     */   }
/*     */   
/*     */   public void setTargetPort(String targetPort)
/*     */   {
/*  87 */     this.targetPort = targetPort;
/*     */   }
/*     */   
/*     */   public String getDescription()
/*     */   {
/*  92 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description)
/*     */   {
/*  97 */     this.description = description;
/*     */   }
/*     */   
/*     */   public Object getEdge() {
/* 101 */     return this.edge;
/*     */   }
/*     */   
/*     */   public void setEdge(Object edge)
/*     */   {
/* 106 */     this.edge = edge;
/*     */   }
/*     */   
/*     */   public Metadata.MetadataType getMetadataType()
/*     */   {
/* 111 */     return Metadata.MetadataType.EDGE;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/EdgeMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */