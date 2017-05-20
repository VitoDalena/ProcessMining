/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.PolarPoint;
/*     */ import edu.uci.ics.jung.visualization.transform.HyperbolicTransformer;
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
/*     */ 
/*     */ public class HyperbolicShapeTransformer
/*     */   extends HyperbolicTransformer
/*     */   implements ShapeFlatnessTransformer
/*     */ {
/*     */   public HyperbolicShapeTransformer(Component component)
/*     */   {
/*  40 */     this(component, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HyperbolicShapeTransformer(Component component, MutableTransformer delegate)
/*     */   {
/*  49 */     super(component, delegate);
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
/*  60 */   public Shape transform(Shape shape) { return transform(shape, 0.0F); }
/*     */   
/*     */   public Shape transform(Shape shape, float flatness) {
/*  63 */     GeneralPath newPath = new GeneralPath();
/*  64 */     float[] coords = new float[6];
/*  65 */     PathIterator iterator = null;
/*  66 */     if (flatness == 0.0F) {
/*  67 */       iterator = shape.getPathIterator(null);
/*     */     } else {
/*  69 */       iterator = shape.getPathIterator(null, flatness);
/*     */     }
/*  72 */     for (; 
/*  72 */         !iterator.isDone(); 
/*  73 */         iterator.next()) {
/*  74 */       int type = iterator.currentSegment(coords);
/*  75 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/*  77 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  78 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/*  79 */         break;
/*     */       
/*     */       case 1: 
/*  82 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  83 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/*  84 */         break;
/*     */       
/*     */       case 2: 
/*  87 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  88 */         q = _transform(new Point2D.Float(coords[2], coords[3]));
/*  89 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/*  90 */         break;
/*     */       
/*     */       case 3: 
/*  93 */         p = _transform(new Point2D.Float(coords[0], coords[1]));
/*  94 */         q = _transform(new Point2D.Float(coords[2], coords[3]));
/*  95 */         Point2D r = _transform(new Point2D.Float(coords[4], coords[5]));
/*  96 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/*  99 */         break;
/*     */       
/*     */       case 4: 
/* 102 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 107 */     return newPath;
/*     */   }
/*     */   
/*     */   public Shape inverseTransform(Shape shape) {
/* 111 */     GeneralPath newPath = new GeneralPath();
/* 112 */     float[] coords = new float[6];
/* 113 */     PathIterator iterator = shape.getPathIterator(null);
/* 114 */     for (; !iterator.isDone(); 
/* 115 */         iterator.next()) {
/* 116 */       int type = iterator.currentSegment(coords);
/* 117 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/* 119 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 120 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/* 121 */         break;
/*     */       
/*     */       case 1: 
/* 124 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 125 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/* 126 */         break;
/*     */       
/*     */       case 2: 
/* 129 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 130 */         q = _inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 131 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/* 132 */         break;
/*     */       
/*     */       case 3: 
/* 135 */         p = _inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 136 */         q = _inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 137 */         Point2D r = _inverseTransform(new Point2D.Float(coords[4], coords[5]));
/* 138 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/* 141 */         break;
/*     */       
/*     */       case 4: 
/* 144 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 149 */     return newPath;
/*     */   }
/*     */   
/*     */ 
/*     */   private Point2D _transform(Point2D graphPoint)
/*     */   {
/* 155 */     if (graphPoint == null) return null;
/* 156 */     Point2D viewCenter = getViewCenter();
/* 157 */     double viewRadius = getViewRadius();
/* 158 */     double ratio = getRatio();
/*     */     
/* 160 */     Point2D viewPoint = graphPoint;
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
/* 173 */     double mag = Math.tan(1.5707963267948966D * this.magnification);
/* 174 */     radius *= mag;
/*     */     
/* 176 */     radius = Math.min(radius, viewRadius);
/* 177 */     radius /= viewRadius;
/* 178 */     radius *= 1.5707963267948966D;
/* 179 */     radius = Math.abs(Math.atan(radius));
/* 180 */     radius *= viewRadius;
/* 181 */     Point2D projectedPoint = PolarPoint.polarToCartesian(theta, radius);
/* 182 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 183 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 185 */     return translatedBack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Point2D _inverseTransform(Point2D viewPoint)
/*     */   {
/* 193 */     viewPoint = this.delegate.inverseTransform(viewPoint);
/* 194 */     Point2D viewCenter = getViewCenter();
/* 195 */     double viewRadius = getViewRadius();
/* 196 */     double ratio = getRatio();
/* 197 */     double dx = viewPoint.getX() - viewCenter.getX();
/* 198 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/* 200 */     dx *= ratio;
/*     */     
/* 202 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/* 204 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*     */     
/* 206 */     double radius = polar.getRadius();
/* 207 */     if (radius > viewRadius) { return viewPoint;
/*     */     }
/* 209 */     radius /= viewRadius;
/* 210 */     radius = Math.abs(Math.tan(radius));
/* 211 */     radius /= 1.5707963267948966D;
/* 212 */     radius *= viewRadius;
/* 213 */     double mag = Math.tan(1.5707963267948966D * this.magnification);
/* 214 */     radius /= mag;
/* 215 */     polar.setRadius(radius);
/* 216 */     Point2D projectedPoint = PolarPoint.polarToCartesian(polar);
/* 217 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 218 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 220 */     return translatedBack;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/HyperbolicShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */