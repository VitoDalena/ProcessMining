/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.matrix.DoubleFactory2D;
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
/*     */ public class SingularValueDecomposition
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   private double[][] U;
/*     */   private double[][] V;
/*     */   private double[] s;
/*     */   private int m;
/*     */   private int n;
/*     */   
/*     */   public SingularValueDecomposition(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  53 */     Property.DEFAULT.checkRectangular(paramDoubleMatrix2D);
/*     */     
/*     */ 
/*     */ 
/*  57 */     double[][] arrayOfDouble = paramDoubleMatrix2D.toArray();
/*  58 */     this.m = paramDoubleMatrix2D.rows();
/*  59 */     this.n = paramDoubleMatrix2D.columns();
/*  60 */     int i = Math.min(this.m, this.n);
/*  61 */     this.s = new double[Math.min(this.m + 1, this.n)];
/*  62 */     this.U = new double[this.m][i];
/*  63 */     this.V = new double[this.n][this.n];
/*  64 */     double[] arrayOfDouble1 = new double[this.n];
/*  65 */     double[] arrayOfDouble2 = new double[this.m];
/*  66 */     int j = 1;
/*  67 */     int k = 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  72 */     int i1 = Math.min(this.m - 1, this.n);
/*  73 */     int i2 = Math.max(0, Math.min(this.n - 2, this.m));
/*  74 */     int i6; int i10; for (double d1 = 0; d1 < Math.max(i1, i2); d1++) {
/*  75 */       if (d1 < i1)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  80 */         this.s[d1] = 0.0D;
/*  81 */         for (i3 = d1; i3 < this.m; i3++) {
/*  82 */           this.s[d1] = Algebra.hypot(this.s[d1], arrayOfDouble[i3][d1]);
/*     */         }
/*  84 */         if (this.s[d1] != 0.0D) {
/*  85 */           if (arrayOfDouble[d1][d1] < 0.0D) {
/*  86 */             this.s[d1] = (-this.s[d1]);
/*     */           }
/*  88 */           for (int i4 = d1; i4 < this.m; i4++) {
/*  89 */             arrayOfDouble[i4][d1] /= this.s[d1];
/*     */           }
/*  91 */           arrayOfDouble[d1][d1] += 1.0D;
/*     */         }
/*  93 */         this.s[d1] = (-this.s[d1]); }
/*     */       double d2;
/*  95 */       int i7; for (i3 = d1 + 1; i3 < this.n; i3++) {
/*  96 */         if (((d1 < i1 ? 1 : 0) & (this.s[d1] != 0.0D ? 1 : 0)) != 0)
/*     */         {
/*     */ 
/*     */ 
/* 100 */           d2 = 0.0D;
/* 101 */           for (i6 = d1; i6 < this.m; i6++) {
/* 102 */             d2 += arrayOfDouble[i6][d1] * arrayOfDouble[i6][i3];
/*     */           }
/* 104 */           d2 = -d2 / arrayOfDouble[d1][d1];
/* 105 */           for (i7 = d1; i7 < this.m; i7++) {
/* 106 */             arrayOfDouble[i7][i3] += d2 * arrayOfDouble[i7][d1];
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 113 */         arrayOfDouble1[i3] = arrayOfDouble[d1][i3];
/*     */       }
/* 115 */       if ((j & (d1 < i1 ? 1 : 0)) != 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 120 */         for (d2 = d1; d2 < this.m; d2++) {
/* 121 */           this.U[d2][d1] = arrayOfDouble[d2][d1];
/*     */         }
/*     */       }
/* 124 */       if (d1 < i2)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 129 */         arrayOfDouble1[d1] = 0.0D;
/* 130 */         for (i5 = d1 + 1; i5 < this.n; i5++) {
/* 131 */           arrayOfDouble1[d1] = Algebra.hypot(arrayOfDouble1[d1], arrayOfDouble1[i5]);
/*     */         }
/* 133 */         if (arrayOfDouble1[d1] != 0.0D) {
/* 134 */           if (arrayOfDouble1[(d1 + 1)] < 0.0D) {
/* 135 */             arrayOfDouble1[d1] = (-arrayOfDouble1[d1]);
/*     */           }
/* 137 */           for (d3 = d1 + 1; d3 < this.n; d3++) {
/* 138 */             arrayOfDouble1[d3] /= arrayOfDouble1[d1];
/*     */           }
/* 140 */           arrayOfDouble1[(d1 + 1)] += 1.0D;
/*     */         }
/* 142 */         arrayOfDouble1[d1] = (-arrayOfDouble1[d1]);
/* 143 */         if (((d1 + 1 < this.m ? 1 : 0) & (arrayOfDouble1[d1] != 0.0D ? 1 : 0)) != 0)
/*     */         {
/*     */ 
/*     */ 
/* 147 */           for (d3 = d1 + 1; d3 < this.m; d3++) {
/* 148 */             arrayOfDouble2[d3] = 0.0D;
/*     */           }
/* 150 */           for (i6 = d1 + 1; i6 < this.n; i6++) {
/* 151 */             for (i7 = d1 + 1; i7 < this.m; i7++) {
/* 152 */               arrayOfDouble2[i7] += arrayOfDouble1[i6] * arrayOfDouble[i7][i6];
/*     */             }
/*     */           }
/* 155 */           for (i7 = d1 + 1; i7 < this.n; i7++) {
/* 156 */             double d6 = -arrayOfDouble1[i7] / arrayOfDouble1[(d1 + 1)];
/* 157 */             for (i10 = d1 + 1; i10 < this.m; i10++) {
/* 158 */               arrayOfDouble[i10][i7] += d6 * arrayOfDouble2[i10];
/*     */             }
/*     */           }
/*     */         }
/* 162 */         if (k != 0)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 167 */           for (d3 = d1 + 1; d3 < this.n; d3++) {
/* 168 */             this.V[d3][d1] = arrayOfDouble1[d3];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 176 */     int i3 = Math.min(this.n, this.m + 1);
/* 177 */     if (i1 < this.n) {
/* 178 */       this.s[i1] = arrayOfDouble[i1][i1];
/*     */     }
/* 180 */     if (this.m < i3) {
/* 181 */       this.s[(i3 - 1)] = 0.0D;
/*     */     }
/* 183 */     if (i2 + 1 < i3) {
/* 184 */       arrayOfDouble1[i2] = arrayOfDouble[i2][(i3 - 1)];
/*     */     }
/* 186 */     arrayOfDouble1[(i3 - 1)] = 0.0D;
/*     */     
/*     */     int i9;
/*     */     int i8;
/* 190 */     if (j != 0) {
/* 191 */       for (i5 = i1; i5 < i; i5++) {
/* 192 */         for (d3 = 0; d3 < this.m; d3++) {
/* 193 */           this.U[d3][i5] = 0.0D;
/*     */         }
/* 195 */         this.U[i5][i5] = 1.0D;
/*     */       }
/* 197 */       for (d3 = i1 - 1; d3 >= 0; d3--) {
/* 198 */         if (this.s[d3] != 0.0D) {
/* 199 */           for (i6 = d3 + 1; i6 < i; i6++) {
/* 200 */             d5 = 0.0D;
/* 201 */             for (i9 = d3; i9 < this.m; i9++) {
/* 202 */               d5 += this.U[i9][d3] * this.U[i9][i6];
/*     */             }
/* 204 */             d5 = -d5 / this.U[d3][d3];
/* 205 */             for (i10 = d3; i10 < this.m; i10++) {
/* 206 */               this.U[i10][i6] += d5 * this.U[i10][d3];
/*     */             }
/*     */           }
/* 209 */           for (double d5 = d3; d5 < this.m; d5++) {
/* 210 */             this.U[d5][d3] = (-this.U[d5][d3]);
/*     */           }
/* 212 */           this.U[d3][d3] = (1.0D + this.U[d3][d3]);
/* 213 */           for (i8 = 0; i8 < d3 - 1; i8++) {
/* 214 */             this.U[i8][d3] = 0.0D;
/*     */           }
/*     */         } else {
/* 217 */           for (i6 = 0; i6 < this.m; i6++) {
/* 218 */             this.U[i6][d3] = 0.0D;
/*     */           }
/* 220 */           this.U[d3][d3] = 1.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 227 */     if (k != 0) {
/* 228 */       for (i5 = this.n - 1; i5 >= 0; i5--) {
/* 229 */         if (((i5 < i2 ? 1 : 0) & (arrayOfDouble1[i5] != 0.0D ? 1 : 0)) != 0) {
/* 230 */           for (d3 = i5 + 1; d3 < i; d3++) {
/* 231 */             d4 = 0.0D;
/* 232 */             for (i8 = i5 + 1; i8 < this.n; i8++) {
/* 233 */               d4 += this.V[i8][i5] * this.V[i8][d3];
/*     */             }
/* 235 */             d4 = -d4 / this.V[(i5 + 1)][i5];
/* 236 */             for (i9 = i5 + 1; i9 < this.n; i9++) {
/* 237 */               this.V[i9][d3] += d4 * this.V[i9][i5];
/*     */             }
/*     */           }
/*     */         }
/* 241 */         for (d3 = 0; d3 < this.n; d3++) {
/* 242 */           this.V[d3][i5] = 0.0D;
/*     */         }
/* 244 */         this.V[i5][i5] = 1.0D;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 250 */     int i5 = i3 - 1;
/* 251 */     double d3 = 0;
/* 252 */     double d4 = Math.pow(2.0D, -52.0D);
/* 253 */     while (i3 > 0)
/*     */     {
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
/* 268 */       for (i8 = i3 - 2; i8 >= -1; i8--) {
/* 269 */         if (i8 == -1) {
/*     */           break;
/*     */         }
/* 272 */         if (Math.abs(arrayOfDouble1[i8]) <= d4 * (Math.abs(this.s[i8]) + Math.abs(this.s[(i8 + 1)]))) {
/* 273 */           arrayOfDouble1[i8] = 0.0D;
/* 274 */           break;
/*     */         }
/*     */       }
/* 277 */       if (i8 == i3 - 2) {
/* 278 */         i9 = 4;
/*     */       }
/*     */       else {
/* 281 */         for (i10 = i3 - 1; i10 >= i8; i10--) {
/* 282 */           if (i10 == i8) {
/*     */             break;
/*     */           }
/* 285 */           double d9 = (i10 != i3 ? Math.abs(arrayOfDouble1[i10]) : 0.0D) + (i10 != i8 + 1 ? Math.abs(arrayOfDouble1[(i10 - 1)]) : 0.0D);
/*     */           
/* 287 */           if (Math.abs(this.s[i10]) <= d4 * d9) {
/* 288 */             this.s[i10] = 0.0D;
/* 289 */             break;
/*     */           }
/*     */         }
/* 292 */         if (i10 == i8) {
/* 293 */           i9 = 3;
/* 294 */         } else if (i10 == i3 - 1) {
/* 295 */           i9 = 1;
/*     */         } else {
/* 297 */           i9 = 2;
/* 298 */           i8 = i10;
/*     */         }
/*     */       }
/* 301 */       i8++;
/*     */       double d7;
/*     */       int i12;
/*     */       double d11;
/* 305 */       double d13; double d15; int i14; switch (i9)
/*     */       {
/*     */ 
/*     */ 
/*     */       case 1: 
/* 310 */         d7 = arrayOfDouble1[(i3 - 2)];
/* 311 */         arrayOfDouble1[(i3 - 2)] = 0.0D;
/* 312 */         for (i12 = i3 - 2; i12 >= i8; i12--) {
/* 313 */           d11 = Algebra.hypot(this.s[i12], d7);
/* 314 */           d13 = this.s[i12] / d11;
/* 315 */           d15 = d7 / d11;
/* 316 */           this.s[i12] = d11;
/* 317 */           if (i12 != i8) {
/* 318 */             d7 = -d15 * arrayOfDouble1[(i12 - 1)];
/* 319 */             arrayOfDouble1[(i12 - 1)] = (d13 * arrayOfDouble1[(i12 - 1)]);
/*     */           }
/* 321 */           if (k != 0) {
/* 322 */             for (i14 = 0; i14 < this.n; i14++) {
/* 323 */               d11 = d13 * this.V[i14][i12] + d15 * this.V[i14][(i3 - 1)];
/* 324 */               this.V[i14][(i3 - 1)] = (-d15 * this.V[i14][i12] + d13 * this.V[i14][(i3 - 1)]);
/* 325 */               this.V[i14][i12] = d11;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 330 */         break;
/*     */       
/*     */ 
/*     */ 
/*     */       case 2: 
/* 335 */         d7 = arrayOfDouble1[(i8 - 1)];
/* 336 */         arrayOfDouble1[(i8 - 1)] = 0.0D;
/* 337 */         for (i12 = i8; i12 < i3; i12++) {
/* 338 */           d11 = Algebra.hypot(this.s[i12], d7);
/* 339 */           d13 = this.s[i12] / d11;
/* 340 */           d15 = d7 / d11;
/* 341 */           this.s[i12] = d11;
/* 342 */           d7 = -d15 * arrayOfDouble1[i12];
/* 343 */           arrayOfDouble1[i12] = (d13 * arrayOfDouble1[i12]);
/* 344 */           if (j != 0) {
/* 345 */             for (i14 = 0; i14 < this.m; i14++) {
/* 346 */               d11 = d13 * this.U[i14][i12] + d15 * this.U[i14][(i8 - 1)];
/* 347 */               this.U[i14][(i8 - 1)] = (-d15 * this.U[i14][i12] + d13 * this.U[i14][(i8 - 1)]);
/* 348 */               this.U[i14][i12] = d11;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 353 */         break;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       case 3: 
/* 361 */         d7 = Math.max(Math.max(Math.max(Math.max(Math.abs(this.s[(i3 - 1)]), Math.abs(this.s[(i3 - 2)])), Math.abs(arrayOfDouble1[(i3 - 2)])), Math.abs(this.s[i8])), Math.abs(arrayOfDouble1[i8]));
/*     */         
/*     */ 
/* 364 */         double d10 = this.s[(i3 - 1)] / d7;
/* 365 */         double d12 = this.s[(i3 - 2)] / d7;
/* 366 */         double d14 = arrayOfDouble1[(i3 - 2)] / d7;
/* 367 */         double d16 = this.s[i8] / d7;
/* 368 */         double d17 = arrayOfDouble1[i8] / d7;
/* 369 */         double d18 = ((d12 + d10) * (d12 - d10) + d14 * d14) / 2.0D;
/* 370 */         double d19 = d10 * d14 * (d10 * d14);
/* 371 */         double d20 = 0.0D;
/* 372 */         if (((d18 != 0.0D ? 1 : 0) | (d19 != 0.0D ? 1 : 0)) != 0) {
/* 373 */           d20 = Math.sqrt(d18 * d18 + d19);
/* 374 */           if (d18 < 0.0D) {
/* 375 */             d20 = -d20;
/*     */           }
/* 377 */           d20 = d19 / (d18 + d20);
/*     */         }
/* 379 */         double d21 = (d16 + d10) * (d16 - d10) + d20;
/* 380 */         double d22 = d16 * d17;
/*     */         
/*     */ 
/*     */ 
/* 384 */         for (int i15 = i8; i15 < i3 - 1; i15++) {
/* 385 */           double d23 = Algebra.hypot(d21, d22);
/* 386 */           double d24 = d21 / d23;
/* 387 */           double d25 = d22 / d23;
/* 388 */           if (i15 != i8) {
/* 389 */             arrayOfDouble1[(i15 - 1)] = d23;
/*     */           }
/* 391 */           d21 = d24 * this.s[i15] + d25 * arrayOfDouble1[i15];
/* 392 */           arrayOfDouble1[i15] = (d24 * arrayOfDouble1[i15] - d25 * this.s[i15]);
/* 393 */           d22 = d25 * this.s[(i15 + 1)];
/* 394 */           this.s[(i15 + 1)] = (d24 * this.s[(i15 + 1)]);
/* 395 */           int i16; if (k != 0) {
/* 396 */             for (i16 = 0; i16 < this.n; i16++) {
/* 397 */               d23 = d24 * this.V[i16][i15] + d25 * this.V[i16][(i15 + 1)];
/* 398 */               this.V[i16][(i15 + 1)] = (-d25 * this.V[i16][i15] + d24 * this.V[i16][(i15 + 1)]);
/* 399 */               this.V[i16][i15] = d23;
/*     */             }
/*     */           }
/* 402 */           d23 = Algebra.hypot(d21, d22);
/* 403 */           d24 = d21 / d23;
/* 404 */           d25 = d22 / d23;
/* 405 */           this.s[i15] = d23;
/* 406 */           d21 = d24 * arrayOfDouble1[i15] + d25 * this.s[(i15 + 1)];
/* 407 */           this.s[(i15 + 1)] = (-d25 * arrayOfDouble1[i15] + d24 * this.s[(i15 + 1)]);
/* 408 */           d22 = d25 * arrayOfDouble1[(i15 + 1)];
/* 409 */           arrayOfDouble1[(i15 + 1)] = (d24 * arrayOfDouble1[(i15 + 1)]);
/* 410 */           if ((j != 0) && (i15 < this.m - 1)) {
/* 411 */             for (i16 = 0; i16 < this.m; i16++) {
/* 412 */               d23 = d24 * this.U[i16][i15] + d25 * this.U[i16][(i15 + 1)];
/* 413 */               this.U[i16][(i15 + 1)] = (-d25 * this.U[i16][i15] + d24 * this.U[i16][(i15 + 1)]);
/* 414 */               this.U[i16][i15] = d23;
/*     */             }
/*     */           }
/*     */         }
/* 418 */         arrayOfDouble1[(i3 - 2)] = d21;
/* 419 */         d3 += 1;
/*     */         
/* 421 */         break;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       case 4: 
/* 429 */         if (this.s[i8] <= 0.0D) {
/* 430 */           this.s[i8] = (this.s[i8] < 0.0D ? -this.s[i8] : 0.0D);
/* 431 */           if (k != 0) {
/* 432 */             for (int i11 = 0; i11 <= i5; i11++) {
/* 433 */               this.V[i11][i8] = (-this.V[i11][i8]);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 440 */         while (i8 < i5) {
/* 441 */           if (this.s[i8] >= this.s[(i8 + 1)]) {
/*     */             break;
/*     */           }
/* 444 */           double d8 = this.s[i8];
/* 445 */           this.s[i8] = this.s[(i8 + 1)];
/* 446 */           this.s[(i8 + 1)] = d8;
/* 447 */           int i13; if ((k != 0) && (i8 < this.n - 1)) {
/* 448 */             for (i13 = 0; i13 < this.n; i13++) {
/* 449 */               d8 = this.V[i13][(i8 + 1)];this.V[i13][(i8 + 1)] = this.V[i13][i8];this.V[i13][i8] = d8;
/*     */             }
/*     */           }
/* 452 */           if ((j != 0) && (i8 < this.m - 1)) {
/* 453 */             for (i13 = 0; i13 < this.m; i13++) {
/* 454 */               d8 = this.U[i13][(i8 + 1)];this.U[i13][(i8 + 1)] = this.U[i13][i8];this.U[i13][i8] = d8;
/*     */             }
/*     */           }
/* 457 */           i8++;
/*     */         }
/* 459 */         d3 = 0;
/* 460 */         i3--;
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double cond()
/*     */   {
/* 470 */     return this.s[0] / this.s[(Math.min(this.m, this.n) - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getS()
/*     */   {
/* 477 */     double[][] arrayOfDouble = new double[this.n][this.n];
/* 478 */     for (int i = 0; i < this.n; i++) {
/* 479 */       for (int j = 0; j < this.n; j++) {
/* 480 */         arrayOfDouble[i][j] = 0.0D;
/*     */       }
/* 482 */       arrayOfDouble[i][i] = this.s[i];
/*     */     }
/* 484 */     return DoubleFactory2D.dense.make(arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double[] getSingularValues()
/*     */   {
/* 491 */     return this.s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getU()
/*     */   {
/* 499 */     return DoubleFactory2D.dense.make(this.U).viewPart(0, 0, this.m, Math.min(this.m + 1, this.n));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getV()
/*     */   {
/* 506 */     return DoubleFactory2D.dense.make(this.V);
/*     */   }
/*     */   
/*     */ 
/*     */   public double norm2()
/*     */   {
/* 512 */     return this.s[0];
/*     */   }
/*     */   
/*     */ 
/*     */   public int rank()
/*     */   {
/* 518 */     double d1 = Math.pow(2.0D, -52.0D);
/* 519 */     double d2 = Math.max(this.m, this.n) * this.s[0] * d1;
/* 520 */     int i = 0;
/* 521 */     for (int j = 0; j < this.s.length; j++) {
/* 522 */       if (this.s[j] > d2) {
/* 523 */         i++;
/*     */       }
/*     */     }
/* 526 */     return i;
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
/* 538 */     StringBuffer localStringBuffer = new StringBuffer();
/* 539 */     String str = "Illegal operation or error: ";
/*     */     
/* 541 */     localStringBuffer.append("---------------------------------------------------------------------\n");
/* 542 */     localStringBuffer.append("SingularValueDecomposition(A) --> cond(A), rank(A), norm2(A), U, S, V\n");
/* 543 */     localStringBuffer.append("---------------------------------------------------------------------\n");
/*     */     
/* 545 */     localStringBuffer.append("cond = ");
/* 546 */     try { localStringBuffer.append(String.valueOf(cond()));
/* 547 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localStringBuffer.append(str + localIllegalArgumentException1.getMessage());
/*     */     }
/* 549 */     localStringBuffer.append("\nrank = ");
/* 550 */     try { localStringBuffer.append(String.valueOf(rank()));
/* 551 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localStringBuffer.append(str + localIllegalArgumentException2.getMessage());
/*     */     }
/* 553 */     localStringBuffer.append("\nnorm2 = ");
/* 554 */     try { localStringBuffer.append(String.valueOf(norm2()));
/* 555 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localStringBuffer.append(str + localIllegalArgumentException3.getMessage());
/*     */     }
/* 557 */     localStringBuffer.append("\n\nU = ");
/* 558 */     try { localStringBuffer.append(String.valueOf(getU()));
/* 559 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localStringBuffer.append(str + localIllegalArgumentException4.getMessage());
/*     */     }
/* 561 */     localStringBuffer.append("\n\nS = ");
/* 562 */     try { localStringBuffer.append(String.valueOf(getS()));
/* 563 */     } catch (IllegalArgumentException localIllegalArgumentException5) { localStringBuffer.append(str + localIllegalArgumentException5.getMessage());
/*     */     }
/* 565 */     localStringBuffer.append("\n\nV = ");
/* 566 */     try { localStringBuffer.append(String.valueOf(getV()));
/* 567 */     } catch (IllegalArgumentException localIllegalArgumentException6) { localStringBuffer.append(str + localIllegalArgumentException6.getMessage());
/*     */     }
/* 569 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/SingularValueDecomposition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */