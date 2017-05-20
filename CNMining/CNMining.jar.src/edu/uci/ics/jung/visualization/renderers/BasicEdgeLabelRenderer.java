/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicEdgeLabelRenderer<V, E>
/*     */   implements Renderer.EdgeLabel<V, E>
/*     */ {
/*     */   public Component prepareRenderer(RenderContext<V, E> rc, EdgeLabelRenderer graphLabelRenderer, Object value, boolean isSelected, E edge)
/*     */   {
/*  31 */     return rc.getEdgeLabelRenderer().getEdgeLabelRendererComponent(rc.getScreenDevice(), value, (Font)rc.getEdgeFontTransformer().transform(edge), isSelected, edge);
/*     */   }
/*     */   
/*     */   public void labelEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e, String label)
/*     */   {
/*  36 */     if ((label == null) || (label.length() == 0)) { return;
/*     */     }
/*  38 */     Graph<V, E> graph = layout.getGraph();
/*     */     
/*  40 */     Pair<V> endpoints = graph.getEndpoints(e);
/*  41 */     V v1 = endpoints.getFirst();
/*  42 */     V v2 = endpoints.getSecond();
/*  43 */     if (!rc.getEdgeIncludePredicate().evaluate(Context.getInstance(graph, e))) {
/*  44 */       return;
/*     */     }
/*  46 */     if ((!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v1))) || (!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v2))))
/*     */     {
/*  48 */       return;
/*     */     }
/*  50 */     Point2D p1 = (Point2D)layout.transform(v1);
/*  51 */     Point2D p2 = (Point2D)layout.transform(v2);
/*  52 */     p1 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p1);
/*  53 */     p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
/*  54 */     float x1 = (float)p1.getX();
/*  55 */     float y1 = (float)p1.getY();
/*  56 */     float x2 = (float)p2.getX();
/*  57 */     float y2 = (float)p2.getY();
/*     */     
/*  59 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  60 */     float distX = x2 - x1;
/*  61 */     float distY = y2 - y1;
/*  62 */     double totalLength = Math.sqrt(distX * distX + distY * distY);
/*     */     
/*  64 */     double closeness = ((Number)rc.getEdgeLabelClosenessTransformer().transform(Context.getInstance(graph, e))).doubleValue();
/*     */     
/*  66 */     int posX = (int)(x1 + closeness * distX);
/*  67 */     int posY = (int)(y1 + closeness * distY);
/*     */     
/*  69 */     int xDisplacement = (int)(rc.getLabelOffset() * (distY / totalLength));
/*  70 */     int yDisplacement = (int)(rc.getLabelOffset() * (-distX / totalLength));
/*     */     
/*  72 */     Component component = prepareRenderer(rc, rc.getEdgeLabelRenderer(), label, rc.getPickedEdgeState().isPicked(e), e);
/*     */     
/*     */ 
/*  75 */     Dimension d = component.getPreferredSize();
/*     */     
/*  77 */     Shape edgeShape = (Shape)rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));
/*     */     
/*  79 */     double parallelOffset = 1.0D;
/*     */     
/*  81 */     parallelOffset += rc.getParallelEdgeIndexFunction().getIndex(graph, e);
/*     */     
/*  83 */     parallelOffset *= d.height;
/*  84 */     if ((edgeShape instanceof Ellipse2D)) {
/*  85 */       parallelOffset += edgeShape.getBounds().getHeight();
/*  86 */       parallelOffset = -parallelOffset;
/*     */     }
/*     */     
/*     */ 
/*  90 */     AffineTransform old = g.getTransform();
/*  91 */     AffineTransform xform = new AffineTransform(old);
/*  92 */     xform.translate(posX + xDisplacement, posY + yDisplacement);
/*  93 */     double dx = x2 - x1;
/*  94 */     double dy = y2 - y1;
/*  95 */     if (rc.getEdgeLabelRenderer().isRotateEdgeLabels()) {
/*  96 */       double theta = Math.atan2(dy, dx);
/*  97 */       if (dx < 0.0D) {
/*  98 */         theta += 3.141592653589793D;
/*     */       }
/* 100 */       xform.rotate(theta);
/*     */     }
/* 102 */     if (dx < 0.0D) {
/* 103 */       parallelOffset = -parallelOffset;
/*     */     }
/*     */     
/* 106 */     xform.translate(-d.width / 2, -(d.height / 2 - parallelOffset));
/* 107 */     g.setTransform(xform);
/* 108 */     g.draw(component, rc.getRendererPane(), 0, 0, d.width, d.height, true);
/*     */     
/* 110 */     g.setTransform(old);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/BasicEdgeLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */