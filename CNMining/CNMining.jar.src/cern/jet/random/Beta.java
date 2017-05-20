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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Beta
/*     */   extends AbstractContinousDistribution
/*     */ {
/*  49 */   double a_last = 0.0D; double b_last = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   double p_last = 0.0D; double q_last = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */   protected static Beta shared = new Beta(10.0D, 10.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   protected double alpha;
/*     */   protected double beta;
/*     */   
/*     */   public Beta(double paramDouble1, double paramDouble2, RandomElement paramRandomElement) {
/*  70 */     setRandomGenerator(paramRandomElement);
/*  71 */     setState(paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double b00(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*  79 */     if ((paramDouble1 != this.a_last) || (paramDouble2 != this.b_last)) {
/*  80 */       this.a_last = paramDouble1;
/*  81 */       this.b_last = paramDouble2;
/*     */       
/*  83 */       this.a_ = (paramDouble1 - 1.0D);
/*  84 */       this.b_ = (paramDouble2 - 1.0D);
/*  85 */       this.c = (paramDouble2 * this.b_ / (paramDouble1 * this.a_));
/*  86 */       this.t = (this.c == 1.0D ? 0.5D : (1.0D - Math.sqrt(this.c)) / (1.0D - this.c));
/*  87 */       this.fa = Math.exp(this.a_ * Math.log(this.t));
/*  88 */       this.fb = Math.exp(this.b_ * Math.log(1.0D - this.t));
/*     */       
/*  90 */       this.p1 = (this.t / paramDouble1);
/*  91 */       this.p2 = ((1.0D - this.t) / paramDouble2 + this.p1); }
/*     */     double d3;
/*     */     for (;;) { double d1;
/*     */       double d4;
/*  95 */       double d2; if ((d1 = paramRandomElement.raw() * this.p2) <= this.p1) {
/*  96 */         d4 = Math.exp(Math.log(d1 / this.p1) / paramDouble1);d3 = this.t * d4;
/*     */         
/*  98 */         if ((d2 = paramRandomElement.raw() * this.fb) > 1.0D - this.b_ * d3)
/*     */         {
/* 100 */           if ((d2 <= 1.0D + (this.fb - 1.0D) * d4) && 
/*     */           
/* 102 */             (Math.log(d2) <= this.b_ * Math.log(1.0D - d3)))
/*     */             break;
/*     */         }
/*     */       } else {
/* 106 */         d4 = Math.exp(Math.log((d1 - this.p1) / (this.p2 - this.p1)) / paramDouble2);d3 = 1.0D - (1.0D - this.t) * d4;
/*     */         
/* 108 */         if ((d2 = paramRandomElement.raw() * this.fa) > 1.0D - this.a_ * (1.0D - d3))
/*     */         {
/* 110 */           if ((d2 <= 1.0D + (this.fa - 1.0D) * d4) && 
/*     */           
/* 112 */             (Math.log(d2) <= this.a_ * Math.log(d3)))
/*     */             break; }
/*     */       }
/*     */     }
/* 116 */     return d3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double b01(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 124 */     if ((paramDouble1 != this.a_last) || (paramDouble2 != this.b_last)) {
/* 125 */       this.a_last = paramDouble1;
/* 126 */       this.b_last = paramDouble2;
/*     */       
/* 128 */       this.a_ = (paramDouble1 - 1.0D);
/* 129 */       this.b_ = (paramDouble2 - 1.0D);
/* 130 */       this.t = (this.a_ / (paramDouble1 - paramDouble2));
/* 131 */       this.fb = Math.exp((this.b_ - 1.0D) * Math.log(1.0D - this.t));this.fa = (paramDouble1 - (paramDouble1 + this.b_) * this.t);
/* 132 */       this.t -= (this.t - (1.0D - this.fa) * (1.0D - this.t) * this.fb / paramDouble2) / (1.0D - this.fa * this.fb);
/* 133 */       this.fa = Math.exp(this.a_ * Math.log(this.t));
/* 134 */       this.fb = Math.exp(this.b_ * Math.log(1.0D - this.t));
/* 135 */       if (this.b_ <= 1.0D) {
/* 136 */         this.ml = ((1.0D - this.fb) / this.t);
/* 137 */         this.mu = (this.b_ * this.t);
/*     */       }
/*     */       else {
/* 140 */         this.ml = this.b_;
/* 141 */         this.mu = (1.0D - this.fb);
/*     */       }
/* 143 */       this.p1 = (this.t / paramDouble1);
/* 144 */       this.p2 = (this.fb * (1.0D - this.t) / paramDouble2 + this.p1); }
/*     */     double d3;
/*     */     for (;;) { double d1;
/*     */       double d4;
/* 148 */       double d2; if ((d1 = paramRandomElement.raw() * this.p2) <= this.p1) {
/* 149 */         d4 = Math.exp(Math.log(d1 / this.p1) / paramDouble1);d3 = this.t * d4;
/*     */         
/* 151 */         if ((d2 = paramRandomElement.raw()) > 1.0D - this.ml * d3)
/*     */         {
/* 153 */           if ((d2 <= 1.0D - this.mu * d4) && 
/*     */           
/* 155 */             (Math.log(d2) <= this.b_ * Math.log(1.0D - d3)))
/*     */             break;
/*     */         }
/*     */       } else {
/* 159 */         d4 = Math.exp(Math.log((d1 - this.p1) / (this.p2 - this.p1)) / paramDouble2);d3 = 1.0D - (1.0D - this.t) * d4;
/*     */         
/* 161 */         if ((d2 = paramRandomElement.raw() * this.fa) > 1.0D - this.a_ * (1.0D - d3))
/*     */         {
/* 163 */           if ((d2 <= 1.0D + (this.fa - 1.0D) * d4) && 
/*     */           
/* 165 */             (Math.log(d2) <= this.a_ * Math.log(d3)))
/*     */             break; }
/*     */       }
/*     */     }
/* 169 */     return d3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double b1prs(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 177 */     if ((paramDouble1 != this.p_last) || (paramDouble2 != this.q_last)) {
/* 178 */       this.p_last = paramDouble1;
/* 179 */       this.q_last = paramDouble2;
/*     */       
/* 181 */       this.a = (paramDouble1 - 1.0D);
/* 182 */       this.b = (paramDouble2 - 1.0D);
/* 183 */       this.s = (this.a + this.b);this.m = (this.a / this.s);
/* 184 */       if ((this.a > 1.0D) || (this.b > 1.0D)) { this.D = Math.sqrt(this.m * (1.0D - this.m) / (this.s - 1.0D));
/*     */       }
/* 186 */       if (this.a <= 1.0D) {
/* 187 */         this.x2 = (this.Dl = this.m * 0.5D);this.x1 = (this.z2 = 0.0D);this.f1 = (this.ll = 0.0D);
/*     */       }
/*     */       else {
/* 190 */         this.x2 = (this.m - this.D);this.x1 = (this.x2 - this.D);
/* 191 */         this.z2 = (this.x2 * (1.0D - (1.0D - this.x2) / (this.s * this.D)));
/* 192 */         if ((this.x1 <= 0.0D) || ((this.s - 6.0D) * this.x2 - this.a + 3.0D > 0.0D)) {
/* 193 */           this.x1 = this.z2;this.x2 = ((this.x1 + this.m) * 0.5D);
/* 194 */           this.Dl = (this.m - this.x2);
/*     */         }
/*     */         else {
/* 197 */           this.Dl = this.D;
/*     */         }
/* 199 */         this.f1 = f(this.x1, this.a, this.b, this.m);
/* 200 */         this.ll = (this.x1 * (1.0D - this.x1) / (this.s * (this.m - this.x1)));
/*     */       }
/* 202 */       this.f2 = f(this.x2, this.a, this.b, this.m);
/*     */       
/* 204 */       if (this.b <= 1.0D) {
/* 205 */         this.x4 = (1.0D - (this.D = (1.0D - this.m) * 0.5D));this.x5 = (this.z4 = 1.0D);this.f5 = (this.lr = 0.0D);
/*     */       }
/*     */       else {
/* 208 */         this.x4 = (this.m + this.D);this.x5 = (this.x4 + this.D);
/* 209 */         this.z4 = (this.x4 * (1.0D + (1.0D - this.x4) / (this.s * this.D)));
/* 210 */         if ((this.x5 >= 1.0D) || ((this.s - 6.0D) * this.x4 - this.a + 3.0D < 0.0D)) {
/* 211 */           this.x5 = this.z4;this.x4 = ((this.m + this.x5) * 0.5D);
/* 212 */           this.D = (this.x4 - this.m);
/*     */         }
/* 214 */         this.f5 = f(this.x5, this.a, this.b, this.m);
/* 215 */         this.lr = (this.x5 * (1.0D - this.x5) / (this.s * (this.x5 - this.m)));
/*     */       }
/* 217 */       this.f4 = f(this.x4, this.a, this.b, this.m);
/*     */       
/* 219 */       this.p1 = (this.f2 * (this.Dl + this.Dl));
/* 220 */       this.p2 = (this.f4 * (this.D + this.D) + this.p1);
/* 221 */       this.p3 = (this.f1 * this.ll + this.p2);
/* 222 */       this.p4 = (this.f5 * this.lr + this.p3); }
/*     */     double d3;
/*     */     double d4;
/*     */     label1311:
/* 226 */     do { double d1; double d5; do { do { double d2; if ((d1 = paramRandomElement.raw() * this.p4) <= this.p1)
/*     */           {
/* 228 */             if ((d3 = d1 / this.Dl - this.f2) <= 0.0D) { return this.m - d1 / this.f2;
/*     */             }
/* 230 */             if (d3 <= this.f1) { return this.x2 - d3 / this.f1 * this.Dl;
/*     */             }
/* 232 */             d2 = this.Dl * (d1 = paramRandomElement.raw());
/* 233 */             d4 = this.x2 - d2;d5 = this.x2 + d2;
/*     */             
/* 235 */             if (d3 * (this.x2 - this.z2) <= this.f2 * (d4 - this.z2)) return d4;
/* 236 */             if ((d2 = this.f2 + this.f2 - d3) >= 1.0D)
/*     */               break label1311;
/* 238 */             if (d2 <= this.f2 + (1.0D - this.f2) * d1) { return d5;
/*     */             }
/* 240 */             if (d2 > f(d5, this.a, this.b, this.m)) break label1311; return d5;
/*     */           }
/*     */           
/* 243 */           if (d1 <= this.p2) {
/* 244 */             d1 -= this.p1;
/*     */             
/* 246 */             if ((d3 = d1 / this.D - this.f4) <= 0.0D) { return this.m + d1 / this.f4;
/*     */             }
/* 248 */             if (d3 <= this.f5) { return this.x4 + d3 / this.f5 * this.D;
/*     */             }
/* 250 */             d2 = this.D * (d1 = paramRandomElement.raw());
/* 251 */             d4 = this.x4 + d2;d5 = this.x4 - d2;
/*     */             
/* 253 */             if (d3 * (this.z4 - this.x4) <= this.f4 * (this.z4 - d4)) return d4;
/* 254 */             if ((d2 = this.f4 + this.f4 - d3) >= 1.0D)
/*     */               break label1311;
/* 256 */             if (d2 <= this.f4 + (1.0D - this.f4) * d1) { return d5;
/*     */             }
/* 258 */             if (d2 > f(d5, this.a, this.b, this.m)) break label1311; return d5;
/*     */           }
/*     */           
/* 261 */           if (d1 > this.p3) break;
/* 262 */           d5 = Math.log(d1 = (d1 - this.p2) / (this.p3 - this.p2));
/* 263 */         } while ((d4 = this.x1 + this.ll * d5) <= 0.0D);
/* 264 */         d3 = paramRandomElement.raw() * d1;
/*     */         
/*     */ 
/* 267 */         if (d3 <= 1.0D + d5) return d4;
/* 268 */         d3 *= this.f1; break;
/*     */         
/*     */ 
/* 271 */         d5 = Math.log(d1 = (d1 - this.p3) / (this.p4 - this.p3));
/* 272 */       } while ((d4 = this.x5 - this.lr * d5) >= 1.0D);
/* 273 */       d3 = paramRandomElement.raw() * d1;
/*     */       
/*     */ 
/* 276 */       if (d3 <= 1.0D + d5) return d4;
/* 277 */       d3 *= this.f5;
/*     */ 
/*     */     }
/* 280 */     while (Math.log(d3) > this.a * Math.log(d4 / this.m) + this.b * Math.log((1.0D - d4) / (1.0D - this.m))); return d4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/* 287 */     return Probability.beta(this.alpha, this.beta, paramDouble);
/*     */   }
/*     */   
/* 290 */   private static double f(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) { return Math.exp(paramDouble2 * Math.log(paramDouble1 / paramDouble4) + paramDouble3 * Math.log((1.0D - paramDouble1) / (1.0D - paramDouble4))); }
/*     */   
/*     */ 
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/* 296 */     return nextDouble(this.alpha, this.beta);
/*     */   }
/*     */   
/*     */ 
/*     */   double PDF_CONST;
/*     */   
/*     */   double a_;
/*     */   
/*     */   double b_;
/*     */   
/*     */   double t;
/*     */   
/*     */   double fa;
/*     */   
/*     */   double fb;
/*     */   
/*     */   double p1;
/*     */   
/*     */   double p2;
/*     */   
/*     */   double c;
/*     */   
/*     */   double ml;
/*     */   
/*     */   double mu;
/*     */   
/*     */   double a;
/*     */   double b;
/*     */   double s;
/*     */   double m;
/*     */   double D;
/*     */   double Dl;
/*     */   double x1;
/*     */   double x2;
/*     */   double x4;
/*     */   double x5;
/*     */   double f1;
/*     */   double f2;
/*     */   double f4;
/*     */   double f5;
/*     */   double ll;
/*     */   double lr;
/*     */   double z2;
/*     */   double z4;
/*     */   double p3;
/*     */   double p4;
/*     */   public double nextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 344 */     double d1 = paramDouble1;
/* 345 */     double d2 = paramDouble2;
/* 346 */     if (d1 > 1.0D) {
/* 347 */       if (d2 > 1.0D) return b1prs(d1, d2, this.randomGenerator);
/* 348 */       if (d2 < 1.0D) return 1.0D - b01(d2, d1, this.randomGenerator);
/* 349 */       if (d2 == 1.0D) {
/* 350 */         return Math.exp(Math.log(this.randomGenerator.raw()) / d1);
/*     */       }
/*     */     }
/*     */     
/* 354 */     if (d1 < 1.0D) {
/* 355 */       if (d2 > 1.0D) return b01(d1, d2, this.randomGenerator);
/* 356 */       if (d2 < 1.0D) return b00(d1, d2, this.randomGenerator);
/* 357 */       if (d2 == 1.0D) {
/* 358 */         return Math.exp(Math.log(this.randomGenerator.raw()) / d1);
/*     */       }
/*     */     }
/*     */     
/* 362 */     if (d1 == 1.0D) {
/* 363 */       if (d2 != 1.0D) return 1.0D - Math.exp(Math.log(this.randomGenerator.raw()) / d2);
/* 364 */       if (d2 == 1.0D) { return this.randomGenerator.raw();
/*     */       }
/*     */     }
/* 367 */     return 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/* 373 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) return 0.0D;
/* 374 */     return Math.exp(this.PDF_CONST) * Math.pow(paramDouble, this.alpha - 1.0D) * Math.pow(1.0D - paramDouble, this.beta - 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 380 */     this.alpha = paramDouble1;
/* 381 */     this.beta = paramDouble2;
/* 382 */     this.PDF_CONST = (Fun.logGamma(paramDouble1 + paramDouble2) - Fun.logGamma(paramDouble1) - Fun.logGamma(paramDouble2));
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 388 */     synchronized (shared) {
/* 389 */       double d = shared.nextDouble(paramDouble1, paramDouble2);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 396 */     return getClass().getName() + "(" + this.alpha + "," + this.beta + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 403 */     synchronized (shared) {
/* 404 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Beta.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */