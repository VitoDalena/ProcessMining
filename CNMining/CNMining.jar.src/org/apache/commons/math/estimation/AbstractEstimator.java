/*     */ package org.apache.commons.math.estimation;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public abstract class AbstractEstimator
/*     */   implements Estimator
/*     */ {
/*     */   protected WeightedMeasurement[] measurements;
/*     */   protected EstimatedParameter[] parameters;
/*     */   protected double[] jacobian;
/*     */   protected int cols;
/*     */   protected int rows;
/*     */   protected double[] residuals;
/*     */   protected double cost;
/*     */   private int maxCostEval;
/*     */   private int costEvaluations;
/*     */   private int jacobianEvaluations;
/*     */   
/*     */   public final void setMaxCostEval(int maxCostEval)
/*     */   {
/*  48 */     this.maxCostEval = maxCostEval;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getCostEvaluations()
/*     */   {
/*  57 */     return this.costEvaluations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getJacobianEvaluations()
/*     */   {
/*  66 */     return this.jacobianEvaluations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void updateJacobian()
/*     */   {
/*  73 */     incrementJacobianEvaluationsCounter();
/*  74 */     Arrays.fill(this.jacobian, 0.0D);
/*  75 */     int i = 0; for (int index = 0; i < this.rows; i++) {
/*  76 */       WeightedMeasurement wm = this.measurements[i];
/*  77 */       double factor = -Math.sqrt(wm.getWeight());
/*  78 */       for (int j = 0; j < this.cols; j++) {
/*  79 */         this.jacobian[(index++)] = (factor * wm.getPartial(this.parameters[j]));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected final void incrementJacobianEvaluationsCounter()
/*     */   {
/*  88 */     this.jacobianEvaluations += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateResidualsAndCost()
/*     */     throws EstimationException
/*     */   {
/*  99 */     if (++this.costEvaluations > this.maxCostEval) {
/* 100 */       throw new EstimationException("maximal number of evaluations exceeded ({0})", new Object[] { new Integer(this.maxCostEval) });
/*     */     }
/*     */     
/*     */ 
/* 104 */     this.cost = 0.0D;
/* 105 */     int i = 0; for (int index = 0; i < this.rows; index += this.cols) {
/* 106 */       WeightedMeasurement wm = this.measurements[i];
/* 107 */       double residual = wm.getResidual();
/* 108 */       this.residuals[i] = (Math.sqrt(wm.getWeight()) * residual);
/* 109 */       this.cost += wm.getWeight() * residual * residual;i++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 111 */     this.cost = Math.sqrt(this.cost);
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
/*     */   public double getRMS(EstimationProblem problem)
/*     */   {
/* 127 */     WeightedMeasurement[] wm = problem.getMeasurements();
/* 128 */     double criterion = 0.0D;
/* 129 */     for (int i = 0; i < wm.length; i++) {
/* 130 */       double residual = wm[i].getResidual();
/* 131 */       criterion += wm[i].getWeight() * residual * residual;
/*     */     }
/* 133 */     return Math.sqrt(criterion / wm.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getChiSquare(EstimationProblem problem)
/*     */   {
/* 142 */     WeightedMeasurement[] wm = problem.getMeasurements();
/* 143 */     double chiSquare = 0.0D;
/* 144 */     for (int i = 0; i < wm.length; i++) {
/* 145 */       double residual = wm[i].getResidual();
/* 146 */       chiSquare += residual * residual / wm[i].getWeight();
/*     */     }
/* 148 */     return chiSquare;
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
/*     */   public double[][] getCovariances(EstimationProblem problem)
/*     */     throws EstimationException
/*     */   {
/* 162 */     updateJacobian();
/*     */     
/*     */ 
/* 165 */     int rows = problem.getMeasurements().length;
/* 166 */     int cols = problem.getAllParameters().length;
/* 167 */     int max = cols * rows;
/* 168 */     double[][] jTj = new double[cols][cols];
/* 169 */     for (int i = 0; i < cols; i++) {
/* 170 */       for (int j = i; j < cols; j++) {
/* 171 */         double sum = 0.0D;
/* 172 */         for (int k = 0; k < max; k += cols) {
/* 173 */           sum += this.jacobian[(k + i)] * this.jacobian[(k + j)];
/*     */         }
/* 175 */         jTj[i][j] = sum;
/* 176 */         jTj[j][i] = sum;
/*     */       }
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 182 */       return new RealMatrixImpl(jTj).inverse().getData();
/*     */     } catch (InvalidMatrixException ime) {
/* 184 */       throw new EstimationException("unable to compute covariances: singular problem", new Object[0]);
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
/*     */   public double[] guessParametersErrors(EstimationProblem problem)
/*     */     throws EstimationException
/*     */   {
/* 201 */     int m = problem.getMeasurements().length;
/* 202 */     int p = problem.getAllParameters().length;
/* 203 */     if (m <= p) {
/* 204 */       throw new EstimationException("no degrees of freedom ({0} measurements, {1} parameters)", new Object[] { new Integer(m), new Integer(p) });
/*     */     }
/*     */     
/* 207 */     double[] errors = new double[problem.getAllParameters().length];
/* 208 */     double c = Math.sqrt(getChiSquare(problem) / (m - p));
/* 209 */     double[][] covar = getCovariances(problem);
/* 210 */     for (int i = 0; i < errors.length; i++) {
/* 211 */       errors[i] = (Math.sqrt(covar[i][i]) * c);
/*     */     }
/* 213 */     return errors;
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
/*     */   protected void initializeEstimate(EstimationProblem problem)
/*     */   {
/* 226 */     this.costEvaluations = 0;
/* 227 */     this.jacobianEvaluations = 0;
/*     */     
/*     */ 
/* 230 */     this.measurements = problem.getMeasurements();
/* 231 */     this.parameters = problem.getUnboundParameters();
/*     */     
/*     */ 
/* 234 */     this.rows = this.measurements.length;
/* 235 */     this.cols = this.parameters.length;
/* 236 */     this.jacobian = new double[this.rows * this.cols];
/* 237 */     this.residuals = new double[this.rows];
/*     */     
/* 239 */     this.cost = Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public abstract void estimate(EstimationProblem paramEstimationProblem)
/*     */     throws EstimationException;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/AbstractEstimator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */