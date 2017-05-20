/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.util.MathUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HypergeometricDistributionImpl
/*     */   extends AbstractIntegerDistribution
/*     */   implements HypergeometricDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -436928820673516179L;
/*     */   private int numberOfSuccesses;
/*     */   private int populationSize;
/*     */   private int sampleSize;
/*     */   
/*     */   public HypergeometricDistributionImpl(int populationSize, int numberOfSuccesses, int sampleSize)
/*     */   {
/*  55 */     if (numberOfSuccesses > populationSize) {
/*  56 */       throw new IllegalArgumentException("number of successes must be less than or equal to population size");
/*     */     }
/*     */     
/*     */ 
/*  60 */     if (sampleSize > populationSize) {
/*  61 */       throw new IllegalArgumentException("sample size must be less than or equal to population size");
/*     */     }
/*     */     
/*  64 */     setPopulationSize(populationSize);
/*  65 */     setSampleSize(sampleSize);
/*  66 */     setNumberOfSuccesses(numberOfSuccesses);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(int x)
/*     */   {
/*  77 */     int n = getPopulationSize();
/*  78 */     int m = getNumberOfSuccesses();
/*  79 */     int k = getSampleSize();
/*     */     
/*  81 */     int[] domain = getDomain(n, m, k);
/*  82 */     double ret; double ret; if (x < domain[0]) {
/*  83 */       ret = 0.0D; } else { double ret;
/*  84 */       if (x >= domain[1]) {
/*  85 */         ret = 1.0D;
/*     */       } else {
/*  87 */         ret = innerCumulativeProbability(domain[0], x, 1, n, m, k);
/*     */       }
/*     */     }
/*  90 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] getDomain(int n, int m, int k)
/*     */   {
/* 102 */     return new int[] { getLowerDomain(n, m, k), getUpperDomain(m, k) };
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
/*     */   protected int getDomainLowerBound(double p)
/*     */   {
/* 117 */     return getLowerDomain(getPopulationSize(), getNumberOfSuccesses(), getSampleSize());
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
/*     */   protected int getDomainUpperBound(double p)
/*     */   {
/* 130 */     return getUpperDomain(getSampleSize(), getNumberOfSuccesses());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getLowerDomain(int n, int m, int k)
/*     */   {
/* 142 */     return Math.max(0, m - (n - k));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumberOfSuccesses()
/*     */   {
/* 150 */     return this.numberOfSuccesses;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPopulationSize()
/*     */   {
/* 158 */     return this.populationSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSampleSize()
/*     */   {
/* 166 */     return this.sampleSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getUpperDomain(int m, int k)
/*     */   {
/* 177 */     return Math.min(k, m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double probability(int x)
/*     */   {
/* 189 */     int n = getPopulationSize();
/* 190 */     int m = getNumberOfSuccesses();
/* 191 */     int k = getSampleSize();
/*     */     
/* 193 */     int[] domain = getDomain(n, m, k);
/* 194 */     double ret; double ret; if ((x < domain[0]) || (x > domain[1])) {
/* 195 */       ret = 0.0D;
/*     */     } else {
/* 197 */       ret = probability(n, m, k, x);
/*     */     }
/*     */     
/* 200 */     return ret;
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
/*     */   private double probability(int n, int m, int k, int x)
/*     */   {
/* 214 */     return Math.exp(MathUtils.binomialCoefficientLog(m, x) + MathUtils.binomialCoefficientLog(n - m, k - x) - MathUtils.binomialCoefficientLog(n, k));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberOfSuccesses(int num)
/*     */   {
/* 225 */     if (num < 0) {
/* 226 */       throw new IllegalArgumentException("number of successes must be non-negative.");
/*     */     }
/*     */     
/* 229 */     this.numberOfSuccesses = num;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPopulationSize(int size)
/*     */   {
/* 238 */     if (size <= 0) {
/* 239 */       throw new IllegalArgumentException("population size must be positive.");
/*     */     }
/*     */     
/* 242 */     this.populationSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSampleSize(int size)
/*     */   {
/* 251 */     if (size < 0) {
/* 252 */       throw new IllegalArgumentException("sample size must be non-negative.");
/*     */     }
/*     */     
/* 255 */     this.sampleSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double upperCumulativeProbability(int x)
/*     */   {
/* 267 */     int n = getPopulationSize();
/* 268 */     int m = getNumberOfSuccesses();
/* 269 */     int k = getSampleSize();
/*     */     
/* 271 */     int[] domain = getDomain(n, m, k);
/* 272 */     double ret; double ret; if (x < domain[0]) {
/* 273 */       ret = 1.0D; } else { double ret;
/* 274 */       if (x > domain[1]) {
/* 275 */         ret = 0.0D;
/*     */       } else {
/* 277 */         ret = innerCumulativeProbability(domain[1], x, -1, n, m, k);
/*     */       }
/*     */     }
/* 280 */     return ret;
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
/*     */   private double innerCumulativeProbability(int x0, int x1, int dx, int n, int m, int k)
/*     */   {
/* 299 */     double ret = probability(n, m, k, x0);
/* 300 */     while (x0 != x1) {
/* 301 */       x0 += dx;
/* 302 */       ret += probability(n, m, k, x0);
/*     */     }
/* 304 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/HypergeometricDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */