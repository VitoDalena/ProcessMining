/*    */ package org.apache.commons.math.optimization;
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
/*    */ public class PointCostPair
/*    */ {
/*    */   private final double[] point;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private final double cost;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PointCostPair(double[] point, double cost)
/*    */   {
/* 35 */     this.point = ((double[])point.clone());
/* 36 */     this.cost = cost;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double[] getPoint()
/*    */   {
/* 43 */     return (double[])this.point.clone();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double getCost()
/*    */   {
/* 50 */     return this.cost;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/PointCostPair.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */