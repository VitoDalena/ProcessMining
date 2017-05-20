/*    */ package org.apache.lucene.search.spans;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpanNotQuery
/*    */   extends SpanQuery
/*    */ {
/*    */   private SpanQuery include;
/*    */   private SpanQuery exclude;
/*    */   
/*    */   public SpanNotQuery(SpanQuery include, SpanQuery exclude)
/*    */   {
/* 33 */     this.include = include;
/* 34 */     this.exclude = exclude;
/*    */     
/* 36 */     if (!include.getField().equals(exclude.getField()))
/* 37 */       throw new IllegalArgumentException("Clauses must have same field.");
/*    */   }
/*    */   
/*    */   public SpanQuery getInclude() {
/* 41 */     return this.include;
/*    */   }
/*    */   
/* 44 */   public SpanQuery getExclude() { return this.exclude; }
/*    */   
/* 46 */   public String getField() { return this.include.getField(); }
/*    */   
/* 48 */   public Collection getTerms() { return this.include.getTerms(); }
/*    */   
/*    */   public String toString(String field) {
/* 51 */     StringBuffer buffer = new StringBuffer();
/* 52 */     buffer.append("spanNot(");
/* 53 */     buffer.append(this.include.toString(field));
/* 54 */     buffer.append(", ");
/* 55 */     buffer.append(this.exclude.toString(field));
/* 56 */     buffer.append(")");
/* 57 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public Spans getSpans(IndexReader reader) throws IOException
/*    */   {
/* 62 */     new Spans() {
/*    */       private Spans includeSpans;
/*    */       private boolean moreInclude;
/*    */       private Spans excludeSpans;
/*    */       private boolean moreExclude;
/*    */       private final IndexReader val$reader;
/*    */       
/*    */       public boolean next() throws IOException {
/* 70 */         if (this.moreInclude) {
/* 71 */           this.moreInclude = this.includeSpans.next();
/*    */         }
/* 73 */         while ((this.moreInclude) && (this.moreExclude))
/*    */         {
/* 75 */           if (this.includeSpans.doc() > this.excludeSpans.doc()) {
/* 76 */             this.moreExclude = this.excludeSpans.skipTo(this.includeSpans.doc());
/*    */           }
/*    */           
/*    */ 
/* 80 */           while ((this.moreExclude) && (this.includeSpans.doc() == this.excludeSpans.doc()) && (this.excludeSpans.end() <= this.includeSpans.start())) {
/* 81 */             this.moreExclude = this.excludeSpans.next();
/*    */           }
/*    */           
/* 84 */           if ((!this.moreExclude) || (this.includeSpans.doc() != this.excludeSpans.doc()) || (this.includeSpans.end() <= this.excludeSpans.start())) {
/*    */             break;
/*    */           }
/*    */           
/*    */ 
/* 89 */           this.moreInclude = this.includeSpans.next();
/*    */         }
/* 91 */         return this.moreInclude;
/*    */       }
/*    */       
/*    */       public boolean skipTo(int target) throws IOException {
/* 95 */         if (this.moreInclude) {
/* 96 */           this.moreInclude = this.includeSpans.skipTo(target);
/*    */         }
/* 98 */         if (!this.moreInclude) {
/* 99 */           return false;
/*    */         }
/* :1 */         if ((this.moreExclude) && (this.includeSpans.doc() > this.excludeSpans.doc()))
/*    */         {
/* :3 */           this.moreExclude = this.excludeSpans.skipTo(this.includeSpans.doc());
/*    */         }
/*    */         
/*    */ 
/* :7 */         while ((this.moreExclude) && (this.includeSpans.doc() == this.excludeSpans.doc()) && (this.excludeSpans.end() <= this.includeSpans.start())) {
/* :8 */           this.moreExclude = this.excludeSpans.next();
/*    */         }
/*    */         
/* ;1 */         if ((!this.moreExclude) || (this.includeSpans.doc() != this.excludeSpans.doc()) || (this.includeSpans.end() <= this.excludeSpans.start()))
/*    */         {
/*    */ 
/* ;4 */           return true;
/*    */         }
/* ;6 */         return next();
/*    */       }
/*    */       
/* ;9 */       public int doc() { return this.includeSpans.doc(); }
/* <0 */       public int start() { return this.includeSpans.start(); }
/* <1 */       public int end() { return this.includeSpans.end(); }
/*    */       
/*    */       public String toString() {
/* <4 */         return "spans(" + SpanNotQuery.this.toString() + ")";
/*    */       }
/*    */     };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanNotQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */