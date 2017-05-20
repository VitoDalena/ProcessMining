/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SubCost1_Minus2
/*     */   extends AbstractSubstitutionCost
/*     */   implements Serializable
/*     */ {
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  61 */     return "SubCost1_Minus2";
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
/*     */   public final float getCost(String str1, int string1Index, String str2, int string2Index)
/*     */   {
/*  75 */     if ((str1.length() <= string1Index) || (string1Index < 0)) {
/*  76 */       return 0.0F;
/*     */     }
/*  78 */     if ((str2.length() <= string2Index) || (string2Index < 0)) {
/*  79 */       return 0.0F;
/*     */     }
/*     */     
/*  82 */     if (str1.charAt(string1Index) == str2.charAt(string2Index)) {
/*  83 */       return 1.0F;
/*     */     }
/*  85 */     return -2.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float getMaxCost()
/*     */   {
/*  95 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float getMinCost()
/*     */   {
/* 104 */     return -2.0F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/SubCost1_Minus2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */