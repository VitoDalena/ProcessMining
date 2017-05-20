/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
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
/*     */ public class CombinedRangeCategoryPlot
/*     */   extends CategoryPlot
/*     */   implements PlotChangeListener
/*     */ {
/*     */   private static final long serialVersionUID = 7260210007554504515L;
/*     */   private List subplots;
/*     */   private double gap;
/*     */   private transient Rectangle2D[] subplotArea;
/*     */   
/*     */   public CombinedRangeCategoryPlot()
/*     */   {
/* 107 */     this(new NumberAxis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedRangeCategoryPlot(ValueAxis rangeAxis)
/*     */   {
/* 116 */     super(null, null, rangeAxis, null);
/* 117 */     this.subplots = new ArrayList();
/* 118 */     this.gap = 5.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGap()
/*     */   {
/* 127 */     return this.gap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGap(double gap)
/*     */   {
/* 137 */     this.gap = gap;
/* 138 */     fireChangeEvent();
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
/*     */   public void add(CategoryPlot subplot)
/*     */   {
/* 152 */     add(subplot, 1);
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
/* 166 */     if (subplot == null) {
/* 167 */       throw new IllegalArgumentException("Null 'subplot' argument.");
/*     */     }
/* 169 */     if (weight <= 0) {
/* 170 */       throw new IllegalArgumentException("Require weight >= 1.");
/*     */     }
/*     */     
/* 173 */     subplot.setParent(this);
/* 174 */     subplot.setWeight(weight);
/* 175 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 176 */     subplot.setRangeAxis(null);
/* 177 */     subplot.setOrientation(getOrientation());
/* 178 */     subplot.addChangeListener(this);
/* 179 */     this.subplots.add(subplot);
/*     */     
/* 181 */     ValueAxis axis = getRangeAxis();
/* 182 */     if (axis != null) {
/* 183 */       axis.configure();
/*     */     }
/* 185 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(CategoryPlot subplot)
/*     */   {
/* 194 */     if (subplot == null) {
/* 195 */       throw new IllegalArgumentException(" Null 'subplot' argument.");
/*     */     }
/* 197 */     int position = -1;
/* 198 */     int size = this.subplots.size();
/* 199 */     int i = 0;
/* 200 */     while ((position == -1) && (i < size)) {
/* 201 */       if (this.subplots.get(i) == subplot) {
/* 202 */         position = i;
/*     */       }
/* 204 */       i++;
/*     */     }
/* 206 */     if (position != -1) {
/* 207 */       this.subplots.remove(position);
/* 208 */       subplot.setParent(null);
/* 209 */       subplot.removeChangeListener(this);
/*     */       
/* 211 */       ValueAxis range = getRangeAxis();
/* 212 */       if (range != null) {
/* 213 */         range.configure();
/*     */       }
/*     */       
/* 216 */       ValueAxis range2 = getRangeAxis(1);
/* 217 */       if (range2 != null) {
/* 218 */         range2.configure();
/*     */       }
/* 220 */       fireChangeEvent();
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
/* 231 */     if (this.subplots != null) {
/* 232 */       return Collections.unmodifiableList(this.subplots);
/*     */     }
/*     */     
/* 235 */     return Collections.EMPTY_LIST;
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
/* 250 */     AxisSpace space = new AxisSpace();
/* 251 */     PlotOrientation orientation = getOrientation();
/*     */     
/*     */ 
/* 254 */     AxisSpace fixed = getFixedRangeAxisSpace();
/* 255 */     if (fixed != null) {
/* 256 */       if (orientation == PlotOrientation.VERTICAL) {
/* 257 */         space.setLeft(fixed.getLeft());
/* 258 */         space.setRight(fixed.getRight());
/*     */       }
/* 260 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 261 */         space.setTop(fixed.getTop());
/* 262 */         space.setBottom(fixed.getBottom());
/*     */       }
/*     */     }
/*     */     else {
/* 266 */       ValueAxis valueAxis = getRangeAxis();
/* 267 */       RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), orientation);
/*     */       
/* 269 */       if (valueAxis != null) {
/* 270 */         space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, space);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 275 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/*     */     
/* 277 */     int n = this.subplots.size();
/* 278 */     int totalWeight = 0;
/* 279 */     for (int i = 0; i < n; i++) {
/* 280 */       CategoryPlot sub = (CategoryPlot)this.subplots.get(i);
/* 281 */       totalWeight += sub.getWeight();
/*     */     }
/*     */     
/*     */ 
/* 285 */     this.subplotArea = new Rectangle2D[n];
/* 286 */     double x = adjustedPlotArea.getX();
/* 287 */     double y = adjustedPlotArea.getY();
/* 288 */     double usableSize = 0.0D;
/* 289 */     if (orientation == PlotOrientation.VERTICAL) {
/* 290 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/*     */     }
/* 292 */     else if (orientation == PlotOrientation.HORIZONTAL) {
/* 293 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     }
/*     */     
/* 296 */     for (int i = 0; i < n; i++) {
/* 297 */       CategoryPlot plot = (CategoryPlot)this.subplots.get(i);
/*     */       
/*     */ 
/* 300 */       if (orientation == PlotOrientation.VERTICAL) {
/* 301 */         double w = usableSize * plot.getWeight() / totalWeight;
/* 302 */         this.subplotArea[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/*     */         
/* 304 */         x = x + w + this.gap;
/*     */       }
/* 306 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 307 */         double h = usableSize * plot.getWeight() / totalWeight;
/* 308 */         this.subplotArea[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/*     */         
/* 310 */         y = y + h + this.gap;
/*     */       }
/*     */       
/* 313 */       AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, this.subplotArea[i], null);
/*     */       
/* 315 */       space.ensureAtLeast(subSpace);
/*     */     }
/*     */     
/*     */ 
/* 319 */     return space;
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
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 340 */     if (info != null) {
/* 341 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 345 */     RectangleInsets insets = getInsets();
/* 346 */     insets.trim(area);
/*     */     
/*     */ 
/* 349 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 350 */     Rectangle2D dataArea = space.shrink(area, null);
/*     */     
/*     */ 
/* 353 */     setFixedDomainAxisSpaceForSubplots(space);
/*     */     
/*     */ 
/* 356 */     ValueAxis axis = getRangeAxis();
/* 357 */     RectangleEdge rangeEdge = getRangeAxisEdge();
/* 358 */     double cursor = RectangleEdge.coordinate(dataArea, rangeEdge);
/* 359 */     AxisState state = axis.draw(g2, cursor, area, dataArea, rangeEdge, info);
/*     */     
/* 361 */     if (parentState == null) {
/* 362 */       parentState = new PlotState();
/*     */     }
/* 364 */     parentState.getSharedAxisStates().put(axis, state);
/*     */     
/*     */ 
/* 367 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 368 */       CategoryPlot plot = (CategoryPlot)this.subplots.get(i);
/* 369 */       PlotRenderingInfo subplotInfo = null;
/* 370 */       if (info != null) {
/* 371 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 372 */         info.addSubplotInfo(subplotInfo);
/*     */       }
/* 374 */       Point2D subAnchor = null;
/* 375 */       if ((anchor != null) && (this.subplotArea[i].contains(anchor))) {
/* 376 */         subAnchor = anchor;
/*     */       }
/* 378 */       plot.draw(g2, this.subplotArea[i], subAnchor, parentState, subplotInfo);
/*     */     }
/*     */     
/*     */ 
/* 382 */     if (info != null) {
/* 383 */       info.setDataArea(dataArea);
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
/* 395 */     super.setOrientation(orientation);
/*     */     
/* 397 */     Iterator iterator = this.subplots.iterator();
/* 398 */     while (iterator.hasNext()) {
/* 399 */       CategoryPlot plot = (CategoryPlot)iterator.next();
/* 400 */       plot.setOrientation(orientation);
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
/* 419 */     Range result = null;
/* 420 */     if (this.subplots != null) {
/* 421 */       Iterator iterator = this.subplots.iterator();
/* 422 */       while (iterator.hasNext()) {
/* 423 */         CategoryPlot subplot = (CategoryPlot)iterator.next();
/* 424 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       }
/*     */     }
/* 427 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 436 */     LegendItemCollection result = getFixedLegendItems();
/* 437 */     if (result == null) {
/* 438 */       result = new LegendItemCollection();
/* 439 */       if (this.subplots != null) {
/* 440 */         Iterator iterator = this.subplots.iterator();
/* 441 */         while (iterator.hasNext()) {
/* 442 */           CategoryPlot plot = (CategoryPlot)iterator.next();
/* 443 */           LegendItemCollection more = plot.getLegendItems();
/* 444 */           result.addAll(more);
/*     */         }
/*     */       }
/*     */     }
/* 448 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space)
/*     */   {
/* 458 */     Iterator iterator = this.subplots.iterator();
/* 459 */     while (iterator.hasNext()) {
/* 460 */       CategoryPlot plot = (CategoryPlot)iterator.next();
/* 461 */       plot.setFixedDomainAxisSpace(space, false);
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
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*     */   {
/* 475 */     Rectangle2D dataArea = info.getDataArea();
/* 476 */     if (dataArea.contains(x, y)) {
/* 477 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 478 */         CategoryPlot subplot = (CategoryPlot)this.subplots.get(i);
/* 479 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 480 */         subplot.handleClick(x, y, subplotInfo);
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
/* 493 */     notifyListeners(event);
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
/* 504 */     if (obj == this) {
/* 505 */       return true;
/*     */     }
/* 507 */     if (!(obj instanceof CombinedRangeCategoryPlot)) {
/* 508 */       return false;
/*     */     }
/* 510 */     CombinedRangeCategoryPlot that = (CombinedRangeCategoryPlot)obj;
/* 511 */     if (this.gap != that.gap) {
/* 512 */       return false;
/*     */     }
/* 514 */     if (!ObjectUtilities.equal(this.subplots, that.subplots)) {
/* 515 */       return false;
/*     */     }
/* 517 */     return super.equals(obj);
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
/* 529 */     CombinedRangeCategoryPlot result = (CombinedRangeCategoryPlot)super.clone();
/*     */     
/* 531 */     result.subplots = ((List)ObjectUtilities.deepClone(this.subplots));
/* 532 */     for (Iterator it = result.subplots.iterator(); it.hasNext();) {
/* 533 */       Plot child = (Plot)it.next();
/* 534 */       child.setParent(result);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 539 */     ValueAxis rangeAxis = result.getRangeAxis();
/* 540 */     if (rangeAxis != null) {
/* 541 */       rangeAxis.configure();
/*     */     }
/*     */     
/* 544 */     return result;
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 558 */     stream.defaultReadObject();
/*     */     
/*     */ 
/*     */ 
/* 562 */     ValueAxis rangeAxis = getRangeAxis();
/* 563 */     if (rangeAxis != null) {
/* 564 */       rangeAxis.configure();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CombinedRangeCategoryPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */