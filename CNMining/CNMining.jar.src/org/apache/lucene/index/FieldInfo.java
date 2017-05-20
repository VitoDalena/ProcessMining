/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class FieldInfo
/*    */ {
/*    */   String name;
/*    */   
/*    */ 
/*    */ 
/*    */   boolean isIndexed;
/*    */   
/*    */ 
/*    */ 
/*    */   int number;
/*    */   
/*    */ 
/*    */ 
/*    */   boolean storeTermVector;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   FieldInfo(String na, boolean tk, int nu, boolean storeTermVector)
/*    */   {
/* 28 */     this.name = na;
/* 29 */     this.isIndexed = tk;
/* 30 */     this.number = nu;
/* 31 */     this.storeTermVector = storeTermVector;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/FieldInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */