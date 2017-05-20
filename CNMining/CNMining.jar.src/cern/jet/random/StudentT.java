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
/*     */ public class StudentT
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double freedom;
/*     */   protected double TERM;
/*  42 */   protected static StudentT shared = new StudentT(1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StudentT(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  50 */     setRandomGenerator(paramRandomElement);
/*  51 */     setState(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/*  57 */     return Probability.studentT(this.freedom, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  63 */     return nextDouble(this.freedom);
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
/*     */   public double nextDouble(double paramDouble)
/*     */   {
/*  81 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/*     */     double d1;
/*     */     double d2;
/*     */     double d3;
/*  85 */     do { d1 = 2.0D * this.randomGenerator.raw() - 1.0D;
/*  86 */       d2 = 2.0D * this.randomGenerator.raw() - 1.0D;
/*     */     }
/*  88 */     while ((d3 = d1 * d1 + d2 * d2) > 1.0D);
/*     */     
/*  90 */     return d1 * Math.sqrt(paramDouble * (Math.exp(-2.0D / paramDouble * Math.log(d3)) - 1.0D) / d3);
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/*  96 */     return this.TERM * Math.pow(1.0D + paramDouble * paramDouble / this.freedom, -(this.freedom + 1.0D) * 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setState(double paramDouble)
/*     */   {
/* 104 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/* 105 */     this.freedom = paramDouble;
/*     */     
/* 107 */     double d = Fun.logGamma((paramDouble + 1.0D) / 2.0D) - Fun.logGamma(paramDouble / 2.0D);
/* 108 */     this.TERM = (Math.exp(d) / Math.sqrt(3.141592653589793D * paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble)
/*     */   {
/* 116 */     synchronized (shared) {
/* 117 */       double d = shared.nextDouble(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 124 */     return getClass().getName() + "(" + this.freedom + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 131 */     synchronized (shared) {
/* 132 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/StudentT.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */