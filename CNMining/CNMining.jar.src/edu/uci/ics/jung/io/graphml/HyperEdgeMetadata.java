/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HyperEdgeMetadata
/*    */   extends AbstractMetadata
/*    */ {
/*    */   private String id;
/*    */   private String description;
/*    */   private Object edge;
/* 28 */   private final List<EndpointMetadata> endpoints = new ArrayList();
/*    */   
/*    */   public String getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 35 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 39 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 43 */     this.description = description;
/*    */   }
/*    */   
/*    */   public void addEndpoint(EndpointMetadata endpoint) {
/* 47 */     this.endpoints.add(endpoint);
/*    */   }
/*    */   
/*    */   public List<EndpointMetadata> getEndpoints() {
/* 51 */     return this.endpoints;
/*    */   }
/*    */   
/*    */   public Object getEdge() {
/* 55 */     return this.edge;
/*    */   }
/*    */   
/*    */   public void setEdge(Object edge) {
/* 59 */     this.edge = edge;
/*    */   }
/*    */   
/*    */   public Metadata.MetadataType getMetadataType() {
/* 63 */     return Metadata.MetadataType.HYPEREDGE;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/HyperEdgeMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */