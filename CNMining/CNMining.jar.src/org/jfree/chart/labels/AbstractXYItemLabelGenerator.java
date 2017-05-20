/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractXYItemLabelGenerator
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5869744396278660636L;
/*     */   private String formatString;
/*     */   private NumberFormat xFormat;
/*     */   private DateFormat xDateFormat;
/*     */   private NumberFormat yFormat;
/*     */   private DateFormat yDateFormat;
/*  91 */   private String nullYString = "null";
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractXYItemLabelGenerator()
/*     */   {
/*  97 */     this("{2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
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
/*     */   protected AbstractXYItemLabelGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat)
/*     */   {
/* 115 */     if (formatString == null) {
/* 116 */       throw new IllegalArgumentException("Null 'formatString' argument.");
/*     */     }
/* 118 */     if (xFormat == null) {
/* 119 */       throw new IllegalArgumentException("Null 'xFormat' argument.");
/*     */     }
/* 121 */     if (yFormat == null) {
/* 122 */       throw new IllegalArgumentException("Null 'yFormat' argument.");
/*     */     }
/* 124 */     this.formatString = formatString;
/* 125 */     this.xFormat = xFormat;
/* 126 */     this.yFormat = yFormat;
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
/*     */   protected AbstractXYItemLabelGenerator(String formatString, DateFormat xFormat, NumberFormat yFormat)
/*     */   {
/* 144 */     this(formatString, NumberFormat.getInstance(), yFormat);
/* 145 */     this.xDateFormat = xFormat;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractXYItemLabelGenerator(String formatString, NumberFormat xFormat, DateFormat yFormat)
/*     */   {
/* 166 */     this(formatString, xFormat, NumberFormat.getInstance());
/* 167 */     this.yDateFormat = yFormat;
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
/*     */   protected AbstractXYItemLabelGenerator(String formatString, DateFormat xFormat, DateFormat yFormat)
/*     */   {
/* 184 */     this(formatString, NumberFormat.getInstance(), NumberFormat.getInstance());
/*     */     
/* 186 */     this.xDateFormat = xFormat;
/* 187 */     this.yDateFormat = yFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFormatString()
/*     */   {
/* 198 */     return this.formatString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getXFormat()
/*     */   {
/* 207 */     return this.xFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getXDateFormat()
/*     */   {
/* 216 */     return this.xDateFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getYFormat()
/*     */   {
/* 225 */     return this.yFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getYDateFormat()
/*     */   {
/* 234 */     return this.yDateFormat;
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
/*     */   public String generateLabelString(XYDataset dataset, int series, int item)
/*     */   {
/* 247 */     String result = null;
/* 248 */     Object[] items = createItemArray(dataset, series, item);
/* 249 */     result = MessageFormat.format(this.formatString, items);
/* 250 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNullYString()
/*     */   {
/* 261 */     return this.nullYString;
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
/*     */   protected Object[] createItemArray(XYDataset dataset, int series, int item)
/*     */   {
/* 277 */     Object[] result = new Object[3];
/* 278 */     result[0] = dataset.getSeriesKey(series).toString();
/*     */     
/* 280 */     double x = dataset.getXValue(series, item);
/* 281 */     if (this.xDateFormat != null) {
/* 282 */       result[1] = this.xDateFormat.format(new Date(x));
/*     */     }
/*     */     else {
/* 285 */       result[1] = this.xFormat.format(x);
/*     */     }
/*     */     
/* 288 */     double y = dataset.getYValue(series, item);
/* 289 */     if ((Double.isNaN(y)) && (dataset.getY(series, item) == null)) {
/* 290 */       result[2] = this.nullYString;
/*     */ 
/*     */     }
/* 293 */     else if (this.yDateFormat != null) {
/* 294 */       result[2] = this.yDateFormat.format(new Date(y));
/*     */     }
/*     */     else {
/* 297 */       result[2] = this.yFormat.format(y);
/*     */     }
/*     */     
/* 300 */     return result;
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
/* 311 */     if (obj == this) {
/* 312 */       return true;
/*     */     }
/* 314 */     if (!(obj instanceof AbstractXYItemLabelGenerator)) {
/* 315 */       return false;
/*     */     }
/* 317 */     AbstractXYItemLabelGenerator that = (AbstractXYItemLabelGenerator)obj;
/* 318 */     if (!this.formatString.equals(that.formatString)) {
/* 319 */       return false;
/*     */     }
/* 321 */     if (!ObjectUtilities.equal(this.xFormat, that.xFormat)) {
/* 322 */       return false;
/*     */     }
/* 324 */     if (!ObjectUtilities.equal(this.xDateFormat, that.xDateFormat)) {
/* 325 */       return false;
/*     */     }
/* 327 */     if (!ObjectUtilities.equal(this.yFormat, that.yFormat)) {
/* 328 */       return false;
/*     */     }
/* 330 */     if (!ObjectUtilities.equal(this.yDateFormat, that.yDateFormat)) {
/* 331 */       return false;
/*     */     }
/* 333 */     if (!this.nullYString.equals(that.nullYString)) {
/* 334 */       return false;
/*     */     }
/* 336 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 345 */     int result = 127;
/* 346 */     result = HashUtilities.hashCode(result, this.formatString);
/* 347 */     result = HashUtilities.hashCode(result, this.xFormat);
/* 348 */     result = HashUtilities.hashCode(result, this.xDateFormat);
/* 349 */     result = HashUtilities.hashCode(result, this.yFormat);
/* 350 */     result = HashUtilities.hashCode(result, this.yDateFormat);
/* 351 */     return result;
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
/* 362 */     AbstractXYItemLabelGenerator clone = (AbstractXYItemLabelGenerator)super.clone();
/*     */     
/* 364 */     if (this.xFormat != null) {
/* 365 */       clone.xFormat = ((NumberFormat)this.xFormat.clone());
/*     */     }
/* 367 */     if (this.yFormat != null) {
/* 368 */       clone.yFormat = ((NumberFormat)this.yFormat.clone());
/*     */     }
/* 370 */     if (this.xDateFormat != null) {
/* 371 */       clone.xDateFormat = ((DateFormat)this.xDateFormat.clone());
/*     */     }
/* 373 */     if (this.yDateFormat != null) {
/* 374 */       clone.yDateFormat = ((DateFormat)this.yDateFormat.clone());
/*     */     }
/* 376 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/AbstractXYItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */