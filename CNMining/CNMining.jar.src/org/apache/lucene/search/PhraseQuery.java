/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.index.IndexReader;
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
/*     */ public class PhraseQuery
/*     */   extends Query
/*     */ {
/*     */   private String field;
/*  31 */   private Vector terms = new Vector();
/*  32 */   private Vector positions = new Vector();
/*  33 */   private int slop = 0;
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
/*  52 */   public void setSlop(int s) { this.slop = s; }
/*     */   
/*  54 */   public int getSlop() { return this.slop; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Term term)
/*     */   {
/*  61 */     int position = 0;
/*  62 */     if (this.positions.size() > 0) {
/*  63 */       position = ((Integer)this.positions.lastElement()).intValue() + 1;
/*     */     }
/*  65 */     add(term, position);
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
/*     */   public void add(Term term, int position)
/*     */   {
/*  78 */     if (this.terms.size() == 0) {
/*  79 */       this.field = term.field();
/*  80 */     } else if (term.field() != this.field) {
/*  81 */       throw new IllegalArgumentException("All phrase terms must be in the same field: " + term);
/*     */     }
/*  83 */     this.terms.addElement(term);
/*  84 */     this.positions.addElement(new Integer(position));
/*     */   }
/*     */   
/*     */   public Term[] getTerms()
/*     */   {
/*  89 */     return (Term[])this.terms.toArray(new Term[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] getPositions()
/*     */   {
/*  96 */     int[] result = new int[this.positions.size()];
/*  97 */     for (int i = 0; i < this.positions.size(); i++)
/*  98 */       result[i] = ((Integer)this.positions.elementAt(i)).intValue();
/*  99 */     return result;
/*     */   }
/*     */   
/*     */   private class PhraseWeight implements Weight {
/*     */     private Searcher searcher;
/*     */     private float value;
/*     */     private float idf;
/*     */     private float queryNorm;
/*     */     private float queryWeight;
/*     */     
/*     */     public PhraseWeight(Searcher searcher) {
/* 110 */       this.searcher = searcher;
/*     */     }
/*     */     
/* 113 */     public String toString() { return "weight(" + PhraseQuery.this + ")"; }
/*     */     
/* 115 */     public Query getQuery() { return PhraseQuery.this; }
/* 116 */     public float getValue() { return this.value; }
/*     */     
/*     */     public float sumOfSquaredWeights() throws IOException {
/* 119 */       this.idf = PhraseQuery.this.getSimilarity(this.searcher).idf(PhraseQuery.this.terms, this.searcher);
/* 120 */       this.queryWeight = (this.idf * PhraseQuery.this.getBoost());
/* 121 */       return this.queryWeight * this.queryWeight;
/*     */     }
/*     */     
/*     */     public void normalize(float queryNorm) {
/* 125 */       this.queryNorm = queryNorm;
/* 126 */       this.queryWeight *= queryNorm;
/* 127 */       this.value = (this.queryWeight * this.idf);
/*     */     }
/*     */     
/*     */     public Scorer scorer(IndexReader reader) throws IOException {
/* 131 */       if (PhraseQuery.this.terms.size() == 0) {
/* 132 */         return null;
/*     */       }
/* 134 */       TermPositions[] tps = new TermPositions[PhraseQuery.this.terms.size()];
/* 135 */       for (int i = 0; i < PhraseQuery.this.terms.size(); i++) {
/* 136 */         TermPositions p = reader.termPositions((Term)PhraseQuery.this.terms.elementAt(i));
/* 137 */         if (p == null)
/* 138 */           return null;
/* 139 */         tps[i] = p;
/*     */       }
/*     */       
/* 142 */       if (PhraseQuery.this.slop == 0) {
/* 143 */         return new ExactPhraseScorer(this, tps, PhraseQuery.this.getPositions(), PhraseQuery.this.getSimilarity(this.searcher), reader.norms(PhraseQuery.this.field));
/*     */       }
/*     */       
/* 146 */       return new SloppyPhraseScorer(this, tps, PhraseQuery.this.getPositions(), PhraseQuery.this.getSimilarity(this.searcher), PhraseQuery.this.slop, reader.norms(PhraseQuery.this.field));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Explanation explain(IndexReader reader, int doc)
/*     */       throws IOException
/*     */     {
/* 155 */       Explanation result = new Explanation();
/* 156 */       result.setDescription("weight(" + getQuery() + " in " + doc + "), product of:");
/*     */       
/* 158 */       StringBuffer docFreqs = new StringBuffer();
/* 159 */       StringBuffer query = new StringBuffer();
/* 160 */       query.append('"');
/* 161 */       for (int i = 0; i < PhraseQuery.this.terms.size(); i++) {
/* 162 */         if (i != 0) {
/* 163 */           docFreqs.append(" ");
/* 164 */           query.append(" ");
/*     */         }
/*     */         
/* 167 */         Term term = (Term)PhraseQuery.this.terms.elementAt(i);
/*     */         
/* 169 */         docFreqs.append(term.text());
/* 170 */         docFreqs.append("=");
/* 171 */         docFreqs.append(this.searcher.docFreq(term));
/*     */         
/* 173 */         query.append(term.text());
/*     */       }
/* 175 */       query.append('"');
/*     */       
/* 177 */       Explanation idfExpl = new Explanation(this.idf, "idf(" + PhraseQuery.this.field + ": " + docFreqs + ")");
/*     */       
/*     */ 
/*     */ 
/* 181 */       Explanation queryExpl = new Explanation();
/* 182 */       queryExpl.setDescription("queryWeight(" + getQuery() + "), product of:");
/*     */       
/* 184 */       Explanation boostExpl = new Explanation(PhraseQuery.this.getBoost(), "boost");
/* 185 */       if (PhraseQuery.this.getBoost() != 1.0F)
/* 186 */         queryExpl.addDetail(boostExpl);
/* 187 */       queryExpl.addDetail(idfExpl);
/*     */       
/* 189 */       Explanation queryNormExpl = new Explanation(this.queryNorm, "queryNorm");
/* 190 */       queryExpl.addDetail(queryNormExpl);
/*     */       
/* 192 */       queryExpl.setValue(boostExpl.getValue() * idfExpl.getValue() * queryNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/* 196 */       result.addDetail(queryExpl);
/*     */       
/*     */ 
/* 199 */       Explanation fieldExpl = new Explanation();
/* 200 */       fieldExpl.setDescription("fieldWeight(" + PhraseQuery.this.field + ":" + query + " in " + doc + "), product of:");
/*     */       
/*     */ 
/* 203 */       Explanation tfExpl = scorer(reader).explain(doc);
/* 204 */       fieldExpl.addDetail(tfExpl);
/* 205 */       fieldExpl.addDetail(idfExpl);
/*     */       
/* 207 */       Explanation fieldNormExpl = new Explanation();
/* 208 */       byte[] fieldNorms = reader.norms(PhraseQuery.this.field);
/* 209 */       float fieldNorm = fieldNorms != null ? Similarity.decodeNorm(fieldNorms[doc]) : 0.0F;
/*     */       
/* 211 */       fieldNormExpl.setValue(fieldNorm);
/* 212 */       fieldNormExpl.setDescription("fieldNorm(field=" + PhraseQuery.this.field + ", doc=" + doc + ")");
/* 213 */       fieldExpl.addDetail(fieldNormExpl);
/*     */       
/* 215 */       fieldExpl.setValue(tfExpl.getValue() * idfExpl.getValue() * fieldNormExpl.getValue());
/*     */       
/*     */ 
/*     */ 
/* 219 */       result.addDetail(fieldExpl);
/*     */       
/*     */ 
/* 222 */       result.setValue(queryExpl.getValue() * fieldExpl.getValue());
/*     */       
/* 224 */       if (queryExpl.getValue() == 1.0F) {
/* 225 */         return fieldExpl;
/*     */       }
/* 227 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Weight createWeight(Searcher searcher) {
/* 232 */     if (this.terms.size() == 1) {
/* 233 */       Term term = (Term)this.terms.elementAt(0);
/* 234 */       Query termQuery = new TermQuery(term);
/* 235 */       termQuery.setBoost(getBoost());
/* 236 */       return termQuery.createWeight(searcher);
/*     */     }
/* 238 */     return new PhraseWeight(searcher);
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString(String f)
/*     */   {
/* 244 */     StringBuffer buffer = new StringBuffer();
/* 245 */     if (!this.field.equals(f)) {
/* 246 */       buffer.append(this.field);
/* 247 */       buffer.append(":");
/*     */     }
/*     */     
/* 250 */     buffer.append("\"");
/* 251 */     for (int i = 0; i < this.terms.size(); i++) {
/* 252 */       buffer.append(((Term)this.terms.elementAt(i)).text());
/* 253 */       if (i != this.terms.size() - 1)
/* 254 */         buffer.append(" ");
/*     */     }
/* 256 */     buffer.append("\"");
/*     */     
/* 258 */     if (this.slop != 0) {
/* 259 */       buffer.append("~");
/* 260 */       buffer.append(this.slop);
/*     */     }
/*     */     
/* 263 */     if (getBoost() != 1.0F) {
/* 264 */       buffer.append("^");
/* 265 */       buffer.append(Float.toString(getBoost()));
/*     */     }
/*     */     
/* 268 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 273 */     if (!(o instanceof PhraseQuery))
/* 274 */       return false;
/* 275 */     PhraseQuery other = (PhraseQuery)o;
/* 276 */     return (getBoost() == other.getBoost()) && (this.slop == other.slop) && (this.terms.equals(other.terms)) && (this.positions.equals(other.positions));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 284 */     return Float.floatToIntBits(getBoost()) ^ Float.floatToIntBits(this.slop) ^ this.terms.hashCode() ^ this.positions.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PhraseQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */