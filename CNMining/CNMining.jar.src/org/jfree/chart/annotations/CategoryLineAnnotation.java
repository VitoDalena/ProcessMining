/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.CategoryAnchor;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ public class CategoryLineAnnotation
/*     */   implements CategoryAnnotation, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3477740483341587984L;
/*     */   private Comparable category1;
/*     */   private double value1;
/*     */   private Comparable category2;
/*     */   private double value2;
/*  93 */   private transient Paint paint = Color.black;
/*     */   
/*     */ 
/*  96 */   private transient Stroke stroke = new BasicStroke(1.0F);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryLineAnnotation(Comparable category1, double value1, Comparable category2, double value2, Paint paint, Stroke stroke)
/*     */   {
/* 112 */     if (category1 == null) {
/* 113 */       throw new IllegalArgumentException("Null 'category1' argument.");
/*     */     }
/* 115 */     if (category2 == null) {
/* 116 */       throw new IllegalArgumentException("Null 'category2' argument.");
/*     */     }
/* 118 */     if (paint == null) {
/* 119 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 121 */     if (stroke == null) {
/* 122 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 124 */     this.category1 = category1;
/* 125 */     this.value1 = value1;
/* 126 */     this.category2 = category2;
/* 127 */     this.value2 = value2;
/* 128 */     this.paint = paint;
/* 129 */     this.stroke = stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getCategory1()
/*     */   {
/* 140 */     return this.category1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCategory1(Comparable category)
/*     */   {
/* 151 */     if (category == null) {
/* 152 */       throw new IllegalArgumentException("Null 'category' argument.");
/*     */     }
/* 154 */     this.category1 = category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue1()
/*     */   {
/* 165 */     return this.value1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue1(double value)
/*     */   {
/* 176 */     this.value1 = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getCategory2()
/*     */   {
/* 187 */     return this.category2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCategory2(Comparable category)
/*     */   {
/* 198 */     if (category == null) {
/* 199 */       throw new IllegalArgumentException("Null 'category' argument.");
/*     */     }
/* 201 */     this.category2 = category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue2()
/*     */   {
/* 212 */     return this.value2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue2(double value)
/*     */   {
/* 223 */     this.value2 = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 234 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 245 */     if (paint == null) {
/* 246 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 248 */     this.paint = paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 259 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke stroke)
/*     */   {
/* 270 */     if (stroke == null) {
/* 271 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 273 */     this.stroke = stroke;
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
/*     */   public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, CategoryAxis domainAxis, ValueAxis rangeAxis)
/*     */   {
/* 288 */     CategoryDataset dataset = plot.getDataset();
/* 289 */     int catIndex1 = dataset.getColumnIndex(this.category1);
/* 290 */     int catIndex2 = dataset.getColumnIndex(this.category2);
/* 291 */     int catCount = dataset.getColumnCount();
/*     */     
/* 293 */     double lineX1 = 0.0D;
/* 294 */     double lineY1 = 0.0D;
/* 295 */     double lineX2 = 0.0D;
/* 296 */     double lineY2 = 0.0D;
/* 297 */     PlotOrientation orientation = plot.getOrientation();
/* 298 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 300 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/* 303 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 304 */       lineY1 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 307 */       lineX1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
/* 308 */       lineY2 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 311 */       lineX2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
/*     */     }
/* 313 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 314 */       lineX1 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 317 */       lineY1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
/* 318 */       lineX2 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 321 */       lineY2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
/*     */     }
/* 323 */     g2.setPaint(this.paint);
/* 324 */     g2.setStroke(this.stroke);
/* 325 */     g2.drawLine((int)lineX1, (int)lineY1, (int)lineX2, (int)lineY2);
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
/* 336 */     if (obj == this) {
/* 337 */       return true;
/*     */     }
/* 339 */     if (!(obj instanceof CategoryLineAnnotation)) {
/* 340 */       return false;
/*     */     }
/* 342 */     CategoryLineAnnotation that = (CategoryLineAnnotation)obj;
/* 343 */     if (!this.category1.equals(that.getCategory1())) {
/* 344 */       return false;
/*     */     }
/* 346 */     if (this.value1 != that.getValue1()) {
/* 347 */       return false;
/*     */     }
/* 349 */     if (!this.category2.equals(that.getCategory2())) {
/* 350 */       return false;
/*     */     }
/* 352 */     if (this.value2 != that.getValue2()) {
/* 353 */       return false;
/*     */     }
/* 355 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 356 */       return false;
/*     */     }
/* 358 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 359 */       return false;
/*     */     }
/* 361 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 370 */     int result = 193;
/* 371 */     result = 37 * result + this.category1.hashCode();
/* 372 */     long temp = Double.doubleToLongBits(this.value1);
/* 373 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 374 */     result = 37 * result + this.category2.hashCode();
/* 375 */     temp = Double.doubleToLongBits(this.value2);
/* 376 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 377 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 378 */     result = 37 * result + this.stroke.hashCode();
/* 379 */     return result;
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
/* 391 */     return super.clone();
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
/* 402 */     stream.defaultWriteObject();
/* 403 */     SerialUtilities.writePaint(this.paint, stream);
/* 404 */     SerialUtilities.writeStroke(this.stroke, stream);
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
/* 417 */     stream.defaultReadObject();
/* 418 */     this.paint = SerialUtilities.readPaint(stream);
/* 419 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/CategoryLineAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */