/*    */ package org.apache.commons.math;
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
/*    */ public class DuplicateSampleAbscissaException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -2271007547170169872L;
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
/*    */   public DuplicateSampleAbscissaException(double abscissa, int i1, int i2)
/*    */   {
/* 37 */     super("Abscissa {0} is duplicated at both indices {1} and {2}", new Object[] { new Double(abscissa), new Integer(i1), new Integer(i2) });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double getDuplicateAbscissa()
/*    */   {
/* 46 */     return ((Double)getArguments()[0]).doubleValue();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/DuplicateSampleAbscissaException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */