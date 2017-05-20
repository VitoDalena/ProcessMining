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
/*    */ final class HitQueue
/*    */   extends PriorityQueue
/*    */ {
/*    */   HitQueue(int size)
/*    */   {
/* 23 */     initialize(size);
/*    */   }
/*    */   
/*    */   protected final boolean lessThan(Object a, Object b) {
/* 27 */     ScoreDoc hitA = (ScoreDoc)a;
/* 28 */     ScoreDoc hitB = (ScoreDoc)b;
/* 29 */     if (hitA.score == hitB.score) {
/* 30 */       return hitA.doc > hitB.doc;
/*    */     }
/* 32 */     return hitA.score < hitB.score;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/HitQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */