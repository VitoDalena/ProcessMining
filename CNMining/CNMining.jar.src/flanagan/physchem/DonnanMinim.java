/*     */ package flanagan.physchem;
/*     */ 
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.math.Minimisation;
/*     */ import flanagan.math.MinimisationFunction;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DonnanMinim
/*     */   implements MinimisationFunction
/*     */ {
/*  49 */   public int numOfIons = 0;
/*  50 */   public double[] concnA = null;
/*  51 */   public double[] concnB = null;
/*  52 */   public double[] molesT = null;
/*  53 */   public double[] complex = null;
/*  54 */   public double[] excessConcnA = null;
/*  55 */   public double[] excessConcnB = null;
/*  56 */   public double[] excessComplex = null;
/*  57 */   public double[] assocConsts = null;
/*  58 */   public int[] indexK = null;
/*  59 */   public int nonZeroAssocK = 0;
/*  60 */   public double[] radii = null;
/*  61 */   public double[] charges = null;
/*  62 */   public double ionophoreConcn = 0.0D;
/*  63 */   public double ionophoreRad = 0.0D;
/*  64 */   public double volumeA = 0.0D;
/*  65 */   public double volumeB = 0.0D;
/*  66 */   public double interfacialArea = 0.0D;
/*  67 */   public double epsilonA = 0.0D;
/*  68 */   public double epsilonB = 0.0D;
/*  69 */   public double epsilonSternA = 0.0D;
/*  70 */   public double epsilonSternB = 0.0D;
/*  71 */   public double temp = 298.15D;
/*  72 */   public double[] partCoeff = null;
/*  73 */   public double[] partCoeffPot = null;
/*  74 */   public double diffPotentialA = 0.0D;
/*  75 */   public double diffPotentialB = 0.0D;
/*  76 */   public double sternPotential = 0.0D;
/*  77 */   public double sternCap = 0.0D;
/*  78 */   public double sternDeltaA = 0.0D;
/*  79 */   public double sternDeltaB = 0.0D;
/*  80 */   public double chargeValue = 0.0D;
/*  81 */   public boolean chargeSame = true;
/*  82 */   public double interfacialChargeDensity = 0.0D;
/*  83 */   public double interfacialCharge = 0.0D;
/*  84 */   public boolean includeIc = true;
/*     */   
/*     */ 
/*  87 */   private double[] start = null;
/*  88 */   private double[] step = null;
/*  89 */   private double[] param = null;
/*     */   
/*     */   public DonnanMinim(int noIons)
/*     */   {
/*  93 */     this.numOfIons = noIons;
/*  94 */     this.start = new double[this.numOfIons];
/*  95 */     this.step = new double[this.numOfIons];
/*  96 */     this.param = new double[this.numOfIons];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double function(double[] x)
/*     */   {
/* 103 */     double chargeBsum = 0.0D;
/*     */     
/*     */ 
/* 106 */     ionConcns(x[0]);
/*     */     
/* 108 */     for (int i = 0; i < this.numOfIons; i++) {
/* 109 */       chargeBsum += (this.concnB[i] + this.complex[i]) * this.charges[i];
/*     */     }
/* 111 */     return chargeBsum * chargeBsum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ionConcns(double potential)
/*     */   {
/* 119 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 120 */       this.partCoeffPot[ii] = (this.partCoeff[ii] * Math.exp(-potential * this.charges[ii] * -1.60217646263E-19D / (1.380650324E-23D * this.temp)));
/*     */     }
/*     */     
/* 123 */     if (!this.includeIc)
/*     */     {
/* 125 */       if (this.nonZeroAssocK < 2)
/*     */       {
/* 127 */         calcConcnsSingleK(potential);
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 134 */         calcConcnsMultiK(potential);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 142 */       calcConcnsMultiK(potential);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcConcnsSingleK(double potential)
/*     */   {
/* 149 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 150 */       if ((this.assocConsts[ii] == 0.0D) || (this.ionophoreConcn == 0.0D)) {
/* 151 */         if (this.molesT[ii] == 0.0D)
/*     */         {
/* 153 */           this.concnB[ii] = 0.0D;
/* 154 */           this.concnA[ii] = 0.0D;
/* 155 */           this.complex[ii] = 0.0D;
/*     */         }
/*     */         else
/*     */         {
/* 159 */           this.concnB[ii] = (this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB));
/* 160 */           this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 161 */           this.complex[ii] = 0.0D;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 168 */         double aa = this.assocConsts[ii] * (this.volumeB + this.volumeA * this.partCoeffPot[ii]);
/* 169 */         double bb = this.volumeB + this.volumeA * this.partCoeffPot[ii] + this.volumeB * this.assocConsts[ii] * this.ionophoreConcn - this.assocConsts[ii] * this.molesT[ii];
/* 170 */         double cc = -this.molesT[ii];
/*     */         
/* 172 */         double root = bb * bb - 4.0D * aa * cc;
/* 173 */         if (root < 0.0D) {
/* 174 */           System.out.println("Class: DonnanMinim\nMethod: ionConcns\nthe square root term (b2-4ac) of the quadratic = " + root);
/* 175 */           System.out.println("this term was set to zero as the negative value MAY have arisen from rounding errors");
/* 176 */           root = 0.0D;
/*     */         }
/* 178 */         double qq = -0.5D * (bb + Fmath.sign(bb) * Math.sqrt(root));
/* 179 */         double root1 = qq / aa;
/* 180 */         double root2 = cc / qq;
/* 181 */         double limit = this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB);
/* 182 */         if ((root1 >= 0.0D) && (root1 <= limit)) {
/* 183 */           if ((root2 < 0.0D) || (root2 > limit)) {
/* 184 */             this.concnB[ii] = root1;
/* 185 */             this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 186 */             this.complex[ii] = (this.assocConsts[ii] * this.ionophoreConcn * this.concnB[ii] / (1.0D + this.assocConsts[ii] * this.concnB[ii]));
/*     */           }
/*     */           else
/*     */           {
/* 190 */             System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 191 */             System.out.println("error1: no physically meaningfull root");
/* 192 */             System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 193 */             System.exit(0);
/*     */           }
/*     */           
/*     */         }
/* 197 */         else if ((root2 >= 0.0D) && (root2 <= limit)) {
/* 198 */           if ((root1 < 0.0D) || (root1 > limit)) {
/* 199 */             this.concnB[ii] = root2;
/* 200 */             this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/*     */             
/* 202 */             this.complex[ii] = (this.assocConsts[ii] * this.ionophoreConcn * this.concnB[ii] / (1.0D + this.assocConsts[ii] * this.concnB[ii]));
/*     */           }
/*     */           else {
/* 205 */             System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 206 */             System.out.println("error2: no physically meaningfull root");
/* 207 */             System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 208 */             System.exit(0);
/*     */           }
/*     */         }
/*     */         else {
/* 212 */           System.out.println("Class: DonnanMinim\nMethod: ionConcns");
/* 213 */           System.out.println("error3: no physically meaningfull root");
/* 214 */           System.out.println("root1 = " + root1 + " root2 = " + root2 + " limit = " + limit);
/* 215 */           System.exit(0);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calcConcnsMultiK(double potential)
/*     */   {
/* 227 */     for (int ii = 0; ii < this.numOfIons; ii++) {
/* 228 */       if (this.molesT[ii] == 0.0D)
/*     */       {
/* 230 */         this.concnB[ii] = 0.0D;
/* 231 */         this.concnA[ii] = 0.0D;
/* 232 */         this.complex[ii] = 0.0D;
/* 233 */         this.excessConcnA[ii] = 0.0D;
/* 234 */         this.excessConcnB[ii] = 0.0D;
/* 235 */         this.excessComplex[ii] = 0.0D;
/*     */       }
/*     */       else
/*     */       {
/* 239 */         this.concnB[ii] = (this.molesT[ii] / (this.volumeA * this.partCoeffPot[ii] + this.volumeB));
/* 240 */         this.concnA[ii] = (this.concnB[ii] * this.partCoeffPot[ii]);
/* 241 */         this.complex[ii] = 0.0D;
/* 242 */         this.excessConcnA[ii] = 0.0D;
/* 243 */         this.excessConcnB[ii] = 0.0D;
/* 244 */         this.excessComplex[ii] = 0.0D;
/*     */       }
/* 246 */       this.start[ii] = this.concnB[ii];
/* 247 */       this.step[ii] = (0.05D * this.start[ii]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 254 */     Minimisation minConcn = new Minimisation();
/*     */     
/*     */ 
/* 257 */     DonnanConcn functC = new DonnanConcn();
/*     */     
/*     */ 
/* 260 */     functC.numOfIons = this.numOfIons;
/* 261 */     functC.concnA = this.concnA;
/* 262 */     functC.concnB = this.concnB;
/* 263 */     functC.molesT = this.molesT;
/* 264 */     functC.complex = this.complex;
/* 265 */     functC.excessConcnA = this.excessConcnA;
/* 266 */     functC.excessConcnB = this.excessConcnB;
/* 267 */     functC.excessComplex = this.excessComplex;
/* 268 */     functC.assocConsts = this.assocConsts;
/* 269 */     functC.indexK = this.indexK;
/* 270 */     functC.nonZeroAssocK = this.nonZeroAssocK;
/* 271 */     functC.radii = this.radii;
/* 272 */     functC.charges = this.charges;
/* 273 */     functC.ionophoreConcn = this.ionophoreConcn;
/* 274 */     functC.ionophoreRad = this.ionophoreRad;
/* 275 */     functC.volumeA = this.volumeA;
/* 276 */     functC.volumeB = this.volumeB;
/* 277 */     functC.interfacialArea = this.interfacialArea;
/* 278 */     functC.epsilonA = this.epsilonA;
/* 279 */     functC.epsilonB = this.epsilonB;
/* 280 */     functC.epsilonSternA = this.epsilonSternA;
/* 281 */     functC.epsilonSternB = this.epsilonSternB;
/* 282 */     functC.temp = this.temp;
/* 283 */     functC.partCoeffPot = this.partCoeffPot;
/* 284 */     functC.sternCap = this.sternCap;
/* 285 */     functC.sternDeltaA = this.sternDeltaA;
/* 286 */     functC.sternDeltaB = this.sternDeltaB;
/* 287 */     functC.chargeValue = this.chargeValue;
/* 288 */     functC.chargeSame = this.chargeSame;
/* 289 */     functC.interfacialCharge = this.interfacialCharge;
/* 290 */     functC.interfacialChargeDensity = this.interfacialChargeDensity;
/* 291 */     functC.potential = potential;
/* 292 */     functC.includeIc = this.includeIc;
/*     */     
/*     */ 
/* 295 */     minConcn.nelderMead(functC, this.start, this.step, 1.0E-20D, 10000);
/*     */     
/*     */ 
/* 298 */     this.param = minConcn.getParamValues();
/*     */     
/* 300 */     for (int i = 0; i < this.numOfIons; i++) {
/* 301 */       this.concnB[i] = this.param[i];
/* 302 */       this.concnA[i] = (this.concnB[i] * this.partCoeffPot[i]);
/*     */     }
/*     */     
/*     */ 
/* 306 */     this.interfacialCharge = functC.interfacialCharge;
/* 307 */     this.interfacialChargeDensity = functC.interfacialChargeDensity;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/DonnanMinim.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */