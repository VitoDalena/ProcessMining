/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class StandardXYURLGenerator
/*     */   implements XYURLGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1771624523496595382L;
/*     */   public static final String DEFAULT_PREFIX = "index.html";
/*     */   public static final String DEFAULT_SERIES_PARAMETER = "series";
/*     */   public static final String DEFAULT_ITEM_PARAMETER = "item";
/*     */   private String prefix;
/*     */   private String seriesParameterName;
/*     */   private String itemParameterName;
/*     */   
/*     */   public StandardXYURLGenerator()
/*     */   {
/*  88 */     this("index.html", "series", "item");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardXYURLGenerator(String prefix)
/*     */   {
/*  99 */     this(prefix, "series", "item");
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
/*     */   public StandardXYURLGenerator(String prefix, String seriesParameterName, String itemParameterName)
/*     */   {
/* 114 */     if (prefix == null) {
/* 115 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 117 */     if (seriesParameterName == null) {
/* 118 */       throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
/*     */     }
/*     */     
/* 121 */     if (itemParameterName == null) {
/* 122 */       throw new IllegalArgumentException("Null 'itemParameterName' argument.");
/*     */     }
/*     */     
/* 125 */     this.prefix = prefix;
/* 126 */     this.seriesParameterName = seriesParameterName;
/* 127 */     this.itemParameterName = itemParameterName;
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
/*     */   public String generateURL(XYDataset dataset, int series, int item)
/*     */   {
/* 141 */     String url = this.prefix;
/* 142 */     boolean firstParameter = url.indexOf("?") == -1;
/* 143 */     url = url + (firstParameter ? "?" : "&amp;");
/* 144 */     url = url + this.seriesParameterName + "=" + series + "&amp;" + this.itemParameterName + "=" + item;
/*     */     
/* 146 */     return url;
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
/* 157 */     if (obj == this) {
/* 158 */       return true;
/*     */     }
/* 160 */     if (!(obj instanceof StandardXYURLGenerator)) {
/* 161 */       return false;
/*     */     }
/* 163 */     StandardXYURLGenerator that = (StandardXYURLGenerator)obj;
/* 164 */     if (!ObjectUtilities.equal(that.prefix, this.prefix)) {
/* 165 */       return false;
/*     */     }
/* 167 */     if (!ObjectUtilities.equal(that.seriesParameterName, this.seriesParameterName))
/*     */     {
/* 169 */       return false;
/*     */     }
/* 171 */     if (!ObjectUtilities.equal(that.itemParameterName, this.itemParameterName))
/*     */     {
/* 173 */       return false;
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/StandardXYURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */