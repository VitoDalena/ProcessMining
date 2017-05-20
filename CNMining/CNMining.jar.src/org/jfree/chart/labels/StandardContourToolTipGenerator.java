/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.contour.ContourDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class StandardContourToolTipGenerator
/*     */   implements ContourToolTipGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1881659351247502711L;
/*  70 */   private DecimalFormat valueForm = new DecimalFormat("##.###");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateToolTip(ContourDataset data, int item)
/*     */   {
/*  82 */     double x = data.getXValue(0, item);
/*  83 */     double y = data.getYValue(0, item);
/*  84 */     double z = data.getZValue(0, item);
/*  85 */     String xString = null;
/*     */     
/*  87 */     if (data.isDateAxis(0)) {
/*  88 */       SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
/*     */       
/*  90 */       StringBuffer strbuf = new StringBuffer();
/*  91 */       strbuf = formatter.format(new Date(x), strbuf, new FieldPosition(0));
/*     */       
/*     */ 
/*  94 */       xString = strbuf.toString();
/*     */     }
/*     */     else {
/*  97 */       xString = this.valueForm.format(x);
/*     */     }
/*  99 */     if (!Double.isNaN(z)) {
/* 100 */       return "X: " + xString + ", Y: " + this.valueForm.format(y) + ", Z: " + this.valueForm.format(z);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 105 */     return "X: " + xString + ", Y: " + this.valueForm.format(y) + ", Z: no data";
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 121 */     if (obj == this) {
/* 122 */       return true;
/*     */     }
/*     */     
/* 125 */     if (!(obj instanceof StandardContourToolTipGenerator)) {
/* 126 */       return false;
/*     */     }
/* 128 */     StandardContourToolTipGenerator that = (StandardContourToolTipGenerator)obj;
/*     */     
/* 130 */     if (this.valueForm != null) {
/* 131 */       return this.valueForm.equals(that.valueForm);
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardContourToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */