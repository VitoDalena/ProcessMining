/*       */ package flanagan.analysis;
/*       */ 
/*       */ import flanagan.circuits.Phasor;
/*       */ import flanagan.complex.Complex;
/*       */ import flanagan.integration.Integration;
/*       */ import flanagan.io.PrintToScreen;
/*       */ import flanagan.math.ArrayMaths;
/*       */ import flanagan.math.Fmath;
/*       */ import flanagan.math.Matrix;
/*       */ import flanagan.math.PsRandom;
/*       */ import flanagan.plot.PlotGraph;
/*       */ import flanagan.roots.RealRoot;
/*       */ import java.io.PrintStream;
/*       */ import java.math.BigDecimal;
/*       */ import java.math.BigInteger;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Random;
/*       */ import java.util.Vector;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public class Stat
/*       */   extends ArrayMaths
/*       */ {
/*    64 */   private boolean nFactorOptionI = false;
/*       */   
/*    66 */   private boolean nFactorReset = false;
/*       */   
/*    68 */   private boolean nEffOptionI = true;
/*       */   
/*    70 */   private boolean nEffReset = false;
/*       */   
/*    72 */   private boolean weightingOptionI = true;
/*       */   
/*    74 */   private boolean weightingReset = false;
/*       */   
/*    76 */   private ArrayMaths amWeights = null;
/*    77 */   private boolean weightsSupplied = false;
/*       */   
/*    79 */   private ArrayList<Object> upperOutlierDetails = new ArrayList();
/*       */   
/*       */ 
/*       */ 
/*    83 */   private boolean upperDone = false;
/*       */   
/*    85 */   private ArrayList<Object> lowerOutlierDetails = new ArrayList();
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*    90 */   private boolean lowerDone = false;
/*       */   
/*       */ 
/*       */ 
/*    94 */   private static boolean nFactorOptionS = false;
/*       */   
/*    96 */   private static boolean nEffOptionS = true;
/*       */   
/*       */ 
/*    99 */   private static boolean weightingOptionS = true;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static final double FPMIN = 1.0E-300D;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   109 */   private static int lgfN = 6;
/*       */   
/*   111 */   private static double[] lgfCoeff = { 1.000000000190015D, 76.18009172947146D, -86.50532032941678D, 24.01409824083091D, -1.231739572450155D, 0.001208650973866179D, -5.395239384953E-6D };
/*       */   
/*   113 */   private static double lgfGamma = 5.0D;
/*       */   
/*   115 */   private static int igfiter = 1000;
/*       */   
/*   117 */   private static double igfeps = 1.0E-8D;
/*       */   
/*       */ 
/*       */ 
/*   121 */   private static double histTol = 1.0001D;
/*       */   
/*       */ 
/*       */   public Stat() {}
/*       */   
/*       */ 
/*       */   public Stat(double[] xx)
/*       */   {
/*   129 */     super(xx);
/*   130 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Double[] xx) {
/*   134 */     super(xx);
/*   135 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(float[] xx) {
/*   139 */     super(xx);
/*   140 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Float[] xx) {
/*   144 */     super(xx);
/*   145 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(long[] xx) {
/*   149 */     super(xx);
/*   150 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Long[] xx) {
/*   154 */     super(xx);
/*   155 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(int[] xx) {
/*   159 */     super(xx);
/*   160 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Integer[] xx) {
/*   164 */     super(xx);
/*   165 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(short[] xx) {
/*   169 */     super(xx);
/*   170 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Short[] xx) {
/*   174 */     super(xx);
/*   175 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(byte[] xx) {
/*   179 */     super(xx);
/*   180 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Byte[] xx) {
/*   184 */     super(xx);
/*   185 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(BigDecimal[] xx) {
/*   189 */     super(xx);
/*       */   }
/*       */   
/*       */   public Stat(BigInteger[] xx) {
/*   193 */     super(xx);
/*   194 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Complex[] xx) {
/*   198 */     super(xx);
/*   199 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Phasor[] xx) {
/*   203 */     super(xx);
/*   204 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(String[] xx) {
/*   208 */     super(xx);
/*   209 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Object[] xx) {
/*   213 */     super(xx);
/*   214 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(Vector<Object> xx) {
/*   218 */     super(xx);
/*   219 */     convertToHighest();
/*       */   }
/*       */   
/*       */   public Stat(ArrayList<Object> xx) {
/*   223 */     super(xx);
/*   224 */     convertToHighest();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void convertToHighest()
/*       */   {
/*   232 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*   247 */       Double[] dd = getArray_as_Double();
/*   248 */       this.array.clear();
/*   249 */       for (int i = 0; i < this.length; i++) this.array.add(dd[i]);
/*   250 */       double[] ww = new double[this.length];
/*   251 */       for (int i = 0; i < this.length; i++) ww[i] = 1.0D;
/*   252 */       this.amWeights = new ArrayMaths(ww);
/*   253 */       this.type = 1;
/*   254 */       break;
/*       */     case 12: case 13: 
/*   256 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*   257 */       this.array.clear();
/*   258 */       for (int i = 0; i < this.length; i++) this.array.add(bd[i]);
/*   259 */       BigDecimal[] wd = new BigDecimal[this.length];
/*   260 */       for (int i = 0; i < this.length; i++) wd[i] = BigDecimal.ONE;
/*   261 */       this.amWeights = new ArrayMaths(wd);
/*   262 */       this.type = 12;
/*   263 */       bd = null;
/*   264 */       break;
/*       */     case 14: case 15: 
/*   266 */       Complex[] cc = getArray_as_Complex();
/*   267 */       this.array.clear();
/*   268 */       for (int i = 0; i < this.length; i++) this.array.add(cc[i]);
/*   269 */       Complex[] wc = new Complex[this.length];
/*   270 */       for (int i = 0; i < this.length; i++) wc[i] = Complex.plusOne();
/*   271 */       this.amWeights = new ArrayMaths(wc);
/*   272 */       this.type = 14;
/*   273 */       break;
/*   274 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*       */     
/*       */   }
/*       */   
/*       */ 
/*       */   public void setWeightsToBigW()
/*       */   {
/*   282 */     this.weightingOptionI = false;
/*   283 */     this.weightingReset = true;
/*       */   }
/*       */   
/*       */   public void setWeightsToLittleW()
/*       */   {
/*   288 */     this.weightingOptionI = true;
/*   289 */     this.weightingReset = true;
/*       */   }
/*       */   
/*       */   public void setDenominatorToN()
/*       */   {
/*   294 */     this.nFactorOptionI = true;
/*   295 */     this.nFactorReset = true;
/*       */   }
/*       */   
/*       */   public void setDenominatorToNminusOne()
/*       */   {
/*   300 */     this.nFactorOptionI = false;
/*   301 */     this.nFactorReset = true;
/*       */   }
/*       */   
/*       */   public void useEffectiveN()
/*       */   {
/*   306 */     this.nEffOptionI = true;
/*   307 */     this.nEffReset = true;
/*       */   }
/*       */   
/*       */   public void useTrueN()
/*       */   {
/*   312 */     this.nEffOptionI = false;
/*   313 */     this.nEffReset = true;
/*       */   }
/*       */   
/*       */   public double effectiveSampleNumber()
/*       */   {
/*   318 */     return effectiveSampleNumber_as_double();
/*       */   }
/*       */   
/*       */   public double effectiveSampleNumber_as_double()
/*       */   {
/*   323 */     boolean holdW = weightingOptionS;
/*   324 */     if (this.weightingReset) {
/*   325 */       if (this.weightingOptionI) {
/*   326 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   329 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   332 */     double nEff = 0.0D;
/*   333 */     switch (this.type) {
/*   334 */     case 1:  double[] dd = getArray_as_double();
/*   335 */       nEff = effectiveSampleNumber(dd);
/*   336 */       break;
/*   337 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   338 */       nEff = effectiveSampleNumber(bd).doubleValue();
/*   339 */       bd = null;
/*   340 */       break;
/*   341 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*   342 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   344 */     weightingOptionS = holdW;
/*   345 */     return nEff;
/*       */   }
/*       */   
/*       */   public BigDecimal effectiveSampleNumber_as_BigDecimal() {
/*   349 */     boolean holdW = weightingOptionS;
/*   350 */     if (this.weightingReset) {
/*   351 */       if (this.weightingOptionI) {
/*   352 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   355 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   358 */     BigDecimal nEff = BigDecimal.ZERO;
/*   359 */     switch (this.type) {
/*       */     case 1: case 12: 
/*   361 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*   362 */       nEff = effectiveSampleNumber(bd);
/*   363 */       bd = null;
/*   364 */       break;
/*   365 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*   366 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   368 */     weightingOptionS = holdW;
/*   369 */     return nEff;
/*       */   }
/*       */   
/*       */   public Complex effectiveSampleNumber_as_Complex() {
/*   373 */     boolean holdW = weightingOptionS;
/*   374 */     if (this.weightingReset) {
/*   375 */       if (this.weightingOptionI) {
/*   376 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   379 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   382 */     Complex nEff = Complex.zero();
/*   383 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*   386 */       Complex[] cc = getArray_as_Complex();
/*   387 */       nEff = effectiveSampleNumber(cc);
/*   388 */       break;
/*   389 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   391 */     weightingOptionS = holdW;
/*   392 */     return nEff;
/*       */   }
/*       */   
/*       */   public int trueSampleNumber()
/*       */   {
/*   397 */     return this.length;
/*       */   }
/*       */   
/*       */   public int trueSampleNumber_as_int()
/*       */   {
/*   402 */     return this.length;
/*       */   }
/*       */   
/*       */   public double trueSampleNumber_as_double() {
/*   406 */     return this.length;
/*       */   }
/*       */   
/*       */   public BigDecimal trueSampleNumber_as_BigDecimal() {
/*   410 */     return new BigDecimal(new Integer(this.length).toString());
/*       */   }
/*       */   
/*       */   public Complex trueSampleNumber_as_Complex() {
/*   414 */     return new Complex(this.length, 0.0D);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void convertBigWtoLittleW()
/*       */   {
/*   422 */     if (!this.weightsSupplied) {
/*   423 */       System.out.println("convertBigWtoLittleW: no weights have been supplied - all weights set to unity");
/*       */     }
/*       */     else {
/*   426 */       this.amWeights = this.amWeights.oneOverSqrt();
/*       */     }
/*       */   }
/*       */   
/*       */   public void setWeights(double[] xx)
/*       */   {
/*   432 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   433 */     ArrayMaths wm = new ArrayMaths(xx);
/*   434 */     convertWeights(wm);
/*   435 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Double[] xx) {
/*   439 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   440 */     ArrayMaths wm = new ArrayMaths(xx);
/*   441 */     convertWeights(wm);
/*   442 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(float[] xx) {
/*   446 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   447 */     ArrayMaths wm = new ArrayMaths(xx);
/*   448 */     convertWeights(wm);
/*   449 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Float[] xx) {
/*   453 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   454 */     ArrayMaths wm = new ArrayMaths(xx);
/*   455 */     convertWeights(wm);
/*   456 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(long[] xx) {
/*   460 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   461 */     ArrayMaths wm = new ArrayMaths(xx);
/*   462 */     convertWeights(wm);
/*   463 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Long[] xx) {
/*   467 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   468 */     ArrayMaths wm = new ArrayMaths(xx);
/*   469 */     convertWeights(wm);
/*   470 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(int[] xx) {
/*   474 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   475 */     ArrayMaths wm = new ArrayMaths(xx);
/*   476 */     convertWeights(wm);
/*   477 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Integer[] xx) {
/*   481 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   482 */     ArrayMaths wm = new ArrayMaths(xx);
/*   483 */     convertWeights(wm);
/*   484 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(short[] xx) {
/*   488 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   489 */     ArrayMaths wm = new ArrayMaths(xx);
/*   490 */     convertWeights(wm);
/*   491 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Short[] xx) {
/*   495 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   496 */     ArrayMaths wm = new ArrayMaths(xx);
/*   497 */     convertWeights(wm);
/*   498 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(byte[] xx) {
/*   502 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   503 */     ArrayMaths wm = new ArrayMaths(xx);
/*   504 */     convertWeights(wm);
/*   505 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Byte[] xx) {
/*   509 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   510 */     ArrayMaths wm = new ArrayMaths(xx);
/*   511 */     convertWeights(wm);
/*   512 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(BigDecimal[] xx) {
/*   516 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   517 */     ArrayMaths wm = new ArrayMaths(xx);
/*   518 */     convertWeights(wm);
/*   519 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(BigInteger[] xx) {
/*   523 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   524 */     ArrayMaths wm = new ArrayMaths(xx);
/*   525 */     convertWeights(wm);
/*   526 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Complex[] xx) {
/*   530 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   531 */     ArrayMaths wm = new ArrayMaths(xx);
/*   532 */     convertWeights(wm);
/*   533 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Phasor[] xx) {
/*   537 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   538 */     ArrayMaths wm = new ArrayMaths(xx);
/*   539 */     convertWeights(wm);
/*   540 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Object[] xx) {
/*   544 */     if (this.length != xx.length) throw new IllegalArgumentException("Length of weights array, " + xx.length + ", must be the same as the length of the instance internal array, " + this.length);
/*   545 */     ArrayMaths wm = new ArrayMaths(xx);
/*   546 */     convertWeights(wm);
/*   547 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(Vector<Object> xx) {
/*   551 */     if (this.length != xx.size()) throw new IllegalArgumentException("Length of weights array, " + xx.size() + ", must be the same as the length of the instance internal array, " + this.length);
/*   552 */     ArrayMaths wm = new ArrayMaths(xx);
/*   553 */     convertWeights(wm);
/*   554 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   public void setWeights(ArrayList<Object> xx) {
/*   558 */     if (this.length != xx.size()) throw new IllegalArgumentException("Length of weights array, " + xx.size() + ", must be the same as the length of the instance internal array, " + this.length);
/*   559 */     ArrayMaths wm = new ArrayMaths(xx);
/*   560 */     convertWeights(wm);
/*   561 */     this.weightsSupplied = true;
/*       */   }
/*       */   
/*       */   private void convertWeights(ArrayMaths wm) {
/*   565 */     switch (this.type) {
/*   566 */     case 1:  switch (wm.typeIndex()) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 4: 
/*       */       case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*   578 */         Double[] w1 = wm.getArray_as_Double();
/*   579 */         this.amWeights = new ArrayMaths(w1);
/*   580 */         break;
/*       */       case 12: case 13: 
/*   582 */         BigDecimal[] a2 = getArray_as_BigDecimal();
/*   583 */         for (int i = 0; i < this.length; i++) this.array.add(a2[i]);
/*   584 */         BigDecimal[] w2 = wm.getArray_as_BigDecimal();
/*   585 */         this.amWeights = new ArrayMaths(w2);
/*   586 */         a2 = null;
/*   587 */         w2 = null;
/*   588 */         break;
/*       */       case 14: case 15: 
/*   590 */         Complex[] a3 = getArray_as_Complex();
/*   591 */         for (int i = 0; i < this.length; i++) this.array.add(a3[i]);
/*   592 */         Complex[] w3 = wm.getArray_as_Complex();
/*   593 */         this.amWeights = new ArrayMaths(w3);
/*   594 */         break;
/*   595 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*       */       
/*   598 */       break;
/*   599 */     case 12:  switch (wm.typeIndex()) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 4: 
/*       */       case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*   611 */         BigDecimal[] w4 = wm.getArray_as_BigDecimal();
/*   612 */         this.amWeights = new ArrayMaths(w4);
/*   613 */         w4 = null;
/*   614 */         break;
/*       */       case 12: case 13: 
/*   616 */         BigDecimal[] w5 = wm.getArray_as_BigDecimal();
/*   617 */         this.amWeights = new ArrayMaths(w5);
/*   618 */         w5 = null;
/*   619 */         break;
/*       */       case 14: case 15: 
/*   621 */         Complex[] a6 = getArray_as_Complex();
/*   622 */         for (int i = 0; i < this.length; i++) this.array.add(a6[i]);
/*   623 */         Complex[] w6 = wm.getArray_as_Complex();
/*   624 */         this.amWeights = new ArrayMaths(w6);
/*   625 */         break;
/*   626 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*   628 */       break;
/*   629 */     case 14:  Complex[] a7 = getArray_as_Complex();
/*   630 */       for (int i = 0; i < this.length; i++) this.array.add(a7[i]);
/*   631 */       Complex[] w7 = wm.getArray_as_Complex();
/*   632 */       this.amWeights = new ArrayMaths(w7);
/*   633 */       break;
/*   634 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*       */   }
/*       */   
/*       */   public double mean()
/*       */   {
/*   640 */     return mean_as_double();
/*       */   }
/*       */   
/*       */   public double mean_as_double()
/*       */   {
/*   645 */     double mean = 0.0D;
/*   646 */     switch (this.type) {
/*   647 */     case 1:  double[] dd = getArray_as_double();
/*   648 */       for (int i = 0; i < this.length; i++) {
/*   649 */         mean += dd[i];
/*       */       }
/*   651 */       mean /= this.length;
/*   652 */       break;
/*   653 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   654 */       BigDecimal meanbd = BigDecimal.ZERO;
/*   655 */       for (int i = 0; i < this.length; i++) meanbd = meanbd.add(bd[i]);
/*   656 */       meanbd = meanbd.divide(new BigDecimal(this.length), 4);
/*   657 */       mean = meanbd.doubleValue();
/*   658 */       bd = null;
/*   659 */       meanbd = null;
/*   660 */       break;
/*   661 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*   662 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   664 */     return mean;
/*       */   }
/*       */   
/*       */   public BigDecimal mean_as_BigDecimal() {
/*   668 */     BigDecimal mean = BigDecimal.ZERO;
/*   669 */     switch (this.type) {
/*   670 */     case 1:  double[] dd = getArray_as_double();
/*   671 */       double meand = 0.0D;
/*   672 */       for (int i = 0; i < this.length; i++) meand += dd[i];
/*   673 */       meand /= this.length;
/*   674 */       mean = new BigDecimal(meand);
/*   675 */       break;
/*   676 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   677 */       for (int i = 0; i < this.length; i++) mean = mean.add(bd[i]);
/*   678 */       mean = mean.divide(new BigDecimal(this.length), 4);
/*   679 */       bd = null;
/*   680 */       break;
/*   681 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*   682 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   684 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex mean_as_Complex() {
/*   688 */     Complex mean = Complex.zero();
/*   689 */     switch (this.type) {
/*   690 */     case 1:  double[] dd = getArray_as_double();
/*   691 */       double meand = 0.0D;
/*   692 */       for (int i = 0; i < this.length; i++) meand += dd[i];
/*   693 */       meand /= this.length;
/*   694 */       mean = new Complex(meand);
/*   695 */       break;
/*   696 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   697 */       BigDecimal meanbd = BigDecimal.ZERO;
/*   698 */       for (int i = 0; i < this.length; i++) meanbd = meanbd.add(bd[i]);
/*   699 */       meanbd = meanbd.divide(new BigDecimal(this.length), 4);
/*   700 */       mean = new Complex(meanbd.doubleValue());
/*   701 */       bd = null;
/*   702 */       meanbd = null;
/*   703 */       break;
/*   704 */     case 14:  Complex[] cc = getArray_as_Complex();
/*   705 */       for (int i = 0; i < this.length; i++) mean = mean.plus(cc[i]);
/*   706 */       mean = mean.over(new Complex(this.length));
/*   707 */       break;
/*   708 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   710 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double weightedMean()
/*       */   {
/*   716 */     return weightedMean_as_double();
/*       */   }
/*       */   
/*       */   public double weightedMean_as_double() {
/*   720 */     if (!this.weightsSupplied) {
/*   721 */       System.out.println("weightedMean_as_double: no weights supplied - unweighted mean returned");
/*   722 */       return mean_as_double();
/*       */     }
/*       */     
/*   725 */     boolean holdW = weightingOptionS;
/*   726 */     if (this.weightingReset) {
/*   727 */       if (this.weightingOptionI) {
/*   728 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   731 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   734 */     double mean = 0.0D;
/*   735 */     switch (this.type) {
/*   736 */     case 1:  double[] dd = getArray_as_double();
/*   737 */       double[] wwd = this.amWeights.getArray_as_double();
/*   738 */       mean = mean(dd, wwd);
/*   739 */       break;
/*   740 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   741 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*   742 */       mean = mean(bd, wwb).doubleValue();
/*   743 */       bd = null;
/*   744 */       wwb = null;
/*   745 */       break;
/*   746 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*   747 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   749 */     weightingOptionS = holdW;
/*   750 */     return mean;
/*       */   }
/*       */   
/*       */   public BigDecimal weightedMean_as_BigDecimal()
/*       */   {
/*   755 */     if (!this.weightsSupplied) {
/*   756 */       System.out.println("weightedMean_as_BigDecimal: no weights supplied - unweighted mean returned");
/*   757 */       return mean_as_BigDecimal();
/*       */     }
/*       */     
/*   760 */     boolean holdW = weightingOptionS;
/*   761 */     if (this.weightingReset) {
/*   762 */       if (this.weightingOptionI) {
/*   763 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   766 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   769 */     BigDecimal mean = BigDecimal.ZERO;
/*   770 */     switch (this.type) {
/*       */     case 1: case 12: 
/*   772 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*   773 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*   774 */       mean = mean(bd, wwb);
/*   775 */       bd = null;
/*   776 */       wwb = null;
/*   777 */       break;
/*   778 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*   779 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   781 */     weightingOptionS = holdW;
/*   782 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex weightedMean_as_Complex()
/*       */   {
/*   787 */     if (!this.weightsSupplied) {
/*   788 */       System.out.println("weightedMean_as_Complex: no weights supplied - unweighted mean returned");
/*   789 */       return mean_as_Complex();
/*       */     }
/*       */     
/*   792 */     boolean holdW = weightingOptionS;
/*   793 */     if (this.weightingReset) {
/*   794 */       if (this.weightingOptionI) {
/*   795 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   798 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   801 */     Complex mean = Complex.zero();
/*   802 */     switch (this.type) {
/*   803 */     case 1:  double[] dd = getArray_as_double();
/*   804 */       double[] wwd = this.amWeights.getArray_as_double();
/*   805 */       mean = new Complex(mean(dd, wwd));
/*   806 */       break;
/*   807 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*   808 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*   809 */       mean = new Complex(mean(bd, wwb).doubleValue());
/*   810 */       bd = null;
/*   811 */       wwb = null;
/*   812 */       break;
/*   813 */     case 14:  Complex[] cc = getArray_as_Complex();
/*   814 */       Complex[] wwc = this.amWeights.getArray_as_Complex();
/*   815 */       mean = mean(cc, wwc);
/*   816 */       break;
/*   817 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   819 */     weightingOptionS = holdW;
/*   820 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double[] subtractMean()
/*       */   {
/*   826 */     return subtractMean_as_double();
/*       */   }
/*       */   
/*       */   public double[] subtractMean_as_double() {
/*   830 */     double[] arrayminus = new double[this.length];
/*   831 */     switch (this.type) {
/*   832 */     case 1:  double meand = mean_as_double();
/*   833 */       ArrayMaths amd = minus(meand);
/*   834 */       arrayminus = amd.getArray_as_double();
/*   835 */       break;
/*   836 */     case 12:  BigDecimal meanb = mean_as_BigDecimal();
/*   837 */       ArrayMaths amb = minus(meanb);
/*   838 */       arrayminus = amb.getArray_as_double();
/*   839 */       meanb = null;
/*   840 */       break;
/*   841 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*   842 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   844 */     return arrayminus;
/*       */   }
/*       */   
/*       */   public BigDecimal[] subtractMean_as_BigDecimal() {
/*   848 */     BigDecimal[] arrayminus = new BigDecimal[this.length];
/*   849 */     switch (this.type) {
/*       */     case 1: case 12: 
/*   851 */       BigDecimal meanb = mean_as_BigDecimal();
/*   852 */       ArrayMaths amb = minus(meanb);
/*   853 */       arrayminus = amb.getArray_as_BigDecimal();
/*   854 */       meanb = null;
/*   855 */       break;
/*   856 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*   857 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   859 */     return arrayminus;
/*       */   }
/*       */   
/*       */   public Complex[] subtractMean_as_Complex() {
/*   863 */     Complex[] arrayminus = new Complex[this.length];
/*   864 */     switch (this.type) {
/*   865 */     case 1:  double meand = mean_as_double();
/*   866 */       ArrayMaths amd = minus(meand);
/*   867 */       arrayminus = amd.getArray_as_Complex();
/*   868 */       break;
/*   869 */     case 12:  BigDecimal meanb = mean_as_BigDecimal();
/*   870 */       ArrayMaths amb = minus(meanb);
/*   871 */       arrayminus = amb.getArray_as_Complex();
/*   872 */       meanb = null;
/*   873 */       break;
/*   874 */     case 14:  Complex meanc = mean_as_Complex();
/*   875 */       ArrayMaths amc = minus(meanc);
/*   876 */       arrayminus = amc.getArray_as_Complex();
/*   877 */       break;
/*   878 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   880 */     return arrayminus;
/*       */   }
/*       */   
/*       */   public double[] subtractWeightedMean()
/*       */   {
/*   885 */     return subtractWeightedMean_as_double();
/*       */   }
/*       */   
/*       */   public double[] subtractWeightedMean_as_double() {
/*   889 */     if (!this.weightsSupplied) {
/*   890 */       System.out.println("subtractWeightedMean_as_double: no weights supplied - unweighted values returned");
/*   891 */       return subtractMean_as_double();
/*       */     }
/*       */     
/*   894 */     boolean holdW = weightingOptionS;
/*   895 */     if (this.weightingReset) {
/*   896 */       if (this.weightingOptionI) {
/*   897 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   900 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   903 */     double[] arrayminus = new double[this.length];
/*   904 */     switch (this.type) {
/*   905 */     case 1:  double meand = weightedMean_as_double();
/*   906 */       ArrayMaths amd = minus(meand);
/*   907 */       arrayminus = amd.getArray_as_double();
/*   908 */       break;
/*   909 */     case 12:  BigDecimal meanb = weightedMean_as_BigDecimal();
/*   910 */       ArrayMaths amb = minus(meanb);
/*   911 */       arrayminus = amb.getArray_as_double();
/*   912 */       meanb = null;
/*   913 */       break;
/*   914 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*   915 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   917 */     weightingOptionS = holdW;
/*   918 */     return arrayminus;
/*       */   }
/*       */   
/*       */   public BigDecimal[] subtractWeightedMean_as_BigDecimal()
/*       */   {
/*   923 */     if (!this.weightsSupplied) {
/*   924 */       System.out.println("subtractWeightedMean_as_BigDecimal: no weights supplied - unweighted values returned");
/*   925 */       return subtractMean_as_BigDecimal();
/*       */     }
/*       */     
/*   928 */     boolean holdW = weightingOptionS;
/*   929 */     if (this.weightingReset) {
/*   930 */       if (this.weightingOptionI) {
/*   931 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   934 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   937 */     BigDecimal[] arrayminus = new BigDecimal[this.length];
/*   938 */     switch (this.type) {
/*       */     case 1: case 12: 
/*   940 */       BigDecimal meanb = weightedMean_as_BigDecimal();
/*   941 */       ArrayMaths amb = minus(meanb);
/*   942 */       arrayminus = amb.getArray_as_BigDecimal();
/*   943 */       meanb = null;
/*   944 */       break;
/*   945 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*   946 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   948 */     weightingOptionS = holdW;
/*   949 */     return arrayminus;
/*       */   }
/*       */   
/*       */   public Complex[] subtractWeightedMean_as_Complex()
/*       */   {
/*   954 */     if (!this.weightsSupplied) {
/*   955 */       System.out.println("subtractWeightedMean_as_Complex: no weights supplied - unweighted values returned");
/*   956 */       return subtractMean_as_Complex();
/*       */     }
/*       */     
/*   959 */     boolean holdW = weightingOptionS;
/*   960 */     if (this.weightingReset) {
/*   961 */       if (this.weightingOptionI) {
/*   962 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*   965 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*   968 */     Complex[] arrayminus = new Complex[this.length];
/*   969 */     switch (this.type) {
/*   970 */     case 1:  double meand = weightedMean_as_double();
/*   971 */       ArrayMaths amd = minus(meand);
/*   972 */       arrayminus = amd.getArray_as_Complex();
/*   973 */       break;
/*   974 */     case 12:  BigDecimal meanb = weightedMean_as_BigDecimal();
/*   975 */       ArrayMaths amb = minus(meanb);
/*   976 */       arrayminus = amb.getArray_as_Complex();
/*   977 */       meanb = null;
/*   978 */       break;
/*   979 */     case 14:  Complex meanc = weightedMean_as_Complex();
/*   980 */       ArrayMaths amc = minus(meanc);
/*   981 */       arrayminus = amc.getArray_as_Complex();
/*   982 */       break;
/*   983 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*   985 */     weightingOptionS = holdW;
/*   986 */     return arrayminus;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double geometricMean()
/*       */   {
/*   993 */     return geometricMean_as_double();
/*       */   }
/*       */   
/*       */   public double geometricMean_as_double() {
/*   997 */     double gmean = 0.0D;
/*   998 */     switch (this.type) {
/*   999 */     case 1:  double[] dd = getArray_as_double();
/*  1000 */       gmean = geometricMean(dd);
/*  1001 */       break;
/*  1002 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1003 */       gmean = geometricMean(bd);
/*  1004 */       bd = null;
/*  1005 */       break;
/*  1006 */     case 14:  throw new IllegalArgumentException("Complex cannot  be converted to double");
/*  1007 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*       */     
/*  1010 */     return gmean;
/*       */   }
/*       */   
/*       */   public Complex geometricMean_as_Complex() {
/*  1014 */     Complex gmean = Complex.zero();
/*  1015 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*  1018 */       Complex[] cc = getArray_as_Complex();
/*  1019 */       gmean = geometricMean(cc);
/*  1020 */       break;
/*  1021 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1023 */     return gmean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double weightedGeometricMean()
/*       */   {
/*  1029 */     return weightedGeometricMean_as_double();
/*       */   }
/*       */   
/*       */   public double weightedGeometricMean_as_double() {
/*  1033 */     if (!this.weightsSupplied) {
/*  1034 */       System.out.println("weightedGeometricMean_as_double: no weights supplied - unweighted value returned");
/*  1035 */       return geometricMean_as_double();
/*       */     }
/*       */     
/*  1038 */     boolean holdW = weightingOptionS;
/*  1039 */     if (this.weightingReset) {
/*  1040 */       if (this.weightingOptionI) {
/*  1041 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1044 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1047 */     double gmean = 0.0D;
/*  1048 */     switch (this.type) {
/*  1049 */     case 1:  double[] dd = getArray_as_double();
/*  1050 */       double[] ww = getArray_as_double();
/*  1051 */       gmean = geometricMean(dd, ww);
/*  1052 */       break;
/*  1053 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1054 */       BigDecimal[] wd = getArray_as_BigDecimal();
/*  1055 */       gmean = geometricMean(bd, wd);
/*  1056 */       bd = null;
/*  1057 */       wd = null;
/*  1058 */       break;
/*  1059 */     case 14:  throw new IllegalArgumentException("Complex cannot  be converted to double");
/*  1060 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*       */     
/*  1063 */     weightingOptionS = holdW;
/*  1064 */     return gmean;
/*       */   }
/*       */   
/*       */   public Complex weightedGeometricMean_as_Complex()
/*       */   {
/*  1069 */     if (!this.weightsSupplied) {
/*  1070 */       System.out.println("weightedGeometricMean_as_Complex: no weights supplied - unweighted value returned");
/*  1071 */       return geometricMean_as_Complex();
/*       */     }
/*       */     
/*  1074 */     boolean holdW = weightingOptionS;
/*  1075 */     if (this.weightingReset) {
/*  1076 */       if (this.weightingOptionI) {
/*  1077 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1080 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1083 */     Complex gmean = Complex.zero();
/*  1084 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*  1087 */       Complex[] cc = getArray_as_Complex();
/*  1088 */       Complex[] ww = getArray_as_Complex();
/*  1089 */       gmean = geometricMean(cc, ww);
/*  1090 */       break;
/*  1091 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1093 */     weightingOptionS = holdW;
/*  1094 */     return gmean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double harmonicMean()
/*       */   {
/*  1100 */     return harmonicMean_as_double();
/*       */   }
/*       */   
/*       */   public double harmonicMean_as_double()
/*       */   {
/*  1105 */     double mean = 0.0D;
/*  1106 */     switch (this.type) {
/*  1107 */     case 1:  double[] dd = getArray_as_double();
/*  1108 */       mean = harmonicMean(dd);
/*  1109 */       break;
/*  1110 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1111 */       mean = harmonicMean(bd).doubleValue();
/*  1112 */       bd = null;
/*  1113 */       break;
/*  1114 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  1115 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1117 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public BigDecimal harmonicMean_as_BigDecimal()
/*       */   {
/*  1123 */     BigDecimal mean = BigDecimal.ZERO;
/*  1124 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1126 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1127 */       mean = harmonicMean(bd);
/*  1128 */       bd = null;
/*  1129 */       break;
/*  1130 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  1131 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1133 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public Complex harmonicMean_as_Complex()
/*       */   {
/*  1139 */     Complex mean = Complex.zero();
/*  1140 */     switch (this.type) {
/*  1141 */     case 1:  double[] dd = getArray_as_double();
/*  1142 */       mean = new Complex(harmonicMean(dd));
/*  1143 */       break;
/*  1144 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1145 */       mean = new Complex(harmonicMean(bd).doubleValue());
/*  1146 */       bd = null;
/*  1147 */       break;
/*  1148 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  1149 */       mean = harmonicMean(cc);
/*  1150 */       break;
/*  1151 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1153 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double weightedHarmonicMean()
/*       */   {
/*  1159 */     return weightedHarmonicMean_as_double();
/*       */   }
/*       */   
/*       */   public double weightedHarmonicMean_as_double() {
/*  1163 */     if (!this.weightsSupplied) {
/*  1164 */       System.out.println("weightedHarmonicMean_as_double: no weights supplied - unweighted mean returned");
/*  1165 */       return harmonicMean_as_double();
/*       */     }
/*       */     
/*  1168 */     boolean holdW = weightingOptionS;
/*  1169 */     if (this.weightingReset) {
/*  1170 */       if (this.weightingOptionI) {
/*  1171 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1174 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1177 */     double mean = 0.0D;
/*  1178 */     switch (this.type) {
/*  1179 */     case 1:  double[] dd = getArray_as_double();
/*  1180 */       double[] wwd = this.amWeights.getArray_as_double();
/*  1181 */       mean = harmonicMean(dd, wwd);
/*  1182 */       break;
/*  1183 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1184 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*  1185 */       mean = harmonicMean(bd, wwb).doubleValue();
/*  1186 */       bd = null;
/*  1187 */       wwb = null;
/*  1188 */       break;
/*  1189 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  1190 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1192 */     weightingOptionS = holdW;
/*  1193 */     return mean;
/*       */   }
/*       */   
/*       */   public BigDecimal weightedHarmonicMean_as_BigDecimal()
/*       */   {
/*  1198 */     if (!this.weightsSupplied) {
/*  1199 */       System.out.println("weightedHarmonicMean_as_BigDecimal: no weights supplied - unweighted mean returned");
/*  1200 */       return harmonicMean_as_BigDecimal();
/*       */     }
/*       */     
/*  1203 */     boolean holdW = weightingOptionS;
/*  1204 */     if (this.weightingReset) {
/*  1205 */       if (this.weightingOptionI) {
/*  1206 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1209 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1212 */     BigDecimal mean = BigDecimal.ZERO;
/*  1213 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1215 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1216 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*  1217 */       mean = harmonicMean(bd, wwb);
/*  1218 */       bd = null;
/*  1219 */       wwb = null;
/*  1220 */       break;
/*  1221 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  1222 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1224 */     weightingOptionS = holdW;
/*  1225 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex weightedHarmonicMean_as_Complex()
/*       */   {
/*  1230 */     if (!this.weightsSupplied) {
/*  1231 */       System.out.println("weightedHarmonicMean_as_Complex: no weights supplied - unweighted mean returned");
/*  1232 */       return harmonicMean_as_Complex();
/*       */     }
/*       */     
/*  1235 */     boolean holdW = weightingOptionS;
/*  1236 */     if (this.weightingReset) {
/*  1237 */       if (this.weightingOptionI) {
/*  1238 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1241 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1244 */     Complex mean = Complex.zero();
/*  1245 */     switch (this.type) {
/*  1246 */     case 1:  double[] dd = getArray_as_double();
/*  1247 */       double[] wwd = this.amWeights.getArray_as_double();
/*  1248 */       mean = new Complex(harmonicMean(dd, wwd));
/*  1249 */       break;
/*  1250 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1251 */       BigDecimal[] wwb = this.amWeights.getArray_as_BigDecimal();
/*  1252 */       mean = new Complex(harmonicMean(bd, wwb).doubleValue());
/*  1253 */       bd = null;
/*  1254 */       wwb = null;
/*  1255 */       break;
/*  1256 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  1257 */       Complex[] wwc = this.amWeights.getArray_as_Complex();
/*  1258 */       mean = harmonicMean(cc, wwc);
/*  1259 */       break;
/*  1260 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1262 */     weightingOptionS = holdW;
/*  1263 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double generalizedMean(double m)
/*       */   {
/*  1269 */     return generalizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double generalizedMean_as_double(double m) {
/*  1273 */     double mean = 0.0D;
/*  1274 */     switch (this.type) {
/*  1275 */     case 1:  double[] dd = getArray_as_double();
/*  1276 */       mean = generalizedMean(dd, m);
/*  1277 */       break;
/*  1278 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1279 */       mean = generalizedMean(bd, m);
/*  1280 */       bd = null;
/*  1281 */       break;
/*  1282 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  1283 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1285 */     return mean;
/*       */   }
/*       */   
/*       */   public double generalizedMean(BigDecimal m)
/*       */   {
/*  1290 */     return generalizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double generalizedMean_as_double(BigDecimal m) {
/*  1294 */     double mean = 0.0D;
/*  1295 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1297 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1298 */       mean = generalizedMean(bd, m);
/*  1299 */       bd = null;
/*  1300 */       break;
/*  1301 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  1302 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1304 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex generalizedMean_as_Complex(double m) {
/*  1308 */     Complex mean = Complex.zero();
/*  1309 */     switch (this.type) {
/*  1310 */     case 1:  double[] dd = getArray_as_double();
/*  1311 */       mean = new Complex(generalizedMean(dd, m));
/*  1312 */       break;
/*  1313 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1314 */       mean = new Complex(generalizedMean(bd, m));
/*  1315 */       bd = null;
/*  1316 */       break;
/*  1317 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  1318 */       mean = generalizedMean(cc, m);
/*  1319 */       break;
/*  1320 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1322 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex generalizedMean_as_Complex(Complex m) {
/*  1326 */     Complex mean = Complex.zero();
/*  1327 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*  1330 */       Complex[] cc = getArray_as_Complex();
/*  1331 */       mean = generalizedMean(cc, m);
/*  1332 */       break;
/*  1333 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1335 */     return mean;
/*       */   }
/*       */   
/*       */   public double generalisedMean(double m) {
/*  1339 */     return generalisedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double generalisedMean_as_double(double m) {
/*  1343 */     double mean = 0.0D;
/*  1344 */     switch (this.type) {
/*  1345 */     case 1:  double[] dd = getArray_as_double();
/*  1346 */       mean = generalisedMean(dd, m);
/*  1347 */       break;
/*  1348 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1349 */       mean = generalisedMean(bd, m);
/*  1350 */       bd = null;
/*  1351 */       break;
/*  1352 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  1353 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1355 */     return mean;
/*       */   }
/*       */   
/*       */   public double generalisedMean(BigDecimal m) {
/*  1359 */     return generalisedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double generalisedMean_as_double(BigDecimal m) {
/*  1363 */     double mean = 0.0D;
/*  1364 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1366 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1367 */       mean = generalisedMean(bd, m);
/*  1368 */       bd = null;
/*  1369 */       break;
/*  1370 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  1371 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1373 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex generalisedMean_as_Complex(double m) {
/*  1377 */     Complex mean = Complex.zero();
/*  1378 */     switch (this.type) {
/*  1379 */     case 1:  double[] dd = getArray_as_double();
/*  1380 */       mean = new Complex(generalisedMean(dd, m));
/*  1381 */       break;
/*  1382 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1383 */       mean = new Complex(generalisedMean(bd, m));
/*  1384 */       bd = null;
/*  1385 */       break;
/*  1386 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  1387 */       mean = generalisedMean(cc, m);
/*  1388 */       break;
/*  1389 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1391 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex generalisedMean_as_Complex(Complex m) {
/*  1395 */     Complex mean = Complex.zero();
/*  1396 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*  1399 */       Complex[] cc = getArray_as_Complex();
/*  1400 */       mean = generalisedMean(cc, m);
/*  1401 */       break;
/*  1402 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1404 */     return mean;
/*       */   }
/*       */   
/*       */ 
/*       */   public double weightedGeneralizedMean(double m)
/*       */   {
/*  1410 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double weightedGeneralizedMean_as_double(double m) {
/*  1414 */     if (!this.weightsSupplied) {
/*  1415 */       System.out.println("weightedGeneralizedMean_as_double: no weights supplied - unweighted mean returned");
/*  1416 */       return generalizedMean_as_double(m);
/*       */     }
/*       */     
/*  1419 */     boolean holdW = weightingOptionS;
/*  1420 */     if (this.weightingReset) {
/*  1421 */       if (this.weightingOptionI) {
/*  1422 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1425 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1428 */     double mean = 0.0D;
/*  1429 */     switch (this.type) {
/*  1430 */     case 1:  double[] dd = getArray_as_double();
/*  1431 */       double[] ww = this.amWeights.getArray_as_double();
/*  1432 */       mean = generalisedMean(dd, ww, m);
/*  1433 */       break;
/*  1434 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1435 */       BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  1436 */       mean = generalisedMean(bd, wd, m);
/*  1437 */       bd = null;
/*  1438 */       wd = null;
/*  1439 */       break;
/*  1440 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  1441 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1443 */     weightingOptionS = holdW;
/*  1444 */     return mean;
/*       */   }
/*       */   
/*       */   public double weightedGeneralizedMean(BigDecimal m)
/*       */   {
/*  1449 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double weightedGeneralizedMean_as_double(BigDecimal m) {
/*  1453 */     if (!this.weightsSupplied) {
/*  1454 */       System.out.println("weightedGeneralizedMean_as_double: no weights supplied - unweighted mean returned");
/*  1455 */       return generalizedMean_as_double(m);
/*       */     }
/*       */     
/*  1458 */     boolean holdW = weightingOptionS;
/*  1459 */     if (this.weightingReset) {
/*  1460 */       if (this.weightingOptionI) {
/*  1461 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1464 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1467 */     double mean = 0.0D;
/*  1468 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1470 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1471 */       BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  1472 */       mean = generalisedMean(bd, wd, m);
/*  1473 */       bd = null;
/*  1474 */       break;
/*  1475 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  1476 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1478 */     weightingOptionS = holdW;
/*  1479 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex weightedGeneralizedMean_as_Complex(double m)
/*       */   {
/*  1484 */     if (!this.weightsSupplied) {
/*  1485 */       System.out.println("weightedGeneralizedMean_as_Complex: no weights supplied - unweighted mean returned");
/*  1486 */       return generalizedMean_as_Complex(m);
/*       */     }
/*       */     
/*  1489 */     boolean holdW = weightingOptionS;
/*  1490 */     if (this.weightingReset) {
/*  1491 */       if (this.weightingOptionI) {
/*  1492 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1495 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1498 */     Complex mean = Complex.zero();
/*  1499 */     switch (this.type) {
/*  1500 */     case 1:  double[] dd = getArray_as_double();
/*  1501 */       double[] ww = this.amWeights.getArray_as_double();
/*  1502 */       mean = new Complex(generalisedMean(dd, ww, m));
/*  1503 */       break;
/*  1504 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1505 */       BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  1506 */       mean = new Complex(generalisedMean(bd, wd, m));
/*  1507 */       bd = null;
/*  1508 */       break;
/*  1509 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  1510 */       Complex[] cw = this.amWeights.getArray_as_Complex();
/*  1511 */       mean = generalisedMean(cc, cw, m);
/*  1512 */       break;
/*  1513 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1515 */     weightingOptionS = holdW;
/*  1516 */     return mean;
/*       */   }
/*       */   
/*       */   public Complex weightedGeneralizedMean_as_Complex(Complex m)
/*       */   {
/*  1521 */     Complex mean = Complex.zero();
/*  1522 */     if (!this.weightsSupplied) {
/*  1523 */       System.out.println("weightedGeneralizedMean_as_dComplex: no weights supplied - unweighted mean returned");
/*  1524 */       return generalizedMean_as_Complex(m);
/*       */     }
/*       */     
/*  1527 */     boolean holdW = weightingOptionS;
/*  1528 */     if (this.weightingReset) {
/*  1529 */       if (this.weightingOptionI) {
/*  1530 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1533 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1536 */     switch (this.type) {
/*       */     case 1: case 12: 
/*       */     case 14: 
/*  1539 */       Complex[] cc = getArray_as_Complex();
/*  1540 */       Complex[] cw = this.amWeights.getArray_as_Complex();
/*  1541 */       mean = generalisedMean(cc, cw, m);
/*  1542 */       break;
/*  1543 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1545 */     weightingOptionS = holdW;
/*  1546 */     return mean;
/*       */   }
/*       */   
/*       */   public double weightedGeneralisedMean(double m)
/*       */   {
/*  1551 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double weightedGeneralisedMean_as_double(double m) {
/*  1555 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double weightedGeneralisedMean(BigDecimal m) {
/*  1559 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public double weightedGeneralisedMean_as_double(BigDecimal m) {
/*  1563 */     return weightedGeneralizedMean_as_double(m);
/*       */   }
/*       */   
/*       */   public Complex weightedGeneralisedMean_as_Complex(double m) {
/*  1567 */     return weightedGeneralizedMean_as_Complex(m);
/*       */   }
/*       */   
/*       */   public Complex weightedGeneralisedMean_as_Complex(Complex m) {
/*  1571 */     return weightedGeneralizedMean_as_Complex(m);
/*       */   }
/*       */   
/*       */   public double interQuartileMean()
/*       */   {
/*  1576 */     return interQuartileMean_as_double();
/*       */   }
/*       */   
/*       */   public double interQuartileMean_as_double() {
/*  1580 */     double mean = 0.0D;
/*  1581 */     switch (this.type) {
/*  1582 */     case 1:  double[] dd = getArray_as_double();
/*  1583 */       mean = interQuartileMean(dd);
/*  1584 */       break;
/*  1585 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1586 */       mean = interQuartileMean(bd).doubleValue();
/*  1587 */       bd = null;
/*  1588 */       break;
/*  1589 */     case 14:  throw new IllegalArgumentException("Complex interquartile mean is not supported");
/*  1590 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1592 */     return mean;
/*       */   }
/*       */   
/*       */   public BigDecimal interQuartileMean_as_BigDecimal() {
/*  1596 */     BigDecimal mean = BigDecimal.ZERO;
/*  1597 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1599 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1600 */       mean = interQuartileMean(bd);
/*  1601 */       bd = null;
/*  1602 */       break;
/*  1603 */     case 14:  throw new IllegalArgumentException("Complex interquartile mean is not supported");
/*  1604 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1606 */     return mean;
/*       */   }
/*       */   
/*       */   public double median()
/*       */   {
/*  1611 */     return median_as_double();
/*       */   }
/*       */   
/*       */   public double median_as_double() {
/*  1615 */     double median = 0.0D;
/*  1616 */     switch (this.type) {
/*  1617 */     case 1:  double[] dd = getArray_as_double();
/*  1618 */       median = median(dd);
/*  1619 */       break;
/*  1620 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1621 */       median = median(bd).doubleValue();
/*  1622 */       bd = null;
/*  1623 */       break;
/*  1624 */     case 14:  throw new IllegalArgumentException("Complex median value not supported");
/*  1625 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1627 */     return median;
/*       */   }
/*       */   
/*       */   public BigDecimal median_as_BigDecimal() {
/*  1631 */     BigDecimal median = BigDecimal.ZERO;
/*  1632 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1634 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1635 */       median = median(bd);
/*  1636 */       bd = null;
/*  1637 */       break;
/*  1638 */     case 14:  throw new IllegalArgumentException("Complex median value not supported");
/*  1639 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1641 */     return median;
/*       */   }
/*       */   
/*       */   public double rms()
/*       */   {
/*  1646 */     double rms = 0.0D;
/*  1647 */     switch (this.type) {
/*  1648 */     case 1:  double[] dd = getArray_as_double();
/*  1649 */       rms = rms(dd);
/*  1650 */       break;
/*  1651 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1652 */       rms = rms(bd);
/*  1653 */       bd = null;
/*  1654 */       break;
/*  1655 */     case 14:  throw new IllegalArgumentException("Complex root mean square is not supported");
/*  1656 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1658 */     return rms;
/*       */   }
/*       */   
/*       */   public double weightedRms()
/*       */   {
/*  1663 */     if (!this.weightsSupplied) {
/*  1664 */       System.out.println("weightedRms: no weights supplied - unweighted rms returned");
/*  1665 */       return rms();
/*       */     }
/*       */     
/*  1668 */     boolean holdW = weightingOptionS;
/*  1669 */     if (this.weightingReset) {
/*  1670 */       if (this.weightingOptionI) {
/*  1671 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  1674 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  1677 */     double rms = 0.0D;
/*  1678 */     switch (this.type) {
/*  1679 */     case 1:  double[] dd = getArray_as_double();
/*  1680 */       double[] ww = this.amWeights.getArray_as_double();
/*  1681 */       rms = rms(dd, ww);
/*  1682 */       break;
/*  1683 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1684 */       BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  1685 */       rms = rms(bd, wd);
/*  1686 */       bd = null;
/*  1687 */       wd = null;
/*  1688 */       break;
/*  1689 */     case 14:  throw new IllegalArgumentException("Complex root mean square is not supported");
/*  1690 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1692 */     weightingOptionS = holdW;
/*  1693 */     return rms;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double momentSkewness()
/*       */   {
/*  1702 */     boolean hold = nFactorOptionS;
/*  1703 */     if (this.nFactorReset) {
/*  1704 */       if (this.nFactorOptionI) {
/*  1705 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1708 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1711 */     double skewness = 0.0D;
/*  1712 */     switch (this.type) {
/*  1713 */     case 1:  double[] dd = getArray_as_double();
/*  1714 */       skewness = momentSkewness(dd);
/*  1715 */       break;
/*  1716 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1717 */       skewness = momentSkewness(bd);
/*  1718 */       bd = null;
/*  1719 */       break;
/*  1720 */     case 14:  throw new IllegalArgumentException("Complex skewness is not supported");
/*  1721 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1723 */     nFactorOptionS = hold;
/*  1724 */     return skewness;
/*       */   }
/*       */   
/*       */   public double momentSkewness_as_double() {
/*  1728 */     return momentSkewness();
/*       */   }
/*       */   
/*       */   public double medianSkewness()
/*       */   {
/*  1733 */     boolean hold = nFactorOptionS;
/*  1734 */     if (this.nFactorReset) {
/*  1735 */       if (this.nFactorOptionI) {
/*  1736 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1739 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1742 */     double skewness = 0.0D;
/*  1743 */     switch (this.type) {
/*  1744 */     case 1:  double[] dd = getArray_as_double();
/*  1745 */       skewness = medianSkewness(dd);
/*  1746 */       break;
/*  1747 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1748 */       skewness = medianSkewness(bd);
/*  1749 */       bd = null;
/*  1750 */       break;
/*  1751 */     case 14:  throw new IllegalArgumentException("Complex skewness is not supported");
/*  1752 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1754 */     nFactorOptionS = hold;
/*  1755 */     return skewness;
/*       */   }
/*       */   
/*       */   public double medianSkewness_as_double() {
/*  1759 */     return medianSkewness();
/*       */   }
/*       */   
/*       */   public double quartileSkewness()
/*       */   {
/*  1764 */     boolean hold = nFactorOptionS;
/*  1765 */     if (this.nFactorReset) {
/*  1766 */       if (this.nFactorOptionI) {
/*  1767 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1770 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1773 */     double skewness = 0.0D;
/*  1774 */     switch (this.type) {
/*  1775 */     case 1:  double[] dd = getArray_as_double();
/*  1776 */       skewness = quartileSkewness(dd);
/*  1777 */       break;
/*  1778 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1779 */       skewness = quartileSkewness(bd).doubleValue();
/*  1780 */       bd = null;
/*  1781 */       break;
/*  1782 */     case 14:  throw new IllegalArgumentException("Complex skewness is not supported");
/*  1783 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1785 */     nFactorOptionS = hold;
/*  1786 */     return skewness;
/*       */   }
/*       */   
/*       */   public double quartileSkewness_as_double() {
/*  1790 */     return quartileSkewness();
/*       */   }
/*       */   
/*       */   public BigDecimal quartileSkewness_as_BigDecimal()
/*       */   {
/*  1795 */     boolean hold = nFactorOptionS;
/*  1796 */     if (this.nFactorReset) {
/*  1797 */       if (this.nFactorOptionI) {
/*  1798 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1801 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1804 */     BigDecimal skewness = BigDecimal.ZERO;
/*  1805 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1807 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1808 */       skewness = quartileSkewness(bd);
/*  1809 */       bd = null;
/*  1810 */       break;
/*  1811 */     case 14:  throw new IllegalArgumentException("Complex skewness is not supported");
/*  1812 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1814 */     nFactorOptionS = hold;
/*  1815 */     return skewness;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double kurtosis()
/*       */   {
/*  1822 */     return kurtosis_as_double();
/*       */   }
/*       */   
/*       */   public double kurtosis_as_double() {
/*  1826 */     boolean hold = nFactorOptionS;
/*  1827 */     if (this.nFactorReset) {
/*  1828 */       if (this.nFactorOptionI) {
/*  1829 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1832 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1835 */     double kurtosis = 0.0D;
/*  1836 */     switch (this.type) {
/*  1837 */     case 1:  double[] dd = getArray_as_double();
/*  1838 */       kurtosis = kurtosis(dd);
/*  1839 */       break;
/*  1840 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1841 */       kurtosis = kurtosis(bd).doubleValue();
/*  1842 */       bd = null;
/*  1843 */       break;
/*  1844 */     case 14:  throw new IllegalArgumentException("Complex kurtosis is not supported");
/*  1845 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1847 */     nFactorOptionS = hold;
/*  1848 */     return kurtosis;
/*       */   }
/*       */   
/*       */   public double curtosis() {
/*  1852 */     return kurtosis_as_double();
/*       */   }
/*       */   
/*       */   public double curtosis_as_double() {
/*  1856 */     return kurtosis_as_double();
/*       */   }
/*       */   
/*       */   public double kurtosisExcess() {
/*  1860 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double excessKurtosis() {
/*  1864 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double excessCurtosis() {
/*  1868 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double kurtosisExcess_as_double() {
/*  1872 */     boolean hold = nFactorOptionS;
/*  1873 */     if (this.nFactorReset) {
/*  1874 */       if (this.nFactorOptionI) {
/*  1875 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1878 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1881 */     double kurtosis = 0.0D;
/*  1882 */     switch (this.type) {
/*  1883 */     case 1:  double[] dd = getArray_as_double();
/*  1884 */       kurtosis = kurtosisExcess(dd);
/*  1885 */       break;
/*  1886 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  1887 */       kurtosis = kurtosisExcess(bd).doubleValue();
/*  1888 */       bd = null;
/*  1889 */       break;
/*  1890 */     case 14:  throw new IllegalArgumentException("Complex kurtosis is not supported");
/*  1891 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1893 */     nFactorOptionS = hold;
/*  1894 */     return kurtosis;
/*       */   }
/*       */   
/*       */   public double excessKurtosis_as_double() {
/*  1898 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double curtosisExcess()
/*       */   {
/*  1903 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double curtosisExcess_as_double() {
/*  1907 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public double excessCurtosis_as_double() {
/*  1911 */     return kurtosisExcess_as_double();
/*       */   }
/*       */   
/*       */   public BigDecimal kurtosis_as_BigDecimal() {
/*  1915 */     boolean hold = nFactorOptionS;
/*  1916 */     if (this.nFactorReset) {
/*  1917 */       if (this.nFactorOptionI) {
/*  1918 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1921 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1924 */     BigDecimal kurtosis = BigDecimal.ZERO;
/*  1925 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1927 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1928 */       kurtosis = kurtosis(bd);
/*  1929 */       bd = null;
/*  1930 */       break;
/*  1931 */     case 14:  throw new IllegalArgumentException("Complex kurtosis is not supported");
/*  1932 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1934 */     nFactorOptionS = hold;
/*  1935 */     return kurtosis;
/*       */   }
/*       */   
/*       */   public BigDecimal curtosis_as_BigDecimal() {
/*  1939 */     return kurtosis_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal kurtosisExcess_as_BigDecimal()
/*       */   {
/*  1944 */     boolean hold = nFactorOptionS;
/*  1945 */     if (this.nFactorReset) {
/*  1946 */       if (this.nFactorOptionI) {
/*  1947 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1950 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1953 */     BigDecimal kurtosis = BigDecimal.ZERO;
/*  1954 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  1956 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  1957 */       kurtosis = kurtosisExcess(bd);
/*  1958 */       bd = null;
/*  1959 */       break;
/*  1960 */     case 14:  throw new IllegalArgumentException("Complex kurtosis is not supported");
/*  1961 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  1963 */     nFactorOptionS = hold;
/*  1964 */     return kurtosis;
/*       */   }
/*       */   
/*       */   public BigDecimal excessKurtosis_as_BigDecimal() {
/*  1968 */     return kurtosisExcess_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal curtosisExcess_as_BigDecimal() {
/*  1972 */     return kurtosisExcess_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal excessCurtosis_as_BigDecimal() {
/*  1976 */     return kurtosisExcess_as_BigDecimal();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double variance()
/*       */   {
/*  1983 */     return variance_as_double();
/*       */   }
/*       */   
/*       */   public double variance_as_double() {
/*  1987 */     boolean hold = nFactorOptionS;
/*  1988 */     if (this.nFactorReset) {
/*  1989 */       if (this.nFactorOptionI) {
/*  1990 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  1993 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  1996 */     double variance = 0.0D;
/*  1997 */     switch (this.type) {
/*  1998 */     case 1:  double[] dd = getArray_as_double();
/*  1999 */       variance = variance(dd);
/*  2000 */       break;
/*  2001 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  2002 */       variance = variance(bd).doubleValue();
/*  2003 */       bd = null;
/*  2004 */       break;
/*  2005 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  2006 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  2008 */     nFactorOptionS = hold;
/*  2009 */     return variance;
/*       */   }
/*       */   
/*       */   public BigDecimal variance_as_BigDecimal() {
/*  2013 */     boolean hold = nFactorOptionS;
/*  2014 */     if (this.nFactorReset) {
/*  2015 */       if (this.nFactorOptionI) {
/*  2016 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2019 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2022 */     BigDecimal variance = BigDecimal.ZERO;
/*  2023 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  2025 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  2026 */       variance = variance(bd);
/*  2027 */       bd = null;
/*  2028 */       break;
/*  2029 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  2030 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  2032 */     nFactorOptionS = hold;
/*  2033 */     return variance;
/*       */   }
/*       */   
/*       */   public Complex variance_as_Complex() {
/*  2037 */     boolean hold = nFactorOptionS;
/*  2038 */     if (this.nFactorReset) {
/*  2039 */       if (this.nFactorOptionI) {
/*  2040 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2043 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2046 */     Complex variance = Complex.zero();
/*  2047 */     Complex[] cc = getArray_as_Complex();
/*  2048 */     variance = variance(cc);
/*  2049 */     nFactorOptionS = hold;
/*  2050 */     return variance;
/*       */   }
/*       */   
/*       */   public double variance_as_Complex_ConjugateCalcn() {
/*  2054 */     boolean hold = nFactorOptionS;
/*  2055 */     if (this.nFactorReset) {
/*  2056 */       if (this.nFactorOptionI) {
/*  2057 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2060 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2063 */     Complex[] cc = getArray_as_Complex();
/*  2064 */     double variance = varianceConjugateCalcn(cc);
/*  2065 */     nFactorOptionS = hold;
/*  2066 */     return variance;
/*       */   }
/*       */   
/*       */   public double variance_of_ComplexModuli() {
/*  2070 */     boolean hold = nFactorOptionS;
/*  2071 */     if (this.nFactorReset) {
/*  2072 */       if (this.nFactorOptionI) {
/*  2073 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2076 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2079 */     double[] re = array_as_modulus_of_Complex();
/*  2080 */     double variance = variance(re);
/*  2081 */     nFactorOptionS = hold;
/*  2082 */     return variance;
/*       */   }
/*       */   
/*       */   public double variance_of_ComplexRealParts() {
/*  2086 */     boolean hold = nFactorOptionS;
/*  2087 */     if (this.nFactorReset) {
/*  2088 */       if (this.nFactorOptionI) {
/*  2089 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2092 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2095 */     double[] re = array_as_real_part_of_Complex();
/*  2096 */     double variance = variance(re);
/*  2097 */     nFactorOptionS = hold;
/*  2098 */     return variance;
/*       */   }
/*       */   
/*       */   public double variance_of_ComplexImaginaryParts() {
/*  2102 */     boolean hold = nFactorOptionS;
/*  2103 */     if (this.nFactorReset) {
/*  2104 */       if (this.nFactorOptionI) {
/*  2105 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2108 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2111 */     double[] im = array_as_imaginary_part_of_Complex();
/*  2112 */     double variance = variance(im);
/*  2113 */     nFactorOptionS = hold;
/*  2114 */     return variance;
/*       */   }
/*       */   
/*       */   public double weightedVariance()
/*       */   {
/*  2119 */     return weightedVariance_as_double();
/*       */   }
/*       */   
/*       */   public double weightedVariance_as_double() {
/*  2123 */     boolean hold = nFactorOptionS;
/*  2124 */     if (this.nFactorReset) {
/*  2125 */       if (this.nFactorOptionI) {
/*  2126 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2129 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2132 */     boolean hold2 = nEffOptionS;
/*  2133 */     if (this.nEffReset) {
/*  2134 */       if (this.nEffOptionI) {
/*  2135 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2138 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2141 */     boolean holdW = weightingOptionS;
/*  2142 */     if (this.weightingReset) {
/*  2143 */       if (this.weightingOptionI) {
/*  2144 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2147 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2151 */     double varr = NaN.0D;
/*  2152 */     if (!this.weightsSupplied) {
/*  2153 */       System.out.println("weightedVariance_as_double: no weights supplied - unweighted value returned");
/*  2154 */       varr = variance_as_double();
/*       */     }
/*       */     else {
/*  2157 */       double weightedVariance = 0.0D;
/*  2158 */       switch (this.type) {
/*  2159 */       case 1:  double[] dd = getArray_as_double();
/*  2160 */         double[] ww = this.amWeights.getArray_as_double();
/*  2161 */         weightedVariance = variance(dd, ww);
/*  2162 */         break;
/*  2163 */       case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  2164 */         BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  2165 */         weightedVariance = variance(bd, wd).doubleValue();
/*  2166 */         bd = null;
/*  2167 */         wd = null;
/*  2168 */         break;
/*  2169 */       case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  2170 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*  2172 */       varr = weightedVariance;
/*       */     }
/*  2174 */     nFactorOptionS = hold;
/*  2175 */     nEffOptionS = hold2;
/*  2176 */     weightingOptionS = holdW;
/*  2177 */     return varr;
/*       */   }
/*       */   
/*       */   public BigDecimal weightedVariance_as_BigDecimal()
/*       */   {
/*  2182 */     boolean hold = nFactorOptionS;
/*  2183 */     if (this.nFactorReset) {
/*  2184 */       if (this.nFactorOptionI) {
/*  2185 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2188 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2191 */     boolean hold2 = nEffOptionS;
/*  2192 */     if (this.nEffReset) {
/*  2193 */       if (this.nEffOptionI) {
/*  2194 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2197 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2200 */     boolean holdW = weightingOptionS;
/*  2201 */     if (this.weightingReset) {
/*  2202 */       if (this.weightingOptionI) {
/*  2203 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2206 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2209 */     BigDecimal varr = BigDecimal.ZERO;
/*  2210 */     if (!this.weightsSupplied) {
/*  2211 */       System.out.println("weightedVariance_as_BigDecimal: no weights supplied - unweighted value returned");
/*  2212 */       varr = variance_as_BigDecimal();
/*       */     }
/*       */     else {
/*  2215 */       BigDecimal weightedVariance = BigDecimal.ZERO;
/*  2216 */       switch (this.type) {
/*       */       case 1: case 12: 
/*  2218 */         BigDecimal[] bd = getArray_as_BigDecimal();
/*  2219 */         BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  2220 */         weightedVariance = variance(bd, wd);
/*  2221 */         bd = null;
/*  2222 */         wd = null;
/*  2223 */         break;
/*  2224 */       case 14:  throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
/*  2225 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*  2227 */       varr = weightedVariance;
/*       */     }
/*  2229 */     nFactorOptionS = hold;
/*  2230 */     nEffOptionS = hold2;
/*  2231 */     weightingOptionS = holdW;
/*  2232 */     return varr;
/*       */   }
/*       */   
/*       */   public Complex weightedVariance_as_Complex()
/*       */   {
/*  2237 */     boolean hold = nFactorOptionS;
/*  2238 */     if (this.nFactorReset) {
/*  2239 */       if (this.nFactorOptionI) {
/*  2240 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2243 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2246 */     boolean hold2 = nEffOptionS;
/*  2247 */     if (this.nEffReset) {
/*  2248 */       if (this.nEffOptionI) {
/*  2249 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2252 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2255 */     boolean holdW = weightingOptionS;
/*  2256 */     if (this.weightingReset) {
/*  2257 */       if (this.weightingOptionI) {
/*  2258 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2261 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2264 */     Complex varr = Complex.zero();
/*  2265 */     if (!this.weightsSupplied) {
/*  2266 */       System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
/*  2267 */       varr = variance_as_Complex();
/*       */     }
/*       */     else {
/*  2270 */       Complex weightedVariance = Complex.zero();
/*  2271 */       Complex[] cc = getArray_as_Complex();
/*  2272 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  2273 */       weightedVariance = variance(cc, wc);
/*  2274 */       varr = weightedVariance;
/*       */     }
/*  2276 */     nFactorOptionS = hold;
/*  2277 */     nEffOptionS = hold2;
/*  2278 */     weightingOptionS = holdW;
/*  2279 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedVariance_as_Complex_ConjugateCalcn() {
/*  2283 */     boolean hold = nFactorOptionS;
/*  2284 */     if (this.nFactorReset) {
/*  2285 */       if (this.nFactorOptionI) {
/*  2286 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2289 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2292 */     boolean hold2 = nEffOptionS;
/*  2293 */     if (this.nEffReset) {
/*  2294 */       if (this.nEffOptionI) {
/*  2295 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2298 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2301 */     boolean holdW = weightingOptionS;
/*  2302 */     if (this.weightingReset) {
/*  2303 */       if (this.weightingOptionI) {
/*  2304 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2307 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2310 */     double varr = NaN.0D;
/*  2311 */     if (!this.weightsSupplied) {
/*  2312 */       System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
/*  2313 */       varr = variance_as_Complex_ConjugateCalcn();
/*       */     }
/*       */     else {
/*  2316 */       Complex[] cc = getArray_as_Complex();
/*  2317 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  2318 */       varr = varianceConjugateCalcn(cc, wc);
/*       */     }
/*  2320 */     nFactorOptionS = hold;
/*  2321 */     nEffOptionS = hold2;
/*  2322 */     weightingOptionS = holdW;
/*  2323 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedVariance_of_ComplexModuli() {
/*  2327 */     boolean hold = nFactorOptionS;
/*  2328 */     if (this.nFactorReset) {
/*  2329 */       if (this.nFactorOptionI) {
/*  2330 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2333 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2336 */     boolean hold2 = nEffOptionS;
/*  2337 */     if (this.nEffReset) {
/*  2338 */       if (this.nEffOptionI) {
/*  2339 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2342 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2345 */     boolean holdW = weightingOptionS;
/*  2346 */     if (this.weightingReset) {
/*  2347 */       if (this.weightingOptionI) {
/*  2348 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2351 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2354 */     double varr = NaN.0D;
/*  2355 */     if (!this.weightsSupplied) {
/*  2356 */       System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
/*  2357 */       varr = variance_of_ComplexModuli();
/*       */     }
/*       */     else {
/*  2360 */       double[] cc = array_as_modulus_of_Complex();
/*  2361 */       double[] wc = this.amWeights.array_as_modulus_of_Complex();
/*  2362 */       varr = variance(cc, wc);
/*       */     }
/*  2364 */     nFactorOptionS = hold;
/*  2365 */     nEffOptionS = hold2;
/*  2366 */     weightingOptionS = holdW;
/*  2367 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedVariance_of_ComplexRealParts() {
/*  2371 */     boolean hold = nFactorOptionS;
/*  2372 */     if (this.nFactorReset) {
/*  2373 */       if (this.nFactorOptionI) {
/*  2374 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2377 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2380 */     boolean hold2 = nEffOptionS;
/*  2381 */     if (this.nEffReset) {
/*  2382 */       if (this.nEffOptionI) {
/*  2383 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2386 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2389 */     boolean holdW = weightingOptionS;
/*  2390 */     if (this.weightingReset) {
/*  2391 */       if (this.weightingOptionI) {
/*  2392 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2395 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2398 */     double varr = NaN.0D;
/*  2399 */     if (!this.weightsSupplied) {
/*  2400 */       System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
/*  2401 */       varr = variance_of_ComplexRealParts();
/*       */     }
/*       */     else {
/*  2404 */       double[] cc = array_as_real_part_of_Complex();
/*  2405 */       double[] wc = this.amWeights.array_as_real_part_of_Complex();
/*  2406 */       varr = variance(cc, wc);
/*       */     }
/*  2408 */     nFactorOptionS = hold;
/*  2409 */     nEffOptionS = hold2;
/*  2410 */     weightingOptionS = holdW;
/*  2411 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedVariance_of_ComplexImaginaryParts() {
/*  2415 */     boolean hold = nFactorOptionS;
/*  2416 */     if (this.nFactorReset) {
/*  2417 */       if (this.nFactorOptionI) {
/*  2418 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2421 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2424 */     boolean hold2 = nEffOptionS;
/*  2425 */     if (this.nEffReset) {
/*  2426 */       if (this.nEffOptionI) {
/*  2427 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2430 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2433 */     boolean holdW = weightingOptionS;
/*  2434 */     if (this.weightingReset) {
/*  2435 */       if (this.weightingOptionI) {
/*  2436 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2439 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2442 */     double varr = NaN.0D;
/*  2443 */     if (!this.weightsSupplied) {
/*  2444 */       System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
/*  2445 */       varr = variance_of_ComplexImaginaryParts();
/*       */     }
/*       */     else {
/*  2448 */       double[] cc = array_as_imaginary_part_of_Complex();
/*  2449 */       double[] wc = this.amWeights.array_as_imaginary_part_of_Complex();
/*  2450 */       varr = variance(cc, wc);
/*       */     }
/*  2452 */     nFactorOptionS = hold;
/*  2453 */     nEffOptionS = hold2;
/*  2454 */     weightingOptionS = holdW;
/*  2455 */     return varr;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double standardDeviation()
/*       */   {
/*  2462 */     return standardDeviation_as_double();
/*       */   }
/*       */   
/*       */   public double standardDeviation_as_double() {
/*  2466 */     boolean hold = nFactorOptionS;
/*  2467 */     if (this.nFactorReset) {
/*  2468 */       if (this.nFactorOptionI) {
/*  2469 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2472 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2476 */     double variance = 0.0D;
/*  2477 */     switch (this.type) {
/*  2478 */     case 1:  double[] dd = getArray_as_double();
/*  2479 */       variance = variance(dd);
/*  2480 */       break;
/*  2481 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  2482 */       variance = variance(bd).doubleValue();
/*  2483 */       bd = null;
/*  2484 */       break;
/*  2485 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  2486 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  2488 */     nFactorOptionS = hold;
/*  2489 */     return Math.sqrt(variance);
/*       */   }
/*       */   
/*       */   public Complex standardDeviation_as_Complex() {
/*  2493 */     boolean hold = nFactorOptionS;
/*  2494 */     if (this.nFactorReset) {
/*  2495 */       if (this.nFactorOptionI) {
/*  2496 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2499 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2503 */     Complex variance = Complex.zero();
/*  2504 */     Complex[] cc = getArray_as_Complex();
/*  2505 */     variance = variance(cc);
/*  2506 */     nFactorOptionS = hold;
/*  2507 */     return Complex.sqrt(variance);
/*       */   }
/*       */   
/*       */   public double standardDeviation_as_Complex_ConjugateCalcn() {
/*  2511 */     boolean hold = nFactorOptionS;
/*  2512 */     if (this.nFactorReset) {
/*  2513 */       if (this.nFactorOptionI) {
/*  2514 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2517 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2521 */     Complex[] cc = getArray_as_Complex();
/*  2522 */     double variance = varianceConjugateCalcn(cc);
/*  2523 */     nFactorOptionS = hold;
/*  2524 */     return Math.sqrt(variance);
/*       */   }
/*       */   
/*       */   public double standardDeviation_of_ComplexModuli() {
/*  2528 */     boolean hold = nFactorOptionS;
/*  2529 */     if (this.nFactorReset) {
/*  2530 */       if (this.nFactorOptionI) {
/*  2531 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2534 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2537 */     double[] re = array_as_modulus_of_Complex();
/*  2538 */     double standardDeviation = standardDeviation(re);
/*  2539 */     nFactorOptionS = hold;
/*  2540 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public double standardDeviation_of_ComplexRealParts() {
/*  2544 */     boolean hold = nFactorOptionS;
/*  2545 */     if (this.nFactorReset) {
/*  2546 */       if (this.nFactorOptionI) {
/*  2547 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2550 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2553 */     double[] re = array_as_real_part_of_Complex();
/*  2554 */     double standardDeviation = standardDeviation(re);
/*  2555 */     nFactorOptionS = hold;
/*  2556 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public double standardDeviation_of_ComplexImaginaryParts() {
/*  2560 */     boolean hold = nFactorOptionS;
/*  2561 */     if (this.nFactorReset) {
/*  2562 */       if (this.nFactorOptionI) {
/*  2563 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2566 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2569 */     double[] im = array_as_imaginary_part_of_Complex();
/*  2570 */     double standardDeviation = standardDeviation(im);
/*  2571 */     nFactorOptionS = hold;
/*  2572 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation()
/*       */   {
/*  2577 */     return weightedStandardDeviation_as_double();
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation_as_double() {
/*  2581 */     boolean hold = nFactorOptionS;
/*  2582 */     if (this.nFactorReset) {
/*  2583 */       if (this.nFactorOptionI) {
/*  2584 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2587 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2590 */     boolean holdW = weightingOptionS;
/*  2591 */     if (this.weightingReset) {
/*  2592 */       if (this.weightingOptionI) {
/*  2593 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2596 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2600 */     double varr = 0.0D;
/*  2601 */     if (!this.weightsSupplied) {
/*  2602 */       System.out.println("weightedStandardDeviation_as_double: no weights supplied - unweighted value returned");
/*  2603 */       varr = standardDeviation_as_double();
/*       */     }
/*       */     else {
/*  2606 */       double variance = 0.0D;
/*  2607 */       switch (this.type) {
/*  2608 */       case 1:  double[] dd = getArray_as_double();
/*  2609 */         double[] ww = this.amWeights.getArray_as_double();
/*  2610 */         variance = variance(dd, ww);
/*  2611 */         break;
/*  2612 */       case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  2613 */         BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  2614 */         variance = variance(bd, wd).doubleValue();
/*  2615 */         bd = null;
/*  2616 */         wd = null;
/*  2617 */         break;
/*  2618 */       case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  2619 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*  2621 */       varr = Math.sqrt(variance);
/*       */     }
/*       */     
/*  2624 */     nFactorOptionS = hold;
/*  2625 */     weightingOptionS = holdW;
/*  2626 */     return varr;
/*       */   }
/*       */   
/*       */   public Complex weightedStandardDeviation_as_Complex()
/*       */   {
/*  2631 */     boolean hold = nFactorOptionS;
/*  2632 */     if (this.nFactorReset) {
/*  2633 */       if (this.nFactorOptionI) {
/*  2634 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2637 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2640 */     boolean holdW = weightingOptionS;
/*  2641 */     if (this.weightingReset) {
/*  2642 */       if (this.weightingOptionI) {
/*  2643 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2646 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2650 */     Complex varr = Complex.zero();
/*  2651 */     if (!this.weightsSupplied) {
/*  2652 */       System.out.println("weightedtandardDeviationS_as_Complex: no weights supplied - unweighted value returned");
/*  2653 */       varr = standardDeviation_as_Complex();
/*       */     }
/*       */     else {
/*  2656 */       Complex variance = Complex.zero();
/*  2657 */       Complex[] cc = getArray_as_Complex();
/*  2658 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  2659 */       variance = variance(cc, wc);
/*  2660 */       varr = Complex.sqrt(variance);
/*       */     }
/*  2662 */     nFactorOptionS = hold;
/*  2663 */     weightingOptionS = holdW;
/*  2664 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation_as_Complex_ConjugateCalcn()
/*       */   {
/*  2669 */     boolean hold = nFactorOptionS;
/*  2670 */     if (this.nFactorReset) {
/*  2671 */       if (this.nFactorOptionI) {
/*  2672 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2675 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2678 */     boolean holdW = weightingOptionS;
/*  2679 */     if (this.weightingReset) {
/*  2680 */       if (this.weightingOptionI) {
/*  2681 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2684 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2687 */     double varr = NaN.0D;
/*  2688 */     if (!this.weightsSupplied) {
/*  2689 */       System.out.println("weightedtandardDeviationS_as_Complex: no weights supplied - unweighted value returned");
/*  2690 */       varr = standardDeviation_as_Complex_ConjugateCalcn();
/*       */     }
/*       */     else {
/*  2693 */       double variance = NaN.0D;
/*  2694 */       Complex[] cc = getArray_as_Complex();
/*  2695 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  2696 */       variance = varianceConjugateCalcn(cc, wc);
/*  2697 */       varr = Math.sqrt(variance);
/*       */     }
/*  2699 */     nFactorOptionS = hold;
/*  2700 */     weightingOptionS = holdW;
/*  2701 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation_of_ComplexModuli()
/*       */   {
/*  2706 */     boolean hold = nFactorOptionS;
/*  2707 */     if (this.nFactorReset) {
/*  2708 */       if (this.nFactorOptionI) {
/*  2709 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2712 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2715 */     boolean hold2 = nEffOptionS;
/*  2716 */     if (this.nEffReset) {
/*  2717 */       if (this.nEffOptionI) {
/*  2718 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2721 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2724 */     boolean holdW = weightingOptionS;
/*  2725 */     if (this.weightingReset) {
/*  2726 */       if (this.weightingOptionI) {
/*  2727 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2730 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2733 */     double varr = NaN.0D;
/*  2734 */     if (!this.weightsSupplied) {
/*  2735 */       System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
/*  2736 */       varr = standardDeviation_of_ComplexModuli();
/*       */     }
/*       */     else {
/*  2739 */       double[] cc = array_as_modulus_of_Complex();
/*  2740 */       double[] wc = this.amWeights.array_as_modulus_of_Complex();
/*  2741 */       varr = standardDeviation(cc, wc);
/*       */     }
/*  2743 */     nFactorOptionS = hold;
/*  2744 */     nEffOptionS = hold2;
/*  2745 */     weightingOptionS = holdW;
/*  2746 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation_of_ComplexRealParts() {
/*  2750 */     boolean hold = nFactorOptionS;
/*  2751 */     if (this.nFactorReset) {
/*  2752 */       if (this.nFactorOptionI) {
/*  2753 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2756 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2759 */     boolean hold2 = nEffOptionS;
/*  2760 */     if (this.nEffReset) {
/*  2761 */       if (this.nEffOptionI) {
/*  2762 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2765 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2768 */     boolean holdW = weightingOptionS;
/*  2769 */     if (this.weightingReset) {
/*  2770 */       if (this.weightingOptionI) {
/*  2771 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2774 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2777 */     double varr = NaN.0D;
/*  2778 */     if (!this.weightsSupplied) {
/*  2779 */       System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
/*  2780 */       varr = standardDeviation_of_ComplexRealParts();
/*       */     }
/*       */     else {
/*  2783 */       double[] cc = array_as_real_part_of_Complex();
/*  2784 */       double[] wc = this.amWeights.array_as_real_part_of_Complex();
/*  2785 */       varr = standardDeviation(cc, wc);
/*       */     }
/*  2787 */     nFactorOptionS = hold;
/*  2788 */     nEffOptionS = hold2;
/*  2789 */     weightingOptionS = holdW;
/*  2790 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardDeviation_of_ComplexImaginaryParts()
/*       */   {
/*  2795 */     boolean hold = nFactorOptionS;
/*  2796 */     if (this.nFactorReset) {
/*  2797 */       if (this.nFactorOptionI) {
/*  2798 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2801 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2804 */     boolean hold2 = nEffOptionS;
/*  2805 */     if (this.nEffReset) {
/*  2806 */       if (this.nEffOptionI) {
/*  2807 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2810 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  2813 */     boolean holdW = weightingOptionS;
/*  2814 */     if (this.weightingReset) {
/*  2815 */       if (this.weightingOptionI) {
/*  2816 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2819 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  2822 */     double varr = NaN.0D;
/*  2823 */     if (!this.weightsSupplied) {
/*  2824 */       System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
/*  2825 */       varr = standardDeviation_of_ComplexImaginaryParts();
/*       */     }
/*       */     else {
/*  2828 */       double[] cc = array_as_imaginary_part_of_Complex();
/*  2829 */       double[] wc = this.amWeights.array_as_imaginary_part_of_Complex();
/*  2830 */       varr = standardDeviation(cc, wc);
/*       */     }
/*  2832 */     nFactorOptionS = hold;
/*  2833 */     nEffOptionS = hold2;
/*  2834 */     weightingOptionS = holdW;
/*  2835 */     return varr;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double standardError()
/*       */   {
/*  2844 */     return standardError_as_double();
/*       */   }
/*       */   
/*       */   public double standardError_as_double() {
/*  2848 */     boolean hold = nFactorOptionS;
/*  2849 */     if (this.nFactorReset) {
/*  2850 */       if (this.nFactorOptionI) {
/*  2851 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2854 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2858 */     double standardError = 0.0D;
/*  2859 */     switch (this.type) {
/*  2860 */     case 1:  double[] dd = getArray_as_double();
/*  2861 */       standardError = standardError(dd);
/*  2862 */       break;
/*  2863 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  2864 */       standardError = standardError(bd);
/*  2865 */       bd = null;
/*  2866 */       break;
/*  2867 */     case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  2868 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  2870 */     nFactorOptionS = hold;
/*  2871 */     return standardError;
/*       */   }
/*       */   
/*       */   public Complex standardError_as_Complex() {
/*  2875 */     boolean hold = nFactorOptionS;
/*  2876 */     if (this.nFactorReset) {
/*  2877 */       if (this.nFactorOptionI) {
/*  2878 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2881 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2885 */     Complex standardError = Complex.zero();
/*  2886 */     Complex[] cc = getArray_as_Complex();
/*  2887 */     standardError = standardError(cc);
/*  2888 */     nFactorOptionS = hold;
/*  2889 */     return standardError;
/*       */   }
/*       */   
/*       */   public double standardError_as_Complex_ConjugateCalcn() {
/*  2893 */     boolean hold = nFactorOptionS;
/*  2894 */     if (this.nFactorReset) {
/*  2895 */       if (this.nFactorOptionI) {
/*  2896 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2899 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2902 */     Complex[] cc = getArray_as_Complex();
/*  2903 */     double standardError = standardErrorConjugateCalcn(cc);
/*  2904 */     nFactorOptionS = hold;
/*  2905 */     return standardError;
/*       */   }
/*       */   
/*       */   public double standardError_of_ComplexModuli() {
/*  2909 */     boolean hold = nFactorOptionS;
/*  2910 */     if (this.nFactorReset) {
/*  2911 */       if (this.nFactorOptionI) {
/*  2912 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2915 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2918 */     double[] re = array_as_modulus_of_Complex();
/*  2919 */     double standardError = standardError(re);
/*  2920 */     nFactorOptionS = hold;
/*  2921 */     return standardError;
/*       */   }
/*       */   
/*       */   public double standardError_of_ComplexRealParts() {
/*  2925 */     boolean hold = nFactorOptionS;
/*  2926 */     if (this.nFactorReset) {
/*  2927 */       if (this.nFactorOptionI) {
/*  2928 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2931 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2934 */     double[] re = array_as_real_part_of_Complex();
/*  2935 */     double standardError = standardError(re);
/*  2936 */     nFactorOptionS = hold;
/*  2937 */     return standardError;
/*       */   }
/*       */   
/*       */   public double standardError_of_ComplexImaginaryParts() {
/*  2941 */     boolean hold = nFactorOptionS;
/*  2942 */     if (this.nFactorReset) {
/*  2943 */       if (this.nFactorOptionI) {
/*  2944 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2947 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  2950 */     double[] re = array_as_imaginary_part_of_Complex();
/*  2951 */     double standardError = standardError(re);
/*  2952 */     nFactorOptionS = hold;
/*  2953 */     return standardError;
/*       */   }
/*       */   
/*       */   public double weightedStandardError()
/*       */   {
/*  2958 */     return weightedStandardError_as_double();
/*       */   }
/*       */   
/*       */   public double weightedStandardError_as_double() {
/*  2962 */     boolean hold = nFactorOptionS;
/*  2963 */     if (this.nFactorReset) {
/*  2964 */       if (this.nFactorOptionI) {
/*  2965 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  2968 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2972 */     boolean hold2 = nEffOptionS;
/*  2973 */     if (this.nEffReset) {
/*  2974 */       if (this.nEffOptionI) {
/*  2975 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  2978 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2982 */     boolean holdW = weightingOptionS;
/*  2983 */     if (this.weightingReset) {
/*  2984 */       if (this.weightingOptionI) {
/*  2985 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  2988 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  2992 */     double standardError = 0.0D;
/*  2993 */     if (!this.weightsSupplied) {
/*  2994 */       System.out.println("weightedStandardError_as_double: no weights supplied - unweighted value returned");
/*  2995 */       standardError = standardError_as_double();
/*       */     }
/*       */     else {
/*  2998 */       switch (this.type) {
/*  2999 */       case 1:  double[] dd = getArray_as_double();
/*  3000 */         double[] ww = this.amWeights.getArray_as_double();
/*  3001 */         standardError = standardError(dd, ww);
/*  3002 */         break;
/*  3003 */       case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3004 */         BigDecimal[] wd = this.amWeights.getArray_as_BigDecimal();
/*  3005 */         standardError = standardError(bd, wd);
/*  3006 */         bd = null;
/*  3007 */         wd = null;
/*  3008 */         break;
/*  3009 */       case 14:  throw new IllegalArgumentException("Complex cannot be converted to double");
/*  3010 */       default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */       }
/*  3012 */       standardError = Math.sqrt(standardError);
/*       */     }
/*  3014 */     nFactorOptionS = hold;
/*  3015 */     nEffOptionS = hold2;
/*  3016 */     weightingOptionS = holdW;
/*  3017 */     return standardError;
/*       */   }
/*       */   
/*       */   public Complex weightedStandarError_as_Complex()
/*       */   {
/*  3022 */     boolean hold = nFactorOptionS;
/*  3023 */     if (this.nFactorReset) {
/*  3024 */       if (this.nFactorOptionI) {
/*  3025 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3028 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  3032 */     boolean hold2 = nEffOptionS;
/*  3033 */     if (this.nEffReset) {
/*  3034 */       if (this.nEffOptionI) {
/*  3035 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  3038 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  3042 */     boolean holdW = weightingOptionS;
/*  3043 */     if (this.weightingReset) {
/*  3044 */       if (this.weightingOptionI) {
/*  3045 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3048 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  3052 */     Complex standardError = Complex.zero();
/*  3053 */     if (!this.weightsSupplied) {
/*  3054 */       System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
/*  3055 */       standardError = standardError_as_Complex();
/*       */     }
/*       */     else {
/*  3058 */       Complex[] cc = getArray_as_Complex();
/*  3059 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  3060 */       standardError = standardError(cc, wc);
/*       */     }
/*  3062 */     nFactorOptionS = hold;
/*  3063 */     nEffOptionS = hold2;
/*  3064 */     weightingOptionS = holdW;
/*       */     
/*  3066 */     return standardError;
/*       */   }
/*       */   
/*       */ 
/*       */   public double weightedStandarError_as_Complex_ConjugateCalcn()
/*       */   {
/*  3072 */     boolean hold = nFactorOptionS;
/*  3073 */     if (this.nFactorReset) {
/*  3074 */       if (this.nFactorOptionI) {
/*  3075 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3078 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  3082 */     boolean hold2 = nEffOptionS;
/*  3083 */     if (this.nEffReset) {
/*  3084 */       if (this.nEffOptionI) {
/*  3085 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  3088 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*       */     
/*  3092 */     boolean holdW = weightingOptionS;
/*  3093 */     if (this.weightingReset) {
/*  3094 */       if (this.weightingOptionI) {
/*  3095 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3098 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  3101 */     double standardError = NaN.0D;
/*  3102 */     if (!this.weightsSupplied) {
/*  3103 */       System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
/*  3104 */       standardError = standardError_as_Complex_ConjugateCalcn();
/*       */     }
/*       */     else {
/*  3107 */       Complex[] cc = getArray_as_Complex();
/*  3108 */       Complex[] wc = this.amWeights.getArray_as_Complex();
/*  3109 */       standardError = standardErrorConjugateCalcn(cc, wc);
/*       */     }
/*  3111 */     nFactorOptionS = hold;
/*  3112 */     nEffOptionS = hold2;
/*  3113 */     weightingOptionS = holdW;
/*       */     
/*  3115 */     return standardError;
/*       */   }
/*       */   
/*       */   public double weightedStandardError_of_ComplexModuli()
/*       */   {
/*  3120 */     boolean hold = nFactorOptionS;
/*  3121 */     if (this.nFactorReset) {
/*  3122 */       if (this.nFactorOptionI) {
/*  3123 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3126 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  3129 */     boolean hold2 = nEffOptionS;
/*  3130 */     if (this.nEffReset) {
/*  3131 */       if (this.nEffOptionI) {
/*  3132 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  3135 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  3138 */     boolean holdW = weightingOptionS;
/*  3139 */     if (this.weightingReset) {
/*  3140 */       if (this.weightingOptionI) {
/*  3141 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3144 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  3147 */     double varr = NaN.0D;
/*  3148 */     if (!this.weightsSupplied) {
/*  3149 */       System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
/*  3150 */       varr = standardError_of_ComplexModuli();
/*       */     }
/*       */     else {
/*  3153 */       double[] cc = array_as_modulus_of_Complex();
/*  3154 */       double[] wc = this.amWeights.array_as_modulus_of_Complex();
/*  3155 */       varr = standardError(cc, wc);
/*       */     }
/*  3157 */     nFactorOptionS = hold;
/*  3158 */     nEffOptionS = hold2;
/*  3159 */     weightingOptionS = holdW;
/*  3160 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardError_of_ComplexRealParts() {
/*  3164 */     boolean hold = nFactorOptionS;
/*  3165 */     if (this.nFactorReset) {
/*  3166 */       if (this.nFactorOptionI) {
/*  3167 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3170 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  3173 */     boolean hold2 = nEffOptionS;
/*  3174 */     if (this.nEffReset) {
/*  3175 */       if (this.nEffOptionI) {
/*  3176 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  3179 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  3182 */     boolean holdW = weightingOptionS;
/*  3183 */     if (this.weightingReset) {
/*  3184 */       if (this.weightingOptionI) {
/*  3185 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3188 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  3191 */     double varr = NaN.0D;
/*  3192 */     if (!this.weightsSupplied) {
/*  3193 */       System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
/*  3194 */       varr = standardError_of_ComplexRealParts();
/*       */     }
/*       */     else {
/*  3197 */       double[] cc = array_as_real_part_of_Complex();
/*  3198 */       double[] wc = this.amWeights.array_as_real_part_of_Complex();
/*  3199 */       varr = standardError(cc, wc);
/*       */     }
/*  3201 */     nFactorOptionS = hold;
/*  3202 */     nEffOptionS = hold2;
/*  3203 */     weightingOptionS = holdW;
/*  3204 */     return varr;
/*       */   }
/*       */   
/*       */   public double weightedStandardError_of_ComplexImaginaryParts()
/*       */   {
/*  3209 */     boolean hold = nFactorOptionS;
/*  3210 */     if (this.nFactorReset) {
/*  3211 */       if (this.nFactorOptionI) {
/*  3212 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3215 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  3218 */     boolean hold2 = nEffOptionS;
/*  3219 */     if (this.nEffReset) {
/*  3220 */       if (this.nEffOptionI) {
/*  3221 */         nEffOptionS = true;
/*       */       }
/*       */       else {
/*  3224 */         nEffOptionS = false;
/*       */       }
/*       */     }
/*  3227 */     boolean holdW = weightingOptionS;
/*  3228 */     if (this.weightingReset) {
/*  3229 */       if (this.weightingOptionI) {
/*  3230 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3233 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  3236 */     double varr = NaN.0D;
/*  3237 */     if (!this.weightsSupplied) {
/*  3238 */       System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
/*  3239 */       varr = standardError_of_ComplexImaginaryParts();
/*       */     }
/*       */     else {
/*  3242 */       double[] cc = array_as_imaginary_part_of_Complex();
/*  3243 */       double[] wc = this.amWeights.array_as_imaginary_part_of_Complex();
/*  3244 */       varr = standardError(cc, wc);
/*       */     }
/*  3246 */     nFactorOptionS = hold;
/*  3247 */     nEffOptionS = hold2;
/*  3248 */     weightingOptionS = holdW;
/*  3249 */     return varr;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double[] standardize()
/*       */   {
/*  3258 */     double[] bb = null;
/*  3259 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3261 */       double[] dd = getArray_as_double();
/*  3262 */       bb = standardize(dd);
/*  3263 */       break;
/*  3264 */     case 14:  throw new IllegalArgumentException("Standardization of Complex is not supported");
/*  3265 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3267 */     return bb;
/*       */   }
/*       */   
/*       */   public double[] standardise() {
/*  3271 */     return standardize();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double[] scale(double mean, double sd)
/*       */   {
/*  3278 */     double[] bb = null;
/*  3279 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3281 */       double[] dd = getArray_as_double();
/*  3282 */       bb = scale(dd, mean, sd);
/*  3283 */       break;
/*  3284 */     case 14:  throw new IllegalArgumentException("Scaling of Complex is not supported");
/*  3285 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3287 */     return bb;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double volatilityLogChange()
/*       */   {
/*  3294 */     double volatilityLogChange = 0.0D;
/*  3295 */     switch (this.type) {
/*  3296 */     case 1:  double[] dd = getArray_as_double();
/*  3297 */       volatilityLogChange = volatilityLogChange(dd);
/*  3298 */       break;
/*  3299 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3300 */       volatilityLogChange = volatilityLogChange(bd);
/*  3301 */       bd = null;
/*  3302 */       break;
/*  3303 */     case 14:  throw new IllegalArgumentException("Complex volatilty is not supported");
/*  3304 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3306 */     return volatilityLogChange;
/*       */   }
/*       */   
/*       */   public double volatilityPerCentChange() {
/*  3310 */     double volatilityPerCentChange = 0.0D;
/*  3311 */     switch (this.type) {
/*  3312 */     case 1:  double[] dd = getArray_as_double();
/*  3313 */       volatilityPerCentChange = volatilityPerCentChange(dd);
/*  3314 */       break;
/*  3315 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3316 */       volatilityPerCentChange = volatilityPerCentChange(bd);
/*  3317 */       bd = null;
/*  3318 */       break;
/*  3319 */     case 14:  throw new IllegalArgumentException("Complex volatilty is not supported");
/*  3320 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3322 */     return volatilityPerCentChange;
/*       */   }
/*       */   
/*       */   public double coefficientOfVariation()
/*       */   {
/*  3327 */     boolean hold = nFactorOptionS;
/*  3328 */     if (this.nFactorReset) {
/*  3329 */       if (this.nFactorOptionI) {
/*  3330 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3333 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  3336 */     double coefficientOfVariation = 0.0D;
/*  3337 */     switch (this.type) {
/*  3338 */     case 1:  double[] dd = getArray_as_double();
/*  3339 */       coefficientOfVariation = coefficientOfVariation(dd);
/*  3340 */       break;
/*  3341 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3342 */       coefficientOfVariation = coefficientOfVariation(bd);
/*  3343 */       bd = null;
/*  3344 */       break;
/*  3345 */     case 14:  throw new IllegalArgumentException("Complex coefficient of variation is not supported");
/*  3346 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3348 */     nFactorOptionS = hold;
/*  3349 */     return coefficientOfVariation;
/*       */   }
/*       */   
/*       */   public double weightedCoefficientOfVariation() {
/*  3353 */     boolean hold = nFactorOptionS;
/*  3354 */     if (this.nFactorReset) {
/*  3355 */       if (this.nFactorOptionI) {
/*  3356 */         nFactorOptionS = true;
/*       */       }
/*       */       else {
/*  3359 */         nFactorOptionS = false;
/*       */       }
/*       */     }
/*  3362 */     boolean holdW = weightingOptionS;
/*  3363 */     if (this.weightingReset) {
/*  3364 */       if (this.weightingOptionI) {
/*  3365 */         weightingOptionS = true;
/*       */       }
/*       */       else {
/*  3368 */         weightingOptionS = false;
/*       */       }
/*       */     }
/*  3371 */     double coefficientOfVariation = 0.0D;
/*  3372 */     switch (this.type) {
/*  3373 */     case 1:  double[] dd = getArray_as_double();
/*  3374 */       double[] wd = this.amWeights.getArray_as_double();
/*  3375 */       coefficientOfVariation = coefficientOfVariation(dd, wd);
/*  3376 */       break;
/*  3377 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3378 */       BigDecimal[] bw = this.amWeights.getArray_as_BigDecimal();
/*  3379 */       coefficientOfVariation = coefficientOfVariation(bd, bw);
/*  3380 */       bd = null;
/*  3381 */       bw = null;
/*  3382 */       break;
/*  3383 */     case 14:  throw new IllegalArgumentException("Complex coefficient of variation is not supported");
/*  3384 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3386 */     nFactorOptionS = hold;
/*  3387 */     weightingOptionS = holdW;
/*  3388 */     return coefficientOfVariation;
/*       */   }
/*       */   
/*       */ 
/*       */   public double shannonEntropy()
/*       */   {
/*  3394 */     double entropy = 0.0D;
/*  3395 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3397 */       double[] dd = getArray_as_double();
/*  3398 */       entropy = shannonEntropy(dd);
/*  3399 */       break;
/*  3400 */     case 14:  throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
/*  3401 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3403 */     return entropy;
/*       */   }
/*       */   
/*       */   public double shannonEntropyBit()
/*       */   {
/*  3408 */     double entropy = 0.0D;
/*  3409 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3411 */       double[] dd = getArray_as_double();
/*  3412 */       entropy = shannonEntropy(dd);
/*  3413 */       break;
/*  3414 */     case 14:  throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
/*  3415 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3417 */     return entropy;
/*       */   }
/*       */   
/*       */   public double shannonEntropyNat()
/*       */   {
/*  3422 */     double entropy = 0.0D;
/*  3423 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3425 */       double[] dd = getArray_as_double();
/*  3426 */       entropy = shannonEntropyNat(dd);
/*  3427 */       break;
/*  3428 */     case 14:  throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
/*  3429 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3431 */     return entropy;
/*       */   }
/*       */   
/*       */   public double shannonEntropyDit()
/*       */   {
/*  3436 */     double entropy = 0.0D;
/*  3437 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3439 */       double[] dd = getArray_as_double();
/*  3440 */       entropy = shannonEntropyDit(dd);
/*  3441 */       break;
/*  3442 */     case 14:  throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
/*  3443 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3445 */     return entropy;
/*       */   }
/*       */   
/*       */ 
/*       */   public double renyiEntropy(double alpha)
/*       */   {
/*  3451 */     double entropy = 0.0D;
/*  3452 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3454 */       double[] dd = getArray_as_double();
/*  3455 */       entropy = renyiEntropy(dd, alpha);
/*  3456 */       break;
/*  3457 */     case 14:  throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
/*  3458 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3460 */     return entropy;
/*       */   }
/*       */   
/*       */   public double renyiEntropyBit(double alpha)
/*       */   {
/*  3465 */     double entropy = 0.0D;
/*  3466 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3468 */       double[] dd = getArray_as_double();
/*  3469 */       entropy = renyiEntropy(dd, alpha);
/*  3470 */       break;
/*  3471 */     case 14:  throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
/*  3472 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3474 */     return entropy;
/*       */   }
/*       */   
/*       */   public double renyiEntropyNat(double alpha)
/*       */   {
/*  3479 */     double entropy = 0.0D;
/*  3480 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3482 */       double[] dd = getArray_as_double();
/*  3483 */       entropy = renyiEntropyNat(dd, alpha);
/*  3484 */       break;
/*  3485 */     case 14:  throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
/*  3486 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3488 */     return entropy;
/*       */   }
/*       */   
/*       */   public double renyiEntropyDit(double alpha)
/*       */   {
/*  3493 */     double entropy = 0.0D;
/*  3494 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3496 */       double[] dd = getArray_as_double();
/*  3497 */       entropy = renyiEntropyDit(dd, alpha);
/*  3498 */       break;
/*  3499 */     case 14:  throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
/*  3500 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3502 */     return entropy;
/*       */   }
/*       */   
/*       */ 
/*       */   public double tsallisEntropyNat(double q)
/*       */   {
/*  3508 */     double entropy = 0.0D;
/*  3509 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3511 */       double[] dd = getArray_as_double();
/*  3512 */       entropy = tsallisEntropyNat(dd, q);
/*  3513 */       break;
/*  3514 */     case 14:  throw new IllegalArgumentException("Complex Tsallis Entropy is not meaningful");
/*  3515 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3517 */     return entropy;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public double generalizedEntropyOneNat(double q, double r)
/*       */   {
/*  3524 */     double entropy = 0.0D;
/*  3525 */     switch (this.type) {
/*       */     case 1: case 12: 
/*  3527 */       double[] dd = getArray_as_double();
/*  3528 */       entropy = generalizedEntropyOneNat(dd, q, r);
/*  3529 */       break;
/*  3530 */     case 14:  throw new IllegalArgumentException("Complex Generalized Entropy is not meaningful");
/*  3531 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3533 */     return entropy;
/*       */   }
/*       */   
/*       */   public double generalisedEntropyOneNat(double q, double r) {
/*  3537 */     return generalizedEntropyOneNat(q, r);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayList<Object> upperOutliersAnscombe(double constant)
/*       */   {
/*  3543 */     return upperOutliersAnscombe_as_double(constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayList<Object> upperOutliersAnscombe_as_double(double constant)
/*       */   {
/*  3549 */     switch (this.type) {
/*  3550 */     case 1:  double[] dd = getArray_as_double();
/*  3551 */       this.upperOutlierDetails = upperOutliersAnscombeAsArrayList(dd, constant);
/*  3552 */       break;
/*  3553 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3554 */       ArrayList<Object> ret = new ArrayList();
/*  3555 */       ret = upperOutliersAnscombeAsArrayList(bd, new BigDecimal(constant));
/*  3556 */       this.upperOutlierDetails.add((Integer)ret.get(0));
/*  3557 */       BigDecimal[] bd1 = (BigDecimal[])ret.get(1);
/*  3558 */       ArrayMaths am1 = new ArrayMaths(bd1);
/*  3559 */       this.upperOutlierDetails.add(am1.getArray_as_Double());
/*  3560 */       this.upperOutlierDetails.add((int[])ret.get(2));
/*  3561 */       BigDecimal[] bd2 = (BigDecimal[])ret.get(3);
/*  3562 */       ArrayMaths am2 = new ArrayMaths(bd2);
/*  3563 */       this.upperOutlierDetails.add(am2.getArray_as_Double());
/*  3564 */       break;
/*  3565 */     case 14:  throw new IllegalArgumentException("Outlier detection of Complex is not supported");
/*  3566 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3568 */     this.upperDone = true;
/*  3569 */     return this.upperOutlierDetails;
/*       */   }
/*       */   
/*       */   public ArrayList<Object> upperOutliersAnscombe(BigDecimal constant)
/*       */   {
/*  3574 */     return upperOutliersAnscombe_as_BigDecimal(constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayList<Object> upperOutliersAnscombe_as_BigDecimal(BigDecimal constant)
/*       */   {
/*  3580 */     switch (this.type) {
/*  3581 */     case 1:  double[] dd = getArray_as_double();
/*  3582 */       ArrayList<Object> ret = new ArrayList();
/*  3583 */       ret = upperOutliersAnscombeAsArrayList(dd, constant.doubleValue());
/*  3584 */       this.upperOutlierDetails.add((Integer)ret.get(0));
/*  3585 */       Double[] dd1 = (Double[])ret.get(1);
/*  3586 */       ArrayMaths am1 = new ArrayMaths(dd1);
/*  3587 */       this.upperOutlierDetails.add(am1.getArray_as_BigDecimal());
/*  3588 */       this.upperOutlierDetails.add((int[])ret.get(2));
/*  3589 */       Double[] dd2 = (Double[])ret.get(3);
/*  3590 */       ArrayMaths am2 = new ArrayMaths(dd2);
/*  3591 */       this.upperOutlierDetails.add(am2.getArray_as_BigDecimal());
/*  3592 */       break;
/*  3593 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3594 */       this.upperOutlierDetails = upperOutliersAnscombeAsArrayList(bd, constant);
/*  3595 */       break;
/*  3596 */     case 14:  throw new IllegalArgumentException("Outlier detection of Complex is not supported");
/*  3597 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3599 */     this.upperDone = true;
/*  3600 */     return this.upperOutlierDetails;
/*       */   }
/*       */   
/*       */   public ArrayList<Object> upperOutliersAnscombe(BigInteger constant)
/*       */   {
/*  3605 */     return upperOutliersAnscombe_as_BigDecimal(new BigDecimal(constant));
/*       */   }
/*       */   
/*       */   public ArrayList<Object> upperOutliersAnscombe_as_BigDecimal(BigInteger constant) {
/*  3609 */     return upperOutliersAnscombe_as_BigDecimal(new BigDecimal(constant));
/*       */   }
/*       */   
/*       */   public ArrayList<Object> lowerOutliersAnscombe(double constant)
/*       */   {
/*  3614 */     return lowerOutliersAnscombe_as_double(constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayList<Object> lowerOutliersAnscombe_as_double(double constant)
/*       */   {
/*  3620 */     switch (this.type) {
/*  3621 */     case 1:  double[] dd = getArray_as_double();
/*  3622 */       this.lowerOutlierDetails = lowerOutliersAnscombeAsArrayList(dd, constant);
/*  3623 */       break;
/*  3624 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3625 */       ArrayList<Object> ret = new ArrayList();
/*  3626 */       ret = lowerOutliersAnscombeAsArrayList(bd, new BigDecimal(constant));
/*  3627 */       this.lowerOutlierDetails.add((Integer)ret.get(0));
/*  3628 */       BigDecimal[] bd1 = (BigDecimal[])ret.get(1);
/*  3629 */       ArrayMaths am1 = new ArrayMaths(bd1);
/*  3630 */       this.lowerOutlierDetails.add(am1.getArray_as_Double());
/*  3631 */       this.lowerOutlierDetails.add((int[])ret.get(2));
/*  3632 */       BigDecimal[] bd2 = (BigDecimal[])ret.get(3);
/*  3633 */       ArrayMaths am2 = new ArrayMaths(bd2);
/*  3634 */       this.lowerOutlierDetails.add(am2.getArray_as_Double());
/*  3635 */       break;
/*  3636 */     case 14:  throw new IllegalArgumentException("Outlier detection of Complex is not supported");
/*  3637 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3639 */     this.lowerDone = true;
/*  3640 */     return this.lowerOutlierDetails;
/*       */   }
/*       */   
/*       */   public ArrayList<Object> lowerOutliersAnscombe(BigDecimal constant) {
/*  3644 */     return lowerOutliersAnscombe_as_BigDecimal(constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayList<Object> lowerOutliersAnscombe_as_BigDecimal(BigDecimal constant)
/*       */   {
/*  3650 */     switch (this.type) {
/*  3651 */     case 1:  double[] dd = getArray_as_double();
/*  3652 */       ArrayList<Object> ret = new ArrayList();
/*  3653 */       ret = lowerOutliersAnscombeAsArrayList(dd, constant.doubleValue());
/*  3654 */       this.lowerOutlierDetails.add((Integer)ret.get(0));
/*  3655 */       Double[] dd1 = (Double[])ret.get(1);
/*  3656 */       ArrayMaths am1 = new ArrayMaths(dd1);
/*  3657 */       this.lowerOutlierDetails.add(am1.getArray_as_BigDecimal());
/*  3658 */       this.lowerOutlierDetails.add((int[])ret.get(2));
/*  3659 */       Double[] dd2 = (Double[])ret.get(3);
/*  3660 */       ArrayMaths am2 = new ArrayMaths(dd2);
/*  3661 */       this.lowerOutlierDetails.add(am2.getArray_as_BigDecimal());
/*  3662 */       break;
/*  3663 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  3664 */       this.lowerOutlierDetails = lowerOutliersAnscombeAsArrayList(bd, constant);
/*  3665 */       break;
/*  3666 */     case 14:  throw new IllegalArgumentException("Outlier detection of Complex is not supported");
/*  3667 */     default:  throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
/*       */     }
/*  3669 */     this.lowerDone = true;
/*  3670 */     return this.lowerOutlierDetails;
/*       */   }
/*       */   
/*       */   public ArrayList<Object> lowerOutliersAnscombe(BigInteger constant) {
/*  3674 */     return lowerOutliersAnscombe_as_BigDecimal(new BigDecimal(constant));
/*       */   }
/*       */   
/*       */   public ArrayList<Object> lowerOutliersAnscombe_as_BigDecimal(BigInteger constant) {
/*  3678 */     return lowerOutliersAnscombe_as_BigDecimal(new BigDecimal(constant));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static void setStaticWeightsToBigW()
/*       */   {
/*  3686 */     weightingOptionS = false;
/*       */   }
/*       */   
/*       */   public static void setStaticWeightsToLittleW()
/*       */   {
/*  3691 */     weightingOptionS = true;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] convertBigWtoLittleW(double[] bigW)
/*       */   {
/*  3697 */     ArrayMaths am1 = new ArrayMaths(bigW);
/*  3698 */     ArrayMaths am2 = am1.oneOverSqrt();
/*  3699 */     return am2.getArray_as_double();
/*       */   }
/*       */   
/*       */   public static float[] convertBigWtoLittleW(float[] bigW) {
/*  3703 */     ArrayMaths am1 = new ArrayMaths(bigW);
/*  3704 */     ArrayMaths am2 = am1.oneOverSqrt();
/*  3705 */     return am2.getArray_as_float();
/*       */   }
/*       */   
/*       */   public static Complex[] convertBigWtoLittleW(Complex[] bigW) {
/*  3709 */     ArrayMaths am1 = new ArrayMaths(bigW);
/*  3710 */     ArrayMaths am2 = am1.oneOverSqrt();
/*  3711 */     return am2.getArray_as_Complex();
/*       */   }
/*       */   
/*       */   public static double[] convertBigWtoLittleW(BigDecimal[] bigW) {
/*  3715 */     ArrayMaths am1 = new ArrayMaths(bigW);
/*  3716 */     ArrayMaths am2 = am1.oneOverSqrt();
/*  3717 */     return am2.getArray_as_double();
/*       */   }
/*       */   
/*       */   public static double[] convertBigWtoLittleW(BigInteger[] bigW) {
/*  3721 */     ArrayMaths am1 = new ArrayMaths(bigW);
/*  3722 */     ArrayMaths am2 = am1.oneOverSqrt();
/*  3723 */     return am2.getArray_as_double();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private static double[] invertAndSquare(double[] ww)
/*       */   {
/*  3730 */     double[] weight = (double[])ww.clone();
/*  3731 */     if (weightingOptionS) {
/*  3732 */       ArrayMaths am = new ArrayMaths(ww);
/*  3733 */       am = am.pow(2);
/*  3734 */       am = am.invert();
/*  3735 */       weight = am.array();
/*       */     }
/*  3737 */     return weight;
/*       */   }
/*       */   
/*       */   private static float[] invertAndSquare(float[] ww) {
/*  3741 */     float[] weight = (float[])ww.clone();
/*  3742 */     if (weightingOptionS) {
/*  3743 */       ArrayMaths am = new ArrayMaths(ww);
/*  3744 */       am = am.pow(2);
/*  3745 */       am = am.invert();
/*  3746 */       weight = am.array_as_float();
/*       */     }
/*  3748 */     return weight;
/*       */   }
/*       */   
/*       */   private static Complex[] invertAndSquare(Complex[] ww) {
/*  3752 */     Complex[] weight = (Complex[])ww.clone();
/*  3753 */     if (weightingOptionS) {
/*  3754 */       ArrayMaths am = new ArrayMaths(ww);
/*  3755 */       am = am.pow(2);
/*  3756 */       am = am.invert();
/*  3757 */       weight = am.array_as_Complex();
/*       */     }
/*  3759 */     return weight;
/*       */   }
/*       */   
/*       */   private static BigDecimal[] invertAndSquare(BigDecimal[] ww) {
/*  3763 */     BigDecimal[] weight = (BigDecimal[])ww.clone();
/*  3764 */     if (weightingOptionS) {
/*  3765 */       ArrayMaths am = new ArrayMaths(ww);
/*  3766 */       am = am.pow(2);
/*  3767 */       am = am.invert();
/*  3768 */       weight = am.array_as_BigDecimal();
/*       */     }
/*  3770 */     return weight;
/*       */   }
/*       */   
/*       */   private static BigDecimal[] invertAndSquare(BigInteger[] ww) {
/*  3774 */     ArrayMaths am = new ArrayMaths(ww);
/*  3775 */     BigDecimal[] weight = am.array_as_BigDecimal();
/*  3776 */     if (weightingOptionS) {
/*  3777 */       am = am.pow(2);
/*  3778 */       am = am.invert();
/*  3779 */       weight = am.array_as_BigDecimal();
/*       */     }
/*  3781 */     return weight;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static void setStaticDenominatorToN()
/*       */   {
/*  3789 */     nFactorOptionS = true;
/*       */   }
/*       */   
/*       */   public static void setStaticDenominatorToNminusOne()
/*       */   {
/*  3794 */     nFactorOptionS = false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static void useStaticEffectiveN()
/*       */   {
/*  3801 */     nEffOptionS = true;
/*       */   }
/*       */   
/*       */   public static void useStaticTrueN()
/*       */   {
/*  3806 */     nEffOptionS = false;
/*       */   }
/*       */   
/*       */   public static double effectiveSampleNumber(double[] ww)
/*       */   {
/*  3811 */     double[] weight = (double[])ww.clone();
/*  3812 */     if (weightingOptionS) {
/*  3813 */       ArrayMaths am = new ArrayMaths(ww);
/*  3814 */       am = am.pow(2);
/*  3815 */       am = am.invert();
/*  3816 */       weight = am.array();
/*       */     }
/*  3818 */     int n = weight.length;
/*       */     
/*  3820 */     double nEff = n;
/*  3821 */     if (nEffOptionS) {
/*  3822 */       double sum2w = 0.0D;
/*  3823 */       double sumw2 = 0.0D;
/*  3824 */       for (int i = 0; i < n; i++) {
/*  3825 */         sum2w += weight[i];
/*  3826 */         sumw2 += weight[i] * weight[i];
/*       */       }
/*  3828 */       sum2w *= sum2w;
/*  3829 */       nEff = sum2w / sumw2;
/*       */     }
/*  3831 */     return nEff;
/*       */   }
/*       */   
/*       */   public static float effectiveSampleNumber(float[] ww)
/*       */   {
/*  3836 */     float[] weight = (float[])ww.clone();
/*  3837 */     if (weightingOptionS) {
/*  3838 */       ArrayMaths am = new ArrayMaths(ww);
/*  3839 */       am = am.pow(2);
/*  3840 */       am = am.invert();
/*  3841 */       weight = am.array_as_float();
/*       */     }
/*  3843 */     int n = weight.length;
/*       */     
/*  3845 */     float nEff = n;
/*  3846 */     if (nEffOptionS) {
/*  3847 */       float sum2w = 0.0F;
/*  3848 */       float sumw2 = 0.0F;
/*  3849 */       for (int i = 0; i < n; i++) {
/*  3850 */         sum2w += weight[i];
/*  3851 */         sumw2 += weight[i] * weight[i];
/*       */       }
/*  3853 */       sum2w *= sum2w;
/*  3854 */       nEff = sum2w / sumw2;
/*       */     }
/*  3856 */     return nEff;
/*       */   }
/*       */   
/*       */   public static Complex effectiveSampleNumber(Complex[] ww)
/*       */   {
/*  3861 */     Complex[] weight = (Complex[])ww.clone();
/*  3862 */     if (weightingOptionS) {
/*  3863 */       ArrayMaths am = new ArrayMaths(ww);
/*  3864 */       am = am.pow(2);
/*  3865 */       am = am.invert();
/*  3866 */       weight = am.array_as_Complex();
/*       */     }
/*  3868 */     int n = weight.length;
/*       */     
/*  3870 */     Complex nEff = new Complex(n, 0.0D);
/*  3871 */     if (nEffOptionS) {
/*  3872 */       Complex sumw2 = Complex.zero();
/*  3873 */       Complex sum2w = Complex.zero();
/*  3874 */       for (int i = 0; i < n; i++) {
/*  3875 */         sum2w = sum2w.plus(weight[i]);
/*  3876 */         sumw2 = sumw2.plus(weight[i].times(weight[i]));
/*       */       }
/*  3878 */       sum2w = sum2w.times(sum2w);
/*  3879 */       nEff = sum2w.over(sumw2);
/*       */     }
/*  3881 */     return nEff;
/*       */   }
/*       */   
/*       */   public static double effectiveSampleNumberConjugateCalcn(Complex[] ww)
/*       */   {
/*  3886 */     Complex[] weight = (Complex[])ww.clone();
/*  3887 */     if (weightingOptionS) {
/*  3888 */       ArrayMaths am = new ArrayMaths(ww);
/*  3889 */       am = am.pow(2);
/*  3890 */       am = am.invert();
/*  3891 */       weight = am.array_as_Complex();
/*       */     }
/*  3893 */     int n = weight.length;
/*       */     
/*  3895 */     double nEff = NaN.0D;
/*  3896 */     if (nEffOptionS) {
/*  3897 */       Complex sumw2 = Complex.zero();
/*  3898 */       Complex sum2w = Complex.zero();
/*  3899 */       for (int i = 0; i < n; i++) {
/*  3900 */         sum2w = sum2w.plus(weight[i]);
/*  3901 */         sumw2 = sumw2.plus(weight[i].times(weight[i].conjugate()));
/*       */       }
/*  3903 */       sum2w = sum2w.times(sum2w.conjugate());
/*  3904 */       nEff = sum2w.getReal() / sumw2.getReal();
/*       */     }
/*  3906 */     return nEff;
/*       */   }
/*       */   
/*       */   public static BigDecimal effectiveSampleNumber(BigDecimal[] ww)
/*       */   {
/*  3911 */     BigDecimal[] weight = (BigDecimal[])ww.clone();
/*  3912 */     if (weightingOptionS) {
/*  3913 */       ArrayMaths am = new ArrayMaths(ww);
/*  3914 */       am = am.pow(2);
/*  3915 */       am = am.invert();
/*  3916 */       weight = am.array_as_BigDecimal();
/*       */     }
/*  3918 */     int n = weight.length;
/*       */     
/*  3920 */     BigDecimal nEff = new BigDecimal(new Integer(n).toString());
/*  3921 */     if (nEffOptionS) {
/*  3922 */       BigDecimal sumw2 = BigDecimal.ZERO;
/*  3923 */       BigDecimal sum2w = BigDecimal.ZERO;
/*  3924 */       for (int i = 0; i < n; i++) {
/*  3925 */         sum2w = sum2w.add(weight[i]);
/*  3926 */         sumw2 = sumw2.add(weight[i].multiply(weight[i]));
/*       */       }
/*  3928 */       sum2w = sum2w.multiply(sum2w);
/*  3929 */       nEff = sum2w.divide(sumw2, 4);
/*  3930 */       sumw2 = null;
/*  3931 */       sum2w = null;
/*  3932 */       weight = null;
/*       */     }
/*  3934 */     return nEff;
/*       */   }
/*       */   
/*       */   public static BigDecimal effectiveSampleNumber(BigInteger[] ww) {
/*  3938 */     ArrayMaths am = new ArrayMaths(ww);
/*  3939 */     BigDecimal[] www = am.array_as_BigDecimal();
/*  3940 */     return effectiveSampleNumber(www);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double mean(double[] aa)
/*       */   {
/*  3948 */     int n = aa.length;
/*  3949 */     double sum = 0.0D;
/*  3950 */     for (int i = 0; i < n; i++) {
/*  3951 */       sum += aa[i];
/*       */     }
/*  3953 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static float mean(float[] aa)
/*       */   {
/*  3958 */     int n = aa.length;
/*  3959 */     float sum = 0.0F;
/*  3960 */     for (int i = 0; i < n; i++) {
/*  3961 */       sum += aa[i];
/*       */     }
/*  3963 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static double mean(long[] aa)
/*       */   {
/*  3968 */     int n = aa.length;
/*  3969 */     double sum = 0.0D;
/*  3970 */     for (int i = 0; i < n; i++) {
/*  3971 */       sum += aa[i];
/*       */     }
/*  3973 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static double mean(int[] aa)
/*       */   {
/*  3978 */     int n = aa.length;
/*  3979 */     double sum = 0.0D;
/*  3980 */     for (int i = 0; i < n; i++) {
/*  3981 */       sum += aa[i];
/*       */     }
/*  3983 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static double mean(short[] aa)
/*       */   {
/*  3988 */     int n = aa.length;
/*  3989 */     double sum = 0.0D;
/*  3990 */     for (int i = 0; i < n; i++) {
/*  3991 */       sum += aa[i];
/*       */     }
/*  3993 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static double mean(byte[] aa)
/*       */   {
/*  3998 */     int n = aa.length;
/*  3999 */     double sum = 0.0D;
/*  4000 */     for (int i = 0; i < n; i++) {
/*  4001 */       sum += aa[i];
/*       */     }
/*  4003 */     return sum / n;
/*       */   }
/*       */   
/*       */   public static Complex mean(Complex[] aa)
/*       */   {
/*  4008 */     int n = aa.length;
/*  4009 */     Complex sum = new Complex(0.0D, 0.0D);
/*  4010 */     for (int i = 0; i < n; i++) {
/*  4011 */       sum = sum.plus(aa[i]);
/*       */     }
/*  4013 */     return sum.over(n);
/*       */   }
/*       */   
/*       */   public static BigDecimal mean(BigDecimal[] aa)
/*       */   {
/*  4018 */     int n = aa.length;
/*  4019 */     BigDecimal sum = BigDecimal.ZERO;
/*  4020 */     for (int i = 0; i < n; i++) {
/*  4021 */       sum = sum.add(aa[i]);
/*       */     }
/*  4023 */     return sum.divide(new BigDecimal(n), 4);
/*       */   }
/*       */   
/*       */   public static BigDecimal mean(BigInteger[] aa)
/*       */   {
/*  4028 */     int n = aa.length;
/*  4029 */     BigDecimal sum = BigDecimal.ZERO;
/*  4030 */     BigDecimal bi = BigDecimal.ZERO;
/*  4031 */     for (int i = 0; i < n; i++) {
/*  4032 */       bi = new BigDecimal(aa[i]);
/*  4033 */       sum = sum.add(bi);
/*       */     }
/*  4035 */     bi = null;
/*  4036 */     return sum.divide(new BigDecimal(n), 4);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double mean(double[] aa, double[] ww)
/*       */   {
/*  4045 */     int n = aa.length;
/*  4046 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4047 */     double[] weight = (double[])ww.clone();
/*  4048 */     if (weightingOptionS) {
/*  4049 */       ArrayMaths am = new ArrayMaths(ww);
/*  4050 */       am = am.pow(2);
/*  4051 */       am = am.invert();
/*  4052 */       weight = am.array();
/*       */     }
/*  4054 */     double sumx = 0.0D;
/*  4055 */     double sumw = 0.0D;
/*  4056 */     for (int i = 0; i < n; i++) {
/*  4057 */       sumx += aa[i] * weight[i];
/*  4058 */       sumw += weight[i];
/*       */     }
/*  4060 */     return sumx / sumw;
/*       */   }
/*       */   
/*       */   public static float mean(float[] aa, float[] ww)
/*       */   {
/*  4065 */     int n = aa.length;
/*  4066 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4067 */     float[] weight = (float[])ww.clone();
/*  4068 */     if (weightingOptionS) {
/*  4069 */       ArrayMaths am = new ArrayMaths(ww);
/*  4070 */       am = am.pow(2);
/*  4071 */       am = am.invert();
/*  4072 */       weight = am.array_as_float();
/*       */     }
/*       */     
/*  4075 */     float sumx = 0.0F;
/*  4076 */     float sumw = 0.0F;
/*  4077 */     for (int i = 0; i < n; i++) {
/*  4078 */       sumx += aa[i] * weight[i];
/*  4079 */       sumw += weight[i];
/*       */     }
/*  4081 */     return sumx / sumw;
/*       */   }
/*       */   
/*       */   public static Complex mean(Complex[] aa, Complex[] ww)
/*       */   {
/*  4086 */     int n = aa.length;
/*  4087 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4088 */     Complex[] weight = (Complex[])ww.clone();
/*  4089 */     if (weightingOptionS) {
/*  4090 */       ArrayMaths am = new ArrayMaths(ww);
/*  4091 */       am = am.pow(2);
/*  4092 */       am = am.invert();
/*  4093 */       weight = am.array_as_Complex();
/*       */     }
/*  4095 */     Complex sumx = Complex.zero();
/*  4096 */     Complex sumw = Complex.zero();
/*  4097 */     for (int i = 0; i < n; i++) {
/*  4098 */       sumx = sumx.plus(aa[i].times(weight[i]));
/*  4099 */       sumw = sumw.plus(weight[i]);
/*       */     }
/*  4101 */     return sumx.over(sumw);
/*       */   }
/*       */   
/*       */   public static BigDecimal mean(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  4106 */     int n = aa.length;
/*  4107 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4108 */     BigDecimal[] weight = (BigDecimal[])ww.clone();
/*  4109 */     if (weightingOptionS) {
/*  4110 */       ArrayMaths am = new ArrayMaths(ww);
/*  4111 */       am = am.pow(2);
/*  4112 */       am = am.invert();
/*  4113 */       weight = am.array_as_BigDecimal();
/*       */     }
/*       */     
/*  4116 */     BigDecimal sumx = BigDecimal.ZERO;
/*  4117 */     BigDecimal sumw = BigDecimal.ZERO;
/*  4118 */     for (int i = 0; i < n; i++) {
/*  4119 */       sumx = sumx.add(aa[i].multiply(weight[i]));
/*  4120 */       sumw = sumw.add(weight[i]);
/*       */     }
/*  4122 */     sumx = sumx.divide(sumw, 4);
/*  4123 */     sumw = null;
/*  4124 */     weight = null;
/*  4125 */     return sumx;
/*       */   }
/*       */   
/*       */   public static BigDecimal mean(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  4130 */     ArrayMaths amaa = new ArrayMaths(aa);
/*  4131 */     ArrayMaths amww = new ArrayMaths(ww);
/*       */     
/*  4133 */     return mean(amaa.array_as_BigDecimal(), amww.array_as_BigDecimal());
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] subtractMean(double[] array)
/*       */   {
/*  4139 */     int n = array.length;
/*  4140 */     double mean = mean(array);
/*  4141 */     double[] arrayMinusMean = new double[n];
/*  4142 */     for (int i = 0; i < n; i++) { array[i] -= mean;
/*       */     }
/*  4144 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static float[] subtractMean(float[] array)
/*       */   {
/*  4149 */     int n = array.length;
/*  4150 */     float mean = mean(array);
/*  4151 */     float[] arrayMinusMean = new float[n];
/*  4152 */     for (int i = 0; i < n; i++) { array[i] -= mean;
/*       */     }
/*  4154 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigDecimal[] subtractMean(BigDecimal[] array)
/*       */   {
/*  4160 */     int n = array.length;
/*  4161 */     BigDecimal mean = mean(array);
/*  4162 */     BigDecimal[] arrayMinusMean = new BigDecimal[n];
/*  4163 */     for (int i = 0; i < n; i++) arrayMinusMean[i] = array[i].subtract(mean);
/*  4164 */     mean = null;
/*  4165 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static BigDecimal[] subtractMean(BigInteger[] array)
/*       */   {
/*  4170 */     int n = array.length;
/*  4171 */     BigDecimal mean = mean(array);
/*  4172 */     BigDecimal[] arrayMinusMean = new BigDecimal[n];
/*  4173 */     for (int i = 0; i < n; i++) arrayMinusMean[i] = new BigDecimal(array[i]).subtract(mean);
/*  4174 */     mean = null;
/*  4175 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static Complex[] subtractMean(Complex[] array)
/*       */   {
/*  4180 */     int n = array.length;
/*  4181 */     Complex mean = mean(array);
/*  4182 */     Complex[] arrayMinusMean = new Complex[n];
/*  4183 */     for (int i = 0; i < n; i++) { arrayMinusMean[i] = array[i].minus(mean);
/*       */     }
/*  4185 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static double[] subtractMean(double[] array, double[] weights)
/*       */   {
/*  4190 */     int n = array.length;
/*  4191 */     double mean = mean(array, weights);
/*  4192 */     double[] arrayMinusMean = new double[n];
/*  4193 */     for (int i = 0; i < n; i++) { array[i] -= mean;
/*       */     }
/*  4195 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static float[] subtractMean(float[] array, float[] weights)
/*       */   {
/*  4200 */     int n = array.length;
/*  4201 */     float mean = mean(array, weights);
/*  4202 */     float[] arrayMinusMean = new float[n];
/*  4203 */     for (int i = 0; i < n; i++) { array[i] -= mean;
/*       */     }
/*  4205 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigDecimal[] subtractMean(BigDecimal[] array, BigDecimal[] weights)
/*       */   {
/*  4211 */     int n = array.length;
/*  4212 */     BigDecimal mean = mean(array, weights);
/*  4213 */     BigDecimal[] arrayMinusMean = new BigDecimal[n];
/*  4214 */     for (int i = 0; i < n; i++) arrayMinusMean[i] = array[i].subtract(mean);
/*  4215 */     mean = null;
/*  4216 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static BigDecimal[] subtractMean(BigInteger[] array, BigInteger[] weights)
/*       */   {
/*  4221 */     int n = array.length;
/*  4222 */     BigDecimal mean = mean(array, weights);
/*  4223 */     BigDecimal[] arrayMinusMean = new BigDecimal[n];
/*  4224 */     for (int i = 0; i < n; i++) arrayMinusMean[i] = new BigDecimal(array[i]).subtract(mean);
/*  4225 */     mean = null;
/*  4226 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */   public static Complex[] subtractMean(Complex[] array, Complex[] weights)
/*       */   {
/*  4231 */     int n = array.length;
/*  4232 */     Complex mean = mean(array, weights);
/*  4233 */     Complex[] arrayMinusMean = new Complex[n];
/*  4234 */     for (int i = 0; i < n; i++) { arrayMinusMean[i] = array[i].minus(mean);
/*       */     }
/*  4236 */     return arrayMinusMean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double geometricMean(BigDecimal[] aa)
/*       */   {
/*  4243 */     int n = aa.length;
/*  4244 */     double sum = 0.0D;
/*  4245 */     for (int i = 0; i < n; i++) sum += Math.log(aa[i].doubleValue());
/*  4246 */     return Math.exp(sum / n);
/*       */   }
/*       */   
/*       */   public static double geometricMean(BigInteger[] aa)
/*       */   {
/*  4251 */     int n = aa.length;
/*  4252 */     double sum = 0.0D;
/*  4253 */     for (int i = 0; i < n; i++) sum += Math.log(aa[i].doubleValue());
/*  4254 */     return Math.exp(sum / n);
/*       */   }
/*       */   
/*       */   public static Complex geometricMean(Complex[] aa)
/*       */   {
/*  4259 */     int n = aa.length;
/*  4260 */     Complex sum = Complex.zero();
/*  4261 */     for (int i = 0; i < n; i++) sum = sum.plus(Complex.log(aa[i]));
/*  4262 */     return Complex.exp(sum.over(n));
/*       */   }
/*       */   
/*       */   public static double geometricMean(double[] aa)
/*       */   {
/*  4267 */     int n = aa.length;
/*  4268 */     double sum = 0.0D;
/*  4269 */     for (int i = 0; i < n; i++) sum += Math.log(aa[i]);
/*  4270 */     return Math.exp(sum / n);
/*       */   }
/*       */   
/*       */   public static float geometricMean(float[] aa)
/*       */   {
/*  4275 */     int n = aa.length;
/*  4276 */     float sum = 0.0F;
/*  4277 */     for (int i = 0; i < n; i++) sum += (float)Math.log(aa[i]);
/*  4278 */     return (float)Math.exp(sum / n);
/*       */   }
/*       */   
/*       */   public static Complex geometricMean(Complex[] aa, Complex[] ww)
/*       */   {
/*  4283 */     int n = aa.length;
/*  4284 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4285 */     Complex sumW = Complex.zero();
/*  4286 */     Complex[] weight = invertAndSquare(ww);
/*  4287 */     for (int i = 0; i < n; i++) {
/*  4288 */       sumW = sumW.plus(weight[i]);
/*       */     }
/*  4290 */     Complex sum = Complex.zero();
/*  4291 */     for (int i = 0; i < n; i++) {
/*  4292 */       sum = sum.plus(Complex.log(aa[i]).times(weight[i]));
/*       */     }
/*  4294 */     return Complex.exp(sum.over(sumW));
/*       */   }
/*       */   
/*       */   public static double geometricMean(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  4299 */     int n = aa.length;
/*  4300 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4301 */     ArrayMaths weighting = new ArrayMaths(invertAndSquare(ww));
/*  4302 */     double[] weight = weighting.array();
/*       */     
/*  4304 */     double sumW = 0.0D;
/*  4305 */     for (int i = 0; i < n; i++) {
/*  4306 */       sumW += weight[i];
/*       */     }
/*  4308 */     double sum = 0.0D;
/*  4309 */     for (int i = 0; i < n; i++) {
/*  4310 */       sum += Math.log(aa[i].doubleValue()) * weight[i];
/*       */     }
/*  4312 */     return Math.exp(sum / sumW);
/*       */   }
/*       */   
/*       */   public static double geometricMean(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  4317 */     ArrayMaths amaa = new ArrayMaths(aa);
/*  4318 */     ArrayMaths amww = new ArrayMaths(ww);
/*  4319 */     return geometricMean(amaa.array_as_BigDecimal(), amww.array_as_BigDecimal());
/*       */   }
/*       */   
/*       */   public static double geometricMean(double[] aa, double[] ww)
/*       */   {
/*  4324 */     int n = aa.length;
/*  4325 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4326 */     double sumW = 0.0D;
/*  4327 */     double[] weight = invertAndSquare(ww);
/*  4328 */     for (int i = 0; i < n; i++) {
/*  4329 */       sumW += weight[i];
/*       */     }
/*  4331 */     double sum = 0.0D;
/*  4332 */     for (int i = 0; i < n; i++) {
/*  4333 */       sum += Math.log(aa[i]) * weight[i];
/*       */     }
/*  4335 */     return Math.exp(sum / sumW);
/*       */   }
/*       */   
/*       */   public static float geometricMean(float[] aa, float[] ww)
/*       */   {
/*  4340 */     int n = aa.length;
/*  4341 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4342 */     float sumW = 0.0F;
/*  4343 */     float[] weight = invertAndSquare(ww);
/*  4344 */     for (int i = 0; i < n; i++) {
/*  4345 */       sumW += weight[i];
/*       */     }
/*  4347 */     float sum = 0.0F;
/*  4348 */     for (int i = 0; i < n; i++) {
/*  4349 */       sum += (float)Math.log(aa[i]) * weight[i];
/*       */     }
/*  4351 */     return (float)Math.exp(sum / sumW);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static BigDecimal harmonicMean(BigDecimal[] aa)
/*       */   {
/*  4358 */     int n = aa.length;
/*  4359 */     BigDecimal sum = BigDecimal.ZERO;
/*  4360 */     for (int i = 0; i < n; i++) sum = sum.add(BigDecimal.ONE.divide(aa[i], 4));
/*  4361 */     sum = new BigDecimal(n).divide(sum, 4);
/*  4362 */     return sum;
/*       */   }
/*       */   
/*       */   public static BigDecimal harmonicMean(BigInteger[] aa)
/*       */   {
/*  4367 */     int n = aa.length;
/*  4368 */     ArrayMaths am = new ArrayMaths(aa);
/*  4369 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/*  4370 */     BigDecimal sum = BigDecimal.ZERO;
/*  4371 */     for (int i = 0; i < n; i++) sum = sum.add(BigDecimal.ONE.divide(bd[i], 4));
/*  4372 */     sum = new BigDecimal(n).divide(sum, 4);
/*  4373 */     bd = null;
/*  4374 */     return sum;
/*       */   }
/*       */   
/*       */   public static Complex harmonicMean(Complex[] aa)
/*       */   {
/*  4379 */     int n = aa.length;
/*  4380 */     Complex sum = Complex.zero();
/*  4381 */     for (int i = 0; i < n; i++) sum = sum.plus(Complex.plusOne().over(aa[i]));
/*  4382 */     sum = new Complex(n).over(sum);
/*  4383 */     return sum;
/*       */   }
/*       */   
/*       */   public static double harmonicMean(double[] aa)
/*       */   {
/*  4388 */     int n = aa.length;
/*  4389 */     double sum = 0.0D;
/*  4390 */     for (int i = 0; i < n; i++) sum += 1.0D / aa[i];
/*  4391 */     return n / sum;
/*       */   }
/*       */   
/*       */   public static float harmonicMean(float[] aa)
/*       */   {
/*  4396 */     int n = aa.length;
/*  4397 */     float sum = 0.0F;
/*  4398 */     for (int i = 0; i < n; i++) sum += 1.0F / aa[i];
/*  4399 */     return n / sum;
/*       */   }
/*       */   
/*       */   public static BigDecimal harmonicMean(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  4404 */     int n = aa.length;
/*  4405 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4406 */     BigDecimal sum = BigDecimal.ZERO;
/*  4407 */     BigDecimal sumW = BigDecimal.ZERO;
/*  4408 */     BigDecimal[] weight = invertAndSquare(ww);
/*  4409 */     for (int i = 0; i < n; i++) {
/*  4410 */       sumW = sumW.add(weight[i]);
/*       */     }
/*  4412 */     for (int i = 0; i < n; i++) sum = sum.add(weight[i].divide(aa[i], 4));
/*  4413 */     sum = sumW.divide(sum, 4);
/*  4414 */     sumW = null;
/*  4415 */     weight = null;
/*  4416 */     return sum;
/*       */   }
/*       */   
/*       */   public static BigDecimal harmonicMean(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  4421 */     int n = aa.length;
/*  4422 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4423 */     ArrayMaths am = new ArrayMaths(aa);
/*  4424 */     ArrayMaths wm = new ArrayMaths(ww);
/*  4425 */     return harmonicMean(am.getArray_as_BigDecimal(), wm.getArray_as_BigDecimal());
/*       */   }
/*       */   
/*       */   public static Complex harmonicMean(Complex[] aa, Complex[] ww)
/*       */   {
/*  4430 */     int n = aa.length;
/*  4431 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4432 */     Complex sum = Complex.zero();
/*  4433 */     Complex sumW = Complex.zero();
/*  4434 */     Complex[] weight = invertAndSquare(ww);
/*  4435 */     for (int i = 0; i < n; i++) {
/*  4436 */       sumW = sumW.plus(weight[i]);
/*       */     }
/*  4438 */     for (int i = 0; i < n; i++) sum = sum.plus(weight[i].over(aa[i]));
/*  4439 */     return sumW.over(sum);
/*       */   }
/*       */   
/*       */   public static double harmonicMean(double[] aa, double[] ww)
/*       */   {
/*  4444 */     int n = aa.length;
/*  4445 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4446 */     double sum = 0.0D;
/*  4447 */     double sumW = 0.0D;
/*  4448 */     double[] weight = invertAndSquare(ww);
/*  4449 */     for (int i = 0; i < n; i++) {
/*  4450 */       sumW += weight[i];
/*       */     }
/*  4452 */     for (int i = 0; i < n; i++) sum += weight[i] / aa[i];
/*  4453 */     return sumW / sum;
/*       */   }
/*       */   
/*       */   public static float harmonicMean(float[] aa, float[] ww)
/*       */   {
/*  4458 */     int n = aa.length;
/*  4459 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  4460 */     float sum = 0.0F;
/*  4461 */     float sumW = 0.0F;
/*  4462 */     float[] weight = invertAndSquare(ww);
/*  4463 */     for (int i = 0; i < n; i++) {
/*  4464 */       sumW += weight[i];
/*       */     }
/*  4466 */     for (int i = 0; i < n; i++) sum += weight[i] / aa[i];
/*  4467 */     return sumW / sum;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static Complex generalizedMean(Complex[] aa, double m)
/*       */   {
/*  4474 */     int n = aa.length;
/*  4475 */     Complex sum = Complex.zero();
/*  4476 */     if (m == 0.0D) {
/*  4477 */       for (int i = 0; i < n; i++) {
/*  4478 */         sum = sum.plus(Complex.log(aa[i]));
/*       */       }
/*  4480 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4483 */     for (int i = 0; i < n; i++) {
/*  4484 */       sum = sum.plus(Complex.pow(aa[i], m));
/*       */     }
/*  4486 */     return Complex.pow(sum.over(n), 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static Complex generalizedMean(Complex[] aa, Complex m)
/*       */   {
/*  4492 */     int n = aa.length;
/*  4493 */     Complex sum = Complex.zero();
/*  4494 */     if (m.equals(Complex.zero())) {
/*  4495 */       for (int i = 0; i < n; i++) {
/*  4496 */         sum = sum.plus(Complex.log(aa[i]));
/*       */       }
/*  4498 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4501 */     for (int i = 0; i < n; i++) {
/*  4502 */       sum = sum.plus(Complex.pow(aa[i], m));
/*       */     }
/*  4504 */     return Complex.pow(sum.over(n), Complex.plusOne().over(m));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double generalizedMean(BigDecimal[] aa, double m)
/*       */   {
/*  4510 */     ArrayMaths am = new ArrayMaths(aa);
/*  4511 */     double[] dd = am.getArray_as_double();
/*  4512 */     return generalizedMean(dd, m);
/*       */   }
/*       */   
/*       */   public static double generalizedMean(BigDecimal[] aa, BigDecimal m)
/*       */   {
/*  4517 */     ArrayMaths am = new ArrayMaths(aa);
/*  4518 */     double[] dd = am.getArray_as_double();
/*  4519 */     return generalizedMean(dd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalizedMean(BigInteger[] aa, double m)
/*       */   {
/*  4524 */     ArrayMaths am = new ArrayMaths(aa);
/*  4525 */     double[] dd = am.getArray_as_double();
/*  4526 */     return generalizedMean(dd, m);
/*       */   }
/*       */   
/*       */   public static double generalizedMean(BigInteger[] aa, BigInteger m)
/*       */   {
/*  4531 */     ArrayMaths am = new ArrayMaths(aa);
/*  4532 */     double[] dd = am.getArray_as_double();
/*  4533 */     return generalizedMean(dd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalizedMean(double[] aa, double m)
/*       */   {
/*  4538 */     int n = aa.length;
/*  4539 */     double sum = 0.0D;
/*  4540 */     if (m == 0.0D) {
/*  4541 */       for (int i = 0; i < n; i++) {
/*  4542 */         sum += Math.log(aa[i]);
/*       */       }
/*  4544 */       return Math.exp(sum);
/*       */     }
/*       */     
/*  4547 */     for (int i = 0; i < n; i++) {
/*  4548 */       sum += Math.pow(aa[i], m);
/*       */     }
/*  4550 */     return Math.pow(sum / n, 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static float generalizedMean(float[] aa, float m)
/*       */   {
/*  4556 */     int n = aa.length;
/*  4557 */     float sum = 0.0F;
/*  4558 */     if (m == 0.0F) {
/*  4559 */       for (int i = 0; i < n; i++) {
/*  4560 */         sum += (float)Math.log(aa[i]);
/*       */       }
/*  4562 */       return (float)Math.exp(sum);
/*       */     }
/*       */     
/*  4565 */     for (int i = 0; i < n; i++) {
/*  4566 */       sum = (float)(sum + Math.pow(aa[i], m));
/*       */     }
/*  4568 */     return (float)Math.pow(sum / n, 1.0F / m);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static Complex generalisedMean(Complex[] aa, double m)
/*       */   {
/*  4575 */     int n = aa.length;
/*  4576 */     Complex sum = Complex.zero();
/*  4577 */     if (m == 0.0D) {
/*  4578 */       for (int i = 0; i < n; i++) {
/*  4579 */         sum = sum.plus(Complex.log(aa[i]));
/*       */       }
/*  4581 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4584 */     for (int i = 0; i < n; i++) {
/*  4585 */       sum = sum.plus(Complex.pow(aa[i], m));
/*       */     }
/*  4587 */     return Complex.pow(sum.over(n), 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static Complex generalisedMean(Complex[] aa, Complex m)
/*       */   {
/*  4593 */     int n = aa.length;
/*  4594 */     Complex sum = Complex.zero();
/*  4595 */     if (m.equals(Complex.zero())) {
/*  4596 */       for (int i = 0; i < n; i++) {
/*  4597 */         sum = sum.plus(Complex.log(aa[i]));
/*       */       }
/*  4599 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4602 */     for (int i = 0; i < n; i++) {
/*  4603 */       sum = sum.plus(Complex.pow(aa[i], m));
/*       */     }
/*  4605 */     return Complex.pow(sum.over(n), Complex.plusOne().over(m));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double generalisedMean(BigDecimal[] aa, double m)
/*       */   {
/*  4611 */     ArrayMaths am = new ArrayMaths(aa);
/*  4612 */     double[] dd = am.getArray_as_double();
/*  4613 */     return generalisedMean(dd, m);
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigDecimal[] aa, BigDecimal m)
/*       */   {
/*  4618 */     ArrayMaths am = new ArrayMaths(aa);
/*  4619 */     double[] dd = am.getArray_as_double();
/*  4620 */     return generalisedMean(dd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigInteger[] aa, double m)
/*       */   {
/*  4625 */     ArrayMaths am = new ArrayMaths(aa);
/*  4626 */     double[] dd = am.getArray_as_double();
/*  4627 */     return generalisedMean(dd, m);
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigInteger[] aa, BigInteger m)
/*       */   {
/*  4632 */     ArrayMaths am = new ArrayMaths(aa);
/*  4633 */     double[] dd = am.getArray_as_double();
/*  4634 */     return generalisedMean(dd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalisedMean(double[] aa, double m)
/*       */   {
/*  4639 */     int n = aa.length;
/*  4640 */     double sum = 0.0D;
/*  4641 */     if (m == 0.0D) {
/*  4642 */       for (int i = 0; i < n; i++) {
/*  4643 */         sum += Math.log(aa[i]);
/*       */       }
/*  4645 */       return Math.exp(sum);
/*       */     }
/*       */     
/*  4648 */     for (int i = 0; i < n; i++) {
/*  4649 */       sum += Math.pow(aa[i], m);
/*       */     }
/*  4651 */     return Math.pow(sum / n, 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static float generalisedMean(float[] aa, float m)
/*       */   {
/*  4657 */     int n = aa.length;
/*  4658 */     float sum = 0.0F;
/*  4659 */     if (m == 0.0F) {
/*  4660 */       for (int i = 0; i < n; i++) {
/*  4661 */         sum += (float)Math.log(aa[i]);
/*       */       }
/*  4663 */       return (float)Math.exp(sum);
/*       */     }
/*       */     
/*  4666 */     for (int i = 0; i < n; i++) {
/*  4667 */       sum = (float)(sum + Math.pow(aa[i], m));
/*       */     }
/*  4669 */     return (float)Math.pow(sum / n, 1.0F / m);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static Complex generalisedMean(Complex[] aa, Complex[] ww, double m)
/*       */   {
/*  4677 */     int n = aa.length;
/*  4678 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4680 */     Complex sum = Complex.zero();
/*  4681 */     Complex sumw = Complex.zero();
/*  4682 */     Complex[] weight = invertAndSquare(ww);
/*  4683 */     for (int i = 0; i < n; i++) {
/*  4684 */       sumw = sumw.plus(weight[i]);
/*       */     }
/*       */     
/*  4687 */     if (m == 0.0D) {
/*  4688 */       for (int i = 0; i < n; i++) {
/*  4689 */         sum = sum.plus(Complex.log(weight[i].times(aa[i])).over(sumw));
/*       */       }
/*  4691 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4694 */     for (int i = 0; i < n; i++) {
/*  4695 */       sum = sum.plus(weight[i].times(Complex.pow(aa[i], m)));
/*       */     }
/*  4697 */     return Complex.pow(sum.over(sumw), 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static Complex generalisedMean(Complex[] aa, Complex[] ww, Complex m)
/*       */   {
/*  4703 */     int n = aa.length;
/*  4704 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4706 */     Complex sum = Complex.zero();
/*  4707 */     Complex sumw = Complex.zero();
/*  4708 */     Complex[] weight = invertAndSquare(ww);
/*  4709 */     for (int i = 0; i < n; i++) {
/*  4710 */       sumw = sumw.plus(weight[i]);
/*       */     }
/*       */     
/*  4713 */     if (m.equals(Complex.zero())) {
/*  4714 */       for (int i = 0; i < n; i++) {
/*  4715 */         sum = sum.plus(Complex.log(weight[i].times(aa[i])).over(sumw));
/*       */       }
/*  4717 */       return Complex.exp(sum);
/*       */     }
/*       */     
/*  4720 */     for (int i = 0; i < n; i++) {
/*  4721 */       sum = sum.plus(weight[i].times(Complex.pow(aa[i], m)));
/*       */     }
/*  4723 */     return Complex.pow(sum.over(sumw), Complex.plusOne().over(m));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double generalisedMean(BigDecimal[] aa, BigDecimal[] ww, double m)
/*       */   {
/*  4729 */     int n = aa.length;
/*  4730 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4732 */     ArrayMaths am1 = new ArrayMaths(aa);
/*  4733 */     double[] dd = am1.getArray_as_double();
/*  4734 */     ArrayMaths am2 = new ArrayMaths(ww);
/*  4735 */     double[] wd = am2.getArray_as_double();
/*  4736 */     return generalisedMean(dd, wd, m);
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigDecimal[] aa, BigDecimal[] ww, BigDecimal m)
/*       */   {
/*  4741 */     int n = aa.length;
/*  4742 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4744 */     ArrayMaths am1 = new ArrayMaths(aa);
/*  4745 */     double[] dd = am1.getArray_as_double();
/*  4746 */     ArrayMaths am2 = new ArrayMaths(ww);
/*  4747 */     double[] wd = am2.getArray_as_double();
/*  4748 */     return generalisedMean(dd, wd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigInteger[] aa, BigInteger[] ww, double m)
/*       */   {
/*  4753 */     int n = aa.length;
/*  4754 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4756 */     ArrayMaths am1 = new ArrayMaths(aa);
/*  4757 */     double[] dd = am1.getArray_as_double();
/*  4758 */     ArrayMaths am2 = new ArrayMaths(ww);
/*  4759 */     double[] wd = am2.getArray_as_double();
/*  4760 */     return generalisedMean(dd, wd, m);
/*       */   }
/*       */   
/*       */   public static double generalisedMean(BigInteger[] aa, BigInteger[] ww, BigInteger m)
/*       */   {
/*  4765 */     int n = aa.length;
/*  4766 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4768 */     ArrayMaths am1 = new ArrayMaths(aa);
/*  4769 */     double[] dd = am1.getArray_as_double();
/*  4770 */     ArrayMaths am2 = new ArrayMaths(ww);
/*  4771 */     double[] wd = am2.getArray_as_double();
/*  4772 */     return generalisedMean(dd, wd, m.doubleValue());
/*       */   }
/*       */   
/*       */   public static double generalisedMean(double[] aa, double[] ww, double m)
/*       */   {
/*  4777 */     int n = aa.length;
/*  4778 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4780 */     double sum = 0.0D;
/*  4781 */     double sumw = 0.0D;
/*  4782 */     double[] weight = invertAndSquare(ww);
/*  4783 */     for (int i = 0; i < n; i++) {
/*  4784 */       sumw += weight[i];
/*       */     }
/*       */     
/*  4787 */     if (m == 0.0D) {
/*  4788 */       for (int i = 0; i < n; i++) {
/*  4789 */         sum += Math.log(aa[i] * weight[i] / sumw);
/*       */       }
/*  4791 */       return Math.exp(sum);
/*       */     }
/*       */     
/*  4794 */     for (int i = 0; i < n; i++) {
/*  4795 */       sum += weight[i] * Math.pow(aa[i], m);
/*       */     }
/*  4797 */     return Math.pow(sum / sumw, 1.0D / m);
/*       */   }
/*       */   
/*       */ 
/*       */   public static float generalisedMean(float[] aa, float[] ww, float m)
/*       */   {
/*  4803 */     int n = aa.length;
/*  4804 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4806 */     float sum = 0.0F;
/*  4807 */     float sumw = 0.0F;
/*  4808 */     float[] weight = invertAndSquare(ww);
/*  4809 */     for (int i = 0; i < n; i++) {
/*  4810 */       sumw += weight[i];
/*       */     }
/*  4812 */     if (m == 0.0F) {
/*  4813 */       for (int i = 0; i < n; i++) {
/*  4814 */         sum += (float)Math.log(aa[i]);
/*       */       }
/*  4816 */       return (float)Math.exp(sum);
/*       */     }
/*       */     
/*  4819 */     for (int i = 0; i < n; i++) {
/*  4820 */       sum = (float)(sum + Math.pow(aa[i], m));
/*       */     }
/*  4822 */     return (float)Math.pow(sum / sumw, 1.0F / m);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static Complex weightedGeneralisedMean(Complex[] aa, Complex[] ww, double m)
/*       */   {
/*  4829 */     int n = aa.length;
/*  4830 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4832 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static Complex weightedGeneralisedMean(Complex[] aa, Complex[] ww, Complex m)
/*       */   {
/*  4837 */     int n = aa.length;
/*  4838 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4840 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static double weightedGeneralisedMean(BigDecimal[] aa, BigDecimal[] ww, double m)
/*       */   {
/*  4845 */     int n = aa.length;
/*  4846 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4848 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static double weightedGeneralisedMean(BigDecimal[] aa, BigDecimal[] ww, BigDecimal m)
/*       */   {
/*  4853 */     int n = aa.length;
/*  4854 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4856 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static double weightedGeneralisedMean(BigInteger[] aa, BigInteger[] ww, double m)
/*       */   {
/*  4861 */     int n = aa.length;
/*  4862 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4864 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static double weightedGeneralisedMean(BigInteger[] aa, BigInteger[] ww, BigInteger m)
/*       */   {
/*  4869 */     int n = aa.length;
/*  4870 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4872 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static double weightedGeneralisedMean(double[] aa, double[] ww, double m)
/*       */   {
/*  4877 */     int n = aa.length;
/*  4878 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4880 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */   public static float weightedGeneralisedMean(float[] aa, float[] ww, float m)
/*       */   {
/*  4885 */     int n = aa.length;
/*  4886 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  4888 */     return generalisedMean(aa, ww, m);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static BigDecimal interQuartileMean(BigDecimal[] aa)
/*       */   {
/*  4898 */     int n = aa.length;
/*  4899 */     if (n < 4) throw new IllegalArgumentException("At least 4 array elements needed");
/*  4900 */     ArrayMaths am = new ArrayMaths(aa);
/*  4901 */     ArrayMaths as = am.sort();
/*  4902 */     BigDecimal[] bb = as.getArray_as_BigDecimal();
/*  4903 */     BigDecimal sum = BigDecimal.ZERO;
/*  4904 */     for (int i = n / 4; i < 3 * n / 4; i++) sum = sum.add(bb[i]);
/*  4905 */     sum = sum.multiply(new BigDecimal(2.0D / n));
/*  4906 */     bb = null;
/*  4907 */     return sum;
/*       */   }
/*       */   
/*       */   public static BigDecimal interQuartileMean(BigInteger[] aa)
/*       */   {
/*  4912 */     int n = aa.length;
/*  4913 */     if (n < 4) throw new IllegalArgumentException("At least 4 array elements needed");
/*  4914 */     ArrayMaths am = new ArrayMaths(aa);
/*  4915 */     ArrayMaths as = am.sort();
/*  4916 */     BigDecimal[] bb = as.getArray_as_BigDecimal();
/*  4917 */     BigDecimal sum = BigDecimal.ZERO;
/*  4918 */     for (int i = n / 4; i < 3 * n / 4; i++) sum = sum.add(bb[i]);
/*  4919 */     sum = sum.multiply(new BigDecimal(2.0D / n));
/*  4920 */     bb = null;
/*  4921 */     return sum;
/*       */   }
/*       */   
/*       */   public static double interQuartileMean(double[] aa)
/*       */   {
/*  4926 */     int n = aa.length;
/*  4927 */     if (n < 4) throw new IllegalArgumentException("At least 4 array elements needed");
/*  4928 */     double[] bb = Fmath.selectionSort(aa);
/*  4929 */     double sum = 0.0D;
/*  4930 */     for (int i = n / 4; i < 3 * n / 4; i++) sum += bb[i];
/*  4931 */     return 2.0D * sum / n;
/*       */   }
/*       */   
/*       */   public static float interQuartileMean(float[] aa)
/*       */   {
/*  4936 */     int n = aa.length;
/*  4937 */     if (n < 4) throw new IllegalArgumentException("At least 4 array elements needed");
/*  4938 */     float[] bb = Fmath.selectionSort(aa);
/*  4939 */     float sum = 0.0F;
/*  4940 */     for (int i = n / 4; i < 3 * n / 4; i++) sum += bb[i];
/*  4941 */     return 2.0F * sum / n;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double rms(double[] aa)
/*       */   {
/*  4948 */     int n = aa.length;
/*  4949 */     double sum = 0.0D;
/*  4950 */     for (int i = 0; i < n; i++) {
/*  4951 */       sum += aa[i] * aa[i];
/*       */     }
/*  4953 */     return Math.sqrt(sum / n);
/*       */   }
/*       */   
/*       */   public static float rms(float[] aa)
/*       */   {
/*  4958 */     int n = aa.length;
/*  4959 */     float sum = 0.0F;
/*  4960 */     for (int i = 0; i < n; i++) {
/*  4961 */       sum += aa[i] * aa[i];
/*       */     }
/*  4963 */     sum /= n;
/*       */     
/*  4965 */     return (float)Math.sqrt(sum);
/*       */   }
/*       */   
/*       */   public static double rms(BigDecimal[] aa)
/*       */   {
/*  4970 */     int n = aa.length;
/*  4971 */     BigDecimal sum = BigDecimal.ZERO;
/*  4972 */     for (int i = 0; i < n; i++) {
/*  4973 */       sum = sum.add(aa[i].multiply(aa[i]));
/*       */     }
/*  4975 */     sum = sum.divide(new BigDecimal(n), 4);
/*  4976 */     double ret = Math.sqrt(sum.doubleValue());
/*  4977 */     sum = null;
/*  4978 */     return ret;
/*       */   }
/*       */   
/*       */   public static double rms(BigInteger[] aa)
/*       */   {
/*  4983 */     int n = aa.length;
/*  4984 */     BigDecimal sum = BigDecimal.ZERO;
/*  4985 */     BigDecimal bd = BigDecimal.ZERO;
/*  4986 */     for (int i = 0; i < n; i++) {
/*  4987 */       bd = new BigDecimal(aa[i]);
/*  4988 */       sum = sum.add(bd.multiply(bd));
/*       */     }
/*  4990 */     sum = sum.divide(new BigDecimal(n), 4);
/*  4991 */     double ret = Math.sqrt(sum.doubleValue());
/*  4992 */     bd = null;
/*  4993 */     sum = null;
/*  4994 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double rms(double[] aa, double[] ww)
/*       */   {
/*  5001 */     int n = aa.length;
/*  5002 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  5004 */     double sumw = 0.0D;
/*  5005 */     double[] weight = invertAndSquare(ww);
/*  5006 */     for (int i = 0; i < n; i++) {
/*  5007 */       sumw += weight[i];
/*       */     }
/*  5009 */     double sum = 0.0D;
/*  5010 */     for (int i = 0; i < n; i++) {
/*  5011 */       sum += weight[i] * aa[i] * aa[i];
/*       */     }
/*  5013 */     return Math.sqrt(sum / sumw);
/*       */   }
/*       */   
/*       */   public static float rms(float[] aa, float[] ww)
/*       */   {
/*  5018 */     int n = aa.length;
/*  5019 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  5021 */     double sumw = 0.0D;
/*  5022 */     float[] weight = invertAndSquare(ww);
/*  5023 */     for (int i = 0; i < n; i++) {
/*  5024 */       sumw += weight[i];
/*       */     }
/*  5026 */     float sum = 0.0F;
/*  5027 */     for (int i = 0; i < n; i++) {
/*  5028 */       sum += weight[i] * aa[i] * aa[i];
/*       */     }
/*  5030 */     return (float)Math.sqrt(sum / sumw);
/*       */   }
/*       */   
/*       */   public static double rms(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  5035 */     int n = aa.length;
/*  5036 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*  5038 */     BigDecimal sumw = BigDecimal.ZERO;
/*  5039 */     BigDecimal[] weight = invertAndSquare(ww);
/*  5040 */     for (int i = 0; i < n; i++) {
/*  5041 */       sumw = sumw.add(weight[i]);
/*       */     }
/*       */     
/*  5044 */     BigDecimal sum = BigDecimal.ZERO;
/*  5045 */     for (int i = 0; i < n; i++) {
/*  5046 */       sum = sum.add(aa[i].multiply(aa[i]).multiply(weight[i]));
/*       */     }
/*  5048 */     sum = sum.divide(sumw, 4);
/*  5049 */     double ret = Math.sqrt(sum.doubleValue());
/*  5050 */     sum = null;
/*  5051 */     weight = null;
/*  5052 */     return ret;
/*       */   }
/*       */   
/*       */   public static double rms(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  5057 */     int n = aa.length;
/*  5058 */     if (n != ww.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*       */     }
/*       */     
/*  5061 */     ArrayMaths amaa = new ArrayMaths(aa);
/*  5062 */     ArrayMaths amww = new ArrayMaths(ww);
/*  5063 */     return rms(amaa.array_as_BigDecimal(), amww.array_as_BigDecimal());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static BigDecimal median(BigDecimal[] aa)
/*       */   {
/*  5070 */     int n = aa.length;
/*  5071 */     int nOverTwo = n / 2;
/*  5072 */     BigDecimal med = BigDecimal.ZERO;
/*  5073 */     ArrayMaths bm = new ArrayMaths(aa);
/*  5074 */     ArrayMaths sm = bm.sort();
/*  5075 */     BigDecimal[] bb = bm.getArray_as_BigDecimal();
/*  5076 */     if (Fmath.isOdd(n)) {
/*  5077 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5080 */       med = bb[(nOverTwo - 1)].add(bb[nOverTwo]).divide(new BigDecimal(2.0D), 4);
/*       */     }
/*  5082 */     bb = null;
/*  5083 */     return med;
/*       */   }
/*       */   
/*       */   public static BigInteger median(BigInteger[] aa)
/*       */   {
/*  5088 */     int n = aa.length;
/*  5089 */     int nOverTwo = n / 2;
/*  5090 */     BigInteger med = BigInteger.ZERO;
/*  5091 */     ArrayMaths bm = new ArrayMaths(aa);
/*  5092 */     ArrayMaths sm = bm.sort();
/*  5093 */     BigInteger[] bb = bm.getArray_as_BigInteger();
/*  5094 */     if (Fmath.isOdd(n)) {
/*  5095 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5098 */       med = bb[(nOverTwo - 1)].add(bb[nOverTwo]).divide(new BigInteger("2"));
/*       */     }
/*  5100 */     bb = null;
/*  5101 */     return med;
/*       */   }
/*       */   
/*       */   public static double median(double[] aa)
/*       */   {
/*  5106 */     int n = aa.length;
/*  5107 */     int nOverTwo = n / 2;
/*  5108 */     double med = 0.0D;
/*  5109 */     double[] bb = Fmath.selectionSort(aa);
/*  5110 */     if (Fmath.isOdd(n)) {
/*  5111 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5114 */       med = (bb[(nOverTwo - 1)] + bb[nOverTwo]) / 2.0D;
/*       */     }
/*       */     
/*  5117 */     return med;
/*       */   }
/*       */   
/*       */ 
/*       */   public static float median(float[] aa)
/*       */   {
/*  5123 */     int n = aa.length;
/*  5124 */     int nOverTwo = n / 2;
/*  5125 */     float med = 0.0F;
/*  5126 */     float[] bb = Fmath.selectionSort(aa);
/*  5127 */     if (Fmath.isOdd(n)) {
/*  5128 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5131 */       med = (bb[(nOverTwo - 1)] + bb[nOverTwo]) / 2.0F;
/*       */     }
/*       */     
/*  5134 */     return med;
/*       */   }
/*       */   
/*       */   public static double median(int[] aa)
/*       */   {
/*  5139 */     int n = aa.length;
/*  5140 */     int nOverTwo = n / 2;
/*  5141 */     double med = 0.0D;
/*  5142 */     int[] bb = Fmath.selectionSort(aa);
/*  5143 */     if (Fmath.isOdd(n)) {
/*  5144 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5147 */       med = (bb[(nOverTwo - 1)] + bb[nOverTwo]) / 2.0D;
/*       */     }
/*       */     
/*  5150 */     return med;
/*       */   }
/*       */   
/*       */   public static double median(long[] aa)
/*       */   {
/*  5155 */     int n = aa.length;
/*  5156 */     int nOverTwo = n / 2;
/*  5157 */     double med = 0.0D;
/*  5158 */     long[] bb = Fmath.selectionSort(aa);
/*  5159 */     if (Fmath.isOdd(n)) {
/*  5160 */       med = bb[nOverTwo];
/*       */     }
/*       */     else {
/*  5163 */       med = (bb[(nOverTwo - 1)] + bb[nOverTwo]) / 2.0D;
/*       */     }
/*       */     
/*  5166 */     return med;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double standardDeviation(BigDecimal[] aa)
/*       */   {
/*  5174 */     return Math.sqrt(variance(aa).doubleValue());
/*       */   }
/*       */   
/*       */   public static double standardDeviation(BigInteger[] aa)
/*       */   {
/*  5179 */     return Math.sqrt(variance(aa).doubleValue());
/*       */   }
/*       */   
/*       */   public static Complex standardDeviation(Complex[] aa)
/*       */   {
/*  5184 */     return Complex.sqrt(variance(aa));
/*       */   }
/*       */   
/*       */   public static double standardDeviationConjugateCalcn(Complex[] aa)
/*       */   {
/*  5189 */     return Math.sqrt(varianceConjugateCalcn(aa));
/*       */   }
/*       */   
/*       */   public static double standardDeviationModuli(Complex[] aa)
/*       */   {
/*  5194 */     ArrayMaths am = new ArrayMaths(aa);
/*  5195 */     double[] rl = am.array_as_modulus_of_Complex();
/*  5196 */     double standardDeviation = standardDeviation(rl);
/*  5197 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public static double standardDeviationRealParts(Complex[] aa)
/*       */   {
/*  5202 */     ArrayMaths am = new ArrayMaths(aa);
/*  5203 */     double[] rl = am.array_as_real_part_of_Complex();
/*  5204 */     double standardDeviation = standardDeviation(rl);
/*  5205 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public static double standardDeviationImaginaryParts(Complex[] aa)
/*       */   {
/*  5210 */     ArrayMaths am = new ArrayMaths(aa);
/*  5211 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  5212 */     double standardDeviation = standardDeviation(im);
/*  5213 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public static double standardDeviation(double[] aa)
/*       */   {
/*  5218 */     return Math.sqrt(variance(aa));
/*       */   }
/*       */   
/*       */   public static float standardDeviation(float[] aa)
/*       */   {
/*  5223 */     return (float)Math.sqrt(variance(aa));
/*       */   }
/*       */   
/*       */   public static double standardDeviation(int[] aa)
/*       */   {
/*  5228 */     return Math.sqrt(variance(aa));
/*       */   }
/*       */   
/*       */   public static double standardDeviation(long[] aa)
/*       */   {
/*  5233 */     return Math.sqrt(variance(aa));
/*       */   }
/*       */   
/*       */   public static Complex standardDeviation(Complex[] aa, Complex[] ww)
/*       */   {
/*  5238 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5239 */     return Complex.sqrt(variance(aa, ww));
/*       */   }
/*       */   
/*       */   public static double standardDeviationConjugateCalcn(Complex[] aa, Complex[] ww)
/*       */   {
/*  5244 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5245 */     return Math.sqrt(varianceConjugateCalcn(aa, ww));
/*       */   }
/*       */   
/*       */   public static double standardDeviationModuli(Complex[] aa, Complex[] ww)
/*       */   {
/*  5250 */     int n = aa.length;
/*  5251 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  5252 */     ArrayMaths am = new ArrayMaths(aa);
/*  5253 */     double[] rl = am.array_as_modulus_of_Complex();
/*  5254 */     ArrayMaths wm = new ArrayMaths(ww);
/*  5255 */     double[] wt = wm.array_as_modulus_of_Complex();
/*  5256 */     double standardDeviation = standardDeviation(rl, wt);
/*  5257 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public static double standardDeviationRealParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  5262 */     int n = aa.length;
/*  5263 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  5264 */     ArrayMaths am = new ArrayMaths(aa);
/*  5265 */     double[] rl = am.array_as_real_part_of_Complex();
/*  5266 */     ArrayMaths wm = new ArrayMaths(ww);
/*  5267 */     double[] wt = wm.array_as_real_part_of_Complex();
/*  5268 */     double standardDeviation = standardDeviation(rl, wt);
/*  5269 */     return standardDeviation;
/*       */   }
/*       */   
/*       */   public static double standardDeviationImaginaryParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  5274 */     int n = aa.length;
/*  5275 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  5276 */     ArrayMaths am = new ArrayMaths(aa);
/*  5277 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  5278 */     ArrayMaths wm = new ArrayMaths(ww);
/*  5279 */     double[] wt = wm.array_as_imaginary_part_of_Complex();
/*  5280 */     double standardDeviation = standardDeviation(im, wt);
/*  5281 */     return standardDeviation;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double standardDeviation(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  5287 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5288 */     return Math.sqrt(variance(aa, ww).doubleValue());
/*       */   }
/*       */   
/*       */   public static double standardDeviation(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  5293 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5294 */     return Math.sqrt(variance(aa, ww).doubleValue());
/*       */   }
/*       */   
/*       */   public static double standardDeviation(double[] aa, double[] ww)
/*       */   {
/*  5299 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5300 */     return Math.sqrt(variance(aa, ww));
/*       */   }
/*       */   
/*       */   public static float standardDeviation(float[] aa, float[] ww)
/*       */   {
/*  5305 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  5306 */     return (float)Math.sqrt(variance(aa, ww));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double volatilityLogChange(BigDecimal[] array)
/*       */   {
/*  5314 */     int n = array.length - 1;
/*  5315 */     double[] change = new double[n];
/*  5316 */     for (int i = 0; i < n; i++) change[i] = Math.log(array[(i + 1)].divide(array[i], 4).doubleValue());
/*  5317 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityLogChange(BigInteger[] array)
/*       */   {
/*  5322 */     int n = array.length - 1;
/*  5323 */     double[] change = new double[n];
/*  5324 */     for (int i = 0; i < n; i++) change[i] = Math.log(new BigDecimal(array[(i + 1)]).divide(new BigDecimal(array[i]), 4).doubleValue());
/*  5325 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityLogChange(double[] array)
/*       */   {
/*  5330 */     int n = array.length - 1;
/*  5331 */     double[] change = new double[n];
/*  5332 */     for (int i = 0; i < n; i++) change[i] = Math.log(array[(i + 1)] / array[i]);
/*  5333 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static float volatilityLogChange(float[] array)
/*       */   {
/*  5338 */     int n = array.length - 1;
/*  5339 */     float[] change = new float[n];
/*  5340 */     for (int i = 0; i < n; i++) change[i] = ((float)Math.log(array[(i + 1)] / array[i]));
/*  5341 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityPerCentChange(BigDecimal[] array)
/*       */   {
/*  5346 */     int n = array.length - 1;
/*  5347 */     double[] change = new double[n];
/*  5348 */     for (int i = 0; i < n; i++) change[i] = array[(i + 1)].add(array[i]).multiply(new BigDecimal(100.0D).divide(array[i], 4)).doubleValue();
/*  5349 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityPerCentChange(BigInteger[] array)
/*       */   {
/*  5354 */     int n = array.length - 1;
/*  5355 */     double[] change = new double[n];
/*  5356 */     ArrayMaths am = new ArrayMaths(array);
/*  5357 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/*  5358 */     for (int i = 0; i < n; i++) change[i] = bd[(i + 1)].add(bd[i]).multiply(new BigDecimal(100.0D).divide(bd[i], 4)).doubleValue();
/*  5359 */     bd = null;
/*  5360 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityPerCentChange(double[] array)
/*       */   {
/*  5365 */     int n = array.length - 1;
/*  5366 */     double[] change = new double[n];
/*  5367 */     for (int i = 0; i < n; i++) change[i] = ((array[(i + 1)] - array[i]) * 100.0D / array[i]);
/*  5368 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */   public static double volatilityPerCentChange(float[] array)
/*       */   {
/*  5373 */     int n = array.length - 1;
/*  5374 */     float[] change = new float[n];
/*  5375 */     for (int i = 0; i < n; i++) change[i] = ((array[(i + 1)] - array[i]) * 100.0F / array[i]);
/*  5376 */     return standardDeviation(change);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double coefficientOfVariation(BigInteger[] array)
/*       */   {
/*  5384 */     return 100.0D * standardDeviation(array) / Math.abs(mean(array).doubleValue());
/*       */   }
/*       */   
/*       */   public static double coefficientOfVariation(BigDecimal[] array)
/*       */   {
/*  5389 */     return 100.0D * standardDeviation(array) / Math.abs(mean(array).doubleValue());
/*       */   }
/*       */   
/*       */   public static double coefficientOfVariation(double[] array)
/*       */   {
/*  5394 */     return 100.0D * standardDeviation(array) / Math.abs(mean(array));
/*       */   }
/*       */   
/*       */   public static float coefficientOfVariation(float[] array)
/*       */   {
/*  5399 */     return 100.0F * standardDeviation(array) / Math.abs(mean(array));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double coefficientOfVariation(BigInteger[] array, BigInteger[] weight)
/*       */   {
/*  5407 */     int n = array.length;
/*  5408 */     if (n != weight.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + weight.length + " are different");
/*       */     }
/*  5410 */     return 100.0D * standardDeviation(array, weight) / Math.abs(mean(array, weight).doubleValue());
/*       */   }
/*       */   
/*       */   public static double coefficientOfVariation(BigDecimal[] array, BigDecimal[] weight)
/*       */   {
/*  5415 */     int n = array.length;
/*  5416 */     if (n != weight.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + weight.length + " are different");
/*       */     }
/*  5418 */     return 100.0D * standardDeviation(array, weight) / Math.abs(mean(array, weight).doubleValue());
/*       */   }
/*       */   
/*       */   public static double coefficientOfVariation(double[] array, double[] weight)
/*       */   {
/*  5423 */     int n = array.length;
/*  5424 */     if (n != weight.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + weight.length + " are different");
/*       */     }
/*  5426 */     return 100.0D * standardDeviation(array, weight) / Math.abs(mean(array, weight));
/*       */   }
/*       */   
/*       */   public static float coefficientOfVariation(float[] array, float[] weight)
/*       */   {
/*  5431 */     int n = array.length;
/*  5432 */     if (n != weight.length) { throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + weight.length + " are different");
/*       */     }
/*  5434 */     return 100.0F * standardDeviation(array, weight) / Math.abs(mean(array, weight));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double[] standardize(double[] aa)
/*       */   {
/*  5441 */     double mean0 = mean(aa);
/*  5442 */     double sd0 = standardDeviation(aa);
/*  5443 */     int n = aa.length;
/*  5444 */     double[] bb = new double[n];
/*  5445 */     for (int i = 0; i < n; i++) {
/*  5446 */       bb[i] = ((aa[i] - mean0) / sd0);
/*       */     }
/*       */     
/*  5449 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] standardise(double[] aa) {
/*  5453 */     return standardize(aa);
/*       */   }
/*       */   
/*       */   public static float[] standardize(float[] aa)
/*       */   {
/*  5458 */     float mean0 = mean(aa);
/*  5459 */     float sd0 = standardDeviation(aa);
/*  5460 */     int n = aa.length;
/*  5461 */     float[] bb = new float[n];
/*  5462 */     for (int i = 0; i < n; i++) {
/*  5463 */       bb[i] = ((aa[i] - mean0) / sd0);
/*       */     }
/*       */     
/*  5466 */     return bb;
/*       */   }
/*       */   
/*       */   public static float[] standardise(float[] aa) {
/*  5470 */     return standardize(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] standardize(long[] aa)
/*       */   {
/*  5476 */     double mean0 = mean(aa);
/*  5477 */     double sd0 = standardDeviation(aa);
/*  5478 */     int n = aa.length;
/*  5479 */     double[] bb = new double[n];
/*  5480 */     for (int i = 0; i < n; i++) {
/*  5481 */       bb[i] = ((aa[i] - mean0) / sd0);
/*       */     }
/*       */     
/*  5484 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] standardise(long[] aa) {
/*  5488 */     return standardize(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] standardize(int[] aa)
/*       */   {
/*  5494 */     double mean0 = mean(aa);
/*  5495 */     double sd0 = standardDeviation(aa);
/*  5496 */     int n = aa.length;
/*  5497 */     double[] bb = new double[n];
/*  5498 */     for (int i = 0; i < n; i++) {
/*  5499 */       bb[i] = ((aa[i] - mean0) / sd0);
/*       */     }
/*       */     
/*  5502 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] standardise(int[] aa) {
/*  5506 */     return standardize(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] standardize(BigDecimal[] aa)
/*       */   {
/*  5512 */     double mean0 = mean(aa).doubleValue();
/*  5513 */     double sd0 = standardDeviation(aa);
/*  5514 */     int n = aa.length;
/*  5515 */     double[] bb = new double[n];
/*  5516 */     for (int i = 0; i < n; i++) {
/*  5517 */       bb[i] = ((aa[i].doubleValue() - mean0) / sd0);
/*       */     }
/*       */     
/*  5520 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] standardise(BigDecimal[] aa) {
/*  5524 */     return standardize(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] standardize(BigInteger[] aa)
/*       */   {
/*  5530 */     ArrayMaths am = new ArrayMaths(aa);
/*  5531 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/*       */     
/*  5533 */     return standardize(bd);
/*       */   }
/*       */   
/*       */   public static double[] standardise(BigInteger[] aa) {
/*  5537 */     return standardize(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] scale(double[] aa, double mean, double sd)
/*       */   {
/*  5543 */     double[] bb = standardize(aa);
/*  5544 */     int n = aa.length;
/*  5545 */     for (int i = 0; i < n; i++) {
/*  5546 */       bb[i] = (bb[i] * sd + mean);
/*       */     }
/*       */     
/*  5549 */     return bb;
/*       */   }
/*       */   
/*       */   public static float[] scale(float[] aa, float mean, float sd)
/*       */   {
/*  5554 */     float[] bb = standardize(aa);
/*  5555 */     int n = aa.length;
/*  5556 */     for (int i = 0; i < n; i++) {
/*  5557 */       bb[i] = (bb[i] * sd + mean);
/*       */     }
/*       */     
/*  5560 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] scale(long[] aa, double mean, double sd)
/*       */   {
/*  5565 */     double[] bb = standardize(aa);
/*  5566 */     int n = aa.length;
/*  5567 */     for (int i = 0; i < n; i++) {
/*  5568 */       bb[i] = (bb[i] * sd + mean);
/*       */     }
/*       */     
/*  5571 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] scale(int[] aa, double mean, double sd)
/*       */   {
/*  5576 */     double[] bb = standardize(aa);
/*  5577 */     int n = aa.length;
/*  5578 */     for (int i = 0; i < n; i++) {
/*  5579 */       bb[i] = (bb[i] * sd + mean);
/*       */     }
/*       */     
/*  5582 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] scale(BigDecimal[] aa, double mean, double sd)
/*       */   {
/*  5587 */     double[] bb = standardize(aa);
/*  5588 */     int n = aa.length;
/*  5589 */     for (int i = 0; i < n; i++) {
/*  5590 */       bb[i] = (bb[i] * sd + mean);
/*       */     }
/*       */     
/*  5593 */     return bb;
/*       */   }
/*       */   
/*       */   public static double[] scale(BigInteger[] aa, double mean, double sd)
/*       */   {
/*  5598 */     ArrayMaths am = new ArrayMaths(aa);
/*  5599 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/*       */     
/*  5601 */     return scale(bd, mean, sd);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double momentSkewness(double[] aa)
/*       */   {
/*  5609 */     int n = aa.length;
/*  5610 */     double denom = n - 1;
/*  5611 */     if (nFactorOptionS) denom = n;
/*  5612 */     double sum = 0.0D;
/*  5613 */     double mean = mean(aa);
/*  5614 */     for (int i = 0; i < n; i++) {
/*  5615 */       sum += Math.pow(aa[i] - mean, 3.0D);
/*       */     }
/*  5617 */     sum /= denom;
/*  5618 */     return sum / Math.pow(standardDeviation(aa), 3.0D);
/*       */   }
/*       */   
/*       */ 
/*       */   public static float momentSkewness(float[] aa)
/*       */   {
/*  5624 */     int n = aa.length;
/*  5625 */     float denom = n - 1;
/*  5626 */     if (nFactorOptionS) denom = n;
/*  5627 */     float sum = 0.0F;
/*  5628 */     float mean = mean(aa);
/*  5629 */     for (int i = 0; i < n; i++) {
/*  5630 */       sum = (float)(sum + Math.pow(aa[i] - mean, 3.0D));
/*       */     }
/*  5632 */     sum /= denom;
/*  5633 */     return sum / (float)Math.pow(standardDeviation(aa), 3.0D);
/*       */   }
/*       */   
/*       */   public static double momentSkewness(BigDecimal[] aa)
/*       */   {
/*  5638 */     int n = aa.length;
/*  5639 */     double denom = n - 1;
/*  5640 */     if (nFactorOptionS) denom = n;
/*  5641 */     BigDecimal sum = BigDecimal.ZERO;
/*  5642 */     BigDecimal mean = mean(aa);
/*  5643 */     double sd = standardDeviation(aa);
/*  5644 */     for (int i = 0; i < n; i++) {
/*  5645 */       BigDecimal hold = aa[i].subtract(mean);
/*  5646 */       sum = sum.add(hold.multiply(hold.multiply(hold)));
/*       */     }
/*  5648 */     sum = sum.multiply(new BigDecimal(1.0D / denom));
/*  5649 */     return sum.doubleValue() / Math.pow(sd, 3.0D);
/*       */   }
/*       */   
/*       */   public static double momentSkewness(long[] aa)
/*       */   {
/*  5654 */     int n = aa.length;
/*  5655 */     double denom = n - 1;
/*  5656 */     if (nFactorOptionS) denom = n;
/*  5657 */     double sum = 0.0D;
/*  5658 */     double mean = mean(aa);
/*  5659 */     for (int i = 0; i < n; i++) {
/*  5660 */       sum += Math.pow(aa[i] - mean, 3.0D);
/*       */     }
/*  5662 */     sum /= denom;
/*  5663 */     return sum / Math.pow(standardDeviation(aa), 3.0D);
/*       */   }
/*       */   
/*       */   public static double momentSkewness(int[] aa)
/*       */   {
/*  5668 */     int n = aa.length;
/*  5669 */     double denom = n - 1;
/*  5670 */     if (nFactorOptionS) denom = n;
/*  5671 */     double sum = 0.0D;
/*  5672 */     double mean = mean(aa);
/*  5673 */     for (int i = 0; i < n; i++) {
/*  5674 */       sum += Math.pow(aa[i] - mean, 3.0D);
/*       */     }
/*  5676 */     sum /= denom;
/*  5677 */     return sum / Math.pow(standardDeviation(aa), 3.0D);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double medianSkewness(double[] aa)
/*       */   {
/*  5685 */     double mean = mean(aa);
/*  5686 */     double median = median(aa);
/*  5687 */     double sd = standardDeviation(aa);
/*  5688 */     return 3.0D * (mean - median) / sd;
/*       */   }
/*       */   
/*       */   public static float medianSkewness(float[] aa)
/*       */   {
/*  5693 */     float mean = mean(aa);
/*  5694 */     float median = median(aa);
/*  5695 */     float sd = standardDeviation(aa);
/*  5696 */     return 3.0F * (mean - median) / sd;
/*       */   }
/*       */   
/*       */   public static double medianSkewness(BigDecimal[] aa)
/*       */   {
/*  5701 */     BigDecimal mean = mean(aa);
/*  5702 */     BigDecimal median = median(aa);
/*  5703 */     double sd = standardDeviation(aa);
/*  5704 */     return 3.0D * mean.subtract(median).doubleValue() / sd;
/*       */   }
/*       */   
/*       */   public static double medianSkewness(long[] aa)
/*       */   {
/*  5709 */     double mean = mean(aa);
/*  5710 */     double median = median(aa);
/*  5711 */     double sd = standardDeviation(aa);
/*  5712 */     return 3.0D * (mean - median) / sd;
/*       */   }
/*       */   
/*       */   public static double medianSkewness(int[] aa)
/*       */   {
/*  5717 */     double mean = mean(aa);
/*  5718 */     double median = median(aa);
/*  5719 */     double sd = standardDeviation(aa);
/*  5720 */     return 3.0D * (mean - median) / sd;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double quartileSkewness(double[] aa)
/*       */   {
/*  5726 */     int n = aa.length;
/*  5727 */     double median50 = median(aa);
/*  5728 */     int start1 = 0;
/*  5729 */     int start2 = 0;
/*  5730 */     int end1 = n / 2 - 1;
/*  5731 */     int end2 = n - 1;
/*  5732 */     if (Fmath.isOdd(n)) {
/*  5733 */       start2 = end1 + 2;
/*       */     }
/*       */     else {
/*  5736 */       start2 = end1 + 1;
/*       */     }
/*  5738 */     ArrayMaths am = new ArrayMaths(aa);
/*  5739 */     double[] first = am.subarray_as_double(start1, end1);
/*  5740 */     double[] last = am.subarray_as_double(start2, end2);
/*  5741 */     double median25 = median(first);
/*  5742 */     double median75 = median(last);
/*       */     
/*  5744 */     return (median25 - 2.0D * median50 + median75) / (median75 - median25);
/*       */   }
/*       */   
/*       */   public static float quartileSkewness(float[] aa)
/*       */   {
/*  5749 */     int n = aa.length;
/*  5750 */     float median50 = median(aa);
/*  5751 */     int start1 = 0;
/*  5752 */     int start2 = 0;
/*  5753 */     int end1 = n / 2 - 1;
/*  5754 */     int end2 = n - 1;
/*  5755 */     if (Fmath.isOdd(n)) {
/*  5756 */       start2 = end1 + 2;
/*       */     }
/*       */     else {
/*  5759 */       start2 = end1 + 1;
/*       */     }
/*  5761 */     ArrayMaths am = new ArrayMaths(aa);
/*  5762 */     float[] first = am.subarray_as_float(start1, end1);
/*  5763 */     float[] last = am.subarray_as_float(start2, end2);
/*  5764 */     float median25 = median(first);
/*  5765 */     float median75 = median(last);
/*       */     
/*  5767 */     return (median25 - 2.0F * median50 + median75) / (median75 - median25);
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigDecimal quartileSkewness(BigDecimal[] aa)
/*       */   {
/*  5773 */     int n = aa.length;
/*  5774 */     BigDecimal median50 = median(aa);
/*  5775 */     int start1 = 0;
/*  5776 */     int start2 = 0;
/*  5777 */     int end1 = n / 2 - 1;
/*  5778 */     int end2 = n - 1;
/*  5779 */     if (Fmath.isOdd(n)) {
/*  5780 */       start2 = end1 + 2;
/*       */     }
/*       */     else {
/*  5783 */       start2 = end1 + 1;
/*       */     }
/*  5785 */     ArrayMaths am = new ArrayMaths(aa);
/*  5786 */     BigDecimal[] first = am.subarray_as_BigDecimal(start1, end1);
/*  5787 */     BigDecimal[] last = am.subarray_as_BigDecimal(start2, end2);
/*  5788 */     BigDecimal median25 = median(first);
/*  5789 */     BigDecimal median75 = median(last);
/*  5790 */     BigDecimal ret1 = median25.subtract(median50.multiply(new BigDecimal(2.0D))).add(median75);
/*  5791 */     BigDecimal ret2 = median75.subtract(median25);
/*  5792 */     BigDecimal ret = ret1.divide(ret2, 4);
/*  5793 */     first = null;
/*  5794 */     last = null;
/*  5795 */     median25 = null;
/*  5796 */     median50 = null;
/*  5797 */     median75 = null;
/*  5798 */     ret1 = null;
/*  5799 */     ret2 = null;
/*  5800 */     return ret;
/*       */   }
/*       */   
/*       */   public static BigDecimal quartileSkewness(BigInteger[] aa)
/*       */   {
/*  5805 */     ArrayMaths am = new ArrayMaths(aa);
/*  5806 */     BigDecimal[] bd = am.array_as_BigDecimal();
/*  5807 */     return quartileSkewness(bd);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double quartileSkewness(long[] aa)
/*       */   {
/*  5813 */     int n = aa.length;
/*  5814 */     double median50 = median(aa);
/*  5815 */     int start1 = 0;
/*  5816 */     int start2 = 0;
/*  5817 */     int end1 = n / 2 - 1;
/*  5818 */     int end2 = n - 1;
/*  5819 */     if (Fmath.isOdd(n)) {
/*  5820 */       start2 = end1 + 2;
/*       */     }
/*       */     else {
/*  5823 */       start2 = end1 + 1;
/*       */     }
/*  5825 */     ArrayMaths am = new ArrayMaths(aa);
/*  5826 */     double[] first = am.subarray_as_double(start1, end1);
/*  5827 */     double[] last = am.subarray_as_double(start2, end2);
/*  5828 */     double median25 = median(first);
/*  5829 */     double median75 = median(last);
/*       */     
/*  5831 */     return (median25 - 2.0D * median50 + median75) / (median75 - median25);
/*       */   }
/*       */   
/*       */   public static double quartileSkewness(int[] aa)
/*       */   {
/*  5836 */     int n = aa.length;
/*  5837 */     double median50 = median(aa);
/*  5838 */     int start1 = 0;
/*  5839 */     int start2 = 0;
/*  5840 */     int end1 = n / 2 - 1;
/*  5841 */     int end2 = n - 1;
/*  5842 */     if (Fmath.isOdd(n)) {
/*  5843 */       start2 = end1 + 2;
/*       */     }
/*       */     else {
/*  5846 */       start2 = end1 + 1;
/*       */     }
/*  5848 */     ArrayMaths am = new ArrayMaths(aa);
/*  5849 */     double[] first = am.subarray_as_double(start1, end1);
/*  5850 */     double[] last = am.subarray_as_double(start2, end2);
/*  5851 */     double median25 = median(first);
/*  5852 */     double median75 = median(last);
/*       */     
/*  5854 */     return (median25 - 2.0D * median50 + median75) / (median75 - median25);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double kurtosis(double[] aa)
/*       */   {
/*  5863 */     int n = aa.length;
/*  5864 */     double denom = n - 1;
/*  5865 */     if (nFactorOptionS) denom = n;
/*  5866 */     double sum = 0.0D;
/*  5867 */     double mean = mean(aa);
/*  5868 */     for (int i = 0; i < n; i++) {
/*  5869 */       sum += Math.pow(aa[i] - mean, 4.0D);
/*       */     }
/*  5871 */     sum /= denom;
/*  5872 */     return sum / Fmath.square(variance(aa));
/*       */   }
/*       */   
/*       */   public static double curtosis(double[] aa) {
/*  5876 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static double kurtosisExcess(double[] aa)
/*       */   {
/*  5881 */     return kurtosis(aa) - 3.0D;
/*       */   }
/*       */   
/*       */   public static double curtosisExcess(double[] aa) {
/*  5885 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessKurtosis(double[] aa) {
/*  5889 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessCurtosis(double[] aa) {
/*  5893 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static float kurtosis(float[] aa)
/*       */   {
/*  5898 */     int n = aa.length;
/*  5899 */     float denom = n - 1;
/*  5900 */     if (nFactorOptionS) denom = n;
/*  5901 */     float sum = 0.0F;
/*  5902 */     float mean = mean(aa);
/*  5903 */     for (int i = 0; i < n; i++) {
/*  5904 */       sum = (float)(sum + Math.pow(aa[i] - mean, 4.0D));
/*       */     }
/*  5906 */     sum /= denom;
/*  5907 */     return sum / Fmath.square(variance(aa));
/*       */   }
/*       */   
/*       */   public static float curtosis(float[] aa) {
/*  5911 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static float kurtosisExcess(float[] aa)
/*       */   {
/*  5916 */     return kurtosis(aa) - 3.0F;
/*       */   }
/*       */   
/*       */   public static float curtosisExcess(float[] aa) {
/*  5920 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static float excessKurtosis(float[] aa) {
/*  5924 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static float excessCurtosis(float[] aa) {
/*  5928 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal kurtosis(BigInteger[] aa)
/*       */   {
/*  5933 */     ArrayMaths am = new ArrayMaths(aa);
/*  5934 */     BigDecimal[] bd = am.array_as_BigDecimal();
/*  5935 */     return kurtosis(bd);
/*       */   }
/*       */   
/*       */   public static BigDecimal curtosis(BigInteger[] aa) {
/*  5939 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal kurtosisExcess(BigInteger[] aa)
/*       */   {
/*  5944 */     return kurtosis(aa).subtract(new BigDecimal("3.0"));
/*       */   }
/*       */   
/*       */   public static BigDecimal curtosisExcess(BigInteger[] aa) {
/*  5948 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal excessKurtosis(BigInteger[] aa) {
/*  5952 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal excessCurtosis(BigInteger[] aa) {
/*  5956 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigDecimal kurtosis(BigDecimal[] aa)
/*       */   {
/*  5962 */     int n = aa.length;
/*  5963 */     double denom = n - 1;
/*  5964 */     if (nFactorOptionS) denom = n;
/*  5965 */     BigDecimal sum = BigDecimal.ZERO;
/*  5966 */     BigDecimal mean = mean(aa);
/*  5967 */     for (int i = 0; i < n; i++) {
/*  5968 */       BigDecimal hold = aa[i].subtract(mean);
/*  5969 */       sum = sum.add(hold.multiply(hold.multiply(hold.multiply(hold))));
/*       */     }
/*  5971 */     sum = sum.divide(new BigDecimal(denom), 4);
/*  5972 */     mean = variance(aa);
/*  5973 */     sum = sum.divide(mean.multiply(mean), 4);
/*  5974 */     mean = null;
/*  5975 */     return sum;
/*       */   }
/*       */   
/*       */   public static BigDecimal curtosis(BigDecimal[] aa) {
/*  5979 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal kurtosisExcess(BigDecimal[] aa)
/*       */   {
/*  5984 */     return kurtosis(aa).subtract(new BigDecimal("3.0"));
/*       */   }
/*       */   
/*       */   public static BigDecimal curtosisExcess(BigDecimal[] aa) {
/*  5988 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal excessCurtosis(BigDecimal[] aa) {
/*  5992 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static BigDecimal excessKurtosis(BigDecimal[] aa) {
/*  5996 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double kurtosis(long[] aa)
/*       */   {
/*  6002 */     int n = aa.length;
/*  6003 */     double denom = n - 1;
/*  6004 */     if (nFactorOptionS) denom = n;
/*  6005 */     double sum = 0.0D;
/*  6006 */     double mean = mean(aa);
/*  6007 */     for (int i = 0; i < n; i++) {
/*  6008 */       sum += Math.pow(aa[i] - mean, 4.0D);
/*       */     }
/*  6010 */     sum /= denom;
/*  6011 */     return sum / Fmath.square(variance(aa));
/*       */   }
/*       */   
/*       */   public static double curtosis(long[] aa) {
/*  6015 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static double kurtosisExcess(long[] aa)
/*       */   {
/*  6020 */     return kurtosis(aa) - 3.0D;
/*       */   }
/*       */   
/*       */   public static double curtosisExcess(long[] aa) {
/*  6024 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessCurtosis(long[] aa) {
/*  6028 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessKurtosis(long[] aa) {
/*  6032 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double kurtosis(int[] aa)
/*       */   {
/*  6038 */     int n = aa.length;
/*  6039 */     double denom = n - 1;
/*  6040 */     if (nFactorOptionS) denom = n;
/*  6041 */     double sum = 0.0D;
/*  6042 */     double mean = mean(aa);
/*  6043 */     for (int i = 0; i < n; i++) {
/*  6044 */       sum += Math.pow(aa[i] - mean, 4.0D);
/*       */     }
/*  6046 */     sum /= denom;
/*  6047 */     return sum / Fmath.square(variance(aa));
/*       */   }
/*       */   
/*       */   public static double curtosis(int[] aa) {
/*  6051 */     return kurtosis(aa);
/*       */   }
/*       */   
/*       */   public static double kurtosisExcess(int[] aa)
/*       */   {
/*  6056 */     return kurtosis(aa) - 3.0D;
/*       */   }
/*       */   
/*       */   public static double curtosisExcess(int[] aa) {
/*  6060 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessCurtosis(int[] aa) {
/*  6064 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */   public static double excessKurtosis(int[] aa) {
/*  6068 */     return kurtosisExcess(aa);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static BigDecimal variance(BigDecimal[] aa)
/*       */   {
/*  6075 */     int n = aa.length;
/*  6076 */     BigDecimal sum = BigDecimal.ZERO;
/*  6077 */     BigDecimal mean = mean(aa);
/*  6078 */     for (int i = 0; i < n; i++) {
/*  6079 */       BigDecimal hold = aa[i].subtract(mean);
/*  6080 */       sum = sum.add(hold.multiply(hold));
/*       */     }
/*  6082 */     BigDecimal ret = sum.divide(new BigDecimal(n - 1), 4);
/*  6083 */     if (nFactorOptionS) ret = sum.divide(new BigDecimal(n), 4);
/*  6084 */     sum = null;
/*  6085 */     mean = null;
/*  6086 */     return ret;
/*       */   }
/*       */   
/*       */   public static BigDecimal variance(BigInteger[] aa)
/*       */   {
/*  6091 */     int n = aa.length;
/*  6092 */     BigDecimal sum = BigDecimal.ZERO;
/*  6093 */     BigDecimal mean = BigDecimal.ZERO;
/*  6094 */     for (int i = 0; i < n; i++) {
/*  6095 */       sum = sum.add(new BigDecimal(aa[i]));
/*       */     }
/*  6097 */     mean = sum.divide(new BigDecimal(n), 4);
/*  6098 */     sum = BigDecimal.ZERO;
/*  6099 */     for (int i = 0; i < n; i++) {
/*  6100 */       BigDecimal hold = new BigDecimal(aa[i]).subtract(mean);
/*  6101 */       sum = sum.add(hold.multiply(hold));
/*       */     }
/*  6103 */     BigDecimal ret = sum.divide(new BigDecimal(n - 1), 4);
/*  6104 */     if (nFactorOptionS) ret = sum.divide(new BigDecimal(n), 4);
/*  6105 */     sum = null;
/*  6106 */     mean = null;
/*  6107 */     return ret;
/*       */   }
/*       */   
/*       */   public static Complex variance(Complex[] aa)
/*       */   {
/*  6112 */     int n = aa.length;
/*  6113 */     Complex sum = Complex.zero();
/*  6114 */     Complex mean = mean(aa);
/*  6115 */     for (int i = 0; i < n; i++) {
/*  6116 */       Complex hold = new Complex(aa[i]).minus(mean);
/*  6117 */       sum = sum.plus(hold.times(hold));
/*       */     }
/*  6119 */     Complex ret = sum.over(new Complex(n - 1));
/*  6120 */     if (nFactorOptionS) ret = sum.over(new Complex(n));
/*  6121 */     return ret;
/*       */   }
/*       */   
/*       */   public static double varianceConjugateCalcn(Complex[] aa)
/*       */   {
/*  6126 */     int n = aa.length;
/*  6127 */     Complex sum = Complex.zero();
/*  6128 */     Complex mean = mean(aa);
/*  6129 */     for (int i = 0; i < n; i++) {
/*  6130 */       Complex hold = new Complex(aa[i]).minus(mean);
/*  6131 */       sum = sum.plus(hold.times(hold.conjugate()));
/*       */     }
/*  6133 */     double ret = sum.getReal() / (n - 1);
/*  6134 */     if (nFactorOptionS) ret = sum.getReal() / n;
/*  6135 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double varianceModuli(Complex[] aa)
/*       */   {
/*  6141 */     ArrayMaths am = new ArrayMaths(aa);
/*  6142 */     double[] rl = am.array_as_modulus_of_Complex();
/*  6143 */     double variance = variance(rl);
/*  6144 */     return variance;
/*       */   }
/*       */   
/*       */   public static double varianceRealParts(Complex[] aa)
/*       */   {
/*  6149 */     ArrayMaths am = new ArrayMaths(aa);
/*  6150 */     double[] rl = am.array_as_real_part_of_Complex();
/*  6151 */     double variance = variance(rl);
/*  6152 */     return variance;
/*       */   }
/*       */   
/*       */   public static double varianceImaginaryParts(Complex[] aa)
/*       */   {
/*  6157 */     ArrayMaths am = new ArrayMaths(aa);
/*  6158 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  6159 */     double variance = variance(im);
/*  6160 */     return variance;
/*       */   }
/*       */   
/*       */   public static double variance(double[] aa)
/*       */   {
/*  6165 */     int n = aa.length;
/*  6166 */     double sum = 0.0D;
/*  6167 */     double mean = mean(aa);
/*  6168 */     sum = 0.0D;
/*  6169 */     for (int i = 0; i < n; i++) {
/*  6170 */       sum += Fmath.square(aa[i] - mean);
/*       */     }
/*  6172 */     double ret = sum / (n - 1);
/*  6173 */     if (nFactorOptionS) ret = sum / n;
/*  6174 */     return ret;
/*       */   }
/*       */   
/*       */   public static float variance(float[] aa)
/*       */   {
/*  6179 */     int n = aa.length;
/*  6180 */     float sum = 0.0F;
/*  6181 */     float mean = mean(aa);
/*  6182 */     for (int i = 0; i < n; i++) {
/*  6183 */       sum += Fmath.square(aa[i] - mean);
/*       */     }
/*  6185 */     float ret = sum / (n - 1);
/*  6186 */     if (nFactorOptionS) ret = sum / n;
/*  6187 */     return ret;
/*       */   }
/*       */   
/*       */   public static double variance(int[] aa)
/*       */   {
/*  6192 */     int n = aa.length;
/*  6193 */     double sum = 0.0D;
/*  6194 */     double mean = mean(aa);
/*  6195 */     for (int i = 0; i < n; i++) {
/*  6196 */       sum += Fmath.square(aa[i] - mean);
/*       */     }
/*  6198 */     double ret = sum / (n - 1);
/*  6199 */     if (nFactorOptionS) ret = sum / n;
/*  6200 */     return ret;
/*       */   }
/*       */   
/*       */   public static double variance(long[] aa)
/*       */   {
/*  6205 */     int n = aa.length;
/*  6206 */     double sum = 0.0D;
/*  6207 */     double mean = mean(aa);
/*  6208 */     for (int i = 0; i < n; i++) {
/*  6209 */       sum += Fmath.square(aa[i] - mean);
/*       */     }
/*  6211 */     double ret = sum / (n - 1);
/*  6212 */     if (nFactorOptionS) ret = sum / n;
/*  6213 */     return ret;
/*       */   }
/*       */   
/*       */   public static double variance(double[] aa, double[] ww)
/*       */   {
/*  6218 */     int n = aa.length;
/*  6219 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6220 */     double nn = effectiveSampleNumber(ww);
/*  6221 */     double nterm = nn / (nn - 1.0D);
/*  6222 */     if (nFactorOptionS) { nterm = 1.0D;
/*       */     }
/*  6224 */     double sumx = 0.0D;double sumw = 0.0D;double mean = 0.0D;
/*  6225 */     double[] weight = invertAndSquare(ww);
/*  6226 */     for (int i = 0; i < n; i++) {
/*  6227 */       sumx += aa[i] * weight[i];
/*  6228 */       sumw += weight[i];
/*       */     }
/*  6230 */     mean = sumx / sumw;
/*  6231 */     sumx = 0.0D;
/*  6232 */     for (int i = 0; i < n; i++) {
/*  6233 */       sumx += weight[i] * Fmath.square(aa[i] - mean);
/*       */     }
/*  6235 */     return sumx * nterm / sumw;
/*       */   }
/*       */   
/*       */   public static float variance(float[] aa, float[] ww)
/*       */   {
/*  6240 */     int n = aa.length;
/*  6241 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6242 */     float nn = effectiveSampleNumber(ww);
/*  6243 */     float nterm = nn / (nn - 1.0F);
/*  6244 */     if (nFactorOptionS) { nterm = 1.0F;
/*       */     }
/*  6246 */     float sumx = 0.0F;float sumw = 0.0F;float mean = 0.0F;
/*  6247 */     float[] weight = invertAndSquare(ww);
/*  6248 */     for (int i = 0; i < n; i++) {
/*  6249 */       sumx += aa[i] * weight[i];
/*  6250 */       sumw += weight[i];
/*       */     }
/*  6252 */     mean = sumx / sumw;
/*  6253 */     sumx = 0.0F;
/*  6254 */     for (int i = 0; i < n; i++) {
/*  6255 */       sumx += weight[i] * Fmath.square(aa[i] - mean);
/*       */     }
/*  6257 */     return sumx * nterm / sumw;
/*       */   }
/*       */   
/*       */   public static Complex variance(Complex[] aa, Complex[] ww)
/*       */   {
/*  6262 */     int n = aa.length;
/*  6263 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6264 */     Complex nn = effectiveSampleNumber(ww);
/*  6265 */     Complex nterm = nn.over(nn.minus(1.0D));
/*  6266 */     if (nFactorOptionS) nterm = Complex.plusOne();
/*  6267 */     Complex sumx = Complex.zero();
/*  6268 */     Complex sumw = Complex.zero();
/*  6269 */     Complex mean = Complex.zero();
/*  6270 */     Complex[] weight = invertAndSquare(ww);
/*  6271 */     for (int i = 0; i < n; i++) {
/*  6272 */       sumx = sumx.plus(aa[i].times(weight[i]));
/*  6273 */       sumw = sumw.plus(weight[i]);
/*       */     }
/*  6275 */     mean = sumx.over(sumw);
/*  6276 */     sumx = Complex.zero();
/*  6277 */     for (int i = 0; i < n; i++) {
/*  6278 */       Complex hold = aa[i].minus(mean);
/*  6279 */       sumx = sumx.plus(weight[i].times(hold).times(hold));
/*       */     }
/*  6281 */     return sumx.times(nterm).over(sumw);
/*       */   }
/*       */   
/*       */   public static double varianceConjugateCalcn(Complex[] aa, Complex[] ww)
/*       */   {
/*  6286 */     int n = aa.length;
/*  6287 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6288 */     double nn = effectiveSampleNumberConjugateCalcn(ww);
/*  6289 */     double nterm = nn / (nn - 1.0D);
/*  6290 */     if (nFactorOptionS) nterm = 1.0D;
/*  6291 */     Complex sumx = Complex.zero();
/*  6292 */     Complex sumw = Complex.zero();
/*  6293 */     Complex sumwc = Complex.zero();
/*  6294 */     Complex mean = Complex.zero();
/*  6295 */     Stat st = new Stat(ww);
/*  6296 */     st = st.invert();
/*  6297 */     Complex[] weight = st.array_as_Complex();
/*  6298 */     for (int i = 0; i < n; i++) {
/*  6299 */       sumx = sumx.plus(aa[i].times(weight[i].times(weight[i])));
/*  6300 */       sumw = sumw.plus(weight[i].times(weight[i]));
/*  6301 */       sumwc = sumwc.plus(weight[i].times(weight[i].conjugate()));
/*       */     }
/*  6303 */     mean = sumx.over(sumw);
/*  6304 */     sumx = Complex.zero();
/*       */     
/*  6306 */     for (int i = 0; i < n; i++) {
/*  6307 */       Complex hold = aa[i].minus(mean);
/*  6308 */       sumx = sumx.plus(weight[i].times(weight[i].conjugate()).times(hold).times(hold.conjugate()));
/*       */     }
/*  6310 */     return nterm * sumx.times(nterm).over(sumwc).getReal();
/*       */   }
/*       */   
/*       */   public static double varianceModuli(Complex[] aa, Complex[] ww)
/*       */   {
/*  6315 */     int n = aa.length;
/*  6316 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6317 */     ArrayMaths am = new ArrayMaths(aa);
/*  6318 */     double[] rl = am.array_as_modulus_of_Complex();
/*  6319 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6320 */     double[] wt = wm.array_as_modulus_of_Complex();
/*  6321 */     double variance = variance(rl, wt);
/*  6322 */     return variance;
/*       */   }
/*       */   
/*       */   public static double varianceRealParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  6327 */     int n = aa.length;
/*  6328 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6329 */     ArrayMaths am = new ArrayMaths(aa);
/*  6330 */     double[] rl = am.array_as_real_part_of_Complex();
/*  6331 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6332 */     double[] wt = wm.array_as_real_part_of_Complex();
/*  6333 */     double variance = variance(rl, wt);
/*  6334 */     return variance;
/*       */   }
/*       */   
/*       */   public static double varianceImaginaryParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  6339 */     int n = aa.length;
/*  6340 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6341 */     ArrayMaths am = new ArrayMaths(aa);
/*  6342 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  6343 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6344 */     double[] wt = wm.array_as_imaginary_part_of_Complex();
/*  6345 */     double variance = variance(im, wt);
/*  6346 */     return variance;
/*       */   }
/*       */   
/*       */   public static BigDecimal variance(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  6351 */     int n = aa.length;
/*  6352 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6353 */     BigDecimal nn = effectiveSampleNumber(ww);
/*  6354 */     BigDecimal nterm = nn.divide(nn.subtract(BigDecimal.ONE), 4);
/*  6355 */     if (nFactorOptionS) nterm = BigDecimal.ONE;
/*  6356 */     BigDecimal sumx = BigDecimal.ZERO;
/*  6357 */     BigDecimal sumw = BigDecimal.ZERO;
/*  6358 */     BigDecimal mean = BigDecimal.ZERO;
/*  6359 */     BigDecimal[] weight = invertAndSquare(ww);
/*  6360 */     for (int i = 0; i < n; i++) {
/*  6361 */       sumx = sumx.add(aa[i].multiply(weight[i]));
/*  6362 */       sumw = sumw.add(weight[i]);
/*       */     }
/*  6364 */     mean = sumx.divide(sumw, 4);
/*  6365 */     sumx = BigDecimal.ZERO;
/*  6366 */     for (int i = 0; i < n; i++) {
/*  6367 */       sumx = sumx.add(weight[i].multiply(aa[i].subtract(mean)).multiply(aa[i].subtract(mean)));
/*       */     }
/*  6369 */     sumx = sumx.multiply(nterm).divide(sumw, 4);
/*  6370 */     sumw = null;
/*  6371 */     mean = null;
/*  6372 */     weight = null;
/*  6373 */     nn = null;
/*  6374 */     nterm = null;
/*  6375 */     return sumx;
/*       */   }
/*       */   
/*       */   public static BigDecimal variance(BigInteger[] aa, BigInteger[] ww) {
/*  6379 */     int n = aa.length;
/*  6380 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6381 */     ArrayMaths aab = new ArrayMaths(aa);
/*  6382 */     ArrayMaths wwb = new ArrayMaths(ww);
/*  6383 */     return variance(aab.array_as_BigDecimal(), wwb.array_as_BigDecimal());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double standardError(BigDecimal[] aa)
/*       */   {
/*  6391 */     return Math.sqrt(variance(aa).doubleValue() / aa.length);
/*       */   }
/*       */   
/*       */   public static double standardError(BigInteger[] aa)
/*       */   {
/*  6396 */     return Math.sqrt(variance(aa).doubleValue() / aa.length);
/*       */   }
/*       */   
/*       */   public static Complex standardError(Complex[] aa)
/*       */   {
/*  6401 */     return Complex.sqrt(variance(aa).over(aa.length));
/*       */   }
/*       */   
/*       */   public static double standardErrorConjugateCalcn(Complex[] aa)
/*       */   {
/*  6406 */     return Math.sqrt(varianceConjugateCalcn(aa) / aa.length);
/*       */   }
/*       */   
/*       */   public static double standardErrorModuli(Complex[] aa)
/*       */   {
/*  6411 */     ArrayMaths am = new ArrayMaths(aa);
/*  6412 */     double[] rl = am.array_as_modulus_of_Complex();
/*  6413 */     return standardError(rl);
/*       */   }
/*       */   
/*       */   public static double standardErrorRealParts(Complex[] aa)
/*       */   {
/*  6418 */     ArrayMaths am = new ArrayMaths(aa);
/*  6419 */     double[] rl = am.array_as_real_part_of_Complex();
/*  6420 */     return standardError(rl);
/*       */   }
/*       */   
/*       */   public static double standardErrorImaginaryParts(Complex[] aa)
/*       */   {
/*  6425 */     ArrayMaths am = new ArrayMaths(aa);
/*  6426 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  6427 */     return standardError(im);
/*       */   }
/*       */   
/*       */   public static double standardError(double[] aa)
/*       */   {
/*  6432 */     return Math.sqrt(variance(aa) / aa.length);
/*       */   }
/*       */   
/*       */   public static float standardError(float[] aa)
/*       */   {
/*  6437 */     return (float)Math.sqrt(variance(aa) / aa.length);
/*       */   }
/*       */   
/*       */   public static double standardError(int[] aa)
/*       */   {
/*  6442 */     return Math.sqrt(variance(aa) / aa.length);
/*       */   }
/*       */   
/*       */   public static double standardError(long[] aa)
/*       */   {
/*  6447 */     return Math.sqrt(variance(aa) / aa.length);
/*       */   }
/*       */   
/*       */   public static Complex standardError(Complex[] aa, Complex[] ww)
/*       */   {
/*  6452 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  6453 */     Complex effectiveNumber = effectiveSampleNumber(ww);
/*  6454 */     return Complex.sqrt(variance(aa, ww).over(effectiveNumber));
/*       */   }
/*       */   
/*       */   public static double standardErrorConjugateCalcn(Complex[] aa, Complex[] ww)
/*       */   {
/*  6459 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  6460 */     double effectiveNumber = effectiveSampleNumberConjugateCalcn(ww);
/*  6461 */     return Math.sqrt(varianceConjugateCalcn(aa, ww) / effectiveNumber);
/*       */   }
/*       */   
/*       */   public static double standardErrorModuli(Complex[] aa, Complex[] ww)
/*       */   {
/*  6466 */     int n = aa.length;
/*  6467 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6468 */     ArrayMaths am = new ArrayMaths(aa);
/*  6469 */     double[] rl = am.array_as_modulus_of_Complex();
/*  6470 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6471 */     double[] wt = wm.array_as_modulus_of_Complex();
/*  6472 */     return standardError(rl, wt);
/*       */   }
/*       */   
/*       */   public static double standardErrorRealParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  6477 */     int n = aa.length;
/*  6478 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6479 */     ArrayMaths am = new ArrayMaths(aa);
/*  6480 */     double[] rl = am.array_as_real_part_of_Complex();
/*  6481 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6482 */     double[] wt = wm.array_as_real_part_of_Complex();
/*  6483 */     return standardError(rl, wt);
/*       */   }
/*       */   
/*       */   public static double standardErrorImaginaryParts(Complex[] aa, Complex[] ww)
/*       */   {
/*  6488 */     int n = aa.length;
/*  6489 */     if (n != ww.length) throw new IllegalArgumentException("length of variable array, " + n + " and length of weight array, " + ww.length + " are different");
/*  6490 */     ArrayMaths am = new ArrayMaths(aa);
/*  6491 */     double[] im = am.array_as_imaginary_part_of_Complex();
/*  6492 */     ArrayMaths wm = new ArrayMaths(ww);
/*  6493 */     double[] wt = wm.array_as_imaginary_part_of_Complex();
/*  6494 */     return standardError(im, wt);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double standardError(BigDecimal[] aa, BigDecimal[] ww)
/*       */   {
/*  6500 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  6501 */     double effectiveNumber = effectiveSampleNumber(ww).doubleValue();
/*  6502 */     return Math.sqrt(variance(aa, ww).doubleValue() / effectiveNumber);
/*       */   }
/*       */   
/*       */   public static double standardError(BigInteger[] aa, BigInteger[] ww)
/*       */   {
/*  6507 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  6508 */     double effectiveNumber = effectiveSampleNumber(ww).doubleValue();
/*  6509 */     return Math.sqrt(variance(aa, ww).doubleValue() / effectiveNumber);
/*       */   }
/*       */   
/*       */   public static double standardError(double[] aa, double[] ww)
/*       */   {
/*  6514 */     if (aa.length != ww.length) throw new IllegalArgumentException("length of variable array, " + aa.length + " and length of weight array, " + ww.length + " are different");
/*  6515 */     double effectiveNumber = effectiveSampleNumber(ww);
/*  6516 */     return Math.sqrt(variance(aa, ww) / effectiveNumber);
/*       */   }
/*       */   
/*       */   public static float standardError(float[] aa, float[] ww)
/*       */   {
/*  6521 */     float effectiveNumber = effectiveSampleNumber(ww);
/*  6522 */     return (float)Math.sqrt(variance(aa, ww) / effectiveNumber);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double covariance(double[] xx, double[] yy)
/*       */   {
/*  6529 */     int n = xx.length;
/*  6530 */     if (n != yy.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of y array, " + yy.length + " are different");
/*  6531 */     double denom = n - 1;
/*  6532 */     if (nFactorOptionS) { denom = n;
/*       */     }
/*  6534 */     double sumx = 0.0D;double meanx = 0.0D;
/*  6535 */     double sumy = 0.0D;double meany = 0.0D;
/*  6536 */     for (int i = 0; i < n; i++) {
/*  6537 */       sumx += xx[i];
/*  6538 */       sumy += yy[i];
/*       */     }
/*  6540 */     meanx = sumx / n;
/*  6541 */     meany = sumy / n;
/*  6542 */     double sum = 0.0D;
/*  6543 */     for (int i = 0; i < n; i++) {
/*  6544 */       sum += (xx[i] - meanx) * (yy[i] - meany);
/*       */     }
/*  6546 */     return sum / denom;
/*       */   }
/*       */   
/*       */   public static float covariance(float[] xx, float[] yy)
/*       */   {
/*  6551 */     int n = xx.length;
/*  6552 */     if (n != yy.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of y array, " + yy.length + " are different");
/*  6553 */     float denom = n - 1;
/*  6554 */     if (nFactorOptionS) { denom = n;
/*       */     }
/*  6556 */     float sumx = 0.0F;float meanx = 0.0F;
/*  6557 */     float sumy = 0.0F;float meany = 0.0F;
/*  6558 */     for (int i = 0; i < n; i++) {
/*  6559 */       sumx += xx[i];
/*  6560 */       sumy += yy[i];
/*       */     }
/*  6562 */     meanx = sumx / n;
/*  6563 */     meany = sumy / n;
/*  6564 */     float sum = 0.0F;
/*  6565 */     for (int i = 0; i < n; i++) {
/*  6566 */       sum += (xx[i] - meanx) * (yy[i] - meany);
/*       */     }
/*  6568 */     return sum / denom;
/*       */   }
/*       */   
/*       */   public static double covariance(int[] xx, int[] yy)
/*       */   {
/*  6573 */     int n = xx.length;
/*  6574 */     if (n != yy.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of y array, " + yy.length + " are different");
/*  6575 */     double denom = n - 1;
/*  6576 */     if (nFactorOptionS) { denom = n;
/*       */     }
/*  6578 */     double sumx = 0.0D;double meanx = 0.0D;
/*  6579 */     double sumy = 0.0D;double meany = 0.0D;
/*  6580 */     for (int i = 0; i < n; i++) {
/*  6581 */       sumx += xx[i];
/*  6582 */       sumy += yy[i];
/*       */     }
/*  6584 */     meanx = sumx / n;
/*  6585 */     meany = sumy / n;
/*  6586 */     double sum = 0.0D;
/*  6587 */     for (int i = 0; i < n; i++) {
/*  6588 */       sum += (xx[i] - meanx) * (yy[i] - meany);
/*       */     }
/*  6590 */     return sum / denom;
/*       */   }
/*       */   
/*       */   public static double covariance(long[] xx, long[] yy)
/*       */   {
/*  6595 */     int n = xx.length;
/*  6596 */     if (n != yy.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of y array, " + yy.length + " are different");
/*  6597 */     double denom = n - 1;
/*  6598 */     if (nFactorOptionS) { denom = n;
/*       */     }
/*  6600 */     double sumx = 0.0D;double meanx = 0.0D;
/*  6601 */     double sumy = 0.0D;double meany = 0.0D;
/*  6602 */     for (int i = 0; i < n; i++) {
/*  6603 */       sumx += xx[i];
/*  6604 */       sumy += yy[i];
/*       */     }
/*  6606 */     meanx = sumx / n;
/*  6607 */     meany = sumy / n;
/*  6608 */     double sum = 0.0D;
/*  6609 */     for (int i = 0; i < n; i++) {
/*  6610 */       sum += (xx[i] - meanx) * (yy[i] - meany);
/*       */     }
/*  6612 */     return sum / denom;
/*       */   }
/*       */   
/*       */   public static double covariance(double[] xx, double[] yy, double[] ww)
/*       */   {
/*  6617 */     int n = xx.length;
/*  6618 */     if (n != yy.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of y array, " + yy.length + " are different");
/*  6619 */     if (n != ww.length) throw new IllegalArgumentException("length of x variable array, " + n + " and length of weight array, " + yy.length + " are different");
/*  6620 */     double nn = effectiveSampleNumber(ww);
/*  6621 */     double nterm = nn / (nn - 1.0D);
/*  6622 */     if (nFactorOptionS) nterm = 1.0D;
/*  6623 */     double sumx = 0.0D;double sumy = 0.0D;double sumw = 0.0D;double meanx = 0.0D;double meany = 0.0D;
/*  6624 */     double[] weight = invertAndSquare(ww);
/*  6625 */     for (int i = 0; i < n; i++) {
/*  6626 */       sumx += xx[i] * weight[i];
/*  6627 */       sumy += yy[i] * weight[i];
/*  6628 */       sumw += weight[i];
/*       */     }
/*  6630 */     meanx = sumx / sumw;
/*  6631 */     meany = sumy / sumw;
/*       */     
/*  6633 */     double sum = 0.0D;
/*  6634 */     for (int i = 0; i < n; i++) {
/*  6635 */       sum += weight[i] * (xx[i] - meanx) * (yy[i] - meany);
/*       */     }
/*  6637 */     return sum * nterm / sumw;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double corrCoeff(double[] xx, double[] yy)
/*       */   {
/*  6647 */     double temp0 = 0.0D;double temp1 = 0.0D;
/*  6648 */     int nData = xx.length;
/*  6649 */     if (yy.length != nData) throw new IllegalArgumentException("array lengths must be equal");
/*  6650 */     int df = nData - 1;
/*       */     
/*  6652 */     double mx = 0.0D;
/*  6653 */     double my = 0.0D;
/*  6654 */     for (int i = 0; i < nData; i++) {
/*  6655 */       mx += xx[i];
/*  6656 */       my += yy[i];
/*       */     }
/*  6658 */     mx /= nData;
/*  6659 */     my /= nData;
/*       */     
/*       */ 
/*  6662 */     double s2xx = 0.0D;
/*  6663 */     double s2yy = 0.0D;
/*  6664 */     double s2xy = 0.0D;
/*  6665 */     for (int i = 0; i < nData; i++) {
/*  6666 */       s2xx += Fmath.square(xx[i] - mx);
/*  6667 */       s2yy += Fmath.square(yy[i] - my);
/*  6668 */       s2xy += (xx[i] - mx) * (yy[i] - my);
/*       */     }
/*       */     
/*       */ 
/*  6672 */     double sampleR = s2xy / Math.sqrt(s2xx * s2yy);
/*       */     
/*  6674 */     return sampleR;
/*       */   }
/*       */   
/*       */ 
/*       */   public static float corrCoeff(float[] x, float[] y)
/*       */   {
/*  6680 */     int nData = x.length;
/*  6681 */     if (y.length != nData) throw new IllegalArgumentException("array lengths must be equal");
/*  6682 */     int n = x.length;
/*  6683 */     double[] xx = new double[n];
/*  6684 */     double[] yy = new double[n];
/*  6685 */     for (int i = 0; i < n; i++) {
/*  6686 */       xx[i] = x[i];
/*  6687 */       yy[i] = y[i];
/*       */     }
/*  6689 */     return (float)corrCoeff(xx, yy);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double corrCoeff(int[] x, int[] y)
/*       */   {
/*  6695 */     int n = x.length;
/*  6696 */     if (y.length != n) { throw new IllegalArgumentException("array lengths must be equal");
/*       */     }
/*  6698 */     double[] xx = new double[n];
/*  6699 */     double[] yy = new double[n];
/*  6700 */     for (int i = 0; i < n; i++) {
/*  6701 */       xx[i] = x[i];
/*  6702 */       yy[i] = y[i];
/*       */     }
/*  6704 */     return corrCoeff(xx, yy);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double corrCoeff(double[] x, double[] y, double[] w)
/*       */   {
/*  6710 */     int n = x.length;
/*  6711 */     if (y.length != n) throw new IllegalArgumentException("x and y array lengths must be equal");
/*  6712 */     if (w.length != n) { throw new IllegalArgumentException("x and weight array lengths must be equal");
/*       */     }
/*  6714 */     double sxy = covariance(x, y, w);
/*  6715 */     double sx = variance(x, w);
/*  6716 */     double sy = variance(y, w);
/*  6717 */     return sxy / Math.sqrt(sx * sy);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double corrCoeff(int element00, int element01, int element10, int element11)
/*       */   {
/*  6728 */     return (element00 * element11 - element01 * element10) / Math.sqrt((element00 + element01) * (element10 + element11) * (element00 + element10) * (element01 + element11));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double corrCoeff(int[][] freqMatrix)
/*       */   {
/*  6739 */     double element00 = freqMatrix[0][0];
/*  6740 */     double element01 = freqMatrix[0][1];
/*  6741 */     double element10 = freqMatrix[1][0];
/*  6742 */     double element11 = freqMatrix[1][1];
/*  6743 */     return (element00 * element11 - element01 * element10) / Math.sqrt((element00 + element01) * (element10 + element11) * (element00 + element10) * (element01 + element11));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double linearCorrCoeffProb(double rCoeff, int nu)
/*       */   {
/*  6749 */     return corrCoeffProb(rCoeff, nu);
/*       */   }
/*       */   
/*       */   public static double corrCoeffProb(double rCoeff, int nu)
/*       */   {
/*  6754 */     if (Math.abs(rCoeff) > 1.0D) { throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + rCoeff);
/*       */     }
/*       */     
/*  6757 */     CorrCoeff cc = new CorrCoeff();
/*       */     
/*       */ 
/*  6760 */     cc.a = ((nu - 2.0D) / 2.0D);
/*       */     
/*       */ 
/*  6763 */     double integral = Integration.gaussQuad(cc, Math.abs(rCoeff), 1.0D, 128);
/*       */     
/*  6765 */     double preterm = Math.exp(logGamma((nu + 1.0D) / 2.0D) - logGamma(nu / 2.0D)) / Math.sqrt(3.141592653589793D);
/*       */     
/*  6767 */     return preterm * integral;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double linearCorrCoeff(double rCoeff, int nu)
/*       */   {
/*  6773 */     return corrCoeffPDF(rCoeff, nu);
/*       */   }
/*       */   
/*       */   public static double corrCoeffPDF(double rCoeff, int nu)
/*       */   {
/*  6778 */     if (Math.abs(rCoeff) > 1.0D) { throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + rCoeff);
/*       */     }
/*  6780 */     double a = (nu - 2.0D) / 2.0D;
/*  6781 */     double y = Math.pow(1.0D - Fmath.square(rCoeff), a);
/*       */     
/*  6783 */     double preterm = Math.exp(logGamma((nu + 1.0D) / 2.0D) - logGamma(nu / 2.0D)) / Math.sqrt(3.141592653589793D);
/*       */     
/*  6785 */     return preterm * y;
/*       */   }
/*       */   
/*       */   public static double corrCoeffPdf(double rCoeff, int nu)
/*       */   {
/*  6790 */     if (Math.abs(rCoeff) > 1.0D) { throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + rCoeff);
/*       */     }
/*  6792 */     double a = (nu - 2.0D) / 2.0D;
/*  6793 */     double y = Math.pow(1.0D - Fmath.square(rCoeff), a);
/*       */     
/*  6795 */     double preterm = Math.exp(logGamma((nu + 1.0D) / 2.0D) - logGamma(nu / 2.0D)) / Math.sqrt(3.141592653589793D);
/*       */     
/*  6797 */     return preterm * y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double shannonEntropy(double[] p)
/*       */   {
/*  6803 */     ArrayMaths am = new ArrayMaths(p);
/*  6804 */     double max = am.getMaximum_as_double();
/*  6805 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  6806 */     double min = am.getMinimum_as_double();
/*  6807 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  6808 */     double total = am.getSum_as_double();
/*  6809 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) { throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*       */     }
/*  6811 */     return am.minusxLog2x().getSum_as_double();
/*       */   }
/*       */   
/*       */   public static double shannonEntropyBit(double[] p)
/*       */   {
/*  6816 */     return shannonEntropy(p);
/*       */   }
/*       */   
/*       */   public static double shannonEntropyNat(double[] p)
/*       */   {
/*  6821 */     ArrayMaths am = new ArrayMaths(p);
/*  6822 */     double max = am.getMaximum_as_double();
/*  6823 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  6824 */     double min = am.getMinimum_as_double();
/*  6825 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  6826 */     double total = am.getSum_as_double();
/*  6827 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) { throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*       */     }
/*  6829 */     return am.minusxLogEx().getSum_as_double();
/*       */   }
/*       */   
/*       */   public static double shannonEntropyDit(double[] p)
/*       */   {
/*  6834 */     ArrayMaths am = new ArrayMaths(p);
/*  6835 */     double max = am.getMaximum_as_double();
/*  6836 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  6837 */     double min = am.getMinimum_as_double();
/*  6838 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  6839 */     double total = am.getSum_as_double();
/*  6840 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) { throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*       */     }
/*  6842 */     return am.minusxLog10x().getSum_as_double();
/*       */   }
/*       */   
/*       */   public static double binaryShannonEntropy(double p)
/*       */   {
/*  6847 */     if (p > 1.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be less than or equal to 1");
/*  6848 */     if (p < 0.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be greater than or equal to 0");
/*  6849 */     double entropy = 0.0D;
/*  6850 */     if ((p > 0.0D) && (p < 1.0D)) {
/*  6851 */       entropy = -p * Fmath.log2(p) - (1.0D - p) * Fmath.log2(1.0D - p);
/*       */     }
/*  6853 */     return entropy;
/*       */   }
/*       */   
/*       */   public static double binaryShannonEntropyBit(double p)
/*       */   {
/*  6858 */     return binaryShannonEntropy(p);
/*       */   }
/*       */   
/*       */   public static double binaryShannonEntropyNat(double p)
/*       */   {
/*  6863 */     if (p > 1.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be less than or equal to 1");
/*  6864 */     if (p < 0.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be greater than or equal to 0");
/*  6865 */     double entropy = 0.0D;
/*  6866 */     if ((p > 0.0D) && (p < 1.0D)) {
/*  6867 */       entropy = -p * Math.log(p) - (1.0D - p) * Math.log(1.0D - p);
/*       */     }
/*  6869 */     return entropy;
/*       */   }
/*       */   
/*       */   public static double binaryShannonEntropyDit(double p)
/*       */   {
/*  6874 */     if (p > 1.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be less than or equal to 1");
/*  6875 */     if (p < 0.0D) throw new IllegalArgumentException("The probabiliy, " + p + ",  must be greater than or equal to 0");
/*  6876 */     double entropy = 0.0D;
/*  6877 */     if ((p > 0.0D) && (p < 1.0D)) {
/*  6878 */       entropy = -p * Math.log10(p) - (1.0D - p) * Math.log10(1.0D - p);
/*       */     }
/*  6880 */     return entropy;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double renyiEntropy(double[] p, double alpha)
/*       */   {
/*  6887 */     ArrayMaths am = new ArrayMaths(p);
/*  6888 */     double max = am.getMaximum_as_double();
/*  6889 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  6890 */     double min = am.getMinimum_as_double();
/*  6891 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  6892 */     double total = am.getSum_as_double();
/*  6893 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*  6894 */     if (alpha < 0.0D) throw new IllegalArgumentException("alpha, " + alpha + ", must be greater than or equal to 0");
/*  6895 */     double entropy = 0.0D;
/*  6896 */     if (alpha == 0.0D) {
/*  6897 */       entropy = Fmath.log2(p.length);
/*       */ 
/*       */     }
/*  6900 */     else if (alpha == 1.0D) {
/*  6901 */       entropy = shannonEntropy(p);
/*       */ 
/*       */     }
/*  6904 */     else if (Fmath.isPlusInfinity(alpha)) {
/*  6905 */       entropy = -Fmath.log2(max);
/*       */ 
/*       */     }
/*  6908 */     else if (alpha <= 3000.0D) {
/*  6909 */       am = am.pow(alpha);
/*  6910 */       boolean testUnderFlow = false;
/*  6911 */       if (am.getMaximum_as_double() == Double.MIN_VALUE) testUnderFlow = true;
/*  6912 */       entropy = Fmath.log2(am.getSum_as_double()) / (1.0D - alpha);
/*  6913 */       if ((Fmath.isPlusInfinity(entropy)) || (testUnderFlow)) {
/*  6914 */         entropy = -Fmath.log2(max);
/*  6915 */         double entropyMin = entropy;
/*  6916 */         System.out.println("Stat: renyiEntropy/renyiEntopyBit: underflow or overflow in calculating the entropy");
/*  6917 */         boolean test1 = true;
/*  6918 */         boolean test2 = true;
/*  6919 */         boolean test3 = true;
/*  6920 */         int iter = 0;
/*  6921 */         double alpha2 = alpha / 2.0D;
/*  6922 */         double entropy2 = 0.0D;
/*  6923 */         while (test3) {
/*  6924 */           while (test1) {
/*  6925 */             ArrayMaths am2 = new ArrayMaths(p);
/*  6926 */             am2 = am2.pow(alpha2);
/*  6927 */             entropy2 = Fmath.log2(am2.getSum_as_double()) / (1.0D - alpha2);
/*  6928 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  6929 */               alpha2 /= 2.0D;
/*  6930 */               iter++;
/*  6931 */               if (iter == 100000) {
/*  6932 */                 test1 = false;
/*  6933 */                 test2 = false;
/*       */               }
/*       */             }
/*       */             else {
/*  6937 */               test1 = false;
/*       */             }
/*       */           }
/*  6940 */           double alphaTest = alpha2 + 40.0D * alpha / 1000.0D;
/*  6941 */           ArrayMaths am3 = new ArrayMaths(p);
/*  6942 */           am3 = am3.pow(alphaTest);
/*  6943 */           double entropy3 = Fmath.log2(am3.getSum_as_double()) / (1.0D - alphaTest);
/*  6944 */           if (!Fmath.isPlusInfinity(entropy3)) {
/*  6945 */             test3 = false;
/*       */           }
/*       */           else {
/*  6948 */             alpha2 /= 2.0D;
/*       */           }
/*       */         }
/*  6951 */         double entropyLast = entropy2;
/*  6952 */         double alphaLast = alpha2;
/*  6953 */         ArrayList<Double> extrap = new ArrayList();
/*  6954 */         if (test2) {
/*  6955 */           double diff = alpha2 / 1000.0D;
/*  6956 */           test1 = true;
/*  6957 */           while (test1) {
/*  6958 */             extrap.add(new Double(alpha2));
/*  6959 */             extrap.add(new Double(entropy2));
/*  6960 */             entropyLast = entropy2;
/*  6961 */             alphaLast = alpha2;
/*  6962 */             alpha2 += diff;
/*  6963 */             ArrayMaths am2 = new ArrayMaths(p);
/*  6964 */             am2 = am2.pow(alpha2);
/*  6965 */             entropy2 = Fmath.log2(am2.getSum_as_double()) / (1.0D - alpha2);
/*  6966 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  6967 */               test1 = false;
/*  6968 */               entropy2 = entropyLast;
/*  6969 */               alpha2 = alphaLast;
/*       */             }
/*       */           }
/*       */         }
/*  6973 */         int nex = extrap.size() / 2 - 20;
/*  6974 */         double[] alphaex = new double[nex];
/*  6975 */         double[] entroex = new double[nex];
/*  6976 */         int ii = -1;
/*  6977 */         for (int i = 0; i < nex; i++) {
/*  6978 */           alphaex[i] = ((Double)extrap.get(++ii)).doubleValue();
/*  6979 */           entroex[i] = Math.log(((Double)extrap.get(++ii)).doubleValue() - entropyMin);
/*       */         }
/*  6981 */         Regression reg = new Regression(alphaex, entroex);
/*  6982 */         reg.linear();
/*  6983 */         double[] param = reg.getCoeff();
/*  6984 */         entropy = Math.exp(param[0] + param[1] * alpha) + entropyMin;
/*       */         
/*       */ 
/*  6987 */         System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*  6988 */         System.out.println("Lowest calculable value =  " + (Math.exp(entroex[(nex - 1)]) + entropyMin) + ", alpha = " + alphaex[(nex - 1)]);
/*  6989 */         System.out.println("Minimum entropy value =  " + entropyMin + ", alpha = infinity");
/*       */       }
/*       */     }
/*       */     else {
/*  6993 */       entropy = -Fmath.log2(max);
/*  6994 */       System.out.println("Stat: renyiEntropy/renyiEntropyBit: underflow or overflow in calculating the entropy");
/*  6995 */       System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7000 */     return entropy;
/*       */   }
/*       */   
/*       */   public static double renyiEntropyNat(double[] p, double alpha)
/*       */   {
/*  7005 */     ArrayMaths am = new ArrayMaths(p);
/*  7006 */     double max = am.getMaximum_as_double();
/*  7007 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  7008 */     double min = am.getMinimum_as_double();
/*  7009 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  7010 */     double total = am.getSum_as_double();
/*  7011 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*  7012 */     if (alpha < 0.0D) throw new IllegalArgumentException("alpha, " + alpha + ", must be greater than or equal to 0");
/*  7013 */     double entropy = 0.0D;
/*  7014 */     if (alpha == 0.0D) {
/*  7015 */       entropy = Math.log(p.length);
/*       */ 
/*       */     }
/*  7018 */     else if (alpha == 1.0D) {
/*  7019 */       entropy = shannonEntropy(p);
/*       */ 
/*       */     }
/*  7022 */     else if (Fmath.isPlusInfinity(alpha)) {
/*  7023 */       entropy = -Math.log(max);
/*       */ 
/*       */     }
/*  7026 */     else if (alpha <= 3000.0D) {
/*  7027 */       am = am.pow(alpha);
/*  7028 */       boolean testUnderFlow = false;
/*  7029 */       if (am.getMaximum_as_double() == Double.MIN_VALUE) testUnderFlow = true;
/*  7030 */       entropy = Math.log(am.getSum_as_double()) / (1.0D - alpha);
/*  7031 */       if ((Fmath.isPlusInfinity(entropy)) || (testUnderFlow)) {
/*  7032 */         entropy = -Math.log(max);
/*  7033 */         double entropyMin = entropy;
/*  7034 */         System.out.println("Stat: renyiEntropyNat: underflow or overflow in calculating the entropy");
/*  7035 */         boolean test1 = true;
/*  7036 */         boolean test2 = true;
/*  7037 */         boolean test3 = true;
/*  7038 */         int iter = 0;
/*  7039 */         double alpha2 = alpha / 2.0D;
/*  7040 */         double entropy2 = 0.0D;
/*  7041 */         while (test3) {
/*  7042 */           while (test1) {
/*  7043 */             ArrayMaths am2 = new ArrayMaths(p);
/*  7044 */             am2 = am2.pow(alpha2);
/*  7045 */             entropy2 = Math.log(am2.getSum_as_double()) / (1.0D - alpha2);
/*  7046 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  7047 */               alpha2 /= 2.0D;
/*  7048 */               iter++;
/*  7049 */               if (iter == 100000) {
/*  7050 */                 test1 = false;
/*  7051 */                 test2 = false;
/*       */               }
/*       */             }
/*       */             else {
/*  7055 */               test1 = false;
/*       */             }
/*       */           }
/*  7058 */           double alphaTest = alpha2 + 40.0D * alpha / 1000.0D;
/*  7059 */           ArrayMaths am3 = new ArrayMaths(p);
/*  7060 */           am3 = am3.pow(alphaTest);
/*  7061 */           double entropy3 = Math.log(am3.getSum_as_double()) / (1.0D - alphaTest);
/*  7062 */           if (!Fmath.isPlusInfinity(entropy3)) {
/*  7063 */             test3 = false;
/*       */           }
/*       */           else {
/*  7066 */             alpha2 /= 2.0D;
/*       */           }
/*       */         }
/*  7069 */         double entropyLast = entropy2;
/*  7070 */         double alphaLast = alpha2;
/*  7071 */         ArrayList<Double> extrap = new ArrayList();
/*  7072 */         if (test2) {
/*  7073 */           double diff = alpha2 / 1000.0D;
/*  7074 */           test1 = true;
/*  7075 */           while (test1) {
/*  7076 */             extrap.add(new Double(alpha2));
/*  7077 */             extrap.add(new Double(entropy2));
/*  7078 */             entropyLast = entropy2;
/*  7079 */             alphaLast = alpha2;
/*  7080 */             alpha2 += diff;
/*  7081 */             ArrayMaths am2 = new ArrayMaths(p);
/*  7082 */             am2 = am2.pow(alpha2);
/*  7083 */             entropy2 = Math.log(am2.getSum_as_double()) / (1.0D - alpha2);
/*  7084 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  7085 */               test1 = false;
/*  7086 */               entropy2 = entropyLast;
/*  7087 */               alpha2 = alphaLast;
/*       */             }
/*       */           }
/*       */         }
/*  7091 */         int nex = extrap.size() / 2 - 20;
/*  7092 */         double[] alphaex = new double[nex];
/*  7093 */         double[] entroex = new double[nex];
/*  7094 */         int ii = -1;
/*  7095 */         for (int i = 0; i < nex; i++) {
/*  7096 */           alphaex[i] = ((Double)extrap.get(++ii)).doubleValue();
/*  7097 */           entroex[i] = Math.log(((Double)extrap.get(++ii)).doubleValue() - entropyMin);
/*       */         }
/*  7099 */         Regression reg = new Regression(alphaex, entroex);
/*  7100 */         reg.linear();
/*  7101 */         double[] param = reg.getCoeff();
/*  7102 */         entropy = Math.exp(param[0] + param[1] * alpha) + entropyMin;
/*       */         
/*       */ 
/*  7105 */         System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*  7106 */         System.out.println("Lowest calculable value =  " + (Math.exp(entroex[(nex - 1)]) + entropyMin) + ", alpha = " + alphaex[(nex - 1)]);
/*  7107 */         System.out.println("Minimum entropy value =  " + entropyMin + ", alpha = infinity");
/*       */       }
/*       */     }
/*       */     else {
/*  7111 */       entropy = -Math.log(max);
/*  7112 */       System.out.println("Stat: renyiEntropyNat: underflow or overflow in calculating the entropy");
/*  7113 */       System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7118 */     return entropy;
/*       */   }
/*       */   
/*       */   public static double renyiEntropyDit(double[] p, double alpha)
/*       */   {
/*  7123 */     ArrayMaths am = new ArrayMaths(p);
/*  7124 */     double max = am.getMaximum_as_double();
/*  7125 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  7126 */     double min = am.getMinimum_as_double();
/*  7127 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  7128 */     double total = am.getSum_as_double();
/*  7129 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*  7130 */     if (alpha < 0.0D) throw new IllegalArgumentException("alpha, " + alpha + ", must be greater than or equal to 0");
/*  7131 */     double entropy = 0.0D;
/*  7132 */     if (alpha == 0.0D) {
/*  7133 */       entropy = Math.log10(p.length);
/*       */ 
/*       */     }
/*  7136 */     else if (alpha == 1.0D) {
/*  7137 */       entropy = shannonEntropy(p);
/*       */ 
/*       */     }
/*  7140 */     else if (Fmath.isPlusInfinity(alpha)) {
/*  7141 */       entropy = -Math.log10(max);
/*       */ 
/*       */     }
/*  7144 */     else if (alpha <= 3000.0D) {
/*  7145 */       am = am.pow(alpha);
/*  7146 */       boolean testUnderFlow = false;
/*  7147 */       if (am.getMaximum_as_double() == Double.MIN_VALUE) testUnderFlow = true;
/*  7148 */       entropy = Math.log10(am.getSum_as_double()) / (1.0D - alpha);
/*  7149 */       if ((Fmath.isPlusInfinity(entropy)) || (testUnderFlow)) {
/*  7150 */         entropy = -Math.log10(max);
/*  7151 */         double entropyMin = entropy;
/*  7152 */         System.out.println("Stat: renyiEntropyDit: underflow or overflow in calculating the entropy");
/*  7153 */         boolean test1 = true;
/*  7154 */         boolean test2 = true;
/*  7155 */         boolean test3 = true;
/*  7156 */         int iter = 0;
/*  7157 */         double alpha2 = alpha / 2.0D;
/*  7158 */         double entropy2 = 0.0D;
/*  7159 */         while (test3) {
/*  7160 */           while (test1) {
/*  7161 */             ArrayMaths am2 = new ArrayMaths(p);
/*  7162 */             am2 = am2.pow(alpha2);
/*  7163 */             entropy2 = Math.log10(am2.getSum_as_double()) / (1.0D - alpha2);
/*  7164 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  7165 */               alpha2 /= 2.0D;
/*  7166 */               iter++;
/*  7167 */               if (iter == 100000) {
/*  7168 */                 test1 = false;
/*  7169 */                 test2 = false;
/*       */               }
/*       */             }
/*       */             else {
/*  7173 */               test1 = false;
/*       */             }
/*       */           }
/*  7176 */           double alphaTest = alpha2 + 40.0D * alpha / 1000.0D;
/*  7177 */           ArrayMaths am3 = new ArrayMaths(p);
/*  7178 */           am3 = am3.pow(alphaTest);
/*  7179 */           double entropy3 = Math.log10(am3.getSum_as_double()) / (1.0D - alphaTest);
/*  7180 */           if (!Fmath.isPlusInfinity(entropy3)) {
/*  7181 */             test3 = false;
/*       */           }
/*       */           else {
/*  7184 */             alpha2 /= 2.0D;
/*       */           }
/*       */         }
/*  7187 */         double entropyLast = entropy2;
/*  7188 */         double alphaLast = alpha2;
/*  7189 */         ArrayList<Double> extrap = new ArrayList();
/*  7190 */         if (test2) {
/*  7191 */           double diff = alpha2 / 1000.0D;
/*  7192 */           test1 = true;
/*  7193 */           while (test1) {
/*  7194 */             extrap.add(new Double(alpha2));
/*  7195 */             extrap.add(new Double(entropy2));
/*  7196 */             entropyLast = entropy2;
/*  7197 */             alphaLast = alpha2;
/*  7198 */             alpha2 += diff;
/*  7199 */             ArrayMaths am2 = new ArrayMaths(p);
/*  7200 */             am2 = am2.pow(alpha2);
/*  7201 */             entropy2 = Math.log10(am2.getSum_as_double()) / (1.0D - alpha2);
/*  7202 */             if (Fmath.isPlusInfinity(entropy2)) {
/*  7203 */               test1 = false;
/*  7204 */               entropy2 = entropyLast;
/*  7205 */               alpha2 = alphaLast;
/*       */             }
/*       */           }
/*       */         }
/*  7209 */         int nex = extrap.size() / 2 - 20;
/*  7210 */         double[] alphaex = new double[nex];
/*  7211 */         double[] entroex = new double[nex];
/*  7212 */         int ii = -1;
/*  7213 */         for (int i = 0; i < nex; i++) {
/*  7214 */           alphaex[i] = ((Double)extrap.get(++ii)).doubleValue();
/*  7215 */           entroex[i] = Math.log10(((Double)extrap.get(++ii)).doubleValue() - entropyMin);
/*       */         }
/*  7217 */         Regression reg = new Regression(alphaex, entroex);
/*  7218 */         reg.linear();
/*  7219 */         double[] param = reg.getCoeff();
/*  7220 */         entropy = Math.exp(param[0] + param[1] * alpha) + entropyMin;
/*       */         
/*       */ 
/*  7223 */         System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*  7224 */         System.out.println("Lowest calculable value =  " + (Math.exp(entroex[(nex - 1)]) + entropyMin) + ", alpha = " + alphaex[(nex - 1)]);
/*  7225 */         System.out.println("Minimum entropy value =  " + entropyMin + ", alpha = infinity");
/*       */       }
/*       */     }
/*       */     else {
/*  7229 */       entropy = -Math.log10(max);
/*  7230 */       System.out.println("Stat: renyiEntropyDit: underflow or overflow in calculating the entropy");
/*  7231 */       System.out.println("An interpolated entropy of " + entropy + " returned (see documentation for exponential interpolation)");
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7236 */     return entropy;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double renyiEntropyBit(double[] p, double alpha)
/*       */   {
/*  7243 */     return renyiEntropy(p, alpha);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double tsallisEntropyNat(double[] p, double q)
/*       */   {
/*  7250 */     ArrayMaths am = new ArrayMaths(p);
/*  7251 */     double max = am.getMaximum_as_double();
/*  7252 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  7253 */     double min = am.getMinimum_as_double();
/*  7254 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  7255 */     double total = am.getSum_as_double();
/*  7256 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) { throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*       */     }
/*  7258 */     if (q == 1.0D) {
/*  7259 */       return shannonEntropyNat(p);
/*       */     }
/*       */     
/*  7262 */     am = am.pow(q);
/*  7263 */     return (1.0D - am.getSum_as_double()) / (q - 1.0D);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double generalizedEntropyOneNat(double[] p, double q, double r)
/*       */   {
/*  7270 */     ArrayMaths am = new ArrayMaths(p);
/*  7271 */     double max = am.getMaximum_as_double();
/*  7272 */     if (max > 1.0D) throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + max);
/*  7273 */     double min = am.getMinimum_as_double();
/*  7274 */     if (min < 0.0D) throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + min);
/*  7275 */     double total = am.getSum_as_double();
/*  7276 */     if (!Fmath.isEqualWithinPerCent(total, 1.0D, 0.1D)) throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + total);
/*  7277 */     if (r == 0.0D) {
/*  7278 */       return renyiEntropyNat(p, q);
/*       */     }
/*       */     
/*  7281 */     if (r == 1.0D) {
/*  7282 */       return tsallisEntropyNat(p, q);
/*       */     }
/*       */     
/*  7285 */     if (q == 1.0D) {
/*  7286 */       double[] tsen = new double[10];
/*  7287 */       double[] tsqq = new double[10];
/*  7288 */       double qq = 0.995D;
/*  7289 */       for (int i = 0; i < 5; i++) {
/*  7290 */         ArrayMaths am1 = am.pow(qq);
/*  7291 */         tsen[i] = ((1.0D - Math.pow(am1.getSum_as_double(), r)) / (r * (qq - 1.0D)));
/*  7292 */         tsqq[i] = qq;
/*  7293 */         qq += 0.001D;
/*       */       }
/*  7295 */       qq = 1.001D;
/*  7296 */       for (int i = 5; i < 10; i++) {
/*  7297 */         ArrayMaths am1 = am.pow(qq);
/*  7298 */         tsen[i] = ((1.0D - Math.pow(am1.getSum_as_double(), r)) / (r * (qq - 1.0D)));
/*  7299 */         tsqq[i] = qq;
/*  7300 */         qq += 0.001D;
/*       */       }
/*  7302 */       Regression reg = new Regression(tsqq, tsen);
/*  7303 */       reg.polynomial(2);
/*  7304 */       double[] param = reg.getCoeff();
/*  7305 */       return param[0] + param[1] + param[2];
/*       */     }
/*       */     
/*  7308 */     am = am.pow(q);
/*  7309 */     return (1.0D - Math.pow(am.getSum_as_double(), r)) / (r * (q - 1.0D));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double generalisedEntropyOneNat(double[] p, double q, double r)
/*       */   {
/*  7316 */     return generalizedEntropyOneNat(p, q, r);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[][] histogramBins(double[] data, double binWidth, double binZero, double binUpper)
/*       */   {
/*  7325 */     int n = 0;
/*  7326 */     int m = data.length;
/*  7327 */     for (int i = 0; i < m; i++) if (data[i] <= binUpper) n++;
/*  7328 */     if (n != m) {
/*  7329 */       double[] newData = new double[n];
/*  7330 */       int j = 0;
/*  7331 */       for (int i = 0; i < m; i++) {
/*  7332 */         if (data[i] <= binUpper) {
/*  7333 */           newData[j] = data[i];
/*  7334 */           j++;
/*       */         }
/*       */       }
/*  7337 */       System.out.println(m - n + " data points, above histogram upper limit, excluded in Stat.histogramBins");
/*  7338 */       return histogramBins(newData, binWidth, binZero);
/*       */     }
/*       */     
/*  7341 */     return histogramBins(data, binWidth, binZero);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[][] histogramBins(double[] data, double binWidth, double binZero)
/*       */   {
/*  7349 */     double dmax = Fmath.maximum(data);
/*  7350 */     int nBins = (int)Math.ceil((dmax - binZero) / binWidth);
/*  7351 */     if (binZero + nBins * binWidth > dmax) nBins++;
/*  7352 */     int nPoints = data.length;
/*  7353 */     int[] dataCheck = new int[nPoints];
/*  7354 */     for (int i = 0; i < nPoints; i++) dataCheck[i] = 0;
/*  7355 */     double[] binWall = new double[nBins + 1];
/*  7356 */     binWall[0] = binZero;
/*  7357 */     for (int i = 1; i <= nBins; i++) {
/*  7358 */       binWall[i] = (binWall[(i - 1)] + binWidth);
/*       */     }
/*  7360 */     double[][] binFreq = new double[2][nBins];
/*  7361 */     for (int i = 0; i < nBins; i++) {
/*  7362 */       binFreq[0][i] = ((binWall[i] + binWall[(i + 1)]) / 2.0D);
/*  7363 */       binFreq[1][i] = 0.0D;
/*       */     }
/*  7365 */     boolean test = true;
/*       */     
/*  7367 */     for (int i = 0; i < nPoints; i++) {
/*  7368 */       test = true;
/*  7369 */       int j = 0;
/*  7370 */       while (test) {
/*  7371 */         if (j == nBins - 1) {
/*  7372 */           if ((data[i] >= binWall[j]) && (data[i] <= binWall[(j + 1)] * (1.0D + histTol))) {
/*  7373 */             binFreq[1][j] += 1.0D;
/*  7374 */             dataCheck[i] = 1;
/*  7375 */             test = false;
/*       */           }
/*       */           
/*       */         }
/*  7379 */         else if ((data[i] >= binWall[j]) && (data[i] < binWall[(j + 1)])) {
/*  7380 */           binFreq[1][j] += 1.0D;
/*  7381 */           dataCheck[i] = 1;
/*  7382 */           test = false;
/*       */         }
/*       */         
/*  7385 */         if (test) {
/*  7386 */           if (j == nBins - 1) {
/*  7387 */             test = false;
/*       */           }
/*       */           else {
/*  7390 */             j++;
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/*  7395 */     int nMissed = 0;
/*  7396 */     for (int i = 0; i < nPoints; i++) if (dataCheck[i] == 0) {
/*  7397 */         nMissed++;
/*  7398 */         System.out.println("p " + i + " " + data[i] + " " + binWall[0] + " " + binWall[nBins]);
/*       */       }
/*  7400 */     if (nMissed > 0) System.out.println(nMissed + " data points, outside histogram limits, excluded in Stat.histogramBins");
/*  7401 */     return binFreq;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double[][] histogramBins(double[] data, double binWidth)
/*       */   {
/*  7408 */     double dmin = Fmath.minimum(data);
/*  7409 */     double dmax = Fmath.maximum(data);
/*  7410 */     double span = dmax - dmin;
/*  7411 */     double binZero = dmin;
/*  7412 */     int nBins = (int)Math.ceil(span / binWidth);
/*  7413 */     double histoSpan = nBins * binWidth;
/*  7414 */     double rem = histoSpan - span;
/*  7415 */     if (rem >= 0.0D) {
/*  7416 */       binZero -= rem / 2.0D;
/*       */ 
/*       */     }
/*  7419 */     else if (Math.abs(rem) / span > histTol)
/*       */     {
/*  7421 */       boolean testBw = true;
/*  7422 */       double incr = histTol / nBins;
/*  7423 */       int iTest = 0;
/*  7424 */       while (testBw) {
/*  7425 */         binWidth += incr;
/*  7426 */         histoSpan = nBins * binWidth;
/*  7427 */         rem = histoSpan - span;
/*  7428 */         if (rem < 0.0D) {
/*  7429 */           iTest++;
/*  7430 */           if (iTest > 1000) {
/*  7431 */             testBw = false;
/*  7432 */             System.out.println("histogram method could not encompass all data within histogram\nContact Michael thomas Flanagan");
/*       */           }
/*       */         }
/*       */         else {
/*  7436 */           testBw = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  7442 */     return histogramBins(data, binWidth, binZero);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth, double binZero, double binUpper)
/*       */   {
/*  7448 */     String xLegend = null;
/*  7449 */     return histogramBinsPlot(data, binWidth, binZero, binUpper, xLegend);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth, double binZero, double binUpper, String xLegend)
/*       */   {
/*  7455 */     int n = 0;
/*  7456 */     int m = data.length;
/*  7457 */     for (int i = 0; i < m; i++) if (data[i] <= binUpper) n++;
/*  7458 */     if (n != m) {
/*  7459 */       double[] newData = new double[n];
/*  7460 */       int j = 0;
/*  7461 */       for (int i = 0; i < m; i++) {
/*  7462 */         if (data[i] <= binUpper) {
/*  7463 */           newData[j] = data[i];
/*  7464 */           j++;
/*       */         }
/*       */       }
/*  7467 */       System.out.println(m - n + " data points, above histogram upper limit, excluded in Stat.histogramBins");
/*  7468 */       return histogramBinsPlot(newData, binWidth, binZero, xLegend);
/*       */     }
/*       */     
/*  7471 */     return histogramBinsPlot(data, binWidth, binZero, xLegend);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth, double binZero)
/*       */   {
/*  7479 */     String xLegend = null;
/*  7480 */     return histogramBinsPlot(data, binWidth, binZero, xLegend);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth, double binZero, String xLegend)
/*       */   {
/*  7486 */     double[][] results = histogramBins(data, binWidth, binZero);
/*  7487 */     int nBins = results[0].length;
/*  7488 */     int nPoints = nBins * 3 + 1;
/*  7489 */     double[][] cdata = PlotGraph.data(1, nPoints);
/*  7490 */     cdata[0][0] = binZero;
/*  7491 */     cdata[1][0] = 0.0D;
/*  7492 */     int k = 1;
/*  7493 */     for (int i = 0; i < nBins; i++) {
/*  7494 */       cdata[0][k] = cdata[0][(k - 1)];
/*  7495 */       cdata[1][k] = results[1][i];
/*  7496 */       k++;
/*  7497 */       cdata[0][k] = (cdata[0][(k - 1)] + binWidth);
/*  7498 */       cdata[1][k] = results[1][i];
/*  7499 */       k++;
/*  7500 */       cdata[0][k] = cdata[0][(k - 1)];
/*  7501 */       cdata[1][k] = 0.0D;
/*  7502 */       k++;
/*       */     }
/*       */     
/*  7505 */     PlotGraph pg = new PlotGraph(cdata);
/*  7506 */     pg.setGraphTitle("Histogram:  Bin Width = " + binWidth);
/*  7507 */     pg.setLine(3);
/*  7508 */     pg.setPoint(0);
/*  7509 */     pg.setYaxisLegend("Frequency");
/*  7510 */     if (xLegend != null) pg.setXaxisLegend(xLegend);
/*  7511 */     pg.plot();
/*       */     
/*  7513 */     return results;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth)
/*       */   {
/*  7519 */     String xLegend = null;
/*  7520 */     return histogramBinsPlot(data, binWidth, xLegend);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[][] histogramBinsPlot(double[] data, double binWidth, String xLegend)
/*       */   {
/*  7526 */     double dmin = Fmath.minimum(data);
/*  7527 */     double dmax = Fmath.maximum(data);
/*  7528 */     double span = dmax - dmin;
/*  7529 */     int nBins = (int)Math.ceil(span / binWidth);
/*  7530 */     double rem = nBins * binWidth - span;
/*  7531 */     double binZero = dmin - rem / 2.0D;
/*  7532 */     return histogramBinsPlot(data, binWidth, binZero, xLegend);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double[] uniformOrderStatisticMedians(int n)
/*       */   {
/*  7539 */     double nn = n;
/*  7540 */     double[] uosm = new double[n];
/*  7541 */     uosm[(n - 1)] = Math.pow(0.5D, 1.0D / nn);
/*  7542 */     uosm[0] = (1.0D - uosm[(n - 1)]);
/*  7543 */     for (int i = 1; i < n - 1; i++) {
/*  7544 */       uosm[i] = ((i + 1 - 0.3175D) / (nn + 0.365D));
/*       */     }
/*  7546 */     return uosm;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gammaCDF(double mu, double beta, double gamma, double upperLimit)
/*       */   {
/*  7556 */     if (upperLimit < mu) throw new IllegalArgumentException("The upper limit, " + upperLimit + "must be equal to or greater than the location parameter, " + mu);
/*  7557 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7558 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7559 */     double xx = (upperLimit - mu) / beta;
/*  7560 */     return regularisedGammaFunction(gamma, xx);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaCDF(double gamma, double upperLimit)
/*       */   {
/*  7566 */     if (upperLimit < 0.0D) throw new IllegalArgumentException("The upper limit, " + upperLimit + "must be equal to or greater than zero");
/*  7567 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7568 */     return regularisedGammaFunction(gamma, upperLimit);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaPDF(double mu, double beta, double gamma, double x)
/*       */   {
/*  7574 */     if (x < mu) throw new IllegalArgumentException("The variable, x, " + x + "must be equal to or greater than the location parameter, " + mu);
/*  7575 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7576 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7577 */     double xx = (x - mu) / beta;
/*  7578 */     return Math.pow(xx, gamma - 1.0D) * Math.exp(-xx) / (beta * gammaFunction(gamma));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaPDF(double gamma, double x)
/*       */   {
/*  7584 */     if (x < 0.0D) throw new IllegalArgumentException("The variable, x, " + x + "must be equal to or greater than zero");
/*  7585 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7586 */     return Math.pow(x, gamma - 1.0D) * Math.exp(-x) / gammaFunction(gamma);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaMean(double mu, double beta, double gamma)
/*       */   {
/*  7592 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7593 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7594 */     return gamma * beta - mu;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaMode(double mu, double beta, double gamma)
/*       */   {
/*  7600 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7601 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7602 */     double mode = NaN.0D;
/*  7603 */     if (gamma >= 1.0D) mode = (gamma - 1.0D) * beta - mu;
/*  7604 */     return mode;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gammaStandardDeviation(double mu, double beta, double gamma)
/*       */   {
/*  7610 */     return gammaStandDev(mu, beta, gamma);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double gammaStandDev(double mu, double beta, double gamma)
/*       */   {
/*  7617 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7618 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7619 */     return Math.sqrt(gamma) * beta;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gammaRand(double mu, double beta, double gamma, int n)
/*       */   {
/*  7625 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7626 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7627 */     PsRandom psr = new PsRandom();
/*  7628 */     return psr.gammaArray(mu, beta, gamma, n);
/*       */   }
/*       */   
/*       */   public static double[] gammaRand(double mu, double beta, double gamma, int n, long seed)
/*       */   {
/*  7633 */     if (beta <= 0.0D) throw new IllegalArgumentException("The scale parameter, " + beta + "must be greater than zero");
/*  7634 */     if (gamma <= 0.0D) throw new IllegalArgumentException("The shape parameter, " + gamma + "must be greater than zero");
/*  7635 */     PsRandom psr = new PsRandom(seed);
/*  7636 */     return psr.gammaArray(mu, beta, gamma, n);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double gammaFunction(double x)
/*       */   {
/*  7643 */     double xcopy = x;
/*  7644 */     double first = x + lgfGamma + 0.5D;
/*  7645 */     double second = lgfCoeff[0];
/*  7646 */     double fg = 0.0D;
/*       */     
/*  7648 */     if (x >= 0.0D) {
/*  7649 */       if ((x >= 1.0D) && (x - (int)x == 0.0D)) {
/*  7650 */         fg = factorial(x) / x;
/*       */       }
/*       */       else {
/*  7653 */         first = Math.pow(first, x + 0.5D) * Math.exp(-first);
/*  7654 */         for (int i = 1; i <= lgfN; i++) second += lgfCoeff[i] / ++xcopy;
/*  7655 */         fg = first * Math.sqrt(6.283185307179586D) * second / x;
/*       */       }
/*       */     }
/*       */     else {
/*  7659 */       fg = -3.141592653589793D / (x * gamma(-x) * Math.sin(3.141592653589793D * x));
/*       */     }
/*  7661 */     return fg;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gamma(double x)
/*       */   {
/*  7669 */     double xcopy = x;
/*  7670 */     double first = x + lgfGamma + 0.5D;
/*  7671 */     double second = lgfCoeff[0];
/*  7672 */     double fg = 0.0D;
/*       */     
/*  7674 */     if (x >= 0.0D) {
/*  7675 */       if ((x >= 1.0D) && (x - (int)x == 0.0D)) {
/*  7676 */         fg = factorial(x) / x;
/*       */       }
/*       */       else {
/*  7679 */         first = Math.pow(first, x + 0.5D) * Math.exp(-first);
/*  7680 */         for (int i = 1; i <= lgfN; i++) second += lgfCoeff[i] / ++xcopy;
/*  7681 */         fg = first * Math.sqrt(6.283185307179586D) * second / x;
/*       */       }
/*       */     }
/*       */     else {
/*  7685 */       fg = -3.141592653589793D / (x * gamma(-x) * Math.sin(3.141592653589793D * x));
/*       */     }
/*  7687 */     return fg;
/*       */   }
/*       */   
/*       */   public static double getLanczosGamma()
/*       */   {
/*  7692 */     return lgfGamma;
/*       */   }
/*       */   
/*       */   public static int getLanczosN()
/*       */   {
/*  7697 */     return lgfN;
/*       */   }
/*       */   
/*       */   public static double[] getLanczosCoeff()
/*       */   {
/*  7702 */     int n = getLanczosN() + 1;
/*  7703 */     double[] coef = new double[n];
/*  7704 */     for (int i = 0; i < n; i++) {
/*  7705 */       coef[i] = lgfCoeff[i];
/*       */     }
/*  7707 */     return coef;
/*       */   }
/*       */   
/*       */   public static double getFpmin()
/*       */   {
/*  7712 */     return 1.0E-300D;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double logGammaFunction(double x)
/*       */   {
/*  7718 */     double xcopy = x;
/*  7719 */     double fg = 0.0D;
/*  7720 */     double first = x + lgfGamma + 0.5D;
/*  7721 */     double second = lgfCoeff[0];
/*       */     
/*  7723 */     if (x >= 0.0D) {
/*  7724 */       if ((x >= 1.0D) && (x - (int)x == 0.0D)) {
/*  7725 */         fg = logFactorial(x) - Math.log(x);
/*       */       }
/*       */       else {
/*  7728 */         first -= (x + 0.5D) * Math.log(first);
/*  7729 */         for (int i = 1; i <= lgfN; i++) second += lgfCoeff[i] / ++xcopy;
/*  7730 */         fg = Math.log(Math.sqrt(6.283185307179586D) * second / x) - first;
/*       */       }
/*       */     }
/*       */     else {
/*  7734 */       fg = 3.141592653589793D / (gamma(1.0D - x) * Math.sin(3.141592653589793D * x));
/*       */       
/*  7736 */       if ((fg != Double.POSITIVE_INFINITY) && (fg != Double.NEGATIVE_INFINITY)) {
/*  7737 */         if (fg < 0.0D) {
/*  7738 */           throw new IllegalArgumentException("\nThe gamma function is negative");
/*       */         }
/*       */         
/*  7741 */         fg = Math.log(fg);
/*       */       }
/*       */     }
/*       */     
/*  7745 */     return fg;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double logGamma(double x)
/*       */   {
/*  7752 */     double xcopy = x;
/*  7753 */     double fg = 0.0D;
/*  7754 */     double first = x + lgfGamma + 0.5D;
/*  7755 */     double second = lgfCoeff[0];
/*       */     
/*  7757 */     if (x >= 0.0D) {
/*  7758 */       if ((x >= 1.0D) && (x - (int)x == 0.0D)) {
/*  7759 */         fg = logFactorial(x) - Math.log(x);
/*       */       }
/*       */       else {
/*  7762 */         first -= (x + 0.5D) * Math.log(first);
/*  7763 */         for (int i = 1; i <= lgfN; i++) second += lgfCoeff[i] / ++xcopy;
/*  7764 */         fg = Math.log(Math.sqrt(6.283185307179586D) * second / x) - first;
/*       */       }
/*       */     }
/*       */     else {
/*  7768 */       fg = 3.141592653589793D / (gamma(1.0D - x) * Math.sin(3.141592653589793D * x));
/*       */       
/*  7770 */       if ((fg != Double.POSITIVE_INFINITY) && (fg != Double.NEGATIVE_INFINITY)) {
/*  7771 */         if (fg < 0.0D) {
/*  7772 */           throw new IllegalArgumentException("\nThe gamma function is negative");
/*       */         }
/*       */         
/*  7775 */         fg = Math.log(fg);
/*       */       }
/*       */     }
/*       */     
/*  7779 */     return fg;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double regularisedGammaFunction(double a, double x)
/*       */   {
/*  7786 */     if ((a < 0.0D) || (x < 0.0D)) throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
/*  7787 */     double igf = 0.0D;
/*       */     
/*  7789 */     if (x < a + 1.0D)
/*       */     {
/*  7791 */       igf = incompleteGammaSer(a, x);
/*       */     }
/*       */     else
/*       */     {
/*  7795 */       igf = incompleteGammaFract(a, x);
/*       */     }
/*  7797 */     return igf;
/*       */   }
/*       */   
/*       */   public static double regularizedGammaFunction(double a, double x)
/*       */   {
/*  7802 */     return regularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double regIncompleteGamma(double a, double x)
/*       */   {
/*  7808 */     return regularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double incompleteGamma(double a, double x)
/*       */   {
/*  7814 */     return regularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */   public static double complementaryRegularisedGammaFunction(double a, double x)
/*       */   {
/*  7819 */     if ((a < 0.0D) || (x < 0.0D)) throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
/*  7820 */     double igf = 0.0D;
/*       */     
/*  7822 */     if (x != 0.0D) {
/*  7823 */       if (x == Double.POSITIVE_INFINITY)
/*       */       {
/*  7825 */         igf = 1.0D;
/*       */ 
/*       */       }
/*  7828 */       else if (x < a + 1.0D)
/*       */       {
/*  7830 */         igf = 1.0D - incompleteGammaSer(a, x);
/*       */       }
/*       */       else
/*       */       {
/*  7834 */         igf = 1.0D - incompleteGammaFract(a, x);
/*       */       }
/*       */     }
/*       */     
/*  7838 */     return igf;
/*       */   }
/*       */   
/*       */   public static double complementaryRegularizedGammaFunction(double a, double x)
/*       */   {
/*  7843 */     return complementaryRegularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double incompleteGammaComplementary(double a, double x)
/*       */   {
/*  7849 */     return complementaryRegularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double regIncompleteGammaComplementary(double a, double x)
/*       */   {
/*  7855 */     return complementaryRegularisedGammaFunction(a, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double incompleteGammaSer(double a, double x)
/*       */   {
/*  7861 */     if ((a < 0.0D) || (x < 0.0D)) throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
/*  7862 */     if (x >= a + 1.0D) { throw new IllegalArgumentException("\nx >= a+1   use Continued Fraction Representation");
/*       */     }
/*  7864 */     int i = 0;
/*  7865 */     double igf = 0.0D;
/*  7866 */     boolean check = true;
/*       */     
/*  7868 */     double acopy = a;
/*  7869 */     double sum = 1.0D / a;
/*  7870 */     double incr = sum;
/*  7871 */     double loggamma = logGamma(a);
/*       */     
/*  7873 */     while (check) {
/*  7874 */       i++;
/*  7875 */       a += 1.0D;
/*  7876 */       incr *= x / a;
/*  7877 */       sum += incr;
/*  7878 */       if (Math.abs(incr) < Math.abs(sum) * igfeps) {
/*  7879 */         igf = sum * Math.exp(-x + acopy * Math.log(x) - loggamma);
/*  7880 */         check = false;
/*       */       }
/*  7882 */       if (i >= igfiter) {
/*  7883 */         check = false;
/*  7884 */         igf = sum * Math.exp(-x + acopy * Math.log(x) - loggamma);
/*  7885 */         System.out.println("\nMaximum number of iterations were exceeded in Stat.incompleteGammaSer().\nCurrent value returned.\nIncrement = " + String.valueOf(incr) + ".\nSum = " + String.valueOf(sum) + ".\nTolerance =  " + String.valueOf(igfeps));
/*       */       }
/*       */     }
/*  7888 */     return igf;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double incompleteGammaFract(double a, double x)
/*       */   {
/*  7898 */     if ((a < 0.0D) || (x < 0.0D)) throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
/*  7899 */     if (x < a + 1.0D) { throw new IllegalArgumentException("\nx < a+1   Use Series Representation");
/*       */     }
/*  7901 */     int i = 0;
/*  7902 */     double ii = 0.0D;
/*  7903 */     double igf = 0.0D;
/*  7904 */     boolean check = true;
/*       */     
/*  7906 */     double loggamma = logGamma(a);
/*  7907 */     double numer = 0.0D;
/*  7908 */     double incr = 0.0D;
/*  7909 */     double denom = x - a + 1.0D;
/*  7910 */     double first = 1.0D / denom;
/*  7911 */     double term = 9.999999999999999E299D;
/*  7912 */     double prod = first;
/*       */     
/*  7914 */     while (check) {
/*  7915 */       i++;
/*  7916 */       ii = i;
/*  7917 */       numer = -ii * (ii - a);
/*  7918 */       denom += 2.0D;
/*  7919 */       first = numer * first + denom;
/*  7920 */       if (Math.abs(first) < 1.0E-300D) {
/*  7921 */         first = 1.0E-300D;
/*       */       }
/*  7923 */       term = denom + numer / term;
/*  7924 */       if (Math.abs(term) < 1.0E-300D) {
/*  7925 */         term = 1.0E-300D;
/*       */       }
/*  7927 */       first = 1.0D / first;
/*  7928 */       incr = first * term;
/*  7929 */       prod *= incr;
/*  7930 */       if (Math.abs(incr - 1.0D) < igfeps) check = false;
/*  7931 */       if (i >= igfiter) {
/*  7932 */         check = false;
/*  7933 */         System.out.println("\nMaximum number of iterations were exceeded in Stat.incompleteGammaFract().\nCurrent value returned.\nIncrement - 1 = " + String.valueOf(incr - 1.0D) + ".\nTolerance =  " + String.valueOf(igfeps));
/*       */       }
/*       */     }
/*  7936 */     igf = 1.0D - Math.exp(-x + a * Math.log(x) - loggamma) * prod;
/*  7937 */     return igf;
/*       */   }
/*       */   
/*       */   public static void setIncGammaMaxIter(int igfiter)
/*       */   {
/*  7942 */     igfiter = igfiter;
/*       */   }
/*       */   
/*       */   public static int getIncGammaMaxIter()
/*       */   {
/*  7947 */     return igfiter;
/*       */   }
/*       */   
/*       */   public static void setIncGammaTol(double igfeps)
/*       */   {
/*  7952 */     igfeps = igfeps;
/*       */   }
/*       */   
/*       */   public static double getIncGammaTol()
/*       */   {
/*  7957 */     return igfeps;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static int factorial(int n)
/*       */   {
/*  7966 */     if (n < 0) throw new IllegalArgumentException("n must be a positive integer");
/*  7967 */     if (n > 12) throw new IllegalArgumentException("n must less than 13 to avoid integer overflow\nTry long or double argument");
/*  7968 */     int f = 1;
/*  7969 */     for (int i = 2; i <= n; i++) f *= i;
/*  7970 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static long factorial(long n)
/*       */   {
/*  7977 */     if (n < 0L) throw new IllegalArgumentException("n must be a positive integer");
/*  7978 */     if (n > 20L) throw new IllegalArgumentException("n must less than 21 to avoid long integer overflow\nTry double argument");
/*  7979 */     long f = 1L;
/*  7980 */     long iCount = 2L;
/*  7981 */     while (iCount <= n) {
/*  7982 */       f *= iCount;
/*  7983 */       iCount += 1L;
/*       */     }
/*  7985 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigInteger factorial(BigInteger n)
/*       */   {
/*  7991 */     if (n.compareTo(BigInteger.ZERO) == -1) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  7992 */     BigInteger one = BigInteger.ONE;
/*  7993 */     BigInteger f = one;
/*  7994 */     BigInteger iCount = new BigInteger("2");
/*  7995 */     while (iCount.compareTo(n) != 1) {
/*  7996 */       f = f.multiply(iCount);
/*  7997 */       iCount = iCount.add(one);
/*       */     }
/*  7999 */     one = null;
/*  8000 */     iCount = null;
/*  8001 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double factorial(double n)
/*       */   {
/*  8009 */     if ((n < 0.0D) || (n - Math.floor(n) != 0.0D)) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  8010 */     double f = 1.0D;
/*  8011 */     double iCount = 2.0D;
/*  8012 */     while (iCount <= n) {
/*  8013 */       f *= iCount;
/*  8014 */       iCount += 1.0D;
/*       */     }
/*  8016 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */   public static BigDecimal factorial(BigDecimal n)
/*       */   {
/*  8022 */     if ((n.compareTo(BigDecimal.ZERO) == -1) || (!Fmath.isInteger(n))) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  8023 */     BigDecimal one = BigDecimal.ONE;
/*  8024 */     BigDecimal f = one;
/*  8025 */     BigDecimal iCount = new BigDecimal(2.0D);
/*  8026 */     while (iCount.compareTo(n) != 1) {
/*  8027 */       f = f.multiply(iCount);
/*  8028 */       iCount = iCount.add(one);
/*       */     }
/*  8030 */     one = null;
/*  8031 */     iCount = null;
/*  8032 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logFactorial(int n)
/*       */   {
/*  8040 */     if (n < 0) throw new IllegalArgumentException("\nn, " + n + ", must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  8041 */     double f = 0.0D;
/*  8042 */     for (int i = 2; i <= n; i++) f += Math.log(i);
/*  8043 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logFactorial(long n)
/*       */   {
/*  8051 */     if (n < 0L) throw new IllegalArgumentException("\nn, " + n + ", must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  8052 */     double f = 0.0D;
/*  8053 */     long iCount = 2L;
/*  8054 */     while (iCount <= n) {
/*  8055 */       f += Math.log(iCount);
/*  8056 */       iCount += 1L;
/*       */     }
/*  8058 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logFactorial(double n)
/*       */   {
/*  8066 */     if ((n < 0.0D) || (n - Math.floor(n) != 0.0D)) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  8067 */     double f = 0.0D;
/*  8068 */     double iCount = 2.0D;
/*  8069 */     while (iCount <= n) {
/*  8070 */       f += Math.log(iCount);
/*  8071 */       iCount += 1.0D;
/*       */     }
/*  8073 */     return f;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangCDF(double lambda, int kay, double upperLimit)
/*       */   {
/*  8082 */     return gammaCDF(0.0D, 1.0D / lambda, kay, upperLimit);
/*       */   }
/*       */   
/*       */   public static double erlangCDF(double lambda, long kay, double upperLimit) {
/*  8086 */     return gammaCDF(0.0D, 1.0D / lambda, kay, upperLimit);
/*       */   }
/*       */   
/*       */   public static double erlangCDF(double lambda, double kay, double upperLimit) {
/*  8090 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8091 */     return gammaCDF(0.0D, 1.0D / lambda, kay, upperLimit);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double erlangPDF(double lambda, int kay, double x)
/*       */   {
/*  8097 */     return gammaPDF(0.0D, 1.0D / lambda, kay, x);
/*       */   }
/*       */   
/*       */   public static double erlangPDF(double lambda, long kay, double x) {
/*  8101 */     return gammaPDF(0.0D, 1.0D / lambda, kay, x);
/*       */   }
/*       */   
/*       */   public static double erlangPDF(double lambda, double kay, double x) {
/*  8105 */     if (kay - Math.round(kay) != 0.0D) { throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*       */     }
/*  8107 */     return gammaPDF(0.0D, 1.0D / lambda, kay, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double erlangMean(double lambda, int kay)
/*       */   {
/*  8113 */     if (kay < 1) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8114 */     return kay / lambda;
/*       */   }
/*       */   
/*       */   public static double erlangMean(double lambda, long kay) {
/*  8118 */     if (kay < 1L) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8119 */     return kay / lambda;
/*       */   }
/*       */   
/*       */   public static double erlangMean(double lambda, double kay) {
/*  8123 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8124 */     if (kay < 1.0D) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8125 */     return kay / lambda;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double erlangMode(double lambda, int kay)
/*       */   {
/*  8131 */     if (kay < 1) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8132 */     double mode = NaN.0D;
/*  8133 */     if (kay >= 1) mode = (kay - 1.0D) / lambda;
/*  8134 */     return mode;
/*       */   }
/*       */   
/*       */   public static double erlangMode(double lambda, long kay) {
/*  8138 */     if (kay < 1L) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8139 */     double mode = NaN.0D;
/*  8140 */     if (kay >= 1L) mode = (kay - 1.0D) / lambda;
/*  8141 */     return mode;
/*       */   }
/*       */   
/*       */   public static double erlangMode(double lambda, double kay) {
/*  8145 */     if (kay < 1.0D) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8146 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8147 */     double mode = NaN.0D;
/*  8148 */     if (kay >= 1.0D) mode = (kay - 1.0D) / lambda;
/*  8149 */     return mode;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double erlangStandardDeviation(double lambda, int kay)
/*       */   {
/*  8156 */     return erlangStandDev(lambda, kay);
/*       */   }
/*       */   
/*       */   public static double erlangStandardDeviation(double lambda, long kay)
/*       */   {
/*  8161 */     return erlangStandDev(lambda, kay);
/*       */   }
/*       */   
/*       */   public static double erlangStandardDeviation(double lambda, double kay)
/*       */   {
/*  8166 */     return erlangStandDev(lambda, kay);
/*       */   }
/*       */   
/*       */   public static double erlangStandDev(double lambda, int kay)
/*       */   {
/*  8171 */     if (kay < 1) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8172 */     return Math.sqrt(kay) / lambda;
/*       */   }
/*       */   
/*       */   public static double erlangStandDev(double lambda, long kay) {
/*  8176 */     if (kay < 1L) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8177 */     return Math.sqrt(kay) / lambda;
/*       */   }
/*       */   
/*       */   public static double erlangStandDev(double lambda, double kay) {
/*  8181 */     if (kay < 1.0D) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8182 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8183 */     return Math.sqrt(kay) / lambda;
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, int kay, int n)
/*       */   {
/*  8188 */     if (kay < 1) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8189 */     return gammaRand(0.0D, 1.0D / lambda, kay, n);
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, long kay, int n) {
/*  8193 */     if (kay < 1L) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8194 */     return gammaRand(0.0D, 1.0D / lambda, kay, n);
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, double kay, int n) {
/*  8198 */     if (kay < 1.0D) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8199 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8200 */     return gammaRand(0.0D, 1.0D / lambda, kay, n);
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, int kay, int n, long seed)
/*       */   {
/*  8205 */     if (kay < 1) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8206 */     return gammaRand(0.0D, 1.0D / lambda, kay, n, seed);
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, long kay, int n, long seed) {
/*  8210 */     if (kay < 1L) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8211 */     return gammaRand(0.0D, 1.0D / lambda, kay, n, seed);
/*       */   }
/*       */   
/*       */   public static double[] erlangRand(double lambda, double kay, int n, long seed) {
/*  8215 */     if (kay < 1.0D) throw new IllegalArgumentException("The rate parameter, " + kay + "must be equal to or greater than one");
/*  8216 */     if (kay - Math.round(kay) != 0.0D) throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
/*  8217 */     return gammaRand(0.0D, 1.0D / lambda, kay, n, seed);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangMprobability(double totalTraffic, double totalResources, double em)
/*       */   {
/*  8227 */     double prob = 0.0D;
/*  8228 */     if (totalTraffic > 0.0D)
/*       */     {
/*  8230 */       double numer = totalResources * Math.log(em) - Fmath.logFactorial(em);
/*  8231 */       double denom = 1.0D;
/*  8232 */       double lastTerm = 1.0D;
/*  8233 */       for (int i = 1; i <= totalResources; i++) {
/*  8234 */         lastTerm = lastTerm * totalTraffic / i;
/*  8235 */         denom += lastTerm;
/*       */       }
/*  8237 */       denom = Math.log(denom);
/*  8238 */       prob = numer - denom;
/*  8239 */       prob = Math.exp(prob);
/*       */     }
/*  8241 */     return prob;
/*       */   }
/*       */   
/*       */   public static double erlangMprobability(double totalTraffic, long totalResources, long em) {
/*  8245 */     return erlangMprobability(totalTraffic, totalResources, em);
/*       */   }
/*       */   
/*       */   public static double erlangMprobability(double totalTraffic, int totalResources, int em) {
/*  8249 */     return erlangMprobability(totalTraffic, totalResources, em);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangBprobability(double totalTraffic, double totalResources)
/*       */   {
/*  8258 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8259 */     if (!Fmath.isInteger(totalResources)) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*  8260 */     double prob = 0.0D;
/*  8261 */     double iCount = 1.0D;
/*  8262 */     if (totalTraffic > 0.0D) {
/*  8263 */       prob = 1.0D;
/*  8264 */       double hold = 0.0D;
/*  8265 */       while (iCount <= totalResources) {
/*  8266 */         hold = prob * totalTraffic;
/*  8267 */         prob = hold / (iCount + hold);
/*  8268 */         iCount += 1.0D;
/*       */       }
/*       */     }
/*  8271 */     return prob;
/*       */   }
/*       */   
/*       */   public static double erlangBprobability(double totalTraffic, long totalResources)
/*       */   {
/*  8276 */     return erlangBprobability(totalTraffic, totalResources);
/*       */   }
/*       */   
/*       */   public static double erlangBprobability(double totalTraffic, int totalResources) {
/*  8280 */     return erlangBprobability(totalTraffic, totalResources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangBload(double blockingProbability, double totalResources)
/*       */   {
/*  8288 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8289 */     if (!Fmath.isInteger(totalResources)) { throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*       */     }
/*       */     
/*  8292 */     ErlangBfunct eBfunc = new ErlangBfunct();
/*       */     
/*       */ 
/*  8295 */     eBfunc.blockingProbability = blockingProbability;
/*  8296 */     eBfunc.totalResources = totalResources;
/*       */     
/*       */ 
/*  8299 */     double lowerBound = 0.0D;
/*       */     
/*  8301 */     double upperBound = 20.0D;
/*       */     
/*  8303 */     double tolerance = 1.0E-6D;
/*       */     
/*       */ 
/*  8306 */     RealRoot realR = new RealRoot();
/*       */     
/*       */ 
/*  8309 */     realR.setTolerance(tolerance);
/*       */     
/*       */ 
/*  8312 */     realR.noLowerBoundExtension();
/*       */     
/*       */ 
/*  8315 */     realR.supressLimitReachedMessage();
/*       */     
/*       */ 
/*  8318 */     double root = realR.bisect(eBfunc, lowerBound, upperBound);
/*       */     
/*  8320 */     return root;
/*       */   }
/*       */   
/*       */   public static double erlangBload(double blockingProbability, long totalResources) {
/*  8324 */     return erlangBload(blockingProbability, totalResources);
/*       */   }
/*       */   
/*       */   public static double erlangBload(double blockingProbability, int totalResources) {
/*  8328 */     return erlangBload(blockingProbability, totalResources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] erlangBresources(double blockingProbability, double totalTraffic)
/*       */   {
/*  8337 */     double[] ret = new double[8];
/*  8338 */     long counter = 1L;
/*  8339 */     double lastProb = NaN.0D;
/*  8340 */     double prob = NaN.0D;
/*  8341 */     boolean test = true;
/*  8342 */     while (test) {
/*  8343 */       prob = erlangBprobability(totalTraffic, counter);
/*  8344 */       if (prob <= blockingProbability) {
/*  8345 */         ret[0] = counter;
/*  8346 */         ret[1] = prob;
/*  8347 */         ret[2] = erlangBload(blockingProbability, counter);
/*  8348 */         ret[3] = (counter - 1L);
/*  8349 */         ret[4] = lastProb;
/*  8350 */         ret[5] = erlangBload(blockingProbability, counter - 1L);
/*  8351 */         ret[6] = blockingProbability;
/*  8352 */         ret[7] = totalTraffic;
/*  8353 */         test = false;
/*       */       }
/*       */       else {
/*  8356 */         lastProb = prob;
/*  8357 */         counter += 1L;
/*  8358 */         if (counter == 2147483647L) {
/*  8359 */           System.out.println("Method erlangBresources: no solution found below 9223372036854775807resources");
/*  8360 */           for (int i = 0; i < 8; i++) ret[i] = NaN.0D;
/*  8361 */           test = false;
/*       */         }
/*       */       }
/*       */     }
/*  8365 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangCprobability(double totalTraffic, double totalResources)
/*       */   {
/*  8373 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8374 */     if (!Fmath.isInteger(totalResources)) { throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*       */     }
/*  8376 */     double prob = 0.0D;
/*  8377 */     if (totalTraffic > 0.0D)
/*       */     {
/*  8379 */       double numer = totalResources * Math.log(totalTraffic) - Fmath.logFactorial(totalResources);
/*  8380 */       numer = Math.exp(numer) * totalResources / (totalResources - totalTraffic);
/*  8381 */       double denom = 1.0D;
/*  8382 */       double lastTerm = 1.0D;
/*  8383 */       double iCount = 1.0D;
/*  8384 */       while (iCount <= totalResources) {
/*  8385 */         lastTerm = lastTerm * totalTraffic / iCount;
/*  8386 */         denom += lastTerm;
/*  8387 */         iCount += 1.0D;
/*       */       }
/*  8389 */       denom += numer;
/*  8390 */       prob = numer / denom;
/*       */     }
/*  8392 */     return prob;
/*       */   }
/*       */   
/*       */   public static double erlangCprobability(double totalTraffic, long totalResources) {
/*  8396 */     return erlangCprobability(totalTraffic, totalResources);
/*       */   }
/*       */   
/*       */   public static double erlangCprobability(double totalTraffic, int totalResources) {
/*  8400 */     return erlangCprobability(totalTraffic, totalResources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erlangCload(double nonZeroDelayProbability, double totalResources)
/*       */   {
/*  8408 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8409 */     if (!Fmath.isInteger(totalResources)) { throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*       */     }
/*       */     
/*  8412 */     ErlangCfunct eCfunc = new ErlangCfunct();
/*       */     
/*       */ 
/*  8415 */     eCfunc.nonZeroDelayProbability = nonZeroDelayProbability;
/*  8416 */     eCfunc.totalResources = totalResources;
/*       */     
/*       */ 
/*  8419 */     double lowerBound = 0.0D;
/*       */     
/*  8421 */     double upperBound = 10.0D;
/*       */     
/*  8423 */     double tolerance = 1.0E-6D;
/*       */     
/*       */ 
/*  8426 */     RealRoot realR = new RealRoot();
/*       */     
/*       */ 
/*  8429 */     realR.setTolerance(tolerance);
/*       */     
/*       */ 
/*  8432 */     realR.supressLimitReachedMessage();
/*       */     
/*       */ 
/*  8435 */     realR.noLowerBoundExtension();
/*       */     
/*       */ 
/*  8438 */     double root = realR.bisect(eCfunc, lowerBound, upperBound);
/*       */     
/*  8440 */     return root;
/*       */   }
/*       */   
/*       */   public static double erlangCload(double nonZeroDelayProbability, long totalResources) {
/*  8444 */     return erlangCload(nonZeroDelayProbability, totalResources);
/*       */   }
/*       */   
/*       */   public static double erlangCload(double nonZeroDelayProbability, int totalResources) {
/*  8448 */     return erlangCload(nonZeroDelayProbability, totalResources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] erlangCresources(double nonZeroDelayProbability, double totalTraffic)
/*       */   {
/*  8457 */     double[] ret = new double[8];
/*  8458 */     long counter = 1L;
/*  8459 */     double lastProb = NaN.0D;
/*  8460 */     double prob = NaN.0D;
/*  8461 */     boolean test = true;
/*  8462 */     while (test) {
/*  8463 */       prob = erlangCprobability(totalTraffic, counter);
/*  8464 */       if (prob <= nonZeroDelayProbability) {
/*  8465 */         ret[0] = counter;
/*  8466 */         ret[1] = prob;
/*  8467 */         ret[2] = erlangCload(nonZeroDelayProbability, counter);
/*  8468 */         ret[3] = (counter - 1L);
/*  8469 */         ret[4] = lastProb;
/*  8470 */         ret[5] = erlangCload(nonZeroDelayProbability, counter - 1L);
/*  8471 */         ret[6] = nonZeroDelayProbability;
/*  8472 */         ret[7] = totalTraffic;
/*  8473 */         test = false;
/*       */       }
/*       */       else {
/*  8476 */         lastProb = prob;
/*  8477 */         counter += 1L;
/*  8478 */         if (counter == 2147483647L) {
/*  8479 */           System.out.println("Method erlangCresources: no solution found below 9223372036854775807resources");
/*  8480 */           for (int i = 0; i < 8; i++) ret[i] = NaN.0D;
/*  8481 */           test = false;
/*       */         }
/*       */       }
/*       */     }
/*  8485 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double engsetProbability(double offeredTraffic, double totalResources, double numberOfSources)
/*       */   {
/*  8498 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8499 */     if (!Fmath.isInteger(totalResources)) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*  8500 */     if (numberOfSources < 1.0D) throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be an integer greater than or equal to 1");
/*  8501 */     if (!Fmath.isInteger(numberOfSources)) throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be, arithmetically, an integer");
/*  8502 */     if (totalResources > numberOfSources - 1.0D) throw new IllegalArgumentException("total resources, " + totalResources + ", must be less than or  equal to the number of sources minus one, " + (numberOfSources - 1.0D));
/*  8503 */     if (offeredTraffic >= numberOfSources) { throw new IllegalArgumentException("Number of sources, " + numberOfSources + ", must be greater than the offered traffic, " + offeredTraffic);
/*       */     }
/*  8505 */     double prob = 0.0D;
/*  8506 */     if (totalResources == 0.0D) {
/*  8507 */       prob = 1.0D;
/*       */ 
/*       */     }
/*  8510 */     else if (offeredTraffic == 0.0D) {
/*  8511 */       prob = 0.0D;
/*       */     }
/*       */     else
/*       */     {
/*  8515 */       double lowerBound = 0.0D;
/*  8516 */       double upperBound = 1.0D;
/*       */       
/*       */ 
/*  8519 */       EngsetProb engProb = new EngsetProb();
/*       */       
/*       */ 
/*  8522 */       engProb.offeredTraffic = offeredTraffic;
/*  8523 */       engProb.totalResources = totalResources;
/*  8524 */       engProb.numberOfSources = numberOfSources;
/*       */       
/*       */ 
/*  8527 */       RealRoot eprt = new RealRoot();
/*       */       
/*       */ 
/*  8530 */       eprt.supressLimitReachedMessage();
/*       */       
/*  8532 */       prob = eprt.bisect(engProb, lowerBound, upperBound);
/*       */     }
/*       */     
/*  8535 */     return prob;
/*       */   }
/*       */   
/*       */   public static double engsetProbability(double offeredTraffic, long totalResources, long numberOfSources) {
/*  8539 */     return engsetProbability(offeredTraffic, totalResources, numberOfSources);
/*       */   }
/*       */   
/*       */   public static double engsetProbability(double offeredTraffic, int totalResources, int numberOfSources) {
/*  8543 */     return engsetProbability(offeredTraffic, totalResources, numberOfSources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double engsetLoad(double blockingProbability, double totalResources, double numberOfSources)
/*       */   {
/*  8552 */     if (totalResources < 1.0D) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be an integer greater than or equal to 1");
/*  8553 */     if (!Fmath.isInteger(totalResources)) throw new IllegalArgumentException("Total resources, " + totalResources + ", must be, arithmetically, an integer");
/*  8554 */     if (numberOfSources < 1.0D) throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be an integer greater than or equal to 1");
/*  8555 */     if (!Fmath.isInteger(numberOfSources)) throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be, arithmetically, an integer");
/*  8556 */     if (totalResources > numberOfSources - 1.0D) { throw new IllegalArgumentException("total resources, " + totalResources + ", must be less than or  equal to the number of sources minus one, " + (numberOfSources - 1.0D));
/*       */     }
/*       */     
/*  8559 */     EngsetLoad eLfunc = new EngsetLoad();
/*       */     
/*       */ 
/*  8562 */     eLfunc.blockingProbability = blockingProbability;
/*  8563 */     eLfunc.totalResources = totalResources;
/*  8564 */     eLfunc.numberOfSources = numberOfSources;
/*       */     
/*       */ 
/*  8567 */     double lowerBound = 0.0D;
/*       */     
/*  8569 */     double upperBound = numberOfSources * 0.999999999D;
/*       */     
/*  8571 */     double tolerance = 1.0E-6D;
/*       */     
/*       */ 
/*  8574 */     RealRoot realR = new RealRoot();
/*       */     
/*       */ 
/*  8577 */     realR.setTolerance(tolerance);
/*       */     
/*       */ 
/*  8580 */     realR.noLowerBoundExtension();
/*  8581 */     realR.noUpperBoundExtension();
/*       */     
/*       */ 
/*  8584 */     realR.supressLimitReachedMessage();
/*       */     
/*       */ 
/*  8587 */     double root = realR.bisect(eLfunc, lowerBound, upperBound);
/*       */     
/*  8589 */     return root;
/*       */   }
/*       */   
/*       */   public static double engsetLoad(double blockingProbability, long totalResources, long numberOfSources) {
/*  8593 */     return engsetLoad(blockingProbability, totalResources, numberOfSources);
/*       */   }
/*       */   
/*       */   public static double engsetLoad(double blockingProbability, int totalResources, int numberOfSources) {
/*  8597 */     return engsetLoad(blockingProbability, totalResources, numberOfSources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] engsetResources(double blockingProbability, double offeredTraffic, double numberOfSources)
/*       */   {
/*  8606 */     if (numberOfSources < 1.0D) throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be an integer greater than or equal to 1");
/*  8607 */     if (!Fmath.isInteger(numberOfSources)) { throw new IllegalArgumentException("number of sources, " + numberOfSources + ", must be, arithmetically, an integer");
/*       */     }
/*  8609 */     double[] ret = new double[9];
/*  8610 */     long counter = 1L;
/*  8611 */     double lastProb = NaN.0D;
/*  8612 */     double prob = NaN.0D;
/*  8613 */     boolean test = true;
/*  8614 */     while (test) {
/*  8615 */       prob = engsetProbability(offeredTraffic, counter, numberOfSources);
/*  8616 */       if (prob <= blockingProbability)
/*       */       {
/*  8618 */         ret[0] = counter;
/*  8619 */         ret[1] = prob;
/*  8620 */         ret[2] = engsetLoad(blockingProbability, counter, numberOfSources);
/*  8621 */         ret[3] = (counter - 1L);
/*  8622 */         ret[4] = lastProb;
/*  8623 */         ret[5] = engsetLoad(blockingProbability, counter - 1L, numberOfSources);
/*  8624 */         ret[6] = blockingProbability;
/*  8625 */         ret[7] = offeredTraffic;
/*  8626 */         ret[8] = numberOfSources;
/*  8627 */         test = false;
/*       */       }
/*       */       else {
/*  8630 */         lastProb = prob;
/*  8631 */         counter += 1L;
/*  8632 */         if (counter > numberOfSources - 1L) {
/*  8633 */           System.out.println("Method engsetResources: no solution found below the (sources-1), " + (numberOfSources - 1.0D));
/*  8634 */           for (int i = 0; i < 8; i++) ret[i] = NaN.0D;
/*  8635 */           test = false;
/*       */         }
/*       */       }
/*       */     }
/*  8639 */     return ret;
/*       */   }
/*       */   
/*       */   public static double[] engsetResources(double blockingProbability, double totalTraffic, long numberOfSources) {
/*  8643 */     return engsetResources(blockingProbability, totalTraffic, numberOfSources);
/*       */   }
/*       */   
/*       */   public static double[] engsetResources(double blockingProbability, double totalTraffic, int numberOfSources) {
/*  8647 */     return engsetResources(blockingProbability, totalTraffic, numberOfSources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] engsetSources(double blockingProbability, double offeredTraffic, double resources)
/*       */   {
/*  8657 */     if (resources < 1.0D) throw new IllegalArgumentException("resources, " + resources + ", must be an integer greater than or equal to 1");
/*  8658 */     if (!Fmath.isInteger(resources)) { throw new IllegalArgumentException("resources, " + resources + ", must be, arithmetically, an integer");
/*       */     }
/*  8660 */     double[] ret = new double[9];
/*  8661 */     long counter = resources + 1L;
/*  8662 */     double lastProb = NaN.0D;
/*  8663 */     double prob = NaN.0D;
/*  8664 */     boolean test = true;
/*  8665 */     while (test) {
/*  8666 */       prob = engsetProbability(offeredTraffic, resources, counter);
/*  8667 */       if (prob >= blockingProbability)
/*       */       {
/*  8669 */         ret[0] = counter;
/*  8670 */         ret[1] = prob;
/*  8671 */         ret[2] = engsetLoad(blockingProbability, resources, counter);
/*  8672 */         ret[3] = (counter - 1L);
/*  8673 */         ret[4] = lastProb;
/*  8674 */         if (counter - 1L >= (resources + 1.0D)) {
/*  8675 */           ret[5] = engsetLoad(blockingProbability, resources, counter - 1L);
/*       */         }
/*       */         else {
/*  8678 */           ret[5] = NaN.0D;
/*       */         }
/*  8680 */         ret[6] = blockingProbability;
/*  8681 */         ret[7] = offeredTraffic;
/*  8682 */         ret[8] = resources;
/*  8683 */         test = false;
/*       */       }
/*       */       else {
/*  8686 */         lastProb = prob;
/*  8687 */         counter += 1L;
/*  8688 */         if (counter >= Long.MAX_VALUE) {
/*  8689 */           System.out.println("Method engsetResources: no solution found below 9223372036854775807sources");
/*  8690 */           for (int i = 0; i < 8; i++) ret[i] = NaN.0D;
/*  8691 */           test = false;
/*       */         }
/*       */       }
/*       */     }
/*  8695 */     return ret;
/*       */   }
/*       */   
/*       */   public static double[] engsetSources(double blockingProbability, double totalTraffic, long resources) {
/*  8699 */     return engsetSources(blockingProbability, totalTraffic, resources);
/*       */   }
/*       */   
/*       */   public static double[] engsetSources(double blockingProbability, double totalTraffic, int resources) {
/*  8703 */     return engsetSources(blockingProbability, totalTraffic, resources);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double betaCDF(double alpha, double beta, double limit)
/*       */   {
/*  8713 */     return betaCDF(0.0D, 1.0D, alpha, beta, limit);
/*       */   }
/*       */   
/*       */   public static double betaCDF(double min, double max, double alpha, double beta, double limit)
/*       */   {
/*  8718 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8719 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8720 */     if (limit < min) throw new IllegalArgumentException("limit, " + limit + ", must be greater than or equal to the minimum value, " + min);
/*  8721 */     if (limit > max) throw new IllegalArgumentException("limit, " + limit + ", must be less than or equal to the maximum value, " + max);
/*  8722 */     return regularisedBetaFunction(alpha, beta, (limit - min) / (max - min));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double betaPDF(double alpha, double beta, double x)
/*       */   {
/*  8728 */     return betaPDF(0.0D, 1.0D, alpha, beta, x);
/*       */   }
/*       */   
/*       */   public static double betaPDF(double min, double max, double alpha, double beta, double x)
/*       */   {
/*  8733 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8734 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8735 */     if (x < min) throw new IllegalArgumentException("x, " + x + ", must be greater than or equal to the minimum value, " + min);
/*  8736 */     if (x > max) throw new IllegalArgumentException("x, " + x + ", must be less than or equal to the maximum value, " + max);
/*  8737 */     double pdf = Math.pow(x - min, alpha - 1.0D) * Math.pow(max - x, beta - 1.0D) / Math.pow(max - min, alpha + beta - 1.0D);
/*  8738 */     return pdf / betaFunction(alpha, beta);
/*       */   }
/*       */   
/*       */   public static double[] betaRand(double alpha, double beta, int n)
/*       */   {
/*  8743 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8744 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8745 */     PsRandom psr = new PsRandom();
/*  8746 */     return psr.betaArray(alpha, beta, n);
/*       */   }
/*       */   
/*       */   public static double[] betaRand(double min, double max, double alpha, double beta, int n)
/*       */   {
/*  8751 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8752 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8753 */     PsRandom psr = new PsRandom();
/*  8754 */     return psr.betaArray(min, max, alpha, beta, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] betaRand(double alpha, double beta, int n, long seed)
/*       */   {
/*  8760 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8761 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter,  beta, " + beta + "must be greater than zero");
/*  8762 */     PsRandom psr = new PsRandom(seed);
/*  8763 */     return psr.betaArray(alpha, beta, n);
/*       */   }
/*       */   
/*       */   public static double[] betaRand(double min, double max, double alpha, double beta, int n, long seed)
/*       */   {
/*  8768 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8769 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter,  beta, " + beta + "must be greater than zero");
/*  8770 */     PsRandom psr = new PsRandom(seed);
/*  8771 */     return psr.betaArray(min, max, alpha, beta, n);
/*       */   }
/*       */   
/*       */   public static double betaMean(double alpha, double beta)
/*       */   {
/*  8776 */     return betaMean(0.0D, 1.0D, alpha, beta);
/*       */   }
/*       */   
/*       */   public static double betaMean(double min, double max, double alpha, double beta)
/*       */   {
/*  8781 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8782 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8783 */     return min + alpha * (max - min) / (alpha + beta);
/*       */   }
/*       */   
/*       */   public static double betaMode(double alpha, double beta)
/*       */   {
/*  8788 */     return betaMode(0.0D, 1.0D, alpha, beta);
/*       */   }
/*       */   
/*       */   public static double betaMode(double min, double max, double alpha, double beta)
/*       */   {
/*  8793 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8794 */     if (beta <= 0.0D) { throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*       */     }
/*  8796 */     double mode = NaN.0D;
/*  8797 */     if (alpha > 1.0D) {
/*  8798 */       if (beta > 1.0D) {
/*  8799 */         mode = min + (alpha + beta) * (max - min) / (alpha + beta - 2.0D);
/*       */       }
/*       */       else {
/*  8802 */         mode = max;
/*       */       }
/*       */       
/*       */     }
/*  8806 */     else if (alpha == 1.0D) {
/*  8807 */       if (beta > 1.0D) {
/*  8808 */         mode = min;
/*       */ 
/*       */       }
/*  8811 */       else if (beta == 1.0D) {
/*  8812 */         mode = NaN.0D;
/*       */       }
/*       */       else {
/*  8815 */         mode = max;
/*       */       }
/*       */       
/*       */ 
/*       */     }
/*  8820 */     else if (beta >= 1.0D) {
/*  8821 */       mode = min;
/*       */     }
/*       */     else {
/*  8824 */       System.out.println("Class Stat; method betaMode; distribution is bimodal wirh modes at " + min + " and " + max);
/*  8825 */       System.out.println("NaN returned");
/*       */     }
/*       */     
/*       */ 
/*  8829 */     return mode;
/*       */   }
/*       */   
/*       */   public static double betaStandardDeviation(double alpha, double beta)
/*       */   {
/*  8834 */     return betaStandDev(alpha, beta);
/*       */   }
/*       */   
/*       */   public static double betaStandDev(double alpha, double beta)
/*       */   {
/*  8839 */     return betaStandDev(0.0D, 1.0D, alpha, beta);
/*       */   }
/*       */   
/*       */   public static double betaStandardDeviation(double min, double max, double alpha, double beta)
/*       */   {
/*  8844 */     return betaStandDev(min, max, alpha, beta);
/*       */   }
/*       */   
/*       */   public static double betaStandDev(double min, double max, double alpha, double beta)
/*       */   {
/*  8849 */     if (alpha <= 0.0D) throw new IllegalArgumentException("The shape parameter, alpha, " + alpha + "must be greater than zero");
/*  8850 */     if (beta <= 0.0D) throw new IllegalArgumentException("The shape parameter, beta, " + beta + "must be greater than zero");
/*  8851 */     return (max - min) / (alpha + beta) * Math.sqrt(alpha * beta / (alpha + beta + 1.0D));
/*       */   }
/*       */   
/*       */   public static double betaFunction(double z, double w)
/*       */   {
/*  8856 */     return Math.exp(logGamma(z) + logGamma(w) - logGamma(z + w));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double beta(double z, double w)
/*       */   {
/*  8862 */     return Math.exp(logGamma(z) + logGamma(w) - logGamma(z + w));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double regularisedBetaFunction(double z, double w, double x)
/*       */   {
/*  8868 */     if ((x < 0.0D) || (x > 1.0D)) throw new IllegalArgumentException("Argument x, " + x + ", must be lie between 0 and 1 (inclusive)");
/*  8869 */     double ibeta = 0.0D;
/*  8870 */     if (x == 0.0D) {
/*  8871 */       ibeta = 0.0D;
/*       */ 
/*       */     }
/*  8874 */     else if (x == 1.0D) {
/*  8875 */       ibeta = 1.0D;
/*       */     }
/*       */     else
/*       */     {
/*  8879 */       ibeta = Math.exp(logGamma(z + w) - logGamma(z) - logGamma(w) + z * Math.log(x) + w * Math.log(1.0D - x));
/*       */       
/*  8881 */       if (x < (z + 1.0D) / (z + w + 2.0D)) {
/*  8882 */         ibeta = ibeta * contFract(z, w, x) / z;
/*       */       }
/*       */       else
/*       */       {
/*  8886 */         ibeta = 1.0D - ibeta * contFract(w, z, 1.0D - x) / w;
/*       */       }
/*       */     }
/*       */     
/*  8890 */     return ibeta;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double regularizedBetaFunction(double z, double w, double x)
/*       */   {
/*  8897 */     return regularisedBetaFunction(z, w, x);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double incompleteBeta(double z, double w, double x)
/*       */   {
/*  8904 */     return regularisedBetaFunction(z, w, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double contFract(double a, double b, double x)
/*       */   {
/*  8910 */     int maxit = 500;
/*  8911 */     double eps = 3.0E-7D;
/*  8912 */     double aplusb = a + b;
/*  8913 */     double aplus1 = a + 1.0D;
/*  8914 */     double aminus1 = a - 1.0D;
/*  8915 */     double c = 1.0D;
/*  8916 */     double d = 1.0D - aplusb * x / aplus1;
/*  8917 */     if (Math.abs(d) < 1.0E-300D) d = 1.0E-300D;
/*  8918 */     d = 1.0D / d;
/*  8919 */     double h = d;
/*  8920 */     double aa = 0.0D;
/*  8921 */     double del = 0.0D;
/*  8922 */     int i = 1;int i2 = 0;
/*  8923 */     boolean test = true;
/*  8924 */     while (test) {
/*  8925 */       i2 = 2 * i;
/*  8926 */       aa = i * (b - i) * x / ((aminus1 + i2) * (a + i2));
/*  8927 */       d = 1.0D + aa * d;
/*  8928 */       if (Math.abs(d) < 1.0E-300D) d = 1.0E-300D;
/*  8929 */       c = 1.0D + aa / c;
/*  8930 */       if (Math.abs(c) < 1.0E-300D) c = 1.0E-300D;
/*  8931 */       d = 1.0D / d;
/*  8932 */       h *= d * c;
/*  8933 */       aa = -(a + i) * (aplusb + i) * x / ((a + i2) * (aplus1 + i2));
/*  8934 */       d = 1.0D + aa * d;
/*  8935 */       if (Math.abs(d) < 1.0E-300D) d = 1.0E-300D;
/*  8936 */       c = 1.0D + aa / c;
/*  8937 */       if (Math.abs(c) < 1.0E-300D) c = 1.0E-300D;
/*  8938 */       d = 1.0D / d;
/*  8939 */       del = d * c;
/*  8940 */       h *= del;
/*  8941 */       i++;
/*  8942 */       if (Math.abs(del - 1.0D) < eps) test = false;
/*  8943 */       if (i > maxit) {
/*  8944 */         test = false;
/*  8945 */         System.out.println("Maximum number of iterations (" + maxit + ") exceeded in Stat.contFract in Stat.incomplete Beta");
/*       */       }
/*       */     }
/*  8948 */     return h;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double erf(double x)
/*       */   {
/*  8957 */     double erf = 0.0D;
/*  8958 */     if (x != 0.0D) {
/*  8959 */       if (x == Double.POSITIVE_INFINITY) {
/*  8960 */         erf = 1.0D;
/*       */ 
/*       */       }
/*  8963 */       else if (x >= 0.0D) {
/*  8964 */         erf = incompleteGamma(0.5D, x * x);
/*       */       }
/*       */       else {
/*  8967 */         erf = -incompleteGamma(0.5D, x * x);
/*       */       }
/*       */     }
/*       */     
/*  8971 */     return erf;
/*       */   }
/*       */   
/*       */   public static double erfc(double x)
/*       */   {
/*  8976 */     double erfc = 1.0D;
/*  8977 */     if (x != 0.0D) {
/*  8978 */       if (x == Double.POSITIVE_INFINITY) {
/*  8979 */         erfc = 0.0D;
/*       */ 
/*       */       }
/*  8982 */       else if (x >= 0.0D) {
/*  8983 */         erfc = 1.0D - incompleteGamma(0.5D, x * x);
/*       */       }
/*       */       else {
/*  8986 */         erfc = 1.0D + incompleteGamma(0.5D, x * x);
/*       */       }
/*       */     }
/*       */     
/*  8990 */     return erfc;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double normalCDF(double mean, double sd, double upperlimit)
/*       */   {
/*  9000 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9001 */       return 1.0D;
/*       */     }
/*       */     
/*  9004 */     if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9005 */       return 0.0D;
/*       */     }
/*       */     
/*  9008 */     double arg = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9009 */     return (1.0D + erf(arg)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double normalProb(double mean, double sd, double upperlimit)
/*       */   {
/*  9018 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9019 */       return 1.0D;
/*       */     }
/*       */     
/*  9022 */     if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9023 */       return 0.0D;
/*       */     }
/*       */     
/*  9026 */     double arg = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9027 */     return (1.0D + erf(arg)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gaussianCDF(double mean, double sd, double upperlimit)
/*       */   {
/*  9036 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9037 */       return 1.0D;
/*       */     }
/*       */     
/*  9040 */     if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9041 */       return 0.0D;
/*       */     }
/*       */     
/*  9044 */     double arg = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9045 */     return (1.0D + erf(arg)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gaussianProb(double mean, double sd, double upperlimit)
/*       */   {
/*  9054 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9055 */       return 1.0D;
/*       */     }
/*       */     
/*  9058 */     if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9059 */       return 0.0D;
/*       */     }
/*       */     
/*  9062 */     double arg = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9063 */     return (1.0D + erf(arg)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double normalCDF(double mean, double sd, double lowerlimit, double upperlimit)
/*       */   {
/*  9072 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9073 */       if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9074 */         return 1.0D;
/*       */       }
/*       */       
/*  9077 */       if (lowerlimit == Double.POSITIVE_INFINITY) {
/*  9078 */         return 0.0D;
/*       */       }
/*       */       
/*  9081 */       return 1.0D - normalCDF(mean, sd, lowerlimit);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9086 */     if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9087 */       if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9088 */         return 0.0D;
/*       */       }
/*       */       
/*  9091 */       return normalCDF(mean, sd, upperlimit);
/*       */     }
/*       */     
/*       */ 
/*  9095 */     double arg1 = (lowerlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9096 */     double arg2 = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9097 */     return (erf(arg2) - erf(arg1)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double normalProb(double mean, double sd, double lowerlimit, double upperlimit)
/*       */   {
/*  9106 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9107 */       if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9108 */         return 1.0D;
/*       */       }
/*       */       
/*  9111 */       if (lowerlimit == Double.POSITIVE_INFINITY) {
/*  9112 */         return 0.0D;
/*       */       }
/*       */       
/*  9115 */       return 1.0D - normalCDF(mean, sd, lowerlimit);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9120 */     if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9121 */       if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9122 */         return 0.0D;
/*       */       }
/*       */       
/*  9125 */       return normalCDF(mean, sd, upperlimit);
/*       */     }
/*       */     
/*       */ 
/*  9129 */     double arg1 = (lowerlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9130 */     double arg2 = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9131 */     return (erf(arg2) - erf(arg1)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gaussianCDF(double mean, double sd, double lowerlimit, double upperlimit)
/*       */   {
/*  9140 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9141 */       if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9142 */         return 1.0D;
/*       */       }
/*       */       
/*  9145 */       if (lowerlimit == Double.POSITIVE_INFINITY) {
/*  9146 */         return 0.0D;
/*       */       }
/*       */       
/*  9149 */       return 1.0D - normalCDF(mean, sd, lowerlimit);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9154 */     if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9155 */       if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9156 */         return 0.0D;
/*       */       }
/*       */       
/*  9159 */       return normalCDF(mean, sd, upperlimit);
/*       */     }
/*       */     
/*       */ 
/*  9163 */     double arg1 = (lowerlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9164 */     double arg2 = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9165 */     return (erf(arg2) - erf(arg1)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gaussianProb(double mean, double sd, double lowerlimit, double upperlimit)
/*       */   {
/*  9174 */     if (upperlimit == Double.POSITIVE_INFINITY) {
/*  9175 */       if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9176 */         return 1.0D;
/*       */       }
/*       */       
/*  9179 */       if (lowerlimit == Double.POSITIVE_INFINITY) {
/*  9180 */         return 0.0D;
/*       */       }
/*       */       
/*  9183 */       return 1.0D - normalCDF(mean, sd, lowerlimit);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9188 */     if (lowerlimit == Double.NEGATIVE_INFINITY) {
/*  9189 */       if (upperlimit == Double.NEGATIVE_INFINITY) {
/*  9190 */         return 0.0D;
/*       */       }
/*       */       
/*  9193 */       return normalCDF(mean, sd, upperlimit);
/*       */     }
/*       */     
/*       */ 
/*  9197 */     double arg1 = (lowerlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9198 */     double arg2 = (upperlimit - mean) / (sd * Math.sqrt(2.0D));
/*  9199 */     return (erf(arg2) - erf(arg1)) / 2.0D;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double gaussianInverseCDF(double mean, double sd, double prob)
/*       */   {
/*  9206 */     if ((prob < 0.0D) || (prob > 1.0D)) { throw new IllegalArgumentException("Entered cdf value, " + prob + ", must lie between 0 and 1 inclusive");
/*       */     }
/*  9208 */     double ran = 0.0D;
/*       */     
/*  9210 */     if (prob == 0.0D) {
/*  9211 */       ran = Double.NEGATIVE_INFINITY;
/*       */ 
/*       */     }
/*  9214 */     else if (prob == 1.0D) {
/*  9215 */       ran = Double.POSITIVE_INFINITY;
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*  9220 */       GaussianFunct gauss = new GaussianFunct();
/*       */       
/*       */ 
/*  9223 */       gauss.mean = mean;
/*  9224 */       gauss.sd = sd;
/*       */       
/*       */ 
/*  9227 */       double tolerance = 1.0E-12D;
/*       */       
/*       */ 
/*  9230 */       double lowerBound = mean - 10.0D * sd;
/*       */       
/*       */ 
/*  9233 */       double upperBound = mean + 10.0D * sd;
/*       */       
/*       */ 
/*  9236 */       RealRoot realR = new RealRoot();
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  9242 */       realR.setTolerance(tolerance);
/*       */       
/*       */ 
/*  9245 */       realR.resetNaNexceptionToTrue();
/*  9246 */       realR.supressLimitReachedMessage();
/*  9247 */       realR.supressNaNmessage();
/*       */       
/*       */ 
/*  9250 */       gauss.cfd = prob;
/*       */       
/*       */ 
/*  9253 */       ran = realR.bisect(gauss, lowerBound, upperBound);
/*       */     }
/*       */     
/*       */ 
/*  9257 */     return ran;
/*       */   }
/*       */   
/*       */   public static double inverseGaussianCDF(double mean, double sd, double prob)
/*       */   {
/*  9262 */     return gaussianInverseCDF(mean, sd, prob);
/*       */   }
/*       */   
/*       */   public static double normalInverseCDF(double mean, double sd, double prob)
/*       */   {
/*  9267 */     return gaussianInverseCDF(mean, sd, prob);
/*       */   }
/*       */   
/*       */   public static double inverseNormalCDF(double mean, double sd, double prob)
/*       */   {
/*  9272 */     return gaussianInverseCDF(mean, sd, prob);
/*       */   }
/*       */   
/*       */   public static double[] gaussianOrderStatisticMedians(double mean, double sigma, int n)
/*       */   {
/*  9277 */     double nn = n;
/*  9278 */     double[] gosm = new double[n];
/*  9279 */     double[] uosm = uniformOrderStatisticMedians(n);
/*  9280 */     for (int i = 0; i < n; i++) {
/*  9281 */       gosm[i] = inverseGaussianCDF(mean, sigma, uosm[i]);
/*       */     }
/*  9283 */     gosm = scale(gosm, mean, sigma);
/*  9284 */     return gosm;
/*       */   }
/*       */   
/*       */   public static double[] normalOrderStatisticMedians(double mean, double sigma, int n) {
/*  9288 */     return gaussianOrderStatisticMedians(mean, sigma, n);
/*       */   }
/*       */   
/*       */   public static double[] gaussianOrderStatisticMedians(int n)
/*       */   {
/*  9293 */     return gaussianOrderStatisticMedians(0.0D, 1.0D, n);
/*       */   }
/*       */   
/*       */   public static double[] normalOrderStatisticMedians(int n) {
/*  9297 */     return gaussianOrderStatisticMedians(0.0D, 1.0D, n);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double normalPDF(double mean, double sd, double x)
/*       */   {
/*  9305 */     return Math.exp(-Fmath.square((x - mean) / sd) / 2.0D) / (sd * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double normal(double mean, double sd, double x)
/*       */   {
/*  9311 */     return Math.exp(-Fmath.square((x - mean) / sd) / 2.0D) / (sd * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gaussianPDF(double mean, double sd, double x)
/*       */   {
/*  9317 */     return Math.exp(-Fmath.square((x - mean) / sd) / 2.0D) / (sd * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */   public static double gaussian(double mean, double sd, double x)
/*       */   {
/*  9322 */     return Math.exp(-Fmath.square((x - mean) / sd) / 2.0D) / (sd * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] normalRand(double mean, double sd, int n)
/*       */   {
/*  9328 */     double[] ran = new double[n];
/*  9329 */     Random rr = new Random();
/*  9330 */     for (int i = 0; i < n; i++) {
/*  9331 */       ran[i] = rr.nextGaussian();
/*       */     }
/*  9333 */     ran = standardize(ran);
/*  9334 */     for (int i = 0; i < n; i++) {
/*  9335 */       ran[i] = (ran[i] * sd + mean);
/*       */     }
/*  9337 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gaussianRand(double mean, double sd, int n)
/*       */   {
/*  9343 */     double[] ran = new double[n];
/*  9344 */     Random rr = new Random();
/*  9345 */     for (int i = 0; i < n; i++) {
/*  9346 */       ran[i] = rr.nextGaussian();
/*       */     }
/*  9348 */     ran = standardize(ran);
/*  9349 */     for (int i = 0; i < n; i++) {
/*  9350 */       ran[i] = (ran[i] * sd + mean);
/*       */     }
/*  9352 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] normalRand(double mean, double sd, int n, long seed)
/*       */   {
/*  9358 */     double[] ran = new double[n];
/*  9359 */     Random rr = new Random(seed);
/*  9360 */     for (int i = 0; i < n; i++) {
/*  9361 */       ran[i] = rr.nextGaussian();
/*       */     }
/*  9363 */     ran = standardize(ran);
/*  9364 */     for (int i = 0; i < n; i++) {
/*  9365 */       ran[i] = (ran[i] * sd + mean);
/*       */     }
/*  9367 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gaussianRand(double mean, double sd, int n, long seed)
/*       */   {
/*  9373 */     double[] ran = new double[n];
/*  9374 */     Random rr = new Random(seed);
/*  9375 */     for (int i = 0; i < n; i++) {
/*  9376 */       ran[i] = rr.nextGaussian();
/*       */     }
/*  9378 */     ran = standardize(ran);
/*  9379 */     for (int i = 0; i < n; i++) {
/*  9380 */       ran[i] = (ran[i] * sd + mean);
/*       */     }
/*  9382 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logNormalCDF(double mu, double sigma, double upperLimit)
/*       */   {
/*  9395 */     if (sigma < 0.0D) throw new IllegalArgumentException("The parameter sigma, " + sigma + ", must be greater than or equal to zero");
/*  9396 */     if (upperLimit <= 0.0D) {
/*  9397 */       return 0.0D;
/*       */     }
/*       */     
/*  9400 */     return 0.5D * (1.0D + erf((Math.log(upperLimit) - mu) / (sigma * Math.sqrt(2.0D))));
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParCDF(double mu, double sigma, double upperLimit)
/*       */   {
/*  9405 */     return logNormalCDF(mu, sigma, upperLimit);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double logNormalCDF(double mu, double sigma, double lowerLimit, double upperLimit)
/*       */   {
/*  9412 */     if (sigma < 0.0D) throw new IllegalArgumentException("The parameter sigma, " + sigma + ", must be greater than or equal to zero");
/*  9413 */     if (upperLimit < lowerLimit) { throw new IllegalArgumentException("The upper limit, " + upperLimit + ", must be greater than the " + lowerLimit);
/*       */     }
/*  9415 */     double arg1 = 0.0D;
/*  9416 */     double arg2 = 0.0D;
/*  9417 */     double cdf = 0.0D;
/*       */     
/*  9419 */     if (lowerLimit != upperLimit) {
/*  9420 */       if (upperLimit > 0.0D) arg1 = 0.5D * (1.0D + erf((Math.log(upperLimit) - mu) / (sigma * Math.sqrt(2.0D))));
/*  9421 */       if (lowerLimit > 0.0D) arg2 = 0.5D * (1.0D + erf((Math.log(lowerLimit) - mu) / (sigma * Math.sqrt(2.0D))));
/*  9422 */       cdf = arg1 - arg2;
/*       */     }
/*       */     
/*  9425 */     return cdf;
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParCDF(double mu, double sigma, double lowerLimit, double upperLimit) {
/*  9429 */     return logNormalCDF(mu, sigma, lowerLimit, upperLimit);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double logNormalPDF(double mu, double sigma, double x)
/*       */   {
/*  9435 */     if (sigma < 0.0D) throw new IllegalArgumentException("The parameter sigma, " + sigma + ", must be greater than or equal to zero");
/*  9436 */     if (x < 0.0D) {
/*  9437 */       return 0.0D;
/*       */     }
/*       */     
/*  9440 */     return Math.exp(-0.5D * Fmath.square((Math.log(x) - mu) / sigma)) / (x * sigma * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParPDF(double mu, double sigma, double x)
/*       */   {
/*  9445 */     return logNormalPDF(mu, sigma, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double logNormalMean(double mu, double sigma)
/*       */   {
/*  9451 */     return Math.exp(mu + sigma * sigma / 2.0D);
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParMean(double mu, double sigma) {
/*  9455 */     return Math.exp(mu + sigma * sigma / 2.0D);
/*       */   }
/*       */   
/*       */   public static double logNormalStandardDeviation(double mu, double sigma)
/*       */   {
/*  9460 */     return logNormalStandDev(mu, sigma);
/*       */   }
/*       */   
/*       */   public static double logNormalStandDev(double mu, double sigma)
/*       */   {
/*  9465 */     double sigma2 = sigma * sigma;
/*  9466 */     return Math.sqrt((Math.exp(sigma2) - 1.0D) * Math.exp(2.0D * mu + sigma2));
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParStandardDeviation(double mu, double sigma) {
/*  9470 */     return logNormalTwoParStandDev(mu, sigma);
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParStandDev(double mu, double sigma) {
/*  9474 */     double sigma2 = sigma * sigma;
/*  9475 */     return Math.sqrt((Math.exp(sigma2) - 1.0D) * Math.exp(2.0D * mu + sigma2));
/*       */   }
/*       */   
/*       */   public static double logNormalMode(double mu, double sigma)
/*       */   {
/*  9480 */     return Math.exp(mu - sigma * sigma);
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParMode(double mu, double sigma) {
/*  9484 */     return Math.exp(mu - sigma * sigma);
/*       */   }
/*       */   
/*       */   public static double logNormalMedian(double mu)
/*       */   {
/*  9489 */     return Math.exp(mu);
/*       */   }
/*       */   
/*       */   public static double logNormalTwoParMedian(double mu) {
/*  9493 */     return Math.exp(mu);
/*       */   }
/*       */   
/*       */   public static double[] logNormalRand(double mu, double sigma, int n)
/*       */   {
/*  9498 */     if (n <= 0) throw new IllegalArgumentException("The number of random deviates required, " + n + ", must be greater than zero");
/*  9499 */     if (sigma < 0.0D) throw new IllegalArgumentException("The parameter sigma, " + sigma + ", must be greater than or equal to zero");
/*  9500 */     PsRandom psr = new PsRandom();
/*  9501 */     return psr.logNormalArray(mu, sigma, n);
/*       */   }
/*       */   
/*       */   public static double[] logNormalTwoParRand(double mu, double sigma, int n) {
/*  9505 */     return logNormalRand(mu, sigma, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] logNormalRand(double mu, double sigma, int n, long seed)
/*       */   {
/*  9511 */     if (n <= 0) throw new IllegalArgumentException("The number of random deviates required, " + n + ", must be greater than zero");
/*  9512 */     if (sigma < 0.0D) throw new IllegalArgumentException("The parameter sigma, " + sigma + ", must be greater than or equal to zero");
/*  9513 */     PsRandom psr = new PsRandom(seed);
/*  9514 */     return psr.logNormalArray(mu, sigma, n);
/*       */   }
/*       */   
/*       */   public static double[] logNormalTwoParRand(double mu, double sigma, int n, long seed) {
/*  9518 */     return logNormalRand(mu, sigma, n, seed);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logNormalThreeParCDF(double alpha, double beta, double gamma, double upperLimit)
/*       */   {
/*  9526 */     if (beta < 0.0D) throw new IllegalArgumentException("The parameter beta, " + beta + ", must be greater than or equal to zero");
/*  9527 */     if (upperLimit <= 0.0D) {
/*  9528 */       return 0.0D;
/*       */     }
/*       */     
/*  9531 */     return 0.5D * (1.0D + erf(Math.log((upperLimit - alpha) / gamma) / (beta * Math.sqrt(2.0D))));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logNormalThreeParCDF(double alpha, double beta, double gamma, double lowerLimit, double upperLimit)
/*       */   {
/*  9539 */     if (beta < 0.0D) throw new IllegalArgumentException("The parameter beta, " + beta + ", must be greater than or equal to zero");
/*  9540 */     if (upperLimit < lowerLimit) { throw new IllegalArgumentException("The upper limit, " + upperLimit + ", must be greater than the " + lowerLimit);
/*       */     }
/*  9542 */     double arg1 = 0.0D;
/*  9543 */     double arg2 = 0.0D;
/*  9544 */     double cdf = 0.0D;
/*       */     
/*  9546 */     if (lowerLimit != upperLimit) {
/*  9547 */       if (upperLimit > 0.0D) arg1 = 0.5D * (1.0D + erf(Math.log((upperLimit - alpha) / gamma) / (beta * Math.sqrt(2.0D))));
/*  9548 */       if (lowerLimit > 0.0D) arg2 = 0.5D * (1.0D + erf(Math.log((lowerLimit - alpha) / gamma) / (beta * Math.sqrt(2.0D))));
/*  9549 */       cdf = arg1 - arg2;
/*       */     }
/*       */     
/*  9552 */     return cdf;
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParPDF(double alpha, double beta, double gamma, double x)
/*       */   {
/*  9557 */     if (beta < 0.0D) throw new IllegalArgumentException("The parameter beta, " + beta + ", must be greater than or equal to zero");
/*  9558 */     if (x < 0.0D) {
/*  9559 */       return 0.0D;
/*       */     }
/*       */     
/*  9562 */     return Math.exp(-0.5D * Fmath.square(Math.log((x - alpha) / gamma) / beta)) / ((x - gamma) * beta * Math.sqrt(6.283185307179586D));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double[] logNormalThreeParRand(double alpha, double beta, double gamma, int n)
/*       */   {
/*  9569 */     if (n <= 0) throw new IllegalArgumentException("The number of random deviates required, " + n + ", must be greater than zero");
/*  9570 */     if (beta < 0.0D) throw new IllegalArgumentException("The parameter beta, " + beta + ", must be greater than or equal to zero");
/*  9571 */     PsRandom psr = new PsRandom();
/*  9572 */     return psr.logNormalThreeParArray(alpha, beta, gamma, n);
/*       */   }
/*       */   
/*       */   public static double[] logNormalThreeParRand(double alpha, double beta, double gamma, int n, long seed)
/*       */   {
/*  9577 */     if (n <= 0) throw new IllegalArgumentException("The number of random deviates required, " + n + ", must be greater than zero");
/*  9578 */     if (beta < 0.0D) throw new IllegalArgumentException("The parameter beta, " + beta + ", must be greater than or equal to zero");
/*  9579 */     PsRandom psr = new PsRandom(seed);
/*  9580 */     return psr.logNormalThreeParArray(alpha, beta, gamma, n);
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParMean(double alpha, double beta, double gamma)
/*       */   {
/*  9585 */     return gamma * Math.exp(beta * beta / 2.0D) + alpha;
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParStandardDeviation(double alpha, double beta, double gamma)
/*       */   {
/*  9590 */     return logNormalThreeParStandDev(alpha, beta, gamma);
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParStandDev(double alpha, double beta, double gamma)
/*       */   {
/*  9595 */     double beta2 = beta * beta;
/*  9596 */     return Math.sqrt((Math.exp(beta2) - 1.0D) * Math.exp(2.0D * Math.log(gamma) + beta2));
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParMode(double alpha, double beta, double gamma)
/*       */   {
/*  9601 */     return gamma * Math.exp(-beta * beta) + alpha;
/*       */   }
/*       */   
/*       */   public static double logNormalThreeParMedian(double alpha, double gamma)
/*       */   {
/*  9606 */     return gamma + alpha;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logisticCDF(double mu, double beta, double upperlimit)
/*       */   {
/*  9616 */     return 0.5D * (1.0D + Math.tanh((upperlimit - mu) / (2.0D * beta)));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logisticProb(double mu, double beta, double upperlimit)
/*       */   {
/*  9624 */     return 0.5D * (1.0D + Math.tanh((upperlimit - mu) / (2.0D * beta)));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double logisticCDF(double mu, double beta, double lowerlimit, double upperlimit)
/*       */   {
/*  9632 */     double arg1 = 0.5D * (1.0D + Math.tanh((lowerlimit - mu) / (2.0D * beta)));
/*  9633 */     double arg2 = 0.5D * (1.0D + Math.tanh((upperlimit - mu) / (2.0D * beta)));
/*  9634 */     return arg2 - arg1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double logisticProb(double mu, double beta, double lowerlimit, double upperlimit)
/*       */   {
/*  9641 */     double arg1 = 0.5D * (1.0D + Math.tanh((lowerlimit - mu) / (2.0D * beta)));
/*  9642 */     double arg2 = 0.5D * (1.0D + Math.tanh((upperlimit - mu) / (2.0D * beta)));
/*  9643 */     return arg2 - arg1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double logisticPDF(double mu, double beta, double x)
/*       */   {
/*  9649 */     return Fmath.square(Fmath.sech((x - mu) / (2.0D * beta))) / (4.0D * beta);
/*       */   }
/*       */   
/*       */   public static double logistic(double mu, double beta, double x)
/*       */   {
/*  9654 */     return Fmath.square(Fmath.sech((x - mu) / (2.0D * beta))) / (4.0D * beta);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] logisticRand(double mu, double beta, int n)
/*       */   {
/*  9660 */     double[] ran = new double[n];
/*  9661 */     Random rr = new Random();
/*  9662 */     for (int i = 0; i < n; i++) {
/*  9663 */       ran[i] = (2.0D * beta * Fmath.atanh(2.0D * rr.nextDouble() - 1.0D) + mu);
/*       */     }
/*  9665 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] logisticRand(double mu, double beta, int n, long seed)
/*       */   {
/*  9671 */     double[] ran = new double[n];
/*  9672 */     Random rr = new Random(seed);
/*  9673 */     for (int i = 0; i < n; i++) {
/*  9674 */       ran[i] = (2.0D * beta * Fmath.atanh(2.0D * rr.nextDouble() - 1.0D) + mu);
/*       */     }
/*  9676 */     return ran;
/*       */   }
/*       */   
/*       */   public static double logisticMean(double mu)
/*       */   {
/*  9681 */     return mu;
/*       */   }
/*       */   
/*       */   public static double logisticStandardDeviation(double beta)
/*       */   {
/*  9686 */     return logisticStandDev(beta);
/*       */   }
/*       */   
/*       */   public static double logisticStandDev(double beta)
/*       */   {
/*  9691 */     return Math.sqrt(Fmath.square(3.141592653589793D * beta) / 3.0D);
/*       */   }
/*       */   
/*       */   public static double logisticMode(double mu)
/*       */   {
/*  9696 */     return mu;
/*       */   }
/*       */   
/*       */   public static double logisticMedian(double mu)
/*       */   {
/*  9701 */     return mu;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double lorentzianProb(double mu, double gamma, double upperlimit)
/*       */   {
/*  9710 */     double arg = (upperlimit - mu) / (gamma / 2.0D);
/*  9711 */     return 0.3183098861837907D * (Math.atan(arg) + 1.5707963267948966D);
/*       */   }
/*       */   
/*       */   public static double lorentzianCDF(double mu, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/*  9716 */     double arg1 = (upperlimit - mu) / (gamma / 2.0D);
/*  9717 */     double arg2 = (lowerlimit - mu) / (gamma / 2.0D);
/*  9718 */     return 0.3183098861837907D * (Math.atan(arg1) - Math.atan(arg2));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double lorentzianProb(double mu, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/*  9724 */     double arg1 = (upperlimit - mu) / (gamma / 2.0D);
/*  9725 */     double arg2 = (lowerlimit - mu) / (gamma / 2.0D);
/*  9726 */     return 0.3183098861837907D * (Math.atan(arg1) - Math.atan(arg2));
/*       */   }
/*       */   
/*       */   public static double lorentzianPDF(double mu, double gamma, double x)
/*       */   {
/*  9731 */     double arg = gamma / 2.0D;
/*  9732 */     return 0.3183098861837907D * arg / (Fmath.square(mu - x) + arg * arg);
/*       */   }
/*       */   
/*       */   public static double lorentzian(double mu, double gamma, double x)
/*       */   {
/*  9737 */     double arg = gamma / 2.0D;
/*  9738 */     return 0.3183098861837907D * arg / (Fmath.square(mu - x) + arg * arg);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double[] lorentzianRand(double mu, double gamma, int n)
/*       */   {
/*  9745 */     double[] ran = new double[n];
/*  9746 */     Random rr = new Random();
/*  9747 */     for (int i = 0; i < n; i++) {
/*  9748 */       ran[i] = Math.tan((rr.nextDouble() - 0.5D) * 3.141592653589793D);
/*  9749 */       ran[i] = (ran[i] * gamma / 2.0D + mu);
/*       */     }
/*  9751 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] lorentzianRand(double mu, double gamma, int n, long seed)
/*       */   {
/*  9757 */     double[] ran = new double[n];
/*  9758 */     Random rr = new Random(seed);
/*  9759 */     for (int i = 0; i < n; i++) {
/*  9760 */       ran[i] = Math.tan((rr.nextDouble() - 0.5D) * 3.141592653589793D);
/*  9761 */       ran[i] = (ran[i] * gamma / 2.0D + mu);
/*       */     }
/*  9763 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double poissonCDF(int k, double mean)
/*       */   {
/*  9774 */     if (k < 1) throw new IllegalArgumentException("k must be an integer greater than or equal to 1");
/*  9775 */     return incompleteGammaComplementary(k, mean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double poissonProb(int k, double mean)
/*       */   {
/*  9783 */     if (k < 1) throw new IllegalArgumentException("k must be an integer greater than or equal to 1");
/*  9784 */     return incompleteGammaComplementary(k, mean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double poissonPDF(int k, double mean)
/*       */   {
/*  9791 */     if (k < 0) throw new IllegalArgumentException("k must be an integer greater than or equal to 0");
/*  9792 */     return Math.pow(mean, k) * Math.exp(-mean) / factorial(k);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double poisson(int k, double mean)
/*       */   {
/*  9799 */     if (k < 0) throw new IllegalArgumentException("k must be an integer greater than or equal to 0");
/*  9800 */     return Math.pow(mean, k) * Math.exp(-mean) / factorial(k);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] poissonRand(double mean, int n)
/*       */   {
/*  9808 */     Random rr = new Random();
/*  9809 */     double[] ran = poissonRandCalc(rr, mean, n);
/*  9810 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double[] poissonRand(double mean, int n, long seed)
/*       */   {
/*  9818 */     Random rr = new Random(seed);
/*  9819 */     double[] ran = poissonRandCalc(rr, mean, n);
/*  9820 */     return ran;
/*       */   }
/*       */   
/*       */   private static double[] poissonRandCalc(Random rr, double mean, int n)
/*       */   {
/*  9825 */     double[] ran = new double[n];
/*  9826 */     double oldm = -1.0D;
/*  9827 */     double expt = 0.0D;
/*  9828 */     double em = 0.0D;
/*  9829 */     double term = 0.0D;
/*  9830 */     double sq = 0.0D;
/*  9831 */     double lnMean = 0.0D;
/*  9832 */     double yDev = 0.0D;
/*       */     
/*  9834 */     if (mean < 12.0D) {
/*  9835 */       for (int i = 0; i < n; i++) {
/*  9836 */         if (mean != oldm) {
/*  9837 */           oldm = mean;
/*  9838 */           expt = Math.exp(-mean);
/*       */         }
/*  9840 */         em = -1.0D;
/*  9841 */         term = 1.0D;
/*       */         do {
/*  9843 */           em += 1.0D;
/*  9844 */           term *= rr.nextDouble();
/*  9845 */         } while (term > expt);
/*  9846 */         ran[i] = em;
/*       */       }
/*       */       
/*       */     } else {
/*  9850 */       for (int i = 0; i < n; i++) {
/*  9851 */         if (mean != oldm) {
/*  9852 */           oldm = mean;
/*  9853 */           sq = Math.sqrt(2.0D * mean);
/*  9854 */           lnMean = Math.log(mean);
/*  9855 */           expt = lnMean - logGamma(mean + 1.0D);
/*       */         }
/*       */         do {
/*       */           do {
/*  9859 */             yDev = Math.tan(3.141592653589793D * rr.nextDouble());
/*  9860 */             em = sq * yDev + mean;
/*  9861 */           } while (em < 0.0D);
/*  9862 */           em = Math.floor(em);
/*  9863 */           term = 0.9D * (1.0D + yDev * yDev) * Math.exp(em * lnMean - logGamma(em + 1.0D) - expt);
/*  9864 */         } while (rr.nextDouble() > term);
/*  9865 */         ran[i] = em;
/*       */       }
/*       */     }
/*  9868 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double chiSquareCDF(double chiSquare, int nu)
/*       */   {
/*  9878 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9879 */     return incompleteGamma(nu / 2.0D, chiSquare / 2.0D);
/*       */   }
/*       */   
/*       */   public static double chiSquareProb(double chiSquare, int nu)
/*       */   {
/*  9884 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9885 */     return incompleteGamma(nu / 2.0D, chiSquare / 2.0D);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double chiSquarePDF(double chiSquare, int nu)
/*       */   {
/*  9891 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9892 */     double dnu = nu;
/*  9893 */     return Math.pow(0.5D, dnu / 2.0D) * Math.pow(chiSquare, dnu / 2.0D - 1.0D) * Math.exp(-chiSquare / 2.0D) / gammaFunction(dnu / 2.0D);
/*       */   }
/*       */   
/*       */   public static double[] chiSquareRand(int nu, int n)
/*       */   {
/*  9898 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9899 */     PsRandom psr = new PsRandom();
/*  9900 */     return psr.chiSquareArray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] chiSquareRand(int nu, int n, long seed)
/*       */   {
/*  9906 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9907 */     PsRandom psr = new PsRandom(seed);
/*  9908 */     return psr.chiSquareArray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double chiSquareMean(int nu)
/*       */   {
/*  9914 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9915 */     return nu;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double chiSquareMode(int nu)
/*       */   {
/*  9921 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9922 */     double mode = 0.0D;
/*  9923 */     if (nu >= 2) mode = nu - 2.0D;
/*  9924 */     return mode;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double chiSquareStandardDeviation(int nu)
/*       */   {
/*  9930 */     return chiSquareStandDev(nu);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double chiSquareStandDev(int nu)
/*       */   {
/*  9937 */     if (nu <= 0) throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*  9938 */     double dnu = nu;
/*  9939 */     return Math.sqrt(2.0D * dnu);
/*       */   }
/*       */   
/*       */   public static double chiSquare(double[] observed, double[] expected, double[] variance)
/*       */   {
/*  9944 */     int nObs = observed.length;
/*  9945 */     int nExp = expected.length;
/*  9946 */     int nVar = variance.length;
/*  9947 */     if (nObs != nExp) throw new IllegalArgumentException("observed array length does not equal the expected array length");
/*  9948 */     if (nObs != nVar) throw new IllegalArgumentException("observed array length does not equal the variance array length");
/*  9949 */     double chi = 0.0D;
/*  9950 */     for (int i = 0; i < nObs; i++) {
/*  9951 */       chi += Fmath.square(observed[i] - expected[i]) / variance[i];
/*       */     }
/*  9953 */     return chi;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double chiSquareFreq(double[] observedFreq, double[] expectedFreq)
/*       */   {
/*  9960 */     int nObs = observedFreq.length;
/*  9961 */     int nExp = expectedFreq.length;
/*  9962 */     if (nObs != nExp) throw new IllegalArgumentException("observed array length does not equal the expected array length");
/*  9963 */     double chi = 0.0D;
/*  9964 */     for (int i = 0; i < nObs; i++) {
/*  9965 */       chi += Fmath.square(observedFreq[i] - expectedFreq[i]) / expectedFreq[i];
/*       */     }
/*  9967 */     return chi;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double chiSquareFreq(int[] observedFreq, int[] expectedFreq)
/*       */   {
/*  9974 */     int nObs = observedFreq.length;
/*  9975 */     int nExp = expectedFreq.length;
/*  9976 */     if (nObs != nExp) throw new IllegalArgumentException("observed array length does not equal the expected array length");
/*  9977 */     double[] observ = new double[nObs];
/*  9978 */     double[] expect = new double[nObs];
/*  9979 */     for (int i = 0; i < nObs; i++) {
/*  9980 */       observ[i] = observedFreq[i];
/*  9981 */       expect[i] = expectedFreq[i];
/*       */     }
/*       */     
/*  9984 */     return chiSquareFreq(observ, expect);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double binomialCDF(double p, int n, int k)
/*       */   {
/*  9992 */     if ((p < 0.0D) || (p > 1.0D)) throw new IllegalArgumentException("\np must lie between 0 and 1");
/*  9993 */     if ((k < 0) || (n < 0)) throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
/*  9994 */     if (k > n) throw new IllegalArgumentException("\nk is greater than n");
/*  9995 */     return regularisedBetaFunction(k, n - k + 1, p);
/*       */   }
/*       */   
/*       */   public static double binomialProb(double p, int n, int k) {
/*  9999 */     if ((p < 0.0D) || (p > 1.0D)) throw new IllegalArgumentException("\np must lie between 0 and 1");
/* 10000 */     if ((k < 0) || (n < 0)) throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
/* 10001 */     if (k > n) throw new IllegalArgumentException("\nk is greater than n");
/* 10002 */     return regularisedBetaFunction(k, n - k + 1, p);
/*       */   }
/*       */   
/*       */   public static double binomialPDF(double p, int n, int k)
/*       */   {
/* 10007 */     if ((k < 0) || (n < 0)) throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
/* 10008 */     if (k > n) throw new IllegalArgumentException("\nk is greater than n");
/* 10009 */     return Math.floor(0.5D + Math.exp(logFactorial(n) - logFactorial(k) - logFactorial(n - k))) * Math.pow(p, k) * Math.pow(1.0D - p, n - k);
/*       */   }
/*       */   
/*       */   public static double binomial(double p, int n, int k)
/*       */   {
/* 10014 */     if ((k < 0) || (n < 0)) throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
/* 10015 */     if (k > n) throw new IllegalArgumentException("\nk is greater than n");
/* 10016 */     return Math.floor(0.5D + Math.exp(logFactorial(n) - logFactorial(k) - logFactorial(n - k))) * Math.pow(p, k) * Math.pow(1.0D - p, n - k);
/*       */   }
/*       */   
/*       */   public static double binomialCoeff(int n, int k)
/*       */   {
/* 10021 */     if ((k < 0) || (n < 0)) throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
/* 10022 */     if (k > n) throw new IllegalArgumentException("\nk is greater than n");
/* 10023 */     return Math.floor(0.5D + Math.exp(logFactorial(n) - logFactorial(k) - logFactorial(n - k)));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double[] binomialRand(double prob, int nTrials, int n)
/*       */   {
/* 10032 */     if (nTrials < n) throw new IllegalArgumentException("Number of deviates requested, " + n + ", must be less than the number of trials, " + nTrials);
/* 10033 */     if ((prob < 0.0D) || (prob > 1.0D)) { throw new IllegalArgumentException("The probablity provided, " + prob + ", must lie between 0 and 1)");
/*       */     }
/* 10035 */     double[] ran = new double[n];
/* 10036 */     Random rr = new Random();
/*       */     
/* 10038 */     double binomialDeviate = 0.0D;
/* 10039 */     double deviateMean = 0.0D;
/* 10040 */     double testDeviate = 0.0D;
/* 10041 */     double workingProb = 0.0D;
/* 10042 */     double logProb = 0.0D;
/* 10043 */     double probOld = -1.0D;
/* 10044 */     double probC = -1.0D;
/* 10045 */     double logProbC = -1.0D;
/* 10046 */     int nOld = -1;
/* 10047 */     double enTrials = 0.0D;
/* 10048 */     double oldGamma = 0.0D;
/* 10049 */     double tanW = 0.0D;
/* 10050 */     double hold0 = 0.0D;
/*       */     
/*       */ 
/* 10053 */     double probOriginalValue = prob;
/* 10054 */     for (int i = 0; i < n; i++) {
/* 10055 */       prob = probOriginalValue;
/* 10056 */       workingProb = prob <= 0.5D ? prob : 1.0D - prob;
/* 10057 */       deviateMean = nTrials * workingProb;
/*       */       
/* 10059 */       if (nTrials < 25)
/*       */       {
/* 10061 */         binomialDeviate = 0.0D;
/* 10062 */         for (int jj = 1; jj <= nTrials; jj++) if (rr.nextDouble() < workingProb) binomialDeviate += 1.0D;
/*       */       }
/* 10064 */       if (deviateMean < 1.0D)
/*       */       {
/* 10066 */         double expOfMean = Math.exp(-deviateMean);
/* 10067 */         testDeviate = 1.0D;
/* 10068 */         for (int jj = 0; jj <= nTrials; jj++) {
/* 10069 */           testDeviate *= rr.nextDouble();
/* 10070 */           if (testDeviate < expOfMean) break;
/*       */         }
/* 10072 */         binomialDeviate = jj <= nTrials ? jj : nTrials;
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10077 */         if (nTrials != nOld)
/*       */         {
/* 10079 */           enTrials = nTrials;
/* 10080 */           oldGamma = logGamma(enTrials + 1.0D);
/* 10081 */           nOld = nTrials;
/*       */         }
/* 10083 */         if (workingProb != probOld)
/*       */         {
/* 10085 */           probC = 1.0D - workingProb;
/* 10086 */           logProb = Math.log(workingProb);
/* 10087 */           logProbC = Math.log(probC);
/* 10088 */           probOld = workingProb;
/*       */         }
/*       */         
/* 10091 */         double sq = Math.sqrt(2.0D * deviateMean * probC);
/*       */         do {
/*       */           do {
/* 10094 */             double angle = 3.141592653589793D * rr.nextDouble();
/* 10095 */             tanW = Math.tan(angle);
/* 10096 */             hold0 = sq * tanW + deviateMean;
/* 10097 */           } while ((hold0 < 0.0D) || (hold0 >= enTrials + 1.0D));
/* 10098 */           hold0 = Math.floor(hold0);
/* 10099 */           testDeviate = 1.2D * sq * (1.0D + tanW * tanW) * Math.exp(oldGamma - logGamma(hold0 + 1.0D) - logGamma(enTrials - hold0 + 1.0D) + hold0 * logProb + (enTrials - hold0) * logProbC);
/* 10100 */         } while (rr.nextDouble() > testDeviate);
/* 10101 */         binomialDeviate = hold0;
/*       */       }
/*       */       
/* 10104 */       if (workingProb != prob) { binomialDeviate = nTrials - binomialDeviate;
/*       */       }
/* 10106 */       ran[i] = binomialDeviate;
/*       */     }
/*       */     
/* 10109 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double[] binomialRand(double prob, int nTrials, int n, long seed)
/*       */   {
/* 10118 */     if (nTrials < n) throw new IllegalArgumentException("Number of deviates requested, " + n + ", must be less than the number of trials, " + nTrials);
/* 10119 */     if ((prob < 0.0D) || (prob > 1.0D)) { throw new IllegalArgumentException("The probablity provided, " + prob + ", must lie between 0 and 1)");
/*       */     }
/* 10121 */     double[] ran = new double[n];
/* 10122 */     Random rr = new Random(seed);
/*       */     
/* 10124 */     double binomialDeviate = 0.0D;
/* 10125 */     double deviateMean = 0.0D;
/* 10126 */     double testDeviate = 0.0D;
/* 10127 */     double workingProb = 0.0D;
/* 10128 */     double logProb = 0.0D;
/* 10129 */     double probOld = -1.0D;
/* 10130 */     double probC = -1.0D;
/* 10131 */     double logProbC = -1.0D;
/* 10132 */     int nOld = -1;
/* 10133 */     double enTrials = 0.0D;
/* 10134 */     double oldGamma = 0.0D;
/* 10135 */     double tanW = 0.0D;
/* 10136 */     double hold0 = 0.0D;
/*       */     
/*       */ 
/* 10139 */     double probOriginalValue = prob;
/* 10140 */     for (int i = 0; i < n; i++) {
/* 10141 */       prob = probOriginalValue;
/* 10142 */       workingProb = prob <= 0.5D ? prob : 1.0D - prob;
/* 10143 */       deviateMean = nTrials * workingProb;
/*       */       
/* 10145 */       if (nTrials < 25)
/*       */       {
/* 10147 */         binomialDeviate = 0.0D;
/* 10148 */         for (int jj = 1; jj <= nTrials; jj++) if (rr.nextDouble() < workingProb) binomialDeviate += 1.0D;
/*       */       }
/* 10150 */       if (deviateMean < 1.0D)
/*       */       {
/* 10152 */         double expOfMean = Math.exp(-deviateMean);
/* 10153 */         testDeviate = 1.0D;
/* 10154 */         for (int jj = 0; jj <= nTrials; jj++) {
/* 10155 */           testDeviate *= rr.nextDouble();
/* 10156 */           if (testDeviate < expOfMean) break;
/*       */         }
/* 10158 */         binomialDeviate = jj <= nTrials ? jj : nTrials;
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/* 10163 */         if (nTrials != nOld)
/*       */         {
/* 10165 */           enTrials = nTrials;
/* 10166 */           oldGamma = logGamma(enTrials + 1.0D);
/* 10167 */           nOld = nTrials;
/*       */         }
/* 10169 */         if (workingProb != probOld)
/*       */         {
/* 10171 */           probC = 1.0D - workingProb;
/* 10172 */           logProb = Math.log(workingProb);
/* 10173 */           logProbC = Math.log(probC);
/* 10174 */           probOld = workingProb;
/*       */         }
/*       */         
/* 10177 */         double sq = Math.sqrt(2.0D * deviateMean * probC);
/*       */         do {
/*       */           do {
/* 10180 */             double angle = 3.141592653589793D * rr.nextDouble();
/* 10181 */             tanW = Math.tan(angle);
/* 10182 */             hold0 = sq * tanW + deviateMean;
/* 10183 */           } while ((hold0 < 0.0D) || (hold0 >= enTrials + 1.0D));
/* 10184 */           hold0 = Math.floor(hold0);
/* 10185 */           testDeviate = 1.2D * sq * (1.0D + tanW * tanW) * Math.exp(oldGamma - logGamma(hold0 + 1.0D) - logGamma(enTrials - hold0 + 1.0D) + hold0 * logProb + (enTrials - hold0) * logProbC);
/* 10186 */         } while (rr.nextDouble() > testDeviate);
/* 10187 */         binomialDeviate = hold0;
/*       */       }
/*       */       
/* 10190 */       if (workingProb != prob) { binomialDeviate = nTrials - binomialDeviate;
/*       */       }
/* 10192 */       ran[i] = binomialDeviate;
/*       */     }
/*       */     
/* 10195 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double fCompCDF(double fValue, int df1, int df2)
/*       */   {
/* 10204 */     if (df1 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu1, " + df1 + ", must be greater than zero");
/* 10205 */     if (df2 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu2, " + df2 + ", must be greater than zero");
/* 10206 */     if (fValue < 0.0D) throw new IllegalArgumentException("the F-ratio, " + fValue + ", must be greater than or equal to zero");
/* 10207 */     double ddf1 = df1;
/* 10208 */     double ddf2 = df2;
/* 10209 */     double x = ddf2 / (ddf2 + ddf1 * fValue);
/* 10210 */     return regularisedBetaFunction(df2 / 2.0D, df1 / 2.0D, x);
/*       */   }
/*       */   
/*       */   public static double fTestProb(double fValue, int df1, int df2)
/*       */   {
/* 10215 */     if (df1 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu1, " + df1 + ", must be greater than zero");
/* 10216 */     if (df2 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu2, " + df2 + ", must be greater than zero");
/* 10217 */     if (fValue < 0.0D) throw new IllegalArgumentException("the F-ratio, " + fValue + ", must be greater than or equal to zero");
/* 10218 */     double ddf1 = df1;
/* 10219 */     double ddf2 = df2;
/* 10220 */     double x = ddf2 / (ddf2 + ddf1 * fValue);
/* 10221 */     return regularisedBetaFunction(df2 / 2.0D, df1 / 2.0D, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double fCompCDF(double var1, int df1, double var2, int df2)
/*       */   {
/* 10227 */     if (df1 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu1, " + df1 + ", must be greater than zero");
/* 10228 */     if (df2 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu2, " + df2 + ", must be greater than zero");
/* 10229 */     if (var1 < 0.0D) throw new IllegalArgumentException("the variance, var1" + var1 + ", must be greater than or equal to zero");
/* 10230 */     if (var1 <= 0.0D) throw new IllegalArgumentException("the variance, var2" + var2 + ", must be greater than zero");
/* 10231 */     double fValue = var1 / var2;
/* 10232 */     double ddf1 = df1;
/* 10233 */     double ddf2 = df2;
/* 10234 */     double x = ddf2 / (ddf2 + ddf1 * fValue);
/* 10235 */     return regularisedBetaFunction(df2 / 2.0D, df1 / 2.0D, x);
/*       */   }
/*       */   
/*       */   public static double fTestProb(double var1, int df1, double var2, int df2)
/*       */   {
/* 10240 */     if (df1 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu1, " + df1 + ", must be greater than zero");
/* 10241 */     if (df2 <= 0) throw new IllegalArgumentException("the degrees of freedom, nu2, " + df2 + ", must be greater than zero");
/* 10242 */     if (var1 < 0.0D) throw new IllegalArgumentException("the variance, var1" + var1 + ", must be greater than or equal to zero");
/* 10243 */     if (var1 <= 0.0D) throw new IllegalArgumentException("the variance, var2" + var2 + ", must be greater than zero");
/* 10244 */     double fValue = var1 / var2;
/* 10245 */     double ddf1 = df1;
/* 10246 */     double ddf2 = df2;
/* 10247 */     double x = ddf2 / (ddf2 + ddf1 * fValue);
/* 10248 */     return regularisedBetaFunction(df2 / 2.0D, df1 / 2.0D, x);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double fTestValueGivenFprob(double fProb, int df1, int df2)
/*       */   {
/* 10256 */     int fTestsNum = 100;
/* 10257 */     double[] fTestValues = new double[fTestsNum];
/* 10258 */     fTestValues[0] = 1.0E-4D;
/* 10259 */     fTestValues[(fTestsNum - 1)] = 10000.0D;
/*       */     
/* 10261 */     double diff = (Fmath.log10(fTestValues[(fTestsNum - 1)]) - Fmath.log10(fTestValues[0])) / (fTestsNum - 1);
/*       */     
/* 10263 */     for (int i = 1; i < fTestsNum - 1; i++) {
/* 10264 */       fTestValues[i] = Math.pow(10.0D, Fmath.log10(fTestValues[(i - 1)]) + diff);
/*       */     }
/*       */     
/*       */ 
/* 10268 */     double[] fTestProb = new double[fTestsNum];
/* 10269 */     for (int i = 0; i < fTestsNum; i++) {
/* 10270 */       fTestProb[i] = fTestProb(fTestValues[i], df1, df2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/* 10275 */     double fTest0 = 0.0D;
/* 10276 */     double fTest1 = 0.0D;
/* 10277 */     double fTest2 = 0.0D;
/*       */     
/*       */ 
/* 10280 */     boolean test0 = true;
/* 10281 */     boolean test1 = true;
/* 10282 */     int i = 0;
/* 10283 */     int endTest = 0;
/* 10284 */     while (test0) {
/* 10285 */       if (fProb == fTestProb[i]) {
/* 10286 */         fTest0 = fTestValues[i];
/* 10287 */         test0 = false;
/* 10288 */         test1 = false;
/*       */ 
/*       */       }
/* 10291 */       else if (fProb > fTestProb[i]) {
/* 10292 */         test0 = false;
/* 10293 */         if (i > 0) {
/* 10294 */           fTest1 = fTestValues[(i - 1)];
/* 10295 */           fTest2 = fTestValues[i];
/* 10296 */           endTest = -1;
/*       */         }
/*       */         else {
/* 10299 */           fTest1 = fTestValues[i] / 10.0D;
/* 10300 */           fTest2 = fTestValues[i];
/*       */         }
/*       */       }
/*       */       else {
/* 10304 */         i++;
/* 10305 */         if (i > fTestsNum - 1) {
/* 10306 */           test0 = false;
/* 10307 */           fTest1 = fTestValues[(i - 1)];
/* 10308 */           fTest2 = 10.0D * fTestValues[(i - 1)];
/* 10309 */           endTest = 1;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*       */ 
/* 10316 */     if (test1) { fTest0 = fTestBisect(fProb, fTest1, fTest2, df1, df2, endTest);
/*       */     }
/* 10318 */     return fTest0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private static double fTestBisect(double fProb, double fTestLow, double fTestHigh, int df1, int df2, int endTest)
/*       */   {
/* 10325 */     double funcLow = fProb - fTestProb(fTestLow, df1, df2);
/* 10326 */     double funcHigh = fProb - fTestProb(fTestHigh, df1, df2);
/* 10327 */     double fTestMid = 0.0D;
/* 10328 */     double funcMid = 0.0D;
/* 10329 */     int nExtensions = 0;
/* 10330 */     int nIter = 1000;
/* 10331 */     double check = fProb * 1.0E-6D;
/* 10332 */     boolean test0 = true;
/* 10333 */     boolean test1 = true;
/* 10334 */     while (test0) {
/* 10335 */       if (funcLow * funcHigh > 0.0D) {
/* 10336 */         if (endTest < 0) {
/* 10337 */           nExtensions++;
/* 10338 */           if (nExtensions > 100) {
/* 10339 */             System.out.println("Class: Stats\nMethod: fTestBisect\nProbability higher than range covered\nF-test value is less than " + fTestLow);
/* 10340 */             System.out.println("This value was returned");
/* 10341 */             fTestMid = fTestLow;
/* 10342 */             test0 = false;
/* 10343 */             test1 = false;
/*       */           }
/* 10345 */           fTestLow /= 10.0D;
/* 10346 */           funcLow = fProb - fTestProb(fTestLow, df1, df2);
/*       */         }
/*       */         else {
/* 10349 */           nExtensions++;
/* 10350 */           if (nExtensions > 100) {
/* 10351 */             System.out.println("Class: Stats\nMethod: fTestBisect\nProbability lower than range covered\nF-test value is greater than " + fTestHigh);
/* 10352 */             System.out.println("This value was returned");
/* 10353 */             fTestMid = fTestHigh;
/* 10354 */             test0 = false;
/* 10355 */             test1 = false;
/*       */           }
/* 10357 */           fTestHigh *= 10.0D;
/* 10358 */           funcHigh = fProb - fTestProb(fTestHigh, df1, df2);
/*       */         }
/*       */       }
/*       */       else {
/* 10362 */         test0 = false;
/*       */       }
/*       */       
/* 10365 */       int i = 0;
/* 10366 */       while (test1) {
/* 10367 */         fTestMid = (fTestLow + fTestHigh) / 2.0D;
/* 10368 */         funcMid = fProb - fTestProb(fTestMid, df1, df2);
/* 10369 */         if (Math.abs(funcMid) < check) {
/* 10370 */           test1 = false;
/*       */         }
/*       */         else {
/* 10373 */           i++;
/* 10374 */           if (i > nIter) {
/* 10375 */             System.out.println("Class: Stats\nMethod: fTestBisect\nmaximum number of iterations exceeded\ncurrent value of F-test value returned");
/* 10376 */             test1 = false;
/*       */           }
/* 10378 */           if (funcMid * funcHigh > 0.0D) {
/* 10379 */             funcHigh = funcMid;
/* 10380 */             fTestHigh = fTestMid;
/*       */           }
/*       */           else {
/* 10383 */             funcLow = funcMid;
/* 10384 */             fTestLow = fTestMid;
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/* 10389 */     return fTestMid;
/*       */   }
/*       */   
/*       */   public double fPDF(double fValue, int nu1, int nu2)
/*       */   {
/* 10394 */     double numer = Math.pow(nu1 * fValue, nu1) * Math.pow(nu2, nu2);
/* 10395 */     double dnu1 = nu1;
/* 10396 */     double dnu2 = nu2;
/* 10397 */     numer /= Math.pow(dnu1 * fValue + dnu2, dnu1 + dnu2);
/* 10398 */     numer = Math.sqrt(numer);
/* 10399 */     double denom = fValue * betaFunction(dnu1 / 2.0D, dnu2 / 2.0D);
/* 10400 */     return numer / denom;
/*       */   }
/*       */   
/*       */   public double fPDF(double var1, int nu1, double var2, int nu2) {
/* 10404 */     return fPDF(var1 / var2, nu1, nu2);
/*       */   }
/*       */   
/*       */   public static double[] fRand(int nu1, int nu2, int n)
/*       */   {
/* 10409 */     if (nu1 <= 0) throw new IllegalArgumentException("The degrees of freedom [nu1], " + nu1 + ", must be greater than zero");
/* 10410 */     if (nu2 <= 0) throw new IllegalArgumentException("The degrees of freedom [nu2], " + nu2 + ", must be greater than zero");
/* 10411 */     PsRandom psr = new PsRandom();
/* 10412 */     return psr.fArray(nu1, nu2, n);
/*       */   }
/*       */   
/*       */   public static double[] fRand(int nu1, int nu2, int n, long seed)
/*       */   {
/* 10417 */     if (nu1 <= 0) throw new IllegalArgumentException("The degrees of freedom [nu1], " + nu1 + ", must be greater than zero");
/* 10418 */     if (nu2 <= 0) throw new IllegalArgumentException("The degrees of freedom [nu2], " + nu2 + ", must be greater than zero");
/* 10419 */     PsRandom psr = new PsRandom(seed);
/* 10420 */     return psr.fArray(nu1, nu2, n);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double studentst(double tValue, int df)
/*       */   {
/* 10427 */     return studentT(tValue, df);
/*       */   }
/*       */   
/*       */   public static double studentT(double tValue, int df)
/*       */   {
/* 10432 */     double ddf = df;
/* 10433 */     double dfterm = (ddf + 1.0D) / 2.0D;
/* 10434 */     return gamma(dfterm) / gamma(ddf / 2.0D) / Math.sqrt(ddf * 3.141592653589793D) * Math.pow(1.0D + tValue * tValue / ddf, -dfterm);
/*       */   }
/*       */   
/*       */   public static double studentstPDF(double tValue, int df)
/*       */   {
/* 10439 */     return studentTpdf(tValue, df);
/*       */   }
/*       */   
/*       */   public static double studentTpdf(double tValue, int df)
/*       */   {
/* 10444 */     double ddf = df;
/* 10445 */     double dfterm = (ddf + 1.0D) / 2.0D;
/* 10446 */     return gamma(dfterm) / gamma(ddf / 2.0D) / Math.sqrt(ddf * 3.141592653589793D) * Math.pow(1.0D + tValue * tValue / ddf, -dfterm);
/*       */   }
/*       */   
/*       */   public static double studentTPDF(double tValue, int df)
/*       */   {
/* 10451 */     double ddf = df;
/* 10452 */     double dfterm = (ddf + 1.0D) / 2.0D;
/* 10453 */     return gamma(dfterm) / gamma(ddf / 2.0D) / Math.sqrt(ddf * 3.141592653589793D) * Math.pow(1.0D + tValue * tValue / ddf, -dfterm);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double studentstCDF(double tValue, int df)
/*       */   {
/* 10459 */     return studentTcdf(tValue, df);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double studentTProb(double tValue, int df)
/*       */   {
/* 10465 */     if (tValue == Double.POSITIVE_INFINITY) {
/* 10466 */       return 1.0D;
/*       */     }
/*       */     
/* 10469 */     if (tValue == Double.NEGATIVE_INFINITY) {
/* 10470 */       return 0.0D;
/*       */     }
/*       */     
/* 10473 */     double ddf = df;
/* 10474 */     double x = ddf / (ddf + tValue * tValue);
/* 10475 */     return 0.5D * (1.0D + (regularisedBetaFunction(ddf / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(ddf / 2.0D, 0.5D, x)) * Fmath.sign(tValue));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double studentTcdf(double tValue, int df)
/*       */   {
/* 10482 */     if (tValue == Double.POSITIVE_INFINITY) {
/* 10483 */       return 1.0D;
/*       */     }
/*       */     
/* 10486 */     if (tValue == Double.NEGATIVE_INFINITY) {
/* 10487 */       return 0.0D;
/*       */     }
/*       */     
/* 10490 */     double ddf = df;
/* 10491 */     double x = ddf / (ddf + tValue * tValue);
/* 10492 */     return 0.5D * (1.0D + (regularisedBetaFunction(ddf / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(ddf / 2.0D, 0.5D, x)) * Fmath.sign(tValue));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double studentTCDF(double tValue, int df)
/*       */   {
/* 10499 */     if (tValue == Double.POSITIVE_INFINITY) {
/* 10500 */       return 1.0D;
/*       */     }
/*       */     
/* 10503 */     if (tValue == Double.NEGATIVE_INFINITY) {
/* 10504 */       return 0.0D;
/*       */     }
/*       */     
/* 10507 */     double ddf = df;
/* 10508 */     double x = ddf / (ddf + tValue * tValue);
/* 10509 */     return 0.5D * (1.0D + (regularisedBetaFunction(ddf / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(ddf / 2.0D, 0.5D, x)) * Fmath.sign(tValue));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double studentTcdf(double tValueLower, double tValueUpper, int df)
/*       */   {
/* 10516 */     if (tValueUpper == Double.POSITIVE_INFINITY) {
/* 10517 */       if (tValueLower == Double.NEGATIVE_INFINITY) {
/* 10518 */         return 1.0D;
/*       */       }
/*       */       
/* 10521 */       if (tValueLower == Double.POSITIVE_INFINITY) {
/* 10522 */         return 0.0D;
/*       */       }
/*       */       
/* 10525 */       return 1.0D - studentTcdf(tValueLower, df);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/* 10530 */     if (tValueLower == Double.NEGATIVE_INFINITY) {
/* 10531 */       if (tValueUpper == Double.NEGATIVE_INFINITY) {
/* 10532 */         return 0.0D;
/*       */       }
/*       */       
/* 10535 */       return studentTcdf(tValueUpper, df);
/*       */     }
/*       */     
/*       */ 
/* 10539 */     return studentTcdf(tValueUpper, df) - studentTcdf(tValueLower, df);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double pValue(double tValue, int df)
/*       */   {
/* 10546 */     double abst = Math.abs(tValue);
/* 10547 */     return 1.0D - studentTcdf(-abst, abst, df);
/*       */   }
/*       */   
/*       */   public static double studentstMean(int df)
/*       */   {
/* 10552 */     return studentTmean(df);
/*       */   }
/*       */   
/*       */   public static double studentTmean(int df)
/*       */   {
/* 10557 */     double mean = NaN.0D;
/* 10558 */     if (df > 1) mean = 0.0D;
/* 10559 */     return mean;
/*       */   }
/*       */   
/*       */   public static double studentstMedian()
/*       */   {
/* 10564 */     return 0.0D;
/*       */   }
/*       */   
/*       */   public static double studentTmedian()
/*       */   {
/* 10569 */     return 0.0D;
/*       */   }
/*       */   
/*       */   public static double studentstMode()
/*       */   {
/* 10574 */     return 0.0D;
/*       */   }
/*       */   
/*       */   public static double studentTmode()
/*       */   {
/* 10579 */     return 0.0D;
/*       */   }
/*       */   
/*       */   public static double studentstStandardDeviation(int df)
/*       */   {
/* 10584 */     return studentTstandDev(df);
/*       */   }
/*       */   
/*       */   public static double studentTstandDev(int df)
/*       */   {
/* 10589 */     double standDev = Double.POSITIVE_INFINITY;
/* 10590 */     if (df > 2) standDev = Math.sqrt(df / (1 - df));
/* 10591 */     return standDev;
/*       */   }
/*       */   
/*       */   public static double probAtn(double tValue, int df)
/*       */   {
/* 10596 */     double ddf = df;
/* 10597 */     double x = ddf / (ddf + tValue * tValue);
/* 10598 */     return 1.0D - regularisedBetaFunction(ddf / 2.0D, 0.5D, x);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentstRand(int nu, int n)
/*       */   {
/* 10604 */     return studentTRand(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentTRand(int nu, int n)
/*       */   {
/* 10610 */     PsRandom psr = new PsRandom();
/* 10611 */     return psr.studentTarray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentTrand(int nu, int n)
/*       */   {
/* 10617 */     PsRandom psr = new PsRandom();
/* 10618 */     return psr.studentTarray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentstRand(int nu, int n, long seed)
/*       */   {
/* 10624 */     return studentTrand(nu, n, seed);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentTrand(int nu, int n, long seed)
/*       */   {
/* 10630 */     PsRandom psr = new PsRandom(seed);
/* 10631 */     return psr.studentTarray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] studentTRand(int nu, int n, long seed)
/*       */   {
/* 10637 */     PsRandom psr = new PsRandom(seed);
/* 10638 */     return psr.studentTarray(nu, n);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double gumbelMinProbCDF(double mu, double sigma, double upperlimit)
/*       */   {
/* 10647 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10648 */     double arg = -(upperlimit - mu) / sigma;
/* 10649 */     return Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMinProb(double mu, double sigma, double upperlimit)
/*       */   {
/* 10655 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10656 */     double arg = -(upperlimit - mu) / sigma;
/* 10657 */     return Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMaxCDF(double mu, double sigma, double upperlimit)
/*       */   {
/* 10663 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10664 */     double arg = -(upperlimit - mu) / sigma;
/* 10665 */     return 1.0D - Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMaxProb(double mu, double sigma, double upperlimit)
/*       */   {
/* 10671 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10672 */     double arg = -(upperlimit - mu) / sigma;
/* 10673 */     return 1.0D - Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double gumbelMinCDF(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 10680 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10681 */     double arg1 = -(lowerlimit - mu) / sigma;
/* 10682 */     double arg2 = -(upperlimit - mu) / sigma;
/* 10683 */     double term1 = Math.exp(-Math.exp(arg1));
/* 10684 */     double term2 = Math.exp(-Math.exp(arg2));
/* 10685 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMinProb(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 10691 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10692 */     double arg1 = -(lowerlimit - mu) / sigma;
/* 10693 */     double arg2 = -(upperlimit - mu) / sigma;
/* 10694 */     double term1 = Math.exp(-Math.exp(arg1));
/* 10695 */     double term2 = Math.exp(-Math.exp(arg2));
/* 10696 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMaxCDF(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 10702 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10703 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10704 */     double arg2 = (upperlimit - mu) / sigma;
/* 10705 */     double term1 = -Math.exp(-Math.exp(arg1));
/* 10706 */     double term2 = -Math.exp(-Math.exp(arg2));
/* 10707 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double gumbelMaxProb(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 10713 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10714 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10715 */     double arg2 = (upperlimit - mu) / sigma;
/* 10716 */     double term1 = -Math.exp(-Math.exp(arg1));
/* 10717 */     double term2 = -Math.exp(-Math.exp(arg2));
/* 10718 */     return term2 - term1;
/*       */   }
/*       */   
/*       */   public static double gumbelMinPDF(double mu, double sigma, double x)
/*       */   {
/* 10723 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10724 */     double arg = (x - mu) / sigma;
/* 10725 */     return 1.0D / sigma * Math.exp(arg) * Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */   public static double gumbelMin(double mu, double sigma, double x)
/*       */   {
/* 10730 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10731 */     double arg = (x - mu) / sigma;
/* 10732 */     return 1.0D / sigma * Math.exp(arg) * Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */   public static double gumbelMaxPDF(double mu, double sigma, double x)
/*       */   {
/* 10737 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10738 */     double arg = -(x - mu) / sigma;
/* 10739 */     return 1.0D / sigma * Math.exp(arg) * Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */   public static double gumbelMax(double mu, double sigma, double x)
/*       */   {
/* 10744 */     if (sigma < 0.0D) throw new IllegalArgumentException("sigma must be positive");
/* 10745 */     double arg = -(x - mu) / sigma;
/* 10746 */     return 1.0D / sigma * Math.exp(arg) * Math.exp(-Math.exp(arg));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gumbelMinRand(double mu, double sigma, int n)
/*       */   {
/* 10752 */     double[] ran = new double[n];
/* 10753 */     Random rr = new Random();
/* 10754 */     for (int i = 0; i < n; i++) {
/* 10755 */       ran[i] = (Math.log(Math.log(1.0D / (1.0D - rr.nextDouble()))) * sigma + mu);
/*       */     }
/* 10757 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gumbelMinRand(double mu, double sigma, int n, long seed)
/*       */   {
/* 10763 */     double[] ran = new double[n];
/* 10764 */     Random rr = new Random(seed);
/* 10765 */     for (int i = 0; i < n; i++) {
/* 10766 */       ran[i] = (Math.log(Math.log(1.0D / (1.0D - rr.nextDouble()))) * sigma + mu);
/*       */     }
/* 10768 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gumbelMaxRand(double mu, double sigma, int n)
/*       */   {
/* 10774 */     double[] ran = new double[n];
/* 10775 */     Random rr = new Random();
/* 10776 */     for (int i = 0; i < n; i++) {
/* 10777 */       ran[i] = (mu - Math.log(Math.log(1.0D / (1.0D - rr.nextDouble()))) * sigma);
/*       */     }
/* 10779 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] gumbelMaxRand(double mu, double sigma, int n, long seed)
/*       */   {
/* 10785 */     double[] ran = new double[n];
/* 10786 */     Random rr = new Random(seed);
/* 10787 */     for (int i = 0; i < n; i++) {
/* 10788 */       ran[i] = (mu - Math.log(Math.log(1.0D / (1.0D - rr.nextDouble()))) * sigma);
/*       */     }
/* 10790 */     return ran;
/*       */   }
/*       */   
/*       */   public static double gumbelMinMean(double mu, double sigma)
/*       */   {
/* 10795 */     return mu - sigma * 0.5772156649015627D;
/*       */   }
/*       */   
/*       */   public static double gumbelMaxMean(double mu, double sigma)
/*       */   {
/* 10800 */     return mu + sigma * 0.5772156649015627D;
/*       */   }
/*       */   
/*       */   public static double gumbelMinStandardDeviation(double sigma)
/*       */   {
/* 10805 */     return sigma * 3.141592653589793D / Math.sqrt(6.0D);
/*       */   }
/*       */   
/*       */   public static double gumbelMinStandDev(double sigma)
/*       */   {
/* 10810 */     return sigma * 3.141592653589793D / Math.sqrt(6.0D);
/*       */   }
/*       */   
/*       */   public static double gumbelMaxStandardDeviation(double sigma)
/*       */   {
/* 10815 */     return sigma * 3.141592653589793D / Math.sqrt(6.0D);
/*       */   }
/*       */   
/*       */   public static double gumbelMaxStandDev(double sigma)
/*       */   {
/* 10820 */     return sigma * 3.141592653589793D / Math.sqrt(6.0D);
/*       */   }
/*       */   
/*       */   public static double gumbelMinMode(double mu, double sigma)
/*       */   {
/* 10825 */     return mu;
/*       */   }
/*       */   
/*       */   public static double gumbelMaxMode(double mu, double sigma)
/*       */   {
/* 10830 */     return mu;
/*       */   }
/*       */   
/*       */   public static double gumbelMinMedian(double mu, double sigma)
/*       */   {
/* 10835 */     return mu + sigma * Math.log(Math.log(2.0D));
/*       */   }
/*       */   
/*       */   public static double gumbelMaxMedian(double mu, double sigma)
/*       */   {
/* 10840 */     return mu - sigma * Math.log(Math.log(2.0D));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double frechetProb(double mu, double sigma, double gamma, double upperlimit)
/*       */   {
/* 10849 */     double arg = (upperlimit - mu) / sigma;
/* 10850 */     double y = 0.0D;
/* 10851 */     if (arg > 0.0D) y = Math.exp(-Math.pow(arg, -gamma));
/* 10852 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double frechetCDF(double mu, double sigma, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/* 10859 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10860 */     double arg2 = (upperlimit - mu) / sigma;
/* 10861 */     double term1 = 0.0D;double term2 = 0.0D;
/* 10862 */     if (arg1 >= 0.0D) term1 = Math.exp(-Math.pow(arg1, -gamma));
/* 10863 */     if (arg2 >= 0.0D) term2 = Math.exp(-Math.pow(arg2, -gamma));
/* 10864 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double frechetProb(double mu, double sigma, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/* 10870 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10871 */     double arg2 = (upperlimit - mu) / sigma;
/* 10872 */     double term1 = 0.0D;double term2 = 0.0D;
/* 10873 */     if (arg1 >= 0.0D) term1 = Math.exp(-Math.pow(arg1, -gamma));
/* 10874 */     if (arg2 >= 0.0D) term2 = Math.exp(-Math.pow(arg2, -gamma));
/* 10875 */     return term2 - term1;
/*       */   }
/*       */   
/*       */   public static double frechetPDF(double mu, double sigma, double gamma, double x)
/*       */   {
/* 10880 */     double arg = (x - mu) / sigma;
/* 10881 */     double y = 0.0D;
/* 10882 */     if (arg >= 0.0D) {
/* 10883 */       y = gamma / sigma * Math.pow(arg, -gamma - 1.0D) * Math.exp(-Math.pow(arg, -gamma));
/*       */     }
/* 10885 */     return y;
/*       */   }
/*       */   
/*       */   public static double frechet(double mu, double sigma, double gamma, double x)
/*       */   {
/* 10890 */     double arg = (x - mu) / sigma;
/* 10891 */     double y = 0.0D;
/* 10892 */     if (arg >= 0.0D) {
/* 10893 */       y = gamma / sigma * Math.pow(arg, -gamma - 1.0D) * Math.exp(-Math.pow(arg, -gamma));
/*       */     }
/* 10895 */     return y;
/*       */   }
/*       */   
/*       */   public static double frechetMean(double mu, double sigma, double gamma)
/*       */   {
/* 10900 */     double y = NaN.0D;
/* 10901 */     if (gamma > 1.0D) {
/* 10902 */       y = mu + sigma * gamma(1.0D - 1.0D / gamma);
/*       */     }
/* 10904 */     return y;
/*       */   }
/*       */   
/*       */   public static double frechetStandardDeviation(double sigma, double gamma)
/*       */   {
/* 10909 */     return frechetStandDev(sigma, gamma);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double frechetStandDev(double sigma, double gamma)
/*       */   {
/* 10915 */     double y = NaN.0D;
/* 10916 */     if (gamma > 2.0D) {
/* 10917 */       y = gamma(1.0D - 2.0D / gamma) - Fmath.square(gamma(1.0D - 1.0D / gamma));
/* 10918 */       y = sigma * Math.sqrt(y);
/*       */     }
/* 10920 */     return y;
/*       */   }
/*       */   
/*       */   public static double frechetMode(double mu, double sigma, double gamma)
/*       */   {
/* 10925 */     return mu + sigma * Math.pow(gamma / (1.0D + gamma), 1.0D / gamma);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] frechetRand(double mu, double sigma, double gamma, int n)
/*       */   {
/* 10931 */     double[] ran = new double[n];
/* 10932 */     Random rr = new Random();
/* 10933 */     for (int i = 0; i < n; i++) {
/* 10934 */       ran[i] = (Math.pow(1.0D / Math.log(1.0D / rr.nextDouble()), 1.0D / gamma) * sigma + mu);
/*       */     }
/* 10936 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] frechetRand(double mu, double sigma, double gamma, int n, long seed)
/*       */   {
/* 10942 */     double[] ran = new double[n];
/* 10943 */     Random rr = new Random(seed);
/* 10944 */     for (int i = 0; i < n; i++) {
/* 10945 */       ran[i] = (Math.pow(1.0D / Math.log(1.0D / rr.nextDouble()), 1.0D / gamma) * sigma + mu);
/*       */     }
/* 10947 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double weibullCDF(double mu, double sigma, double gamma, double upperlimit)
/*       */   {
/* 10956 */     double arg = (upperlimit - mu) / sigma;
/* 10957 */     double y = 0.0D;
/* 10958 */     if (arg > 0.0D) y = 1.0D - Math.exp(-Math.pow(arg, gamma));
/* 10959 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double weibullProb(double mu, double sigma, double gamma, double upperlimit)
/*       */   {
/* 10965 */     double arg = (upperlimit - mu) / sigma;
/* 10966 */     double y = 0.0D;
/* 10967 */     if (arg > 0.0D) y = 1.0D - Math.exp(-Math.pow(arg, gamma));
/* 10968 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static double weibullCDF(double mu, double sigma, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/* 10975 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10976 */     double arg2 = (upperlimit - mu) / sigma;
/* 10977 */     double term1 = 0.0D;double term2 = 0.0D;
/* 10978 */     if (arg1 >= 0.0D) term1 = -Math.exp(-Math.pow(arg1, gamma));
/* 10979 */     if (arg2 >= 0.0D) term2 = -Math.exp(-Math.pow(arg2, gamma));
/* 10980 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double weibullProb(double mu, double sigma, double gamma, double lowerlimit, double upperlimit)
/*       */   {
/* 10986 */     double arg1 = (lowerlimit - mu) / sigma;
/* 10987 */     double arg2 = (upperlimit - mu) / sigma;
/* 10988 */     double term1 = 0.0D;double term2 = 0.0D;
/* 10989 */     if (arg1 >= 0.0D) term1 = -Math.exp(-Math.pow(arg1, gamma));
/* 10990 */     if (arg2 >= 0.0D) term2 = -Math.exp(-Math.pow(arg2, gamma));
/* 10991 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double weibullInverseCDF(double mu, double sigma, double gamma, double prob)
/*       */   {
/* 10997 */     if ((prob < 0.0D) || (prob > 1.0D)) throw new IllegalArgumentException("Entered cdf value, " + prob + ", must lie between 0 and 1 inclusive");
/* 10998 */     double ran = 0.0D;
/*       */     
/* 11000 */     if (prob == 0.0D) {
/* 11001 */       ran = mu;
/*       */ 
/*       */     }
/* 11004 */     else if (prob == 1.0D) {
/* 11005 */       ran = Double.POSITIVE_INFINITY;
/*       */     }
/*       */     else {
/* 11008 */       ran = mu + sigma * Math.pow(-Math.log(1.0D - prob), 1.0D / gamma);
/*       */     }
/*       */     
/*       */ 
/* 11012 */     return ran;
/*       */   }
/*       */   
/*       */   public static double inverseWeibullCDF(double mu, double sigma, double gamma, double prob)
/*       */   {
/* 11017 */     return weibullInverseCDF(mu, sigma, gamma, prob);
/*       */   }
/*       */   
/*       */   public static double weibullInverseCDF(double sigma, double gamma, double prob)
/*       */   {
/* 11022 */     return weibullInverseCDF(0.0D, sigma, gamma, prob);
/*       */   }
/*       */   
/*       */   public static double inverseWeibullCDF(double sigma, double gamma, double prob)
/*       */   {
/* 11027 */     return weibullInverseCDF(0.0D, sigma, gamma, prob);
/*       */   }
/*       */   
/*       */   public static double weibullInverseCDF(double gamma, double prob)
/*       */   {
/* 11032 */     return weibullInverseCDF(0.0D, 1.0D, gamma, prob);
/*       */   }
/*       */   
/*       */   public static double inverseWeibullCDF(double gamma, double prob)
/*       */   {
/* 11037 */     return weibullInverseCDF(0.0D, 1.0D, gamma, prob);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double weibullPDF(double mu, double sigma, double gamma, double x)
/*       */   {
/* 11043 */     double arg = (x - mu) / sigma;
/* 11044 */     double y = 0.0D;
/* 11045 */     if (arg >= 0.0D) {
/* 11046 */       y = gamma / sigma * Math.pow(arg, gamma - 1.0D) * Math.exp(-Math.pow(arg, gamma));
/*       */     }
/* 11048 */     return y;
/*       */   }
/*       */   
/*       */   public static double weibull(double mu, double sigma, double gamma, double x)
/*       */   {
/* 11053 */     double arg = (x - mu) / sigma;
/* 11054 */     double y = 0.0D;
/* 11055 */     if (arg >= 0.0D) {
/* 11056 */       y = gamma / sigma * Math.pow(arg, gamma - 1.0D) * Math.exp(-Math.pow(arg, gamma));
/*       */     }
/* 11058 */     return y;
/*       */   }
/*       */   
/*       */   public static double weibullMean(double mu, double sigma, double gamma)
/*       */   {
/* 11063 */     return mu + sigma * gamma(1.0D / gamma + 1.0D);
/*       */   }
/*       */   
/*       */   public static double weibullStandardDeviation(double sigma, double gamma)
/*       */   {
/* 11068 */     return weibullStandDev(sigma, gamma);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double weibullStandDev(double sigma, double gamma)
/*       */   {
/* 11074 */     double y = gamma(2.0D / gamma + 1.0D) - Fmath.square(gamma(1.0D / gamma + 1.0D));
/* 11075 */     return sigma * Math.sqrt(y);
/*       */   }
/*       */   
/*       */   public static double weibullMode(double mu, double sigma, double gamma)
/*       */   {
/* 11080 */     double y = mu;
/* 11081 */     if (gamma > 1.0D) {
/* 11082 */       y = mu + sigma * Math.pow((gamma - 1.0D) / gamma, 1.0D / gamma);
/*       */     }
/* 11084 */     return y;
/*       */   }
/*       */   
/*       */   public static double weibullMedian(double mu, double sigma, double gamma)
/*       */   {
/* 11089 */     return mu + sigma * Math.pow(Math.log(2.0D), 1.0D / gamma);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] weibullRand(double mu, double sigma, double gamma, int n)
/*       */   {
/* 11095 */     double[] ran = new double[n];
/* 11096 */     Random rr = new Random();
/* 11097 */     for (int i = 0; i < n; i++) {
/* 11098 */       ran[i] = (Math.pow(-Math.log(1.0D - rr.nextDouble()), 1.0D / gamma) * sigma + mu);
/*       */     }
/* 11100 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] weibullRand(double mu, double sigma, double gamma, int n, long seed)
/*       */   {
/* 11106 */     double[] ran = new double[n];
/* 11107 */     Random rr = new Random(seed);
/* 11108 */     for (int i = 0; i < n; i++) {
/* 11109 */       ran[i] = (Math.pow(-Math.log(1.0D - rr.nextDouble()), 1.0D / gamma) * sigma + mu);
/*       */     }
/* 11111 */     return ran;
/*       */   }
/*       */   
/*       */   public static double[] weibullOrderStatisticMedians(double mu, double sigma, double gamma, int n)
/*       */   {
/* 11116 */     double nn = n;
/* 11117 */     double[] wosm = new double[n];
/* 11118 */     double[] uosm = uniformOrderStatisticMedians(n);
/* 11119 */     for (int i = 0; i < n; i++) {
/* 11120 */       wosm[i] = inverseWeibullCDF(mu, sigma, gamma, uosm[i]);
/*       */     }
/* 11122 */     return wosm;
/*       */   }
/*       */   
/*       */   public static double[] weibullOrderStatisticMedians(double sigma, double gamma, int n)
/*       */   {
/* 11127 */     return weibullOrderStatisticMedians(0.0D, sigma, gamma, n);
/*       */   }
/*       */   
/*       */   public static double[] weibullOrderStatisticMedians(double gamma, int n)
/*       */   {
/* 11132 */     return weibullOrderStatisticMedians(0.0D, 1.0D, gamma, n);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double exponentialCDF(double mu, double sigma, double upperlimit)
/*       */   {
/* 11141 */     double arg = (upperlimit - mu) / sigma;
/* 11142 */     double y = 0.0D;
/* 11143 */     if (arg > 0.0D) y = 1.0D - Math.exp(-arg);
/* 11144 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double exponentialProb(double mu, double sigma, double upperlimit)
/*       */   {
/* 11150 */     double arg = (upperlimit - mu) / sigma;
/* 11151 */     double y = 0.0D;
/* 11152 */     if (arg > 0.0D) y = 1.0D - Math.exp(-arg);
/* 11153 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double exponentialCDF(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 11159 */     double arg1 = (lowerlimit - mu) / sigma;
/* 11160 */     double arg2 = (upperlimit - mu) / sigma;
/* 11161 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11162 */     if (arg1 >= 0.0D) term1 = -Math.exp(-arg1);
/* 11163 */     if (arg2 >= 0.0D) term2 = -Math.exp(-arg2);
/* 11164 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double exponentialProb(double mu, double sigma, double lowerlimit, double upperlimit)
/*       */   {
/* 11170 */     double arg1 = (lowerlimit - mu) / sigma;
/* 11171 */     double arg2 = (upperlimit - mu) / sigma;
/* 11172 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11173 */     if (arg1 >= 0.0D) term1 = -Math.exp(-arg1);
/* 11174 */     if (arg2 >= 0.0D) term2 = -Math.exp(-arg2);
/* 11175 */     return term2 - term1;
/*       */   }
/*       */   
/*       */   public static double exponentialInverseCDF(double mu, double sigma, double prob)
/*       */   {
/* 11180 */     if ((prob < 0.0D) || (prob > 1.0D)) throw new IllegalArgumentException("Entered cdf value, " + prob + ", must lie between 0 and 1 inclusive");
/* 11181 */     double ran = 0.0D;
/*       */     
/* 11183 */     if (prob == 0.0D) {
/* 11184 */       ran = mu;
/*       */ 
/*       */     }
/* 11187 */     else if (prob == 1.0D) {
/* 11188 */       ran = Double.POSITIVE_INFINITY;
/*       */     }
/*       */     else {
/* 11191 */       ran = mu - sigma * Math.log(1.0D - prob);
/*       */     }
/*       */     
/*       */ 
/* 11195 */     return ran;
/*       */   }
/*       */   
/*       */   public static double inverseExponentialCDF(double mu, double sigma, double prob)
/*       */   {
/* 11200 */     return exponentialInverseCDF(mu, sigma, prob);
/*       */   }
/*       */   
/*       */   public static double exponentialPDF(double mu, double sigma, double x)
/*       */   {
/* 11205 */     double arg = (x - mu) / sigma;
/* 11206 */     double y = 0.0D;
/* 11207 */     if (arg >= 0.0D) {
/* 11208 */       y = Math.exp(-arg) / sigma;
/*       */     }
/* 11210 */     return y;
/*       */   }
/*       */   
/*       */   public static double exponential(double mu, double sigma, double x)
/*       */   {
/* 11215 */     double arg = (x - mu) / sigma;
/* 11216 */     double y = 0.0D;
/* 11217 */     if (arg >= 0.0D) {
/* 11218 */       y = Math.exp(-arg) / sigma;
/*       */     }
/* 11220 */     return y;
/*       */   }
/*       */   
/*       */   public static double exponentialMean(double mu, double sigma)
/*       */   {
/* 11225 */     return mu + sigma;
/*       */   }
/*       */   
/*       */   public static double exponentialStandardDeviation(double sigma)
/*       */   {
/* 11230 */     return sigma;
/*       */   }
/*       */   
/*       */   public static double exponentialStandDev(double sigma)
/*       */   {
/* 11235 */     return sigma;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double exponentialMode(double mu)
/*       */   {
/* 11241 */     return mu;
/*       */   }
/*       */   
/*       */   public static double exponentialMedian(double mu, double sigma)
/*       */   {
/* 11246 */     return mu + sigma * Math.log(2.0D);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] exponentialRand(double mu, double sigma, int n)
/*       */   {
/* 11252 */     double[] ran = new double[n];
/* 11253 */     Random rr = new Random();
/* 11254 */     for (int i = 0; i < n; i++) {
/* 11255 */       ran[i] = (mu - Math.log(1.0D - rr.nextDouble()) * sigma);
/*       */     }
/* 11257 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] exponentialRand(double mu, double sigma, int n, long seed)
/*       */   {
/* 11263 */     double[] ran = new double[n];
/* 11264 */     Random rr = new Random(seed);
/* 11265 */     for (int i = 0; i < n; i++) {
/* 11266 */       ran[i] = (mu - Math.log(1.0D - rr.nextDouble()) * sigma);
/*       */     }
/* 11268 */     return ran;
/*       */   }
/*       */   
/*       */   public static double[] exponentialOrderStatisticMedians(double mu, double sigma, int n)
/*       */   {
/* 11273 */     double nn = n;
/* 11274 */     double[] eosm = new double[n];
/* 11275 */     double[] uosm = uniformOrderStatisticMedians(n);
/* 11276 */     for (int i = 0; i < n; i++) {
/* 11277 */       eosm[i] = inverseExponentialCDF(mu, sigma, uosm[i]);
/*       */     }
/* 11279 */     return eosm;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double rayleighCDF(double beta, double upperlimit)
/*       */   {
/* 11289 */     double arg = upperlimit / beta;
/* 11290 */     double y = 0.0D;
/* 11291 */     if (arg > 0.0D) y = 1.0D - Math.exp(-arg * arg / 2.0D);
/* 11292 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double rayleighProb(double beta, double upperlimit)
/*       */   {
/* 11298 */     double arg = upperlimit / beta;
/* 11299 */     double y = 0.0D;
/* 11300 */     if (arg > 0.0D) y = 1.0D - Math.exp(-arg * arg / 2.0D);
/* 11301 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double rayleighCDF(double beta, double lowerlimit, double upperlimit)
/*       */   {
/* 11307 */     double arg1 = lowerlimit / beta;
/* 11308 */     double arg2 = upperlimit / beta;
/* 11309 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11310 */     if (arg1 >= 0.0D) term1 = -Math.exp(-arg1 * arg1 / 2.0D);
/* 11311 */     if (arg2 >= 0.0D) term2 = -Math.exp(-arg2 * arg2 / 2.0D);
/* 11312 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double rayleighProb(double beta, double lowerlimit, double upperlimit)
/*       */   {
/* 11318 */     double arg1 = lowerlimit / beta;
/* 11319 */     double arg2 = upperlimit / beta;
/* 11320 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11321 */     if (arg1 >= 0.0D) term1 = -Math.exp(-arg1 * arg1 / 2.0D);
/* 11322 */     if (arg2 >= 0.0D) term2 = -Math.exp(-arg2 * arg2 / 2.0D);
/* 11323 */     return term2 - term1;
/*       */   }
/*       */   
/*       */   public static double rayleighInverseCDF(double beta, double prob)
/*       */   {
/* 11328 */     if ((prob < 0.0D) || (prob > 1.0D)) throw new IllegalArgumentException("Entered cdf value, " + prob + ", must lie between 0 and 1 inclusive");
/* 11329 */     double ran = 0.0D;
/*       */     
/* 11331 */     if (prob == 0.0D) {
/* 11332 */       ran = 0.0D;
/*       */ 
/*       */     }
/* 11335 */     else if (prob == 1.0D) {
/* 11336 */       ran = Double.POSITIVE_INFINITY;
/*       */     }
/*       */     else {
/* 11339 */       ran = beta * Math.sqrt(-Math.log(1.0D - prob));
/*       */     }
/*       */     
/*       */ 
/* 11343 */     return ran;
/*       */   }
/*       */   
/*       */   public static double inverseRayleighCDF(double beta, double prob)
/*       */   {
/* 11348 */     return rayleighInverseCDF(beta, prob);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double rayleighPDF(double beta, double x)
/*       */   {
/* 11354 */     double arg = x / beta;
/* 11355 */     double y = 0.0D;
/* 11356 */     if (arg >= 0.0D) {
/* 11357 */       y = arg / beta * Math.exp(-arg * arg / 2.0D) / beta;
/*       */     }
/* 11359 */     return y;
/*       */   }
/*       */   
/*       */   public static double rayleigh(double beta, double x)
/*       */   {
/* 11364 */     double arg = x / beta;
/* 11365 */     double y = 0.0D;
/* 11366 */     if (arg >= 0.0D) {
/* 11367 */       y = arg / beta * Math.exp(-arg * arg / 2.0D) / beta;
/*       */     }
/* 11369 */     return y;
/*       */   }
/*       */   
/*       */   public static double rayleighMean(double beta)
/*       */   {
/* 11374 */     return beta * Math.sqrt(1.5707963267948966D);
/*       */   }
/*       */   
/*       */   public static double rayleighStandardDeviation(double beta)
/*       */   {
/* 11379 */     return beta * Math.sqrt(0.42920367320510344D);
/*       */   }
/*       */   
/*       */   public static double rayleighStandDev(double beta)
/*       */   {
/* 11384 */     return beta * Math.sqrt(0.42920367320510344D);
/*       */   }
/*       */   
/*       */   public static double rayleighMode(double beta)
/*       */   {
/* 11389 */     return beta;
/*       */   }
/*       */   
/*       */   public static double rayleighMedian(double beta)
/*       */   {
/* 11394 */     return beta * Math.sqrt(Math.log(2.0D));
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] rayleighRand(double beta, int n)
/*       */   {
/* 11400 */     double[] ran = new double[n];
/* 11401 */     Random rr = new Random();
/* 11402 */     for (int i = 0; i < n; i++) {
/* 11403 */       ran[i] = (Math.sqrt(-2.0D * Math.log(1.0D - rr.nextDouble())) * beta);
/*       */     }
/* 11405 */     return ran;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double[] rayleighRand(double beta, int n, long seed)
/*       */   {
/* 11411 */     double[] ran = new double[n];
/* 11412 */     Random rr = new Random(seed);
/* 11413 */     for (int i = 0; i < n; i++) {
/* 11414 */       ran[i] = (Math.sqrt(-2.0D * Math.log(1.0D - rr.nextDouble())) * beta);
/*       */     }
/* 11416 */     return ran;
/*       */   }
/*       */   
/*       */   public static double[] rayleighOrderStatisticMedians(double beta, int n)
/*       */   {
/* 11421 */     double nn = n;
/* 11422 */     double[] rosm = new double[n];
/* 11423 */     double[] uosm = uniformOrderStatisticMedians(n);
/* 11424 */     for (int i = 0; i < n; i++) {
/* 11425 */       rosm[i] = inverseRayleighCDF(beta, uosm[i]);
/*       */     }
/* 11427 */     return rosm;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static double paretoCDF(double alpha, double beta, double upperlimit)
/*       */   {
/* 11436 */     double y = 0.0D;
/* 11437 */     if (upperlimit >= beta) y = 1.0D - Math.pow(beta / upperlimit, alpha);
/* 11438 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double paretoProb(double alpha, double beta, double upperlimit)
/*       */   {
/* 11444 */     double y = 0.0D;
/* 11445 */     if (upperlimit >= beta) y = 1.0D - Math.pow(beta / upperlimit, alpha);
/* 11446 */     return y;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double paretoCDF(double alpha, double beta, double lowerlimit, double upperlimit)
/*       */   {
/* 11452 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11453 */     if (lowerlimit >= beta) term1 = -Math.pow(beta / lowerlimit, alpha);
/* 11454 */     if (upperlimit >= beta) term2 = -Math.pow(beta / upperlimit, alpha);
/* 11455 */     return term2 - term1;
/*       */   }
/*       */   
/*       */ 
/*       */   public static double paretoProb(double alpha, double beta, double lowerlimit, double upperlimit)
/*       */   {
/* 11461 */     double term1 = 0.0D;double term2 = 0.0D;
/* 11462 */     if (lowerlimit >= beta) term1 = -Math.pow(beta / lowerlimit, alpha);
/* 11463 */     if (upperlimit >= beta) term2 = -Math.pow(beta / upperlimit, alpha);
/* 11464 */     return term2 - term1;
/*       */   }
/*       */   
/*       */   public static double paretoInverseCDF(double alpha, double beta, double prob)
/*       */   {
/* 11469 */     if ((prob < 0.0D) || (prob > 1.0D)) throw new IllegalArgumentException("Entered cdf value, " + prob + ", must lie between 0 and 1 inclusive");
/* 11470 */     double ran = 0.0D;
/*       */     
/* 11472 */     if (prob == 0.0D) {
/* 11473 */       ran = beta;
/*       */ 
/*       */     }
/* 11476 */     else if (prob == 1.0D) {
/* 11477 */       ran = Double.POSITIVE_INFINITY;
/*       */     }
/*       */     else {
/* 11480 */       ran = beta / Math.pow(1.0D - prob, 1.0D / alpha);
/*       */     }
/*       */     
/*       */ 
/* 11484 */     return ran;
/*       */   }
/*       */   
/*       */   public static double inverseParetoCDF(double alpha, double beta, double prob)
/*       */   {
/* 11489 */     return paretoInverseCDF(alpha, beta, prob);
/*       */   }
/*       */   
/*       */ 
/*       */   public static double paretoPDF(double alpha, double beta, double x)
/*       */   {
/* 11495 */     double y = 0.0D;
/* 11496 */     if (x >= beta) {
/* 11497 */       y = alpha * Math.pow(beta, alpha) / Math.pow(x, alpha + 1.0D);
/*       */     }
/* 11499 */     return y;
/*       */   }
/*       */   
/*       */   public static double pareto(double alpha, double beta, double x)
/*       */   {
/* 11504 */     double y = 0.0D;
/* 11505 */     if (x >= beta) {
/* 11506 */       y = alpha * Math.pow(beta, alpha) / Math.pow(x, alpha + 1.0D);
/*       */     }
/* 11508 */     return y;
/*       */   }
/*       */   
/*       */   public static double paretoMean(double alpha, double beta)
/*       */   {
/* 11513 */     double y = NaN.0D;
/* 11514 */     if (alpha > 1.0D) y = alpha * beta / (alpha - 1.0D);
/* 11515 */     return y;
/*       */   }
/*       */   
/*       */   public static double paretoStandardDeviation(double alpha, double beta)
/*       */   {
/* 11520 */     double y = NaN.0D;
/* 11521 */     if (alpha > 1.0D) y = alpha * Fmath.square(beta) / (Fmath.square(alpha - 1.0D) * (alpha - 2.0D));
/* 11522 */     return y;
/*       */   }
/*       */   
/*       */   public static double paretoStandDev(double alpha, double beta)
/*       */   {
/* 11527 */     double y = NaN.0D;
/* 11528 */     if (alpha > 1.0D) y = alpha * Fmath.square(beta) / (Fmath.square(alpha - 1.0D) * (alpha - 2.0D));
/* 11529 */     return y;
/*       */   }
/*       */   
/*       */   public static double paretoMode(double beta)
/*       */   {
/* 11534 */     return beta;
/*       */   }
/*       */   
/*       */   public static double[] paretoRand(double alpha, double beta, int n)
/*       */   {
/* 11539 */     double[] ran = new double[n];
/* 11540 */     Random rr = new Random();
/* 11541 */     for (int i = 0; i < n; i++) {
/* 11542 */       ran[i] = (Math.pow(1.0D - rr.nextDouble(), -1.0D / alpha) * beta);
/*       */     }
/* 11544 */     return ran;
/*       */   }
/*       */   
/*       */   public static double[] paretoRand(double alpha, double beta, int n, long seed)
/*       */   {
/* 11549 */     double[] ran = new double[n];
/* 11550 */     Random rr = new Random(seed);
/* 11551 */     for (int i = 0; i < n; i++) {
/* 11552 */       ran[i] = (Math.pow(1.0D - rr.nextDouble(), -1.0D / alpha) * beta);
/*       */     }
/* 11554 */     return ran;
/*       */   }
/*       */   
/*       */   public static double[] paretoOrderStatisticMedians(double alpha, double beta, int n)
/*       */   {
/* 11559 */     double nn = n;
/* 11560 */     double[] posm = new double[n];
/* 11561 */     double[] uosm = uniformOrderStatisticMedians(n);
/* 11562 */     for (int i = 0; i < n; i++) {
/* 11563 */       posm[i] = inverseParetoCDF(alpha, beta, uosm[i]);
/*       */     }
/* 11565 */     return posm;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void fitOneOrSeveralDistributions()
/*       */   {
/* 11573 */     double[] dd = getArray_as_double();
/* 11574 */     Regression.fitOneOrSeveralDistributions(dd);
/*       */   }
/*       */   
/*       */   public static void fitOneOrSeveralDistributions(double[] array)
/*       */   {
/* 11579 */     Regression.fitOneOrSeveralDistributions(array);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public static Vector<Object> upperOutliersAnscombeAsVector(double[] values, double constant)
/*       */   {
/* 11587 */     ArrayList<Object> res = upperOutliersAnscombeAsArrayList(values, constant);
/* 11588 */     Vector<Object> ret = null;
/* 11589 */     if (res != null) {
/* 11590 */       int n = res.size();
/* 11591 */       ret = new Vector(n);
/* 11592 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11594 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> upperOutliersAnscombe(double[] values, double constant)
/*       */   {
/* 11600 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static ArrayList<Object> upperOutliersAnscombeAsArrayList(double[] values, double constant)
/*       */   {
/* 11607 */     Stat am = new Stat(values);
/* 11608 */     double[] copy0 = am.getArray_as_double();
/* 11609 */     double[] copy1 = am.getArray_as_double();
/* 11610 */     int nValues = values.length;
/* 11611 */     int nValues0 = nValues;
/* 11612 */     ArrayList<Object> outers = new ArrayList();
/* 11613 */     int nOutliers = 0;
/* 11614 */     boolean test = true;
/*       */     
/* 11616 */     while (test) {
/* 11617 */       double mean = am.mean_as_double();
/* 11618 */       double standDev = am.standardDeviation_as_double();
/* 11619 */       double max = am.getMaximum_as_double();
/* 11620 */       int maxIndex = am.getMaximumIndex();
/* 11621 */       double statistic = (max - mean) / standDev;
/* 11622 */       if (statistic > constant) {
/* 11623 */         outers.add(new Double(max));
/* 11624 */         outers.add(new Integer(maxIndex));
/* 11625 */         nOutliers++;
/* 11626 */         copy1 = new double[nValues - 1];
/* 11627 */         for (int i = maxIndex; i < nValues - 1; i++) { copy1[i] = copy0[(i + 1)];
/*       */         }
/* 11629 */         nValues--;
/* 11630 */         am = new Stat((double[])copy1.clone());
/*       */       }
/*       */       else {
/* 11633 */         test = false;
/*       */       }
/*       */     }
/*       */     
/* 11637 */     double[] outliers = null;
/* 11638 */     int[] outIndices = null;
/*       */     
/* 11640 */     if (nOutliers > 0) {
/* 11641 */       outliers = new double[nOutliers];
/* 11642 */       outIndices = new int[nOutliers];
/* 11643 */       for (int i = 0; i < nOutliers; i++) {
/* 11644 */         outliers[i] = ((Double)outers.get(2 * i)).doubleValue();
/* 11645 */         outIndices[i] = ((Integer)outers.get(2 * i + 1)).intValue();
/*       */       }
/*       */     }
/*       */     
/* 11649 */     ArrayList<Object> ret = new ArrayList(4);
/* 11650 */     ret.add(new Integer(nOutliers));
/* 11651 */     ret.add(outliers);
/* 11652 */     ret.add(outIndices);
/* 11653 */     ret.add(copy1);
/* 11654 */     return ret;
/*       */   }
/*       */   
/*       */   public static Vector<Object> upperOutliersAnscombeAsVector(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11659 */     ArrayList<Object> res = upperOutliersAnscombeAsArrayList(values, constant);
/* 11660 */     Vector<Object> ret = null;
/* 11661 */     if (res != null) {
/* 11662 */       int n = res.size();
/* 11663 */       ret = new Vector(n);
/* 11664 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11666 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> upperOutliersAnscombe(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11672 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public static ArrayList<Object> upperOutliersAnscombeAsArrayList(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11679 */     Stat am = new Stat(values);
/* 11680 */     BigDecimal[] copy0 = am.getArray_as_BigDecimal();
/* 11681 */     BigDecimal[] copy1 = am.getArray_as_BigDecimal();
/* 11682 */     int nValues = values.length;
/* 11683 */     int nValues0 = nValues;
/* 11684 */     ArrayList<Object> outers = new ArrayList();
/* 11685 */     int nOutliers = 0;
/* 11686 */     boolean test = true;
/* 11687 */     while (test) {
/* 11688 */       BigDecimal mean = am.mean_as_BigDecimal();
/* 11689 */       BigDecimal variance = am.variance_as_BigDecimal();
/* 11690 */       BigDecimal max = am.getMaximum_as_BigDecimal();
/* 11691 */       int maxIndex = am.getMaximumIndex();
/* 11692 */       BigDecimal statistic = max.subtract(mean).divide(variance, 4);
/* 11693 */       if (statistic.compareTo(constant.multiply(constant)) == 1) {
/* 11694 */         outers.add(max);
/* 11695 */         outers.add(new Integer(maxIndex));
/* 11696 */         nOutliers++;
/* 11697 */         copy1 = new BigDecimal[nValues - 1];
/* 11698 */         for (int i = maxIndex; i < nValues - 1; i++) { copy1[i] = copy0[(i + 1)];
/*       */         }
/* 11700 */         nValues--;
/* 11701 */         am = new Stat((BigDecimal[])copy1.clone());
/*       */       }
/*       */       else {
/* 11704 */         mean = null;
/* 11705 */         variance = null;
/* 11706 */         statistic = null;
/* 11707 */         copy0 = null;
/* 11708 */         test = false;
/*       */       }
/*       */     }
/*       */     
/* 11712 */     BigDecimal[] outliers = null;
/* 11713 */     int[] outIndices = null;
/*       */     
/* 11715 */     if (nOutliers > 0) {
/* 11716 */       outliers = new BigDecimal[nOutliers];
/* 11717 */       outIndices = new int[nOutliers];
/* 11718 */       for (int i = 0; i < nOutliers; i++) {
/* 11719 */         outliers[i] = ((BigDecimal)outers.get(2 * i));
/* 11720 */         outIndices[i] = ((Integer)outers.get(2 * i + 1)).intValue();
/*       */       }
/*       */     }
/*       */     
/* 11724 */     ArrayList<Object> ret = new ArrayList(4);
/* 11725 */     ret.add(new Integer(nOutliers));
/* 11726 */     ret.add(outliers);
/* 11727 */     ret.add(outIndices);
/* 11728 */     ret.add(copy1);
/* 11729 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> upperOutliersAnscombeAsVector(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11735 */     ArrayList<Object> res = upperOutliersAnscombeAsArrayList(values, constant);
/* 11736 */     Vector<Object> ret = null;
/* 11737 */     if (res != null) {
/* 11738 */       int n = res.size();
/* 11739 */       ret = new Vector(n);
/* 11740 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11742 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> upperOutliersAnscombe(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11748 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public static ArrayList<Object> upperOutliersAnscombeAsArrayList(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11754 */     ArrayMaths am = new ArrayMaths(values);
/* 11755 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/* 11756 */     BigDecimal cd = new BigDecimal(constant);
/* 11757 */     return upperOutliersAnscombeAsArrayList(bd, cd);
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> lowerOutliersAnscombeAsVector(double[] values, double constant)
/*       */   {
/* 11763 */     ArrayList<Object> res = lowerOutliersAnscombeAsArrayList(values, constant);
/* 11764 */     Vector<Object> ret = null;
/* 11765 */     if (res != null) {
/* 11766 */       int n = res.size();
/* 11767 */       ret = new Vector(n);
/* 11768 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11770 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> lowerOutliersAnscombe(double[] values, double constant)
/*       */   {
/* 11776 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(double[] values, double constant)
/*       */   {
/* 11782 */     Stat am = new Stat(values);
/* 11783 */     double[] copy0 = am.getArray_as_double();
/* 11784 */     double[] copy1 = am.getArray_as_double();
/* 11785 */     int nValues = values.length;
/* 11786 */     int nValues0 = nValues;
/* 11787 */     ArrayList<Object> outers = new ArrayList();
/* 11788 */     int nOutliers = 0;
/* 11789 */     boolean test = true;
/*       */     
/* 11791 */     while (test) {
/* 11792 */       double mean = am.mean_as_double();
/* 11793 */       double standDev = am.standardDeviation_as_double();
/* 11794 */       double min = am.getMinimum_as_double();
/* 11795 */       int minIndex = am.getMinimumIndex();
/* 11796 */       double statistic = (mean - min) / standDev;
/* 11797 */       if (statistic > constant) {
/* 11798 */         outers.add(new Double(min));
/* 11799 */         outers.add(new Integer(minIndex));
/* 11800 */         nOutliers++;
/* 11801 */         copy1 = new double[nValues - 1];
/* 11802 */         for (int i = minIndex; i < nValues - 1; i++) { copy1[i] = copy0[(i + 1)];
/*       */         }
/* 11804 */         nValues--;
/* 11805 */         am = new Stat((double[])copy1.clone());
/*       */       }
/*       */       else {
/* 11808 */         test = false;
/*       */       }
/*       */     }
/*       */     
/* 11812 */     double[] outliers = null;
/* 11813 */     int[] outIndices = null;
/*       */     
/* 11815 */     if (nOutliers > 0) {
/* 11816 */       outliers = new double[nOutliers];
/* 11817 */       outIndices = new int[nOutliers];
/* 11818 */       for (int i = 0; i < nOutliers; i++) {
/* 11819 */         outliers[i] = ((Double)outers.get(2 * i)).doubleValue();
/* 11820 */         outIndices[i] = ((Integer)outers.get(2 * i + 1)).intValue();
/*       */       }
/*       */     }
/*       */     
/* 11824 */     ArrayList<Object> ret = new ArrayList();
/* 11825 */     ret.add(new Integer(nOutliers));
/* 11826 */     ret.add(outliers);
/* 11827 */     ret.add(outIndices);
/* 11828 */     ret.add(copy1);
/* 11829 */     return ret;
/*       */   }
/*       */   
/*       */   public static Vector<Object> lowerOutliersAnscombeAsVector(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11834 */     ArrayList<Object> res = lowerOutliersAnscombeAsArrayList(values, constant);
/* 11835 */     Vector<Object> ret = null;
/* 11836 */     if (res != null) {
/* 11837 */       int n = res.size();
/* 11838 */       ret = new Vector(n);
/* 11839 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11841 */     return ret;
/*       */   }
/*       */   
/*       */   public static Vector<Object> lowerOutliersAnscombe(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11846 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */ 
/*       */   public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(BigDecimal[] values, BigDecimal constant)
/*       */   {
/* 11852 */     Stat am = new Stat(values);
/* 11853 */     BigDecimal[] copy0 = am.getArray_as_BigDecimal();
/* 11854 */     BigDecimal[] copy1 = am.getArray_as_BigDecimal();
/* 11855 */     int nValues = values.length;
/* 11856 */     int nValues0 = nValues;
/* 11857 */     ArrayList<Object> outers = new ArrayList();
/* 11858 */     int nOutliers = 0;
/* 11859 */     boolean test = true;
/* 11860 */     while (test) {
/* 11861 */       BigDecimal mean = am.mean_as_BigDecimal();
/* 11862 */       BigDecimal variance = am.variance_as_BigDecimal();
/* 11863 */       BigDecimal min = am.getMinimum_as_BigDecimal();
/* 11864 */       int minIndex = am.getMinimumIndex();
/* 11865 */       BigDecimal statistic = mean.subtract(min).divide(variance, 4);
/* 11866 */       if (statistic.compareTo(constant.multiply(constant)) == 1) {
/* 11867 */         outers.add(min);
/* 11868 */         outers.add(new Integer(minIndex));
/* 11869 */         nOutliers++;
/* 11870 */         copy1 = new BigDecimal[nValues - 1];
/* 11871 */         for (int i = minIndex; i < nValues - 1; i++) { copy1[i] = copy0[(i + 1)];
/*       */         }
/* 11873 */         nValues--;
/* 11874 */         am = new Stat((BigDecimal[])copy1.clone());
/*       */       }
/*       */       else {
/* 11877 */         mean = null;
/* 11878 */         variance = null;
/* 11879 */         statistic = null;
/* 11880 */         copy0 = null;
/* 11881 */         test = false;
/*       */       }
/*       */     }
/*       */     
/* 11885 */     BigDecimal[] outliers = null;
/* 11886 */     int[] outIndices = null;
/*       */     
/* 11888 */     if (nOutliers > 0) {
/* 11889 */       outliers = new BigDecimal[nOutliers];
/* 11890 */       outIndices = new int[nOutliers];
/* 11891 */       for (int i = 0; i < nOutliers; i++) {
/* 11892 */         outliers[i] = ((BigDecimal)outers.get(2 * i));
/* 11893 */         outIndices[i] = ((Integer)outers.get(2 * i + 1)).intValue();
/*       */       }
/*       */     }
/*       */     
/* 11897 */     ArrayList<Object> ret = new ArrayList();
/* 11898 */     ret.add(new Integer(nOutliers));
/* 11899 */     ret.add(outliers);
/* 11900 */     ret.add(outIndices);
/* 11901 */     ret.add(copy1);
/* 11902 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */   public static Vector<Object> lowerOutliersAnscombeAsVector(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11908 */     ArrayList<Object> res = lowerOutliersAnscombeAsArrayList(values, constant);
/* 11909 */     Vector<Object> ret = null;
/* 11910 */     if (res != null) {
/* 11911 */       int n = res.size();
/* 11912 */       ret = new Vector(n);
/* 11913 */       for (int i = 0; i < n; i++) ret.add(res.get(i));
/*       */     }
/* 11915 */     return ret;
/*       */   }
/*       */   
/*       */   public static Vector<Object> lowerOutliersAnscombe(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11920 */     return upperOutliersAnscombeAsVector(values, constant);
/*       */   }
/*       */   
/*       */   public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(BigInteger[] values, BigInteger constant)
/*       */   {
/* 11925 */     ArrayMaths am = new ArrayMaths(values);
/* 11926 */     BigDecimal[] bd = am.getArray_as_BigDecimal();
/* 11927 */     BigDecimal cd = new BigDecimal(constant);
/* 11928 */     return lowerOutliersAnscombeAsArrayList(bd, cd);
/*       */   }
/*       */   
/*       */ 
/*       */   private static double[] probPlotStats(int flag, double[] param, double[] data)
/*       */   {
/* 11934 */     int nParam = param.length;
/* 11935 */     int nData = data.length;
/*       */     
/* 11937 */     double[] ret = new double[nParam + 1];
/* 11938 */     Stat st = new Stat(data);
/* 11939 */     double range = st.maximum() - st.minimum();
/* 11940 */     double delta = 0.001D;
/*       */     
/* 11942 */     switch (flag) {
/*       */     case 0: 
/* 11944 */       GaussProbPlotFunc func = new GaussProbPlotFunc();
/*       */       
/* 11946 */       func.setDataArray(data);
/* 11947 */       double sumOfSquares = func.function(param);
/*       */       
/* 11949 */       double[][] secondDeriv = new double[2][2];
/* 11950 */       double[] paramTemp = new double[2];
/* 11951 */       double pari = 0.0D;
/* 11952 */       double parj = 0.0D;
/* 11953 */       for (int i = 0; i < 2; i++) {
/* 11954 */         for (int j = 0; j < 2; j++) {
/* 11955 */           if (i == j) {
/* 11956 */             paramTemp = (double[])param.clone();
/* 11957 */             param[i] *= (1.0D + delta);
/* 11958 */             pari = param[i];
/* 11959 */             if (i == 0) {
/* 11960 */               if (param[i] == 0.0D) paramTemp[i] = (param[1] * delta / 10.0D);
/* 11961 */               pari = param[1] / 10.0D;
/* 11962 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 100.0D);
/* 11963 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 11966 */               if (param[i] == 0.0D) paramTemp[i] = (3.0D * param[0] * delta);
/* 11967 */               pari = 3.0D * param[0];
/* 11968 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 35.0D);
/* 11969 */               pari = range / 35.0D;
/*       */             }
/*       */             
/* 11972 */             double term1 = func.function(paramTemp);
/* 11973 */             System.out.println("1 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 11975 */             paramTemp = (double[])param.clone();
/* 11976 */             paramTemp[i] = param[i];
/* 11977 */             pari = param[i];
/*       */             
/*       */ 
/* 11980 */             double term2 = func.function(paramTemp);
/* 11981 */             System.out.println("2 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 11983 */             paramTemp = (double[])param.clone();
/* 11984 */             paramTemp[i] = param[i];
/* 11985 */             pari = param[i];
/*       */             
/* 11987 */             double term3 = func.function(paramTemp);
/* 11988 */             System.out.println("3 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 11990 */             paramTemp = (double[])param.clone();
/* 11991 */             param[i] *= (1.0D - delta);
/* 11992 */             pari = param[i];
/* 11993 */             if (i == 0) {
/* 11994 */               if (param[i] == 0.0D) paramTemp[i] = (-param[1] * delta / 10.0D);
/* 11995 */               pari = param[1] / 10.0D;
/* 11996 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 100.0D);
/* 11997 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 12000 */               if (param[i] == 0.0D) paramTemp[i] = (-3.0D * param[0] * delta);
/* 12001 */               pari = 3.0D * param[0];
/* 12002 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 35.0D);
/* 12003 */               pari = range / 35.0D;
/*       */             }
/*       */             
/* 12006 */             double term4 = func.function(paramTemp);
/* 12007 */             System.out.println("4 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 12009 */             System.out.println(term1 + " " + term2 + " " + term3 + " " + term4);
/* 12010 */             secondDeriv[i][j] = ((term1 - term2 - term3 + term4) / (pari * pari * delta * delta));
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*       */ 
/* 12016 */             paramTemp = (double[])param.clone();
/* 12017 */             param[i] *= (1.0D + delta / 2.0D);
/* 12018 */             pari = param[i];
/* 12019 */             if (i == 0) {
/* 12020 */               if (param[i] == 0.0D) paramTemp[i] = (param[1] * delta / 20.0D);
/* 12021 */               pari = param[1] / 10.0D;
/* 12022 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 200.0D);
/* 12023 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 12026 */               if (param[i] == 0.0D) paramTemp[i] = (3.0D * param[0] * delta / 2.0D);
/* 12027 */               pari = 3.0D * param[0];
/* 12028 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 70.0D);
/* 12029 */               pari = range / 35.0D;
/*       */             }
/* 12031 */             param[j] *= (1.0D + delta / 2.0D);
/* 12032 */             parj = param[j];
/* 12033 */             if (j == 0) {
/* 12034 */               if (param[j] == 0.0D) paramTemp[j] = (param[1] * delta / 20.0D);
/* 12035 */               parj = param[1] / 10.0D;
/* 12036 */               if (param[j] == 0.0D) paramTemp[j] = (range * delta / 200.0D);
/* 12037 */               parj = range / 100.0D;
/*       */             }
/*       */             else {
/* 12040 */               if (param[j] == 0.0D) paramTemp[j] = (3.0D * param[0] * delta / 2.0D);
/* 12041 */               parj = 3.0D * param[0];
/* 12042 */               if (param[j] == 0.0D) paramTemp[j] = (range * delta / 70.0D);
/* 12043 */               parj = range / 70.0D;
/*       */             }
/* 12045 */             double term1 = func.function(paramTemp);
/* 12046 */             System.out.println("1 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 12048 */             paramTemp = (double[])param.clone();
/* 12049 */             param[i] *= (1.0D - delta / 2.0D);
/* 12050 */             pari = param[i];
/* 12051 */             if (i == 0) {
/* 12052 */               if (param[i] == 0.0D) paramTemp[i] = (-param[1] * delta / 20.0D);
/* 12053 */               pari = param[1] / 10.0D;
/* 12054 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 200.0D);
/* 12055 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 12058 */               if (param[i] == 0.0D) paramTemp[i] = (-3.0D * param[0] * delta / 2.0D);
/* 12059 */               pari = 3.0D * param[0];
/* 12060 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 70.0D);
/* 12061 */               pari = range / 35.0D;
/*       */             }
/* 12063 */             param[j] *= (1.0D + delta / 2.0D);
/* 12064 */             parj = param[j];
/* 12065 */             if (j == 0) {
/* 12066 */               if (param[j] == 0.0D) paramTemp[j] = (param[1] * delta / 20.0D);
/* 12067 */               parj = param[1] / 10.0D;
/* 12068 */               if (param[j] == 0.0D) paramTemp[j] = (range * delta / 200.0D);
/* 12069 */               parj = range / 100.0D;
/*       */             }
/*       */             else {
/* 12072 */               if (param[j] == 0.0D) paramTemp[j] = (3.0D * param[0] * delta / 2.0D);
/* 12073 */               parj = 3.0D * param[0];
/* 12074 */               if (param[j] == 0.0D) paramTemp[j] = (range * delta / 70.0D);
/* 12075 */               parj = range / 35.0D;
/*       */             }
/* 12077 */             double term2 = func.function(paramTemp);
/* 12078 */             System.out.println("2 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 12080 */             paramTemp = (double[])param.clone();
/* 12081 */             param[i] *= (1.0D + delta / 2.0D);
/* 12082 */             pari = param[i];
/* 12083 */             if (i == 0) {
/* 12084 */               if (param[i] == 0.0D) paramTemp[i] = (param[1] * delta / 20.0D);
/* 12085 */               pari = param[1] / 10.0D;
/* 12086 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 200.0D);
/* 12087 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 12090 */               if (param[i] == 0.0D) paramTemp[i] = (3.0D * param[0] * delta / 2.0D);
/* 12091 */               pari = 3.0D * param[0];
/* 12092 */               if (param[i] == 0.0D) paramTemp[i] = (range * delta / 70.0D);
/* 12093 */               pari = range / 35.0D;
/*       */             }
/* 12095 */             param[j] *= (1.0D - delta / 2.0D);
/* 12096 */             parj = param[j];
/* 12097 */             if (j == 0) {
/* 12098 */               if (param[j] == 0.0D) paramTemp[j] = (-param[1] * delta / 20.0D);
/* 12099 */               parj = param[1] / 10.0D;
/* 12100 */               if (param[j] == 0.0D) paramTemp[j] = (-range * delta / 200.0D);
/* 12101 */               parj = range / 100.0D;
/*       */             }
/*       */             else {
/* 12104 */               if (param[j] == 0.0D) paramTemp[j] = (-3.0D * param[0] * delta / 2.0D);
/* 12105 */               parj = 3.0D * param[0];
/* 12106 */               if (param[j] == 0.0D) paramTemp[j] = (-range * delta / 70.0D);
/* 12107 */               parj = range / 35.0D;
/*       */             }
/* 12109 */             double term3 = func.function(paramTemp);
/* 12110 */             System.out.println("3 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 12112 */             paramTemp = (double[])param.clone();
/* 12113 */             param[i] *= (1.0D - delta / 2.0D);
/* 12114 */             pari = param[i];
/* 12115 */             if (i == 0) {
/* 12116 */               if (param[i] == 0.0D) paramTemp[i] = (-param[1] * delta / 20.0D);
/* 12117 */               pari = param[1] / 10.0D;
/* 12118 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 200.0D);
/* 12119 */               pari = range / 100.0D;
/*       */             }
/*       */             else {
/* 12122 */               if (param[i] == 0.0D) paramTemp[i] = (-3.0D * param[0] * delta / 2.0D);
/* 12123 */               pari = 3.0D * param[0];
/* 12124 */               if (param[i] == 0.0D) paramTemp[i] = (-range * delta / 70.0D);
/* 12125 */               pari = range / 35.0D;
/*       */             }
/* 12127 */             param[j] *= (1.0D - delta / 2.0D);
/* 12128 */             parj = param[j];
/* 12129 */             if (j == 0) {
/* 12130 */               if (param[j] == 0.0D) paramTemp[j] = (-param[1] * delta / 20.0D);
/* 12131 */               parj = param[1] / 10.0D;
/* 12132 */               if (param[j] == 0.0D) paramTemp[j] = (-range * delta / 200.0D);
/* 12133 */               parj = range / 100.0D;
/*       */             }
/*       */             else {
/* 12136 */               if (param[j] == 0.0D) paramTemp[j] = (-3.0D * param[0] * delta / 2.0D);
/* 12137 */               parj = 3.0D * param[0];
/* 12138 */               if (param[j] == 0.0D) paramTemp[j] = (-range * delta / 70.0D);
/* 12139 */               parj = range / 35.0D;
/*       */             }
/* 12141 */             double term4 = func.function(paramTemp);
/* 12142 */             System.out.println("4 " + paramTemp[0] + " " + paramTemp[1]);
/*       */             
/* 12144 */             System.out.println(term1 + " " + term2 + " " + term3 + " " + term4 + " " + (term1 - term2 - term3 + term4));
/* 12145 */             secondDeriv[i][j] = ((term1 - term2 - term3 + term4) / (pari * parj * delta * delta));
/*       */           }
/*       */         }
/*       */       }
/*       */       
/* 12150 */       PrintToScreen.print(secondDeriv);
/* 12151 */       Matrix mat = new Matrix(secondDeriv);
/* 12152 */       mat = mat.inverse();
/* 12153 */       mat = mat.times(sumOfSquares / (nData - nParam));
/* 12154 */       for (int i = 0; i < 2; i++) ret[i] = Math.sqrt(mat.getElement(i, i));
/* 12155 */       ret[2] = sumOfSquares;
/*       */     }
/*       */     
/*       */     
/* 12159 */     return ret;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Stat copy()
/*       */   {
/* 12168 */     Stat am = new Stat();
/*       */     
/* 12170 */     if (this.amWeights == null) {
/* 12171 */       am.amWeights = null;
/*       */     }
/*       */     else {
/* 12174 */       am.amWeights = this.amWeights;
/*       */     }
/* 12176 */     am.weightsSupplied = this.weightsSupplied;
/* 12177 */     am.upperOutlierDetails = new ArrayList();
/* 12178 */     if (this.upperOutlierDetails.size() != 0) {
/* 12179 */       Integer hold0 = (Integer)this.upperOutlierDetails.get(0);
/* 12180 */       am.upperOutlierDetails.add(hold0);
/* 12181 */       am.upperOutlierDetails.add(this.upperOutlierDetails.get(1));
/* 12182 */       int[] hold2 = (int[])this.upperOutlierDetails.get(2);
/* 12183 */       am.upperOutlierDetails.add(hold2);
/* 12184 */       am.upperOutlierDetails.add(this.upperOutlierDetails.get(3));
/*       */     }
/* 12186 */     am.upperDone = this.upperDone;
/* 12187 */     am.lowerOutlierDetails = new ArrayList();
/* 12188 */     if (this.lowerOutlierDetails.size() != 0) {
/* 12189 */       Integer hold0 = (Integer)this.lowerOutlierDetails.get(0);
/* 12190 */       am.lowerOutlierDetails.add(hold0);
/* 12191 */       am.lowerOutlierDetails.add(this.lowerOutlierDetails.get(1));
/* 12192 */       int[] hold2 = (int[])this.lowerOutlierDetails.get(2);
/* 12193 */       am.lowerOutlierDetails.add(hold2);
/* 12194 */       am.lowerOutlierDetails.add(this.lowerOutlierDetails.get(3));
/*       */     }
/* 12196 */     am.lowerDone = this.lowerDone;
/*       */     
/* 12198 */     am.length = this.length;
/* 12199 */     am.maxIndex = this.maxIndex;
/* 12200 */     am.minIndex = this.minIndex;
/* 12201 */     am.sumDone = this.sumDone;
/* 12202 */     am.productDone = this.productDone;
/* 12203 */     am.sumlongToDouble = this.sumlongToDouble;
/* 12204 */     am.productlongToDouble = this.productlongToDouble;
/* 12205 */     am.type = this.type;
/* 12206 */     if (this.originalTypes == null) {
/* 12207 */       am.originalTypes = null;
/*       */     }
/*       */     else {
/* 12210 */       am.originalTypes = ((int[])this.originalTypes.clone());
/*       */     }
/* 12212 */     if (this.sortedIndices == null) {
/* 12213 */       am.sortedIndices = null;
/*       */     }
/*       */     else {
/* 12216 */       am.sortedIndices = ((int[])this.sortedIndices.clone());
/*       */     }
/* 12218 */     am.suppressMessages = this.suppressMessages;
/* 12219 */     am.minmax = new ArrayList();
/* 12220 */     if (this.minmax.size() != 0) { short ss;
/* 12221 */       switch (this.type) {
/*       */       case 0: case 1: 
/* 12223 */         double dd = ((Double)this.minmax.get(0)).doubleValue();
/* 12224 */         am.minmax.add(new Double(dd));
/* 12225 */         dd = ((Double)this.minmax.get(1)).doubleValue();
/* 12226 */         am.minmax.add(new Double(dd));
/* 12227 */         break;
/*       */       case 4: case 5: 
/* 12229 */         long ll = ((Long)this.minmax.get(0)).longValue();
/* 12230 */         am.minmax.add(new Double(ll));
/* 12231 */         ll = ((Long)this.minmax.get(1)).longValue();
/* 12232 */         am.minmax.add(new Long(ll));
/* 12233 */         break;
/*       */       case 2: case 3: 
/* 12235 */         float ff = ((Float)this.minmax.get(0)).floatValue();
/* 12236 */         am.minmax.add(new Double(ff));
/* 12237 */         ff = ((Float)this.minmax.get(1)).floatValue();
/* 12238 */         am.minmax.add(new Double(ff));
/* 12239 */         break;
/*       */       case 6: case 7: 
/* 12241 */         int ii = ((Integer)this.minmax.get(0)).intValue();
/* 12242 */         am.minmax.add(new Integer(ii));
/* 12243 */         ii = ((Double)this.minmax.get(1)).intValue();
/* 12244 */         am.minmax.add(new Integer(ii));
/* 12245 */         break;
/*       */       case 8: case 9: 
/* 12247 */         ss = ((Short)this.minmax.get(0)).shortValue();
/* 12248 */         am.minmax.add(new Short(ss));
/* 12249 */         ss = ((Double)this.minmax.get(1)).shortValue();
/* 12250 */         am.minmax.add(new Short(ss));
/* 12251 */         break;
/*       */       case 10: case 11: 
/* 12253 */         byte bb = ((Byte)this.minmax.get(0)).byteValue();
/* 12254 */         am.minmax.add(new Byte(bb));
/* 12255 */         ss = (short)((Byte)this.minmax.get(1)).byteValue();
/* 12256 */         am.minmax.add(new Byte(bb));
/* 12257 */         break;
/* 12258 */       case 12:  BigDecimal bd = (BigDecimal)this.minmax.get(0);
/* 12259 */         am.minmax.add(bd);
/* 12260 */         bd = (BigDecimal)this.minmax.get(1);
/* 12261 */         am.minmax.add(bd);
/* 12262 */         bd = null;
/* 12263 */         break;
/* 12264 */       case 13:  BigInteger bi = (BigInteger)this.minmax.get(0);
/* 12265 */         am.minmax.add(bi);
/* 12266 */         bi = (BigInteger)this.minmax.get(1);
/* 12267 */         am.minmax.add(bi);
/* 12268 */         bi = null;
/* 12269 */         break;
/*       */       case 16: case 17: 
/* 12271 */         int iii = ((Integer)this.minmax.get(0)).intValue();
/* 12272 */         am.minmax.add(new Integer(iii));
/* 12273 */         iii = ((Double)this.minmax.get(1)).intValue();
/* 12274 */         am.minmax.add(new Integer(iii));
/*       */       }
/*       */       
/*       */     }
/*       */     
/* 12279 */     am.summ = new ArrayList();
/* 12280 */     if (this.summ.size() != 0) {
/* 12281 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/* 12286 */         double dd = ((Double)this.summ.get(0)).doubleValue();
/* 12287 */         am.summ.add(new Double(dd));
/* 12288 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/* 12298 */         if (this.sumlongToDouble) {
/* 12299 */           double dd2 = ((Double)this.summ.get(0)).doubleValue();
/* 12300 */           am.summ.add(new Double(dd2));
/*       */         }
/*       */         else {
/* 12303 */           long ll = ((Long)this.summ.get(0)).longValue();
/* 12304 */           am.summ.add(new Long(ll));
/*       */         }
/* 12306 */         break;
/* 12307 */       case 12:  BigDecimal bd = (BigDecimal)this.summ.get(0);
/* 12308 */         am.summ.add(bd);
/* 12309 */         break;
/* 12310 */       case 13:  BigInteger bi = (BigInteger)this.summ.get(0);
/* 12311 */         am.summ.add(bi);
/* 12312 */         break;
/* 12313 */       case 14:  Complex cc = (Complex)this.summ.get(0);
/* 12314 */         am.summ.add(cc);
/* 12315 */         break;
/* 12316 */       case 15:  Phasor pp = (Phasor)this.summ.get(0);
/* 12317 */         am.summ.add(pp);
/* 12318 */         break;
/* 12319 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/* 12323 */     am.productt = new ArrayList();
/* 12324 */     if (this.productt.size() != 0) {
/* 12325 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/* 12330 */         double dd = ((Double)this.productt.get(0)).doubleValue();
/* 12331 */         am.productt.add(new Double(dd));
/* 12332 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/* 12342 */         if (this.sumlongToDouble) {
/* 12343 */           double dd2 = ((Double)this.productt.get(0)).doubleValue();
/* 12344 */           am.productt.add(new Double(dd2));
/*       */         }
/*       */         else {
/* 12347 */           long ll = ((Long)this.productt.get(0)).longValue();
/* 12348 */           am.productt.add(new Long(ll));
/*       */         }
/* 12350 */         break;
/* 12351 */       case 12:  BigDecimal bd = (BigDecimal)this.productt.get(0);
/* 12352 */         am.productt.add(bd);
/* 12353 */         break;
/* 12354 */       case 13:  BigInteger bi = (BigInteger)this.productt.get(0);
/* 12355 */         am.productt.add(bi);
/* 12356 */         break;
/* 12357 */       case 14:  Complex cc = (Complex)this.productt.get(0);
/* 12358 */         am.productt.add(cc);
/* 12359 */         break;
/* 12360 */       case 15:  Phasor pp = (Phasor)this.productt.get(0);
/* 12361 */         am.productt.add(pp);
/* 12362 */         break;
/* 12363 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/*       */     
/* 12368 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 12370 */       double[] dd = (double[])getArray_as_double().clone();
/* 12371 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i]));
/* 12372 */       break;
/*       */     case 2: case 3: 
/* 12374 */       float[] ff = (float[])getArray_as_float().clone();
/* 12375 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(ff[i]));
/* 12376 */       break;
/*       */     case 4: case 5: 
/* 12378 */       long[] ll = (long[])getArray_as_long().clone();
/* 12379 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/* 12380 */       break;
/*       */     case 6: case 7: 
/* 12382 */       int[] ii = (int[])getArray_as_int().clone();
/* 12383 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(ii[i]));
/* 12384 */       break;
/*       */     case 8: case 9: 
/* 12386 */       short[] ss = (short[])getArray_as_short().clone();
/* 12387 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/* 12388 */       break;
/*       */     case 10: case 11: 
/* 12390 */       byte[] bb = (byte[])getArray_as_byte().clone();
/* 12391 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/* 12392 */       break;
/* 12393 */     case 12:  BigDecimal[] bd = (BigDecimal[])getArray_as_BigDecimal().clone();
/* 12394 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i]);
/* 12395 */       break;
/* 12396 */     case 13:  BigInteger[] bi = (BigInteger[])getArray_as_BigInteger().clone();
/* 12397 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/* 12398 */       break;
/* 12399 */     case 14:  Complex[] ccc = getArray_as_Complex();
/* 12400 */       for (int i = 0; i < this.length; i++) am.array.add(ccc[i].copy());
/* 12401 */       break;
/* 12402 */     case 15:  Phasor[] ppp = getArray_as_Phasor();
/* 12403 */       for (int i = 0; i < this.length; i++) am.array.add(ppp[i].copy());
/* 12404 */       break;
/*       */     case 16: case 17: 
/* 12406 */       char[] cc = (char[])getArray_as_char().clone();
/* 12407 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(cc[i]));
/* 12408 */       break;
/* 12409 */     case 18:  String[] sss = (String[])getArray_as_String().clone();
/* 12410 */       for (int i = 0; i < this.length; i++) { am.array.add(sss[i]);
/*       */       }
/*       */     }
/*       */     
/* 12414 */     return am;
/*       */   }
/*       */   
/*       */   public Stat plus(double constant) {
/* 12418 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(float constant) {
/* 12422 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(long constant) {
/* 12426 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(int constant) {
/* 12430 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(short constant) {
/* 12434 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(byte constant) {
/* 12438 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(char constant) {
/* 12442 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(BigDecimal constant) {
/* 12446 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(BigInteger constant) {
/* 12450 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Complex constant) {
/* 12454 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Phasor constant) {
/* 12458 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(String constant) {
/* 12462 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Double constant)
/*       */   {
/* 12467 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Float constant) {
/* 12471 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Long constant) {
/* 12475 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Integer constant) {
/* 12479 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Short constant) {
/* 12483 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Byte constant) {
/* 12487 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Character constant)
/*       */   {
/* 12492 */     return super.plus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Stat arrays) {
/* 12496 */     return super.plus(arrays).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(ArrayMaths arraym) {
/* 12500 */     return super.plus(arraym).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(ArrayList<Object> arrayl) {
/* 12504 */     return super.plus(arrayl).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Vector<Object> list) {
/* 12508 */     return super.plus(list).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(double[] array) {
/* 12512 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(float[] array) {
/* 12516 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(long[] array) {
/* 12520 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(int[] array) {
/* 12524 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(short[] array) {
/* 12528 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(byte[] array) {
/* 12532 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(char[] array) {
/* 12536 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(BigDecimal[] array) {
/* 12540 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(BigInteger[] array) {
/* 12544 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Complex[] array) {
/* 12548 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Phasor[] array) {
/* 12552 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(String[] array) {
/* 12556 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Double[] array) {
/* 12560 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Float[] array) {
/* 12564 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Long[] array) {
/* 12568 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Integer[] array) {
/* 12572 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Short[] array) {
/* 12576 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Byte[] array) {
/* 12580 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat plus(Character[] array) {
/* 12584 */     return super.plus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(double constant) {
/* 12588 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(float constant) {
/* 12592 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(long constant) {
/* 12596 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(int constant) {
/* 12600 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(short constant) {
/* 12604 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(byte constant) {
/* 12608 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(char constant) {
/* 12612 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(BigDecimal constant) {
/* 12616 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(BigInteger constant) {
/* 12620 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Complex constant) {
/* 12624 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Phasor constant) {
/* 12628 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Double constant) {
/* 12632 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Float constant) {
/* 12636 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Long constant) {
/* 12640 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Integer constant) {
/* 12644 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Short constant) {
/* 12648 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Byte constant) {
/* 12652 */     return super.minus(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Character constant)
/*       */   {
/* 12657 */     return super.minus(constant.charValue()).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Stat arrays) {
/* 12661 */     return super.minus(arrays).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(ArrayMaths arraym) {
/* 12665 */     return super.minus(arraym).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Vector<Object> vec) {
/* 12669 */     return super.minus(vec).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(ArrayList<Object> list) {
/* 12673 */     return super.minus(list).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(double[] array) {
/* 12677 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(float[] array) {
/* 12681 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(long[] array) {
/* 12685 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(int[] array) {
/* 12689 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(short[] array) {
/* 12693 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(byte[] array) {
/* 12697 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(BigDecimal[] array) {
/* 12701 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(BigInteger[] array) {
/* 12705 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Complex[] array) {
/* 12709 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Phasor[] array) {
/* 12713 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Double[] array) {
/* 12717 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Float[] array) {
/* 12721 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Long[] array) {
/* 12725 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Integer[] array) {
/* 12729 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Short[] array) {
/* 12733 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat minus(Byte[] array) {
/* 12737 */     return super.minus(array).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(double constant) {
/* 12741 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(float constant) {
/* 12745 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(long constant) {
/* 12749 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(int constant) {
/* 12753 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(short constant) {
/* 12757 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(byte constant) {
/* 12761 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(BigDecimal constant) {
/* 12765 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(BigInteger constant) {
/* 12769 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Complex constant) {
/* 12773 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Phasor constant) {
/* 12777 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Double constant) {
/* 12781 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Float constant) {
/* 12785 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Long constant) {
/* 12789 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Integer constant) {
/* 12793 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Short constant) {
/* 12797 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat times(Byte constant) {
/* 12801 */     return super.times(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(double constant) {
/* 12805 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(float constant) {
/* 12809 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(long constant) {
/* 12813 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(int constant) {
/* 12817 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(short constant) {
/* 12821 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(byte constant) {
/* 12825 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(BigDecimal constant) {
/* 12829 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(BigInteger constant) {
/* 12833 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Complex constant) {
/* 12837 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Phasor constant) {
/* 12841 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Double constant) {
/* 12845 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Float constant) {
/* 12849 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Long constant) {
/* 12853 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Integer constant) {
/* 12857 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Short constant) {
/* 12861 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat over(Byte constant) {
/* 12865 */     return super.over(constant).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(double n)
/*       */   {
/* 12870 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(float n) {
/* 12874 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(long n) {
/* 12878 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(int n) {
/* 12882 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(short n) {
/* 12886 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(byte n) {
/* 12890 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Double n) {
/* 12894 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Float n) {
/* 12898 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Long n) {
/* 12902 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Integer n) {
/* 12906 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Short n) {
/* 12910 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(Byte n) {
/* 12914 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(BigInteger n) {
/* 12918 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat pow(BigDecimal n) {
/* 12922 */     return super.pow(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat sqrt() {
/* 12926 */     return super.sqrt().toStat();
/*       */   }
/*       */   
/*       */   public Stat oneOverSqrt() {
/* 12930 */     return super.oneOverSqrt().toStat();
/*       */   }
/*       */   
/*       */   public Stat abs() {
/* 12934 */     return super.abs().toStat();
/*       */   }
/*       */   
/*       */   public Stat log() {
/* 12938 */     return super.log().toStat();
/*       */   }
/*       */   
/*       */   public Stat log2() {
/* 12942 */     return super.log2().toStat();
/*       */   }
/*       */   
/*       */   public Stat log10() {
/* 12946 */     return super.log10().toStat();
/*       */   }
/*       */   
/*       */   public Stat antilog10() {
/* 12950 */     return super.antilog10().toStat();
/*       */   }
/*       */   
/*       */   public Stat xLog2x() {
/* 12954 */     return super.xLog2x().toStat();
/*       */   }
/*       */   
/*       */   public Stat xLogEx() {
/* 12958 */     return super.xLogEx().toStat();
/*       */   }
/*       */   
/*       */   public Stat xLog10x() {
/* 12962 */     return super.xLog10x().toStat();
/*       */   }
/*       */   
/*       */   public Stat minusxLog2x() {
/* 12966 */     return super.minusxLog2x().toStat();
/*       */   }
/*       */   
/*       */   public Stat minusxLogEx() {
/* 12970 */     return super.minusxLogEx().toStat();
/*       */   }
/*       */   
/*       */   public Stat minusxLog10x() {
/* 12974 */     return super.minusxLog10x().toStat();
/*       */   }
/*       */   
/*       */   public Stat exp() {
/* 12978 */     return super.exp().toStat();
/*       */   }
/*       */   
/*       */   public Stat invert() {
/* 12982 */     return super.invert().toStat();
/*       */   }
/*       */   
/*       */   public Stat negate() {
/* 12986 */     return super.negate().toStat();
/*       */   }
/*       */   
/*       */   public Stat sort() {
/* 12990 */     return super.sort().toStat();
/*       */   }
/*       */   
/*       */   public Stat sort(int[] indices) {
/* 12994 */     return super.sort(indices).toStat();
/*       */   }
/*       */   
/*       */   public Stat reverse() {
/* 12998 */     return super.reverse().toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Stat xx) {
/* 13002 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(ArrayMaths xx) {
/* 13006 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(double[] xx) {
/* 13010 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(float[] xx) {
/* 13014 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(long[] xx) {
/* 13018 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(int[] xx) {
/* 13022 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(short[] xx) {
/* 13026 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(byte[] xx) {
/* 13030 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(char[] xx) {
/* 13034 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Double[] xx) {
/* 13038 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Float[] xx) {
/* 13042 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Long[] xx) {
/* 13046 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Integer[] xx) {
/* 13050 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Short[] xx) {
/* 13054 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Byte[] xx) {
/* 13058 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Character[] xx) {
/* 13062 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(String[] xx) {
/* 13066 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(BigDecimal[] xx) {
/* 13070 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(BigInteger[] xx) {
/* 13074 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Complex[] xx) {
/* 13078 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat concatenate(Phasor[] xx) {
/* 13082 */     return super.concatenate(xx).toStat();
/*       */   }
/*       */   
/*       */   public Stat truncate(int n) {
/* 13086 */     return super.truncate(n).toStat();
/*       */   }
/*       */   
/*       */   public Stat floor() {
/* 13090 */     return super.floor().toStat();
/*       */   }
/*       */   
/*       */   public Stat ceil() {
/* 13094 */     return super.ceil().toStat();
/*       */   }
/*       */   
/*       */   public Stat rint() {
/* 13098 */     return super.rint().toStat();
/*       */   }
/*       */   
/*       */   public Stat randomize() {
/* 13102 */     return super.randomize().toStat();
/*       */   }
/*       */   
/*       */   public Stat randomise() {
/* 13106 */     return super.randomize().toStat();
/*       */   }
/*       */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/Stat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */