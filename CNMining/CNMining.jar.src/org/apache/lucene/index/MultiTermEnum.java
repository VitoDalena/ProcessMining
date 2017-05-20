/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MultiTermEnum
/*     */   extends TermEnum
/*     */ {
/*     */   private SegmentMergeQueue queue;
/*     */   private Term term;
/*     */   private int docFreq;
/*     */   
/*     */   public MultiTermEnum(IndexReader[] readers, int[] starts, Term t)
/*     */     throws IOException
/*     */   {
/* 267 */     this.queue = new SegmentMergeQueue(readers.length);
/* 268 */     for (int i = 0; i < readers.length; i++) {
/* 269 */       IndexReader reader = readers[i];
/*     */       TermEnum termEnum;
/*     */       TermEnum termEnum;
/* 272 */       if (t != null) {
/* 273 */         termEnum = reader.terms(t);
/*     */       } else {
/* 275 */         termEnum = reader.terms();
/*     */       }
/* 277 */       SegmentMergeInfo smi = new SegmentMergeInfo(starts[i], termEnum, reader);
/* 278 */       if (t == null ? smi.next() : termEnum.term() != null) {
/* 279 */         this.queue.put(smi);
/*     */       } else {
/* 281 */         smi.close();
/*     */       }
/*     */     }
/* 284 */     if ((t != null) && (this.queue.size() > 0)) {
/* 285 */       next();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean next() throws IOException {
/* 290 */     SegmentMergeInfo top = (SegmentMergeInfo)this.queue.top();
/* 291 */     if (top == null) {
/* 292 */       this.term = null;
/* 293 */       return false;
/*     */     }
/*     */     
/* 296 */     this.term = top.term;
/* 297 */     this.docFreq = 0;
/*     */     
/* 299 */     while ((top != null) && (this.term.compareTo(top.term) == 0)) {
/* 300 */       this.queue.pop();
/* 301 */       this.docFreq += top.termEnum.docFreq();
/* 302 */       if (top.next()) {
/* 303 */         this.queue.put(top);
/*     */       } else
/* 305 */         top.close();
/* 306 */       top = (SegmentMergeInfo)this.queue.top();
/*     */     }
/* 308 */     return true;
/*     */   }
/*     */   
/*     */   public Term term() {
/* 312 */     return this.term;
/*     */   }
/*     */   
/*     */   public int docFreq() {
/* 316 */     return this.docFreq;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 320 */     this.queue.close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/MultiTermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */