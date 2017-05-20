/*    */ package org.apache.commons.math.stat.descriptive.moment;
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
/*    */ public class SecondMoment
/*    */   extends FirstMoment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3942403127395076445L;
/*    */   protected double m2;
/*    */   
/*    */   public SecondMoment()
/*    */   {
/* 58 */     this.m2 = NaN.0D;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void increment(double d)
/*    */   {
/* 65 */     if (this.n < 1L) {
/* 66 */       this.m1 = (this.m2 = 0.0D);
/*    */     }
/* 68 */     super.increment(d);
/* 69 */     this.m2 += (this.n - 1.0D) * this.dev * this.nDev;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 76 */     super.clear();
/* 77 */     this.m2 = NaN.0D;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double getResult()
/*    */   {
/* 84 */     return this.m2;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/SecondMoment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */