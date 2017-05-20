/*      */ package flanagan.circuits;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.io.Db;
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
/*      */ public class ImpedSpecSimulation
/*      */ {
/*   47 */   private double lowestFrequency = 0.0D;
/*   48 */   private double lowestOmega = 0.0D;
/*   49 */   private boolean lowestSet = false;
/*   50 */   private double highestFrequency = 0.0D;
/*   51 */   private double highestOmega = 0.0D;
/*   52 */   private boolean highestSet = false;
/*   53 */   private boolean logOrLinear = true;
/*      */   
/*   55 */   private double increment = 0.0D;
/*      */   
/*      */ 
/*   58 */   private double[] frequencies = null;
/*   59 */   private double[] omegas = null;
/*   60 */   private double[] log10frequencies = null;
/*   61 */   private double[] log10omegas = null;
/*      */   
/*   63 */   private int numberOfFrequencies = 800;
/*   64 */   private boolean numberSet = true;
/*   65 */   private boolean frequenciesSet = false;
/*      */   
/*   67 */   private int modelNumber = 0;
/*   68 */   private double[] parameters = null;
/*   69 */   private int numberOfParameters = 0;
/*   70 */   private String[] modelParameterSymbols = null;
/*   71 */   private boolean parametersSet = false;
/*   72 */   private boolean modelSet = false;
/*      */   
/*   74 */   private Complex[] impedances = null;
/*   75 */   private double[] magnitudesZ = null;
/*   76 */   private double[] phasesRadZ = null;
/*   77 */   private double[] phasesDegZ = null;
/*   78 */   private double[] realZ = null;
/*   79 */   private double[] imagZ = null;
/*      */   
/*   81 */   private boolean impedancesSet = false;
/*      */   
/*   83 */   private Complex[] voltages = null;
/*   84 */   private double[] magnitudesV = null;
/*   85 */   private double[] phasesRadV = null;
/*   86 */   private double[] phasesDegV = null;
/*   87 */   private double[] realV = null;
/*   88 */   private double[] imagV = null;
/*      */   
/*   90 */   private ImpedSpecModel userModel = null;
/*      */   
/*   92 */   private String simulationTitle = null;
/*   93 */   private boolean fileType = false;
/*      */   
/*   95 */   private Complex appliedVoltage = null;
/*   96 */   private boolean voltageSet = false;
/*      */   
/*   98 */   private Complex referenceImpedance = null;
/*   99 */   private boolean referenceSet = false;
/*      */   
/*      */ 
/*      */   public ImpedSpecSimulation()
/*      */   {
/*  104 */     this.simulationTitle = "  ";
/*      */   }
/*      */   
/*      */   public ImpedSpecSimulation(String simulationTitle)
/*      */   {
/*  109 */     this.simulationTitle = simulationTitle;
/*      */   }
/*      */   
/*      */   public void setScanRangeHz(double low, double high)
/*      */   {
/*  114 */     this.lowestFrequency = low;
/*  115 */     this.lowestOmega = (6.283185307179586D * low);
/*  116 */     this.highestFrequency = high;
/*  117 */     this.highestOmega = (6.283185307179586D * high);
/*  118 */     calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setScanRangeRadians(double low, double high)
/*      */   {
/*  123 */     this.lowestFrequency = (low / 6.283185307179586D);
/*  124 */     this.lowestOmega = low;
/*  125 */     this.highestFrequency = (high / 6.283185307179586D);
/*  126 */     this.highestOmega = high;
/*  127 */     calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setLowFrequency(double low)
/*      */   {
/*  132 */     this.lowestFrequency = low;
/*  133 */     this.lowestOmega = (6.283185307179586D * low);
/*  134 */     this.lowestSet = true;
/*  135 */     if ((this.highestSet) && (this.numberSet)) calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setLowRadialFrequency(double low)
/*      */   {
/*  140 */     this.lowestOmega = low;
/*  141 */     this.lowestFrequency = (low / 6.283185307179586D);
/*  142 */     this.lowestSet = true;
/*  143 */     if ((this.highestSet) && (this.numberSet)) calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setHighFrequency(double high)
/*      */   {
/*  148 */     this.highestFrequency = high;
/*  149 */     this.highestOmega = (6.283185307179586D * high);
/*  150 */     this.highestSet = true;
/*  151 */     if ((this.lowestSet) && (this.numberSet)) calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setHighRadialFrequency(double high)
/*      */   {
/*  156 */     this.highestOmega = high;
/*  157 */     this.highestFrequency = (high / 6.283185307179586D);
/*  158 */     this.highestSet = true;
/*  159 */     if ((this.lowestSet) && (this.numberSet)) { calculateFrequencies();
/*      */     }
/*      */   }
/*      */   
/*      */   private void calculateFrequencies()
/*      */   {
/*  165 */     if (this.logOrLinear) {
/*  166 */       double logLow = Fmath.log10(this.lowestFrequency);
/*  167 */       double logHigh = Fmath.log10(this.highestFrequency);
/*  168 */       this.increment = ((logHigh - logLow) / (this.numberOfFrequencies - 1));
/*  169 */       this.frequencies = new double[this.numberOfFrequencies];
/*  170 */       this.log10frequencies = new double[this.numberOfFrequencies];
/*  171 */       this.omegas = new double[this.numberOfFrequencies];
/*  172 */       this.log10omegas = new double[this.numberOfFrequencies];
/*  173 */       this.log10frequencies[0] = logLow;
/*  174 */       this.log10frequencies[(this.numberOfFrequencies - 1)] = logHigh;
/*  175 */       for (int i = 1; i < this.numberOfFrequencies - 1; i++) this.log10frequencies[i] = (this.log10frequencies[(i - 1)] + this.increment);
/*  176 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  177 */         this.frequencies[i] = Math.pow(10.0D, this.log10frequencies[i]);
/*  178 */         this.omegas[i] = (this.frequencies[i] * 2.0D * 3.141592653589793D);
/*  179 */         this.log10omegas[i] = Fmath.log10(this.omegas[i]);
/*      */       }
/*      */     }
/*      */     else {
/*  183 */       this.increment = ((this.highestFrequency - this.lowestFrequency) / (this.numberOfFrequencies - 1));
/*  184 */       this.frequencies = new double[this.numberOfFrequencies];
/*  185 */       this.frequencies[0] = this.lowestFrequency;
/*  186 */       this.log10frequencies = new double[this.numberOfFrequencies];
/*  187 */       this.omegas = new double[this.numberOfFrequencies];
/*  188 */       this.log10omegas = new double[this.numberOfFrequencies];
/*  189 */       this.frequencies[(this.numberOfFrequencies - 1)] = this.highestFrequency;
/*  190 */       for (int i = 1; i < this.numberOfFrequencies - 1; i++) this.frequencies[i] = (this.frequencies[(i - 1)] + this.increment);
/*  191 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  192 */         this.log10frequencies[i] = Fmath.log10(this.frequencies[i]);
/*  193 */         this.omegas[i] = (this.frequencies[i] * 2.0D * 3.141592653589793D);
/*  194 */         this.log10omegas[i] = Fmath.log10(this.omegas[i]);
/*      */       }
/*      */     }
/*  197 */     this.frequenciesSet = true;
/*      */   }
/*      */   
/*      */   public void setLinearPlot()
/*      */   {
/*  202 */     this.logOrLinear = false;
/*  203 */     if ((this.lowestSet) && (this.highestSet) && (this.numberSet)) { calculateFrequencies();
/*      */     }
/*      */   }
/*      */   
/*      */   public void setLog10Plot()
/*      */   {
/*  209 */     this.logOrLinear = true;
/*  210 */     if ((this.lowestSet) && (this.highestSet) && (this.numberSet)) calculateFrequencies();
/*      */   }
/*      */   
/*      */   public void setAppliedVoltage(double voltage)
/*      */   {
/*  215 */     this.appliedVoltage = new Complex(voltage, 0.0D);
/*  216 */     this.voltageSet = true;
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(double resistance)
/*      */   {
/*  221 */     this.referenceImpedance = new Complex(resistance, 0.0D);
/*  222 */     this.referenceSet = true;
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(double real, double imag)
/*      */   {
/*  227 */     this.referenceImpedance = new Complex(real, imag);
/*  228 */     this.referenceSet = true;
/*      */   }
/*      */   
/*      */   public void setReferenceImpedance(Complex impedance)
/*      */   {
/*  233 */     this.referenceImpedance = impedance;
/*  234 */     this.referenceSet = true;
/*      */   }
/*      */   
/*      */   public void setModel(int modelNumber, double[] parameters)
/*      */   {
/*  239 */     if ((modelNumber == 0) || (modelNumber > Impedance.numberOfModels)) throw new IllegalArgumentException("The model number, " + modelNumber + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
/*  240 */     this.modelNumber = modelNumber;
/*  241 */     this.parameters = parameters;
/*  242 */     this.modelParameterSymbols = Impedance.modelComponents(modelNumber);
/*  243 */     this.numberOfParameters = this.modelParameterSymbols.length;
/*  244 */     if (this.numberOfParameters != this.parameters.length) throw new IllegalArgumentException("The number of model parametes passed, " + parameters.length + ", does not match the number required, " + this.numberOfParameters + ", by model number " + modelNumber);
/*  245 */     this.parametersSet = true;
/*  246 */     this.modelSet = true;
/*      */   }
/*      */   
/*      */   public void setModel(int modelNumber, double[] parameters, String[] symbols)
/*      */   {
/*  251 */     if ((modelNumber == 0) || (modelNumber > Impedance.numberOfModels)) throw new IllegalArgumentException("The model number, " + modelNumber + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
/*  252 */     this.modelNumber = modelNumber;
/*  253 */     this.parameters = parameters;
/*  254 */     this.modelParameterSymbols = Impedance.modelComponents(modelNumber);
/*  255 */     this.numberOfParameters = this.modelParameterSymbols.length;
/*  256 */     if (this.numberOfParameters != this.parameters.length) throw new IllegalArgumentException("The number of model parametes passed, " + parameters.length + ", does not match the numbber required, " + this.numberOfParameters + ", by model number " + modelNumber);
/*  257 */     if (this.numberOfParameters != symbols.length) throw new IllegalArgumentException("The number of model symbols passed, " + symbols.length + ", does not match the number required, " + this.numberOfParameters + ", by model number " + modelNumber);
/*  258 */     this.modelParameterSymbols = symbols;
/*  259 */     this.parametersSet = true;
/*  260 */     this.modelSet = true;
/*      */   }
/*      */   
/*      */   public void setModel(int modelNumber)
/*      */   {
/*  265 */     if ((modelNumber == 0) || (modelNumber > Impedance.numberOfModels)) throw new IllegalArgumentException("The model number, " + modelNumber + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
/*  266 */     this.modelNumber = modelNumber;
/*  267 */     this.modelSet = true;
/*  268 */     this.modelParameterSymbols = Impedance.modelComponents(modelNumber);
/*  269 */     this.numberOfParameters = this.modelParameterSymbols.length;
/*  270 */     this.parameters = new double[this.numberOfParameters];
/*      */     
/*      */ 
/*  273 */     int ii = 0;
/*  274 */     String symbol = null;
/*  275 */     while (ii < this.numberOfParameters) {
/*  276 */       symbol = this.modelParameterSymbols[ii];
/*  277 */       if (symbol.trim().charAt(0) == 'R') {
/*  278 */         this.parameters[ii] = Db.readDouble("Enter resistance " + symbol.trim() + " [ohms]");
/*  279 */         ii++;
/*      */ 
/*      */       }
/*  282 */       else if (symbol.trim().charAt(0) == 'C') {
/*  283 */         this.parameters[ii] = Db.readDouble("Enter capacitance " + symbol.trim() + " [farads]");
/*  284 */         ii++;
/*      */ 
/*      */       }
/*  287 */       else if (symbol.trim().charAt(0) == 'L') {
/*  288 */         this.parameters[ii] = Db.readDouble("Enter inductance " + symbol.trim() + " [henries]");
/*  289 */         ii++;
/*      */ 
/*      */       }
/*  292 */       else if (symbol.trim().charAt(0) == 'W') {
/*  293 */         this.parameters[ii] = Db.readDouble("Enter 'infinite' Warburg constant, sigma, " + symbol.trim() + " [ohms*sqrt(radians)]");
/*  294 */         ii++;
/*      */ 
/*      */       }
/*  297 */       else if (symbol.trim().charAt(0) == 'F') {
/*  298 */         this.parameters[ii] = Db.readDouble("Enter 'finite' Warburg constant, sigma, " + symbol.trim() + " [SI units]");
/*  299 */         ii++;
/*      */         
/*  301 */         this.parameters[ii] = Db.readDouble("Enter 'finite' Warburg power, alpha, " + symbol.trim());
/*  302 */         ii++;
/*      */ 
/*      */ 
/*      */       }
/*  306 */       else if (symbol.trim().charAt(0) == 'Q') {
/*  307 */         this.parameters[ii] = Db.readDouble("Enter CPE constant, sigma, " + symbol.trim() + " [SI units]");
/*  308 */         ii++;
/*      */         
/*  310 */         this.parameters[ii] = Db.readDouble("Enter CPE power, alpha, " + symbol.trim());
/*  311 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  319 */     this.parametersSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModel(ImpedSpecModel userModel, double[] parameters)
/*      */   {
/*  325 */     this.userModel = userModel;
/*  326 */     this.parameters = parameters;
/*  327 */     this.numberOfParameters = parameters.length;
/*  328 */     this.modelParameterSymbols = new String[this.numberOfParameters];
/*  329 */     for (int i = 0; i < this.numberOfParameters; i++) this.modelParameterSymbols[i] = ("P" + (i + 1));
/*  330 */     this.parametersSet = true;
/*      */   }
/*      */   
/*      */   public void setModel(ImpedSpecModel userModel, double[] parameters, String[] symbols)
/*      */   {
/*  335 */     this.userModel = userModel;
/*  336 */     this.parameters = parameters;
/*  337 */     this.modelParameterSymbols = symbols;
/*  338 */     this.numberOfParameters = parameters.length;
/*  339 */     this.parametersSet = true;
/*      */   }
/*      */   
/*      */   public Complex[] calculateImpedances()
/*      */   {
/*  344 */     if (!this.parametersSet) throw new IllegalArgumentException("model parameters values have not been entered");
/*  345 */     if (!this.frequenciesSet) throw new IllegalArgumentException("frequency values have not been entered");
/*  346 */     this.impedances = Complex.oneDarray(this.numberOfFrequencies);
/*  347 */     if (this.modelSet) {
/*  348 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  349 */         this.impedances[i] = Impedance.modelImpedance(this.parameters, this.omegas[i], this.modelNumber);
/*      */       }
/*      */       
/*      */     } else {
/*  353 */       for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  354 */         this.impedances[i] = this.userModel.modelImpedance(this.parameters, this.omegas[i]);
/*      */       }
/*      */     }
/*  357 */     this.magnitudesZ = new double[this.numberOfFrequencies];
/*  358 */     this.phasesRadZ = new double[this.numberOfFrequencies];
/*  359 */     this.phasesDegZ = new double[this.numberOfFrequencies];
/*  360 */     this.realZ = new double[this.numberOfFrequencies];
/*  361 */     this.imagZ = new double[this.numberOfFrequencies];
/*  362 */     this.magnitudesV = new double[this.numberOfFrequencies];
/*  363 */     this.phasesRadV = new double[this.numberOfFrequencies];
/*  364 */     this.phasesDegV = new double[this.numberOfFrequencies];
/*  365 */     this.realV = new double[this.numberOfFrequencies];
/*  366 */     this.imagV = new double[this.numberOfFrequencies];
/*  367 */     this.voltages = Complex.oneDarray(this.numberOfFrequencies);
/*  368 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  369 */       this.magnitudesZ[i] = Complex.abs(this.impedances[i]);
/*  370 */       this.phasesRadZ[i] = Complex.arg(this.impedances[i]);
/*  371 */       this.phasesDegZ[i] = Math.toDegrees(this.phasesRadZ[i]);
/*  372 */       this.realZ[i] = this.impedances[i].getReal();
/*  373 */       this.imagZ[i] = this.impedances[i].getImag();
/*  374 */       if ((this.voltageSet) && (this.referenceSet)) {
/*  375 */         this.voltages[i] = this.appliedVoltage.times(this.impedances[i].over(this.impedances[i].plus(this.referenceImpedance)));
/*  376 */         this.magnitudesV[i] = Complex.abs(this.voltages[i]);
/*  377 */         this.phasesRadV[i] = Complex.arg(this.voltages[i]);
/*  378 */         this.phasesDegV[i] = Math.toDegrees(this.phasesRadV[i]);
/*  379 */         this.realV[i] = this.voltages[i].getReal();
/*  380 */         this.imagV[i] = this.voltages[i].getImag();
/*      */       }
/*      */     }
/*  383 */     this.impedancesSet = true;
/*  384 */     return this.impedances;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<Object> getSimulationResultsAsArrayList(int nPoints)
/*      */   {
/*  393 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*      */     
/*  396 */     if (nPoints > this.numberOfFrequencies) nPoints = this.numberOfFrequencies;
/*  397 */     int increment = (int)Math.round(this.numberOfFrequencies / nPoints);
/*  398 */     int[] points = new int[nPoints];
/*  399 */     points[0] = 0;
/*  400 */     for (int i = 1; i < nPoints; i++) points[i] = (points[(i - 1)] + increment);
/*  401 */     if (points[(nPoints - 1)] != this.numberOfFrequencies - 1) { points[(nPoints - 1)] = (this.numberOfFrequencies - 1);
/*      */     }
/*      */     
/*  404 */     ArrayList<Object> selectedData = new ArrayList();
/*      */     
/*  406 */     Complex[] imp = Complex.oneDarray(nPoints);
/*  407 */     for (int i = 0; i < nPoints; i++) imp[i] = this.impedances[points[i]];
/*  408 */     selectedData.add(Complex.copy(imp));
/*      */     
/*  410 */     double[] hold = new double[nPoints];
/*  411 */     for (int i = 0; i < nPoints; i++) hold[i] = this.realZ[points[i]];
/*  412 */     selectedData.add((double[])hold.clone());
/*      */     
/*  414 */     for (int i = 0; i < nPoints; i++) hold[i] = this.imagZ[points[i]];
/*  415 */     selectedData.add((double[])hold.clone());
/*      */     
/*  417 */     for (int i = 0; i < nPoints; i++) hold[i] = this.magnitudesZ[points[i]];
/*  418 */     selectedData.add((double[])hold.clone());
/*      */     
/*  420 */     for (int i = 0; i < nPoints; i++) hold[i] = this.phasesDegZ[points[i]];
/*  421 */     selectedData.add((double[])hold.clone());
/*      */     
/*  423 */     for (int i = 0; i < nPoints; i++) hold[i] = this.phasesRadZ[points[i]];
/*  424 */     selectedData.add((double[])hold.clone());
/*      */     
/*  426 */     for (int i = 0; i < nPoints; i++) hold[i] = this.frequencies[points[i]];
/*  427 */     selectedData.add((double[])hold.clone());
/*      */     
/*  429 */     for (int i = 0; i < nPoints; i++) hold[i] = this.log10frequencies[points[i]];
/*  430 */     selectedData.add((double[])hold.clone());
/*      */     
/*  432 */     for (int i = 0; i < nPoints; i++) hold[i] = this.omegas[points[i]];
/*  433 */     selectedData.add((double[])hold.clone());
/*      */     
/*  435 */     if ((this.voltageSet) && (this.referenceSet)) {
/*  436 */       selectedData.add(new Double(this.appliedVoltage.getReal()));
/*      */       
/*  438 */       selectedData.add(this.referenceImpedance);
/*      */       
/*  440 */       for (int i = 0; i < nPoints; i++) imp[i] = this.voltages[points[i]];
/*  441 */       selectedData.add(Complex.copy(imp));
/*      */       
/*  443 */       for (int i = 0; i < nPoints; i++) hold[i] = this.realV[points[i]];
/*  444 */       selectedData.add((double[])hold.clone());
/*      */       
/*  446 */       for (int i = 0; i < nPoints; i++) hold[i] = this.imagV[points[i]];
/*  447 */       selectedData.add((double[])hold.clone());
/*      */       
/*  449 */       for (int i = 0; i < nPoints; i++) hold[i] = this.magnitudesV[points[i]];
/*  450 */       selectedData.add((double[])hold.clone());
/*      */       
/*  452 */       for (int i = 0; i < nPoints; i++) hold[i] = this.phasesDegV[points[i]];
/*  453 */       selectedData.add((double[])hold.clone());
/*      */       
/*  455 */       for (int i = 0; i < nPoints; i++) hold[i] = this.phasesRadV[points[i]];
/*  456 */       selectedData.add((double[])hold.clone());
/*      */     }
/*      */     else {
/*  459 */       for (int i = 0; i < 8; i++) { selectedData.add(null);
/*      */       }
/*      */     }
/*  462 */     return selectedData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector<Object> getSimulationResultsAsVector(int nPoints)
/*      */   {
/*  473 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*      */     
/*  476 */     if (nPoints > this.numberOfFrequencies) nPoints = this.numberOfFrequencies;
/*  477 */     int increment = (int)Math.round(this.numberOfFrequencies / nPoints);
/*  478 */     int[] points = new int[nPoints];
/*  479 */     points[0] = 0;
/*  480 */     for (int i = 1; i < nPoints; i++) points[i] = (points[(i - 1)] + increment);
/*  481 */     if (points[(nPoints - 1)] != this.numberOfFrequencies - 1) { points[(nPoints - 1)] = (this.numberOfFrequencies - 1);
/*      */     }
/*      */     
/*  484 */     Vector<Object> vec = new Vector();
/*      */     
/*  486 */     Complex[] imp = Complex.oneDarray(nPoints);
/*  487 */     for (int i = 0; i < nPoints; i++) imp[i] = this.impedances[points[i]];
/*  488 */     vec.addElement(Complex.copy(imp));
/*      */     
/*  490 */     double[] hold = new double[nPoints];
/*  491 */     for (int i = 0; i < nPoints; i++) hold[i] = this.realZ[points[i]];
/*  492 */     vec.addElement((double[])hold.clone());
/*      */     
/*  494 */     for (int i = 0; i < nPoints; i++) hold[i] = this.imagZ[points[i]];
/*  495 */     vec.addElement((double[])hold.clone());
/*      */     
/*  497 */     for (int i = 0; i < nPoints; i++) hold[i] = this.magnitudesZ[points[i]];
/*  498 */     vec.addElement((double[])hold.clone());
/*      */     
/*  500 */     for (int i = 0; i < nPoints; i++) hold[i] = this.phasesDegZ[points[i]];
/*  501 */     vec.addElement((double[])hold.clone());
/*      */     
/*  503 */     for (int i = 0; i < nPoints; i++) hold[i] = this.phasesRadZ[points[i]];
/*  504 */     vec.addElement((double[])hold.clone());
/*      */     
/*  506 */     for (int i = 0; i < nPoints; i++) hold[i] = this.frequencies[points[i]];
/*  507 */     vec.addElement((double[])hold.clone());
/*      */     
/*  509 */     for (int i = 0; i < nPoints; i++) hold[i] = this.log10frequencies[points[i]];
/*  510 */     vec.addElement((double[])hold.clone());
/*      */     
/*  512 */     for (int i = 0; i < nPoints; i++) hold[i] = this.omegas[points[i]];
/*  513 */     vec.addElement((double[])hold.clone());
/*      */     
/*  515 */     if ((this.voltageSet) && (this.referenceSet)) {
/*  516 */       vec.addElement(new Double(this.appliedVoltage.getReal()));
/*      */       
/*  518 */       vec.addElement(this.referenceImpedance);
/*      */       
/*  520 */       for (int i = 0; i < nPoints; i++) imp[i] = this.voltages[points[i]];
/*  521 */       vec.addElement(Complex.copy(imp));
/*      */       
/*  523 */       for (int i = 0; i < nPoints; i++) hold[i] = this.realV[points[i]];
/*  524 */       vec.addElement((double[])hold.clone());
/*      */       
/*  526 */       for (int i = 0; i < nPoints; i++) hold[i] = this.imagV[points[i]];
/*  527 */       vec.addElement((double[])hold.clone());
/*      */       
/*  529 */       for (int i = 0; i < nPoints; i++) hold[i] = this.magnitudesV[points[i]];
/*  530 */       vec.addElement((double[])hold.clone());
/*      */       
/*  532 */       for (int i = 0; i < nPoints; i++) hold[i] = this.phasesDegV[points[i]];
/*  533 */       vec.addElement((double[])hold.clone());
/*      */       
/*  535 */       for (int i = 0; i < nPoints; i++) hold[i] = this.phasesRadV[points[i]];
/*  536 */       vec.addElement((double[])hold.clone());
/*      */     }
/*      */     else {
/*  539 */       for (int i = 0; i < 8; i++) { vec.addElement(null);
/*      */       }
/*      */     }
/*  542 */     return vec;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector<Object> getSimulationResults(int nPoints)
/*      */   {
/*  550 */     return getSimulationResultsAsVector(nPoints);
/*      */   }
/*      */   
/*      */   public void plotImpedanceMagnitudes()
/*      */   {
/*  555 */     plotImpedanceMagnitudeVersusFrequency();
/*      */   }
/*      */   
/*      */   public void plotImpedanceMagnitudeVersusFrequency()
/*      */   {
/*  560 */     String[] dAndT = dateAndTime();
/*  561 */     String graphTitle1 = "ImpedSpecSimulation program:  Impedance Magnitude versus Frequency   [" + dAndT[0] + "    " + dAndT[1] + "]";
/*  562 */     String graphTitle2 = this.simulationTitle;
/*  563 */     if (this.logOrLinear) {
/*  564 */       impedanceMagnitudeVersusLogFrequencyPlot(graphTitle1, graphTitle2);
/*      */     }
/*      */     else {
/*  567 */       impedanceMagnitudeVersusFrequencyPlot(graphTitle1, graphTitle2);
/*      */     }
/*      */   }
/*      */   
/*      */   public void plotMagnitudeVersusFrequency()
/*      */   {
/*  573 */     plotImpedanceMagnitudeVersusFrequency();
/*      */   }
/*      */   
/*      */ 
/*      */   private void impedanceMagnitudeVersusLogFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  579 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  581 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  582 */     data[0] = this.log10frequencies;
/*  583 */     data[1] = this.magnitudesZ;
/*  584 */     PlotGraph pg = new PlotGraph(data);
/*  585 */     pg.setLine(3);
/*  586 */     pg.setPoint(0);
/*  587 */     pg.setGraphTitle(graphTitle1);
/*  588 */     pg.setGraphTitle2(graphTitle2);
/*  589 */     pg.setXaxisLegend("Log10[Frequency / Hz]");
/*  590 */     pg.setYaxisLegend("Impedance Magnitude");
/*  591 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   private void impedanceMagnitudeVersusFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  597 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  599 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  600 */     data[0] = this.frequencies;
/*  601 */     data[1] = this.magnitudesZ;
/*  602 */     PlotGraph pg = new PlotGraph(data);
/*  603 */     pg.setLine(3);
/*  604 */     pg.setPoint(0);
/*  605 */     pg.setGraphTitle(graphTitle1);
/*  606 */     pg.setGraphTitle2(graphTitle2);
/*  607 */     pg.setXaxisLegend("Frequency");
/*  608 */     pg.setXaxisUnitsName("Hz");
/*  609 */     pg.setYaxisLegend("Impedance Magnitude");
/*  610 */     pg.plot();
/*      */   }
/*      */   
/*      */   public void plotImpedancePhases()
/*      */   {
/*  615 */     plotImpedancePhaseVersusFrequency();
/*      */   }
/*      */   
/*      */   public void plotImpedancePhaseVersusFrequency()
/*      */   {
/*  620 */     String[] dAndT = dateAndTime();
/*  621 */     String graphTitle1 = "ImpedSpecSimulation program:  Impedance Phase versus Frequency   [" + dAndT[0] + "    " + dAndT[1] + "]";
/*  622 */     String graphTitle2 = this.simulationTitle;
/*  623 */     if (this.logOrLinear) {
/*  624 */       impedancePhaseVersusLogFrequencyPlot(graphTitle1, graphTitle2);
/*      */     }
/*      */     else {
/*  627 */       impedancePhaseVersusFrequencyPlot(graphTitle1, graphTitle2);
/*      */     }
/*      */   }
/*      */   
/*      */   public void plotPhaseVersusFrequency()
/*      */   {
/*  633 */     plotImpedancePhaseVersusFrequency();
/*      */   }
/*      */   
/*      */ 
/*      */   private void impedancePhaseVersusLogFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  639 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  641 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  642 */     data[0] = this.log10frequencies;
/*  643 */     data[1] = this.phasesDegZ;
/*  644 */     PlotGraph pg = new PlotGraph(data);
/*  645 */     pg.setLine(3);
/*  646 */     pg.setPoint(0);
/*  647 */     pg.setGraphTitle(graphTitle1);
/*  648 */     pg.setGraphTitle2(graphTitle2);
/*  649 */     pg.setXaxisLegend("Log10[Frequency / Hz]");
/*  650 */     pg.setYaxisLegend("Impedance Phase");
/*  651 */     pg.setYaxisUnitsName("degrees");
/*  652 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   private void impedancePhaseVersusFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  658 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  660 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  661 */     data[0] = this.frequencies;
/*  662 */     data[1] = this.phasesDegZ;
/*  663 */     PlotGraph pg = new PlotGraph(data);
/*  664 */     pg.setLine(3);
/*  665 */     pg.setPoint(0);
/*  666 */     pg.setGraphTitle(graphTitle1);
/*  667 */     pg.setGraphTitle2(graphTitle2);
/*  668 */     pg.setXaxisLegend("Frequency");
/*  669 */     pg.setXaxisUnitsName("Hz");
/*  670 */     pg.setYaxisLegend("Impedance Phase");
/*  671 */     pg.setYaxisUnitsName("degrees");
/*  672 */     pg.plot();
/*      */   }
/*      */   
/*      */   public void plotColeCole()
/*      */   {
/*  677 */     String[] dAndT = dateAndTime();
/*  678 */     String graphTitle1 = "ImpedSpecSimulation program:  Cole - Cole plot   [" + dAndT[0] + "    " + dAndT[1] + "]";
/*  679 */     String graphTitle2 = this.simulationTitle;
/*  680 */     coleColePlot(graphTitle1, graphTitle2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void coleColePlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  687 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  689 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  690 */     for (int i = 0; i < this.numberOfFrequencies; i++) {
/*  691 */       data[0][i] = this.realZ[(this.numberOfFrequencies - i - 1)];
/*  692 */       data[1][i] = (-this.imagZ[(this.numberOfFrequencies - i - 1)]);
/*      */     }
/*  694 */     PlotGraph pg = new PlotGraph(data);
/*  695 */     pg.setLine(3);
/*  696 */     pg.setPoint(0);
/*  697 */     pg.setGraphTitle(graphTitle1);
/*  698 */     pg.setGraphTitle2(graphTitle2);
/*  699 */     pg.setXaxisLegend("Real[Impedance / ohms]");
/*  700 */     pg.setYaxisLegend("-Imag[Impedance / ohms]");
/*  701 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotVoltageMagnitudes()
/*      */   {
/*  707 */     plotVoltageMagnitudeVersusFrequency();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotVoltageMagnitudeVersusFrequency()
/*      */   {
/*  713 */     if ((this.voltageSet) && (this.referenceSet)) {
/*  714 */       String[] dAndT = dateAndTime();
/*  715 */       String graphTitle1 = "ImpedSpecSimulation program:  Voltage Magnitude versus Frequency   [" + dAndT[0] + "    " + dAndT[1] + "]";
/*  716 */       String graphTitle2 = this.simulationTitle;
/*  717 */       if (this.logOrLinear) {
/*  718 */         voltageMagnitudeVersusLogFrequencyPlot(graphTitle1, graphTitle2);
/*      */       }
/*      */       else {
/*  721 */         voltageMagnitudeVersusFrequencyPlot(graphTitle1, graphTitle2);
/*      */       }
/*      */     }
/*      */     else {
/*  725 */       System.out.println("A Voltage phase plot cannot be displayed, either no applied");
/*  726 */       System.out.println("voltage and/or reference impedance has been entered");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void voltageMagnitudeVersusLogFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  735 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  737 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  738 */     data[0] = this.log10frequencies;
/*  739 */     data[1] = this.magnitudesV;
/*  740 */     PlotGraph pg = new PlotGraph(data);
/*  741 */     pg.setLine(3);
/*  742 */     pg.setPoint(0);
/*  743 */     pg.setGraphTitle(graphTitle1);
/*  744 */     pg.setGraphTitle2(graphTitle2);
/*  745 */     pg.setXaxisLegend("Log10[Frequency / Hz]");
/*  746 */     pg.setYaxisLegend("Voltage Magnitude");
/*  747 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   private void voltageMagnitudeVersusFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  753 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  755 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  756 */     data[0] = this.frequencies;
/*  757 */     data[1] = this.magnitudesV;
/*  758 */     PlotGraph pg = new PlotGraph(data);
/*  759 */     pg.setLine(3);
/*  760 */     pg.setPoint(0);
/*  761 */     pg.setGraphTitle(graphTitle1);
/*  762 */     pg.setGraphTitle2(graphTitle2);
/*  763 */     pg.setXaxisLegend("Frequency");
/*  764 */     pg.setXaxisUnitsName("Hz");
/*  765 */     pg.setYaxisLegend("Voltage Magnitude");
/*  766 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotVoltagePhases()
/*      */   {
/*  772 */     plotVoltagePhaseVersusFrequency();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotVoltagePhaseVersusFrequency()
/*      */   {
/*  778 */     if ((this.voltageSet) && (this.referenceSet)) {
/*  779 */       String[] dAndT = dateAndTime();
/*  780 */       String graphTitle1 = "ImpedSpecSimulation program:  Voltage Phase versus Frequency   [" + dAndT[0] + "    " + dAndT[1] + "]";
/*  781 */       String graphTitle2 = this.simulationTitle;
/*  782 */       if (this.logOrLinear) {
/*  783 */         voltagePhaseVersusLogFrequencyPlot(graphTitle1, graphTitle2);
/*      */       }
/*      */       else {
/*  786 */         voltagePhaseVersusFrequencyPlot(graphTitle1, graphTitle2);
/*      */       }
/*      */     }
/*      */     else {
/*  790 */       System.out.println("A Voltage phase plot cannot be displayed, either no applied");
/*  791 */       System.out.println("voltage and/or reference impedance has been entered");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void voltagePhaseVersusLogFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  799 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  801 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  802 */     data[0] = this.log10frequencies;
/*  803 */     data[1] = this.phasesDegV;
/*  804 */     PlotGraph pg = new PlotGraph(data);
/*  805 */     pg.setLine(3);
/*  806 */     pg.setPoint(0);
/*  807 */     pg.setGraphTitle(graphTitle1);
/*  808 */     pg.setGraphTitle2(graphTitle2);
/*  809 */     pg.setXaxisLegend("Log10[Frequency / Hz]");
/*  810 */     pg.setYaxisLegend("Voltage Phase");
/*  811 */     pg.setYaxisUnitsName("degrees");
/*  812 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   private void voltagePhaseVersusFrequencyPlot(String graphTitle1, String graphTitle2)
/*      */   {
/*  818 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  820 */     double[][] data = new double[2][this.numberOfFrequencies];
/*  821 */     data[0] = this.frequencies;
/*  822 */     data[1] = this.phasesDegV;
/*  823 */     PlotGraph pg = new PlotGraph(data);
/*  824 */     pg.setLine(3);
/*  825 */     pg.setPoint(0);
/*  826 */     pg.setGraphTitle(graphTitle1);
/*  827 */     pg.setGraphTitle2(graphTitle2);
/*  828 */     pg.setXaxisLegend("Frequency");
/*  829 */     pg.setXaxisUnitsName("Hz");
/*  830 */     pg.setYaxisLegend("Voltage Phase");
/*  831 */     pg.setYaxisUnitsName("degrees");
/*  832 */     pg.plot();
/*      */   }
/*      */   
/*      */   public String[] dateAndTime()
/*      */   {
/*  837 */     Date d = new Date();
/*  838 */     String[] ret = new String[2];
/*  839 */     ret[0] = DateFormat.getDateInstance().format(d);
/*  840 */     ret[1] = DateFormat.getTimeInstance().format(d);
/*  841 */     return ret;
/*      */   }
/*      */   
/*      */   public void printToTextFile(int nPoints)
/*      */   {
/*  846 */     String fileName = "ImpedSpecSimulationOutput.txt";
/*  847 */     this.fileType = true;
/*  848 */     printToTextFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */   public void print(int nPoints)
/*      */   {
/*  853 */     String fileName = "ImpedSpecSimulationOutput.txt";
/*  854 */     this.fileType = true;
/*  855 */     printToTextFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */   public void print(String fileName, int nPoints)
/*      */   {
/*  860 */     printToTextFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */ 
/*      */   public void printToTextFile(String fileName, int nPoints)
/*      */   {
/*  866 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/*  868 */     int field = 10;
/*  869 */     int trunc = 4;
/*      */     
/*      */ 
/*  872 */     fileName = fileName.trim();
/*  873 */     int dotPosition = fileName.indexOf('.');
/*  874 */     if (dotPosition == -1) { fileName = fileName + ".txt";
/*      */     }
/*      */     
/*  877 */     if (nPoints > this.numberOfFrequencies) nPoints = this.numberOfFrequencies;
/*  878 */     int increment = (int)Math.round(this.numberOfFrequencies / nPoints);
/*  879 */     int[] points = new int[nPoints];
/*  880 */     points[0] = 0;
/*  881 */     for (int i = 1; i < nPoints; i++) points[i] = (points[(i - 1)] + increment);
/*  882 */     if (points[(nPoints - 1)] != this.numberOfFrequencies - 1) { points[(nPoints - 1)] = (this.numberOfFrequencies - 1);
/*      */     }
/*      */     
/*  885 */     FileOutput fout = null;
/*  886 */     if (this.fileType) {
/*  887 */       fout = new FileOutput(fileName, 'n');
/*      */     }
/*      */     else {
/*  890 */       fout = new FileOutput(fileName);
/*      */     }
/*      */     
/*      */ 
/*  894 */     fout.println("ImpedSpecSimulation Program Output File:  " + this.simulationTitle);
/*  895 */     fout.dateAndTimeln(fileName);
/*  896 */     fout.println();
/*  897 */     if (this.modelSet) {
/*  898 */       fout.println("Circuit - model number " + this.modelNumber);
/*      */     }
/*      */     else {
/*  901 */       fout.println("Circuit supplied by the user");
/*      */     }
/*  903 */     fout.println();
/*      */     
/*      */ 
/*  906 */     fout.println("Circuit Parameters");
/*  907 */     fout.printtab("Parameters");
/*  908 */     fout.println("Value (SI unit)");
/*  909 */     for (int i = 0; i < this.numberOfParameters; i++) {
/*  910 */       fout.printtab(this.modelParameterSymbols[i], field);
/*  911 */       fout.println(this.parameters[i]);
/*      */     }
/*  913 */     fout.println();
/*      */     
/*      */ 
/*  916 */     field = 14;
/*  917 */     fout.println("Frequecy - Impedance data");
/*      */     
/*  919 */     fout.print("Frequency", field);
/*  920 */     fout.print("Magnitude", field);
/*  921 */     fout.print("Phase", field);
/*  922 */     fout.print("Phase", field);
/*  923 */     fout.print("Real[Z]", field);
/*  924 */     fout.print("Imag[Z]", field);
/*  925 */     fout.print("Log10(freq)", field);
/*  926 */     fout.println("Radial frequency");
/*      */     
/*  928 */     fout.print("/Hz [freq]", field);
/*  929 */     fout.print("  ", field);
/*  930 */     fout.print("/degrees", field);
/*  931 */     fout.print("/radians", field);
/*  932 */     fout.print("/ohms", field);
/*  933 */     fout.print("/ohms", field);
/*  934 */     fout.print("  ", field);
/*  935 */     fout.println("/radians");
/*      */     
/*  937 */     for (int i = 0; i < nPoints; i++) {
/*  938 */       fout.print(Fmath.truncate(this.frequencies[points[i]], trunc), field);
/*  939 */       fout.print(Fmath.truncate(this.magnitudesZ[points[i]], trunc), field);
/*  940 */       fout.print(Fmath.truncate(this.phasesDegZ[points[i]], trunc), field);
/*  941 */       fout.print(Fmath.truncate(this.phasesRadZ[points[i]], trunc), field);
/*  942 */       fout.print(Fmath.truncate(this.realZ[points[i]], trunc), field);
/*  943 */       fout.print(Fmath.truncate(this.imagZ[points[i]], trunc), field);
/*  944 */       fout.print(Fmath.truncate(this.log10frequencies[points[i]], trunc), field);
/*  945 */       fout.println(Fmath.truncate(this.omegas[points[i]], trunc));
/*      */     }
/*  947 */     fout.println();
/*      */     
/*  949 */     if ((this.voltageSet) && (this.referenceSet)) {
/*  950 */       fout.println("Aplied voltage: " + this.appliedVoltage.getReal() + " volts");
/*  951 */       fout.println();
/*      */       
/*  953 */       fout.println("Reference impedance: " + this.referenceImpedance + " ohms");
/*  954 */       fout.println();
/*      */       
/*      */ 
/*  957 */       field = 14;
/*  958 */       fout.println("Frequecy - Voltage data");
/*      */       
/*  960 */       fout.print("Frequency", field);
/*  961 */       fout.print("Magnitude", field);
/*  962 */       fout.print("Phase", field);
/*  963 */       fout.print("Phase", field);
/*  964 */       fout.print("Real[V]", field);
/*  965 */       fout.print("Imag[V]", field);
/*  966 */       fout.print("Log10(freq)", field);
/*  967 */       fout.println("Radial frequency");
/*      */       
/*  969 */       fout.print("/Hz [freq]", field);
/*  970 */       fout.print("  ", field);
/*  971 */       fout.print("/degrees", field);
/*  972 */       fout.print("/radians", field);
/*  973 */       fout.print("/volts", field);
/*  974 */       fout.print("/volts", field);
/*  975 */       fout.print("  ", field);
/*  976 */       fout.println("/radians");
/*      */       
/*  978 */       for (int i = 0; i < nPoints; i++) {
/*  979 */         fout.print(Fmath.truncate(this.frequencies[points[i]], trunc), field);
/*  980 */         fout.print(Fmath.truncate(this.magnitudesV[points[i]], trunc), field);
/*  981 */         fout.print(Fmath.truncate(this.phasesDegV[points[i]], trunc), field);
/*  982 */         fout.print(Fmath.truncate(this.phasesRadV[points[i]], trunc), field);
/*  983 */         fout.print(Fmath.truncate(this.realV[points[i]], trunc), field);
/*  984 */         fout.print(Fmath.truncate(this.imagV[points[i]], trunc), field);
/*  985 */         fout.print(Fmath.truncate(this.log10frequencies[points[i]], trunc), field);
/*  986 */         fout.println(Fmath.truncate(this.omegas[points[i]], trunc));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  991 */     fout.close();
/*      */   }
/*      */   
/*      */   public void printToExcelFile(int nPoints)
/*      */   {
/*  996 */     String fileName = "ImpedSpecSimulationOutput.xls";
/*  997 */     this.fileType = true;
/*  998 */     printToExcelFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */   public void printForExcel(int nPoints)
/*      */   {
/* 1003 */     String fileName = "ImpedSpecSimulationOutput.xls";
/* 1004 */     this.fileType = true;
/* 1005 */     printToExcelFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */   public void printForExcel(String fileName, int nPoints)
/*      */   {
/* 1010 */     printToExcelFile(fileName, nPoints);
/*      */   }
/*      */   
/*      */ 
/*      */   public void printToExcelFile(String fileName, int nPoints)
/*      */   {
/* 1016 */     if (!this.impedancesSet) { calculateImpedances();
/*      */     }
/* 1018 */     int field = 10;
/* 1019 */     int trunc = 4;
/*      */     
/*      */ 
/* 1022 */     fileName = fileName.trim();
/* 1023 */     int dotPosition = fileName.indexOf('.');
/* 1024 */     if (dotPosition == -1) {
/* 1025 */       fileName = fileName + ".xls";
/*      */     }
/*      */     else {
/* 1028 */       fileName = fileName.substring(0, dotPosition) + ".xls";
/*      */     }
/*      */     
/*      */ 
/* 1032 */     if (nPoints > this.numberOfFrequencies) nPoints = this.numberOfFrequencies;
/* 1033 */     int increment = (int)Math.round(this.numberOfFrequencies / nPoints);
/* 1034 */     int[] points = new int[nPoints];
/* 1035 */     points[0] = 0;
/* 1036 */     for (int i = 1; i < nPoints; i++) points[i] = (points[(i - 1)] + increment);
/* 1037 */     if (points[(nPoints - 1)] != this.numberOfFrequencies - 1) { points[(nPoints - 1)] = (this.numberOfFrequencies - 1);
/*      */     }
/*      */     
/* 1040 */     FileOutput fout = null;
/* 1041 */     if (this.fileType) {
/* 1042 */       fout = new FileOutput(fileName, 'n');
/*      */     }
/*      */     else {
/* 1045 */       fout = new FileOutput(fileName);
/*      */     }
/*      */     
/*      */ 
/* 1049 */     fout.println("ImpedSpecSimulation Program Output File:  " + this.simulationTitle);
/* 1050 */     fout.dateAndTimeln(fileName);
/* 1051 */     fout.println();
/* 1052 */     if (this.modelSet) {
/* 1053 */       fout.println("Circuit - model number " + this.modelNumber);
/*      */     }
/*      */     else {
/* 1056 */       fout.println("Circuit supplied by the user");
/*      */     }
/* 1058 */     fout.println();
/*      */     
/*      */ 
/* 1061 */     fout.println("Circuit Parameters");
/* 1062 */     fout.printtab("Parameters");
/* 1063 */     fout.println("Value (SI unit)");
/* 1064 */     for (int i = 0; i < this.numberOfParameters; i++) {
/* 1065 */       fout.printtab(this.modelParameterSymbols[i], field);
/* 1066 */       fout.println(this.parameters[i]);
/*      */     }
/* 1068 */     fout.println();
/*      */     
/*      */ 
/* 1071 */     field = 10;
/* 1072 */     fout.println("Frequecy - Impedance data");
/*      */     
/* 1074 */     fout.printtab("Frequency", field);
/* 1075 */     fout.printtab("Magnitude", field);
/* 1076 */     fout.printtab("Phase", field);
/* 1077 */     fout.printtab("Phase", field);
/* 1078 */     fout.printtab("Real[Z]", field);
/* 1079 */     fout.printtab("Imag[Z]", field);
/* 1080 */     fout.printtab("Log10(freq)", field);
/* 1081 */     fout.println("Radial frequency");
/*      */     
/* 1083 */     fout.printtab("/Hz [freq]", field);
/* 1084 */     fout.printtab("  ", field);
/* 1085 */     fout.printtab("/degrees", field);
/* 1086 */     fout.printtab("/radians", field);
/* 1087 */     fout.printtab("/ohms", field);
/* 1088 */     fout.printtab("/ohms", field);
/* 1089 */     fout.printtab("  ", field);
/* 1090 */     fout.println("/radians");
/*      */     
/* 1092 */     for (int i = 0; i < nPoints; i++) {
/* 1093 */       fout.printtab(Fmath.truncate(this.frequencies[points[i]], trunc), field);
/* 1094 */       fout.printtab(Fmath.truncate(this.magnitudesZ[points[i]], trunc), field);
/* 1095 */       fout.printtab(Fmath.truncate(this.phasesDegZ[points[i]], trunc), field);
/* 1096 */       fout.printtab(Fmath.truncate(this.phasesRadZ[points[i]], trunc), field);
/* 1097 */       fout.printtab(Fmath.truncate(this.realZ[points[i]], trunc), field);
/* 1098 */       fout.printtab(Fmath.truncate(this.imagZ[points[i]], trunc), field);
/* 1099 */       fout.printtab(Fmath.truncate(this.log10frequencies[points[i]], trunc), field);
/* 1100 */       fout.println(Fmath.truncate(this.omegas[points[i]], trunc));
/*      */     }
/* 1102 */     fout.println();
/*      */     
/* 1104 */     if ((this.voltageSet) && (this.referenceSet)) {
/* 1105 */       fout.println("Aplied voltage: " + this.appliedVoltage.getReal() + " volts");
/* 1106 */       fout.println();
/*      */       
/* 1108 */       fout.println("Reference impedance: " + this.referenceImpedance + " ohms");
/* 1109 */       fout.println();
/*      */       
/*      */ 
/* 1112 */       field = 14;
/* 1113 */       fout.println("Frequecy - Voltage data");
/*      */       
/* 1115 */       fout.printtab("Frequency", field);
/* 1116 */       fout.printtab("Magnitude", field);
/* 1117 */       fout.printtab("Phase", field);
/* 1118 */       fout.printtab("Phase", field);
/* 1119 */       fout.printtab("Real[V]", field);
/* 1120 */       fout.printtab("Imag[V]", field);
/* 1121 */       fout.printtab("Log10(freq)", field);
/* 1122 */       fout.println("Radial frequency");
/*      */       
/* 1124 */       fout.printtab("/Hz [freq]", field);
/* 1125 */       fout.printtab("  ", field);
/* 1126 */       fout.printtab("/degrees", field);
/* 1127 */       fout.printtab("/radians", field);
/* 1128 */       fout.printtab("/volts", field);
/* 1129 */       fout.printtab("/volts", field);
/* 1130 */       fout.printtab("  ", field);
/* 1131 */       fout.println("/radians");
/*      */       
/* 1133 */       for (int i = 0; i < nPoints; i++) {
/* 1134 */         fout.printtab(Fmath.truncate(this.frequencies[points[i]], trunc), field);
/* 1135 */         fout.printtab(Fmath.truncate(this.magnitudesV[points[i]], trunc), field);
/* 1136 */         fout.printtab(Fmath.truncate(this.phasesDegV[points[i]], trunc), field);
/* 1137 */         fout.printtab(Fmath.truncate(this.phasesRadV[points[i]], trunc), field);
/* 1138 */         fout.printtab(Fmath.truncate(this.realV[points[i]], trunc), field);
/* 1139 */         fout.printtab(Fmath.truncate(this.imagV[points[i]], trunc), field);
/* 1140 */         fout.printtab(Fmath.truncate(this.log10frequencies[points[i]], trunc), field);
/* 1141 */         fout.println(Fmath.truncate(this.omegas[points[i]], trunc));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1146 */     fout.close();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ImpedSpecSimulation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */