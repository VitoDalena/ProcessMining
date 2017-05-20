/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
/*     */ import edu.uci.ics.jung.visualization.control.ModalLensGraphMouse;
/*     */ import edu.uci.ics.jung.visualization.picking.ViewLensShapePickSupport;
/*     */ import edu.uci.ics.jung.visualization.renderers.BasicRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.Renderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.Renderer.Edge;
/*     */ import edu.uci.ics.jung.visualization.renderers.ReshapingEdgeRenderer;
/*     */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport;
/*     */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport.Lens;
/*     */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport.LensControls;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import java.awt.Dimension;
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
/*     */ public class MagnifyImageLensSupport<V, E>
/*     */   extends AbstractLensSupport<V, E>
/*     */ {
/*     */   protected RenderContext<V, E> renderContext;
/*     */   protected GraphicsDecorator lensGraphicsDecorator;
/*     */   protected GraphicsDecorator savedGraphicsDecorator;
/*     */   protected Renderer<V, E> renderer;
/*     */   protected Renderer<V, E> transformingRenderer;
/*     */   protected GraphElementAccessor<V, E> pickSupport;
/*     */   protected Renderer.Edge<V, E> savedEdgeRenderer;
/*     */   protected Renderer.Edge<V, E> reshapingEdgeRenderer;
/*     */   static final String instructions = "<html><center>Mouse-Drag the Lens center to move it<p>Mouse-Drag the Lens edge to resize it<p>Ctrl+MouseWheel to change magnification</center></html>";
/*     */   
/*     */   public MagnifyImageLensSupport(VisualizationViewer<V, E> vv)
/*     */   {
/*  50 */     this(vv, new MagnifyShapeTransformer(vv), new ModalLensGraphMouse());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MagnifyImageLensSupport(VisualizationViewer<V, E> vv, LensTransformer lensTransformer, ModalGraphMouse lensGraphMouse)
/*     */   {
/*  60 */     super(vv, lensGraphMouse);
/*  61 */     this.renderContext = vv.getRenderContext();
/*  62 */     this.pickSupport = this.renderContext.getPickSupport();
/*  63 */     this.renderer = vv.getRenderer();
/*  64 */     this.transformingRenderer = new BasicRenderer();
/*  65 */     this.savedGraphicsDecorator = this.renderContext.getGraphicsContext();
/*  66 */     this.lensTransformer = lensTransformer;
/*  67 */     this.savedEdgeRenderer = vv.getRenderer().getEdgeRenderer();
/*  68 */     this.reshapingEdgeRenderer = new ReshapingEdgeRenderer();
/*     */     
/*  70 */     Dimension d = vv.getSize();
/*  71 */     if ((d.width == 0) || (d.height == 0)) {
/*  72 */       d = vv.getPreferredSize();
/*     */     }
/*  74 */     lensTransformer.setViewRadius(d.width / 5);
/*  75 */     this.lensGraphicsDecorator = new MagnifyIconGraphics(lensTransformer);
/*     */   }
/*     */   
/*     */   public void activate() {
/*  79 */     this.lensTransformer.setDelegate(this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW));
/*  80 */     if (this.lens == null) {
/*  81 */       this.lens = new AbstractLensSupport.Lens(this.lensTransformer);
/*     */     }
/*  83 */     if (this.lensControls == null) {
/*  84 */       this.lensControls = new AbstractLensSupport.LensControls(this.lensTransformer);
/*     */     }
/*  86 */     this.renderContext.setPickSupport(new ViewLensShapePickSupport(this.vv));
/*  87 */     this.lensTransformer.setDelegate(this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW));
/*  88 */     this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.VIEW, this.lensTransformer);
/*  89 */     this.renderContext.setGraphicsContext(this.lensGraphicsDecorator);
/*  90 */     this.vv.getRenderer().setEdgeRenderer(this.reshapingEdgeRenderer);
/*  91 */     this.vv.addPreRenderPaintable(this.lens);
/*  92 */     this.vv.addPostRenderPaintable(this.lensControls);
/*  93 */     this.vv.setGraphMouse(this.lensGraphMouse);
/*  94 */     this.vv.setToolTipText("<html><center>Mouse-Drag the Lens center to move it<p>Mouse-Drag the Lens edge to resize it<p>Ctrl+MouseWheel to change magnification</center></html>");
/*  95 */     this.vv.repaint();
/*     */   }
/*     */   
/*     */   public void deactivate() {
/*  99 */     this.renderContext.setPickSupport(this.pickSupport);
/* 100 */     this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.VIEW, this.lensTransformer.getDelegate());
/* 101 */     this.vv.removePreRenderPaintable(this.lens);
/* 102 */     this.vv.removePostRenderPaintable(this.lensControls);
/* 103 */     this.renderContext.setGraphicsContext(this.savedGraphicsDecorator);
/* 104 */     this.vv.getRenderer().setEdgeRenderer(this.savedEdgeRenderer);
/* 105 */     this.vv.setToolTipText(this.defaultToolTipText);
/* 106 */     this.vv.setGraphMouse(this.graphMouse);
/* 107 */     this.vv.repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/MagnifyImageLensSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */