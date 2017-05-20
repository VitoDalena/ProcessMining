/*      */ package flanagan.physchem;
/*      */ 
/*      */ import flanagan.integration.Integration;
/*      */ import flanagan.io.Db;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GouyChapmanStern
/*      */ {
/*   70 */   private ArrayList<Object> vec = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   79 */   private boolean unpackArrayList = false;
/*   80 */   private int numOfIons = 0;
/*   81 */   private int numOfAnions = 0;
/*   82 */   private int numOfCations = 0;
/*   83 */   private String[] ionNames = null;
/*   84 */   private double[] initConcnM = null;
/*   85 */   private double[] initConcn = null;
/*   86 */   private double[] siteConcn = null;
/*   87 */   private double[] sternConcn = null;
/*   88 */   private double[] bulkConcn = null;
/*   89 */   private double electrolyteConcn = 0.0D;
/*   90 */   private double ionicStrength = 0.0D;
/*   91 */   private int[] indexK = null;
/*   92 */   private int nonZeroAssocK = 0;
/*   93 */   private double[] radii = null;
/*   94 */   private boolean radiusType = true;
/*      */   
/*   96 */   private double[] charges = null;
/*   97 */   private double tolNeutral = 1.0E-6D;
/*      */   
/*      */ 
/*  100 */   private double[] assocConstsM = null;
/*  101 */   private double[] assocConsts = null;
/*      */   
/*  103 */   private double surfaceSiteDensity = 0.0D;
/*      */   
/*  105 */   private double freeSurfaceSiteDensity = 0.0D;
/*  106 */   private boolean surfaceDensitySet = false;
/*  107 */   private double epsilon = 0.0D;
/*  108 */   private double epsilonStern = 0.0D;
/*  109 */   private boolean epsilonSet = false;
/*  110 */   private double temp = 25.0D;
/*  111 */   private double tempK = 298.15D;
/*  112 */   private boolean tempSet = false;
/*  113 */   private double surfacePotential = 0.0D;
/*  114 */   private boolean psi0set = false;
/*  115 */   private double diffPotential = 0.0D;
/*  116 */   private double sternPotential = 0.0D;
/*  117 */   private double surfaceArea = 1.0D;
/*  118 */   private boolean surfaceAreaSet = false;
/*  119 */   private double volume = 1.0D;
/*  120 */   private boolean volumeSet = false;
/*  121 */   private double sternCap = 0.0D;
/*  122 */   private double diffCap = 0.0D;
/*  123 */   private double totalCap = 0.0D;
/*  124 */   private double sternDelta = 0.0D;
/*  125 */   private double chargeValue = 0.0D;
/*  126 */   private boolean chargeSame = true;
/*  127 */   private double averageCharge = 0.0D;
/*  128 */   private double surfaceChargeDensity = 0.0D;
/*  129 */   private double adsorbedChargeDensity = 0.0D;
/*  130 */   private double diffuseChargeDensity = 0.0D;
/*  131 */   private boolean sigmaSet = false;
/*  132 */   private double surfaceCharge = 0.0D;
/*  133 */   private boolean chargeSet = false;
/*  134 */   private double recipKappa = 0.0D;
/*  135 */   private boolean sternOption = true;
/*      */   
/*  137 */   private double expTerm = 0.0D;
/*  138 */   private double expTermOver2 = 0.0D;
/*  139 */   private double twoTerm = 0.0D;
/*  140 */   private double eightTerm = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setHydratedRadii()
/*      */   {
/*  149 */     this.radiusType = true;
/*      */   }
/*      */   
/*      */   public void setBareRadii()
/*      */   {
/*  154 */     this.radiusType = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void ignoreStern()
/*      */   {
/*  160 */     this.sternOption = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void includeStern()
/*      */   {
/*  166 */     this.sternOption = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setIon(String ion, double concn, double radius, int charge, double assocK)
/*      */   {
/*  172 */     this.vec.add(ion);
/*  173 */     this.vec.add(new Double(concn));
/*  174 */     this.vec.add(new Double(radius));
/*  175 */     this.vec.add(new Integer(charge));
/*  176 */     this.vec.add(new Double(assocK));
/*  177 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  178 */     this.numOfIons += 1;
/*  179 */     this.unpackArrayList = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concn, double radius, int charge)
/*      */   {
/*  186 */     this.vec.add(ion);
/*  187 */     this.vec.add(new Double(concn));
/*  188 */     this.vec.add(new Double(radius));
/*  189 */     this.vec.add(new Integer(charge));
/*  190 */     this.vec.add(new Double(0.0D));
/*  191 */     this.numOfIons += 1;
/*  192 */     this.unpackArrayList = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concn, double assocK)
/*      */   {
/*  200 */     IonicRadii ir = new IonicRadii();
/*  201 */     this.vec.add(ion);
/*  202 */     this.vec.add(new Double(concn));
/*  203 */     double rad = 0.0D;
/*  204 */     if (this.radiusType) {
/*  205 */       rad = IonicRadii.hydratedRadius(ion);
/*      */     }
/*      */     else {
/*  208 */       rad = IonicRadii.radius(ion);
/*      */     }
/*  210 */     if (rad == 0.0D) {
/*  211 */       String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  212 */       String mess2 = "Please enter radius in metres\n";
/*  213 */       rad = Db.readDouble(mess1 + mess2);
/*      */     }
/*  215 */     this.vec.add(new Double(rad));
/*  216 */     int charg = 0;
/*  217 */     charg = IonicRadii.charge(ion);
/*  218 */     if (charg == 0) {
/*  219 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  220 */       String mess2 = "Please enter charge as, e.g +2";
/*  221 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  223 */     this.vec.add(new Integer(charg));
/*  224 */     this.vec.add(new Double(assocK));
/*  225 */     if (assocK != 0.0D) this.nonZeroAssocK += 1;
/*  226 */     this.numOfIons += 1;
/*  227 */     this.unpackArrayList = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIon(String ion, double concn)
/*      */   {
/*  235 */     IonicRadii ir = new IonicRadii();
/*  236 */     this.vec.add(ion);
/*  237 */     this.vec.add(new Double(concn));
/*  238 */     double rad = 0.0D;
/*  239 */     if (this.radiusType) {
/*  240 */       rad = IonicRadii.hydratedRadius(ion);
/*      */     }
/*      */     else {
/*  243 */       rad = IonicRadii.radius(ion);
/*      */     }
/*  245 */     if (rad == 0.0D) {
/*  246 */       String mess1 = ion + " radius is not in the IonicRadii list\n";
/*  247 */       String mess2 = "Please enter radius in metres\n";
/*  248 */       rad = Db.readDouble(mess1 + mess2);
/*      */     }
/*  250 */     this.vec.add(new Double(rad));
/*  251 */     int charg = 0;
/*  252 */     charg = IonicRadii.charge(ion);
/*  253 */     if (charg == 0) {
/*  254 */       String mess1 = ion + " charge is not in the IonicRadii list\n";
/*  255 */       String mess2 = "Please enter charge as, e.g +2";
/*  256 */       charg = Db.readInt(mess1 + mess2);
/*      */     }
/*  258 */     this.vec.add(new Integer(charg));
/*  259 */     this.vec.add(new Double(0.0D));
/*  260 */     this.numOfIons += 1;
/*  261 */     this.unpackArrayList = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSurfaceSiteDensity(double density)
/*      */   {
/*  267 */     this.surfaceSiteDensity = (density / 6.0221419947E23D);
/*  268 */     this.surfaceDensitySet = true;
/*      */   }
/*      */   
/*      */   public void setSurfaceArea(double area)
/*      */   {
/*  273 */     this.surfaceArea = area;
/*  274 */     this.surfaceAreaSet = true;
/*      */   }
/*      */   
/*      */   public void setVolume(double vol)
/*      */   {
/*  279 */     this.volume = vol;
/*  280 */     this.volumeSet = true;
/*      */   }
/*      */   
/*      */   public void setRelPerm(double epsilon, double epsilonStern)
/*      */   {
/*  285 */     this.epsilon = epsilon;
/*  286 */     this.epsilonStern = epsilonStern;
/*  287 */     this.epsilonSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setRelPerm(double epsilon)
/*      */   {
/*  293 */     this.epsilon = epsilon;
/*  294 */     this.epsilonSet = true;
/*      */   }
/*      */   
/*      */   public void setTemp(double temp)
/*      */   {
/*  299 */     this.tempK = (temp - -273.15D);
/*  300 */     this.tempSet = true;
/*      */   }
/*      */   
/*      */   public void setSurfaceChargeDensity(double sigma)
/*      */   {
/*  305 */     if (this.psi0set) {
/*  306 */       System.out.println("You have already entered a surface potential");
/*  307 */       System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
/*  308 */       System.out.println("or the calculation of a surface potential for a given surface charge density");
/*  309 */       System.out.println("The previously entered surface potential will now be ignored");
/*  310 */       this.psi0set = false;
/*      */     }
/*      */     
/*  313 */     this.surfaceChargeDensity = sigma;
/*  314 */     this.sigmaSet = true;
/*  315 */     if (this.surfaceAreaSet) {
/*  316 */       this.surfaceCharge = (sigma * this.surfaceArea);
/*  317 */       this.chargeSet = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setSurfaceCharge(double charge, double area)
/*      */   {
/*  323 */     if (this.psi0set) {
/*  324 */       System.out.println("You have already entered a surface potential");
/*  325 */       System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
/*  326 */       System.out.println("or the calculation of a surface potential for a given surface charge density");
/*  327 */       System.out.println("The previously entered surface potential will now be ignored");
/*  328 */       this.psi0set = false;
/*      */     }
/*      */     
/*  331 */     this.surfaceCharge = charge;
/*  332 */     this.chargeSet = true;
/*  333 */     this.surfaceArea = area;
/*  334 */     this.surfaceAreaSet = true;
/*  335 */     this.surfaceChargeDensity = (charge / this.surfaceArea);
/*  336 */     this.sigmaSet = true;
/*      */   }
/*      */   
/*      */   public void setSurfaceCharge(double charge)
/*      */   {
/*  341 */     if (this.psi0set) {
/*  342 */       System.out.println("You have already entered a surface potential");
/*  343 */       System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
/*  344 */       System.out.println("or the calculation of a surface potential for a given surface charge density");
/*  345 */       System.out.println("The previously entered surface potential will now be ignored");
/*  346 */       this.psi0set = false;
/*      */     }
/*      */     
/*  349 */     this.surfaceCharge = charge;
/*  350 */     this.chargeSet = true;
/*  351 */     if (this.surfaceAreaSet) {
/*  352 */       this.surfaceChargeDensity = (charge / this.surfaceArea);
/*  353 */       this.sigmaSet = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setSurfacePotential(double psi0)
/*      */   {
/*  359 */     if (this.sigmaSet) {
/*  360 */       System.out.println("You have already entered a surface charge density");
/*  361 */       System.out.println("This class allows the calculation of a surface potential for a given surface charge density");
/*  362 */       System.out.println("or the calculation of a surface charge density for a given surface potential");
/*  363 */       System.out.println("The previously entered surface charge density will now be ignored");
/*  364 */       this.sigmaSet = false;
/*      */     }
/*      */     
/*  367 */     this.surfacePotential = psi0;
/*  368 */     this.psi0set = true;
/*      */   }
/*      */   
/*      */   public double getDiffuseLayerPotential()
/*      */   {
/*  373 */     if (!this.sternOption) {
/*  374 */       System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerPotential\nThe Stern modification was not included");
/*  375 */       System.out.println("The value of the diffuse layer potential has been set equal to the surface potential");
/*  376 */       return getSurfacePotential();
/*      */     }
/*      */     
/*  379 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  380 */       return this.diffPotential;
/*      */     }
/*      */     
/*  383 */     if (this.sigmaSet) {
/*  384 */       getSurfacePotential();
/*  385 */       return this.diffPotential;
/*      */     }
/*      */     
/*  388 */     if (this.psi0set) {
/*  389 */       getSurfaceChargeDensity();
/*  390 */       return this.diffPotential;
/*      */     }
/*      */     
/*  393 */     System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerPotential\nThe value of the diffuse layer potential has not been calculated\nzero returned");
/*  394 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  395 */     return 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getSternLayerPotential()
/*      */   {
/*  403 */     if (!this.sternOption) {
/*  404 */       System.out.println("Class: GouyChapmanStern\nMethod: getSternLayerPotential\nThe Stern modification has not been included");
/*  405 */       System.out.println("The value of zero has been returned");
/*  406 */       return 0.0D;
/*      */     }
/*      */     
/*  409 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  410 */       return this.sternPotential;
/*      */     }
/*      */     
/*  413 */     if (this.sigmaSet) {
/*  414 */       getSurfacePotential();
/*  415 */       return this.sternPotential;
/*      */     }
/*      */     
/*  418 */     if (this.psi0set) {
/*  419 */       getSurfaceChargeDensity();
/*  420 */       return this.sternPotential;
/*      */     }
/*      */     
/*  423 */     System.out.println("Class: GouyChapmanStern\nMethod: getSternLayerPotential\nThe value of the Stern layer potential has not been calculated\nzero returned");
/*  424 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  425 */     return 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getSternCapPerSquareMetre()
/*      */   {
/*  433 */     if (!this.sternOption) {
/*  434 */       System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe Stern modification has not been included");
/*  435 */       System.out.println("A value of infinity has been returned");
/*  436 */       return Double.POSITIVE_INFINITY;
/*      */     }
/*      */     
/*  439 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  440 */       return this.sternCap;
/*      */     }
/*      */     
/*  443 */     if (this.sigmaSet) {
/*  444 */       getSurfacePotential();
/*  445 */       return this.sternCap;
/*      */     }
/*      */     
/*  448 */     if (this.psi0set) {
/*  449 */       getSurfaceChargeDensity();
/*  450 */       return this.sternCap;
/*      */     }
/*      */     
/*  453 */     System.out.println("Class: GouyChapmanStern\nMethod: getSternCap\nThe value of the Stern capacitance has not been calculated\ninfinity returned");
/*  454 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  455 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getSternCapacitance()
/*      */   {
/*  463 */     if (!this.sternOption) {
/*  464 */       System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe Stern modification has not been included");
/*  465 */       System.out.println("A value of infinity has been returned");
/*  466 */       return Double.POSITIVE_INFINITY;
/*      */     }
/*      */     
/*  469 */     if (!this.surfaceAreaSet) {
/*  470 */       System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe surface area has not bee included");
/*  471 */       System.out.println("A value per square metre has been returned");
/*  472 */       return this.sternCap;
/*      */     }
/*      */     
/*  475 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  476 */       return this.sternCap * this.surfaceArea;
/*      */     }
/*      */     
/*  479 */     if (this.sigmaSet) {
/*  480 */       getSurfacePotential();
/*  481 */       return this.sternCap * this.surfaceArea;
/*      */     }
/*      */     
/*  484 */     if (this.psi0set) {
/*  485 */       getSurfaceChargeDensity();
/*  486 */       return this.sternCap * this.surfaceArea;
/*      */     }
/*      */     
/*  489 */     System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe value of the Stern capacitance has not been calculated\ninfinity returned");
/*  490 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  491 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDiffuseLayerCapPerSquareMetre()
/*      */   {
/*  499 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  500 */       return this.diffCap;
/*      */     }
/*      */     
/*  503 */     if (this.sigmaSet) {
/*  504 */       getSurfacePotential();
/*  505 */       return this.diffCap;
/*      */     }
/*      */     
/*  508 */     if (this.psi0set) {
/*  509 */       getSurfaceChargeDensity();
/*  510 */       return this.diffCap;
/*      */     }
/*      */     
/*  513 */     System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCapPerSquareMetre\nThe value of the diffuse layer capacitance has not been calculated\ninfinity returned");
/*  514 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  515 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDiffuseLayerCapacitance()
/*      */   {
/*  523 */     if (!this.surfaceAreaSet) {
/*  524 */       System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCapacitance\nThe surface area has not bee included");
/*  525 */       System.out.println("A value per square metre has been returned");
/*  526 */       return this.diffCap;
/*      */     }
/*      */     
/*  529 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  530 */       return this.diffCap * this.surfaceArea;
/*      */     }
/*      */     
/*  533 */     if (this.sigmaSet) {
/*  534 */       getSurfacePotential();
/*  535 */       return this.diffCap * this.surfaceArea;
/*      */     }
/*      */     
/*  538 */     if (this.psi0set) {
/*  539 */       getSurfaceChargeDensity();
/*  540 */       return this.diffCap * this.surfaceArea;
/*      */     }
/*      */     
/*  543 */     System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCap\nThe value of the diffuse layer capacitance has not been calculated\ninfinity returned");
/*  544 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  545 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTotalCapPerSquareMetre()
/*      */   {
/*  553 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  554 */       return this.totalCap;
/*      */     }
/*      */     
/*  557 */     if (this.sigmaSet) {
/*  558 */       getSurfacePotential();
/*  559 */       return this.totalCap;
/*      */     }
/*      */     
/*  562 */     if (this.psi0set) {
/*  563 */       getSurfaceChargeDensity();
/*  564 */       return this.totalCap;
/*      */     }
/*      */     
/*  567 */     System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapPerSquareMetre\nThe value of the total capacitance has not been calculated\ninfinity returned");
/*  568 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  569 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTotalCapacitance()
/*      */   {
/*  577 */     if (!this.surfaceAreaSet) {
/*  578 */       System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapacitance\nThe surface area has not bee included");
/*  579 */       System.out.println("A value per square metre has been returned");
/*  580 */       return this.diffCap;
/*      */     }
/*      */     
/*  583 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  584 */       return this.totalCap * this.surfaceArea;
/*      */     }
/*      */     
/*  587 */     if (this.sigmaSet) {
/*  588 */       getSurfacePotential();
/*  589 */       return this.totalCap * this.surfaceArea;
/*      */     }
/*      */     
/*  592 */     if (this.psi0set) {
/*  593 */       getSurfaceChargeDensity();
/*  594 */       return this.totalCap * this.surfaceArea;
/*      */     }
/*      */     
/*  597 */     System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapacitance\nThe value of the total capacitance has not been calculated\ninfinity returned");
/*  598 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  599 */     return Double.POSITIVE_INFINITY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getSternThickness()
/*      */   {
/*  607 */     if (!this.sternOption) {
/*  608 */       System.out.println("Class: GouyChapmanStern\nMethod: getSternThickness");
/*  609 */       System.out.println("The Stern modification has not been included");
/*  610 */       System.out.println("A value of zero has been returned");
/*  611 */       return 0.0D;
/*      */     }
/*      */     
/*  614 */     if ((this.psi0set) && (this.sigmaSet)) {
/*  615 */       return this.sternDelta;
/*      */     }
/*      */     
/*  618 */     if (this.sigmaSet) {
/*  619 */       getSurfacePotential();
/*  620 */       return this.sternDelta;
/*      */     }
/*      */     
/*  623 */     if (this.psi0set) {
/*  624 */       getSurfaceChargeDensity();
/*  625 */       return this.sternDelta;
/*      */     }
/*      */     
/*  628 */     System.out.println("Class: GouyChapmanStern\nMethod: getSternThickness\nThe value of the Stern thickness has not been calculated\nzero returned");
/*  629 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/*  630 */     return 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDebyeLength()
/*      */   {
/*  638 */     if (!this.unpackArrayList) unpack();
/*  639 */     return calcDebyeLength();
/*      */   }
/*      */   
/*      */   private double calcDebyeLength()
/*      */   {
/*  644 */     if (!this.epsilonSet) throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
/*  645 */     if (!this.tempSet) { System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
/*      */     }
/*  647 */     double preterm = 2.0D * Fmath.square(-1.60217646263E-19D) * 6.0221419947E23D / (8.854187817E-12D * this.epsilon * 1.380650324E-23D * this.tempK);
/*  648 */     this.recipKappa = 0.0D;
/*  649 */     for (int i = 0; i < this.numOfIons; i++) {
/*  650 */       this.recipKappa += this.bulkConcn[i] * this.charges[i] * this.charges[i];
/*      */     }
/*  652 */     this.recipKappa = (1.0D / Math.sqrt(this.recipKappa * preterm));
/*  653 */     return this.recipKappa;
/*      */   }
/*      */   
/*      */   public double getIonicStrength()
/*      */   {
/*  658 */     if (!this.unpackArrayList) unpack();
/*  659 */     return this.ionicStrength;
/*      */   }
/*      */   
/*      */   private void unpack()
/*      */   {
/*  664 */     if (this.numOfIons == 0) { throw new IllegalArgumentException("No ions have been entered");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  670 */     this.ionNames = new String[this.numOfIons];
/*  671 */     this.siteConcn = new double[this.numOfIons];
/*  672 */     this.sternConcn = new double[this.numOfIons];
/*  673 */     this.initConcnM = new double[this.numOfIons];
/*  674 */     this.initConcn = new double[this.numOfIons];
/*  675 */     this.bulkConcn = new double[this.numOfIons];
/*  676 */     this.radii = new double[this.numOfIons];
/*  677 */     this.charges = new double[this.numOfIons];
/*  678 */     this.assocConsts = new double[this.numOfIons];
/*  679 */     this.assocConstsM = new double[this.numOfIons];
/*  680 */     this.indexK = new int[this.nonZeroAssocK];
/*  681 */     Double hold = null;
/*  682 */     Integer holi = null;
/*  683 */     int ii = 0;
/*  684 */     this.chargeValue = 0.0D;
/*  685 */     this.chargeSame = true;
/*      */     
/*  687 */     for (int i = 0; i < this.numOfIons; i++) {
/*  688 */       this.ionNames[i] = ((String)this.vec.get(0 + i * 5));
/*  689 */       hold = (Double)this.vec.get(1 + i * 5);
/*  690 */       this.initConcnM[i] = hold.doubleValue();
/*  691 */       this.initConcn[i] = (this.initConcnM[i] * 1000.0D);
/*  692 */       hold = (Double)this.vec.get(2 + i * 5);
/*  693 */       this.radii[i] = hold.doubleValue();
/*  694 */       holi = (Integer)this.vec.get(3 + i * 5);
/*  695 */       this.charges[i] = holi.intValue();
/*  696 */       hold = (Double)this.vec.get(4 + i * 5);
/*  697 */       this.assocConstsM[i] = hold.doubleValue();
/*  698 */       this.assocConsts[i] = (this.assocConstsM[i] * 0.001D);
/*  699 */       if (this.assocConsts[i] > 0.0D) {
/*  700 */         this.indexK[ii] = i;
/*  701 */         ii++;
/*      */       }
/*      */       
/*      */ 
/*  705 */       if (i == 0) {
/*  706 */         this.chargeValue = Math.abs(this.charges[0]);
/*      */ 
/*      */       }
/*  709 */       else if (Math.abs(this.charges[i]) != this.chargeValue) { this.chargeSame = false;
/*      */       }
/*      */       
/*      */ 
/*  713 */       if (this.charges[i] < 0.0D) {
/*  714 */         this.numOfAnions += 1;
/*      */       }
/*      */       else {
/*  717 */         this.numOfCations += 1;
/*      */       }
/*  719 */       if (this.surfaceSiteDensity == 0.0D) { this.nonZeroAssocK = 0;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  724 */     this.averageCharge = 0.0D;
/*  725 */     this.ionicStrength = 0.0D;
/*  726 */     double overallCharge = 0.0D;
/*  727 */     double positives = 0.0D;
/*  728 */     double negatives = 0.0D;
/*  729 */     for (int i = 0; i < this.numOfIons; i++) {
/*  730 */       if (this.charges[i] > 0.0D) {
/*  731 */         positives += this.initConcn[i] * this.charges[i];
/*      */       }
/*      */       else {
/*  734 */         negatives += this.initConcn[i] * this.charges[i];
/*      */       }
/*  736 */       overallCharge = positives + negatives;
/*      */     }
/*  738 */     if (Math.abs(overallCharge) > positives * this.tolNeutral) {
/*  739 */       String quest0 = "Class: GouyChapmanStern, method: unpack()\n";
/*  740 */       String quest1 = "Total charge = " + overallCharge + " mol/dm, i.e. is not equal to zero\n";
/*  741 */       String quest2 = "Positive charge = " + positives + " mol/dm\n";
/*  742 */       String quest3 = "Do you wish to continue?";
/*  743 */       String quest = quest0 + quest1 + quest2 + quest3;
/*  744 */       int res = JOptionPane.showConfirmDialog(null, quest, "Neutrality check", 0, 3);
/*  745 */       if (res == 1) { System.exit(0);
/*      */       }
/*      */     }
/*  748 */     double numer = 0.0D;
/*  749 */     double denom = 0.0D;
/*  750 */     double anionConc = 0.0D;
/*  751 */     double cationConc = 0.0D;
/*  752 */     for (int i = 0; i < this.numOfIons; i++) {
/*  753 */       this.ionicStrength += this.initConcn[i] * this.charges[i] * this.charges[i];
/*  754 */       if (this.charges[i] > 0.0D) {
/*  755 */         cationConc += this.initConcn[i];
/*      */       }
/*      */       else {
/*  758 */         anionConc += this.initConcn[i];
/*      */       }
/*  760 */       if (this.initConcn[i] > 0.0D) {
/*  761 */         numer += this.initConcn[i] * Math.abs(this.charges[i]);
/*  762 */         denom += this.initConcn[i];
/*      */       }
/*      */     }
/*  765 */     this.ionicStrength = (this.ionicStrength * 0.001D / 2.0D);
/*  766 */     this.averageCharge = (numer / denom);
/*  767 */     this.electrolyteConcn = ((anionConc + cationConc) / 2.0D);
/*      */     
/*      */ 
/*  770 */     for (int i = 0; i < this.numOfIons; i++) {
/*  771 */       this.bulkConcn[i] = this.initConcn[i];
/*  772 */       this.siteConcn[i] = 0.0D;
/*  773 */       this.sternConcn[i] = 0.0D;
/*      */     }
/*      */     
/*      */ 
/*  777 */     this.expTerm = (1.60217646263E-19D / (1.380650324E-23D * this.tempK));
/*  778 */     this.expTermOver2 = (this.expTerm / 2.0D);
/*  779 */     this.twoTerm = (1.4723579861882691E-10D * this.epsilon * this.tempK);
/*  780 */     this.eightTerm = (4.0D * this.twoTerm);
/*      */     
/*  782 */     this.unpackArrayList = true;
/*      */   }
/*      */   
/*      */   public double getSurfaceChargeDensity()
/*      */   {
/*  787 */     if ((this.sigmaSet) && (this.psi0set)) {
/*  788 */       return this.surfaceChargeDensity;
/*      */     }
/*      */     
/*  791 */     if (!this.psi0set) throw new IllegalArgumentException("No surface potential has been entered");
/*  792 */     return getSurfaceChargeDensity(this.surfacePotential);
/*      */   }
/*      */   
/*      */ 
/*      */   public double getSurfaceChargeDensity(double psi)
/*      */   {
/*  798 */     this.surfaceChargeDensity = calcSurfaceChargeDensity(psi);
/*  799 */     this.sigmaSet = true;
/*  800 */     if (this.surfaceAreaSet) {
/*  801 */       this.surfaceCharge = (this.surfaceChargeDensity * this.surfaceArea);
/*  802 */       this.chargeSet = true;
/*      */     }
/*  804 */     return this.surfaceChargeDensity;
/*      */   }
/*      */   
/*      */   private double calcSurfaceChargeDensity(double psi)
/*      */   {
/*  809 */     double surCharDen = 0.0D;
/*  810 */     if (!this.epsilonSet) throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
/*  811 */     if (!this.tempSet) { System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
/*      */     }
/*  813 */     if (!this.unpackArrayList) unpack();
/*  814 */     if (this.sternOption) {
/*  815 */       if (!this.surfaceAreaSet) throw new IllegalArgumentException("The surface area has not been entered");
/*  816 */       if (!this.volumeSet) { throw new IllegalArgumentException("The electrolyte volume has not been entered");
/*      */       }
/*      */       
/*  819 */       if (this.nonZeroAssocK == 0)
/*      */       {
/*  821 */         if (this.chargeSame)
/*      */         {
/*  823 */           surCharDen = surfaceChargeDensity0(psi);
/*      */         }
/*      */         else
/*      */         {
/*  827 */           surCharDen = surfaceChargeDensity1(psi);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*  832 */       else if (this.chargeSame)
/*      */       {
/*  834 */         surCharDen = surfaceChargeDensity2(psi);
/*      */       }
/*      */       else
/*      */       {
/*  838 */         surCharDen = surfaceChargeDensity3(psi);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  844 */     else if (this.chargeSame)
/*      */     {
/*  846 */       surCharDen = Math.sqrt(this.eightTerm * this.electrolyteConcn) * Fmath.sinh(this.chargeValue * this.expTermOver2 * psi);
/*      */     }
/*      */     else
/*      */     {
/*  850 */       double sigmaSum = 0.0D;
/*  851 */       for (int i = 0; i < this.numOfIons; i++) {
/*  852 */         sigmaSum += this.bulkConcn[i] * (Math.exp(-this.expTerm * psi * this.charges[i]) - 1.0D);
/*      */       }
/*  854 */       surCharDen = Fmath.sign(psi) * Math.sqrt(this.twoTerm * sigmaSum);
/*      */     }
/*      */     
/*      */ 
/*  858 */     this.totalCap = (surCharDen / psi);
/*  859 */     if (!this.sternOption) {
/*  860 */       this.diffPotential = psi;
/*  861 */       this.sternCap = Double.POSITIVE_INFINITY;
/*  862 */       this.sternPotential = 0.0D;
/*  863 */       this.diffCap = this.totalCap;
/*      */     }
/*      */     else {
/*  866 */       this.diffPotential = (psi - surCharDen / this.sternCap);
/*  867 */       this.sternPotential = (psi - this.diffPotential);
/*  868 */       this.diffCap = ((surCharDen + this.adsorbedChargeDensity) / this.diffPotential);
/*      */     }
/*  870 */     return surCharDen;
/*      */   }
/*      */   
/*      */   public double getSurfaceCharge()
/*      */   {
/*  875 */     return getSurfaceCharge(this.surfacePotential);
/*      */   }
/*      */   
/*      */   public double getSurfaceCharge(double psi)
/*      */   {
/*  880 */     if (!this.surfaceAreaSet) throw new IllegalArgumentException("No surface area has been entered");
/*  881 */     if (this.sigmaSet) {
/*  882 */       this.surfaceCharge = (this.surfaceChargeDensity * this.surfaceArea);
/*      */     }
/*      */     else {
/*  885 */       if (!this.psi0set) throw new IllegalArgumentException("No surface potential has been entered");
/*  886 */       this.surfaceCharge = (getSurfaceChargeDensity(psi) * this.surfaceArea);
/*      */     }
/*      */     
/*  889 */     return this.surfaceCharge;
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfaceChargeDensity0(double psi)
/*      */   {
/*  895 */     double surCharDen = 0.0D;
/*      */     
/*      */ 
/*  898 */     double ionSum = 0.0D;
/*  899 */     for (int i = 0; i < this.numOfIons; i++) {
/*  900 */       if (this.charges[i] > 0.0D) {
/*  901 */         ionSum += this.bulkConcn[i];
/*      */       }
/*      */     }
/*  904 */     double sigmaLow = 0.0D;
/*  905 */     double sFuncLow = sigmaFunction0(sigmaLow, psi);
/*  906 */     double sigmaHigh = Math.sqrt(this.eightTerm * ionSum) * Fmath.sinh(this.chargeValue * this.expTermOver2 * psi);
/*  907 */     double sFuncHigh = sigmaFunction0(sigmaHigh, psi);
/*  908 */     if (sFuncHigh * sFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/*  909 */     double check = Math.abs(sigmaHigh) * 1.0E-6D;
/*  910 */     boolean test = true;
/*  911 */     double sigmaMid = 0.0D;
/*  912 */     double sFuncMid = 0.0D;
/*  913 */     int nIter = 0;
/*  914 */     while (test) {
/*  915 */       sigmaMid = (sigmaLow + sigmaHigh) / 2.0D;
/*  916 */       sFuncMid = sigmaFunction0(sigmaMid, psi);
/*  917 */       if (Math.abs(sFuncMid) <= check) {
/*  918 */         surCharDen = sigmaMid;
/*  919 */         test = false;
/*      */       }
/*      */       else {
/*  922 */         nIter++;
/*  923 */         if (nIter > 10000) {
/*  924 */           System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity0\nnumber of iterations exceeded in bisection\ncurrent value of sigma returned");
/*  925 */           surCharDen = sigmaMid;
/*  926 */           test = false;
/*      */ 
/*      */         }
/*  929 */         else if (sFuncMid * sFuncLow > 0.0D) {
/*  930 */           sigmaLow = sigmaMid;
/*  931 */           sFuncLow = sFuncMid;
/*      */         }
/*      */         else {
/*  934 */           sigmaHigh = sigmaMid;
/*  935 */           sFuncHigh = sFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  941 */     return surCharDen;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private double sigmaFunction0(double sigma, double psi)
/*      */   {
/*  948 */     calcSurfacePotential(sigma);
/*  949 */     return this.diffPotential - psi + sigma / this.sternCap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private double surfaceChargeDensity1(double psi)
/*      */   {
/*  956 */     double surCharDen = 0.0D;
/*      */     
/*      */ 
/*  959 */     double sigmaLow = 0.0D;
/*  960 */     double sFuncLow = sigmaFunction0(sigmaLow, psi);
/*  961 */     double sigmaHigh = 0.0D;
/*  962 */     for (int i = 0; i < this.numOfIons; i++) {
/*  963 */       sigmaHigh += this.bulkConcn[i] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[i] * psi) - 1.0D);
/*      */     }
/*  965 */     sigmaHigh = Fmath.sign(psi) * Math.sqrt(this.twoTerm * sigmaHigh);
/*  966 */     double sFuncHigh = sigmaFunction0(sigmaHigh, psi);
/*  967 */     if (sFuncHigh * sFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/*  968 */     double check = Math.abs(sigmaHigh) * 1.0E-6D;
/*  969 */     boolean test = true;
/*  970 */     double sigmaMid = 0.0D;
/*  971 */     double sFuncMid = 0.0D;
/*  972 */     int nIter = 0;
/*  973 */     while (test) {
/*  974 */       sigmaMid = (sigmaLow + sigmaHigh) / 2.0D;
/*  975 */       sFuncMid = sigmaFunction0(sigmaMid, psi);
/*  976 */       if (Math.abs(sFuncMid) <= check) {
/*  977 */         surCharDen = sigmaMid;
/*  978 */         test = false;
/*      */       }
/*      */       else {
/*  981 */         nIter++;
/*  982 */         if (nIter > 10000) {
/*  983 */           System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity1\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
/*  984 */           surCharDen = sigmaMid;
/*  985 */           test = false;
/*      */ 
/*      */         }
/*  988 */         else if (sFuncLow * sFuncMid > 0.0D) {
/*  989 */           sigmaLow = sigmaMid;
/*  990 */           sFuncLow = sFuncMid;
/*      */         }
/*      */         else {
/*  993 */           sigmaHigh = sigmaMid;
/*  994 */           sFuncHigh = sFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  999 */     return surCharDen;
/*      */   }
/*      */   
/*      */ 
/*      */   private double calcDelta(double psi)
/*      */   {
/* 1005 */     double numer = 0.0D;
/* 1006 */     double denom = 0.0D;
/* 1007 */     double delta = 0.0D;
/*      */     
/* 1009 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1010 */       this.sternConcn[i] = (this.bulkConcn[i] * Math.exp(-this.charges[i] * this.expTerm));
/*      */       
/* 1012 */       numer += this.sternConcn[i] * this.radii[i];
/* 1013 */       denom += this.sternConcn[i];
/*      */     }
/*      */     
/* 1016 */     this.sternDelta = (numer / denom);
/*      */     
/* 1018 */     return this.sternDelta;
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfaceChargeDensity2(double psi)
/*      */   {
/* 1024 */     double surCharDen = 0.0D;
/*      */     
/*      */ 
/* 1027 */     double sigmaAdsPos = 0.0D;
/* 1028 */     double sigmaAdsNeg = 0.0D;
/* 1029 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1030 */       if (this.charges[this.indexK[i]] > 0.0D) {
/* 1031 */         sigmaAdsPos = this.surfaceSiteDensity;
/*      */       }
/*      */       else {
/* 1034 */         sigmaAdsNeg = -this.surfaceSiteDensity;
/*      */       }
/*      */     }
/*      */     
/* 1038 */     double sigmaLow = 0.0D;
/* 1039 */     double sigmaHigh = 0.0D;
/* 1040 */     double ionSum = 0.0D;
/* 1041 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1042 */       ionSum += this.bulkConcn[i];
/*      */     }
/* 1044 */     ionSum /= 2.0D;
/* 1045 */     sigmaHigh = Math.sqrt(ionSum * this.eightTerm) * Fmath.sinh(this.expTermOver2 * psi * this.chargeValue);
/* 1046 */     if (sigmaHigh > 0.0D) {
/* 1047 */       sigmaHigh += sigmaAdsPos;
/* 1048 */       sigmaLow -= sigmaAdsNeg;
/*      */     }
/*      */     else {
/* 1051 */       sigmaHigh -= sigmaAdsNeg;
/* 1052 */       sigmaLow += sigmaAdsPos;
/*      */     }
/* 1054 */     System.out.println("pp0 " + sigmaLow + " " + psi + " " + ionSum + " " + ionSum * this.eightTerm);
/* 1055 */     double sFuncLow = sigmaFunction2(sigmaLow, psi);
/* 1056 */     System.out.println("pp1 " + sigmaHigh + " " + psi + " " + ionSum + " " + this.chargeValue + " " + Math.sqrt(ionSum * this.eightTerm) + " " + this.expTermOver2 * psi * this.chargeValue + " " + Fmath.sinh(this.expTermOver2 * psi * this.chargeValue));
/* 1057 */     double sFuncHigh = sigmaFunction2(sigmaHigh, psi);
/* 1058 */     if (sFuncHigh * sFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1059 */     double check = Math.abs(sigmaHigh) * 1.0E-6D;
/* 1060 */     boolean test = true;
/* 1061 */     double sigmaMid = 0.0D;
/* 1062 */     double sFuncMid = 0.0D;
/* 1063 */     int nIter = 0;
/* 1064 */     while (test) {
/* 1065 */       sigmaMid = (sigmaLow + sigmaHigh) / 2.0D;
/* 1066 */       sFuncMid = sigmaFunction2(sigmaMid, psi);
/* 1067 */       if (Math.abs(sFuncMid) <= check) {
/* 1068 */         surCharDen = sigmaMid;
/* 1069 */         test = false;
/*      */       }
/*      */       else {
/* 1072 */         nIter++;
/* 1073 */         if (nIter > 10000) {
/* 1074 */           System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity2\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
/* 1075 */           surCharDen = sigmaMid;
/* 1076 */           test = false;
/*      */ 
/*      */         }
/* 1079 */         else if (sFuncLow * sFuncMid > 0.0D) {
/* 1080 */           sigmaLow = sigmaMid;
/* 1081 */           sFuncLow = sFuncMid;
/*      */         }
/*      */         else {
/* 1084 */           sigmaHigh = sigmaMid;
/* 1085 */           sFuncHigh = sFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1091 */     return surCharDen;
/*      */   }
/*      */   
/*      */   private double sigmaFunction2(double sigma, double psi)
/*      */   {
/* 1096 */     System.out.println("F2begin");
/*      */     
/* 1098 */     double psiLow = -10.0D * psi;
/* 1099 */     double pFuncLow = psiFunctionQ(psiLow, psi, sigma);
/* 1100 */     double psiHigh = 10.0D * psi;
/* 1101 */     double pFuncHigh = psiFunctionQ(psiHigh, psi, sigma);
/* 1102 */     System.out.println("qq " + pFuncLow + " " + pFuncHigh + " " + psiLow + " " + psiHigh);
/* 1103 */     if (pFuncHigh * pFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1104 */     double check = Math.abs(psi) * 1.0E-6D;
/* 1105 */     boolean test = true;
/* 1106 */     double psiMid = 0.0D;
/* 1107 */     double pFuncMid = 0.0D;
/* 1108 */     int nIter = 0;
/* 1109 */     while (test) {
/* 1110 */       psiMid = (psiLow + psiHigh) / 2.0D;
/* 1111 */       pFuncMid = psiFunctionQ(psiMid, psi, sigma);
/* 1112 */       if (Math.abs(pFuncMid) <= check) {
/* 1113 */         test = false;
/*      */       }
/*      */       else {
/* 1116 */         nIter++;
/* 1117 */         if (nIter > 10000) {
/* 1118 */           System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity3\nnumber of iterations exceeded in inner bisection\ncurrent value of sigma returned");
/* 1119 */           test = false;
/*      */         }
/* 1121 */         if (pFuncMid * pFuncHigh > 0.0D) {
/* 1122 */           psiHigh = psiMid;
/* 1123 */           pFuncHigh = pFuncMid;
/*      */         }
/*      */         else {
/* 1126 */           psiLow = psiMid;
/* 1127 */           pFuncLow = pFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1132 */     double sigmaEst = 0.0D;
/* 1133 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1134 */       sigmaEst += this.bulkConcn[i];
/*      */     }
/* 1136 */     sigmaEst = Math.sqrt(this.eightTerm * sigmaEst / 2.0D) * Fmath.sinh(this.expTermOver2 * psi * this.chargeValue);
/* 1137 */     System.out.println("F2end");
/*      */     
/* 1139 */     return sigma + this.adsorbedChargeDensity - sigmaEst;
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfaceChargeDensity3(double psi)
/*      */   {
/* 1145 */     double surCharDen = 0.0D;
/*      */     
/*      */ 
/* 1148 */     double sigmaAdsPos = 0.0D;
/* 1149 */     double sigmaAdsNeg = 0.0D;
/* 1150 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1151 */       if (this.charges[this.indexK[i]] > 0.0D) {
/* 1152 */         sigmaAdsPos = this.surfaceSiteDensity;
/*      */       }
/*      */       else {
/* 1155 */         sigmaAdsNeg = -this.surfaceSiteDensity;
/*      */       }
/*      */     }
/*      */     
/* 1159 */     double sigmaLow = 0.0D;
/* 1160 */     double sigmaHigh = 0.0D;
/* 1161 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1162 */       sigmaHigh += this.bulkConcn[i] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[i] * psi) - 1.0D);
/*      */     }
/* 1164 */     sigmaHigh = Fmath.sign(psi) * Math.sqrt(sigmaHigh);
/* 1165 */     if (sigmaHigh > 0.0D) {
/* 1166 */       sigmaHigh += sigmaAdsPos;
/* 1167 */       sigmaLow -= sigmaAdsNeg;
/*      */     }
/*      */     else {
/* 1170 */       sigmaHigh -= sigmaAdsNeg;
/* 1171 */       sigmaLow += sigmaAdsPos;
/*      */     }
/* 1173 */     double sFuncLow = sigmaFunction3(sigmaLow, psi);
/* 1174 */     double sFuncHigh = sigmaFunction3(sigmaHigh, psi);
/* 1175 */     if (sFuncHigh * sFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1176 */     double check = Math.abs(sigmaHigh) * 1.0E-6D;
/* 1177 */     boolean test = true;
/* 1178 */     double sigmaMid = 0.0D;
/* 1179 */     double sFuncMid = 0.0D;
/* 1180 */     int nIter = 0;
/* 1181 */     while (test) {
/* 1182 */       sigmaMid = (sigmaLow + sigmaHigh) / 2.0D;
/* 1183 */       sFuncMid = sigmaFunction3(sigmaMid, psi);
/* 1184 */       if (Math.abs(sFuncMid) <= check) {
/* 1185 */         surCharDen = sigmaMid;
/* 1186 */         test = false;
/*      */       }
/*      */       else {
/* 1189 */         nIter++;
/* 1190 */         if (nIter > 10000) {
/* 1191 */           System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity3\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
/* 1192 */           surCharDen = sigmaMid;
/* 1193 */           test = false;
/*      */ 
/*      */         }
/* 1196 */         else if (sFuncLow * sFuncMid > 0.0D) {
/* 1197 */           sigmaLow = sigmaMid;
/* 1198 */           sFuncLow = sFuncMid;
/*      */         }
/*      */         else {
/* 1201 */           sigmaHigh = sigmaMid;
/* 1202 */           sFuncHigh = sFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1208 */     return surCharDen;
/*      */   }
/*      */   
/*      */ 
/*      */   private double sigmaFunction3(double sigma, double psi)
/*      */   {
/* 1214 */     double psiLow = 0.0D;
/* 1215 */     double pFuncLow = psiFunctionQ(psiLow, psi, sigma);
/* 1216 */     double psiHigh = psi;
/* 1217 */     double pFuncHigh = psiFunctionQ(psiHigh, psi, sigma);
/* 1218 */     if (pFuncHigh * pFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1219 */     double check = Math.abs(psi) * 1.0E-6D;
/* 1220 */     boolean test = true;
/* 1221 */     double psiMid = 0.0D;
/* 1222 */     double pFuncMid = 0.0D;
/* 1223 */     int nIter = 0;
/* 1224 */     while (test) {
/* 1225 */       psiMid = (psiLow + psiHigh) / 2.0D;
/* 1226 */       pFuncMid = psiFunctionQ(psiMid, psi, sigma);
/* 1227 */       if (Math.abs(pFuncMid) <= check) {
/* 1228 */         test = false;
/*      */       }
/*      */       else {
/* 1231 */         nIter++;
/* 1232 */         if (nIter > 10000) {
/* 1233 */           System.out.println("Class: GouyChapmanStern\nMethod: sigmaFunction3\nnumber of iterations exceeded in inner bisection\ncurrent value of sigma returned");
/* 1234 */           test = false;
/*      */         }
/* 1236 */         if (pFuncMid * pFuncHigh > 0.0D) {
/* 1237 */           psiHigh = psiMid;
/* 1238 */           pFuncHigh = pFuncMid;
/*      */         }
/*      */         else {
/* 1241 */           psiLow = psiMid;
/* 1242 */           pFuncLow = pFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1247 */     double sigmaEst = 0.0D;
/* 1248 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1249 */       sigmaEst += this.bulkConcn[i] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[i] * psiMid) - 1.0D);
/*      */     }
/* 1251 */     sigmaEst = Fmath.sign(psiMid) * Math.sqrt(sigmaEst);
/*      */     
/* 1253 */     return sigma + this.adsorbedChargeDensity - sigmaEst;
/*      */   }
/*      */   
/*      */ 
/*      */   private double psiFunctionQ(double psiDelta, double psi0, double sigma)
/*      */   {
/* 1259 */     this.sternDelta = calcDeltaQ(psiDelta);
/* 1260 */     this.diffPotential = psiDelta;
/* 1261 */     this.sternCap = (this.epsilonStern * 8.854187817E-12D / this.sternDelta);
/* 1262 */     System.out.println("aaa " + psiDelta + " " + psi0 + " " + sigma / this.sternCap + " " + sigma);
/* 1263 */     return psiDelta - psi0 + sigma / this.sternCap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private double calcDeltaQ(double psi)
/*      */   {
/* 1270 */     double numer = 0.0D;
/* 1271 */     double denom = 0.0D;
/* 1272 */     double convFac = this.surfaceArea / this.volume;
/* 1273 */     int ii = 0;
/*      */     
/*      */ 
/* 1276 */     if (this.nonZeroAssocK == 1) {
/* 1277 */       ii = this.indexK[0];
/* 1278 */       double hold = this.assocConsts[ii] * Math.exp(-this.charges[ii] * psi * this.expTerm);
/* 1279 */       double aa = hold * convFac;
/* 1280 */       double bb = -(1.0D + this.initConcn[ii] * hold + this.surfaceSiteDensity * hold * convFac);
/* 1281 */       double cc = this.initConcn[ii] * this.surfaceSiteDensity * hold;
/*      */       
/* 1283 */       double root = bb * bb - 4.0D * aa * cc;
/* 1284 */       if (root < 0.0D) {
/* 1285 */         System.out.println("Class: GouyChapmanStern\nMethod: calcDeltaQ\nthe square root term (b2-4ac) of the quadratic = " + root);
/* 1286 */         System.out.println("this term was set to zero as the negative value MAY have arisen from rounding errors");
/* 1287 */         root = 0.0D;
/*      */       }
/* 1289 */       double qq = -0.5D * (bb + Fmath.sign(bb) * Math.sqrt(root));
/* 1290 */       double root1 = qq / aa;
/* 1291 */       double root2 = cc / qq;
/* 1292 */       double limit = this.surfaceSiteDensity * 1.001D;
/*      */       
/* 1294 */       if ((root1 >= 0.0D) && (root1 <= limit)) {
/* 1295 */         if ((root2 < 0.0D) || (root2 > limit)) {
/* 1296 */           this.siteConcn[this.indexK[0]] = root1;
/* 1297 */           this.bulkConcn[this.indexK[0]] = (this.initConcn[this.indexK[0]] - this.siteConcn[this.indexK[0]] * this.surfaceArea / this.volume);
/*      */         }
/*      */         else {
/* 1300 */           System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
/* 1301 */           System.out.println("error1: no physically meaningfull root");
/* 1302 */           System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1303 */           System.exit(0);
/*      */         }
/*      */         
/*      */       }
/* 1307 */       else if ((root2 >= 0.0D) && (root2 <= limit)) {
/* 1308 */         if ((root1 < 0.0D) || (root1 > limit)) {
/* 1309 */           this.siteConcn[this.indexK[0]] = root2;
/* 1310 */           this.bulkConcn[this.indexK[0]] = (this.initConcn[this.indexK[0]] - this.siteConcn[this.indexK[0]] * this.surfaceArea / this.volume);
/*      */         }
/*      */         else {
/* 1313 */           System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
/* 1314 */           System.out.println("error2: no physically meaningfull root");
/* 1315 */           System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1316 */           System.exit(0);
/*      */         }
/*      */       }
/*      */       else {
/* 1320 */         System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
/* 1321 */         System.out.println("error3: no physically meaningfull root");
/* 1322 */         System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 1323 */         System.exit(0);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1332 */       double[] vec = new double[this.nonZeroAssocK];
/* 1333 */       double[][] mat = new double[this.nonZeroAssocK][this.nonZeroAssocK];
/*      */       
/*      */ 
/* 1336 */       double expPsiTerm = -psi * this.expTerm;
/* 1337 */       for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1338 */         ii = this.indexK[i];
/* 1339 */         vec[i] = (this.assocConsts[ii] * this.initConcn[ii] * this.surfaceSiteDensity * Math.exp(this.charges[ii] * expPsiTerm));
/* 1340 */         for (int j = 0; j < this.nonZeroAssocK; j++) {
/* 1341 */           mat[i][j] = (this.assocConsts[ii] * this.initConcn[ii] * Math.exp(this.charges[ii] * expPsiTerm));
/* 1342 */           if (i == j) { mat[i][j] += 1.0D;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1347 */       Matrix matrix = new Matrix(mat);
/* 1348 */       vec = matrix.solveLinearSet(vec);
/* 1349 */       for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1350 */         this.siteConcn[this.indexK[i]] = vec[i];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1355 */     Minimisation min = new Minimisation();
/* 1356 */     GCSminim functA = new GCSminim();
/*      */     
/*      */ 
/* 1359 */     functA.psiDelta = psi;
/* 1360 */     functA.tempK = this.tempK;
/* 1361 */     functA.surfaceSiteDensity = this.surfaceSiteDensity;
/* 1362 */     functA.surfaceArea = this.surfaceArea;
/* 1363 */     functA.volume = this.volume;
/* 1364 */     functA.nonZeroAssocK = this.nonZeroAssocK;
/* 1365 */     functA.assocK = this.assocConsts;
/* 1366 */     functA.initConcn = this.initConcn;
/* 1367 */     functA.charges = this.charges;
/* 1368 */     functA.indexK = this.indexK;
/*      */     
/*      */ 
/* 1371 */     double[] start = new double[this.nonZeroAssocK];
/*      */     
/* 1373 */     double[] step = new double[this.nonZeroAssocK];
/* 1374 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1375 */       start[i] = (this.surfaceSiteDensity / this.nonZeroAssocK);
/* 1376 */       start[i] *= 0.05D;
/*      */     }
/*      */     
/*      */ 
/* 1380 */     double tolerance = this.surfaceSiteDensity * 1.0E-8D;
/* 1381 */     int maxIter = 100000;
/*      */     
/*      */ 
/* 1384 */     min.nelderMead(functA, start, step, tolerance, maxIter);
/*      */     
/*      */ 
/* 1387 */     double[] param = min.getParamValues();
/* 1388 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 1389 */       ii = this.indexK[i];
/* 1390 */       this.siteConcn[ii] = param[i];
/* 1391 */       this.bulkConcn[ii] = (this.initConcn[ii] - param[i] * this.surfaceArea / this.volume);
/*      */     }
/*      */     
/*      */ 
/* 1395 */     this.adsorbedChargeDensity = 0.0D;
/* 1396 */     double factor1 = 96485.34158524018D;
/* 1397 */     double factor2 = this.surfaceArea / this.volume;
/* 1398 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1399 */       this.sternConcn[i] = (this.bulkConcn[i] * Math.exp(-this.charges[i] * this.expTerm));
/* 1400 */       this.adsorbedChargeDensity += this.siteConcn[i] * this.charges[i] * factor1;
/* 1401 */       numer += (this.sternConcn[i] + this.siteConcn[i] * factor2) * this.radii[i];
/* 1402 */       denom += this.sternConcn[i] + this.siteConcn[i] * factor2;
/*      */     }
/*      */     
/* 1405 */     double delta = numer / denom;
/*      */     
/* 1407 */     return delta;
/*      */   }
/*      */   
/*      */   public double getAdsorbedChargeDensity()
/*      */   {
/* 1412 */     if ((!this.sternOption) || (this.nonZeroAssocK == 0)) {
/* 1413 */       return 0.0D;
/*      */     }
/*      */     
/* 1416 */     if ((this.psi0set) && (this.sigmaSet)) {
/* 1417 */       return this.adsorbedChargeDensity;
/*      */     }
/*      */     
/* 1420 */     if (this.sigmaSet) {
/* 1421 */       getSurfacePotential();
/* 1422 */       return this.sternPotential;
/*      */     }
/*      */     
/* 1425 */     if (this.psi0set) {
/* 1426 */       getSurfaceChargeDensity();
/* 1427 */       return this.adsorbedChargeDensity;
/*      */     }
/*      */     
/* 1430 */     System.out.println("Class: GouyChapmanStern\nMethod: getAdsorbedChargeDensity\nThe value of the adsorbed ion charge density has not been calculated\nzero returned");
/* 1431 */     System.out.println("Neither a surface potential nor a surface charge density have been entered");
/* 1432 */     return 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDiffuseChargeDensity()
/*      */   {
/* 1440 */     double ads = getAdsorbedChargeDensity();
/* 1441 */     this.diffuseChargeDensity = (-(this.surfaceChargeDensity + ads));
/* 1442 */     return this.diffuseChargeDensity;
/*      */   }
/*      */   
/*      */   public double getSurfacePotential(double sigma)
/*      */   {
/* 1447 */     this.surfacePotential = calcSurfacePotential(sigma);
/* 1448 */     this.psi0set = true;
/* 1449 */     return this.surfacePotential;
/*      */   }
/*      */   
/*      */   private double calcSurfacePotential(double sigma)
/*      */   {
/* 1454 */     double surPot = 0.0D;
/*      */     
/* 1456 */     if (!this.epsilonSet) throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
/* 1457 */     if (!this.tempSet) { System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
/*      */     }
/* 1459 */     if ((this.psi0set) && (this.sigmaSet)) { return this.surfacePotential;
/*      */     }
/* 1461 */     if (!this.unpackArrayList) { unpack();
/*      */     }
/* 1463 */     if (this.sternOption)
/*      */     {
/* 1465 */       if (this.nonZeroAssocK == 0)
/*      */       {
/* 1467 */         if (this.chargeSame)
/*      */         {
/* 1469 */           this.diffPotential = (Fmath.asinh(sigma / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2));
/*      */         }
/*      */         else
/*      */         {
/* 1473 */           this.diffPotential = surfacePotential1(sigma);
/*      */         }
/* 1475 */         this.sternCap = (8.854187817E-12D * this.epsilonStern / calcDelta(this.diffPotential));
/* 1476 */         surPot = this.diffPotential + sigma / this.sternCap;
/* 1477 */         this.totalCap = (sigma / this.surfacePotential);
/* 1478 */         this.diffCap = (sigma / this.diffPotential);
/*      */ 
/*      */ 
/*      */       }
/* 1482 */       else if (this.chargeSame)
/*      */       {
/* 1484 */         surPot = surfacePotential2(sigma);
/*      */       }
/*      */       else
/*      */       {
/* 1488 */         surPot = surfacePotential3(sigma);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1495 */       if (this.chargeSame)
/*      */       {
/* 1497 */         double preterm = Math.sqrt(this.eightTerm * this.electrolyteConcn);
/* 1498 */         this.surfacePotential = (Fmath.asinh(this.surfaceChargeDensity / preterm) / (this.chargeValue * this.expTermOver2));
/*      */       }
/*      */       else
/*      */       {
/* 1502 */         surPot = surfacePotential4(sigma);
/*      */       }
/* 1504 */       this.diffPotential = surPot;
/* 1505 */       this.sternPotential = 0.0D;
/* 1506 */       this.totalCap = (sigma / surPot);
/* 1507 */       this.diffCap = (sigma / this.diffPotential);
/* 1508 */       this.sternCap = Double.POSITIVE_INFINITY;
/*      */     }
/*      */     
/* 1511 */     return surPot;
/*      */   }
/*      */   
/*      */   public double getSurfacePotential()
/*      */   {
/* 1516 */     if (!this.sigmaSet) throw new IllegalArgumentException("No surface charge density has been entered");
/* 1517 */     return getSurfacePotential(this.surfaceChargeDensity);
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfacePotential4(double sigma)
/*      */   {
/* 1523 */     double surPot = 0.0D;
/*      */     
/*      */ 
/* 1526 */     double psiLow = 0.0D;
/* 1527 */     double pFuncLow = psiFunction4(psiLow, sigma);
/* 1528 */     double asinhDenom = Math.sqrt(this.eightTerm * this.electrolyteConcn);
/* 1529 */     double psiHigh = 10.0D / (this.averageCharge * this.expTerm) * Fmath.asinh(sigma / asinhDenom);
/* 1530 */     double pFuncHigh = psiFunction4(psiHigh, sigma);
/* 1531 */     if (pFuncHigh * pFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1532 */     double check = Math.abs(psiHigh) * 1.0E-6D;
/* 1533 */     boolean test = true;
/* 1534 */     double psiMid = 0.0D;
/* 1535 */     double pFuncMid = 0.0D;
/* 1536 */     int nIter = 0;
/* 1537 */     while (test) {
/* 1538 */       psiMid = (psiLow + psiHigh) / 2.0D;
/* 1539 */       pFuncMid = psiFunction4(psiMid, sigma);
/* 1540 */       if (Math.abs(pFuncMid) <= check) {
/* 1541 */         surPot = psiMid;
/* 1542 */         test = false;
/*      */       }
/*      */       else {
/* 1545 */         nIter++;
/* 1546 */         if (nIter > 10000) {
/* 1547 */           System.out.println("Class: GouyChapmanStern\nMethod: getSurfacePotential\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
/* 1548 */           surPot = psiMid;
/* 1549 */           test = false;
/*      */ 
/*      */         }
/* 1552 */         else if (pFuncLow * pFuncMid > 0.0D) {
/* 1553 */           psiLow = psiMid;
/* 1554 */           pFuncLow = pFuncMid;
/*      */         }
/*      */         else {
/* 1557 */           psiHigh = psiMid;
/* 1558 */           pFuncHigh = pFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1563 */     return surPot;
/*      */   }
/*      */   
/*      */   private double psiFunction4(double psi, double sigma)
/*      */   {
/* 1568 */     double sigmaEst = 0.0D;
/* 1569 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1570 */       sigmaEst += this.bulkConcn[i] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[i] * psi) - 1.0D);
/*      */     }
/* 1572 */     sigmaEst = Fmath.sign(sigma) * Math.sqrt(sigmaEst);
/*      */     
/* 1574 */     return sigma - sigmaEst;
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfacePotential1(double sigma)
/*      */   {
/* 1580 */     double difPot = 0.0D;
/*      */     
/*      */ 
/* 1583 */     double psiLow = 0.0D;
/* 1584 */     double pFuncLow = psiFunction1(psiLow, sigma);
/* 1585 */     double psiHigh = 5.0D / (this.expTerm * this.chargeValue) * Fmath.asinh(sigma / Math.sqrt(this.eightTerm * this.electrolyteConcn));
/* 1586 */     double pFuncHigh = psiFunction1(psiHigh, sigma);
/* 1587 */     if (pFuncHigh * pFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1588 */     double check = Math.abs(psiHigh) * 1.0E-6D;
/* 1589 */     boolean test = true;
/* 1590 */     double psiMid = 0.0D;
/* 1591 */     double pFuncMid = 0.0D;
/* 1592 */     int nIter = 0;
/* 1593 */     while (test) {
/* 1594 */       psiMid = (psiLow + psiHigh) / 2.0D;
/* 1595 */       pFuncMid = psiFunction1(psiMid, sigma);
/* 1596 */       if (Math.abs(pFuncMid) <= check) {
/* 1597 */         this.diffPotential = psiMid;
/* 1598 */         test = false;
/*      */       }
/*      */       else {
/* 1601 */         nIter++;
/* 1602 */         if (nIter > 10000) {
/* 1603 */           System.out.println("Class: GouyChapmanStern\nMethod: getSurfacePotential\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
/* 1604 */           this.diffPotential = psiMid;
/* 1605 */           test = false;
/*      */ 
/*      */         }
/* 1608 */         else if (pFuncLow * pFuncMid > 0.0D) {
/* 1609 */           psiLow = psiMid;
/* 1610 */           pFuncLow = pFuncMid;
/*      */         }
/*      */         else {
/* 1613 */           psiHigh = psiMid;
/* 1614 */           pFuncHigh = pFuncMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1620 */     return difPot;
/*      */   }
/*      */   
/*      */ 
/*      */   private double psiFunction1(double psi, double sigma)
/*      */   {
/* 1626 */     double func = 0.0D;
/* 1627 */     for (int i = 0; i < this.numOfIons; i++) {
/* 1628 */       func += this.twoTerm * this.bulkConcn[i] * (Math.exp(-this.charges[i] * psi * this.expTerm) - 1.0D);
/*      */     }
/* 1630 */     return sigma - Fmath.sign(sigma) * Math.sqrt(func);
/*      */   }
/*      */   
/*      */   public double getPotentialAtX(double xDistance)
/*      */   {
/* 1635 */     if ((!this.psi0set) && (!this.sigmaSet)) throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
/* 1636 */     if ((this.sigmaSet) && (!this.psi0set)) getSurfacePotential();
/* 1637 */     if ((this.psi0set) && (!this.sigmaSet)) { getSurfaceChargeDensity();
/*      */     }
/* 1639 */     double potAtX = 0.0D;
/*      */     
/* 1641 */     if (xDistance == 0.0D)
/*      */     {
/* 1643 */       potAtX = this.surfacePotential;
/*      */ 
/*      */     }
/* 1646 */     else if (this.sternOption)
/*      */     {
/* 1648 */       if (xDistance == this.sternDelta) {
/* 1649 */         potAtX = this.diffPotential;
/*      */ 
/*      */       }
/* 1652 */       else if (xDistance < this.sternDelta)
/*      */       {
/* 1654 */         potAtX = this.surfacePotential - xDistance / this.sternDelta * (this.surfacePotential - this.diffPotential);
/*      */       }
/*      */       else
/*      */       {
/* 1658 */         potAtX = calcPotAtX(this.diffPotential, xDistance);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else {
/* 1664 */       potAtX = calcPotAtX(this.surfacePotential, xDistance);
/*      */     }
/*      */     
/* 1667 */     return potAtX;
/*      */   }
/*      */   
/*      */   private double calcPotAtX(double psiL, double xDistance)
/*      */   {
/* 1672 */     double potAtX = 0.0D;
/* 1673 */     if (this.chargeSame)
/*      */     {
/* 1675 */       double kappa = Math.sqrt(2.0D * Fmath.square(-1.60217646263E-19D * this.chargeValue) * 6.0221419947E23D * this.electrolyteConcn / (8.854187817E-12D * this.epsilon * 1.380650324E-23D * this.tempK));
/* 1676 */       double expPart = Math.exp(this.expTerm * this.chargeValue * psiL / 2.0D);
/* 1677 */       double gamma = (expPart - 1.0D) / (expPart + 1.0D);
/* 1678 */       double gammaExp = gamma * Math.exp(-kappa * xDistance);
/* 1679 */       potAtX = 2.0D * Math.log((1.0D + gammaExp) / (1.0D - gammaExp)) / (this.expTerm * this.chargeValue);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1684 */       FunctionPatX func = new FunctionPatX();
/* 1685 */       func.numOfIons = this.numOfIons;
/* 1686 */       func.termOne = (16.628944592313125D * this.tempK / (8.854187817E-12D * this.epsilon));
/* 1687 */       func.expTerm = this.expTerm;
/* 1688 */       func.bulkConcn = this.bulkConcn;
/* 1689 */       func.charges = this.charges;
/* 1690 */       int nPointsGQ = 2000;
/*      */       
/*      */ 
/* 1693 */       double psiXlow = 0.0D;
/* 1694 */       double pFuncLow = xDistance - Integration.trapezium(func, psiXlow, psiL, nPointsGQ);
/* 1695 */       double psiXhigh = psiL;
/* 1696 */       double pFuncHigh = xDistance - Integration.trapezium(func, psiXhigh, psiL, nPointsGQ);
/* 1697 */       if (pFuncHigh * pFuncLow > 0.0D) throw new IllegalArgumentException("root not bounded");
/* 1698 */       double check = Math.abs(xDistance) * 0.01D;
/* 1699 */       boolean test = true;
/* 1700 */       double psiXmid = 0.0D;
/* 1701 */       double pFuncMid = 0.0D;
/* 1702 */       int nIter = 0;
/*      */       
/* 1704 */       while (test) {
/* 1705 */         psiXmid = (psiXlow + psiXhigh) / 2.0D;
/* 1706 */         pFuncMid = xDistance - Integration.trapezium(func, psiXmid, psiL, nPointsGQ);
/* 1707 */         if (Math.abs(pFuncMid) <= check) {
/* 1708 */           potAtX = psiXmid;
/* 1709 */           test = false;
/*      */         }
/*      */         else {
/* 1712 */           nIter++;
/* 1713 */           if (nIter > 10000) {
/* 1714 */             System.out.println("Class: GouyChapmanStern\nMethod: getPotentialAtX\nnumber of iterations exceeded in outer bisection\ncurrent value of psi(x) returned");
/* 1715 */             potAtX = psiXmid;
/* 1716 */             test = false;
/*      */ 
/*      */           }
/* 1719 */           else if (pFuncLow * pFuncMid > 0.0D) {
/* 1720 */             psiXlow = psiXmid;
/* 1721 */             pFuncLow = pFuncMid;
/*      */           }
/*      */           else {
/* 1724 */             psiXhigh = psiXmid;
/* 1725 */             pFuncHigh = pFuncMid;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1731 */     return potAtX;
/*      */   }
/*      */   
/*      */   public double[] getConcnsAtX(double xDistance)
/*      */   {
/* 1736 */     if ((!this.psi0set) && (!this.sigmaSet)) throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
/* 1737 */     if ((this.sigmaSet) && (!this.psi0set)) getSurfacePotential();
/* 1738 */     if ((this.psi0set) && (!this.sigmaSet)) { getSurfaceChargeDensity();
/*      */     }
/* 1740 */     double[] conc = new double[this.numOfIons];
/* 1741 */     if ((this.sternOption) && (xDistance < this.sternDelta)) {
/* 1742 */       for (int i = 0; i < this.numOfIons; i++) conc[i] = 0.0D;
/*      */     }
/*      */     else {
/* 1745 */       double psi = getPotentialAtX(xDistance);
/* 1746 */       for (int i = 0; i < this.numOfIons; i++) conc[i] = (this.bulkConcn[i] * Math.exp(-this.expTerm * this.charges[i] * psi));
/*      */     }
/* 1748 */     return conc;
/*      */   }
/*      */   
/*      */   public double[] getInitConcns()
/*      */   {
/* 1753 */     if ((!this.psi0set) && (!this.sigmaSet)) unpack();
/* 1754 */     double[] conc = (double[])this.initConcn.clone();
/* 1755 */     for (int i = 0; i < this.numOfIons; i++) conc[i] *= 0.001D;
/* 1756 */     return conc;
/*      */   }
/*      */   
/*      */   public double[] getBulkConcns()
/*      */   {
/* 1761 */     if ((!this.psi0set) && (!this.sigmaSet)) throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
/* 1762 */     if ((this.sigmaSet) && (!this.psi0set)) getSurfacePotential();
/* 1763 */     if ((this.psi0set) && (!this.sigmaSet)) { getSurfaceChargeDensity();
/*      */     }
/* 1765 */     double[] conc = (double[])this.bulkConcn.clone();
/* 1766 */     for (int i = 0; i < this.numOfIons; i++) conc[i] *= 0.001D;
/* 1767 */     return conc;
/*      */   }
/*      */   
/*      */   public double[] getSiteConcns()
/*      */   {
/* 1772 */     if ((!this.psi0set) && (!this.sigmaSet)) throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
/* 1773 */     if ((this.sigmaSet) && (!this.psi0set)) getSurfacePotential();
/* 1774 */     if ((this.psi0set) && (!this.sigmaSet)) { getSurfaceChargeDensity();
/*      */     }
/* 1776 */     return this.siteConcn;
/*      */   }
/*      */   
/*      */ 
/*      */   private double surfacePotential2(double sigma)
/*      */   {
/* 1782 */     double surPot = 0.0D;
/*      */     
/*      */ 
/*      */ 
/* 1786 */     double diffPot = Fmath.asinh(sigma / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2);
/*      */     
/*      */ 
/* 1789 */     double pLow = 0.0D;
/* 1790 */     double pHigh = 0.0D;
/* 1791 */     if (diffPot > 0.0D) {
/* 1792 */       pHigh = 2.0D * diffPot;
/*      */     }
/*      */     else {
/* 1795 */       pLow = 2.0D * diffPot;
/*      */     }
/*      */     
/* 1798 */     surPot = surfacePotentialBisection(pLow, pHigh, sigma, 2);
/*      */     
/* 1800 */     return surPot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private double surfacePotentialBisection(double pLow, double pHigh, double sigma, int n)
/*      */   {
/* 1807 */     double surPot = 0.0D;
/*      */     
/*      */ 
/* 1810 */     boolean testC = true;
/* 1811 */     int testCiter = 0;
/* 1812 */     int testCmax = 10;
/* 1813 */     double pDiff = pHigh - pLow;
/* 1814 */     double cLow = cFunction(pLow, sigma);
/* 1815 */     double cHigh = cFunction(pHigh, sigma);
/* 1816 */     while (testC) {
/* 1817 */       if (pHigh * pLow > 0.0D) {
/* 1818 */         testCiter++;
/* 1819 */         if (testCiter > testCmax) throw new IllegalArgumentException("root not bounded after " + testCiter + "expansions");
/* 1820 */         pLow -= pDiff;
/* 1821 */         pHigh += pDiff;
/* 1822 */         cLow = cFunction(pLow, sigma);
/* 1823 */         cHigh = cFunction(pHigh, sigma);
/*      */       }
/*      */       else {
/* 1826 */         testC = false;
/*      */       }
/*      */     }
/* 1829 */     double check = Math.abs(sigma) * 1.0E-6D;
/* 1830 */     boolean test = true;
/* 1831 */     double cMid = 0.0D;
/* 1832 */     int nIter = 0;
/* 1833 */     while (test) {
/* 1834 */       surPot = (pLow + pHigh) / 2.0D;
/* 1835 */       cMid = cFunction(surPot, sigma);
/* 1836 */       if (Math.abs(cMid) <= check) {
/* 1837 */         test = false;
/*      */       }
/*      */       else {
/* 1840 */         nIter++;
/* 1841 */         if (nIter > 10000) {
/* 1842 */           System.out.println("Class: GouyChapmanStern\nMethod: surfacePotential" + n + "\nnumber of iterations exceeded in bisection\ncurrent value of sigma returned");
/* 1843 */           test = false;
/*      */         }
/* 1845 */         if (cMid * cHigh > 0.0D) {
/* 1846 */           pHigh = surPot;
/* 1847 */           cHigh = cMid;
/*      */         }
/*      */         else {
/* 1850 */           pLow = surPot;
/* 1851 */           cLow = cMid;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1856 */     return surPot;
/*      */   }
/*      */   
/*      */   private double cFunction(double psi, double sigma)
/*      */   {
/* 1861 */     double sigmaEst = calcSurfaceChargeDensity(psi);
/* 1862 */     return sigmaEst - sigma;
/*      */   }
/*      */   
/*      */   private double surfacePotential3(double sigma)
/*      */   {
/* 1867 */     double surPot = 0.0D;
/*      */     
/*      */ 
/*      */ 
/* 1871 */     double diffPot = Fmath.asinh(sigma / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2);
/*      */     
/*      */ 
/* 1874 */     double pLow = 0.0D;
/* 1875 */     double pHigh = 0.0D;
/* 1876 */     if (diffPot > 0.0D) {
/* 1877 */       pHigh = 2.0D * diffPot;
/*      */     }
/*      */     else {
/* 1880 */       pLow = 2.0D * diffPot;
/*      */     }
/*      */     
/* 1883 */     surPot = surfacePotentialBisection(pLow, pHigh, sigma, 3);
/*      */     
/* 1885 */     return surPot;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/GouyChapmanStern.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */