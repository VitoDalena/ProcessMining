/*    */ package org.apache.lucene.search.spans;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ import org.apache.lucene.index.Term;
/*    */ import org.apache.lucene.index.TermPositions;
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
/*    */ public class SpanTermQuery
/*    */   extends SpanQuery
/*    */ {
/*    */   private Term term;
/*    */   
/*    */   public SpanTermQuery(Term term)
/*    */   {
/* 33 */     this.term = term;
/*    */   }
/*    */   
/* 36 */   public Term getTerm() { return this.term; }
/*    */   
/* 38 */   public String getField() { return this.term.field(); }
/*    */   
/*    */   public Collection getTerms() {
/* 41 */     Collection terms = new ArrayList();
/* 42 */     terms.add(this.term);
/* 43 */     return terms;
/*    */   }
/*    */   
/*    */   public String toString(String field) {
/* 47 */     if (this.term.field().equals(field)) {
/* 48 */       return this.term.text();
/*    */     }
/* 50 */     return this.term.toString();
/*    */   }
/*    */   
/*    */   public Spans getSpans(IndexReader reader) throws IOException {
/* 54 */     new Spans() {
/*    */       private TermPositions positions;
/*    */       private int doc;
/*    */       private int freq;
/*    */       private int count;
/*    */       private int position;
/*    */       private final IndexReader val$reader;
/*    */       
/*    */       public boolean next() throws IOException {
/* 63 */         if (this.count == this.freq) {
/* 64 */           if (!this.positions.next()) {
/* 65 */             this.doc = Integer.MAX_VALUE;
/* 66 */             return false;
/*    */           }
/* 68 */           this.doc = this.positions.doc();
/* 69 */           this.freq = this.positions.freq();
/* 70 */           this.count = 0;
/*    */         }
/* 72 */         this.position = this.positions.nextPosition();
/* 73 */         this.count += 1;
/* 74 */         return true;
/*    */       }
/*    */       
/*    */       public boolean skipTo(int target) throws IOException {
/* 78 */         if (!this.positions.skipTo(target)) {
/* 79 */           this.doc = Integer.MAX_VALUE;
/* 80 */           return false;
/*    */         }
/*    */         
/* 83 */         this.doc = this.positions.doc();
/* 84 */         this.freq = this.positions.freq();
/* 85 */         this.count = 0;
/*    */         
/* 87 */         this.position = this.positions.nextPosition();
/* 88 */         this.count += 1;
/*    */         
/* 90 */         return true;
/*    */       }
/*    */       
/* 93 */       public int doc() { return this.doc; }
/* 94 */       public int start() { return this.position; }
/* 95 */       public int end() { return this.position + 1; }
/*    */       
/*    */       public String toString() {
/* 98 */         return "spans(" + SpanTermQuery.this.toString() + ")@" + (this.doc == Integer.MAX_VALUE ? "END" : this.doc == -1 ? "START" : new StringBuffer().append(this.doc).append("-").append(this.position).toString());
/*    */       }
/*    */     };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanTermQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */