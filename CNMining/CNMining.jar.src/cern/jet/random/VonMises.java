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
/*     */ public class VonMises
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double my_k;
/*  37 */   private double k_set = -1.0D;
/*     */   private double tau;
/*     */   private double rho;
/*     */   private double r;
/*  41 */   protected static VonMises shared = new VonMises(1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public VonMises(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  48 */     setRandomGenerator(paramRandomElement);
/*  49 */     setState(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  55 */     return nextDouble(this.my_k);
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
/*     */   public double nextDouble(double paramDouble)
/*     */   {
/*  82 */     if (paramDouble <= 0.0D) { throw new IllegalArgumentException();
/*     */     }
/*  84 */     if (this.k_set != paramDouble) {
/*  85 */       this.tau = (1.0D + Math.sqrt(1.0D + 4.0D * paramDouble * paramDouble));
/*  86 */       this.rho = ((this.tau - Math.sqrt(2.0D * this.tau)) / (2.0D * paramDouble));
/*  87 */       this.r = ((1.0D + this.rho * this.rho) / (2.0D * this.rho));
/*  88 */       this.k_set = paramDouble;
/*     */     }
/*     */     double d2;
/*     */     double d3;
/*     */     double d4;
/*  93 */     do { double d1 = this.randomGenerator.raw();
/*  94 */       d2 = this.randomGenerator.raw();
/*  95 */       double d5 = Math.cos(3.141592653589793D * d1);
/*  96 */       d3 = (1.0D + this.r * d5) / (this.r + d5);
/*  97 */       d4 = paramDouble * (this.r - d3);
/*  98 */     } while ((d4 * (2.0D - d4) < d2) && (Math.log(d4 / d2) + 1.0D < d4));
/*     */     
/* 100 */     return this.randomGenerator.raw() > 0.5D ? Math.acos(d3) : -Math.acos(d3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setState(double paramDouble)
/*     */   {
/* 108 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/* 109 */     this.my_k = paramDouble;
/*     */   }
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
/* 124 */     return getClass().getName() + "(" + this.my_k + ")";
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/VonMises.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */