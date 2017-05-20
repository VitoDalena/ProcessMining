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
/*     */ public class Hyperbolic
/*     */   extends AbstractContinousDistribution
/*     */ {
/*  37 */   protected double a_setup = 0.0D; protected double b_setup = -1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */   protected static Hyperbolic shared = new Hyperbolic(10.0D, 10.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   protected double alpha;
/*     */   protected double beta;
/*     */   protected double x;
/*     */   
/*  48 */   public Hyperbolic(double paramDouble1, double paramDouble2, RandomElement paramRandomElement) { setRandomGenerator(paramRandomElement);
/*  49 */     setState(paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  55 */     return nextDouble(this.alpha, this.beta);
/*     */   }
/*     */   
/*     */ 
/*     */   protected double u;
/*     */   
/*     */   protected double v;
/*     */   
/*     */   protected double e;
/*     */   
/*     */   protected double hr;
/*     */   
/*     */   protected double hl;
/*     */   
/*     */   protected double s;
/*     */   protected double pm;
/*     */   protected double pr;
/*     */   protected double samb;
/*     */   protected double pmr;
/*     */   protected double mpa_1;
/*     */   protected double mmb_1;
/*     */   public double nextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/*  78 */     double d1 = paramDouble1;
/*  79 */     double d2 = paramDouble2;
/*     */     
/*  81 */     if ((this.a_setup != d1) || (this.b_setup != d2))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  86 */       double d6 = d1 * d1 - d2 * d2;
/*  87 */       this.samb = Math.sqrt(d6);
/*  88 */       double d5 = d2 / this.samb;
/*  89 */       double d11 = d1 * Math.sqrt(2.0D * this.samb + 1.0D);
/*  90 */       double d12 = d2 * (this.samb + 1.0D);
/*  91 */       double d3 = (d12 + d11) / d6;
/*  92 */       double d4 = (d12 - d11) / d6;
/*  93 */       double d7 = d3 - d5;
/*  94 */       double d8 = -d4 + d5;
/*  95 */       this.hr = (-1.0D / (-d1 * d3 / Math.sqrt(1.0D + d3 * d3) + d2));
/*  96 */       this.hl = (1.0D / (-d1 * d4 / Math.sqrt(1.0D + d4 * d4) + d2));
/*  97 */       double d9 = d7 - this.hr;
/*  98 */       double d10 = d8 - this.hl;
/*  99 */       this.mmb_1 = (d5 - d10);
/* 100 */       this.mpa_1 = (d5 + d9);
/*     */       
/* 102 */       this.s = (d7 + d8);
/* 103 */       this.pm = ((d9 + d10) / this.s);
/* 104 */       this.pr = (this.hr / this.s);
/* 105 */       this.pmr = (this.pm + this.pr);
/*     */       
/* 107 */       this.a_setup = d1;
/* 108 */       this.b_setup = d2;
/*     */     }
/*     */     
/*     */     for (;;)
/*     */     {
/* 113 */       this.u = this.randomGenerator.raw();
/* 114 */       this.v = this.randomGenerator.raw();
/* 115 */       if (this.u <= this.pm)
/*     */       {
/* 117 */         this.x = (this.mmb_1 + this.u * this.s);
/* 118 */         if (Math.log(this.v) <= -d1 * Math.sqrt(1.0D + this.x * this.x) + d2 * this.x + this.samb) {
/*     */           break;
/*     */         }
/* 121 */       } else if (this.u <= this.pmr)
/*     */       {
/* 123 */         this.e = (-Math.log((this.u - this.pm) / this.pr));
/* 124 */         this.x = (this.mpa_1 + this.hr * this.e);
/* 125 */         if (Math.log(this.v) - this.e <= -d1 * Math.sqrt(1.0D + this.x * this.x) + d2 * this.x + this.samb) {
/*     */           break;
/*     */         }
/*     */       } else {
/* 129 */         this.e = Math.log((this.u - this.pmr) / (1.0D - this.pmr));
/* 130 */         this.x = (this.mmb_1 + this.hl * this.e);
/* 131 */         if (Math.log(this.v) + this.e <= -d1 * Math.sqrt(1.0D + this.x * this.x) + d2 * this.x + this.samb) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 136 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 142 */     this.alpha = paramDouble1;
/* 143 */     this.beta = paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 149 */     synchronized (shared) {
/* 150 */       double d = shared.nextDouble(paramDouble1, paramDouble2);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 157 */     return getClass().getName() + "(" + this.alpha + "," + this.beta + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 164 */     synchronized (shared) {
/* 165 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Hyperbolic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */