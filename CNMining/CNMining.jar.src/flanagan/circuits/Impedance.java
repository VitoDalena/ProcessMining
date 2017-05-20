/*     */ package flanagan.circuits;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.math.Fmath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Impedance
/*     */ {
/*  40 */   protected static int numberOfModels = 44;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex resistanceImpedance(double resistance)
/*     */   {
/*  48 */     return new Complex(resistance, 0.0D);
/*     */   }
/*     */   
/*     */   public static Complex capacitanceImpedance(double capacitance, double omega)
/*     */   {
/*  53 */     return new Complex(0.0D, -1.0D / (capacitance * omega));
/*     */   }
/*     */   
/*     */   public static Complex inductanceImpedance(double inductance, double omega)
/*     */   {
/*  58 */     return new Complex(0.0D, inductance * omega);
/*     */   }
/*     */   
/*     */   public static Complex infiniteWarburgImpedance(double sigma, double omega)
/*     */   {
/*  63 */     double term = sigma / Math.sqrt(omega);
/*  64 */     return new Complex(term, -term);
/*     */   }
/*     */   
/*     */   public static Complex finiteWarburgImpedance(double sigma, double tanhConst, double omega)
/*     */   {
/*  69 */     Complex zVal1 = new Complex(sigma * Math.sqrt(omega), 0.0D);
/*  70 */     Complex zVal2 = new Complex(tanhConst, 0.0D);
/*  71 */     Complex zVal3 = new Complex(0.0D, omega);
/*  72 */     Complex zVal4 = Complex.sqrt(zVal3);
/*  73 */     Complex zVal5 = zVal2.times(zVal4);
/*  74 */     Complex zVal6 = Complex.tanh(zVal5);
/*  75 */     Complex zVal7 = zVal1.times(zVal6);
/*  76 */     Complex zVal8 = Complex.plusOne().minus(Complex.plusJay());
/*  77 */     return zVal8.times(zVal7);
/*     */   }
/*     */   
/*     */   public static Complex constantPhaseElementImpedance(double cpeCoeff, double alpha, double omega)
/*     */   {
/*  82 */     Complex jOmega = new Complex(0.0D, omega);
/*  83 */     Complex jOmegaAlpha = Complex.pow(jOmega, -alpha);
/*  84 */     Complex coeff = new Complex(cpeCoeff, 0.0D);
/*  85 */     return coeff.times(jOmegaAlpha);
/*     */   }
/*     */   
/*     */   public static Complex impedanceInSeries(Complex z1, Complex z2)
/*     */   {
/*  90 */     return z1.plus(z2);
/*     */   }
/*     */   
/*     */   public static Complex impedanceInSeries(double z1, Complex z2)
/*     */   {
/*  95 */     return z2.plus(z1);
/*     */   }
/*     */   
/*     */   public static Complex impedanceInSeries(Complex z1, double z2)
/*     */   {
/* 100 */     return z1.plus(z2);
/*     */   }
/*     */   
/*     */   public static Complex impedanceInSeries(double z1, double z2)
/*     */   {
/* 105 */     return new Complex(z1 + z2, 0.0D);
/*     */   }
/*     */   
/*     */   public static Complex rInSeriesWithC(double res, double cap, double omega)
/*     */   {
/* 110 */     Complex ret = new Complex(res, -1.0D / (cap * omega));
/* 111 */     return ret;
/*     */   }
/*     */   
/*     */   public static Complex rInSeriesWithL(double res, double ind, double omega)
/*     */   {
/* 116 */     Complex ret = new Complex(res, ind * omega);
/* 117 */     return ret;
/*     */   }
/*     */   
/*     */   public static Complex cInSeriesWithL(double cap, double ind, double omega)
/*     */   {
/* 122 */     Complex z1 = new Complex(0.0D, -1.0D / (cap * omega));
/* 123 */     Complex z2 = new Complex(0.0D, ind * omega);
/* 124 */     return z1.plus(z2);
/*     */   }
/*     */   
/*     */   public static Complex impedanceInParallel(Complex z1, Complex z2)
/*     */   {
/* 129 */     Complex ret = z1.times(z2);
/* 130 */     return ret.over(z1.plus(z2));
/*     */   }
/*     */   
/*     */   public static Complex impedanceInParallel(Complex z1, double z2)
/*     */   {
/* 135 */     Complex ret = z1.times(z2);
/* 136 */     return ret.over(z1.plus(z2));
/*     */   }
/*     */   
/*     */   public static Complex impedanceInParallel(double z1, Complex z2)
/*     */   {
/* 141 */     Complex ret = z2.times(z1);
/* 142 */     return ret.over(z2.plus(z1));
/*     */   }
/*     */   
/*     */   public static Complex impedanceInParallel(double z1, double z2)
/*     */   {
/* 147 */     return new Complex(z1 * z2 / (z1 + z2), 0.0D);
/*     */   }
/*     */   
/*     */   public static Complex rInParallelWithC(double res, double cap, double omega)
/*     */   {
/* 152 */     Complex zC = new Complex(0.0D, -1.0D / (cap * omega));
/* 153 */     Complex zR = new Complex(res, 0.0D);
/* 154 */     Complex ret = zC.times(zR);
/* 155 */     return ret.over(zC.plus(zR));
/*     */   }
/*     */   
/*     */   public static Complex rInParallelWithL(double res, double ind, double omega)
/*     */   {
/* 160 */     Complex zL = new Complex(0.0D, ind * omega);
/* 161 */     Complex zR = new Complex(res, 0.0D);
/* 162 */     Complex ret = zL.times(zR);
/* 163 */     return ret.over(zL.plus(zR));
/*     */   }
/*     */   
/*     */   public static Complex cInParallelWithL(double cap, double ind, double omega)
/*     */   {
/* 168 */     Complex z1 = new Complex(0.0D, -1.0D / (cap * omega));
/* 169 */     Complex z2 = new Complex(0.0D, ind * omega);
/* 170 */     Complex z3 = z1.plus(z2);
/* 171 */     Complex z4 = z1.times(z2);
/* 172 */     return z4.over(z3);
/*     */   }
/*     */   
/*     */ 
/*     */   public static String[] modelComponents(int modelNumber)
/*     */   {
/* 178 */     String[][] compName = { { " " }, { "R1" }, { "C1" }, { "L1" }, { "W1" }, { "Fsigma1", "Fdelta1" }, { "Qsigma1", "Qalpha1" }, { "R1", "C1" }, { "R1", "L1" }, { "L1", "C1" }, { "R1", "C1" }, { "R1", "L1" }, { "L1", "C1" }, { "R1", "C1", "R2" }, { "R1", "C1", "R2", "L1" }, { "R1", "C1", "R2", "L1" }, { "R1", "C1", "C2" }, { "R1", "C1", "C2" }, { "R1", "C1", "R2", "C2" }, { "R1", "C1", "R2", "C2" }, { "R1", "C1", "R2", "C2", "R3" }, { "R1", "C1", "R2", "C2", "R3" }, { "R1", "C1", "R2", "C2", "R3", "C3" }, { "R1", "C1", "R2", "C2", "R3", "C3", "R4" }, { "R1", "C1", "W1", "R2" }, { "R1", "C1", "Fsigma1", "Fdelta1", "R2" }, { "R1", "C1", "Qsigma1", "Qalpha1", "R2" }, { "R1", "C1", "R2", "C2", "W1" }, { "R1", "C1", "R2", "C2", "W3", "C3", "R4" }, { "R1", "C1", "R2", "Qsigma1", "Qalpha1" }, { "R1", "C1", "R2", "Qsigma1", "Qalpha1", "R3" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1" }, { "C1", "Qsigma1", "Qalpha1", "C2", "Qsigma2", "Qalpha2", "C3", "Qsigma3", "Qalpha3" }, { "C1", "Qsigma1", "Qalpha1", "C2", "Qsigma2", "Qalpha2", "C3", "Qsigma3", "Qalpha3", "R1" }, { "R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3", "Qsigma3", "Qalpha3", "C3" }, { "R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3", "Qsigma3", "Qalpha3", "C3", "R4" }, { "R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2" }, { "R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1" }, { "R1", "Qsigma1", "Qalpha1", "R2", "C2", "R3", "Qsigma3", "Qalpha3", "R4", "C1" }, { "R1", "Qsigma1", "Qalpha1", "R2", "C2", "R3", "Qsigma3", "Qalpha3", "R4" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1" }, { "R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "C1" } };
/* 179 */     return compName[modelNumber];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Complex modelImpedance(double[] parameter, double omega, int modelNumber)
/*     */   {
/* 186 */     Complex zVal = null;
/* 187 */     Complex z1 = null;
/* 188 */     Complex z2 = null;
/* 189 */     Complex z3 = null;
/* 190 */     Complex z4 = null;
/* 191 */     switch (modelNumber) {
/* 192 */     case 1:  zVal = resistanceImpedance(parameter[0]);
/* 193 */       break;
/* 194 */     case 2:  zVal = capacitanceImpedance(parameter[0], omega);
/* 195 */       break;
/* 196 */     case 3:  zVal = inductanceImpedance(parameter[0], omega);
/* 197 */       break;
/* 198 */     case 4:  zVal = infiniteWarburgImpedance(parameter[0], omega);
/* 199 */       break;
/* 200 */     case 5:  zVal = finiteWarburgImpedance(parameter[0], parameter[1], omega);
/* 201 */       break;
/* 202 */     case 6:  zVal = constantPhaseElementImpedance(parameter[0], parameter[1], omega);
/* 203 */       break;
/* 204 */     case 7:  zVal = rInSeriesWithC(parameter[0], parameter[1], omega);
/* 205 */       break;
/* 206 */     case 8:  zVal = new Complex(parameter[0], parameter[1] * omega);
/* 207 */       break;
/* 208 */     case 9:  z1 = new Complex(0.0D, -1.0D / (parameter[0] * omega));
/* 209 */       z2 = new Complex(0.0D, parameter[1] * omega);
/* 210 */       zVal = impedanceInSeries(z1, z2);
/* 211 */       break;
/* 212 */     case 10:  zVal = rInParallelWithC(parameter[0], parameter[1], omega);
/* 213 */       break;
/* 214 */     case 11:  z1 = new Complex(parameter[0], 0.0D);
/* 215 */       z2 = new Complex(0.0D, parameter[1] * omega);
/* 216 */       zVal = impedanceInParallel(z1, z2);
/* 217 */       break;
/* 218 */     case 12:  z1 = new Complex(0.0D, -1.0D / (parameter[0] * omega));
/* 219 */       z2 = new Complex(0.0D, parameter[1] * omega);
/* 220 */       zVal = impedanceInParallel(z1, z2);
/* 221 */       break;
/* 222 */     case 13:  zVal = rInParallelWithC(parameter[0], parameter[1], omega);
/* 223 */       zVal = zVal.plus(parameter[2]);
/* 224 */       break;
/* 225 */     case 14:  zVal = rInParallelWithC(parameter[0], parameter[1], omega);
/* 226 */       zVal = zVal.plus(parameter[2]);
/* 227 */       z1 = new Complex(0.0D, parameter[3] * omega);
/* 228 */       zVal = zVal.plus(z1);
/* 229 */       break;
/* 230 */     case 15:  zVal = rInParallelWithC(parameter[0], parameter[1], omega);
/* 231 */       zVal = zVal.plus(parameter[2]);
/* 232 */       z1 = new Complex(0.0D, parameter[3] * omega);
/* 233 */       zVal = impedanceInParallel(zVal, z1);
/* 234 */       break;
/* 235 */     case 16:  zVal = rInParallelWithC(parameter[0], parameter[1], omega);
/* 236 */       z2 = new Complex(0.0D, -1.0D / (parameter[2] * omega));
/* 237 */       zVal = zVal.plus(z2);
/* 238 */       break;
/* 239 */     case 17:  z1 = new Complex(parameter[0], -1.0D / (parameter[1] * omega));
/* 240 */       z2 = new Complex(0.0D, -1.0D / (parameter[2] * omega));
/* 241 */       zVal = z1.times(z2).over(z2.plus(z1));
/* 242 */       break;
/* 243 */     case 18:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 244 */       z2 = new Complex(parameter[2], -1.0D / (parameter[3] * omega));
/* 245 */       zVal = z1.plus(z2);
/* 246 */       break;
/* 247 */     case 19:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 248 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 249 */       zVal = z1.plus(z2);
/* 250 */       break;
/* 251 */     case 20:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 252 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 253 */       zVal = z1.plus(z2);
/* 254 */       zVal = zVal.plus(parameter[4]);
/* 255 */       break;
/* 256 */     case 21:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 257 */       z2 = z1.plus(parameter[2]);
/* 258 */       z3 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 259 */       z4 = impedanceInParallel(z2, z3);
/* 260 */       zVal = z4.plus(parameter[4]);
/* 261 */       break;
/* 262 */     case 22:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 263 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 264 */       zVal = z1.plus(z2);
/* 265 */       z3 = rInParallelWithC(parameter[4], parameter[5], omega);
/* 266 */       zVal = zVal.plus(z3);
/* 267 */       break;
/* 268 */     case 23:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 269 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 270 */       zVal = z1.plus(z2);
/* 271 */       z3 = rInParallelWithC(parameter[4], parameter[5], omega);
/* 272 */       zVal = zVal.plus(z3);
/* 273 */       zVal = zVal.plus(parameter[6]);
/* 274 */       break;
/* 275 */     case 24:  z1 = infiniteWarburgImpedance(parameter[2], omega);
/* 276 */       z2 = impedanceInSeries(parameter[0], z1);
/* 277 */       z3 = capacitanceImpedance(parameter[1], omega);
/* 278 */       z4 = impedanceInParallel(z2, z3);
/* 279 */       zVal = impedanceInSeries(z4, parameter[3]);
/* 280 */       break;
/* 281 */     case 25:  z1 = finiteWarburgImpedance(parameter[2], parameter[3], omega);
/* 282 */       z2 = impedanceInSeries(parameter[0], z1);
/* 283 */       z3 = capacitanceImpedance(parameter[1], omega);
/* 284 */       z4 = impedanceInParallel(z2, z3);
/* 285 */       zVal = impedanceInSeries(z4, parameter[4]);
/* 286 */       break;
/* 287 */     case 26:  z1 = constantPhaseElementImpedance(parameter[2], parameter[3], omega);
/* 288 */       z2 = impedanceInSeries(parameter[0], z1);
/* 289 */       z3 = capacitanceImpedance(parameter[1], omega);
/* 290 */       z4 = impedanceInParallel(z2, z3);
/* 291 */       zVal = impedanceInSeries(z4, parameter[4]);
/* 292 */       break;
/* 293 */     case 27:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 294 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 295 */       zVal = z1.plus(z2);
/* 296 */       z3 = infiniteWarburgImpedance(parameter[4], omega);
/* 297 */       zVal = zVal.plus(z3);
/* 298 */       break;
/* 299 */     case 28:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 300 */       z2 = rInParallelWithC(parameter[2], parameter[3], omega);
/* 301 */       zVal = z1.plus(z2);
/* 302 */       z3 = infiniteWarburgImpedance(parameter[4], omega);
/* 303 */       z4 = new Complex(0.0D, -1.0D / (parameter[5] * omega));
/* 304 */       z4 = impedanceInParallel(z3, z4);
/* 305 */       zVal = zVal.plus(z4);
/* 306 */       zVal = zVal.plus(parameter[6]);
/* 307 */       break;
/* 308 */     case 29:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 309 */       z2 = constantPhaseElementImpedance(parameter[3], parameter[4], omega);
/* 310 */       z3 = impedanceInParallel(z2, parameter[2]);
/* 311 */       zVal = z1.plus(z3);
/* 312 */       break;
/* 313 */     case 30:  z1 = rInParallelWithC(parameter[0], parameter[1], omega);
/* 314 */       z2 = constantPhaseElementImpedance(parameter[3], parameter[4], omega);
/* 315 */       z3 = impedanceInParallel(z2, parameter[2]);
/* 316 */       zVal = z1.plus(z3);
/* 317 */       zVal = zVal.plus(parameter[5]);
/* 318 */       break;
/* 319 */     case 31:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 320 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 321 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 322 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 323 */       zVal = zVal.plus(z2);
/* 324 */       z1 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 325 */       z2 = impedanceInParallel(z1, parameter[6]);
/* 326 */       zVal = zVal.plus(z2);
/* 327 */       break;
/* 328 */     case 32:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 329 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 330 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 331 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 332 */       zVal = zVal.plus(z2);
/* 333 */       z1 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 334 */       z2 = impedanceInParallel(z1, parameter[6]);
/* 335 */       zVal = zVal.plus(z2);
/* 336 */       zVal = zVal.plus(parameter[9]);
/* 337 */       break;
/* 338 */     case 33:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 339 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 340 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 341 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 342 */       zVal = zVal.plus(z2);
/* 343 */       z1 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 344 */       z2 = impedanceInParallel(z1, parameter[6]);
/* 345 */       zVal = zVal.plus(z2);
/* 346 */       zVal = zVal.plus(parameter[9]);
/* 347 */       z3 = new Complex(0.0D, -1.0D / (parameter[10] * omega));
/* 348 */       zVal = impedanceInParallel(zVal, z3);
/* 349 */       break;
/* 350 */     case 34:  z1 = new Complex(0.0D, -1.0D / (parameter[0] * omega));
/* 351 */       z2 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 352 */       zVal = impedanceInParallel(z1, z2);
/* 353 */       z1 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 354 */       z2 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 355 */       z3 = impedanceInParallel(z1, z2);
/* 356 */       zVal = zVal.plus(z3);
/* 357 */       z1 = new Complex(0.0D, -1.0D / (parameter[6] * omega));
/* 358 */       z2 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 359 */       z3 = impedanceInParallel(z1, z2);
/* 360 */       zVal = zVal.plus(z3);
/* 361 */       break;
/* 362 */     case 35:  z1 = new Complex(0.0D, -1.0D / (parameter[0] * omega));
/* 363 */       z2 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 364 */       zVal = impedanceInParallel(z1, z2);
/* 365 */       z1 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 366 */       z2 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 367 */       z3 = impedanceInParallel(z1, z2);
/* 368 */       zVal = zVal.plus(z3);
/* 369 */       z1 = new Complex(0.0D, -1.0D / (parameter[6] * omega));
/* 370 */       z2 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 371 */       z3 = impedanceInParallel(z1, z2);
/* 372 */       zVal = zVal.plus(z3);
/* 373 */       zVal = zVal.plus(parameter[9]);
/* 374 */       break;
/* 375 */     case 36:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 376 */       z2 = z1.plus(parameter[0]);
/* 377 */       z3 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 378 */       zVal = impedanceInParallel(z2, z3);
/* 379 */       z1 = constantPhaseElementImpedance(parameter[5], parameter[6], omega);
/* 380 */       z2 = z1.plus(parameter[4]);
/* 381 */       z3 = new Complex(0.0D, -1.0D / (parameter[7] * omega));
/* 382 */       z4 = impedanceInParallel(z2, z3);
/* 383 */       zVal = zVal.plus(z4);
/* 384 */       z1 = constantPhaseElementImpedance(parameter[9], parameter[10], omega);
/* 385 */       z2 = z1.plus(parameter[8]);
/* 386 */       z3 = new Complex(0.0D, -1.0D / (parameter[11] * omega));
/* 387 */       z4 = impedanceInParallel(z2, z3);
/* 388 */       zVal = zVal.plus(z4);
/* 389 */       break;
/* 390 */     case 37:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 391 */       z2 = z1.plus(parameter[0]);
/* 392 */       z3 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 393 */       zVal = impedanceInParallel(z2, z3);
/* 394 */       z1 = constantPhaseElementImpedance(parameter[5], parameter[6], omega);
/* 395 */       z2 = z1.plus(parameter[4]);
/* 396 */       z3 = new Complex(0.0D, -1.0D / (parameter[7] * omega));
/* 397 */       z4 = impedanceInParallel(z2, z3);
/* 398 */       zVal = zVal.plus(z4);
/* 399 */       z1 = constantPhaseElementImpedance(parameter[9], parameter[10], omega);
/* 400 */       z2 = z1.plus(parameter[8]);
/* 401 */       z3 = new Complex(0.0D, -1.0D / (parameter[11] * omega));
/* 402 */       z4 = impedanceInParallel(z2, z3);
/* 403 */       zVal = zVal.plus(z4);
/* 404 */       zVal = zVal.plus(parameter[12]);
/* 405 */       break;
/* 406 */     case 38:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 407 */       z2 = z1.plus(parameter[0]);
/* 408 */       z3 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 409 */       zVal = impedanceInParallel(z2, z3);
/* 410 */       z1 = constantPhaseElementImpedance(parameter[5], parameter[6], omega);
/* 411 */       z2 = z1.plus(parameter[4]);
/* 412 */       z3 = new Complex(0.0D, -1.0D / (parameter[7] * omega));
/* 413 */       z4 = impedanceInParallel(z2, z3);
/* 414 */       zVal = zVal.plus(z4);
/* 415 */       break;
/* 416 */     case 39:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 417 */       z2 = z1.plus(parameter[0]);
/* 418 */       z3 = new Complex(0.0D, -1.0D / (parameter[3] * omega));
/* 419 */       zVal = impedanceInParallel(z2, z3);
/* 420 */       z1 = constantPhaseElementImpedance(parameter[5], parameter[6], omega);
/* 421 */       z2 = z1.plus(parameter[4]);
/* 422 */       z3 = new Complex(0.0D, -1.0D / (parameter[7] * omega));
/* 423 */       z4 = impedanceInParallel(z2, z3);
/* 424 */       zVal = zVal.plus(z4);
/* 425 */       zVal = z4.plus(parameter[8]);
/* 426 */       break;
/* 427 */     case 40:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 428 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 429 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 430 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 431 */       zVal = zVal.plus(z2);
/* 432 */       z1 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 433 */       z2 = impedanceInParallel(z1, parameter[6]);
/* 434 */       zVal = zVal.plus(z2);
/* 435 */       z3 = new Complex(0.0D, -1.0D / (parameter[10] * omega));
/* 436 */       zVal = impedanceInParallel(zVal, z3);
/* 437 */       zVal = zVal.plus(parameter[9]);
/* 438 */       break;
/* 439 */     case 41:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 440 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 441 */       z1 = new Complex(0.0D, -1.0D / (parameter[4] * omega));
/* 442 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 443 */       zVal = zVal.plus(z2);
/* 444 */       z1 = constantPhaseElementImpedance(parameter[6], parameter[7], omega);
/* 445 */       z2 = impedanceInParallel(z1, parameter[5]);
/* 446 */       zVal = zVal.plus(z2);
/* 447 */       z3 = new Complex(0.0D, -1.0D / (parameter[9] * omega));
/* 448 */       zVal = impedanceInParallel(zVal, z3);
/* 449 */       zVal = zVal.plus(parameter[8]);
/* 450 */       break;
/* 451 */     case 42:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 452 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 453 */       z1 = new Complex(0.0D, -1.0D / (parameter[4] * omega));
/* 454 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 455 */       zVal = zVal.plus(z2);
/* 456 */       z1 = constantPhaseElementImpedance(parameter[6], parameter[7], omega);
/* 457 */       z2 = impedanceInParallel(z1, parameter[5]);
/* 458 */       zVal = zVal.plus(z2);
/* 459 */       zVal = zVal.plus(parameter[8]);
/* 460 */       break;
/* 461 */     case 43:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 462 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 463 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 464 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 465 */       zVal = zVal.plus(z2);
/* 466 */       z1 = constantPhaseElementImpedance(parameter[7], parameter[8], omega);
/* 467 */       z2 = impedanceInParallel(z1, parameter[6]);
/* 468 */       zVal = zVal.plus(z2);
/* 469 */       zVal = zVal.plus(parameter[9]);
/* 470 */       z3 = new Complex(0.0D, -1.0D / (parameter[10] * omega));
/* 471 */       zVal = impedanceInParallel(zVal, z3);
/* 472 */       break;
/* 473 */     case 44:  z1 = constantPhaseElementImpedance(parameter[1], parameter[2], omega);
/* 474 */       zVal = impedanceInParallel(z1, parameter[0]);
/* 475 */       z1 = constantPhaseElementImpedance(parameter[4], parameter[5], omega);
/* 476 */       z2 = impedanceInParallel(z1, parameter[3]);
/* 477 */       zVal = zVal.plus(z2);
/* 478 */       zVal = zVal.plus(parameter[6]);
/* 479 */       z3 = new Complex(0.0D, -1.0D / (parameter[7] * omega));
/* 480 */       zVal = impedanceInParallel(zVal, z3);
/* 481 */       break;
/* 482 */     default:  throw new IllegalArgumentException("No model " + modelNumber + " exists");
/*     */     }
/*     */     
/* 485 */     return zVal;
/*     */   }
/*     */   
/*     */   public static double warburgSigma(double electrodeArea, double oxidantDiffCoeff, double oxidantConcn, double reductantDiffCoeff, double reductantConcn, double tempCelsius, int electronsTransferred)
/*     */   {
/* 490 */     double firstTerm = 8.31447215D * (tempCelsius - -273.15D) / (Fmath.square(electronsTransferred * 96485.341539D) * electrodeArea * Math.sqrt(2.0D));
/* 491 */     double secondTerm = 1.0D / (oxidantConcn * Math.sqrt(oxidantDiffCoeff));
/* 492 */     double thirdTerm = 1.0D / (reductantConcn * Math.sqrt(reductantDiffCoeff));
/* 493 */     return firstTerm * (secondTerm + thirdTerm);
/*     */   }
/*     */   
/*     */ 
/*     */   public static double parallelPlateCapacitance(double plateArea, double plateSeparation, double relativePermittivity)
/*     */   {
/* 499 */     return plateArea * relativePermittivity * 8.854187817E-12D / plateSeparation;
/*     */   }
/*     */   
/*     */   public static double parallelPlateCapacitance(double plateLength, double plateWidth, double plateSeparation, double relativePermittivity) {
/* 503 */     return plateLength * plateWidth * relativePermittivity * 8.854187817E-12D / plateSeparation;
/*     */   }
/*     */   
/*     */   public static double coaxialCapacitance(double cylinderLength, double innerRadius, double outerRadius, double relativePermittivity)
/*     */   {
/* 508 */     return 6.283185307179586D * relativePermittivity * 8.854187817E-12D * cylinderLength / Math.log(outerRadius / innerRadius);
/*     */   }
/*     */   
/*     */   public static double parallelWiresCapacitance(double wireLength, double wireRadius, double wireSeparation, double relativePermittivity)
/*     */   {
/* 513 */     return 3.141592653589793D * relativePermittivity * 8.854187817E-12D * wireLength / Math.log((wireSeparation - wireRadius) / wireRadius);
/*     */   }
/*     */   
/*     */   public static double parallelPlateInductance(double plateLength, double plateWidth, double plateSeparation, double relativePermeability)
/*     */   {
/* 518 */     return relativePermeability * 1.2566370614359173E-6D * plateSeparation * plateLength / plateWidth;
/*     */   }
/*     */   
/*     */   public static double coaxialInductance(double cylinderLength, double innerRadius, double outerRadius, double relativePermeability)
/*     */   {
/* 523 */     return relativePermeability * 1.2566370614359173E-6D * cylinderLength * Math.log(outerRadius / innerRadius) / 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double parallelWiresInductance(double wireLength, double wireRadius, double wireSeparation, double relativePermeability)
/*     */   {
/* 528 */     return relativePermeability * 1.2566370614359173E-6D * wireLength * Math.log((wireSeparation - wireRadius) / wireRadius) / 3.141592653589793D;
/*     */   }
/*     */   
/*     */   public static double getMagnitude(Complex variable)
/*     */   {
/* 533 */     return variable.abs();
/*     */   }
/*     */   
/*     */   public static double getPhaseRad(Complex variable)
/*     */   {
/* 538 */     return variable.argRad();
/*     */   }
/*     */   
/*     */   public static double getPhaseDeg(Complex variable)
/*     */   {
/* 543 */     return variable.argDeg();
/*     */   }
/*     */   
/*     */   public static Complex polarRad(double magnitude, double phase)
/*     */   {
/* 548 */     Complex aa = new Complex();
/* 549 */     aa.polarRad(magnitude, phase);
/* 550 */     return aa;
/*     */   }
/*     */   
/*     */   public static Complex polarDeg(double magnitude, double phase)
/*     */   {
/* 555 */     Complex aa = new Complex();
/* 556 */     aa.polarDeg(magnitude, phase);
/* 557 */     return aa;
/*     */   }
/*     */   
/*     */   public static double frequencyToRadialFrequency(double frequency)
/*     */   {
/* 562 */     return 6.283185307179586D * frequency;
/*     */   }
/*     */   
/*     */   public static double radialFrequencyToFrequency(double omega)
/*     */   {
/* 567 */     return omega / 6.283185307179586D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double resistanceAluminium(double length, double area, double tempC)
/*     */   {
/* 579 */     double rho = 2.824E-8D;
/* 580 */     double alpha = 0.0039D;
/* 581 */     double resistance20 = rho * length / area;
/* 582 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceAluminum(double length, double area, double tempC)
/*     */   {
/* 587 */     double rho = 2.824E-8D;
/* 588 */     double alpha = 0.0039D;
/* 589 */     double resistance20 = rho * length / area;
/* 590 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceHardDrawnCopper(double length, double area, double tempC)
/*     */   {
/* 595 */     double rho = 1.771E-8D;
/* 596 */     double alpha = 0.00382D;
/* 597 */     double resistance20 = rho * length / area;
/* 598 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceAnnealedCopper(double length, double area, double tempC)
/*     */   {
/* 603 */     double rho = 1.7241E-8D;
/* 604 */     double alpha = 0.00393D;
/* 605 */     double resistance20 = rho * length / area;
/* 606 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceIron(double length, double area, double tempC)
/*     */   {
/* 611 */     double rho = 1.0E-7D;
/* 612 */     double alpha = 0.005D;
/* 613 */     double resistance20 = rho * length / area;
/* 614 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceManganeseSteel(double length, double area, double tempC)
/*     */   {
/* 619 */     double rho = 7.0E-7D;
/* 620 */     double alpha = 0.001D;
/* 621 */     double resistance20 = rho * length / area;
/* 622 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceSiemensMartinSteel(double length, double area, double tempC)
/*     */   {
/* 627 */     double rho = 1.8E-7D;
/* 628 */     double alpha = 0.003D;
/* 629 */     double resistance20 = rho * length / area;
/* 630 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceBBSteel(double length, double area, double tempC)
/*     */   {
/* 635 */     double rho = 1.19E-7D;
/* 636 */     double alpha = 0.004D;
/* 637 */     double resistance20 = rho * length / area;
/* 638 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceEBBSteel(double length, double area, double tempC)
/*     */   {
/* 643 */     double rho = 1.04E-7D;
/* 644 */     double alpha = 0.005D;
/* 645 */     double resistance20 = rho * length / area;
/* 646 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public static double resistanceBrass(double length, double area, double tempC)
/*     */   {
/* 652 */     double rho = 7.4E-8D;
/* 653 */     double alpha = 0.002D;
/* 654 */     double resistance20 = rho * length / area;
/* 655 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceDrawnTunsten(double length, double area, double tempC)
/*     */   {
/* 660 */     double rho = 5.6E-8D;
/* 661 */     double alpha = 0.0045D;
/* 662 */     double resistance20 = rho * length / area;
/* 663 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceSilver(double length, double area, double tempC)
/*     */   {
/* 668 */     double rho = 1.59E-8D;
/* 669 */     double alpha = 0.0038D;
/* 670 */     double resistance20 = rho * length / area;
/* 671 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceGold(double length, double area, double tempC)
/*     */   {
/* 676 */     double rho = 2.84E-8D;
/* 677 */     double alpha = 0.0034D;
/* 678 */     double resistance20 = rho * length / area;
/* 679 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistancePlatinum(double length, double area, double tempC)
/*     */   {
/* 684 */     double rho = 1.0E-7D;
/* 685 */     double alpha = 0.003D;
/* 686 */     double resistance20 = rho * length / area;
/* 687 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceNickel(double length, double area, double tempC)
/*     */   {
/* 692 */     double rho = 7.8E-8D;
/* 693 */     double alpha = 0.006D;
/* 694 */     double resistance20 = rho * length / area;
/* 695 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceMolybdenum(double length, double area, double tempC)
/*     */   {
/* 700 */     double rho = 5.7E-8D;
/* 701 */     double alpha = 0.004D;
/* 702 */     double resistance20 = rho * length / area;
/* 703 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistancePhosphorBronze(double length, double area, double tempC)
/*     */   {
/* 708 */     double rho = 1.1E-7D;
/* 709 */     double alpha = 0.0033D;
/* 710 */     double resistance20 = rho * length / area;
/* 711 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceTin(double length, double area, double tempC)
/*     */   {
/* 716 */     double rho = 1.15E-7D;
/* 717 */     double alpha = 0.0042D;
/* 718 */     double resistance20 = rho * length / area;
/* 719 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceNichrome(double length, double area, double tempC)
/*     */   {
/* 724 */     double rho = 1.0E-6D;
/* 725 */     double alpha = 4.0E-4D;
/* 726 */     double resistance20 = rho * length / area;
/* 727 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistancePalladium(double length, double area, double tempC)
/*     */   {
/* 732 */     double rho = 1.1E-7D;
/* 733 */     double alpha = 0.0033D;
/* 734 */     double resistance20 = rho * length / area;
/* 735 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceTantalum(double length, double area, double tempC)
/*     */   {
/* 740 */     double rho = 1.55E-7D;
/* 741 */     double alpha = 0.0031D;
/* 742 */     double resistance20 = rho * length / area;
/* 743 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceTherlo(double length, double area, double tempC)
/*     */   {
/* 748 */     double rho = 4.7E-7D;
/* 749 */     double alpha = 1.0E-5D;
/* 750 */     double resistance20 = rho * length / area;
/* 751 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceMonelMetal(double length, double area, double tempC)
/*     */   {
/* 756 */     double rho = 4.2E-7D;
/* 757 */     double alpha = 0.002D;
/* 758 */     double resistance20 = rho * length / area;
/* 759 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceManganan(double length, double area, double tempC)
/*     */   {
/* 764 */     double rho = 4.4E-7D;
/* 765 */     double alpha = 1.0E-5D;
/* 766 */     double resistance20 = rho * length / area;
/* 767 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceConstantan(double length, double area, double tempC)
/*     */   {
/* 772 */     double rho = 4.9E-7D;
/* 773 */     double alpha = 1.0E-5D;
/* 774 */     double resistance20 = rho * length / area;
/* 775 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public static double resistanceAntimony(double length, double area, double tempC)
/*     */   {
/* 781 */     double rho = 4.17E-7D;
/* 782 */     double alpha = 0.0036D;
/* 783 */     double resistance20 = rho * length / area;
/* 784 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceCobalt(double length, double area, double tempC)
/*     */   {
/* 789 */     double rho = 9.8E-8D;
/* 790 */     double alpha = 0.0033D;
/* 791 */     double resistance20 = rho * length / area;
/* 792 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceMagnesium(double length, double area, double tempC)
/*     */   {
/* 797 */     double rho = 4.6E-8D;
/* 798 */     double alpha = 0.004D;
/* 799 */     double resistance20 = rho * length / area;
/* 800 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceZinc(double length, double area, double tempC)
/*     */   {
/* 805 */     double rho = 5.8E-8D;
/* 806 */     double alpha = 0.0037D;
/* 807 */     double resistance20 = rho * length / area;
/* 808 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceMercury(double length, double area, double tempC)
/*     */   {
/* 813 */     double rho = 9.5738E-7D;
/* 814 */     double alpha = 8.9E-4D;
/* 815 */     double resistance20 = rho * length / area;
/* 816 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceLead(double length, double area, double tempC)
/*     */   {
/* 821 */     double rho = 2.2E-7D;
/* 822 */     double alpha = 0.0039D;
/* 823 */     double resistance20 = rho * length / area;
/* 824 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistanceGermanSilver(double length, double area, double tempC)
/*     */   {
/* 829 */     double rho = 3.3E-7D;
/* 830 */     double alpha = 4.0E-4D;
/* 831 */     double resistance20 = rho * length / area;
/* 832 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistivityToResistance(double rho, double alpha, double length, double area, double tempC)
/*     */   {
/* 837 */     double resistance20 = rho * length / area;
/* 838 */     return resistance20 * (1.0D + alpha * (tempC - 20.0D));
/*     */   }
/*     */   
/*     */   public static double resistivityToResistance(double rho, double length, double area)
/*     */   {
/* 843 */     return rho * length / area;
/*     */   }
/*     */   
/*     */   public static double resistanceToResistivity(double resistance, double length, double area)
/*     */   {
/* 848 */     return resistance * area / length;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/Impedance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */