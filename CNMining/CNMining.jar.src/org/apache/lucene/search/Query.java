/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
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
/*     */ public abstract class Query
/*     */   implements Serializable, Cloneable
/*     */ {
/*  46 */   private float boost = 1.0F;
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBoost(float b)
/*     */   {
/*  52 */     this.boost = b;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getBoost()
/*     */   {
/*  58 */     return this.boost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String toString(String paramString);
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  70 */     return toString("");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Weight createWeight(Searcher searcher)
/*     */   {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Weight weight(Searcher searcher)
/*     */     throws IOException
/*     */   {
/*  84 */     Query query = searcher.rewrite(this);
/*  85 */     Weight weight = query.createWeight(searcher);
/*  86 */     float sum = weight.sumOfSquaredWeights();
/*  87 */     float norm = getSimilarity(searcher).queryNorm(sum);
/*  88 */     weight.normalize(norm);
/*  89 */     return weight;
/*     */   }
/*     */   
/*     */   public Query rewrite(IndexReader reader) throws IOException
/*     */   {
/*  94 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Query combine(Query[] queries)
/*     */   {
/* 103 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Query mergeBooleanQueries(Query[] queries)
/*     */   {
/* 113 */     HashSet allClauses = new HashSet();
/* 114 */     for (int i = 0; i < queries.length; i++) {
/* 115 */       BooleanClause[] clauses = ((BooleanQuery)queries[i]).getClauses();
/* 116 */       for (int j = 0; j < clauses.length; j++) {
/* 117 */         allClauses.add(clauses[j]);
/*     */       }
/*     */     }
/*     */     
/* 121 */     BooleanQuery result = new BooleanQuery();
/* 122 */     Iterator i = allClauses.iterator();
/* 123 */     while (i.hasNext()) {
/* 124 */       result.add((BooleanClause)i.next());
/*     */     }
/* 126 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Similarity getSimilarity(Searcher searcher)
/*     */   {
/* 134 */     return searcher.getSimilarity();
/*     */   }
/*     */   
/*     */   public Object clone()
/*     */   {
/*     */     try {
/* 140 */       return (Query)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 142 */       throw new RuntimeException("Clone not supported: " + e.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Query.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */