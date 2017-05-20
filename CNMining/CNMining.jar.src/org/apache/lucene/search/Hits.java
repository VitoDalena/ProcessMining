/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.document.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Hits
/*     */ {
/*     */   private Query query;
/*     */   private Searcher searcher;
/*  28 */   private Filter filter = null;
/*  29 */   private Sort sort = null;
/*     */   
/*     */   private int length;
/*  32 */   private Vector hitDocs = new Vector();
/*     */   
/*     */   private HitDoc first;
/*     */   private HitDoc last;
/*  36 */   private int numDocs = 0;
/*  37 */   private int maxDocs = 200;
/*     */   
/*     */   Hits(Searcher s, Query q, Filter f) throws IOException {
/*  40 */     this.query = q;
/*  41 */     this.searcher = s;
/*  42 */     this.filter = f;
/*  43 */     getMoreDocs(50);
/*     */   }
/*     */   
/*     */   Hits(Searcher s, Query q, Filter f, Sort o) throws IOException {
/*  47 */     this.query = q;
/*  48 */     this.searcher = s;
/*  49 */     this.filter = f;
/*  50 */     this.sort = o;
/*  51 */     getMoreDocs(50);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final void getMoreDocs(int min)
/*     */     throws IOException
/*     */   {
/*  59 */     if (this.hitDocs.size() > min) {
/*  60 */       min = this.hitDocs.size();
/*     */     }
/*     */     
/*  63 */     int n = min * 2;
/*  64 */     TopDocs topDocs = this.sort == null ? this.searcher.search(this.query, this.filter, n) : this.searcher.search(this.query, this.filter, n, this.sort);
/*  65 */     this.length = topDocs.totalHits;
/*  66 */     ScoreDoc[] scoreDocs = topDocs.scoreDocs;
/*     */     
/*  68 */     float scoreNorm = 1.0F;
/*  69 */     if ((this.length > 0) && (scoreDocs[0].score > 1.0F)) {
/*  70 */       scoreNorm = 1.0F / scoreDocs[0].score;
/*     */     }
/*     */     
/*  73 */     int end = scoreDocs.length < this.length ? scoreDocs.length : this.length;
/*  74 */     for (int i = this.hitDocs.size(); i < end; i++) {
/*  75 */       this.hitDocs.addElement(new HitDoc(scoreDocs[i].score * scoreNorm, scoreDocs[i].doc));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final int length()
/*     */   {
/*  82 */     return this.length;
/*     */   }
/*     */   
/*     */ 
/*     */   public final Document doc(int n)
/*     */     throws IOException
/*     */   {
/*  89 */     HitDoc hitDoc = hitDoc(n);
/*     */     
/*     */ 
/*  92 */     remove(hitDoc);
/*  93 */     addToFront(hitDoc);
/*  94 */     if (this.numDocs > this.maxDocs) {
/*  95 */       HitDoc oldLast = this.last;
/*  96 */       remove(this.last);
/*  97 */       oldLast.doc = null;
/*     */     }
/*     */     
/* 100 */     if (hitDoc.doc == null) {
/* 101 */       hitDoc.doc = this.searcher.doc(hitDoc.id);
/*     */     }
/*     */     
/* 104 */     return hitDoc.doc;
/*     */   }
/*     */   
/*     */   public final float score(int n) throws IOException
/*     */   {
/* 109 */     return hitDoc(n).score;
/*     */   }
/*     */   
/*     */   public final int id(int n) throws IOException
/*     */   {
/* 114 */     return hitDoc(n).id;
/*     */   }
/*     */   
/*     */   private final HitDoc hitDoc(int n) throws IOException
/*     */   {
/* 119 */     if (n >= this.length) {
/* 120 */       throw new IndexOutOfBoundsException("Not a valid hit number: " + n);
/*     */     }
/*     */     
/* 123 */     if (n >= this.hitDocs.size()) {
/* 124 */       getMoreDocs(n);
/*     */     }
/*     */     
/* 127 */     return (HitDoc)this.hitDocs.elementAt(n);
/*     */   }
/*     */   
/*     */   private final void addToFront(HitDoc hitDoc) {
/* 131 */     if (this.first == null) {
/* 132 */       this.last = hitDoc;
/*     */     } else {
/* 134 */       this.first.prev = hitDoc;
/*     */     }
/*     */     
/* 137 */     hitDoc.next = this.first;
/* 138 */     this.first = hitDoc;
/* 139 */     hitDoc.prev = null;
/*     */     
/* 141 */     this.numDocs += 1;
/*     */   }
/*     */   
/*     */   private final void remove(HitDoc hitDoc) {
/* 145 */     if (hitDoc.doc == null) {
/* 146 */       return;
/*     */     }
/*     */     
/* 149 */     if (hitDoc.next == null) {
/* 150 */       this.last = hitDoc.prev;
/*     */     } else {
/* 152 */       hitDoc.next.prev = hitDoc.prev;
/*     */     }
/*     */     
/* 155 */     if (hitDoc.prev == null) {
/* 156 */       this.first = hitDoc.next;
/*     */     } else {
/* 158 */       hitDoc.prev.next = hitDoc.next;
/*     */     }
/*     */     
/* 161 */     this.numDocs -= 1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Hits.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */