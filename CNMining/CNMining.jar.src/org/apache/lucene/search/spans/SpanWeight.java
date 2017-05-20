/*     */ package org.apache.lucene.search.spans;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.search.Explanation;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.Scorer;
/*     */ import org.apache.lucene.search.Searcher;
/*     */ import org.apache.lucene.search.Similarity;
/*     */ import org.apache.lucene.search.Weight;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SpanWeight
/*     */   implements Weight
/*     */ {
/*     */   private Searcher searcher;
/*     */   private float value;
/*     */   private float idf;
/*     */   private float queryNorm;
/*     */   private float queryWeight;
/*     */   private Collection terms;
/*     */   private SpanQuery query;
/*     */   
/*     */   public SpanWeight(SpanQuery query, Searcher searcher)
/*     */   {
/*  45 */     this.searcher = searcher;
/*  46 */     this.query = query;
/*  47 */     this.terms = query.getTerms();
/*     */   }
/*     */   
/*  50 */   public Query getQuery() { return this.query; }
/*  51 */   public float getValue() { return this.value; }
/*     */   
/*     */   public float sumOfSquaredWeights() throws IOException {
/*  54 */     this.idf = this.query.getSimilarity(this.searcher).idf(this.terms, this.searcher);
/*  55 */     this.queryWeight = (this.idf * this.query.getBoost());
/*  56 */     return this.queryWeight * this.queryWeight;
/*     */   }
/*     */   
/*     */   public void normalize(float queryNorm) {
/*  60 */     this.queryNorm = queryNorm;
/*  61 */     this.queryWeight *= queryNorm;
/*  62 */     this.value = (this.queryWeight * this.idf);
/*     */   }
/*     */   
/*     */   public Scorer scorer(IndexReader reader) throws IOException {
/*  66 */     return new SpanScorer(this.query.getSpans(reader), this, this.query.getSimilarity(this.searcher), reader.norms(this.query.getField()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Explanation explain(IndexReader reader, int doc)
/*     */     throws IOException
/*     */   {
/*  74 */     Explanation result = new Explanation();
/*  75 */     result.setDescription("weight(" + getQuery() + " in " + doc + "), product of:");
/*  76 */     String field = ((SpanQuery)getQuery()).getField();
/*     */     
/*  78 */     StringBuffer docFreqs = new StringBuffer();
/*  79 */     Iterator i = this.terms.iterator();
/*  80 */     while (i.hasNext()) {
/*  81 */       Term term = (Term)i.next();
/*  82 */       docFreqs.append(term.text());
/*  83 */       docFreqs.append("=");
/*  84 */       docFreqs.append(this.searcher.docFreq(term));
/*     */       
/*  86 */       if (i.hasNext()) {
/*  87 */         docFreqs.append(" ");
/*     */       }
/*     */     }
/*     */     
/*  91 */     Explanation idfExpl = new Explanation(this.idf, "idf(" + field + ": " + docFreqs + ")");
/*     */     
/*     */ 
/*     */ 
/*  95 */     Explanation queryExpl = new Explanation();
/*  96 */     queryExpl.setDescription("queryWeight(" + getQuery() + "), product of:");
/*     */     
/*  98 */     Explanation boostExpl = new Explanation(getQuery().getBoost(), "boost");
/*  99 */     if (getQuery().getBoost() != 1.0F)
/* 100 */       queryExpl.addDetail(boostExpl);
/* 101 */     queryExpl.addDetail(idfExpl);
/*     */     
/* 103 */     Explanation queryNormExpl = new Explanation(this.queryNorm, "queryNorm");
/* 104 */     queryExpl.addDetail(queryNormExpl);
/*     */     
/* 106 */     queryExpl.setValue(boostExpl.getValue() * idfExpl.getValue() * queryNormExpl.getValue());
/*     */     
/*     */ 
/*     */ 
/* 110 */     result.addDetail(queryExpl);
/*     */     
/*     */ 
/* 113 */     Explanation fieldExpl = new Explanation();
/* 114 */     fieldExpl.setDescription("fieldWeight(" + field + ":" + this.query.toString(field) + " in " + doc + "), product of:");
/*     */     
/*     */ 
/* 117 */     Explanation tfExpl = scorer(reader).explain(doc);
/* 118 */     fieldExpl.addDetail(tfExpl);
/* 119 */     fieldExpl.addDetail(idfExpl);
/*     */     
/* 121 */     Explanation fieldNormExpl = new Explanation();
/* 122 */     byte[] fieldNorms = reader.norms(field);
/* 123 */     float fieldNorm = fieldNorms != null ? Similarity.decodeNorm(fieldNorms[doc]) : 0.0F;
/*     */     
/* 125 */     fieldNormExpl.setValue(fieldNorm);
/* 126 */     fieldNormExpl.setDescription("fieldNorm(field=" + field + ", doc=" + doc + ")");
/* 127 */     fieldExpl.addDetail(fieldNormExpl);
/*     */     
/* 129 */     fieldExpl.setValue(tfExpl.getValue() * idfExpl.getValue() * fieldNormExpl.getValue());
/*     */     
/*     */ 
/*     */ 
/* 133 */     result.addDetail(fieldExpl);
/*     */     
/*     */ 
/* 136 */     result.setValue(queryExpl.getValue() * fieldExpl.getValue());
/*     */     
/* 138 */     if (queryExpl.getValue() == 1.0F) {
/* 139 */       return fieldExpl;
/*     */     }
/* 141 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanWeight.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */