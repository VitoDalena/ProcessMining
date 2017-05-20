/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ final class SegmentMergeInfo
/*    */ {
/*    */   Term term;
/*    */   int base;
/*    */   TermEnum termEnum;
/*    */   IndexReader reader;
/*    */   TermPositions postings;
/* 27 */   int[] docMap = null;
/*    */   
/*    */   SegmentMergeInfo(int b, TermEnum te, IndexReader r) throws IOException
/*    */   {
/* 31 */     this.base = b;
/* 32 */     this.reader = r;
/* 33 */     this.termEnum = te;
/* 34 */     this.term = te.term();
/* 35 */     this.postings = this.reader.termPositions();
/*    */     
/*    */ 
/* 38 */     if (this.reader.hasDeletions()) {
/* 39 */       int maxDoc = this.reader.maxDoc();
/* 40 */       this.docMap = new int[maxDoc];
/* 41 */       int j = 0;
/* 42 */       for (int i = 0; i < maxDoc; i++) {
/* 43 */         if (this.reader.isDeleted(i)) {
/* 44 */           this.docMap[i] = -1;
/*    */         } else
/* 46 */           this.docMap[i] = (j++);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   final boolean next() throws IOException {
/* 52 */     if (this.termEnum.next()) {
/* 53 */       this.term = this.termEnum.term();
/* 54 */       return true;
/*    */     }
/* 56 */     this.term = null;
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   final void close() throws IOException
/*    */   {
/* 62 */     this.termEnum.close();
/* 63 */     this.postings.close();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentMergeInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */