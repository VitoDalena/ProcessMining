/*     */ package flanagan.physchem;
/*     */ 
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.math.Matrix;
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
/*     */ class DonnanConcn
/*     */   implements MinimisationFunction
/*     */ {
/*  41 */   public int numOfIons = 0;
/*  42 */   public double[] concnA = null;
/*  43 */   public double[] concnB = null;
/*  44 */   public double[] molesT = null;
/*  45 */   public double[] complex = null;
/*  46 */   public double[] excessConcnA = null;
/*  47 */   public double[] excessConcnB = null;
/*  48 */   public double[] excessComplex = null;
/*  49 */   public double[] assocConsts = null;
/*  50 */   public double[] partCoeffPot = null;
/*  51 */   public int[] indexK = null;
/*  52 */   public int nonZeroAssocK = 0;
/*  53 */   public double[] radii = null;
/*  54 */   public double[] charges = null;
/*  55 */   public double ionophoreConcn = 0.0D;
/*  56 */   public double ionophoreRad = 0.0D;
/*  57 */   public double volumeA = 0.0D;
/*  58 */   public double volumeB = 0.0D;
/*  59 */   public double interfacialArea = 0.0D;
/*  60 */   public double epsilonA = 0.0D;
/*  61 */   public double epsilonB = 0.0D;
/*  62 */   public double epsilonSternA = 0.0D;
/*  63 */   public double epsilonSternB = 0.0D;
/*  64 */   public double temp = 298.15D;
/*  65 */   public double diffPotentialA = 0.0D;
/*  66 */   public double diffPotentialB = 0.0D;
/*  67 */   public double sternPotential = 0.0D;
/*  68 */   public double sternCap = 0.0D;
/*  69 */   public double sternDeltaA = 0.0D;
/*  70 */   public double sternDeltaB = 0.0D;
/*  71 */   public double chargeValue = 0.0D;
/*  72 */   public boolean chargeSame = true;
/*  73 */   public double interfacialChargeDensity = 0.0D;
/*  74 */   public double interfacialCharge = 0.0D;
/*  75 */   public boolean includeIc = true;
/*     */   
/*  77 */   public double potential = 0.0D;
/*  78 */   private double penalty = 1.0E50D;
/*     */   
/*     */ 
/*     */ 
/*     */   public double function(double[] x)
/*     */   {
/*  84 */     double sumOfSquares = 0.0D;
/*     */     
/*     */ 
/*  87 */     if ((this.nonZeroAssocK > 0) && (this.ionophoreConcn > 0.0D)) {
/*  88 */       if (this.nonZeroAssocK == 1) {
/*  89 */         this.complex[this.indexK[0]] = (this.assocConsts[this.indexK[0]] * x[this.indexK[0]] * this.ionophoreConcn / (1.0D + this.assocConsts[this.indexK[0]] * x[this.indexK[0]]));
/*     */       }
/*     */       else {
/*  92 */         double[] vec = new double[this.nonZeroAssocK];
/*  93 */         double[][] mat = new double[this.nonZeroAssocK][this.nonZeroAssocK];
/*     */         
/*     */ 
/*  96 */         for (int i = 0; i < this.nonZeroAssocK; i++) {
/*  97 */           vec[i] = (this.assocConsts[this.indexK[i]] * x[this.indexK[i]] * this.ionophoreConcn);
/*  98 */           for (int j = 0; j < this.nonZeroAssocK; j++) {
/*  99 */             mat[i][j] = (this.assocConsts[this.indexK[i]] * x[this.indexK[i]]);
/* 100 */             if (i == j) { mat[i][j] += 1.0D;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 105 */         Matrix matrix = new Matrix(mat);
/* 106 */         vec = matrix.solveLinearSet(vec);
/* 107 */         for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 108 */           this.complex[this.indexK[i]] = vec[i];
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 114 */     if (this.includeIc)
/*     */     {
/* 116 */       double excess = Math.abs(interfaceCharge(x, this.potential));
/*     */       
/*     */ 
/* 119 */       excessConcentrations(x, excess, this.potential);
/*     */       
/*     */ 
/* 122 */       for (int i = 0; i < this.numOfIons; i++) {
/* 123 */         double aa = x[i] * (this.volumeB + this.partCoeffPot[i] * this.volumeA) + this.excessConcnA[i] * this.volumeA + (this.excessConcnB[i] + this.complex[i] + this.excessComplex[i]) * this.volumeB - this.molesT[i];
/* 124 */         sumOfSquares += aa * aa;
/* 125 */         if (x[i] < 0.0D) sumOfSquares += x[i] * x[i] * this.penalty;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 130 */       for (int i = 0; i < this.numOfIons; i++) {
/* 131 */         double aa = x[i] * (this.volumeB + this.partCoeffPot[i] * this.volumeA) + this.complex[i] * this.volumeB - this.molesT[i];
/* 132 */         sumOfSquares += aa * aa;
/* 133 */         if (x[i] < 0.0D) sumOfSquares += x[i] * x[i] * this.penalty;
/*     */       }
/*     */     }
/* 136 */     return sumOfSquares;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void excessConcentrations(double[] x, double excess, double potential)
/*     */   {
/* 143 */     if (potential == 0.0D) {
/* 144 */       for (int i = 0; i < this.numOfIons; i++) {
/* 145 */         this.excessConcnA[i] = 0.0D;
/* 146 */         this.excessConcnB[i] = 0.0D;
/* 147 */         this.excessComplex[i] = 0.0D;
/*     */       }
/*     */     }
/*     */     else {
/* 151 */       double sumA = 0.0D;
/* 152 */       double sumB = 0.0D;
/* 153 */       double sumC = 0.0D;
/* 154 */       for (int i = 0; i < this.numOfIons; i++) {
/* 155 */         if (potential > 0.0D) {
/* 156 */           if (this.charges[i] > 0.0D) {
/* 157 */             sumB += x[i] * Math.abs(this.charges[i]);
/* 158 */             sumC += this.complex[i] * Math.abs(this.charges[i]);
/*     */           }
/*     */           else
/*     */           {
/* 162 */             sumA += x[i] * this.partCoeffPot[i] * Math.abs(this.charges[i]);
/*     */           }
/*     */           
/*     */         }
/* 166 */         else if (this.charges[i] < 0.0D) {
/* 167 */           sumB += x[i] * Math.abs(this.charges[i]);
/* 168 */           sumC += this.complex[i] * Math.abs(this.charges[i]);
/*     */         }
/*     */         else {
/* 171 */           sumA += x[i] * this.partCoeffPot[i] * Math.abs(this.charges[i]);
/*     */         }
/*     */       }
/*     */       
/* 175 */       double factorA = excess / (sumA * this.volumeA);
/* 176 */       double factorB = excess / ((sumB + sumC) * this.volumeB);
/* 177 */       for (int i = 0; i < this.numOfIons; i++) {
/* 178 */         if (potential > 0.0D) {
/* 179 */           if (this.charges[i] > 0.0D) {
/* 180 */             this.excessConcnB[i] = Math.abs(this.concnB[i] * factorB);
/* 181 */             this.excessComplex[i] = Math.abs(this.complex[i] * factorB);
/*     */           }
/*     */           else
/*     */           {
/* 185 */             this.excessConcnA[i] = Math.abs(this.concnA[i] * factorA);
/*     */           }
/*     */           
/*     */         }
/* 189 */         else if (this.charges[i] < 0.0D) {
/* 190 */           this.excessConcnB[i] = Math.abs(this.concnB[i] * factorB);
/* 191 */           this.excessComplex[i] = Math.abs(this.complex[i] * factorB);
/*     */         }
/*     */         else {
/* 194 */           this.excessConcnA[i] = Math.abs(this.concnA[i] * factorA);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double interfaceCharge(double[] ions, double potential)
/*     */   {
/* 205 */     if (potential == 0.0D) {
/* 206 */       this.interfacialCharge = 0.0D;
/* 207 */       this.interfacialChargeDensity = 0.0D;
/* 208 */       this.diffPotentialA = 0.0D;
/* 209 */       this.diffPotentialB = 0.0D;
/* 210 */       this.sternPotential = 0.0D;
/*     */     }
/*     */     else
/*     */     {
/* 214 */       double sigmaM = 0.0D;
/* 215 */       double funcM = 0.0D;
/* 216 */       double sigmaL = 0.0D;double funcL = 0.0D;
/* 217 */       double sumAions = 0.0D;
/* 218 */       double sumBions = 0.0D;
/* 219 */       double aveCharge = 0.0D;
/* 220 */       for (int i = 0; i < this.numOfIons; i++) {
/* 221 */         sumBions += Math.abs(ions[i] * this.charges[i]);
/* 222 */         sumAions += Math.abs(ions[i] * this.charges[i] * this.partCoeffPot[i]);
/* 223 */         aveCharge += Math.abs(this.charges[i]);
/*     */       }
/* 225 */       aveCharge /= this.numOfIons;
/* 226 */       sumBions /= 2.0D * aveCharge;
/* 227 */       sumAions /= 2.0D * aveCharge;
/* 228 */       double maxQ = 1.2D * Math.sqrt(4.81771359576E24D * sumBions * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonB) * Fmath.sinh(-aveCharge * -1.60217646263E-19D * Math.abs(potential) / (2.761300648E-23D * this.temp));
/* 229 */       double sigmaH = maxQ;double funcH = 0.0D;
/* 230 */       double tolQ = Math.abs(potential) * 1.0E-8D;
/* 231 */       int nIterQ = 10000;
/* 232 */       boolean testQ = true;
/* 233 */       int iExpandQ = 0;int iBisectQ = 0;
/* 234 */       double diffQ = 0.0D;
/*     */       
/* 236 */       while (testQ) {
/* 237 */         funcL = icFunct(sigmaL, potential, ions);
/* 238 */         funcH = icFunct(sigmaH, potential, ions);
/* 239 */         if (funcH * funcL > 0.0D) {
/* 240 */           iExpandQ++;
/* 241 */           if (iExpandQ > 10) throw new IllegalArgumentException("iExpandQ has reached its limit");
/* 242 */           diffQ = sigmaH - sigmaL;
/* 243 */           sigmaH += diffQ;
/*     */         }
/*     */         else {
/* 246 */           testQ = false;
/*     */         }
/*     */       }
/* 249 */       if (Math.abs(funcL) <= tolQ) {
/* 250 */         sigmaM = sigmaL;
/*     */ 
/*     */       }
/* 253 */       else if (Math.abs(funcH) <= tolQ) {
/* 254 */         sigmaM = sigmaH;
/*     */       }
/*     */       else {
/* 257 */         testQ = true;
/* 258 */         while (testQ) {
/* 259 */           sigmaM = (sigmaL + sigmaH) / 2.0D;
/* 260 */           funcM = icFunct(sigmaM, potential, ions);
/* 261 */           if (Math.abs(funcM) <= tolQ) {
/* 262 */             testQ = false;
/*     */ 
/*     */           }
/* 265 */           else if (funcL * funcM > 0.0D) {
/* 266 */             funcL = funcM;
/* 267 */             sigmaL = sigmaM;
/*     */           }
/*     */           else {
/* 270 */             funcH = funcM;
/* 271 */             sigmaH = sigmaM;
/*     */           }
/*     */           
/* 274 */           iBisectQ++;
/* 275 */           if (iBisectQ > nIterQ) {
/* 276 */             System.out.println("Class: DonnanConcn\nMethod: interfaceCharge");
/* 277 */             System.out.println("Maximum iterations in bisection procedure exceeded\nCurrent value of interface charge returned");
/* 278 */             testQ = false;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 283 */       this.interfacialCharge = sigmaM;
/* 284 */       this.interfacialChargeDensity = (sigmaM / this.interfacialArea);
/*     */     }
/*     */     
/*     */ 
/* 288 */     return this.interfacialCharge / 96485.34158524018D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double icFunct(double sigma, double potential, double[] ions)
/*     */   {
/* 295 */     double sigmaAbs = Math.abs(sigma);
/* 296 */     double sgn = Fmath.sign(potential);
/*     */     
/* 298 */     if (this.chargeSame) {
/* 299 */       double NtotalA = 0.0D;
/* 300 */       double NtotalB = 0.0D;
/* 301 */       for (int i = 0; i < this.numOfIons; i++) {
/* 302 */         NtotalA += this.concnA[i];
/* 303 */         NtotalB += this.concnB[i] + this.complex[i];
/*     */       }
/* 305 */       NtotalA /= 2.0D;
/* 306 */       NtotalB /= 2.0D;
/* 307 */       double preterm = 2.761300648E-23D * this.temp / (-this.chargeValue * -1.60217646263E-19D);
/* 308 */       this.diffPotentialA = (sgn * preterm * Fmath.asinh(sigmaAbs / Math.sqrt(4.81771359576E24D * NtotalA * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonA)));
/* 309 */       this.diffPotentialB = (sgn * preterm * Fmath.asinh(sigmaAbs / Math.sqrt(4.81771359576E24D * NtotalB * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonB)));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 314 */       double phiAm = 0.0D;
/* 315 */       double funcM = 0.0D;
/* 316 */       double phiAl = 0.0D;
/* 317 */       double funcL = 0.0D;
/* 318 */       double maxPhiA = 1.1D * potential;
/* 319 */       double phiAh = maxPhiA;
/* 320 */       double funcH = 0.0D;
/* 321 */       double tolP = Math.abs(sigma) * 0.1D;
/* 322 */       int nIterP = 1000;
/* 323 */       boolean testP = true;
/* 324 */       int iExpandP = 0;int iBisectP = 0;
/* 325 */       double diffP = 0.0D;
/*     */       
/* 327 */       while (testP) {
/* 328 */         funcL = phiAfunct(sigma, phiAl, ions);
/* 329 */         funcH = phiAfunct(sigma, phiAh, ions);
/* 330 */         if (funcH * funcL > 0.0D) {
/* 331 */           iExpandP++;
/* 332 */           if (iExpandP > 10) throw new IllegalArgumentException("iExpandP (partition A) has reached its limit");
/* 333 */           diffP = phiAh - phiAl;
/* 334 */           phiAh += diffP;
/*     */         }
/*     */         else {
/* 337 */           testP = false;
/*     */         }
/*     */       }
/*     */       
/* 341 */       testP = true;
/* 342 */       while (testP) {
/* 343 */         phiAm = (phiAl + phiAh) / 2.0D;
/* 344 */         funcM = phiAfunct(sigma, phiAm, ions);
/* 345 */         if (Math.abs(funcM) <= tolP) {
/* 346 */           this.diffPotentialA = (sgn * phiAm);
/* 347 */           testP = false;
/*     */ 
/*     */         }
/* 350 */         else if (funcL * funcM > 0.0D) {
/* 351 */           funcL = funcM;
/* 352 */           phiAl = phiAm;
/*     */         }
/*     */         else {
/* 355 */           funcH = funcM;
/* 356 */           phiAh = phiAm;
/*     */         }
/*     */         
/* 359 */         iBisectP++;
/* 360 */         if (iBisectP > nIterP)
/*     */         {
/*     */ 
/* 363 */           System.out.println("phiA = " + phiAm + " sigma = " + sigma + " funcM = " + funcM + " tol = " + tolP);
/* 364 */           this.diffPotentialA = (sgn * phiAm);
/* 365 */           testP = false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 370 */       double phiBm = 0.0D;
/* 371 */       double phiBl = 0.0D;
/* 372 */       double maxPhiB = -1.1D * potential;
/* 373 */       double phiBh = maxPhiB;
/* 374 */       tolP = Math.abs(potential) * 1.0E-5D;
/* 375 */       if (tolP == 0.0D) tolP = 1.0E-6D;
/* 376 */       nIterP = 100000;
/* 377 */       testP = true;
/* 378 */       iExpandP = 0;
/* 379 */       iBisectP = 0;
/* 380 */       diffP = 0.0D;
/*     */       
/* 382 */       while (testP) {
/* 383 */         funcL = phiAfunct(sigma, phiBl, ions);
/* 384 */         funcH = phiAfunct(sigma, phiBh, ions);
/* 385 */         if (funcH * funcL > 0.0D) {
/* 386 */           iExpandP++;
/* 387 */           if (iExpandP > 10) throw new IllegalArgumentException("iExpandP (partition B) has reached its limit");
/* 388 */           diffP = phiBh - phiBl;
/* 389 */           phiBh += diffP;
/*     */         }
/*     */         else {
/* 392 */           testP = false;
/*     */         }
/*     */       }
/*     */       
/* 396 */       if (Math.abs(funcH) <= tolP) {
/* 397 */         phiBm = phiBh;
/*     */       }
/*     */       else {
/* 400 */         testP = true;
/* 401 */         while (testP) {
/* 402 */           phiBm = (phiBl + phiBh) / 2.0D;
/* 403 */           funcM = phiAfunct(sigma, phiBm, ions);
/* 404 */           if (Math.abs(funcM) <= tolP) {
/* 405 */             testP = false;
/*     */ 
/*     */           }
/* 408 */           else if (funcL * funcM > 0.0D) {
/* 409 */             funcL = funcM;
/* 410 */             phiBl = phiBm;
/*     */           }
/*     */           else {
/* 413 */             funcH = funcM;
/* 414 */             phiBh = phiBm;
/*     */           }
/*     */           
/* 417 */           iBisectP++;
/* 418 */           if (iBisectP > nIterP) {
/* 419 */             System.out.println("Class: DonnanConcn\nMethod: icFunct");
/* 420 */             System.out.println("Maximum iterations in bisection B procedure exceeded\nCurrent value of interface charge returned");
/* 421 */             System.out.println("phiB = " + phiBm + " maxPhiB = " + maxPhiB + " funcM = " + funcM + " tol = " + tolP);
/* 422 */             testP = false;
/*     */           }
/*     */         }
/*     */       }
/* 426 */       this.diffPotentialB = (sgn * phiBm);
/*     */     }
/*     */     
/*     */ 
/* 430 */     sternCapacitance(ions, sigma, this.diffPotentialA, -this.diffPotentialB);
/* 431 */     this.sternPotential = (sgn * sigmaAbs / this.sternCap);
/*     */     
/* 433 */     return potential - (this.diffPotentialA + this.diffPotentialB + this.sternPotential);
/*     */   }
/*     */   
/*     */ 
/*     */   public double phiAfunct(double sigma, double potential, double[] ions)
/*     */   {
/* 439 */     double sumAsigma = 0.0D;
/* 440 */     double sgns = Fmath.sign(sigma);
/* 441 */     double preterm1 = 1.7708375634E-11D * this.epsilonA * 1.380650324E-23D * this.temp * 6.0221419947E23D;
/* 442 */     double preterm2 = potential * -1.60217646263E-19D / (1.380650324E-23D * this.temp);
/*     */     
/* 444 */     for (int i = 0; i < this.numOfIons; i++)
/*     */     {
/* 446 */       sumAsigma += preterm1 * ions[i] * this.partCoeffPot[i] * (Math.exp(this.charges[i] * preterm2) - 1.0D);
/*     */     }
/* 448 */     if (sumAsigma < 0.0D) {
/* 449 */       sgns = -sgns;
/* 450 */       sumAsigma = -sumAsigma;
/*     */     }
/* 452 */     double diffSigma = sigma - sgns * Math.sqrt(sumAsigma);
/* 453 */     return diffSigma;
/*     */   }
/*     */   
/*     */ 
/*     */   public double phiBfunct(double sigma, double potential, double[] ions)
/*     */   {
/* 459 */     double sumBsigma = 0.0D;
/* 460 */     double sgns = Fmath.sign(sigma);
/* 461 */     double preterm1 = 1.7708375634E-11D * this.epsilonB * 1.380650324E-23D * this.temp * 6.0221419947E23D;
/* 462 */     double preterm2 = potential * -1.60217646263E-19D / (1.380650324E-23D * this.temp);
/*     */     
/* 464 */     for (int i = 0; i < this.numOfIons; i++) {
/* 465 */       sumBsigma += preterm1 * (ions[i] + this.complex[i]) * (Math.exp(this.charges[i] * preterm2) - 1.0D);
/*     */     }
/* 467 */     if (sumBsigma < 0.0D) {
/* 468 */       sgns = -sgns;
/* 469 */       sumBsigma = -sumBsigma;
/*     */     }
/* 471 */     double diffSigma = sigma - sgns * Math.sqrt(sumBsigma);
/* 472 */     return diffSigma;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void sternCapacitance(double[] ions, double sigma, double psiA, double psiB)
/*     */   {
/* 479 */     double denomA = 0.0D;
/* 480 */     double denomB = 0.0D;
/* 481 */     this.sternDeltaA = 0.0D;
/* 482 */     this.sternDeltaB = 0.0D;
/* 483 */     double preterm = 1.60217646263E-19D / (1.380650324E-23D * this.temp);
/* 484 */     for (int i = 0; i < this.numOfIons; i++) {
/* 485 */       this.sternDeltaA += this.radii[i] * ions[i] * this.partCoeffPot[i] * Math.exp(preterm * psiA * this.charges[i]);
/* 486 */       this.sternDeltaB += (this.radii[i] * ions[i] + this.ionophoreRad * this.complex[i]) * Math.exp(-preterm * psiB * this.charges[i]);
/* 487 */       denomA += ions[i] * this.partCoeffPot[i] * Math.exp(preterm * psiA * this.charges[i]);
/* 488 */       denomB += (ions[i] + this.complex[i]) * Math.exp(-preterm * psiB * this.charges[i]);
/*     */     }
/* 490 */     this.sternDeltaA /= denomA;
/* 491 */     this.sternDeltaB /= denomB;
/* 492 */     this.sternCap = (8.854187817E-12D * this.epsilonSternA * this.epsilonSternB / (this.sternDeltaA * this.epsilonSternB + this.sternDeltaB * this.epsilonSternA));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/DonnanConcn.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */