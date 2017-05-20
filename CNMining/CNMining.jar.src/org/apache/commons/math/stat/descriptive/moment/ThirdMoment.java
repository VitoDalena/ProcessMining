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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThirdMoment
/*    */   extends SecondMoment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7818711964045118679L;
/*    */   protected double m3;
/*    */   protected double nDevSq;
/*    */   
/*    */   public ThirdMoment()
/*    */   {
/* 66 */     this.m3 = NaN.0D;
/* 67 */     this.nDevSq = NaN.0D;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void increment(double d)
/*    */   {
/* 74 */     if (this.n < 1L) {
/* 75 */       this.m3 = (this.m2 = this.m1 = 0.0D);
/*    */     }
/*    */     
/* 78 */     double prevM2 = this.m2;
/* 79 */     super.increment(d);
/* 80 */     this.nDevSq = (this.nDev * this.nDev);
/* 81 */     double n0 = this.n;
/* 82 */     this.m3 = (this.m3 - 3.0D * this.nDev * prevM2 + (n0 - 1.0D) * (n0 - 2.0D) * this.nDevSq * this.dev);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double getResult()
/*    */   {
/* 89 */     return this.m3;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 96 */     super.clear();
/* 97 */     this.m3 = NaN.0D;
/* 98 */     this.nDevSq = NaN.0D;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/ThirdMoment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */