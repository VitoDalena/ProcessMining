/*     */ package org.apache.commons.math.stat.regression;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.distribution.TDistribution;
/*     */ import org.apache.commons.math.distribution.TDistributionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleRegression
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3004689053607543335L;
/*     */   private TDistribution distribution;
/*  64 */   private double sumX = 0.0D;
/*     */   
/*     */ 
/*  67 */   private double sumXX = 0.0D;
/*     */   
/*     */ 
/*  70 */   private double sumY = 0.0D;
/*     */   
/*     */ 
/*  73 */   private double sumYY = 0.0D;
/*     */   
/*     */ 
/*  76 */   private double sumXY = 0.0D;
/*     */   
/*     */ 
/*  79 */   private long n = 0L;
/*     */   
/*     */ 
/*  82 */   private double xbar = 0.0D;
/*     */   
/*     */ 
/*  85 */   private double ybar = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SimpleRegression()
/*     */   {
/*  93 */     this(new TDistributionImpl(1.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SimpleRegression(TDistribution t)
/*     */   {
/* 104 */     setDistribution(t);
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
/*     */   public void addData(double x, double y)
/*     */   {
/* 121 */     if (this.n == 0L) {
/* 122 */       this.xbar = x;
/* 123 */       this.ybar = y;
/*     */     } else {
/* 125 */       double dx = x - this.xbar;
/* 126 */       double dy = y - this.ybar;
/* 127 */       this.sumXX += dx * dx * this.n / (this.n + 1.0D);
/* 128 */       this.sumYY += dy * dy * this.n / (this.n + 1.0D);
/* 129 */       this.sumXY += dx * dy * this.n / (this.n + 1.0D);
/* 130 */       this.xbar += dx / (this.n + 1.0D);
/* 131 */       this.ybar += dy / (this.n + 1.0D);
/*     */     }
/* 133 */     this.sumX += x;
/* 134 */     this.sumY += y;
/* 135 */     this.n += 1L;
/*     */     
/* 137 */     if (this.n > 2L) {
/* 138 */       this.distribution.setDegreesOfFreedom(this.n - 2L);
/*     */     }
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
/*     */   public void addData(double[][] data)
/*     */   {
/* 159 */     for (int i = 0; i < data.length; i++) {
/* 160 */       addData(data[i][0], data[i][1]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 168 */     this.sumX = 0.0D;
/* 169 */     this.sumXX = 0.0D;
/* 170 */     this.sumY = 0.0D;
/* 171 */     this.sumYY = 0.0D;
/* 172 */     this.sumXY = 0.0D;
/* 173 */     this.n = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 182 */     return this.n;
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
/*     */   public double predict(double x)
/*     */   {
/* 203 */     double b1 = getSlope();
/* 204 */     return getIntercept(b1) + b1 * x;
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
/*     */   public double getIntercept()
/*     */   {
/* 224 */     return getIntercept(getSlope());
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
/*     */   public double getSlope()
/*     */   {
/* 244 */     if (this.n < 2L) {
/* 245 */       return NaN.0D;
/*     */     }
/* 247 */     if (Math.abs(this.sumXX) < 4.9E-323D) {
/* 248 */       return NaN.0D;
/*     */     }
/* 250 */     return this.sumXY / this.sumXX;
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
/*     */   public double getSumSquaredErrors()
/*     */   {
/* 283 */     return Math.max(0.0D, this.sumYY - this.sumXY * this.sumXY / this.sumXX);
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
/*     */   public double getTotalSumSquares()
/*     */   {
/* 297 */     if (this.n < 2L) {
/* 298 */       return NaN.0D;
/*     */     }
/* 300 */     return this.sumYY;
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
/*     */   public double getRegressionSumSquares()
/*     */   {
/* 320 */     return getRegressionSumSquares(getSlope());
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
/*     */   public double getMeanSquareError()
/*     */   {
/* 334 */     if (this.n < 3L) {
/* 335 */       return NaN.0D;
/*     */     }
/* 337 */     return getSumSquaredErrors() / (this.n - 2L);
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
/*     */   public double getR()
/*     */   {
/* 355 */     double b1 = getSlope();
/* 356 */     double result = Math.sqrt(getRSquare());
/* 357 */     if (b1 < 0.0D) {
/* 358 */       result = -result;
/*     */     }
/* 360 */     return result;
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
/*     */   public double getRSquare()
/*     */   {
/* 378 */     double ssto = getTotalSumSquares();
/* 379 */     return (ssto - getSumSquaredErrors()) / ssto;
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
/*     */   public double getInterceptStdErr()
/*     */   {
/* 394 */     return Math.sqrt(getMeanSquareError() * (1.0D / this.n + this.xbar * this.xbar / this.sumXX));
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
/*     */   public double getSlopeStdErr()
/*     */   {
/* 410 */     return Math.sqrt(getMeanSquareError() / this.sumXX);
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
/*     */   public double getSlopeConfidenceInterval()
/*     */     throws MathException
/*     */   {
/* 436 */     return getSlopeConfidenceInterval(0.05D);
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
/*     */   public double getSlopeConfidenceInterval(double alpha)
/*     */     throws MathException
/*     */   {
/* 472 */     if ((alpha >= 1.0D) || (alpha <= 0.0D)) {
/* 473 */       throw new IllegalArgumentException();
/*     */     }
/* 475 */     return getSlopeStdErr() * this.distribution.inverseCumulativeProbability(1.0D - alpha / 2.0D);
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
/*     */   public double getSignificance()
/*     */     throws MathException
/*     */   {
/* 501 */     return 2.0D * (1.0D - this.distribution.cumulativeProbability(Math.abs(getSlope()) / getSlopeStdErr()));
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
/*     */   private double getIntercept(double slope)
/*     */   {
/* 516 */     return (this.sumY - slope * this.sumX) / this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getRegressionSumSquares(double slope)
/*     */   {
/* 526 */     return slope * slope * this.sumXX;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDistribution(TDistribution value)
/*     */   {
/* 535 */     this.distribution = value;
/*     */     
/*     */ 
/* 538 */     if (this.n > 2L) {
/* 539 */       this.distribution.setDegreesOfFreedom(this.n - 2L);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/regression/SimpleRegression.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */