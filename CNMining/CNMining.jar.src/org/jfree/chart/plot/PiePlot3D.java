/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.PieSectionEntity;
/*      */ import org.jfree.chart.labels.PieToolTipGenerator;
/*      */ import org.jfree.chart.urls.PieURLGenerator;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.general.PieDataset;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.Rotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PiePlot3D
/*      */   extends PiePlot
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 3408984188945161432L;
/*  127 */   private double depthFactor = 0.12D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  135 */   private boolean darkerSides = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public PiePlot3D()
/*      */   {
/*  142 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PiePlot3D(PieDataset dataset)
/*      */   {
/*  152 */     super(dataset);
/*  153 */     setCircular(false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDepthFactor()
/*      */   {
/*  164 */     return this.depthFactor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDepthFactor(double factor)
/*      */   {
/*  176 */     this.depthFactor = factor;
/*  177 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDarkerSides()
/*      */   {
/*  192 */     return this.darkerSides;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDarkerSides(boolean darker)
/*      */   {
/*  209 */     this.darkerSides = darker;
/*  210 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*      */   {
/*  231 */     RectangleInsets insets = getInsets();
/*  232 */     insets.trim(plotArea);
/*      */     
/*  234 */     Rectangle2D originalPlotArea = (Rectangle2D)plotArea.clone();
/*  235 */     if (info != null) {
/*  236 */       info.setPlotArea(plotArea);
/*  237 */       info.setDataArea(plotArea);
/*      */     }
/*      */     
/*  240 */     drawBackground(g2, plotArea);
/*      */     
/*  242 */     Shape savedClip = g2.getClip();
/*  243 */     g2.clip(plotArea);
/*      */     
/*      */ 
/*  246 */     double gapPercent = getInteriorGap();
/*  247 */     double labelPercent = 0.0D;
/*  248 */     if (getLabelGenerator() != null) {
/*  249 */       labelPercent = getLabelGap() + getMaximumLabelWidth();
/*      */     }
/*  251 */     double gapHorizontal = plotArea.getWidth() * (gapPercent + labelPercent) * 2.0D;
/*      */     
/*  253 */     double gapVertical = plotArea.getHeight() * gapPercent * 2.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  267 */     double linkX = plotArea.getX() + gapHorizontal / 2.0D;
/*  268 */     double linkY = plotArea.getY() + gapVertical / 2.0D;
/*  269 */     double linkW = plotArea.getWidth() - gapHorizontal;
/*  270 */     double linkH = plotArea.getHeight() - gapVertical;
/*      */     
/*      */ 
/*  273 */     if (isCircular()) {
/*  274 */       double min = Math.min(linkW, linkH) / 2.0D;
/*  275 */       linkX = (linkX + linkX + linkW) / 2.0D - min;
/*  276 */       linkY = (linkY + linkY + linkH) / 2.0D - min;
/*  277 */       linkW = 2.0D * min;
/*  278 */       linkH = 2.0D * min;
/*      */     }
/*      */     
/*  281 */     PiePlotState state = initialise(g2, plotArea, this, null, info);
/*      */     
/*      */ 
/*      */ 
/*  285 */     Rectangle2D linkAreaXX = new Rectangle2D.Double(linkX, linkY, linkW, linkH * (1.0D - this.depthFactor));
/*      */     
/*  287 */     state.setLinkArea(linkAreaXX);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  300 */     double hh = linkW * getLabelLinkMargin();
/*  301 */     double vv = linkH * getLabelLinkMargin();
/*  302 */     Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0D, linkY + vv / 2.0D, linkW - hh, linkH - vv);
/*      */     
/*      */ 
/*  305 */     state.setExplodedPieArea(explodeArea);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  310 */     double maximumExplodePercent = getMaximumExplodePercent();
/*  311 */     double percent = maximumExplodePercent / (1.0D + maximumExplodePercent);
/*      */     
/*  313 */     double h1 = explodeArea.getWidth() * percent;
/*  314 */     double v1 = explodeArea.getHeight() * percent;
/*  315 */     Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() + h1 / 2.0D, explodeArea.getY() + v1 / 2.0D, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  321 */     int depth = (int)(pieArea.getHeight() * this.depthFactor);
/*  322 */     Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, linkH - depth);
/*      */     
/*  324 */     state.setLinkArea(linkArea);
/*      */     
/*  326 */     state.setPieArea(pieArea);
/*  327 */     state.setPieCenterX(pieArea.getCenterX());
/*  328 */     state.setPieCenterY(pieArea.getCenterY() - depth / 2.0D);
/*  329 */     state.setPieWRadius(pieArea.getWidth() / 2.0D);
/*  330 */     state.setPieHRadius((pieArea.getHeight() - depth) / 2.0D);
/*      */     
/*      */ 
/*  333 */     PieDataset dataset = getDataset();
/*  334 */     if (DatasetUtilities.isEmptyOrNull(getDataset())) {
/*  335 */       drawNoDataMessage(g2, plotArea);
/*  336 */       g2.setClip(savedClip);
/*  337 */       drawOutline(g2, plotArea);
/*  338 */       return;
/*      */     }
/*      */     
/*      */ 
/*  342 */     if (dataset.getKeys().size() > plotArea.getWidth()) {
/*  343 */       String text = "Too many elements";
/*  344 */       Font sfont = new Font("dialog", 1, 10);
/*  345 */       g2.setFont(sfont);
/*  346 */       FontMetrics fm = g2.getFontMetrics(sfont);
/*  347 */       int stringWidth = fm.stringWidth(text);
/*      */       
/*  349 */       g2.drawString(text, (int)(plotArea.getX() + (plotArea.getWidth() - stringWidth) / 2.0D), (int)(plotArea.getY() + plotArea.getHeight() / 2.0D));
/*      */       
/*      */ 
/*  352 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  357 */     if (isCircular()) {
/*  358 */       double min = Math.min(plotArea.getWidth(), plotArea.getHeight()) / 2.0D;
/*      */       
/*  360 */       plotArea = new Rectangle2D.Double(plotArea.getCenterX() - min, plotArea.getCenterY() - min, 2.0D * min, 2.0D * min);
/*      */     }
/*      */     
/*      */ 
/*  364 */     List sectionKeys = dataset.getKeys();
/*      */     
/*  366 */     if (sectionKeys.size() == 0) {
/*  367 */       return;
/*      */     }
/*      */     
/*      */ 
/*  371 */     double arcX = pieArea.getX();
/*  372 */     double arcY = pieArea.getY();
/*      */     
/*      */ 
/*  375 */     Composite originalComposite = g2.getComposite();
/*  376 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/*  379 */     double totalValue = DatasetUtilities.calculatePieDatasetTotal(dataset);
/*  380 */     double runningTotal = 0.0D;
/*  381 */     if (depth < 0) {
/*  382 */       return;
/*      */     }
/*      */     
/*  385 */     ArrayList arcList = new ArrayList();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  391 */     Iterator iterator = sectionKeys.iterator();
/*  392 */     while (iterator.hasNext())
/*      */     {
/*  394 */       Comparable currentKey = (Comparable)iterator.next();
/*  395 */       Number dataValue = dataset.getValue(currentKey);
/*  396 */       if (dataValue == null) {
/*  397 */         arcList.add(null);
/*      */       }
/*      */       else {
/*  400 */         double value = dataValue.doubleValue();
/*  401 */         if (value <= 0.0D) {
/*  402 */           arcList.add(null);
/*      */         }
/*      */         else {
/*  405 */           double startAngle = getStartAngle();
/*  406 */           double direction = getDirection().getFactor();
/*  407 */           double angle1 = startAngle + direction * (runningTotal * 360.0D) / totalValue;
/*      */           
/*  409 */           double angle2 = startAngle + direction * (runningTotal + value) * 360.0D / totalValue;
/*      */           
/*  411 */           if (Math.abs(angle2 - angle1) > getMinimumArcAngleToDraw()) {
/*  412 */             arcList.add(new Arc2D.Double(arcX, arcY + depth, pieArea.getWidth(), pieArea.getHeight() - depth, angle1, angle2 - angle1, 2));
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  417 */             arcList.add(null);
/*      */           }
/*  419 */           runningTotal += value;
/*      */         }
/*      */       } }
/*  422 */     Shape oldClip = g2.getClip();
/*      */     
/*  424 */     Ellipse2D top = new Ellipse2D.Double(pieArea.getX(), pieArea.getY(), pieArea.getWidth(), pieArea.getHeight() - depth);
/*      */     
/*      */ 
/*  427 */     Ellipse2D bottom = new Ellipse2D.Double(pieArea.getX(), pieArea.getY() + depth, pieArea.getWidth(), pieArea.getHeight() - depth);
/*      */     
/*      */ 
/*  430 */     Rectangle2D lower = new Rectangle2D.Double(top.getX(), top.getCenterY(), pieArea.getWidth(), bottom.getMaxY() - top.getCenterY());
/*      */     
/*      */ 
/*      */ 
/*  434 */     Rectangle2D upper = new Rectangle2D.Double(pieArea.getX(), top.getY(), pieArea.getWidth(), bottom.getCenterY() - top.getY());
/*      */     
/*      */ 
/*  437 */     Area a = new Area(top);
/*  438 */     a.add(new Area(lower));
/*  439 */     Area b = new Area(bottom);
/*  440 */     b.add(new Area(upper));
/*  441 */     Area pie = new Area(a);
/*  442 */     pie.intersect(b);
/*      */     
/*  444 */     Area front = new Area(pie);
/*  445 */     front.subtract(new Area(top));
/*      */     
/*  447 */     Area back = new Area(pie);
/*  448 */     back.subtract(new Area(bottom));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  453 */     Arc2D.Double arc = new Arc2D.Double(arcX, arcY + depth, pieArea.getWidth(), pieArea.getHeight() - depth, 0.0D, 360.0D, 2);
/*      */     
/*      */ 
/*  456 */     int categoryCount = arcList.size();
/*  457 */     for (int categoryIndex = 0; categoryIndex < categoryCount; 
/*  458 */         categoryIndex++) {
/*  459 */       arc = (Arc2D.Double)arcList.get(categoryIndex);
/*  460 */       if (arc != null)
/*      */       {
/*      */ 
/*  463 */         Comparable key = getSectionKey(categoryIndex);
/*  464 */         Paint paint = lookupSectionPaint(key);
/*  465 */         Paint outlinePaint = lookupSectionOutlinePaint(key);
/*  466 */         Stroke outlineStroke = lookupSectionOutlineStroke(key);
/*  467 */         g2.setPaint(paint);
/*  468 */         g2.fill(arc);
/*  469 */         g2.setPaint(outlinePaint);
/*  470 */         g2.setStroke(outlineStroke);
/*  471 */         g2.draw(arc);
/*  472 */         g2.setPaint(paint);
/*      */         
/*  474 */         Point2D p1 = arc.getStartPoint();
/*      */         
/*      */ 
/*  477 */         int[] xs = { (int)arc.getCenterX(), (int)arc.getCenterX(), (int)p1.getX(), (int)p1.getX() };
/*      */         
/*  479 */         int[] ys = { (int)arc.getCenterY(), (int)arc.getCenterY() - depth, (int)p1.getY() - depth, (int)p1.getY() };
/*      */         
/*  481 */         Polygon polygon = new Polygon(xs, ys, 4);
/*  482 */         g2.setPaint(Color.lightGray);
/*  483 */         g2.fill(polygon);
/*  484 */         g2.setPaint(outlinePaint);
/*  485 */         g2.setStroke(outlineStroke);
/*  486 */         g2.draw(polygon);
/*  487 */         g2.setPaint(paint);
/*      */       }
/*      */     }
/*      */     
/*  491 */     g2.setPaint(Color.gray);
/*  492 */     g2.fill(back);
/*  493 */     g2.fill(front);
/*      */     
/*      */ 
/*  496 */     int cat = 0;
/*  497 */     iterator = arcList.iterator();
/*  498 */     while (iterator.hasNext()) {
/*  499 */       Arc2D segment = (Arc2D)iterator.next();
/*  500 */       if (segment != null) {
/*  501 */         Comparable key = getSectionKey(cat);
/*  502 */         Paint paint = lookupSectionPaint(key);
/*  503 */         Paint outlinePaint = lookupSectionOutlinePaint(key);
/*  504 */         Stroke outlineStroke = lookupSectionOutlineStroke(key);
/*  505 */         drawSide(g2, pieArea, segment, front, back, paint, outlinePaint, outlineStroke, false, true);
/*      */       }
/*      */       
/*  508 */       cat++;
/*      */     }
/*      */     
/*      */ 
/*  512 */     cat = 0;
/*  513 */     iterator = arcList.iterator();
/*  514 */     while (iterator.hasNext()) {
/*  515 */       Arc2D segment = (Arc2D)iterator.next();
/*  516 */       if (segment != null) {
/*  517 */         Comparable key = getSectionKey(cat);
/*  518 */         Paint paint = lookupSectionPaint(key);
/*  519 */         Paint outlinePaint = lookupSectionOutlinePaint(key);
/*  520 */         Stroke outlineStroke = lookupSectionOutlineStroke(key);
/*  521 */         drawSide(g2, pieArea, segment, front, back, paint, outlinePaint, outlineStroke, true, false);
/*      */       }
/*      */       
/*  524 */       cat++;
/*      */     }
/*      */     
/*  527 */     g2.setClip(oldClip);
/*      */     
/*      */ 
/*      */ 
/*  531 */     for (int sectionIndex = 0; sectionIndex < categoryCount; 
/*  532 */         sectionIndex++) {
/*  533 */       arc = (Arc2D.Double)arcList.get(sectionIndex);
/*  534 */       if (arc != null)
/*      */       {
/*      */ 
/*  537 */         Arc2D upperArc = new Arc2D.Double(arcX, arcY, pieArea.getWidth(), pieArea.getHeight() - depth, arc.getAngleStart(), arc.getAngleExtent(), 2);
/*      */         
/*      */ 
/*      */ 
/*  541 */         Comparable currentKey = (Comparable)sectionKeys.get(sectionIndex);
/*  542 */         Paint paint = lookupSectionPaint(currentKey, true);
/*  543 */         Paint outlinePaint = lookupSectionOutlinePaint(currentKey);
/*  544 */         Stroke outlineStroke = lookupSectionOutlineStroke(currentKey);
/*  545 */         g2.setPaint(paint);
/*  546 */         g2.fill(upperArc);
/*  547 */         g2.setStroke(outlineStroke);
/*  548 */         g2.setPaint(outlinePaint);
/*  549 */         g2.draw(upperArc);
/*      */         
/*      */ 
/*  552 */         if (info != null) {
/*  553 */           EntityCollection entities = info.getOwner().getEntityCollection();
/*      */           
/*  555 */           if (entities != null) {
/*  556 */             String tip = null;
/*  557 */             PieToolTipGenerator tipster = getToolTipGenerator();
/*  558 */             if (tipster != null)
/*      */             {
/*  560 */               tip = tipster.generateToolTip(dataset, currentKey);
/*      */             }
/*  562 */             String url = null;
/*  563 */             if (getURLGenerator() != null) {
/*  564 */               url = getURLGenerator().generateURL(dataset, currentKey, getPieIndex());
/*      */             }
/*      */             
/*  567 */             PieSectionEntity entity = new PieSectionEntity(upperArc, dataset, getPieIndex(), sectionIndex, currentKey, tip, url);
/*      */             
/*      */ 
/*  570 */             entities.add(entity);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  575 */     List keys = dataset.getKeys();
/*  576 */     Rectangle2D adjustedPlotArea = new Rectangle2D.Double(originalPlotArea.getX(), originalPlotArea.getY(), originalPlotArea.getWidth(), originalPlotArea.getHeight() - depth);
/*      */     
/*      */ 
/*      */ 
/*  580 */     if (getSimpleLabels()) {
/*  581 */       drawSimpleLabels(g2, keys, totalValue, adjustedPlotArea, linkArea, state);
/*      */     }
/*      */     else
/*      */     {
/*  585 */       drawLabels(g2, keys, totalValue, adjustedPlotArea, linkArea, state);
/*      */     }
/*      */     
/*      */ 
/*  589 */     g2.setClip(savedClip);
/*  590 */     g2.setComposite(originalComposite);
/*  591 */     drawOutline(g2, originalPlotArea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawSide(Graphics2D g2, Rectangle2D plotArea, Arc2D arc, Area front, Area back, Paint paint, Paint outlinePaint, Stroke outlineStroke, boolean drawFront, boolean drawBack)
/*      */   {
/*  620 */     if ((getDarkerSides()) && 
/*  621 */       ((paint instanceof Color))) {
/*  622 */       Color c = (Color)paint;
/*  623 */       c = c.darker();
/*  624 */       paint = c;
/*      */     }
/*      */     
/*      */ 
/*  628 */     double start = arc.getAngleStart();
/*  629 */     double extent = arc.getAngleExtent();
/*  630 */     double end = start + extent;
/*      */     
/*  632 */     g2.setStroke(outlineStroke);
/*      */     
/*      */ 
/*  635 */     if (extent < 0.0D)
/*      */     {
/*  637 */       if (isAngleAtFront(start))
/*      */       {
/*  639 */         if (!isAngleAtBack(end))
/*      */         {
/*  641 */           if (extent > -180.0D)
/*      */           {
/*  643 */             if (drawFront) {
/*  644 */               Area side = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), arc.getStartPoint().getX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*  649 */               side.intersect(front);
/*  650 */               g2.setPaint(paint);
/*  651 */               g2.fill(side);
/*  652 */               g2.setPaint(outlinePaint);
/*  653 */               g2.draw(side);
/*      */             }
/*      */             
/*      */           }
/*      */           else
/*      */           {
/*  659 */             Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  663 */             side1.intersect(front);
/*      */             
/*  665 */             Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  670 */             side2.intersect(front);
/*  671 */             g2.setPaint(paint);
/*  672 */             if (drawFront) {
/*  673 */               g2.fill(side1);
/*  674 */               g2.fill(side2);
/*      */             }
/*      */             
/*  677 */             if (drawBack) {
/*  678 */               g2.fill(back);
/*      */             }
/*      */             
/*  681 */             g2.setPaint(outlinePaint);
/*  682 */             if (drawFront) {
/*  683 */               g2.draw(side1);
/*  684 */               g2.draw(side2);
/*      */             }
/*      */             
/*  687 */             if (drawBack) {
/*  688 */               g2.draw(back);
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */         else
/*      */         {
/*  696 */           if (drawBack) {
/*  697 */             Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  701 */             side2.intersect(back);
/*  702 */             g2.setPaint(paint);
/*  703 */             g2.fill(side2);
/*  704 */             g2.setPaint(outlinePaint);
/*  705 */             g2.draw(side2);
/*      */           }
/*      */           
/*  708 */           if (drawFront) {
/*  709 */             Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  713 */             side1.intersect(front);
/*  714 */             g2.setPaint(paint);
/*  715 */             g2.fill(side1);
/*  716 */             g2.setPaint(outlinePaint);
/*  717 */             g2.draw(side1);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*  724 */       else if (!isAngleAtFront(end)) {
/*  725 */         if (extent > -180.0D) {
/*  726 */           if (drawBack) {
/*  727 */             Area side = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), arc.getEndPoint().getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  732 */             side.intersect(back);
/*  733 */             g2.setPaint(paint);
/*  734 */             g2.fill(side);
/*  735 */             g2.setPaint(outlinePaint);
/*  736 */             g2.draw(side);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  741 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  745 */           side1.intersect(back);
/*      */           
/*  747 */           Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  752 */           side2.intersect(back);
/*      */           
/*  754 */           g2.setPaint(paint);
/*  755 */           if (drawBack) {
/*  756 */             g2.fill(side1);
/*  757 */             g2.fill(side2);
/*      */           }
/*      */           
/*  760 */           if (drawFront) {
/*  761 */             g2.fill(front);
/*      */           }
/*      */           
/*  764 */           g2.setPaint(outlinePaint);
/*  765 */           if (drawBack) {
/*  766 */             g2.draw(side1);
/*  767 */             g2.draw(side2);
/*      */           }
/*      */           
/*  770 */           if (drawFront) {
/*  771 */             g2.draw(front);
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  778 */         if (drawBack) {
/*  779 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  783 */           side1.intersect(back);
/*  784 */           g2.setPaint(paint);
/*  785 */           g2.fill(side1);
/*  786 */           g2.setPaint(outlinePaint);
/*  787 */           g2.draw(side1);
/*      */         }
/*      */         
/*  790 */         if (drawFront) {
/*  791 */           Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  795 */           side2.intersect(front);
/*  796 */           g2.setPaint(paint);
/*  797 */           g2.fill(side2);
/*  798 */           g2.setPaint(outlinePaint);
/*  799 */           g2.draw(side2);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */     }
/*  805 */     else if (extent > 0.0D)
/*      */     {
/*  807 */       if (isAngleAtFront(start))
/*      */       {
/*  809 */         if (!isAngleAtBack(end))
/*      */         {
/*  811 */           if (extent < 180.0D) {
/*  812 */             if (drawFront) {
/*  813 */               Area side = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), arc.getEndPoint().getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*  818 */               side.intersect(front);
/*  819 */               g2.setPaint(paint);
/*  820 */               g2.fill(side);
/*  821 */               g2.setPaint(outlinePaint);
/*  822 */               g2.draw(side);
/*      */             }
/*      */           }
/*      */           else {
/*  826 */             Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  830 */             side1.intersect(front);
/*      */             
/*  832 */             Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  836 */             side2.intersect(front);
/*      */             
/*  838 */             g2.setPaint(paint);
/*  839 */             if (drawFront) {
/*  840 */               g2.fill(side1);
/*  841 */               g2.fill(side2);
/*      */             }
/*      */             
/*  844 */             if (drawBack) {
/*  845 */               g2.fill(back);
/*      */             }
/*      */             
/*  848 */             g2.setPaint(outlinePaint);
/*  849 */             if (drawFront) {
/*  850 */               g2.draw(side1);
/*  851 */               g2.draw(side2);
/*      */             }
/*      */             
/*  854 */             if (drawBack) {
/*  855 */               g2.draw(back);
/*      */             }
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  861 */           if (drawBack) {
/*  862 */             Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  866 */             side2.intersect(back);
/*  867 */             g2.setPaint(paint);
/*  868 */             g2.fill(side2);
/*  869 */             g2.setPaint(outlinePaint);
/*  870 */             g2.draw(side2);
/*      */           }
/*      */           
/*  873 */           if (drawFront) {
/*  874 */             Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*  878 */             side1.intersect(front);
/*  879 */             g2.setPaint(paint);
/*  880 */             g2.fill(side1);
/*  881 */             g2.setPaint(outlinePaint);
/*  882 */             g2.draw(side1);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*  888 */       else if (!isAngleAtFront(end)) {
/*  889 */         if (extent < 180.0D) {
/*  890 */           if (drawBack) {
/*  891 */             Area side = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), arc.getStartPoint().getX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  896 */             side.intersect(back);
/*  897 */             g2.setPaint(paint);
/*  898 */             g2.fill(side);
/*  899 */             g2.setPaint(outlinePaint);
/*  900 */             g2.draw(side);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  905 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  909 */           side1.intersect(back);
/*      */           
/*  911 */           Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  915 */           side2.intersect(back);
/*      */           
/*  917 */           g2.setPaint(paint);
/*  918 */           if (drawBack) {
/*  919 */             g2.fill(side1);
/*  920 */             g2.fill(side2);
/*      */           }
/*      */           
/*  923 */           if (drawFront) {
/*  924 */             g2.fill(front);
/*      */           }
/*      */           
/*  927 */           g2.setPaint(outlinePaint);
/*  928 */           if (drawBack) {
/*  929 */             g2.draw(side1);
/*  930 */             g2.draw(side2);
/*      */           }
/*      */           
/*  933 */           if (drawFront) {
/*  934 */             g2.draw(front);
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  941 */         if (drawBack) {
/*  942 */           Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  946 */           side1.intersect(back);
/*  947 */           g2.setPaint(paint);
/*  948 */           g2.fill(side1);
/*  949 */           g2.setPaint(outlinePaint);
/*  950 */           g2.draw(side1);
/*      */         }
/*      */         
/*  953 */         if (drawFront) {
/*  954 */           Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/*      */           
/*      */ 
/*      */ 
/*  958 */           side2.intersect(front);
/*  959 */           g2.setPaint(paint);
/*  960 */           g2.fill(side2);
/*  961 */           g2.setPaint(outlinePaint);
/*  962 */           g2.draw(side2);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  977 */     return localizationResources.getString("Pie_3D_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isAngleAtFront(double angle)
/*      */   {
/*  990 */     return Math.sin(Math.toRadians(angle)) < 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isAngleAtBack(double angle)
/*      */   {
/* 1003 */     return Math.sin(Math.toRadians(angle)) > 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1014 */     if (obj == this) {
/* 1015 */       return true;
/*      */     }
/* 1017 */     if (!(obj instanceof PiePlot3D)) {
/* 1018 */       return false;
/*      */     }
/* 1020 */     PiePlot3D that = (PiePlot3D)obj;
/* 1021 */     if (this.depthFactor != that.depthFactor) {
/* 1022 */       return false;
/*      */     }
/* 1024 */     if (this.darkerSides != that.darkerSides) {
/* 1025 */       return false;
/*      */     }
/* 1027 */     return super.equals(obj);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PiePlot3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */