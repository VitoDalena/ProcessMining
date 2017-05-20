/*      */ package flanagan.math;
/*      */ 
/*      */ import flanagan.analysis.Stat;
/*      */ import flanagan.roots.RealRoot;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PsRandom
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private long seed;
/*      */   private long initialSeed;
/*   73 */   private int methodOptionDecimal = 1;
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
/*   85 */   private Random rr = null;
/*      */   
/*   87 */   private int methodOptionBinary = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   97 */   private long ia = 16807L;
/*   98 */   private long im = 2147483647L;
/*   99 */   private double am = 1.0D / this.im;
/*  100 */   private long iq = 127773L;
/*  101 */   private long ir = 2836L;
/*  102 */   private int ntab = 32;
/*  103 */   private long ndiv = 1L + (this.im - 1L) / this.ntab;
/*  104 */   private double eps = 1.2E-7D;
/*  105 */   private double rnmx = 1.0D - this.eps;
/*  106 */   private long iy = 0L;
/*  107 */   private long[] iv = new long[this.ntab];
/*      */   
/*      */ 
/*  110 */   private int iset = 0;
/*  111 */   private double gset = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*  115 */   private long powTwo1 = 1L;
/*  116 */   private long powTwo2 = 2L;
/*  117 */   private long powTwo5 = 16L;
/*  118 */   private long powTwo18 = 131072L;
/*  119 */   private long mask = this.powTwo1 + this.powTwo2 + this.powTwo5;
/*      */   
/*      */ 
/*      */ 
/*      */   public PsRandom()
/*      */   {
/*  125 */     this.seed = System.currentTimeMillis();
/*  126 */     this.initialSeed = this.seed;
/*  127 */     this.rr = new Random(this.seed);
/*      */   }
/*      */   
/*      */   public PsRandom(long seed)
/*      */   {
/*  132 */     this.seed = seed;
/*  133 */     this.initialSeed = seed;
/*  134 */     this.rr = new Random(this.seed);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSeed(long seed)
/*      */   {
/*  141 */     this.seed = seed;
/*  142 */     if (this.methodOptionDecimal == 1) this.rr = new Random(this.seed);
/*      */   }
/*      */   
/*      */   public long getInitialSeed()
/*      */   {
/*  147 */     return this.initialSeed;
/*      */   }
/*      */   
/*      */   public long getSeed()
/*      */   {
/*  152 */     return this.seed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMethodDecimal(int methodOpt)
/*      */   {
/*  159 */     if ((methodOpt < 1) || (methodOpt > 2)) throw new IllegalArgumentException("Argument to PsRandom.setMethodDecimal must 1 or 2\nValue transferred was" + methodOpt);
/*  160 */     this.methodOptionDecimal = methodOpt;
/*  161 */     if (methodOpt == 1) this.rr = new Random(this.seed);
/*      */   }
/*      */   
/*      */   public int getMethodDecimal()
/*      */   {
/*  166 */     return this.methodOptionDecimal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMethodBinary(int methodOpt)
/*      */   {
/*  173 */     if ((methodOpt < 1) || (methodOpt > 2)) throw new IllegalArgumentException("Argument to PsRandom.setMethodBinary must 1 or 2\nValue transferred was" + methodOpt);
/*  174 */     this.methodOptionBinary = methodOpt;
/*  175 */     if (methodOpt == 1) this.rr = new Random(this.seed);
/*      */   }
/*      */   
/*      */   public int getMethodBinary()
/*      */   {
/*  180 */     return this.methodOptionBinary;
/*      */   }
/*      */   
/*      */   public double nextDouble()
/*      */   {
/*  185 */     if (this.methodOptionDecimal == 1) {
/*  186 */       return this.rr.nextDouble();
/*      */     }
/*      */     
/*  189 */     return parkMiller();
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextDouble(double top)
/*      */   {
/*  195 */     return top * nextDouble();
/*      */   }
/*      */   
/*      */   public double nextDouble(double bottom, double top)
/*      */   {
/*  200 */     return (top - bottom) * nextDouble() + bottom;
/*      */   }
/*      */   
/*      */   public double[] doubleArray(int arrayLength)
/*      */   {
/*  205 */     double[] array = new double[arrayLength];
/*  206 */     for (int i = 0; i < arrayLength; i++) {
/*  207 */       array[i] = nextDouble();
/*      */     }
/*  209 */     return array;
/*      */   }
/*      */   
/*      */   public double[] doubleArray(int arrayLength, double top)
/*      */   {
/*  214 */     double[] array = new double[arrayLength];
/*  215 */     for (int i = 0; i < arrayLength; i++) {
/*  216 */       array[i] = (top * nextDouble());
/*      */     }
/*  218 */     return array;
/*      */   }
/*      */   
/*      */   public double[] doubleArray(int arrayLength, double bottom, double top)
/*      */   {
/*  223 */     double[] array = new double[arrayLength];
/*  224 */     for (int i = 0; i < arrayLength; i++) {
/*  225 */       array[i] = (top * nextDouble());
/*      */     }
/*  227 */     return array;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double parkMiller()
/*      */   {
/*  236 */     int jj = 0;
/*  237 */     long kk = 0L;
/*  238 */     double temp = 0.0D;
/*  239 */     this.iy = 0L;
/*      */     
/*  241 */     if ((this.seed <= 0L) || (this.iy != 0L)) {
/*  242 */       if (-this.seed < 1L) {
/*  243 */         this.seed = 1L;
/*      */       }
/*      */       else {
/*  246 */         this.seed = (-this.seed);
/*      */       }
/*  248 */       for (int j = this.ntab + 7; j >= 0; j--) {
/*  249 */         kk = this.seed / this.iq;
/*  250 */         this.seed = (this.ia * (this.seed - kk * this.iq) - this.ir * kk);
/*  251 */         if (this.seed < 0L) this.seed += this.im;
/*  252 */         if (j < this.ntab) this.iv[j] = this.seed;
/*      */       }
/*  254 */       this.iy = this.iv[0];
/*      */     }
/*  256 */     kk = this.seed / this.iq;
/*  257 */     this.seed = (this.ia * (this.seed - kk * this.iq) - this.ir * kk);
/*  258 */     if (this.seed < 0L) this.seed += this.im;
/*  259 */     jj = (int)(this.iy / this.ndiv);
/*  260 */     this.iy = this.iv[jj];
/*  261 */     this.iv[jj] = this.seed;
/*  262 */     if ((temp = this.am * this.iy) > this.rnmx) {
/*  263 */       return this.rnmx;
/*      */     }
/*      */     
/*  266 */     return temp;
/*      */   }
/*      */   
/*      */ 
/*      */   public int nextBit()
/*      */   {
/*  272 */     if (this.methodOptionBinary == 1) {
/*  273 */       return nextBitM1();
/*      */     }
/*      */     
/*  276 */     return nextBitM2();
/*      */   }
/*      */   
/*      */ 
/*      */   public int[] bitArray(int arrayLength)
/*      */   {
/*  282 */     int[] bitarray = new int[arrayLength];
/*  283 */     for (int i = 0; i < arrayLength; i++) {
/*  284 */       bitarray[i] = nextBit();
/*      */     }
/*  286 */     return bitarray;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int nextBitM1()
/*      */   {
/*  295 */     long newBit = (this.seed & this.powTwo18) >> 17 ^ (this.seed & this.powTwo5) >> 4 ^ (this.seed & this.powTwo2) >> 1 ^ this.seed & this.powTwo1;
/*  296 */     this.seed = (this.seed << 1 | newBit);
/*  297 */     return (int)newBit;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int nextBitM2()
/*      */   {
/*  304 */     int randomBit = 0;
/*  305 */     if ((this.seed & this.powTwo18) <= 0L) {
/*  306 */       this.seed = ((this.seed ^ this.mask) << 1 | this.powTwo1);
/*  307 */       randomBit = 1;
/*      */     }
/*      */     else {
/*  310 */       this.seed <<= 1;
/*  311 */       randomBit = 0;
/*      */     }
/*      */     
/*  314 */     return randomBit;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextGaussian(double mean, double sd)
/*      */   {
/*  320 */     double ran = 0.0D;
/*  321 */     if (this.methodOptionDecimal == 1) {
/*  322 */       ran = this.rr.nextGaussian();
/*      */     }
/*      */     else {
/*  325 */       ran = boxMullerParkMiller();
/*      */     }
/*  327 */     ran = ran * sd + mean;
/*  328 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextGaussian()
/*      */   {
/*  334 */     double ran = 0.0D;
/*  335 */     if (this.methodOptionDecimal == 1) {
/*  336 */       ran = this.rr.nextGaussian();
/*      */     }
/*      */     else {
/*  339 */       ran = boxMullerParkMiller();
/*      */     }
/*  341 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextNormal(double mean, double sd) {
/*  345 */     return nextGaussian(mean, sd);
/*      */   }
/*      */   
/*      */   public double nextNormal() {
/*  349 */     return nextGaussian();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] gaussianArray(double mean, double sd, int n)
/*      */   {
/*  356 */     double[] ran = new double[n];
/*  357 */     for (int i = 0; i < n; i++) {
/*  358 */       ran[i] = nextGaussian();
/*      */     }
/*  360 */     ran = Stat.standardize(ran);
/*  361 */     for (int i = 0; i < n; i++) {
/*  362 */       ran[i] = (ran[i] * sd + mean);
/*      */     }
/*  364 */     return ran;
/*      */   }
/*      */   
/*      */   public double[] normalArray(double mean, double sd, int n) {
/*  368 */     return gaussianArray(mean, sd, n);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] correlatedGaussianArrays(double mean1, double mean2, double sd1, double sd2, double rho, int n)
/*      */   {
/*  376 */     if (Math.abs(rho) > 1.0D) throw new IllegalArgumentException("The correlation coefficient, " + rho + ", must lie between -1 and 1");
/*  377 */     double[][] ran = new double[2][n];
/*  378 */     double[] ran1 = gaussianArray(0.0D, 1.0D, n);
/*  379 */     double[] ran2 = gaussianArray(0.0D, 1.0D, n);
/*      */     
/*  381 */     double ranh = 0.0D;
/*  382 */     double rhot = Math.sqrt(1.0D - rho * rho);
/*      */     
/*  384 */     for (int i = 0; i < n; i++) {
/*  385 */       ran[0][i] = (ran1[i] * sd1 + mean1);
/*  386 */       ran[1][i] = ((rho * ran1[i] + rhot * ran2[i]) * sd2 + mean2);
/*      */     }
/*  388 */     return ran;
/*      */   }
/*      */   
/*      */   public double[][] correlatedNormalArrays(double mean1, double mean2, double sd1, double sd2, double rho, int n) {
/*  392 */     return correlatedGaussianArrays(mean1, mean2, sd1, sd2, rho, n);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   double boxMullerParkMiller()
/*      */   {
/*  401 */     double fac = 0.0D;double rsq = 0.0D;double v1 = 0.0D;double v2 = 0.0D;
/*      */     
/*  403 */     if (this.iset == 0) {
/*      */       do {
/*  405 */         v1 = 2.0D * parkMiller() - 1.0D;
/*  406 */         v2 = 2.0D * parkMiller() - 1.0D;
/*  407 */         rsq = v1 * v1 + v2 * v2;
/*  408 */       } while ((rsq >= 1.0D) || (rsq == 0.0D));
/*  409 */       fac = Math.sqrt(-2.0D * Math.log(rsq) / rsq);
/*  410 */       this.gset = (v1 * fac);
/*  411 */       this.iset = 1;
/*  412 */       return v2 * fac;
/*      */     }
/*  414 */     this.iset = 0;
/*  415 */     return this.gset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double nextLogNormal(double mu, double sigma)
/*      */   {
/*  423 */     double ran = 0.0D;
/*      */     
/*      */ 
/*  426 */     LogNormalTwoParFunct logNorm2 = new LogNormalTwoParFunct();
/*      */     
/*      */ 
/*  429 */     logNorm2.mu = mu;
/*  430 */     logNorm2.sigma = sigma;
/*      */     
/*      */ 
/*  433 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/*  436 */     double lowerBound = 0.0D;
/*  437 */     double sigma2 = sigma * sigma;
/*      */     
/*      */ 
/*  440 */     double upperBound = 5.0D * Math.sqrt((Math.exp(sigma2) - 1.0D) * Math.exp(2.0D * mu + sigma2));
/*      */     
/*      */ 
/*  443 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/*  446 */     realR.noLowerBoundExtension();
/*      */     
/*      */ 
/*  449 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/*  452 */     realR.resetNaNexceptionToTrue();
/*  453 */     realR.supressLimitReachedMessage();
/*  454 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/*  457 */     boolean test = true;
/*  458 */     int ii = 0;
/*  459 */     int imax = 10;
/*  460 */     while (test)
/*      */     {
/*      */ 
/*  463 */       logNorm2.cfd = nextDouble();
/*      */       
/*      */ 
/*  466 */       ran = realR.falsePosition(logNorm2, lowerBound, upperBound);
/*      */       
/*  468 */       if (!Double.isNaN(ran)) {
/*  469 */         test = false;
/*      */ 
/*      */       }
/*  472 */       else if (ii > imax) {
/*  473 */         System.out.println("class: PsRandom,  method: nextLogNormal");
/*  474 */         System.out.println(imax + " successive attempts at calculating a random log-normal deviate failed for values of mu = " + mu + ", sigma = " + sigma);
/*  475 */         System.out.println("NaN returned");
/*  476 */         ran = NaN.0D;
/*  477 */         test = false;
/*      */       }
/*      */       else {
/*  480 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  484 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextLogNormalTwoPar(double mu, double sigma) {
/*  488 */     return nextLogNormal(mu, sigma);
/*      */   }
/*      */   
/*      */   public double[] logNormalArray(double mu, double sigma, int n)
/*      */   {
/*  493 */     double[] ran = new double[n];
/*      */     
/*      */ 
/*  496 */     LogNormalTwoParFunct logNorm2 = new LogNormalTwoParFunct();
/*      */     
/*      */ 
/*  499 */     logNorm2.mu = mu;
/*  500 */     logNorm2.sigma = sigma;
/*      */     
/*      */ 
/*  503 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/*  506 */     double lowerBound = 0.0D;
/*      */     
/*      */ 
/*  509 */     double sigma2 = sigma * sigma;
/*  510 */     double upperBound = 5.0D * Math.sqrt((Math.exp(sigma2) - 1.0D) * Math.exp(2.0D * mu + sigma2));
/*      */     
/*  512 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/*  515 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/*  518 */       realR.noLowerBoundExtension();
/*      */       
/*      */ 
/*  521 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/*  524 */       realR.resetNaNexceptionToTrue();
/*  525 */       realR.supressLimitReachedMessage();
/*  526 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/*  529 */       boolean test = true;
/*  530 */       int ii = 0;
/*  531 */       int imax = 10;
/*  532 */       while (test)
/*      */       {
/*      */ 
/*  535 */         logNorm2.cfd = nextDouble();
/*      */         
/*      */ 
/*  538 */         double rangd = realR.bisect(logNorm2, lowerBound, upperBound);
/*      */         
/*  540 */         if (!Double.isNaN(rangd)) {
/*  541 */           test = false;
/*  542 */           ran[i] = rangd;
/*      */ 
/*      */         }
/*  545 */         else if (ii > imax) {
/*  546 */           System.out.println("class: PsRandom,  method: logNormalArray");
/*  547 */           System.out.println(imax + " successive attempts at calculating a random gamma deviate failed for values of mu = " + mu + ", sigma = " + sigma);
/*  548 */           System.out.println("NaN returned");
/*  549 */           ran[i] = NaN.0D;
/*  550 */           test = false;
/*      */         }
/*      */         else {
/*  553 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  558 */     return ran;
/*      */   }
/*      */   
/*      */   public double[] logNormalTwoParArray(double mu, double sigma, int n) {
/*  562 */     return logNormalArray(mu, sigma, n);
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextLogNormalThreePar(double alpha, double beta, double gamma)
/*      */   {
/*  568 */     double ran = NaN.0D;
/*      */     
/*      */ 
/*  571 */     LogNormalThreeParFunct logNorm3 = new LogNormalThreeParFunct();
/*      */     
/*      */ 
/*  574 */     logNorm3.alpha = alpha;
/*  575 */     logNorm3.beta = beta;
/*  576 */     logNorm3.gamma = gamma;
/*      */     
/*      */ 
/*  579 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/*  582 */     double lowerBound = 0.0D;
/*  583 */     double beta2 = beta * beta;
/*  584 */     double upperBound = 5.0D * Math.sqrt((Math.exp(beta2) - 1.0D) * Math.exp(2.0D * Math.log(gamma) + beta2));
/*      */     
/*      */ 
/*  587 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/*  590 */     realR.noLowerBoundExtension();
/*      */     
/*      */ 
/*  593 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/*  596 */     realR.resetNaNexceptionToTrue();
/*  597 */     realR.supressLimitReachedMessage();
/*  598 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/*  601 */     boolean test = true;
/*  602 */     int ii = 0;
/*  603 */     int imax = 10;
/*  604 */     while (test)
/*      */     {
/*      */ 
/*  607 */       logNorm3.cfd = nextDouble();
/*      */       
/*      */ 
/*  610 */       ran = realR.falsePosition(logNorm3, lowerBound, upperBound);
/*      */       
/*  612 */       if (!Double.isNaN(ran)) {
/*  613 */         test = false;
/*      */ 
/*      */       }
/*  616 */       else if (ii > imax) {
/*  617 */         System.out.println("class: PsRandom,  method: nextLogNormalThreePar");
/*  618 */         System.out.println(imax + " successive attempts at calculating a random log-normal deviate failed for values of alpha = " + alpha + ", beta = " + beta + ", gamma = " + gamma);
/*  619 */         System.out.println("NaN returned");
/*  620 */         ran = NaN.0D;
/*  621 */         test = false;
/*      */       }
/*      */       else {
/*  624 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*  628 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] logNormalThreeParArray(double alpha, double beta, double gamma, int n)
/*      */   {
/*  634 */     double[] ran = new double[n];
/*      */     
/*      */ 
/*  637 */     LogNormalThreeParFunct logNorm3 = new LogNormalThreeParFunct();
/*      */     
/*      */ 
/*  640 */     logNorm3.alpha = alpha;
/*  641 */     logNorm3.beta = beta;
/*  642 */     logNorm3.gamma = gamma;
/*      */     
/*      */ 
/*  645 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/*  648 */     double lowerBound = 0.0D;
/*      */     
/*      */ 
/*  651 */     double beta2 = beta * beta;
/*  652 */     double upperBound = 5.0D * Math.sqrt((Math.exp(beta2) - 1.0D) * Math.exp(2.0D * Math.log(gamma) + beta2));
/*      */     
/*  654 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/*  657 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/*  660 */       realR.noLowerBoundExtension();
/*      */       
/*      */ 
/*  663 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/*  666 */       realR.resetNaNexceptionToTrue();
/*  667 */       realR.supressLimitReachedMessage();
/*  668 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/*  671 */       boolean test = true;
/*  672 */       int ii = 0;
/*  673 */       int imax = 10;
/*  674 */       while (test)
/*      */       {
/*      */ 
/*  677 */         logNorm3.cfd = nextDouble();
/*      */         
/*      */ 
/*  680 */         double rangd = realR.falsePosition(logNorm3, lowerBound, upperBound);
/*      */         
/*  682 */         if (!Double.isNaN(rangd)) {
/*  683 */           test = false;
/*  684 */           ran[i] = rangd;
/*      */ 
/*      */         }
/*  687 */         else if (ii > imax) {
/*  688 */           System.out.println("class: PsRandom,  method: logNormalThreeParArray");
/*  689 */           System.out.println(imax + " successive attempts at calculating a log-normal gamma deviate failed for values of alpha = " + alpha + ", beta = " + beta + ", gamma = " + gamma);
/*  690 */           System.out.println("NaN returned");
/*  691 */           ran[i] = NaN.0D;
/*  692 */           test = false;
/*      */         }
/*      */         else {
/*  695 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  700 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double nextLorentzian(double mu, double gamma)
/*      */   {
/*  708 */     double ran = Math.tan((nextDouble() - 0.5D) * 3.141592653589793D);
/*  709 */     ran = ran * gamma / 2.0D + mu;
/*      */     
/*  711 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] lorentzianArray(double mu, double gamma, int n)
/*      */   {
/*  718 */     double[] ran = new double[n];
/*  719 */     for (int i = 0; i < n; i++) {
/*  720 */       ran[i] = Math.tan((nextDouble() - 0.5D) * 3.141592653589793D);
/*  721 */       ran[i] = (ran[i] * gamma / 2.0D + mu);
/*      */     }
/*  723 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextPoissonian(double mean)
/*      */   {
/*  729 */     double ran = 0.0D;
/*  730 */     double oldm = -1.0D;
/*  731 */     double expt = 0.0D;
/*  732 */     double em = 0.0D;
/*  733 */     double term = 0.0D;
/*  734 */     double sq = 0.0D;
/*  735 */     double lnMean = 0.0D;
/*  736 */     double yDev = 0.0D;
/*      */     
/*  738 */     if (mean < 12.0D) {
/*  739 */       if (mean != oldm) {
/*  740 */         oldm = mean;
/*  741 */         expt = Math.exp(-mean);
/*      */       }
/*  743 */       em = -1.0D;
/*  744 */       term = 1.0D;
/*      */       do {
/*  746 */         em += 1.0D;
/*  747 */         term *= nextDouble();
/*  748 */       } while (term > expt);
/*  749 */       ran = em;
/*      */     }
/*      */     else {
/*  752 */       if (mean != oldm) {
/*  753 */         oldm = mean;
/*  754 */         sq = Math.sqrt(2.0D * mean);
/*  755 */         lnMean = Math.log(mean);
/*  756 */         expt = lnMean - Stat.logGamma(mean + 1.0D);
/*      */       }
/*      */       do {
/*      */         do {
/*  760 */           yDev = Math.tan(3.141592653589793D * nextDouble());
/*  761 */           em = sq * yDev + mean;
/*  762 */         } while (em < 0.0D);
/*  763 */         em = Math.floor(em);
/*  764 */         term = 0.9D * (1.0D + yDev * yDev) * Math.exp(em * lnMean - Stat.logGamma(em + 1.0D) - expt);
/*  765 */       } while (nextDouble() > term);
/*  766 */       ran = em;
/*      */     }
/*  768 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] poissonianArray(double mean, int n)
/*      */   {
/*  774 */     double[] ran = new double[n];
/*  775 */     double oldm = -1.0D;
/*  776 */     double expt = 0.0D;
/*  777 */     double em = 0.0D;
/*  778 */     double term = 0.0D;
/*  779 */     double sq = 0.0D;
/*  780 */     double lnMean = 0.0D;
/*  781 */     double yDev = 0.0D;
/*      */     
/*  783 */     if (mean < 12.0D) {
/*  784 */       for (int i = 0; i < n; i++) {
/*  785 */         if (mean != oldm) {
/*  786 */           oldm = mean;
/*  787 */           expt = Math.exp(-mean);
/*      */         }
/*  789 */         em = -1.0D;
/*  790 */         term = 1.0D;
/*      */         do {
/*  792 */           em += 1.0D;
/*  793 */           term *= nextDouble();
/*  794 */         } while (term > expt);
/*  795 */         ran[i] = em;
/*      */       }
/*      */       
/*      */     } else {
/*  799 */       for (int i = 0; i < n; i++) {
/*  800 */         if (mean != oldm) {
/*  801 */           oldm = mean;
/*  802 */           sq = Math.sqrt(2.0D * mean);
/*  803 */           lnMean = Math.log(mean);
/*  804 */           expt = lnMean - Stat.logGamma(mean + 1.0D);
/*      */         }
/*      */         do {
/*      */           do {
/*  808 */             yDev = Math.tan(3.141592653589793D * nextDouble());
/*  809 */             em = sq * yDev + mean;
/*  810 */           } while (em < 0.0D);
/*  811 */           em = Math.floor(em);
/*  812 */           term = 0.9D * (1.0D + yDev * yDev) * Math.exp(em * lnMean - Stat.logGamma(em + 1.0D) - expt);
/*  813 */         } while (nextDouble() > term);
/*  814 */         ran[i] = em;
/*      */       }
/*      */     }
/*  817 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double nextBinomial(double prob, int nTrials)
/*      */   {
/*  826 */     if ((prob < 0.0D) || (prob > 1.0D)) { throw new IllegalArgumentException("The probablity provided, " + prob + ", must lie between 0 and 1)");
/*      */     }
/*  828 */     double binomialDeviate = 0.0D;
/*  829 */     double deviateMean = 0.0D;
/*  830 */     double testDeviate = 0.0D;
/*  831 */     double workingProb = 0.0D;
/*  832 */     double logProb = 0.0D;
/*  833 */     double probOld = -1.0D;
/*  834 */     double probC = -1.0D;
/*  835 */     double logProbC = -1.0D;
/*  836 */     int nOld = -1;
/*  837 */     double enTrials = 0.0D;
/*  838 */     double oldGamma = 0.0D;
/*  839 */     double tanW = 0.0D;
/*  840 */     double hold0 = 0.0D;
/*      */     
/*      */ 
/*  843 */     workingProb = prob <= 0.5D ? prob : 1.0D - prob;
/*  844 */     deviateMean = nTrials * workingProb;
/*      */     
/*  846 */     if (nTrials < 25)
/*      */     {
/*  848 */       binomialDeviate = 0.0D;
/*  849 */       for (int jj = 1; jj <= nTrials; jj++) if (nextDouble() < workingProb) binomialDeviate += 1.0D;
/*      */     }
/*  851 */     if (deviateMean < 1.0D)
/*      */     {
/*  853 */       double expOfMean = Math.exp(-deviateMean);
/*  854 */       testDeviate = 1.0D;
/*  855 */       for (int jj = 0; jj <= nTrials; jj++) {
/*  856 */         testDeviate *= nextDouble();
/*  857 */         if (testDeviate < expOfMean) break;
/*      */       }
/*  859 */       binomialDeviate = jj <= nTrials ? jj : nTrials;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  864 */       if (nTrials != nOld)
/*      */       {
/*  866 */         enTrials = nTrials;
/*  867 */         oldGamma = Stat.logGamma(enTrials + 1.0D);
/*  868 */         nOld = nTrials;
/*      */       }
/*  870 */       if (workingProb != probOld)
/*      */       {
/*  872 */         probC = 1.0D - workingProb;
/*  873 */         logProb = Math.log(workingProb);
/*  874 */         logProbC = Math.log(probC);
/*  875 */         probOld = workingProb;
/*      */       }
/*      */       
/*  878 */       double sq = Math.sqrt(2.0D * deviateMean * probC);
/*      */       do {
/*      */         do {
/*  881 */           double angle = 3.141592653589793D * nextDouble();
/*  882 */           tanW = Math.tan(angle);
/*  883 */           hold0 = sq * tanW + deviateMean;
/*  884 */         } while ((hold0 < 0.0D) || (hold0 >= enTrials + 1.0D));
/*  885 */         hold0 = Math.floor(hold0);
/*  886 */         testDeviate = 1.2D * sq * (1.0D + tanW * tanW) * Math.exp(oldGamma - Stat.logGamma(hold0 + 1.0D) - Stat.logGamma(enTrials - hold0 + 1.0D) + hold0 * logProb + (enTrials - hold0) * logProbC);
/*  887 */       } while (nextDouble() > testDeviate);
/*  888 */       binomialDeviate = hold0;
/*      */     }
/*      */     
/*  891 */     if (workingProb != prob) { binomialDeviate = nTrials - binomialDeviate;
/*      */     }
/*  893 */     return binomialDeviate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] binomialArray(double prob, int nTrials, int n)
/*      */   {
/*  902 */     if (nTrials < n) throw new IllegalArgumentException("Number of deviates requested, " + n + ", must be less than the number of trials, " + nTrials);
/*  903 */     if ((prob < 0.0D) || (prob > 1.0D)) { throw new IllegalArgumentException("The probablity provided, " + prob + ", must lie between 0 and 1)");
/*      */     }
/*  905 */     double[] ran = new double[n];
/*      */     
/*  907 */     double binomialDeviate = 0.0D;
/*  908 */     double deviateMean = 0.0D;
/*  909 */     double testDeviate = 0.0D;
/*  910 */     double workingProb = 0.0D;
/*  911 */     double logProb = 0.0D;
/*  912 */     double probOld = -1.0D;
/*  913 */     double probC = -1.0D;
/*  914 */     double logProbC = -1.0D;
/*  915 */     int nOld = -1;
/*  916 */     double enTrials = 0.0D;
/*  917 */     double oldGamma = 0.0D;
/*  918 */     double tanW = 0.0D;
/*  919 */     double hold0 = 0.0D;
/*      */     
/*      */ 
/*  922 */     double probOriginalValue = prob;
/*  923 */     for (int i = 0; i < n; i++) {
/*  924 */       prob = probOriginalValue;
/*  925 */       workingProb = prob <= 0.5D ? prob : 1.0D - prob;
/*  926 */       deviateMean = nTrials * workingProb;
/*      */       
/*  928 */       if (nTrials < 25)
/*      */       {
/*  930 */         binomialDeviate = 0.0D;
/*  931 */         for (int jj = 1; jj <= nTrials; jj++) if (nextDouble() < workingProb) binomialDeviate += 1.0D;
/*      */       }
/*  933 */       if (deviateMean < 1.0D)
/*      */       {
/*  935 */         double expOfMean = Math.exp(-deviateMean);
/*  936 */         testDeviate = 1.0D;
/*  937 */         for (int jj = 0; jj <= nTrials; jj++) {
/*  938 */           testDeviate *= nextDouble();
/*  939 */           if (testDeviate < expOfMean) break;
/*      */         }
/*  941 */         binomialDeviate = jj <= nTrials ? jj : nTrials;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  946 */         if (nTrials != nOld)
/*      */         {
/*  948 */           enTrials = nTrials;
/*  949 */           oldGamma = Stat.logGamma(enTrials + 1.0D);
/*  950 */           nOld = nTrials;
/*      */         }
/*  952 */         if (workingProb != probOld)
/*      */         {
/*  954 */           probC = 1.0D - workingProb;
/*  955 */           logProb = Math.log(workingProb);
/*  956 */           logProbC = Math.log(probC);
/*  957 */           probOld = workingProb;
/*      */         }
/*      */         
/*  960 */         double sq = Math.sqrt(2.0D * deviateMean * probC);
/*      */         do {
/*      */           do {
/*  963 */             double angle = 3.141592653589793D * nextDouble();
/*  964 */             tanW = Math.tan(angle);
/*  965 */             hold0 = sq * tanW + deviateMean;
/*  966 */           } while ((hold0 < 0.0D) || (hold0 >= enTrials + 1.0D));
/*  967 */           hold0 = Math.floor(hold0);
/*  968 */           testDeviate = 1.2D * sq * (1.0D + tanW * tanW) * Math.exp(oldGamma - Stat.logGamma(hold0 + 1.0D) - Stat.logGamma(enTrials - hold0 + 1.0D) + hold0 * logProb + (enTrials - hold0) * logProbC);
/*  969 */         } while (nextDouble() > testDeviate);
/*  970 */         binomialDeviate = hold0;
/*      */       }
/*      */       
/*  973 */       if (workingProb != prob) { binomialDeviate = nTrials - binomialDeviate;
/*      */       }
/*  975 */       ran[i] = binomialDeviate;
/*      */     }
/*      */     
/*  978 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextPareto(double alpha, double beta)
/*      */   {
/*  984 */     return Math.pow(1.0D - nextDouble(), -1.0D / alpha) * beta;
/*      */   }
/*      */   
/*      */   public double[] paretoArray(double alpha, double beta, int n)
/*      */   {
/*  989 */     double[] ran = new double[n];
/*  990 */     for (int i = 0; i < n; i++) {
/*  991 */       ran[i] = (Math.pow(1.0D - nextDouble(), -1.0D / alpha) * beta);
/*      */     }
/*  993 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextExponential(double mu, double sigma)
/*      */   {
/*  998 */     return mu - Math.log(1.0D - nextDouble()) * sigma;
/*      */   }
/*      */   
/*      */   public double[] exponentialArray(double mu, double sigma, int n)
/*      */   {
/* 1003 */     double[] ran = new double[n];
/* 1004 */     for (int i = 0; i < n; i++) {
/* 1005 */       ran[i] = (mu - Math.log(1.0D - nextDouble()) * sigma);
/*      */     }
/* 1007 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextRayleigh(double sigma)
/*      */   {
/* 1012 */     return Math.sqrt(-2.0D * Math.log(1.0D - nextDouble())) * sigma;
/*      */   }
/*      */   
/*      */   public double[] rayleighArray(double sigma, int n)
/*      */   {
/* 1017 */     double[] ran = new double[n];
/* 1018 */     for (int i = 0; i < n; i++) {
/* 1019 */       ran[i] = (Math.sqrt(-2.0D * Math.log(1.0D - nextDouble())) * sigma);
/*      */     }
/* 1021 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextMinimalGumbel(double mu, double sigma)
/*      */   {
/* 1027 */     return Math.log(Math.log(1.0D / (1.0D - nextDouble()))) * sigma + mu;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] minimalGumbelArray(double mu, double sigma, int n)
/*      */   {
/* 1033 */     double[] ran = new double[n];
/* 1034 */     for (int i = 0; i < n; i++) {
/* 1035 */       ran[i] = (Math.log(Math.log(1.0D / (1.0D - nextDouble()))) * sigma + mu);
/*      */     }
/* 1037 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextMaximalGumbel(double mu, double sigma)
/*      */   {
/* 1043 */     return mu - Math.log(Math.log(1.0D / (1.0D - nextDouble()))) * sigma;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] maximalGumbelArray(double mu, double sigma, int n)
/*      */   {
/* 1049 */     double[] ran = new double[n];
/* 1050 */     for (int i = 0; i < n; i++) {
/* 1051 */       ran[i] = (mu - Math.log(Math.log(1.0D / (1.0D - nextDouble()))) * sigma);
/*      */     }
/* 1053 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextFrechet(double mu, double sigma, double gamma)
/*      */   {
/* 1059 */     return Math.pow(1.0D / Math.log(1.0D / nextDouble()), 1.0D / gamma) * sigma + mu;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] frechetArray(double mu, double sigma, double gamma, int n)
/*      */   {
/* 1065 */     double[] ran = new double[n];
/* 1066 */     for (int i = 0; i < n; i++) {
/* 1067 */       ran[i] = (Math.pow(1.0D / Math.log(1.0D / nextDouble()), 1.0D / gamma) * sigma + mu);
/*      */     }
/* 1069 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextWeibull(double mu, double sigma, double gamma)
/*      */   {
/* 1075 */     return Math.pow(-Math.log(1.0D - nextDouble()), 1.0D / gamma) * sigma + mu;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] weibullArray(double mu, double sigma, double gamma, int n)
/*      */   {
/* 1081 */     double[] ran = new double[n];
/* 1082 */     for (int i = 0; i < n; i++) {
/* 1083 */       ran[i] = (Math.pow(-Math.log(1.0D - nextDouble()), 1.0D / gamma) * sigma + mu);
/*      */     }
/* 1085 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextLogistic(double mu, double scale)
/*      */   {
/* 1091 */     return 2.0D * scale * Fmath.atanh(2.0D * nextDouble() - 1.0D) + mu;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] logisticArray(double mu, double scale, int n)
/*      */   {
/* 1097 */     double[] ran = new double[n];
/* 1098 */     for (int i = 0; i < n; i++) ran[i] = (2.0D * scale * Fmath.atanh(2.0D * nextDouble() - 1.0D) + mu);
/* 1099 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double nextStudentT(int nu)
/*      */   {
/* 1107 */     double ran = 0.0D;
/*      */     
/*      */ 
/* 1110 */     StudentTfunct strt = new StudentTfunct();
/*      */     
/*      */ 
/* 1113 */     strt.nu = nu;
/*      */     
/*      */ 
/* 1116 */     double centre = 0.0D;
/* 1117 */     double range = 100.0D;
/* 1118 */     if (nu > 2) { range = nu / (nu - 2);
/*      */     }
/*      */     
/* 1121 */     double lowerBound = centre - 5.0D * range;
/*      */     
/*      */ 
/* 1124 */     double upperBound = centre + 5.0D * range;
/*      */     
/*      */ 
/* 1127 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1130 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/* 1133 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/* 1136 */     realR.resetNaNexceptionToTrue();
/* 1137 */     realR.supressLimitReachedMessage();
/* 1138 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/* 1141 */     boolean test = true;
/* 1142 */     int ii = 0;
/* 1143 */     int imax = 10;
/* 1144 */     while (test)
/*      */     {
/*      */ 
/* 1147 */       strt.cfd = nextDouble();
/*      */       
/*      */ 
/* 1150 */       ran = realR.falsePosition(strt, lowerBound, upperBound);
/*      */       
/* 1152 */       if (!Double.isNaN(ran)) {
/* 1153 */         test = false;
/*      */ 
/*      */       }
/* 1156 */       else if (ii > imax) {
/* 1157 */         System.out.println("class: PsRandom,  method: studentT");
/* 1158 */         System.out.println(imax + " successive attempts at calculating a random Student's T deviate failed for values of nu = " + nu);
/* 1159 */         System.out.println("NaN returned");
/* 1160 */         ran = NaN.0D;
/* 1161 */         test = false;
/*      */       }
/*      */       else {
/* 1164 */         ii++;
/*      */       }
/*      */     }
/*      */     
/* 1168 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] studentTarray(int nu, int n)
/*      */   {
/* 1176 */     double[] ran = new double[n];
/*      */     
/*      */ 
/* 1179 */     StudentTfunct strt = new StudentTfunct();
/*      */     
/*      */ 
/* 1182 */     strt.nu = nu;
/*      */     
/*      */ 
/* 1185 */     double centre = 0.0D;
/* 1186 */     double range = 100.0D;
/* 1187 */     if (nu > 2) { range = nu / (nu - 2);
/*      */     }
/*      */     
/* 1190 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1193 */     double lowerBound = centre - 5.0D * range;
/*      */     
/*      */ 
/* 1196 */     double upperBound = centre + 5.0D * range;
/*      */     
/* 1198 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/* 1201 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/* 1204 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/* 1207 */       realR.resetNaNexceptionToTrue();
/* 1208 */       realR.supressLimitReachedMessage();
/* 1209 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/* 1212 */       boolean test = true;
/* 1213 */       int ii = 0;
/* 1214 */       int imax = 10;
/* 1215 */       while (test)
/*      */       {
/*      */ 
/* 1218 */         strt.cfd = nextDouble();
/*      */         
/*      */ 
/* 1221 */         double rangd = realR.falsePosition(strt, lowerBound, upperBound);
/*      */         
/* 1223 */         if (!Double.isNaN(rangd)) {
/* 1224 */           test = false;
/* 1225 */           ran[i] = rangd;
/*      */ 
/*      */         }
/* 1228 */         else if (ii > imax) {
/* 1229 */           System.out.println("class: PsRandom,  method: studentTarray");
/* 1230 */           System.out.println(imax + " successive attempts at calculating a random gamma deviate failed for values of nu = " + nu);
/* 1231 */           System.out.println("NaN returned");
/* 1232 */           ran[i] = NaN.0D;
/* 1233 */           test = false;
/*      */         }
/*      */         else {
/* 1236 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1241 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextBeta(double alpha, double beta)
/*      */   {
/* 1246 */     return nextBeta(0.0D, 1.0D, alpha, beta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double nextBeta(double min, double max, double alpha, double beta)
/*      */   {
/* 1253 */     double ran = 0.0D;
/*      */     
/*      */ 
/* 1256 */     BetaFunct bart = new BetaFunct();
/*      */     
/*      */ 
/* 1259 */     bart.alpha = alpha;
/* 1260 */     bart.beta = beta;
/* 1261 */     bart.min = min;
/* 1262 */     bart.max = max;
/*      */     
/*      */ 
/* 1265 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1268 */     double lowerBound = min;
/*      */     
/* 1270 */     double upperBound = max;
/*      */     
/*      */ 
/* 1273 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/* 1276 */     realR.noLowerBoundExtension();
/* 1277 */     realR.noUpperBoundExtension();
/*      */     
/*      */ 
/* 1280 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/* 1283 */     realR.resetNaNexceptionToTrue();
/* 1284 */     realR.supressLimitReachedMessage();
/* 1285 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/* 1288 */     boolean test = true;
/* 1289 */     int ii = 0;
/* 1290 */     int imax = 10;
/* 1291 */     while (test)
/*      */     {
/*      */ 
/* 1294 */       bart.cfd = nextDouble();
/*      */       
/*      */ 
/* 1297 */       ran = realR.falsePosition(bart, lowerBound, upperBound);
/*      */       
/* 1299 */       if (!Double.isNaN(ran)) {
/* 1300 */         test = false;
/*      */ 
/*      */       }
/* 1303 */       else if (ii > imax) {
/* 1304 */         System.out.println("class: PsRandom,  method: nextBeta");
/* 1305 */         System.out.println(imax + " successive attempts at calculating a random beta deviate failed for values of min = " + min + ", max = " + max + ", alpha = " + alpha + ", beta = " + beta);
/* 1306 */         System.out.println("NaN returned");
/* 1307 */         ran = NaN.0D;
/* 1308 */         test = false;
/*      */       }
/*      */       else {
/* 1311 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1316 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] betaArray(double alpha, double beta, int n)
/*      */   {
/* 1322 */     return betaArray(0.0D, 1.0D, alpha, beta, n);
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] betaArray(double min, double max, double alpha, double beta, int n)
/*      */   {
/* 1328 */     double[] ran = new double[n];
/*      */     
/*      */ 
/* 1331 */     BetaFunct bart = new BetaFunct();
/*      */     
/*      */ 
/* 1334 */     bart.alpha = alpha;
/* 1335 */     bart.beta = beta;
/* 1336 */     bart.min = min;
/* 1337 */     bart.max = max;
/*      */     
/*      */ 
/* 1340 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1343 */     double lowerBound = min;
/*      */     
/*      */ 
/* 1346 */     double upperBound = max;
/*      */     
/* 1348 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/* 1351 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/* 1354 */       realR.noLowerBoundExtension();
/* 1355 */       realR.noUpperBoundExtension();
/*      */       
/*      */ 
/* 1358 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/* 1361 */       realR.resetNaNexceptionToTrue();
/* 1362 */       realR.supressLimitReachedMessage();
/* 1363 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/* 1366 */       boolean test = true;
/* 1367 */       int ii = 0;
/* 1368 */       int imax = 10;
/* 1369 */       while (test)
/*      */       {
/*      */ 
/* 1372 */         bart.cfd = nextDouble();
/*      */         
/*      */ 
/* 1375 */         double rangd = realR.bisect(bart, lowerBound, upperBound);
/*      */         
/* 1377 */         if (!Double.isNaN(rangd)) {
/* 1378 */           test = false;
/* 1379 */           ran[i] = rangd;
/*      */ 
/*      */         }
/* 1382 */         else if (ii > imax) {
/* 1383 */           System.out.println("class: PsRandom,  method: betaArray");
/* 1384 */           System.out.println(imax + " successive attempts at calculating a random beta deviate failed for values of min = " + min + ", max = " + max + ", alpha = " + alpha + ", beta = " + beta);
/* 1385 */           System.out.println("NaN returned");
/* 1386 */           ran[i] = NaN.0D;
/* 1387 */           test = false;
/*      */         }
/*      */         else {
/* 1390 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1396 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double nextGamma(double mu, double beta, double gamma)
/*      */   {
/* 1403 */     double ran = 0.0D;
/*      */     
/*      */ 
/* 1406 */     GammaFunct gart = new GammaFunct();
/*      */     
/*      */ 
/* 1409 */     gart.mu = mu;
/* 1410 */     gart.beta = beta;
/* 1411 */     gart.gamma = gamma;
/*      */     
/*      */ 
/* 1414 */     double range = Math.sqrt(gamma) * beta;
/*      */     
/*      */ 
/* 1417 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1420 */     double lowerBound = mu;
/*      */     
/*      */ 
/* 1423 */     double upperBound = mu + 5.0D * range;
/* 1424 */     if (upperBound <= lowerBound) { upperBound += range;
/*      */     }
/*      */     
/* 1427 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/* 1430 */     realR.noLowerBoundExtension();
/*      */     
/*      */ 
/* 1433 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/* 1436 */     realR.resetNaNexceptionToTrue();
/* 1437 */     realR.supressLimitReachedMessage();
/* 1438 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/* 1441 */     boolean test = true;
/* 1442 */     int ii = 0;
/* 1443 */     int imax = 10;
/* 1444 */     while (test)
/*      */     {
/*      */ 
/* 1447 */       gart.cfd = nextDouble();
/*      */       
/*      */ 
/* 1450 */       ran = realR.bisect(gart, lowerBound, upperBound);
/*      */       
/* 1452 */       if (!Double.isNaN(ran)) {
/* 1453 */         test = false;
/*      */ 
/*      */       }
/* 1456 */       else if (ii > imax) {
/* 1457 */         System.out.println("class: PsRandom,  method: nextGamma");
/* 1458 */         System.out.println(imax + " successive attempts at calculating a random gamma deviate failed for values of mu = " + mu + ", beta = " + beta + ", gamma = " + gamma);
/* 1459 */         System.out.println("NaN returned");
/* 1460 */         ran = NaN.0D;
/* 1461 */         test = false;
/*      */       }
/*      */       else {
/* 1464 */         ii++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1469 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] gammaArray(double mu, double beta, double gamma, int n)
/*      */   {
/* 1476 */     double[] ran = new double[n];
/*      */     
/*      */ 
/* 1479 */     GammaFunct gart = new GammaFunct();
/*      */     
/*      */ 
/* 1482 */     gart.mu = mu;
/* 1483 */     gart.beta = beta;
/* 1484 */     gart.gamma = gamma;
/*      */     
/*      */ 
/* 1487 */     double range = Math.sqrt(gamma) * beta;
/*      */     
/*      */ 
/* 1490 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1493 */     double lowerBound = mu;
/*      */     
/*      */ 
/* 1496 */     double upperBound = mu + 5.0D * range;
/* 1497 */     if (upperBound <= lowerBound) { upperBound += range;
/*      */     }
/* 1499 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/* 1502 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/* 1505 */       realR.noLowerBoundExtension();
/*      */       
/*      */ 
/* 1508 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/* 1511 */       realR.resetNaNexceptionToTrue();
/* 1512 */       realR.supressLimitReachedMessage();
/* 1513 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/* 1516 */       boolean test = true;
/* 1517 */       int ii = 0;
/* 1518 */       int imax = 10;
/* 1519 */       while (test)
/*      */       {
/*      */ 
/* 1522 */         gart.cfd = nextDouble();
/*      */         
/*      */ 
/* 1525 */         double rangd = realR.bisect(gart, lowerBound, upperBound);
/*      */         
/* 1527 */         if (!Double.isNaN(rangd)) {
/* 1528 */           test = false;
/* 1529 */           ran[i] = rangd;
/*      */ 
/*      */         }
/* 1532 */         else if (ii > imax) {
/* 1533 */           System.out.println("class: PsRandom,  method: gammaArray");
/* 1534 */           System.out.println(imax + " successive attempts at calculating a random gamma deviate failed for values of mu = " + mu + ", beta = " + beta + ", gamma = " + gamma);
/* 1535 */           System.out.println("NaN returned");
/* 1536 */           ran[i] = NaN.0D;
/* 1537 */           test = false;
/*      */         }
/*      */         else {
/* 1540 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1546 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextErlang(double lambda, int kay)
/*      */   {
/* 1552 */     return nextGamma(0.0D, 1.0D / lambda, kay);
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] erlangArray(double lambda, int kay, int n)
/*      */   {
/* 1558 */     return gammaArray(0.0D, 1.0D / lambda, kay, n);
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] chiSquareArray(int nu, int n)
/*      */   {
/* 1564 */     if (nu <= 0) { throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*      */     }
/* 1566 */     double[] ran = new double[n];
/*      */     
/*      */ 
/* 1569 */     ChiSquareFunct csrt = new ChiSquareFunct();
/*      */     
/*      */ 
/* 1572 */     csrt.nu = nu;
/*      */     
/*      */ 
/* 1575 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1578 */     double lowerBound = 0.0D;
/*      */     
/*      */ 
/* 1581 */     double upperBound = 5.0D * Math.sqrt(2 * nu);
/*      */     
/* 1583 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/* 1586 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/* 1589 */       realR.noLowerBoundExtension();
/*      */       
/*      */ 
/* 1592 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/* 1595 */       realR.resetNaNexceptionToTrue();
/* 1596 */       realR.supressLimitReachedMessage();
/* 1597 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/* 1600 */       boolean test = true;
/* 1601 */       int ii = 0;
/* 1602 */       int imax = 10;
/* 1603 */       while (test)
/*      */       {
/*      */ 
/* 1606 */         csrt.cfd = nextDouble();
/*      */         
/*      */ 
/* 1609 */         double rangd = realR.falsePosition(csrt, lowerBound, upperBound);
/*      */         
/* 1611 */         if (!Double.isNaN(rangd)) {
/* 1612 */           test = false;
/* 1613 */           ran[i] = rangd;
/*      */ 
/*      */         }
/* 1616 */         else if (ii > imax) {
/* 1617 */           System.out.println("class: PsRandom,  method: chiSquareArray");
/* 1618 */           System.out.println(imax + " successive attempts at calculating a random chi square deviate failed for values of nu = " + nu);
/* 1619 */           System.out.println("NaN returned");
/* 1620 */           ran[i] = NaN.0D;
/* 1621 */           test = false;
/*      */         }
/*      */         else {
/* 1624 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1629 */     return ran;
/*      */   }
/*      */   
/*      */   public double nextChiSquare(int nu)
/*      */   {
/* 1634 */     if (nu <= 0) { throw new IllegalArgumentException("The degrees of freedom [nu], " + nu + ", must be greater than zero");
/*      */     }
/* 1636 */     double ran = 0.0D;
/*      */     
/*      */ 
/* 1639 */     ChiSquareFunct csrt = new ChiSquareFunct();
/*      */     
/*      */ 
/* 1642 */     csrt.nu = nu;
/*      */     
/*      */ 
/* 1645 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1648 */     double lowerBound = 0.0D;
/*      */     
/* 1650 */     double upperBound = 5.0D * Math.sqrt(2 * nu);
/*      */     
/*      */ 
/* 1653 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/* 1656 */     realR.noLowerBoundExtension();
/*      */     
/*      */ 
/* 1659 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/* 1662 */     realR.resetNaNexceptionToTrue();
/* 1663 */     realR.supressLimitReachedMessage();
/* 1664 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/* 1667 */     boolean test = true;
/* 1668 */     int ii = 0;
/* 1669 */     int imax = 10;
/* 1670 */     while (test)
/*      */     {
/*      */ 
/* 1673 */       csrt.cfd = nextDouble();
/*      */       
/*      */ 
/* 1676 */       ran = realR.falsePosition(csrt, lowerBound, upperBound);
/*      */       
/* 1678 */       if (!Double.isNaN(ran)) {
/* 1679 */         test = false;
/*      */ 
/*      */       }
/* 1682 */       else if (ii > imax) {
/* 1683 */         System.out.println("class: PsRandom,  method: nextChiSqauare");
/* 1684 */         System.out.println(imax + " successive attempts at calculating a random Chi Square deviate failed for values of nu = " + nu);
/* 1685 */         System.out.println("NaN returned");
/* 1686 */         ran = NaN.0D;
/* 1687 */         test = false;
/*      */       }
/*      */       else {
/* 1690 */         ii++;
/*      */       }
/*      */     }
/*      */     
/* 1694 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double nextF(int nu1, int nu2)
/*      */   {
/* 1700 */     double ran = NaN.0D;
/*      */     
/*      */ 
/* 1703 */     fFunct frt = new fFunct();
/*      */     
/*      */ 
/* 1706 */     frt.nu1 = nu1;
/* 1707 */     frt.nu2 = nu2;
/*      */     
/* 1709 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1712 */     double lowerBound = 0.0D;
/*      */     
/*      */ 
/* 1715 */     double upperBound = 10.0D;
/* 1716 */     if (nu2 > 4) { upperBound = 5.0D * Math.sqrt(2 * nu2 * nu2 * (nu1 + nu2 - 2) / (nu1 * (nu2 - 2) * (nu2 - 2) * (nu2 - 4)));
/*      */     }
/*      */     
/*      */ 
/* 1720 */     RealRoot realR = new RealRoot();
/*      */     
/*      */ 
/* 1723 */     realR.noLowerBoundExtension();
/*      */     
/*      */ 
/* 1726 */     realR.setTolerance(tolerance);
/*      */     
/*      */ 
/* 1729 */     realR.resetNaNexceptionToTrue();
/* 1730 */     realR.supressLimitReachedMessage();
/* 1731 */     realR.supressNaNmessage();
/*      */     
/*      */ 
/* 1734 */     boolean test = true;
/* 1735 */     int ii = 0;
/* 1736 */     int imax = 10;
/* 1737 */     while (test)
/*      */     {
/*      */ 
/* 1740 */       frt.cfd = nextDouble();
/*      */       
/*      */ 
/* 1743 */       ran = realR.falsePosition(frt, lowerBound, upperBound);
/*      */       
/* 1745 */       if (!Double.isNaN(ran)) {
/* 1746 */         test = false;
/*      */ 
/*      */       }
/* 1749 */       else if (ii > imax) {
/* 1750 */         System.out.println("class: PsRandom,  method: nextF");
/* 1751 */         System.out.println(imax + " successive attempts at calculating a random F-distribution deviate failed for values of nu1 = " + nu1 + ", nu2 = " + nu2);
/* 1752 */         System.out.println("NaN returned");
/* 1753 */         ran = NaN.0D;
/* 1754 */         test = false;
/*      */       }
/*      */       else {
/* 1757 */         ii++;
/*      */       }
/*      */     }
/*      */     
/* 1761 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] fArray(int nu1, int nu2, int n)
/*      */   {
/* 1767 */     double[] ran = new double[n];
/*      */     
/*      */ 
/* 1770 */     fFunct frt = new fFunct();
/*      */     
/*      */ 
/* 1773 */     frt.nu1 = nu1;
/* 1774 */     frt.nu2 = nu2;
/*      */     
/*      */ 
/* 1777 */     double tolerance = 1.0E-10D;
/*      */     
/*      */ 
/* 1780 */     double lowerBound = 0.0D;
/*      */     
/*      */ 
/* 1783 */     double upperBound = 10.0D;
/* 1784 */     if (nu2 > 4) { upperBound = 5.0D * Math.sqrt(2 * nu2 * nu2 * (nu1 + nu2 - 2) / (nu1 * (nu2 - 2) * (nu2 - 2) * (nu2 - 4)));
/*      */     }
/* 1786 */     for (int i = 0; i < n; i++)
/*      */     {
/*      */ 
/* 1789 */       RealRoot realR = new RealRoot();
/*      */       
/*      */ 
/* 1792 */       realR.noLowerBoundExtension();
/*      */       
/*      */ 
/* 1795 */       realR.setTolerance(tolerance);
/*      */       
/*      */ 
/* 1798 */       realR.resetNaNexceptionToTrue();
/* 1799 */       realR.supressLimitReachedMessage();
/* 1800 */       realR.supressNaNmessage();
/*      */       
/*      */ 
/* 1803 */       boolean test = true;
/* 1804 */       int ii = 0;
/* 1805 */       int imax = 10;
/* 1806 */       while (test)
/*      */       {
/*      */ 
/* 1809 */         frt.cfd = nextDouble();
/*      */         
/*      */ 
/* 1812 */         double rangd = realR.falsePosition(frt, lowerBound, upperBound);
/* 1813 */         if (!Double.isNaN(rangd)) {
/* 1814 */           test = false;
/* 1815 */           ran[i] = rangd;
/*      */ 
/*      */         }
/* 1818 */         else if (ii > imax) {
/* 1819 */           System.out.println("class: PsRandom,  method: fArray");
/* 1820 */           System.out.println(imax + " successive attempts at calculating a random f-distribution deviate failed for values of nu1 = " + nu1 + ", nu2 = " + nu2);
/* 1821 */           System.out.println("NaN returned");
/* 1822 */           ran[i] = NaN.0D;
/* 1823 */           test = false;
/*      */         }
/*      */         else {
/* 1826 */           ii++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1831 */     return ran;
/*      */   }
/*      */   
/*      */ 
/*      */   public int nextInteger(int top)
/*      */   {
/* 1837 */     double pr0 = nextDouble();
/* 1838 */     int pr1 = (int)Math.round(pr0 * top);
/* 1839 */     return pr1;
/*      */   }
/*      */   
/*      */   public int nextInteger(int bottom, int top)
/*      */   {
/* 1844 */     double pr0 = nextDouble();
/* 1845 */     int pr1 = (int)Math.round(pr0 * (top - bottom));
/* 1846 */     return pr1 + bottom;
/*      */   }
/*      */   
/*      */   public int[] integerArray(int arrayLength, int top)
/*      */   {
/* 1851 */     int[] array = new int[arrayLength];
/* 1852 */     for (int i = 0; i < arrayLength; i++) {
/* 1853 */       array[i] = nextInteger(top);
/*      */     }
/* 1855 */     return array;
/*      */   }
/*      */   
/*      */   public int[] integerArray(int arrayLength, int bottom, int top)
/*      */   {
/* 1860 */     int[] array = new int[arrayLength];
/* 1861 */     for (int i = 0; i < arrayLength; i++) {
/* 1862 */       array[i] = (nextInteger(bottom - top) + bottom);
/*      */     }
/* 1864 */     return array;
/*      */   }
/*      */   
/*      */ 
/*      */   public int[] uniqueIntegerArray(int bottom, int top)
/*      */   {
/* 1870 */     int range = top - bottom;
/* 1871 */     int[] array = uniqueIntegerArray(range);
/* 1872 */     for (int i = 0; i < range + 1; i++) array[i] += bottom;
/* 1873 */     return array;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int[] uniqueIntegerArray(int top)
/*      */   {
/* 1880 */     int numberOfIntegers = top + 1;
/* 1881 */     int[] array = new int[numberOfIntegers];
/* 1882 */     boolean allFound = false;
/* 1883 */     int nFound = 0;
/* 1884 */     boolean[] found = new boolean[numberOfIntegers];
/* 1885 */     for (int i = 0; i < numberOfIntegers; i++) { found[i] = false;
/*      */     }
/* 1887 */     boolean test0 = true;
/* 1888 */     while (test0) {
/* 1889 */       int ii = nextInteger(top);
/* 1890 */       if (found[ii] == 0) {
/* 1891 */         array[nFound] = ii;
/* 1892 */         found[ii] = true;
/* 1893 */         nFound++;
/* 1894 */         if (nFound == numberOfIntegers) test0 = false;
/*      */       }
/*      */     }
/* 1897 */     return array;
/*      */   }
/*      */   
/*      */   public static long getSerialVersionUID()
/*      */   {
/* 1902 */     return 1L;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/PsRandom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */