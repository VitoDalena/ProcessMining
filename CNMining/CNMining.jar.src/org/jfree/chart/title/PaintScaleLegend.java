/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.axis.AxisLocation;
/*     */ import org.jfree.chart.axis.AxisSpace;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.block.BlockFrame;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.event.AxisChangeListener;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.renderer.PaintScale;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
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
/*     */ public class PaintScaleLegend
/*     */   extends Title
/*     */   implements AxisChangeListener, PublicCloneable
/*     */ {
/*     */   static final long serialVersionUID = -1365146490993227503L;
/*     */   private PaintScale scale;
/*     */   private ValueAxis axis;
/*     */   private AxisLocation axisLocation;
/*     */   private double axisOffset;
/*     */   private double stripWidth;
/*     */   private boolean stripOutlineVisible;
/*     */   private transient Paint stripOutlinePaint;
/*     */   private transient Stroke stripOutlineStroke;
/*     */   private transient Paint backgroundPaint;
/*     */   private int subdivisions;
/*     */   
/*     */   public PaintScaleLegend(PaintScale scale, ValueAxis axis)
/*     */   {
/* 133 */     if (axis == null) {
/* 134 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*     */     }
/* 136 */     this.scale = scale;
/* 137 */     this.axis = axis;
/* 138 */     this.axis.addChangeListener(this);
/* 139 */     this.axisLocation = AxisLocation.BOTTOM_OR_LEFT;
/* 140 */     this.axisOffset = 0.0D;
/* 141 */     this.axis.setRange(scale.getLowerBound(), scale.getUpperBound());
/* 142 */     this.stripWidth = 15.0D;
/* 143 */     this.stripOutlineVisible = true;
/* 144 */     this.stripOutlinePaint = Color.gray;
/* 145 */     this.stripOutlineStroke = new BasicStroke(0.5F);
/* 146 */     this.backgroundPaint = Color.white;
/* 147 */     this.subdivisions = 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PaintScale getScale()
/*     */   {
/* 158 */     return this.scale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScale(PaintScale scale)
/*     */   {
/* 170 */     if (scale == null) {
/* 171 */       throw new IllegalArgumentException("Null 'scale' argument.");
/*     */     }
/* 173 */     this.scale = scale;
/* 174 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueAxis getAxis()
/*     */   {
/* 185 */     return this.axis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxis(ValueAxis axis)
/*     */   {
/* 197 */     if (axis == null) {
/* 198 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*     */     }
/* 200 */     this.axis.removeChangeListener(this);
/* 201 */     this.axis = axis;
/* 202 */     this.axis.addChangeListener(this);
/* 203 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisLocation getAxisLocation()
/*     */   {
/* 214 */     return this.axisLocation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxisLocation(AxisLocation location)
/*     */   {
/* 226 */     if (location == null) {
/* 227 */       throw new IllegalArgumentException("Null 'location' argument.");
/*     */     }
/* 229 */     this.axisLocation = location;
/* 230 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAxisOffset()
/*     */   {
/* 241 */     return this.axisOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxisOffset(double offset)
/*     */   {
/* 251 */     this.axisOffset = offset;
/* 252 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStripWidth()
/*     */   {
/* 263 */     return this.stripWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStripWidth(double width)
/*     */   {
/* 275 */     this.stripWidth = width;
/* 276 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isStripOutlineVisible()
/*     */   {
/* 288 */     return this.stripOutlineVisible;
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
/*     */   public void setStripOutlineVisible(boolean visible)
/*     */   {
/* 301 */     this.stripOutlineVisible = visible;
/* 302 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getStripOutlinePaint()
/*     */   {
/* 313 */     return this.stripOutlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStripOutlinePaint(Paint paint)
/*     */   {
/* 325 */     if (paint == null) {
/* 326 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 328 */     this.stripOutlinePaint = paint;
/* 329 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStripOutlineStroke()
/*     */   {
/* 340 */     return this.stripOutlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStripOutlineStroke(Stroke stroke)
/*     */   {
/* 352 */     if (stroke == null) {
/* 353 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 355 */     this.stripOutlineStroke = stroke;
/* 356 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 365 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 375 */     this.backgroundPaint = paint;
/* 376 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSubdivisionCount()
/*     */   {
/* 387 */     return this.subdivisions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubdivisionCount(int count)
/*     */   {
/* 399 */     if (count <= 0) {
/* 400 */       throw new IllegalArgumentException("Requires 'count' > 0.");
/*     */     }
/* 402 */     this.subdivisions = count;
/* 403 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void axisChanged(AxisChangeEvent event)
/*     */   {
/* 415 */     if (this.axis == event.getAxis()) {
/* 416 */       notifyListeners(new TitleChangeEvent(this));
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 430 */     RectangleConstraint cc = toContentConstraint(constraint);
/* 431 */     LengthConstraintType w = cc.getWidthConstraintType();
/* 432 */     LengthConstraintType h = cc.getHeightConstraintType();
/* 433 */     Size2D contentSize = null;
/* 434 */     if (w == LengthConstraintType.NONE) {
/* 435 */       if (h == LengthConstraintType.NONE) {
/* 436 */         contentSize = new Size2D(getWidth(), getHeight());
/*     */       } else {
/* 438 */         if (h == LengthConstraintType.RANGE) {
/* 439 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/* 441 */         if (h == LengthConstraintType.FIXED) {
/* 442 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/*     */       }
/* 445 */     } else if (w == LengthConstraintType.RANGE) {
/* 446 */       if (h == LengthConstraintType.NONE) {
/* 447 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 449 */       if (h == LengthConstraintType.RANGE) {
/* 450 */         contentSize = arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
/*     */ 
/*     */       }
/* 453 */       else if (h == LengthConstraintType.FIXED) {
/* 454 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 457 */     else if (w == LengthConstraintType.FIXED) {
/* 458 */       if (h == LengthConstraintType.NONE) {
/* 459 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 461 */       if (h == LengthConstraintType.RANGE) {
/* 462 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 464 */       if (h == LengthConstraintType.FIXED) {
/* 465 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 468 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
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
/*     */   protected Size2D arrangeRR(Graphics2D g2, Range widthRange, Range heightRange)
/*     */   {
/* 486 */     RectangleEdge position = getPosition();
/* 487 */     if ((position == RectangleEdge.TOP) || (position == RectangleEdge.BOTTOM))
/*     */     {
/*     */ 
/* 490 */       float maxWidth = (float)widthRange.getUpperBound();
/*     */       
/*     */ 
/* 493 */       AxisSpace space = this.axis.reserveSpace(g2, null, new Rectangle2D.Double(0.0D, 0.0D, maxWidth, 100.0D), RectangleEdge.BOTTOM, null);
/*     */       
/*     */ 
/*     */ 
/* 497 */       return new Size2D(maxWidth, this.stripWidth + this.axisOffset + space.getTop() + space.getBottom());
/*     */     }
/*     */     
/* 500 */     if ((position == RectangleEdge.LEFT) || (position == RectangleEdge.RIGHT))
/*     */     {
/* 502 */       float maxHeight = (float)heightRange.getUpperBound();
/* 503 */       AxisSpace space = this.axis.reserveSpace(g2, null, new Rectangle2D.Double(0.0D, 0.0D, 100.0D, maxHeight), RectangleEdge.RIGHT, null);
/*     */       
/*     */ 
/* 506 */       return new Size2D(this.stripWidth + this.axisOffset + space.getLeft() + space.getRight(), maxHeight);
/*     */     }
/*     */     
/*     */ 
/* 510 */     throw new RuntimeException("Unrecognised position.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 521 */     draw(g2, area, null);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 535 */     Rectangle2D target = (Rectangle2D)area.clone();
/* 536 */     target = trimMargin(target);
/* 537 */     if (this.backgroundPaint != null) {
/* 538 */       g2.setPaint(this.backgroundPaint);
/* 539 */       g2.fill(target);
/*     */     }
/* 541 */     getFrame().draw(g2, target);
/* 542 */     getFrame().getInsets().trim(target);
/* 543 */     target = trimPadding(target);
/* 544 */     double base = this.axis.getLowerBound();
/* 545 */     double increment = this.axis.getRange().getLength() / this.subdivisions;
/* 546 */     Rectangle2D r = new Rectangle2D.Double();
/*     */     
/* 548 */     if (RectangleEdge.isTopOrBottom(getPosition())) {
/* 549 */       RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(this.axisLocation, PlotOrientation.HORIZONTAL);
/*     */       
/* 551 */       if (axisEdge == RectangleEdge.TOP) {
/* 552 */         for (int i = 0; i < this.subdivisions; i++) {
/* 553 */           double v = base + i * increment;
/* 554 */           Paint p = this.scale.getPaint(v);
/* 555 */           double vv0 = this.axis.valueToJava2D(v, target, RectangleEdge.TOP);
/*     */           
/* 557 */           double vv1 = this.axis.valueToJava2D(v + increment, target, RectangleEdge.TOP);
/*     */           
/* 559 */           double ww = Math.abs(vv1 - vv0) + 1.0D;
/* 560 */           r.setRect(Math.min(vv0, vv1), target.getMaxY() - this.stripWidth, ww, this.stripWidth);
/*     */           
/* 562 */           g2.setPaint(p);
/* 563 */           g2.fill(r);
/*     */         }
/* 565 */         if (isStripOutlineVisible()) {
/* 566 */           g2.setPaint(this.stripOutlinePaint);
/* 567 */           g2.setStroke(this.stripOutlineStroke);
/* 568 */           g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMaxY() - this.stripWidth, target.getWidth(), this.stripWidth));
/*     */         }
/*     */         
/*     */ 
/* 572 */         this.axis.draw(g2, target.getMaxY() - this.stripWidth - this.axisOffset, target, target, RectangleEdge.TOP, null);
/*     */ 
/*     */ 
/*     */       }
/* 576 */       else if (axisEdge == RectangleEdge.BOTTOM) {
/* 577 */         for (int i = 0; i < this.subdivisions; i++) {
/* 578 */           double v = base + i * increment;
/* 579 */           Paint p = this.scale.getPaint(v);
/* 580 */           double vv0 = this.axis.valueToJava2D(v, target, RectangleEdge.BOTTOM);
/*     */           
/* 582 */           double vv1 = this.axis.valueToJava2D(v + increment, target, RectangleEdge.BOTTOM);
/*     */           
/* 584 */           double ww = Math.abs(vv1 - vv0) + 1.0D;
/* 585 */           r.setRect(Math.min(vv0, vv1), target.getMinY(), ww, this.stripWidth);
/*     */           
/* 587 */           g2.setPaint(p);
/* 588 */           g2.fill(r);
/*     */         }
/* 590 */         if (isStripOutlineVisible()) {
/* 591 */           g2.setPaint(this.stripOutlinePaint);
/* 592 */           g2.setStroke(this.stripOutlineStroke);
/* 593 */           g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMinY(), target.getWidth(), this.stripWidth));
/*     */         }
/*     */         
/*     */ 
/* 597 */         this.axis.draw(g2, target.getMinY() + this.stripWidth + this.axisOffset, target, target, RectangleEdge.BOTTOM, null);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 603 */       RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(this.axisLocation, PlotOrientation.VERTICAL);
/*     */       
/* 605 */       if (axisEdge == RectangleEdge.LEFT) {
/* 606 */         for (int i = 0; i < this.subdivisions; i++) {
/* 607 */           double v = base + i * increment;
/* 608 */           Paint p = this.scale.getPaint(v);
/* 609 */           double vv0 = this.axis.valueToJava2D(v, target, RectangleEdge.LEFT);
/*     */           
/* 611 */           double vv1 = this.axis.valueToJava2D(v + increment, target, RectangleEdge.LEFT);
/*     */           
/* 613 */           double hh = Math.abs(vv1 - vv0) + 1.0D;
/* 614 */           r.setRect(target.getMaxX() - this.stripWidth, Math.min(vv0, vv1), this.stripWidth, hh);
/*     */           
/* 616 */           g2.setPaint(p);
/* 617 */           g2.fill(r);
/*     */         }
/* 619 */         if (isStripOutlineVisible()) {
/* 620 */           g2.setPaint(this.stripOutlinePaint);
/* 621 */           g2.setStroke(this.stripOutlineStroke);
/* 622 */           g2.draw(new Rectangle2D.Double(target.getMaxX() - this.stripWidth, target.getMinY(), this.stripWidth, target.getHeight()));
/*     */         }
/*     */         
/*     */ 
/* 626 */         this.axis.draw(g2, target.getMaxX() - this.stripWidth - this.axisOffset, target, target, RectangleEdge.LEFT, null);
/*     */ 
/*     */ 
/*     */       }
/* 630 */       else if (axisEdge == RectangleEdge.RIGHT) {
/* 631 */         for (int i = 0; i < this.subdivisions; i++) {
/* 632 */           double v = base + i * increment;
/* 633 */           Paint p = this.scale.getPaint(v);
/* 634 */           double vv0 = this.axis.valueToJava2D(v, target, RectangleEdge.LEFT);
/*     */           
/* 636 */           double vv1 = this.axis.valueToJava2D(v + increment, target, RectangleEdge.LEFT);
/*     */           
/* 638 */           double hh = Math.abs(vv1 - vv0) + 1.0D;
/* 639 */           r.setRect(target.getMinX(), Math.min(vv0, vv1), this.stripWidth, hh);
/*     */           
/* 641 */           g2.setPaint(p);
/* 642 */           g2.fill(r);
/*     */         }
/* 644 */         if (isStripOutlineVisible()) {
/* 645 */           g2.setPaint(this.stripOutlinePaint);
/* 646 */           g2.setStroke(this.stripOutlineStroke);
/* 647 */           g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMinY(), this.stripWidth, target.getHeight()));
/*     */         }
/*     */         
/*     */ 
/* 651 */         this.axis.draw(g2, target.getMinX() + this.stripWidth + this.axisOffset, target, target, RectangleEdge.RIGHT, null);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 656 */     return null;
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
/* 667 */     if (!(obj instanceof PaintScaleLegend)) {
/* 668 */       return false;
/*     */     }
/* 670 */     PaintScaleLegend that = (PaintScaleLegend)obj;
/* 671 */     if (!this.scale.equals(that.scale)) {
/* 672 */       return false;
/*     */     }
/* 674 */     if (!this.axis.equals(that.axis)) {
/* 675 */       return false;
/*     */     }
/* 677 */     if (!this.axisLocation.equals(that.axisLocation)) {
/* 678 */       return false;
/*     */     }
/* 680 */     if (this.axisOffset != that.axisOffset) {
/* 681 */       return false;
/*     */     }
/* 683 */     if (this.stripWidth != that.stripWidth) {
/* 684 */       return false;
/*     */     }
/* 686 */     if (this.stripOutlineVisible != that.stripOutlineVisible) {
/* 687 */       return false;
/*     */     }
/* 689 */     if (!PaintUtilities.equal(this.stripOutlinePaint, that.stripOutlinePaint))
/*     */     {
/* 691 */       return false;
/*     */     }
/* 693 */     if (!this.stripOutlineStroke.equals(that.stripOutlineStroke)) {
/* 694 */       return false;
/*     */     }
/* 696 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 697 */       return false;
/*     */     }
/* 699 */     if (this.subdivisions != that.subdivisions) {
/* 700 */       return false;
/*     */     }
/* 702 */     return super.equals(obj);
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
/* 713 */     stream.defaultWriteObject();
/* 714 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 715 */     SerialUtilities.writePaint(this.stripOutlinePaint, stream);
/* 716 */     SerialUtilities.writeStroke(this.stripOutlineStroke, stream);
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
/* 729 */     stream.defaultReadObject();
/* 730 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 731 */     this.stripOutlinePaint = SerialUtilities.readPaint(stream);
/* 732 */     this.stripOutlineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/PaintScaleLegend.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */