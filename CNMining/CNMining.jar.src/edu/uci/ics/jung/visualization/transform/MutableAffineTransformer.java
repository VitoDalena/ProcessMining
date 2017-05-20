/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import edu.uci.ics.jung.visualization.util.DefaultChangeEventSupport;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MutableAffineTransformer
/*     */   extends AffineTransformer
/*     */   implements MutableTransformer, ShapeTransformer, ChangeEventSupport
/*     */ {
/*  37 */   protected ChangeEventSupport changeSupport = new DefaultChangeEventSupport(this);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableAffineTransformer() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableAffineTransformer(AffineTransform transform)
/*     */   {
/*  51 */     super(transform);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  55 */     return "MutableAffineTransformer using " + this.transform;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void scale(double scalex, double scaley, Point2D from)
/*     */   {
/*  65 */     AffineTransform xf = AffineTransform.getTranslateInstance(from.getX(), from.getY());
/*  66 */     xf.scale(scalex, scaley);
/*  67 */     xf.translate(-from.getX(), -from.getY());
/*  68 */     this.inverse = null;
/*  69 */     this.transform.preConcatenate(xf);
/*  70 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScale(double scalex, double scaley, Point2D from)
/*     */   {
/*  81 */     this.transform.setToIdentity();
/*  82 */     scale(scalex, scaley, from);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shear(double shx, double shy, Point2D from)
/*     */   {
/*  91 */     this.inverse = null;
/*  92 */     AffineTransform at = AffineTransform.getTranslateInstance(from.getX(), from.getY());
/*     */     
/*  94 */     at.shear(shx, shy);
/*  95 */     at.translate(-from.getX(), -from.getY());
/*  96 */     this.transform.preConcatenate(at);
/*  97 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTranslate(double tx, double ty)
/*     */   {
/* 108 */     float scalex = (float)this.transform.getScaleX();
/* 109 */     float scaley = (float)this.transform.getScaleY();
/* 110 */     float shearx = (float)this.transform.getShearX();
/* 111 */     float sheary = (float)this.transform.getShearY();
/* 112 */     this.inverse = null;
/* 113 */     this.transform.setTransform(scalex, sheary, shearx, scaley, tx, ty);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 118 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void translate(double offsetx, double offsety)
/*     */   {
/* 127 */     this.inverse = null;
/* 128 */     this.transform.translate(offsetx, offsety);
/* 129 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rotate(double theta, Point2D from)
/*     */   {
/* 136 */     AffineTransform rotate = AffineTransform.getRotateInstance(theta, from.getX(), from.getY());
/*     */     
/* 138 */     this.inverse = null;
/* 139 */     this.transform.preConcatenate(rotate);
/*     */     
/* 141 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rotate(double radians, double x, double y)
/*     */   {
/* 148 */     this.inverse = null;
/* 149 */     this.transform.rotate(radians, x, y);
/* 150 */     fireStateChanged();
/*     */   }
/*     */   
/*     */   public void concatenate(AffineTransform xform) {
/* 154 */     this.inverse = null;
/* 155 */     this.transform.concatenate(xform);
/* 156 */     fireStateChanged();
/*     */   }
/*     */   
/*     */   public void preConcatenate(AffineTransform xform) {
/* 160 */     this.inverse = null;
/* 161 */     this.transform.preConcatenate(xform);
/* 162 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(ChangeListener l)
/*     */   {
/* 171 */     this.changeSupport.addChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(ChangeListener l)
/*     */   {
/* 179 */     this.changeSupport.removeChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChangeListener[] getChangeListeners()
/*     */   {
/* 190 */     return this.changeSupport.getChangeListeners();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireStateChanged()
/*     */   {
/* 200 */     this.changeSupport.fireStateChanged();
/*     */   }
/*     */   
/*     */   public void setToIdentity() {
/* 204 */     this.inverse = null;
/* 205 */     this.transform.setToIdentity();
/* 206 */     fireStateChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/MutableAffineTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */