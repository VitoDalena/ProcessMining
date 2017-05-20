/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ public class ParallelMultiSearcher
/*     */   extends MultiSearcher
/*     */ {
/*     */   private Searchable[] searchables;
/*     */   private int[] starts;
/*     */   
/*     */   public ParallelMultiSearcher(Searchable[] searchables)
/*     */     throws IOException
/*     */   {
/*  36 */     super(searchables);
/*  37 */     this.searchables = searchables;
/*  38 */     this.starts = getStarts();
/*     */   }
/*     */   
/*     */ 
/*     */   public int docFreq(Term term)
/*     */     throws IOException
/*     */   {
/*  45 */     int docFreq = 0;
/*  46 */     for (int i = 0; i < this.searchables.length; i++)
/*  47 */       docFreq += this.searchables[i].docFreq(term);
/*  48 */     return docFreq;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TopDocs search(Query query, Filter filter, int nDocs)
/*     */     throws IOException
/*     */   {
/*  58 */     HitQueue hq = new HitQueue(nDocs);
/*  59 */     int totalHits = 0;
/*  60 */     MultiSearcherThread[] msta = new MultiSearcherThread[this.searchables.length];
/*     */     
/*  62 */     for (int i = 0; i < this.searchables.length; i++)
/*     */     {
/*  64 */       msta[i] = new MultiSearcherThread(this.searchables[i], query, filter, nDocs, hq, i, this.starts, "MultiSearcher thread #" + (i + 1));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */       msta[i].start();
/*     */     }
/*     */     
/*  77 */     for (int i = 0; i < this.searchables.length; i++) {
/*     */       try {
/*  79 */         msta[i].join();
/*     */       }
/*     */       catch (InterruptedException ie) {}
/*     */       
/*  83 */       IOException ioe = msta[i].getIOException();
/*  84 */       if (ioe == null) {
/*  85 */         totalHits += msta[i].hits();
/*     */       }
/*     */       else {
/*  88 */         throw ioe;
/*     */       }
/*     */     }
/*     */     
/*  92 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/*  93 */     for (int i = hq.size() - 1; i >= 0; i--) {
/*  94 */       scoreDocs[i] = ((ScoreDoc)hq.pop());
/*     */     }
/*  96 */     return new TopDocs(totalHits, scoreDocs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TopFieldDocs search(Query query, Filter filter, int nDocs, Sort sort)
/*     */     throws IOException
/*     */   {
/* 107 */     FieldDocSortedHitQueue hq = new FieldDocSortedHitQueue(null, nDocs);
/* 108 */     int totalHits = 0;
/* 109 */     MultiSearcherThread[] msta = new MultiSearcherThread[this.searchables.length];
/* 110 */     for (int i = 0; i < this.searchables.length; i++)
/*     */     {
/* 112 */       msta[i] = new MultiSearcherThread(this.searchables[i], query, filter, nDocs, hq, sort, i, this.starts, "MultiSearcher thread #" + (i + 1));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 123 */       msta[i].start();
/*     */     }
/*     */     
/* 126 */     for (int i = 0; i < this.searchables.length; i++) {
/*     */       try {
/* 128 */         msta[i].join();
/*     */       }
/*     */       catch (InterruptedException ie) {}
/*     */       
/* 132 */       IOException ioe = msta[i].getIOException();
/* 133 */       if (ioe == null) {
/* 134 */         totalHits += msta[i].hits();
/*     */       }
/*     */       else {
/* 137 */         throw ioe;
/*     */       }
/*     */     }
/*     */     
/* 141 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/* 142 */     for (int i = hq.size() - 1; i >= 0; i--) {
/* 143 */       scoreDocs[i] = ((ScoreDoc)hq.pop());
/*     */     }
/* 145 */     return new TopFieldDocs(totalHits, scoreDocs, hq.getFields());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void search(Query query, Filter filter, HitCollector results)
/*     */     throws IOException
/*     */   {
/* 166 */     for (int i = 0; i < this.searchables.length; i++)
/*     */     {
/* 168 */       int start = this.starts[i];
/*     */       
/* 170 */       this.searchables[i].search(query, filter, new HitCollector() { private final HitCollector val$results;
/*     */         
/* 172 */         public void collect(int doc, float score) { this.val$results.collect(doc + this.val$start, score); }
/*     */       });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final int val$start;
/*     */   
/*     */   public Query rewrite(Query original)
/*     */     throws IOException
/*     */   {
/* 184 */     Query[] queries = new Query[this.searchables.length];
/* 185 */     for (int i = 0; i < this.searchables.length; i++) {
/* 186 */       queries[i] = this.searchables[i].rewrite(original);
/*     */     }
/* 188 */     return original.combine(queries);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/ParallelMultiSearcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */