/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ final class SegmentMergeQueue
/*    */   extends PriorityQueue
/*    */ {
/*    */   SegmentMergeQueue(int size)
/*    */   {
/* 24 */     initialize(size);
/*    */   }
/*    */   
/*    */   protected final boolean lessThan(Object a, Object b) {
/* 28 */     SegmentMergeInfo stiA = (SegmentMergeInfo)a;
/* 29 */     SegmentMergeInfo stiB = (SegmentMergeInfo)b;
/* 30 */     int comparison = stiA.term.compareTo(stiB.term);
/* 31 */     if (comparison == 0) {
/* 32 */       return stiA.base < stiB.base;
/*    */     }
/* 34 */     return comparison < 0;
/*    */   }
/*    */   
/*    */   final void close() throws IOException {
/* 38 */     while (top() != null) {
/* 39 */       ((SegmentMergeInfo)pop()).close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentMergeQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */