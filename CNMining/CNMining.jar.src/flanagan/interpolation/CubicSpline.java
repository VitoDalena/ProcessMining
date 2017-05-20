/*     */ package flanagan.interpolation;
/*     */ 
/*     */ import flanagan.math.Fmath;
/*     */ import java.io.PrintStream;
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
/*     */ public class CubicSpline
/*     */ {
/*  51 */   private int nPoints = 0;
/*  52 */   private int nPointsOriginal = 0;
/*  53 */   private double[] y = null;
/*  54 */   private double[] x = null;
/*     */   private int[] newAndOldIndices;
/*  56 */   private double xMin = NaN.0D;
/*  57 */   private double xMax = NaN.0D;
/*  58 */   private double range = NaN.0D;
/*  59 */   private double[] d2ydx2 = null;
/*  60 */   private double yp1 = NaN.0D;
/*     */   
/*  62 */   private double ypn = NaN.0D;
/*     */   
/*  64 */   private boolean derivCalculated = false;
/*  65 */   private String subMatrixIndices = " ";
/*     */   
/*  67 */   private boolean checkPoints = false;
/*  68 */   private boolean averageIdenticalAbscissae = false;
/*     */   
/*  70 */   private static double potentialRoundingError = 5.0E-15D;
/*  71 */   private static boolean roundingCheck = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public CubicSpline(double[] x, double[] y)
/*     */   {
/*  77 */     this.nPoints = x.length;
/*  78 */     this.nPointsOriginal = this.nPoints;
/*  79 */     if (this.nPoints != y.length) throw new IllegalArgumentException("Arrays x and y are of different length " + this.nPoints + " " + y.length);
/*  80 */     if (this.nPoints < 3) throw new IllegalArgumentException("A minimum of three data points is needed");
/*  81 */     this.x = new double[this.nPoints];
/*  82 */     this.y = new double[this.nPoints];
/*  83 */     this.d2ydx2 = new double[this.nPoints];
/*  84 */     for (int i = 0; i < this.nPoints; i++) {
/*  85 */       this.x[i] = x[i];
/*  86 */       this.y[i] = y[i];
/*     */     }
/*  88 */     orderPoints();
/*     */   }
/*     */   
/*     */ 
/*     */   public CubicSpline(int nPoints)
/*     */   {
/*  94 */     this.nPoints = nPoints;
/*  95 */     this.nPointsOriginal = this.nPoints;
/*  96 */     if (this.nPoints < 3) throw new IllegalArgumentException("A minimum of three data points is needed");
/*  97 */     this.x = new double[nPoints];
/*  98 */     this.y = new double[nPoints];
/*  99 */     this.d2ydx2 = new double[nPoints];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void noRoundingErrorCheck()
/*     */   {
/* 107 */     roundingCheck = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void potentialRoundingError(double potentialRoundingError)
/*     */   {
/* 115 */     potentialRoundingError = potentialRoundingError;
/*     */   }
/*     */   
/*     */   public void resetData(double[] x, double[] y)
/*     */   {
/* 120 */     this.nPoints = this.nPointsOriginal;
/* 121 */     if (x.length != y.length) throw new IllegalArgumentException("Arrays x and y are of different length");
/* 122 */     if (this.nPoints != x.length) { throw new IllegalArgumentException("Original array length not matched by new array length");
/*     */     }
/* 124 */     for (int i = 0; i < this.nPoints; i++) {
/* 125 */       this.x[i] = x[i];
/* 126 */       this.y[i] = y[i];
/*     */     }
/* 128 */     orderPoints();
/*     */   }
/*     */   
/*     */   public void setSubMatrix(String subMatrixIndices)
/*     */   {
/* 133 */     this.subMatrixIndices = subMatrixIndices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void averageIdenticalAbscissae()
/*     */   {
/* 140 */     this.averageIdenticalAbscissae = true;
/*     */   }
/*     */   
/*     */   public void orderPoints()
/*     */   {
/* 145 */     double[] dummy = new double[this.nPoints];
/* 146 */     this.newAndOldIndices = new int[this.nPoints];
/*     */     
/* 148 */     Fmath.selectionSort(this.x, dummy, this.newAndOldIndices);
/*     */     
/* 150 */     Fmath.selectionSort(this.x, this.y, this.x, this.y);
/*     */     
/*     */ 
/* 153 */     this.xMin = Fmath.minimum(this.x);
/* 154 */     this.xMax = Fmath.maximum(this.x);
/* 155 */     this.range = (this.xMax - this.xMin);
/*     */   }
/*     */   
/*     */   public double getXmax()
/*     */   {
/* 160 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public double getXmin()
/*     */   {
/* 165 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public double[] getLimits()
/*     */   {
/* 170 */     double[] limits = { this.xMin, this.xMax };
/* 171 */     return limits;
/*     */   }
/*     */   
/*     */   public void displayLimits()
/*     */   {
/* 176 */     System.out.println("\nThe limits of the abscissae (x-values) are " + this.xMin + " and " + this.xMax + "\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void checkForIdenticalPoints()
/*     */   {
/* 183 */     int nP = this.nPoints;
/* 184 */     boolean test1 = true;
/* 185 */     int ii = 0;
/* 186 */     while (test1) {
/* 187 */       boolean test2 = true;
/* 188 */       int jj = ii + 1;
/* 189 */       while (test2) {
/* 190 */         if (this.x[ii] == this.x[jj]) {
/* 191 */           if (this.y[ii] == this.y[jj]) {
/* 192 */             System.out.print(this.subMatrixIndices + "CubicSpline: Two identical points, " + this.x[ii] + ", " + this.y[ii]);
/* 193 */             System.out.println(", in data array at indices " + this.newAndOldIndices[ii] + " and " + this.newAndOldIndices[jj] + ", latter point removed");
/*     */             
/* 195 */             for (int i = jj; i < nP - 1; i++) {
/* 196 */               this.x[i] = this.x[(i + 1)];
/* 197 */               this.y[i] = this.y[(i + 1)];
/* 198 */               this.newAndOldIndices[(i - 1)] = this.newAndOldIndices[i];
/*     */             }
/* 200 */             nP--;
/* 201 */             for (int i = nP; i < this.nPoints; i++) {
/* 202 */               this.x[i] = NaN.0D;
/* 203 */               this.y[i] = NaN.0D;
/* 204 */               this.newAndOldIndices[(i - 1)] = 64536;
/*     */             }
/*     */             
/*     */           }
/* 208 */           else if (this.averageIdenticalAbscissae == true) {
/* 209 */             System.out.print(this.subMatrixIndices + "CubicSpline: Two identical points on the absicca (x-axis) with different ordinate (y-axis) values, " + this.x[ii] + ": " + this.y[ii] + ", " + this.y[jj]);
/* 210 */             System.out.println(", average of the ordinates taken");
/* 211 */             this.y[ii] = ((this.y[ii] + this.y[jj]) / 2.0D);
/* 212 */             for (int i = jj; i < nP - 1; i++) {
/* 213 */               this.x[i] = this.x[(i + 1)];
/* 214 */               this.y[i] = this.y[(i + 1)];
/* 215 */               this.newAndOldIndices[(i - 1)] = this.newAndOldIndices[i];
/*     */             }
/* 217 */             nP--;
/* 218 */             for (int i = nP; i < this.nPoints; i++) {
/* 219 */               this.x[i] = NaN.0D;
/* 220 */               this.y[i] = NaN.0D;
/* 221 */               this.newAndOldIndices[(i - 1)] = 64536;
/*     */             }
/*     */           }
/*     */           else {
/* 225 */             double sepn = this.range * 5.0E-4D;
/* 226 */             System.out.print(this.subMatrixIndices + "CubicSpline: Two identical points on the absicca (x-axis) with different ordinate (y-axis) values, " + this.x[ii] + ": " + this.y[ii] + ", " + this.y[jj]);
/* 227 */             boolean check = false;
/* 228 */             if (ii == 0) {
/* 229 */               if (this.x[2] - this.x[1] <= sepn) sepn = (this.x[2] - this.x[1]) / 2.0D;
/* 230 */               if (this.y[0] > this.y[1]) {
/* 231 */                 if (this.y[1] > this.y[2]) {
/* 232 */                   check = stay(ii, jj, sepn);
/*     */                 }
/*     */                 else {
/* 235 */                   check = swap(ii, jj, sepn);
/*     */                 }
/*     */                 
/*     */               }
/* 239 */               else if (this.y[2] <= this.y[1]) {
/* 240 */                 check = swap(ii, jj, sepn);
/*     */               }
/*     */               else {
/* 243 */                 check = stay(ii, jj, sepn);
/*     */               }
/*     */             }
/*     */             
/* 247 */             if (jj == nP - 1) {
/* 248 */               if (this.x[(nP - 2)] - this.x[(nP - 3)] <= sepn) sepn = (this.x[(nP - 2)] - this.x[(nP - 3)]) / 2.0D;
/* 249 */               if (this.y[ii] <= this.y[jj]) {
/* 250 */                 if (this.y[(ii - 1)] <= this.y[ii]) {
/* 251 */                   check = stay(ii, jj, sepn);
/*     */                 }
/*     */                 else {
/* 254 */                   check = swap(ii, jj, sepn);
/*     */                 }
/*     */                 
/*     */               }
/* 258 */               else if (this.y[(ii - 1)] <= this.y[ii]) {
/* 259 */                 check = swap(ii, jj, sepn);
/*     */               }
/*     */               else {
/* 262 */                 check = stay(ii, jj, sepn);
/*     */               }
/*     */             }
/*     */             
/* 266 */             if ((ii != 0) && (jj != nP - 1)) {
/* 267 */               if (this.x[ii] - this.x[(ii - 1)] <= sepn) sepn = (this.x[ii] - this.x[(ii - 1)]) / 2.0D;
/* 268 */               if (this.x[(jj + 1)] - this.x[jj] <= sepn) sepn = (this.x[(jj + 1)] - this.x[jj]) / 2.0D;
/* 269 */               if (this.y[ii] > this.y[(ii - 1)]) {
/* 270 */                 if (this.y[jj] > this.y[ii]) {
/* 271 */                   if (this.y[jj] > this.y[(jj + 1)]) {
/* 272 */                     if (this.y[(ii - 1)] <= this.y[(jj + 1)]) {
/* 273 */                       check = stay(ii, jj, sepn);
/*     */                     }
/*     */                     else {
/* 276 */                       check = swap(ii, jj, sepn);
/*     */                     }
/*     */                   }
/*     */                   else {
/* 280 */                     check = stay(ii, jj, sepn);
/*     */                   }
/*     */                   
/*     */                 }
/* 284 */                 else if (this.y[(jj + 1)] > this.y[jj]) {
/* 285 */                   if ((this.y[(jj + 1)] > this.y[(ii - 1)]) && (this.y[(jj + 1)] > this.y[(ii - 1)])) {
/* 286 */                     check = stay(ii, jj, sepn);
/*     */                   }
/*     */                 }
/*     */                 else {
/* 290 */                   check = swap(ii, jj, sepn);
/*     */                 }
/*     */                 
/*     */ 
/*     */               }
/* 295 */               else if (this.y[jj] > this.y[ii]) {
/* 296 */                 if (this.y[(jj + 1)] > this.y[jj]) {
/* 297 */                   check = stay(ii, jj, sepn);
/*     */                 }
/*     */                 
/*     */               }
/* 301 */               else if (this.y[(jj + 1)] > this.y[(ii - 1)]) {
/* 302 */                 check = stay(ii, jj, sepn);
/*     */               }
/*     */               else {
/* 305 */                 check = swap(ii, jj, sepn);
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 311 */             if (!check) {
/* 312 */               check = stay(ii, jj, sepn);
/*     */             }
/* 314 */             System.out.println(", the two abscissae have been separated by a distance " + sepn);
/* 315 */             jj++;
/*     */           }
/*     */           
/* 318 */           if (nP - 1 == ii) test2 = false;
/*     */         }
/*     */         else {
/* 321 */           jj++;
/*     */         }
/* 323 */         if (jj >= nP) test2 = false;
/*     */       }
/* 325 */       ii++;
/* 326 */       if (ii >= nP - 1) test1 = false;
/*     */     }
/* 328 */     this.nPoints = nP;
/* 329 */     if (this.nPoints < 3) { throw new IllegalArgumentException("Removal of duplicate points has reduced the number of points to less than the required minimum of three data points");
/*     */     }
/* 331 */     this.checkPoints = true;
/*     */   }
/*     */   
/*     */   private boolean swap(int ii, int jj, double sepn)
/*     */   {
/* 336 */     this.x[ii] += sepn;
/* 337 */     this.x[jj] -= sepn;
/* 338 */     double hold = this.x[ii];
/* 339 */     this.x[ii] = this.x[jj];
/* 340 */     this.x[jj] = hold;
/* 341 */     hold = this.y[ii];
/* 342 */     this.y[ii] = this.y[jj];
/* 343 */     this.y[jj] = hold;
/* 344 */     return true;
/*     */   }
/*     */   
/*     */   private boolean stay(int ii, int jj, double sepn)
/*     */   {
/* 349 */     this.x[ii] -= sepn;
/* 350 */     this.x[jj] += sepn;
/* 351 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public static CubicSpline zero(int n)
/*     */   {
/* 357 */     if (n < 3) throw new IllegalArgumentException("A minimum of three data points is needed");
/* 358 */     CubicSpline aa = new CubicSpline(n);
/* 359 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public static CubicSpline[] oneDarray(int n, int m)
/*     */   {
/* 365 */     if (m < 3) throw new IllegalArgumentException("A minimum of three data points is needed");
/* 366 */     CubicSpline[] a = new CubicSpline[n];
/* 367 */     for (int i = 0; i < n; i++) {
/* 368 */       a[i] = zero(m);
/*     */     }
/* 370 */     return a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDerivLimits(double yp1, double ypn)
/*     */   {
/* 377 */     this.yp1 = yp1;
/* 378 */     this.ypn = ypn;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDerivLimits()
/*     */   {
/* 384 */     this.yp1 = NaN.0D;
/* 385 */     this.ypn = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDeriv(double yp1, double ypn)
/*     */   {
/* 393 */     this.yp1 = yp1;
/* 394 */     this.ypn = ypn;
/* 395 */     this.derivCalculated = false;
/*     */   }
/*     */   
/*     */   public double[] getDeriv()
/*     */   {
/* 400 */     if (!this.derivCalculated) calcDeriv();
/* 401 */     return this.d2ydx2;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDeriv(double[] deriv)
/*     */   {
/* 407 */     this.d2ydx2 = deriv;
/* 408 */     this.derivCalculated = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void calcDeriv()
/*     */   {
/* 415 */     double p = 0.0D;double qn = 0.0D;double sig = 0.0D;double un = 0.0D;
/* 416 */     double[] u = new double[this.nPoints];
/*     */     
/* 418 */     if (Double.isNaN(this.yp1)) {
/* 419 */       this.d2ydx2[0] = (u[0] = 0.0D);
/*     */     }
/*     */     else {
/* 422 */       this.d2ydx2[0] = -0.5D;
/* 423 */       u[0] = (3.0D / (this.x[1] - this.x[0]) * ((this.y[1] - this.y[0]) / (this.x[1] - this.x[0]) - this.yp1));
/*     */     }
/*     */     
/* 426 */     for (int i = 1; i <= this.nPoints - 2; i++) {
/* 427 */       sig = (this.x[i] - this.x[(i - 1)]) / (this.x[(i + 1)] - this.x[(i - 1)]);
/* 428 */       p = sig * this.d2ydx2[(i - 1)] + 2.0D;
/* 429 */       this.d2ydx2[i] = ((sig - 1.0D) / p);
/* 430 */       u[i] = ((this.y[(i + 1)] - this.y[i]) / (this.x[(i + 1)] - this.x[i]) - (this.y[i] - this.y[(i - 1)]) / (this.x[i] - this.x[(i - 1)]));
/* 431 */       u[i] = ((6.0D * u[i] / (this.x[(i + 1)] - this.x[(i - 1)]) - sig * u[(i - 1)]) / p);
/*     */     }
/*     */     
/* 434 */     if (Double.isNaN(this.ypn)) {
/* 435 */       qn = un = 0.0D;
/*     */     }
/*     */     else {
/* 438 */       qn = 0.5D;
/* 439 */       un = 3.0D / (this.x[(this.nPoints - 1)] - this.x[(this.nPoints - 2)]) * (this.ypn - (this.y[(this.nPoints - 1)] - this.y[(this.nPoints - 2)]) / (this.x[(this.nPoints - 1)] - this.x[(this.nPoints - 2)]));
/*     */     }
/*     */     
/* 442 */     this.d2ydx2[(this.nPoints - 1)] = ((un - qn * u[(this.nPoints - 2)]) / (qn * this.d2ydx2[(this.nPoints - 2)] + 1.0D));
/* 443 */     for (int k = this.nPoints - 2; k >= 0; k--) {
/* 444 */       this.d2ydx2[k] = (this.d2ydx2[k] * this.d2ydx2[(k + 1)] + u[k]);
/*     */     }
/* 446 */     this.derivCalculated = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double interpolate(double xx)
/*     */   {
/* 456 */     if (!this.checkPoints) { checkForIdenticalPoints();
/*     */     }
/* 458 */     if (xx < this.x[0])
/*     */     {
/* 460 */       if ((roundingCheck) && (Math.abs(this.x[0] - xx) <= Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.x[0])))) * potentialRoundingError)) {
/* 461 */         xx = this.x[0];
/*     */       }
/*     */       else {
/* 464 */         throw new IllegalArgumentException("x (" + xx + ") is outside the range of data points (" + this.x[0] + " to " + this.x[(this.nPoints - 1)] + ")");
/*     */       }
/*     */     }
/* 467 */     if (xx > this.x[(this.nPoints - 1)]) {
/* 468 */       if ((roundingCheck) && (Math.abs(xx - this.x[(this.nPoints - 1)]) <= Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.x[(this.nPoints - 1)])))) * potentialRoundingError)) {
/* 469 */         xx = this.x[(this.nPoints - 1)];
/*     */       }
/*     */       else {
/* 472 */         throw new IllegalArgumentException("x (" + xx + ") is outside the range of data points (" + this.x[0] + " to " + this.x[(this.nPoints - 1)] + ")");
/*     */       }
/*     */     }
/*     */     
/* 476 */     if (!this.derivCalculated) { calcDeriv();
/*     */     }
/* 478 */     double h = 0.0D;double b = 0.0D;double a = 0.0D;double yy = 0.0D;
/* 479 */     int k = 0;
/* 480 */     int klo = 0;
/* 481 */     int khi = this.nPoints - 1;
/* 482 */     while (khi - klo > 1) {
/* 483 */       k = khi + klo >> 1;
/* 484 */       if (this.x[k] > xx) {
/* 485 */         khi = k;
/*     */       }
/*     */       else {
/* 488 */         klo = k;
/*     */       }
/*     */     }
/* 491 */     h = this.x[khi] - this.x[klo];
/*     */     
/* 493 */     if (h == 0.0D) {
/* 494 */       throw new IllegalArgumentException("Two values of x are identical: point " + klo + " (" + this.x[klo] + ") and point " + khi + " (" + this.x[khi] + ")");
/*     */     }
/*     */     
/* 497 */     a = (this.x[khi] - xx) / h;
/* 498 */     b = (xx - this.x[klo]) / h;
/* 499 */     yy = a * this.y[klo] + b * this.y[khi] + ((a * a * a - a) * this.d2ydx2[klo] + (b * b * b - b) * this.d2ydx2[khi]) * (h * h) / 6.0D;
/*     */     
/* 501 */     return yy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double interpolate(double xx, double[] x, double[] y, double[] deriv)
/*     */   {
/* 508 */     if ((x.length != y.length) || (x.length != deriv.length) || (y.length != deriv.length)) {
/* 509 */       throw new IllegalArgumentException("array lengths are not all equal");
/*     */     }
/* 511 */     int n = x.length;
/* 512 */     double h = 0.0D;double b = 0.0D;double a = 0.0D;double yy = 0.0D;
/*     */     
/* 514 */     int k = 0;
/* 515 */     int klo = 0;
/* 516 */     int khi = n - 1;
/* 517 */     while (khi - klo > 1) {
/* 518 */       k = khi + klo >> 1;
/* 519 */       if (x[k] > xx) {
/* 520 */         khi = k;
/*     */       }
/*     */       else {
/* 523 */         klo = k;
/*     */       }
/*     */     }
/* 526 */     h = x[khi] - x[klo];
/*     */     
/* 528 */     if (h == 0.0D) {
/* 529 */       throw new IllegalArgumentException("Two values of x are identical");
/*     */     }
/*     */     
/* 532 */     a = (x[khi] - xx) / h;
/* 533 */     b = (xx - x[klo]) / h;
/* 534 */     yy = a * y[klo] + b * y[khi] + ((a * a * a - a) * deriv[klo] + (b * b * b - b) * deriv[khi]) * (h * h) / 6.0D;
/*     */     
/* 536 */     return yy;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/interpolation/CubicSpline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */