/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformerDecorator;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicVertexRenderer<V, E>
/*     */   implements Renderer.Vertex<V, E>
/*     */ {
/*     */   public void paintVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v)
/*     */   {
/*  35 */     Graph<V, E> graph = layout.getGraph();
/*  36 */     if (rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v))) {
/*  37 */       paintIconForVertex(rc, v, layout);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void paintIconForVertex(RenderContext<V, E> rc, V v, Layout<V, E> layout)
/*     */   {
/*  45 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  46 */     boolean vertexHit = true;
/*     */     
/*  48 */     Shape shape = (Shape)rc.getVertexShapeTransformer().transform(v);
/*     */     
/*  50 */     Point2D p = (Point2D)layout.transform(v);
/*  51 */     p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*  52 */     float x = (float)p.getX();
/*  53 */     float y = (float)p.getY();
/*     */     
/*     */ 
/*  56 */     AffineTransform xform = AffineTransform.getTranslateInstance(x, y);
/*     */     
/*  58 */     shape = xform.createTransformedShape(shape);
/*     */     
/*  60 */     vertexHit = vertexHit(rc, shape);
/*     */     
/*     */ 
/*  63 */     if (vertexHit) {
/*  64 */       if (rc.getVertexIconTransformer() != null) {
/*  65 */         Icon icon = (Icon)rc.getVertexIconTransformer().transform(v);
/*  66 */         if (icon != null)
/*     */         {
/*  68 */           g.draw(icon, rc.getScreenDevice(), shape, (int)x, (int)y);
/*     */         }
/*     */         else {
/*  71 */           paintShapeForVertex(rc, v, shape);
/*     */         }
/*     */       } else {
/*  74 */         paintShapeForVertex(rc, v, shape);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean vertexHit(RenderContext<V, E> rc, Shape s) {
/*  80 */     JComponent vv = rc.getScreenDevice();
/*  81 */     Rectangle deviceRectangle = null;
/*  82 */     if (vv != null) {
/*  83 */       Dimension d = vv.getSize();
/*  84 */       deviceRectangle = new Rectangle(0, 0, d.width, d.height);
/*     */     }
/*     */     
/*     */ 
/*  88 */     MutableTransformer vt = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW);
/*  89 */     if ((vt instanceof MutableTransformerDecorator)) {
/*  90 */       vt = ((MutableTransformerDecorator)vt).getDelegate();
/*     */     }
/*  92 */     return vt.transform(s).intersects(deviceRectangle);
/*     */   }
/*     */   
/*     */   protected void paintShapeForVertex(RenderContext<V, E> rc, V v, Shape shape) {
/*  96 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  97 */     Paint oldPaint = g.getPaint();
/*  98 */     Paint fillPaint = (Paint)rc.getVertexFillPaintTransformer().transform(v);
/*  99 */     if (fillPaint != null) {
/* 100 */       g.setPaint(fillPaint);
/* 101 */       g.fill(shape);
/* 102 */       g.setPaint(oldPaint);
/*     */     }
/* 104 */     Paint drawPaint = (Paint)rc.getVertexDrawPaintTransformer().transform(v);
/* 105 */     if (drawPaint != null) {
/* 106 */       g.setPaint(drawPaint);
/* 107 */       Stroke oldStroke = g.getStroke();
/* 108 */       Stroke stroke = (Stroke)rc.getVertexStrokeTransformer().transform(v);
/* 109 */       if (stroke != null) {
/* 110 */         g.setStroke(stroke);
/*     */       }
/* 112 */       g.draw(shape);
/* 113 */       g.setPaint(oldPaint);
/* 114 */       g.setStroke(oldStroke);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/BasicVertexRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */