/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
/*      */ import flanagan.math.Minimization;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
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
/*      */ public class ProbabilityPlot
/*      */ {
/*   48 */   private double[] array = null;
/*   49 */   private Stat arrayAsStat = null;
/*   50 */   private double[] sortedData = null;
/*      */   
/*   52 */   private double mean = NaN.0D;
/*   53 */   private double standardDeviation = NaN.0D;
/*   54 */   private double minimum = NaN.0D;
/*   55 */   private double maximum = NaN.0D;
/*   56 */   private double range = NaN.0D;
/*      */   
/*   58 */   private int numberOfDataPoints = 0;
/*      */   
/*   60 */   private int gaussianNumberOfParameters = 2;
/*   61 */   private double[] gaussianOrderMedians = null;
/*   62 */   private double[] gaussianParam = null;
/*   63 */   private double[] gaussianParamErrors = null;
/*   64 */   private double gaussianSumOfSquares = NaN.0D;
/*   65 */   private double[] gaussianLine = null;
/*   66 */   private double[] gaussianLineErrors = null;
/*   67 */   private double gaussianCorrCoeff = NaN.0D;
/*   68 */   private boolean gaussianDone = false;
/*      */   
/*   70 */   private int weibullNumberOfParameters = 3;
/*   71 */   private double[] weibullOrderMedians = null;
/*   72 */   private double[] weibullParam = null;
/*   73 */   private double[] weibullParamErrors = null;
/*   74 */   private double weibullSumOfSquares = NaN.0D;
/*   75 */   private double[] weibullLine = null;
/*   76 */   private double[] weibullLineErrors = null;
/*   77 */   private double weibullCorrCoeff = NaN.0D;
/*   78 */   private boolean weibullDone = false;
/*      */   
/*   80 */   private int exponentialNumberOfParameters = 2;
/*   81 */   private double[] exponentialOrderMedians = null;
/*   82 */   private double[] exponentialParam = null;
/*   83 */   private double[] exponentialParamErrors = null;
/*   84 */   private double exponentialSumOfSquares = NaN.0D;
/*   85 */   private double[] exponentialLine = null;
/*   86 */   private double[] exponentialLineErrors = null;
/*   87 */   private double exponentialCorrCoeff = NaN.0D;
/*   88 */   private boolean exponentialDone = false;
/*      */   
/*   90 */   private int rayleighNumberOfParameters = 2;
/*   91 */   private double[] rayleighOrderMedians = null;
/*   92 */   private double[] rayleighParam = null;
/*   93 */   private double[] rayleighParamErrors = null;
/*   94 */   private double rayleighSumOfSquares = NaN.0D;
/*   95 */   private double[] rayleighLine = null;
/*   96 */   private double[] rayleighLineErrors = null;
/*   97 */   private double rayleighCorrCoeff = NaN.0D;
/*   98 */   private boolean rayleighDone = false;
/*      */   
/*  100 */   private int paretoNumberOfParameters = 2;
/*  101 */   private double[] paretoOrderMedians = null;
/*  102 */   private double[] paretoParam = null;
/*  103 */   private double[] paretoParamErrors = null;
/*  104 */   private double paretoSumOfSquares = NaN.0D;
/*  105 */   private double[] paretoLine = null;
/*  106 */   private double[] paretoLineErrors = null;
/*  107 */   private double paretoCorrCoeff = NaN.0D;
/*  108 */   private boolean paretoDone = false;
/*      */   
/*  110 */   private boolean probPlotDone = false;
/*      */   
/*  112 */   private double delta = 100.0D;
/*      */   
/*  114 */   private boolean nFactorOptionI = false;
/*  115 */   private boolean nFactorReset = false;
/*      */   
/*      */ 
/*      */ 
/*      */   public ProbabilityPlot(double[] xx)
/*      */   {
/*  121 */     this.arrayAsStat = new Stat(xx);
/*  122 */     this.array = this.arrayAsStat.array();
/*  123 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Double[] xx) {
/*  127 */     this.arrayAsStat = new Stat(xx);
/*  128 */     this.array = this.arrayAsStat.array();
/*  129 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(float[] xx) {
/*  133 */     this.arrayAsStat = new Stat(xx);
/*  134 */     this.array = this.arrayAsStat.array();
/*  135 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Float[] xx) {
/*  139 */     this.arrayAsStat = new Stat(xx);
/*  140 */     this.array = this.arrayAsStat.array();
/*  141 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(long[] xx) {
/*  145 */     this.arrayAsStat = new Stat(xx);
/*  146 */     this.array = this.arrayAsStat.array();
/*  147 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Long[] xx) {
/*  151 */     this.arrayAsStat = new Stat(xx);
/*  152 */     this.array = this.arrayAsStat.array();
/*  153 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(int[] xx) {
/*  157 */     this.arrayAsStat = new Stat(xx);
/*  158 */     this.array = this.arrayAsStat.array();
/*  159 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Integer[] xx) {
/*  163 */     this.arrayAsStat = new Stat(xx);
/*  164 */     this.array = this.arrayAsStat.array();
/*  165 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(short[] xx) {
/*  169 */     this.arrayAsStat = new Stat(xx);
/*  170 */     this.array = this.arrayAsStat.array();
/*  171 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Short[] xx) {
/*  175 */     this.arrayAsStat = new Stat(xx);
/*  176 */     this.array = this.arrayAsStat.array();
/*  177 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(byte[] xx) {
/*  181 */     this.arrayAsStat = new Stat(xx);
/*  182 */     this.array = this.arrayAsStat.array();
/*  183 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Byte[] xx) {
/*  187 */     this.arrayAsStat = new Stat(xx);
/*  188 */     this.array = this.arrayAsStat.array();
/*  189 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(BigDecimal[] xx) {
/*  193 */     this.arrayAsStat = new Stat(xx);
/*  194 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(BigInteger[] xx) {
/*  198 */     this.arrayAsStat = new Stat(xx);
/*  199 */     this.array = this.arrayAsStat.array();
/*  200 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Object[] xx) {
/*  204 */     this.arrayAsStat = new Stat(xx);
/*  205 */     this.array = this.arrayAsStat.array();
/*  206 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Vector<Object> xx) {
/*  210 */     this.arrayAsStat = new Stat(xx);
/*  211 */     this.array = this.arrayAsStat.array();
/*  212 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(ArrayList<Object> xx) {
/*  216 */     this.arrayAsStat = new Stat(xx);
/*  217 */     this.array = this.arrayAsStat.array();
/*  218 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(ArrayMaths xx) {
/*  222 */     this.arrayAsStat = xx.toStat();
/*  223 */     this.array = this.arrayAsStat.array();
/*  224 */     initialize();
/*      */   }
/*      */   
/*      */   public ProbabilityPlot(Stat xx) {
/*  228 */     this.arrayAsStat = xx;
/*  229 */     this.array = this.arrayAsStat.array();
/*  230 */     initialize();
/*      */   }
/*      */   
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  236 */     this.numberOfDataPoints = this.array.length;
/*  237 */     Stat sorted = this.arrayAsStat.sort();
/*  238 */     this.sortedData = sorted.array();
/*  239 */     this.mean = this.arrayAsStat.mean();
/*  240 */     this.minimum = this.arrayAsStat.minimum();
/*  241 */     this.maximum = this.arrayAsStat.maximum();
/*  242 */     this.range = (this.maximum - this.minimum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gaussianProbabilityPlot()
/*      */   {
/*  250 */     if (this.nFactorOptionI) this.arrayAsStat.setDenominatorToN();
/*  251 */     this.standardDeviation = this.arrayAsStat.standardDeviation();
/*  252 */     this.gaussianNumberOfParameters = 2;
/*  253 */     if (this.numberOfDataPoints < this.gaussianNumberOfParameters + 1) { throw new IllegalArgumentException("There must be at least three data points - preferably considerably more");
/*      */     }
/*      */     
/*  256 */     Minimization min = new Minimization();
/*  257 */     double meanest = this.mean;
/*  258 */     if (this.mean == 0.0D) meanest = this.standardDeviation / 3.0D;
/*  259 */     double[] start = { meanest, this.standardDeviation };
/*  260 */     double[] step = { 0.3D * meanest, 0.3D * this.standardDeviation };
/*  261 */     double tolerance = 1.0E-4D;
/*      */     
/*      */ 
/*  264 */     min.addConstraint(1, -1, 0.0D);
/*      */     
/*      */ 
/*  267 */     GaussProbPlotFunc gppf = new GaussProbPlotFunc();
/*  268 */     gppf.setDataArray(this.sortedData);
/*      */     
/*      */ 
/*      */ 
/*  272 */     min.nelderMead(gppf, start, step, tolerance);
/*      */     
/*      */ 
/*  275 */     this.gaussianParam = min.getParamValues();
/*      */     
/*      */ 
/*  278 */     this.gaussianOrderMedians = Stat.gaussianOrderStatisticMedians(this.gaussianParam[0], this.gaussianParam[1], this.numberOfDataPoints);
/*      */     
/*      */ 
/*  281 */     Regression reg = new Regression(this.gaussianOrderMedians, this.sortedData);
/*  282 */     reg.linear();
/*      */     
/*      */ 
/*  285 */     this.gaussianLine = reg.getBestEstimates();
/*      */     
/*      */ 
/*  288 */     this.gaussianLineErrors = reg.getBestEstimatesErrors();
/*      */     
/*      */ 
/*  291 */     this.gaussianCorrCoeff = reg.getSampleR();
/*      */     
/*      */ 
/*  294 */     double[][] data = PlotGraph.data(2, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  297 */     data[0] = this.gaussianOrderMedians;
/*  298 */     data[1] = this.sortedData;
/*      */     
/*  300 */     data[2] = this.gaussianOrderMedians;
/*  301 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/*  302 */       data[3][i] = (this.gaussianLine[0] + this.gaussianLine[1] * this.gaussianOrderMedians[i]);
/*      */     }
/*      */     
/*      */ 
/*  306 */     probPlotStats(0, this.gaussianParam);
/*      */     
/*      */ 
/*  309 */     PlotGraph pg = new PlotGraph(data);
/*  310 */     int[] points = { 4, 0 };
/*  311 */     pg.setPoint(points);
/*  312 */     int[] lines = { 0, 3 };
/*  313 */     pg.setLine(lines);
/*  314 */     pg.setXaxisLegend("Gaussian Order Statistic Medians");
/*  315 */     pg.setYaxisLegend("Ordered Data Values");
/*  316 */     pg.setGraphTitle("Gaussian probability plot:   gradient = " + Fmath.truncate(this.gaussianLine[1], 4) + ", intercept = " + Fmath.truncate(this.gaussianLine[0], 4) + ",  R = " + Fmath.truncate(this.gaussianCorrCoeff, 4));
/*  317 */     pg.setGraphTitle2("  mu = " + Fmath.truncate(this.gaussianParam[0], 4) + ", sigma = " + Fmath.truncate(this.gaussianParam[1], 4));
/*      */     
/*      */ 
/*  320 */     pg.plot();
/*      */     
/*  322 */     this.gaussianDone = true;
/*      */   }
/*      */   
/*      */   public void normalProbabilityPlot() {
/*  326 */     gaussianProbabilityPlot();
/*      */   }
/*      */   
/*      */   public double gaussianMu()
/*      */   {
/*  331 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  332 */     return this.gaussianParam[0];
/*      */   }
/*      */   
/*      */   public double gaussianMuError()
/*      */   {
/*  337 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  338 */     return this.gaussianParamErrors[0];
/*      */   }
/*      */   
/*      */   public double gaussianSigma()
/*      */   {
/*  343 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  344 */     return this.gaussianParam[1];
/*      */   }
/*      */   
/*      */   public double gaussianSigmaError()
/*      */   {
/*  349 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  350 */     return this.gaussianParamErrors[1];
/*      */   }
/*      */   
/*      */   public double gaussianGradient()
/*      */   {
/*  355 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  356 */     return this.gaussianLine[1];
/*      */   }
/*      */   
/*      */   public double gaussianGradientError()
/*      */   {
/*  361 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  362 */     return this.gaussianLineErrors[1];
/*      */   }
/*      */   
/*      */   public double gaussianIntercept()
/*      */   {
/*  367 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  368 */     return this.gaussianLine[0];
/*      */   }
/*      */   
/*      */   public double gaussianInterceptError()
/*      */   {
/*  373 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  374 */     return this.gaussianLineErrors[0];
/*      */   }
/*      */   
/*      */   public double gaussianCorrelationCoefficient()
/*      */   {
/*  379 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  380 */     return this.gaussianCorrCoeff;
/*      */   }
/*      */   
/*      */   public double gaussianSumOfSquares()
/*      */   {
/*  385 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  386 */     return this.gaussianSumOfSquares;
/*      */   }
/*      */   
/*      */   public double[] gaussianOrderStatisticMedians()
/*      */   {
/*  391 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  392 */     return this.gaussianOrderMedians;
/*      */   }
/*      */   
/*      */   public double normalMu()
/*      */   {
/*  397 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  398 */     return this.gaussianParam[0];
/*      */   }
/*      */   
/*      */   public double normalMuError()
/*      */   {
/*  403 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  404 */     return this.gaussianParamErrors[0];
/*      */   }
/*      */   
/*      */   public double normalSigma()
/*      */   {
/*  409 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  410 */     return this.gaussianParam[1];
/*      */   }
/*      */   
/*      */   public double normalSigmaError()
/*      */   {
/*  415 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  416 */     return this.gaussianParamErrors[1];
/*      */   }
/*      */   
/*      */   public double normalGradient()
/*      */   {
/*  421 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  422 */     return this.gaussianLine[1];
/*      */   }
/*      */   
/*      */   public double normalGradientError()
/*      */   {
/*  427 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  428 */     return this.gaussianLineErrors[1];
/*      */   }
/*      */   
/*      */   public double normalIntercept()
/*      */   {
/*  433 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  434 */     return this.gaussianLine[0];
/*      */   }
/*      */   
/*      */   public double normalInterceptError()
/*      */   {
/*  439 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  440 */     return this.gaussianLineErrors[0];
/*      */   }
/*      */   
/*      */   public double normalCorrelationCoefficient()
/*      */   {
/*  445 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  446 */     return this.gaussianCorrCoeff;
/*      */   }
/*      */   
/*      */   public double normalSumOfSquares()
/*      */   {
/*  451 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  452 */     return this.gaussianSumOfSquares;
/*      */   }
/*      */   
/*      */   public double[] normalOrderStatisticMedians()
/*      */   {
/*  457 */     if (!this.gaussianDone) throw new IllegalArgumentException("Gaussian Probability Plot method has not been called");
/*  458 */     return this.gaussianOrderMedians;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void weibullProbabilityPlot()
/*      */   {
/*  468 */     if (this.nFactorOptionI) this.arrayAsStat.setDenominatorToN();
/*  469 */     this.standardDeviation = this.arrayAsStat.standardDeviation();
/*  470 */     this.weibullNumberOfParameters = 3;
/*  471 */     if (this.numberOfDataPoints < 4) { throw new IllegalArgumentException("There must be at least four data points - preferably considerably more");
/*      */     }
/*      */     
/*  474 */     Minimization min = new Minimization();
/*  475 */     double muest = this.minimum;
/*  476 */     if (muest == 0.0D) muest = this.standardDeviation / 3.0D;
/*  477 */     double sigmaest = this.standardDeviation;
/*  478 */     double gammaest = 4.0D;
/*  479 */     double[] start = { muest, sigmaest, gammaest };
/*  480 */     double[] step = { 0.3D * muest, 0.3D * sigmaest, 0.3D * gammaest };
/*  481 */     double tolerance = 1.0E-4D;
/*      */     
/*      */ 
/*  484 */     min.addConstraint(1, -1, 0.0D);
/*  485 */     min.addConstraint(2, -1, 0.0D);
/*      */     
/*      */ 
/*  488 */     WeibullProbPlotFunc wppf = new WeibullProbPlotFunc();
/*  489 */     wppf.setDataArray(this.sortedData);
/*      */     
/*      */ 
/*      */ 
/*  493 */     min.nelderMead(wppf, start, step, tolerance);
/*      */     
/*      */ 
/*  496 */     this.weibullParam = min.getParamValues();
/*      */     
/*      */ 
/*  499 */     this.weibullOrderMedians = Stat.weibullOrderStatisticMedians(this.weibullParam[0], this.weibullParam[1], this.weibullParam[2], this.numberOfDataPoints);
/*      */     
/*      */ 
/*  502 */     Regression reg = new Regression(this.weibullOrderMedians, this.sortedData);
/*  503 */     reg.linear();
/*      */     
/*      */ 
/*  506 */     this.weibullLine = reg.getBestEstimates();
/*      */     
/*      */ 
/*  509 */     this.weibullLineErrors = reg.getBestEstimatesErrors();
/*      */     
/*      */ 
/*  512 */     this.weibullCorrCoeff = reg.getSampleR();
/*      */     
/*      */ 
/*  515 */     double[][] data = PlotGraph.data(2, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  518 */     data[0] = this.weibullOrderMedians;
/*  519 */     data[1] = this.sortedData;
/*      */     
/*  521 */     data[2] = this.weibullOrderMedians;
/*  522 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/*  523 */       data[3][i] = (this.weibullLine[0] + this.weibullLine[1] * this.weibullOrderMedians[i]);
/*      */     }
/*      */     
/*      */ 
/*  527 */     probPlotStats(1, this.weibullParam);
/*      */     
/*      */ 
/*  530 */     PlotGraph pg = new PlotGraph(data);
/*  531 */     int[] points = { 4, 0 };
/*  532 */     pg.setPoint(points);
/*  533 */     int[] lines = { 0, 3 };
/*  534 */     pg.setLine(lines);
/*  535 */     pg.setXaxisLegend("Weibull Order Statistic Medians");
/*  536 */     pg.setYaxisLegend("Ordered Data Values");
/*  537 */     pg.setGraphTitle("Weibull probability plot:   gradient = " + Fmath.truncate(this.weibullLine[1], 4) + ", intercept = " + Fmath.truncate(this.weibullLine[0], 4) + ",  R = " + Fmath.truncate(this.weibullCorrCoeff, 4));
/*  538 */     pg.setGraphTitle2("  mu = " + Fmath.truncate(this.weibullParam[0], 4) + ", sigma = " + Fmath.truncate(this.weibullParam[1], 4) + ", gamma = " + Fmath.truncate(this.weibullParam[2], 4));
/*      */     
/*      */ 
/*  541 */     pg.plot();
/*      */     
/*  543 */     this.weibullDone = true;
/*  544 */     this.probPlotDone = true;
/*      */   }
/*      */   
/*      */   public double weibullMu()
/*      */   {
/*  549 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  550 */     return this.weibullParam[0];
/*      */   }
/*      */   
/*      */   public double weibullMuError()
/*      */   {
/*  555 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  556 */     return this.weibullParamErrors[0];
/*      */   }
/*      */   
/*      */   public double weibullSigma()
/*      */   {
/*  561 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  562 */     return this.weibullParam[1];
/*      */   }
/*      */   
/*      */   public double weibullSigmaError()
/*      */   {
/*  567 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  568 */     return this.weibullParamErrors[1];
/*      */   }
/*      */   
/*      */   public double weibullGamma()
/*      */   {
/*  573 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  574 */     return this.weibullParam[2];
/*      */   }
/*      */   
/*      */   public double weibullGammaError()
/*      */   {
/*  579 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  580 */     return this.weibullParamErrors[2];
/*      */   }
/*      */   
/*      */   public double[] weibullOrderStatisticMedians()
/*      */   {
/*  585 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  586 */     return this.weibullOrderMedians;
/*      */   }
/*      */   
/*      */   public double weibullGradient()
/*      */   {
/*  591 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  592 */     return this.weibullLine[1];
/*      */   }
/*      */   
/*      */   public double weibullGradientError()
/*      */   {
/*  597 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  598 */     return this.weibullLineErrors[1];
/*      */   }
/*      */   
/*      */   public double weibullIntercept()
/*      */   {
/*  603 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  604 */     return this.weibullLine[0];
/*      */   }
/*      */   
/*      */   public double weibullInterceptError()
/*      */   {
/*  609 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  610 */     return this.weibullLineErrors[0];
/*      */   }
/*      */   
/*      */   public double weibullCorrelationCoefficient()
/*      */   {
/*  615 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  616 */     return this.weibullCorrCoeff;
/*      */   }
/*      */   
/*      */   public double weibullSumOfSquares()
/*      */   {
/*  621 */     if (!this.weibullDone) throw new IllegalArgumentException("Weibull Probability Plot method has not been called");
/*  622 */     return this.weibullSumOfSquares;
/*      */   }
/*      */   
/*      */ 
/*      */   public void exponentialProbabilityPlot()
/*      */   {
/*  628 */     if (this.nFactorOptionI) this.arrayAsStat.setDenominatorToN();
/*  629 */     this.standardDeviation = this.arrayAsStat.standardDeviation();
/*  630 */     this.exponentialNumberOfParameters = 2;
/*  631 */     if (this.numberOfDataPoints < 3) { throw new IllegalArgumentException("There must be at least three data points - preferably considerably more");
/*      */     }
/*      */     
/*  634 */     Minimization min = new Minimization();
/*  635 */     double muest = this.minimum;
/*  636 */     if (muest == 0.0D) muest = this.standardDeviation / 3.0D;
/*  637 */     double sigmaest = this.standardDeviation;
/*  638 */     double[] start = { muest, sigmaest };
/*  639 */     double[] step = { 0.3D * muest, 0.3D * sigmaest };
/*  640 */     double tolerance = 1.0E-4D;
/*      */     
/*      */ 
/*  643 */     min.addConstraint(1, -1, 0.0D);
/*      */     
/*      */ 
/*  646 */     ExponentialProbPlotFunc eppf = new ExponentialProbPlotFunc();
/*  647 */     eppf.setDataArray(this.sortedData);
/*      */     
/*      */ 
/*      */ 
/*  651 */     min.nelderMead(eppf, start, step, tolerance);
/*      */     
/*      */ 
/*  654 */     this.exponentialParam = min.getParamValues();
/*      */     
/*      */ 
/*  657 */     this.exponentialOrderMedians = Stat.weibullOrderStatisticMedians(this.exponentialParam[0], this.exponentialParam[1], 1.0D, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  660 */     Regression reg = new Regression(this.exponentialOrderMedians, this.sortedData);
/*  661 */     reg.linear();
/*      */     
/*      */ 
/*  664 */     this.exponentialLine = reg.getBestEstimates();
/*      */     
/*      */ 
/*  667 */     this.exponentialLineErrors = reg.getBestEstimatesErrors();
/*      */     
/*      */ 
/*  670 */     this.exponentialCorrCoeff = reg.getSampleR();
/*      */     
/*      */ 
/*  673 */     double[][] data = PlotGraph.data(2, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  676 */     data[0] = this.exponentialOrderMedians;
/*  677 */     data[1] = this.sortedData;
/*      */     
/*  679 */     data[2] = this.exponentialOrderMedians;
/*  680 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/*  681 */       data[3][i] = (this.exponentialLine[0] + this.exponentialLine[1] * this.exponentialOrderMedians[i]);
/*      */     }
/*      */     
/*      */ 
/*  685 */     probPlotStats(2, this.exponentialParam);
/*      */     
/*      */ 
/*  688 */     PlotGraph pg = new PlotGraph(data);
/*  689 */     int[] points = { 4, 0 };
/*  690 */     pg.setPoint(points);
/*  691 */     int[] lines = { 0, 3 };
/*  692 */     pg.setLine(lines);
/*  693 */     pg.setXaxisLegend("Exponential Order Statistic Medians");
/*  694 */     pg.setYaxisLegend("Ordered Data Values");
/*  695 */     pg.setGraphTitle("Exponential probability plot:   gradient = " + Fmath.truncate(this.exponentialLine[1], 4) + ", intercept = " + Fmath.truncate(this.exponentialLine[0], 4) + ",  R = " + Fmath.truncate(this.exponentialCorrCoeff, 4));
/*  696 */     pg.setGraphTitle2("  mu = " + Fmath.truncate(this.exponentialParam[0], 4) + ", sigma = " + Fmath.truncate(this.exponentialParam[1], 4));
/*      */     
/*      */ 
/*  699 */     pg.plot();
/*      */     
/*  701 */     this.exponentialDone = true;
/*  702 */     this.probPlotDone = true;
/*      */   }
/*      */   
/*      */   public double exponentialMu()
/*      */   {
/*  707 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  708 */     return this.exponentialParam[0];
/*      */   }
/*      */   
/*      */   public double exponentialMuError()
/*      */   {
/*  713 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  714 */     return this.exponentialParamErrors[0];
/*      */   }
/*      */   
/*      */   public double exponentialSigma()
/*      */   {
/*  719 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  720 */     return this.exponentialParam[1];
/*      */   }
/*      */   
/*      */   public double exponentialSigmaError()
/*      */   {
/*  725 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  726 */     return this.exponentialParamErrors[1];
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] exponentialOrderStatisticMedians()
/*      */   {
/*  732 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  733 */     return this.exponentialOrderMedians;
/*      */   }
/*      */   
/*      */   public double exponentialGradient()
/*      */   {
/*  738 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  739 */     return this.exponentialLine[1];
/*      */   }
/*      */   
/*      */   public double exponentialGradientError()
/*      */   {
/*  744 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  745 */     return this.exponentialLineErrors[1];
/*      */   }
/*      */   
/*      */   public double exponentialIntercept()
/*      */   {
/*  750 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  751 */     return this.exponentialLine[0];
/*      */   }
/*      */   
/*      */   public double exponentialInterceptError()
/*      */   {
/*  756 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  757 */     return this.exponentialLineErrors[0];
/*      */   }
/*      */   
/*      */   public double exponentialCorrelationCoefficient()
/*      */   {
/*  762 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  763 */     return this.exponentialCorrCoeff;
/*      */   }
/*      */   
/*      */   public double exponentialSumOfSquares()
/*      */   {
/*  768 */     if (!this.exponentialDone) throw new IllegalArgumentException("Exponential Probability Plot method has not been called");
/*  769 */     return this.exponentialSumOfSquares;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void rayleighProbabilityPlot()
/*      */   {
/*  776 */     if (this.nFactorOptionI) this.arrayAsStat.setDenominatorToN();
/*  777 */     this.standardDeviation = this.arrayAsStat.standardDeviation();
/*  778 */     this.rayleighNumberOfParameters = 1;
/*  779 */     if (this.numberOfDataPoints < 2) { throw new IllegalArgumentException("There must be at least two data points - preferably considerably more");
/*      */     }
/*      */     
/*  782 */     Minimization min = new Minimization();
/*  783 */     double sigmaest = this.standardDeviation;
/*  784 */     double[] start = { sigmaest };
/*  785 */     double[] step = { 0.3D * sigmaest };
/*  786 */     double tolerance = 1.0E-4D;
/*      */     
/*      */ 
/*  789 */     min.addConstraint(0, -1, 0.0D);
/*      */     
/*      */ 
/*  792 */     RayleighProbPlotFunc rppf = new RayleighProbPlotFunc();
/*  793 */     rppf.setDataArray(this.sortedData);
/*      */     
/*      */ 
/*      */ 
/*  797 */     min.nelderMead(rppf, start, step, tolerance);
/*      */     
/*      */ 
/*  800 */     this.rayleighParam = min.getParamValues();
/*      */     
/*      */ 
/*  803 */     this.rayleighOrderMedians = Stat.weibullOrderStatisticMedians(0.0D, this.rayleighParam[0] * Math.sqrt(2.0D), 2.0D, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  806 */     Regression reg = new Regression(this.rayleighOrderMedians, this.sortedData);
/*  807 */     reg.linear();
/*      */     
/*      */ 
/*  810 */     this.rayleighLine = reg.getBestEstimates();
/*      */     
/*      */ 
/*  813 */     this.rayleighLineErrors = reg.getBestEstimatesErrors();
/*      */     
/*      */ 
/*  816 */     this.rayleighCorrCoeff = reg.getSampleR();
/*      */     
/*      */ 
/*  819 */     double[][] data = PlotGraph.data(2, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  822 */     data[0] = this.rayleighOrderMedians;
/*  823 */     data[1] = this.sortedData;
/*      */     
/*  825 */     data[2] = this.rayleighOrderMedians;
/*  826 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/*  827 */       data[3][i] = (this.rayleighLine[0] + this.rayleighLine[1] * this.rayleighOrderMedians[i]);
/*      */     }
/*      */     
/*      */ 
/*  831 */     probPlotStats(3, this.rayleighParam);
/*      */     
/*      */ 
/*  834 */     PlotGraph pg = new PlotGraph(data);
/*  835 */     int[] points = { 4, 0 };
/*  836 */     pg.setPoint(points);
/*  837 */     int[] lines = { 0, 3 };
/*  838 */     pg.setLine(lines);
/*  839 */     pg.setXaxisLegend("Rayleigh Order Statistic Medians");
/*  840 */     pg.setYaxisLegend("Ordered Data Values");
/*  841 */     pg.setGraphTitle("Rayleigh probability plot:   gradient = " + Fmath.truncate(this.rayleighLine[1], 4) + ", intercept = " + Fmath.truncate(this.rayleighLine[0], 4) + ",  R = " + Fmath.truncate(this.rayleighCorrCoeff, 4));
/*  842 */     pg.setGraphTitle2("  beta = " + Fmath.truncate(this.rayleighParam[0], 4));
/*      */     
/*      */ 
/*  845 */     pg.plot();
/*      */     
/*  847 */     this.rayleighDone = true;
/*  848 */     this.probPlotDone = true;
/*      */   }
/*      */   
/*      */   public double rayleighBeta()
/*      */   {
/*  853 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  854 */     return this.rayleighParam[0];
/*      */   }
/*      */   
/*      */   public double rayleighBetaError()
/*      */   {
/*  859 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  860 */     return this.rayleighParamErrors[0];
/*      */   }
/*      */   
/*      */   public double[] rayleighOrderStatisticMedians()
/*      */   {
/*  865 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  866 */     return this.rayleighOrderMedians;
/*      */   }
/*      */   
/*      */   public double rayleighGradient()
/*      */   {
/*  871 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  872 */     return this.rayleighLine[1];
/*      */   }
/*      */   
/*      */   public double rayleighGradientError()
/*      */   {
/*  877 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  878 */     return this.rayleighLineErrors[1];
/*      */   }
/*      */   
/*      */   public double rayleighIntercept()
/*      */   {
/*  883 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  884 */     return this.rayleighLine[0];
/*      */   }
/*      */   
/*      */   public double rayleighInterceptError()
/*      */   {
/*  889 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  890 */     return this.rayleighLineErrors[0];
/*      */   }
/*      */   
/*      */   public double rayleighCorrelationCoefficient()
/*      */   {
/*  895 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  896 */     return this.rayleighCorrCoeff;
/*      */   }
/*      */   
/*      */   public double rayleighSumOfSquares()
/*      */   {
/*  901 */     if (!this.rayleighDone) throw new IllegalArgumentException("Rayleigh Probability Plot method has not been called");
/*  902 */     return this.rayleighSumOfSquares;
/*      */   }
/*      */   
/*      */ 
/*      */   public void paretoProbabilityPlot()
/*      */   {
/*  908 */     if (this.nFactorOptionI) this.arrayAsStat.setDenominatorToN();
/*  909 */     this.standardDeviation = this.arrayAsStat.standardDeviation();
/*  910 */     this.paretoNumberOfParameters = 2;
/*  911 */     if (this.numberOfDataPoints < 3) { throw new IllegalArgumentException("There must be at least two data points - preferably considerably more");
/*      */     }
/*      */     
/*  914 */     Minimization min = new Minimization();
/*  915 */     double betaest = this.minimum;
/*  916 */     double alphaest = this.mean / (this.mean - betaest);
/*  917 */     double[] start = { alphaest, betaest };
/*  918 */     double[] step = { 0.3D * alphaest, 0.3D * betaest };
/*  919 */     double tolerance = 1.0E-4D;
/*      */     
/*      */ 
/*  922 */     ParetoProbPlotFunc pppf = new ParetoProbPlotFunc();
/*  923 */     pppf.setDataArray(this.sortedData);
/*      */     
/*      */ 
/*      */ 
/*  927 */     min.nelderMead(pppf, start, step, tolerance);
/*      */     
/*      */ 
/*  930 */     this.paretoParam = min.getParamValues();
/*      */     
/*      */ 
/*  933 */     this.paretoOrderMedians = Stat.paretoOrderStatisticMedians(this.paretoParam[0], this.paretoParam[1], this.numberOfDataPoints);
/*      */     
/*      */ 
/*  936 */     Regression reg = new Regression(this.paretoOrderMedians, this.sortedData);
/*  937 */     reg.linear();
/*      */     
/*      */ 
/*  940 */     this.paretoLine = reg.getBestEstimates();
/*      */     
/*      */ 
/*  943 */     this.paretoLineErrors = reg.getBestEstimatesErrors();
/*      */     
/*      */ 
/*  946 */     this.paretoCorrCoeff = reg.getSampleR();
/*      */     
/*      */ 
/*  949 */     double[][] data = PlotGraph.data(2, this.numberOfDataPoints);
/*      */     
/*      */ 
/*  952 */     data[0] = this.paretoOrderMedians;
/*  953 */     data[1] = this.sortedData;
/*      */     
/*  955 */     data[2] = this.paretoOrderMedians;
/*  956 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/*  957 */       data[3][i] = (this.paretoLine[0] + this.paretoLine[1] * this.paretoOrderMedians[i]);
/*      */     }
/*      */     
/*      */ 
/*  961 */     probPlotStats(4, this.paretoParam);
/*      */     
/*      */ 
/*  964 */     PlotGraph pg = new PlotGraph(data);
/*  965 */     int[] points = { 4, 0 };
/*  966 */     pg.setPoint(points);
/*  967 */     int[] lines = { 0, 3 };
/*  968 */     pg.setLine(lines);
/*  969 */     pg.setXaxisLegend("Pareto Order Statistic Medians");
/*  970 */     pg.setYaxisLegend("Ordered Data Values");
/*  971 */     pg.setGraphTitle("Pareto probability plot:   gradient = " + Fmath.truncate(this.paretoLine[1], 4) + ", intercept = " + Fmath.truncate(this.paretoLine[0], 4) + ",  R = " + Fmath.truncate(this.paretoCorrCoeff, 4));
/*  972 */     pg.setGraphTitle2("  alpha = " + Fmath.truncate(this.paretoParam[0], 4) + ", beta = " + Fmath.truncate(this.paretoParam[1], 4));
/*      */     
/*      */ 
/*  975 */     pg.plot();
/*      */     
/*  977 */     this.paretoDone = true;
/*  978 */     this.probPlotDone = true;
/*      */   }
/*      */   
/*      */   public double paretoAlpha()
/*      */   {
/*  983 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/*  984 */     return this.paretoParam[0];
/*      */   }
/*      */   
/*      */   public double paretoAlphaError()
/*      */   {
/*  989 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/*  990 */     return this.paretoParamErrors[0];
/*      */   }
/*      */   
/*      */   public double paretoBeta()
/*      */   {
/*  995 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/*  996 */     return this.paretoParam[1];
/*      */   }
/*      */   
/*      */   public double paretoBetaError()
/*      */   {
/* 1001 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1002 */     return this.paretoParamErrors[1];
/*      */   }
/*      */   
/*      */   public double[] paretoOrderStatisticMedians()
/*      */   {
/* 1007 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1008 */     return this.paretoOrderMedians;
/*      */   }
/*      */   
/*      */   public double paretoGradient()
/*      */   {
/* 1013 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1014 */     return this.paretoLine[1];
/*      */   }
/*      */   
/*      */   public double paretoGradientError()
/*      */   {
/* 1019 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1020 */     return this.paretoLineErrors[1];
/*      */   }
/*      */   
/*      */   public double paretoIntercept()
/*      */   {
/* 1025 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1026 */     return this.paretoLine[0];
/*      */   }
/*      */   
/*      */   public double paretoInterceptError()
/*      */   {
/* 1031 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1032 */     return this.paretoLineErrors[0];
/*      */   }
/*      */   
/*      */   public double paretoCorrelationCoefficient()
/*      */   {
/* 1037 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1038 */     return this.paretoCorrCoeff;
/*      */   }
/*      */   
/*      */   public double paretoSumOfSquares()
/*      */   {
/* 1043 */     if (!this.paretoDone) throw new IllegalArgumentException("Pareto Probability Plot method has not been called");
/* 1044 */     return this.paretoSumOfSquares;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] orderedData()
/*      */   {
/* 1054 */     return this.sortedData;
/*      */   }
/*      */   
/*      */   public int numberOfDataPoints()
/*      */   {
/* 1059 */     return this.numberOfDataPoints;
/*      */   }
/*      */   
/*      */   public double mean()
/*      */   {
/* 1064 */     return this.mean;
/*      */   }
/*      */   
/*      */   public double standardDeviation()
/*      */   {
/* 1069 */     if (!this.probPlotDone) throw new IllegalArgumentException("no probability plot method has been called");
/* 1070 */     return this.standardDeviation;
/*      */   }
/*      */   
/*      */   public double minimum()
/*      */   {
/* 1075 */     return this.minimum;
/*      */   }
/*      */   
/*      */   public double maximum()
/*      */   {
/* 1080 */     return this.maximum;
/*      */   }
/*      */   
/*      */   public double delta()
/*      */   {
/* 1085 */     return this.delta;
/*      */   }
/*      */   
/*      */   public void resetDelta(double delta)
/*      */   {
/* 1090 */     this.delta = delta;
/*      */   }
/*      */   
/*      */   public void setDenominatorToN()
/*      */   {
/* 1095 */     this.nFactorOptionI = true;
/* 1096 */     this.nFactorReset = true;
/*      */   }
/*      */   
/*      */   public void setDenominatorToNminusOne()
/*      */   {
/* 1101 */     this.nFactorOptionI = false;
/* 1102 */     this.nFactorReset = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void probPlotStats(int flag, double[] minParam)
/*      */   {
/* 1111 */     double range = this.maximum - this.minimum;
/* 1112 */     double[][] secondDeriv = (double[][])null;
/* 1113 */     double[] paramTemp = null;
/* 1114 */     int nMatrix = 0;
/* 1115 */     GaussProbPlotFunc gppf = null;
/* 1116 */     WeibullProbPlotFunc wppf = null;
/* 1117 */     ExponentialProbPlotFunc eppf = null;
/* 1118 */     RayleighProbPlotFunc rppf = null;
/* 1119 */     ParetoProbPlotFunc pppf = null;
/* 1120 */     Object func = null;
/* 1121 */     switch (flag) {
/*      */     case 0: 
/* 1123 */       this.gaussianParamErrors = new double[this.gaussianNumberOfParameters];
/* 1124 */       gppf = new GaussProbPlotFunc();
/* 1125 */       secondDeriv = new double[this.gaussianNumberOfParameters][this.gaussianNumberOfParameters];
/* 1126 */       paramTemp = new double[this.gaussianNumberOfParameters];
/* 1127 */       gppf.setDataArray(this.array);
/* 1128 */       this.gaussianSumOfSquares = gppf.function(minParam);
/* 1129 */       func = gppf;
/* 1130 */       nMatrix = this.gaussianNumberOfParameters;
/* 1131 */       break;
/*      */     case 1: 
/* 1133 */       this.weibullParamErrors = new double[this.weibullNumberOfParameters];
/* 1134 */       wppf = new WeibullProbPlotFunc();
/* 1135 */       secondDeriv = new double[this.weibullNumberOfParameters][this.weibullNumberOfParameters];
/* 1136 */       paramTemp = new double[this.weibullNumberOfParameters];
/* 1137 */       wppf.setDataArray(this.array);
/* 1138 */       this.weibullSumOfSquares = wppf.function(minParam);
/* 1139 */       func = wppf;
/* 1140 */       nMatrix = this.weibullNumberOfParameters;
/* 1141 */       break;
/*      */     case 2: 
/* 1143 */       this.exponentialParamErrors = new double[this.exponentialNumberOfParameters];
/* 1144 */       eppf = new ExponentialProbPlotFunc();
/* 1145 */       secondDeriv = new double[this.exponentialNumberOfParameters][this.exponentialNumberOfParameters];
/* 1146 */       paramTemp = new double[this.exponentialNumberOfParameters];
/* 1147 */       eppf.setDataArray(this.array);
/* 1148 */       this.exponentialSumOfSquares = eppf.function(minParam);
/* 1149 */       func = eppf;
/* 1150 */       nMatrix = this.exponentialNumberOfParameters;
/* 1151 */       break;
/*      */     case 3: 
/* 1153 */       this.rayleighParamErrors = new double[this.rayleighNumberOfParameters];
/* 1154 */       rppf = new RayleighProbPlotFunc();
/* 1155 */       secondDeriv = new double[this.rayleighNumberOfParameters][this.rayleighNumberOfParameters];
/* 1156 */       paramTemp = new double[this.rayleighNumberOfParameters];
/* 1157 */       rppf.setDataArray(this.array);
/* 1158 */       this.rayleighSumOfSquares = rppf.function(minParam);
/* 1159 */       func = rppf;
/* 1160 */       nMatrix = this.rayleighNumberOfParameters;
/* 1161 */       break;
/*      */     case 4: 
/* 1163 */       this.paretoParamErrors = new double[this.paretoNumberOfParameters];
/* 1164 */       pppf = new ParetoProbPlotFunc();
/* 1165 */       secondDeriv = new double[this.paretoNumberOfParameters][this.paretoNumberOfParameters];
/* 1166 */       paramTemp = new double[this.paretoNumberOfParameters];
/* 1167 */       pppf.setDataArray(this.array);
/* 1168 */       this.paretoSumOfSquares = pppf.function(minParam);
/* 1169 */       func = pppf;
/* 1170 */       nMatrix = this.paretoNumberOfParameters;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1175 */     double pari = 0.0D;
/* 1176 */     double parj = 0.0D;
/* 1177 */     double[] parh = new double[2];
/* 1178 */     for (int i = 0; i < nMatrix; i++) {
/* 1179 */       for (int j = 0; j < nMatrix; j++) {
/* 1180 */         if (i == j) {
/* 1181 */           paramTemp = (double[])minParam.clone();
/* 1182 */           minParam[i] *= (1.0D + this.delta);
/* 1183 */           pari = minParam[i];
/* 1184 */           if (minParam[i] == 0.0D) {
/* 1185 */             minParam[i] = (this.range * this.delta / 70.0D);
/* 1186 */             pari = this.range / 35.0D;
/*      */           }
/* 1188 */           double term1 = statFunction(flag, func, paramTemp);
/*      */           
/* 1190 */           paramTemp = (double[])minParam.clone();
/* 1191 */           paramTemp[i] = minParam[i];
/* 1192 */           pari = minParam[i];
/* 1193 */           double term2 = statFunction(flag, func, paramTemp);
/*      */           
/* 1195 */           double term3 = term2;
/*      */           
/* 1197 */           paramTemp = (double[])minParam.clone();
/* 1198 */           minParam[i] *= (1.0D - this.delta);
/* 1199 */           pari = minParam[i];
/* 1200 */           if (minParam[i] == 0.0D) {
/* 1201 */             minParam[i] = (-this.range * this.delta / 70.0D);
/* 1202 */             pari = this.range / 35.0D;
/*      */           }
/* 1204 */           double term4 = statFunction(flag, func, paramTemp);
/*      */           
/* 1206 */           secondDeriv[i][j] = ((term1 - term2 - term3 + term4) / (pari * pari * this.delta * this.delta));
/*      */         }
/*      */         else {
/* 1209 */           paramTemp = (double[])minParam.clone();
/* 1210 */           minParam[i] *= (1.0D + this.delta / 2.0D);
/* 1211 */           pari = minParam[i];
/* 1212 */           if (minParam[i] == 0.0D) {
/* 1213 */             minParam[i] = (this.range * this.delta / 70.0D);
/* 1214 */             pari = this.range / 35.0D;
/*      */           }
/*      */           
/* 1217 */           minParam[j] *= (1.0D + this.delta / 2.0D);
/* 1218 */           parj = minParam[j];
/* 1219 */           if (minParam[j] == 0.0D) {
/* 1220 */             minParam[j] = (this.range * this.delta / 70.0D);
/* 1221 */             parj = this.range / 35.0D;
/*      */           }
/* 1223 */           double term1 = statFunction(flag, func, paramTemp);
/*      */           
/* 1225 */           paramTemp = (double[])minParam.clone();
/* 1226 */           minParam[i] *= (1.0D - this.delta / 2.0D);
/* 1227 */           pari = minParam[i];
/* 1228 */           if (minParam[i] == 0.0D) {
/* 1229 */             minParam[i] = (-this.range * this.delta / 70.0D);
/* 1230 */             pari = this.range / 35.0D;
/*      */           }
/*      */           
/* 1233 */           minParam[j] *= (1.0D + this.delta / 2.0D);
/* 1234 */           parj = minParam[j];
/* 1235 */           if (minParam[j] == 0.0D) {
/* 1236 */             minParam[j] = (this.range * this.delta / 70.0D);
/* 1237 */             parj = this.range / 35.0D;
/*      */           }
/* 1239 */           double term2 = statFunction(flag, func, paramTemp);
/*      */           
/* 1241 */           paramTemp = (double[])minParam.clone();
/* 1242 */           minParam[i] *= (1.0D + this.delta / 2.0D);
/* 1243 */           pari = minParam[i];
/* 1244 */           if (minParam[i] == 0.0D) {
/* 1245 */             minParam[i] = (this.range * this.delta / 70.0D);
/* 1246 */             pari = this.range / 35.0D;
/*      */           }
/*      */           
/* 1249 */           minParam[j] *= (1.0D - this.delta / 2.0D);
/* 1250 */           parj = minParam[j];
/* 1251 */           if (minParam[j] == 0.0D) {
/* 1252 */             minParam[j] = (-this.range * this.delta / 70.0D);
/* 1253 */             parj = this.range / 35.0D;
/*      */           }
/* 1255 */           double term3 = statFunction(flag, func, paramTemp);
/*      */           
/* 1257 */           paramTemp = (double[])minParam.clone();
/* 1258 */           minParam[i] *= (1.0D - this.delta / 2.0D);
/* 1259 */           pari = minParam[i];
/* 1260 */           if (minParam[i] == 0.0D) {
/* 1261 */             minParam[i] = (-this.range * this.delta / 70.0D);
/* 1262 */             pari = this.range / 35.0D;
/*      */           }
/*      */           
/* 1265 */           minParam[j] *= (1.0D - this.delta / 2.0D);
/* 1266 */           parj = minParam[j];
/* 1267 */           if (minParam[j] == 0.0D) {
/* 1268 */             minParam[j] = (-this.range * this.delta / 70.0D);
/* 1269 */             parj = this.range / 35.0D;
/*      */           }
/* 1271 */           double term4 = statFunction(flag, func, paramTemp);
/*      */           
/* 1273 */           secondDeriv[i][j] = ((term1 - term2 - term3 + term4) / (pari * parj * this.delta * this.delta));
/*      */         }
/*      */       }
/*      */     }
/* 1277 */     Matrix mat = new Matrix(secondDeriv);
/* 1278 */     mat = mat.inverse();
/* 1279 */     switch (flag) {
/* 1280 */     case 0:  mat = mat.times(this.gaussianSumOfSquares / (this.numberOfDataPoints - this.gaussianNumberOfParameters));
/* 1281 */       for (int i = 0; i < this.gaussianNumberOfParameters; i++) {
/* 1282 */         this.gaussianParamErrors[i] = Math.sqrt(mat.getElement(i, i));
/* 1283 */         if (Double.isNaN(this.gaussianParamErrors[i])) this.gaussianParamErrors[i] = Math.abs(this.gaussianSumOfSquares / (secondDeriv[i][i] * (this.numberOfDataPoints - this.gaussianNumberOfParameters)));
/*      */       }
/* 1285 */       break;
/* 1286 */     case 1:  mat = mat.times(this.weibullSumOfSquares / (this.numberOfDataPoints - this.weibullNumberOfParameters));
/* 1287 */       for (int i = 0; i < this.weibullNumberOfParameters; i++) {
/* 1288 */         this.weibullParamErrors[i] = Math.sqrt(mat.getElement(i, i));
/* 1289 */         if (Double.isNaN(this.weibullParamErrors[i])) this.weibullParamErrors[i] = Math.abs(this.weibullSumOfSquares / (secondDeriv[i][i] * (this.numberOfDataPoints - this.weibullNumberOfParameters)));
/*      */       }
/* 1291 */       break;
/* 1292 */     case 2:  mat = mat.times(this.exponentialSumOfSquares / (this.numberOfDataPoints - this.exponentialNumberOfParameters));
/* 1293 */       for (int i = 0; i < this.exponentialNumberOfParameters; i++) {
/* 1294 */         this.exponentialParamErrors[i] = Math.sqrt(mat.getElement(i, i));
/* 1295 */         if (Double.isNaN(this.exponentialParamErrors[i])) this.exponentialParamErrors[i] = Math.abs(this.exponentialSumOfSquares / (secondDeriv[i][i] * (this.numberOfDataPoints - this.exponentialNumberOfParameters)));
/*      */       }
/* 1297 */       break;
/* 1298 */     case 3:  mat = mat.times(this.rayleighSumOfSquares / (this.numberOfDataPoints - this.rayleighNumberOfParameters));
/* 1299 */       for (int i = 0; i < this.rayleighNumberOfParameters; i++) {
/* 1300 */         this.rayleighParamErrors[i] = Math.sqrt(mat.getElement(i, i));
/* 1301 */         if (Double.isNaN(this.rayleighParamErrors[i])) this.rayleighParamErrors[i] = Math.abs(this.rayleighSumOfSquares / (secondDeriv[i][i] * (this.numberOfDataPoints - this.rayleighNumberOfParameters)));
/*      */       }
/* 1303 */       break;
/* 1304 */     case 4:  mat = mat.times(this.paretoSumOfSquares / (this.numberOfDataPoints - this.paretoNumberOfParameters));
/* 1305 */       for (int i = 0; i < this.paretoNumberOfParameters; i++) {
/* 1306 */         this.paretoParamErrors[i] = Math.sqrt(mat.getElement(i, i));
/* 1307 */         if (Double.isNaN(this.paretoParamErrors[i])) { this.paretoParamErrors[i] = Math.abs(this.paretoSumOfSquares / (secondDeriv[i][i] * (this.numberOfDataPoints - this.paretoNumberOfParameters)));
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private double statFunction(int flag, Object func, double[] param)
/*      */   {
/* 1315 */     double term = 0.0D;
/* 1316 */     switch (flag) {
/*      */     case 0: 
/* 1318 */       term = ((GaussProbPlotFunc)func).function(param);
/* 1319 */       break;
/*      */     case 1: 
/* 1321 */       term = ((WeibullProbPlotFunc)func).function(param);
/* 1322 */       break;
/*      */     case 2: 
/* 1324 */       term = ((ExponentialProbPlotFunc)func).function(param);
/* 1325 */       break;
/*      */     case 3: 
/* 1327 */       term = ((RayleighProbPlotFunc)func).function(param);
/* 1328 */       break;
/*      */     case 4: 
/* 1330 */       term = ((ParetoProbPlotFunc)func).function(param);
/*      */     }
/*      */     
/* 1333 */     return term;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/ProbabilityPlot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */