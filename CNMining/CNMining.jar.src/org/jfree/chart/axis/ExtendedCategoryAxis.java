/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextFragment;
/*     */ import org.jfree.text.TextLine;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class ExtendedCategoryAxis
/*     */   extends CategoryAxis
/*     */ {
/*     */   static final long serialVersionUID = -3004429093959826567L;
/*     */   private Map sublabels;
/*     */   private Font sublabelFont;
/*     */   private transient Paint sublabelPaint;
/*     */   
/*     */   public ExtendedCategoryAxis(String label)
/*     */   {
/*  89 */     super(label);
/*  90 */     this.sublabels = new HashMap();
/*  91 */     this.sublabelFont = new Font("SansSerif", 0, 10);
/*  92 */     this.sublabelPaint = Color.black;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getSubLabelFont()
/*     */   {
/* 103 */     return this.sublabelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubLabelFont(Font font)
/*     */   {
/* 115 */     if (font == null) {
/* 116 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 118 */     this.sublabelFont = font;
/* 119 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getSubLabelPaint()
/*     */   {
/* 130 */     return this.sublabelPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubLabelPaint(Paint paint)
/*     */   {
/* 142 */     if (paint == null) {
/* 143 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 145 */     this.sublabelPaint = paint;
/* 146 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubLabel(Comparable category, String label)
/*     */   {
/* 156 */     this.sublabels.put(category, label);
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
/*     */   protected TextBlock createLabel(Comparable category, float width, RectangleEdge edge, Graphics2D g2)
/*     */   {
/* 172 */     TextBlock label = super.createLabel(category, width, edge, g2);
/* 173 */     String s = (String)this.sublabels.get(category);
/* 174 */     if (s != null) {
/* 175 */       if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/* 176 */         TextLine line = new TextLine(s, this.sublabelFont, this.sublabelPaint);
/*     */         
/* 178 */         label.addLine(line);
/*     */       }
/* 180 */       else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*     */       {
/* 182 */         TextLine line = label.getLastLine();
/* 183 */         if (line != null) {
/* 184 */           line.addFragment(new TextFragment("  " + s, this.sublabelFont, this.sublabelPaint));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 189 */     return label;
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
/* 203 */     if (!(obj instanceof ExtendedCategoryAxis)) {
/* 204 */       return false;
/*     */     }
/* 206 */     ExtendedCategoryAxis that = (ExtendedCategoryAxis)obj;
/* 207 */     if (!this.sublabelFont.equals(that.sublabelFont)) {
/* 208 */       return false;
/*     */     }
/* 210 */     if (!PaintUtilities.equal(this.sublabelPaint, that.sublabelPaint)) {
/* 211 */       return false;
/*     */     }
/* 213 */     if (!this.sublabels.equals(that.sublabels)) {
/* 214 */       return false;
/*     */     }
/* 216 */     return super.equals(obj);
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
/* 227 */     ExtendedCategoryAxis clone = (ExtendedCategoryAxis)super.clone();
/* 228 */     clone.sublabels = new HashMap(this.sublabels);
/* 229 */     return clone;
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
/* 240 */     stream.defaultWriteObject();
/* 241 */     SerialUtilities.writePaint(this.sublabelPaint, stream);
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
/* 254 */     stream.defaultReadObject();
/* 255 */     this.sublabelPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/ExtendedCategoryAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */