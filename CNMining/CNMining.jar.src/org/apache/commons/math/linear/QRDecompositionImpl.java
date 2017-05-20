/*     */ package org.apache.commons.math.linear;
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
/*     */ public class QRDecompositionImpl
/*     */   implements QRDecomposition
/*     */ {
/*     */   private double[][] qr;
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
/*     */   private double[] rDiag;
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
/*     */   private int m;
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
/*     */   private int n;
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
/*     */   public QRDecompositionImpl(RealMatrix matrix)
/*     */   {
/*  66 */     this.m = matrix.getRowDimension();
/*  67 */     this.n = matrix.getColumnDimension();
/*  68 */     this.qr = matrix.getData();
/*  69 */     this.rDiag = new double[this.n];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */     for (int minor = 0; minor < Math.min(this.m, this.n); minor++)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */       double xNormSqr = 0.0D;
/*  85 */       for (int row = minor; row < this.m; row++) {
/*  86 */         xNormSqr += this.qr[row][minor] * this.qr[row][minor];
/*     */       }
/*  88 */       double a = Math.sqrt(xNormSqr);
/*  89 */       if (this.qr[minor][minor] > 0.0D) a = -a;
/*  90 */       this.rDiag[minor] = a;
/*     */       
/*  92 */       if (a != 0.0D)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */         this.qr[minor][minor] -= a;
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
/* 116 */         for (int col = minor + 1; col < this.n; col++) {
/* 117 */           double alpha = 0.0D;
/* 118 */           for (int row = minor; row < this.m; row++) {
/* 119 */             alpha -= this.qr[row][col] * this.qr[row][minor];
/*     */           }
/* 121 */           alpha /= a * this.qr[minor][minor];
/*     */           
/*     */ 
/* 124 */           for (int row = minor; row < this.m; row++) {
/* 125 */             this.qr[row][col] -= alpha * this.qr[row][minor];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RealMatrix getR()
/*     */   {
/* 140 */     RealMatrixImpl ret = new RealMatrixImpl(this.m, this.n);
/* 141 */     double[][] r = ret.getDataRef();
/*     */     
/*     */ 
/* 144 */     for (int row = Math.min(this.m, this.n) - 1; row >= 0; row--) {
/* 145 */       r[row][row] = this.rDiag[row];
/* 146 */       for (int col = row + 1; col < this.n; col++) {
/* 147 */         r[row][col] = this.qr[row][col];
/*     */       }
/*     */     }
/* 150 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RealMatrix getQ()
/*     */   {
/* 161 */     RealMatrixImpl ret = new RealMatrixImpl(this.m, this.m);
/* 162 */     double[][] Q = ret.getDataRef();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 169 */     for (int minor = this.m - 1; minor >= Math.min(this.m, this.n); minor--) {
/* 170 */       Q[minor][minor] = 1.0D;
/*     */     }
/*     */     
/* 173 */     for (int minor = Math.min(this.m, this.n) - 1; minor >= 0; minor--) {
/* 174 */       Q[minor][minor] = 1.0D;
/* 175 */       if (this.qr[minor][minor] != 0.0D) {
/* 176 */         for (int col = minor; col < this.m; col++) {
/* 177 */           double alpha = 0.0D;
/* 178 */           for (int row = minor; row < this.m; row++) {
/* 179 */             alpha -= Q[row][col] * this.qr[row][minor];
/*     */           }
/* 181 */           alpha /= this.rDiag[minor] * this.qr[minor][minor];
/*     */           
/* 183 */           for (int row = minor; row < this.m; row++) {
/* 184 */             Q[row][col] -= alpha * this.qr[row][minor];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 190 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/QRDecompositionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */