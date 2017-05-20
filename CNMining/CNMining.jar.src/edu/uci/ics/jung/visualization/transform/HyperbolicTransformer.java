/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.PolarPoint;
/*     */ import java.awt.Component;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HyperbolicTransformer
/*     */   extends LensTransformer
/*     */   implements MutableTransformer
/*     */ {
/*     */   public HyperbolicTransformer(Component component)
/*     */   {
/*  41 */     this(component, new MutableAffineTransformer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public HyperbolicTransformer(Component component, MutableTransformer delegate)
/*     */   {
/*  49 */     super(component, delegate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(Point2D graphPoint)
/*     */   {
/*  56 */     if (graphPoint == null) return null;
/*  57 */     Point2D viewCenter = getViewCenter();
/*  58 */     double viewRadius = getViewRadius();
/*  59 */     double ratio = getRatio();
/*     */     
/*  61 */     Point2D viewPoint = this.delegate.transform(graphPoint);
/*     */     
/*  63 */     double dx = viewPoint.getX() - viewCenter.getX();
/*  64 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/*  66 */     dx *= ratio;
/*  67 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/*  69 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*  70 */     double theta = polar.getTheta();
/*  71 */     double radius = polar.getRadius();
/*  72 */     if (radius > viewRadius) { return viewPoint;
/*     */     }
/*  74 */     double mag = Math.tan(1.5707963267948966D * this.magnification);
/*  75 */     radius *= mag;
/*     */     
/*  77 */     radius = Math.min(radius, viewRadius);
/*  78 */     radius /= viewRadius;
/*  79 */     radius *= 1.5707963267948966D;
/*  80 */     radius = Math.abs(Math.atan(radius));
/*  81 */     radius *= viewRadius;
/*  82 */     Point2D projectedPoint = PolarPoint.polarToCartesian(theta, radius);
/*  83 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/*  84 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/*  86 */     return translatedBack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D inverseTransform(Point2D viewPoint)
/*     */   {
/*  94 */     Point2D viewCenter = getViewCenter();
/*  95 */     double viewRadius = getViewRadius();
/*  96 */     double ratio = getRatio();
/*  97 */     double dx = viewPoint.getX() - viewCenter.getX();
/*  98 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/* 100 */     dx *= ratio;
/*     */     
/* 102 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/* 104 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*     */     
/* 106 */     double radius = polar.getRadius();
/* 107 */     if (radius > viewRadius) { return this.delegate.inverseTransform(viewPoint);
/*     */     }
/* 109 */     radius /= viewRadius;
/* 110 */     radius = Math.abs(Math.tan(radius));
/* 111 */     radius /= 1.5707963267948966D;
/* 112 */     radius *= viewRadius;
/* 113 */     double mag = Math.tan(1.5707963267948966D * this.magnification);
/* 114 */     radius /= mag;
/* 115 */     polar.setRadius(radius);
/* 116 */     Point2D projectedPoint = PolarPoint.polarToCartesian(polar);
/* 117 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 118 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 120 */     return this.delegate.inverseTransform(translatedBack);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/HyperbolicTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */