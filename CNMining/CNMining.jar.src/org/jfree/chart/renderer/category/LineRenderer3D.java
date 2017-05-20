/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
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
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Marker;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.ValueMarker;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ public class LineRenderer3D
/*     */   extends LineAndShapeRenderer
/*     */   implements Effect3D, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5467931468380928736L;
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/* 107 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
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
/*     */   public LineRenderer3D()
/*     */   {
/* 122 */     super(true, false);
/* 123 */     this.xOffset = 12.0D;
/* 124 */     this.yOffset = 8.0D;
/* 125 */     this.wallPaint = DEFAULT_WALL_PAINT;
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
/* 137 */     return this.xOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYOffset()
/*     */   {
/* 149 */     return this.yOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setXOffset(double xOffset)
/*     */   {
/* 161 */     this.xOffset = xOffset;
/* 162 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setYOffset(double yOffset)
/*     */   {
/* 174 */     this.yOffset = yOffset;
/* 175 */     fireChangeEvent();
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
/* 187 */     return this.wallPaint;
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
/* 200 */     if (paint == null) {
/* 201 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 203 */     this.wallPaint = paint;
/* 204 */     fireChangeEvent();
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
/*     */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea)
/*     */   {
/* 217 */     float x0 = (float)dataArea.getX();
/* 218 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 219 */     float x3 = (float)dataArea.getMaxX();
/* 220 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/*     */     
/* 222 */     float y0 = (float)dataArea.getMaxY();
/* 223 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 224 */     float y3 = (float)dataArea.getMinY();
/* 225 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/*     */     
/* 227 */     GeneralPath clip = new GeneralPath();
/* 228 */     clip.moveTo(x0, y0);
/* 229 */     clip.lineTo(x0, y2);
/* 230 */     clip.lineTo(x1, y3);
/* 231 */     clip.lineTo(x3, y3);
/* 232 */     clip.lineTo(x3, y1);
/* 233 */     clip.lineTo(x2, y0);
/* 234 */     clip.closePath();
/*     */     
/* 236 */     Composite originalComposite = g2.getComposite();
/* 237 */     g2.setComposite(AlphaComposite.getInstance(3, plot.getBackgroundAlpha()));
/*     */     
/*     */ 
/*     */ 
/* 241 */     Paint backgroundPaint = plot.getBackgroundPaint();
/* 242 */     if (backgroundPaint != null) {
/* 243 */       g2.setPaint(backgroundPaint);
/* 244 */       g2.fill(clip);
/*     */     }
/*     */     
/* 247 */     GeneralPath leftWall = new GeneralPath();
/* 248 */     leftWall.moveTo(x0, y0);
/* 249 */     leftWall.lineTo(x0, y2);
/* 250 */     leftWall.lineTo(x1, y3);
/* 251 */     leftWall.lineTo(x1, y1);
/* 252 */     leftWall.closePath();
/* 253 */     g2.setPaint(getWallPaint());
/* 254 */     g2.fill(leftWall);
/*     */     
/* 256 */     GeneralPath bottomWall = new GeneralPath();
/* 257 */     bottomWall.moveTo(x0, y0);
/* 258 */     bottomWall.lineTo(x1, y1);
/* 259 */     bottomWall.lineTo(x3, y1);
/* 260 */     bottomWall.lineTo(x2, y0);
/* 261 */     bottomWall.closePath();
/* 262 */     g2.setPaint(getWallPaint());
/* 263 */     g2.fill(bottomWall);
/*     */     
/*     */ 
/* 266 */     g2.setPaint(Color.lightGray);
/* 267 */     Line2D corner = new Line2D.Double(x0, y0, x1, y1);
/* 268 */     g2.draw(corner);
/* 269 */     corner.setLine(x1, y1, x1, y3);
/* 270 */     g2.draw(corner);
/* 271 */     corner.setLine(x1, y1, x3, y1);
/* 272 */     g2.draw(corner);
/*     */     
/*     */ 
/* 275 */     Image backgroundImage = plot.getBackgroundImage();
/* 276 */     if (backgroundImage != null) {
/* 277 */       Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX() + getXOffset(), dataArea.getY(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */       
/*     */ 
/*     */ 
/* 281 */       plot.drawBackgroundImage(g2, adjusted);
/*     */     }
/*     */     
/* 284 */     g2.setComposite(originalComposite);
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
/* 298 */     float x0 = (float)dataArea.getX();
/* 299 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 300 */     float x3 = (float)dataArea.getMaxX();
/* 301 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/*     */     
/* 303 */     float y0 = (float)dataArea.getMaxY();
/* 304 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 305 */     float y3 = (float)dataArea.getMinY();
/* 306 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/*     */     
/* 308 */     GeneralPath clip = new GeneralPath();
/* 309 */     clip.moveTo(x0, y0);
/* 310 */     clip.lineTo(x0, y2);
/* 311 */     clip.lineTo(x1, y3);
/* 312 */     clip.lineTo(x3, y3);
/* 313 */     clip.lineTo(x3, y1);
/* 314 */     clip.lineTo(x2, y0);
/* 315 */     clip.closePath();
/*     */     
/*     */ 
/* 318 */     Stroke outlineStroke = plot.getOutlineStroke();
/* 319 */     Paint outlinePaint = plot.getOutlinePaint();
/* 320 */     if ((outlineStroke != null) && (outlinePaint != null)) {
/* 321 */       g2.setStroke(outlineStroke);
/* 322 */       g2.setPaint(outlinePaint);
/* 323 */       g2.draw(clip);
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
/* 343 */     Line2D line1 = null;
/* 344 */     Line2D line2 = null;
/* 345 */     PlotOrientation orientation = plot.getOrientation();
/* 346 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 347 */       double y0 = value;
/* 348 */       double y1 = value - getYOffset();
/* 349 */       double x0 = dataArea.getMinX();
/* 350 */       double x1 = x0 + getXOffset();
/* 351 */       double x2 = dataArea.getMaxX();
/* 352 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 353 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     }
/* 355 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 356 */       double x0 = value;
/* 357 */       double x1 = value + getXOffset();
/* 358 */       double y0 = dataArea.getMaxY();
/* 359 */       double y1 = y0 - getYOffset();
/* 360 */       double y2 = dataArea.getMinY();
/* 361 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 362 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     }
/* 364 */     g2.setPaint(plot.getDomainGridlinePaint());
/* 365 */     g2.setStroke(plot.getDomainGridlineStroke());
/* 366 */     g2.draw(line1);
/* 367 */     g2.draw(line2);
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
/*     */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value)
/*     */   {
/* 388 */     Range range = axis.getRange();
/*     */     
/* 390 */     if (!range.contains(value)) {
/* 391 */       return;
/*     */     }
/*     */     
/* 394 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 399 */     Line2D line1 = null;
/* 400 */     Line2D line2 = null;
/* 401 */     PlotOrientation orientation = plot.getOrientation();
/* 402 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 403 */       double x0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 405 */       double x1 = x0 + getXOffset();
/* 406 */       double y0 = dataArea.getMaxY();
/* 407 */       double y1 = y0 - getYOffset();
/* 408 */       double y2 = dataArea.getMinY();
/* 409 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 410 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     }
/* 412 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 413 */       double y0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */       
/* 415 */       double y1 = y0 - getYOffset();
/* 416 */       double x0 = dataArea.getMinX();
/* 417 */       double x1 = x0 + getXOffset();
/* 418 */       double x2 = dataArea.getMaxX();
/* 419 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 420 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     }
/* 422 */     g2.setPaint(plot.getRangeGridlinePaint());
/* 423 */     g2.setStroke(plot.getRangeGridlineStroke());
/* 424 */     g2.draw(line1);
/* 425 */     g2.draw(line2);
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
/*     */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea)
/*     */   {
/* 444 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 449 */     if ((marker instanceof ValueMarker)) {
/* 450 */       ValueMarker vm = (ValueMarker)marker;
/* 451 */       double value = vm.getValue();
/* 452 */       Range range = axis.getRange();
/* 453 */       if (!range.contains(value)) {
/* 454 */         return;
/*     */       }
/*     */       
/* 457 */       GeneralPath path = null;
/* 458 */       PlotOrientation orientation = plot.getOrientation();
/* 459 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 460 */         float x = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */         
/* 462 */         float y = (float)adjusted.getMaxY();
/* 463 */         path = new GeneralPath();
/* 464 */         path.moveTo(x, y);
/* 465 */         path.lineTo((float)(x + getXOffset()), y - (float)getYOffset());
/*     */         
/* 467 */         path.lineTo((float)(x + getXOffset()), (float)(adjusted.getMinY() - getYOffset()));
/*     */         
/* 469 */         path.lineTo(x, (float)adjusted.getMinY());
/* 470 */         path.closePath();
/*     */       }
/* 472 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 473 */         float y = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */         
/* 475 */         float x = (float)dataArea.getX();
/* 476 */         path = new GeneralPath();
/* 477 */         path.moveTo(x, y);
/* 478 */         path.lineTo(x + (float)this.xOffset, y - (float)this.yOffset);
/* 479 */         path.lineTo((float)(adjusted.getMaxX() + this.xOffset), y - (float)this.yOffset);
/*     */         
/* 481 */         path.lineTo((float)adjusted.getMaxX(), y);
/* 482 */         path.closePath();
/*     */       }
/* 484 */       g2.setPaint(marker.getPaint());
/* 485 */       g2.fill(path);
/* 486 */       g2.setPaint(marker.getOutlinePaint());
/* 487 */       g2.draw(path);
/*     */     }
/*     */     else {
/* 490 */       super.drawRangeMarker(g2, plot, axis, marker, adjusted);
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 520 */     if (!getItemVisible(row, column)) {
/* 521 */       return;
/*     */     }
/*     */     
/*     */ 
/* 525 */     Number v = dataset.getValue(row, column);
/* 526 */     if (v == null) {
/* 527 */       return;
/*     */     }
/*     */     
/* 530 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 535 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 538 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), adjusted, plot.getDomainAxisEdge());
/*     */     
/* 540 */     double value = v.doubleValue();
/* 541 */     double y1 = rangeAxis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 544 */     Shape shape = getItemShape(row, column);
/* 545 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 546 */       shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/*     */     }
/* 548 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 549 */       shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */     }
/*     */     
/* 552 */     if ((getItemLineVisible(row, column)) && 
/* 553 */       (column != 0))
/*     */     {
/* 555 */       Number previousValue = dataset.getValue(row, column - 1);
/* 556 */       if (previousValue != null)
/*     */       {
/*     */ 
/* 559 */         double previous = previousValue.doubleValue();
/* 560 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), adjusted, plot.getDomainAxisEdge());
/*     */         
/*     */ 
/* 563 */         double y0 = rangeAxis.valueToJava2D(previous, adjusted, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/* 566 */         double x2 = x0 + getXOffset();
/* 567 */         double y2 = y0 - getYOffset();
/* 568 */         double x3 = x1 + getXOffset();
/* 569 */         double y3 = y1 - getYOffset();
/*     */         
/* 571 */         GeneralPath clip = new GeneralPath();
/*     */         
/* 573 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 574 */           clip.moveTo((float)y0, (float)x0);
/* 575 */           clip.lineTo((float)y1, (float)x1);
/* 576 */           clip.lineTo((float)y3, (float)x3);
/* 577 */           clip.lineTo((float)y2, (float)x2);
/* 578 */           clip.lineTo((float)y0, (float)x0);
/* 579 */           clip.closePath();
/*     */         }
/* 581 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 582 */           clip.moveTo((float)x0, (float)y0);
/* 583 */           clip.lineTo((float)x1, (float)y1);
/* 584 */           clip.lineTo((float)x3, (float)y3);
/* 585 */           clip.lineTo((float)x2, (float)y2);
/* 586 */           clip.lineTo((float)x0, (float)y0);
/* 587 */           clip.closePath();
/*     */         }
/*     */         
/* 590 */         g2.setPaint(getItemPaint(row, column));
/* 591 */         g2.fill(clip);
/* 592 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 593 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 594 */         g2.draw(clip);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 600 */     if (isItemLabelVisible(row, column)) {
/* 601 */       drawItemLabel(g2, orientation, dataset, row, column, x1, y1, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 606 */     EntityCollection entities = state.getEntityCollection();
/* 607 */     if (entities != null) {
/* 608 */       addItemEntity(entities, dataset, row, column, shape);
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
/* 621 */     if (obj == this) {
/* 622 */       return true;
/*     */     }
/* 624 */     if (!(obj instanceof LineRenderer3D)) {
/* 625 */       return false;
/*     */     }
/* 627 */     LineRenderer3D that = (LineRenderer3D)obj;
/* 628 */     if (this.xOffset != that.xOffset) {
/* 629 */       return false;
/*     */     }
/* 631 */     if (this.yOffset != that.yOffset) {
/* 632 */       return false;
/*     */     }
/* 634 */     if (!PaintUtilities.equal(this.wallPaint, that.wallPaint)) {
/* 635 */       return false;
/*     */     }
/* 637 */     return super.equals(obj);
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
/* 648 */     stream.defaultWriteObject();
/* 649 */     SerialUtilities.writePaint(this.wallPaint, stream);
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
/* 663 */     this.wallPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/LineRenderer3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */