/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.block.BlockResult;
/*     */ import org.jfree.chart.block.EntityBlockParams;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.chart.entity.TitleEntity;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.G2TextMeasurer;
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class TextTitle
/*     */   extends Title
/*     */   implements Serializable, Cloneable, PublicCloneable
/*     */ {
/*     */   private static final long serialVersionUID = 8372008692127477443L;
/* 132 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 1, 12);
/*     */   
/*     */ 
/*     */ 
/* 136 */   public static final Paint DEFAULT_TEXT_PAINT = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */   private String text;
/*     */   
/*     */ 
/*     */ 
/*     */   private Font font;
/*     */   
/*     */ 
/*     */ 
/*     */   private HorizontalAlignment textAlignment;
/*     */   
/*     */ 
/*     */   private transient Paint paint;
/*     */   
/*     */ 
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */ 
/*     */   private String toolTipText;
/*     */   
/*     */ 
/*     */   private String urlText;
/*     */   
/*     */ 
/*     */   private TextBlock content;
/*     */   
/*     */ 
/* 166 */   private boolean expandToFitSpace = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 173 */   private int maximumLinesToDisplay = Integer.MAX_VALUE;
/*     */   
/*     */ 
/*     */ 
/*     */   public TextTitle()
/*     */   {
/* 179 */     this("");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextTitle(String text)
/*     */   {
/* 188 */     this(text, DEFAULT_FONT, DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextTitle(String text, Font font)
/*     */   {
/* 200 */     this(text, font, DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
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
/*     */ 
/*     */ 
/*     */   public TextTitle(String text, Font font, Paint paint, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding)
/*     */   {
/* 224 */     super(position, horizontalAlignment, verticalAlignment, padding);
/*     */     
/* 226 */     if (text == null) {
/* 227 */       throw new NullPointerException("Null 'text' argument.");
/*     */     }
/* 229 */     if (font == null) {
/* 230 */       throw new NullPointerException("Null 'font' argument.");
/*     */     }
/* 232 */     if (paint == null) {
/* 233 */       throw new NullPointerException("Null 'paint' argument.");
/*     */     }
/* 235 */     this.text = text;
/* 236 */     this.font = font;
/* 237 */     this.paint = paint;
/*     */     
/*     */ 
/*     */ 
/* 241 */     this.textAlignment = horizontalAlignment;
/* 242 */     this.backgroundPaint = null;
/* 243 */     this.content = null;
/* 244 */     this.toolTipText = null;
/* 245 */     this.urlText = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getText()
/*     */   {
/* 257 */     return this.text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 267 */     if (text == null) {
/* 268 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 270 */     if (!this.text.equals(text)) {
/* 271 */       this.text = text;
/* 272 */       notifyListeners(new TitleChangeEvent(this));
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
/*     */   public HorizontalAlignment getTextAlignment()
/*     */   {
/* 285 */     return this.textAlignment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTextAlignment(HorizontalAlignment alignment)
/*     */   {
/* 295 */     if (alignment == null) {
/* 296 */       throw new IllegalArgumentException("Null 'alignment' argument.");
/*     */     }
/* 298 */     this.textAlignment = alignment;
/* 299 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 310 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 322 */     if (font == null) {
/* 323 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 325 */     if (!this.font.equals(font)) {
/* 326 */       this.font = font;
/* 327 */       notifyListeners(new TitleChangeEvent(this));
/*     */     }
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
/* 339 */     return this.paint;
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
/* 351 */     if (paint == null) {
/* 352 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 354 */     if (!this.paint.equals(paint)) {
/* 355 */       this.paint = paint;
/* 356 */       notifyListeners(new TitleChangeEvent(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 366 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 377 */     this.backgroundPaint = paint;
/* 378 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToolTipText()
/*     */   {
/* 387 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setToolTipText(String text)
/*     */   {
/* 397 */     this.toolTipText = text;
/* 398 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURLText()
/*     */   {
/* 407 */     return this.urlText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setURLText(String text)
/*     */   {
/* 417 */     this.urlText = text;
/* 418 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getExpandToFitSpace()
/*     */   {
/* 428 */     return this.expandToFitSpace;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExpandToFitSpace(boolean expand)
/*     */   {
/* 439 */     this.expandToFitSpace = expand;
/* 440 */     notifyListeners(new TitleChangeEvent(this));
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
/*     */   public int getMaximumLinesToDisplay()
/*     */   {
/* 453 */     return this.maximumLinesToDisplay;
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
/*     */   public void setMaximumLinesToDisplay(int max)
/*     */   {
/* 467 */     this.maximumLinesToDisplay = max;
/* 468 */     notifyListeners(new TitleChangeEvent(this));
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
/* 481 */     RectangleConstraint cc = toContentConstraint(constraint);
/* 482 */     LengthConstraintType w = cc.getWidthConstraintType();
/* 483 */     LengthConstraintType h = cc.getHeightConstraintType();
/* 484 */     Size2D contentSize = null;
/* 485 */     if (w == LengthConstraintType.NONE) {
/* 486 */       if (h == LengthConstraintType.NONE) {
/* 487 */         contentSize = arrangeNN(g2);
/*     */       } else {
/* 489 */         if (h == LengthConstraintType.RANGE) {
/* 490 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/* 492 */         if (h == LengthConstraintType.FIXED) {
/* 493 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/*     */       }
/* 496 */     } else if (w == LengthConstraintType.RANGE) {
/* 497 */       if (h == LengthConstraintType.NONE) {
/* 498 */         contentSize = arrangeRN(g2, cc.getWidthRange());
/*     */       }
/* 500 */       else if (h == LengthConstraintType.RANGE) {
/* 501 */         contentSize = arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
/*     */ 
/*     */       }
/* 504 */       else if (h == LengthConstraintType.FIXED) {
/* 505 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 508 */     else if (w == LengthConstraintType.FIXED) {
/* 509 */       if (h == LengthConstraintType.NONE) {
/* 510 */         contentSize = arrangeFN(g2, cc.getWidth());
/*     */       } else {
/* 512 */         if (h == LengthConstraintType.RANGE) {
/* 513 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/* 515 */         if (h == LengthConstraintType.FIXED)
/* 516 */           throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 519 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
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
/*     */   protected Size2D arrangeNN(Graphics2D g2)
/*     */   {
/* 536 */     Range max = new Range(0.0D, 3.4028234663852886E38D);
/* 537 */     return arrangeRR(g2, max, max);
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
/*     */   protected Size2D arrangeFN(Graphics2D g2, double w)
/*     */   {
/* 554 */     RectangleEdge position = getPosition();
/* 555 */     if ((position == RectangleEdge.TOP) || (position == RectangleEdge.BOTTOM)) {
/* 556 */       float maxWidth = (float)w;
/* 557 */       g2.setFont(this.font);
/* 558 */       this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, this.maximumLinesToDisplay, new G2TextMeasurer(g2));
/*     */       
/*     */ 
/* 561 */       this.content.setLineAlignment(this.textAlignment);
/* 562 */       Size2D contentSize = this.content.calculateDimensions(g2);
/* 563 */       if (this.expandToFitSpace) {
/* 564 */         return new Size2D(maxWidth, contentSize.getHeight());
/*     */       }
/*     */       
/* 567 */       return contentSize;
/*     */     }
/*     */     
/* 570 */     if ((position == RectangleEdge.LEFT) || (position == RectangleEdge.RIGHT))
/*     */     {
/* 572 */       float maxWidth = Float.MAX_VALUE;
/* 573 */       g2.setFont(this.font);
/* 574 */       this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, this.maximumLinesToDisplay, new G2TextMeasurer(g2));
/*     */       
/*     */ 
/* 577 */       this.content.setLineAlignment(this.textAlignment);
/* 578 */       Size2D contentSize = this.content.calculateDimensions(g2);
/*     */       
/*     */ 
/* 581 */       if (this.expandToFitSpace) {
/* 582 */         return new Size2D(contentSize.getHeight(), maxWidth);
/*     */       }
/*     */       
/* 585 */       return new Size2D(contentSize.height, contentSize.width);
/*     */     }
/*     */     
/*     */ 
/* 589 */     throw new RuntimeException("Unrecognised exception.");
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
/*     */   protected Size2D arrangeRN(Graphics2D g2, Range widthRange)
/*     */   {
/* 607 */     Size2D s = arrangeNN(g2);
/* 608 */     if (widthRange.contains(s.getWidth())) {
/* 609 */       return s;
/*     */     }
/* 611 */     double ww = widthRange.constrain(s.getWidth());
/* 612 */     return arrangeFN(g2, ww);
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
/*     */   protected Size2D arrangeRR(Graphics2D g2, Range widthRange, Range heightRange)
/*     */   {
/* 628 */     RectangleEdge position = getPosition();
/* 629 */     if ((position == RectangleEdge.TOP) || (position == RectangleEdge.BOTTOM)) {
/* 630 */       float maxWidth = (float)widthRange.getUpperBound();
/* 631 */       g2.setFont(this.font);
/* 632 */       this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, this.maximumLinesToDisplay, new G2TextMeasurer(g2));
/*     */       
/*     */ 
/* 635 */       this.content.setLineAlignment(this.textAlignment);
/* 636 */       Size2D contentSize = this.content.calculateDimensions(g2);
/* 637 */       if (this.expandToFitSpace) {
/* 638 */         return new Size2D(maxWidth, contentSize.getHeight());
/*     */       }
/*     */       
/* 641 */       return contentSize;
/*     */     }
/*     */     
/* 644 */     if ((position == RectangleEdge.LEFT) || (position == RectangleEdge.RIGHT))
/*     */     {
/* 646 */       float maxWidth = (float)heightRange.getUpperBound();
/* 647 */       g2.setFont(this.font);
/* 648 */       this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, this.maximumLinesToDisplay, new G2TextMeasurer(g2));
/*     */       
/*     */ 
/* 651 */       this.content.setLineAlignment(this.textAlignment);
/* 652 */       Size2D contentSize = this.content.calculateDimensions(g2);
/*     */       
/*     */ 
/* 655 */       if (this.expandToFitSpace) {
/* 656 */         return new Size2D(contentSize.getHeight(), maxWidth);
/*     */       }
/*     */       
/* 659 */       return new Size2D(contentSize.height, contentSize.width);
/*     */     }
/*     */     
/*     */ 
/* 663 */     throw new RuntimeException("Unrecognised exception.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 675 */     draw(g2, area, null);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 691 */     if (this.content == null) {
/* 692 */       return null;
/*     */     }
/* 694 */     area = trimMargin(area);
/* 695 */     drawBorder(g2, area);
/* 696 */     if (this.text.equals("")) {
/* 697 */       return null;
/*     */     }
/* 699 */     ChartEntity entity = null;
/* 700 */     if ((params instanceof EntityBlockParams)) {
/* 701 */       EntityBlockParams p = (EntityBlockParams)params;
/* 702 */       if (p.getGenerateEntities()) {
/* 703 */         entity = new TitleEntity(area, this, this.toolTipText, this.urlText);
/*     */       }
/*     */     }
/*     */     
/* 707 */     area = trimBorder(area);
/* 708 */     if (this.backgroundPaint != null) {
/* 709 */       g2.setPaint(this.backgroundPaint);
/* 710 */       g2.fill(area);
/*     */     }
/* 712 */     area = trimPadding(area);
/* 713 */     RectangleEdge position = getPosition();
/* 714 */     if ((position == RectangleEdge.TOP) || (position == RectangleEdge.BOTTOM)) {
/* 715 */       drawHorizontal(g2, area);
/*     */     }
/* 717 */     else if ((position == RectangleEdge.LEFT) || (position == RectangleEdge.RIGHT))
/*     */     {
/* 719 */       drawVertical(g2, area);
/*     */     }
/* 721 */     BlockResult result = new BlockResult();
/* 722 */     if (entity != null) {
/* 723 */       StandardEntityCollection sec = new StandardEntityCollection();
/* 724 */       sec.add(entity);
/* 725 */       result.setEntityCollection(sec);
/*     */     }
/* 727 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawHorizontal(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 739 */     Rectangle2D titleArea = (Rectangle2D)area.clone();
/* 740 */     g2.setFont(this.font);
/* 741 */     g2.setPaint(this.paint);
/* 742 */     TextBlockAnchor anchor = null;
/* 743 */     float x = 0.0F;
/* 744 */     HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
/* 745 */     if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 746 */       x = (float)titleArea.getX();
/* 747 */       anchor = TextBlockAnchor.TOP_LEFT;
/*     */     }
/* 749 */     else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 750 */       x = (float)titleArea.getMaxX();
/* 751 */       anchor = TextBlockAnchor.TOP_RIGHT;
/*     */     }
/* 753 */     else if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 754 */       x = (float)titleArea.getCenterX();
/* 755 */       anchor = TextBlockAnchor.TOP_CENTER;
/*     */     }
/* 757 */     float y = 0.0F;
/* 758 */     RectangleEdge position = getPosition();
/* 759 */     if (position == RectangleEdge.TOP) {
/* 760 */       y = (float)titleArea.getY();
/*     */     }
/* 762 */     else if (position == RectangleEdge.BOTTOM) {
/* 763 */       y = (float)titleArea.getMaxY();
/* 764 */       if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 765 */         anchor = TextBlockAnchor.BOTTOM_LEFT;
/*     */       }
/* 767 */       else if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 768 */         anchor = TextBlockAnchor.BOTTOM_CENTER;
/*     */       }
/* 770 */       else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 771 */         anchor = TextBlockAnchor.BOTTOM_RIGHT;
/*     */       }
/*     */     }
/* 774 */     this.content.draw(g2, x, y, anchor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawVertical(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 786 */     Rectangle2D titleArea = (Rectangle2D)area.clone();
/* 787 */     g2.setFont(this.font);
/* 788 */     g2.setPaint(this.paint);
/* 789 */     TextBlockAnchor anchor = null;
/* 790 */     float y = 0.0F;
/* 791 */     VerticalAlignment verticalAlignment = getVerticalAlignment();
/* 792 */     if (verticalAlignment == VerticalAlignment.TOP) {
/* 793 */       y = (float)titleArea.getY();
/* 794 */       anchor = TextBlockAnchor.TOP_RIGHT;
/*     */     }
/* 796 */     else if (verticalAlignment == VerticalAlignment.BOTTOM) {
/* 797 */       y = (float)titleArea.getMaxY();
/* 798 */       anchor = TextBlockAnchor.TOP_LEFT;
/*     */     }
/* 800 */     else if (verticalAlignment == VerticalAlignment.CENTER) {
/* 801 */       y = (float)titleArea.getCenterY();
/* 802 */       anchor = TextBlockAnchor.TOP_CENTER;
/*     */     }
/* 804 */     float x = 0.0F;
/* 805 */     RectangleEdge position = getPosition();
/* 806 */     if (position == RectangleEdge.LEFT) {
/* 807 */       x = (float)titleArea.getX();
/*     */     }
/* 809 */     else if (position == RectangleEdge.RIGHT) {
/* 810 */       x = (float)titleArea.getMaxX();
/* 811 */       if (verticalAlignment == VerticalAlignment.TOP) {
/* 812 */         anchor = TextBlockAnchor.BOTTOM_RIGHT;
/*     */       }
/* 814 */       else if (verticalAlignment == VerticalAlignment.CENTER) {
/* 815 */         anchor = TextBlockAnchor.BOTTOM_CENTER;
/*     */       }
/* 817 */       else if (verticalAlignment == VerticalAlignment.BOTTOM) {
/* 818 */         anchor = TextBlockAnchor.BOTTOM_LEFT;
/*     */       }
/*     */     }
/* 821 */     this.content.draw(g2, x, y, anchor, x, y, -1.5707963267948966D);
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
/* 832 */     if (obj == this) {
/* 833 */       return true;
/*     */     }
/* 835 */     if (!(obj instanceof TextTitle)) {
/* 836 */       return false;
/*     */     }
/* 838 */     TextTitle that = (TextTitle)obj;
/* 839 */     if (!ObjectUtilities.equal(this.text, that.text)) {
/* 840 */       return false;
/*     */     }
/* 842 */     if (!ObjectUtilities.equal(this.font, that.font)) {
/* 843 */       return false;
/*     */     }
/* 845 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 846 */       return false;
/*     */     }
/* 848 */     if (this.textAlignment != that.textAlignment) {
/* 849 */       return false;
/*     */     }
/* 851 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 852 */       return false;
/*     */     }
/* 854 */     if (this.maximumLinesToDisplay != that.maximumLinesToDisplay) {
/* 855 */       return false;
/*     */     }
/* 857 */     if (this.expandToFitSpace != that.expandToFitSpace) {
/* 858 */       return false;
/*     */     }
/* 860 */     if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText)) {
/* 861 */       return false;
/*     */     }
/* 863 */     if (!ObjectUtilities.equal(this.urlText, that.urlText)) {
/* 864 */       return false;
/*     */     }
/* 866 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 875 */     int result = super.hashCode();
/* 876 */     result = 29 * result + (this.text != null ? this.text.hashCode() : 0);
/* 877 */     result = 29 * result + (this.font != null ? this.font.hashCode() : 0);
/* 878 */     result = 29 * result + (this.paint != null ? this.paint.hashCode() : 0);
/* 879 */     result = 29 * result + (this.backgroundPaint != null ? this.backgroundPaint.hashCode() : 0);
/*     */     
/* 881 */     return result;
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
/* 892 */     return super.clone();
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
/* 903 */     stream.defaultWriteObject();
/* 904 */     SerialUtilities.writePaint(this.paint, stream);
/* 905 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
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
/* 918 */     stream.defaultReadObject();
/* 919 */     this.paint = SerialUtilities.readPaint(stream);
/* 920 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/TextTitle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */