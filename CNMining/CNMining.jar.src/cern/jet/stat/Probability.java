/*     */ package cern.jet.stat;
/*     */ 
/*     */ import cern.jet.math.Constants;
/*     */ import cern.jet.math.Polynomial;
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
/*     */ public class Probability
/*     */   extends Constants
/*     */ {
/*  30 */   protected static final double[] P0 = { -59.96335010141079D, 98.00107541859997D, -56.67628574690703D, 13.931260938727968D, -1.2391658386738125D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  37 */   protected static final double[] Q0 = { 1.9544885833814176D, 4.676279128988815D, 86.36024213908905D, -225.46268785411937D, 200.26021238006066D, -82.03722561683334D, 15.90562251262117D, -1.1833162112133D };
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
/*  53 */   protected static final double[] P1 = { 4.0554489230596245D, 31.525109459989388D, 57.16281922464213D, 44.08050738932008D, 14.684956192885803D, 2.1866330685079025D, -0.1402560791713545D, -0.03504246268278482D, -8.574567851546854E-4D };
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
/*  64 */   protected static final double[] Q1 = { 15.779988325646675D, 45.39076351288792D, 41.3172038254672D, 15.04253856929075D, 2.504649462083094D, -0.14218292285478779D, -0.03808064076915783D, -9.332594808954574E-4D };
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
/*  79 */   protected static final double[] P2 = { 3.2377489177694603D, 6.915228890689842D, 3.9388102529247444D, 1.3330346081580755D, 0.20148538954917908D, 0.012371663481782003D, 3.0158155350823543E-4D, 2.6580697468673755E-6D, 6.239745391849833E-9D };
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
/*  90 */   protected static final double[] Q2 = { 6.02427039364742D, 3.6798356385616087D, 1.3770209948908132D, 0.21623699359449663D, 0.013420400608854318D, 3.2801446468212774E-4D, 2.8924786474538068E-6D, 6.790194080099813E-9D };
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
/*     */   public static double beta(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 127 */     return Gamma.incompleteBeta(paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double betaComplemented(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 137 */     return Gamma.incompleteBeta(paramDouble2, paramDouble1, paramDouble3);
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
/*     */   public static double binomial(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 160 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) throw new IllegalArgumentException();
/* 161 */     if ((paramInt1 < 0) || (paramInt2 < paramInt1)) { throw new IllegalArgumentException();
/*     */     }
/* 163 */     if (paramInt1 == paramInt2) return 1.0D;
/* 164 */     if (paramInt1 == 0) { return Math.pow(1.0D - paramDouble, paramInt2 - paramInt1);
/*     */     }
/* 166 */     return Gamma.incompleteBeta(paramInt2 - paramInt1, paramInt1 + 1, 1.0D - paramDouble);
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
/*     */   public static double binomialComplemented(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 189 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) throw new IllegalArgumentException();
/* 190 */     if ((paramInt1 < 0) || (paramInt2 < paramInt1)) { throw new IllegalArgumentException();
/*     */     }
/* 192 */     if (paramInt1 == paramInt2) return 0.0D;
/* 193 */     if (paramInt1 == 0) { return 1.0D - Math.pow(1.0D - paramDouble, paramInt2 - paramInt1);
/*     */     }
/* 195 */     return Gamma.incompleteBeta(paramInt1 + 1, paramInt2 - paramInt1, paramDouble);
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
/*     */   public static double chiSquare(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/* 223 */     if ((paramDouble2 < 0.0D) || (paramDouble1 < 1.0D)) return 0.0D;
/* 224 */     return Gamma.incompleteGamma(paramDouble1 / 2.0D, paramDouble2 / 2.0D);
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
/*     */   public static double chiSquareComplemented(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/* 252 */     if ((paramDouble2 < 0.0D) || (paramDouble1 < 1.0D)) return 0.0D;
/* 253 */     return Gamma.incompleteGammaComplement(paramDouble1 / 2.0D, paramDouble2 / 2.0D);
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
/*     */   public static double errorFunction(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 278 */     double[] arrayOfDouble1 = { 9.604973739870516D, 90.02601972038427D, 2232.005345946843D, 7003.325141128051D, 55592.30130103949D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 285 */     double[] arrayOfDouble2 = { 33.56171416475031D, 521.3579497801527D, 4594.323829709801D, 22629.000061389095D, 49267.39426086359D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 294 */     if (Math.abs(paramDouble) > 1.0D) return 1.0D - errorFunctionComplemented(paramDouble);
/* 295 */     double d2 = paramDouble * paramDouble;
/* 296 */     double d1 = paramDouble * Polynomial.polevl(d2, arrayOfDouble1, 4) / Polynomial.p1evl(d2, arrayOfDouble2, 5);
/* 297 */     return d1;
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
/*     */   public static double errorFunctionComplemented(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 324 */     double[] arrayOfDouble1 = { 2.461969814735305E-10D, 0.5641895648310689D, 7.463210564422699D, 48.63719709856814D, 196.5208329560771D, 526.4451949954773D, 934.5285271719576D, 1027.5518868951572D, 557.5353353693994D };
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
/* 335 */     double[] arrayOfDouble2 = { 13.228195115474499D, 86.70721408859897D, 354.9377788878199D, 975.7085017432055D, 1823.9091668790973D, 2246.3376081871097D, 1656.6630919416134D, 557.5353408177277D };
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
/* 347 */     double[] arrayOfDouble3 = { 0.5641895835477551D, 1.275366707599781D, 5.019050422511805D, 6.160210979930536D, 7.4097426995044895D, 2.9788666537210022D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 355 */     double[] arrayOfDouble4 = { 2.2605286322011726D, 9.396035249380015D, 12.048953980809666D, 17.08144507475659D, 9.608968090632859D, 3.369076451000815D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     double d1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 365 */     if (paramDouble < 0.0D) d1 = -paramDouble; else {
/* 366 */       d1 = paramDouble;
/*     */     }
/* 368 */     if (d1 < 1.0D) { return 1.0D - errorFunction(paramDouble);
/*     */     }
/* 370 */     double d3 = -paramDouble * paramDouble;
/*     */     
/* 372 */     if (d3 < -709.782712893384D) {
/* 373 */       if (paramDouble < 0.0D) return 2.0D;
/* 374 */       return 0.0D;
/*     */     }
/*     */     
/* 377 */     d3 = Math.exp(d3);
/*     */     double d4;
/* 379 */     double d5; if (d1 < 8.0D) {
/* 380 */       d4 = Polynomial.polevl(d1, arrayOfDouble1, 8);
/* 381 */       d5 = Polynomial.p1evl(d1, arrayOfDouble2, 8);
/*     */     } else {
/* 383 */       d4 = Polynomial.polevl(d1, arrayOfDouble3, 5);
/* 384 */       d5 = Polynomial.p1evl(d1, arrayOfDouble4, 6);
/*     */     }
/*     */     
/* 387 */     double d2 = d3 * d4 / d5;
/*     */     
/* 389 */     if (paramDouble < 0.0D) { d2 = 2.0D - d2;
/*     */     }
/* 391 */     if (d2 == 0.0D) {
/* 392 */       if (paramDouble < 0.0D) return 2.0D;
/* 393 */       return 0.0D;
/*     */     }
/*     */     
/* 396 */     return d2;
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
/*     */   public static double gamma(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 420 */     if (paramDouble3 < 0.0D) return 0.0D;
/* 421 */     return Gamma.incompleteGamma(paramDouble2, paramDouble1 * paramDouble3);
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
/*     */   public static double gammaComplemented(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 445 */     if (paramDouble3 < 0.0D) return 0.0D;
/* 446 */     return Gamma.incompleteGammaComplement(paramDouble2, paramDouble1 * paramDouble3);
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
/*     */   public static double negativeBinomial(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 471 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) throw new IllegalArgumentException();
/* 472 */     if (paramInt1 < 0) { return 0.0D;
/*     */     }
/* 474 */     return Gamma.incompleteBeta(paramInt2, paramInt1 + 1, paramDouble);
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
/*     */   public static double negativeBinomialComplemented(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 497 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) throw new IllegalArgumentException();
/* 498 */     if (paramInt1 < 0) { return 0.0D;
/*     */     }
/* 500 */     return Gamma.incompleteBeta(paramInt1 + 1, paramInt2, 1.0D - paramDouble);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double normal(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 523 */     double d1 = paramDouble * 0.7071067811865476D;
/* 524 */     double d3 = Math.abs(d1);
/*     */     double d2;
/* 526 */     if (d3 < 0.7071067811865476D) { d2 = 0.5D + 0.5D * errorFunction(d1);
/*     */     } else {
/* 528 */       d2 = 0.5D * errorFunctionComplemented(d3);
/* 529 */       if (d1 > 0.0D) { d2 = 1.0D - d2;
/*     */       }
/*     */     }
/* 532 */     return d2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double normal(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     throws ArithmeticException
/*     */   {
/* 555 */     if (paramDouble3 > 0.0D) {
/* 556 */       return 0.5D + 0.5D * errorFunction((paramDouble3 - paramDouble1) / Math.sqrt(2.0D * paramDouble2));
/*     */     }
/* 558 */     return 0.5D - 0.5D * errorFunction(-(paramDouble3 - paramDouble1) / Math.sqrt(2.0D * paramDouble2));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double normalInverse(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 578 */     double d7 = Math.sqrt(6.283185307179586D);
/*     */     
/* 580 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/* 581 */     if (paramDouble >= 1.0D) throw new IllegalArgumentException();
/* 582 */     int i = 1;
/* 583 */     double d2 = paramDouble;
/* 584 */     if (d2 > 0.8646647167633873D) {
/* 585 */       d2 = 1.0D - d2;
/* 586 */       i = 0;
/*     */     }
/*     */     
/* 589 */     if (d2 > 0.1353352832366127D) {
/* 590 */       d2 -= 0.5D;
/* 591 */       double d4 = d2 * d2;
/* 592 */       d1 = d2 + d2 * (d4 * Polynomial.polevl(d4, P0, 4) / Polynomial.p1evl(d4, Q0, 8));
/* 593 */       d1 *= d7;
/* 594 */       return d1;
/*     */     }
/*     */     
/* 597 */     double d1 = Math.sqrt(-2.0D * Math.log(d2));
/* 598 */     double d5 = d1 - Math.log(d1) / d1;
/*     */     
/* 600 */     double d3 = 1.0D / d1;
/* 601 */     double d6; if (d1 < 8.0D) {
/* 602 */       d6 = d3 * Polynomial.polevl(d3, P1, 8) / Polynomial.p1evl(d3, Q1, 8);
/*     */     } else
/* 604 */       d6 = d3 * Polynomial.polevl(d3, P2, 8) / Polynomial.p1evl(d3, Q2, 8);
/* 605 */     d1 = d5 - d6;
/* 606 */     if (i != 0)
/* 607 */       d1 = -d1;
/* 608 */     return d1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double poisson(int paramInt, double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 630 */     if (paramDouble < 0.0D) throw new IllegalArgumentException();
/* 631 */     if (paramInt < 0) return 0.0D;
/* 632 */     return Gamma.incompleteGammaComplement(paramInt + 1, paramDouble);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double poissonComplemented(int paramInt, double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 654 */     if (paramDouble < 0.0D) throw new IllegalArgumentException();
/* 655 */     if (paramInt < -1) return 0.0D;
/* 656 */     return Gamma.incompleteGamma(paramInt + 1, paramDouble);
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
/*     */   public static double studentT(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/* 687 */     if (paramDouble1 <= 0.0D) throw new IllegalArgumentException();
/* 688 */     if (paramDouble2 == 0.0D) { return 0.5D;
/*     */     }
/* 690 */     double d = 0.5D * Gamma.incompleteBeta(0.5D * paramDouble1, 0.5D, paramDouble1 / (paramDouble1 + paramDouble2 * paramDouble2));
/*     */     
/* 692 */     if (paramDouble2 >= 0.0D) { d = 1.0D - d;
/*     */     }
/* 694 */     return d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double studentTInverse(double paramDouble, int paramInt)
/*     */   {
/* 710 */     double d1 = 1.0D - paramDouble / 2.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 715 */     d1 = 1.0D - paramDouble / 2.0D;
/* 716 */     double d5 = normalInverse(d1);
/*     */     
/*     */ 
/* 719 */     if (paramInt > 200) {
/* 720 */       return d5;
/*     */     }
/*     */     
/*     */ 
/* 724 */     double d2 = studentT(paramInt, d5) - d1;
/* 725 */     double d6 = d5;double d3 = d2;
/*     */     do {
/* 727 */       if (d2 > 0.0D) {
/* 728 */         d6 /= 2.0D;
/*     */       } else {
/* 730 */         d6 += d5;
/*     */       }
/* 732 */       d3 = studentT(paramInt, d6) - d1;
/* 733 */     } while (d2 * d3 > 0.0D);
/*     */     
/*     */ 
/*     */ 
/*     */     do
/*     */     {
/* 739 */       double d9 = (d3 - d2) / (d6 - d5);
/* 740 */       double d7 = d6 - d3 / d9;
/*     */       
/*     */ 
/* 743 */       double d4 = studentT(paramInt, d7) - d1;
/* 744 */       if (Math.abs(d4) < 1.0E-8D)
/*     */       {
/* 746 */         return d7;
/*     */       }
/*     */       
/* 749 */       if (d4 * d3 < 0.0D) {
/* 750 */         d5 = d6;d2 = d3;
/* 751 */         d6 = d7;d3 = d4;
/*     */       } else {
/* 753 */         double d8 = d3 / (d3 + d4);
/* 754 */         d2 = d8 * d2;
/* 755 */         d6 = d7;d3 = d4;
/*     */       }
/* 757 */     } while (Math.abs(d6 - d5) > 0.001D);
/*     */     
/* 759 */     if (Math.abs(d3) <= Math.abs(d2)) {
/* 760 */       return d6;
/*     */     }
/* 762 */     return d5;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/Probability.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */