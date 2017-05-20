/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractBlock
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7689852412141274563L;
/*     */   private String id;
/*     */   private RectangleInsets margin;
/*     */   private BlockFrame frame;
/*     */   private RectangleInsets padding;
/*     */   private double width;
/*     */   private double height;
/*     */   private transient Rectangle2D bounds;
/*     */   
/*     */   protected AbstractBlock()
/*     */   {
/* 108 */     this.id = null;
/* 109 */     this.width = 0.0D;
/* 110 */     this.height = 0.0D;
/* 111 */     this.bounds = new Rectangle2D.Float();
/* 112 */     this.margin = RectangleInsets.ZERO_INSETS;
/* 113 */     this.frame = BlockBorder.NONE;
/* 114 */     this.padding = RectangleInsets.ZERO_INSETS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getID()
/*     */   {
/* 125 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setID(String id)
/*     */   {
/* 136 */     this.id = id;
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
/*     */   public double getWidth()
/*     */   {
/* 149 */     return this.width;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWidth(double width)
/*     */   {
/* 160 */     this.width = width;
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
/*     */   public double getHeight()
/*     */   {
/* 173 */     return this.height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHeight(double height)
/*     */   {
/* 184 */     this.height = height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getMargin()
/*     */   {
/* 195 */     return this.margin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMargin(RectangleInsets margin)
/*     */   {
/* 207 */     if (margin == null) {
/* 208 */       throw new IllegalArgumentException("Null 'margin' argument.");
/*     */     }
/* 210 */     this.margin = margin;
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
/*     */   public void setMargin(double top, double left, double bottom, double right)
/*     */   {
/* 225 */     setMargin(new RectangleInsets(top, left, bottom, right));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public BlockBorder getBorder()
/*     */   {
/* 236 */     if ((this.frame instanceof BlockBorder)) {
/* 237 */       return (BlockBorder)this.frame;
/*     */     }
/*     */     
/* 240 */     return null;
/*     */   }
/*     */   
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
/*     */   public void setBorder(BlockBorder border)
/*     */   {
/* 255 */     setFrame(border);
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
/*     */   public void setBorder(double top, double left, double bottom, double right)
/*     */   {
/* 268 */     setFrame(new BlockBorder(top, left, bottom, right));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockFrame getFrame()
/*     */   {
/* 280 */     return this.frame;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFrame(BlockFrame frame)
/*     */   {
/* 292 */     if (frame == null) {
/* 293 */       throw new IllegalArgumentException("Null 'frame' argument.");
/*     */     }
/* 295 */     this.frame = frame;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getPadding()
/*     */   {
/* 306 */     return this.padding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPadding(RectangleInsets padding)
/*     */   {
/* 318 */     if (padding == null) {
/* 319 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*     */     }
/* 321 */     this.padding = padding;
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
/*     */   public void setPadding(double top, double left, double bottom, double right)
/*     */   {
/* 334 */     setPadding(new RectangleInsets(top, left, bottom, right));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getContentXOffset()
/*     */   {
/* 345 */     return this.margin.getLeft() + this.frame.getInsets().getLeft() + this.padding.getLeft();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getContentYOffset()
/*     */   {
/* 357 */     return this.margin.getTop() + this.frame.getInsets().getTop() + this.padding.getTop();
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
/*     */   public Size2D arrange(Graphics2D g2)
/*     */   {
/* 370 */     return arrange(g2, RectangleConstraint.NONE);
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 383 */     Size2D base = new Size2D(getWidth(), getHeight());
/* 384 */     return constraint.calculateConstrainedSize(base);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D getBounds()
/*     */   {
/* 395 */     return this.bounds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBounds(Rectangle2D bounds)
/*     */   {
/* 406 */     if (bounds == null) {
/* 407 */       throw new IllegalArgumentException("Null 'bounds' argument.");
/*     */     }
/* 409 */     this.bounds = bounds;
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
/*     */   protected double trimToContentWidth(double fixedWidth)
/*     */   {
/* 424 */     double result = this.margin.trimWidth(fixedWidth);
/* 425 */     result = this.frame.getInsets().trimWidth(result);
/* 426 */     result = this.padding.trimWidth(result);
/* 427 */     return Math.max(result, 0.0D);
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
/*     */   protected double trimToContentHeight(double fixedHeight)
/*     */   {
/* 442 */     double result = this.margin.trimHeight(fixedHeight);
/* 443 */     result = this.frame.getInsets().trimHeight(result);
/* 444 */     result = this.padding.trimHeight(result);
/* 445 */     return Math.max(result, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected RectangleConstraint toContentConstraint(RectangleConstraint c)
/*     */   {
/* 457 */     if (c == null) {
/* 458 */       throw new IllegalArgumentException("Null 'c' argument.");
/*     */     }
/* 460 */     if (c.equals(RectangleConstraint.NONE)) {
/* 461 */       return c;
/*     */     }
/* 463 */     double w = c.getWidth();
/* 464 */     Range wr = c.getWidthRange();
/* 465 */     double h = c.getHeight();
/* 466 */     Range hr = c.getHeightRange();
/* 467 */     double ww = trimToContentWidth(w);
/* 468 */     double hh = trimToContentHeight(h);
/* 469 */     Range wwr = trimToContentWidth(wr);
/* 470 */     Range hhr = trimToContentHeight(hr);
/* 471 */     return new RectangleConstraint(ww, wwr, c.getWidthConstraintType(), hh, hhr, c.getHeightConstraintType());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Range trimToContentWidth(Range r)
/*     */   {
/* 478 */     if (r == null) {
/* 479 */       return null;
/*     */     }
/* 481 */     double lowerBound = 0.0D;
/* 482 */     double upperBound = Double.POSITIVE_INFINITY;
/* 483 */     if (r.getLowerBound() > 0.0D) {
/* 484 */       lowerBound = trimToContentWidth(r.getLowerBound());
/*     */     }
/* 486 */     if (r.getUpperBound() < Double.POSITIVE_INFINITY) {
/* 487 */       upperBound = trimToContentWidth(r.getUpperBound());
/*     */     }
/* 489 */     return new Range(lowerBound, upperBound);
/*     */   }
/*     */   
/*     */   private Range trimToContentHeight(Range r) {
/* 493 */     if (r == null) {
/* 494 */       return null;
/*     */     }
/* 496 */     double lowerBound = 0.0D;
/* 497 */     double upperBound = Double.POSITIVE_INFINITY;
/* 498 */     if (r.getLowerBound() > 0.0D) {
/* 499 */       lowerBound = trimToContentHeight(r.getLowerBound());
/*     */     }
/* 501 */     if (r.getUpperBound() < Double.POSITIVE_INFINITY) {
/* 502 */       upperBound = trimToContentHeight(r.getUpperBound());
/*     */     }
/* 504 */     return new Range(lowerBound, upperBound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double calculateTotalWidth(double contentWidth)
/*     */   {
/* 515 */     double result = contentWidth;
/* 516 */     result = this.padding.extendWidth(result);
/* 517 */     result = this.frame.getInsets().extendWidth(result);
/* 518 */     result = this.margin.extendWidth(result);
/* 519 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double calculateTotalHeight(double contentHeight)
/*     */   {
/* 530 */     double result = contentHeight;
/* 531 */     result = this.padding.extendHeight(result);
/* 532 */     result = this.frame.getInsets().extendHeight(result);
/* 533 */     result = this.margin.extendHeight(result);
/* 534 */     return result;
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
/*     */   protected Rectangle2D trimMargin(Rectangle2D area)
/*     */   {
/* 547 */     this.margin.trim(area);
/* 548 */     return area;
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
/*     */   protected Rectangle2D trimBorder(Rectangle2D area)
/*     */   {
/* 561 */     this.frame.getInsets().trim(area);
/* 562 */     return area;
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
/*     */   protected Rectangle2D trimPadding(Rectangle2D area)
/*     */   {
/* 575 */     this.padding.trim(area);
/* 576 */     return area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawBorder(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 586 */     this.frame.draw(g2, area);
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
/* 597 */     if (obj == this) {
/* 598 */       return true;
/*     */     }
/* 600 */     if (!(obj instanceof AbstractBlock)) {
/* 601 */       return false;
/*     */     }
/* 603 */     AbstractBlock that = (AbstractBlock)obj;
/* 604 */     if (!ObjectUtilities.equal(this.id, that.id)) {
/* 605 */       return false;
/*     */     }
/* 607 */     if (!this.frame.equals(that.frame)) {
/* 608 */       return false;
/*     */     }
/* 610 */     if (!this.bounds.equals(that.bounds)) {
/* 611 */       return false;
/*     */     }
/* 613 */     if (!this.margin.equals(that.margin)) {
/* 614 */       return false;
/*     */     }
/* 616 */     if (!this.padding.equals(that.padding)) {
/* 617 */       return false;
/*     */     }
/* 619 */     if (this.height != that.height) {
/* 620 */       return false;
/*     */     }
/* 622 */     if (this.width != that.width) {
/* 623 */       return false;
/*     */     }
/* 625 */     return true;
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
/* 637 */     AbstractBlock clone = (AbstractBlock)super.clone();
/* 638 */     clone.bounds = ((Rectangle2D)ShapeUtilities.clone(this.bounds));
/* 639 */     if ((this.frame instanceof PublicCloneable)) {
/* 640 */       PublicCloneable pc = (PublicCloneable)this.frame;
/* 641 */       clone.frame = ((BlockFrame)pc.clone());
/*     */     }
/* 643 */     return clone;
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
/* 654 */     stream.defaultWriteObject();
/* 655 */     SerialUtilities.writeShape(this.bounds, stream);
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
/* 668 */     stream.defaultReadObject();
/* 669 */     this.bounds = ((Rectangle2D)SerialUtilities.readShape(stream));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/AbstractBlock.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */