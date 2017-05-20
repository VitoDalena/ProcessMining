/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.DefaultParallelEdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.IncidentEdgeIndexFunction;
/*     */ import edu.uci.ics.jung.visualization.decorators.ConstantDirectionalEdgeValueTransformer;
/*     */ import edu.uci.ics.jung.visualization.decorators.DirectionalEdgeArrowTransformer;
/*     */ import edu.uci.ics.jung.visualization.decorators.EdgeShape.IndexedRendering;
/*     */ import edu.uci.ics.jung.visualization.decorators.EdgeShape.Orthogonal;
/*     */ import edu.uci.ics.jung.visualization.decorators.EdgeShape.QuadCurve;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.EdgeLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.VertexLabelRenderer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D.Float;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections15.functors.TruePredicate;
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
/*     */ public class PluggableRenderContext<V, E>
/*     */   implements RenderContext<V, E>
/*     */ {
/*  49 */   protected float arrowPlacementTolerance = 1.0F;
/*  50 */   protected Predicate<Context<Graph<V, E>, V>> vertexIncludePredicate = TruePredicate.getInstance();
/*  51 */   protected Transformer<V, Stroke> vertexStrokeTransformer = new ConstantTransformer(new BasicStroke(1.0F));
/*     */   
/*     */ 
/*  54 */   protected Transformer<V, Shape> vertexShapeTransformer = new ConstantTransformer(new Ellipse2D.Float(-10.0F, -10.0F, 20.0F, 20.0F));
/*     */   
/*     */ 
/*     */ 
/*  58 */   protected Transformer<V, String> vertexLabelTransformer = new ConstantTransformer(null);
/*     */   protected Transformer<V, Icon> vertexIconTransformer;
/*  60 */   protected Transformer<V, Font> vertexFontTransformer = new ConstantTransformer(new Font("Helvetica", 0, 12));
/*     */   
/*     */ 
/*  63 */   protected Transformer<V, Paint> vertexDrawPaintTransformer = new ConstantTransformer(Color.BLACK);
/*  64 */   protected Transformer<V, Paint> vertexFillPaintTransformer = new ConstantTransformer(Color.RED);
/*     */   
/*  66 */   protected Transformer<E, String> edgeLabelTransformer = new ConstantTransformer(null);
/*  67 */   protected Transformer<E, Stroke> edgeStrokeTransformer = new ConstantTransformer(new BasicStroke(1.0F));
/*  68 */   protected Transformer<E, Stroke> edgeArrowStrokeTransformer = new ConstantTransformer(new BasicStroke(1.0F));
/*     */   
/*  70 */   protected Transformer<Context<Graph<V, E>, E>, Shape> edgeArrowTransformer = new DirectionalEdgeArrowTransformer(10, 8, 4);
/*     */   
/*     */ 
/*  73 */   protected Predicate<Context<Graph<V, E>, E>> edgeArrowPredicate = new RenderContext.DirectedEdgeArrowPredicate();
/*  74 */   protected Predicate<Context<Graph<V, E>, E>> edgeIncludePredicate = TruePredicate.getInstance();
/*  75 */   protected Transformer<E, Font> edgeFontTransformer = new ConstantTransformer(new Font("Helvetica", 0, 12));
/*     */   
/*  77 */   protected Transformer<Context<Graph<V, E>, E>, Number> edgeLabelClosenessTransformer = new ConstantDirectionalEdgeValueTransformer(0.5D, 0.65D);
/*     */   
/*  79 */   protected Transformer<Context<Graph<V, E>, E>, Shape> edgeShapeTransformer = new EdgeShape.QuadCurve();
/*     */   
/*  81 */   protected Transformer<E, Paint> edgeFillPaintTransformer = new ConstantTransformer(null);
/*     */   
/*  83 */   protected Transformer<E, Paint> edgeDrawPaintTransformer = new ConstantTransformer(Color.black);
/*     */   
/*  85 */   protected Transformer<E, Paint> arrowFillPaintTransformer = new ConstantTransformer(Color.black);
/*     */   
/*  87 */   protected Transformer<E, Paint> arrowDrawPaintTransformer = new ConstantTransformer(Color.black);
/*     */   
/*     */ 
/*  90 */   protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction = DefaultParallelEdgeIndexFunction.getInstance();
/*     */   
/*     */ 
/*  93 */   protected EdgeIndexFunction<V, E> incidentEdgeIndexFunction = IncidentEdgeIndexFunction.getInstance();
/*     */   
/*     */ 
/*  96 */   protected MultiLayerTransformer multiLayerTransformer = new BasicTransformer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected GraphElementAccessor<V, E> pickSupport;
/*     */   
/*     */ 
/*     */ 
/* 105 */   protected int labelOffset = 10;
/*     */   
/*     */ 
/*     */ 
/*     */   protected JComponent screenDevice;
/*     */   
/*     */ 
/*     */ 
/*     */   protected PickedState<V> pickedVertexState;
/*     */   
/*     */ 
/*     */ 
/*     */   protected PickedState<E> pickedEdgeState;
/*     */   
/*     */ 
/* 120 */   protected CellRendererPane rendererPane = new CellRendererPane();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */   protected VertexLabelRenderer vertexLabelRenderer = new DefaultVertexLabelRenderer(Color.blue);
/*     */   
/*     */ 
/* 129 */   protected EdgeLabelRenderer edgeLabelRenderer = new DefaultEdgeLabelRenderer(Color.cyan);
/*     */   protected GraphicsDecorator graphicsContext;
/*     */   
/*     */   PluggableRenderContext()
/*     */   {
/* 134 */     setEdgeShapeTransformer(new EdgeShape.QuadCurve());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Shape> getVertexShapeTransformer()
/*     */   {
/* 141 */     return this.vertexShapeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexShapeTransformer(Transformer<V, Shape> vertexShapeTransformer)
/*     */   {
/* 149 */     this.vertexShapeTransformer = vertexShapeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Stroke> getVertexStrokeTransformer()
/*     */   {
/* 156 */     return this.vertexStrokeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexStrokeTransformer(Transformer<V, Stroke> vertexStrokeTransformer)
/*     */   {
/* 164 */     this.vertexStrokeTransformer = vertexStrokeTransformer;
/*     */   }
/*     */   
/*     */   public static float[] getDashing() {
/* 168 */     return dashing;
/*     */   }
/*     */   
/*     */   public static float[] getDotting() {
/* 172 */     return dotting;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getArrowPlacementTolerance()
/*     */   {
/* 179 */     return this.arrowPlacementTolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setArrowPlacementTolerance(float arrow_placement_tolerance)
/*     */   {
/* 186 */     this.arrowPlacementTolerance = arrow_placement_tolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<Context<Graph<V, E>, E>, Shape> getEdgeArrowTransformer()
/*     */   {
/* 193 */     return this.edgeArrowTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeArrowTransformer(Transformer<Context<Graph<V, E>, E>, Shape> edgeArrowTransformer)
/*     */   {
/* 200 */     this.edgeArrowTransformer = edgeArrowTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Predicate<Context<Graph<V, E>, E>> getEdgeArrowPredicate()
/*     */   {
/* 207 */     return this.edgeArrowPredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeArrowPredicate(Predicate<Context<Graph<V, E>, E>> edgeArrowPredicate)
/*     */   {
/* 214 */     this.edgeArrowPredicate = edgeArrowPredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Font> getEdgeFontTransformer()
/*     */   {
/* 221 */     return this.edgeFontTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeFontTransformer(Transformer<E, Font> edgeFontTransformer)
/*     */   {
/* 228 */     this.edgeFontTransformer = edgeFontTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Predicate<Context<Graph<V, E>, E>> getEdgeIncludePredicate()
/*     */   {
/* 235 */     return this.edgeIncludePredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeIncludePredicate(Predicate<Context<Graph<V, E>, E>> edgeIncludePredicate)
/*     */   {
/* 242 */     this.edgeIncludePredicate = edgeIncludePredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<Context<Graph<V, E>, E>, Number> getEdgeLabelClosenessTransformer()
/*     */   {
/* 249 */     return this.edgeLabelClosenessTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeLabelClosenessTransformer(Transformer<Context<Graph<V, E>, E>, Number> edgeLabelClosenessTransformer)
/*     */   {
/* 257 */     this.edgeLabelClosenessTransformer = edgeLabelClosenessTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EdgeLabelRenderer getEdgeLabelRenderer()
/*     */   {
/* 264 */     return this.edgeLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeLabelRenderer(EdgeLabelRenderer edgeLabelRenderer)
/*     */   {
/* 271 */     this.edgeLabelRenderer = edgeLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Paint> getEdgeFillPaintTransformer()
/*     */   {
/* 278 */     return this.edgeFillPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeDrawPaintTransformer(Transformer<E, Paint> edgeDrawPaintTransformer)
/*     */   {
/* 285 */     this.edgeDrawPaintTransformer = edgeDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Paint> getEdgeDrawPaintTransformer()
/*     */   {
/* 292 */     return this.edgeDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeFillPaintTransformer(Transformer<E, Paint> edgeFillPaintTransformer)
/*     */   {
/* 299 */     this.edgeFillPaintTransformer = edgeFillPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<Context<Graph<V, E>, E>, Shape> getEdgeShapeTransformer()
/*     */   {
/* 306 */     return this.edgeShapeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeShapeTransformer(Transformer<Context<Graph<V, E>, E>, Shape> edgeShapeTransformer)
/*     */   {
/* 313 */     this.edgeShapeTransformer = edgeShapeTransformer;
/* 314 */     if ((edgeShapeTransformer instanceof EdgeShape.Orthogonal)) {
/* 315 */       ((EdgeShape.IndexedRendering)edgeShapeTransformer).setEdgeIndexFunction(this.incidentEdgeIndexFunction);
/*     */     }
/* 317 */     else if ((edgeShapeTransformer instanceof EdgeShape.IndexedRendering)) {
/* 318 */       ((EdgeShape.IndexedRendering)edgeShapeTransformer).setEdgeIndexFunction(this.parallelEdgeIndexFunction);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, String> getEdgeLabelTransformer()
/*     */   {
/* 326 */     return this.edgeLabelTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeLabelTransformer(Transformer<E, String> edgeLabelTransformer)
/*     */   {
/* 333 */     this.edgeLabelTransformer = edgeLabelTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Stroke> getEdgeStrokeTransformer()
/*     */   {
/* 340 */     return this.edgeStrokeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeStrokeTransformer(Transformer<E, Stroke> edgeStrokeTransformer)
/*     */   {
/* 347 */     this.edgeStrokeTransformer = edgeStrokeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Stroke> getEdgeArrowStrokeTransformer()
/*     */   {
/* 354 */     return this.edgeArrowStrokeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeArrowStrokeTransformer(Transformer<E, Stroke> edgeArrowStrokeTransformer)
/*     */   {
/* 361 */     this.edgeArrowStrokeTransformer = edgeArrowStrokeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphicsDecorator getGraphicsContext()
/*     */   {
/* 368 */     return this.graphicsContext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setGraphicsContext(GraphicsDecorator graphicsContext)
/*     */   {
/* 375 */     this.graphicsContext = graphicsContext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getLabelOffset()
/*     */   {
/* 382 */     return this.labelOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLabelOffset(int labelOffset)
/*     */   {
/* 389 */     this.labelOffset = labelOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EdgeIndexFunction<V, E> getParallelEdgeIndexFunction()
/*     */   {
/* 396 */     return this.parallelEdgeIndexFunction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setParallelEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */   {
/* 404 */     this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/*     */     
/*     */ 
/* 407 */     setEdgeShapeTransformer(getEdgeShapeTransformer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PickedState<E> getPickedEdgeState()
/*     */   {
/* 414 */     return this.pickedEdgeState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPickedEdgeState(PickedState<E> pickedEdgeState)
/*     */   {
/* 421 */     this.pickedEdgeState = pickedEdgeState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PickedState<V> getPickedVertexState()
/*     */   {
/* 428 */     return this.pickedVertexState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPickedVertexState(PickedState<V> pickedVertexState)
/*     */   {
/* 435 */     this.pickedVertexState = pickedVertexState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public CellRendererPane getRendererPane()
/*     */   {
/* 442 */     return this.rendererPane;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRendererPane(CellRendererPane rendererPane)
/*     */   {
/* 449 */     this.rendererPane = rendererPane;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JComponent getScreenDevice()
/*     */   {
/* 456 */     return this.screenDevice;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setScreenDevice(JComponent screenDevice)
/*     */   {
/* 463 */     this.screenDevice = screenDevice;
/* 464 */     screenDevice.add(this.rendererPane);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Font> getVertexFontTransformer()
/*     */   {
/* 471 */     return this.vertexFontTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexFontTransformer(Transformer<V, Font> vertexFontTransformer)
/*     */   {
/* 478 */     this.vertexFontTransformer = vertexFontTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Icon> getVertexIconTransformer()
/*     */   {
/* 485 */     return this.vertexIconTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexIconTransformer(Transformer<V, Icon> vertexIconTransformer)
/*     */   {
/* 492 */     this.vertexIconTransformer = vertexIconTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Predicate<Context<Graph<V, E>, V>> getVertexIncludePredicate()
/*     */   {
/* 499 */     return this.vertexIncludePredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexIncludePredicate(Predicate<Context<Graph<V, E>, V>> vertexIncludePredicate)
/*     */   {
/* 506 */     this.vertexIncludePredicate = vertexIncludePredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public VertexLabelRenderer getVertexLabelRenderer()
/*     */   {
/* 513 */     return this.vertexLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexLabelRenderer(VertexLabelRenderer vertexLabelRenderer)
/*     */   {
/* 520 */     this.vertexLabelRenderer = vertexLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Paint> getVertexFillPaintTransformer()
/*     */   {
/* 527 */     return this.vertexFillPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexFillPaintTransformer(Transformer<V, Paint> vertexFillPaintTransformer)
/*     */   {
/* 534 */     this.vertexFillPaintTransformer = vertexFillPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Paint> getVertexDrawPaintTransformer()
/*     */   {
/* 541 */     return this.vertexDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexDrawPaintTransformer(Transformer<V, Paint> vertexDrawPaintTransformer)
/*     */   {
/* 548 */     this.vertexDrawPaintTransformer = vertexDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, String> getVertexLabelTransformer()
/*     */   {
/* 555 */     return this.vertexLabelTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setVertexLabelTransformer(Transformer<V, String> vertexLabelTransformer)
/*     */   {
/* 562 */     this.vertexLabelTransformer = vertexLabelTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphElementAccessor<V, E> getPickSupport()
/*     */   {
/* 569 */     return this.pickSupport;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPickSupport(GraphElementAccessor<V, E> pickSupport)
/*     */   {
/* 576 */     this.pickSupport = pickSupport;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MultiLayerTransformer getMultiLayerTransformer()
/*     */   {
/* 583 */     return this.multiLayerTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMultiLayerTransformer(MultiLayerTransformer basicTransformer)
/*     */   {
/* 590 */     this.multiLayerTransformer = basicTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Paint> getArrowDrawPaintTransformer()
/*     */   {
/* 597 */     return this.arrowDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<E, Paint> getArrowFillPaintTransformer()
/*     */   {
/* 604 */     return this.arrowFillPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setArrowDrawPaintTransformer(Transformer<E, Paint> arrowDrawPaintTransformer)
/*     */   {
/* 611 */     this.arrowDrawPaintTransformer = arrowDrawPaintTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrowFillPaintTransformer(Transformer<E, Paint> arrowFillPaintTransformer)
/*     */   {
/* 619 */     this.arrowFillPaintTransformer = arrowFillPaintTransformer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/PluggableRenderContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */