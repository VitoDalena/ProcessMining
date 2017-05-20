/*      */ package com.imsl.math;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Complex
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   private double re;
/*      */   private double im;
/*      */   static final long serialVersionUID = -633126172485117692L;
/*   82 */   public static String suffix = "i";
/*      */   
/*      */ 
/*   85 */   private static final long negZeroBits = Double.doubleToLongBits(-0.0D);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex()
/*      */   {
/*   95 */     this.re = 0.0D;
/*   96 */     this.im = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex(double paramDouble)
/*      */   {
/*  104 */     this.re = paramDouble;
/*  105 */     this.im = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex(double paramDouble1, double paramDouble2)
/*      */   {
/*  115 */     this.re = paramDouble1;
/*  116 */     this.im = paramDouble2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex(Complex paramComplex)
/*      */   {
/*  125 */     this.re = paramComplex.re;
/*  126 */     this.im = paramComplex.im;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double abs(Complex paramComplex)
/*      */   {
/*  135 */     double d1 = Math.abs(paramComplex.re);
/*  136 */     double d2 = Math.abs(paramComplex.im);
/*      */     
/*  138 */     if ((Double.isInfinite(d1)) || (Double.isInfinite(d2))) {
/*  139 */       return Double.POSITIVE_INFINITY;
/*      */     }
/*  141 */     if (d1 + d2 == 0.0D)
/*  142 */       return 0.0D;
/*  143 */     if (d1 > d2) {
/*  144 */       d2 /= d1;
/*  145 */       return d1 * Math.sqrt(1.0D + d2 * d2);
/*      */     }
/*  147 */     d1 /= d2;
/*  148 */     return d2 * Math.sqrt(d1 * d1 + 1.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex acos(Complex paramComplex)
/*      */   {
/*  162 */     Complex localComplex = new Complex();
/*  163 */     double d = abs(paramComplex);
/*      */     
/*  165 */     if ((Double.isInfinite(paramComplex.re)) && (Double.isNaN(paramComplex.im))) {
/*  166 */       localComplex.re = NaN.0D;
/*  167 */       localComplex.im = Double.NEGATIVE_INFINITY;
/*  168 */     } else if (Double.isInfinite(d)) {
/*  169 */       localComplex.re = Math.atan2(Math.abs(paramComplex.im), paramComplex.re);
/*  170 */       paramComplex.im *= Double.NEGATIVE_INFINITY;
/*  171 */     } else if (d == 0.0D) {
/*  172 */       localComplex.re = 1.5707963267948966D;
/*  173 */       localComplex.im = (-paramComplex.im);
/*      */     } else {
/*  175 */       localComplex = minus(1.5707963267948966D, asin(paramComplex));
/*      */     }
/*  177 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex acosh(Complex paramComplex)
/*      */   {
/*  190 */     Complex localComplex = acos(paramComplex);
/*  191 */     double d = -localComplex.im;
/*  192 */     localComplex.im = localComplex.re;
/*  193 */     localComplex.re = d;
/*  194 */     if ((localComplex.re < 0.0D) || (isNegZero(localComplex.re))) {
/*  195 */       localComplex.re = (-localComplex.re);
/*  196 */       localComplex.im = (-localComplex.im);
/*      */     }
/*  198 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double argument(Complex paramComplex)
/*      */   {
/*  209 */     return Math.atan2(paramComplex.im, paramComplex.re);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex asin(Complex paramComplex)
/*      */   {
/*  222 */     Complex localComplex1 = new Complex();
/*      */     
/*  224 */     double d1 = abs(paramComplex);
/*      */     boolean bool2;
/*  226 */     double d2; if (Double.isInfinite(d1)) {
/*  227 */       boolean bool1 = Double.isInfinite(paramComplex.re);
/*  228 */       bool2 = Double.isInfinite(paramComplex.im);
/*  229 */       if (bool1) {
/*  230 */         d2 = 1.5707963267948966D;
/*  231 */         localComplex1.re = (paramComplex.re > 0.0D ? d2 : -d2);
/*  232 */         if (bool2) localComplex1.re /= 2.0D;
/*  233 */       } else if (bool2) {
/*  234 */         paramComplex.re /= Double.POSITIVE_INFINITY;
/*      */       }
/*  236 */       if (Double.isNaN(paramComplex.im)) {
/*  237 */         localComplex1.im = (-paramComplex.re);
/*  238 */         localComplex1.re = paramComplex.im;
/*      */       } else {
/*  240 */         paramComplex.im *= Double.POSITIVE_INFINITY;
/*      */       }
/*  242 */       return localComplex1; }
/*  243 */     if (Double.isNaN(d1)) {
/*  244 */       localComplex1.re = (localComplex1.im = NaN.0D);
/*  245 */       if (paramComplex.re == 0.0D) localComplex1.re = paramComplex.re;
/*  246 */     } else if (d1 < 2.58095E-8D)
/*      */     {
/*  248 */       localComplex1.re = paramComplex.re;
/*  249 */       localComplex1.im = paramComplex.im;
/*  250 */     } else if (paramComplex.re == 0.0D) {
/*  251 */       localComplex1.re = 0.0D;
/*  252 */       localComplex1.im = Sfun.asinh(paramComplex.im); } else { Complex localComplex2;
/*  253 */       if (d1 <= 0.1D) {
/*  254 */         localComplex2 = times(paramComplex, paramComplex);
/*      */         
/*  256 */         for (bool2 = true; bool2 <= true; bool2++) {
/*  257 */           d2 = 2 * (true - bool2) + 1;
/*  258 */           localComplex1 = times(times(localComplex1, localComplex2), d2 / (d2 + 1.0D));
/*  259 */           localComplex1.re += 1.0D / d2;
/*      */         }
/*  261 */         localComplex1 = localComplex1.times(paramComplex);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  267 */         localComplex2 = paramComplex.im < 0.0D ? negative(paramComplex) : paramComplex;
/*  268 */         Complex localComplex3 = sqrt(plus(localComplex2, 1.0D));
/*  269 */         if (localComplex3.im < 0.0D)
/*  270 */           localComplex3 = negative(localComplex3);
/*  271 */         Complex localComplex4 = sqrt(minus(localComplex2, 1.0D));
/*  272 */         localComplex1 = log(plus(localComplex2, times(localComplex3, localComplex4)));
/*      */         
/*  274 */         double d3 = localComplex1.re;
/*  275 */         localComplex1.re = (1.5707963267948966D + localComplex1.im);
/*  276 */         localComplex1.im = (-d3);
/*      */       }
/*      */     }
/*  279 */     if (localComplex1.re > 1.5707963267948966D) {
/*  280 */       localComplex1.re = (3.141592653589793D - localComplex1.re);
/*  281 */       localComplex1.im = (-localComplex1.im);
/*      */     }
/*  283 */     if (localComplex1.re < -1.5707963267948966D) {
/*  284 */       localComplex1.re = (-3.141592653589793D - localComplex1.re);
/*  285 */       localComplex1.im = (-localComplex1.im);
/*      */     }
/*  287 */     if (paramComplex.im < 0.0D) {
/*  288 */       localComplex1.re = (-localComplex1.re);
/*  289 */       localComplex1.im = (-localComplex1.im);
/*      */     }
/*  291 */     return localComplex1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex asinh(Complex paramComplex)
/*      */   {
/*  304 */     Complex localComplex1 = new Complex(paramComplex.im, -paramComplex.re);
/*  305 */     Complex localComplex2 = asin(localComplex1);
/*  306 */     double d = localComplex2.im;
/*  307 */     localComplex2.im = localComplex2.re;
/*  308 */     localComplex2.re = (-d);
/*  309 */     return localComplex2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex atan(Complex paramComplex)
/*      */   {
/*  322 */     Complex localComplex1 = new Complex();
/*  323 */     double d1 = abs(paramComplex);
/*      */     
/*  325 */     if (Double.isInfinite(d1)) {
/*  326 */       double d2 = 1.5707963267948966D;
/*  327 */       double d4 = Double.isNaN(paramComplex.im) ? 0.0D : paramComplex.im;
/*  328 */       localComplex1.re = (paramComplex.re < 0.0D ? -d2 : d2);
/*  329 */       localComplex1.im = ((d4 < 0.0D ? -1.0D : 1.0D) / Double.POSITIVE_INFINITY);
/*  330 */       if (Double.isNaN(paramComplex.re)) localComplex1.re = paramComplex.re;
/*  331 */     } else if (Double.isNaN(d1)) {
/*  332 */       localComplex1.re = (localComplex1.im = NaN.0D);
/*  333 */       if (paramComplex.im == 0.0D) localComplex1.im = paramComplex.im;
/*  334 */     } else if (d1 < 1.82501E-8D)
/*      */     {
/*  336 */       localComplex1.re = paramComplex.re;
/*  337 */       localComplex1.im = paramComplex.im;
/*  338 */     } else if (d1 < 0.1D) {
/*  339 */       Complex localComplex2 = times(paramComplex, paramComplex);
/*      */       
/*  341 */       for (int i = 0; i < 17; i++) {
/*  342 */         Complex localComplex3 = times(localComplex2, localComplex1);
/*  343 */         int j = 2 * (17 - i) - 1;
/*  344 */         localComplex1.re = (1.0D / j - localComplex3.re);
/*  345 */         localComplex1.im = (-localComplex3.im);
/*      */       }
/*  347 */       localComplex1 = localComplex1.times(paramComplex);
/*  348 */     } else if (d1 < 9.0072E15D)
/*      */     {
/*  350 */       double d3 = d1 * d1;
/*  351 */       localComplex1.re = (0.5D * Math.atan2(2.0D * paramComplex.re, 1.0D - d3));
/*  352 */       localComplex1.im = (0.25D * Math.log((d3 + 2.0D * paramComplex.im + 1.0D) / (d3 - 2.0D * paramComplex.im + 1.0D)));
/*      */     } else {
/*  354 */       localComplex1.re = (paramComplex.re < 0.0D ? -1.5707963267948966D : 1.5707963267948966D);
/*      */     }
/*  356 */     return localComplex1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex atanh(Complex paramComplex)
/*      */   {
/*  370 */     Complex localComplex1 = new Complex(paramComplex.im, -paramComplex.re);
/*  371 */     Complex localComplex2 = atan(localComplex1);
/*  372 */     double d = localComplex2.im;
/*  373 */     localComplex2.im = localComplex2.re;
/*  374 */     localComplex2.re = (-d);
/*  375 */     return localComplex2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex conjugate(Complex paramComplex)
/*      */   {
/*  385 */     return new Complex(paramComplex.re, -paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static double copysign(double paramDouble1, double paramDouble2)
/*      */   {
/*  392 */     double d = Math.abs(paramDouble1);
/*  393 */     return paramDouble2 < 0.0D ? -d : d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex cos(Complex paramComplex)
/*      */   {
/*  403 */     return cosh(new Complex(-paramComplex.im, paramComplex.re));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex cosh(Complex paramComplex)
/*      */   {
/*  413 */     if (paramComplex.im == 0.0D) {
/*  414 */       return new Complex(Sfun.cosh(paramComplex.re));
/*      */     }
/*      */     
/*  417 */     double d1 = Sfun.cosh(paramComplex.re);
/*  418 */     double d2 = Sfun.sinh(paramComplex.re);
/*  419 */     double d3 = Math.cos(paramComplex.im);
/*  420 */     double d4 = Math.sin(paramComplex.im);
/*  421 */     boolean bool1 = Double.isInfinite(d1);
/*  422 */     boolean bool2 = Double.isInfinite(paramComplex.im);
/*      */     
/*      */ 
/*  425 */     Complex localComplex = new Complex(d1 * d3, d2 * d4);
/*  426 */     if (bool2) localComplex.re = NaN.0D;
/*  427 */     if (paramComplex.re == 0.0D) {
/*  428 */       localComplex.im = 0.0D;
/*  429 */     } else if (bool1) {
/*  430 */       paramComplex.re *= d3;
/*  431 */       localComplex.im = (paramComplex.re * d4);
/*  432 */       if (paramComplex.im == 0.0D) localComplex.im = 0.0D;
/*  433 */       if (Double.isNaN(paramComplex.im)) {
/*  434 */         localComplex.re = paramComplex.re;
/*  435 */       } else if (bool2) {
/*  436 */         localComplex.re = paramComplex.im;
/*      */       }
/*      */     }
/*  439 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Complex paramComplex)
/*      */   {
/*  452 */     if ((isNaN()) && (paramComplex.isNaN())) {
/*  453 */       return true;
/*      */     }
/*  455 */     return (this.re == paramComplex.re) && (this.im == paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  468 */     if (paramObject == null)
/*  469 */       return false;
/*  470 */     if ((paramObject instanceof Complex)) {
/*  471 */       return equals((Complex)paramObject);
/*      */     }
/*  473 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex exp(Complex paramComplex)
/*      */   {
/*  484 */     Complex localComplex = new Complex();
/*      */     
/*  486 */     double d1 = Math.exp(paramComplex.re);
/*      */     
/*  488 */     double d2 = Math.cos(paramComplex.im);
/*  489 */     double d3 = Math.sin(paramComplex.im);
/*  490 */     if ((Double.isInfinite(paramComplex.im)) || (Double.isNaN(paramComplex.im)) || (Math.abs(d2) > 1.0D)) {
/*  491 */       d2 = d3 = NaN.0D;
/*      */     }
/*      */     
/*      */ 
/*  495 */     if ((Double.isInfinite(paramComplex.re)) || (Double.isInfinite(d1))) {
/*  496 */       if (paramComplex.re < 0.0D) {
/*  497 */         d1 = 0.0D;
/*  498 */         if ((Double.isInfinite(paramComplex.im)) || (Double.isNaN(paramComplex.im))) {
/*  499 */           d2 = d3 = 0.0D;
/*      */         } else {
/*  501 */           d2 /= Double.POSITIVE_INFINITY;
/*  502 */           d3 /= Double.POSITIVE_INFINITY;
/*      */         }
/*      */       } else {
/*  505 */         d1 = paramComplex.re;
/*  506 */         if (Double.isNaN(paramComplex.im)) { d2 = 1.0D;
/*      */         }
/*      */       }
/*      */     }
/*  510 */     if (paramComplex.im == 0.0D) {
/*  511 */       localComplex.re = d1;
/*  512 */       localComplex.im = paramComplex.im;
/*      */     } else {
/*  514 */       localComplex.re = (d1 * d2);
/*  515 */       localComplex.im = (d1 * d3);
/*      */     }
/*  517 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  525 */     long l1 = Double.doubleToLongBits(this.re);
/*  526 */     long l2 = Double.doubleToLongBits(this.im);
/*  527 */     return (int)(l1 ^ l2 ^ (l1 ^ l2) >> 32);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double imag()
/*      */   {
/*  536 */     return this.im;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double imag(Complex paramComplex)
/*      */   {
/*  545 */     return paramComplex.im;
/*      */   }
/*      */   
/*      */   private static boolean isFinite(double paramDouble) {
/*  549 */     return (!Double.isInfinite(paramDouble)) && (!Double.isNaN(paramDouble));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isNaN()
/*      */   {
/*  558 */     return (Double.isNaN(this.re)) || (Double.isNaN(this.im));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static boolean isNegZero(double paramDouble)
/*      */   {
/*  565 */     return Double.doubleToLongBits(paramDouble) == negZeroBits;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex log(Complex paramComplex)
/*      */   {
/*  577 */     Complex localComplex = new Complex();
/*      */     
/*  579 */     if (Double.isNaN(paramComplex.re)) {
/*  580 */       localComplex.re = (localComplex.im = paramComplex.re);
/*  581 */       if (Double.isInfinite(paramComplex.im))
/*  582 */         localComplex.re = Double.POSITIVE_INFINITY;
/*  583 */     } else if (Double.isNaN(paramComplex.im)) {
/*  584 */       localComplex.re = (localComplex.im = paramComplex.im);
/*  585 */       if (Double.isInfinite(paramComplex.re))
/*  586 */         localComplex.re = Double.POSITIVE_INFINITY;
/*      */     } else {
/*  588 */       localComplex.re = Math.log(abs(paramComplex));
/*  589 */       localComplex.im = argument(paramComplex);
/*      */     }
/*  591 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex minus(double paramDouble)
/*      */   {
/*  600 */     return new Complex(this.re - paramDouble, this.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex minus(double paramDouble, Complex paramComplex)
/*      */   {
/*  610 */     return new Complex(paramDouble - paramComplex.re, -paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex minus(Complex paramComplex)
/*      */   {
/*  620 */     return new Complex(this.re - paramComplex.re, this.im - paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex minus(Complex paramComplex, double paramDouble)
/*      */   {
/*  630 */     return new Complex(paramComplex.re - paramDouble, paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex minus(Complex paramComplex1, Complex paramComplex2)
/*      */   {
/*  640 */     return new Complex(paramComplex1.re - paramComplex2.re, paramComplex1.im - paramComplex2.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex minusReverse(double paramDouble)
/*      */   {
/*  649 */     return new Complex(paramDouble - this.re, -this.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex negative(Complex paramComplex)
/*      */   {
/*  659 */     return new Complex(-paramComplex.re, -paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex over(double paramDouble)
/*      */   {
/*  668 */     return over(this, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex over(double paramDouble, Complex paramComplex)
/*      */   {
/*  678 */     return paramComplex.overReverse(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex over(Complex paramComplex)
/*      */   {
/*  687 */     return over(this, paramComplex);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex over(Complex paramComplex, double paramDouble)
/*      */   {
/*  697 */     return new Complex(paramComplex.re / paramDouble, paramComplex.im / paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex over(Complex paramComplex1, Complex paramComplex2)
/*      */   {
/*  707 */     double d1 = paramComplex1.re;
/*  708 */     double d2 = paramComplex1.im;
/*  709 */     double d3 = paramComplex2.re;
/*  710 */     double d4 = paramComplex2.im;
/*      */     
/*  712 */     double d5 = Math.max(Math.abs(d3), Math.abs(d4));
/*  713 */     boolean bool = isFinite(d5);
/*  714 */     if (bool) {
/*  715 */       d3 /= d5;
/*  716 */       d4 /= d5;
/*      */     }
/*      */     
/*  719 */     double d6 = d3 * d3 + d4 * d4;
/*  720 */     Complex localComplex = new Complex((d1 * d3 + d2 * d4) / d6, (d2 * d3 - d1 * d4) / d6);
/*      */     
/*  722 */     if (bool) {
/*  723 */       localComplex.re /= d5;
/*  724 */       localComplex.im /= d5;
/*      */     }
/*      */     
/*      */ 
/*  728 */     if ((Double.isNaN(localComplex.re)) && (Double.isNaN(localComplex.im))) {
/*  729 */       if ((d6 == 0.0D) && ((!Double.isNaN(d1)) || (!Double.isNaN(d2)))) {
/*  730 */         double d7 = copysign(Double.POSITIVE_INFINITY, d3);
/*  731 */         localComplex.re = (d7 * d1);
/*  732 */         localComplex.im = (d7 * d2);
/*      */       }
/*  734 */       else if (((Double.isInfinite(d1)) || (Double.isInfinite(d2))) && (isFinite(d3)) && (isFinite(d4)))
/*      */       {
/*  736 */         d1 = copysign(Double.isInfinite(d1) ? 1.0D : 0.0D, d1);
/*  737 */         d2 = copysign(Double.isInfinite(d2) ? 1.0D : 0.0D, d2);
/*  738 */         localComplex.re = (Double.POSITIVE_INFINITY * (d1 * d3 + d2 * d4));
/*  739 */         localComplex.im = (Double.POSITIVE_INFINITY * (d2 * d3 - d1 * d4));
/*      */       }
/*  741 */       else if ((Double.isInfinite(d5)) && (isFinite(d1)) && (isFinite(d2)))
/*      */       {
/*  743 */         d3 = copysign(Double.isInfinite(d3) ? 1.0D : 0.0D, d3);
/*  744 */         d4 = copysign(Double.isInfinite(d4) ? 1.0D : 0.0D, d4);
/*  745 */         localComplex.re = (0.0D * (d1 * d3 + d2 * d4));
/*  746 */         localComplex.im = (0.0D * (d2 * d3 - d1 * d4));
/*      */       }
/*      */     }
/*  749 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex overReverse(double paramDouble)
/*      */   {
/*      */     double d2;
/*      */     
/*      */     double d1;
/*      */     
/*      */     Complex localComplex;
/*  760 */     if (Math.abs(this.re) > Math.abs(this.im)) {
/*  761 */       d2 = this.im / this.re;
/*  762 */       d1 = this.re + this.im * d2;
/*  763 */       localComplex = new Complex(paramDouble / d1, -paramDouble * d2 / d1);
/*      */     } else {
/*  765 */       d2 = this.re / this.im;
/*  766 */       d1 = this.im + this.re * d2;
/*  767 */       localComplex = new Complex(paramDouble * d2 / d1, -paramDouble / d1);
/*      */     }
/*  769 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex plus(double paramDouble)
/*      */   {
/*  778 */     return new Complex(this.re + paramDouble, this.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex plus(double paramDouble, Complex paramComplex)
/*      */   {
/*  788 */     return new Complex(paramDouble + paramComplex.re, paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex plus(Complex paramComplex)
/*      */   {
/*  797 */     return new Complex(this.re + paramComplex.re, this.im + paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex plus(Complex paramComplex, double paramDouble)
/*      */   {
/*  807 */     return new Complex(paramComplex.re + paramDouble, paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex plus(Complex paramComplex1, Complex paramComplex2)
/*      */   {
/*  817 */     return new Complex(paramComplex1.re + paramComplex2.re, paramComplex1.im + paramComplex2.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex plusReverse(double paramDouble)
/*      */   {
/*  826 */     return new Complex(this.re + paramDouble, this.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex pow(Complex paramComplex, double paramDouble)
/*      */   {
/*  838 */     double d1 = abs(paramComplex);
/*  839 */     Complex localComplex = new Complex();
/*      */     
/*  841 */     if (d1 == 0.0D) {
/*  842 */       localComplex = paramComplex;
/*      */     } else {
/*  844 */       double d2 = argument(paramComplex);
/*  845 */       double d3 = Math.pow(d1, paramDouble);
/*  846 */       localComplex.re = (d3 * Math.cos(paramDouble * d2));
/*  847 */       localComplex.im = (d3 * Math.sin(paramDouble * d2));
/*      */     }
/*  849 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex pow(Complex paramComplex1, Complex paramComplex2)
/*      */   {
/*  860 */     return exp(times(paramComplex2, log(paramComplex1)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double real()
/*      */   {
/*  868 */     return this.re;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double real(Complex paramComplex)
/*      */   {
/*  877 */     return paramComplex.re;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex sin(Complex paramComplex)
/*      */   {
/*  887 */     Complex localComplex1 = new Complex(-paramComplex.im, paramComplex.re);
/*  888 */     Complex localComplex2 = sinh(localComplex1);
/*  889 */     double d = localComplex2.im;
/*  890 */     localComplex2.im = (-localComplex2.re);
/*  891 */     localComplex2.re = d;
/*  892 */     return localComplex2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex sinh(Complex paramComplex)
/*      */   {
/*  902 */     double d1 = Sfun.cosh(paramComplex.re);
/*  903 */     double d2 = Sfun.sinh(paramComplex.re);
/*  904 */     double d3 = Math.cos(paramComplex.im);
/*  905 */     double d4 = Math.sin(paramComplex.im);
/*  906 */     boolean bool1 = Double.isInfinite(d1);
/*  907 */     boolean bool2 = Double.isInfinite(paramComplex.im);
/*      */     
/*      */     Complex localComplex;
/*  910 */     if (paramComplex.im == 0.0D) {
/*  911 */       localComplex = new Complex(Sfun.sinh(paramComplex.re));
/*      */     }
/*      */     else {
/*  914 */       localComplex = new Complex(d2 * d3, d1 * d4);
/*  915 */       if (bool2) {
/*  916 */         localComplex.im = NaN.0D;
/*  917 */         if (paramComplex.re == 0.0D) localComplex.re = 0.0D;
/*      */       }
/*  919 */       if (bool1) {
/*  920 */         paramComplex.re *= d3;
/*  921 */         localComplex.im = (paramComplex.re * d4);
/*  922 */         if (paramComplex.im == 0.0D) localComplex.im = 0.0D;
/*  923 */         if (bool2) localComplex.re = paramComplex.im;
/*      */       }
/*      */     }
/*  926 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex sqrt(Complex paramComplex)
/*      */   {
/*  938 */     Complex localComplex = new Complex();
/*      */     
/*  940 */     if (Double.isInfinite(paramComplex.im)) {
/*  941 */       localComplex.re = Double.POSITIVE_INFINITY;
/*  942 */       localComplex.im = paramComplex.im;
/*  943 */     } else if (Double.isNaN(paramComplex.re)) {
/*  944 */       localComplex.re = (localComplex.im = NaN.0D);
/*  945 */     } else if (Double.isNaN(paramComplex.im)) {
/*  946 */       if (Double.isInfinite(paramComplex.re)) {
/*  947 */         if (paramComplex.re > 0.0D) {
/*  948 */           localComplex.re = paramComplex.re;
/*  949 */           localComplex.im = paramComplex.im;
/*      */         } else {
/*  951 */           localComplex.re = paramComplex.im;
/*  952 */           localComplex.im = Double.POSITIVE_INFINITY;
/*      */         }
/*      */       } else {
/*  955 */         localComplex.re = (localComplex.im = NaN.0D);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  960 */       double d = abs(paramComplex);
/*      */       
/*  962 */       if (Math.abs(paramComplex.re) <= Math.abs(paramComplex.im))
/*      */       {
/*  964 */         localComplex.re = Math.sqrt(0.5D * (d + paramComplex.re));
/*  965 */         localComplex.im = Math.sqrt(0.5D * (d - paramComplex.re));
/*      */ 
/*      */       }
/*  968 */       else if (paramComplex.re > 0.0D) {
/*  969 */         localComplex.re = (d + paramComplex.re);
/*  970 */         localComplex.im = (Math.abs(paramComplex.im) * Math.sqrt(0.5D / localComplex.re));
/*  971 */         localComplex.re = Math.sqrt(0.5D * localComplex.re);
/*      */       } else {
/*  973 */         localComplex.im = (d - paramComplex.re);
/*  974 */         localComplex.re = (Math.abs(paramComplex.im) * Math.sqrt(0.5D / localComplex.im));
/*  975 */         localComplex.im = Math.sqrt(0.5D * localComplex.im);
/*      */       }
/*      */       
/*  978 */       if (paramComplex.im < 0.0D)
/*  979 */         localComplex.im = (-localComplex.im);
/*      */     }
/*  981 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex tan(Complex paramComplex)
/*      */   {
/*  992 */     Complex localComplex1 = new Complex(-paramComplex.im, paramComplex.re);
/*  993 */     Complex localComplex2 = tanh(localComplex1);
/*  994 */     double d = localComplex2.im;
/*  995 */     localComplex2.im = (-localComplex2.re);
/*  996 */     localComplex2.re = d;
/*  997 */     return localComplex2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex tanh(Complex paramComplex)
/*      */   {
/* 1007 */     double d1 = Sfun.sinh(2.0D * paramComplex.re);
/*      */     
/* 1009 */     if (paramComplex.im == 0.0D)
/* 1010 */       return new Complex(Sfun.tanh(paramComplex.re));
/* 1011 */     if (d1 == 0.0D) {
/* 1012 */       return new Complex(0.0D, Math.tan(paramComplex.im));
/*      */     }
/*      */     
/* 1015 */     double d2 = Sfun.cosh(2.0D * paramComplex.re);
/* 1016 */     double d3 = Math.cos(2.0D * paramComplex.im);
/* 1017 */     double d4 = Math.sin(2.0D * paramComplex.im);
/* 1018 */     boolean bool = Double.isInfinite(d2);
/*      */     
/*      */ 
/* 1021 */     if ((Double.isInfinite(paramComplex.im)) || (Double.isNaN(paramComplex.im))) {
/* 1022 */       d3 = d4 = NaN.0D;
/*      */     }
/*      */     
/* 1025 */     if (bool) {
/* 1026 */       return new Complex(paramComplex.re > 0.0D ? 1.0D : -1.0D);
/*      */     }
/*      */     
/* 1029 */     double d5 = d2 + d3;
/* 1030 */     return new Complex(d1 / d5, d4 / d5);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex times(double paramDouble)
/*      */   {
/* 1039 */     return new Complex(this.re * paramDouble, this.im * paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex times(double paramDouble, Complex paramComplex)
/*      */   {
/* 1049 */     return new Complex(paramDouble * paramComplex.re, paramDouble * paramComplex.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex times(Complex paramComplex)
/*      */   {
/* 1058 */     return times(this, paramComplex);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex times(Complex paramComplex, double paramDouble)
/*      */   {
/* 1068 */     return new Complex(paramComplex.re * paramDouble, paramComplex.im * paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex times(Complex paramComplex1, Complex paramComplex2)
/*      */   {
/* 1078 */     Complex localComplex = new Complex(paramComplex1.re * paramComplex2.re - paramComplex1.im * paramComplex2.im, paramComplex1.re * paramComplex2.im + paramComplex1.im * paramComplex2.re);
/* 1079 */     if ((Double.isNaN(localComplex.re)) && (Double.isNaN(localComplex.im)))
/* 1080 */       timesNaN(paramComplex1, paramComplex2, localComplex);
/* 1081 */     return localComplex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void timesNaN(Complex paramComplex1, Complex paramComplex2, Complex paramComplex3)
/*      */   {
/* 1099 */     int i = 0;
/* 1100 */     double d1 = paramComplex1.re;
/* 1101 */     double d2 = paramComplex1.im;
/* 1102 */     double d3 = paramComplex2.re;
/* 1103 */     double d4 = paramComplex2.im;
/*      */     
/* 1105 */     if ((Double.isInfinite(d1)) || (Double.isInfinite(d2)))
/*      */     {
/* 1107 */       d1 = copysign(Double.isInfinite(d1) ? 1.0D : 0.0D, d1);
/* 1108 */       d2 = copysign(Double.isInfinite(d2) ? 1.0D : 0.0D, d2);
/* 1109 */       if (Double.isNaN(d3)) d3 = copysign(0.0D, d3);
/* 1110 */       if (Double.isNaN(d4)) d4 = copysign(0.0D, d4);
/* 1111 */       i = 1;
/*      */     }
/*      */     
/* 1114 */     if ((Double.isInfinite(d3)) || (Double.isInfinite(d4)))
/*      */     {
/* 1116 */       d1 = copysign(Double.isInfinite(d3) ? 1.0D : 0.0D, d3);
/* 1117 */       d2 = copysign(Double.isInfinite(d4) ? 1.0D : 0.0D, d4);
/* 1118 */       if (Double.isNaN(d1)) d1 = copysign(0.0D, d1);
/* 1119 */       if (Double.isNaN(d2)) d2 = copysign(0.0D, d2);
/* 1120 */       i = 1;
/*      */     }
/*      */     
/* 1123 */     if ((i == 0) && (
/* 1124 */       (Double.isInfinite(d1 * d3)) || (Double.isInfinite(d2 * d4)) || (Double.isInfinite(d1 * d4)) || (Double.isInfinite(d2 * d3))))
/*      */     {
/*      */ 
/* 1127 */       if (Double.isNaN(d1)) d1 = copysign(0.0D, d1);
/* 1128 */       if (Double.isNaN(d2)) d2 = copysign(0.0D, d2);
/* 1129 */       if (Double.isNaN(d3)) d3 = copysign(0.0D, d3);
/* 1130 */       if (Double.isNaN(d4)) d4 = copysign(0.0D, d4);
/* 1131 */       i = 1;
/*      */     }
/*      */     
/*      */ 
/* 1135 */     if (i != 0) {
/* 1136 */       paramComplex3.re = (Double.POSITIVE_INFINITY * (d1 * d3 - d2 * d4));
/* 1137 */       paramComplex3.im = (Double.POSITIVE_INFINITY * (d1 * d4 + d2 * d3));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex timesReverse(double paramDouble)
/*      */   {
/* 1147 */     return new Complex(paramDouble * this.re, paramDouble * this.im);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1155 */     if (this.im == 0.0D) {
/* 1156 */       return String.valueOf(this.re);
/*      */     }
/* 1158 */     if (this.re == 0.0D) {
/* 1159 */       return String.valueOf(this.im) + suffix;
/*      */     }
/* 1161 */     String str = this.im < 0.0D ? "" : "+";
/* 1162 */     return String.valueOf(this.re) + str + String.valueOf(this.im) + suffix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex valueOf(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 1174 */     String str = paramString.trim();
/* 1175 */     int i = 0;
/* 1176 */     Complex localComplex = new Complex();
/* 1177 */     int j = 0;
/* 1178 */     int k = 1;
/* 1179 */     int m = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1190 */     for (int n = 0; n < str.length(); n++)
/*      */     {
/* 1192 */       int i1 = str.charAt(n);
/*      */       
/* 1194 */       switch (i1) {
/*      */       case 48: case 49: case 50: case 51: 
/*      */       case 52: case 53: case 54: 
/*      */       case 55: case 56: case 57: 
/* 1198 */         if ((j == 0) || (j == 1)) {
/* 1199 */           j = 2;
/* 1200 */         } else if (j == 4) {
/* 1201 */           j = 5;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 43: 
/*      */       case 45: 
/* 1207 */         k = i1 == 43 ? 1 : -1;
/* 1208 */         if (j == 0) {
/* 1209 */           j = 1;
/* 1210 */         } else if (j == 4) {
/* 1211 */           j = 5;
/*      */         }
/* 1213 */         else if (m == 0)
/*      */         {
/* 1215 */           localComplex.re = Double.valueOf(str.substring(i, n)).doubleValue();
/* 1216 */           m = 1;
/*      */           
/* 1218 */           i = n;
/* 1219 */           j = 1;
/*      */         } else {
/* 1221 */           throw new NumberFormatException(str);
/*      */         }
/*      */         
/*      */ 
/*      */         break;
/*      */       case 46: 
/* 1227 */         if ((j == 0) || (j == 1) || (j == 2)) {
/* 1228 */           j = 3;
/*      */         } else {
/* 1230 */           throw new NumberFormatException(str);
/*      */         }
/*      */         break;
/*      */       case 73: case 74: 
/*      */       case 105: case 106: 
/* 1235 */         if (n + 1 != str.length())
/* 1236 */           throw new NumberFormatException(str);
/* 1237 */         if ((j == 0) || (j == 1)) {
/* 1238 */           localComplex.im = k;
/* 1239 */           return localComplex; }
/* 1240 */         if ((j == 2) || (j == 3) || (j == 5)) {
/* 1241 */           localComplex.im = Double.valueOf(str.substring(i, n)).doubleValue();
/* 1242 */           return localComplex;
/*      */         }
/* 1244 */         throw new NumberFormatException(str);
/*      */       case 68: 
/*      */       case 69: 
/*      */       case 100: 
/*      */       case 101: 
/* 1249 */         if ((j == 2) || (j == 3)) {
/* 1250 */           j = 4;
/*      */         } else {
/* 1252 */           throw new NumberFormatException(str);
/*      */         }
/*      */         break;
/*      */       case 44: case 47: case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 70: case 71: case 72: case 75: case 76: case 77: case 78: case 79: case 80: case 81: 
/*      */       case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99: case 102: case 103: case 104: default: 
/* 1257 */         throw new NumberFormatException(str);
/*      */       }
/*      */       
/*      */     }
/*      */     
/* 1262 */     if (m == 0) {
/* 1263 */       localComplex.re = Double.valueOf(str).doubleValue();
/* 1264 */       return localComplex;
/*      */     }
/* 1266 */     throw new NumberFormatException(str);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/imsl/math/Complex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */