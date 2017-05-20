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
/*    */ public abstract class Scorer
/*    */ {
/*    */   private Similarity similarity;
/*    */   
/*    */   protected Scorer(Similarity similarity)
/*    */   {
/* 27 */     this.similarity = similarity;
/*    */   }
/*    */   
/*    */   public Similarity getSimilarity()
/*    */   {
/* 32 */     return this.similarity;
/*    */   }
/*    */   
/*    */   public void score(HitCollector hc) throws IOException
/*    */   {
/* 37 */     while (next()) {
/* 38 */       hc.collect(doc(), score());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract boolean next()
/*    */     throws IOException;
/*    */   
/*    */   public abstract int doc();
/*    */   
/*    */   public abstract float score()
/*    */     throws IOException;
/*    */   
/*    */   public abstract boolean skipTo(int paramInt)
/*    */     throws IOException;
/*    */   
/*    */   public abstract Explanation explain(int paramInt)
/*    */     throws IOException;
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Scorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */