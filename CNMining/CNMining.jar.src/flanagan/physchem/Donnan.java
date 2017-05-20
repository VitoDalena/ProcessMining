/*      */ package flanagan.physchem;
/*      */ 
/*      */ import flanagan.io.Db;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Minimisation;
/*      */ import flanagan.physprop.IonicRadii;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
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
/*      */ public class Donnan
/*      */ {
/*   49 */   private ArrayList<Object> arrayl = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   60 */   private int numOfIons = 0;
/*   61 */   private int numOfAnions = 0;
/*   62 */   private int numOfCations = 0;
/*   63 */   private String[] ionNames = null;
/*   64 */   private double[] concnA = null;
/*   65 */   private double[] concnB = null;
/*   66 */   private double[] molesT = null;
/*   67 */   private double[] complex = null;
/*   68 */   private double[] excessConcnA = null;
/*   69 */   private double[] excessConcnB = null;
/*   70 */   private double[] excessComplex = null;
/*   71 */   private int[] indexC = null;
/*   72 */   private int nonZeroConcns = 0;
/*   73 */   private double[] assocConsts = null;
/*   74 */   private int[] indexK = null;
/*   75 */   private int nonZeroAssocK = 0;
/*   76 */   private double[] radii = null;
/*   77 */   private boolean radiusType = true;
/*      */   
/*   79 */   private double[] charges = null;
/*   80 */   private double tol = 1.0E-6D;
/*      */   
/*      */ 
/*   83 */   private String ionophore = "ionophore";
/*   84 */   private double ionophoreConcn = 0.0D;
/*   85 */   private double freeIonophoreConcn = 0.0D;
/*   86 */   private double ionophoreRad = 0.0D;
/*   87 */   private boolean ionophoreSet = false;
/*   88 */   private double volumeA = 0.0D;
/*   89 */   private double volumeB = 0.0D;
/*   90 */   private double interfacialArea = 0.0D;
/*   91 */   private boolean volumeSet = false;
/*   92 */   private double epsilonA = 0.0D;
/*   93 */   private double epsilonB = 0.0D;
/*   94 */   private double epsilonSternA = 0.0D;
/*   95 */   private double epsilonSternB = 0.0D;
/*   96 */   private boolean epsilonSet = false;
/*   97 */   private double temp = 298.15D;
/*   98 */   private boolean tempSet = false;
/*   99 */   private double[] deltaMu0 = null;
/*  100 */   private double[] partCoeff = null;
/*  101 */   private double[] partCoeffPot = null;
/*  102 */   private boolean[] indexPC = null;
/*      */   
/*      */ 
/*  105 */   private double donnanPotential = 0.0D;
/*  106 */   private double diffPotentialA = 0.0D;
/*  107 */   private double diffPotentialB = 0.0D;
/*  108 */   private double sternPotential = 0.0D;
/*  109 */   private double estimate = 0.0D;
/*  110 */   private double step = 0.0D;
/*  111 */   private double tolerance = 1.0E-20D;
/*  112 */   private int nMaxIter = 10000;
/*  113 */   private int numIterations = 0;
/*  114 */   private double minimum = 1.0E300D;
/*  115 */   private double sternCap = 0.0D;
/*  116 */   private double diffCapA = 0.0D;
/*  117 */   private double diffCapB = 0.0D;
/*  118 */   private double donnanCap = 0.0D;
/*  119 */   private double sternDeltaA = 0.0D;
/*  120 */   private double sternDeltaB = 0.0D;
/*  121 */   private double chargeValue = 0.0D;
/*  122 */   private boolean chargeSame = true;
/*  123 */   private double interfacialChargeDensity = 0.0D;
/*  124 */   private double interfacialCharge = 0.0D;
/*  125 */   private boolean includeIc = true;
/*      */   
/*  127 */   private double[] ratioA = null;
/*  128 */   private double[] ratioB = null;
/*  129 */   private double[] ratioC = null;
/*  130 */   private double recipKappaA = 0.0D;
/*  131 */   private double recipKappaB = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setHydratedRadii()
/*      */   {
/*  140 */     this.radiusType = true;
/*      */   }
/*      */   
/*      */   public void setBareRadii()
/*      */   {
/*  145 */     this.radiusType = false;
/*      */   }
/*      */   
/*      */   public void ignoreInterfaceCharge()
/*      */   {
/*  150 */     this.includeIc = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void includeInterfaceCharge()
/*      */   {
/*  156 */     this.includeIc = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concnA, double concnB, double assocK, double radius, int charge)
/*      */   {
/*  164 */     this.arrayl.add(ion);
/*  165 */     this.arrayl.add(new Double(concnA));
/*  166 */     this.arrayl.add(new Double(concnB));
/*  167 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  168 */     this.arrayl.add(new Double(assocK));
/*  169 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  170 */     this.arrayl.add(new Double(radius));
/*  171 */     this.arrayl.add(new Integer(charge));
/*  172 */     this.arrayl.add(new Double(-1.0D));
/*  173 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIon(double partCoeff, String ion, double concnA, double concnB, double assocK, double radius, int charge)
/*      */   {
/*  180 */     this.arrayl.add(ion);
/*  181 */     this.arrayl.add(new Double(concnA));
/*  182 */     this.arrayl.add(new Double(concnB));
/*  183 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  184 */     this.arrayl.add(new Double(assocK));
/*  185 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  186 */     this.arrayl.add(new Double(radius));
/*  187 */     this.arrayl.add(new Integer(charge));
/*  188 */     this.arrayl.add(new Double(partCoeff));
/*  189 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concnA, double concnB, double radius, int charge)
/*      */   {
/*  197 */     this.arrayl.add(ion);
/*  198 */     this.arrayl.add(new Double(concnA));
/*  199 */     this.arrayl.add(new Double(concnB));
/*  200 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  201 */     this.arrayl.add(new Double(0.0D));
/*  202 */     this.arrayl.add(new Double(radius));
/*  203 */     this.arrayl.add(new Integer(charge));
/*  204 */     this.arrayl.add(new Double(-1.0D));
/*  205 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(double partCoeff, String ion, double concnA, double concnB, double radius, int charge)
/*      */   {
/*  213 */     this.arrayl.add(ion);
/*  214 */     this.arrayl.add(new Double(concnA));
/*  215 */     this.arrayl.add(new Double(concnB));
/*  216 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  217 */     this.arrayl.add(new Double(0.0D));
/*  218 */     this.arrayl.add(new Double(radius));
/*  219 */     this.arrayl.add(new Integer(charge));
/*  220 */     this.arrayl.add(new Double(partCoeff));
/*  221 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concnA, double concnB, double assocK)
/*      */   {
/*  230 */     IonicRadii ir = new IonicRadii();
/*  231 */     this.arrayl.add(ion);
/*  232 */     this.arrayl.add(new Double(concnA));
/*  233 */     this.arrayl.add(new Double(concnB));
/*  234 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  235 */     this.arrayl.add(new Double(assocK));
/*  236 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  237 */     double rad = 0.0D;
/*  238 */     if (this.radiusType) {
/*  239 */       rad = IonicRadii.hydratedRadius(ion);
/*      */     }
/*      */     else {
/*  242 */       rad = IonicRadii.radius(ion);
/*      */     }
/*      */     
/*  245 */     if (rad == 0.0D) {
/*  246 */       String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  247 */       String mess2 = "Please enter radius in metres\n";
/*  248 */       rad = Db.readDouble(mess1 + mess2);
/*      */     }
/*  250 */     this.arrayl.add(new Double(rad));
/*  251 */     int charg = 0;
/*  252 */     charg = IonicRadii.charge(ion);
/*  253 */     if (charg == 0) {
/*  254 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  255 */       String mess2 = "Please enter charge, e.g +2";
/*  256 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  258 */     this.arrayl.add(new Integer(charg));
/*  259 */     this.arrayl.add(new Double(-1.0D));
/*  260 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(double partCoeff, String ion, double concnA, double concnB, double assocK)
/*      */   {
/*  268 */     IonicRadii ir = new IonicRadii();
/*  269 */     this.arrayl.add(ion);
/*  270 */     this.arrayl.add(new Double(concnA));
/*  271 */     this.arrayl.add(new Double(concnB));
/*  272 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  273 */     this.arrayl.add(new Double(assocK));
/*  274 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  275 */     double rad = 0.0D;
/*  276 */     if (this.includeIc) {
/*  277 */       if (this.radiusType) {
/*  278 */         rad = IonicRadii.hydratedRadius(ion);
/*      */       }
/*      */       else {
/*  281 */         rad = IonicRadii.radius(ion);
/*      */       }
/*      */       
/*  284 */       if (rad == 0.0D) {
/*  285 */         String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  286 */         String mess2 = "Please enter radius in metres\n";
/*  287 */         String mess3 = "Enter 0.0 if you wish interfacial charge to be neglected";
/*  288 */         rad = Db.readDouble(mess1 + mess2 + mess3);
/*  289 */         if (rad == 0.0D) this.includeIc = false;
/*      */       }
/*      */     }
/*  292 */     this.arrayl.add(new Double(rad));
/*  293 */     int charg = 0;
/*  294 */     charg = IonicRadii.charge(ion);
/*  295 */     if (charg == 0) {
/*  296 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  297 */       String mess2 = "Please enter charge, e.g +2";
/*  298 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  300 */     this.arrayl.add(new Integer(charg));
/*  301 */     this.arrayl.add(new Double(partCoeff));
/*  302 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concnA, double concnB)
/*      */   {
/*  311 */     IonicRadii ir = new IonicRadii();
/*  312 */     this.arrayl.add(ion);
/*  313 */     this.arrayl.add(new Double(concnA));
/*  314 */     this.arrayl.add(new Double(concnB));
/*  315 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  316 */     this.arrayl.add(new Double(0.0D));
/*  317 */     double rad = 0.0D;
/*  318 */     if (this.radiusType) {
/*  319 */       rad = IonicRadii.hydratedRadius(ion);
/*      */     }
/*      */     else {
/*  322 */       rad = IonicRadii.radius(ion);
/*      */     }
/*  324 */     if (rad == 0.0D) {
/*  325 */       String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  326 */       String mess2 = "Please enter radius in metres\n";
/*  327 */       rad = Db.readDouble(mess1 + mess2);
/*  328 */       if (rad == 0.0D) this.includeIc = false;
/*      */     }
/*  330 */     this.arrayl.add(new Double(rad));
/*  331 */     int charg = 0;
/*  332 */     charg = IonicRadii.charge(ion);
/*  333 */     if (charg == 0) {
/*  334 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  335 */       String mess2 = "Please enter charge, e.g +2";
/*  336 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  338 */     this.arrayl.add(new Integer(charg));
/*  339 */     this.arrayl.add(new Double(-1.0D));
/*  340 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(double partCoeff, String ion, double concnA, double concnB)
/*      */   {
/*  349 */     IonicRadii ir = new IonicRadii();
/*  350 */     this.arrayl.add(ion);
/*  351 */     this.arrayl.add(new Double(concnA));
/*  352 */     this.arrayl.add(new Double(concnB));
/*  353 */     if ((concnA > 0.0D) || (concnB > 0.0D)) this.nonZeroConcns += 1;
/*  354 */     this.arrayl.add(new Double(0.0D));
/*  355 */     double rad = 0.0D;
/*  356 */     if (this.includeIc) {
/*  357 */       if (this.radiusType) {
/*  358 */         rad = IonicRadii.hydratedRadius(ion);
/*      */       }
/*      */       else {
/*  361 */         rad = IonicRadii.radius(ion);
/*      */       }
/*      */       
/*  364 */       if (rad == 0.0D) {
/*  365 */         String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  366 */         String mess2 = "Please enter radius in metres\n";
/*  367 */         String mess3 = "Enter 0.0 if you wish interfacial charge to be neglected";
/*  368 */         rad = Db.readDouble(mess1 + mess2 + mess3);
/*  369 */         if (rad == 0.0D) this.includeIc = false;
/*      */       }
/*      */     }
/*  372 */     this.arrayl.add(new Double(rad));
/*  373 */     int charg = 0;
/*  374 */     charg = IonicRadii.charge(ion);
/*  375 */     if (charg == 0) {
/*  376 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  377 */       String mess2 = "Please enter charge, e.g +2";
/*  378 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  380 */     this.arrayl.add(new Integer(charg));
/*  381 */     this.arrayl.add(new Double(partCoeff));
/*  382 */     this.numOfIons += 1;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setIonophore(double concn, double radius)
/*      */   {
/*  388 */     this.ionophoreConcn = (concn * 1000.0D);
/*  389 */     this.ionophoreRad = radius;
/*  390 */     this.ionophoreSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIonophore(String ionophore, double concn, double radius)
/*      */   {
/*  397 */     this.ionophore = ionophore;
/*  398 */     this.ionophoreConcn = (concn * 1000.0D);
/*  399 */     this.ionophoreRad = radius;
/*  400 */     this.ionophoreSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIonophore(String ionophore, double concn)
/*      */   {
/*  408 */     this.ionophore = ionophore;
/*  409 */     this.ionophoreConcn = (concn * 1000.0D);
/*  410 */     this.includeIc = false;
/*  411 */     this.ionophoreSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIonophore(double concn)
/*      */   {
/*  418 */     this.ionophoreConcn = (concn * 1000.0D);
/*  419 */     this.includeIc = false;
/*  420 */     this.ionophoreSet = true;
/*      */   }
/*      */   
/*      */   public void setVolumes(double volA, double volB, double area)
/*      */   {
/*  425 */     this.volumeA = volA;
/*  426 */     this.volumeB = volB;
/*  427 */     this.interfacialArea = area;
/*  428 */     this.volumeSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setVolumes(double volA, double volB)
/*      */   {
/*  434 */     this.volumeA = volA;
/*  435 */     this.volumeB = volB;
/*  436 */     this.includeIc = false;
/*  437 */     this.volumeSet = true;
/*      */   }
/*      */   
/*      */   public void setRelPerm(double epsA, double epsB, double epsSternA, double epsSternB)
/*      */   {
/*  442 */     this.epsilonA = epsA;
/*  443 */     this.epsilonB = epsB;
/*  444 */     this.epsilonSternA = epsSternA;
/*  445 */     this.epsilonSternB = epsSternB;
/*  446 */     this.epsilonSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setRelPerm(double epsA, double epsB)
/*      */   {
/*  452 */     this.epsilonA = epsA;
/*  453 */     this.epsilonB = epsB;
/*  454 */     this.includeIc = false;
/*  455 */     this.epsilonSet = true;
/*      */   }
/*      */   
/*      */   public void setTemp(double temp)
/*      */   {
/*  460 */     this.temp = (temp - -273.15D);
/*  461 */     this.tempSet = true;
/*      */   }
/*      */   
/*      */   public void setEstimate(double pot)
/*      */   {
/*  466 */     this.estimate = pot;
/*      */   }
/*      */   
/*      */   public void setStep(double step)
/*      */   {
/*  471 */     this.step = step;
/*      */   }
/*      */   
/*      */   public void setTolerance(double tol)
/*      */   {
/*  476 */     this.tolerance = tol;
/*      */   }
/*      */   
/*      */   public void setMaxIterations(int nMax)
/*      */   {
/*  481 */     this.nMaxIter = nMax;
/*      */   }
/*      */   
/*      */   public double getDonnanPotential()
/*      */   {
/*  486 */     return this.donnanPotential;
/*      */   }
/*      */   
/*      */   public double getDiffuseLayerPotentialA()
/*      */   {
/*  491 */     return this.diffPotentialA;
/*      */   }
/*      */   
/*      */   public double getDiffuseLayerPotentialB()
/*      */   {
/*  496 */     return this.diffPotentialB;
/*      */   }
/*      */   
/*      */   public double getSternLayerPotential()
/*      */   {
/*  501 */     return this.sternPotential;
/*      */   }
/*      */   
/*      */   public double[] getConcnA()
/*      */   {
/*  506 */     double[] concn = (double[])this.concnA.clone();
/*  507 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  508 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getConcnB()
/*      */   {
/*  513 */     double[] concn = (double[])this.concnB.clone();
/*  514 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  515 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getComplex()
/*      */   {
/*  520 */     double[] concn = (double[])this.complex.clone();
/*  521 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  522 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getExcessConcnA()
/*      */   {
/*  527 */     if (!this.includeIc) {
/*  528 */       System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
/*      */     }
/*  530 */     double[] concn = (double[])this.excessConcnA.clone();
/*  531 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  532 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getExcessConcnB()
/*      */   {
/*  537 */     if (!this.includeIc) {
/*  538 */       System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
/*      */     }
/*  540 */     double[] concn = (double[])this.excessConcnB.clone();
/*  541 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  542 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getExcessComplex()
/*      */   {
/*  547 */     if (!this.includeIc) {
/*  548 */       System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
/*      */     }
/*  550 */     double[] concn = (double[])this.excessComplex.clone();
/*  551 */     for (int i = 0; i < this.numOfIons; i++) concn[i] *= 0.001D;
/*  552 */     return concn;
/*      */   }
/*      */   
/*      */   public double[] getRatioA()
/*      */   {
/*  557 */     if (!this.includeIc) {
/*  558 */       System.out.println("Class: Donnan\nMethod: getRatioA\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
/*      */     }
/*  560 */     return this.ratioA;
/*      */   }
/*      */   
/*      */   public double[] getRatioB()
/*      */   {
/*  565 */     if (!this.includeIc) {
/*  566 */       System.out.println("Class: Donnan\nMethod: getRatioB\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
/*      */     }
/*  568 */     return this.ratioB;
/*      */   }
/*      */   
/*      */   public double[] getRatioComplex()
/*      */   {
/*  573 */     if (!this.includeIc) {
/*  574 */       System.out.println("Class: Donnan\nMethod: getRatioComplex\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
/*      */     }
/*  576 */     return this.ratioC;
/*      */   }
/*      */   
/*      */   public double[] getPartitionCoefficients()
/*      */   {
/*  581 */     return this.partCoeffPot;
/*      */   }
/*      */   
/*      */   public double[] getPartitionCoefficientsZero()
/*      */   {
/*  586 */     return this.partCoeff;
/*      */   }
/*      */   
/*      */   public double[] getDeltaMu0()
/*      */   {
/*  591 */     return this.deltaMu0;
/*      */   }
/*      */   
/*      */   public double getInterfaceCharge()
/*      */   {
/*  596 */     if (!this.includeIc) {
/*  597 */       System.out.println("Class: Donnan\nMethod: getInterfaceCharge\nThe value of the interface charge has not been calculated\nzero returned");
/*      */     }
/*  599 */     return this.interfacialCharge;
/*      */   }
/*      */   
/*      */   public double getInterfaceChargeDensity()
/*      */   {
/*  604 */     if (!this.includeIc) {
/*  605 */       System.out.println("Class: Donnan\nMethod: getInterfaceChargeDensity\nThe value of the interface charge density has not been calculated\nzero returned");
/*      */     }
/*  607 */     return this.interfacialCharge;
/*      */   }
/*      */   
/*      */   public double getSternCapacitance()
/*      */   {
/*  612 */     if (!this.includeIc) {
/*  613 */       System.out.println("Class: Donnan\nMethod: getSternCapacitance\nThe value of the Stern capacitance has not been calculated\nzero returned");
/*      */     }
/*  615 */     return this.sternCap * this.interfacialArea;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getDiffuseLayerCapacitanceA()
/*      */   {
/*  621 */     if (!this.includeIc) {
/*  622 */       System.out.println("Class: Donnan\nMethod: getDiffuseLayerCapacitanceA\nThe values of the diffuse layer capacitances have not been calculated\nzero returned");
/*      */     }
/*  624 */     return this.diffCapA * this.interfacialArea;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getDiffuseLayerCapacitanceB()
/*      */   {
/*  630 */     if (!this.includeIc) {
/*  631 */       System.out.println("Class: Donnan\nMethod: getDiffuseLayerCapacitanceB\nThe values of the diffuse layer capacitances have not been calculated\nzero returned");
/*      */     }
/*  633 */     return this.diffCapB * this.interfacialArea;
/*      */   }
/*      */   
/*      */   public double getDonnanCapacitanceB()
/*      */   {
/*  638 */     if (!this.includeIc) {
/*  639 */       System.out.println("Class: Donnan\nMethod: getDonnanCapacitance\nThe value of the Donnan capacitance has not been calculated\nzero returned");
/*      */     }
/*  641 */     return this.donnanCap * this.interfacialArea;
/*      */   }
/*      */   
/*      */   public double getSternThicknessA()
/*      */   {
/*  646 */     if (!this.includeIc) {
/*  647 */       System.out.println("Class: Donnan\nMethod: getSternThicknessA\nThe values of the Stern layer thicknesses have not been calculated\nzero returned");
/*      */     }
/*  649 */     return this.sternDeltaA;
/*      */   }
/*      */   
/*      */   public double getSternThicknessB()
/*      */   {
/*  654 */     if (!this.includeIc) {
/*  655 */       System.out.println("Class: Donnan\nMethod: getSternThicknessB\nThe values of the Stern layer thicknesses have not been calculated\nzero returned");
/*      */     }
/*  657 */     return this.sternDeltaB;
/*      */   }
/*      */   
/*      */   public double getDebyeLengthA()
/*      */   {
/*  662 */     if (!this.includeIc) {
/*  663 */       System.out.println("Class: Donnan\nMethod: getDebyeLengthA\nThe values of the Debye lengths have not been calculated\nzero returned");
/*      */     }
/*  665 */     return this.recipKappaA;
/*      */   }
/*      */   
/*      */   public double getDebyeLengthB()
/*      */   {
/*  670 */     if (!this.includeIc) {
/*  671 */       System.out.println("Class: Donnan\nMethod: getDebyeLengthB\nThe values of the Debye lengths have not been calculated\nzero returned");
/*      */     }
/*  673 */     return this.recipKappaB;
/*      */   }
/*      */   
/*      */   public double getMinimum()
/*      */   {
/*  678 */     return this.minimum;
/*      */   }
/*      */   
/*      */ 
/*      */   public double calcPotential()
/*      */   {
/*  684 */     unpack();
/*      */     
/*      */ 
/*  687 */     double numOfIonsHold = this.numOfIons;
/*  688 */     double[] assocConstshold = null;
/*  689 */     double[] radiihold = null;
/*  690 */     double[] chargeshold = null;
/*  691 */     double[] deltaMu0hold = null;
/*  692 */     double[] partCoeffhold = null;
/*      */     
/*  694 */     if (this.nonZeroConcns < this.numOfIons) {
/*  695 */       assocConstshold = (double[])this.assocConsts.clone();
/*  696 */       radiihold = (double[])this.radii.clone();
/*  697 */       chargeshold = (double[])this.charges.clone();
/*  698 */       deltaMu0hold = (double[])this.deltaMu0.clone();
/*  699 */       partCoeffhold = (double[])this.partCoeff.clone();
/*  700 */       boolean test = true;
/*  701 */       int jj = 0;
/*  702 */       while (test) {
/*  703 */         if (this.indexC[jj] == 0) {
/*  704 */           for (int k = jj + 1; k < this.numOfIons; k++) {
/*  705 */             this.concnA[(k - 1)] = this.concnA[k];
/*  706 */             this.concnB[(k - 1)] = this.concnB[k];
/*  707 */             this.complex[(k - 1)] = this.complex[k];
/*  708 */             this.molesT[(k - 1)] = this.molesT[k];
/*  709 */             this.assocConsts[(k - 1)] = this.assocConsts[k];
/*  710 */             this.radii[(k - 1)] = this.radii[k];
/*  711 */             this.charges[(k - 1)] = this.charges[k];
/*  712 */             this.deltaMu0[(k - 1)] = this.deltaMu0[k];
/*  713 */             this.partCoeff[(k - 1)] = this.partCoeff[k];
/*      */           }
/*  715 */           this.numOfIons -= 1;
/*      */         }
/*      */         else {
/*  718 */           jj++;
/*      */         }
/*  720 */         if (this.numOfIons == this.nonZeroConcns) { test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  730 */     boolean includeIcHold = false;
/*  731 */     if (this.includeIc) {
/*  732 */       includeIcHold = true;
/*  733 */       this.includeIc = false;
/*      */     }
/*      */     
/*      */ 
/*  737 */     Minimisation minPot = new Minimisation();
/*      */     
/*      */ 
/*  740 */     DonnanMinim functD = new DonnanMinim(this.numOfIons);
/*      */     
/*      */ 
/*      */ 
/*  744 */     functD.numOfIons = this.numOfIons;
/*  745 */     functD.concnA = this.concnA;
/*  746 */     functD.concnB = this.concnB;
/*  747 */     functD.molesT = this.molesT;
/*  748 */     functD.complex = this.complex;
/*  749 */     functD.excessConcnA = this.excessConcnA;
/*  750 */     functD.excessConcnB = this.excessConcnB;
/*  751 */     functD.excessComplex = this.excessComplex;
/*  752 */     functD.assocConsts = this.assocConsts;
/*  753 */     functD.indexK = this.indexK;
/*  754 */     functD.nonZeroAssocK = this.nonZeroAssocK;
/*  755 */     functD.radii = this.radii;
/*  756 */     functD.charges = this.charges;
/*  757 */     functD.ionophoreConcn = this.ionophoreConcn;
/*  758 */     functD.ionophoreRad = this.ionophoreRad;
/*  759 */     functD.volumeA = this.volumeA;
/*  760 */     functD.volumeB = this.volumeB;
/*  761 */     functD.interfacialArea = this.interfacialArea;
/*  762 */     functD.epsilonA = this.epsilonA;
/*  763 */     functD.epsilonB = this.epsilonB;
/*  764 */     functD.epsilonSternA = this.epsilonSternA;
/*  765 */     functD.epsilonSternB = this.epsilonSternB;
/*  766 */     functD.temp = this.temp;
/*  767 */     functD.partCoeff = this.partCoeff;
/*  768 */     functD.partCoeffPot = this.partCoeffPot;
/*  769 */     functD.sternCap = this.sternCap;
/*  770 */     functD.sternDeltaA = this.sternDeltaA;
/*  771 */     functD.sternDeltaB = this.sternDeltaB;
/*  772 */     functD.chargeValue = this.chargeValue;
/*  773 */     functD.chargeSame = this.chargeSame;
/*  774 */     functD.interfacialCharge = this.interfacialCharge;
/*  775 */     functD.interfacialChargeDensity = this.interfacialChargeDensity;
/*  776 */     functD.includeIc = this.includeIc;
/*      */     
/*      */ 
/*      */ 
/*  780 */     double[] start = { this.estimate };
/*      */     
/*  782 */     double[] step = { this.step };
/*      */     
/*      */ 
/*  785 */     minPot.nelderMead(functD, start, step, this.tolerance, this.nMaxIter);
/*      */     
/*      */ 
/*  788 */     double[] param = minPot.getParamValues();
/*  789 */     this.donnanPotential = param[0];
/*      */     
/*      */ 
/*  792 */     if (includeIcHold) {
/*  793 */       this.includeIc = true;
/*      */       
/*      */ 
/*  796 */       start[0] = this.donnanPotential;
/*      */       
/*  798 */       step[0] = this.step;
/*      */       
/*      */ 
/*  801 */       minPot.nelderMead(functD, start, step, this.tolerance, this.nMaxIter);
/*      */       
/*      */ 
/*  804 */       param = minPot.getParamValues();
/*  805 */       this.donnanPotential = param[0];
/*      */     }
/*      */     
/*      */ 
/*  809 */     ionConcns(this.donnanPotential);
/*      */     
/*      */ 
/*  812 */     if (this.nonZeroConcns != numOfIonsHold) {
/*  813 */       boolean test = true;
/*  814 */       int jj = 0;
/*  815 */       while (test) {
/*  816 */         if (this.indexC[jj] == 0) {
/*  817 */           for (int k = jj; k < this.numOfIons; k++) {
/*  818 */             this.concnA[(k + 1)] = this.concnA[k];
/*  819 */             this.concnB[(k + 1)] = this.concnB[k];
/*  820 */             this.complex[(k + 1)] = this.complex[k];
/*  821 */             this.excessConcnA[(k + 1)] = this.excessConcnA[k];
/*  822 */             this.excessConcnB[(k + 1)] = this.excessConcnB[k];
/*  823 */             this.excessComplex[(k + 1)] = this.excessComplex[k];
/*  824 */             this.molesT[(k + 1)] = this.molesT[k];
/*  825 */             this.assocConsts[(k + 1)] = this.assocConsts[k];
/*  826 */             this.radii[(k + 1)] = this.radii[k];
/*  827 */             this.charges[(k + 1)] = this.charges[k];
/*  828 */             this.deltaMu0[(k + 1)] = this.deltaMu0[k];
/*  829 */             this.partCoeff[(k + 1)] = this.partCoeff[k];
/*  830 */             this.partCoeffPot[(k + 1)] = this.partCoeffPot[k];
/*      */           }
/*  832 */           this.numOfIons += 1;
/*  833 */           this.concnA[jj] = 0.0D;
/*  834 */           this.concnB[jj] = 0.0D;
/*  835 */           this.complex[jj] = 0.0D;
/*  836 */           this.excessConcnA[jj] = 0.0D;
/*  837 */           this.excessConcnB[jj] = 0.0D;
/*  838 */           this.excessComplex[jj] = 0.0D;
/*  839 */           this.molesT[jj] = 0.0D;
/*  840 */           this.assocConsts[jj] = assocConstshold[jj];
/*  841 */           this.radii[jj] = radiihold[jj];
/*  842 */           this.charges[jj] = chargeshold[jj];
/*  843 */           this.deltaMu0[jj] = deltaMu0hold[jj];
/*  844 */           this.partCoeff[jj] = partCoeffhold[jj];
/*      */         }
/*      */         else {
/*  847 */           jj++;
/*      */         }
/*  849 */         if (this.numOfIons == this.nonZeroConcns) { test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  854 */     this.minimum = minPot.getMinimum();
/*      */     
/*      */ 
/*  857 */     this.numIterations = minPot.getNiter();
/*      */     
/*  859 */     if (this.includeIc)
/*      */     {
/*  861 */       for (int i = 0; i < this.numOfIons; i++) {
/*  862 */         this.ratioA[i] = (this.excessConcnA[i] / this.concnA[i]);
/*  863 */         this.ratioB[i] = (this.excessConcnB[i] / this.concnB[i]);
/*  864 */         this.ratioC[i] = (this.excessComplex[i] / this.complex[i]);
/*      */       }
/*      */       
/*      */ 
/*  868 */       this.diffCapA = (this.interfacialCharge / this.diffPotentialA);
/*  869 */       this.diffCapB = (this.interfacialCharge / this.diffPotentialB);
/*  870 */       this.donnanCap = (this.interfacialCharge / this.donnanPotential);
/*      */       
/*      */ 
/*  873 */       double preterm = 2.0D * Fmath.square(-1.60217646263E-19D) * 6.0221419947E23D / (1.2224537278297904E-34D * this.temp);
/*  874 */       double pretermA = preterm / this.epsilonA;
/*  875 */       double pretermB = preterm / this.epsilonB;
/*  876 */       this.recipKappaA = 0.0D;
/*  877 */       this.recipKappaB = 0.0D;
/*  878 */       for (int i = 0; i < this.numOfIons; i++) {
/*  879 */         this.recipKappaA += this.concnA[i] * this.charges[i] * this.charges[i];
/*  880 */         this.recipKappaB += (this.concnB[i] + this.complex[i]) * this.charges[i] * this.charges[i];
/*      */       }
/*  882 */       this.recipKappaA = (1.0D / Math.sqrt(this.recipKappaA * pretermA));
/*  883 */       this.recipKappaB = (1.0D / Math.sqrt(this.recipKappaB * pretermB));
/*      */       
/*      */ 
/*      */ 
/*  887 */       for (int ii = 0; ii < this.numOfIons; ii++) {
/*  888 */         if (this.indexPC[ii] != 0) {
/*  889 */           this.deltaMu0[ii] = (Math.log(this.partCoeff[ii]) * 8.314472296156563D * this.temp);
/*      */         }
/*      */         else {
/*  892 */           this.deltaMu0[ii] *= 6.0221419947E23D;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  897 */       for (int ii = 0; ii < this.numOfIons; ii++) {
/*  898 */         this.partCoeffPot[ii] = (this.partCoeff[ii] * Math.exp(-this.donnanPotential * this.charges[ii] * -1.60217646263E-19D / (1.380650324E-23D * this.temp)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  903 */     return this.donnanPotential;
/*      */   }
/*      */   
/*      */ 
/*      */   private void unpack()
/*      */   {
/*  909 */     if (!this.volumeSet) throw new IllegalArgumentException("The volumes of the partitions have not been set");
/*  910 */     if (this.numOfIons == 0) throw new IllegalArgumentException("No ions have been entered");
/*  911 */     if (this.nonZeroConcns == 0) throw new IllegalArgumentException("No non-zero ionic concentrations have been entered");
/*  912 */     if (!this.epsilonSet) throw new IllegalArgumentException("The relative permittivities of the partitions have not been set");
/*  913 */     if (!this.tempSet) System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
/*  914 */     if (!this.ionophoreSet) {
/*  915 */       System.out.println("The ionophore has not been entered\na concentration value of zero has been used");
/*  916 */       if (this.includeIc) {
/*  917 */         this.includeIc = false;
/*  918 */         System.out.println("and the interface charge option has been set to neglect the interface charge");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  926 */     this.ionNames = new String[this.numOfIons];
/*  927 */     this.concnA = new double[this.numOfIons];
/*  928 */     this.concnB = new double[this.numOfIons];
/*  929 */     this.molesT = new double[this.numOfIons];
/*  930 */     this.complex = new double[this.numOfIons];
/*  931 */     this.excessConcnA = new double[this.numOfIons];
/*  932 */     this.excessConcnB = new double[this.numOfIons];
/*  933 */     this.excessComplex = new double[this.numOfIons];
/*  934 */     this.ratioA = new double[this.numOfIons];
/*  935 */     this.ratioB = new double[this.numOfIons];
/*  936 */     this.ratioC = new double[this.numOfIons];
/*  937 */     this.assocConsts = new double[this.numOfIons];
/*  938 */     this.radii = new double[this.numOfIons];
/*  939 */     this.charges = new double[this.numOfIons];
/*  940 */     this.deltaMu0 = new double[this.numOfIons];
/*  941 */     this.partCoeff = new double[this.numOfIons];
/*  942 */     this.partCoeffPot = new double[this.numOfIons];
/*  943 */     this.indexK = new int[this.nonZeroAssocK];
/*  944 */     this.indexC = new int[this.numOfIons];
/*  945 */     this.indexPC = new boolean[this.numOfIons];
/*  946 */     Double hold = null;
/*  947 */     Integer holi = null;
/*  948 */     int ii = 0;
/*  949 */     this.chargeValue = 0.0D;
/*  950 */     this.chargeSame = true;
/*      */     
/*  952 */     for (int i = 0; i < this.numOfIons; i++)
/*      */     {
/*  954 */       this.ionNames[i] = ((String)this.arrayl.get(0 + i * 7));
/*      */       
/*  956 */       hold = (Double)this.arrayl.get(1 + i * 7);
/*  957 */       this.concnA[i] = (hold.doubleValue() * 1000.0D);
/*  958 */       hold = (Double)this.arrayl.get(2 + i * 7);
/*      */       
/*  960 */       this.concnB[i] = (hold.doubleValue() * 1000.0D);
/*      */       
/*  962 */       this.molesT[i] = (this.concnA[i] * this.volumeA + this.concnB[i] * this.volumeB);
/*  963 */       if (this.molesT[i] > 0.0D) {
/*  964 */         this.indexC[i] = 1;
/*      */       }
/*      */       else {
/*  967 */         this.indexC[i] = 0;
/*      */       }
/*      */       
/*  970 */       hold = (Double)this.arrayl.get(3 + i * 7);
/*  971 */       this.assocConsts[i] = (hold.doubleValue() * 0.001D);
/*  972 */       if (this.assocConsts[i] > 0.0D) {
/*  973 */         this.indexK[ii] = i;
/*  974 */         ii++;
/*      */       }
/*      */       
/*  977 */       hold = (Double)this.arrayl.get(4 + i * 7);
/*  978 */       this.radii[i] = hold.doubleValue();
/*      */       
/*  980 */       holi = (Integer)this.arrayl.get(5 + i * 7);
/*  981 */       this.charges[i] = holi.intValue();
/*      */       
/*  983 */       if (i == 0) {
/*  984 */         this.chargeValue = Math.abs(this.charges[0]);
/*      */ 
/*      */       }
/*  987 */       else if (Math.abs(this.charges[i]) != this.chargeValue) { this.chargeSame = false;
/*      */       }
/*      */       
/*      */ 
/*  991 */       hold = (Double)this.arrayl.get(6 + i * 7);
/*  992 */       this.partCoeff[i] = hold.doubleValue();
/*  993 */       this.indexPC[i] = true;
/*  994 */       if (this.partCoeff[i] == -1.0D) {
/*  995 */         this.indexPC[i] = false;
/*      */         
/*      */ 
/*  998 */         this.deltaMu0[i] = (BornChargingEnergy(this.radii[i], this.charges[i], this.epsilonB) - BornChargingEnergy(this.radii[i], this.charges[i], this.epsilonA));
/*      */         
/*      */ 
/* 1001 */         this.partCoeff[i] = Math.exp(this.deltaMu0[i] / (1.380650324E-23D * this.temp));
/*      */       }
/*      */       
/* 1004 */       if (this.charges[i] < 0.0D) {
/* 1005 */         this.numOfAnions += 1;
/*      */       }
/*      */       else {
/* 1008 */         this.numOfCations += 1;
/*      */       }
/* 1010 */       if (this.ionophoreConcn == 0.0D) { this.nonZeroAssocK = 0;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1015 */     double overallCharge = 0.0D;
/* 1016 */     double positives = 0.0D;
/* 1017 */     double negatives = 0.0D;
/* 1018 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1019 */       if (this.charges[i] > 0.0D) {
/* 1020 */         positives += this.molesT[i] * this.charges[i];
/*      */       }
/*      */       else {
/* 1023 */         negatives += this.molesT[i] * this.charges[i];
/*      */       }
/* 1025 */       overallCharge = positives + negatives;
/*      */     }
/* 1027 */     if (Math.abs(overallCharge) > positives * this.tol) {
/* 1028 */       String quest0 = "Class: Donnan, method: unpack()\n";
/* 1029 */       String quest1 = "Total charge = " + overallCharge + " mol/dm, i.e. is not equal to zero\n";
/* 1030 */       String quest2 = "Positive charge = " + positives + " mol/dm\n";
/* 1031 */       String quest3 = "Do you wish to continue?";
/* 1032 */       String quest = quest0 + quest1 + quest2 + quest3;
/* 1033 */       int res = JOptionPane.showConfirmDialog(null, quest, "Neutrality check", 0, 3);
/* 1034 */       if (res == 1) System.exit(0);
/*      */     }
/*      */   }
/*      */   
/*      */   public double BornChargingEnergy(double radius, double charge, double epsilon)
/*      */   {
/* 1040 */     return Fmath.square(-1.60217646263E-19D * charge) / (25.132741228718345D * radius * 8.854187817E-12D * epsilon);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void ionConcns(double potential)
/*      */   {
/* 1048 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 1049 */       this.partCoeffPot[ii] = (this.partCoeff[ii] * Math.exp(-potential * this.charges[ii] * -1.60217646263E-19D / (1.380650324E-23D * this.temp)));
/*      */     }
/*      */     
/* 1052 */     if (!this.includeIc)
/*      */     {
/* 1054 */       if (this.nonZeroAssocK < 2)
/*      */       {
/* 1056 */         calcConcnsSingleK(potential);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1063 */         calcConcnsMultiK(potential);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1071 */       calcConcnsMultiK(potential);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void calcConcnsSingleK(double potential)
/*      */   {
/* 1078 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 1079 */       if ((this.assocConsts[ii] == 0.0D) || (this.ionophoreConcn == 0.0D)) {
/* 1080 */         if (this.molesT[ii] == 0.0D)
/*      */         {
/* 1082 */           this.concnB[ii] = 0.0D;
/* 1083 */           this.concnA[ii] = 0.0D;
/* 1084 */           this.complex[ii] = 0.0D;
/*      */         }
/*      */         else
/*      */         {
/* 1088 */           this.concnB[ii] = (this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB));
/* 1089 */           this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 1090 */           this.complex[ii] = 0.0D;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1097 */         double aa = this.assocConsts[ii] * (this.volumeB + this.volumeA * this.partCoeffPot[ii]);
/* 1098 */         double bb = this.volumeB + this.volumeA * this.partCoeffPot[ii] + this.volumeB * this.assocConsts[ii] * this.ionophoreConcn - this.assocConsts[ii] * this.molesT[ii];
/* 1099 */         double cc = -this.molesT[ii];
/*      */         
/* 1101 */         double root = bb * bb - 4.0D * aa * cc;
/* 1102 */         if (root < 0.0D) {
/* 1103 */           System.out.println("Class: DonnanMinim\nMethod: ionConcns\nthe square root term (b2-4ac) of the quadratic = " + root);
/* 1104 */           System.out.println("this term was set to zero as the negative value MAY have arisen from rounding errors");
/* 1105 */           root = 0.0D;
/*      */         }
/* 1107 */         double qq = -0.5D * (bb + Fmath.sign(bb) * Math.sqrt(root));
/* 1108 */         double root1 = qq / aa;
/* 1109 */         double root2 = cc / qq;
/* 1110 */         double limit = this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB);
/* 1111 */         if ((root1 >= 0.0D) && (root1 <= limit)) {
/* 1112 */           if ((root2 < 0.0D) || (root2 > limit)) {
/* 1113 */             this.concnB[ii] = root1;
/* 1114 */             this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 1115 */             this.complex[ii] = (this.assocConsts[ii] * this.ionophoreConcn * this.concnB[ii] / (1.0D + this.assocConsts[ii] * this.concnB[ii]));
/*      */           }
/*      */           else
/*      */           {
/* 1119 */             System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 1120 */             System.out.println("error1: no physically meaningfull root");
/* 1121 */             System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1122 */             System.exit(0);
/*      */           }
/*      */           
/*      */         }
/* 1126 */         else if ((root2 >= 0.0D) && (root2 <= limit)) {
/* 1127 */           if ((root1 < 0.0D) || (root1 > limit)) {
/* 1128 */             this.concnB[ii] = root2;
/* 1129 */             this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/*      */             
/* 1131 */             this.complex[ii] = (this.assocConsts[ii] * this.ionophoreConcn * this.concnB[ii] / (1.0D + this.assocConsts[ii] * this.concnB[ii]));
/*      */           }
/*      */           else {
/* 1134 */             System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 1135 */             System.out.println("error2: no physically meaningfull root");
/* 1136 */             System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1137 */             System.exit(0);
/*      */           }
/*      */         }
/*      */         else {
/* 1141 */           System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 1142 */           System.out.println("error3: no physically meaningfull root");
/* 1143 */           System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1144 */           System.exit(0);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void calcConcnsMultiK(double potential)
/*      */   {
/* 1158 */     double[] start = new double[this.numOfIons];
/* 1159 */     double[] step = new double[this.numOfIons];
/*      */     
/*      */ 
/* 1162 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 1163 */       if (this.molesT[ii] == 0.0D)
/*      */       {
/* 1165 */         this.concnB[ii] = 0.0D;
/* 1166 */         this.concnA[ii] = 0.0D;
/* 1167 */         this.complex[ii] = 0.0D;
/* 1168 */         this.excessConcnA[ii] = 0.0D;
/* 1169 */         this.excessConcnB[ii] = 0.0D;
/* 1170 */         this.excessComplex[ii] = 0.0D;
/*      */       }
/*      */       else
/*      */       {
/* 1174 */         this.concnB[ii] = (this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB));
/* 1175 */         this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 1176 */         this.complex[ii] = 0.0D;
/* 1177 */         this.excessConcnA[ii] = 0.0D;
/* 1178 */         this.excessConcnB[ii] = 0.0D;
/* 1179 */         this.excessComplex[ii] = 0.0D;
/*      */       }
/* 1181 */       start[ii] = this.concnB[ii];
/* 1182 */       step[ii] = (0.05D * start[ii]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1189 */     Minimisation minConcn = new Minimisation();
/*      */     
/*      */ 
/* 1192 */     DonnanConcn functC = new DonnanConcn();
/*      */     
/*      */ 
/* 1195 */     functC.numOfIons = this.numOfIons;
/* 1196 */     functC.concnA = this.concnA;
/* 1197 */     functC.concnB = this.concnB;
/* 1198 */     functC.molesT = this.molesT;
/* 1199 */     functC.complex = this.complex;
/* 1200 */     functC.excessConcnA = this.excessConcnA;
/* 1201 */     functC.excessConcnB = this.excessConcnB;
/* 1202 */     functC.excessComplex = this.excessComplex;
/* 1203 */     functC.assocConsts = this.assocConsts;
/* 1204 */     functC.indexK = this.indexK;
/* 1205 */     functC.nonZeroAssocK = this.nonZeroAssocK;
/* 1206 */     functC.radii = this.radii;
/* 1207 */     functC.charges = this.charges;
/* 1208 */     functC.ionophoreConcn = this.ionophoreConcn;
/* 1209 */     functC.ionophoreRad = this.ionophoreRad;
/* 1210 */     functC.volumeA = this.volumeA;
/* 1211 */     functC.volumeB = this.volumeB;
/* 1212 */     functC.interfacialArea = this.interfacialArea;
/* 1213 */     functC.epsilonA = this.epsilonA;
/* 1214 */     functC.epsilonB = this.epsilonB;
/* 1215 */     functC.epsilonSternA = this.epsilonSternA;
/* 1216 */     functC.epsilonSternB = this.epsilonSternB;
/* 1217 */     functC.temp = this.temp;
/* 1218 */     functC.partCoeffPot = this.partCoeffPot;
/* 1219 */     functC.sternCap = this.sternCap;
/* 1220 */     functC.sternDeltaA = this.sternDeltaA;
/* 1221 */     functC.sternDeltaB = this.sternDeltaB;
/* 1222 */     functC.chargeValue = this.chargeValue;
/* 1223 */     functC.chargeSame = this.chargeSame;
/* 1224 */     functC.interfacialCharge = this.interfacialCharge;
/* 1225 */     functC.interfacialChargeDensity = this.interfacialChargeDensity;
/* 1226 */     functC.potential = potential;
/* 1227 */     functC.includeIc = this.includeIc;
/*      */     
/*      */ 
/* 1230 */     minConcn.nelderMead(functC, start, step, 1.0E-20D, 10000);
/*      */     
/*      */ 
/* 1233 */     double[] param = minConcn.getParamValues();
/*      */     
/* 1235 */     this.freeIonophoreConcn = this.ionophoreConcn;
/* 1236 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1237 */       this.concnB[i] = param[i];
/* 1238 */       this.concnA[i] = (this.concnB[i] * this.partCoeffPot[i]);
/* 1239 */       this.freeIonophoreConcn -= this.complex[i];
/*      */     }
/*      */     
/* 1242 */     this.interfacialCharge = functC.interfacialCharge;
/* 1243 */     this.interfacialChargeDensity = functC.interfacialChargeDensity;
/* 1244 */     this.sternCap = functC.sternCap;
/* 1245 */     this.sternDeltaA = functC.sternDeltaA;
/* 1246 */     this.sternDeltaB = functC.sternDeltaB;
/* 1247 */     this.sternPotential = functC.sternPotential;
/* 1248 */     this.diffPotentialA = functC.diffPotentialA;
/* 1249 */     this.diffPotentialB = functC.diffPotentialB;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void printToFile(String title)
/*      */   {
/* 1257 */     FileOutput fout = new FileOutput(title);
/* 1258 */     fout.dateAndTimeln(title);
/* 1259 */     fout.println();
/* 1260 */     fout.print("Donnan potential = ");
/* 1261 */     fout.printsp(Fmath.truncate(this.donnanPotential, 7));
/* 1262 */     fout.println("volts");
/* 1263 */     if (this.includeIc) {
/* 1264 */       fout.print("Compartment A double layer potential difference = ");
/* 1265 */       fout.printsp(Fmath.truncate(this.diffPotentialA, 7));
/* 1266 */       fout.println("volts");
/* 1267 */       fout.print("Compartment B double layer potential difference = ");
/* 1268 */       fout.printsp(Fmath.truncate(this.diffPotentialB, 7));
/* 1269 */       fout.println("volts");
/* 1270 */       fout.print("Stern potential difference = ");
/* 1271 */       fout.printsp(Fmath.truncate(this.sternPotential, 7));
/* 1272 */       fout.println("volts");
/*      */     }
/* 1274 */     fout.println();
/*      */     
/* 1276 */     fout.println("Ionic concentrations expressed as mol per cubic decimetre (M)");
/* 1277 */     fout.println("Total = equivalent concentration with all ions in compartment A");
/* 1278 */     if (this.includeIc) {
/* 1279 */       fout.printtab("Ion");
/* 1280 */       fout.println("Bulk concentrations / M                         Excess concentrations / M                       total / M");
/* 1281 */       fout.printtab(" ");
/* 1282 */       fout.println("A               B               complex         A               B               complex         ");
/*      */       
/* 1284 */       for (int i = 0; i < this.numOfIons; i++) {
/* 1285 */         fout.printtab(this.ionNames[i]);
/* 1286 */         fout.printtab(Fmath.truncate(this.concnA[i] * 0.001D, 7));
/* 1287 */         fout.printtab(Fmath.truncate(this.concnB[i] * 0.001D, 7));
/* 1288 */         fout.printtab(Fmath.truncate(this.complex[i] * 0.001D, 7));
/* 1289 */         fout.printtab(Fmath.truncate(this.excessConcnA[i] * 0.001D, 7));
/* 1290 */         fout.printtab(Fmath.truncate(this.excessConcnB[i] * 0.001D, 7));
/* 1291 */         fout.printtab(Fmath.truncate(this.excessComplex[i] * 0.001D, 7));
/* 1292 */         fout.println(Fmath.truncate(this.molesT[i] * 0.001D / this.volumeA, 7));
/*      */       }
/*      */     }
/*      */     else {
/* 1296 */       fout.printtab("Ion");
/* 1297 */       fout.println("A               B               complex         total");
/* 1298 */       for (int i = 0; i < this.numOfIons; i++) {
/* 1299 */         fout.printtab(this.ionNames[i]);
/* 1300 */         fout.printtab(Fmath.truncate(this.concnA[i] * 0.001D, 7));
/* 1301 */         fout.printtab(Fmath.truncate(this.concnB[i] * 0.001D, 7));
/* 1302 */         fout.printtab(Fmath.truncate(this.complex[i] * 0.001D, 7));
/* 1303 */         fout.println(Fmath.truncate(this.molesT[i] * 0.001D / this.volumeA, 7));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1308 */     fout.println();
/* 1309 */     fout.println("mols of each ionic species");
/* 1310 */     if (this.includeIc) {
/* 1311 */       fout.printtab("Ion");
/* 1312 */       fout.println("Bulk mols                                       Excess mols                                 total mols");
/* 1313 */       fout.printtab(" ");
/* 1314 */       fout.println("A               B               complex         A               B               complex         ");
/* 1315 */       for (int i = 0; i < this.numOfIons; i++) {
/* 1316 */         fout.printtab(this.ionNames[i]);
/* 1317 */         fout.printtab(Fmath.truncate(this.concnA[i] * this.volumeA, 7));
/* 1318 */         fout.printtab(Fmath.truncate(this.concnB[i] * this.volumeB, 7));
/* 1319 */         fout.printtab(Fmath.truncate(this.complex[i] * this.volumeB, 7));
/* 1320 */         fout.printtab(Fmath.truncate(this.excessConcnA[i] * this.volumeA, 7));
/* 1321 */         fout.printtab(Fmath.truncate(this.excessConcnB[i] * this.volumeB, 7));
/* 1322 */         fout.printtab(Fmath.truncate(this.excessComplex[i] * this.volumeB, 7));
/* 1323 */         fout.println(Fmath.truncate(this.molesT[i], 7));
/*      */       }
/*      */     }
/*      */     else {
/* 1327 */       fout.printtab("Ion");
/* 1328 */       fout.println("A               B               complex         total mols");
/* 1329 */       for (int i = 0; i < this.numOfIons; i++) {
/* 1330 */         fout.printtab(this.ionNames[i]);
/* 1331 */         fout.printtab(Fmath.truncate(this.concnA[i] * this.volumeA, 7));
/* 1332 */         fout.printtab(Fmath.truncate(this.concnB[i] * this.volumeB, 7));
/* 1333 */         fout.printtab(Fmath.truncate(this.complex[i] * this.volumeB, 7));
/* 1334 */         fout.println(Fmath.truncate(this.molesT[i], 7));
/*      */       }
/*      */     }
/* 1337 */     fout.println();
/*      */     
/* 1339 */     if (this.includeIc) {
/* 1340 */       fout.println("Ratios of excess concentration over bulk concentration");
/* 1341 */       fout.printtab("Ion");
/* 1342 */       fout.println("A               B               complex");
/* 1343 */       for (int i = 0; i < this.numOfIons; i++) {
/* 1344 */         fout.printtab(this.ionNames[i]);
/* 1345 */         fout.printtab(Fmath.truncate(this.ratioA[i], 7));
/* 1346 */         fout.printtab(Fmath.truncate(this.ratioB[i], 7));
/* 1347 */         fout.println(Fmath.truncate(this.ratioC[i], 7));
/*      */       }
/* 1349 */       fout.println();
/*      */     }
/*      */     
/* 1352 */     fout.print("Total ionophore concentration = ");
/* 1353 */     fout.printsp(Fmath.truncate(this.ionophoreConcn * 0.001D, 7));
/* 1354 */     fout.println("M");
/* 1355 */     fout.print("Free ionophore concentration = ");
/* 1356 */     fout.printsp(Fmath.truncate(this.freeIonophoreConcn * 0.001D, 7));
/* 1357 */     fout.println("M");
/* 1358 */     fout.print("Total ionophore moles = ");
/* 1359 */     fout.printsp(Fmath.truncate(this.ionophoreConcn * this.volumeB, 7));
/* 1360 */     fout.println("mol");
/* 1361 */     fout.print("Ionophore radius = ");
/* 1362 */     fout.printsp(Fmath.truncate(this.ionophoreRad, 7));
/* 1363 */     fout.println("m");
/* 1364 */     fout.println();
/* 1365 */     if (this.includeIc) {
/* 1366 */       fout.print("Interface charge density = ");
/* 1367 */       fout.printsp(Fmath.truncate(this.interfacialChargeDensity, 7));
/* 1368 */       fout.println("C per square metre");
/* 1369 */       fout.print("Total interface charge = ");
/* 1370 */       fout.printsp(Fmath.truncate(this.interfacialCharge, 7));
/* 1371 */       fout.println("C");
/* 1372 */       fout.print("Overall interfacial capacitance = ");
/* 1373 */       fout.printsp(Fmath.truncate(this.donnanCap * this.interfacialArea, 7));
/* 1374 */       fout.printsp("F ");
/* 1375 */       fout.print("  ->  ");
/* 1376 */       fout.printsp(Fmath.truncate(this.donnanCap, 7));
/* 1377 */       fout.println("Farads per square metre");
/* 1378 */       fout.print("Diffuse double layer capacitance (Compartment A) = ");
/* 1379 */       fout.printsp(Fmath.truncate(this.diffCapA * this.interfacialArea, 7));
/* 1380 */       fout.printsp("F ");
/* 1381 */       fout.print("  ->  ");
/* 1382 */       fout.printsp(Fmath.truncate(this.diffCapA, 7));
/* 1383 */       fout.println("Farads per square metre");
/* 1384 */       fout.print("Diffuse double layer capacitance (Compartment B) = ");
/* 1385 */       fout.printsp(Fmath.truncate(this.diffCapB * this.interfacialArea, 7));
/* 1386 */       fout.printsp("F ");
/* 1387 */       fout.print("  ->  ");
/* 1388 */       fout.printsp(Fmath.truncate(this.diffCapB, 7));
/* 1389 */       fout.println("Farads per square metre");
/* 1390 */       fout.print("Stern capacitance = ");
/* 1391 */       fout.printsp(Fmath.truncate(this.sternCap * this.interfacialArea, 7));
/* 1392 */       fout.printsp("F ");
/* 1393 */       fout.print("  ->  ");
/* 1394 */       fout.printsp(Fmath.truncate(this.sternCap, 7));
/* 1395 */       fout.println("Farads per square metre");
/* 1396 */       fout.print("Stern thickness (Compartment A) = ");
/* 1397 */       fout.printsp(Fmath.truncate(this.sternDeltaA, 7));
/* 1398 */       fout.println("m");
/* 1399 */       fout.print("Stern thickness (Compartment B) = ");
/* 1400 */       fout.printsp(Fmath.truncate(this.sternDeltaB, 7));
/* 1401 */       fout.println("m");
/* 1402 */       fout.print("Debye length (Compartment A) = ");
/* 1403 */       fout.printsp(Fmath.truncate(this.recipKappaA, 7));
/* 1404 */       fout.println("m");
/* 1405 */       fout.print("Debye length (Compartment B) = ");
/* 1406 */       fout.printsp(Fmath.truncate(this.recipKappaB, 7));
/* 1407 */       fout.println("m");
/* 1408 */       fout.println("Compartment thicknesses assuming cubes with one side equal to the interfacial area");
/* 1409 */       fout.print("Compartment A thickness = ");
/* 1410 */       fout.printsp(Fmath.truncate(this.volumeA / this.interfacialArea, 7));
/* 1411 */       fout.println("m");
/* 1412 */       fout.print("Compartment B thickness = ");
/* 1413 */       fout.printsp(Fmath.truncate(this.volumeB / this.interfacialArea, 7));
/* 1414 */       fout.println("m");
/* 1415 */       fout.println();
/*      */     }
/* 1417 */     fout.print("Volume of compartment A = ");
/* 1418 */     fout.printsp(this.volumeA);
/* 1419 */     fout.println("cubic metres");
/* 1420 */     fout.print("Volume of compartment B = ");
/* 1421 */     fout.printsp(this.volumeB);
/* 1422 */     fout.println("cubic metres");
/* 1423 */     fout.print("Interfacial area = ");
/* 1424 */     fout.printsp(this.interfacialArea);
/* 1425 */     fout.println("square metres");
/* 1426 */     fout.print("Relative electrical permittivity of compartment A = ");
/* 1427 */     fout.println(this.epsilonA);
/* 1428 */     fout.print("Relative electrical permittivity of compartment B = ");
/* 1429 */     fout.println(this.epsilonB);
/* 1430 */     fout.print("Relative electrical permittivity of compartment A Stern layer= ");
/* 1431 */     fout.println(this.epsilonSternA);
/* 1432 */     fout.print("Relative electrical permittivity of compartment B Stern layer= ");
/* 1433 */     fout.println(this.epsilonSternB);
/* 1434 */     fout.print("Temperature= ");
/* 1435 */     fout.printsp(this.temp + -273.15D);
/* 1436 */     fout.println("degrees Celsius");
/* 1437 */     fout.println();
/*      */     
/* 1439 */     fout.printtab("Ion");
/* 1440 */     fout.printtab("Radius   ");
/* 1441 */     fout.printtab("Charge");
/* 1442 */     fout.printtab("Partition");
/* 1443 */     fout.printtab("Partition");
/* 1444 */     fout.printtab("Delta(mu0)");
/* 1445 */     fout.println("Ion-Ionophore ");
/*      */     
/* 1447 */     fout.printtab("  ");
/* 1448 */     fout.printtab(" m       ");
/* 1449 */     fout.printtab(" ");
/* 1450 */     fout.printtab("Coefficient ");
/* 1451 */     fout.printtab("Coefficient ");
/* 1452 */     fout.printtab("/ J per mol");
/* 1453 */     fout.println("associaion ");
/*      */     
/* 1455 */     fout.printtab("  ");
/* 1456 */     fout.printtab("         ");
/* 1457 */     fout.printtab(" ");
/* 1458 */     fout.printtab("at      ");
/* 1459 */     fout.printtab("at zero ");
/* 1460 */     fout.printtab("       ");
/* 1461 */     fout.println("constant");
/*      */     
/* 1463 */     fout.printtab("  ");
/* 1464 */     fout.printtab("          ");
/* 1465 */     fout.printtab(" ");
/* 1466 */     fout.printtab("equilibrium ");
/* 1467 */     fout.printtab("potential ");
/* 1468 */     fout.printtab("          ");
/* 1469 */     fout.println("mol per cubic dm");
/*      */     
/* 1471 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1472 */       fout.printtab(this.ionNames[i]);
/* 1473 */       fout.printtab(Fmath.truncate(this.radii[i], 4));
/* 1474 */       fout.printtab(this.charges[i]);
/* 1475 */       fout.printtab(Fmath.truncate(this.partCoeffPot[i], 4));
/* 1476 */       fout.printtab(Fmath.truncate(this.partCoeff[i], 4));
/* 1477 */       fout.printtab(Fmath.truncate(this.deltaMu0[i], 4));
/* 1478 */       fout.println(Fmath.truncate(this.assocConsts[i] * 1000.0D, 4));
/*      */     }
/*      */     
/*      */ 
/* 1482 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void printToFile()
/*      */   {
/* 1488 */     String title = "DonnanOutputFile.txt";
/* 1489 */     printToFile(title);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/Donnan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */