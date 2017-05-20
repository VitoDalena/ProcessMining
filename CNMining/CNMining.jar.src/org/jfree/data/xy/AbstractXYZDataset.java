/*    */ package org.jfree.data.xy;
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
/*    */ public abstract class AbstractXYZDataset
/*    */   extends AbstractXYDataset
/*    */   implements XYZDataset
/*    */ {
/*    */   public double getZValue(int series, int item)
/*    */   {
/* 60 */     double result = NaN.0D;
/* 61 */     Number z = getZ(series, item);
/* 62 */     if (z != null) {
/* 63 */       result = z.doubleValue();
/*    */     }
/* 65 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/AbstractXYZDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */