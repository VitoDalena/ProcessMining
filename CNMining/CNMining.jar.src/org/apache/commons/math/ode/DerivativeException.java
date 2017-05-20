/*    */ package org.apache.commons.math.ode;
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
/*    */ public class DerivativeException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -4100440615830558122L;
/*    */   
/*    */   public DerivativeException(String specifier, String[] parts)
/*    */   {
/* 38 */     super(specifier, parts);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public DerivativeException(Throwable cause)
/*    */   {
/* 45 */     super(cause);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/DerivativeException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */