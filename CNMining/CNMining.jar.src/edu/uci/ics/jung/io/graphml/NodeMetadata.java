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
/*    */ public class NodeMetadata
/*    */   extends AbstractMetadata
/*    */ {
/*    */   private String id;
/*    */   private String description;
/*    */   private Object vertex;
/* 28 */   private final List<PortMetadata> ports = new ArrayList();
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
/*    */   public void setDescription(String desc) {
/* 43 */     this.description = desc;
/*    */   }
/*    */   
/*    */   public void addPort(PortMetadata port) {
/* 47 */     this.ports.add(port);
/*    */   }
/*    */   
/*    */   public List<PortMetadata> getPorts() {
/* 51 */     return this.ports;
/*    */   }
/*    */   
/*    */   public Object getVertex() {
/* 55 */     return this.vertex;
/*    */   }
/*    */   
/*    */   public void setVertex(Object vertex) {
/* 59 */     this.vertex = vertex;
/*    */   }
/*    */   
/*    */   public Metadata.MetadataType getMetadataType() {
/* 63 */     return Metadata.MetadataType.NODE;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/NodeMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */