/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.util.BooleanUtilities;
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
/*     */ public class StackedBarRenderer3D
/*     */   extends BarRenderer3D
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5832945916493247123L;
/*     */   private boolean renderAsPercentages;
/*     */   
/*     */   public StackedBarRenderer3D()
/*     */   {
/* 144 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedBarRenderer3D(double xOffset, double yOffset)
/*     */   {
/* 154 */     super(xOffset, yOffset);
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
/*     */   public StackedBarRenderer3D(boolean renderAsPercentages)
/*     */   {
/* 167 */     this.renderAsPercentages = renderAsPercentages;
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
/*     */   public StackedBarRenderer3D(double xOffset, double yOffset, boolean renderAsPercentages)
/*     */   {
/* 182 */     super(xOffset, yOffset);
/* 183 */     this.renderAsPercentages = renderAsPercentages;
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
/*     */   public boolean getRenderAsPercentages()
/*     */   {
/* 196 */     return this.renderAsPercentages;
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
/*     */   public void setRenderAsPercentages(boolean asPercentages)
/*     */   {
/* 209 */     this.renderAsPercentages = asPercentages;
/* 210 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findRangeBounds(CategoryDataset dataset)
/*     */   {
/* 222 */     if (dataset == null) {
/* 223 */       return null;
/*     */     }
/* 225 */     if (this.renderAsPercentages) {
/* 226 */       return new Range(0.0D, 1.0D);
/*     */     }
/*     */     
/* 229 */     return DatasetUtilities.findStackedRangeBounds(dataset);
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
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*     */   {
/* 247 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 248 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 249 */     if (data != null) {
/* 250 */       PlotOrientation orientation = plot.getOrientation();
/* 251 */       double space = 0.0D;
/* 252 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 253 */         space = dataArea.getHeight();
/*     */       }
/* 255 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 256 */         space = dataArea.getWidth();
/*     */       }
/* 258 */       double maxWidth = space * getMaximumBarWidth();
/* 259 */       int columns = data.getColumnCount();
/* 260 */       double categoryMargin = 0.0D;
/* 261 */       if (columns > 1) {
/* 262 */         categoryMargin = domainAxis.getCategoryMargin();
/*     */       }
/*     */       
/* 265 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin);
/*     */       
/*     */ 
/* 268 */       if (columns > 0) {
/* 269 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       }
/*     */       else {
/* 272 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected static List createStackedValueList(CategoryDataset dataset, Comparable category, double base, boolean asPercentages)
/*     */   {
/* 297 */     int[] rows = new int[dataset.getRowCount()];
/* 298 */     for (int i = 0; i < rows.length; i++) {
/* 299 */       rows[i] = i;
/*     */     }
/* 301 */     return createStackedValueList(dataset, category, rows, base, asPercentages);
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
/*     */   protected static List createStackedValueList(CategoryDataset dataset, Comparable category, int[] includedRows, double base, boolean asPercentages)
/*     */   {
/* 324 */     List result = new ArrayList();
/* 325 */     double posBase = base;
/* 326 */     double negBase = base;
/* 327 */     double total = 0.0D;
/* 328 */     if (asPercentages) {
/* 329 */       total = DataUtilities.calculateColumnTotal(dataset, dataset.getColumnIndex(category), includedRows);
/*     */     }
/*     */     
/*     */ 
/* 333 */     int baseIndex = -1;
/* 334 */     int rowCount = includedRows.length;
/* 335 */     for (int i = 0; i < rowCount; i++) {
/* 336 */       int r = includedRows[i];
/* 337 */       Number n = dataset.getValue(dataset.getRowKey(r), category);
/* 338 */       if (n != null)
/*     */       {
/*     */ 
/* 341 */         double v = n.doubleValue();
/* 342 */         if (asPercentages) {
/* 343 */           v /= total;
/*     */         }
/* 345 */         if (v >= 0.0D) {
/* 346 */           if (baseIndex < 0) {
/* 347 */             result.add(new Object[] { null, new Double(base) });
/* 348 */             baseIndex = 0;
/*     */           }
/* 350 */           posBase += v;
/* 351 */           result.add(new Object[] { new Integer(r), new Double(posBase) });
/*     */         }
/* 353 */         else if (v < 0.0D) {
/* 354 */           if (baseIndex < 0) {
/* 355 */             result.add(new Object[] { null, new Double(base) });
/* 356 */             baseIndex = 0;
/*     */           }
/* 358 */           negBase += v;
/* 359 */           result.add(0, new Object[] { new Integer(-r - 1), new Double(negBase) });
/*     */           
/* 361 */           baseIndex++;
/*     */         }
/*     */       } }
/* 364 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 397 */     if (row < dataset.getRowCount() - 1) {
/* 398 */       return;
/*     */     }
/* 400 */     Comparable category = dataset.getColumnKey(column);
/*     */     
/* 402 */     List values = createStackedValueList(dataset, dataset.getColumnKey(column), state.getVisibleSeriesArray(), getBase(), this.renderAsPercentages);
/*     */     
/*     */ 
/*     */ 
/* 406 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 412 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 415 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 416 */       drawStackHorizontal(values, category, g2, state, adjusted, plot, domainAxis, rangeAxis, dataset);
/*     */     }
/*     */     else
/*     */     {
/* 420 */       drawStackVertical(values, category, g2, state, adjusted, plot, domainAxis, rangeAxis, dataset);
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
/*     */   protected void drawStackHorizontal(List values, Comparable category, Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset)
/*     */   {
/* 447 */     int column = dataset.getColumnIndex(category);
/* 448 */     double barX0 = domainAxis.getCategoryMiddle(column, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     
/*     */ 
/* 451 */     double barW = state.getBarWidth();
/*     */     
/*     */ 
/*     */ 
/* 455 */     List itemLabelList = new ArrayList();
/*     */     
/*     */ 
/* 458 */     boolean inverted = rangeAxis.isInverted();
/* 459 */     int blockCount = values.size() - 1;
/* 460 */     for (int k = 0; k < blockCount; k++) {
/* 461 */       int index = inverted ? blockCount - k - 1 : k;
/* 462 */       Object[] prev = (Object[])values.get(index);
/* 463 */       Object[] curr = (Object[])values.get(index + 1);
/* 464 */       int series = 0;
/* 465 */       if (curr[0] == null) {
/* 466 */         series = -((Integer)prev[0]).intValue() - 1;
/*     */       }
/*     */       else {
/* 469 */         series = ((Integer)curr[0]).intValue();
/* 470 */         if (series < 0) {
/* 471 */           series = -((Integer)prev[0]).intValue() - 1;
/*     */         }
/*     */       }
/* 474 */       double v0 = ((Double)prev[1]).doubleValue();
/* 475 */       double vv0 = rangeAxis.valueToJava2D(v0, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/* 478 */       double v1 = ((Double)curr[1]).doubleValue();
/* 479 */       double vv1 = rangeAxis.valueToJava2D(v1, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/* 482 */       Shape[] faces = createHorizontalBlock(barX0, barW, vv0, vv1, inverted);
/*     */       
/* 484 */       Paint fillPaint = getItemPaint(series, column);
/* 485 */       Paint fillPaintDark = fillPaint;
/* 486 */       if ((fillPaintDark instanceof Color)) {
/* 487 */         fillPaintDark = ((Color)fillPaint).darker();
/*     */       }
/* 489 */       boolean drawOutlines = isDrawBarOutline();
/* 490 */       Paint outlinePaint = fillPaint;
/* 491 */       if (drawOutlines) {
/* 492 */         outlinePaint = getItemOutlinePaint(series, column);
/* 493 */         g2.setStroke(getItemOutlineStroke(series, column));
/*     */       }
/* 495 */       for (int f = 0; f < 6; f++) {
/* 496 */         if (f == 5) {
/* 497 */           g2.setPaint(fillPaint);
/*     */         }
/*     */         else {
/* 500 */           g2.setPaint(fillPaintDark);
/*     */         }
/* 502 */         g2.fill(faces[f]);
/* 503 */         if (drawOutlines) {
/* 504 */           g2.setPaint(outlinePaint);
/* 505 */           g2.draw(faces[f]);
/*     */         }
/*     */       }
/*     */       
/* 509 */       itemLabelList.add(new Object[] { new Integer(series), faces[5].getBounds2D(), BooleanUtilities.valueOf(v0 < getBase() ? 1 : false) });
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 514 */       EntityCollection entities = state.getEntityCollection();
/* 515 */       if (entities != null) {
/* 516 */         addItemEntity(entities, dataset, series, column, faces[5]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 521 */     for (int i = 0; i < itemLabelList.size(); i++) {
/* 522 */       Object[] record = (Object[])itemLabelList.get(i);
/* 523 */       int series = ((Integer)record[0]).intValue();
/* 524 */       Rectangle2D bar = (Rectangle2D)record[1];
/* 525 */       boolean neg = ((Boolean)record[2]).booleanValue();
/* 526 */       CategoryItemLabelGenerator generator = getItemLabelGenerator(series, column);
/*     */       
/* 528 */       if ((generator != null) && (isItemLabelVisible(series, column))) {
/* 529 */         drawItemLabel(g2, dataset, series, column, plot, generator, bar, neg);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Shape[] createHorizontalBlock(double x0, double width, double y0, double y1, boolean inverted)
/*     */   {
/* 551 */     Shape[] result = new Shape[6];
/* 552 */     Point2D p00 = new Point2D.Double(y0, x0);
/* 553 */     Point2D p01 = new Point2D.Double(y0, x0 + width);
/* 554 */     Point2D p02 = new Point2D.Double(p01.getX() + getXOffset(), p01.getY() - getYOffset());
/*     */     
/* 556 */     Point2D p03 = new Point2D.Double(p00.getX() + getXOffset(), p00.getY() - getYOffset());
/*     */     
/*     */ 
/* 559 */     Point2D p0 = new Point2D.Double(y1, x0);
/* 560 */     Point2D p1 = new Point2D.Double(y1, x0 + width);
/* 561 */     Point2D p2 = new Point2D.Double(p1.getX() + getXOffset(), p1.getY() - getYOffset());
/*     */     
/* 563 */     Point2D p3 = new Point2D.Double(p0.getX() + getXOffset(), p0.getY() - getYOffset());
/*     */     
/*     */ 
/* 566 */     GeneralPath bottom = new GeneralPath();
/* 567 */     bottom.moveTo((float)p1.getX(), (float)p1.getY());
/* 568 */     bottom.lineTo((float)p01.getX(), (float)p01.getY());
/* 569 */     bottom.lineTo((float)p02.getX(), (float)p02.getY());
/* 570 */     bottom.lineTo((float)p2.getX(), (float)p2.getY());
/* 571 */     bottom.closePath();
/*     */     
/* 573 */     GeneralPath top = new GeneralPath();
/* 574 */     top.moveTo((float)p0.getX(), (float)p0.getY());
/* 575 */     top.lineTo((float)p00.getX(), (float)p00.getY());
/* 576 */     top.lineTo((float)p03.getX(), (float)p03.getY());
/* 577 */     top.lineTo((float)p3.getX(), (float)p3.getY());
/* 578 */     top.closePath();
/*     */     
/* 580 */     GeneralPath back = new GeneralPath();
/* 581 */     back.moveTo((float)p2.getX(), (float)p2.getY());
/* 582 */     back.lineTo((float)p02.getX(), (float)p02.getY());
/* 583 */     back.lineTo((float)p03.getX(), (float)p03.getY());
/* 584 */     back.lineTo((float)p3.getX(), (float)p3.getY());
/* 585 */     back.closePath();
/*     */     
/* 587 */     GeneralPath front = new GeneralPath();
/* 588 */     front.moveTo((float)p0.getX(), (float)p0.getY());
/* 589 */     front.lineTo((float)p1.getX(), (float)p1.getY());
/* 590 */     front.lineTo((float)p01.getX(), (float)p01.getY());
/* 591 */     front.lineTo((float)p00.getX(), (float)p00.getY());
/* 592 */     front.closePath();
/*     */     
/* 594 */     GeneralPath left = new GeneralPath();
/* 595 */     left.moveTo((float)p0.getX(), (float)p0.getY());
/* 596 */     left.lineTo((float)p1.getX(), (float)p1.getY());
/* 597 */     left.lineTo((float)p2.getX(), (float)p2.getY());
/* 598 */     left.lineTo((float)p3.getX(), (float)p3.getY());
/* 599 */     left.closePath();
/*     */     
/* 601 */     GeneralPath right = new GeneralPath();
/* 602 */     right.moveTo((float)p00.getX(), (float)p00.getY());
/* 603 */     right.lineTo((float)p01.getX(), (float)p01.getY());
/* 604 */     right.lineTo((float)p02.getX(), (float)p02.getY());
/* 605 */     right.lineTo((float)p03.getX(), (float)p03.getY());
/* 606 */     right.closePath();
/* 607 */     result[0] = bottom;
/* 608 */     result[1] = back;
/* 609 */     if (inverted) {
/* 610 */       result[2] = right;
/* 611 */       result[3] = left;
/*     */     }
/*     */     else {
/* 614 */       result[2] = left;
/* 615 */       result[3] = right;
/*     */     }
/* 617 */     result[4] = top;
/* 618 */     result[5] = front;
/* 619 */     return result;
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
/*     */   protected void drawStackVertical(List values, Comparable category, Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset)
/*     */   {
/* 643 */     int column = dataset.getColumnIndex(category);
/* 644 */     double barX0 = domainAxis.getCategoryMiddle(column, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     
/*     */ 
/* 647 */     double barW = state.getBarWidth();
/*     */     
/*     */ 
/*     */ 
/* 651 */     List itemLabelList = new ArrayList();
/*     */     
/*     */ 
/* 654 */     boolean inverted = rangeAxis.isInverted();
/* 655 */     int blockCount = values.size() - 1;
/* 656 */     for (int k = 0; k < blockCount; k++) {
/* 657 */       int index = inverted ? blockCount - k - 1 : k;
/* 658 */       Object[] prev = (Object[])values.get(index);
/* 659 */       Object[] curr = (Object[])values.get(index + 1);
/* 660 */       int series = 0;
/* 661 */       if (curr[0] == null) {
/* 662 */         series = -((Integer)prev[0]).intValue() - 1;
/*     */       }
/*     */       else {
/* 665 */         series = ((Integer)curr[0]).intValue();
/* 666 */         if (series < 0) {
/* 667 */           series = -((Integer)prev[0]).intValue() - 1;
/*     */         }
/*     */       }
/* 670 */       double v0 = ((Double)prev[1]).doubleValue();
/* 671 */       double vv0 = rangeAxis.valueToJava2D(v0, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/* 674 */       double v1 = ((Double)curr[1]).doubleValue();
/* 675 */       double vv1 = rangeAxis.valueToJava2D(v1, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/* 678 */       Shape[] faces = createVerticalBlock(barX0, barW, vv0, vv1, inverted);
/*     */       
/* 680 */       Paint fillPaint = getItemPaint(series, column);
/* 681 */       Paint fillPaintDark = fillPaint;
/* 682 */       if ((fillPaintDark instanceof Color)) {
/* 683 */         fillPaintDark = ((Color)fillPaint).darker();
/*     */       }
/* 685 */       boolean drawOutlines = isDrawBarOutline();
/* 686 */       Paint outlinePaint = fillPaint;
/* 687 */       if (drawOutlines) {
/* 688 */         outlinePaint = getItemOutlinePaint(series, column);
/* 689 */         g2.setStroke(getItemOutlineStroke(series, column));
/*     */       }
/*     */       
/* 692 */       for (int f = 0; f < 6; f++) {
/* 693 */         if (f == 5) {
/* 694 */           g2.setPaint(fillPaint);
/*     */         }
/*     */         else {
/* 697 */           g2.setPaint(fillPaintDark);
/*     */         }
/* 699 */         g2.fill(faces[f]);
/* 700 */         if (drawOutlines) {
/* 701 */           g2.setPaint(outlinePaint);
/* 702 */           g2.draw(faces[f]);
/*     */         }
/*     */       }
/*     */       
/* 706 */       itemLabelList.add(new Object[] { new Integer(series), faces[5].getBounds2D(), BooleanUtilities.valueOf(v0 < getBase() ? 1 : false) });
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 711 */       EntityCollection entities = state.getEntityCollection();
/* 712 */       if (entities != null) {
/* 713 */         addItemEntity(entities, dataset, series, column, faces[5]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 718 */     for (int i = 0; i < itemLabelList.size(); i++) {
/* 719 */       Object[] record = (Object[])itemLabelList.get(i);
/* 720 */       int series = ((Integer)record[0]).intValue();
/* 721 */       Rectangle2D bar = (Rectangle2D)record[1];
/* 722 */       boolean neg = ((Boolean)record[2]).booleanValue();
/* 723 */       CategoryItemLabelGenerator generator = getItemLabelGenerator(series, column);
/*     */       
/* 725 */       if ((generator != null) && (isItemLabelVisible(series, column))) {
/* 726 */         drawItemLabel(g2, dataset, series, column, plot, generator, bar, neg);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Shape[] createVerticalBlock(double x0, double width, double y0, double y1, boolean inverted)
/*     */   {
/* 748 */     Shape[] result = new Shape[6];
/* 749 */     Point2D p00 = new Point2D.Double(x0, y0);
/* 750 */     Point2D p01 = new Point2D.Double(x0 + width, y0);
/* 751 */     Point2D p02 = new Point2D.Double(p01.getX() + getXOffset(), p01.getY() - getYOffset());
/*     */     
/* 753 */     Point2D p03 = new Point2D.Double(p00.getX() + getXOffset(), p00.getY() - getYOffset());
/*     */     
/*     */ 
/*     */ 
/* 757 */     Point2D p0 = new Point2D.Double(x0, y1);
/* 758 */     Point2D p1 = new Point2D.Double(x0 + width, y1);
/* 759 */     Point2D p2 = new Point2D.Double(p1.getX() + getXOffset(), p1.getY() - getYOffset());
/*     */     
/* 761 */     Point2D p3 = new Point2D.Double(p0.getX() + getXOffset(), p0.getY() - getYOffset());
/*     */     
/*     */ 
/* 764 */     GeneralPath right = new GeneralPath();
/* 765 */     right.moveTo((float)p1.getX(), (float)p1.getY());
/* 766 */     right.lineTo((float)p01.getX(), (float)p01.getY());
/* 767 */     right.lineTo((float)p02.getX(), (float)p02.getY());
/* 768 */     right.lineTo((float)p2.getX(), (float)p2.getY());
/* 769 */     right.closePath();
/*     */     
/* 771 */     GeneralPath left = new GeneralPath();
/* 772 */     left.moveTo((float)p0.getX(), (float)p0.getY());
/* 773 */     left.lineTo((float)p00.getX(), (float)p00.getY());
/* 774 */     left.lineTo((float)p03.getX(), (float)p03.getY());
/* 775 */     left.lineTo((float)p3.getX(), (float)p3.getY());
/* 776 */     left.closePath();
/*     */     
/* 778 */     GeneralPath back = new GeneralPath();
/* 779 */     back.moveTo((float)p2.getX(), (float)p2.getY());
/* 780 */     back.lineTo((float)p02.getX(), (float)p02.getY());
/* 781 */     back.lineTo((float)p03.getX(), (float)p03.getY());
/* 782 */     back.lineTo((float)p3.getX(), (float)p3.getY());
/* 783 */     back.closePath();
/*     */     
/* 785 */     GeneralPath front = new GeneralPath();
/* 786 */     front.moveTo((float)p0.getX(), (float)p0.getY());
/* 787 */     front.lineTo((float)p1.getX(), (float)p1.getY());
/* 788 */     front.lineTo((float)p01.getX(), (float)p01.getY());
/* 789 */     front.lineTo((float)p00.getX(), (float)p00.getY());
/* 790 */     front.closePath();
/*     */     
/* 792 */     GeneralPath top = new GeneralPath();
/* 793 */     top.moveTo((float)p0.getX(), (float)p0.getY());
/* 794 */     top.lineTo((float)p1.getX(), (float)p1.getY());
/* 795 */     top.lineTo((float)p2.getX(), (float)p2.getY());
/* 796 */     top.lineTo((float)p3.getX(), (float)p3.getY());
/* 797 */     top.closePath();
/*     */     
/* 799 */     GeneralPath bottom = new GeneralPath();
/* 800 */     bottom.moveTo((float)p00.getX(), (float)p00.getY());
/* 801 */     bottom.lineTo((float)p01.getX(), (float)p01.getY());
/* 802 */     bottom.lineTo((float)p02.getX(), (float)p02.getY());
/* 803 */     bottom.lineTo((float)p03.getX(), (float)p03.getY());
/* 804 */     bottom.closePath();
/*     */     
/* 806 */     result[0] = bottom;
/* 807 */     result[1] = back;
/* 808 */     result[2] = left;
/* 809 */     result[3] = right;
/* 810 */     result[4] = top;
/* 811 */     result[5] = front;
/* 812 */     if (inverted) {
/* 813 */       result[0] = top;
/* 814 */       result[4] = bottom;
/*     */     }
/* 816 */     return result;
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
/* 827 */     if (obj == this) {
/* 828 */       return true;
/*     */     }
/* 830 */     if (!(obj instanceof StackedBarRenderer3D)) {
/* 831 */       return false;
/*     */     }
/* 833 */     if (!super.equals(obj)) {
/* 834 */       return false;
/*     */     }
/* 836 */     StackedBarRenderer3D that = (StackedBarRenderer3D)obj;
/* 837 */     if (this.renderAsPercentages != that.getRenderAsPercentages()) {
/* 838 */       return false;
/*     */     }
/* 840 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StackedBarRenderer3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */