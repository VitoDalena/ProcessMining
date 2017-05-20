/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.io.Db;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
/*      */ import flanagan.math.PsRandom;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Toolkit;
/*      */ import java.io.PrintStream;
/*      */ import java.text.DateFormat;
/*      */ import java.util.Date;
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
/*      */ public class PCA
/*      */   extends Scores
/*      */ {
/*   54 */   private Matrix data = null;
/*   55 */   private Matrix dataMinusMeans = null;
/*   56 */   private Matrix dataMinusMeansTranspose = null;
/*   57 */   private Matrix covarianceMatrix = null;
/*   58 */   private Matrix correlationMatrix = null;
/*      */   
/*   60 */   private double[] eigenValues = null;
/*   61 */   private double[] orderedEigenValues = null;
/*   62 */   private int[] eigenValueIndices = null;
/*   63 */   private double eigenValueTotal = 0.0D;
/*      */   
/*   65 */   private double[] rotatedEigenValues = null;
/*   66 */   private double[] usRotatedEigenValues = null;
/*      */   
/*      */ 
/*   69 */   private int nMonteCarlo = 200;
/*   70 */   private double[][] randomEigenValues = (double[][])null;
/*   71 */   private double[] randomEigenValuesMeans = null;
/*   72 */   private double[] randomEigenValuesSDs = null;
/*   73 */   private double[] randomEigenValuesPercentiles = null;
/*   74 */   private double percentile = 95.0D;
/*   75 */   private boolean gaussianDeviates = false;
/*      */   
/*   77 */   private double[] proportionPercentage = null;
/*   78 */   private double[] cumulativePercentage = null;
/*   79 */   private double[] rotatedProportionPercentage = null;
/*   80 */   private double[] rotatedCumulativePercentage = null;
/*      */   
/*   82 */   private double[][] eigenVectorsAsColumns = (double[][])null;
/*   83 */   private double[][] eigenVectorsAsRows = (double[][])null;
/*      */   
/*   85 */   private double[][] orderedEigenVectorsAsColumns = (double[][])null;
/*   86 */   private double[][] orderedEigenVectorsAsRows = (double[][])null;
/*      */   
/*   88 */   private double[][] loadingFactorsAsColumns = (double[][])null;
/*   89 */   private double[][] loadingFactorsAsRows = (double[][])null;
/*      */   
/*   91 */   private double[][] rotatedLoadingFactorsAsColumns = (double[][])null;
/*   92 */   private double[][] rotatedLoadingFactorsAsRows = (double[][])null;
/*   93 */   private double[][] usRotatedLoadingFactorsAsColumns = (double[][])null;
/*   94 */   private double[][] usRotatedLoadingFactorsAsRows = (double[][])null;
/*      */   
/*   96 */   private double[] communalities = null;
/*      */   
/*      */ 
/*   99 */   private boolean covRhoOption = false;
/*      */   
/*      */ 
/*  102 */   private int greaterThanOneLimit = 0;
/*  103 */   private int percentileCrossover = 0;
/*  104 */   private int meanCrossover = 0;
/*      */   
/*  106 */   private int nVarimaxMax = 1000;
/*  107 */   private int nVarimax = 0;
/*  108 */   private double varimaxTolerance = 1.0E-4D;
/*      */   
/*  110 */   private boolean varimaxOption = true;
/*      */   
/*  112 */   private boolean pcaDone = false;
/*  113 */   private boolean monteCarloDone = false;
/*  114 */   private boolean rotationDone = false;
/*      */   
/*      */   public PCA()
/*      */   {
/*  118 */     this.trunc = 4;
/*      */   }
/*      */   
/*      */ 
/*      */   public void useCovarianceMatrix()
/*      */   {
/*  124 */     this.covRhoOption = true;
/*      */   }
/*      */   
/*      */   public void useCorrelationMatrix()
/*      */   {
/*  129 */     this.covRhoOption = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void useNormalVarimax()
/*      */   {
/*  135 */     this.varimaxOption = true;
/*      */   }
/*      */   
/*      */   public void useRawVarimax()
/*      */   {
/*  140 */     this.varimaxOption = false;
/*      */   }
/*      */   
/*      */   public String getVarimaxOption()
/*      */   {
/*  145 */     if (this.varimaxOption) {
/*  146 */       return "normal varimax option";
/*      */     }
/*      */     
/*  149 */     return "raw varimax option";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNumberOfSimulations(int nSimul)
/*      */   {
/*  156 */     this.nMonteCarlo = nSimul;
/*      */   }
/*      */   
/*      */   public int getNumberOfSimulations()
/*      */   {
/*  161 */     return this.nMonteCarlo;
/*      */   }
/*      */   
/*      */   public void useGaussianDeviates()
/*      */   {
/*  166 */     this.gaussianDeviates = true;
/*      */   }
/*      */   
/*      */   public void useUniformDeviates()
/*      */   {
/*  171 */     this.gaussianDeviates = false;
/*      */   }
/*      */   
/*      */   public void setParallelAnalysisPercentileValue(double percent)
/*      */   {
/*  176 */     this.percentile = percent;
/*      */   }
/*      */   
/*      */   public double getParallelAnalysisPercentileValue()
/*      */   {
/*  181 */     return this.percentile;
/*      */   }
/*      */   
/*      */ 
/*      */   public void pca()
/*      */   {
/*  187 */     if (!this.pcaDone)
/*      */     {
/*  189 */       if (this.nItems == 1) throw new IllegalArgumentException("You have entered only one item - PCA is not meaningful");
/*  190 */       if (this.nPersons == 1) { throw new IllegalArgumentException("You have entered only one score or measurement source - PCA is not meaningful");
/*      */       }
/*      */       
/*  193 */       if (!this.dataPreprocessed) { preprocessData();
/*      */       }
/*      */       
/*  196 */       this.data = new Matrix(this.scores0);
/*      */       
/*      */ 
/*  199 */       this.dataMinusMeans = this.data.subtractRowMeans();
/*      */       
/*      */ 
/*  202 */       this.dataMinusMeansTranspose = this.dataMinusMeans.transpose();
/*      */       
/*      */ 
/*  205 */       this.covarianceMatrix = this.dataMinusMeans.times(this.dataMinusMeansTranspose);
/*  206 */       double denom = this.nPersons;
/*  207 */       if (!this.nFactorOption) denom -= 1.0D;
/*  208 */       this.covarianceMatrix = this.covarianceMatrix.times(1.0D / denom);
/*      */       
/*      */ 
/*  211 */       double[][] cov = this.covarianceMatrix.getArrayCopy();
/*  212 */       double[][] corr = new double[this.nItems][this.nItems];
/*  213 */       for (int i = 0; i < this.nItems; i++) {
/*  214 */         for (int j = 0; j < this.nItems; j++) {
/*  215 */           if (i == j) {
/*  216 */             corr[i][j] = 1.0D;
/*      */           }
/*      */           else {
/*  219 */             cov[i][j] /= Math.sqrt(cov[i][i] * cov[j][j]);
/*      */           }
/*      */         }
/*      */       }
/*  223 */       this.correlationMatrix = new Matrix(corr);
/*      */       
/*      */ 
/*  226 */       Matrix forEigen = null;
/*  227 */       if (this.covRhoOption) {
/*  228 */         forEigen = this.covarianceMatrix;
/*      */       }
/*      */       else {
/*  231 */         forEigen = this.correlationMatrix;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  236 */       this.eigenValues = forEigen.getEigenValues();
/*      */       
/*      */ 
/*  239 */       this.orderedEigenValues = forEigen.getSortedEigenValues();
/*      */       
/*      */ 
/*  242 */       this.eigenValueIndices = forEigen.eigenValueIndices();
/*      */       
/*      */ 
/*  245 */       this.eigenVectorsAsColumns = forEigen.getEigenVectorsAsColumns();
/*  246 */       this.eigenVectorsAsRows = forEigen.getEigenVectorsAsRows();
/*      */       
/*      */ 
/*  249 */       this.orderedEigenVectorsAsColumns = forEigen.getSortedEigenVectorsAsColumns();
/*  250 */       this.orderedEigenVectorsAsRows = forEigen.getSortedEigenVectorsAsRows();
/*      */       
/*      */ 
/*  253 */       ArrayMaths am = new ArrayMaths(this.orderedEigenValues);
/*  254 */       double total = am.sum();
/*  255 */       am = am.times(100.0D / total);
/*  256 */       this.proportionPercentage = am.array();
/*      */       
/*      */ 
/*  259 */       this.cumulativePercentage = new double[this.nItems];
/*  260 */       this.cumulativePercentage[0] = this.proportionPercentage[0];
/*  261 */       this.eigenValueTotal = 0.0D;
/*  262 */       for (int i = 1; i < this.nItems; i++) {
/*  263 */         this.cumulativePercentage[i] = (this.cumulativePercentage[(i - 1)] + this.proportionPercentage[i]);
/*  264 */         this.eigenValueTotal += this.eigenValues[i];
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  269 */       boolean test = true;
/*  270 */       int counter = 0;
/*  271 */       while (test) {
/*  272 */         if (this.orderedEigenValues[counter] < 1.0D) {
/*  273 */           this.greaterThanOneLimit = counter;
/*  274 */           test = false;
/*      */         }
/*      */         else {
/*  277 */           counter++;
/*  278 */           if (counter == this.nItems) {
/*  279 */             this.greaterThanOneLimit = counter;
/*  280 */             test = false;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  286 */       this.loadingFactorsAsColumns = new double[this.nItems][this.nItems];
/*  287 */       this.loadingFactorsAsRows = new double[this.nItems][this.nItems];
/*  288 */       for (int i = 0; i < this.nItems; i++) {
/*  289 */         for (int j = 0; j < this.nItems; j++) {
/*  290 */           this.loadingFactorsAsColumns[i][j] = (this.orderedEigenVectorsAsColumns[i][j] * Math.sqrt(Math.abs(this.orderedEigenValues[j])));
/*  291 */           this.loadingFactorsAsRows[i][j] = (this.orderedEigenVectorsAsRows[i][j] * Math.sqrt(Math.abs(this.orderedEigenValues[i])));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  296 */       this.communalities = new double[this.nItems];
/*  297 */       for (int k = 0; k < this.nItems; k++) {
/*  298 */         double sum = 0.0D;
/*  299 */         for (int j = 0; j < this.nItems; j++) sum += this.loadingFactorsAsRows[j][k] * this.loadingFactorsAsRows[j][k];
/*  300 */         this.communalities[k] = sum;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  305 */     this.pcaDone = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void monteCarlo()
/*      */   {
/*  311 */     if (!this.pcaDone) pca();
/*  312 */     double[] rowMeans = super.rawItemMeans();
/*  313 */     double[] rowSDs = super.rawItemStandardDeviations();
/*  314 */     double[][] randomData = new double[this.nItems][this.nPersons];
/*  315 */     this.randomEigenValues = new double[this.nMonteCarlo][this.nItems];
/*  316 */     PsRandom rr = new PsRandom();
/*  317 */     for (int i = 0; i < this.nMonteCarlo; i++) {
/*  318 */       for (int j = 0; j < this.nItems; j++) {
/*  319 */         if (this.gaussianDeviates) {
/*  320 */           randomData[j] = rr.gaussianArray(rowMeans[j], rowSDs[j], this.nPersons);
/*      */         }
/*      */         else {
/*  323 */           randomData[j] = rr.doubleArray(this.nPersons);
/*  324 */           randomData[j] = Stat.scale(randomData[j], rowMeans[j], rowSDs[j]);
/*      */         }
/*      */       }
/*  327 */       PCA pca = new PCA();
/*  328 */       if (this.covRhoOption) {
/*  329 */         pca.useCovarianceMatrix();
/*      */       }
/*      */       else {
/*  332 */         pca.useCorrelationMatrix();
/*      */       }
/*  334 */       pca.enterScoresAsRowPerItem(randomData);
/*  335 */       this.randomEigenValues[i] = pca.orderedEigenValues();
/*      */     }
/*      */     
/*  338 */     Matrix mat = new Matrix(this.randomEigenValues);
/*  339 */     this.randomEigenValuesMeans = mat.columnMeans();
/*  340 */     this.randomEigenValuesSDs = mat.columnStandardDeviations();
/*  341 */     this.randomEigenValuesPercentiles = new double[this.nItems];
/*      */     
/*  343 */     int pIndex1 = (int)Math.ceil(this.nMonteCarlo * this.percentile / 100.0D);
/*  344 */     int pIndex2 = pIndex1 - 1;
/*  345 */     double factor = this.percentile * this.nMonteCarlo / 100.0D - pIndex2;
/*  346 */     pIndex1--;
/*  347 */     pIndex2--;
/*  348 */     for (int j = 0; j < this.nItems; j++) {
/*  349 */       double[] ordered = new double[this.nMonteCarlo];
/*  350 */       for (int k = 0; k < this.nMonteCarlo; k++) ordered[k] = this.randomEigenValues[k][j];
/*  351 */       ArrayMaths am = new ArrayMaths(ordered);
/*  352 */       am = am.sort();
/*  353 */       ordered = am.array();
/*  354 */       this.randomEigenValuesPercentiles[j] = (ordered[pIndex2] + factor * (ordered[pIndex1] - ordered[pIndex2]));
/*      */     }
/*      */     
/*      */ 
/*  358 */     boolean test = true;
/*  359 */     int counter = 0;
/*  360 */     while (test) {
/*  361 */       if (this.orderedEigenValues[counter] <= this.randomEigenValuesPercentiles[counter]) {
/*  362 */         this.percentileCrossover = counter;
/*  363 */         test = false;
/*      */       }
/*      */       else {
/*  366 */         counter++;
/*  367 */         if (counter == this.nItems) {
/*  368 */           this.percentileCrossover = counter;
/*  369 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  375 */     test = true;
/*  376 */     counter = 0;
/*  377 */     while (test) {
/*  378 */       if (this.orderedEigenValues[counter] <= this.randomEigenValuesMeans[counter]) {
/*  379 */         this.meanCrossover = counter;
/*  380 */         test = false;
/*      */       }
/*      */       else {
/*  383 */         counter++;
/*  384 */         if (counter == this.nItems) {
/*  385 */           this.meanCrossover = counter;
/*  386 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  391 */     this.monteCarloDone = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void screePlotDataAlone()
/*      */   {
/*  398 */     if (!this.pcaDone) { pca();
/*      */     }
/*      */     
/*  401 */     double[] components = new double[this.nItems];
/*  402 */     for (int i = 0; i < this.nItems; i++) { components[i] = (i + 1);
/*      */     }
/*      */     
/*  405 */     PlotGraph pg = new PlotGraph(components, this.orderedEigenValues);
/*  406 */     pg.setGraphTitle("Principal Component Analysis Scree Plot");
/*  407 */     pg.setXaxisLegend("Component");
/*  408 */     pg.setYaxisLegend("Eigenvalues");
/*  409 */     pg.setLine(3);
/*  410 */     pg.setPoint(1);
/*  411 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   public void screePlot()
/*      */   {
/*  417 */     if (!this.pcaDone) pca();
/*  418 */     if (!this.monteCarloDone) { monteCarlo();
/*      */     }
/*      */     
/*  421 */     double[][] plotData = new double[6][this.nItems];
/*  422 */     double[] components = new double[this.nItems];
/*  423 */     for (int i = 0; i < this.nItems; i++) components[i] = (i + 1);
/*  424 */     plotData[0] = components;
/*  425 */     plotData[1] = this.orderedEigenValues;
/*  426 */     plotData[2] = components;
/*  427 */     plotData[3] = this.randomEigenValuesPercentiles;
/*  428 */     plotData[4] = components;
/*  429 */     plotData[5] = this.randomEigenValuesMeans;
/*      */     
/*      */ 
/*  432 */     PlotGraph pg = new PlotGraph(plotData);
/*  433 */     pg.setErrorBars(2, this.randomEigenValuesSDs);
/*  434 */     if (this.gaussianDeviates) {
/*  435 */       pg.setGraphTitle("Principal Component Analysis Scree Plot with Parallel Analysis using Gaussian deviates (" + this.nMonteCarlo + " simulations)");
/*      */     }
/*      */     else {
/*  438 */       pg.setGraphTitle("Principal Component Analysis Scree Plot with Parallel Analysis using uniform deviates (" + this.nMonteCarlo + " simulations)");
/*      */     }
/*  440 */     pg.setGraphTitle2("Closed squares - data eigenvalues; open circles = Monte Carlo eigenvalue " + this.percentile + "% percentiles; error bars = standard deviations about the Monte carlo means (crosses)");
/*  441 */     pg.setXaxisLegend("Component");
/*  442 */     pg.setYaxisLegend("Eigenvalue");
/*  443 */     int[] line = { 3, 0, 3 };
/*  444 */     pg.setLine(line);
/*  445 */     int[] point = { 5, 1, 7 };
/*  446 */     pg.setPoint(point);
/*  447 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setVarimaxTolerance(double tolerance)
/*      */   {
/*  454 */     this.varimaxTolerance = tolerance;
/*      */   }
/*      */   
/*      */   public void setVarimaxMaximumIterations(int max)
/*      */   {
/*  459 */     this.nVarimaxMax = max;
/*      */   }
/*      */   
/*      */   public int getVarimaxIterations()
/*      */   {
/*  464 */     return this.nVarimax;
/*      */   }
/*      */   
/*      */ 
/*      */   public void varimaxRotation(int nFactors)
/*      */   {
/*  470 */     if (!this.pcaDone) pca();
/*  471 */     if (this.varimaxOption) {
/*  472 */       normalVarimaxRotation(nFactors);
/*      */     }
/*      */     else {
/*  475 */       rawVarimaxRotation(nFactors);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void varimaxRotation(double[][] loadingFactorMatrix)
/*      */   {
/*  482 */     if (this.varimaxOption) System.out.println("Method varimaxRotation: communality weights not supplied - raw varimax option used");
/*  483 */     rawVarimaxRotationInHouse(loadingFactorMatrix);
/*      */   }
/*      */   
/*      */   public void varimaxRotation(double[][] loadingFactorMatrix, double[] communalityWeights)
/*      */   {
/*  488 */     if (this.varimaxOption) {
/*  489 */       normalVarimaxRotationInHouse(loadingFactorMatrix, communalityWeights);
/*      */     }
/*      */     else {
/*  492 */       System.out.println("Method varimaxRotation: raw varimax option chosen, supplied communality weights ignored");
/*  493 */       rawVarimaxRotationInHouse(loadingFactorMatrix);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void rawVarimaxRotation(int nFactors)
/*      */   {
/*  500 */     if (!this.pcaDone) pca();
/*  501 */     double[][] loadingFactorMatrix = new double[nFactors][this.nItems];
/*  502 */     for (int i = 0; i < nFactors; i++) loadingFactorMatrix[i] = this.loadingFactorsAsRows[i];
/*  503 */     double[] communalityWeights = new double[this.nItems];
/*  504 */     for (int i = 0; i < this.nItems; i++) communalityWeights[i] = 1.0D;
/*  505 */     normalVarimaxRotationInHouse(loadingFactorMatrix, communalityWeights);
/*      */   }
/*      */   
/*      */   private void rawVarimaxRotationInHouse(double[][] loadingFactorMatrix)
/*      */   {
/*  510 */     double[] communalityWeights = new double[this.nItems];
/*  511 */     for (int i = 0; i < this.nItems; i++) communalityWeights[i] = 1.0D;
/*  512 */     normalVarimaxRotationInHouse(loadingFactorMatrix, communalityWeights);
/*      */   }
/*      */   
/*      */   public void normalVarimaxRotation(int nFactors)
/*      */   {
/*  517 */     if (!this.pcaDone) pca();
/*  518 */     double[][] loadingFactorMatrix = new double[nFactors][this.nItems];
/*  519 */     for (int i = 0; i < nFactors; i++) loadingFactorMatrix[i] = this.loadingFactorsAsRows[i];
/*  520 */     double[] communalityWeights = new double[this.nItems];
/*  521 */     for (int i = 0; i < this.nItems; i++) {
/*  522 */       communalityWeights[i] = 0.0D;
/*  523 */       for (int j = 0; j < nFactors; j++) communalityWeights[i] += loadingFactorMatrix[j][i] * loadingFactorMatrix[j][i];
/*      */     }
/*  525 */     normalVarimaxRotationInHouse(loadingFactorMatrix, communalityWeights);
/*      */   }
/*      */   
/*      */   private void normalVarimaxRotationInHouse(double[][] loadingFactorMatrix, double[] communalityWeights)
/*      */   {
/*  530 */     if (!this.pcaDone) pca();
/*  531 */     int nRows = loadingFactorMatrix.length;
/*  532 */     int nColumns = loadingFactorMatrix[0].length;
/*  533 */     this.usRotatedLoadingFactorsAsRows = new double[nRows][nColumns];
/*  534 */     this.rotatedLoadingFactorsAsRows = new double[nRows][nColumns];
/*  535 */     this.usRotatedEigenValues = new double[nRows];
/*  536 */     this.rotatedEigenValues = new double[nRows];
/*  537 */     this.rotatedProportionPercentage = new double[nRows];
/*  538 */     this.rotatedCumulativePercentage = new double[nRows];
/*      */     
/*      */ 
/*  541 */     for (int j = 0; j < nColumns; j++) communalityWeights[j] = Math.sqrt(communalityWeights[j]);
/*  542 */     for (int i = 0; i < nRows; i++) {
/*  543 */       for (int j = 0; j < nColumns; j++) {
/*  544 */         loadingFactorMatrix[i][j] /= communalityWeights[j];
/*  545 */         this.usRotatedLoadingFactorsAsRows[i][j] = loadingFactorMatrix[i][j];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  550 */     double va = varimaxCriterion(this.usRotatedLoadingFactorsAsRows);
/*  551 */     double vaLast = 0.0D;
/*  552 */     double angle = 0.0D;
/*  553 */     boolean test = true;
/*  554 */     this.nVarimax = 0;
/*  555 */     while (test) {
/*  556 */       for (int i = 0; i < nRows - 1; i++) {
/*  557 */         for (int j = i + 1; j < nRows; j++) {
/*  558 */           angle = varimaxAngle(this.usRotatedLoadingFactorsAsRows, i, j);
/*  559 */           this.usRotatedLoadingFactorsAsRows = singleRotation(this.usRotatedLoadingFactorsAsRows, i, j, angle);
/*  560 */           va = varimaxCriterion(this.usRotatedLoadingFactorsAsRows);
/*      */         }
/*      */       }
/*  563 */       if (Math.abs(va - vaLast) < this.varimaxTolerance) {
/*  564 */         test = false;
/*      */       }
/*      */       else {
/*  567 */         vaLast = va;
/*  568 */         this.nVarimax += 1;
/*  569 */         if (this.nVarimax > this.nVarimaxMax) {
/*  570 */           test = false;
/*  571 */           System.out.println("Method varimaxRotation: maximum iterations " + this.nVarimaxMax + "exceeded");
/*  572 */           System.out.println("Current values returned");
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  579 */     this.usRotatedLoadingFactorsAsColumns = new double[nColumns][nRows];
/*  580 */     for (int i = 0; i < nRows; i++) {
/*  581 */       for (int j = 0; j < nColumns; j++) {
/*  582 */         this.usRotatedLoadingFactorsAsRows[i][j] *= communalityWeights[j];
/*  583 */         this.usRotatedLoadingFactorsAsColumns[j][i] = this.usRotatedLoadingFactorsAsRows[i][j];
/*  584 */         loadingFactorMatrix[i][j] *= communalityWeights[j];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  589 */     double usRotatedEigenValueTotal = 0.0D;
/*  590 */     double unRotatedEigenValueTotal = 0.0D;
/*  591 */     for (int i = 0; i < nRows; i++) {
/*  592 */       this.usRotatedEigenValues[i] = 0.0D;
/*  593 */       for (int j = 0; j < nColumns; j++) {
/*  594 */         this.usRotatedEigenValues[i] += this.usRotatedLoadingFactorsAsRows[i][j] * this.usRotatedLoadingFactorsAsRows[i][j];
/*      */       }
/*  596 */       usRotatedEigenValueTotal += this.usRotatedEigenValues[i];
/*  597 */       unRotatedEigenValueTotal += this.orderedEigenValues[i];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  602 */     double scale0 = Math.abs(unRotatedEigenValueTotal / usRotatedEigenValueTotal);
/*  603 */     double scale1 = Math.sqrt(scale0);
/*  604 */     for (int i = 0; i < nRows; i++) {
/*  605 */       this.rotatedEigenValues[i] = (scale0 * this.usRotatedEigenValues[i]);
/*  606 */       this.rotatedProportionPercentage[i] = (this.rotatedEigenValues[i] * 100.0D / this.eigenValueTotal);
/*  607 */       for (int j = 0; j < nColumns; j++) {
/*  608 */         this.rotatedLoadingFactorsAsRows[i][j] = (scale1 * this.usRotatedLoadingFactorsAsRows[i][j]);
/*      */       }
/*      */     }
/*  611 */     this.rotatedCumulativePercentage[0] = this.rotatedProportionPercentage[0];
/*  612 */     for (int i = 1; i < nRows; i++) { this.rotatedCumulativePercentage[i] = (this.rotatedCumulativePercentage[(i - 1)] + this.rotatedProportionPercentage[i]);
/*      */     }
/*  614 */     this.rotationDone = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double[][] rawVarimaxRotation(double[][] loadingFactorMatrix)
/*      */   {
/*  621 */     double tolerance = 1.0E-4D;
/*  622 */     int nIterMax = 1000;
/*  623 */     return rawVarimaxRotation(loadingFactorMatrix, tolerance, nIterMax);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[][] rawVarimaxRotation(double[][] loadingFactorMatrix, double tolerance, int nIterMax)
/*      */   {
/*  629 */     int nRows = loadingFactorMatrix.length;
/*  630 */     int nColumns = loadingFactorMatrix[0].length;
/*  631 */     double[] communalityWeights = new double[nColumns];
/*  632 */     for (int i = 0; i < nColumns; i++) {
/*  633 */       communalityWeights[i] = 0.0D;
/*  634 */       for (int j = 0; j < nRows; j++) communalityWeights[i] += loadingFactorMatrix[j][i] * loadingFactorMatrix[j][i];
/*      */     }
/*  636 */     return normalVarimaxRotation(loadingFactorMatrix, communalityWeights, tolerance, nIterMax);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[][] normalVarimaxRotation(double[][] loadingFactorMatrix, double[] communalityWeights)
/*      */   {
/*  642 */     double tolerance = 1.0E-4D;
/*  643 */     int nIterMax = 1000;
/*  644 */     return normalVarimaxRotation(loadingFactorMatrix, communalityWeights, tolerance, nIterMax);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[][] normalVarimaxRotation(double[][] loadingFactorMatrix, double[] communalityWeights, double tolerance, int nIterMax)
/*      */   {
/*  650 */     int nRows = loadingFactorMatrix.length;
/*  651 */     int nColumns = loadingFactorMatrix[0].length;
/*  652 */     for (int i = 1; i < nRows; i++) if (loadingFactorMatrix[i].length != nColumns) throw new IllegalArgumentException("All rows must be the same length");
/*  653 */     double[][] rotatedLoadingFactorsAsRows = new double[nRows][nColumns];
/*      */     
/*      */ 
/*  656 */     for (int j = 0; j < nColumns; j++) communalityWeights[j] = Math.sqrt(communalityWeights[j]);
/*  657 */     for (int i = 0; i < nRows; i++) {
/*  658 */       for (int j = 0; j < nColumns; j++) {
/*  659 */         loadingFactorMatrix[i][j] /= communalityWeights[j];
/*  660 */         rotatedLoadingFactorsAsRows[i][j] = loadingFactorMatrix[i][j];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  665 */     double va = varimaxCriterion(rotatedLoadingFactorsAsRows);
/*  666 */     double vaLast = 0.0D;
/*  667 */     double angle = 0.0D;
/*  668 */     boolean test = true;
/*  669 */     int nIter = 0;
/*  670 */     while (test) {
/*  671 */       for (int i = 0; i < nRows - 1; i++) {
/*  672 */         for (int j = i + 1; j < nRows; j++) {
/*  673 */           angle = varimaxAngle(rotatedLoadingFactorsAsRows, i, j);
/*  674 */           rotatedLoadingFactorsAsRows = singleRotation(rotatedLoadingFactorsAsRows, i, j, angle);
/*  675 */           va = varimaxCriterion(rotatedLoadingFactorsAsRows);
/*      */         }
/*      */       }
/*  678 */       if (Math.abs(va - vaLast) < tolerance) {
/*  679 */         test = false;
/*      */       }
/*      */       else {
/*  682 */         vaLast = va;
/*  683 */         nIter++;
/*  684 */         if (nIter > nIterMax) {
/*  685 */           test = false;
/*  686 */           System.out.println("Method varimaxRotation: maximum iterations " + nIterMax + "exceeded");
/*  687 */           System.out.println("Current values returned");
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  693 */     for (int i = 0; i < nRows; i++) {
/*  694 */       for (int j = 0; j < nColumns; j++) {
/*  695 */         rotatedLoadingFactorsAsRows[i][j] *= communalityWeights[j];
/*  696 */         loadingFactorMatrix[i][j] *= communalityWeights[j];
/*      */       }
/*      */     }
/*      */     
/*  700 */     return rotatedLoadingFactorsAsRows;
/*      */   }
/*      */   
/*      */   public static double[][] transposeMatrix(double[][] matrix)
/*      */   {
/*  705 */     int nRows = matrix.length;
/*  706 */     int nColumns = matrix[0].length;
/*  707 */     for (int i = 1; i < nRows; i++) if (matrix[i].length != nColumns) throw new IllegalArgumentException("All rows must be the same length");
/*  708 */     double[][] transpose = new double[nColumns][nRows];
/*  709 */     for (int i = 0; i < nRows; i++) {
/*  710 */       for (int j = 0; j < nColumns; j++) {
/*  711 */         transpose[j][i] = matrix[i][j];
/*      */       }
/*      */     }
/*  714 */     return transpose;
/*      */   }
/*      */   
/*      */   public static double varimaxCriterion(double[][] loadingFactorMatrix)
/*      */   {
/*  719 */     int nRows = loadingFactorMatrix.length;
/*  720 */     int nColumns = loadingFactorMatrix[0].length;
/*  721 */     double va1 = 0.0D;
/*  722 */     double va2 = 0.0D;
/*  723 */     double va3 = 0.0D;
/*  724 */     for (int j = 0; j < nRows; j++) {
/*  725 */       double sum1 = 0.0D;
/*  726 */       for (int k = 0; k < nColumns; k++) sum1 += Math.pow(loadingFactorMatrix[j][k], 4.0D);
/*  727 */       va1 += sum1;
/*      */     }
/*  729 */     va1 *= nColumns;
/*  730 */     for (int j = 0; j < nRows; j++) {
/*  731 */       double sum2 = 0.0D;
/*  732 */       for (int k = 0; k < nColumns; k++) sum2 += Math.pow(loadingFactorMatrix[j][k], 2.0D);
/*  733 */       va2 += sum2 * sum2;
/*      */     }
/*  735 */     va3 = va1 - va2;
/*  736 */     return va3;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double varimaxAngle(double[][] loadingFactorMatrix, int k, int l)
/*      */   {
/*  742 */     int nColumns = loadingFactorMatrix[0].length;
/*  743 */     double uTerm = 0.0D;
/*  744 */     double vTerm = 0.0D;
/*  745 */     double bigA = 0.0D;
/*  746 */     double bigB = 0.0D;
/*  747 */     double bigC = 0.0D;
/*  748 */     double bigD = 0.0D;
/*      */     
/*  750 */     for (int j = 0; j < nColumns; j++) {
/*  751 */       double lmjk = loadingFactorMatrix[k][j];
/*  752 */       double lmjl = loadingFactorMatrix[l][j];
/*  753 */       uTerm = lmjk * lmjk - lmjl * lmjl;
/*  754 */       vTerm = 2.0D * lmjk * lmjl;
/*  755 */       bigA += uTerm;
/*  756 */       bigB += vTerm;
/*  757 */       bigC += uTerm * uTerm - vTerm * vTerm;
/*  758 */       bigD += 2.0D * uTerm * vTerm;
/*      */     }
/*  760 */     double bigE = bigD - 2.0D * bigA * bigB / nColumns;
/*  761 */     double bigF = bigC - (bigA * bigA - bigB * bigB) / nColumns;
/*  762 */     double angle = 0.25D * Math.atan2(bigE, bigF);
/*  763 */     return angle;
/*      */   }
/*      */   
/*      */   public static double[][] singleRotation(double[][] loadingFactorMatrix, int k, int l, double angle)
/*      */   {
/*  768 */     int nRows = loadingFactorMatrix.length;
/*  769 */     int nColumns = loadingFactorMatrix[0].length;
/*  770 */     double[][] rotatedMatrix = new double[nRows][nColumns];
/*  771 */     for (int i = 0; i < nRows; i++) {
/*  772 */       for (int j = 0; j < nColumns; j++) {
/*  773 */         rotatedMatrix[i][j] = loadingFactorMatrix[i][j];
/*      */       }
/*      */     }
/*      */     
/*  777 */     double sinphi = Math.sin(angle);
/*  778 */     double cosphi = Math.cos(angle);
/*  779 */     for (int j = 0; j < nColumns; j++) {
/*  780 */       rotatedMatrix[k][j] = (loadingFactorMatrix[k][j] * cosphi + loadingFactorMatrix[l][j] * sinphi);
/*  781 */       rotatedMatrix[l][j] = (-loadingFactorMatrix[k][j] * sinphi + loadingFactorMatrix[l][j] * cosphi);
/*      */     }
/*  783 */     return rotatedMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] eigenValues()
/*      */   {
/*  791 */     if (!this.pcaDone) pca();
/*  792 */     return this.eigenValues;
/*      */   }
/*      */   
/*      */   public double[] orderedEigenValues()
/*      */   {
/*  797 */     if (!this.pcaDone) pca();
/*  798 */     return this.orderedEigenValues;
/*      */   }
/*      */   
/*      */   public int[] eigenValueIndices()
/*      */   {
/*  803 */     if (!this.pcaDone) pca();
/*  804 */     return this.eigenValueIndices;
/*      */   }
/*      */   
/*      */   public double eigenValueTotal()
/*      */   {
/*  809 */     if (!this.pcaDone) pca();
/*  810 */     return this.eigenValueTotal;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] proportionPercentage()
/*      */   {
/*  816 */     if (!this.pcaDone) pca();
/*  817 */     return this.proportionPercentage;
/*      */   }
/*      */   
/*      */   public double[] cumulativePercentage()
/*      */   {
/*  822 */     if (!this.pcaDone) pca();
/*  823 */     return this.cumulativePercentage;
/*      */   }
/*      */   
/*      */   public double[] rotatedEigenValues()
/*      */   {
/*  828 */     if (!this.rotationDone) throw new IllegalArgumentException("No rotation has been performed");
/*  829 */     return this.rotatedEigenValues;
/*      */   }
/*      */   
/*      */   public double[] rotatedProportionPercentage()
/*      */   {
/*  834 */     if (!this.rotationDone) throw new IllegalArgumentException("No rotation has been performed");
/*  835 */     return this.rotatedProportionPercentage;
/*      */   }
/*      */   
/*      */   public double[] rotatedCumulativePercentage()
/*      */   {
/*  840 */     if (!this.rotationDone) throw new IllegalArgumentException("No rotation has been performed");
/*  841 */     return this.rotatedCumulativePercentage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] eigenVectors()
/*      */   {
/*  849 */     if (!this.pcaDone) pca();
/*  850 */     return this.eigenVectorsAsColumns;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] eigenVectorsAsRows()
/*      */   {
/*  856 */     if (!this.pcaDone) pca();
/*  857 */     return this.eigenVectorsAsRows;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] orderedEigenVectorsAsColumns()
/*      */   {
/*  863 */     if (!this.pcaDone) pca();
/*  864 */     return this.orderedEigenVectorsAsColumns;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] orderedEigenVectors()
/*      */   {
/*  870 */     if (!this.pcaDone) pca();
/*  871 */     return this.orderedEigenVectorsAsColumns;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] orderedEigenVectorsAsRows()
/*      */   {
/*  877 */     if (!this.pcaDone) pca();
/*  878 */     return this.orderedEigenVectorsAsRows;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] loadingFactorsAsColumns()
/*      */   {
/*  884 */     if (!this.pcaDone) pca();
/*  885 */     return this.loadingFactorsAsColumns;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] loadingFactorsAsRows()
/*      */   {
/*  891 */     if (!this.pcaDone) pca();
/*  892 */     return this.loadingFactorsAsRows;
/*      */   }
/*      */   
/*      */   public double[][] rotatedLoadingFactorsAsColumns()
/*      */   {
/*  897 */     if (!this.rotationDone) throw new IllegalArgumentException("No rotation has been performed");
/*  898 */     return this.rotatedLoadingFactorsAsColumns;
/*      */   }
/*      */   
/*      */   public double[][] rotatedLoadingFactorsAsRows()
/*      */   {
/*  903 */     if (!this.rotationDone) throw new IllegalArgumentException("No rotation has been performed");
/*  904 */     return this.rotatedLoadingFactorsAsRows;
/*      */   }
/*      */   
/*      */   public double[] communalities()
/*      */   {
/*  909 */     if (!this.pcaDone) pca();
/*  910 */     return this.communalities;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix covarianceMatrix()
/*      */   {
/*  916 */     if (!this.pcaDone) pca();
/*  917 */     return this.covarianceMatrix;
/*      */   }
/*      */   
/*      */   public Matrix correlationMatrix()
/*      */   {
/*  922 */     if (!this.pcaDone) pca();
/*  923 */     return this.correlationMatrix;
/*      */   }
/*      */   
/*      */   public double[] monteCarloMeans()
/*      */   {
/*  928 */     if (!this.monteCarloDone) monteCarlo();
/*  929 */     return this.randomEigenValuesMeans;
/*      */   }
/*      */   
/*      */   public double[] monteCarloStandardDeviations()
/*      */   {
/*  934 */     if (!this.monteCarloDone) monteCarlo();
/*  935 */     return this.randomEigenValuesSDs;
/*      */   }
/*      */   
/*      */   public double[] monteCarloPercentiles()
/*      */   {
/*  940 */     if (!this.monteCarloDone) monteCarlo();
/*  941 */     return this.randomEigenValuesPercentiles;
/*      */   }
/*      */   
/*      */   public double[][] monteCarloEigenValues()
/*      */   {
/*  946 */     if (!this.monteCarloDone) monteCarlo();
/*  947 */     return this.randomEigenValues;
/*      */   }
/*      */   
/*      */   public Matrix originalData()
/*      */   {
/*  952 */     if (!this.pcaDone) pca();
/*  953 */     return this.data;
/*      */   }
/*      */   
/*      */   public Matrix xMatrix()
/*      */   {
/*  958 */     if (!this.pcaDone) pca();
/*  959 */     double denom = this.nItems;
/*  960 */     if (!this.nFactorOption) denom -= 1.0D;
/*  961 */     Matrix mat = this.dataMinusMeans.times(1.0D / Math.sqrt(denom));
/*  962 */     return mat;
/*      */   }
/*      */   
/*      */   public Matrix xMatrixTranspose()
/*      */   {
/*  967 */     if (!this.pcaDone) pca();
/*  968 */     double denom = this.nItems;
/*  969 */     if (!this.nFactorOption) denom -= 1.0D;
/*  970 */     Matrix mat = this.dataMinusMeansTranspose.times(1.0D / Math.sqrt(denom));
/*  971 */     return mat;
/*      */   }
/*      */   
/*      */   public int nEigenOneOrGreater()
/*      */   {
/*  976 */     if (!this.pcaDone) pca();
/*  977 */     return this.greaterThanOneLimit;
/*      */   }
/*      */   
/*      */   public int nMeanCrossover()
/*      */   {
/*  982 */     if (!this.monteCarloDone) monteCarlo();
/*  983 */     return this.meanCrossover;
/*      */   }
/*      */   
/*      */   public int nPercentileCrossover()
/*      */   {
/*  988 */     if (!this.monteCarloDone) monteCarlo();
/*  989 */     return this.percentileCrossover;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void analysis()
/*      */   {
/*  999 */     this.outputFilename = "PCAOutput";
/* 1000 */     if (this.fileOption == 1) {
/* 1001 */       this.outputFilename += ".txt";
/*      */     }
/*      */     else {
/* 1004 */       this.outputFilename += ".xls";
/*      */     }
/* 1006 */     String message1 = "Output file name for the analysis details:";
/* 1007 */     String message2 = "\nEnter the required name (as a single word) and click OK ";
/* 1008 */     String message3 = "\nor simply click OK for default value";
/* 1009 */     String message = message1 + message2 + message3;
/* 1010 */     String defaultName = this.outputFilename;
/* 1011 */     this.outputFilename = Db.readLine(message, defaultName);
/* 1012 */     analysis(this.outputFilename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void analysis(String filename)
/*      */   {
/* 1020 */     screePlot();
/*      */     
/*      */ 
/* 1023 */     this.outputFilename = filename;
/* 1024 */     String outputFilenameWithoutExtension = null;
/* 1025 */     String extension = null;
/* 1026 */     int pos = filename.indexOf('.');
/* 1027 */     if (pos == -1) {
/* 1028 */       outputFilenameWithoutExtension = filename;
/* 1029 */       if (this.fileOption == 1) {
/* 1030 */         this.outputFilename += ".txt";
/*      */       }
/*      */       else {
/* 1033 */         this.outputFilename += ".xls";
/*      */       }
/*      */     }
/*      */     else {
/* 1037 */       extension = filename.substring(pos).trim();
/*      */       
/* 1039 */       outputFilenameWithoutExtension = filename.substring(0, pos).trim();
/* 1040 */       if ((extension.equalsIgnoreCase(".xls")) && 
/* 1041 */         (this.fileOption == 1)) {
/* 1042 */         if (this.fileOptionSet) {
/* 1043 */           String message1 = "Your entered output file type is .xls";
/* 1044 */           String message2 = "\nbut you have chosen a .txt output";
/* 1045 */           String message = message1 + message2;
/* 1046 */           String headerComment = "Your output file name extension";
/* 1047 */           String[] comments = { message, "replace it with .txt [text file]" };
/* 1048 */           String[] boxTitles = { "Retain", ".txt" };
/* 1049 */           int defaultBox = 1;
/* 1050 */           int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/* 1051 */           if (opt == 2) this.outputFilename = (outputFilenameWithoutExtension + ".txt");
/*      */         }
/*      */         else {
/* 1054 */           this.fileOption = 2;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1059 */       if ((extension.equalsIgnoreCase(".txt")) && 
/* 1060 */         (this.fileOption == 2)) {
/* 1061 */         if (this.fileOptionSet) {
/* 1062 */           String message1 = "Your entered output file type is .txt";
/* 1063 */           String message2 = "\nbut you have chosen a .xls output";
/* 1064 */           String message = message1 + message2;
/* 1065 */           String headerComment = "Your output file name extension";
/* 1066 */           String[] comments = { message, "replace it with .xls [Excel file]" };
/* 1067 */           String[] boxTitles = { "Retain", ".xls" };
/* 1068 */           int defaultBox = 1;
/* 1069 */           int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/* 1070 */           if (opt == 2) this.outputFilename = (outputFilenameWithoutExtension + ".xls");
/*      */         }
/*      */         else {
/* 1073 */           this.fileOption = 1;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1078 */       if ((!extension.equalsIgnoreCase(".txt")) && (!extension.equalsIgnoreCase(".xls"))) {
/* 1079 */         String message1 = "Your extension is " + extension;
/* 1080 */         String message2 = "\n    Do you wish to retain it:";
/* 1081 */         String message = message1 + message2;
/* 1082 */         String headerComment = "Your output file name extension";
/* 1083 */         String[] comments = { message, "replace it with .txt [text file]", "replace it with .xls [MS Excel file]" };
/* 1084 */         String[] boxTitles = { "Retain", ".txt", ".xls" };
/* 1085 */         int defaultBox = 1;
/* 1086 */         int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/* 1087 */         switch (opt) {
/* 1088 */         case 1:  this.fileOption = 1;
/* 1089 */           break;
/* 1090 */         case 2:  this.outputFilename = (outputFilenameWithoutExtension + ".txt");
/* 1091 */           this.fileOption = 1;
/* 1092 */           break;
/* 1093 */         case 3:  this.outputFilename = (outputFilenameWithoutExtension + ".xls");
/* 1094 */           this.fileOption = 2;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     
/* 1100 */     if (this.fileOption == 1) {
/* 1101 */       analysisText();
/*      */     }
/*      */     else {
/* 1104 */       analysisExcel();
/*      */     }
/*      */     
/* 1107 */     System.out.println("The analysis has been written to the file " + this.outputFilename);
/*      */   }
/*      */   
/*      */ 
/*      */   private void analysisText()
/*      */   {
/* 1113 */     FileOutput fout = null;
/* 1114 */     if (this.fileNumberingSet) {
/* 1115 */       fout = new FileOutput(this.outputFilename, 'n');
/*      */     }
/*      */     else {
/* 1118 */       fout = new FileOutput(this.outputFilename);
/*      */     }
/*      */     
/*      */ 
/* 1122 */     if (!this.pcaDone) pca();
/* 1123 */     if (!this.monteCarloDone) { monteCarlo();
/*      */     }
/*      */     
/* 1126 */     fout.println("PRINCIPAL COMPONENT ANALYSIS");
/* 1127 */     fout.println("Program: PCA - Analysis Output");
/* 1128 */     for (int i = 0; i < this.titleLines; i++) fout.println(this.title[i]);
/* 1129 */     Date d = new Date();
/* 1130 */     String day = DateFormat.getDateInstance().format(d);
/* 1131 */     String tim = DateFormat.getTimeInstance().format(d);
/* 1132 */     fout.println("Program executed at " + tim + " on " + day);
/* 1133 */     fout.println();
/* 1134 */     if (this.covRhoOption) {
/* 1135 */       fout.println("Covariance matrix used");
/*      */     }
/*      */     else {
/* 1138 */       fout.println("Correlation matrix used");
/*      */     }
/* 1140 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 1144 */     int field1 = 10;
/* 1145 */     int field2 = 12;
/* 1146 */     int field3 = 2;
/*      */     
/* 1148 */     fout.println("ALL EIGENVALUES");
/*      */     
/* 1150 */     fout.print("Component ", field1);
/* 1151 */     fout.print("Unordered ", field1);
/* 1152 */     fout.print("Eigenvalue ", field2);
/* 1153 */     fout.print("Proportion ", field2);
/* 1154 */     fout.print("Cumulative ", field2);
/* 1155 */     fout.println("Difference ");
/*      */     
/* 1157 */     fout.print(" ", field1);
/* 1158 */     fout.print("index", field1);
/* 1159 */     fout.print(" ", field2);
/* 1160 */     fout.print("as % ", field2);
/* 1161 */     fout.print("percentage ", field2);
/* 1162 */     fout.println(" ");
/*      */     
/*      */ 
/*      */ 
/* 1166 */     for (int i = 0; i < this.nItems; i++) {
/* 1167 */       fout.print(i + 1, field1);
/* 1168 */       fout.print(this.eigenValueIndices[i] + 1, field1);
/* 1169 */       fout.print(Fmath.truncate(this.orderedEigenValues[i], this.trunc), field2);
/* 1170 */       fout.print(Fmath.truncate(this.proportionPercentage[i], this.trunc), field2);
/* 1171 */       fout.print(Fmath.truncate(this.cumulativePercentage[i], this.trunc), field2);
/* 1172 */       if (i < this.nItems - 1) {
/* 1173 */         fout.print(Fmath.truncate(this.orderedEigenValues[i] - this.orderedEigenValues[(i + 1)], this.trunc), field2);
/*      */       }
/*      */       else {
/* 1176 */         fout.print(" ", field2);
/*      */       }
/* 1178 */       fout.print(" ", field3);
/*      */       
/* 1180 */       fout.println();
/*      */     }
/* 1182 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 1186 */     int nMax = this.greaterThanOneLimit;
/* 1187 */     if (nMax < this.meanCrossover) nMax = this.meanCrossover;
/* 1188 */     if (nMax < this.percentileCrossover) nMax = this.percentileCrossover;
/* 1189 */     fout.println("EXTRACTED EIGENVALUES");
/* 1190 */     fout.print(" ", field1);
/* 1191 */     fout.print("Greater than unity", 3 * field2 + field3);
/* 1192 */     fout.print("Greater than Monte Carlo Mean ", 3 * field2 + field3);
/* 1193 */     fout.println("Greater than Monte Carlo Percentile");
/*      */     
/* 1195 */     fout.print("Component ", field1);
/* 1196 */     fout.print("Eigenvalue ", field2);
/* 1197 */     fout.print("Proportion ", field2);
/* 1198 */     fout.print("Cumulative ", field2);
/* 1199 */     fout.print(" ", field3);
/*      */     
/* 1201 */     fout.print("Eigenvalue ", field2);
/* 1202 */     fout.print("Proportion ", field2);
/* 1203 */     fout.print("Cumulative ", field2);
/* 1204 */     fout.print(" ", field3);
/*      */     
/* 1206 */     fout.print("Eigenvalue ", field2);
/* 1207 */     fout.print("Proportion ", field2);
/* 1208 */     fout.print("Cumulative ", field2);
/* 1209 */     fout.println(" ");
/*      */     
/* 1211 */     fout.print(" ", field1);
/* 1212 */     fout.print(" ", field2);
/* 1213 */     fout.print("as % ", field2);
/* 1214 */     fout.print("percentage ", field2);
/* 1215 */     fout.print(" ", field3);
/*      */     
/* 1217 */     fout.print(" ", field2);
/* 1218 */     fout.print("as % ", field2);
/* 1219 */     fout.print("percentage ", field2);
/* 1220 */     fout.print(" ", field3);
/*      */     
/* 1222 */     fout.print(" ", field2);
/* 1223 */     fout.print("as % ", field2);
/* 1224 */     fout.print("percentage ", field2);
/* 1225 */     fout.println(" ");
/*      */     
/* 1227 */     int ii = 0;
/* 1228 */     while (ii < nMax) {
/* 1229 */       fout.print(ii + 1, field1);
/*      */       
/* 1231 */       if (ii < this.greaterThanOneLimit) {
/* 1232 */         fout.print(Fmath.truncate(this.orderedEigenValues[ii], this.trunc), field2);
/* 1233 */         fout.print(Fmath.truncate(this.proportionPercentage[ii], this.trunc), field2);
/* 1234 */         fout.print(Fmath.truncate(this.cumulativePercentage[ii], this.trunc), field2 + field3);
/*      */       }
/*      */       
/* 1237 */       if (ii < this.meanCrossover) {
/* 1238 */         fout.print(Fmath.truncate(this.orderedEigenValues[ii], this.trunc), field2);
/* 1239 */         fout.print(Fmath.truncate(this.proportionPercentage[ii], this.trunc), field2);
/* 1240 */         fout.print(Fmath.truncate(this.cumulativePercentage[ii], this.trunc), field2 + field3);
/*      */       }
/*      */       
/* 1243 */       if (ii < this.percentileCrossover) {
/* 1244 */         fout.print(Fmath.truncate(this.orderedEigenValues[ii], this.trunc), field2);
/* 1245 */         fout.print(Fmath.truncate(this.proportionPercentage[ii], this.trunc), field2);
/* 1246 */         fout.print(Fmath.truncate(this.cumulativePercentage[ii], this.trunc));
/*      */       }
/* 1248 */       fout.println();
/* 1249 */       ii++;
/*      */     }
/* 1251 */     fout.println();
/*      */     
/*      */ 
/* 1254 */     fout.println("PARALLEL ANALYSIS");
/* 1255 */     fout.println("Number of simulations = " + this.nMonteCarlo);
/* 1256 */     if (this.gaussianDeviates) {
/* 1257 */       fout.println("Gaussian random deviates used");
/*      */     }
/*      */     else {
/* 1260 */       fout.println("Uniform random deviates used");
/*      */     }
/* 1262 */     fout.println("Percentile value used = " + this.percentile + " %");
/*      */     
/* 1264 */     fout.println();
/* 1265 */     fout.print("Component ", field1);
/* 1266 */     fout.print("Data ", field2);
/* 1267 */     fout.print("Proportion ", field2);
/* 1268 */     fout.print("Cumulative ", field2);
/* 1269 */     fout.print(" ", field3);
/* 1270 */     fout.print("Data ", field2);
/* 1271 */     fout.print("Monte Carlo ", field2);
/* 1272 */     fout.print("Monte Carlo ", field2);
/* 1273 */     fout.println("Monte Carlo ");
/*      */     
/* 1275 */     fout.print(" ", field1);
/* 1276 */     fout.print("Eigenvalue ", field2);
/* 1277 */     fout.print("as % ", field2);
/* 1278 */     fout.print("percentage ", field2);
/* 1279 */     fout.print(" ", field3);
/* 1280 */     fout.print("Eigenvalue ", field2);
/* 1281 */     fout.print("Eigenvalue ", field2);
/* 1282 */     fout.print("Eigenvalue ", field2);
/* 1283 */     fout.println("Eigenvalue ");
/*      */     
/* 1285 */     fout.print(" ", field1);
/* 1286 */     fout.print(" ", field2);
/* 1287 */     fout.print(" ", field2);
/* 1288 */     fout.print(" ", field2);
/* 1289 */     fout.print(" ", field3);
/* 1290 */     fout.print(" ", field2);
/* 1291 */     fout.print("Percentile ", field2);
/* 1292 */     fout.print("Mean ", field2);
/* 1293 */     fout.println("Standard Deviation ");
/*      */     
/* 1295 */     for (int i = 0; i < this.nItems; i++) {
/* 1296 */       fout.print(i + 1, field1);
/* 1297 */       fout.print(Fmath.truncate(this.orderedEigenValues[i], this.trunc), field2);
/* 1298 */       fout.print(Fmath.truncate(this.proportionPercentage[i], this.trunc), field2);
/* 1299 */       fout.print(Fmath.truncate(this.cumulativePercentage[i], this.trunc), field2);
/* 1300 */       fout.print(" ", field3);
/* 1301 */       fout.print(Fmath.truncate(this.orderedEigenValues[i], this.trunc), field2);
/* 1302 */       fout.print(Fmath.truncate(this.randomEigenValuesPercentiles[i], this.trunc), field2);
/* 1303 */       fout.print(Fmath.truncate(this.randomEigenValuesMeans[i], this.trunc), field2);
/* 1304 */       fout.println(Fmath.truncate(this.randomEigenValuesSDs[i], this.trunc));
/*      */     }
/* 1306 */     fout.println();
/*      */     
/*      */ 
/* 1309 */     fout.println("CORRELATION MATRIX");
/* 1310 */     fout.println("Original component indices in parenthesis");
/* 1311 */     fout.println();
/* 1312 */     fout.print(" ", field1);
/* 1313 */     fout.print("component", field1);
/* 1314 */     for (int i = 0; i < this.nItems; i++) fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", field2);
/* 1315 */     fout.println();
/* 1316 */     fout.println("component");
/* 1317 */     for (int i = 0; i < this.nItems; i++) {
/* 1318 */       fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", 2 * field1);
/* 1319 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.correlationMatrix.getElement(j, i), this.trunc), field2);
/* 1320 */       fout.println();
/*      */     }
/* 1322 */     fout.println();
/*      */     
/*      */ 
/* 1325 */     fout.println("COVARIANCE MATRIX");
/* 1326 */     fout.println("Original component indices in parenthesis");
/* 1327 */     fout.println();
/* 1328 */     fout.print(" ", field1);
/* 1329 */     fout.print("component", field1);
/* 1330 */     for (int i = 0; i < this.nItems; i++) fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", field2);
/* 1331 */     fout.println();
/* 1332 */     fout.println("component");
/* 1333 */     for (int i = 0; i < this.nItems; i++) {
/* 1334 */       fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", 2 * field1);
/* 1335 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.covarianceMatrix.getElement(j, i), this.trunc), field2);
/* 1336 */       fout.println();
/*      */     }
/*      */     
/* 1339 */     fout.println();
/*      */     
/*      */ 
/* 1342 */     fout.println("EIGENVECTORS");
/* 1343 */     fout.println("Original component indices in parenthesis");
/* 1344 */     fout.println("Vector corresponding to an ordered eigenvalues in each row");
/* 1345 */     fout.println();
/* 1346 */     fout.print(" ", field1);
/* 1347 */     fout.print("component", field1);
/* 1348 */     for (int i = 0; i < this.nItems; i++) fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", field2);
/* 1349 */     fout.println();
/* 1350 */     fout.println("component");
/* 1351 */     for (int i = 0; i < this.nItems; i++) {
/* 1352 */       fout.print(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")", 2 * field1);
/* 1353 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.orderedEigenVectorsAsRows[i][j], this.trunc), field2);
/* 1354 */       fout.println();
/*      */     }
/* 1356 */     fout.println();
/*      */     
/*      */ 
/* 1359 */     fout.println("LOADING FACTORS");
/* 1360 */     fout.println("Original  indices in parenthesis");
/* 1361 */     fout.println("Loading factors corresponding to an ordered eigenvalues in each row");
/* 1362 */     fout.println();
/* 1363 */     fout.print(" ", field1);
/* 1364 */     fout.print("component", field1);
/* 1365 */     for (int i = 0; i < this.nItems; i++) fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", field2);
/* 1366 */     fout.print(" ", field1);
/* 1367 */     fout.print("Eigenvalue", field2);
/* 1368 */     fout.print("Proportion", field2);
/* 1369 */     fout.println("Cumulative %");
/* 1370 */     fout.println("factor");
/* 1371 */     for (int i = 0; i < this.nItems; i++) {
/* 1372 */       fout.print(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")", 2 * field1);
/* 1373 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.loadingFactorsAsRows[i][j], this.trunc), field2);
/* 1374 */       fout.print(" ", field1);
/* 1375 */       fout.print(Fmath.truncate(this.orderedEigenValues[i], this.trunc), field2);
/* 1376 */       fout.print(Fmath.truncate(this.proportionPercentage[i], this.trunc), field2);
/* 1377 */       fout.println(Fmath.truncate(this.cumulativePercentage[i], this.trunc));
/*      */     }
/* 1379 */     fout.println();
/*      */     
/*      */ 
/* 1382 */     fout.println("ROTATED LOADING FACTORS");
/* 1383 */     if (this.varimaxOption) {
/* 1384 */       fout.println("NORMAL VARIMAX");
/*      */     }
/*      */     else {
/* 1387 */       fout.println("RAW VARIMAX");
/*      */     }
/*      */     
/* 1390 */     String message = "The ordered eigenvalues with Monte Carlo means and percentiles in parenthesis";
/* 1391 */     message = message + "\n (Total number of eigenvalues = " + this.nItems + ")";
/* 1392 */     int nDisplay = this.nItems;
/* 1393 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1394 */     int screenHeight = screenSize.height;
/* 1395 */     int nDisplayLimit = 20 * screenHeight / 800;
/* 1396 */     if (nDisplay > nDisplay) nDisplay = nDisplayLimit;
/* 1397 */     for (int i = 0; i < nDisplay; i++) {
/* 1398 */       message = message + "\n " + Fmath.truncate(this.orderedEigenValues[i], 4) + " (" + Fmath.truncate(this.randomEigenValuesMeans[i], 4) + "  " + Fmath.truncate(this.randomEigenValuesPercentiles[i], 4) + ")";
/*      */     }
/* 1400 */     if (nDisplay < this.nItems) message = message + "\n . . . ";
/* 1401 */     message = message + "\nEnter number of eigenvalues to be extracted";
/* 1402 */     int nExtracted = this.greaterThanOneLimit;
/* 1403 */     nExtracted = Db.readInt(message, nExtracted);
/* 1404 */     varimaxRotation(nExtracted);
/*      */     
/* 1406 */     fout.println("Varimax rotation for " + nExtracted + " extracted factors");
/* 1407 */     fout.println("Rotated loading factors and eigenvalues scaled to ensure total 'rotated variance' matches unrotated variance for the extracted factors");
/* 1408 */     fout.println("Original  indices in parenthesis");
/* 1409 */     fout.println();
/* 1410 */     fout.print(" ", field1);
/* 1411 */     fout.print("component", field1);
/* 1412 */     for (int i = 0; i < this.nItems; i++) fout.print(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")", field2);
/* 1413 */     fout.print(" ", field1);
/* 1414 */     fout.print("Eigenvalue", field2);
/* 1415 */     fout.print("Proportion", field2);
/* 1416 */     fout.println("Cumulative %");
/* 1417 */     fout.println("factor");
/*      */     
/* 1419 */     for (int i = 0; i < nExtracted; i++) {
/* 1420 */       fout.print(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")", 2 * field1);
/* 1421 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.rotatedLoadingFactorsAsRows[i][j], this.trunc), field2);
/* 1422 */       fout.print(" ", field1);
/* 1423 */       fout.print(Fmath.truncate(this.rotatedEigenValues[i], this.trunc), field2);
/* 1424 */       fout.print(Fmath.truncate(this.rotatedProportionPercentage[i], this.trunc), field2);
/* 1425 */       fout.println(Fmath.truncate(this.rotatedCumulativePercentage[i], this.trunc));
/*      */     }
/* 1427 */     fout.println();
/*      */     
/*      */ 
/* 1430 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   private void analysisExcel()
/*      */   {
/* 1436 */     FileOutput fout = null;
/* 1437 */     if (this.fileNumberingSet) {
/* 1438 */       fout = new FileOutput(this.outputFilename, 'n');
/*      */     }
/*      */     else {
/* 1441 */       fout = new FileOutput(this.outputFilename);
/*      */     }
/*      */     
/*      */ 
/* 1445 */     if (!this.pcaDone) pca();
/* 1446 */     if (!this.monteCarloDone) { monteCarlo();
/*      */     }
/*      */     
/* 1449 */     fout.println("PRINCIPAL COMPONENT ANALYSIS");
/* 1450 */     fout.println("Program: PCA - Analysis Output");
/* 1451 */     for (int i = 0; i < this.titleLines; i++) fout.println(this.title[i]);
/* 1452 */     Date d = new Date();
/* 1453 */     String day = DateFormat.getDateInstance().format(d);
/* 1454 */     String tim = DateFormat.getTimeInstance().format(d);
/* 1455 */     fout.println("Program executed at " + tim + " on " + day);
/* 1456 */     fout.println();
/* 1457 */     if (this.covRhoOption) {
/* 1458 */       fout.println("Covariance matrix used");
/*      */     }
/*      */     else {
/* 1461 */       fout.println("Correlation matrix used");
/*      */     }
/* 1463 */     fout.println();
/*      */     
/*      */ 
/* 1466 */     fout.println("ALL EIGENVALUES");
/*      */     
/* 1468 */     fout.printtab("Component ");
/* 1469 */     fout.printtab("Unordered ");
/* 1470 */     fout.printtab("Eigenvalue ");
/* 1471 */     fout.printtab("Proportion ");
/* 1472 */     fout.printtab("Cumulative ");
/* 1473 */     fout.println("Difference ");
/*      */     
/* 1475 */     fout.printtab(" ");
/* 1476 */     fout.printtab("index");
/* 1477 */     fout.printtab(" ");
/* 1478 */     fout.printtab("as % ");
/* 1479 */     fout.printtab("percentage ");
/* 1480 */     fout.println(" ");
/*      */     
/*      */ 
/*      */ 
/* 1484 */     for (int i = 0; i < this.nItems; i++) {
/* 1485 */       fout.printtab(i + 1);
/* 1486 */       fout.printtab(this.eigenValueIndices[i] + 1);
/* 1487 */       fout.printtab(Fmath.truncate(this.orderedEigenValues[i], this.trunc));
/* 1488 */       fout.printtab(Fmath.truncate(this.proportionPercentage[i], this.trunc));
/* 1489 */       fout.printtab(Fmath.truncate(this.cumulativePercentage[i], this.trunc));
/* 1490 */       if (i < this.nItems - 1) {
/* 1491 */         fout.printtab(Fmath.truncate(this.orderedEigenValues[i] - this.orderedEigenValues[(i + 1)], this.trunc));
/*      */       }
/*      */       else {
/* 1494 */         fout.printtab(" ");
/*      */       }
/* 1496 */       fout.printtab(" ");
/*      */       
/* 1498 */       fout.println();
/*      */     }
/* 1500 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 1504 */     int nMax = this.greaterThanOneLimit;
/* 1505 */     if (nMax < this.meanCrossover) nMax = this.meanCrossover;
/* 1506 */     if (nMax < this.percentileCrossover) nMax = this.percentileCrossover;
/* 1507 */     fout.println("EXTRACTED EIGENVALUES");
/* 1508 */     fout.printtab(" ");
/* 1509 */     fout.printtab("Greater than unity");
/* 1510 */     fout.printtab(" ");fout.printtab(" ");fout.printtab(" ");
/* 1511 */     fout.printtab("Greater than Monte Carlo Mean ");
/* 1512 */     fout.printtab(" ");fout.printtab(" ");fout.printtab(" ");
/* 1513 */     fout.println("Greater than Monte Carlo Percentile");
/*      */     
/* 1515 */     fout.printtab("Component ");
/* 1516 */     fout.printtab("Eigenvalue ");
/* 1517 */     fout.printtab("Proportion ");
/* 1518 */     fout.printtab("Cumulative ");
/* 1519 */     fout.printtab(" ");
/*      */     
/* 1521 */     fout.printtab("Eigenvalue ");
/* 1522 */     fout.printtab("Proportion ");
/* 1523 */     fout.printtab("Cumulative ");
/* 1524 */     fout.printtab(" ");
/*      */     
/* 1526 */     fout.printtab("Eigenvalue ");
/* 1527 */     fout.printtab("Proportion ");
/* 1528 */     fout.printtab("Cumulative ");
/* 1529 */     fout.println(" ");
/*      */     
/* 1531 */     fout.printtab(" ");
/* 1532 */     fout.printtab(" ");
/* 1533 */     fout.printtab("as % ");
/* 1534 */     fout.printtab("percentage ");
/* 1535 */     fout.printtab(" ");
/*      */     
/* 1537 */     fout.printtab(" ");
/* 1538 */     fout.printtab("as % ");
/* 1539 */     fout.printtab("percentage ");
/* 1540 */     fout.printtab(" ");
/*      */     
/* 1542 */     fout.printtab(" ");
/* 1543 */     fout.printtab("as % ");
/* 1544 */     fout.printtab("percentage ");
/* 1545 */     fout.println(" ");
/*      */     
/* 1547 */     int ii = 0;
/* 1548 */     while (ii < nMax) {
/* 1549 */       fout.printtab(ii + 1);
/*      */       
/* 1551 */       if (ii < this.greaterThanOneLimit) {
/* 1552 */         fout.printtab(Fmath.truncate(this.orderedEigenValues[ii], this.trunc));
/* 1553 */         fout.printtab(Fmath.truncate(this.proportionPercentage[ii], this.trunc));
/* 1554 */         fout.printtab(Fmath.truncate(this.cumulativePercentage[ii], this.trunc));
/* 1555 */         fout.printtab(" ");
/*      */       }
/*      */       
/* 1558 */       if (ii < this.meanCrossover) {
/* 1559 */         fout.printtab(Fmath.truncate(this.orderedEigenValues[ii], this.trunc));
/* 1560 */         fout.printtab(Fmath.truncate(this.proportionPercentage[ii], this.trunc));
/* 1561 */         fout.printtab(Fmath.truncate(this.cumulativePercentage[ii], this.trunc));
/* 1562 */         fout.printtab(" ");
/*      */       }
/*      */       
/* 1565 */       if (ii < this.percentileCrossover) {
/* 1566 */         fout.printtab(Fmath.truncate(this.orderedEigenValues[ii], this.trunc));
/* 1567 */         fout.printtab(Fmath.truncate(this.proportionPercentage[ii], this.trunc));
/* 1568 */         fout.printtab(Fmath.truncate(this.cumulativePercentage[ii], this.trunc));
/*      */       }
/* 1570 */       fout.println();
/* 1571 */       ii++;
/*      */     }
/* 1573 */     fout.println();
/*      */     
/*      */ 
/* 1576 */     fout.println("PARALLEL ANALYSIS");
/* 1577 */     fout.println("Number of simulations = " + this.nMonteCarlo);
/* 1578 */     if (this.gaussianDeviates) {
/* 1579 */       fout.println("Gaussian random deviates used");
/*      */     }
/*      */     else {
/* 1582 */       fout.println("Uniform random deviates used");
/*      */     }
/* 1584 */     fout.println("Percentile value used = " + this.percentile + " %");
/*      */     
/* 1586 */     fout.println();
/* 1587 */     fout.printtab("Component ");
/* 1588 */     fout.printtab("Data ");
/* 1589 */     fout.printtab("Proportion ");
/* 1590 */     fout.printtab("Cumulative ");
/* 1591 */     fout.printtab(" ");
/* 1592 */     fout.printtab("Data ");
/* 1593 */     fout.printtab("Monte Carlo ");
/* 1594 */     fout.printtab("Monte Carlo ");
/* 1595 */     fout.println("Monte Carlo ");
/*      */     
/* 1597 */     fout.printtab(" ");
/* 1598 */     fout.printtab("Eigenvalue ");
/* 1599 */     fout.printtab("as % ");
/* 1600 */     fout.printtab("percentage ");
/* 1601 */     fout.printtab(" ");
/* 1602 */     fout.printtab("Eigenvalue ");
/* 1603 */     fout.printtab("Eigenvalue ");
/* 1604 */     fout.printtab("Eigenvalue ");
/* 1605 */     fout.println("Eigenvalue ");
/*      */     
/* 1607 */     fout.printtab(" ");
/* 1608 */     fout.printtab(" ");
/* 1609 */     fout.printtab(" ");
/* 1610 */     fout.printtab(" ");
/* 1611 */     fout.printtab(" ");
/* 1612 */     fout.printtab(" ");
/* 1613 */     fout.printtab("Percentile ");
/* 1614 */     fout.printtab("Mean ");
/* 1615 */     fout.println("Standard Deviation ");
/*      */     
/* 1617 */     for (int i = 0; i < this.nItems; i++) {
/* 1618 */       fout.printtab(i + 1);
/* 1619 */       fout.printtab(Fmath.truncate(this.orderedEigenValues[i], this.trunc));
/* 1620 */       fout.printtab(Fmath.truncate(this.proportionPercentage[i], this.trunc));
/* 1621 */       fout.printtab(Fmath.truncate(this.cumulativePercentage[i], this.trunc));
/* 1622 */       fout.printtab(" ");
/* 1623 */       fout.printtab(Fmath.truncate(this.orderedEigenValues[i], this.trunc));
/* 1624 */       fout.printtab(Fmath.truncate(this.randomEigenValuesPercentiles[i], this.trunc));
/* 1625 */       fout.printtab(Fmath.truncate(this.randomEigenValuesMeans[i], this.trunc));
/* 1626 */       fout.println(Fmath.truncate(this.randomEigenValuesSDs[i], this.trunc));
/*      */     }
/* 1628 */     fout.println();
/*      */     
/*      */ 
/* 1631 */     fout.println("CORRELATION MATRIX");
/* 1632 */     fout.println("Original component indices in parenthesis");
/* 1633 */     fout.println();
/* 1634 */     fout.printtab(" ");
/* 1635 */     fout.printtab("component");
/* 1636 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1637 */     fout.println();
/* 1638 */     fout.println("component");
/* 1639 */     for (int i = 0; i < this.nItems; i++) {
/* 1640 */       fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1641 */       fout.printtab(" ");
/* 1642 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.correlationMatrix.getElement(j, i), this.trunc));
/* 1643 */       fout.println();
/*      */     }
/* 1645 */     fout.println();
/*      */     
/*      */ 
/* 1648 */     fout.println("COVARIANCE MATRIX");
/* 1649 */     fout.println("Original component indices in parenthesis");
/* 1650 */     fout.println();
/* 1651 */     fout.printtab(" ");
/* 1652 */     fout.printtab("component");
/* 1653 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1654 */     fout.println();
/* 1655 */     fout.println("component");
/* 1656 */     for (int i = 0; i < this.nItems; i++) {
/* 1657 */       fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1658 */       fout.printtab(" ");
/* 1659 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.covarianceMatrix.getElement(j, i), this.trunc));
/* 1660 */       fout.println();
/*      */     }
/* 1662 */     fout.println();
/*      */     
/*      */ 
/* 1665 */     fout.println("EIGENVECTORS");
/* 1666 */     fout.println("Original component indices in parenthesis");
/* 1667 */     fout.println("Vector corresponding to an ordered eigenvalues in each row");
/* 1668 */     fout.println();
/* 1669 */     fout.printtab(" ");
/* 1670 */     fout.printtab("component");
/* 1671 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1672 */     fout.println();
/* 1673 */     fout.println("component");
/*      */     
/* 1675 */     for (int i = 0; i < this.nItems; i++) {
/* 1676 */       fout.printtab(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")");
/* 1677 */       fout.printtab(" ");
/* 1678 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.orderedEigenVectorsAsRows[i][j], this.trunc));
/* 1679 */       fout.println();
/*      */     }
/* 1681 */     fout.println();
/*      */     
/*      */ 
/* 1684 */     fout.println("LOADING FACTORS");
/* 1685 */     fout.println("Original  indices in parenthesis");
/* 1686 */     fout.println("Loading factors corresponding to an ordered eigenvalues in each row");
/* 1687 */     fout.println();
/* 1688 */     fout.printtab(" ");
/* 1689 */     fout.printtab("component");
/* 1690 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1691 */     fout.printtab(" ");
/* 1692 */     fout.printtab("Eigenvalue");
/* 1693 */     fout.printtab("% Proportion");
/* 1694 */     fout.println("Cumulative %");
/* 1695 */     fout.println("factor");
/* 1696 */     for (int i = 0; i < this.nItems; i++) {
/* 1697 */       fout.printtab(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")");
/* 1698 */       fout.printtab(" ");
/* 1699 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.loadingFactorsAsRows[i][j], this.trunc));
/* 1700 */       fout.printtab(" ");
/* 1701 */       fout.printtab(Fmath.truncate(this.orderedEigenValues[i], this.trunc));
/* 1702 */       fout.printtab(Fmath.truncate(this.proportionPercentage[i], this.trunc));
/* 1703 */       fout.println(Fmath.truncate(this.cumulativePercentage[i], this.trunc));
/*      */     }
/* 1705 */     fout.println();
/*      */     
/*      */ 
/* 1708 */     fout.println("ROTATED LOADING FACTORS");
/* 1709 */     if (this.varimaxOption) {
/* 1710 */       fout.println("NORMAL VARIMAX");
/*      */     }
/*      */     else {
/* 1713 */       fout.println("RAW VARIMAX");
/*      */     }
/*      */     
/* 1716 */     String message = "The ordered eigenvalues with Monte Carlo means and percentiles in parenthesis";
/* 1717 */     message = message + "\n (Total number of eigenvalues = " + this.nItems + ")";
/* 1718 */     int nDisplay = this.nItems;
/* 1719 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1720 */     int screenHeight = screenSize.height;
/* 1721 */     int nDisplayLimit = 20 * screenHeight / 800;
/* 1722 */     if (nDisplay > nDisplay) nDisplay = nDisplayLimit;
/* 1723 */     for (int i = 0; i < nDisplay; i++) {
/* 1724 */       message = message + "\n " + Fmath.truncate(this.orderedEigenValues[i], 4) + " (" + Fmath.truncate(this.randomEigenValuesMeans[i], 4) + "  " + Fmath.truncate(this.randomEigenValuesPercentiles[i], 4) + ")";
/*      */     }
/* 1726 */     if (nDisplay < this.nItems) message = message + "\n . . . ";
/* 1727 */     message = message + "\nEnter number of eigenvalues to be extracted";
/* 1728 */     int nExtracted = this.greaterThanOneLimit;
/* 1729 */     nExtracted = Db.readInt(message, nExtracted);
/* 1730 */     varimaxRotation(nExtracted);
/*      */     
/* 1732 */     fout.println("Varimax rotation for " + nExtracted + " extracted factors");
/* 1733 */     fout.println("Rotated loading factors and eigenvalues scaled to ensure total 'rotated variance' matches unrotated variance for the extracted factors");
/* 1734 */     fout.println("Original  indices in parenthesis");
/* 1735 */     fout.println();
/* 1736 */     fout.printtab(" ");
/* 1737 */     fout.printtab("component");
/* 1738 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.eigenValueIndices[i] + 1 + " (" + (i + 1) + ")");
/* 1739 */     fout.printtab(" ");
/* 1740 */     fout.printtab("Eigenvalue");
/* 1741 */     fout.printtab("% Proportion");
/* 1742 */     fout.println("Cumulative %");
/* 1743 */     fout.println("factor");
/* 1744 */     for (int i = 0; i < nExtracted; i++) {
/* 1745 */       fout.printtab(i + 1 + " (" + (this.eigenValueIndices[i] + 1) + ")");
/* 1746 */       fout.printtab(" ");
/* 1747 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.rotatedLoadingFactorsAsRows[i][j], this.trunc));
/* 1748 */       fout.printtab(" ");
/* 1749 */       fout.printtab(Fmath.truncate(this.rotatedEigenValues[i], this.trunc));
/* 1750 */       fout.printtab(Fmath.truncate(this.rotatedProportionPercentage[i], this.trunc));
/* 1751 */       fout.println(Fmath.truncate(this.rotatedCumulativePercentage[i], this.trunc));
/*      */     }
/* 1753 */     fout.println();
/*      */     
/* 1755 */     fout.close();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/PCA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */