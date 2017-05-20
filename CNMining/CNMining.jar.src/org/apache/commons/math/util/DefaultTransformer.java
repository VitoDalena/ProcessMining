/*    */ package org.apache.commons.math.util;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ public class DefaultTransformer
/*    */   implements NumberTransformer, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4019938025047800455L;
/*    */   
/*    */   public double transform(Object o)
/*    */     throws MathException
/*    */   {
/* 46 */     if (o == null) {
/* 47 */       throw new MathException("Conversion Exception in Transformation, Object is null", new Object[0]);
/*    */     }
/*    */     
/* 50 */     if ((o instanceof Number)) {
/* 51 */       return ((Number)o).doubleValue();
/*    */     }
/*    */     try
/*    */     {
/* 55 */       return new Double(o.toString()).doubleValue();
/*    */     } catch (Exception e) {
/* 57 */       throw new MathException("Conversion Exception in Transformation: {0}", new Object[] { e.getMessage() }, e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/DefaultTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */