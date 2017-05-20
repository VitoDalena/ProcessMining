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
/*     */ public class Logarithmic
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double my_p;
/*     */   private double t;
/*     */   private double h;
/*  37 */   private double a_prev = -1.0D;
/*     */   
/*     */ 
/*  40 */   protected static Logarithmic shared = new Logarithmic(0.5D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */   public Logarithmic(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  45 */     setRandomGenerator(paramRandomElement);
/*  46 */     setState(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  52 */     return nextDouble(this.my_p);
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
/*     */ 
/*     */ 
/*     */ 
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
/*  94 */     if (paramDouble != this.a_prev) {
/*  95 */       this.a_prev = paramDouble;
/*  96 */       if (paramDouble < 0.97D) this.t = (-paramDouble / Math.log(1.0D - paramDouble)); else {
/*  97 */         this.h = Math.log(1.0D - paramDouble);
/*     */       }
/*     */     }
/* 100 */     double d1 = this.randomGenerator.raw();
/* 101 */     int i; if (paramDouble < 0.97D) {
/* 102 */       i = 1;
/* 103 */       double d3 = this.t;
/* 104 */       while (d1 > d3)
/*     */       {
/* 106 */         d1 -= d3;
/* 107 */         i++;
/* 108 */         d3 *= paramDouble * (i - 1.0D) / i;
/*     */       }
/* 110 */       return i;
/*     */     }
/*     */     
/* 113 */     if (d1 > paramDouble) return 1.0D;
/* 114 */     d1 = this.randomGenerator.raw();
/* 115 */     double d2 = d1;
/* 116 */     double d4 = 1.0D - Math.exp(d2 * this.h);
/* 117 */     if (d1 <= d4 * d4) {
/* 118 */       i = (int)(1.0D + Math.log(d1) / Math.log(d4));
/* 119 */       return i;
/*     */     }
/* 121 */     if (d1 > d4) return 1.0D;
/* 122 */     return 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble)
/*     */   {
/* 128 */     this.my_p = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble)
/*     */   {
/* 134 */     synchronized (shared) {
/* 135 */       double d = shared.nextDouble(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 142 */     return getClass().getName() + "(" + this.my_p + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 149 */     synchronized (shared) {
/* 150 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Logarithmic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */