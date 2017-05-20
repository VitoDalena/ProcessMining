/*     */ package org.apache.commons.math.estimation;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WeightedMeasurement
/*     */   implements Serializable
/*     */ {
/*     */   private final double weight;
/*     */   private final double measuredValue;
/*     */   private boolean ignored;
/*     */   
/*     */   public WeightedMeasurement(double weight, double measuredValue)
/*     */   {
/*  66 */     this.weight = weight;
/*  67 */     this.measuredValue = measuredValue;
/*  68 */     this.ignored = false;
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
/*     */   public WeightedMeasurement(double weight, double measuredValue, boolean ignored)
/*     */   {
/*  81 */     this.weight = weight;
/*  82 */     this.measuredValue = measuredValue;
/*  83 */     this.ignored = ignored;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getWeight()
/*     */   {
/*  92 */     return this.weight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMeasuredValue()
/*     */   {
/* 101 */     return this.measuredValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getResidual()
/*     */   {
/* 111 */     return this.measuredValue - getTheoreticalValue();
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
/*     */   public abstract double getTheoreticalValue();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract double getPartial(EstimatedParameter paramEstimatedParameter);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIgnored(boolean ignored)
/*     */   {
/* 146 */     this.ignored = ignored;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isIgnored()
/*     */   {
/* 155 */     return this.ignored;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/WeightedMeasurement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */