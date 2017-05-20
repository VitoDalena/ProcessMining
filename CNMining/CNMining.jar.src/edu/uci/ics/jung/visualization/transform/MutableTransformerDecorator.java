/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MutableTransformerDecorator
/*     */   implements MutableTransformer
/*     */ {
/*     */   protected MutableTransformer delegate;
/*     */   
/*     */   public MutableTransformerDecorator(MutableTransformer delegate)
/*     */   {
/*  29 */     if (delegate == null) {
/*  30 */       delegate = new MutableAffineTransformer();
/*     */     }
/*  32 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MutableTransformer getDelegate()
/*     */   {
/*  39 */     return this.delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDelegate(MutableTransformer delegate)
/*     */   {
/*  46 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(ChangeListener l)
/*     */   {
/*  55 */     this.delegate.addChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void concatenate(AffineTransform transform)
/*     */   {
/*  62 */     this.delegate.concatenate(transform);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fireStateChanged()
/*     */   {
/*  69 */     this.delegate.fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ChangeListener[] getChangeListeners()
/*     */   {
/*  76 */     return this.delegate.getChangeListeners();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getScale()
/*     */   {
/*  83 */     return this.delegate.getScale();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getScaleX()
/*     */   {
/*  90 */     return this.delegate.getScaleX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getScaleY()
/*     */   {
/*  97 */     return this.delegate.getScaleY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getShearX()
/*     */   {
/* 104 */     return this.delegate.getShearX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getShearY()
/*     */   {
/* 111 */     return this.delegate.getShearY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AffineTransform getTransform()
/*     */   {
/* 118 */     return this.delegate.getTransform();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getTranslateX()
/*     */   {
/* 125 */     return this.delegate.getTranslateX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getTranslateY()
/*     */   {
/* 132 */     return this.delegate.getTranslateY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D inverseTransform(Point2D p)
/*     */   {
/* 139 */     return this.delegate.inverseTransform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Shape inverseTransform(Shape shape)
/*     */   {
/* 146 */     return this.delegate.inverseTransform(shape);
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
/*     */   public void preConcatenate(AffineTransform transform)
/*     */   {
/* 160 */     this.delegate.preConcatenate(transform);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(ChangeListener l)
/*     */   {
/* 167 */     this.delegate.removeChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rotate(double radians, Point2D point)
/*     */   {
/* 174 */     this.delegate.rotate(radians, point);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void scale(double sx, double sy, Point2D point)
/*     */   {
/* 181 */     this.delegate.scale(sx, sy, point);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setScale(double sx, double sy, Point2D point)
/*     */   {
/* 188 */     this.delegate.setScale(sx, sy, point);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setToIdentity()
/*     */   {
/* 195 */     this.delegate.setToIdentity();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTranslate(double dx, double dy)
/*     */   {
/* 202 */     this.delegate.setTranslate(dx, dy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void shear(double shx, double shy, Point2D from)
/*     */   {
/* 209 */     this.delegate.shear(shx, shy, from);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(Point2D p)
/*     */   {
/* 216 */     return this.delegate.transform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Shape transform(Shape shape)
/*     */   {
/* 223 */     return this.delegate.transform(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void translate(double dx, double dy)
/*     */   {
/* 230 */     this.delegate.translate(dx, dy);
/*     */   }
/*     */   
/*     */   public double getRotation() {
/* 234 */     return this.delegate.getRotation();
/*     */   }
/*     */   
/*     */   public void rotate(double radians, double x, double y) {
/* 238 */     this.delegate.rotate(radians, x, y);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/MutableTransformerDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */