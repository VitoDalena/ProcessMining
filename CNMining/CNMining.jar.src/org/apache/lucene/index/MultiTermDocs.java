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
/*     */ class MultiTermDocs
/*     */   implements TermDocs
/*     */ {
/*     */   protected IndexReader[] readers;
/*     */   protected int[] starts;
/*     */   protected Term term;
/* 329 */   protected int base = 0;
/* 330 */   protected int pointer = 0;
/*     */   private TermDocs[] readerTermDocs;
/*     */   protected TermDocs current;
/*     */   
/*     */   public MultiTermDocs(IndexReader[] r, int[] s)
/*     */   {
/* 336 */     this.readers = r;
/* 337 */     this.starts = s;
/*     */     
/* 339 */     this.readerTermDocs = new TermDocs[r.length];
/*     */   }
/*     */   
/*     */   public int doc() {
/* 343 */     return this.base + this.current.doc();
/*     */   }
/*     */   
/* 346 */   public int freq() { return this.current.freq(); }
/*     */   
/*     */   public void seek(Term term)
/*     */   {
/* 350 */     this.term = term;
/* 351 */     this.base = 0;
/* 352 */     this.pointer = 0;
/* 353 */     this.current = null;
/*     */   }
/*     */   
/*     */   public void seek(TermEnum termEnum) throws IOException {
/* 357 */     seek(termEnum.term());
/*     */   }
/*     */   
/*     */   public boolean next() throws IOException {
/* 361 */     if ((this.current != null) && (this.current.next()))
/* 362 */       return true;
/* 363 */     if (this.pointer < this.readers.length) {
/* 364 */       this.base = this.starts[this.pointer];
/* 365 */       this.current = termDocs(this.pointer++);
/* 366 */       return next();
/*     */     }
/* 368 */     return false;
/*     */   }
/*     */   
/*     */   public int read(int[] docs, int[] freqs) throws IOException
/*     */   {
/*     */     for (;;) {
/* 374 */       if (this.current == null) {
/* 375 */         if (this.pointer < this.readers.length) {
/* 376 */           this.base = this.starts[this.pointer];
/* 377 */           this.current = termDocs(this.pointer++);
/*     */         } else {
/* 379 */           return 0;
/*     */         }
/*     */       } else {
/* 382 */         int end = this.current.read(docs, freqs);
/* 383 */         if (end == 0) {
/* 384 */           this.current = null;
/*     */         } else {
/* 386 */           int b = this.base;
/* 387 */           for (int i = 0; i < end; i++)
/* 388 */             docs[i] += b;
/* 389 */           return end;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/*     */     do {
/* 397 */       if (!next())
/* 398 */         return false;
/* 399 */     } while (target > doc());
/* 400 */     return true;
/*     */   }
/*     */   
/*     */   private TermDocs termDocs(int i) throws IOException {
/* 404 */     if (this.term == null)
/* 405 */       return null;
/* 406 */     TermDocs result = this.readerTermDocs[i];
/* 407 */     if (result == null)
/* 408 */       result = this.readerTermDocs[i] = termDocs(this.readers[i]);
/* 409 */     result.seek(this.term);
/* 410 */     return result;
/*     */   }
/*     */   
/*     */   protected TermDocs termDocs(IndexReader reader) throws IOException
/*     */   {
/* 415 */     return reader.termDocs();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 419 */     for (int i = 0; i < this.readerTermDocs.length; i++) {
/* 420 */       if (this.readerTermDocs[i] != null) {
/* 421 */         this.readerTermDocs[i].close();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/MultiTermDocs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */