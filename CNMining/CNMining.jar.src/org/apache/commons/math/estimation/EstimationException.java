/*    */ package org.apache.commons.math.estimation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EstimationException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = -573038581493881337L;
/*    */   
/*    */   public EstimationException(String specifier, Object[] parts)
/*    */   {
/* 43 */     super(specifier, parts);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/EstimationException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */