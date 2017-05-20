/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ import java.io.Serializable;
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
/*     */ public class CholeskyDecomposition
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   private DoubleMatrix2D L;
/*     */   private int n;
/*     */   private boolean isSymmetricPositiveDefinite;
/*     */   
/*     */   public CholeskyDecomposition(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  46 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/*     */     
/*     */ 
/*     */ 
/*  50 */     this.n = paramDoubleMatrix2D.rows();
/*     */     
/*  52 */     this.L = paramDoubleMatrix2D.like(this.n, this.n);
/*  53 */     this.isSymmetricPositiveDefinite = (paramDoubleMatrix2D.columns() == this.n);
/*     */     
/*     */ 
/*  56 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[this.n];
/*  57 */     for (int i = 0; i < this.n; i++) { arrayOfDoubleMatrix1D[i] = this.L.viewRow(i);
/*     */     }
/*     */     
/*  60 */     for (int j = 0; j < this.n; j++)
/*     */     {
/*     */ 
/*  63 */       double d1 = 0.0D;
/*  64 */       for (int k = 0; k < j; k++)
/*     */       {
/*  66 */         double d2 = arrayOfDoubleMatrix1D[k].zDotProduct(arrayOfDoubleMatrix1D[j], 0, k);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */         d2 = (paramDoubleMatrix2D.getQuick(j, k) - d2) / this.L.getQuick(k, k);
/*  75 */         arrayOfDoubleMatrix1D[j].setQuick(k, d2);
/*  76 */         d1 += d2 * d2;
/*  77 */         this.isSymmetricPositiveDefinite = ((this.isSymmetricPositiveDefinite) && (paramDoubleMatrix2D.getQuick(k, j) == paramDoubleMatrix2D.getQuick(j, k)));
/*     */       }
/*  79 */       d1 = paramDoubleMatrix2D.getQuick(j, j) - d1;
/*  80 */       this.isSymmetricPositiveDefinite = ((this.isSymmetricPositiveDefinite) && (d1 > 0.0D));
/*  81 */       this.L.setQuick(j, j, Math.sqrt(Math.max(d1, 0.0D)));
/*     */       
/*  83 */       for (int m = j + 1; m < this.n; m++) {
/*  84 */         this.L.setQuick(j, m, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getL()
/*     */   {
/*  93 */     return this.L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isSymmetricPositiveDefinite()
/*     */   {
/* 100 */     return this.isSymmetricPositiveDefinite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D solve(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 111 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/* 112 */     int i = paramDoubleMatrix2D.columns();
/*     */     
/*     */ 
/*     */ 
/* 116 */     for (int j = 0; j < i; j++)
/*     */     {
/* 118 */       for (int k = 0; k < this.n; k++) {
/* 119 */         double d1 = paramDoubleMatrix2D.getQuick(k, j);
/* 120 */         for (int i1 = k - 1; i1 >= 0; i1--) {
/* 121 */           d1 -= this.L.getQuick(k, i1) * localDoubleMatrix2D.getQuick(i1, j);
/*     */         }
/* 123 */         localDoubleMatrix2D.setQuick(k, j, d1 / this.L.getQuick(k, k));
/*     */       }
/*     */       
/*     */ 
/* 127 */       for (int m = this.n - 1; m >= 0; m--) {
/* 128 */         double d2 = localDoubleMatrix2D.getQuick(m, j);
/* 129 */         for (int i2 = m + 1; i2 < this.n; i2++) {
/* 130 */           d2 -= this.L.getQuick(i2, m) * localDoubleMatrix2D.getQuick(i2, j);
/*     */         }
/* 132 */         localDoubleMatrix2D.setQuick(m, j, d2 / this.L.getQuick(m, m));
/*     */       }
/*     */     }
/*     */     
/* 136 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DoubleMatrix2D XXXsolveBuggy(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 146 */     Functions localFunctions = Functions.functions;
/* 147 */     if (paramDoubleMatrix2D.rows() != this.n) {
/* 148 */       throw new IllegalArgumentException("Matrix row dimensions must agree.");
/*     */     }
/* 150 */     if (!this.isSymmetricPositiveDefinite) {
/* 151 */       throw new IllegalArgumentException("Matrix is not symmetric positive definite.");
/*     */     }
/*     */     
/*     */ 
/* 155 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/* 156 */     int i = paramDoubleMatrix2D.columns();
/*     */     
/*     */ 
/* 159 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[this.n];
/* 160 */     for (int j = 0; j < this.n; j++) { arrayOfDoubleMatrix1D[j] = localDoubleMatrix2D.viewRow(j);
/*     */     }
/*     */     
/* 163 */     for (int k = 0; k < this.n; k++) {
/* 164 */       for (m = k + 1; m < this.n; m++)
/*     */       {
/* 166 */         arrayOfDoubleMatrix1D[m].assign(arrayOfDoubleMatrix1D[k], Functions.minusMult(this.L.getQuick(m, k)));
/*     */       }
/* 168 */       arrayOfDoubleMatrix1D[k].assign(Functions.div(this.L.getQuick(k, k)));
/*     */     }
/*     */     
/*     */ 
/* 172 */     for (int m = this.n - 1; m >= 0; m--) {
/* 173 */       arrayOfDoubleMatrix1D[m].assign(Functions.div(this.L.getQuick(m, m)));
/* 174 */       for (int i1 = 0; i1 < m; i1++)
/*     */       {
/* 176 */         arrayOfDoubleMatrix1D[i1].assign(arrayOfDoubleMatrix1D[m], Functions.minusMult(this.L.getQuick(m, i1)));
/*     */       }
/*     */     }
/* 179 */     return localDoubleMatrix2D;
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
/*     */   public String toString()
/*     */   {
/* 192 */     StringBuffer localStringBuffer = new StringBuffer();
/* 193 */     String str = "Illegal operation or error: ";
/*     */     
/* 195 */     localStringBuffer.append("--------------------------------------------------------------------------\n");
/* 196 */     localStringBuffer.append("CholeskyDecomposition(A) --> isSymmetricPositiveDefinite(A), L, inverse(A)\n");
/* 197 */     localStringBuffer.append("--------------------------------------------------------------------------\n");
/*     */     
/* 199 */     localStringBuffer.append("isSymmetricPositiveDefinite = ");
/* 200 */     try { localStringBuffer.append(String.valueOf(isSymmetricPositiveDefinite()));
/* 201 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localStringBuffer.append(str + localIllegalArgumentException1.getMessage());
/*     */     }
/* 203 */     localStringBuffer.append("\n\nL = ");
/* 204 */     try { localStringBuffer.append(String.valueOf(getL()));
/* 205 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localStringBuffer.append(str + localIllegalArgumentException2.getMessage());
/*     */     }
/* 207 */     localStringBuffer.append("\n\ninverse(A) = ");
/* 208 */     try { localStringBuffer.append(String.valueOf(solve(DoubleFactory2D.dense.identity(this.L.rows()))));
/* 209 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localStringBuffer.append(str + localIllegalArgumentException3.getMessage());
/*     */     }
/* 211 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/CholeskyDecomposition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */