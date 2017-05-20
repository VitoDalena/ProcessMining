/*    */ package org.apache.commons.math;
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
/*    */ public class MathConfigurationException
/*    */   extends MathException
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4056541384141349722L;
/*    */   
/*    */   public MathConfigurationException() {}
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public MathConfigurationException(String message)
/*    */   {
/* 42 */     super(message);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public MathConfigurationException(String pattern, Object[] arguments)
/*    */   {
/* 53 */     super(pattern, arguments);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public MathConfigurationException(String message, Throwable cause)
/*    */   {
/* 63 */     super(message, cause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public MathConfigurationException(Throwable cause)
/*    */   {
/* 71 */     super(cause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public MathConfigurationException(String pattern, Object[] arguments, Throwable cause)
/*    */   {
/* 83 */     super(pattern, arguments, cause);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/MathConfigurationException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */