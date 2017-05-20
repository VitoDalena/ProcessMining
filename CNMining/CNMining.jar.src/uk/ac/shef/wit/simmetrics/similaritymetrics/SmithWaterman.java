/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import uk.ac.shef.wit.simmetrics.math.MathFuncs;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.AbstractSubstitutionCost;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.SubCost1_Minus2;
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
/*     */ public final class SmithWaterman
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  63 */   private final float ESTIMATEDTIMINGCONST = 1.61E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private AbstractSubstitutionCost dCostFunc;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private float gapCost;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWaterman()
/*     */   {
/*  80 */     this.gapCost = 0.5F;
/*     */     
/*  82 */     this.dCostFunc = new SubCost1_Minus2();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWaterman(float costG)
/*     */   {
/*  92 */     this.gapCost = costG;
/*     */     
/*  94 */     this.dCostFunc = new SubCost1_Minus2();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWaterman(float costG, AbstractSubstitutionCost costFunc)
/*     */   {
/* 105 */     this.gapCost = costG;
/*     */     
/* 107 */     this.dCostFunc = costFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWaterman(AbstractSubstitutionCost costFunc)
/*     */   {
/* 117 */     this.gapCost = 0.5F;
/*     */     
/* 119 */     this.dCostFunc = costFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getGapCost()
/*     */   {
/* 128 */     return this.gapCost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGapCost(float gapCost)
/*     */   {
/* 137 */     this.gapCost = gapCost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractSubstitutionCost getdCostFunc()
/*     */   {
/* 146 */     return this.dCostFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setdCostFunc(AbstractSubstitutionCost dCostFunc)
/*     */   {
/* 155 */     this.dCostFunc = dCostFunc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 164 */     return "SmithWaterman";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 173 */     return "Implements the Smith-Waterman algorithm providing a similarity measure between two string";
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
/* 186 */     return null;
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
/* 200 */     float str1Length = string1.length();
/* 201 */     float str2Length = string2.length();
/* 202 */     return (str1Length * str2Length + str1Length + str2Length) * 1.61E-4F;
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
/* 213 */     float smithWaterman = getUnNormalisedSimilarity(string1, string2);
/*     */     
/*     */ 
/* 216 */     float maxValue = Math.min(string1.length(), string2.length());
/* 217 */     if (this.dCostFunc.getMaxCost() > -this.gapCost) {
/* 218 */       maxValue *= this.dCostFunc.getMaxCost();
/*     */     } else {
/* 220 */       maxValue *= -this.gapCost;
/*     */     }
/*     */     
/*     */ 
/* 224 */     if (maxValue == 0.0F) {
/* 225 */       return 1.0F;
/*     */     }
/*     */     
/* 228 */     return smithWaterman / maxValue;
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
/*     */   public float getUnNormalisedSimilarity(String s, String t)
/*     */   {
/* 249 */     int n = s.length();
/* 250 */     int m = t.length();
/* 251 */     if (n == 0) {
/* 252 */       return m;
/*     */     }
/* 254 */     if (m == 0) {
/* 255 */       return n;
/*     */     }
/*     */     
/*     */ 
/* 259 */     float[][] d = new float[n][m];
/*     */     
/*     */ 
/* 262 */     float maxSoFar = 0.0F;
/* 263 */     for (int i = 0; i < n; i++)
/*     */     {
/* 265 */       float cost = this.dCostFunc.getCost(s, i, t, 0);
/*     */       
/* 267 */       if (i == 0) {
/* 268 */         d[0][0] = MathFuncs.max3(0.0F, -this.gapCost, cost);
/*     */       }
/*     */       else
/*     */       {
/* 272 */         d[i][0] = MathFuncs.max3(0.0F, d[(i - 1)][0] - this.gapCost, cost);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 277 */       if (d[i][0] > maxSoFar) {
/* 278 */         maxSoFar = d[i][0];
/*     */       }
/*     */     }
/* 281 */     for (int j = 0; j < m; j++)
/*     */     {
/* 283 */       float cost = this.dCostFunc.getCost(s, 0, t, j);
/*     */       
/* 285 */       if (j == 0) {
/* 286 */         d[0][0] = MathFuncs.max3(0.0F, -this.gapCost, cost);
/*     */       }
/*     */       else
/*     */       {
/* 290 */         d[0][j] = MathFuncs.max3(0.0F, d[0][(j - 1)] - this.gapCost, cost);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 295 */       if (d[0][j] > maxSoFar) {
/* 296 */         maxSoFar = d[0][j];
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 301 */     for (i = 1; i < n; i++) {
/* 302 */       for (j = 1; j < m; j++)
/*     */       {
/* 304 */         float cost = this.dCostFunc.getCost(s, i, t, j);
/*     */         
/*     */ 
/* 307 */         d[i][j] = MathFuncs.max4(0.0F, d[(i - 1)][j] - this.gapCost, d[i][(j - 1)] - this.gapCost, d[(i - 1)][(j - 1)] + cost);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 312 */         if (d[i][j] > maxSoFar) {
/* 313 */           maxSoFar = d[i][j];
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 319 */     return maxSoFar;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/SmithWaterman.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */