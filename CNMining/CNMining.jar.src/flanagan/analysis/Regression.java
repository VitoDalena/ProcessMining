/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.io.Db;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
/*      */ import javax.swing.JOptionPane;
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
/*      */ public class Regression
/*      */ {
/*   72 */   protected int nData0 = 0;
/*   73 */   protected int nData = 0;
/*   74 */   protected int nXarrays = 1;
/*   75 */   protected int nYarrays = 1;
/*   76 */   protected int nTerms = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   81 */   protected int degreesOfFreedom = 0;
/*   82 */   protected double[][] xData = (double[][])null;
/*   83 */   protected double[] yData = null;
/*   84 */   protected double[] yCalc = null;
/*   85 */   protected double[] weight = null;
/*   86 */   protected double[] residual = null;
/*   87 */   protected double[] residualW = null;
/*   88 */   protected boolean weightOpt = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   95 */   protected int weightFlag = 0;
/*   96 */   protected String[] weightWord = { "", "Weighted " };
/*      */   
/*   98 */   protected double[] best = null;
/*   99 */   protected double[] bestSd = null;
/*  100 */   protected double[] pseudoSd = null;
/*  101 */   protected double[] tValues = null;
/*  102 */   protected double[] pValues = null;
/*      */   
/*  104 */   protected double chiSquare = NaN.0D;
/*  105 */   protected double reducedChiSquare = NaN.0D;
/*  106 */   protected double sumOfSquares = NaN.0D;
/*  107 */   protected double lastSSnoConstraint = 0.0D;
/*  108 */   protected double[][] covar = (double[][])null;
/*  109 */   protected double[][] corrCoeff = (double[][])null;
/*  110 */   protected double sampleR = NaN.0D;
/*  111 */   protected double sampleR2 = NaN.0D;
/*  112 */   protected double adjustedR = NaN.0D;
/*  113 */   protected double adjustedR2 = NaN.0D;
/*  114 */   protected double multipleF = NaN.0D;
/*  115 */   protected double adjustedF = NaN.0D;
/*      */   
/*  117 */   protected String[] paraName = null;
/*  118 */   protected int prec = 4;
/*  119 */   protected int field = 13;
/*      */   
/*  121 */   protected int lastMethod = -1;
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
/*  166 */   protected double kayValue = 0.0D;
/*      */   
/*  168 */   protected boolean frechetWeibull = true;
/*  169 */   protected boolean linNonLin = true;
/*  170 */   protected boolean trueFreq = false;
/*      */   
/*      */ 
/*  173 */   protected String xLegend = "x axis values";
/*  174 */   protected String yLegend = "y axis values";
/*  175 */   protected String graphTitle = " ";
/*  176 */   protected boolean legendCheck = false;
/*  177 */   protected boolean supressPrint = false;
/*  178 */   protected boolean supressYYplot = false;
/*  179 */   protected boolean supressErrorMessages = false;
/*      */   
/*      */ 
/*  182 */   protected boolean nlrStatus = true;
/*      */   
/*      */ 
/*  185 */   protected int scaleOpt = 0;
/*      */   
/*      */ 
/*      */ 
/*  189 */   protected double[] scale = null;
/*  190 */   protected boolean zeroCheck = false;
/*      */   
/*  192 */   protected boolean penalty = false;
/*  193 */   protected boolean sumPenalty = false;
/*  194 */   protected int nConstraints = 0;
/*  195 */   protected int nSumConstraints = 0;
/*  196 */   protected int maxConstraintIndex = -1;
/*  197 */   protected double constraintTolerance = 1.0E-4D;
/*  198 */   protected ArrayList<Object> penalties = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  204 */   protected ArrayList<Object> sumPenalties = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  212 */   protected int[] penaltyCheck = null;
/*      */   
/*  214 */   protected int[] sumPenaltyCheck = null;
/*      */   
/*  216 */   protected double penaltyWeight = 1.0E30D;
/*  217 */   protected int[] penaltyParam = null;
/*  218 */   protected int[][] sumPenaltyParam = (int[][])null;
/*  219 */   protected double[][] sumPlusOrMinus = (double[][])null;
/*  220 */   protected int[] sumPenaltyNumber = null;
/*      */   
/*  222 */   protected double[] constraints = null;
/*  223 */   protected double[] sumConstraints = null;
/*  224 */   protected int constraintMethod = 0;
/*      */   
/*      */ 
/*  227 */   protected boolean scaleFlag = true;
/*      */   
/*  229 */   protected double yScaleFactor = 1.0D;
/*  230 */   protected int nMax = 3000;
/*  231 */   protected int nIter = 0;
/*  232 */   protected int konvge = 3;
/*  233 */   protected int kRestart = 0;
/*  234 */   protected double fMin = -1.0D;
/*  235 */   protected double fTol = 1.0E-9D;
/*  236 */   protected double rCoeff = 1.0D;
/*  237 */   protected double eCoeff = 2.0D;
/*  238 */   protected double cCoeff = 0.5D;
/*  239 */   protected double[] startH = null;
/*  240 */   protected double[] step = null;
/*  241 */   protected double dStep = 0.5D;
/*  242 */   protected double[][] grad = (double[][])null;
/*  243 */   protected double delta = 1.0E-4D;
/*  244 */   protected boolean invertFlag = true;
/*      */   
/*  246 */   protected boolean posVarFlag = true;
/*      */   
/*  248 */   protected int minTest = 0;
/*      */   
/*      */ 
/*  251 */   protected double simplexSd = 0.0D;
/*  252 */   protected boolean statFlag = true;
/*      */   
/*  254 */   protected boolean plotOpt = true;
/*      */   
/*  256 */   protected boolean multipleY = false;
/*      */   
/*      */ 
/*  259 */   protected double[] values = null;
/*  260 */   protected boolean[] fixed = null;
/*      */   
/*  262 */   protected boolean ignoreDofFcheck = false;
/*      */   
/*  264 */   protected boolean nFactorOption = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  270 */   protected static double histTol = 1.0001D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Regression() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Regression(double[][] xData, double[] yData, double[] weight)
/*      */   {
/*  282 */     int n = weight.length;
/*  283 */     this.nData0 = yData.length;
/*  284 */     weight = checkForZeroWeights(weight);
/*  285 */     if (this.weightOpt) this.weightFlag = 1;
/*  286 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[][] xxData, double[][] yyData, double[][] wWeight)
/*      */   {
/*  291 */     this.multipleY = true;
/*  292 */     int nY1 = yyData.length;
/*  293 */     this.nYarrays = nY1;
/*  294 */     int nY2 = yyData[0].length;
/*  295 */     this.nData0 = nY2;
/*  296 */     int nX1 = xxData.length;
/*  297 */     int nX2 = xxData[0].length;
/*  298 */     double[] yData = new double[nY1 * nY2];
/*  299 */     double[] weight = new double[nY1 * nY2];
/*  300 */     double[][] xData = new double[nY1 * nY2][nX1];
/*  301 */     int ii = 0;
/*  302 */     for (int i = 0; i < nY1; i++) {
/*  303 */       int nY = yyData[i].length;
/*  304 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  305 */       int nX = xxData[i].length;
/*  306 */       if (nY != nX) throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
/*  307 */       for (int j = 0; j < nY2; j++) {
/*  308 */         yData[ii] = yyData[i][j];
/*  309 */         xData[ii][i] = xxData[i][j];
/*  310 */         weight[ii] = wWeight[i][j];
/*  311 */         ii++;
/*      */       }
/*      */     }
/*  314 */     weight = checkForZeroWeights(weight);
/*  315 */     if (this.weightOpt) this.weightFlag = 1;
/*  316 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[] xxData, double[] yData, double[] weight)
/*      */   {
/*  321 */     this.nData0 = yData.length;
/*  322 */     int n = xxData.length;
/*  323 */     int m = weight.length;
/*  324 */     double[][] xData = new double[1][n];
/*  325 */     for (int i = 0; i < n; i++) {
/*  326 */       xData[0][i] = xxData[i];
/*      */     }
/*      */     
/*  329 */     weight = checkForZeroWeights(weight);
/*  330 */     if (this.weightOpt) this.weightFlag = 1;
/*  331 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public Regression(double[] xxData, double[][] yyData, double[][] wWeight)
/*      */   {
/*  337 */     this.multipleY = true;
/*  338 */     int nY1 = yyData.length;
/*  339 */     this.nYarrays = nY1;
/*  340 */     int nY2 = yyData[0].length;
/*  341 */     this.nData0 = nY2;
/*  342 */     double[] yData = new double[nY1 * nY2];
/*  343 */     double[] weight = new double[nY1 * nY2];
/*  344 */     int ii = 0;
/*  345 */     for (int i = 0; i < nY1; i++) {
/*  346 */       int nY = yyData[i].length;
/*  347 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  348 */       for (int j = 0; j < nY2; j++) {
/*  349 */         yData[ii] = yyData[i][j];
/*  350 */         weight[ii] = wWeight[i][j];
/*  351 */         ii++;
/*      */       }
/*      */     }
/*  354 */     int n = xxData.length;
/*  355 */     if (n != nY2) throw new IllegalArgumentException("x and y data lengths must be the same");
/*  356 */     double[][] xData = new double[1][nY1 * n];
/*  357 */     ii = 0;
/*  358 */     for (int j = 0; j < nY1; j++) {
/*  359 */       for (int i = 0; i < n; i++) {
/*  360 */         xData[0][ii] = xxData[i];
/*  361 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  365 */     weight = checkForZeroWeights(weight);
/*  366 */     if (this.weightOpt) this.weightFlag = 1;
/*  367 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[][] xData, double[] yData)
/*      */   {
/*  372 */     this.nData0 = yData.length;
/*  373 */     int n = yData.length;
/*  374 */     double[] weight = new double[n];
/*      */     
/*  376 */     this.weightOpt = false;
/*  377 */     this.weightFlag = 0;
/*  378 */     for (int i = 0; i < n; i++) { weight[i] = 1.0D;
/*      */     }
/*  380 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[][] xxData, double[][] yyData)
/*      */   {
/*  385 */     this.multipleY = true;
/*  386 */     int nY1 = yyData.length;
/*  387 */     this.nYarrays = nY1;
/*  388 */     int nY2 = yyData[0].length;
/*  389 */     this.nData0 = nY2;
/*  390 */     int nX1 = xxData.length;
/*  391 */     int nX2 = xxData[0].length;
/*  392 */     double[] yData = new double[nY1 * nY2];
/*  393 */     if (nY1 != nX1) throw new IllegalArgumentException("Multiple xData and yData arrays of different overall dimensions not supported");
/*  394 */     double[][] xData = new double[1][nY1 * nY2];
/*  395 */     int ii = 0;
/*  396 */     for (int i = 0; i < nY1; i++) {
/*  397 */       int nY = yyData[i].length;
/*  398 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  399 */       int nX = xxData[i].length;
/*  400 */       if (nY != nX) throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
/*  401 */       for (int j = 0; j < nY2; j++) {
/*  402 */         yData[ii] = yyData[i][j];
/*  403 */         xData[0][ii] = xxData[i][j];
/*  404 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  408 */     int n = yData.length;
/*  409 */     double[] weight = new double[n];
/*      */     
/*  411 */     this.weightOpt = false;
/*  412 */     for (int i = 0; i < n; i++) weight[i] = 1.0D;
/*  413 */     this.weightFlag = 0;
/*      */     
/*  415 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[] xxData, double[] yData)
/*      */   {
/*  420 */     this.nData0 = yData.length;
/*  421 */     int n = xxData.length;
/*  422 */     double[][] xData = new double[1][n];
/*  423 */     double[] weight = new double[n];
/*      */     
/*  425 */     for (int i = 0; i < n; i++) { xData[0][i] = xxData[i];
/*      */     }
/*  427 */     this.weightOpt = false;
/*  428 */     this.weightFlag = 0;
/*  429 */     for (int i = 0; i < n; i++) { weight[i] = 1.0D;
/*      */     }
/*  431 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public Regression(double[] xxData, double[][] yyData)
/*      */   {
/*  436 */     this.multipleY = true;
/*  437 */     int nY1 = yyData.length;
/*  438 */     this.nYarrays = nY1;
/*  439 */     int nY2 = yyData[0].length;
/*  440 */     this.nData0 = nY2;
/*  441 */     double[] yData = new double[nY1 * nY2];
/*  442 */     int ii = 0;
/*  443 */     for (int i = 0; i < nY1; i++) {
/*  444 */       int nY = yyData[i].length;
/*  445 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  446 */       for (int j = 0; j < nY2; j++) {
/*  447 */         yData[ii] = yyData[i][j];
/*  448 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  452 */     double[][] xData = new double[1][nY1 * nY2];
/*  453 */     double[] weight = new double[nY1 * nY2];
/*      */     
/*  455 */     ii = 0;
/*  456 */     int n = xxData.length;
/*  457 */     for (int j = 0; j < nY1; j++) {
/*  458 */       for (int i = 0; i < n; i++) {
/*  459 */         xData[0][ii] = xxData[i];
/*  460 */         weight[ii] = 1.0D;
/*  461 */         ii++;
/*      */       }
/*      */     }
/*  464 */     this.weightOpt = false;
/*  465 */     this.weightFlag = 0;
/*      */     
/*  467 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public Regression(double[] xxData, double binWidth, double binZero)
/*      */   {
/*  473 */     double[][] data = histogramBins(xxData, binWidth, binZero);
/*  474 */     int n = data[0].length;
/*  475 */     this.nData0 = n;
/*  476 */     double[][] xData = new double[1][n];
/*  477 */     double[] yData = new double[n];
/*  478 */     double[] weight = new double[n];
/*  479 */     for (int i = 0; i < n; i++) {
/*  480 */       xData[0][i] = data[0][i];
/*  481 */       yData[i] = data[1][i];
/*      */     }
/*  483 */     boolean flag = setTrueFreqWeights(yData, weight);
/*  484 */     if (flag) {
/*  485 */       this.trueFreq = true;
/*  486 */       this.weightOpt = true;
/*  487 */       this.weightFlag = 1;
/*      */     }
/*      */     else {
/*  490 */       this.trueFreq = false;
/*  491 */       this.weightOpt = false;
/*  492 */       this.weightFlag = 0;
/*      */     }
/*  494 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public Regression(double[] xxData, double binWidth)
/*      */   {
/*  500 */     double[][] data = histogramBins(xxData, binWidth);
/*  501 */     int n = data[0].length;
/*  502 */     this.nData0 = n;
/*  503 */     double[][] xData = new double[1][n];
/*  504 */     double[] yData = new double[n];
/*  505 */     double[] weight = new double[n];
/*  506 */     for (int i = 0; i < n; i++) {
/*  507 */       xData[0][i] = data[0][i];
/*  508 */       yData[i] = data[1][i];
/*      */     }
/*  510 */     boolean flag = setTrueFreqWeights(yData, weight);
/*  511 */     if (flag) {
/*  512 */       this.trueFreq = true;
/*  513 */       this.weightOpt = true;
/*  514 */       this.weightFlag = 1;
/*      */     }
/*      */     else {
/*  517 */       this.trueFreq = false;
/*  518 */       this.weightOpt = false;
/*  519 */       this.weightFlag = 0;
/*      */     }
/*  521 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected double[] checkForZeroWeights(double[] weight)
/*      */   {
/*  528 */     this.weightOpt = true;
/*  529 */     int nZeros = 0;
/*  530 */     int n = weight.length;
/*      */     
/*  532 */     for (int i = 0; i < n; i++) if (weight[i] <= 0.0D) nZeros++;
/*  533 */     double perCentZeros = 100.0D * nZeros / n;
/*  534 */     if (perCentZeros > 40.0D) {
/*  535 */       System.out.println(perCentZeros + "% of the weights are zero or less; all weights set to 1.0");
/*  536 */       for (int i = 0; i < n; i++) weight[i] = 1.0D;
/*  537 */       this.weightOpt = false;
/*      */ 
/*      */     }
/*  540 */     else if (perCentZeros > 0.0D) {
/*  541 */       for (int i = 0; i < n; i++) {
/*  542 */         if (weight[i] <= 0.0D) {
/*  543 */           if (i == 0) {
/*  544 */             int ii = 1;
/*  545 */             boolean test = true;
/*  546 */             while (test) {
/*  547 */               if (weight[ii] > 0.0D) {
/*  548 */                 double ww = weight[0];
/*  549 */                 weight[0] = weight[ii];
/*  550 */                 System.out.println("weight at point " + i + ", " + ww + ", replaced by " + weight[i]);
/*  551 */                 test = false;
/*      */               }
/*      */               else {
/*  554 */                 ii++;
/*      */               }
/*      */             }
/*      */           }
/*  558 */           if (i == n - 1) {
/*  559 */             int ii = n - 2;
/*  560 */             boolean test = true;
/*  561 */             while (test) {
/*  562 */               if (weight[ii] > 0.0D) {
/*  563 */                 double ww = weight[i];
/*  564 */                 weight[i] = weight[ii];
/*  565 */                 System.out.println("weight at point " + i + ", " + ww + ", replaced by " + weight[i]);
/*  566 */                 test = false;
/*      */               }
/*      */               else {
/*  569 */                 ii--;
/*      */               }
/*      */             }
/*      */           }
/*  573 */           if ((i > 0) && (i < n - 2)) {
/*  574 */             double lower = 0.0D;
/*  575 */             double upper = 0.0D;
/*  576 */             int ii = i - 1;
/*  577 */             boolean test = true;
/*  578 */             while (test) {
/*  579 */               if (weight[ii] > 0.0D) {
/*  580 */                 lower = weight[ii];
/*  581 */                 test = false;
/*      */               }
/*      */               else {
/*  584 */                 ii--;
/*  585 */                 if (ii == 0) test = false;
/*      */               }
/*      */             }
/*  588 */             ii = i + 1;
/*  589 */             test = true;
/*  590 */             while (test) {
/*  591 */               if (weight[ii] > 0.0D) {
/*  592 */                 upper = weight[ii];
/*  593 */                 test = false;
/*      */               }
/*      */               else {
/*  596 */                 ii++;
/*  597 */                 if (ii == n - 1) test = false;
/*      */               }
/*      */             }
/*  600 */             double ww = weight[i];
/*  601 */             if (lower == 0.0D) {
/*  602 */               weight[i] = upper;
/*      */ 
/*      */             }
/*  605 */             else if (upper == 0.0D) {
/*  606 */               weight[i] = lower;
/*      */             }
/*      */             else {
/*  609 */               weight[i] = ((lower + upper) / 2.0D);
/*      */             }
/*      */             
/*  612 */             System.out.println("weight at point " + i + ", " + ww + ", replaced by " + weight[i]);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  618 */     return weight;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterData(double[][] xData, double[] yData, double[] weight)
/*      */   {
/*  625 */     int n = weight.length;
/*  626 */     this.nData0 = yData.length;
/*  627 */     this.weightOpt = true;
/*  628 */     weight = checkForZeroWeights(weight);
/*  629 */     if (this.weightOpt) this.weightFlag = 1;
/*  630 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[][] xxData, double[][] yyData, double[][] wWeight)
/*      */   {
/*  635 */     this.multipleY = true;
/*  636 */     int nY1 = yyData.length;
/*  637 */     this.nYarrays = nY1;
/*  638 */     int nY2 = yyData[0].length;
/*  639 */     this.nData0 = nY2;
/*  640 */     int nX1 = xxData.length;
/*  641 */     int nX2 = xxData[0].length;
/*  642 */     double[] yData = new double[nY1 * nY2];
/*  643 */     double[] weight = new double[nY1 * nY2];
/*  644 */     double[][] xData = new double[nY1 * nY2][nX1];
/*  645 */     int ii = 0;
/*  646 */     for (int i = 0; i < nY1; i++) {
/*  647 */       int nY = yyData[i].length;
/*  648 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  649 */       int nX = xxData[i].length;
/*  650 */       if (nY != nX) throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
/*  651 */       for (int j = 0; j < nY2; j++) {
/*  652 */         yData[ii] = yyData[i][j];
/*  653 */         xData[ii][i] = xxData[i][j];
/*  654 */         weight[ii] = wWeight[i][j];
/*  655 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  659 */     weight = checkForZeroWeights(weight);
/*  660 */     if (this.weightOpt) this.weightFlag = 1;
/*  661 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[] xxData, double[] yData, double[] weight)
/*      */   {
/*  666 */     this.nData0 = yData.length;
/*  667 */     int n = xxData.length;
/*  668 */     int m = weight.length;
/*  669 */     double[][] xData = new double[1][n];
/*  670 */     for (int i = 0; i < n; i++) {
/*  671 */       xData[0][i] = xxData[i];
/*      */     }
/*      */     
/*  674 */     weight = checkForZeroWeights(weight);
/*  675 */     if (this.weightOpt) this.weightFlag = 1;
/*  676 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public void enterData(double[] xxData, double[][] yyData, double[][] wWeight)
/*      */   {
/*  682 */     this.multipleY = true;
/*  683 */     int nY1 = yyData.length;
/*  684 */     this.nYarrays = nY1;
/*  685 */     int nY2 = yyData[0].length;
/*  686 */     this.nData0 = nY2;
/*  687 */     double[] yData = new double[nY1 * nY2];
/*  688 */     double[] weight = new double[nY1 * nY2];
/*  689 */     int ii = 0;
/*  690 */     for (int i = 0; i < nY1; i++) {
/*  691 */       int nY = yyData[i].length;
/*  692 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  693 */       for (int j = 0; j < nY2; j++) {
/*  694 */         yData[ii] = yyData[i][j];
/*  695 */         weight[ii] = wWeight[i][j];
/*  696 */         ii++;
/*      */       }
/*      */     }
/*  699 */     int n = xxData.length;
/*  700 */     if (n != nY2) throw new IllegalArgumentException("x and y data lengths must be the same");
/*  701 */     double[][] xData = new double[1][nY1 * n];
/*  702 */     ii = 0;
/*  703 */     for (int j = 0; j < nY1; j++) {
/*  704 */       for (int i = 0; i < n; i++) {
/*  705 */         xData[0][ii] = xxData[i];
/*  706 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  710 */     weight = checkForZeroWeights(weight);
/*  711 */     if (this.weightOpt) this.weightFlag = 1;
/*  712 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[][] xData, double[] yData)
/*      */   {
/*  717 */     this.nData0 = yData.length;
/*  718 */     int n = yData.length;
/*  719 */     double[] weight = new double[n];
/*      */     
/*  721 */     this.weightOpt = false;
/*  722 */     for (int i = 0; i < n; i++) weight[i] = 1.0D;
/*  723 */     this.weightFlag = 0;
/*  724 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[][] xxData, double[][] yyData)
/*      */   {
/*  729 */     this.multipleY = true;
/*  730 */     int nY1 = yyData.length;
/*  731 */     this.nYarrays = nY1;
/*  732 */     int nY2 = yyData[0].length;
/*  733 */     this.nData0 = nY2;
/*  734 */     int nX1 = xxData.length;
/*  735 */     int nX2 = xxData[0].length;
/*  736 */     double[] yData = new double[nY1 * nY2];
/*  737 */     double[][] xData = new double[nY1 * nY2][nX1];
/*  738 */     int ii = 0;
/*  739 */     for (int i = 0; i < nY1; i++) {
/*  740 */       int nY = yyData[i].length;
/*  741 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  742 */       int nX = xxData[i].length;
/*  743 */       if (nY != nX) throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
/*  744 */       for (int j = 0; j < nY2; j++) {
/*  745 */         yData[ii] = yyData[i][j];
/*  746 */         xData[ii][i] = xxData[i][j];
/*  747 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  751 */     int n = yData.length;
/*  752 */     double[] weight = new double[n];
/*      */     
/*  754 */     this.weightOpt = false;
/*  755 */     for (int i = 0; i < n; i++) weight[i] = 1.0D;
/*  756 */     this.weightFlag = 0;
/*      */     
/*  758 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[] xxData, double[] yData)
/*      */   {
/*  763 */     this.nData0 = yData.length;
/*  764 */     int n = xxData.length;
/*  765 */     double[][] xData = new double[1][n];
/*  766 */     double[] weight = new double[n];
/*      */     
/*  768 */     for (int i = 0; i < n; i++) { xData[0][i] = xxData[i];
/*      */     }
/*  770 */     this.weightOpt = false;
/*  771 */     for (int i = 0; i < n; i++) weight[i] = 1.0D;
/*  772 */     this.weightFlag = 0;
/*      */     
/*  774 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   public void enterData(double[] xxData, double[][] yyData)
/*      */   {
/*  779 */     this.multipleY = true;
/*  780 */     int nY1 = yyData.length;
/*  781 */     this.nYarrays = nY1;
/*  782 */     int nY2 = yyData[0].length;
/*  783 */     this.nData0 = nY2;
/*  784 */     double[] yData = new double[nY1 * nY2];
/*  785 */     int ii = 0;
/*  786 */     for (int i = 0; i < nY1; i++) {
/*  787 */       int nY = yyData[i].length;
/*  788 */       if (nY != nY2) throw new IllegalArgumentException("multiple y arrays must be of the same length");
/*  789 */       for (int j = 0; j < nY2; j++) {
/*  790 */         yData[ii] = yyData[i][j];
/*  791 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  795 */     double[][] xData = new double[1][nY1 * nY2];
/*  796 */     double[] weight = new double[nY1 * nY2];
/*      */     
/*  798 */     ii = 0;
/*  799 */     int n = xxData.length;
/*  800 */     for (int j = 0; j < nY1; j++) {
/*  801 */       for (int i = 0; i < n; i++) {
/*  802 */         xData[0][ii] = xxData[i];
/*  803 */         weight[ii] = 1.0D;
/*  804 */         ii++;
/*      */       }
/*      */     }
/*  807 */     this.weightOpt = false;
/*  808 */     this.weightFlag = 0;
/*      */     
/*  810 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public void enterData(double[] xxData, double binWidth, double binZero)
/*      */   {
/*  816 */     double[][] data = histogramBins(xxData, binWidth, binZero);
/*  817 */     int n = data[0].length;
/*  818 */     this.nData0 = n;
/*  819 */     double[][] xData = new double[1][n];
/*  820 */     double[] yData = new double[n];
/*  821 */     double[] weight = new double[n];
/*  822 */     for (int i = 0; i < n; i++) {
/*  823 */       xData[0][i] = data[0][i];
/*  824 */       yData[i] = data[1][i];
/*      */     }
/*  826 */     boolean flag = setTrueFreqWeights(yData, weight);
/*  827 */     if (flag) {
/*  828 */       this.trueFreq = true;
/*  829 */       this.weightOpt = true;
/*  830 */       this.weightFlag = 1;
/*      */     }
/*      */     else {
/*  833 */       this.trueFreq = false;
/*  834 */       this.weightOpt = false;
/*  835 */       this.weightFlag = 0;
/*      */     }
/*  837 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */ 
/*      */   public void enterData(double[] xxData, double binWidth)
/*      */   {
/*  843 */     double[][] data = histogramBins(xxData, binWidth);
/*  844 */     int n = data[0].length;
/*  845 */     this.nData0 = n;
/*  846 */     double[][] xData = new double[1][n];
/*  847 */     double[] yData = new double[n];
/*  848 */     double[] weight = new double[n];
/*  849 */     for (int i = 0; i < n; i++) {
/*  850 */       xData[0][i] = data[0][i];
/*  851 */       yData[i] = data[1][i];
/*      */     }
/*  853 */     boolean flag = setTrueFreqWeights(yData, weight);
/*  854 */     if (flag) {
/*  855 */       this.trueFreq = true;
/*  856 */       this.weightOpt = true;
/*  857 */       this.weightFlag = 0;
/*      */     }
/*      */     else {
/*  860 */       this.trueFreq = false;
/*  861 */       this.weightOpt = false;
/*  862 */       this.weightFlag = 0;
/*      */     }
/*  864 */     setDefaultValues(xData, yData, weight);
/*      */   }
/*      */   
/*      */   protected static boolean setTrueFreqWeights(double[] yData, double[] weight)
/*      */   {
/*  869 */     int nData = yData.length;
/*  870 */     boolean flag = true;
/*  871 */     boolean unityWeight = false;
/*      */     
/*      */ 
/*  874 */     for (int ii = 0; ii < nData; ii++) {
/*  875 */       weight[ii] = Math.sqrt(Math.abs(yData[ii]));
/*      */     }
/*      */     
/*      */ 
/*  879 */     for (int ii = 0; ii < nData; ii++) {
/*  880 */       double last = 0.0D;
/*  881 */       double next = 0.0D;
/*  882 */       if (weight[ii] == 0.0D)
/*      */       {
/*  884 */         boolean testLast = true;
/*  885 */         int iLast = ii - 1;
/*  886 */         while (testLast) {
/*  887 */           if (iLast < 0) {
/*  888 */             testLast = false;
/*      */ 
/*      */           }
/*  891 */           else if (weight[iLast] == 0.0D) {
/*  892 */             iLast--;
/*      */           }
/*      */           else {
/*  895 */             last = weight[iLast];
/*  896 */             testLast = false;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  902 */         boolean testNext = true;
/*  903 */         int iNext = ii + 1;
/*  904 */         while (testNext) {
/*  905 */           if (iNext >= nData) {
/*  906 */             testNext = false;
/*      */ 
/*      */           }
/*  909 */           else if (weight[iNext] == 0.0D) {
/*  910 */             iNext++;
/*      */           }
/*      */           else {
/*  913 */             next = weight[iNext];
/*  914 */             testNext = false;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  920 */         weight[ii] = ((last + next) / 2.0D);
/*      */       }
/*      */     }
/*  923 */     return flag;
/*      */   }
/*      */   
/*      */   protected void setDefaultValues(double[][] xData, double[] yData, double[] weight)
/*      */   {
/*  928 */     this.nData = yData.length;
/*  929 */     this.nXarrays = xData.length;
/*  930 */     this.nTerms = this.nXarrays;
/*  931 */     this.yData = new double[this.nData];
/*  932 */     this.yCalc = new double[this.nData];
/*  933 */     this.weight = new double[this.nData];
/*  934 */     this.residual = new double[this.nData];
/*  935 */     this.residualW = new double[this.nData];
/*  936 */     this.xData = new double[this.nXarrays][this.nData];
/*  937 */     int n = weight.length;
/*  938 */     if (n != this.nData) throw new IllegalArgumentException("The weight and the y data lengths do not agree");
/*  939 */     for (int i = 0; i < this.nData; i++) {
/*  940 */       this.yData[i] = yData[i];
/*  941 */       this.weight[i] = weight[i];
/*      */     }
/*  943 */     for (int j = 0; j < this.nXarrays; j++) {
/*  944 */       n = xData[j].length;
/*  945 */       if (n != this.nData) throw new IllegalArgumentException("An x [" + j + "] length " + n + " and the y data length, " + this.nData + ", do not agree");
/*  946 */       for (int i = 0; i < this.nData; i++) {
/*  947 */         this.xData[j][i] = xData[j][i];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setDenominatorToN()
/*      */   {
/*  954 */     this.nFactorOption = true;
/*  955 */     Stat.setStaticDenominatorToN();
/*      */   }
/*      */   
/*      */   public void setDenominatorToNminusOne()
/*      */   {
/*  960 */     this.nFactorOption = false;
/*  961 */     Stat.setStaticDenominatorToNminusOne();
/*      */   }
/*      */   
/*      */ 
/*      */   public void supressPrint()
/*      */   {
/*  967 */     this.supressPrint = true;
/*      */   }
/*      */   
/*      */   public void supressYYplot()
/*      */   {
/*  972 */     this.supressYYplot = true;
/*      */   }
/*      */   
/*      */   public void supressErrorMessages()
/*      */   {
/*  977 */     this.supressErrorMessages = true;
/*      */   }
/*      */   
/*      */   public void ignoreDofFcheck()
/*      */   {
/*  982 */     this.ignoreDofFcheck = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setYscaleOption(boolean flag)
/*      */   {
/*  989 */     this.scaleFlag = flag;
/*  990 */     if (!flag) { this.yScaleFactor = 1.0D;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setYscale(boolean flag)
/*      */   {
/*  998 */     this.scaleFlag = flag;
/*  999 */     if (!flag) { this.yScaleFactor = 1.0D;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setYscaleFactor(double scale)
/*      */   {
/* 1006 */     this.scaleFlag = false;
/* 1007 */     this.yScaleFactor = scale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getYscaleOption()
/*      */   {
/* 1014 */     return this.scaleFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getYscale()
/*      */   {
/* 1022 */     return this.scaleFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTrueFreq(boolean trFr)
/*      */   {
/* 1029 */     boolean trFrOld = this.trueFreq;
/* 1030 */     this.trueFreq = trFr;
/* 1031 */     if (trFr) {
/* 1032 */       boolean flag = setTrueFreqWeights(this.yData, this.weight);
/* 1033 */       if (flag) {
/* 1034 */         this.trueFreq = true;
/* 1035 */         this.weightOpt = true;
/*      */       }
/*      */       else {
/* 1038 */         this.trueFreq = false;
/* 1039 */         this.weightOpt = false;
/*      */       }
/*      */       
/*      */     }
/* 1043 */     else if (trFrOld) {
/* 1044 */       for (int i = 0; i < this.weight.length; i++) {
/* 1045 */         this.weight[i] = 1.0D;
/*      */       }
/* 1047 */       this.weightOpt = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getTrueFreq()
/*      */   {
/* 1054 */     return this.trueFreq;
/*      */   }
/*      */   
/*      */   public void setXlegend(String legend)
/*      */   {
/* 1059 */     this.xLegend = legend;
/* 1060 */     this.legendCheck = true;
/*      */   }
/*      */   
/*      */   public void setYlegend(String legend)
/*      */   {
/* 1065 */     this.yLegend = legend;
/* 1066 */     this.legendCheck = true;
/*      */   }
/*      */   
/*      */   public void setTitle(String title)
/*      */   {
/* 1071 */     this.graphTitle = title;
/*      */   }
/*      */   
/*      */ 
/*      */   public void linear()
/*      */   {
/* 1077 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 1078 */     this.lastMethod = 0;
/* 1079 */     this.linNonLin = true;
/* 1080 */     this.nTerms = (this.nXarrays + 1);
/* 1081 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 1082 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 1083 */     double[][] aa = new double[this.nTerms][this.nData];
/*      */     
/* 1085 */     for (int j = 0; j < this.nData; j++) aa[0][j] = 1.0D;
/* 1086 */     for (int i = 1; i < this.nTerms; i++) {
/* 1087 */       for (int j = 0; j < this.nData; j++) {
/* 1088 */         aa[i][j] = this.xData[(i - 1)][j];
/*      */       }
/*      */     }
/* 1091 */     this.best = new double[this.nTerms];
/* 1092 */     this.bestSd = new double[this.nTerms];
/* 1093 */     this.tValues = new double[this.nTerms];
/* 1094 */     this.pValues = new double[this.nTerms];
/* 1095 */     generalLinear(aa);
/* 1096 */     if (!this.ignoreDofFcheck) { generalLinearStats(aa);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void linearPlot(String xLegend, String yLegend)
/*      */   {
/* 1104 */     this.xLegend = xLegend;
/* 1105 */     this.yLegend = yLegend;
/* 1106 */     this.legendCheck = true;
/* 1107 */     linear();
/* 1108 */     if (!this.supressPrint) print();
/* 1109 */     int flag = 0;
/* 1110 */     if (this.xData.length < 2) flag = plotXY();
/* 1111 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void linearPlot()
/*      */   {
/* 1119 */     linear();
/* 1120 */     if (!this.supressPrint) print();
/* 1121 */     int flag = 0;
/* 1122 */     if (this.xData.length < 2) flag = plotXY();
/* 1123 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void polynomial(int deg)
/*      */   {
/* 1129 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 1130 */     if (this.nXarrays > 1) throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
/* 1131 */     if (deg < 1) throw new IllegalArgumentException("Polynomial degree must be greater than zero");
/* 1132 */     this.lastMethod = 1;
/* 1133 */     this.linNonLin = true;
/* 1134 */     this.nTerms = (deg + 1);
/* 1135 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 1136 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 1137 */     double[][] aa = new double[this.nTerms][this.nData];
/*      */     
/* 1139 */     for (int j = 0; j < this.nData; j++) aa[0][j] = 1.0D;
/* 1140 */     for (int j = 0; j < this.nData; j++) { aa[1][j] = this.xData[0][j];
/*      */     }
/* 1142 */     for (int i = 2; i < this.nTerms; i++) {
/* 1143 */       for (int j = 0; j < this.nData; j++) {
/* 1144 */         aa[i][j] = Math.pow(this.xData[0][j], i);
/*      */       }
/*      */     }
/* 1147 */     this.best = new double[this.nTerms];
/* 1148 */     this.bestSd = new double[this.nTerms];
/* 1149 */     this.tValues = new double[this.nTerms];
/* 1150 */     this.pValues = new double[this.nTerms];
/* 1151 */     generalLinear(aa);
/* 1152 */     if (!this.ignoreDofFcheck) { generalLinearStats(aa);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void polynomialPlot(int n, String xLegend, String yLegend)
/*      */   {
/* 1160 */     this.xLegend = xLegend;
/* 1161 */     this.yLegend = yLegend;
/* 1162 */     this.legendCheck = true;
/* 1163 */     polynomial(n);
/* 1164 */     if (!this.supressPrint) print();
/* 1165 */     int flag = plotXY();
/* 1166 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void polynomialPlot(int n)
/*      */   {
/* 1173 */     polynomial(n);
/* 1174 */     if (!this.supressPrint) print();
/* 1175 */     int flag = plotXY();
/* 1176 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void linearGeneral()
/*      */   {
/* 1182 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 1183 */     this.lastMethod = 2;
/*      */     
/* 1185 */     this.linNonLin = true;
/* 1186 */     this.nTerms = this.nXarrays;
/* 1187 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 1188 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 1189 */     this.best = new double[this.nTerms];
/* 1190 */     this.bestSd = new double[this.nTerms];
/* 1191 */     this.tValues = new double[this.nTerms];
/* 1192 */     this.pValues = new double[this.nTerms];
/* 1193 */     generalLinear(this.xData);
/* 1194 */     if (!this.ignoreDofFcheck) { generalLinearStats(this.xData);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void linearGeneralPlot(String xLegend, String yLegend)
/*      */   {
/* 1201 */     this.xLegend = xLegend;
/* 1202 */     this.yLegend = yLegend;
/* 1203 */     this.legendCheck = true;
/* 1204 */     linearGeneral();
/* 1205 */     if (!this.supressPrint) print();
/* 1206 */     if (!this.supressYYplot) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void linearGeneralPlot()
/*      */   {
/* 1213 */     linearGeneral();
/* 1214 */     if (!this.supressPrint) print();
/* 1215 */     if (!this.supressYYplot) plotYY();
/*      */   }
/*      */   
/*      */   protected void generalLinear(double[][] xd)
/*      */   {
/* 1220 */     if ((this.nData <= this.nTerms) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Number of unknown parameters is greater than or equal to the number of data points");
/* 1221 */     double sde = 0.0D;double sum = 0.0D;double yCalctemp = 0.0D;
/* 1222 */     double[][] a = new double[this.nTerms][this.nTerms];
/* 1223 */     double[][] h = new double[this.nTerms][this.nTerms];
/* 1224 */     double[] b = new double[this.nTerms];
/* 1225 */     double[] coeff = new double[this.nTerms];
/*      */     
/*      */ 
/* 1228 */     if (this.ignoreDofFcheck) {
/* 1229 */       this.bestSd = new double[this.nTerms];
/* 1230 */       this.pseudoSd = new double[this.nTerms];
/* 1231 */       this.tValues = new double[this.nTerms];
/* 1232 */       this.pValues = new double[this.nTerms];
/*      */       
/* 1234 */       this.covar = new double[this.nTerms][this.nTerms];
/* 1235 */       this.corrCoeff = new double[this.nTerms][this.nTerms];
/* 1236 */       for (int i = 0; i < this.nTerms; i++) {
/* 1237 */         this.bestSd[i] = NaN.0D;
/* 1238 */         this.pseudoSd[i] = NaN.0D;
/* 1239 */         for (int j = 0; j < this.nTerms; j++) {
/* 1240 */           this.covar[i][j] = NaN.0D;
/* 1241 */           this.corrCoeff[i][j] = NaN.0D;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1246 */     for (int i = 0; i < this.nTerms; i++) {
/* 1247 */       sum = 0.0D;
/* 1248 */       for (int j = 0; j < this.nData; j++) {
/* 1249 */         sum += this.yData[j] * xd[i][j] / Fmath.square(this.weight[j]);
/*      */       }
/* 1251 */       b[i] = sum;
/*      */     }
/* 1253 */     for (int i = 0; i < this.nTerms; i++) {
/* 1254 */       for (int j = 0; j < this.nTerms; j++) {
/* 1255 */         sum = 0.0D;
/* 1256 */         for (int k = 0; k < this.nData; k++) {
/* 1257 */           sum += xd[i][k] * xd[j][k] / Fmath.square(this.weight[k]);
/*      */         }
/* 1259 */         a[j][i] = sum;
/*      */       }
/*      */     }
/* 1262 */     Matrix aa = new Matrix(a);
/* 1263 */     if (this.supressErrorMessages) aa.supressErrorMessage();
/* 1264 */     coeff = aa.solveLinearSet(b);
/*      */     
/* 1266 */     for (int i = 0; i < this.nTerms; i++) {
/* 1267 */       this.best[i] = coeff[i];
/*      */     }
/*      */   }
/*      */   
/*      */   protected void generalLinearStats(double[][] xd)
/*      */   {
/* 1273 */     double sde = 0.0D;double sum = 0.0D;double yCalctemp = 0.0D;
/* 1274 */     double[][] a = new double[this.nTerms][this.nTerms];
/* 1275 */     double[][] h = new double[this.nTerms][this.nTerms];
/* 1276 */     double[][] stat = new double[this.nTerms][this.nTerms];
/* 1277 */     double[][] cov = new double[this.nTerms][this.nTerms];
/* 1278 */     this.covar = new double[this.nTerms][this.nTerms];
/* 1279 */     this.corrCoeff = new double[this.nTerms][this.nTerms];
/* 1280 */     double[] coeffSd = new double[this.nTerms];
/* 1281 */     double[] coeff = new double[this.nTerms];
/*      */     
/* 1283 */     for (int i = 0; i < this.nTerms; i++) {
/* 1284 */       coeff[i] = this.best[i];
/*      */     }
/*      */     
/* 1287 */     if (this.weightOpt) this.chiSquare = 0.0D;
/* 1288 */     this.sumOfSquares = 0.0D;
/* 1289 */     for (int i = 0; i < this.nData; i++) {
/* 1290 */       yCalctemp = 0.0D;
/* 1291 */       for (int j = 0; j < this.nTerms; j++) {
/* 1292 */         yCalctemp += coeff[j] * xd[j][i];
/*      */       }
/* 1294 */       this.yCalc[i] = yCalctemp;
/* 1295 */       yCalctemp -= this.yData[i];
/* 1296 */       this.residual[i] = yCalctemp;
/* 1297 */       this.residualW[i] = (yCalctemp / this.weight[i]);
/* 1298 */       if (this.weightOpt) this.chiSquare += Fmath.square(yCalctemp / this.weight[i]);
/* 1299 */       this.sumOfSquares += Fmath.square(yCalctemp);
/*      */     }
/* 1301 */     if ((this.weightOpt) || (this.trueFreq)) this.reducedChiSquare = (this.chiSquare / this.degreesOfFreedom);
/* 1302 */     double varY = this.sumOfSquares / this.degreesOfFreedom;
/* 1303 */     double sdY = Math.sqrt(varY);
/*      */     
/* 1305 */     if (this.sumOfSquares == 0.0D) {
/* 1306 */       for (int i = 0; i < this.nTerms; i++) {
/* 1307 */         coeffSd[i] = 0.0D;
/* 1308 */         for (int j = 0; j < this.nTerms; j++) {
/* 1309 */           this.covar[i][j] = 0.0D;
/* 1310 */           if (i == j) {
/* 1311 */             this.corrCoeff[i][j] = 1.0D;
/*      */           }
/*      */           else {
/* 1314 */             this.corrCoeff[i][j] = 0.0D;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 1320 */       for (int i = 0; i < this.nTerms; i++) {
/* 1321 */         for (int j = 0; j < this.nTerms; j++) {
/* 1322 */           sum = 0.0D;
/* 1323 */           for (int k = 0; k < this.nData; k++) {
/* 1324 */             if (this.weightOpt) {
/* 1325 */               sde = this.weight[k];
/*      */             }
/*      */             else {
/* 1328 */               sde = sdY;
/*      */             }
/* 1330 */             sum += xd[i][k] * xd[j][k] / Fmath.square(sde);
/*      */           }
/* 1332 */           h[j][i] = sum;
/*      */         }
/*      */       }
/* 1335 */       Matrix hh = new Matrix(h);
/* 1336 */       if (this.supressErrorMessages) hh.supressErrorMessage();
/* 1337 */       hh = hh.inverse();
/* 1338 */       stat = hh.getArrayCopy();
/* 1339 */       for (int j = 0; j < this.nTerms; j++) {
/* 1340 */         coeffSd[j] = Math.sqrt(stat[j][j]);
/*      */       }
/*      */       
/* 1343 */       for (int i = 0; i < this.nTerms; i++) {
/* 1344 */         for (int j = 0; j < this.nTerms; j++) {
/* 1345 */           this.covar[i][j] = stat[i][j];
/*      */         }
/*      */       }
/*      */       
/* 1349 */       for (int i = 0; i < this.nTerms; i++) {
/* 1350 */         for (int j = 0; j < this.nTerms; j++) {
/* 1351 */           if (i == j) {
/* 1352 */             this.corrCoeff[i][j] = 1.0D;
/*      */           }
/*      */           else {
/* 1355 */             this.corrCoeff[i][j] = (this.covar[i][j] / (coeffSd[i] * coeffSd[j]));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1361 */     for (int i = 0; i < this.nTerms; i++) {
/* 1362 */       this.bestSd[i] = coeffSd[i];
/* 1363 */       this.tValues[i] = (this.best[i] / this.bestSd[i]);
/* 1364 */       double atv = Math.abs(this.tValues[i]);
/* 1365 */       this.pValues[i] = (1.0D - Stat.studentTcdf(-atv, atv, this.degreesOfFreedom));
/*      */     }
/*      */     
/* 1368 */     if ((this.nXarrays == 1) && (this.nYarrays == 1)) {
/* 1369 */       this.sampleR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
/* 1370 */       this.sampleR2 = (this.sampleR * this.sampleR);
/* 1371 */       this.adjustedR = this.sampleR;
/* 1372 */       this.adjustedR2 = this.sampleR2;
/*      */     }
/*      */     else {
/* 1375 */       multCorrelCoeff(this.yData, this.yCalc, this.weight);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void nelderMead(Object regFun, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/* 1382 */     boolean testContract = false;
/* 1383 */     int np = start.length;
/* 1384 */     if (this.maxConstraintIndex >= np) throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + np + ")");
/* 1385 */     this.nlrStatus = true;
/* 1386 */     this.nTerms = np;
/* 1387 */     int nnp = np + 1;
/* 1388 */     this.lastSSnoConstraint = 0.0D;
/*      */     
/* 1390 */     if (this.scaleOpt < 2) this.scale = new double[np];
/* 1391 */     if ((this.scaleOpt == 2) && (this.scale.length != start.length)) throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
/* 1392 */     if (step.length != start.length) { throw new IllegalArgumentException("step array length " + step.length + " and initial estimate array length " + start.length + " are of different");
/*      */     }
/*      */     
/* 1395 */     for (int i = 0; i < np; i++) { if (step[i] == 0.0D) { throw new IllegalArgumentException("step " + i + " size is zero");
/*      */       }
/*      */     }
/* 1398 */     if (this.ignoreDofFcheck) {
/* 1399 */       this.bestSd = new double[this.nTerms];
/* 1400 */       this.pseudoSd = new double[this.nTerms];
/* 1401 */       this.tValues = new double[this.nTerms];
/* 1402 */       this.pValues = new double[this.nTerms];
/*      */       
/* 1404 */       this.covar = new double[this.nTerms][this.nTerms];
/* 1405 */       this.corrCoeff = new double[this.nTerms][this.nTerms];
/* 1406 */       for (int i = 0; i < this.nTerms; i++) {
/* 1407 */         this.bestSd[i] = NaN.0D;
/* 1408 */         this.pseudoSd[i] = NaN.0D;
/* 1409 */         for (int j = 0; j < this.nTerms; j++) {
/* 1410 */           this.covar[i][j] = NaN.0D;
/* 1411 */           this.corrCoeff[i][j] = NaN.0D;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1417 */     this.startH = new double[np];
/* 1418 */     this.step = new double[np];
/* 1419 */     double[] pmin = new double[np];
/* 1420 */     this.best = new double[np];
/* 1421 */     this.bestSd = new double[np];
/* 1422 */     this.tValues = new double[np];
/* 1423 */     this.pValues = new double[np];
/*      */     
/* 1425 */     double[][] pp = new double[nnp][nnp];
/* 1426 */     double[] yy = new double[nnp];
/* 1427 */     double[] pbar = new double[nnp];
/* 1428 */     double[] pstar = new double[nnp];
/* 1429 */     double[] p2star = new double[nnp];
/*      */     
/*      */ 
/* 1432 */     double yabsmean = 0.0D;
/* 1433 */     for (int i = 0; i < this.nData; i++) yabsmean += Math.abs(this.yData[i]);
/* 1434 */     yabsmean /= this.nData;
/*      */     
/*      */ 
/* 1437 */     if (this.penalty) {
/* 1438 */       Integer itemp = (Integer)this.penalties.get(1);
/* 1439 */       this.nConstraints = itemp.intValue();
/* 1440 */       this.penaltyParam = new int[this.nConstraints];
/* 1441 */       this.penaltyCheck = new int[this.nConstraints];
/* 1442 */       this.constraints = new double[this.nConstraints];
/* 1443 */       Double dtemp = null;
/* 1444 */       int j = 2;
/* 1445 */       for (int i = 0; i < this.nConstraints; i++) {
/* 1446 */         itemp = (Integer)this.penalties.get(j);
/* 1447 */         this.penaltyParam[i] = itemp.intValue();
/* 1448 */         j++;
/* 1449 */         itemp = (Integer)this.penalties.get(j);
/* 1450 */         this.penaltyCheck[i] = itemp.intValue();
/* 1451 */         j++;
/* 1452 */         dtemp = (Double)this.penalties.get(j);
/* 1453 */         this.constraints[i] = dtemp.doubleValue();
/* 1454 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1459 */     if (this.sumPenalty) {
/* 1460 */       Integer itemp = (Integer)this.sumPenalties.get(1);
/* 1461 */       this.nSumConstraints = itemp.intValue();
/* 1462 */       this.sumPenaltyParam = new int[this.nSumConstraints][];
/* 1463 */       this.sumPlusOrMinus = new double[this.nSumConstraints][];
/* 1464 */       this.sumPenaltyCheck = new int[this.nSumConstraints];
/* 1465 */       this.sumPenaltyNumber = new int[this.nSumConstraints];
/* 1466 */       this.sumConstraints = new double[this.nSumConstraints];
/* 1467 */       int[] itempArray = null;
/* 1468 */       double[] dtempArray = null;
/* 1469 */       Double dtemp = null;
/* 1470 */       int j = 2;
/* 1471 */       for (int i = 0; i < this.nSumConstraints; i++) {
/* 1472 */         itemp = (Integer)this.sumPenalties.get(j);
/* 1473 */         this.sumPenaltyNumber[i] = itemp.intValue();
/* 1474 */         j++;
/* 1475 */         itempArray = (int[])this.sumPenalties.get(j);
/* 1476 */         this.sumPenaltyParam[i] = itempArray;
/* 1477 */         j++;
/* 1478 */         dtempArray = (double[])this.sumPenalties.get(j);
/* 1479 */         this.sumPlusOrMinus[i] = dtempArray;
/* 1480 */         j++;
/* 1481 */         itemp = (Integer)this.sumPenalties.get(j);
/* 1482 */         this.sumPenaltyCheck[i] = itemp.intValue();
/* 1483 */         j++;
/* 1484 */         dtemp = (Double)this.sumPenalties.get(j);
/* 1485 */         this.sumConstraints[i] = dtemp.doubleValue();
/* 1486 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1491 */     for (int i = 0; i < np; i++) { this.startH[i] = start[i];
/*      */     }
/*      */     
/* 1494 */     if (this.scaleOpt > 0) {
/* 1495 */       boolean testzero = false;
/* 1496 */       for (int i = 0; i < np; i++) if (start[i] == 0.0D) testzero = true;
/* 1497 */       if (testzero) {
/* 1498 */         System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
/* 1499 */         System.out.println("Regression performed without scaling");
/* 1500 */         this.scaleOpt = 0;
/*      */       }
/*      */     }
/* 1503 */     switch (this.scaleOpt) {
/* 1504 */     case 0:  for (int i = 0; i < np; i++) this.scale[i] = 1.0D;
/* 1505 */       break;
/* 1506 */     case 1:  for (int i = 0; i < np; i++) {
/* 1507 */         this.scale[i] = (1.0D / start[i]);
/* 1508 */         step[i] /= start[i];
/* 1509 */         start[i] = 1.0D;
/*      */       }
/* 1511 */       break;
/* 1512 */     case 2:  for (int i = 0; i < np; i++) {
/* 1513 */         step[i] *= this.scale[i];
/* 1514 */         start[i] *= this.scale[i];
/*      */       }
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1520 */     this.fTol = fTol;
/* 1521 */     this.nMax = nMax;
/* 1522 */     this.nIter = 0;
/* 1523 */     for (int i = 0; i < np; i++) {
/* 1524 */       this.step[i] = step[i];
/* 1525 */       this.scale[i] = this.scale[i];
/*      */     }
/*      */     
/*      */ 
/* 1529 */     double sho = 0.0D;
/* 1530 */     for (int i = 0; i < np; i++) {
/* 1531 */       sho = start[i];
/* 1532 */       pstar[i] = sho;
/* 1533 */       p2star[i] = sho;
/* 1534 */       pmin[i] = sho;
/*      */     }
/*      */     
/* 1537 */     int jcount = this.konvge;
/*      */     
/* 1539 */     for (int i = 0; i < np; i++) {
/* 1540 */       pp[i][(nnp - 1)] = start[i];
/*      */     }
/* 1542 */     yy[(nnp - 1)] = sumSquares(regFun, start);
/* 1543 */     for (int j = 0; j < np; j++) {
/* 1544 */       start[j] += step[j];
/*      */       
/* 1546 */       for (int i = 0; i < np; i++) pp[i][j] = start[i];
/* 1547 */       yy[j] = sumSquares(regFun, start);
/* 1548 */       start[j] -= step[j];
/*      */     }
/*      */     
/*      */ 
/* 1552 */     double ynewlo = 0.0D;
/* 1553 */     double ystar = 0.0D;
/* 1554 */     double y2star = 0.0D;
/* 1555 */     double ylo = 0.0D;
/*      */     
/*      */ 
/* 1558 */     double curMin = 0.0D;double sumnm = 0.0D;double summnm = 0.0D;double zn = 0.0D;
/* 1559 */     int ilo = 0;
/* 1560 */     int ihi = 0;
/* 1561 */     int ln = 0;
/* 1562 */     boolean test = true;
/*      */     
/* 1564 */     while (test)
/*      */     {
/* 1566 */       ylo = yy[0];
/* 1567 */       ynewlo = ylo;
/* 1568 */       ilo = 0;
/* 1569 */       ihi = 0;
/* 1570 */       for (int i = 1; i < nnp; i++) {
/* 1571 */         if (yy[i] < ylo) {
/* 1572 */           ylo = yy[i];
/* 1573 */           ilo = i;
/*      */         }
/* 1575 */         if (yy[i] > ynewlo) {
/* 1576 */           ynewlo = yy[i];
/* 1577 */           ihi = i;
/*      */         }
/*      */       }
/*      */       
/* 1581 */       for (int i = 0; i < np; i++) {
/* 1582 */         zn = 0.0D;
/* 1583 */         for (int j = 0; j < nnp; j++) {
/* 1584 */           zn += pp[i][j];
/*      */         }
/* 1586 */         zn -= pp[i][ihi];
/* 1587 */         pbar[i] = (zn / np);
/*      */       }
/*      */       
/*      */ 
/* 1591 */       for (int i = 0; i < np; i++) { pstar[i] = ((1.0D + this.rCoeff) * pbar[i] - this.rCoeff * pp[i][ihi]);
/*      */       }
/*      */       
/* 1594 */       ystar = sumSquares(regFun, pstar);
/*      */       
/* 1596 */       this.nIter += 1;
/*      */       
/*      */ 
/* 1599 */       if (ystar < ylo)
/*      */       {
/* 1601 */         for (int i = 0; i < np; i++) { p2star[i] = (pstar[i] * (1.0D + this.eCoeff) - this.eCoeff * pbar[i]);
/*      */         }
/* 1603 */         y2star = sumSquares(regFun, p2star);
/* 1604 */         this.nIter += 1;
/* 1605 */         if (y2star < ylo)
/*      */         {
/* 1607 */           for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/* 1608 */           yy[ihi] = y2star;
/*      */         }
/*      */         else
/*      */         {
/* 1612 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/* 1613 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1618 */         ln = 0;
/* 1619 */         for (int i = 0; i < nnp; i++) if ((i != ihi) && (ystar > yy[i])) ln++;
/* 1620 */         if (ln == np)
/*      */         {
/* 1622 */           if (ystar <= yy[ihi])
/*      */           {
/* 1624 */             for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/* 1625 */             yy[ihi] = ystar;
/*      */           }
/*      */           
/* 1628 */           for (int i = 0; i < np; i++) { p2star[i] = (this.cCoeff * pp[i][ihi] + (1.0D - this.cCoeff) * pbar[i]);
/*      */           }
/* 1630 */           y2star = sumSquares(regFun, p2star);
/* 1631 */           this.nIter += 1;
/*      */           
/* 1633 */           if (y2star > yy[ihi])
/*      */           {
/*      */ 
/* 1636 */             for (int j = 0; j < nnp; j++) {
/* 1637 */               for (int i = 0; i < np; i++) {
/* 1638 */                 pp[i][j] = (0.5D * (pp[i][j] + pp[i][ilo]));
/* 1639 */                 pmin[i] = pp[i][j];
/*      */               }
/* 1641 */               yy[j] = sumSquares(regFun, pmin);
/*      */             }
/* 1643 */             this.nIter += nnp;
/*      */           }
/*      */           else
/*      */           {
/* 1647 */             for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/* 1648 */             yy[ihi] = y2star;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 1653 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/* 1654 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1660 */       sumnm = 0.0D;
/* 1661 */       ynewlo = yy[0];
/* 1662 */       ilo = 0;
/* 1663 */       for (int i = 0; i < nnp; i++) {
/* 1664 */         sumnm += yy[i];
/* 1665 */         if (ynewlo > yy[i]) {
/* 1666 */           ynewlo = yy[i];
/* 1667 */           ilo = i;
/*      */         }
/*      */       }
/* 1670 */       sumnm /= nnp;
/* 1671 */       summnm = 0.0D;
/* 1672 */       for (int i = 0; i < nnp; i++) {
/* 1673 */         zn = yy[i] - sumnm;
/* 1674 */         summnm += zn * zn;
/*      */       }
/* 1676 */       curMin = Math.sqrt(summnm / np);
/*      */       
/*      */ 
/* 1679 */       switch (this.minTest) {
/*      */       case 0: 
/* 1681 */         if (curMin < fTol) test = false;
/*      */         break;
/*      */       case 1: 
/* 1684 */         if (Math.sqrt(ynewlo / this.degreesOfFreedom) < yabsmean * fTol) test = false;
/*      */         break;
/*      */       }
/* 1687 */       this.sumOfSquares = ynewlo;
/* 1688 */       if (!test)
/*      */       {
/* 1690 */         for (int i = 0; i < np; i++) pmin[i] = pp[i][ilo];
/* 1691 */         yy[(nnp - 1)] = ynewlo;
/*      */         
/* 1693 */         this.simplexSd = curMin;
/*      */         
/* 1695 */         jcount--;
/* 1696 */         if (jcount > 0) {
/* 1697 */           test = true;
/* 1698 */           for (int j = 0; j < np; j++) {
/* 1699 */             pmin[j] += step[j];
/* 1700 */             for (int i = 0; i < np; i++) pp[i][j] = pmin[i];
/* 1701 */             yy[j] = sumSquares(regFun, pmin);
/* 1702 */             pmin[j] -= step[j];
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1707 */       if ((test) && (this.nIter > this.nMax)) {
/* 1708 */         if (!this.supressErrorMessages) {
/* 1709 */           System.out.println("Maximum iteration number reached, in Regression.simplex(...)");
/* 1710 */           System.out.println("without the convergence criterion being satisfied");
/* 1711 */           System.out.println("Current parameter estimates and sum of squares values returned");
/*      */         }
/* 1713 */         this.nlrStatus = false;
/*      */         
/* 1715 */         for (int i = 0; i < np; i++) pmin[i] = pp[i][ilo];
/* 1716 */         yy[(nnp - 1)] = ynewlo;
/* 1717 */         test = false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1722 */     for (int i = 0; i < np; i++) {
/* 1723 */       pmin[i] = pp[i][ilo];
/* 1724 */       this.best[i] = (pmin[i] / this.scale[i]);
/* 1725 */       this.scale[i] = 1.0D;
/*      */     }
/* 1727 */     this.fMin = ynewlo;
/* 1728 */     this.kRestart = (this.konvge - jcount);
/*      */     
/* 1730 */     if (this.statFlag) {
/* 1731 */       if (!this.ignoreDofFcheck) pseudoLinearStats(regFun);
/*      */     }
/*      */     else {
/* 1734 */       for (int i = 0; i < np; i++) {
/* 1735 */         this.bestSd[i] = NaN.0D;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex(RegressionFunction g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/* 1742 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1743 */     Object regFun = g;
/* 1744 */     this.lastMethod = 3;
/* 1745 */     this.linNonLin = false;
/* 1746 */     this.zeroCheck = false;
/* 1747 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1748 */     nelderMead(regFun, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/* 1755 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1756 */     Object regFun = g;
/* 1757 */     this.lastMethod = 3;
/* 1758 */     this.linNonLin = false;
/* 1759 */     this.zeroCheck = false;
/* 1760 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1761 */     nelderMead(regFun, start, step, fTol, nMax);
/* 1762 */     if (!this.supressPrint) print();
/* 1763 */     int flag = 0;
/* 1764 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1765 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex(RegressionFunction g, double[] start, double[] step, double fTol)
/*      */   {
/* 1771 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1772 */     Object regFun = g;
/* 1773 */     int nMaxx = this.nMax;
/* 1774 */     this.lastMethod = 3;
/* 1775 */     this.linNonLin = false;
/* 1776 */     this.zeroCheck = false;
/* 1777 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1778 */     nelderMead(regFun, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double[] step, double fTol)
/*      */   {
/* 1785 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1786 */     this.lastMethod = 3;
/* 1787 */     this.linNonLin = false;
/* 1788 */     this.zeroCheck = false;
/* 1789 */     simplex(g, start, step, fTol);
/* 1790 */     if (!this.supressPrint) print();
/* 1791 */     int flag = 0;
/* 1792 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1793 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex(RegressionFunction g, double[] start, double[] step, int nMax)
/*      */   {
/* 1799 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1800 */     Object regFun = g;
/* 1801 */     double fToll = this.fTol;
/* 1802 */     this.lastMethod = 3;
/* 1803 */     this.linNonLin = false;
/* 1804 */     this.zeroCheck = false;
/* 1805 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1806 */     nelderMead(regFun, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double[] step, int nMax)
/*      */   {
/* 1813 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1814 */     this.lastMethod = 3;
/* 1815 */     this.linNonLin = false;
/* 1816 */     this.zeroCheck = false;
/* 1817 */     simplex(g, start, step, nMax);
/* 1818 */     if (!this.supressPrint) print();
/* 1819 */     int flag = 0;
/* 1820 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1821 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex(RegressionFunction g, double[] start, double[] step)
/*      */   {
/* 1828 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1829 */     Object regFun = g;
/* 1830 */     double fToll = this.fTol;
/* 1831 */     int nMaxx = this.nMax;
/* 1832 */     this.lastMethod = 3;
/* 1833 */     this.linNonLin = false;
/* 1834 */     this.zeroCheck = false;
/* 1835 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1836 */     nelderMead(regFun, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double[] step)
/*      */   {
/* 1844 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1845 */     this.lastMethod = 3;
/* 1846 */     this.linNonLin = false;
/* 1847 */     this.zeroCheck = false;
/* 1848 */     simplex(g, start, step);
/* 1849 */     if (!this.supressPrint) print();
/* 1850 */     int flag = 0;
/* 1851 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1852 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex(RegressionFunction g, double[] start, double fTol, int nMax)
/*      */   {
/* 1858 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1859 */     Object regFun = g;
/* 1860 */     int n = start.length;
/* 1861 */     double[] stepp = new double[n];
/* 1862 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 1863 */     this.lastMethod = 3;
/* 1864 */     this.linNonLin = false;
/* 1865 */     this.zeroCheck = false;
/* 1866 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1867 */     nelderMead(regFun, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double fTol, int nMax)
/*      */   {
/* 1874 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1875 */     this.lastMethod = 3;
/* 1876 */     this.linNonLin = false;
/* 1877 */     this.zeroCheck = false;
/* 1878 */     simplex(g, start, fTol, nMax);
/* 1879 */     if (!this.supressPrint) print();
/* 1880 */     int flag = 0;
/* 1881 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1882 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex(RegressionFunction g, double[] start, double fTol)
/*      */   {
/* 1889 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1890 */     Object regFun = g;
/* 1891 */     int n = start.length;
/* 1892 */     int nMaxx = this.nMax;
/* 1893 */     double[] stepp = new double[n];
/* 1894 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 1895 */     this.lastMethod = 3;
/* 1896 */     this.linNonLin = false;
/* 1897 */     this.zeroCheck = false;
/* 1898 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1899 */     nelderMead(regFun, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, double fTol)
/*      */   {
/* 1907 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1908 */     this.lastMethod = 3;
/* 1909 */     this.linNonLin = false;
/* 1910 */     this.zeroCheck = false;
/* 1911 */     simplex(g, start, fTol);
/* 1912 */     if (!this.supressPrint) print();
/* 1913 */     int flag = 0;
/* 1914 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1915 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex(RegressionFunction g, double[] start, int nMax)
/*      */   {
/* 1922 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1923 */     Object regFun = g;
/* 1924 */     int n = start.length;
/* 1925 */     double fToll = this.fTol;
/* 1926 */     double[] stepp = new double[n];
/* 1927 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 1928 */     this.lastMethod = 3;
/* 1929 */     this.zeroCheck = false;
/* 1930 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1931 */     nelderMead(regFun, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start, int nMax)
/*      */   {
/* 1939 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1940 */     this.lastMethod = 3;
/* 1941 */     this.linNonLin = false;
/* 1942 */     this.zeroCheck = false;
/* 1943 */     simplex(g, start, nMax);
/* 1944 */     if (!this.supressPrint) print();
/* 1945 */     int flag = 0;
/* 1946 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1947 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplex(RegressionFunction g, double[] start)
/*      */   {
/* 1955 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplex2 should have been called");
/* 1956 */     Object regFun = g;
/* 1957 */     int n = start.length;
/* 1958 */     int nMaxx = this.nMax;
/* 1959 */     double fToll = this.fTol;
/* 1960 */     double[] stepp = new double[n];
/* 1961 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 1962 */     this.lastMethod = 3;
/* 1963 */     this.linNonLin = false;
/* 1964 */     this.zeroCheck = false;
/* 1965 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1966 */     nelderMead(regFun, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot(RegressionFunction g, double[] start)
/*      */   {
/* 1975 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays\nsimplexPlot2 should have been called");
/* 1976 */     this.lastMethod = 3;
/* 1977 */     this.linNonLin = false;
/* 1978 */     this.zeroCheck = false;
/* 1979 */     simplex(g, start);
/* 1980 */     if (!this.supressPrint) print();
/* 1981 */     int flag = 0;
/* 1982 */     if (this.xData.length < 2) flag = plotXY(g);
/* 1983 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/* 1990 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 1991 */     Object regFun = g;
/* 1992 */     this.lastMethod = 3;
/* 1993 */     this.linNonLin = false;
/* 1994 */     this.zeroCheck = false;
/* 1995 */     this.degreesOfFreedom = (this.nData - start.length);
/* 1996 */     nelderMead(regFun, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/* 2003 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2004 */     Object regFun = g;
/* 2005 */     this.lastMethod = 3;
/* 2006 */     this.linNonLin = false;
/* 2007 */     this.zeroCheck = false;
/* 2008 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2009 */     nelderMead(regFun, start, step, fTol, nMax);
/* 2010 */     if (!this.supressPrint) print();
/* 2011 */     int flag = 0;
/* 2012 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2013 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double[] step, double fTol)
/*      */   {
/* 2019 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2020 */     Object regFun = g;
/* 2021 */     int nMaxx = this.nMax;
/* 2022 */     this.lastMethod = 3;
/* 2023 */     this.linNonLin = false;
/* 2024 */     this.zeroCheck = false;
/* 2025 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2026 */     nelderMead(regFun, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double[] step, double fTol)
/*      */   {
/* 2033 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2034 */     this.lastMethod = 3;
/* 2035 */     this.linNonLin = false;
/* 2036 */     this.zeroCheck = false;
/* 2037 */     simplex2(g, start, step, fTol);
/* 2038 */     if (!this.supressPrint) print();
/* 2039 */     int flag = 0;
/* 2040 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2041 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double[] step, int nMax)
/*      */   {
/* 2047 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2048 */     Object regFun = g;
/* 2049 */     double fToll = this.fTol;
/* 2050 */     this.lastMethod = 3;
/* 2051 */     this.linNonLin = false;
/* 2052 */     this.zeroCheck = false;
/* 2053 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2054 */     nelderMead(regFun, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double[] step, int nMax)
/*      */   {
/* 2061 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2062 */     this.lastMethod = 3;
/* 2063 */     this.linNonLin = false;
/* 2064 */     this.zeroCheck = false;
/* 2065 */     simplex2(g, start, step, nMax);
/* 2066 */     if (!this.supressPrint) print();
/* 2067 */     int flag = 0;
/* 2068 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2069 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double[] step)
/*      */   {
/* 2076 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2077 */     Object regFun = g;
/* 2078 */     double fToll = this.fTol;
/* 2079 */     int nMaxx = this.nMax;
/* 2080 */     this.lastMethod = 3;
/* 2081 */     this.linNonLin = false;
/* 2082 */     this.zeroCheck = false;
/* 2083 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2084 */     nelderMead(regFun, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double[] step)
/*      */   {
/* 2092 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2093 */     this.lastMethod = 3;
/* 2094 */     this.linNonLin = false;
/* 2095 */     this.zeroCheck = false;
/* 2096 */     simplex2(g, start, step);
/* 2097 */     if (!this.supressPrint) print();
/* 2098 */     int flag = 0;
/* 2099 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2100 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double fTol, int nMax)
/*      */   {
/* 2106 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2107 */     Object regFun = g;
/* 2108 */     int n = start.length;
/* 2109 */     double[] stepp = new double[n];
/* 2110 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 2111 */     this.lastMethod = 3;
/* 2112 */     this.linNonLin = false;
/* 2113 */     this.zeroCheck = false;
/* 2114 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2115 */     nelderMead(regFun, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double fTol, int nMax)
/*      */   {
/* 2122 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2123 */     this.lastMethod = 3;
/* 2124 */     this.linNonLin = false;
/* 2125 */     this.zeroCheck = false;
/* 2126 */     simplex2(g, start, fTol, nMax);
/* 2127 */     if (!this.supressPrint) print();
/* 2128 */     int flag = 0;
/* 2129 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2130 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex2(RegressionFunction2 g, double[] start, double fTol)
/*      */   {
/* 2137 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2138 */     Object regFun = g;
/* 2139 */     int n = start.length;
/* 2140 */     int nMaxx = this.nMax;
/* 2141 */     double[] stepp = new double[n];
/* 2142 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 2143 */     this.lastMethod = 3;
/* 2144 */     this.linNonLin = false;
/* 2145 */     this.zeroCheck = false;
/* 2146 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2147 */     nelderMead(regFun, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, double fTol)
/*      */   {
/* 2155 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2156 */     this.lastMethod = 3;
/* 2157 */     this.linNonLin = false;
/* 2158 */     this.zeroCheck = false;
/* 2159 */     simplex2(g, start, fTol);
/* 2160 */     if (!this.supressPrint) print();
/* 2161 */     int flag = 0;
/* 2162 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2163 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void simplex2(RegressionFunction2 g, double[] start, int nMax)
/*      */   {
/* 2170 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2171 */     Object regFun = g;
/* 2172 */     int n = start.length;
/* 2173 */     double fToll = this.fTol;
/* 2174 */     double[] stepp = new double[n];
/* 2175 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 2176 */     this.lastMethod = 3;
/* 2177 */     this.zeroCheck = false;
/* 2178 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2179 */     nelderMead(regFun, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start, int nMax)
/*      */   {
/* 2187 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2188 */     this.lastMethod = 3;
/* 2189 */     this.linNonLin = false;
/* 2190 */     this.zeroCheck = false;
/* 2191 */     simplex2(g, start, nMax);
/* 2192 */     if (!this.supressPrint) print();
/* 2193 */     int flag = 0;
/* 2194 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2195 */     if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void simplex2(RegressionFunction2 g, double[] start)
/*      */   {
/* 2203 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2204 */     Object regFun = g;
/* 2205 */     int n = start.length;
/* 2206 */     int nMaxx = this.nMax;
/* 2207 */     double fToll = this.fTol;
/* 2208 */     double[] stepp = new double[n];
/* 2209 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/* 2210 */     this.lastMethod = 3;
/* 2211 */     this.linNonLin = false;
/* 2212 */     this.zeroCheck = false;
/* 2213 */     this.degreesOfFreedom = (this.nData - start.length);
/* 2214 */     nelderMead(regFun, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void simplexPlot2(RegressionFunction2 g, double[] start)
/*      */   {
/* 2223 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 2224 */     this.lastMethod = 3;
/* 2225 */     this.linNonLin = false;
/* 2226 */     this.zeroCheck = false;
/* 2227 */     simplex2(g, start);
/* 2228 */     if (!this.supressPrint) print();
/* 2229 */     int flag = 0;
/* 2230 */     if (this.xData.length < 2) flag = plotXY2(g);
/* 2231 */     if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */   }
/*      */   
/*      */   protected double sumSquares(Object regFun, double[] x)
/*      */   {
/* 2236 */     RegressionFunction g1 = null;
/* 2237 */     RegressionFunction2 g2 = null;
/* 2238 */     if (this.multipleY) {
/* 2239 */       g2 = (RegressionFunction2)regFun;
/*      */     }
/*      */     else {
/* 2242 */       g1 = (RegressionFunction)regFun;
/*      */     }
/*      */     
/* 2245 */     double ss = -3.0D;
/* 2246 */     double[] param = new double[this.nTerms];
/* 2247 */     double[] xd = new double[this.nXarrays];
/*      */     
/* 2249 */     for (int i = 0; i < this.nTerms; i++) { x[i] /= this.scale[i];
/*      */     }
/*      */     
/* 2252 */     double tempFunctVal = this.lastSSnoConstraint;
/* 2253 */     boolean test = true;
/* 2254 */     if (this.penalty) {
/* 2255 */       int k = 0;
/* 2256 */       for (int i = 0; i < this.nConstraints; i++) {
/* 2257 */         k = this.penaltyParam[i];
/* 2258 */         switch (this.penaltyCheck[i]) {
/* 2259 */         case -1:  if (param[k] < this.constraints[i]) {
/* 2260 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] - param[k]);
/* 2261 */             test = false; }
/* 2262 */           break;
/*      */         case 0: 
/* 2264 */           if (param[k] < this.constraints[i] * (1.0D - this.constraintTolerance)) {
/* 2265 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] * (1.0D - this.constraintTolerance) - param[k]);
/* 2266 */             test = false;
/*      */           }
/* 2268 */           if (param[k] > this.constraints[i] * (1.0D + this.constraintTolerance)) {
/* 2269 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i] * (1.0D + this.constraintTolerance));
/* 2270 */             test = false; }
/* 2271 */           break;
/*      */         case 1: 
/* 2273 */           if (param[k] > this.constraints[i]) {
/* 2274 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i]);
/* 2275 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 2283 */     if (this.sumPenalty) {
/* 2284 */       int kk = 0;
/* 2285 */       double pSign = 0.0D;
/* 2286 */       double sumPenaltySum = 0.0D;
/* 2287 */       for (int i = 0; i < this.nSumConstraints; i++) {
/* 2288 */         for (int j = 0; j < this.sumPenaltyNumber[i]; j++) {
/* 2289 */           kk = this.sumPenaltyParam[i][j];
/* 2290 */           pSign = this.sumPlusOrMinus[i][j];
/* 2291 */           sumPenaltySum += param[kk] * pSign;
/*      */         }
/* 2293 */         switch (this.sumPenaltyCheck[i]) {
/* 2294 */         case -1:  if (sumPenaltySum < this.sumConstraints[i]) {
/* 2295 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] - sumPenaltySum);
/* 2296 */             test = false; }
/* 2297 */           break;
/*      */         case 0: 
/* 2299 */           if (sumPenaltySum < this.sumConstraints[i] * (1.0D - this.constraintTolerance)) {
/* 2300 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] * (1.0D - this.constraintTolerance) - sumPenaltySum);
/* 2301 */             test = false;
/*      */           }
/* 2303 */           if (sumPenaltySum > this.sumConstraints[i] * (1.0D + this.constraintTolerance)) {
/* 2304 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i] * (1.0D + this.constraintTolerance));
/* 2305 */             test = false; }
/* 2306 */           break;
/*      */         case 1: 
/* 2308 */           if (sumPenaltySum > this.sumConstraints[i]) {
/* 2309 */             ss = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i]);
/* 2310 */             test = false;
/*      */           }
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 2317 */     if (test) {
/* 2318 */       ss = 0.0D;
/* 2319 */       for (int i = 0; i < this.nData; i++) {
/* 2320 */         for (int j = 0; j < this.nXarrays; j++) xd[j] = this.xData[j][i];
/* 2321 */         if (!this.multipleY) {
/* 2322 */           ss += Fmath.square((this.yData[i] - g1.function(param, xd)) / this.weight[i]);
/*      */         }
/*      */         else {
/* 2325 */           ss += Fmath.square((this.yData[i] - g2.function(param, xd, i)) / this.weight[i]);
/*      */         }
/*      */       }
/*      */       
/* 2329 */       this.lastSSnoConstraint = ss;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2334 */     return ss;
/*      */   }
/*      */   
/*      */ 
/*      */   public void addConstraint(int paramIndex, int conDir, double constraint)
/*      */   {
/* 2340 */     this.penalty = true;
/*      */     
/*      */ 
/* 2343 */     if (this.penalties.isEmpty()) { this.penalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/* 2346 */     if (this.penalties.size() == 1) {
/* 2347 */       this.penalties.add(new Integer(1));
/*      */     }
/*      */     else {
/* 2350 */       int nPC = ((Integer)this.penalties.get(1)).intValue();
/* 2351 */       nPC++;
/* 2352 */       this.penalties.set(1, new Integer(nPC));
/*      */     }
/* 2354 */     this.penalties.add(new Integer(paramIndex));
/* 2355 */     this.penalties.add(new Integer(conDir));
/* 2356 */     this.penalties.add(new Double(constraint));
/* 2357 */     if (paramIndex > this.maxConstraintIndex) this.maxConstraintIndex = paramIndex;
/*      */   }
/*      */   
/*      */   public void addConstraint(int[] paramIndices, int[] plusOrMinus, int conDir, double constraint)
/*      */   {
/* 2362 */     ArrayMaths am = new ArrayMaths(plusOrMinus);
/* 2363 */     double[] dpom = am.getArray_as_double();
/* 2364 */     addConstraint(paramIndices, dpom, conDir, constraint);
/*      */   }
/*      */   
/*      */ 
/*      */   public void addConstraint(int[] paramIndices, double[] plusOrMinus, int conDir, double constraint)
/*      */   {
/* 2370 */     int nCon = paramIndices.length;
/* 2371 */     int nPorM = plusOrMinus.length;
/* 2372 */     if (nCon != nPorM) throw new IllegalArgumentException("num of parameters, " + nCon + ", does not equal number of parameter signs, " + nPorM);
/* 2373 */     this.sumPenalty = true;
/*      */     
/*      */ 
/* 2376 */     if (this.sumPenalties.isEmpty()) { this.sumPenalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/* 2379 */     if (this.sumPenalties.size() == 1) {
/* 2380 */       this.sumPenalties.add(new Integer(1));
/*      */     }
/*      */     else {
/* 2383 */       int nPC = ((Integer)this.sumPenalties.get(1)).intValue();
/* 2384 */       nPC++;
/* 2385 */       this.sumPenalties.set(1, new Integer(nPC));
/*      */     }
/* 2387 */     this.sumPenalties.add(new Integer(nCon));
/* 2388 */     this.sumPenalties.add(paramIndices);
/* 2389 */     this.sumPenalties.add(plusOrMinus);
/* 2390 */     this.sumPenalties.add(new Integer(conDir));
/* 2391 */     this.sumPenalties.add(new Double(constraint));
/* 2392 */     ArrayMaths am = new ArrayMaths(paramIndices);
/* 2393 */     int maxI = am.getMaximum_as_int();
/* 2394 */     if (maxI > this.maxConstraintIndex) { this.maxConstraintIndex = maxI;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void removeConstraints()
/*      */   {
/* 2401 */     if (!this.penalties.isEmpty()) {
/* 2402 */       int m = this.penalties.size();
/*      */       
/*      */ 
/* 2405 */       for (int i = m - 1; i >= 0; i--) {
/* 2406 */         this.penalties.remove(i);
/*      */       }
/*      */     }
/* 2409 */     this.penalty = false;
/* 2410 */     this.nConstraints = 0;
/*      */     
/*      */ 
/* 2413 */     if (!this.sumPenalties.isEmpty()) {
/* 2414 */       int m = this.sumPenalties.size();
/*      */       
/*      */ 
/* 2417 */       for (int i = m - 1; i >= 0; i--) {
/* 2418 */         this.sumPenalties.remove(i);
/*      */       }
/*      */     }
/* 2421 */     this.sumPenalty = false;
/* 2422 */     this.nSumConstraints = 0;
/* 2423 */     this.maxConstraintIndex = -1;
/*      */   }
/*      */   
/*      */   public void setConstraintTolerance(double tolerance)
/*      */   {
/* 2428 */     this.constraintTolerance = tolerance;
/*      */   }
/*      */   
/*      */   protected int pseudoLinearStats(Object regFun)
/*      */   {
/* 2433 */     double f1 = 0.0D;double f2 = 0.0D;double f3 = 0.0D;double f4 = 0.0D;
/* 2434 */     int flag = 0;
/*      */     
/*      */ 
/*      */ 
/* 2438 */     int np = this.nTerms;
/*      */     
/* 2440 */     double[] f = new double[np];
/* 2441 */     double[] pmin = new double[np];
/* 2442 */     double[] coeffSd = new double[np];
/* 2443 */     double[] xd = new double[this.nXarrays];
/* 2444 */     double[][] stat = new double[np][np];
/* 2445 */     this.pseudoSd = new double[np];
/*      */     
/* 2447 */     Double temp = null;
/*      */     
/* 2449 */     this.grad = new double[np][2];
/* 2450 */     this.covar = new double[np][np];
/* 2451 */     this.corrCoeff = new double[np][np];
/*      */     
/*      */ 
/* 2454 */     pmin = (double[])this.best.clone();
/*      */     
/*      */ 
/* 2457 */     double hold0 = 1.0D;
/* 2458 */     double hold1 = 1.0D;
/* 2459 */     for (int i = 0; i < np; i++) {
/* 2460 */       for (int k = 0; k < np; k++) {
/* 2461 */         f[k] = pmin[k];
/*      */       }
/* 2463 */       hold0 = pmin[i];
/* 2464 */       if (hold0 == 0.0D) {
/* 2465 */         hold0 = this.step[i];
/* 2466 */         this.zeroCheck = true;
/*      */       }
/* 2468 */       f[i] = (hold0 * (1.0D - this.delta));
/* 2469 */       this.lastSSnoConstraint = this.sumOfSquares;
/* 2470 */       f1 = sumSquares(regFun, f);
/* 2471 */       f[i] = (hold0 * (1.0D + this.delta));
/* 2472 */       this.lastSSnoConstraint = this.sumOfSquares;
/* 2473 */       f2 = sumSquares(regFun, f);
/* 2474 */       this.grad[i][0] = ((this.fMin - f1) / Math.abs(this.delta * hold0));
/* 2475 */       this.grad[i][1] = ((f2 - this.fMin) / Math.abs(this.delta * hold0));
/*      */     }
/*      */     
/*      */ 
/* 2479 */     this.lastSSnoConstraint = this.sumOfSquares;
/* 2480 */     for (int i = 0; i < np; i++) {
/* 2481 */       for (int j = 0; j < np; j++) {
/* 2482 */         for (int k = 0; k < np; k++) {
/* 2483 */           f[k] = pmin[k];
/*      */         }
/* 2485 */         hold0 = f[i];
/* 2486 */         if (hold0 == 0.0D) {
/* 2487 */           hold0 = this.step[i];
/* 2488 */           this.zeroCheck = true;
/*      */         }
/* 2490 */         f[i] = (hold0 * (1.0D + this.delta / 2.0D));
/* 2491 */         hold0 = f[j];
/* 2492 */         if (hold0 == 0.0D) {
/* 2493 */           hold0 = this.step[j];
/* 2494 */           this.zeroCheck = true;
/*      */         }
/* 2496 */         f[j] = (hold0 * (1.0D + this.delta / 2.0D));
/* 2497 */         this.lastSSnoConstraint = this.sumOfSquares;
/* 2498 */         f1 = sumSquares(regFun, f);
/* 2499 */         f[i] = pmin[i];
/* 2500 */         f[j] = pmin[j];
/* 2501 */         hold0 = f[i];
/* 2502 */         if (hold0 == 0.0D) {
/* 2503 */           hold0 = this.step[i];
/* 2504 */           this.zeroCheck = true;
/*      */         }
/* 2506 */         f[i] = (hold0 * (1.0D - this.delta / 2.0D));
/* 2507 */         hold0 = f[j];
/* 2508 */         if (hold0 == 0.0D) {
/* 2509 */           hold0 = this.step[j];
/* 2510 */           this.zeroCheck = true;
/*      */         }
/* 2512 */         f[j] = (hold0 * (1.0D + this.delta / 2.0D));
/* 2513 */         this.lastSSnoConstraint = this.sumOfSquares;
/* 2514 */         f2 = sumSquares(regFun, f);
/* 2515 */         f[i] = pmin[i];
/* 2516 */         f[j] = pmin[j];
/* 2517 */         hold0 = f[i];
/* 2518 */         if (hold0 == 0.0D) {
/* 2519 */           hold0 = this.step[i];
/* 2520 */           this.zeroCheck = true;
/*      */         }
/* 2522 */         f[i] = (hold0 * (1.0D + this.delta / 2.0D));
/* 2523 */         hold0 = f[j];
/* 2524 */         if (hold0 == 0.0D) {
/* 2525 */           hold0 = this.step[j];
/* 2526 */           this.zeroCheck = true;
/*      */         }
/* 2528 */         f[j] = (hold0 * (1.0D - this.delta / 2.0D));
/* 2529 */         this.lastSSnoConstraint = this.sumOfSquares;
/* 2530 */         f3 = sumSquares(regFun, f);
/* 2531 */         f[i] = pmin[i];
/* 2532 */         f[j] = pmin[j];
/* 2533 */         hold0 = f[i];
/* 2534 */         if (hold0 == 0.0D) {
/* 2535 */           hold0 = this.step[i];
/* 2536 */           this.zeroCheck = true;
/*      */         }
/* 2538 */         f[i] = (hold0 * (1.0D - this.delta / 2.0D));
/* 2539 */         hold0 = f[j];
/* 2540 */         if (hold0 == 0.0D) {
/* 2541 */           hold0 = this.step[j];
/* 2542 */           this.zeroCheck = true;
/*      */         }
/* 2544 */         f[j] = (hold0 * (1.0D - this.delta / 2.0D));
/* 2545 */         this.lastSSnoConstraint = this.sumOfSquares;
/* 2546 */         f4 = sumSquares(regFun, f);
/* 2547 */         stat[i][j] = ((f1 - f2 - f3 + f4) / (this.delta * this.delta));
/*      */       }
/*      */     }
/*      */     
/* 2551 */     double ss = 0.0D;
/* 2552 */     double sc = 0.0D;
/* 2553 */     for (int i = 0; i < this.nData; i++) {
/* 2554 */       for (int j = 0; j < this.nXarrays; j++) xd[j] = this.xData[j][i];
/* 2555 */       if (this.multipleY) {
/* 2556 */         this.yCalc[i] = ((RegressionFunction2)regFun).function(pmin, xd, i);
/*      */       }
/*      */       else {
/* 2559 */         this.yCalc[i] = ((RegressionFunction)regFun).function(pmin, xd);
/*      */       }
/* 2561 */       this.residual[i] = (this.yCalc[i] - this.yData[i]);
/* 2562 */       ss += Fmath.square(this.residual[i]);
/* 2563 */       this.residualW[i] = (this.residual[i] / this.weight[i]);
/* 2564 */       sc += Fmath.square(this.residualW[i]);
/*      */     }
/* 2566 */     this.sumOfSquares = ss;
/* 2567 */     double varY = ss / (this.nData - np);
/* 2568 */     double sdY = Math.sqrt(varY);
/* 2569 */     if ((this.weightOpt) || (this.trueFreq)) {
/* 2570 */       this.chiSquare = sc;
/* 2571 */       this.reducedChiSquare = (sc / (this.nData - np));
/*      */     }
/*      */     
/*      */ 
/* 2575 */     double red = 1.0D;
/* 2576 */     if ((!this.weightOpt) && (!this.trueFreq)) { red = this.sumOfSquares / (this.nData - np);
/*      */     }
/*      */     
/* 2579 */     for (int i = 0; i < np; i++) {
/* 2580 */       this.pseudoSd[i] = (2.0D * this.delta * red * Math.abs(pmin[i]) / (this.grad[i][1] - this.grad[i][0]));
/* 2581 */       if (this.pseudoSd[i] >= 0.0D) {
/* 2582 */         this.pseudoSd[i] = Math.sqrt(this.pseudoSd[i]);
/*      */       }
/*      */       else {
/* 2585 */         this.pseudoSd[i] = NaN.0D;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2590 */     if (np == 1) {
/* 2591 */       hold0 = pmin[0];
/* 2592 */       if (hold0 == 0.0D) hold0 = this.step[0];
/* 2593 */       stat[0][0] = (1.0D / stat[0][0]);
/* 2594 */       this.covar[0][0] = (stat[0][0] * red * hold0 * hold0);
/* 2595 */       if (this.covar[0][0] >= 0.0D) {
/* 2596 */         coeffSd[0] = Math.sqrt(this.covar[0][0]);
/* 2597 */         this.corrCoeff[0][0] = 1.0D;
/*      */       }
/*      */       else {
/* 2600 */         coeffSd[0] = NaN.0D;
/* 2601 */         this.corrCoeff[0][0] = NaN.0D;
/* 2602 */         this.posVarFlag = false;
/*      */       }
/*      */     }
/*      */     else {
/* 2606 */       Matrix cov = new Matrix(stat);
/* 2607 */       if (this.supressErrorMessages) cov.supressErrorMessage();
/* 2608 */       cov = cov.inverse();
/* 2609 */       this.invertFlag = cov.getMatrixCheck();
/* 2610 */       if (!this.invertFlag) flag--;
/* 2611 */       stat = cov.getArrayCopy();
/*      */       
/* 2613 */       this.posVarFlag = true;
/* 2614 */       if (this.invertFlag) {
/* 2615 */         for (int i = 0; i < np; i++) {
/* 2616 */           hold0 = pmin[i];
/* 2617 */           if (hold0 == 0.0D) hold0 = this.step[i];
/* 2618 */           for (int j = i; j < np; j++) {
/* 2619 */             hold1 = pmin[j];
/* 2620 */             if (hold1 == 0.0D) hold1 = this.step[j];
/* 2621 */             this.covar[i][j] = (2.0D * stat[i][j] * red * hold0 * hold1);
/* 2622 */             this.covar[j][i] = this.covar[i][j];
/*      */           }
/* 2624 */           if (this.covar[i][i] >= 0.0D) {
/* 2625 */             coeffSd[i] = Math.sqrt(this.covar[i][i]);
/*      */           }
/*      */           else {
/* 2628 */             coeffSd[i] = NaN.0D;
/* 2629 */             this.posVarFlag = false;
/*      */           }
/*      */         }
/*      */         
/* 2633 */         for (int i = 0; i < np; i++) {
/* 2634 */           for (int j = 0; j < np; j++) {
/* 2635 */             if ((coeffSd[i] != NaN.0D) && (coeffSd[j] != NaN.0D)) {
/* 2636 */               this.corrCoeff[i][j] = (this.covar[i][j] / (coeffSd[i] * coeffSd[j]));
/*      */             }
/*      */             else {
/* 2639 */               this.corrCoeff[i][j] = NaN.0D;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/* 2645 */         for (int i = 0; i < np; i++) {
/* 2646 */           for (int j = 0; j < np; j++) {
/* 2647 */             this.covar[i][j] = NaN.0D;
/* 2648 */             this.corrCoeff[i][j] = NaN.0D;
/*      */           }
/* 2650 */           coeffSd[i] = NaN.0D;
/* 2651 */           this.posVarFlag = false;
/*      */         }
/*      */       }
/*      */     }
/* 2655 */     if (!this.posVarFlag) { flag--;
/*      */     }
/* 2657 */     for (int i = 0; i < this.nTerms; i++) {
/* 2658 */       this.bestSd[i] = coeffSd[i];
/* 2659 */       this.tValues[i] = (this.best[i] / this.bestSd[i]);
/* 2660 */       double atv = Math.abs(this.tValues[i]);
/* 2661 */       this.pValues[i] = (1.0D - Stat.studentTcdf(-atv, atv, this.degreesOfFreedom));
/*      */     }
/*      */     
/*      */ 
/* 2665 */     multCorrelCoeff(this.yData, this.yCalc, this.weight);
/*      */     
/* 2667 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void print(String filename, int prec)
/*      */   {
/* 2675 */     this.prec = prec;
/* 2676 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(int prec)
/*      */   {
/* 2683 */     this.prec = prec;
/* 2684 */     String filename = "RegressionOutput.txt";
/* 2685 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(String filename)
/*      */   {
/* 2692 */     if (filename.indexOf('.') == -1) filename = filename + ".txt";
/* 2693 */     FileOutput fout = new FileOutput(filename, 'n');
/* 2694 */     fout.dateAndTimeln(filename);
/* 2695 */     fout.println(this.graphTitle);
/* 2696 */     this.paraName = new String[this.nTerms];
/* 2697 */     if (this.lastMethod == 38) this.paraName = new String[3];
/* 2698 */     if (this.weightOpt) {
/* 2699 */       fout.println("Weighted Least Squares Minimisation");
/*      */     }
/*      */     else {
/* 2702 */       fout.println("Unweighted Least Squares Minimisation");
/*      */     }
/* 2704 */     switch (this.lastMethod) {
/* 2705 */     case 0:  fout.println("Linear Regression with intercept");
/* 2706 */       fout.println("y = c[0] + c[1]*x1 + c[2]*x2 +c[3]*x3 + . . .");
/* 2707 */       for (int i = 0; i < this.nTerms; i++) this.paraName[i] = ("c[" + i + "]");
/* 2708 */       linearPrint(fout);
/* 2709 */       break;
/* 2710 */     case 1:  fout.println("Polynomial (with degree = " + (this.nTerms - 1) + ") Fitting Linear Regression");
/* 2711 */       fout.println("y = c[0] + c[1]*x + c[2]*x^2 +c[3]*x^3 + . . .");
/* 2712 */       for (int i = 0; i < this.nTerms; i++) this.paraName[i] = ("c[" + i + "]");
/* 2713 */       linearPrint(fout);
/* 2714 */       break;
/* 2715 */     case 2:  fout.println("Generalised linear regression");
/* 2716 */       fout.println("y = c[0]*f1(x) + c[1]*f2(x) + c[2]*f3(x) + . . .");
/* 2717 */       for (int i = 0; i < this.nTerms; i++) this.paraName[i] = ("c[" + i + "]");
/* 2718 */       linearPrint(fout);
/* 2719 */       break;
/* 2720 */     case 3:  fout.println("Nelder and Mead Simplex Non-linear Regression");
/* 2721 */       fout.println("y = f(x1, x2, x3 . . ., c[0], c[1], c[2] . . .");
/* 2722 */       fout.println("y is non-linear with respect to the c[i]");
/* 2723 */       for (int i = 0; i < this.nTerms; i++) this.paraName[i] = ("c[" + i + "]");
/* 2724 */       nonLinearPrint(fout);
/* 2725 */       break;
/* 2726 */     case 4:  fout.println("Fitting to a Normal (Gaussian) distribution");
/* 2727 */       fout.println("y = (yscale/(sd.sqrt(2.pi)).exp(0.5.square((x-mean)/sd))");
/* 2728 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2729 */       this.paraName[0] = "mean";
/* 2730 */       this.paraName[1] = "sd";
/* 2731 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2732 */       nonLinearPrint(fout);
/* 2733 */       break;
/* 2734 */     case 5:  fout.println("Fitting to a Lorentzian distribution");
/* 2735 */       fout.println("y = (yscale/pi).(gamma/2)/((x-mean)^2+(gamma/2)^2)");
/* 2736 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2737 */       this.paraName[0] = "mean";
/* 2738 */       this.paraName[1] = "gamma";
/* 2739 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2740 */       nonLinearPrint(fout);
/* 2741 */       break;
/* 2742 */     case 6:  fout.println("Fitting to a Poisson distribution");
/* 2743 */       fout.println("y = yscale.mu^k.exp(-mu)/mu!");
/* 2744 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2745 */       this.paraName[0] = "mean";
/* 2746 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2747 */       nonLinearPrint(fout);
/* 2748 */       break;
/* 2749 */     case 7:  fout.println("Fitting to a Two Parameter Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2750 */       fout.println("y = (yscale/sigma)*exp((x - mu)/sigma))*exp(-exp((x-mu)/sigma))");
/* 2751 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2752 */       this.paraName[0] = "mu";
/* 2753 */       this.paraName[1] = "sigma";
/* 2754 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2755 */       nonLinearPrint(fout);
/* 2756 */       break;
/* 2757 */     case 8:  fout.println("Fitting to a Two Parameter Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2758 */       fout.println("y = (yscale/sigma)*exp(-(x - mu)/sigma))*exp(-exp(-(x-mu)/sigma))");
/* 2759 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2760 */       this.paraName[0] = "mu";
/* 2761 */       this.paraName[1] = "sigma";
/* 2762 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2763 */       nonLinearPrint(fout);
/* 2764 */       break;
/* 2765 */     case 9:  fout.println("Fitting to a One Parameter Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2766 */       fout.println("y = (yscale)*exp(x/sigma))*exp(-exp(x/sigma))");
/* 2767 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2768 */       this.paraName[0] = "sigma";
/* 2769 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2770 */       nonLinearPrint(fout);
/* 2771 */       break;
/* 2772 */     case 10:  fout.println("Fitting to a One Parameter Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2773 */       fout.println("y = (yscale)*exp(-x/sigma))*exp(-exp(-x/sigma))");
/* 2774 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2775 */       this.paraName[0] = "sigma";
/* 2776 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2777 */       nonLinearPrint(fout);
/* 2778 */       break;
/* 2779 */     case 11:  fout.println("Fitting to a Standard Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2780 */       fout.println("y = (yscale)*exp(x))*exp(-exp(x))");
/* 2781 */       fout.println("Linear regression used to fit y = yscale*z where z = exp(x))*exp(-exp(x)))");
/* 2782 */       if (this.scaleFlag) this.paraName[0] = "y scale";
/* 2783 */       linearPrint(fout);
/* 2784 */       break;
/* 2785 */     case 12:  fout.println("Fitting to a Standard Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
/* 2786 */       fout.println("y = (yscale)*exp(-x))*exp(-exp(-x))");
/* 2787 */       fout.println("Linear regression used to fit y = yscale*z where z = exp(-x))*exp(-exp(-x)))");
/* 2788 */       if (this.scaleFlag) this.paraName[0] = "y scale";
/* 2789 */       linearPrint(fout);
/* 2790 */       break;
/* 2791 */     case 13:  fout.println("Fitting to a Three Parameter Frechet [Type 2 Extreme Value] Distribution");
/* 2792 */       fout.println("y = yscale.(gamma/sigma)*((x - mu)/sigma)^(-gamma-1)*exp(-((x-mu)/sigma)^-gamma");
/* 2793 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2794 */       this.paraName[0] = "mu";
/* 2795 */       this.paraName[1] = "sigma";
/* 2796 */       this.paraName[2] = "gamma";
/* 2797 */       if (this.scaleFlag) this.paraName[3] = "y scale";
/* 2798 */       nonLinearPrint(fout);
/* 2799 */       break;
/* 2800 */     case 14:  fout.println("Fitting to a Two parameter Frechet [Type2  Extreme Value] Distribution");
/* 2801 */       fout.println("y = yscale.(gamma/sigma)*(x/sigma)^(-gamma-1)*exp(-(x/sigma)^-gamma");
/* 2802 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2803 */       this.paraName[0] = "sigma";
/* 2804 */       this.paraName[1] = "gamma";
/* 2805 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2806 */       nonLinearPrint(fout);
/* 2807 */       break;
/* 2808 */     case 15:  fout.println("Fitting to a Standard Frechet [Type 2 Extreme Value] Distribution");
/* 2809 */       fout.println("y = yscale.gamma*(x)^(-gamma-1)*exp(-(x)^-gamma");
/* 2810 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2811 */       this.paraName[0] = "gamma";
/* 2812 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2813 */       nonLinearPrint(fout);
/* 2814 */       break;
/* 2815 */     case 16:  fout.println("Fitting to a Three parameter Weibull [Type 3 Extreme Value] Distribution");
/* 2816 */       fout.println("y = yscale.(gamma/sigma)*((x - mu)/sigma)^(gamma-1)*exp(-((x-mu)/sigma)^gamma");
/* 2817 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2818 */       this.paraName[0] = "mu";
/* 2819 */       this.paraName[1] = "sigma";
/* 2820 */       this.paraName[2] = "gamma";
/* 2821 */       if (this.scaleFlag) this.paraName[3] = "y scale";
/* 2822 */       nonLinearPrint(fout);
/* 2823 */       break;
/* 2824 */     case 17:  fout.println("Fitting to a Two parameter Weibull [Type 3 Extreme Value] Distribution");
/* 2825 */       fout.println("y = yscale.(gamma/sigma)*(x/sigma)^(gamma-1)*exp(-(x/sigma)^gamma");
/* 2826 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2827 */       this.paraName[0] = "sigma";
/* 2828 */       this.paraName[1] = "gamma";
/* 2829 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2830 */       nonLinearPrint(fout);
/* 2831 */       break;
/* 2832 */     case 18:  fout.println("Fitting to a Standard Weibull [Type 3 Extreme Value] Distribution");
/* 2833 */       fout.println("y = yscale.gamma*(x)^(gamma-1)*exp(-(x)^gamma");
/* 2834 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2835 */       this.paraName[0] = "gamma";
/* 2836 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2837 */       nonLinearPrint(fout);
/* 2838 */       break;
/* 2839 */     case 19:  fout.println("Fitting to a Two parameter Exponential Distribution");
/* 2840 */       fout.println("y = (yscale/sigma)*exp(-(x-mu)/sigma)");
/* 2841 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2842 */       this.paraName[0] = "mu";
/* 2843 */       this.paraName[1] = "sigma";
/* 2844 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2845 */       nonLinearPrint(fout);
/* 2846 */       break;
/* 2847 */     case 20:  fout.println("Fitting to a One parameter Exponential Distribution");
/* 2848 */       fout.println("y = (yscale/sigma)*exp(-x/sigma)");
/* 2849 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2850 */       this.paraName[0] = "sigma";
/* 2851 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2852 */       nonLinearPrint(fout);
/* 2853 */       break;
/* 2854 */     case 21:  fout.println("Fitting to a Standard Exponential Distribution");
/* 2855 */       fout.println("y = yscale*exp(-x)");
/* 2856 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2857 */       if (this.scaleFlag) this.paraName[0] = "y scale";
/* 2858 */       nonLinearPrint(fout);
/* 2859 */       break;
/* 2860 */     case 22:  fout.println("Fitting to a Rayleigh Distribution");
/* 2861 */       fout.println("y = (yscale/sigma)*(x/sigma)*exp(-0.5*(x/sigma)^2)");
/* 2862 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2863 */       this.paraName[0] = "sigma";
/* 2864 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2865 */       nonLinearPrint(fout);
/* 2866 */       break;
/* 2867 */     case 23:  fout.println("Fitting to a Two Parameter Pareto Distribution");
/* 2868 */       fout.println("y = yscale*(alpha*beta^alpha)/(x^(alpha+1))");
/* 2869 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2870 */       this.paraName[0] = "alpha";
/* 2871 */       this.paraName[1] = "beta";
/* 2872 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2873 */       nonLinearPrint(fout);
/* 2874 */       break;
/* 2875 */     case 24:  fout.println("Fitting to a One Parameter Pareto Distribution");
/* 2876 */       fout.println("y = yscale*(alpha)/(x^(alpha+1))");
/* 2877 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2878 */       this.paraName[0] = "alpha";
/* 2879 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2880 */       nonLinearPrint(fout);
/* 2881 */       break;
/* 2882 */     case 25:  fout.println("Fitting to a Sigmoidal Threshold Function");
/* 2883 */       fout.println("y = yscale/(1 + exp(-slopeTerm(x - theta)))");
/* 2884 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2885 */       this.paraName[0] = "slope term";
/* 2886 */       this.paraName[1] = "theta";
/* 2887 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2888 */       nonLinearPrint(fout);
/* 2889 */       break;
/* 2890 */     case 26:  fout.println("Fitting to a Rectangular Hyperbola");
/* 2891 */       fout.println("y = yscale.x/(theta + x)");
/* 2892 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2893 */       this.paraName[0] = "theta";
/* 2894 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2895 */       nonLinearPrint(fout);
/* 2896 */       break;
/* 2897 */     case 27:  fout.println("Fitting to a Scaled Heaviside Step Function");
/* 2898 */       fout.println("y = yscale.H(x - theta)");
/* 2899 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2900 */       this.paraName[0] = "theta";
/* 2901 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2902 */       nonLinearPrint(fout);
/* 2903 */       break;
/* 2904 */     case 28:  fout.println("Fitting to a Hill/Sips Sigmoid");
/* 2905 */       fout.println("y = yscale.x^n/(theta^n + x^n)");
/* 2906 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2907 */       this.paraName[0] = "theta";
/* 2908 */       this.paraName[1] = "n";
/* 2909 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2910 */       nonLinearPrint(fout);
/* 2911 */       break;
/* 2912 */     case 29:  fout.println("Fitting to a Shifted Pareto Distribution");
/* 2913 */       fout.println("y = yscale*(alpha*beta^alpha)/((x-theta)^(alpha+1))");
/* 2914 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2915 */       this.paraName[0] = "alpha";
/* 2916 */       this.paraName[1] = "beta";
/* 2917 */       this.paraName[2] = "theta";
/* 2918 */       if (this.scaleFlag) this.paraName[3] = "y scale";
/* 2919 */       nonLinearPrint(fout);
/* 2920 */       break;
/* 2921 */     case 30:  fout.println("Fitting to a Logistic distribution");
/* 2922 */       fout.println("y = yscale*exp(-(x-mu)/beta)/(beta*(1 + exp(-(x-mu)/beta))^2");
/* 2923 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2924 */       this.paraName[0] = "mu";
/* 2925 */       this.paraName[1] = "beta";
/* 2926 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2927 */       nonLinearPrint(fout);
/* 2928 */       break;
/* 2929 */     case 31:  fout.println("Fitting to a Beta distribution - [0, 1] interval");
/* 2930 */       fout.println("y = yscale*x^(alpha-1)*(1-x)^(beta-1)/B(alpha, beta)");
/* 2931 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2932 */       this.paraName[0] = "alpha";
/* 2933 */       this.paraName[1] = "beta";
/* 2934 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2935 */       nonLinearPrint(fout);
/* 2936 */       break;
/* 2937 */     case 32:  fout.println("Fitting to a Beta distribution - [min, max] interval");
/* 2938 */       fout.println("y = yscale*(x-min)^(alpha-1)*(max-x)^(beta-1)/(B(alpha, beta)*(max-min)^(alpha+beta-1)");
/* 2939 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2940 */       this.paraName[0] = "alpha";
/* 2941 */       this.paraName[1] = "beta";
/* 2942 */       this.paraName[2] = "min";
/* 2943 */       this.paraName[3] = "max";
/* 2944 */       if (this.scaleFlag) this.paraName[4] = "y scale";
/* 2945 */       nonLinearPrint(fout);
/* 2946 */       break;
/* 2947 */     case 33:  fout.println("Fitting to a Three Parameter Gamma distribution");
/* 2948 */       fout.println("y = yscale*((x-mu)/beta)^(gamma-1)*exp(-(x-mu)/beta)/(beta*Gamma(gamma))");
/* 2949 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2950 */       this.paraName[0] = "mu";
/* 2951 */       this.paraName[1] = "beta";
/* 2952 */       this.paraName[2] = "gamma";
/* 2953 */       if (this.scaleFlag) this.paraName[3] = "y scale";
/* 2954 */       nonLinearPrint(fout);
/* 2955 */       break;
/* 2956 */     case 34:  fout.println("Fitting to a Standard Gamma distribution");
/* 2957 */       fout.println("y = yscale*x^(gamma-1)*exp(-x)/Gamma(gamma)");
/* 2958 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2959 */       this.paraName[0] = "gamma";
/* 2960 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2961 */       nonLinearPrint(fout);
/* 2962 */       break;
/* 2963 */     case 35:  fout.println("Fitting to an Erang distribution");
/* 2964 */       fout.println("y = yscale*lambda^k*x^(k-1)*exp(-x*lambda)/(k-1)!");
/* 2965 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2966 */       this.paraName[0] = "lambda";
/* 2967 */       if (this.scaleFlag) this.paraName[1] = "y scale";
/* 2968 */       nonLinearPrint(fout);
/* 2969 */       break;
/* 2970 */     case 36:  fout.println("Fitting to a two parameter log-normal distribution");
/* 2971 */       fout.println("y = (yscale/(x.sigma.sqrt(2.pi)).exp(0.5.square((log(x)-muu)/sigma))");
/* 2972 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2973 */       this.paraName[0] = "mu";
/* 2974 */       this.paraName[1] = "sigma";
/* 2975 */       if (this.scaleFlag) this.paraName[2] = "y scale";
/* 2976 */       nonLinearPrint(fout);
/* 2977 */       break;
/* 2978 */     case 37:  fout.println("Fitting to a three parameter log-normal distribution");
/* 2979 */       fout.println("y = (yscale/((x-alpha).beta.sqrt(2.pi)).exp(0.5.square((log(x-alpha)/gamma)/beta))");
/* 2980 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2981 */       this.paraName[0] = "alpha";
/* 2982 */       this.paraName[1] = "beta";
/* 2983 */       this.paraName[2] = "gamma";
/* 2984 */       if (this.scaleFlag) this.paraName[3] = "y scale";
/* 2985 */       nonLinearPrint(fout);
/* 2986 */       break;
/* 2987 */     case 38:  fout.println("Fitting to a Normal (Gaussian) distribution with fixed parameters");
/* 2988 */       fout.println("y = (yscale/(sd.sqrt(2.pi)).exp(0.5.square((x-mean)/sd))");
/* 2989 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2990 */       this.paraName[0] = "mean";
/* 2991 */       this.paraName[1] = "sd";
/* 2992 */       this.paraName[2] = "y scale";
/* 2993 */       nonLinearPrint(fout);
/* 2994 */       break;
/* 2995 */     case 39:  fout.println("Fitting to a EC50 dose response curve");
/* 2996 */       fout.println("y = bottom + (top - bottom)/(1 + (x/EC50)^HillSlope)");
/* 2997 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 2998 */       this.paraName[0] = "bottom";
/* 2999 */       this.paraName[1] = "top";
/* 3000 */       this.paraName[2] = "EC50";
/* 3001 */       this.paraName[3] = "Hill Slope";
/* 3002 */       nonLinearPrint(fout);
/* 3003 */       break;
/* 3004 */     case 40:  fout.println("Fitting to a LogEC50 dose response curve");
/* 3005 */       fout.println("y = bottom + (top - bottom)/(1 + 10^((logEC50 - x).HillSlope))");
/* 3006 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 3007 */       this.paraName[0] = "bottom";
/* 3008 */       this.paraName[1] = "top";
/* 3009 */       this.paraName[2] = "LogEC50";
/* 3010 */       this.paraName[3] = "Hill Slope";
/* 3011 */       nonLinearPrint(fout);
/* 3012 */       break;
/* 3013 */     case 41:  fout.println("Fitting to a EC50 dose response curve - bottom constrained to be zero or positive");
/* 3014 */       fout.println("y = bottom + (top - bottom)/(1 + (x/EC50)^HillSlope)");
/* 3015 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 3016 */       this.paraName[0] = "bottom";
/* 3017 */       this.paraName[1] = "top";
/* 3018 */       this.paraName[2] = "EC50";
/* 3019 */       this.paraName[3] = "Hill Slope";
/* 3020 */       nonLinearPrint(fout);
/* 3021 */       break;
/* 3022 */     case 42:  fout.println("Fitting to a LogEC50 dose response curve - bottom constrained to be zero or positive");
/* 3023 */       fout.println("y = bottom + (top - bottom)/(1 + 10^((logEC50 - x).HillSlope))");
/* 3024 */       fout.println("Nelder and Mead Simplex used to fit the data)");
/* 3025 */       this.paraName[0] = "bottom";
/* 3026 */       this.paraName[1] = "top";
/* 3027 */       this.paraName[2] = "LogEC50";
/* 3028 */       this.paraName[3] = "Hill Slope";
/* 3029 */       nonLinearPrint(fout);
/* 3030 */       break;
/* 3031 */     default:  throw new IllegalArgumentException("Method number (this.lastMethod) not found");
/*      */     }
/*      */     
/*      */     
/* 3035 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void print()
/*      */   {
/* 3041 */     String filename = "RegressOutput.txt";
/* 3042 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void linearPrint(FileOutput fout)
/*      */   {
/* 3048 */     if (this.legendCheck) {
/* 3049 */       fout.println();
/* 3050 */       fout.println("x1 = " + this.xLegend);
/* 3051 */       fout.println("y  = " + this.yLegend);
/*      */     }
/*      */     
/* 3054 */     fout.println();
/* 3055 */     fout.printtab(" ", this.field);
/* 3056 */     fout.printtab("Best", this.field);
/* 3057 */     fout.printtab("Error", this.field);
/* 3058 */     fout.printtab("Coefficient of", this.field);
/* 3059 */     fout.printtab("t-value  ", this.field);
/* 3060 */     fout.println("p-value");
/*      */     
/* 3062 */     fout.printtab(" ", this.field);
/* 3063 */     fout.printtab("Estimate", this.field);
/* 3064 */     fout.printtab("        ", this.field);
/* 3065 */     fout.printtab("variation (%)", this.field);
/* 3066 */     fout.printtab("t ", this.field);
/* 3067 */     fout.println("P > |t|");
/*      */     
/* 3069 */     for (int i = 0; i < this.nTerms; i++) {
/* 3070 */       fout.printtab(this.paraName[i], this.field);
/* 3071 */       fout.printtab(Fmath.truncate(this.best[i], this.prec), this.field);
/* 3072 */       fout.printtab(Fmath.truncate(this.bestSd[i], this.prec), this.field);
/* 3073 */       fout.printtab(Fmath.truncate(Math.abs(this.bestSd[i] * 100.0D / this.best[i]), this.prec), this.field);
/* 3074 */       fout.printtab(Fmath.truncate(this.tValues[i], this.prec), this.field);
/* 3075 */       fout.println(Fmath.truncate(this.pValues[i], this.prec));
/*      */     }
/* 3077 */     fout.println();
/*      */     
/* 3079 */     int ii = 0;
/* 3080 */     if (this.lastMethod < 2) ii = 1;
/* 3081 */     for (int i = 0; i < this.nXarrays; i++) {
/* 3082 */       fout.printtab("x" + String.valueOf(i + ii), this.field);
/*      */     }
/* 3084 */     fout.printtab("y(expl)", this.field);
/* 3085 */     fout.printtab("y(calc)", this.field);
/* 3086 */     fout.printtab("weight", this.field);
/* 3087 */     fout.printtab("residual", this.field);
/* 3088 */     fout.println("residual");
/*      */     
/* 3090 */     for (int i = 0; i < this.nXarrays; i++) {
/* 3091 */       fout.printtab(" ", this.field);
/*      */     }
/* 3093 */     fout.printtab(" ", this.field);
/* 3094 */     fout.printtab(" ", this.field);
/* 3095 */     fout.printtab(" ", this.field);
/* 3096 */     fout.printtab("(unweighted)", this.field);
/* 3097 */     fout.println("(weighted)");
/*      */     
/*      */ 
/* 3100 */     for (int i = 0; i < this.nData; i++) {
/* 3101 */       for (int j = 0; j < this.nXarrays; j++) {
/* 3102 */         fout.printtab(Fmath.truncate(this.xData[j][i], this.prec), this.field);
/*      */       }
/* 3104 */       fout.printtab(Fmath.truncate(this.yData[i], this.prec), this.field);
/* 3105 */       fout.printtab(Fmath.truncate(this.yCalc[i], this.prec), this.field);
/* 3106 */       fout.printtab(Fmath.truncate(this.weight[i], this.prec), this.field);
/* 3107 */       fout.printtab(Fmath.truncate(this.residual[i], this.prec), this.field);
/* 3108 */       fout.println(Fmath.truncate(this.residualW[i], this.prec));
/*      */     }
/* 3110 */     fout.println();
/* 3111 */     fout.println("Sum of squares " + Fmath.truncate(this.sumOfSquares, this.prec));
/* 3112 */     if (this.trueFreq) {
/* 3113 */       fout.printtab("Chi Square (Poissonian bins)");
/* 3114 */       fout.println(Fmath.truncate(this.chiSquare, this.prec));
/* 3115 */       fout.printtab("Reduced Chi Square (Poissonian bins)");
/* 3116 */       fout.println(Fmath.truncate(this.reducedChiSquare, this.prec));
/* 3117 */       fout.printtab("Chi Square (Poissonian bins) Probability");
/* 3118 */       fout.println(Fmath.truncate(1.0D - Stat.chiSquareProb(this.chiSquare, this.nData - this.nXarrays), this.prec));
/*      */ 
/*      */     }
/* 3121 */     else if (this.weightOpt) {
/* 3122 */       fout.printtab("Chi Square");
/* 3123 */       fout.println(Fmath.truncate(this.chiSquare, this.prec));
/* 3124 */       fout.printtab("Reduced Chi Square");
/* 3125 */       fout.println(Fmath.truncate(this.reducedChiSquare, this.prec));
/* 3126 */       fout.printtab("Chi Square Probability");
/* 3127 */       fout.println(Fmath.truncate(getchiSquareProb(), this.prec));
/*      */     }
/*      */     
/* 3130 */     fout.println(" ");
/* 3131 */     fout.println("Correlation: x - y data");
/* 3132 */     if (this.nXarrays > 1) {
/* 3133 */       fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient (R)");
/* 3134 */       fout.println(Fmath.truncate(this.sampleR, this.prec));
/* 3135 */       fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient Squared (R^2)");
/* 3136 */       fout.println(Fmath.truncate(this.sampleR2, this.prec));
/* 3137 */       if (this.sampleR2 <= 1.0D) {
/* 3138 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Correlation Coefficient F-test ratio");
/* 3139 */         fout.println(Fmath.truncate(this.multipleF, this.prec));
/* 3140 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Correlation Coefficient F-test probability");
/* 3141 */         fout.println(Fmath.truncate(Stat.fTestProb(this.multipleF, this.nXarrays - 1, this.nData - this.nXarrays), this.prec));
/*      */       }
/* 3143 */       fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Sample Correlation Coefficient (adjR)");
/* 3144 */       fout.println(Fmath.truncate(this.adjustedR, this.prec));
/* 3145 */       fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Sample Correlation Coefficient Squared (adjR*adjR)");
/* 3146 */       fout.println(Fmath.truncate(this.adjustedR2, this.prec));
/* 3147 */       if (this.sampleR2 <= 1.0D) {
/* 3148 */         fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Correlation Coefficient F-test ratio");
/* 3149 */         fout.println(Fmath.truncate(this.adjustedF, this.prec));
/* 3150 */         fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Correlation Coefficient F-test probability");
/* 3151 */         fout.println(Fmath.truncate(Stat.fTestProb(this.adjustedF, this.nXarrays - 1, this.nData - this.nXarrays), this.prec));
/*      */       }
/*      */     }
/*      */     else {
/* 3155 */       fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient (R)");
/* 3156 */       fout.println(Fmath.truncate(this.sampleR, this.prec));
/* 3157 */       fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Squared (R^2)");
/* 3158 */       fout.println(Fmath.truncate(this.sampleR2, this.prec));
/* 3159 */       if (this.sampleR2 <= 1.0D) {
/* 3160 */         fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
/* 3161 */         fout.println(Fmath.truncate(Stat.linearCorrCoeffProb(this.sampleR, this.nData - this.nTerms), this.prec));
/*      */       }
/*      */     }
/*      */     
/* 3165 */     fout.println(" ");
/* 3166 */     fout.println("Correlation: y(experimental) - y(calculated");
/* 3167 */     fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient");
/* 3168 */     double ccyy = Stat.corrCoeff(this.yData, this.yCalc, this.weight);
/*      */     
/* 3170 */     fout.println(Fmath.truncate(ccyy, this.prec));
/* 3171 */     fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
/* 3172 */     fout.println(Fmath.truncate(Stat.linearCorrCoeffProb(ccyy, this.nData - 1), this.prec));
/*      */     
/*      */ 
/* 3175 */     fout.println(" ");
/* 3176 */     fout.printtab("Degrees of freedom");
/* 3177 */     fout.println(this.nData - this.nTerms);
/* 3178 */     fout.printtab("Number of data points");
/* 3179 */     fout.println(this.nData);
/* 3180 */     fout.printtab("Number of estimated paramaters");
/* 3181 */     fout.println(this.nTerms);
/*      */     
/* 3183 */     fout.println();
/* 3184 */     if (this.chiSquare != 0.0D) {
/* 3185 */       fout.println("Correlation coefficients");
/* 3186 */       fout.printtab(" ", this.field);
/* 3187 */       for (int i = 0; i < this.nTerms; i++) {
/* 3188 */         fout.printtab(this.paraName[i], this.field);
/*      */       }
/* 3190 */       fout.println();
/*      */       
/* 3192 */       for (int j = 0; j < this.nTerms; j++) {
/* 3193 */         fout.printtab(this.paraName[j], this.field);
/* 3194 */         for (int i = 0; i < this.nTerms; i++) {
/* 3195 */           fout.printtab(Fmath.truncate(this.corrCoeff[i][j], this.prec), this.field);
/*      */         }
/* 3197 */         fout.println();
/*      */       }
/*      */     }
/*      */     
/* 3201 */     fout.println();
/* 3202 */     fout.println("End of file");
/*      */     
/* 3204 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void nonLinearPrint(FileOutput fout)
/*      */   {
/* 3210 */     if (this.legendCheck) {
/* 3211 */       fout.println();
/* 3212 */       fout.println("x1 = " + this.xLegend);
/* 3213 */       fout.println("y  = " + this.yLegend);
/*      */     }
/*      */     
/* 3216 */     fout.println();
/* 3217 */     if (!this.nlrStatus) {
/* 3218 */       fout.println("Convergence criterion was not satisfied");
/* 3219 */       fout.println("The following results are, or a derived from, the current estimates on exiting the regression method");
/* 3220 */       fout.println();
/*      */     }
/*      */     
/* 3223 */     fout.println("Estimated parameters");
/* 3224 */     fout.println("The statistics are obtained assuming that the model behaves as a linear model about the minimum.");
/* 3225 */     fout.println("The Hessian matrix is calculated as the numerically derived second derivatives of chi square with respect to all pairs of parameters.");
/* 3226 */     if (this.zeroCheck) fout.println("The best estimate/s equal to zero were replaced by the step size in the numerical differentiation!!!");
/* 3227 */     fout.println("Consequentlty treat the statistics with great caution");
/* 3228 */     if (!this.posVarFlag) {
/* 3229 */       fout.println("Covariance matrix contains at least one negative diagonal element");
/* 3230 */       fout.println(" - all variances are dubious");
/* 3231 */       fout.println(" - may not be at a minimum");
/*      */     }
/* 3233 */     if (!this.invertFlag) {
/* 3234 */       fout.println("Hessian matrix is singular");
/* 3235 */       fout.println(" - variances cannot be calculated");
/* 3236 */       fout.println(" - may not be at a minimum");
/*      */     }
/*      */     
/* 3239 */     fout.println(" ");
/* 3240 */     if (!this.scaleFlag) {
/* 3241 */       fout.println("The ordinate scaling factor [yscale, Ao] has been set equal to " + this.yScaleFactor);
/* 3242 */       fout.println(" ");
/*      */     }
/* 3244 */     if (this.lastMethod == 35) {
/* 3245 */       fout.println("The integer rate parameter, k, was varied in unit steps to obtain a minimum sum of squares");
/* 3246 */       fout.println("This value of k was " + this.kayValue);
/* 3247 */       fout.println(" ");
/*      */     }
/*      */     
/* 3250 */     fout.printtab(" ", this.field);
/* 3251 */     fout.printtab("Best", this.field);
/* 3252 */     if (this.invertFlag) {
/* 3253 */       fout.printtab("Estimate of", this.field);
/* 3254 */       fout.printtab("Coefficient", this.field);
/* 3255 */       fout.printtab("t-value", this.field);
/* 3256 */       fout.println("p-value");
/*      */     }
/*      */     
/* 3259 */     fout.printtab(" ", this.field);
/* 3260 */     fout.printtab("estimate", this.field);
/* 3261 */     if (this.invertFlag) {
/* 3262 */       fout.printtab("the error", this.field);
/* 3263 */       fout.printtab("of", this.field);
/* 3264 */       fout.printtab("t", this.field);
/* 3265 */       fout.println("P > |t|");
/*      */     }
/* 3267 */     if (this.invertFlag) {
/* 3268 */       fout.printtab(" ", this.field);
/* 3269 */       fout.printtab(" ", this.field);
/* 3270 */       fout.printtab(" ", this.field);
/* 3271 */       fout.println("variation (%)");
/*      */     }
/*      */     
/* 3274 */     if (this.lastMethod == 38) {
/* 3275 */       int nT = 3;
/* 3276 */       int ii = 0;
/* 3277 */       for (int i = 0; i < nT; i++) {
/* 3278 */         fout.printtab(this.paraName[i], this.field);
/* 3279 */         if (this.fixed[i] != 0) {
/* 3280 */           fout.printtab(this.values[i]);
/* 3281 */           fout.println(" fixed parameter");
/*      */         }
/*      */         else {
/* 3284 */           fout.printtab(Fmath.truncate(this.best[ii], this.prec), this.field);
/* 3285 */           if (this.invertFlag) {
/* 3286 */             fout.printtab(Fmath.truncate(this.bestSd[ii], this.prec), this.field);
/* 3287 */             fout.printtab(Fmath.truncate(Math.abs(this.bestSd[ii] * 100.0D / this.best[ii]), this.prec), this.field);
/* 3288 */             fout.printtab(Fmath.truncate(this.tValues[ii], this.prec), this.field);
/* 3289 */             fout.println(Fmath.truncate(this.pValues[ii], this.prec));
/*      */           }
/* 3291 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 3296 */       for (int i = 0; i < this.nTerms; i++) {
/* 3297 */         fout.printtab(this.paraName[i], this.field);
/* 3298 */         fout.printtab(Fmath.truncate(this.best[i], this.prec), this.field);
/* 3299 */         if (this.invertFlag) {
/* 3300 */           fout.printtab(Fmath.truncate(this.bestSd[i], this.prec), this.field);
/* 3301 */           fout.printtab(Fmath.truncate(Math.abs(this.bestSd[i] * 100.0D / this.best[i]), this.prec), this.field);
/* 3302 */           fout.printtab(Fmath.truncate(this.tValues[i], this.prec), this.field);
/* 3303 */           fout.println(Fmath.truncate(this.pValues[i], this.prec));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3308 */     fout.println();
/*      */     
/* 3310 */     fout.printtab(" ", this.field);
/* 3311 */     fout.printtab("Best", this.field);
/* 3312 */     fout.printtab("Pre-min", this.field);
/* 3313 */     fout.printtab("Post-min", this.field);
/* 3314 */     fout.printtab("Initial", this.field);
/* 3315 */     fout.println("Fractional");
/*      */     
/* 3317 */     fout.printtab(" ", this.field);
/* 3318 */     fout.printtab("estimate", this.field);
/* 3319 */     fout.printtab("gradient", this.field);
/* 3320 */     fout.printtab("gradient", this.field);
/* 3321 */     fout.printtab("estimate", this.field);
/* 3322 */     fout.println("step");
/*      */     
/*      */ 
/* 3325 */     if (this.lastMethod == 38) {
/* 3326 */       int nT = 3;
/* 3327 */       int ii = 0;
/* 3328 */       for (int i = 0; i < nT; i++) {
/* 3329 */         fout.printtab(this.paraName[i], this.field);
/* 3330 */         if (this.fixed[i] != 0) {
/* 3331 */           fout.printtab(this.values[i]);
/* 3332 */           fout.println(" fixed parameter");
/*      */         }
/*      */         else {
/* 3335 */           fout.printtab(Fmath.truncate(this.best[ii], this.prec), this.field);
/* 3336 */           fout.printtab(Fmath.truncate(this.grad[ii][0], this.prec), this.field);
/* 3337 */           fout.printtab(Fmath.truncate(this.grad[ii][1], this.prec), this.field);
/* 3338 */           fout.printtab(Fmath.truncate(this.startH[ii], this.prec), this.field);
/* 3339 */           fout.println(Fmath.truncate(this.step[ii], this.prec));
/* 3340 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 3345 */       for (int i = 0; i < this.nTerms; i++) {
/* 3346 */         fout.printtab(this.paraName[i], this.field);
/* 3347 */         fout.printtab(Fmath.truncate(this.best[i], this.prec), this.field);
/* 3348 */         fout.printtab(Fmath.truncate(this.grad[i][0], this.prec), this.field);
/* 3349 */         fout.printtab(Fmath.truncate(this.grad[i][1], this.prec), this.field);
/* 3350 */         fout.printtab(Fmath.truncate(this.startH[i], this.prec), this.field);
/* 3351 */         fout.println(Fmath.truncate(this.step[i], this.prec));
/*      */       }
/*      */     }
/* 3354 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 3358 */     ErrorProp ePeak = null;
/* 3359 */     ErrorProp eYscale = null;
/* 3360 */     if (this.scaleFlag) {
/* 3361 */       switch (this.lastMethod) {
/* 3362 */       case 4:  ErrorProp eSigma = new ErrorProp(this.best[1], this.bestSd[1]);
/* 3363 */         eYscale = new ErrorProp(this.best[2] / Math.sqrt(6.283185307179586D), this.bestSd[2] / Math.sqrt(6.283185307179586D));
/* 3364 */         ePeak = eYscale.over(eSigma);
/* 3365 */         fout.printsp("Calculated estimate of the peak value = ");
/* 3366 */         fout.println(ErrorProp.truncate(ePeak, this.prec));
/* 3367 */         break;
/* 3368 */       case 5:  ErrorProp eGamma = new ErrorProp(this.best[1], this.bestSd[1]);
/* 3369 */         eYscale = new ErrorProp(2.0D * this.best[2] / 3.141592653589793D, 2.0D * this.bestSd[2] / 3.141592653589793D);
/* 3370 */         ePeak = eYscale.over(eGamma);
/* 3371 */         fout.printsp("Calculated estimate of the peak value = ");
/* 3372 */         fout.println(ErrorProp.truncate(ePeak, this.prec));
/*      */       }
/*      */       
/*      */     }
/*      */     
/* 3377 */     if (this.lastMethod == 25) {
/* 3378 */       fout.printsp("Calculated estimate of the maximum gradient = ");
/* 3379 */       if (this.scaleFlag) {
/* 3380 */         fout.println(Fmath.truncate(this.best[0] * this.best[2] / 4.0D, this.prec));
/*      */       }
/*      */       else {
/* 3383 */         fout.println(Fmath.truncate(this.best[0] * this.yScaleFactor / 4.0D, this.prec));
/*      */       }
/*      */     }
/*      */     
/* 3387 */     if (this.lastMethod == 28) {
/* 3388 */       fout.printsp("Calculated estimate of the maximum gradient = ");
/* 3389 */       if (this.scaleFlag) {
/* 3390 */         fout.println(Fmath.truncate(this.best[1] * this.best[2] / (4.0D * this.best[0]), this.prec));
/*      */       }
/*      */       else {
/* 3393 */         fout.println(Fmath.truncate(this.best[1] * this.yScaleFactor / (4.0D * this.best[0]), this.prec));
/*      */       }
/* 3395 */       fout.printsp("Calculated estimate of the Ka, i.e. theta raised to the power n = ");
/* 3396 */       fout.println(Fmath.truncate(Math.pow(this.best[0], this.best[1]), this.prec));
/*      */     }
/* 3398 */     fout.println();
/*      */     
/* 3400 */     int kk = 0;
/* 3401 */     for (int j = 0; j < this.nYarrays; j++) {
/* 3402 */       if (this.multipleY) { fout.println("Y array " + j);
/*      */       }
/* 3404 */       for (int i = 0; i < this.nXarrays; i++) {
/* 3405 */         fout.printtab("x" + String.valueOf(i), this.field);
/*      */       }
/*      */       
/* 3408 */       fout.printtab("y(expl)", this.field);
/* 3409 */       fout.printtab("y(calc)", this.field);
/* 3410 */       fout.printtab("weight", this.field);
/* 3411 */       fout.printtab("residual", this.field);
/* 3412 */       fout.println("residual");
/*      */       
/* 3414 */       for (int i = 0; i < this.nXarrays; i++) {
/* 3415 */         fout.printtab(" ", this.field);
/*      */       }
/* 3417 */       fout.printtab(" ", this.field);
/* 3418 */       fout.printtab(" ", this.field);
/* 3419 */       fout.printtab(" ", this.field);
/* 3420 */       fout.printtab("(unweighted)", this.field);
/* 3421 */       fout.println("(weighted)");
/* 3422 */       for (int i = 0; i < this.nData0; i++) {
/* 3423 */         for (int jj = 0; jj < this.nXarrays; jj++) {
/* 3424 */           fout.printtab(Fmath.truncate(this.xData[jj][kk], this.prec), this.field);
/*      */         }
/* 3426 */         fout.printtab(Fmath.truncate(this.yData[kk], this.prec), this.field);
/* 3427 */         fout.printtab(Fmath.truncate(this.yCalc[kk], this.prec), this.field);
/* 3428 */         fout.printtab(Fmath.truncate(this.weight[kk], this.prec), this.field);
/* 3429 */         fout.printtab(Fmath.truncate(this.residual[kk], this.prec), this.field);
/* 3430 */         fout.println(Fmath.truncate(this.residualW[kk], this.prec));
/* 3431 */         kk++;
/*      */       }
/* 3433 */       fout.println();
/*      */     }
/*      */     
/* 3436 */     fout.printtab("Sum of squares of the unweighted residuals");
/* 3437 */     fout.println(Fmath.truncate(this.sumOfSquares, this.prec));
/* 3438 */     if (this.trueFreq) {
/* 3439 */       fout.printtab("Chi Square (Poissonian bins)");
/* 3440 */       fout.println(Fmath.truncate(this.chiSquare, this.prec));
/* 3441 */       fout.printtab("Reduced Chi Square (Poissonian bins)");
/* 3442 */       fout.println(Fmath.truncate(this.reducedChiSquare, this.prec));
/* 3443 */       fout.printtab("Chi Square (Poissonian bins) Probability");
/* 3444 */       fout.println(Fmath.truncate(1.0D - Stat.chiSquareProb(this.reducedChiSquare, this.degreesOfFreedom), this.prec));
/*      */ 
/*      */     }
/* 3447 */     else if (this.weightOpt) {
/* 3448 */       fout.printtab("Chi Square");
/* 3449 */       fout.println(Fmath.truncate(this.chiSquare, this.prec));
/* 3450 */       fout.printtab("Reduced Chi Square");
/* 3451 */       fout.println(Fmath.truncate(this.reducedChiSquare, this.prec));
/* 3452 */       fout.printtab("Chi Square Probability");
/* 3453 */       fout.println(Fmath.truncate(getchiSquareProb(), this.prec));
/*      */     }
/*      */     
/*      */ 
/* 3457 */     fout.println(" ");
/* 3458 */     fout.println("Correlation: x - y data");
/* 3459 */     if (this.nXarrays > 1) {
/* 3460 */       fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient (R)");
/* 3461 */       fout.println(Fmath.truncate(this.sampleR, this.prec));
/* 3462 */       fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient Squared (R^2)");
/* 3463 */       fout.println(Fmath.truncate(this.sampleR2, this.prec));
/* 3464 */       if (this.sampleR2 <= 1.0D) {
/* 3465 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient F-test ratio");
/* 3466 */         fout.println(Fmath.truncate(this.multipleF, this.prec));
/* 3467 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient F-test probability");
/* 3468 */         fout.println(Stat.fTestProb(this.multipleF, this.nXarrays - 1, this.nData - this.nXarrays));
/*      */       }
/* 3470 */       fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Sample Correlation Coefficient (adjR)");
/* 3471 */       fout.println(Fmath.truncate(this.adjustedR, this.prec));
/* 3472 */       fout.printtab(this.weightWord[this.weightFlag] + "Adjusted Multiple Sample Correlation Coefficient Squared (adjR*adjR)");
/* 3473 */       fout.println(Fmath.truncate(this.adjustedR2, this.prec));
/* 3474 */       if (this.sampleR2 <= 1.0D) {
/* 3475 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient F-test ratio");
/* 3476 */         fout.println(Fmath.truncate(this.adjustedF, this.prec));
/* 3477 */         fout.printtab(this.weightWord[this.weightFlag] + "Multiple Sample Correlation Coefficient F-test probability");
/* 3478 */         fout.println(Stat.fTestProb(this.adjustedF, this.nXarrays - 1, this.nData - this.nXarrays));
/*      */       }
/*      */     }
/*      */     else {
/* 3482 */       fout.printtab(this.weightWord[this.weightFlag] + "Sample Correlation Coefficient (R)");
/* 3483 */       fout.println(Fmath.truncate(this.sampleR, this.prec));
/* 3484 */       fout.printtab(this.weightWord[this.weightFlag] + "Sample Correlation Coefficient Squared (R^2)");
/* 3485 */       fout.println(Fmath.truncate(this.sampleR2, this.prec));
/*      */     }
/*      */     
/*      */ 
/* 3489 */     fout.println(" ");
/* 3490 */     fout.println("Correlation: y(experimental) - y(calculated)");
/* 3491 */     fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient");
/* 3492 */     double ccyy = Stat.corrCoeff(this.yData, this.yCalc, this.weight);
/* 3493 */     fout.println(Fmath.truncate(ccyy, this.prec));
/* 3494 */     fout.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
/* 3495 */     fout.println(Fmath.truncate(Stat.linearCorrCoeffProb(ccyy, this.nData - 1), this.prec));
/*      */     
/* 3497 */     fout.println(" ");
/* 3498 */     fout.printtab("Degrees of freedom");
/* 3499 */     fout.println(this.degreesOfFreedom);
/* 3500 */     fout.printtab("Number of data points");
/* 3501 */     fout.println(this.nData);
/* 3502 */     fout.printtab("Number of estimated paramaters");
/* 3503 */     fout.println(this.nTerms);
/*      */     
/* 3505 */     fout.println();
/*      */     
/* 3507 */     if ((this.posVarFlag) && (this.invertFlag) && (this.chiSquare != 0.0D)) {
/* 3508 */       fout.println("Parameter - parameter correlation coefficients");
/* 3509 */       fout.printtab(" ", this.field);
/* 3510 */       for (int i = 0; i < this.nTerms; i++) {
/* 3511 */         fout.printtab(this.paraName[i], this.field);
/*      */       }
/* 3513 */       fout.println();
/*      */       
/* 3515 */       for (int j = 0; j < this.nTerms; j++) {
/* 3516 */         fout.printtab(this.paraName[j], this.field);
/* 3517 */         for (int i = 0; i < this.nTerms; i++) {
/* 3518 */           fout.printtab(Fmath.truncate(this.corrCoeff[i][j], this.prec), this.field);
/*      */         }
/* 3520 */         fout.println();
/*      */       }
/* 3522 */       fout.println();
/*      */     }
/*      */     
/* 3525 */     fout.println();
/* 3526 */     fout.printtab("Number of iterations taken");
/* 3527 */     fout.println(this.nIter);
/* 3528 */     fout.printtab("Maximum number of iterations allowed");
/* 3529 */     fout.println(this.nMax);
/* 3530 */     fout.printtab("Number of restarts taken");
/* 3531 */     fout.println(this.kRestart);
/* 3532 */     fout.printtab("Maximum number of restarts allowed");
/* 3533 */     fout.println(this.konvge);
/* 3534 */     fout.printtab("Standard deviation of the simplex at the minimum");
/* 3535 */     fout.println(Fmath.truncate(this.simplexSd, this.prec));
/* 3536 */     fout.printtab("Convergence tolerance");
/* 3537 */     fout.println(this.fTol);
/* 3538 */     switch (this.minTest) {
/* 3539 */     case 0:  fout.println("simplex sd < the tolerance times the mean of the absolute values of the y values");
/* 3540 */       break;
/* 3541 */     case 1:  fout.println("simplex sd < the tolerance");
/* 3542 */       break;
/* 3543 */     case 2:  fout.println("simplex sd < the tolerance times the square root(sum of squares/degrees of freedom");
/*      */     }
/*      */     
/* 3546 */     fout.println("Step used in numerical differentiation to obtain Hessian matrix");
/* 3547 */     fout.println("d(parameter) = parameter*" + this.delta);
/*      */     
/* 3549 */     fout.println();
/* 3550 */     fout.println("End of file");
/* 3551 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotYY(String title)
/*      */   {
/* 3557 */     this.graphTitle = title;
/* 3558 */     int ncurves = 2;
/* 3559 */     int npoints = this.nData0;
/* 3560 */     double[][] data = PlotGraph.data(ncurves, npoints);
/*      */     
/* 3562 */     int kk = 0;
/* 3563 */     for (int jj = 0; jj < this.nYarrays; jj++)
/*      */     {
/*      */ 
/* 3566 */       for (int i = 0; i < this.nData0; i++) {
/* 3567 */         data[0][i] = this.yData[kk];
/* 3568 */         data[1][i] = this.yCalc[kk];
/* 3569 */         kk++;
/*      */       }
/*      */       
/*      */ 
/* 3573 */       String title0 = setGandPtitle(this.graphTitle);
/* 3574 */       if (this.multipleY) title0 = title0 + "y array " + jj;
/* 3575 */       String title1 = "Calculated versus experimental y values";
/*      */       
/*      */ 
/* 3578 */       Regression yyRegr = new Regression(this.yData, this.yCalc, this.weight);
/* 3579 */       yyRegr.linear();
/* 3580 */       double[] coef = yyRegr.getCoeff();
/* 3581 */       data[2][0] = Fmath.minimum(this.yData);
/* 3582 */       data[3][0] = (coef[0] + coef[1] * data[2][0]);
/* 3583 */       data[2][1] = Fmath.maximum(this.yData);
/* 3584 */       data[3][1] = (coef[0] + coef[1] * data[2][1]);
/*      */       
/* 3586 */       PlotGraph pg = new PlotGraph(data);
/*      */       
/* 3588 */       pg.setGraphTitle(title0);
/* 3589 */       pg.setGraphTitle2(title1);
/* 3590 */       pg.setXaxisLegend("Experimental y value");
/* 3591 */       pg.setYaxisLegend("Calculated y value");
/* 3592 */       int[] popt = { 1, 0 };
/* 3593 */       pg.setPoint(popt);
/* 3594 */       int[] lopt = { 0, 3 };
/* 3595 */       pg.setLine(lopt);
/*      */       
/* 3597 */       pg.plot();
/*      */     }
/*      */   }
/*      */   
/*      */   protected String setGandPtitle(String title)
/*      */   {
/* 3603 */     String title1 = "";
/* 3604 */     switch (this.lastMethod) {
/* 3605 */     case 0:  title1 = "Linear regression (with intercept): " + title;
/* 3606 */       break;
/* 3607 */     case 1:  title1 = "Linear(polynomial with degree = " + (this.nTerms - 1) + ") regression: " + title;
/* 3608 */       break;
/* 3609 */     case 2:  title1 = "General linear regression: " + title;
/* 3610 */       break;
/* 3611 */     case 3:  title1 = "Non-linear (simplex) regression: " + title;
/* 3612 */       break;
/* 3613 */     case 4:  title1 = "Fit to a Gaussian distribution: " + title;
/* 3614 */       break;
/* 3615 */     case 5:  title1 = "Fit to a Lorentzian distribution: " + title;
/* 3616 */       break;
/* 3617 */     case 6:  title1 = "Fit to a Poisson distribution: " + title;
/* 3618 */       break;
/* 3619 */     case 7:  title1 = "Fit to a Two Parameter Minimum Order Statistic Gumbel distribution: " + title;
/* 3620 */       break;
/* 3621 */     case 8:  title1 = "Fit to a two Parameter Maximum Order Statistic Gumbel distribution: " + title;
/* 3622 */       break;
/* 3623 */     case 9:  title1 = "Fit to a One Parameter Minimum Order Statistic Gumbel distribution: " + title;
/* 3624 */       break;
/* 3625 */     case 10:  title1 = "Fit to a One Parameter Maximum Order Statistic Gumbel distribution: " + title;
/* 3626 */       break;
/* 3627 */     case 11:  title1 = "Fit to a Standard Minimum Order Statistic Gumbel distribution: " + title;
/* 3628 */       break;
/* 3629 */     case 12:  title1 = "Fit to a Standard Maximum Order Statistic Gumbel distribution: " + title;
/* 3630 */       break;
/* 3631 */     case 13:  title1 = "Fit to a Three Parameter Frechet distribution: " + title;
/* 3632 */       break;
/* 3633 */     case 14:  title1 = "Fit to a Two Parameter Frechet distribution: " + title;
/* 3634 */       break;
/* 3635 */     case 15:  title1 = "Fit to a Standard Frechet distribution: " + title;
/* 3636 */       break;
/* 3637 */     case 16:  title1 = "Fit to a Three Parameter Weibull distribution: " + title;
/* 3638 */       break;
/* 3639 */     case 17:  title1 = "Fit to a Two Parameter Weibull distribution: " + title;
/* 3640 */       break;
/* 3641 */     case 18:  title1 = "Fit to a Standard Weibull distribution: " + title;
/* 3642 */       break;
/* 3643 */     case 19:  title1 = "Fit to a Two Parameter Exponential distribution: " + title;
/* 3644 */       break;
/* 3645 */     case 20:  title1 = "Fit to a One Parameter Exponential distribution: " + title;
/* 3646 */       break;
/* 3647 */     case 21:  title1 = "Fit to a Standard exponential distribution: " + title;
/* 3648 */       break;
/* 3649 */     case 22:  title1 = "Fit to a Rayleigh distribution: " + title;
/* 3650 */       break;
/* 3651 */     case 23:  title1 = "Fit to a Two Parameter Pareto distribution: " + title;
/* 3652 */       break;
/* 3653 */     case 24:  title1 = "Fit to a One Parameter Pareto distribution: " + title;
/* 3654 */       break;
/* 3655 */     case 25:  title1 = "Fit to a Sigmoid Threshold Function: " + title;
/* 3656 */       break;
/* 3657 */     case 26:  title1 = "Fit to a Rectangular Hyperbola: " + title;
/* 3658 */       break;
/* 3659 */     case 27:  title1 = "Fit to a Scaled Heaviside Step Function: " + title;
/* 3660 */       break;
/* 3661 */     case 28:  title1 = "Fit to a Hill/Sips Sigmoid: " + title;
/* 3662 */       break;
/* 3663 */     case 29:  title1 = "Fit to a Shifted Pareto distribution: " + title;
/* 3664 */       break;
/* 3665 */     case 30:  title1 = "Fit to a Logistic distribution: " + title;
/* 3666 */       break;
/* 3667 */     case 31:  title1 = "Fit to a Beta distribution - interval [0, 1]: " + title;
/* 3668 */       break;
/* 3669 */     case 32:  title1 = "Fit to a Beta distribution - interval [min, max]: " + title;
/* 3670 */       break;
/* 3671 */     case 33:  title1 = "Fit to a Three Parameter Gamma distribution]: " + title;
/* 3672 */       break;
/* 3673 */     case 34:  title1 = "Fit to a Standard Gamma distribution]: " + title;
/* 3674 */       break;
/* 3675 */     case 35:  title1 = "Fit to an Erlang distribution]: " + title;
/* 3676 */       break;
/* 3677 */     case 36:  title1 = "Fit to an two parameter log-normal distribution]: " + title;
/* 3678 */       break;
/* 3679 */     case 37:  title1 = "Fit to an three parameter log-normal distribution]: " + title;
/* 3680 */       break;
/* 3681 */     case 38:  title1 = "Fit to a Gaussian distribution with fixed parameters: " + title;
/* 3682 */       break;
/* 3683 */     case 39:  title1 = "Fit to a EC50 dose response curve: " + title;
/* 3684 */       break;
/* 3685 */     case 40:  title1 = "Fit to a LogEC50 dose response curve: " + title;
/* 3686 */       break;
/* 3687 */     case 41:  title1 = "Fit to a EC50 dose response curve - bottom constrained [>= 0]: " + title;
/* 3688 */       break;
/* 3689 */     case 42:  title1 = "Fit to a LogEC50 dose response curve - bottom constrained [>= 0]: " + title;
/* 3690 */       break;
/* 3691 */     default:  title1 = " " + title;
/*      */     }
/* 3693 */     return title1;
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotYY()
/*      */   {
/* 3699 */     plotYY(this.graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int plotXY(String title)
/*      */   {
/* 3706 */     this.graphTitle = title;
/* 3707 */     int flag = 0;
/* 3708 */     if ((!this.linNonLin) && (this.nTerms > 0)) {
/* 3709 */       System.out.println("You attempted to use Regression.plotXY() for a non-linear regression without providing the function reference (pointer) in the plotXY argument list");
/* 3710 */       System.out.println("No plot attempted");
/* 3711 */       flag = -1;
/* 3712 */       return flag;
/*      */     }
/* 3714 */     flag = plotXYlinear(title);
/* 3715 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int plotXY()
/*      */   {
/* 3722 */     int flag = plotXY(this.graphTitle);
/* 3723 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int plotXY(RegressionFunction g, String title)
/*      */   {
/* 3731 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y array\nplotXY2 should have been called");
/* 3732 */     Object regFun = g;
/* 3733 */     int flag = plotXYnonlinear(regFun, title);
/* 3734 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int plotXY2(RegressionFunction2 g, String title)
/*      */   {
/* 3742 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nsimplex should have been called");
/* 3743 */     this.graphTitle = title;
/* 3744 */     Object regFun = g;
/* 3745 */     int flag = plotXYnonlinear(regFun, title);
/* 3746 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int plotXY(RegressionFunction g)
/*      */   {
/* 3754 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y array\nplotXY2 should have been called");
/* 3755 */     Object regFun = g;
/* 3756 */     int flag = plotXYnonlinear(regFun, this.graphTitle);
/* 3757 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int plotXY2(RegressionFunction2 g)
/*      */   {
/* 3765 */     if (!this.multipleY) throw new IllegalArgumentException("This method cannot handle singly dimensioned y array\nplotXY should have been called");
/* 3766 */     Object regFun = g;
/* 3767 */     int flag = plotXYnonlinear(regFun, this.graphTitle);
/* 3768 */     return flag;
/*      */   }
/*      */   
/*      */   public void addLegends()
/*      */   {
/* 3773 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to add your own legends to the x and y axes", "Axis Legends", 0, 3);
/* 3774 */     if (ans == 0) {
/* 3775 */       this.xLegend = JOptionPane.showInputDialog("Type the legend for the abscissae (x-axis) [first data set]");
/* 3776 */       this.yLegend = JOptionPane.showInputDialog("Type the legend for the ordinates (y-axis) [second data set]");
/* 3777 */       this.legendCheck = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int plotXYlinear(String title)
/*      */   {
/* 3785 */     this.graphTitle = title;
/* 3786 */     int flag = 0;
/* 3787 */     if (this.nXarrays > 1) {
/* 3788 */       System.out.println("You attempted to use Regression.plotXY() for a multiple regression");
/* 3789 */       System.out.println("No plot attempted");
/* 3790 */       flag = -2;
/* 3791 */       return flag;
/*      */     }
/*      */     
/* 3794 */     int ncurves = 2;
/* 3795 */     int npoints = 200;
/* 3796 */     if (npoints < this.nData0) npoints = this.nData0;
/* 3797 */     if ((this.lastMethod == 11) || (this.lastMethod == 12) || (this.lastMethod == 21)) npoints = this.nData0;
/* 3798 */     double[][] data = PlotGraph.data(ncurves, npoints);
/* 3799 */     double xmin = Fmath.minimum(this.xData[0]);
/* 3800 */     double xmax = Fmath.maximum(this.xData[0]);
/* 3801 */     double inc = (xmax - xmin) / (npoints - 1);
/* 3802 */     String title1 = " ";
/* 3803 */     String title2 = " ";
/*      */     
/* 3805 */     for (int i = 0; i < this.nData0; i++) {
/* 3806 */       data[0][i] = this.xData[0][i];
/* 3807 */       data[1][i] = this.yData[i];
/*      */     }
/*      */     
/* 3810 */     data[2][0] = xmin;
/* 3811 */     for (int i = 1; i < npoints; i++) data[2][i] = (data[2][(i - 1)] + inc);
/* 3812 */     if (this.nTerms == 0) {
/* 3813 */       switch (this.lastMethod) {
/* 3814 */       case 11:  title1 = "No regression: Minimum Order Statistic Standard Gumbel (y = exp(x)exp(-exp(x))): " + this.graphTitle;
/* 3815 */         title2 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
/* 3816 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3817 */         for (int i = 0; i < npoints; i++) data[3][i] = this.yCalc[i];
/* 3818 */         break;
/* 3819 */       case 12:  title1 = "No regression:  Maximum Order Statistic Standard Gumbel (y = exp(-x)exp(-exp(-x))): " + this.graphTitle;
/* 3820 */         title2 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
/* 3821 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3822 */         for (int i = 0; i < npoints; i++) data[3][i] = this.yCalc[i];
/* 3823 */         break;
/* 3824 */       case 21:  title1 = "No regression:  Standard Exponential (y = exp(-x)): " + this.graphTitle;
/* 3825 */         title2 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
/* 3826 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3827 */         for (int i = 0; i < npoints; i++) { data[3][i] = this.yCalc[i];
/*      */         }
/*      */       
/*      */       }
/*      */       
/*      */     } else {
/* 3833 */       switch (this.lastMethod) {
/* 3834 */       case 0:  title1 = "Linear regression  (y = a + b.x): " + this.graphTitle;
/* 3835 */         title2 = " points - experimental values;   line - best fit curve";
/* 3836 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3837 */         for (int i = 0; i < npoints; i++) data[3][i] = (this.best[0] + this.best[1] * data[2][i]);
/* 3838 */         break;
/* 3839 */       case 1:  title1 = "Linear (polynomial with degree = " + (this.nTerms - 1) + ") regression: " + this.graphTitle;
/* 3840 */         title2 = " points - experimental values;   line - best fit curve";
/* 3841 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3842 */         for (int i = 0; i < npoints; i++) {
/* 3843 */           double sum = this.best[0];
/* 3844 */           for (int j = 1; j < this.nTerms; j++) sum += this.best[j] * Math.pow(data[2][i], j);
/* 3845 */           data[3][i] = sum;
/*      */         }
/* 3847 */         break;
/* 3848 */       case 2:  title1 = "Linear regression  (y = a.x): " + this.graphTitle;
/* 3849 */         title2 = " points - experimental values;   line - best fit curve";
/* 3850 */         if (this.nXarrays == 1) {
/* 3851 */           if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3852 */           for (int i = 0; i < npoints; i++) data[3][i] = (this.best[0] * data[2][i]);
/*      */         }
/*      */         else {
/* 3855 */           System.out.println("Regression.plotXY(linear): lastMethod, " + this.lastMethod + ",cannot be plotted in two dimensions");
/* 3856 */           System.out.println("No plot attempted");
/* 3857 */           flag = -1;
/*      */         }
/* 3859 */         break;
/* 3860 */       case 11:  title1 = "Linear regression: Minimum Order Statistic Standard Gumbel (y = a.z where z = exp(x)exp(-exp(x))): " + this.graphTitle;
/* 3861 */         title2 = " points - experimental values;   line - best fit curve";
/* 3862 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3863 */         for (int i = 0; i < npoints; i++) data[3][i] = (this.best[0] * Math.exp(data[2][i]) * Math.exp(-Math.exp(data[2][i])));
/* 3864 */         break;
/* 3865 */       case 12:  title1 = "Linear regression:  Maximum Order Statistic Standard Gumbel (y = a.z where z=exp(-x)exp(-exp(-x))): " + this.graphTitle;
/* 3866 */         title2 = " points - experimental values;   line - best fit curve";
/* 3867 */         if (this.weightOpt) title2 = title2 + ";   error bars - weighting factors";
/* 3868 */         for (int i = 0; i < npoints; i++) data[3][i] = (this.best[0] * Math.exp(-data[2][i]) * Math.exp(-Math.exp(-data[2][i])));
/* 3869 */         break;
/* 3870 */       case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: default:  System.out.println("Regression.plotXY(linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
/* 3871 */         System.out.println("No plot attempted");
/* 3872 */         flag = -1;
/* 3873 */         return flag;
/*      */       }
/*      */       
/*      */     }
/* 3877 */     PlotGraph pg = new PlotGraph(data);
/*      */     
/* 3879 */     pg.setGraphTitle(title1);
/* 3880 */     pg.setGraphTitle2(title2);
/* 3881 */     pg.setXaxisLegend(this.xLegend);
/* 3882 */     pg.setYaxisLegend(this.yLegend);
/* 3883 */     int[] popt = { 1, 0 };
/* 3884 */     pg.setPoint(popt);
/* 3885 */     int[] lopt = { 0, 3 };
/* 3886 */     pg.setLine(lopt);
/* 3887 */     if (this.weightOpt) pg.setErrorBars(0, this.weight);
/* 3888 */     pg.plot();
/*      */     
/* 3890 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int plotXYnonlinear(Object regFun, String title)
/*      */   {
/* 3897 */     this.graphTitle = title;
/* 3898 */     RegressionFunction g1 = null;
/* 3899 */     RegressionFunction2 g2 = null;
/* 3900 */     if (this.multipleY) {
/* 3901 */       g2 = (RegressionFunction2)regFun;
/*      */     }
/*      */     else {
/* 3904 */       g1 = (RegressionFunction)regFun;
/*      */     }
/*      */     
/* 3907 */     int flag = 0;
/*      */     
/* 3909 */     if (this.lastMethod < 3) {
/* 3910 */       System.out.println("Regression.plotXY(non-linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
/* 3911 */       System.out.println("No plot attempted");
/* 3912 */       flag = -1;
/* 3913 */       return flag;
/*      */     }
/*      */     
/* 3916 */     if (this.nXarrays > 1) {
/* 3917 */       System.out.println("Multiple Linear Regression with more than one independent variable cannot be plotted in two dimensions");
/* 3918 */       System.out.println("plotYY() called instead of plotXY()");
/* 3919 */       plotYY(title);
/* 3920 */       flag = -2;
/*      */ 
/*      */     }
/* 3923 */     else if (this.multipleY) {
/* 3924 */       int ncurves = 2;
/* 3925 */       int npoints = 200;
/* 3926 */       if (npoints < this.nData0) { npoints = this.nData0;
/*      */       }
/* 3928 */       int kk = 0;
/* 3929 */       double[] wWeight = new double[this.nData0];
/* 3930 */       for (int jj = 0; jj < this.nYarrays; jj++) {
/* 3931 */         double[][] data = PlotGraph.data(ncurves, npoints);
/* 3932 */         for (int i = 0; i < this.nData0; i++) {
/* 3933 */           data[0][i] = this.xData[0][kk];
/* 3934 */           data[1][i] = this.yData[kk];
/* 3935 */           wWeight[i] = this.weight[kk];
/* 3936 */           kk++;
/*      */         }
/* 3938 */         double xmin = Fmath.minimum(this.xData[0]);
/* 3939 */         double xmax = Fmath.maximum(this.xData[0]);
/* 3940 */         double inc = (xmax - xmin) / (npoints - 1);
/* 3941 */         data[2][0] = xmin;
/* 3942 */         for (int i = 1; i < npoints; i++) data[2][i] = (data[2][(i - 1)] + inc);
/* 3943 */         double[] xd = new double[this.nXarrays];
/* 3944 */         for (int i = 0; i < npoints; i++) {
/* 3945 */           xd[0] = data[2][i];
/* 3946 */           data[3][i] = g2.function(this.best, xd, jj * this.nData0);
/*      */         }
/*      */         
/*      */ 
/* 3950 */         String title1 = setGandPtitle(title);
/* 3951 */         String title2 = " points - experimental values;   line - best fit curve;  y data array " + jj;
/* 3952 */         if (this.weightOpt) { title2 = title2 + ";   error bars - weighting factors";
/*      */         }
/* 3954 */         PlotGraph pg = new PlotGraph(data);
/*      */         
/* 3956 */         pg.setGraphTitle(title1);
/* 3957 */         pg.setGraphTitle2(title2);
/* 3958 */         pg.setXaxisLegend(this.xLegend);
/* 3959 */         pg.setYaxisLegend(this.yLegend);
/* 3960 */         int[] popt = { 1, 0 };
/* 3961 */         pg.setPoint(popt);
/* 3962 */         int[] lopt = { 0, 3 };
/* 3963 */         pg.setLine(lopt);
/* 3964 */         if (this.weightOpt) { pg.setErrorBars(0, wWeight);
/*      */         }
/* 3966 */         pg.plot();
/*      */       }
/*      */     }
/*      */     else {
/* 3970 */       int ncurves = 2;
/* 3971 */       int npoints = 200;
/* 3972 */       if (npoints < this.nData0) npoints = this.nData0;
/* 3973 */       if (this.lastMethod == 6) { npoints = this.nData0;
/*      */       }
/* 3975 */       double[][] data = PlotGraph.data(ncurves, npoints);
/* 3976 */       for (int i = 0; i < this.nData0; i++) {
/* 3977 */         data[0][i] = this.xData[0][i];
/* 3978 */         data[1][i] = this.yData[i];
/*      */       }
/* 3980 */       if (this.lastMethod == 6) {
/* 3981 */         double[] xd = new double[this.nXarrays];
/* 3982 */         for (int i = 0; i < npoints; i++) {
/* 3983 */           data[2][i] = data[0][i];
/* 3984 */           xd[0] = data[2][i];
/* 3985 */           data[3][i] = g1.function(this.best, xd);
/*      */         }
/*      */       }
/*      */       else {
/* 3989 */         double xmin = Fmath.minimum(this.xData[0]);
/* 3990 */         double xmax = Fmath.maximum(this.xData[0]);
/* 3991 */         double inc = (xmax - xmin) / (npoints - 1);
/* 3992 */         data[2][0] = xmin;
/* 3993 */         for (int i = 1; i < npoints; i++) data[2][i] = (data[2][(i - 1)] + inc);
/* 3994 */         double[] xd = new double[this.nXarrays];
/* 3995 */         for (int i = 0; i < npoints; i++) {
/* 3996 */           xd[0] = data[2][i];
/* 3997 */           data[3][i] = g1.function(this.best, xd);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 4002 */       String title1 = setGandPtitle(title);
/* 4003 */       String title2 = " points - experimental values;   line - best fit curve";
/* 4004 */       if (this.weightOpt) { title2 = title2 + ";   error bars - weighting factors";
/*      */       }
/* 4006 */       PlotGraph pg = new PlotGraph(data);
/*      */       
/* 4008 */       pg.setGraphTitle(title1);
/* 4009 */       pg.setGraphTitle2(title2);
/* 4010 */       pg.setXaxisLegend(this.xLegend);
/* 4011 */       pg.setYaxisLegend(this.yLegend);
/* 4012 */       int[] popt = { 1, 0 };
/* 4013 */       pg.setPoint(popt);
/* 4014 */       int[] lopt = { 0, 3 };
/* 4015 */       pg.setLine(lopt);
/*      */       
/* 4017 */       if (this.weightOpt) { pg.setErrorBars(0, this.weight);
/*      */       }
/* 4019 */       pg.plot();
/*      */     }
/*      */     
/* 4022 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int plotXYfixed(Object regFun, String title)
/*      */   {
/* 4029 */     this.graphTitle = title;
/* 4030 */     RegressionFunction g1 = null;
/* 4031 */     RegressionFunction2 g2 = null;
/* 4032 */     if (this.multipleY) {
/* 4033 */       g2 = (RegressionFunction2)regFun;
/*      */     }
/*      */     else {
/* 4036 */       g1 = (RegressionFunction)regFun;
/*      */     }
/*      */     
/* 4039 */     int flag = 0;
/*      */     
/* 4041 */     if (this.lastMethod < 3) {
/* 4042 */       System.out.println("Regression.plotXY(non-linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
/* 4043 */       System.out.println("No plot attempted");
/* 4044 */       flag = -1;
/* 4045 */       return flag;
/*      */     }
/*      */     
/*      */ 
/* 4049 */     if (this.nXarrays > 1) {
/* 4050 */       System.out.println("Multiple Linear Regression with more than one independent variable cannot be plotted in two dimensions");
/* 4051 */       System.out.println("plotYY() called instead of plotXY()");
/* 4052 */       plotYY(title);
/* 4053 */       flag = -2;
/*      */ 
/*      */     }
/* 4056 */     else if (this.multipleY) {
/* 4057 */       int ncurves = 2;
/* 4058 */       int npoints = 200;
/* 4059 */       if (npoints < this.nData0) { npoints = this.nData0;
/*      */       }
/* 4061 */       int kk = 0;
/* 4062 */       double[] wWeight = new double[this.nData0];
/* 4063 */       for (int jj = 0; jj < this.nYarrays; jj++) {
/* 4064 */         double[][] data = PlotGraph.data(ncurves, npoints);
/* 4065 */         for (int i = 0; i < this.nData0; i++) {
/* 4066 */           data[0][i] = this.xData[0][kk];
/* 4067 */           data[1][i] = this.yData[kk];
/* 4068 */           wWeight[i] = this.weight[kk];
/* 4069 */           kk++;
/*      */         }
/* 4071 */         double xmin = Fmath.minimum(this.xData[0]);
/* 4072 */         double xmax = Fmath.maximum(this.xData[0]);
/* 4073 */         double inc = (xmax - xmin) / (npoints - 1);
/* 4074 */         data[2][0] = xmin;
/* 4075 */         for (int i = 1; i < npoints; i++) data[2][i] = (data[2][(i - 1)] + inc);
/* 4076 */         double[] xd = new double[this.nXarrays];
/* 4077 */         for (int i = 0; i < npoints; i++) {
/* 4078 */           xd[0] = data[2][i];
/* 4079 */           data[3][i] = g2.function(this.values, xd, jj * this.nData0);
/*      */         }
/*      */         
/*      */ 
/* 4083 */         String title1 = setGandPtitle(title);
/* 4084 */         String title2 = " points - experimental values;   line - best fit curve;  y data array " + jj;
/* 4085 */         if (this.weightOpt) { title2 = title2 + ";   error bars - weighting factors";
/*      */         }
/* 4087 */         PlotGraph pg = new PlotGraph(data);
/*      */         
/* 4089 */         pg.setGraphTitle(title1);
/* 4090 */         pg.setGraphTitle2(title2);
/* 4091 */         pg.setXaxisLegend(this.xLegend);
/* 4092 */         pg.setYaxisLegend(this.yLegend);
/* 4093 */         int[] popt = { 1, 0 };
/* 4094 */         pg.setPoint(popt);
/* 4095 */         int[] lopt = { 0, 3 };
/* 4096 */         pg.setLine(lopt);
/* 4097 */         if (this.weightOpt) { pg.setErrorBars(0, wWeight);
/*      */         }
/* 4099 */         pg.plot();
/*      */       }
/*      */     }
/*      */     else {
/* 4103 */       int ncurves = 2;
/* 4104 */       int npoints = 200;
/* 4105 */       if (npoints < this.nData0) npoints = this.nData0;
/* 4106 */       if (this.lastMethod == 6) { npoints = this.nData0;
/*      */       }
/* 4108 */       double[][] data = PlotGraph.data(ncurves, npoints);
/* 4109 */       for (int i = 0; i < this.nData0; i++) {
/* 4110 */         data[0][i] = this.xData[0][i];
/* 4111 */         data[1][i] = this.yData[i];
/*      */       }
/* 4113 */       if (this.lastMethod == 6) {
/* 4114 */         double[] xd = new double[this.nXarrays];
/* 4115 */         for (int i = 0; i < npoints; i++) {
/* 4116 */           data[2][i] = data[0][i];
/* 4117 */           xd[0] = data[2][i];
/* 4118 */           data[3][i] = g1.function(this.values, xd);
/*      */         }
/*      */       }
/*      */       else {
/* 4122 */         double xmin = Fmath.minimum(this.xData[0]);
/* 4123 */         double xmax = Fmath.maximum(this.xData[0]);
/* 4124 */         double inc = (xmax - xmin) / (npoints - 1);
/* 4125 */         data[2][0] = xmin;
/* 4126 */         for (int i = 1; i < npoints; i++) data[2][i] = (data[2][(i - 1)] + inc);
/* 4127 */         double[] xd = new double[this.nXarrays];
/* 4128 */         for (int i = 0; i < npoints; i++) {
/* 4129 */           xd[0] = data[2][i];
/* 4130 */           data[3][i] = g1.function(this.values, xd);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 4135 */       String title1 = setGandPtitle(title);
/* 4136 */       String title2 = " points - experimental values;   line - best fit curve";
/* 4137 */       if (this.weightOpt) { title2 = title2 + ";   error bars - weighting factors";
/*      */       }
/* 4139 */       PlotGraph pg = new PlotGraph(data);
/*      */       
/* 4141 */       pg.setGraphTitle(title1);
/* 4142 */       pg.setGraphTitle2(title2);
/* 4143 */       pg.setXaxisLegend(this.xLegend);
/* 4144 */       pg.setYaxisLegend(this.yLegend);
/* 4145 */       int[] popt = { 1, 0 };
/* 4146 */       pg.setPoint(popt);
/* 4147 */       int[] lopt = { 0, 3 };
/* 4148 */       pg.setLine(lopt);
/*      */       
/* 4150 */       if (this.weightOpt) { pg.setErrorBars(0, this.weight);
/*      */       }
/* 4152 */       pg.plot();
/*      */     }
/*      */     
/* 4155 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getNlrStatus()
/*      */   {
/* 4164 */     return this.nlrStatus;
/*      */   }
/*      */   
/*      */   public void setScale(int n)
/*      */   {
/* 4169 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("The argument must be 0 (no scaling) 1(initial estimates all scaled to unity) or the array of scaling factors");
/* 4170 */     this.scaleOpt = n;
/*      */   }
/*      */   
/*      */   public void setScale(double[] sc)
/*      */   {
/* 4175 */     this.scale = sc;
/* 4176 */     this.scaleOpt = 2;
/*      */   }
/*      */   
/*      */   public double[] getScale()
/*      */   {
/* 4181 */     return this.scale;
/*      */   }
/*      */   
/*      */   public void setMinTest(int n)
/*      */   {
/* 4186 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("minTest must be 0 or 1");
/* 4187 */     this.minTest = n;
/*      */   }
/*      */   
/*      */   public int getMinTest()
/*      */   {
/* 4192 */     return this.minTest;
/*      */   }
/*      */   
/*      */   public double getSimplexSd()
/*      */   {
/* 4197 */     return this.simplexSd;
/*      */   }
/*      */   
/*      */   public double[] getBestEstimates()
/*      */   {
/* 4202 */     return (double[])this.best.clone();
/*      */   }
/*      */   
/*      */   public double[] getCoeff()
/*      */   {
/* 4207 */     return (double[])this.best.clone();
/*      */   }
/*      */   
/*      */   public double[] getbestestimatesStandardDeviations()
/*      */   {
/* 4212 */     return (double[])this.bestSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getBestEstimatesStandardDeviations()
/*      */   {
/* 4217 */     return (double[])this.bestSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getCoeffSd()
/*      */   {
/* 4222 */     return (double[])this.bestSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getBestEstimatesErrors()
/*      */   {
/* 4227 */     return (double[])this.bestSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getCoeffVar()
/*      */   {
/* 4232 */     double[] coeffVar = new double[this.nTerms];
/*      */     
/* 4234 */     for (int i = 0; i < this.nTerms; i++) {
/* 4235 */       coeffVar[i] = (this.bestSd[i] * 100.0D / this.best[i]);
/*      */     }
/* 4237 */     return coeffVar;
/*      */   }
/*      */   
/*      */   public double[] getPseudoSd()
/*      */   {
/* 4242 */     return (double[])this.pseudoSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getPseudoErrors()
/*      */   {
/* 4247 */     return (double[])this.pseudoSd.clone();
/*      */   }
/*      */   
/*      */   public double[] getTvalues()
/*      */   {
/* 4252 */     return (double[])this.tValues.clone();
/*      */   }
/*      */   
/*      */   public double[] getPvalues()
/*      */   {
/* 4257 */     return (double[])this.pValues.clone();
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getXdata()
/*      */   {
/* 4263 */     return (double[][])this.xData.clone();
/*      */   }
/*      */   
/*      */   public double[] getYdata()
/*      */   {
/* 4268 */     return (double[])this.yData.clone();
/*      */   }
/*      */   
/*      */   public double[] getYcalc()
/*      */   {
/* 4273 */     double[] temp = new double[this.nData];
/* 4274 */     for (int i = 0; i < this.nData; i++) temp[i] = this.yCalc[i];
/* 4275 */     return temp;
/*      */   }
/*      */   
/*      */   public double[] getResiduals()
/*      */   {
/* 4280 */     double[] temp = new double[this.nData];
/* 4281 */     for (int i = 0; i < this.nData; i++) temp[i] = (this.yData[i] - this.yCalc[i]);
/* 4282 */     return temp;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] getWeightedResiduals()
/*      */   {
/* 4288 */     double[] temp = new double[this.nData];
/* 4289 */     for (int i = 0; i < this.nData; i++) temp[i] = ((this.yData[i] - this.yCalc[i]) / this.weight[i]);
/* 4290 */     return temp;
/*      */   }
/*      */   
/*      */   public double getSumOfSquares()
/*      */   {
/* 4295 */     return this.sumOfSquares;
/*      */   }
/*      */   
/*      */   public double getChiSquare()
/*      */   {
/* 4300 */     double ret = 0.0D;
/* 4301 */     if (this.weightOpt) {
/* 4302 */       ret = this.chiSquare;
/*      */     }
/*      */     else {
/* 4305 */       System.out.println("Chi Square cannot be calculated as data are neither true frequencies nor weighted");
/* 4306 */       System.out.println("A value of -1 is returned as Chi Square");
/* 4307 */       ret = -1.0D;
/*      */     }
/* 4309 */     return ret;
/*      */   }
/*      */   
/*      */   public double getReducedChiSquare()
/*      */   {
/* 4314 */     double ret = 0.0D;
/* 4315 */     if (this.weightOpt) {
/* 4316 */       ret = this.reducedChiSquare;
/*      */     }
/*      */     else {
/* 4319 */       System.out.println("A Reduced Chi Square cannot be calculated as data are neither true frequencies nor weighted");
/* 4320 */       System.out.println("A value of -1 is returned as Reduced Chi Square");
/* 4321 */       ret = -1.0D;
/*      */     }
/* 4323 */     return ret;
/*      */   }
/*      */   
/*      */   public double getchiSquareProb()
/*      */   {
/* 4328 */     double ret = 0.0D;
/* 4329 */     if (this.weightOpt) {
/* 4330 */       ret = 1.0D - Stat.chiSquareProb(this.chiSquare, this.nData - this.nXarrays);
/*      */     }
/*      */     else {
/* 4333 */       System.out.println("A Chi Square probablity cannot be calculated as data are neither true frequencies nor weighted");
/* 4334 */       System.out.println("A value of -1 is returned as Reduced Chi Square");
/* 4335 */       ret = -1.0D;
/*      */     }
/* 4337 */     return ret;
/*      */   }
/*      */   
/*      */   public double[][] getCovMatrix()
/*      */   {
/* 4342 */     return this.covar;
/*      */   }
/*      */   
/*      */   public double[][] getCorrCoeffMatrix()
/*      */   {
/* 4347 */     return this.corrCoeff;
/*      */   }
/*      */   
/*      */   public int getNiter()
/*      */   {
/* 4352 */     return this.nIter;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNmax(int nmax)
/*      */   {
/* 4358 */     this.nMax = nmax;
/*      */   }
/*      */   
/*      */   public int getNmax()
/*      */   {
/* 4363 */     return this.nMax;
/*      */   }
/*      */   
/*      */   public int getNrestarts()
/*      */   {
/* 4368 */     return this.kRestart;
/*      */   }
/*      */   
/*      */   public void setNrestartsMax(int nrs)
/*      */   {
/* 4373 */     this.konvge = nrs;
/*      */   }
/*      */   
/*      */   public int getNrestartsMax()
/*      */   {
/* 4378 */     return this.konvge;
/*      */   }
/*      */   
/*      */   public double getDegFree()
/*      */   {
/* 4383 */     return this.degreesOfFreedom;
/*      */   }
/*      */   
/*      */   public void setNMreflect(double refl)
/*      */   {
/* 4388 */     this.rCoeff = refl;
/*      */   }
/*      */   
/*      */   public double getNMreflect()
/*      */   {
/* 4393 */     return this.rCoeff;
/*      */   }
/*      */   
/*      */   public void setNMextend(double ext)
/*      */   {
/* 4398 */     this.eCoeff = ext;
/*      */   }
/*      */   
/*      */   public double getNMextend() {
/* 4402 */     return this.eCoeff;
/*      */   }
/*      */   
/*      */   public void setNMcontract(double con)
/*      */   {
/* 4407 */     this.cCoeff = con;
/*      */   }
/*      */   
/*      */   public double getNMcontract()
/*      */   {
/* 4412 */     return this.cCoeff;
/*      */   }
/*      */   
/*      */   public void setTolerance(double tol)
/*      */   {
/* 4417 */     this.fTol = tol;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getTolerance()
/*      */   {
/* 4423 */     return this.fTol;
/*      */   }
/*      */   
/*      */   public double[][] getGrad()
/*      */   {
/* 4428 */     return this.grad;
/*      */   }
/*      */   
/*      */   public void setDelta(double delta)
/*      */   {
/* 4433 */     this.delta = delta;
/*      */   }
/*      */   
/*      */   public double getDelta()
/*      */   {
/* 4438 */     return this.delta;
/*      */   }
/*      */   
/*      */   public boolean getInversionCheck()
/*      */   {
/* 4443 */     return this.invertFlag;
/*      */   }
/*      */   
/*      */   public boolean getPosVarCheck()
/*      */   {
/* 4448 */     return this.posVarFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Vector<Object> testOfAdditionalTerms(double chiSquareR, int nParametersR, double chiSquareF, int nParametersF, int nPoints)
/*      */   {
/* 4455 */     ArrayList<Object> res = testOfAdditionalTerms_ArrayList(chiSquareR, nParametersR, chiSquareF, nParametersF, nPoints);
/* 4456 */     Vector<Object> ret = null;
/* 4457 */     if (res != null) {
/* 4458 */       int n = ret.size();
/* 4459 */       ret = new Vector(n);
/* 4460 */       for (int i = 0; i < n; i++) ret.addElement(res.get(i));
/*      */     }
/* 4462 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Vector<Object> testOfAdditionalTerms_Vector(double chiSquareR, int nParametersR, double chiSquareF, int nParametersF, int nPoints)
/*      */   {
/* 4468 */     return testOfAdditionalTerms(chiSquareR, nParametersR, chiSquareF, nParametersF, nPoints);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> testOfAdditionalTerms_ArrayList(double chiSquareR, int nParametersR, double chiSquareF, int nParametersF, int nPoints)
/*      */   {
/* 4475 */     int degFreedomR = nPoints - nParametersR;
/* 4476 */     int degFreedomF = nPoints - nParametersF;
/*      */     
/*      */ 
/* 4479 */     boolean reversed = false;
/* 4480 */     if (degFreedomR < degFreedomF) {
/* 4481 */       reversed = true;
/* 4482 */       double holdD = chiSquareR;
/* 4483 */       chiSquareR = chiSquareF;
/* 4484 */       chiSquareF = holdD;
/* 4485 */       int holdI = nParametersR;
/* 4486 */       nParametersR = nParametersF;
/* 4487 */       nParametersF = holdI;
/* 4488 */       degFreedomR = nPoints - nParametersR;
/* 4489 */       degFreedomF = nPoints - nParametersF;
/* 4490 */       System.out.println("package flanagan.analysis; class Regression; method testAdditionalTerms");
/* 4491 */       System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
/*      */     }
/* 4493 */     int degFreedomD = degFreedomR - degFreedomF;
/*      */     
/*      */ 
/* 4496 */     double numer = (chiSquareR - chiSquareF) / degFreedomD;
/* 4497 */     double denom = chiSquareF / degFreedomF;
/* 4498 */     double fRatio = numer / denom;
/*      */     
/*      */ 
/* 4501 */     double fProb = 1.0D;
/* 4502 */     if (chiSquareR > chiSquareF) {
/* 4503 */       fProb = Stat.fTestProb(fRatio, degFreedomD, degFreedomF);
/*      */     }
/*      */     
/*      */ 
/* 4507 */     ArrayList<Object> arrayl = new ArrayList();
/* 4508 */     arrayl.add(new Double(fRatio));
/* 4509 */     arrayl.add(new Double(fProb));
/* 4510 */     arrayl.add(new Boolean(reversed));
/* 4511 */     arrayl.add(new Double(chiSquareR));
/* 4512 */     arrayl.add(new Integer(nParametersR));
/* 4513 */     arrayl.add(new Double(chiSquareF));
/* 4514 */     arrayl.add(new Integer(nParametersF));
/* 4515 */     arrayl.add(new Integer(nPoints));
/*      */     
/* 4517 */     return arrayl;
/*      */   }
/*      */   
/*      */ 
/*      */   public double testOfAdditionalTermsFratio(double chiSquareR, int nParametersR, double chiSquareF, int nParametersF, int nPoints)
/*      */   {
/* 4523 */     int degFreedomR = nPoints - nParametersR;
/* 4524 */     int degFreedomF = nPoints - nParametersF;
/*      */     
/*      */ 
/* 4527 */     boolean reversed = false;
/* 4528 */     if (degFreedomR < degFreedomF) {
/* 4529 */       reversed = true;
/* 4530 */       double holdD = chiSquareR;
/* 4531 */       chiSquareR = chiSquareF;
/* 4532 */       chiSquareF = holdD;
/* 4533 */       int holdI = nParametersR;
/* 4534 */       nParametersR = nParametersF;
/* 4535 */       nParametersF = holdI;
/* 4536 */       degFreedomR = nPoints - nParametersR;
/* 4537 */       degFreedomF = nPoints - nParametersF;
/* 4538 */       System.out.println("package flanagan.analysis; class Regression; method testAdditionalTermsFratio");
/* 4539 */       System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
/*      */     }
/* 4541 */     int degFreedomD = degFreedomR - degFreedomF;
/*      */     
/*      */ 
/* 4544 */     double numer = (chiSquareR - chiSquareF) / degFreedomD;
/* 4545 */     double denom = chiSquareF / degFreedomF;
/* 4546 */     double fRatio = numer / denom;
/*      */     
/* 4548 */     return fRatio;
/*      */   }
/*      */   
/*      */ 
/*      */   public double testOfAdditionalTermsFprobabilty(double chiSquareR, int nParametersR, double chiSquareF, int nParametersF, int nPoints)
/*      */   {
/* 4554 */     int degFreedomR = nPoints - nParametersR;
/* 4555 */     int degFreedomF = nPoints - nParametersF;
/*      */     
/*      */ 
/* 4558 */     boolean reversed = false;
/* 4559 */     if (degFreedomR < degFreedomF) {
/* 4560 */       reversed = true;
/* 4561 */       double holdD = chiSquareR;
/* 4562 */       chiSquareR = chiSquareF;
/* 4563 */       chiSquareF = holdD;
/* 4564 */       int holdI = nParametersR;
/* 4565 */       nParametersR = nParametersF;
/* 4566 */       nParametersF = holdI;
/* 4567 */       degFreedomR = nPoints - nParametersR;
/* 4568 */       degFreedomF = nPoints - nParametersF;
/* 4569 */       System.out.println("package flanagan.analysis; class Regression; method testAdditionalTermsFprobability");
/* 4570 */       System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
/*      */     }
/* 4572 */     int degFreedomD = degFreedomR - degFreedomF;
/*      */     
/*      */ 
/* 4575 */     double numer = (chiSquareR - chiSquareF) / degFreedomD;
/* 4576 */     double denom = chiSquareF / degFreedomF;
/* 4577 */     double fRatio = numer / denom;
/*      */     
/*      */ 
/* 4580 */     double fProb = 1.0D;
/* 4581 */     if (chiSquareR > chiSquareF) {
/* 4582 */       fProb = Stat.fTestProb(fRatio, degFreedomD, degFreedomF);
/*      */     }
/*      */     
/* 4585 */     return fProb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void poisson()
/*      */   {
/* 4593 */     fitPoisson(0);
/*      */   }
/*      */   
/*      */   public void poissonPlot()
/*      */   {
/* 4598 */     fitPoisson(1);
/*      */   }
/*      */   
/*      */   protected void fitPoisson(int plotFlag) {
/* 4602 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 4603 */     this.lastMethod = 6;
/* 4604 */     this.linNonLin = false;
/* 4605 */     this.zeroCheck = false;
/* 4606 */     this.nTerms = 2;
/* 4607 */     if (!this.scaleFlag) this.nTerms = 2;
/* 4608 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 4609 */     if (this.degreesOfFreedom < 1) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 4612 */     for (int i = 0; i < this.nData; i++) {
/* 4613 */       if (this.xData[0][i] - Math.floor(this.xData[0][i]) != 0.0D) { throw new IllegalArgumentException("all abscissae must be, mathematically, integer values");
/*      */       }
/*      */     }
/*      */     
/* 4617 */     ArrayList<Object> ret1 = dataSign(this.yData);
/* 4618 */     Double tempd = null;
/* 4619 */     Integer tempi = null;
/* 4620 */     tempi = (Integer)ret1.get(5);
/* 4621 */     int peaki = tempi.intValue();
/* 4622 */     double mean = this.xData[0][peaki];
/*      */     
/*      */ 
/* 4625 */     tempd = (Double)ret1.get(4);
/* 4626 */     double peak = tempd.doubleValue();
/*      */     
/*      */ 
/* 4629 */     double[] start = new double[this.nTerms];
/* 4630 */     double[] step = new double[this.nTerms];
/* 4631 */     start[0] = mean;
/* 4632 */     if (this.scaleFlag) {
/* 4633 */       start[1] = (peak / (Math.exp(mean * Math.log(mean) - Stat.logFactorial(mean)) * Math.exp(-mean)));
/*      */     }
/* 4635 */     step[0] = (0.1D * start[0]);
/* 4636 */     if (step[0] == 0.0D) {
/* 4637 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 4638 */       Double tempdd = null;
/* 4639 */       tempdd = (Double)ret0.get(2);
/* 4640 */       double xmax = tempdd.doubleValue();
/* 4641 */       if (xmax == 0.0D) {
/* 4642 */         tempdd = (Double)ret0.get(0);
/* 4643 */         xmax = tempdd.doubleValue();
/*      */       }
/* 4645 */       step[0] = (xmax * 0.1D);
/*      */     }
/* 4647 */     if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */     }
/*      */     
/* 4650 */     PoissonFunction f = new PoissonFunction();
/* 4651 */     addConstraint(1, -1, 0.0D);
/* 4652 */     f.scaleOption = this.scaleFlag;
/* 4653 */     f.scaleFactor = this.yScaleFactor;
/*      */     
/* 4655 */     Object regFun2 = f;
/* 4656 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 4658 */     if (plotFlag == 1)
/*      */     {
/* 4660 */       if (!this.supressPrint) { print();
/*      */       }
/* 4662 */       this.plotOpt = false;
/* 4663 */       int flag = plotXY(f);
/* 4664 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void gaussian()
/*      */   {
/* 4673 */     fitGaussian(0);
/*      */   }
/*      */   
/*      */   public void normal() {
/* 4677 */     fitGaussian(0);
/*      */   }
/*      */   
/*      */   public void gaussianPlot()
/*      */   {
/* 4682 */     fitGaussian(1);
/*      */   }
/*      */   
/*      */   public void normalPlot()
/*      */   {
/* 4687 */     fitGaussian(1);
/*      */   }
/*      */   
/*      */   protected void fitGaussian(int plotFlag)
/*      */   {
/* 4692 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 4693 */     this.lastMethod = 4;
/* 4694 */     this.linNonLin = false;
/* 4695 */     this.zeroCheck = false;
/* 4696 */     this.nTerms = 3;
/* 4697 */     if (!this.scaleFlag) this.nTerms = 2;
/* 4698 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 4699 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 4702 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 4705 */     Double tempd = null;
/* 4706 */     ArrayList<Object> retY = dataSign(this.yData);
/* 4707 */     tempd = (Double)retY.get(4);
/* 4708 */     double yPeak = tempd.doubleValue();
/* 4709 */     boolean yFlag = false;
/* 4710 */     if (yPeak < 0.0D) {
/* 4711 */       System.out.println("Regression.fitGaussian(): This implementation of the Gaussian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 4712 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 4713 */       for (int i = 0; i < this.nData; i++) {
/* 4714 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 4716 */       retY = dataSign(this.yData);
/* 4717 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 4721 */     ArrayList<Object> ret1 = dataSign(this.yData);
/* 4722 */     Integer tempi = null;
/* 4723 */     tempi = (Integer)ret1.get(5);
/* 4724 */     int peaki = tempi.intValue();
/* 4725 */     double mean = this.xData[0][peaki];
/*      */     
/*      */ 
/* 4728 */     double sd = Math.sqrt(2.0D) * halfWidth(this.xData[0], this.yData);
/*      */     
/*      */ 
/* 4731 */     tempd = (Double)ret1.get(4);
/* 4732 */     double ym = tempd.doubleValue();
/* 4733 */     ym = ym * sd * Math.sqrt(6.283185307179586D);
/*      */     
/*      */ 
/* 4736 */     double[] start = new double[this.nTerms];
/* 4737 */     double[] step = new double[this.nTerms];
/* 4738 */     start[0] = mean;
/* 4739 */     start[1] = sd;
/* 4740 */     if (this.scaleFlag) {
/* 4741 */       start[2] = ym;
/*      */     }
/* 4743 */     step[0] = (0.1D * sd);
/* 4744 */     step[1] = (0.1D * start[1]);
/* 4745 */     if (step[1] == 0.0D) {
/* 4746 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 4747 */       Double tempdd = null;
/* 4748 */       tempdd = (Double)ret0.get(2);
/* 4749 */       double xmax = tempdd.doubleValue();
/* 4750 */       if (xmax == 0.0D) {
/* 4751 */         tempdd = (Double)ret0.get(0);
/* 4752 */         xmax = tempdd.doubleValue();
/*      */       }
/* 4754 */       step[0] = (xmax * 0.1D);
/*      */     }
/* 4756 */     if (this.scaleFlag) { step[2] = (0.1D * start[1]);
/*      */     }
/*      */     
/* 4759 */     GaussianFunction f = new GaussianFunction();
/* 4760 */     addConstraint(1, -1, 0.0D);
/* 4761 */     f.scaleOption = this.scaleFlag;
/* 4762 */     f.scaleFactor = this.yScaleFactor;
/*      */     
/* 4764 */     Object regFun2 = f;
/* 4765 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 4767 */     if (plotFlag == 1)
/*      */     {
/* 4769 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 4772 */       int flag = plotXY(f);
/* 4773 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 4776 */     if (yFlag)
/*      */     {
/* 4778 */       for (int i = 0; i < this.nData - 1; i++) {
/* 4779 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gaussian(double[] initialEstimates, boolean[] fixed)
/*      */   {
/* 4789 */     fitGaussianFixed(initialEstimates, fixed, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void normal(double[] initialEstimates, boolean[] fixed)
/*      */   {
/* 4796 */     fitGaussianFixed(initialEstimates, fixed, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void gaussianPlot(double[] initialEstimates, boolean[] fixed)
/*      */   {
/* 4803 */     fitGaussianFixed(initialEstimates, fixed, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void normalPlot(double[] initialEstimates, boolean[] fixed)
/*      */   {
/* 4810 */     fitGaussianFixed(initialEstimates, fixed, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fitGaussianFixed(double[] initialEstimates, boolean[] fixed, int plotFlag)
/*      */   {
/* 4818 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 4819 */     this.lastMethod = 38;
/* 4820 */     this.values = initialEstimates;
/* 4821 */     this.fixed = fixed;
/* 4822 */     this.scaleFlag = true;
/* 4823 */     this.linNonLin = false;
/* 4824 */     this.zeroCheck = false;
/* 4825 */     this.nTerms = 3;
/* 4826 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 4827 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 4830 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 4833 */     Double tempd = null;
/* 4834 */     ArrayList<Object> retY = dataSign(this.yData);
/* 4835 */     tempd = (Double)retY.get(4);
/* 4836 */     double yPeak = tempd.doubleValue();
/* 4837 */     boolean yFlag = false;
/* 4838 */     if (yPeak < 0.0D) {
/* 4839 */       System.out.println("Regression.fitGaussian(): This implementation of the Gaussian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 4840 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 4841 */       for (int i = 0; i < this.nData; i++) {
/* 4842 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 4844 */       retY = dataSign(this.yData);
/* 4845 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 4849 */     GaussianFunctionFixed f = new GaussianFunctionFixed();
/* 4850 */     f.fixed = fixed;
/* 4851 */     f.param = initialEstimates;
/*      */     
/*      */ 
/* 4854 */     int nT = this.nTerms;
/* 4855 */     for (int i = 0; i < this.nTerms; i++) if (fixed[i] != 0) nT--;
/* 4856 */     if (nT == 0) {
/* 4857 */       if (plotFlag == 0) {
/* 4858 */         throw new IllegalArgumentException("At least one parameter must be available for variation by the Regression procedure or GauasianPlot should have been called and not Gaussian");
/*      */       }
/*      */       
/* 4861 */       plotFlag = 3;
/*      */     }
/*      */     
/*      */ 
/* 4865 */     double[] start = new double[nT];
/* 4866 */     double[] step = new double[nT];
/* 4867 */     boolean[] constraint = new boolean[nT];
/*      */     
/*      */ 
/* 4870 */     double xMin = Fmath.minimum(this.xData[0]);
/* 4871 */     double xMax = Fmath.maximum(this.xData[0]);
/* 4872 */     double yMax = Fmath.maximum(this.yData);
/* 4873 */     if (initialEstimates[2] == 0.0D) {
/* 4874 */       if (fixed[2] != 0) {
/* 4875 */         throw new IllegalArgumentException("Scale factor has been fixed at zero");
/*      */       }
/*      */       
/* 4878 */       initialEstimates[2] = yMax;
/*      */     }
/*      */     
/* 4881 */     int ii = 0;
/* 4882 */     for (int i = 0; i < this.nTerms; i++) {
/* 4883 */       if (fixed[i] == 0) {
/* 4884 */         start[ii] = initialEstimates[i];
/* 4885 */         start[ii] *= 0.1D;
/* 4886 */         if (step[ii] == 0.0D) step[ii] = ((xMax - xMin) * 0.1D);
/* 4887 */         constraint[ii] = false;
/* 4888 */         if (i == 1) constraint[ii] = true;
/* 4889 */         ii++;
/*      */       }
/*      */     }
/* 4892 */     this.nTerms = nT;
/*      */     
/*      */ 
/* 4895 */     for (int i = 0; i < this.nTerms; i++) {
/* 4896 */       if (constraint[i] != 0) addConstraint(i, -1, 0.0D);
/*      */     }
/* 4898 */     Object regFun2 = f;
/* 4899 */     if (plotFlag != 3) { nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     }
/* 4901 */     if (plotFlag == 1)
/*      */     {
/* 4903 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 4906 */       int flag = plotXY(f);
/* 4907 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */     int flag;
/* 4910 */     if (plotFlag == 3)
/*      */     {
/* 4912 */       flag = plotXYfixed(regFun2, "Gaussian distribution - all parameters fixed");
/*      */     }
/*      */     
/* 4915 */     if (yFlag)
/*      */     {
/* 4917 */       for (int i = 0; i < this.nData - 1; i++) {
/* 4918 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void logNormal()
/*      */   {
/* 4929 */     fitLogNormalTwoPar(0);
/*      */   }
/*      */   
/*      */   public void logNormalTwoPar() {
/* 4933 */     fitLogNormalTwoPar(0);
/*      */   }
/*      */   
/*      */   public void logNormalPlot()
/*      */   {
/* 4938 */     fitLogNormalTwoPar(1);
/*      */   }
/*      */   
/*      */   public void logNormalTwoParPlot() {
/* 4942 */     fitLogNormalTwoPar(1);
/*      */   }
/*      */   
/*      */   protected void fitLogNormalTwoPar(int plotFlag)
/*      */   {
/* 4947 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 4948 */     this.lastMethod = 36;
/* 4949 */     this.linNonLin = false;
/* 4950 */     this.zeroCheck = false;
/* 4951 */     this.nTerms = 3;
/* 4952 */     if (!this.scaleFlag) this.nTerms = 2;
/* 4953 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 4954 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 4957 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 4960 */     Double tempd = null;
/* 4961 */     ArrayList<Object> retY = dataSign(this.yData);
/* 4962 */     tempd = (Double)retY.get(4);
/* 4963 */     double yPeak = tempd.doubleValue();
/* 4964 */     boolean yFlag = false;
/* 4965 */     if (yPeak < 0.0D) {
/* 4966 */       System.out.println("Regression.fitLogNormalTwoPar(): This implementation of the two parameter log-nprmal distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 4967 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 4968 */       for (int i = 0; i < this.nData; i++) {
/* 4969 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 4971 */       retY = dataSign(this.yData);
/* 4972 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 4976 */     ArrayList<Object> ret1 = dataSign(this.yData);
/* 4977 */     Integer tempi = null;
/* 4978 */     tempi = (Integer)ret1.get(5);
/* 4979 */     int peaki = tempi.intValue();
/* 4980 */     double mean = this.xData[0][peaki];
/*      */     
/*      */ 
/* 4983 */     double mu = 0.0D;
/* 4984 */     for (int i = 0; i < this.nData; i++) mu += Math.log(this.xData[0][i]);
/* 4985 */     mu /= this.nData;
/*      */     
/*      */ 
/* 4988 */     double sigma = 0.0D;
/* 4989 */     for (int i = 0; i < this.nData; i++) sigma += Fmath.square(Math.log(this.xData[0][i]) - mu);
/* 4990 */     sigma = Math.sqrt(sigma / this.nData);
/*      */     
/*      */ 
/* 4993 */     tempd = (Double)ret1.get(4);
/* 4994 */     double ym = tempd.doubleValue();
/* 4995 */     ym *= Math.exp(mu - sigma * sigma / 2.0D);
/*      */     
/*      */ 
/* 4998 */     double[] start = new double[this.nTerms];
/* 4999 */     double[] step = new double[this.nTerms];
/* 5000 */     start[0] = mu;
/* 5001 */     start[1] = sigma;
/* 5002 */     if (this.scaleFlag) {
/* 5003 */       start[2] = ym;
/*      */     }
/* 5005 */     step[0] = (0.1D * start[0]);
/* 5006 */     step[1] = (0.1D * start[1]);
/* 5007 */     if (step[0] == 0.0D) {
/* 5008 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 5009 */       Double tempdd = null;
/* 5010 */       tempdd = (Double)ret0.get(2);
/* 5011 */       double xmax = tempdd.doubleValue();
/* 5012 */       if (xmax == 0.0D) {
/* 5013 */         tempdd = (Double)ret0.get(0);
/* 5014 */         xmax = tempdd.doubleValue();
/*      */       }
/* 5016 */       step[0] = (xmax * 0.1D);
/*      */     }
/* 5018 */     if (step[0] == 0.0D) {
/* 5019 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 5020 */       Double tempdd = null;
/* 5021 */       tempdd = (Double)ret0.get(2);
/* 5022 */       double xmax = tempdd.doubleValue();
/* 5023 */       if (xmax == 0.0D) {
/* 5024 */         tempdd = (Double)ret0.get(0);
/* 5025 */         xmax = tempdd.doubleValue();
/*      */       }
/* 5027 */       step[1] = (xmax * 0.1D);
/*      */     }
/* 5029 */     if (this.scaleFlag) { step[2] = (0.1D * start[2]);
/*      */     }
/*      */     
/* 5032 */     LogNormalTwoParFunction f = new LogNormalTwoParFunction();
/* 5033 */     addConstraint(1, -1, 0.0D);
/* 5034 */     f.scaleOption = this.scaleFlag;
/* 5035 */     f.scaleFactor = this.yScaleFactor;
/* 5036 */     Object regFun2 = f;
/* 5037 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 5039 */     if (plotFlag == 1)
/*      */     {
/* 5041 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 5044 */       int flag = plotXY(f);
/* 5045 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 5048 */     if (yFlag)
/*      */     {
/* 5050 */       for (int i = 0; i < this.nData - 1; i++) {
/* 5051 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void logNormalThreePar()
/*      */   {
/* 5060 */     fitLogNormalThreePar(0);
/*      */   }
/*      */   
/*      */   public void logNormalThreeParPlot()
/*      */   {
/* 5065 */     fitLogNormalThreePar(1);
/*      */   }
/*      */   
/*      */   protected void fitLogNormalThreePar(int plotFlag)
/*      */   {
/* 5070 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 5071 */     this.lastMethod = 37;
/* 5072 */     this.linNonLin = false;
/* 5073 */     this.zeroCheck = false;
/* 5074 */     this.nTerms = 4;
/* 5075 */     if (!this.scaleFlag) this.nTerms = 3;
/* 5076 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 5077 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 5080 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 5083 */     Double tempd = null;
/* 5084 */     ArrayList<Object> retY = dataSign(this.yData);
/* 5085 */     tempd = (Double)retY.get(4);
/* 5086 */     double yPeak = tempd.doubleValue();
/* 5087 */     boolean yFlag = false;
/* 5088 */     if (yPeak < 0.0D) {
/* 5089 */       System.out.println("Regression.fitLogNormalThreePar(): This implementation of the three parameter log-normal distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 5090 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 5091 */       for (int i = 0; i < this.nData; i++) {
/* 5092 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 5094 */       retY = dataSign(this.yData);
/* 5095 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 5099 */     ArrayList<Object> ret1 = dataSign(this.yData);
/* 5100 */     Integer tempi = null;
/* 5101 */     tempi = (Integer)ret1.get(5);
/* 5102 */     int peaki = tempi.intValue();
/* 5103 */     double mean = this.xData[0][peaki];
/*      */     
/*      */ 
/* 5106 */     double gamma = 0.0D;
/* 5107 */     for (int i = 0; i < this.nData; i++) gamma += this.xData[0][i];
/* 5108 */     gamma /= this.nData;
/*      */     
/*      */ 
/* 5111 */     double beta = 0.0D;
/* 5112 */     for (int i = 0; i < this.nData; i++) beta += Fmath.square(Math.log(this.xData[0][i]) - Math.log(gamma));
/* 5113 */     beta = Math.sqrt(beta / this.nData);
/*      */     
/*      */ 
/* 5116 */     ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 5117 */     Double tempdd = null;
/* 5118 */     tempdd = (Double)ret0.get(0);
/* 5119 */     double xmin = tempdd.doubleValue();
/* 5120 */     tempdd = (Double)ret0.get(2);
/* 5121 */     double xmax = tempdd.doubleValue();
/* 5122 */     double alpha = xmin - (xmax - xmin) / 100.0D;
/* 5123 */     if (xmin == 0.0D) { alpha -= (xmax - xmin) / 100.0D;
/*      */     }
/*      */     
/*      */ 
/* 5127 */     tempd = (Double)ret1.get(4);
/* 5128 */     double ym = tempd.doubleValue();
/* 5129 */     ym = ym * (gamma + alpha) * Math.exp(-beta * beta / 2.0D);
/*      */     
/*      */ 
/* 5132 */     double[] start = new double[this.nTerms];
/* 5133 */     double[] step = new double[this.nTerms];
/* 5134 */     start[0] = alpha;
/* 5135 */     start[1] = beta;
/* 5136 */     start[2] = gamma;
/* 5137 */     if (this.scaleFlag) {
/* 5138 */       start[3] = ym;
/*      */     }
/* 5140 */     step[0] = (0.1D * start[0]);
/* 5141 */     step[1] = (0.1D * start[1]);
/* 5142 */     step[2] = (0.1D * start[2]);
/* 5143 */     for (int i = 0; i < 3; i++) {
/* 5144 */       if (step[i] == 0.0D) step[i] = (xmax * 0.1D);
/*      */     }
/* 5146 */     if (this.scaleFlag) { step[3] = (0.1D * start[3]);
/*      */     }
/*      */     
/* 5149 */     LogNormalThreeParFunction f = new LogNormalThreeParFunction();
/* 5150 */     addConstraint(0, 1, xmin);
/* 5151 */     addConstraint(1, -1, 0.0D);
/* 5152 */     addConstraint(2, -1, 0.0D);
/*      */     
/* 5154 */     f.scaleOption = this.scaleFlag;
/* 5155 */     f.scaleFactor = this.yScaleFactor;
/* 5156 */     Object regFun2 = f;
/* 5157 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 5159 */     if (plotFlag == 1)
/*      */     {
/* 5161 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 5164 */       int flag = plotXY(f);
/* 5165 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 5168 */     if (yFlag)
/*      */     {
/* 5170 */       for (int i = 0; i < this.nData - 1; i++) {
/* 5171 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void lorentzian()
/*      */   {
/* 5181 */     fitLorentzian(0);
/*      */   }
/*      */   
/*      */   public void lorentzianPlot() {
/* 5185 */     fitLorentzian(1);
/*      */   }
/*      */   
/*      */   protected void fitLorentzian(int allTest) {
/* 5189 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 5190 */     this.lastMethod = 5;
/* 5191 */     this.linNonLin = false;
/* 5192 */     this.zeroCheck = false;
/* 5193 */     this.nTerms = 3;
/* 5194 */     if (!this.scaleFlag) this.nTerms = 2;
/* 5195 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 5196 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 5199 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 5202 */     Double tempd = null;
/* 5203 */     ArrayList<Object> retY = dataSign(this.yData);
/* 5204 */     tempd = (Double)retY.get(4);
/* 5205 */     double yPeak = tempd.doubleValue();
/* 5206 */     boolean yFlag = false;
/* 5207 */     if (yPeak < 0.0D) {
/* 5208 */       System.out.println("Regression.fitLorentzian(): This implementation of the Lorentzian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 5209 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 5210 */       for (int i = 0; i < this.nData; i++) {
/* 5211 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 5213 */       retY = dataSign(this.yData);
/* 5214 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 5218 */     ArrayList ret1 = dataSign(this.yData);
/* 5219 */     Integer tempi = null;
/* 5220 */     tempi = (Integer)ret1.get(5);
/* 5221 */     int peaki = tempi.intValue();
/* 5222 */     double mean = this.xData[0][peaki];
/*      */     
/*      */ 
/* 5225 */     double sd = halfWidth(this.xData[0], this.yData);
/*      */     
/*      */ 
/* 5228 */     tempd = (Double)ret1.get(4);
/* 5229 */     double ym = tempd.doubleValue();
/* 5230 */     ym = ym * sd * 3.141592653589793D / 2.0D;
/*      */     
/*      */ 
/* 5233 */     double[] start = new double[this.nTerms];
/* 5234 */     double[] step = new double[this.nTerms];
/* 5235 */     start[0] = mean;
/* 5236 */     start[1] = (sd * 0.9D);
/* 5237 */     if (this.scaleFlag) {
/* 5238 */       start[2] = ym;
/*      */     }
/* 5240 */     step[0] = (0.2D * sd);
/* 5241 */     if (step[0] == 0.0D) {
/* 5242 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 5243 */       Double tempdd = null;
/* 5244 */       tempdd = (Double)ret0.get(2);
/* 5245 */       double xmax = tempdd.doubleValue();
/* 5246 */       if (xmax == 0.0D) {
/* 5247 */         tempdd = (Double)ret0.get(0);
/* 5248 */         xmax = tempdd.doubleValue();
/*      */       }
/* 5250 */       step[0] = (xmax * 0.1D);
/*      */     }
/* 5252 */     step[1] = (0.2D * start[1]);
/* 5253 */     if (this.scaleFlag) { step[2] = (0.2D * start[2]);
/*      */     }
/*      */     
/* 5256 */     LorentzianFunction f = new LorentzianFunction();
/* 5257 */     addConstraint(1, -1, 0.0D);
/* 5258 */     f.scaleOption = this.scaleFlag;
/* 5259 */     f.scaleFactor = this.yScaleFactor;
/* 5260 */     Object regFun2 = f;
/* 5261 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 5263 */     if (allTest == 1)
/*      */     {
/* 5265 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 5268 */       int flag = plotXY(f);
/* 5269 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 5272 */     if (yFlag)
/*      */     {
/* 5274 */       for (int i = 0; i < this.nData - 1; i++) {
/* 5275 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void fitOneOrSeveralDistributions(double[] array)
/*      */   {
/* 5285 */     int numberOfPoints = array.length;
/* 5286 */     double maxValue = Fmath.maximum(array);
/* 5287 */     double minValue = Fmath.minimum(array);
/* 5288 */     double span = maxValue - minValue;
/*      */     
/*      */ 
/* 5291 */     int numberOfBins = (int)Math.ceil(Math.sqrt(numberOfPoints));
/* 5292 */     double binWidth = span / numberOfBins;
/* 5293 */     double averagePointsPerBin = numberOfPoints / numberOfBins;
/*      */     
/*      */ 
/* 5296 */     String comment = "Maximum value:  " + maxValue + "\n";
/* 5297 */     comment = comment + "Minimum value:  " + minValue + "\n";
/* 5298 */     comment = comment + "Suggested bin width:  " + binWidth + "\n";
/* 5299 */     comment = comment + "Giving an average points per bin:  " + averagePointsPerBin + "\n";
/* 5300 */     comment = comment + "If you wish to change the bin width enter the new value below \n";
/* 5301 */     comment = comment + "and click on OK\n";
/* 5302 */     comment = comment + "If you do NOT wish to change the bin width simply click on OK";
/* 5303 */     binWidth = Db.readDouble(comment, binWidth);
/*      */     
/*      */ 
/* 5306 */     comment = "Input the name of the output text file\n";
/* 5307 */     comment = comment + "[Do not forget the extension, e.g.   .txt]";
/* 5308 */     String outputTitle = Db.readLine(comment, "fitOneOrSeveralDistributionsOutput.txt");
/* 5309 */     FileOutput fout = new FileOutput(outputTitle, 'n');
/* 5310 */     fout.println("Fitting a set of data to one or more distributions");
/* 5311 */     fout.println("Class Regression/Stat: method fitAllDistributions");
/* 5312 */     fout.dateAndTimeln();
/* 5313 */     fout.println();
/* 5314 */     fout.printtab("Number of points: ");
/* 5315 */     fout.println(numberOfPoints);
/* 5316 */     fout.printtab("Minimum value: ");
/* 5317 */     fout.println(minValue);
/* 5318 */     fout.printtab("Maximum value: ");
/* 5319 */     fout.println(maxValue);
/* 5320 */     fout.printtab("Number of bins: ");
/* 5321 */     fout.println(numberOfBins);
/* 5322 */     fout.printtab("Bin width: ");
/* 5323 */     fout.println(binWidth);
/* 5324 */     fout.printtab("Average number of points per bin: ");
/* 5325 */     fout.println(averagePointsPerBin);
/* 5326 */     fout.println();
/*      */     
/*      */ 
/* 5329 */     String[] comments = { "Gaussian Distribution", "Two parameter Log-normal Distribution", "Three parameter Log-normal Distribution", "Logistic Distribution", "Lorentzian Distribution", "Type 1 Extreme Distribution - Gumbel minimum order statistic", "Type 1 Extreme Distribution - Gumbel maximum order statistic", "Type 2 Extreme Distribution - Frechet", "Type 3 Extreme Distribution - Weibull", "Type 3 Extreme Distribution - Exponential Distribution", "Type 3 Extreme Distribution - Rayleigh Distribution", "Pareto Distribution", "Beta Distribution", "Gamma Distribution", "Erlang Distribution", "exit" };
/* 5330 */     String[] boxTitles = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "exit" };
/* 5331 */     String headerComment = "Choose next distribution to be fitted by clicking on box number";
/* 5332 */     int defaultBox = 1;
/* 5333 */     boolean testDistType = true;
/* 5334 */     Regression reg = null;
/* 5335 */     double[] coeff = null;
/* 5336 */     while (testDistType) {
/* 5337 */       int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/* 5338 */       switch (opt) {
/*      */       case 1: 
/* 5340 */         reg = new Regression(array, binWidth);
/* 5341 */         reg.supressPrint();
/* 5342 */         reg.gaussianPlot();
/* 5343 */         coeff = reg.getCoeff();
/* 5344 */         fout.println("NORMAL (GAUSSIAN) DISTRIBUTION");
/* 5345 */         fout.println("Best Estimates:");
/* 5346 */         fout.printtab("Mean [mu] ");
/* 5347 */         fout.println(coeff[0]);
/* 5348 */         fout.printtab("Standard deviation [sigma] ");
/* 5349 */         fout.println(coeff[1]);
/* 5350 */         fout.printtab("Scaling factor [Ao] ");
/* 5351 */         fout.println(coeff[2]);
/* 5352 */         regressionDetails(fout, reg);
/* 5353 */         break;
/*      */       case 2: 
/* 5355 */         reg = new Regression(array, binWidth);
/* 5356 */         reg.supressPrint();
/* 5357 */         reg.logNormalTwoParPlot();
/* 5358 */         coeff = reg.getCoeff();
/* 5359 */         fout.println("LOG-NORMAL DISTRIBUTION (two parameter statistic)");
/* 5360 */         fout.println("Best Estimates:");
/* 5361 */         fout.printtab("Location parameter [mu] ");
/* 5362 */         fout.println(coeff[0]);
/* 5363 */         fout.printtab("Shape parameter [sigma] ");
/* 5364 */         fout.println(coeff[1]);
/* 5365 */         fout.printtab("Scaling factor [Ao] ");
/* 5366 */         fout.println(coeff[2]);
/* 5367 */         regressionDetails(fout, reg);
/* 5368 */         break;
/*      */       case 3: 
/* 5370 */         reg = new Regression(array, binWidth);
/* 5371 */         reg.supressPrint();
/* 5372 */         reg.logNormalThreeParPlot();
/* 5373 */         coeff = reg.getCoeff();
/* 5374 */         fout.println("LOG-NORMAL DISTRIBUTION (three parameter statistic)");
/* 5375 */         fout.println("Best Estimates:");
/* 5376 */         fout.printtab("Location parameter [alpha] ");
/* 5377 */         fout.println(coeff[0]);
/* 5378 */         fout.printtab("Shape parameter [beta] ");
/* 5379 */         fout.println(coeff[1]);
/* 5380 */         fout.printtab("Scale parameter [gamma] ");
/* 5381 */         fout.println(coeff[2]);
/* 5382 */         fout.printtab("Scaling factor [Ao] ");
/* 5383 */         fout.println(coeff[3]);
/* 5384 */         regressionDetails(fout, reg);
/* 5385 */         break;
/*      */       case 4: 
/* 5387 */         reg = new Regression(array, binWidth);
/* 5388 */         reg.supressPrint();
/* 5389 */         reg.logisticPlot();
/* 5390 */         coeff = reg.getCoeff();
/* 5391 */         fout.println("LOGISTIC DISTRIBUTION");
/* 5392 */         fout.println("Best Estimates:");
/* 5393 */         fout.printtab("Location parameter [mu] ");
/* 5394 */         fout.println(coeff[0]);
/* 5395 */         fout.printtab("Scale parameter [beta] ");
/* 5396 */         fout.println(coeff[1]);
/* 5397 */         fout.printtab("Scaling factor [Ao] ");
/* 5398 */         fout.println(coeff[2]);
/* 5399 */         regressionDetails(fout, reg);
/* 5400 */         break;
/*      */       case 5: 
/* 5402 */         reg = new Regression(array, binWidth);
/* 5403 */         reg.supressPrint();
/* 5404 */         reg.lorentzianPlot();
/* 5405 */         coeff = reg.getCoeff();
/* 5406 */         fout.println("LORENTZIAN DISTRIBUTION");
/* 5407 */         fout.println("Best Estimates:");
/* 5408 */         fout.printtab("Mean [mu] ");
/* 5409 */         fout.println(coeff[0]);
/* 5410 */         fout.printtab("Half-height parameter [Gamma] ");
/* 5411 */         fout.println(coeff[1]);
/* 5412 */         fout.printtab("Scaling factor [Ao] ");
/* 5413 */         fout.println(coeff[2]);
/* 5414 */         regressionDetails(fout, reg);
/* 5415 */         break;
/*      */       case 6: 
/* 5417 */         reg = new Regression(array, binWidth);
/* 5418 */         reg.supressPrint();
/* 5419 */         reg.gumbelMinPlot();
/* 5420 */         coeff = reg.getCoeff();
/* 5421 */         fout.println("TYPE 1 (GUMBEL) EXTREME DISTRIBUTION [MINIMUM ORDER STATISTIC]");
/* 5422 */         fout.println("Best Estimates:");
/* 5423 */         fout.printtab("Location parameter [mu] ");
/* 5424 */         fout.println(coeff[0]);
/* 5425 */         fout.printtab("Scale parameter [sigma] ");
/* 5426 */         fout.println(coeff[1]);
/* 5427 */         fout.printtab("Scaling factor [Ao] ");
/* 5428 */         fout.println(coeff[2]);
/* 5429 */         regressionDetails(fout, reg);
/* 5430 */         break;
/*      */       case 7: 
/* 5432 */         reg = new Regression(array, binWidth);
/* 5433 */         reg.supressPrint();
/* 5434 */         reg.gumbelMaxPlot();
/* 5435 */         coeff = reg.getCoeff();
/* 5436 */         fout.println("TYPE 1 (GUMBEL) EXTREME DISTRIBUTION [MAXIMUM ORDER STATISTIC]");
/* 5437 */         fout.println("Best Estimates:");
/* 5438 */         fout.printtab("Location parameter [mu] ");
/* 5439 */         fout.println(coeff[0]);
/* 5440 */         fout.printtab("Scale parameter [sigma] ");
/* 5441 */         fout.println(coeff[1]);
/* 5442 */         fout.printtab("Scaling factor [Ao] ");
/* 5443 */         fout.println(coeff[2]);
/* 5444 */         regressionDetails(fout, reg);
/* 5445 */         break;
/*      */       case 8: 
/* 5447 */         reg = new Regression(array, binWidth);
/* 5448 */         reg.supressPrint();
/* 5449 */         reg.frechetPlot();
/* 5450 */         coeff = reg.getCoeff();
/* 5451 */         fout.println("TYPE 2 (FRECHET) EXTREME DISTRIBUTION");
/* 5452 */         fout.println("Best Estimates:");
/* 5453 */         fout.printtab("Location parameter [mu] ");
/* 5454 */         fout.println(coeff[0]);
/* 5455 */         fout.printtab("Scale parameter [sigma] ");
/* 5456 */         fout.println(coeff[1]);
/* 5457 */         fout.printtab("Shape parameter [gamma] ");
/* 5458 */         fout.println(coeff[2]);
/* 5459 */         fout.printtab("Scaling factor [Ao] ");
/* 5460 */         fout.println(coeff[3]);
/* 5461 */         regressionDetails(fout, reg);
/* 5462 */         break;
/*      */       case 9: 
/* 5464 */         reg = new Regression(array, binWidth);
/* 5465 */         reg.supressPrint();
/* 5466 */         reg.weibullPlot();
/* 5467 */         coeff = reg.getCoeff();
/* 5468 */         fout.println("TYPE 3 (WEIBULL) EXTREME DISTRIBUTION");
/* 5469 */         fout.println("Best Estimates:");
/* 5470 */         fout.printtab("Location parameter [mu] ");
/* 5471 */         fout.println(coeff[0]);
/* 5472 */         fout.printtab("Scale parameter [sigma] ");
/* 5473 */         fout.println(coeff[1]);
/* 5474 */         fout.printtab("Shape parameter [gamma] ");
/* 5475 */         fout.println(coeff[2]);
/* 5476 */         fout.printtab("Scaling factor [Ao] ");
/* 5477 */         fout.println(coeff[3]);
/* 5478 */         regressionDetails(fout, reg);
/* 5479 */         break;
/*      */       case 10: 
/* 5481 */         reg = new Regression(array, binWidth);
/* 5482 */         reg.supressPrint();
/* 5483 */         reg.exponentialPlot();
/* 5484 */         coeff = reg.getCoeff();
/* 5485 */         fout.println("EXPONENTIAL DISTRIBUTION");
/* 5486 */         fout.println("Best Estimates:");
/* 5487 */         fout.printtab("Location parameter [mu] ");
/* 5488 */         fout.println(coeff[0]);
/* 5489 */         fout.printtab("Scale parameter [sigma] ");
/* 5490 */         fout.println(coeff[1]);
/* 5491 */         fout.printtab("Scaling factor [Ao] ");
/* 5492 */         fout.println(coeff[2]);
/* 5493 */         regressionDetails(fout, reg);
/* 5494 */         break;
/*      */       case 11: 
/* 5496 */         reg = new Regression(array, binWidth);
/* 5497 */         reg.supressPrint();
/* 5498 */         reg.rayleighPlot();
/* 5499 */         coeff = reg.getCoeff();
/* 5500 */         fout.println("RAYLEIGH DISTRIBUTION");
/* 5501 */         fout.println("Best Estimates:");
/* 5502 */         fout.printtab("Scale parameter [beta] ");
/* 5503 */         fout.println(coeff[0]);
/* 5504 */         fout.printtab("Scaling factor [Ao] ");
/* 5505 */         fout.println(coeff[1]);
/* 5506 */         regressionDetails(fout, reg);
/* 5507 */         break;
/*      */       case 12: 
/* 5509 */         reg = new Regression(array, binWidth);
/* 5510 */         reg.supressPrint();
/* 5511 */         reg.paretoThreeParPlot();
/* 5512 */         coeff = reg.getCoeff();
/* 5513 */         fout.println("PARETO DISTRIBUTION");
/* 5514 */         fout.println("Best Estimates:");
/* 5515 */         fout.printtab("Shape parameter [alpha] ");
/* 5516 */         fout.println(coeff[0]);
/* 5517 */         fout.printtab("Scale parameter [beta] ");
/* 5518 */         fout.println(coeff[1]);
/* 5519 */         fout.printtab("Threshold parameter [theta] ");
/* 5520 */         fout.println(coeff[2]);
/* 5521 */         fout.printtab("Scaling factor [Ao] ");
/* 5522 */         fout.println(coeff[3]);
/* 5523 */         regressionDetails(fout, reg);
/* 5524 */         break;
/*      */       case 13: 
/* 5526 */         reg = new Regression(array, binWidth);
/* 5527 */         reg.supressPrint();
/* 5528 */         reg.betaMinMaxPlot();
/* 5529 */         coeff = reg.getCoeff();
/* 5530 */         fout.println("BETA DISTRIBUTION");
/* 5531 */         fout.println("Best Estimates:");
/* 5532 */         fout.printtab("Shape parameter [alpha] ");
/* 5533 */         fout.println(coeff[0]);
/* 5534 */         fout.printtab("Shape parameter [beta] ");
/* 5535 */         fout.println(coeff[1]);
/* 5536 */         fout.printtab("minimum limit [min] ");
/* 5537 */         fout.println(coeff[2]);
/* 5538 */         fout.printtab("maximum limit [max] ");
/* 5539 */         fout.println(coeff[3]);
/* 5540 */         fout.printtab("Scaling factor [Ao] ");
/* 5541 */         fout.println(coeff[4]);
/* 5542 */         regressionDetails(fout, reg);
/* 5543 */         break;
/*      */       case 14: 
/* 5545 */         reg = new Regression(array, binWidth);
/* 5546 */         reg.supressPrint();
/* 5547 */         reg.gammaPlot();
/* 5548 */         coeff = reg.getCoeff();
/* 5549 */         fout.println("GAMMA DISTRIBUTION");
/* 5550 */         fout.println("Best Estimates:");
/* 5551 */         fout.printtab("Location parameter [mu] ");
/* 5552 */         fout.println(coeff[0]);
/* 5553 */         fout.printtab("Scale parameter [beta] ");
/* 5554 */         fout.println(coeff[1]);
/* 5555 */         fout.printtab("Shape parameter [gamma] ");
/* 5556 */         fout.println(coeff[2]);
/* 5557 */         fout.printtab("Scaling factor [Ao] ");
/* 5558 */         fout.println(coeff[3]);
/* 5559 */         regressionDetails(fout, reg);
/* 5560 */         break;
/*      */       case 15: 
/* 5562 */         reg = new Regression(array, binWidth);
/* 5563 */         reg.supressPrint();
/* 5564 */         reg.erlangPlot();
/* 5565 */         coeff = reg.getCoeff();
/* 5566 */         fout.println("ERLANG DISTRIBUTION");
/* 5567 */         fout.println("Best Estimates:");
/* 5568 */         fout.printtab("Shape parameter [lambda] ");
/* 5569 */         fout.println(coeff[0]);
/* 5570 */         fout.printtab("Rate parameter [k] ");
/* 5571 */         fout.println(reg.getKayValue());
/* 5572 */         fout.printtab("Scaling factor [Ao] ");
/* 5573 */         fout.println(coeff[1]);
/* 5574 */         regressionDetails(fout, reg);
/* 5575 */         break;
/*      */       case 16: default: 
/* 5577 */         fout.close();
/* 5578 */         testDistType = false;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected static void regressionDetails(FileOutput fout, Regression reg)
/*      */   {
/* 5585 */     fout.println();
/* 5586 */     fout.println("Regression details:");
/* 5587 */     fout.printtab("Chi squared: ");
/* 5588 */     fout.println(reg.getChiSquare());
/* 5589 */     fout.printtab("Reduced chi squared: ");
/* 5590 */     fout.println(reg.getReducedChiSquare());
/* 5591 */     fout.printtab("Sum of squares: ");
/* 5592 */     fout.println(reg.getSumOfSquares());
/* 5593 */     fout.printtab("Degrees of freedom: ");
/* 5594 */     fout.println(reg.getDegFree());
/* 5595 */     fout.printtab("Number of iterations: ");
/* 5596 */     fout.println(reg.getNiter());
/* 5597 */     fout.printtab("maximum number of iterations allowed: ");
/* 5598 */     fout.println(reg.getNmax());
/* 5599 */     fout.println();
/* 5600 */     fout.println();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void multCorrelCoeff(double[] yy, double[] yyCalc, double[] ww)
/*      */   {
/* 5608 */     double sumRecipW = 0.0D;
/* 5609 */     for (int i = 0; i < this.nData; i++) {
/* 5610 */       sumRecipW += 1.0D / Fmath.square(ww[i]);
/*      */     }
/*      */     
/*      */ 
/* 5614 */     double my = 0.0D;
/* 5615 */     for (int j = 0; j < this.nData; j++) {
/* 5616 */       my += yy[j] / Fmath.square(ww[j]);
/*      */     }
/* 5618 */     my /= sumRecipW;
/*      */     
/*      */ 
/*      */ 
/* 5622 */     double mr = 0.0D;
/* 5623 */     double[] residuals = new double[this.nData];
/* 5624 */     for (int j = 0; j < this.nData; j++) {
/* 5625 */       yy[j] -= yyCalc[j];
/* 5626 */       mr += residuals[j] / Fmath.square(ww[j]);
/*      */     }
/* 5628 */     mr /= sumRecipW;
/*      */     
/*      */ 
/* 5631 */     double s2yy = 0.0D;
/* 5632 */     for (int k = 0; k < this.nData; k++) {
/* 5633 */       s2yy += Fmath.square((yy[k] - my) / ww[k]);
/*      */     }
/*      */     
/*      */ 
/* 5637 */     double s2r = 0.0D;
/* 5638 */     for (int k = 0; k < this.nData; k++) {
/* 5639 */       s2r += Fmath.square((residuals[k] - mr) / ww[k]);
/*      */     }
/*      */     
/*      */ 
/* 5643 */     this.sampleR2 = (1.0D - s2r / s2yy);
/* 5644 */     this.sampleR = Math.sqrt(this.sampleR2);
/*      */     
/*      */ 
/* 5647 */     this.adjustedR2 = (((this.nData - 1) * this.sampleR2 - this.nXarrays) / (this.nData - this.nXarrays - 1));
/* 5648 */     this.adjustedR = Math.sqrt(this.adjustedR2);
/*      */     
/*      */ 
/* 5651 */     if (this.nXarrays > 1) {
/* 5652 */       this.multipleF = (this.sampleR2 * (this.nData - this.nXarrays) / ((1.0D - this.sampleR2) * (this.nXarrays - 1)));
/* 5653 */       this.adjustedF = (this.adjustedR2 * (this.nData - this.nXarrays) / ((1.0D - this.adjustedR2) * (this.nXarrays - 1)));
/*      */     }
/*      */   }
/*      */   
/*      */   public double getSampleR()
/*      */   {
/* 5659 */     return this.sampleR;
/*      */   }
/*      */   
/*      */   public double getSampleR2()
/*      */   {
/* 5664 */     return this.sampleR2;
/*      */   }
/*      */   
/*      */   public double getAdjustedR()
/*      */   {
/* 5669 */     return this.adjustedR;
/*      */   }
/*      */   
/*      */   public double getAdjustedR2()
/*      */   {
/* 5674 */     return this.adjustedR2;
/*      */   }
/*      */   
/*      */   public double getMultipleF()
/*      */   {
/* 5679 */     if (this.nXarrays == 1) {
/* 5680 */       System.out.println("Regression.getMultipleF - The regression is not a multple regession: NaN returned");
/*      */     }
/* 5682 */     return this.multipleF;
/*      */   }
/*      */   
/*      */ 
/*      */   protected static ArrayList<Object> dataSign(double[] data)
/*      */   {
/* 5688 */     ArrayList<Object> ret = new ArrayList();
/* 5689 */     int n = data.length;
/*      */     
/*      */ 
/* 5692 */     double peak = 0.0D;
/* 5693 */     int peaki = -1;
/* 5694 */     double shift = 0.0D;
/* 5695 */     double max = data[0];
/* 5696 */     int maxi = 0;
/* 5697 */     double min = data[0];
/* 5698 */     int mini = 0;
/* 5699 */     int signCheckPos = 0;
/* 5700 */     int signCheckNeg = 0;
/* 5701 */     int signCheckZero = 0;
/* 5702 */     int signFlag = -1;
/* 5703 */     double mean = 0.0D;
/*      */     
/* 5705 */     for (int i = 0; i < n; i++) {
/* 5706 */       mean = data[i];
/* 5707 */       if (data[i] > max) {
/* 5708 */         max = data[i];
/* 5709 */         maxi = i;
/*      */       }
/* 5711 */       if (data[i] < min) {
/* 5712 */         min = data[i];
/* 5713 */         mini = i;
/*      */       }
/* 5715 */       if (data[i] == 0.0D) signCheckZero++;
/* 5716 */       if (data[i] > 0.0D) signCheckPos++;
/* 5717 */       if (data[i] < 0.0D) signCheckNeg++;
/*      */     }
/* 5719 */     mean /= n;
/*      */     
/* 5721 */     if (signCheckZero + signCheckPos == n) {
/* 5722 */       peak = max;
/* 5723 */       peaki = maxi;
/* 5724 */       signFlag = 0;
/*      */ 
/*      */     }
/* 5727 */     else if (signCheckZero + signCheckNeg == n) {
/* 5728 */       peak = min;
/* 5729 */       peaki = mini;
/* 5730 */       signFlag = 1;
/*      */     }
/*      */     else {
/* 5733 */       peak = max;
/* 5734 */       peaki = maxi;
/* 5735 */       if (-min > max) {
/* 5736 */         peak = min;
/* 5737 */         peak = mini;
/*      */       }
/* 5739 */       signFlag = 2;
/* 5740 */       shift = -min;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 5745 */     ret.add(new Double(min));
/* 5746 */     ret.add(new Integer(mini));
/* 5747 */     ret.add(new Double(max));
/* 5748 */     ret.add(new Integer(maxi));
/* 5749 */     ret.add(new Double(peak));
/* 5750 */     ret.add(new Integer(peaki));
/* 5751 */     ret.add(new Integer(signFlag));
/* 5752 */     ret.add(new Double(shift));
/* 5753 */     ret.add(new Double(mean));
/*      */     
/* 5755 */     return ret;
/*      */   }
/*      */   
/*      */   public void frechet() {
/* 5759 */     fitFrechet(0, 0);
/*      */   }
/*      */   
/*      */   public void frechetPlot() {
/* 5763 */     fitFrechet(1, 0);
/*      */   }
/*      */   
/*      */   public void frechetTwoPar() {
/* 5767 */     fitFrechet(0, 1);
/*      */   }
/*      */   
/*      */   public void frechetTwoParPlot() {
/* 5771 */     fitFrechet(1, 1);
/*      */   }
/*      */   
/*      */   public void frechetStandard() {
/* 5775 */     fitFrechet(0, 2);
/*      */   }
/*      */   
/*      */   public void frechetStandardPlot() {
/* 5779 */     fitFrechet(1, 2);
/*      */   }
/*      */   
/*      */   protected void fitFrechet(int allTest, int typeFlag) {
/* 5783 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 5784 */     switch (typeFlag) {
/* 5785 */     case 0:  this.lastMethod = 13;
/* 5786 */       this.nTerms = 4;
/* 5787 */       break;
/* 5788 */     case 1:  this.lastMethod = 14;
/* 5789 */       this.nTerms = 3;
/* 5790 */       break;
/* 5791 */     case 2:  this.lastMethod = 15;
/* 5792 */       this.nTerms = 2;
/*      */     }
/*      */     
/* 5795 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 5796 */     this.frechetWeibull = true;
/* 5797 */     fitFrechetWeibull(allTest, typeFlag);
/*      */   }
/*      */   
/*      */   protected void fitFrechetWeibull(int allTest, int typeFlag)
/*      */   {
/* 5802 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 5803 */     this.linNonLin = false;
/* 5804 */     this.zeroCheck = false;
/* 5805 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 5806 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 5809 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 5812 */     Double tempd = null;
/* 5813 */     ArrayList<Object> retY = dataSign(this.yData);
/* 5814 */     tempd = (Double)retY.get(4);
/* 5815 */     double yPeak = tempd.doubleValue();
/* 5816 */     Integer tempi = null;
/* 5817 */     tempi = (Integer)retY.get(5);
/* 5818 */     int peaki = tempi.intValue();
/*      */     
/*      */ 
/* 5821 */     if (infinityCheck(yPeak, peaki)) {
/* 5822 */       retY = dataSign(this.yData);
/* 5823 */       tempd = (Double)retY.get(4);
/* 5824 */       yPeak = tempd.doubleValue();
/* 5825 */       tempi = null;
/* 5826 */       tempi = (Integer)retY.get(5);
/* 5827 */       peaki = tempi.intValue();
/*      */     }
/*      */     
/*      */ 
/* 5831 */     String ss = "Weibull";
/* 5832 */     if (this.frechetWeibull) ss = "Frechet";
/* 5833 */     boolean ySignFlag = false;
/* 5834 */     if (yPeak < 0.0D) {
/* 5835 */       reverseYsign(ss);
/* 5836 */       retY = dataSign(this.yData);
/* 5837 */       yPeak = -yPeak;
/* 5838 */       ySignFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 5842 */     boolean magCheck = false;
/* 5843 */     double magScale = checkYallSmall(yPeak, ss);
/* 5844 */     if (magScale != 1.0D) {
/* 5845 */       magCheck = true;
/* 5846 */       yPeak = 1.0D;
/*      */     }
/*      */     
/*      */ 
/* 5850 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 5851 */     tempd = (Double)retX.get(0);
/* 5852 */     double xMin = tempd.doubleValue();
/*      */     
/*      */ 
/* 5855 */     tempd = (Double)retX.get(2);
/* 5856 */     double xMax = tempd.doubleValue();
/*      */     
/*      */ 
/* 5859 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 5862 */     double sd = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
/*      */     
/*      */ 
/* 5865 */     double[] xx = new double[this.nData];
/* 5866 */     double[] yy = new double[this.nData];
/* 5867 */     double[] ww = new double[this.nData];
/*      */     
/* 5869 */     for (int i = 0; i < this.nData; i++) {
/* 5870 */       xx[i] = this.xData[0][i];
/* 5871 */       yy[i] = this.yData[i];
/* 5872 */       ww[i] = this.weight[i];
/*      */     }
/*      */     
/*      */ 
/* 5876 */     double[] cumX = new double[this.nData];
/* 5877 */     double[] cumY = new double[this.nData];
/* 5878 */     double[] cumW = new double[this.nData];
/* 5879 */     ErrorProp[] cumYe = ErrorProp.oneDarray(this.nData);
/* 5880 */     double yScale = calculateCumulativeValues(cumX, cumY, cumW, cumYe, peaki, yPeak, distribMode, ss);
/*      */     
/*      */ 
/* 5883 */     if (this.frechetWeibull) {
/* 5884 */       for (int i = 0; i < this.nData; i++) {
/* 5885 */         cumYe[i] = ErrorProp.over(1.0D, cumYe[i]);
/* 5886 */         cumYe[i] = ErrorProp.log(cumYe[i]);
/* 5887 */         cumYe[i] = ErrorProp.log(cumYe[i]);
/* 5888 */         cumY[i] = cumYe[i].getValue();
/* 5889 */         cumW[i] = cumYe[i].getError();
/*      */       }
/*      */       
/*      */     } else {
/* 5893 */       for (int i = 0; i < this.nData; i++) {
/* 5894 */         cumYe[i] = ErrorProp.minus(1.0D, cumYe[i]);
/* 5895 */         cumYe[i] = ErrorProp.over(1.0D, cumYe[i]);
/* 5896 */         cumYe[i] = ErrorProp.log(cumYe[i]);
/* 5897 */         cumYe[i] = ErrorProp.log(cumYe[i]);
/* 5898 */         cumY[i] = cumYe[i].getValue();
/* 5899 */         cumW[i] = cumYe[i].getError();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 5904 */     for (int i = 0; i < this.nData; i++) {
/* 5905 */       this.xData[0][i] = cumX[i];
/* 5906 */       this.yData[i] = cumY[i];
/* 5907 */       this.weight[i] = cumW[i];
/*      */     }
/* 5909 */     boolean weightOptHold = this.weightOpt;
/* 5910 */     this.weightOpt = true;
/*      */     
/*      */ 
/*      */ 
/* 5914 */     this.statFlag = false;
/*      */     
/*      */ 
/* 5917 */     double[] start = new double[this.nTerms];
/* 5918 */     double[] step = new double[this.nTerms];
/* 5919 */     for (int i = 0; i < this.nTerms; i++) {
/* 5920 */       start[i] = 1.0D;
/* 5921 */       step[i] = 0.2D;
/*      */     }
/* 5923 */     switch (typeFlag) {
/*      */     case 0: 
/* 5925 */       start[0] = (xMin * 0.9D);
/* 5926 */       start[1] = sd;
/* 5927 */       start[2] = 4.0D;
/* 5928 */       step[0] = (0.2D * start[0]);
/* 5929 */       if (step[0] == 0.0D) {
/* 5930 */         ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 5931 */         Double tempdd = null;
/* 5932 */         tempdd = (Double)ret0.get(2);
/* 5933 */         double xmax = tempdd.doubleValue();
/* 5934 */         if (xmax == 0.0D) {
/* 5935 */           tempdd = (Double)ret0.get(0);
/* 5936 */           xmax = tempdd.doubleValue();
/*      */         }
/* 5938 */         step[0] = (xmax * 0.1D);
/*      */       }
/* 5940 */       step[1] = (0.2D * start[1]);
/* 5941 */       step[2] = (0.5D * start[2]);
/* 5942 */       addConstraint(0, 1, xMin);
/* 5943 */       addConstraint(1, -1, 0.0D);
/* 5944 */       addConstraint(2, -1, 0.0D);
/* 5945 */       break;
/* 5946 */     case 1:  start[0] = sd;
/* 5947 */       start[1] = 4.0D;
/* 5948 */       step[0] = (0.2D * start[0]);
/* 5949 */       step[1] = (0.5D * start[1]);
/* 5950 */       addConstraint(0, -1, 0.0D);
/* 5951 */       addConstraint(1, -1, 0.0D);
/* 5952 */       break;
/* 5953 */     case 2:  start[0] = 4.0D;
/* 5954 */       step[0] = (0.5D * start[0]);
/* 5955 */       addConstraint(0, -1, 0.0D);
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 5960 */     if (this.frechetWeibull) {
/* 5961 */       FrechetFunctionTwo f = new FrechetFunctionTwo();
/* 5962 */       f.typeFlag = typeFlag;
/* 5963 */       Object regFun2 = f;
/* 5964 */       nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     }
/*      */     else {
/* 5967 */       WeibullFunctionTwo f = new WeibullFunctionTwo();
/* 5968 */       f.typeFlag = typeFlag;
/* 5969 */       Object regFun2 = f;
/* 5970 */       nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     }
/*      */     
/*      */ 
/* 5974 */     double[] ests = (double[])this.best.clone();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5980 */     this.statFlag = true;
/*      */     
/*      */ 
/* 5983 */     this.weightOpt = weightOptHold;
/* 5984 */     for (int i = 0; i < this.nData; i++) {
/* 5985 */       this.xData[0][i] = xx[i];
/* 5986 */       this.yData[i] = yy[i];
/* 5987 */       this.weight[i] = ww[i];
/*      */     }
/*      */     
/*      */ 
/* 5991 */     switch (typeFlag) {
/* 5992 */     case 0:  start[0] = ests[0];
/* 5993 */       start[1] = ests[1];
/* 5994 */       start[2] = ests[2];
/* 5995 */       if (this.scaleFlag) {
/* 5996 */         start[3] = (1.0D / yScale);
/*      */       }
/* 5998 */       step[0] = (0.1D * start[0]);
/* 5999 */       if (step[0] == 0.0D) {
/* 6000 */         ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 6001 */         Double tempdd = null;
/* 6002 */         tempdd = (Double)ret0.get(2);
/* 6003 */         double xmax = tempdd.doubleValue();
/* 6004 */         if (xmax == 0.0D) {
/* 6005 */           tempdd = (Double)ret0.get(0);
/* 6006 */           xmax = tempdd.doubleValue();
/*      */         }
/* 6008 */         step[0] = (xmax * 0.1D);
/*      */       }
/* 6010 */       step[1] = (0.1D * start[1]);
/* 6011 */       step[2] = (0.1D * start[2]);
/* 6012 */       if (this.scaleFlag)
/* 6013 */         step[3] = (0.1D * start[3]);
/* 6014 */       break;
/*      */     case 1: 
/* 6016 */       start[0] = ests[0];
/* 6017 */       start[1] = ests[1];
/* 6018 */       if (this.scaleFlag) {
/* 6019 */         start[2] = (1.0D / yScale);
/*      */       }
/* 6021 */       step[0] = (0.1D * start[0]);
/* 6022 */       step[1] = (0.1D * start[1]);
/* 6023 */       if (this.scaleFlag) step[2] = (0.1D * start[2]);
/*      */       break;
/* 6025 */     case 2:  start[0] = ests[0];
/* 6026 */       if (this.scaleFlag) {
/* 6027 */         start[1] = (1.0D / yScale);
/*      */       }
/* 6029 */       step[0] = (0.1D * start[0]);
/* 6030 */       if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */       }
/*      */       break;
/*      */     }
/*      */     
/* 6035 */     if (this.frechetWeibull) {
/* 6036 */       FrechetFunctionOne ff = new FrechetFunctionOne();
/* 6037 */       ff.typeFlag = typeFlag;
/* 6038 */       ff.scaleOption = this.scaleFlag;
/* 6039 */       ff.scaleFactor = this.yScaleFactor;
/* 6040 */       Object regFun3 = ff;
/* 6041 */       nelderMead(regFun3, start, step, this.fTol, this.nMax);
/* 6042 */       if (allTest == 1)
/*      */       {
/* 6044 */         if (!this.supressPrint) { print();
/*      */         }
/* 6046 */         int flag = plotXY(ff);
/* 6047 */         if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */       }
/*      */     }
/*      */     else {
/* 6051 */       WeibullFunctionOne ff = new WeibullFunctionOne();
/* 6052 */       ff.typeFlag = typeFlag;
/* 6053 */       ff.scaleOption = this.scaleFlag;
/* 6054 */       ff.scaleFactor = this.yScaleFactor;
/* 6055 */       Object regFun3 = ff;
/* 6056 */       nelderMead(regFun3, start, step, this.fTol, this.nMax);
/* 6057 */       if (allTest == 1)
/*      */       {
/* 6059 */         if (!this.supressPrint) { print();
/*      */         }
/* 6061 */         int flag = plotXY(ff);
/* 6062 */         if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 6067 */     this.weightOpt = weightOptHold;
/* 6068 */     if (magCheck) {
/* 6069 */       for (int i = 0; i < this.nData; i++) {
/* 6070 */         this.yData[i] = (yy[i] / magScale);
/* 6071 */         if (this.weightOpt) this.weight[i] = (ww[i] / magScale);
/*      */       }
/*      */     }
/* 6074 */     if (ySignFlag) {
/* 6075 */       for (int i = 0; i < this.nData; i++) {
/* 6076 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean infinityCheck(double yPeak, int peaki)
/*      */   {
/* 6083 */     boolean flag = false;
/* 6084 */     if ((yPeak == Double.POSITIVE_INFINITY) || (yPeak == Double.NEGATIVE_INFINITY)) {
/* 6085 */       int ii = peaki + 1;
/* 6086 */       if (peaki == this.nData - 1) ii = peaki - 1;
/* 6087 */       this.xData[0][peaki] = this.xData[0][ii];
/* 6088 */       this.yData[peaki] = this.yData[ii];
/* 6089 */       this.weight[peaki] = this.weight[ii];
/* 6090 */       System.out.println("An infinty has been removed at point " + peaki);
/* 6091 */       flag = true;
/*      */     }
/* 6093 */     return flag;
/*      */   }
/*      */   
/*      */   public void reverseYsign(String ss)
/*      */   {
/* 6098 */     System.out.println("This implementation of the " + ss + " distributions takes only positive y values\n(noise taking low values below zero are allowed)");
/* 6099 */     System.out.println("All y values have been multiplied by -1 before fitting");
/* 6100 */     for (int i = 0; i < this.nData; i++) {
/* 6101 */       this.yData[i] = (-this.yData[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public double checkYallSmall(double yPeak, String ss)
/*      */   {
/* 6107 */     double magScale = 1.0D;
/* 6108 */     double recipYpeak = Fmath.truncate(1.0D / yPeak, 4);
/* 6109 */     if (yPeak < 1.0E-4D) {
/* 6110 */       System.out.println(ss + " fitting: The ordinate axis (y axis) has been rescaled by " + recipYpeak + " to reduce rounding errors");
/* 6111 */       for (int i = 0; i < this.nData; i++) {
/* 6112 */         this.yData[i] *= recipYpeak;
/* 6113 */         if (this.weightOpt) this.weight[i] *= recipYpeak;
/*      */       }
/* 6115 */       magScale = recipYpeak;
/*      */     }
/* 6117 */     return magScale;
/*      */   }
/*      */   
/*      */   public double calculateCumulativeValues(double[] cumX, double[] cumY, double[] cumW, ErrorProp[] cumYe, int peaki, double yPeak, double distribMode, String ss)
/*      */   {
/* 6122 */     cumX[0] = this.xData[0][0];
/* 6123 */     for (int i = 1; i < this.nData; i++) {
/* 6124 */       cumX[i] = this.xData[0][i];
/*      */     }
/*      */     
/* 6127 */     ErrorProp[] yE = ErrorProp.oneDarray(this.nData);
/* 6128 */     for (int i = 0; i < this.nData; i++) {
/* 6129 */       yE[i].reset(this.yData[i], this.weight[i]);
/*      */     }
/*      */     
/*      */ 
/* 6133 */     if (peaki != 0) {
/* 6134 */       if (peaki == this.nData - 1) {
/* 6135 */         System.out.println("The data does not cover a wide enough range of x values to fit to a " + ss + " distribution with any accuracy");
/* 6136 */         System.out.println("The regression will be attempted but you should treat any result with great caution");
/*      */       }
/* 6138 */       if ((this.yData[0] < this.yData[1] * 0.5D) && (this.yData[0] > distribMode * 0.02D)) {
/* 6139 */         ErrorProp x0 = new ErrorProp(0.0D, 0.0D);
/* 6140 */         x0 = yE[0].times(this.xData[0][1] - this.xData[0][0]);
/* 6141 */         x0 = x0.over(yE[1].minus(yE[0]));
/* 6142 */         x0 = ErrorProp.minus(this.xData[0][0], x0);
/* 6143 */         if (this.yData[0] >= 0.9D * yPeak) x0 = x0.plus(this.xData[0][0]).over(2.0D);
/* 6144 */         if (x0.getValue() < 0.0D) x0.reset(0.0D, 0.0D);
/* 6145 */         cumYe[0] = yE[0].over(2.0D);
/* 6146 */         cumYe[0] = cumYe[0].times(ErrorProp.minus(this.xData[0][0], x0));
/*      */       }
/*      */       else {
/* 6149 */         cumYe[0].reset(0.0D, this.weight[0]);
/*      */       }
/*      */     }
/*      */     else {
/* 6153 */       cumYe[0].reset(0.0D, this.weight[0]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6158 */     for (int i = 1; i < this.nData; i++) {
/* 6159 */       cumYe[i] = yE[i].plus(yE[(i - 1)]);
/* 6160 */       cumYe[i] = cumYe[i].over(2.0D);
/* 6161 */       cumYe[i] = cumYe[i].times(this.xData[0][i] - this.xData[0][(i - 1)]);
/* 6162 */       cumYe[i] = cumYe[i].plus(cumYe[(i - 1)]);
/*      */     }
/*      */     
/* 6165 */     ErrorProp cumYtotal = cumYe[(this.nData - 1)].copy();
/* 6166 */     if (peaki == this.nData - 1) {
/* 6167 */       cumYtotal = cumYtotal.times(2.0D);
/*      */ 
/*      */     }
/* 6170 */     else if ((this.yData[(this.nData - 1)] < this.yData[(this.nData - 2)] * 0.5D) && (this.yData[(this.nData - 1)] > distribMode * 0.02D)) {
/* 6171 */       ErrorProp xn = new ErrorProp();
/* 6172 */       xn = yE[(this.nData - 1)].times(this.xData[0][(this.nData - 2)] - this.xData[0][(this.nData - 1)]);
/* 6173 */       xn = xn.over(yE[(this.nData - 2)].minus(yE[(this.nData - 1)]));
/* 6174 */       xn = ErrorProp.minus(this.xData[0][(this.nData - 1)], xn);
/* 6175 */       if (this.yData[0] >= 0.9D * yPeak) xn = xn.plus(this.xData[0][(this.nData - 1)]).over(2.0D);
/* 6176 */       cumYtotal = cumYtotal.plus(ErrorProp.times(0.5D, yE[(this.nData - 1)].times(xn.minus(this.xData[0][(this.nData - 1)]))));
/*      */     }
/*      */     
/*      */ 
/* 6180 */     double yScale = 1.0D / cumYtotal.getValue();
/* 6181 */     for (int i = 0; i < this.nData; i++) {
/* 6182 */       cumYe[i] = cumYe[i].over(cumYtotal);
/*      */     }
/*      */     
/*      */ 
/* 6186 */     int jj = 0;
/* 6187 */     boolean test = true;
/* 6188 */     for (int i = 0; i < this.nData; i++) {
/* 6189 */       if (cumYe[i].getValue() <= 0.0D) {
/* 6190 */         if (i <= jj) {
/* 6191 */           test = true;
/* 6192 */           jj = i;
/* 6193 */           while (test) {
/* 6194 */             jj++;
/* 6195 */             if (jj >= this.nData) throw new ArithmeticException("all zero cumulative data!!");
/* 6196 */             if (cumYe[jj].getValue() > 0.0D) {
/* 6197 */               cumYe[i] = cumYe[jj].copy();
/* 6198 */               cumX[i] = cumX[jj];
/* 6199 */               test = false;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 6204 */         if (i == this.nData - 1) {
/* 6205 */           cumYe[i] = cumYe[(i - 1)].copy();
/* 6206 */           cumX[i] = cumX[(i - 1)];
/*      */         }
/*      */         else {
/* 6209 */           cumYe[i] = cumYe[(i - 1)].plus(cumYe[(i + 1)]);
/* 6210 */           cumYe[i] = cumYe[i].over(2.0D);
/* 6211 */           cumX[i] = ((cumX[(i - 1)] + cumX[(i + 1)]) / 2.0D);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6218 */     jj = this.nData - 1;
/* 6219 */     for (int i = this.nData - 1; i >= 0; i--) {
/* 6220 */       if (cumYe[i].getValue() >= 1.0D) {
/* 6221 */         if (i >= jj) {
/* 6222 */           test = true;
/* 6223 */           jj = this.nData - 1;
/* 6224 */           while (test) {
/* 6225 */             jj--;
/* 6226 */             if (jj < 0) throw new ArithmeticException("all unity cumulative data!!");
/* 6227 */             if (cumYe[jj].getValue() < 1.0D) {
/* 6228 */               cumYe[i] = cumYe[jj].copy();
/* 6229 */               cumX[i] = cumX[jj];
/* 6230 */               test = false;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 6235 */         if (i == 0) {
/* 6236 */           cumYe[i] = cumYe[(i + 1)].copy();
/* 6237 */           cumX[i] = cumX[(i + 1)];
/*      */         }
/*      */         else {
/* 6240 */           cumYe[i] = cumYe[(i - 1)].plus(cumYe[(i + 1)]);
/* 6241 */           cumYe[i] = cumYe[i].over(2.0D);
/* 6242 */           cumX[i] = ((cumX[(i - 1)] + cumX[(i + 1)]) / 2.0D);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 6247 */     return yScale;
/*      */   }
/*      */   
/*      */   public void weibull() {
/* 6251 */     fitWeibull(0, 0);
/*      */   }
/*      */   
/*      */   public void weibullPlot() {
/* 6255 */     fitWeibull(1, 0);
/*      */   }
/*      */   
/*      */   public void weibullTwoPar() {
/* 6259 */     fitWeibull(0, 1);
/*      */   }
/*      */   
/*      */   public void weibullTwoParPlot() {
/* 6263 */     fitWeibull(1, 1);
/*      */   }
/*      */   
/*      */   public void weibullStandard() {
/* 6267 */     fitWeibull(0, 2);
/*      */   }
/*      */   
/*      */   public void weibullStandardPlot() {
/* 6271 */     fitWeibull(1, 2);
/*      */   }
/*      */   
/*      */   protected void fitWeibull(int allTest, int typeFlag) {
/* 6275 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 6276 */     switch (typeFlag) {
/* 6277 */     case 0:  this.lastMethod = 16;
/* 6278 */       this.nTerms = 4;
/* 6279 */       break;
/* 6280 */     case 1:  this.lastMethod = 17;
/* 6281 */       this.nTerms = 3;
/* 6282 */       break;
/* 6283 */     case 2:  this.lastMethod = 18;
/* 6284 */       this.nTerms = 2;
/*      */     }
/*      */     
/* 6287 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 6288 */     this.frechetWeibull = false;
/* 6289 */     fitFrechetWeibull(allTest, typeFlag);
/*      */   }
/*      */   
/*      */   public void gumbelMin() {
/* 6293 */     fitGumbel(0, 0);
/*      */   }
/*      */   
/*      */   public void gumbelMinPlot() {
/* 6297 */     fitGumbel(1, 0);
/*      */   }
/*      */   
/*      */   public void gumbelMax() {
/* 6301 */     fitGumbel(0, 1);
/*      */   }
/*      */   
/* 6304 */   public void gumbelMaxPlot() { fitGumbel(1, 1); }
/*      */   
/*      */   public void gumbelMinOnePar()
/*      */   {
/* 6308 */     fitGumbel(0, 2);
/*      */   }
/*      */   
/*      */   public void gumbelMinOneParPlot() {
/* 6312 */     fitGumbel(1, 2);
/*      */   }
/*      */   
/*      */   public void gumbelMaxOnePar() {
/* 6316 */     fitGumbel(0, 3);
/*      */   }
/*      */   
/*      */   public void gumbelMaxOneParPlot() {
/* 6320 */     fitGumbel(1, 3);
/*      */   }
/*      */   
/*      */   public void gumbelMinStandard() {
/* 6324 */     fitGumbel(0, 4);
/*      */   }
/*      */   
/*      */   public void gumbelMinStandardPlot() {
/* 6328 */     fitGumbel(1, 4);
/*      */   }
/*      */   
/*      */   public void gumbelMaxStandard() {
/* 6332 */     fitGumbel(0, 5);
/*      */   }
/*      */   
/*      */   public void gumbelMaxStandardPlot() {
/* 6336 */     fitGumbel(1, 5);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void noParameters(String ss)
/*      */   {
/* 6342 */     System.out.println(ss + " Regression");
/* 6343 */     System.out.println("No parameters set for estimation");
/* 6344 */     System.out.println("Theoretical curve obtained");
/* 6345 */     String filename1 = "RegressOutput.txt";
/* 6346 */     String filename2 = "RegressOutputN.txt";
/* 6347 */     FileOutput fout = new FileOutput(filename1, 'n');
/* 6348 */     System.out.println("Results printed to the file " + filename2);
/* 6349 */     fout.dateAndTimeln(filename1);
/* 6350 */     fout.println("No parameters set for estimation");
/* 6351 */     switch (this.lastMethod) {
/* 6352 */     case 11:  fout.println("Minimal Standard Gumbel p(x) = exp(x)exp(-exp(x))");
/* 6353 */       for (int i = 0; i < this.nData; i++) this.yCalc[i] = (Math.exp(this.xData[0][i]) * Math.exp(-Math.exp(this.xData[0][i])));
/* 6354 */       break;
/* 6355 */     case 12:  fout.println("Maximal Standard Gumbel p(x) = exp(-x)exp(-exp(-x))");
/* 6356 */       for (int i = 0; i < this.nData; i++) this.yCalc[i] = (Math.exp(-this.xData[0][i]) * Math.exp(-Math.exp(-this.xData[0][i])));
/* 6357 */       break;
/* 6358 */     case 21:  fout.println("Standard Exponential p(x) = exp(-x)");
/* 6359 */       for (int i = 0; i < this.nData; i++) { this.yCalc[i] = Math.exp(-this.xData[0][i]);
/*      */       }
/*      */     }
/* 6362 */     this.sumOfSquares = 0.0D;
/* 6363 */     this.chiSquare = 0.0D;
/* 6364 */     double temp = 0.0D;
/* 6365 */     for (int i = 0; i < this.nData; i++) {
/* 6366 */       temp = Fmath.square(this.yData[i] - this.yCalc[i]);
/* 6367 */       this.sumOfSquares += temp;
/* 6368 */       this.chiSquare += temp / Fmath.square(this.weight[i]);
/*      */     }
/* 6370 */     double corrCoeff = Stat.corrCoeff(this.yData, this.yCalc);
/* 6371 */     fout.printtab("Correlation Coefficient");
/* 6372 */     fout.println(Fmath.truncate(corrCoeff, this.prec));
/* 6373 */     fout.printtab("Correlation Coefficient Probability");
/* 6374 */     fout.println(Fmath.truncate(1.0D - Stat.linearCorrCoeffProb(corrCoeff, this.degreesOfFreedom - 1), this.prec));
/*      */     
/* 6376 */     fout.printtab("Sum of Squares");
/* 6377 */     fout.println(Fmath.truncate(this.sumOfSquares, this.prec));
/* 6378 */     if ((this.weightOpt) || (this.trueFreq)) {
/* 6379 */       fout.printtab("Chi Square");
/* 6380 */       fout.println(Fmath.truncate(this.chiSquare, this.prec));
/* 6381 */       fout.printtab("chi square probability");
/* 6382 */       fout.println(Fmath.truncate(Stat.chiSquareProb(this.chiSquare, this.degreesOfFreedom - 1), this.prec));
/*      */     }
/* 6384 */     fout.println(" ");
/*      */     
/* 6386 */     fout.printtab("x", this.field);
/* 6387 */     fout.printtab("p(x) [expl]", this.field);
/* 6388 */     fout.printtab("p(x) [calc]", this.field);
/* 6389 */     fout.println("residual");
/*      */     
/* 6391 */     for (int i = 0; i < this.nData; i++) {
/* 6392 */       fout.printtab(Fmath.truncate(this.xData[0][i], this.prec), this.field);
/* 6393 */       fout.printtab(Fmath.truncate(this.yData[i], this.prec), this.field);
/* 6394 */       fout.printtab(Fmath.truncate(this.yCalc[i], this.prec), this.field);
/* 6395 */       fout.println(Fmath.truncate(this.yData[i] - this.yCalc[i], this.prec));
/*      */     }
/* 6397 */     fout.close();
/* 6398 */     plotXY();
/* 6399 */     if (!this.supressYYplot) plotYY();
/*      */   }
/*      */   
/*      */   protected void fitGumbel(int allTest, int typeFlag) {
/* 6403 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 6404 */     switch (typeFlag) {
/* 6405 */     case 0:  this.lastMethod = 7;
/* 6406 */       this.nTerms = 3;
/* 6407 */       break;
/* 6408 */     case 1:  this.lastMethod = 8;
/* 6409 */       this.nTerms = 3;
/* 6410 */       break;
/* 6411 */     case 2:  this.lastMethod = 9;
/* 6412 */       this.nTerms = 2;
/* 6413 */       break;
/* 6414 */     case 3:  this.lastMethod = 10;
/* 6415 */       this.nTerms = 2;
/* 6416 */       break;
/* 6417 */     case 4:  this.lastMethod = 11;
/* 6418 */       this.nTerms = 1;
/* 6419 */       break;
/* 6420 */     case 5:  this.lastMethod = 12;
/* 6421 */       this.nTerms = 1;
/*      */     }
/*      */     
/* 6424 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 6425 */     this.zeroCheck = false;
/* 6426 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 6427 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 6428 */     if (this.nTerms == 0) {
/* 6429 */       noParameters("Gumbel");
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 6435 */       sort(this.xData[0], this.yData, this.weight);
/*      */       
/*      */ 
/* 6438 */       Double tempd = null;
/* 6439 */       ArrayList<Object> retY = dataSign(this.yData);
/* 6440 */       tempd = (Double)retY.get(4);
/* 6441 */       double yPeak = tempd.doubleValue();
/* 6442 */       boolean yFlag = false;
/*      */       
/* 6444 */       if (yPeak < 0.0D) {
/* 6445 */         System.out.println("Regression.fitGumbel(): This implementation of the Gumbel distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 6446 */         System.out.println("All y values have been multiplied by -1 before fitting");
/* 6447 */         for (int i = 0; i < this.nData; i++) {
/* 6448 */           this.yData[i] = (-this.yData[i]);
/*      */         }
/* 6450 */         retY = dataSign(this.yData);
/* 6451 */         yFlag = true;
/*      */       }
/*      */       
/*      */ 
/* 6455 */       ArrayList<Object> retX = dataSign(this.xData[0]);
/* 6456 */       Integer tempi = null;
/*      */       
/*      */ 
/* 6459 */       tempi = (Integer)retY.get(5);
/* 6460 */       int peaki = tempi.intValue();
/* 6461 */       double distribMode = this.xData[0][peaki];
/*      */       
/*      */ 
/* 6464 */       double sd = halfWidth(this.xData[0], this.yData);
/*      */       
/*      */ 
/*      */ 
/* 6468 */       double[] start = new double[this.nTerms];
/* 6469 */       double[] step = new double[this.nTerms];
/* 6470 */       switch (typeFlag) {
/*      */       case 0: 
/*      */       case 1: 
/* 6473 */         start[0] = distribMode;
/* 6474 */         start[1] = (sd * Math.sqrt(6.0D) / 3.141592653589793D);
/* 6475 */         if (this.scaleFlag) {
/* 6476 */           start[2] = (yPeak * start[1] * Math.exp(1.0D));
/*      */         }
/* 6478 */         step[0] = (0.1D * start[0]);
/* 6479 */         if (step[0] == 0.0D) {
/* 6480 */           ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 6481 */           Double tempdd = null;
/* 6482 */           tempdd = (Double)ret0.get(2);
/* 6483 */           double xmax = tempdd.doubleValue();
/* 6484 */           if (xmax == 0.0D) {
/* 6485 */             tempdd = (Double)ret0.get(0);
/* 6486 */             xmax = tempdd.doubleValue();
/*      */           }
/* 6488 */           step[0] = (xmax * 0.1D);
/*      */         }
/* 6490 */         step[1] = (0.1D * start[1]);
/* 6491 */         if (this.scaleFlag) { step[2] = (0.1D * start[2]);
/*      */         }
/*      */         
/* 6494 */         addConstraint(1, -1, 0.0D);
/* 6495 */         break;
/*      */       case 2: 
/*      */       case 3: 
/* 6498 */         start[0] = (sd * Math.sqrt(6.0D) / 3.141592653589793D);
/* 6499 */         if (this.scaleFlag) {
/* 6500 */           start[1] = (yPeak * start[0] * Math.exp(1.0D));
/*      */         }
/* 6502 */         step[0] = (0.1D * start[0]);
/* 6503 */         if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */         }
/* 6505 */         addConstraint(0, -1, 0.0D);
/* 6506 */         break;
/*      */       case 4: 
/*      */       case 5: 
/* 6509 */         if (this.scaleFlag) {
/* 6510 */           start[0] = (yPeak * Math.exp(1.0D));
/* 6511 */           step[0] = (0.1D * start[0]);
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/* 6517 */       GumbelFunction ff = new GumbelFunction();
/*      */       
/*      */ 
/* 6520 */       ff.typeFlag = typeFlag;
/*      */       
/*      */ 
/* 6523 */       ff.scaleOption = this.scaleFlag;
/* 6524 */       ff.scaleFactor = this.yScaleFactor;
/*      */       
/* 6526 */       if (typeFlag < 4)
/*      */       {
/*      */ 
/* 6529 */         Object regFun3 = ff;
/* 6530 */         nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */         
/* 6532 */         if (allTest == 1)
/*      */         {
/* 6534 */           if (!this.supressPrint) { print();
/*      */           }
/*      */           
/* 6537 */           int flag = plotXY(ff);
/* 6538 */           if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 6543 */         double[][] xxx = new double[1][this.nData];
/* 6544 */         double aa = 1.0D;
/* 6545 */         if (typeFlag == 5) aa = -1.0D;
/* 6546 */         for (int i = 0; i < this.nData; i++) {
/* 6547 */           xxx[0][i] = (Math.exp(aa * this.xData[0][i]) * Math.exp(-Math.exp(aa * this.xData[0][i])));
/*      */         }
/*      */         
/*      */ 
/* 6551 */         this.linNonLin = true;
/* 6552 */         generalLinear(xxx);
/*      */         
/* 6554 */         if (!this.supressPrint) print();
/* 6555 */         if (!this.supressYYplot) plotYY();
/* 6556 */         plotXY();
/*      */         
/* 6558 */         this.linNonLin = false;
/*      */       }
/*      */       
/*      */ 
/* 6562 */       if (yFlag)
/*      */       {
/* 6564 */         for (int i = 0; i < this.nData - 1; i++) {
/* 6565 */           this.yData[i] = (-this.yData[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected static void sort(double[] x, double[] y, double[] w)
/*      */   {
/* 6574 */     int index = 0;
/* 6575 */     int lastIndex = -1;
/* 6576 */     int n = x.length;
/* 6577 */     double holdx = 0.0D;
/* 6578 */     double holdy = 0.0D;
/* 6579 */     double holdw = 0.0D;
/*      */     
/* 6581 */     while (lastIndex < n - 1) {
/* 6582 */       index = lastIndex + 1;
/* 6583 */       for (int i = lastIndex + 2; i < n; i++) {
/* 6584 */         if (x[i] < x[index]) {
/* 6585 */           index = i;
/*      */         }
/*      */       }
/* 6588 */       lastIndex++;
/* 6589 */       holdx = x[index];
/* 6590 */       x[index] = x[lastIndex];
/* 6591 */       x[lastIndex] = holdx;
/* 6592 */       holdy = y[index];
/* 6593 */       y[index] = y[lastIndex];
/* 6594 */       y[lastIndex] = holdy;
/* 6595 */       holdw = w[index];
/* 6596 */       w[index] = w[lastIndex];
/* 6597 */       w[lastIndex] = holdw;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static double halfWidth(double[] xData, double[] yData)
/*      */   {
/* 6603 */     double ymax = yData[0];
/* 6604 */     int imax = 0;
/* 6605 */     int n = xData.length;
/*      */     
/* 6607 */     for (int i = 1; i < n; i++) {
/* 6608 */       if (yData[i] > ymax) {
/* 6609 */         ymax = yData[i];
/* 6610 */         imax = i;
/*      */       }
/*      */     }
/* 6613 */     ymax /= 2.0D;
/*      */     
/* 6615 */     double halflow = -1.0D;
/* 6616 */     double temp = -1.0D;
/* 6617 */     int ihl = -1;
/* 6618 */     if (imax > 0) {
/* 6619 */       ihl = imax - 1;
/* 6620 */       halflow = Math.abs(ymax - yData[ihl]);
/* 6621 */       for (int i = imax - 2; i >= 0; i--) {
/* 6622 */         temp = Math.abs(ymax - yData[i]);
/* 6623 */         if (temp < halflow) {
/* 6624 */           halflow = temp;
/* 6625 */           ihl = i;
/*      */         }
/*      */       }
/* 6628 */       halflow = Math.abs(xData[ihl] - xData[imax]);
/*      */     }
/*      */     
/* 6631 */     double halfhigh = -1.0D;
/* 6632 */     temp = -1.0D;
/* 6633 */     int ihh = -1;
/* 6634 */     if (imax < n - 1) {
/* 6635 */       ihh = imax + 1;
/* 6636 */       halfhigh = Math.abs(ymax - yData[ihh]);
/* 6637 */       for (int i = imax + 2; i < n; i++) {
/* 6638 */         temp = Math.abs(ymax - yData[i]);
/* 6639 */         if (temp < halfhigh) {
/* 6640 */           halfhigh = temp;
/* 6641 */           ihh = i;
/*      */         }
/*      */       }
/* 6644 */       halfhigh = Math.abs(xData[ihh] - xData[imax]);
/*      */     }
/*      */     
/* 6647 */     double halfw = 0.0D;
/* 6648 */     int nd = 0;
/* 6649 */     if (ihl != -1) {
/* 6650 */       halfw += halflow;
/* 6651 */       nd++;
/*      */     }
/* 6653 */     if (ihh != -1) {
/* 6654 */       halfw += halfhigh;
/* 6655 */       nd++;
/*      */     }
/* 6657 */     halfw /= nd;
/*      */     
/* 6659 */     return halfw;
/*      */   }
/*      */   
/*      */   public void exponential() {
/* 6663 */     fitExponential(0, 0);
/*      */   }
/*      */   
/*      */   public void exponentialPlot() {
/* 6667 */     fitExponential(1, 0);
/*      */   }
/*      */   
/*      */   public void exponentialOnePar() {
/* 6671 */     fitExponential(0, 1);
/*      */   }
/*      */   
/*      */   public void exponentialOneParPlot() {
/* 6675 */     fitExponential(1, 1);
/*      */   }
/*      */   
/*      */   public void exponentialStandard() {
/* 6679 */     fitExponential(0, 2);
/*      */   }
/*      */   
/*      */   public void exponentialStandardPlot() {
/* 6683 */     fitExponential(1, 2);
/*      */   }
/*      */   
/*      */   protected void fitExponential(int allTest, int typeFlag) {
/* 6687 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 6688 */     switch (typeFlag) {
/* 6689 */     case 0:  this.lastMethod = 19;
/* 6690 */       this.nTerms = 3;
/* 6691 */       break;
/* 6692 */     case 1:  this.lastMethod = 20;
/* 6693 */       this.nTerms = 2;
/* 6694 */       break;
/* 6695 */     case 2:  this.lastMethod = 21;
/* 6696 */       this.nTerms = 1;
/*      */     }
/*      */     
/* 6699 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 6700 */     this.linNonLin = false;
/* 6701 */     this.zeroCheck = false;
/* 6702 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 6703 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 6704 */     if (this.nTerms == 0) {
/* 6705 */       noParameters("Exponential");
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 6710 */       double[] xx = new double[this.nData];
/* 6711 */       double[] yy = new double[this.nData];
/* 6712 */       double[] ww = new double[this.nData];
/*      */       
/* 6714 */       for (int i = 0; i < this.nData; i++) {
/* 6715 */         xx[i] = this.xData[0][i];
/* 6716 */         yy[i] = this.yData[i];
/* 6717 */         ww[i] = this.weight[i];
/*      */       }
/*      */       
/*      */ 
/* 6721 */       sort(this.xData[0], this.yData, this.weight);
/*      */       
/*      */ 
/* 6724 */       Double tempd = null;
/* 6725 */       ArrayList<Object> retY = dataSign(this.yData);
/* 6726 */       tempd = (Double)retY.get(4);
/* 6727 */       double yPeak = tempd.doubleValue();
/* 6728 */       Integer tempi = null;
/* 6729 */       tempi = (Integer)retY.get(5);
/* 6730 */       int peaki = tempi.intValue();
/*      */       
/*      */ 
/* 6733 */       String ss = "Exponential";
/* 6734 */       boolean ySignFlag = false;
/* 6735 */       if (yPeak < 0.0D) {
/* 6736 */         reverseYsign(ss);
/* 6737 */         retY = dataSign(this.yData);
/* 6738 */         yPeak = -yPeak;
/* 6739 */         ySignFlag = true;
/*      */       }
/*      */       
/*      */ 
/* 6743 */       boolean magCheck = false;
/* 6744 */       double magScale = checkYallSmall(yPeak, ss);
/* 6745 */       if (magScale != 1.0D) {
/* 6746 */         magCheck = true;
/* 6747 */         yPeak = 1.0D;
/*      */       }
/*      */       
/*      */ 
/* 6751 */       ArrayList<Object> retX = dataSign(this.xData[0]);
/* 6752 */       tempd = (Double)retX.get(0);
/* 6753 */       double xMin = tempd.doubleValue();
/*      */       
/*      */ 
/* 6756 */       double yE = yPeak / Math.exp(1.0D);
/* 6757 */       if (this.yData[0] < yPeak) yE = (yPeak + this.yData[0]) / (2.0D * Math.exp(1.0D));
/* 6758 */       double yDiff = Math.abs(this.yData[0] - yE);
/* 6759 */       double yTest = 0.0D;
/* 6760 */       int iE = 0;
/* 6761 */       for (int i = 1; i < this.nData; i++) {
/* 6762 */         yTest = Math.abs(this.yData[i] - yE);
/* 6763 */         if (yTest < yDiff) {
/* 6764 */           yDiff = yTest;
/* 6765 */           iE = i;
/*      */         }
/*      */       }
/* 6768 */       double sigma = this.xData[0][iE] - this.xData[0][0];
/*      */       
/*      */ 
/* 6771 */       double[] start = new double[this.nTerms];
/* 6772 */       double[] step = new double[this.nTerms];
/*      */       
/*      */ 
/* 6775 */       switch (typeFlag) {
/* 6776 */       case 0:  start[0] = (xMin * 0.9D);
/* 6777 */         start[1] = sigma;
/* 6778 */         if (this.scaleFlag) {
/* 6779 */           start[2] = (yPeak * sigma);
/*      */         }
/* 6781 */         step[0] = (0.1D * start[0]);
/* 6782 */         if (step[0] == 0.0D) {
/* 6783 */           ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 6784 */           Double tempdd = null;
/* 6785 */           tempdd = (Double)ret0.get(2);
/* 6786 */           double xmax = tempdd.doubleValue();
/* 6787 */           if (xmax == 0.0D) {
/* 6788 */             tempdd = (Double)ret0.get(0);
/* 6789 */             xmax = tempdd.doubleValue();
/*      */           }
/* 6791 */           step[0] = (xmax * 0.1D);
/*      */         }
/* 6793 */         step[1] = (0.1D * start[1]);
/* 6794 */         if (this.scaleFlag) step[2] = (0.1D * start[2]);
/*      */         break;
/* 6796 */       case 1:  start[0] = sigma;
/* 6797 */         if (this.scaleFlag) {
/* 6798 */           start[1] = (yPeak * sigma);
/*      */         }
/* 6800 */         step[0] = (0.1D * start[0]);
/* 6801 */         if (this.scaleFlag) step[1] = (0.1D * start[1]);
/*      */         break;
/* 6803 */       case 2:  if (this.scaleFlag) {
/* 6804 */           start[0] = yPeak;
/* 6805 */           step[0] = (0.1D * start[0]);
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/* 6811 */       ExponentialFunction ff = new ExponentialFunction();
/* 6812 */       ff.typeFlag = typeFlag;
/* 6813 */       ff.scaleOption = this.scaleFlag;
/* 6814 */       ff.scaleFactor = this.yScaleFactor;
/* 6815 */       Object regFun3 = ff;
/* 6816 */       nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */       
/* 6818 */       if (allTest == 1)
/*      */       {
/* 6820 */         if (!this.supressPrint) { print();
/*      */         }
/* 6822 */         int flag = plotXY(ff);
/* 6823 */         if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */         }
/*      */       }
/*      */       
/* 6827 */       if (magCheck) {
/* 6828 */         for (int i = 0; i < this.nData; i++) {
/* 6829 */           this.yData[i] = (yy[i] / magScale);
/* 6830 */           if (this.weightOpt) this.weight[i] = (ww[i] / magScale);
/*      */         }
/*      */       }
/* 6833 */       if (ySignFlag) {
/* 6834 */         for (int i = 0; i < this.nData; i++) {
/* 6835 */           this.yData[i] = (-this.yData[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void checkZeroNeg(double[] xx, double[] yy, double[] ww)
/*      */   {
/* 6843 */     int jj = 0;
/* 6844 */     boolean test = true;
/* 6845 */     for (int i = 0; i < this.nData; i++) {
/* 6846 */       if (yy[i] <= 0.0D) {
/* 6847 */         if (i <= jj) {
/* 6848 */           test = true;
/* 6849 */           jj = i;
/* 6850 */           while (test) {
/* 6851 */             jj++;
/* 6852 */             if (jj >= this.nData) throw new ArithmeticException("all zero cumulative data!!");
/* 6853 */             if (yy[jj] > 0.0D) {
/* 6854 */               yy[i] = yy[jj];
/* 6855 */               xx[i] = xx[jj];
/* 6856 */               ww[i] = ww[jj];
/* 6857 */               test = false;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 6862 */         if (i == this.nData - 1) {
/* 6863 */           yy[i] = yy[(i - 1)];
/* 6864 */           xx[i] = xx[(i - 1)];
/* 6865 */           ww[i] = ww[(i - 1)];
/*      */         }
/*      */         else {
/* 6868 */           yy[i] = ((yy[(i - 1)] + yy[(i + 1)]) / 2.0D);
/* 6869 */           xx[i] = ((xx[(i - 1)] + xx[(i + 1)]) / 2.0D);
/* 6870 */           ww[i] = ((ww[(i - 1)] + ww[(i + 1)]) / 2.0D);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void rayleigh()
/*      */   {
/* 6878 */     fitRayleigh(0, 0);
/*      */   }
/*      */   
/*      */   public void rayleighPlot() {
/* 6882 */     fitRayleigh(1, 0);
/*      */   }
/*      */   
/*      */   protected void fitRayleigh(int allTest, int typeFlag) {
/* 6886 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 6887 */     this.lastMethod = 22;
/* 6888 */     this.nTerms = 2;
/* 6889 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 6890 */     this.linNonLin = false;
/* 6891 */     this.zeroCheck = false;
/* 6892 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 6893 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/*      */ 
/* 6897 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 6900 */     Double tempd = null;
/* 6901 */     ArrayList<Object> retY = dataSign(this.yData);
/* 6902 */     tempd = (Double)retY.get(4);
/* 6903 */     double yPeak = tempd.doubleValue();
/* 6904 */     Integer tempi = null;
/* 6905 */     tempi = (Integer)retY.get(5);
/* 6906 */     int peaki = tempi.intValue();
/*      */     
/*      */ 
/* 6909 */     String ss = "Rayleigh";
/* 6910 */     boolean ySignFlag = false;
/* 6911 */     if (yPeak < 0.0D) {
/* 6912 */       reverseYsign(ss);
/* 6913 */       retY = dataSign(this.yData);
/* 6914 */       yPeak = -yPeak;
/* 6915 */       ySignFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 6919 */     boolean magCheck = false;
/* 6920 */     double magScale = checkYallSmall(yPeak, ss);
/* 6921 */     if (magScale != 1.0D) {
/* 6922 */       magCheck = true;
/* 6923 */       yPeak = 1.0D;
/*      */     }
/*      */     
/*      */ 
/* 6927 */     double[] xx = new double[this.nData];
/* 6928 */     double[] yy = new double[this.nData];
/* 6929 */     double[] ww = new double[this.nData];
/*      */     
/* 6931 */     for (int i = 0; i < this.nData; i++) {
/* 6932 */       xx[i] = this.xData[0][i];
/* 6933 */       yy[i] = this.yData[i];
/* 6934 */       ww[i] = this.weight[i];
/*      */     }
/*      */     
/*      */ 
/* 6938 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 6939 */     tempd = (Double)retX.get(0);
/* 6940 */     double xMin = tempd.doubleValue();
/*      */     
/*      */ 
/* 6943 */     tempd = (Double)retX.get(2);
/* 6944 */     double xMax = tempd.doubleValue();
/*      */     
/*      */ 
/* 6947 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 6950 */     double sd = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
/*      */     
/*      */ 
/* 6953 */     double[] cumX = new double[this.nData];
/* 6954 */     double[] cumY = new double[this.nData];
/* 6955 */     double[] cumW = new double[this.nData];
/* 6956 */     ErrorProp[] cumYe = ErrorProp.oneDarray(this.nData);
/* 6957 */     double yScale = calculateCumulativeValues(cumX, cumY, cumW, cumYe, peaki, yPeak, distribMode, ss);
/*      */     
/*      */ 
/* 6960 */     for (int i = 0; i < this.nData; i++) {
/* 6961 */       cumYe[i] = ErrorProp.minus(1.0D, cumYe[i]);
/* 6962 */       cumYe[i] = ErrorProp.over(1.0D, cumYe[i]);
/* 6963 */       cumYe[i] = ErrorProp.log(cumYe[i]);
/* 6964 */       cumY[i] = cumYe[i].getValue();
/* 6965 */       cumW[i] = cumYe[i].getError();
/*      */     }
/*      */     
/*      */ 
/* 6969 */     for (int i = 0; i < this.nData; i++) {
/* 6970 */       this.xData[0][i] = cumX[i];
/* 6971 */       this.yData[i] = cumY[i];
/* 6972 */       this.weight[i] = cumW[i];
/*      */     }
/* 6974 */     boolean weightOptHold = this.weightOpt;
/* 6975 */     this.weightOpt = true;
/*      */     
/*      */ 
/*      */ 
/* 6979 */     this.statFlag = false;
/*      */     
/*      */ 
/* 6982 */     double[] start = new double[this.nTerms];
/* 6983 */     double[] step = new double[this.nTerms];
/* 6984 */     for (int i = 0; i < this.nTerms; i++) {
/* 6985 */       start[i] = 1.0D;
/* 6986 */       step[i] = 0.2D;
/*      */     }
/* 6988 */     start[0] = sd;
/* 6989 */     step[0] = 0.2D;
/* 6990 */     addConstraint(0, -1, 0.0D);
/*      */     
/*      */ 
/* 6993 */     RayleighFunctionTwo f = new RayleighFunctionTwo();
/* 6994 */     Object regFun2 = f;
/* 6995 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/*      */ 
/* 6998 */     double[] ests = (double[])this.best.clone();
/*      */     
/*      */ 
/* 7001 */     this.statFlag = true;
/*      */     
/*      */ 
/* 7004 */     this.weightOpt = weightOptHold;
/* 7005 */     for (int i = 0; i < this.nData; i++) {
/* 7006 */       this.xData[0][i] = xx[i];
/* 7007 */       this.yData[i] = yy[i];
/* 7008 */       this.weight[i] = ww[i];
/*      */     }
/*      */     
/*      */ 
/* 7012 */     start[0] = ests[0];
/* 7013 */     if (this.scaleFlag) {
/* 7014 */       start[1] = (1.0D / yScale);
/*      */     }
/* 7016 */     step[0] = (0.1D * start[0]);
/* 7017 */     if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */     }
/*      */     
/*      */ 
/* 7021 */     RayleighFunctionOne ff = new RayleighFunctionOne();
/* 7022 */     ff.scaleOption = this.scaleFlag;
/* 7023 */     ff.scaleFactor = this.yScaleFactor;
/* 7024 */     Object regFun3 = ff;
/* 7025 */     nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */     
/* 7027 */     if (allTest == 1)
/*      */     {
/* 7029 */       if (!this.supressPrint) { print();
/*      */       }
/* 7031 */       int flag = plotXY(ff);
/* 7032 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/*      */     
/* 7036 */     if (magCheck) {
/* 7037 */       for (int i = 0; i < this.nData; i++) {
/* 7038 */         this.yData[i] = (yy[i] / magScale);
/* 7039 */         if (this.weightOpt) this.weight[i] = (ww[i] / magScale);
/*      */       }
/*      */     }
/* 7042 */     if (ySignFlag) {
/* 7043 */       for (int i = 0; i < this.nData; i++) {
/* 7044 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void paretoShifted()
/*      */   {
/* 7051 */     fitPareto(0, 3);
/*      */   }
/*      */   
/*      */   public void paretoThreePar() {
/* 7055 */     fitPareto(0, 3);
/*      */   }
/*      */   
/*      */   public void paretoShiftedPlot() {
/* 7059 */     fitPareto(1, 3);
/*      */   }
/*      */   
/* 7062 */   public void paretoThreeParPlot() { fitPareto(1, 3); }
/*      */   
/*      */ 
/*      */   public void paretoTwoPar()
/*      */   {
/* 7067 */     fitPareto(0, 2);
/*      */   }
/*      */   
/*      */   public void pareto() {
/* 7071 */     fitPareto(0, 2);
/*      */   }
/*      */   
/*      */   public void paretoTwoParPlot() {
/* 7075 */     fitPareto(1, 2);
/*      */   }
/*      */   
/*      */   public void paretoPlot() {
/* 7079 */     fitPareto(1, 2);
/*      */   }
/*      */   
/*      */   public void paretoOnePar()
/*      */   {
/* 7084 */     fitPareto(0, 1);
/*      */   }
/*      */   
/*      */   public void paretoOneParPlot() {
/* 7088 */     fitPareto(1, 1);
/*      */   }
/*      */   
/*      */   protected void fitPareto(int allTest, int typeFlag)
/*      */   {
/* 7093 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7094 */     switch (typeFlag) {
/* 7095 */     case 3:  this.lastMethod = 29;
/* 7096 */       this.nTerms = 4;
/* 7097 */       break;
/* 7098 */     case 2:  this.lastMethod = 23;
/* 7099 */       this.nTerms = 3;
/* 7100 */       break;
/* 7101 */     case 1:  this.lastMethod = 24;
/* 7102 */       this.nTerms = 2;
/*      */     }
/*      */     
/*      */     
/* 7106 */     if (!this.scaleFlag) this.nTerms -= 1;
/* 7107 */     this.linNonLin = false;
/* 7108 */     this.zeroCheck = false;
/* 7109 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7110 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/* 7111 */     String ss = "Pareto";
/*      */     
/*      */ 
/* 7114 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7117 */     Double tempd = null;
/* 7118 */     ArrayList<Object> retY = dataSign(this.yData);
/* 7119 */     tempd = (Double)retY.get(4);
/* 7120 */     double yPeak = tempd.doubleValue();
/* 7121 */     Integer tempi = null;
/* 7122 */     tempi = (Integer)retY.get(5);
/* 7123 */     int peaki = tempi.intValue();
/*      */     
/*      */ 
/* 7126 */     if (infinityCheck(yPeak, peaki)) {
/* 7127 */       retY = dataSign(this.yData);
/* 7128 */       tempd = (Double)retY.get(4);
/* 7129 */       yPeak = tempd.doubleValue();
/* 7130 */       tempi = null;
/* 7131 */       tempi = (Integer)retY.get(5);
/* 7132 */       peaki = tempi.intValue();
/*      */     }
/*      */     
/*      */ 
/* 7136 */     boolean ySignFlag = false;
/* 7137 */     if (yPeak < 0.0D) {
/* 7138 */       reverseYsign(ss);
/* 7139 */       retY = dataSign(this.yData);
/* 7140 */       yPeak = -yPeak;
/* 7141 */       ySignFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 7145 */     boolean magCheck = false;
/* 7146 */     double magScale = checkYallSmall(yPeak, ss);
/* 7147 */     if (magScale != 1.0D) {
/* 7148 */       magCheck = true;
/* 7149 */       yPeak = 1.0D;
/*      */     }
/*      */     
/*      */ 
/* 7153 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 7154 */     tempd = (Double)retX.get(0);
/* 7155 */     double xMin = tempd.doubleValue();
/*      */     
/*      */ 
/* 7158 */     tempd = (Double)retX.get(2);
/* 7159 */     double xMax = tempd.doubleValue();
/*      */     
/*      */ 
/* 7162 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 7165 */     double sd = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
/*      */     
/*      */ 
/* 7168 */     double[] xx = new double[this.nData];
/* 7169 */     double[] yy = new double[this.nData];
/* 7170 */     double[] ww = new double[this.nData];
/*      */     
/* 7172 */     for (int i = 0; i < this.nData; i++) {
/* 7173 */       xx[i] = this.xData[0][i];
/* 7174 */       yy[i] = this.yData[i];
/* 7175 */       ww[i] = this.weight[i];
/*      */     }
/*      */     
/*      */ 
/* 7179 */     double[] cumX = new double[this.nData];
/* 7180 */     double[] cumY = new double[this.nData];
/* 7181 */     double[] cumW = new double[this.nData];
/* 7182 */     ErrorProp[] cumYe = ErrorProp.oneDarray(this.nData);
/* 7183 */     double yScale = calculateCumulativeValues(cumX, cumY, cumW, cumYe, peaki, yPeak, distribMode, ss);
/*      */     
/*      */ 
/* 7186 */     for (int i = 0; i < this.nData; i++) {
/* 7187 */       cumYe[i] = ErrorProp.minus(1.0D, cumYe[i]);
/* 7188 */       cumY[i] = cumYe[i].getValue();
/* 7189 */       cumW[i] = cumYe[i].getError();
/*      */     }
/*      */     
/*      */ 
/* 7193 */     for (int i = 0; i < this.nData; i++) {
/* 7194 */       this.xData[0][i] = cumX[i];
/* 7195 */       this.yData[i] = cumY[i];
/* 7196 */       this.weight[i] = cumW[i];
/*      */     }
/* 7198 */     boolean weightOptHold = this.weightOpt;
/* 7199 */     this.weightOpt = true;
/*      */     
/*      */ 
/*      */ 
/* 7203 */     this.statFlag = false;
/*      */     
/*      */ 
/* 7206 */     double[] start = new double[this.nTerms];
/* 7207 */     double[] step = new double[this.nTerms];
/* 7208 */     for (int i = 0; i < this.nTerms; i++) {
/* 7209 */       start[i] = 1.0D;
/* 7210 */       step[i] = 0.2D;
/*      */     }
/* 7212 */     switch (typeFlag) {
/* 7213 */     case 3:  start[0] = 2.0D;
/* 7214 */       start[1] = (xMin * 0.9D);
/* 7215 */       if (xMin < 0.0D) {
/* 7216 */         start[2] = (-xMin * 1.1D);
/*      */       }
/*      */       else {
/* 7219 */         start[2] = (xMin * 0.01D);
/*      */       }
/* 7221 */       if (start[1] < 0.0D) start[1] = 0.0D;
/* 7222 */       step[0] = (0.2D * start[0]);
/* 7223 */       step[1] = (0.2D * start[1]);
/* 7224 */       if (step[1] == 0.0D) {
/* 7225 */         double xmax = xMax;
/* 7226 */         if (xmax == 0.0D) {
/* 7227 */           xmax = xMin;
/*      */         }
/* 7229 */         step[1] = (xmax * 0.1D);
/*      */       }
/* 7231 */       addConstraint(0, -1, 0.0D);
/* 7232 */       addConstraint(1, -1, 0.0D);
/* 7233 */       addConstraint(1, 1, xMin);
/* 7234 */       break;
/* 7235 */     case 2:  if (xMin < 0.0D) System.out.println("Method: FitParetoTwoPar/FitParetoTwoParPlot\nNegative data values present\nFitParetoShifted/FitParetoShiftedPlot would have been more appropriate");
/* 7236 */       start[0] = 2.0D;
/* 7237 */       start[1] = (xMin * 0.9D);
/* 7238 */       if (start[1] < 0.0D) start[1] = 0.0D;
/* 7239 */       step[0] = (0.2D * start[0]);
/* 7240 */       step[1] = (0.2D * start[1]);
/* 7241 */       if (step[1] == 0.0D) {
/* 7242 */         double xmax = xMax;
/* 7243 */         if (xmax == 0.0D) {
/* 7244 */           xmax = xMin;
/*      */         }
/* 7246 */         step[1] = (xmax * 0.1D);
/*      */       }
/* 7248 */       addConstraint(0, -1, 0.0D);
/* 7249 */       addConstraint(1, -1, 0.0D);
/* 7250 */       break;
/* 7251 */     case 1:  if (xMin < 0.0D) System.out.println("Method: FitParetoOnePar/FitParetoOneParPlot\nNegative data values present\nFitParetoShifted/FitParetoShiftedPlot would have been more appropriate");
/* 7252 */       start[0] = 2.0D;
/* 7253 */       step[0] = (0.2D * start[0]);
/* 7254 */       addConstraint(0, -1, 0.0D);
/* 7255 */       addConstraint(1, -1, 0.0D);
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 7260 */     ParetoFunctionTwo f = new ParetoFunctionTwo();
/* 7261 */     f.typeFlag = typeFlag;
/* 7262 */     Object regFun2 = f;
/* 7263 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/*      */ 
/* 7266 */     double[] ests = (double[])this.best.clone();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7272 */     this.statFlag = true;
/*      */     
/*      */ 
/* 7275 */     this.weightOpt = weightOptHold;
/* 7276 */     for (int i = 0; i < this.nData; i++) {
/* 7277 */       this.xData[0][i] = xx[i];
/* 7278 */       this.yData[i] = yy[i];
/* 7279 */       this.weight[i] = ww[i];
/*      */     }
/*      */     
/*      */ 
/* 7283 */     switch (typeFlag) {
/* 7284 */     case 3:  start[0] = ests[0];
/* 7285 */       if (start[0] <= 0.0D) {
/* 7286 */         if (start[0] == 0.0D) {
/* 7287 */           start[0] = 1.0D;
/*      */         }
/*      */         else {
/* 7290 */           start[0] = Math.min(1.0D, -start[0]);
/*      */         }
/*      */       }
/* 7293 */       start[1] = ests[1];
/* 7294 */       if (start[1] <= 0.0D) {
/* 7295 */         if (start[1] == 0.0D) {
/* 7296 */           start[1] = 1.0D;
/*      */         }
/*      */         else {
/* 7299 */           start[1] = Math.min(1.0D, -start[1]);
/*      */         }
/*      */       }
/* 7302 */       start[2] = ests[2];
/* 7303 */       if (this.scaleFlag) {
/* 7304 */         start[3] = (1.0D / yScale);
/*      */       }
/* 7306 */       step[0] = (0.1D * start[0]);
/* 7307 */       step[1] = (0.1D * start[1]);
/* 7308 */       if (step[1] == 0.0D) {
/* 7309 */         double xmax = xMax;
/* 7310 */         if (xmax == 0.0D) {
/* 7311 */           xmax = xMin;
/*      */         }
/* 7313 */         step[1] = (xmax * 0.1D);
/*      */       }
/* 7315 */       if (this.scaleFlag) step[2] = (0.1D * start[2]);
/*      */       break;
/* 7317 */     case 2:  start[0] = ests[0];
/* 7318 */       if (start[0] <= 0.0D) {
/* 7319 */         if (start[0] == 0.0D) {
/* 7320 */           start[0] = 1.0D;
/*      */         }
/*      */         else {
/* 7323 */           start[0] = Math.min(1.0D, -start[0]);
/*      */         }
/*      */       }
/* 7326 */       start[1] = ests[1];
/* 7327 */       if (start[1] <= 0.0D) {
/* 7328 */         if (start[1] == 0.0D) {
/* 7329 */           start[1] = 1.0D;
/*      */         }
/*      */         else {
/* 7332 */           start[1] = Math.min(1.0D, -start[1]);
/*      */         }
/*      */       }
/* 7335 */       if (this.scaleFlag) {
/* 7336 */         start[2] = (1.0D / yScale);
/*      */       }
/* 7338 */       step[0] = (0.1D * start[0]);
/* 7339 */       step[1] = (0.1D * start[1]);
/* 7340 */       if (step[1] == 0.0D) {
/* 7341 */         double xmax = xMax;
/* 7342 */         if (xmax == 0.0D) {
/* 7343 */           xmax = xMin;
/*      */         }
/* 7345 */         step[1] = (xmax * 0.1D);
/*      */       }
/* 7347 */       if (this.scaleFlag) step[2] = (0.1D * start[2]);
/*      */       break;
/* 7349 */     case 1:  start[0] = ests[0];
/* 7350 */       if (start[0] <= 0.0D) {
/* 7351 */         if (start[0] == 0.0D) {
/* 7352 */           start[0] = 1.0D;
/*      */         }
/*      */         else {
/* 7355 */           start[0] = Math.min(1.0D, -start[0]);
/*      */         }
/*      */       }
/* 7358 */       if (this.scaleFlag) {
/* 7359 */         start[1] = (1.0D / yScale);
/*      */       }
/* 7361 */       step[0] = (0.1D * start[0]);
/* 7362 */       if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */       }
/*      */       break;
/*      */     }
/*      */     
/* 7367 */     ParetoFunctionOne ff = new ParetoFunctionOne();
/* 7368 */     ff.typeFlag = typeFlag;
/* 7369 */     ff.scaleOption = this.scaleFlag;
/* 7370 */     ff.scaleFactor = this.yScaleFactor;
/* 7371 */     Object regFun3 = ff;
/* 7372 */     nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */     
/* 7374 */     if (allTest == 1)
/*      */     {
/* 7376 */       if (!this.supressPrint) { print();
/*      */       }
/* 7378 */       int flag = plotXY(ff);
/* 7379 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/*      */     
/* 7383 */     this.weightOpt = weightOptHold;
/* 7384 */     if (magCheck) {
/* 7385 */       for (int i = 0; i < this.nData; i++) {
/* 7386 */         this.yData[i] = (yy[i] / magScale);
/* 7387 */         if (this.weightOpt) this.weight[i] = (ww[i] / magScale);
/*      */       }
/*      */     }
/* 7390 */     if (ySignFlag) {
/* 7391 */       for (int i = 0; i < this.nData; i++) {
/* 7392 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void sigmoidThreshold()
/*      */   {
/* 7400 */     fitSigmoidThreshold(0);
/*      */   }
/*      */   
/*      */   public void sigmoidThresholdPlot()
/*      */   {
/* 7405 */     fitSigmoidThreshold(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void fitSigmoidThreshold(int plotFlag)
/*      */   {
/* 7412 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7413 */     this.lastMethod = 25;
/* 7414 */     this.linNonLin = false;
/* 7415 */     this.zeroCheck = false;
/* 7416 */     this.nTerms = 3;
/* 7417 */     if (!this.scaleFlag) this.nTerms = 2;
/* 7418 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7419 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 7422 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7425 */     double yymin = Fmath.minimum(this.yData);
/* 7426 */     double yymax = Fmath.maximum(this.yData);
/* 7427 */     int dirFlag = 1;
/* 7428 */     if (yymin < 0.0D) dirFlag = -1;
/* 7429 */     double yyymid = (yymax - yymin) / 2.0D;
/* 7430 */     double yyxmidl = this.xData[0][0];
/* 7431 */     int ii = 1;
/* 7432 */     int nLen = this.yData.length;
/* 7433 */     boolean test = true;
/* 7434 */     while (test) {
/* 7435 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 7436 */         yyxmidl = this.xData[0][ii];
/* 7437 */         test = false;
/*      */       }
/*      */       else {
/* 7440 */         ii++;
/* 7441 */         if (ii >= nLen) {
/* 7442 */           yyxmidl = Stat.mean(this.xData[0]);
/* 7443 */           ii = nLen - 1;
/* 7444 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7448 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 7449 */     int jj = nLen - 1;
/* 7450 */     test = true;
/* 7451 */     while (test) {
/* 7452 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 7453 */         yyxmidh = this.xData[0][jj];
/* 7454 */         test = false;
/*      */       }
/*      */       else {
/* 7457 */         jj--;
/* 7458 */         if (jj < 0) {
/* 7459 */           yyxmidh = Stat.mean(this.xData[0]);
/* 7460 */           jj = 1;
/* 7461 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7465 */     int thetaPos = (ii + jj) / 2;
/* 7466 */     double theta0 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 7469 */     double thetaSlope1 = 2.0D * (this.yData[(nLen - 1)] - theta0) / (this.xData[0][(nLen - 1)] - this.xData[0][thetaPos]);
/* 7470 */     double thetaSlope2 = 2.0D * theta0 / (this.xData[0][thetaPos] - this.xData[0][(nLen - 1)]);
/* 7471 */     double thetaSlope = Math.max(thetaSlope1, thetaSlope2);
/*      */     
/*      */ 
/* 7474 */     double[] start = new double[this.nTerms];
/* 7475 */     start[0] = (4.0D * thetaSlope);
/* 7476 */     if (dirFlag == 1) {
/* 7477 */       start[0] /= yymax;
/*      */     }
/*      */     else {
/* 7480 */       start[0] /= yymin;
/*      */     }
/* 7482 */     start[1] = theta0;
/* 7483 */     if (this.scaleFlag) {
/* 7484 */       if (dirFlag == 1) {
/* 7485 */         start[2] = yymax;
/*      */       }
/*      */       else {
/* 7488 */         start[2] = yymin;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 7493 */     double[] step = new double[this.nTerms];
/* 7494 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 7495 */     if (step[0] == 0.0D) step[0] = (0.1D * (this.xData[0][(nLen - 1)] - this.xData[0][0]) / (this.yData[(nLen - 1)] - this.yData[0]));
/* 7496 */     if (step[1] == 0.0D) step[1] = ((this.xData[0][(nLen - 1)] - this.xData[0][0]) / 20.0D);
/* 7497 */     if ((this.scaleFlag) && (step[2] == 0.0D)) { step[2] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 7500 */     SigmoidThresholdFunction f = new SigmoidThresholdFunction();
/* 7501 */     f.scaleOption = this.scaleFlag;
/* 7502 */     f.scaleFactor = this.yScaleFactor;
/* 7503 */     Object regFun2 = f;
/* 7504 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 7506 */     if (plotFlag == 1)
/*      */     {
/* 7508 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 7511 */       int flag = plotXY(f);
/* 7512 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void sigmoidHillSips() {
/* 7517 */     fitsigmoidHillSips(0);
/*      */   }
/*      */   
/*      */   public void sigmoidHillSipsPlot()
/*      */   {
/* 7522 */     fitsigmoidHillSips(1);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void fitsigmoidHillSips(int plotFlag)
/*      */   {
/* 7528 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7529 */     this.lastMethod = 28;
/* 7530 */     this.linNonLin = false;
/* 7531 */     this.zeroCheck = false;
/* 7532 */     this.nTerms = 3;
/* 7533 */     if (!this.scaleFlag) this.nTerms = 2;
/* 7534 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7535 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 7538 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7541 */     double yymin = Fmath.minimum(this.yData);
/* 7542 */     double yymax = Fmath.maximum(this.yData);
/* 7543 */     int dirFlag = 1;
/* 7544 */     if (yymin < 0.0D) dirFlag = -1;
/* 7545 */     double yyymid = (yymax - yymin) / 2.0D;
/* 7546 */     double yyxmidl = this.xData[0][0];
/* 7547 */     int ii = 1;
/* 7548 */     int nLen = this.yData.length;
/* 7549 */     boolean test = true;
/* 7550 */     while (test) {
/* 7551 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 7552 */         yyxmidl = this.xData[0][ii];
/* 7553 */         test = false;
/*      */       }
/*      */       else {
/* 7556 */         ii++;
/* 7557 */         if (ii >= nLen) {
/* 7558 */           yyxmidl = Stat.mean(this.xData[0]);
/* 7559 */           ii = nLen - 1;
/* 7560 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7564 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 7565 */     int jj = nLen - 1;
/* 7566 */     test = true;
/* 7567 */     while (test) {
/* 7568 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 7569 */         yyxmidh = this.xData[0][jj];
/* 7570 */         test = false;
/*      */       }
/*      */       else {
/* 7573 */         jj--;
/* 7574 */         if (jj < 0) {
/* 7575 */           yyxmidh = Stat.mean(this.xData[0]);
/* 7576 */           jj = 1;
/* 7577 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7581 */     int thetaPos = (ii + jj) / 2;
/* 7582 */     double theta0 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 7585 */     double[] start = new double[this.nTerms];
/* 7586 */     start[0] = theta0;
/* 7587 */     start[1] = 1.0D;
/* 7588 */     if (this.scaleFlag) {
/* 7589 */       if (dirFlag == 1) {
/* 7590 */         start[2] = yymax;
/*      */       }
/*      */       else {
/* 7593 */         start[2] = yymin;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 7598 */     double[] step = new double[this.nTerms];
/* 7599 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 7600 */     if (step[0] == 0.0D) step[0] = ((this.xData[0][(nLen - 1)] - this.xData[0][0]) / 20.0D);
/* 7601 */     if ((this.scaleFlag) && (step[2] == 0.0D)) { step[2] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 7604 */     SigmoidHillSipsFunction f = new SigmoidHillSipsFunction();
/* 7605 */     f.scaleOption = this.scaleFlag;
/* 7606 */     f.scaleFactor = this.yScaleFactor;
/* 7607 */     Object regFun2 = f;
/* 7608 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 7610 */     if (plotFlag == 1)
/*      */     {
/* 7612 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 7615 */       int flag = plotXY(f);
/* 7616 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void ec50()
/*      */   {
/* 7622 */     fitEC50(0);
/*      */   }
/*      */   
/*      */   public void ec50Plot()
/*      */   {
/* 7627 */     fitEC50(1);
/*      */   }
/*      */   
/*      */ 
/*      */   public void ec50constrained()
/*      */   {
/* 7633 */     fitEC50(2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void ec50constrainedPlot()
/*      */   {
/* 7639 */     fitEC50(3);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void fitEC50(int cpFlag)
/*      */   {
/* 7645 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7646 */     int plotFlag = 0;
/* 7647 */     boolean constrained = false;
/* 7648 */     switch (cpFlag) {
/* 7649 */     case 0:  this.lastMethod = 39;
/* 7650 */       plotFlag = 0;
/* 7651 */       break;
/* 7652 */     case 1:  this.lastMethod = 39;
/* 7653 */       plotFlag = 1;
/* 7654 */       break;
/* 7655 */     case 2:  this.lastMethod = 41;
/* 7656 */       plotFlag = 0;
/* 7657 */       constrained = true;
/* 7658 */       break;
/* 7659 */     case 3:  this.lastMethod = 41;
/* 7660 */       plotFlag = 1;
/* 7661 */       constrained = true;
/*      */     }
/*      */     
/*      */     
/* 7665 */     this.linNonLin = false;
/* 7666 */     this.zeroCheck = false;
/* 7667 */     this.nTerms = 4;
/* 7668 */     this.scaleFlag = false;
/* 7669 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7670 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 7673 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7676 */     double bottom = Fmath.minimum(this.yData);
/* 7677 */     double top = Fmath.maximum(this.yData);
/*      */     
/*      */ 
/* 7680 */     int dirFlag = 1;
/* 7681 */     double yyymid = (top - bottom) / 2.0D;
/* 7682 */     double yyxmidl = this.xData[0][0];
/* 7683 */     int ii = 1;
/* 7684 */     int nLen = this.yData.length;
/* 7685 */     boolean test = true;
/* 7686 */     while (test) {
/* 7687 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 7688 */         yyxmidl = this.xData[0][ii];
/* 7689 */         test = false;
/*      */       }
/*      */       else {
/* 7692 */         ii++;
/* 7693 */         if (ii >= nLen) {
/* 7694 */           yyxmidl = Stat.mean(this.xData[0]);
/* 7695 */           ii = nLen - 1;
/* 7696 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7700 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 7701 */     int jj = nLen - 1;
/* 7702 */     test = true;
/* 7703 */     while (test) {
/* 7704 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 7705 */         yyxmidh = this.xData[0][jj];
/* 7706 */         test = false;
/*      */       }
/*      */       else {
/* 7709 */         jj--;
/* 7710 */         if (jj < 0) {
/* 7711 */           yyxmidh = Stat.mean(this.xData[0]);
/* 7712 */           jj = 1;
/* 7713 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7717 */     int thetaPos = (ii + jj) / 2;
/* 7718 */     double EC50 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 7721 */     double thetaSlope1 = 2.0D * (this.yData[(nLen - 1)] - EC50) / (this.xData[0][(nLen - 1)] - this.xData[0][thetaPos]);
/* 7722 */     double thetaSlope2 = 2.0D * EC50 / (this.xData[0][thetaPos] - this.xData[0][(nLen - 1)]);
/* 7723 */     double hillSlope = Math.max(thetaSlope1, thetaSlope2);
/*      */     
/*      */ 
/* 7726 */     double[] start = new double[this.nTerms];
/* 7727 */     start[0] = bottom;
/* 7728 */     start[1] = top;
/* 7729 */     start[2] = EC50;
/* 7730 */     start[3] = (-hillSlope);
/*      */     
/*      */ 
/* 7733 */     double[] step = new double[this.nTerms];
/* 7734 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 7735 */     if (step[0] == 0.0D) step[0] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/* 7736 */     if (step[1] == 0.0D) step[1] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]) + this.yData[(nLen - 1)]);
/* 7737 */     if (step[2] == 0.0D) step[2] = (0.05D * (this.xData[0][(nLen - 1)] - this.xData[0][0]));
/* 7738 */     if (step[3] == 0.0D) { step[3] = (0.1D * (this.xData[0][(nLen - 1)] - this.xData[0][0]) / (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 7741 */     if (constrained) { addConstraint(0, -1, 0.0D);
/*      */     }
/*      */     
/* 7744 */     EC50Function f = new EC50Function();
/* 7745 */     Object regFun2 = f;
/* 7746 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 7748 */     if (plotFlag == 1)
/*      */     {
/* 7750 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 7753 */       int flag = plotXY(f);
/* 7754 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void logEC50()
/*      */   {
/* 7760 */     fitLogEC50(0);
/*      */   }
/*      */   
/*      */   public void logEC50Plot()
/*      */   {
/* 7765 */     fitLogEC50(1);
/*      */   }
/*      */   
/*      */ 
/*      */   public void logEC50constrained()
/*      */   {
/* 7771 */     fitLogEC50(2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void logEC50constrainedPlot()
/*      */   {
/* 7777 */     fitLogEC50(3);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void fitLogEC50(int cpFlag)
/*      */   {
/* 7783 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7784 */     int plotFlag = 0;
/* 7785 */     boolean constrained = false;
/* 7786 */     switch (cpFlag) {
/* 7787 */     case 0:  this.lastMethod = 40;
/* 7788 */       plotFlag = 0;
/* 7789 */       break;
/* 7790 */     case 1:  this.lastMethod = 40;
/* 7791 */       plotFlag = 1;
/* 7792 */       break;
/* 7793 */     case 2:  this.lastMethod = 42;
/* 7794 */       plotFlag = 0;
/* 7795 */       constrained = true;
/* 7796 */       break;
/* 7797 */     case 3:  this.lastMethod = 42;
/* 7798 */       plotFlag = 1;
/* 7799 */       constrained = true;
/*      */     }
/*      */     
/* 7802 */     this.linNonLin = false;
/* 7803 */     this.zeroCheck = false;
/* 7804 */     this.nTerms = 4;
/* 7805 */     this.scaleFlag = false;
/* 7806 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7807 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 7810 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7813 */     double bottom = Fmath.minimum(this.yData);
/* 7814 */     double top = Fmath.maximum(this.yData);
/*      */     
/*      */ 
/* 7817 */     int dirFlag = 1;
/* 7818 */     double yyymid = (top - bottom) / 2.0D;
/* 7819 */     double yyxmidl = this.xData[0][0];
/* 7820 */     int ii = 1;
/* 7821 */     int nLen = this.yData.length;
/* 7822 */     boolean test = true;
/* 7823 */     while (test) {
/* 7824 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 7825 */         yyxmidl = this.xData[0][ii];
/* 7826 */         test = false;
/*      */       }
/*      */       else {
/* 7829 */         ii++;
/* 7830 */         if (ii >= nLen) {
/* 7831 */           yyxmidl = Stat.mean(this.xData[0]);
/* 7832 */           ii = nLen - 1;
/* 7833 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7837 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 7838 */     int jj = nLen - 1;
/* 7839 */     test = true;
/* 7840 */     while (test) {
/* 7841 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 7842 */         yyxmidh = this.xData[0][jj];
/* 7843 */         test = false;
/*      */       }
/*      */       else {
/* 7846 */         jj--;
/* 7847 */         if (jj < 0) {
/* 7848 */           yyxmidh = Stat.mean(this.xData[0]);
/* 7849 */           jj = 1;
/* 7850 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7854 */     int thetaPos = (ii + jj) / 2;
/* 7855 */     double logEC50 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 7858 */     double thetaSlope1 = 2.0D * (this.yData[(nLen - 1)] - logEC50) / (this.xData[0][(nLen - 1)] - this.xData[0][thetaPos]);
/* 7859 */     double thetaSlope2 = 2.0D * logEC50 / (this.xData[0][thetaPos] - this.xData[0][(nLen - 1)]);
/* 7860 */     double hillSlope = Math.max(thetaSlope1, thetaSlope2);
/*      */     
/*      */ 
/* 7863 */     double[] start = new double[this.nTerms];
/* 7864 */     start[0] = bottom;
/* 7865 */     start[1] = top;
/* 7866 */     start[2] = logEC50;
/* 7867 */     start[3] = hillSlope;
/*      */     
/*      */ 
/* 7870 */     double[] step = new double[this.nTerms];
/* 7871 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 7872 */     if (step[0] == 0.0D) step[0] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/* 7873 */     if (step[1] == 0.0D) step[1] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]) + this.yData[(nLen - 1)]);
/* 7874 */     if (step[2] == 0.0D) step[2] = (0.05D * (this.xData[0][(nLen - 1)] - this.xData[0][0]));
/* 7875 */     if (step[3] == 0.0D) { step[3] = (0.1D * (this.xData[0][(nLen - 1)] - this.xData[0][0]) / (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 7878 */     if (constrained) { addConstraint(0, -1, 0.0D);
/*      */     }
/*      */     
/* 7881 */     LogEC50Function f = new LogEC50Function();
/* 7882 */     Object regFun2 = f;
/* 7883 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 7885 */     if (plotFlag == 1)
/*      */     {
/* 7887 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 7890 */       int flag = plotXY(f);
/* 7891 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void rectangularHyperbola()
/*      */   {
/* 7897 */     fitRectangularHyperbola(0);
/*      */   }
/*      */   
/*      */   public void rectangularHyperbolaPlot()
/*      */   {
/* 7902 */     fitRectangularHyperbola(1);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void fitRectangularHyperbola(int plotFlag)
/*      */   {
/* 7908 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 7909 */     this.lastMethod = 26;
/* 7910 */     this.linNonLin = false;
/* 7911 */     this.zeroCheck = false;
/* 7912 */     this.nTerms = 2;
/* 7913 */     if (!this.scaleFlag) this.nTerms = 1;
/* 7914 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 7915 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 7918 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 7921 */     double yymin = Fmath.minimum(this.yData);
/* 7922 */     double yymax = Fmath.maximum(this.yData);
/* 7923 */     int dirFlag = 1;
/* 7924 */     if (yymin < 0.0D) dirFlag = -1;
/* 7925 */     double yyymid = (yymax - yymin) / 2.0D;
/* 7926 */     double yyxmidl = this.xData[0][0];
/* 7927 */     int ii = 1;
/* 7928 */     int nLen = this.yData.length;
/* 7929 */     boolean test = true;
/* 7930 */     while (test) {
/* 7931 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 7932 */         yyxmidl = this.xData[0][ii];
/* 7933 */         test = false;
/*      */       }
/*      */       else {
/* 7936 */         ii++;
/* 7937 */         if (ii >= nLen) {
/* 7938 */           yyxmidl = Stat.mean(this.xData[0]);
/* 7939 */           ii = nLen - 1;
/* 7940 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7944 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 7945 */     int jj = nLen - 1;
/* 7946 */     test = true;
/* 7947 */     while (test) {
/* 7948 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 7949 */         yyxmidh = this.xData[0][jj];
/* 7950 */         test = false;
/*      */       }
/*      */       else {
/* 7953 */         jj--;
/* 7954 */         if (jj < 0) {
/* 7955 */           yyxmidh = Stat.mean(this.xData[0]);
/* 7956 */           jj = 1;
/* 7957 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 7961 */     int thetaPos = (ii + jj) / 2;
/* 7962 */     double theta0 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 7965 */     double[] start = new double[this.nTerms];
/* 7966 */     start[0] = theta0;
/* 7967 */     if (this.scaleFlag) {
/* 7968 */       if (dirFlag == 1) {
/* 7969 */         start[1] = yymax;
/*      */       }
/*      */       else {
/* 7972 */         start[1] = yymin;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 7977 */     double[] step = new double[this.nTerms];
/* 7978 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 7979 */     if (step[0] == 0.0D) step[0] = ((this.xData[0][(nLen - 1)] - this.xData[0][0]) / 20.0D);
/* 7980 */     if ((this.scaleFlag) && (step[1] == 0.0D)) { step[1] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 7983 */     RectangularHyperbolaFunction f = new RectangularHyperbolaFunction();
/* 7984 */     f.scaleOption = this.scaleFlag;
/* 7985 */     f.scaleFactor = this.yScaleFactor;
/* 7986 */     Object regFun2 = f;
/* 7987 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 7989 */     if (plotFlag == 1)
/*      */     {
/* 7991 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 7994 */       int flag = plotXY(f);
/* 7995 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void stepFunction()
/*      */   {
/* 8001 */     fitStepFunction(0);
/*      */   }
/*      */   
/*      */   public void stepFunctionPlot()
/*      */   {
/* 8006 */     fitStepFunction(1);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void fitStepFunction(int plotFlag)
/*      */   {
/* 8012 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 8013 */     this.lastMethod = 27;
/* 8014 */     this.linNonLin = false;
/* 8015 */     this.zeroCheck = false;
/* 8016 */     this.nTerms = 2;
/* 8017 */     if (!this.scaleFlag) this.nTerms = 1;
/* 8018 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 8019 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 8022 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 8025 */     double yymin = Fmath.minimum(this.yData);
/* 8026 */     double yymax = Fmath.maximum(this.yData);
/* 8027 */     int dirFlag = 1;
/* 8028 */     if (yymin < 0.0D) dirFlag = -1;
/* 8029 */     double yyymid = (yymax - yymin) / 2.0D;
/* 8030 */     double yyxmidl = this.xData[0][0];
/* 8031 */     int ii = 1;
/* 8032 */     int nLen = this.yData.length;
/* 8033 */     boolean test = true;
/* 8034 */     while (test) {
/* 8035 */       if (this.yData[ii] >= dirFlag * yyymid) {
/* 8036 */         yyxmidl = this.xData[0][ii];
/* 8037 */         test = false;
/*      */       }
/*      */       else {
/* 8040 */         ii++;
/* 8041 */         if (ii >= nLen) {
/* 8042 */           yyxmidl = Stat.mean(this.xData[0]);
/* 8043 */           ii = nLen - 1;
/* 8044 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 8048 */     double yyxmidh = this.xData[0][(nLen - 1)];
/* 8049 */     int jj = nLen - 1;
/* 8050 */     test = true;
/* 8051 */     while (test) {
/* 8052 */       if (this.yData[jj] <= dirFlag * yyymid) {
/* 8053 */         yyxmidh = this.xData[0][jj];
/* 8054 */         test = false;
/*      */       }
/*      */       else {
/* 8057 */         jj--;
/* 8058 */         if (jj < 0) {
/* 8059 */           yyxmidh = Stat.mean(this.xData[0]);
/* 8060 */           jj = 1;
/* 8061 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 8065 */     int thetaPos = (ii + jj) / 2;
/* 8066 */     double theta0 = this.xData[0][thetaPos];
/*      */     
/*      */ 
/* 8069 */     double[] start = new double[this.nTerms];
/* 8070 */     start[0] = theta0;
/* 8071 */     if (this.scaleFlag) {
/* 8072 */       if (dirFlag == 1) {
/* 8073 */         start[1] = yymax;
/*      */       }
/*      */       else {
/* 8076 */         start[1] = yymin;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 8081 */     double[] step = new double[this.nTerms];
/* 8082 */     for (int i = 0; i < this.nTerms; i++) step[i] = (0.1D * start[i]);
/* 8083 */     if (step[0] == 0.0D) step[0] = ((this.xData[0][(nLen - 1)] - this.xData[0][0]) / 20.0D);
/* 8084 */     if ((this.scaleFlag) && (step[1] == 0.0D)) { step[1] = (0.1D * (this.yData[(nLen - 1)] - this.yData[0]));
/*      */     }
/*      */     
/* 8087 */     StepFunctionFunction f = new StepFunctionFunction();
/* 8088 */     f.scaleOption = this.scaleFlag;
/* 8089 */     f.scaleFactor = this.yScaleFactor;
/* 8090 */     Object regFun2 = f;
/* 8091 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 8093 */     if (plotFlag == 1)
/*      */     {
/* 8095 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 8098 */       int flag = plotXY(f);
/* 8099 */       if ((flag != -2) && (!this.supressYYplot)) plotYY();
/*      */     }
/*      */   }
/*      */   
/*      */   public void logistic()
/*      */   {
/* 8105 */     fitLogistic(0);
/*      */   }
/*      */   
/*      */ 
/*      */   public void logisticPlot()
/*      */   {
/* 8111 */     fitLogistic(1);
/*      */   }
/*      */   
/*      */   protected void fitLogistic(int plotFlag)
/*      */   {
/* 8116 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 8117 */     this.lastMethod = 30;
/* 8118 */     this.linNonLin = false;
/* 8119 */     this.zeroCheck = false;
/* 8120 */     this.nTerms = 3;
/* 8121 */     if (!this.scaleFlag) this.nTerms = 2;
/* 8122 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 8123 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 8126 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 8129 */     Double tempd = null;
/* 8130 */     ArrayList<Object> retY = dataSign(this.yData);
/* 8131 */     tempd = (Double)retY.get(4);
/* 8132 */     double yPeak = tempd.doubleValue();
/* 8133 */     boolean yFlag = false;
/* 8134 */     if (yPeak < 0.0D) {
/* 8135 */       System.out.println("Regression.fitLogistic(): This implementation of the Logistic distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 8136 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 8137 */       for (int i = 0; i < this.nData; i++) {
/* 8138 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 8140 */       retY = dataSign(this.yData);
/* 8141 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 8145 */     ArrayList<Object> ret1 = dataSign(this.yData);
/* 8146 */     Integer tempi = null;
/* 8147 */     tempi = (Integer)ret1.get(5);
/* 8148 */     int peaki = tempi.intValue();
/* 8149 */     double mu = this.xData[0][peaki];
/*      */     
/*      */ 
/* 8152 */     double beta = Math.sqrt(6.0D) * halfWidth(this.xData[0], this.yData) / 3.141592653589793D;
/*      */     
/*      */ 
/* 8155 */     tempd = (Double)ret1.get(4);
/* 8156 */     double ym = tempd.doubleValue();
/* 8157 */     ym = ym * beta * Math.sqrt(6.283185307179586D);
/*      */     
/*      */ 
/* 8160 */     double[] start = new double[this.nTerms];
/* 8161 */     double[] step = new double[this.nTerms];
/* 8162 */     start[0] = mu;
/* 8163 */     start[1] = beta;
/* 8164 */     if (this.scaleFlag) {
/* 8165 */       start[2] = ym;
/*      */     }
/* 8167 */     step[0] = (0.1D * beta);
/* 8168 */     step[1] = (0.1D * start[1]);
/* 8169 */     if (step[1] == 0.0D) {
/* 8170 */       ArrayList<Object> ret0 = dataSign(this.xData[0]);
/* 8171 */       Double tempdd = null;
/* 8172 */       tempdd = (Double)ret0.get(2);
/* 8173 */       double xmax = tempdd.doubleValue();
/* 8174 */       if (xmax == 0.0D) {
/* 8175 */         tempdd = (Double)ret0.get(0);
/* 8176 */         xmax = tempdd.doubleValue();
/*      */       }
/* 8178 */       step[0] = (xmax * 0.1D);
/*      */     }
/* 8180 */     if (this.scaleFlag) { step[2] = (0.1D * start[2]);
/*      */     }
/*      */     
/* 8183 */     LogisticFunction f = new LogisticFunction();
/* 8184 */     addConstraint(1, -1, 0.0D);
/* 8185 */     f.scaleOption = this.scaleFlag;
/* 8186 */     f.scaleFactor = this.yScaleFactor;
/* 8187 */     Object regFun2 = f;
/* 8188 */     nelderMead(regFun2, start, step, this.fTol, this.nMax);
/*      */     
/* 8190 */     if (plotFlag == 1)
/*      */     {
/* 8192 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 8195 */       int flag = plotXY(f);
/* 8196 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 8199 */     if (yFlag)
/*      */     {
/* 8201 */       for (int i = 0; i < this.nData - 1; i++) {
/* 8202 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void beta()
/*      */   {
/* 8209 */     fitBeta(0, 0);
/*      */   }
/*      */   
/*      */   public void betaPlot() {
/* 8213 */     fitBeta(1, 0);
/*      */   }
/*      */   
/*      */   public void betaMinMax() {
/* 8217 */     fitBeta(0, 1);
/*      */   }
/*      */   
/*      */   public void betaMinMaxPlot() {
/* 8221 */     fitBeta(1, 1);
/*      */   }
/*      */   
/*      */   protected void fitBeta(int allTest, int typeFlag) {
/* 8225 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 8226 */     switch (typeFlag) {
/* 8227 */     case 0:  this.lastMethod = 31;
/* 8228 */       this.nTerms = 3;
/* 8229 */       break;
/* 8230 */     case 1:  this.lastMethod = 32;
/* 8231 */       this.nTerms = 5;
/*      */     }
/*      */     
/* 8234 */     if (!this.scaleFlag) { this.nTerms -= 1;
/*      */     }
/* 8236 */     this.zeroCheck = false;
/* 8237 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 8238 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 8241 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 8244 */     Double tempd = null;
/* 8245 */     ArrayList<Object> retY = dataSign(this.yData);
/* 8246 */     tempd = (Double)retY.get(4);
/* 8247 */     double yPeak = tempd.doubleValue();
/* 8248 */     boolean yFlag = false;
/* 8249 */     if (yPeak < 0.0D) {
/* 8250 */       System.out.println("Regression.fitBeta(): This implementation of the Beta distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 8251 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 8252 */       for (int i = 0; i < this.nData; i++) {
/* 8253 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 8255 */       retY = dataSign(this.yData);
/* 8256 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 8260 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 8261 */     Integer tempi = null;
/*      */     
/*      */ 
/* 8264 */     tempi = (Integer)retY.get(5);
/* 8265 */     int peaki = tempi.intValue();
/* 8266 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 8269 */     tempd = (Double)retX.get(0);
/* 8270 */     double minX = tempd.doubleValue();
/*      */     
/* 8272 */     tempd = (Double)retX.get(2);
/* 8273 */     double maxX = tempd.doubleValue();
/*      */     
/* 8275 */     tempd = (Double)retX.get(8);
/* 8276 */     double meanX = tempd.doubleValue();
/*      */     
/*      */ 
/*      */ 
/* 8280 */     if (typeFlag == 0) {
/* 8281 */       if (minX < 0.0D) {
/* 8282 */         System.out.println("Regression: beta: data points must be greater than or equal to 0");
/* 8283 */         System.out.println("method betaMinMax used in place of method beta");
/* 8284 */         typeFlag = 1;
/* 8285 */         this.lastMethod = 32;
/* 8286 */         this.nTerms = 5;
/*      */       }
/* 8288 */       if (maxX > 1.0D) {
/* 8289 */         System.out.println("Regression: beta: data points must be less than or equal to 1");
/* 8290 */         System.out.println("method betaMinMax used in place of method beta");
/* 8291 */         typeFlag = 1;
/* 8292 */         this.lastMethod = 32;
/* 8293 */         this.nTerms = 5;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 8298 */     double dMode = distribMode;
/* 8299 */     double dMean = meanX;
/* 8300 */     if (typeFlag == 1) {
/* 8301 */       dMode = (distribMode - minX * 0.9D) / (maxX * 1.2D - minX * 0.9D);
/* 8302 */       dMean = (meanX - minX * 0.9D) / (maxX * 1.2D - minX * 0.9D);
/*      */     }
/* 8304 */     double alphaGuess = 2.0D * dMode * dMean / (dMode - dMean);
/* 8305 */     if (alphaGuess < 1.3D) alphaGuess = 1.6D;
/* 8306 */     double betaGuess = alphaGuess * (1.0D - dMean) / dMean;
/* 8307 */     if (betaGuess <= 1.3D) betaGuess = 1.6D;
/* 8308 */     double scaleGuess = 0.0D;
/* 8309 */     if (typeFlag == 0) {
/* 8310 */       scaleGuess = yPeak / Stat.betaPDF(alphaGuess, betaGuess, distribMode);
/*      */     }
/*      */     else {
/* 8313 */       scaleGuess = yPeak / Stat.betaPDF(minX, maxX, alphaGuess, betaGuess, distribMode);
/*      */     }
/* 8315 */     if (scaleGuess < 0.0D) { scaleGuess = 1.0D;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8320 */     double[] start = new double[this.nTerms];
/* 8321 */     double[] step = new double[this.nTerms];
/* 8322 */     switch (typeFlag) {
/* 8323 */     case 0:  start[0] = alphaGuess;
/* 8324 */       start[1] = betaGuess;
/* 8325 */       if (this.scaleFlag) {
/* 8326 */         start[2] = scaleGuess;
/*      */       }
/* 8328 */       step[0] = (0.1D * start[0]);
/* 8329 */       step[1] = (0.1D * start[1]);
/* 8330 */       if (this.scaleFlag) { step[2] = (0.1D * start[2]);
/*      */       }
/*      */       
/* 8333 */       addConstraint(0, -1, 1.0D);
/* 8334 */       addConstraint(1, -1, 1.0D);
/* 8335 */       break;
/* 8336 */     case 1:  start[0] = alphaGuess;
/* 8337 */       start[1] = betaGuess;
/* 8338 */       start[2] = (0.9D * minX);
/* 8339 */       start[3] = (1.1D * maxX);
/* 8340 */       if (this.scaleFlag) {
/* 8341 */         start[4] = scaleGuess;
/*      */       }
/* 8343 */       step[0] = (0.1D * start[0]);
/* 8344 */       step[1] = (0.1D * start[1]);
/* 8345 */       step[2] = (0.1D * start[2]);
/* 8346 */       step[3] = (0.1D * start[3]);
/* 8347 */       if (this.scaleFlag) { step[4] = (0.1D * start[4]);
/*      */       }
/*      */       
/* 8350 */       addConstraint(0, -1, 1.0D);
/* 8351 */       addConstraint(1, -1, 1.0D);
/* 8352 */       addConstraint(2, 1, minX);
/* 8353 */       addConstraint(3, -1, maxX);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/* 8359 */     BetaFunction ff = new BetaFunction();
/*      */     
/*      */ 
/* 8362 */     ff.typeFlag = typeFlag;
/*      */     
/*      */ 
/* 8365 */     ff.scaleOption = this.scaleFlag;
/* 8366 */     ff.scaleFactor = this.yScaleFactor;
/*      */     
/*      */ 
/* 8369 */     Object regFun3 = ff;
/* 8370 */     nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */     
/* 8372 */     if (allTest == 1)
/*      */     {
/* 8374 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 8377 */       int flag = plotXY(ff);
/* 8378 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 8381 */     if (yFlag)
/*      */     {
/* 8383 */       for (int i = 0; i < this.nData - 1; i++) {
/* 8384 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void gamma() {
/* 8390 */     fitGamma(0, 0);
/*      */   }
/*      */   
/*      */   public void gammaPlot() {
/* 8394 */     fitGamma(1, 0);
/*      */   }
/*      */   
/*      */   public void gammaStandard() {
/* 8398 */     fitGamma(0, 1);
/*      */   }
/*      */   
/*      */   public void gammaStandardPlot() {
/* 8402 */     fitGamma(1, 1);
/*      */   }
/*      */   
/*      */   protected void fitGamma(int allTest, int typeFlag) {
/* 8406 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 8407 */     switch (typeFlag) {
/* 8408 */     case 0:  this.lastMethod = 33;
/* 8409 */       this.nTerms = 4;
/* 8410 */       break;
/* 8411 */     case 1:  this.lastMethod = 34;
/* 8412 */       this.nTerms = 2;
/*      */     }
/*      */     
/* 8415 */     if (!this.scaleFlag) { this.nTerms -= 1;
/*      */     }
/* 8417 */     this.zeroCheck = false;
/* 8418 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 8419 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 8422 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 8425 */     Double tempd = null;
/* 8426 */     ArrayList<Object> retY = dataSign(this.yData);
/* 8427 */     tempd = (Double)retY.get(4);
/* 8428 */     double yPeak = tempd.doubleValue();
/* 8429 */     boolean yFlag = false;
/* 8430 */     if (yPeak < 0.0D) {
/* 8431 */       System.out.println("Regression.fitGamma(): This implementation of the Gamma distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 8432 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 8433 */       for (int i = 0; i < this.nData; i++) {
/* 8434 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 8436 */       retY = dataSign(this.yData);
/* 8437 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 8441 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 8442 */     Integer tempi = null;
/*      */     
/*      */ 
/* 8445 */     tempi = (Integer)retY.get(5);
/* 8446 */     int peaki = tempi.intValue();
/* 8447 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 8450 */     tempd = (Double)retX.get(0);
/* 8451 */     double minX = tempd.doubleValue();
/*      */     
/* 8453 */     tempd = (Double)retX.get(2);
/* 8454 */     double maxX = tempd.doubleValue();
/*      */     
/* 8456 */     tempd = (Double)retX.get(8);
/* 8457 */     double meanX = tempd.doubleValue();
/*      */     
/*      */ 
/*      */ 
/* 8461 */     if ((typeFlag == 1) && 
/* 8462 */       (minX < 0.0D)) {
/* 8463 */       System.out.println("Regression: gammaStandard: data points must be greater than or equal to 0");
/* 8464 */       System.out.println("method gamma used in place of method gammaStandard");
/* 8465 */       typeFlag = 0;
/* 8466 */       this.lastMethod = 33;
/* 8467 */       this.nTerms = 2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8472 */     double muGuess = 0.8D * minX;
/* 8473 */     if (muGuess == 0.0D) muGuess = -0.1D;
/* 8474 */     double betaGuess = meanX - distribMode;
/* 8475 */     if (betaGuess <= 0.0D) betaGuess = 1.0D;
/* 8476 */     double gammaGuess = (meanX + muGuess) / betaGuess;
/* 8477 */     if (typeFlag == 1) gammaGuess = meanX;
/* 8478 */     if (gammaGuess <= 0.0D) gammaGuess = 1.0D;
/* 8479 */     double scaleGuess = 0.0D;
/* 8480 */     if (typeFlag == 0) {
/* 8481 */       scaleGuess = yPeak / Stat.gammaPDF(muGuess, betaGuess, gammaGuess, distribMode);
/*      */     }
/*      */     else {
/* 8484 */       scaleGuess = yPeak / Stat.gammaPDF(gammaGuess, distribMode);
/*      */     }
/* 8486 */     if (scaleGuess < 0.0D) { scaleGuess = 1.0D;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8491 */     double[] start = new double[this.nTerms];
/* 8492 */     double[] step = new double[this.nTerms];
/* 8493 */     switch (typeFlag) {
/* 8494 */     case 1:  start[0] = gammaGuess;
/* 8495 */       if (this.scaleFlag) {
/* 8496 */         start[1] = scaleGuess;
/*      */       }
/* 8498 */       step[0] = (0.1D * start[0]);
/* 8499 */       if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */       }
/*      */       
/* 8502 */       addConstraint(0, -1, 0.0D);
/* 8503 */       break;
/* 8504 */     case 0:  start[0] = muGuess;
/* 8505 */       start[1] = betaGuess;
/* 8506 */       start[2] = gammaGuess;
/* 8507 */       if (this.scaleFlag) {
/* 8508 */         start[3] = scaleGuess;
/*      */       }
/* 8510 */       step[0] = (0.1D * start[0]);
/* 8511 */       step[1] = (0.1D * start[1]);
/* 8512 */       step[2] = (0.1D * start[2]);
/* 8513 */       if (this.scaleFlag) { step[3] = (0.1D * start[3]);
/*      */       }
/*      */       
/* 8516 */       addConstraint(1, -1, 0.0D);
/* 8517 */       addConstraint(2, -1, 0.0D);
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 8522 */     GammaFunction ff = new GammaFunction();
/*      */     
/*      */ 
/* 8525 */     ff.typeFlag = typeFlag;
/*      */     
/*      */ 
/* 8528 */     ff.scaleOption = this.scaleFlag;
/* 8529 */     ff.scaleFactor = this.yScaleFactor;
/*      */     
/*      */ 
/* 8532 */     Object regFun3 = ff;
/* 8533 */     nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */     
/* 8535 */     if (allTest == 1)
/*      */     {
/* 8537 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 8540 */       int flag = plotXY(ff);
/* 8541 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 8544 */     if (yFlag)
/*      */     {
/* 8546 */       for (int i = 0; i < this.nData - 1; i++) {
/* 8547 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void erlang()
/*      */   {
/* 8554 */     fitErlang(0, 0);
/*      */   }
/*      */   
/*      */   public void erlangPlot() {
/* 8558 */     fitErlang(1, 0);
/*      */   }
/*      */   
/*      */   protected void fitErlang(int allTest, int typeFlag) {
/* 8562 */     if (this.multipleY) throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
/* 8563 */     this.lastMethod = 35;
/* 8564 */     int nTerms0 = 2;
/* 8565 */     int nTerms1 = 4;
/* 8566 */     this.nTerms = nTerms1;
/* 8567 */     if (!this.scaleFlag) { this.nTerms -= 1;
/*      */     }
/* 8569 */     this.zeroCheck = false;
/* 8570 */     this.degreesOfFreedom = (this.nData - this.nTerms);
/* 8571 */     if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) { throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
/*      */     }
/*      */     
/* 8574 */     sort(this.xData[0], this.yData, this.weight);
/*      */     
/*      */ 
/* 8577 */     Double tempd = null;
/* 8578 */     ArrayList<Object> retY = dataSign(this.yData);
/* 8579 */     tempd = (Double)retY.get(4);
/* 8580 */     double yPeak = tempd.doubleValue();
/* 8581 */     boolean yFlag = false;
/* 8582 */     if (yPeak < 0.0D) {
/* 8583 */       System.out.println("Regression.fitGamma(): This implementation of the Erlang distribution takes only positive y values\n(noise taking low values below zero are allowed)");
/* 8584 */       System.out.println("All y values have been multiplied by -1 before fitting");
/* 8585 */       for (int i = 0; i < this.nData; i++) {
/* 8586 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/* 8588 */       retY = dataSign(this.yData);
/* 8589 */       yFlag = true;
/*      */     }
/*      */     
/*      */ 
/* 8593 */     ArrayList<Object> retX = dataSign(this.xData[0]);
/* 8594 */     Integer tempi = null;
/*      */     
/*      */ 
/* 8597 */     tempi = (Integer)retY.get(5);
/* 8598 */     int peaki = tempi.intValue();
/* 8599 */     double distribMode = this.xData[0][peaki];
/*      */     
/*      */ 
/* 8602 */     tempd = (Double)retX.get(0);
/* 8603 */     double minX = tempd.doubleValue();
/*      */     
/* 8605 */     tempd = (Double)retX.get(2);
/* 8606 */     double maxX = tempd.doubleValue();
/*      */     
/* 8608 */     tempd = (Double)retX.get(8);
/* 8609 */     double meanX = tempd.doubleValue();
/*      */     
/*      */ 
/*      */ 
/* 8613 */     if (minX < 0.0D) { throw new IllegalArgumentException("data points must be greater than or equal to 0");
/*      */     }
/*      */     
/*      */ 
/* 8617 */     double muGuess = 0.8D * minX;
/* 8618 */     if (muGuess == 0.0D) muGuess = -0.1D;
/* 8619 */     double betaGuess = meanX - distribMode;
/* 8620 */     if (betaGuess <= 0.0D) betaGuess = 1.0D;
/* 8621 */     double gammaGuess = (meanX + muGuess) / betaGuess;
/* 8622 */     if (typeFlag == 1) gammaGuess = meanX;
/* 8623 */     if (gammaGuess <= 0.0D) gammaGuess = 1.0D;
/* 8624 */     double scaleGuess = 0.0D;
/* 8625 */     scaleGuess = yPeak / Stat.gammaPDF(muGuess, betaGuess, gammaGuess, distribMode);
/* 8626 */     if (scaleGuess < 0.0D) { scaleGuess = 1.0D;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8631 */     double[] start = new double[this.nTerms];
/* 8632 */     double[] step = new double[this.nTerms];
/* 8633 */     start[0] = muGuess;
/* 8634 */     start[1] = betaGuess;
/* 8635 */     start[2] = gammaGuess;
/* 8636 */     if (this.scaleFlag) { start[3] = scaleGuess;
/*      */     }
/* 8638 */     step[0] = (0.1D * start[0]);
/* 8639 */     step[1] = (0.1D * start[1]);
/* 8640 */     step[2] = (0.1D * start[2]);
/* 8641 */     if (this.scaleFlag) { step[3] = (0.1D * start[3]);
/*      */     }
/*      */     
/* 8644 */     addConstraint(1, -1, 0.0D);
/* 8645 */     addConstraint(2, -1, 0.0D);
/*      */     
/*      */ 
/* 8648 */     GammaFunction ff = new GammaFunction();
/*      */     
/*      */ 
/* 8651 */     ff.typeFlag = typeFlag;
/*      */     
/*      */ 
/* 8654 */     ff.scaleOption = this.scaleFlag;
/* 8655 */     ff.scaleFactor = this.yScaleFactor;
/*      */     
/*      */ 
/* 8658 */     Object regFun3 = ff;
/* 8659 */     nelderMead(regFun3, start, step, this.fTol, this.nMax);
/*      */     
/*      */ 
/*      */ 
/* 8663 */     removeConstraints();
/*      */     
/*      */ 
/* 8666 */     double[] bestGammaEst = getCoeff();
/*      */     
/*      */ 
/* 8669 */     this.nTerms = nTerms0;
/* 8670 */     start = new double[this.nTerms];
/* 8671 */     step = new double[this.nTerms];
/* 8672 */     if (bestGammaEst[3] < 0.0D) { bestGammaEst[3] *= -1.0D;
/*      */     }
/*      */     
/* 8675 */     start[0] = (1.0D / bestGammaEst[1]);
/* 8676 */     if (this.scaleFlag) { start[1] = bestGammaEst[3];
/*      */     }
/* 8678 */     step[0] = (0.1D * start[0]);
/* 8679 */     if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */     }
/*      */     
/* 8682 */     addConstraint(0, -1, 0.0D);
/*      */     
/*      */ 
/* 8685 */     double kay0 = Math.round(bestGammaEst[2]);
/* 8686 */     double kay = kay0;
/*      */     
/*      */ 
/* 8689 */     ErlangFunction ef = new ErlangFunction();
/*      */     
/*      */ 
/* 8692 */     ef.scaleOption = this.scaleFlag;
/* 8693 */     ef.scaleFactor = this.yScaleFactor;
/* 8694 */     ef.kay = kay;
/*      */     
/*      */ 
/* 8697 */     boolean testKay = true;
/* 8698 */     double ssMin = NaN.0D;
/* 8699 */     double upSS = NaN.0D;
/* 8700 */     double upKay = NaN.0D;
/* 8701 */     double kayFinal = NaN.0D;
/* 8702 */     int iStart = 1;
/* 8703 */     int ssSame = 0;
/*      */     
/* 8705 */     while (testKay)
/*      */     {
/*      */ 
/* 8708 */       Object regFun4 = ef;
/*      */       
/* 8710 */       nelderMead(regFun4, start, step, this.fTol, this.nMax);
/* 8711 */       double sumOfSquares = getSumOfSquares();
/* 8712 */       if (iStart == 1) {
/* 8713 */         iStart = 2;
/* 8714 */         ssMin = sumOfSquares;
/* 8715 */         kay += 1.0D;
/* 8716 */         start[0] = (1.0D / bestGammaEst[1]);
/* 8717 */         if (this.scaleFlag) start[1] = bestGammaEst[3];
/* 8718 */         step[0] = (0.1D * start[0]);
/* 8719 */         if (this.scaleFlag) step[1] = (0.1D * start[1]);
/* 8720 */         addConstraint(0, -1, 0.0D);
/* 8721 */         ef.kay = kay;
/*      */ 
/*      */       }
/* 8724 */       else if (sumOfSquares <= ssMin) {
/* 8725 */         if (sumOfSquares == ssMin) {
/* 8726 */           ssSame++;
/* 8727 */           if (ssSame == 10) {
/* 8728 */             upSS = ssMin;
/* 8729 */             upKay = kay - 5.0D;
/* 8730 */             testKay = false;
/*      */           }
/*      */         }
/* 8733 */         ssMin = sumOfSquares;
/* 8734 */         kay += 1.0D;
/* 8735 */         start[0] = (1.0D / bestGammaEst[1]);
/* 8736 */         if (this.scaleFlag) start[1] = bestGammaEst[3];
/* 8737 */         step[0] = (0.1D * start[0]);
/* 8738 */         if (this.scaleFlag) step[1] = (0.1D * start[1]);
/* 8739 */         addConstraint(0, -1, 0.0D);
/* 8740 */         ef.kay = kay;
/*      */       }
/*      */       else {
/* 8743 */         upSS = ssMin;
/* 8744 */         upKay = kay - 1.0D;
/* 8745 */         testKay = false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 8750 */     if (kay0 == 1.0D) {
/* 8751 */       kayFinal = upKay;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 8756 */       iStart = 1;
/* 8757 */       testKay = true;
/* 8758 */       ssMin = NaN.0D;
/* 8759 */       double downSS = NaN.0D;
/* 8760 */       double downKay = NaN.0D;
/*      */       
/* 8762 */       start[0] = (1.0D / bestGammaEst[1]);
/* 8763 */       if (this.scaleFlag) start[1] = bestGammaEst[3];
/* 8764 */       step[0] = (0.1D * start[0]);
/* 8765 */       if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */       }
/* 8767 */       addConstraint(0, -1, 0.0D);
/* 8768 */       kay = kay0;
/* 8769 */       ef.kay = kay;
/*      */       
/* 8771 */       while (testKay)
/*      */       {
/*      */ 
/* 8774 */         Object regFun5 = ef;
/*      */         
/* 8776 */         nelderMead(regFun5, start, step, this.fTol, this.nMax);
/* 8777 */         double sumOfSquares = getSumOfSquares();
/* 8778 */         if (iStart == 1) {
/* 8779 */           iStart = 2;
/* 8780 */           ssMin = sumOfSquares;
/* 8781 */           kay -= 1.0D;
/* 8782 */           if (Math.rint(kay) < 1.0D) {
/* 8783 */             downSS = ssMin;
/* 8784 */             downKay = kay + 1.0D;
/* 8785 */             testKay = false;
/*      */           }
/*      */           else {
/* 8788 */             start[0] = (1.0D / bestGammaEst[1]);
/* 8789 */             if (this.scaleFlag) start[1] = bestGammaEst[3];
/* 8790 */             step[0] = (0.1D * start[0]);
/* 8791 */             if (this.scaleFlag) step[1] = (0.1D * start[1]);
/* 8792 */             addConstraint(0, -1, 0.0D);
/* 8793 */             ef.kay = kay;
/*      */           }
/*      */           
/*      */         }
/* 8797 */         else if (sumOfSquares <= ssMin) {
/* 8798 */           ssMin = sumOfSquares;
/* 8799 */           kay -= 1.0D;
/* 8800 */           if (Math.rint(kay) < 1.0D) {
/* 8801 */             downSS = ssMin;
/* 8802 */             downKay = kay + 1.0D;
/* 8803 */             testKay = false;
/*      */           }
/*      */           else {
/* 8806 */             start[0] = (1.0D / bestGammaEst[1]);
/* 8807 */             if (this.scaleFlag) start[1] = bestGammaEst[3];
/* 8808 */             step[0] = (0.1D * start[0]);
/* 8809 */             if (this.scaleFlag) step[1] = (0.1D * start[1]);
/* 8810 */             addConstraint(0, -1, 0.0D);
/* 8811 */             ef.kay = kay;
/*      */           }
/*      */         }
/*      */         else {
/* 8815 */           downSS = ssMin;
/* 8816 */           downKay = kay + 1.0D;
/* 8817 */           testKay = false;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 8822 */       if (downSS < upSS) {
/* 8823 */         kayFinal = downKay;
/*      */       }
/*      */       else {
/* 8826 */         kayFinal = upKay;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 8833 */     start[0] = (1.0D / bestGammaEst[1]);
/* 8834 */     if (this.scaleFlag) { start[1] = bestGammaEst[3];
/*      */     }
/* 8836 */     step[0] = (0.1D * start[0]);
/* 8837 */     if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */     }
/*      */     
/* 8840 */     addConstraint(0, -1, 0.0D);
/*      */     
/*      */ 
/* 8843 */     ef.scaleOption = this.scaleFlag;
/* 8844 */     ef.scaleFactor = this.yScaleFactor;
/* 8845 */     ef.kay = Math.round(kayFinal);
/* 8846 */     this.kayValue = Math.round(kayFinal);
/*      */     
/*      */ 
/* 8849 */     Object regFun4 = ef;
/*      */     
/* 8851 */     nelderMead(regFun4, start, step, this.fTol, this.nMax);
/* 8852 */     double[] coeff = getCoeff();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 8857 */     start[0] = coeff[0];
/* 8858 */     if (this.scaleFlag) { start[1] = coeff[1];
/*      */     }
/* 8860 */     step[0] = (0.1D * start[0]);
/* 8861 */     if (this.scaleFlag) { step[1] = (0.1D * start[1]);
/*      */     }
/*      */     
/* 8864 */     addConstraint(0, -1, 0.0D);
/*      */     
/*      */ 
/* 8867 */     ef.scaleOption = this.scaleFlag;
/* 8868 */     ef.scaleFactor = this.yScaleFactor;
/* 8869 */     ef.kay = Math.round(kayFinal);
/* 8870 */     this.kayValue = Math.round(kayFinal);
/*      */     
/*      */ 
/* 8873 */     Object regFun5 = ef;
/*      */     
/* 8875 */     nelderMead(regFun5, start, step, this.fTol, this.nMax);
/*      */     
/* 8877 */     if (allTest == 1)
/*      */     {
/* 8879 */       if (!this.supressPrint) { print();
/*      */       }
/*      */       
/* 8882 */       int flag = plotXY(ef);
/* 8883 */       if ((flag != -2) && (!this.supressYYplot)) { plotYY();
/*      */       }
/*      */     }
/* 8886 */     if (yFlag)
/*      */     {
/* 8888 */       for (int i = 0; i < this.nData - 1; i++) {
/* 8889 */         this.yData[i] = (-this.yData[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public double getKayValue()
/*      */   {
/* 8896 */     return this.kayValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double[][] histogramBins(double[] data, double binWidth, double binZero, double binUpper)
/*      */   {
/* 8904 */     int n = 0;
/* 8905 */     int m = data.length;
/* 8906 */     for (int i = 0; i < m; i++) if (data[i] <= binUpper) n++;
/* 8907 */     if (n != m) {
/* 8908 */       double[] newData = new double[n];
/* 8909 */       int j = 0;
/* 8910 */       for (int i = 0; i < m; i++) {
/* 8911 */         if (data[i] <= binUpper) {
/* 8912 */           newData[j] = data[i];
/* 8913 */           j++;
/*      */         }
/*      */       }
/* 8916 */       System.out.println(m - n + " data points, above histogram upper limit, excluded in Stat.histogramBins");
/* 8917 */       return histogramBins(newData, binWidth, binZero);
/*      */     }
/*      */     
/* 8920 */     return histogramBins(data, binWidth, binZero);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double[][] histogramBins(double[] data, double binWidth, double binZero)
/*      */   {
/* 8928 */     double dmax = Fmath.maximum(data);
/* 8929 */     int nBins = (int)Math.ceil((dmax - binZero) / binWidth);
/* 8930 */     if (binZero + nBins * binWidth > dmax) nBins++;
/* 8931 */     int nPoints = data.length;
/* 8932 */     int[] dataCheck = new int[nPoints];
/* 8933 */     for (int i = 0; i < nPoints; i++) dataCheck[i] = 0;
/* 8934 */     double[] binWall = new double[nBins + 1];
/* 8935 */     binWall[0] = binZero;
/* 8936 */     for (int i = 1; i <= nBins; i++) {
/* 8937 */       binWall[i] = (binWall[(i - 1)] + binWidth);
/*      */     }
/* 8939 */     double[][] binFreq = new double[2][nBins];
/* 8940 */     for (int i = 0; i < nBins; i++) {
/* 8941 */       binFreq[0][i] = ((binWall[i] + binWall[(i + 1)]) / 2.0D);
/* 8942 */       binFreq[1][i] = 0.0D;
/*      */     }
/* 8944 */     boolean test = true;
/*      */     
/* 8946 */     for (int i = 0; i < nPoints; i++) {
/* 8947 */       test = true;
/* 8948 */       int j = 0;
/* 8949 */       while (test) {
/* 8950 */         if (j == nBins - 1) {
/* 8951 */           if ((data[i] >= binWall[j]) && (data[i] <= binWall[(j + 1)] * (1.0D + histTol))) {
/* 8952 */             binFreq[1][j] += 1.0D;
/* 8953 */             dataCheck[i] = 1;
/* 8954 */             test = false;
/*      */           }
/*      */           
/*      */         }
/* 8958 */         else if ((data[i] >= binWall[j]) && (data[i] < binWall[(j + 1)])) {
/* 8959 */           binFreq[1][j] += 1.0D;
/* 8960 */           dataCheck[i] = 1;
/* 8961 */           test = false;
/*      */         }
/*      */         
/* 8964 */         if (test) {
/* 8965 */           if (j == nBins - 1) {
/* 8966 */             test = false;
/*      */           }
/*      */           else {
/* 8969 */             j++;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 8974 */     int nMissed = 0;
/* 8975 */     for (int i = 0; i < nPoints; i++) if (dataCheck[i] == 0) {
/* 8976 */         nMissed++;
/* 8977 */         System.out.println("p " + i + " " + data[i] + " " + binWall[0] + " " + binWall[nBins]);
/*      */       }
/* 8979 */     if (nMissed > 0) System.out.println(nMissed + " data points, outside histogram limits, excluded in Stat.histogramBins");
/* 8980 */     return binFreq;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double[][] histogramBins(double[] data, double binWidth)
/*      */   {
/* 8987 */     double dmin = Fmath.minimum(data);
/* 8988 */     double dmax = Fmath.maximum(data);
/* 8989 */     double span = dmax - dmin;
/* 8990 */     double binZero = dmin;
/* 8991 */     int nBins = (int)Math.ceil(span / binWidth);
/* 8992 */     double histoSpan = nBins * binWidth;
/* 8993 */     double rem = histoSpan - span;
/* 8994 */     if (rem >= 0.0D) {
/* 8995 */       binZero -= rem / 2.0D;
/*      */ 
/*      */     }
/* 8998 */     else if (Math.abs(rem) / span > histTol)
/*      */     {
/* 9000 */       boolean testBw = true;
/* 9001 */       double incr = histTol / nBins;
/* 9002 */       int iTest = 0;
/* 9003 */       while (testBw) {
/* 9004 */         binWidth += incr;
/* 9005 */         histoSpan = nBins * binWidth;
/* 9006 */         rem = histoSpan - span;
/* 9007 */         if (rem < 0.0D) {
/* 9008 */           iTest++;
/* 9009 */           if (iTest > 1000) {
/* 9010 */             testBw = false;
/* 9011 */             System.out.println("histogram method could not encompass all data within histogram\nContact Michael thomas Flanagan");
/*      */           }
/*      */         }
/*      */         else {
/* 9015 */           testBw = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 9021 */     return histogramBins(data, binWidth, binZero);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/Regression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */