/*    */ package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public final class SubCost01
/*    */   extends AbstractSubstitutionCost
/*    */   implements Serializable
/*    */ {
/*    */   public final String getShortDescriptionString()
/*    */   {
/* 60 */     return "SubCost01";
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
/*    */   public final float getCost(String str1, int string1Index, String str2, int string2Index)
/*    */   {
/* 73 */     if (str1.charAt(string1Index) == str2.charAt(string2Index)) {
/* 74 */       return 0.0F;
/*    */     }
/* 76 */     return 1.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMaxCost()
/*    */   {
/* 86 */     return 1.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMinCost()
/*    */   {
/* 95 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/SubCost01.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */