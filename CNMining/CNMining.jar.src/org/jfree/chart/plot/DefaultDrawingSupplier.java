/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.jfree.chart.ChartColor;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultDrawingSupplier
/*     */   implements DrawingSupplier, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7339847061039422538L;
/*  82 */   public static final Paint[] DEFAULT_PAINT_SEQUENCE = ;
/*     */   
/*     */ 
/*     */ 
/*  86 */   public static final Paint[] DEFAULT_OUTLINE_PAINT_SEQUENCE = { Color.lightGray };
/*     */   
/*     */ 
/*     */ 
/*  90 */   public static final Paint[] DEFAULT_FILL_PAINT_SEQUENCE = { Color.white };
/*     */   
/*     */ 
/*     */ 
/*  94 */   public static final Stroke[] DEFAULT_STROKE_SEQUENCE = { new BasicStroke(1.0F, 2, 2) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  99 */   public static final Stroke[] DEFAULT_OUTLINE_STROKE_SEQUENCE = { new BasicStroke(1.0F, 2, 2) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 104 */   public static final Shape[] DEFAULT_SHAPE_SEQUENCE = createStandardSeriesShapes();
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint[] paintSequence;
/*     */   
/*     */ 
/*     */ 
/*     */   private int paintIndex;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint[] outlinePaintSequence;
/*     */   
/*     */ 
/*     */   private int outlinePaintIndex;
/*     */   
/*     */ 
/*     */   private transient Paint[] fillPaintSequence;
/*     */   
/*     */ 
/*     */   private int fillPaintIndex;
/*     */   
/*     */ 
/*     */   private transient Stroke[] strokeSequence;
/*     */   
/*     */ 
/*     */   private int strokeIndex;
/*     */   
/*     */ 
/*     */   private transient Stroke[] outlineStrokeSequence;
/*     */   
/*     */ 
/*     */   private int outlineStrokeIndex;
/*     */   
/*     */ 
/*     */   private transient Shape[] shapeSequence;
/*     */   
/*     */ 
/*     */   private int shapeIndex;
/*     */   
/*     */ 
/*     */ 
/*     */   public DefaultDrawingSupplier()
/*     */   {
/* 149 */     this(DEFAULT_PAINT_SEQUENCE, DEFAULT_FILL_PAINT_SEQUENCE, DEFAULT_OUTLINE_PAINT_SEQUENCE, DEFAULT_STROKE_SEQUENCE, DEFAULT_OUTLINE_STROKE_SEQUENCE, DEFAULT_SHAPE_SEQUENCE);
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
/*     */   public DefaultDrawingSupplier(Paint[] paintSequence, Paint[] outlinePaintSequence, Stroke[] strokeSequence, Stroke[] outlineStrokeSequence, Shape[] shapeSequence)
/*     */   {
/* 172 */     this.paintSequence = paintSequence;
/* 173 */     this.fillPaintSequence = DEFAULT_FILL_PAINT_SEQUENCE;
/* 174 */     this.outlinePaintSequence = outlinePaintSequence;
/* 175 */     this.strokeSequence = strokeSequence;
/* 176 */     this.outlineStrokeSequence = outlineStrokeSequence;
/* 177 */     this.shapeSequence = shapeSequence;
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
/*     */   public DefaultDrawingSupplier(Paint[] paintSequence, Paint[] fillPaintSequence, Paint[] outlinePaintSequence, Stroke[] strokeSequence, Stroke[] outlineStrokeSequence, Shape[] shapeSequence)
/*     */   {
/* 198 */     this.paintSequence = paintSequence;
/* 199 */     this.fillPaintSequence = fillPaintSequence;
/* 200 */     this.outlinePaintSequence = outlinePaintSequence;
/* 201 */     this.strokeSequence = strokeSequence;
/* 202 */     this.outlineStrokeSequence = outlineStrokeSequence;
/* 203 */     this.shapeSequence = shapeSequence;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getNextPaint()
/*     */   {
/* 212 */     Paint result = this.paintSequence[(this.paintIndex % this.paintSequence.length)];
/*     */     
/* 214 */     this.paintIndex += 1;
/* 215 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getNextOutlinePaint()
/*     */   {
/* 224 */     Paint result = this.outlinePaintSequence[(this.outlinePaintIndex % this.outlinePaintSequence.length)];
/*     */     
/* 226 */     this.outlinePaintIndex += 1;
/* 227 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getNextFillPaint()
/*     */   {
/* 238 */     Paint result = this.fillPaintSequence[(this.fillPaintIndex % this.fillPaintSequence.length)];
/*     */     
/* 240 */     this.fillPaintIndex += 1;
/* 241 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getNextStroke()
/*     */   {
/* 250 */     Stroke result = this.strokeSequence[(this.strokeIndex % this.strokeSequence.length)];
/*     */     
/* 252 */     this.strokeIndex += 1;
/* 253 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getNextOutlineStroke()
/*     */   {
/* 262 */     Stroke result = this.outlineStrokeSequence[(this.outlineStrokeIndex % this.outlineStrokeSequence.length)];
/*     */     
/* 264 */     this.outlineStrokeIndex += 1;
/* 265 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getNextShape()
/*     */   {
/* 274 */     Shape result = this.shapeSequence[(this.shapeIndex % this.shapeSequence.length)];
/*     */     
/* 276 */     this.shapeIndex += 1;
/* 277 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Shape[] createStandardSeriesShapes()
/*     */   {
/* 288 */     Shape[] result = new Shape[10];
/*     */     
/* 290 */     double size = 6.0D;
/* 291 */     double delta = size / 2.0D;
/* 292 */     int[] xpoints = null;
/* 293 */     int[] ypoints = null;
/*     */     
/*     */ 
/* 296 */     result[0] = new Rectangle2D.Double(-delta, -delta, size, size);
/*     */     
/* 298 */     result[1] = new Ellipse2D.Double(-delta, -delta, size, size);
/*     */     
/*     */ 
/* 301 */     xpoints = intArray(0.0D, delta, -delta);
/* 302 */     ypoints = intArray(-delta, delta, delta);
/* 303 */     result[2] = new Polygon(xpoints, ypoints, 3);
/*     */     
/*     */ 
/* 306 */     xpoints = intArray(0.0D, delta, 0.0D, -delta);
/* 307 */     ypoints = intArray(-delta, 0.0D, delta, 0.0D);
/* 308 */     result[3] = new Polygon(xpoints, ypoints, 4);
/*     */     
/*     */ 
/* 311 */     result[4] = new Rectangle2D.Double(-delta, -delta / 2.0D, size, size / 2.0D);
/*     */     
/*     */ 
/* 314 */     xpoints = intArray(-delta, delta, 0.0D);
/* 315 */     ypoints = intArray(-delta, -delta, delta);
/* 316 */     result[5] = new Polygon(xpoints, ypoints, 3);
/*     */     
/*     */ 
/* 319 */     result[6] = new Ellipse2D.Double(-delta, -delta / 2.0D, size, size / 2.0D);
/*     */     
/*     */ 
/* 322 */     xpoints = intArray(-delta, delta, -delta);
/* 323 */     ypoints = intArray(-delta, 0.0D, delta);
/* 324 */     result[7] = new Polygon(xpoints, ypoints, 3);
/*     */     
/*     */ 
/* 327 */     result[8] = new Rectangle2D.Double(-delta / 2.0D, -delta, size / 2.0D, size);
/*     */     
/*     */ 
/* 330 */     xpoints = intArray(-delta, delta, delta);
/* 331 */     ypoints = intArray(0.0D, -delta, delta);
/* 332 */     result[9] = new Polygon(xpoints, ypoints, 3);
/*     */     
/* 334 */     return result;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 347 */     if (obj == this) {
/* 348 */       return true;
/*     */     }
/*     */     
/* 351 */     if (!(obj instanceof DefaultDrawingSupplier)) {
/* 352 */       return false;
/*     */     }
/*     */     
/* 355 */     DefaultDrawingSupplier that = (DefaultDrawingSupplier)obj;
/*     */     
/* 357 */     if (!Arrays.equals(this.paintSequence, that.paintSequence)) {
/* 358 */       return false;
/*     */     }
/* 360 */     if (this.paintIndex != that.paintIndex) {
/* 361 */       return false;
/*     */     }
/* 363 */     if (!Arrays.equals(this.outlinePaintSequence, that.outlinePaintSequence))
/*     */     {
/* 365 */       return false;
/*     */     }
/* 367 */     if (this.outlinePaintIndex != that.outlinePaintIndex) {
/* 368 */       return false;
/*     */     }
/* 370 */     if (!Arrays.equals(this.strokeSequence, that.strokeSequence)) {
/* 371 */       return false;
/*     */     }
/* 373 */     if (this.strokeIndex != that.strokeIndex) {
/* 374 */       return false;
/*     */     }
/* 376 */     if (!Arrays.equals(this.outlineStrokeSequence, that.outlineStrokeSequence))
/*     */     {
/* 378 */       return false;
/*     */     }
/* 380 */     if (this.outlineStrokeIndex != that.outlineStrokeIndex) {
/* 381 */       return false;
/*     */     }
/* 383 */     if (!equalShapes(this.shapeSequence, that.shapeSequence)) {
/* 384 */       return false;
/*     */     }
/* 386 */     if (this.shapeIndex != that.shapeIndex) {
/* 387 */       return false;
/*     */     }
/* 389 */     return true;
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
/*     */   private boolean equalShapes(Shape[] s1, Shape[] s2)
/*     */   {
/* 402 */     if (s1 == null) {
/* 403 */       return s2 == null;
/*     */     }
/* 405 */     if (s2 == null) {
/* 406 */       return false;
/*     */     }
/* 408 */     if (s1.length != s2.length) {
/* 409 */       return false;
/*     */     }
/* 411 */     for (int i = 0; i < s1.length; i++) {
/* 412 */       if (!ShapeUtilities.equal(s1[i], s2[i])) {
/* 413 */         return false;
/*     */       }
/*     */     }
/* 416 */     return true;
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
/* 427 */     stream.defaultWriteObject();
/*     */     
/* 429 */     int paintCount = this.paintSequence.length;
/* 430 */     stream.writeInt(paintCount);
/* 431 */     for (int i = 0; i < paintCount; i++) {
/* 432 */       SerialUtilities.writePaint(this.paintSequence[i], stream);
/*     */     }
/*     */     
/* 435 */     int outlinePaintCount = this.outlinePaintSequence.length;
/* 436 */     stream.writeInt(outlinePaintCount);
/* 437 */     for (int i = 0; i < outlinePaintCount; i++) {
/* 438 */       SerialUtilities.writePaint(this.outlinePaintSequence[i], stream);
/*     */     }
/*     */     
/* 441 */     int strokeCount = this.strokeSequence.length;
/* 442 */     stream.writeInt(strokeCount);
/* 443 */     for (int i = 0; i < strokeCount; i++) {
/* 444 */       SerialUtilities.writeStroke(this.strokeSequence[i], stream);
/*     */     }
/*     */     
/* 447 */     int outlineStrokeCount = this.outlineStrokeSequence.length;
/* 448 */     stream.writeInt(outlineStrokeCount);
/* 449 */     for (int i = 0; i < outlineStrokeCount; i++) {
/* 450 */       SerialUtilities.writeStroke(this.outlineStrokeSequence[i], stream);
/*     */     }
/*     */     
/* 453 */     int shapeCount = this.shapeSequence.length;
/* 454 */     stream.writeInt(shapeCount);
/* 455 */     for (int i = 0; i < shapeCount; i++) {
/* 456 */       SerialUtilities.writeShape(this.shapeSequence[i], stream);
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 471 */     stream.defaultReadObject();
/*     */     
/* 473 */     int paintCount = stream.readInt();
/* 474 */     this.paintSequence = new Paint[paintCount];
/* 475 */     for (int i = 0; i < paintCount; i++) {
/* 476 */       this.paintSequence[i] = SerialUtilities.readPaint(stream);
/*     */     }
/*     */     
/* 479 */     int outlinePaintCount = stream.readInt();
/* 480 */     this.outlinePaintSequence = new Paint[outlinePaintCount];
/* 481 */     for (int i = 0; i < outlinePaintCount; i++) {
/* 482 */       this.outlinePaintSequence[i] = SerialUtilities.readPaint(stream);
/*     */     }
/*     */     
/* 485 */     int strokeCount = stream.readInt();
/* 486 */     this.strokeSequence = new Stroke[strokeCount];
/* 487 */     for (int i = 0; i < strokeCount; i++) {
/* 488 */       this.strokeSequence[i] = SerialUtilities.readStroke(stream);
/*     */     }
/*     */     
/* 491 */     int outlineStrokeCount = stream.readInt();
/* 492 */     this.outlineStrokeSequence = new Stroke[outlineStrokeCount];
/* 493 */     for (int i = 0; i < outlineStrokeCount; i++) {
/* 494 */       this.outlineStrokeSequence[i] = SerialUtilities.readStroke(stream);
/*     */     }
/*     */     
/* 497 */     int shapeCount = stream.readInt();
/* 498 */     this.shapeSequence = new Shape[shapeCount];
/* 499 */     for (int i = 0; i < shapeCount; i++) {
/* 500 */       this.shapeSequence[i] = SerialUtilities.readShape(stream);
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
/*     */   private static int[] intArray(double a, double b, double c)
/*     */   {
/* 516 */     return new int[] { (int)a, (int)b, (int)c };
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
/*     */   private static int[] intArray(double a, double b, double c, double d)
/*     */   {
/* 531 */     return new int[] { (int)a, (int)b, (int)c, (int)d };
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
/* 543 */     DefaultDrawingSupplier clone = (DefaultDrawingSupplier)super.clone();
/* 544 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/DefaultDrawingSupplier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */