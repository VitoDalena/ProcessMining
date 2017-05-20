/*     */ package org.jfree.chart.panel;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.CrosshairLabelGenerator;
/*     */ import org.jfree.chart.plot.Crosshair;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class CrosshairOverlay
/*     */   extends AbstractOverlay
/*     */   implements Overlay, PropertyChangeListener, PublicCloneable, Cloneable, Serializable
/*     */ {
/*     */   private List xCrosshairs;
/*     */   private List yCrosshairs;
/*     */   
/*     */   public CrosshairOverlay()
/*     */   {
/*  89 */     this.xCrosshairs = new ArrayList();
/*  90 */     this.yCrosshairs = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDomainCrosshair(Crosshair crosshair)
/*     */   {
/*  99 */     if (crosshair == null) {
/* 100 */       throw new IllegalArgumentException("Null 'crosshair' argument.");
/*     */     }
/* 102 */     this.xCrosshairs.add(crosshair);
/* 103 */     crosshair.addPropertyChangeListener(this);
/*     */   }
/*     */   
/*     */   public void removeDomainCrosshair(Crosshair crosshair) {
/* 107 */     if (crosshair == null) {
/* 108 */       throw new IllegalArgumentException("Null 'crosshair' argument.");
/*     */     }
/* 110 */     if (this.xCrosshairs.remove(crosshair)) {
/* 111 */       crosshair.removePropertyChangeListener(this);
/* 112 */       fireOverlayChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearDomainCrosshairs() {
/* 117 */     if (this.xCrosshairs.isEmpty()) {
/* 118 */       return;
/*     */     }
/* 120 */     List crosshairs = getDomainCrosshairs();
/* 121 */     for (int i = 0; i < crosshairs.size(); i++) {
/* 122 */       Crosshair c = (Crosshair)crosshairs.get(i);
/* 123 */       this.xCrosshairs.remove(c);
/* 124 */       c.removePropertyChangeListener(this);
/*     */     }
/* 126 */     fireOverlayChanged();
/*     */   }
/*     */   
/*     */   public List getDomainCrosshairs() {
/* 130 */     return new ArrayList(this.xCrosshairs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addRangeCrosshair(Crosshair crosshair)
/*     */   {
/* 139 */     if (crosshair == null) {
/* 140 */       throw new IllegalArgumentException("Null 'crosshair' argument.");
/*     */     }
/* 142 */     this.yCrosshairs.add(crosshair);
/* 143 */     crosshair.addPropertyChangeListener(this);
/*     */   }
/*     */   
/*     */   public void removeRangeCrosshair(Crosshair crosshair) {
/* 147 */     if (crosshair == null) {
/* 148 */       throw new IllegalArgumentException("Null 'crosshair' argument.");
/*     */     }
/* 150 */     if (this.yCrosshairs.remove(crosshair)) {
/* 151 */       crosshair.removePropertyChangeListener(this);
/* 152 */       fireOverlayChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearRangeCrosshairs() {
/* 157 */     if (this.yCrosshairs.isEmpty()) {
/* 158 */       return;
/*     */     }
/* 160 */     List crosshairs = getRangeCrosshairs();
/* 161 */     for (int i = 0; i < crosshairs.size(); i++) {
/* 162 */       Crosshair c = (Crosshair)crosshairs.get(i);
/* 163 */       this.yCrosshairs.remove(c);
/* 164 */       c.removePropertyChangeListener(this);
/*     */     }
/* 166 */     fireOverlayChanged();
/*     */   }
/*     */   
/*     */   public List getRangeCrosshairs() {
/* 170 */     return new ArrayList(this.yCrosshairs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void propertyChange(PropertyChangeEvent e)
/*     */   {
/* 180 */     fireOverlayChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintOverlay(Graphics2D g2, ChartPanel chartPanel)
/*     */   {
/* 190 */     Shape savedClip = g2.getClip();
/* 191 */     Rectangle2D dataArea = chartPanel.getScreenDataArea();
/* 192 */     g2.clip(dataArea);
/* 193 */     JFreeChart chart = chartPanel.getChart();
/* 194 */     XYPlot plot = (XYPlot)chart.getPlot();
/* 195 */     ValueAxis xAxis = plot.getDomainAxis();
/* 196 */     RectangleEdge xAxisEdge = plot.getDomainAxisEdge();
/* 197 */     Iterator iterator = this.xCrosshairs.iterator();
/* 198 */     while (iterator.hasNext()) {
/* 199 */       Crosshair ch = (Crosshair)iterator.next();
/* 200 */       if (ch.isVisible()) {
/* 201 */         double x = ch.getValue();
/* 202 */         double xx = xAxis.valueToJava2D(x, dataArea, xAxisEdge);
/* 203 */         if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 204 */           drawVerticalCrosshair(g2, dataArea, xx, ch);
/*     */         }
/*     */         else {
/* 207 */           drawHorizontalCrosshair(g2, dataArea, xx, ch);
/*     */         }
/*     */       }
/*     */     }
/* 211 */     ValueAxis yAxis = plot.getRangeAxis();
/* 212 */     RectangleEdge yAxisEdge = plot.getRangeAxisEdge();
/* 213 */     iterator = this.yCrosshairs.iterator();
/* 214 */     while (iterator.hasNext()) {
/* 215 */       Crosshair ch = (Crosshair)iterator.next();
/* 216 */       if (ch.isVisible()) {
/* 217 */         double y = ch.getValue();
/* 218 */         double yy = yAxis.valueToJava2D(y, dataArea, yAxisEdge);
/* 219 */         if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 220 */           drawHorizontalCrosshair(g2, dataArea, yy, ch);
/*     */         }
/*     */         else {
/* 223 */           drawVerticalCrosshair(g2, dataArea, yy, ch);
/*     */         }
/*     */       }
/*     */     }
/* 227 */     g2.setClip(savedClip);
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
/*     */   protected void drawHorizontalCrosshair(Graphics2D g2, Rectangle2D dataArea, double y, Crosshair crosshair)
/*     */   {
/* 241 */     if ((y >= dataArea.getMinY()) && (y <= dataArea.getMaxY())) {
/* 242 */       Line2D line = new Line2D.Double(dataArea.getMinX(), y, dataArea.getMaxX(), y);
/*     */       
/* 244 */       Paint savedPaint = g2.getPaint();
/* 245 */       Stroke savedStroke = g2.getStroke();
/* 246 */       g2.setPaint(crosshair.getPaint());
/* 247 */       g2.setStroke(crosshair.getStroke());
/* 248 */       g2.draw(line);
/* 249 */       if (crosshair.isLabelVisible()) {
/* 250 */         String label = crosshair.getLabelGenerator().generateLabel(crosshair);
/*     */         
/* 252 */         RectangleAnchor anchor = crosshair.getLabelAnchor();
/* 253 */         Point2D pt = calculateLabelPoint(line, anchor, 5.0D, 5.0D);
/* 254 */         float xx = (float)pt.getX();
/* 255 */         float yy = (float)pt.getY();
/* 256 */         TextAnchor alignPt = textAlignPtForLabelAnchorH(anchor);
/* 257 */         Shape hotspot = TextUtilities.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0D, TextAnchor.CENTER);
/*     */         
/* 259 */         if (!dataArea.contains(hotspot.getBounds2D())) {
/* 260 */           anchor = flipAnchorV(anchor);
/* 261 */           pt = calculateLabelPoint(line, anchor, 5.0D, 5.0D);
/* 262 */           xx = (float)pt.getX();
/* 263 */           yy = (float)pt.getY();
/* 264 */           alignPt = textAlignPtForLabelAnchorH(anchor);
/* 265 */           hotspot = TextUtilities.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0D, TextAnchor.CENTER);
/*     */         }
/*     */         
/*     */ 
/* 269 */         g2.setPaint(crosshair.getLabelBackgroundPaint());
/* 270 */         g2.fill(hotspot);
/* 271 */         g2.setPaint(crosshair.getLabelOutlinePaint());
/* 272 */         g2.draw(hotspot);
/* 273 */         TextUtilities.drawAlignedString(label, g2, xx, yy, alignPt);
/*     */       }
/* 275 */       g2.setPaint(savedPaint);
/* 276 */       g2.setStroke(savedStroke);
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
/*     */   protected void drawVerticalCrosshair(Graphics2D g2, Rectangle2D dataArea, double x, Crosshair crosshair)
/*     */   {
/* 291 */     if ((x >= dataArea.getMinX()) && (x <= dataArea.getMaxX())) {
/* 292 */       Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, dataArea.getMaxY());
/*     */       
/* 294 */       Paint savedPaint = g2.getPaint();
/* 295 */       Stroke savedStroke = g2.getStroke();
/* 296 */       g2.setPaint(crosshair.getPaint());
/* 297 */       g2.setStroke(crosshair.getStroke());
/* 298 */       g2.draw(line);
/* 299 */       if (crosshair.isLabelVisible()) {
/* 300 */         String label = crosshair.getLabelGenerator().generateLabel(crosshair);
/*     */         
/* 302 */         RectangleAnchor anchor = crosshair.getLabelAnchor();
/* 303 */         Point2D pt = calculateLabelPoint(line, anchor, 5.0D, 5.0D);
/* 304 */         float xx = (float)pt.getX();
/* 305 */         float yy = (float)pt.getY();
/* 306 */         TextAnchor alignPt = textAlignPtForLabelAnchorV(anchor);
/* 307 */         Shape hotspot = TextUtilities.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0D, TextAnchor.CENTER);
/*     */         
/* 309 */         if (!dataArea.contains(hotspot.getBounds2D())) {
/* 310 */           anchor = flipAnchorH(anchor);
/* 311 */           pt = calculateLabelPoint(line, anchor, 5.0D, 5.0D);
/* 312 */           xx = (float)pt.getX();
/* 313 */           yy = (float)pt.getY();
/* 314 */           alignPt = textAlignPtForLabelAnchorV(anchor);
/* 315 */           hotspot = TextUtilities.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0D, TextAnchor.CENTER);
/*     */         }
/*     */         
/* 318 */         g2.setPaint(crosshair.getLabelBackgroundPaint());
/* 319 */         g2.fill(hotspot);
/* 320 */         g2.setPaint(crosshair.getLabelOutlinePaint());
/* 321 */         g2.draw(hotspot);
/* 322 */         TextUtilities.drawAlignedString(label, g2, xx, yy, alignPt);
/*     */       }
/* 324 */       g2.setPaint(savedPaint);
/* 325 */       g2.setStroke(savedStroke);
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
/*     */   private Point2D calculateLabelPoint(Line2D line, RectangleAnchor anchor, double deltaX, double deltaY)
/*     */   {
/* 341 */     double x = 0.0D;
/* 342 */     double y = 0.0D;
/* 343 */     boolean left = (anchor == RectangleAnchor.BOTTOM_LEFT) || (anchor == RectangleAnchor.LEFT) || (anchor == RectangleAnchor.TOP_LEFT);
/*     */     
/*     */ 
/* 346 */     boolean right = (anchor == RectangleAnchor.BOTTOM_RIGHT) || (anchor == RectangleAnchor.RIGHT) || (anchor == RectangleAnchor.TOP_RIGHT);
/*     */     
/*     */ 
/* 349 */     boolean top = (anchor == RectangleAnchor.TOP_LEFT) || (anchor == RectangleAnchor.TOP) || (anchor == RectangleAnchor.TOP_RIGHT);
/*     */     
/*     */ 
/* 352 */     boolean bottom = (anchor == RectangleAnchor.BOTTOM_LEFT) || (anchor == RectangleAnchor.BOTTOM) || (anchor == RectangleAnchor.BOTTOM_RIGHT);
/*     */     
/*     */ 
/* 355 */     Rectangle rect = line.getBounds();
/* 356 */     Point2D pt = RectangleAnchor.coordinates(rect, anchor);
/*     */     
/* 358 */     if (line.getX1() == line.getX2()) {
/* 359 */       x = line.getX1();
/* 360 */       y = (line.getY1() + line.getY2()) / 2.0D;
/* 361 */       if (left) {
/* 362 */         x -= deltaX;
/*     */       }
/* 364 */       if (right) {
/* 365 */         x += deltaX;
/*     */       }
/* 367 */       if (top) {
/* 368 */         y = Math.min(line.getY1(), line.getY2()) + deltaY;
/*     */       }
/* 370 */       if (bottom) {
/* 371 */         y = Math.max(line.getY1(), line.getY2()) - deltaY;
/*     */       }
/*     */     }
/*     */     else {
/* 375 */       x = (line.getX1() + line.getX2()) / 2.0D;
/* 376 */       y = line.getY1();
/* 377 */       if (left) {
/* 378 */         x = Math.min(line.getX1(), line.getX2()) + deltaX;
/*     */       }
/* 380 */       if (right) {
/* 381 */         x = Math.max(line.getX1(), line.getX2()) - deltaX;
/*     */       }
/* 383 */       if (top) {
/* 384 */         y -= deltaY;
/*     */       }
/* 386 */       if (bottom) {
/* 387 */         y += deltaY;
/*     */       }
/*     */     }
/* 390 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private TextAnchor textAlignPtForLabelAnchorV(RectangleAnchor anchor)
/*     */   {
/* 402 */     TextAnchor result = TextAnchor.CENTER;
/* 403 */     if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
/* 404 */       result = TextAnchor.TOP_RIGHT;
/*     */     }
/* 406 */     else if (anchor.equals(RectangleAnchor.TOP)) {
/* 407 */       result = TextAnchor.TOP_CENTER;
/*     */     }
/* 409 */     else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
/* 410 */       result = TextAnchor.TOP_LEFT;
/*     */     }
/* 412 */     else if (anchor.equals(RectangleAnchor.LEFT)) {
/* 413 */       result = TextAnchor.HALF_ASCENT_RIGHT;
/*     */     }
/* 415 */     else if (anchor.equals(RectangleAnchor.RIGHT)) {
/* 416 */       result = TextAnchor.HALF_ASCENT_LEFT;
/*     */     }
/* 418 */     else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
/* 419 */       result = TextAnchor.BOTTOM_RIGHT;
/*     */     }
/* 421 */     else if (anchor.equals(RectangleAnchor.BOTTOM)) {
/* 422 */       result = TextAnchor.BOTTOM_CENTER;
/*     */     }
/* 424 */     else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
/* 425 */       result = TextAnchor.BOTTOM_LEFT;
/*     */     }
/* 427 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private TextAnchor textAlignPtForLabelAnchorH(RectangleAnchor anchor)
/*     */   {
/* 439 */     TextAnchor result = TextAnchor.CENTER;
/* 440 */     if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
/* 441 */       result = TextAnchor.BOTTOM_LEFT;
/*     */     }
/* 443 */     else if (anchor.equals(RectangleAnchor.TOP)) {
/* 444 */       result = TextAnchor.BOTTOM_CENTER;
/*     */     }
/* 446 */     else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
/* 447 */       result = TextAnchor.BOTTOM_RIGHT;
/*     */     }
/* 449 */     else if (anchor.equals(RectangleAnchor.LEFT)) {
/* 450 */       result = TextAnchor.HALF_ASCENT_LEFT;
/*     */     }
/* 452 */     else if (anchor.equals(RectangleAnchor.RIGHT)) {
/* 453 */       result = TextAnchor.HALF_ASCENT_RIGHT;
/*     */     }
/* 455 */     else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
/* 456 */       result = TextAnchor.TOP_LEFT;
/*     */     }
/* 458 */     else if (anchor.equals(RectangleAnchor.BOTTOM)) {
/* 459 */       result = TextAnchor.TOP_CENTER;
/*     */     }
/* 461 */     else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
/* 462 */       result = TextAnchor.TOP_RIGHT;
/*     */     }
/* 464 */     return result;
/*     */   }
/*     */   
/*     */   private RectangleAnchor flipAnchorH(RectangleAnchor anchor) {
/* 468 */     RectangleAnchor result = anchor;
/* 469 */     if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
/* 470 */       result = RectangleAnchor.TOP_RIGHT;
/*     */     }
/* 472 */     else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
/* 473 */       result = RectangleAnchor.TOP_LEFT;
/*     */     }
/* 475 */     else if (anchor.equals(RectangleAnchor.LEFT)) {
/* 476 */       result = RectangleAnchor.RIGHT;
/*     */     }
/* 478 */     else if (anchor.equals(RectangleAnchor.RIGHT)) {
/* 479 */       result = RectangleAnchor.LEFT;
/*     */     }
/* 481 */     else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
/* 482 */       result = RectangleAnchor.BOTTOM_RIGHT;
/*     */     }
/* 484 */     else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
/* 485 */       result = RectangleAnchor.BOTTOM_LEFT;
/*     */     }
/* 487 */     return result;
/*     */   }
/*     */   
/*     */   private RectangleAnchor flipAnchorV(RectangleAnchor anchor) {
/* 491 */     RectangleAnchor result = anchor;
/* 492 */     if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
/* 493 */       result = RectangleAnchor.BOTTOM_LEFT;
/*     */     }
/* 495 */     else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
/* 496 */       result = RectangleAnchor.BOTTOM_RIGHT;
/*     */     }
/* 498 */     else if (anchor.equals(RectangleAnchor.TOP)) {
/* 499 */       result = RectangleAnchor.BOTTOM;
/*     */     }
/* 501 */     else if (anchor.equals(RectangleAnchor.BOTTOM)) {
/* 502 */       result = RectangleAnchor.TOP;
/*     */     }
/* 504 */     else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
/* 505 */       result = RectangleAnchor.TOP_LEFT;
/*     */     }
/* 507 */     else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
/* 508 */       result = RectangleAnchor.TOP_RIGHT;
/*     */     }
/* 510 */     return result;
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
/* 521 */     if (obj == this) {
/* 522 */       return true;
/*     */     }
/* 524 */     if (!(obj instanceof CrosshairOverlay)) {
/* 525 */       return false;
/*     */     }
/* 527 */     CrosshairOverlay that = (CrosshairOverlay)obj;
/* 528 */     if (!this.xCrosshairs.equals(that.xCrosshairs)) {
/* 529 */       return false;
/*     */     }
/* 531 */     if (!this.yCrosshairs.equals(that.yCrosshairs)) {
/* 532 */       return false;
/*     */     }
/* 534 */     return true;
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
/* 546 */     CrosshairOverlay clone = (CrosshairOverlay)super.clone();
/* 547 */     clone.xCrosshairs = ((List)ObjectUtilities.deepClone(this.xCrosshairs));
/* 548 */     clone.yCrosshairs = ((List)ObjectUtilities.deepClone(this.yCrosshairs));
/* 549 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/panel/CrosshairOverlay.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */