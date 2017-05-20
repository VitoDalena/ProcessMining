/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D.Double;
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
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.PieSectionEntity;
/*     */ import org.jfree.chart.labels.PieToolTipGenerator;
/*     */ import org.jfree.chart.urls.PieURLGenerator;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.Rotation;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ import org.jfree.util.UnitType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RingPlot
/*     */   extends PiePlot
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1556064784129676620L;
/*     */   private boolean separatorsVisible;
/*     */   private transient Stroke separatorStroke;
/*     */   private transient Paint separatorPaint;
/*     */   private double innerSeparatorExtension;
/*     */   private double outerSeparatorExtension;
/*     */   private double sectionDepth;
/*     */   
/*     */   public RingPlot()
/*     */   {
/* 122 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RingPlot(PieDataset dataset)
/*     */   {
/* 131 */     super(dataset);
/* 132 */     this.separatorsVisible = true;
/* 133 */     this.separatorStroke = new BasicStroke(0.5F);
/* 134 */     this.separatorPaint = Color.gray;
/* 135 */     this.innerSeparatorExtension = 0.2D;
/* 136 */     this.outerSeparatorExtension = 0.2D;
/* 137 */     this.sectionDepth = 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getSeparatorsVisible()
/*     */   {
/* 149 */     return this.separatorsVisible;
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
/*     */   public void setSeparatorsVisible(boolean visible)
/*     */   {
/* 162 */     this.separatorsVisible = visible;
/* 163 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getSeparatorStroke()
/*     */   {
/* 174 */     return this.separatorStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeparatorStroke(Stroke stroke)
/*     */   {
/* 186 */     if (stroke == null) {
/* 187 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 189 */     this.separatorStroke = stroke;
/* 190 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getSeparatorPaint()
/*     */   {
/* 201 */     return this.separatorPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeparatorPaint(Paint paint)
/*     */   {
/* 213 */     if (paint == null) {
/* 214 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 216 */     this.separatorPaint = paint;
/* 217 */     fireChangeEvent();
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
/*     */   public double getInnerSeparatorExtension()
/*     */   {
/* 230 */     return this.innerSeparatorExtension;
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
/*     */   public void setInnerSeparatorExtension(double percent)
/*     */   {
/* 245 */     this.innerSeparatorExtension = percent;
/* 246 */     fireChangeEvent();
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
/*     */   public double getOuterSeparatorExtension()
/*     */   {
/* 259 */     return this.outerSeparatorExtension;
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
/*     */   public void setOuterSeparatorExtension(double percent)
/*     */   {
/* 273 */     this.outerSeparatorExtension = percent;
/* 274 */     fireChangeEvent();
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
/*     */   public double getSectionDepth()
/*     */   {
/* 287 */     return this.sectionDepth;
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
/*     */   public void setSectionDepth(double sectionDepth)
/*     */   {
/* 300 */     this.sectionDepth = sectionDepth;
/* 301 */     fireChangeEvent();
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
/*     */   public PiePlotState initialise(Graphics2D g2, Rectangle2D plotArea, PiePlot plot, Integer index, PlotRenderingInfo info)
/*     */   {
/* 322 */     PiePlotState state = super.initialise(g2, plotArea, plot, index, info);
/* 323 */     state.setPassesRequired(3);
/* 324 */     return state;
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
/*     */   protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass)
/*     */   {
/* 343 */     PieDataset dataset = getDataset();
/* 344 */     Number n = dataset.getValue(section);
/* 345 */     if (n == null) {
/* 346 */       return;
/*     */     }
/* 348 */     double value = n.doubleValue();
/* 349 */     double angle1 = 0.0D;
/* 350 */     double angle2 = 0.0D;
/*     */     
/* 352 */     Rotation direction = getDirection();
/* 353 */     if (direction == Rotation.CLOCKWISE) {
/* 354 */       angle1 = state.getLatestAngle();
/* 355 */       angle2 = angle1 - value / state.getTotal() * 360.0D;
/*     */     }
/* 357 */     else if (direction == Rotation.ANTICLOCKWISE) {
/* 358 */       angle1 = state.getLatestAngle();
/* 359 */       angle2 = angle1 + value / state.getTotal() * 360.0D;
/*     */     }
/*     */     else {
/* 362 */       throw new IllegalStateException("Rotation type not recognised.");
/*     */     }
/*     */     
/* 365 */     double angle = angle2 - angle1;
/* 366 */     if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
/* 367 */       Comparable key = getSectionKey(section);
/* 368 */       double ep = 0.0D;
/* 369 */       double mep = getMaximumExplodePercent();
/* 370 */       if (mep > 0.0D) {
/* 371 */         ep = getExplodePercent(key) / mep;
/*     */       }
/* 373 */       Rectangle2D arcBounds = getArcBounds(state.getPieArea(), state.getExplodedPieArea(), angle1, angle, ep);
/*     */       
/* 375 */       Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 0);
/*     */       
/*     */ 
/*     */ 
/* 379 */       double depth = this.sectionDepth / 2.0D;
/* 380 */       RectangleInsets s = new RectangleInsets(UnitType.RELATIVE, depth, depth, depth, depth);
/*     */       
/* 382 */       Rectangle2D innerArcBounds = new Rectangle2D.Double();
/* 383 */       innerArcBounds.setRect(arcBounds);
/* 384 */       s.trim(innerArcBounds);
/*     */       
/*     */ 
/* 387 */       Arc2D.Double arc2 = new Arc2D.Double(innerArcBounds, angle1 + angle, -angle, 0);
/*     */       
/* 389 */       GeneralPath path = new GeneralPath();
/* 390 */       path.moveTo((float)arc.getStartPoint().getX(), (float)arc.getStartPoint().getY());
/*     */       
/* 392 */       path.append(arc.getPathIterator(null), false);
/* 393 */       path.append(arc2.getPathIterator(null), true);
/* 394 */       path.closePath();
/*     */       
/* 396 */       Line2D separator = new Line2D.Double(arc2.getEndPoint(), arc.getStartPoint());
/*     */       
/*     */ 
/* 399 */       if (currentPass == 0) {
/* 400 */         Paint shadowPaint = getShadowPaint();
/* 401 */         double shadowXOffset = getShadowXOffset();
/* 402 */         double shadowYOffset = getShadowYOffset();
/* 403 */         if (shadowPaint != null) {
/* 404 */           Shape shadowArc = ShapeUtilities.createTranslatedShape(path, (float)shadowXOffset, (float)shadowYOffset);
/*     */           
/* 406 */           g2.setPaint(shadowPaint);
/* 407 */           g2.fill(shadowArc);
/*     */         }
/*     */       }
/* 410 */       else if (currentPass == 1) {
/* 411 */         Paint paint = lookupSectionPaint(key);
/* 412 */         g2.setPaint(paint);
/* 413 */         g2.fill(path);
/* 414 */         Paint outlinePaint = lookupSectionOutlinePaint(key);
/* 415 */         Stroke outlineStroke = lookupSectionOutlineStroke(key);
/* 416 */         if ((outlinePaint != null) && (outlineStroke != null)) {
/* 417 */           g2.setPaint(outlinePaint);
/* 418 */           g2.setStroke(outlineStroke);
/* 419 */           g2.draw(path);
/*     */         }
/*     */         
/*     */ 
/* 423 */         if (state.getInfo() != null) {
/* 424 */           EntityCollection entities = state.getEntityCollection();
/* 425 */           if (entities != null) {
/* 426 */             String tip = null;
/* 427 */             PieToolTipGenerator toolTipGenerator = getToolTipGenerator();
/*     */             
/* 429 */             if (toolTipGenerator != null) {
/* 430 */               tip = toolTipGenerator.generateToolTip(dataset, key);
/*     */             }
/*     */             
/* 433 */             String url = null;
/* 434 */             PieURLGenerator urlGenerator = getURLGenerator();
/* 435 */             if (urlGenerator != null) {
/* 436 */               url = urlGenerator.generateURL(dataset, key, getPieIndex());
/*     */             }
/*     */             
/* 439 */             PieSectionEntity entity = new PieSectionEntity(path, dataset, getPieIndex(), section, key, tip, url);
/*     */             
/*     */ 
/* 442 */             entities.add(entity);
/*     */           }
/*     */         }
/*     */       }
/* 446 */       else if ((currentPass == 2) && 
/* 447 */         (this.separatorsVisible)) {
/* 448 */         Line2D extendedSeparator = extendLine(separator, this.innerSeparatorExtension, this.outerSeparatorExtension);
/*     */         
/*     */ 
/* 451 */         g2.setStroke(this.separatorStroke);
/* 452 */         g2.setPaint(this.separatorPaint);
/* 453 */         g2.draw(extendedSeparator);
/*     */       }
/*     */     }
/*     */     
/* 457 */     state.setLatestAngle(angle2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getLabelLinkDepth()
/*     */   {
/* 467 */     return Math.min(super.getLabelLinkDepth(), getSectionDepth() / 2.0D);
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
/* 478 */     if (this == obj) {
/* 479 */       return true;
/*     */     }
/* 481 */     if (!(obj instanceof RingPlot)) {
/* 482 */       return false;
/*     */     }
/* 484 */     RingPlot that = (RingPlot)obj;
/* 485 */     if (this.separatorsVisible != that.separatorsVisible) {
/* 486 */       return false;
/*     */     }
/* 488 */     if (!ObjectUtilities.equal(this.separatorStroke, that.separatorStroke))
/*     */     {
/* 490 */       return false;
/*     */     }
/* 492 */     if (!PaintUtilities.equal(this.separatorPaint, that.separatorPaint)) {
/* 493 */       return false;
/*     */     }
/* 495 */     if (this.innerSeparatorExtension != that.innerSeparatorExtension) {
/* 496 */       return false;
/*     */     }
/* 498 */     if (this.outerSeparatorExtension != that.outerSeparatorExtension) {
/* 499 */       return false;
/*     */     }
/* 501 */     if (this.sectionDepth != that.sectionDepth) {
/* 502 */       return false;
/*     */     }
/* 504 */     return super.equals(obj);
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
/*     */   private Line2D extendLine(Line2D line, double startPercent, double endPercent)
/*     */   {
/* 519 */     if (line == null) {
/* 520 */       throw new IllegalArgumentException("Null 'line' argument.");
/*     */     }
/* 522 */     double x1 = line.getX1();
/* 523 */     double x2 = line.getX2();
/* 524 */     double deltaX = x2 - x1;
/* 525 */     double y1 = line.getY1();
/* 526 */     double y2 = line.getY2();
/* 527 */     double deltaY = y2 - y1;
/* 528 */     x1 -= startPercent * deltaX;
/* 529 */     y1 -= startPercent * deltaY;
/* 530 */     x2 += endPercent * deltaX;
/* 531 */     y2 += endPercent * deltaY;
/* 532 */     return new Line2D.Double(x1, y1, x2, y2);
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
/* 543 */     stream.defaultWriteObject();
/* 544 */     SerialUtilities.writeStroke(this.separatorStroke, stream);
/* 545 */     SerialUtilities.writePaint(this.separatorPaint, stream);
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
/* 558 */     stream.defaultReadObject();
/* 559 */     this.separatorStroke = SerialUtilities.readStroke(stream);
/* 560 */     this.separatorPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/RingPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */