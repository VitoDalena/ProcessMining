/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.matrix.DoubleFactory1D;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
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
/*     */ public class EigenvalueDecomposition
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   private int n;
/*     */   private boolean issymmetric;
/*     */   private double[] d;
/*     */   private double[] e;
/*     */   private double[][] V;
/*     */   private double[][] H;
/*     */   private double[] ort;
/*     */   private transient double cdivr;
/*     */   private transient double cdivi;
/*     */   
/*     */   public EigenvalueDecomposition(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  76 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/*     */     
/*  78 */     this.n = paramDoubleMatrix2D.columns();
/*  79 */     this.V = new double[this.n][this.n];
/*  80 */     this.d = new double[this.n];
/*  81 */     this.e = new double[this.n];
/*     */     
/*  83 */     this.issymmetric = Property.DEFAULT.isSymmetric(paramDoubleMatrix2D);
/*     */     int i;
/*  85 */     int j; if (this.issymmetric) {
/*  86 */       for (i = 0; i < this.n; i++) {
/*  87 */         for (j = 0; j < this.n; j++) {
/*  88 */           this.V[i][j] = paramDoubleMatrix2D.getQuick(i, j);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  93 */       tred2();
/*     */       
/*     */ 
/*  96 */       tql2();
/*     */     }
/*     */     else
/*     */     {
/* 100 */       this.H = new double[this.n][this.n];
/* 101 */       this.ort = new double[this.n];
/*     */       
/* 103 */       for (i = 0; i < this.n; i++) {
/* 104 */         for (j = 0; j < this.n; j++) {
/* 105 */           this.H[j][i] = paramDoubleMatrix2D.getQuick(j, i);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 110 */       orthes();
/*     */       
/*     */ 
/* 113 */       hqr2();
/*     */     } }
/*     */   
/*     */   private void cdiv(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) { double d1;
/*     */     double d2;
/* 118 */     if (Math.abs(paramDouble3) > Math.abs(paramDouble4)) {
/* 119 */       d1 = paramDouble4 / paramDouble3;
/* 120 */       d2 = paramDouble3 + d1 * paramDouble4;
/* 121 */       this.cdivr = ((paramDouble1 + d1 * paramDouble2) / d2);
/* 122 */       this.cdivi = ((paramDouble2 - d1 * paramDouble1) / d2);
/*     */     }
/*     */     else {
/* 125 */       d1 = paramDouble3 / paramDouble4;
/* 126 */       d2 = paramDouble4 + d1 * paramDouble3;
/* 127 */       this.cdivr = ((d1 * paramDouble1 + paramDouble2) / d2);
/* 128 */       this.cdivi = ((d1 * paramDouble2 - paramDouble1) / d2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getD()
/*     */   {
/* 136 */     double[][] arrayOfDouble = new double[this.n][this.n];
/* 137 */     for (int i = 0; i < this.n; i++) {
/* 138 */       for (int j = 0; j < this.n; j++) {
/* 139 */         arrayOfDouble[i][j] = 0.0D;
/*     */       }
/* 141 */       arrayOfDouble[i][i] = this.d[i];
/* 142 */       if (this.e[i] > 0.0D) {
/* 143 */         arrayOfDouble[i][(i + 1)] = this.e[i];
/*     */       }
/* 145 */       else if (this.e[i] < 0.0D) {
/* 146 */         arrayOfDouble[i][(i - 1)] = this.e[i];
/*     */       }
/*     */     }
/* 149 */     return DoubleFactory2D.dense.make(arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D getImagEigenvalues()
/*     */   {
/* 156 */     return DoubleFactory1D.dense.make(this.e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D getRealEigenvalues()
/*     */   {
/* 163 */     return DoubleFactory1D.dense.make(this.d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getV()
/*     */   {
/* 170 */     return DoubleFactory2D.dense.make(this.V);
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
/*     */   private void hqr2()
/*     */   {
/* 183 */     int i = this.n;
/* 184 */     double d1 = i - 1;
/* 185 */     double d2 = 0;
/* 186 */     double d3 = i - 1;
/* 187 */     double d4 = Math.pow(2.0D, -52.0D);
/* 188 */     double d5 = 0.0D;
/* 189 */     double d6 = 0.0D;double d7 = 0.0D;double d8 = 0.0D;double d9 = 0.0D;double d10 = 0.0D;
/*     */     
/*     */ 
/*     */ 
/* 193 */     double d15 = 0.0D;
/* 194 */     for (int j = 0; j < i; j++) {
/* 195 */       if (((j < d2 ? 1 : 0) | (j > d3 ? 1 : 0)) != 0) {
/* 196 */         this.d[j] = this.H[j][j];
/* 197 */         this.e[j] = 0.0D;
/*     */       }
/* 199 */       for (k = Math.max(j - 1, 0); k < i; k++) {
/* 200 */         d15 += Math.abs(this.H[j][k]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 206 */     int k = 0;
/* 207 */     double d12; double d13; int m; double d14; while (d1 >= d2)
/*     */     {
/*     */ 
/*     */ 
/* 211 */       d16 = d1;
/* 212 */       while (d16 > d2) {
/* 213 */         d9 = Math.abs(this.H[(d16 - 1)][(d16 - 1)]) + Math.abs(this.H[d16][d16]);
/* 214 */         if (d9 == 0.0D) {
/* 215 */           d9 = d15;
/*     */         }
/* 217 */         if (Math.abs(this.H[d16][(d16 - 1)]) < d4 * d9) {
/*     */           break;
/*     */         }
/* 220 */         d16--;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 226 */       if (d16 == d1) {
/* 227 */         this.H[d1][d1] += d5;
/* 228 */         this.d[d1] = this.H[d1][d1];
/* 229 */         this.e[d1] = 0.0D;
/* 230 */         d1--;
/* 231 */         k = 0;
/*     */       }
/*     */       else {
/*     */         double d19;
/* 235 */         if (d16 == d1 - 1) {
/* 236 */           d12 = this.H[d1][(d1 - 1)] * this.H[(d1 - 1)][d1];
/* 237 */           d6 = (this.H[(d1 - 1)][(d1 - 1)] - this.H[d1][d1]) / 2.0D;
/* 238 */           d7 = d6 * d6 + d12;
/* 239 */           d10 = Math.sqrt(Math.abs(d7));
/* 240 */           this.H[d1][d1] += d5;
/* 241 */           this.H[(d1 - 1)][(d1 - 1)] += d5;
/* 242 */           d13 = this.H[d1][d1];
/*     */           
/*     */ 
/*     */ 
/* 246 */           if (d7 >= 0.0D) {
/* 247 */             if (d6 >= 0.0D) {
/* 248 */               d10 = d6 + d10;
/*     */             } else {
/* 250 */               d10 = d6 - d10;
/*     */             }
/* 252 */             this.d[(d1 - 1)] = (d13 + d10);
/* 253 */             this.d[d1] = this.d[(d1 - 1)];
/* 254 */             if (d10 != 0.0D) {
/* 255 */               this.d[d1] = (d13 - d12 / d10);
/*     */             }
/* 257 */             this.e[(d1 - 1)] = 0.0D;
/* 258 */             this.e[d1] = 0.0D;
/* 259 */             d13 = this.H[d1][(d1 - 1)];
/* 260 */             d9 = Math.abs(d13) + Math.abs(d10);
/* 261 */             d6 = d13 / d9;
/* 262 */             d7 = d10 / d9;
/* 263 */             d8 = Math.sqrt(d6 * d6 + d7 * d7);
/* 264 */             d6 /= d8;
/* 265 */             d7 /= d8;
/*     */             
/*     */ 
/*     */ 
/* 269 */             for (d17 = d1 - 1; d17 < i; d17++) {
/* 270 */               d10 = this.H[(d1 - 1)][d17];
/* 271 */               this.H[(d1 - 1)][d17] = (d7 * d10 + d6 * this.H[d1][d17]);
/* 272 */               this.H[d1][d17] = (d7 * this.H[d1][d17] - d6 * d10);
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 277 */             for (m = 0; m <= d1; m++) {
/* 278 */               d10 = this.H[m][(d1 - 1)];
/* 279 */               this.H[m][(d1 - 1)] = (d7 * d10 + d6 * this.H[m][d1]);
/* 280 */               this.H[m][d1] = (d7 * this.H[m][d1] - d6 * d10);
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 285 */             for (d19 = d2; d19 <= d3; d19++) {
/* 286 */               d10 = this.V[d19][(d1 - 1)];
/* 287 */               this.V[d19][(d1 - 1)] = (d7 * d10 + d6 * this.V[d19][d1]);
/* 288 */               this.V[d19][d1] = (d7 * this.V[d19][d1] - d6 * d10);
/*     */             }
/*     */             
/*     */           }
/*     */           else
/*     */           {
/* 294 */             this.d[(d1 - 1)] = (d13 + d6);
/* 295 */             this.d[d1] = (d13 + d6);
/* 296 */             this.e[(d1 - 1)] = d10;
/* 297 */             this.e[d1] = (-d10);
/*     */           }
/* 299 */           d1 -= 2;
/* 300 */           k = 0;
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*     */ 
/* 308 */           d13 = this.H[d1][d1];
/* 309 */           d14 = 0.0D;
/* 310 */           d12 = 0.0D;
/* 311 */           if (d16 < d1) {
/* 312 */             d14 = this.H[(d1 - 1)][(d1 - 1)];
/* 313 */             d12 = this.H[d1][(d1 - 1)] * this.H[(d1 - 1)][d1];
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 318 */           if (k == 10) {
/* 319 */             d5 += d13;
/* 320 */             for (d17 = d2; d17 <= d1; d17++) {
/* 321 */               this.H[d17][d17] -= d13;
/*     */             }
/* 323 */             d9 = Math.abs(this.H[d1][(d1 - 1)]) + Math.abs(this.H[(d1 - 1)][(d1 - 2)]);
/* 324 */             d13 = d14 = 0.75D * d9;
/* 325 */             d12 = -0.4375D * d9 * d9;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 330 */           if (k == 30) {
/* 331 */             d9 = (d14 - d13) / 2.0D;
/* 332 */             d9 = d9 * d9 + d12;
/* 333 */             if (d9 > 0.0D) {
/* 334 */               d9 = Math.sqrt(d9);
/* 335 */               if (d14 < d13) {
/* 336 */                 d9 = -d9;
/*     */               }
/* 338 */               d9 = d13 - d12 / ((d14 - d13) / 2.0D + d9);
/* 339 */               for (d17 = d2; d17 <= d1; d17++) {
/* 340 */                 this.H[d17][d17] -= d9;
/*     */               }
/* 342 */               d5 += d9;
/* 343 */               d13 = d14 = d12 = 0.964D;
/*     */             }
/*     */           }
/*     */           
/* 347 */           k += 1;
/*     */           
/*     */ 
/*     */ 
/* 351 */           d17 = d1 - 2;
/* 352 */           while (d17 >= d16) {
/* 353 */             d10 = this.H[d17][d17];
/* 354 */             d8 = d13 - d10;
/* 355 */             d9 = d14 - d10;
/* 356 */             d6 = (d8 * d9 - d12) / this.H[(d17 + 1)][d17] + this.H[d17][(d17 + 1)];
/* 357 */             d7 = this.H[(d17 + 1)][(d17 + 1)] - d10 - d8 - d9;
/* 358 */             d8 = this.H[(d17 + 2)][(d17 + 1)];
/* 359 */             d9 = Math.abs(d6) + Math.abs(d7) + Math.abs(d8);
/* 360 */             d6 /= d9;
/* 361 */             d7 /= d9;
/* 362 */             d8 /= d9;
/* 363 */             if (d17 == d16) {
/*     */               break;
/*     */             }
/* 366 */             if (Math.abs(this.H[d17][(d17 - 1)]) * (Math.abs(d7) + Math.abs(d8)) < d4 * (Math.abs(d6) * (Math.abs(this.H[(d17 - 1)][(d17 - 1)]) + Math.abs(d10) + Math.abs(this.H[(d17 + 1)][(d17 + 1)])))) {
/*     */               break;
/*     */             }
/*     */             
/*     */ 
/* 371 */             d17--;
/*     */           }
/*     */           
/* 374 */           for (m = d17 + 2; m <= d1; m++) {
/* 375 */             this.H[m][(m - 2)] = 0.0D;
/* 376 */             if (m > d17 + 2) {
/* 377 */               this.H[m][(m - 3)] = 0.0D;
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 383 */           for (d19 = d17; d19 <= d1 - 1; d19++) {
/* 384 */             int i2 = d19 != d1 - 1 ? 1 : 0;
/* 385 */             if (d19 != d17) {
/* 386 */               d6 = this.H[d19][(d19 - 1)];
/* 387 */               d7 = this.H[(d19 + 1)][(d19 - 1)];
/* 388 */               d8 = i2 != 0 ? this.H[(d19 + 2)][(d19 - 1)] : 0.0D;
/* 389 */               d13 = Math.abs(d6) + Math.abs(d7) + Math.abs(d8);
/* 390 */               if (d13 != 0.0D) {
/* 391 */                 d6 /= d13;
/* 392 */                 d7 /= d13;
/* 393 */                 d8 /= d13;
/*     */               }
/*     */             }
/* 396 */             if (d13 == 0.0D) {
/*     */               break;
/*     */             }
/* 399 */             d9 = Math.sqrt(d6 * d6 + d7 * d7 + d8 * d8);
/* 400 */             if (d6 < 0.0D) {
/* 401 */               d9 = -d9;
/*     */             }
/* 403 */             if (d9 != 0.0D) {
/* 404 */               if (d19 != d17) {
/* 405 */                 this.H[d19][(d19 - 1)] = (-d9 * d13);
/* 406 */               } else if (d16 != d17) {
/* 407 */                 this.H[d19][(d19 - 1)] = (-this.H[d19][(d19 - 1)]);
/*     */               }
/* 409 */               d6 += d9;
/* 410 */               d13 = d6 / d9;
/* 411 */               d14 = d7 / d9;
/* 412 */               d10 = d8 / d9;
/* 413 */               d7 /= d6;
/* 414 */               d8 /= d6;
/*     */               
/*     */ 
/*     */ 
/* 418 */               for (int i3 = d19; i3 < i; i3++) {
/* 419 */                 d6 = this.H[d19][i3] + d7 * this.H[(d19 + 1)][i3];
/* 420 */                 if (i2 != 0) {
/* 421 */                   d6 += d8 * this.H[(d19 + 2)][i3];
/* 422 */                   this.H[(d19 + 2)][i3] -= d6 * d10;
/*     */                 }
/* 424 */                 this.H[d19][i3] -= d6 * d13;
/* 425 */                 this.H[(d19 + 1)][i3] -= d6 * d14;
/*     */               }
/*     */               
/*     */ 
/*     */ 
/* 430 */               for (int i4 = 0; i4 <= Math.min(d1, d19 + 3); i4++) {
/* 431 */                 d6 = d13 * this.H[i4][d19] + d14 * this.H[i4][(d19 + 1)];
/* 432 */                 if (i2 != 0) {
/* 433 */                   d6 += d10 * this.H[i4][(d19 + 2)];
/* 434 */                   this.H[i4][(d19 + 2)] -= d6 * d8;
/*     */                 }
/* 436 */                 this.H[i4][d19] -= d6;
/* 437 */                 this.H[i4][(d19 + 1)] -= d6 * d7;
/*     */               }
/*     */               
/*     */ 
/*     */ 
/* 442 */               for (double d22 = d2; d22 <= d3; d22++) {
/* 443 */                 d6 = d13 * this.V[d22][d19] + d14 * this.V[d22][(d19 + 1)];
/* 444 */                 if (i2 != 0) {
/* 445 */                   d6 += d10 * this.V[d22][(d19 + 2)];
/* 446 */                   this.V[d22][(d19 + 2)] -= d6 * d8;
/*     */                 }
/* 448 */                 this.V[d22][d19] -= d6;
/* 449 */                 this.V[d22][(d19 + 1)] -= d6 * d7;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 458 */     if (d15 == 0.0D)
/*     */       return;
/*     */     int i1;
/*     */     double d18;
/* 462 */     for (d1 = i - 1; d1 >= 0; d1--) {
/* 463 */       d6 = this.d[d1];
/* 464 */       d7 = this.e[d1];
/*     */       
/*     */       double d11;
/*     */       
/* 468 */       if (d7 == 0.0D) {
/* 469 */         d16 = d1;
/* 470 */         this.H[d1][d1] = 1.0D;
/* 471 */         for (d17 = d1 - 1; d17 >= 0; d17--) {
/* 472 */           d12 = this.H[d17][d17] - d6;
/* 473 */           d8 = 0.0D;
/* 474 */           for (m = d16; m <= d1; m++) {
/* 475 */             d8 += this.H[d17][m] * this.H[m][d1];
/*     */           }
/* 477 */           if (this.e[d17] < 0.0D) {
/* 478 */             d10 = d12;
/* 479 */             d9 = d8;
/*     */           } else {
/* 481 */             d16 = d17;
/* 482 */             if (this.e[d17] == 0.0D) {
/* 483 */               if (d12 != 0.0D) {
/* 484 */                 this.H[d17][d1] = (-d8 / d12);
/*     */               } else {
/* 486 */                 this.H[d17][d1] = (-d8 / (d4 * d15));
/*     */               }
/*     */               
/*     */             }
/*     */             else
/*     */             {
/* 492 */               d13 = this.H[d17][(d17 + 1)];
/* 493 */               d14 = this.H[(d17 + 1)][d17];
/* 494 */               d7 = (this.d[d17] - d6) * (this.d[d17] - d6) + this.e[d17] * this.e[d17];
/* 495 */               d11 = (d13 * d9 - d10 * d8) / d7;
/* 496 */               this.H[d17][d1] = d11;
/* 497 */               if (Math.abs(d13) > Math.abs(d10)) {
/* 498 */                 this.H[(d17 + 1)][d1] = ((-d8 - d12 * d11) / d13);
/*     */               } else {
/* 500 */                 this.H[(d17 + 1)][d1] = ((-d9 - d14 * d11) / d10);
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 506 */             d11 = Math.abs(this.H[d17][d1]);
/* 507 */             if (d4 * d11 * d11 > 1.0D) {
/* 508 */               for (i1 = d17; i1 <= d1; i1++) {
/* 509 */                 this.H[i1][d1] /= d11;
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */       }
/* 517 */       else if (d7 < 0.0D) {
/* 518 */         d16 = d1 - 1;
/*     */         
/*     */ 
/*     */ 
/* 522 */         if (Math.abs(this.H[d1][(d1 - 1)]) > Math.abs(this.H[(d1 - 1)][d1])) {
/* 523 */           this.H[(d1 - 1)][(d1 - 1)] = (d7 / this.H[d1][(d1 - 1)]);
/* 524 */           this.H[(d1 - 1)][d1] = (-(this.H[d1][d1] - d6) / this.H[d1][(d1 - 1)]);
/*     */         } else {
/* 526 */           cdiv(0.0D, -this.H[(d1 - 1)][d1], this.H[(d1 - 1)][(d1 - 1)] - d6, d7);
/* 527 */           this.H[(d1 - 1)][(d1 - 1)] = this.cdivr;
/* 528 */           this.H[(d1 - 1)][d1] = this.cdivi;
/*     */         }
/* 530 */         this.H[d1][(d1 - 1)] = 0.0D;
/* 531 */         this.H[d1][d1] = 1.0D;
/* 532 */         for (d17 = d1 - 2; d17 >= 0; d17--)
/*     */         {
/* 534 */           d18 = 0.0D;
/* 535 */           double d20 = 0.0D;
/* 536 */           for (int i5 = d16; i5 <= d1; i5++) {
/* 537 */             d18 += this.H[d17][i5] * this.H[i5][(d1 - 1)];
/* 538 */             d20 += this.H[d17][i5] * this.H[i5][d1];
/*     */           }
/* 540 */           d12 = this.H[d17][d17] - d6;
/*     */           
/* 542 */           if (this.e[d17] < 0.0D) {
/* 543 */             d10 = d12;
/* 544 */             d8 = d18;
/* 545 */             d9 = d20;
/*     */           } else {
/* 547 */             d16 = d17;
/* 548 */             if (this.e[d17] == 0.0D) {
/* 549 */               cdiv(-d18, -d20, d12, d7);
/* 550 */               this.H[d17][(d1 - 1)] = this.cdivr;
/* 551 */               this.H[d17][d1] = this.cdivi;
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 556 */               d13 = this.H[d17][(d17 + 1)];
/* 557 */               d14 = this.H[(d17 + 1)][d17];
/* 558 */               double d21 = (this.d[d17] - d6) * (this.d[d17] - d6) + this.e[d17] * this.e[d17] - d7 * d7;
/* 559 */               double d23 = (this.d[d17] - d6) * 2.0D * d7;
/* 560 */               if (((d21 == 0.0D ? 1 : 0) & (d23 == 0.0D ? 1 : 0)) != 0) {
/* 561 */                 d21 = d4 * d15 * (Math.abs(d12) + Math.abs(d7) + Math.abs(d13) + Math.abs(d14) + Math.abs(d10));
/*     */               }
/*     */               
/* 564 */               cdiv(d13 * d8 - d10 * d18 + d7 * d20, d13 * d9 - d10 * d20 - d7 * d18, d21, d23);
/* 565 */               this.H[d17][(d1 - 1)] = this.cdivr;
/* 566 */               this.H[d17][d1] = this.cdivi;
/* 567 */               if (Math.abs(d13) > Math.abs(d10) + Math.abs(d7)) {
/* 568 */                 this.H[(d17 + 1)][(d1 - 1)] = ((-d18 - d12 * this.H[d17][(d1 - 1)] + d7 * this.H[d17][d1]) / d13);
/* 569 */                 this.H[(d17 + 1)][d1] = ((-d20 - d12 * this.H[d17][d1] - d7 * this.H[d17][(d1 - 1)]) / d13);
/*     */               } else {
/* 571 */                 cdiv(-d8 - d14 * this.H[d17][(d1 - 1)], -d9 - d14 * this.H[d17][d1], d10, d7);
/* 572 */                 this.H[(d17 + 1)][(d1 - 1)] = this.cdivr;
/* 573 */                 this.H[(d17 + 1)][d1] = this.cdivi;
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 579 */             d11 = Math.max(Math.abs(this.H[d17][(d1 - 1)]), Math.abs(this.H[d17][d1]));
/* 580 */             if (d4 * d11 * d11 > 1.0D) {
/* 581 */               for (int i6 = d17; i6 <= d1; i6++) {
/* 582 */                 this.H[i6][(d1 - 1)] /= d11;
/* 583 */                 this.H[i6][d1] /= d11;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 593 */     for (double d16 = 0; d16 < i; d16++) {
/* 594 */       if (((d16 < d2 ? 1 : 0) | (d16 > d3 ? 1 : 0)) != 0) {
/* 595 */         for (d17 = d16; d17 < i; d17++) {
/* 596 */           this.V[d16][d17] = this.H[d16][d17];
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 603 */     for (double d17 = i - 1; d17 >= d2; d17--) {
/* 604 */       for (d18 = d2; d18 <= d3; d18++) {
/* 605 */         d10 = 0.0D;
/* 606 */         for (i1 = d2; i1 <= Math.min(d17, d3); i1++) {
/* 607 */           d10 += this.V[d18][i1] * this.H[i1][d17];
/*     */         }
/* 609 */         this.V[d18][d17] = d10;
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
/*     */   private void orthes()
/*     */   {
/* 622 */     int i = 0;
/* 623 */     double d1 = this.n - 1;
/*     */     int k;
/* 625 */     double d5; double d7; for (double d2 = i + 1; d2 <= d1 - 1; d2++)
/*     */     {
/*     */ 
/*     */ 
/* 629 */       double d3 = 0.0D;
/* 630 */       for (k = d2; k <= d1; k++) {
/* 631 */         d3 += Math.abs(this.H[k][(d2 - 1)]);
/*     */       }
/* 633 */       if (d3 != 0.0D)
/*     */       {
/*     */ 
/*     */ 
/* 637 */         d5 = 0.0D;
/* 638 */         for (int m = d1; m >= d2; m--) {
/* 639 */           this.ort[m] = (this.H[m][(d2 - 1)] / d3);
/* 640 */           d5 += this.ort[m] * this.ort[m];
/*     */         }
/* 642 */         d7 = Math.sqrt(d5);
/* 643 */         if (this.ort[d2] > 0.0D) {
/* 644 */           d7 = -d7;
/*     */         }
/* 646 */         d5 -= this.ort[d2] * d7;
/* 647 */         this.ort[d2] -= d7;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 652 */         for (int i1 = d2; i1 < this.n; i1++) {
/* 653 */           double d9 = 0.0D;
/* 654 */           for (int i2 = d1; i2 >= d2; i2--) {
/* 655 */             d9 += this.ort[i2] * this.H[i2][i1];
/*     */           }
/* 657 */           d9 /= d5;
/* 658 */           for (double d12 = d2; d12 <= d1; d12++) {
/* 659 */             this.H[d12][i1] -= d9 * this.ort[d12];
/*     */           }
/*     */         }
/*     */         
/* 663 */         for (double d10 = 0; d10 <= d1; d10++) {
/* 664 */           double d11 = 0.0D;
/* 665 */           for (int i3 = d1; i3 >= d2; i3--) {
/* 666 */             d11 += this.ort[i3] * this.H[d10][i3];
/*     */           }
/* 668 */           d11 /= d5;
/* 669 */           for (double d13 = d2; d13 <= d1; d13++) {
/* 670 */             this.H[d10][d13] -= d11 * this.ort[d13];
/*     */           }
/*     */         }
/* 673 */         this.ort[d2] = (d3 * this.ort[d2]);
/* 674 */         this.H[d2][(d2 - 1)] = (d3 * d7);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 680 */     for (int j = 0; j < this.n; j++) {
/* 681 */       for (d4 = 0; d4 < this.n; d4++) {
/* 682 */         this.V[j][d4] = (j == d4 ? 1.0D : 0.0D);
/*     */       }
/*     */     }
/*     */     
/* 686 */     for (double d4 = d1 - 1; d4 >= i + 1; d4--) {
/* 687 */       if (this.H[d4][(d4 - 1)] != 0.0D) {
/* 688 */         for (k = d4 + 1; k <= d1; k++) {
/* 689 */           this.ort[k] = this.H[k][(d4 - 1)];
/*     */         }
/* 691 */         for (d5 = d4; d5 <= d1; d5++) {
/* 692 */           double d6 = 0.0D;
/* 693 */           for (d7 = d4; d7 <= d1; d7++) {
/* 694 */             d6 += this.ort[d7] * this.V[d7][d5];
/*     */           }
/*     */           
/* 697 */           d6 = d6 / this.ort[d4] / this.H[d4][(d4 - 1)];
/* 698 */           for (double d8 = d4; d8 <= d1; d8++) {
/* 699 */             this.V[d8][d5] += d6 * this.ort[d8];
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
/*     */ 
/*     */   public String toString()
/*     */   {
/* 715 */     StringBuffer localStringBuffer = new StringBuffer();
/* 716 */     String str = "Illegal operation or error: ";
/*     */     
/* 718 */     localStringBuffer.append("---------------------------------------------------------------------\n");
/* 719 */     localStringBuffer.append("EigenvalueDecomposition(A) --> D, V, realEigenvalues, imagEigenvalues\n");
/* 720 */     localStringBuffer.append("---------------------------------------------------------------------\n");
/*     */     
/* 722 */     localStringBuffer.append("realEigenvalues = ");
/* 723 */     try { localStringBuffer.append(String.valueOf(getRealEigenvalues()));
/* 724 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localStringBuffer.append(str + localIllegalArgumentException1.getMessage());
/*     */     }
/* 726 */     localStringBuffer.append("\nimagEigenvalues = ");
/* 727 */     try { localStringBuffer.append(String.valueOf(getImagEigenvalues()));
/* 728 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localStringBuffer.append(str + localIllegalArgumentException2.getMessage());
/*     */     }
/* 730 */     localStringBuffer.append("\n\nD = ");
/* 731 */     try { localStringBuffer.append(String.valueOf(getD()));
/* 732 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localStringBuffer.append(str + localIllegalArgumentException3.getMessage());
/*     */     }
/* 734 */     localStringBuffer.append("\n\nV = ");
/* 735 */     try { localStringBuffer.append(String.valueOf(getV()));
/* 736 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localStringBuffer.append(str + localIllegalArgumentException4.getMessage());
/*     */     }
/* 738 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void tql2()
/*     */   {
/* 750 */     for (int i = 1; i < this.n; i++) {
/* 751 */       this.e[(i - 1)] = this.e[i];
/*     */     }
/* 753 */     this.e[(this.n - 1)] = 0.0D;
/*     */     
/* 755 */     double d1 = 0.0D;
/* 756 */     double d2 = 0.0D;
/* 757 */     double d3 = Math.pow(2.0D, -52.0D);
/* 758 */     int m; double d4; for (int j = 0; j < this.n; j++)
/*     */     {
/*     */ 
/*     */ 
/* 762 */       d2 = Math.max(d2, Math.abs(this.d[j]) + Math.abs(this.e[j]));
/* 763 */       k = j;
/* 764 */       while (k < this.n) {
/* 765 */         if (Math.abs(this.e[k]) <= d3 * d2) {
/*     */           break;
/*     */         }
/* 768 */         k++;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 774 */       if (k > j) {
/* 775 */         m = 0;
/*     */         do {
/* 777 */           m += 1;
/*     */           
/*     */ 
/*     */ 
/* 781 */           d4 = this.d[j];
/* 782 */           double d5 = (this.d[(j + 1)] - d4) / (2.0D * this.e[j]);
/* 783 */           double d6 = Algebra.hypot(d5, 1.0D);
/* 784 */           if (d5 < 0.0D) {
/* 785 */             d6 = -d6;
/*     */           }
/* 787 */           this.d[j] = (this.e[j] / (d5 + d6));
/* 788 */           this.d[(j + 1)] = (this.e[j] * (d5 + d6));
/* 789 */           double d7 = this.d[(j + 1)];
/* 790 */           double d8 = d4 - this.d[j];
/* 791 */           for (int i3 = j + 2; i3 < this.n; i3++) {
/* 792 */             this.d[i3] -= d8;
/*     */           }
/* 794 */           d1 += d8;
/*     */           
/*     */ 
/*     */ 
/* 798 */           d5 = this.d[k];
/* 799 */           double d9 = 1.0D;
/* 800 */           double d10 = d9;
/* 801 */           double d11 = d9;
/* 802 */           double d12 = this.e[(j + 1)];
/* 803 */           double d13 = 0.0D;
/* 804 */           double d14 = 0.0D;
/* 805 */           for (int i4 = k - 1; i4 >= j; i4--) {
/* 806 */             d11 = d10;
/* 807 */             d10 = d9;
/* 808 */             d14 = d13;
/* 809 */             d4 = d9 * this.e[i4];
/* 810 */             d8 = d9 * d5;
/* 811 */             d6 = Algebra.hypot(d5, this.e[i4]);
/* 812 */             this.e[(i4 + 1)] = (d13 * d6);
/* 813 */             d13 = this.e[i4] / d6;
/* 814 */             d9 = d5 / d6;
/* 815 */             d5 = d9 * this.d[i4] - d13 * d4;
/* 816 */             this.d[(i4 + 1)] = (d8 + d13 * (d9 * d4 + d13 * this.d[i4]));
/*     */             
/*     */ 
/*     */ 
/* 820 */             for (int i5 = 0; i5 < this.n; i5++) {
/* 821 */               d8 = this.V[i5][(i4 + 1)];
/* 822 */               this.V[i5][(i4 + 1)] = (d13 * this.V[i5][i4] + d9 * d8);
/* 823 */               this.V[i5][i4] = (d9 * this.V[i5][i4] - d13 * d8);
/*     */             }
/*     */           }
/* 826 */           d5 = -d13 * d14 * d11 * d12 * this.e[j] / d7;
/* 827 */           this.e[j] = (d13 * d5);
/* 828 */           this.d[j] = (d9 * d5);
/*     */ 
/*     */ 
/*     */         }
/* 832 */         while (Math.abs(this.e[j]) > d3 * d2);
/*     */       }
/* 834 */       this.d[j] += d1;
/* 835 */       this.e[j] = 0.0D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 840 */     for (int k = 0; k < this.n - 1; k++) {
/* 841 */       m = k;
/* 842 */       d4 = this.d[k];
/* 843 */       for (int i1 = k + 1; i1 < this.n; i1++) {
/* 844 */         if (this.d[i1] < d4) {
/* 845 */           m = i1;
/* 846 */           d4 = this.d[i1];
/*     */         }
/*     */       }
/* 849 */       if (m != k) {
/* 850 */         this.d[m] = this.d[k];
/* 851 */         this.d[k] = d4;
/* 852 */         for (int i2 = 0; i2 < this.n; i2++) {
/* 853 */           d4 = this.V[i2][k];
/* 854 */           this.V[i2][k] = this.V[i2][m];
/* 855 */           this.V[i2][m] = d4;
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
/*     */ 
/*     */   private void tred2()
/*     */   {
/* 870 */     for (int i = 0; i < this.n; i++) {
/* 871 */       this.d[i] = this.V[(this.n - 1)][i];
/*     */     }
/*     */     
/*     */ 
/*     */     int i2;
/*     */     
/* 877 */     for (int j = this.n - 1; j > 0; j--)
/*     */     {
/*     */ 
/*     */ 
/* 881 */       double d1 = 0.0D;
/* 882 */       double d3 = 0.0D;
/* 883 */       for (i2 = 0; i2 < j; i2++)
/* 884 */         d1 += Math.abs(this.d[i2]);
/*     */       int i3;
/* 886 */       if (d1 == 0.0D) {
/* 887 */         this.e[j] = this.d[(j - 1)];
/* 888 */         for (i3 = 0; i3 < j; i3++) {
/* 889 */           this.d[i3] = this.V[(j - 1)][i3];
/* 890 */           this.V[j][i3] = 0.0D;
/* 891 */           this.V[i3][j] = 0.0D;
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 897 */         for (i3 = 0; i3 < j; i3++) {
/* 898 */           this.d[i3] /= d1;
/* 899 */           d3 += this.d[i3] * this.d[i3];
/*     */         }
/* 901 */         double d5 = this.d[(j - 1)];
/* 902 */         double d6 = Math.sqrt(d3);
/* 903 */         if (d5 > 0.0D) {
/* 904 */           d6 = -d6;
/*     */         }
/* 906 */         this.e[j] = (d1 * d6);
/* 907 */         d3 -= d5 * d6;
/* 908 */         this.d[(j - 1)] = (d5 - d6);
/* 909 */         for (int i6 = 0; i6 < j; i6++) {
/* 910 */           this.e[i6] = 0.0D;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 915 */         for (int i7 = 0; i7 < j; i7++) {
/* 916 */           d5 = this.d[i7];
/* 917 */           this.V[i7][j] = d5;
/* 918 */           d6 = this.e[i7] + this.V[i7][i7] * d5;
/* 919 */           for (i8 = i7 + 1; i8 <= j - 1; i8++) {
/* 920 */             d6 += this.V[i8][i7] * this.d[i8];
/* 921 */             this.e[i8] += this.V[i8][i7] * d5;
/*     */           }
/* 923 */           this.e[i7] = d6;
/*     */         }
/* 925 */         d5 = 0.0D;
/* 926 */         for (int i8 = 0; i8 < j; i8++) {
/* 927 */           this.e[i8] /= d3;
/* 928 */           d5 += this.e[i8] * this.d[i8];
/*     */         }
/* 930 */         double d7 = d5 / (d3 + d3);
/* 931 */         for (int i9 = 0; i9 < j; i9++) {
/* 932 */           this.e[i9] -= d7 * this.d[i9];
/*     */         }
/* 934 */         for (int i10 = 0; i10 < j; i10++) {
/* 935 */           d5 = this.d[i10];
/* 936 */           d6 = this.e[i10];
/* 937 */           for (int i11 = i10; i11 <= j - 1; i11++) {
/* 938 */             this.V[i11][i10] -= d5 * this.e[i11] + d6 * this.d[i11];
/*     */           }
/* 940 */           this.d[i10] = this.V[(j - 1)][i10];
/* 941 */           this.V[j][i10] = 0.0D;
/*     */         }
/*     */       }
/* 944 */       this.d[j] = d3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 949 */     for (int k = 0; k < this.n - 1; k++) {
/* 950 */       this.V[(this.n - 1)][k] = this.V[k][k];
/* 951 */       this.V[k][k] = 1.0D;
/* 952 */       double d2 = this.d[(k + 1)];
/* 953 */       if (d2 != 0.0D) {
/* 954 */         for (i1 = 0; i1 <= k; i1++) {
/* 955 */           this.d[i1] = (this.V[i1][(k + 1)] / d2);
/*     */         }
/* 957 */         for (i2 = 0; i2 <= k; i2++) {
/* 958 */           double d4 = 0.0D;
/* 959 */           for (int i4 = 0; i4 <= k; i4++) {
/* 960 */             d4 += this.V[i4][(k + 1)] * this.V[i4][i2];
/*     */           }
/* 962 */           for (int i5 = 0; i5 <= k; i5++) {
/* 963 */             this.V[i5][i2] -= d4 * this.d[i5];
/*     */           }
/*     */         }
/*     */       }
/* 967 */       for (int i1 = 0; i1 <= k; i1++) {
/* 968 */         this.V[i1][(k + 1)] = 0.0D;
/*     */       }
/*     */     }
/* 971 */     for (int m = 0; m < this.n; m++) {
/* 972 */       this.d[m] = this.V[(this.n - 1)][m];
/* 973 */       this.V[(this.n - 1)][m] = 0.0D;
/*     */     }
/* 975 */     this.V[(this.n - 1)][(this.n - 1)] = 1.0D;
/* 976 */     this.e[0] = 0.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/EigenvalueDecomposition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */