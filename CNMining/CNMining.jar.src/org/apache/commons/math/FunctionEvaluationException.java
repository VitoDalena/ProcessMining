/*     */ package org.apache.commons.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionEvaluationException
/*     */   extends MathException
/*     */ {
/*     */   private static final long serialVersionUID = -7619974756160279127L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  33 */   private double argument = NaN.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FunctionEvaluationException(double argument)
/*     */   {
/*  42 */     super("Evaluation failed for argument = {0}", new Object[] { new Double(argument) });
/*     */     
/*  44 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public FunctionEvaluationException(double argument, String message)
/*     */   {
/*  56 */     super(message);
/*  57 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FunctionEvaluationException(double argument, String pattern, Object[] arguments)
/*     */   {
/*  70 */     super(pattern, arguments);
/*  71 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public FunctionEvaluationException(double argument, String message, Throwable cause)
/*     */   {
/*  84 */     super(message, cause);
/*  85 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FunctionEvaluationException(double argument, Throwable cause)
/*     */   {
/*  96 */     super(cause);
/*  97 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FunctionEvaluationException(double argument, String pattern, Object[] arguments, Throwable cause)
/*     */   {
/* 112 */     super(pattern, arguments, cause);
/* 113 */     this.argument = argument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getArgument()
/*     */   {
/* 122 */     return this.argument;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/FunctionEvaluationException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */