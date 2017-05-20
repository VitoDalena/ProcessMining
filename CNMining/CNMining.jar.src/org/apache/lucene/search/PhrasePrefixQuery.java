/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.MultipleTermPositions;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.index.TermPositions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PhrasePrefixQuery
/*     */   extends Query
/*     */ {
/*     */   private String field;
/*     */   private ArrayList termArrays;
/*     */   private Vector positions;
/*     */   private int slop;
/*     */   
/*     */   public PhrasePrefixQuery()
/*     */   {
/*  43 */     this.termArrays = new ArrayList();
/*  44 */     this.positions = new Vector();
/*     */     
/*  46 */     this.slop = 0;
/*     */   }
/*     */   
/*     */   public void setSlop(int s)
/*     */   {
/*  51 */     this.slop = s;
/*     */   }
/*     */   
/*     */   public int getSlop()
/*     */   {
/*  56 */     return this.slop;
/*     */   }
/*     */   
/*     */   public void add(Term term)
/*     */   {
/*  61 */     add(new Term[] { term });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Term[] terms)
/*     */   {
/*  69 */     int position = 0;
/*  70 */     if (this.positions.size() > 0) {
/*  71 */       position = ((Integer)this.positions.lastElement()).intValue() + 1;
/*     */     }
/*  73 */     add(terms, position);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Term[] terms, int position)
/*     */   {
/*  84 */     if (this.termArrays.size() == 0) {
/*  85 */       this.field = terms[0].field();
/*     */     }
/*  87 */     for (int i = 0; i < terms.length; i++) {
/*  88 */       if (terms[i].field() != this.field) {
/*  89 */         throw new IllegalArgumentException("All phrase terms must be in the same field (" + this.field + "): " + terms[i]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  95 */     this.termArrays.add(terms);
/*  96 */     this.positions.addElement(new Integer(position));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] getPositions()
/*     */   {
/* 103 */     int[] result = new int[this.positions.size()];
/* 104 */     for (int i = 0; i < this.positions.size(); i++)
/* 105 */       result[i] = ((Integer)this.positions.elementAt(i)).intValue();
/* 106 */     return result;
/*     */   }
/*     */   
/*     */   private class PhrasePrefixWeight implements Weight {
/*     */     private Searcher searcher;
/*     */     private float value;
/*     */     private float idf;
/*     */     private float queryNorm;
/*     */     private float queryWeight;
/*     */     
/*     */     public PhrasePrefixWeight(Searcher searcher) {
/* 117 */       this.searcher = searcher;
/*     */     }
/*     */     
/* 120 */     public Query getQuery() { return PhrasePrefixQuery.this; }
/* 121 */     public float getValue() { return this.value; }
/*     */     
/*     */     public float sumOfSquaredWeights() throws IOException {
/* 124 */       Iterator i = PhrasePrefixQuery.this.termArrays.iterator();
/* 125 */       while (i.hasNext()) {
/* 126 */         Term[] terms = (Term[])i.next();
/* 127 */         for (int j = 0; j < terms.length; j++) {
/* 128 */           this.idf += PhrasePrefixQuery.this.getSimilarity(this.searcher).idf(terms[j], this.searcher);
/*     */         }
/*     */       }
/* 131 */       this.queryWeight = (this.idf * PhrasePrefixQuery.this.getBoost());
/* 132 */       return this.queryWeight * this.queryWeight;
/*     */     }
/*     */     
/*     */     public void normalize(float queryNorm) {
/* 136 */       this.queryNorm = queryNorm;
/* 137 */       this.queryWeight *= queryNorm;
/* 138 */       this.value = (this.queryWeight * this.idf);
/*     */     }
/*     */     
/*     */     public Scorer scorer(IndexReader reader) throws IOException {
/* 142 */       if (PhrasePrefixQuery.this.termArrays.size() == 0) {
/* 143 */         return null;
/*     */       }
/* 145 */       TermPositions[] tps = new TermPositions[PhrasePrefixQuery.this.termArrays.size()];
/* 146 */       for (int i = 0; i < tps.length; i++) {
/* 147 */         Term[] terms = (Term[])PhrasePrefixQuery.this.termArrays.get(i);
/*     */         TermPositions p;
/*     */         TermPositions p;
/* 150 */         if (terms.length > 1) {
/* 151 */           p = new MultipleTermPositions(reader, terms);
/*     */         } else {
/* 153 */           p = reader.termPositions(terms[0]);
/*     */         }
/* 155 */         if (p == null) {
/* 156 */           return null;
/*     */         }
/* 158 */         tps[i] = p;
/*     */       }
/*     */       
/* 161 */       if (PhrasePrefixQuery.this.slop == 0) {
/* 162 */         return new ExactPhraseScorer(this, tps, PhrasePrefixQuery.this.getPositions(), PhrasePrefixQuery.this.getSimilarity(this.searcher), reader.norms(PhrasePrefixQuery.this.field));
/*     */       }
/*     */       
/* 165 */       return new SloppyPhraseScorer(this, tps, PhrasePrefixQuery.this.getPositions(), PhrasePrefixQuery.this.getSimilarity(this.searcher), PhrasePrefixQuery.this.slop, reader.norms(PhrasePrefixQuery.this.field));
/*     */     }
/*     */     
/*     */     public Explanation explain(IndexReader reader, int doc)
/*     */       throws IOException
/*     */     {
/* 171 */       Explanation result = new Explanation();
/* 172 */       result.setDescription("weight(" + getQuery() + " in " + doc + "), product of:");
/*     */       
/* 174 */       Explanation idfExpl = new Explanation(this.idf, "idf(" + getQuery() + ")");
/*     */       
/*     */ 
/* 177 */       Explanation queryExpl = new Explanation();
/* 178 */       queryExpl.setDescription("queryWeight(" + getQuery() + "), product of:");
/*     */       
/* 180 */       Explanation boostExpl = new Explanation(PhrasePrefixQuery.this.getBoost(), "boost");
/* 181 */       if (PhrasePrefixQuery.this.getBoost() != 1.0F) {
/* 182 */         queryExpl.addDetail(boostExpl);
/*     */       }
/* 184 */       queryExpl.addDetail(idfExpl);
/*     */       
/* 186 */       Explanation queryNormExpl = new Explanation(this.queryNorm, "queryNorm");
/* 187 */       queryExpl.addDetail(queryNormExpl);
/*     */       
/* 189 */       queryExpl.setValue(boostExpl.getValue() * idfExpl.getValue() * queryNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/* 193 */       result.addDetail(queryExpl);
/*     */       
/*     */ 
/* 196 */       Explanation fieldExpl = new Explanation();
/* 197 */       fieldExpl.setDescription("fieldWeight(" + getQuery() + " in " + doc + "), product of:");
/*     */       
/*     */ 
/* 200 */       Explanation tfExpl = scorer(reader).explain(doc);
/* 201 */       fieldExpl.addDetail(tfExpl);
/* 202 */       fieldExpl.addDetail(idfExpl);
/*     */       
/* 204 */       Explanation fieldNormExpl = new Explanation();
/* 205 */       byte[] fieldNorms = reader.norms(PhrasePrefixQuery.this.field);
/* 206 */       float fieldNorm = fieldNorms != null ? Similarity.decodeNorm(fieldNorms[doc]) : 0.0F;
/*     */       
/* 208 */       fieldNormExpl.setValue(fieldNorm);
/* 209 */       fieldNormExpl.setDescription("fieldNorm(field=" + PhrasePrefixQuery.this.field + ", doc=" + doc + ")");
/* 210 */       fieldExpl.addDetail(fieldNormExpl);
/*     */       
/* 212 */       fieldExpl.setValue(tfExpl.getValue() * idfExpl.getValue() * fieldNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/* 216 */       result.addDetail(fieldExpl);
/*     */       
/*     */ 
/* 219 */       result.setValue(queryExpl.getValue() * fieldExpl.getValue());
/*     */       
/* 221 */       if (queryExpl.getValue() == 1.0F) {
/* 222 */         return fieldExpl;
/*     */       }
/* 224 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Weight createWeight(Searcher searcher) {
/* 229 */     if (this.termArrays.size() == 1) {
/* 230 */       Term[] terms = (Term[])this.termArrays.get(0);
/* 231 */       BooleanQuery boq = new BooleanQuery();
/* 232 */       for (int i = 0; i < terms.length; i++) {
/* 233 */         boq.add(new TermQuery(terms[i]), false, false);
/*     */       }
/* 235 */       boq.setBoost(getBoost());
/* 236 */       return boq.createWeight(searcher);
/*     */     }
/* 238 */     return new PhrasePrefixWeight(searcher);
/*     */   }
/*     */   
/*     */   public final String toString(String f)
/*     */   {
/* 243 */     StringBuffer buffer = new StringBuffer();
/* 244 */     if (!this.field.equals(f)) {
/* 245 */       buffer.append(this.field);
/* 246 */       buffer.append(":");
/*     */     }
/*     */     
/* 249 */     buffer.append("\"");
/* 250 */     Iterator i = this.termArrays.iterator();
/* 251 */     while (i.hasNext()) {
/* 252 */       Term[] terms = (Term[])i.next();
/* 253 */       buffer.append(terms[0].text() + (terms.length > 1 ? "*" : ""));
/* 254 */       if (i.hasNext())
/* 255 */         buffer.append(" ");
/*     */     }
/* 257 */     buffer.append("\"");
/*     */     
/* 259 */     if (this.slop != 0) {
/* 260 */       buffer.append("~");
/* 261 */       buffer.append(this.slop);
/*     */     }
/*     */     
/* 264 */     if (getBoost() != 1.0F) {
/* 265 */       buffer.append("^");
/* 266 */       buffer.append(Float.toString(getBoost()));
/*     */     }
/*     */     
/* 269 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PhrasePrefixQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */