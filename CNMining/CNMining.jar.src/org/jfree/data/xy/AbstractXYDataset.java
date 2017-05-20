/*    */ package org.jfree.data.xy;
/*    */ 
/*    */ import org.jfree.data.DomainOrder;
/*    */ import org.jfree.data.general.AbstractSeriesDataset;
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
/*    */ public abstract class AbstractXYDataset
/*    */   extends AbstractSeriesDataset
/*    */   implements XYDataset
/*    */ {
/*    */   public DomainOrder getDomainOrder()
/*    */   {
/* 62 */     return DomainOrder.NONE;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double getXValue(int series, int item)
/*    */   {
/* 74 */     double result = NaN.0D;
/* 75 */     Number x = getX(series, item);
/* 76 */     if (x != null) {
/* 77 */       result = x.doubleValue();
/*    */     }
/* 79 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double getYValue(int series, int item)
/*    */   {
/* 91 */     double result = NaN.0D;
/* 92 */     Number y = getY(series, item);
/* 93 */     if (y != null) {
/* 94 */       result = y.doubleValue();
/*    */     }
/* 96 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/AbstractXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */