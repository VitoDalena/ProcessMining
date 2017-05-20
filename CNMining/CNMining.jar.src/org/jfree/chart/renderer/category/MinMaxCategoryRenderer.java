/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.swing.Icon;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinMaxCategoryRenderer
/*     */   extends AbstractCategoryItemRenderer
/*     */ {
/*     */   private static final long serialVersionUID = 2935615937671064911L;
/* 108 */   private boolean plotLines = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 113 */   private transient Paint groupPaint = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 118 */   private transient Stroke groupStroke = new BasicStroke(1.0F);
/*     */   
/*     */ 
/* 121 */   private transient Icon minIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), null, Color.black);
/*     */   
/*     */ 
/*     */ 
/* 125 */   private transient Icon maxIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), null, Color.black);
/*     */   
/*     */ 
/*     */ 
/* 129 */   private transient Icon objectIcon = getIcon(new Line2D.Double(-4.0D, 0.0D, 4.0D, 0.0D), false, true);
/*     */   
/*     */ 
/*     */ 
/* 133 */   private int lastCategory = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double min;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double max;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDrawLines()
/*     */   {
/* 157 */     return this.plotLines;
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
/*     */   public void setDrawLines(boolean draw)
/*     */   {
/* 170 */     if (this.plotLines != draw) {
/* 171 */       this.plotLines = draw;
/* 172 */       fireChangeEvent();
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
/*     */   public Paint getGroupPaint()
/*     */   {
/* 186 */     return this.groupPaint;
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
/*     */   public void setGroupPaint(Paint paint)
/*     */   {
/* 199 */     if (paint == null) {
/* 200 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 202 */     this.groupPaint = paint;
/* 203 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getGroupStroke()
/*     */   {
/* 215 */     return this.groupStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGroupStroke(Stroke stroke)
/*     */   {
/* 226 */     if (stroke == null) {
/* 227 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 229 */     this.groupStroke = stroke;
/* 230 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Icon getObjectIcon()
/*     */   {
/* 241 */     return this.objectIcon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setObjectIcon(Icon icon)
/*     */   {
/* 253 */     if (icon == null) {
/* 254 */       throw new IllegalArgumentException("Null 'icon' argument.");
/*     */     }
/* 256 */     this.objectIcon = icon;
/* 257 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Icon getMaxIcon()
/*     */   {
/* 269 */     return this.maxIcon;
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
/*     */   public void setMaxIcon(Icon icon)
/*     */   {
/* 282 */     if (icon == null) {
/* 283 */       throw new IllegalArgumentException("Null 'icon' argument.");
/*     */     }
/* 285 */     this.maxIcon = icon;
/* 286 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Icon getMinIcon()
/*     */   {
/* 298 */     return this.minIcon;
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
/*     */   public void setMinIcon(Icon icon)
/*     */   {
/* 311 */     if (icon == null) {
/* 312 */       throw new IllegalArgumentException("Null 'icon' argument.");
/*     */     }
/* 314 */     this.minIcon = icon;
/* 315 */     fireChangeEvent();
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 338 */     Number value = dataset.getValue(row, column);
/* 339 */     if (value != null)
/*     */     {
/* 341 */       double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */       
/* 343 */       double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, plot.getRangeAxisEdge());
/*     */       
/* 345 */       g2.setPaint(getItemPaint(row, column));
/* 346 */       g2.setStroke(getItemStroke(row, column));
/* 347 */       Shape shape = null;
/* 348 */       shape = new Rectangle2D.Double(x1 - 4.0D, y1 - 4.0D, 8.0D, 8.0D);
/*     */       
/* 350 */       PlotOrientation orient = plot.getOrientation();
/* 351 */       if (orient == PlotOrientation.VERTICAL) {
/* 352 */         this.objectIcon.paintIcon(null, g2, (int)x1, (int)y1);
/*     */       }
/*     */       else {
/* 355 */         this.objectIcon.paintIcon(null, g2, (int)y1, (int)x1);
/*     */       }
/*     */       
/* 358 */       if (this.lastCategory == column) {
/* 359 */         if (this.min > value.doubleValue()) {
/* 360 */           this.min = value.doubleValue();
/*     */         }
/* 362 */         if (this.max < value.doubleValue()) {
/* 363 */           this.max = value.doubleValue();
/*     */         }
/*     */         
/*     */ 
/* 367 */         if (dataset.getRowCount() - 1 == row) {
/* 368 */           g2.setPaint(this.groupPaint);
/* 369 */           g2.setStroke(this.groupStroke);
/* 370 */           double minY = rangeAxis.valueToJava2D(this.min, dataArea, plot.getRangeAxisEdge());
/*     */           
/* 372 */           double maxY = rangeAxis.valueToJava2D(this.max, dataArea, plot.getRangeAxisEdge());
/*     */           
/*     */ 
/* 375 */           if (orient == PlotOrientation.VERTICAL) {
/* 376 */             g2.draw(new Line2D.Double(x1, minY, x1, maxY));
/* 377 */             this.minIcon.paintIcon(null, g2, (int)x1, (int)minY);
/* 378 */             this.maxIcon.paintIcon(null, g2, (int)x1, (int)maxY);
/*     */           }
/*     */           else {
/* 381 */             g2.draw(new Line2D.Double(minY, x1, maxY, x1));
/* 382 */             this.minIcon.paintIcon(null, g2, (int)minY, (int)x1);
/* 383 */             this.maxIcon.paintIcon(null, g2, (int)maxY, (int)x1);
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 388 */         this.lastCategory = column;
/* 389 */         this.min = value.doubleValue();
/* 390 */         this.max = value.doubleValue();
/*     */       }
/*     */       
/*     */ 
/* 394 */       if ((this.plotLines) && 
/* 395 */         (column != 0)) {
/* 396 */         Number previousValue = dataset.getValue(row, column - 1);
/* 397 */         if (previousValue != null)
/*     */         {
/* 399 */           double previous = previousValue.doubleValue();
/* 400 */           double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */           
/*     */ 
/* 403 */           double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/*     */           
/* 405 */           g2.setPaint(getItemPaint(row, column));
/* 406 */           g2.setStroke(getItemStroke(row, column));
/*     */           Line2D line;
/* 408 */           Line2D line; if (orient == PlotOrientation.VERTICAL) {
/* 409 */             line = new Line2D.Double(x0, y0, x1, y1);
/*     */           }
/*     */           else {
/* 412 */             line = new Line2D.Double(y0, x0, y1, x1);
/*     */           }
/* 414 */           g2.draw(line);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 420 */       EntityCollection entities = state.getEntityCollection();
/* 421 */       if ((entities != null) && (shape != null)) {
/* 422 */         addItemEntity(entities, dataset, row, column, shape);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 439 */     if (obj == this) {
/* 440 */       return true;
/*     */     }
/* 442 */     if (!(obj instanceof MinMaxCategoryRenderer)) {
/* 443 */       return false;
/*     */     }
/* 445 */     MinMaxCategoryRenderer that = (MinMaxCategoryRenderer)obj;
/* 446 */     if (this.plotLines != that.plotLines) {
/* 447 */       return false;
/*     */     }
/* 449 */     if (!PaintUtilities.equal(this.groupPaint, that.groupPaint)) {
/* 450 */       return false;
/*     */     }
/* 452 */     if (!this.groupStroke.equals(that.groupStroke)) {
/* 453 */       return false;
/*     */     }
/* 455 */     return super.equals(obj);
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
/*     */   private Icon getIcon(Shape shape, Paint fillPaint, Paint outlinePaint)
/*     */   {
/* 470 */     int width = shape.getBounds().width;
/* 471 */     int height = shape.getBounds().height;
/* 472 */     GeneralPath path = new GeneralPath(shape);
/* 473 */     new Icon() { private final GeneralPath val$path;
/*     */       
/* 475 */       public void paintIcon(Component c, Graphics g, int x, int y) { Graphics2D g2 = (Graphics2D)g;
/* 476 */         this.val$path.transform(AffineTransform.getTranslateInstance(x, y));
/* 477 */         if (this.val$fillPaint != null) {
/* 478 */           g2.setPaint(this.val$fillPaint);
/* 479 */           g2.fill(this.val$path);
/*     */         }
/* 481 */         if (this.val$outlinePaint != null) {
/* 482 */           g2.setPaint(this.val$outlinePaint);
/* 483 */           g2.draw(this.val$path);
/*     */         }
/* 485 */         this.val$path.transform(AffineTransform.getTranslateInstance(-x, -y));
/*     */       }
/*     */       
/*     */       public int getIconWidth() {
/* 489 */         return this.val$width;
/*     */       }
/*     */       
/*     */       public int getIconHeight() {
/* 493 */         return this.val$height;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final Paint val$fillPaint;
/*     */   
/*     */   private final Paint val$outlinePaint;
/*     */   
/*     */   private final int val$width;
/*     */   
/*     */   private final int val$height;
/*     */   
/*     */   private Icon getIcon(Shape shape, boolean fill, boolean outline)
/*     */   {
/* 510 */     int width = shape.getBounds().width;
/* 511 */     int height = shape.getBounds().height;
/* 512 */     GeneralPath path = new GeneralPath(shape);
/* 513 */     new Icon() { private final GeneralPath val$path;
/*     */       
/* 515 */       public void paintIcon(Component c, Graphics g, int x, int y) { Graphics2D g2 = (Graphics2D)g;
/* 516 */         this.val$path.transform(AffineTransform.getTranslateInstance(x, y));
/* 517 */         if (this.val$fill) {
/* 518 */           g2.fill(this.val$path);
/*     */         }
/* 520 */         if (this.val$outline) {
/* 521 */           g2.draw(this.val$path);
/*     */         }
/* 523 */         this.val$path.transform(AffineTransform.getTranslateInstance(-x, -y));
/*     */       }
/*     */       
/*     */       public int getIconWidth() {
/* 527 */         return this.val$width;
/*     */       }
/*     */       
/*     */       public int getIconHeight() {
/* 531 */         return this.val$height;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final boolean val$fill;
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 544 */     stream.defaultWriteObject();
/* 545 */     SerialUtilities.writeStroke(this.groupStroke, stream);
/* 546 */     SerialUtilities.writePaint(this.groupPaint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */   private final boolean val$outline;
/*     */   
/*     */   private final int val$width;
/*     */   
/*     */   private final int val$height;
/*     */   
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 559 */     stream.defaultReadObject();
/* 560 */     this.groupStroke = SerialUtilities.readStroke(stream);
/* 561 */     this.groupPaint = SerialUtilities.readPaint(stream);
/*     */     
/* 563 */     this.minIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), null, Color.black);
/*     */     
/* 565 */     this.maxIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), null, Color.black);
/*     */     
/* 567 */     this.objectIcon = getIcon(new Line2D.Double(-4.0D, 0.0D, 4.0D, 0.0D), false, true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/MinMaxCategoryRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */