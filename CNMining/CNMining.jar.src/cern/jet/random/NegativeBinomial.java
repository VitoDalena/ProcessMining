/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.jet.math.Arithmetic;
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
/*     */ public class NegativeBinomial
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*     */   protected int n;
/*     */   protected double p;
/*     */   protected Gamma gamma;
/*     */   protected Poisson poisson;
/*  38 */   protected static NegativeBinomial shared = new NegativeBinomial(1, 0.5D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NegativeBinomial(int paramInt, double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  47 */     setRandomGenerator(paramRandomElement);
/*  48 */     setNandP(paramInt, paramDouble);
/*  49 */     this.gamma = new Gamma(paramInt, 1.0D, paramRandomElement);
/*  50 */     this.poisson = new Poisson(0.0D, paramRandomElement);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(int paramInt)
/*     */   {
/*  56 */     return Probability.negativeBinomial(paramInt, this.n, this.p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  65 */     NegativeBinomial localNegativeBinomial = (NegativeBinomial)super.clone();
/*  66 */     if (this.poisson != null) localNegativeBinomial.poisson = ((Poisson)this.poisson.clone());
/*  67 */     localNegativeBinomial.poisson.setRandomGenerator(localNegativeBinomial.getRandomGenerator());
/*  68 */     if (this.gamma != null) localNegativeBinomial.gamma = ((Gamma)this.gamma.clone());
/*  69 */     localNegativeBinomial.gamma.setRandomGenerator(localNegativeBinomial.getRandomGenerator());
/*  70 */     return localNegativeBinomial;
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/*  76 */     return nextInt(this.n, this.p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt(int paramInt, double paramDouble)
/*     */   {
/* 106 */     double d1 = paramDouble / (1.0D - paramDouble);
/* 107 */     double d2 = paramDouble;
/* 108 */     double d3 = d1 * this.gamma.nextDouble(paramInt, 1.0D);
/* 109 */     return this.poisson.nextInt(d3);
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 115 */     if (paramInt > this.n) throw new IllegalArgumentException();
/* 116 */     return Arithmetic.binomial(this.n, paramInt) * Math.pow(this.p, paramInt) * Math.pow(1.0D - this.p, this.n - paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNandP(int paramInt, double paramDouble)
/*     */   {
/* 124 */     this.n = paramInt;
/* 125 */     this.p = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int staticNextInt(int paramInt, double paramDouble)
/*     */   {
/* 133 */     synchronized (shared) {
/* 134 */       int i = shared.nextInt(paramInt, paramDouble);return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 141 */     return getClass().getName() + "(" + this.n + "," + this.p + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 148 */     synchronized (shared) {
/* 149 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/NegativeBinomial.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */