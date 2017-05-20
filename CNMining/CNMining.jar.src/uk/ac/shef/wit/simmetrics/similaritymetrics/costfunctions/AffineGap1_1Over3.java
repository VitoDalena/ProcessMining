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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AffineGap1_1Over3
/*    */   extends AbstractAffineGapCost
/*    */   implements Serializable
/*    */ {
/*    */   public final String getShortDescriptionString()
/*    */   {
/* 63 */     return "SubCost01";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getCost(String stringToGap, int stringIndexStartGap, int stringIndexEndGap)
/*    */   {
/* 75 */     if (stringIndexStartGap >= stringIndexEndGap) {
/* 76 */       return 0.0F;
/*    */     }
/* 78 */     return 1.0F + (stringIndexEndGap - 1 - stringIndexStartGap) * 0.33333334F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMaxCost()
/*    */   {
/* 88 */     return 1.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMinCost()
/*    */   {
/* 97 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AffineGap1_1Over3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */