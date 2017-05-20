/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import org.jfree.data.DomainOrder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RendererUtilities
/*     */ {
/*     */   public static int findLiveItemsLowerBound(XYDataset dataset, int series, double xLow, double xHigh)
/*     */   {
/*  71 */     if (dataset == null) {
/*  72 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/*  74 */     if (xLow >= xHigh) {
/*  75 */       throw new IllegalArgumentException("Requires xLow < xHigh.");
/*     */     }
/*  77 */     int itemCount = dataset.getItemCount(series);
/*  78 */     if (itemCount <= 1) {
/*  79 */       return 0;
/*     */     }
/*  81 */     if (dataset.getDomainOrder() == DomainOrder.ASCENDING)
/*     */     {
/*     */ 
/*  84 */       int low = 0;
/*  85 */       int high = itemCount - 1;
/*  86 */       double lowValue = dataset.getXValue(series, low);
/*  87 */       if (lowValue >= xLow)
/*     */       {
/*  89 */         return low;
/*     */       }
/*  91 */       double highValue = dataset.getXValue(series, high);
/*  92 */       if (highValue < xLow)
/*     */       {
/*  94 */         return high;
/*     */       }
/*  96 */       while (high - low > 1) {
/*  97 */         int mid = (low + high) / 2;
/*  98 */         double midV = dataset.getXValue(series, mid);
/*  99 */         if (midV >= xLow) {
/* 100 */           high = mid;
/*     */         }
/*     */         else {
/* 103 */           low = mid;
/*     */         }
/*     */       }
/* 106 */       return high;
/*     */     }
/* 108 */     if (dataset.getDomainOrder() == DomainOrder.DESCENDING)
/*     */     {
/*     */ 
/* 111 */       int low = 0;
/* 112 */       int high = itemCount - 1;
/* 113 */       double lowValue = dataset.getXValue(series, low);
/* 114 */       if (lowValue <= xHigh) {
/* 115 */         return low;
/*     */       }
/* 117 */       double highValue = dataset.getXValue(series, high);
/* 118 */       if (highValue > xHigh) {
/* 119 */         return high;
/*     */       }
/* 121 */       while (high - low > 1) {
/* 122 */         int mid = (low + high) / 2;
/* 123 */         double midV = dataset.getXValue(series, mid);
/* 124 */         if (midV > xHigh) {
/* 125 */           low = mid;
/*     */         }
/*     */         else {
/* 128 */           high = mid;
/*     */         }
/* 130 */         mid = (low + high) / 2;
/*     */       }
/* 132 */       return high;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 138 */     int index = 0;
/*     */     
/* 140 */     double x = dataset.getXValue(series, index);
/* 141 */     while ((index < itemCount) && ((x < xLow) || (x > xHigh))) {
/* 142 */       index++;
/* 143 */       if (index < itemCount) {
/* 144 */         x = dataset.getXValue(series, index);
/*     */       }
/*     */     }
/* 147 */     return Math.min(Math.max(0, index), itemCount - 1);
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
/*     */   public static int findLiveItemsUpperBound(XYDataset dataset, int series, double xLow, double xHigh)
/*     */   {
/* 168 */     if (dataset == null) {
/* 169 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 171 */     if (xLow >= xHigh) {
/* 172 */       throw new IllegalArgumentException("Requires xLow < xHigh.");
/*     */     }
/* 174 */     int itemCount = dataset.getItemCount(series);
/* 175 */     if (itemCount <= 1) {
/* 176 */       return 0;
/*     */     }
/* 178 */     if (dataset.getDomainOrder() == DomainOrder.ASCENDING) {
/* 179 */       int low = 0;
/* 180 */       int high = itemCount - 1;
/* 181 */       double lowValue = dataset.getXValue(series, low);
/* 182 */       if (lowValue > xHigh) {
/* 183 */         return low;
/*     */       }
/* 185 */       double highValue = dataset.getXValue(series, high);
/* 186 */       if (highValue <= xHigh) {
/* 187 */         return high;
/*     */       }
/* 189 */       int mid = (low + high) / 2;
/* 190 */       while (high - low > 1) {
/* 191 */         double midV = dataset.getXValue(series, mid);
/* 192 */         if (midV <= xHigh) {
/* 193 */           low = mid;
/*     */         }
/*     */         else {
/* 196 */           high = mid;
/*     */         }
/* 198 */         mid = (low + high) / 2;
/*     */       }
/* 200 */       return mid;
/*     */     }
/* 202 */     if (dataset.getDomainOrder() == DomainOrder.DESCENDING)
/*     */     {
/*     */ 
/* 205 */       int low = 0;
/* 206 */       int high = itemCount - 1;
/* 207 */       int mid = (low + high) / 2;
/* 208 */       double lowValue = dataset.getXValue(series, low);
/* 209 */       if (lowValue < xLow) {
/* 210 */         return low;
/*     */       }
/* 212 */       double highValue = dataset.getXValue(series, high);
/* 213 */       if (highValue >= xLow) {
/* 214 */         return high;
/*     */       }
/* 216 */       while (high - low > 1) {
/* 217 */         double midV = dataset.getXValue(series, mid);
/* 218 */         if (midV >= xLow) {
/* 219 */           low = mid;
/*     */         }
/*     */         else {
/* 222 */           high = mid;
/*     */         }
/* 224 */         mid = (low + high) / 2;
/*     */       }
/* 226 */       return mid;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 232 */     int index = itemCount - 1;
/*     */     
/* 234 */     double x = dataset.getXValue(series, index);
/* 235 */     while ((index >= 0) && ((x < xLow) || (x > xHigh))) {
/* 236 */       index--;
/* 237 */       if (index >= 0) {
/* 238 */         x = dataset.getXValue(series, index);
/*     */       }
/*     */     }
/* 241 */     return Math.max(index, 0);
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
/*     */   public static int[] findLiveItems(XYDataset dataset, int series, double xLow, double xHigh)
/*     */   {
/* 261 */     int i0 = findLiveItemsLowerBound(dataset, series, xLow, xHigh);
/* 262 */     int i1 = findLiveItemsUpperBound(dataset, series, xLow, xHigh);
/* 263 */     if (i0 > i1) {
/* 264 */       i0 = i1;
/*     */     }
/* 266 */     return new int[] { i0, i1 };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/RendererUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */