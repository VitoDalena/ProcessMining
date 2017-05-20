/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ import org.apache.lucene.index.Term;
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
/*    */ public abstract class MultiTermQuery
/*    */   extends Query
/*    */ {
/*    */   private Term term;
/*    */   
/*    */   public MultiTermQuery(Term term)
/*    */   {
/* 42 */     this.term = term;
/*    */   }
/*    */   
/*    */   public Term getTerm() {
/* 46 */     return this.term;
/*    */   }
/*    */   
/*    */   protected abstract FilteredTermEnum getEnum(IndexReader paramIndexReader) throws IOException;
/*    */   
/*    */   public Query rewrite(IndexReader reader) throws IOException
/*    */   {
/* 53 */     FilteredTermEnum enumerator = getEnum(reader);
/* 54 */     BooleanQuery query = new BooleanQuery();
/*    */     try {
/*    */       do {
/* 57 */         Term t = enumerator.term();
/* 58 */         if (t != null) {
/* 59 */           TermQuery tq = new TermQuery(t);
/* 60 */           tq.setBoost(getBoost() * enumerator.difference());
/* 61 */           query.add(tq, false, false);
/*    */         }
/* 63 */       } while (enumerator.next());
/*    */     } finally {
/* 65 */       enumerator.close();
/*    */     }
/* 67 */     return query;
/*    */   }
/*    */   
/*    */   public Query combine(Query[] queries) {
/* 71 */     return Query.mergeBooleanQueries(queries);
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString(String field)
/*    */   {
/* 77 */     StringBuffer buffer = new StringBuffer();
/* 78 */     if (!this.term.field().equals(field)) {
/* 79 */       buffer.append(this.term.field());
/* 80 */       buffer.append(":");
/*    */     }
/* 82 */     buffer.append(this.term.text());
/* 83 */     if (getBoost() != 1.0F) {
/* 84 */       buffer.append("^");
/* 85 */       buffer.append(Float.toString(getBoost()));
/*    */     }
/* 87 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/MultiTermQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */