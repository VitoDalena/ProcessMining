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
/*    */ public class SpanFirstQuery
/*    */   extends SpanQuery
/*    */ {
/*    */   private SpanQuery match;
/*    */   private int end;
/*    */   
/*    */   public SpanFirstQuery(SpanQuery match, int end)
/*    */   {
/* 33 */     this.match = match;
/* 34 */     this.end = end;
/*    */   }
/*    */   
/*    */   public SpanQuery getMatch() {
/* 38 */     return this.match;
/*    */   }
/*    */   
/* 41 */   public int getEnd() { return this.end; }
/*    */   
/* 43 */   public String getField() { return this.match.getField(); }
/*    */   
/* 45 */   public Collection getTerms() { return this.match.getTerms(); }
/*    */   
/*    */   public String toString(String field) {
/* 48 */     StringBuffer buffer = new StringBuffer();
/* 49 */     buffer.append("spanFirst(");
/* 50 */     buffer.append(this.match.toString(field));
/* 51 */     buffer.append(", ");
/* 52 */     buffer.append(this.end);
/* 53 */     buffer.append(")");
/* 54 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public Spans getSpans(IndexReader reader) throws IOException {
/* 58 */     new Spans() {
/*    */       private Spans spans;
/*    */       private final IndexReader val$reader;
/*    */       
/* 62 */       public boolean next() throws IOException { while (this.spans.next()) {
/* 63 */           if (end() <= SpanFirstQuery.this.end)
/* 64 */             return true;
/*    */         }
/* 66 */         return false;
/*    */       }
/*    */       
/*    */       public boolean skipTo(int target) throws IOException {
/* 70 */         if (!this.spans.skipTo(target)) {
/* 71 */           return false;
/*    */         }
/* 73 */         if (this.spans.end() <= SpanFirstQuery.this.end) {
/* 74 */           return true;
/*    */         }
/* 76 */         return next();
/*    */       }
/*    */       
/* 79 */       public int doc() { return this.spans.doc(); }
/* 80 */       public int start() { return this.spans.start(); }
/* 81 */       public int end() { return this.spans.end(); }
/*    */       
/*    */       public String toString() {
/* 84 */         return "spans(" + SpanFirstQuery.this.toString() + ")";
/*    */       }
/*    */     };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanFirstQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */