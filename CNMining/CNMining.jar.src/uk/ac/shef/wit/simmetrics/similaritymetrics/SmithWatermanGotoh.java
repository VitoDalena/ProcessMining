/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public final class SmithWatermanGotoh
/*     */   extends SmithWatermanGotohWindowedAffine
/*     */   implements Serializable
/*     */ {
/*  64 */   private final float ESTIMATEDTIMINGCONST = 2.2E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotoh()
/*     */   {
/*  71 */     super(new AffineGap5_1(), new SubCost5_3_Minus3(), Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotoh(AbstractAffineGapCost gapCostFunc)
/*     */   {
/*  81 */     super(gapCostFunc, new SubCost5_3_Minus3(), Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotoh(AbstractAffineGapCost gapCostFunc, AbstractSubstitutionCost costFunc)
/*     */   {
/*  92 */     super(gapCostFunc, costFunc, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SmithWatermanGotoh(AbstractSubstitutionCost costFunc)
/*     */   {
/* 102 */     super(new AffineGap5_1(), costFunc, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 111 */     return "SmithWatermanGotoh";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 120 */     return "Implements the Smith-Waterman-Gotoh algorithm providing a similarity measure between two string";
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
/* 133 */     return null;
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
/* 147 */     float str1Length = string1.length();
/* 148 */     float str2Length = string2.length();
/* 149 */     return (str1Length * str2Length * str1Length + str1Length * str2Length * str2Length) * 2.2E-5F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/SmithWatermanGotoh.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */