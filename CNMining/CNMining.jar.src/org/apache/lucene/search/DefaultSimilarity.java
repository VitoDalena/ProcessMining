/*    */ package org.apache.lucene.search;
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
/*    */ public class DefaultSimilarity
/*    */   extends Similarity
/*    */ {
/*    */   public float lengthNorm(String fieldName, int numTerms)
/*    */   {
/* 23 */     return (float)(1.0D / Math.sqrt(numTerms));
/*    */   }
/*    */   
/*    */   public float queryNorm(float sumOfSquaredWeights)
/*    */   {
/* 28 */     return (float)(1.0D / Math.sqrt(sumOfSquaredWeights));
/*    */   }
/*    */   
/*    */   public float tf(float freq)
/*    */   {
/* 33 */     return (float)Math.sqrt(freq);
/*    */   }
/*    */   
/*    */   public float sloppyFreq(int distance)
/*    */   {
/* 38 */     return 1.0F / (distance + 1);
/*    */   }
/*    */   
/*    */   public float idf(int docFreq, int numDocs)
/*    */   {
/* 43 */     return (float)(Math.log(numDocs / (docFreq + 1)) + 1.0D);
/*    */   }
/*    */   
/*    */   public float coord(int overlap, int maxOverlap)
/*    */   {
/* 48 */     return overlap / maxOverlap;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/DefaultSimilarity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */