/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import uk.ac.shef.wit.simmetrics.math.MathFuncs;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.AbstractAffineGapCost;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.AbstractSubstitutionCost;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.AffineGap5_1;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.SubCost5_3_Minus3;
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
/*     */ 
/*     */ public class SmithWatermanGotohWindowedAffine
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  65 */   private final float ESTIMATEDTIMINGCONST = 4.5E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int windowSize;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private AbstractSubstitutionCost dCostFunc;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private AbstractAffineGapCost gGapFunc;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine()
/*     */   {
/*  87 */     this.gGapFunc = new AffineGap5_1();
/*     */     
/*  89 */     this.dCostFunc = new SubCost5_3_Minus3();
/*     */     
/*  91 */     this.windowSize = 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractAffineGapCost gapCostFunc)
/*     */   {
/* 101 */     this.gGapFunc = gapCostFunc;
/*     */     
/* 103 */     this.dCostFunc = new SubCost5_3_Minus3();
/*     */     
/* 105 */     this.windowSize = 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractAffineGapCost gapCostFunc, AbstractSubstitutionCost costFunc)
/*     */   {
/* 116 */     this.gGapFunc = gapCostFunc;
/*     */     
/* 118 */     this.dCostFunc = costFunc;
/*     */     
/* 120 */     this.windowSize = 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractSubstitutionCost costFunc)
/*     */   {
/* 130 */     this.gGapFunc = new AffineGap5_1();
/*     */     
/* 132 */     this.dCostFunc = costFunc;
/*     */     
/* 134 */     this.windowSize = 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(int affineGapWindowSize)
/*     */   {
/* 144 */     this.gGapFunc = new AffineGap5_1();
/*     */     
/* 146 */     this.dCostFunc = new SubCost5_3_Minus3();
/*     */     
/* 148 */     this.windowSize = affineGapWindowSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractAffineGapCost gapCostFunc, int affineGapWindowSize)
/*     */   {
/* 159 */     this.gGapFunc = gapCostFunc;
/*     */     
/* 161 */     this.dCostFunc = new SubCost5_3_Minus3();
/*     */     
/* 163 */     this.windowSize = affineGapWindowSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractAffineGapCost gapCostFunc, AbstractSubstitutionCost costFunc, int affineGapWindowSize)
/*     */   {
/* 175 */     this.gGapFunc = gapCostFunc;
/*     */     
/* 177 */     this.dCostFunc = costFunc;
/*     */     
/* 179 */     this.windowSize = affineGapWindowSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotohWindowedAffine(AbstractSubstitutionCost costFunc, int affineGapWindowSize)
/*     */   {
/* 190 */     this.gGapFunc = new AffineGap5_1();
/*     */     
/* 192 */     this.dCostFunc = costFunc;
/*     */     
/* 194 */     this.windowSize = affineGapWindowSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final AbstractAffineGapCost getgGapFunc()
/*     */   {
/* 203 */     return this.gGapFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setgGapFunc(AbstractAffineGapCost gGapFunc)
/*     */   {
/* 212 */     this.gGapFunc = gGapFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final AbstractSubstitutionCost getdCostFunc()
/*     */   {
/* 221 */     return this.dCostFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setdCostFunc(AbstractSubstitutionCost dCostFunc)
/*     */   {
/* 230 */     this.dCostFunc = dCostFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 239 */     return "SmithWatermanGotohWindowedAffine";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 248 */     return "Implements the Smith-Waterman-Gotoh algorithm with a windowed affine gap providing a similarity measure between two string";
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
/* 261 */     return null;
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
/* 275 */     float str1Length = string1.length();
/* 276 */     float str2Length = string2.length();
/* 277 */     return (str1Length * str2Length * this.windowSize + str1Length * str2Length * this.windowSize) * 4.5E-5F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float getSimilarity(String string1, String string2)
/*     */   {
/* 289 */     float smithWatermanGotoh = getUnNormalisedSimilarity(string1, string2);
/*     */     
/*     */ 
/* 292 */     float maxValue = Math.min(string1.length(), string2.length());
/* 293 */     if (this.dCostFunc.getMaxCost() > -this.gGapFunc.getMaxCost()) {
/* 294 */       maxValue *= this.dCostFunc.getMaxCost();
/*     */     } else {
/* 296 */       maxValue *= -this.gGapFunc.getMaxCost();
/*     */     }
/*     */     
/*     */ 
/* 300 */     if (maxValue == 0.0F) {
/* 301 */       return 1.0F;
/*     */     }
/*     */     
/* 304 */     return smithWatermanGotoh / maxValue;
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
/*     */   public float getUnNormalisedSimilarity(String s, String t)
/*     */   {
/* 326 */     int n = s.length();
/* 327 */     int m = t.length();
/* 328 */     if (n == 0) {
/* 329 */       return m;
/*     */     }
/* 331 */     if (m == 0) {
/* 332 */       return n;
/*     */     }
/*     */     
/*     */ 
/* 336 */     float[][] d = new float[n][m];
/*     */     
/*     */ 
/* 339 */     float maxSoFar = 0.0F;
/* 340 */     for (int i = 0; i < n; i++)
/*     */     {
/* 342 */       float cost = this.dCostFunc.getCost(s, i, t, 0);
/*     */       
/* 344 */       if (i == 0) {
/* 345 */         d[0][0] = Math.max(0.0F, cost);
/*     */       }
/*     */       else {
/* 348 */         float maxGapCost = 0.0F;
/* 349 */         int windowStart = i - this.windowSize;
/* 350 */         if (windowStart < 1) {
/* 351 */           windowStart = 1;
/*     */         }
/* 353 */         for (int k = windowStart; k < i; k++) {
/* 354 */           maxGapCost = Math.max(maxGapCost, d[(i - k)][0] - this.gGapFunc.getCost(s, i - k, i));
/*     */         }
/* 356 */         d[i][0] = MathFuncs.max3(0.0F, maxGapCost, cost);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 361 */       if (d[i][0] > maxSoFar) {
/* 362 */         maxSoFar = d[i][0];
/*     */       }
/*     */     }
/* 365 */     for (int j = 0; j < m; j++)
/*     */     {
/* 367 */       float cost = this.dCostFunc.getCost(s, 0, t, j);
/*     */       
/* 369 */       if (j == 0) {
/* 370 */         d[0][0] = Math.max(0.0F, cost);
/*     */       }
/*     */       else {
/* 373 */         float maxGapCost = 0.0F;
/* 374 */         int windowStart = j - this.windowSize;
/* 375 */         if (windowStart < 1) {
/* 376 */           windowStart = 1;
/*     */         }
/* 378 */         for (int k = windowStart; k < j; k++) {
/* 379 */           maxGapCost = Math.max(maxGapCost, d[0][(j - k)] - this.gGapFunc.getCost(t, j - k, j));
/*     */         }
/* 381 */         d[0][j] = MathFuncs.max3(0.0F, maxGapCost, cost);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 386 */       if (d[0][j] > maxSoFar) {
/* 387 */         maxSoFar = d[0][j];
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 392 */     for (i = 1; i < n; i++) {
/* 393 */       for (j = 1; j < m; j++)
/*     */       {
/* 395 */         float cost = this.dCostFunc.getCost(s, i, t, j);
/*     */         
/*     */ 
/* 398 */         float maxGapCost1 = 0.0F;
/* 399 */         float maxGapCost2 = 0.0F;
/* 400 */         int windowStart = i - this.windowSize;
/* 401 */         if (windowStart < 1) {
/* 402 */           windowStart = 1;
/*     */         }
/* 404 */         for (int k = windowStart; k < i; k++) {
/* 405 */           maxGapCost1 = Math.max(maxGapCost1, d[(i - k)][j] - this.gGapFunc.getCost(s, i - k, i));
/*     */         }
/* 407 */         windowStart = j - this.windowSize;
/* 408 */         if (windowStart < 1) {
/* 409 */           windowStart = 1;
/*     */         }
/* 411 */         for (int k = windowStart; k < j; k++) {
/* 412 */           maxGapCost2 = Math.max(maxGapCost2, d[i][(j - k)] - this.gGapFunc.getCost(t, j - k, j));
/*     */         }
/* 414 */         d[i][j] = MathFuncs.max4(0.0F, maxGapCost1, maxGapCost2, d[(i - 1)][(j - 1)] + cost);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 419 */         if (d[i][j] > maxSoFar) {
/* 420 */           maxSoFar = d[i][j];
/*     */         }
/*     */       }
/*     */     }
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
/* 442 */     return maxSoFar;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/SmithWatermanGotohWindowedAffine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */