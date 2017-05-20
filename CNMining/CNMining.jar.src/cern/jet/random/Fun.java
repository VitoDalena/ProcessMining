/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.jet.math.Arithmetic;
/*     */ import com.imsl.math.Sfun;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Fun
/*     */ {
/*     */   protected Fun()
/*     */   {
/*  25 */     throw new RuntimeException("Non instantiable");
/*     */   }
/*     */   
/*     */   private static double _fkt_value(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
/*     */   {
/*  30 */     double d = Math.cos(paramDouble2 * paramDouble4) / Math.pow(paramDouble4 * paramDouble4 + paramDouble3 * paramDouble3, paramDouble1 + 0.5D);
/*  31 */     return d;
/*     */   }
/*     */   
/*     */   public static double bessel2_fkt(double paramDouble1, double paramDouble2)
/*     */   {
/*  36 */     double d7 = 0.01D;
/*     */     
/*     */ 
/*  39 */     double d19 = 0.0D;
/*     */     
/*     */ 
/*  42 */     double[] arrayOfDouble1 = { -1.5787132D, -0.6130827D, 0.1735823D, 1.4793411D, 2.6667307D, 4.9086836D, 8.1355339D };
/*     */     
/*     */ 
/*  45 */     double[] arrayOfDouble2 = { -1.9694802D, -0.7642538D, 0.0826017D, 1.4276355D, 2.6303682D, 4.8857787D, 8.1207968D };
/*     */     
/*     */ 
/*  48 */     double[] arrayOfDouble3 = { -2.9807345D, -1.1969943D, -0.1843161D, 1.2739241D, 2.5218256D, 4.8172216D, 8.0765633D };
/*     */     
/*     */ 
/*  51 */     double[] arrayOfDouble4 = { -5.9889676D, -2.7145389D, -1.1781269D, 0.6782201D, 2.0954009D, 4.5452152D, 7.9003173D };
/*     */     
/*     */ 
/*  54 */     double[] arrayOfDouble5 = { -9.680344D, -4.8211925D, -2.6533185D, -0.2583337D, 1.4091915D, 4.0993448D, 7.608831D };
/*     */     
/*     */ 
/*  57 */     double[] arrayOfDouble6 = { -18.1567152D, -10.0939408D, -6.5819139D, -2.9371545D, -0.6289005D, 2.7270412D, 6.6936799D };
/*     */     
/*     */ 
/*  60 */     double[] arrayOfDouble7 = { -32.4910195D, -19.6065943D, -14.0347298D, -8.3839439D, -4.967973D, -0.3567823D, 4.5589697D };
/*     */     
/*     */ 
/*     */ 
/*  64 */     if (paramDouble1 == 0.0D) {
/*  65 */       if (paramDouble2 == 0.1D) return arrayOfDouble1[0];
/*  66 */       if (paramDouble2 == 0.5D) return arrayOfDouble1[1];
/*  67 */       if (paramDouble2 == 1.0D) return arrayOfDouble1[2];
/*  68 */       if (paramDouble2 == 2.0D) return arrayOfDouble1[3];
/*  69 */       if (paramDouble2 == 3.0D) return arrayOfDouble1[4];
/*  70 */       if (paramDouble2 == 5.0D) return arrayOfDouble1[5];
/*  71 */       if (paramDouble2 == 8.0D) { return arrayOfDouble1[6];
/*     */       }
/*     */     }
/*  74 */     if (paramDouble1 == 0.5D) {
/*  75 */       if (paramDouble2 == 0.1D) return arrayOfDouble2[0];
/*  76 */       if (paramDouble2 == 0.5D) return arrayOfDouble2[1];
/*  77 */       if (paramDouble2 == 1.0D) return arrayOfDouble2[2];
/*  78 */       if (paramDouble2 == 2.0D) return arrayOfDouble2[3];
/*  79 */       if (paramDouble2 == 3.0D) return arrayOfDouble2[4];
/*  80 */       if (paramDouble2 == 5.0D) return arrayOfDouble2[5];
/*  81 */       if (paramDouble2 == 8.0D) { return arrayOfDouble2[6];
/*     */       }
/*     */     }
/*  84 */     if (paramDouble1 == 1.0D) {
/*  85 */       if (paramDouble2 == 0.1D) return arrayOfDouble3[0];
/*  86 */       if (paramDouble2 == 0.5D) return arrayOfDouble3[1];
/*  87 */       if (paramDouble2 == 1.0D) return arrayOfDouble3[2];
/*  88 */       if (paramDouble2 == 2.0D) return arrayOfDouble3[3];
/*  89 */       if (paramDouble2 == 3.0D) return arrayOfDouble3[4];
/*  90 */       if (paramDouble2 == 5.0D) return arrayOfDouble3[5];
/*  91 */       if (paramDouble2 == 8.0D) { return arrayOfDouble3[6];
/*     */       }
/*     */     }
/*  94 */     if (paramDouble1 == 2.0D) {
/*  95 */       if (paramDouble2 == 0.1D) return arrayOfDouble4[0];
/*  96 */       if (paramDouble2 == 0.5D) return arrayOfDouble4[1];
/*  97 */       if (paramDouble2 == 1.0D) return arrayOfDouble4[2];
/*  98 */       if (paramDouble2 == 2.0D) return arrayOfDouble4[3];
/*  99 */       if (paramDouble2 == 3.0D) return arrayOfDouble4[4];
/* 100 */       if (paramDouble2 == 5.0D) return arrayOfDouble4[5];
/* 101 */       if (paramDouble2 == 8.0D) { return arrayOfDouble4[6];
/*     */       }
/*     */     }
/* 104 */     if (paramDouble1 == 3.0D) {
/* 105 */       if (paramDouble2 == 0.1D) return arrayOfDouble5[0];
/* 106 */       if (paramDouble2 == 0.5D) return arrayOfDouble5[1];
/* 107 */       if (paramDouble2 == 1.0D) return arrayOfDouble5[2];
/* 108 */       if (paramDouble2 == 2.0D) return arrayOfDouble5[3];
/* 109 */       if (paramDouble2 == 3.0D) return arrayOfDouble5[4];
/* 110 */       if (paramDouble2 == 5.0D) return arrayOfDouble5[5];
/* 111 */       if (paramDouble2 == 8.0D) { return arrayOfDouble5[6];
/*     */       }
/*     */     }
/* 114 */     if (paramDouble1 == 5.0D) {
/* 115 */       if (paramDouble2 == 0.1D) return arrayOfDouble6[0];
/* 116 */       if (paramDouble2 == 0.5D) return arrayOfDouble6[1];
/* 117 */       if (paramDouble2 == 1.0D) return arrayOfDouble6[2];
/* 118 */       if (paramDouble2 == 2.0D) return arrayOfDouble6[3];
/* 119 */       if (paramDouble2 == 3.0D) return arrayOfDouble6[4];
/* 120 */       if (paramDouble2 == 5.0D) return arrayOfDouble6[5];
/* 121 */       if (paramDouble2 == 8.0D) { return arrayOfDouble6[6];
/*     */       }
/*     */     }
/* 124 */     if (paramDouble1 == 8.0D) {
/* 125 */       if (paramDouble2 == 0.1D) return arrayOfDouble7[0];
/* 126 */       if (paramDouble2 == 0.5D) return arrayOfDouble7[1];
/* 127 */       if (paramDouble2 == 1.0D) return arrayOfDouble7[2];
/* 128 */       if (paramDouble2 == 2.0D) return arrayOfDouble7[3];
/* 129 */       if (paramDouble2 == 3.0D) return arrayOfDouble7[4];
/* 130 */       if (paramDouble2 == 5.0D) return arrayOfDouble7[5];
/* 131 */       if (paramDouble2 == 8.0D) { return arrayOfDouble7[6];
/*     */       }
/*     */     }
/*     */     
/* 135 */     if (paramDouble2 - 5.0D * paramDouble1 - 8.0D >= 0.0D) {
/* 136 */       double d17 = 4.0D * paramDouble1 * paramDouble1;
/* 137 */       double d18 = -0.9189385D + 0.5D * Math.log(paramDouble2) + paramDouble2;
/* 138 */       d1 = 1.0D;
/* 139 */       double d21 = 1.0D;
/* 140 */       double d20 = 8.0D;
/* 141 */       i = 1;
/*     */       
/* 143 */       while ((factorial(i) * Math.pow(8.0D * paramDouble2, i) <= 1.0E250D) && 
/* 144 */         (i <= 10)) {
/* 145 */         if (i == 1) { d19 = d17 - 1.0D;
/*     */         } else {
/* 147 */           d21 += d20;
/* 148 */           d19 *= (d17 - d21);
/* 149 */           d20 *= 2.0D;
/*     */         }
/* 151 */         d1 += d19 / (factorial(i) * Math.pow(8.0D * paramDouble2, i));
/* 152 */         i++;
/*     */       }
/* 154 */       d12 = d18 - Math.log(d1);
/* 155 */       return d12;
/*     */     }
/*     */     
/* 158 */     if ((paramDouble1 > 0.0D) && (paramDouble2 - 0.04D * paramDouble1 <= 0.0D)) {
/* 159 */       if (paramDouble1 < 11.5D) {
/* 160 */         d12 = -Math.log(gamma(paramDouble1)) - paramDouble1 * Math.log(2.0D) + paramDouble1 * Math.log(paramDouble2);
/* 161 */         return d12;
/*     */       }
/*     */       
/* 164 */       d12 = -(paramDouble1 + 1.0D) * Math.log(2.0D) - (paramDouble1 - 0.5D) * Math.log(paramDouble1) + paramDouble1 + paramDouble1 * Math.log(paramDouble2) - 0.5D * Math.log(1.5707963267948966D);
/* 165 */       return d12;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 172 */     double d2 = 0.0D;
/*     */     double d6;
/* 174 */     if (paramDouble2 < 1.57D) {
/* 175 */       double d9 = fkt2_value(paramDouble1, paramDouble2, d2) * 0.01D;
/* 176 */       double d8 = 0.0D;
/*     */       for (;;) {
/* 178 */         d8 += 0.1D;
/* 179 */         if (fkt2_value(paramDouble1, paramDouble2, d8) < d9) break;
/*     */       }
/* 181 */       d3 = d8 * 0.001D;
/* 182 */       d4 = d3;
/* 183 */       d1 = 0.5D * (10.0D * d3 + fkt2_value(paramDouble1, paramDouble2, d4)) * d3;
/* 184 */       double d5 = d1;
/*     */       for (;;) {
/* 186 */         d2 = d4;
/* 187 */         d4 += d3;
/* 188 */         d6 = 0.5D * (fkt2_value(paramDouble1, paramDouble2, d2) + fkt2_value(paramDouble1, paramDouble2, d4)) * d3;
/* 189 */         d1 += d6;
/* 190 */         if (d6 / d5 < d7) break;
/*     */       }
/* 192 */       d12 = -Math.log(2.0D * d1);
/* 193 */       return d12;
/*     */     }
/*     */     
/* 196 */     double d11 = 1.57D;
/* 197 */     double d10 = paramDouble2 / 1.57D;
/* 198 */     double d1 = 0.0D;
/* 199 */     double d13 = 3.141592653589793D / d10;
/* 200 */     double d3 = 0.1D * d13;
/* 201 */     double d14 = 100.0D / ((paramDouble1 + 0.1D) * (paramDouble1 + 0.1D));
/* 202 */     int k = (int)Math.ceil(d14 / d13) + 20;
/* 203 */     double d4 = d3;
/* 204 */     for (int i = 1; i <= k; i++) {
/* 205 */       for (j = 1; j <= 10; j++) {
/* 206 */         d6 = 0.5D * (_fkt_value(paramDouble1, d10, d11, d2) + _fkt_value(paramDouble1, d10, d11, d4)) * d3;
/* 207 */         d1 += d6;
/* 208 */         d2 = d4;
/* 209 */         d4 += d3;
/*     */       }
/*     */     }
/* 212 */     for (int j = 1; j <= 5; j++) {
/* 213 */       d6 = 0.5D * (_fkt_value(paramDouble1, d10, d11, d2) + _fkt_value(paramDouble1, d10, d11, d4)) * d3;
/* 214 */       d1 += d6;
/* 215 */       d2 = d4;
/* 216 */       d4 += d3;
/*     */     }
/* 218 */     double d15 = d1;
/* 219 */     for (j = 1; j <= 10; j++) {
/* 220 */       d6 = 0.5D * (_fkt_value(paramDouble1, d10, d11, d2) + _fkt_value(paramDouble1, d10, d11, d4)) * d3;
/* 221 */       d1 += d6;
/* 222 */       d2 = d4;
/* 223 */       d4 += d3;
/*     */     }
/* 225 */     double d16 = d1;
/* 226 */     d1 = 0.5D * (d15 + d16);
/* 227 */     double d12 = gamma(paramDouble1 + 0.5D) * Math.pow(2.0D * d11, paramDouble1) / (Math.sqrt(3.141592653589793D) * Math.pow(d10, paramDouble1)) * d1;
/* 228 */     d12 = -Math.log(2.0D * d12);
/* 229 */     return d12;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double bessi0(double paramDouble)
/*     */   {
/*     */     double d1;
/*     */     
/*     */     double d3;
/*     */     double d2;
/* 239 */     if ((d1 = Math.abs(paramDouble)) < 3.75D) {
/* 240 */       d3 = paramDouble / 3.75D;
/* 241 */       d3 *= d3;
/* 242 */       d2 = 1.0D + d3 * (3.5156229D + d3 * (3.0899424D + d3 * (1.2067492D + d3 * (0.2659732D + d3 * (0.0360768D + d3 * 0.0045813D)))));
/*     */     }
/*     */     else
/*     */     {
/* 246 */       d3 = 3.75D / d1;
/* 247 */       d2 = Math.exp(d1) / Math.sqrt(d1) * (0.39894228D + d3 * (0.01328592D + d3 * (0.00225319D + d3 * (-0.00157565D + d3 * (0.00916281D + d3 * (-0.02057706D + d3 * (0.02635537D + d3 * (-0.01647633D + d3 * 0.00392377D))))))));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 252 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double bessi1(double paramDouble)
/*     */   {
/*     */     double d1;
/*     */     double d3;
/*     */     double d2;
/* 261 */     if ((d1 = Math.abs(paramDouble)) < 3.75D) {
/* 262 */       d3 = paramDouble / 3.75D;
/* 263 */       d3 *= d3;
/* 264 */       d2 = d1 * (0.5D + d3 * (0.87890594D + d3 * (0.51498869D + d3 * (0.15084934D + d3 * (0.02658733D + d3 * (0.00301532D + d3 * 3.2411E-4D))))));
/*     */     }
/*     */     else
/*     */     {
/* 268 */       d3 = 3.75D / d1;
/* 269 */       d2 = 0.02282967D + d3 * (-0.02895312D + d3 * (0.01787654D - d3 * 0.00420059D));
/*     */       
/* 271 */       d2 = 0.39894228D + d3 * (-0.03988024D + d3 * (-0.00362018D + d3 * (0.00163801D + d3 * (-0.01031555D + d3 * d2))));
/*     */       
/* 273 */       d2 *= Math.exp(d1) / Math.sqrt(d1);
/*     */     }
/* 275 */     return paramDouble < 0.0D ? -d2 : d2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static long factorial(int paramInt)
/*     */   {
/* 281 */     return Arithmetic.longFactorial(paramInt);
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
/*     */   private static double fkt2_value(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 295 */     double d = Sfun.cosh(paramDouble1 * paramDouble3) * Math.exp(-paramDouble2 * Sfun.cosh(paramDouble3));
/* 296 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double gamma(double paramDouble)
/*     */   {
/* 302 */     paramDouble = logGamma(paramDouble);
/*     */     
/* 304 */     return Math.exp(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logGamma(double paramDouble)
/*     */   {
/* 316 */     if (paramDouble <= 0.0D) { return -999.0D;
/*     */     }
/* 318 */     for (double d3 = 1.0D; paramDouble < 11.0D; paramDouble += 1.0D) { d3 *= paramDouble;
/*     */     }
/* 320 */     double d2 = 1.0D / (paramDouble * paramDouble);
/* 321 */     double d1 = 0.08333333333333333D + d2 * (-0.002777777777777778D + d2 * (7.936507936507937E-4D + d2 * (-5.952380952380953E-4D + d2 * (8.417508417508417E-4D + d2 + -0.0019175269175269174D))));
/* 322 */     d1 = (paramDouble - 0.5D) * Math.log(paramDouble) - paramDouble + 0.9189385332046728D + d1 / paramDouble;
/* 323 */     if (d3 == 1.0D) return d1;
/* 324 */     return d1 - Math.log(d3);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Fun.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */