/*     */ package cern.jet.random;
/*     */ 
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
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
/*     */ public class PoissonSlow
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*     */   protected double mean;
/*     */   protected double cached_sq;
/*     */   protected double cached_alxm;
/*     */   protected double cached_g;
/*     */   protected static final double MEAN_MAX = 2.147483647E9D;
/*     */   protected static final double SWITCH_MEAN = 12.0D;
/*  43 */   protected static final double[] cof = { 76.18009172947146D, -86.50532032941678D, 24.01409824083091D, -1.231739572450155D, 0.001208650973866179D, -5.395239384953E-6D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  49 */   protected static PoissonSlow shared = new PoissonSlow(0.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */   public PoissonSlow(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  55 */     setRandomGenerator(paramRandomElement);
/*  56 */     setMean(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logGamma(double paramDouble)
/*     */   {
/*  64 */     double d1 = paramDouble - 1.0D;
/*  65 */     double d2 = d1 + 5.5D;
/*  66 */     d2 -= (d1 + 0.5D) * Math.log(d2);
/*  67 */     double d3 = 1.000000000190015D;
/*     */     
/*  69 */     double[] arrayOfDouble = cof;
/*  70 */     for (int i = 0; i <= 5; i++) {
/*  71 */       d1 += 1.0D;
/*  72 */       d3 += arrayOfDouble[i] / d1;
/*     */     }
/*  74 */     return -d2 + Math.log(2.5066282746310007D * d3);
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/*  80 */     return nextInt(this.mean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int nextInt(double paramDouble)
/*     */   {
/*  89 */     double d1 = paramDouble;
/*  90 */     double d2 = this.cached_g;
/*     */     
/*  92 */     if (d1 == -1.0D) return 0;
/*  93 */     if (d1 < 12.0D) {
/*  94 */       int i = -1;
/*  95 */       double d4 = 1.0D;
/*     */       do {
/*  97 */         i++;
/*  98 */         d4 *= this.randomGenerator.raw();
/*  99 */       } while (d4 >= d2);
/*     */       
/* 101 */       return i;
/*     */     }
/* 103 */     if (d1 < 2.147483647E9D)
/*     */     {
/*     */ 
/* 106 */       double d6 = this.cached_sq;
/* 107 */       double d7 = this.cached_alxm;
/*     */       
/* 109 */       RandomElement localRandomElement = this.randomGenerator;
/*     */       double d5;
/*     */       double d3;
/*     */       do { double d8;
/* 113 */         do { d8 = Math.tan(3.141592653589793D * localRandomElement.raw());
/* 114 */           d5 = d6 * d8 + d1;
/* 115 */         } while (d5 < 0.0D);
/* 116 */         d5 = (int)d5;
/* 117 */         d3 = 0.9D * (1.0D + d8 * d8) * Math.exp(d5 * d7 - logGamma(d5 + 1.0D) - d2);
/* 118 */       } while (localRandomElement.raw() > d3);
/* 119 */       return (int)d5;
/*     */     }
/*     */     
/* 122 */     return (int)d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int nextIntSlow()
/*     */   {
/* 129 */     double d1 = Math.exp(-this.mean);
/* 130 */     int i = 0;
/*     */     
/* 132 */     for (double d2 = 1.0D; (d2 >= d1) && (d2 > 0.0D); i++) {
/* 133 */       d2 *= this.randomGenerator.raw();
/*     */     }
/* 135 */     if ((d2 <= 0.0D) && (d1 > 0.0D)) return (int)Math.round(this.mean);
/* 136 */     return i - 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMean(double paramDouble)
/*     */   {
/* 142 */     if (paramDouble != this.mean) {
/* 143 */       this.mean = paramDouble;
/* 144 */       if (paramDouble == -1.0D) return;
/* 145 */       if (paramDouble < 12.0D) {
/* 146 */         this.cached_g = Math.exp(-paramDouble);
/*     */       }
/*     */       else {
/* 149 */         this.cached_sq = Math.sqrt(2.0D * paramDouble);
/* 150 */         this.cached_alxm = Math.log(paramDouble);
/* 151 */         this.cached_g = (paramDouble * this.cached_alxm - logGamma(paramDouble + 1.0D));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static int staticNextInt(double paramDouble)
/*     */   {
/* 159 */     synchronized (shared) {
/* 160 */       shared.setMean(paramDouble);
/* 161 */       int i = shared.nextInt();return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 168 */     return getClass().getName() + "(" + this.mean + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 175 */     synchronized (shared) {
/* 176 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/PoissonSlow.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */