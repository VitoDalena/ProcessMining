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
/*     */ 
/*     */ public final class NeedlemanWunch
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  63 */   private final float ESTIMATEDTIMINGCONST = 1.842E-4F;
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
/*     */   public NeedlemanWunch()
/*     */   {
/*  80 */     this.gapCost = 2.0F;
/*     */     
/*  82 */     this.dCostFunc = new SubCost01();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NeedlemanWunch(float costG)
/*     */   {
/*  92 */     this.gapCost = costG;
/*     */     
/*  94 */     this.dCostFunc = new SubCost01();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NeedlemanWunch(float costG, AbstractSubstitutionCost costFunc)
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
/*     */   public NeedlemanWunch(AbstractSubstitutionCost costFunc)
/*     */   {
/* 117 */     this.gapCost = 2.0F;
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
/* 164 */     return "NeedlemanWunch";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 173 */     return "Implements the Needleman-Wunch algorithm providing an edit distance based similarity measure between two strings";
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
/* 202 */     return str1Length * str2Length * 1.842E-4F;
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
/* 213 */     float needlemanWunch = getUnNormalisedSimilarity(string1, string2);
/*     */     
/*     */ 
/* 216 */     float maxValue = Math.max(string1.length(), string2.length());
/* 217 */     float minValue = maxValue;
/* 218 */     if (this.dCostFunc.getMaxCost() > this.gapCost) {
/* 219 */       maxValue *= this.dCostFunc.getMaxCost();
/*     */     } else {
/* 221 */       maxValue *= this.gapCost;
/*     */     }
/* 223 */     if (this.dCostFunc.getMinCost() < this.gapCost) {
/* 224 */       minValue *= this.dCostFunc.getMinCost();
/*     */     } else {
/* 226 */       minValue *= this.gapCost;
/*     */     }
/* 228 */     if (minValue < 0.0F) {
/* 229 */       maxValue -= minValue;
/* 230 */       needlemanWunch -= minValue;
/*     */     }
/*     */     
/*     */ 
/* 234 */     if (maxValue == 0.0F) {
/* 235 */       return 1.0F;
/*     */     }
/*     */     
/* 238 */     return 1.0F - needlemanWunch / maxValue;
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
/* 259 */     int n = s.length();
/* 260 */     int m = t.length();
/* 261 */     if (n == 0) {
/* 262 */       return m;
/*     */     }
/* 264 */     if (m == 0) {
/* 265 */       return n;
/*     */     }
/*     */     
/*     */ 
/* 269 */     float[][] d = new float[n + 1][m + 1];
/*     */     
/*     */ 
/* 272 */     for (int i = 0; i <= n; i++) {
/* 273 */       d[i][0] = i;
/*     */     }
/* 275 */     for (int j = 0; j <= m; j++) {
/* 276 */       d[0][j] = j;
/*     */     }
/*     */     
/*     */ 
/* 280 */     for (i = 1; i <= n; i++) {
/* 281 */       for (j = 1; j <= m; j++)
/*     */       {
/* 283 */         float cost = this.dCostFunc.getCost(s, i - 1, t, j - 1);
/*     */         
/*     */ 
/* 286 */         d[i][j] = MathFuncs.min3(d[(i - 1)][j] + this.gapCost, d[i][(j - 1)] + this.gapCost, d[(i - 1)][(j - 1)] + cost);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 291 */     return d[n][m];
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/NeedlemanWunch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */