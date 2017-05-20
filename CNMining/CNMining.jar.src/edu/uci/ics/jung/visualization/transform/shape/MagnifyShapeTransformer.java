/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.PolarPoint;
/*     */ import edu.uci.ics.jung.visualization.transform.MagnifyTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Component;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MagnifyShapeTransformer
/*     */   extends MagnifyTransformer
/*     */   implements ShapeFlatnessTransformer
/*     */ {
/*     */   public MagnifyShapeTransformer(Component component)
/*     */   {
/*  39 */     this(component, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MagnifyShapeTransformer(Component component, MutableTransformer delegate)
/*     */   {
/*  48 */     super(component, delegate);
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
/*  59 */   public Shape transform(Shape shape) { return transform(shape, 0.0F); }
/*     */   
/*     */   public Shape transform(Shape shape, float flatness) {
/*  62 */     GeneralPath newPath = new GeneralPath();
/*  63 */     float[] coords = new float[6];
/*  64 */     PathIterator iterator = null;
/*  65 */     if (flatness == 0.0F) {
/*  66 */       iterator = shape.getPathIterator(null);
/*     */     } else {
/*  68 */       iterator = shape.getPathIterator(null, flatness);
/*     */     }
/*  71 */     for (; 
/*  71 */         !iterator.isDone(); 
/*  72 */         iterator.next()) {
/*  73 */       int type = iterator.currentSegment(coords);
/*  74 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/*  76 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  77 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/*  78 */         break;
/*     */       
/*     */       case 1: 
/*  81 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  82 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/*  83 */         break;
/*     */       
/*     */       case 2: 
/*  86 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  87 */         q = _transform(new Point2D.Float(coords[2], coords[3]));
/*  88 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/*  89 */         break;
/*     */       
/*     */       case 3: 
/*  92 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  93 */         q = _transform(new Point2D.Float(coords[2], coords[3]));
/*  94 */         Point2D r = _transform(new Point2D.Float(coords[4], coords[5]));
/*  95 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/*  98 */         break;
/*     */       
/*     */       case 4: 
/* 101 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 106 */     return newPath;
/*     */   }
/*     */   
/*     */   public Shape inverseTransform(Shape shape) {
/* 110 */     GeneralPath newPath = new GeneralPath();
/* 111 */     float[] coords = new float[6];
/* 112 */     PathIterator iterator = shape.getPathIterator(null);
/* 113 */     for (; !iterator.isDone(); 
/* 114 */         iterator.next()) {
/* 115 */       int type = iterator.currentSegment(coords);
/* 116 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/* 118 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 119 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/* 120 */         break;
/*     */       
/*     */       case 1: 
/* 123 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 124 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/* 125 */         break;
/*     */       
/*     */       case 2: 
/* 128 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 129 */         q = _inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 130 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/* 131 */         break;
/*     */       
/*     */       case 3: 
/* 134 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 135 */         q = _inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 136 */         Point2D r = _inverseTransform(new Point2D.Float(coords[4], coords[5]));
/* 137 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/* 140 */         break;
/*     */       
/*     */       case 4: 
/* 143 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 148 */     return newPath;
/*     */   }
/*     */   
/*     */ 
/*     */   private Point2D _transform(Point2D graphPoint)
/*     */   {
/* 154 */     if (graphPoint == null) return null;
/* 155 */     Point2D viewCenter = getViewCenter();
/* 156 */     double viewRadius = getViewRadius();
/* 157 */     double ratio = getRatio();
/*     */     
/* 159 */     Point2D viewPoint = graphPoint;
/*     */     
/*     */ 
/* 162 */     double dx = viewPoint.getX() - viewCenter.getX();
/* 163 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/* 165 */     dx *= ratio;
/* 166 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/* 168 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/* 169 */     double theta = polar.getTheta();
/* 170 */     double radius = polar.getRadius();
/* 171 */     if (radius > viewRadius) { return viewPoint;
/*     */     }
/* 173 */     double mag = this.magnification;
/* 174 */     radius *= mag;
/*     */     
/* 176 */     radius = Math.min(radius, viewRadius);
/* 177 */     Point2D projectedPoint = PolarPoint.polarToCartesian(theta, radius);
/* 178 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 179 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 181 */     return translatedBack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Point2D _inverseTransform(Point2D viewPoint)
/*     */   {
/* 189 */     viewPoint = this.delegate.inverseTransform(viewPoint);
/* 190 */     Point2D viewCenter = getViewCenter();
/* 191 */     double viewRadius = getViewRadius();
/* 192 */     double ratio = getRatio();
/* 193 */     double dx = viewPoint.getX() - viewCenter.getX();
/* 194 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/* 196 */     dx *= ratio;
/*     */     
/* 198 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/* 200 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*     */     
/* 202 */     double radius = polar.getRadius();
/* 203 */     if (radius > viewRadius) { return viewPoint;
/*     */     }
/*     */     
/* 206 */     double mag = this.magnification;
/* 207 */     radius /= mag;
/* 208 */     polar.setRadius(radius);
/* 209 */     Point2D projectedPoint = PolarPoint.polarToCartesian(polar);
/* 210 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 211 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/*     */ 
/* 214 */     return translatedBack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 222 */   public Shape magnify(Shape shape) { return magnify(shape, 0.0F); }
/*     */   
/*     */   public Shape magnify(Shape shape, float flatness) {
/* 225 */     GeneralPath newPath = new GeneralPath();
/* 226 */     float[] coords = new float[6];
/* 227 */     PathIterator iterator = null;
/* 228 */     if (flatness == 0.0F) {
/* 229 */       iterator = shape.getPathIterator(null);
/*     */     } else {
/* 231 */       iterator = shape.getPathIterator(null, flatness);
/*     */     }
/* 234 */     for (; 
/* 234 */         !iterator.isDone(); 
/* 235 */         iterator.next()) {
/* 236 */       int type = iterator.currentSegment(coords);
/* 237 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/* 239 */         p = magnify(new Point2D.Float(coords[0], coords[1]));
/* 240 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/* 241 */         break;
/*     */       
/*     */       case 1: 
/* 244 */         p = magnify(new Point2D.Float(coords[0], coords[1]));
/* 245 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/* 246 */         break;
/*     */       
/*     */       case 2: 
/* 249 */         p = magnify(new Point2D.Float(coords[0], coords[1]));
/* 250 */         q = magnify(new Point2D.Float(coords[2], coords[3]));
/* 251 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/* 252 */         break;
/*     */       
/*     */       case 3: 
/* 255 */         p = magnify(new Point2D.Float(coords[0], coords[1]));
/* 256 */         q = magnify(new Point2D.Float(coords[2], coords[3]));
/* 257 */         Point2D r = magnify(new Point2D.Float(coords[4], coords[5]));
/* 258 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/* 261 */         break;
/*     */       
/*     */       case 4: 
/* 264 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 269 */     return newPath;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/MagnifyShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */