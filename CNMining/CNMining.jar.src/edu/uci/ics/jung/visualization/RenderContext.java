/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.renderers.EdgeLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.VertexLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract interface RenderContext<V, E>
/*     */ {
/*  28 */   public static final float[] dotting = { 1.0F, 3.0F };
/*  29 */   public static final float[] dashing = { 5.0F };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  35 */   public static final Stroke DOTTED = new BasicStroke(1.0F, 1, 1, 1.0F, dotting, 0.0F);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  42 */   public static final Stroke DASHED = new BasicStroke(1.0F, 2, 2, 1.0F, dashing, 0.0F);
/*     */   
/*     */   public static final int LABEL_OFFSET = 10;
/*     */   
/*     */ 
/*     */   public abstract int getLabelOffset();
/*     */   
/*     */   public abstract void setLabelOffset(int paramInt);
/*     */   
/*     */   public abstract float getArrowPlacementTolerance();
/*     */   
/*     */   public abstract void setArrowPlacementTolerance(float paramFloat);
/*     */   
/*     */   public abstract Transformer<Context<Graph<V, E>, E>, Shape> getEdgeArrowTransformer();
/*     */   
/*     */   public abstract void setEdgeArrowTransformer(Transformer<Context<Graph<V, E>, E>, Shape> paramTransformer);
/*     */   
/*     */   public abstract Predicate<Context<Graph<V, E>, E>> getEdgeArrowPredicate();
/*     */   
/*     */   public abstract void setEdgeArrowPredicate(Predicate<Context<Graph<V, E>, E>> paramPredicate);
/*     */   
/*     */   public abstract Transformer<E, Font> getEdgeFontTransformer();
/*     */   
/*     */   public abstract void setEdgeFontTransformer(Transformer<E, Font> paramTransformer);
/*     */   
/*     */   public abstract Predicate<Context<Graph<V, E>, E>> getEdgeIncludePredicate();
/*     */   
/*     */   public abstract void setEdgeIncludePredicate(Predicate<Context<Graph<V, E>, E>> paramPredicate);
/*     */   
/*     */   public abstract Transformer<Context<Graph<V, E>, E>, Number> getEdgeLabelClosenessTransformer();
/*     */   
/*     */   public abstract void setEdgeLabelClosenessTransformer(Transformer<Context<Graph<V, E>, E>, Number> paramTransformer);
/*     */   
/*     */   public abstract EdgeLabelRenderer getEdgeLabelRenderer();
/*     */   
/*     */   public abstract void setEdgeLabelRenderer(EdgeLabelRenderer paramEdgeLabelRenderer);
/*     */   
/*     */   public abstract Transformer<E, Paint> getEdgeFillPaintTransformer();
/*     */   
/*     */   public abstract void setEdgeFillPaintTransformer(Transformer<E, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, Paint> getEdgeDrawPaintTransformer();
/*     */   
/*     */   public abstract void setEdgeDrawPaintTransformer(Transformer<E, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, Paint> getArrowDrawPaintTransformer();
/*     */   
/*     */   public abstract void setArrowDrawPaintTransformer(Transformer<E, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, Paint> getArrowFillPaintTransformer();
/*     */   
/*     */   public abstract void setArrowFillPaintTransformer(Transformer<E, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<Context<Graph<V, E>, E>, Shape> getEdgeShapeTransformer();
/*     */   
/*     */   public abstract void setEdgeShapeTransformer(Transformer<Context<Graph<V, E>, E>, Shape> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, String> getEdgeLabelTransformer();
/*     */   
/*     */   public abstract void setEdgeLabelTransformer(Transformer<E, String> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, Stroke> getEdgeStrokeTransformer();
/*     */   
/*     */   public abstract void setEdgeStrokeTransformer(Transformer<E, Stroke> paramTransformer);
/*     */   
/*     */   public abstract Transformer<E, Stroke> getEdgeArrowStrokeTransformer();
/*     */   
/*     */   public abstract void setEdgeArrowStrokeTransformer(Transformer<E, Stroke> paramTransformer);
/*     */   
/*     */   public abstract GraphicsDecorator getGraphicsContext();
/*     */   
/*     */   public abstract void setGraphicsContext(GraphicsDecorator paramGraphicsDecorator);
/*     */   
/*     */   public abstract EdgeIndexFunction<V, E> getParallelEdgeIndexFunction();
/*     */   
/*     */   public abstract void setParallelEdgeIndexFunction(EdgeIndexFunction<V, E> paramEdgeIndexFunction);
/*     */   
/*     */   public abstract PickedState<E> getPickedEdgeState();
/*     */   
/*     */   public abstract void setPickedEdgeState(PickedState<E> paramPickedState);
/*     */   
/*     */   public abstract PickedState<V> getPickedVertexState();
/*     */   
/*     */   public abstract void setPickedVertexState(PickedState<V> paramPickedState);
/*     */   
/*     */   public abstract CellRendererPane getRendererPane();
/*     */   
/*     */   public abstract void setRendererPane(CellRendererPane paramCellRendererPane);
/*     */   
/*     */   public abstract JComponent getScreenDevice();
/*     */   
/*     */   public abstract void setScreenDevice(JComponent paramJComponent);
/*     */   
/*     */   public abstract Transformer<V, Font> getVertexFontTransformer();
/*     */   
/*     */   public abstract void setVertexFontTransformer(Transformer<V, Font> paramTransformer);
/*     */   
/*     */   public abstract Transformer<V, Icon> getVertexIconTransformer();
/*     */   
/*     */   public abstract void setVertexIconTransformer(Transformer<V, Icon> paramTransformer);
/*     */   
/*     */   public abstract Predicate<Context<Graph<V, E>, V>> getVertexIncludePredicate();
/*     */   
/*     */   public abstract void setVertexIncludePredicate(Predicate<Context<Graph<V, E>, V>> paramPredicate);
/*     */   
/*     */   public abstract VertexLabelRenderer getVertexLabelRenderer();
/*     */   
/*     */   public abstract void setVertexLabelRenderer(VertexLabelRenderer paramVertexLabelRenderer);
/*     */   
/*     */   public abstract Transformer<V, Paint> getVertexFillPaintTransformer();
/*     */   
/*     */   public abstract void setVertexFillPaintTransformer(Transformer<V, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<V, Paint> getVertexDrawPaintTransformer();
/*     */   
/*     */   public abstract void setVertexDrawPaintTransformer(Transformer<V, Paint> paramTransformer);
/*     */   
/*     */   public abstract Transformer<V, Shape> getVertexShapeTransformer();
/*     */   
/*     */   public abstract void setVertexShapeTransformer(Transformer<V, Shape> paramTransformer);
/*     */   
/*     */   public abstract Transformer<V, String> getVertexLabelTransformer();
/*     */   
/*     */   public abstract void setVertexLabelTransformer(Transformer<V, String> paramTransformer);
/*     */   
/*     */   public abstract Transformer<V, Stroke> getVertexStrokeTransformer();
/*     */   
/*     */   public abstract void setVertexStrokeTransformer(Transformer<V, Stroke> paramTransformer);
/*     */   
/*     */   public abstract MultiLayerTransformer getMultiLayerTransformer();
/*     */   
/*     */   public abstract void setMultiLayerTransformer(MultiLayerTransformer paramMultiLayerTransformer);
/*     */   
/*     */   public abstract GraphElementAccessor<V, E> getPickSupport();
/*     */   
/*     */   public abstract void setPickSupport(GraphElementAccessor<V, E> paramGraphElementAccessor);
/*     */   
/*     */   public static class DirectedEdgeArrowPredicate<V, E>
/*     */     implements Predicate<Context<Graph<V, E>, E>>
/*     */   {
/*     */     public boolean evaluate(Context<Graph<V, E>, E> c)
/*     */     {
/* 184 */       return ((Graph)c.graph).getEdgeType(c.element) == EdgeType.DIRECTED;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class UndirectedEdgeArrowPredicate<V, E>
/*     */     implements Predicate<Context<Graph<V, E>, E>>
/*     */   {
/*     */     public boolean evaluate(Context<Graph<V, E>, E> c)
/*     */     {
/* 194 */       return ((Graph)c.graph).getEdgeType(c.element) == EdgeType.UNDIRECTED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/RenderContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */