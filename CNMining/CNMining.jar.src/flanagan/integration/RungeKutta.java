/*     */ package flanagan.integration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RungeKutta
/*     */ {
/*  50 */   private static double SAFETY = 0.9D;
/*     */   
/*     */   public static double fourthOrder(DerivFunction g, double x0, double y0, double xn, double h)
/*     */   {
/*  54 */     double k1 = 0.0D;double k2 = 0.0D;double k3 = 0.0D;double k4 = 0.0D;
/*  55 */     double x = 0.0D;double y = y0;
/*     */     
/*     */ 
/*  58 */     double ns = (xn - x0) / h;
/*  59 */     ns = Math.rint(ns);
/*  60 */     int nsteps = (int)ns;
/*  61 */     h = (xn - x0) / ns;
/*     */     
/*  63 */     for (int i = 0; i < nsteps; i++) {
/*  64 */       x = x0 + i * h;
/*     */       
/*  66 */       k1 = h * g.deriv(x, y);
/*  67 */       k2 = h * g.deriv(x + h / 2.0D, y + k1 / 2.0D);
/*  68 */       k3 = h * g.deriv(x + h / 2.0D, y + k2 / 2.0D);
/*  69 */       k4 = h * g.deriv(x + h, y + k3);
/*     */       
/*  71 */       y += k1 / 6.0D + k2 / 3.0D + k3 / 3.0D + k4 / 6.0D;
/*     */     }
/*  73 */     return y;
/*     */   }
/*     */   
/*     */   public static double[] fourthOrder(DerivnFunction g, double x0, double[] y0, double xn, double h)
/*     */   {
/*  78 */     int nequ = y0.length;
/*  79 */     double[] k1 = new double[nequ];
/*  80 */     double[] k2 = new double[nequ];
/*  81 */     double[] k3 = new double[nequ];
/*  82 */     double[] k4 = new double[nequ];
/*  83 */     double[] y = new double[nequ];
/*  84 */     double[] yd = new double[nequ];
/*  85 */     double[] dydx = new double[nequ];
/*  86 */     double x = 0.0D;
/*     */     
/*     */ 
/*  89 */     double ns = (xn - x0) / h;
/*  90 */     ns = Math.rint(ns);
/*  91 */     int nsteps = (int)ns;
/*  92 */     h = (xn - x0) / ns;
/*     */     
/*     */ 
/*  95 */     for (int i = 0; i < nequ; i++) { y[i] = y0[i];
/*     */     }
/*     */     
/*  98 */     for (int j = 0; j < nsteps; j++) {
/*  99 */       x = x0 + j * h;
/* 100 */       dydx = g.derivn(x, y);
/* 101 */       for (int i = 0; i < nequ; i++) { k1[i] = (h * dydx[i]);
/*     */       }
/* 103 */       for (int i = 0; i < nequ; i++) y[i] += k1[i] / 2.0D;
/* 104 */       dydx = g.derivn(x + h / 2.0D, yd);
/* 105 */       for (int i = 0; i < nequ; i++) { k2[i] = (h * dydx[i]);
/*     */       }
/* 107 */       for (int i = 0; i < nequ; i++) y[i] += k2[i] / 2.0D;
/* 108 */       dydx = g.derivn(x + h / 2.0D, yd);
/* 109 */       for (int i = 0; i < nequ; i++) { k3[i] = (h * dydx[i]);
/*     */       }
/* 111 */       for (int i = 0; i < nequ; i++) y[i] += k3[i];
/* 112 */       dydx = g.derivn(x + h, yd);
/* 113 */       for (int i = 0; i < nequ; i++) { k4[i] = (h * dydx[i]);
/*     */       }
/* 115 */       for (int i = 0; i < nequ; i++) { y[i] += k1[i] / 6.0D + k2[i] / 3.0D + k3[i] / 3.0D + k4[i] / 6.0D;
/*     */       }
/*     */     }
/* 118 */     return y;
/*     */   }
/*     */   
/*     */   public static double cashKarp(DerivFunction g, double x0, double y0, double xn, double h, double abstol, double reltol, int maxiter)
/*     */   {
/* 123 */     double k1 = 0.0D;double k2 = 0.0D;double k3 = 0.0D;double k4 = 0.0D;double k5 = 0.0D;double k6 = 0.0D;
/* 124 */     double y = y0;double y6 = 0.0D;double y5 = 0.0D;double yd = 0.0D;double dydx = 0.0D;
/* 125 */     double x = x0;double err = 0.0D;double delta = 0.0D;double tol = 0.0D;
/* 126 */     int i = 0;
/*     */     
/* 128 */     while (x < xn) {
/* 129 */       i++;
/* 130 */       if (i > maxiter) throw new ArithmeticException("Maximum number of iterations exceeded");
/* 131 */       dydx = g.deriv(x, y);
/* 132 */       k1 = h * dydx;
/*     */       
/* 134 */       yd = y + k1 / 5.0D;
/* 135 */       dydx = g.deriv(x + h / 5.0D, yd);
/* 136 */       k2 = h * dydx;
/*     */       
/* 138 */       yd = y + (3.0D * k1 + 9.0D * k2) / 40.0D;
/* 139 */       dydx = g.deriv(x + 3.0D * h / 10.0D, yd);
/* 140 */       k3 = h * dydx;
/*     */       
/* 142 */       yd = y + (3.0D * k1 - 9.0D * k2 + 12.0D * k3) / 10.0D;
/* 143 */       dydx = g.deriv(x + 3.0D * h / 5.0D, yd);
/* 144 */       k4 = h * dydx;
/*     */       
/* 146 */       yd = y - 11.0D * k1 / 54.0D + 5.0D * k2 / 2.0D - 70.0D * k3 / 27.0D + 35.0D * k4 / 27.0D;
/* 147 */       dydx = g.deriv(x + h, yd);
/* 148 */       k5 = h * dydx;
/*     */       
/* 150 */       yd = y + 1631.0D * k1 / 55296.0D + 175.0D * k2 / 512.0D + 575.0D * k3 / 13824.0D + 44275.0D * k4 / 110592.0D + 253.0D * k5 / 4096.0D;
/* 151 */       dydx = g.deriv(x + 7.0D * h / 8.0D, yd);
/* 152 */       k6 = h * dydx;
/*     */       
/* 154 */       y5 = y + 2825.0D * k1 / 27648.0D + 18575.0D * k3 / 48384.0D + 13525.0D * k4 / 55296.0D + 277.0D * k5 / 14336.0D + k6 / 4.0D;
/* 155 */       y6 = y + 37.0D * k1 / 378.0D + 250.0D * k3 / 621.0D + 125.0D * k4 / 594.0D + 512.0D * k6 / 1771.0D;
/* 156 */       err = Math.abs(y6 - y5);
/* 157 */       tol = err / (Math.abs(y5) * reltol + abstol);
/* 158 */       if (tol <= 1.0D) {
/* 159 */         x += h;
/* 160 */         delta = SAFETY * Math.pow(tol, -0.2D);
/* 161 */         if (delta > 4.0D) {
/* 162 */           h *= 4.0D;
/* 163 */         } else if (delta > 1.0D) {
/* 164 */           h *= delta;
/*     */         }
/* 166 */         if (x + h > xn) h = xn - x;
/* 167 */         y = y5;
/*     */       }
/*     */       else {
/* 170 */         delta = SAFETY * Math.pow(tol, -0.25D);
/* 171 */         if (delta < 0.1D) h *= 0.1D; else
/* 172 */           h *= delta;
/*     */       }
/*     */     }
/* 175 */     return y;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double cashKarp(DerivFunction g, double x0, double y0, double xn, double h, double abstol, double reltol)
/*     */   {
/* 181 */     double nsteps = (xn - x0) / h;
/* 182 */     int maxiter = (int)nsteps * 100;
/*     */     
/* 184 */     return cashKarp(g, x0, y0, xn, h, abstol, reltol, maxiter);
/*     */   }
/*     */   
/*     */ 
/*     */   public static double[] cashKarp(DerivnFunction g, double x0, double[] y0, double xn, double h, double abstol, double reltol, int maxiter)
/*     */   {
/* 190 */     int nequ = y0.length;
/* 191 */     double[] k1 = new double[nequ];
/* 192 */     double[] k2 = new double[nequ];
/* 193 */     double[] k3 = new double[nequ];
/* 194 */     double[] k4 = new double[nequ];
/* 195 */     double[] k5 = new double[nequ];
/* 196 */     double[] k6 = new double[nequ];
/* 197 */     double[] y = new double[nequ];
/* 198 */     double[] y6 = new double[nequ];
/* 199 */     double[] y5 = new double[nequ];
/* 200 */     double[] yd = new double[nequ];
/* 201 */     double[] dydx = new double[nequ];
/*     */     
/* 203 */     double x = 0.0D;double err = 0.0D;double maxerr = 0.0D;double delta = 0.0D;double tol = 1.0D;
/* 204 */     int ii = 0;
/*     */     
/*     */ 
/* 207 */     for (int i = 0; i < nequ; i++) y[i] = y0[i];
/* 208 */     x = x0;
/*     */     
/* 210 */     while (x < xn) {
/* 211 */       ii++;
/* 212 */       if (ii > maxiter) { throw new ArithmeticException("Maximum number of iterations exceeded");
/*     */       }
/* 214 */       dydx = g.derivn(x, y);
/* 215 */       for (int i = 0; i < nequ; i++) { k1[i] = (h * dydx[i]);
/*     */       }
/* 217 */       for (int i = 0; i < nequ; i++) y[i] += k1[i] / 5.0D;
/* 218 */       dydx = g.derivn(x + h / 5.0D, yd);
/* 219 */       for (int i = 0; i < nequ; i++) { k2[i] = (h * dydx[i]);
/*     */       }
/* 221 */       for (int i = 0; i < nequ; i++) y[i] += (3.0D * k1[i] + 9.0D * k2[i]) / 40.0D;
/* 222 */       dydx = g.derivn(x + 3.0D * h / 10.0D, yd);
/* 223 */       for (int i = 0; i < nequ; i++) { k3[i] = (h * dydx[i]);
/*     */       }
/* 225 */       for (int i = 0; i < nequ; i++) y[i] += (3.0D * k1[i] - 9.0D * k2[i] + 12.0D * k3[i]) / 10.0D;
/* 226 */       dydx = g.derivn(x + 3.0D * h / 5.0D, yd);
/* 227 */       for (int i = 0; i < nequ; i++) { k4[i] = (h * dydx[i]);
/*     */       }
/* 229 */       for (int i = 0; i < nequ; i++) yd[i] = (y[i] - 11.0D * k1[i] / 54.0D + 5.0D * k2[i] / 2.0D - 70.0D * k3[i] / 27.0D + 35.0D * k4[i] / 27.0D);
/* 230 */       dydx = g.derivn(x + h, yd);
/* 231 */       for (int i = 0; i < nequ; i++) { k5[i] = (h * dydx[i]);
/*     */       }
/* 233 */       for (int i = 0; i < nequ; i++) yd[i] = (y[i] + 1631.0D * k1[i] / 55296.0D + 175.0D * k2[i] / 512.0D + 575.0D * k3[i] / 13824.0D + 44275.0D * k4[i] / 110592.0D + 253.0D * k5[i] / 4096.0D);
/* 234 */       dydx = g.derivn(x + 7.0D * h / 8.0D, yd);
/* 235 */       for (int i = 0; i < nequ; i++) { k6[i] = (h * dydx[i]);
/*     */       }
/* 237 */       maxerr = 0.0D;
/* 238 */       for (int i = 0; i < nequ; i++) {
/* 239 */         y5[i] = (y[i] + 2825.0D * k1[i] / 27648.0D + 18575.0D * k3[i] / 48384.0D + 13525.0D * k4[i] / 55296.0D + 277.0D * k5[i] / 14336.0D + k6[i] / 4.0D);
/* 240 */         y6[i] = (y[i] + 37.0D * k1[i] / 378.0D + 250.0D * k3[i] / 621.0D + 125.0D * k4[i] / 594.0D + 512.0D * k6[i] / 1771.0D);
/* 241 */         err = Math.abs(y6[i] - y5[i]);
/* 242 */         tol = Math.abs(y5[i]) * reltol + abstol;
/* 243 */         maxerr = Math.max(maxerr, err / tol);
/*     */       }
/* 245 */       if (maxerr <= 1.0D) {
/* 246 */         x += h;
/* 247 */         delta = SAFETY * Math.pow(maxerr, -0.2D);
/* 248 */         if (delta > 4.0D) {
/* 249 */           h *= 4.0D;
/*     */         }
/* 251 */         else if (delta > 1.0D) {
/* 252 */           h *= delta;
/*     */         }
/* 254 */         if (x + h > xn) h = xn - x;
/* 255 */         y = (double[])y5.clone();
/*     */       }
/*     */       else {
/* 258 */         delta = SAFETY * Math.pow(maxerr, -0.25D);
/* 259 */         if (delta < 0.1D) h *= 0.1D; else
/* 260 */           h *= delta;
/*     */       }
/*     */     }
/* 263 */     return y;
/*     */   }
/*     */   
/*     */   public static double[] cashKarp(DerivnFunction g, double x0, double[] y0, double xn, double h, double abstol, double reltol)
/*     */   {
/* 268 */     double nsteps = (xn - x0) / h;
/* 269 */     int maxiter = (int)nsteps * 100;
/*     */     
/* 271 */     return cashKarp(g, x0, y0, xn, h, abstol, reltol, maxiter);
/*     */   }
/*     */   
/*     */   public static double fehlberg(DerivFunction g, double x0, double y0, double xn, double h, double abstol, double reltol, int maxiter)
/*     */   {
/* 276 */     double k1 = 0.0D;double k2 = 0.0D;double k3 = 0.0D;double k4 = 0.0D;double k5 = 0.0D;double k6 = 0.0D;
/* 277 */     double x = x0;double y = y0;double y5 = 0.0D;double y6 = 0.0D;double err = 0.0D;double delta = 0.0D;double tol = 0.0D;
/* 278 */     int i = 0;
/*     */     
/* 280 */     while (x < xn) {
/* 281 */       i++;
/* 282 */       if (i > maxiter) throw new ArithmeticException("Maximum number of iterations exceeded");
/* 283 */       k1 = h * g.deriv(x, y);
/* 284 */       k2 = h * g.deriv(x + h / 4.0D, y + k1 / 4.0D);
/* 285 */       k3 = h * g.deriv(x + 3.0D * h / 8.0D, y + (3.0D * k1 + 9.0D * k2) / 32.0D);
/* 286 */       k4 = h * g.deriv(x + 12.0D * h / 13.0D, y + (1932.0D * k1 - 7200.0D * k2 + 7296.0D * k3) / 2197.0D);
/* 287 */       k5 = h * g.deriv(x + h, y + 439.0D * k1 / 216.0D - 8.0D * k2 + 3680.0D * k3 / 513.0D - 845.0D * k4 / 4104.0D);
/* 288 */       k6 = h * g.deriv(x + 0.5D * h, y - 8.0D * k1 / 27.0D + 2.0D * k2 - 3544.0D * k3 / 2565.0D + 1859.0D * k4 / 4104.0D - 11.0D * k5 / 40.0D);
/*     */       
/* 290 */       y5 = y + 25.0D * k1 / 216.0D + 1408.0D * k3 / 2565.0D + 2197.0D * k4 / 4104.0D - k5 / 5.0D;
/* 291 */       y6 = y + 16.0D * k1 / 135.0D + 6656.0D * k3 / 12825.0D + 28561.0D * k4 / 56430.0D - 9.0D * k5 / 50.0D + 2.0D * k6 / 55.0D;
/* 292 */       err = Math.abs(y6 - y5);
/* 293 */       tol = err / (Math.abs(y5) * reltol + abstol);
/* 294 */       if (tol <= 1.0D) {
/* 295 */         x += h;
/* 296 */         delta = SAFETY * Math.pow(tol, -0.2D);
/* 297 */         if (delta > 4.0D) {
/* 298 */           h *= 4.0D;
/* 299 */         } else if (delta < 1.0D) {
/* 300 */           h *= delta;
/*     */         }
/* 302 */         if (x + h > xn) h = xn - x;
/* 303 */         y = y5;
/*     */       }
/*     */       else {
/* 306 */         delta = SAFETY * Math.pow(tol, -0.25D);
/* 307 */         if (delta < 0.1D) h *= 0.1D; else
/* 308 */           h *= delta;
/*     */       }
/*     */     }
/* 311 */     return y;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double fehlberg(DerivFunction g, double x0, double y0, double xn, double h, double abstol, double reltol)
/*     */   {
/* 317 */     double nsteps = (xn - x0) / h;
/* 318 */     int maxiter = (int)nsteps * 100;
/*     */     
/* 320 */     return fehlberg(g, x0, y0, xn, h, abstol, reltol, maxiter);
/*     */   }
/*     */   
/*     */   public static double[] fehlberg(DerivnFunction g, double x0, double[] y0, double xn, double h, double abstol, double reltol, int maxiter)
/*     */   {
/* 325 */     int nequ = y0.length;
/* 326 */     double[] k1 = new double[nequ];
/* 327 */     double[] k2 = new double[nequ];
/* 328 */     double[] k3 = new double[nequ];
/* 329 */     double[] k4 = new double[nequ];
/* 330 */     double[] k5 = new double[nequ];
/* 331 */     double[] k6 = new double[nequ];
/* 332 */     double[] y = new double[nequ];
/* 333 */     double[] y6 = new double[nequ];
/* 334 */     double[] y5 = new double[nequ];
/* 335 */     double[] yd = new double[nequ];
/* 336 */     double[] dydx = new double[nequ];
/*     */     
/* 338 */     double x = x0;double err = 0.0D;double maxerr = 0.0D;double delta = 0.0D;double tol = 1.0D;
/* 339 */     int ii = 0;
/*     */     
/*     */ 
/* 342 */     for (int i = 0; i < nequ; i++) { y[i] = y0[i];
/*     */     }
/* 344 */     while (x < xn) {
/* 345 */       ii++;
/* 346 */       if (ii > maxiter) throw new ArithmeticException("Maximum number of iterations exceeded");
/* 347 */       dydx = g.derivn(x, y);
/* 348 */       for (int i = 0; i < nequ; i++) { k1[i] = (h * dydx[i]);
/*     */       }
/* 350 */       for (int i = 0; i < nequ; i++) y[i] += k1[i] / 4.0D;
/* 351 */       dydx = g.derivn(x + h / 4.0D, yd);
/* 352 */       for (int i = 0; i < nequ; i++) { k2[i] = (h * dydx[i]);
/*     */       }
/* 354 */       for (int i = 0; i < nequ; i++) y[i] += (3.0D * k1[i] + 9.0D * k2[i]) / 32.0D;
/* 355 */       dydx = g.derivn(x + 3.0D * h / 8.0D, yd);
/* 356 */       for (int i = 0; i < nequ; i++) { k3[i] = (h * dydx[i]);
/*     */       }
/* 358 */       for (int i = 0; i < nequ; i++) y[i] += (1932.0D * k1[i] - 7200.0D * k2[i] + 7296.0D * k3[i]) / 2197.0D;
/* 359 */       dydx = g.derivn(x + 12.0D * h / 13.0D, yd);
/* 360 */       for (int i = 0; i < nequ; i++) { k4[i] = (h * dydx[i]);
/*     */       }
/* 362 */       for (int i = 0; i < nequ; i++) yd[i] = (y[i] + 439.0D * k1[i] / 216.0D - 8.0D * k2[i] + 3680.0D * k3[i] / 513.0D - 845.0D * k4[i] / 4104.0D);
/* 363 */       dydx = g.derivn(x + h, yd);
/* 364 */       for (int i = 0; i < nequ; i++) { k5[i] = (h * dydx[i]);
/*     */       }
/* 366 */       for (int i = 0; i < nequ; i++) yd[i] = (y[i] - 8.0D * k1[i] / 27.0D + 2.0D * k2[i] - 3544.0D * k3[i] / 2565.0D + 1859.0D * k4[i] / 4104.0D - 11.0D * k5[i] / 40.0D);
/* 367 */       dydx = g.derivn(x + 0.5D * h, yd);
/* 368 */       for (int i = 0; i < nequ; i++) { k6[i] = (h * dydx[i]);
/*     */       }
/* 370 */       maxerr = 0.0D;
/* 371 */       for (int i = 0; i < nequ; i++) {
/* 372 */         y5[i] = (y[i] + 25.0D * k1[i] / 216.0D + 1408.0D * k3[i] / 2565.0D + 2197.0D * k4[i] / 4104.0D - k5[i] / 5.0D);
/* 373 */         y6[i] = (y[i] + 16.0D * k1[i] / 135.0D + 6656.0D * k3[i] / 12825.0D + 28561.0D * k4[i] / 56430.0D - 9.0D * k5[i] / 50.0D + 2.0D * k6[i] / 55.0D);
/* 374 */         err = Math.abs(y6[i] - y5[i]);
/* 375 */         tol = y5[i] * reltol + abstol;
/* 376 */         maxerr = Math.max(maxerr, err / tol);
/*     */       }
/*     */       
/* 379 */       if (maxerr <= 1.0D) {
/* 380 */         x += h;
/* 381 */         delta = SAFETY * Math.pow(maxerr, -0.2D);
/* 382 */         if (delta > 4.0D) {
/* 383 */           h *= 4.0D;
/*     */         }
/* 385 */         else if (delta > 1.0D) {
/* 386 */           h *= delta;
/*     */         }
/* 388 */         if (x + h > xn) h = xn - x;
/* 389 */         y = (double[])y5.clone();
/*     */       }
/*     */       else {
/* 392 */         delta = SAFETY * Math.pow(maxerr, -0.25D);
/* 393 */         if (delta < 0.1D) h *= 0.1D; else
/* 394 */           h *= delta;
/*     */       }
/*     */     }
/* 397 */     return y;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double[] fehlberg(DerivnFunction g, double x0, double[] y0, double xn, double h, double abstol, double reltol)
/*     */   {
/* 403 */     double nsteps = (xn - x0) / h;
/* 404 */     int maxiter = (int)nsteps * 100;
/*     */     
/* 406 */     return fehlberg(g, x0, y0, xn, h, abstol, reltol, maxiter);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/integration/RungeKutta.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */