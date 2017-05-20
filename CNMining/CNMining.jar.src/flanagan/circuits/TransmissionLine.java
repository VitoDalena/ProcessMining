/*      */ package flanagan.circuits;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexMatrix;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.plot.PlotGraph;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TransmissionLine
/*      */ {
/*   43 */   protected String title = "Transmission Line";
/*      */   
/*   45 */   protected double distributedResistance = 0.0D;
/*   46 */   protected double distributedConductance = 0.0D;
/*   47 */   protected double distributedCapacitance = 0.0D;
/*   48 */   protected double distributedInductance = 0.0D;
/*   49 */   protected Complex distributedImpedance = null;
/*   50 */   protected Complex distributedAdmittance = null;
/*      */   
/*   52 */   protected Complex loadImpedance = Complex.plusInfinity();
/*      */   
/*      */ 
/*   55 */   protected double lineLength = -1.0D;
/*   56 */   protected double segmentLength = -1.0D;
/*      */   
/*   58 */   protected double frequency = 0.0D;
/*   59 */   protected double omega = 0.0D;
/*      */   
/*   61 */   protected Complex inputVoltage = null;
/*   62 */   protected Complex inputCurrent = null;
/*      */   
/*   64 */   protected Complex outputVoltage = null;
/*   65 */   protected Complex outputCurrent = null;
/*      */   
/*   67 */   protected double idealWavelength = 0.0D;
/*   68 */   protected double generalWavelength = 0.0D;
/*   69 */   protected double lowLossWavelength = 0.0D;
/*      */   
/*   71 */   protected double idealPhaseVelocity = 0.0D;
/*   72 */   protected double generalPhaseVelocity = 0.0D;
/*   73 */   protected double lowLossPhaseVelocity = 0.0D;
/*      */   
/*   75 */   protected double idealGroupVelocity = 0.0D;
/*   76 */   protected double generalGroupVelocity = 0.0D;
/*   77 */   protected double lowLossGroupVelocity = 0.0D;
/*   78 */   protected double delta = 0.001D;
/*      */   
/*   80 */   protected double idealAttenuationConstant = 0.0D;
/*   81 */   protected double generalAttenuationConstant = 0.0D;
/*   82 */   protected double lowLossAttenuationConstant = 0.0D;
/*      */   
/*   84 */   protected double idealPhaseConstant = 0.0D;
/*   85 */   protected double generalPhaseConstant = 0.0D;
/*   86 */   protected double lowLossPhaseConstant = 0.0D;
/*      */   
/*   88 */   protected Complex idealPropagationConstant = null;
/*   89 */   protected Complex generalPropagationConstant = null;
/*   90 */   protected Complex lowLossPropagationConstant = null;
/*      */   
/*   92 */   protected Complex idealCharacteristicImpedance = null;
/*   93 */   protected double idealRealCharacteristicImpedance = 0.0D;
/*   94 */   protected Complex generalCharacteristicImpedance = null;
/*   95 */   protected Complex lowLossCharacteristicImpedance = null;
/*      */   
/*   97 */   protected Complex idealInputImpedance = null;
/*   98 */   protected Complex generalInputImpedance = null;
/*   99 */   protected Complex lowLossInputImpedance = null;
/*      */   
/*  101 */   protected Complex idealShortedLineImpedance = null;
/*  102 */   protected Complex generalShortedLineImpedance = null;
/*  103 */   protected Complex lowLossShortedLineImpedance = null;
/*      */   
/*  105 */   protected Complex idealOpenLineImpedance = null;
/*  106 */   protected Complex generalOpenLineImpedance = null;
/*  107 */   protected Complex lowLossOpenLineImpedance = null;
/*      */   
/*  109 */   protected Complex idealQuarterWaveLineImpedance = null;
/*  110 */   protected Complex generalQuarterWaveLineImpedance = null;
/*  111 */   protected Complex lowLossQuarterWaveLineImpedance = null;
/*      */   
/*  113 */   protected Complex idealHalfWaveLineImpedance = null;
/*  114 */   protected Complex generalHalfWaveLineImpedance = null;
/*  115 */   protected Complex lowLossHalfWaveLineImpedance = null;
/*      */   
/*  117 */   protected Complex idealRefectionCoefficient = null;
/*  118 */   protected Complex generalRefectionCoefficient = null;
/*  119 */   protected Complex lowLossRefectionCoefficient = null;
/*      */   
/*  121 */   protected double idealStandingWaveRatio = 0.0D;
/*  122 */   protected double generalStandingWaveRatio = 0.0D;
/*  123 */   protected double lowLossStandingWaveRatio = 0.0D;
/*      */   
/*  125 */   protected ComplexMatrix idealABCDmatrix = null;
/*  126 */   protected ComplexMatrix generalABCDmatrix = null;
/*  127 */   protected ComplexMatrix lowLossABCDmatrix = null;
/*      */   
/*  129 */   protected int numberOfPoints = 1000;
/*      */   
/*      */ 
/*      */ 
/*      */   public TransmissionLine() {}
/*      */   
/*      */ 
/*      */ 
/*      */   public TransmissionLine(String title)
/*      */   {
/*  139 */     this.title = title;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTitle(String title)
/*      */   {
/*  145 */     this.title = title;
/*      */   }
/*      */   
/*      */   public String getTitle()
/*      */   {
/*  150 */     return this.title;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFrequency(double frequency)
/*      */   {
/*  156 */     this.frequency = frequency;
/*  157 */     this.omega = (this.frequency * 2.0D * 3.141592653589793D);
/*      */   }
/*      */   
/*      */   public double getFrequency()
/*      */   {
/*  162 */     return this.frequency;
/*      */   }
/*      */   
/*      */   public double getRadialFrequency()
/*      */   {
/*  167 */     return this.omega;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLoadImpedance(double impedance)
/*      */   {
/*  173 */     this.loadImpedance = new Complex(impedance, 0.0D);
/*      */   }
/*      */   
/*      */   public void setLoadImpedance(Complex impedance)
/*      */   {
/*  178 */     this.loadImpedance = impedance;
/*      */   }
/*      */   
/*      */   public Complex getLoadImpedance()
/*      */   {
/*  183 */     return this.loadImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLineLength(double length)
/*      */   {
/*  189 */     this.lineLength = length;
/*      */   }
/*      */   
/*      */   public double getLineLength()
/*      */   {
/*  194 */     return this.lineLength;
/*      */   }
/*      */   
/*      */   public void setSegmentLength(double length)
/*      */   {
/*  199 */     this.segmentLength = length;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setOutputVoltage(Phasor voltage)
/*      */   {
/*  205 */     this.outputVoltage = Phasor.toRectangular(voltage);
/*      */   }
/*      */   
/*      */   public void setOutputVoltage(Complex voltage)
/*      */   {
/*  210 */     this.outputVoltage = voltage;
/*      */   }
/*      */   
/*      */   public void setOutputVoltage(double magnitude, double phase)
/*      */   {
/*  215 */     this.outputVoltage = new Complex();
/*  216 */     this.outputVoltage.polar(magnitude, phase);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setOutputCurrent(Phasor current)
/*      */   {
/*  222 */     this.outputCurrent = Phasor.toRectangular(current);
/*      */   }
/*      */   
/*      */   public void setOutputCurrent(Complex current)
/*      */   {
/*  227 */     this.outputCurrent = current;
/*      */   }
/*      */   
/*      */   public void setOutputCurrent(double magnitude, double phase)
/*      */   {
/*  232 */     this.outputCurrent = new Complex();
/*  233 */     this.outputCurrent.polar(magnitude, phase);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDistributedResistance(double resistance)
/*      */   {
/*  239 */     this.distributedResistance = resistance;
/*      */   }
/*      */   
/*      */   public void setDistributedInductance(double inductance)
/*      */   {
/*  244 */     this.distributedInductance = inductance;
/*      */   }
/*      */   
/*      */   public void setDistributedCapacitance(double capacitance)
/*      */   {
/*  249 */     this.distributedCapacitance = capacitance;
/*      */   }
/*      */   
/*      */   public void setDistributedConductance(double conductance)
/*      */   {
/*  254 */     this.distributedConductance = conductance;
/*      */   }
/*      */   
/*      */   public double getDistributedResistance()
/*      */   {
/*  259 */     return this.distributedResistance;
/*      */   }
/*      */   
/*      */   public double getDistributedInductance()
/*      */   {
/*  264 */     return this.distributedInductance;
/*      */   }
/*      */   
/*      */   public double getDistributedCapacitance()
/*      */   {
/*  269 */     return this.distributedCapacitance;
/*      */   }
/*      */   
/*      */   public double getDistributedConductance()
/*      */   {
/*  274 */     return this.distributedConductance;
/*      */   }
/*      */   
/*      */   public Complex getDistributedImpedance()
/*      */   {
/*  279 */     this.distributedImpedance = new Complex(this.distributedResistance, this.distributedInductance * this.omega);
/*  280 */     return this.distributedImpedance;
/*      */   }
/*      */   
/*      */   public Complex getDistributedAdmittance()
/*      */   {
/*  285 */     this.distributedAdmittance = new Complex(this.distributedConductance, this.distributedCapacitance * this.omega);
/*  286 */     return this.distributedAdmittance;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getWavelength()
/*      */   {
/*  292 */     this.generalWavelength = (getPhaseVelocity() / this.frequency);
/*  293 */     return this.generalWavelength;
/*      */   }
/*      */   
/*      */   public double getIdealWavelength()
/*      */   {
/*  298 */     this.idealWavelength = (getIdealPhaseVelocity() / this.frequency);
/*  299 */     return this.idealWavelength;
/*      */   }
/*      */   
/*      */   public double getLowLossWavelength()
/*      */   {
/*  304 */     this.lowLossWavelength = (getLowLossPhaseVelocity() / this.frequency);
/*  305 */     return this.lowLossWavelength;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getPhaseVelocity()
/*      */   {
/*  311 */     this.generalPhaseVelocity = (this.omega / getPhaseConstant());
/*  312 */     return this.generalPhaseVelocity;
/*      */   }
/*      */   
/*      */   public double getIdealPhaseVelocity()
/*      */   {
/*  317 */     this.idealPhaseVelocity = (this.omega / getIdealPhaseConstant());
/*  318 */     return this.idealPhaseVelocity;
/*      */   }
/*      */   
/*      */   public double getLowLossPhaseVelocity()
/*      */   {
/*  323 */     this.lowLossPhaseVelocity = (this.omega / getLowLossPhaseConstant());
/*  324 */     return this.lowLossPhaseVelocity;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getGroupVelocity()
/*      */   {
/*  330 */     if ((this.distributedResistance == 0.0D) && (this.distributedConductance == 0.0D)) {
/*  331 */       this.generalPhaseVelocity = (1.0D / Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*      */     }
/*      */     else {
/*  334 */       double omegaStored = this.omega;
/*  335 */       this.omega = (omegaStored * (1.0D - this.delta));
/*  336 */       double betaLower = getPhaseConstant();
/*  337 */       this.omega = (omegaStored * (1.0D + this.delta));
/*  338 */       double betaUpper = getPhaseConstant();
/*  339 */       this.omega = omegaStored;
/*  340 */       this.generalPhaseVelocity = (2.0D * this.omega * this.delta / (betaUpper - betaLower));
/*      */     }
/*  342 */     return this.generalGroupVelocity;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDelta(double delta)
/*      */   {
/*  348 */     this.delta = delta;
/*      */   }
/*      */   
/*      */   public double getIdealGroupVelocity()
/*      */   {
/*  353 */     this.idealGroupVelocity = (1.0D / Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*  354 */     return this.idealGroupVelocity;
/*      */   }
/*      */   
/*      */   public double getLowLossGroupVelocity()
/*      */   {
/*  359 */     double temp0 = this.omega * this.omega;
/*  360 */     double temp1 = Math.sqrt(this.distributedInductance * this.distributedCapacitance);
/*  361 */     double temp2 = this.distributedResistance * this.distributedConductance / (4.0D * temp0 * this.distributedInductance * this.distributedCapacitance);
/*  362 */     double temp3 = this.distributedConductance * this.distributedConductance / (8.0D * temp0 * this.distributedCapacitance * this.distributedCapacitance);
/*  363 */     double temp4 = this.distributedResistance * this.distributedResistance / (8.0D * temp0 * this.distributedInductance * this.distributedInductance);
/*  364 */     this.lowLossPhaseConstant = (1.0D / (temp1 * (1.0D + temp2 - temp3 - temp4)));
/*  365 */     return this.lowLossGroupVelocity;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getAttenuationConstant()
/*      */   {
/*  371 */     if ((this.distributedResistance == 0.0D) && (this.distributedConductance == 0.0D)) {
/*  372 */       this.generalAttenuationConstant = 0.0D;
/*      */     }
/*      */     else {
/*  375 */       this.generalAttenuationConstant = Complex.sqrt(getDistributedImpedance().times(getDistributedAdmittance())).getReal();
/*      */     }
/*  377 */     return this.generalAttenuationConstant;
/*      */   }
/*      */   
/*      */   public double getLowLossAttenuationConstant()
/*      */   {
/*  382 */     double temp1 = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
/*  383 */     double temp2 = this.distributedResistance / (2.0D * temp1);
/*  384 */     double temp3 = this.distributedConductance * temp1 / 2.0D;
/*  385 */     this.lowLossAttenuationConstant = (temp2 + temp3);
/*  386 */     return this.lowLossAttenuationConstant;
/*      */   }
/*      */   
/*      */   public double getIdealAttenuationConstant()
/*      */   {
/*  391 */     this.idealAttenuationConstant = 0.0D;
/*  392 */     return this.idealAttenuationConstant;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getPhaseConstant()
/*      */   {
/*  398 */     if ((this.distributedResistance == 0.0D) && (this.distributedConductance == 0.0D)) {
/*  399 */       this.generalPhaseConstant = (this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*      */     }
/*      */     else {
/*  402 */       this.generalPhaseConstant = Complex.sqrt(getDistributedImpedance().times(getDistributedAdmittance())).getImag();
/*      */     }
/*  404 */     return this.generalPhaseConstant;
/*      */   }
/*      */   
/*      */   public double getLowLossPhaseConstant()
/*      */   {
/*  409 */     double temp0 = this.omega * this.omega;
/*  410 */     double temp1 = this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance);
/*  411 */     double temp2 = this.distributedResistance * this.distributedConductance / (4.0D * temp0 * this.distributedInductance * this.distributedCapacitance);
/*  412 */     double temp3 = this.distributedConductance * this.distributedConductance / (8.0D * temp0 * this.distributedCapacitance * this.distributedCapacitance);
/*  413 */     double temp4 = this.distributedResistance * this.distributedResistance / (8.0D * temp0 * this.distributedInductance * this.distributedInductance);
/*  414 */     this.lowLossPhaseConstant = (temp1 * (1.0D - temp2 + temp3 + temp4));
/*  415 */     return this.lowLossPhaseConstant;
/*      */   }
/*      */   
/*      */   public double getIdealPhaseConstant()
/*      */   {
/*  420 */     this.idealPhaseConstant = (this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*  421 */     return this.idealPhaseConstant;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getPropagationConstant()
/*      */   {
/*  427 */     if ((this.distributedResistance == 0.0D) && (this.distributedConductance == 0.0D)) {
/*  428 */       this.generalPropagationConstant = new Complex(0.0D, this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*      */     }
/*      */     else {
/*  431 */       this.generalPropagationConstant = Complex.sqrt(getDistributedImpedance().times(getDistributedAdmittance()));
/*      */     }
/*  433 */     return this.generalPropagationConstant;
/*      */   }
/*      */   
/*      */   public Complex getLowLossPropagationConstant()
/*      */   {
/*  438 */     this.lowLossPropagationConstant = new Complex(getLowLossAttenuationConstant(), getLowLossPhaseConstant());
/*  439 */     return this.lowLossPropagationConstant;
/*      */   }
/*      */   
/*      */   public Complex getIdealPropagationConstant()
/*      */   {
/*  444 */     this.idealPropagationConstant = new Complex(0.0D, this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
/*  445 */     return this.idealPropagationConstant;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getCharacteristicImpedance()
/*      */   {
/*  451 */     this.generalCharacteristicImpedance = Complex.sqrt(getDistributedImpedance().over(getDistributedAdmittance()));
/*  452 */     return this.generalCharacteristicImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossCharacteristicImpedance()
/*      */   {
/*  457 */     double temp0 = this.omega * this.omega;
/*  458 */     double temp1 = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
/*  459 */     double temp2 = this.distributedResistance * this.distributedResistance / (8.0D * temp0 * this.distributedInductance * this.distributedInductance);
/*  460 */     double temp3 = this.distributedConductance * this.distributedConductance / (8.0D * temp0 * this.distributedCapacitance * this.distributedCapacitance);
/*  461 */     double temp4 = this.distributedResistance * this.distributedConductance / (4.0D * temp0 * this.distributedInductance * this.distributedCapacitance);
/*  462 */     double temp5 = this.distributedConductance / (2.0D * this.omega * this.distributedCapacitance);
/*  463 */     double temp6 = this.distributedResistance / (2.0D * this.omega * this.distributedInductance);
/*  464 */     this.lowLossCharacteristicImpedance = new Complex(temp1 * (1.0D + temp2 - temp3 + temp4), temp1 * (temp5 - temp6));
/*  465 */     return this.lowLossCharacteristicImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealCharacteristicImpedance()
/*      */   {
/*  470 */     this.idealRealCharacteristicImpedance = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
/*  471 */     this.idealCharacteristicImpedance = new Complex(this.idealRealCharacteristicImpedance, 0.0D);
/*  472 */     return this.idealCharacteristicImpedance;
/*      */   }
/*      */   
/*      */   public double getIdealCharacteristicImpedanceAsReal()
/*      */   {
/*  477 */     this.idealRealCharacteristicImpedance = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
/*  478 */     this.idealCharacteristicImpedance = new Complex(this.idealRealCharacteristicImpedance, 0.0D);
/*  479 */     return this.idealRealCharacteristicImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getInputImpedance()
/*      */   {
/*  485 */     Complex gamma = getPropagationConstant();
/*  486 */     Complex zed0 = getCharacteristicImpedance();
/*  487 */     Complex temp0 = Complex.cosh(gamma.times(this.lineLength));
/*  488 */     Complex temp1 = Complex.sinh(gamma.times(this.lineLength));
/*  489 */     Complex temp2 = temp0.times(this.loadImpedance);
/*  490 */     Complex temp3 = temp1.times(zed0);
/*  491 */     Complex temp4 = temp0.times(zed0);
/*  492 */     Complex temp5 = temp1.times(this.loadImpedance);
/*  493 */     Complex temp6 = temp2.plus(temp3).over(temp4.plus(temp5));
/*  494 */     this.generalInputImpedance = zed0.times(temp6);
/*  495 */     return this.generalInputImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossInputImpedance()
/*      */   {
/*  500 */     Complex gamma = getLowLossPropagationConstant();
/*  501 */     Complex zed0 = getLowLossCharacteristicImpedance();
/*  502 */     Complex temp0 = Complex.cosh(gamma.times(this.lineLength));
/*  503 */     Complex temp1 = Complex.sinh(gamma.times(this.lineLength));
/*  504 */     Complex temp2 = temp0.times(this.loadImpedance);
/*  505 */     Complex temp3 = temp1.times(zed0);
/*  506 */     Complex temp4 = temp0.times(zed0);
/*  507 */     Complex temp5 = temp1.times(this.loadImpedance);
/*  508 */     Complex temp6 = temp2.plus(temp3).over(temp4.plus(temp5));
/*  509 */     this.lowLossInputImpedance = zed0.times(temp6);
/*  510 */     return this.lowLossInputImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealInputImpedance()
/*      */   {
/*  515 */     double beta = getIdealPhaseConstant();
/*  516 */     double zed0 = getIdealCharacteristicImpedanceAsReal();
/*  517 */     double temp0 = Math.cos(beta * this.lineLength);
/*  518 */     double temp1 = Math.sin(beta * this.lineLength);
/*  519 */     Complex temp2 = new Complex(0.0D, temp1 * zed0).plus(this.loadImpedance.times(temp0));
/*  520 */     Complex temp3 = new Complex(temp0 * zed0, 0.0D).plus(Complex.plusJay().times(this.loadImpedance.times(temp1)));
/*  521 */     Complex temp4 = temp2.over(temp3);
/*  522 */     this.idealInputImpedance = temp4.times(zed0);
/*  523 */     return this.idealInputImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getShortedLineImpedance()
/*      */   {
/*  529 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  530 */     this.generalShortedLineImpedance = getCharacteristicImpedance().times(Complex.tanh(getPropagationConstant().times(this.lineLength)));
/*  531 */     return this.generalShortedLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossShortedLineImpedance()
/*      */   {
/*  536 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  537 */     double temp0 = getLowLossAttenuationConstant() * this.lineLength;
/*  538 */     double temp1 = Math.cos(getLowLossPhaseConstant() * this.lineLength);
/*  539 */     double temp2 = Math.sin(getLowLossPhaseConstant() * this.lineLength);
/*  540 */     Complex temp3 = new Complex(temp0 * temp1, temp2);
/*  541 */     Complex temp4 = new Complex(temp1, temp0 * temp2);
/*  542 */     this.lowLossShortedLineImpedance = temp3.over(temp4);
/*  543 */     return this.lowLossShortedLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealShortedLineImpedance()
/*      */   {
/*  548 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  549 */     this.idealShortedLineImpedance = new Complex(0.0D, getIdealCharacteristicImpedanceAsReal() * Math.tan(getIdealPhaseConstant() * this.lineLength));
/*  550 */     return this.idealShortedLineImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getOpenLineImpedance()
/*      */   {
/*  556 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  557 */     this.generalShortedLineImpedance = getCharacteristicImpedance().times(Complex.coth(getPropagationConstant().times(this.lineLength)));
/*  558 */     return this.generalShortedLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossOpenLineImpedance()
/*      */   {
/*  563 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  564 */     double temp0 = getLowLossAttenuationConstant() * this.lineLength;
/*  565 */     double temp1 = Math.cos(getLowLossPhaseConstant() * this.lineLength);
/*  566 */     double temp2 = Math.sin(getLowLossPhaseConstant() * this.lineLength);
/*  567 */     Complex temp3 = new Complex(temp1, temp0 * temp2);
/*  568 */     Complex temp4 = new Complex(temp0 * temp1, temp2);
/*  569 */     this.lowLossShortedLineImpedance = temp3.over(temp4);
/*  570 */     return this.lowLossShortedLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealOpenLineImpedance()
/*      */   {
/*  575 */     if (this.lineLength == -1.0D) throw new IllegalArgumentException("No line length as been entered");
/*  576 */     this.idealShortedLineImpedance = new Complex(0.0D, -getIdealCharacteristicImpedanceAsReal() * Fmath.cot(getIdealPhaseConstant() * this.lineLength));
/*  577 */     return this.idealShortedLineImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getQuarterWaveLineImpedance()
/*      */   {
/*  583 */     Complex alpha = new Complex(getAttenuationConstant(), 0.0D);
/*  584 */     Complex zed0 = getCharacteristicImpedance();
/*  585 */     Complex temp0 = Complex.sinh(alpha.times(this.lineLength));
/*  586 */     Complex temp1 = Complex.cosh(alpha.times(this.lineLength));
/*  587 */     Complex temp2 = temp0.times(this.loadImpedance);
/*  588 */     Complex temp3 = temp1.times(zed0);
/*  589 */     Complex temp4 = temp0.times(zed0);
/*  590 */     Complex temp5 = temp1.times(this.loadImpedance);
/*  591 */     Complex temp6 = temp2.plus(temp3).over(temp4.plus(temp5));
/*  592 */     this.generalQuarterWaveLineImpedance = zed0.times(temp6);
/*  593 */     return this.generalQuarterWaveLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossQuarterWaveLineImpedance()
/*      */   {
/*  598 */     Complex alpha = new Complex(getLowLossAttenuationConstant(), 0.0D);
/*  599 */     Complex zed0 = getLowLossCharacteristicImpedance();
/*  600 */     Complex temp0 = alpha.times(this.lineLength);
/*  601 */     Complex temp1 = zed0.plus(this.loadImpedance.times(temp0));
/*  602 */     Complex temp2 = this.loadImpedance.plus(zed0.times(temp0));
/*  603 */     Complex temp3 = temp1.over(temp2);
/*  604 */     this.lowLossQuarterWaveLineImpedance = zed0.times(temp3);
/*  605 */     return this.lowLossQuarterWaveLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealQuarterWaveLineImpedance()
/*      */   {
/*  610 */     Complex zed02 = new Complex(Fmath.square(getIdealCharacteristicImpedanceAsReal()), 0.0D);
/*  611 */     this.idealQuarterWaveLineImpedance = zed02.over(this.loadImpedance);
/*  612 */     return this.idealQuarterWaveLineImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getHalfWaveLineImpedance()
/*      */   {
/*  618 */     Complex alpha = new Complex(getAttenuationConstant(), 0.0D);
/*  619 */     Complex zed0 = getCharacteristicImpedance();
/*  620 */     Complex temp0 = Complex.cosh(alpha.times(this.lineLength));
/*  621 */     Complex temp1 = Complex.sinh(alpha.times(this.lineLength));
/*  622 */     Complex temp2 = temp0.times(this.loadImpedance);
/*  623 */     Complex temp3 = temp1.times(zed0);
/*  624 */     Complex temp4 = temp0.times(zed0);
/*  625 */     Complex temp5 = temp1.times(this.loadImpedance);
/*  626 */     Complex temp6 = temp2.plus(temp3).over(temp4.plus(temp5));
/*  627 */     this.generalHalfWaveLineImpedance = zed0.times(temp6);
/*  628 */     return this.generalHalfWaveLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getLowLossHalfWaveLineImpedance()
/*      */   {
/*  633 */     Complex alpha = new Complex(getLowLossAttenuationConstant(), 0.0D);
/*  634 */     Complex zed0 = getLowLossCharacteristicImpedance();
/*  635 */     Complex temp0 = alpha.times(this.lineLength);
/*  636 */     Complex temp1 = this.loadImpedance.plus(zed0.times(temp0));
/*  637 */     Complex temp2 = zed0.plus(this.loadImpedance.times(temp0));
/*  638 */     Complex temp3 = temp1.over(temp2);
/*  639 */     this.lowLossHalfWaveLineImpedance = zed0.times(temp3);
/*  640 */     return this.lowLossHalfWaveLineImpedance;
/*      */   }
/*      */   
/*      */   public Complex getIdealHalfWaveLineImpedance()
/*      */   {
/*  645 */     this.idealHalfWaveLineImpedance = this.loadImpedance;
/*  646 */     return this.idealHalfWaveLineImpedance;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getRefectionCoefficient()
/*      */   {
/*  652 */     Complex complex1 = this.loadImpedance.minus(getCharacteristicImpedance());
/*  653 */     Complex complex2 = this.loadImpedance.plus(getCharacteristicImpedance());
/*  654 */     this.generalRefectionCoefficient = complex1.over(complex2);
/*  655 */     return this.generalRefectionCoefficient;
/*      */   }
/*      */   
/*      */   public Complex getLowLossRefectionCoefficient()
/*      */   {
/*  660 */     Complex complex1 = this.loadImpedance.minus(getLowLossCharacteristicImpedance());
/*  661 */     Complex complex2 = this.loadImpedance.plus(getLowLossCharacteristicImpedance());
/*  662 */     this.lowLossRefectionCoefficient = complex1.over(complex2);
/*  663 */     return this.lowLossRefectionCoefficient;
/*      */   }
/*      */   
/*      */   public Complex getIdealRefectionCoefficient()
/*      */   {
/*  668 */     Complex complex1 = this.loadImpedance.minus(getIdealCharacteristicImpedance());
/*  669 */     Complex complex2 = this.loadImpedance.plus(getIdealCharacteristicImpedance());
/*  670 */     this.idealRefectionCoefficient = complex1.over(complex2);
/*  671 */     return this.idealRefectionCoefficient;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getStandingWaveRatio()
/*      */   {
/*  677 */     double rho = getRefectionCoefficient().abs();
/*  678 */     this.generalStandingWaveRatio = ((1.0D + rho) / (1.0D - rho));
/*  679 */     return this.generalStandingWaveRatio;
/*      */   }
/*      */   
/*      */   public double getLowLossStandingWaveRatio()
/*      */   {
/*  684 */     double rho = getLowLossRefectionCoefficient().abs();
/*  685 */     this.lowLossStandingWaveRatio = ((1.0D + rho) / (1.0D - rho));
/*  686 */     return this.lowLossStandingWaveRatio;
/*      */   }
/*      */   
/*      */   public double getIdealStandingWaveRatio()
/*      */   {
/*  691 */     double rho = getIdealRefectionCoefficient().abs();
/*  692 */     this.idealStandingWaveRatio = ((1.0D + rho) / (1.0D - rho));
/*  693 */     return this.idealStandingWaveRatio;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix getABCDmatrix()
/*      */   {
/*  699 */     if (this.segmentLength == -1.0D) throw new IllegalArgumentException("No distance along the line as been entered");
/*  700 */     if ((this.distributedResistance == 0.0D) && (this.distributedConductance == 0.0D)) {
/*  701 */       this.generalABCDmatrix = getIdealABCDmatrix();
/*      */     }
/*      */     else {
/*  704 */       this.generalABCDmatrix = new ComplexMatrix(2, 2);
/*  705 */       Complex gammal = getPropagationConstant().times(this.segmentLength);
/*  706 */       Complex zed0 = getCharacteristicImpedance();
/*  707 */       this.generalABCDmatrix.setElement(0, 0, Complex.cosh(gammal));
/*  708 */       this.generalABCDmatrix.setElement(0, 1, Complex.sinh(gammal).times(zed0));
/*  709 */       this.generalABCDmatrix.setElement(1, 0, Complex.sinh(gammal).over(zed0));
/*  710 */       this.generalABCDmatrix.setElement(1, 1, Complex.cosh(gammal));
/*      */     }
/*  712 */     return this.generalABCDmatrix;
/*      */   }
/*      */   
/*      */   public ComplexMatrix getIdealABCDmatrix()
/*      */   {
/*  717 */     if (this.segmentLength == -1.0D) { throw new IllegalArgumentException("No distance along the line as been entered");
/*      */     }
/*  719 */     this.idealABCDmatrix = new ComplexMatrix(2, 2);
/*  720 */     double betal = getIdealPhaseConstant() * this.segmentLength;
/*  721 */     double zed0 = getIdealCharacteristicImpedanceAsReal();
/*  722 */     this.idealABCDmatrix.setElement(0, 0, new Complex(Math.cos(betal), 0.0D));
/*  723 */     this.idealABCDmatrix.setElement(0, 1, new Complex(0.0D, Math.sin(betal) * zed0));
/*  724 */     this.idealABCDmatrix.setElement(1, 0, new Complex(0.0D, Math.sin(betal) / zed0));
/*  725 */     this.idealABCDmatrix.setElement(1, 1, new Complex(Math.cos(betal), 0.0D));
/*      */     
/*  727 */     return this.idealABCDmatrix;
/*      */   }
/*      */   
/*      */   public ComplexMatrix getLowLossABCDmatrix()
/*      */   {
/*  732 */     if (this.segmentLength == -1.0D) { throw new IllegalArgumentException("No distance along the line as been entered");
/*      */     }
/*  734 */     this.lowLossABCDmatrix = new ComplexMatrix(2, 2);
/*  735 */     Complex gammal = getLowLossPropagationConstant().times(this.segmentLength);
/*  736 */     Complex zed0 = getLowLossCharacteristicImpedance();
/*  737 */     this.lowLossABCDmatrix.setElement(0, 0, Complex.cosh(gammal));
/*  738 */     this.lowLossABCDmatrix.setElement(0, 1, Complex.sinh(gammal).times(zed0));
/*  739 */     this.lowLossABCDmatrix.setElement(1, 0, Complex.sinh(gammal).over(zed0));
/*  740 */     this.lowLossABCDmatrix.setElement(1, 1, Complex.cosh(gammal));
/*  741 */     return this.lowLossABCDmatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Complex[] voltageAndCurrentAsComplex(double segLen)
/*      */   {
/*  748 */     this.segmentLength = segLen;
/*  749 */     return voltageAndCurrentAsComplex();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex[] voltageAndCurrentAsComplex()
/*      */   {
/*  757 */     Complex[] outputVector = { this.outputVoltage, this.outputCurrent };
/*  758 */     ComplexMatrix abcdMatrix = getABCDmatrix();
/*  759 */     Complex[] inputVector = abcdMatrix.solveLinearSet(outputVector);
/*  760 */     this.inputVoltage = inputVector[0];
/*  761 */     this.inputCurrent = inputVector[1];
/*  762 */     return inputVector;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Phasor[] voltageAndCurrentAsPhasor(double segLen)
/*      */   {
/*  769 */     this.segmentLength = segLen;
/*  770 */     Complex[] outputVector = { this.outputVoltage, this.outputCurrent };
/*  771 */     ComplexMatrix abcdMatrix = getABCDmatrix();
/*  772 */     Complex[] inputVector = abcdMatrix.solveLinearSet(outputVector);
/*  773 */     this.inputVoltage = inputVector[0];
/*  774 */     this.inputCurrent = inputVector[1];
/*  775 */     Phasor[] input = { Phasor.toPhasor(this.inputVoltage), Phasor.toPhasor(this.inputCurrent) };
/*  776 */     return input;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor[] voltageAndCurrentAsPhasor()
/*      */   {
/*  785 */     Complex[] outputVector = { this.outputVoltage, this.outputCurrent };
/*  786 */     ComplexMatrix abcdMatrix = getABCDmatrix();
/*  787 */     Complex[] inputVector = abcdMatrix.solveLinearSet(outputVector);
/*  788 */     this.inputVoltage = inputVector[0];
/*  789 */     this.inputCurrent = inputVector[1];
/*  790 */     Phasor[] input = { Phasor.toPhasor(this.inputVoltage), Phasor.toPhasor(this.inputCurrent) };
/*  791 */     return input;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] voltageAndCurrentAsReal()
/*      */   {
/*  798 */     Complex[] outputVector = { this.outputVoltage, this.outputCurrent };
/*  799 */     ComplexMatrix abcdMatrix = getABCDmatrix();
/*  800 */     Complex[] inputVector = abcdMatrix.solveLinearSet(outputVector);
/*      */     
/*  802 */     double[] input = { inputVector[0].abs() * Math.cos(inputVector[0].arg()), inputVector[1].abs() * Math.cos(inputVector[1].arg()) };
/*  803 */     return input;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] voltageAndCurrentAsReal(double segLen)
/*      */   {
/*  811 */     this.segmentLength = segLen;
/*  812 */     return voltageAndCurrentAsReal();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] voltageAndCurrentAsMagnitudeAndPhase()
/*      */   {
/*  819 */     Complex[] outputVector = { this.outputVoltage, this.outputCurrent };
/*  820 */     ComplexMatrix abcdMatrix = getABCDmatrix();
/*  821 */     Complex[] inputVector = abcdMatrix.solveLinearSet(outputVector);
/*      */     
/*  823 */     double[] input = { inputVector[0].abs(), inputVector[0].arg(), inputVector[1].abs(), inputVector[1].arg() };
/*  824 */     return input;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] voltageAndCurrentAsAsMagnitudeAndPhase(double segLen)
/*      */   {
/*  832 */     this.segmentLength = segLen;
/*  833 */     return voltageAndCurrentAsMagnitudeAndPhase();
/*      */   }
/*      */   
/*      */ 
/*      */   public void plotVandI()
/*      */   {
/*  839 */     double[][] data = PlotGraph.data(4, this.numberOfPoints);
/*      */     
/*  841 */     double increment = this.segmentLength / (this.numberOfPoints - 1);
/*      */     
/*  843 */     data[0][0] = 0.0D;
/*  844 */     data[2][0] = 0.0D;
/*  845 */     for (int i = 1; i < this.numberOfPoints; i++) {
/*  846 */       data[0][i] = (data[0][(i - 1)] + increment);
/*  847 */       data[2][i] = (data[2][(i - 1)] + increment);
/*      */     }
/*  849 */     for (int i = 0; i < this.numberOfPoints; i++) {
/*  850 */       double[] output = voltageAndCurrentAsReal(data[0][i]);
/*  851 */       data[1][i] = output[0];
/*  852 */       data[3][i] = output[1];
/*      */     }
/*      */     
/*      */ 
/*  856 */     data[4][0] = 0.0D;
/*  857 */     data[6][0] = 0.0D;
/*  858 */     data[4][1] = data[0][(this.numberOfPoints / 2)];
/*  859 */     data[6][1] = data[0][(this.numberOfPoints / 2)];
/*  860 */     data[4][2] = data[0][(this.numberOfPoints - 1)];
/*  861 */     data[6][2] = data[0][(this.numberOfPoints - 1)];
/*      */     
/*  863 */     data[5][0] = data[1][0];
/*  864 */     data[7][0] = data[3][0];
/*  865 */     data[5][1] = data[1][(this.numberOfPoints / 2)];
/*  866 */     data[7][1] = data[3][(this.numberOfPoints / 2)];
/*  867 */     data[5][2] = data[1][(this.numberOfPoints - 1)];
/*  868 */     data[7][2] = data[3][(this.numberOfPoints - 1)];
/*      */     
/*      */ 
/*  871 */     PlotGraph pg = new PlotGraph(data);
/*  872 */     int[] lineOpt = { 3, 3, 0, 0 };
/*  873 */     pg.setLine(lineOpt);
/*  874 */     int[] pointOpt = { 0, 0, 1, 2 };
/*  875 */     pg.setPoint(pointOpt);
/*  876 */     pg.setXaxisLegend("distance / metres");
/*  877 */     pg.setYaxisLegend("Voltage / V and Current / A");
/*  878 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */   public TransmissionLine copy()
/*      */   {
/*  884 */     if (this == null) {
/*  885 */       return null;
/*      */     }
/*      */     
/*  888 */     TransmissionLine tl = new TransmissionLine();
/*      */     
/*  890 */     tl.title = this.title;
/*  891 */     tl.distributedResistance = this.distributedResistance;
/*  892 */     tl.distributedConductance = this.distributedConductance;
/*  893 */     tl.distributedCapacitance = this.distributedCapacitance;
/*  894 */     tl.distributedInductance = this.distributedInductance;
/*      */     
/*  896 */     tl.distributedImpedance = this.distributedImpedance.copy();
/*  897 */     tl.distributedAdmittance = this.distributedAdmittance.copy();
/*  898 */     tl.loadImpedance = this.loadImpedance.copy();
/*      */     
/*  900 */     tl.lineLength = this.lineLength;
/*  901 */     tl.segmentLength = this.segmentLength;
/*  902 */     tl.frequency = this.frequency;
/*  903 */     tl.segmentLength = this.segmentLength;
/*  904 */     tl.omega = this.omega;
/*      */     
/*  906 */     tl.inputVoltage = this.inputVoltage.copy();
/*  907 */     tl.inputCurrent = this.inputCurrent.copy();
/*  908 */     tl.outputVoltage = this.outputVoltage.copy();
/*  909 */     tl.outputCurrent = this.outputCurrent.copy();
/*      */     
/*  911 */     tl.idealWavelength = this.idealWavelength;
/*  912 */     tl.generalWavelength = this.generalWavelength;
/*  913 */     tl.lowLossWavelength = this.lowLossWavelength;
/*      */     
/*  915 */     tl.idealPhaseVelocity = this.idealPhaseVelocity;
/*  916 */     tl.generalPhaseVelocity = this.generalPhaseVelocity;
/*  917 */     tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*      */     
/*  919 */     tl.idealGroupVelocity = this.idealGroupVelocity;
/*  920 */     tl.generalGroupVelocity = this.generalGroupVelocity;
/*  921 */     tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/*  922 */     tl.delta = this.delta;
/*      */     
/*  924 */     tl.idealAttenuationConstant = this.idealAttenuationConstant;
/*  925 */     tl.generalAttenuationConstant = this.generalAttenuationConstant;
/*  926 */     tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*      */     
/*  928 */     tl.idealPhaseConstant = this.idealPhaseConstant;
/*  929 */     tl.generalPhaseConstant = this.generalPhaseConstant;
/*  930 */     tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*      */     
/*  932 */     tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/*  933 */     tl.loadImpedance = this.loadImpedance.copy();
/*  934 */     tl.loadImpedance = this.loadImpedance.copy();
/*  935 */     tl.loadImpedance = this.loadImpedance.copy();
/*      */     
/*  937 */     tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/*  938 */     tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/*  939 */     tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/*  940 */     tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*      */     
/*  942 */     tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/*  943 */     tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/*  944 */     tl.idealInputImpedance = this.idealInputImpedance.copy();
/*  945 */     tl.generalInputImpedance = this.generalInputImpedance.copy();
/*  946 */     tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*      */     
/*  948 */     tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/*  949 */     tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/*  950 */     tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*      */     
/*  952 */     tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/*  953 */     tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/*  954 */     tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*      */     
/*  956 */     tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/*  957 */     tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/*  958 */     tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*      */     
/*  960 */     tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/*  961 */     tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/*  962 */     tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*      */     
/*  964 */     tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/*  965 */     tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/*  966 */     tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*      */     
/*  968 */     tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/*  969 */     tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/*  970 */     tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*      */     
/*  972 */     tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/*  973 */     tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/*  974 */     tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*      */     
/*  976 */     tl.numberOfPoints = this.numberOfPoints;
/*      */     
/*  978 */     return tl;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  986 */     Object ret = null;
/*      */     
/*  988 */     if (this != null)
/*      */     {
/*  990 */       TransmissionLine tl = new TransmissionLine();
/*      */       
/*  992 */       tl.title = this.title;
/*  993 */       tl.distributedResistance = this.distributedResistance;
/*  994 */       tl.distributedConductance = this.distributedConductance;
/*  995 */       tl.distributedCapacitance = this.distributedCapacitance;
/*  996 */       tl.distributedInductance = this.distributedInductance;
/*      */       
/*  998 */       tl.distributedImpedance = this.distributedImpedance.copy();
/*  999 */       tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 1000 */       tl.loadImpedance = this.loadImpedance.copy();
/*      */       
/* 1002 */       tl.lineLength = this.lineLength;
/* 1003 */       tl.segmentLength = this.segmentLength;
/* 1004 */       tl.frequency = this.frequency;
/* 1005 */       tl.segmentLength = this.segmentLength;
/* 1006 */       tl.omega = this.omega;
/*      */       
/* 1008 */       tl.inputVoltage = this.inputVoltage.copy();
/* 1009 */       tl.inputCurrent = this.inputCurrent.copy();
/* 1010 */       tl.outputVoltage = this.outputVoltage.copy();
/* 1011 */       tl.outputCurrent = this.outputCurrent.copy();
/*      */       
/* 1013 */       tl.idealWavelength = this.idealWavelength;
/* 1014 */       tl.generalWavelength = this.generalWavelength;
/* 1015 */       tl.lowLossWavelength = this.lowLossWavelength;
/*      */       
/* 1017 */       tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 1018 */       tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 1019 */       tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*      */       
/* 1021 */       tl.idealGroupVelocity = this.idealGroupVelocity;
/* 1022 */       tl.generalGroupVelocity = this.generalGroupVelocity;
/* 1023 */       tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 1024 */       tl.delta = this.delta;
/*      */       
/* 1026 */       tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 1027 */       tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 1028 */       tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*      */       
/* 1030 */       tl.idealPhaseConstant = this.idealPhaseConstant;
/* 1031 */       tl.generalPhaseConstant = this.generalPhaseConstant;
/* 1032 */       tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*      */       
/* 1034 */       tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 1035 */       tl.loadImpedance = this.loadImpedance.copy();
/* 1036 */       tl.loadImpedance = this.loadImpedance.copy();
/* 1037 */       tl.loadImpedance = this.loadImpedance.copy();
/*      */       
/* 1039 */       tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 1040 */       tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 1041 */       tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 1042 */       tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*      */       
/* 1044 */       tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 1045 */       tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 1046 */       tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 1047 */       tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 1048 */       tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*      */       
/* 1050 */       tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 1051 */       tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 1052 */       tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*      */       
/* 1054 */       tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 1055 */       tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 1056 */       tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*      */       
/* 1058 */       tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 1059 */       tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 1060 */       tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*      */       
/* 1062 */       tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 1063 */       tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 1064 */       tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*      */       
/* 1066 */       tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 1067 */       tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 1068 */       tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*      */       
/* 1070 */       tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 1071 */       tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 1072 */       tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*      */       
/* 1074 */       tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 1075 */       tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 1076 */       tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*      */       
/* 1078 */       tl.numberOfPoints = this.numberOfPoints;
/*      */       
/* 1080 */       ret = tl;
/*      */     }
/* 1082 */     return ret;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/TransmissionLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */