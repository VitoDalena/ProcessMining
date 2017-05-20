/*    */ package org.apache.lucene.index;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class TermInfo
/*    */ {
/* 23 */   int docFreq = 0;
/*    */   
/* 25 */   long freqPointer = 0L;
/* 26 */   long proxPointer = 0L;
/*    */   int skipOffset;
/*    */   
/*    */   TermInfo() {}
/*    */   
/*    */   TermInfo(int df, long fp, long pp) {
/* 32 */     this.docFreq = df;
/* 33 */     this.freqPointer = fp;
/* 34 */     this.proxPointer = pp;
/*    */   }
/*    */   
/*    */   TermInfo(TermInfo ti) {
/* 38 */     this.docFreq = ti.docFreq;
/* 39 */     this.freqPointer = ti.freqPointer;
/* 40 */     this.proxPointer = ti.proxPointer;
/* 41 */     this.skipOffset = ti.skipOffset;
/*    */   }
/*    */   
/*    */   final void set(int docFreq, long freqPointer, long proxPointer, int skipOffset)
/*    */   {
/* 46 */     this.docFreq = docFreq;
/* 47 */     this.freqPointer = freqPointer;
/* 48 */     this.proxPointer = proxPointer;
/* 49 */     this.skipOffset = skipOffset;
/*    */   }
/*    */   
/*    */   final void set(TermInfo ti) {
/* 53 */     this.docFreq = ti.docFreq;
/* 54 */     this.freqPointer = ti.freqPointer;
/* 55 */     this.proxPointer = ti.proxPointer;
/* 56 */     this.skipOffset = ti.skipOffset;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */