/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.category.CategoryDataset;
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
/*     */ public class StandardCategoryURLGenerator
/*     */   implements CategoryURLGenerator, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2276668053074881909L;
/*  73 */   private String prefix = "index.html";
/*     */   
/*     */ 
/*  76 */   private String seriesParameterName = "series";
/*     */   
/*     */ 
/*  79 */   private String categoryParameterName = "category";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryURLGenerator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryURLGenerator(String prefix)
/*     */   {
/*  94 */     if (prefix == null) {
/*  95 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/*  97 */     this.prefix = prefix;
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
/*     */   public StandardCategoryURLGenerator(String prefix, String seriesParameterName, String categoryParameterName)
/*     */   {
/* 113 */     if (prefix == null) {
/* 114 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 116 */     if (seriesParameterName == null) {
/* 117 */       throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
/*     */     }
/*     */     
/* 120 */     if (categoryParameterName == null) {
/* 121 */       throw new IllegalArgumentException("Null 'categoryParameterName' argument.");
/*     */     }
/*     */     
/* 124 */     this.prefix = prefix;
/* 125 */     this.seriesParameterName = seriesParameterName;
/* 126 */     this.categoryParameterName = categoryParameterName;
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
/*     */   public String generateURL(CategoryDataset dataset, int series, int category)
/*     */   {
/* 141 */     String url = this.prefix;
/* 142 */     Comparable seriesKey = dataset.getRowKey(series);
/* 143 */     Comparable categoryKey = dataset.getColumnKey(category);
/* 144 */     boolean firstParameter = url.indexOf("?") == -1;
/* 145 */     url = url + (firstParameter ? "?" : "&amp;");
/* 146 */     url = url + this.seriesParameterName + "=" + URLUtilities.encode(seriesKey.toString(), "UTF-8");
/*     */     
/* 148 */     url = url + "&amp;" + this.categoryParameterName + "=" + URLUtilities.encode(categoryKey.toString(), "UTF-8");
/*     */     
/* 150 */     return url;
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 165 */     return super.clone();
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
/* 176 */     if (obj == this) {
/* 177 */       return true;
/*     */     }
/* 179 */     if (!(obj instanceof StandardCategoryURLGenerator)) {
/* 180 */       return false;
/*     */     }
/* 182 */     StandardCategoryURLGenerator that = (StandardCategoryURLGenerator)obj;
/* 183 */     if (!ObjectUtilities.equal(this.prefix, that.prefix)) {
/* 184 */       return false;
/*     */     }
/*     */     
/* 187 */     if (!ObjectUtilities.equal(this.seriesParameterName, that.seriesParameterName))
/*     */     {
/* 189 */       return false;
/*     */     }
/* 191 */     if (!ObjectUtilities.equal(this.categoryParameterName, that.categoryParameterName))
/*     */     {
/* 193 */       return false;
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 205 */     int result = this.prefix != null ? this.prefix.hashCode() : 0;
/* 206 */     result = 29 * result + (this.seriesParameterName != null ? this.seriesParameterName.hashCode() : 0);
/*     */     
/*     */ 
/* 209 */     result = 29 * result + (this.categoryParameterName != null ? this.categoryParameterName.hashCode() : 0);
/*     */     
/*     */ 
/* 212 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/StandardCategoryURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */