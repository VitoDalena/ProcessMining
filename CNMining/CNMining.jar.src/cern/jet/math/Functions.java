/*     */ package cern.jet.math;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleDoubleProcedure;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import com.imsl.math.Sfun;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
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
/*     */ public class Functions
/*     */ {
/* 163 */   public static final Functions functions = new Functions();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 171 */   public static final DoubleFunction abs = new DoubleFunction() {
/* 172 */     public final double apply(double paramAnonymousDouble) { return Math.abs(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 178 */   public static final DoubleFunction acos = new DoubleFunction() {
/* 179 */     public final double apply(double paramAnonymousDouble) { return Math.acos(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 185 */   public static final DoubleFunction acosh = new DoubleFunction() {
/* 186 */     public final double apply(double paramAnonymousDouble) { return Sfun.acosh(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 192 */   public static final DoubleFunction asin = new DoubleFunction() {
/* 193 */     public final double apply(double paramAnonymousDouble) { return Math.asin(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 199 */   public static final DoubleFunction asinh = new DoubleFunction() {
/* 200 */     public final double apply(double paramAnonymousDouble) { return Sfun.asinh(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 206 */   public static final DoubleFunction atan = new DoubleFunction() {
/* 207 */     public final double apply(double paramAnonymousDouble) { return Math.atan(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 213 */   public static final DoubleFunction atanh = new DoubleFunction() {
/* 214 */     public final double apply(double paramAnonymousDouble) { return Sfun.atanh(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 220 */   public static final DoubleFunction ceil = new DoubleFunction() {
/* 221 */     public final double apply(double paramAnonymousDouble) { return Math.ceil(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 227 */   public static final DoubleFunction cos = new DoubleFunction() {
/* 228 */     public final double apply(double paramAnonymousDouble) { return Math.cos(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 234 */   public static final DoubleFunction cosh = new DoubleFunction() {
/* 235 */     public final double apply(double paramAnonymousDouble) { return Sfun.cosh(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 241 */   public static final DoubleFunction cot = new DoubleFunction() {
/* 242 */     public final double apply(double paramAnonymousDouble) { return Sfun.cot(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 248 */   public static final DoubleFunction erf = new DoubleFunction() {
/* 249 */     public final double apply(double paramAnonymousDouble) { return Sfun.erf(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 255 */   public static final DoubleFunction erfc = new DoubleFunction() {
/* 256 */     public final double apply(double paramAnonymousDouble) { return Sfun.erfc(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 262 */   public static final DoubleFunction exp = new DoubleFunction() {
/* 263 */     public final double apply(double paramAnonymousDouble) { return Math.exp(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 269 */   public static final DoubleFunction floor = new DoubleFunction() {
/* 270 */     public final double apply(double paramAnonymousDouble) { return Math.floor(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 276 */   public static final DoubleFunction gamma = new DoubleFunction() {
/* 277 */     public final double apply(double paramAnonymousDouble) { return Sfun.gamma(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 283 */   public static final DoubleFunction identity = new DoubleFunction() {
/* 284 */     public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 290 */   public static final DoubleFunction inv = new DoubleFunction() {
/* 291 */     public final double apply(double paramAnonymousDouble) { return 1.0D / paramAnonymousDouble; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 297 */   public static final DoubleFunction log = new DoubleFunction() {
/* 298 */     public final double apply(double paramAnonymousDouble) { return Math.log(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 304 */   public static final DoubleFunction log10 = new DoubleFunction() {
/* 305 */     public final double apply(double paramAnonymousDouble) { return Sfun.log10(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 311 */   public static final DoubleFunction log2 = new DoubleFunction() {
/*     */     public final double apply(double paramAnonymousDouble) {
/* 313 */       return Math.log(paramAnonymousDouble) * 1.4426950408889634D;
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/* 319 */   public static final DoubleFunction logGamma = new DoubleFunction() {
/* 320 */     public final double apply(double paramAnonymousDouble) { return Sfun.logGamma(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 326 */   public static final DoubleFunction neg = new DoubleFunction() {
/* 327 */     public final double apply(double paramAnonymousDouble) { return -paramAnonymousDouble; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 333 */   public static final DoubleFunction rint = new DoubleFunction() {
/* 334 */     public final double apply(double paramAnonymousDouble) { return Math.rint(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 340 */   public static final DoubleFunction sign = new DoubleFunction() {
/* 341 */     public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble > 0.0D ? 1.0D : paramAnonymousDouble < 0.0D ? -1.0D : 0.0D; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 347 */   public static final DoubleFunction sin = new DoubleFunction() {
/* 348 */     public final double apply(double paramAnonymousDouble) { return Math.sin(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 354 */   public static final DoubleFunction sinh = new DoubleFunction() {
/* 355 */     public final double apply(double paramAnonymousDouble) { return Sfun.sinh(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 361 */   public static final DoubleFunction sqrt = new DoubleFunction() {
/* 362 */     public final double apply(double paramAnonymousDouble) { return Math.sqrt(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 368 */   public static final DoubleFunction square = new DoubleFunction() {
/* 369 */     public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble * paramAnonymousDouble; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 375 */   public static final DoubleFunction tan = new DoubleFunction() {
/* 376 */     public final double apply(double paramAnonymousDouble) { return Math.tan(paramAnonymousDouble); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 382 */   public static final DoubleFunction tanh = new DoubleFunction() {
/* 383 */     public final double apply(double paramAnonymousDouble) { return Sfun.tanh(paramAnonymousDouble); }
/*     */   };
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
/* 413 */   public static final DoubleDoubleFunction atan2 = new DoubleDoubleFunction() {
/* 414 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.atan2(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 420 */   public static final DoubleDoubleFunction logBeta = new DoubleDoubleFunction() {
/* 421 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Sfun.logBeta(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 427 */   public static final DoubleDoubleFunction compare = new DoubleDoubleFunction() {
/* 428 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 > paramAnonymousDouble2 ? 1.0D : paramAnonymousDouble1 < paramAnonymousDouble2 ? -1.0D : 0.0D; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 434 */   public static final DoubleDoubleFunction div = new DoubleDoubleFunction() {
/* 435 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 / paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 441 */   public static final DoubleDoubleFunction equals = new DoubleDoubleFunction() {
/* 442 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 == paramAnonymousDouble2 ? 1.0D : 0.0D; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 448 */   public static final DoubleDoubleFunction greater = new DoubleDoubleFunction() {
/* 449 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 > paramAnonymousDouble2 ? 1.0D : 0.0D; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 455 */   public static final DoubleDoubleFunction IEEEremainder = new DoubleDoubleFunction() {
/* 456 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.IEEEremainder(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 462 */   public static final DoubleDoubleProcedure isEqual = new DoubleDoubleProcedure() {
/* 463 */     public final boolean apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 == paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 469 */   public static final DoubleDoubleProcedure isLess = new DoubleDoubleProcedure() {
/* 470 */     public final boolean apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 < paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 476 */   public static final DoubleDoubleProcedure isGreater = new DoubleDoubleProcedure() {
/* 477 */     public final boolean apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 > paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 483 */   public static final DoubleDoubleFunction less = new DoubleDoubleFunction() {
/* 484 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 < paramAnonymousDouble2 ? 1.0D : 0.0D; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 490 */   public static final DoubleDoubleFunction lg = new DoubleDoubleFunction() {
/* 491 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.log(paramAnonymousDouble1) / Math.log(paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 497 */   public static final DoubleDoubleFunction max = new DoubleDoubleFunction() {
/* 498 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.max(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 504 */   public static final DoubleDoubleFunction min = new DoubleDoubleFunction() {
/* 505 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.min(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 511 */   public static final DoubleDoubleFunction minus = plusMult(-1.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 521 */   public static final DoubleDoubleFunction mod = new DoubleDoubleFunction() {
/* 522 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 % paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 528 */   public static final DoubleDoubleFunction mult = new DoubleDoubleFunction() {
/* 529 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return paramAnonymousDouble1 * paramAnonymousDouble2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 535 */   public static final DoubleDoubleFunction plus = plusMult(1.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 545 */   public static final DoubleDoubleFunction plusAbs = new DoubleDoubleFunction() {
/* 546 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.abs(paramAnonymousDouble1) + Math.abs(paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 552 */   public static final DoubleDoubleFunction pow = new DoubleDoubleFunction() {
/* 553 */     public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.pow(paramAnonymousDouble1, paramAnonymousDouble2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleFunction between(double paramDouble1, double paramDouble2)
/*     */   {
/* 564 */     new DoubleFunction() {
/* 565 */       public final double apply(double paramAnonymousDouble) { return (this.val$from <= paramAnonymousDouble) && (paramAnonymousDouble <= this.val$to) ? 1.0D : 0.0D; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final double val$from;
/*     */   
/*     */ 
/*     */   public static DoubleFunction bindArg1(DoubleDoubleFunction paramDoubleDoubleFunction, double paramDouble)
/*     */   {
/* 576 */     new DoubleFunction() {
/* 577 */       public final double apply(double paramAnonymousDouble) { return this.val$function.apply(this.val$c, paramAnonymousDouble); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final DoubleDoubleFunction val$function;
/*     */   
/*     */   private final double val$c;
/*     */   
/*     */   public static DoubleFunction bindArg2(DoubleDoubleFunction paramDoubleDoubleFunction, double paramDouble)
/*     */   {
/* 588 */     new DoubleFunction() {
/* 589 */       public final double apply(double paramAnonymousDouble) { return this.val$function.apply(paramAnonymousDouble, this.val$c); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final DoubleDoubleFunction val$function;
/*     */   
/*     */   private final double val$c;
/*     */   
/*     */   private final double val$to;
/*     */   public static DoubleDoubleFunction chain(DoubleDoubleFunction paramDoubleDoubleFunction, DoubleFunction paramDoubleFunction1, DoubleFunction paramDoubleFunction2)
/*     */   {
/* 601 */     new DoubleDoubleFunction() {
/* 602 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return this.val$f.apply(this.val$g.apply(paramAnonymousDouble1), this.val$h.apply(paramAnonymousDouble2)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final DoubleDoubleFunction val$f;
/*     */   
/*     */ 
/*     */   public static DoubleDoubleFunction chain(DoubleFunction paramDoubleFunction, DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 613 */     new DoubleDoubleFunction() {
/* 614 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return this.val$g.apply(this.val$h.apply(paramAnonymousDouble1, paramAnonymousDouble2)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final DoubleFunction val$g;
/*     */   
/*     */   private final DoubleDoubleFunction val$h;
/*     */   
/*     */   public static DoubleFunction chain(DoubleFunction paramDoubleFunction1, DoubleFunction paramDoubleFunction2)
/*     */   {
/* 625 */     new DoubleFunction() {
/* 626 */       public final double apply(double paramAnonymousDouble) { return this.val$g.apply(this.val$h.apply(paramAnonymousDouble)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final DoubleFunction val$g;
/*     */   public static DoubleFunction compare(double paramDouble)
/*     */   {
/* 634 */     new DoubleFunction() {
/* 635 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble > this.val$b ? 1.0D : paramAnonymousDouble < this.val$b ? -1.0D : 0.0D; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   public static DoubleFunction constant(double paramDouble)
/*     */   {
/* 642 */     new DoubleFunction() {
/* 643 */       public final double apply(double paramAnonymousDouble) { return this.val$c; }
/*     */     };
/*     */   }
/*     */   
/*     */   private final double val$c;
/*     */   public static void demo1()
/*     */   {
/* 650 */     Functions localFunctions = functions;
/* 651 */     double d1 = 0.5D;
/* 652 */     double d2 = 0.2D;
/* 653 */     double d3 = Math.sin(d1) + Math.pow(Math.cos(d2), 2.0D);
/* 654 */     System.out.println(d3);
/* 655 */     DoubleDoubleFunction localDoubleDoubleFunction = chain(plus, sin, chain(square, cos));
/*     */     
/* 657 */     System.out.println(localDoubleDoubleFunction.apply(d1, d2));
/* 658 */     DoubleDoubleFunction local58 = new DoubleDoubleFunction() {
/* 659 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.sin(paramAnonymousDouble1) + Math.pow(Math.cos(paramAnonymousDouble2), 2.0D); }
/* 660 */     };
/* 661 */     System.out.println(local58.apply(d1, d2));
/* 662 */     DoubleFunction localDoubleFunction1 = plus(3.0D);
/* 663 */     DoubleFunction localDoubleFunction2 = plus(4.0D);
/* 664 */     System.out.println(localDoubleFunction1.apply(0.0D));
/* 665 */     System.out.println(localDoubleFunction2.apply(0.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public static void demo2(int paramInt)
/*     */   {
/* 671 */     Functions localFunctions = functions;
/* 672 */     System.out.println("\n\n");
/* 673 */     double d1 = 0.0D;
/* 674 */     double d2 = 0.0D;
/* 675 */     double d3 = Math.abs(Math.sin(d1) + Math.pow(Math.cos(d2), 2.0D));
/*     */     
/*     */ 
/* 678 */     System.out.println(d3);
/*     */     
/*     */ 
/* 681 */     DoubleDoubleFunction localDoubleDoubleFunction = chain(abs, chain(plus, sin, chain(square, cos)));
/*     */     
/*     */ 
/*     */ 
/* 685 */     System.out.println(localDoubleDoubleFunction.apply(d1, d2));
/* 686 */     DoubleDoubleFunction local59 = new DoubleDoubleFunction() {
/* 687 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return Math.abs(Math.sin(paramAnonymousDouble1) + Math.pow(Math.cos(paramAnonymousDouble2), 2.0D));
/*     */       }
/* 689 */     };
/* 690 */     System.out.println(local59.apply(d1, d2));
/*     */     
/*     */ 
/* 693 */     Timer localTimer1 = new Timer().start();
/* 694 */     d1 = 0.0D;d2 = 0.0D;
/* 695 */     double d4 = 0.0D;
/* 696 */     int i = paramInt;
/* 697 */     do { d4 += d1;
/* 698 */       d1 += 1.0D;
/* 699 */       d2 += 1.0D;i--;
/* 696 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 701 */     localTimer1.stop().display();
/* 702 */     System.out.println("empty sum=" + d4);
/*     */     
/* 704 */     Timer localTimer2 = new Timer().start();
/* 705 */     d1 = 0.0D;d2 = 0.0D;
/* 706 */     d4 = 0.0D;
/* 707 */     int j = paramInt;
/* 708 */     do { d4 += Math.abs(Math.sin(d1) + Math.pow(Math.cos(d2), 2.0D));
/*     */       
/* 710 */       d1 += 1.0D;d2 += 1.0D;j--;
/* 707 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 712 */     localTimer2.stop().display();
/* 713 */     System.out.println("evals / sec = " + paramInt / localTimer2.minus(localTimer1).seconds());
/* 714 */     System.out.println("sum=" + d4);
/*     */     
/* 716 */     localTimer2.reset().start();
/* 717 */     d1 = 0.0D;d2 = 0.0D;
/* 718 */     d4 = 0.0D;
/* 719 */     int k = paramInt;
/* 720 */     do { d4 += localDoubleDoubleFunction.apply(d1, d2);
/* 721 */       d1 += 1.0D;d2 += 1.0D;k--;
/* 719 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/* 723 */     localTimer2.stop().display();
/* 724 */     System.out.println("evals / sec = " + paramInt / localTimer2.minus(localTimer1).seconds());
/* 725 */     System.out.println("sum=" + d4);
/*     */     
/* 727 */     localTimer2.reset().start();
/* 728 */     d1 = 0.0D;d2 = 0.0D;
/* 729 */     d4 = 0.0D;
/* 730 */     int m = paramInt;
/* 731 */     do { d4 += local59.apply(d1, d2);
/* 732 */       d1 += 1.0D;d2 += 1.0D;m--;
/* 730 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/* 734 */     localTimer2.stop().display();
/* 735 */     System.out.println("evals / sec = " + paramInt / localTimer2.minus(localTimer1).seconds());
/* 736 */     System.out.println("sum=" + d4);
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   private final DoubleFunction val$h;
/*     */   public static DoubleFunction div(double paramDouble)
/*     */   {
/* 744 */     return mult(1.0D / paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction equals(double paramDouble)
/*     */   {
/* 751 */     new DoubleFunction() {
/* 752 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble == this.val$b ? 1.0D : 0.0D; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   public static DoubleFunction greater(double paramDouble)
/*     */   {
/* 760 */     new DoubleFunction() {
/* 761 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble > this.val$b ? 1.0D : 0.0D; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   public static DoubleFunction IEEEremainder(double paramDouble)
/*     */   {
/* 769 */     new DoubleFunction() {
/* 770 */       public final double apply(double paramAnonymousDouble) { return Math.IEEEremainder(paramAnonymousDouble, this.val$b); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   public static DoubleProcedure isBetween(double paramDouble1, double paramDouble2)
/*     */   {
/* 778 */     new DoubleProcedure() {
/* 779 */       public final boolean apply(double paramAnonymousDouble) { return (this.val$from <= paramAnonymousDouble) && (paramAnonymousDouble <= this.val$to); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$from;
/*     */   public static DoubleProcedure isEqual(double paramDouble)
/*     */   {
/* 787 */     new DoubleProcedure() {
/* 788 */       public final boolean apply(double paramAnonymousDouble) { return paramAnonymousDouble == this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   public static DoubleProcedure isGreater(double paramDouble)
/*     */   {
/* 796 */     new DoubleProcedure() {
/* 797 */       public final boolean apply(double paramAnonymousDouble) { return paramAnonymousDouble > this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$b;
/*     */   public static DoubleProcedure isLess(double paramDouble)
/*     */   {
/* 805 */     new DoubleProcedure() { private final double val$b;
/* 806 */       public final boolean apply(double paramAnonymousDouble) { return paramAnonymousDouble < this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction less(double paramDouble)
/*     */   {
/* 814 */     new DoubleFunction() { private final double val$b;
/* 815 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble < this.val$b ? 1.0D : 0.0D; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction lg(double paramDouble)
/*     */   {
/* 823 */     new DoubleFunction() { private final double logInv;
/*     */       private final double val$b;
/* 825 */       public final double apply(double paramAnonymousDouble) { return Math.log(paramAnonymousDouble) * this.logInv; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void main(String[] paramArrayOfString)
/*     */   {
/* 832 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 833 */     demo2(i);
/*     */   }
/*     */   
/*     */ 
/*     */   private final double val$to;
/*     */   
/*     */   public static DoubleFunction max(double paramDouble)
/*     */   {
/* 841 */     new DoubleFunction() { private final double val$b;
/* 842 */       public final double apply(double paramAnonymousDouble) { return Math.max(paramAnonymousDouble, this.val$b); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction min(double paramDouble)
/*     */   {
/* 850 */     new DoubleFunction() { private final double val$b;
/* 851 */       public final double apply(double paramAnonymousDouble) { return Math.min(paramAnonymousDouble, this.val$b); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction minus(double paramDouble)
/*     */   {
/* 859 */     return plus(-paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleDoubleFunction minusMult(double paramDouble)
/*     */   {
/* 866 */     return plusMult(-paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction mod(double paramDouble)
/*     */   {
/* 873 */     new DoubleFunction() { private final double val$b;
/* 874 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble % this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleFunction mult(double paramDouble)
/*     */   {
/* 882 */     return new Mult(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final DoubleFunction val$g;
/*     */   
/*     */ 
/*     */   private final DoubleFunction val$h;
/*     */   
/*     */   public static DoubleFunction plus(double paramDouble)
/*     */   {
/* 894 */     new DoubleFunction() { private final double val$b;
/* 895 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble + this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleDoubleFunction plusMult(double paramDouble)
/*     */   {
/* 903 */     return new PlusMult(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleFunction pow(double paramDouble)
/*     */   {
/* 915 */     new DoubleFunction() { private final double val$b;
/* 916 */       public final double apply(double paramAnonymousDouble) { return Math.pow(paramAnonymousDouble, this.val$b); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleFunction random()
/*     */   {
/* 928 */     return new MersenneTwister(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleFunction round(double paramDouble)
/*     */   {
/* 939 */     new DoubleFunction() { private final double val$precision;
/* 940 */       public final double apply(double paramAnonymousDouble) { return Math.rint(paramAnonymousDouble / this.val$precision) * this.val$precision; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleDoubleFunction swapArgs(DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 950 */     new DoubleDoubleFunction() { private final DoubleDoubleFunction val$function;
/* 951 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) { return this.val$function.apply(paramAnonymousDouble2, paramAnonymousDouble1); }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/Functions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */