/*    */ package org.apache.lucene.search.spans;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ import org.apache.lucene.search.Query;
/*    */ import org.apache.lucene.search.Searcher;
/*    */ import org.apache.lucene.search.Weight;
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
/*    */ public abstract class SpanQuery
/*    */   extends Query
/*    */ {
/*    */   public abstract Spans getSpans(IndexReader paramIndexReader)
/*    */     throws IOException;
/*    */   
/*    */   public abstract String getField();
/*    */   
/*    */   public abstract Collection getTerms();
/*    */   
/*    */   protected Weight createWeight(Searcher searcher)
/*    */   {
/* 41 */     return new SpanWeight(this, searcher);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */