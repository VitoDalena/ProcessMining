/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
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
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VertexLabelAsShapeRenderer<V, E>
/*     */   implements Renderer.VertexLabel<V, E>, Transformer<V, Shape>
/*     */ {
/*  43 */   protected Map<V, Shape> shapes = new HashMap();
/*     */   protected RenderContext<V, E> rc;
/*     */   
/*     */   public VertexLabelAsShapeRenderer(RenderContext<V, E> rc) {
/*  47 */     this.rc = rc;
/*     */   }
/*     */   
/*     */   public Component prepareRenderer(RenderContext<V, E> rc, VertexLabelRenderer graphLabelRenderer, Object value, boolean isSelected, V vertex)
/*     */   {
/*  52 */     return rc.getVertexLabelRenderer().getVertexLabelRendererComponent(rc.getScreenDevice(), value, (Font)rc.getVertexFontTransformer().transform(vertex), isSelected, vertex);
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
/*     */   public void labelVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v, String label)
/*     */   {
/*  65 */     Graph<V, E> graph = layout.getGraph();
/*  66 */     if (!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v))) {
/*  67 */       return;
/*     */     }
/*  69 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  70 */     Component component = prepareRenderer(rc, rc.getVertexLabelRenderer(), label, rc.getPickedVertexState().isPicked(v), v);
/*     */     
/*  72 */     Dimension d = component.getPreferredSize();
/*     */     
/*  74 */     int h_offset = -d.width / 2;
/*  75 */     int v_offset = -d.height / 2;
/*     */     
/*  77 */     Point2D p = (Point2D)layout.transform(v);
/*  78 */     p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*     */     
/*  80 */     int x = (int)p.getX();
/*  81 */     int y = (int)p.getY();
/*     */     
/*  83 */     g.draw(component, rc.getRendererPane(), x + h_offset, y + v_offset, d.width, d.height, true);
/*     */     
/*  85 */     Dimension size = component.getPreferredSize();
/*  86 */     Rectangle bounds = new Rectangle(-size.width / 2 - 2, -size.height / 2 - 2, size.width + 4, size.height);
/*  87 */     this.shapes.put(v, bounds);
/*     */   }
/*     */   
/*     */   public Shape transform(V v) {
/*  91 */     Component component = prepareRenderer(this.rc, this.rc.getVertexLabelRenderer(), this.rc.getVertexLabelTransformer().transform(v), this.rc.getPickedVertexState().isPicked(v), v);
/*     */     
/*  93 */     Dimension size = component.getPreferredSize();
/*  94 */     Rectangle bounds = new Rectangle(-size.width / 2 - 2, -size.height / 2 - 2, size.width + 4, size.height);
/*  95 */     return bounds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Renderer.VertexLabel.Position getPosition()
/*     */   {
/* 104 */     return Renderer.VertexLabel.Position.CNTR;
/*     */   }
/*     */   
/*     */   public Renderer.VertexLabel.Positioner getPositioner() {
/* 108 */     new Renderer.VertexLabel.Positioner() {
/*     */       public Renderer.VertexLabel.Position getPosition(float x, float y, Dimension d) {
/* 110 */         return Renderer.VertexLabel.Position.CNTR;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   public void setPosition(Renderer.VertexLabel.Position position) {}
/*     */   
/*     */   public void setPositioner(Renderer.VertexLabel.Positioner positioner) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/VertexLabelAsShapeRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */