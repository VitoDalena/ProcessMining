/*     */ package org.apache.lucene.search.spans;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.util.PriorityQueue;
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
/*     */ public class SpanOrQuery
/*     */   extends SpanQuery
/*     */ {
/*     */   private List clauses;
/*     */   private String field;
/*     */   
/*     */   public SpanOrQuery(SpanQuery[] clauses)
/*     */   {
/*  38 */     this.clauses = new ArrayList(clauses.length);
/*  39 */     for (int i = 0; i < clauses.length; i++) {
/*  40 */       SpanQuery clause = clauses[i];
/*  41 */       if (i == 0) {
/*  42 */         this.field = clause.getField();
/*  43 */       } else if (!clause.getField().equals(this.field)) {
/*  44 */         throw new IllegalArgumentException("Clauses must have same field.");
/*     */       }
/*  46 */       this.clauses.add(clause);
/*     */     }
/*     */   }
/*     */   
/*     */   public SpanQuery[] getClauses()
/*     */   {
/*  52 */     return (SpanQuery[])this.clauses.toArray(new SpanQuery[this.clauses.size()]);
/*     */   }
/*     */   
/*  55 */   public String getField() { return this.field; }
/*     */   
/*     */   public Collection getTerms() {
/*  58 */     Collection terms = new ArrayList();
/*  59 */     Iterator i = this.clauses.iterator();
/*  60 */     while (i.hasNext()) {
/*  61 */       SpanQuery clause = (SpanQuery)i.next();
/*  62 */       terms.addAll(clause.getTerms());
/*     */     }
/*  64 */     return terms;
/*     */   }
/*     */   
/*     */   public String toString(String field) {
/*  68 */     StringBuffer buffer = new StringBuffer();
/*  69 */     buffer.append("spanOr([");
/*  70 */     Iterator i = this.clauses.iterator();
/*  71 */     while (i.hasNext()) {
/*  72 */       SpanQuery clause = (SpanQuery)i.next();
/*  73 */       buffer.append(clause.toString(field));
/*  74 */       if (i.hasNext()) {
/*  75 */         buffer.append(", ");
/*     */       }
/*     */     }
/*  78 */     buffer.append("])");
/*  79 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private class SpanQueue extends PriorityQueue {
/*     */     public SpanQueue(int size) {
/*  84 */       initialize(size);
/*     */     }
/*     */     
/*     */     protected final boolean lessThan(Object o1, Object o2) {
/*  88 */       Spans spans1 = (Spans)o1;
/*  89 */       Spans spans2 = (Spans)o2;
/*  90 */       if (spans1.doc() == spans2.doc()) {
/*  91 */         if (spans1.start() == spans2.start()) {
/*  92 */           return spans1.end() < spans2.end();
/*     */         }
/*  94 */         return spans1.start() < spans2.start();
/*     */       }
/*     */       
/*  97 */       return spans1.doc() < spans2.doc();
/*     */     }
/*     */   }
/*     */   
/*     */   public Spans getSpans(IndexReader reader)
/*     */     throws IOException
/*     */   {
/* 104 */     if (this.clauses.size() == 1) {
/* 105 */       return ((SpanQuery)this.clauses.get(0)).getSpans(reader);
/*     */     }
/* 107 */     new Spans()
/*     */     {
/*     */       private List all;
/*     */       
/*     */       private SpanOrQuery.SpanQueue queue;
/*     */       
/*     */       private boolean firstTime;
/*     */       
/*     */       private final IndexReader val$reader;
/*     */       
/*     */ 
/*     */       public boolean next()
/*     */         throws IOException
/*     */       {
/* 121 */         if (this.firstTime) {
/* 122 */           for (int i = 0; i < this.all.size(); i++) {
/* 123 */             Spans spans = (Spans)this.all.get(i);
/* 124 */             if (spans.next()) {
/* 125 */               this.queue.put(spans);
/*     */             } else {
/* 127 */               this.all.remove(i--);
/*     */             }
/*     */           }
/* 130 */           this.firstTime = false;
/* 131 */           return this.queue.size() != 0;
/*     */         }
/*     */         
/* 134 */         if (this.queue.size() == 0) {
/* 135 */           return false;
/*     */         }
/*     */         
/* 138 */         if (top().next()) {
/* 139 */           this.queue.adjustTop();
/* 140 */           return true;
/*     */         }
/*     */         
/* 143 */         this.all.remove(this.queue.pop());
/*     */         
/* 145 */         return this.queue.size() != 0;
/*     */       }
/*     */       
/* 148 */       private Spans top() { return (Spans)this.queue.top(); }
/*     */       
/*     */       public boolean skipTo(int target) throws IOException {
/* 151 */         if (this.firstTime) {
/* 152 */           for (int i = 0; i < this.all.size(); i++) {
/* 153 */             Spans spans = (Spans)this.all.get(i);
/* 154 */             if (spans.skipTo(target)) {
/* 155 */               this.queue.put(spans);
/*     */             } else {
/* 157 */               this.all.remove(i--);
/*     */             }
/*     */           }
/* 160 */           this.firstTime = false;
/*     */         } else {
/* 162 */           while ((this.queue.size() != 0) && (top().doc() < target)) {
/* 163 */             if (top().skipTo(target)) {
/* 164 */               this.queue.adjustTop();
/*     */             } else {
/* 166 */               this.all.remove(this.queue.pop());
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 171 */         return this.queue.size() != 0;
/*     */       }
/*     */       
/* 174 */       public int doc() { return top().doc(); }
/* 175 */       public int start() { return top().start(); }
/* 176 */       public int end() { return top().end(); }
/*     */       
/*     */       public String toString() {
/* 179 */         return "spans(" + SpanOrQuery.this + ")@" + (this.queue.size() > 0 ? doc() + ":" + start() + "-" + end() : this.firstTime ? "START" : "END");
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanOrQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */