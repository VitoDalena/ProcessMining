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
/*     */ public class EstimatedParameter
/*     */   implements Serializable
/*     */ {
/*     */   private String name;
/*     */   protected double estimate;
/*     */   private boolean bound;
/*     */   private static final long serialVersionUID = -555440800213416949L;
/*     */   
/*     */   public EstimatedParameter(String name, double firstEstimate)
/*     */   {
/*  45 */     this.name = name;
/*  46 */     this.estimate = firstEstimate;
/*  47 */     this.bound = false;
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
/*     */   public EstimatedParameter(String name, double firstEstimate, boolean bound)
/*     */   {
/*  60 */     this.name = name;
/*  61 */     this.estimate = firstEstimate;
/*  62 */     this.bound = bound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EstimatedParameter(EstimatedParameter parameter)
/*     */   {
/*  70 */     this.name = parameter.name;
/*  71 */     this.estimate = parameter.estimate;
/*  72 */     this.bound = parameter.bound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEstimate(double estimate)
/*     */   {
/*  79 */     this.estimate = estimate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getEstimate()
/*     */   {
/*  86 */     return this.estimate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  93 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBound(boolean bound)
/*     */   {
/* 101 */     this.bound = bound;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBound()
/*     */   {
/* 107 */     return this.bound;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/EstimatedParameter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */