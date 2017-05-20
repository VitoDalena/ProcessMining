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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Poisson
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*     */   protected double mean;
/*  48 */   protected double my_old = -1.0D;
/*     */   protected double p;
/*  50 */   protected double q; protected double p0; protected double[] pp = new double[36];
/*     */   
/*     */   protected int llll;
/*     */   
/*  54 */   protected double my_last = -1.0D;
/*     */   protected double ll;
/*     */   protected int k2;
/*     */   protected int k4;
/*     */   protected int k1;
/*     */   protected int k5;
/*     */   protected double dl;
/*     */   protected double dr;
/*     */   protected double r1;
/*     */   protected double r2;
/*     */   protected double r4;
/*     */   protected double r5;
/*     */   protected double lr;
/*     */   protected double l_my;
/*     */   protected double c_pm;
/*  69 */   protected double f1; protected double f2; protected double f4; protected double f5; protected double p1; protected double p2; protected double p3; protected double p4; protected double p5; protected double p6; protected int m; protected static final double MEAN_MAX = 2.147483647E9D; protected static final double SWITCH_MEAN = 10.0D; protected static Poisson shared = new Poisson(0.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */   public Poisson(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  75 */     setRandomGenerator(paramRandomElement);
/*  76 */     setMean(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(int paramInt)
/*     */   {
/*  82 */     return Probability.poisson(paramInt, this.mean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  91 */     Poisson localPoisson = (Poisson)super.clone();
/*  92 */     if (this.pp != null) localPoisson.pp = ((double[])this.pp.clone());
/*  93 */     return localPoisson;
/*     */   }
/*     */   
/*  96 */   private static double f(int paramInt, double paramDouble1, double paramDouble2) { return Math.exp(paramInt * paramDouble1 - Arithmetic.logFactorial(paramInt) - paramDouble2); }
/*     */   
/*     */ 
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 102 */     return nextInt(this.mean);
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
/*     */   public int nextInt(double paramDouble)
/*     */   {
/* 124 */     RandomElement localRandomElement = this.randomGenerator;
/* 125 */     double d1 = paramDouble;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 137 */     if (d1 < 10.0D) {
/* 138 */       if (d1 != this.my_old) {
/* 139 */         this.my_old = d1;
/* 140 */         this.llll = 0;
/* 141 */         this.p = Math.exp(-d1);
/* 142 */         this.q = this.p;
/* 143 */         this.p0 = this.p;
/*     */       }
/*     */       
/* 146 */       this.m = (d1 > 1.0D ? (int)d1 : 1);
/*     */       for (;;) {
/* 148 */         double d2 = localRandomElement.raw();
/* 149 */         int i = 0;
/* 150 */         if (d2 <= this.p0) return i;
/* 151 */         if (this.llll != 0) {
/* 152 */           int j = d2 > 0.458D ? Math.min(this.llll, this.m) : 1;
/* 153 */           for (i = j; i <= this.llll; i++) if (d2 <= this.pp[i]) return i;
/* 154 */           if (this.llll == 35) {}
/*     */         } else {
/* 156 */           for (i = this.llll + 1; i <= 35; i++) {
/* 157 */             this.p *= d1 / i;
/* 158 */             this.q += this.p;
/* 159 */             this.pp[i] = this.q;
/* 160 */             if (d2 <= this.q) {
/* 161 */               this.llll = i;
/* 162 */               return i;
/*     */             }
/*     */           }
/* 165 */           this.llll = 35;
/*     */         }
/*     */       } }
/* 168 */     if (d1 < 2.147483647E9D)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */       this.m = ((int)d1);
/* 177 */       if (d1 != this.my_last) {
/* 178 */         this.my_last = d1;
/*     */         
/*     */ 
/* 181 */         double d3 = Math.sqrt(d1 + 0.25D);
/*     */         
/*     */ 
/*     */ 
/* 185 */         this.k2 = ((int)Math.ceil(d1 - 0.5D - d3));
/* 186 */         this.k4 = ((int)(d1 - 0.5D + d3));
/* 187 */         this.k1 = (this.k2 + this.k2 - this.m + 1);
/* 188 */         this.k5 = (this.k4 + this.k4 - this.m);
/*     */         
/*     */ 
/* 191 */         this.dl = (this.k2 - this.k1);
/* 192 */         this.dr = (this.k5 - this.k4);
/*     */         
/*     */ 
/* 195 */         this.r1 = (d1 / this.k1);
/* 196 */         this.r2 = (d1 / this.k2);
/* 197 */         this.r4 = (d1 / (this.k4 + 1));
/* 198 */         this.r5 = (d1 / (this.k5 + 1));
/*     */         
/*     */ 
/* 201 */         this.ll = Math.log(this.r1);
/* 202 */         this.lr = (-Math.log(this.r5));
/*     */         
/*     */ 
/* 205 */         this.l_my = Math.log(d1);
/* 206 */         this.c_pm = (this.m * this.l_my - Arithmetic.logFactorial(this.m));
/*     */         
/*     */ 
/* 209 */         this.f2 = f(this.k2, this.l_my, this.c_pm);
/* 210 */         this.f4 = f(this.k4, this.l_my, this.c_pm);
/* 211 */         this.f1 = f(this.k1, this.l_my, this.c_pm);
/* 212 */         this.f5 = f(this.k5, this.l_my, this.c_pm);
/*     */         
/*     */ 
/*     */ 
/* 216 */         this.p1 = (this.f2 * (this.dl + 1.0D));
/* 217 */         this.p2 = (this.f2 * this.dl + this.p1);
/* 218 */         this.p3 = (this.f4 * (this.dr + 1.0D) + this.p2);
/* 219 */         this.p4 = (this.f4 * this.dr + this.p3);
/* 220 */         this.p5 = (this.f1 / this.ll + this.p4);
/* 221 */         this.p6 = (this.f5 / this.lr + this.p5); }
/*     */       double d6;
/*     */       int n;
/*     */       label1321:
/*     */       do { double d4;
/*     */         int k;
/* 227 */         do { double d5; int i1; if ((d4 = localRandomElement.raw() * this.p6) < this.p2)
/*     */           {
/*     */ 
/* 230 */             if ((d5 = d4 - this.p1) < 0.0D) { return this.k2 + (int)(d4 / this.f2);
/*     */             }
/* 232 */             if ((d6 = d5 / this.dl) < this.f1) { return this.k1 + (int)(d5 / this.f1);
/*     */             }
/*     */             
/*     */ 
/* 236 */             k = (int)(this.dl * localRandomElement.raw()) + 1;
/* 237 */             if (d6 <= this.f2 - k * (this.f2 - this.f2 / this.r2)) {
/* 238 */               return this.k2 - k;
/*     */             }
/* 240 */             if ((d5 = this.f2 + this.f2 - d6) < 1.0D) {
/* 241 */               i1 = this.k2 + k;
/* 242 */               if (d5 <= this.f2 + k * (1.0D - this.f2) / (this.dl + 1.0D)) {
/* 243 */                 return i1;
/*     */               }
/* 245 */               if (d5 <= f(i1, this.l_my, this.c_pm)) return i1;
/*     */             }
/* 247 */             n = this.k2 - k;
/*     */             break label1321; }
/* 249 */           if (d4 < this.p4)
/*     */           {
/* 251 */             if ((d5 = d4 - this.p3) < 0.0D) { return this.k4 - (int)((d4 - this.p2) / this.f4);
/*     */             }
/* 253 */             if ((d6 = d5 / this.dr) < this.f5) { return this.k5 - (int)(d5 / this.f5);
/*     */             }
/*     */             
/*     */ 
/* 257 */             k = (int)(this.dr * localRandomElement.raw()) + 1;
/* 258 */             if (d6 <= this.f4 - k * (this.f4 - this.f4 * this.r4)) {
/* 259 */               return this.k4 + k;
/*     */             }
/* 261 */             if ((d5 = this.f4 + this.f4 - d6) < 1.0D) {
/* 262 */               i1 = this.k4 - k;
/* 263 */               if (d5 <= this.f4 + k * (1.0D - this.f4) / this.dr) {
/* 264 */                 return i1;
/*     */               }
/* 266 */               if (d5 <= f(i1, this.l_my, this.c_pm)) return i1;
/*     */             }
/* 268 */             n = this.k4 + k;
/*     */             break label1321;
/*     */           }
/* 271 */           d6 = localRandomElement.raw();
/* 272 */           if (d4 >= this.p5) break;
/* 273 */           k = (int)(1.0D - Math.log(d6) / this.ll);
/* 274 */         } while ((n = this.k1 - k) < 0);
/* 275 */         d6 *= (d4 - this.p4) * this.ll;
/* 276 */         if (d6 <= this.f1 - k * (this.f1 - this.f1 / this.r1)) { return n;
/*     */           
/*     */ 
/* 279 */           k = (int)(1.0D - Math.log(d6) / this.lr);
/* 280 */           n = this.k5 + k;
/* 281 */           d6 *= (d4 - this.p5) * this.lr;
/* 282 */           if (d6 <= this.f5 - k * (this.f5 - this.f5 * this.r5)) { return n;
/*     */           }
/*     */           
/*     */ 
/*     */         }
/*     */         
/*     */       }
/* 289 */       while (Math.log(d6) > n * this.l_my - Arithmetic.logFactorial(n) - this.c_pm); return n;
/*     */     }
/*     */     
/*     */ 
/* 293 */     return (int)d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 300 */     return Math.exp(paramInt * Math.log(this.mean) - Arithmetic.logFactorial(paramInt) - this.mean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMean(double paramDouble)
/*     */   {
/* 309 */     this.mean = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int staticNextInt(double paramDouble)
/*     */   {
/* 315 */     synchronized (shared) {
/* 316 */       shared.setMean(paramDouble);
/* 317 */       int i = shared.nextInt();return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 324 */     return getClass().getName() + "(" + this.mean + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 331 */     synchronized (shared) {
/* 332 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Poisson.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */