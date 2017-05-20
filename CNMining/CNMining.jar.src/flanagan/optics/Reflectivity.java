/*      */ package flanagan.optics;
/*      */ 
/*      */ import flanagan.analysis.Regression;
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexMatrix;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Reflectivity
/*      */ {
/*   50 */   private int numberOfLayers = 0;
/*   51 */   private int numberOfInterfaces = 0;
/*      */   
/*   53 */   private Complex[][] refractiveIndices = (Complex[][])null;
/*   54 */   private Complex[] meanRefractiveIndices = null;
/*   55 */   private boolean refractSet = false;
/*   56 */   private boolean[] refractLayerSet = null;
/*   57 */   private boolean meanRefractUsed = false;
/*      */   
/*   59 */   private Complex[][] relativeMagneticPermeabilities = (Complex[][])null;
/*      */   
/*   61 */   private Complex[] meanRelativeMagneticPermeabilities = null;
/*   62 */   private boolean magneticSet = false;
/*   63 */   private boolean meanMagneticUsed = false;
/*      */   
/*   65 */   private double[][] absorptionCoefficients = (double[][])null;
/*      */   
/*   67 */   private boolean absorbSet = false;
/*      */   
/*   69 */   private double[] thicknesses = null;
/*   70 */   private double[] distances = null;
/*   71 */   private boolean thickSet = false;
/*   72 */   private boolean[] thickLayerSet = null;
/*      */   
/*   74 */   private int numberOfWavelengths = 0;
/*   75 */   private double[] wavelengths = null;
/*   76 */   private double[] frequencies = null;
/*   77 */   private double[] omega = null;
/*   78 */   private int[] origWavelIndices = null;
/*   79 */   private boolean wavelSet = false;
/*   80 */   private boolean freqSet = false;
/*   81 */   private boolean wavelNumberSet = false;
/*      */   
/*   83 */   private double[] incidentAngleDeg = null;
/*   84 */   private double[] incidentAngleRad = null;
/*   85 */   private int[] incidentAngleIndices = null;
/*   86 */   private int numberOfIncidentAngles = 0;
/*   87 */   private boolean incidentAngleSet = false;
/*      */   
/*   89 */   private String mode = null;
/*   90 */   private double eVectorAngleDeg = 0.0D;
/*      */   
/*   92 */   private double eVectorAngleRad = 0.0D;
/*      */   
/*   94 */   private double teFraction = 0.0D;
/*   95 */   private double tmFraction = 0.0D;
/*   96 */   private boolean modeSet = false;
/*      */   
/*   98 */   private Complex[][][] koVector = (Complex[][][])null;
/*   99 */   private Complex[][][] kVector = (Complex[][][])null;
/*  100 */   private Complex[][][] kxVector = (Complex[][][])null;
/*  101 */   private Complex[][][] kzVector = (Complex[][][])null;
/*      */   
/*  103 */   private double[][] reflectivities = (double[][])null;
/*  104 */   private double[][] transmissivities = (double[][])null;
/*  105 */   private double[][] powerLosses = (double[][])null;
/*  106 */   private Complex[][] reflectCoeffTE = (Complex[][])null;
/*  107 */   private Complex[][] reflectCoeffTM = (Complex[][])null;
/*  108 */   private Complex[][] transmitCoeffTE = (Complex[][])null;
/*  109 */   private Complex[][] transmitCoeffTM = (Complex[][])null;
/*      */   
/*  111 */   private double[][] reflectPhaseShiftRadTE = (double[][])null;
/*  112 */   private double[][] reflectPhaseShiftRadTM = (double[][])null;
/*  113 */   private double[][] transmitPhaseShiftRadTE = (double[][])null;
/*  114 */   private double[][] transmitPhaseShiftRadTM = (double[][])null;
/*  115 */   private double[][] reflectPhaseShiftDegTE = (double[][])null;
/*  116 */   private double[][] reflectPhaseShiftDegTM = (double[][])null;
/*  117 */   private double[][] transmitPhaseShiftDegTE = (double[][])null;
/*  118 */   private double[][] transmitPhaseShiftDegTM = (double[][])null;
/*      */   
/*  120 */   private double[][] evanescentFields = (double[][])null;
/*  121 */   private double fieldDistance = Double.POSITIVE_INFINITY;
/*  122 */   private boolean fieldIntensityCalc = false;
/*  123 */   private double[][] penetrationDepths = (double[][])null;
/*  124 */   private double[][] transmitAnglesRad = (double[][])null;
/*  125 */   private double[][] transmitAnglesDeg = (double[][])null;
/*      */   
/*  127 */   private boolean singleReflectCalculated = false;
/*  128 */   private boolean angularReflectCalculated = false;
/*  129 */   private boolean wavelengthReflectCalculated = false;
/*  130 */   private boolean wavelengthAndAngularReflectCalculated = false;
/*      */   
/*  132 */   private double mu0overEps0 = 141925.72909094833D;
/*  133 */   private double impedance = Math.sqrt(this.mu0overEps0);
/*      */   
/*  135 */   private int wavelengthAxisOption = 1;
/*      */   
/*      */ 
/*      */ 
/*  139 */   private double[] experimentalData = null;
/*  140 */   private double[] experimentalWeights = null;
/*  141 */   private double[] calculatedData = null;
/*      */   
/*  143 */   private int numberOfDataPoints = 0;
/*  144 */   private boolean experimentalDataSet = false;
/*  145 */   private boolean weightingOption = false;
/*      */   
/*  147 */   private int numberOfEstimatedParameters = 0;
/*  148 */   private int[] thicknessEstimateIndices = null;
/*  149 */   private int[] refractIndexRealEstimateIndices = null;
/*  150 */   private int[] refractIndexImagEstimateIndices = null;
/*  151 */   private int[] absorptionCoeffEstimateIndices = null;
/*  152 */   private int[] magneticPermRealEstimateIndices = null;
/*  153 */   private int[] magneticPermImagEstimateIndices = null;
/*      */   
/*  155 */   private boolean refractIndexImagEstimateSet = false;
/*  156 */   private boolean absorptionCoeffEstimateSet = false;
/*      */   
/*  158 */   private int thicknessEstimateNumber = 0;
/*  159 */   private int refractIndexRealEstimateNumber = 0;
/*  160 */   private int refractIndexImagEstimateNumber = 0;
/*  161 */   private int absorptionCoeffEstimateNumber = 0;
/*  162 */   private int magneticPermRealEstimateNumber = 0;
/*  163 */   private int magneticPermImagEstimateNumber = 0;
/*      */   
/*  165 */   private double fieldScalingFactor = 0.0D;
/*      */   
/*  167 */   public int regressionOption = 0;
/*      */   
/*      */ 
/*      */ 
/*  171 */   public int degreesOfFreedom = 0;
/*      */   
/*      */ 
/*      */   public Reflectivity(int n)
/*      */   {
/*  176 */     this.numberOfLayers = n;
/*  177 */     this.numberOfInterfaces = (n - 1);
/*  178 */     if (n < 2) { throw new IllegalArgumentException("There must be at least two layers, i.e. at least one interface");
/*      */     }
/*  180 */     this.meanRelativeMagneticPermeabilities = Complex.oneDarray(this.numberOfLayers, 1.0D, 0.0D);
/*      */     
/*  182 */     this.meanRefractiveIndices = Complex.oneDarray(this.numberOfLayers);
/*  183 */     this.refractLayerSet = new boolean[this.numberOfLayers];
/*  184 */     for (int i = 0; i < this.numberOfLayers; i++) { this.refractLayerSet[i] = false;
/*      */     }
/*  186 */     this.thicknesses = new double[this.numberOfLayers];
/*  187 */     this.thicknesses[0] = Double.NEGATIVE_INFINITY;
/*  188 */     this.thicknesses[(this.numberOfLayers - 1)] = Double.POSITIVE_INFINITY;
/*  189 */     this.thickLayerSet = new boolean[this.numberOfLayers];
/*  190 */     this.thickLayerSet[0] = true;
/*  191 */     for (int i = 1; i < this.numberOfLayers - 2; i++) this.thickLayerSet[i] = false;
/*  192 */     this.thickLayerSet[(this.numberOfLayers - 1)] = true;
/*      */     
/*  194 */     this.distances = new double[this.numberOfInterfaces];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMode(String mode)
/*      */   {
/*  201 */     if ((mode.equalsIgnoreCase("TE")) || (mode.equalsIgnoreCase("transverse electric"))) {
/*  202 */       this.mode = "TE";
/*  203 */       this.teFraction = 1.0D;
/*  204 */       this.tmFraction = 0.0D;
/*  205 */       this.eVectorAngleDeg = 0.0D;
/*  206 */       this.eVectorAngleRad = 0.0D;
/*      */ 
/*      */     }
/*  209 */     else if ((mode.equalsIgnoreCase("TM")) || (mode.equalsIgnoreCase("transverse magnetic"))) {
/*  210 */       this.mode = "TM";
/*  211 */       this.teFraction = 0.0D;
/*  212 */       this.tmFraction = 1.0D;
/*  213 */       this.eVectorAngleDeg = 90.0D;
/*  214 */       this.eVectorAngleRad = 1.5707963267948966D;
/*      */ 
/*      */     }
/*  217 */     else if ((mode.equalsIgnoreCase("unpolarised")) || (mode.equalsIgnoreCase("unpolarized")) || (mode.equalsIgnoreCase("none"))) {
/*  218 */       this.mode = "unpolarised";
/*  219 */       this.teFraction = 0.5D;
/*  220 */       this.tmFraction = 0.5D;
/*  221 */       this.eVectorAngleDeg = 45.0D;
/*  222 */       this.eVectorAngleRad = 0.7853981633974483D;
/*      */     }
/*      */     else {
/*  225 */       throw new IllegalArgumentException("mode must be TE, TM or unpolarised; it cannot be " + mode);
/*      */     }
/*      */     
/*      */ 
/*  229 */     this.modeSet = true;
/*      */   }
/*      */   
/*      */   public void setMode(double modeAngle)
/*      */   {
/*  234 */     this.mode = "mixed";
/*  235 */     this.eVectorAngleDeg = modeAngle;
/*  236 */     this.eVectorAngleRad = Math.toRadians(modeAngle);
/*  237 */     this.teFraction = Math.sin(this.eVectorAngleRad);
/*  238 */     this.teFraction *= this.teFraction;
/*  239 */     this.tmFraction = (1.0D - this.teFraction);
/*  240 */     this.modeSet = true;
/*      */   }
/*      */   
/*      */   public double fractionInTEmode()
/*      */   {
/*  245 */     return this.teFraction;
/*      */   }
/*      */   
/*      */   public double fractionInTMmode()
/*      */   {
/*  250 */     return this.tmFraction;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIncidentAngle(double incidentAngle)
/*      */   {
/*  257 */     double[] incident = { incidentAngle };
/*  258 */     setIncidentAngle(incident);
/*      */   }
/*      */   
/*      */   public void setIncidentAngle(double[] incidentAngle)
/*      */   {
/*  263 */     this.numberOfIncidentAngles = incidentAngle.length;
/*  264 */     this.incidentAngleIndices = new int[this.numberOfIncidentAngles];
/*  265 */     this.incidentAngleDeg = new double[this.numberOfIncidentAngles];
/*  266 */     Fmath.selectionSort(incidentAngle, this.incidentAngleDeg, this.incidentAngleIndices);
/*  267 */     if (this.experimentalDataSet) {
/*  268 */       if (this.numberOfDataPoints != this.numberOfIncidentAngles) throw new IllegalArgumentException("Number of experimental reflectivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
/*  269 */       double[] temp = (double[])this.experimentalData.clone();
/*  270 */       for (int i = 0; i < this.numberOfIncidentAngles; i++) this.experimentalData[i] = temp[this.incidentAngleIndices[i]];
/*      */     }
/*  272 */     this.incidentAngleRad = new double[this.numberOfIncidentAngles];
/*  273 */     for (int i = 0; i < this.numberOfIncidentAngles; i++) this.incidentAngleRad[i] = Math.toRadians(this.incidentAngleDeg[i]);
/*  274 */     this.incidentAngleSet = true;
/*      */   }
/*      */   
/*      */   public void setIncidentAngle(double angleLow, double angleHigh, int nAngles)
/*      */   {
/*  279 */     this.numberOfIncidentAngles = nAngles;
/*  280 */     double increment = (angleHigh - angleLow) / (nAngles - 1);
/*  281 */     double[] incidentAngles = new double[nAngles];
/*  282 */     incidentAngles[0] = angleLow;
/*  283 */     for (int i = 1; i < nAngles - 1; i++) incidentAngles[i] = (incidentAngles[(i - 1)] + increment);
/*  284 */     incidentAngles[(nAngles - 1)] = angleHigh;
/*  285 */     setIncidentAngle(incidentAngles);
/*      */   }
/*      */   
/*      */   public double[] getIncidentAngles()
/*      */   {
/*  290 */     return this.incidentAngleDeg;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setThicknesses(double[] thick)
/*      */   {
/*  297 */     int n = thick.length;
/*  298 */     if (n != this.numberOfLayers - 2) throw new IllegalArgumentException("Number of thicknesses, " + n + ", does not match the number of layers minus the outer two semi-finite layers, " + (this.numberOfLayers - 2));
/*  299 */     for (int i = 1; i < this.numberOfLayers - 1; i++) { this.thicknesses[i] = thick[(i - 1)];
/*      */     }
/*      */     
/*  302 */     this.distances[0] = 0.0D;
/*  303 */     for (int i = 1; i < this.numberOfInterfaces; i++) { this.distances[i] = (this.distances[(i - 1)] + this.thicknesses[i]);
/*      */     }
/*  305 */     for (int i = 1; i < this.numberOfLayers - 2; i++) this.thickLayerSet[i] = true;
/*  306 */     this.thickSet = true;
/*      */   }
/*      */   
/*      */   public void setThicknesses(double thickness, int layerNumber)
/*      */   {
/*  311 */     if ((layerNumber < 1) || (layerNumber > this.numberOfLayers)) throw new IllegalArgumentException("Layer number, " + layerNumber + ", must be in the range 1 to " + this.numberOfLayers);
/*  312 */     this.thicknesses[(layerNumber - 1)] = thickness;
/*      */     
/*      */ 
/*  315 */     this.distances[0] = 0.0D;
/*  316 */     for (int i = 1; i < this.numberOfInterfaces; i++) { this.distances[i] = (this.distances[(i - 1)] + this.thicknesses[i]);
/*      */     }
/*  318 */     this.thickLayerSet[(layerNumber - 1)] = true;
/*  319 */     int check = 0;
/*  320 */     for (int i = 0; i < this.numberOfLayers - i; i++) if (this.thickLayerSet[i] != 0) check++;
/*  321 */     if (check == this.numberOfLayers) this.thickSet = true;
/*      */   }
/*      */   
/*      */   public double[] getThicknesses()
/*      */   {
/*  326 */     return this.thicknesses;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWavelength(double[] wavelengths)
/*      */   {
/*  334 */     int n = wavelengths.length;
/*  335 */     if ((this.wavelNumberSet) && 
/*  336 */       (n != this.numberOfWavelengths)) { throw new IllegalArgumentException("The number of wavelengths entered, " + n + ", does not equal that previously set," + this.numberOfWavelengths);
/*      */     }
/*  338 */     this.numberOfWavelengths = n;
/*  339 */     this.wavelengths = wavelengths;
/*  340 */     this.wavelSet = true;
/*      */     
/*      */ 
/*  343 */     if (!this.refractSet) { this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/*      */     }
/*      */     
/*  346 */     if (!this.wavelNumberSet)
/*      */     {
/*  348 */       if (this.meanRefractUsed) {
/*  349 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  350 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  351 */             this.refractiveIndices[j][i] = this.meanRefractiveIndices[i];
/*      */           }
/*      */         }
/*  354 */         for (int i = 0; i < this.numberOfLayers; i++) this.refractLayerSet[i] = true;
/*  355 */         this.refractSet = true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  360 */       if (this.absorptionCoefficients != null) {
/*  361 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  362 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  363 */             if (this.refractiveIndices[i][j].getImag() == 0.0D) { this.refractiveIndices[j][i].setImag(this.absorptionCoefficients[j][i] * this.wavelengths[j] / 12.566370614359172D);
/*      */             }
/*      */           }
/*      */         }
/*      */       } else {
/*  368 */         this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */       }
/*      */       
/*      */ 
/*  372 */       this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/*  373 */       if (this.meanMagneticUsed)
/*      */       {
/*  375 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  376 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  377 */             this.relativeMagneticPermeabilities[j][i] = this.meanRelativeMagneticPermeabilities[i];
/*      */           }
/*      */         }
/*  380 */         this.magneticSet = true;
/*      */       }
/*      */       else
/*      */       {
/*  384 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  385 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  386 */             this.relativeMagneticPermeabilities[j][i] = Complex.plusOne();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  393 */     if (!this.freqSet) {
/*  394 */       this.frequencies = new double[this.numberOfWavelengths];
/*  395 */       for (int i = 0; i < this.numberOfWavelengths; i++) { this.frequencies[(this.numberOfWavelengths - 1 - i)] = (2.99792458E8D / wavelengths[i]);
/*      */       }
/*      */     }
/*      */     
/*  399 */     this.omega = new double[this.numberOfWavelengths];
/*  400 */     for (int i = 0; i < this.numberOfWavelengths; i++) { this.omega[i] = (6.283185307179586D * this.frequencies[i]);
/*      */     }
/*  402 */     this.wavelNumberSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFrequency(double[] frequency)
/*      */   {
/*  408 */     int n = frequency.length;
/*  409 */     if ((this.wavelNumberSet) && 
/*  410 */       (n != this.numberOfWavelengths)) { throw new IllegalArgumentException("The number of frequencies entered, " + n + ", does not equal that previously set," + this.numberOfWavelengths);
/*      */     }
/*  412 */     this.frequencies = frequency;
/*  413 */     this.freqSet = true;
/*  414 */     this.wavelengthAxisOption = 2;
/*      */     
/*      */ 
/*  417 */     double[] wavelength = new double[n];
/*  418 */     for (int i = 0; i < n; i++) wavelength[i] = (2.99792458E8D / this.frequencies[(n - 1 - i)]);
/*  419 */     setWavelength(wavelength);
/*      */   }
/*      */   
/*      */   public void setWavelength(double lambdaLow, double lambdaHigh, int nLambda)
/*      */   {
/*  424 */     double increment = (lambdaHigh - lambdaLow) / (nLambda - 1);
/*  425 */     double[] wavelength = new double[nLambda];
/*  426 */     wavelength[0] = lambdaLow;
/*  427 */     for (int i = 1; i < nLambda - 1; i++) wavelength[i] = (wavelength[(i - 1)] + increment);
/*  428 */     wavelength[(nLambda - 1)] = lambdaHigh;
/*  429 */     setWavelength(wavelength);
/*      */   }
/*      */   
/*      */   public void setFrequency(double freqLow, double freqHigh, int nFreq)
/*      */   {
/*  434 */     double increment = (freqHigh - freqLow) / (nFreq - 1);
/*  435 */     double[] frequency = new double[nFreq];
/*  436 */     frequency[0] = freqLow;
/*  437 */     for (int i = 1; i < nFreq - 1; i++) frequency[i] = (frequency[(i - 1)] + increment);
/*  438 */     frequency[(nFreq - 1)] = freqHigh;
/*  439 */     setFrequency(frequency);
/*      */   }
/*      */   
/*      */   public void setWavelength(double wavelength)
/*      */   {
/*  444 */     double[] wavelengths = { wavelength };
/*  445 */     setWavelength(wavelengths);
/*      */   }
/*      */   
/*      */   public void setFrequency(double frequency)
/*      */   {
/*  450 */     double[] frequencies = { frequency };
/*  451 */     setFrequency(frequencies);
/*      */   }
/*      */   
/*      */   public double[] getWavelengths()
/*      */   {
/*  456 */     return this.wavelengths;
/*      */   }
/*      */   
/*      */   public double[] getRadialFrequencies()
/*      */   {
/*  461 */     return this.omega;
/*      */   }
/*      */   
/*      */ 
/*      */   private void sortWavelengths()
/*      */   {
/*  467 */     this.origWavelIndices = new int[this.numberOfWavelengths];
/*  468 */     for (int i = 0; i < this.numberOfWavelengths; i++) this.origWavelIndices[i] = i;
/*  469 */     if (this.numberOfWavelengths > 1)
/*      */     {
/*  471 */       boolean test0 = true;
/*  472 */       boolean test1 = false;
/*  473 */       int ii = 1;
/*  474 */       while (test0) {
/*  475 */         if (this.wavelengths[ii] < this.wavelengths[(ii - 1)]) {
/*  476 */           test0 = false;
/*  477 */           test1 = true;
/*      */         }
/*      */         else {
/*  480 */           ii++;
/*  481 */           if (ii >= this.numberOfWavelengths) test0 = false;
/*      */         }
/*      */       }
/*  484 */       if (test1)
/*      */       {
/*  486 */         ArrayList arrayl = Fmath.selectSortArrayList(this.wavelengths);
/*  487 */         this.wavelengths = ((double[])arrayl.get(1));
/*  488 */         this.origWavelIndices = ((int[])arrayl.get(2));
/*      */         
/*  490 */         Complex[][] tempC = new Complex[this.numberOfWavelengths][this.numberOfLayers];
/*  491 */         for (int i = 0; i < this.numberOfWavelengths; i++) {
/*  492 */           for (int j = 0; j < this.numberOfLayers; j++) {
/*  493 */             tempC[i][j] = this.refractiveIndices[this.origWavelIndices[i]][j];
/*      */           }
/*      */         }
/*  496 */         this.refractiveIndices = Complex.copy(tempC);
/*      */         
/*  498 */         for (int i = 0; i < this.numberOfWavelengths; i++) {
/*  499 */           for (int j = 0; j < this.numberOfLayers; j++) {
/*  500 */             tempC[i][j] = this.relativeMagneticPermeabilities[this.origWavelIndices[i]][j];
/*      */           }
/*      */         }
/*  503 */         this.relativeMagneticPermeabilities = Complex.copy(tempC);
/*      */         
/*  505 */         double[][] tempD = new double[this.numberOfWavelengths][this.numberOfLayers];
/*  506 */         for (int i = 0; i < this.numberOfWavelengths; i++) {
/*  507 */           for (int j = 0; j < this.numberOfLayers; j++) {
/*  508 */             tempD[i][j] = this.absorptionCoefficients[this.origWavelIndices[i]][j];
/*      */           }
/*      */         }
/*  511 */         this.absorptionCoefficients = tempD;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefractiveIndices(Complex[][] refractiveIndices)
/*      */   {
/*  520 */     int n = refractiveIndices[0].length;
/*  521 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of refractive indices layers, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  522 */     int m = refractiveIndices.length;
/*  523 */     if ((this.wavelSet) && 
/*  524 */       (m != this.numberOfWavelengths)) { throw new IllegalArgumentException("Number of refractive indices wavelength sets, " + m + ", does not match the number of wavelengths already set, " + this.numberOfWavelengths);
/*      */     }
/*      */     
/*      */ 
/*  528 */     this.refractiveIndices = refractiveIndices;
/*  529 */     for (int i = 0; i < this.numberOfLayers; i++) this.refractLayerSet[i] = true;
/*  530 */     this.refractSet = true;
/*  531 */     this.wavelNumberSet = true;
/*      */     
/*      */ 
/*  534 */     for (int i = 0; i < this.numberOfLayers; i++) {
/*  535 */       Complex sum = Complex.zero();
/*  536 */       for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  537 */         sum.plusEquals(this.refractiveIndices[j][i]);
/*      */       }
/*  539 */       this.meanRefractiveIndices[i] = sum.over(this.numberOfWavelengths);
/*      */     }
/*      */     
/*      */ 
/*  543 */     if ((this.wavelSet) && (this.absorptionCoefficients != null)) {
/*  544 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  545 */         for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  546 */           if (this.refractiveIndices[j][i].getImag() == 0.0D) { this.refractiveIndices[j][i].setImag(this.absorptionCoefficients[j][i] * this.wavelengths[i] / 12.566370614359172D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  551 */     if (!this.absorbSet) { this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */     }
/*      */     
/*  554 */     if (!this.magneticSet) {
/*  555 */       if (this.meanMagneticUsed) {
/*  556 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  557 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  558 */             this.relativeMagneticPermeabilities[j][i] = this.meanRelativeMagneticPermeabilities[i];
/*      */           }
/*      */         }
/*  561 */         this.magneticSet = true;
/*      */       }
/*      */       else {
/*  564 */         this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRefractiveIndices(double[][] refractiveIndices)
/*      */   {
/*  571 */     int n = refractiveIndices[0].length;
/*  572 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of refractive indices layers, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  573 */     int m = refractiveIndices.length;
/*  574 */     if ((this.wavelSet) && 
/*  575 */       (m != this.numberOfWavelengths)) { throw new IllegalArgumentException("Number of refractive indices wavelength sets, " + m + ", does not match the number of wavelengths already set, " + this.numberOfWavelengths);
/*      */     }
/*  577 */     Complex[][] complexRefractiveIndices = Complex.twoDarray(m, n);
/*  578 */     for (int i = 0; i < m; i++) {
/*  579 */       for (int j = 0; j < n; j++) {
/*  580 */         complexRefractiveIndices[i][j].setReal(refractiveIndices[i][j]);
/*      */       }
/*      */     }
/*  583 */     setRefractiveIndices(complexRefractiveIndices);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefractiveIndices(Complex[] refractiveIndices)
/*      */   {
/*  590 */     int n = refractiveIndices.length;
/*  591 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of refrative indices layers, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  592 */     this.meanRefractiveIndices = refractiveIndices;
/*  593 */     this.meanRefractUsed = true;
/*      */     
/*  595 */     if (this.wavelNumberSet)
/*      */     {
/*  597 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  598 */         for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  599 */           this.refractiveIndices[j][i] = this.meanRefractiveIndices[i];
/*      */         }
/*      */       }
/*  602 */       for (int i = 0; i < this.numberOfLayers; i++) this.refractLayerSet[i] = true;
/*  603 */       this.refractSet = true;
/*      */     }
/*      */     
/*      */ 
/*  607 */     if ((this.absorptionCoefficients != null) && (this.wavelSet)) {
/*  608 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  609 */         for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  610 */           if (this.refractiveIndices[j][i].getImag() == 0.0D) { this.refractiveIndices[j][i].setImag(this.absorptionCoefficients[j][i] * this.wavelengths[j] / 12.566370614359172D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  615 */     if (this.absorptionCoefficients == null) { this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */     }
/*      */     
/*      */ 
/*  619 */     if (!this.magneticSet) {
/*  620 */       if (!this.meanMagneticUsed) {
/*  621 */         if (this.wavelNumberSet) {
/*  622 */           this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
/*      */         }
/*      */       }
/*      */       else {
/*  626 */         this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/*  627 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  628 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  629 */             this.relativeMagneticPermeabilities[j][i] = this.meanRelativeMagneticPermeabilities[i];
/*      */           }
/*      */         }
/*  632 */         this.magneticSet = true;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRefractiveIndices(double[] refractiveIndices)
/*      */   {
/*  639 */     int n = refractiveIndices.length;
/*  640 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of refrative indices, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  641 */     Complex[] complexRefractiveIndices = Complex.oneDarray(n);
/*  642 */     for (int i = 0; i < n; i++) {
/*  643 */       complexRefractiveIndices[i].setReal(refractiveIndices[i]);
/*      */     }
/*  645 */     setRefractiveIndices(complexRefractiveIndices);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setRefractiveIndices(Complex[] refractiveIndices, int layerNumber)
/*      */   {
/*  651 */     if ((layerNumber < 0) || (layerNumber > this.numberOfLayers)) throw new IllegalArgumentException("Layer number, " + layerNumber + ", must be in the range 1 to " + this.numberOfLayers);
/*  652 */     int n = refractiveIndices.length;
/*  653 */     if (this.wavelNumberSet) {
/*  654 */       if (n != this.numberOfWavelengths) { throw new IllegalArgumentException("The number of refractive index wavelength values, " + n + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  659 */       this.numberOfWavelengths = n;
/*  660 */       this.wavelNumberSet = true;
/*  661 */       this.refractiveIndices = Complex.twoDarray(this.numberOfLayers, this.numberOfWavelengths);
/*  662 */       if (this.meanRefractUsed) {
/*  663 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  664 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  665 */             this.refractiveIndices[j][i] = this.meanRefractiveIndices[i];
/*      */           }
/*      */         }
/*  668 */         for (int i = 0; i < this.numberOfLayers; i++) this.refractLayerSet[i] = true;
/*  669 */         this.refractSet = true;
/*      */       }
/*      */       
/*  672 */       this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
/*      */       
/*  674 */       if (this.meanMagneticUsed) {
/*  675 */         for (int i = 0; i < this.numberOfLayers; i++) {
/*  676 */           for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  677 */             this.relativeMagneticPermeabilities[j][i] = this.meanRelativeMagneticPermeabilities[i];
/*      */           }
/*      */         }
/*  680 */         this.magneticSet = true;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  685 */     layerNumber--;
/*  686 */     this.refractiveIndices[layerNumber] = refractiveIndices;
/*  687 */     this.refractLayerSet[layerNumber] = true;
/*  688 */     int check = 0;
/*  689 */     for (int i = 0; i < this.numberOfLayers; i++) if (this.refractLayerSet[i] != 0) check++;
/*  690 */     if (check == this.numberOfLayers) { this.refractSet = true;
/*      */     }
/*      */     
/*  693 */     if (this.absorptionCoefficients != null) {
/*  694 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  695 */         for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  696 */           if (this.refractiveIndices[j][i].getImag() == 0.0D) { this.refractiveIndices[j][i].setImag(this.absorptionCoefficients[j][i] * this.wavelengths[j] / 12.566370614359172D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  701 */     if (this.absorptionCoefficients == null) { this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRefractiveIndices(double[] refractiveIndices, int layerNumber)
/*      */   {
/*  707 */     if ((layerNumber < 0) || (layerNumber > this.numberOfLayers)) throw new IllegalArgumentException("Layer number, " + layerNumber + ", must be in the range 1 to " + this.numberOfLayers);
/*  708 */     int n = refractiveIndices.length;
/*  709 */     if ((this.wavelNumberSet) && 
/*  710 */       (n != this.numberOfWavelengths)) { throw new IllegalArgumentException("The number of refractive index wavelength values, " + n + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */     }
/*  712 */     Complex[] complexRefractiveIndices = Complex.oneDarray(n);
/*  713 */     for (int i = 0; i < n; i++) {
/*  714 */       complexRefractiveIndices[i].setReal(refractiveIndices[i]);
/*      */     }
/*  716 */     setRefractiveIndices(complexRefractiveIndices, layerNumber);
/*      */   }
/*      */   
/*      */   public void setRefractiveIndices(Complex refractiveIndex, int layerNumber)
/*      */   {
/*  721 */     if (this.wavelNumberSet) {
/*  722 */       Complex[] complexRefractiveIndices = Complex.oneDarray(this.numberOfWavelengths);
/*  723 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/*  724 */         complexRefractiveIndices[i] = refractiveIndex;
/*      */       }
/*  726 */       setRefractiveIndices(complexRefractiveIndices, layerNumber);
/*      */     }
/*      */     else {
/*  729 */       this.meanRefractiveIndices[(layerNumber - 1)] = refractiveIndex;
/*  730 */       this.meanRefractUsed = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRefractiveIndices(double refractiveIndex, int layerNumber)
/*      */   {
/*  736 */     Complex complexRefractiveIndex = new Complex(refractiveIndex, 0.0D);
/*  737 */     setRefractiveIndices(complexRefractiveIndex, layerNumber);
/*      */   }
/*      */   
/*      */   public Object getRefractiveIndices()
/*      */   {
/*  742 */     if (this.numberOfWavelengths == 1) {
/*  743 */       Complex[] ret = this.refractiveIndices[0];
/*  744 */       return ret;
/*      */     }
/*      */     
/*  747 */     return this.refractiveIndices;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAbsorptionCoefficients(double[] absorptionCoefficients)
/*      */   {
/*  756 */     int n = absorptionCoefficients.length;
/*  757 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of absorption coefficients sets, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  758 */     this.absorptionCoefficients = new double[1][n];
/*  759 */     this.absorptionCoefficients[0] = absorptionCoefficients;
/*  760 */     this.absorbSet = true;
/*      */     
/*  762 */     if (this.refractSet) {
/*  763 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  764 */         if (this.refractiveIndices[0][i].getImag() == 0.0D) { this.refractiveIndices[0][i].setImag(this.absorptionCoefficients[0][i] * this.wavelengths[0] / 12.566370614359172D);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAbsorptionCoefficients(double[][] absorptionCoefficients)
/*      */   {
/*  773 */     int n = absorptionCoefficients[0].length;
/*  774 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of absorption coefficients sets, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  775 */     int m = absorptionCoefficients.length;
/*  776 */     if ((this.wavelNumberSet) && (m != this.numberOfWavelengths)) throw new IllegalArgumentException("Number of absorption coefficients wavelengths, " + m + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*  777 */     this.absorptionCoefficients = absorptionCoefficients;
/*  778 */     this.absorbSet = true;
/*      */     
/*  780 */     if ((this.refractSet) && (this.wavelSet)) {
/*  781 */       for (int i = 0; i < this.numberOfLayers; i++) {
/*  782 */         for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  783 */           if (this.refractiveIndices[j][i].getImag() == 0.0D) { this.refractiveIndices[j][i].setImag(absorptionCoefficients[j][i] * this.wavelengths[j] / 12.566370614359172D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setAbsorptionCoefficients(double[] absorptionCoefficients, int layerNumber)
/*      */   {
/*  792 */     int n = absorptionCoefficients.length;
/*  793 */     if (this.wavelNumberSet) {
/*  794 */       if (n != this.numberOfWavelengths) throw new IllegalArgumentException("Layer " + layerNumber + ": number of absorption coefficients wavelengths, " + n + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */     }
/*      */     else {
/*  797 */       this.numberOfWavelengths = n;
/*  798 */       this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/*  799 */       this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */     }
/*  801 */     layerNumber--;
/*  802 */     this.absorptionCoefficients[layerNumber] = absorptionCoefficients;
/*  803 */     for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  804 */       if (this.refractiveIndices[j][layerNumber].getImag() == 0.0D) this.refractiveIndices[j][layerNumber].setImag(absorptionCoefficients[j] * this.wavelengths[j] / 12.566370614359172D);
/*      */     }
/*  806 */     this.absorbSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAbsorptionCoefficients(double absorptionCoefficient, int layerNumber)
/*      */   {
/*  812 */     if (this.wavelNumberSet) {
/*  813 */       if (this.numberOfWavelengths != 1) throw new IllegalArgumentException("Layer " + layerNumber + ": number of absorption coefficients wavelengths, " + 1 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */     }
/*      */     else {
/*  816 */       this.numberOfWavelengths = 1;
/*  817 */       this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/*  818 */       this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
/*      */     }
/*  820 */     layerNumber--;
/*  821 */     this.absorptionCoefficients[0][layerNumber] = absorptionCoefficient;
/*  822 */     if (this.refractiveIndices[0][layerNumber].getImag() == 0.0D) { this.refractiveIndices[0][layerNumber].setImag(absorptionCoefficient * this.wavelengths[0] / 12.566370614359172D);
/*      */     }
/*  824 */     this.absorbSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getAbsorptionCoefficients()
/*      */   {
/*  830 */     double[][] absC = this.absorptionCoefficients;
/*  831 */     for (int i = 0; i < this.numberOfLayers; i++) {
/*  832 */       for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  833 */         absC[i][j] = (12.566370614359172D * this.wavelengths[j] * this.refractiveIndices[i][j].getImag());
/*      */       }
/*      */     }
/*      */     
/*  837 */     if (this.numberOfWavelengths == 1) {
/*  838 */       double[] ret = absC[0];
/*  839 */       return ret;
/*      */     }
/*      */     
/*  842 */     return absC;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRelativeMagneticPermeabilities(Complex[][] relativeMagneticPermeabilities)
/*      */   {
/*  850 */     int n = relativeMagneticPermeabilities[0].length;
/*  851 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  852 */     int m = relativeMagneticPermeabilities.length;
/*  853 */     if ((this.wavelNumberSet) && (m != this.numberOfWavelengths)) throw new IllegalArgumentException("Number of relative magnetic permeabilities associated wavelengths, " + m + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*  854 */     this.relativeMagneticPermeabilities = relativeMagneticPermeabilities;
/*  855 */     this.magneticSet = true;
/*      */     
/*      */ 
/*  858 */     for (int i = 0; i < this.numberOfLayers; i++) {
/*  859 */       Complex sum = Complex.zero();
/*  860 */       for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  861 */         sum.plusEquals(this.relativeMagneticPermeabilities[j][i]);
/*      */       }
/*  863 */       this.meanRelativeMagneticPermeabilities[i] = sum.over(this.numberOfWavelengths);
/*      */     }
/*      */   }
/*      */   
/*      */   public void relativeMagneticPermeabilities(double[][] relativeMagneticPermeabilities)
/*      */   {
/*  869 */     int n = relativeMagneticPermeabilities[0].length;
/*  870 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  871 */     int m = relativeMagneticPermeabilities.length;
/*  872 */     if ((this.wavelNumberSet) && (m != this.numberOfWavelengths)) throw new IllegalArgumentException("Number of relative magnetic permeabilities associated wavelengths, " + m + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*  873 */     this.relativeMagneticPermeabilities = Complex.twoDarray(m, n);
/*  874 */     for (int i = 0; i < this.numberOfLayers; i++) {
/*  875 */       for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  876 */         this.relativeMagneticPermeabilities[j][i].setReal(relativeMagneticPermeabilities[j][i]);
/*      */       }
/*      */     }
/*  879 */     this.magneticSet = true;
/*      */     
/*      */ 
/*  882 */     for (int i = 0; i < this.numberOfLayers; i++) {
/*  883 */       Complex sum = Complex.zero();
/*  884 */       for (int j = 0; j < this.numberOfWavelengths; j++) {
/*  885 */         sum.plusEquals(this.relativeMagneticPermeabilities[j][i]);
/*      */       }
/*  887 */       this.meanRelativeMagneticPermeabilities[i] = sum.over(this.numberOfWavelengths);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(Complex[] relativeMagneticPermeabilities)
/*      */   {
/*  893 */     int n = relativeMagneticPermeabilities.length;
/*  894 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  895 */     this.meanRelativeMagneticPermeabilities = relativeMagneticPermeabilities;
/*  896 */     this.meanMagneticUsed = true;
/*  897 */     if (this.wavelNumberSet) for (int i = 0; i < this.numberOfWavelengths; i++) this.relativeMagneticPermeabilities[i] = Complex.copy(relativeMagneticPermeabilities);
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(double[] relativeMagneticPermeabilities)
/*      */   {
/*  902 */     int n = relativeMagneticPermeabilities.length;
/*  903 */     if (n != this.numberOfLayers) throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + n + ", does not match the number of layers, " + this.numberOfLayers);
/*  904 */     for (int i = 0; i < n; i++) this.meanRelativeMagneticPermeabilities[i].setReal(relativeMagneticPermeabilities[i]);
/*  905 */     this.meanMagneticUsed = true;
/*  906 */     if (this.wavelNumberSet) for (int i = 0; i < this.numberOfWavelengths; i++) { this.relativeMagneticPermeabilities[i] = Complex.copy(this.meanRelativeMagneticPermeabilities);
/*      */       }
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(Complex[] relativeMagneticPermeabilities, int layerNumber)
/*      */   {
/*  912 */     int n = relativeMagneticPermeabilities.length;
/*  913 */     if ((this.wavelNumberSet) && 
/*  914 */       (n != this.numberOfWavelengths)) { throw new IllegalArgumentException("Layer " + layerNumber + ": number of relative magnetic permeabilities associated wavelengths, " + n + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */     }
/*  916 */     if (this.relativeMagneticPermeabilities == null) this.relativeMagneticPermeabilities = Complex.twoDarray(n, this.numberOfLayers);
/*  917 */     this.relativeMagneticPermeabilities[(layerNumber - 1)] = relativeMagneticPermeabilities;
/*  918 */     Complex sum = Complex.zero();
/*  919 */     for (int i = 0; i < n; i++) sum.plusEquals(this.relativeMagneticPermeabilities[i][(layerNumber - 1)]);
/*  920 */     this.meanRelativeMagneticPermeabilities[(layerNumber - 1)] = sum.over(n);
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(double[] relativeMagneticPermeabilities, int layerNumber)
/*      */   {
/*  925 */     int n = relativeMagneticPermeabilities.length;
/*  926 */     if ((this.wavelNumberSet) && 
/*  927 */       (n != this.numberOfWavelengths)) { throw new IllegalArgumentException("Layer " + layerNumber + ": number of relative magnetic permeabilities associated wavelengths, " + n + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
/*      */     }
/*  929 */     if (this.relativeMagneticPermeabilities == null) this.relativeMagneticPermeabilities = Complex.twoDarray(n, this.numberOfLayers);
/*  930 */     for (int i = 0; i < n; i++) this.relativeMagneticPermeabilities[i][(layerNumber - 1)].setReal(relativeMagneticPermeabilities[i]);
/*  931 */     Complex sum = Complex.zero();
/*  932 */     for (int i = 0; i < n; i++) sum.plusEquals(this.relativeMagneticPermeabilities[i][(layerNumber - 1)]);
/*  933 */     this.meanRelativeMagneticPermeabilities[(layerNumber - 1)] = sum.over(n);
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(Complex relativeMagneticPermeability, int layerNumber)
/*      */   {
/*  938 */     this.meanRelativeMagneticPermeabilities[(layerNumber - 1)] = relativeMagneticPermeability;
/*  939 */     this.meanMagneticUsed = true;
/*  940 */     if (this.relativeMagneticPermeabilities != null) {
/*  941 */       int n = this.relativeMagneticPermeabilities[0].length;
/*  942 */       for (int i = 0; i < n; i++) this.relativeMagneticPermeabilities[i][(layerNumber - 1)] = relativeMagneticPermeability;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRelativeMagneticPermeabilities(double relativeMagneticPermeability, int layerNumber)
/*      */   {
/*  948 */     this.meanRelativeMagneticPermeabilities[(layerNumber - 1)].setReal(relativeMagneticPermeability);
/*  949 */     this.meanMagneticUsed = true;
/*  950 */     if (this.relativeMagneticPermeabilities != null) {
/*  951 */       int n = this.relativeMagneticPermeabilities[0].length;
/*  952 */       for (int i = 0; i < n; i++) this.relativeMagneticPermeabilities[i][(layerNumber - 1)] = this.meanRelativeMagneticPermeabilities[(layerNumber - 1)];
/*      */     }
/*      */   }
/*      */   
/*      */   public Object getRelativeMagneticPermeabilities()
/*      */   {
/*  958 */     if (this.numberOfWavelengths == 1) {
/*  959 */       Complex[] ret = this.relativeMagneticPermeabilities[0];
/*  960 */       return ret;
/*      */     }
/*      */     
/*  963 */     return this.relativeMagneticPermeabilities;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getReflectivities()
/*      */   {
/*  971 */     checkWhichCalculation();
/*  972 */     if (this.singleReflectCalculated) {
/*  973 */       return this.reflectivities[0];
/*      */     }
/*      */     
/*  976 */     if (this.angularReflectCalculated) {
/*  977 */       return this.reflectivities[0];
/*      */     }
/*      */     
/*  980 */     if (this.wavelengthReflectCalculated) {
/*  981 */       double[] ret = new double[this.numberOfWavelengths];
/*  982 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectivities[i][0];
/*  983 */       return ret;
/*      */     }
/*      */     
/*  986 */     if (this.wavelengthAndAngularReflectCalculated) {
/*  987 */       return this.reflectivities;
/*      */     }
/*      */     
/*  990 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEreflectionCoefficients()
/*      */   {
/*  999 */     checkWhichCalculation();
/* 1000 */     if (this.singleReflectCalculated) {
/* 1001 */       return this.reflectCoeffTE[0];
/*      */     }
/*      */     
/* 1004 */     if (this.angularReflectCalculated) {
/* 1005 */       return this.reflectCoeffTE[0];
/*      */     }
/*      */     
/* 1008 */     if (this.wavelengthReflectCalculated) {
/* 1009 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1010 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectCoeffTE[i][0];
/* 1011 */       return ret;
/*      */     }
/*      */     
/* 1014 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1015 */       return this.reflectCoeffTE;
/*      */     }
/*      */     
/* 1018 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMreflectionCoefficients()
/*      */   {
/* 1027 */     checkWhichCalculation();
/* 1028 */     if (this.singleReflectCalculated) {
/* 1029 */       return this.reflectCoeffTM[0];
/*      */     }
/*      */     
/* 1032 */     if (this.angularReflectCalculated) {
/* 1033 */       return this.reflectCoeffTM[0];
/*      */     }
/*      */     
/* 1036 */     if (this.wavelengthReflectCalculated) {
/* 1037 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1038 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectCoeffTM[i][0];
/* 1039 */       return ret;
/*      */     }
/*      */     
/* 1042 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1043 */       return this.reflectCoeffTM;
/*      */     }
/*      */     
/* 1046 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTransmissivities()
/*      */   {
/* 1057 */     checkWhichCalculation();
/* 1058 */     if (this.singleReflectCalculated) {
/* 1059 */       return this.transmissivities[0];
/*      */     }
/*      */     
/* 1062 */     if (this.angularReflectCalculated) {
/* 1063 */       return this.transmissivities[0];
/*      */     }
/*      */     
/* 1066 */     if (this.wavelengthReflectCalculated) {
/* 1067 */       double[] ret = new double[this.numberOfWavelengths];
/* 1068 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmissivities[i][0];
/* 1069 */       return ret;
/*      */     }
/*      */     
/* 1072 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1073 */       return this.transmissivities;
/*      */     }
/*      */     
/* 1076 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getPowerLoss()
/*      */   {
/* 1085 */     checkWhichCalculation();
/* 1086 */     if (this.singleReflectCalculated) {
/* 1087 */       return this.powerLosses[0];
/*      */     }
/*      */     
/* 1090 */     if (this.angularReflectCalculated) {
/* 1091 */       return this.powerLosses[0];
/*      */     }
/*      */     
/* 1094 */     if (this.wavelengthReflectCalculated) {
/* 1095 */       double[] ret = new double[this.numberOfWavelengths];
/* 1096 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.powerLosses[i][0];
/* 1097 */       return ret;
/*      */     }
/*      */     
/* 1100 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1101 */       return this.powerLosses;
/*      */     }
/*      */     
/* 1104 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTransmissionAnglesInRadians()
/*      */   {
/* 1113 */     checkWhichCalculation();
/* 1114 */     if (this.singleReflectCalculated) {
/* 1115 */       return this.transmitAnglesRad[0];
/*      */     }
/*      */     
/* 1118 */     if (this.angularReflectCalculated) {
/* 1119 */       return this.transmitAnglesRad[0];
/*      */     }
/*      */     
/* 1122 */     if (this.wavelengthReflectCalculated) {
/* 1123 */       double[] ret = new double[this.numberOfWavelengths];
/* 1124 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitAnglesRad[i][0];
/* 1125 */       return ret;
/*      */     }
/*      */     
/* 1128 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1129 */       return this.transmitAnglesRad;
/*      */     }
/*      */     
/* 1132 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTransmissionAnglesInDegrees()
/*      */   {
/* 1141 */     checkWhichCalculation();
/* 1142 */     if (this.singleReflectCalculated) {
/* 1143 */       return this.transmitAnglesDeg[0];
/*      */     }
/*      */     
/* 1146 */     if (this.angularReflectCalculated) {
/* 1147 */       return this.transmitAnglesDeg[0];
/*      */     }
/*      */     
/* 1150 */     if (this.wavelengthReflectCalculated) {
/* 1151 */       double[] ret = new double[this.numberOfWavelengths];
/* 1152 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitAnglesDeg[i][0];
/* 1153 */       return ret;
/*      */     }
/*      */     
/* 1156 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1157 */       return this.transmitAnglesDeg;
/*      */     }
/*      */     
/* 1160 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEtransmissionCoefficients()
/*      */   {
/* 1169 */     checkWhichCalculation();
/* 1170 */     if (this.singleReflectCalculated) {
/* 1171 */       return this.transmitCoeffTE[0];
/*      */     }
/*      */     
/* 1174 */     if (this.angularReflectCalculated) {
/* 1175 */       return this.transmitCoeffTE[0];
/*      */     }
/*      */     
/* 1178 */     if (this.wavelengthReflectCalculated) {
/* 1179 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1180 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitCoeffTE[i][0];
/* 1181 */       return ret;
/*      */     }
/*      */     
/* 1184 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1185 */       return this.transmitCoeffTE;
/*      */     }
/*      */     
/* 1188 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMtransmissionCoefficients()
/*      */   {
/* 1197 */     checkWhichCalculation();
/* 1198 */     if (this.singleReflectCalculated) {
/* 1199 */       return this.transmitCoeffTM[0];
/*      */     }
/*      */     
/* 1202 */     if (this.angularReflectCalculated) {
/* 1203 */       return this.transmitCoeffTM[0];
/*      */     }
/*      */     
/* 1206 */     if (this.wavelengthReflectCalculated) {
/* 1207 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1208 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitCoeffTM[i][0];
/* 1209 */       return ret;
/*      */     }
/*      */     
/* 1212 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1213 */       return this.transmitCoeffTM;
/*      */     }
/*      */     
/* 1216 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEreflectionPhaseShiftDeg()
/*      */   {
/* 1228 */     checkWhichCalculation();
/* 1229 */     if (this.singleReflectCalculated) {
/* 1230 */       return this.reflectPhaseShiftDegTE[0];
/*      */     }
/*      */     
/* 1233 */     if (this.angularReflectCalculated) {
/* 1234 */       return this.reflectPhaseShiftDegTE[0];
/*      */     }
/*      */     
/* 1237 */     if (this.wavelengthReflectCalculated) {
/* 1238 */       double[] ret = new double[this.numberOfWavelengths];
/* 1239 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectPhaseShiftDegTE[i][0];
/* 1240 */       return ret;
/*      */     }
/*      */     
/* 1243 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1244 */       return this.reflectPhaseShiftDegTE;
/*      */     }
/*      */     
/* 1247 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEreflectionPhaseShiftRad()
/*      */   {
/* 1256 */     checkWhichCalculation();
/* 1257 */     if (this.singleReflectCalculated) {
/* 1258 */       return this.reflectPhaseShiftRadTE[0];
/*      */     }
/*      */     
/* 1261 */     if (this.angularReflectCalculated) {
/* 1262 */       return this.reflectPhaseShiftRadTE[0];
/*      */     }
/*      */     
/* 1265 */     if (this.wavelengthReflectCalculated) {
/* 1266 */       double[] ret = new double[this.numberOfWavelengths];
/* 1267 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectPhaseShiftRadTE[i][0];
/* 1268 */       return ret;
/*      */     }
/*      */     
/* 1271 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1272 */       return this.reflectPhaseShiftRadTE;
/*      */     }
/*      */     
/* 1275 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMreflectionPhaseShiftDeg()
/*      */   {
/* 1284 */     checkWhichCalculation();
/* 1285 */     if (this.singleReflectCalculated) {
/* 1286 */       return this.reflectPhaseShiftDegTM[0];
/*      */     }
/*      */     
/* 1289 */     if (this.angularReflectCalculated) {
/* 1290 */       return this.reflectPhaseShiftDegTM[0];
/*      */     }
/*      */     
/* 1293 */     if (this.wavelengthReflectCalculated) {
/* 1294 */       double[] ret = new double[this.numberOfWavelengths];
/* 1295 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectPhaseShiftDegTM[i][0];
/* 1296 */       return ret;
/*      */     }
/*      */     
/* 1299 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1300 */       return this.reflectPhaseShiftDegTM;
/*      */     }
/*      */     
/* 1303 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMreflectionPhaseShiftRad()
/*      */   {
/* 1312 */     checkWhichCalculation();
/* 1313 */     if (this.singleReflectCalculated) {
/* 1314 */       return this.reflectPhaseShiftRadTM[0];
/*      */     }
/*      */     
/* 1317 */     if (this.angularReflectCalculated) {
/* 1318 */       return this.reflectPhaseShiftRadTM[0];
/*      */     }
/*      */     
/* 1321 */     if (this.wavelengthReflectCalculated) {
/* 1322 */       double[] ret = new double[this.numberOfWavelengths];
/* 1323 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.reflectPhaseShiftRadTM[i][0];
/* 1324 */       return ret;
/*      */     }
/*      */     
/* 1327 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1328 */       return this.reflectPhaseShiftRadTM;
/*      */     }
/*      */     
/* 1331 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEtransmissionPhaseShiftDeg()
/*      */   {
/* 1340 */     checkWhichCalculation();
/* 1341 */     if (this.singleReflectCalculated) {
/* 1342 */       return this.transmitPhaseShiftDegTE[0];
/*      */     }
/*      */     
/* 1345 */     if (this.angularReflectCalculated) {
/* 1346 */       return this.transmitPhaseShiftDegTE[0];
/*      */     }
/*      */     
/* 1349 */     if (this.wavelengthReflectCalculated) {
/* 1350 */       double[] ret = new double[this.numberOfWavelengths];
/* 1351 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitPhaseShiftDegTE[i][0];
/* 1352 */       return ret;
/*      */     }
/*      */     
/* 1355 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1356 */       return this.transmitPhaseShiftDegTE;
/*      */     }
/*      */     
/* 1359 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTEtransmissionPhaseShiftRad()
/*      */   {
/* 1368 */     checkWhichCalculation();
/* 1369 */     if (this.singleReflectCalculated) {
/* 1370 */       return this.transmitPhaseShiftRadTE[0];
/*      */     }
/*      */     
/* 1373 */     if (this.angularReflectCalculated) {
/* 1374 */       return this.transmitPhaseShiftRadTE[0];
/*      */     }
/*      */     
/* 1377 */     if (this.wavelengthReflectCalculated) {
/* 1378 */       double[] ret = new double[this.numberOfWavelengths];
/* 1379 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitPhaseShiftRadTE[i][0];
/* 1380 */       return ret;
/*      */     }
/*      */     
/* 1383 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1384 */       return this.transmitPhaseShiftRadTE;
/*      */     }
/*      */     
/* 1387 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMtransmissionPhaseShiftDeg()
/*      */   {
/* 1396 */     checkWhichCalculation();
/* 1397 */     if (this.singleReflectCalculated) {
/* 1398 */       return this.transmitPhaseShiftDegTM[0];
/*      */     }
/*      */     
/* 1401 */     if (this.angularReflectCalculated) {
/* 1402 */       return this.transmitPhaseShiftDegTM[0];
/*      */     }
/*      */     
/* 1405 */     if (this.wavelengthReflectCalculated) {
/* 1406 */       double[] ret = new double[this.numberOfWavelengths];
/* 1407 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitPhaseShiftDegTM[i][0];
/* 1408 */       return ret;
/*      */     }
/*      */     
/* 1411 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1412 */       return this.transmitPhaseShiftDegTM;
/*      */     }
/*      */     
/* 1415 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTMtransmissionPhaseShiftRad()
/*      */   {
/* 1424 */     checkWhichCalculation();
/* 1425 */     if (this.singleReflectCalculated) {
/* 1426 */       return this.transmitPhaseShiftRadTM[0];
/*      */     }
/*      */     
/* 1429 */     if (this.angularReflectCalculated) {
/* 1430 */       return this.transmitPhaseShiftRadTM[0];
/*      */     }
/*      */     
/* 1433 */     if (this.wavelengthReflectCalculated) {
/* 1434 */       double[] ret = new double[this.numberOfWavelengths];
/* 1435 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.transmitPhaseShiftRadTM[i][0];
/* 1436 */       return ret;
/*      */     }
/*      */     
/* 1439 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1440 */       return this.transmitPhaseShiftRadTM;
/*      */     }
/*      */     
/* 1443 */     return null;
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
/*      */   public Object getEvanescentFields(double fieldDistance)
/*      */   {
/* 1456 */     this.fieldDistance = fieldDistance;
/* 1457 */     return getEvanescentFields();
/*      */   }
/*      */   
/*      */   public Object getEvanescentFields()
/*      */   {
/* 1462 */     checkWhichCalculation();
/* 1463 */     if (this.singleReflectCalculated) {
/* 1464 */       return this.evanescentFields[0];
/*      */     }
/*      */     
/* 1467 */     if (this.angularReflectCalculated) {
/* 1468 */       return this.evanescentFields[0];
/*      */     }
/*      */     
/* 1471 */     if (this.wavelengthReflectCalculated) {
/* 1472 */       double[] ret = new double[this.numberOfWavelengths];
/* 1473 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.evanescentFields[i][0];
/* 1474 */       return ret;
/*      */     }
/*      */     
/* 1477 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1478 */       return this.evanescentFields;
/*      */     }
/*      */     
/* 1481 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getPenetrationDepths()
/*      */   {
/* 1490 */     checkWhichCalculation();
/* 1491 */     if (this.singleReflectCalculated) {
/* 1492 */       return this.penetrationDepths[0];
/*      */     }
/*      */     
/* 1495 */     if (this.angularReflectCalculated) {
/* 1496 */       return this.penetrationDepths[0];
/*      */     }
/*      */     
/* 1499 */     if (this.wavelengthReflectCalculated) {
/* 1500 */       double[] ret = new double[this.numberOfWavelengths];
/* 1501 */       for (int i = 0; i < this.numberOfWavelengths; i++) ret[i] = this.penetrationDepths[i][0];
/* 1502 */       return ret;
/*      */     }
/*      */     
/* 1505 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1506 */       return this.penetrationDepths;
/*      */     }
/*      */     
/* 1509 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getKoVectors()
/*      */   {
/* 1520 */     checkWhichCalculation();
/* 1521 */     if (this.singleReflectCalculated) {
/* 1522 */       return this.koVector[0][0][0];
/*      */     }
/*      */     
/* 1525 */     if (this.angularReflectCalculated) {
/* 1526 */       return this.koVector[0][0][0];
/*      */     }
/*      */     
/* 1529 */     if (this.wavelengthReflectCalculated) {
/* 1530 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1531 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1532 */         ret[i] = this.koVector[i][0][0];
/*      */       }
/* 1534 */       return ret;
/*      */     }
/*      */     
/* 1537 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1538 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1539 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1540 */         ret[i] = this.koVector[i][0][0];
/*      */       }
/* 1542 */       return ret;
/*      */     }
/*      */     
/* 1545 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getKzVectors()
/*      */   {
/* 1554 */     checkWhichCalculation();
/* 1555 */     if (this.singleReflectCalculated) {
/* 1556 */       return this.kzVector[0][0][0];
/*      */     }
/*      */     
/* 1559 */     if (this.angularReflectCalculated) {
/* 1560 */       Complex[] ret = Complex.oneDarray(this.numberOfIncidentAngles);
/* 1561 */       for (int i = 0; i < this.numberOfIncidentAngles; i++) {
/* 1562 */         ret[i] = this.kzVector[0][i][0];
/*      */       }
/* 1564 */       return ret;
/*      */     }
/*      */     
/* 1567 */     if (this.wavelengthReflectCalculated) {
/* 1568 */       Complex[] ret = Complex.oneDarray(this.numberOfWavelengths);
/* 1569 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1570 */         ret[i] = this.kzVector[i][0][0];
/*      */       }
/* 1572 */       return ret;
/*      */     }
/*      */     
/* 1575 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1576 */       Complex[][] ret = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
/* 1577 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1578 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 1579 */           ret[i][j] = this.kzVector[i][j][0];
/*      */         }
/*      */       }
/* 1582 */       return ret;
/*      */     }
/*      */     
/* 1585 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getKvectors()
/*      */   {
/* 1594 */     checkWhichCalculation();
/* 1595 */     if (this.singleReflectCalculated) {
/* 1596 */       return this.kVector[0][0];
/*      */     }
/*      */     
/* 1599 */     if (this.angularReflectCalculated) {
/* 1600 */       return this.kVector[0];
/*      */     }
/*      */     
/* 1603 */     if (this.wavelengthReflectCalculated) {
/* 1604 */       Complex[][] ret = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/* 1605 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1606 */         for (int j = 0; i < this.numberOfLayers; i++) {
/* 1607 */           ret[i][j] = this.kVector[i][0][j];
/*      */         }
/*      */       }
/* 1610 */       return ret;
/*      */     }
/*      */     
/* 1613 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1614 */       Complex[][] ret = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/* 1615 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1616 */         for (int j = 0; i < this.numberOfLayers; i++) {
/* 1617 */           ret[i][j] = this.kVector[i][0][j];
/*      */         }
/*      */       }
/* 1620 */       return ret;
/*      */     }
/*      */     
/* 1623 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getKxVectors()
/*      */   {
/* 1632 */     checkWhichCalculation();
/* 1633 */     if (this.singleReflectCalculated) {
/* 1634 */       return this.kxVector[0][0];
/*      */     }
/*      */     
/* 1637 */     if (this.angularReflectCalculated) {
/* 1638 */       return this.kxVector[0];
/*      */     }
/*      */     
/* 1641 */     if (this.wavelengthReflectCalculated) {
/* 1642 */       Complex[][] ret = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
/* 1643 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1644 */         for (int j = 0; i < this.numberOfLayers; i++) {
/* 1645 */           ret[i][j] = this.kxVector[i][0][j];
/*      */         }
/*      */       }
/* 1648 */       return ret;
/*      */     }
/*      */     
/* 1651 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 1652 */       return this.kxVector;
/*      */     }
/*      */     
/* 1655 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void resetPlotAxisAsFrequency()
/*      */   {
/* 1666 */     this.wavelengthAxisOption = 2;
/*      */   }
/*      */   
/*      */   public void resetPlotAxisAsRadians()
/*      */   {
/* 1671 */     this.wavelengthAxisOption = 3;
/*      */   }
/*      */   
/*      */   public void resetPlotAxisAsWavelength()
/*      */   {
/* 1676 */     this.wavelengthAxisOption = 1;
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotReflectivities()
/*      */   {
/* 1682 */     String legend = "Polarisation mode: " + this.mode;
/* 1683 */     plotReflectivities(legend);
/*      */   }
/*      */   
/*      */   public void plotReflectivities(String legend)
/*      */   {
/* 1688 */     checkWhichCalculation();
/* 1689 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/*      */     
/* 1692 */     String graphLegendExtra = " Reflectivities";
/* 1693 */     String yLegend = "Reflectivity";
/* 1694 */     String yUnits = " ";
/* 1695 */     plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.reflectivities);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTransmissivities()
/*      */   {
/* 1701 */     String legend = "Polarisation mode: " + this.mode;
/* 1702 */     plotTransmissivities(legend);
/*      */   }
/*      */   
/*      */   public void plotTransmissivities(String legend)
/*      */   {
/* 1707 */     checkWhichCalculation();
/* 1708 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1710 */     String graphLegendExtra = " Transmissivities";
/* 1711 */     String yLegend = "Transmissivity";
/* 1712 */     String yUnits = " ";
/* 1713 */     plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmissivities);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPowerLosses()
/*      */   {
/* 1719 */     String legend = "Polarisation mode: " + this.mode;
/* 1720 */     plotPowerLosses(legend);
/*      */   }
/*      */   
/*      */   public void plotPowerLosses(String legend)
/*      */   {
/* 1725 */     checkWhichCalculation();
/* 1726 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1728 */     String graphLegendExtra = " Power Losses in decibels relative to an incident power of 1 mW";
/* 1729 */     String yLegend = "Power Losses";
/* 1730 */     String yUnits = "dBm";
/*      */     
/* 1732 */     plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.powerLosses);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTransmissionAngles()
/*      */   {
/* 1738 */     String legend = "Polarisation mode: " + this.mode;
/* 1739 */     plotTransmissionAngles(legend);
/*      */   }
/*      */   
/*      */   public void plotTransmissionAngles(String legend)
/*      */   {
/* 1744 */     checkWhichCalculation();
/* 1745 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1747 */     String graphLegendExtra = " Transmission angles (degrees)";
/* 1748 */     String yLegend = "Transmission angle";
/* 1749 */     String yUnits = "degrees";
/* 1750 */     plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmitAnglesDeg);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotAbsTEreflectionCoefficients()
/*      */   {
/* 1756 */     String legend = "Polarisation mode: " + this.mode;
/* 1757 */     plotAbsTEreflectionCoefficients(legend);
/*      */   }
/*      */   
/*      */   public void plotAbsTEreflectionCoefficients(String legend)
/*      */   {
/* 1762 */     checkWhichCalculation();
/* 1763 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1765 */     if (this.teFraction == 0.0D) {
/* 1766 */       System.out.println("No TE transmission coefficient plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 1769 */       double[][] absTEr = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 1770 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1771 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 1772 */           absTEr[i][j] = this.reflectCoeffTE[i][j].abs();
/*      */         }
/*      */       }
/*      */       
/* 1776 */       String graphLegendExtra = " Absolute values of the TE reflection coefficients";
/* 1777 */       String yLegend = "|TE Reflection Coefficient|";
/* 1778 */       String yUnits = " ";
/*      */       
/* 1780 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, absTEr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotAbsTMreflectionCoefficients()
/*      */   {
/* 1787 */     String legend = "Polarisation mode: " + this.mode;
/* 1788 */     plotAbsTMreflectionCoefficients(legend);
/*      */   }
/*      */   
/*      */   public void plotAbsTMreflectionCoefficients(String legend)
/*      */   {
/* 1793 */     checkWhichCalculation();
/* 1794 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1796 */     if (this.tmFraction == 0.0D) {
/* 1797 */       System.out.println("No TM transmission coefficient plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 1800 */       double[][] absTMr = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 1801 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1802 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 1803 */           absTMr[i][j] = this.reflectCoeffTM[i][j].abs();
/*      */         }
/*      */       }
/*      */       
/* 1807 */       String graphLegendExtra = " Absolute values of the TM reflection coefficients";
/* 1808 */       String yLegend = "|TM Reflection Coefficient|";
/* 1809 */       String yUnits = " ";
/*      */       
/* 1811 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, absTMr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotAbsTEtransmissionCoefficients()
/*      */   {
/* 1818 */     String legend = "Polarisation mode: " + this.mode;
/* 1819 */     plotAbsTEtransmissionCoefficients(legend);
/*      */   }
/*      */   
/*      */   public void plotAbsTEtransmissionCoefficients(String legend)
/*      */   {
/* 1824 */     checkWhichCalculation();
/* 1825 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1827 */     if (this.teFraction == 0.0D) {
/* 1828 */       System.out.println("No TE transmission coefficient plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 1831 */       double[][] absTEt = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 1832 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1833 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 1834 */           absTEt[i][j] = this.transmitCoeffTE[i][j].abs();
/*      */         }
/*      */       }
/*      */       
/* 1838 */       String graphLegendExtra = " Absolute values of the TE transmission coefficients";
/* 1839 */       String yLegend = "|TE Transmission Coefficient|";
/* 1840 */       String yUnits = " ";
/*      */       
/* 1842 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, absTEt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotAbsTMtransmissionCoefficients()
/*      */   {
/* 1849 */     String legend = "Polarisation mode: " + this.mode;
/* 1850 */     plotAbsTMtransmissionCoefficients(legend);
/*      */   }
/*      */   
/*      */   public void plotAbsTMtransmissionCoefficients(String legend)
/*      */   {
/* 1855 */     checkWhichCalculation();
/* 1856 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1858 */     if (this.tmFraction == 0.0D) {
/* 1859 */       System.out.println("No TM transmission coefficient plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 1862 */       double[][] absTMt = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 1863 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 1864 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 1865 */           absTMt[i][j] = this.transmitCoeffTM[i][j].abs();
/*      */         }
/*      */       }
/*      */       
/* 1869 */       String graphLegendExtra = " Absolute values of the TM transmission coefficients";
/* 1870 */       String yLegend = "|TM Transmission Coefficient|";
/* 1871 */       String yUnits = " ";
/*      */       
/* 1873 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, absTMt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotEvanescentFields()
/*      */   {
/* 1880 */     String legend = "Polarisation mode: " + this.mode;
/* 1881 */     plotEvanescentFields(legend);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotEvanescentFields(double distanceIntoField)
/*      */   {
/* 1887 */     this.fieldDistance = this.fieldDistance;
/* 1888 */     String legend = "Polarisation mode: " + this.mode;
/* 1889 */     plotEvanescentFields(legend);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotEvanescentFields(double fieldDistance, String legend)
/*      */   {
/* 1895 */     this.fieldDistance = fieldDistance;
/* 1896 */     plotEvanescentFields(legend);
/*      */   }
/*      */   
/*      */   public void plotEvanescentFields(String legend)
/*      */   {
/* 1901 */     checkWhichCalculation();
/* 1902 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1904 */     String graphLegendExtra = " Integrated Evanescent Field Intensities to a depth of " + this.fieldDistance + " metres";
/* 1905 */     String yLegend = "Evanescent Field intensity";
/* 1906 */     String yUnits = " ";
/*      */     
/* 1908 */     plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.evanescentFields);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotPenetrationDepths()
/*      */   {
/* 1914 */     String legend = "Polarisation mode: " + this.mode;
/* 1915 */     plotPenetrationDepths(legend);
/*      */   }
/*      */   
/*      */   public void plotPenetrationDepths(String graphLegend)
/*      */   {
/* 1920 */     checkWhichCalculation();
/* 1921 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1923 */     String graphLegendExtra = " Evanescent Field Penetration Depths";
/* 1924 */     String yLegend = "Penetration Depth";
/* 1925 */     String yUnits = "metres";
/*      */     
/* 1927 */     plotSimulation(graphLegend, graphLegendExtra, yLegend, yUnits, this.penetrationDepths);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTEreflectionPhaseShiftDeg()
/*      */   {
/* 1933 */     String legend = "Polarisation mode: " + this.mode;
/* 1934 */     plotTEreflectionPhaseShiftDeg(legend);
/*      */   }
/*      */   
/*      */   public void plotTEreflectionPhaseShiftDeg(String legend)
/*      */   {
/* 1939 */     checkWhichCalculation();
/* 1940 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1942 */     if (this.teFraction == 0.0D) {
/* 1943 */       System.out.println("No TE phase shift plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 1946 */       String graphLegendExtra = " Phase Shift on Reflection (TE mode)";
/* 1947 */       String yLegend = "Phase shift";
/* 1948 */       String yUnits = "degrees ";
/* 1949 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.reflectPhaseShiftDegTE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTMreflectionPhaseShiftDeg()
/*      */   {
/* 1956 */     String legend = "Polarisation mode: " + this.mode;
/* 1957 */     plotTMreflectionPhaseShiftDeg(legend);
/*      */   }
/*      */   
/*      */   public void plotTMreflectionPhaseShiftDeg(String legend)
/*      */   {
/* 1962 */     checkWhichCalculation();
/* 1963 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1965 */     if (this.tmFraction == 0.0D) {
/* 1966 */       System.out.println("No TM phase shift plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 1969 */       String graphLegendExtra = " Phase Shift on Reflection (TM mode)";
/* 1970 */       String yLegend = "Phase shift";
/* 1971 */       String yUnits = "degrees ";
/* 1972 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.reflectPhaseShiftDegTM);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTEreflectionPhaseShiftRad()
/*      */   {
/* 1979 */     String legend = "Polarisation mode: " + this.mode;
/* 1980 */     plotTEreflectionPhaseShiftRad(legend);
/*      */   }
/*      */   
/*      */   public void plotTEreflectionPhaseShiftRad(String legend)
/*      */   {
/* 1985 */     checkWhichCalculation();
/* 1986 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 1988 */     if (this.teFraction == 0.0D) {
/* 1989 */       System.out.println("No TE phase shift plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 1992 */       String graphLegendExtra = " Phase Shift on Reflection (TE mode)";
/* 1993 */       String yLegend = "Phase shift";
/* 1994 */       String yUnits = "radians ";
/* 1995 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.reflectPhaseShiftRadTE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotTMreflectionPhaseShiftRad()
/*      */   {
/* 2003 */     String legend = "Polarisation mode: " + this.mode;
/* 2004 */     plotTMreflectionPhaseShiftRad(legend);
/*      */   }
/*      */   
/*      */   public void plotTMreflectionPhaseShiftRad(String legend)
/*      */   {
/* 2009 */     checkWhichCalculation();
/* 2010 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 2012 */     if (this.tmFraction == 0.0D) {
/* 2013 */       System.out.println("No TM phase shift plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 2016 */       String graphLegendExtra = " Phase Shift on Reflection (TM mode)";
/* 2017 */       String yLegend = "Phase shift";
/* 2018 */       String yUnits = "radians ";
/* 2019 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.reflectPhaseShiftRadTM);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTEtransmissionPhaseShiftDeg()
/*      */   {
/* 2026 */     String legend = "Polarisation mode: " + this.mode;
/* 2027 */     plotTEtransmissionPhaseShiftDeg(legend);
/*      */   }
/*      */   
/*      */   public void plotTEtransmissionPhaseShiftDeg(String legend)
/*      */   {
/* 2032 */     checkWhichCalculation();
/* 2033 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 2035 */     if (this.teFraction == 0.0D) {
/* 2036 */       System.out.println("No TE phase shift plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 2039 */       String graphLegendExtra = " Phase Shift on Transmission (TE mode)";
/* 2040 */       String yLegend = "Phase shift";
/* 2041 */       String yUnits = "degrees ";
/* 2042 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmitPhaseShiftDegTE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTMtransmissionPhaseShiftDeg()
/*      */   {
/* 2049 */     String legend = "Polarisation mode: " + this.mode;
/* 2050 */     plotTMtransmissionPhaseShiftDeg(legend);
/*      */   }
/*      */   
/*      */   public void plotTMtransmissionPhaseShiftDeg(String legend)
/*      */   {
/* 2055 */     checkWhichCalculation();
/* 2056 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 2058 */     if (this.tmFraction == 0.0D) {
/* 2059 */       System.out.println("No TM phase shift plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 2062 */       String graphLegendExtra = " Phase Shift on Transmission (TM mode)";
/* 2063 */       String yLegend = "Phase shift";
/* 2064 */       String yUnits = "degrees ";
/* 2065 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmitPhaseShiftDegTM);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTEtransmissionPhaseShiftRad()
/*      */   {
/* 2072 */     String legend = "Polarisation mode: " + this.mode;
/* 2073 */     plotTEtransmissionPhaseShiftRad(legend);
/*      */   }
/*      */   
/*      */   public void plotTEtransmissionPhaseShiftRad(String legend)
/*      */   {
/* 2078 */     checkWhichCalculation();
/* 2079 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 2081 */     if (this.teFraction == 0.0D) {
/* 2082 */       System.out.println("No TE phase shift plot displayed as no light in the TE mode");
/*      */     }
/*      */     else {
/* 2085 */       String graphLegendExtra = " Phase Shift on Transmission (TE mode)";
/* 2086 */       String yLegend = "Phase shift";
/* 2087 */       String yUnits = "radians ";
/* 2088 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmitPhaseShiftRadTE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotTMtransmissionPhaseShiftRad()
/*      */   {
/* 2095 */     String legend = "Polarisation mode: " + this.mode;
/* 2096 */     plotTMtransmissionPhaseShiftRad(legend);
/*      */   }
/*      */   
/*      */   public void plotTMtransmissionPhaseShiftRad(String legend)
/*      */   {
/* 2101 */     checkWhichCalculation();
/* 2102 */     if (this.singleReflectCalculated) { throw new IllegalArgumentException("Plot methods require more than one data point");
/*      */     }
/* 2104 */     if (this.tmFraction == 0.0D) {
/* 2105 */       System.out.println("No TM phase shift plot displayed as no light in the TM mode");
/*      */     }
/*      */     else {
/* 2108 */       String graphLegendExtra = " Phase Shift on Transmission (TM mode)";
/* 2109 */       String yLegend = "Phase shift";
/* 2110 */       String yUnits = "radians ";
/* 2111 */       plotSimulation(legend, graphLegendExtra, yLegend, yUnits, this.transmitPhaseShiftRadTM);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plotSimulation(String graphLegend, String graphLegendExtra, String yLegend, String yUnits, Object yValuesObject)
/*      */   {
/* 2119 */     Object internalArray = yValuesObject;
/* 2120 */     int nCurves = 1;
/* 2121 */     while (!((internalArray = Array.get(internalArray, 0)) instanceof Double)) nCurves++;
/* 2122 */     double[][] yValues = new double[nCurves][];
/* 2123 */     if (nCurves == 1) {
/* 2124 */       double[] temp = (double[])yValuesObject;
/* 2125 */       yValues[0] = temp;
/*      */     }
/*      */     else {
/* 2128 */       yValues = (double[][])yValuesObject;
/*      */     }
/* 2130 */     int nPoints = yValues.length;
/*      */     
/* 2132 */     int[] pointOptions = null;
/* 2133 */     double[][] plotData = (double[][])null;
/* 2134 */     String xLegend = null;
/* 2135 */     String xUnits = null;
/*      */     
/* 2137 */     if (this.angularReflectCalculated) {
/* 2138 */       pointOptions = new int[1];
/* 2139 */       pointOptions[0] = 1;
/* 2140 */       plotData = new double[2][nPoints];
/* 2141 */       plotData[0] = this.incidentAngleDeg;
/* 2142 */       plotData[1] = yValues[0];
/* 2143 */       xLegend = "Incident Angle";
/* 2144 */       xUnits = "degrees";
/*      */     }
/*      */     
/* 2147 */     if (this.wavelengthReflectCalculated) {
/* 2148 */       pointOptions = new int[1];
/* 2149 */       pointOptions[0] = 1;
/* 2150 */       plotData = new double[2][nPoints];
/* 2151 */       plotData[0] = this.wavelengths;
/* 2152 */       double[] temp = new double[this.numberOfWavelengths];
/* 2153 */       for (int i = 0; i < this.numberOfWavelengths; i++) temp[i] = yValues[i][0];
/* 2154 */       switch (this.wavelengthAxisOption) {
/* 2155 */       case 1:  plotData[0] = this.wavelengths;
/* 2156 */         plotData[1] = temp;
/* 2157 */         xLegend = "Wavelength";
/* 2158 */         xUnits = "metres";
/* 2159 */         break;
/* 2160 */       case 2:  plotData[0] = this.frequencies;
/* 2161 */         for (int i = 0; i < this.numberOfWavelengths; i++) plotData[1][(this.numberOfWavelengths - 1 - i)] = temp[i];
/* 2162 */         xLegend = "Frequency";
/* 2163 */         xUnits = "Hz";
/* 2164 */         break;
/* 2165 */       case 3:  plotData[0] = this.omega;
/* 2166 */         for (int i = 0; i < this.numberOfWavelengths; i++) plotData[1][(this.numberOfWavelengths - 1 - i)] = temp[i];
/* 2167 */         xLegend = "Radial Frequency";
/* 2168 */         xUnits = "radians";
/*      */       }
/*      */       
/*      */     }
/*      */     
/* 2173 */     if (this.wavelengthAndAngularReflectCalculated) {
/* 2174 */       pointOptions = new int[nCurves];
/* 2175 */       plotData = new double[2 * nCurves][nPoints];
/* 2176 */       for (int i = 0; i < nCurves; i++) {
/* 2177 */         pointOptions[i] = (i + 1);
/* 2178 */         plotData[(2 * i)] = this.incidentAngleDeg;
/* 2179 */         plotData[(2 * i + 1)] = yValues[i];
/*      */       }
/* 2181 */       xLegend = "Incident Angle";
/* 2182 */       xUnits = "degrees";
/*      */     }
/*      */     
/* 2185 */     PlotGraph pg = new PlotGraph(plotData);
/* 2186 */     pg.setGraphTitle("Class Reflectivity: Simulation Plot - " + graphLegendExtra);
/* 2187 */     pg.setGraphTitle2(graphLegend);
/* 2188 */     pg.setXaxisLegend(xLegend);
/* 2189 */     pg.setYaxisLegend(yLegend);
/* 2190 */     pg.setXaxisUnitsName(xUnits);
/* 2191 */     if (!yUnits.equals(" ")) pg.setYaxisUnitsName(yUnits);
/* 2192 */     pg.setLine(3);
/* 2193 */     pg.setPoint(pointOptions);
/* 2194 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void checkWhichCalculation()
/*      */   {
/* 2203 */     boolean test = false;
/* 2204 */     if (this.singleReflectCalculated) test = true;
/* 2205 */     if (this.angularReflectCalculated) test = true;
/* 2206 */     if (this.wavelengthReflectCalculated) test = true;
/* 2207 */     if (this.wavelengthAndAngularReflectCalculated) { test = true;
/*      */     }
/* 2209 */     if (test) {
/* 2210 */       if ((this.fieldDistance != Double.POSITIVE_INFINITY) && (!this.fieldIntensityCalc)) {
/* 2211 */         int nkouter = this.numberOfLayers - 1;
/* 2212 */         double integratedEvanescentField = 0.0D;
/* 2213 */         for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 2214 */           for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 2215 */             if (this.kxVector[i][j][nkouter].getReal() == 0.0D) {
/* 2216 */               double penetrationDepth = 1.0D / this.kxVector[i][j][nkouter].getImag();
/* 2217 */               integratedEvanescentField += this.teFraction * Fmath.square(this.transmitCoeffTE[i][j].abs()) * (1.0D - Math.exp(-2.0D * this.fieldDistance / penetrationDepth)) * penetrationDepth / 2.0D;
/* 2218 */               double refrTerm = this.refractiveIndices[i][0].getReal() / this.refractiveIndices[i][j].getReal();
/* 2219 */               double magnTerm = Math.sqrt(this.relativeMagneticPermeabilities[i][nkouter].getReal() / this.relativeMagneticPermeabilities[i][0].getReal());
/* 2220 */               integratedEvanescentField += this.teFraction * Fmath.square(this.transmitCoeffTM[i][j].abs()) * magnTerm * refrTerm * (1.0D - Math.exp(-2.0D * this.fieldDistance / penetrationDepth)) * penetrationDepth / 2.0D;
/*      */             }
/*      */           }
/*      */         }
/* 2224 */         this.fieldIntensityCalc = true;
/*      */       }
/*      */     }
/*      */     else {
/* 2228 */       if (this.numberOfIncidentAngles == 0) throw new IllegalArgumentException("No incident angle/s has/have been entered");
/* 2229 */       if (this.numberOfWavelengths == 0) { throw new IllegalArgumentException("No wavelength/s has/have been entered");
/*      */       }
/* 2231 */       if (this.numberOfWavelengths > 1) { sortWavelengths();
/*      */       }
/*      */       
/*      */ 
/* 2235 */       this.koVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
/* 2236 */       this.kzVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
/* 2237 */       this.kVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
/* 2238 */       this.kxVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
/*      */       
/* 2240 */       for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 2241 */         for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 2242 */           for (int k = 0; k < this.numberOfLayers; k++)
/*      */           {
/* 2244 */             this.koVector[i][j][k].reset(6.283185307179586D / this.wavelengths[i], 0.0D);
/*      */             
/*      */ 
/* 2247 */             this.kVector[i][j][k] = this.koVector[i][j][k].times(this.refractiveIndices[i][k]).times(Complex.sqrt(this.relativeMagneticPermeabilities[i][k]));
/*      */             
/*      */ 
/* 2250 */             this.kzVector[i][j][k] = this.koVector[i][j][k].times(this.refractiveIndices[i][0]).times(Complex.sqrt(this.relativeMagneticPermeabilities[i][0]));
/* 2251 */             this.kzVector[i][j][k] = this.kzVector[i][j][k].times(Math.sin(this.incidentAngleRad[j]));
/*      */             
/*      */ 
/* 2254 */             this.kxVector[i][j][k] = Complex.square(this.kVector[i][j][k]).minus(Complex.square(this.kzVector[i][j][k]));
/* 2255 */             this.kxVector[i][j][k] = Complex.sqrt(this.kxVector[i][j][k]);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2263 */       this.reflectivities = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2264 */       this.transmissivities = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2265 */       this.powerLosses = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2266 */       this.reflectCoeffTE = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
/* 2267 */       this.reflectCoeffTM = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
/* 2268 */       this.transmitCoeffTE = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
/* 2269 */       this.transmitCoeffTM = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
/* 2270 */       this.evanescentFields = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2271 */       this.penetrationDepths = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2272 */       this.transmitAnglesRad = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2273 */       this.transmitAnglesDeg = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2274 */       this.reflectPhaseShiftRadTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2275 */       this.reflectPhaseShiftRadTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2276 */       this.reflectPhaseShiftDegTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2277 */       this.reflectPhaseShiftDegTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2278 */       this.transmitPhaseShiftRadTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2279 */       this.transmitPhaseShiftRadTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2280 */       this.transmitPhaseShiftDegTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/* 2281 */       this.transmitPhaseShiftDegTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
/*      */       
/*      */ 
/* 2284 */       scan();
/*      */     }
/*      */   }
/*      */   
/*      */   public void scan()
/*      */   {
/* 2290 */     if (!this.wavelSet) throw new IllegalArgumentException("No wavelength has been entered");
/* 2291 */     if (!this.refractSet) throw new IllegalArgumentException("No, or not all, refractive indices have been entered");
/* 2292 */     if (!this.thickSet) throw new IllegalArgumentException("No, or not all, layer thicknesses have been entered");
/* 2293 */     if (!this.incidentAngleSet) throw new IllegalArgumentException("No incident angle has been entered");
/* 2294 */     if (!this.modeSet) { throw new IllegalArgumentException("No polaristaion mode (TE, TM, unpolarised or mixed[angle to be entered]) has been entered");
/*      */     }
/* 2296 */     this.singleReflectCalculated = false;
/* 2297 */     this.angularReflectCalculated = false;
/* 2298 */     this.wavelengthReflectCalculated = false;
/* 2299 */     this.wavelengthAndAngularReflectCalculated = false;
/*      */     
/* 2301 */     for (int i = 0; i < this.numberOfWavelengths; i++) {
/* 2302 */       for (int j = 0; j < this.numberOfIncidentAngles; j++) {
/* 2303 */         calcReflectivity(i, j);
/*      */       }
/*      */     }
/*      */     
/* 2307 */     if (this.numberOfWavelengths == 1) {
/* 2308 */       if (this.numberOfIncidentAngles == 1) {
/* 2309 */         this.singleReflectCalculated = true;
/*      */       }
/*      */       else {
/* 2312 */         this.angularReflectCalculated = true;
/*      */       }
/*      */       
/*      */     }
/* 2316 */     else if (this.numberOfIncidentAngles == 1) {
/* 2317 */       this.wavelengthReflectCalculated = true;
/*      */     }
/*      */     else {
/* 2320 */       this.wavelengthAndAngularReflectCalculated = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void calcReflectivity(int wavelengthIndex, int angleIndex)
/*      */   {
/* 2328 */     double[] ret1 = new double[6];
/*      */     
/* 2330 */     if (this.teFraction > 0.0D) {
/* 2331 */       ret1 = calcTEreflectivity(wavelengthIndex, angleIndex);
/*      */     }
/* 2333 */     if (this.tmFraction > 0.0D) {
/* 2334 */       double[] ret2 = calcTMreflectivity(wavelengthIndex, angleIndex);
/* 2335 */       ret1[0] = (this.teFraction * ret1[0] + this.tmFraction * ret2[0]);
/* 2336 */       ret1[1] = (this.teFraction * ret1[1] + this.tmFraction * ret2[1]);
/* 2337 */       ret1[2] = (this.teFraction * ret1[2] + this.tmFraction * ret2[2]);
/* 2338 */       ret1[3] = (this.teFraction * ret1[3] + this.tmFraction * ret2[3]);
/* 2339 */       ret1[4] = (this.teFraction * ret1[4] + this.tmFraction * ret2[4]);
/* 2340 */       ret1[5] = (this.teFraction * ret1[5] + this.tmFraction * ret2[5]);
/*      */     }
/*      */     
/* 2343 */     this.reflectivities[wavelengthIndex][angleIndex] = ret1[0];
/* 2344 */     this.transmissivities[wavelengthIndex][angleIndex] = ret1[1];
/* 2345 */     this.transmitAnglesRad[wavelengthIndex][angleIndex] = ret1[2];
/* 2346 */     this.transmitAnglesDeg[wavelengthIndex][angleIndex] = Math.toDegrees(ret1[2]);
/* 2347 */     this.evanescentFields[wavelengthIndex][angleIndex] = ret1[3];
/* 2348 */     this.penetrationDepths[wavelengthIndex][angleIndex] = ret1[4];
/* 2349 */     this.powerLosses[wavelengthIndex][angleIndex] = ret1[5];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] calcTEreflectivity(int wavelengthIndex, int angleIndex)
/*      */   {
/* 2356 */     Complex tempc1 = Complex.zero();
/* 2357 */     Complex tempc2 = Complex.zero();
/* 2358 */     Complex tempc3 = Complex.zero();
/* 2359 */     Complex tempc4 = Complex.zero();
/*      */     
/* 2361 */     double penetrationDepth = 0.0D;
/*      */     
/* 2363 */     if (this.numberOfLayers == 2) {
/* 2364 */       tempc1 = this.relativeMagneticPermeabilities[wavelengthIndex][1].times(this.kxVector[wavelengthIndex][angleIndex][0]);
/* 2365 */       tempc2 = this.relativeMagneticPermeabilities[wavelengthIndex][0].times(this.kxVector[wavelengthIndex][angleIndex][1]);
/* 2366 */       tempc3 = tempc1.minus(tempc2);
/* 2367 */       tempc4 = tempc1.plus(tempc2);
/* 2368 */       this.reflectCoeffTE[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/*      */       
/* 2370 */       tempc3 = tempc1.times(2.0D);
/* 2371 */       this.transmitCoeffTE[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/*      */     }
/*      */     else
/*      */     {
/* 2375 */       ComplexMatrix mati = new ComplexMatrix(2, 2);
/*      */       
/*      */ 
/* 2378 */       Complex[][] matic = Complex.twoDarray(2, 2);
/*      */       
/*      */ 
/* 2381 */       Complex costheta = this.kxVector[wavelengthIndex][angleIndex][1].over(this.kVector[wavelengthIndex][angleIndex][1]);
/* 2382 */       Complex pTerm = this.refractiveIndices[wavelengthIndex][1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][1]));
/* 2383 */       pTerm = pTerm.times(costheta);
/* 2384 */       Complex beta = this.kxVector[wavelengthIndex][angleIndex][1].times(this.thicknesses[1]);
/* 2385 */       matic[0][0] = Complex.cos(beta);
/* 2386 */       matic[1][1] = matic[0][0];
/* 2387 */       tempc1 = Complex.sin(beta);
/* 2388 */       tempc1 = tempc1.times(Complex.minusJay());
/* 2389 */       matic[0][1] = tempc1.over(pTerm);
/* 2390 */       matic[1][0] = tempc1.times(pTerm);
/*      */       
/* 2392 */       if (this.numberOfLayers > 3)
/*      */       {
/*      */ 
/* 2395 */         ComplexMatrix mat = new ComplexMatrix(Complex.copy(matic));
/*      */         
/* 2397 */         for (int i = 2; i < this.numberOfLayers - 1; i++) {
/* 2398 */           costheta = this.kxVector[wavelengthIndex][angleIndex][i].over(this.kVector[wavelengthIndex][angleIndex][i]);
/* 2399 */           pTerm = this.refractiveIndices[wavelengthIndex][i].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][i]));
/* 2400 */           pTerm = pTerm.times(costheta);
/* 2401 */           beta = this.kxVector[wavelengthIndex][angleIndex][i].times(this.thicknesses[i]);
/* 2402 */           matic[0][0] = Complex.cos(beta);
/* 2403 */           matic[1][1] = matic[0][0];
/* 2404 */           tempc1 = Complex.sin(beta);
/* 2405 */           tempc1 = tempc1.times(Complex.minusJay());
/* 2406 */           matic[0][1] = tempc1.over(pTerm);
/* 2407 */           matic[1][0] = tempc1.times(pTerm);
/* 2408 */           mati.setTwoDarray(Complex.copy(matic));
/* 2409 */           mat = mat.times(mati);
/* 2410 */           matic = mat.getArrayCopy();
/*      */         }
/*      */       }
/*      */       
/* 2414 */       costheta = this.kxVector[wavelengthIndex][angleIndex][0].over(this.kVector[wavelengthIndex][angleIndex][0]);
/* 2415 */       Complex pTerm0 = this.refractiveIndices[wavelengthIndex][0].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][0]));
/* 2416 */       pTerm0 = pTerm0.times(costheta);
/*      */       
/* 2418 */       costheta = this.kxVector[wavelengthIndex][angleIndex][(this.numberOfLayers - 1)].over(this.kVector[wavelengthIndex][angleIndex][(this.numberOfLayers - 1)]);
/* 2419 */       Complex pTermN = this.refractiveIndices[wavelengthIndex][(this.numberOfLayers - 1)].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][(this.numberOfLayers - 1)]));
/* 2420 */       pTermN = pTermN.times(costheta);
/*      */       
/* 2422 */       tempc1 = matic[0][0].plus(matic[0][1].times(pTermN));
/* 2423 */       tempc1 = tempc1.times(pTerm0);
/* 2424 */       tempc2 = matic[1][0].plus(matic[1][1].times(pTermN));
/* 2425 */       tempc3 = tempc1.minus(tempc2);
/* 2426 */       tempc4 = tempc1.plus(tempc2);
/* 2427 */       this.reflectCoeffTE[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/* 2428 */       this.reflectPhaseShiftRadTE[wavelengthIndex][angleIndex] = this.reflectCoeffTE[wavelengthIndex][angleIndex].arg();
/* 2429 */       this.reflectPhaseShiftDegTE[wavelengthIndex][angleIndex] = Math.toDegrees(this.reflectPhaseShiftRadTE[wavelengthIndex][angleIndex]);
/*      */       
/* 2431 */       tempc3 = pTerm0.times(2.0D);
/* 2432 */       this.transmitCoeffTE[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/* 2433 */       this.transmitPhaseShiftRadTE[wavelengthIndex][angleIndex] = this.transmitCoeffTE[wavelengthIndex][angleIndex].arg();
/* 2434 */       this.transmitPhaseShiftDegTE[wavelengthIndex][angleIndex] = Math.toDegrees(this.transmitPhaseShiftRadTE[wavelengthIndex][angleIndex]);
/*      */     }
/*      */     
/*      */ 
/* 2438 */     double reflectivity = Fmath.square(this.reflectCoeffTE[wavelengthIndex][angleIndex].getReal()) + Fmath.square(this.reflectCoeffTE[wavelengthIndex][angleIndex].getImag());
/*      */     
/* 2440 */     int nkouter = this.numberOfLayers - 1;
/* 2441 */     double tempd1 = Fmath.square(this.transmitCoeffTE[wavelengthIndex][angleIndex].getReal()) + Fmath.square(this.transmitCoeffTE[wavelengthIndex][angleIndex].getImag());
/* 2442 */     tempc2 = this.relativeMagneticPermeabilities[wavelengthIndex][0].over(this.relativeMagneticPermeabilities[wavelengthIndex][nkouter]).times(tempd1);
/* 2443 */     tempc3 = this.kxVector[wavelengthIndex][angleIndex][nkouter].conjugate().over(this.kxVector[wavelengthIndex][angleIndex][0]);
/* 2444 */     Complex complexTransmissivity = tempc2.times(tempc3);
/*      */     
/* 2446 */     double transmissivity = 0.0D;
/* 2447 */     double reflectedAngleRad = 1.5707963267948966D;
/* 2448 */     double integratedEvanescentField = 0.0D;
/* 2449 */     if (this.kxVector[wavelengthIndex][angleIndex][nkouter].getReal() == 0.0D) {
/* 2450 */       penetrationDepth = 1.0D / this.kxVector[wavelengthIndex][angleIndex][nkouter].getImag();
/* 2451 */       integratedEvanescentField = Fmath.square(this.transmitCoeffTE[wavelengthIndex][angleIndex].abs()) * (1.0D - Math.exp(-2.0D * this.fieldDistance / penetrationDepth)) * penetrationDepth / 2.0D;
/* 2452 */       if (this.fieldDistance != Double.POSITIVE_INFINITY) this.fieldIntensityCalc = true;
/*      */     }
/*      */     else {
/* 2455 */       transmissivity = complexTransmissivity.getReal();
/* 2456 */       reflectedAngleRad = Math.atan2(this.kzVector[wavelengthIndex][angleIndex][nkouter].getReal(), this.kxVector[wavelengthIndex][angleIndex][nkouter].getReal());
/*      */     }
/*      */     
/* 2459 */     double powerLoss = 10.0D * Fmath.log10((1.0D - transmissivity) * 0.001D);
/*      */     
/* 2461 */     double[] ret = new double[6];
/* 2462 */     ret[0] = reflectivity;
/* 2463 */     ret[1] = transmissivity;
/* 2464 */     ret[2] = reflectedAngleRad;
/* 2465 */     ret[3] = integratedEvanescentField;
/* 2466 */     ret[4] = penetrationDepth;
/* 2467 */     ret[5] = powerLoss;
/* 2468 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] calcTMreflectivity(int wavelengthIndex, int angleIndex)
/*      */   {
/* 2474 */     Complex tempc1 = Complex.zero();
/* 2475 */     Complex tempc2 = Complex.zero();
/* 2476 */     Complex tempc3 = Complex.zero();
/* 2477 */     Complex tempc4 = Complex.zero();
/*      */     
/* 2479 */     double penetrationDepth = 0.0D;
/*      */     
/* 2481 */     if (this.numberOfLayers == 2) {
/* 2482 */       tempc1 = Complex.square(this.refractiveIndices[wavelengthIndex][1]).times(this.kxVector[wavelengthIndex][angleIndex][0]);
/* 2483 */       tempc2 = Complex.square(this.refractiveIndices[wavelengthIndex][0]).times(this.kxVector[wavelengthIndex][angleIndex][1]);
/* 2484 */       tempc3 = tempc1.minus(tempc2);
/* 2485 */       tempc4 = tempc1.plus(tempc2);
/* 2486 */       this.reflectCoeffTM[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/*      */       
/* 2488 */       tempc3 = tempc1.times(2.0D);
/* 2489 */       this.transmitCoeffTM[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/*      */     }
/*      */     else
/*      */     {
/* 2493 */       ComplexMatrix mati = new ComplexMatrix(2, 2);
/*      */       
/*      */ 
/* 2496 */       Complex[][] matic = Complex.twoDarray(2, 2);
/*      */       
/*      */ 
/* 2499 */       Complex costheta = this.kxVector[wavelengthIndex][angleIndex][1].over(this.kVector[wavelengthIndex][angleIndex][1]);
/* 2500 */       Complex pTerm = this.refractiveIndices[wavelengthIndex][1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][1]));
/* 2501 */       pTerm = pTerm.over(costheta);
/* 2502 */       Complex beta = this.kxVector[wavelengthIndex][angleIndex][1].times(this.thicknesses[1]);
/* 2503 */       matic[0][0] = Complex.cos(beta);
/* 2504 */       matic[1][1] = matic[0][0];
/* 2505 */       tempc1 = Complex.sin(beta);
/* 2506 */       tempc1 = tempc1.times(Complex.minusJay());
/* 2507 */       matic[0][1] = tempc1.over(pTerm);
/* 2508 */       matic[1][0] = tempc1.times(pTerm);
/*      */       
/* 2510 */       if (this.numberOfLayers > 3)
/*      */       {
/* 2512 */         ComplexMatrix mat = new ComplexMatrix(Complex.copy(matic));
/*      */         
/* 2514 */         for (int i = 2; i < this.numberOfLayers - 1; i++) {
/* 2515 */           costheta = this.kxVector[wavelengthIndex][angleIndex][i].over(this.kVector[wavelengthIndex][angleIndex][i]);
/* 2516 */           pTerm = this.refractiveIndices[wavelengthIndex][i].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][i]));
/* 2517 */           pTerm = pTerm.over(costheta);
/* 2518 */           beta = this.kxVector[wavelengthIndex][angleIndex][i].times(this.thicknesses[i]);
/* 2519 */           matic[0][0] = Complex.cos(beta);
/* 2520 */           matic[1][1] = matic[0][0];
/* 2521 */           tempc1 = Complex.sin(beta);
/* 2522 */           tempc1 = tempc1.times(Complex.minusJay());
/* 2523 */           matic[0][1] = tempc1.over(pTerm);
/* 2524 */           matic[1][0] = tempc1.times(pTerm);
/* 2525 */           mati.setTwoDarray(Complex.copy(matic));
/* 2526 */           mat = mat.times(mati);
/* 2527 */           matic = mat.getArrayReference();
/*      */         }
/*      */       }
/* 2530 */       costheta = this.kxVector[wavelengthIndex][angleIndex][0].over(this.kVector[wavelengthIndex][angleIndex][0]);
/* 2531 */       Complex pTerm0 = this.refractiveIndices[wavelengthIndex][0].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][0]));
/* 2532 */       pTerm0 = pTerm0.over(costheta);
/*      */       
/* 2534 */       costheta = this.kxVector[wavelengthIndex][angleIndex][(this.numberOfLayers - 1)].over(this.kVector[wavelengthIndex][angleIndex][(this.numberOfLayers - 1)]);
/* 2535 */       Complex pTermN = this.refractiveIndices[wavelengthIndex][(this.numberOfLayers - 1)].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][(this.numberOfLayers - 1)]));
/* 2536 */       pTermN = pTermN.over(costheta);
/*      */       
/* 2538 */       tempc1 = matic[0][0].plus(matic[0][1].times(pTermN));
/* 2539 */       tempc1 = tempc1.times(pTerm0);
/* 2540 */       tempc2 = matic[1][0].plus(matic[1][1].times(pTermN));
/* 2541 */       tempc3 = tempc1.minus(tempc2);
/* 2542 */       tempc4 = tempc1.plus(tempc2);
/* 2543 */       this.reflectCoeffTM[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/* 2544 */       this.reflectPhaseShiftRadTM[wavelengthIndex][angleIndex] = this.reflectCoeffTM[wavelengthIndex][angleIndex].arg();
/* 2545 */       this.reflectPhaseShiftDegTM[wavelengthIndex][angleIndex] = Math.toDegrees(this.reflectPhaseShiftRadTM[wavelengthIndex][angleIndex]);
/*      */       
/* 2547 */       tempc3 = pTerm0.times(2.0D);
/* 2548 */       this.transmitCoeffTM[wavelengthIndex][angleIndex] = tempc3.over(tempc4);
/* 2549 */       this.transmitPhaseShiftRadTM[wavelengthIndex][angleIndex] = this.transmitCoeffTM[wavelengthIndex][angleIndex].arg();
/* 2550 */       this.transmitPhaseShiftDegTM[wavelengthIndex][angleIndex] = Math.toDegrees(this.transmitPhaseShiftRadTM[wavelengthIndex][angleIndex]);
/*      */     }
/*      */     
/*      */ 
/* 2554 */     double reflectivity = Fmath.square(this.reflectCoeffTM[wavelengthIndex][angleIndex].getReal()) + Fmath.square(this.reflectCoeffTM[wavelengthIndex][angleIndex].getImag());
/*      */     
/* 2556 */     int nkouter = this.numberOfLayers - 1;
/* 2557 */     double tempd1 = Fmath.square(this.transmitCoeffTM[wavelengthIndex][angleIndex].getReal()) + Fmath.square(this.transmitCoeffTM[wavelengthIndex][angleIndex].getImag());
/* 2558 */     tempc2 = Complex.square(this.refractiveIndices[wavelengthIndex][0].over(this.refractiveIndices[wavelengthIndex][nkouter])).times(tempd1);
/* 2559 */     tempc3 = this.kxVector[wavelengthIndex][angleIndex][nkouter].conjugate().over(this.kxVector[wavelengthIndex][angleIndex][0]);
/* 2560 */     Complex complexTransmissivity = tempc2.times(tempc3);
/*      */     
/* 2562 */     double transmissivity = 0.0D;
/* 2563 */     double reflectedAngleRad = 1.5707963267948966D;
/* 2564 */     double integratedEvanescentField = 0.0D;
/* 2565 */     if (this.kxVector[wavelengthIndex][angleIndex][nkouter].getReal() == 0.0D) {
/* 2566 */       penetrationDepth = 1.0D / this.kxVector[wavelengthIndex][angleIndex][nkouter].getImag();
/* 2567 */       double refrTerm = this.refractiveIndices[wavelengthIndex][0].getReal() / this.refractiveIndices[wavelengthIndex][nkouter].getReal();
/* 2568 */       double magnTerm = Math.sqrt(this.relativeMagneticPermeabilities[wavelengthIndex][nkouter].getReal() / this.relativeMagneticPermeabilities[wavelengthIndex][0].getReal());
/* 2569 */       integratedEvanescentField = Fmath.square(this.transmitCoeffTM[wavelengthIndex][angleIndex].abs()) * magnTerm * refrTerm * (1.0D - Math.exp(-2.0D * this.fieldDistance / penetrationDepth)) * penetrationDepth / 2.0D;
/* 2570 */       if (this.fieldDistance != Double.POSITIVE_INFINITY) this.fieldIntensityCalc = true;
/*      */     }
/*      */     else {
/* 2573 */       transmissivity = complexTransmissivity.getReal();
/* 2574 */       reflectedAngleRad = Math.atan2(this.kzVector[wavelengthIndex][angleIndex][nkouter].getReal(), this.kxVector[wavelengthIndex][angleIndex][nkouter].getReal());
/*      */     }
/*      */     
/* 2577 */     double powerLoss = 10.0D * Fmath.log10((1.0D - transmissivity) * 0.001D);
/*      */     
/* 2579 */     double[] ret = new double[6];
/* 2580 */     ret[0] = reflectivity;
/* 2581 */     ret[1] = transmissivity;
/* 2582 */     ret[2] = reflectedAngleRad;
/* 2583 */     ret[3] = integratedEvanescentField;
/* 2584 */     ret[4] = penetrationDepth;
/* 2585 */     ret[5] = powerLoss;
/* 2586 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setThicknessEstimatesIndices(int[] indices)
/*      */   {
/* 2595 */     this.thicknessEstimateIndices = indices;
/* 2596 */     this.thicknessEstimateNumber = indices.length;
/*      */   }
/*      */   
/*      */   public void setRealRefractIndexEstimateIndices(int[] indices)
/*      */   {
/* 2601 */     this.refractIndexRealEstimateIndices = indices;
/* 2602 */     this.refractIndexRealEstimateNumber = indices.length;
/*      */   }
/*      */   
/*      */   public void setImagRefractIndexEstimateIndices(int[] indices)
/*      */   {
/* 2607 */     this.refractIndexImagEstimateIndices = indices;
/* 2608 */     this.refractIndexImagEstimateNumber = indices.length;
/* 2609 */     this.refractIndexImagEstimateSet = true;
/*      */     
/*      */ 
/* 2612 */     if (this.absorptionCoeffEstimateSet) {
/* 2613 */       int[] temp0 = new int[this.absorptionCoeffEstimateNumber];
/* 2614 */       int newIndex = 0;
/* 2615 */       for (int i = 0; i < this.numberOfLayers; i++) {
/* 2616 */         boolean testR = false;
/* 2617 */         for (int j = 0; j < this.refractIndexImagEstimateNumber; j++) {
/* 2618 */           if (i == this.refractIndexImagEstimateIndices[j]) testR = true;
/*      */         }
/* 2620 */         boolean testA = false;
/* 2621 */         for (int j = 0; j < this.absorptionCoeffEstimateNumber; j++) {
/* 2622 */           if (i == this.absorptionCoeffEstimateIndices[j]) testA = true;
/*      */         }
/* 2624 */         if ((!testR) && (testA)) {
/* 2625 */           temp0[newIndex] = i;
/* 2626 */           newIndex++;
/*      */         }
/*      */       }
/* 2629 */       int newRefrNumber = this.refractIndexImagEstimateNumber + newIndex;
/* 2630 */       int[] temp1 = new int[newRefrNumber];
/* 2631 */       for (int j = 0; j < this.refractIndexImagEstimateNumber; j++) {
/* 2632 */         temp1[j] = this.refractIndexImagEstimateIndices[j];
/*      */       }
/* 2634 */       for (int j = 0; j < this.absorptionCoeffEstimateNumber; j++) {
/* 2635 */         temp1[(this.refractIndexImagEstimateNumber + j)] = this.absorptionCoeffEstimateIndices[j];
/*      */       }
/* 2637 */       this.refractIndexImagEstimateIndices = Fmath.selectionSort(temp1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAbsorptionCoefficientEstimateIndices(int[] indices)
/*      */   {
/* 2644 */     this.absorptionCoeffEstimateIndices = indices;
/* 2645 */     this.absorptionCoeffEstimateNumber = indices.length;
/* 2646 */     this.absorptionCoeffEstimateSet = true;
/*      */     
/*      */ 
/* 2649 */     if (this.refractIndexImagEstimateSet) {
/* 2650 */       int[] temp0 = new int[this.absorptionCoeffEstimateNumber];
/* 2651 */       int newIndex = 0;
/* 2652 */       for (int i = 0; i < this.numberOfLayers; i++) {
/* 2653 */         boolean testR = false;
/* 2654 */         for (int j = 0; j < this.refractIndexImagEstimateNumber; j++) {
/* 2655 */           if (i == this.refractIndexImagEstimateIndices[j]) testR = true;
/*      */         }
/* 2657 */         boolean testA = false;
/* 2658 */         for (int j = 0; j < this.absorptionCoeffEstimateNumber; j++) {
/* 2659 */           if (i == this.absorptionCoeffEstimateIndices[j]) testA = true;
/*      */         }
/* 2661 */         if ((!testR) && (testA)) {
/* 2662 */           temp0[newIndex] = i;
/* 2663 */           newIndex++;
/*      */         }
/*      */       }
/* 2666 */       int newRefrNumber = this.refractIndexImagEstimateNumber + newIndex;
/* 2667 */       int[] temp1 = new int[newRefrNumber];
/* 2668 */       for (int j = 0; j < this.refractIndexImagEstimateNumber; j++) {
/* 2669 */         temp1[j] = this.refractIndexImagEstimateIndices[j];
/*      */       }
/* 2671 */       for (int j = 0; j < this.absorptionCoeffEstimateNumber; j++) {
/* 2672 */         temp1[(this.refractIndexImagEstimateNumber + j)] = this.absorptionCoeffEstimateIndices[j];
/*      */       }
/* 2674 */       this.refractIndexImagEstimateIndices = Fmath.selectionSort(temp1);
/*      */     }
/*      */     else {
/* 2677 */       this.refractIndexImagEstimateIndices = this.absorptionCoeffEstimateIndices;
/* 2678 */       this.refractIndexImagEstimateNumber = this.absorptionCoeffEstimateNumber;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setRealRelativeMagneticPermeabilityEstimateIndices(int[] indices)
/*      */   {
/* 2684 */     this.magneticPermRealEstimateIndices = indices;
/* 2685 */     this.magneticPermRealEstimateNumber = indices.length;
/*      */   }
/*      */   
/*      */   public void setImagRelativeMagneticPermeabilityEstimateIndices(int[] indices)
/*      */   {
/* 2690 */     this.magneticPermImagEstimateIndices = indices;
/* 2691 */     this.magneticPermImagEstimateNumber = indices.length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void fitReflectivities(double[] experimentalReflectivities)
/*      */   {
/* 2699 */     int n = experimentalReflectivities.length;
/* 2700 */     double[] errors = new double[n];
/* 2701 */     for (int i = 0; i < n; i++) errors[i] = 1.0D;
/* 2702 */     fitReflectivities(experimentalReflectivities, errors);
/*      */   }
/*      */   
/*      */ 
/*      */   public void fitReflectivities(double[] experimentalReflectivities, double[] errors)
/*      */   {
/* 2708 */     this.numberOfDataPoints = experimentalReflectivities.length;
/* 2709 */     if (this.numberOfDataPoints != errors.length) throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + errors.length + ".");
/* 2710 */     if (this.incidentAngleSet) {
/* 2711 */       if (this.numberOfDataPoints != this.numberOfIncidentAngles) throw new IllegalArgumentException("Number of experimental reflectivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
/* 2712 */       double[] temp0 = (double[])experimentalReflectivities.clone();
/* 2713 */       double[] temp1 = (double[])errors.clone();
/* 2714 */       for (int i = 0; i < this.numberOfIncidentAngles; i++) {
/* 2715 */         this.experimentalData[i] = temp0[this.incidentAngleIndices[i]];
/* 2716 */         this.experimentalWeights[i] = temp1[this.incidentAngleIndices[i]];
/*      */       }
/*      */     }
/* 2719 */     this.regressionOption = 1;
/* 2720 */     this.experimentalDataSet = true;
/*      */     
/* 2722 */     nonLinearRegression();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotReflectivities(double[] experimentalReflectivities)
/*      */   {
/* 2729 */     fitReflectivities(experimentalReflectivities);
/* 2730 */     String graphTitle = " ";
/* 2731 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotReflectivities(double[] experimentalReflectivities, String graphTitle)
/*      */   {
/* 2738 */     fitReflectivities(experimentalReflectivities);
/* 2739 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotReflectivities(double[] experimentalReflectivities, double[] errors)
/*      */   {
/* 2746 */     fitReflectivities(experimentalReflectivities, errors);
/* 2747 */     String graphTitle = " ";
/* 2748 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotReflectivities(double[] experimentalReflectivities, double[] errors, String graphTitle)
/*      */   {
/* 2755 */     fitReflectivities(experimentalReflectivities, errors);
/* 2756 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void fitTransmissivities(double[] experimentalTransmissivities)
/*      */   {
/* 2764 */     int n = experimentalTransmissivities.length;
/* 2765 */     double[] errors = new double[n];
/* 2766 */     for (int i = 0; i < n; i++) errors[i] = 1.0D;
/* 2767 */     fitTransmissivities(experimentalTransmissivities, errors);
/*      */   }
/*      */   
/*      */ 
/*      */   public void fitTransmissivities(double[] experimentalTransmissivities, double[] errors)
/*      */   {
/* 2773 */     this.numberOfDataPoints = experimentalTransmissivities.length;
/* 2774 */     if (this.numberOfDataPoints != errors.length) throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + errors.length + ".");
/* 2775 */     if (this.incidentAngleSet) {
/* 2776 */       if (this.numberOfDataPoints != this.numberOfIncidentAngles) throw new IllegalArgumentException("Number of experimental transmissivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
/* 2777 */       double[] temp0 = (double[])experimentalTransmissivities.clone();
/* 2778 */       double[] temp1 = (double[])errors.clone();
/* 2779 */       for (int i = 0; i < this.numberOfIncidentAngles; i++) {
/* 2780 */         this.experimentalData[i] = temp0[this.incidentAngleIndices[i]];
/* 2781 */         this.experimentalWeights[i] = temp1[this.incidentAngleIndices[i]];
/*      */       }
/*      */     }
/* 2784 */     this.regressionOption = 1;
/* 2785 */     this.experimentalDataSet = true;
/*      */     
/* 2787 */     nonLinearRegression();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotTransmissivities(double[] experimentalTransmissivities)
/*      */   {
/* 2794 */     fitTransmissivities(experimentalTransmissivities);
/* 2795 */     String graphTitle = " ";
/* 2796 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotTransmissivities(double[] experimentalTransmissivities, String graphTitle)
/*      */   {
/* 2803 */     fitTransmissivities(experimentalTransmissivities);
/* 2804 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotTransmissivities(double[] experimentalTransmissivities, double[] errors)
/*      */   {
/* 2811 */     fitTransmissivities(experimentalTransmissivities, errors);
/* 2812 */     String graphTitle = " ";
/* 2813 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitAndPlotTransmissivities(double[] experimentalTransmissivities, double[] errors, String graphTitle)
/*      */   {
/* 2820 */     fitTransmissivities(experimentalTransmissivities, errors);
/* 2821 */     plotFit(graphTitle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void fitEvanescentField(double[] experimentalEvanescentFieldIntensities)
/*      */   {
/* 2830 */     int n = experimentalEvanescentFieldIntensities.length;
/* 2831 */     double[] errors = new double[n];
/* 2832 */     for (int i = 0; i < n; i++) errors[i] = 1.0D;
/* 2833 */     double fieldDistance = Double.POSITIVE_INFINITY;
/* 2834 */     fitEvanescentField(experimentalEvanescentFieldIntensities, errors, fieldDistance);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitEvanescentField(double[] experimentalEvanescentFieldIntensities, double[] errors)
/*      */   {
/* 2841 */     double fieldDistance = Double.POSITIVE_INFINITY;
/* 2842 */     fitEvanescentField(experimentalEvanescentFieldIntensities, errors, fieldDistance);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitEvanescentField(double[] experimentalEvanescentFieldIntensities, double fieldDistance)
/*      */   {
/* 2849 */     int n = experimentalEvanescentFieldIntensities.length;
/* 2850 */     double[] errors = new double[n];
/* 2851 */     for (int i = 0; i < n; i++) errors[i] = 1.0D;
/* 2852 */     fitEvanescentField(experimentalEvanescentFieldIntensities, errors, fieldDistance);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void fitEvanescentField(double[] experimentalEvanescentFieldIntensities, double[] errors, double fieldDistance)
/*      */   {
/* 2859 */     this.numberOfDataPoints = experimentalEvanescentFieldIntensities.length;
/* 2860 */     if (this.numberOfDataPoints != errors.length) { throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + errors.length + ".");
/*      */     }
/* 2862 */     if (this.incidentAngleSet) {
/* 2863 */       if (this.numberOfDataPoints != this.numberOfIncidentAngles) throw new IllegalArgumentException("Number of experimental transmissivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
/* 2864 */       double[] temp0 = (double[])experimentalEvanescentFieldIntensities.clone();
/* 2865 */       double[] temp1 = (double[])errors.clone();
/* 2866 */       for (int i = 0; i < this.numberOfIncidentAngles; i++) {
/* 2867 */         this.experimentalData[i] = temp0[this.incidentAngleIndices[i]];
/* 2868 */         this.experimentalWeights[i] = temp1[this.incidentAngleIndices[i]];
/*      */       }
/*      */     }
/* 2871 */     this.regressionOption = 3;
/* 2872 */     this.fieldDistance = fieldDistance;
/* 2873 */     this.experimentalDataSet = true;
/*      */     
/* 2875 */     nonLinearRegression();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void nonLinearRegression()
/*      */   {
/* 2884 */     int ii = 0;
/* 2885 */     boolean test = true;
/* 2886 */     while (test) {
/* 2887 */       if (this.experimentalWeights[ii] != 1.0D) {
/* 2888 */         this.weightingOption = true;
/* 2889 */         test = false;
/*      */       }
/*      */       else {
/* 2892 */         ii++;
/* 2893 */         if (ii >= this.numberOfDataPoints) { test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2898 */     Regression regr = null;
/* 2899 */     if (this.weightingOption) {
/* 2900 */       regr = new Regression(this.incidentAngleDeg, this.experimentalData, this.experimentalWeights);
/*      */     }
/*      */     else {
/* 2903 */       regr = new Regression(this.incidentAngleDeg, this.experimentalData);
/*      */     }
/*      */     
/*      */ 
/* 2907 */     RegressFunct funct0 = new RegressFunct();
/*      */     
/*      */ 
/* 2910 */     funct0.numberOfLayers = this.numberOfLayers;
/* 2911 */     funct0.mode = this.mode;
/* 2912 */     funct0.eVectorAngleDeg = this.eVectorAngleDeg;
/* 2913 */     funct0.thicknesses = this.thicknesses;
/* 2914 */     funct0.refractiveIndices = this.refractiveIndices;
/* 2915 */     funct0.relativeMagneticPermeabilities = this.relativeMagneticPermeabilities;
/* 2916 */     funct0.regressionOption = this.regressionOption;
/* 2917 */     funct0.thicknessEstimateIndices = this.thicknessEstimateIndices;
/* 2918 */     funct0.refractIndexRealEstimateIndices = this.refractIndexRealEstimateIndices;
/* 2919 */     funct0.refractIndexImagEstimateIndices = this.refractIndexImagEstimateIndices;
/* 2920 */     funct0.magneticPermRealEstimateIndices = this.magneticPermRealEstimateIndices;
/* 2921 */     funct0.magneticPermImagEstimateIndices = this.magneticPermImagEstimateIndices;
/*      */     
/*      */ 
/* 2924 */     this.numberOfEstimatedParameters = this.thicknessEstimateNumber;
/* 2925 */     this.numberOfEstimatedParameters += this.refractIndexRealEstimateNumber;
/* 2926 */     this.numberOfEstimatedParameters += this.refractIndexImagEstimateNumber;
/* 2927 */     this.numberOfEstimatedParameters += this.magneticPermRealEstimateNumber;
/* 2928 */     this.numberOfEstimatedParameters += this.magneticPermImagEstimateNumber;
/* 2929 */     if (this.regressionOption == 3) { this.numberOfEstimatedParameters += 1;
/*      */     }
/* 2931 */     this.degreesOfFreedom = (this.numberOfDataPoints - this.numberOfEstimatedParameters);
/* 2932 */     if (this.degreesOfFreedom < 1) { throw new IllegalArgumentException("Number of parameters to be estimated, " + this.numberOfEstimatedParameters + ", is greater than or equal to the number of data points, " + this.numberOfDataPoints + ".");
/*      */     }
/*      */     
/* 2935 */     double[] start = new double[this.numberOfEstimatedParameters];
/* 2936 */     double[] init = new double[this.numberOfEstimatedParameters];
/* 2937 */     double[] step = new double[this.numberOfEstimatedParameters];
/*      */     
/* 2939 */     int pIndex = 0;
/* 2940 */     for (int i = 0; i < this.thicknessEstimateNumber; i++) {
/* 2941 */       init[pIndex] = this.thicknesses[this.thicknessEstimateIndices[pIndex]];
/* 2942 */       start[pIndex] = init[pIndex];
/* 2943 */       init[pIndex] *= 0.1D;
/* 2944 */       if (step[pIndex] == 0.0D) step[pIndex] = 1.0E-9D;
/* 2945 */       pIndex++;
/*      */     }
/* 2947 */     for (int i = 0; i < this.refractIndexRealEstimateNumber; i++) {
/* 2948 */       init[pIndex] = this.refractiveIndices[0][this.refractIndexRealEstimateIndices[pIndex]].getReal();
/* 2949 */       start[pIndex] = init[pIndex];
/* 2950 */       init[pIndex] *= 0.1D;
/* 2951 */       if (step[pIndex] == 0.0D) step[pIndex] = 0.1D;
/* 2952 */       pIndex++;
/*      */     }
/* 2954 */     for (int i = 0; i < this.refractIndexImagEstimateNumber; i++) {
/* 2955 */       init[pIndex] = this.refractiveIndices[0][this.refractIndexImagEstimateIndices[pIndex]].getImag();
/* 2956 */       start[pIndex] = init[pIndex];
/* 2957 */       init[pIndex] *= 0.1D;
/* 2958 */       if (step[pIndex] == 0.0D) step[pIndex] = 0.1D;
/* 2959 */       pIndex++;
/*      */     }
/* 2961 */     for (int i = 0; i < this.magneticPermRealEstimateNumber; i++) {
/* 2962 */       init[pIndex] = this.relativeMagneticPermeabilities[0][this.magneticPermRealEstimateIndices[pIndex]].getReal();
/* 2963 */       start[pIndex] = init[pIndex];
/* 2964 */       init[pIndex] *= 0.1D;
/* 2965 */       if (step[pIndex] == 0.0D) step[pIndex] = 0.1D;
/* 2966 */       pIndex++;
/*      */     }
/* 2968 */     for (int i = 0; i < this.magneticPermImagEstimateNumber; i++) {
/* 2969 */       init[pIndex] = this.relativeMagneticPermeabilities[0][this.magneticPermImagEstimateIndices[pIndex]].getImag();
/* 2970 */       start[pIndex] = init[pIndex];
/* 2971 */       init[pIndex] *= 0.1D;
/* 2972 */       if (step[pIndex] == 0.0D) step[pIndex] = 0.1D;
/* 2973 */       pIndex++;
/*      */     }
/*      */     
/*      */ 
/* 2977 */     if (this.regressionOption == 3) {
/* 2978 */       double[] evanFields = (double[])getEvanescentFields(this.fieldDistance);
/* 2979 */       double calcFieldMean = 0.0D;
/* 2980 */       double explFieldMean = 0.0D;
/* 2981 */       for (int i = 0; i < this.numberOfDataPoints; i++) {
/* 2982 */         if (evanFields[i] != 0.0D) {
/* 2983 */           calcFieldMean += evanFields[i];
/* 2984 */           explFieldMean += this.experimentalData[i];
/*      */         }
/*      */       }
/* 2987 */       if (explFieldMean == 0.0D) throw new IllegalArgumentException("All entered field values are zero or sum to zero");
/* 2988 */       if (calcFieldMean == 0.0D) throw new IllegalArgumentException("All calculated field values are zero or sum to zero");
/* 2989 */       init[pIndex] = (explFieldMean / calcFieldMean);
/* 2990 */       start[pIndex] = init[pIndex];
/* 2991 */       init[pIndex] *= 0.1D;
/* 2992 */       if (step[pIndex] == 0.0D) step[pIndex] = 0.1D;
/* 2993 */       pIndex++;
/*      */     }
/*      */     
/*      */ 
/* 2997 */     double ftol = 1.0E-6D;
/*      */     
/*      */ 
/* 3000 */     int nmax = 1000;
/*      */     
/*      */ 
/* 3003 */     regr.simplex(funct0, start, step, ftol, nmax);
/*      */     
/*      */ 
/* 3006 */     double[] bestEstimates = regr.getCoeff();
/*      */     
/*      */ 
/* 3009 */     pIndex = 0;
/* 3010 */     for (int i = 0; i < this.thicknessEstimateNumber; i++) {
/* 3011 */       this.thicknesses[this.thicknessEstimateIndices[pIndex]] = bestEstimates[pIndex];
/* 3012 */       pIndex++;
/*      */     }
/* 3014 */     for (int i = 0; i < this.refractIndexRealEstimateNumber; i++) {
/* 3015 */       this.refractiveIndices[0][this.refractIndexRealEstimateIndices[pIndex]].setReal(bestEstimates[pIndex]);
/* 3016 */       pIndex++;
/*      */     }
/* 3018 */     for (int i = 0; i < this.refractIndexImagEstimateNumber; i++) {
/* 3019 */       this.refractiveIndices[0][this.refractIndexImagEstimateIndices[pIndex]].setImag(bestEstimates[pIndex]);
/* 3020 */       pIndex++;
/*      */     }
/* 3022 */     for (int i = 0; i < this.magneticPermRealEstimateNumber; i++) {
/* 3023 */       this.relativeMagneticPermeabilities[0][this.magneticPermRealEstimateIndices[pIndex]].setReal(bestEstimates[pIndex]);
/* 3024 */       pIndex++;
/*      */     }
/* 3026 */     for (int i = 0; i < this.magneticPermImagEstimateNumber; i++) {
/* 3027 */       this.relativeMagneticPermeabilities[0][this.magneticPermImagEstimateIndices[pIndex]].setImag(bestEstimates[pIndex]);
/* 3028 */       pIndex++;
/*      */     }
/* 3030 */     if (this.regressionOption == 3) { this.fieldScalingFactor = bestEstimates[pIndex];
/*      */     }
/*      */     
/* 3033 */     switch (this.regressionOption) {
/*      */     case 1: 
/* 3035 */       this.calculatedData = ((double[])getReflectivities());
/* 3036 */       break;
/*      */     case 2: 
/* 3038 */       this.calculatedData = ((double[])getTransmissivities());
/* 3039 */       break;
/*      */     case 3: 
/* 3041 */       this.calculatedData = ((double[])getEvanescentFields());
/* 3042 */       for (int i = 0; i < this.numberOfDataPoints; i++) this.calculatedData[i] *= this.fieldScalingFactor;
/* 3043 */       break;
/* 3044 */     default:  throw new IllegalArgumentException("Regresion option " + this.regressionOption + " does not exist");
/*      */     }
/*      */   }
/*      */   
/*      */   public double[] getCalculatedData()
/*      */   {
/* 3050 */     return this.calculatedData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void plotFit(String graphTitle2)
/*      */   {
/* 3059 */     int numberOfCalculatedDataPoints = 200;
/* 3060 */     double[][] data = PlotGraph.data(numberOfCalculatedDataPoints, 2);
/*      */     
/*      */ 
/* 3063 */     for (int i = 0; i < this.numberOfDataPoints; i++) {
/* 3064 */       data[0][i] = this.incidentAngleDeg[i];
/* 3065 */       data[1][i] = this.experimentalData[i];
/*      */     }
/*      */     
/*      */ 
/* 3069 */     double angleIncrement = (this.incidentAngleDeg[(this.numberOfIncidentAngles - 1)] - this.incidentAngleDeg[0]) / (numberOfCalculatedDataPoints - 1);
/* 3070 */     data[2][0] = this.incidentAngleDeg[0];
/* 3071 */     for (int i = 1; i < numberOfCalculatedDataPoints - 1; i++) data[2][i] = (data[2][(i - 1)] + angleIncrement);
/* 3072 */     data[2][(numberOfCalculatedDataPoints - 1)] = this.incidentAngleDeg[(this.numberOfIncidentAngles - 1)];
/*      */     
/*      */ 
/* 3075 */     Reflectivity refl2 = new Reflectivity(this.numberOfLayers);
/*      */     
/*      */ 
/* 3078 */     if (this.mode.equals("mixed")) {
/* 3079 */       refl2.setMode(this.eVectorAngleDeg);
/*      */     }
/*      */     else {
/* 3082 */       refl2.setMode(this.mode);
/*      */     }
/*      */     
/* 3085 */     refl2.setThicknesses(this.thicknesses);
/*      */     
/* 3087 */     refl2.setRefractiveIndices(this.refractiveIndices);
/*      */     
/* 3089 */     refl2.setRelativeMagneticPermeabilities(this.relativeMagneticPermeabilities);
/*      */     
/* 3091 */     refl2.setIncidentAngle(data[2]);
/*      */     
/*      */ 
/* 3094 */     String titleEnd = null;
/* 3095 */     String yAxis = null;
/* 3096 */     switch (this.regressionOption) {
/*      */     case 1: 
/* 3098 */       data[3] = ((double[])(double[])refl2.getReflectivities());
/* 3099 */       titleEnd = "Plot of reflectivities versus incident angle";
/* 3100 */       yAxis = "Reflectivity";
/* 3101 */       break;
/*      */     case 2: 
/* 3103 */       data[3] = ((double[])(double[])refl2.getTransmissivities());
/* 3104 */       titleEnd = "Plot of transmissivities versus incident angle";
/* 3105 */       yAxis = "Transmissivity";
/* 3106 */       break;
/*      */     case 3: 
/* 3108 */       data[3] = ((double[])(double[])refl2.getEvanescentFields());
/* 3109 */       for (int i = 0; i < numberOfCalculatedDataPoints; i++) data[3][i] *= this.fieldScalingFactor;
/* 3110 */       titleEnd = "Plot of evanescent fields versus incident angle";
/* 3111 */       yAxis = "Evanescent Field";
/* 3112 */       break;
/* 3113 */     default:  throw new IllegalArgumentException("Regresion option " + this.regressionOption + " does not exist");
/*      */     }
/*      */     
/*      */     
/* 3117 */     PlotGraph pg = new PlotGraph(data);
/*      */     
/* 3119 */     pg.setGraphTitle("Reflectivity class: " + titleEnd);
/* 3120 */     pg.setGraphTitle2(graphTitle2);
/* 3121 */     pg.setXaxisLegend("Incident angle");
/* 3122 */     pg.setXaxisUnitsName("degrees");
/* 3123 */     pg.setYaxisLegend(yAxis);
/*      */     
/* 3125 */     int[] pointsOptions = { 1, 0 };
/* 3126 */     pg.setPoint(pointsOptions);
/*      */     
/* 3128 */     int[] lineOptions = { 0, 3 };
/* 3129 */     pg.setLine(lineOptions);
/*      */     
/* 3131 */     pg.plot();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/optics/Reflectivity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */