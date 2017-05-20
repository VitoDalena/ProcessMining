/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.transform.AffineTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.JComponent;
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
/*     */ public class AnnotationPaintable
/*     */   implements VisualizationServer.Paintable
/*     */ {
/*  43 */   protected Set<Annotation> annotations = new HashSet();
/*     */   protected AnnotationRenderer annotationRenderer;
/*     */   protected RenderContext<?, ?> rc;
/*     */   protected AffineTransformer transformer;
/*     */   
/*     */   public AnnotationPaintable(RenderContext<?, ?> rc, AnnotationRenderer annotationRenderer)
/*     */   {
/*  50 */     this.rc = rc;
/*  51 */     this.annotationRenderer = annotationRenderer;
/*  52 */     MutableTransformer mt = rc.getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*  53 */     if ((mt instanceof AffineTransformer)) {
/*  54 */       this.transformer = ((AffineTransformer)mt);
/*  55 */     } else if ((mt instanceof LensTransformer)) {
/*  56 */       this.transformer = ((AffineTransformer)((LensTransformer)mt).getDelegate());
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(Annotation annotation)
/*     */   {
/*  62 */     this.annotations.add(annotation);
/*     */   }
/*     */   
/*     */   public void remove(Annotation annotation)
/*     */   {
/*  67 */     this.annotations.remove(annotation);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Annotation> getAnnotations()
/*     */   {
/*  75 */     return Collections.unmodifiableSet(this.annotations);
/*     */   }
/*     */   
/*     */   public void paint(Graphics g)
/*     */   {
/*  80 */     Graphics2D g2d = (Graphics2D)g;
/*  81 */     Color oldColor = g.getColor();
/*  82 */     for (Annotation annotation : this.annotations) {
/*  83 */       Object ann = annotation.getAnnotation();
/*  84 */       if ((ann instanceof Shape)) {
/*  85 */         Shape shape = (Shape)ann;
/*  86 */         Paint paint = annotation.getPaint();
/*  87 */         Shape s = this.transformer.transform(shape);
/*  88 */         g2d.setPaint(paint);
/*  89 */         if (annotation.isFill()) {
/*  90 */           g2d.fill(s);
/*     */         } else {
/*  92 */           g2d.draw(s);
/*     */         }
/*  94 */       } else if ((ann instanceof String)) {
/*  95 */         Point2D p = annotation.getLocation();
/*  96 */         String label = (String)ann;
/*  97 */         Component component = prepareRenderer(this.rc, this.annotationRenderer, label);
/*  98 */         component.setForeground((Color)annotation.getPaint());
/*  99 */         if (annotation.isFill()) {
/* 100 */           ((JComponent)component).setOpaque(true);
/* 101 */           component.setBackground((Color)annotation.getPaint());
/* 102 */           component.setForeground(Color.black);
/*     */         }
/* 104 */         Dimension d = component.getPreferredSize();
/* 105 */         AffineTransform old = g2d.getTransform();
/* 106 */         AffineTransform base = new AffineTransform(old);
/* 107 */         AffineTransform xform = this.transformer.getTransform();
/*     */         
/* 109 */         double rotation = this.transformer.getRotation();
/*     */         
/* 111 */         AffineTransform unrotate = AffineTransform.getRotateInstance(-rotation, p.getX(), p.getY());
/*     */         
/* 113 */         base.concatenate(xform);
/* 114 */         base.concatenate(unrotate);
/* 115 */         g2d.setTransform(base);
/* 116 */         this.rc.getRendererPane().paintComponent(g, component, this.rc.getScreenDevice(), (int)p.getX(), (int)p.getY(), d.width, d.height, true);
/*     */         
/*     */ 
/* 119 */         g2d.setTransform(old);
/*     */       }
/*     */     }
/* 122 */     g.setColor(oldColor);
/*     */   }
/*     */   
/*     */   public Component prepareRenderer(RenderContext<?, ?> rc, AnnotationRenderer annotationRenderer, Object value) {
/* 126 */     return annotationRenderer.getAnnotationRendererComponent(rc.getScreenDevice(), value);
/*     */   }
/*     */   
/*     */   public boolean useTransform() {
/* 130 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotationPaintable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */