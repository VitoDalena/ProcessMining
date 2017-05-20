/*     */ package flanagan.integration;
/*     */ 
/*     */ import flanagan.math.Fmath;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Integration
/*     */ {
/*  49 */   private IntegralFunction integralFunc = null;
/*  50 */   private boolean setFunction = false;
/*  51 */   private double lowerLimit = NaN.0D;
/*  52 */   private double upperLimit = NaN.0D;
/*  53 */   private boolean setLimits = false;
/*     */   
/*  55 */   private int glPoints = 0;
/*  56 */   private boolean setGLpoints = false;
/*  57 */   private int nIntervals = 0;
/*  58 */   private boolean setIntervals = false;
/*     */   
/*  60 */   private double integralSum = 0.0D;
/*  61 */   private boolean setIntegration = false;
/*     */   
/*     */ 
/*  64 */   private static ArrayList<Integer> gaussQuadIndex = new ArrayList();
/*  65 */   private static ArrayList<double[]> gaussQuadDistArrayList = new ArrayList();
/*  66 */   private static ArrayList<double[]> gaussQuadWeightArrayList = new ArrayList();
/*     */   
/*     */ 
/*  69 */   private double requiredAccuracy = 0.0D;
/*  70 */   private double trapeziumAccuracy = 0.0D;
/*  71 */   private static double trapAccuracy = 0.0D;
/*  72 */   private int maxIntervals = 0;
/*  73 */   private int trapeziumIntervals = 1;
/*  74 */   private static int trapIntervals = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   public Integration() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Integration(IntegralFunction intFunc)
/*     */   {
/*  84 */     this.integralFunc = intFunc;
/*  85 */     this.setFunction = true;
/*     */   }
/*     */   
/*     */   public Integration(IntegralFunction intFunc, double lowerLimit, double upperLimit)
/*     */   {
/*  90 */     this.integralFunc = intFunc;
/*  91 */     this.setFunction = true;
/*  92 */     this.lowerLimit = lowerLimit;
/*  93 */     this.upperLimit = upperLimit;
/*  94 */     this.setLimits = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setIntegrationFunction(IntegralFunction intFunc)
/*     */   {
/* 101 */     this.integralFunc = intFunc;
/* 102 */     this.setFunction = true;
/*     */   }
/*     */   
/*     */   public void setLimits(double lowerLimit, double upperLimit)
/*     */   {
/* 107 */     this.lowerLimit = lowerLimit;
/* 108 */     this.upperLimit = upperLimit;
/* 109 */     this.setLimits = true;
/*     */   }
/*     */   
/*     */   public void setLowerLimit(double lowerLimit)
/*     */   {
/* 114 */     this.lowerLimit = lowerLimit;
/* 115 */     if (!Fmath.isNaN(this.upperLimit)) this.setLimits = true;
/*     */   }
/*     */   
/*     */   public void setlowerLimit(double lowerLimit)
/*     */   {
/* 120 */     this.lowerLimit = lowerLimit;
/* 121 */     if (!Fmath.isNaN(this.upperLimit)) this.setLimits = true;
/*     */   }
/*     */   
/*     */   public void setUpperLimit(double upperLimit)
/*     */   {
/* 126 */     this.upperLimit = upperLimit;
/* 127 */     if (!Fmath.isNaN(this.lowerLimit)) this.setLimits = true;
/*     */   }
/*     */   
/*     */   public void setupperLimit(double upperLimit)
/*     */   {
/* 132 */     this.upperLimit = upperLimit;
/* 133 */     if (!Fmath.isNaN(this.lowerLimit)) this.setLimits = true;
/*     */   }
/*     */   
/*     */   public void setGLpoints(int nPoints)
/*     */   {
/* 138 */     this.glPoints = nPoints;
/* 139 */     this.setGLpoints = true;
/*     */   }
/*     */   
/*     */   public void setNintervals(int nIntervals)
/*     */   {
/* 144 */     this.nIntervals = nIntervals;
/* 145 */     this.setIntervals = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getIntegralSum()
/*     */   {
/* 152 */     if (!this.setIntegration) throw new IllegalArgumentException("No integration has been performed");
/* 153 */     return this.integralSum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double gaussQuad()
/*     */   {
/* 161 */     if (!this.setGLpoints) throw new IllegalArgumentException("Number of points not set");
/* 162 */     if (!this.setLimits) throw new IllegalArgumentException("One limit or both limits not set");
/* 163 */     if (!this.setFunction) { throw new IllegalArgumentException("No integral function has been set");
/*     */     }
/* 165 */     double[] gaussQuadDist = new double[this.glPoints];
/* 166 */     double[] gaussQuadWeight = new double[this.glPoints];
/* 167 */     double sum = 0.0D;
/* 168 */     double xplus = 0.5D * (this.upperLimit + this.lowerLimit);
/* 169 */     double xminus = 0.5D * (this.upperLimit - this.lowerLimit);
/* 170 */     double dx = 0.0D;
/* 171 */     boolean test = true;
/* 172 */     int k = -1;int kn = -1;
/*     */     
/*     */ 
/*     */ 
/* 176 */     if (!gaussQuadIndex.isEmpty()) {
/* 177 */       for (k = 0; k < gaussQuadIndex.size(); k++) {
/* 178 */         Integer ki = (Integer)gaussQuadIndex.get(k);
/* 179 */         if (ki.intValue() == this.glPoints) {
/* 180 */           test = false;
/* 181 */           kn = k;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 186 */     if (test)
/*     */     {
/* 188 */       gaussQuadCoeff(gaussQuadDist, gaussQuadWeight, this.glPoints);
/* 189 */       gaussQuadIndex.add(new Integer(this.glPoints));
/* 190 */       gaussQuadDistArrayList.add(gaussQuadDist);
/* 191 */       gaussQuadWeightArrayList.add(gaussQuadWeight);
/*     */     }
/*     */     else
/*     */     {
/* 195 */       gaussQuadDist = (double[])gaussQuadDistArrayList.get(kn);
/* 196 */       gaussQuadWeight = (double[])gaussQuadWeightArrayList.get(kn);
/*     */     }
/*     */     
/*     */ 
/* 200 */     for (int i = 0; i < this.glPoints; i++) {
/* 201 */       dx = xminus * gaussQuadDist[i];
/* 202 */       sum += gaussQuadWeight[i] * this.integralFunc.function(xplus + dx);
/*     */     }
/* 204 */     this.integralSum = (sum * xminus);
/* 205 */     this.setIntegration = true;
/* 206 */     return this.integralSum;
/*     */   }
/*     */   
/*     */ 
/*     */   public double gaussQuad(int glPoints)
/*     */   {
/* 212 */     this.glPoints = glPoints;
/* 213 */     this.setGLpoints = true;
/* 214 */     return gaussQuad();
/*     */   }
/*     */   
/*     */ 
/*     */   public static double gaussQuad(IntegralFunction intFunc, double lowerLimit, double upperLimit, int glPoints)
/*     */   {
/* 220 */     Integration intgrtn = new Integration(intFunc, lowerLimit, upperLimit);
/* 221 */     return intgrtn.gaussQuad(glPoints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void gaussQuadCoeff(double[] gaussQuadDist, double[] gaussQuadWeight, int n)
/*     */   {
/* 230 */     double z = 0.0D;double z1 = 0.0D;
/* 231 */     double pp = 0.0D;double p1 = 0.0D;double p2 = 0.0D;double p3 = 0.0D;
/*     */     
/* 233 */     double eps = 3.0E-11D;
/* 234 */     double x1 = -1.0D;
/* 235 */     double x2 = 1.0D;
/*     */     
/*     */ 
/*     */ 
/* 239 */     int m = (n + 1) / 2;
/* 240 */     double xm = 0.5D * (x2 + x1);
/* 241 */     double xl = 0.5D * (x2 - x1);
/*     */     
/*     */ 
/* 244 */     for (int i = 1; i <= m; i++)
/*     */     {
/* 246 */       z = Math.cos(3.141592653589793D * (i - 0.25D) / (n + 0.5D));
/*     */       
/*     */       do
/*     */       {
/* 250 */         p1 = 1.0D;
/* 251 */         p2 = 0.0D;
/*     */         
/*     */ 
/*     */ 
/* 255 */         for (int j = 1; j <= n; j++) {
/* 256 */           p3 = p2;
/* 257 */           p2 = p1;
/* 258 */           p1 = ((2.0D * j - 1.0D) * z * p2 - (j - 1.0D) * p3) / j;
/*     */         }
/* 260 */         pp = n * (z * p1 - p2) / (z * z - 1.0D);
/* 261 */         z1 = z;
/* 262 */         z = z1 - p1 / pp;
/* 263 */       } while (Math.abs(z - z1) > eps);
/*     */       
/* 265 */       gaussQuadDist[(i - 1)] = (xm - xl * z);
/* 266 */       gaussQuadDist[(n - i)] = (xm + xl * z);
/* 267 */       gaussQuadWeight[(i - 1)] = (2.0D * xl / ((1.0D - z * z) * pp * pp));
/* 268 */       gaussQuadWeight[(n - i)] = gaussQuadWeight[(i - 1)];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double trapezium()
/*     */   {
/* 277 */     if (!this.setIntervals) throw new IllegalArgumentException("Number of intervals not set");
/* 278 */     if (!this.setLimits) throw new IllegalArgumentException("One limit or both limits not set");
/* 279 */     if (!this.setFunction) { throw new IllegalArgumentException("No integral function has been set");
/*     */     }
/* 281 */     double y1 = 0.0D;
/* 282 */     double interval = (this.upperLimit - this.lowerLimit) / this.nIntervals;
/* 283 */     double x0 = this.lowerLimit;
/* 284 */     double x1 = this.lowerLimit + interval;
/* 285 */     double y0 = this.integralFunc.function(x0);
/* 286 */     this.integralSum = 0.0D;
/*     */     
/* 288 */     for (int i = 0; i < this.nIntervals; i++)
/*     */     {
/* 290 */       if (x1 > this.upperLimit) {
/* 291 */         x1 = this.upperLimit;
/* 292 */         interval -= x1 - this.upperLimit;
/*     */       }
/*     */       
/*     */ 
/* 296 */       y1 = this.integralFunc.function(x1);
/* 297 */       this.integralSum += 0.5D * (y0 + y1) * interval;
/* 298 */       x0 = x1;
/* 299 */       y0 = y1;
/* 300 */       x1 += interval;
/*     */     }
/* 302 */     this.setIntegration = true;
/* 303 */     return this.integralSum;
/*     */   }
/*     */   
/*     */ 
/*     */   public double trapezium(int nIntervals)
/*     */   {
/* 309 */     this.nIntervals = nIntervals;
/* 310 */     this.setIntervals = true;
/* 311 */     return trapezium();
/*     */   }
/*     */   
/*     */ 
/*     */   public static double trapezium(IntegralFunction intFunc, double lowerLimit, double upperLimit, int nIntervals)
/*     */   {
/* 317 */     Integration intgrtn = new Integration(intFunc, lowerLimit, upperLimit);
/* 318 */     return intgrtn.trapezium(nIntervals);
/*     */   }
/*     */   
/*     */ 
/*     */   public double trapezium(double accuracy, int maxIntervals)
/*     */   {
/* 324 */     this.requiredAccuracy = accuracy;
/* 325 */     this.maxIntervals = maxIntervals;
/* 326 */     this.trapeziumIntervals = 1;
/*     */     
/* 328 */     double summ = trapezium(this.integralFunc, this.lowerLimit, this.upperLimit, 1);
/* 329 */     double oldSumm = summ;
/* 330 */     int i = 2;
/* 331 */     for (i = 2; i <= this.maxIntervals; i++) {
/* 332 */       summ = trapezium(this.integralFunc, this.lowerLimit, this.upperLimit, i);
/* 333 */       this.trapeziumAccuracy = Math.abs((summ - oldSumm) / oldSumm);
/* 334 */       if (this.trapeziumAccuracy <= this.requiredAccuracy) break;
/* 335 */       oldSumm = summ;
/*     */     }
/*     */     
/* 338 */     if (i > this.maxIntervals) {
/* 339 */       System.out.println("accuracy criterion was not met in Integration.trapezium - current sum was returned as result.");
/* 340 */       this.trapeziumIntervals = this.maxIntervals;
/*     */     }
/*     */     else {
/* 343 */       this.trapeziumIntervals = i;
/*     */     }
/* 345 */     trapIntervals = this.trapeziumIntervals;
/* 346 */     trapAccuracy = this.trapeziumAccuracy;
/* 347 */     return summ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double trapezium(IntegralFunction intFunc, double lowerLimit, double upperLimit, double accuracy, int maxIntervals)
/*     */   {
/* 354 */     Integration intgrtn = new Integration(intFunc, lowerLimit, upperLimit);
/* 355 */     return intgrtn.trapezium(accuracy, maxIntervals);
/*     */   }
/*     */   
/*     */   public int getTrapeziumIntervals()
/*     */   {
/* 360 */     return this.trapeziumIntervals;
/*     */   }
/*     */   
/*     */   public static int getTrapIntervals()
/*     */   {
/* 365 */     return trapIntervals;
/*     */   }
/*     */   
/*     */   public double getTrapeziumAccuracy()
/*     */   {
/* 370 */     return this.trapeziumAccuracy;
/*     */   }
/*     */   
/*     */   public static double getTrapAccuracy()
/*     */   {
/* 375 */     return trapAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double backward()
/*     */   {
/* 383 */     if (!this.setIntervals) throw new IllegalArgumentException("Number of intervals not set");
/* 384 */     if (!this.setLimits) throw new IllegalArgumentException("One limit or both limits not set");
/* 385 */     if (!this.setFunction) { throw new IllegalArgumentException("No integral function has been set");
/*     */     }
/* 387 */     double interval = (this.upperLimit - this.lowerLimit) / this.nIntervals;
/* 388 */     double x = this.lowerLimit + interval;
/* 389 */     double y = this.integralFunc.function(x);
/* 390 */     this.integralSum = 0.0D;
/*     */     
/* 392 */     for (int i = 0; i < this.nIntervals; i++)
/*     */     {
/* 394 */       if (x > this.upperLimit) {
/* 395 */         x = this.upperLimit;
/* 396 */         interval -= x - this.upperLimit;
/*     */       }
/*     */       
/*     */ 
/* 400 */       y = this.integralFunc.function(x);
/* 401 */       this.integralSum += y * interval;
/* 402 */       x += interval;
/*     */     }
/*     */     
/* 405 */     this.setIntegration = true;
/* 406 */     return this.integralSum;
/*     */   }
/*     */   
/*     */ 
/*     */   public double backward(int nIntervals)
/*     */   {
/* 412 */     this.nIntervals = nIntervals;
/* 413 */     this.setIntervals = true;
/* 414 */     return backward();
/*     */   }
/*     */   
/*     */ 
/*     */   public static double backward(IntegralFunction intFunc, double lowerLimit, double upperLimit, int nIntervals)
/*     */   {
/* 420 */     Integration intgrtn = new Integration(intFunc, lowerLimit, upperLimit);
/* 421 */     return intgrtn.backward(nIntervals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double forward()
/*     */   {
/* 430 */     double interval = (this.upperLimit - this.lowerLimit) / this.nIntervals;
/* 431 */     double x = this.lowerLimit;
/* 432 */     double y = this.integralFunc.function(x);
/* 433 */     this.integralSum = 0.0D;
/*     */     
/* 435 */     for (int i = 0; i < this.nIntervals; i++)
/*     */     {
/* 437 */       if (x > this.upperLimit) {
/* 438 */         x = this.upperLimit;
/* 439 */         interval -= x - this.upperLimit;
/*     */       }
/*     */       
/*     */ 
/* 443 */       y = this.integralFunc.function(x);
/* 444 */       this.integralSum += y * interval;
/* 445 */       x += interval;
/*     */     }
/* 447 */     this.setIntegration = true;
/* 448 */     return this.integralSum;
/*     */   }
/*     */   
/*     */ 
/*     */   public double forward(int nIntervals)
/*     */   {
/* 454 */     this.nIntervals = nIntervals;
/* 455 */     this.setIntervals = true;
/* 456 */     return forward();
/*     */   }
/*     */   
/*     */ 
/*     */   public static double forward(IntegralFunction integralFunc, double lowerLimit, double upperLimit, int nIntervals)
/*     */   {
/* 462 */     Integration intgrtn = new Integration(integralFunc, lowerLimit, upperLimit);
/* 463 */     return intgrtn.forward(nIntervals);
/*     */   }
/*     */   
/*     */   public static double foreward(IntegralFunction integralFunc, double lowerLimit, double upperLimit, int nIntervals) {
/* 467 */     Integration intgrtn = new Integration(integralFunc, lowerLimit, upperLimit);
/* 468 */     return intgrtn.forward(nIntervals);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/integration/Integration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */