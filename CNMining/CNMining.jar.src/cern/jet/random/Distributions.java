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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Distributions
/*     */ {
/*     */   protected Distributions()
/*     */   {
/*  44 */     throw new RuntimeException("Non instantiable");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double geometricPdf(int paramInt, double paramDouble)
/*     */   {
/*  55 */     if (paramInt < 0) throw new IllegalArgumentException();
/*  56 */     return paramDouble * Math.pow(1.0D - paramDouble, paramInt);
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
/*     */   public static double nextBurr1(double paramDouble, int paramInt, RandomElement paramRandomElement)
/*     */   {
/*  90 */     double d = Math.exp(Math.log(paramRandomElement.raw()) / paramDouble);
/*  91 */     switch (paramInt) {
/*     */     case 2: 
/*  93 */       return -Math.log(1.0D / d - 1.0D);
/*     */     
/*     */     case 7: 
/*  96 */       return Math.log(2.0D * d / (2.0D - 2.0D * d)) / 2.0D;
/*     */     
/*     */     case 8: 
/*  99 */       return Math.log(Math.tan(d * 3.141592653589793D / 2.0D));
/*     */     
/*     */     case 10: 
/* 102 */       return Math.sqrt(-Math.log(1.0D - d));
/*     */     }
/* 104 */     return 0.0D;
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
/*     */   public static double nextBurr2(double paramDouble1, double paramDouble2, int paramInt, RandomElement paramRandomElement)
/*     */   {
/* 138 */     double d2 = paramRandomElement.raw();
/* 139 */     double d1 = Math.exp(-Math.log(d2) / paramDouble1) - 1.0D;
/* 140 */     switch (paramInt) {
/*     */     case 3: 
/* 142 */       return Math.exp(-Math.log(d1) / paramDouble2);
/*     */     
/*     */     case 4: 
/* 145 */       d1 = Math.exp(paramDouble2 * Math.log(d1)) + 1.0D;
/* 146 */       d1 = paramDouble2 / d1;
/* 147 */       return d1;
/*     */     
/*     */     case 5: 
/* 150 */       d1 = Math.atan(-Math.log(d1 / paramDouble2));
/* 151 */       return d1;
/*     */     
/*     */     case 6: 
/* 154 */       d1 = -Math.log(d1 / paramDouble2) / paramDouble1;
/* 155 */       d1 = Math.log(d1 + Math.sqrt(d1 * d1 + 1.0D));
/* 156 */       return d1;
/*     */     
/*     */     case 9: 
/* 159 */       d1 = 1.0D + 2.0D * d2 / (paramDouble2 * (1.0D - d2));
/* 160 */       d1 = Math.exp(Math.log(d1) / paramDouble1) - 1.0D;
/* 161 */       return Math.log(d1);
/*     */     
/*     */     case 12: 
/* 164 */       return Math.exp(Math.log(d1) / paramDouble2);
/*     */     }
/* 166 */     return 0.0D;
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
/*     */   public static double nextCauchy(RandomElement paramRandomElement)
/*     */   {
/* 181 */     return Math.tan(3.141592653589793D * paramRandomElement.raw());
/*     */   }
/*     */   
/*     */ 
/*     */   public static double nextErlang(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 187 */     int i = (int)(paramDouble2 * paramDouble2 / paramDouble1 + 0.5D);
/* 188 */     i = i > 0 ? i : 1;
/* 189 */     double d1 = i / paramDouble2;
/*     */     
/* 191 */     double d2 = 1.0D;
/* 192 */     for (int j = 0; j < i; j++) d2 *= paramRandomElement.raw();
/* 193 */     return -Math.log(d2) / d1;
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
/*     */   public static int nextGeometric(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/* 232 */     double d = paramRandomElement.raw();
/* 233 */     return (int)(Math.log(d) / Math.log(1.0D - paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double nextLambda(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*     */     double d1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 247 */     if ((paramDouble1 < 0.0D) || (paramDouble2 < 0.0D)) d1 = -1.0D; else {
/* 248 */       d1 = 1.0D;
/*     */     }
/* 250 */     double d2 = paramRandomElement.raw();
/* 251 */     double d3 = d1 * (Math.exp(Math.log(d2) * paramDouble1) - Math.exp(Math.log(1.0D - d2) * paramDouble2));
/* 252 */     return d3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double nextLaplace(RandomElement paramRandomElement)
/*     */   {
/* 263 */     double d = paramRandomElement.raw();
/* 264 */     d = d + d - 1.0D;
/* 265 */     if (d > 0.0D) return -Math.log(1.0D - d);
/* 266 */     return Math.log(1.0D + d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double nextLogistic(RandomElement paramRandomElement)
/*     */   {
/* 275 */     double d = paramRandomElement.raw();
/* 276 */     return -Math.log(1.0D / d - 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double nextPowLaw(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 284 */     return paramRandomElement.powlaw(paramDouble1, paramDouble2);
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
/*     */   public static double nextTriangular(RandomElement paramRandomElement)
/*     */   {
/* 308 */     double d = paramRandomElement.raw();
/* 309 */     if (d <= 0.5D) return Math.sqrt(2.0D * d) - 1.0D;
/* 310 */     return 1.0D - Math.sqrt(2.0D * (1.0D - d));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double nextWeibull(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 320 */     return Math.pow(paramDouble2 * -Math.log(1.0D - paramRandomElement.raw()), 1.0D / paramDouble1);
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
/*     */   public static int nextZipfInt(double paramDouble, RandomElement paramRandomElement)
/*     */   {
/* 337 */     double d1 = Math.pow(2.0D, paramDouble - 1.0D);
/* 338 */     double d2 = -1.0D / (paramDouble - 1.0D);
/*     */     
/* 340 */     int i = 0;
/*     */     for (;;) {
/* 342 */       double d3 = paramRandomElement.raw();
/* 343 */       double d4 = paramRandomElement.raw();
/* 344 */       i = (int)Math.floor(Math.pow(d3, d2));
/* 345 */       double d5 = Math.pow(1.0D + 1.0D / i, paramDouble - 1.0D);
/* 346 */       if (d4 * i * (d5 - 1.0D) / (d1 - 1.0D) <= d5 / d1) break;
/*     */     }
/* 348 */     return i;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Distributions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */