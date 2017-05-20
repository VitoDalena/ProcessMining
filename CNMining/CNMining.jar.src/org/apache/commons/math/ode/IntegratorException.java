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
/*    */ public class IntegratorException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -1215318282266670558L;
/*    */   
/*    */   public IntegratorException(String specifier, Object[] parts)
/*    */   {
/* 37 */     super(specifier, parts);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public IntegratorException(Throwable cause)
/*    */   {
/* 45 */     super(cause);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/IntegratorException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */