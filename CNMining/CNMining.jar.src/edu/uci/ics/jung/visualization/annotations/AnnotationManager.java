/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.transform.AffineTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationManager
/*     */ {
/*  37 */   protected AnnotationRenderer annotationRenderer = new AnnotationRenderer();
/*     */   protected AnnotationPaintable lowerAnnotationPaintable;
/*     */   protected AnnotationPaintable upperAnnotationPaintable;
/*     */   protected RenderContext<?, ?> rc;
/*     */   protected AffineTransformer transformer;
/*     */   
/*     */   public AnnotationManager(RenderContext<?, ?> rc)
/*     */   {
/*  45 */     this.rc = rc;
/*  46 */     this.lowerAnnotationPaintable = new AnnotationPaintable(rc, this.annotationRenderer);
/*  47 */     this.upperAnnotationPaintable = new AnnotationPaintable(rc, this.annotationRenderer);
/*     */     
/*  49 */     MutableTransformer mt = rc.getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*  50 */     if ((mt instanceof AffineTransformer)) {
/*  51 */       this.transformer = ((AffineTransformer)mt);
/*  52 */     } else if ((mt instanceof LensTransformer)) {
/*  53 */       this.transformer = ((AffineTransformer)((LensTransformer)mt).getDelegate());
/*     */     }
/*     */   }
/*     */   
/*     */   public AnnotationPaintable getAnnotationPaintable(Annotation.Layer layer)
/*     */   {
/*  59 */     if (layer == Annotation.Layer.LOWER) {
/*  60 */       return this.lowerAnnotationPaintable;
/*     */     }
/*  62 */     if (layer == Annotation.Layer.UPPER) {
/*  63 */       return this.upperAnnotationPaintable;
/*     */     }
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   public void add(Annotation.Layer layer, Annotation<?> annotation) {
/*  69 */     if (layer == Annotation.Layer.LOWER) {
/*  70 */       this.lowerAnnotationPaintable.add(annotation);
/*     */     }
/*  72 */     if (layer == Annotation.Layer.UPPER) {
/*  73 */       this.upperAnnotationPaintable.add(annotation);
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(Annotation<?> annotation) {
/*  78 */     this.lowerAnnotationPaintable.remove(annotation);
/*  79 */     this.upperAnnotationPaintable.remove(annotation);
/*     */   }
/*     */   
/*     */   protected AnnotationPaintable getLowerAnnotationPaintable() {
/*  83 */     return this.lowerAnnotationPaintable;
/*     */   }
/*     */   
/*     */   protected AnnotationPaintable getUpperAnnotationPaintable() {
/*  87 */     return this.upperAnnotationPaintable;
/*     */   }
/*     */   
/*     */   public Annotation getAnnotation(Point2D p)
/*     */   {
/*  92 */     Set<Annotation> annotations = new HashSet(this.lowerAnnotationPaintable.getAnnotations());
/*  93 */     annotations.addAll(this.upperAnnotationPaintable.getAnnotations());
/*  94 */     return getAnnotation(p, annotations);
/*     */   }
/*     */   
/*     */   public Annotation getAnnotation(Point2D p, Collection<Annotation> annotations)
/*     */   {
/*  99 */     double closestDistance = Double.MAX_VALUE;
/* 100 */     Annotation closestAnnotation = null;
/* 101 */     for (Annotation annotation : annotations) {
/* 102 */       Object ann = annotation.getAnnotation();
/* 103 */       if ((ann instanceof Shape)) {
/* 104 */         Point2D ip = this.rc.getMultiLayerTransformer().inverseTransform(p);
/* 105 */         Shape shape = (Shape)ann;
/* 106 */         if (shape.contains(ip))
/*     */         {
/* 108 */           Rectangle2D shapeBounds = shape.getBounds2D();
/* 109 */           Point2D shapeCenter = new Point2D.Double(shapeBounds.getCenterX(), shapeBounds.getCenterY());
/* 110 */           double distanceSq = shapeCenter.distanceSq(ip);
/* 111 */           if (distanceSq < closestDistance) {
/* 112 */             closestDistance = distanceSq;
/* 113 */             closestAnnotation = annotation;
/*     */           }
/*     */         }
/* 116 */       } else if ((ann instanceof String))
/*     */       {
/* 118 */         Point2D ip = this.rc.getMultiLayerTransformer().inverseTransform(Layer.VIEW, p);
/* 119 */         Point2D ap = annotation.getLocation();
/* 120 */         String label = (String)ann;
/* 121 */         Component component = prepareRenderer(this.rc, this.annotationRenderer, label);
/*     */         
/* 123 */         AffineTransform base = new AffineTransform(this.transformer.getTransform());
/* 124 */         double rotation = this.transformer.getRotation();
/*     */         
/* 126 */         AffineTransform unrotate = AffineTransform.getRotateInstance(-rotation, ap.getX(), ap.getY());
/*     */         
/* 128 */         base.concatenate(unrotate);
/*     */         
/* 130 */         Dimension d = component.getPreferredSize();
/* 131 */         Rectangle2D componentBounds = new Rectangle2D.Double(ap.getX(), ap.getY(), d.width, d.height);
/*     */         
/* 133 */         Shape componentBoundsShape = base.createTransformedShape(componentBounds);
/* 134 */         Point2D componentCenter = new Point2D.Double(componentBoundsShape.getBounds().getCenterX(), componentBoundsShape.getBounds().getCenterY());
/*     */         
/* 136 */         if (componentBoundsShape.contains(ip)) {
/* 137 */           double distanceSq = componentCenter.distanceSq(ip);
/* 138 */           if (distanceSq < closestDistance) {
/* 139 */             closestDistance = distanceSq;
/* 140 */             closestAnnotation = annotation;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 146 */     return closestAnnotation;
/*     */   }
/*     */   
/*     */   public Component prepareRenderer(RenderContext<?, ?> rc, AnnotationRenderer annotationRenderer, Object value) {
/* 150 */     return annotationRenderer.getAnnotationRendererComponent(rc.getScreenDevice(), value);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotationManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */