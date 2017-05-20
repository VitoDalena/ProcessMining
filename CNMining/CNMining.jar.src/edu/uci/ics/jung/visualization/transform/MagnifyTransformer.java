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
/*     */ public class MagnifyTransformer
/*     */   extends LensTransformer
/*     */   implements MutableTransformer
/*     */ {
/*     */   public MagnifyTransformer(Component component)
/*     */   {
/*  39 */     this(component, new MutableAffineTransformer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MagnifyTransformer(Component component, MutableTransformer delegate)
/*     */   {
/*  47 */     super(component, delegate);
/*  48 */     this.magnification = 3.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(Point2D graphPoint)
/*     */   {
/*  55 */     if (graphPoint == null) return null;
/*  56 */     Point2D viewCenter = getViewCenter();
/*  57 */     double viewRadius = getViewRadius();
/*  58 */     double ratio = getRatio();
/*     */     
/*  60 */     Point2D viewPoint = this.delegate.transform(graphPoint);
/*     */     
/*  62 */     double dx = viewPoint.getX() - viewCenter.getX();
/*  63 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/*  65 */     dx *= ratio;
/*  66 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/*  68 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*  69 */     double theta = polar.getTheta();
/*  70 */     double radius = polar.getRadius();
/*  71 */     if (radius > viewRadius) { return viewPoint;
/*     */     }
/*  73 */     double mag = this.magnification;
/*  74 */     radius *= mag;
/*     */     
/*  76 */     radius = Math.min(radius, viewRadius);
/*  77 */     Point2D projectedPoint = PolarPoint.polarToCartesian(theta, radius);
/*  78 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/*  79 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/*  81 */     return translatedBack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D inverseTransform(Point2D viewPoint)
/*     */   {
/*  89 */     Point2D viewCenter = getViewCenter();
/*  90 */     double viewRadius = getViewRadius();
/*  91 */     double ratio = getRatio();
/*  92 */     double dx = viewPoint.getX() - viewCenter.getX();
/*  93 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/*  95 */     dx *= ratio;
/*     */     
/*  97 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/*  99 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/*     */     
/* 101 */     double radius = polar.getRadius();
/* 102 */     if (radius > viewRadius) { return this.delegate.inverseTransform(viewPoint);
/*     */     }
/* 104 */     double mag = this.magnification;
/* 105 */     radius /= mag;
/* 106 */     polar.setRadius(radius);
/* 107 */     Point2D projectedPoint = PolarPoint.polarToCartesian(polar);
/* 108 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 109 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 111 */     return this.delegate.inverseTransform(translatedBack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D magnify(Point2D graphPoint)
/*     */   {
/* 120 */     if (graphPoint == null) return null;
/* 121 */     Point2D viewCenter = getViewCenter();
/* 122 */     double ratio = getRatio();
/*     */     
/* 124 */     Point2D viewPoint = graphPoint;
/*     */     
/* 126 */     double dx = viewPoint.getX() - viewCenter.getX();
/* 127 */     double dy = viewPoint.getY() - viewCenter.getY();
/*     */     
/* 129 */     dx *= ratio;
/* 130 */     Point2D pointFromCenter = new Point2D.Double(dx, dy);
/*     */     
/* 132 */     PolarPoint polar = PolarPoint.cartesianToPolar(pointFromCenter);
/* 133 */     double theta = polar.getTheta();
/* 134 */     double radius = polar.getRadius();
/*     */     
/* 136 */     double mag = this.magnification;
/* 137 */     radius *= mag;
/*     */     
/*     */ 
/* 140 */     Point2D projectedPoint = PolarPoint.polarToCartesian(theta, radius);
/* 141 */     projectedPoint.setLocation(projectedPoint.getX() / ratio, projectedPoint.getY());
/* 142 */     Point2D translatedBack = new Point2D.Double(projectedPoint.getX() + viewCenter.getX(), projectedPoint.getY() + viewCenter.getY());
/*     */     
/* 144 */     return translatedBack;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/MagnifyTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */