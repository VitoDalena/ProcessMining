/*     */ package flanagan.analysis;
/*     */ 
/*     */ import flanagan.io.FileOutput;
/*     */ import flanagan.math.ArrayMaths;
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.math.Maximization;
/*     */ import flanagan.plot.PlotGraph;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.DateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoxCox
/*     */ {
/*  56 */   private double[] originalData = null;
/*  57 */   private double[] sortedOriginalData = null;
/*  58 */   private double[] standardizedOriginalData = null;
/*  59 */   private Stat sod = null;
/*  60 */   private double[] shiftedStandardizedOriginalData = null;
/*  61 */   private int[] originalIndices = null;
/*  62 */   private int nData = 0;
/*     */   
/*  64 */   private double originalRange = 0.0D;
/*  65 */   private double originalMinimum = 0.0D;
/*  66 */   private double originalMaximum = 0.0D;
/*  67 */   private double originalMean = 0.0D;
/*  68 */   private double originalMedian = 0.0D;
/*  69 */   private double originalStandardDeviation = 0.0D;
/*  70 */   private double originalVariance = 0.0D;
/*  71 */   private double originalMomentSkewness = 0.0D;
/*  72 */   private double originalMedianSkewness = 0.0D;
/*  73 */   private double originalQuartileSkewness = 0.0D;
/*  74 */   private double originalExcessKurtosis = 0.0D;
/*     */   
/*  76 */   private double standardizedOriginalRange = 0.0D;
/*  77 */   private double standardizedOriginalMinimum = 0.0D;
/*  78 */   private double standardizedOriginalMaximum = 0.0D;
/*  79 */   private double standardizedOriginalMean = 0.0D;
/*  80 */   private double standardizedOriginalMedian = 0.0D;
/*  81 */   private double standardizedOriginalStandardDeviation = 1.0D;
/*  82 */   private double standardizedOriginalVariance = 1.0D;
/*  83 */   private double standardizedOriginalMomentSkewness = 0.0D;
/*  84 */   private double standardizedOriginalMedianSkewness = 0.0D;
/*  85 */   private double standardizedOriginalQuartileSkewness = 0.0D;
/*  86 */   private double standardizedOriginalExcessKurtosis = 0.0D;
/*     */   
/*  88 */   private double originalSampleR = 0.0D;
/*  89 */   private double originalIntercept = 0.0D;
/*  90 */   private double originalGradient = 0.0D;
/*     */   
/*  92 */   private double[] transformedData = null;
/*  93 */   private double[] standardizedTransformedData = null;
/*  94 */   private double[] scaledTransformedData = null;
/*  95 */   private double[] sortedScaledTransformedData = null;
/*     */   
/*  97 */   private double transformedRange = 0.0D;
/*  98 */   private double transformedMinimum = 0.0D;
/*  99 */   private double transformedMaximum = 0.0D;
/* 100 */   private double transformedMean = 0.0D;
/* 101 */   private double transformedStandardDeviation = 0.0D;
/* 102 */   private double transformedMedian = 0.0D;
/* 103 */   private double transformedVariance = 0.0D;
/* 104 */   private double transformedMomentSkewness = 0.0D;
/* 105 */   private double transformedMedianSkewness = 0.0D;
/* 106 */   private double transformedQuartileSkewness = 0.0D;
/* 107 */   private double transformedExcessKurtosis = 0.0D;
/*     */   
/* 109 */   private double standardizedTransformedRange = 0.0D;
/* 110 */   private double standardizedTransformedMinimum = 0.0D;
/* 111 */   private double standardizedTransformedMaximum = 0.0D;
/* 112 */   private double standardizedTransformedMean = 0.0D;
/* 113 */   private double standardizedTransformedMedian = 0.0D;
/* 114 */   private double standardizedTransformedStandardDeviation = 1.0D;
/* 115 */   private double standardizedTransformedVariance = 1.0D;
/* 116 */   private double standardizedTransformedMomentSkewness = 0.0D;
/* 117 */   private double standardizedTransformedMedianSkewness = 0.0D;
/* 118 */   private double standardizedTransformedQuartileSkewness = 0.0D;
/* 119 */   private double standardizedTransformedExcessKurtosis = 0.0D;
/*     */   
/*     */ 
/* 122 */   private double lambdaOne = 0.0D;
/* 123 */   private double lambdaTwo = 0.0D;
/*     */   
/* 125 */   private double transformedSampleR = 0.0D;
/* 126 */   private double transformedIntercept = 0.0D;
/* 127 */   private double transformedGradient = 0.0D;
/*     */   
/* 129 */   private double[] uniformOrderMedians = null;
/* 130 */   private double[] gaussianOrderMedians = null;
/*     */   
/* 132 */   private boolean transformDone = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public BoxCox(double[] originalData)
/*     */   {
/* 138 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(float[] originalData) {
/* 142 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(int[] originalData) {
/* 146 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(long[] originalData) {
/* 150 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(short[] originalData) {
/* 154 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(byte[] originalData) {
/* 158 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(BigDecimal[] originalData) {
/* 162 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(BigInteger[] originalData) {
/* 166 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(Stat originalData) {
/* 170 */     this.sod = originalData;
/*     */   }
/*     */   
/*     */   public BoxCox(ArrayMaths amoriginalData) {
/* 174 */     this.sod = amoriginalData.toStat();
/*     */   }
/*     */   
/*     */   public BoxCox(ArrayList<Object> originalData) {
/* 178 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */   public BoxCox(Vector<Object> originalData) {
/* 182 */     this.sod = new Stat(originalData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDenominatorToN() {}
/*     */   
/*     */ 
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 194 */     this.originalData = this.sod.array_as_double();
/*     */     
/*     */ 
/* 197 */     this.originalMinimum = this.sod.minimum();
/* 198 */     this.originalMaximum = this.sod.maximum();
/* 199 */     this.originalMedian = this.sod.median();
/* 200 */     if (this.originalMinimum == this.originalMaximum) throw new IllegalArgumentException("A Box-Cox transformation cannot be performed on a data array of identical values");
/* 201 */     this.originalRange = (this.originalMaximum - this.originalMinimum);
/* 202 */     this.originalMean = this.sod.mean();
/* 203 */     this.originalStandardDeviation = this.sod.standardDeviation();
/* 204 */     this.originalVariance = this.sod.variance();
/* 205 */     this.originalMomentSkewness = this.sod.momentSkewness();
/* 206 */     this.originalMedianSkewness = this.sod.medianSkewness();
/* 207 */     this.originalQuartileSkewness = this.sod.quartileSkewness();
/* 208 */     this.originalExcessKurtosis = this.sod.excessKurtosis();
/*     */     
/*     */ 
/* 211 */     Stat sorted = this.sod.sort();
/* 212 */     this.sortedOriginalData = sorted.array();
/* 213 */     this.originalIndices = sorted.originalIndices();
/*     */     
/*     */ 
/* 216 */     this.standardizedOriginalData = this.sod.standardize();
/*     */     
/*     */ 
/* 219 */     this.standardizedOriginalMinimum = this.sod.minimum();
/* 220 */     this.standardizedOriginalMaximum = this.sod.maximum();
/* 221 */     this.standardizedOriginalMedian = this.sod.median();
/* 222 */     this.standardizedOriginalRange = (this.standardizedOriginalMaximum - this.standardizedOriginalMinimum);
/* 223 */     this.standardizedOriginalMean = 0.0D;
/* 224 */     this.standardizedOriginalStandardDeviation = 1.0D;
/* 225 */     this.standardizedOriginalVariance = 1.0D;
/* 226 */     this.standardizedOriginalMomentSkewness = this.sod.momentSkewness();
/* 227 */     this.standardizedOriginalMedianSkewness = this.sod.medianSkewness();
/* 228 */     this.standardizedOriginalQuartileSkewness = this.sod.quartileSkewness();
/* 229 */     this.standardizedOriginalExcessKurtosis = this.sod.excessKurtosis();
/*     */     
/*     */ 
/* 232 */     this.nData = this.originalData.length;
/*     */     
/*     */ 
/* 235 */     this.uniformOrderMedians = Stat.uniformOrderStatisticMedians(this.nData);
/*     */     
/*     */ 
/* 238 */     this.gaussianOrderMedians = Stat.gaussianOrderStatisticMedians(this.nData);
/*     */     
/*     */ 
/* 241 */     Regression reg = new Regression(this.gaussianOrderMedians, new ArrayMaths(this.standardizedOriginalData).sort().array());
/* 242 */     reg.linear();
/* 243 */     this.originalSampleR = reg.getSampleR();
/* 244 */     double[] coeff = reg.getBestEstimates();
/* 245 */     this.originalIntercept = coeff[0];
/* 246 */     this.originalGradient = coeff[1];
/*     */   }
/*     */   
/*     */   private double[] transform()
/*     */   {
/* 251 */     if (this.originalData == null) { throw new IllegalArgumentException("No data has been entered (via a constructor)");
/*     */     }
/*     */     
/* 254 */     initialize();
/*     */     
/*     */ 
/* 257 */     this.lambdaTwo = (0.1D * this.standardizedOriginalRange - this.standardizedOriginalMinimum);
/* 258 */     Stat st1 = this.sod.plus(this.lambdaTwo);
/* 259 */     this.shiftedStandardizedOriginalData = st1.getArray_as_double();
/*     */     
/*     */ 
/* 262 */     BoxCoxFunction bcf = new BoxCoxFunction();
/* 263 */     bcf.shiftedData = this.shiftedStandardizedOriginalData;
/* 264 */     bcf.nData = this.nData;
/* 265 */     bcf.yTransform = new double[this.nData];
/* 266 */     bcf.gaussianOrderMedians = this.gaussianOrderMedians;
/*     */     
/*     */ 
/* 269 */     Maximization max = new Maximization();
/*     */     
/*     */ 
/* 272 */     double[] start = { 1.0D };
/*     */     
/*     */ 
/* 275 */     double[] step = { 0.3D };
/*     */     
/*     */ 
/* 278 */     double maxzTol = 1.0E-9D;
/*     */     
/*     */ 
/* 281 */     max.nelderMead(bcf, start, step, maxzTol);
/*     */     
/*     */ 
/* 284 */     double[] coeff = max.getParamValues();
/* 285 */     double lambda1 = coeff[0];
/*     */     
/*     */ 
/* 288 */     double sampleR1 = max.getMaximum();
/*     */     
/*     */ 
/* 291 */     start[0] = (lambda1 - (start[0] - lambda1));
/* 292 */     max.nelderMead(bcf, start, step, maxzTol);
/* 293 */     coeff = max.getParamValues();
/* 294 */     this.lambdaOne = coeff[0];
/* 295 */     this.transformedSampleR = max.getMaximum();
/*     */     
/*     */ 
/* 298 */     if (sampleR1 > this.transformedSampleR) {
/* 299 */       this.transformedSampleR = sampleR1;
/* 300 */       this.lambdaOne = lambda1;
/*     */     }
/*     */     
/*     */ 
/* 304 */     this.transformedData = new double[this.nData];
/* 305 */     for (int i = 0; i < this.nData; i++) {
/* 306 */       this.transformedData[i] = ((Math.pow(this.shiftedStandardizedOriginalData[i], this.lambdaOne) - 1.0D) / this.lambdaOne);
/*     */     }
/*     */     
/*     */ 
/* 310 */     this.standardizedTransformedData = new Stat(this.transformedData).standardize();
/*     */     
/*     */ 
/* 313 */     Stat st3 = new Stat(this.transformedData);
/* 314 */     this.standardizedTransformedMinimum = st3.minimum();
/* 315 */     this.standardizedTransformedMaximum = st3.maximum();
/* 316 */     this.standardizedTransformedMedian = st3.median();
/* 317 */     this.standardizedTransformedRange = (this.standardizedTransformedMaximum - this.standardizedTransformedMinimum);
/* 318 */     this.standardizedTransformedMean = 0.0D;
/* 319 */     this.standardizedTransformedStandardDeviation = 1.0D;
/* 320 */     this.standardizedTransformedVariance = 1.0D;
/* 321 */     this.standardizedTransformedMomentSkewness = st3.momentSkewness();
/* 322 */     this.standardizedTransformedMedianSkewness = st3.medianSkewness();
/* 323 */     this.standardizedTransformedQuartileSkewness = st3.quartileSkewness();
/* 324 */     this.standardizedTransformedExcessKurtosis = st3.excessKurtosis();
/*     */     
/*     */ 
/* 327 */     Stat st4 = new Stat(this.standardizedTransformedData);
/* 328 */     st4 = st4.sort();
/* 329 */     double[] ordered = st4.array();
/* 330 */     Regression reg = new Regression(this.gaussianOrderMedians, ordered);
/* 331 */     reg.linear();
/* 332 */     coeff = reg.getBestEstimates();
/* 333 */     this.transformedIntercept = coeff[0];
/* 334 */     this.transformedGradient = coeff[1];
/*     */     
/*     */ 
/* 337 */     this.scaledTransformedData = Stat.scale(this.standardizedTransformedData, this.originalMean, this.originalStandardDeviation);
/*     */     
/*     */ 
/* 340 */     Stat st2 = new Stat(this.scaledTransformedData);
/* 341 */     this.transformedMinimum = st2.minimum();
/* 342 */     this.transformedMaximum = st2.maximum();
/* 343 */     this.transformedMedian = st2.median();
/* 344 */     this.transformedRange = (this.transformedMaximum - this.transformedMinimum);
/* 345 */     this.transformedMean = st2.mean();
/* 346 */     this.transformedStandardDeviation = st2.standardDeviation();
/* 347 */     this.transformedVariance = st2.variance();
/* 348 */     this.transformedMomentSkewness = st2.momentSkewness();
/* 349 */     this.transformedMedianSkewness = st2.medianSkewness();
/* 350 */     this.transformedQuartileSkewness = st2.quartileSkewness();
/* 351 */     this.transformedExcessKurtosis = st2.excessKurtosis();
/*     */     
/*     */ 
/* 354 */     Stat st5 = new Stat(this.scaledTransformedData);
/* 355 */     this.sortedScaledTransformedData = st5.sort().array();
/*     */     
/*     */ 
/* 358 */     this.transformDone = true;
/* 359 */     return this.transformedData;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double lambdaOne()
/*     */   {
/* 366 */     if (!this.transformDone) transform();
/* 367 */     return this.lambdaOne;
/*     */   }
/*     */   
/*     */   public double lambdaTwo()
/*     */   {
/* 372 */     if (!this.transformDone) transform();
/* 373 */     return this.lambdaTwo;
/*     */   }
/*     */   
/*     */   public double transformedCorrelationCoefficient()
/*     */   {
/* 378 */     if (!this.transformDone) transform();
/* 379 */     return this.transformedSampleR;
/*     */   }
/*     */   
/*     */   public double transformedGradient()
/*     */   {
/* 384 */     if (!this.transformDone) transform();
/* 385 */     return this.transformedGradient;
/*     */   }
/*     */   
/*     */   public double transformedIntercept()
/*     */   {
/* 390 */     if (!this.transformDone) transform();
/* 391 */     return this.transformedIntercept;
/*     */   }
/*     */   
/*     */   public double originalCorrelationCoefficient()
/*     */   {
/* 396 */     if (!this.transformDone) transform();
/* 397 */     return this.originalSampleR;
/*     */   }
/*     */   
/*     */   public double originalGradient()
/*     */   {
/* 402 */     if (!this.transformDone) transform();
/* 403 */     return this.originalGradient;
/*     */   }
/*     */   
/*     */   public double originalIntercept()
/*     */   {
/* 408 */     if (!this.transformDone) transform();
/* 409 */     return this.originalIntercept;
/*     */   }
/*     */   
/*     */   public double[] originalData()
/*     */   {
/* 414 */     if (!this.transformDone) transform();
/* 415 */     return this.originalData;
/*     */   }
/*     */   
/*     */   public double[] standardizedOriginalData()
/*     */   {
/* 420 */     if (!this.transformDone) transform();
/* 421 */     return this.standardizedOriginalData;
/*     */   }
/*     */   
/*     */   public double[] sortedOriginalData()
/*     */   {
/* 426 */     if (!this.transformDone) transform();
/* 427 */     return this.sortedOriginalData;
/*     */   }
/*     */   
/*     */   public double[] shiftedStandardizedOriginalata()
/*     */   {
/* 432 */     if (!this.transformDone) transform();
/* 433 */     return this.shiftedStandardizedOriginalData;
/*     */   }
/*     */   
/*     */   public double[] transformedData()
/*     */   {
/* 438 */     if (!this.transformDone) transform();
/* 439 */     return this.transformedData;
/*     */   }
/*     */   
/*     */   public double[] scaledTransformedData()
/*     */   {
/* 444 */     if (!this.transformDone) transform();
/* 445 */     return this.scaledTransformedData;
/*     */   }
/*     */   
/*     */   public double[] standardizedTransformedData()
/*     */   {
/* 450 */     if (!this.transformDone) transform();
/* 451 */     return this.standardizedTransformedData;
/*     */   }
/*     */   
/*     */   public double[] orderedTransformedData()
/*     */   {
/* 456 */     if (!this.transformDone) transform();
/* 457 */     ArrayMaths am = new ArrayMaths(this.transformedData);
/* 458 */     double[] ordered = am.sort().array();
/* 459 */     return ordered;
/*     */   }
/*     */   
/*     */   public double[] orderedScaledTransformedData()
/*     */   {
/* 464 */     if (!this.transformDone) transform();
/* 465 */     return this.sortedScaledTransformedData;
/*     */   }
/*     */   
/*     */ 
/*     */   public double originalMean()
/*     */   {
/* 471 */     if (!this.transformDone) transform();
/* 472 */     return this.originalMean;
/*     */   }
/*     */   
/*     */   public double originalMedian()
/*     */   {
/* 477 */     if (!this.transformDone) transform();
/* 478 */     return this.originalMedian;
/*     */   }
/*     */   
/*     */   public double originalStandardDeviation()
/*     */   {
/* 483 */     if (!this.transformDone) transform();
/* 484 */     return this.originalStandardDeviation;
/*     */   }
/*     */   
/*     */   public double originalStandardError()
/*     */   {
/* 489 */     return this.originalStandardDeviation / Math.sqrt(this.nData);
/*     */   }
/*     */   
/*     */   public double originalVariance()
/*     */   {
/* 494 */     if (!this.transformDone) transform();
/* 495 */     return this.originalVariance;
/*     */   }
/*     */   
/*     */   public double originalMomentSkewness()
/*     */   {
/* 500 */     if (!this.transformDone) transform();
/* 501 */     return this.originalMomentSkewness;
/*     */   }
/*     */   
/*     */   public double originalMedianSkewness()
/*     */   {
/* 506 */     if (!this.transformDone) transform();
/* 507 */     return this.originalMedianSkewness;
/*     */   }
/*     */   
/*     */   public double originalQuartiletSkewness()
/*     */   {
/* 512 */     if (!this.transformDone) transform();
/* 513 */     return this.originalQuartileSkewness;
/*     */   }
/*     */   
/*     */   public double originalExcessKurtosis()
/*     */   {
/* 518 */     if (!this.transformDone) transform();
/* 519 */     return this.originalExcessKurtosis;
/*     */   }
/*     */   
/*     */   public double originalMaximum()
/*     */   {
/* 524 */     if (!this.transformDone) transform();
/* 525 */     return this.originalMaximum;
/*     */   }
/*     */   
/*     */   public double originalMinimum()
/*     */   {
/* 530 */     if (!this.transformDone) transform();
/* 531 */     return this.originalMinimum;
/*     */   }
/*     */   
/*     */   public double originalRange()
/*     */   {
/* 536 */     if (!this.transformDone) transform();
/* 537 */     return this.originalRange;
/*     */   }
/*     */   
/*     */ 
/*     */   public double transformedMean()
/*     */   {
/* 543 */     if (!this.transformDone) transform();
/* 544 */     return this.transformedMean;
/*     */   }
/*     */   
/*     */   public double transformedMedian()
/*     */   {
/* 549 */     if (!this.transformDone) transform();
/* 550 */     return this.transformedMedian;
/*     */   }
/*     */   
/*     */ 
/*     */   public double transformedStandardDeviation()
/*     */   {
/* 556 */     if (!this.transformDone) transform();
/* 557 */     return this.transformedStandardDeviation;
/*     */   }
/*     */   
/*     */   public double transformedStandardError()
/*     */   {
/* 562 */     if (!this.transformDone) transform();
/* 563 */     return this.transformedStandardDeviation / Math.sqrt(this.nData);
/*     */   }
/*     */   
/*     */   public double transformedVariance()
/*     */   {
/* 568 */     if (!this.transformDone) transform();
/* 569 */     return this.transformedVariance;
/*     */   }
/*     */   
/*     */   public double transformedMomentSkewness()
/*     */   {
/* 574 */     if (!this.transformDone) transform();
/* 575 */     return this.transformedMomentSkewness;
/*     */   }
/*     */   
/*     */   public double transformedMedianSkewness()
/*     */   {
/* 580 */     if (!this.transformDone) transform();
/* 581 */     return this.transformedMedianSkewness;
/*     */   }
/*     */   
/*     */   public double transformedQuartileSkewness()
/*     */   {
/* 586 */     if (!this.transformDone) transform();
/* 587 */     return this.transformedQuartileSkewness;
/*     */   }
/*     */   
/*     */   public double transformedExcessKurtosis()
/*     */   {
/* 592 */     if (!this.transformDone) transform();
/* 593 */     return this.transformedExcessKurtosis;
/*     */   }
/*     */   
/*     */   public double transformedMaximum()
/*     */   {
/* 598 */     if (!this.transformDone) transform();
/* 599 */     return this.transformedMaximum;
/*     */   }
/*     */   
/*     */   public double transformedMinimum()
/*     */   {
/* 604 */     if (!this.transformDone) transform();
/* 605 */     return this.transformedMinimum;
/*     */   }
/*     */   
/*     */   public double transformedRange()
/*     */   {
/* 610 */     if (!this.transformDone) transform();
/* 611 */     return this.transformedRange;
/*     */   }
/*     */   
/*     */ 
/*     */   public void transformedProbabilityPlot()
/*     */   {
/* 617 */     if (!this.transformDone) { transform();
/*     */     }
/* 619 */     double[][] data = PlotGraph.data(2, this.nData);
/* 620 */     data[0] = this.gaussianOrderMedians;
/* 621 */     data[1] = new ArrayMaths(this.standardizedTransformedData).sort().array();
/*     */     
/* 623 */     data[2] = this.gaussianOrderMedians;
/* 624 */     for (int i = 0; i < this.nData; i++) {
/* 625 */       data[3][i] = (this.transformedIntercept + this.transformedGradient * this.gaussianOrderMedians[i]);
/*     */     }
/* 627 */     PlotGraph pg = new PlotGraph(data);
/* 628 */     int[] points = { 4, 0 };
/* 629 */     pg.setPoint(points);
/* 630 */     int[] lines = { 0, 3 };
/* 631 */     pg.setLine(lines);
/* 632 */     pg.setXaxisLegend("Gaussian [0,1] Order Statistic Medians");
/* 633 */     pg.setYaxisLegend("Ordered Response Values");
/* 634 */     String legend1 = "Gausssian probability plot:  Box-Cox transformed data";
/* 635 */     String legend2 = "lambdaOne = " + Fmath.truncate(this.lambdaOne, 4) + ",  lambdaTwo = " + Fmath.truncate(this.lambdaTwo, 4) + ",   gradient = " + Fmath.truncate(this.transformedGradient, 4) + ", intercept = " + Fmath.truncate(this.transformedIntercept, 4) + ",  R = " + Fmath.truncate(this.transformedSampleR, 4);
/* 636 */     pg.setGraphTitle(legend1);
/* 637 */     pg.setGraphTitle2(legend2);
/* 638 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void originalProbabilityPlot()
/*     */   {
/* 643 */     double[][] data = PlotGraph.data(2, this.nData);
/* 644 */     data[0] = this.gaussianOrderMedians;
/* 645 */     data[1] = new ArrayMaths(this.standardizedOriginalData).sort().array();
/* 646 */     data[2] = this.gaussianOrderMedians;
/* 647 */     for (int i = 0; i < this.nData; i++) {
/* 648 */       data[3][i] = (this.originalIntercept + this.originalGradient * this.gaussianOrderMedians[i]);
/*     */     }
/* 650 */     PlotGraph pg = new PlotGraph(data);
/* 651 */     int[] points = { 4, 0 };
/* 652 */     pg.setPoint(points);
/* 653 */     int[] lines = { 0, 3 };
/* 654 */     pg.setLine(lines);
/* 655 */     pg.setXaxisLegend("Gaussian [0,1] Order Statistic Medians");
/* 656 */     pg.setYaxisLegend("Ordered Response Values");
/* 657 */     String legend1 = "Gausssian probability plot: original data for a Box-Cox transformation";
/* 658 */     String legend2 = "gradient = " + Fmath.truncate(this.originalGradient, 4) + ", intercept = " + Fmath.truncate(this.originalIntercept, 4) + ",  R = " + Fmath.truncate(this.originalSampleR, 4);
/* 659 */     pg.setGraphTitle(legend1);
/* 660 */     pg.setGraphTitle2(legend2);
/* 661 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void analysis()
/*     */   {
/* 666 */     analysis("BoxCoxAnalysis.txt");
/*     */   }
/*     */   
/*     */   public void analysis(String title) {
/* 670 */     if (!this.transformDone) { transform();
/*     */     }
/* 672 */     originalProbabilityPlot();
/* 673 */     transformedProbabilityPlot();
/*     */     
/* 675 */     int posdot = title.indexOf(".");
/* 676 */     if (posdot == -1) { title = title + ".txt";
/*     */     }
/* 678 */     FileOutput fout = new FileOutput(title);
/* 679 */     fout.println("Box-Cox Analysis");
/* 680 */     fout.println("File name:   " + title);
/* 681 */     Date d = new Date();
/* 682 */     String day = DateFormat.getDateInstance().format(d);
/* 683 */     String tim = DateFormat.getTimeInstance().format(d);
/* 684 */     fout.println("Program executed at " + tim + " on " + day);
/* 685 */     fout.println();
/*     */     
/* 687 */     int field1 = 30;
/* 688 */     int field2 = 15;
/* 689 */     fout.print("Box-Cox lambda one", field1);
/* 690 */     fout.println(Fmath.truncate(this.lambdaOne, 4));
/* 691 */     fout.print("Box-Cox lambda two", field1);
/* 692 */     fout.println(Fmath.truncate(this.lambdaTwo, 4));
/* 693 */     fout.println();
/*     */     
/* 695 */     fout.print("  ", field1);
/* 696 */     fout.print("Transformed", field2);
/* 697 */     fout.println("Original   ");
/* 698 */     fout.print("  ", field1);
/* 699 */     fout.print("scaled data", field2);
/* 700 */     fout.println("data   ");
/*     */     
/* 702 */     fout.println("Gaussian Probability plot ");
/* 703 */     fout.print("  Correlation coefficient", field1);
/* 704 */     fout.print(Fmath.truncate(this.transformedSampleR, 4), field2);
/* 705 */     fout.println(Fmath.truncate(this.originalSampleR, 4));
/*     */     
/* 707 */     fout.print("  Gradient", field1);
/* 708 */     fout.print(Fmath.truncate(this.transformedGradient, 4), field2);
/* 709 */     fout.println(Fmath.truncate(this.originalGradient, 4));
/*     */     
/* 711 */     fout.print("  Intercept", field1);
/* 712 */     fout.print(Fmath.truncate(this.transformedIntercept, 4), field2);
/* 713 */     fout.println(Fmath.truncate(this.originalIntercept, 4));
/* 714 */     fout.println();
/*     */     
/* 716 */     fout.print("Data ");
/* 717 */     fout.println();
/* 718 */     fout.print("Mean", field1);
/* 719 */     fout.print(Fmath.truncate(this.transformedMean, 4), field2);
/* 720 */     fout.println(Fmath.truncate(this.originalMean, 4));
/*     */     
/* 722 */     fout.print("Median", field1);
/* 723 */     fout.print(Fmath.truncate(this.transformedMedian, 4), field2);
/* 724 */     fout.println(Fmath.truncate(this.originalMedian, 4));
/*     */     
/* 726 */     fout.print("Standard deviation", field1);
/* 727 */     fout.print(Fmath.truncate(this.transformedStandardDeviation, 4), field2);
/* 728 */     fout.println(Fmath.truncate(this.originalStandardDeviation, 4));
/*     */     
/* 730 */     fout.print("Standard error", field1);
/* 731 */     fout.print(Fmath.truncate(this.transformedStandardDeviation / Math.sqrt(this.nData), 4), field2);
/* 732 */     fout.println(Fmath.truncate(this.originalStandardDeviation / Math.sqrt(this.nData), 4));
/*     */     
/* 734 */     fout.print("Moment skewness", field1);
/* 735 */     fout.print(Fmath.truncate(this.transformedMomentSkewness, 4), field2);
/* 736 */     fout.println(Fmath.truncate(this.originalMomentSkewness, 4));
/*     */     
/* 738 */     fout.print("Median skewness", field1);
/* 739 */     fout.print(Fmath.truncate(this.transformedMedianSkewness, 4), field2);
/* 740 */     fout.println(Fmath.truncate(this.originalMedianSkewness, 4));
/*     */     
/* 742 */     fout.print("Quartile skewness", field1);
/* 743 */     fout.print(Fmath.truncate(this.transformedQuartileSkewness, 4), field2);
/* 744 */     fout.println(Fmath.truncate(this.originalQuartileSkewness, 4));
/*     */     
/* 746 */     fout.print("Excess kurtosis", field1);
/* 747 */     fout.print(Fmath.truncate(this.transformedExcessKurtosis, 4), field2);
/* 748 */     fout.println(Fmath.truncate(this.originalExcessKurtosis, 4));
/*     */     
/* 750 */     fout.print("Minimum", field1);
/* 751 */     fout.print(Fmath.truncate(this.transformedMinimum, 4), field2);
/* 752 */     fout.println(Fmath.truncate(this.originalMinimum, 4));
/*     */     
/* 754 */     fout.print("Maximum", field1);
/* 755 */     fout.print(Fmath.truncate(this.transformedMaximum, 4), field2);
/* 756 */     fout.println(Fmath.truncate(this.originalMaximum, 4));
/*     */     
/* 758 */     fout.print("Range", field1);
/* 759 */     fout.print(Fmath.truncate(this.transformedRange, 4), field2);
/* 760 */     fout.println(Fmath.truncate(this.originalRange, 4));
/*     */     
/* 762 */     fout.close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/BoxCox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */