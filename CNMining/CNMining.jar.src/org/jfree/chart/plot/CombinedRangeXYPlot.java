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
/*     */ import java.util.ResourceBundle;
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
/*     */ 
/*     */ public class CombinedRangeXYPlot
/*     */   extends XYPlot
/*     */   implements PlotChangeListener
/*     */ {
/*     */   private static final long serialVersionUID = -5177814085082031168L;
/*     */   private List subplots;
/* 135 */   private double gap = 5.0D;
/*     */   
/*     */ 
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */ 
/*     */ 
/*     */   public CombinedRangeXYPlot()
/*     */   {
/* 144 */     this(new NumberAxis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedRangeXYPlot(ValueAxis rangeAxis)
/*     */   {
/* 154 */     super(null, null, rangeAxis, null);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 159 */     this.subplots = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 169 */     return localizationResources.getString("Combined_Range_XYPlot");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGap()
/*     */   {
/* 178 */     return this.gap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGap(double gap)
/*     */   {
/* 187 */     this.gap = gap;
/*     */   }
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
/* 199 */     add(subplot, 1);
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
/* 216 */     if (weight <= 0) {
/* 217 */       String msg = "The 'weight' must be positive.";
/* 218 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */     
/*     */ 
/* 222 */     subplot.setParent(this);
/* 223 */     subplot.setWeight(weight);
/* 224 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 225 */     subplot.setRangeAxis(null);
/* 226 */     subplot.addChangeListener(this);
/* 227 */     this.subplots.add(subplot);
/* 228 */     configureRangeAxes();
/* 229 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(XYPlot subplot)
/*     */   {
/* 239 */     if (subplot == null) {
/* 240 */       throw new IllegalArgumentException(" Null 'subplot' argument.");
/*     */     }
/* 242 */     int position = -1;
/* 243 */     int size = this.subplots.size();
/* 244 */     int i = 0;
/* 245 */     while ((position == -1) && (i < size)) {
/* 246 */       if (this.subplots.get(i) == subplot) {
/* 247 */         position = i;
/*     */       }
/* 249 */       i++;
/*     */     }
/* 251 */     if (position != -1) {
/* 252 */       this.subplots.remove(position);
/* 253 */       subplot.setParent(null);
/* 254 */       subplot.removeChangeListener(this);
/* 255 */       configureRangeAxes();
/* 256 */       fireChangeEvent();
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
/* 267 */     if (this.subplots != null) {
/* 268 */       return Collections.unmodifiableList(this.subplots);
/*     */     }
/*     */     
/* 271 */     return Collections.EMPTY_LIST;
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
/* 286 */     AxisSpace space = new AxisSpace();
/* 287 */     PlotOrientation orientation = getOrientation();
/*     */     
/*     */ 
/* 290 */     AxisSpace fixed = getFixedRangeAxisSpace();
/* 291 */     if (fixed != null) {
/* 292 */       if (orientation == PlotOrientation.VERTICAL) {
/* 293 */         space.setLeft(fixed.getLeft());
/* 294 */         space.setRight(fixed.getRight());
/*     */       }
/* 296 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 297 */         space.setTop(fixed.getTop());
/* 298 */         space.setBottom(fixed.getBottom());
/*     */       }
/*     */     }
/*     */     else {
/* 302 */       ValueAxis valueAxis = getRangeAxis();
/* 303 */       RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), orientation);
/*     */       
/*     */ 
/* 306 */       if (valueAxis != null) {
/* 307 */         space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, space);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 312 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/*     */     
/* 314 */     int n = this.subplots.size();
/* 315 */     int totalWeight = 0;
/* 316 */     for (int i = 0; i < n; i++) {
/* 317 */       XYPlot sub = (XYPlot)this.subplots.get(i);
/* 318 */       totalWeight += sub.getWeight();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 323 */     this.subplotAreas = new Rectangle2D[n];
/* 324 */     double x = adjustedPlotArea.getX();
/* 325 */     double y = adjustedPlotArea.getY();
/* 326 */     double usableSize = 0.0D;
/* 327 */     if (orientation == PlotOrientation.VERTICAL) {
/* 328 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/*     */     }
/* 330 */     else if (orientation == PlotOrientation.HORIZONTAL) {
/* 331 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     }
/*     */     
/* 334 */     for (int i = 0; i < n; i++) {
/* 335 */       XYPlot plot = (XYPlot)this.subplots.get(i);
/*     */       
/*     */ 
/* 338 */       if (orientation == PlotOrientation.VERTICAL) {
/* 339 */         double w = usableSize * plot.getWeight() / totalWeight;
/* 340 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/*     */         
/* 342 */         x = x + w + this.gap;
/*     */       }
/* 344 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 345 */         double h = usableSize * plot.getWeight() / totalWeight;
/* 346 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/*     */         
/* 348 */         y = y + h + this.gap;
/*     */       }
/*     */       
/* 351 */       AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, this.subplotAreas[i], null);
/*     */       
/* 353 */       space.ensureAtLeast(subSpace);
/*     */     }
/*     */     
/*     */ 
/* 357 */     return space;
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
/* 379 */     if (info != null) {
/* 380 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 384 */     RectangleInsets insets = getInsets();
/* 385 */     insets.trim(area);
/*     */     
/* 387 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 388 */     Rectangle2D dataArea = space.shrink(area, null);
/*     */     
/*     */ 
/*     */ 
/* 392 */     setFixedDomainAxisSpaceForSubplots(space);
/*     */     
/*     */ 
/* 395 */     ValueAxis axis = getRangeAxis();
/* 396 */     RectangleEdge edge = getRangeAxisEdge();
/* 397 */     double cursor = RectangleEdge.coordinate(dataArea, edge);
/* 398 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
/*     */     
/* 400 */     if (parentState == null) {
/* 401 */       parentState = new PlotState();
/*     */     }
/* 403 */     parentState.getSharedAxisStates().put(axis, axisState);
/*     */     
/*     */ 
/* 406 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 407 */       XYPlot plot = (XYPlot)this.subplots.get(i);
/* 408 */       PlotRenderingInfo subplotInfo = null;
/* 409 */       if (info != null) {
/* 410 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 411 */         info.addSubplotInfo(subplotInfo);
/*     */       }
/* 413 */       plot.draw(g2, this.subplotAreas[i], anchor, parentState, subplotInfo);
/*     */     }
/*     */     
/*     */ 
/* 417 */     if (info != null) {
/* 418 */       info.setDataArea(dataArea);
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
/* 429 */     LegendItemCollection result = getFixedLegendItems();
/* 430 */     if (result == null) {
/* 431 */       result = new LegendItemCollection();
/*     */       
/* 433 */       if (this.subplots != null) {
/* 434 */         Iterator iterator = this.subplots.iterator();
/* 435 */         while (iterator.hasNext()) {
/* 436 */           XYPlot plot = (XYPlot)iterator.next();
/* 437 */           LegendItemCollection more = plot.getLegendItems();
/* 438 */           result.addAll(more);
/*     */         }
/*     */       }
/*     */     }
/* 442 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source)
/*     */   {
/* 454 */     zoomDomainAxes(factor, info, source, false);
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
/*     */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor)
/*     */   {
/* 468 */     XYPlot subplot = findSubplot(info, source);
/* 469 */     if (subplot != null) {
/* 470 */       subplot.zoomDomainAxes(factor, info, source, useAnchor);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 475 */       Iterator iterator = getSubplots().iterator();
/* 476 */       while (iterator.hasNext()) {
/* 477 */         subplot = (XYPlot)iterator.next();
/* 478 */         subplot.zoomDomainAxes(factor, info, source, useAnchor);
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
/*     */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*     */   {
/* 494 */     XYPlot subplot = findSubplot(info, source);
/* 495 */     if (subplot != null) {
/* 496 */       subplot.zoomDomainAxes(lowerPercent, upperPercent, info, source);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 501 */       Iterator iterator = getSubplots().iterator();
/* 502 */       while (iterator.hasNext()) {
/* 503 */         subplot = (XYPlot)iterator.next();
/* 504 */         subplot.zoomDomainAxes(lowerPercent, upperPercent, info, source);
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
/*     */   public XYPlot findSubplot(PlotRenderingInfo info, Point2D source)
/*     */   {
/* 520 */     if (info == null) {
/* 521 */       throw new IllegalArgumentException("Null 'info' argument.");
/*     */     }
/* 523 */     if (source == null) {
/* 524 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/* 526 */     XYPlot result = null;
/* 527 */     int subplotIndex = info.getSubplotIndex(source);
/* 528 */     if (subplotIndex >= 0) {
/* 529 */       result = (XYPlot)this.subplots.get(subplotIndex);
/*     */     }
/* 531 */     return result;
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
/* 545 */     super.setRenderer(renderer);
/*     */     
/*     */ 
/*     */ 
/* 549 */     Iterator iterator = this.subplots.iterator();
/* 550 */     while (iterator.hasNext()) {
/* 551 */       XYPlot plot = (XYPlot)iterator.next();
/* 552 */       plot.setRenderer(renderer);
/*     */     }
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
/* 564 */     super.setOrientation(orientation);
/*     */     
/* 566 */     Iterator iterator = this.subplots.iterator();
/* 567 */     while (iterator.hasNext()) {
/* 568 */       XYPlot plot = (XYPlot)iterator.next();
/* 569 */       plot.setOrientation(orientation);
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
/* 588 */     Range result = null;
/* 589 */     if (this.subplots != null) {
/* 590 */       Iterator iterator = this.subplots.iterator();
/* 591 */       while (iterator.hasNext()) {
/* 592 */         XYPlot subplot = (XYPlot)iterator.next();
/* 593 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       }
/*     */     }
/* 596 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space)
/*     */   {
/* 606 */     Iterator iterator = this.subplots.iterator();
/* 607 */     while (iterator.hasNext()) {
/* 608 */       XYPlot plot = (XYPlot)iterator.next();
/* 609 */       plot.setFixedDomainAxisSpace(space, false);
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
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*     */   {
/* 622 */     Rectangle2D dataArea = info.getDataArea();
/* 623 */     if (dataArea.contains(x, y)) {
/* 624 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 625 */         XYPlot subplot = (XYPlot)this.subplots.get(i);
/* 626 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 627 */         subplot.handleClick(x, y, subplotInfo);
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
/* 640 */     notifyListeners(event);
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
/* 651 */     if (obj == this) {
/* 652 */       return true;
/*     */     }
/* 654 */     if (!(obj instanceof CombinedRangeXYPlot)) {
/* 655 */       return false;
/*     */     }
/* 657 */     CombinedRangeXYPlot that = (CombinedRangeXYPlot)obj;
/* 658 */     if (this.gap != that.gap) {
/* 659 */       return false;
/*     */     }
/* 661 */     if (!ObjectUtilities.equal(this.subplots, that.subplots)) {
/* 662 */       return false;
/*     */     }
/* 664 */     return super.equals(obj);
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
/* 677 */     CombinedRangeXYPlot result = (CombinedRangeXYPlot)super.clone();
/* 678 */     result.subplots = ((List)ObjectUtilities.deepClone(this.subplots));
/* 679 */     for (Iterator it = result.subplots.iterator(); it.hasNext();) {
/* 680 */       Plot child = (Plot)it.next();
/* 681 */       child.setParent(result);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 686 */     ValueAxis rangeAxis = result.getRangeAxis();
/* 687 */     if (rangeAxis != null) {
/* 688 */       rangeAxis.configure();
/*     */     }
/*     */     
/* 691 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CombinedRangeXYPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */