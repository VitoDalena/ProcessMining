/*     */ package org.jfree.data.contour;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.AbstractXYZDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class DefaultContourDataset
/*     */   extends AbstractXYZDataset
/*     */   implements ContourDataset
/*     */ {
/*  71 */   protected Comparable seriesKey = null;
/*     */   
/*     */ 
/*  74 */   protected Number[] xValues = null;
/*     */   
/*     */ 
/*  77 */   protected Number[] yValues = null;
/*     */   
/*     */ 
/*  80 */   protected Number[] zValues = null;
/*     */   
/*     */ 
/*  83 */   protected int[] xIndex = null;
/*     */   
/*     */ 
/*  86 */   boolean[] dateAxis = new boolean[3];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultContourDataset() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultContourDataset(Comparable seriesKey, Object[] xData, Object[] yData, Object[] zData)
/*     */   {
/* 108 */     this.seriesKey = seriesKey;
/* 109 */     initialize(xData, yData, zData);
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
/*     */   public void initialize(Object[] xData, Object[] yData, Object[] zData)
/*     */   {
/* 123 */     this.xValues = new Double[xData.length];
/* 124 */     this.yValues = new Double[yData.length];
/* 125 */     this.zValues = new Double[zData.length];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 135 */     Vector tmpVector = new Vector();
/* 136 */     double x = 1.123452E31D;
/* 137 */     for (int k = 0; k < this.xValues.length; k++) {
/* 138 */       if (xData[k] != null) { Number xNumber;
/*     */         Number xNumber;
/* 140 */         if ((xData[k] instanceof Number)) {
/* 141 */           xNumber = (Number)xData[k];
/*     */         } else { Number xNumber;
/* 143 */           if ((xData[k] instanceof Date)) {
/* 144 */             this.dateAxis[0] = true;
/* 145 */             Date xDate = (Date)xData[k];
/* 146 */             xNumber = new Long(xDate.getTime());
/*     */           }
/*     */           else {
/* 149 */             xNumber = new Integer(0);
/*     */           } }
/* 151 */         this.xValues[k] = new Double(xNumber.doubleValue());
/*     */         
/*     */ 
/*     */ 
/* 155 */         if (x != this.xValues[k].doubleValue()) {
/* 156 */           tmpVector.add(new Integer(k));
/*     */           
/* 158 */           x = this.xValues[k].doubleValue();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 164 */     Object[] inttmp = tmpVector.toArray();
/* 165 */     this.xIndex = new int[inttmp.length];
/*     */     
/*     */ 
/* 168 */     for (int i = 0; i < inttmp.length; i++) {
/* 169 */       this.xIndex[i] = ((Integer)inttmp[i]).intValue();
/*     */     }
/* 171 */     for (int k = 0; k < this.yValues.length; k++)
/*     */     {
/* 173 */       this.yValues[k] = ((Double)yData[k]);
/* 174 */       if (zData[k] != null) {
/* 175 */         this.zValues[k] = ((Double)zData[k]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object[][] formObjectArray(double[][] data)
/*     */   {
/* 188 */     Object[][] object = new Double[data.length][data[0].length];
/*     */     
/* 190 */     for (int i = 0; i < object.length; i++) {
/* 191 */       for (int j = 0; j < object[i].length; j++) {
/* 192 */         object[i][j] = new Double(data[i][j]);
/*     */       }
/*     */     }
/* 195 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object[] formObjectArray(double[] data)
/*     */   {
/* 206 */     Object[] object = new Double[data.length];
/* 207 */     for (int i = 0; i < object.length; i++) {
/* 208 */       object[i] = new Double(data[i]);
/*     */     }
/* 210 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount(int series)
/*     */   {
/* 222 */     if (series > 0) {
/* 223 */       throw new IllegalArgumentException("Only one series for contour");
/*     */     }
/* 225 */     return this.zValues.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaxZValue()
/*     */   {
/* 234 */     double zMax = -1.0E20D;
/* 235 */     for (int k = 0; k < this.zValues.length; k++) {
/* 236 */       if (this.zValues[k] != null) {
/* 237 */         zMax = Math.max(zMax, this.zValues[k].doubleValue());
/*     */       }
/*     */     }
/* 240 */     return zMax;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinZValue()
/*     */   {
/* 249 */     double zMin = 1.0E20D;
/* 250 */     for (int k = 0; k < this.zValues.length; k++) {
/* 251 */       if (this.zValues[k] != null) {
/* 252 */         zMin = Math.min(zMin, this.zValues[k].doubleValue());
/*     */       }
/*     */     }
/* 255 */     return zMin;
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
/*     */   public Range getZValueRange(Range x, Range y)
/*     */   {
/* 268 */     double minX = x.getLowerBound();
/* 269 */     double minY = y.getLowerBound();
/* 270 */     double maxX = x.getUpperBound();
/* 271 */     double maxY = y.getUpperBound();
/*     */     
/* 273 */     double zMin = 1.0E20D;
/* 274 */     double zMax = -1.0E20D;
/* 275 */     for (int k = 0; k < this.zValues.length; k++) {
/* 276 */       if ((this.xValues[k].doubleValue() >= minX) && (this.xValues[k].doubleValue() <= maxX) && (this.yValues[k].doubleValue() >= minY) && (this.yValues[k].doubleValue() <= maxY))
/*     */       {
/*     */ 
/*     */ 
/* 280 */         if (this.zValues[k] != null) {
/* 281 */           zMin = Math.min(zMin, this.zValues[k].doubleValue());
/* 282 */           zMax = Math.max(zMax, this.zValues[k].doubleValue());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 287 */     return new Range(zMin, zMax);
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
/*     */   public double getMinZValue(double minX, double minY, double maxX, double maxY)
/*     */   {
/* 305 */     double zMin = 1.0E20D;
/* 306 */     for (int k = 0; k < this.zValues.length; k++) {
/* 307 */       if (this.zValues[k] != null) {
/* 308 */         zMin = Math.min(zMin, this.zValues[k].doubleValue());
/*     */       }
/*     */     }
/* 311 */     return zMin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 323 */     return 1;
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 336 */     if (series > 0) {
/* 337 */       throw new IllegalArgumentException("Only one series for contour");
/*     */     }
/* 339 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] getXIndices()
/*     */   {
/* 348 */     return this.xIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number[] getXValues()
/*     */   {
/* 357 */     return this.xValues;
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 370 */     if (series > 0) {
/* 371 */       throw new IllegalArgumentException("Only one series for contour");
/*     */     }
/* 373 */     return this.xValues[item];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getXValue(int item)
/*     */   {
/* 384 */     return this.xValues[item];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number[] getYValues()
/*     */   {
/* 393 */     return this.yValues;
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 406 */     if (series > 0) {
/* 407 */       throw new IllegalArgumentException("Only one series for contour");
/*     */     }
/* 409 */     return this.yValues[item];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number[] getZValues()
/*     */   {
/* 418 */     return this.zValues;
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
/*     */   public Number getZ(int series, int item)
/*     */   {
/* 431 */     if (series > 0) {
/* 432 */       throw new IllegalArgumentException("Only one series for contour");
/*     */     }
/* 434 */     return this.zValues[item];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] indexX()
/*     */   {
/* 443 */     int[] index = new int[this.xValues.length];
/* 444 */     for (int k = 0; k < index.length; k++) {
/* 445 */       index[k] = indexX(k);
/*     */     }
/* 447 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexX(int k)
/*     */   {
/* 458 */     int i = Arrays.binarySearch(this.xIndex, k);
/* 459 */     if (i >= 0) {
/* 460 */       return i;
/*     */     }
/*     */     
/* 463 */     return -1 * i - 2;
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
/*     */   public int indexY(int k)
/*     */   {
/* 476 */     return k / this.xValues.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexZ(int i, int j)
/*     */   {
/* 488 */     return this.xValues.length * j + i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateAxis(int axisNumber)
/*     */   {
/* 499 */     if ((axisNumber < 0) || (axisNumber > 2)) {
/* 500 */       return false;
/*     */     }
/* 502 */     return this.dateAxis[axisNumber];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesKeys(Comparable[] seriesKeys)
/*     */   {
/* 511 */     if (seriesKeys.length > 1) {
/* 512 */       throw new IllegalArgumentException("Contours only support one series");
/*     */     }
/*     */     
/* 515 */     this.seriesKey = seriesKeys[0];
/* 516 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/contour/DefaultContourDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */