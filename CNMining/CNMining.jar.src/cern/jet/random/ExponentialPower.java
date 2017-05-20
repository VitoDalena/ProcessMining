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
/*     */ public class ExponentialPower
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double tau;
/*     */   private double s;
/*     */   private double sm1;
/*  36 */   private double tau_set = -1.0D;
/*     */   
/*     */ 
/*  39 */   protected static ExponentialPower shared = new ExponentialPower(1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExponentialPower(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  46 */     setRandomGenerator(paramRandomElement);
/*  47 */     setState(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  53 */     return nextDouble(this.tau);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double nextDouble(double paramDouble)
/*     */   {
/*  62 */     if (paramDouble != this.tau_set) {
/*  63 */       this.s = (1.0D / paramDouble);
/*  64 */       this.sm1 = (1.0D - this.s);
/*     */       
/*  66 */       this.tau_set = paramDouble;
/*     */     }
/*     */     double d1;
/*     */     double d3;
/*     */     double d4;
/*  71 */     do { d1 = this.randomGenerator.raw();
/*  72 */       d1 = 2.0D * d1 - 1.0D;
/*  73 */       double d2 = Math.abs(d1);
/*  74 */       d3 = this.randomGenerator.raw();
/*     */       
/*  76 */       if (d2 <= this.sm1) {
/*  77 */         d4 = d2;
/*     */       }
/*     */       else {
/*  80 */         double d5 = paramDouble * (1.0D - d2);
/*  81 */         d4 = this.sm1 - this.s * Math.log(d5);
/*  82 */         d3 *= d5;
/*     */ 
/*     */       }
/*     */       
/*     */     }
/*  87 */     while (Math.log(d3) > -Math.exp(Math.log(d4) * paramDouble));
/*     */     
/*     */ 
/*  90 */     if (d1 < 0.0D) {
/*  91 */       return d4;
/*     */     }
/*  93 */     return -d4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setState(double paramDouble)
/*     */   {
/* 100 */     if (paramDouble < 1.0D) throw new IllegalArgumentException();
/* 101 */     this.tau = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble)
/*     */   {
/* 108 */     synchronized (shared) {
/* 109 */       double d = shared.nextDouble(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 116 */     return getClass().getName() + "(" + this.tau + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 123 */     synchronized (shared) {
/* 124 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/ExponentialPower.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */