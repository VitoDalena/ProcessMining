/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.BitSet;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilteredQuery
/*     */   extends Query
/*     */ {
/*     */   Query query;
/*     */   Filter filter;
/*     */   
/*     */   public FilteredQuery(Query query, Filter filter)
/*     */   {
/*  51 */     this.query = query;
/*  52 */     this.filter = filter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Weight createWeight(Searcher searcher)
/*     */   {
/*  60 */     Weight weight = this.query.createWeight(searcher);
/*  61 */     new Weight() { private final Weight val$weight;
/*     */       private final Searcher val$searcher;
/*     */       
/*  64 */       public float getValue() { return this.val$weight.getValue(); }
/*  65 */       public float sumOfSquaredWeights() throws IOException { return this.val$weight.sumOfSquaredWeights(); }
/*  66 */       public void normalize(float v) { this.val$weight.normalize(v); }
/*  67 */       public Explanation explain(IndexReader ir, int i) throws IOException { return this.val$weight.explain(ir, i); }
/*     */       
/*     */       public Query getQuery() {
/*  70 */         return FilteredQuery.this;
/*     */       }
/*     */       
/*     */       public Scorer scorer(IndexReader indexReader) throws IOException
/*     */       {
/*  75 */         Scorer scorer = this.val$weight.scorer(indexReader);
/*  76 */         BitSet bitset = FilteredQuery.this.filter.bits(indexReader);
/*  77 */         return new FilteredQuery.2(this, FilteredQuery.this.query.getSimilarity(this.val$searcher), scorer, bitset);
/*     */       }
/*     */     };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Query rewrite(IndexReader reader)
/*     */     throws IOException
/*     */   {
/* 105 */     Query rewritten = this.query.rewrite(reader);
/* 106 */     if (rewritten != this.query) {
/* 107 */       FilteredQuery clone = (FilteredQuery)clone();
/* 108 */       clone.query = rewritten;
/* 109 */       return clone;
/*     */     }
/* 111 */     return this;
/*     */   }
/*     */   
/*     */   public Query getQuery()
/*     */   {
/* 116 */     return this.query;
/*     */   }
/*     */   
/*     */   public String toString(String s)
/*     */   {
/* 121 */     return "filtered(" + this.query.toString(s) + ")->" + this.filter;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 126 */     if ((o instanceof FilteredQuery)) {
/* 127 */       FilteredQuery fq = (FilteredQuery)o;
/* 128 */       return (this.query.equals(fq.query)) && (this.filter.equals(fq.filter));
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 135 */     return this.query.hashCode() ^ this.filter.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FilteredQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */