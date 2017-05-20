/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContinuousOutputModel
/*     */   implements StepHandler, Serializable
/*     */ {
/*     */   private double initialTime;
/*     */   private double finalTime;
/*     */   private boolean forward;
/*     */   private int index;
/*     */   private ArrayList steps;
/*     */   private static final long serialVersionUID = 2259286184268533249L;
/*     */   
/*     */   public ContinuousOutputModel()
/*     */   {
/*  88 */     this.steps = new ArrayList();
/*  89 */     reset();
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
/*     */   public void append(ContinuousOutputModel model)
/*     */     throws DerivativeException
/*     */   {
/* 103 */     if (model.steps.size() == 0) {
/* 104 */       return;
/*     */     }
/*     */     
/* 107 */     if (this.steps.size() == 0) {
/* 108 */       this.initialTime = model.initialTime;
/* 109 */       this.forward = model.forward;
/*     */     }
/*     */     else {
/* 112 */       if (getInterpolatedState().length != model.getInterpolatedState().length) {
/* 113 */         throw new IllegalArgumentException("state vector dimension mismatch");
/*     */       }
/*     */       
/* 116 */       if ((this.forward ^ model.forward)) {
/* 117 */         throw new IllegalArgumentException("propagation direction mismatch");
/*     */       }
/*     */       
/* 120 */       StepInterpolator lastInterpolator = (StepInterpolator)this.steps.get(this.index);
/* 121 */       double current = lastInterpolator.getCurrentTime();
/* 122 */       double previous = lastInterpolator.getPreviousTime();
/* 123 */       double step = current - previous;
/* 124 */       double gap = model.getInitialTime() - current;
/* 125 */       if (Math.abs(gap) > 0.001D * Math.abs(step)) {
/* 126 */         throw new IllegalArgumentException("hole between time ranges");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 131 */     for (Iterator iter = model.steps.iterator(); iter.hasNext();) {
/* 132 */       this.steps.add(((AbstractStepInterpolator)iter.next()).copy());
/*     */     }
/*     */     
/* 135 */     this.index = (this.steps.size() - 1);
/* 136 */     this.finalTime = ((StepInterpolator)this.steps.get(this.index)).getCurrentTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean requiresDenseOutput()
/*     */   {
/* 147 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 155 */     this.initialTime = NaN.0D;
/* 156 */     this.finalTime = NaN.0D;
/* 157 */     this.forward = true;
/* 158 */     this.index = 0;
/* 159 */     this.steps.clear();
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
/*     */   public void handleStep(StepInterpolator interpolator, boolean isLast)
/*     */     throws DerivativeException
/*     */   {
/* 173 */     AbstractStepInterpolator ai = (AbstractStepInterpolator)interpolator;
/*     */     
/* 175 */     if (this.steps.size() == 0) {
/* 176 */       this.initialTime = interpolator.getPreviousTime();
/* 177 */       this.forward = interpolator.isForward();
/*     */     }
/*     */     
/* 180 */     this.steps.add(ai.copy());
/*     */     
/* 182 */     if (isLast) {
/* 183 */       this.finalTime = ai.getCurrentTime();
/* 184 */       this.index = (this.steps.size() - 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getInitialTime()
/*     */   {
/* 194 */     return this.initialTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getFinalTime()
/*     */   {
/* 202 */     return this.finalTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getInterpolatedTime()
/*     */   {
/* 212 */     return ((StepInterpolator)this.steps.get(this.index)).getInterpolatedTime();
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
/*     */   public void setInterpolatedTime(double time)
/*     */   {
/*     */     try
/*     */     {
/* 231 */       int iMin = 0;
/* 232 */       StepInterpolator sMin = (StepInterpolator)this.steps.get(iMin);
/* 233 */       double tMin = 0.5D * (sMin.getPreviousTime() + sMin.getCurrentTime());
/*     */       
/* 235 */       int iMax = this.steps.size() - 1;
/* 236 */       StepInterpolator sMax = (StepInterpolator)this.steps.get(iMax);
/* 237 */       double tMax = 0.5D * (sMax.getPreviousTime() + sMax.getCurrentTime());
/*     */       
/*     */ 
/*     */ 
/* 241 */       if (locatePoint(time, sMin) <= 0) {
/* 242 */         this.index = iMin;
/* 243 */         sMin.setInterpolatedTime(time);
/* 244 */         return;
/*     */       }
/* 246 */       if (locatePoint(time, sMax) >= 0) {
/* 247 */         this.index = iMax;
/* 248 */         sMax.setInterpolatedTime(time);
/* 249 */         return;
/*     */       }
/*     */       
/*     */ 
/* 253 */       while (iMax - iMin > 5)
/*     */       {
/*     */ 
/* 256 */         StepInterpolator si = (StepInterpolator)this.steps.get(this.index);
/* 257 */         int location = locatePoint(time, si);
/* 258 */         if (location < 0) {
/* 259 */           iMax = this.index;
/* 260 */           tMax = 0.5D * (si.getPreviousTime() + si.getCurrentTime());
/* 261 */         } else if (location > 0) {
/* 262 */           iMin = this.index;
/* 263 */           tMin = 0.5D * (si.getPreviousTime() + si.getCurrentTime());
/*     */         }
/*     */         else {
/* 266 */           si.setInterpolatedTime(time);
/* 267 */           return;
/*     */         }
/*     */         
/*     */ 
/* 271 */         int iMed = (iMin + iMax) / 2;
/* 272 */         StepInterpolator sMed = (StepInterpolator)this.steps.get(iMed);
/* 273 */         double tMed = 0.5D * (sMed.getPreviousTime() + sMed.getCurrentTime());
/*     */         
/* 275 */         if ((Math.abs(tMed - tMin) < 1.0E-6D) || (Math.abs(tMax - tMed) < 1.0E-6D))
/*     */         {
/* 277 */           this.index = iMed;
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 282 */           double d12 = tMax - tMed;
/* 283 */           double d23 = tMed - tMin;
/* 284 */           double d13 = tMax - tMin;
/* 285 */           double dt1 = time - tMax;
/* 286 */           double dt2 = time - tMed;
/* 287 */           double dt3 = time - tMin;
/* 288 */           double iLagrange = (dt2 * dt3 * d23 * iMax - dt1 * dt3 * d13 * iMed + dt1 * dt2 * d12 * iMin) / (d12 * d23 * d13);
/*     */           
/*     */ 
/*     */ 
/* 292 */           this.index = ((int)Math.rint(iLagrange));
/*     */         }
/*     */         
/*     */ 
/* 296 */         int low = Math.max(iMin + 1, (9 * iMin + iMax) / 10);
/* 297 */         int high = Math.min(iMax - 1, (iMin + 9 * iMax) / 10);
/* 298 */         if (this.index < low) {
/* 299 */           this.index = low;
/* 300 */         } else if (this.index > high) {
/* 301 */           this.index = high;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 307 */       this.index = iMin;
/* 308 */       while ((this.index <= iMax) && (locatePoint(time, (StepInterpolator)this.steps.get(this.index)) > 0))
/*     */       {
/* 310 */         this.index += 1;
/*     */       }
/*     */       
/* 313 */       StepInterpolator si = (StepInterpolator)this.steps.get(this.index);
/*     */       
/* 315 */       si.setInterpolatedTime(time);
/*     */     }
/*     */     catch (DerivativeException de) {
/* 318 */       throw new RuntimeException("unexpected DerivativeException caught: " + de.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getInterpolatedState()
/*     */   {
/* 329 */     return ((StepInterpolator)this.steps.get(this.index)).getInterpolatedState();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int locatePoint(double time, StepInterpolator interval)
/*     */   {
/* 340 */     if (this.forward) {
/* 341 */       if (time < interval.getPreviousTime())
/* 342 */         return -1;
/* 343 */       if (time > interval.getCurrentTime()) {
/* 344 */         return 1;
/*     */       }
/* 346 */       return 0;
/*     */     }
/*     */     
/* 349 */     if (time > interval.getPreviousTime())
/* 350 */       return -1;
/* 351 */     if (time < interval.getCurrentTime()) {
/* 352 */       return 1;
/*     */     }
/* 354 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/ContinuousOutputModel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */