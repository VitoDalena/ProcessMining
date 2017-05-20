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
/*    */ 
/*    */ public abstract interface Metadata
/*    */ {
/*    */   public abstract MetadataType getMetadataType();
/*    */   
/*    */   public abstract Map<String, String> getProperties();
/*    */   
/*    */   public static enum MetadataType
/*    */   {
/* 26 */     GRAPH,  NODE,  EDGE,  HYPEREDGE,  PORT,  ENDPOINT;
/*    */     
/*    */     private MetadataType() {}
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/Metadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */