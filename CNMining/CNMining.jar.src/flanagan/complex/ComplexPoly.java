/*     */ package flanagan.complex;
/*     */ 
/*     */ import flanagan.io.FileOutput;
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
/*     */ public class ComplexPoly
/*     */ {
/*  45 */   private int deg = 0;
/*  46 */   private int degwz = 0;
/*     */   private Complex[] coeff;
/*     */   private Complex[] coeffwz;
/*     */   
/*     */   public ComplexPoly(int n)
/*     */   {
/*  52 */     this.deg = n;
/*  53 */     this.coeff = Complex.oneDarray(n + 1);
/*     */   }
/*     */   
/*     */   public ComplexPoly(Complex[] aa)
/*     */   {
/*  58 */     this.deg = (aa.length - 1);
/*  59 */     this.coeff = Complex.oneDarray(this.deg + 1);
/*  60 */     for (int i = 0; i <= this.deg; i++) {
/*  61 */       this.coeff[i] = Complex.copy(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public ComplexPoly(double[] aa)
/*     */   {
/*  67 */     this.deg = (aa.length - 1);
/*  68 */     this.coeff = Complex.oneDarray(this.deg + 1);
/*  69 */     for (int i = 0; i <= this.deg; i++) {
/*  70 */       this.coeff[i].reset(aa[i], 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ComplexPoly(Complex aa)
/*     */   {
/*  78 */     this.deg = 0;
/*  79 */     this.coeff = Complex.oneDarray(1);
/*  80 */     this.coeff[0] = Complex.copy(aa);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ComplexPoly(double aa)
/*     */   {
/*  87 */     this.deg = 0;
/*  88 */     this.coeff = Complex.oneDarray(1);
/*  89 */     this.coeff[0].reset(aa, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(Complex aa, Complex bb)
/*     */   {
/*  95 */     this.deg = 1;
/*  96 */     this.coeff = Complex.oneDarray(2);
/*  97 */     this.coeff[0] = Complex.copy(aa);
/*  98 */     this.coeff[1] = Complex.copy(bb);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(double aa, double bb)
/*     */   {
/* 104 */     this.deg = 1;
/* 105 */     this.coeff = Complex.oneDarray(2);
/* 106 */     this.coeff[0].reset(aa, 0.0D);
/* 107 */     this.coeff[1].reset(bb, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(Complex aa, Complex bb, Complex cc)
/*     */   {
/* 113 */     this.deg = 2;
/* 114 */     this.coeff = Complex.oneDarray(3);
/* 115 */     this.coeff[0] = Complex.copy(aa);
/* 116 */     this.coeff[1] = Complex.copy(bb);
/* 117 */     this.coeff[2] = Complex.copy(cc);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(double aa, double bb, double cc)
/*     */   {
/* 123 */     this.deg = 2;
/* 124 */     this.coeff = Complex.oneDarray(3);
/* 125 */     this.coeff[0].reset(aa, 0.0D);
/* 126 */     this.coeff[1].reset(bb, 0.0D);
/* 127 */     this.coeff[2].reset(cc, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(Complex aa, Complex bb, Complex cc, Complex dd)
/*     */   {
/* 133 */     this.deg = 3;
/* 134 */     this.coeff = Complex.oneDarray(4);
/* 135 */     this.coeff[0] = Complex.copy(aa);
/* 136 */     this.coeff[1] = Complex.copy(bb);
/* 137 */     this.coeff[2] = Complex.copy(cc);
/* 138 */     this.coeff[3] = Complex.copy(dd);
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly(double aa, double bb, double cc, double dd)
/*     */   {
/* 144 */     this.deg = 3;
/* 145 */     this.coeff = Complex.oneDarray(4);
/* 146 */     this.coeff[0].reset(aa, 0.0D);
/* 147 */     this.coeff[1].reset(bb, 0.0D);
/* 148 */     this.coeff[2].reset(cc, 0.0D);
/* 149 */     this.coeff[3].reset(dd, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ComplexPoly rootsToPoly(Complex[] roots)
/*     */   {
/* 156 */     int pdeg = roots.length;
/*     */     
/* 158 */     Complex[] rootCoeff = Complex.oneDarray(2);
/* 159 */     rootCoeff[0] = roots[0].times(Complex.minusOne());
/* 160 */     rootCoeff[1] = Complex.plusOne();
/* 161 */     ComplexPoly rPoly = new ComplexPoly(rootCoeff);
/* 162 */     for (int i = 1; i < pdeg; i++) {
/* 163 */       rootCoeff[0] = roots[i].times(Complex.minusOne());
/* 164 */       ComplexPoly cRoot = new ComplexPoly(rootCoeff);
/* 165 */       rPoly = rPoly.times(cRoot);
/*     */     }
/* 167 */     return rPoly;
/*     */   }
/*     */   
/*     */   public void resetPoly(Complex[] aa)
/*     */   {
/* 172 */     if (this.deg + 1 != aa.length) throw new IllegalArgumentException("array lengths do not match");
/* 173 */     for (int i = 0; i < this.deg; i++) {
/* 174 */       this.coeff[i] = Complex.copy(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void resetCoeff(int i, Complex aa)
/*     */   {
/* 180 */     this.coeff[i] = Complex.copy(aa);
/*     */   }
/*     */   
/*     */   public ComplexPoly copy()
/*     */   {
/* 185 */     if (this == null) {
/* 186 */       return null;
/*     */     }
/*     */     
/* 189 */     ComplexPoly aa = new ComplexPoly(this.deg);
/* 190 */     for (int i = 0; i <= this.deg; i++) {
/* 191 */       aa.coeff[i] = Complex.copy(this.coeff[i]);
/*     */     }
/* 193 */     aa.deg = this.deg;
/* 194 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ComplexPoly copy(ComplexPoly bb)
/*     */   {
/* 200 */     if (bb == null) {
/* 201 */       return null;
/*     */     }
/*     */     
/* 204 */     ComplexPoly aa = new ComplexPoly(bb.deg);
/* 205 */     for (int i = 0; i <= bb.deg; i++) {
/* 206 */       aa.coeff[i] = Complex.copy(bb.coeff[i]);
/*     */     }
/* 208 */     aa.deg = bb.deg;
/* 209 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 215 */     if (this == null) {
/* 216 */       return null;
/*     */     }
/*     */     
/* 219 */     ComplexPoly aa = new ComplexPoly(this.deg);
/* 220 */     for (int i = 0; i <= this.deg; i++) {
/* 221 */       aa.coeff[i] = Complex.copy(this.coeff[i]);
/*     */     }
/* 223 */     aa.deg = this.deg;
/* 224 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public Complex[] polyNomCopy()
/*     */   {
/* 230 */     Complex[] aa = Complex.oneDarray(this.deg + 1);
/* 231 */     for (int i = 0; i <= this.deg; i++) {
/* 232 */       aa[i] = Complex.copy(this.coeff[i]);
/*     */     }
/* 234 */     return aa;
/*     */   }
/*     */   
/*     */   public Complex[] polyNomReference()
/*     */   {
/* 239 */     return this.coeff;
/*     */   }
/*     */   
/*     */   public Complex[] polyNomPointer() {
/* 243 */     return this.coeff;
/*     */   }
/*     */   
/*     */   public Complex coeffCopy(int i)
/*     */   {
/* 248 */     return Complex.copy(this.coeff[i]);
/*     */   }
/*     */   
/*     */   public Complex coeffReference(int i)
/*     */   {
/* 253 */     return this.coeff[i];
/*     */   }
/*     */   
/*     */   public Complex coeffPointer(int i)
/*     */   {
/* 258 */     return this.coeff[i];
/*     */   }
/*     */   
/*     */   public int getDeg()
/*     */   {
/* 263 */     return this.deg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setj() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void seti() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 278 */     String ss = "";
/* 279 */     ss = ss + coeffCopy(0).toString();
/* 280 */     if (this.deg > 0) ss = ss + " + (" + coeffCopy(1).toString() + ").x";
/* 281 */     for (int i = 2; i <= this.deg; i++) {
/* 282 */       ss = ss + " + (" + coeffCopy(i).toString() + ").x^" + i;
/*     */     }
/* 284 */     return ss;
/*     */   }
/*     */   
/*     */   public void print()
/*     */   {
/* 289 */     System.out.print(toString());
/*     */   }
/*     */   
/*     */   public void println()
/*     */   {
/* 294 */     System.out.println(toString());
/*     */   }
/*     */   
/*     */   public void printToText(String title)
/*     */   {
/* 299 */     title = title + ".txt";
/* 300 */     FileOutput fout = new FileOutput(title, 'n');
/*     */     
/* 302 */     fout.println("Output File for a ComplexPoly");
/* 303 */     fout.dateAndTimeln();
/* 304 */     fout.println();
/* 305 */     fout.print("Polynomial degree is ");
/* 306 */     fout.println(this.deg);
/* 307 */     fout.println();
/* 308 */     fout.println("The coefficients are ");
/*     */     
/* 310 */     for (int i = 0; i <= this.deg; i++) {
/* 311 */       fout.println(this.coeff[i]);
/*     */     }
/* 313 */     fout.println();
/* 314 */     fout.println("End of file.");
/* 315 */     fout.close();
/*     */   }
/*     */   
/*     */   public void printToText()
/*     */   {
/* 320 */     String title = "ComplexPolyOut";
/* 321 */     printToText(title);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(ComplexPoly cp)
/*     */   {
/* 327 */     return isEqual(cp);
/*     */   }
/*     */   
/*     */   public boolean isEqual(ComplexPoly cp) {
/* 331 */     boolean ret = false;
/* 332 */     int nDegThis = getDeg();
/* 333 */     int nDegCp = cp.getDeg();
/* 334 */     if (nDegThis == nDegCp) {
/* 335 */       boolean test = true;
/* 336 */       int i = 0;
/* 337 */       while (test) {
/* 338 */         if (!this.coeff[i].isEqual(cp.coeffReference(i))) {
/* 339 */           test = false;
/*     */         }
/*     */         else {
/* 342 */           i++;
/* 343 */           if (i > nDegCp) {
/* 344 */             test = false;
/* 345 */             ret = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 350 */     return ret;
/*     */   }
/*     */   
/*     */   public static boolean isEqual(ComplexPoly cp1, ComplexPoly cp2)
/*     */   {
/* 355 */     boolean ret = false;
/* 356 */     int nDegCp1 = cp1.getDeg();
/* 357 */     int nDegCp2 = cp2.getDeg();
/* 358 */     if (nDegCp1 == nDegCp2) {
/* 359 */       boolean test = true;
/* 360 */       int i = 0;
/* 361 */       while (test) {
/* 362 */         if (!cp1.coeffReference(i).isEqual(cp2.coeffReference(i))) {
/* 363 */           test = false;
/*     */         }
/*     */         else {
/* 366 */           i++;
/* 367 */           if (i > nDegCp1) {
/* 368 */             test = false;
/* 369 */             ret = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 374 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly plus(ComplexPoly b)
/*     */   {
/* 380 */     int n = Math.max(this.deg, b.deg);
/* 381 */     ComplexPoly c = new ComplexPoly(n);
/* 382 */     if (n == this.deg) {
/* 383 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(this.coeff[i]);
/* 384 */       for (int i = 0; i <= b.deg; i++) c.coeff[i] = this.coeff[i].plus(b.coeff[i]);
/*     */     }
/*     */     else {
/* 387 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(b.coeff[i]);
/* 388 */       for (int i = 0; i <= this.deg; i++) c.coeff[i] = this.coeff[i].plus(b.coeff[i]);
/*     */     }
/* 390 */     return c;
/*     */   }
/*     */   
/*     */   public static ComplexPoly plus(ComplexPoly a, ComplexPoly b)
/*     */   {
/* 395 */     int n = Math.max(a.deg, b.deg);
/* 396 */     ComplexPoly c = new ComplexPoly(n);
/* 397 */     if (n == a.deg) {
/* 398 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(a.coeff[i]);
/* 399 */       for (int i = 0; i <= b.deg; i++) c.coeff[i] = a.coeff[i].plus(b.coeff[i]);
/*     */     }
/*     */     else {
/* 402 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(b.coeff[i]);
/* 403 */       for (int i = 0; i <= a.deg; i++) c.coeff[i] = a.coeff[i].plus(b.coeff[i]);
/*     */     }
/* 405 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly minus(ComplexPoly b)
/*     */   {
/* 411 */     int n = Math.max(this.deg, b.deg);
/* 412 */     ComplexPoly c = new ComplexPoly(n);
/* 413 */     if (n == this.deg) {
/* 414 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(this.coeff[i]);
/* 415 */       for (int i = 0; i <= b.deg; i++) c.coeff[i] = this.coeff[i].minus(b.coeff[i]);
/*     */     }
/*     */     else {
/* 418 */       for (int i = 0; i <= n; i++) c.coeff[i] = b.coeff[i].times(Complex.minusOne());
/* 419 */       for (int i = 0; i <= this.deg; i++) c.coeff[i] = b.coeff[i].plus(this.coeff[i]);
/*     */     }
/* 421 */     return c;
/*     */   }
/*     */   
/*     */   public static ComplexPoly minus(ComplexPoly a, ComplexPoly b)
/*     */   {
/* 426 */     int n = Math.max(a.deg, b.deg);
/* 427 */     ComplexPoly c = new ComplexPoly(n);
/* 428 */     if (n == a.deg) {
/* 429 */       for (int i = 0; i <= n; i++) c.coeff[i] = Complex.copy(a.coeff[i]);
/* 430 */       for (int i = 0; i <= b.deg; i++) c.coeff[i] = a.coeff[i].minus(b.coeff[i]);
/*     */     }
/*     */     else {
/* 433 */       for (int i = 0; i <= n; i++) c.coeff[i] = b.coeff[i].times(Complex.minusOne());
/* 434 */       for (int i = 0; i <= a.deg; i++) c.coeff[i] = b.coeff[i].plus(a.coeff[i]);
/*     */     }
/* 436 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly times(ComplexPoly b)
/*     */   {
/* 442 */     int n = this.deg + b.deg;
/* 443 */     ComplexPoly c = new ComplexPoly(n);
/* 444 */     for (int i = 0; i <= this.deg; i++) {
/* 445 */       for (int j = 0; j <= b.deg; j++) {
/* 446 */         c.coeff[(i + j)].plusEquals(this.coeff[i].times(b.coeff[j]));
/*     */       }
/*     */     }
/* 449 */     return c;
/*     */   }
/*     */   
/*     */   public static ComplexPoly times(ComplexPoly a, ComplexPoly b)
/*     */   {
/* 454 */     int n = a.deg + b.deg;
/* 455 */     ComplexPoly c = new ComplexPoly(n);
/* 456 */     for (int i = 0; i <= a.deg; i++) {
/* 457 */       for (int j = 0; j <= b.deg; j++) {
/* 458 */         c.coeff[(i + j)].plusEquals(a.coeff[i].times(b.coeff[j]));
/*     */       }
/*     */     }
/* 461 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexPoly nthDerivative(int n)
/*     */   {
/*     */     ComplexPoly dnydxn;
/*     */     ComplexPoly dnydxn;
/* 469 */     if (n > this.deg) {
/* 470 */       dnydxn = new ComplexPoly(0.0D);
/*     */     }
/*     */     else {
/* 473 */       dnydxn = new ComplexPoly(this.deg - n);
/* 474 */       Complex[] nc = Complex.oneDarray(this.deg - n + 1);
/*     */       
/* 476 */       int k = this.deg - n;
/* 477 */       for (int i = this.deg; i > n - 1; i--) {
/* 478 */         nc[k] = Complex.copy(this.coeff[i]);
/* 479 */         for (int j = 0; j < n; j++) {
/* 480 */           nc[k] = Complex.times(nc[k], i - j);
/*     */         }
/* 482 */         k--;
/*     */       }
/* 484 */       dnydxn = new ComplexPoly(nc);
/*     */     }
/* 486 */     return dnydxn;
/*     */   }
/*     */   
/*     */ 
/*     */   public Complex evaluate(Complex x)
/*     */   {
/* 492 */     Complex y = new Complex();
/* 493 */     if (this.deg == 0) {
/* 494 */       y = Complex.copy(this.coeff[0]);
/*     */     }
/*     */     else {
/* 497 */       y = Complex.copy(this.coeff[this.deg]);
/* 498 */       for (int i = this.deg - 1; i >= 0; i--) {
/* 499 */         y = Complex.plus(Complex.times(y, x), this.coeff[i]);
/*     */       }
/*     */     }
/* 502 */     return y;
/*     */   }
/*     */   
/*     */   public Complex evaluate(double xx) {
/* 506 */     Complex x = new Complex(xx, 0.0D);
/* 507 */     Complex y = new Complex();
/* 508 */     if (this.deg == 0) {
/* 509 */       y = Complex.copy(this.coeff[0]);
/*     */     }
/*     */     else {
/* 512 */       y = Complex.copy(this.coeff[this.deg]);
/* 513 */       for (int i = this.deg - 1; i >= 0; i--) {
/* 514 */         y = Complex.plus(Complex.times(y, x), this.coeff[i]);
/*     */       }
/*     */     }
/* 517 */     return y;
/*     */   }
/*     */   
/*     */   public Complex nthDerivEvaluate(int n, Complex x)
/*     */   {
/* 522 */     Complex dnydxn = new Complex();
/* 523 */     Complex[] nc = Complex.oneDarray(this.deg + 1);
/*     */     
/* 525 */     if (n == 0)
/*     */     {
/* 527 */       dnydxn = evaluate(x);
/* 528 */       System.out.println("n = 0 in ComplexPoly.nthDerivative");
/* 529 */       System.out.println("polynomial itself evaluated and returned");
/*     */     }
/*     */     else {
/* 532 */       ComplexPoly nthderiv = nthDerivative(n);
/* 533 */       dnydxn = nthderiv.evaluate(x);
/*     */     }
/* 535 */     return dnydxn;
/*     */   }
/*     */   
/*     */   public Complex nthDerivEvaluate(int n, double xx) {
/* 539 */     Complex x = new Complex(xx, 0.0D);
/* 540 */     return nthDerivEvaluate(n, x);
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
/*     */   public Complex[] roots()
/*     */   {
/* 553 */     boolean polish = true;
/* 554 */     Complex estx = new Complex(0.0D, 0.0D);
/* 555 */     return roots(polish, estx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Complex[] roots(boolean polish)
/*     */   {
/* 562 */     Complex estx = new Complex(0.0D, 0.0D);
/* 563 */     return roots(polish, estx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Complex[] roots(Complex estx)
/*     */   {
/* 570 */     boolean polish = true;
/* 571 */     return roots(polish, estx);
/*     */   }
/*     */   
/*     */   public Complex[] roots(boolean polish, Complex estx)
/*     */   {
/* 576 */     if (this.deg == 0) {
/* 577 */       System.out.println("degree of the polynomial is zero in the method ComplexPoly.roots");
/* 578 */       System.out.println("null returned");
/* 579 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 583 */     boolean testzero = true;
/* 584 */     int ii = 0;int nzeros = 0;
/* 585 */     while (testzero) {
/* 586 */       if (this.coeff[ii].isZero()) {
/* 587 */         nzeros++;
/* 588 */         ii++;
/*     */       }
/*     */       else {
/* 591 */         testzero = false;
/*     */       }
/*     */     }
/* 594 */     if (nzeros > 0) {
/* 595 */       this.degwz = (this.deg - nzeros);
/* 596 */       this.coeffwz = Complex.oneDarray(this.degwz + 1);
/* 597 */       for (int i = 0; i <= this.degwz; i++) this.coeffwz[i] = this.coeff[(i + nzeros)].copy();
/*     */     }
/*     */     else {
/* 600 */       this.degwz = this.deg;
/* 601 */       this.coeffwz = Complex.oneDarray(this.degwz + 1);
/* 602 */       for (int i = 0; i <= this.degwz; i++) { this.coeffwz[i] = this.coeff[i].copy();
/*     */       }
/*     */     }
/*     */     
/* 606 */     Complex[] roots = Complex.oneDarray(this.deg);
/* 607 */     Complex[] root = Complex.oneDarray(this.degwz);
/*     */     
/* 609 */     switch (this.degwz) {
/* 610 */     case 1:  root[0] = Complex.negate(this.coeffwz[0].over(this.coeffwz[1]));
/* 611 */       break;
/* 612 */     case 2:  root = quadratic(this.coeffwz[0], this.coeffwz[1], this.coeffwz[2]);
/* 613 */       break;
/* 614 */     case 3:  root = cubic(this.coeffwz[0], this.coeffwz[1], this.coeffwz[2], this.coeffwz[3]);
/* 615 */       break;
/* 616 */     default:  root = laguerreAll(polish, estx);
/*     */     }
/*     */     
/* 619 */     for (int i = 0; i < this.degwz; i++) {
/* 620 */       roots[i] = root[i].copy();
/*     */     }
/* 622 */     if (nzeros > 0) {
/* 623 */       for (int i = this.degwz; i < this.deg; i++) {
/* 624 */         roots[i] = Complex.zero();
/*     */       }
/*     */     }
/*     */     
/* 628 */     return roots;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex[] quadratic(Complex c, Complex b, Complex a)
/*     */   {
/* 636 */     double qsign = 1.0D;
/* 637 */     Complex qsqrt = new Complex();
/* 638 */     Complex qtest = new Complex();
/* 639 */     Complex bconj = new Complex();
/* 640 */     Complex[] root = Complex.oneDarray(2);
/*     */     
/* 642 */     bconj = b.conjugate();
/* 643 */     qsqrt = Complex.sqrt(Complex.square(b).minus(a.times(c).times(4.0D)));
/*     */     
/* 645 */     qtest = bconj.times(qsqrt);
/*     */     
/* 647 */     if (qtest.getReal() < 0.0D) { qsign = -1.0D;
/*     */     }
/* 649 */     qsqrt = qsqrt.over(qsign).plus(b).over(-2.0D);
/* 650 */     root[0] = Complex.over(qsqrt, a);
/* 651 */     root[1] = Complex.over(c, qsqrt);
/*     */     
/* 653 */     return root;
/*     */   }
/*     */   
/*     */   public static Complex[] quadratic(double c, double b, double a) {
/* 657 */     Complex aa = new Complex(a, 0.0D);
/* 658 */     Complex bb = new Complex(b, 0.0D);
/* 659 */     Complex cc = new Complex(c, 0.0D);
/*     */     
/* 661 */     return quadratic(cc, bb, aa);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex[] cubic(Complex aa, Complex bb, Complex cc, Complex dd)
/*     */   {
/* 673 */     Complex a = cc.over(dd);
/* 674 */     Complex b = bb.over(dd);
/* 675 */     Complex c = aa.over(dd);
/*     */     
/* 677 */     Complex[] roots = Complex.oneDarray(3);
/*     */     
/* 679 */     Complex bigQ = a.times(a).minus(b.times(3.0D)).over(9.0D);
/* 680 */     Complex bigR = a.times(a).times(a).times(2.0D).minus(a.times(b).times(9.0D)).plus(c.times(27.0D)).over(54.0D);
/*     */     
/* 682 */     Complex sign = Complex.plusOne();
/* 683 */     Complex bigAsqrtTerm = Complex.sqrt(bigR.times(bigR).minus(bigQ.times(bigQ).times(bigQ)));
/* 684 */     Complex bigRconjugate = bigR.conjugate();
/* 685 */     if (bigRconjugate.times(bigAsqrtTerm).getReal() < 0.0D) sign = Complex.minusOne();
/* 686 */     Complex bigA = Complex.pow(bigR.plus(sign.times(bigAsqrtTerm)), 0.3333333333333333D).times(Complex.minusOne());
/* 687 */     Complex bigB = null;
/* 688 */     if (bigA.isZero()) {
/* 689 */       bigB = Complex.zero();
/*     */     }
/*     */     else {
/* 692 */       bigB = bigQ.over(bigA);
/*     */     }
/* 694 */     Complex aPlusB = bigA.plus(bigB);
/* 695 */     Complex aMinusB = bigA.minus(bigB);
/* 696 */     Complex minusAplusB = aPlusB.times(Complex.minusOne());
/* 697 */     Complex aOver3 = a.over(3.0D);
/* 698 */     Complex isqrt3over2 = new Complex(0.0D, Math.sqrt(3.0D) / 2.0D);
/* 699 */     roots[0] = aPlusB.minus(aOver3);
/* 700 */     roots[1] = minusAplusB.over(2.0D).minus(aOver3).plus(isqrt3over2.times(aMinusB));
/* 701 */     roots[2] = minusAplusB.over(2.0D).minus(aOver3).minus(isqrt3over2.times(aMinusB));
/*     */     
/* 703 */     return roots;
/*     */   }
/*     */   
/*     */   public static Complex[] cubic(double d, double c, double b, double a) {
/* 707 */     Complex aa = new Complex(a, 0.0D);
/* 708 */     Complex bb = new Complex(b, 0.0D);
/* 709 */     Complex cc = new Complex(c, 0.0D);
/* 710 */     Complex dd = new Complex(d, 0.0D);
/*     */     
/* 712 */     return cubic(dd, cc, bb, aa);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex laguerre(Complex estx, Complex[] pcoeff, int m)
/*     */   {
/* 723 */     double eps = 1.0E-7D;
/* 724 */     int mr = 8;
/* 725 */     int mt = 1000;
/* 726 */     int maxit = mr * mt;
/* 727 */     int niter = 0;
/*     */     
/*     */ 
/* 730 */     double[] frac = { 0.5D, 0.25D, 0.75D, 0.13D, 0.38D, 0.62D, 0.88D, 1.0D };
/*     */     
/* 732 */     Complex root = new Complex();
/* 733 */     Complex b = new Complex();
/* 734 */     Complex d = new Complex();
/* 735 */     Complex f = new Complex();
/* 736 */     Complex g = new Complex();
/* 737 */     Complex g2 = new Complex();
/* 738 */     Complex h = new Complex();
/* 739 */     Complex sq = new Complex();
/* 740 */     Complex gp = new Complex();
/* 741 */     Complex gm = new Complex();
/* 742 */     Complex dx = new Complex();
/* 743 */     Complex x1 = new Complex();
/* 744 */     Complex temp1 = new Complex();
/* 745 */     Complex temp2 = new Complex();
/*     */     
/* 747 */     double abp = 0.0D;double abm = 0.0D;
/* 748 */     double err = 0.0D;double abx = 0.0D;
/*     */     
/* 750 */     for (int i = 1; i <= maxit; i++) {
/* 751 */       niter = i;
/* 752 */       b = Complex.copy(pcoeff[m]);
/* 753 */       err = Complex.abs(b);
/* 754 */       d = f = Complex.zero();
/* 755 */       abx = Complex.abs(estx);
/* 756 */       for (int j = m - 1; j >= 0; j--)
/*     */       {
/*     */ 
/* 759 */         f = Complex.plus(Complex.times(estx, f), d);
/* 760 */         d = Complex.plus(Complex.times(estx, d), b);
/* 761 */         b = Complex.plus(Complex.times(estx, b), pcoeff[j]);
/* 762 */         err = Complex.abs(b) + abx * err;
/*     */       }
/* 764 */       err *= eps;
/*     */       
/*     */ 
/* 767 */       if (Complex.abs(b) <= err)
/*     */       {
/* 769 */         root = Complex.copy(estx);
/* 770 */         niter = i;
/* 771 */         return root;
/*     */       }
/*     */       
/* 774 */       g = Complex.over(d, b);
/* 775 */       g2 = Complex.square(g);
/* 776 */       h = Complex.minus(g2, Complex.times(2.0D, Complex.over(f, b)));
/* 777 */       sq = Complex.sqrt(Complex.times(m - 1, Complex.minus(Complex.times(m, h), g2)));
/* 778 */       gp = Complex.plus(g, sq);
/* 779 */       gm = Complex.minus(g, sq);
/* 780 */       abp = Complex.abs(gp);
/* 781 */       abm = Complex.abs(gm);
/* 782 */       if (abp < abm) gp = gm;
/* 783 */       temp1.setReal(m);
/* 784 */       temp2.setReal(Math.cos(i));
/* 785 */       temp2.setImag(Math.sin(i));
/* 786 */       dx = Math.max(abp, abm) > 0.0D ? Complex.over(temp1, gp) : Complex.times(Math.exp(1.0D + abx), temp2);
/* 787 */       x1 = Complex.minus(estx, dx);
/* 788 */       if (Complex.isEqual(estx, x1))
/*     */       {
/* 790 */         root = Complex.copy(estx);
/* 791 */         niter = i;
/* 792 */         return root;
/*     */       }
/* 794 */       if (i % mt != 0) {
/* 795 */         estx = Complex.copy(x1);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 800 */         estx = Complex.minus(estx, Complex.times(frac[(i / mt - 1)], dx));
/*     */       }
/* 802 */       niter = i;
/*     */     }
/*     */     
/* 805 */     root = Complex.copy(estx);
/* 806 */     System.out.println("Maximum number of iterations exceeded in laguerre");
/* 807 */     System.out.println("root returned at this point");
/* 808 */     return root;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Complex[] laguerreAll()
/*     */   {
/* 815 */     Complex estx = new Complex(0.0D, 0.0D);
/* 816 */     boolean polish = true;
/* 817 */     return laguerreAll(polish, estx);
/*     */   }
/*     */   
/*     */   public Complex[] laguerreAll(Complex estx)
/*     */   {
/* 822 */     boolean polish = true;
/* 823 */     return laguerreAll(polish, estx);
/*     */   }
/*     */   
/*     */   public Complex[] laguerreAll(boolean polish)
/*     */   {
/* 828 */     Complex estx = new Complex(0.0D, 0.0D);
/* 829 */     return laguerreAll(polish, estx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Complex[] laguerreAll(boolean polish, Complex estx)
/*     */   {
/* 841 */     int m = this.degwz;
/* 842 */     double eps = 2.0E-6D;
/*     */     
/* 844 */     Complex x = new Complex();
/* 845 */     Complex b = new Complex();
/* 846 */     Complex c = new Complex();
/* 847 */     Complex[] ad = new Complex[m + 1];
/* 848 */     Complex[] roots = new Complex[m + 1];
/*     */     
/*     */ 
/* 851 */     for (int j = 0; j <= m; j++) { ad[j] = Complex.copy(this.coeffwz[j]);
/*     */     }
/*     */     
/* 854 */     for (int j = m; j >= 1; j--) {
/* 855 */       x = Complex.copy(estx);
/*     */       
/* 857 */       x = laguerre(x, ad, j);
/* 858 */       if (Math.abs(x.getImag()) <= 2.0D * eps * Math.abs(x.getReal())) x.setImag(0.0D);
/* 859 */       roots[j] = Complex.copy(x);
/* 860 */       b = Complex.copy(ad[j]);
/* 861 */       for (int jj = j - 1; jj >= 0; jj--) {
/* 862 */         c = Complex.copy(ad[jj]);
/* 863 */         ad[jj] = Complex.copy(b);
/* 864 */         b = x.times(b).plus(c);
/*     */       }
/*     */     }
/*     */     
/* 868 */     if (polish)
/*     */     {
/* 870 */       for (int j = 1; j <= m; j++) {
/* 871 */         roots[j] = laguerre(roots[j], this.coeffwz, m);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 876 */     for (int j = 2; j <= m; j++) {
/* 877 */       x = Complex.copy(roots[j]);
/* 878 */       int i = 0;
/* 879 */       for (i = j - 1; i >= 1; i--) {
/* 880 */         if (roots[i].getReal() <= x.getReal()) break;
/* 881 */         roots[(i + 1)] = Complex.copy(roots[i]);
/*     */       }
/* 883 */       roots[(i + 1)] = Complex.copy(x);
/*     */     }
/*     */     
/* 886 */     for (int i = 0; i < m; i++) roots[i] = Complex.copy(roots[(i + 1)]);
/* 887 */     return roots;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/complex/ComplexPoly.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */