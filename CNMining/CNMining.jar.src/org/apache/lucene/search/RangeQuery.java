/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.index.TermEnum;
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
/*     */ public class RangeQuery
/*     */   extends Query
/*     */ {
/*     */   private Term lowerTerm;
/*     */   private Term upperTerm;
/*     */   private boolean inclusive;
/*     */   
/*     */   public RangeQuery(Term lowerTerm, Term upperTerm, boolean inclusive)
/*     */   {
/*  44 */     if ((lowerTerm == null) && (upperTerm == null))
/*     */     {
/*  46 */       throw new IllegalArgumentException("At least one term must be non-null");
/*     */     }
/*  48 */     if ((lowerTerm != null) && (upperTerm != null) && (lowerTerm.field() != upperTerm.field()))
/*     */     {
/*  50 */       throw new IllegalArgumentException("Both terms must be for the same field");
/*     */     }
/*     */     
/*     */ 
/*  54 */     if (lowerTerm != null) {
/*  55 */       this.lowerTerm = lowerTerm;
/*     */     }
/*     */     else {
/*  58 */       this.lowerTerm = new Term(upperTerm.field(), "");
/*     */     }
/*     */     
/*  61 */     this.upperTerm = upperTerm;
/*  62 */     this.inclusive = inclusive;
/*     */   }
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
/*  74 */     BooleanQuery query = new BooleanQuery();
/*  75 */     TermEnum enumerator = reader.terms(this.lowerTerm);
/*     */     
/*     */     try
/*     */     {
/*  79 */       boolean checkLower = false;
/*  80 */       if (!this.inclusive) {
/*  81 */         checkLower = true;
/*     */       }
/*  83 */       String testField = getField();
/*     */       do
/*     */       {
/*  86 */         Term term = enumerator.term();
/*  87 */         if ((term == null) || (term.field() != testField)) break;
/*  88 */         if ((!checkLower) || (term.text().compareTo(this.lowerTerm.text()) > 0)) {
/*  89 */           checkLower = false;
/*  90 */           if (this.upperTerm != null) {
/*  91 */             int compare = this.upperTerm.text().compareTo(term.text());
/*     */             
/*     */ 
/*  94 */             if ((compare < 0) || ((!this.inclusive) && (compare == 0)))
/*     */               break;
/*     */           }
/*  97 */           TermQuery tq = new TermQuery(term);
/*  98 */           tq.setBoost(getBoost());
/*  99 */           query.add(tq, false, false);
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 106 */       while (enumerator.next());
/*     */     }
/*     */     finally {
/* 109 */       enumerator.close();
/*     */     }
/* 111 */     return query;
/*     */   }
/*     */   
/*     */   public Query combine(Query[] queries) {
/* 115 */     return Query.mergeBooleanQueries(queries);
/*     */   }
/*     */   
/*     */   public String getField()
/*     */   {
/* 120 */     return this.lowerTerm != null ? this.lowerTerm.field() : this.upperTerm.field();
/*     */   }
/*     */   
/*     */   public Term getLowerTerm() {
/* 124 */     return this.lowerTerm;
/*     */   }
/*     */   
/* 127 */   public Term getUpperTerm() { return this.upperTerm; }
/*     */   
/*     */   public boolean isInclusive() {
/* 130 */     return this.inclusive;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString(String field)
/*     */   {
/* 136 */     StringBuffer buffer = new StringBuffer();
/* 137 */     if (!getField().equals(field))
/*     */     {
/* 139 */       buffer.append(getField());
/* 140 */       buffer.append(":");
/*     */     }
/* 142 */     buffer.append(this.inclusive ? "[" : "{");
/* 143 */     buffer.append(this.lowerTerm != null ? this.lowerTerm.text() : "null");
/* 144 */     buffer.append(" TO ");
/* 145 */     buffer.append(this.upperTerm != null ? this.upperTerm.text() : "null");
/* 146 */     buffer.append(this.inclusive ? "]" : "}");
/* 147 */     if (getBoost() != 1.0F)
/*     */     {
/* 149 */       buffer.append("^");
/* 150 */       buffer.append(Float.toString(getBoost()));
/*     */     }
/* 152 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/RangeQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */