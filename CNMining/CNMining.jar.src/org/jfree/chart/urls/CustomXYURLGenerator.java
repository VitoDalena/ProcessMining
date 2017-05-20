/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomXYURLGenerator
/*     */   implements XYURLGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8565933356596551832L;
/*  67 */   private ArrayList urlSeries = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getListCount()
/*     */   {
/*  82 */     return this.urlSeries.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getURLCount(int list)
/*     */   {
/*  93 */     int result = 0;
/*  94 */     List urls = (List)this.urlSeries.get(list);
/*  95 */     if (urls != null) {
/*  96 */       result = urls.size();
/*     */     }
/*  98 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURL(int series, int item)
/*     */   {
/* 110 */     String result = null;
/* 111 */     if (series < getListCount()) {
/* 112 */       List urls = (List)this.urlSeries.get(series);
/* 113 */       if ((urls != null) && 
/* 114 */         (item < urls.size())) {
/* 115 */         result = (String)urls.get(item);
/*     */       }
/*     */     }
/*     */     
/* 119 */     return result;
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
/* 132 */     return getURL(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addURLSeries(List urls)
/*     */   {
/* 142 */     List listToAdd = null;
/* 143 */     if (urls != null) {
/* 144 */       listToAdd = new ArrayList(urls);
/*     */     }
/* 146 */     this.urlSeries.add(listToAdd);
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
/* 160 */     if (!(obj instanceof CustomXYURLGenerator)) {
/* 161 */       return false;
/*     */     }
/* 163 */     CustomXYURLGenerator that = (CustomXYURLGenerator)obj;
/* 164 */     int listCount = getListCount();
/* 165 */     if (listCount != that.getListCount()) {
/* 166 */       return false;
/*     */     }
/*     */     
/* 169 */     for (int series = 0; series < listCount; series++) {
/* 170 */       int urlCount = getURLCount(series);
/* 171 */       if (urlCount != that.getURLCount(series)) {
/* 172 */         return false;
/*     */       }
/*     */       
/* 175 */       for (int item = 0; item < urlCount; item++) {
/* 176 */         String u1 = getURL(series, item);
/* 177 */         String u2 = that.getURL(series, item);
/* 178 */         if (u1 != null) {
/* 179 */           if (!u1.equals(u2)) {
/* 180 */             return false;
/*     */           }
/*     */           
/*     */         }
/* 184 */         else if (u2 != null) {
/* 185 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 190 */     return true;
/*     */   }
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
/* 203 */     CustomXYURLGenerator clone = (CustomXYURLGenerator)super.clone();
/* 204 */     clone.urlSeries = new ArrayList(this.urlSeries);
/* 205 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/CustomXYURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */