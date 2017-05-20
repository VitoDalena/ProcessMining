/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.block.AbstractBlock;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LegendGraphic
/*     */   extends AbstractBlock
/*     */   implements Block, PublicCloneable
/*     */ {
/*     */   static final long serialVersionUID = -1338791523854985009L;
/*     */   private boolean shapeVisible;
/*     */   private transient Shape shape;
/*     */   private RectangleAnchor shapeLocation;
/*     */   private RectangleAnchor shapeAnchor;
/*     */   private boolean shapeFilled;
/*     */   private transient Paint fillPaint;
/*     */   private GradientPaintTransformer fillPaintTransformer;
/*     */   private boolean shapeOutlineVisible;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Stroke outlineStroke;
/*     */   private boolean lineVisible;
/*     */   private transient Shape line;
/*     */   private transient Stroke lineStroke;
/*     */   private transient Paint linePaint;
/*     */   
/*     */   public LegendGraphic(Shape shape, Paint fillPaint)
/*     */   {
/* 155 */     if (shape == null) {
/* 156 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*     */     }
/* 158 */     if (fillPaint == null) {
/* 159 */       throw new IllegalArgumentException("Null 'fillPaint' argument.");
/*     */     }
/* 161 */     this.shapeVisible = true;
/* 162 */     this.shape = shape;
/* 163 */     this.shapeAnchor = RectangleAnchor.CENTER;
/* 164 */     this.shapeLocation = RectangleAnchor.CENTER;
/* 165 */     this.shapeFilled = true;
/* 166 */     this.fillPaint = fillPaint;
/* 167 */     this.fillPaintTransformer = new StandardGradientPaintTransformer();
/* 168 */     setPadding(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShapeVisible()
/*     */   {
/* 180 */     return this.shapeVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeVisible(boolean visible)
/*     */   {
/* 192 */     this.shapeVisible = visible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getShape()
/*     */   {
/* 203 */     return this.shape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShape(Shape shape)
/*     */   {
/* 214 */     this.shape = shape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShapeFilled()
/*     */   {
/* 226 */     return this.shapeFilled;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeFilled(boolean filled)
/*     */   {
/* 238 */     this.shapeFilled = filled;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getFillPaint()
/*     */   {
/* 249 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFillPaint(Paint paint)
/*     */   {
/* 260 */     this.fillPaint = paint;
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
/*     */   public GradientPaintTransformer getFillPaintTransformer()
/*     */   {
/* 274 */     return this.fillPaintTransformer;
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
/*     */   public void setFillPaintTransformer(GradientPaintTransformer transformer)
/*     */   {
/* 288 */     if (transformer == null) {
/* 289 */       throw new IllegalArgumentException("Null 'transformer' argument.");
/*     */     }
/* 291 */     this.fillPaintTransformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShapeOutlineVisible()
/*     */   {
/* 302 */     return this.shapeOutlineVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeOutlineVisible(boolean visible)
/*     */   {
/* 314 */     this.shapeOutlineVisible = visible;
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
/* 325 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlinePaint(Paint paint)
/*     */   {
/* 336 */     this.outlinePaint = paint;
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
/* 347 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlineStroke(Stroke stroke)
/*     */   {
/* 358 */     this.outlineStroke = stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getShapeAnchor()
/*     */   {
/* 369 */     return this.shapeAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeAnchor(RectangleAnchor anchor)
/*     */   {
/* 381 */     if (anchor == null) {
/* 382 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 384 */     this.shapeAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getShapeLocation()
/*     */   {
/* 395 */     return this.shapeLocation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeLocation(RectangleAnchor location)
/*     */   {
/* 407 */     if (location == null) {
/* 408 */       throw new IllegalArgumentException("Null 'location' argument.");
/*     */     }
/* 410 */     this.shapeLocation = location;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLineVisible()
/*     */   {
/* 421 */     return this.lineVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLineVisible(boolean visible)
/*     */   {
/* 432 */     this.lineVisible = visible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getLine()
/*     */   {
/* 443 */     return this.line;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLine(Shape line)
/*     */   {
/* 455 */     this.line = line;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLinePaint()
/*     */   {
/* 466 */     return this.linePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLinePaint(Paint paint)
/*     */   {
/* 477 */     this.linePaint = paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getLineStroke()
/*     */   {
/* 488 */     return this.lineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLineStroke(Stroke stroke)
/*     */   {
/* 499 */     this.lineStroke = stroke;
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
/* 512 */     RectangleConstraint contentConstraint = toContentConstraint(constraint);
/* 513 */     LengthConstraintType w = contentConstraint.getWidthConstraintType();
/* 514 */     LengthConstraintType h = contentConstraint.getHeightConstraintType();
/* 515 */     Size2D contentSize = null;
/* 516 */     if (w == LengthConstraintType.NONE) {
/* 517 */       if (h == LengthConstraintType.NONE) {
/* 518 */         contentSize = arrangeNN(g2);
/*     */       } else {
/* 520 */         if (h == LengthConstraintType.RANGE) {
/* 521 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/* 523 */         if (h == LengthConstraintType.FIXED) {
/* 524 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/*     */       }
/* 527 */     } else if (w == LengthConstraintType.RANGE) {
/* 528 */       if (h == LengthConstraintType.NONE) {
/* 529 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 531 */       if (h == LengthConstraintType.RANGE) {
/* 532 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 534 */       if (h == LengthConstraintType.FIXED) {
/* 535 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 538 */     else if (w == LengthConstraintType.FIXED) {
/* 539 */       if (h == LengthConstraintType.NONE) {
/* 540 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 542 */       if (h == LengthConstraintType.RANGE) {
/* 543 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/* 545 */       if (h == LengthConstraintType.FIXED) {
/* 546 */         contentSize = new Size2D(contentConstraint.getWidth(), contentConstraint.getHeight());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 552 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
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
/*     */   protected Size2D arrangeNN(Graphics2D g2)
/*     */   {
/* 568 */     Rectangle2D contentSize = new Rectangle2D.Double();
/* 569 */     if (this.line != null) {
/* 570 */       contentSize.setRect(this.line.getBounds2D());
/*     */     }
/* 572 */     if (this.shape != null) {
/* 573 */       contentSize = contentSize.createUnion(this.shape.getBounds2D());
/*     */     }
/* 575 */     return new Size2D(contentSize.getWidth(), contentSize.getHeight());
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
/* 586 */     area = trimMargin(area);
/* 587 */     drawBorder(g2, area);
/* 588 */     area = trimBorder(area);
/* 589 */     area = trimPadding(area);
/*     */     
/* 591 */     if (this.lineVisible) {
/* 592 */       Point2D location = RectangleAnchor.coordinates(area, this.shapeLocation);
/*     */       
/* 594 */       Shape aLine = ShapeUtilities.createTranslatedShape(getLine(), this.shapeAnchor, location.getX(), location.getY());
/*     */       
/* 596 */       g2.setPaint(this.linePaint);
/* 597 */       g2.setStroke(this.lineStroke);
/* 598 */       g2.draw(aLine);
/*     */     }
/*     */     
/* 601 */     if (this.shapeVisible) {
/* 602 */       Point2D location = RectangleAnchor.coordinates(area, this.shapeLocation);
/*     */       
/*     */ 
/* 605 */       Shape s = ShapeUtilities.createTranslatedShape(this.shape, this.shapeAnchor, location.getX(), location.getY());
/*     */       
/* 607 */       if (this.shapeFilled) {
/* 608 */         Paint p = this.fillPaint;
/* 609 */         if ((p instanceof GradientPaint)) {
/* 610 */           GradientPaint gp = (GradientPaint)this.fillPaint;
/* 611 */           p = this.fillPaintTransformer.transform(gp, s);
/*     */         }
/* 613 */         g2.setPaint(p);
/* 614 */         g2.fill(s);
/*     */       }
/* 616 */       if (this.shapeOutlineVisible) {
/* 617 */         g2.setPaint(this.outlinePaint);
/* 618 */         g2.setStroke(this.outlineStroke);
/* 619 */         g2.draw(s);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 635 */     draw(g2, area);
/* 636 */     return null;
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
/* 648 */     if (!(obj instanceof LegendGraphic)) {
/* 649 */       return false;
/*     */     }
/* 651 */     LegendGraphic that = (LegendGraphic)obj;
/* 652 */     if (this.shapeVisible != that.shapeVisible) {
/* 653 */       return false;
/*     */     }
/* 655 */     if (!ShapeUtilities.equal(this.shape, that.shape)) {
/* 656 */       return false;
/*     */     }
/* 658 */     if (this.shapeFilled != that.shapeFilled) {
/* 659 */       return false;
/*     */     }
/* 661 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 662 */       return false;
/*     */     }
/* 664 */     if (!ObjectUtilities.equal(this.fillPaintTransformer, that.fillPaintTransformer))
/*     */     {
/* 666 */       return false;
/*     */     }
/* 668 */     if (this.shapeOutlineVisible != that.shapeOutlineVisible) {
/* 669 */       return false;
/*     */     }
/* 671 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 672 */       return false;
/*     */     }
/* 674 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
/* 675 */       return false;
/*     */     }
/* 677 */     if (this.shapeAnchor != that.shapeAnchor) {
/* 678 */       return false;
/*     */     }
/* 680 */     if (this.shapeLocation != that.shapeLocation) {
/* 681 */       return false;
/*     */     }
/* 683 */     if (this.lineVisible != that.lineVisible) {
/* 684 */       return false;
/*     */     }
/* 686 */     if (!ShapeUtilities.equal(this.line, that.line)) {
/* 687 */       return false;
/*     */     }
/* 689 */     if (!PaintUtilities.equal(this.linePaint, that.linePaint)) {
/* 690 */       return false;
/*     */     }
/* 692 */     if (!ObjectUtilities.equal(this.lineStroke, that.lineStroke)) {
/* 693 */       return false;
/*     */     }
/* 695 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 704 */     int result = 193;
/* 705 */     result = 37 * result + ObjectUtilities.hashCode(this.fillPaint);
/*     */     
/* 707 */     return result;
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
/* 718 */     LegendGraphic clone = (LegendGraphic)super.clone();
/* 719 */     clone.shape = ShapeUtilities.clone(this.shape);
/* 720 */     clone.line = ShapeUtilities.clone(this.line);
/* 721 */     return clone;
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
/* 732 */     stream.defaultWriteObject();
/* 733 */     SerialUtilities.writeShape(this.shape, stream);
/* 734 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 735 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 736 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 737 */     SerialUtilities.writeShape(this.line, stream);
/* 738 */     SerialUtilities.writePaint(this.linePaint, stream);
/* 739 */     SerialUtilities.writeStroke(this.lineStroke, stream);
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
/* 752 */     stream.defaultReadObject();
/* 753 */     this.shape = SerialUtilities.readShape(stream);
/* 754 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 755 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 756 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 757 */     this.line = SerialUtilities.readShape(stream);
/* 758 */     this.linePaint = SerialUtilities.readPaint(stream);
/* 759 */     this.lineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/LegendGraphic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */