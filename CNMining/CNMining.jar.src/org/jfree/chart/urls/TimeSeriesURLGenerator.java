/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeSeriesURLGenerator
/*     */   implements XYURLGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -9122773175671182445L;
/*  68 */   private DateFormat dateFormat = DateFormat.getInstance();
/*     */   
/*     */ 
/*  71 */   private String prefix = "index.html";
/*     */   
/*     */ 
/*  74 */   private String seriesParameterName = "series";
/*     */   
/*     */ 
/*  77 */   private String itemParameterName = "item";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesURLGenerator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesURLGenerator(DateFormat dateFormat, String prefix, String seriesParameterName, String itemParameterName)
/*     */   {
/* 100 */     if (dateFormat == null) {
/* 101 */       throw new IllegalArgumentException("Null 'dateFormat' argument.");
/*     */     }
/* 103 */     if (prefix == null) {
/* 104 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 106 */     if (seriesParameterName == null) {
/* 107 */       throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
/*     */     }
/*     */     
/* 110 */     if (itemParameterName == null) {
/* 111 */       throw new IllegalArgumentException("Null 'itemParameterName' argument.");
/*     */     }
/*     */     
/*     */ 
/* 115 */     this.dateFormat = ((DateFormat)dateFormat.clone());
/* 116 */     this.prefix = prefix;
/* 117 */     this.seriesParameterName = seriesParameterName;
/* 118 */     this.itemParameterName = itemParameterName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getDateFormat()
/*     */   {
/* 130 */     return (DateFormat)this.dateFormat.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 141 */     return this.prefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSeriesParameterName()
/*     */   {
/* 152 */     return this.seriesParameterName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getItemParameterName()
/*     */   {
/* 163 */     return this.itemParameterName;
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
/*     */   public String generateURL(XYDataset dataset, int series, int item)
/*     */   {
/* 176 */     String result = this.prefix;
/* 177 */     boolean firstParameter = result.indexOf("?") == -1;
/* 178 */     Comparable seriesKey = dataset.getSeriesKey(series);
/* 179 */     if (seriesKey != null) {
/* 180 */       result = result + (firstParameter ? "?" : "&amp;");
/* 181 */       result = result + this.seriesParameterName + "=" + URLUtilities.encode(seriesKey.toString(), "UTF-8");
/*     */       
/* 183 */       firstParameter = false;
/*     */     }
/*     */     
/* 186 */     long x = dataset.getXValue(series, item);
/* 187 */     String xValue = this.dateFormat.format(new Date(x));
/* 188 */     result = result + (firstParameter ? "?" : "&amp;");
/* 189 */     result = result + this.itemParameterName + "=" + URLUtilities.encode(xValue, "UTF-8");
/*     */     
/*     */ 
/* 192 */     return result;
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
/* 203 */     if (obj == this) {
/* 204 */       return true;
/*     */     }
/* 206 */     if (!(obj instanceof TimeSeriesURLGenerator)) {
/* 207 */       return false;
/*     */     }
/* 209 */     TimeSeriesURLGenerator that = (TimeSeriesURLGenerator)obj;
/* 210 */     if (!this.dateFormat.equals(that.dateFormat)) {
/* 211 */       return false;
/*     */     }
/* 213 */     if (!this.itemParameterName.equals(that.itemParameterName)) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (!this.prefix.equals(that.prefix)) {
/* 217 */       return false;
/*     */     }
/* 219 */     if (!this.seriesParameterName.equals(that.seriesParameterName)) {
/* 220 */       return false;
/*     */     }
/* 222 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/TimeSeriesURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */