/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ import java.util.HashMap;
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
/*    */ 
/*    */ 
/*    */ public abstract class AbstractMetadata
/*    */   implements Metadata
/*    */ {
/* 23 */   private final Map<String, String> properties = new HashMap();
/*    */   
/*    */   public Map<String, String> getProperties() {
/* 26 */     return this.properties;
/*    */   }
/*    */   
/*    */   public String getProperty(String key) {
/* 30 */     return (String)this.properties.get(key);
/*    */   }
/*    */   
/*    */   public String setProperty(String key, String value) {
/* 34 */     return (String)this.properties.put(key, value);
/*    */   }
/*    */   
/*    */   public void addData(DataMetadata data) {
/* 38 */     this.properties.put(data.getKey(), data.getValue());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/AbstractMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */