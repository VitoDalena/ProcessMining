/*    */ package org.apache.commons.math;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DimensionMismatchException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -1316089546353786411L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int dimension1;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int dimension2;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DimensionMismatchException(int dimension1, int dimension2)
/*    */   {
/* 36 */     super("dimension mismatch {0} != {1}", new Object[] { new Integer(dimension1), new Integer(dimension2) });
/*    */     
/*    */ 
/*    */ 
/* 40 */     this.dimension1 = dimension1;
/* 41 */     this.dimension2 = dimension2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getDimension1()
/*    */   {
/* 49 */     return this.dimension1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getDimension2()
/*    */   {
/* 57 */     return this.dimension2;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/DimensionMismatchException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */