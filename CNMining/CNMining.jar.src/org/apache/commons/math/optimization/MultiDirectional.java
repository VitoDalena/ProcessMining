/*     */ package org.apache.commons.math.optimization;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiDirectional
/*     */   extends DirectSearchOptimizer
/*     */ {
/*     */   private double khi;
/*     */   
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiDirectional()
/*     */   {
/*  35 */     this.khi = 2.0D;
/*  36 */     this.gamma = 0.5D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiDirectional(double khi, double gamma)
/*     */   {
/*  45 */     this.khi = khi;
/*  46 */     this.gamma = gamma;
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
/*     */     for (;;)
/*     */     {
/*  59 */       PointCostPair[] original = this.simplex;
/*  60 */       double originalCost = original[0].getCost();
/*     */       
/*     */ 
/*  63 */       double reflectedCost = evaluateNewSimplex(original, 1.0D);
/*  64 */       if (reflectedCost < originalCost)
/*     */       {
/*     */ 
/*  67 */         PointCostPair[] reflected = this.simplex;
/*  68 */         double expandedCost = evaluateNewSimplex(original, this.khi);
/*  69 */         if (reflectedCost <= expandedCost)
/*     */         {
/*  71 */           this.simplex = reflected;
/*     */         }
/*     */         
/*  74 */         return;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  79 */       double contractedCost = evaluateNewSimplex(original, this.gamma);
/*  80 */       if (contractedCost < originalCost)
/*     */       {
/*  82 */         return;
/*     */       }
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
/*     */   private double evaluateNewSimplex(PointCostPair[] original, double coeff)
/*     */     throws CostException
/*     */   {
/*  99 */     double[] xSmallest = original[0].getPoint();
/* 100 */     int n = xSmallest.length;
/*     */     
/*     */ 
/* 103 */     this.simplex = new PointCostPair[n + 1];
/* 104 */     this.simplex[0] = original[0];
/* 105 */     for (int i = 1; i <= n; i++) {
/* 106 */       double[] xOriginal = original[i].getPoint();
/* 107 */       double[] xTransformed = new double[n];
/* 108 */       for (int j = 0; j < n; j++) {
/* 109 */         xSmallest[j] += coeff * (xSmallest[j] - xOriginal[j]);
/*     */       }
/* 111 */       this.simplex[i] = new PointCostPair(xTransformed, NaN.0D);
/*     */     }
/*     */     
/*     */ 
/* 115 */     evaluateSimplex();
/* 116 */     return this.simplex[0].getCost();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/MultiDirectional.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */