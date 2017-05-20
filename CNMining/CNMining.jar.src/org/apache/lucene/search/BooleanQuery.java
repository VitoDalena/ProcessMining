/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
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
/*     */ public class BooleanQuery
/*     */   extends Query
/*     */ {
/*  32 */   public static int maxClauseCount = Integer.parseInt(System.getProperty("org.apache.lucene.maxClauseCount", "1024"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getMaxClauseCount()
/*     */   {
/*  43 */     return maxClauseCount;
/*     */   }
/*     */   
/*     */   public static void setMaxClauseCount(int maxClauseCount) {
/*  47 */     maxClauseCount = maxClauseCount;
/*     */   }
/*     */   
/*  50 */   private Vector clauses = new Vector();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Query query, boolean required, boolean prohibited)
/*     */   {
/*  71 */     add(new BooleanClause(query, required, prohibited));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void add(BooleanClause clause)
/*     */   {
/*  78 */     if (this.clauses.size() >= maxClauseCount) {
/*  79 */       throw new TooManyClauses();
/*     */     }
/*  81 */     this.clauses.addElement(clause);
/*     */   }
/*     */   
/*     */   public BooleanClause[] getClauses()
/*     */   {
/*  86 */     return (BooleanClause[])this.clauses.toArray(new BooleanClause[0]);
/*     */   }
/*     */   
/*     */   private class BooleanWeight implements Weight {
/*     */     private Searcher searcher;
/*  91 */     private Vector weights = new Vector();
/*     */     
/*     */     public BooleanWeight(Searcher searcher) {
/*  94 */       this.searcher = searcher;
/*  95 */       for (int i = 0; i < BooleanQuery.this.clauses.size(); i++) {
/*  96 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/*  97 */         this.weights.add(c.query.createWeight(searcher));
/*     */       }
/*     */     }
/*     */     
/* 101 */     public Query getQuery() { return BooleanQuery.this; }
/* 102 */     public float getValue() { return BooleanQuery.this.getBoost(); }
/*     */     
/*     */     public float sumOfSquaredWeights() throws IOException {
/* 105 */       float sum = 0.0F;
/* 106 */       for (int i = 0; i < this.weights.size(); i++) {
/* 107 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/* 108 */         Weight w = (Weight)this.weights.elementAt(i);
/* 109 */         if (!c.prohibited) {
/* 110 */           sum += w.sumOfSquaredWeights();
/*     */         }
/*     */       }
/* 113 */       sum *= BooleanQuery.this.getBoost() * BooleanQuery.this.getBoost();
/*     */       
/* 115 */       return sum;
/*     */     }
/*     */     
/*     */     public void normalize(float norm)
/*     */     {
/* 120 */       norm *= BooleanQuery.this.getBoost();
/* 121 */       for (int i = 0; i < this.weights.size(); i++) {
/* 122 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/* 123 */         Weight w = (Weight)this.weights.elementAt(i);
/* 124 */         if (!c.prohibited) {
/* 125 */           w.normalize(norm);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Scorer scorer(IndexReader reader)
/*     */       throws IOException
/*     */     {
/* 136 */       boolean allRequired = true;
/* 137 */       boolean noneBoolean = true;
/* 138 */       for (int i = 0; i < this.weights.size(); i++) {
/* 139 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/* 140 */         if (!c.required)
/* 141 */           allRequired = false;
/* 142 */         if ((c.query instanceof BooleanQuery)) {
/* 143 */           noneBoolean = false;
/*     */         }
/*     */       }
/* 146 */       if ((allRequired) && (noneBoolean)) {
/* 147 */         ConjunctionScorer result = new ConjunctionScorer(BooleanQuery.this.getSimilarity(this.searcher));
/*     */         
/* 149 */         for (int i = 0; i < this.weights.size(); i++) {
/* 150 */           Weight w = (Weight)this.weights.elementAt(i);
/* 151 */           Scorer subScorer = w.scorer(reader);
/* 152 */           if (subScorer == null)
/* 153 */             return null;
/* 154 */           result.add(subScorer);
/*     */         }
/* 156 */         return result;
/*     */       }
/*     */       
/*     */ 
/* 160 */       BooleanScorer result = new BooleanScorer(BooleanQuery.this.getSimilarity(this.searcher));
/*     */       
/* 162 */       for (int i = 0; i < this.weights.size(); i++) {
/* 163 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/* 164 */         Weight w = (Weight)this.weights.elementAt(i);
/* 165 */         Scorer subScorer = w.scorer(reader);
/* 166 */         if (subScorer != null) {
/* 167 */           result.add(subScorer, c.required, c.prohibited);
/* 168 */         } else if (c.required) {
/* 169 */           return null;
/*     */         }
/*     */       }
/* 172 */       return result;
/*     */     }
/*     */     
/*     */     public Explanation explain(IndexReader reader, int doc) throws IOException
/*     */     {
/* 177 */       Explanation sumExpl = new Explanation();
/* 178 */       sumExpl.setDescription("sum of:");
/* 179 */       int coord = 0;
/* 180 */       int maxCoord = 0;
/* 181 */       float sum = 0.0F;
/* 182 */       for (int i = 0; i < this.weights.size(); i++) {
/* 183 */         BooleanClause c = (BooleanClause)BooleanQuery.this.clauses.elementAt(i);
/* 184 */         Weight w = (Weight)this.weights.elementAt(i);
/* 185 */         Explanation e = w.explain(reader, doc);
/* 186 */         if (!c.prohibited) maxCoord++;
/* 187 */         if (e.getValue() > 0.0F) {
/* 188 */           if (!c.prohibited) {
/* 189 */             sumExpl.addDetail(e);
/* 190 */             sum += e.getValue();
/* 191 */             coord++;
/*     */           } else {
/* 193 */             return new Explanation(0.0F, "match prohibited");
/*     */           }
/* 195 */         } else if (c.required) {
/* 196 */           return new Explanation(0.0F, "match required");
/*     */         }
/*     */       }
/* 199 */       sumExpl.setValue(sum);
/*     */       
/* 201 */       if (coord == 1) {
/* 202 */         sumExpl = sumExpl.getDetails()[0];
/*     */       }
/* 204 */       float coordFactor = BooleanQuery.this.getSimilarity(this.searcher).coord(coord, maxCoord);
/* 205 */       if (coordFactor == 1.0F) {
/* 206 */         return sumExpl;
/*     */       }
/* 208 */       Explanation result = new Explanation();
/* 209 */       result.setDescription("product of:");
/* 210 */       result.addDetail(sumExpl);
/* 211 */       result.addDetail(new Explanation(coordFactor, "coord(" + coord + "/" + maxCoord + ")"));
/*     */       
/* 213 */       result.setValue(sum * coordFactor);
/* 214 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Weight createWeight(Searcher searcher)
/*     */   {
/* 220 */     return new BooleanWeight(searcher);
/*     */   }
/*     */   
/*     */   public Query rewrite(IndexReader reader) throws IOException {
/* 224 */     if (this.clauses.size() == 1) {
/* 225 */       BooleanClause c = (BooleanClause)this.clauses.elementAt(0);
/* 226 */       if (!c.prohibited)
/*     */       {
/* 228 */         Query query = c.query.rewrite(reader);
/*     */         
/* 230 */         if (getBoost() != 1.0F) {
/* 231 */           if (query == c.query)
/* 232 */             query = (Query)query.clone();
/* 233 */           query.setBoost(getBoost() * query.getBoost());
/*     */         }
/*     */         
/* 236 */         return query;
/*     */       }
/*     */     }
/*     */     
/* 240 */     BooleanQuery clone = null;
/* 241 */     for (int i = 0; i < this.clauses.size(); i++) {
/* 242 */       BooleanClause c = (BooleanClause)this.clauses.elementAt(i);
/* 243 */       Query query = c.query.rewrite(reader);
/* 244 */       if (query != c.query) {
/* 245 */         if (clone == null)
/* 246 */           clone = (BooleanQuery)clone();
/* 247 */         clone.clauses.setElementAt(new BooleanClause(query, c.required, c.prohibited), i);
/*     */       }
/*     */     }
/*     */     
/* 251 */     if (clone != null) {
/* 252 */       return clone;
/*     */     }
/* 254 */     return this;
/*     */   }
/*     */   
/*     */   public Object clone()
/*     */   {
/* 259 */     BooleanQuery clone = (BooleanQuery)super.clone();
/* 260 */     clone.clauses = ((Vector)this.clauses.clone());
/* 261 */     return clone;
/*     */   }
/*     */   
/*     */   public String toString(String field)
/*     */   {
/* 266 */     StringBuffer buffer = new StringBuffer();
/* 267 */     if (getBoost() != 1.0D) {
/* 268 */       buffer.append("(");
/*     */     }
/*     */     
/* 271 */     for (int i = 0; i < this.clauses.size(); i++) {
/* 272 */       BooleanClause c = (BooleanClause)this.clauses.elementAt(i);
/* 273 */       if (c.prohibited) {
/* 274 */         buffer.append("-");
/* 275 */       } else if (c.required) {
/* 276 */         buffer.append("+");
/*     */       }
/* 278 */       Query subQuery = c.query;
/* 279 */       if ((subQuery instanceof BooleanQuery)) {
/* 280 */         buffer.append("(");
/* 281 */         buffer.append(c.query.toString(field));
/* 282 */         buffer.append(")");
/*     */       } else {
/* 284 */         buffer.append(c.query.toString(field));
/*     */       }
/* 286 */       if (i != this.clauses.size() - 1) {
/* 287 */         buffer.append(" ");
/*     */       }
/*     */     }
/* 290 */     if (getBoost() != 1.0D) {
/* 291 */       buffer.append(")^");
/* 292 */       buffer.append(getBoost());
/*     */     }
/*     */     
/* 295 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 300 */     if (!(o instanceof BooleanQuery))
/* 301 */       return false;
/* 302 */     BooleanQuery other = (BooleanQuery)o;
/* 303 */     return (getBoost() == other.getBoost()) && (this.clauses.equals(other.clauses));
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 309 */     return Float.floatToIntBits(getBoost()) ^ this.clauses.hashCode();
/*     */   }
/*     */   
/*     */   public static class TooManyClauses
/*     */     extends RuntimeException
/*     */   {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/BooleanQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */