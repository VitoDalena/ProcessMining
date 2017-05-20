/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import uk.ac.shef.wit.simmetrics.math.MathFuncs;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.AbstractSubstitutionCost;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.SubCost01;
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
/*     */ public final class Levenshtein
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  62 */   private final float ESTIMATEDTIMINGCONST = 1.8E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  67 */   private final AbstractSubstitutionCost dCostFunc = new SubCost01();
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
/*     */   public String getShortDescriptionString()
/*     */   {
/*  81 */     return "Levenshtein";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/*  90 */     return "Implements the basic Levenshtein algorithm providing a similarity measure between two strings";
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
/*     */   public String getSimilarityExplained(String string1, String string2)
/*     */   {
/* 103 */     return null;
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
/*     */   public float getSimilarityTimingEstimated(String string1, String string2)
/*     */   {
/* 117 */     float str1Length = string1.length();
/* 118 */     float str2Length = string2.length();
/* 119 */     return str1Length * str2Length * 1.8E-4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSimilarity(String string1, String string2)
/*     */   {
/* 130 */     float levensteinDistance = getUnNormalisedSimilarity(string1, string2);
/*     */     
/*     */ 
/*     */ 
/* 134 */     float maxLen = string1.length();
/* 135 */     if (maxLen < string2.length()) {
/* 136 */       maxLen = string2.length();
/*     */     }
/*     */     
/*     */ 
/* 140 */     if (maxLen == 0.0F) {
/* 141 */       return 1.0F;
/*     */     }
/*     */     
/* 144 */     return 1.0F - levensteinDistance / maxLen;
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
/*     */   public float getUnNormalisedSimilarity(String s, String t)
/*     */   {
/* 176 */     int n = s.length();
/* 177 */     int m = t.length();
/* 178 */     if (n == 0) {
/* 179 */       return m;
/*     */     }
/* 181 */     if (m == 0) {
/* 182 */       return n;
/*     */     }
/* 184 */     float[][] d = new float[n + 1][m + 1];
/*     */     
/*     */ 
/* 187 */     for (int i = 0; i <= n; i++) {
/* 188 */       d[i][0] = i;
/*     */     }
/* 190 */     for (int j = 0; j <= m; j++) {
/* 191 */       d[0][j] = j;
/*     */     }
/*     */     
/*     */ 
/* 195 */     for (i = 1; i <= n; i++)
/*     */     {
/* 197 */       for (j = 1; j <= m; j++)
/*     */       {
/* 199 */         float cost = this.dCostFunc.getCost(s, i - 1, t, j - 1);
/*     */         
/*     */ 
/* 202 */         d[i][j] = MathFuncs.min3(d[(i - 1)][j] + 1.0F, d[i][(j - 1)] + 1.0F, d[(i - 1)][(j - 1)] + cost);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 207 */     return d[n][m];
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/Levenshtein.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */