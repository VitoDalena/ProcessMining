/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GradientVertexRenderer<V, E>
/*     */   implements Renderer.Vertex<V, E>
/*     */ {
/*     */   Color colorOne;
/*     */   Color colorTwo;
/*     */   Color pickedColorOne;
/*     */   Color pickedColorTwo;
/*     */   PickedState<V> pickedState;
/*     */   boolean cyclic;
/*     */   
/*     */   public GradientVertexRenderer(Color colorOne, Color colorTwo, boolean cyclic)
/*     */   {
/*  50 */     this.colorOne = colorOne;
/*  51 */     this.colorTwo = colorTwo;
/*  52 */     this.cyclic = cyclic;
/*     */   }
/*     */   
/*     */   public GradientVertexRenderer(Color colorOne, Color colorTwo, Color pickedColorOne, Color pickedColorTwo, PickedState<V> pickedState, boolean cyclic)
/*     */   {
/*  57 */     this.colorOne = colorOne;
/*  58 */     this.colorTwo = colorTwo;
/*  59 */     this.pickedColorOne = pickedColorOne;
/*  60 */     this.pickedColorTwo = pickedColorTwo;
/*  61 */     this.pickedState = pickedState;
/*  62 */     this.cyclic = cyclic;
/*     */   }
/*     */   
/*     */   public void paintVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v)
/*     */   {
/*  67 */     Graph<V, E> graph = layout.getGraph();
/*  68 */     if (rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v))) {
/*  69 */       boolean vertexHit = true;
/*     */       
/*  71 */       Shape shape = (Shape)rc.getVertexShapeTransformer().transform(v);
/*     */       
/*  73 */       Point2D p = (Point2D)layout.transform(v);
/*  74 */       p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*     */       
/*  76 */       float x = (float)p.getX();
/*  77 */       float y = (float)p.getY();
/*     */       
/*     */ 
/*     */ 
/*  81 */       AffineTransform xform = AffineTransform.getTranslateInstance(x, y);
/*     */       
/*  83 */       shape = xform.createTransformedShape(shape);
/*     */       
/*  85 */       vertexHit = vertexHit(rc, shape);
/*     */       
/*     */ 
/*  88 */       if (vertexHit) {
/*  89 */         paintShapeForVertex(rc, v, shape);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean vertexHit(RenderContext<V, E> rc, Shape s) {
/*  95 */     JComponent vv = rc.getScreenDevice();
/*  96 */     Rectangle deviceRectangle = null;
/*  97 */     if (vv != null) {
/*  98 */       Dimension d = vv.getSize();
/*  99 */       deviceRectangle = new Rectangle(0, 0, d.width, d.height);
/*     */     }
/*     */     
/*     */ 
/* 103 */     return rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(s).intersects(deviceRectangle);
/*     */   }
/*     */   
/*     */   protected void paintShapeForVertex(RenderContext<V, E> rc, V v, Shape shape) {
/* 107 */     GraphicsDecorator g = rc.getGraphicsContext();
/* 108 */     Paint oldPaint = g.getPaint();
/* 109 */     Rectangle r = shape.getBounds();
/* 110 */     float y2 = (float)r.getMaxY();
/* 111 */     if (this.cyclic) {
/* 112 */       y2 = (float)(r.getMinY() + r.getHeight() / 2.0D);
/*     */     }
/*     */     
/* 115 */     Paint fillPaint = null;
/* 116 */     if ((this.pickedState != null) && (this.pickedState.isPicked(v))) {
/* 117 */       fillPaint = new GradientPaint((float)r.getMinX(), (float)r.getMinY(), this.pickedColorOne, (float)r.getMinX(), y2, this.pickedColorTwo, this.cyclic);
/*     */     }
/*     */     else {
/* 120 */       fillPaint = new GradientPaint((float)r.getMinX(), (float)r.getMinY(), this.colorOne, (float)r.getMinX(), y2, this.colorTwo, this.cyclic);
/*     */     }
/*     */     
/* 123 */     if (fillPaint != null) {
/* 124 */       g.setPaint(fillPaint);
/* 125 */       g.fill(shape);
/* 126 */       g.setPaint(oldPaint);
/*     */     }
/* 128 */     Paint drawPaint = (Paint)rc.getVertexDrawPaintTransformer().transform(v);
/* 129 */     if (drawPaint != null) {
/* 130 */       g.setPaint(drawPaint);
/*     */     }
/* 132 */     Stroke oldStroke = g.getStroke();
/* 133 */     Stroke stroke = (Stroke)rc.getVertexStrokeTransformer().transform(v);
/* 134 */     if (stroke != null) {
/* 135 */       g.setStroke(stroke);
/*     */     }
/* 137 */     g.draw(shape);
/* 138 */     g.setPaint(oldPaint);
/* 139 */     g.setStroke(oldStroke);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/GradientVertexRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */