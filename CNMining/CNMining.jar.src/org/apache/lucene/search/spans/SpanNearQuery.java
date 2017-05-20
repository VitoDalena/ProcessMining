/*     */ package org.apache.lucene.search.spans;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class SpanNearQuery
/*     */   extends SpanQuery
/*     */ {
/*     */   private List clauses;
/*     */   private int slop;
/*     */   private boolean inOrder;
/*     */   private String field;
/*     */   
/*     */   public SpanNearQuery(SpanQuery[] clauses, int slop, boolean inOrder)
/*     */   {
/*  46 */     this.clauses = new ArrayList(clauses.length);
/*  47 */     for (int i = 0; i < clauses.length; i++) {
/*  48 */       SpanQuery clause = clauses[i];
/*  49 */       if (i == 0) {
/*  50 */         this.field = clause.getField();
/*  51 */       } else if (!clause.getField().equals(this.field)) {
/*  52 */         throw new IllegalArgumentException("Clauses must have same field.");
/*     */       }
/*  54 */       this.clauses.add(clause);
/*     */     }
/*     */     
/*  57 */     this.slop = slop;
/*  58 */     this.inOrder = inOrder;
/*     */   }
/*     */   
/*     */   public SpanQuery[] getClauses()
/*     */   {
/*  63 */     return (SpanQuery[])this.clauses.toArray(new SpanQuery[this.clauses.size()]);
/*     */   }
/*     */   
/*     */   public int getSlop() {
/*  67 */     return this.slop;
/*     */   }
/*     */   
/*  70 */   public boolean isInOrder() { return this.inOrder; }
/*     */   
/*  72 */   public String getField() { return this.field; }
/*     */   
/*     */   public Collection getTerms() {
/*  75 */     Collection terms = new ArrayList();
/*  76 */     Iterator i = this.clauses.iterator();
/*  77 */     while (i.hasNext()) {
/*  78 */       SpanQuery clause = (SpanQuery)i.next();
/*  79 */       terms.addAll(clause.getTerms());
/*     */     }
/*  81 */     return terms;
/*     */   }
/*     */   
/*     */   public String toString(String field) {
/*  85 */     StringBuffer buffer = new StringBuffer();
/*  86 */     buffer.append("spanNear([");
/*  87 */     Iterator i = this.clauses.iterator();
/*  88 */     while (i.hasNext()) {
/*  89 */       SpanQuery clause = (SpanQuery)i.next();
/*  90 */       buffer.append(clause.toString(field));
/*  91 */       if (i.hasNext()) {
/*  92 */         buffer.append(", ");
/*     */       }
/*     */     }
/*  95 */     buffer.append("], ");
/*  96 */     buffer.append(this.slop);
/*  97 */     buffer.append(", ");
/*  98 */     buffer.append(this.inOrder);
/*  99 */     buffer.append(")");
/* 100 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public Spans getSpans(IndexReader reader) throws IOException {
/* 104 */     if (this.clauses.size() == 0) {
/* 105 */       return new SpanOrQuery(getClauses()).getSpans(reader);
/*     */     }
/* 107 */     if (this.clauses.size() == 1) {
/* 108 */       return ((SpanQuery)this.clauses.get(0)).getSpans(reader);
/*     */     }
/* 110 */     return new NearSpans(this, reader);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanNearQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */