/*     */ package org.apache.commons.math.optimization;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NelderMead
/*     */   extends DirectSearchOptimizer
/*     */ {
/*     */   private double rho;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double khi;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double gamma;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double sigma;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NelderMead()
/*     */   {
/*  36 */     this.rho = 1.0D;
/*  37 */     this.khi = 2.0D;
/*  38 */     this.gamma = 0.5D;
/*  39 */     this.sigma = 0.5D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NelderMead(double rho, double khi, double gamma, double sigma)
/*     */   {
/*  50 */     this.rho = rho;
/*  51 */     this.khi = khi;
/*  52 */     this.gamma = gamma;
/*  53 */     this.sigma = sigma;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void iterateSimplex()
/*     */     throws CostException
/*     */   {
/*  64 */     int n = this.simplex.length - 1;
/*     */     
/*     */ 
/*  67 */     double smallest = this.simplex[0].getCost();
/*  68 */     double secondLargest = this.simplex[(n - 1)].getCost();
/*  69 */     double largest = this.simplex[n].getCost();
/*  70 */     double[] xLargest = this.simplex[n].getPoint();
/*     */     
/*     */ 
/*     */ 
/*  74 */     double[] centroid = new double[n];
/*  75 */     for (int i = 0; i < n; i++) {
/*  76 */       double[] x = this.simplex[i].getPoint();
/*  77 */       for (int j = 0; j < n; j++) {
/*  78 */         centroid[j] += x[j];
/*     */       }
/*     */     }
/*  81 */     double scaling = 1.0D / n;
/*  82 */     for (int j = 0; j < n; j++) {
/*  83 */       centroid[j] *= scaling;
/*     */     }
/*     */     
/*     */ 
/*  87 */     double[] xR = new double[n];
/*  88 */     for (int j = 0; j < n; j++) {
/*  89 */       centroid[j] += this.rho * (centroid[j] - xLargest[j]);
/*     */     }
/*  91 */     double costR = evaluateCost(xR);
/*     */     
/*  93 */     if ((smallest <= costR) && (costR < secondLargest))
/*     */     {
/*     */ 
/*  96 */       replaceWorstPoint(new PointCostPair(xR, costR));
/*     */     }
/*  98 */     else if (costR < smallest)
/*     */     {
/*     */ 
/* 101 */       double[] xE = new double[n];
/* 102 */       for (int j = 0; j < n; j++) {
/* 103 */         centroid[j] += this.khi * (xR[j] - centroid[j]);
/*     */       }
/* 105 */       double costE = evaluateCost(xE);
/*     */       
/* 107 */       if (costE < costR)
/*     */       {
/* 109 */         replaceWorstPoint(new PointCostPair(xE, costE));
/*     */       }
/*     */       else {
/* 112 */         replaceWorstPoint(new PointCostPair(xR, costR));
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 117 */       if (costR < largest)
/*     */       {
/*     */ 
/* 120 */         double[] xC = new double[n];
/* 121 */         for (int j = 0; j < n; j++) {
/* 122 */           centroid[j] += this.gamma * (xR[j] - centroid[j]);
/*     */         }
/* 124 */         double costC = evaluateCost(xC);
/*     */         
/* 126 */         if (costC <= costR)
/*     */         {
/* 128 */           replaceWorstPoint(new PointCostPair(xC, costC));
/* 129 */           return;
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 135 */         double[] xC = new double[n];
/* 136 */         for (int j = 0; j < n; j++) {
/* 137 */           centroid[j] -= this.gamma * (centroid[j] - xLargest[j]);
/*     */         }
/* 139 */         double costC = evaluateCost(xC);
/*     */         
/* 141 */         if (costC < largest)
/*     */         {
/* 143 */           replaceWorstPoint(new PointCostPair(xC, costC));
/* 144 */           return;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 150 */       double[] xSmallest = this.simplex[0].getPoint();
/* 151 */       for (int i = 1; i < this.simplex.length; i++) {
/* 152 */         double[] x = this.simplex[i].getPoint();
/* 153 */         for (int j = 0; j < n; j++) {
/* 154 */           xSmallest[j] += this.sigma * (x[j] - xSmallest[j]);
/*     */         }
/* 156 */         this.simplex[i] = new PointCostPair(x, NaN.0D);
/*     */       }
/* 158 */       evaluateSimplex();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/NelderMead.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */