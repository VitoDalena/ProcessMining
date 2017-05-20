/*     */ package org.apache.commons.math.stat.inference;
/*     */ 
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.distribution.ChiSquaredDistribution;
/*     */ import org.apache.commons.math.distribution.ChiSquaredDistributionImpl;
/*     */ import org.apache.commons.math.distribution.DistributionFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChiSquareTestImpl
/*     */   implements UnknownDistributionChiSquareTest
/*     */ {
/*     */   private ChiSquaredDistribution distribution;
/*     */   
/*     */   public ChiSquareTestImpl()
/*     */   {
/*  39 */     this(new ChiSquaredDistributionImpl(1.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChiSquareTestImpl(ChiSquaredDistribution x)
/*     */   {
/*  50 */     setDistribution(x);
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
/*     */   public double chiSquare(double[] expected, long[] observed)
/*     */     throws IllegalArgumentException
/*     */   {
/*  66 */     if ((expected.length < 2) || (expected.length != observed.length)) {
/*  67 */       throw new IllegalArgumentException("observed, expected array lengths incorrect");
/*     */     }
/*     */     
/*  70 */     if ((!isPositive(expected)) || (!isNonNegative(observed))) {
/*  71 */       throw new IllegalArgumentException("observed counts must be non-negative and expected counts must be postive");
/*     */     }
/*     */     
/*  74 */     double sumExpected = 0.0D;
/*  75 */     double sumObserved = 0.0D;
/*  76 */     for (int i = 0; i < observed.length; i++) {
/*  77 */       sumExpected += expected[i];
/*  78 */       sumObserved += observed[i];
/*     */     }
/*  80 */     double ratio = 1.0D;
/*  81 */     boolean rescale = false;
/*  82 */     if (Math.abs(sumExpected - sumObserved) > 1.0E-5D) {
/*  83 */       ratio = sumObserved / sumExpected;
/*  84 */       rescale = true;
/*     */     }
/*  86 */     double sumSq = 0.0D;
/*  87 */     double dev = 0.0D;
/*  88 */     for (int i = 0; i < observed.length; i++) {
/*  89 */       if (rescale) {
/*  90 */         dev = observed[i] - ratio * expected[i];
/*  91 */         sumSq += dev * dev / (ratio * expected[i]);
/*     */       } else {
/*  93 */         dev = observed[i] - expected[i];
/*  94 */         sumSq += dev * dev / expected[i];
/*     */       }
/*     */     }
/*  97 */     return sumSq;
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
/*     */   public double chiSquareTest(double[] expected, long[] observed)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 114 */     this.distribution.setDegreesOfFreedom(expected.length - 1.0D);
/* 115 */     return 1.0D - this.distribution.cumulativeProbability(chiSquare(expected, observed));
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
/*     */   public boolean chiSquareTest(double[] expected, long[] observed, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 135 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/* 136 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*     */     }
/*     */     
/* 139 */     return chiSquareTest(expected, observed) < alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double chiSquare(long[][] counts)
/*     */     throws IllegalArgumentException
/*     */   {
/* 149 */     checkArray(counts);
/* 150 */     int nRows = counts.length;
/* 151 */     int nCols = counts[0].length;
/*     */     
/*     */ 
/* 154 */     double[] rowSum = new double[nRows];
/* 155 */     double[] colSum = new double[nCols];
/* 156 */     double total = 0.0D;
/* 157 */     for (int row = 0; row < nRows; row++) {
/* 158 */       for (int col = 0; col < nCols; col++) {
/* 159 */         rowSum[row] += counts[row][col];
/* 160 */         colSum[col] += counts[row][col];
/* 161 */         total += counts[row][col];
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 166 */     double sumSq = 0.0D;
/* 167 */     double expected = 0.0D;
/* 168 */     for (int row = 0; row < nRows; row++) {
/* 169 */       for (int col = 0; col < nCols; col++) {
/* 170 */         expected = rowSum[row] * colSum[col] / total;
/* 171 */         sumSq += (counts[row][col] - expected) * (counts[row][col] - expected) / expected;
/*     */       }
/*     */     }
/*     */     
/* 175 */     return sumSq;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double chiSquareTest(long[][] counts)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 186 */     checkArray(counts);
/* 187 */     double df = (counts.length - 1.0D) * (counts[0].length - 1.0D);
/* 188 */     this.distribution.setDegreesOfFreedom(df);
/* 189 */     return 1.0D - this.distribution.cumulativeProbability(chiSquare(counts));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean chiSquareTest(long[][] counts, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 202 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/* 203 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*     */     }
/* 205 */     return chiSquareTest(counts) < alpha;
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
/*     */   public double chiSquareDataSetsComparison(long[] observed1, long[] observed2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 219 */     if ((observed1.length < 2) || (observed1.length != observed2.length)) {
/* 220 */       throw new IllegalArgumentException("oberved1, observed2 array lengths incorrect");
/*     */     }
/*     */     
/*     */ 
/* 224 */     if ((!isNonNegative(observed1)) || (!isNonNegative(observed2))) {
/* 225 */       throw new IllegalArgumentException("observed counts must be non-negative");
/*     */     }
/*     */     
/*     */ 
/* 229 */     long countSum1 = 0L;
/* 230 */     long countSum2 = 0L;
/* 231 */     boolean unequalCounts = false;
/* 232 */     double weight = 0.0D;
/* 233 */     for (int i = 0; i < observed1.length; i++) {
/* 234 */       countSum1 += observed1[i];
/* 235 */       countSum2 += observed2[i];
/*     */     }
/*     */     
/* 238 */     if (countSum1 * countSum2 == 0L) {
/* 239 */       throw new IllegalArgumentException("observed counts cannot all be 0");
/*     */     }
/*     */     
/*     */ 
/* 243 */     unequalCounts = countSum1 != countSum2;
/* 244 */     if (unequalCounts) {
/* 245 */       weight = Math.sqrt(countSum1 / countSum2);
/*     */     }
/*     */     
/* 248 */     double sumSq = 0.0D;
/* 249 */     double dev = 0.0D;
/* 250 */     double obs1 = 0.0D;
/* 251 */     double obs2 = 0.0D;
/* 252 */     for (int i = 0; i < observed1.length; i++) {
/* 253 */       if ((observed1[i] == 0L) && (observed2[i] == 0L)) {
/* 254 */         throw new IllegalArgumentException("observed counts must not both be zero");
/*     */       }
/*     */       
/* 257 */       obs1 = observed1[i];
/* 258 */       obs2 = observed2[i];
/* 259 */       if (unequalCounts) {
/* 260 */         dev = obs1 / weight - obs2 * weight;
/*     */       } else {
/* 262 */         dev = obs1 - obs2;
/*     */       }
/* 264 */       sumSq += dev * dev / (obs1 + obs2);
/*     */     }
/*     */     
/* 267 */     return sumSq;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 280 */     this.distribution.setDegreesOfFreedom(observed1.length - 1.0D);
/* 281 */     return 1.0D - this.distribution.cumulativeProbability(chiSquareDataSetsComparison(observed1, observed2));
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
/*     */   public boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 297 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/* 298 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*     */     }
/*     */     
/* 301 */     return chiSquareTestDataSetsComparison(observed1, observed2) < alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkArray(long[][] in)
/*     */     throws IllegalArgumentException
/*     */   {
/* 314 */     if (in.length < 2) {
/* 315 */       throw new IllegalArgumentException("Input table must have at least two rows");
/*     */     }
/*     */     
/* 318 */     if (in[0].length < 2) {
/* 319 */       throw new IllegalArgumentException("Input table must have at least two columns");
/*     */     }
/*     */     
/* 322 */     if (!isRectangular(in)) {
/* 323 */       throw new IllegalArgumentException("Input table must be rectangular");
/*     */     }
/*     */     
/* 326 */     if (!isNonNegative(in)) {
/* 327 */       throw new IllegalArgumentException("All entries in input 2-way table must be non-negative");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected DistributionFactory getDistributionFactory()
/*     */   {
/* 339 */     return DistributionFactory.newInstance();
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
/*     */   private boolean isRectangular(long[][] in)
/*     */   {
/* 353 */     for (int i = 1; i < in.length; i++) {
/* 354 */       if (in[i].length != in[0].length) {
/* 355 */         return false;
/*     */       }
/*     */     }
/* 358 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isPositive(double[] in)
/*     */   {
/* 370 */     for (int i = 0; i < in.length; i++) {
/* 371 */       if (in[i] <= 0.0D) {
/* 372 */         return false;
/*     */       }
/*     */     }
/* 375 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isNonNegative(long[] in)
/*     */   {
/* 387 */     for (int i = 0; i < in.length; i++) {
/* 388 */       if (in[i] < 0L) {
/* 389 */         return false;
/*     */       }
/*     */     }
/* 392 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isNonNegative(long[][] in)
/*     */   {
/* 404 */     for (int i = 0; i < in.length; i++) {
/* 405 */       for (int j = 0; j < in[i].length; j++) {
/* 406 */         if (in[i][j] < 0L) {
/* 407 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 411 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDistribution(ChiSquaredDistribution value)
/*     */   {
/* 422 */     this.distribution = value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/ChiSquareTestImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */