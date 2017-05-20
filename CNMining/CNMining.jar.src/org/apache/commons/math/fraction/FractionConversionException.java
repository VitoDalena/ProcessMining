/*    */ package org.apache.commons.math.fraction;
/*    */ 
/*    */ import org.apache.commons.math.ConvergenceException;
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
/*    */ public class FractionConversionException
/*    */   extends ConvergenceException
/*    */ {
/*    */   private static final long serialVersionUID = -4661812640132576263L;
/*    */   
/*    */   public FractionConversionException(double value, int maxIterations)
/*    */   {
/* 41 */     super("Unable to convert {0} to fraction after {1} iterations", new Object[] { new Double(value), new Integer(maxIterations) });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public FractionConversionException(double value, long p, long q)
/*    */   {
/* 53 */     super("Overflow trying to convert {0} to fraction ({1}/{2})", new Object[] { new Double(value), new Long(p), new Long(q) });
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/fraction/FractionConversionException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */