/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ import org.apache.lucene.index.Term;
/*    */ import org.apache.lucene.index.TermEnum;
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
/*    */ public class PrefixQuery
/*    */   extends Query
/*    */ {
/*    */   private Term prefix;
/*    */   
/*    */   public PrefixQuery(Term prefix)
/*    */   {
/* 31 */     this.prefix = prefix;
/*    */   }
/*    */   
/*    */ 
/* 35 */   public Term getPrefix() { return this.prefix; }
/*    */   
/*    */   public Query rewrite(IndexReader reader) throws IOException {
/* 38 */     BooleanQuery query = new BooleanQuery();
/* 39 */     TermEnum enumerator = reader.terms(this.prefix);
/*    */     try {
/* 41 */       String prefixText = this.prefix.text();
/* 42 */       String prefixField = this.prefix.field();
/*    */       do {
/* 44 */         Term term = enumerator.term();
/* 45 */         if ((term == null) || (!term.text().startsWith(prefixText)) || (term.field() != prefixField)) {
/*    */           break;
/*    */         }
/* 48 */         TermQuery tq = new TermQuery(term);
/* 49 */         tq.setBoost(getBoost());
/* 50 */         query.add(tq, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */       }
/* 55 */       while (enumerator.next());
/*    */     } finally {
/* 57 */       enumerator.close();
/*    */     }
/* 59 */     return query;
/*    */   }
/*    */   
/*    */   public Query combine(Query[] queries) {
/* 63 */     return Query.mergeBooleanQueries(queries);
/*    */   }
/*    */   
/*    */   public String toString(String field)
/*    */   {
/* 68 */     StringBuffer buffer = new StringBuffer();
/* 69 */     if (!this.prefix.field().equals(field)) {
/* 70 */       buffer.append(this.prefix.field());
/* 71 */       buffer.append(":");
/*    */     }
/* 73 */     buffer.append(this.prefix.text());
/* 74 */     buffer.append('*');
/* 75 */     if (getBoost() != 1.0F) {
/* 76 */       buffer.append("^");
/* 77 */       buffer.append(Float.toString(getBoost()));
/*    */     }
/* 79 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PrefixQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */