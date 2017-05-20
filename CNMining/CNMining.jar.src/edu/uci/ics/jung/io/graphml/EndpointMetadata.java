/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EndpointMetadata
/*    */   extends AbstractMetadata
/*    */ {
/*    */   private String id;
/*    */   
/*    */ 
/*    */   private String port;
/*    */   
/*    */ 
/*    */   private String node;
/*    */   
/*    */ 
/*    */   private String description;
/*    */   
/*    */ 
/*    */ 
/*    */   public static enum EndpointType
/*    */   {
/* 23 */     IN, 
/* 24 */     OUT, 
/* 25 */     UNDIR;
/*    */     
/*    */ 
/*    */     private EndpointType() {}
/*    */   }
/*    */   
/*    */ 
/* 32 */   private EndpointType endpointType = EndpointType.UNDIR;
/*    */   
/*    */   public String getId() {
/* 35 */     return this.id;
/*    */   }
/*    */   
/* 38 */   public void setId(String id) { this.id = id; }
/*    */   
/*    */   public String getPort() {
/* 41 */     return this.port;
/*    */   }
/*    */   
/* 44 */   public void setPort(String port) { this.port = port; }
/*    */   
/*    */   public String getNode() {
/* 47 */     return this.node;
/*    */   }
/*    */   
/* 50 */   public void setNode(String node) { this.node = node; }
/*    */   
/*    */   public EndpointType getEndpointType() {
/* 53 */     return this.endpointType;
/*    */   }
/*    */   
/* 56 */   public void setEndpointType(EndpointType endpointType) { this.endpointType = endpointType; }
/*    */   
/*    */   public String getDescription() {
/* 59 */     return this.description;
/*    */   }
/*    */   
/* 62 */   public void setDescription(String description) { this.description = description; }
/*    */   
/*    */   public Metadata.MetadataType getMetadataType() {
/* 65 */     return Metadata.MetadataType.ENDPOINT;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/EndpointMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */