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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gamma
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double alpha;
/*     */   protected double lambda;
/*  55 */   protected static Gamma shared = new Gamma(1.0D, 1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Gamma(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*  62 */     setRandomGenerator(paramRandomElement);
/*  63 */     setState(paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/*  69 */     return Probability.gamma(this.alpha, this.lambda, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  75 */     return nextDouble(this.alpha, this.lambda);
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
/*     */   public double nextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 103 */     double d1 = paramDouble1;
/* 104 */     double d2 = -1.0D;double d3 = -1.0D;
/* 105 */     double d4 = 0.0D;double d5 = 0.0D;double d6 = 0.0D;double d9 = 0.0D;double d10 = 0.0D;double d11 = 0.0D;double d12 = 0.0D;
/* 106 */     double d13 = 0.0416666664D;double d14 = 0.0208333723D;double d15 = 0.0079849875D;
/* 107 */     double d16 = 0.0015746717D;double d17 = -3.349403E-4D;double d18 = 3.340332E-4D;
/* 108 */     double d19 = 6.053049E-4D;double d20 = -4.701849E-4D;double d21 = 1.71032E-4D;
/* 109 */     double d22 = 0.333333333D;double d23 = -0.249999949D;double d24 = 0.199999867D;
/* 110 */     double d25 = -0.166677482D;double d26 = 0.142873973D;double d27 = -0.124385581D;
/* 111 */     double d28 = 0.11036831D;double d29 = -0.112750886D;double d30 = 0.104089866D;
/* 112 */     double d31 = 1.0D;double d32 = 0.499999994D;double d33 = 0.166666848D;
/* 113 */     double d34 = 0.041664508D;double d35 = 0.008345522D;double d36 = 0.001353826D;
/* 114 */     double d37 = 2.47453E-4D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     if (d1 <= 0.0D) throw new IllegalArgumentException();
/* 122 */     if (paramDouble2 <= 0.0D) { new IllegalArgumentException();
/*     */     }
/* 124 */     if (d1 < 1.0D) {
/* 125 */       d4 = 1.0D + 0.36788794412D * d1;
/*     */       do { double d39;
/* 127 */         do { d39 = d4 * this.randomGenerator.raw();
/* 128 */           if (d39 > 1.0D) break;
/* 129 */           d38 = Math.exp(Math.log(d39) / d1);
/* 130 */         } while (Math.log(this.randomGenerator.raw()) > -d38); return d38 / paramDouble2;
/*     */         
/*     */ 
/* 133 */         d38 = -Math.log((d4 - d39) / d1);
/* 134 */       } while (Math.log(this.randomGenerator.raw()) > (d1 - 1.0D) * Math.log(d38)); return d38 / paramDouble2;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 140 */     if (d1 != d2) {
/* 141 */       d2 = d1;
/* 142 */       d11 = d1 - 0.5D;
/* 143 */       d9 = Math.sqrt(d11);
/* 144 */       d6 = 5.656854249D - 12.0D * d9;
/*     */     }
/*     */     double d47;
/*     */     double d49;
/* 148 */     do { d47 = 2.0D * this.randomGenerator.raw() - 1.0D;
/* 149 */       double d48 = 2.0D * this.randomGenerator.raw() - 1.0D;
/* 150 */       d49 = d47 * d47 + d48 * d48;
/* 151 */     } while (d49 > 1.0D);
/* 152 */     double d41 = d47 * Math.sqrt(-2.0D * Math.log(d49) / d49);
/* 153 */     double d46 = d9 + 0.5D * d41;
/* 154 */     double d38 = d46 * d46;
/* 155 */     if (d41 >= 0.0D) { return d38 / paramDouble2;
/*     */     }
/* 157 */     double d43 = this.randomGenerator.raw();
/* 158 */     if (d6 * d43 <= d41 * d41 * d41) { return d38 / paramDouble2;
/*     */     }
/* 160 */     if (d1 != d3) {
/* 161 */       d3 = d1;
/* 162 */       double d8 = 1.0D / d1;
/* 163 */       d12 = ((((((((d21 * d8 + d20) * d8 + d19) * d8 + d18) * d8 + d17) * d8 + d16) * d8 + d15) * d8 + d14) * d8 + d13) * d8;
/*     */       
/* 165 */       if (d1 > 3.686D) {
/* 166 */         if (d1 > 13.022D) {
/* 167 */           d4 = 1.77D;
/* 168 */           d10 = 0.75D;
/* 169 */           d5 = 0.1515D / d9;
/*     */         }
/*     */         else {
/* 172 */           d4 = 1.654D + 0.0076D * d11;
/* 173 */           d10 = 1.68D / d9 + 0.275D;
/* 174 */           d5 = 0.062D / d9 + 0.024D;
/*     */         }
/*     */       }
/*     */       else {
/* 178 */         d4 = 0.463D + d9 - 0.178D * d11;
/* 179 */         d10 = 1.235D;
/* 180 */         d5 = 0.195D / d9 - 0.079D + 0.016D * d9; } }
/*     */     double d44;
/*     */     double d40;
/* 183 */     if (d46 > 0.0D) {
/* 184 */       d44 = d41 / (d9 + d9);
/* 185 */       if (Math.abs(d44) > 0.25D) {
/* 186 */         d40 = d12 - d9 * d41 + 0.25D * d41 * d41 + (d11 + d11) * Math.log(1.0D + d44);
/*     */       }
/*     */       else {
/* 189 */         d40 = d12 + 0.5D * d41 * d41 * ((((((((d30 * d44 + d29) * d44 + d28) * d44 + d27) * d44 + d26) * d44 + d25) * d44 + d24) * d44 + d23) * d44 + d22) * d44;
/*     */       }
/*     */       
/* 192 */       if (Math.log(1.0D - d43) <= d40) return d38 / paramDouble2;
/*     */     }
/*     */     double d7;
/*     */     double d42;
/*     */     double d45;
/* 197 */     do { do { do { d7 = -Math.log(this.randomGenerator.raw());
/* 198 */           d43 = this.randomGenerator.raw();
/* 199 */           d43 = d43 + d43 - 1.0D;
/* 200 */           d42 = d43 > 0.0D ? 1.0D : -1.0D;
/* 201 */           d41 = d4 + d7 * d10 * d42;
/* 202 */         } while (d41 <= -0.71874483771719D);
/* 203 */         d44 = d41 / (d9 + d9);
/* 204 */         if (Math.abs(d44) > 0.25D) {
/* 205 */           d40 = d12 - d9 * d41 + 0.25D * d41 * d41 + (d11 + d11) * Math.log(1.0D + d44);
/*     */         }
/*     */         else {
/* 208 */           d40 = d12 + 0.5D * d41 * d41 * ((((((((d30 * d44 + d29) * d44 + d28) * d44 + d27) * d44 + d26) * d44 + d25) * d44 + d24) * d44 + d23) * d44 + d22) * d44;
/*     */         }
/*     */         
/* 211 */       } while (d40 <= 0.0D);
/* 212 */       if (d40 > 0.5D) {
/* 213 */         d45 = Math.exp(d40) - 1.0D;
/*     */       }
/*     */       else {
/* 216 */         d45 = ((((((d37 * d40 + d36) * d40 + d35) * d40 + d34) * d40 + d33) * d40 + d32) * d40 + d31) * d40;
/*     */       }
/*     */       
/* 219 */     } while (d5 * d43 * d42 > d45 * Math.exp(d7 - 0.5D * d41 * d41));
/* 220 */     d46 = d9 + 0.5D * d41;
/* 221 */     return d46 * d46 / paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/* 230 */     if (paramDouble < 0.0D) throw new IllegalArgumentException();
/* 231 */     if (paramDouble == 0.0D) {
/* 232 */       if (this.alpha == 1.0D) return 1.0D / this.lambda;
/* 233 */       return 0.0D;
/*     */     }
/* 235 */     if (this.alpha == 1.0D) { return Math.exp(-paramDouble / this.lambda) / this.lambda;
/*     */     }
/* 237 */     return Math.exp((this.alpha - 1.0D) * Math.log(paramDouble / this.lambda) - paramDouble / this.lambda - Fun.logGamma(this.alpha)) / this.lambda;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 244 */     if (paramDouble1 <= 0.0D) throw new IllegalArgumentException();
/* 245 */     if (paramDouble2 <= 0.0D) throw new IllegalArgumentException();
/* 246 */     this.alpha = paramDouble1;
/* 247 */     this.lambda = paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double staticNextDouble(double paramDouble1, double paramDouble2)
/*     */   {
/* 254 */     synchronized (shared) {
/* 255 */       double d = shared.nextDouble(paramDouble1, paramDouble2);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 262 */     return getClass().getName() + "(" + this.alpha + "," + this.lambda + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 269 */     synchronized (shared) {
/* 270 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Gamma.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */