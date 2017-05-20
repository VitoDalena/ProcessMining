/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractStringMetric
/*     */   implements InterfaceStringMetric
/*     */ {
/*     */   public abstract String getShortDescriptionString();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String getLongDescriptionString();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String getSimilarityExplained(String paramString1, String paramString2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final long getSimilarityTimingActual(String string1, String string2)
/*     */   {
/*  87 */     long timeBefore = System.currentTimeMillis();
/*     */     
/*  89 */     getSimilarity(string1, string2);
/*     */     
/*  91 */     long timeAfter = System.currentTimeMillis();
/*     */     
/*  93 */     return timeAfter - timeBefore;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float[] batchCompareSet(String[] set, String comparator)
/*     */   {
/* 108 */     float[] results = new float[set.length];
/* 109 */     for (int strNum = 0; strNum < set.length; strNum++)
/*     */     {
/* 111 */       results[strNum] = getSimilarity(set[strNum], comparator);
/*     */     }
/* 113 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float[] batchCompareSets(String[] firstSet, String[] secondSet)
/*     */   {
/*     */     float[] results;
/*     */     
/*     */ 
/*     */ 
/*     */     float[] results;
/*     */     
/*     */ 
/*     */ 
/* 130 */     if (firstSet.length <= secondSet.length) {
/* 131 */       results = new float[firstSet.length];
/*     */     } else {
/* 133 */       results = new float[secondSet.length];
/*     */     }
/* 135 */     for (int strNum = 0; strNum < results.length; strNum++)
/*     */     {
/* 137 */       results[strNum] = getSimilarity(firstSet[strNum], secondSet[strNum]);
/*     */     }
/* 139 */     return results;
/*     */   }
/*     */   
/*     */   public abstract float getSimilarityTimingEstimated(String paramString1, String paramString2);
/*     */   
/*     */   public abstract float getSimilarity(String paramString1, String paramString2);
/*     */   
/*     */   public abstract float getUnNormalisedSimilarity(String paramString1, String paramString2);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/AbstractStringMetric.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */