/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiSearcher
/*     */   extends Searcher
/*     */ {
/*     */   private Searchable[] searchables;
/*     */   private int[] starts;
/*  32 */   private int maxDoc = 0;
/*     */   
/*     */   public MultiSearcher(Searchable[] searchables) throws IOException
/*     */   {
/*  36 */     this.searchables = searchables;
/*     */     
/*  38 */     this.starts = new int[searchables.length + 1];
/*  39 */     for (int i = 0; i < searchables.length; i++) {
/*  40 */       this.starts[i] = this.maxDoc;
/*  41 */       this.maxDoc += searchables[i].maxDoc();
/*     */     }
/*  43 */     this.starts[searchables.length] = this.maxDoc;
/*     */   }
/*     */   
/*     */   protected int[] getStarts() {
/*  47 */     return this.starts;
/*     */   }
/*     */   
/*     */   public void close() throws IOException
/*     */   {
/*  52 */     for (int i = 0; i < this.searchables.length; i++)
/*  53 */       this.searchables[i].close();
/*     */   }
/*     */   
/*     */   public int docFreq(Term term) throws IOException {
/*  57 */     int docFreq = 0;
/*  58 */     for (int i = 0; i < this.searchables.length; i++)
/*  59 */       docFreq += this.searchables[i].docFreq(term);
/*  60 */     return docFreq;
/*     */   }
/*     */   
/*     */   public Document doc(int n) throws IOException
/*     */   {
/*  65 */     int i = subSearcher(n);
/*  66 */     return this.searchables[i].doc(n - this.starts[i]);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int searcherIndex(int n) {
/*  73 */     return subSearcher(n);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int subSearcher(int n)
/*     */   {
/*  80 */     int lo = 0;
/*  81 */     int hi = this.searchables.length - 1;
/*     */     
/*  83 */     while (hi >= lo) {
/*  84 */       int mid = lo + hi >> 1;
/*  85 */       int midValue = this.starts[mid];
/*  86 */       if (n < midValue) {
/*  87 */         hi = mid - 1;
/*  88 */       } else if (n > midValue) {
/*  89 */         lo = mid + 1;
/*     */       } else {
/*  91 */         while ((mid + 1 < this.searchables.length) && (this.starts[(mid + 1)] == midValue)) {
/*  92 */           mid++;
/*     */         }
/*  94 */         return mid;
/*     */       }
/*     */     }
/*  97 */     return hi;
/*     */   }
/*     */   
/*     */ 
/*     */   public int subDoc(int n)
/*     */   {
/* 103 */     return n - this.starts[subSearcher(n)];
/*     */   }
/*     */   
/*     */   public int maxDoc() throws IOException {
/* 107 */     return this.maxDoc;
/*     */   }
/*     */   
/*     */   public TopDocs search(Query query, Filter filter, int nDocs) throws IOException
/*     */   {
/* 112 */     HitQueue hq = new HitQueue(nDocs);
/* 113 */     int totalHits = 0;
/*     */     
/* 115 */     for (int i = 0; i < this.searchables.length; i++) {
/* 116 */       TopDocs docs = this.searchables[i].search(query, filter, nDocs);
/* 117 */       totalHits += docs.totalHits;
/* 118 */       ScoreDoc[] scoreDocs = docs.scoreDocs;
/* 119 */       for (int j = 0; j < scoreDocs.length; j++) {
/* 120 */         ScoreDoc scoreDoc = scoreDocs[j];
/* 121 */         scoreDoc.doc += this.starts[i];
/* 122 */         if (!hq.insert(scoreDoc)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 127 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/* 128 */     for (int i = hq.size() - 1; i >= 0; i--) {
/* 129 */       scoreDocs[i] = ((ScoreDoc)hq.pop());
/*     */     }
/* 131 */     return new TopDocs(totalHits, scoreDocs);
/*     */   }
/*     */   
/*     */   public TopFieldDocs search(Query query, Filter filter, int n, Sort sort)
/*     */     throws IOException
/*     */   {
/* 137 */     FieldDocSortedHitQueue hq = null;
/* 138 */     int totalHits = 0;
/*     */     
/* 140 */     for (int i = 0; i < this.searchables.length; i++) {
/* 141 */       TopFieldDocs docs = this.searchables[i].search(query, filter, n, sort);
/* 142 */       if (hq == null) hq = new FieldDocSortedHitQueue(docs.fields, n);
/* 143 */       totalHits += docs.totalHits;
/* 144 */       ScoreDoc[] scoreDocs = docs.scoreDocs;
/* 145 */       for (int j = 0; j < scoreDocs.length; j++) {
/* 146 */         ScoreDoc scoreDoc = scoreDocs[j];
/* 147 */         scoreDoc.doc += this.starts[i];
/* 148 */         if (!hq.insert(scoreDoc)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 153 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/* 154 */     for (int i = hq.size() - 1; i >= 0; i--) {
/* 155 */       scoreDocs[i] = ((ScoreDoc)hq.pop());
/*     */     }
/* 157 */     return new TopFieldDocs(totalHits, scoreDocs, hq.getFields());
/*     */   }
/*     */   
/*     */ 
/*     */   public void search(Query query, Filter filter, HitCollector results)
/*     */     throws IOException
/*     */   {
/* 164 */     for (int i = 0; i < this.searchables.length; i++)
/*     */     {
/* 166 */       int start = this.starts[i];
/*     */       
/* 168 */       this.searchables[i].search(query, filter, new HitCollector() { private final HitCollector val$results;
/*     */         
/* 170 */         public void collect(int doc, float score) { this.val$results.collect(doc + this.val$start, score); }
/*     */       });
/*     */     }
/*     */   }
/*     */   
/*     */   private final int val$start;
/*     */   public Query rewrite(Query original) throws IOException
/*     */   {
/* 178 */     Query[] queries = new Query[this.searchables.length];
/* 179 */     for (int i = 0; i < this.searchables.length; i++) {
/* 180 */       queries[i] = this.searchables[i].rewrite(original);
/*     */     }
/* 182 */     return original.combine(queries);
/*     */   }
/*     */   
/*     */   public Explanation explain(Query query, int doc) throws IOException {
/* 186 */     int i = subSearcher(doc);
/* 187 */     return this.searchables[i].explain(query, doc - this.starts[i]);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/MultiSearcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */