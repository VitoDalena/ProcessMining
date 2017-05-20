/*      */ package flanagan.circuits;
/*      */ 
/*      */ import flanagan.analysis.ErrorProp;
/*      */ import flanagan.analysis.Regression;
/*      */ import flanagan.analysis.RegressionFunction2;
/*      */ import flanagan.analysis.Stat;
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexErrorProp;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.io.PrintStream;
/*      */ import java.text.DateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
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
/*      */ public class ImpedSpecRegression
/*      */   extends Regression
/*      */ {
/*   54 */   private String regressionTitle = null;
/*   55 */   private boolean fileType = false;
/*      */   
/*   57 */   private Complex appliedVoltage = null;
/*   58 */   private boolean appliedVoltageSet = false;
/*   59 */   private Complex appliedVoltageError = null;
/*   60 */   private boolean voltageErrorSet = false;
/*      */   
/*   62 */   private Complex referenceImpedance = null;
/*   63 */   private boolean referenceSet = false;
/*      */   
/*   65 */   private double[] frequencies = null;
/*   66 */   private double[] omegas = null;
/*   67 */   private double[] log10frequencies = null;
/*   68 */   private double[] log10omegas = null;
/*   69 */   private int numberOfFrequencies = 0;
/*   70 */   private boolean frequenciesSet = false;
/*      */   
/*   72 */   private Complex[] voltages = null;
/*   73 */   private Complex[] voltageWeights = null;
/*   74 */   private double[] voltageMagnitudes = null;
/*   75 */   private double[] voltageMagnitudeWeights = null;
/*   76 */   private double[] voltagePhasesRad = null;
/*   77 */   private double[] voltagePhaseWeightsRad = null;
/*   78 */   private double[] voltagePhasesDeg = null;
/*   79 */   private double[] voltagePhaseWeightsDeg = null;
/*   80 */   private double[] realV = null;
/*   81 */   private double[] realVweights = null;
/*   82 */   private double[] imagV = null;
/*   83 */   private double[] imagVweights = null;
/*      */   
/*   85 */   private boolean weightsSet = true;
/*      */   
/*   87 */   private int dataEnteredTypePointer = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   96 */   private String[] dataEnteredType = { "Complex voltage (as real and imaginary parts)", "Complex voltage (as Complex)", "Voltage Magnitude and phase (in radians)", "Voltage Magnitude and phase (in degrees)", "Complex impedance (as real and imaginary parts)", "Complex impedance (as Complex)", "Magnitude and phase (in radians)", "Magnitude and phase (in degrees)" };
/*   97 */   private boolean voltageOrImpedance = true;
/*      */   
/*      */ 
/*  100 */   private Complex[] impedances = null;
/*  101 */   private Complex[] impedanceWeights = null;
/*  102 */   private double[] impedanceMagnitudes = null;
/*  103 */   private double[] impedanceMagnitudeWeights = null;
/*  104 */   private double[] impedancePhasesRad = null;
/*  105 */   private double[] impedancePhaseWeightsRad = null;
/*  106 */   private double[] impedancePhasesDeg = null;
/*  107 */   private double[] impedancePhaseWeightsDeg = null;
/*  108 */   private double[] realZ = null;
/*  109 */   private double[] realZweights = null;
/*  110 */   private double[] imagZ = null;
/*  111 */   private double[] imagZweights = null;
/*  112 */   private boolean impedancesSet = false;
/*      */   
/*      */ 
/*  115 */   private double[] xRegression = null;
/*  116 */   private double[][] yRegression = (double[][])null;
/*  117 */   private double[][] wRegression = (double[][])null;
/*      */   
/*  119 */   private int modelNumber = 0;
/*  120 */   private int numberOfParameters = 0;
/*  121 */   private String[] parameterSymbols = null;
/*  122 */   private boolean modelSet = false;
/*  123 */   private boolean estimatesNeeded = false;
/*      */   
/*  125 */   private boolean supressDefaultConstraints = false;
/*  126 */   private boolean supressAddedConstraints = false;
/*  127 */   private boolean supressAllConstraints = false;
/*      */   
/*  129 */   private ArrayList<Object> constraints = null;
/*  130 */   private int numberOfAddedConstraints = -1;
/*      */   
/*  132 */   private boolean constraintsAdded = false;
/*      */   
/*  134 */   private double[] initialEstimates = null;
/*  135 */   private double[] initialSteps = null;
/*      */   
/*  137 */   private double[] bestEstimates = null;
/*  138 */   private double[] standardDeviations = null;
/*  139 */   private double[] coefficientsOfVariation = null;
/*  140 */   private double[][] correlationCoefficients = (double[][])null;
/*  141 */   private double[] preMinimumGradients = null;
/*  142 */   private double[] postMinimumGradients = null;
/*      */   
/*  144 */   private int degreesOfFreedom = 0;
/*  145 */   private double sumOfSquares = 0.0D;
/*  146 */   private double reducedSumOfSquares = 0.0D;
/*  147 */   private double chiSquare = NaN.0D;
/*  148 */   private double reducedChiSquare = NaN.0D;
/*  149 */   private double[] realZresiduals = null;
/*  150 */   private double[] imagZresiduals = null;
/*      */   
/*  152 */   private double[] calculatedRealZ = null;
/*  153 */   private double[] calculatedImagZ = null;
/*  154 */   private Complex[] calculatedImpedances = null;
/*  155 */   private double[] calculatedImpedanceMagnitudes = null;
/*  156 */   private double[] calculatedImpedancePhasesRad = null;
/*  157 */   private double[] calculatedImpedancePhasesDeg = null;
/*      */   
/*  159 */   private double[] calculatedRealV = null;
/*  160 */   private double[] calculatedImagV = null;
/*  161 */   private Complex[] calculatedVoltages = null;
/*  162 */   private double[] calculatedVoltageMagnitudes = null;
/*  163 */   private double[] calculatedVoltagePhasesRad = null;
/*  164 */   private double[] calculatedVoltagePhasesDeg = null;
/*      */   
/*  166 */   ArrayList<Object> results = null;
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
/*  181 */   private boolean estimatesSet = false;
/*      */   
/*  183 */   private ImpedSpecModel userModel = null;
/*  184 */   private boolean userModelSet = false;
/*  185 */   private RegressionFunction2 regressionFunction = null;
/*  186 */   private double tolerance = 1.0E-9D;
/*  187 */   private int maximumIterations = 10000;
/*  188 */   private int numberOfIterations1 = -1;
/*  189 */   private int numberOfIterations2 = -1;
/*  190 */   private boolean regressionDone = false;
/*      */   
/*  192 */   private int numberOfLineFrequencies = 8000;
/*  193 */   private boolean logOrLinear = true;
/*      */   
/*  195 */   private double[] lineFrequencies = null;
/*  196 */   private double[] log10lineFrequencies = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpedSpecRegression()
/*      */   {
/*  203 */     this.regressionTitle = "  ";
/*      */   }
/*      */   
/*      */   public ImpedSpecRegression(String regressionTitle)
/*      */   {
/*  208 */     this.regressionTitle = regressionTitle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAppliedVoltage(double voltage)
/*      */   {
/*  215 */     this.appliedVoltage = new Complex(voltage, 0.0D);
/*  216 */     this.appliedVoltageError = new Complex(0.0D, 0.0D);
/*  217 */     this.appliedVoltageSet = true;
/*  218 */     if ((this.referenceSet) && (this.frequenciesSet)) calculateExperimentalImpedances();
/*      */   }
/*      */   
/*      */   public void appliedVoltage(double voltage, double voltageError)
/*      */   {
/*  223 */     this.appliedVoltage = new Complex(voltage, 0.0D);
/*  224 */     this.appliedVoltageSet = true;
/*  225 */     this.appliedVoltage = new Complex(voltageError, 0.0D);
/*  226 */     this.voltageErrorSet = true;
/*  227 */     if ((this.referenceSet) && (this.frequenciesSet)) calculateExperimentalImpedances();
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(double resistance)
/*      */   {
/*  232 */     this.referenceImpedance = new Complex(resistance, 0.0D);
/*  233 */     this.referenceSet = true;
/*  234 */     if ((this.appliedVoltageSet) && (this.frequenciesSet)) calculateExperimentalImpedances();
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(double real, double imag)
/*      */   {
/*  239 */     this.referenceImpedance = new Complex(real, imag);
/*  240 */     this.referenceSet = true;
/*  241 */     if ((this.appliedVoltageSet) && (this.frequenciesSet)) calculateExperimentalImpedances();
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(Complex impedance)
/*      */   {
/*  246 */     this.referenceImpedance = impedance;
/*  247 */     this.referenceSet = true;
/*  248 */     if ((this.appliedVoltageSet) && (this.frequenciesSet)) { calculateExperimentalImpedances();
/*      */     }
/*      */   }
/*      */   
/*      */   public void voltageDataAsComplex(double[] frequencies, double[] real, double[] imag)
/*      */   {
/*  254 */     double[] realWeight = new double[frequencies.length];
/*  255 */     double[] imagWeight = new double[frequencies.length];
/*  256 */     this.weightsSet = false;
/*  257 */     voltageDataAsComplex(frequencies, real, imag, realWeight, imagWeight);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void voltageDataAsComplex(double[] frequencies, double[] real, double[] imag, double[] realWeight, double[] imagWeight)
/*      */   {
/*  264 */     this.numberOfFrequencies = frequencies.length;
/*  265 */     if (this.numberOfFrequencies != real.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Real[voltages], " + real.length);
/*  266 */     if (this.numberOfFrequencies != imag.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Imag[voltages], " + imag.length);
/*  267 */     if (this.numberOfFrequencies != realWeight.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of real weights, " + realWeight.length);
/*  268 */     if (this.numberOfFrequencies != imagWeight.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of imag weights, " + imagWeight.length);
/*      */     }
/*  270 */     this.frequencies = ((double[])frequencies.clone());
/*  271 */     setAllFrequencyArrays();
/*  272 */     setCalculatedArrayLengths();
/*      */     
/*  274 */     this.realV = ((double[])real.clone());
/*  275 */     this.imagV = ((double[])imag.clone());
/*  276 */     this.realVweights = ((double[])realWeight.clone());
/*  277 */     this.imagVweights = ((double[])imagWeight.clone());
/*  278 */     this.voltageMagnitudes = new double[this.numberOfFrequencies];
/*  279 */     this.voltagePhasesDeg = new double[this.numberOfFrequencies];
/*  280 */     this.voltagePhasesRad = new double[this.numberOfFrequencies];
/*  281 */     this.voltages = Complex.oneDarray(this.numberOfFrequencies);
/*  282 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  283 */       this.voltages[i] = new Complex(this.realV[i], this.imagV[i]);
/*  284 */       this.voltageMagnitudes[i] = this.voltages[i].abs();
/*  285 */       this.voltagePhasesRad[i] = this.voltages[i].arg();
/*  286 */       this.voltagePhasesDeg[i] = Math.toDegrees(this.voltagePhasesRad[i]);
/*      */     }
/*  288 */     this.frequenciesSet = true;
/*      */     
/*  290 */     setImpedanceArrayLengths();
/*  291 */     calculateExperimentalImpedances();
/*  292 */     this.dataEnteredTypePointer = 4;
/*  293 */     this.voltageOrImpedance = true;
/*  294 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void voltageDataAsComplex(double[] frequencies, Complex[] voltages)
/*      */   {
/*  300 */     Complex[] weights = Complex.oneDarray(voltages.length, 0.0D, 0.0D);
/*  301 */     this.weightsSet = false;
/*  302 */     voltageDataAsComplex(frequencies, voltages, weights);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void voltageDataAsComplex(double[] frequencies, Complex[] voltages, Complex[] weights)
/*      */   {
/*  309 */     this.numberOfFrequencies = frequencies.length;
/*  310 */     if (this.numberOfFrequencies != voltages.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of voltages, " + voltages.length);
/*  311 */     if (this.numberOfFrequencies != weights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of weights, " + weights.length);
/*      */     }
/*  313 */     this.frequencies = ((double[])frequencies.clone());
/*  314 */     setAllFrequencyArrays();
/*  315 */     setCalculatedArrayLengths();
/*      */     
/*  317 */     this.voltages = Complex.copy(voltages);
/*  318 */     this.voltageWeights = Complex.copy(weights);
/*  319 */     this.voltageMagnitudes = new double[this.numberOfFrequencies];
/*  320 */     this.voltagePhasesDeg = new double[this.numberOfFrequencies];
/*  321 */     this.voltagePhasesRad = new double[this.numberOfFrequencies];
/*  322 */     this.realV = new double[this.numberOfFrequencies];
/*  323 */     this.imagV = new double[this.numberOfFrequencies];
/*  324 */     this.realVweights = new double[this.numberOfFrequencies];
/*  325 */     this.imagVweights = new double[this.numberOfFrequencies];
/*      */     
/*  327 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  328 */       this.realV[i] = this.voltages[i].getReal();
/*  329 */       this.imagV[i] = this.voltages[i].getImag();
/*  330 */       this.realVweights[i] = weights[i].getReal();
/*  331 */       this.imagVweights[i] = weights[i].getImag();
/*  332 */       this.voltageMagnitudes[i] = this.voltages[i].abs();
/*  333 */       this.voltagePhasesRad[i] = this.voltages[i].arg();
/*  334 */       this.voltagePhasesDeg[i] = Math.toDegrees(this.voltagePhasesRad[i]);
/*      */     }
/*  336 */     this.frequenciesSet = true;
/*      */     
/*  338 */     setImpedanceArrayLengths();
/*  339 */     calculateExperimentalImpedances();
/*  340 */     this.voltageOrImpedance = true;
/*  341 */     this.dataEnteredTypePointer = 1;
/*  342 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void voltageDataAsPhasorRad(double[] frequencies, double[] voltageMagnitudes, double[] voltagePhasesRad)
/*      */   {
/*  348 */     double[] voltageMagWeights = new double[frequencies.length];
/*  349 */     double[] voltagePhaseWeights = new double[frequencies.length];
/*  350 */     this.weightsSet = false;
/*  351 */     voltageDataAsPhasorRad(frequencies, voltageMagnitudes, voltagePhasesRad, voltageMagWeights, voltagePhaseWeights);
/*      */   }
/*      */   
/*      */ 
/*      */   public void voltageDataAsPhasorRad(double[] frequencies, double[] voltageMagnitudes, double[] voltagePhasesRad, double[] voltageMagWeights, double[] voltagePhaseWeights)
/*      */   {
/*  357 */     this.numberOfFrequencies = frequencies.length;
/*  358 */     if (this.numberOfFrequencies != voltageMagnitudes.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + voltageMagnitudes.length);
/*  359 */     if (this.numberOfFrequencies != voltagePhasesRad.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + voltagePhasesRad.length);
/*  360 */     if (this.numberOfFrequencies != voltageMagWeights.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + voltageMagWeights.length);
/*  361 */     if (this.numberOfFrequencies != voltagePhaseWeights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + voltagePhaseWeights.length);
/*      */     }
/*  363 */     this.frequencies = ((double[])frequencies.clone());
/*  364 */     setAllFrequencyArrays();
/*  365 */     setCalculatedArrayLengths();
/*      */     
/*  367 */     this.voltageMagnitudes = ((double[])voltageMagnitudes.clone());
/*  368 */     this.voltageMagnitudeWeights = ((double[])voltageMagWeights.clone());
/*  369 */     this.voltagePhaseWeightsRad = ((double[])voltagePhaseWeights.clone());
/*  370 */     this.voltages = Complex.oneDarray(this.numberOfFrequencies);
/*  371 */     this.voltagePhasesDeg = new double[this.numberOfFrequencies];
/*  372 */     this.realV = new double[this.numberOfFrequencies];
/*  373 */     this.imagV = new double[this.numberOfFrequencies];
/*  374 */     this.realVweights = new double[this.numberOfFrequencies];
/*  375 */     this.imagVweights = new double[this.numberOfFrequencies];
/*      */     
/*  377 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  378 */       this.voltagePhasesDeg[i] = Math.toDegrees(this.voltagePhasesRad[i]);
/*  379 */       this.voltages[i].polar(voltageMagnitudes[i], voltagePhasesRad[i]);
/*  380 */       this.realV[i] = this.voltages[i].getReal();
/*  381 */       this.imagV[i] = this.voltages[i].getImag();
/*  382 */       ErrorProp mag = new ErrorProp(voltageMagnitudes[i], this.voltageMagnitudeWeights[i]);
/*  383 */       ErrorProp phase = new ErrorProp(voltagePhasesRad[i], voltagePhaseWeights[i]);
/*  384 */       ComplexErrorProp volt = new ComplexErrorProp();
/*  385 */       volt.polar(mag, phase);
/*  386 */       this.realVweights[i] = volt.getRealError();
/*  387 */       this.imagVweights[i] = volt.getImagError();
/*      */     }
/*  389 */     this.frequenciesSet = true;
/*      */     
/*  391 */     setImpedanceArrayLengths();
/*  392 */     calculateExperimentalImpedances();
/*  393 */     this.voltageOrImpedance = true;
/*  394 */     this.dataEnteredTypePointer = 2;
/*  395 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void voltageDataAsPhasorDeg(double[] frequencies, double[] voltageMagnitudes, double[] voltagePhasesRad)
/*      */   {
/*  401 */     double[] voltageMagWeights = new double[frequencies.length];
/*  402 */     double[] voltagePhaseWeights = new double[frequencies.length];
/*  403 */     this.weightsSet = false;
/*  404 */     voltageDataAsPhasorDeg(frequencies, voltageMagnitudes, voltagePhasesRad, voltageMagWeights, voltagePhaseWeights);
/*      */   }
/*      */   
/*      */ 
/*      */   public void voltageDataAsPhasorDeg(double[] frequencies, double[] voltageMagnitudes, double[] voltagePhasesDeg, double[] voltageMagWeights, double[] voltagePhaseWeights)
/*      */   {
/*  410 */     this.numberOfFrequencies = frequencies.length;
/*  411 */     if (this.numberOfFrequencies != voltageMagnitudes.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + voltageMagnitudes.length);
/*  412 */     if (this.numberOfFrequencies != voltagePhasesDeg.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + voltagePhasesDeg.length);
/*  413 */     if (this.numberOfFrequencies != voltageMagWeights.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + voltageMagWeights.length);
/*  414 */     if (this.numberOfFrequencies != voltagePhaseWeights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + voltagePhaseWeights.length);
/*      */     }
/*  416 */     this.frequencies = ((double[])frequencies.clone());
/*  417 */     setAllFrequencyArrays();
/*  418 */     setCalculatedArrayLengths();
/*      */     
/*  420 */     this.voltageMagnitudes = ((double[])voltageMagnitudes.clone());
/*  421 */     this.voltagePhasesDeg = ((double[])voltagePhasesDeg.clone());
/*  422 */     this.voltages = Complex.oneDarray(this.numberOfFrequencies);
/*  423 */     this.voltagePhasesRad = new double[this.numberOfFrequencies];
/*  424 */     this.voltagePhaseWeightsRad = new double[this.numberOfFrequencies];
/*  425 */     this.voltageMagnitudeWeights = ((double[])voltageMagWeights.clone());
/*  426 */     this.voltagePhaseWeightsDeg = ((double[])voltagePhaseWeights.clone());
/*  427 */     this.realV = new double[this.numberOfFrequencies];
/*  428 */     this.imagV = new double[this.numberOfFrequencies];
/*  429 */     this.realVweights = new double[this.numberOfFrequencies];
/*  430 */     this.imagVweights = new double[this.numberOfFrequencies];
/*  431 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  432 */       this.voltagePhasesRad[i] = Math.toRadians(this.voltagePhasesDeg[i]);
/*  433 */       this.voltagePhaseWeightsRad[i] = Math.toRadians(voltagePhaseWeights[i]);
/*  434 */       this.voltages[i].polar(voltageMagnitudes[i], this.voltagePhasesRad[i]);
/*  435 */       this.realV[i] = this.voltages[i].getReal();
/*  436 */       this.imagV[i] = this.voltages[i].getImag();
/*  437 */       ErrorProp mag = new ErrorProp(voltageMagnitudes[i], this.voltageMagnitudeWeights[i]);
/*  438 */       ErrorProp phase = new ErrorProp(this.voltagePhasesRad[i], this.voltagePhaseWeightsRad[i]);
/*  439 */       ComplexErrorProp volt = new ComplexErrorProp();
/*  440 */       volt.polar(mag, phase);
/*  441 */       this.realVweights[i] = volt.getRealError();
/*  442 */       this.imagVweights[i] = volt.getImagError();
/*      */     }
/*      */     
/*  445 */     this.frequenciesSet = true;
/*      */     
/*  447 */     setImpedanceArrayLengths();
/*  448 */     calculateExperimentalImpedances();
/*  449 */     this.voltageOrImpedance = true;
/*  450 */     this.dataEnteredTypePointer = 3;
/*  451 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void impedanceDataAsComplex(double[] frequencies, double[] real, double[] imag)
/*      */   {
/*  457 */     double[] realWeight = new double[frequencies.length];
/*  458 */     double[] imagWeight = new double[frequencies.length];
/*  459 */     this.weightsSet = false;
/*  460 */     impedanceDataAsComplex(frequencies, real, imag, realWeight, imagWeight);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void impedanceDataAsComplex(double[] frequencies, double[] real, double[] imag, double[] realWeight, double[] imagWeight)
/*      */   {
/*  467 */     this.numberOfFrequencies = frequencies.length;
/*  468 */     if (this.numberOfFrequencies != real.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Real[impedances], " + real.length);
/*  469 */     if (this.numberOfFrequencies != imag.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Imag[impedances], " + imag.length);
/*  470 */     if (this.numberOfFrequencies != realWeight.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of real weights, " + realWeight.length);
/*  471 */     if (this.numberOfFrequencies != imagWeight.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of imag weights, " + imagWeight.length);
/*      */     }
/*  473 */     this.frequencies = ((double[])frequencies.clone());
/*  474 */     setAllFrequencyArrays();
/*  475 */     setCalculatedArrayLengths();
/*      */     
/*  477 */     this.realZ = ((double[])real.clone());
/*  478 */     this.imagZ = ((double[])imag.clone());
/*  479 */     this.realZweights = ((double[])realWeight.clone());
/*  480 */     this.imagZweights = ((double[])imagWeight.clone());
/*  481 */     this.impedanceMagnitudes = new double[this.numberOfFrequencies];
/*  482 */     this.impedancePhasesDeg = new double[this.numberOfFrequencies];
/*  483 */     this.impedancePhasesRad = new double[this.numberOfFrequencies];
/*  484 */     this.impedances = Complex.oneDarray(this.numberOfFrequencies);
/*  485 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  486 */       this.impedances[i] = new Complex(this.realZ[i], this.imagZ[i]);
/*  487 */       this.impedanceMagnitudes[i] = this.impedances[i].abs();
/*  488 */       this.impedancePhasesRad[i] = this.impedances[i].arg();
/*  489 */       this.impedancePhasesDeg[i] = Math.toDegrees(this.impedancePhasesRad[i]);
/*      */     }
/*  491 */     this.frequenciesSet = true;
/*  492 */     this.impedancesSet = true;
/*      */     
/*  494 */     this.dataEnteredTypePointer = 4;
/*  495 */     this.voltageOrImpedance = false;
/*  496 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void impedanceDataAsComplex(double[] frequencies, Complex[] impedances)
/*      */   {
/*  502 */     Complex[] weights = Complex.oneDarray(impedances.length, 0.0D, 0.0D);
/*  503 */     this.weightsSet = false;
/*  504 */     impedanceDataAsComplex(frequencies, impedances, weights);
/*      */   }
/*      */   
/*      */ 
/*      */   public void impedanceDataAsComplex(double[] frequencies, Complex[] impedances, Complex[] weights)
/*      */   {
/*  510 */     this.numberOfFrequencies = frequencies.length;
/*  511 */     if (this.numberOfFrequencies != impedances.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of impedances, " + impedances.length);
/*  512 */     if (this.numberOfFrequencies != weights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of weights, " + weights.length);
/*      */     }
/*  514 */     this.frequencies = ((double[])frequencies.clone());
/*  515 */     setAllFrequencyArrays();
/*  516 */     setCalculatedArrayLengths();
/*      */     
/*  518 */     this.impedances = Complex.copy(impedances);
/*  519 */     this.impedanceWeights = Complex.copy(weights);
/*  520 */     this.impedanceMagnitudes = new double[this.numberOfFrequencies];
/*  521 */     this.impedancePhasesDeg = new double[this.numberOfFrequencies];
/*  522 */     this.impedancePhasesRad = new double[this.numberOfFrequencies];
/*  523 */     this.realZ = new double[this.numberOfFrequencies];
/*  524 */     this.imagZ = new double[this.numberOfFrequencies];
/*  525 */     this.realZweights = new double[this.numberOfFrequencies];
/*  526 */     this.imagZweights = new double[this.numberOfFrequencies];
/*      */     
/*  528 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  529 */       this.realZ[i] = this.impedances[i].getReal();
/*  530 */       this.imagZ[i] = this.impedances[i].getImag();
/*  531 */       this.realZweights[i] = weights[i].getReal();
/*  532 */       this.imagZweights[i] = weights[i].getImag();
/*  533 */       this.impedanceMagnitudes[i] = this.impedances[i].abs();
/*  534 */       this.impedancePhasesRad[i] = this.impedances[i].arg();
/*  535 */       this.impedancePhasesDeg[i] = Math.toDegrees(this.impedancePhasesRad[i]);
/*      */     }
/*  537 */     this.frequenciesSet = true;
/*  538 */     this.impedancesSet = true;
/*      */     
/*  540 */     this.voltageOrImpedance = false;
/*  541 */     this.dataEnteredTypePointer = 5;
/*  542 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void impedanceDataAsPhasorRad(double[] frequencies, double[] impedanceMagnitudes, double[] impedancePhasesRad)
/*      */   {
/*  548 */     double[] impedanceMagWeights = new double[frequencies.length];
/*  549 */     double[] impedancePhaseWeights = new double[frequencies.length];
/*  550 */     this.weightsSet = false;
/*  551 */     impedanceDataAsPhasorRad(frequencies, impedanceMagnitudes, impedancePhasesRad, impedanceMagWeights, impedancePhaseWeights);
/*      */   }
/*      */   
/*      */ 
/*      */   public void impedanceDataAsPhasorRad(double[] frequencies, double[] impedanceMagnitudes, double[] impedancePhasesRad, double[] impedanceMagWeights, double[] impedancePhaseWeights)
/*      */   {
/*  557 */     this.numberOfFrequencies = frequencies.length;
/*  558 */     if (this.numberOfFrequencies != impedanceMagnitudes.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + impedanceMagnitudes.length);
/*  559 */     if (this.numberOfFrequencies != impedancePhasesRad.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + impedancePhasesRad.length);
/*  560 */     if (this.numberOfFrequencies != impedanceMagWeights.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + impedanceMagWeights.length);
/*  561 */     if (this.numberOfFrequencies != impedancePhaseWeights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + impedancePhaseWeights.length);
/*      */     }
/*  563 */     this.frequencies = ((double[])frequencies.clone());
/*  564 */     setAllFrequencyArrays();
/*  565 */     setCalculatedArrayLengths();
/*      */     
/*  567 */     this.impedanceMagnitudes = ((double[])impedanceMagnitudes.clone());
/*  568 */     this.impedanceMagnitudeWeights = ((double[])impedanceMagWeights.clone());
/*  569 */     this.impedancePhaseWeightsRad = ((double[])impedancePhaseWeights.clone());
/*  570 */     this.impedances = Complex.oneDarray(this.numberOfFrequencies);
/*  571 */     this.impedancePhasesDeg = new double[this.numberOfFrequencies];
/*  572 */     this.realZ = new double[this.numberOfFrequencies];
/*  573 */     this.imagZ = new double[this.numberOfFrequencies];
/*  574 */     this.realZweights = new double[this.numberOfFrequencies];
/*  575 */     this.imagZweights = new double[this.numberOfFrequencies];
/*      */     
/*  577 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  578 */       this.impedancePhasesDeg[i] = Math.toDegrees(this.impedancePhasesRad[i]);
/*  579 */       this.impedances[i].polar(impedanceMagnitudes[i], impedancePhasesRad[i]);
/*  580 */       this.realZ[i] = this.impedances[i].getReal();
/*  581 */       this.imagZ[i] = this.impedances[i].getImag();
/*  582 */       ErrorProp mag = new ErrorProp(impedanceMagnitudes[i], this.impedanceMagnitudeWeights[i]);
/*  583 */       ErrorProp phase = new ErrorProp(impedancePhasesRad[i], impedancePhaseWeights[i]);
/*  584 */       ComplexErrorProp volt = new ComplexErrorProp();
/*  585 */       volt.polar(mag, phase);
/*  586 */       this.realZweights[i] = volt.getRealError();
/*  587 */       this.imagZweights[i] = volt.getImagError();
/*      */     }
/*  589 */     this.frequenciesSet = true;
/*  590 */     this.impedancesSet = true;
/*      */     
/*  592 */     this.voltageOrImpedance = false;
/*  593 */     this.dataEnteredTypePointer = 6;
/*  594 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   public void impedanceDataAsPhasorDeg(double[] frequencies, double[] impedanceMagnitudes, double[] impedancePhasesRad)
/*      */   {
/*  600 */     double[] impedanceMagWeights = new double[frequencies.length];
/*  601 */     double[] impedancePhaseWeights = new double[frequencies.length];
/*  602 */     this.weightsSet = false;
/*  603 */     impedanceDataAsPhasorDeg(frequencies, impedanceMagnitudes, impedancePhasesRad, impedanceMagWeights, impedancePhaseWeights);
/*      */   }
/*      */   
/*      */ 
/*      */   public void impedanceDataAsPhasorDeg(double[] frequencies, double[] impedanceMagnitudes, double[] impedancePhasesDeg, double[] impedanceMagWeights, double[] impedancePhaseWeights)
/*      */   {
/*  609 */     this.numberOfFrequencies = frequencies.length;
/*  610 */     if (this.numberOfFrequencies != impedanceMagnitudes.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + impedanceMagnitudes.length);
/*  611 */     if (this.numberOfFrequencies != impedancePhasesDeg.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + impedancePhasesDeg.length);
/*  612 */     if (this.numberOfFrequencies != impedanceMagWeights.length) throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + impedanceMagWeights.length);
/*  613 */     if (this.numberOfFrequencies != impedancePhaseWeights.length) { throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + impedancePhaseWeights.length);
/*      */     }
/*  615 */     this.frequencies = ((double[])frequencies.clone());
/*  616 */     setAllFrequencyArrays();
/*  617 */     setCalculatedArrayLengths();
/*      */     
/*  619 */     this.impedanceMagnitudes = ((double[])impedanceMagnitudes.clone());
/*  620 */     this.impedancePhasesDeg = ((double[])impedancePhasesDeg.clone());
/*  621 */     this.impedances = Complex.oneDarray(this.numberOfFrequencies);
/*  622 */     this.impedancePhasesRad = new double[this.numberOfFrequencies];
/*  623 */     this.impedancePhaseWeightsRad = new double[this.numberOfFrequencies];
/*  624 */     this.impedanceMagnitudeWeights = ((double[])impedanceMagWeights.clone());
/*  625 */     this.impedancePhaseWeightsDeg = ((double[])impedancePhaseWeights.clone());
/*  626 */     this.realZ = new double[this.numberOfFrequencies];
/*  627 */     this.imagZ = new double[this.numberOfFrequencies];
/*  628 */     this.realZweights = new double[this.numberOfFrequencies];
/*  629 */     this.imagZweights = new double[this.numberOfFrequencies];
/*      */     
/*  631 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  632 */       this.impedancePhasesRad[i] = Math.toRadians(this.impedancePhasesDeg[i]);
/*  633 */       this.impedancePhaseWeightsRad[i] = Math.toRadians(impedancePhaseWeights[i]);
/*  634 */       this.impedances[i].polar(impedanceMagnitudes[i], this.impedancePhasesRad[i]);
/*  635 */       this.realZ[i] = this.impedances[i].getReal();
/*  636 */       this.imagZ[i] = this.impedances[i].getImag();
/*  637 */       ErrorProp mag = new ErrorProp(impedanceMagnitudes[i], this.impedanceMagnitudeWeights[i]);
/*  638 */       ErrorProp phase = new ErrorProp(this.impedancePhasesRad[i], this.impedancePhaseWeightsRad[i]);
/*  639 */       ComplexErrorProp volt = new ComplexErrorProp();
/*  640 */       volt.polar(mag, phase);
/*  641 */       this.realZweights[i] = volt.getRealError();
/*  642 */       this.imagZweights[i] = volt.getImagError();
/*      */     }
/*      */     
/*  645 */     this.frequenciesSet = true;
/*  646 */     this.impedancesSet = true;
/*      */     
/*  648 */     this.voltageOrImpedance = false;
/*  649 */     this.dataEnteredTypePointer = 7;
/*  650 */     if (this.estimatesNeeded) { setInitialEstimates();
/*      */     }
/*      */   }
/*      */   
/*      */   private void setAllFrequencyArrays()
/*      */   {
/*  656 */     this.log10frequencies = new double[this.numberOfFrequencies];
/*  657 */     this.omegas = new double[this.numberOfFrequencies];
/*  658 */     this.log10omegas = new double[this.numberOfFrequencies];
/*  659 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  660 */       this.log10frequencies[i] = Math.log10(this.frequencies[i]);
/*  661 */       this.omegas[i] = (6.283185307179586D * this.frequencies[i]);
/*  662 */       this.log10omegas[i] = Math.log10(this.omegas[i]);
/*      */     }
/*  664 */     this.frequenciesSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   private void setCalculatedArrayLengths()
/*      */   {
/*  670 */     this.realZresiduals = new double[this.numberOfFrequencies];
/*  671 */     this.imagZresiduals = new double[this.numberOfFrequencies];
/*  672 */     this.calculatedRealZ = new double[this.numberOfFrequencies];
/*  673 */     this.calculatedImagZ = new double[this.numberOfFrequencies];
/*  674 */     this.calculatedImpedances = Complex.oneDarray(this.numberOfFrequencies);
/*  675 */     this.calculatedImpedanceMagnitudes = new double[this.numberOfFrequencies];
/*  676 */     this.calculatedImpedancePhasesRad = new double[this.numberOfFrequencies];
/*  677 */     this.calculatedImpedancePhasesDeg = new double[this.numberOfFrequencies];
/*      */     
/*  679 */     if ((this.appliedVoltageSet) && (this.referenceSet)) {
/*  680 */       this.calculatedRealV = new double[this.numberOfFrequencies];
/*  681 */       this.calculatedImagV = new double[this.numberOfFrequencies];
/*  682 */       this.calculatedVoltages = Complex.oneDarray(this.numberOfFrequencies);
/*  683 */       this.calculatedVoltageMagnitudes = new double[this.numberOfFrequencies];
/*  684 */       this.calculatedVoltagePhasesRad = new double[this.numberOfFrequencies];
/*  685 */       this.calculatedVoltagePhasesDeg = new double[this.numberOfFrequencies];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void setImpedanceArrayLengths()
/*      */   {
/*  692 */     this.realZ = new double[this.numberOfFrequencies];
/*  693 */     this.imagZ = new double[this.numberOfFrequencies];
/*  694 */     this.realZweights = new double[this.numberOfFrequencies];
/*  695 */     this.imagZweights = new double[this.numberOfFrequencies];
/*  696 */     this.impedances = Complex.oneDarray(this.numberOfFrequencies);
/*  697 */     this.impedanceMagnitudes = new double[this.numberOfFrequencies];
/*  698 */     this.impedancePhasesRad = new double[this.numberOfFrequencies];
/*  699 */     this.impedancePhasesDeg = new double[this.numberOfFrequencies];
/*      */   }
/*      */   
/*      */   private void calculateExperimentalImpedances()
/*      */   {
/*  704 */     if ((this.referenceSet) && (this.appliedVoltageSet)) {
/*  705 */       for (int i = 0; i < this.numberOfFrequencies; i++)
/*      */       {
/*      */ 
/*  708 */         this.impedances[i] = this.referenceImpedance.times(this.voltages[i]).over(this.appliedVoltage.minus(this.voltages[i]));
/*      */         
/*  710 */         this.realZ[i] = this.impedances[i].getReal();
/*  711 */         this.imagZ[i] = this.impedances[i].getImag();
/*      */         
/*  713 */         this.impedanceMagnitudes[i] = this.impedances[i].abs();
/*  714 */         this.impedancePhasesRad[i] = this.impedances[i].arg();
/*  715 */         this.impedancePhasesDeg[i] = Math.toDegrees(this.impedancePhasesRad[i]);
/*      */         
/*  717 */         if ((this.weightsSet) && (this.voltageErrorSet)) {
/*  718 */           ComplexErrorProp appliedV = new ComplexErrorProp(this.appliedVoltage.getReal(), this.appliedVoltageError.getReal(), this.appliedVoltage.getImag(), this.appliedVoltageError.getImag());
/*  719 */           ComplexErrorProp expertlV = new ComplexErrorProp(this.realV[i], this.realVweights[i], this.imagV[i], this.imagVweights[i]);
/*  720 */           ComplexErrorProp refImped = new ComplexErrorProp(this.referenceImpedance.getReal(), 0.0D, this.referenceImpedance.getImag(), 0.0D);
/*  721 */           ComplexErrorProp eVoverAv = expertlV.over(appliedV).times(refImped);
/*  722 */           this.realZweights[i] = eVoverAv.getRealError();
/*  723 */           this.imagZweights[i] = eVoverAv.getImagError();
/*      */         }
/*  725 */         this.impedancesSet = true;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setModel(ImpedSpecModel userModel, String[] symbols, double[] initialEstimates, double[] initialSteps)
/*      */   {
/*  736 */     this.userModel = userModel;
/*  737 */     this.parameterSymbols = symbols;
/*  738 */     this.numberOfParameters = symbols.length;
/*  739 */     if (this.numberOfParameters != initialEstimates.length) throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial estimates, " + initialEstimates.length);
/*  740 */     if (this.numberOfParameters != initialSteps.length) throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial steps, " + initialSteps.length);
/*  741 */     this.initialEstimates = initialEstimates;
/*  742 */     this.initialSteps = initialSteps;
/*  743 */     setEstimateArrayDimensions();
/*  744 */     this.estimatesSet = true;
/*  745 */     this.userModelSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModel(ImpedSpecModel userModel, String[] symbols, double[] initialEstimates)
/*      */   {
/*  751 */     this.userModel = userModel;
/*  752 */     this.parameterSymbols = symbols;
/*  753 */     this.numberOfParameters = symbols.length;
/*  754 */     if (this.numberOfParameters != initialEstimates.length) throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial estimates, " + initialEstimates.length);
/*  755 */     this.initialEstimates = initialEstimates;
/*  756 */     this.initialSteps = new double[this.numberOfParameters];
/*  757 */     for (int i = 0; i < this.numberOfParameters; i++) this.initialSteps[i] = (Math.abs(this.initialEstimates[i]) * 0.1D);
/*  758 */     setEstimateArrayDimensions();
/*  759 */     this.estimatesSet = true;
/*  760 */     this.userModelSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModel(int modelNumber, double[] initialEstimates, double[] initialSteps)
/*      */   {
/*  766 */     this.numberOfParameters = initialEstimates.length;
/*  767 */     if (this.numberOfParameters != Impedance.modelComponents(modelNumber).length) throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameters, " + Impedance.modelComponents(modelNumber).length + ", in model number " + modelNumber);
/*  768 */     if (this.numberOfParameters != initialSteps.length) { throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameter steps, " + initialSteps.length);
/*      */     }
/*  770 */     this.modelNumber = modelNumber;
/*  771 */     this.initialEstimates = initialEstimates;
/*  772 */     this.initialSteps = initialSteps;
/*  773 */     this.parameterSymbols = Impedance.modelComponents(modelNumber);
/*  774 */     setEstimateArrayDimensions();
/*  775 */     this.estimatesSet = true;
/*  776 */     this.modelSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModel(int modelNumber, double[] initialEstimates)
/*      */   {
/*  782 */     this.numberOfParameters = initialEstimates.length;
/*  783 */     if (this.numberOfParameters != Impedance.modelComponents(modelNumber).length) { throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameters, " + Impedance.modelComponents(modelNumber).length + ", in model number " + modelNumber);
/*      */     }
/*  785 */     this.modelNumber = modelNumber;
/*  786 */     this.initialEstimates = initialEstimates;
/*  787 */     this.parameterSymbols = Impedance.modelComponents(modelNumber);
/*  788 */     this.initialSteps = new double[this.numberOfParameters];
/*  789 */     for (int i = 0; i < this.numberOfParameters; i++) this.initialSteps[i] = (Math.abs(this.initialEstimates[i]) * 0.1D);
/*  790 */     setEstimateArrayDimensions();
/*  791 */     this.estimatesSet = true;
/*  792 */     this.modelSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModel(int modelNumber)
/*      */   {
/*  798 */     this.modelNumber = modelNumber;
/*  799 */     this.parameterSymbols = Impedance.modelComponents(modelNumber);
/*  800 */     this.numberOfParameters = this.parameterSymbols.length;
/*      */     
/*  802 */     setEstimateArrayDimensions();
/*      */     
/*      */ 
/*  805 */     setInitialEstimates();
/*  806 */     this.estimatesSet = true;
/*      */     
/*  808 */     this.modelSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   private void setEstimateArrayDimensions()
/*      */   {
/*  814 */     this.bestEstimates = new double[this.numberOfParameters];
/*  815 */     this.standardDeviations = new double[this.numberOfParameters];
/*  816 */     this.coefficientsOfVariation = new double[this.numberOfParameters];
/*  817 */     this.preMinimumGradients = new double[this.numberOfParameters];
/*  818 */     this.postMinimumGradients = new double[this.numberOfParameters];
/*      */     
/*  820 */     this.correlationCoefficients = new double[this.numberOfParameters][this.numberOfParameters];
/*      */   }
/*      */   
/*      */   private void setInitialEstimates()
/*      */   {
/*  825 */     if ((this.impedancesSet) && (this.frequenciesSet))
/*      */     {
/*  827 */       this.degreesOfFreedom = (this.numberOfFrequencies - this.numberOfParameters);
/*  828 */       if (this.degreesOfFreedom <= 0) { throw new IllegalArgumentException("Degrees of freedom, " + this.degreesOfFreedom + ", are less than 1");
/*      */       }
/*  830 */       double meanRealZ = Stat.mean(this.realZ);
/*  831 */       double minRealZ = Fmath.minimum(this.realZ);
/*  832 */       int indexMinRealZ = Fmath.indexOf(this.realZ, minRealZ);
/*  833 */       double maxRealZ = Fmath.maximum(this.realZ);
/*  834 */       int indexMaxRealZ = Fmath.indexOf(this.realZ, maxRealZ);
/*      */       
/*  836 */       double meanImagZ = Stat.mean(this.imagZ);
/*  837 */       double minImagZ = Fmath.minimum(this.imagZ);
/*  838 */       int indexMinImagZ = Fmath.indexOf(this.imagZ, minImagZ);
/*  839 */       double maxImagZ = Fmath.maximum(this.imagZ);
/*  840 */       int indexMaxImagZ = Fmath.indexOf(this.imagZ, maxImagZ);
/*      */       
/*  842 */       double imagBig = Math.max(Math.abs(minImagZ), Math.abs(maxImagZ));
/*  843 */       int bigIndex = Fmath.indexOf(this.imagZ, imagBig);
/*  844 */       if (bigIndex == -1) bigIndex = Fmath.indexOf(this.imagZ, -imagBig);
/*  845 */       if (bigIndex == -1) { bigIndex = this.numberOfFrequencies / 2;
/*      */       }
/*  847 */       double geometricFreqMean = Stat.geometricMean(this.log10frequencies);
/*      */       
/*  849 */       switch (this.modelNumber) {
/*  850 */       case 1:  this.initialEstimates = new double[this.numberOfParameters];
/*  851 */         this.initialEstimates[0] = meanRealZ;
/*  852 */         break;
/*  853 */       case 2:  this.initialEstimates = new double[this.numberOfParameters];
/*  854 */         double sumC = 0.0D;
/*  855 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/*  856 */         this.initialEstimates[0] = (sumC / this.numberOfFrequencies);
/*  857 */         break;
/*  858 */       case 3:  this.initialEstimates = new double[this.numberOfParameters];
/*  859 */         double sumL = 0.0D;
/*  860 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumL += Math.abs(this.imagZ[i] / this.omegas[i]);
/*  861 */         this.initialEstimates[0] = (sumL / this.numberOfFrequencies);
/*  862 */         break;
/*  863 */       case 4:  this.initialEstimates = new double[this.numberOfParameters];
/*  864 */         double sumW = 0.0D;
/*  865 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  866 */           sumW += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/*  867 */           sumW += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */         }
/*  869 */         this.initialEstimates[0] = (sumW / (2.0D * this.numberOfFrequencies));
/*  870 */         break;
/*  871 */       case 5:  this.initialEstimates = new double[this.numberOfParameters];
/*  872 */         double sumF = 0.0D;
/*  873 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  874 */           sumF += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/*  875 */           sumF += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */         }
/*  877 */         this.initialEstimates[0] = (sumF / (2.0D * this.numberOfFrequencies));
/*  878 */         this.initialEstimates[1] = Math.abs(meanRealZ / this.initialEstimates[0]);
/*  879 */         break;
/*  880 */       case 6:  this.initialEstimates = new double[this.numberOfParameters];
/*  881 */         double sumQ = 0.0D;
/*  882 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumQ += this.imagZ[i] / this.realZ[i];
/*  883 */         sumQ /= this.numberOfFrequencies;
/*  884 */         double theta = Math.abs(Math.atan(sumQ));
/*  885 */         double cosTheta = Math.cos(theta);
/*  886 */         double sinTheta = Math.sin(theta);
/*  887 */         this.initialEstimates[1] = (theta / 1.5707963267948966D);
/*  888 */         double sigmaQ = 0.0D;
/*  889 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  890 */           sigmaQ += Math.abs(this.realZ[i] / (cosTheta * Math.pow(this.omegas[i], this.initialEstimates[1])));
/*  891 */           sigmaQ += Math.abs(this.imagZ[i] / (sinTheta * Math.pow(this.omegas[i], this.initialEstimates[1])));
/*      */         }
/*  893 */         this.initialEstimates[0] = (sigmaQ / (2.0D * this.numberOfFrequencies));
/*  894 */         break;
/*  895 */       case 7:  this.initialEstimates = new double[this.numberOfParameters];
/*  896 */         this.initialEstimates[0] = meanRealZ;
/*  897 */         double sumC7 = 0.0D;
/*  898 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC7 += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/*  899 */         this.initialEstimates[1] = (sumC7 / this.numberOfFrequencies);
/*  900 */         break;
/*  901 */       case 8:  this.initialEstimates = new double[this.numberOfParameters];
/*  902 */         this.initialEstimates[0] = meanRealZ;
/*  903 */         double sumL8 = 0.0D;
/*  904 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumL8 += Math.abs(this.imagZ[i] / this.omegas[i]);
/*  905 */         this.initialEstimates[1] = (sumL8 / this.numberOfFrequencies);
/*  906 */         break;
/*  907 */       case 9:  this.initialEstimates = new double[this.numberOfParameters];
/*  908 */         double sumL9 = 0.0D;
/*  909 */         double sumC9 = 0.0D;
/*  910 */         for (int i = 1; i < this.numberOfFrequencies; i++) {
/*  911 */           double cC9 = (this.frequencies[i] - this.frequencies[(i - 1)]) / this.frequencies[i] / (this.imagZ[i] * this.frequencies[(i - 1)] - this.imagZ[(i - 1)] * this.frequencies[i]);
/*  912 */           double lL9 = (this.imagZ[i] + 1.0D / (cC9 * this.frequencies[i])) / this.frequencies[i];
/*  913 */           sumL9 += lL9;
/*  914 */           sumC9 += cC9;
/*      */         }
/*  916 */         this.initialEstimates[0] = (sumL9 / (this.numberOfFrequencies - 1));
/*  917 */         this.initialEstimates[1] = (sumC9 / (this.numberOfFrequencies - 1));
/*  918 */         break;
/*  919 */       case 10:  this.initialEstimates = new double[this.numberOfParameters];
/*  920 */         this.initialEstimates[0] = maxRealZ;
/*  921 */         this.initialEstimates[1] = (1.0D / (maxRealZ * this.frequencies[indexMinImagZ]));
/*  922 */         break;
/*  923 */       case 11:  this.initialEstimates = new double[this.numberOfParameters];
/*  924 */         this.initialEstimates[0] = maxRealZ;
/*  925 */         this.initialEstimates[1] = (maxRealZ / this.frequencies[indexMaxImagZ]);
/*  926 */         break;
/*  927 */       case 12:  this.initialEstimates = new double[this.numberOfParameters];
/*  928 */         double cL12 = 1.0D / this.frequencies[indexMinImagZ];
/*  929 */         double sumL12 = 0.0D;
/*  930 */         double sumC12 = 0.0D;
/*  931 */         for (int i = 1; i < this.numberOfFrequencies; i++) {
/*  932 */           double c12 = this.imagZ[i] * (this.frequencies[i] * cL12 - 1.0D / this.frequencies[i]);
/*  933 */           sumL12 += c12;
/*  934 */           sumC12 += cL12 / c12;
/*      */         }
/*  936 */         this.initialEstimates[0] = (sumL12 / this.numberOfFrequencies);
/*  937 */         this.initialEstimates[1] = (sumC12 / this.numberOfFrequencies);
/*  938 */         break;
/*  939 */       case 13:  this.initialEstimates = new double[this.numberOfParameters];
/*  940 */         this.initialEstimates[2] = minRealZ;
/*  941 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/*  942 */         this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/*  943 */         break;
/*  944 */       case 14:  this.initialEstimates = new double[this.numberOfParameters];
/*  945 */         this.initialEstimates[2] = minRealZ;
/*  946 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/*  947 */         double sumL14 = 0.0D;
/*  948 */         double sumC14 = 0.0D;
/*  949 */         for (int i = 1; i < this.numberOfFrequencies; i++) {
/*  950 */           double cC14 = (this.frequencies[i] - this.frequencies[(i - 1)]) / this.frequencies[i] / (this.imagZ[i] * this.frequencies[(i - 1)] - this.imagZ[(i - 1)] * this.frequencies[i]);
/*  951 */           double lL14 = (this.imagZ[i] + 1.0D / (cC14 * this.frequencies[i])) / this.frequencies[i];
/*  952 */           sumL14 += lL14;
/*  953 */           sumC14 += cC14;
/*      */         }
/*  955 */         this.initialEstimates[3] = (sumL14 / (this.numberOfFrequencies - 1));
/*  956 */         this.initialEstimates[1] = (sumC14 / (this.numberOfFrequencies - 1));
/*  957 */         break;
/*  958 */       case 15:  this.initialEstimates = new double[this.numberOfParameters];
/*  959 */         this.initialEstimates[2] = minRealZ;
/*  960 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/*  961 */         double cL15 = 1.0D / this.frequencies[indexMinImagZ];
/*  962 */         double sumL15 = 0.0D;
/*  963 */         double sumC15 = 0.0D;
/*  964 */         for (int i = 1; i < this.numberOfFrequencies; i++) {
/*  965 */           double c15 = this.imagZ[i] * (this.frequencies[i] * cL15 - 1.0D / this.frequencies[i]);
/*  966 */           sumL15 += c15;
/*  967 */           sumC15 += cL15 / c15;
/*      */         }
/*  969 */         this.initialEstimates[3] = (sumL15 / this.numberOfFrequencies);
/*  970 */         this.initialEstimates[1] = (sumC15 / this.numberOfFrequencies);
/*  971 */         break;
/*  972 */       case 16:  this.initialEstimates = new double[this.numberOfParameters];
/*  973 */         this.initialEstimates[0] = maxRealZ;
/*  974 */         double sumC16 = 0.0D;
/*  975 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC16 += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/*  976 */         this.initialEstimates[1] = (2.0D * sumC16 / this.numberOfFrequencies);
/*  977 */         this.initialEstimates[2] = this.initialEstimates[1];
/*  978 */         break;
/*  979 */       case 17:  this.initialEstimates = new double[this.numberOfParameters];
/*  980 */         this.initialEstimates[0] = maxRealZ;
/*  981 */         double sumC17 = 0.0D;
/*  982 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC17 += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/*  983 */         this.initialEstimates[1] = (sumC17 / (2.0D * this.numberOfFrequencies));
/*  984 */         this.initialEstimates[2] = this.initialEstimates[1];
/*  985 */       case 18:  this.initialEstimates = new double[this.numberOfParameters];
/*  986 */         this.initialEstimates[0] = minRealZ;
/*  987 */         this.initialEstimates[2] = (maxRealZ - minRealZ);
/*  988 */         double sumC18 = 0.0D;
/*  989 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC18 += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/*  990 */         this.initialEstimates[1] = (2.0D * sumC18 / this.numberOfFrequencies);
/*  991 */         this.initialEstimates[3] = this.initialEstimates[1];
/*  992 */         break;
/*  993 */       case 19:  this.initialEstimates = new double[this.numberOfParameters];
/*  994 */         this.initialEstimates[0] = (maxRealZ / 2.0D);
/*  995 */         this.initialEstimates[2] = this.initialEstimates[0];
/*  996 */         this.initialEstimates[1] = (2.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/*  997 */         this.initialEstimates[3] = this.initialEstimates[1];
/*  998 */         break;
/*  999 */       case 20:  this.initialEstimates = new double[this.numberOfParameters];
/* 1000 */         this.initialEstimates[4] = minRealZ;
/* 1001 */         this.initialEstimates[0] = ((maxRealZ - minRealZ) / 2.0D);
/* 1002 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1003 */         this.initialEstimates[1] = (2.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1004 */         this.initialEstimates[3] = this.initialEstimates[1];
/* 1005 */         break;
/* 1006 */       case 21:  this.initialEstimates = new double[this.numberOfParameters];
/* 1007 */         this.initialEstimates[4] = minRealZ;
/* 1008 */         this.initialEstimates[0] = ((maxRealZ - minRealZ) / 2.0D);
/* 1009 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1010 */         double sumC21 = 0.0D;
/* 1011 */         for (int i = 0; i < this.numberOfFrequencies; i++) sumC21 += 1.0D / Math.abs(this.imagZ[i] * this.omegas[i]);
/* 1012 */         this.initialEstimates[1] = (sumC21 / (2.0D * this.numberOfFrequencies));
/* 1013 */         this.initialEstimates[3] = this.initialEstimates[1];
/* 1014 */         break;
/* 1015 */       case 22:  this.initialEstimates = new double[this.numberOfParameters];
/* 1016 */         this.initialEstimates[0] = (maxRealZ / 3.0D);
/* 1017 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1018 */         this.initialEstimates[4] = this.initialEstimates[0];
/* 1019 */         this.initialEstimates[1] = (3.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1020 */         this.initialEstimates[3] = this.initialEstimates[1];
/* 1021 */         this.initialEstimates[5] = this.initialEstimates[1];
/* 1022 */         break;
/* 1023 */       case 23:  this.initialEstimates = new double[this.numberOfParameters];
/* 1024 */         this.initialEstimates[6] = minRealZ;
/* 1025 */         this.initialEstimates[0] = ((maxRealZ - minRealZ) / 3.0D);
/* 1026 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1027 */         this.initialEstimates[4] = this.initialEstimates[0];
/* 1028 */         this.initialEstimates[1] = (3.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1029 */         this.initialEstimates[3] = this.initialEstimates[1];
/* 1030 */         this.initialEstimates[5] = this.initialEstimates[1];
/* 1031 */         break;
/* 1032 */       case 24:  this.initialEstimates = new double[this.numberOfParameters];
/* 1033 */         this.initialEstimates[3] = minRealZ;
/* 1034 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/* 1035 */         double sumW24 = 0.0D;
/* 1036 */         if (indexMinImagZ < this.numberOfFrequencies - 3) {
/* 1037 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1038 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1039 */             sumW24 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1040 */             sumW24 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1042 */           this.initialEstimates[2] = (sumW24 / (2.0D * (this.numberOfFrequencies - indexMinImagZ)));
/*      */         }
/*      */         else {
/* 1045 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * geometricFreqMean));
/* 1046 */           for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1047 */             sumW24 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1048 */             sumW24 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1050 */           this.initialEstimates[2] = (sumW24 / (2.0D * this.numberOfFrequencies));
/*      */         }
/* 1052 */         break;
/* 1053 */       case 25:  this.initialEstimates = new double[this.numberOfParameters];
/* 1054 */         this.initialEstimates[4] = minRealZ;
/* 1055 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/* 1056 */         double sumF25 = 0.0D;
/* 1057 */         if (indexMinImagZ < this.numberOfFrequencies - 3) {
/* 1058 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1059 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1060 */             sumF25 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1061 */             sumF25 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1063 */           this.initialEstimates[2] = (sumF25 / (2.0D * (this.numberOfFrequencies - indexMinImagZ)));
/* 1064 */           this.initialEstimates[3] = Math.abs(meanRealZ / this.initialEstimates[2]);
/*      */         }
/*      */         else {
/* 1067 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * geometricFreqMean));
/* 1068 */           for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1069 */             sumF25 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1070 */             sumF25 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1072 */           this.initialEstimates[2] = (sumF25 / (2.0D * this.numberOfFrequencies));
/* 1073 */           this.initialEstimates[3] = Math.abs(meanRealZ / this.initialEstimates[2]);
/*      */         }
/* 1075 */         break;
/* 1076 */       case 26:  this.initialEstimates = new double[this.numberOfParameters];
/* 1077 */         this.initialEstimates[4] = minRealZ;
/* 1078 */         this.initialEstimates[0] = (maxRealZ - minRealZ);
/* 1079 */         double sumQ26 = 0.0D;
/* 1080 */         if (indexMinImagZ < this.numberOfFrequencies - 3) {
/* 1081 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1082 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) sumQ26 += this.imagZ[i] / this.realZ[i];
/* 1083 */           sumQ26 /= (this.numberOfFrequencies - indexMinImagZ);
/* 1084 */           double theta26 = Math.abs(Math.atan(sumQ26));
/* 1085 */           double cosTheta26 = Math.cos(theta26);
/* 1086 */           double sinTheta26 = Math.sin(theta26);
/* 1087 */           this.initialEstimates[3] = (theta26 / 1.5707963267948966D);
/* 1088 */           double sigmaQ26 = 0.0D;
/* 1089 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1090 */             sigmaQ26 += Math.abs(this.realZ[i] / (cosTheta26 * Math.pow(this.omegas[i], this.initialEstimates[1])));
/* 1091 */             sigmaQ26 += Math.abs(this.imagZ[i] / (sinTheta26 * Math.pow(this.omegas[i], this.initialEstimates[1])));
/*      */           }
/* 1093 */           this.initialEstimates[2] = (sigmaQ26 / (2.0D * (this.numberOfFrequencies - indexMinImagZ)));
/*      */         }
/*      */         else {
/* 1096 */           this.initialEstimates[1] = (1.0D / (this.initialEstimates[0] * geometricFreqMean));
/* 1097 */           for (int i = 0; i < this.numberOfFrequencies; i++) sumQ26 += this.imagZ[i] / this.realZ[i];
/* 1098 */           sumQ26 /= this.numberOfFrequencies;
/* 1099 */           double theta26 = Math.abs(Math.atan(sumQ26));
/* 1100 */           double cosTheta26 = Math.cos(theta26);
/* 1101 */           double sinTheta26 = Math.sin(theta26);
/* 1102 */           this.initialEstimates[3] = (theta26 / 1.5707963267948966D);
/* 1103 */           double sigmaQ26 = 0.0D;
/* 1104 */           for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1105 */             sigmaQ26 += Math.abs(this.realZ[i] / (cosTheta26 * Math.pow(this.omegas[i], this.initialEstimates[1])));
/* 1106 */             sigmaQ26 += Math.abs(this.imagZ[i] / (sinTheta26 * Math.pow(this.omegas[i], this.initialEstimates[1])));
/*      */           }
/* 1108 */           this.initialEstimates[2] = (sigmaQ26 / (2.0D * this.numberOfFrequencies));
/*      */         }
/* 1110 */         break;
/* 1111 */       case 27:  this.initialEstimates = new double[this.numberOfParameters];
/* 1112 */         this.initialEstimates[0] = (maxRealZ / 2.0D);
/* 1113 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1114 */         double sumW27 = 0.0D;
/* 1115 */         if (indexMinImagZ < this.numberOfFrequencies - 3) {
/* 1116 */           this.initialEstimates[1] = (2.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1117 */           this.initialEstimates[3] = this.initialEstimates[1];
/* 1118 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1119 */             sumW27 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1120 */             sumW27 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1122 */           this.initialEstimates[4] = (sumW27 / (2.0D * (this.numberOfFrequencies - indexMinImagZ)));
/*      */         }
/*      */         else {
/* 1125 */           this.initialEstimates[1] = (2.0D / (this.initialEstimates[0] * geometricFreqMean));
/* 1126 */           this.initialEstimates[3] = this.initialEstimates[1];
/* 1127 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1128 */             sumW27 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1129 */             sumW27 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1131 */           this.initialEstimates[4] = (sumW27 / (2.0D * this.numberOfFrequencies));
/*      */         }
/* 1133 */         break;
/* 1134 */       case 28:  this.initialEstimates = new double[this.numberOfParameters];
/* 1135 */         this.initialEstimates[6] = minRealZ;
/* 1136 */         this.initialEstimates[0] = ((maxRealZ - minRealZ) / 2.0D);
/* 1137 */         this.initialEstimates[2] = this.initialEstimates[0];
/* 1138 */         double sumW28 = 0.0D;
/* 1139 */         if (indexMinImagZ < this.numberOfFrequencies - 3) {
/* 1140 */           this.initialEstimates[1] = (3.0D / (this.initialEstimates[0] * this.frequencies[indexMinImagZ]));
/* 1141 */           this.initialEstimates[3] = this.initialEstimates[1];
/* 1142 */           this.initialEstimates[5] = this.initialEstimates[1];
/* 1143 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1144 */             sumW28 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1145 */             sumW28 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1147 */           this.initialEstimates[4] = (sumW28 / (2.0D * (this.numberOfFrequencies - indexMinImagZ)));
/*      */         }
/*      */         else {
/* 1150 */           this.initialEstimates[1] = (3.0D / (this.initialEstimates[0] * geometricFreqMean));
/* 1151 */           this.initialEstimates[3] = this.initialEstimates[1];
/* 1152 */           this.initialEstimates[5] = this.initialEstimates[1];
/* 1153 */           for (int i = indexMinImagZ; i < this.numberOfFrequencies; i++) {
/* 1154 */             sumW28 += Math.abs(this.realZ[i] * Math.sqrt(this.omegas[i]));
/* 1155 */             sumW28 += Math.abs(this.imagZ[i] * Math.sqrt(this.omegas[i]));
/*      */           }
/* 1157 */           this.initialEstimates[4] = (sumW28 / (2.0D * this.numberOfFrequencies));
/*      */         }
/* 1159 */         break;
/* 1160 */       default:  throw new IllegalArgumentException("Automatically calculated initial estimates are only presntly available for models 1 to 28");
/*      */       }
/*      */       
/*      */       
/* 1164 */       this.initialSteps = new double[this.numberOfParameters];
/* 1165 */       for (int i = 0; i < this.numberOfParameters; i++) { this.initialSteps[i] = (Math.abs(this.initialEstimates[i]) * 0.1D);
/*      */       }
/*      */       
/* 1168 */       for (int i = 0; i < this.numberOfParameters; i++) {
/* 1169 */         if (this.initialSteps[i] == 0.0D) {
/* 1170 */           if (this.parameterSymbols[i].trim().substring(0, 1).equals("R")) this.initialSteps[i] = (maxRealZ * 0.01D);
/* 1171 */           if (this.parameterSymbols[i].trim().substring(0, 1).equals("C")) this.initialSteps[i] = (0.01D / (imagBig * this.frequencies[bigIndex]));
/* 1172 */           if (this.parameterSymbols[i].trim().substring(0, 1).equals("L")) this.initialSteps[i] = (imagBig * 0.01D / this.frequencies[bigIndex]);
/* 1173 */           if (this.parameterSymbols[i].trim().substring(0, 1).equals("W")) this.initialSteps[i] = (0.01D / (imagBig * Math.sqrt(this.frequencies[bigIndex])));
/* 1174 */           if (this.parameterSymbols[i].trim().substring(0, 2).equals("Fs")) this.initialSteps[i] = (0.01D / (imagBig * Math.sqrt(this.frequencies[bigIndex])));
/* 1175 */           if (this.parameterSymbols[i].trim().substring(0, 2).equals("Fd")) this.initialSteps[i] = 0.05D;
/* 1176 */           if (this.parameterSymbols[i].trim().substring(0, 2).equals("Qs")) this.initialSteps[i] = (0.01D / (imagBig * Math.sqrt(this.frequencies[bigIndex])));
/* 1177 */           if (this.parameterSymbols[i].trim().substring(0, 2).equals("Qa")) { this.initialSteps[i] = 0.005D;
/*      */           }
/*      */         }
/*      */       }
/* 1181 */       this.estimatesSet = true;
/*      */     }
/*      */     else {
/* 1184 */       this.estimatesNeeded = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public double[] getInitialEstimates()
/*      */   {
/* 1190 */     if (!this.estimatesSet) throw new IllegalArgumentException("No initial estimates have been entered or calculated");
/* 1191 */     return this.initialEstimates;
/*      */   }
/*      */   
/*      */   public String[] getCircuitComponents()
/*      */   {
/* 1196 */     return this.parameterSymbols;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addNewConstraint(int parameter, int direction, double boundary)
/*      */   {
/* 1203 */     this.constraints.add(new Integer(parameter));
/* 1204 */     this.constraints.add(new Integer(direction));
/* 1205 */     this.constraints.add(new Double(boundary));
/* 1206 */     this.numberOfAddedConstraints += 1;
/* 1207 */     this.constraintsAdded = true;
/*      */   }
/*      */   
/*      */   public void addNewConstraint(String parameterSymbol, int direction, double boundary) {
/* 1211 */     if (this.numberOfParameters == 0) throw new IllegalArgumentException("No model number or model parameters entered");
/* 1212 */     int parameterNumber = -1;
/* 1213 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 1214 */       if (this.parameterSymbols[i].trim().equals(parameterSymbol.trim())) parameterNumber = i;
/*      */     }
/* 1216 */     if (parameterNumber == -1) { throw new IllegalArgumentException("Parameter symbol, " + parameterSymbol + ", not found");
/*      */     }
/* 1218 */     this.constraints.add(new Integer(parameterNumber));
/* 1219 */     this.constraints.add(new Integer(direction));
/* 1220 */     this.constraints.add(new Double(boundary));
/* 1221 */     this.numberOfAddedConstraints += 1;
/* 1222 */     this.constraintsAdded = true;
/*      */   }
/*      */   
/*      */   public void removeDefaultConstraints()
/*      */   {
/* 1227 */     this.supressDefaultConstraints = true;
/*      */   }
/*      */   
/*      */   public void restoreDefaultConstraints()
/*      */   {
/* 1232 */     this.supressDefaultConstraints = false;
/*      */   }
/*      */   
/*      */   public void removeAddedConstraints()
/*      */   {
/* 1237 */     this.supressAddedConstraints = true;
/* 1238 */     this.constraintsAdded = false;
/*      */   }
/*      */   
/*      */   public void removeAllConstraints()
/*      */   {
/* 1243 */     this.supressDefaultConstraints = true;
/* 1244 */     this.supressAddedConstraints = true;
/* 1245 */     this.constraintsAdded = false;
/*      */   }
/*      */   
/*      */   public void resetMaximumNumberOfIterations(int max)
/*      */   {
/* 1250 */     this.maximumIterations = max;
/*      */   }
/*      */   
/*      */   public void resetTolerance(double tol)
/*      */   {
/* 1255 */     this.tolerance = tol;
/*      */   }
/*      */   
/*      */   public ArrayList<Object> getRegressionResultsAsArrayList()
/*      */   {
/* 1260 */     if (!this.regressionDone) regression();
/* 1261 */     return this.results;
/*      */   }
/*      */   
/*      */   public Vector<Object> getRegressionResultsAsVector()
/*      */   {
/* 1266 */     if (!this.regressionDone) regression();
/* 1267 */     int n = this.results.size();
/* 1268 */     Vector<Object> res = new Vector(n);
/* 1269 */     for (int i = 0; i < n; i++) res.add(this.results.get(i));
/* 1270 */     return res;
/*      */   }
/*      */   
/*      */   public Vector<Object> getRegressionResults()
/*      */   {
/* 1275 */     return getRegressionResults();
/*      */   }
/*      */   
/*      */ 
/*      */   private void setRegressionArrays()
/*      */   {
/* 1281 */     this.xRegression = new double[this.numberOfFrequencies];
/* 1282 */     this.yRegression = new double[2][this.numberOfFrequencies];
/* 1283 */     if (this.weightsSet) { this.wRegression = new double[2][this.numberOfFrequencies];
/*      */     }
/* 1285 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1286 */       this.xRegression[i] = this.omegas[i];
/* 1287 */       this.yRegression[0][i] = this.realZ[i];
/* 1288 */       this.yRegression[1][i] = this.imagZ[i];
/*      */     }
/*      */     
/* 1291 */     if (this.weightsSet) {
/* 1292 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1293 */         this.wRegression[0][i] = this.realZweights[i];
/* 1294 */         this.wRegression[1][i] = this.imagZweights[i];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<Object> regression()
/*      */   {
/* 1303 */     this.degreesOfFreedom = (this.numberOfFrequencies - this.numberOfParameters);
/* 1304 */     if (this.degreesOfFreedom <= 0) throw new IllegalArgumentException("Degrees of freedom, " + this.degreesOfFreedom + ", are less than 1");
/* 1305 */     if (!this.impedancesSet) { throw new IllegalArgumentException("No impedances or voltages have been entered");
/*      */     }
/*      */     
/* 1308 */     if ((!this.estimatesSet) && (!this.userModelSet)) { setInitialEstimates();
/*      */     }
/*      */     
/* 1311 */     setRegressionArrays();
/*      */     
/*      */ 
/* 1314 */     this.results = new ArrayList();
/* 1315 */     this.results.add(new Integer(this.numberOfFrequencies));
/* 1316 */     this.results.add(new Integer(this.numberOfParameters));
/* 1317 */     this.results.add(new Integer(this.degreesOfFreedom));
/* 1318 */     this.results.add(this.parameterSymbols);
/* 1319 */     this.results.add((double[])this.initialEstimates.clone());
/* 1320 */     this.results.add((double[])this.initialSteps.clone());
/*      */     
/*      */ 
/* 1323 */     if (this.weightsSet) {
/* 1324 */       enterData(this.xRegression, this.yRegression, this.wRegression);
/*      */     }
/*      */     else {
/* 1327 */       enterData(this.xRegression, this.yRegression);
/*      */     }
/*      */     
/*      */ 
/* 1331 */     if (this.userModelSet) {
/* 1332 */       ImpedSpecRegressionFunction2 function = new ImpedSpecRegressionFunction2();
/* 1333 */       function.numberOfFrequencies = this.numberOfFrequencies;
/* 1334 */       function.isModel = this.userModel;
/* 1335 */       this.regressionFunction = function;
/*      */     } else {
/* 1337 */       ImpedSpecRegressionFunction1 function = new ImpedSpecRegressionFunction1();
/* 1338 */       function.numberOfFrequencies = this.numberOfFrequencies;
/* 1339 */       function.modelNumber = this.modelNumber;
/* 1340 */       this.regressionFunction = function;
/*      */     }
/*      */     
/*      */ 
/* 1344 */     int[] param = null;
/* 1345 */     int[] direct = null;
/* 1346 */     double[] bound = null;
/* 1347 */     if (this.constraintsAdded) {
/* 1348 */       param = new int[this.numberOfAddedConstraints];
/* 1349 */       direct = new int[this.numberOfAddedConstraints];
/* 1350 */       bound = new double[this.numberOfAddedConstraints];
/* 1351 */       int index = 0;
/* 1352 */       for (int i = 0; i < this.numberOfAddedConstraints; i++) {
/* 1353 */         int parameter = ((Integer)this.constraints.get(index)).intValue();
/* 1354 */         param[i] = parameter;
/* 1355 */         index++;
/* 1356 */         int direction = ((Integer)this.constraints.get(index)).intValue();
/* 1357 */         direct[i] = direction;
/* 1358 */         index++;
/* 1359 */         double boundary = ((Double)this.constraints.get(index)).doubleValue();
/* 1360 */         bound[i] = boundary;
/* 1361 */         index++;
/* 1362 */         addConstraint(parameter, direction, boundary);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1367 */     if (!this.supressDefaultConstraints)
/*      */     {
/* 1369 */       for (int i = 0; i < this.numberOfParameters; i++) {
/* 1370 */         double lower = 0.0D;
/* 1371 */         double upper = 1.0D;
/* 1372 */         if (this.constraintsAdded) {
/* 1373 */           for (int j = 0; j < this.numberOfAddedConstraints; j++) {
/* 1374 */             if (param[j] == i) {
/* 1375 */               if (direct[j] == 1) {
/* 1376 */                 upper = bound[j];
/*      */               }
/*      */               else {
/* 1379 */                 lower = bound[j];
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1384 */         addConstraint(i, -1, lower);
/* 1385 */         if (this.parameterSymbols[i].trim().substring(0, 1).equals("Qa")) { addConstraint(i, 1, upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1390 */     simplex2(this.regressionFunction, (double[])this.initialEstimates.clone(), (double[])this.initialSteps.clone(), this.tolerance, this.maximumIterations);
/*      */     
/*      */ 
/* 1393 */     this.numberOfIterations1 = getNiter();
/* 1394 */     double[] estimates = getCoeff();
/* 1395 */     double[] steps = new double[this.numberOfParameters];
/* 1396 */     for (int i = 0; i < this.numberOfParameters; i++) { steps[i] = (Math.abs(estimates[i]) * 0.1D);
/*      */     }
/* 1398 */     simplex2(this.regressionFunction, estimates, steps, this.tolerance, this.maximumIterations);
/*      */     
/*      */ 
/* 1401 */     this.bestEstimates = getCoeff();
/* 1402 */     this.results.add(this.bestEstimates);
/* 1403 */     this.standardDeviations = getCoeffSd();
/* 1404 */     this.results.add(this.standardDeviations);
/* 1405 */     this.coefficientsOfVariation = getCoeffVar();
/* 1406 */     this.results.add(this.coefficientsOfVariation);
/* 1407 */     this.correlationCoefficients = getCorrCoeffMatrix();
/* 1408 */     this.results.add(this.correlationCoefficients);
/* 1409 */     double[][] gradients = new double[this.numberOfParameters][2];
/* 1410 */     if (getGrad() == null) {
/* 1411 */       for (int i = 0; i < this.numberOfParameters; i++) {
/* 1412 */         this.preMinimumGradients[i] = NaN.0D;
/* 1413 */         this.postMinimumGradients[i] = NaN.0D;
/*      */       }
/*      */     }
/*      */     else {
/* 1417 */       gradients = getGrad();
/* 1418 */       for (int i = 0; i < this.numberOfParameters; i++) {
/* 1419 */         this.preMinimumGradients[i] = gradients[i][0];
/* 1420 */         this.postMinimumGradients[i] = gradients[i][1];
/*      */       }
/*      */     }
/*      */     
/* 1424 */     this.results.add(this.preMinimumGradients);
/* 1425 */     this.results.add(this.postMinimumGradients);
/* 1426 */     this.sumOfSquares = getSumOfSquares();
/* 1427 */     this.results.add(new Double(this.sumOfSquares));
/* 1428 */     this.reducedSumOfSquares = (this.sumOfSquares / this.degreesOfFreedom);
/* 1429 */     this.results.add(new Double(this.reducedSumOfSquares));
/* 1430 */     if (this.weightsSet) {
/* 1431 */       this.chiSquare = getChiSquare();
/* 1432 */       this.results.add(new Double(this.chiSquare));
/* 1433 */       this.reducedChiSquare = getReducedChiSquare();
/* 1434 */       this.results.add(new Double(this.reducedChiSquare));
/*      */     }
/*      */     else {
/* 1437 */       this.results.add(null);
/* 1438 */       this.results.add(null);
/*      */     }
/* 1440 */     this.numberOfIterations2 = getNiter();
/* 1441 */     this.results.add(new Integer(this.numberOfIterations1));
/* 1442 */     this.results.add(new Integer(this.numberOfIterations2));
/* 1443 */     this.results.add(new Integer(this.maximumIterations));
/* 1444 */     this.results.add(this.dataEnteredType[this.dataEnteredTypePointer]);
/*      */     
/* 1446 */     this.results.add(this.frequencies);
/* 1447 */     this.results.add(this.log10frequencies);
/* 1448 */     this.results.add(this.omegas);
/* 1449 */     this.results.add(this.log10omegas);
/* 1450 */     this.results.add(this.impedanceMagnitudes);
/* 1451 */     this.results.add(this.impedancePhasesRad);
/* 1452 */     this.results.add(this.impedancePhasesDeg);
/* 1453 */     this.results.add(this.impedances);
/* 1454 */     this.results.add(this.realZ);
/* 1455 */     this.results.add(this.imagZ);
/*      */     
/* 1457 */     double[] calculatedY = getYcalc();
/* 1458 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1459 */       this.calculatedRealZ[i] = calculatedY[i];
/* 1460 */       this.calculatedImagZ[i] = calculatedY[(i + this.numberOfFrequencies)];
/*      */     }
/* 1462 */     this.results.add(this.calculatedRealZ);
/* 1463 */     this.results.add(this.calculatedImagZ);
/*      */     
/*      */ 
/* 1466 */     double[] residuals = getResiduals();
/* 1467 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1468 */       this.realZresiduals[i] = residuals[i];
/* 1469 */       this.imagZresiduals[i] = residuals[(i + this.numberOfFrequencies)];
/*      */     }
/* 1471 */     this.results.add(this.realZresiduals);
/* 1472 */     this.results.add(this.imagZresiduals);
/*      */     
/* 1474 */     if (this.weightsSet) {
/* 1475 */       switch (this.dataEnteredTypePointer) {
/* 1476 */       case 0:  this.results.add(this.realVweights);
/* 1477 */         this.results.add(this.imagVweights);
/* 1478 */         break;
/* 1479 */       case 1:  this.results.add(this.voltageWeights);
/* 1480 */         this.results.add(null);
/* 1481 */         break;
/* 1482 */       case 2:  this.results.add(this.voltageMagnitudeWeights);
/* 1483 */         this.results.add(this.voltagePhaseWeightsRad);
/* 1484 */         break;
/* 1485 */       case 3:  this.results.add(this.voltageMagnitudeWeights);
/* 1486 */         this.results.add(this.voltagePhaseWeightsDeg);
/* 1487 */         break;
/* 1488 */       case 4:  this.results.add(this.realZweights);
/* 1489 */         this.results.add(this.imagZweights);
/* 1490 */         break;
/* 1491 */       case 5:  this.results.add(this.impedanceWeights);
/* 1492 */         this.results.add(null);
/* 1493 */         break;
/* 1494 */       case 6:  this.results.add(this.impedanceMagnitudeWeights);
/* 1495 */         this.results.add(this.impedancePhaseWeightsRad);
/* 1496 */         break;
/* 1497 */       case 7:  this.results.add(this.impedanceMagnitudeWeights);
/* 1498 */         this.results.add(this.impedancePhaseWeightsDeg);
/* 1499 */         break;
/* 1500 */       default:  this.results.add(null);
/* 1501 */         this.results.add(null);
/*      */       }
/* 1503 */       this.results.add(this.realZweights);
/* 1504 */       this.results.add(this.imagZweights);
/*      */     }
/*      */     else {
/* 1507 */       for (int i = 0; i < 4; i++) { this.results.add(null);
/*      */       }
/*      */     }
/* 1510 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1511 */       this.calculatedImpedances[i] = new Complex(this.calculatedRealZ[i], this.calculatedImagZ[i]);
/* 1512 */       this.calculatedImpedanceMagnitudes[i] = this.calculatedImpedances[i].abs();
/* 1513 */       this.calculatedImpedancePhasesRad[i] = this.calculatedImpedances[i].arg();
/* 1514 */       this.calculatedImpedancePhasesDeg[i] = Math.toDegrees(this.calculatedImpedancePhasesRad[i]);
/*      */     }
/* 1516 */     this.results.add(this.calculatedImpedances);
/* 1517 */     this.results.add(this.calculatedImpedanceMagnitudes);
/* 1518 */     this.results.add(this.calculatedImpedancePhasesRad);
/* 1519 */     this.results.add(this.calculatedImpedancePhasesDeg);
/*      */     
/* 1521 */     if ((this.appliedVoltageSet) && (this.referenceSet)) {
/* 1522 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1523 */         this.calculatedVoltages[i] = this.appliedVoltage.times(this.calculatedImpedances[i]).over(this.calculatedImpedances[i].plus(this.referenceImpedance));
/* 1524 */         this.calculatedRealV[i] = this.calculatedVoltages[i].getReal();
/* 1525 */         this.calculatedImagV[i] = this.calculatedVoltages[i].getImag();
/* 1526 */         this.calculatedVoltageMagnitudes[i] = this.calculatedVoltages[i].abs();
/* 1527 */         this.calculatedVoltagePhasesRad[i] = this.calculatedVoltages[i].arg();
/* 1528 */         this.calculatedVoltagePhasesDeg[i] = Math.toDegrees(this.calculatedVoltagePhasesRad[i]);
/*      */       }
/* 1530 */       this.results.add(this.calculatedVoltages);
/* 1531 */       this.results.add(this.calculatedRealV);
/* 1532 */       this.results.add(this.calculatedImagV);
/* 1533 */       this.results.add(this.calculatedVoltageMagnitudes);
/* 1534 */       this.results.add(this.calculatedVoltagePhasesRad);
/* 1535 */       this.results.add(this.calculatedVoltagePhasesDeg);
/*      */     }
/*      */     else {
/* 1538 */       for (int i = 0; i < 6; i++) { this.results.add(null);
/*      */       }
/*      */     }
/* 1541 */     this.regressionDone = true;
/*      */     
/* 1543 */     return this.results;
/*      */   }
/*      */   
/*      */   public double[] getBestEstimates()
/*      */   {
/* 1548 */     if (!this.regressionDone) regression();
/* 1549 */     return this.bestEstimates;
/*      */   }
/*      */   
/*      */   public double[] getStandardDeviations()
/*      */   {
/* 1554 */     if (!this.regressionDone) regression();
/* 1555 */     return this.standardDeviations;
/*      */   }
/*      */   
/*      */   public int getFirstNumberOfIterations()
/*      */   {
/* 1560 */     return this.numberOfIterations1;
/*      */   }
/*      */   
/*      */   public int getSecondNumberOfIterations()
/*      */   {
/* 1565 */     return this.numberOfIterations2;
/*      */   }
/*      */   
/*      */   public double getTolerance()
/*      */   {
/* 1570 */     return this.tolerance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLinearPlot()
/*      */   {
/* 1577 */     this.logOrLinear = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLog10Plot()
/*      */   {
/* 1583 */     this.logOrLinear = true;
/*      */   }
/*      */   
/*      */   private void calculateLineFrequencies()
/*      */   {
/* 1588 */     double lowestFrequency = Fmath.minimum(this.frequencies);
/* 1589 */     double highestFrequency = Fmath.maximum(this.frequencies);
/* 1590 */     if (this.logOrLinear) {
/* 1591 */       double logLow = Fmath.log10(lowestFrequency);
/* 1592 */       double logHigh = Fmath.log10(highestFrequency);
/* 1593 */       double increment = (logHigh - logLow) / (this.numberOfLineFrequencies - 1);
/* 1594 */       this.lineFrequencies = new double[this.numberOfLineFrequencies];
/* 1595 */       this.log10lineFrequencies = new double[this.numberOfLineFrequencies];
/* 1596 */       this.log10lineFrequencies[0] = logLow;
/* 1597 */       this.log10lineFrequencies[(this.numberOfLineFrequencies - 1)] = logHigh;
/* 1598 */       for (int i = 1; i < this.numberOfLineFrequencies - 1; i++) this.log10lineFrequencies[i] = (this.log10lineFrequencies[(i - 1)] + increment);
/* 1599 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) this.lineFrequencies[i] = Math.pow(10.0D, this.log10lineFrequencies[i]);
/*      */     }
/*      */     else
/*      */     {
/* 1603 */       double increment = (highestFrequency - lowestFrequency) / (this.numberOfLineFrequencies - 1);
/* 1604 */       this.lineFrequencies = new double[this.numberOfLineFrequencies];
/* 1605 */       this.lineFrequencies[0] = lowestFrequency;
/* 1606 */       this.log10lineFrequencies = new double[this.numberOfLineFrequencies];
/* 1607 */       this.lineFrequencies[(this.numberOfLineFrequencies - 1)] = highestFrequency;
/* 1608 */       for (int i = 1; i < this.numberOfLineFrequencies - 1; i++) this.lineFrequencies[i] = (this.lineFrequencies[(i - 1)] + increment);
/* 1609 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) this.log10lineFrequencies[i] = Fmath.log10(this.lineFrequencies[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   private String[] dateAndTime()
/*      */   {
/* 1615 */     Date d = new Date();
/* 1616 */     String[] ret = new String[2];
/* 1617 */     ret[0] = DateFormat.getDateInstance().format(d);
/* 1618 */     ret[1] = DateFormat.getTimeInstance().format(d);
/* 1619 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> plotColeCole()
/*      */   {
/* 1625 */     String[] dAndT = dateAndTime();
/* 1626 */     String graphTitle1 = "ImpedSpecRegression program:  Cole - Cole plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/* 1627 */     String graphTitle2 = this.regressionTitle;
/*      */     
/* 1629 */     if (!this.regressionDone) { regression();
/*      */     }
/* 1631 */     calculateLineFrequencies();
/*      */     
/* 1633 */     double[][] data = PlotGraph.data(2, this.numberOfLineFrequencies);
/*      */     
/* 1635 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1636 */       data[0][i] = this.realZ[(this.numberOfFrequencies - i - 1)];
/* 1637 */       data[1][i] = (-this.imagZ[(this.numberOfFrequencies - i - 1)]);
/*      */     }
/*      */     
/* 1640 */     if (this.userModelSet) {
/* 1641 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1642 */         data[2][i] = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[(this.numberOfLineFrequencies - i - 1)] * 2.0D * 3.141592653589793D).getReal();
/* 1643 */         data[3][i] = (-this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[(this.numberOfLineFrequencies - i - 1)] * 2.0D * 3.141592653589793D).getImag());
/*      */       }
/*      */       
/*      */     } else {
/* 1647 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1648 */         data[2][i] = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[(this.numberOfLineFrequencies - i - 1)] * 2.0D * 3.141592653589793D, this.modelNumber).getReal();
/* 1649 */         data[3][i] = (-Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[(this.numberOfLineFrequencies - i - 1)] * 2.0D * 3.141592653589793D, this.modelNumber).getImag());
/*      */       }
/*      */     }
/*      */     
/* 1653 */     PlotGraph pg = new PlotGraph(data);
/* 1654 */     int[] lineOpt = { 0, 3 };
/* 1655 */     pg.setLine(lineOpt);
/* 1656 */     int[] pointOpt = { 1, 0 };
/* 1657 */     pg.setPoint(pointOpt);
/* 1658 */     pg.setGraphTitle(graphTitle1);
/* 1659 */     pg.setGraphTitle2(graphTitle2);
/* 1660 */     pg.setXaxisLegend("Real[Impedance / ohms]");
/* 1661 */     pg.setYaxisLegend("-Imag[Impedance / ohms]");
/* 1662 */     pg.plot();
/*      */     
/* 1664 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> plotImpedanceMagnitudes()
/*      */   {
/* 1670 */     String[] dAndT = dateAndTime();
/* 1671 */     String graphTitle1 = "ImpedSpecRegression program:  Impedance magnitude versus frequency plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/* 1672 */     String graphTitle2 = this.regressionTitle;
/*      */     
/* 1674 */     if (!this.regressionDone) { regression();
/*      */     }
/* 1676 */     calculateLineFrequencies();
/*      */     
/*      */ 
/* 1679 */     double[][] data = PlotGraph.data(2, this.numberOfLineFrequencies);
/*      */     
/* 1681 */     if (this.logOrLinear) {
/* 1682 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1683 */         data[0][i] = this.log10frequencies[i];
/* 1684 */         data[1][i] = this.impedanceMagnitudes[i];
/*      */       }
/*      */       
/*      */     } else {
/* 1688 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1689 */         data[0][i] = this.frequencies[i];
/* 1690 */         data[1][i] = this.impedanceMagnitudes[i];
/*      */       }
/*      */     }
/*      */     
/* 1694 */     if (this.logOrLinear) {
/* 1695 */       if (this.userModelSet) {
/* 1696 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1697 */           data[2][i] = this.log10lineFrequencies[i];
/* 1698 */           Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1699 */           data[3][i] = imped.abs();
/*      */         }
/*      */         
/*      */       } else {
/* 1703 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1704 */           data[2][i] = this.log10lineFrequencies[i];
/* 1705 */           Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1706 */           data[3][i] = imped.abs();
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1711 */     else if (this.userModelSet) {
/* 1712 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1713 */         data[2][i] = this.lineFrequencies[i];
/* 1714 */         Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1715 */         data[3][i] = imped.abs();
/*      */       }
/*      */       
/*      */     } else {
/* 1719 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1720 */         data[2][i] = this.lineFrequencies[i];
/* 1721 */         Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1722 */         data[3][i] = imped.abs();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1727 */     PlotGraph pg = new PlotGraph(data);
/* 1728 */     int[] lineOpt = { 0, 3 };
/* 1729 */     pg.setLine(lineOpt);
/* 1730 */     int[] pointOpt = { 1, 0 };
/* 1731 */     pg.setPoint(pointOpt);
/* 1732 */     pg.setGraphTitle(graphTitle1);
/* 1733 */     pg.setGraphTitle2(graphTitle2);
/* 1734 */     if (this.logOrLinear) {
/* 1735 */       pg.setXaxisLegend("Log10[Frequency / Hz]");
/*      */     }
/*      */     else {
/* 1738 */       pg.setXaxisLegend("Frequency / Hz");
/*      */     }
/* 1740 */     pg.setYaxisLegend("Impedance Magnitude");
/* 1741 */     pg.plot();
/*      */     
/* 1743 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> plotImpedancePhases()
/*      */   {
/* 1749 */     String[] dAndT = dateAndTime();
/* 1750 */     String graphTitle1 = "ImpedSpecRegression program:  Impedance phase versus frequency plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/* 1751 */     String graphTitle2 = this.regressionTitle;
/*      */     
/* 1753 */     if (!this.regressionDone) { regression();
/*      */     }
/* 1755 */     calculateLineFrequencies();
/*      */     
/*      */ 
/* 1758 */     double[][] data = PlotGraph.data(2, this.numberOfLineFrequencies);
/*      */     
/* 1760 */     if (this.logOrLinear) {
/* 1761 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1762 */         data[0][i] = this.log10frequencies[i];
/* 1763 */         data[1][i] = this.impedancePhasesDeg[i];
/*      */       }
/*      */       
/*      */     } else {
/* 1767 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1768 */         data[0][i] = this.frequencies[i];
/* 1769 */         data[1][i] = this.impedancePhasesDeg[i];
/*      */       }
/*      */     }
/*      */     
/* 1773 */     if (this.logOrLinear) {
/* 1774 */       if (this.userModelSet) {
/* 1775 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1776 */           data[2][i] = this.log10lineFrequencies[i];
/* 1777 */           Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1778 */           data[3][i] = Math.toDegrees(imped.arg());
/*      */         }
/*      */         
/*      */       } else {
/* 1782 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1783 */           data[2][i] = this.log10lineFrequencies[i];
/* 1784 */           Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1785 */           data[3][i] = Math.toDegrees(imped.arg());
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1790 */     else if (this.userModelSet) {
/* 1791 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1792 */         data[2][i] = this.lineFrequencies[i];
/* 1793 */         Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1794 */         data[3][i] = Math.toDegrees(imped.arg());
/*      */       }
/*      */       
/*      */     } else {
/* 1798 */       for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1799 */         data[2][i] = this.lineFrequencies[i];
/* 1800 */         Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1801 */         data[3][i] = Math.toDegrees(imped.arg());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1806 */     PlotGraph pg = new PlotGraph(data);
/* 1807 */     int[] lineOpt = { 0, 3 };
/* 1808 */     pg.setLine(lineOpt);
/* 1809 */     int[] pointOpt = { 1, 0 };
/* 1810 */     pg.setPoint(pointOpt);
/* 1811 */     pg.setGraphTitle(graphTitle1);
/* 1812 */     pg.setGraphTitle2(graphTitle2);
/* 1813 */     if (this.logOrLinear) {
/* 1814 */       pg.setXaxisLegend("Log10[Frequency / Hz]");
/*      */     }
/*      */     else {
/* 1817 */       pg.setXaxisLegend("Frequency / Hz");
/*      */     }
/* 1819 */     pg.setYaxisLegend("Impedance Phase / degrees");
/* 1820 */     pg.plot();
/*      */     
/* 1822 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<Object> plotVoltageMagnitudes()
/*      */   {
/* 1829 */     if (!this.regressionDone) { regression();
/*      */     }
/* 1831 */     if ((this.referenceSet) && (this.appliedVoltageSet)) {
/* 1832 */       String[] dAndT = dateAndTime();
/* 1833 */       String graphTitle1 = "ImpedSpecRegression program:  Voltage magnitude versus frequency plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/* 1834 */       String graphTitle2 = this.regressionTitle;
/*      */       
/* 1836 */       calculateLineFrequencies();
/*      */       
/* 1838 */       double[][] data = PlotGraph.data(2, this.numberOfLineFrequencies);
/*      */       
/* 1840 */       if (this.logOrLinear) {
/* 1841 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1842 */           data[0][i] = this.log10frequencies[i];
/* 1843 */           data[1][i] = this.voltageMagnitudes[i];
/*      */         }
/*      */         
/*      */       } else {
/* 1847 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1848 */           data[0][i] = this.frequencies[i];
/* 1849 */           data[1][i] = this.voltageMagnitudes[i];
/*      */         }
/*      */       }
/*      */       
/* 1853 */       if (this.logOrLinear) {
/* 1854 */         if (this.userModelSet) {
/* 1855 */           for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1856 */             data[2][i] = this.log10lineFrequencies[i];
/* 1857 */             Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1858 */             Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1859 */             data[3][i] = volt.abs();
/*      */           }
/*      */           
/*      */         } else {
/* 1863 */           for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1864 */             data[2][i] = this.log10lineFrequencies[i];
/* 1865 */             Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1866 */             Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1867 */             data[3][i] = volt.abs();
/*      */           }
/*      */           
/*      */         }
/*      */       }
/* 1872 */       else if (this.userModelSet) {
/* 1873 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1874 */           data[2][i] = this.lineFrequencies[i];
/* 1875 */           Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1876 */           Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1877 */           data[3][i] = volt.abs();
/*      */         }
/*      */         
/*      */       } else {
/* 1881 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1882 */           data[2][i] = this.lineFrequencies[i];
/* 1883 */           Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1884 */           Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1885 */           data[3][i] = volt.abs();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1890 */       PlotGraph pg = new PlotGraph(data);
/* 1891 */       int[] lineOpt = { 0, 3 };
/* 1892 */       pg.setLine(lineOpt);
/* 1893 */       int[] pointOpt = { 1, 0 };
/* 1894 */       pg.setPoint(pointOpt);
/* 1895 */       pg.setGraphTitle(graphTitle1);
/* 1896 */       pg.setGraphTitle2(graphTitle2);
/* 1897 */       if (this.logOrLinear) {
/* 1898 */         pg.setXaxisLegend("Log10[Frequency / Hz]");
/*      */       }
/*      */       else {
/* 1901 */         pg.setXaxisLegend("Frequency / Hz");
/*      */       }
/* 1903 */       pg.setYaxisLegend("Voltage Magnitude");
/* 1904 */       pg.plot();
/*      */     }
/*      */     else {
/* 1907 */       System.out.println("The voltage magnitudes cannot be plotted as no reference impedance or applied voltage has been entered");
/*      */     }
/*      */     
/* 1910 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> plotVoltagePhases()
/*      */   {
/* 1916 */     if (!this.regressionDone) { regression();
/*      */     }
/* 1918 */     if ((this.referenceSet) && (this.appliedVoltageSet)) {
/* 1919 */       String[] dAndT = dateAndTime();
/* 1920 */       String graphTitle1 = "ImpedSpecRegression program:  Voltage phase versus frequency plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/* 1921 */       String graphTitle2 = this.regressionTitle;
/*      */       
/* 1923 */       calculateLineFrequencies();
/*      */       
/* 1925 */       double[][] data = PlotGraph.data(2, this.numberOfLineFrequencies);
/*      */       
/* 1927 */       if (this.logOrLinear) {
/* 1928 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1929 */           data[0][i] = this.log10frequencies[i];
/* 1930 */           data[1][i] = this.voltagePhasesDeg[i];
/*      */         }
/*      */         
/*      */       } else {
/* 1934 */         for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 1935 */           data[0][i] = this.frequencies[i];
/* 1936 */           data[1][i] = this.voltagePhasesDeg[i];
/*      */         }
/*      */       }
/*      */       
/* 1940 */       if (this.logOrLinear) {
/* 1941 */         if (this.userModelSet) {
/* 1942 */           for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1943 */             data[2][i] = this.log10lineFrequencies[i];
/* 1944 */             Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1945 */             Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1946 */             data[3][i] = Math.toDegrees(volt.arg());
/*      */           }
/*      */           
/*      */         } else {
/* 1950 */           for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1951 */             data[2][i] = this.log10lineFrequencies[i];
/* 1952 */             Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1953 */             Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1954 */             data[3][i] = Math.toDegrees(volt.arg());
/*      */           }
/*      */           
/*      */         }
/*      */       }
/* 1959 */       else if (this.userModelSet) {
/* 1960 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1961 */           data[2][i] = this.lineFrequencies[i];
/* 1962 */           Complex imped = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D);
/* 1963 */           Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1964 */           data[3][i] = Math.toDegrees(volt.arg());
/*      */         }
/*      */         
/*      */       } else {
/* 1968 */         for (int i = 0; i < this.numberOfLineFrequencies; i++) {
/* 1969 */           data[2][i] = this.lineFrequencies[i];
/* 1970 */           Complex imped = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[i] * 2.0D * 3.141592653589793D, this.modelNumber);
/* 1971 */           Complex volt = imped.times(this.appliedVoltage).over(this.referenceImpedance.plus(imped));
/* 1972 */           data[3][i] = Math.toDegrees(volt.arg());
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1977 */       PlotGraph pg = new PlotGraph(data);
/* 1978 */       int[] lineOpt = { 0, 3 };
/* 1979 */       pg.setLine(lineOpt);
/* 1980 */       int[] pointOpt = { 1, 0 };
/* 1981 */       pg.setPoint(pointOpt);
/* 1982 */       pg.setGraphTitle(graphTitle1);
/* 1983 */       pg.setGraphTitle2(graphTitle2);
/* 1984 */       if (this.logOrLinear) {
/* 1985 */         pg.setXaxisLegend("Log10[Frequency / Hz]");
/*      */       }
/*      */       else {
/* 1988 */         pg.setXaxisLegend("Frequency / Hz");
/*      */       }
/* 1990 */       pg.setYaxisLegend("Voltage Phases / degrees");
/* 1991 */       pg.plot();
/*      */     }
/*      */     else {
/* 1994 */       System.out.println("The voltage magnitudes cannot be plotted as no reference impedance or applied voltage has been entered");
/*      */     }
/*      */     
/* 1997 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<Object> printToTextFile()
/*      */   {
/* 2004 */     String fileName = "ImpedSpecRegressionOutput.txt";
/* 2005 */     this.fileType = true;
/* 2006 */     return printToTextFile(fileName);
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> printToTextFile(String fileName)
/*      */   {
/* 2012 */     if (!this.regressionDone) { regression();
/*      */     }
/* 2014 */     int field = 11;
/* 2015 */     int trunc = 4;
/*      */     
/*      */ 
/* 2018 */     fileName = fileName.trim();
/* 2019 */     int dotPosition = fileName.indexOf('.');
/* 2020 */     if (dotPosition == -1) { fileName = fileName + ".txt";
/*      */     }
/*      */     
/* 2023 */     FileOutput fout = null;
/* 2024 */     if (this.fileType) {
/* 2025 */       fout = new FileOutput(fileName, 'n');
/*      */     }
/*      */     else {
/* 2028 */       fout = new FileOutput(fileName);
/*      */     }
/*      */     
/*      */ 
/* 2032 */     fout.println("ImpedSpecRegression Program Output File:  " + this.regressionTitle);
/* 2033 */     fout.dateAndTimeln(fileName);
/* 2034 */     fout.println();
/* 2035 */     if (this.modelSet) {
/* 2036 */       fout.println("Circuit - model number " + this.modelNumber);
/*      */     }
/*      */     else {
/* 2039 */       fout.println("Circuit supplied by the user");
/*      */     }
/* 2041 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 2045 */     fout.println("Circuit Parameters");
/* 2046 */     fout.println("Best Estimates");
/*      */     
/* 2048 */     fout.print("Parameter", field);
/* 2049 */     fout.print("Best", field);
/* 2050 */     fout.print("Standard", field);
/* 2051 */     fout.print("Coeff. of", field);
/* 2052 */     fout.print("Pre-", field);
/* 2053 */     fout.println("Post-");
/*      */     
/* 2055 */     fout.print("   ", field);
/* 2056 */     fout.print("estimate", field);
/* 2057 */     fout.print("deviation", field);
/* 2058 */     fout.print("variation", field);
/* 2059 */     fout.print("gradient", field);
/* 2060 */     fout.println("gradient");
/*      */     
/* 2062 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 2063 */       fout.print(this.parameterSymbols[i], field);
/* 2064 */       fout.print(Fmath.truncate(this.bestEstimates[i], trunc), field);
/* 2065 */       fout.print(Fmath.truncate(this.standardDeviations[i], trunc), field);
/* 2066 */       fout.print(Fmath.truncate(this.coefficientsOfVariation[i], trunc), field);
/* 2067 */       fout.print(Fmath.truncate(this.preMinimumGradients[i], trunc), field);
/* 2068 */       fout.println(Fmath.truncate(this.postMinimumGradients[i], trunc));
/*      */     }
/* 2070 */     fout.println();
/*      */     
/* 2072 */     fout.println("Initial Estimates");
/*      */     
/* 2074 */     fout.print("Parameter", field);
/* 2075 */     fout.print("Initial", field);
/* 2076 */     fout.println("initial");
/*      */     
/* 2078 */     fout.print("   ", field);
/* 2079 */     fout.print("estimate", field);
/* 2080 */     fout.println("step size");
/*      */     
/* 2082 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 2083 */       fout.print(this.parameterSymbols[i], field);
/* 2084 */       fout.print(Fmath.truncate(this.initialEstimates[i], trunc), field);
/* 2085 */       fout.println(Fmath.truncate(this.initialSteps[i], trunc));
/*      */     }
/* 2087 */     fout.println();
/*      */     
/*      */ 
/* 2090 */     fout.println("Sum of squares of the Real[Z] and Imag[Z] residuals:         " + Fmath.truncate(this.sumOfSquares, trunc));
/* 2091 */     fout.println("Reduced sum of squares of the Real[Z] and Imag[Z] residuals: " + Fmath.truncate(this.sumOfSquares / this.degreesOfFreedom, trunc));
/* 2092 */     fout.println("Degrees of freedom: " + this.degreesOfFreedom);
/* 2093 */     if (this.weightsSet) {
/* 2094 */       fout.println("Chi square:         " + Fmath.truncate(this.chiSquare, trunc));
/* 2095 */       fout.println("Reduced chi square: " + Fmath.truncate(this.reducedChiSquare, trunc));
/*      */     }
/* 2097 */     fout.println("Number of iterations taken in the first regression:      " + this.numberOfIterations1);
/* 2098 */     fout.println("Number of iterations taken in the second regression:     " + this.numberOfIterations2);
/* 2099 */     fout.println("Maximum number of iterations allowed in each regression: " + this.maximumIterations);
/* 2100 */     fout.println();
/*      */     
/*      */ 
/* 2103 */     if (this.appliedVoltageSet) fout.println("Applied voltage: " + this.appliedVoltage.getReal());
/* 2104 */     if (this.referenceSet) fout.println("Reference impedance: " + this.referenceImpedance);
/* 2105 */     fout.println();
/*      */     
/*      */ 
/* 2108 */     field = 14;
/* 2109 */     fout.println("Fitted and entered data [frequencies, calculated impedances, data as entered]");
/* 2110 */     fout.print("Entered data type:  ");
/* 2111 */     fout.println(this.dataEnteredType[this.dataEnteredTypePointer]);
/* 2112 */     fout.println();
/*      */     
/* 2114 */     fout.print("Frequency", field);
/* 2115 */     fout.print("Experimental", field);
/* 2116 */     fout.print("Calculated", field);
/* 2117 */     fout.print("Experimental", field);
/* 2118 */     fout.print("Calculated", field);
/*      */     
/* 2120 */     switch (this.dataEnteredTypePointer) {
/* 2121 */     case 0:  fout.print("Real", field);
/* 2122 */       fout.print("Imag", field);
/* 2123 */       break;
/* 2124 */     case 1:  fout.print("Complex", field);
/* 2125 */       break;
/* 2126 */     case 2:  fout.print("Magnitude", field);
/* 2127 */       fout.print("Phase (rad)", field);
/* 2128 */       break;
/* 2129 */     case 3:  fout.print("Magnitude", field);
/* 2130 */       fout.print("Phase (deg)", field);
/* 2131 */       break;
/* 2132 */     case 4:  fout.print("Real", field);
/* 2133 */       fout.print("Imag", field);
/* 2134 */       break;
/* 2135 */     case 5:  fout.print("Complex", field);
/* 2136 */       break;
/* 2137 */     case 6:  fout.print("Magnitude", field);
/* 2138 */       fout.print("Phase (rad)", field);
/* 2139 */       break;
/* 2140 */     case 7:  fout.print("Magnitude", field);
/* 2141 */       fout.print("Phase (deg)", field);
/*      */     }
/*      */     
/* 2144 */     fout.println();
/*      */     
/* 2146 */     fout.print("Frequency", field);
/* 2147 */     fout.print("Real[Z]", field);
/* 2148 */     fout.print("Real[Z]", field);
/* 2149 */     fout.print("Imag[Z]", field);
/* 2150 */     fout.print("Imag[Z]", field);
/* 2151 */     switch (this.dataEnteredTypePointer) {
/* 2152 */     case 0:  fout.print("[voltage]", field);
/* 2153 */       fout.print("[voltage]", field);
/* 2154 */       break;
/* 2155 */     case 1:  fout.print("voltage", field);
/* 2156 */       break;
/* 2157 */     case 2:  fout.print("[voltage]", field);
/* 2158 */       fout.print("[voltage]", field);
/* 2159 */       break;
/* 2160 */     case 3:  fout.print("[voltage]", field);
/* 2161 */       fout.print("[voltage]", field);
/* 2162 */       break;
/* 2163 */     case 4:  fout.print("[impedance]", field);
/* 2164 */       fout.print("[impedance]", field);
/* 2165 */       break;
/* 2166 */     case 5:  fout.print("impedance", field);
/* 2167 */       break;
/* 2168 */     case 6:  fout.print("[impedance]", field);
/* 2169 */       fout.print("[impedance]", field);
/* 2170 */       break;
/* 2171 */     case 7:  fout.print("[impedance]", field);
/* 2172 */       fout.print("[impedance]", field);
/*      */     }
/*      */     
/* 2175 */     fout.println();
/*      */     
/* 2177 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 2178 */       fout.print(Fmath.truncate(this.frequencies[i], trunc), field);
/* 2179 */       fout.print(Fmath.truncate(this.realZ[i], trunc), field);
/* 2180 */       fout.print(Fmath.truncate(this.calculatedRealZ[i], trunc), field);
/* 2181 */       fout.print(Fmath.truncate(this.imagZ[i], trunc), field);
/* 2182 */       fout.print(Fmath.truncate(this.calculatedImagZ[i], trunc), field);
/*      */       
/* 2184 */       switch (this.dataEnteredTypePointer) {
/* 2185 */       case 0:  fout.print(Fmath.truncate(this.realV[i], trunc), field);
/* 2186 */         fout.print(Fmath.truncate(this.imagV[i], trunc), field);
/* 2187 */         break;
/* 2188 */       case 1:  fout.print(Complex.truncate(this.voltages[i], trunc), field);
/* 2189 */         break;
/* 2190 */       case 2:  fout.print(Fmath.truncate(this.voltageMagnitudes[i], trunc), field);
/* 2191 */         fout.print(Fmath.truncate(this.voltagePhasesRad[i], trunc), field);
/* 2192 */         break;
/* 2193 */       case 3:  fout.print(Fmath.truncate(this.voltageMagnitudes[i], trunc), field);
/* 2194 */         fout.print(Fmath.truncate(this.voltagePhasesDeg[i], trunc), field);
/* 2195 */         break;
/* 2196 */       case 4:  fout.print(Fmath.truncate(this.realZ[i], trunc), field);
/* 2197 */         fout.print(Fmath.truncate(this.imagZ[i], trunc), field);
/* 2198 */         break;
/* 2199 */       case 5:  fout.print(Complex.truncate(this.impedances[i], trunc), field);
/* 2200 */         break;
/* 2201 */       case 6:  fout.print(Fmath.truncate(this.impedanceMagnitudes[i], trunc), field);
/* 2202 */         fout.print(Fmath.truncate(this.impedancePhasesRad[i], trunc), field);
/* 2203 */         break;
/* 2204 */       case 7:  fout.print(Fmath.truncate(this.impedanceMagnitudes[i], trunc), field);
/* 2205 */         fout.print(Fmath.truncate(this.impedancePhasesDeg[i], trunc), field);
/*      */       }
/*      */       
/* 2208 */       fout.println();
/*      */     }
/* 2210 */     fout.close();
/*      */     
/* 2212 */     return this.results;
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> printToExcelFile()
/*      */   {
/* 2218 */     String fileName = "ImpedSpecRegressionOutput.txt";
/* 2219 */     this.fileType = true;
/* 2220 */     return printToExcelFile(fileName);
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<Object> printToExcelFile(String fileName)
/*      */   {
/* 2226 */     if (!this.regressionDone) { regression();
/*      */     }
/* 2228 */     int field = 11;
/* 2229 */     int trunc = 4;
/*      */     
/*      */ 
/* 2232 */     fileName = fileName.trim();
/* 2233 */     int dotPosition = fileName.indexOf('.');
/* 2234 */     if (dotPosition == -1) {
/* 2235 */       fileName = fileName + ".xls";
/*      */     }
/*      */     else {
/* 2238 */       fileName = fileName.substring(0, dotPosition) + ".xls";
/*      */     }
/*      */     
/*      */ 
/* 2242 */     FileOutput fout = null;
/* 2243 */     if (this.fileType) {
/* 2244 */       fout = new FileOutput(fileName, 'n');
/*      */     }
/*      */     else {
/* 2247 */       fout = new FileOutput(fileName);
/*      */     }
/*      */     
/*      */ 
/* 2251 */     fout.println("ImpedSpecRegression Program Output File:  " + this.regressionTitle);
/* 2252 */     fout.dateAndTimeln(fileName);
/* 2253 */     fout.println();
/* 2254 */     if (this.modelSet) {
/* 2255 */       fout.println("Circuit - model number " + this.modelNumber);
/*      */     }
/*      */     else {
/* 2258 */       fout.println("Circuit supplied by the user");
/*      */     }
/* 2260 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 2264 */     fout.println("Circuit Parameters");
/* 2265 */     fout.println("Best Estimates");
/*      */     
/* 2267 */     fout.printtab("Parameter", field);
/* 2268 */     fout.printtab("Best", field);
/* 2269 */     fout.printtab("Standard", field);
/* 2270 */     fout.printtab("Coeff. of", field);
/* 2271 */     fout.printtab("Pre-", field);
/* 2272 */     fout.println("Post-");
/*      */     
/* 2274 */     fout.printtab("   ", field);
/* 2275 */     fout.printtab("estimate", field);
/* 2276 */     fout.printtab("deviation", field);
/* 2277 */     fout.printtab("variation", field);
/* 2278 */     fout.printtab("gradient", field);
/* 2279 */     fout.println("gradient");
/*      */     
/* 2281 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 2282 */       fout.printtab(this.parameterSymbols[i], field);
/* 2283 */       fout.printtab(Fmath.truncate(this.bestEstimates[i], trunc), field);
/* 2284 */       fout.printtab(Fmath.truncate(this.standardDeviations[i], trunc), field);
/* 2285 */       fout.printtab(Fmath.truncate(this.coefficientsOfVariation[i], trunc), field);
/* 2286 */       fout.printtab(Fmath.truncate(this.preMinimumGradients[i], trunc), field);
/* 2287 */       fout.println(Fmath.truncate(this.postMinimumGradients[i], trunc));
/*      */     }
/* 2289 */     fout.println();
/*      */     
/* 2291 */     fout.println("Initial Estimates");
/*      */     
/* 2293 */     fout.printtab("Parameter", field);
/* 2294 */     fout.printtab("Initial", field);
/* 2295 */     fout.println("initial");
/*      */     
/* 2297 */     fout.printtab("   ", field);
/* 2298 */     fout.printtab("estimate", field);
/* 2299 */     fout.println("step size");
/*      */     
/* 2301 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 2302 */       fout.printtab(this.parameterSymbols[i], field);
/* 2303 */       fout.printtab(Fmath.truncate(this.initialEstimates[i], trunc), field);
/* 2304 */       fout.println(Fmath.truncate(this.initialSteps[i], trunc));
/*      */     }
/* 2306 */     fout.println();
/*      */     
/*      */ 
/* 2309 */     fout.println("Sum of squares of the Real[Z] and Imag[z] residuals:         " + Fmath.truncate(this.sumOfSquares, trunc));
/* 2310 */     fout.println("Reduced sum of squares of the Real[Z] and Imag[z] residuals: " + Fmath.truncate(this.sumOfSquares / this.degreesOfFreedom, trunc));
/* 2311 */     fout.println("Degrees of freedom: " + this.degreesOfFreedom);
/* 2312 */     if (this.weightsSet) {
/* 2313 */       fout.println("Chi square:         " + Fmath.truncate(this.chiSquare, trunc));
/* 2314 */       fout.println("Reduced chi square: " + Fmath.truncate(this.reducedChiSquare, trunc));
/*      */     }
/* 2316 */     fout.println("Number of iterations taken in the first regression:      " + this.numberOfIterations1);
/* 2317 */     fout.println("Number of iterations taken in the second regression:     " + this.numberOfIterations2);
/* 2318 */     fout.println("Maximum number of iterations allowed in each regression: " + this.maximumIterations);
/* 2319 */     fout.println();
/*      */     
/*      */ 
/* 2322 */     field = 14;
/* 2323 */     fout.println("Fitted and entered data [frequencies, calculated impedances, data as entered]");
/* 2324 */     fout.print("Entered data type:  ");
/* 2325 */     fout.println(this.dataEnteredType[this.dataEnteredTypePointer]);
/* 2326 */     fout.println();
/*      */     
/* 2328 */     fout.printtab("Frequency", field);
/* 2329 */     fout.printtab("Experimental", field);
/* 2330 */     fout.printtab("Calculated", field);
/* 2331 */     fout.printtab("Experimental", field);
/* 2332 */     fout.printtab("Calculated", field);
/*      */     
/* 2334 */     switch (this.dataEnteredTypePointer) {
/* 2335 */     case 0:  fout.printtab("Real", field);
/* 2336 */       fout.printtab("Imag", field);
/* 2337 */       break;
/* 2338 */     case 1:  fout.printtab("Complex", field);
/* 2339 */       break;
/* 2340 */     case 2:  fout.printtab("Magnitude", field);
/* 2341 */       fout.printtab("Phase (rad)", field);
/* 2342 */       break;
/* 2343 */     case 3:  fout.printtab("Magnitude", field);
/* 2344 */       fout.printtab("Phase (deg)", field);
/* 2345 */       break;
/* 2346 */     case 4:  fout.printtab("Real", field);
/* 2347 */       fout.printtab("Imag", field);
/* 2348 */       break;
/* 2349 */     case 5:  fout.printtab("Complex", field);
/* 2350 */       break;
/* 2351 */     case 6:  fout.printtab("Magnitude", field);
/* 2352 */       fout.printtab("Phase (rad)", field);
/* 2353 */       break;
/* 2354 */     case 7:  fout.printtab("Magnitude", field);
/* 2355 */       fout.printtab("Phase (deg)", field);
/*      */     }
/*      */     
/* 2358 */     fout.println();
/*      */     
/* 2360 */     fout.printtab("Frequency", field);
/* 2361 */     fout.printtab("Real[Z]", field);
/* 2362 */     fout.printtab("Real[Z]", field);
/* 2363 */     fout.printtab("Imag[Z]", field);
/* 2364 */     fout.printtab("Imag[Z]", field);
/* 2365 */     switch (this.dataEnteredTypePointer) {
/* 2366 */     case 0:  fout.printtab("[voltage]", field);
/* 2367 */       fout.printtab("[voltage]", field);
/* 2368 */       break;
/* 2369 */     case 1:  fout.printtab("voltage", field);
/* 2370 */       break;
/* 2371 */     case 2:  fout.printtab("[voltage]", field);
/* 2372 */       fout.printtab("[voltage]", field);
/* 2373 */       break;
/* 2374 */     case 3:  fout.printtab("[voltage]", field);
/* 2375 */       fout.printtab("[voltage]", field);
/* 2376 */       break;
/* 2377 */     case 4:  fout.printtab("[impedance]", field);
/* 2378 */       fout.printtab("[impedance]", field);
/* 2379 */       break;
/* 2380 */     case 5:  fout.printtab("impedance", field);
/* 2381 */       break;
/* 2382 */     case 6:  fout.printtab("[impedance]", field);
/* 2383 */       fout.printtab("[impedance]", field);
/* 2384 */       break;
/* 2385 */     case 7:  fout.printtab("[impedance]", field);
/* 2386 */       fout.printtab("[impedance]", field);
/*      */     }
/*      */     
/* 2389 */     fout.println();
/*      */     
/* 2391 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/* 2392 */       fout.printtab(Fmath.truncate(this.frequencies[i], trunc), field);
/* 2393 */       fout.printtab(Fmath.truncate(this.realZ[i], trunc), field);
/* 2394 */       fout.printtab(Fmath.truncate(this.calculatedRealZ[i], trunc), field);
/* 2395 */       fout.printtab(Fmath.truncate(this.imagZ[i], trunc), field);
/* 2396 */       fout.printtab(Fmath.truncate(this.calculatedImagZ[i], trunc), field);
/*      */       
/* 2398 */       switch (this.dataEnteredTypePointer) {
/* 2399 */       case 0:  fout.printtab(Fmath.truncate(this.realV[i], trunc), field);
/* 2400 */         fout.printtab(Fmath.truncate(this.imagV[i], trunc), field);
/* 2401 */         break;
/* 2402 */       case 1:  fout.printtab(Complex.truncate(this.voltages[i], trunc), field);
/* 2403 */         break;
/* 2404 */       case 2:  fout.printtab(Fmath.truncate(this.voltageMagnitudes[i], trunc), field);
/* 2405 */         fout.printtab(Fmath.truncate(this.voltagePhasesRad[i], trunc), field);
/* 2406 */         break;
/* 2407 */       case 3:  fout.printtab(Fmath.truncate(this.voltageMagnitudes[i], trunc), field);
/* 2408 */         fout.printtab(Fmath.truncate(this.voltagePhasesDeg[i], trunc), field);
/* 2409 */         break;
/* 2410 */       case 4:  fout.printtab(Fmath.truncate(this.realZ[i], trunc), field);
/* 2411 */         fout.printtab(Fmath.truncate(this.imagZ[i], trunc), field);
/* 2412 */         break;
/* 2413 */       case 5:  fout.printtab(Complex.truncate(this.impedances[i], trunc), field);
/* 2414 */         break;
/* 2415 */       case 6:  fout.printtab(Fmath.truncate(this.impedanceMagnitudes[i], trunc), field);
/* 2416 */         fout.printtab(Fmath.truncate(this.impedancePhasesRad[i], trunc), field);
/* 2417 */         break;
/* 2418 */       case 7:  fout.printtab(Fmath.truncate(this.impedanceMagnitudes[i], trunc), field);
/* 2419 */         fout.printtab(Fmath.truncate(this.impedancePhasesDeg[i], trunc), field);
/*      */       }
/*      */       
/*      */       
/* 2423 */       fout.println();
/*      */     }
/*      */     
/*      */ 
/* 2427 */     fout.close();
/*      */     
/* 2429 */     return this.results;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ImpedSpecRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */