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
/*    */ 
/*    */ public class ArgumentOutsideDomainException
/*    */   extends FunctionEvaluationException
/*    */ {
/*    */   private static final long serialVersionUID = -4965972841162580234L;
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
/*    */   public ArgumentOutsideDomainException(double argument, double lower, double upper)
/*    */   {
/* 39 */     super(argument, "Argument {0} outside domain [{1} ; {2}]", new Object[] { new Double(argument), new Double(lower), new Double(upper) });
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ArgumentOutsideDomainException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */