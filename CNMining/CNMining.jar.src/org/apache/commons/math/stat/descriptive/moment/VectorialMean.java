/*    */ package org.apache.commons.math.stat.descriptive.moment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math.DimensionMismatchException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VectorialMean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8223009086481006892L;
/*    */   private Mean[] means;
/*    */   
/*    */   public VectorialMean(int dimension)
/*    */   {
/* 40 */     this.means = new Mean[dimension];
/* 41 */     for (int i = 0; i < dimension; i++) {
/* 42 */       this.means[i] = new Mean();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void increment(double[] v)
/*    */     throws DimensionMismatchException
/*    */   {
/* 52 */     if (v.length != this.means.length) {
/* 53 */       throw new DimensionMismatchException(v.length, this.means.length);
/*    */     }
/* 55 */     for (int i = 0; i < v.length; i++) {
/* 56 */       this.means[i].increment(v[i]);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public double[] getResult()
/*    */   {
/* 65 */     double[] result = new double[this.means.length];
/* 66 */     for (int i = 0; i < result.length; i++) {
/* 67 */       result[i] = this.means[i].getResult();
/*    */     }
/* 69 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public long getN()
/*    */   {
/* 77 */     return this.means.length == 0 ? 0L : this.means[0].getN();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/VectorialMean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */