/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public class DefaultWindDataset
/*     */   extends AbstractXYDataset
/*     */   implements WindDataset, PublicCloneable
/*     */ {
/*     */   private List seriesKeys;
/*     */   private List allSeriesData;
/*     */   
/*     */   public DefaultWindDataset()
/*     */   {
/*  75 */     this.seriesKeys = new ArrayList();
/*  76 */     this.allSeriesData = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultWindDataset(Object[][][] data)
/*     */   {
/*  87 */     this(seriesNameListFromDataArray(data), data);
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
/*     */   public DefaultWindDataset(String[] seriesNames, Object[][][] data)
/*     */   {
/* 101 */     this(Arrays.asList(seriesNames), data);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultWindDataset(List seriesKeys, Object[][][] data)
/*     */   {
/* 129 */     if (seriesKeys == null) {
/* 130 */       throw new IllegalArgumentException("Null 'seriesKeys' argument.");
/*     */     }
/* 132 */     if (seriesKeys.size() != data.length) {
/* 133 */       throw new IllegalArgumentException("The number of series keys does not match the number of series in the data array.");
/*     */     }
/*     */     
/* 136 */     this.seriesKeys = seriesKeys;
/* 137 */     int seriesCount = data.length;
/* 138 */     this.allSeriesData = new ArrayList(seriesCount);
/*     */     
/* 140 */     for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
/* 141 */       List oneSeriesData = new ArrayList();
/* 142 */       int maxItemCount = data[seriesIndex].length;
/* 143 */       for (int itemIndex = 0; itemIndex < maxItemCount; itemIndex++) {
/* 144 */         Object xObject = data[seriesIndex][itemIndex][0];
/* 145 */         if (xObject != null) { Number xNumber;
/*     */           Number xNumber;
/* 147 */           if ((xObject instanceof Number)) {
/* 148 */             xNumber = (Number)xObject;
/*     */           } else {
/*     */             Number xNumber;
/* 151 */             if ((xObject instanceof Date)) {
/* 152 */               Date xDate = (Date)xObject;
/* 153 */               xNumber = new Long(xDate.getTime());
/*     */             }
/*     */             else {
/* 156 */               xNumber = new Integer(0);
/*     */             }
/*     */           }
/* 159 */           Number windDir = (Number)data[seriesIndex][itemIndex][1];
/* 160 */           Number windForce = (Number)data[seriesIndex][itemIndex][2];
/* 161 */           oneSeriesData.add(new WindDataItem(xNumber, windDir, windForce));
/*     */         }
/*     */       }
/*     */       
/* 165 */       Collections.sort(oneSeriesData);
/* 166 */       this.allSeriesData.add(seriesIndex, oneSeriesData);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 177 */     return this.allSeriesData.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount(int series)
/*     */   {
/* 188 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 189 */       throw new IllegalArgumentException("Invalid series index: " + series);
/*     */     }
/*     */     
/* 192 */     List oneSeriesData = (List)this.allSeriesData.get(series);
/* 193 */     return oneSeriesData.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 204 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 205 */       throw new IllegalArgumentException("Invalid series index: " + series);
/*     */     }
/*     */     
/* 208 */     return (Comparable)this.seriesKeys.get(series);
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 222 */     List oneSeriesData = (List)this.allSeriesData.get(series);
/* 223 */     WindDataItem windItem = (WindDataItem)oneSeriesData.get(item);
/* 224 */     return windItem.getX();
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 238 */     return getWindForce(series, item);
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
/*     */   public Number getWindDirection(int series, int item)
/*     */   {
/* 251 */     List oneSeriesData = (List)this.allSeriesData.get(series);
/* 252 */     WindDataItem windItem = (WindDataItem)oneSeriesData.get(item);
/* 253 */     return windItem.getWindDirection();
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
/*     */   public Number getWindForce(int series, int item)
/*     */   {
/* 266 */     List oneSeriesData = (List)this.allSeriesData.get(series);
/* 267 */     WindDataItem windItem = (WindDataItem)oneSeriesData.get(item);
/* 268 */     return windItem.getWindForce();
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
/*     */   public static List seriesNameListFromDataArray(Object[][] data)
/*     */   {
/* 282 */     int seriesCount = data.length;
/* 283 */     List seriesNameList = new ArrayList(seriesCount);
/* 284 */     for (int i = 0; i < seriesCount; i++) {
/* 285 */       seriesNameList.add("Series " + (i + 1));
/*     */     }
/* 287 */     return seriesNameList;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 307 */     if (this == obj) {
/* 308 */       return true;
/*     */     }
/* 310 */     if (!(obj instanceof DefaultWindDataset)) {
/* 311 */       return false;
/*     */     }
/* 313 */     DefaultWindDataset that = (DefaultWindDataset)obj;
/* 314 */     if (!this.seriesKeys.equals(that.seriesKeys)) {
/* 315 */       return false;
/*     */     }
/* 317 */     if (!this.allSeriesData.equals(that.allSeriesData)) {
/* 318 */       return false;
/*     */     }
/* 320 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultWindDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */