/*      */ package flanagan.math;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.io.FileInput;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.awt.Canvas;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Graphics;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import javax.swing.JFrame;
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
/*      */ public class FourierTransform
/*      */   extends Canvas
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   58 */   private Complex[] complexData = null;
/*   59 */   private Complex[] complexCorr = null;
/*   60 */   private boolean complexDataSet = false;
/*   61 */   private int originalDataLength = 0;
/*   62 */   private int fftDataLength = 0;
/*   63 */   private boolean dataAltered = false;
/*      */   
/*   65 */   private double[] fftData = null;
/*      */   
/*   67 */   private double[] fftCorr = null;
/*   68 */   private double[] fftResp = null;
/*   69 */   private boolean fftDataSet = false;
/*      */   
/*   71 */   private double[] fftDataWindow = null;
/*   72 */   private double[] fftCorrWindow = null;
/*      */   
/*   74 */   private int windowOption = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   84 */   private String[] windowNames = { "no windowing applied", "Rectangular (square, box-car)", "Bartlett (triangular)", "Welch", "Hann (Hanning)", "Hamming", "Kaiser", "Gaussian" };
/*   85 */   private String windowName = this.windowNames[0];
/*   86 */   private double kaiserAlpha = 2.0D;
/*   87 */   private double gaussianAlpha = 2.5D;
/*   88 */   private double[] weights = null;
/*   89 */   private boolean windowSet = false;
/*   90 */   private boolean windowApplied = false;
/*   91 */   private double sumOfSquaredWeights = 0.0D;
/*      */   
/*   93 */   private Complex[] transformedDataComplex = null;
/*   94 */   private double[] transformedDataFft = null;
/*   95 */   private boolean fftDone = false;
/*      */   
/*      */ 
/*   98 */   private double[][] powerSpectrumEstimate = (double[][])null;
/*      */   
/*  100 */   private boolean powSpecDone = false;
/*      */   
/*  102 */   private int psdNumberOfPoints = 0;
/*      */   
/*  104 */   private int segmentNumber = 1;
/*  105 */   private int segmentLength = 0;
/*  106 */   private boolean overlap = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  111 */   private boolean segNumSet = false;
/*  112 */   private boolean segLenSet = false;
/*      */   
/*  114 */   private double deltaT = 1.0D;
/*  115 */   private boolean deltaTset = false;
/*      */   
/*  117 */   private double[][] correlationArray = (double[][])null;
/*      */   
/*  119 */   private boolean correlateDone = false;
/*      */   
/*      */ 
/*  122 */   private int numberOfWarnings = 9;
/*  123 */   private boolean[] warning = new boolean[this.numberOfWarnings];
/*      */   
/*  125 */   private int plotLineOption = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  130 */   private int plotPointOption = 0;
/*      */   
/*      */ 
/*      */ 
/*  134 */   private double[][] timeFrequency = (double[][])null;
/*      */   
/*      */ 
/*      */ 
/*  138 */   private boolean shortTimeDone = false;
/*  139 */   private int numShortFreq = 0;
/*  140 */   private int numShortTimes = 0;
/*  141 */   private String shortTitle = " ";
/*      */   
/*      */ 
/*      */   public FourierTransform()
/*      */   {
/*  146 */     for (int i = 0; i < this.numberOfWarnings; i++) { this.warning[i] = false;
/*      */     }
/*      */   }
/*      */   
/*      */   public FourierTransform(double[] realData)
/*      */   {
/*  152 */     this.originalDataLength = realData.length;
/*  153 */     this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
/*  154 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/*  155 */     for (int i = 0; i < this.originalDataLength; i++) {
/*  156 */       this.complexData[i].setReal(realData[i]);
/*  157 */       this.complexData[i].setImag(0.0D);
/*      */     }
/*  159 */     for (int i = this.originalDataLength; i < this.fftDataLength; i++) this.complexData[i].reset(0.0D, 0.0D);
/*  160 */     this.complexDataSet = true;
/*      */     
/*  162 */     this.fftData = new double[2 * this.fftDataLength];
/*  163 */     int j = 0;
/*  164 */     for (int i = 0; i < this.fftDataLength; i++) {
/*  165 */       this.fftData[j] = this.complexData[i].getReal();
/*  166 */       j++;
/*  167 */       this.fftData[j] = 0.0D;
/*  168 */       j++;
/*      */     }
/*  170 */     this.fftDataSet = true;
/*      */     
/*  172 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/*  173 */     this.weights = new double[this.fftDataLength];
/*  174 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/*  176 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/*  177 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*  178 */     this.segmentLength = this.fftDataLength;
/*      */     
/*  180 */     for (int i = 0; i < this.numberOfWarnings; i++) this.warning[i] = false;
/*      */   }
/*      */   
/*      */   public FourierTransform(Complex[] data)
/*      */   {
/*  185 */     this.originalDataLength = data.length;
/*  186 */     this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
/*  187 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/*  188 */     for (int i = 0; i < this.originalDataLength; i++) {
/*  189 */       this.complexData[i] = data[i].copy();
/*      */     }
/*  191 */     for (int i = this.originalDataLength; i < this.fftDataLength; i++) this.complexData[i].reset(0.0D, 0.0D);
/*  192 */     this.complexDataSet = true;
/*      */     
/*  194 */     this.fftData = new double[2 * this.fftDataLength];
/*  195 */     int j = 0;
/*  196 */     for (int i = 0; i < this.fftDataLength; i++) {
/*  197 */       this.fftData[j] = this.complexData[i].getReal();
/*  198 */       j++;
/*  199 */       this.fftData[j] = this.complexData[i].getImag();
/*  200 */       j++;
/*      */     }
/*  202 */     this.fftDataSet = true;
/*      */     
/*  204 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/*  205 */     this.weights = new double[this.fftDataLength];
/*  206 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/*  208 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/*  209 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*  210 */     this.segmentLength = this.fftDataLength;
/*      */     
/*  212 */     for (int i = 0; i < this.numberOfWarnings; i++) this.warning[i] = false;
/*      */   }
/*      */   
/*      */   public void setData(double[] realData)
/*      */   {
/*  217 */     this.originalDataLength = realData.length;
/*  218 */     this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
/*  219 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/*  220 */     for (int i = 0; i < this.originalDataLength; i++) {
/*  221 */       this.complexData[i].setReal(realData[i]);
/*  222 */       this.complexData[i].setImag(0.0D);
/*      */     }
/*  224 */     for (int i = this.originalDataLength; i < this.fftDataLength; i++) this.complexData[i].reset(0.0D, 0.0D);
/*  225 */     this.complexDataSet = true;
/*      */     
/*  227 */     this.fftData = new double[2 * this.fftDataLength];
/*  228 */     int j = 0;
/*  229 */     for (int i = 0; i < this.fftDataLength; i++) {
/*  230 */       this.fftData[j] = this.complexData[i].getReal();
/*  231 */       j++;
/*  232 */       this.fftData[j] = 0.0D;
/*  233 */       j++;
/*      */     }
/*  235 */     this.fftDataSet = true;
/*      */     
/*  237 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/*  238 */     this.weights = new double[this.fftDataLength];
/*  239 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/*  241 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/*  242 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*      */     
/*  244 */     if (this.segNumSet) {
/*  245 */       setSegmentNumber(this.segmentNumber);
/*      */ 
/*      */     }
/*  248 */     else if (this.segLenSet) {
/*  249 */       setSegmentLength(this.segmentLength);
/*      */     }
/*      */     else {
/*  252 */       this.segmentLength = this.fftDataLength;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setData(Complex[] data)
/*      */   {
/*  259 */     this.originalDataLength = data.length;
/*  260 */     this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
/*  261 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/*  262 */     for (int i = 0; i < this.originalDataLength; i++) {
/*  263 */       this.complexData[i] = data[i].copy();
/*      */     }
/*  265 */     for (int i = this.originalDataLength; i < this.fftDataLength; i++) this.complexData[i].reset(0.0D, 0.0D);
/*  266 */     this.complexDataSet = true;
/*      */     
/*  268 */     this.fftData = new double[2 * this.fftDataLength];
/*  269 */     int j = 0;
/*  270 */     for (int i = 0; i < this.fftDataLength; i++) {
/*  271 */       this.fftData[j] = this.complexData[i].getReal();
/*  272 */       j++;
/*  273 */       this.fftData[j] = this.complexData[i].getImag();
/*  274 */       j++;
/*      */     }
/*  276 */     this.fftDataSet = true;
/*      */     
/*  278 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/*  279 */     this.weights = new double[this.fftDataLength];
/*  280 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/*  282 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/*  283 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*      */     
/*  285 */     if (this.segNumSet) {
/*  286 */       setSegmentNumber(this.segmentNumber);
/*      */ 
/*      */     }
/*  289 */     else if (this.segLenSet) {
/*  290 */       setSegmentLength(this.segmentLength);
/*      */     }
/*      */     else {
/*  293 */       this.segmentLength = this.fftDataLength;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFftData(double[] fftdata)
/*      */   {
/*  300 */     if (fftdata.length % 2 != 0) { throw new IllegalArgumentException("data length must be an even number");
/*      */     }
/*  302 */     this.originalDataLength = (fftdata.length / 2);
/*  303 */     this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
/*  304 */     this.fftData = new double[2 * this.fftDataLength];
/*  305 */     for (int i = 0; i < 2 * this.originalDataLength; i++) this.fftData[i] = fftdata[i];
/*  306 */     for (int i = 2 * this.originalDataLength; i < 2 * this.fftDataLength; i++) this.fftData[i] = 0.0D;
/*  307 */     this.fftDataSet = true;
/*      */     
/*  309 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/*  310 */     int j = -1;
/*  311 */     for (int i = 0; i < this.fftDataLength; i++) {
/*  312 */       this.complexData[i].setReal(this.fftData[(++j)]);
/*  313 */       this.complexData[i].setImag(this.fftData[(++j)]);
/*      */     }
/*  315 */     this.complexDataSet = true;
/*      */     
/*  317 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/*  318 */     this.weights = new double[this.fftDataLength];
/*  319 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/*  321 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/*  322 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*      */     
/*  324 */     if (this.segNumSet) {
/*  325 */       setSegmentNumber(this.segmentNumber);
/*      */ 
/*      */     }
/*  328 */     else if (this.segLenSet) {
/*  329 */       setSegmentLength(this.segmentLength);
/*      */     }
/*      */     else {
/*  332 */       this.segmentLength = this.fftDataLength;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex[] getComplexInputData()
/*      */   {
/*  339 */     if (!this.complexDataSet) {
/*  340 */       System.out.println("complex data set not entered or calculated - null returned");
/*      */     }
/*  342 */     return this.complexData;
/*      */   }
/*      */   
/*      */   public double[] getAlternateInputData()
/*      */   {
/*  347 */     if (!this.fftDataSet) {
/*  348 */       System.out.println("fft data set not entered or calculted - null returned");
/*      */     }
/*  350 */     return this.fftData;
/*      */   }
/*      */   
/*      */   public double[] getAlternateWindowedInputData()
/*      */   {
/*  355 */     if (!this.fftDataSet) {
/*  356 */       System.out.println("fft data set not entered or calculted - null returned");
/*      */     }
/*  358 */     if (!this.fftDataSet) {
/*  359 */       System.out.println("fft data set not entered or calculted - null returned");
/*      */     }
/*  361 */     if (!this.windowApplied) {
/*  362 */       System.out.println("fft data set has not been multiplied by windowing weights");
/*      */     }
/*  364 */     return this.fftDataWindow;
/*      */   }
/*      */   
/*      */   public int getOriginalDataLength()
/*      */   {
/*  369 */     return this.originalDataLength;
/*      */   }
/*      */   
/*      */   public int getUsedDataLength()
/*      */   {
/*  374 */     return this.fftDataLength;
/*      */   }
/*      */   
/*      */   public void setDeltaT(double deltaT)
/*      */   {
/*  379 */     this.deltaT = deltaT;
/*  380 */     this.deltaTset = true;
/*      */   }
/*      */   
/*      */   public double getDeltaT()
/*      */   {
/*  385 */     double ret = 0.0D;
/*  386 */     if (this.deltaTset) {
/*  387 */       ret = this.deltaT;
/*      */     }
/*      */     else {
/*  390 */       System.out.println("detaT has not been set - zero returned");
/*      */     }
/*  392 */     return ret;
/*      */   }
/*      */   
/*      */   public void setRectangular()
/*      */   {
/*  397 */     this.windowOption = 1;
/*  398 */     this.windowSet = true;
/*  399 */     if (this.fftDataSet) {
/*  400 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  401 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setBartlett()
/*      */   {
/*  407 */     this.windowOption = 2;
/*  408 */     this.windowSet = true;
/*  409 */     if (this.fftDataSet) {
/*  410 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  411 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setWelch()
/*      */   {
/*  417 */     this.windowOption = 3;
/*  418 */     this.windowSet = true;
/*  419 */     if (this.fftDataSet) {
/*  420 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  421 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setHann()
/*      */   {
/*  427 */     this.windowOption = 4;
/*  428 */     this.windowSet = true;
/*  429 */     if (this.fftDataSet) {
/*  430 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  431 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setHamming()
/*      */   {
/*  437 */     this.windowOption = 5;
/*  438 */     this.windowSet = true;
/*  439 */     if (this.fftDataSet) {
/*  440 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  441 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setKaiser(double alpha)
/*      */   {
/*  447 */     this.kaiserAlpha = alpha;
/*  448 */     this.windowOption = 6;
/*  449 */     this.windowSet = true;
/*  450 */     if (this.fftDataSet) {
/*  451 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  452 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setKaiser()
/*      */   {
/*  459 */     this.windowOption = 6;
/*  460 */     this.windowSet = true;
/*  461 */     if (this.fftDataSet) {
/*  462 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  463 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setGaussian(double alpha)
/*      */   {
/*  469 */     if (alpha < 2.0D) {
/*  470 */       alpha = 2.0D;
/*  471 */       System.out.println("setGaussian; alpha must be greater than or equal to 2 - alpha has been reset to 2");
/*      */     }
/*  473 */     this.gaussianAlpha = alpha;
/*  474 */     this.windowOption = 7;
/*  475 */     this.windowSet = true;
/*  476 */     if (this.fftDataSet) {
/*  477 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  478 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setGaussian()
/*      */   {
/*  485 */     this.windowOption = 7;
/*  486 */     this.windowSet = true;
/*  487 */     if (this.fftDataSet) {
/*  488 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  489 */       this.windowApplied = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void removeWindow()
/*      */   {
/*  495 */     this.windowOption = 0;
/*  496 */     this.windowSet = false;
/*  497 */     if (this.fftDataSet) {
/*  498 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*  499 */       this.windowApplied = false;
/*      */     }
/*      */   }
/*      */   
/*      */   private double windowData(double[] data, double[] window, double[] weight)
/*      */   {
/*  505 */     int m = data.length;
/*  506 */     int n = m / 2 - 1;
/*  507 */     int j = 0;
/*  508 */     double sum = 0.0D;
/*  509 */     switch (this.windowOption)
/*      */     {
/*      */     case 0: 
/*      */     case 1: 
/*  513 */       for (int i = 0; i <= n; i++) {
/*  514 */         weight[i] = 1.0D;
/*  515 */         window[j] = data[(j++)];
/*  516 */         window[j] = data[(j++)];
/*      */       }
/*  518 */       sum = n + 1;
/*  519 */       break;
/*      */     case 2: 
/*  521 */       for (int i = 0; i <= n; i++) {
/*  522 */         weight[i] = (1.0D - Math.abs((i - n / 2) / n / 2));
/*  523 */         sum += weight[i] * weight[i];
/*  524 */         window[j] = (data[(j++)] * weight[i]);
/*  525 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*  527 */       break;
/*      */     case 3: 
/*  529 */       for (int i = 0; i <= n; i++) {
/*  530 */         weight[i] = (1.0D - Fmath.square((i - n / 2) / n / 2));
/*  531 */         sum += weight[i] * weight[i];
/*  532 */         window[j] = (data[(j++)] * weight[i]);
/*  533 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*  535 */       break;
/*      */     case 4: 
/*  537 */       for (int i = 0; i <= n; i++) {
/*  538 */         weight[i] = ((1.0D - Math.cos(2.0D * i * 3.141592653589793D / n)) / 2.0D);
/*  539 */         sum += weight[i] * weight[i];
/*  540 */         window[j] = (data[(j++)] * weight[i]);
/*  541 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*  543 */       break;
/*      */     case 5: 
/*  545 */       for (int i = 0; i <= n; i++) {
/*  546 */         weight[i] = (0.54D + 0.46D * Math.cos(2.0D * i * 3.141592653589793D / n));
/*  547 */         sum += weight[i] * weight[i];
/*  548 */         window[j] = (data[(j++)] * weight[i]);
/*  549 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*  551 */       break;
/*      */     case 6: 
/*  553 */       double denom = modBesselIo(3.141592653589793D * this.kaiserAlpha);
/*  554 */       double numer = 0.0D;
/*  555 */       for (int i = 0; i <= n; i++) {
/*  556 */         numer = modBesselIo(3.141592653589793D * this.kaiserAlpha * Math.sqrt(1.0D - Fmath.square(2.0D * i / n - 1.0D)));
/*  557 */         weight[i] = (numer / denom);
/*  558 */         sum += weight[i] * weight[i];
/*  559 */         window[j] = (data[(j++)] * weight[i]);
/*  560 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*  562 */       break;
/*      */     case 7: 
/*  564 */       for (int i = 0; i <= n; i++) {
/*  565 */         weight[i] = Math.exp(-0.5D * Fmath.square(this.gaussianAlpha * (2 * i - n) / n));
/*  566 */         sum += weight[i] * weight[i];
/*  567 */         window[j] = (data[(j++)] * weight[i]);
/*  568 */         window[j] = (data[(j++)] * weight[i]);
/*      */       }
/*      */     }
/*      */     
/*  572 */     return sum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double modBesselIo(double arg)
/*      */   {
/*  579 */     double absArg = 0.0D;
/*  580 */     double poly = 0.0D;
/*  581 */     double bessel = 0.0D;
/*      */     
/*  583 */     if ((absArg = Math.abs(arg)) < 3.75D) {
/*  584 */       poly = arg / 3.75D;
/*  585 */       poly *= poly;
/*  586 */       bessel = 1.0D + poly * (3.5156229D + poly * (3.08989424D + poly * (1.2067492D + poly * (0.2659732D + poly * (0.0360768D + poly * 0.0045813D)))));
/*      */     }
/*      */     else {
/*  589 */       bessel = Math.exp(absArg) / Math.sqrt(absArg) * (0.39894228D + poly * (0.01328592D + poly * (0.00225319D + poly * (-0.00157565D + poly * (0.00916281D + poly * (-0.02057706D + poly * (0.02635537D + poly * (-0.01647633D + poly * 0.00392377D))))))));
/*      */     }
/*  591 */     return bessel;
/*      */   }
/*      */   
/*      */   public String getWindowOption()
/*      */   {
/*  596 */     String option = " ";
/*  597 */     switch (this.windowOption) {
/*  598 */     case 0:  option = "No windowing applied";
/*  599 */       break;
/*  600 */     case 1:  option = "Rectangular";
/*  601 */       break;
/*  602 */     case 2:  option = "Bartlett";
/*  603 */       break;
/*  604 */     case 3:  option = "Welch";
/*  605 */       break;
/*  606 */     case 4:  option = "Hann";
/*  607 */       break;
/*  608 */     case 5:  option = "Hamming";
/*  609 */       break;
/*  610 */     case 6:  option = "Kaiser";
/*  611 */       break;
/*  612 */     case 7:  option = "Gaussian";
/*      */     }
/*      */     
/*  615 */     return option;
/*      */   }
/*      */   
/*      */   public double[] getWeights()
/*      */   {
/*  620 */     return this.weights;
/*      */   }
/*      */   
/*      */   public void setSegmentNumber(int sNum)
/*      */   {
/*  625 */     this.segmentNumber = sNum;
/*  626 */     this.segNumSet = true;
/*  627 */     if (this.segLenSet) this.segLenSet = false;
/*      */   }
/*      */   
/*      */   public void setSegmentLength(int sLen)
/*      */   {
/*  632 */     this.segmentLength = sLen;
/*  633 */     this.segLenSet = true;
/*  634 */     if (this.segNumSet) this.segNumSet = false;
/*      */   }
/*      */   
/*      */   private void checkSegmentDetails()
/*      */   {
/*  639 */     if (!this.fftDataSet) throw new IllegalArgumentException("No fft data has been entered or calculated");
/*  640 */     if (this.fftDataLength < 2) { throw new IllegalArgumentException("More than one point, MANY MORE, are needed");
/*      */     }
/*      */     
/*  643 */     if (this.fftDataLength % 2 != 0) {
/*  644 */       System.out.println("Number of data points must be an even number");
/*  645 */       System.out.println("last point deleted");
/*  646 */       this.fftDataLength -= 1;
/*  647 */       this.dataAltered = true;
/*  648 */       this.warning[0] = true;
/*      */     }
/*      */     
/*      */ 
/*  652 */     if ((this.segNumSet) && (!this.overlap)) {
/*  653 */       if (this.fftDataLength % this.segmentNumber == 0) {
/*  654 */         int segL = this.fftDataLength / this.segmentNumber;
/*  655 */         if (checkPowerOfTwo(segL)) {
/*  656 */           this.segmentLength = segL;
/*  657 */           this.segLenSet = true;
/*      */         }
/*      */         else {
/*  660 */           System.out.println("segment length is not an integer power of two");
/*  661 */           System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  662 */           this.warning[1] = true;
/*  663 */           this.segmentNumber = 1;
/*  664 */           this.segmentLength = this.fftDataLength;
/*  665 */           this.segLenSet = true;
/*      */         }
/*      */       }
/*      */       else {
/*  669 */         System.out.println("total data length divided by the number of segments is not an integer");
/*  670 */         System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  671 */         this.warning[2] = true;
/*  672 */         this.segmentNumber = 1;
/*  673 */         this.segmentLength = this.fftDataLength;
/*  674 */         this.segLenSet = true;
/*      */       }
/*      */     }
/*      */     
/*  678 */     if ((this.segLenSet) && (!this.overlap)) {
/*  679 */       if (this.fftDataLength % this.segmentLength == 0) {
/*  680 */         if (checkPowerOfTwo(this.segmentLength)) {
/*  681 */           this.segmentNumber = (this.fftDataLength / this.segmentLength);
/*  682 */           this.segNumSet = true;
/*      */         }
/*      */         else {
/*  685 */           System.out.println("segment length is not an integer power of two");
/*  686 */           System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  687 */           this.warning[1] = true;
/*  688 */           this.segmentNumber = 1;
/*  689 */           this.segmentLength = this.fftDataLength;
/*  690 */           this.segNumSet = true;
/*      */         }
/*      */       }
/*      */       else {
/*  694 */         System.out.println("total data length divided by the segment length is not an integer");
/*  695 */         System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  696 */         this.warning[3] = true;
/*  697 */         this.segmentNumber = 1;
/*  698 */         this.segmentLength = this.fftDataLength;
/*  699 */         this.segNumSet = true;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  704 */     if ((this.segNumSet) && (this.overlap)) {
/*  705 */       if (this.fftDataLength % (this.segmentNumber + 1) == 0) {
/*  706 */         int segL = 2 * this.fftDataLength / (this.segmentNumber + 1);
/*  707 */         if (checkPowerOfTwo(segL)) {
/*  708 */           this.segmentLength = segL;
/*  709 */           this.segLenSet = true;
/*      */         }
/*      */         else {
/*  712 */           System.out.println("segment length is not an integer power of two");
/*  713 */           System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  714 */           this.warning[1] = true;
/*  715 */           this.segmentNumber = 1;
/*  716 */           this.segmentLength = this.fftDataLength;
/*  717 */           this.segLenSet = true;
/*  718 */           this.overlap = false;
/*      */         }
/*      */       }
/*      */       else {
/*  722 */         System.out.println("total data length divided by the number of segments plus one is not an integer");
/*  723 */         System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  724 */         this.warning[4] = true;
/*  725 */         this.segmentNumber = 1;
/*  726 */         this.segmentLength = this.fftDataLength;
/*  727 */         this.segLenSet = true;
/*  728 */         this.overlap = false;
/*      */       }
/*      */     }
/*      */     
/*  732 */     if ((this.segLenSet) && (this.overlap)) {
/*  733 */       if (2 * this.fftDataLength % this.segmentLength == 0) {
/*  734 */         if (checkPowerOfTwo(this.segmentLength)) {
/*  735 */           this.segmentNumber = (2 * this.fftDataLength / this.segmentLength - 1);
/*  736 */           this.segNumSet = true;
/*      */         }
/*      */         else {
/*  739 */           System.out.println("segment length is not an integer power of two");
/*  740 */           System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  741 */           this.warning[1] = true;
/*  742 */           this.segmentNumber = 1;
/*  743 */           this.segmentLength = this.fftDataLength;
/*  744 */           this.segNumSet = true;
/*  745 */           this.overlap = false;
/*      */         }
/*      */       }
/*      */       else {
/*  749 */         System.out.println("twice the total data length divided by the segment length is not an integer");
/*  750 */         System.out.println("segment length reset to total data length, i.e. no segmentation");
/*  751 */         this.warning[5] = true;
/*  752 */         this.segmentNumber = 1;
/*  753 */         this.segmentLength = this.fftDataLength;
/*  754 */         this.segNumSet = true;
/*  755 */         this.overlap = false;
/*      */       }
/*      */     }
/*      */     
/*  759 */     if ((!this.segNumSet) && (!this.segLenSet)) {
/*  760 */       this.segmentNumber = 1;
/*  761 */       this.segNumSet = true;
/*  762 */       this.overlap = false;
/*      */     }
/*      */     
/*  765 */     if ((this.overlap) && (this.segmentNumber < 2)) {
/*  766 */       System.out.println("Overlap is not possible with less than two segments.");
/*  767 */       System.out.println("Overlap option has been reset to 'no overlap' i.e. to false.");
/*  768 */       this.overlap = false;
/*  769 */       this.segmentNumber = 1;
/*  770 */       this.segNumSet = true;
/*  771 */       this.warning[6] = true;
/*      */     }
/*      */     
/*      */ 
/*  775 */     int segLno = 0;
/*  776 */     int segNno = 0;
/*  777 */     int segLov = 0;
/*  778 */     int segNov = 0;
/*      */     
/*  780 */     if (this.segmentNumber == 1)
/*      */     {
/*  782 */       if (!checkPowerOfTwo(this.fftDataLength)) {
/*  783 */         boolean test0 = true;
/*  784 */         boolean test1 = true;
/*  785 */         boolean test2 = true;
/*  786 */         int newL = 0;
/*  787 */         int ii = 2;
/*      */         
/*      */ 
/*  790 */         while (test0) {
/*  791 */           newL = this.fftDataLength / ii;
/*  792 */           if ((checkPowerOfTwo(newL)) && (this.fftDataLength % ii == 0)) {
/*  793 */             test0 = false;
/*  794 */             segLno = newL;
/*  795 */             segNno = ii;
/*      */ 
/*      */           }
/*  798 */           else if (newL < 2) {
/*  799 */             test1 = false;
/*  800 */             test0 = false;
/*      */           }
/*      */           else {
/*  803 */             ii++;
/*      */           }
/*      */         }
/*      */         
/*  807 */         test0 = true;
/*  808 */         ii = 2;
/*      */         
/*  810 */         while (test0) {
/*  811 */           newL = 2 * (this.fftDataLength / (ii + 1));
/*  812 */           if ((checkPowerOfTwo(newL)) && (this.fftDataLength % (ii + 1) == 0)) {
/*  813 */             test0 = false;
/*  814 */             segLov = newL;
/*  815 */             segNov = ii;
/*      */ 
/*      */           }
/*  818 */           else if (newL < 2) {
/*  819 */             test2 = false;
/*  820 */             test0 = false;
/*      */           }
/*      */           else {
/*  823 */             ii++;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  828 */         boolean setSegment = true;
/*  829 */         int segL = 0;
/*  830 */         int segN = 0;
/*  831 */         boolean ovrlp = false;
/*  832 */         if (test1) {
/*  833 */           if (test2) {
/*  834 */             if (segLov > segLno) {
/*  835 */               segL = segLov;
/*  836 */               segN = segNov;
/*  837 */               ovrlp = true;
/*      */             }
/*      */             else {
/*  840 */               segL = segLno;
/*  841 */               segN = segNno;
/*  842 */               ovrlp = false;
/*      */             }
/*      */           }
/*      */           else {
/*  846 */             segL = segLno;
/*  847 */             segN = segNno;
/*  848 */             ovrlp = false;
/*      */           }
/*      */           
/*      */         }
/*  852 */         else if (test2) {
/*  853 */           segL = segLov;
/*  854 */           segN = segNov;
/*  855 */           ovrlp = true;
/*      */         }
/*      */         else {
/*  858 */           setSegment = false;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  863 */         if ((setSegment) && (this.originalDataLength - segL <= this.fftDataLength - this.originalDataLength)) {
/*  864 */           System.out.println("Data length is not an integer power of two");
/*  865 */           System.out.println("Data cannot be transformed as a single segment");
/*  866 */           System.out.print("The data has been split into " + segN + " segments of length " + segL);
/*  867 */           if (ovrlp) {
/*  868 */             System.out.println(" with 50% overlap");
/*      */           }
/*      */           else {
/*  871 */             System.out.println(" with no overlap");
/*      */           }
/*  873 */           this.segmentLength = segL;
/*  874 */           this.segmentNumber = segN;
/*  875 */           this.overlap = ovrlp;
/*  876 */           this.warning[7] = true;
/*      */         }
/*      */         else {
/*  879 */           System.out.println("Data length is not an integer power of two");
/*  880 */           if (this.dataAltered) {
/*  881 */             System.out.println("Deleted point has been restored and the data has been padded with zeros to give a power of two length");
/*  882 */             this.warning[0] = false;
/*      */           }
/*      */           else {
/*  885 */             System.out.println("Data has been padded with zeros to give a power of two length");
/*      */           }
/*  887 */           this.fftDataLength = this.fftDataLength;
/*  888 */           this.warning[8] = true;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void printWarnings(FileOutput fout) {
/*  895 */     if (this.warning[0] != 0) {
/*  896 */       fout.println("WARNING!");
/*  897 */       fout.println("Number of data points must be an even number");
/*  898 */       fout.println("The last point was deleted");
/*  899 */       fout.println();
/*      */     }
/*      */     
/*  902 */     if (this.warning[1] != 0) {
/*  903 */       fout.println("WARNING!");
/*  904 */       fout.println("Segment length was not an integer power of two");
/*  905 */       fout.println("Segment length was reset to total data length, i.e. no segmentation");
/*  906 */       fout.println();
/*      */     }
/*      */     
/*  909 */     if (this.warning[2] != 0) {
/*  910 */       fout.println("WARNING!");
/*  911 */       fout.println("Total data length divided by the number of segments was not an integer");
/*  912 */       fout.println("Segment length was reset to total data length, i.e. no segmentation");
/*  913 */       fout.println();
/*      */     }
/*      */     
/*  916 */     if (this.warning[3] != 0) {
/*  917 */       fout.println("WARNING!");
/*  918 */       fout.println("Total data length divided by the segment length was not an integer");
/*  919 */       fout.println("Segment length was reset to total data length, i.e. no segmentation");
/*  920 */       fout.println();
/*      */     }
/*      */     
/*  923 */     if (this.warning[4] != 0) {
/*  924 */       fout.println("WARNING!");
/*  925 */       fout.println("Total data length divided by the number of segments plus one was not an integer");
/*  926 */       fout.println("Segment length was reset to total data length, i.e. no segmentation");
/*  927 */       fout.println();
/*      */     }
/*      */     
/*  930 */     if (this.warning[5] != 0) {
/*  931 */       fout.println("WARNING!");
/*  932 */       fout.println("Twice the total data length divided by the segment length was not an integer");
/*  933 */       fout.println("Segment length was reset to total data length, i.e. no segmentation");
/*  934 */       fout.println();
/*      */     }
/*      */     
/*  937 */     if (this.warning[6] != 0) {
/*  938 */       fout.println("WARNING!");
/*  939 */       fout.println("Overlap is not possible with less than two segments");
/*  940 */       fout.println("Overlap option has been reset to 'no overlap' i.e. to false");
/*  941 */       fout.println();
/*      */     }
/*      */     
/*  944 */     if (this.warning[7] != 0) {
/*  945 */       fout.println("WARNING!");
/*  946 */       fout.println("Data length was not an integer power of two");
/*  947 */       fout.println("The data could not be transformed as a single segment");
/*  948 */       fout.print("The data has been split into " + this.segmentNumber + " segment/s of length " + this.segmentLength);
/*  949 */       if (this.overlap) {
/*  950 */         fout.println(" with 50% overlap");
/*      */       }
/*      */       else {
/*  953 */         fout.println(" with no overlap");
/*      */       }
/*  955 */       fout.println();
/*      */     }
/*      */     
/*  958 */     if (this.warning[8] != 0) {
/*  959 */       fout.println("WARNING!");
/*  960 */       fout.println("Data length was not an integer power of two");
/*  961 */       fout.println("Data has been padded with " + (this.fftDataLength - this.originalDataLength) + " zeros to give an integer power of two length");
/*  962 */       fout.println();
/*      */     }
/*      */   }
/*      */   
/*      */   public int getSegmentNumber()
/*      */   {
/*  968 */     return this.segmentNumber;
/*      */   }
/*      */   
/*      */   public int getSegmentLength()
/*      */   {
/*  973 */     return this.segmentLength;
/*      */   }
/*      */   
/*      */   public void setOverlapOption(boolean overlapOpt)
/*      */   {
/*  978 */     boolean old = this.overlap;
/*  979 */     this.overlap = overlapOpt;
/*  980 */     if ((old != this.overlap) && 
/*  981 */       (this.fftDataSet)) {
/*  982 */       setSegmentNumber(this.segmentNumber);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getOverlapOption()
/*      */   {
/*  989 */     return this.overlap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int calcDataLength(boolean overlap, int segLen, int segNum)
/*      */   {
/*  996 */     if (overlap) {
/*  997 */       return (segNum + 1) * segLen / 2;
/*      */     }
/*      */     
/* 1000 */     return segNum * segLen;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void transform()
/*      */   {
/* 1008 */     int isign = 1;
/* 1009 */     if (!this.fftDataSet) throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
/* 1010 */     if (this.originalDataLength != this.fftDataLength) {
/* 1011 */       System.out.println("Fast Fourier Transform data length ," + this.originalDataLength + ", is not an integer power of two");
/* 1012 */       System.out.println("WARNING!!! Data has been padded with zeros to fill to nearest integer power of two length " + this.fftDataLength);
/*      */     }
/*      */     
/*      */ 
/* 1016 */     double[] hold = new double[this.fftDataLength * 2];
/* 1017 */     for (int i = 0; i < this.fftDataLength * 2; i++) hold[i] = this.fftDataWindow[i];
/* 1018 */     basicFft(hold, this.fftDataLength, isign);
/* 1019 */     for (int i = 0; i < this.fftDataLength * 2; i++) { this.transformedDataFft[i] = hold[i];
/*      */     }
/*      */     
/* 1022 */     for (int i = 0; i < this.fftDataLength; i++) {
/* 1023 */       this.transformedDataComplex[i].reset(this.transformedDataFft[(2 * i)], this.transformedDataFft[(2 * i + 1)]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void inverse()
/*      */   {
/* 1031 */     int isign = -1;
/* 1032 */     if (!this.fftDataSet) throw new IllegalArgumentException("No data has been entered for the inverse Fast Fourier Transform");
/* 1033 */     if (this.originalDataLength != this.fftDataLength) {
/* 1034 */       System.out.println("Fast Fourier Transform data length ," + this.originalDataLength + ", is not an integer power of two");
/* 1035 */       System.out.println("WARNING!!! Data has been padded with zeros to fill to nearest integer power of two length " + this.fftDataLength);
/*      */     }
/*      */     
/*      */ 
/* 1039 */     double[] hold = new double[this.fftDataLength * 2];
/* 1040 */     for (int i = 0; i < this.fftDataLength * 2; i++) hold[i] = this.fftDataWindow[i];
/* 1041 */     basicFft(hold, this.fftDataLength, isign);
/*      */     
/* 1043 */     for (int i = 0; i < this.fftDataLength * 2; i++) { this.transformedDataFft[i] = (hold[i] / this.fftDataLength);
/*      */     }
/*      */     
/* 1046 */     for (int i = 0; i < this.fftDataLength; i++) {
/* 1047 */       this.transformedDataComplex[i].reset(this.transformedDataFft[(2 * i)], this.transformedDataFft[(2 * i + 1)]);
/*      */     }
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
/*      */   public void basicFft(double[] data, int nn, int isign)
/*      */   {
/* 1064 */     double dtemp = 0.0D;double wtemp = 0.0D;double tempr = 0.0D;double tempi = 0.0D;
/* 1065 */     double theta = 0.0D;double wr = 0.0D;double wpr = 0.0D;double wpi = 0.0D;double wi = 0.0D;
/* 1066 */     int istep = 0;int m = 0;int mmax = 0;
/* 1067 */     int n = nn << 1;
/* 1068 */     int j = 1;
/* 1069 */     int jj = 0;
/* 1070 */     for (int i = 1; i < n; i += 2) {
/* 1071 */       jj = j - 1;
/* 1072 */       if (j > i) {
/* 1073 */         int ii = i - 1;
/* 1074 */         dtemp = data[jj];
/* 1075 */         data[jj] = data[ii];
/* 1076 */         data[ii] = dtemp;
/* 1077 */         dtemp = data[(jj + 1)];
/* 1078 */         data[(jj + 1)] = data[(ii + 1)];
/* 1079 */         data[(ii + 1)] = dtemp;
/*      */       }
/* 1081 */       m = n >> 1;
/* 1082 */       while ((m >= 2) && (j > m)) {
/* 1083 */         j -= m;
/* 1084 */         m >>= 1;
/*      */       }
/* 1086 */       j += m;
/*      */     }
/* 1088 */     mmax = 2;
/* 1089 */     while (n > mmax) {
/* 1090 */       istep = mmax << 1;
/* 1091 */       theta = isign * (6.28318530717959D / mmax);
/* 1092 */       wtemp = Math.sin(0.5D * theta);
/* 1093 */       wpr = -2.0D * wtemp * wtemp;
/* 1094 */       wpi = Math.sin(theta);
/* 1095 */       wr = 1.0D;
/* 1096 */       wi = 0.0D;
/* 1097 */       for (m = 1; m < mmax; m = (int)(m + 2L)) {
/* 1098 */         for (int i = m; i <= n; i += istep) {
/* 1099 */           int ii = i - 1;
/* 1100 */           jj = ii + mmax;
/* 1101 */           tempr = wr * data[jj] - wi * data[(jj + 1)];
/* 1102 */           tempi = wr * data[(jj + 1)] + wi * data[jj];
/* 1103 */           data[ii] -= tempr;
/* 1104 */           data[(ii + 1)] -= tempi;
/* 1105 */           data[ii] += tempr;
/* 1106 */           data[(ii + 1)] += tempi;
/*      */         }
/* 1108 */         wr = (wtemp = wr) * wpr - wi * wpi + wr;
/* 1109 */         wi = wi * wpr + wtemp * wpi + wi;
/*      */       }
/* 1111 */       mmax = istep;
/*      */     }
/*      */   }
/*      */   
/*      */   public Complex[] getTransformedDataAsComplex()
/*      */   {
/* 1117 */     return this.transformedDataComplex;
/*      */   }
/*      */   
/*      */   public double[] getTransformedDataAsAlternate()
/*      */   {
/* 1122 */     return this.transformedDataFft;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] powerSpectrum()
/*      */   {
/* 1130 */     checkSegmentDetails();
/*      */     
/* 1132 */     this.psdNumberOfPoints = (this.segmentLength / 2);
/* 1133 */     this.powerSpectrumEstimate = new double[2][this.psdNumberOfPoints];
/*      */     
/* 1135 */     if ((!this.overlap) && (this.segmentNumber < 2))
/*      */     {
/*      */ 
/*      */ 
/* 1139 */       int isign = 1;
/* 1140 */       if (!this.fftDataSet) throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
/* 1141 */       if (!checkPowerOfTwo(this.fftDataLength)) { throw new IllegalArgumentException("Fast Fourier Transform data length ," + this.fftDataLength + ", is not an integer power of two");
/*      */       }
/*      */       
/* 1144 */       double[] hold = new double[this.fftDataLength * 2];
/* 1145 */       for (int i = 0; i < this.fftDataLength * 2; i++) hold[i] = this.fftDataWindow[i];
/* 1146 */       basicFft(hold, this.fftDataLength, isign);
/* 1147 */       for (int i = 0; i < this.fftDataLength * 2; i++) { this.transformedDataFft[i] = hold[i];
/*      */       }
/*      */       
/* 1150 */       for (int i = 0; i < this.fftDataLength; i++) {
/* 1151 */         this.transformedDataComplex[i].reset(this.transformedDataFft[(2 * i)], this.transformedDataFft[(2 * i + 1)]);
/*      */       }
/*      */       
/*      */ 
/* 1155 */       this.powerSpectrumEstimate[1][0] = (Fmath.square(hold[0]) + Fmath.square(hold[1]));
/* 1156 */       for (int i = 1; i < this.psdNumberOfPoints; i++) {
/* 1157 */         this.powerSpectrumEstimate[1][i] = (Fmath.square(hold[(2 * i)]) + Fmath.square(hold[(2 * i + 1)]) + Fmath.square(hold[(2 * this.segmentLength - 2 * i)]) + Fmath.square(hold[(2 * this.segmentLength - 2 * i + 1)]));
/*      */       }
/*      */       
/*      */ 
/* 1161 */       for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1162 */         this.powerSpectrumEstimate[1][i] = (2.0D * this.powerSpectrumEstimate[1][i] / (this.fftDataLength * this.sumOfSquaredWeights));
/*      */       }
/*      */       
/*      */ 
/* 1166 */       for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1167 */         this.powerSpectrumEstimate[0][i] = (i / (this.segmentLength * this.deltaT));
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1172 */       this.powerSpectrumEstimate = powerSpectrumSeg();
/*      */     }
/*      */     
/* 1175 */     this.powSpecDone = true;
/*      */     
/* 1177 */     return this.powerSpectrumEstimate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] powerSpectrum(String fileName)
/*      */   {
/* 1185 */     if (!checkPowerOfTwo(this.segmentLength)) { throw new IllegalArgumentException("Fast Fourier Transform segment length ," + this.segmentLength + ", is not an integer power of two");
/*      */     }
/* 1187 */     FileInput fin = new FileInput(fileName);
/*      */     
/* 1189 */     this.psdNumberOfPoints = (this.segmentLength / 2);
/* 1190 */     this.powerSpectrumEstimate = new double[2][this.psdNumberOfPoints];
/* 1191 */     this.fftDataLength = calcDataLength(this.overlap, this.segmentLength, this.segmentNumber);
/*      */     
/* 1193 */     if ((!this.overlap) && (this.segmentNumber < 2))
/*      */     {
/*      */ 
/*      */ 
/* 1197 */       this.fftData = new double[2 * this.fftDataLength];
/* 1198 */       int j = -1;
/* 1199 */       for (int i = 0; i < this.segmentLength; i++) {
/* 1200 */         this.fftData[(++j)] = fin.readDouble();
/* 1201 */         this.fftData[(++j)] = fin.readDouble();
/*      */       }
/*      */       
/* 1204 */       this.complexData = Complex.oneDarray(this.fftDataLength);
/* 1205 */       j = -1;
/* 1206 */       for (int i = 0; i < this.fftDataLength; i++) {
/* 1207 */         this.complexData[i].setReal(this.fftData[(++j)]);
/* 1208 */         this.complexData[i].setImag(this.fftData[(++j)]);
/*      */       }
/*      */       
/* 1211 */       this.fftDataWindow = new double[2 * this.fftDataLength];
/* 1212 */       this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */       
/*      */ 
/* 1215 */       int isign = 1;
/* 1216 */       double[] hold = new double[this.fftDataLength * 2];
/* 1217 */       for (int i = 0; i < this.fftDataLength * 2; i++) hold[i] = this.fftDataWindow[i];
/* 1218 */       basicFft(hold, this.fftDataLength, isign);
/* 1219 */       for (int i = 0; i < this.fftDataLength * 2; i++) { this.transformedDataFft[i] = hold[i];
/*      */       }
/*      */       
/* 1222 */       for (int i = 0; i < this.fftDataLength; i++) {
/* 1223 */         this.transformedDataComplex[i].reset(this.transformedDataFft[(2 * i)], this.transformedDataFft[(2 * i + 1)]);
/*      */       }
/*      */       
/*      */ 
/* 1227 */       this.powerSpectrumEstimate[1][0] = (Fmath.square(hold[0]) + Fmath.square(hold[1]));
/* 1228 */       for (int i = 1; i < this.psdNumberOfPoints; i++) {
/* 1229 */         this.powerSpectrumEstimate[1][i] = (Fmath.square(hold[(2 * i)]) + Fmath.square(hold[(2 * i + 1)]) + Fmath.square(hold[(2 * this.segmentLength - 2 * i)]) + Fmath.square(hold[(2 * this.segmentLength - 2 * i + 1)]));
/*      */       }
/*      */       
/*      */ 
/* 1233 */       for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1234 */         this.powerSpectrumEstimate[1][i] = (2.0D * this.powerSpectrumEstimate[1][i] / (this.fftDataLength * this.sumOfSquaredWeights));
/*      */       }
/*      */       
/*      */ 
/* 1238 */       for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1239 */         this.powerSpectrumEstimate[0][i] = (i / (this.segmentLength * this.deltaT));
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 1245 */       this.powerSpectrumEstimate = powerSpectrumSeg(fin);
/*      */     }
/*      */     
/* 1248 */     this.powSpecDone = true;
/*      */     
/* 1250 */     return this.powerSpectrumEstimate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double[][] powerSpectrumSeg()
/*      */   {
/* 1260 */     int segmentStartIndex = 0;
/* 1261 */     int segmentStartIncrement = this.segmentLength;
/* 1262 */     if (this.overlap) segmentStartIncrement /= 2;
/* 1263 */     double[] data = new double[2 * this.segmentLength];
/* 1264 */     this.psdNumberOfPoints = (this.segmentLength / 2);
/* 1265 */     double[] segPSD = new double[this.psdNumberOfPoints];
/* 1266 */     double[][] avePSD = new double[2][this.psdNumberOfPoints];
/*      */     
/*      */ 
/*      */ 
/* 1270 */     for (int j = 0; j < this.psdNumberOfPoints; j++) avePSD[1][j] = 0.0D;
/* 1271 */     int isign = 1;
/*      */     
/*      */ 
/* 1274 */     for (int i = 1; i <= this.segmentNumber; i++)
/*      */     {
/*      */ 
/* 1277 */       for (int j = 0; j < 2 * this.segmentLength; j++) { data[j] = this.fftData[(segmentStartIndex + j)];
/*      */       }
/*      */       
/* 1280 */       if (i == 1) {
/* 1281 */         this.sumOfSquaredWeights = windowData(data, data, this.weights);
/*      */       }
/*      */       else {
/* 1284 */         int k = 0;
/* 1285 */         for (int j = 0; j < this.segmentLength; j++) {
/* 1286 */           data[k] *= this.weights[j];
/* 1287 */           data[(++k)] = (data[k] * this.weights[j]);
/* 1288 */           k++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1293 */       basicFft(data, this.segmentLength, isign);
/*      */       
/*      */ 
/* 1296 */       segPSD[0] = (Fmath.square(data[0]) + Fmath.square(data[1]));
/* 1297 */       for (int j = 1; j < this.psdNumberOfPoints; j++) {
/* 1298 */         segPSD[j] = (Fmath.square(data[(2 * j)]) + Fmath.square(data[(2 * j + 1)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j + 1)]));
/*      */       }
/*      */       
/*      */ 
/* 1302 */       for (int j = 0; j < this.psdNumberOfPoints; j++) {
/* 1303 */         segPSD[j] = (2.0D * segPSD[j] / (this.segmentLength * this.sumOfSquaredWeights));
/*      */       }
/*      */       
/*      */ 
/* 1307 */       for (int j = 0; j < this.psdNumberOfPoints; j++) { avePSD[1][j] += segPSD[j];
/*      */       }
/*      */       
/* 1310 */       segmentStartIndex += segmentStartIncrement;
/*      */     }
/*      */     
/*      */ 
/* 1314 */     for (int j = 0; j < this.psdNumberOfPoints; j++) { avePSD[1][j] /= this.segmentNumber;
/*      */     }
/*      */     
/* 1317 */     for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1318 */       avePSD[0][i] = (i / (this.segmentLength * this.deltaT));
/*      */     }
/*      */     
/* 1321 */     return avePSD;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double[][] powerSpectrumSeg(FileInput fin)
/*      */   {
/* 1330 */     double[] data = new double[2 * this.segmentLength];
/* 1331 */     this.weights = new double[this.segmentLength];
/* 1332 */     double[] hold = new double[2 * this.segmentLength];
/* 1333 */     this.psdNumberOfPoints = (this.segmentLength / 2);
/* 1334 */     double[] segPSD = new double[this.psdNumberOfPoints];
/* 1335 */     double[][] avePSD = new double[2][this.psdNumberOfPoints];
/*      */     
/*      */ 
/*      */ 
/* 1339 */     for (int j = 0; j < this.psdNumberOfPoints; j++) avePSD[1][j] = 0.0D;
/* 1340 */     int isign = 1;
/*      */     
/*      */ 
/* 1343 */     this.sumOfSquaredWeights = windowData(hold, hold, this.weights);
/*      */     
/* 1345 */     if (this.overlap)
/*      */     {
/*      */ 
/*      */ 
/* 1349 */       for (int j = 0; j < this.segmentLength; j++) {
/* 1350 */         data[j] = fin.readDouble();
/*      */       }
/*      */       
/*      */ 
/* 1354 */       for (int i = 1; i <= this.segmentNumber; i++)
/*      */       {
/*      */ 
/* 1357 */         for (int j = 0; j < this.segmentLength; j++) {
/* 1358 */           data[(j + this.segmentLength)] = fin.readDouble();
/*      */         }
/*      */         
/*      */ 
/* 1362 */         int k = -1;
/* 1363 */         for (int j = 0; j < this.segmentLength; j++) {
/* 1364 */           data[(++k)] = (data[k] * this.weights[j]);
/* 1365 */           data[(++k)] = (data[k] * this.weights[j]);
/*      */         }
/*      */         
/*      */ 
/* 1369 */         basicFft(data, this.segmentLength, isign);
/*      */         
/*      */ 
/* 1372 */         segPSD[0] = (Fmath.square(data[0]) + Fmath.square(data[1]));
/* 1373 */         for (int j = 1; j < this.psdNumberOfPoints; j++) {
/* 1374 */           segPSD[j] = (Fmath.square(data[(2 * j)]) + Fmath.square(data[(2 * j + 1)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j + 1)]));
/*      */         }
/*      */         
/*      */ 
/* 1378 */         for (int j = 0; j < this.psdNumberOfPoints; j++) {
/* 1379 */           segPSD[j] = (2.0D * segPSD[j] / (this.segmentLength * this.sumOfSquaredWeights));
/*      */         }
/*      */         
/*      */ 
/* 1383 */         for (int j = 0; j < this.psdNumberOfPoints; j++) { avePSD[1][j] += segPSD[j];
/*      */         }
/*      */         
/* 1386 */         for (int j = 0; j < this.segmentLength; j++) {
/* 1387 */           data[j] = data[(j + this.segmentLength)];
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 1395 */       for (int i = 1; i <= this.segmentNumber; i++)
/*      */       {
/*      */ 
/* 1398 */         for (int j = 0; j < 2 * this.segmentLength; j++) {
/* 1399 */           data[j] = fin.readDouble();
/*      */         }
/*      */         
/*      */ 
/* 1403 */         int k = -1;
/* 1404 */         for (int j = 0; j < this.segmentLength; j++) {
/* 1405 */           data[(++k)] = (data[k] * this.weights[j]);
/* 1406 */           data[(++k)] = (data[k] * this.weights[j]);
/*      */         }
/*      */         
/*      */ 
/* 1410 */         basicFft(data, this.segmentLength, isign);
/*      */         
/*      */ 
/* 1413 */         segPSD[0] = (Fmath.square(data[0]) + Fmath.square(data[1]));
/* 1414 */         for (int j = 1; j < this.psdNumberOfPoints; j++) {
/* 1415 */           segPSD[j] = (Fmath.square(data[(2 * j)]) + Fmath.square(data[(2 * j + 1)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j)]) + Fmath.square(data[(2 * this.segmentLength - 2 * j + 1)]));
/*      */         }
/*      */         
/*      */ 
/* 1419 */         for (int j = 1; j < this.psdNumberOfPoints; j++) {
/* 1420 */           segPSD[j] = (2.0D * segPSD[j] / (this.segmentLength * this.sumOfSquaredWeights));
/*      */         }
/*      */         
/*      */ 
/* 1424 */         for (int j = 0; j < this.psdNumberOfPoints; j++) { avePSD[1][j] += segPSD[j];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1429 */     for (int j = 0; j < this.psdNumberOfPoints; j++) { avePSD[1][j] /= this.segmentNumber;
/*      */     }
/*      */     
/* 1432 */     for (int i = 0; i < this.psdNumberOfPoints; i++) {
/* 1433 */       avePSD[0][i] = (i / (this.segmentLength * this.deltaT));
/*      */     }
/*      */     
/* 1436 */     return avePSD;
/*      */   }
/*      */   
/*      */   public double[][] getpowerSpectrumEstimate()
/*      */   {
/* 1441 */     if (!this.powSpecDone) System.out.println("getpowerSpectrumEstimate - powerSpectrum has not been called - null returned");
/* 1442 */     return this.powerSpectrumEstimate;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getNumberOfPsdPoints()
/*      */   {
/* 1448 */     return this.psdNumberOfPoints;
/*      */   }
/*      */   
/*      */ 
/*      */   public void printPowerSpectrum()
/*      */   {
/* 1454 */     String filename = "FourierTransformPSD.txt";
/* 1455 */     printPowerSpectrum(filename);
/*      */   }
/*      */   
/*      */   public void printPowerSpectrum(String filename)
/*      */   {
/* 1460 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1462 */     FileOutput fout = new FileOutput(filename);
/* 1463 */     fout.println("Power Spectrum Density Estimate Output File from FourierTransform");
/* 1464 */     fout.dateAndTimeln(filename);
/* 1465 */     String title = "Window: " + this.windowNames[this.windowOption];
/* 1466 */     if (this.windowOption == 6) title = title + ", alpha = " + this.kaiserAlpha;
/* 1467 */     if (this.windowOption == 7) title = title + ", alpha = " + this.gaussianAlpha;
/* 1468 */     fout.println(title);
/* 1469 */     fout.printtab("Number of segments = ");
/* 1470 */     fout.println(this.segmentNumber);
/* 1471 */     fout.printtab("Segment length = ");
/* 1472 */     fout.println(this.segmentLength);
/* 1473 */     if (this.segmentNumber > 1) {
/* 1474 */       if (this.overlap) {
/* 1475 */         fout.printtab("Segments overlap by 50%");
/*      */       }
/*      */       else {
/* 1478 */         fout.printtab("Segments do not overlap");
/*      */       }
/*      */     }
/*      */     
/* 1482 */     fout.println();
/* 1483 */     printWarnings(fout);
/*      */     
/* 1485 */     fout.printtab("Frequency");
/* 1486 */     fout.println("Mean Square");
/* 1487 */     fout.printtab("(cycles per");
/* 1488 */     fout.println("Amplitude");
/* 1489 */     if (this.deltaTset) {
/* 1490 */       fout.printtab("unit time)");
/*      */     }
/*      */     else {
/* 1493 */       fout.printtab("gridpoint)");
/*      */     }
/* 1495 */     fout.println(" ");
/* 1496 */     int n = this.powerSpectrumEstimate[0].length;
/* 1497 */     for (int i = 0; i < n; i++) {
/* 1498 */       fout.printtab(Fmath.truncate(this.powerSpectrumEstimate[0][i], 4));
/* 1499 */       fout.println(Fmath.truncate(this.powerSpectrumEstimate[1][i], 4));
/*      */     }
/* 1501 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(int lowPoint)
/*      */   {
/* 1507 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1508 */     plotPowerSpectrum(lowPoint, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(int lowPoint, String graphTitle)
/*      */   {
/* 1514 */     plotPowerSpectrum(lowPoint, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(int lowPoint, int highPoint)
/*      */   {
/* 1520 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1521 */     plotPowerSpectrum(lowPoint, highPoint, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(int lowPoint, int highPoint, String graphTitle)
/*      */   {
/* 1527 */     if (!this.powSpecDone) {
/* 1528 */       System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
/*      */     }
/*      */     else {
/* 1531 */       int n = this.powerSpectrumEstimate[0].length - 1;
/* 1532 */       if ((lowPoint < 0) || (lowPoint >= n)) lowPoint = 0;
/* 1533 */       if ((highPoint < 0) || (highPoint > n)) highPoint = n;
/* 1534 */       plotPowerSpectrumLinear(lowPoint, highPoint, graphTitle);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(double lowFreq)
/*      */   {
/* 1541 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1542 */     plotPowerSpectrum(lowFreq, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotPowerSpectrum(double lowFreq, String graphTitle)
/*      */   {
/* 1549 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1551 */     double highFreq = this.powerSpectrumEstimate[1][(this.powerSpectrumEstimate[0].length - 1)];
/* 1552 */     plotPowerSpectrum(lowFreq, highFreq, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotPowerSpectrum(double lowFreq, double highFreq)
/*      */   {
/* 1559 */     if (!this.powSpecDone) {
/* 1560 */       System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
/*      */     }
/*      */     else {
/* 1563 */       String graphTitle = "Estimation of Power Spectrum Density";
/* 1564 */       plotPowerSpectrum(lowFreq, highFreq, graphTitle);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerSpectrum(double lowFreq, double highFreq, String graphTitle)
/*      */   {
/* 1571 */     if (!this.powSpecDone) {
/* 1572 */       System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
/*      */     }
/*      */     else {
/* 1575 */       int low = 0;
/* 1576 */       int high = 0;
/* 1577 */       if (!this.deltaTset) {
/* 1578 */         System.out.println("plotPowerSpectrum - deltaT has not been set");
/* 1579 */         System.out.println("full spectrum plotted");
/*      */       }
/*      */       else {
/* 1582 */         int ii = 0;
/* 1583 */         int n = this.powerSpectrumEstimate[0].length - 1;
/* 1584 */         boolean test = true;
/* 1585 */         if (lowFreq == -1.0D) {
/* 1586 */           low = 1;
/*      */         }
/*      */         else {
/* 1589 */           while (test) {
/* 1590 */             if (this.powerSpectrumEstimate[0][ii] > lowFreq) {
/* 1591 */               low = ii - 1;
/* 1592 */               if (low < 0) low = 0;
/* 1593 */               test = false;
/*      */             }
/*      */             else {
/* 1596 */               ii++;
/* 1597 */               if (ii >= n) {
/* 1598 */                 low = 0;
/* 1599 */                 System.out.println("plotPowerSpectrum - lowFreq out of range -  reset to zero");
/* 1600 */                 test = false;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1605 */         test = true;
/* 1606 */         ii = 0;
/* 1607 */         while (test) {
/* 1608 */           if (this.powerSpectrumEstimate[0][ii] > highFreq) {
/* 1609 */             high = ii - 1;
/* 1610 */             if (high < 0) {
/* 1611 */               System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
/* 1612 */               high = n;
/*      */             }
/* 1614 */             test = false;
/*      */           }
/*      */           else {
/* 1617 */             ii++;
/* 1618 */             if (ii >= n) {
/* 1619 */               high = n;
/* 1620 */               System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
/* 1621 */               test = false;
/*      */             }
/*      */           }
/*      */         }
/* 1625 */         plotPowerSpectrumLinear(low, high, graphTitle);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotPowerSpectrum()
/*      */   {
/* 1634 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1636 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1637 */     plotPowerSpectrumLinear(0, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */   public void plotPowerSpectrum(String graphTitle)
/*      */   {
/* 1642 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1644 */     plotPowerSpectrumLinear(0, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   private void plotPowerSpectrumLinear(int low, int high, String graphTitle)
/*      */   {
/* 1650 */     int nData = this.powerSpectrumEstimate[0].length;
/* 1651 */     int nNew = high - low + 1;
/* 1652 */     double[][] spectrum = new double[2][nNew];
/* 1653 */     for (int i = 0; i < nNew; i++) {
/* 1654 */       spectrum[0][i] = this.powerSpectrumEstimate[0][(i + low)];
/* 1655 */       spectrum[1][i] = this.powerSpectrumEstimate[1][(i + low)];
/*      */     }
/* 1657 */     String yLegend = "Mean Square Amplitude";
/*      */     
/* 1659 */     plotPowerDisplay(spectrum, low, high, graphTitle, yLegend);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(int lowPoint)
/*      */   {
/* 1665 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1666 */     plotPowerLog(lowPoint, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(int lowPoint, String graphTitle)
/*      */   {
/* 1672 */     plotPowerLog(lowPoint, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(int lowPoint, int highPoint)
/*      */   {
/* 1678 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1679 */     plotPowerLog(lowPoint, highPoint, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(int lowPoint, int highPoint, String graphTitle)
/*      */   {
/* 1685 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1687 */     int n = this.powerSpectrumEstimate[0].length - 1;
/* 1688 */     if ((lowPoint < 0) || (lowPoint >= n)) lowPoint = 0;
/* 1689 */     if ((highPoint < 0) || (highPoint > n)) highPoint = n;
/* 1690 */     plotPowerSpectrumLog(lowPoint, highPoint, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(double lowFreq)
/*      */   {
/* 1696 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1697 */     plotPowerLog(lowFreq, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotPowerLog(double lowFreq, String graphTitle)
/*      */   {
/* 1704 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1706 */     double highFreq = this.powerSpectrumEstimate[1][(this.powerSpectrumEstimate[0].length - 1)];
/* 1707 */     plotPowerLog(lowFreq, highFreq, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(double lowFreq, double highFreq)
/*      */   {
/* 1713 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1715 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1716 */     plotPowerLog(lowFreq, highFreq, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog(double lowFreq, double highFreq, String graphTitle)
/*      */   {
/* 1722 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1724 */     int low = 0;
/* 1725 */     int high = 0;
/* 1726 */     if (!this.deltaTset) {
/* 1727 */       System.out.println("plotPowerLog - deltaT has not been set");
/* 1728 */       System.out.println("full spectrum plotted");
/*      */     }
/*      */     else {
/* 1731 */       int ii = 0;
/* 1732 */       int n = this.powerSpectrumEstimate[0].length - 1;
/* 1733 */       boolean test = true;
/* 1734 */       if (lowFreq == -1.0D) {
/* 1735 */         low = 1;
/*      */       }
/*      */       else {
/* 1738 */         while (test) {
/* 1739 */           if (this.powerSpectrumEstimate[0][ii] > lowFreq) {
/* 1740 */             low = ii - 1;
/* 1741 */             if (low < 0) low = 0;
/* 1742 */             test = false;
/*      */           }
/*      */           else {
/* 1745 */             ii++;
/* 1746 */             if (ii >= n) {
/* 1747 */               low = 0;
/* 1748 */               System.out.println("plotPowerLog - lowFreq out of range -  reset to zero");
/* 1749 */               test = false;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 1754 */       test = true;
/* 1755 */       ii = 0;
/* 1756 */       while (test) {
/* 1757 */         if (this.powerSpectrumEstimate[0][ii] > highFreq) {
/* 1758 */           high = ii - 1;
/* 1759 */           if (high < 0) {
/* 1760 */             System.out.println("plotPowerLog - highFreq out of range -  reset to highest value");
/* 1761 */             high = n;
/*      */           }
/* 1763 */           test = false;
/*      */         }
/*      */         else {
/* 1766 */           ii++;
/* 1767 */           if (ii >= n) {
/* 1768 */             high = n;
/* 1769 */             System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
/* 1770 */             test = false;
/*      */           }
/*      */         }
/*      */       }
/* 1774 */       plotPowerSpectrumLog(low, high, graphTitle);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLog()
/*      */   {
/* 1781 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1783 */     String graphTitle = "Estimation of Power Spectrum Density";
/* 1784 */     plotPowerSpectrumLog(0, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */   public void plotPowerLog(String graphTitle)
/*      */   {
/* 1789 */     if (!this.powSpecDone) { powerSpectrum();
/*      */     }
/* 1791 */     plotPowerSpectrumLog(0, this.powerSpectrumEstimate[0].length - 1, graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */   private void plotPowerSpectrumLog(int low, int high, String graphTitle)
/*      */   {
/* 1797 */     int nData = this.powerSpectrumEstimate[0].length;
/* 1798 */     int nNew = high - low + 1;
/* 1799 */     double[][] spectrum = new double[2][nNew];
/* 1800 */     for (int i = 0; i < nNew; i++) {
/* 1801 */       spectrum[0][i] = this.powerSpectrumEstimate[0][(i + low)];
/* 1802 */       spectrum[1][i] = this.powerSpectrumEstimate[1][(i + low)];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1807 */     boolean test = true;
/* 1808 */     int ii = 0;
/* 1809 */     double minimum = 0.0D;
/* 1810 */     while (test) {
/* 1811 */       if (spectrum[1][ii] > 0.0D) {
/* 1812 */         minimum = spectrum[1][ii];
/* 1813 */         test = false;
/*      */       }
/*      */       else {
/* 1816 */         ii++;
/* 1817 */         if (ii >= nNew) {
/* 1818 */           test = false;
/* 1819 */           System.out.println("plotPowerSpectrumLog:  no non-zero amplitudes");
/* 1820 */           System.exit(0);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1826 */     for (int i = ii + 1; i < nNew; i++) { if (spectrum[1][i] < minimum) { minimum = spectrum[1][i];
/*      */       }
/*      */     }
/* 1829 */     for (int i = 0; i < nNew; i++) { if (spectrum[1][i] <= 0.0D) { spectrum[1][i] = minimum;
/*      */       }
/*      */     }
/* 1832 */     for (int i = 0; i < nNew; i++) { spectrum[1][i] = Fmath.log10(spectrum[1][i]);
/*      */     }
/*      */     
/* 1835 */     String yLegend = "Log10(Mean Square Amplitude)";
/* 1836 */     plotPowerDisplay(spectrum, low, high, graphTitle, yLegend);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void plotPowerDisplay(double[][] spectrum, int low, int high, String graphTitle, String yLegend)
/*      */   {
/* 1844 */     PlotGraph pg = new PlotGraph(spectrum);
/* 1845 */     graphTitle = graphTitle + "  [plot between points " + low + " and " + high + "]";
/* 1846 */     pg.setGraphTitle(graphTitle);
/* 1847 */     String graphTitle2 = "Window: " + this.windowNames[this.windowOption];
/* 1848 */     if (this.windowOption == 6) graphTitle2 = graphTitle2 + " - alpha = " + this.kaiserAlpha;
/* 1849 */     if (this.windowOption == 7) graphTitle2 = graphTitle2 + " - alpha = " + this.gaussianAlpha;
/* 1850 */     graphTitle2 = graphTitle2 + ", " + this.segmentNumber + " segment/s of length " + this.segmentLength;
/* 1851 */     if (this.segmentNumber > 1) {
/* 1852 */       if (this.overlap) {
/* 1853 */         graphTitle2 = graphTitle2 + ", segments overlap by 50%";
/*      */       }
/*      */       else {
/* 1856 */         graphTitle2 = graphTitle2 + ", segments do not overlap";
/*      */       }
/*      */     }
/*      */     
/* 1860 */     pg.setGraphTitle2(graphTitle2);
/* 1861 */     pg.setXaxisLegend("Frequency");
/* 1862 */     if (this.deltaTset) {
/* 1863 */       pg.setXaxisUnitsName("cycles per unit time");
/*      */     }
/*      */     else {
/* 1866 */       pg.setXaxisUnitsName("cycles per grid point");
/*      */     }
/* 1868 */     pg.setYaxisLegend(yLegend);
/*      */     
/* 1870 */     switch (this.plotLineOption) {
/* 1871 */     case 0:  pg.setLine(3);
/* 1872 */       break;
/* 1873 */     case 1:  pg.setLine(1);
/* 1874 */       break;
/* 1875 */     case 2:  pg.setLine(2);
/* 1876 */       break;
/* 1877 */     default:  pg.setLine(3);
/*      */     }
/*      */     
/* 1880 */     switch (this.plotPointOption) {
/* 1881 */     case 0:  pg.setPoint(0);
/* 1882 */       break;
/* 1883 */     case 1:  pg.setPoint(4);
/* 1884 */       break;
/* 1885 */     default:  pg.setPoint(0);
/*      */     }
/*      */     
/* 1888 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotLineOption(int lineOpt)
/*      */   {
/* 1897 */     this.plotLineOption = lineOpt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPlotLineOption()
/*      */   {
/* 1905 */     return this.plotLineOption;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPlotPointOption(int pointOpt)
/*      */   {
/* 1912 */     this.plotPointOption = pointOpt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getPlotPointOption()
/*      */   {
/* 1919 */     return this.plotPointOption;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[][] correlate(double[] data)
/*      */   {
/* 1926 */     int nLen = data.length;
/* 1927 */     if (!this.fftDataSet) throw new IllegalArgumentException("No data has been previously entered");
/* 1928 */     if (nLen != this.originalDataLength) throw new IllegalArgumentException("The two data sets to be correlated are of different length");
/* 1929 */     if (!checkPowerOfTwo(nLen)) { throw new IllegalArgumentException("The length of the correlation data sets is not equal to an integer power of two");
/*      */     }
/* 1931 */     this.complexCorr = Complex.oneDarray(nLen);
/* 1932 */     for (int i = 0; i < nLen; i++) {
/* 1933 */       this.complexCorr[i].setReal(data[i]);
/* 1934 */       this.complexCorr[i].setImag(0.0D);
/*      */     }
/*      */     
/* 1937 */     this.fftCorr = new double[2 * nLen];
/* 1938 */     int j = -1;
/* 1939 */     for (int i = 0; i < nLen; i++) {
/* 1940 */       this.fftCorr[(++j)] = data[i];
/* 1941 */       this.fftCorr[(++j)] = 0.0D;
/*      */     }
/*      */     
/* 1944 */     return correlation(nLen);
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] correlate(double[] data1, double[] data2)
/*      */   {
/* 1950 */     int nLen = data1.length;
/* 1951 */     int nLen2 = data2.length;
/* 1952 */     if (nLen != nLen2) throw new IllegalArgumentException("The two data sets to be correlated are of different length");
/* 1953 */     if (!checkPowerOfTwo(nLen)) { throw new IllegalArgumentException("The length of the correlation data sets is not equal to an integer power of two");
/*      */     }
/* 1955 */     this.fftDataLength = nLen;
/* 1956 */     this.complexData = Complex.oneDarray(this.fftDataLength);
/* 1957 */     for (int i = 0; i < this.fftDataLength; i++) {
/* 1958 */       this.complexData[i].setReal(data1[i]);
/* 1959 */       this.complexData[i].setImag(0.0D);
/*      */     }
/*      */     
/* 1962 */     this.fftData = new double[2 * this.fftDataLength];
/* 1963 */     int j = 0;
/* 1964 */     for (int i = 0; i < this.fftDataLength; i++) {
/* 1965 */       this.fftData[j] = data1[i];
/* 1966 */       j++;
/* 1967 */       this.fftData[j] = 0.0D;
/* 1968 */       j++;
/*      */     }
/* 1970 */     this.fftDataSet = true;
/*      */     
/* 1972 */     this.fftDataWindow = new double[2 * this.fftDataLength];
/* 1973 */     this.weights = new double[this.fftDataLength];
/* 1974 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/*      */     
/* 1976 */     this.transformedDataFft = new double[2 * this.fftDataLength];
/* 1977 */     this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
/*      */     
/* 1979 */     this.complexCorr = Complex.oneDarray(nLen);
/* 1980 */     for (int i = 0; i < nLen; i++) {
/* 1981 */       this.complexCorr[i].setReal(data2[i]);
/* 1982 */       this.complexCorr[i].setImag(0.0D);
/*      */     }
/*      */     
/* 1985 */     this.fftCorr = new double[2 * nLen];
/* 1986 */     j = -1;
/* 1987 */     for (int i = 0; i < nLen; i++) {
/* 1988 */       this.fftCorr[(++j)] = data2[i];
/* 1989 */       this.fftCorr[(++j)] = 0.0D;
/*      */     }
/*      */     
/* 1992 */     return correlation(nLen);
/*      */   }
/*      */   
/*      */ 
/*      */   private double[][] correlation(int nLen)
/*      */   {
/* 1998 */     this.fftDataWindow = new double[2 * nLen];
/* 1999 */     this.fftCorrWindow = new double[2 * nLen];
/* 2000 */     this.weights = new double[nLen];
/*      */     
/* 2002 */     this.sumOfSquaredWeights = windowData(this.fftData, this.fftDataWindow, this.weights);
/* 2003 */     windowData(this.fftCorr, this.fftCorrWindow, this.weights);
/*      */     
/*      */ 
/* 2006 */     int isign = 1;
/* 2007 */     double[] hold1 = new double[2 * nLen];
/* 2008 */     for (int i = 0; i < nLen * 2; i++) hold1[i] = this.fftDataWindow[i];
/* 2009 */     basicFft(hold1, nLen, isign);
/*      */     
/*      */ 
/* 2012 */     isign = 1;
/* 2013 */     double[] hold2 = new double[2 * nLen];
/* 2014 */     for (int i = 0; i < nLen * 2; i++) hold2[i] = this.fftCorrWindow[i];
/* 2015 */     basicFft(hold2, nLen, isign);
/*      */     
/*      */ 
/* 2018 */     double[] hold3 = new double[2 * nLen];
/* 2019 */     int j = 0;
/* 2020 */     for (int i = 0; i < nLen; i++) {
/* 2021 */       hold3[j] = ((hold1[j] * hold2[j] + hold1[(j + 1)] * hold2[(j + 1)]) / nLen);
/* 2022 */       hold3[(j + 1)] = ((-hold1[j] * hold2[(j + 1)] + hold1[(j + 1)] * hold2[j]) / nLen);
/* 2023 */       j += 2;
/*      */     }
/*      */     
/*      */ 
/* 2027 */     isign = -1;
/* 2028 */     basicFft(hold3, nLen, isign);
/*      */     
/*      */ 
/* 2031 */     for (int i = 0; i < 2 * nLen; i++) this.transformedDataFft[i] = hold3[i];
/* 2032 */     this.correlationArray = new double[2][nLen];
/* 2033 */     j = 0;
/* 2034 */     int k = nLen;
/* 2035 */     for (int i = nLen / 2 + 1; i < nLen; i++) {
/* 2036 */       this.correlationArray[1][j] = (hold3[k] / nLen);
/* 2037 */       j++;
/* 2038 */       k += 2;
/*      */     }
/* 2040 */     k = 0;
/* 2041 */     for (int i = 0; i < nLen / 2; i++) {
/* 2042 */       this.correlationArray[1][j] = (hold3[k] / nLen);
/* 2043 */       j++;
/* 2044 */       k += 2;
/*      */     }
/*      */     
/*      */ 
/* 2048 */     this.correlationArray[0][0] = (-(nLen / 2) * this.deltaT);
/* 2049 */     for (int i = 1; i < nLen; i++) {
/* 2050 */       this.correlationArray[0][i] = (this.correlationArray[0][(i - 1)] + this.deltaT);
/*      */     }
/*      */     
/* 2053 */     this.correlateDone = true;
/* 2054 */     return this.correlationArray;
/*      */   }
/*      */   
/*      */   public double[][] getCorrelation()
/*      */   {
/* 2059 */     if (!this.correlateDone) {
/* 2060 */       System.out.println("getCorrelation - correlation has not been called - no correlation returned");
/*      */     }
/* 2062 */     return this.correlationArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public void printCorrelation()
/*      */   {
/* 2068 */     String filename = "Correlation.txt";
/* 2069 */     printCorrelation(filename);
/*      */   }
/*      */   
/*      */   public void printCorrelation(String filename)
/*      */   {
/* 2074 */     if (!this.correlateDone) {
/* 2075 */       System.out.println("printCorrelation - correlate has not been called - no file printed");
/*      */     }
/*      */     else {
/* 2078 */       FileOutput fout = new FileOutput(filename);
/* 2079 */       fout.println("Correlation Output File from FourierTransform");
/* 2080 */       fout.dateAndTimeln(filename);
/* 2081 */       String title = "Window: " + this.windowNames[this.windowOption];
/* 2082 */       if (this.windowOption == 6) title = title + ", alpha = " + this.kaiserAlpha;
/* 2083 */       if (this.windowOption == 7) title = title + ", alpha = " + this.gaussianAlpha;
/* 2084 */       fout.println(title);
/* 2085 */       fout.printtab("Data length = ");
/* 2086 */       fout.println(this.fftDataLength);
/* 2087 */       fout.println();
/*      */       
/* 2089 */       fout.printtab("Time lag");
/* 2090 */       fout.println("Correlation");
/* 2091 */       if (this.deltaTset) {
/* 2092 */         fout.printtab("/unit time");
/*      */       }
/*      */       else {
/* 2095 */         fout.printtab("/grid interval)");
/*      */       }
/* 2097 */       fout.println("Coefficient");
/*      */       
/* 2099 */       int n = this.correlationArray[0].length;
/* 2100 */       for (int i = 0; i < n; i++) {
/* 2101 */         fout.printtab(Fmath.truncate(this.correlationArray[0][i], 4));
/* 2102 */         fout.println(Fmath.truncate(this.correlationArray[1][i], 4));
/*      */       }
/* 2104 */       fout.close();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotCorrelation()
/*      */   {
/* 2112 */     if (!this.correlateDone) {
/* 2113 */       System.out.println("plotCorrelation - correlation has not been called - no plot displayed");
/*      */     }
/*      */     else {
/* 2116 */       String graphTitle = "Correlation Plot";
/* 2117 */       plotCorrelation(graphTitle);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotCorrelation(String graphTitle)
/*      */   {
/* 2124 */     if (!this.correlateDone) {
/* 2125 */       System.out.println("plotCorrelation - correlate has not been called - no plot displayed");
/*      */     }
/*      */     else
/*      */     {
/* 2129 */       PlotGraph pg = new PlotGraph(this.correlationArray);
/* 2130 */       pg.setGraphTitle(graphTitle);
/* 2131 */       String graphTitle2 = "Window: " + this.windowNames[this.windowOption];
/* 2132 */       if (this.windowOption == 6) graphTitle2 = graphTitle2 + " - alpha = " + this.kaiserAlpha;
/* 2133 */       if (this.windowOption == 7) { graphTitle2 = graphTitle2 + " - alpha = " + this.gaussianAlpha;
/*      */       }
/* 2135 */       pg.setGraphTitle2(graphTitle2);
/* 2136 */       pg.setXaxisLegend("Correlation Lag");
/* 2137 */       if (this.deltaTset) {
/* 2138 */         pg.setXaxisUnitsName("unit time");
/*      */       }
/*      */       else {
/* 2141 */         pg.setXaxisUnitsName("grid interval");
/*      */       }
/* 2143 */       pg.setYaxisLegend("Correlation coefficient");
/*      */       
/* 2145 */       switch (this.plotLineOption) {
/* 2146 */       case 0:  pg.setLine(3);
/* 2147 */         break;
/* 2148 */       case 1:  pg.setLine(1);
/* 2149 */         break;
/* 2150 */       case 2:  pg.setLine(2);
/* 2151 */         break;
/* 2152 */       default:  pg.setLine(3);
/*      */       }
/*      */       
/* 2155 */       switch (this.plotPointOption) {
/* 2156 */       case 0:  pg.setPoint(0);
/* 2157 */         break;
/* 2158 */       case 1:  pg.setPoint(4);
/* 2159 */         break;
/* 2160 */       default:  pg.setPoint(0);
/*      */       }
/*      */       
/* 2163 */       pg.plot();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] shortTime(double windowTime)
/*      */   {
/* 2172 */     int windowLength = (int)Math.round(windowTime / this.deltaT);
/* 2173 */     if (!checkPowerOfTwo(windowLength)) {
/* 2174 */       int low = lastPowerOfTwo(windowLength);
/* 2175 */       int high = nextPowerOfTwo(windowLength);
/*      */       
/* 2177 */       if (windowLength - low <= high - windowLength) {
/* 2178 */         windowLength = low;
/* 2179 */         if (low == 0) windowLength = high;
/*      */       }
/*      */       else {
/* 2182 */         windowLength = high;
/*      */       }
/* 2184 */       System.out.println("Method - shortTime");
/* 2185 */       System.out.println("Window length, provided as time, " + windowTime + ", did not convert to an integer power of two data points");
/* 2186 */       System.out.println("A value of " + (windowLength - 1) * this.deltaT + " was substituted");
/*      */     }
/*      */     
/* 2189 */     return shortTime(windowLength);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] shortTime(int windowLength)
/*      */   {
/* 2198 */     if (!checkPowerOfTwo(windowLength)) throw new IllegalArgumentException("Moving window data length ," + windowLength + ", is not an integer power of two");
/* 2199 */     if (!this.fftDataSet) throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
/* 2200 */     if (windowLength > this.originalDataLength) { throw new IllegalArgumentException("The window length, " + windowLength + ", is greater than the data length, " + this.originalDataLength + ".");
/*      */     }
/*      */     
/* 2203 */     if (this.windowOption == 0) { setGaussian();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2208 */     this.numShortTimes = (this.originalDataLength - windowLength + 1);
/* 2209 */     this.numShortFreq = (windowLength / 2);
/* 2210 */     this.timeFrequency = new double[this.numShortFreq + 1][this.numShortTimes + 1];
/* 2211 */     this.timeFrequency[0][0] = 0.0D;
/* 2212 */     this.timeFrequency[0][1] = ((windowLength - 1) * this.deltaT / 2.0D);
/* 2213 */     for (int i = 2; i <= this.numShortTimes; i++) {
/* 2214 */       this.timeFrequency[0][i] = (this.timeFrequency[0][(i - 1)] + this.deltaT);
/*      */     }
/* 2216 */     for (int i = 0; i < this.numShortFreq; i++) {
/* 2217 */       this.timeFrequency[(i + 1)][0] = (i / (windowLength * this.deltaT));
/*      */     }
/*      */     
/*      */ 
/* 2221 */     this.segmentLength = windowLength;
/* 2222 */     int windowStartIndex = 0;
/* 2223 */     double[] data = new double[2 * windowLength];
/* 2224 */     double[] winPSD = new double[this.numShortFreq];
/* 2225 */     int isign = 1;
/*      */     
/*      */ 
/* 2228 */     for (int i = 1; i <= this.numShortTimes; i++)
/*      */     {
/*      */ 
/* 2231 */       for (int j = 0; j < 2 * windowLength; j++) { data[j] = this.fftData[(windowStartIndex + j)];
/*      */       }
/*      */       
/* 2234 */       if (i == 1) {
/* 2235 */         this.sumOfSquaredWeights = windowData(data, data, this.weights);
/*      */       }
/*      */       else {
/* 2238 */         int k = 0;
/* 2239 */         for (int j = 0; j < this.segmentLength; j++) {
/* 2240 */           data[k] *= this.weights[j];
/* 2241 */           data[(++k)] = (data[k] * this.weights[j]);
/* 2242 */           k++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 2247 */       basicFft(data, windowLength, isign);
/*      */       
/*      */ 
/* 2250 */       winPSD[0] = (Fmath.square(data[0]) + Fmath.square(data[1]));
/* 2251 */       for (int j = 1; j < this.numShortFreq; j++) {
/* 2252 */         winPSD[j] = (Fmath.square(data[(2 * j)]) + Fmath.square(data[(2 * j + 1)]) + Fmath.square(data[(2 * windowLength - 2 * j)]) + Fmath.square(data[(2 * windowLength - 2 * j + 1)]));
/*      */       }
/*      */       
/*      */ 
/* 2256 */       for (int j = 0; j < this.numShortFreq; j++) {
/* 2257 */         this.timeFrequency[(j + 1)][i] = (2.0D * winPSD[j] / (windowLength * this.sumOfSquaredWeights));
/*      */       }
/*      */       
/*      */ 
/* 2261 */       windowStartIndex += 2;
/*      */     }
/*      */     
/* 2264 */     this.shortTimeDone = true;
/* 2265 */     return this.timeFrequency;
/*      */   }
/*      */   
/*      */   public double[][] getTimeFrequencyMatrix()
/*      */   {
/* 2270 */     if (!this.shortTimeDone) throw new IllegalArgumentException("No short time Fourier transform has been performed");
/* 2271 */     return this.timeFrequency;
/*      */   }
/*      */   
/*      */   public int getShortTimeNumberOfTimes()
/*      */   {
/* 2276 */     if (!this.shortTimeDone) throw new IllegalArgumentException("No short time Fourier transform has been performed");
/* 2277 */     return this.numShortTimes;
/*      */   }
/*      */   
/*      */   public int getShortTimeNumberOfFrequencies()
/*      */   {
/* 2282 */     if (!this.shortTimeDone) throw new IllegalArgumentException("No short time Fourier transform has been performed");
/* 2283 */     return this.numShortFreq;
/*      */   }
/*      */   
/*      */   public int getShortTimeWindowLength()
/*      */   {
/* 2288 */     if (!this.shortTimeDone) throw new IllegalArgumentException("No short time Fourier transform has been performed");
/* 2289 */     return this.segmentLength;
/*      */   }
/*      */   
/*      */ 
/*      */   public void printShortTime()
/*      */   {
/* 2295 */     String filename = "ShortTime.txt";
/* 2296 */     printShortTime(filename);
/*      */   }
/*      */   
/*      */   public void printShortTime(String filename)
/*      */   {
/* 2301 */     if (!this.shortTimeDone) {
/* 2302 */       System.out.println("printShortTime- shortTime has not been called - no file printed");
/*      */     }
/*      */     else {
/* 2305 */       FileOutput fout = new FileOutput(filename);
/* 2306 */       fout.println("Short Time Fourier Transform Output File from FourierTransform");
/* 2307 */       fout.dateAndTimeln(filename);
/* 2308 */       String title = "Window: " + this.windowNames[this.windowOption];
/* 2309 */       if (this.windowOption == 6) title = title + ", alpha = " + this.kaiserAlpha;
/* 2310 */       if (this.windowOption == 7) title = title + ", alpha = " + this.gaussianAlpha;
/* 2311 */       fout.println(title);
/* 2312 */       fout.printtab("Data length = ");
/* 2313 */       fout.println(this.originalDataLength);
/* 2314 */       fout.printtab("Delta T = ");
/* 2315 */       fout.println(this.deltaT);
/* 2316 */       fout.printtab("Window length (points) = ");
/* 2317 */       fout.println(this.segmentLength);
/* 2318 */       fout.printtab("Window length (time units) = ");
/* 2319 */       fout.println((this.segmentLength - 1) * this.deltaT);
/* 2320 */       fout.printtab("Number of frequency points = ");
/* 2321 */       fout.println(this.numShortFreq);
/* 2322 */       fout.printtab("Number of time points = ");
/* 2323 */       fout.println(this.numShortTimes);
/*      */       
/*      */ 
/* 2326 */       boolean checkAve = false;
/* 2327 */       int newTp = this.numShortTimes;
/* 2328 */       int maxN = 100;
/* 2329 */       int nAve = this.numShortTimes / maxN;
/* 2330 */       int nLast = this.numShortTimes % maxN;
/* 2331 */       if (this.numShortTimes > 127) {
/* 2332 */         checkAve = true;
/* 2333 */         if (nLast > 0) {
/* 2334 */           nAve++;
/* 2335 */           newTp = maxN;
/* 2336 */           nLast = this.numShortTimes - nAve * (newTp - 1);
/*      */         }
/*      */         else {
/* 2339 */           newTp = maxN;
/* 2340 */           nLast = nAve;
/*      */         }
/* 2342 */         if (nLast != nAve) {
/* 2343 */           fout.println("In the output below, each of the first " + (newTp - 2) + " magnitude points, along the time axis, is the average of " + nAve + " calculated points");
/* 2344 */           fout.println("The last point is the average of " + nLast + " calculated points");
/*      */         }
/*      */         else {
/* 2347 */           fout.println("In the output below, each magnitude point is the average of " + nAve + " calculated points");
/*      */         }
/* 2349 */         fout.println("The data, without averaging, may be accessed using the method getTimeFrequencyMatrix()");
/*      */       }
/* 2351 */       fout.println();
/*      */       
/* 2353 */       fout.println("first row = times");
/* 2354 */       fout.println("first column = frequencies");
/* 2355 */       fout.println("all other cells = mean square amplitudes at the corresponding time and frequency");
/* 2356 */       if (checkAve) {
/* 2357 */         double sum = 0.0D;
/* 2358 */         int start = 1;
/* 2359 */         int workingAve = nAve;
/* 2360 */         for (int i = 0; i <= this.numShortFreq; i++) {
/* 2361 */           fout.printtab(Fmath.truncate(this.timeFrequency[i][0], 4));
/* 2362 */           start = 1;
/* 2363 */           for (int j = 1; j <= newTp; j++) {
/* 2364 */             workingAve = nAve;
/* 2365 */             if (j == newTp) workingAve = nLast;
/* 2366 */             sum = 0.0D;
/* 2367 */             for (int k = start; k <= start + workingAve - 1; k++) {
/* 2368 */               sum += this.timeFrequency[i][k];
/*      */             }
/* 2370 */             sum /= workingAve;
/* 2371 */             fout.printtab(Fmath.truncate(sum, 4));
/* 2372 */             start += workingAve;
/*      */           }
/* 2374 */           fout.println();
/*      */         }
/*      */       }
/*      */       else {
/* 2378 */         for (int i = 0; i <= this.numShortFreq; i++) {
/* 2379 */           for (int j = 0; j <= newTp; j++) {
/* 2380 */             fout.printtab(Fmath.truncate(this.timeFrequency[i][j], 4));
/*      */           }
/* 2382 */           fout.println();
/*      */         }
/*      */       }
/* 2385 */       fout.close();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void paint(Graphics g)
/*      */   {
/* 2393 */     graph(g);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotShortTime(String title)
/*      */   {
/* 2399 */     this.shortTitle = title;
/* 2400 */     plotShortTime();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotShortTime()
/*      */   {
/* 2407 */     JFrame window = new JFrame("Michael T Flanagan's plotting program - FourierTransform.plotShortTime");
/*      */     
/*      */ 
/* 2410 */     setSize(800, 600);
/*      */     
/*      */ 
/* 2413 */     window.getContentPane().setBackground(Color.white);
/*      */     
/*      */ 
/* 2416 */     window.setDefaultCloseOperation(3);
/*      */     
/*      */ 
/* 2419 */     window.getContentPane().add("Center", this);
/*      */     
/*      */ 
/* 2422 */     window.pack();
/* 2423 */     window.setResizable(true);
/* 2424 */     window.toFront();
/*      */     
/*      */ 
/* 2427 */     window.setVisible(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void graph(Graphics g)
/*      */   {
/* 2435 */     int xLen = 512;
/* 2436 */     int yLen = 256;
/* 2437 */     int yTop = 100;
/* 2438 */     int xBot = 100;
/* 2439 */     int numBands = 18;
/*      */     
/* 2441 */     Color[] color = new Color[numBands + 1];
/* 2442 */     color[18] = Color.black;
/* 2443 */     color[17] = Color.darkGray;
/* 2444 */     color[16] = Color.gray;
/* 2445 */     color[15] = Color.lightGray;
/* 2446 */     color[14] = Color.red.darker();
/* 2447 */     color[13] = Color.red;
/* 2448 */     color[12] = Color.magenta.darker();
/* 2449 */     color[11] = Color.magenta;
/* 2450 */     color[10] = Color.pink;
/* 2451 */     color[9] = Color.pink.darker();
/* 2452 */     color[8] = Color.orange.darker();
/* 2453 */     color[7] = Color.orange;
/* 2454 */     color[6] = Color.yellow;
/* 2455 */     color[5] = Color.green;
/* 2456 */     color[4] = Color.green.darker();
/* 2457 */     color[3] = Color.cyan;
/* 2458 */     color[2] = Color.cyan.darker();
/* 2459 */     color[1] = Color.blue;
/* 2460 */     color[0] = Color.blue.darker();
/*      */     
/*      */ 
/* 2463 */     int pixelsPerXpoint = 0;
/* 2464 */     int xTp = 0;
/* 2465 */     int xAve = 0;
/* 2466 */     int xLast = 0;
/* 2467 */     boolean xCheck = true;
/* 2468 */     if (this.numShortTimes <= xLen) {
/* 2469 */       pixelsPerXpoint = xLen / this.numShortTimes;
/* 2470 */       xLen = pixelsPerXpoint * this.numShortTimes;
/* 2471 */       xTp = this.numShortTimes;
/*      */     }
/*      */     else {
/* 2474 */       xCheck = false;
/* 2475 */       pixelsPerXpoint = 1;
/* 2476 */       xTp = this.numShortTimes;
/* 2477 */       xAve = this.numShortTimes / xLen;
/* 2478 */       xLast = this.numShortTimes % xLen;
/* 2479 */       if (xLast > 0) {
/* 2480 */         xAve++;
/* 2481 */         xTp = this.numShortTimes / xAve + 1;
/* 2482 */         xLast = this.numShortTimes - xAve * (xTp - 1);
/*      */       }
/*      */       else {
/* 2485 */         xTp = this.numShortTimes / xAve;
/* 2486 */         xLast = xAve;
/*      */       }
/* 2488 */       xLen = xTp;
/*      */     }
/*      */     
/*      */ 
/* 2492 */     int pixelsPerYpoint = 0;
/* 2493 */     int yTp = 0;
/* 2494 */     int yAve = 0;
/* 2495 */     int yLast = 0;
/* 2496 */     boolean yCheck = true;
/*      */     
/* 2498 */     if (this.numShortFreq <= yLen) {
/* 2499 */       pixelsPerYpoint = yLen / this.numShortFreq;
/* 2500 */       yLen = pixelsPerYpoint * this.numShortFreq;
/* 2501 */       yTp = this.numShortFreq;
/*      */     }
/*      */     else {
/* 2504 */       yCheck = false;
/* 2505 */       pixelsPerYpoint = 1;
/* 2506 */       yTp = this.numShortFreq;
/* 2507 */       yAve = this.numShortFreq / yLen;
/* 2508 */       yLast = this.numShortFreq % yLen;
/* 2509 */       if (yLast > 0) {
/* 2510 */         yAve++;
/* 2511 */         yTp = this.numShortFreq / yAve + 1;
/* 2512 */         yLast = this.numShortFreq - yAve * (yTp - 1);
/*      */       }
/*      */       else {
/* 2515 */         yTp = this.numShortFreq / yAve;
/* 2516 */         yLast = yAve;
/*      */       }
/* 2518 */       yLen = yTp;
/*      */     }
/*      */     
/*      */ 
/* 2522 */     int yBot = yTop + yLen;
/* 2523 */     int xTop = xBot + xLen;
/*      */     
/*      */ 
/* 2526 */     double[][] averages = new double[yTp][xTp];
/* 2527 */     int[][] pixels = new int[yTp][xTp];
/* 2528 */     double[] times = new double[xTp];
/* 2529 */     int[] timesPixels = new int[xTp];
/* 2530 */     double[] freqs = new double[yTp];
/* 2531 */     int[] freqPixels = new int[yTp];
/*      */     
/* 2533 */     double[][] hold = new double[this.numShortFreq][xTp];
/*      */     
/*      */ 
/* 2536 */     if (xCheck) {
/* 2537 */       for (int i = 0; i <= this.numShortFreq; i++) {
/* 2538 */         for (int j = 1; j <= this.numShortTimes; j++) {
/* 2539 */           if (i == 0) {
/* 2540 */             times[(j - 1)] = this.timeFrequency[0][j];
/*      */           }
/*      */           else {
/* 2543 */             hold[(i - 1)][(j - 1)] = this.timeFrequency[i][j];
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 2549 */       double sum = 0.0D;
/* 2550 */       int start = 1;
/* 2551 */       int workingAve = xAve;
/* 2552 */       for (int i = 0; i <= this.numShortFreq; i++) {
/* 2553 */         start = 1;
/* 2554 */         for (int j = 1; j <= xTp; j++) {
/* 2555 */           workingAve = xAve;
/* 2556 */           if (j == xTp) workingAve = xLast;
/* 2557 */           sum = 0.0D;
/* 2558 */           for (int k = start; k <= start + workingAve - 1; k++) {
/* 2559 */             sum += this.timeFrequency[i][k];
/*      */           }
/* 2561 */           if (i == 0) {
/* 2562 */             times[(j - 1)] = (sum / workingAve);
/*      */           }
/*      */           else {
/* 2565 */             hold[(i - 1)][(j - 1)] = (sum / workingAve);
/*      */           }
/* 2567 */           start += workingAve;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2573 */     if (yCheck) {
/* 2574 */       for (int i = 0; i < this.numShortFreq; i++) {
/* 2575 */         freqs[i] = this.timeFrequency[(i + 1)][0];
/* 2576 */         for (int j = 0; j < xTp; j++) {
/* 2577 */           averages[i][j] = hold[i][j];
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 2582 */       double sum = 0.0D;
/* 2583 */       double sFreq = 0.0D;
/* 2584 */       int start = 0;
/* 2585 */       int workingAve = yAve;
/* 2586 */       for (int i = 0; i < xTp; i++) {
/* 2587 */         start = 0;
/* 2588 */         for (int j = 0; j < yTp; j++) {
/* 2589 */           workingAve = yAve;
/* 2590 */           if (j == yTp - 1) workingAve = yLast;
/* 2591 */           sum = 0.0D;
/* 2592 */           sFreq = 0.0D;
/* 2593 */           for (int k = start; k <= start + workingAve - 1; k++) {
/* 2594 */             sum += hold[k][i];
/* 2595 */             sFreq += this.timeFrequency[(k + 1)][0];
/*      */           }
/* 2597 */           averages[j][i] = sum;
/* 2598 */           freqs[j] = (sFreq / workingAve);
/* 2599 */           start += workingAve;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2605 */     double max = averages[0][0];
/* 2606 */     double min = max;
/* 2607 */     for (int i = 0; i < yTp; i++) {
/* 2608 */       for (int j = 0; j < xTp; j++) {
/* 2609 */         if (averages[i][j] > max) max = averages[i][j];
/* 2610 */         if (averages[i][j] < min) { min = averages[i][j];
/*      */         }
/*      */       }
/*      */     }
/* 2614 */     double bandZero = 0.0D;
/* 2615 */     if (min > 0.1D * max) bandZero = 0.99D * min;
/* 2616 */     double bandWidth = (1.01D * max - 0.99D * min) / numBands;
/* 2617 */     double[] band = new double[numBands];
/* 2618 */     band[0] = (bandZero + bandWidth);
/* 2619 */     for (int i = 1; i < numBands; i++) {
/* 2620 */       band[i] = (band[(i - 1)] + bandWidth);
/*      */     }
/* 2622 */     boolean test = true;
/* 2623 */     for (int i = 0; i < yTp; i++) {
/* 2624 */       for (int j = 0; j < xTp; j++) {
/* 2625 */         test = true;
/* 2626 */         int k = 0;
/* 2627 */         while (test) {
/* 2628 */           if (averages[i][j] <= band[k]) {
/* 2629 */             pixels[i][j] = k;
/* 2630 */             test = false;
/*      */           }
/*      */           else {
/* 2633 */             k++;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2640 */     int yPixels = 0;
/* 2641 */     int xPixels = 0;
/* 2642 */     int yInner = 0;
/* 2643 */     int xInner = 0;
/* 2644 */     int xx = xBot;
/* 2645 */     int yy = yTop;
/* 2646 */     for (int i = 0; i < yTp; i++) {
/* 2647 */       for (int j = 0; j < xTp; j++) {
/* 2648 */         yInner = 0;
/* 2649 */         for (int k = 0; k < pixelsPerYpoint; k++) {
/* 2650 */           xInner = 0;
/* 2651 */           for (int l = 0; l < pixelsPerXpoint; l++) {
/* 2652 */             g.setColor(color[pixels[i][j]]);
/* 2653 */             xx = xBot + (xPixels + xInner);
/* 2654 */             yy = yBot - (yPixels + yInner);
/* 2655 */             g.drawLine(xx, yy, xx, yy);
/* 2656 */             xInner++;
/*      */           }
/* 2658 */           yInner++;
/*      */         }
/* 2660 */         xPixels += xInner;
/*      */       }
/* 2662 */       yPixels += yInner;
/* 2663 */       xPixels = 0;
/*      */     }
/*      */     
/*      */ 
/* 2667 */     g.setColor(color[numBands]);
/* 2668 */     g.drawLine(xBot, yBot, xBot, yTop);
/* 2669 */     g.drawLine(xTop, yBot, xTop, yTop);
/* 2670 */     g.drawLine(xBot, yBot, xTop, yBot);
/* 2671 */     g.drawLine(xBot, yTop, xTop, yTop);
/*      */     
/*      */ 
/* 2674 */     int yInc = yLen / 4;
/* 2675 */     int yScale = this.numShortFreq / 4;
/* 2676 */     double yUnits = yInc * (freqs[1] - freqs[0]) / (pixelsPerYpoint * yScale);
/* 2677 */     String[] yArray = new String[5];
/* 2678 */     int yArr = 0;
/* 2679 */     yArray[0] = "0  ";
/* 2680 */     for (int i = 1; i < 5; i++) {
/* 2681 */       yArr += yScale;
/* 2682 */       yArray[i] = (yArr + "  ");
/*      */     }
/* 2684 */     xx = xBot;
/* 2685 */     yy = yBot;
/* 2686 */     int yWord = 6 * (yArray[4].length() + 1);
/* 2687 */     for (int i = 0; i < 5; i++) {
/* 2688 */       g.drawLine(xx - 5, yy, xx, yy);
/* 2689 */       g.drawString(yArray[i], xx - yWord, yy + 4);
/* 2690 */       yy -= yInc;
/*      */     }
/*      */     
/* 2693 */     int xInc = xLen / 8;
/* 2694 */     int xScale = this.numShortTimes / 8;
/* 2695 */     double xUnits = xInc * (times[1] - times[0]) / (pixelsPerXpoint * xScale);
/* 2696 */     String[] xArray = new String[9];
/* 2697 */     int xArr = 0;
/* 2698 */     xArray[0] = "0 ";
/* 2699 */     for (int i = 1; i < 9; i++) {
/* 2700 */       xArr += xScale;
/* 2701 */       xArray[i] = (xArr + " ");
/*      */     }
/* 2703 */     xx = xBot;
/* 2704 */     yy = yBot;
/* 2705 */     for (int i = 0; i < 9; i++) {
/* 2706 */       g.drawLine(xx, yy, xx, yy + 5);
/* 2707 */       g.drawString(xArray[i], xx - 4, yy + 20);
/* 2708 */       xx += xInc;
/*      */     }
/*      */     
/*      */ 
/* 2712 */     g.drawString("Short Time Fourier Transfer Time-Frequency Plot", xBot - 80, yTop - 80);
/* 2713 */     g.drawString(this.shortTitle, xBot - 80, yTop - 60);
/*      */     
/* 2715 */     String yAxis = "Frequency / (" + Fmath.truncate(yUnits, 3) + " cycles per time unit)";
/* 2716 */     g.drawString(yAxis, xBot - 60, yTop - 20);
/* 2717 */     String xAxis = "Time / (" + Fmath.truncate(xUnits, 3) + " time units)";
/* 2718 */     g.drawString(xAxis, xBot, yBot + 40);
/* 2719 */     String totalTime = "Total time = " + Fmath.truncate(xLen * (times[1] - times[0]) / pixelsPerXpoint, 3) + " time units";
/* 2720 */     g.drawString(totalTime, xBot, yBot + 80);
/*      */     
/* 2722 */     String totalFreq = "Frequecy range = 0 to " + Fmath.truncate(yLen * (freqs[1] - freqs[0]) / pixelsPerYpoint, 3) + " cycles per time unit";
/* 2723 */     g.drawString(totalFreq, xBot, yBot + 100);
/*      */     
/* 2725 */     g.drawString("Widow length = " + Fmath.truncate((this.segmentLength - 1) * this.deltaT, 3) + " time units", xBot, yBot + 120);
/* 2726 */     String filter = "Window filter = " + this.windowNames[this.windowOption];
/* 2727 */     if (this.windowOption == 6) filter = filter + ", alpha = " + this.kaiserAlpha;
/* 2728 */     if (this.windowOption == 7) filter = filter + ", alpha = " + this.gaussianAlpha;
/* 2729 */     g.drawString(filter, xBot, yBot + 140);
/*      */     
/*      */ 
/* 2732 */     yy = yBot + 100;
/* 2733 */     xx = xTop + 40;
/* 2734 */     double ss = Fmath.truncate(bandZero, 3);
/* 2735 */     for (int i = 0; i < numBands; i++) {
/* 2736 */       double ff = Fmath.truncate(band[i], 3);
/* 2737 */       g.setColor(color[numBands]);
/* 2738 */       g.drawString(ss + " - " + ff, xx + 25, yy);
/* 2739 */       ss = ff;
/* 2740 */       g.setColor(color[i]);
/* 2741 */       for (int j = 0; j < 20; j++) {
/* 2742 */         yy -= 1;
/* 2743 */         g.drawLine(xx, yy, xx + 20, yy);
/*      */       }
/*      */     }
/* 2746 */     g.setColor(Color.black);
/* 2747 */     g.drawString("Mean square", xx + 25, yy - 25);
/* 2748 */     g.drawString("amplitudes ", xx + 25, yy - 10);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int lastPowerOfTwo(int len)
/*      */   {
/* 2757 */     boolean test0 = true;
/* 2758 */     while (test0) {
/* 2759 */       if (checkPowerOfTwo(len)) {
/* 2760 */         test0 = false;
/*      */       }
/*      */       else {
/* 2763 */         len--;
/*      */       }
/*      */     }
/* 2766 */     return len;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int nextPowerOfTwo(int len)
/*      */   {
/* 2772 */     boolean test0 = true;
/* 2773 */     while (test0) {
/* 2774 */       if (checkPowerOfTwo(len)) {
/* 2775 */         test0 = false;
/*      */       }
/*      */       else {
/* 2778 */         len++;
/*      */       }
/*      */     }
/* 2781 */     return len;
/*      */   }
/*      */   
/*      */   public static boolean checkPowerOfTwo(int n)
/*      */   {
/* 2786 */     boolean test = true;
/* 2787 */     int m = n;
/* 2788 */     while ((test) && (m > 1)) {
/* 2789 */       if (m % 2 != 0) {
/* 2790 */         test = false;
/*      */       }
/*      */       else {
/* 2793 */         m /= 2;
/*      */       }
/*      */     }
/* 2796 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int checkIntegerTimesPowerOfTwo(int n)
/*      */   {
/* 2803 */     boolean testOuter1 = true;
/* 2804 */     boolean testInner1 = true;
/* 2805 */     boolean testInner2 = true;
/* 2806 */     boolean testReturn = true;
/*      */     
/* 2808 */     int m = n;
/* 2809 */     int j = 1;
/* 2810 */     int mult = 0;
/*      */     
/* 2812 */     while (testOuter1) {
/* 2813 */       testInner1 = checkPowerOfTwo(m);
/* 2814 */       if (testInner1) {
/* 2815 */         testReturn = true;
/* 2816 */         testOuter1 = false;
/*      */       }
/*      */       else {
/* 2819 */         testInner2 = true;
/* 2820 */         while (testInner2) {
/* 2821 */           m /= ++j;
/* 2822 */           if (m < 1) {
/* 2823 */             testInner2 = false;
/* 2824 */             testInner1 = false;
/* 2825 */             testOuter1 = false;
/* 2826 */             testReturn = false;
/*      */ 
/*      */           }
/* 2829 */           else if (m % 2 == 0) { testInner2 = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2834 */     if (testReturn) mult = j;
/* 2835 */     return mult;
/*      */   }
/*      */   
/*      */   public static long getSerialVersionUID()
/*      */   {
/* 2840 */     return 1L;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/FourierTransform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */