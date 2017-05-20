/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.chart.plot.Crosshair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardCrosshairLabelGenerator
/*     */   implements CrosshairLabelGenerator, Serializable
/*     */ {
/*     */   private String labelTemplate;
/*     */   private NumberFormat numberFormat;
/*     */   
/*     */   public StandardCrosshairLabelGenerator()
/*     */   {
/*  66 */     this("{0}", NumberFormat.getNumberInstance());
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
/*     */   public StandardCrosshairLabelGenerator(String labelTemplate, NumberFormat numberFormat)
/*     */   {
/*  80 */     if (labelTemplate == null) {
/*  81 */       throw new IllegalArgumentException("Null 'labelTemplate' argument.");
/*     */     }
/*     */     
/*  84 */     if (numberFormat == null) {
/*  85 */       throw new IllegalArgumentException("Null 'numberFormat' argument.");
/*     */     }
/*     */     
/*  88 */     this.labelTemplate = labelTemplate;
/*  89 */     this.numberFormat = numberFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabelTemplate()
/*     */   {
/*  98 */     return this.labelTemplate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getNumberFormat()
/*     */   {
/* 107 */     return this.numberFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateLabel(Crosshair crosshair)
/*     */   {
/* 118 */     Object[] v = { this.numberFormat.format(crosshair.getValue()) };
/*     */     
/* 120 */     String result = MessageFormat.format(this.labelTemplate, v);
/* 121 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 132 */     if (obj == this) {
/* 133 */       return true;
/*     */     }
/* 135 */     if (!(obj instanceof StandardCrosshairLabelGenerator)) {
/* 136 */       return false;
/*     */     }
/* 138 */     StandardCrosshairLabelGenerator that = (StandardCrosshairLabelGenerator)obj;
/*     */     
/* 140 */     if (!this.labelTemplate.equals(that.labelTemplate)) {
/* 141 */       return false;
/*     */     }
/* 143 */     if (!this.numberFormat.equals(that.numberFormat)) {
/* 144 */       return false;
/*     */     }
/* 146 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 155 */     return this.labelTemplate.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardCrosshairLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */