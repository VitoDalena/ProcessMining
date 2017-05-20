/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.jet.math.Arithmetic;
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
/*     */ public class HyperGeometric
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*  44 */   private int N_last = -1; private int M_last = -1; private int n_last = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */   protected static HyperGeometric shared = new HyperGeometric(1, 1, 1, AbstractDistribution.makeDefaultGenerator());
/*     */   protected int my_N;
/*     */   protected int my_s;
/*     */   
/*     */   public HyperGeometric(int paramInt1, int paramInt2, int paramInt3, RandomElement paramRandomElement) {
/*  63 */     setRandomGenerator(paramRandomElement);
/*  64 */     setState(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*  67 */   private static double fc_lnpk(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { return Arithmetic.logFactorial(paramInt1) + Arithmetic.logFactorial(paramInt3 - paramInt1) + Arithmetic.logFactorial(paramInt4 - paramInt1) + Arithmetic.logFactorial(paramInt2 + paramInt1); }
/*     */   
/*     */   protected int my_n;
/*     */   private int N_Mn;
/*     */   private int m;
/*     */   private int mp;
/*     */   private int b;
/*     */   private double Mp;
/*     */   protected int hmdu(int paramInt1, int paramInt2, int paramInt3, RandomElement paramRandomElement)
/*     */   {
/*  77 */     if ((paramInt1 != this.N_last) || (paramInt2 != this.M_last) || (paramInt3 != this.n_last)) {
/*  78 */       this.N_last = paramInt1;
/*  79 */       this.M_last = paramInt2;
/*  80 */       this.n_last = paramInt3;
/*     */       
/*  82 */       this.Mp = (paramInt2 + 1);
/*  83 */       this.np = (paramInt3 + 1);this.N_Mn = (paramInt1 - paramInt2 - paramInt3);
/*     */       
/*  85 */       double d1 = this.Mp / (paramInt1 + 2.0D);
/*  86 */       double d2 = this.np * d1;
/*  87 */       if (((this.m = (int)d2) == d2) && (d1 == 0.5D)) {
/*  88 */         this.mp = (this.m--);
/*     */       }
/*     */       else {
/*  91 */         this.mp = (this.m + 1);
/*     */       }
/*     */       
/*     */ 
/*  95 */       this.fm = Math.exp(Arithmetic.logFactorial(paramInt1 - paramInt2) - Arithmetic.logFactorial(this.N_Mn + this.m) - Arithmetic.logFactorial(paramInt3 - this.m) + Arithmetic.logFactorial(paramInt2) - Arithmetic.logFactorial(paramInt2 - this.m) - Arithmetic.logFactorial(this.m) - Arithmetic.logFactorial(paramInt1) + Arithmetic.logFactorial(paramInt1 - paramInt3) + Arithmetic.logFactorial(paramInt3));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 101 */       this.b = ((int)(d2 + 11.0D * Math.sqrt(d2 * (1.0D - d1) * (1.0D - paramInt3 / paramInt1) + 1.0D)));
/* 102 */       if (this.b > paramInt3) this.b = paramInt3;
/*     */     }
/*     */     for (;;) {
/*     */       double d5;
/* 106 */       if ((d5 = paramRandomElement.raw() - this.fm) <= 0.0D) return this.m;
/* 107 */       double d4; double d3 = d4 = this.fm;
/*     */       
/*     */ 
/* 110 */       for (int i = 1; i <= this.m; i++) {
/* 111 */         j = this.mp - i;
/* 112 */         d3 *= j / (this.np - j) * ((this.N_Mn + j) / (this.Mp - j));
/* 113 */         if (d5 -= d3 <= 0.0D) { return j - 1;
/*     */         }
/* 115 */         j = this.m + i;
/* 116 */         d4 *= (this.np - j) / j * ((this.Mp - j) / (this.N_Mn + j));
/* 117 */         if (d5 -= d4 <= 0.0D) { return j;
/*     */         }
/*     */       }
/*     */       
/* 121 */       for (int j = this.mp + this.m; j <= this.b; j++) {
/* 122 */         d4 *= (this.np - j) / j * ((this.Mp - j) / (this.N_Mn + j));
/* 123 */         if (d5 -= d4 <= 0.0D) { return j;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected int hprs(int paramInt1, int paramInt2, int paramInt3, RandomElement paramRandomElement)
/*     */   {
/*     */     double d5;
/*     */     
/* 134 */     if ((paramInt1 != this.N_last) || (paramInt2 != this.M_last) || (paramInt3 != this.n_last)) {
/* 135 */       this.N_last = paramInt1;
/* 136 */       this.M_last = paramInt2;
/* 137 */       this.n_last = paramInt3;
/*     */       
/* 139 */       double d1 = paramInt2 + 1;
/* 140 */       double d2 = paramInt3 + 1;this.N_Mn = (paramInt1 - paramInt2 - paramInt3);
/*     */       
/* 142 */       double d3 = d1 / (paramInt1 + 2.0D);double d4 = d2 * d3;
/*     */       
/*     */ 
/* 145 */       d5 = Math.sqrt(d4 * (1.0D - d3) * (1.0D - (paramInt3 + 2.0D) / (paramInt1 + 3.0D)) + 0.25D);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */       this.m = ((int)d4);
/* 153 */       this.k2 = ((int)Math.ceil(d4 - 0.5D - d5)); if (this.k2 >= this.m) this.k2 = (this.m - 1);
/* 154 */       this.k4 = ((int)(d4 - 0.5D + d5));
/* 155 */       this.k1 = (this.k2 + this.k2 - this.m + 1);
/* 156 */       this.k5 = (this.k4 + this.k4 - this.m);
/*     */       
/*     */ 
/* 159 */       this.dl = (this.k2 - this.k1);
/* 160 */       this.dr = (this.k5 - this.k4);
/*     */       
/*     */ 
/* 163 */       this.r1 = ((d2 / this.k1 - 1.0D) * (d1 - this.k1) / (this.N_Mn + this.k1));
/* 164 */       this.r2 = ((d2 / this.k2 - 1.0D) * (d1 - this.k2) / (this.N_Mn + this.k2));
/* 165 */       this.r4 = ((d2 / (this.k4 + 1) - 1.0D) * (paramInt2 - this.k4) / (this.N_Mn + this.k4 + 1));
/* 166 */       this.r5 = ((d2 / (this.k5 + 1) - 1.0D) * (paramInt2 - this.k5) / (this.N_Mn + this.k5 + 1));
/*     */       
/*     */ 
/* 169 */       this.ll = Math.log(this.r1);
/* 170 */       this.lr = (-Math.log(this.r5));
/*     */       
/*     */ 
/* 173 */       this.c_pm = fc_lnpk(this.m, this.N_Mn, paramInt2, paramInt3);
/*     */       
/*     */ 
/* 176 */       this.f2 = Math.exp(this.c_pm - fc_lnpk(this.k2, this.N_Mn, paramInt2, paramInt3));
/* 177 */       this.f4 = Math.exp(this.c_pm - fc_lnpk(this.k4, this.N_Mn, paramInt2, paramInt3));
/* 178 */       this.f1 = Math.exp(this.c_pm - fc_lnpk(this.k1, this.N_Mn, paramInt2, paramInt3));
/* 179 */       this.f5 = Math.exp(this.c_pm - fc_lnpk(this.k5, this.N_Mn, paramInt2, paramInt3));
/*     */       
/*     */ 
/*     */ 
/* 183 */       this.p1 = (this.f2 * (this.dl + 1.0D));
/* 184 */       this.p2 = (this.f2 * this.dl + this.p1);
/* 185 */       this.p3 = (this.f4 * (this.dr + 1.0D) + this.p2);
/* 186 */       this.p4 = (this.f4 * this.dr + this.p3);
/* 187 */       this.p5 = (this.f1 / this.ll + this.p4);
/* 188 */       this.p6 = (this.f5 / this.lr + this.p5); }
/*     */     double d6;
/*     */     int j;
/*     */     label1265:
/*     */     do { int i;
/*     */       do { do { double d7;
/* 194 */           int k; if ((d5 = paramRandomElement.raw() * this.p6) < this.p2)
/*     */           {
/*     */ 
/* 197 */             if ((d7 = d5 - this.p1) < 0.0D) { return this.k2 + (int)(d5 / this.f2);
/*     */             }
/* 199 */             if ((d6 = d7 / this.dl) < this.f1) { return this.k1 + (int)(d7 / this.f1);
/*     */             }
/*     */             
/*     */ 
/* 203 */             i = (int)(this.dl * paramRandomElement.raw()) + 1;
/* 204 */             if (d6 <= this.f2 - i * (this.f2 - this.f2 / this.r2)) {
/* 205 */               return this.k2 - i;
/*     */             }
/* 207 */             if ((d7 = this.f2 + this.f2 - d6) < 1.0D) {
/* 208 */               k = this.k2 + i;
/* 209 */               if (d7 <= this.f2 + i * (1.0D - this.f2) / (this.dl + 1.0D)) {
/* 210 */                 return k;
/*     */               }
/* 212 */               if (Math.log(d7) <= this.c_pm - fc_lnpk(k, this.N_Mn, paramInt2, paramInt3)) {
/* 213 */                 return k;
/*     */               }
/*     */             }
/* 216 */             j = this.k2 - i;
/*     */             break label1265; }
/* 218 */           if (d5 < this.p4)
/*     */           {
/*     */ 
/* 221 */             if ((d7 = d5 - this.p3) < 0.0D) { return this.k4 - (int)((d5 - this.p2) / this.f4);
/*     */             }
/* 223 */             if ((d6 = d7 / this.dr) < this.f5) { return this.k5 - (int)(d7 / this.f5);
/*     */             }
/*     */             
/*     */ 
/* 227 */             i = (int)(this.dr * paramRandomElement.raw()) + 1;
/* 228 */             if (d6 <= this.f4 - i * (this.f4 - this.f4 * this.r4)) {
/* 229 */               return this.k4 + i;
/*     */             }
/* 231 */             if ((d7 = this.f4 + this.f4 - d6) < 1.0D) {
/* 232 */               k = this.k4 - i;
/* 233 */               if (d7 <= this.f4 + i * (1.0D - this.f4) / this.dr) {
/* 234 */                 return k;
/*     */               }
/* 236 */               if (Math.log(d7) <= this.c_pm - fc_lnpk(k, this.N_Mn, paramInt2, paramInt3)) {
/* 237 */                 return k;
/*     */               }
/*     */             }
/* 240 */             j = this.k4 + i;
/*     */             break label1265;
/*     */           }
/* 243 */           d6 = paramRandomElement.raw();
/* 244 */           if (d5 >= this.p5) break;
/* 245 */           i = (int)(1.0D - Math.log(d6) / this.ll);
/* 246 */         } while ((j = this.k1 - i) < 0);
/* 247 */         d6 *= (d5 - this.p4) * this.ll;
/* 248 */         if (d6 > this.f1 - i * (this.f1 - this.f1 / this.r1)) break;
/* 249 */         return j;
/*     */         
/*     */ 
/*     */ 
/* 253 */         i = (int)(1.0D - Math.log(d6) / this.lr);
/* 254 */       } while ((j = this.k5 + i) > paramInt3);
/* 255 */       d6 *= (d5 - this.p5) * this.lr;
/* 256 */       if (d6 <= this.f5 - i * (this.f5 - this.f5 * this.r5)) {
/* 257 */         return j;
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 267 */     while (Math.log(d6) > this.c_pm - fc_lnpk(j, this.N_Mn, paramInt2, paramInt3)); return j;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 274 */     return nextInt(this.my_N, this.my_s, this.my_n, this.randomGenerator);
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 280 */     return nextInt(paramInt1, paramInt2, paramInt3, this.randomGenerator);
/*     */   }
/*     */   
/*     */ 
/*     */   private double np;
/*     */   
/*     */   private double fm;
/*     */   
/*     */   private int k2;
/*     */   
/*     */   private int k4;
/*     */   
/*     */   private int k1;
/*     */   
/*     */   private int k5;
/*     */   
/*     */   private double dl;
/*     */   
/*     */   private double dr;
/*     */   
/*     */   private double r1;
/*     */   
/*     */   private double r2;
/*     */   
/*     */   private double r4;
/*     */   
/*     */   private double r5;
/*     */   
/*     */   private double ll;
/*     */   
/*     */   private double lr;
/*     */   
/*     */   private double c_pm;
/*     */   
/*     */   private double f1;
/*     */   
/*     */   private double f2;
/*     */   
/*     */   private double f4;
/*     */   
/*     */   private double f5;
/*     */   
/*     */   private double p1;
/*     */   private double p2;
/*     */   private double p3;
/*     */   private double p4;
/*     */   private double p5;
/*     */   private double p6;
/*     */   protected int nextInt(int paramInt1, int paramInt2, int paramInt3, RandomElement paramRandomElement)
/*     */   {
/* 330 */     int i = paramInt1 / 2;
/* 331 */     int j = paramInt3 <= i ? paramInt3 : paramInt1 - paramInt3;
/* 332 */     int k = paramInt2 <= i ? paramInt2 : paramInt1 - paramInt2;
/*     */     int n;
/* 334 */     if (paramInt3 * paramInt2 / paramInt1 < 10) {
/* 335 */       n = j <= k ? hmdu(paramInt1, k, j, paramRandomElement) : hmdu(paramInt1, j, k, paramRandomElement);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 340 */       n = j <= k ? hprs(paramInt1, k, j, paramRandomElement) : hprs(paramInt1, j, k, paramRandomElement);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 345 */     if (paramInt3 <= i) {
/* 346 */       return paramInt2 <= i ? n : paramInt3 - n;
/*     */     }
/*     */     
/* 349 */     return paramInt2 <= i ? paramInt2 - n : paramInt3 - paramInt1 + paramInt2 + n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 356 */     return Arithmetic.binomial(this.my_s, paramInt) * Arithmetic.binomial(this.my_N - this.my_s, this.my_n - paramInt) / Arithmetic.binomial(this.my_N, this.my_n);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setState(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 363 */     this.my_N = paramInt1;
/* 364 */     this.my_s = paramInt2;
/* 365 */     this.my_n = paramInt3;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextInt(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 371 */     synchronized (shared) {
/* 372 */       double d = shared.nextInt(paramInt1, paramInt2, paramInt3);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 379 */     return getClass().getName() + "(" + this.my_N + "," + this.my_s + "," + this.my_n + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 386 */     synchronized (shared) {
/* 387 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/HyperGeometric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */