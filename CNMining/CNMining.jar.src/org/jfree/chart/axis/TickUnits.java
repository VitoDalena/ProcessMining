/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TickUnits
/*     */   implements TickUnitSource, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1134174035901467545L;
/*     */   private List tickUnits;
/*     */   
/*     */   public TickUnits()
/*     */   {
/*  80 */     this.tickUnits = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(TickUnit unit)
/*     */   {
/*  90 */     if (unit == null) {
/*  91 */       throw new NullPointerException("Null 'unit' argument.");
/*     */     }
/*  93 */     this.tickUnits.add(unit);
/*  94 */     Collections.sort(this.tickUnits);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 105 */     return this.tickUnits.size();
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
/*     */   public TickUnit get(int pos)
/*     */   {
/* 118 */     return (TickUnit)this.tickUnits.get(pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TickUnit getLargerTickUnit(TickUnit unit)
/*     */   {
/* 130 */     int index = Collections.binarySearch(this.tickUnits, unit);
/* 131 */     if (index >= 0) {
/* 132 */       index += 1;
/*     */     }
/*     */     else {
/* 135 */       index = -index;
/*     */     }
/*     */     
/* 138 */     return (TickUnit)this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
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
/*     */   public TickUnit getCeilingTickUnit(TickUnit unit)
/*     */   {
/* 153 */     int index = Collections.binarySearch(this.tickUnits, unit);
/* 154 */     if (index >= 0) {
/* 155 */       return (TickUnit)this.tickUnits.get(index);
/*     */     }
/*     */     
/* 158 */     index = -(index + 1);
/* 159 */     return (TickUnit)this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
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
/*     */   public TickUnit getCeilingTickUnit(double size)
/*     */   {
/* 174 */     return getCeilingTickUnit(new NumberTickUnit(size, NumberFormat.getInstance()));
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
/* 187 */     TickUnits clone = (TickUnits)super.clone();
/* 188 */     clone.tickUnits = new ArrayList(this.tickUnits);
/* 189 */     return clone;
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
/* 200 */     if (obj == this) {
/* 201 */       return true;
/*     */     }
/* 203 */     if (!(obj instanceof TickUnits)) {
/* 204 */       return false;
/*     */     }
/* 206 */     TickUnits that = (TickUnits)obj;
/* 207 */     return that.tickUnits.equals(this.tickUnits);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/TickUnits.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */