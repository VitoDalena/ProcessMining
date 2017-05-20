/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import org.apache.lucene.util.PriorityQueue;
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
/*    */ final class PhraseQueue
/*    */   extends PriorityQueue
/*    */ {
/*    */   PhraseQueue(int size)
/*    */   {
/* 23 */     initialize(size);
/*    */   }
/*    */   
/*    */   protected final boolean lessThan(Object o1, Object o2) {
/* 27 */     PhrasePositions pp1 = (PhrasePositions)o1;
/* 28 */     PhrasePositions pp2 = (PhrasePositions)o2;
/* 29 */     if (pp1.doc == pp2.doc) {
/* 30 */       return pp1.position < pp2.position;
/*    */     }
/* 32 */     return pp1.doc < pp2.doc;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PhraseQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */