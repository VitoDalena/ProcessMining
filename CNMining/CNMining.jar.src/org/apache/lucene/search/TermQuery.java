/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.index.TermDocs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TermQuery
/*     */   extends Query
/*     */ {
/*     */   private Term term;
/*     */   
/*     */   private class TermWeight
/*     */     implements Weight
/*     */   {
/*     */     private Searcher searcher;
/*     */     private float value;
/*     */     private float idf;
/*     */     private float queryNorm;
/*     */     private float queryWeight;
/*     */     
/*     */     public TermWeight(Searcher searcher)
/*     */     {
/*  38 */       this.searcher = searcher;
/*     */     }
/*     */     
/*  41 */     public String toString() { return "weight(" + TermQuery.this + ")"; }
/*     */     
/*  43 */     public Query getQuery() { return TermQuery.this; }
/*  44 */     public float getValue() { return this.value; }
/*     */     
/*     */     public float sumOfSquaredWeights() throws IOException {
/*  47 */       this.idf = TermQuery.this.getSimilarity(this.searcher).idf(TermQuery.this.term, this.searcher);
/*  48 */       this.queryWeight = (this.idf * TermQuery.this.getBoost());
/*  49 */       return this.queryWeight * this.queryWeight;
/*     */     }
/*     */     
/*     */     public void normalize(float queryNorm) {
/*  53 */       this.queryNorm = queryNorm;
/*  54 */       this.queryWeight *= queryNorm;
/*  55 */       this.value = (this.queryWeight * this.idf);
/*     */     }
/*     */     
/*     */     public Scorer scorer(IndexReader reader) throws IOException {
/*  59 */       TermDocs termDocs = reader.termDocs(TermQuery.this.term);
/*     */       
/*  61 */       if (termDocs == null) {
/*  62 */         return null;
/*     */       }
/*  64 */       return new TermScorer(this, termDocs, TermQuery.this.getSimilarity(this.searcher), reader.norms(TermQuery.this.term.field()));
/*     */     }
/*     */     
/*     */ 
/*     */     public Explanation explain(IndexReader reader, int doc)
/*     */       throws IOException
/*     */     {
/*  71 */       Explanation result = new Explanation();
/*  72 */       result.setDescription("weight(" + getQuery() + " in " + doc + "), product of:");
/*     */       
/*  74 */       Explanation idfExpl = new Explanation(this.idf, "idf(docFreq=" + this.searcher.docFreq(TermQuery.this.term) + ")");
/*     */       
/*     */ 
/*     */ 
/*  78 */       Explanation queryExpl = new Explanation();
/*  79 */       queryExpl.setDescription("queryWeight(" + getQuery() + "), product of:");
/*     */       
/*  81 */       Explanation boostExpl = new Explanation(TermQuery.this.getBoost(), "boost");
/*  82 */       if (TermQuery.this.getBoost() != 1.0F)
/*  83 */         queryExpl.addDetail(boostExpl);
/*  84 */       queryExpl.addDetail(idfExpl);
/*     */       
/*  86 */       Explanation queryNormExpl = new Explanation(this.queryNorm, "queryNorm");
/*  87 */       queryExpl.addDetail(queryNormExpl);
/*     */       
/*  89 */       queryExpl.setValue(boostExpl.getValue() * idfExpl.getValue() * queryNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/*  93 */       result.addDetail(queryExpl);
/*     */       
/*     */ 
/*  96 */       String field = TermQuery.this.term.field();
/*  97 */       Explanation fieldExpl = new Explanation();
/*  98 */       fieldExpl.setDescription("fieldWeight(" + TermQuery.this.term + " in " + doc + "), product of:");
/*     */       
/*     */ 
/* 101 */       Explanation tfExpl = scorer(reader).explain(doc);
/* 102 */       fieldExpl.addDetail(tfExpl);
/* 103 */       fieldExpl.addDetail(idfExpl);
/*     */       
/* 105 */       Explanation fieldNormExpl = new Explanation();
/* 106 */       byte[] fieldNorms = reader.norms(field);
/* 107 */       float fieldNorm = fieldNorms != null ? Similarity.decodeNorm(fieldNorms[doc]) : 0.0F;
/*     */       
/* 109 */       fieldNormExpl.setValue(fieldNorm);
/* 110 */       fieldNormExpl.setDescription("fieldNorm(field=" + field + ", doc=" + doc + ")");
/* 111 */       fieldExpl.addDetail(fieldNormExpl);
/*     */       
/* 113 */       fieldExpl.setValue(tfExpl.getValue() * idfExpl.getValue() * fieldNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/* 117 */       result.addDetail(fieldExpl);
/*     */       
/*     */ 
/* 120 */       result.setValue(queryExpl.getValue() * fieldExpl.getValue());
/*     */       
/* 122 */       if (queryExpl.getValue() == 1.0F) {
/* 123 */         return fieldExpl;
/*     */       }
/* 125 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public TermQuery(Term t)
/*     */   {
/* 131 */     this.term = t;
/*     */   }
/*     */   
/*     */   public Term getTerm() {
/* 135 */     return this.term;
/*     */   }
/*     */   
/* 138 */   protected Weight createWeight(Searcher searcher) { return new TermWeight(searcher); }
/*     */   
/*     */ 
/*     */   public String toString(String field)
/*     */   {
/* 143 */     StringBuffer buffer = new StringBuffer();
/* 144 */     if (!this.term.field().equals(field)) {
/* 145 */       buffer.append(this.term.field());
/* 146 */       buffer.append(":");
/*     */     }
/* 148 */     buffer.append(this.term.text());
/* 149 */     if (getBoost() != 1.0F) {
/* 150 */       buffer.append("^");
/* 151 */       buffer.append(Float.toString(getBoost()));
/*     */     }
/* 153 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 158 */     if (!(o instanceof TermQuery))
/* 159 */       return false;
/* 160 */     TermQuery other = (TermQuery)o;
/* 161 */     return (getBoost() == other.getBoost()) && (this.term.equals(other.term));
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 167 */     return Float.floatToIntBits(getBoost()) ^ this.term.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/TermQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */