/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import edu.uci.ics.jung.visualization.util.DefaultChangeEventSupport;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.event.ChangeEvent;
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
/*     */ public class BasicTransformer
/*     */   implements MultiLayerTransformer, ShapeTransformer, ChangeListener, ChangeEventSupport
/*     */ {
/*  25 */   protected ChangeEventSupport changeSupport = new DefaultChangeEventSupport(this);
/*     */   
/*     */ 
/*  28 */   protected MutableTransformer viewTransformer = new MutableAffineTransformer(new AffineTransform());
/*     */   
/*     */ 
/*  31 */   protected MutableTransformer layoutTransformer = new MutableAffineTransformer(new AffineTransform());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicTransformer()
/*     */   {
/*  40 */     this.viewTransformer.addChangeListener(this);
/*  41 */     this.layoutTransformer.addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setViewTransformer(MutableTransformer transformer)
/*     */   {
/*  48 */     this.viewTransformer.removeChangeListener(this);
/*  49 */     this.viewTransformer = transformer;
/*  50 */     this.viewTransformer.addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setLayoutTransformer(MutableTransformer transformer)
/*     */   {
/*  57 */     this.layoutTransformer.removeChangeListener(this);
/*  58 */     this.layoutTransformer = transformer;
/*  59 */     this.layoutTransformer.addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected MutableTransformer getLayoutTransformer()
/*     */   {
/*  67 */     return this.layoutTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected MutableTransformer getViewTransformer()
/*     */   {
/*  74 */     return this.viewTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D inverseTransform(Point2D p)
/*     */   {
/*  81 */     return inverseLayoutTransform(inverseViewTransform(p));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Point2D inverseViewTransform(Point2D p)
/*     */   {
/*  88 */     return this.viewTransformer.inverseTransform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Point2D inverseLayoutTransform(Point2D p)
/*     */   {
/*  95 */     return this.layoutTransformer.inverseTransform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(Point2D p)
/*     */   {
/* 102 */     return viewTransform(layoutTransform(p));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Point2D viewTransform(Point2D p)
/*     */   {
/* 109 */     return this.viewTransformer.transform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Point2D layoutTransform(Point2D p)
/*     */   {
/* 116 */     return this.layoutTransformer.transform(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Shape inverseTransform(Shape shape)
/*     */   {
/* 123 */     return inverseLayoutTransform(inverseViewTransform(shape));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Shape inverseViewTransform(Shape shape)
/*     */   {
/* 130 */     return this.viewTransformer.inverseTransform(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Shape inverseLayoutTransform(Shape shape)
/*     */   {
/* 137 */     return this.layoutTransformer.inverseTransform(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Shape transform(Shape shape)
/*     */   {
/* 144 */     return viewTransform(layoutTransform(shape));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Shape viewTransform(Shape shape)
/*     */   {
/* 151 */     return this.viewTransformer.transform(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Shape layoutTransform(Shape shape)
/*     */   {
/* 158 */     return this.layoutTransformer.transform(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setToIdentity()
/*     */   {
/* 165 */     this.layoutTransformer.setToIdentity();
/* 166 */     this.viewTransformer.setToIdentity();
/*     */   }
/*     */   
/*     */ 
/*     */   public void addChangeListener(ChangeListener l)
/*     */   {
/* 172 */     this.changeSupport.addChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeChangeListener(ChangeListener l)
/*     */   {
/* 178 */     this.changeSupport.removeChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */   public ChangeListener[] getChangeListeners()
/*     */   {
/* 184 */     return this.changeSupport.getChangeListeners();
/*     */   }
/*     */   
/*     */ 
/*     */   public void fireStateChanged()
/*     */   {
/* 190 */     this.changeSupport.fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */   public void stateChanged(ChangeEvent e)
/*     */   {
/* 196 */     fireStateChanged();
/*     */   }
/*     */   
/*     */   public MutableTransformer getTransformer(Layer layer) {
/* 200 */     if (layer == Layer.LAYOUT) return this.layoutTransformer;
/* 201 */     if (layer == Layer.VIEW) return this.viewTransformer;
/* 202 */     return null;
/*     */   }
/*     */   
/*     */   public Point2D inverseTransform(Layer layer, Point2D p) {
/* 206 */     if (layer == Layer.LAYOUT) return inverseLayoutTransform(p);
/* 207 */     if (layer == Layer.VIEW) return inverseViewTransform(p);
/* 208 */     return null;
/*     */   }
/*     */   
/*     */   public void setTransformer(Layer layer, MutableTransformer transformer) {
/* 212 */     if (layer == Layer.LAYOUT) setLayoutTransformer(transformer);
/* 213 */     if (layer == Layer.VIEW) setViewTransformer(transformer);
/*     */   }
/*     */   
/*     */   public Point2D transform(Layer layer, Point2D p)
/*     */   {
/* 218 */     if (layer == Layer.LAYOUT) return layoutTransform(p);
/* 219 */     if (layer == Layer.VIEW) return viewTransform(p);
/* 220 */     return null;
/*     */   }
/*     */   
/*     */   public Shape transform(Layer layer, Shape shape) {
/* 224 */     if (layer == Layer.LAYOUT) return layoutTransform(shape);
/* 225 */     if (layer == Layer.VIEW) return viewTransform(shape);
/* 226 */     return null;
/*     */   }
/*     */   
/*     */   public Shape inverseTransform(Layer layer, Shape shape) {
/* 230 */     if (layer == Layer.LAYOUT) return inverseLayoutTransform(shape);
/* 231 */     if (layer == Layer.VIEW) return inverseViewTransform(shape);
/* 232 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/BasicTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */