/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortMetadata
/*    */   extends AbstractMetadata
/*    */ {
/*    */   private String name;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private String description;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 26 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 30 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 34 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String desc) {
/* 38 */     this.description = desc;
/*    */   }
/*    */   
/*    */   public Metadata.MetadataType getMetadataType() {
/* 42 */     return Metadata.MetadataType.PORT;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/PortMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */