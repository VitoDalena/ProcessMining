/*     */ package org.apache.commons.math.estimation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleEstimationProblem
/*     */   implements EstimationProblem
/*     */ {
/*     */   private final List parameters;
/*     */   private final List measurements;
/*     */   
/*     */   public SimpleEstimationProblem()
/*     */   {
/*  49 */     this.parameters = new ArrayList();
/*  50 */     this.measurements = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EstimatedParameter[] getAllParameters()
/*     */   {
/*  58 */     return (EstimatedParameter[])this.parameters.toArray(new EstimatedParameter[this.parameters.size()]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EstimatedParameter[] getUnboundParameters()
/*     */   {
/*  68 */     List unbound = new ArrayList(this.parameters.size());
/*  69 */     for (Iterator iterator = this.parameters.iterator(); iterator.hasNext();) {
/*  70 */       EstimatedParameter p = (EstimatedParameter)iterator.next();
/*  71 */       if (!p.isBound()) {
/*  72 */         unbound.add(p);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  77 */     return (EstimatedParameter[])unbound.toArray(new EstimatedParameter[unbound.size()]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WeightedMeasurement[] getMeasurements()
/*     */   {
/*  86 */     return (WeightedMeasurement[])this.measurements.toArray(new WeightedMeasurement[this.measurements.size()]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void addParameter(EstimatedParameter p)
/*     */   {
/*  93 */     this.parameters.add(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addMeasurement(WeightedMeasurement m)
/*     */   {
/* 101 */     this.measurements.add(m);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/SimpleEstimationProblem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */