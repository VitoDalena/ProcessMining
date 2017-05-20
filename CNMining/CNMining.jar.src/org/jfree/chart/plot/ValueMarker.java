/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import org.jfree.chart.event.MarkerChangeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValueMarker
/*     */   extends Marker
/*     */ {
/*     */   private double value;
/*     */   
/*     */   public ValueMarker(double value)
/*     */   {
/*  69 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueMarker(double value, Paint paint, Stroke stroke)
/*     */   {
/*  80 */     this(value, paint, stroke, paint, stroke, 1.0F);
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
/*     */   public ValueMarker(double value, Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha)
/*     */   {
/*  95 */     super(paint, stroke, outlinePaint, outlineStroke, alpha);
/*  96 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue()
/*     */   {
/* 107 */     return this.value;
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
/*     */   public void setValue(double value)
/*     */   {
/* 121 */     this.value = value;
/* 122 */     notifyListeners(new MarkerChangeEvent(this));
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 141 */     if (obj == this) {
/* 142 */       return true;
/*     */     }
/* 144 */     if (!super.equals(obj)) {
/* 145 */       return false;
/*     */     }
/* 147 */     if (!(obj instanceof ValueMarker)) {
/* 148 */       return false;
/*     */     }
/* 150 */     ValueMarker that = (ValueMarker)obj;
/* 151 */     if (this.value != that.value) {
/* 152 */       return false;
/*     */     }
/* 154 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/ValueMarker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */