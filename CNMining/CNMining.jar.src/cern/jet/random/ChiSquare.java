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
/*     */ public class ChiSquare
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double freedom;
/*  42 */   private double freedom_in = -1.0D;
/*     */   private double b;
/*     */   private double vm;
/*  45 */   private double vp; private double vd; protected static ChiSquare shared = new ChiSquare(1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChiSquare(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  53 */     setRandomGenerator(paramRandomElement);
/*  54 */     setState(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/*  60 */     return Probability.chiSquare(this.freedom, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  66 */     return nextDouble(this.freedom);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double nextDouble(double paramDouble)
/*     */   {
/*     */     double d1;
/*     */     
/*     */ 
/*     */ 
/*     */     double d2;
/*     */     
/*     */ 
/*     */ 
/*     */     double d3;
/*     */     
/*     */ 
/*     */ 
/*     */     double d4;
/*     */     
/*     */ 
/*     */ 
/*     */     double d5;
/*     */     
/*     */ 
/*     */ 
/*  95 */     if (paramDouble == 1.0D) {
/*     */       do {
/*  97 */         do { d1 = this.randomGenerator.raw();
/*  98 */           d2 = this.randomGenerator.raw() * 0.857763884960707D;
/*  99 */           d3 = d2 / d1;
/* 100 */         } while (d3 < 0.0D);
/* 101 */         d4 = d3 * d3;
/* 102 */         d5 = 2.5D - d4;
/* 103 */         if (d3 < 0.0D) d5 += d4 * d3 / (3.0D * d3);
/* 104 */         if (d1 < d5 * 0.3894003915D) return d3 * d3;
/* 105 */       } while ((d4 > 1.036961043D / d1 + 1.4D) || 
/* 106 */         (2.0D * Math.log(d1) >= -d4 * 0.5D)); return d3 * d3;
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (paramDouble != this.freedom_in) {
/* 111 */       this.b = Math.sqrt(paramDouble - 1.0D);
/* 112 */       this.vm = (-0.6065306597D * (1.0D - 0.25D / (this.b * this.b + 1.0D)));
/* 113 */       this.vm = (-this.b > this.vm ? -this.b : this.vm);
/* 114 */       this.vp = (0.6065306597D * (0.7071067812D + this.b) / (0.5D + this.b));
/* 115 */       this.vd = (this.vp - this.vm);
/* 116 */       this.freedom_in = paramDouble;
/*     */     }
/*     */     do {
/* 119 */       do { d1 = this.randomGenerator.raw();
/* 120 */         d2 = this.randomGenerator.raw() * this.vd + this.vm;
/* 121 */         d3 = d2 / d1;
/* 122 */       } while (d3 < -this.b);
/* 123 */       d4 = d3 * d3;
/* 124 */       d5 = 2.5D - d4;
/* 125 */       if (d3 < 0.0D) d5 += d4 * d3 / (3.0D * (d3 + this.b));
/* 126 */       if (d1 < d5 * 0.3894003915D) return (d3 + this.b) * (d3 + this.b);
/* 127 */     } while ((d4 > 1.036961043D / d1 + 1.4D) || 
/* 128 */       (2.0D * Math.log(d1) >= Math.log(1.0D + d3 / this.b) * this.b * this.b - d4 * 0.5D - d3 * this.b)); return (d3 + this.b) * (d3 + this.b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/* 136 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/* 137 */     double d = Fun.logGamma(this.freedom / 2.0D);
/* 138 */     return Math.exp((this.freedom / 2.0D - 1.0D) * Math.log(paramDouble / 2.0D) - paramDouble / 2.0D - d) / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setState(double paramDouble)
/*     */   {
/* 146 */     if (paramDouble < 1.0D) throw new IllegalArgumentException();
/* 147 */     this.freedom = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble)
/*     */   {
/* 155 */     synchronized (shared) {
/* 156 */       double d = shared.nextDouble(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 163 */     return getClass().getName() + "(" + this.freedom + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 170 */     synchronized (shared) {
/* 171 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/ChiSquare.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */