/*    */ package org.apache.commons.math.geometry;
/*    */ 
/*    */ import org.apache.commons.math.MathException;
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
/*    */ public class CardanEulerSingularityException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -1360952845582206770L;
/*    */   
/*    */   public CardanEulerSingularityException(boolean isCardan)
/*    */   {
/* 38 */     super(isCardan ? "Cardan angles singularity" : "Euler angles singularity", new Object[0]);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/geometry/CardanEulerSingularityException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */