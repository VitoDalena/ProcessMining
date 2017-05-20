/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Marker;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.ValueMarker;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarRenderer3D
/*     */   extends BarRenderer
/*     */   implements Effect3D, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7686976503536003636L;
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/* 164 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
/*     */   
/*     */ 
/*     */   private double xOffset;
/*     */   
/*     */ 
/*     */   private double yOffset;
/*     */   
/*     */ 
/*     */   private transient Paint wallPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   public BarRenderer3D()
/*     */   {
/* 179 */     this(12.0D, 8.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BarRenderer3D(double xOffset, double yOffset)
/*     */   {
/* 191 */     this.xOffset = xOffset;
/* 192 */     this.yOffset = yOffset;
/* 193 */     this.wallPaint = DEFAULT_WALL_PAINT;
/*     */     
/* 195 */     ItemLabelPosition p1 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
/*     */     
/* 197 */     setBasePositiveItemLabelPosition(p1);
/* 198 */     ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
/*     */     
/* 200 */     setBaseNegativeItemLabelPosition(p2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXOffset()
/*     */   {
/* 212 */     return this.xOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYOffset()
/*     */   {
/* 221 */     return this.yOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getWallPaint()
/*     */   {
/* 233 */     return this.wallPaint;
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
/*     */   public void setWallPaint(Paint paint)
/*     */   {
/* 246 */     if (paint == null) {
/* 247 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 249 */     this.wallPaint = paint;
/* 250 */     fireChangeEvent();
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
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 273 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/* 276 */     CategoryItemRendererState state = super.initialise(g2, adjusted, plot, rendererIndex, info);
/*     */     
/* 278 */     return state;
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
/*     */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea)
/*     */   {
/* 292 */     float x0 = (float)dataArea.getX();
/* 293 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 294 */     float x3 = (float)dataArea.getMaxX();
/* 295 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/*     */     
/* 297 */     float y0 = (float)dataArea.getMaxY();
/* 298 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 299 */     float y3 = (float)dataArea.getMinY();
/* 300 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/*     */     
/* 302 */     GeneralPath clip = new GeneralPath();
/* 303 */     clip.moveTo(x0, y0);
/* 304 */     clip.lineTo(x0, y2);
/* 305 */     clip.lineTo(x1, y3);
/* 306 */     clip.lineTo(x3, y3);
/* 307 */     clip.lineTo(x3, y1);
/* 308 */     clip.lineTo(x2, y0);
/* 309 */     clip.closePath();
/*     */     
/* 311 */     Composite originalComposite = g2.getComposite();
/* 312 */     g2.setComposite(AlphaComposite.getInstance(3, plot.getBackgroundAlpha()));
/*     */     
/*     */ 
/*     */ 
/* 316 */     Paint backgroundPaint = plot.getBackgroundPaint();
/* 317 */     if (backgroundPaint != null) {
/* 318 */       g2.setPaint(backgroundPaint);
/* 319 */       g2.fill(clip);
/*     */     }
/*     */     
/* 322 */     GeneralPath leftWall = new GeneralPath();
/* 323 */     leftWall.moveTo(x0, y0);
/* 324 */     leftWall.lineTo(x0, y2);
/* 325 */     leftWall.lineTo(x1, y3);
/* 326 */     leftWall.lineTo(x1, y1);
/* 327 */     leftWall.closePath();
/* 328 */     g2.setPaint(getWallPaint());
/* 329 */     g2.fill(leftWall);
/*     */     
/* 331 */     GeneralPath bottomWall = new GeneralPath();
/* 332 */     bottomWall.moveTo(x0, y0);
/* 333 */     bottomWall.lineTo(x1, y1);
/* 334 */     bottomWall.lineTo(x3, y1);
/* 335 */     bottomWall.lineTo(x2, y0);
/* 336 */     bottomWall.closePath();
/* 337 */     g2.setPaint(getWallPaint());
/* 338 */     g2.fill(bottomWall);
/*     */     
/*     */ 
/* 341 */     g2.setPaint(Color.lightGray);
/* 342 */     Line2D corner = new Line2D.Double(x0, y0, x1, y1);
/* 343 */     g2.draw(corner);
/* 344 */     corner.setLine(x1, y1, x1, y3);
/* 345 */     g2.draw(corner);
/* 346 */     corner.setLine(x1, y1, x3, y1);
/* 347 */     g2.draw(corner);
/*     */     
/*     */ 
/* 350 */     Image backgroundImage = plot.getBackgroundImage();
/* 351 */     if (backgroundImage != null) {
/* 352 */       Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX() + getXOffset(), dataArea.getY(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */       
/*     */ 
/*     */ 
/* 356 */       plot.drawBackgroundImage(g2, adjusted);
/*     */     }
/*     */     
/* 359 */     g2.setComposite(originalComposite);
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
/*     */   public void drawOutline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea)
/*     */   {
/* 373 */     float x0 = (float)dataArea.getX();
/* 374 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 375 */     float x3 = (float)dataArea.getMaxX();
/* 376 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/*     */     
/* 378 */     float y0 = (float)dataArea.getMaxY();
/* 379 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 380 */     float y3 = (float)dataArea.getMinY();
/* 381 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/*     */     
/* 383 */     GeneralPath clip = new GeneralPath();
/* 384 */     clip.moveTo(x0, y0);
/* 385 */     clip.lineTo(x0, y2);
/* 386 */     clip.lineTo(x1, y3);
/* 387 */     clip.lineTo(x3, y3);
/* 388 */     clip.lineTo(x3, y1);
/* 389 */     clip.lineTo(x2, y0);
/* 390 */     clip.closePath();
/*     */     
/*     */ 
/* 393 */     Stroke outlineStroke = plot.getOutlineStroke();
/* 394 */     Paint outlinePaint = plot.getOutlinePaint();
/* 395 */     if ((outlineStroke != null) && (outlinePaint != null)) {
/* 396 */       g2.setStroke(outlineStroke);
/* 397 */       g2.setPaint(outlinePaint);
/* 398 */       g2.draw(clip);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawDomainGridline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, double value)
/*     */   {
/* 418 */     Line2D line1 = null;
/* 419 */     Line2D line2 = null;
/* 420 */     PlotOrientation orientation = plot.getOrientation();
/* 421 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 422 */       double y0 = value;
/* 423 */       double y1 = value - getYOffset();
/* 424 */       double x0 = dataArea.getMinX();
/* 425 */       double x1 = x0 + getXOffset();
/* 426 */       double x2 = dataArea.getMaxX();
/* 427 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 428 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     }
/* 430 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 431 */       double x0 = value;
/* 432 */       double x1 = value + getXOffset();
/* 433 */       double y0 = dataArea.getMaxY();
/* 434 */       double y1 = y0 - getYOffset();
/* 435 */       double y2 = dataArea.getMinY();
/* 436 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 437 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     }
/* 439 */     Paint paint = plot.getDomainGridlinePaint();
/* 440 */     Stroke stroke = plot.getDomainGridlineStroke();
/* 441 */     g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 442 */     g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 443 */     g2.draw(line1);
/* 444 */     g2.draw(line2);
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
/*     */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value)
/*     */   {
/* 462 */     Range range = axis.getRange();
/*     */     
/* 464 */     if (!range.contains(value)) {
/* 465 */       return;
/*     */     }
/*     */     
/* 468 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/* 472 */     Line2D line1 = null;
/* 473 */     Line2D line2 = null;
/* 474 */     PlotOrientation orientation = plot.getOrientation();
/* 475 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 476 */       double x0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 478 */       double x1 = x0 + getXOffset();
/* 479 */       double y0 = dataArea.getMaxY();
/* 480 */       double y1 = y0 - getYOffset();
/* 481 */       double y2 = dataArea.getMinY();
/* 482 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 483 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     }
/* 485 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 486 */       double y0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 488 */       double y1 = y0 - getYOffset();
/* 489 */       double x0 = dataArea.getMinX();
/* 490 */       double x1 = x0 + getXOffset();
/* 491 */       double x2 = dataArea.getMaxX();
/* 492 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 493 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     }
/* 495 */     Paint paint = plot.getRangeGridlinePaint();
/* 496 */     Stroke stroke = plot.getRangeGridlineStroke();
/* 497 */     g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 498 */     g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 499 */     g2.draw(line1);
/* 500 */     g2.draw(line2);
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
/*     */   public void drawRangeLine(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value, Paint paint, Stroke stroke)
/*     */   {
/* 523 */     Range range = axis.getRange();
/* 524 */     if (!range.contains(value)) {
/* 525 */       return;
/*     */     }
/*     */     
/* 528 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/* 532 */     Line2D line1 = null;
/* 533 */     Line2D line2 = null;
/* 534 */     PlotOrientation orientation = plot.getOrientation();
/* 535 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 536 */       double x0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 538 */       double x1 = x0 + getXOffset();
/* 539 */       double y0 = dataArea.getMaxY();
/* 540 */       double y1 = y0 - getYOffset();
/* 541 */       double y2 = dataArea.getMinY();
/* 542 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 543 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     }
/* 545 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 546 */       double y0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 548 */       double y1 = y0 - getYOffset();
/* 549 */       double x0 = dataArea.getMinX();
/* 550 */       double x1 = x0 + getXOffset();
/* 551 */       double x2 = dataArea.getMaxX();
/* 552 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 553 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     }
/* 555 */     g2.setPaint(paint);
/* 556 */     g2.setStroke(stroke);
/* 557 */     g2.draw(line1);
/* 558 */     g2.draw(line2);
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
/*     */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea)
/*     */   {
/* 578 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/* 581 */     if ((marker instanceof ValueMarker)) {
/* 582 */       ValueMarker vm = (ValueMarker)marker;
/* 583 */       double value = vm.getValue();
/* 584 */       Range range = axis.getRange();
/* 585 */       if (!range.contains(value)) {
/* 586 */         return;
/*     */       }
/*     */       
/* 589 */       GeneralPath path = null;
/* 590 */       PlotOrientation orientation = plot.getOrientation();
/* 591 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 592 */         float x = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */         
/* 594 */         float y = (float)adjusted.getMaxY();
/* 595 */         path = new GeneralPath();
/* 596 */         path.moveTo(x, y);
/* 597 */         path.lineTo((float)(x + getXOffset()), y - (float)getYOffset());
/*     */         
/* 599 */         path.lineTo((float)(x + getXOffset()), (float)(adjusted.getMinY() - getYOffset()));
/*     */         
/* 601 */         path.lineTo(x, (float)adjusted.getMinY());
/* 602 */         path.closePath();
/*     */       }
/* 604 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 605 */         float y = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */         
/* 607 */         float x = (float)dataArea.getX();
/* 608 */         path = new GeneralPath();
/* 609 */         path.moveTo(x, y);
/* 610 */         path.lineTo(x + (float)this.xOffset, y - (float)this.yOffset);
/* 611 */         path.lineTo((float)(adjusted.getMaxX() + this.xOffset), y - (float)this.yOffset);
/*     */         
/* 613 */         path.lineTo((float)adjusted.getMaxX(), y);
/* 614 */         path.closePath();
/*     */       }
/* 616 */       g2.setPaint(marker.getPaint());
/* 617 */       g2.fill(path);
/* 618 */       g2.setPaint(marker.getOutlinePaint());
/* 619 */       g2.draw(path);
/*     */       
/* 621 */       String label = marker.getLabel();
/* 622 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 623 */       if (label != null) {
/* 624 */         Font labelFont = marker.getLabelFont();
/* 625 */         g2.setFont(labelFont);
/* 626 */         g2.setPaint(marker.getLabelPaint());
/* 627 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, path.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*     */         
/*     */ 
/*     */ 
/* 631 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 638 */       super.drawRangeMarker(g2, plot, axis, marker, adjusted);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 669 */     Number dataValue = dataset.getValue(row, column);
/* 670 */     if (dataValue == null) {
/* 671 */       return;
/*     */     }
/*     */     
/* 674 */     double value = dataValue.doubleValue();
/*     */     
/* 676 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 681 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 683 */     double barW0 = calculateBarW0(plot, orientation, adjusted, domainAxis, state, row, column);
/*     */     
/* 685 */     double[] barL0L1 = calculateBarL0L1(value);
/* 686 */     if (barL0L1 == null) {
/* 687 */       return;
/*     */     }
/*     */     
/* 690 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 691 */     double transL0 = rangeAxis.valueToJava2D(barL0L1[0], adjusted, edge);
/* 692 */     double transL1 = rangeAxis.valueToJava2D(barL0L1[1], adjusted, edge);
/* 693 */     double barL0 = Math.min(transL0, transL1);
/* 694 */     double barLength = Math.abs(transL1 - transL0);
/*     */     
/*     */ 
/* 697 */     Rectangle2D bar = null;
/* 698 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 699 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     }
/*     */     else
/*     */     {
/* 703 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     }
/*     */     
/* 706 */     Paint itemPaint = getItemPaint(row, column);
/* 707 */     g2.setPaint(itemPaint);
/* 708 */     g2.fill(bar);
/*     */     
/* 710 */     double x0 = bar.getMinX();
/* 711 */     double x1 = x0 + getXOffset();
/* 712 */     double x2 = bar.getMaxX();
/* 713 */     double x3 = x2 + getXOffset();
/*     */     
/* 715 */     double y0 = bar.getMinY() - getYOffset();
/* 716 */     double y1 = bar.getMinY();
/* 717 */     double y2 = bar.getMaxY() - getYOffset();
/* 718 */     double y3 = bar.getMaxY();
/*     */     
/* 720 */     GeneralPath bar3dRight = null;
/* 721 */     GeneralPath bar3dTop = null;
/* 722 */     if (barLength > 0.0D) {
/* 723 */       bar3dRight = new GeneralPath();
/* 724 */       bar3dRight.moveTo((float)x2, (float)y3);
/* 725 */       bar3dRight.lineTo((float)x2, (float)y1);
/* 726 */       bar3dRight.lineTo((float)x3, (float)y0);
/* 727 */       bar3dRight.lineTo((float)x3, (float)y2);
/* 728 */       bar3dRight.closePath();
/*     */       
/* 730 */       if ((itemPaint instanceof Color)) {
/* 731 */         g2.setPaint(((Color)itemPaint).darker());
/*     */       }
/* 733 */       g2.fill(bar3dRight);
/*     */     }
/*     */     
/* 736 */     bar3dTop = new GeneralPath();
/* 737 */     bar3dTop.moveTo((float)x0, (float)y1);
/* 738 */     bar3dTop.lineTo((float)x1, (float)y0);
/* 739 */     bar3dTop.lineTo((float)x3, (float)y0);
/* 740 */     bar3dTop.lineTo((float)x2, (float)y1);
/* 741 */     bar3dTop.closePath();
/* 742 */     g2.fill(bar3dTop);
/*     */     
/* 744 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 746 */       g2.setStroke(getItemOutlineStroke(row, column));
/* 747 */       g2.setPaint(getItemOutlinePaint(row, column));
/* 748 */       g2.draw(bar);
/* 749 */       if (bar3dRight != null) {
/* 750 */         g2.draw(bar3dRight);
/*     */       }
/* 752 */       if (bar3dTop != null) {
/* 753 */         g2.draw(bar3dTop);
/*     */       }
/*     */     }
/*     */     
/* 757 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 759 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 760 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 765 */     EntityCollection entities = state.getEntityCollection();
/* 766 */     if (entities != null) {
/* 767 */       GeneralPath barOutline = new GeneralPath();
/* 768 */       barOutline.moveTo((float)x0, (float)y3);
/* 769 */       barOutline.lineTo((float)x0, (float)y1);
/* 770 */       barOutline.lineTo((float)x1, (float)y0);
/* 771 */       barOutline.lineTo((float)x3, (float)y0);
/* 772 */       barOutline.lineTo((float)x3, (float)y2);
/* 773 */       barOutline.lineTo((float)x2, (float)y3);
/* 774 */       barOutline.closePath();
/* 775 */       addItemEntity(entities, dataset, row, column, barOutline);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 788 */     if (obj == this) {
/* 789 */       return true;
/*     */     }
/* 791 */     if (!(obj instanceof BarRenderer3D)) {
/* 792 */       return false;
/*     */     }
/* 794 */     BarRenderer3D that = (BarRenderer3D)obj;
/* 795 */     if (this.xOffset != that.xOffset) {
/* 796 */       return false;
/*     */     }
/* 798 */     if (this.yOffset != that.yOffset) {
/* 799 */       return false;
/*     */     }
/* 801 */     if (!PaintUtilities.equal(this.wallPaint, that.wallPaint)) {
/* 802 */       return false;
/*     */     }
/* 804 */     return super.equals(obj);
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
/* 815 */     stream.defaultWriteObject();
/* 816 */     SerialUtilities.writePaint(this.wallPaint, stream);
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
/* 829 */     stream.defaultReadObject();
/* 830 */     this.wallPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/BarRenderer3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */