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
/*    */ public class WildcardQuery
/*    */   extends MultiTermQuery
/*    */ {
/*    */   public WildcardQuery(Term term)
/*    */   {
/* 34 */     super(term);
/*    */   }
/*    */   
/*    */   protected FilteredTermEnum getEnum(IndexReader reader) throws IOException {
/* 38 */     return new WildcardTermEnum(reader, getTerm());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/WildcardQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */