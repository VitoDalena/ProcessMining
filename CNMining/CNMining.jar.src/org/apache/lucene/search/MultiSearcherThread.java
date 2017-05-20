/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.util.PriorityQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MultiSearcherThread
/*     */   extends Thread
/*     */ {
/*     */   private Searchable searchable;
/*     */   private Query query;
/*     */   private Filter filter;
/*     */   private int nDocs;
/*     */   private TopDocs docs;
/*     */   private int i;
/*     */   private PriorityQueue hq;
/*     */   private int[] starts;
/*     */   private IOException ioe;
/*     */   private Sort sort;
/*     */   
/*     */   public MultiSearcherThread(Searchable searchable, Query query, Filter filter, int nDocs, HitQueue hq, int i, int[] starts, String name)
/*     */   {
/* 218 */     super(name);
/* 219 */     this.searchable = searchable;
/* 220 */     this.query = query;
/* 221 */     this.filter = filter;
/* 222 */     this.nDocs = nDocs;
/* 223 */     this.hq = hq;
/* 224 */     this.i = i;
/* 225 */     this.starts = starts;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiSearcherThread(Searchable searchable, Query query, Filter filter, int nDocs, FieldDocSortedHitQueue hq, Sort sort, int i, int[] starts, String name)
/*     */   {
/* 238 */     super(name);
/* 239 */     this.searchable = searchable;
/* 240 */     this.query = query;
/* 241 */     this.filter = filter;
/* 242 */     this.nDocs = nDocs;
/* 243 */     this.hq = hq;
/* 244 */     this.i = i;
/* 245 */     this.starts = starts;
/* 246 */     this.sort = sort;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/* 251 */       this.docs = (this.sort == null ? this.searchable.search(this.query, this.filter, this.nDocs) : this.searchable.search(this.query, this.filter, this.nDocs, this.sort));
/*     */ 
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 256 */       this.ioe = ioe;
/*     */     }
/* 258 */     if (this.ioe == null)
/*     */     {
/*     */ 
/*     */ 
/* 262 */       if (this.sort != null) {
/* 263 */         ((FieldDocSortedHitQueue)this.hq).setFields(((TopFieldDocs)this.docs).fields);
/*     */       }
/* 265 */       ScoreDoc[] scoreDocs = this.docs.scoreDocs;
/* 266 */       for (int j = 0; 
/* 267 */           j < scoreDocs.length; 
/* 268 */           j++) {
/* 269 */         ScoreDoc scoreDoc = scoreDocs[j];
/* 270 */         scoreDoc.doc += this.starts[this.i];
/*     */         
/* 272 */         synchronized (this.hq) {
/* 273 */           if (!this.hq.insert(scoreDoc))
/*     */             break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int hits() {
/* 281 */     return this.docs.totalHits;
/*     */   }
/*     */   
/*     */   public IOException getIOException() {
/* 285 */     return this.ioe;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/MultiSearcherThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */