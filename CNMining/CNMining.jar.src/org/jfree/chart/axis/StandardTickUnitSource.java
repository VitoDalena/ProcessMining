/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardTickUnitSource
/*     */   implements TickUnitSource, Serializable
/*     */ {
/*  56 */   private static final double LOG_10_VALUE = Math.log(10.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TickUnit getLargerTickUnit(TickUnit unit)
/*     */   {
/*  73 */     double x = unit.getSize();
/*  74 */     double log = Math.log(x) / LOG_10_VALUE;
/*  75 */     double higher = Math.ceil(log);
/*  76 */     return new NumberTickUnit(Math.pow(10.0D, higher), new DecimalFormat("0.0E0"));
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
/*     */   public TickUnit getCeilingTickUnit(TickUnit unit)
/*     */   {
/*  89 */     return getLargerTickUnit(unit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TickUnit getCeilingTickUnit(double size)
/*     */   {
/* 101 */     double log = Math.log(size) / LOG_10_VALUE;
/* 102 */     double higher = Math.ceil(log);
/* 103 */     return new NumberTickUnit(Math.pow(10.0D, higher), new DecimalFormat("0.0E0"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 115 */     if (obj == this) {
/* 116 */       return true;
/*     */     }
/* 118 */     return obj instanceof StandardTickUnitSource;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 127 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/StandardTickUnitSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */