/*     */ package org.apache.commons.math.estimation;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.linear.InvalidMatrixException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ import org.apache.commons.math.linear.RealMatrixImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GaussNewtonEstimator
/*     */   extends AbstractEstimator
/*     */   implements Serializable
/*     */ {
/*     */   private double steadyStateThreshold;
/*     */   private double convergence;
/*     */   private static final long serialVersionUID = 5485001826076289109L;
/*     */   
/*     */   public GaussNewtonEstimator(int maxCostEval, double convergence, double steadyStateThreshold)
/*     */   {
/*  74 */     setMaxCostEval(maxCostEval);
/*  75 */     this.steadyStateThreshold = steadyStateThreshold;
/*  76 */     this.convergence = convergence;
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
/*     */   public void estimate(EstimationProblem problem)
/*     */     throws EstimationException
/*     */   {
/* 105 */     initializeEstimate(problem);
/*     */     
/*     */ 
/* 108 */     double[] grad = new double[this.parameters.length];
/* 109 */     RealMatrixImpl bDecrement = new RealMatrixImpl(this.parameters.length, 1);
/* 110 */     double[][] bDecrementData = bDecrement.getDataRef();
/* 111 */     RealMatrixImpl wGradGradT = new RealMatrixImpl(this.parameters.length, this.parameters.length);
/* 112 */     double[][] wggData = wGradGradT.getDataRef();
/*     */     
/*     */ 
/* 115 */     double previous = Double.POSITIVE_INFINITY;
/*     */     
/*     */     do
/*     */     {
/* 119 */       incrementJacobianEvaluationsCounter();
/* 120 */       RealMatrix b = new RealMatrixImpl(this.parameters.length, 1);
/* 121 */       RealMatrix a = new RealMatrixImpl(this.parameters.length, this.parameters.length);
/* 122 */       for (int i = 0; i < this.measurements.length; i++) {
/* 123 */         if (!this.measurements[i].isIgnored())
/*     */         {
/* 125 */           double weight = this.measurements[i].getWeight();
/* 126 */           double residual = this.measurements[i].getResidual();
/*     */           
/*     */ 
/* 129 */           for (int j = 0; j < this.parameters.length; j++) {
/* 130 */             grad[j] = this.measurements[i].getPartial(this.parameters[j]);
/* 131 */             bDecrementData[j][0] = (weight * residual * grad[j]);
/*     */           }
/*     */           
/*     */ 
/* 135 */           for (int k = 0; k < this.parameters.length; k++) {
/* 136 */             double[] wggRow = wggData[k];
/* 137 */             double gk = grad[k];
/* 138 */             for (int l = 0; l < this.parameters.length; l++) {
/* 139 */               wggRow[l] = (weight * gk * grad[l]);
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 144 */           a = a.add(wGradGradT);
/* 145 */           b = b.add(bDecrement);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 153 */         RealMatrix dX = a.solve(b);
/*     */         
/*     */ 
/* 156 */         for (int i = 0; i < this.parameters.length; i++) {
/* 157 */           this.parameters[i].setEstimate(this.parameters[i].getEstimate() + dX.getEntry(i, 0));
/*     */         }
/*     */       }
/*     */       catch (InvalidMatrixException e) {
/* 161 */         throw new EstimationException("unable to solve: singular problem", new Object[0]);
/*     */       }
/*     */       
/*     */ 
/* 165 */       previous = this.cost;
/* 166 */       updateResidualsAndCost();
/*     */     }
/* 168 */     while ((getCostEvaluations() < 2) || ((Math.abs(previous - this.cost) > this.cost * this.steadyStateThreshold) && (Math.abs(this.cost) > this.convergence)));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/GaussNewtonEstimator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */