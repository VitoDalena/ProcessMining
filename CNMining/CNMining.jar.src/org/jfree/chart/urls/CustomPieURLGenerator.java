/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jfree.data.general.PieDataset;
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
/*     */ public class CustomPieURLGenerator
/*     */   implements PieURLGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7100607670144900503L;
/*     */   private ArrayList urls;
/*     */   
/*     */   public CustomPieURLGenerator()
/*     */   {
/*  74 */     this.urls = new ArrayList();
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
/*     */   public String generateURL(PieDataset dataset, Comparable key, int pieIndex)
/*     */   {
/*  90 */     return getURL(key, pieIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getListCount()
/*     */   {
/* 101 */     return this.urls.size();
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
/*     */   public int getURLCount(int list)
/*     */   {
/* 115 */     int result = 0;
/* 116 */     Map urlMap = (Map)this.urls.get(list);
/* 117 */     if (urlMap != null) {
/* 118 */       result = urlMap.size();
/*     */     }
/* 120 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURL(Comparable key, int mapIndex)
/*     */   {
/* 132 */     String result = null;
/* 133 */     if (mapIndex < getListCount()) {
/* 134 */       Map urlMap = (Map)this.urls.get(mapIndex);
/* 135 */       if (urlMap != null) {
/* 136 */         result = (String)urlMap.get(key);
/*     */       }
/*     */     }
/* 139 */     return result;
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
/*     */   public void addURLs(Map urlMap)
/*     */   {
/* 154 */     this.urls.add(urlMap);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 166 */     if (o == this) {
/* 167 */       return true;
/*     */     }
/*     */     
/* 170 */     if ((o instanceof CustomPieURLGenerator)) {
/* 171 */       CustomPieURLGenerator generator = (CustomPieURLGenerator)o;
/* 172 */       if (getListCount() != generator.getListCount()) {
/* 173 */         return false;
/*     */       }
/*     */       Iterator i;
/* 176 */       for (int pieItem = 0; pieItem < getListCount(); pieItem++) {
/* 177 */         if (getURLCount(pieItem) != generator.getURLCount(pieItem)) {
/* 178 */           return false;
/*     */         }
/* 180 */         Set keySet = ((HashMap)this.urls.get(pieItem)).keySet();
/*     */         
/* 182 */         for (i = keySet.iterator(); i.hasNext();) {
/* 183 */           String key = (String)i.next();
/* 184 */           if (!getURL(key, pieItem).equals(generator.getURL(key, pieItem)))
/*     */           {
/* 186 */             return false;
/*     */           }
/*     */         }
/*     */       }
/* 190 */       return true;
/*     */     }
/* 192 */     return false;
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
/* 203 */     CustomPieURLGenerator urlGen = new CustomPieURLGenerator();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 208 */     for (Iterator i = this.urls.iterator(); i.hasNext();) {
/* 209 */       Map map = (Map)i.next();
/*     */       
/* 211 */       Map newMap = new HashMap();
/* 212 */       for (Iterator j = map.keySet().iterator(); j.hasNext();) {
/* 213 */         String key = (String)j.next();
/* 214 */         newMap.put(key, map.get(key));
/*     */       }
/*     */       
/* 217 */       urlGen.addURLs(newMap);
/* 218 */       newMap = null;
/*     */     }
/*     */     
/* 221 */     return urlGen;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/CustomPieURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */