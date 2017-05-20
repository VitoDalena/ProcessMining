/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.labels.CrosshairLabelGenerator;
/*     */ import org.jfree.chart.labels.StandardCrosshairLabelGenerator;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Crosshair
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private boolean visible;
/*     */   private double value;
/*     */   private transient Paint paint;
/*     */   private transient Stroke stroke;
/*     */   private boolean labelVisible;
/*     */   private RectangleAnchor labelAnchor;
/*     */   private CrosshairLabelGenerator labelGenerator;
/*     */   private double labelXOffset;
/*     */   private double labelYOffset;
/*     */   private Font labelFont;
/*     */   private transient Paint labelPaint;
/*     */   private transient Paint labelBackgroundPaint;
/*     */   private boolean labelOutlineVisible;
/*     */   private transient Stroke labelOutlineStroke;
/*     */   private transient Paint labelOutlinePaint;
/*     */   private transient PropertyChangeSupport pcs;
/*     */   
/*     */   public Crosshair()
/*     */   {
/* 136 */     this(0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Crosshair(double value)
/*     */   {
/* 145 */     this(value, Color.black, new BasicStroke(1.0F));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Crosshair(double value, Paint paint, Stroke stroke)
/*     */   {
/* 156 */     if (paint == null) {
/* 157 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 159 */     if (stroke == null) {
/* 160 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 162 */     this.visible = true;
/* 163 */     this.value = value;
/* 164 */     this.paint = paint;
/* 165 */     this.stroke = stroke;
/* 166 */     this.labelVisible = false;
/* 167 */     this.labelGenerator = new StandardCrosshairLabelGenerator();
/* 168 */     this.labelAnchor = RectangleAnchor.BOTTOM_LEFT;
/* 169 */     this.labelXOffset = 3.0D;
/* 170 */     this.labelYOffset = 3.0D;
/* 171 */     this.labelFont = new Font("Tahoma", 0, 12);
/* 172 */     this.labelPaint = Color.black;
/* 173 */     this.labelBackgroundPaint = new Color(0, 0, 255, 63);
/* 174 */     this.labelOutlineVisible = true;
/* 175 */     this.labelOutlinePaint = Color.black;
/* 176 */     this.labelOutlineStroke = new BasicStroke(0.5F);
/* 177 */     this.pcs = new PropertyChangeSupport(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isVisible()
/*     */   {
/* 187 */     return this.visible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVisible(boolean visible)
/*     */   {
/* 198 */     boolean old = this.visible;
/* 199 */     this.visible = visible;
/* 200 */     this.pcs.firePropertyChange("visible", old, visible);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue()
/*     */   {
/* 209 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(double value)
/*     */   {
/* 219 */     Double oldValue = new Double(this.value);
/* 220 */     this.value = value;
/* 221 */     this.pcs.firePropertyChange("value", oldValue, new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 230 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 240 */     Paint old = this.paint;
/* 241 */     this.paint = paint;
/* 242 */     this.pcs.firePropertyChange("paint", old, paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 251 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke stroke)
/*     */   {
/* 261 */     Stroke old = this.stroke;
/* 262 */     this.stroke = stroke;
/* 263 */     this.pcs.firePropertyChange("stroke", old, stroke);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLabelVisible()
/*     */   {
/* 273 */     return this.labelVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelVisible(boolean visible)
/*     */   {
/* 284 */     boolean old = this.labelVisible;
/* 285 */     this.labelVisible = visible;
/* 286 */     this.pcs.firePropertyChange("labelVisible", old, visible);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CrosshairLabelGenerator getLabelGenerator()
/*     */   {
/* 295 */     return this.labelGenerator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelGenerator(CrosshairLabelGenerator generator)
/*     */   {
/* 305 */     if (generator == null) {
/* 306 */       throw new IllegalArgumentException("Null 'generator' argument.");
/*     */     }
/* 308 */     CrosshairLabelGenerator old = this.labelGenerator;
/* 309 */     this.labelGenerator = generator;
/* 310 */     this.pcs.firePropertyChange("labelGenerator", old, generator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getLabelAnchor()
/*     */   {
/* 319 */     return this.labelAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelAnchor(RectangleAnchor anchor)
/*     */   {
/* 329 */     RectangleAnchor old = this.labelAnchor;
/* 330 */     this.labelAnchor = anchor;
/* 331 */     this.pcs.firePropertyChange("labelAnchor", old, anchor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLabelXOffset()
/*     */   {
/* 340 */     return this.labelXOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelXOffset(double offset)
/*     */   {
/* 350 */     Double old = new Double(this.labelXOffset);
/* 351 */     this.labelXOffset = offset;
/* 352 */     this.pcs.firePropertyChange("labelXOffset", old, new Double(offset));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLabelYOffset()
/*     */   {
/* 361 */     return this.labelYOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelYOffset(double offset)
/*     */   {
/* 371 */     Double old = new Double(this.labelYOffset);
/* 372 */     this.labelYOffset = offset;
/* 373 */     this.pcs.firePropertyChange("labelYOffset", old, new Double(offset));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getLabelFont()
/*     */   {
/* 382 */     return this.labelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelFont(Font font)
/*     */   {
/* 392 */     if (font == null) {
/* 393 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 395 */     Font old = this.labelFont;
/* 396 */     this.labelFont = font;
/* 397 */     this.pcs.firePropertyChange("labelFont", old, font);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelPaint()
/*     */   {
/* 406 */     return this.labelPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelPaint(Paint paint)
/*     */   {
/* 416 */     if (paint == null) {
/* 417 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 419 */     Paint old = this.labelPaint;
/* 420 */     this.labelPaint = paint;
/* 421 */     this.pcs.firePropertyChange("labelPaint", old, paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelBackgroundPaint()
/*     */   {
/* 430 */     return this.labelBackgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelBackgroundPaint(Paint paint)
/*     */   {
/* 440 */     Paint old = this.labelBackgroundPaint;
/* 441 */     this.labelBackgroundPaint = paint;
/* 442 */     this.pcs.firePropertyChange("labelBackgroundPaint", old, paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLabelOutlineVisible()
/*     */   {
/* 451 */     return this.labelOutlineVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOutlineVisible(boolean visible)
/*     */   {
/* 462 */     boolean old = this.labelOutlineVisible;
/* 463 */     this.labelOutlineVisible = visible;
/* 464 */     this.pcs.firePropertyChange("labelOutlineVisible", old, visible);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelOutlinePaint()
/*     */   {
/* 473 */     return this.labelOutlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOutlinePaint(Paint paint)
/*     */   {
/* 483 */     if (paint == null) {
/* 484 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 486 */     Paint old = this.labelOutlinePaint;
/* 487 */     this.labelOutlinePaint = paint;
/* 488 */     this.pcs.firePropertyChange("labelOutlinePaint", old, paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getLabelOutlineStroke()
/*     */   {
/* 497 */     return this.labelOutlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOutlineStroke(Stroke stroke)
/*     */   {
/* 507 */     if (stroke == null) {
/* 508 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 510 */     Stroke old = this.labelOutlineStroke;
/* 511 */     this.labelOutlineStroke = stroke;
/* 512 */     this.pcs.firePropertyChange("labelOutlineStroke", old, stroke);
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
/* 523 */     if (obj == this) {
/* 524 */       return true;
/*     */     }
/* 526 */     if (!(obj instanceof Crosshair)) {
/* 527 */       return false;
/*     */     }
/* 529 */     Crosshair that = (Crosshair)obj;
/* 530 */     if (this.visible != that.visible) {
/* 531 */       return false;
/*     */     }
/* 533 */     if (this.value != that.value) {
/* 534 */       return false;
/*     */     }
/* 536 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 537 */       return false;
/*     */     }
/* 539 */     if (!this.stroke.equals(that.stroke)) {
/* 540 */       return false;
/*     */     }
/* 542 */     if (this.labelVisible != that.labelVisible) {
/* 543 */       return false;
/*     */     }
/* 545 */     if (!this.labelGenerator.equals(that.labelGenerator)) {
/* 546 */       return false;
/*     */     }
/* 548 */     if (!this.labelAnchor.equals(that.labelAnchor)) {
/* 549 */       return false;
/*     */     }
/* 551 */     if (this.labelXOffset != that.labelXOffset) {
/* 552 */       return false;
/*     */     }
/* 554 */     if (this.labelYOffset != that.labelYOffset) {
/* 555 */       return false;
/*     */     }
/* 557 */     if (!this.labelFont.equals(that.labelFont)) {
/* 558 */       return false;
/*     */     }
/* 560 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/* 561 */       return false;
/*     */     }
/* 563 */     if (!PaintUtilities.equal(this.labelBackgroundPaint, that.labelBackgroundPaint))
/*     */     {
/* 565 */       return false;
/*     */     }
/* 567 */     if (this.labelOutlineVisible != that.labelOutlineVisible) {
/* 568 */       return false;
/*     */     }
/* 570 */     if (!PaintUtilities.equal(this.labelOutlinePaint, that.labelOutlinePaint))
/*     */     {
/* 572 */       return false;
/*     */     }
/* 574 */     if (!this.labelOutlineStroke.equals(that.labelOutlineStroke)) {
/* 575 */       return false;
/*     */     }
/* 577 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 586 */     int hash = 7;
/* 587 */     hash = HashUtilities.hashCode(hash, this.visible);
/* 588 */     hash = HashUtilities.hashCode(hash, this.value);
/* 589 */     hash = HashUtilities.hashCode(hash, this.paint);
/* 590 */     hash = HashUtilities.hashCode(hash, this.stroke);
/* 591 */     hash = HashUtilities.hashCode(hash, this.labelVisible);
/* 592 */     hash = HashUtilities.hashCode(hash, this.labelAnchor);
/* 593 */     hash = HashUtilities.hashCode(hash, this.labelGenerator);
/* 594 */     hash = HashUtilities.hashCode(hash, this.labelXOffset);
/* 595 */     hash = HashUtilities.hashCode(hash, this.labelYOffset);
/* 596 */     hash = HashUtilities.hashCode(hash, this.labelFont);
/* 597 */     hash = HashUtilities.hashCode(hash, this.labelPaint);
/* 598 */     hash = HashUtilities.hashCode(hash, this.labelBackgroundPaint);
/* 599 */     hash = HashUtilities.hashCode(hash, this.labelOutlineVisible);
/* 600 */     hash = HashUtilities.hashCode(hash, this.labelOutlineStroke);
/* 601 */     hash = HashUtilities.hashCode(hash, this.labelOutlinePaint);
/* 602 */     return hash;
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
/* 614 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPropertyChangeListener(PropertyChangeListener l)
/*     */   {
/* 623 */     this.pcs.addPropertyChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePropertyChangeListener(PropertyChangeListener l)
/*     */   {
/* 632 */     this.pcs.removePropertyChangeListener(l);
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
/* 643 */     stream.defaultWriteObject();
/* 644 */     SerialUtilities.writePaint(this.paint, stream);
/* 645 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 646 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 647 */     SerialUtilities.writePaint(this.labelBackgroundPaint, stream);
/* 648 */     SerialUtilities.writeStroke(this.labelOutlineStroke, stream);
/* 649 */     SerialUtilities.writePaint(this.labelOutlinePaint, stream);
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
/* 662 */     stream.defaultReadObject();
/* 663 */     this.paint = SerialUtilities.readPaint(stream);
/* 664 */     this.stroke = SerialUtilities.readStroke(stream);
/* 665 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 666 */     this.labelBackgroundPaint = SerialUtilities.readPaint(stream);
/* 667 */     this.labelOutlineStroke = SerialUtilities.readStroke(stream);
/* 668 */     this.labelOutlinePaint = SerialUtilities.readPaint(stream);
/* 669 */     this.pcs = new PropertyChangeSupport(this);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/Crosshair.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */