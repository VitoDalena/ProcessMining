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
/*    */ public class GraphMLDocument
/*    */ {
/* 21 */   private final KeyMap keyMap = new KeyMap();
/* 22 */   private final List<GraphMetadata> graphMetadata = new ArrayList();
/*    */   
/*    */   public KeyMap getKeyMap() {
/* 25 */     return this.keyMap;
/*    */   }
/*    */   
/*    */   public List<GraphMetadata> getGraphMetadata() {
/* 29 */     return this.graphMetadata;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 33 */     this.graphMetadata.clear();
/* 34 */     this.keyMap.clear();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/GraphMLDocument.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */