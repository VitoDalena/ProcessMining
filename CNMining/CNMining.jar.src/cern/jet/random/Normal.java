/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.jet.stat.Probability;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Normal
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double mean;
/*     */   protected double variance;
/*     */   protected double standardDeviation;
/*     */   protected double cache;
/*     */   protected boolean cacheFilled;
/*     */   protected double SQRT_INV;
/*  52 */   protected static Normal shared = new Normal(0.0D, 1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */   public Normal(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*  58 */     setRandomGenerator(paramRandomElement);
/*  59 */     setState(paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/*  65 */     return Probability.normal(this.mean, this.variance, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  71 */     return nextDouble(this.mean, this.standardDeviation);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double nextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/*  78 */     if (this.cacheFilled) {
/*  79 */       this.cacheFilled = false;
/*  80 */       return this.cache;
/*     */     }
/*     */     double d1;
/*     */     double d2;
/*     */     double d3;
/*  85 */     do { d1 = 2.0D * this.randomGenerator.raw() - 1.0D;
/*  86 */       d2 = 2.0D * this.randomGenerator.raw() - 1.0D;
/*  87 */       d3 = d1 * d1 + d2 * d2;
/*  88 */     } while (d3 >= 1.0D);
/*     */     
/*  90 */     double d4 = Math.sqrt(-2.0D * Math.log(d3) / d3);
/*  91 */     this.cache = (paramDouble1 + paramDouble2 * d1 * d4);
/*  92 */     this.cacheFilled = true;
/*  93 */     return paramDouble1 + paramDouble2 * d2 * d4;
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/*  99 */     double d = paramDouble - this.mean;
/* 100 */     return this.SQRT_INV * Math.exp(-(d * d) / (2.0D * this.variance));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 106 */     super.setRandomGenerator(paramRandomElement);
/* 107 */     this.cacheFilled = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 113 */     if ((paramDouble1 != this.mean) || (paramDouble2 != this.standardDeviation)) {
/* 114 */       this.mean = paramDouble1;
/* 115 */       this.standardDeviation = paramDouble2;
/* 116 */       this.variance = (paramDouble2 * paramDouble2);
/* 117 */       this.cacheFilled = false;
/*     */       
/* 119 */       this.SQRT_INV = (1.0D / Math.sqrt(6.283185307179586D * this.variance));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 126 */     synchronized (shared) {
/* 127 */       double d = shared.nextDouble(paramDouble1, paramDouble2);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 134 */     return getClass().getName() + "(" + this.mean + "," + this.standardDeviation + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 141 */     synchronized (shared) {
/* 142 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Normal.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */