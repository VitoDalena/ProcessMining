/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimeSeries;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XisSymbolic;
/*     */ import org.jfree.data.xy.YisSymbolic;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SymbolicXYItemLabelGenerator
/*     */   implements XYItemLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3963400354475494395L;
/*     */   
/*     */   public String generateToolTip(XYDataset data, int series, int item)
/*     */   {
/*     */     String yStr;
/*     */     String yStr;
/*  83 */     if ((data instanceof YisSymbolic)) {
/*  84 */       yStr = ((YisSymbolic)data).getYSymbolicValue(series, item);
/*     */     }
/*     */     else {
/*  87 */       double y = data.getYValue(series, item);
/*  88 */       yStr = Double.toString(round(y, 2)); }
/*     */     String xStr;
/*  90 */     String xStr; if ((data instanceof XisSymbolic)) {
/*  91 */       xStr = ((XisSymbolic)data).getXSymbolicValue(series, item);
/*     */     } else { String xStr;
/*  93 */       if ((data instanceof TimeSeriesCollection)) {
/*  94 */         RegularTimePeriod p = ((TimeSeriesCollection)data).getSeries(series).getTimePeriod(item);
/*     */         
/*     */ 
/*  97 */         xStr = p.toString();
/*     */       }
/*     */       else {
/* 100 */         double x = data.getXValue(series, item);
/* 101 */         xStr = Double.toString(round(x, 2));
/*     */       } }
/* 103 */     return "X: " + xStr + ", Y: " + yStr;
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
/*     */   public String generateLabel(XYDataset dataset, int series, int category)
/*     */   {
/* 117 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static double round(double value, int nb)
/*     */   {
/* 129 */     if (nb <= 0) {
/* 130 */       return Math.floor(value + 0.5D);
/*     */     }
/* 132 */     double p = Math.pow(10.0D, nb);
/* 133 */     double tempval = Math.floor(value * p + 0.5D);
/* 134 */     return tempval / p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 145 */     return super.clone();
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
/* 156 */     if (obj == this) {
/* 157 */       return true;
/*     */     }
/* 159 */     if ((obj instanceof SymbolicXYItemLabelGenerator)) {
/* 160 */       return true;
/*     */     }
/* 162 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 171 */     int result = 127;
/* 172 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/SymbolicXYItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */