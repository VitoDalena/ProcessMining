/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.DataUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultHeatMapDataset
/*     */   extends AbstractDataset
/*     */   implements HeatMapDataset, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private int xSamples;
/*     */   private int ySamples;
/*     */   private double minX;
/*     */   private double maxX;
/*     */   private double minY;
/*     */   private double maxY;
/*     */   private double[][] zValues;
/*     */   
/*     */   public DefaultHeatMapDataset(int xSamples, int ySamples, double minX, double maxX, double minY, double maxY)
/*     */   {
/*  90 */     if (xSamples < 1) {
/*  91 */       throw new IllegalArgumentException("Requires 'xSamples' > 0");
/*     */     }
/*  93 */     if (ySamples < 1) {
/*  94 */       throw new IllegalArgumentException("Requires 'ySamples' > 0");
/*     */     }
/*  96 */     if ((Double.isInfinite(minX)) || (Double.isNaN(minX))) {
/*  97 */       throw new IllegalArgumentException("'minX' cannot be INF or NaN.");
/*     */     }
/*  99 */     if ((Double.isInfinite(maxX)) || (Double.isNaN(maxX))) {
/* 100 */       throw new IllegalArgumentException("'maxX' cannot be INF or NaN.");
/*     */     }
/* 102 */     if ((Double.isInfinite(minY)) || (Double.isNaN(minY))) {
/* 103 */       throw new IllegalArgumentException("'minY' cannot be INF or NaN.");
/*     */     }
/* 105 */     if ((Double.isInfinite(maxY)) || (Double.isNaN(maxY))) {
/* 106 */       throw new IllegalArgumentException("'maxY' cannot be INF or NaN.");
/*     */     }
/*     */     
/* 109 */     this.xSamples = xSamples;
/* 110 */     this.ySamples = ySamples;
/* 111 */     this.minX = minX;
/* 112 */     this.maxX = maxX;
/* 113 */     this.minY = minY;
/* 114 */     this.maxY = maxY;
/* 115 */     this.zValues = new double[xSamples][];
/* 116 */     for (int x = 0; x < xSamples; x++) {
/* 117 */       this.zValues[x] = new double[ySamples];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getXSampleCount()
/*     */   {
/* 129 */     return this.xSamples;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYSampleCount()
/*     */   {
/* 140 */     return this.ySamples;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinimumXValue()
/*     */   {
/* 151 */     return this.minX;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaximumXValue()
/*     */   {
/* 162 */     return this.maxX;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinimumYValue()
/*     */   {
/* 173 */     return this.minY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaximumYValue()
/*     */   {
/* 184 */     return this.maxY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXValue(int xIndex)
/*     */   {
/* 195 */     double x = this.minX + (this.maxX - this.minX) * (xIndex / this.xSamples);
/*     */     
/* 197 */     return x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue(int yIndex)
/*     */   {
/* 208 */     double y = this.minY + (this.maxY - this.minY) * (yIndex / this.ySamples);
/*     */     
/* 210 */     return y;
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
/*     */   public double getZValue(int xIndex, int yIndex)
/*     */   {
/* 223 */     return this.zValues[xIndex][yIndex];
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
/*     */   public Number getZ(int xIndex, int yIndex)
/*     */   {
/* 238 */     return new Double(getZValue(xIndex, yIndex));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setZValue(int xIndex, int yIndex, double z)
/*     */   {
/* 250 */     setZValue(xIndex, yIndex, z, true);
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
/*     */   public void setZValue(int xIndex, int yIndex, double z, boolean notify)
/*     */   {
/* 263 */     this.zValues[xIndex][yIndex] = z;
/* 264 */     if (notify) {
/* 265 */       fireDatasetChanged();
/*     */     }
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
/* 277 */     if (obj == this) {
/* 278 */       return true;
/*     */     }
/* 280 */     if (!(obj instanceof DefaultHeatMapDataset)) {
/* 281 */       return false;
/*     */     }
/* 283 */     DefaultHeatMapDataset that = (DefaultHeatMapDataset)obj;
/* 284 */     if (this.xSamples != that.xSamples) {
/* 285 */       return false;
/*     */     }
/* 287 */     if (this.ySamples != that.ySamples) {
/* 288 */       return false;
/*     */     }
/* 290 */     if (this.minX != that.minX) {
/* 291 */       return false;
/*     */     }
/* 293 */     if (this.maxX != that.maxX) {
/* 294 */       return false;
/*     */     }
/* 296 */     if (this.minY != that.minY) {
/* 297 */       return false;
/*     */     }
/* 299 */     if (this.maxY != that.maxY) {
/* 300 */       return false;
/*     */     }
/* 302 */     if (!DataUtilities.equal(this.zValues, that.zValues)) {
/* 303 */       return false;
/*     */     }
/*     */     
/* 306 */     return true;
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
/* 317 */     DefaultHeatMapDataset clone = (DefaultHeatMapDataset)super.clone();
/* 318 */     clone.zValues = DataUtilities.clone(this.zValues);
/* 319 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/DefaultHeatMapDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */