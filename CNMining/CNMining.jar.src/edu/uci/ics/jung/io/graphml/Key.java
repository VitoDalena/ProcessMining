/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class Key
/*    */ {
/*    */   private String id;
/*    */   private String description;
/*    */   private String attributeName;
/*    */   private String attributeType;
/*    */   private String defaultValue;
/*    */   
/*    */   public static enum ForType
/*    */   {
/* 27 */     ALL,  GRAPH,  NODE,  EDGE,  HYPEREDGE,  PORT,  ENDPOINT;
/*    */     
/*    */ 
/*    */ 
/*    */     private ForType() {}
/*    */   }
/*    */   
/*    */ 
/* 35 */   private ForType forType = ForType.ALL;
/*    */   
/*    */   public String getDescription() {
/* 38 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 42 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getAttributeName() {
/* 46 */     return this.attributeName;
/*    */   }
/*    */   
/*    */   public void setAttributeName(String attributeName) {
/* 50 */     this.attributeName = attributeName;
/*    */   }
/*    */   
/*    */   public String getAttributeType() {
/* 54 */     return this.attributeType;
/*    */   }
/*    */   
/*    */   public void setAttributeType(String attributeType) {
/* 58 */     this.attributeType = attributeType;
/*    */   }
/*    */   
/*    */   public String getDefaultValue() {
/* 62 */     return this.defaultValue;
/*    */   }
/*    */   
/*    */   public void setDefaultValue(String defaultValue) {
/* 66 */     this.defaultValue = defaultValue;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 70 */     this.id = id;
/*    */   }
/*    */   
/*    */   public void setForType(ForType forType) {
/* 74 */     this.forType = forType;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 78 */     return this.id;
/*    */   }
/*    */   
/*    */   public String defaultValue() {
/* 82 */     return this.defaultValue;
/*    */   }
/*    */   
/*    */   public ForType getForType() {
/* 86 */     return this.forType;
/*    */   }
/*    */   
/*    */   public void applyKey(Metadata metadata) {
/* 90 */     Map<String, String> props = metadata.getProperties();
/* 91 */     if ((this.defaultValue != null) && (!props.containsKey(this.id))) {
/* 92 */       props.put(this.id, this.defaultValue);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/Key.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */