/*     */ package org.jfree.data.function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NormalDistributionFunction2D
/*     */   implements Function2D
/*     */ {
/*     */   private double mean;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double std;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double factor;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double denominator;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalDistributionFunction2D(double mean, double std)
/*     */   {
/*  71 */     if (std <= 0.0D) {
/*  72 */       throw new IllegalArgumentException("Requires 'std' > 0.");
/*     */     }
/*  74 */     this.mean = mean;
/*  75 */     this.std = std;
/*     */     
/*  77 */     this.factor = (1.0D / (std * Math.sqrt(6.283185307179586D)));
/*  78 */     this.denominator = (2.0D * std * std);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/*  87 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStandardDeviation()
/*     */   {
/*  96 */     return this.std;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue(double x)
/*     */   {
/* 107 */     double z = x - this.mean;
/* 108 */     return this.factor * Math.exp(-z * z / this.denominator);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/function/NormalDistributionFunction2D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */