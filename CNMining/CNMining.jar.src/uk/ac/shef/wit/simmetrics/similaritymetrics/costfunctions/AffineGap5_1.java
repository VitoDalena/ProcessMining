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
/*    */ public final class AffineGap5_1
/*    */   extends AbstractAffineGapCost
/*    */   implements Serializable
/*    */ {
/*    */   public final String getShortDescriptionString()
/*    */   {
/* 60 */     return "AffineGap5_1";
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
/* 72 */     if (stringIndexStartGap >= stringIndexEndGap) {
/* 73 */       return 0.0F;
/*    */     }
/* 75 */     return 5.0F + (stringIndexEndGap - 1 - stringIndexStartGap);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMaxCost()
/*    */   {
/* 85 */     return 5.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float getMinCost()
/*    */   {
/* 94 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AffineGap5_1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */