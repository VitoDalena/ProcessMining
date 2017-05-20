/*      */ package flanagan.roots;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexPoly;
/*      */ import flanagan.math.Fmath;
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
/*      */ 
/*      */ public class RealRoot
/*      */ {
/*   55 */   private double root = NaN.0D;
/*   56 */   private double tol = 1.0E-9D;
/*   57 */   private int iterMax = 3000;
/*   58 */   private int iterN = 0;
/*   59 */   private double upperBound = 0.0D;
/*   60 */   private double lowerBound = 0.0D;
/*   61 */   private double estimate = 0.0D;
/*   62 */   private int maximumBoundsExtension = 100;
/*      */   
/*      */ 
/*   65 */   private boolean noBoundExtensions = false;
/*   66 */   private boolean noLowerBoundExtensions = false;
/*   67 */   private boolean noUpperBoundExtensions = false;
/*   68 */   private boolean supressLimitReachedMessage = false;
/*   69 */   private boolean returnNaN = false;
/*      */   
/*   71 */   private boolean supressNaNmessage = false;
/*      */   
/*      */ 
/*      */ 
/*   75 */   private static int staticIterMax = 3000;
/*      */   
/*   77 */   private static int maximumStaticBoundsExtension = 100;
/*      */   
/*      */ 
/*   80 */   private static boolean noStaticBoundExtensions = false;
/*   81 */   private static boolean noStaticLowerBoundExtensions = false;
/*   82 */   private static boolean noStaticUpperBoundExtensions = false;
/*   83 */   private static boolean staticReturnNaN = false;
/*      */   
/*   85 */   private static double realTol = 1.0E-14D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLowerBound(double lower)
/*      */   {
/*   95 */     this.lowerBound = lower;
/*      */   }
/*      */   
/*      */   public void setUpperBound(double upper)
/*      */   {
/*  100 */     this.upperBound = upper;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void resetNaNexceptionToTrue()
/*      */   {
/*  107 */     this.returnNaN = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void resetNaNexceptionToFalse()
/*      */   {
/*  114 */     this.returnNaN = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void supressNaNmessage()
/*      */   {
/*  120 */     this.supressNaNmessage = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void allowNaNmessage()
/*      */   {
/*  126 */     this.supressNaNmessage = false;
/*      */   }
/*      */   
/*      */   public void setEstimate(double estimate)
/*      */   {
/*  131 */     this.estimate = estimate;
/*      */   }
/*      */   
/*      */   public void setTolerance(double tolerance)
/*      */   {
/*  136 */     this.tol = tolerance;
/*      */   }
/*      */   
/*      */   public double getTolerance()
/*      */   {
/*  141 */     return this.tol;
/*      */   }
/*      */   
/*      */   public void setIterMax(int imax)
/*      */   {
/*  146 */     this.iterMax = imax;
/*      */   }
/*      */   
/*      */   public int getIterMax()
/*      */   {
/*  151 */     return this.iterMax;
/*      */   }
/*      */   
/*      */   public int getIterN()
/*      */   {
/*  156 */     return this.iterN;
/*      */   }
/*      */   
/*      */   public void setmaximumStaticBoundsExtension(int maximumBoundsExtension)
/*      */   {
/*  161 */     this.maximumBoundsExtension = maximumBoundsExtension;
/*      */   }
/*      */   
/*      */   public void noBoundsExtensions()
/*      */   {
/*  166 */     this.noBoundExtensions = true;
/*  167 */     this.noLowerBoundExtensions = true;
/*  168 */     this.noUpperBoundExtensions = true;
/*      */   }
/*      */   
/*      */   public void noLowerBoundExtension()
/*      */   {
/*  173 */     this.noLowerBoundExtensions = true;
/*  174 */     if (this.noUpperBoundExtensions) this.noBoundExtensions = true;
/*      */   }
/*      */   
/*      */   public void noUpperBoundExtension()
/*      */   {
/*  179 */     this.noUpperBoundExtensions = true;
/*  180 */     if (this.noLowerBoundExtensions) { this.noBoundExtensions = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void supressLimitReachedMessage()
/*      */   {
/*  186 */     this.supressLimitReachedMessage = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public double brent(RealRootFunction g)
/*      */   {
/*  192 */     return brent(g, this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */ 
/*      */   public double brent(RealRootFunction g, double lower, double upper)
/*      */   {
/*  198 */     this.lowerBound = lower;
/*  199 */     this.upperBound = upper;
/*      */     
/*      */ 
/*  202 */     if (upper == lower) { throw new IllegalArgumentException("upper cannot equal lower");
/*      */     }
/*  204 */     boolean testConv = true;
/*  205 */     this.iterN = 0;
/*  206 */     double temp = 0.0D;
/*      */     
/*  208 */     if (upper < lower) {
/*  209 */       temp = upper;
/*  210 */       upper = lower;
/*  211 */       lower = temp;
/*      */     }
/*      */     
/*      */ 
/*  215 */     double fu = g.function(upper);
/*      */     
/*  217 */     double fl = g.function(lower);
/*  218 */     if (Double.isNaN(fl)) {
/*  219 */       if (this.returnNaN) {
/*  220 */         if (!this.supressNaNmessage) System.out.println("Realroot: brent: lower bound returned NaN as the function value - NaN returned as root");
/*  221 */         return NaN.0D;
/*      */       }
/*      */       
/*  224 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/*  227 */     if (Double.isNaN(fu)) {
/*  228 */       if (this.returnNaN) {
/*  229 */         if (!this.supressNaNmessage) System.out.println("Realroot: brent: upper bound returned NaN as the function value - NaN returned as root");
/*  230 */         return NaN.0D;
/*      */       }
/*      */       
/*  233 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  238 */     boolean testBounds = true;
/*  239 */     int numberOfBoundsExtension = 0;
/*  240 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/*  241 */     while (testBounds) {
/*  242 */       if (fu * fl <= 0.0D) {
/*  243 */         testBounds = false;
/*      */       }
/*      */       else {
/*  246 */         if (this.noBoundExtensions) {
/*  247 */           String message = "RealRoot.brent: root not bounded and no extension to bounds allowed\n";
/*  248 */           message = message + "NaN returned";
/*  249 */           if (!this.supressNaNmessage) System.out.println(message);
/*  250 */           return NaN.0D;
/*      */         }
/*      */         
/*  253 */         numberOfBoundsExtension++;
/*  254 */         if (numberOfBoundsExtension > this.maximumBoundsExtension) {
/*  255 */           String message = "RealRoot.brent: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
/*  256 */           message = message + "NaN returned";
/*  257 */           if (!this.supressNaNmessage) System.out.println(message);
/*  258 */           return NaN.0D;
/*      */         }
/*  260 */         if (!this.noLowerBoundExtensions) {
/*  261 */           lower -= initialBoundsDifference;
/*  262 */           fl = g.function(lower);
/*      */         }
/*  264 */         if (!this.noUpperBoundExtensions) {
/*  265 */           upper += initialBoundsDifference;
/*  266 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  273 */     if (fl == 0.0D) {
/*  274 */       this.root = lower;
/*  275 */       testConv = false;
/*      */     }
/*  277 */     if (fu == 0.0D) {
/*  278 */       this.root = upper;
/*  279 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/*  283 */     double mid = (lower + upper) / 2.0D;
/*  284 */     double lastMidB = mid;
/*  285 */     double fm = g.function(mid);
/*  286 */     double diff = mid - lower;
/*  287 */     double fmB = fm;
/*  288 */     double lastMid = mid;
/*  289 */     boolean lastMethod = true;
/*  290 */     boolean nextMethod = true;
/*      */     
/*      */ 
/*  293 */     double rr = 0.0D;double ss = 0.0D;double tt = 0.0D;double pp = 0.0D;double qq = 0.0D;
/*  294 */     while (testConv)
/*      */     {
/*  296 */       if ((fm == 0.0D) || (Math.abs(diff) < this.tol)) {
/*  297 */         testConv = false;
/*  298 */         if (fm == 0.0D) {
/*  299 */           this.root = lastMid;
/*      */ 
/*      */         }
/*  302 */         else if (Math.abs(diff) < this.tol) this.root = mid;
/*      */       }
/*      */       else
/*      */       {
/*  306 */         lastMethod = nextMethod;
/*      */         
/*  308 */         if (lastMethod) {
/*  309 */           if ((mid < lower) || (mid > upper))
/*      */           {
/*  311 */             nextMethod = false;
/*      */           }
/*      */           else {
/*  314 */             fmB = fm;
/*  315 */             lastMidB = mid;
/*      */           }
/*      */         }
/*      */         else {
/*  319 */           nextMethod = true;
/*      */         }
/*  321 */         if (nextMethod)
/*      */         {
/*  323 */           fl = g.function(lower);
/*  324 */           fm = g.function(mid);
/*  325 */           fu = g.function(upper);
/*  326 */           rr = fm / fu;
/*  327 */           ss = fm / fl;
/*  328 */           tt = fl / fu;
/*  329 */           pp = ss * (tt * (rr - tt) * (upper - mid) - (1.0D - rr) * (mid - lower));
/*  330 */           qq = (tt - 1.0D) * (rr - 1.0D) * (ss - 1.0D);
/*  331 */           lastMid = mid;
/*  332 */           diff = pp / qq;
/*  333 */           mid += diff;
/*      */         }
/*      */         else
/*      */         {
/*  337 */           fm = fmB;
/*  338 */           mid = lastMidB;
/*  339 */           if (fm * fl > 0.0D) {
/*  340 */             lower = mid;
/*  341 */             fl = fm;
/*      */           }
/*      */           else {
/*  344 */             upper = mid;
/*  345 */             fu = fm;
/*      */           }
/*  347 */           lastMid = mid;
/*  348 */           mid = (lower + upper) / 2.0D;
/*  349 */           fm = g.function(mid);
/*  350 */           diff = mid - lastMid;
/*  351 */           fmB = fm;
/*  352 */           lastMidB = mid;
/*      */         }
/*      */       }
/*  355 */       this.iterN += 1;
/*  356 */       if (this.iterN > this.iterMax) {
/*  357 */         if (!this.supressLimitReachedMessage) {
/*  358 */           if (!this.supressNaNmessage) System.out.println("Class: RealRoot; method: brent; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/*  359 */           if (!this.supressNaNmessage) System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + this.tol);
/*      */         }
/*  361 */         this.root = mid;
/*  362 */         testConv = false;
/*      */       }
/*      */     }
/*  365 */     return this.root;
/*      */   }
/*      */   
/*      */ 
/*      */   public double bisect(RealRootFunction g)
/*      */   {
/*  371 */     return bisect(g, this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */   public double bisect(RealRootFunction g, double lower, double upper)
/*      */   {
/*  376 */     this.lowerBound = lower;
/*  377 */     this.upperBound = upper;
/*      */     
/*      */ 
/*  380 */     if (upper == lower) throw new IllegalArgumentException("upper cannot equal lower");
/*  381 */     if (upper < lower) {
/*  382 */       double temp = upper;
/*  383 */       upper = lower;
/*  384 */       lower = temp;
/*      */     }
/*      */     
/*  387 */     boolean testConv = true;
/*  388 */     this.iterN = 0;
/*  389 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/*  392 */     double fu = g.function(upper);
/*      */     
/*  394 */     double fl = g.function(lower);
/*  395 */     if (Double.isNaN(fl)) {
/*  396 */       if (this.returnNaN) {
/*  397 */         if (!this.supressNaNmessage) System.out.println("RealRoot: bisect: lower bound returned NaN as the function value - NaN returned as root");
/*  398 */         return NaN.0D;
/*      */       }
/*      */       
/*  401 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/*  404 */     if (Double.isNaN(fu)) {
/*  405 */       if (this.returnNaN) {
/*  406 */         if (!this.supressNaNmessage) System.out.println("RealRoot: bisect: upper bound returned NaN as the function value - NaN returned as root");
/*  407 */         return NaN.0D;
/*      */       }
/*      */       
/*  410 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*  414 */     boolean testBounds = true;
/*  415 */     int numberOfBoundsExtension = 0;
/*  416 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/*  417 */     while (testBounds) {
/*  418 */       if (fu * fl <= 0.0D) {
/*  419 */         testBounds = false;
/*      */       }
/*      */       else {
/*  422 */         if (this.noBoundExtensions) {
/*  423 */           String message = "RealRoot.bisect: root not bounded and no extension to bounds allowed\n";
/*  424 */           message = message + "NaN returned";
/*  425 */           if (!this.supressNaNmessage) System.out.println(message);
/*  426 */           return NaN.0D;
/*      */         }
/*      */         
/*      */ 
/*  430 */         numberOfBoundsExtension++;
/*  431 */         if (numberOfBoundsExtension > this.maximumBoundsExtension) {
/*  432 */           String message = "RealRoot.bisect: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
/*  433 */           message = message + "NaN returned";
/*  434 */           if (!this.supressNaNmessage) System.out.println(message);
/*  435 */           return NaN.0D;
/*      */         }
/*  437 */         if (!this.noLowerBoundExtensions) {
/*  438 */           lower -= initialBoundsDifference;
/*  439 */           fl = g.function(lower);
/*      */         }
/*  441 */         if (!this.noUpperBoundExtensions) {
/*  442 */           upper += initialBoundsDifference;
/*  443 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  450 */     if (fl == 0.0D) {
/*  451 */       this.root = lower;
/*  452 */       testConv = false;
/*      */     }
/*  454 */     if (fu == 0.0D) {
/*  455 */       this.root = upper;
/*  456 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/*  460 */     double mid = (lower + upper) / 2.0D;
/*  461 */     double lastMid = 1.0E300D;
/*  462 */     double fm = g.function(mid);
/*  463 */     while (testConv) {
/*  464 */       if ((fm == 0.0D) || (diff < this.tol)) {
/*  465 */         testConv = false;
/*  466 */         this.root = mid;
/*      */       }
/*  468 */       if (fm * fl > 0.0D) {
/*  469 */         lower = mid;
/*  470 */         fl = fm;
/*      */       }
/*      */       else {
/*  473 */         upper = mid;
/*  474 */         fu = fm;
/*      */       }
/*  476 */       lastMid = mid;
/*  477 */       mid = (lower + upper) / 2.0D;
/*  478 */       fm = g.function(mid);
/*  479 */       diff = Math.abs(mid - lastMid);
/*  480 */       this.iterN += 1;
/*  481 */       if (this.iterN > this.iterMax) {
/*  482 */         if (!this.supressLimitReachedMessage) {
/*  483 */           if (!this.supressNaNmessage) System.out.println("Class: RealRoot; method: bisect; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/*  484 */           if (!this.supressNaNmessage) System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + this.tol);
/*      */         }
/*  486 */         this.root = mid;
/*  487 */         testConv = false;
/*      */       }
/*      */     }
/*  490 */     return this.root;
/*      */   }
/*      */   
/*      */ 
/*      */   public double falsePosition(RealRootFunction g)
/*      */   {
/*  496 */     return falsePosition(g, this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */   public double falsePosition(RealRootFunction g, double lower, double upper)
/*      */   {
/*  501 */     this.lowerBound = lower;
/*  502 */     this.upperBound = upper;
/*      */     
/*      */ 
/*  505 */     if (upper == lower) throw new IllegalArgumentException("upper cannot equal lower");
/*  506 */     if (upper < lower) {
/*  507 */       double temp = upper;
/*  508 */       upper = lower;
/*  509 */       lower = temp;
/*      */     }
/*      */     
/*  512 */     boolean testConv = true;
/*  513 */     this.iterN = 0;
/*  514 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/*  517 */     double fu = g.function(upper);
/*      */     
/*  519 */     double fl = g.function(lower);
/*  520 */     if (Double.isNaN(fl)) {
/*  521 */       if (this.returnNaN) {
/*  522 */         if (!this.supressNaNmessage) System.out.println("RealRoot: fals: ePositionlower bound returned NaN as the function value - NaN returned as root");
/*  523 */         return NaN.0D;
/*      */       }
/*      */       
/*  526 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/*  529 */     if (Double.isNaN(fu)) {
/*  530 */       if (this.returnNaN) {
/*  531 */         if (!this.supressNaNmessage) System.out.println("RealRoot: falsePosition: upper bound returned NaN as the function value - NaN returned as root");
/*  532 */         return NaN.0D;
/*      */       }
/*      */       
/*  535 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  540 */     boolean testBounds = true;
/*  541 */     int numberOfBoundsExtension = 0;
/*  542 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/*  543 */     while (testBounds) {
/*  544 */       if (fu * fl <= 0.0D) {
/*  545 */         testBounds = false;
/*      */       }
/*      */       else {
/*  548 */         if (this.noBoundExtensions) {
/*  549 */           String message = "RealRoot.falsePosition: root not bounded and no extension to bounds allowed\n";
/*  550 */           message = message + "NaN returned";
/*  551 */           if (!this.supressNaNmessage) System.out.println(message);
/*  552 */           return NaN.0D;
/*      */         }
/*      */         
/*  555 */         numberOfBoundsExtension++;
/*  556 */         if (numberOfBoundsExtension > this.maximumBoundsExtension) {
/*  557 */           String message = "RealRoot.falsePosition: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
/*  558 */           message = message + "NaN returned";
/*  559 */           if (!this.supressNaNmessage) System.out.println(message);
/*  560 */           return NaN.0D;
/*      */         }
/*  562 */         if (!this.noLowerBoundExtensions) {
/*  563 */           lower -= initialBoundsDifference;
/*  564 */           fl = g.function(lower);
/*      */         }
/*  566 */         if (!this.noUpperBoundExtensions) {
/*  567 */           upper += initialBoundsDifference;
/*  568 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  575 */     if (fl == 0.0D) {
/*  576 */       this.root = lower;
/*  577 */       testConv = false;
/*      */     }
/*  579 */     if (fu == 0.0D) {
/*  580 */       this.root = upper;
/*  581 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/*  585 */     double mid = lower + (upper - lower) * Math.abs(fl) / (Math.abs(fl) + Math.abs(fu));
/*  586 */     double lastMid = 1.0E300D;
/*  587 */     double fm = g.function(mid);
/*  588 */     while (testConv) {
/*  589 */       if ((fm == 0.0D) || (diff < this.tol)) {
/*  590 */         testConv = false;
/*  591 */         this.root = mid;
/*      */       }
/*  593 */       if (fm * fl > 0.0D) {
/*  594 */         lower = mid;
/*  595 */         fl = fm;
/*      */       }
/*      */       else {
/*  598 */         upper = mid;
/*  599 */         fu = fm;
/*      */       }
/*  601 */       lastMid = mid;
/*  602 */       mid = lower + (upper - lower) * Math.abs(fl) / (Math.abs(fl) + Math.abs(fu));
/*  603 */       fm = g.function(mid);
/*  604 */       diff = Math.abs(mid - lastMid);
/*  605 */       this.iterN += 1;
/*  606 */       if (this.iterN > this.iterMax) {
/*  607 */         if (!this.supressLimitReachedMessage) {
/*  608 */           if (!this.supressNaNmessage) System.out.println("Class: RealRoot; method: falsePostion; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/*  609 */           if (!this.supressNaNmessage) System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + this.tol);
/*      */         }
/*  611 */         this.root = mid;
/*  612 */         testConv = false;
/*      */       }
/*      */     }
/*  615 */     return this.root;
/*      */   }
/*      */   
/*      */ 
/*      */   public double bisectNewtonRaphson(RealRootDerivFunction g)
/*      */   {
/*  621 */     return bisectNewtonRaphson(g, this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */ 
/*      */   public double bisectNewtonRaphson(RealRootDerivFunction g, double lower, double upper)
/*      */   {
/*  627 */     this.lowerBound = lower;
/*  628 */     this.upperBound = upper;
/*      */     
/*      */ 
/*  631 */     if (upper == lower) { throw new IllegalArgumentException("upper cannot equal lower");
/*      */     }
/*  633 */     boolean testConv = true;
/*  634 */     this.iterN = 0;
/*  635 */     double temp = 0.0D;
/*      */     
/*  637 */     if (upper < lower) {
/*  638 */       temp = upper;
/*  639 */       upper = lower;
/*  640 */       lower = temp;
/*      */     }
/*      */     
/*      */ 
/*  644 */     double[] f = g.function(upper);
/*  645 */     double fu = f[0];
/*      */     
/*  647 */     f = g.function(lower);
/*  648 */     double fl = f[0];
/*  649 */     if (Double.isNaN(fl)) {
/*  650 */       if (this.returnNaN) {
/*  651 */         if (!this.supressNaNmessage) System.out.println("RealRoot: bisectNewtonRaphson: lower bound returned NaN as the function value - NaN returned as root");
/*  652 */         return NaN.0D;
/*      */       }
/*      */       
/*  655 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/*  658 */     if (Double.isNaN(fu)) {
/*  659 */       if (this.returnNaN) {
/*  660 */         if (!this.supressNaNmessage) System.out.println("RealRoot: bisectNewtonRaphson: upper bound returned NaN as the function value - NaN returned as root");
/*  661 */         return NaN.0D;
/*      */       }
/*      */       
/*  664 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  669 */     boolean testBounds = true;
/*  670 */     int numberOfBoundsExtension = 0;
/*  671 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/*  672 */     while (testBounds) {
/*  673 */       if (fu * fl <= 0.0D) {
/*  674 */         testBounds = false;
/*      */       }
/*      */       else {
/*  677 */         if (this.noBoundExtensions) {
/*  678 */           String message = "RealRoot.bisectNewtonRaphson: root not bounded and no extension to bounds allowed\n";
/*  679 */           message = message + "NaN returned";
/*  680 */           if (!this.supressNaNmessage) System.out.println(message);
/*  681 */           return NaN.0D;
/*      */         }
/*      */         
/*  684 */         numberOfBoundsExtension++;
/*  685 */         if (numberOfBoundsExtension > this.maximumBoundsExtension) {
/*  686 */           String message = "RealRoot.bisectNewtonRaphson: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
/*  687 */           message = message + "NaN returned";
/*  688 */           if (!this.supressNaNmessage) System.out.println(message);
/*  689 */           return NaN.0D;
/*      */         }
/*  691 */         if (!this.noLowerBoundExtensions) {
/*  692 */           lower -= initialBoundsDifference;
/*  693 */           f = g.function(lower);
/*  694 */           fl = f[0];
/*      */         }
/*  696 */         if (!this.noUpperBoundExtensions) {
/*  697 */           upper += initialBoundsDifference;
/*  698 */           f = g.function(upper);
/*  699 */           fu = f[0];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  706 */     if (fl == 0.0D) {
/*  707 */       this.root = lower;
/*  708 */       testConv = false;
/*      */     }
/*  710 */     if (fu == 0.0D) {
/*  711 */       this.root = upper;
/*  712 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/*  716 */     double mid = (lower + upper) / 2.0D;
/*  717 */     double lastMidB = mid;
/*  718 */     f = g.function(mid);
/*  719 */     double diff = f[0] / f[1];
/*  720 */     double fm = f[0];
/*  721 */     double fmB = fm;
/*  722 */     double lastMid = mid;
/*  723 */     mid -= diff;
/*  724 */     boolean lastMethod = true;
/*  725 */     boolean nextMethod = true;
/*      */     
/*      */ 
/*  728 */     while (testConv)
/*      */     {
/*  730 */       if ((fm == 0.0D) || (Math.abs(diff) < this.tol)) {
/*  731 */         testConv = false;
/*  732 */         if (fm == 0.0D) {
/*  733 */           this.root = lastMid;
/*      */ 
/*      */         }
/*  736 */         else if (Math.abs(diff) < this.tol) this.root = mid;
/*      */       }
/*      */       else
/*      */       {
/*  740 */         lastMethod = nextMethod;
/*      */         
/*  742 */         if (lastMethod) {
/*  743 */           if ((mid < lower) || (mid > upper))
/*      */           {
/*  745 */             nextMethod = false;
/*      */           }
/*      */           else {
/*  748 */             fmB = fm;
/*  749 */             lastMidB = mid;
/*      */           }
/*      */         }
/*      */         else {
/*  753 */           nextMethod = true;
/*      */         }
/*  755 */         if (nextMethod)
/*      */         {
/*  757 */           f = g.function(mid);
/*  758 */           fm = f[0];
/*  759 */           diff = f[0] / f[1];
/*  760 */           lastMid = mid;
/*  761 */           mid -= diff;
/*      */         }
/*      */         else
/*      */         {
/*  765 */           fm = fmB;
/*  766 */           mid = lastMidB;
/*  767 */           if (fm * fl > 0.0D) {
/*  768 */             lower = mid;
/*  769 */             fl = fm;
/*      */           }
/*      */           else {
/*  772 */             upper = mid;
/*  773 */             fu = fm;
/*      */           }
/*  775 */           lastMid = mid;
/*  776 */           mid = (lower + upper) / 2.0D;
/*  777 */           f = g.function(mid);
/*  778 */           fm = f[0];
/*  779 */           diff = mid - lastMid;
/*  780 */           fmB = fm;
/*  781 */           lastMidB = mid;
/*      */         }
/*      */       }
/*  784 */       this.iterN += 1;
/*  785 */       if (this.iterN > this.iterMax) {
/*  786 */         if (!this.supressLimitReachedMessage) {
/*  787 */           if (!this.supressNaNmessage) System.out.println("Class: RealRoot; method: bisectNewtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/*  788 */           if (!this.supressNaNmessage) System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + this.tol);
/*      */         }
/*  790 */         this.root = mid;
/*  791 */         testConv = false;
/*      */       }
/*      */     }
/*  794 */     return this.root;
/*      */   }
/*      */   
/*      */ 
/*      */   public double newtonRaphson(RealRootDerivFunction g)
/*      */   {
/*  800 */     return newtonRaphson(g, this.estimate);
/*      */   }
/*      */   
/*      */ 
/*      */   public double newtonRaphson(RealRootDerivFunction g, double x)
/*      */   {
/*  806 */     this.estimate = x;
/*  807 */     boolean testConv = true;
/*  808 */     this.iterN = 0;
/*  809 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/*  812 */     double[] f = g.function(x);
/*  813 */     if (Double.isNaN(f[0])) {
/*  814 */       if (this.returnNaN) {
/*  815 */         if (!this.supressNaNmessage) System.out.println("RealRoot: newtonRaphson: NaN returned as the function value - NaN returned as root");
/*  816 */         return NaN.0D;
/*      */       }
/*      */       
/*  819 */       throw new ArithmeticException("NaN returned as the function value");
/*      */     }
/*      */     
/*  822 */     if (Double.isNaN(f[1])) {
/*  823 */       if (this.returnNaN) {
/*  824 */         if (!this.supressNaNmessage) System.out.println("RealRoot: newtonRaphson: NaN returned as the derivative function value - NaN returned as root");
/*  825 */         return NaN.0D;
/*      */       }
/*      */       
/*  828 */       throw new ArithmeticException("NaN returned as the derivative function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  834 */     while (testConv) {
/*  835 */       diff = f[0] / f[1];
/*  836 */       if ((f[0] == 0.0D) || (Math.abs(diff) < this.tol)) {
/*  837 */         this.root = x;
/*  838 */         testConv = false;
/*      */       }
/*      */       else {
/*  841 */         x -= diff;
/*  842 */         f = g.function(x);
/*  843 */         if (Double.isNaN(f[0])) throw new ArithmeticException("NaN returned as the function value");
/*  844 */         if (Double.isNaN(f[1])) throw new ArithmeticException("NaN returned as the derivative function value");
/*  845 */         if (Double.isNaN(f[0])) {
/*  846 */           if (this.returnNaN) {
/*  847 */             if (!this.supressNaNmessage) System.out.println("RealRoot: bisect: NaN as the function value - NaN returned as root");
/*  848 */             return NaN.0D;
/*      */           }
/*      */           
/*  851 */           throw new ArithmeticException("NaN as the function value");
/*      */         }
/*      */         
/*  854 */         if (Double.isNaN(f[1])) {
/*  855 */           if (this.returnNaN) {
/*  856 */             if (!this.supressNaNmessage) System.out.println("NaN as the function value - NaN returned as root");
/*  857 */             return NaN.0D;
/*      */           }
/*      */           
/*  860 */           throw new ArithmeticException("NaN as the function value");
/*      */         }
/*      */       }
/*      */       
/*  864 */       this.iterN += 1;
/*  865 */       if (this.iterN > this.iterMax) {
/*  866 */         if (!this.supressLimitReachedMessage) {
/*  867 */           if (!this.supressNaNmessage) System.out.println("Class: RealRoot; method: newtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(x, 4) + ", returned");
/*  868 */           if (!this.supressNaNmessage) System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + this.tol);
/*      */         }
/*  870 */         this.root = x;
/*  871 */         testConv = false;
/*      */       }
/*      */     }
/*  874 */     return this.root;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStaticIterMax(int imax)
/*      */   {
/*  881 */     staticIterMax = imax;
/*      */   }
/*      */   
/*      */   public int getStaticIterMax()
/*      */   {
/*  886 */     return staticIterMax;
/*      */   }
/*      */   
/*      */   public void setStaticMaximumStaticBoundsExtension(int maximumBoundsExtension)
/*      */   {
/*  891 */     maximumStaticBoundsExtension = maximumBoundsExtension;
/*      */   }
/*      */   
/*      */   public void noStaticBoundsExtensions()
/*      */   {
/*  896 */     noStaticBoundExtensions = true;
/*  897 */     noStaticLowerBoundExtensions = true;
/*  898 */     noStaticUpperBoundExtensions = true;
/*      */   }
/*      */   
/*      */   public void noStaticLowerBoundExtension()
/*      */   {
/*  903 */     noStaticLowerBoundExtensions = true;
/*  904 */     if (noStaticUpperBoundExtensions) noStaticBoundExtensions = true;
/*      */   }
/*      */   
/*      */   public void noStaticUpperBoundExtension()
/*      */   {
/*  909 */     noStaticUpperBoundExtensions = true;
/*  910 */     if (noStaticLowerBoundExtensions) { noStaticBoundExtensions = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void resetStaticNaNexceptionToTrue()
/*      */   {
/*  917 */     staticReturnNaN = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void resetStaticNaNexceptionToFalse()
/*      */   {
/*  924 */     staticReturnNaN = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double brent(RealRootFunction g, double lower, double upper, double tol)
/*      */   {
/*  934 */     if (upper == lower) { throw new IllegalArgumentException("upper cannot equal lower");
/*      */     }
/*  936 */     double root = NaN.0D;
/*  937 */     boolean testConv = true;
/*  938 */     int iterN = 0;
/*  939 */     double temp = 0.0D;
/*      */     
/*  941 */     if (upper < lower) {
/*  942 */       temp = upper;
/*  943 */       upper = lower;
/*  944 */       lower = temp;
/*      */     }
/*      */     
/*      */ 
/*  948 */     double fu = g.function(upper);
/*      */     
/*  950 */     double fl = g.function(lower);
/*  951 */     if (Double.isNaN(fl)) {
/*  952 */       if (staticReturnNaN) {
/*  953 */         System.out.println("Realroot: brent: lower bound returned NaN as the function value - NaN returned as root");
/*  954 */         return NaN.0D;
/*      */       }
/*      */       
/*  957 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/*  960 */     if (Double.isNaN(fu)) {
/*  961 */       if (staticReturnNaN) {
/*  962 */         System.out.println("Realroot: brent: upper bound returned NaN as the function value - NaN returned as root");
/*  963 */         return NaN.0D;
/*      */       }
/*      */       
/*  966 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  971 */     boolean testBounds = true;
/*  972 */     int numberOfBoundsExtension = 0;
/*  973 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/*  974 */     while (testBounds) {
/*  975 */       if (fu * fl <= 0.0D) {
/*  976 */         testBounds = false;
/*      */       }
/*      */       else {
/*  979 */         if (noStaticBoundExtensions) {
/*  980 */           String message = "RealRoot.brent: root not bounded and no extension to bounds allowed\n";
/*  981 */           message = message + "NaN returned";
/*  982 */           System.out.println(message);
/*  983 */           return NaN.0D;
/*      */         }
/*      */         
/*  986 */         numberOfBoundsExtension++;
/*  987 */         if (numberOfBoundsExtension > maximumStaticBoundsExtension) {
/*  988 */           String message = "RealRoot.brent: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
/*  989 */           message = message + "NaN returned";
/*  990 */           System.out.println(message);
/*  991 */           return NaN.0D;
/*      */         }
/*  993 */         if (!noStaticLowerBoundExtensions) {
/*  994 */           lower -= initialBoundsDifference;
/*  995 */           fl = g.function(lower);
/*      */         }
/*  997 */         if (!noStaticUpperBoundExtensions) {
/*  998 */           upper += initialBoundsDifference;
/*  999 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1006 */     if (fl == 0.0D) {
/* 1007 */       root = lower;
/* 1008 */       testConv = false;
/*      */     }
/* 1010 */     if (fu == 0.0D) {
/* 1011 */       root = upper;
/* 1012 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/* 1016 */     double mid = (lower + upper) / 2.0D;
/* 1017 */     double lastMidB = mid;
/* 1018 */     double fm = g.function(mid);
/* 1019 */     double diff = mid - lower;
/* 1020 */     double fmB = fm;
/* 1021 */     double lastMid = mid;
/* 1022 */     boolean lastMethod = true;
/* 1023 */     boolean nextMethod = true;
/*      */     
/*      */ 
/* 1026 */     double rr = 0.0D;double ss = 0.0D;double tt = 0.0D;double pp = 0.0D;double qq = 0.0D;
/* 1027 */     while (testConv)
/*      */     {
/* 1029 */       if ((fm == 0.0D) || (Math.abs(diff) < tol)) {
/* 1030 */         testConv = false;
/* 1031 */         if (fm == 0.0D) {
/* 1032 */           root = lastMid;
/*      */ 
/*      */         }
/* 1035 */         else if (Math.abs(diff) < tol) root = mid;
/*      */       }
/*      */       else
/*      */       {
/* 1039 */         lastMethod = nextMethod;
/*      */         
/* 1041 */         if (lastMethod) {
/* 1042 */           if ((mid < lower) || (mid > upper))
/*      */           {
/* 1044 */             nextMethod = false;
/*      */           }
/*      */           else {
/* 1047 */             fmB = fm;
/* 1048 */             lastMidB = mid;
/*      */           }
/*      */         }
/*      */         else {
/* 1052 */           nextMethod = true;
/*      */         }
/* 1054 */         if (nextMethod)
/*      */         {
/* 1056 */           fl = g.function(lower);
/* 1057 */           fm = g.function(mid);
/* 1058 */           fu = g.function(upper);
/* 1059 */           rr = fm / fu;
/* 1060 */           ss = fm / fl;
/* 1061 */           tt = fl / fu;
/* 1062 */           pp = ss * (tt * (rr - tt) * (upper - mid) - (1.0D - rr) * (mid - lower));
/* 1063 */           qq = (tt - 1.0D) * (rr - 1.0D) * (ss - 1.0D);
/* 1064 */           lastMid = mid;
/* 1065 */           diff = pp / qq;
/* 1066 */           mid += diff;
/*      */         }
/*      */         else
/*      */         {
/* 1070 */           fm = fmB;
/* 1071 */           mid = lastMidB;
/* 1072 */           if (fm * fl > 0.0D) {
/* 1073 */             lower = mid;
/* 1074 */             fl = fm;
/*      */           }
/*      */           else {
/* 1077 */             upper = mid;
/* 1078 */             fu = fm;
/*      */           }
/* 1080 */           lastMid = mid;
/* 1081 */           mid = (lower + upper) / 2.0D;
/* 1082 */           fm = g.function(mid);
/* 1083 */           diff = mid - lastMid;
/* 1084 */           fmB = fm;
/* 1085 */           lastMidB = mid;
/*      */         }
/*      */       }
/* 1088 */       iterN++;
/* 1089 */       if (iterN > staticIterMax) {
/* 1090 */         System.out.println("Class: RealRoot; method: brent; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/* 1091 */         System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + tol);
/* 1092 */         root = mid;
/* 1093 */         testConv = false;
/*      */       }
/*      */     }
/* 1096 */     return root;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double bisect(RealRootFunction g, double lower, double upper, double tol)
/*      */   {
/* 1105 */     if (upper == lower) throw new IllegalArgumentException("upper cannot equal lower");
/* 1106 */     if (upper < lower) {
/* 1107 */       double temp = upper;
/* 1108 */       upper = lower;
/* 1109 */       lower = temp;
/*      */     }
/*      */     
/* 1112 */     double root = NaN.0D;
/* 1113 */     boolean testConv = true;
/* 1114 */     int iterN = 0;
/* 1115 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/* 1118 */     double fu = g.function(upper);
/*      */     
/* 1120 */     double fl = g.function(lower);
/* 1121 */     if (Double.isNaN(fl)) {
/* 1122 */       if (staticReturnNaN) {
/* 1123 */         System.out.println("RealRoot: bisect: lower bound returned NaN as the function value - NaN returned as root");
/* 1124 */         return NaN.0D;
/*      */       }
/*      */       
/* 1127 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/* 1130 */     if (Double.isNaN(fu)) {
/* 1131 */       if (staticReturnNaN) {
/* 1132 */         System.out.println("RealRoot: bisect: upper bound returned NaN as the function value - NaN returned as root");
/* 1133 */         return NaN.0D;
/*      */       }
/*      */       
/* 1136 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/* 1140 */     boolean testBounds = true;
/* 1141 */     int numberOfBoundsExtension = 0;
/* 1142 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/* 1143 */     while (testBounds) {
/* 1144 */       if (fu * fl <= 0.0D) {
/* 1145 */         testBounds = false;
/*      */       }
/*      */       else {
/* 1148 */         if (noStaticBoundExtensions) {
/* 1149 */           String message = "RealRoot.bisect: root not bounded and no extension to bounds allowed\n";
/* 1150 */           message = message + "NaN returned";
/* 1151 */           System.out.println(message);
/* 1152 */           return NaN.0D;
/*      */         }
/*      */         
/*      */ 
/* 1156 */         numberOfBoundsExtension++;
/* 1157 */         if (numberOfBoundsExtension > maximumStaticBoundsExtension) {
/* 1158 */           String message = "RealRoot.bisect: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
/* 1159 */           message = message + "NaN returned";
/* 1160 */           System.out.println(message);
/* 1161 */           return NaN.0D;
/*      */         }
/* 1163 */         if (!noStaticLowerBoundExtensions) {
/* 1164 */           lower -= initialBoundsDifference;
/* 1165 */           fl = g.function(lower);
/*      */         }
/* 1167 */         if (!noStaticUpperBoundExtensions) {
/* 1168 */           upper += initialBoundsDifference;
/* 1169 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1176 */     if (fl == 0.0D) {
/* 1177 */       root = lower;
/* 1178 */       testConv = false;
/*      */     }
/* 1180 */     if (fu == 0.0D) {
/* 1181 */       root = upper;
/* 1182 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/* 1186 */     double mid = (lower + upper) / 2.0D;
/* 1187 */     double lastMid = 1.0E300D;
/* 1188 */     double fm = g.function(mid);
/* 1189 */     while (testConv) {
/* 1190 */       if ((fm == 0.0D) || (diff < tol)) {
/* 1191 */         testConv = false;
/* 1192 */         root = mid;
/*      */       }
/* 1194 */       if (fm * fl > 0.0D) {
/* 1195 */         lower = mid;
/* 1196 */         fl = fm;
/*      */       }
/*      */       else {
/* 1199 */         upper = mid;
/* 1200 */         fu = fm;
/*      */       }
/* 1202 */       lastMid = mid;
/* 1203 */       mid = (lower + upper) / 2.0D;
/* 1204 */       fm = g.function(mid);
/* 1205 */       diff = Math.abs(mid - lastMid);
/* 1206 */       iterN++;
/* 1207 */       if (iterN > staticIterMax) {
/* 1208 */         System.out.println("Class: RealRoot; method: bisect; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/* 1209 */         System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + tol);
/* 1210 */         root = mid;
/* 1211 */         testConv = false;
/*      */       }
/*      */     }
/* 1214 */     return root;
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
/*      */   public static double falsePosition(RealRootFunction g, double lower, double upper, double tol)
/*      */   {
/* 1227 */     if (upper == lower) throw new IllegalArgumentException("upper cannot equal lower");
/* 1228 */     if (upper < lower) {
/* 1229 */       double temp = upper;
/* 1230 */       upper = lower;
/* 1231 */       lower = temp;
/*      */     }
/*      */     
/* 1234 */     double root = NaN.0D;
/* 1235 */     boolean testConv = true;
/* 1236 */     int iterN = 0;
/* 1237 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/* 1240 */     double fu = g.function(upper);
/*      */     
/* 1242 */     double fl = g.function(lower);
/* 1243 */     if (Double.isNaN(fl)) {
/* 1244 */       if (staticReturnNaN) {
/* 1245 */         System.out.println("RealRoot: fals: ePositionlower bound returned NaN as the function value - NaN returned as root");
/* 1246 */         return NaN.0D;
/*      */       }
/*      */       
/* 1249 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/* 1252 */     if (Double.isNaN(fu)) {
/* 1253 */       if (staticReturnNaN) {
/* 1254 */         System.out.println("RealRoot: falsePosition: upper bound returned NaN as the function value - NaN returned as root");
/* 1255 */         return NaN.0D;
/*      */       }
/*      */       
/* 1258 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1263 */     boolean testBounds = true;
/* 1264 */     int numberOfBoundsExtension = 0;
/* 1265 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/* 1266 */     while (testBounds) {
/* 1267 */       if (fu * fl <= 0.0D) {
/* 1268 */         testBounds = false;
/*      */       }
/*      */       else {
/* 1271 */         if (noStaticBoundExtensions) {
/* 1272 */           String message = "RealRoot.falsePosition: root not bounded and no extension to bounds allowed\n";
/* 1273 */           message = message + "NaN returned";
/* 1274 */           System.out.println(message);
/* 1275 */           return NaN.0D;
/*      */         }
/*      */         
/* 1278 */         numberOfBoundsExtension++;
/* 1279 */         if (numberOfBoundsExtension > maximumStaticBoundsExtension) {
/* 1280 */           String message = "RealRoot.falsePosition: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
/* 1281 */           message = message + "NaN returned";
/* 1282 */           System.out.println(message);
/* 1283 */           return NaN.0D;
/*      */         }
/* 1285 */         if (!noStaticLowerBoundExtensions) {
/* 1286 */           lower -= initialBoundsDifference;
/* 1287 */           fl = g.function(lower);
/*      */         }
/* 1289 */         if (!noStaticUpperBoundExtensions) {
/* 1290 */           upper += initialBoundsDifference;
/* 1291 */           fu = g.function(upper);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1298 */     if (fl == 0.0D) {
/* 1299 */       root = lower;
/* 1300 */       testConv = false;
/*      */     }
/* 1302 */     if (fu == 0.0D) {
/* 1303 */       root = upper;
/* 1304 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/* 1308 */     double mid = lower + (upper - lower) * Math.abs(fl) / (Math.abs(fl) + Math.abs(fu));
/* 1309 */     double lastMid = 1.0E300D;
/* 1310 */     double fm = g.function(mid);
/* 1311 */     while (testConv) {
/* 1312 */       if ((fm == 0.0D) || (diff < tol)) {
/* 1313 */         testConv = false;
/* 1314 */         root = mid;
/*      */       }
/* 1316 */       if (fm * fl > 0.0D) {
/* 1317 */         lower = mid;
/* 1318 */         fl = fm;
/*      */       }
/*      */       else {
/* 1321 */         upper = mid;
/* 1322 */         fu = fm;
/*      */       }
/* 1324 */       lastMid = mid;
/* 1325 */       mid = lower + (upper - lower) * Math.abs(fl) / (Math.abs(fl) + Math.abs(fu));
/* 1326 */       fm = g.function(mid);
/* 1327 */       diff = Math.abs(mid - lastMid);
/* 1328 */       iterN++;
/* 1329 */       if (iterN > staticIterMax) {
/* 1330 */         System.out.println("Class: RealRoot; method: falsePostion; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/* 1331 */         System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + tol);
/* 1332 */         root = mid;
/* 1333 */         testConv = false;
/*      */       }
/*      */     }
/* 1336 */     return root;
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
/*      */   public static double bisectNewtonRaphson(RealRootDerivFunction g, double lower, double upper, double tol)
/*      */   {
/* 1349 */     if (upper == lower) { throw new IllegalArgumentException("upper cannot equal lower");
/*      */     }
/* 1351 */     double root = NaN.0D;
/* 1352 */     boolean testConv = true;
/* 1353 */     int iterN = 0;
/* 1354 */     double temp = 0.0D;
/*      */     
/* 1356 */     if (upper < lower) {
/* 1357 */       temp = upper;
/* 1358 */       upper = lower;
/* 1359 */       lower = temp;
/*      */     }
/*      */     
/*      */ 
/* 1363 */     double[] f = g.function(upper);
/* 1364 */     double fu = f[0];
/*      */     
/* 1366 */     f = g.function(lower);
/* 1367 */     double fl = f[0];
/* 1368 */     if (Double.isNaN(fl)) {
/* 1369 */       if (staticReturnNaN) {
/* 1370 */         System.out.println("RealRoot: bisectNewtonRaphson: lower bound returned NaN as the function value - NaN returned as root");
/* 1371 */         return NaN.0D;
/*      */       }
/*      */       
/* 1374 */       throw new ArithmeticException("lower bound returned NaN as the function value");
/*      */     }
/*      */     
/* 1377 */     if (Double.isNaN(fu)) {
/* 1378 */       if (staticReturnNaN) {
/* 1379 */         System.out.println("RealRoot: bisectNewtonRaphson: upper bound returned NaN as the function value - NaN returned as root");
/* 1380 */         return NaN.0D;
/*      */       }
/*      */       
/* 1383 */       throw new ArithmeticException("upper bound returned NaN as the function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1388 */     boolean testBounds = true;
/* 1389 */     int numberOfBoundsExtension = 0;
/* 1390 */     double initialBoundsDifference = (upper - lower) / 2.0D;
/* 1391 */     while (testBounds) {
/* 1392 */       if (fu * fl <= 0.0D) {
/* 1393 */         testBounds = false;
/*      */       }
/*      */       else {
/* 1396 */         if (noStaticBoundExtensions) {
/* 1397 */           String message = "RealRoot.bisectNewtonRaphson: root not bounded and no extension to bounds allowed\n";
/* 1398 */           message = message + "NaN returned";
/* 1399 */           System.out.println(message);
/* 1400 */           return NaN.0D;
/*      */         }
/*      */         
/* 1403 */         numberOfBoundsExtension++;
/* 1404 */         if (numberOfBoundsExtension > maximumStaticBoundsExtension) {
/* 1405 */           String message = "RealRoot.bisectNewtonRaphson: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
/* 1406 */           message = message + "NaN returned";
/* 1407 */           System.out.println(message);
/* 1408 */           return NaN.0D;
/*      */         }
/* 1410 */         if (!noStaticLowerBoundExtensions) {
/* 1411 */           lower -= initialBoundsDifference;
/* 1412 */           f = g.function(lower);
/* 1413 */           fl = f[0];
/*      */         }
/* 1415 */         if (!noStaticUpperBoundExtensions) {
/* 1416 */           upper += initialBoundsDifference;
/* 1417 */           f = g.function(upper);
/* 1418 */           fu = f[0];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1425 */     if (fl == 0.0D) {
/* 1426 */       root = lower;
/* 1427 */       testConv = false;
/*      */     }
/* 1429 */     if (fu == 0.0D) {
/* 1430 */       root = upper;
/* 1431 */       testConv = false;
/*      */     }
/*      */     
/*      */ 
/* 1435 */     double mid = (lower + upper) / 2.0D;
/* 1436 */     double lastMidB = mid;
/* 1437 */     f = g.function(mid);
/* 1438 */     double diff = f[0] / f[1];
/* 1439 */     double fm = f[0];
/* 1440 */     double fmB = fm;
/* 1441 */     double lastMid = mid;
/* 1442 */     mid -= diff;
/* 1443 */     boolean lastMethod = true;
/* 1444 */     boolean nextMethod = true;
/*      */     
/*      */ 
/* 1447 */     while (testConv)
/*      */     {
/* 1449 */       if ((fm == 0.0D) || (Math.abs(diff) < tol)) {
/* 1450 */         testConv = false;
/* 1451 */         if (fm == 0.0D) {
/* 1452 */           root = lastMid;
/*      */ 
/*      */         }
/* 1455 */         else if (Math.abs(diff) < tol) root = mid;
/*      */       }
/*      */       else
/*      */       {
/* 1459 */         lastMethod = nextMethod;
/*      */         
/* 1461 */         if (lastMethod) {
/* 1462 */           if ((mid < lower) || (mid > upper))
/*      */           {
/* 1464 */             nextMethod = false;
/*      */           }
/*      */           else {
/* 1467 */             fmB = fm;
/* 1468 */             lastMidB = mid;
/*      */           }
/*      */         }
/*      */         else {
/* 1472 */           nextMethod = true;
/*      */         }
/* 1474 */         if (nextMethod)
/*      */         {
/* 1476 */           f = g.function(mid);
/* 1477 */           fm = f[0];
/* 1478 */           diff = f[0] / f[1];
/* 1479 */           lastMid = mid;
/* 1480 */           mid -= diff;
/*      */         }
/*      */         else
/*      */         {
/* 1484 */           fm = fmB;
/* 1485 */           mid = lastMidB;
/* 1486 */           if (fm * fl > 0.0D) {
/* 1487 */             lower = mid;
/* 1488 */             fl = fm;
/*      */           }
/*      */           else {
/* 1491 */             upper = mid;
/* 1492 */             fu = fm;
/*      */           }
/* 1494 */           lastMid = mid;
/* 1495 */           mid = (lower + upper) / 2.0D;
/* 1496 */           f = g.function(mid);
/* 1497 */           fm = f[0];
/* 1498 */           diff = mid - lastMid;
/* 1499 */           fmB = fm;
/* 1500 */           lastMidB = mid;
/*      */         }
/*      */       }
/* 1503 */       iterN++;
/* 1504 */       if (iterN > staticIterMax) {
/* 1505 */         System.out.println("Class: RealRoot; method: bisectNewtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(mid, 4) + ", returned");
/* 1506 */         System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + tol);
/* 1507 */         root = mid;
/* 1508 */         testConv = false;
/*      */       }
/*      */     }
/* 1511 */     return root;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double newtonRaphson(RealRootDerivFunction g, double x, double tol)
/*      */   {
/* 1520 */     double root = NaN.0D;
/* 1521 */     boolean testConv = true;
/* 1522 */     int iterN = 0;
/* 1523 */     double diff = 1.0E300D;
/*      */     
/*      */ 
/* 1526 */     double[] f = g.function(x);
/* 1527 */     if (Double.isNaN(f[0])) {
/* 1528 */       if (staticReturnNaN) {
/* 1529 */         System.out.println("RealRoot: newtonRaphson: NaN returned as the function value - NaN returned as root");
/* 1530 */         return NaN.0D;
/*      */       }
/*      */       
/* 1533 */       throw new ArithmeticException("NaN returned as the function value");
/*      */     }
/*      */     
/* 1536 */     if (Double.isNaN(f[1])) {
/* 1537 */       if (staticReturnNaN) {
/* 1538 */         System.out.println("RealRoot: newtonRaphson: NaN returned as the derivative function value - NaN returned as root");
/* 1539 */         return NaN.0D;
/*      */       }
/*      */       
/* 1542 */       throw new ArithmeticException("NaN returned as the derivative function value");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1548 */     while (testConv) {
/* 1549 */       diff = f[0] / f[1];
/* 1550 */       if ((f[0] == 0.0D) || (Math.abs(diff) < tol)) {
/* 1551 */         root = x;
/* 1552 */         testConv = false;
/*      */       }
/*      */       else {
/* 1555 */         x -= diff;
/* 1556 */         f = g.function(x);
/* 1557 */         if (Double.isNaN(f[0])) throw new ArithmeticException("NaN returned as the function value");
/* 1558 */         if (Double.isNaN(f[1])) throw new ArithmeticException("NaN returned as the derivative function value");
/* 1559 */         if (Double.isNaN(f[0])) {
/* 1560 */           if (staticReturnNaN) {
/* 1561 */             System.out.println("RealRoot: NewtonRaphson: NaN as the function value - NaN returned as root");
/* 1562 */             return NaN.0D;
/*      */           }
/*      */           
/* 1565 */           throw new ArithmeticException("NaN as the function value");
/*      */         }
/*      */         
/* 1568 */         if (Double.isNaN(f[1])) {
/* 1569 */           if (staticReturnNaN) {
/* 1570 */             System.out.println("NaN as the function value - NaN returned as root");
/* 1571 */             return NaN.0D;
/*      */           }
/*      */           
/* 1574 */           throw new ArithmeticException("NaN as the function value");
/*      */         }
/*      */       }
/*      */       
/* 1578 */       iterN++;
/* 1579 */       if (iterN > staticIterMax) {
/* 1580 */         System.out.println("Class: RealRoot; method: newtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(x, 4) + ", returned");
/* 1581 */         System.out.println("Last mid-point difference = " + Fmath.truncate(diff, 4) + ", tolerance = " + tol);
/* 1582 */         root = x;
/* 1583 */         testConv = false;
/*      */       }
/*      */     }
/* 1586 */     return root;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> quadratic(double c, double b, double a)
/*      */   {
/* 1597 */     ArrayList<Object> roots = new ArrayList(2);
/*      */     
/* 1599 */     double bsquared = b * b;
/* 1600 */     double fourac = 4.0D * a * c;
/* 1601 */     if (bsquared < fourac) {
/* 1602 */       Complex[] croots = ComplexPoly.quadratic(c, b, a);
/* 1603 */       roots.add("complex");
/* 1604 */       roots.add(croots);
/*      */     }
/*      */     else {
/* 1607 */       double[] droots = new double[2];
/* 1608 */       double bsign = Fmath.sign(b);
/* 1609 */       double qsqrt = Math.sqrt(bsquared - fourac);
/* 1610 */       qsqrt = -0.5D * (b + bsign * qsqrt);
/* 1611 */       droots[0] = (qsqrt / a);
/* 1612 */       droots[1] = (c / qsqrt);
/* 1613 */       roots.add("real");
/* 1614 */       roots.add(droots);
/*      */     }
/* 1616 */     return roots;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> cubic(double a, double b, double c, double d)
/*      */   {
/* 1626 */     ArrayList<Object> roots = new ArrayList(2);
/*      */     
/* 1628 */     double aa = c / d;
/* 1629 */     double bb = b / d;
/* 1630 */     double cc = a / d;
/* 1631 */     double bigQ = (aa * aa - 3.0D * bb) / 9.0D;
/* 1632 */     double bigQcubed = bigQ * bigQ * bigQ;
/* 1633 */     double bigR = (2.0D * aa * aa * aa - 9.0D * aa * bb + 27.0D * cc) / 54.0D;
/* 1634 */     double bigRsquared = bigR * bigR;
/*      */     
/* 1636 */     if (bigRsquared >= bigQcubed) {
/* 1637 */       Complex[] croots = ComplexPoly.cubic(a, b, c, d);
/* 1638 */       roots.add("complex");
/* 1639 */       roots.add(croots);
/*      */     }
/*      */     else {
/* 1642 */       double[] droots = new double[3];
/* 1643 */       double theta = Math.acos(bigR / Math.sqrt(bigQcubed));
/* 1644 */       double aover3 = a / 3.0D;
/* 1645 */       double qterm = -2.0D * Math.sqrt(bigQ);
/*      */       
/* 1647 */       droots[0] = (qterm * Math.cos(theta / 3.0D) - aover3);
/* 1648 */       droots[1] = (qterm * Math.cos((theta + 6.283185307179586D) / 3.0D) - aover3);
/* 1649 */       droots[2] = (qterm * Math.cos((theta - 6.283185307179586D) / 3.0D) - aover3);
/* 1650 */       roots.add("real");
/* 1651 */       roots.add(droots);
/*      */     }
/* 1653 */     return roots;
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
/*      */   public static ArrayList<Object> polynomial(double[] coeff)
/*      */   {
/* 1666 */     boolean polish = true;
/* 1667 */     double estx = 0.0D;
/* 1668 */     return polynomial(coeff, polish, estx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> polynomial(double[] coeff, boolean polish)
/*      */   {
/* 1675 */     double estx = 0.0D;
/* 1676 */     return polynomial(coeff, polish, estx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> polynomial(double[] coeff, double estx)
/*      */   {
/* 1683 */     boolean polish = true;
/* 1684 */     return polynomial(coeff, polish, estx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> polynomial(double[] coeff, boolean polish, double estx)
/*      */   {
/* 1692 */     int nCoeff = coeff.length;
/* 1693 */     if (nCoeff < 2) throw new IllegalArgumentException("a minimum of two coefficients is required");
/* 1694 */     ArrayList<Object> roots = new ArrayList(nCoeff);
/* 1695 */     boolean realRoots = true;
/*      */     
/*      */ 
/* 1698 */     int nZeros = 0;
/* 1699 */     int ii = 0;
/* 1700 */     boolean testZero = true;
/* 1701 */     while (testZero) {
/* 1702 */       if (coeff[ii] == 0.0D) {
/* 1703 */         nZeros++;
/* 1704 */         ii++;
/*      */       }
/*      */       else {
/* 1707 */         testZero = false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1712 */     int nCoeffWz = nCoeff - nZeros;
/* 1713 */     double[] coeffWz = new double[nCoeffWz];
/* 1714 */     if (nZeros > 0) {
/* 1715 */       for (int i = 0; i < nCoeffWz; i++) { coeffWz[i] = coeff[(i + nZeros)];
/*      */       }
/*      */     } else {
/* 1718 */       for (int i = 0; i < nCoeffWz; i++) { coeffWz[i] = coeff[i];
/*      */       }
/*      */     }
/*      */     
/* 1722 */     ArrayList<Object> temp = new ArrayList(2);
/* 1723 */     double[] cdreal = null;
/* 1724 */     switch (nCoeffWz) {
/*      */     case 0: case 1: 
/*      */       break;
/* 1727 */     case 2:  temp.add("real");
/* 1728 */       double[] dtemp = { -coeffWz[0] / coeffWz[1] };
/* 1729 */       temp.add(dtemp);
/* 1730 */       break;
/* 1731 */     case 3:  temp = quadratic(coeffWz[0], coeffWz[1], coeffWz[2]);
/* 1732 */       if (((String)temp.get(0)).equals("complex")) realRoots = false;
/*      */       break;
/* 1734 */     case 4:  temp = cubic(coeffWz[0], coeffWz[1], coeffWz[2], coeffWz[3]);
/* 1735 */       if (((String)temp.get(0)).equals("complex")) realRoots = false;
/*      */       break;
/* 1737 */     default:  ComplexPoly cp = new ComplexPoly(coeffWz);
/* 1738 */       Complex[] croots = cp.roots(polish, new Complex(estx, 0.0D));
/* 1739 */       cdreal = new double[nCoeffWz - 1];
/* 1740 */       int counter = 0;
/* 1741 */       for (int i = 0; i < nCoeffWz - 1; i++) {
/* 1742 */         if (croots[i].getImag() / croots[i].getReal() < realTol) {
/* 1743 */           cdreal[i] = croots[i].getReal();
/* 1744 */           counter++;
/*      */         }
/*      */       }
/* 1747 */       if (counter == nCoeffWz - 1) {
/* 1748 */         temp.add("real");
/* 1749 */         temp.add(cdreal);
/*      */       }
/*      */       else {
/* 1752 */         temp.add("complex");
/* 1753 */         temp.add(croots);
/* 1754 */         realRoots = false;
/*      */       }
/*      */       break;
/*      */     }
/*      */     
/* 1759 */     if (nZeros == 0) {
/* 1760 */       roots = temp;
/*      */ 
/*      */     }
/* 1763 */     else if (realRoots) {
/* 1764 */       double[] dtemp1 = new double[nCoeff - 1];
/* 1765 */       double[] dtemp2 = (double[])temp.get(1);
/* 1766 */       for (int i = 0; i < nCoeffWz - 1; i++) dtemp1[i] = dtemp2[i];
/* 1767 */       for (int i = 0; i < nZeros; i++) dtemp1[(i + nCoeffWz - 1)] = 0.0D;
/* 1768 */       roots.add("real");
/* 1769 */       roots.add(dtemp1);
/*      */     }
/*      */     else {
/* 1772 */       Complex[] dtemp1 = Complex.oneDarray(nCoeff - 1);
/* 1773 */       Complex[] dtemp2 = (Complex[])temp.get(1);
/* 1774 */       for (int i = 0; i < nCoeffWz - 1; i++) dtemp1[i] = dtemp2[i];
/* 1775 */       for (int i = 0; i < nZeros; i++) dtemp1[(i + nCoeffWz - 1)] = new Complex(0.0D, 0.0D);
/* 1776 */       roots.add("complex");
/* 1777 */       roots.add(dtemp1);
/*      */     }
/*      */     
/*      */ 
/* 1781 */     return roots;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void resetRealTest(double ratio)
/*      */   {
/* 1788 */     realTol = ratio;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/roots/RealRoot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */