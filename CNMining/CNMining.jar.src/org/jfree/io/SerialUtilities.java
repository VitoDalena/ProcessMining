/*     */ package org.jfree.io;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerialUtilities
/*     */ {
/*     */   public static boolean isSerializable(Class c)
/*     */   {
/* 106 */     return Serializable.class.isAssignableFrom(c);
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
/*     */   public static Paint readPaint(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 123 */     if (stream == null) {
/* 124 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 126 */     Paint result = null;
/* 127 */     boolean isNull = stream.readBoolean();
/* 128 */     if (!isNull) {
/* 129 */       Class c = (Class)stream.readObject();
/* 130 */       if (isSerializable(c)) {
/* 131 */         result = (Paint)stream.readObject();
/*     */       }
/* 133 */       else if (c.equals(GradientPaint.class)) {
/* 134 */         float x1 = stream.readFloat();
/* 135 */         float y1 = stream.readFloat();
/* 136 */         Color c1 = (Color)stream.readObject();
/* 137 */         float x2 = stream.readFloat();
/* 138 */         float y2 = stream.readFloat();
/* 139 */         Color c2 = (Color)stream.readObject();
/* 140 */         boolean isCyclic = stream.readBoolean();
/* 141 */         result = new GradientPaint(x1, y1, c1, x2, y2, c2, isCyclic);
/*     */       }
/*     */     }
/* 144 */     return result;
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
/*     */   public static void writePaint(Paint paint, ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 160 */     if (stream == null) {
/* 161 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 163 */     if (paint != null) {
/* 164 */       stream.writeBoolean(false);
/* 165 */       stream.writeObject(paint.getClass());
/* 166 */       if ((paint instanceof Serializable)) {
/* 167 */         stream.writeObject(paint);
/*     */       }
/* 169 */       else if ((paint instanceof GradientPaint)) {
/* 170 */         GradientPaint gp = (GradientPaint)paint;
/* 171 */         stream.writeFloat((float)gp.getPoint1().getX());
/* 172 */         stream.writeFloat((float)gp.getPoint1().getY());
/* 173 */         stream.writeObject(gp.getColor1());
/* 174 */         stream.writeFloat((float)gp.getPoint2().getX());
/* 175 */         stream.writeFloat((float)gp.getPoint2().getY());
/* 176 */         stream.writeObject(gp.getColor2());
/* 177 */         stream.writeBoolean(gp.isCyclic());
/*     */       }
/*     */     }
/*     */     else {
/* 181 */       stream.writeBoolean(true);
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
/*     */   public static Stroke readStroke(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 200 */     if (stream == null) {
/* 201 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 203 */     Stroke result = null;
/* 204 */     boolean isNull = stream.readBoolean();
/* 205 */     if (!isNull) {
/* 206 */       Class c = (Class)stream.readObject();
/* 207 */       if (c.equals(BasicStroke.class)) {
/* 208 */         float width = stream.readFloat();
/* 209 */         int cap = stream.readInt();
/* 210 */         int join = stream.readInt();
/* 211 */         float miterLimit = stream.readFloat();
/* 212 */         float[] dash = (float[])stream.readObject();
/* 213 */         float dashPhase = stream.readFloat();
/* 214 */         result = new BasicStroke(width, cap, join, miterLimit, dash, dashPhase);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 219 */         result = (Stroke)stream.readObject();
/*     */       }
/*     */     }
/* 222 */     return result;
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
/*     */   public static void writeStroke(Stroke stroke, ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 241 */     if (stream == null) {
/* 242 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 244 */     if (stroke != null) {
/* 245 */       stream.writeBoolean(false);
/* 246 */       if ((stroke instanceof BasicStroke)) {
/* 247 */         BasicStroke s = (BasicStroke)stroke;
/* 248 */         stream.writeObject(BasicStroke.class);
/* 249 */         stream.writeFloat(s.getLineWidth());
/* 250 */         stream.writeInt(s.getEndCap());
/* 251 */         stream.writeInt(s.getLineJoin());
/* 252 */         stream.writeFloat(s.getMiterLimit());
/* 253 */         stream.writeObject(s.getDashArray());
/* 254 */         stream.writeFloat(s.getDashPhase());
/*     */       }
/*     */       else {
/* 257 */         stream.writeObject(stroke.getClass());
/* 258 */         stream.writeObject(stroke);
/*     */       }
/*     */     }
/*     */     else {
/* 262 */       stream.writeBoolean(true);
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
/*     */   public static Shape readShape(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 280 */     if (stream == null) {
/* 281 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 283 */     Shape result = null;
/* 284 */     boolean isNull = stream.readBoolean();
/* 285 */     if (!isNull) {
/* 286 */       Class c = (Class)stream.readObject();
/* 287 */       if (c.equals(Line2D.class)) {
/* 288 */         double x1 = stream.readDouble();
/* 289 */         double y1 = stream.readDouble();
/* 290 */         double x2 = stream.readDouble();
/* 291 */         double y2 = stream.readDouble();
/* 292 */         result = new Line2D.Double(x1, y1, x2, y2);
/*     */       }
/* 294 */       else if (c.equals(Rectangle2D.class)) {
/* 295 */         double x = stream.readDouble();
/* 296 */         double y = stream.readDouble();
/* 297 */         double w = stream.readDouble();
/* 298 */         double h = stream.readDouble();
/* 299 */         result = new Rectangle2D.Double(x, y, w, h);
/*     */       }
/* 301 */       else if (c.equals(Ellipse2D.class)) {
/* 302 */         double x = stream.readDouble();
/* 303 */         double y = stream.readDouble();
/* 304 */         double w = stream.readDouble();
/* 305 */         double h = stream.readDouble();
/* 306 */         result = new Ellipse2D.Double(x, y, w, h);
/*     */       }
/* 308 */       else if (c.equals(Arc2D.class)) {
/* 309 */         double x = stream.readDouble();
/* 310 */         double y = stream.readDouble();
/* 311 */         double w = stream.readDouble();
/* 312 */         double h = stream.readDouble();
/* 313 */         double as = stream.readDouble();
/* 314 */         double ae = stream.readDouble();
/* 315 */         int at = stream.readInt();
/* 316 */         result = new Arc2D.Double(x, y, w, h, as, ae, at);
/*     */       }
/* 318 */       else if (c.equals(GeneralPath.class)) {
/* 319 */         GeneralPath gp = new GeneralPath();
/* 320 */         float[] args = new float[6];
/* 321 */         boolean hasNext = stream.readBoolean();
/* 322 */         while (!hasNext) {
/* 323 */           int type = stream.readInt();
/* 324 */           for (int i = 0; i < 6; i++) {
/* 325 */             args[i] = stream.readFloat();
/*     */           }
/* 327 */           switch (type) {
/*     */           case 0: 
/* 329 */             gp.moveTo(args[0], args[1]);
/* 330 */             break;
/*     */           case 1: 
/* 332 */             gp.lineTo(args[0], args[1]);
/* 333 */             break;
/*     */           case 3: 
/* 335 */             gp.curveTo(args[0], args[1], args[2], args[3], args[4], args[5]);
/*     */             
/* 337 */             break;
/*     */           case 2: 
/* 339 */             gp.quadTo(args[0], args[1], args[2], args[3]);
/* 340 */             break;
/*     */           case 4: 
/* 342 */             gp.closePath();
/* 343 */             break;
/*     */           default: 
/* 345 */             throw new RuntimeException("JFreeChart - No path exists");
/*     */           }
/*     */           
/* 348 */           gp.setWindingRule(stream.readInt());
/* 349 */           hasNext = stream.readBoolean();
/*     */         }
/* 351 */         result = gp;
/*     */       }
/*     */       else {
/* 354 */         result = (Shape)stream.readObject();
/*     */       }
/*     */     }
/* 357 */     return result;
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
/*     */   public static void writeShape(Shape shape, ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 373 */     if (stream == null) {
/* 374 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 376 */     if (shape != null) {
/* 377 */       stream.writeBoolean(false);
/* 378 */       if ((shape instanceof Line2D)) {
/* 379 */         Line2D line = (Line2D)shape;
/* 380 */         stream.writeObject(Line2D.class);
/* 381 */         stream.writeDouble(line.getX1());
/* 382 */         stream.writeDouble(line.getY1());
/* 383 */         stream.writeDouble(line.getX2());
/* 384 */         stream.writeDouble(line.getY2());
/*     */       }
/* 386 */       else if ((shape instanceof Rectangle2D)) {
/* 387 */         Rectangle2D rectangle = (Rectangle2D)shape;
/* 388 */         stream.writeObject(Rectangle2D.class);
/* 389 */         stream.writeDouble(rectangle.getX());
/* 390 */         stream.writeDouble(rectangle.getY());
/* 391 */         stream.writeDouble(rectangle.getWidth());
/* 392 */         stream.writeDouble(rectangle.getHeight());
/*     */       }
/* 394 */       else if ((shape instanceof Ellipse2D)) {
/* 395 */         Ellipse2D ellipse = (Ellipse2D)shape;
/* 396 */         stream.writeObject(Ellipse2D.class);
/* 397 */         stream.writeDouble(ellipse.getX());
/* 398 */         stream.writeDouble(ellipse.getY());
/* 399 */         stream.writeDouble(ellipse.getWidth());
/* 400 */         stream.writeDouble(ellipse.getHeight());
/*     */       }
/* 402 */       else if ((shape instanceof Arc2D)) {
/* 403 */         Arc2D arc = (Arc2D)shape;
/* 404 */         stream.writeObject(Arc2D.class);
/* 405 */         stream.writeDouble(arc.getX());
/* 406 */         stream.writeDouble(arc.getY());
/* 407 */         stream.writeDouble(arc.getWidth());
/* 408 */         stream.writeDouble(arc.getHeight());
/* 409 */         stream.writeDouble(arc.getAngleStart());
/* 410 */         stream.writeDouble(arc.getAngleExtent());
/* 411 */         stream.writeInt(arc.getArcType());
/*     */       }
/* 413 */       else if ((shape instanceof GeneralPath)) {
/* 414 */         stream.writeObject(GeneralPath.class);
/* 415 */         PathIterator pi = shape.getPathIterator(null);
/* 416 */         float[] args = new float[6];
/* 417 */         stream.writeBoolean(pi.isDone());
/* 418 */         while (!pi.isDone()) {
/* 419 */           int type = pi.currentSegment(args);
/* 420 */           stream.writeInt(type);
/*     */           
/*     */ 
/* 423 */           for (int i = 0; i < 6; i++) {
/* 424 */             stream.writeFloat(args[i]);
/*     */           }
/* 426 */           stream.writeInt(pi.getWindingRule());
/* 427 */           pi.next();
/* 428 */           stream.writeBoolean(pi.isDone());
/*     */         }
/*     */       }
/*     */       else {
/* 432 */         stream.writeObject(shape.getClass());
/* 433 */         stream.writeObject(shape);
/*     */       }
/*     */     }
/*     */     else {
/* 437 */       stream.writeBoolean(true);
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
/*     */   public static Point2D readPoint2D(ObjectInputStream stream)
/*     */     throws IOException
/*     */   {
/* 454 */     if (stream == null) {
/* 455 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 457 */     Point2D result = null;
/* 458 */     boolean isNull = stream.readBoolean();
/* 459 */     if (!isNull) {
/* 460 */       double x = stream.readDouble();
/* 461 */       double y = stream.readDouble();
/* 462 */       result = new Point2D.Double(x, y);
/*     */     }
/* 464 */     return result;
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
/*     */   public static void writePoint2D(Point2D p, ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 480 */     if (stream == null) {
/* 481 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 483 */     if (p != null) {
/* 484 */       stream.writeBoolean(false);
/* 485 */       stream.writeDouble(p.getX());
/* 486 */       stream.writeDouble(p.getY());
/*     */     }
/*     */     else {
/* 489 */       stream.writeBoolean(true);
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
/*     */   public static AttributedString readAttributedString(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 509 */     if (stream == null) {
/* 510 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 512 */     AttributedString result = null;
/* 513 */     boolean isNull = stream.readBoolean();
/* 514 */     if (!isNull)
/*     */     {
/* 516 */       String plainStr = (String)stream.readObject();
/* 517 */       result = new AttributedString(plainStr);
/* 518 */       char c = stream.readChar();
/* 519 */       int start = 0;
/* 520 */       while (c != 65535) {
/* 521 */         int limit = stream.readInt();
/* 522 */         Map atts = (Map)stream.readObject();
/* 523 */         result.addAttributes(atts, start, limit);
/* 524 */         start = limit;
/* 525 */         c = stream.readChar();
/*     */       }
/*     */     }
/* 528 */     return result;
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
/*     */   public static void writeAttributedString(AttributedString as, ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 542 */     if (stream == null) {
/* 543 */       throw new IllegalArgumentException("Null 'stream' argument.");
/*     */     }
/* 545 */     if (as != null) {
/* 546 */       stream.writeBoolean(false);
/* 547 */       AttributedCharacterIterator aci = as.getIterator();
/*     */       
/*     */ 
/* 550 */       StringBuffer plainStr = new StringBuffer();
/* 551 */       char current = aci.first();
/* 552 */       while (current != 65535) {
/* 553 */         plainStr = plainStr.append(current);
/* 554 */         current = aci.next();
/*     */       }
/* 556 */       stream.writeObject(plainStr.toString());
/*     */       
/*     */ 
/* 559 */       current = aci.first();
/* 560 */       int begin = aci.getBeginIndex();
/* 561 */       while (current != 65535)
/*     */       {
/*     */ 
/*     */ 
/* 565 */         stream.writeChar(current);
/*     */         
/*     */ 
/* 568 */         int limit = aci.getRunLimit();
/* 569 */         stream.writeInt(limit - begin);
/*     */         
/*     */ 
/* 572 */         Map atts = new HashMap(aci.getAttributes());
/* 573 */         stream.writeObject(atts);
/* 574 */         current = aci.setIndex(limit);
/*     */       }
/*     */       
/*     */ 
/* 578 */       stream.writeChar(65535);
/*     */     }
/*     */     else
/*     */     {
/* 582 */       stream.writeBoolean(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/io/SerialUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */