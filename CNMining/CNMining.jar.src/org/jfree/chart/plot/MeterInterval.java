/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MeterInterval
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1530982090622488257L;
/*     */   private String label;
/*     */   private Range range;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Stroke outlineStroke;
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */   public MeterInterval(String label, Range range)
/*     */   {
/*  89 */     this(label, range, Color.yellow, new BasicStroke(2.0F), null);
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
/*     */   public MeterInterval(String label, Range range, Paint outlinePaint, Stroke outlineStroke, Paint backgroundPaint)
/*     */   {
/* 104 */     if (label == null) {
/* 105 */       throw new IllegalArgumentException("Null 'label' argument.");
/*     */     }
/* 107 */     if (range == null) {
/* 108 */       throw new IllegalArgumentException("Null 'range' argument.");
/*     */     }
/* 110 */     this.label = label;
/* 111 */     this.range = range;
/* 112 */     this.outlinePaint = outlinePaint;
/* 113 */     this.outlineStroke = outlineStroke;
/* 114 */     this.backgroundPaint = backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 123 */     return this.label;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getRange()
/*     */   {
/* 132 */     return this.range;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 142 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 151 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 160 */     return this.outlineStroke;
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
/* 171 */     if (obj == this) {
/* 172 */       return true;
/*     */     }
/* 174 */     if (!(obj instanceof MeterInterval)) {
/* 175 */       return false;
/*     */     }
/* 177 */     MeterInterval that = (MeterInterval)obj;
/* 178 */     if (!this.label.equals(that.label)) {
/* 179 */       return false;
/*     */     }
/* 181 */     if (!this.range.equals(that.range)) {
/* 182 */       return false;
/*     */     }
/* 184 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 185 */       return false;
/*     */     }
/* 187 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
/* 188 */       return false;
/*     */     }
/* 190 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 191 */       return false;
/*     */     }
/* 193 */     return true;
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
/* 204 */     stream.defaultWriteObject();
/* 205 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 206 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 207 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
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
/* 220 */     stream.defaultReadObject();
/* 221 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 222 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 223 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/MeterInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */