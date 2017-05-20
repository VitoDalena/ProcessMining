/*     */ package org.apache.commons.math.optimization;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.DimensionMismatchException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ import org.apache.commons.math.random.CorrelatedRandomVectorGenerator;
/*     */ import org.apache.commons.math.random.JDKRandomGenerator;
/*     */ import org.apache.commons.math.random.NotPositiveDefiniteMatrixException;
/*     */ import org.apache.commons.math.random.RandomGenerator;
/*     */ import org.apache.commons.math.random.RandomVectorGenerator;
/*     */ import org.apache.commons.math.random.UncorrelatedRandomVectorGenerator;
/*     */ import org.apache.commons.math.random.UniformRandomGenerator;
/*     */ import org.apache.commons.math.stat.descriptive.moment.VectorialCovariance;
/*     */ import org.apache.commons.math.stat.descriptive.moment.VectorialMean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DirectSearchOptimizer
/*     */ {
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, double[] vertexA, double[] vertexB)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 116 */     buildSimplex(vertexA, vertexB);
/* 117 */     setSingleStart();
/*     */     
/*     */ 
/* 120 */     return minimize(f, maxEvaluations, checker);
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
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, double[] vertexA, double[] vertexB, int starts, long seed)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 159 */     buildSimplex(vertexA, vertexB);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 164 */     double[] mean = new double[vertexA.length];
/* 165 */     double[] standardDeviation = new double[vertexA.length];
/* 166 */     for (int i = 0; i < vertexA.length; i++) {
/* 167 */       mean[i] = (0.5D * (vertexA[i] + vertexB[i]));
/* 168 */       standardDeviation[i] = (0.5D * Math.abs(vertexA[i] - vertexB[i]));
/*     */     }
/*     */     
/* 171 */     RandomGenerator rg = new JDKRandomGenerator();
/* 172 */     rg.setSeed(seed);
/* 173 */     UniformRandomGenerator urg = new UniformRandomGenerator(rg);
/* 174 */     RandomVectorGenerator rvg = new UncorrelatedRandomVectorGenerator(mean, standardDeviation, urg);
/*     */     
/* 176 */     setMultiStart(starts, rvg);
/*     */     
/*     */ 
/* 179 */     return minimize(f, maxEvaluations, checker);
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
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, double[][] vertices)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 206 */     buildSimplex(vertices);
/* 207 */     setSingleStart();
/*     */     
/*     */ 
/* 210 */     return minimize(f, maxEvaluations, checker);
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
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, double[][] vertices, int starts, long seed)
/*     */     throws NotPositiveDefiniteMatrixException, CostException, ConvergenceException
/*     */   {
/*     */     try
/*     */     {
/* 246 */       buildSimplex(vertices);
/*     */       
/*     */ 
/* 249 */       VectorialMean meanStat = new VectorialMean(vertices[0].length);
/* 250 */       VectorialCovariance covStat = new VectorialCovariance(vertices[0].length, true);
/* 251 */       for (int i = 0; i < vertices.length; i++) {
/* 252 */         meanStat.increment(vertices[i]);
/* 253 */         covStat.increment(vertices[i]);
/*     */       }
/* 255 */       double[] mean = meanStat.getResult();
/* 256 */       RealMatrix covariance = covStat.getResult();
/*     */       
/*     */ 
/* 259 */       RandomGenerator rg = new JDKRandomGenerator();
/* 260 */       rg.setSeed(seed);
/* 261 */       RandomVectorGenerator rvg = new CorrelatedRandomVectorGenerator(mean, covariance, 1.0E-12D * covariance.getNorm(), new UniformRandomGenerator(rg));
/*     */       
/*     */ 
/*     */ 
/* 265 */       setMultiStart(starts, rvg);
/*     */       
/*     */ 
/* 268 */       return minimize(f, maxEvaluations, checker);
/*     */     }
/*     */     catch (DimensionMismatchException dme)
/*     */     {
/* 272 */       throw new RuntimeException("internal error");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, RandomVectorGenerator generator)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 300 */     buildSimplex(generator);
/* 301 */     setSingleStart();
/*     */     
/*     */ 
/* 304 */     return minimize(f, maxEvaluations, checker);
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
/*     */   public PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker, RandomVectorGenerator generator, int starts)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 335 */     buildSimplex(generator);
/* 336 */     setMultiStart(starts, generator);
/*     */     
/*     */ 
/* 339 */     return minimize(f, maxEvaluations, checker);
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
/*     */   private void buildSimplex(double[] vertexA, double[] vertexB)
/*     */   {
/* 356 */     int n = vertexA.length;
/* 357 */     this.simplex = new PointCostPair[n + 1];
/*     */     
/*     */ 
/* 360 */     for (int i = 0; i <= n; i++) {
/* 361 */       double[] vertex = new double[n];
/* 362 */       if (i > 0) {
/* 363 */         System.arraycopy(vertexB, 0, vertex, 0, i);
/*     */       }
/* 365 */       if (i < n) {
/* 366 */         System.arraycopy(vertexA, i, vertex, i, n - i);
/*     */       }
/* 368 */       this.simplex[i] = new PointCostPair(vertex, NaN.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void buildSimplex(double[][] vertices)
/*     */   {
/* 377 */     int n = vertices.length - 1;
/* 378 */     this.simplex = new PointCostPair[n + 1];
/* 379 */     for (int i = 0; i <= n; i++) {
/* 380 */       this.simplex[i] = new PointCostPair(vertices[i], NaN.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void buildSimplex(RandomVectorGenerator generator)
/*     */   {
/* 390 */     double[] vertex = generator.nextVector();
/* 391 */     int n = vertex.length;
/* 392 */     this.simplex = new PointCostPair[n + 1];
/* 393 */     this.simplex[0] = new PointCostPair(vertex, NaN.0D);
/*     */     
/*     */ 
/* 396 */     for (int i = 1; i <= n; i++) {
/* 397 */       this.simplex[i] = new PointCostPair(generator.nextVector(), NaN.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setSingleStart()
/*     */   {
/* 405 */     this.starts = 1;
/* 406 */     this.generator = null;
/* 407 */     this.minima = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setMultiStart(int starts, RandomVectorGenerator generator)
/*     */   {
/* 417 */     if (starts < 2) {
/* 418 */       this.starts = 1;
/* 419 */       this.generator = null;
/* 420 */       this.minima = null;
/*     */     } else {
/* 422 */       this.starts = starts;
/* 423 */       this.generator = generator;
/* 424 */       this.minima = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PointCostPair[] getMinima()
/*     */   {
/* 452 */     return (PointCostPair[])this.minima.clone();
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
/*     */   private PointCostPair minimize(CostFunction f, int maxEvaluations, ConvergenceChecker checker)
/*     */     throws CostException, ConvergenceException
/*     */   {
/* 473 */     this.f = f;
/* 474 */     this.minima = new PointCostPair[this.starts];
/*     */     
/*     */ 
/* 477 */     for (int i = 0; i < this.starts; i++)
/*     */     {
/* 479 */       this.evaluations = 0;
/* 480 */       evaluateSimplex();
/*     */       
/* 482 */       for (boolean loop = true; loop;) {
/* 483 */         if (checker.converged(this.simplex))
/*     */         {
/* 485 */           this.minima[i] = this.simplex[0];
/* 486 */           loop = false;
/* 487 */         } else if (this.evaluations >= maxEvaluations)
/*     */         {
/* 489 */           this.minima[i] = null;
/* 490 */           loop = false;
/*     */         } else {
/* 492 */           iterateSimplex();
/*     */         }
/*     */       }
/*     */       
/* 496 */       if (i < this.starts - 1)
/*     */       {
/* 498 */         buildSimplex(this.generator);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 505 */     Arrays.sort(this.minima, pointCostPairComparator);
/*     */     
/*     */ 
/* 508 */     if (this.minima[0] == null) {
/* 509 */       throw new ConvergenceException("none of the {0} start points lead to convergence", new Object[] { Integer.toString(this.starts) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 515 */     return this.minima[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void iterateSimplex()
/*     */     throws CostException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double evaluateCost(double[] x)
/*     */     throws CostException
/*     */   {
/* 535 */     this.evaluations += 1;
/* 536 */     return this.f.cost(x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void evaluateSimplex()
/*     */     throws CostException
/*     */   {
/* 546 */     for (int i = 0; i < this.simplex.length; i++) {
/* 547 */       PointCostPair pair = this.simplex[i];
/* 548 */       if (Double.isNaN(pair.getCost())) {
/* 549 */         this.simplex[i] = new PointCostPair(pair.getPoint(), evaluateCost(pair.getPoint()));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 554 */     Arrays.sort(this.simplex, pointCostPairComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void replaceWorstPoint(PointCostPair pointCostPair)
/*     */   {
/* 562 */     int n = this.simplex.length - 1;
/* 563 */     for (int i = 0; i < n; i++) {
/* 564 */       if (this.simplex[i].getCost() > pointCostPair.getCost()) {
/* 565 */         PointCostPair tmp = this.simplex[i];
/* 566 */         this.simplex[i] = pointCostPair;
/* 567 */         pointCostPair = tmp;
/*     */       }
/*     */     }
/* 570 */     this.simplex[n] = pointCostPair;
/*     */   }
/*     */   
/*     */ 
/* 574 */   private static Comparator pointCostPairComparator = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/* 576 */       if (o1 == null)
/* 577 */         return o2 == null ? 0 : 1;
/* 578 */       if (o2 == null) {
/* 579 */         return -1;
/*     */       }
/* 581 */       double cost1 = ((PointCostPair)o1).getCost();
/* 582 */       double cost2 = ((PointCostPair)o2).getCost();
/* 583 */       return o1 == o2 ? 0 : cost1 < cost2 ? -1 : 1;
/*     */     }
/*     */   };
/*     */   protected PointCostPair[] simplex;
/*     */   private CostFunction f;
/*     */   private int evaluations;
/*     */   private int starts;
/*     */   private RandomVectorGenerator generator;
/*     */   private PointCostPair[] minima;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/DirectSearchOptimizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */