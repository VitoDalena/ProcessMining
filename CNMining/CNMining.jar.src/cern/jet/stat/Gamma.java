/*     */ package cern.jet.stat;
/*     */ 
/*     */ import cern.jet.math.Constants;
/*     */ import cern.jet.math.Polynomial;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends Constants
/*     */ {
/*     */   public static double beta(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/*  42 */     double d = paramDouble1 + paramDouble2;
/*  43 */     d = gamma(d);
/*  44 */     if (d == 0.0D) { return 1.0D;
/*     */     }
/*  46 */     if (paramDouble1 > paramDouble2) {
/*  47 */       d = gamma(paramDouble1) / d;
/*  48 */       d *= gamma(paramDouble2);
/*     */     }
/*     */     else {
/*  51 */       d = gamma(paramDouble2) / d;
/*  52 */       d *= gamma(paramDouble1);
/*     */     }
/*     */     
/*  55 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double gamma(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/*  62 */     double[] arrayOfDouble1 = { 1.6011952247675185E-4D, 0.0011913514700658638D, 0.010421379756176158D, 0.04763678004571372D, 0.20744822764843598D, 0.4942148268014971D, 1.0D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  71 */     double[] arrayOfDouble2 = { -2.3158187332412014E-5D, 5.396055804933034E-4D, -0.004456419138517973D, 0.011813978522206043D, 0.035823639860549865D, -0.23459179571824335D, 0.0714304917030273D, 1.0D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  87 */     double d3 = Math.abs(paramDouble);
/*     */     
/*  89 */     if (d3 > 33.0D) {
/*  90 */       if (paramDouble < 0.0D) {
/*  91 */         d1 = Math.floor(d3);
/*  92 */         if (d1 == d3) throw new ArithmeticException("gamma: overflow");
/*  93 */         int i = (int)d1;
/*  94 */         d2 = d3 - d1;
/*  95 */         if (d2 > 0.5D) {
/*  96 */           d1 += 1.0D;
/*  97 */           d2 = d3 - d1;
/*     */         }
/*  99 */         d2 = d3 * Math.sin(3.141592653589793D * d2);
/* 100 */         if (d2 == 0.0D) throw new ArithmeticException("gamma: overflow");
/* 101 */         d2 = Math.abs(d2);
/* 102 */         d2 = 3.141592653589793D / (d2 * stirlingFormula(d3));
/*     */         
/* 104 */         return -d2;
/*     */       }
/* 106 */       return stirlingFormula(paramDouble);
/*     */     }
/*     */     
/*     */ 
/* 110 */     double d2 = 1.0D;
/* 111 */     while (paramDouble >= 3.0D) {
/* 112 */       paramDouble -= 1.0D;
/* 113 */       d2 *= paramDouble;
/*     */     }
/*     */     
/* 116 */     while (paramDouble < 0.0D) {
/* 117 */       if (paramDouble == 0.0D) {
/* 118 */         throw new ArithmeticException("gamma: singular");
/*     */       }
/* 120 */       if (paramDouble > -1.0E-9D) {
/* 121 */         return d2 / ((1.0D + 0.5772156649015329D * paramDouble) * paramDouble);
/*     */       }
/* 123 */       d2 /= paramDouble;
/* 124 */       paramDouble += 1.0D;
/*     */     }
/*     */     
/* 127 */     while (paramDouble < 2.0D) {
/* 128 */       if (paramDouble == 0.0D) {
/* 129 */         throw new ArithmeticException("gamma: singular");
/*     */       }
/* 131 */       if (paramDouble < 1.0E-9D) {
/* 132 */         return d2 / ((1.0D + 0.5772156649015329D * paramDouble) * paramDouble);
/*     */       }
/* 134 */       d2 /= paramDouble;
/* 135 */       paramDouble += 1.0D;
/*     */     }
/*     */     
/* 138 */     if ((paramDouble == 2.0D) || (paramDouble == 3.0D)) { return d2;
/*     */     }
/* 140 */     paramDouble -= 2.0D;
/* 141 */     double d1 = Polynomial.polevl(paramDouble, arrayOfDouble1, 6);
/* 142 */     d3 = Polynomial.polevl(paramDouble, arrayOfDouble2, 7);
/* 143 */     return d2 * d1 / d3;
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
/*     */   public static double incompleteBeta(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     throws ArithmeticException
/*     */   {
/* 157 */     if ((paramDouble1 <= 0.0D) || (paramDouble2 <= 0.0D)) { throw new ArithmeticException("ibeta: Domain error!");
/*     */     }
/*     */     
/* 160 */     if ((paramDouble3 <= 0.0D) || (paramDouble3 >= 1.0D)) {
/* 161 */       if (paramDouble3 == 0.0D) return 0.0D;
/* 162 */       if (paramDouble3 == 1.0D) return 1.0D;
/* 163 */       throw new ArithmeticException("ibeta: Domain error!");
/*     */     }
/*     */     
/* 166 */     int i = 0;
/* 167 */     if ((paramDouble2 * paramDouble3 <= 1.0D) && (paramDouble3 <= 0.95D)) {
/* 168 */       d3 = powerSeries(paramDouble1, paramDouble2, paramDouble3);
/* 169 */       return d3;
/*     */     }
/*     */     
/* 172 */     double d6 = 1.0D - paramDouble3;
/*     */     double d1;
/*     */     double d2;
/* 175 */     double d5; double d4; if (paramDouble3 > paramDouble1 / (paramDouble1 + paramDouble2)) {
/* 176 */       i = 1;
/* 177 */       d1 = paramDouble2;
/* 178 */       d2 = paramDouble1;
/* 179 */       d5 = paramDouble3;
/* 180 */       d4 = d6;
/*     */     } else {
/* 182 */       d1 = paramDouble1;
/* 183 */       d2 = paramDouble2;
/* 184 */       d5 = d6;
/* 185 */       d4 = paramDouble3;
/*     */     }
/*     */     
/* 188 */     if ((i != 0) && (d2 * d4 <= 1.0D) && (d4 <= 0.95D)) {
/* 189 */       d3 = powerSeries(d1, d2, d4);
/* 190 */       if (d3 <= 1.1102230246251565E-16D) d3 = 0.9999999999999999D; else
/* 191 */         d3 = 1.0D - d3;
/* 192 */       return d3;
/*     */     }
/*     */     
/*     */ 
/* 196 */     double d7 = d4 * (d1 + d2 - 2.0D) - (d1 - 1.0D);
/* 197 */     if (d7 < 0.0D) {
/* 198 */       d6 = incompleteBetaFraction1(d1, d2, d4);
/*     */     } else {
/* 200 */       d6 = incompleteBetaFraction2(d1, d2, d4) / d5;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 206 */     d7 = d1 * Math.log(d4);
/* 207 */     double d3 = d2 * Math.log(d5);
/* 208 */     if ((d1 + d2 < 171.6243769563027D) && (Math.abs(d7) < 709.782712893384D) && (Math.abs(d3) < 709.782712893384D)) {
/* 209 */       d3 = Math.pow(d5, d2);
/* 210 */       d3 *= Math.pow(d4, d1);
/* 211 */       d3 /= d1;
/* 212 */       d3 *= d6;
/* 213 */       d3 *= gamma(d1 + d2) / (gamma(d1) * gamma(d2));
/* 214 */       if (i != 0) {
/* 215 */         if (d3 <= 1.1102230246251565E-16D) d3 = 0.9999999999999999D; else
/* 216 */           d3 = 1.0D - d3;
/*     */       }
/* 218 */       return d3;
/*     */     }
/*     */     
/* 221 */     d7 += d3 + logGamma(d1 + d2) - logGamma(d1) - logGamma(d2);
/* 222 */     d7 += Math.log(d6 / d1);
/* 223 */     if (d7 < -745.1332191019412D) {
/* 224 */       d3 = 0.0D;
/*     */     } else {
/* 226 */       d3 = Math.exp(d7);
/*     */     }
/* 228 */     if (i != 0) {
/* 229 */       if (d3 <= 1.1102230246251565E-16D) d3 = 0.9999999999999999D; else
/* 230 */         d3 = 1.0D - d3;
/*     */     }
/* 232 */     return d3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static double incompleteBetaFraction1(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     throws ArithmeticException
/*     */   {
/* 243 */     double d8 = paramDouble1;
/* 244 */     double d9 = paramDouble1 + paramDouble2;
/* 245 */     double d10 = paramDouble1;
/* 246 */     double d11 = paramDouble1 + 1.0D;
/* 247 */     double d12 = 1.0D;
/* 248 */     double d13 = paramDouble2 - 1.0D;
/* 249 */     double d14 = d11;
/* 250 */     double d15 = paramDouble1 + 2.0D;
/*     */     
/* 252 */     double d4 = 0.0D;
/* 253 */     double d7 = 1.0D;
/* 254 */     double d3 = 1.0D;
/* 255 */     double d6 = 1.0D;
/* 256 */     double d18 = 1.0D;
/* 257 */     double d16 = 1.0D;
/* 258 */     int i = 0;
/* 259 */     double d19 = 3.3306690738754696E-16D;
/*     */     do {
/* 261 */       double d1 = -(paramDouble3 * d8 * d9) / (d10 * d11);
/* 262 */       double d2 = d3 + d4 * d1;
/* 263 */       double d5 = d6 + d7 * d1;
/* 264 */       d4 = d3;
/* 265 */       d3 = d2;
/* 266 */       d7 = d6;
/* 267 */       d6 = d5;
/*     */       
/* 269 */       d1 = paramDouble3 * d12 * d13 / (d14 * d15);
/* 270 */       d2 = d3 + d4 * d1;
/* 271 */       d5 = d6 + d7 * d1;
/* 272 */       d4 = d3;
/* 273 */       d3 = d2;
/* 274 */       d7 = d6;
/* 275 */       d6 = d5;
/*     */       
/* 277 */       if (d5 != 0.0D) d16 = d2 / d5;
/* 278 */       double d17; if (d16 != 0.0D) {
/* 279 */         d17 = Math.abs((d18 - d16) / d16);
/* 280 */         d18 = d16;
/*     */       } else {
/* 282 */         d17 = 1.0D;
/*     */       }
/* 284 */       if (d17 < d19) { return d18;
/*     */       }
/* 286 */       d8 += 1.0D;
/* 287 */       d9 += 1.0D;
/* 288 */       d10 += 2.0D;
/* 289 */       d11 += 2.0D;
/* 290 */       d12 += 1.0D;
/* 291 */       d13 -= 1.0D;
/* 292 */       d14 += 2.0D;
/* 293 */       d15 += 2.0D;
/*     */       
/* 295 */       if (Math.abs(d5) + Math.abs(d2) > 4.503599627370496E15D) {
/* 296 */         d4 *= 2.220446049250313E-16D;
/* 297 */         d3 *= 2.220446049250313E-16D;
/* 298 */         d7 *= 2.220446049250313E-16D;
/* 299 */         d6 *= 2.220446049250313E-16D;
/*     */       }
/* 301 */       if ((Math.abs(d5) < 2.220446049250313E-16D) || (Math.abs(d2) < 2.220446049250313E-16D)) {
/* 302 */         d4 *= 4.503599627370496E15D;
/* 303 */         d3 *= 4.503599627370496E15D;
/* 304 */         d7 *= 4.503599627370496E15D;
/* 305 */         d6 *= 4.503599627370496E15D;
/*     */       }
/* 307 */       i++; } while (i < 300);
/*     */     
/* 309 */     return d18;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static double incompleteBetaFraction2(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     throws ArithmeticException
/*     */   {
/* 320 */     double d8 = paramDouble1;
/* 321 */     double d9 = paramDouble2 - 1.0D;
/* 322 */     double d10 = paramDouble1;
/* 323 */     double d11 = paramDouble1 + 1.0D;
/* 324 */     double d12 = 1.0D;
/* 325 */     double d13 = paramDouble1 + paramDouble2;
/* 326 */     double d14 = paramDouble1 + 1.0D;
/* 327 */     double d15 = paramDouble1 + 2.0D;
/*     */     
/* 329 */     double d4 = 0.0D;
/* 330 */     double d7 = 1.0D;
/* 331 */     double d3 = 1.0D;
/* 332 */     double d6 = 1.0D;
/* 333 */     double d19 = paramDouble3 / (1.0D - paramDouble3);
/* 334 */     double d18 = 1.0D;
/* 335 */     double d16 = 1.0D;
/* 336 */     int i = 0;
/* 337 */     double d20 = 3.3306690738754696E-16D;
/*     */     do {
/* 339 */       double d1 = -(d19 * d8 * d9) / (d10 * d11);
/* 340 */       double d2 = d3 + d4 * d1;
/* 341 */       double d5 = d6 + d7 * d1;
/* 342 */       d4 = d3;
/* 343 */       d3 = d2;
/* 344 */       d7 = d6;
/* 345 */       d6 = d5;
/*     */       
/* 347 */       d1 = d19 * d12 * d13 / (d14 * d15);
/* 348 */       d2 = d3 + d4 * d1;
/* 349 */       d5 = d6 + d7 * d1;
/* 350 */       d4 = d3;
/* 351 */       d3 = d2;
/* 352 */       d7 = d6;
/* 353 */       d6 = d5;
/*     */       
/* 355 */       if (d5 != 0.0D) d16 = d2 / d5;
/* 356 */       double d17; if (d16 != 0.0D) {
/* 357 */         d17 = Math.abs((d18 - d16) / d16);
/* 358 */         d18 = d16;
/*     */       } else {
/* 360 */         d17 = 1.0D;
/*     */       }
/* 362 */       if (d17 < d20) { return d18;
/*     */       }
/* 364 */       d8 += 1.0D;
/* 365 */       d9 -= 1.0D;
/* 366 */       d10 += 2.0D;
/* 367 */       d11 += 2.0D;
/* 368 */       d12 += 1.0D;
/* 369 */       d13 += 1.0D;
/* 370 */       d14 += 2.0D;
/* 371 */       d15 += 2.0D;
/*     */       
/* 373 */       if (Math.abs(d5) + Math.abs(d2) > 4.503599627370496E15D) {
/* 374 */         d4 *= 2.220446049250313E-16D;
/* 375 */         d3 *= 2.220446049250313E-16D;
/* 376 */         d7 *= 2.220446049250313E-16D;
/* 377 */         d6 *= 2.220446049250313E-16D;
/*     */       }
/* 379 */       if ((Math.abs(d5) < 2.220446049250313E-16D) || (Math.abs(d2) < 2.220446049250313E-16D)) {
/* 380 */         d4 *= 4.503599627370496E15D;
/* 381 */         d3 *= 4.503599627370496E15D;
/* 382 */         d7 *= 4.503599627370496E15D;
/* 383 */         d6 *= 4.503599627370496E15D;
/*     */       }
/* 385 */       i++; } while (i < 300);
/*     */     
/* 387 */     return d18;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double incompleteGamma(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/* 400 */     if ((paramDouble2 <= 0.0D) || (paramDouble1 <= 0.0D)) { return 0.0D;
/*     */     }
/* 402 */     if ((paramDouble2 > 1.0D) && (paramDouble2 > paramDouble1)) { return 1.0D - incompleteGammaComplement(paramDouble1, paramDouble2);
/*     */     }
/*     */     
/* 405 */     double d2 = paramDouble1 * Math.log(paramDouble2) - paramDouble2 - logGamma(paramDouble1);
/* 406 */     if (d2 < -709.782712893384D) { return 0.0D;
/*     */     }
/* 408 */     d2 = Math.exp(d2);
/*     */     
/*     */ 
/* 411 */     double d4 = paramDouble1;
/* 412 */     double d3 = 1.0D;
/* 413 */     double d1 = 1.0D;
/*     */     do
/*     */     {
/* 416 */       d4 += 1.0D;
/* 417 */       d3 *= paramDouble2 / d4;
/* 418 */       d1 += d3;
/*     */     }
/* 420 */     while (d3 / d1 > 1.1102230246251565E-16D);
/*     */     
/* 422 */     return d1 * d2 / paramDouble1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double incompleteGammaComplement(double paramDouble1, double paramDouble2)
/*     */     throws ArithmeticException
/*     */   {
/* 434 */     if ((paramDouble2 <= 0.0D) || (paramDouble1 <= 0.0D)) { return 1.0D;
/*     */     }
/* 436 */     if ((paramDouble2 < 1.0D) || (paramDouble2 < paramDouble1)) { return 1.0D - incompleteGamma(paramDouble1, paramDouble2);
/*     */     }
/* 438 */     double d2 = paramDouble1 * Math.log(paramDouble2) - paramDouble2 - logGamma(paramDouble1);
/* 439 */     if (d2 < -709.782712893384D) { return 0.0D;
/*     */     }
/* 441 */     d2 = Math.exp(d2);
/*     */     
/*     */ 
/* 444 */     double d7 = 1.0D - paramDouble1;
/* 445 */     double d8 = paramDouble2 + d7 + 1.0D;
/* 446 */     double d3 = 0.0D;
/* 447 */     double d11 = 1.0D;
/* 448 */     double d14 = paramDouble2;
/* 449 */     double d10 = paramDouble2 + 1.0D;
/* 450 */     double d13 = d8 * paramDouble2;
/* 451 */     double d1 = d10 / d13;
/*     */     double d6;
/*     */     do {
/* 454 */       d3 += 1.0D;
/* 455 */       d7 += 1.0D;
/* 456 */       d8 += 2.0D;
/* 457 */       double d4 = d7 * d3;
/* 458 */       double d9 = d10 * d8 - d11 * d4;
/* 459 */       double d12 = d13 * d8 - d14 * d4;
/* 460 */       if (d12 != 0.0D) {
/* 461 */         double d5 = d9 / d12;
/* 462 */         d6 = Math.abs((d1 - d5) / d5);
/* 463 */         d1 = d5;
/*     */       } else {
/* 465 */         d6 = 1.0D;
/*     */       }
/* 467 */       d11 = d10;
/* 468 */       d10 = d9;
/* 469 */       d14 = d13;
/* 470 */       d13 = d12;
/* 471 */       if (Math.abs(d9) > 4.503599627370496E15D) {
/* 472 */         d11 *= 2.220446049250313E-16D;
/* 473 */         d10 *= 2.220446049250313E-16D;
/* 474 */         d14 *= 2.220446049250313E-16D;
/* 475 */         d13 *= 2.220446049250313E-16D;
/*     */       }
/* 477 */     } while (d6 > 1.1102230246251565E-16D);
/*     */     
/* 479 */     return d1 * d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double logGamma(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 487 */     double[] arrayOfDouble1 = { 8.116141674705085E-4D, -5.950619042843014E-4D, 7.936503404577169E-4D, -0.002777777777300997D, 0.08333333333333319D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 494 */     double[] arrayOfDouble2 = { -1378.2515256912086D, -38801.631513463784D, -331612.9927388712D, -1162370.974927623D, -1721737.0082083966D, -853555.6642457654D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 502 */     double[] arrayOfDouble3 = { -351.81570143652345D, -17064.210665188115D, -220528.59055385445D, -1139334.4436798252D, -2532523.0717758294D, -2018891.4143353277D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     double d4;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 512 */     if (paramDouble < -34.0D) {
/* 513 */       d2 = -paramDouble;
/* 514 */       double d3 = logGamma(d2);
/* 515 */       d1 = Math.floor(d2);
/* 516 */       if (d1 == d2) throw new ArithmeticException("lgam: Overflow");
/* 517 */       d4 = d2 - d1;
/* 518 */       if (d4 > 0.5D) {
/* 519 */         d1 += 1.0D;
/* 520 */         d4 = d1 - d2;
/*     */       }
/* 522 */       d4 = d2 * Math.sin(3.141592653589793D * d4);
/* 523 */       if (d4 == 0.0D) { throw new ArithmeticException("lgamma: Overflow");
/*     */       }
/* 525 */       d4 = 1.1447298858494002D - Math.log(d4) - d3;
/* 526 */       return d4;
/*     */     }
/*     */     
/* 529 */     if (paramDouble < 13.0D) {
/* 530 */       d4 = 1.0D;
/* 531 */       while (paramDouble >= 3.0D) {
/* 532 */         paramDouble -= 1.0D;
/* 533 */         d4 *= paramDouble;
/*     */       }
/* 535 */       while (paramDouble < 2.0D) {
/* 536 */         if (paramDouble == 0.0D) { throw new ArithmeticException("lgamma: Overflow");
/*     */         }
/* 538 */         d4 /= paramDouble;
/* 539 */         paramDouble += 1.0D;
/*     */       }
/* 541 */       if (d4 < 0.0D) d4 = -d4;
/* 542 */       if (paramDouble == 2.0D) return Math.log(d4);
/* 543 */       paramDouble -= 2.0D;
/* 544 */       d1 = paramDouble * Polynomial.polevl(paramDouble, arrayOfDouble2, 5) / Polynomial.p1evl(paramDouble, arrayOfDouble3, 6);
/* 545 */       return Math.log(d4) + d1;
/*     */     }
/*     */     
/* 548 */     if (paramDouble > 2.556348E305D) { throw new ArithmeticException("lgamma: Overflow");
/*     */     }
/*     */     
/* 551 */     double d2 = (paramDouble - 0.5D) * Math.log(paramDouble) - paramDouble + 0.9189385332046728D;
/*     */     
/* 553 */     if (paramDouble > 1.0E8D) { return d2;
/*     */     }
/* 555 */     double d1 = 1.0D / (paramDouble * paramDouble);
/* 556 */     if (paramDouble >= 1000.0D) {
/* 557 */       d2 += ((7.936507936507937E-4D * d1 - 0.002777777777777778D) * d1 + 0.08333333333333333D) / paramDouble;
/*     */     }
/*     */     else
/*     */     {
/* 561 */       d2 += Polynomial.polevl(d1, arrayOfDouble1, 4) / paramDouble; }
/* 562 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static double powerSeries(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     throws ArithmeticException
/*     */   {
/* 571 */     double d8 = 1.0D / paramDouble1;
/* 572 */     double d3 = (1.0D - paramDouble2) * paramDouble3;
/* 573 */     double d4 = d3 / (paramDouble1 + 1.0D);
/* 574 */     double d6 = d4;
/* 575 */     double d2 = d3;
/* 576 */     double d5 = 2.0D;
/* 577 */     double d1 = 0.0D;
/* 578 */     double d7 = 1.1102230246251565E-16D * d8;
/* 579 */     while (Math.abs(d4) > d7) {
/* 580 */       d3 = (d5 - paramDouble2) * paramDouble3 / d5;
/* 581 */       d2 *= d3;
/* 582 */       d4 = d2 / (paramDouble1 + d5);
/* 583 */       d1 += d4;
/* 584 */       d5 += 1.0D;
/*     */     }
/* 586 */     d1 += d6;
/* 587 */     d1 += d8;
/*     */     
/* 589 */     d3 = paramDouble1 * Math.log(paramDouble3);
/* 590 */     if ((paramDouble1 + paramDouble2 < 171.6243769563027D) && (Math.abs(d3) < 709.782712893384D)) {
/* 591 */       d2 = gamma(paramDouble1 + paramDouble2) / (gamma(paramDouble1) * gamma(paramDouble2));
/* 592 */       d1 = d1 * d2 * Math.pow(paramDouble3, paramDouble1);
/*     */     } else {
/* 594 */       d2 = logGamma(paramDouble1 + paramDouble2) - logGamma(paramDouble1) - logGamma(paramDouble2) + d3 + Math.log(d1);
/* 595 */       if (d2 < -745.1332191019412D) d1 = 0.0D; else
/* 596 */         d1 = Math.exp(d2);
/*     */     }
/* 598 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */   static double stirlingFormula(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 605 */     double[] arrayOfDouble = { 7.873113957930937E-4D, -2.2954996161337813E-4D, -0.0026813261780578124D, 0.0034722222160545866D, 0.08333333333334822D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 612 */     double d1 = 143.01608D;
/*     */     
/* 614 */     double d2 = 1.0D / paramDouble;
/* 615 */     double d3 = Math.exp(paramDouble);
/*     */     
/* 617 */     d2 = 1.0D + d2 * Polynomial.polevl(d2, arrayOfDouble, 4);
/*     */     
/* 619 */     if (paramDouble > d1)
/*     */     {
/* 621 */       double d4 = Math.pow(paramDouble, 0.5D * paramDouble - 0.25D);
/* 622 */       d3 = d4 * (d4 / d3);
/*     */     } else {
/* 624 */       d3 = Math.pow(paramDouble, paramDouble - 0.5D) / d3;
/*     */     }
/* 626 */     d3 = 2.5066282746310007D * d3 * d2;
/* 627 */     return d3;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/Gamma.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */