/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SwitchingFunctionsHandler
/*     */ {
/*     */   private ArrayList functions;
/*     */   private SwitchState first;
/*     */   private boolean initialized;
/*     */   
/*     */   public SwitchingFunctionsHandler()
/*     */   {
/*  40 */     this.functions = new ArrayList();
/*  41 */     this.first = null;
/*  42 */     this.initialized = false;
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
/*     */   public void add(SwitchingFunction function, double maxCheckInterval, double convergence, int maxIterationCount)
/*     */   {
/*  56 */     this.functions.add(new SwitchState(function, maxCheckInterval, convergence, maxIterationCount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  64 */     return this.functions.isEmpty();
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
/*     */   public boolean evaluateStep(StepInterpolator interpolator)
/*     */     throws DerivativeException, IntegratorException
/*     */   {
/*     */     try
/*     */     {
/*  82 */       this.first = null;
/*  83 */       if (this.functions.isEmpty())
/*     */       {
/*     */ 
/*     */ 
/*  87 */         return false;
/*     */       }
/*     */       
/*  90 */       if (!this.initialized)
/*     */       {
/*     */ 
/*  93 */         double t0 = interpolator.getPreviousTime();
/*  94 */         interpolator.setInterpolatedTime(t0);
/*  95 */         double[] y = interpolator.getInterpolatedState();
/*  96 */         for (Iterator iter = this.functions.iterator(); iter.hasNext();) {
/*  97 */           ((SwitchState)iter.next()).reinitializeBegin(t0, y);
/*     */         }
/*     */         
/* 100 */         this.initialized = true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 105 */       for (Iterator iter = this.functions.iterator(); iter.hasNext();)
/*     */       {
/* 107 */         SwitchState state = (SwitchState)iter.next();
/* 108 */         if (state.evaluateStep(interpolator)) {
/* 109 */           if (this.first == null) {
/* 110 */             this.first = state;
/*     */           }
/* 112 */           else if (interpolator.isForward()) {
/* 113 */             if (state.getEventTime() < this.first.getEventTime()) {
/* 114 */               this.first = state;
/*     */             }
/*     */           }
/* 117 */           else if (state.getEventTime() > this.first.getEventTime()) {
/* 118 */             this.first = state;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 126 */       return this.first != null;
/*     */     }
/*     */     catch (FunctionEvaluationException fee) {
/* 129 */       throw new IntegratorException(fee);
/*     */     } catch (ConvergenceException ce) {
/* 131 */       throw new IntegratorException(ce);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getEventTime()
/*     */   {
/* 143 */     return this.first == null ? NaN.0D : this.first.getEventTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stepAccepted(double t, double[] y)
/*     */     throws IntegratorException
/*     */   {
/*     */     try
/*     */     {
/* 158 */       for (iter = this.functions.iterator(); iter.hasNext();)
/* 159 */         ((SwitchState)iter.next()).stepAccepted(t, y);
/*     */     } catch (FunctionEvaluationException fee) {
/*     */       Iterator iter;
/* 162 */       throw new IntegratorException(fee);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stop()
/*     */   {
/* 171 */     for (Iterator iter = this.functions.iterator(); iter.hasNext();) {
/* 172 */       if (((SwitchState)iter.next()).stop()) {
/* 173 */         return true;
/*     */       }
/*     */     }
/* 176 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean reset(double t, double[] y)
/*     */   {
/* 187 */     boolean resetDerivatives = false;
/* 188 */     for (Iterator iter = this.functions.iterator(); iter.hasNext();) {
/* 189 */       if (((SwitchState)iter.next()).reset(t, y)) {
/* 190 */         resetDerivatives = true;
/*     */       }
/*     */     }
/* 193 */     return resetDerivatives;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/SwitchingFunctionsHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */