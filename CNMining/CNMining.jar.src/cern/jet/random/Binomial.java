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
/*     */ public class Binomial
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*  37 */   private int n_last = -1; private int n_prev = -1;
/*  38 */   private double p_last = -1.0D; private double p_prev = -1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  46 */   protected static Binomial shared = new Binomial(1, 0.5D, AbstractDistribution.makeDefaultGenerator());
/*     */   protected int n;
/*     */   protected double p;
/*     */   private double par;
/*     */   private double np;
/*     */   private double p0;
/*     */   private double q;
/*     */   
/*     */   public Binomial(int paramInt, double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  56 */     setRandomGenerator(paramRandomElement);
/*  57 */     setNandP(paramInt, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(int paramInt)
/*     */   {
/*  63 */     return Probability.binomial(paramInt, this.n, this.p);
/*     */   }
/*     */   
/*     */ 
/*     */   private double cdfSlow(int paramInt)
/*     */   {
/*  69 */     if (paramInt < 0) { throw new IllegalArgumentException();
/*     */     }
/*  71 */     double d = 0.0D;
/*  72 */     for (int i = 0; i <= paramInt; i++) { d += pdf(i);
/*     */     }
/*  74 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int b;
/*     */   
/*     */ 
/*     */   private int m;
/*     */   
/*     */ 
/*     */   private int nm;
/*     */   
/*     */ 
/*     */   private double pq;
/*     */   
/*     */   private double rc;
/*     */   
/*     */   private double ss;
/*     */   
/*     */   private double xm;
/*     */   
/*     */   private double xl;
/*     */   
/*     */   private double xr;
/*     */   
/*     */   private double ll;
/*     */   
/*     */   private double lr;
/*     */   
/*     */   private double c;
/*     */   
/*     */   private double p1;
/*     */   
/*     */   private double p2;
/*     */   
/*     */   private double p3;
/*     */   
/*     */   private double p4;
/*     */   
/*     */   private double ch;
/*     */   
/*     */   private double log_p;
/*     */   
/*     */   private double log_q;
/*     */   
/*     */   private double log_n;
/*     */   
/*     */   protected int generateBinomial(int paramInt, double paramDouble)
/*     */   {
/*     */     int j;
/*     */     
/*     */     double d1;
/*     */     
/* 128 */     if ((paramInt != this.n_last) || (paramDouble != this.p_last)) {
/* 129 */       this.n_last = paramInt;
/* 130 */       this.p_last = paramDouble;
/* 131 */       this.par = Math.min(paramDouble, 1.0D - paramDouble);
/* 132 */       this.q = (1.0D - this.par);
/* 133 */       this.np = (paramInt * this.par);
/*     */       
/*     */ 
/*     */ 
/* 137 */       if (this.np <= 0.0D) { return -1;
/*     */       }
/* 139 */       double d2 = this.np + this.par;
/* 140 */       this.m = ((int)d2);
/* 141 */       if (this.np < 10.0D) {
/* 142 */         this.p0 = Math.exp(paramInt * Math.log(this.q));
/* 143 */         int i = (int)(this.np + 10.0D * Math.sqrt(this.np * this.q));
/* 144 */         this.b = Math.min(paramInt, i);
/*     */       }
/*     */       else {
/* 147 */         this.rc = ((paramInt + 1.0D) * (this.pq = this.par / this.q));
/* 148 */         this.ss = (this.np * this.q);
/* 149 */         j = (int)(2.195D * Math.sqrt(this.ss) - 4.6D * this.q);
/* 150 */         this.xm = (this.m + 0.5D);
/* 151 */         this.xl = (this.m - j);
/* 152 */         this.xr = (this.m + j + 1L);
/* 153 */         d1 = (d2 - this.xl) / (d2 - this.xl * this.par);this.ll = (d1 * (1.0D + 0.5D * d1));
/* 154 */         d1 = (this.xr - d2) / (this.xr * this.q);this.lr = (d1 * (1.0D + 0.5D * d1));
/* 155 */         this.c = (0.134D + 20.5D / (15.3D + this.m));
/*     */         
/* 157 */         this.p1 = (j + 0.5D);
/* 158 */         this.p2 = (this.p1 * (1.0D + this.c + this.c));
/* 159 */         this.p3 = (this.p2 + this.c / this.ll);
/* 160 */         this.p4 = (this.p3 + this.c / this.lr);
/*     */       } }
/*     */     int k;
/*     */     double d3;
/* 164 */     if (this.np < 10.0D)
/*     */     {
/*     */ 
/* 167 */       k = 0;
/* 168 */       double d8 = this.p0;
/* 169 */       d3 = this.randomGenerator.raw();
/* 170 */       while (d3 > d8) {
/* 171 */         k++;
/* 172 */         if (k > this.b) {
/* 173 */           d3 = this.randomGenerator.raw();
/* 174 */           k = 0;
/* 175 */           d8 = this.p0;
/*     */         }
/*     */         else {
/* 178 */           d3 -= d8;
/* 179 */           d8 = (paramInt - k + 1) * this.par * d8 / (k * this.q);
/*     */         }
/*     */       }
/* 182 */       return paramDouble > 0.5D ? paramInt - k : k;
/*     */     }
/*     */     for (;;)
/*     */     {
/* 186 */       double d4 = this.randomGenerator.raw();
/* 187 */       if ((d3 = this.randomGenerator.raw() * this.p4) <= this.p1) {
/* 188 */         k = (int)(this.xm - d3 + this.p1 * d4);
/* 189 */         return paramDouble > 0.5D ? paramInt - k : k; }
/*     */       double d5;
/* 191 */       if (d3 <= this.p2) {
/* 192 */         d5 = this.xl + (d3 - this.p1) / this.c;
/* 193 */         if ((d4 = d4 * this.c + 1.0D - Math.abs(this.xm - d5) / this.p1) >= 1.0D) continue;
/* 194 */         k = (int)d5;
/*     */       }
/* 196 */       else if (d3 <= this.p3) {
/* 197 */         if ((d5 = this.xl + Math.log(d4) / this.ll) < 0.0D) continue;
/* 198 */         k = (int)d5;
/* 199 */         d4 *= (d3 - this.p2) * this.ll;
/*     */       }
/*     */       else {
/* 202 */         if ((k = (int)(this.xr - Math.log(d4) / this.lr)) > paramInt) continue;
/* 203 */         d4 *= (d3 - this.p3) * this.lr;
/*     */       }
/*     */       
/*     */       int i1;
/* 207 */       if (((i1 = Math.abs(k - this.m)) <= 20) || (i1 + i1 + 2L >= this.ss))
/*     */       {
/*     */ 
/* 210 */         d1 = 1.0D;
/* 211 */         if (this.m < k) {
/* 212 */           for (j = this.m; j < k;) {
/* 213 */             if (d1 *= (this.rc / ++j - this.pq) < d4)
/*     */               break;
/*     */           }
/*     */         } else {
/* 217 */           for (j = k; j < this.m;)
/* 218 */             if (d4 *= (this.rc / ++j - this.pq) > d1)
/*     */               break;
/*     */         }
/* 221 */         if (d4 <= d1) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       else {
/* 226 */         d4 = Math.log(d4);
/* 227 */         double d6 = -i1 * i1 / (this.ss + this.ss);
/* 228 */         double d7 = i1 / this.ss * ((i1 * (i1 * 0.3333333333333333D + 0.625D) + 0.16666666666666666D) / this.ss + 0.5D);
/* 229 */         if (d4 > d6 - d7) {
/* 230 */           if (d4 <= d6 + d7) {
/* 231 */             if ((paramInt != this.n_prev) || (this.par != this.p_prev)) {
/* 232 */               this.n_prev = paramInt;
/* 233 */               this.p_prev = this.par;
/*     */               
/* 235 */               this.nm = (paramInt - this.m + 1);
/* 236 */               this.ch = (this.xm * Math.log((this.m + 1.0D) / (this.pq * this.nm)) + Arithmetic.stirlingCorrection(this.m + 1) + Arithmetic.stirlingCorrection(this.nm));
/*     */             }
/*     */             
/* 239 */             int i2 = paramInt - k + 1;
/*     */             
/*     */ 
/*     */ 
/* 243 */             if (d4 <= this.ch + (paramInt + 1.0D) * Math.log(this.nm / i2) + (k + 0.5D) * Math.log(i2 * this.pq / (k + 1.0D)) - Arithmetic.stirlingCorrection(k + 1) - Arithmetic.stirlingCorrection(i2))
/*     */               break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 249 */     return paramDouble > 0.5D ? paramInt - k : k;
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 255 */     return generateBinomial(this.n, this.p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt(int paramInt, double paramDouble)
/*     */   {
/* 264 */     if (paramInt * Math.min(paramDouble, 1.0D - paramDouble) <= 0.0D) throw new IllegalArgumentException();
/* 265 */     return generateBinomial(paramInt, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 271 */     if (paramInt < 0) throw new IllegalArgumentException();
/* 272 */     int i = this.n - paramInt;
/* 273 */     return Math.exp(this.log_n - Arithmetic.logFactorial(paramInt) - Arithmetic.logFactorial(i) + this.log_p * paramInt + this.log_q * i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNandP(int paramInt, double paramDouble)
/*     */   {
/* 282 */     if (paramInt * Math.min(paramDouble, 1.0D - paramDouble) <= 0.0D) throw new IllegalArgumentException();
/* 283 */     this.n = paramInt;
/* 284 */     this.p = paramDouble;
/*     */     
/* 286 */     this.log_p = Math.log(paramDouble);
/* 287 */     this.log_q = Math.log(1.0D - paramDouble);
/* 288 */     this.log_n = Arithmetic.logFactorial(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int staticNextInt(int paramInt, double paramDouble)
/*     */   {
/* 297 */     synchronized (shared) {
/* 298 */       int i = shared.nextInt(paramInt, paramDouble);return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 305 */     return getClass().getName() + "(" + this.n + "," + this.p + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 312 */     synchronized (shared) {
/* 313 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Binomial.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */