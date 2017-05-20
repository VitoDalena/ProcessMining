/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class IntervalXYItemLabelGenerator
/*     */   extends AbstractXYItemLabelGenerator
/*     */   implements XYItemLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   public static final String DEFAULT_ITEM_LABEL_FORMAT = "{5} - {6}";
/*     */   
/*     */   public IntervalXYItemLabelGenerator()
/*     */   {
/*  70 */     this("{5} - {6}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
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
/*     */   public IntervalXYItemLabelGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat)
/*     */   {
/*  87 */     super(formatString, xFormat, yFormat);
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
/*     */   public IntervalXYItemLabelGenerator(String formatString, DateFormat xFormat, NumberFormat yFormat)
/*     */   {
/* 103 */     super(formatString, xFormat, yFormat);
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
/*     */ 
/*     */   public IntervalXYItemLabelGenerator(String formatString, NumberFormat xFormat, DateFormat yFormat)
/*     */   {
/* 121 */     super(formatString, xFormat, yFormat);
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
/*     */   public IntervalXYItemLabelGenerator(String formatString, DateFormat xFormat, DateFormat yFormat)
/*     */   {
/* 137 */     super(formatString, xFormat, yFormat);
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
/*     */   protected Object[] createItemArray(XYDataset dataset, int series, int item)
/*     */   {
/* 154 */     IntervalXYDataset intervalDataset = null;
/* 155 */     if ((dataset instanceof IntervalXYDataset)) {
/* 156 */       intervalDataset = (IntervalXYDataset)dataset;
/*     */     }
/* 158 */     Object[] result = new Object[7];
/* 159 */     result[0] = dataset.getSeriesKey(series).toString();
/*     */     
/* 161 */     double x = dataset.getXValue(series, item);
/* 162 */     double xs = x;
/* 163 */     double xe = x;
/* 164 */     double y = dataset.getYValue(series, item);
/* 165 */     double ys = y;
/* 166 */     double ye = y;
/* 167 */     if (intervalDataset != null) {
/* 168 */       xs = intervalDataset.getStartXValue(series, item);
/* 169 */       xe = intervalDataset.getEndXValue(series, item);
/* 170 */       ys = intervalDataset.getStartYValue(series, item);
/* 171 */       ye = intervalDataset.getEndYValue(series, item);
/*     */     }
/*     */     
/* 174 */     DateFormat xdf = getXDateFormat();
/* 175 */     if (xdf != null) {
/* 176 */       result[1] = xdf.format(new Date(x));
/* 177 */       result[2] = xdf.format(new Date(xs));
/* 178 */       result[3] = xdf.format(new Date(xe));
/*     */     }
/*     */     else {
/* 181 */       NumberFormat xnf = getXFormat();
/* 182 */       result[1] = xnf.format(x);
/* 183 */       result[2] = xnf.format(xs);
/* 184 */       result[3] = xnf.format(xe);
/*     */     }
/*     */     
/* 187 */     NumberFormat ynf = getYFormat();
/* 188 */     DateFormat ydf = getYDateFormat();
/* 189 */     if ((Double.isNaN(y)) && (dataset.getY(series, item) == null)) {
/* 190 */       result[4] = getNullYString();
/*     */ 
/*     */     }
/* 193 */     else if (ydf != null) {
/* 194 */       result[4] = ydf.format(new Date(y));
/*     */     }
/*     */     else {
/* 197 */       result[4] = ynf.format(y);
/*     */     }
/*     */     
/* 200 */     if ((Double.isNaN(ys)) && (intervalDataset.getStartY(series, item) == null))
/*     */     {
/* 202 */       result[5] = getNullYString();
/*     */ 
/*     */     }
/* 205 */     else if (ydf != null) {
/* 206 */       result[5] = ydf.format(new Date(ys));
/*     */     }
/*     */     else {
/* 209 */       result[5] = ynf.format(ys);
/*     */     }
/*     */     
/* 212 */     if ((Double.isNaN(ye)) && (intervalDataset.getEndY(series, item) == null))
/*     */     {
/* 214 */       result[6] = getNullYString();
/*     */ 
/*     */     }
/* 217 */     else if (ydf != null) {
/* 218 */       result[6] = ydf.format(new Date(ye));
/*     */     }
/*     */     else {
/* 221 */       result[6] = ynf.format(ye);
/*     */     }
/*     */     
/* 224 */     return result;
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
/*     */   public String generateLabel(XYDataset dataset, int series, int item)
/*     */   {
/* 237 */     return generateLabelString(dataset, series, item);
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
/* 248 */     return super.clone();
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
/* 259 */     if (obj == this) {
/* 260 */       return true;
/*     */     }
/* 262 */     if (!(obj instanceof IntervalXYItemLabelGenerator)) {
/* 263 */       return false;
/*     */     }
/* 265 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/IntervalXYItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */