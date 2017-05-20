/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.analysis.BrentSolver;
/*     */ import org.apache.commons.math.analysis.UnivariateRealFunction;
/*     */ import org.apache.commons.math.analysis.UnivariateRealSolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SwitchState
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7307007422156119622L;
/*     */   private SwitchingFunction function;
/*     */   private double maxCheckInterval;
/*     */   private double convergence;
/*     */   private int maxIterationCount;
/*     */   private double t0;
/*     */   private double g0;
/*     */   private boolean g0Positive;
/*     */   private boolean pendingEvent;
/*     */   private double pendingEventTime;
/*     */   private double previousEventTime;
/*     */   private boolean increasing;
/*     */   private int nextAction;
/*     */   
/*     */   public SwitchState(SwitchingFunction function, double maxCheckInterval, double convergence, int maxIterationCount)
/*     */   {
/*  96 */     this.function = function;
/*  97 */     this.maxCheckInterval = maxCheckInterval;
/*  98 */     this.convergence = Math.abs(convergence);
/*  99 */     this.maxIterationCount = maxIterationCount;
/*     */     
/*     */ 
/* 102 */     this.t0 = NaN.0D;
/* 103 */     this.g0 = NaN.0D;
/* 104 */     this.g0Positive = true;
/* 105 */     this.pendingEvent = false;
/* 106 */     this.pendingEventTime = NaN.0D;
/* 107 */     this.previousEventTime = NaN.0D;
/* 108 */     this.increasing = true;
/* 109 */     this.nextAction = 3;
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
/*     */   public void reinitializeBegin(double t0, double[] y0)
/*     */     throws FunctionEvaluationException
/*     */   {
/* 123 */     this.t0 = t0;
/* 124 */     this.g0 = this.function.g(t0, y0);
/* 125 */     this.g0Positive = (this.g0 >= 0.0D);
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
/*     */   public boolean evaluateStep(StepInterpolator interpolator)
/*     */     throws DerivativeException, FunctionEvaluationException, ConvergenceException
/*     */   {
/*     */     try
/*     */     {
/* 144 */       double t1 = interpolator.getCurrentTime();
/* 145 */       int n = Math.max(1, (int)Math.ceil(Math.abs(t1 - this.t0) / this.maxCheckInterval));
/* 146 */       double h = (t1 - this.t0) / n;
/*     */       
/* 148 */       double ta = this.t0;
/* 149 */       double ga = this.g0;
/* 150 */       double tb = this.t0 + (t1 > this.t0 ? this.convergence : -this.convergence);
/* 151 */       for (int i = 0; i < n; i++)
/*     */       {
/*     */ 
/* 154 */         tb += h;
/* 155 */         interpolator.setInterpolatedTime(tb);
/* 156 */         double gb = this.function.g(tb, interpolator.getInterpolatedState());
/*     */         
/*     */ 
/* 159 */         if ((this.g0Positive ^ gb >= 0.0D))
/*     */         {
/*     */ 
/*     */ 
/* 163 */           this.increasing = (gb >= ga);
/*     */           
/* 165 */           UnivariateRealSolver solver = new BrentSolver(new UnivariateRealFunction() {
/*     */             private final StepInterpolator val$interpolator;
/*     */             
/* 168 */             public double value(double t) throws FunctionEvaluationException { try { this.val$interpolator.setInterpolatedTime(t);
/* 169 */                 return SwitchState.this.function.g(t, this.val$interpolator.getInterpolatedState());
/*     */               } catch (DerivativeException e) {
/* 171 */                 throw new FunctionEvaluationException(t, e);
/*     */               }
/*     */             }
/* 174 */           });
/* 175 */           solver.setAbsoluteAccuracy(this.convergence);
/* 176 */           solver.setMaximalIterationCount(this.maxIterationCount);
/* 177 */           double root = solver.solve(ta, tb);
/* 178 */           if ((Double.isNaN(this.previousEventTime)) || (Math.abs(this.previousEventTime - root) > this.convergence)) {
/* 179 */             this.pendingEventTime = root;
/* 180 */             if ((this.pendingEvent) && (Math.abs(t1 - this.pendingEventTime) <= this.convergence))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 185 */               return false;
/*     */             }
/*     */             
/*     */ 
/* 189 */             this.pendingEvent = true;
/* 190 */             return true;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 195 */           ta = tb;
/* 196 */           ga = gb;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 202 */       this.pendingEvent = false;
/* 203 */       this.pendingEventTime = NaN.0D;
/* 204 */       return false;
/*     */     }
/*     */     catch (FunctionEvaluationException e) {
/* 207 */       Throwable cause = e.getCause();
/* 208 */       if ((cause != null) && ((cause instanceof DerivativeException))) {
/* 209 */         throw ((DerivativeException)cause);
/*     */       }
/* 211 */       throw e;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getEventTime()
/*     */   {
/* 222 */     return this.pendingEventTime;
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
/*     */   public void stepAccepted(double t, double[] y)
/*     */     throws FunctionEvaluationException
/*     */   {
/* 236 */     this.t0 = t;
/* 237 */     this.g0 = this.function.g(t, y);
/*     */     
/* 239 */     if (this.pendingEvent)
/*     */     {
/* 241 */       this.previousEventTime = t;
/* 242 */       this.g0Positive = this.increasing;
/* 243 */       this.nextAction = this.function.eventOccurred(t, y);
/*     */     } else {
/* 245 */       this.g0Positive = (this.g0 >= 0.0D);
/* 246 */       this.nextAction = 3;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stop()
/*     */   {
/* 255 */     return this.nextAction == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean reset(double t, double[] y)
/*     */   {
/* 267 */     if (!this.pendingEvent) {
/* 268 */       return false;
/*     */     }
/*     */     
/* 271 */     if (this.nextAction == 1) {
/* 272 */       this.function.resetState(t, y);
/*     */     }
/* 274 */     this.pendingEvent = false;
/* 275 */     this.pendingEventTime = NaN.0D;
/*     */     
/* 277 */     return (this.nextAction == 1) || (this.nextAction == 2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/SwitchState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */