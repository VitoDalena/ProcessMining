/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.axis.AxisSpace;
/*     */ import org.jfree.chart.axis.AxisState;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.PlotChangeListener;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinedDomainCategoryPlot
/*     */   extends CategoryPlot
/*     */   implements PlotChangeListener
/*     */ {
/*     */   private static final long serialVersionUID = 8207194522653701572L;
/*     */   private List subplots;
/*     */   private double gap;
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */   public CombinedDomainCategoryPlot()
/*     */   {
/* 110 */     this(new CategoryAxis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedDomainCategoryPlot(CategoryAxis domainAxis)
/*     */   {
/* 120 */     super(null, domainAxis, null, null);
/* 121 */     this.subplots = new ArrayList();
/* 122 */     this.gap = 5.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGap()
/*     */   {
/* 131 */     return this.gap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGap(double gap)
/*     */   {
/* 141 */     this.gap = gap;
/* 142 */     fireChangeEvent();
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
/*     */   public void add(CategoryPlot subplot)
/*     */   {
/* 155 */     add(subplot, 1);
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
/*     */   public void add(CategoryPlot subplot, int weight)
/*     */   {
/* 169 */     if (subplot == null) {
/* 170 */       throw new IllegalArgumentException("Null 'subplot' argument.");
/*     */     }
/* 172 */     if (weight < 1) {
/* 173 */       throw new IllegalArgumentException("Require weight >= 1.");
/*     */     }
/* 175 */     subplot.setParent(this);
/* 176 */     subplot.setWeight(weight);
/* 177 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 178 */     subplot.setDomainAxis(null);
/* 179 */     subplot.setOrientation(getOrientation());
/* 180 */     subplot.addChangeListener(this);
/* 181 */     this.subplots.add(subplot);
/* 182 */     CategoryAxis axis = getDomainAxis();
/* 183 */     if (axis != null) {
/* 184 */       axis.configure();
/*     */     }
/* 186 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(CategoryPlot subplot)
/*     */   {
/* 198 */     if (subplot == null) {
/* 199 */       throw new IllegalArgumentException("Null 'subplot' argument.");
/*     */     }
/* 201 */     int position = -1;
/* 202 */     int size = this.subplots.size();
/* 203 */     int i = 0;
/* 204 */     while ((position == -1) && (i < size)) {
/* 205 */       if (this.subplots.get(i) == subplot) {
/* 206 */         position = i;
/*     */       }
/* 208 */       i++;
/*     */     }
/* 210 */     if (position != -1) {
/* 211 */       this.subplots.remove(position);
/* 212 */       subplot.setParent(null);
/* 213 */       subplot.removeChangeListener(this);
/* 214 */       CategoryAxis domain = getDomainAxis();
/* 215 */       if (domain != null) {
/* 216 */         domain.configure();
/*     */       }
/* 218 */       fireChangeEvent();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getSubplots()
/*     */   {
/* 229 */     if (this.subplots != null) {
/* 230 */       return Collections.unmodifiableList(this.subplots);
/*     */     }
/*     */     
/* 233 */     return Collections.EMPTY_LIST;
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
/*     */   public CategoryPlot findSubplot(PlotRenderingInfo info, Point2D source)
/*     */   {
/* 247 */     if (info == null) {
/* 248 */       throw new IllegalArgumentException("Null 'info' argument.");
/*     */     }
/* 250 */     if (source == null) {
/* 251 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/* 253 */     CategoryPlot result = null;
/* 254 */     int subplotIndex = info.getSubplotIndex(source);
/* 255 */     if (subplotIndex >= 0) {
/* 256 */       result = (CategoryPlot)this.subplots.get(subplotIndex);
/*     */     }
/* 258 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source)
/*     */   {
/* 270 */     zoomRangeAxes(factor, info, source, false);
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
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor)
/*     */   {
/* 284 */     CategoryPlot subplot = findSubplot(info, source);
/* 285 */     if (subplot != null) {
/* 286 */       subplot.zoomRangeAxes(factor, info, source, useAnchor);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 291 */       Iterator iterator = getSubplots().iterator();
/* 292 */       while (iterator.hasNext()) {
/* 293 */         subplot = (CategoryPlot)iterator.next();
/* 294 */         subplot.zoomRangeAxes(factor, info, source, useAnchor);
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
/*     */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*     */   {
/* 310 */     CategoryPlot subplot = findSubplot(info, source);
/* 311 */     if (subplot != null) {
/* 312 */       subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 317 */       Iterator iterator = getSubplots().iterator();
/* 318 */       while (iterator.hasNext()) {
/* 319 */         subplot = (CategoryPlot)iterator.next();
/* 320 */         subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
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
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea)
/*     */   {
/* 336 */     AxisSpace space = new AxisSpace();
/* 337 */     PlotOrientation orientation = getOrientation();
/*     */     
/*     */ 
/* 340 */     AxisSpace fixed = getFixedDomainAxisSpace();
/* 341 */     if (fixed != null) {
/* 342 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 343 */         space.setLeft(fixed.getLeft());
/* 344 */         space.setRight(fixed.getRight());
/*     */       }
/* 346 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 347 */         space.setTop(fixed.getTop());
/* 348 */         space.setBottom(fixed.getBottom());
/*     */       }
/*     */     }
/*     */     else {
/* 352 */       CategoryAxis categoryAxis = getDomainAxis();
/* 353 */       RectangleEdge categoryEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), orientation);
/*     */       
/* 355 */       if (categoryAxis != null) {
/* 356 */         space = categoryAxis.reserveSpace(g2, this, plotArea, categoryEdge, space);
/*     */ 
/*     */ 
/*     */       }
/* 360 */       else if (getDrawSharedDomainAxis()) {
/* 361 */         space = getDomainAxis().reserveSpace(g2, this, plotArea, categoryEdge, space);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 367 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/*     */     
/*     */ 
/* 370 */     int n = this.subplots.size();
/* 371 */     int totalWeight = 0;
/* 372 */     for (int i = 0; i < n; i++) {
/* 373 */       CategoryPlot sub = (CategoryPlot)this.subplots.get(i);
/* 374 */       totalWeight += sub.getWeight();
/*     */     }
/* 376 */     this.subplotAreas = new Rectangle2D[n];
/* 377 */     double x = adjustedPlotArea.getX();
/* 378 */     double y = adjustedPlotArea.getY();
/* 379 */     double usableSize = 0.0D;
/* 380 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 381 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/*     */     }
/* 383 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 384 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     }
/*     */     
/* 387 */     for (int i = 0; i < n; i++) {
/* 388 */       CategoryPlot plot = (CategoryPlot)this.subplots.get(i);
/*     */       
/*     */ 
/* 391 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 392 */         double w = usableSize * plot.getWeight() / totalWeight;
/* 393 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/*     */         
/* 395 */         x = x + w + this.gap;
/*     */       }
/* 397 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 398 */         double h = usableSize * plot.getWeight() / totalWeight;
/* 399 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/*     */         
/* 401 */         y = y + h + this.gap;
/*     */       }
/*     */       
/* 404 */       AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, this.subplotAreas[i], null);
/*     */       
/* 406 */       space.ensureAtLeast(subSpace);
/*     */     }
/*     */     
/*     */ 
/* 410 */     return space;
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
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 433 */     if (info != null) {
/* 434 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 438 */     RectangleInsets insets = getInsets();
/* 439 */     area.setRect(area.getX() + insets.getLeft(), area.getY() + insets.getTop(), area.getWidth() - insets.getLeft() - insets.getRight(), area.getHeight() - insets.getTop() - insets.getBottom());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 446 */     setFixedRangeAxisSpaceForSubplots(null);
/* 447 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 448 */     Rectangle2D dataArea = space.shrink(area, null);
/*     */     
/*     */ 
/* 451 */     setFixedRangeAxisSpaceForSubplots(space);
/*     */     
/*     */ 
/* 454 */     CategoryAxis axis = getDomainAxis();
/* 455 */     RectangleEdge domainEdge = getDomainAxisEdge();
/* 456 */     double cursor = RectangleEdge.coordinate(dataArea, domainEdge);
/* 457 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, domainEdge, info);
/*     */     
/* 459 */     if (parentState == null) {
/* 460 */       parentState = new PlotState();
/*     */     }
/* 462 */     parentState.getSharedAxisStates().put(axis, axisState);
/*     */     
/*     */ 
/* 465 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 466 */       CategoryPlot plot = (CategoryPlot)this.subplots.get(i);
/* 467 */       PlotRenderingInfo subplotInfo = null;
/* 468 */       if (info != null) {
/* 469 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 470 */         info.addSubplotInfo(subplotInfo);
/*     */       }
/* 472 */       Point2D subAnchor = null;
/* 473 */       if ((anchor != null) && (this.subplotAreas[i].contains(anchor))) {
/* 474 */         subAnchor = anchor;
/*     */       }
/* 476 */       plot.draw(g2, this.subplotAreas[i], subAnchor, parentState, subplotInfo);
/*     */     }
/*     */     
/*     */ 
/* 480 */     if (info != null) {
/* 481 */       info.setDataArea(dataArea);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space)
/*     */   {
/* 493 */     Iterator iterator = this.subplots.iterator();
/* 494 */     while (iterator.hasNext()) {
/* 495 */       CategoryPlot plot = (CategoryPlot)iterator.next();
/* 496 */       plot.setFixedRangeAxisSpace(space, false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOrientation(PlotOrientation orientation)
/*     */   {
/* 507 */     super.setOrientation(orientation);
/*     */     
/* 509 */     Iterator iterator = this.subplots.iterator();
/* 510 */     while (iterator.hasNext()) {
/* 511 */       CategoryPlot plot = (CategoryPlot)iterator.next();
/* 512 */       plot.setOrientation(orientation);
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
/*     */   public Range getDataRange(ValueAxis axis)
/*     */   {
/* 533 */     return super.getDataRange(axis);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 542 */     LegendItemCollection result = getFixedLegendItems();
/* 543 */     if (result == null) {
/* 544 */       result = new LegendItemCollection();
/* 545 */       if (this.subplots != null) {
/* 546 */         Iterator iterator = this.subplots.iterator();
/* 547 */         while (iterator.hasNext()) {
/* 548 */           CategoryPlot plot = (CategoryPlot)iterator.next();
/* 549 */           LegendItemCollection more = plot.getLegendItems();
/* 550 */           result.addAll(more);
/*     */         }
/*     */       }
/*     */     }
/* 554 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getCategories()
/*     */   {
/* 564 */     List result = new ArrayList();
/* 565 */     if (this.subplots != null) {
/* 566 */       Iterator iterator = this.subplots.iterator();
/* 567 */       while (iterator.hasNext()) {
/* 568 */         CategoryPlot plot = (CategoryPlot)iterator.next();
/* 569 */         List more = plot.getCategories();
/* 570 */         Iterator moreIterator = more.iterator();
/* 571 */         while (moreIterator.hasNext()) {
/* 572 */           Comparable category = (Comparable)moreIterator.next();
/* 573 */           if (!result.contains(category)) {
/* 574 */             result.add(category);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 579 */     return Collections.unmodifiableList(result);
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
/*     */   public List getCategoriesForAxis(CategoryAxis axis)
/*     */   {
/* 594 */     return getCategories();
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
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*     */   {
/* 607 */     Rectangle2D dataArea = info.getDataArea();
/* 608 */     if (dataArea.contains(x, y)) {
/* 609 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 610 */         CategoryPlot subplot = (CategoryPlot)this.subplots.get(i);
/* 611 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 612 */         subplot.handleClick(x, y, subplotInfo);
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
/*     */   public void plotChanged(PlotChangeEvent event)
/*     */   {
/* 625 */     notifyListeners(event);
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
/* 636 */     if (obj == this) {
/* 637 */       return true;
/*     */     }
/* 639 */     if (!(obj instanceof CombinedDomainCategoryPlot)) {
/* 640 */       return false;
/*     */     }
/* 642 */     CombinedDomainCategoryPlot that = (CombinedDomainCategoryPlot)obj;
/* 643 */     if (this.gap != that.gap) {
/* 644 */       return false;
/*     */     }
/* 646 */     if (!ObjectUtilities.equal(this.subplots, that.subplots)) {
/* 647 */       return false;
/*     */     }
/* 649 */     return super.equals(obj);
/*     */   }
/*     */   
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
/* 662 */     CombinedDomainCategoryPlot result = (CombinedDomainCategoryPlot)super.clone();
/*     */     
/* 664 */     result.subplots = ((List)ObjectUtilities.deepClone(this.subplots));
/* 665 */     for (Iterator it = result.subplots.iterator(); it.hasNext();) {
/* 666 */       Plot child = (Plot)it.next();
/* 667 */       child.setParent(result);
/*     */     }
/* 669 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CombinedDomainCategoryPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */