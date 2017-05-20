/*      */ package flanagan.optics;
/*      */ 
/*      */ import flanagan.analysis.Stat;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import flanagan.roots.RealRoot;
/*      */ import java.io.PrintStream;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PlanarWaveguide
/*      */ {
/*   55 */   protected double[][] measurementsTE = (double[][])null;
/*      */   
/*   57 */   protected int numberOfTEmeasurements = 0;
/*   58 */   protected double[] thicknessesUsedTE = null;
/*   59 */   protected double[] calcEffectRefrIndicesTE = null;
/*   60 */   protected boolean setMeasurementsTE = false;
/*   61 */   protected boolean setErrorsTE = false;
/*   62 */   protected double maximumTEmodeEffectiveRefractiveIndex = 0.0D;
/*   63 */   protected double minimumTEmodeEffectiveRefractiveIndex = 0.0D;
/*   64 */   protected double[][] measurementsTM = (double[][])null;
/*      */   
/*   66 */   protected int numberOfTMmeasurements = 0;
/*   67 */   protected double[] thicknessesUsedTM = null;
/*   68 */   protected double[] calcEffectRefrIndicesTM = null;
/*   69 */   protected boolean setMeasurementsTM = false;
/*   70 */   protected boolean setErrorsTM = false;
/*   71 */   protected double maximumTMmodeEffectiveRefractiveIndex = 0.0D;
/*   72 */   protected double minimumTMmodeEffectiveRefractiveIndex = 0.0D;
/*   73 */   protected double maximumEffectiveRefractiveIndex = 0.0D;
/*   74 */   protected double minimumEffectiveRefractiveIndex = 0.0D;
/*   75 */   protected int numberOfMeasurements = 0;
/*   76 */   protected boolean setMeasurements = false;
/*   77 */   protected boolean setWeights = false;
/*   78 */   protected boolean[] eliminatedTE = null;
/*   79 */   protected boolean[] eliminatedTM = null;
/*   80 */   protected double wavelength = 0.0D;
/*   81 */   protected boolean setWavelength = false;
/*   82 */   protected double ko = 0.0D;
/*      */   
/*   84 */   protected double superstrateRefractiveIndex = 0.0D;
/*      */   
/*   86 */   protected double superstrateRefractiveIndex2 = 0.0D;
/*   87 */   protected double[] calcSuperstrateTEmodeRI = null;
/*   88 */   protected double[] calcSuperstrateTMmodeRI = null;
/*   89 */   protected double meanTEmodeSuperstrateRefractiveIndex = NaN.0D;
/*   90 */   protected double meanTMmodeSuperstrateRefractiveIndex = NaN.0D;
/*      */   
/*   92 */   protected double sdTEmodeSuperstrateRefractiveIndex = NaN.0D;
/*   93 */   protected double sdTMmodeSuperstrateRefractiveIndex = NaN.0D;
/*   94 */   protected double sdSuperstrateRefractiveIndex = NaN.0D;
/*   95 */   protected boolean setSuperstrate = false;
/*   96 */   protected boolean superCalculationDone = false;
/*      */   
/*   98 */   protected double substrateRefractiveIndex = 0.0D;
/*   99 */   protected double substrateRefractiveIndex2 = 0.0D;
/*  100 */   protected boolean setSubstrate = false;
/*  101 */   protected double coreFilmRefractiveIndex = 0.0D;
/*  102 */   protected double coreFilmRefractiveIndex2 = 0.0D;
/*  103 */   protected boolean setCore = false;
/*  104 */   protected double[] coreFilmTEmodeRefractiveIndices = null;
/*  105 */   protected double[] coreFilmTMmodeRefractiveIndices = null;
/*  106 */   protected double meanTEmodeCoreFilmRefractiveIndex = NaN.0D;
/*  107 */   protected double meanTMmodeCoreFilmRefractiveIndex = NaN.0D;
/*  108 */   protected double meanCoreFilmRefractiveIndex = NaN.0D;
/*  109 */   protected double meanCoreFilmRefractiveIndex2 = NaN.0D;
/*  110 */   protected double sdTEmodeCoreFilmRefractiveIndex = NaN.0D;
/*  111 */   protected double sdTMmodeCoreFilmRefractiveIndex = NaN.0D;
/*  112 */   protected double sdCoreFilmRefractiveIndex = NaN.0D;
/*  113 */   protected double lowerBound = 0.0D;
/*  114 */   protected double upperBound = 0.0D;
/*  115 */   protected double tolerance = 1.0E-9D;
/*  116 */   protected boolean calculationDone = false;
/*      */   
/*  118 */   protected double prismToWaveguideGap = Double.POSITIVE_INFINITY;
/*  119 */   protected boolean setPrismToWaveguideGap = false;
/*  120 */   protected boolean fixedPrismToWaveguideGap = true;
/*      */   
/*  122 */   protected double prismRefractiveIndex = 0.0D;
/*  123 */   protected double prismRefractiveIndex2 = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterTEmodeData(double thickness, double effectiveRI, double modeNumber)
/*      */   {
/*  133 */     if (this.setMeasurementsTE) {
/*  134 */       if (this.setErrorsTE) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  135 */       int nNew = this.numberOfTEmeasurements + 1;
/*  136 */       double[][] hold = new double[nNew][4];
/*  137 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  138 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTE[i][j];
/*      */       }
/*  140 */       hold[this.numberOfTEmeasurements][0] = thickness;
/*  141 */       hold[this.numberOfTEmeasurements][1] = effectiveRI;
/*  142 */       hold[this.numberOfTEmeasurements][2] = 1.0D;
/*  143 */       hold[this.numberOfTEmeasurements][3] = modeNumber;
/*  144 */       this.measurementsTE = hold;
/*  145 */       this.numberOfTEmeasurements = nNew;
/*      */     }
/*      */     else {
/*  148 */       this.measurementsTE = new double[1][4];
/*  149 */       this.measurementsTE[0][0] = thickness;
/*  150 */       this.measurementsTE[0][1] = effectiveRI;
/*  151 */       this.measurementsTE[0][2] = 1.0D;
/*  152 */       this.measurementsTE[0][3] = modeNumber;
/*  153 */       this.numberOfTEmeasurements = 1;
/*      */     }
/*  155 */     this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*  156 */     this.setMeasurementsTE = true;
/*  157 */     this.setMeasurements = true;
/*      */   }
/*      */   
/*      */   public void enterTEmodeData(double thickness, double effectiveRI, double weight, double modeNumber)
/*      */   {
/*  162 */     if (this.setMeasurementsTE) {
/*  163 */       if (!this.setErrorsTE) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  164 */       int nNew = this.numberOfTEmeasurements + 1;
/*  165 */       double[][] hold = new double[nNew][4];
/*  166 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  167 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTE[i][j];
/*      */       }
/*  169 */       hold[this.numberOfTEmeasurements][0] = thickness;
/*  170 */       hold[this.numberOfTEmeasurements][1] = effectiveRI;
/*  171 */       hold[this.numberOfTEmeasurements][2] = weight;
/*  172 */       hold[this.numberOfTEmeasurements][3] = modeNumber;
/*  173 */       this.measurementsTE = hold;
/*  174 */       this.numberOfTEmeasurements = nNew;
/*      */     }
/*      */     else {
/*  177 */       this.measurementsTE = new double[1][4];
/*  178 */       this.measurementsTE[0][0] = thickness;
/*  179 */       this.measurementsTE[0][1] = effectiveRI;
/*  180 */       this.measurementsTE[0][2] = weight;
/*  181 */       this.measurementsTE[0][3] = modeNumber;
/*  182 */       this.numberOfTEmeasurements = 1;
/*      */     }
/*  184 */     this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*  185 */     this.setMeasurementsTE = true;
/*  186 */     this.setMeasurements = true;
/*  187 */     this.setErrorsTE = true;
/*      */   }
/*      */   
/*      */   public void enterTEmodeData(double[] thicknesses, double[] effectiveRIs, double[] modeNumbers)
/*      */   {
/*  192 */     int o = thicknesses.length;
/*  193 */     int n = effectiveRIs.length;
/*  194 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of effective refractive indices, " + n);
/*  195 */     int m = modeNumbers.length;
/*  196 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*      */     }
/*  198 */     if (this.setMeasurementsTE) {
/*  199 */       if (this.setErrorsTE) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  200 */       int nNew = this.numberOfTEmeasurements + o;
/*  201 */       double[][] hold = new double[nNew][4];
/*  202 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  203 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTE[i][j];
/*      */       }
/*  205 */       for (int i = 0; i < o; i++) {
/*  206 */         hold[(this.numberOfTEmeasurements + i)][0] = thicknesses[i];
/*  207 */         hold[(this.numberOfTEmeasurements + i)][1] = effectiveRIs[i];
/*  208 */         hold[(this.numberOfTEmeasurements + i)][2] = 1.0D;
/*  209 */         hold[(this.numberOfTEmeasurements + i)][3] = modeNumbers[i];
/*      */       }
/*  211 */       this.measurementsTE = hold;
/*  212 */       this.numberOfTEmeasurements = nNew;
/*      */     }
/*      */     else {
/*  215 */       this.numberOfTEmeasurements = o;
/*  216 */       this.measurementsTE = new double[this.numberOfTEmeasurements][4];
/*  217 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  218 */         this.measurementsTE[i][0] = thicknesses[i];
/*  219 */         this.measurementsTE[i][1] = effectiveRIs[i];
/*  220 */         this.measurementsTE[i][2] = 1.0D;
/*  221 */         this.measurementsTE[i][3] = modeNumbers[i];
/*      */       }
/*      */     }
/*      */     
/*  225 */     this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*  226 */     this.setMeasurementsTE = true;
/*  227 */     this.setMeasurements = true;
/*      */   }
/*      */   
/*      */   public void enterTEmodeData(double[] thicknesses, double[] effectiveRIs, double[] weights, double[] modeNumbers)
/*      */   {
/*  232 */     int o = thicknesses.length;
/*  233 */     int n = effectiveRIs.length;
/*  234 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of effective refractive indices, " + n);
/*  235 */     int m = modeNumbers.length;
/*  236 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*      */     }
/*  238 */     if (this.setMeasurementsTE) {
/*  239 */       if (!this.setErrorsTE) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  240 */       int nNew = this.numberOfTEmeasurements + o;
/*  241 */       double[][] hold = new double[nNew][4];
/*  242 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  243 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTE[i][j];
/*      */       }
/*  245 */       for (int i = 0; i < o; i++) {
/*  246 */         hold[(this.numberOfTEmeasurements + i)][0] = thicknesses[i];
/*  247 */         hold[(this.numberOfTEmeasurements + i)][1] = effectiveRIs[i];
/*  248 */         hold[(this.numberOfTEmeasurements + i)][2] = weights[i];
/*  249 */         hold[(this.numberOfTEmeasurements + i)][3] = modeNumbers[i];
/*      */       }
/*  251 */       this.measurementsTE = hold;
/*  252 */       this.numberOfTEmeasurements = nNew;
/*      */     }
/*      */     else {
/*  255 */       this.numberOfTEmeasurements = o;
/*  256 */       this.measurementsTE = new double[this.numberOfTEmeasurements][4];
/*  257 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  258 */         this.measurementsTE[i][0] = thicknesses[i];
/*  259 */         this.measurementsTE[i][1] = effectiveRIs[i];
/*  260 */         this.measurementsTE[i][2] = weights[i];
/*  261 */         this.measurementsTE[i][3] = modeNumbers[i];
/*      */       }
/*      */     }
/*  264 */     this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*  265 */     this.setMeasurementsTE = true;
/*  266 */     this.setMeasurements = true;
/*  267 */     this.setErrorsTE = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterTMmodeData(double thickness, double effectiveRI, double modeNumber)
/*      */   {
/*  274 */     if (this.setMeasurementsTM) {
/*  275 */       if (this.setErrorsTM) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  276 */       int nNew = this.numberOfTMmeasurements + 1;
/*  277 */       double[][] hold = new double[nNew][4];
/*  278 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  279 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTM[i][j];
/*      */       }
/*  281 */       hold[this.numberOfTMmeasurements][0] = thickness;
/*  282 */       hold[this.numberOfTMmeasurements][1] = effectiveRI;
/*  283 */       hold[this.numberOfTMmeasurements][2] = 1.0D;
/*  284 */       hold[this.numberOfTMmeasurements][3] = modeNumber;
/*  285 */       this.measurementsTM = hold;
/*  286 */       this.numberOfTMmeasurements = nNew;
/*      */     }
/*      */     else {
/*  289 */       this.measurementsTM = new double[1][4];
/*  290 */       this.measurementsTM[0][0] = thickness;
/*  291 */       this.measurementsTM[0][1] = effectiveRI;
/*  292 */       this.measurementsTM[0][2] = 1.0D;
/*  293 */       this.measurementsTM[0][3] = modeNumber;
/*  294 */       this.numberOfTMmeasurements = 1;
/*      */     }
/*  296 */     this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*  297 */     this.setMeasurementsTM = true;
/*  298 */     this.setMeasurements = true;
/*      */   }
/*      */   
/*      */   public void enterTMmodeData(double thickness, double effectiveRI, double weight, double modeNumber)
/*      */   {
/*  303 */     if (this.setMeasurementsTM) {
/*  304 */       if (!this.setErrorsTM) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  305 */       int nNew = this.numberOfTMmeasurements + 1;
/*  306 */       double[][] hold = new double[nNew][4];
/*  307 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  308 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTM[i][j];
/*      */       }
/*  310 */       hold[this.numberOfTMmeasurements][0] = thickness;
/*  311 */       hold[this.numberOfTMmeasurements][1] = effectiveRI;
/*  312 */       hold[this.numberOfTMmeasurements][2] = weight;
/*  313 */       hold[this.numberOfTMmeasurements][3] = modeNumber;
/*  314 */       this.measurementsTM = hold;
/*  315 */       this.numberOfTMmeasurements = nNew;
/*      */     }
/*      */     else {
/*  318 */       this.measurementsTM = new double[1][4];
/*  319 */       this.measurementsTM[0][0] = thickness;
/*  320 */       this.measurementsTM[0][1] = effectiveRI;
/*  321 */       this.measurementsTM[0][2] = weight;
/*  322 */       this.measurementsTM[0][3] = modeNumber;
/*  323 */       this.numberOfTMmeasurements = 1;
/*      */     }
/*  325 */     this.numberOfMeasurements = (this.numberOfTMmeasurements + this.numberOfTMmeasurements);
/*  326 */     this.setMeasurementsTM = true;
/*  327 */     this.setMeasurements = true;
/*  328 */     this.setErrorsTM = true;
/*      */   }
/*      */   
/*      */   public void enterTMmodeData(double[] thicknesses, double[] effectiveRIs, double[] modeNumbers)
/*      */   {
/*  333 */     int o = thicknesses.length;
/*  334 */     int n = effectiveRIs.length;
/*  335 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of effective refractive indices, " + n);
/*  336 */     int m = modeNumbers.length;
/*  337 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*      */     }
/*  339 */     if (this.setMeasurementsTM) {
/*  340 */       if (this.setErrorsTM) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  341 */       int nNew = this.numberOfTMmeasurements + o;
/*  342 */       double[][] hold = new double[nNew][4];
/*  343 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  344 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTM[i][j];
/*      */       }
/*  346 */       for (int i = 0; i < o; i++) {
/*  347 */         hold[(this.numberOfTMmeasurements + i)][0] = thicknesses[i];
/*  348 */         hold[(this.numberOfTMmeasurements + i)][1] = effectiveRIs[i];
/*  349 */         hold[(this.numberOfTMmeasurements + i)][2] = 1.0D;
/*  350 */         hold[(this.numberOfTMmeasurements + i)][3] = modeNumbers[i];
/*      */       }
/*  352 */       this.measurementsTM = hold;
/*  353 */       this.numberOfTMmeasurements = nNew;
/*      */     }
/*      */     else {
/*  356 */       this.numberOfTMmeasurements = o;
/*  357 */       this.measurementsTM = new double[this.numberOfTMmeasurements][4];
/*  358 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  359 */         this.measurementsTM[i][0] = thicknesses[i];
/*  360 */         this.measurementsTM[i][1] = effectiveRIs[i];
/*  361 */         this.measurementsTM[i][2] = 1.0D;
/*  362 */         this.measurementsTM[i][3] = modeNumbers[i];
/*      */       }
/*      */     }
/*  365 */     this.numberOfMeasurements = (this.numberOfTMmeasurements + this.numberOfTMmeasurements);
/*  366 */     this.setMeasurementsTM = true;
/*  367 */     this.setMeasurements = true;
/*      */   }
/*      */   
/*      */   public void enterTMmodeData(double[] thicknesses, double[] effectiveRIs, double[] weights, double[] modeNumbers)
/*      */   {
/*  372 */     int o = thicknesses.length;
/*  373 */     int n = effectiveRIs.length;
/*  374 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of effective refractive indices, " + n);
/*  375 */     int m = modeNumbers.length;
/*  376 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*      */     }
/*  378 */     if (this.setMeasurementsTM) {
/*  379 */       if (!this.setErrorsTM) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/*  380 */       int nNew = this.numberOfTMmeasurements + o;
/*  381 */       double[][] hold = new double[nNew][4];
/*  382 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  383 */         for (int j = 0; j < 4; j++) hold[i][j] = this.measurementsTM[i][j];
/*      */       }
/*  385 */       for (int i = 0; i < o; i++) {
/*  386 */         hold[(this.numberOfTMmeasurements + i)][0] = thicknesses[i];
/*  387 */         hold[(this.numberOfTMmeasurements + i)][1] = effectiveRIs[i];
/*  388 */         hold[(this.numberOfTMmeasurements + i)][2] = weights[i];
/*  389 */         hold[(this.numberOfTMmeasurements + i)][3] = modeNumbers[i];
/*      */       }
/*  391 */       this.measurementsTM = hold;
/*  392 */       this.numberOfTMmeasurements = nNew;
/*      */     }
/*      */     else {
/*  395 */       this.numberOfTMmeasurements = o;
/*  396 */       this.measurementsTM = new double[this.numberOfTMmeasurements][4];
/*  397 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  398 */         this.measurementsTM[i][0] = thicknesses[i];
/*  399 */         this.measurementsTM[i][1] = effectiveRIs[i];
/*  400 */         this.measurementsTM[i][2] = weights[i];
/*  401 */         this.measurementsTM[i][3] = modeNumbers[i];
/*      */       }
/*      */     }
/*  404 */     this.numberOfMeasurements = (this.numberOfTMmeasurements + this.numberOfTMmeasurements);
/*  405 */     this.setMeasurementsTM = true;
/*  406 */     this.setMeasurements = true;
/*  407 */     this.setErrorsTM = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void clearData()
/*      */   {
/*  413 */     this.numberOfMeasurements = 0;
/*  414 */     this.setMeasurements = false;
/*  415 */     this.setWeights = false;
/*      */     
/*  417 */     this.numberOfTEmeasurements = 0;
/*  418 */     this.setMeasurementsTE = false;
/*  419 */     this.setErrorsTE = false;
/*      */     
/*  421 */     this.numberOfTMmeasurements = 0;
/*  422 */     this.setMeasurementsTM = false;
/*  423 */     this.setErrorsTM = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setWavelength(double wavelength)
/*      */   {
/*  429 */     this.wavelength = wavelength;
/*  430 */     this.setWavelength = true;
/*  431 */     this.ko = (6.283185307179586D / this.wavelength);
/*  432 */     if (!this.setSuperstrate) { this.superstrateRefractiveIndex = RefractiveIndex.air(this.wavelength);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setSubstrateRefractiveIndex(double refIndex)
/*      */   {
/*  438 */     this.substrateRefractiveIndex = refIndex;
/*  439 */     this.substrateRefractiveIndex2 = (refIndex * refIndex);
/*  440 */     this.setSubstrate = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSuperstrateRefractiveIndex(double refIndex)
/*      */   {
/*  446 */     this.superstrateRefractiveIndex = refIndex;
/*  447 */     this.superstrateRefractiveIndex2 = (refIndex * refIndex);
/*  448 */     this.setSuperstrate = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getSuperstrateRefractiveIndex()
/*      */   {
/*  454 */     if ((!this.superCalculationDone) && (this.setCore)) calcSuperstrateRefractiveIndex();
/*  455 */     return this.superstrateRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getStandardDeviationSuperstrateRefractiveIndex()
/*      */   {
/*  460 */     if ((!this.superCalculationDone) && (this.setCore)) calcSuperstrateRefractiveIndex();
/*  461 */     if (this.setCore) {
/*  462 */       if (this.numberOfTMmeasurements + this.numberOfTEmeasurements == 1) System.out.println("Method: getStandardDeviationSuperstrateRefractiveIndex - Only one measurement entered - NO standard deviation returned");
/*      */     }
/*      */     else {
/*  465 */       System.out.println("Method: getStandardDeviationSuperstrateRefractiveIndex - Superstrate refractive index was entered and NOT calculated - NO standard deviation returned");
/*      */     }
/*  467 */     return this.sdCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCoreLayerRefractiveIndex(double refIndex)
/*      */   {
/*  473 */     this.coreFilmRefractiveIndex = refIndex;
/*  474 */     this.coreFilmRefractiveIndex2 = (refIndex * refIndex);
/*  475 */     this.setCore = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] getTEmodeCoreFilmRefractiveIndices()
/*      */   {
/*  481 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  482 */     if (this.numberOfTEmeasurements == 0) System.out.println("Method: getTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO refractive indices returned");
/*  483 */     return this.coreFilmTEmodeRefractiveIndices;
/*      */   }
/*      */   
/*      */   public double[] getTMmodeCoreFilmRefractiveIndices()
/*      */   {
/*  488 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  489 */     if (this.numberOfTMmeasurements == 0) System.out.println("Method: getTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO refractive indices returned");
/*  490 */     return this.coreFilmTMmodeRefractiveIndices;
/*      */   }
/*      */   
/*      */   public double getMeanTEmodeCoreFilmRefractiveIndex()
/*      */   {
/*  495 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  496 */     if (this.numberOfTEmeasurements == 0) System.out.println("Method: getMeanTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO refractive index returned");
/*  497 */     return this.meanTEmodeCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getMeanTMmodeCoreFilmRefractiveIndex()
/*      */   {
/*  502 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  503 */     if (this.numberOfTMmeasurements == 0) System.out.println("Method: getMeanTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO refractive index returned");
/*  504 */     return this.meanTMmodeCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getMeanCoreFilmRefractiveIndex()
/*      */   {
/*  509 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  510 */     return this.meanCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getCoreFilmRefractiveIndex()
/*      */   {
/*  515 */     if ((!this.calculationDone) && (!this.setCore)) calcCoreFilmRefractiveIndices();
/*  516 */     return this.coreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getStandardDeviationTEmodeCoreFilmRefractiveIndex()
/*      */   {
/*  521 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  522 */     if (this.numberOfTEmeasurements == 0) System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndex - NO TE mode data entered - NO standard deviation returned");
/*  523 */     if (this.numberOfTEmeasurements == 1) System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
/*  524 */     return this.sdTEmodeCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getStandardDeviationTMmodeCoreFilmRefractiveIndex()
/*      */   {
/*  529 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  530 */     if (this.numberOfTMmeasurements == 0) System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndex - NO TM mode data entered - NO standard deviation returned");
/*  531 */     if (this.numberOfTMmeasurements == 1) System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
/*  532 */     return this.sdTMmodeCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */   public double getStandardDeviationCoreFilmRefractiveIndex()
/*      */   {
/*  537 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  538 */     if (this.numberOfTMmeasurements + this.numberOfTEmeasurements == 1) System.out.println("Method: getStandardDeviationCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
/*  539 */     return this.sdCoreFilmRefractiveIndex;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getTEmodeExperimentalEffectiveRefractiveIndices()
/*      */   {
/*  545 */     double[][] returnedArray = (double[][])null;
/*  546 */     if (this.numberOfTEmeasurements == 0) {
/*  547 */       System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode data entered - NO effective refractive indices returned");
/*      */     }
/*      */     else {
/*  550 */       returnedArray = new double[2][this.numberOfTEmeasurements];
/*  551 */       returnedArray[0] = this.thicknessesUsedTE;
/*  552 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  553 */         returnedArray[1][i] = this.measurementsTE[i][1];
/*      */       }
/*      */     }
/*  556 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] getTEmodeEffectiveRefractiveIndicesErrors()
/*      */   {
/*  561 */     double[][] returnedArray = (double[][])null;
/*  562 */     if (this.numberOfTEmeasurements == 0) {
/*  563 */       System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode data entered - NO errors returned");
/*      */ 
/*      */     }
/*  566 */     else if (!this.setErrorsTE) {
/*  567 */       System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode errors entered - NO errors returned");
/*      */     }
/*      */     else {
/*  570 */       returnedArray = new double[2][this.numberOfTEmeasurements];
/*  571 */       returnedArray[0] = this.thicknessesUsedTE;
/*  572 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  573 */         returnedArray[1][i] = this.measurementsTE[i][2];
/*      */       }
/*      */     }
/*      */     
/*  577 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] getTMmodeExperimentalEffectiveRefractiveIndices()
/*      */   {
/*  582 */     double[][] returnedArray = (double[][])null;
/*  583 */     if (this.numberOfTMmeasurements == 0) {
/*  584 */       System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode data entered - NO effective refractive indices returned");
/*      */     }
/*      */     else {
/*  587 */       returnedArray = new double[2][this.numberOfTMmeasurements];
/*  588 */       returnedArray[0] = this.thicknessesUsedTM;
/*  589 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  590 */         returnedArray[1][i] = this.measurementsTM[i][1];
/*      */       }
/*      */     }
/*  593 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] getTMmodeEffectiveRefractiveIndicesErrors()
/*      */   {
/*  598 */     double[][] returnedArray = (double[][])null;
/*  599 */     if (this.numberOfTMmeasurements == 0) {
/*  600 */       System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode data entered - NO errors returned");
/*      */ 
/*      */     }
/*  603 */     else if (!this.setErrorsTM) {
/*  604 */       System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode errors entered - NO errors returned");
/*      */     }
/*      */     else {
/*  607 */       returnedArray = new double[2][this.numberOfTMmeasurements];
/*  608 */       returnedArray[0] = this.thicknessesUsedTM;
/*  609 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  610 */         returnedArray[1][i] = this.measurementsTM[i][2];
/*      */       }
/*      */     }
/*      */     
/*  614 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] getTEmodeCalculatedEffectiveRefractiveIndices()
/*      */   {
/*  619 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  620 */     if (this.numberOfTEmeasurements == 0) System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO effective refractive indices returned");
/*  621 */     double[][] returnedArray = new double[2][this.numberOfTEmeasurements];
/*      */     
/*      */ 
/*  624 */     FunctTEplot func = new FunctTEplot();
/*      */     
/*      */ 
/*  627 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/*  628 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/*  629 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/*  630 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/*  631 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/*  632 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/*  633 */     func.ko = this.ko;
/*      */     
/*  635 */     this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
/*  636 */     this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);
/*      */     
/*  638 */     for (int i = 0; i < this.numberOfTEmeasurements; i++)
/*      */     {
/*      */ 
/*  641 */       func.thickness = this.measurementsTE[i][0];
/*  642 */       func.modeNumber = this.measurementsTE[i][3];
/*      */       
/*      */ 
/*  645 */       RealRoot rr = new RealRoot();
/*  646 */       rr.noBoundsExtensions();
/*  647 */       rr.setTolerance(this.tolerance);
/*  648 */       this.calcEffectRefrIndicesTE[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*  650 */     returnedArray[0] = this.thicknessesUsedTE;
/*  651 */     returnedArray[1] = this.calcEffectRefrIndicesTE;
/*      */     
/*  653 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] getTMmodeCalculatedEffectiveRefractiveIndices()
/*      */   {
/*  658 */     if (!this.calculationDone) calcCoreFilmRefractiveIndices();
/*  659 */     if (this.numberOfTMmeasurements == 0) System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO effective refractive indices returned");
/*  660 */     double[][] returnedArray = new double[2][this.numberOfTMmeasurements];
/*      */     
/*      */ 
/*  663 */     FunctTMplot func = new FunctTMplot();
/*      */     
/*      */ 
/*  666 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/*  667 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/*  668 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/*  669 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/*  670 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/*  671 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/*  672 */     func.ko = this.ko;
/*      */     
/*  674 */     this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
/*  675 */     this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);
/*      */     
/*  677 */     for (int i = 0; i < this.numberOfTMmeasurements; i++)
/*      */     {
/*      */ 
/*  680 */       func.thickness = this.measurementsTM[i][0];
/*  681 */       func.modeNumber = this.measurementsTM[i][3];
/*      */       
/*      */ 
/*  684 */       RealRoot rr = new RealRoot();
/*  685 */       rr.noBoundsExtensions();
/*  686 */       rr.setTolerance(this.tolerance);
/*  687 */       this.calcEffectRefrIndicesTM[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*  689 */     returnedArray[0] = this.thicknessesUsedTM;
/*  690 */     returnedArray[1] = this.calcEffectRefrIndicesTM;
/*      */     
/*  692 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public void calcCoreFilmRefractiveIndices()
/*      */   {
/*  697 */     if (!this.setMeasurements) throw new IllegalArgumentException("Either no thickness, angle/effective refractive index, mode number data has been entered or a key subclass variable, e.g. coupling prism corner angle has not been entered");
/*  698 */     if (!this.setWavelength) throw new IllegalArgumentException("No wavelength has been entered");
/*  699 */     if (!this.setSubstrate) { throw new IllegalArgumentException("No substrate refractive index has been entered");
/*      */     }
/*      */     
/*  702 */     this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
/*  703 */     this.upperBound = 0.0D;
/*      */     
/*  705 */     if (this.numberOfTEmeasurements > 0) this.eliminatedTE = new boolean[this.numberOfTEmeasurements];
/*  706 */     int elimNumberTE = 0;
/*  707 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  708 */       this.eliminatedTE[i] = false;
/*  709 */       if (this.measurementsTE[i][1] < this.lowerBound) {
/*  710 */         System.out.println("TE mode measurement point, " + i + ", eliminated as the effective refractive index, " + this.measurementsTE[i][1] + ", lies below the physical limit, " + this.lowerBound);
/*  711 */         this.eliminatedTE[i] = true;
/*  712 */         elimNumberTE++;
/*      */ 
/*      */       }
/*  715 */       else if (this.upperBound < this.measurementsTE[i][1]) { this.upperBound = this.measurementsTE[i][1];
/*      */       }
/*      */     }
/*  718 */     if (elimNumberTE > 0) {
/*  719 */       int newNumber = this.numberOfTEmeasurements - elimNumberTE;
/*  720 */       if (newNumber == 0) {
/*  721 */         this.numberOfTEmeasurements = 0;
/*      */       }
/*      */       else {
/*  724 */         double[][] temp = new double[newNumber][3];
/*  725 */         int nIndex = 0;
/*  726 */         for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  727 */           if (this.eliminatedTE[i] == 0) {
/*  728 */             temp[nIndex][0] = this.measurementsTE[i][0];
/*  729 */             temp[nIndex][1] = this.measurementsTE[i][1];
/*  730 */             temp[nIndex][2] = this.measurementsTE[i][2];
/*  731 */             temp[nIndex][3] = this.measurementsTE[i][3];
/*  732 */             nIndex++;
/*      */           }
/*      */         }
/*  735 */         this.measurementsTE = temp;
/*  736 */         this.numberOfTEmeasurements = newNumber;
/*  737 */         this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*      */       }
/*      */     }
/*  740 */     this.thicknessesUsedTE = new double[this.numberOfTEmeasurements];
/*  741 */     this.calcEffectRefrIndicesTE = new double[this.numberOfTEmeasurements];
/*  742 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) this.thicknessesUsedTE[i] = this.measurementsTE[i][0];
/*  743 */     this.maximumTEmodeEffectiveRefractiveIndex = this.upperBound;
/*      */     
/*  745 */     this.upperBound = 0.0D;
/*  746 */     if (this.numberOfTMmeasurements > 0) this.eliminatedTM = new boolean[this.numberOfTMmeasurements];
/*  747 */     int elimNumberTM = 0;
/*  748 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  749 */       this.eliminatedTM[i] = false;
/*  750 */       if (this.measurementsTM[i][1] < this.lowerBound) {
/*  751 */         System.out.println("TM mode measurement point, " + i + ", eliminated as the effective refractive index, " + this.measurementsTM[i][1] + ", lies below the physical limit, " + this.lowerBound);
/*  752 */         this.eliminatedTM[i] = true;
/*  753 */         elimNumberTM++;
/*      */ 
/*      */       }
/*  756 */       else if (this.upperBound < this.measurementsTM[i][1]) { this.upperBound = this.measurementsTM[i][1];
/*      */       }
/*      */     }
/*  759 */     if (elimNumberTM > 0) {
/*  760 */       int newNumber = this.numberOfTMmeasurements - elimNumberTM;
/*  761 */       if (newNumber == 0) {
/*  762 */         this.numberOfTMmeasurements = 0;
/*      */       }
/*      */       else {
/*  765 */         double[][] temp = new double[newNumber][3];
/*  766 */         int nIndex = 0;
/*  767 */         for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  768 */           if (this.eliminatedTM[i] == 0) {
/*  769 */             temp[nIndex][0] = this.measurementsTM[i][0];
/*  770 */             temp[nIndex][1] = this.measurementsTM[i][1];
/*  771 */             temp[nIndex][2] = this.measurementsTM[i][2];
/*  772 */             temp[nIndex][3] = this.measurementsTM[i][3];
/*  773 */             nIndex++;
/*      */           }
/*      */         }
/*  776 */         this.measurementsTM = temp;
/*  777 */         this.numberOfTMmeasurements = newNumber;
/*  778 */         this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*      */       }
/*      */     }
/*  781 */     this.thicknessesUsedTM = new double[this.numberOfTMmeasurements];
/*  782 */     this.calcEffectRefrIndicesTM = new double[this.numberOfTMmeasurements];
/*  783 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) this.thicknessesUsedTM[i] = this.measurementsTM[i][0];
/*  784 */     this.maximumTMmodeEffectiveRefractiveIndex = this.upperBound;
/*      */     
/*  786 */     if (this.numberOfMeasurements == 0) { throw new IllegalArgumentException("All data points rejected as lying outside the physically meaningful bounds");
/*      */     }
/*  788 */     if (this.fixedPrismToWaveguideGap) {
/*  789 */       calcCoreFilmRefractiveIndicesFixedGap();
/*      */     }
/*      */     else {
/*  792 */       calcCoreFilmRefractiveIndicesEstimatedGap();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void calcCoreFilmRefractiveIndicesEstimatedGap()
/*      */   {
/*  800 */     ArrayList<Double> arrayl = new ArrayList();
/*      */     
/*      */ 
/*  803 */     this.prismToWaveguideGap = 10.0D;
/*      */     
/*      */ 
/*  806 */     this.fixedPrismToWaveguideGap = true;
/*      */     
/*      */ 
/*  809 */     double[] effectExpl = new double[this.numberOfMeasurements];
/*  810 */     double[] effectCalc = new double[this.numberOfMeasurements];
/*      */     
/*      */ 
/*  813 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) effectExpl[i] = this.measurementsTE[i][1];
/*  814 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) { effectExpl[(i + this.numberOfTEmeasurements)] = this.measurementsTM[i][1];
/*      */     }
/*      */     
/*  817 */     double sumOfSquares = 0.0D;
/*      */     
/*      */ 
/*  820 */     double sumOfSquaresLast = Double.POSITIVE_INFINITY;
/*      */     
/*      */ 
/*  823 */     int numberOfDecrements = 0;
/*      */     
/*      */ 
/*  826 */     boolean test = true;
/*  827 */     while (test)
/*      */     {
/*      */ 
/*  830 */       this.setCore = false;
/*  831 */       this.calculationDone = false;
/*  832 */       this.fixedPrismToWaveguideGap = true;
/*  833 */       this.setPrismToWaveguideGap = true;
/*      */       
/*      */ 
/*  836 */       double coreRI = getMeanCoreFilmRefractiveIndex();
/*      */       
/*      */ 
/*  839 */       if (coreRI != coreRI) {
/*  840 */         System.out.println("NaN");
/*  841 */         test = false;
/*      */       }
/*      */       else
/*      */       {
/*  845 */         double[][] effectTECalc = getTEmodeCalculatedEffectiveRefractiveIndices();
/*  846 */         for (int i = 0; i < this.numberOfTEmeasurements; i++) effectCalc[i] = effectTECalc[1][i];
/*  847 */         double[][] effectTMCalc = getTMmodeCalculatedEffectiveRefractiveIndices();
/*  848 */         for (int i = 0; i < this.numberOfTMmeasurements; i++) effectCalc[(i + this.numberOfTEmeasurements)] = effectTMCalc[1][i];
/*  849 */         sumOfSquares = 0.0D;
/*  850 */         for (int i = 0; i < this.numberOfMeasurements; i++) { sumOfSquares += Fmath.square(effectExpl[i] - effectCalc[i]);
/*      */         }
/*      */         
/*  853 */         System.out.println(this.prismToWaveguideGap + " " + coreRI + " " + sumOfSquares);
/*  854 */         arrayl.add(new Double(coreRI));
/*  855 */         arrayl.add(new Double(sumOfSquares));
/*  856 */         numberOfDecrements++;
/*      */         
/*      */ 
/*  859 */         this.prismToWaveguideGap /= 2.0D;
/*  860 */         if (this.prismToWaveguideGap < 1.0E-10D) { test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcCoreFilmRefractiveIndicesFixedGap()
/*      */   {
/*  869 */     if (this.numberOfTEmeasurements > 0) calcTEmodeCoreFilmRefractiveIndices();
/*  870 */     if (this.numberOfTMmeasurements > 0) { calcTMmodeCoreFilmRefractiveIndices();
/*      */     }
/*      */     
/*  873 */     if ((this.numberOfTEmeasurements > 0) && (this.numberOfTMmeasurements == 0)) {
/*  874 */       this.meanCoreFilmRefractiveIndex = this.meanTEmodeCoreFilmRefractiveIndex;
/*  875 */       this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
/*  876 */       this.sdCoreFilmRefractiveIndex = this.sdTEmodeCoreFilmRefractiveIndex;
/*      */ 
/*      */     }
/*  879 */     else if ((this.numberOfTMmeasurements > 0) && (this.numberOfTEmeasurements == 0)) {
/*  880 */       this.meanCoreFilmRefractiveIndex = this.meanTMmodeCoreFilmRefractiveIndex;
/*  881 */       this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
/*  882 */       this.sdCoreFilmRefractiveIndex = this.sdTMmodeCoreFilmRefractiveIndex;
/*      */     }
/*      */     else {
/*  885 */       double[] values = new double[this.numberOfMeasurements];
/*  886 */       double[] weights = new double[this.numberOfMeasurements];
/*  887 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  888 */         values[i] = this.coreFilmTEmodeRefractiveIndices[i];
/*  889 */         weights[i] = this.measurementsTE[i][2];
/*      */       }
/*  891 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  892 */         values[(i + this.numberOfTEmeasurements)] = this.coreFilmTMmodeRefractiveIndices[i];
/*  893 */         weights[(i + this.numberOfTEmeasurements)] = this.measurementsTM[i][2];
/*      */       }
/*  895 */       this.meanCoreFilmRefractiveIndex = Stat.mean(values, weights);
/*  896 */       this.sdCoreFilmRefractiveIndex = Stat.standardDeviation(values, weights);
/*  897 */       this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
/*      */     }
/*      */     
/*      */ 
/*  901 */     this.meanCoreFilmRefractiveIndex2 = (this.meanCoreFilmRefractiveIndex * this.meanCoreFilmRefractiveIndex);
/*  902 */     this.coreFilmRefractiveIndex2 = this.meanCoreFilmRefractiveIndex2;
/*  903 */     this.maximumEffectiveRefractiveIndex = Math.max(this.maximumTEmodeEffectiveRefractiveIndex, this.maximumTMmodeEffectiveRefractiveIndex);
/*  904 */     this.setCore = true;
/*  905 */     this.calculationDone = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcTEmodeCoreFilmRefractiveIndices()
/*      */   {
/*  911 */     this.coreFilmTEmodeRefractiveIndices = new double[this.numberOfTEmeasurements];
/*      */     
/*      */ 
/*  914 */     FunctTE func = new FunctTE();
/*      */     
/*      */ 
/*  917 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/*  918 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/*  919 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/*  920 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/*  921 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/*  922 */     func.ko = this.ko;
/*      */     
/*  924 */     double[] weights = new double[this.numberOfTEmeasurements];
/*  925 */     this.lowerBound = this.maximumTEmodeEffectiveRefractiveIndex;
/*  926 */     this.upperBound = (2.0D * this.lowerBound);
/*  927 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/*  928 */       weights[i] = this.measurementsTE[i][2];
/*      */       
/*      */ 
/*  931 */       func.thickness = this.measurementsTE[i][0];
/*  932 */       func.effectiveRefractiveIndex2 = (this.measurementsTE[i][1] * this.measurementsTE[i][1]);
/*  933 */       func.modeNumber = this.measurementsTE[i][3];
/*      */       
/*      */ 
/*  936 */       RealRoot rr = new RealRoot();
/*  937 */       rr.noLowerBoundExtension();
/*  938 */       rr.setTolerance(this.tolerance);
/*  939 */       this.coreFilmTEmodeRefractiveIndices[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*      */     
/*      */ 
/*  943 */     if (this.numberOfTEmeasurements > 1) {
/*  944 */       this.meanTEmodeCoreFilmRefractiveIndex = Stat.mean(this.coreFilmTEmodeRefractiveIndices, weights);
/*  945 */       this.sdTEmodeCoreFilmRefractiveIndex = Stat.standardDeviation(this.coreFilmTEmodeRefractiveIndices, weights);
/*      */     }
/*      */     else {
/*  948 */       this.meanTEmodeCoreFilmRefractiveIndex = this.coreFilmTEmodeRefractiveIndices[0];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcTMmodeCoreFilmRefractiveIndices()
/*      */   {
/*  955 */     this.coreFilmTMmodeRefractiveIndices = new double[this.numberOfTMmeasurements];
/*      */     
/*      */ 
/*  958 */     FunctTM func = new FunctTM();
/*      */     
/*      */ 
/*  961 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/*  962 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/*  963 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/*  964 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/*  965 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/*  966 */     func.ko = this.ko;
/*      */     
/*  968 */     double[] weights = new double[this.numberOfTMmeasurements];
/*  969 */     this.lowerBound = this.maximumTMmodeEffectiveRefractiveIndex;
/*  970 */     this.upperBound = (2.0D * this.lowerBound);
/*  971 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/*  972 */       weights[i] = this.measurementsTM[i][2];
/*      */       
/*      */ 
/*  975 */       func.thickness = this.measurementsTM[i][0];
/*  976 */       func.effectiveRefractiveIndex2 = (this.measurementsTM[i][1] * this.measurementsTM[i][1]);
/*  977 */       func.modeNumber = this.measurementsTM[i][3];
/*      */       
/*      */ 
/*  980 */       RealRoot rr = new RealRoot();
/*  981 */       rr.noLowerBoundExtension();
/*  982 */       rr.setTolerance(this.tolerance);
/*  983 */       this.coreFilmTMmodeRefractiveIndices[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*      */     
/*      */ 
/*  987 */     if (this.numberOfTMmeasurements > 1) {
/*  988 */       this.meanTMmodeCoreFilmRefractiveIndex = Stat.mean(this.coreFilmTMmodeRefractiveIndices, weights);
/*  989 */       this.sdTMmodeCoreFilmRefractiveIndex = Stat.standardDeviation(this.coreFilmTMmodeRefractiveIndices, weights);
/*      */     }
/*      */     else {
/*  992 */       this.meanTMmodeCoreFilmRefractiveIndex = this.coreFilmTMmodeRefractiveIndices[0];
/*      */     }
/*      */   }
/*      */   
/*      */   public double[][] dispersionCurveTE(double lowThickness, double highThickness, int numberOfPoints, double modeNumber)
/*      */   {
/*  998 */     if (!this.setWavelength) throw new IllegalArgumentException("No wavelength has been entered");
/*  999 */     if (!this.setSubstrate) throw new IllegalArgumentException("No substrate refractive index has been entered");
/* 1000 */     if (!this.setCore) { throw new IllegalArgumentException("No core film refractive index has been calculated or entered");
/*      */     }
/*      */     
/* 1003 */     double[] thickness = new double[numberOfPoints];
/* 1004 */     double[] effective = new double[numberOfPoints];
/* 1005 */     double[][] returnedArray = new double[2][numberOfPoints];
/* 1006 */     double incr = (Fmath.log10(highThickness) - Fmath.log10(lowThickness)) / (numberOfPoints - 1);
/* 1007 */     thickness[0] = Fmath.log10(lowThickness);
/* 1008 */     thickness[(numberOfPoints - 1)] = Fmath.log10(highThickness);
/* 1009 */     for (int i = 1; i < numberOfPoints - 1; i++) thickness[i] = (thickness[(i - 1)] + incr);
/* 1010 */     returnedArray[0] = thickness;
/*      */     
/*      */ 
/* 1013 */     FunctTEplot func = new FunctTEplot();
/*      */     
/*      */ 
/* 1016 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/* 1017 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/* 1018 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/* 1019 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/* 1020 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/* 1021 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/* 1022 */     func.ko = this.ko;
/* 1023 */     func.modeNumber = modeNumber;
/*      */     
/* 1025 */     this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
/* 1026 */     this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);
/*      */     
/* 1028 */     for (int i = 0; i < numberOfPoints; i++)
/*      */     {
/* 1030 */       func.thickness = Math.pow(10.0D, thickness[i]);
/*      */       
/*      */ 
/* 1033 */       RealRoot rr = new RealRoot();
/* 1034 */       rr.noBoundsExtensions();
/* 1035 */       rr.setTolerance(this.tolerance);
/* 1036 */       effective[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/* 1038 */     returnedArray[1] = effective;
/* 1039 */     return returnedArray;
/*      */   }
/*      */   
/*      */   public double[][] dispersionCurveTM(double lowThickness, double highThickness, int numberOfPoints, double modeNumber)
/*      */   {
/* 1044 */     if (!this.setWavelength) throw new IllegalArgumentException("No wavelength has been entered");
/* 1045 */     if (!this.setSubstrate) throw new IllegalArgumentException("No substrate refractive index has been entered");
/* 1046 */     if (!this.setCore) { throw new IllegalArgumentException("No core film refractive index has been calculated or entered");
/*      */     }
/*      */     
/* 1049 */     double[] thickness = new double[numberOfPoints];
/* 1050 */     double[] effective = new double[numberOfPoints];
/* 1051 */     double[][] returnedArray = new double[2][numberOfPoints];
/* 1052 */     double incr = (Fmath.log10(highThickness) - Fmath.log10(lowThickness)) / (numberOfPoints - 1);
/* 1053 */     thickness[0] = Fmath.log10(lowThickness);
/* 1054 */     thickness[(numberOfPoints - 1)] = Fmath.log10(highThickness);
/* 1055 */     for (int i = 1; i < numberOfPoints - 1; i++) thickness[i] = (thickness[(i - 1)] + incr);
/* 1056 */     returnedArray[0] = thickness;
/*      */     
/*      */ 
/* 1059 */     FunctTMplot func = new FunctTMplot();
/*      */     
/*      */ 
/* 1062 */     func.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
/* 1063 */     func.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
/* 1064 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/* 1065 */     func.prismRefractiveIndex2 = this.prismRefractiveIndex2;
/* 1066 */     func.prismToWaveguideGap = this.prismToWaveguideGap;
/* 1067 */     func.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
/* 1068 */     func.ko = this.ko;
/* 1069 */     func.modeNumber = modeNumber;
/*      */     
/* 1071 */     this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
/* 1072 */     this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);
/* 1073 */     for (int i = 0; i < numberOfPoints; i++)
/*      */     {
/* 1075 */       func.thickness = Math.pow(10.0D, thickness[i]);
/*      */       
/*      */ 
/* 1078 */       RealRoot rr = new RealRoot();
/* 1079 */       rr.noBoundsExtensions();
/* 1080 */       rr.setTolerance(this.tolerance);
/* 1081 */       effective[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/* 1083 */     returnedArray[1] = effective;
/* 1084 */     return returnedArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] plotDispersionCurveTE(double lowThickness, double highThickness, int numberOfPoints, double modeNumber)
/*      */   {
/* 1090 */     String legend1 = " ";
/* 1091 */     return plotDispersionCurveTE(lowThickness, highThickness, numberOfPoints, modeNumber, legend1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] plotDispersionCurveTE(double lowThickness, double highThickness, int numberOfPoints, double modeNumber, String legend1)
/*      */   {
/* 1099 */     double[][] curve = dispersionCurveTE(lowThickness, highThickness, numberOfPoints, modeNumber);
/*      */     
/*      */ 
/* 1102 */     PlotGraph pg1 = new PlotGraph(curve);
/* 1103 */     int lineOption = 3;
/* 1104 */     if (numberOfPoints < 100) lineOption = 1;
/* 1105 */     pg1.setLine(lineOption);
/* 1106 */     pg1.setPoint(0);
/* 1107 */     String legend0 = "Dispersion curve: TE mode  -  mode number " + (int)modeNumber;
/* 1108 */     pg1.setGraphTitle(legend0);
/* 1109 */     pg1.setGraphTitle2(legend1);
/* 1110 */     pg1.setXaxisLegend("Log10( Core Film Thickness / metres )");
/* 1111 */     pg1.setYaxisLegend("Effective Refractive Index (kz/ko)");
/*      */     
/*      */ 
/* 1114 */     pg1.plot();
/*      */     
/*      */ 
/* 1117 */     return curve;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] plotDispersionCurveTM(double lowThickness, double highThickness, int numberOfPoints, double modeNumber)
/*      */   {
/* 1123 */     String legend1 = " ";
/* 1124 */     return plotDispersionCurveTM(lowThickness, highThickness, numberOfPoints, modeNumber, legend1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] plotDispersionCurveTM(double lowThickness, double highThickness, int numberOfPoints, double modeNumber, String legend1)
/*      */   {
/* 1132 */     double[][] curve = dispersionCurveTM(lowThickness, highThickness, numberOfPoints, modeNumber);
/*      */     
/*      */ 
/* 1135 */     PlotGraph pg2 = new PlotGraph(curve);
/* 1136 */     int lineOption = 3;
/* 1137 */     if (numberOfPoints < 100) lineOption = 1;
/* 1138 */     pg2.setLine(lineOption);
/* 1139 */     pg2.setPoint(0);
/* 1140 */     String legend0 = "Dispersion curve: TM mode  -  mode number " + (int)modeNumber;
/* 1141 */     pg2.setGraphTitle(legend0);
/* 1142 */     pg2.setGraphTitle2(legend1);
/* 1143 */     pg2.setXaxisLegend("Log10( Core Film Thickness / metres )");
/* 1144 */     pg2.setYaxisLegend("Effective Refractive Index (kz/ko)");
/*      */     
/*      */ 
/* 1147 */     pg2.plot();
/*      */     
/*      */ 
/* 1150 */     return curve;
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotFittedDispersionCurves()
/*      */   {
/* 1156 */     String legend = "PlanarWaveguide.plotDispersion - Dispersion Plot";
/* 1157 */     plotFittedDispersionCurve(legend);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotFittedDispersionCurve(String legend)
/*      */   {
/* 1163 */     if (!this.calculationDone) { calcCoreFilmRefractiveIndices();
/*      */     }
/*      */     
/* 1166 */     ArrayList<Object> arraylTE = null;
/* 1167 */     int pOrderNumberTE = 0;
/* 1168 */     int pOrdersCheckedTE = 0;
/* 1169 */     int maximumNumberOfPoints = 0;
/* 1170 */     if (this.numberOfTEmeasurements > 0) {
/* 1171 */       arraylTE = new ArrayList();
/* 1172 */       boolean testModes = true;
/* 1173 */       int pOrder = 0;
/* 1174 */       int numberTestedPositive = 0;
/* 1175 */       while (testModes) {
/* 1176 */         int pNumber = 0;
/* 1177 */         for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/* 1178 */           if (this.measurementsTE[i][3] == pOrder) {
/* 1179 */             pNumber++;
/* 1180 */             numberTestedPositive++;
/* 1181 */             arraylTE.add(new Double(this.measurementsTE[i][0]));
/* 1182 */             arraylTE.add(new Double(this.measurementsTE[i][1]));
/*      */           }
/*      */         }
/* 1185 */         arraylTE.set(2 * pOrder, new Integer(pOrder));
/* 1186 */         arraylTE.set(2 * pOrder + 1, new Integer(pNumber));
/* 1187 */         if (pNumber > 0) pOrderNumberTE++;
/* 1188 */         if (pNumber > maximumNumberOfPoints) maximumNumberOfPoints = pNumber;
/* 1189 */         if (numberTestedPositive == this.numberOfTEmeasurements) {
/* 1190 */           testModes = false;
/*      */         }
/*      */         else {
/* 1193 */           pOrder++;
/*      */         }
/*      */       }
/* 1196 */       pOrdersCheckedTE = pOrder;
/*      */     }
/* 1198 */     int numberOfCurves = pOrderNumberTE;
/*      */     
/*      */ 
/* 1201 */     ArrayList<Object> arraylTM = null;
/* 1202 */     int pOrderNumberTM = 0;
/* 1203 */     int pOrdersCheckedTM = 0;
/* 1204 */     if (this.numberOfTMmeasurements > 0) {
/* 1205 */       arraylTM = new ArrayList();
/* 1206 */       boolean testModes = true;
/* 1207 */       int pOrder = 0;
/* 1208 */       int numberTestedPositive = 0;
/* 1209 */       while (testModes) {
/* 1210 */         int pNumber = 0;
/* 1211 */         for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/* 1212 */           if (this.measurementsTM[i][3] == pOrder) {
/* 1213 */             pNumber++;
/* 1214 */             numberTestedPositive++;
/* 1215 */             arraylTM.add(new Double(this.measurementsTM[i][0]));
/* 1216 */             arraylTM.add(new Double(this.measurementsTM[i][1]));
/*      */           }
/*      */         }
/* 1219 */         arraylTM.set(2 * pOrder, new Integer(pOrder));
/* 1220 */         arraylTM.set(2 * pOrder + 1, new Integer(pNumber));
/* 1221 */         if (pNumber > 0) pOrderNumberTM++;
/* 1222 */         if (pNumber > maximumNumberOfPoints) maximumNumberOfPoints = pNumber;
/* 1223 */         if (numberTestedPositive == this.numberOfTMmeasurements) {
/* 1224 */           testModes = false;
/*      */         }
/*      */         else {
/* 1227 */           pOrder++;
/*      */         }
/*      */       }
/* 1230 */       pOrdersCheckedTM = pOrder;
/*      */     }
/* 1232 */     numberOfCurves += pOrderNumberTM;
/* 1233 */     numberOfCurves *= 2;
/* 1234 */     if (maximumNumberOfPoints < 200) { maximumNumberOfPoints = 200;
/*      */     }
/*      */     
/*      */ 
/* 1238 */     double[][] plotData = PlotGraph.data(numberOfCurves, maximumNumberOfPoints);
/* 1239 */     double[] modeNumber = new double[numberOfCurves];
/* 1240 */     String[] modeType = new String[numberOfCurves];
/*      */     
/* 1242 */     int atCurveNumber = 0;
/* 1243 */     int plotNumber = 0;
/*      */     
/* 1245 */     int arraylIndex = 2 * (pOrdersCheckedTE + 1);
/* 1246 */     int arraylHeaderIndex = 1;
/* 1247 */     double tempD = 0.0D;
/* 1248 */     int tempI = 0;
/*      */     
/* 1250 */     if (this.numberOfTEmeasurements > 0) {
/* 1251 */       int testVec = 0;
/* 1252 */       int arraylSize = arraylTE.size();
/* 1253 */       while (testVec < arraylSize)
/*      */       {
/* 1255 */         tempI = ((Integer)arraylTE.get(arraylHeaderIndex)).intValue();
/* 1256 */         testVec++;
/* 1257 */         if (tempI > 0) {
/* 1258 */           modeType[atCurveNumber] = "TE";
/* 1259 */           modeType[(atCurveNumber + 1)] = "TE";
/* 1260 */           modeNumber[atCurveNumber] = ((Integer)arraylTE.get(arraylHeaderIndex - 1)).intValue();
/* 1261 */           modeNumber[(atCurveNumber + 1)] = modeNumber[atCurveNumber];
/* 1262 */           testVec++;
/*      */           
/*      */ 
/* 1265 */           double[] tempThick = new double[tempI];
/* 1266 */           double[] tempRefra = new double[tempI];
/* 1267 */           for (int i = 0; i < tempI; i++) {
/* 1268 */             tempThick[i] = ((Double)arraylTE.get(arraylIndex++)).doubleValue();
/* 1269 */             tempRefra[i] = ((Double)arraylTE.get(arraylIndex++)).doubleValue();
/* 1270 */             testVec += 2;
/*      */           }
/* 1272 */           double[] log10TempThick = (double[])tempThick.clone();
/* 1273 */           for (int i = 0; i < tempI; i++) { log10TempThick[i] = Fmath.log10(tempThick[i]);
/*      */           }
/* 1275 */           plotData[(plotNumber++)] = log10TempThick;
/* 1276 */           plotData[(plotNumber++)] = tempRefra;
/*      */           
/*      */ 
/* 1279 */           Fmath.selectionSort(tempThick, tempRefra, tempThick, tempRefra);
/*      */           
/*      */ 
/* 1282 */           double[][] curveTE = dispersionCurveTE(tempThick[0], tempThick[(tempI - 1)], maximumNumberOfPoints, modeNumber[atCurveNumber]);
/* 1283 */           plotData[(plotNumber++)] = curveTE[0];
/* 1284 */           plotData[(plotNumber++)] = curveTE[1];
/*      */           
/* 1286 */           atCurveNumber += 2;
/*      */         }
/* 1288 */         arraylHeaderIndex = 2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1293 */     arraylIndex = 2 * (pOrdersCheckedTM + 1);
/* 1294 */     arraylHeaderIndex = 1;
/* 1295 */     tempD = 0.0D;
/* 1296 */     tempI = 0;
/*      */     
/* 1298 */     if (this.numberOfTMmeasurements > 0) {
/* 1299 */       int testVec = 0;
/* 1300 */       int arraylSize = arraylTM.size();
/* 1301 */       while (testVec < arraylSize)
/*      */       {
/* 1303 */         tempI = ((Integer)arraylTM.get(arraylHeaderIndex)).intValue();
/* 1304 */         testVec++;
/* 1305 */         if (tempI > 0) {
/* 1306 */           modeType[atCurveNumber] = "TM";
/* 1307 */           modeType[(atCurveNumber + 1)] = "TM";
/* 1308 */           modeNumber[atCurveNumber] = ((Integer)arraylTM.get(arraylHeaderIndex - 1)).intValue();
/* 1309 */           testVec++;
/* 1310 */           modeNumber[(atCurveNumber + 1)] = modeNumber[atCurveNumber];
/*      */           
/*      */ 
/* 1313 */           double[] tempThick = new double[tempI];
/* 1314 */           double[] tempRefra = new double[tempI];
/* 1315 */           for (int i = 0; i < tempI; i++) {
/* 1316 */             tempThick[i] = ((Double)arraylTM.get(arraylIndex++)).doubleValue();
/* 1317 */             tempRefra[i] = ((Double)arraylTM.get(arraylIndex++)).doubleValue();
/* 1318 */             testVec += 2;
/*      */           }
/* 1320 */           double[] log10TempThick = (double[])tempThick.clone();
/* 1321 */           for (int i = 0; i < tempI; i++) { log10TempThick[i] = Fmath.log10(tempThick[i]);
/*      */           }
/* 1323 */           plotData[(plotNumber++)] = log10TempThick;
/* 1324 */           plotData[(plotNumber++)] = tempRefra;
/*      */           
/*      */ 
/* 1327 */           Fmath.selectionSort(tempThick, tempRefra, tempThick, tempRefra);
/*      */           
/*      */ 
/* 1330 */           double[][] curveTM = dispersionCurveTM(tempThick[0], tempThick[(tempI - 1)], maximumNumberOfPoints, modeNumber[atCurveNumber]);
/* 1331 */           plotData[(plotNumber++)] = curveTM[0];
/* 1332 */           plotData[(plotNumber++)] = curveTM[1];
/*      */           
/* 1334 */           atCurveNumber += 2;
/*      */         }
/* 1336 */         arraylHeaderIndex = 2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1341 */     PlotGraph pg0 = new PlotGraph(plotData);
/*      */     
/* 1343 */     int[] lineOptions = new int[numberOfCurves];
/* 1344 */     for (int i = 0; i < numberOfCurves; i += 2) {
/* 1345 */       lineOptions[i] = 0;
/* 1346 */       lineOptions[(i + 1)] = 3;
/* 1347 */       if (maximumNumberOfPoints < 100) lineOptions[(i + 1)] = 1;
/*      */     }
/* 1349 */     pg0.setLine(lineOptions);
/*      */     
/* 1351 */     int[] pointOptions = new int[numberOfCurves];
/* 1352 */     int jj = 1;
/* 1353 */     for (int i = 0; i < numberOfCurves; i += 2) {
/* 1354 */       pointOptions[i] = jj;
/* 1355 */       pointOptions[(i + 1)] = 0;
/* 1356 */       jj++;
/*      */     }
/* 1358 */     pg0.setPoint(pointOptions);
/*      */     
/* 1360 */     pg0.setGraphTitle(legend);
/* 1361 */     pg0.setXaxisLegend("Log10( Core Film Thickness / metres )");
/* 1362 */     pg0.setYaxisLegend("Effective Refractive Index (kz/ko)");
/*      */     
/*      */ 
/* 1365 */     pg0.plot();
/*      */   }
/*      */   
/*      */   public void calcSuperstrateRefractiveIndex()
/*      */   {
/* 1370 */     if (!this.setMeasurements) throw new IllegalArgumentException("Either no thickness, angle/effective refractive index, mode number data has been entered or a key subclass variable, e.g. coupling prism corner angle has not been entered");
/* 1371 */     if (!this.setWavelength) throw new IllegalArgumentException("No wavelength has been entered");
/* 1372 */     if (!this.setSubstrate) throw new IllegalArgumentException("No substrate refractive index has been entered");
/* 1373 */     if (!this.setCore) { throw new IllegalArgumentException("No core layer refractive index has been entered");
/*      */     }
/*      */     
/* 1376 */     this.lowerBound = 1.0D;
/* 1377 */     this.upperBound = this.coreFilmRefractiveIndex;
/*      */     
/* 1379 */     if (this.numberOfTEmeasurements > 0) this.eliminatedTE = new boolean[this.numberOfTEmeasurements];
/* 1380 */     int elimNumberTE = 0;
/* 1381 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/* 1382 */       this.eliminatedTE[i] = false;
/* 1383 */       if (this.measurementsTE[i][1] > this.coreFilmRefractiveIndex) {
/* 1384 */         System.out.println("TE mode measurement point, " + i + ", eliminated as the effective refractive index, " + this.measurementsTE[i][1] + ", lies above the physical limit, " + this.coreFilmRefractiveIndex);
/* 1385 */         this.eliminatedTE[i] = true;
/* 1386 */         elimNumberTE++;
/*      */ 
/*      */       }
/* 1389 */       else if (this.upperBound > this.measurementsTE[i][1]) { this.upperBound = this.measurementsTE[i][1];
/*      */       }
/*      */     }
/* 1392 */     if (elimNumberTE > 0) {
/* 1393 */       int newNumber = this.numberOfTEmeasurements - elimNumberTE;
/* 1394 */       if (newNumber == 0) {
/* 1395 */         this.numberOfTEmeasurements = 0;
/*      */       }
/*      */       else {
/* 1398 */         double[][] temp = new double[newNumber][3];
/* 1399 */         int nIndex = 0;
/* 1400 */         for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/* 1401 */           if (this.eliminatedTE[i] == 0) {
/* 1402 */             temp[nIndex][0] = this.measurementsTE[i][0];
/* 1403 */             temp[nIndex][1] = this.measurementsTE[i][1];
/* 1404 */             temp[nIndex][2] = this.measurementsTE[i][2];
/* 1405 */             temp[nIndex][3] = this.measurementsTE[i][3];
/* 1406 */             nIndex++;
/*      */           }
/*      */         }
/* 1409 */         this.measurementsTE = temp;
/* 1410 */         this.numberOfTEmeasurements = newNumber;
/* 1411 */         this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*      */       }
/*      */     }
/* 1414 */     this.thicknessesUsedTE = new double[this.numberOfTEmeasurements];
/* 1415 */     this.calcEffectRefrIndicesTE = new double[this.numberOfTEmeasurements];
/* 1416 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) this.thicknessesUsedTE[i] = this.measurementsTE[i][0];
/* 1417 */     this.minimumTEmodeEffectiveRefractiveIndex = this.upperBound;
/*      */     
/* 1419 */     this.upperBound = 0.0D;
/* 1420 */     if (this.numberOfTMmeasurements > 0) this.eliminatedTM = new boolean[this.numberOfTMmeasurements];
/* 1421 */     int elimNumberTM = 0;
/* 1422 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/* 1423 */       this.eliminatedTM[i] = false;
/* 1424 */       if (this.measurementsTM[i][1] > this.coreFilmRefractiveIndex) {
/* 1425 */         System.out.println("TM mode measurement point, " + i + ", eliminated as the effective refractive index, " + this.measurementsTM[i][1] + ", lies above the physical limit, " + this.coreFilmRefractiveIndex);
/* 1426 */         this.eliminatedTM[i] = true;
/* 1427 */         elimNumberTM++;
/*      */ 
/*      */       }
/* 1430 */       else if (this.upperBound > this.measurementsTM[i][1]) { this.upperBound = this.measurementsTM[i][1];
/*      */       }
/*      */     }
/* 1433 */     if (elimNumberTM > 0) {
/* 1434 */       int newNumber = this.numberOfTMmeasurements - elimNumberTM;
/* 1435 */       if (newNumber == 0) {
/* 1436 */         this.numberOfTMmeasurements = 0;
/*      */       }
/*      */       else {
/* 1439 */         double[][] temp = new double[newNumber][3];
/* 1440 */         int nIndex = 0;
/* 1441 */         for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/* 1442 */           if (this.eliminatedTM[i] == 0) {
/* 1443 */             temp[nIndex][0] = this.measurementsTM[i][0];
/* 1444 */             temp[nIndex][1] = this.measurementsTM[i][1];
/* 1445 */             temp[nIndex][2] = this.measurementsTM[i][2];
/* 1446 */             temp[nIndex][3] = this.measurementsTM[i][3];
/* 1447 */             nIndex++;
/*      */           }
/*      */         }
/* 1450 */         this.measurementsTM = temp;
/* 1451 */         this.numberOfTMmeasurements = newNumber;
/* 1452 */         this.numberOfMeasurements = (this.numberOfTEmeasurements + this.numberOfTMmeasurements);
/*      */       }
/*      */     }
/* 1455 */     this.thicknessesUsedTM = new double[this.numberOfTMmeasurements];
/* 1456 */     this.calcEffectRefrIndicesTM = new double[this.numberOfTMmeasurements];
/* 1457 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) this.thicknessesUsedTM[i] = this.measurementsTM[i][0];
/* 1458 */     this.minimumTMmodeEffectiveRefractiveIndex = this.upperBound;
/*      */     
/* 1460 */     if (this.numberOfMeasurements == 0) { throw new IllegalArgumentException("All data points rejected as lying outside the physically meaningful bounds");
/*      */     }
/*      */     
/* 1463 */     if (this.numberOfTEmeasurements > 0) calcTEmodeSuperstrateRefractiveIndices();
/* 1464 */     if (this.numberOfTMmeasurements > 0) { calcTMmodeSuperstrateRefractiveIndices();
/*      */     }
/*      */     
/* 1467 */     if ((this.numberOfTEmeasurements > 0) && (this.numberOfTMmeasurements == 0)) {
/* 1468 */       this.superstrateRefractiveIndex = this.meanTEmodeSuperstrateRefractiveIndex;
/* 1469 */       this.sdSuperstrateRefractiveIndex = this.sdTEmodeSuperstrateRefractiveIndex;
/*      */ 
/*      */     }
/* 1472 */     else if ((this.numberOfTMmeasurements > 0) && (this.numberOfTEmeasurements == 0)) {
/* 1473 */       this.superstrateRefractiveIndex = this.meanTMmodeSuperstrateRefractiveIndex;
/* 1474 */       this.sdSuperstrateRefractiveIndex = this.sdTMmodeSuperstrateRefractiveIndex;
/*      */     }
/*      */     else {
/* 1477 */       double[] values = new double[this.numberOfMeasurements];
/* 1478 */       double[] weights = new double[this.numberOfMeasurements];
/* 1479 */       for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/* 1480 */         values[i] = this.calcSuperstrateTEmodeRI[i];
/* 1481 */         weights[i] = this.measurementsTE[i][2];
/*      */       }
/* 1483 */       for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/* 1484 */         values[(i + this.numberOfTEmeasurements)] = this.calcSuperstrateTMmodeRI[i];
/* 1485 */         weights[(i + this.numberOfTEmeasurements)] = this.measurementsTM[i][2];
/*      */       }
/* 1487 */       this.superstrateRefractiveIndex = Stat.mean(values, weights);
/* 1488 */       this.sdSuperstrateRefractiveIndex = Stat.standardDeviation(values, weights);
/*      */     }
/*      */     
/*      */ 
/* 1492 */     this.superstrateRefractiveIndex2 = (this.superstrateRefractiveIndex * this.superstrateRefractiveIndex);
/* 1493 */     this.minimumEffectiveRefractiveIndex = Math.min(this.minimumTEmodeEffectiveRefractiveIndex, this.minimumTMmodeEffectiveRefractiveIndex);
/* 1494 */     this.superCalculationDone = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcTEmodeSuperstrateRefractiveIndices()
/*      */   {
/* 1500 */     this.calcSuperstrateTEmodeRI = new double[this.numberOfTEmeasurements];
/*      */     
/*      */ 
/* 1503 */     FunctTEsuper func = new FunctTEsuper();
/*      */     
/*      */ 
/* 1506 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/* 1507 */     func.ko = this.ko;
/*      */     
/* 1509 */     double[] weights = new double[this.numberOfTEmeasurements];
/* 1510 */     this.lowerBound = 1.0D;
/* 1511 */     this.upperBound = this.minimumTEmodeEffectiveRefractiveIndex;
/*      */     
/* 1513 */     for (int i = 0; i < this.numberOfTEmeasurements; i++) {
/* 1514 */       weights[i] = this.measurementsTE[i][2];
/*      */       
/*      */ 
/* 1517 */       func.thickness = this.measurementsTE[i][0];
/* 1518 */       func.effectiveRefractiveIndex2 = (this.measurementsTE[i][1] * this.measurementsTE[i][1]);
/* 1519 */       func.modeNumber = this.measurementsTE[i][3];
/*      */       
/*      */ 
/* 1522 */       RealRoot rr = new RealRoot();
/* 1523 */       rr.noBoundsExtensions();
/* 1524 */       rr.setTolerance(this.tolerance);
/* 1525 */       this.calcSuperstrateTEmodeRI[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*      */     
/*      */ 
/* 1529 */     if (this.numberOfTEmeasurements > 1) {
/* 1530 */       this.meanTEmodeSuperstrateRefractiveIndex = Stat.mean(this.calcSuperstrateTEmodeRI, weights);
/* 1531 */       this.sdTEmodeSuperstrateRefractiveIndex = Stat.standardDeviation(this.calcSuperstrateTEmodeRI, weights);
/*      */     }
/*      */     else {
/* 1534 */       this.meanTEmodeSuperstrateRefractiveIndex = this.calcSuperstrateTEmodeRI[0];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcTMmodeSuperstrateRefractiveIndices()
/*      */   {
/* 1541 */     this.calcSuperstrateTMmodeRI = new double[this.numberOfTMmeasurements];
/*      */     
/*      */ 
/* 1544 */     FunctTMsuper func = new FunctTMsuper();
/*      */     
/*      */ 
/* 1547 */     func.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
/* 1548 */     func.ko = this.ko;
/*      */     
/* 1550 */     double[] weights = new double[this.numberOfTMmeasurements];
/* 1551 */     this.lowerBound = 1.0D;
/* 1552 */     this.upperBound = this.minimumTMmodeEffectiveRefractiveIndex;
/*      */     
/* 1554 */     for (int i = 0; i < this.numberOfTMmeasurements; i++) {
/* 1555 */       weights[i] = this.measurementsTM[i][2];
/*      */       
/*      */ 
/* 1558 */       func.thickness = this.measurementsTM[i][0];
/* 1559 */       func.effectiveRefractiveIndex2 = (this.measurementsTM[i][1] * this.measurementsTM[i][1]);
/* 1560 */       func.modeNumber = this.measurementsTM[i][3];
/*      */       
/*      */ 
/* 1563 */       RealRoot rr = new RealRoot();
/* 1564 */       rr.noBoundsExtensions();
/* 1565 */       rr.setTolerance(this.tolerance);
/* 1566 */       this.calcSuperstrateTMmodeRI[i] = rr.bisect(func, this.lowerBound, this.upperBound);
/*      */     }
/*      */     
/*      */ 
/* 1570 */     if (this.numberOfTMmeasurements > 1) {
/* 1571 */       this.meanTMmodeSuperstrateRefractiveIndex = Stat.mean(this.calcSuperstrateTMmodeRI, weights);
/* 1572 */       this.sdTMmodeSuperstrateRefractiveIndex = Stat.standardDeviation(this.calcSuperstrateTMmodeRI, weights);
/*      */     }
/*      */     else {
/* 1575 */       this.meanTMmodeSuperstrateRefractiveIndex = this.calcSuperstrateTMmodeRI[0];
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/optics/PlanarWaveguide.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */