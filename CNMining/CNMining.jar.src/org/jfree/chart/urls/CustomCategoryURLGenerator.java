/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.category.CategoryDataset;
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
/*     */ public class CustomCategoryURLGenerator
/*     */   implements CategoryURLGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*  58 */   private ArrayList urlSeries = new ArrayList();
/*     */   
/*     */ 
/*     */ 
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
/*  73 */     return this.urlSeries.size();
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
/*  84 */     int result = 0;
/*  85 */     List urls = (List)this.urlSeries.get(list);
/*  86 */     if (urls != null) {
/*  87 */       result = urls.size();
/*     */     }
/*  89 */     return result;
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
/* 101 */     String result = null;
/* 102 */     if (series < getListCount()) {
/* 103 */       List urls = (List)this.urlSeries.get(series);
/* 104 */       if ((urls != null) && 
/* 105 */         (item < urls.size())) {
/* 106 */         result = (String)urls.get(item);
/*     */       }
/*     */     }
/*     */     
/* 110 */     return result;
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
/*     */   public String generateURL(CategoryDataset dataset, int series, int item)
/*     */   {
/* 123 */     return getURL(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addURLSeries(List urls)
/*     */   {
/* 132 */     List listToAdd = null;
/* 133 */     if (urls != null) {
/* 134 */       listToAdd = new ArrayList(urls);
/*     */     }
/* 136 */     this.urlSeries.add(listToAdd);
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
/* 147 */     if (obj == this) {
/* 148 */       return true;
/*     */     }
/* 150 */     if (!(obj instanceof CustomCategoryURLGenerator)) {
/* 151 */       return false;
/*     */     }
/* 153 */     CustomCategoryURLGenerator generator = (CustomCategoryURLGenerator)obj;
/* 154 */     int listCount = getListCount();
/* 155 */     if (listCount != generator.getListCount()) {
/* 156 */       return false;
/*     */     }
/*     */     
/* 159 */     for (int series = 0; series < listCount; series++) {
/* 160 */       int urlCount = getURLCount(series);
/* 161 */       if (urlCount != generator.getURLCount(series)) {
/* 162 */         return false;
/*     */       }
/*     */       
/* 165 */       for (int item = 0; item < urlCount; item++) {
/* 166 */         String u1 = getURL(series, item);
/* 167 */         String u2 = generator.getURL(series, item);
/* 168 */         if (u1 != null) {
/* 169 */           if (!u1.equals(u2)) {
/* 170 */             return false;
/*     */           }
/*     */         }
/* 173 */         else if (u2 != null) {
/* 174 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 179 */     return true;
/*     */   }
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
/* 191 */     CustomCategoryURLGenerator clone = (CustomCategoryURLGenerator)super.clone();
/*     */     
/* 193 */     clone.urlSeries = new ArrayList(this.urlSeries);
/* 194 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/CustomCategoryURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */