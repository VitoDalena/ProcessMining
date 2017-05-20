/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.event.MarkerChangeEvent;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryMarker
/*     */   extends Marker
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private Comparable key;
/*  71 */   private boolean drawAsLine = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryMarker(Comparable key)
/*     */   {
/*  79 */     this(key, Color.gray, new BasicStroke(1.0F));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryMarker(Comparable key, Paint paint, Stroke stroke)
/*     */   {
/*  90 */     this(key, paint, stroke, paint, stroke, 1.0F);
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
/*     */   public CategoryMarker(Comparable key, Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha)
/*     */   {
/* 106 */     super(paint, stroke, outlinePaint, outlineStroke, alpha);
/* 107 */     this.key = key;
/* 108 */     setLabelOffsetType(LengthAdjustmentType.EXPAND);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/* 117 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setKey(Comparable key)
/*     */   {
/* 129 */     if (key == null) {
/* 130 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 132 */     this.key = key;
/* 133 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawAsLine()
/*     */   {
/* 143 */     return this.drawAsLine;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDrawAsLine(boolean drawAsLine)
/*     */   {
/* 154 */     this.drawAsLine = drawAsLine;
/* 155 */     notifyListeners(new MarkerChangeEvent(this));
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
/* 166 */     if (obj == null) {
/* 167 */       return false;
/*     */     }
/* 169 */     if (!(obj instanceof CategoryMarker)) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (!super.equals(obj)) {
/* 173 */       return false;
/*     */     }
/* 175 */     CategoryMarker that = (CategoryMarker)obj;
/* 176 */     if (!this.key.equals(that.key)) {
/* 177 */       return false;
/*     */     }
/* 179 */     if (this.drawAsLine != that.drawAsLine) {
/* 180 */       return false;
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CategoryMarker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */