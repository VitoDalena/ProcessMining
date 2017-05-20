/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.BitSet;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.store.Directory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndexSearcher
/*     */   extends Searcher
/*     */ {
/*     */   IndexReader reader;
/*     */   private boolean closeReader;
/*     */   
/*     */   public IndexSearcher(String path)
/*     */     throws IOException
/*     */   {
/*  38 */     this(IndexReader.open(path), true);
/*     */   }
/*     */   
/*     */   public IndexSearcher(Directory directory) throws IOException
/*     */   {
/*  43 */     this(IndexReader.open(directory), true);
/*     */   }
/*     */   
/*     */   public IndexSearcher(IndexReader r)
/*     */   {
/*  48 */     this(r, false);
/*     */   }
/*     */   
/*     */   private IndexSearcher(IndexReader r, boolean closeReader) {
/*  52 */     this.reader = r;
/*  53 */     this.closeReader = closeReader;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/*  63 */     if (this.closeReader) {
/*  64 */       this.reader.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public int docFreq(Term term) throws IOException {
/*  69 */     return this.reader.docFreq(term);
/*     */   }
/*     */   
/*     */   public Document doc(int i) throws IOException
/*     */   {
/*  74 */     return this.reader.document(i);
/*     */   }
/*     */   
/*     */   public int maxDoc() throws IOException
/*     */   {
/*  79 */     return this.reader.maxDoc();
/*     */   }
/*     */   
/*     */   public TopDocs search(Query query, Filter filter, int nDocs)
/*     */     throws IOException
/*     */   {
/*  85 */     Scorer scorer = query.weight(this).scorer(this.reader);
/*  86 */     if (scorer == null) {
/*  87 */       return new TopDocs(0, new ScoreDoc[0]);
/*     */     }
/*  89 */     BitSet bits = filter != null ? filter.bits(this.reader) : null;
/*  90 */     HitQueue hq = new HitQueue(nDocs);
/*  91 */     int[] totalHits = new int[1];
/*  92 */     scorer.score(new HitCollector() { private float minScore;
/*     */       private final BitSet val$bits;
/*     */       
/*  95 */       public final void collect(int doc, float score) { if ((score > 0.0F) && ((this.val$bits == null) || (this.val$bits.get(doc))))
/*     */         {
/*  97 */           this.val$totalHits[0] += 1;
/*  98 */           if ((this.val$hq.size() < this.val$nDocs) || (score >= this.minScore)) {
/*  99 */             this.val$hq.insert(new ScoreDoc(doc, score));
/* 100 */             this.minScore = ((ScoreDoc)this.val$hq.top()).score;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 105 */     });
/* 106 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/* 107 */     for (int i = hq.size() - 1; i >= 0; i--) {
/* 108 */       scoreDocs[i] = ((ScoreDoc)hq.pop());
/*     */     }
/* 110 */     return new TopDocs(totalHits[0], scoreDocs);
/*     */   }
/*     */   
/*     */   private final int[] val$totalHits;
/*     */   private final HitQueue val$hq;
/*     */   private final int val$nDocs;
/*     */   public TopFieldDocs search(Query query, Filter filter, int nDocs, Sort sort) throws IOException {
/* 117 */     Scorer scorer = query.weight(this).scorer(this.reader);
/* 118 */     if (scorer == null) {
/* 119 */       return new TopFieldDocs(0, new ScoreDoc[0], sort.fields);
/*     */     }
/* 121 */     BitSet bits = filter != null ? filter.bits(this.reader) : null;
/* 122 */     FieldSortedHitQueue hq = new FieldSortedHitQueue(this.reader, sort.fields, nDocs);
/*     */     
/* 124 */     int[] totalHits = new int[1];
/* 125 */     scorer.score(new HitCollector() { private final BitSet val$bits;
/*     */       
/* 127 */       public final void collect(int doc, float score) { if ((score > 0.0F) && ((this.val$bits == null) || (this.val$bits.get(doc))))
/*     */         {
/* 129 */           this.val$totalHits[0] += 1;
/* 130 */           this.val$hq.insert(new FieldDoc(doc, score));
/*     */         }
/*     */         
/*     */       }
/* 134 */     });
/* 135 */     ScoreDoc[] scoreDocs = new ScoreDoc[hq.size()];
/* 136 */     for (int i = hq.size() - 1; i >= 0; i--) {
/* 137 */       scoreDocs[i] = hq.fillFields((FieldDoc)hq.pop());
/*     */     }
/* 139 */     return new TopFieldDocs(totalHits[0], scoreDocs, hq.getFields());
/*     */   }
/*     */   
/*     */   private final int[] val$totalHits;
/*     */   private final FieldSortedHitQueue val$hq;
/*     */   public void search(Query query, Filter filter, HitCollector results) throws IOException
/*     */   {
/* 146 */     HitCollector collector = results;
/* 147 */     if (filter != null) {
/* 148 */       BitSet bits = filter.bits(this.reader);
/* 149 */       collector = new HitCollector() { private final BitSet val$bits;
/*     */         
/* 151 */         public final void collect(int doc, float score) { if (this.val$bits.get(doc)) {
/* 152 */             this.val$results.collect(doc, score);
/*     */           }
/*     */         }
/*     */       };
/*     */     }
/*     */     
/* 158 */     Scorer scorer = query.weight(this).scorer(this.reader);
/* 159 */     if (scorer == null)
/* 160 */       return;
/* 161 */     scorer.score(collector);
/*     */   }
/*     */   
/*     */   private final HitCollector val$results;
/* 165 */   public Query rewrite(Query original) throws IOException { Query query = original;
/* 166 */     for (Query rewrittenQuery = query.rewrite(this.reader); rewrittenQuery != query; 
/* 167 */         rewrittenQuery = query.rewrite(this.reader)) {
/* 168 */       query = rewrittenQuery;
/*     */     }
/* 170 */     return query;
/*     */   }
/*     */   
/*     */   public Explanation explain(Query query, int doc) throws IOException {
/* 174 */     return query.weight(this).explain(this.reader, doc);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/IndexSearcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */