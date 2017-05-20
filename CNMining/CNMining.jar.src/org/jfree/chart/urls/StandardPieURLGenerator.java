/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.PieDataset;
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
/*     */ public class StandardPieURLGenerator
/*     */   implements PieURLGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1626966402065883419L;
/*  67 */   private String prefix = "index.html";
/*     */   
/*     */ 
/*  70 */   private String categoryParameterName = "category";
/*     */   
/*     */ 
/*  73 */   private String indexParameterName = "pieIndex";
/*     */   
/*     */ 
/*     */ 
/*     */   public StandardPieURLGenerator()
/*     */   {
/*  79 */     this("index.html");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardPieURLGenerator(String prefix)
/*     */   {
/*  88 */     this(prefix, "category");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardPieURLGenerator(String prefix, String categoryParameterName)
/*     */   {
/* 100 */     this(prefix, categoryParameterName, "pieIndex");
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
/*     */   public StandardPieURLGenerator(String prefix, String categoryParameterName, String indexParameterName)
/*     */   {
/* 115 */     if (prefix == null) {
/* 116 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 118 */     if (categoryParameterName == null) {
/* 119 */       throw new IllegalArgumentException("Null 'categoryParameterName' argument.");
/*     */     }
/*     */     
/* 122 */     this.prefix = prefix;
/* 123 */     this.categoryParameterName = categoryParameterName;
/* 124 */     this.indexParameterName = indexParameterName;
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
/*     */   public String generateURL(PieDataset dataset, Comparable key, int pieIndex)
/*     */   {
/* 138 */     String url = this.prefix;
/* 139 */     if (url.indexOf("?") > -1) {
/* 140 */       url = url + "&amp;" + this.categoryParameterName + "=" + URLUtilities.encode(key.toString(), "UTF-8");
/*     */     }
/*     */     else
/*     */     {
/* 144 */       url = url + "?" + this.categoryParameterName + "=" + URLUtilities.encode(key.toString(), "UTF-8");
/*     */     }
/*     */     
/* 147 */     if (this.indexParameterName != null) {
/* 148 */       url = url + "&amp;" + this.indexParameterName + "=" + String.valueOf(pieIndex);
/*     */     }
/*     */     
/* 151 */     return url;
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
/* 162 */     if (obj == this) {
/* 163 */       return true;
/*     */     }
/* 165 */     if (!(obj instanceof StandardPieURLGenerator)) {
/* 166 */       return false;
/*     */     }
/* 168 */     StandardPieURLGenerator that = (StandardPieURLGenerator)obj;
/* 169 */     if (!this.prefix.equals(that.prefix)) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (!this.categoryParameterName.equals(that.categoryParameterName)) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (!ObjectUtilities.equal(this.indexParameterName, that.indexParameterName))
/*     */     {
/* 177 */       return false;
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/StandardPieURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */