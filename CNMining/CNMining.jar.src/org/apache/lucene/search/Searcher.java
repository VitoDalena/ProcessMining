/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public abstract class Searcher
/*    */   implements Searchable
/*    */ {
/*    */   public final Hits search(Query query)
/*    */     throws IOException
/*    */   {
/* 27 */     return search(query, (Filter)null);
/*    */   }
/*    */   
/*    */   public Hits search(Query query, Filter filter)
/*    */     throws IOException
/*    */   {
/* 33 */     return new Hits(this, query, filter);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Hits search(Query query, Sort sort)
/*    */     throws IOException
/*    */   {
/* 41 */     return new Hits(this, query, null, sort);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Hits search(Query query, Filter filter, Sort sort)
/*    */     throws IOException
/*    */   {
/* 49 */     return new Hits(this, query, filter, sort);
/*    */   }
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
/*    */   public void search(Query query, HitCollector results)
/*    */     throws IOException
/*    */   {
/* 67 */     search(query, (Filter)null, results);
/*    */   }
/*    */   
/*    */ 
/* 71 */   private Similarity similarity = Similarity.getDefault();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setSimilarity(Similarity similarity)
/*    */   {
/* 78 */     this.similarity = similarity;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Similarity getSimilarity()
/*    */   {
/* 86 */     return this.similarity;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Searcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */