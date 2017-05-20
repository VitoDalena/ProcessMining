/*    */ package org.apache.commons.math.geometry;
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
/*    */ public class NotARotationMatrixException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = 5647178478658937642L;
/*    */   
/*    */   public NotARotationMatrixException(String specifier, Object[] parts)
/*    */   {
/* 40 */     super(specifier, parts);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/geometry/NotARotationMatrixException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */