/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ public class LookupPaintScale
/*     */   implements PaintScale, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -5239384246251042006L;
/*     */   private double lowerBound;
/*     */   private double upperBound;
/*     */   private transient Paint defaultPaint;
/*     */   private List lookupTable;
/*     */   
/*     */   static class PaintItem
/*     */     implements Comparable, Serializable
/*     */   {
/*     */     static final long serialVersionUID = 698920578512361570L;
/*     */     double value;
/*     */     transient Paint paint;
/*     */     
/*     */     public PaintItem(double value, Paint paint)
/*     */     {
/*  90 */       this.value = value;
/*  91 */       this.paint = paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int compareTo(Object obj)
/*     */     {
/*  98 */       PaintItem that = (PaintItem)obj;
/*  99 */       double d1 = this.value;
/* 100 */       double d2 = that.value;
/* 101 */       if (d1 > d2) {
/* 102 */         return 1;
/*     */       }
/* 104 */       if (d1 < d2) {
/* 105 */         return -1;
/*     */       }
/* 107 */       return 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 118 */       if (obj == this) {
/* 119 */         return true;
/*     */       }
/* 121 */       if (!(obj instanceof PaintItem)) {
/* 122 */         return false;
/*     */       }
/* 124 */       PaintItem that = (PaintItem)obj;
/* 125 */       if (this.value != that.value) {
/* 126 */         return false;
/*     */       }
/* 128 */       if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 129 */         return false;
/*     */       }
/* 131 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void writeObject(ObjectOutputStream stream)
/*     */       throws IOException
/*     */     {
/* 142 */       stream.defaultWriteObject();
/* 143 */       SerialUtilities.writePaint(this.paint, stream);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void readObject(ObjectInputStream stream)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/* 156 */       stream.defaultReadObject();
/* 157 */       this.paint = SerialUtilities.readPaint(stream);
/*     */     }
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
/*     */   public LookupPaintScale()
/*     */   {
/* 181 */     this(0.0D, 1.0D, Color.lightGray);
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
/*     */   public LookupPaintScale(double lowerBound, double upperBound, Paint defaultPaint)
/*     */   {
/* 194 */     if (lowerBound >= upperBound) {
/* 195 */       throw new IllegalArgumentException("Requires lowerBound < upperBound.");
/*     */     }
/*     */     
/* 198 */     if (defaultPaint == null) {
/* 199 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 201 */     this.lowerBound = lowerBound;
/* 202 */     this.upperBound = upperBound;
/* 203 */     this.defaultPaint = defaultPaint;
/* 204 */     this.lookupTable = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getDefaultPaint()
/*     */   {
/* 213 */     return this.defaultPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowerBound()
/*     */   {
/* 224 */     return this.lowerBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getUpperBound()
/*     */   {
/* 235 */     return this.upperBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void add(Number value, Paint paint)
/*     */   {
/* 249 */     add(value.doubleValue(), paint);
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
/*     */   public void add(double value, Paint paint)
/*     */   {
/* 263 */     PaintItem item = new PaintItem(value, paint);
/* 264 */     int index = Collections.binarySearch(this.lookupTable, item);
/* 265 */     if (index >= 0) {
/* 266 */       this.lookupTable.set(index, item);
/*     */     }
/*     */     else {
/* 269 */       this.lookupTable.add(-(index + 1), item);
/*     */     }
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
/*     */   public Paint getPaint(double value)
/*     */   {
/* 285 */     if (value < this.lowerBound) {
/* 286 */       return this.defaultPaint;
/*     */     }
/* 288 */     if (value > this.upperBound) {
/* 289 */       return this.defaultPaint;
/*     */     }
/*     */     
/* 292 */     int count = this.lookupTable.size();
/* 293 */     if (count == 0) {
/* 294 */       return this.defaultPaint;
/*     */     }
/*     */     
/*     */ 
/* 298 */     PaintItem item = (PaintItem)this.lookupTable.get(0);
/* 299 */     if (value < item.value) {
/* 300 */       return this.defaultPaint;
/*     */     }
/*     */     
/*     */ 
/* 304 */     int low = 0;
/* 305 */     int high = this.lookupTable.size() - 1;
/* 306 */     while (high - low > 1) {
/* 307 */       int current = (low + high) / 2;
/* 308 */       item = (PaintItem)this.lookupTable.get(current);
/* 309 */       if (value >= item.value) {
/* 310 */         low = current;
/*     */       }
/*     */       else {
/* 313 */         high = current;
/*     */       }
/*     */     }
/* 316 */     if (high > low) {
/* 317 */       item = (PaintItem)this.lookupTable.get(high);
/* 318 */       if (value < item.value) {
/* 319 */         item = (PaintItem)this.lookupTable.get(low);
/*     */       }
/*     */     }
/* 322 */     return item != null ? item.paint : this.defaultPaint;
/*     */   }
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
/* 334 */     if (obj == this) {
/* 335 */       return true;
/*     */     }
/* 337 */     if (!(obj instanceof LookupPaintScale)) {
/* 338 */       return false;
/*     */     }
/* 340 */     LookupPaintScale that = (LookupPaintScale)obj;
/* 341 */     if (this.lowerBound != that.lowerBound) {
/* 342 */       return false;
/*     */     }
/* 344 */     if (this.upperBound != that.upperBound) {
/* 345 */       return false;
/*     */     }
/* 347 */     if (!PaintUtilities.equal(this.defaultPaint, that.defaultPaint)) {
/* 348 */       return false;
/*     */     }
/* 350 */     if (!this.lookupTable.equals(that.lookupTable)) {
/* 351 */       return false;
/*     */     }
/* 353 */     return true;
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
/* 365 */     LookupPaintScale clone = (LookupPaintScale)super.clone();
/* 366 */     clone.lookupTable = new ArrayList(this.lookupTable);
/* 367 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 378 */     stream.defaultWriteObject();
/* 379 */     SerialUtilities.writePaint(this.defaultPaint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 392 */     stream.defaultReadObject();
/* 393 */     this.defaultPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/LookupPaintScale.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */