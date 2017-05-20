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
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.PlotChangeListener;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinedDomainXYPlot
/*     */   extends XYPlot
/*     */   implements PlotChangeListener
/*     */ {
/*     */   private static final long serialVersionUID = -7765545541261907383L;
/*     */   private List subplots;
/* 133 */   private double gap = 5.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedDomainXYPlot()
/*     */   {
/* 144 */     this(new NumberAxis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedDomainXYPlot(ValueAxis domainAxis)
/*     */   {
/* 155 */     super(null, domainAxis, null, null);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 162 */     this.subplots = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 172 */     return "Combined_Domain_XYPlot";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOrientation(PlotOrientation orientation)
/*     */   {
/* 183 */     super.setOrientation(orientation);
/* 184 */     Iterator iterator = this.subplots.iterator();
/* 185 */     while (iterator.hasNext()) {
/* 186 */       XYPlot plot = (XYPlot)iterator.next();
/* 187 */       plot.setOrientation(orientation);
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
/*     */   public Range getDataRange(ValueAxis axis)
/*     */   {
/* 206 */     Range result = null;
/* 207 */     if (this.subplots != null) {
/* 208 */       Iterator iterator = this.subplots.iterator();
/* 209 */       while (iterator.hasNext()) {
/* 210 */         XYPlot subplot = (XYPlot)iterator.next();
/* 211 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       }
/*     */     }
/* 214 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGap()
/*     */   {
/* 223 */     return this.gap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGap(double gap)
/*     */   {
/* 233 */     this.gap = gap;
/* 234 */     fireChangeEvent();
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
/*     */   public void add(XYPlot subplot)
/*     */   {
/* 248 */     add(subplot, 1);
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
/*     */   public void add(XYPlot subplot, int weight)
/*     */   {
/* 265 */     if (subplot == null) {
/* 266 */       throw new IllegalArgumentException("Null 'subplot' argument.");
/*     */     }
/* 268 */     if (weight <= 0) {
/* 269 */       throw new IllegalArgumentException("Require weight >= 1.");
/*     */     }
/*     */     
/*     */ 
/* 273 */     subplot.setParent(this);
/* 274 */     subplot.setWeight(weight);
/* 275 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D), false);
/* 276 */     subplot.setDomainAxis(null);
/* 277 */     subplot.addChangeListener(this);
/* 278 */     this.subplots.add(subplot);
/*     */     
/* 280 */     ValueAxis axis = getDomainAxis();
/* 281 */     if (axis != null) {
/* 282 */       axis.configure();
/*     */     }
/* 284 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(XYPlot subplot)
/*     */   {
/* 294 */     if (subplot == null) {
/* 295 */       throw new IllegalArgumentException(" Null 'subplot' argument.");
/*     */     }
/* 297 */     int position = -1;
/* 298 */     int size = this.subplots.size();
/* 299 */     int i = 0;
/* 300 */     while ((position == -1) && (i < size)) {
/* 301 */       if (this.subplots.get(i) == subplot) {
/* 302 */         position = i;
/*     */       }
/* 304 */       i++;
/*     */     }
/* 306 */     if (position != -1) {
/* 307 */       this.subplots.remove(position);
/* 308 */       subplot.setParent(null);
/* 309 */       subplot.removeChangeListener(this);
/* 310 */       ValueAxis domain = getDomainAxis();
/* 311 */       if (domain != null) {
/* 312 */         domain.configure();
/*     */       }
/* 314 */       fireChangeEvent();
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
/* 325 */     if (this.subplots != null) {
/* 326 */       return Collections.unmodifiableList(this.subplots);
/*     */     }
/*     */     
/* 329 */     return Collections.EMPTY_LIST;
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
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea)
/*     */   {
/* 344 */     AxisSpace space = new AxisSpace();
/* 345 */     PlotOrientation orientation = getOrientation();
/*     */     
/*     */ 
/* 348 */     AxisSpace fixed = getFixedDomainAxisSpace();
/* 349 */     if (fixed != null) {
/* 350 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 351 */         space.setLeft(fixed.getLeft());
/* 352 */         space.setRight(fixed.getRight());
/*     */       }
/* 354 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 355 */         space.setTop(fixed.getTop());
/* 356 */         space.setBottom(fixed.getBottom());
/*     */       }
/*     */     }
/*     */     else {
/* 360 */       ValueAxis xAxis = getDomainAxis();
/* 361 */       RectangleEdge xEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), orientation);
/*     */       
/* 363 */       if (xAxis != null) {
/* 364 */         space = xAxis.reserveSpace(g2, this, plotArea, xEdge, space);
/*     */       }
/*     */     }
/*     */     
/* 368 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/*     */     
/*     */ 
/* 371 */     int n = this.subplots.size();
/* 372 */     int totalWeight = 0;
/* 373 */     for (int i = 0; i < n; i++) {
/* 374 */       XYPlot sub = (XYPlot)this.subplots.get(i);
/* 375 */       totalWeight += sub.getWeight();
/*     */     }
/* 377 */     this.subplotAreas = new Rectangle2D[n];
/* 378 */     double x = adjustedPlotArea.getX();
/* 379 */     double y = adjustedPlotArea.getY();
/* 380 */     double usableSize = 0.0D;
/* 381 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 382 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/*     */     }
/* 384 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 385 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     }
/*     */     
/* 388 */     for (int i = 0; i < n; i++) {
/* 389 */       XYPlot plot = (XYPlot)this.subplots.get(i);
/*     */       
/*     */ 
/* 392 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 393 */         double w = usableSize * plot.getWeight() / totalWeight;
/* 394 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/*     */         
/* 396 */         x = x + w + this.gap;
/*     */       }
/* 398 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 399 */         double h = usableSize * plot.getWeight() / totalWeight;
/* 400 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/*     */         
/* 402 */         y = y + h + this.gap;
/*     */       }
/*     */       
/* 405 */       AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, this.subplotAreas[i], null);
/*     */       
/* 407 */       space.ensureAtLeast(subSpace);
/*     */     }
/*     */     
/*     */ 
/* 411 */     return space;
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
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 433 */     if (info != null) {
/* 434 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 438 */     RectangleInsets insets = getInsets();
/* 439 */     insets.trim(area);
/*     */     
/* 441 */     setFixedRangeAxisSpaceForSubplots(null);
/* 442 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 443 */     Rectangle2D dataArea = space.shrink(area, null);
/*     */     
/*     */ 
/* 446 */     setFixedRangeAxisSpaceForSubplots(space);
/*     */     
/*     */ 
/* 449 */     ValueAxis axis = getDomainAxis();
/* 450 */     RectangleEdge edge = getDomainAxisEdge();
/* 451 */     double cursor = RectangleEdge.coordinate(dataArea, edge);
/* 452 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
/* 453 */     if (parentState == null) {
/* 454 */       parentState = new PlotState();
/*     */     }
/* 456 */     parentState.getSharedAxisStates().put(axis, axisState);
/*     */     
/*     */ 
/* 459 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 460 */       XYPlot plot = (XYPlot)this.subplots.get(i);
/* 461 */       PlotRenderingInfo subplotInfo = null;
/* 462 */       if (info != null) {
/* 463 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 464 */         info.addSubplotInfo(subplotInfo);
/*     */       }
/* 466 */       plot.draw(g2, this.subplotAreas[i], anchor, parentState, subplotInfo);
/*     */     }
/*     */     
/*     */ 
/* 470 */     if (info != null) {
/* 471 */       info.setDataArea(dataArea);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 482 */     LegendItemCollection result = getFixedLegendItems();
/* 483 */     if (result == null) {
/* 484 */       result = new LegendItemCollection();
/* 485 */       if (this.subplots != null) {
/* 486 */         Iterator iterator = this.subplots.iterator();
/* 487 */         while (iterator.hasNext()) {
/* 488 */           XYPlot plot = (XYPlot)iterator.next();
/* 489 */           LegendItemCollection more = plot.getLegendItems();
/* 490 */           result.addAll(more);
/*     */         }
/*     */       }
/*     */     }
/* 494 */     return result;
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
/* 506 */     zoomRangeAxes(factor, info, source, false);
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
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source, boolean useAnchor)
/*     */   {
/* 520 */     XYPlot subplot = findSubplot(state, source);
/* 521 */     if (subplot != null) {
/* 522 */       subplot.zoomRangeAxes(factor, state, source, useAnchor);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 527 */       Iterator iterator = getSubplots().iterator();
/* 528 */       while (iterator.hasNext()) {
/* 529 */         subplot = (XYPlot)iterator.next();
/* 530 */         subplot.zoomRangeAxes(factor, state, source, useAnchor);
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
/* 546 */     XYPlot subplot = findSubplot(info, source);
/* 547 */     if (subplot != null) {
/* 548 */       subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 553 */       Iterator iterator = getSubplots().iterator();
/* 554 */       while (iterator.hasNext()) {
/* 555 */         subplot = (XYPlot)iterator.next();
/* 556 */         subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
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
/*     */   public XYPlot findSubplot(PlotRenderingInfo info, Point2D source)
/*     */   {
/* 571 */     if (info == null) {
/* 572 */       throw new IllegalArgumentException("Null 'info' argument.");
/*     */     }
/* 574 */     if (source == null) {
/* 575 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/* 577 */     XYPlot result = null;
/* 578 */     int subplotIndex = info.getSubplotIndex(source);
/* 579 */     if (subplotIndex >= 0) {
/* 580 */       result = (XYPlot)this.subplots.get(subplotIndex);
/*     */     }
/* 582 */     return result;
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
/*     */   public void setRenderer(XYItemRenderer renderer)
/*     */   {
/* 596 */     super.setRenderer(renderer);
/*     */     
/*     */ 
/*     */ 
/* 600 */     Iterator iterator = this.subplots.iterator();
/* 601 */     while (iterator.hasNext()) {
/* 602 */       XYPlot plot = (XYPlot)iterator.next();
/* 603 */       plot.setRenderer(renderer);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFixedRangeAxisSpace(AxisSpace space)
/*     */   {
/* 615 */     super.setFixedRangeAxisSpace(space);
/* 616 */     setFixedRangeAxisSpaceForSubplots(space);
/* 617 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space)
/*     */   {
/* 627 */     Iterator iterator = this.subplots.iterator();
/* 628 */     while (iterator.hasNext()) {
/* 629 */       XYPlot plot = (XYPlot)iterator.next();
/* 630 */       plot.setFixedRangeAxisSpace(space, false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*     */   {
/* 642 */     Rectangle2D dataArea = info.getDataArea();
/* 643 */     if (dataArea.contains(x, y)) {
/* 644 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 645 */         XYPlot subplot = (XYPlot)this.subplots.get(i);
/* 646 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 647 */         subplot.handleClick(x, y, subplotInfo);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void plotChanged(PlotChangeEvent event)
/*     */   {
/* 659 */     notifyListeners(event);
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
/* 670 */     if (obj == this) {
/* 671 */       return true;
/*     */     }
/* 673 */     if (!(obj instanceof CombinedDomainXYPlot)) {
/* 674 */       return false;
/*     */     }
/* 676 */     CombinedDomainXYPlot that = (CombinedDomainXYPlot)obj;
/* 677 */     if (this.gap != that.gap) {
/* 678 */       return false;
/*     */     }
/* 680 */     if (!ObjectUtilities.equal(this.subplots, that.subplots)) {
/* 681 */       return false;
/*     */     }
/* 683 */     return super.equals(obj);
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
/* 696 */     CombinedDomainXYPlot result = (CombinedDomainXYPlot)super.clone();
/* 697 */     result.subplots = ((List)ObjectUtilities.deepClone(this.subplots));
/* 698 */     for (Iterator it = result.subplots.iterator(); it.hasNext();) {
/* 699 */       Plot child = (Plot)it.next();
/* 700 */       child.setParent(result);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 705 */     ValueAxis domainAxis = result.getDomainAxis();
/* 706 */     if (domainAxis != null) {
/* 707 */       domainAxis.configure();
/*     */     }
/*     */     
/* 710 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CombinedDomainXYPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */