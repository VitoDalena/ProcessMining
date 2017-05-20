/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D.Float;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RectangularShape;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LensTransformer
/*     */   extends MutableTransformerDecorator
/*     */   implements MutableTransformer
/*     */ {
/*  41 */   protected RectangularShape lensShape = new Ellipse2D.Float();
/*     */   
/*  43 */   protected float magnification = 0.7F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LensTransformer(Component component, MutableTransformer delegate)
/*     */   {
/*  51 */     super(delegate);
/*  52 */     setComponent(component);
/*  53 */     component.addComponentListener(new ComponentListenerImpl());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setComponent(Component component)
/*     */   {
/*  62 */     Dimension d = component.getSize();
/*  63 */     if ((d.width <= 0) || (d.height <= 0)) {
/*  64 */       d = component.getPreferredSize();
/*     */     }
/*  66 */     float ewidth = d.width / 1.5F;
/*  67 */     float eheight = d.height / 1.5F;
/*  68 */     this.lensShape.setFrame(d.width / 2 - ewidth / 2.0F, d.height / 2 - eheight / 2.0F, ewidth, eheight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getMagnification()
/*     */   {
/*  75 */     return this.magnification;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMagnification(float magnification)
/*     */   {
/*  81 */     this.magnification = magnification;
/*     */   }
/*     */   
/*     */ 
/*     */   public Point2D getViewCenter()
/*     */   {
/*  87 */     return new Point2D.Double(this.lensShape.getCenterX(), this.lensShape.getCenterY());
/*     */   }
/*     */   
/*     */ 
/*     */   public void setViewCenter(Point2D viewCenter)
/*     */   {
/*  93 */     double width = this.lensShape.getWidth();
/*  94 */     double height = this.lensShape.getHeight();
/*  95 */     this.lensShape.setFrame(viewCenter.getX() - width / 2.0D, viewCenter.getY() - height / 2.0D, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getViewRadius()
/*     */   {
/* 104 */     return this.lensShape.getHeight() / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setViewRadius(double viewRadius)
/*     */   {
/* 110 */     double x = this.lensShape.getCenterX();
/* 111 */     double y = this.lensShape.getCenterY();
/* 112 */     double viewRatio = getRatio();
/* 113 */     this.lensShape.setFrame(x - viewRadius / viewRatio, y - viewRadius, 2.0D * viewRadius / viewRatio, 2.0D * viewRadius);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRatio()
/*     */   {
/* 123 */     return this.lensShape.getHeight() / this.lensShape.getWidth();
/*     */   }
/*     */   
/*     */   public void setLensShape(RectangularShape ellipse) {
/* 127 */     this.lensShape = ellipse;
/*     */   }
/*     */   
/* 130 */   public RectangularShape getLensShape() { return this.lensShape; }
/*     */   
/*     */ 
/* 133 */   public void setToIdentity() { this.delegate.setToIdentity(); }
/*     */   
/*     */   public abstract Point2D transform(Point2D paramPoint2D);
/*     */   
/*     */   public abstract Point2D inverseTransform(Point2D paramPoint2D);
/*     */   
/*     */   protected class ComponentListenerImpl extends ComponentAdapter { protected ComponentListenerImpl() {}
/*     */     
/* 141 */     public void componentResized(ComponentEvent e) { LensTransformer.this.setComponent(e.getComponent()); }
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
/*     */   public double getDistanceFromCenter(Point2D p)
/*     */   {
/* 157 */     double dx = this.lensShape.getCenterX() - p.getX();
/* 158 */     double dy = this.lensShape.getCenterY() - p.getY();
/* 159 */     dx *= getRatio();
/* 160 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape transform(Shape shape)
/*     */   {
/* 168 */     Rectangle2D bounds = shape.getBounds2D();
/* 169 */     Point2D center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
/* 170 */     Point2D newCenter = transform(center);
/* 171 */     double dx = newCenter.getX() - center.getX();
/* 172 */     double dy = newCenter.getY() - center.getY();
/* 173 */     AffineTransform at = AffineTransform.getTranslateInstance(dx, dy);
/* 174 */     return at.createTransformedShape(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape inverseTransform(Shape shape)
/*     */   {
/* 182 */     Rectangle2D bounds = shape.getBounds2D();
/* 183 */     Point2D center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
/* 184 */     Point2D newCenter = inverseTransform(center);
/* 185 */     double dx = newCenter.getX() - center.getX();
/* 186 */     double dy = newCenter.getY() - center.getY();
/* 187 */     AffineTransform at = AffineTransform.getTranslateInstance(dx, dy);
/* 188 */     return at.createTransformedShape(shape);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/LensTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */