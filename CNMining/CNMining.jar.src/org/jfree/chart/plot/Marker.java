/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.EventListener;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.chart.event.MarkerChangeEvent;
/*     */ import org.jfree.chart.event.MarkerChangeListener;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Marker
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -734389651405327166L;
/*     */   private transient Paint paint;
/*     */   private transient Stroke stroke;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Stroke outlineStroke;
/*     */   private float alpha;
/* 114 */   private String label = null;
/*     */   
/*     */ 
/*     */ 
/*     */   private Font labelFont;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint labelPaint;
/*     */   
/*     */ 
/*     */   private RectangleAnchor labelAnchor;
/*     */   
/*     */ 
/*     */   private TextAnchor labelTextAnchor;
/*     */   
/*     */ 
/*     */   private RectangleInsets labelOffset;
/*     */   
/*     */ 
/*     */   private LengthAdjustmentType labelOffsetType;
/*     */   
/*     */ 
/*     */   private transient EventListenerList listenerList;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Marker()
/*     */   {
/* 143 */     this(Color.gray);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Marker(Paint paint)
/*     */   {
/* 152 */     this(paint, new BasicStroke(0.5F), Color.gray, new BasicStroke(0.5F), 0.8F);
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
/*     */   protected Marker(Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha)
/*     */   {
/* 174 */     if (paint == null) {
/* 175 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 177 */     if (stroke == null) {
/* 178 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 180 */     if ((alpha < 0.0F) || (alpha > 1.0F)) {
/* 181 */       throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f");
/*     */     }
/*     */     
/* 184 */     this.paint = paint;
/* 185 */     this.stroke = stroke;
/* 186 */     this.outlinePaint = outlinePaint;
/* 187 */     this.outlineStroke = outlineStroke;
/* 188 */     this.alpha = alpha;
/*     */     
/* 190 */     this.labelFont = new Font("SansSerif", 0, 9);
/* 191 */     this.labelPaint = Color.black;
/* 192 */     this.labelAnchor = RectangleAnchor.TOP_LEFT;
/* 193 */     this.labelOffset = new RectangleInsets(3.0D, 3.0D, 3.0D, 3.0D);
/* 194 */     this.labelOffsetType = LengthAdjustmentType.CONTRACT;
/* 195 */     this.labelTextAnchor = TextAnchor.CENTER;
/*     */     
/* 197 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 208 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 220 */     if (paint == null) {
/* 221 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 223 */     this.paint = paint;
/* 224 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 235 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke stroke)
/*     */   {
/* 247 */     if (stroke == null) {
/* 248 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 250 */     this.stroke = stroke;
/* 251 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 262 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlinePaint(Paint paint)
/*     */   {
/* 274 */     this.outlinePaint = paint;
/* 275 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 286 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlineStroke(Stroke stroke)
/*     */   {
/* 298 */     this.outlineStroke = stroke;
/* 299 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getAlpha()
/*     */   {
/* 310 */     return this.alpha;
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
/*     */   public void setAlpha(float alpha)
/*     */   {
/* 328 */     if ((alpha < 0.0F) || (alpha > 1.0F)) {
/* 329 */       throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f");
/*     */     }
/* 331 */     this.alpha = alpha;
/* 332 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 343 */     return this.label;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabel(String label)
/*     */   {
/* 355 */     this.label = label;
/* 356 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getLabelFont()
/*     */   {
/* 367 */     return this.labelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelFont(Font font)
/*     */   {
/* 379 */     if (font == null) {
/* 380 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 382 */     this.labelFont = font;
/* 383 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelPaint()
/*     */   {
/* 394 */     return this.labelPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelPaint(Paint paint)
/*     */   {
/* 406 */     if (paint == null) {
/* 407 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 409 */     this.labelPaint = paint;
/* 410 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getLabelAnchor()
/*     */   {
/* 422 */     return this.labelAnchor;
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
/*     */   public void setLabelAnchor(RectangleAnchor anchor)
/*     */   {
/* 435 */     if (anchor == null) {
/* 436 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 438 */     this.labelAnchor = anchor;
/* 439 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getLabelOffset()
/*     */   {
/* 450 */     return this.labelOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOffset(RectangleInsets offset)
/*     */   {
/* 462 */     if (offset == null) {
/* 463 */       throw new IllegalArgumentException("Null 'offset' argument.");
/*     */     }
/* 465 */     this.labelOffset = offset;
/* 466 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LengthAdjustmentType getLabelOffsetType()
/*     */   {
/* 477 */     return this.labelOffsetType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOffsetType(LengthAdjustmentType adj)
/*     */   {
/* 489 */     if (adj == null) {
/* 490 */       throw new IllegalArgumentException("Null 'adj' argument.");
/*     */     }
/* 492 */     this.labelOffsetType = adj;
/* 493 */     notifyListeners(new MarkerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getLabelTextAnchor()
/*     */   {
/* 504 */     return this.labelTextAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelTextAnchor(TextAnchor anchor)
/*     */   {
/* 516 */     if (anchor == null) {
/* 517 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 519 */     this.labelTextAnchor = anchor;
/* 520 */     notifyListeners(new MarkerChangeEvent(this));
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
/*     */   public void addChangeListener(MarkerChangeListener listener)
/*     */   {
/* 533 */     this.listenerList.add(MarkerChangeListener.class, listener);
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
/*     */   public void removeChangeListener(MarkerChangeListener listener)
/*     */   {
/* 546 */     this.listenerList.remove(MarkerChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void notifyListeners(MarkerChangeEvent event)
/*     */   {
/* 558 */     Object[] listeners = this.listenerList.getListenerList();
/* 559 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 560 */       if (listeners[i] == MarkerChangeListener.class) {
/* 561 */         ((MarkerChangeListener)listeners[(i + 1)]).markerChanged(event);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public EventListener[] getListeners(Class listenerType)
/*     */   {
/* 577 */     return this.listenerList.getListeners(listenerType);
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
/* 588 */     if (obj == this) {
/* 589 */       return true;
/*     */     }
/* 591 */     if (!(obj instanceof Marker)) {
/* 592 */       return false;
/*     */     }
/* 594 */     Marker that = (Marker)obj;
/* 595 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 596 */       return false;
/*     */     }
/* 598 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 599 */       return false;
/*     */     }
/* 601 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 602 */       return false;
/*     */     }
/* 604 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
/* 605 */       return false;
/*     */     }
/* 607 */     if (this.alpha != that.alpha) {
/* 608 */       return false;
/*     */     }
/* 610 */     if (!ObjectUtilities.equal(this.label, that.label)) {
/* 611 */       return false;
/*     */     }
/* 613 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont)) {
/* 614 */       return false;
/*     */     }
/* 616 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/* 617 */       return false;
/*     */     }
/* 619 */     if (this.labelAnchor != that.labelAnchor) {
/* 620 */       return false;
/*     */     }
/* 622 */     if (this.labelTextAnchor != that.labelTextAnchor) {
/* 623 */       return false;
/*     */     }
/* 625 */     if (!ObjectUtilities.equal(this.labelOffset, that.labelOffset)) {
/* 626 */       return false;
/*     */     }
/* 628 */     if (!this.labelOffsetType.equals(that.labelOffsetType)) {
/* 629 */       return false;
/*     */     }
/* 631 */     return true;
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
/* 642 */     return super.clone();
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
/* 653 */     stream.defaultWriteObject();
/* 654 */     SerialUtilities.writePaint(this.paint, stream);
/* 655 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 656 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 657 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 658 */     SerialUtilities.writePaint(this.labelPaint, stream);
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
/* 671 */     stream.defaultReadObject();
/* 672 */     this.paint = SerialUtilities.readPaint(stream);
/* 673 */     this.stroke = SerialUtilities.readStroke(stream);
/* 674 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 675 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 676 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 677 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/Marker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */