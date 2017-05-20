/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QRDecomposition
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   private DoubleMatrix2D QR;
/*     */   private int m;
/*     */   private int n;
/*     */   private DoubleMatrix1D Rdiag;
/*     */   
/*     */   public QRDecomposition(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  53 */     Property.DEFAULT.checkRectangular(paramDoubleMatrix2D);
/*     */     
/*  55 */     Functions localFunctions = Functions.functions;
/*     */     
/*  57 */     this.QR = paramDoubleMatrix2D.copy();
/*  58 */     this.m = paramDoubleMatrix2D.rows();
/*  59 */     this.n = paramDoubleMatrix2D.columns();
/*  60 */     this.Rdiag = paramDoubleMatrix2D.like1D(this.n);
/*     */     
/*  62 */     DoubleDoubleFunction localDoubleDoubleFunction = Algebra.hypotFunction();
/*     */     
/*     */ 
/*  65 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D1 = new DoubleMatrix1D[this.n];
/*  66 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D2 = new DoubleMatrix1D[this.n];
/*  67 */     for (int i = 0; i < this.n; i++) {
/*  68 */       arrayOfDoubleMatrix1D1[i] = this.QR.viewColumn(i);
/*  69 */       arrayOfDoubleMatrix1D2[i] = this.QR.viewColumn(i).viewPart(i, this.m - i);
/*     */     }
/*     */     
/*     */ 
/*  73 */     for (int j = 0; j < this.n; j++)
/*     */     {
/*     */ 
/*  76 */       double d1 = 0.0D;
/*     */       
/*     */ 
/*  79 */       for (int k = j; k < this.m; k++) {
/*  80 */         d1 = Algebra.hypot(d1, this.QR.getQuick(k, j));
/*     */       }
/*     */       
/*     */ 
/*  84 */       if (d1 != 0.0D)
/*     */       {
/*  86 */         if (this.QR.getQuick(j, j) < 0.0D) d1 = -d1;
/*  87 */         arrayOfDoubleMatrix1D2[j].assign(Functions.div(d1));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  94 */         this.QR.setQuick(j, j, this.QR.getQuick(j, j) + 1.0D);
/*     */         
/*     */ 
/*  97 */         for (int i1 = j + 1; i1 < this.n; i1++) {
/*  98 */           DoubleMatrix1D localDoubleMatrix1D = this.QR.viewColumn(i1).viewPart(j, this.m - j);
/*  99 */           double d2 = arrayOfDoubleMatrix1D2[j].zDotProduct(localDoubleMatrix1D);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */           d2 = -d2 / this.QR.getQuick(j, j);
/*     */           
/*     */ 
/* 112 */           for (int i2 = j; i2 < this.m; i2++) {
/* 113 */             this.QR.setQuick(i2, i1, this.QR.getQuick(i2, i1) + d2 * this.QR.getQuick(i2, j));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 118 */       this.Rdiag.setQuick(j, -d1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getH()
/*     */   {
/* 126 */     return Algebra.DEFAULT.trapezoidalLower(this.QR.copy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getQ()
/*     */   {
/* 133 */     Functions localFunctions = Functions.functions;
/* 134 */     DoubleMatrix2D localDoubleMatrix2D = this.QR.like();
/*     */     
/* 136 */     for (int i = this.n - 1; i >= 0; i--) {
/* 137 */       DoubleMatrix1D localDoubleMatrix1D1 = this.QR.viewColumn(i).viewPart(i, this.m - i);
/* 138 */       localDoubleMatrix2D.setQuick(i, i, 1.0D);
/* 139 */       for (int j = i; j < this.n; j++) {
/* 140 */         if (this.QR.getQuick(i, i) != 0.0D) {
/* 141 */           DoubleMatrix1D localDoubleMatrix1D2 = localDoubleMatrix2D.viewColumn(j).viewPart(i, this.m - i);
/* 142 */           double d = localDoubleMatrix1D1.zDotProduct(localDoubleMatrix1D2);
/* 143 */           d = -d / this.QR.getQuick(i, i);
/* 144 */           localDoubleMatrix1D2.assign(localDoubleMatrix1D1, Functions.plusMult(d));
/*     */         }
/*     */       }
/*     */     }
/* 148 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getR()
/*     */   {
/* 155 */     DoubleMatrix2D localDoubleMatrix2D = this.QR.like(this.n, this.n);
/* 156 */     for (int i = 0; i < this.n; i++) {
/* 157 */       for (int j = 0; j < this.n; j++) {
/* 158 */         if (i < j) {
/* 159 */           localDoubleMatrix2D.setQuick(i, j, this.QR.getQuick(i, j));
/* 160 */         } else if (i == j) {
/* 161 */           localDoubleMatrix2D.setQuick(i, j, this.Rdiag.getQuick(i));
/*     */         } else
/* 163 */           localDoubleMatrix2D.setQuick(i, j, 0.0D);
/*     */       }
/*     */     }
/* 166 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hasFullRank()
/*     */   {
/* 173 */     for (int i = 0; i < this.n; i++) {
/* 174 */       if (this.Rdiag.getQuick(i) == 0.0D) return false;
/*     */     }
/* 176 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D solve(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 186 */     Functions localFunctions = Functions.functions;
/* 187 */     if (paramDoubleMatrix2D.rows() != this.m) {
/* 188 */       throw new IllegalArgumentException("Matrix row dimensions must agree.");
/*     */     }
/* 190 */     if (!hasFullRank()) {
/* 191 */       throw new IllegalArgumentException("Matrix is rank deficient.");
/*     */     }
/*     */     
/*     */ 
/* 195 */     int i = paramDoubleMatrix2D.columns();
/* 196 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/*     */     
/*     */     int i3;
/* 199 */     for (int j = 0; j < this.n; j++) {
/* 200 */       for (k = 0; k < i; k++) {
/* 201 */         double d = 0.0D;
/* 202 */         for (i3 = j; i3 < this.m; i3++) {
/* 203 */           d += this.QR.getQuick(i3, j) * localDoubleMatrix2D.getQuick(i3, k);
/*     */         }
/* 205 */         d = -d / this.QR.getQuick(j, j);
/* 206 */         for (int i4 = j; i4 < this.m; i4++) {
/* 207 */           localDoubleMatrix2D.setQuick(i4, k, localDoubleMatrix2D.getQuick(i4, k) + d * this.QR.getQuick(i4, j));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 212 */     for (int k = this.n - 1; k >= 0; k--) {
/* 213 */       for (int i1 = 0; i1 < i; i1++) {
/* 214 */         localDoubleMatrix2D.setQuick(k, i1, localDoubleMatrix2D.getQuick(k, i1) / this.Rdiag.getQuick(k));
/*     */       }
/* 216 */       for (int i2 = 0; i2 < k; i2++) {
/* 217 */         for (i3 = 0; i3 < i; i3++) {
/* 218 */           localDoubleMatrix2D.setQuick(i2, i3, localDoubleMatrix2D.getQuick(i2, i3) - localDoubleMatrix2D.getQuick(k, i3) * this.QR.getQuick(i2, k));
/*     */         }
/*     */       }
/*     */     }
/* 222 */     return localDoubleMatrix2D.viewPart(0, 0, this.n, i);
/*     */   }
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
/* 234 */     StringBuffer localStringBuffer = new StringBuffer();
/* 235 */     String str = "Illegal operation or error: ";
/*     */     
/* 237 */     localStringBuffer.append("-----------------------------------------------------------------\n");
/* 238 */     localStringBuffer.append("QRDecomposition(A) --> hasFullRank(A), H, Q, R, pseudo inverse(A)\n");
/* 239 */     localStringBuffer.append("-----------------------------------------------------------------\n");
/*     */     
/* 241 */     localStringBuffer.append("hasFullRank = ");
/* 242 */     try { localStringBuffer.append(String.valueOf(hasFullRank()));
/* 243 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localStringBuffer.append(str + localIllegalArgumentException1.getMessage());
/*     */     }
/* 245 */     localStringBuffer.append("\n\nH = ");
/* 246 */     try { localStringBuffer.append(String.valueOf(getH()));
/* 247 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localStringBuffer.append(str + localIllegalArgumentException2.getMessage());
/*     */     }
/* 249 */     localStringBuffer.append("\n\nQ = ");
/* 250 */     try { localStringBuffer.append(String.valueOf(getQ()));
/* 251 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localStringBuffer.append(str + localIllegalArgumentException3.getMessage());
/*     */     }
/* 253 */     localStringBuffer.append("\n\nR = ");
/* 254 */     try { localStringBuffer.append(String.valueOf(getR()));
/* 255 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localStringBuffer.append(str + localIllegalArgumentException4.getMessage());
/*     */     }
/* 257 */     localStringBuffer.append("\n\npseudo inverse(A) = ");
/* 258 */     try { localStringBuffer.append(String.valueOf(solve(DoubleFactory2D.dense.identity(this.QR.rows()))));
/* 259 */     } catch (IllegalArgumentException localIllegalArgumentException5) { localStringBuffer.append(str + localIllegalArgumentException5.getMessage());
/*     */     }
/* 261 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/QRDecomposition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */