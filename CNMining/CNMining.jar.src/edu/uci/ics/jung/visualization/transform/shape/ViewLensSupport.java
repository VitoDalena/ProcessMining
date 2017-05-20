/*    */ package edu.uci.ics.jung.visualization.transform.shape;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
/*    */ import edu.uci.ics.jung.visualization.picking.ViewLensShapePickSupport;
/*    */ import edu.uci.ics.jung.visualization.renderers.Renderer;
/*    */ import edu.uci.ics.jung.visualization.renderers.Renderer.Edge;
/*    */ import edu.uci.ics.jung.visualization.renderers.ReshapingEdgeRenderer;
/*    */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport;
/*    */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport.Lens;
/*    */ import edu.uci.ics.jung.visualization.transform.AbstractLensSupport.LensControls;
/*    */ import edu.uci.ics.jung.visualization.transform.LensSupport;
/*    */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*    */ import java.awt.Dimension;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ViewLensSupport<V, E>
/*    */   extends AbstractLensSupport<V, E>
/*    */   implements LensSupport
/*    */ {
/*    */   protected RenderContext<V, E> renderContext;
/*    */   protected GraphicsDecorator lensGraphicsDecorator;
/*    */   protected GraphicsDecorator savedGraphicsDecorator;
/*    */   protected GraphElementAccessor<V, E> pickSupport;
/*    */   protected Renderer.Edge<V, E> savedEdgeRenderer;
/*    */   protected Renderer.Edge<V, E> reshapingEdgeRenderer;
/*    */   
/*    */   public ViewLensSupport(VisualizationViewer<V, E> vv, LensTransformer lensTransformer, ModalGraphMouse lensGraphMouse)
/*    */   {
/* 46 */     super(vv, lensGraphMouse);
/* 47 */     this.renderContext = vv.getRenderContext();
/* 48 */     this.pickSupport = this.renderContext.getPickSupport();
/* 49 */     this.savedGraphicsDecorator = this.renderContext.getGraphicsContext();
/* 50 */     this.lensTransformer = lensTransformer;
/* 51 */     Dimension d = vv.getSize();
/* 52 */     lensTransformer.setViewRadius(d.width / 5);
/* 53 */     this.lensGraphicsDecorator = new TransformingFlatnessGraphics(lensTransformer);
/* 54 */     this.savedEdgeRenderer = vv.getRenderer().getEdgeRenderer();
/* 55 */     this.reshapingEdgeRenderer = new ReshapingEdgeRenderer();
/*    */   }
/*    */   
/*    */   public void activate() {
/* 59 */     this.lensTransformer.setDelegate(this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW));
/* 60 */     if (this.lens == null) {
/* 61 */       this.lens = new AbstractLensSupport.Lens(this.lensTransformer);
/*    */     }
/* 63 */     if (this.lensControls == null) {
/* 64 */       this.lensControls = new AbstractLensSupport.LensControls(this.lensTransformer);
/*    */     }
/* 66 */     this.renderContext.setPickSupport(new ViewLensShapePickSupport(this.vv));
/* 67 */     this.lensTransformer.setDelegate(this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW));
/* 68 */     this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.VIEW, this.lensTransformer);
/* 69 */     this.renderContext.setGraphicsContext(this.lensGraphicsDecorator);
/* 70 */     this.vv.getRenderer().setEdgeRenderer(this.reshapingEdgeRenderer);
/* 71 */     this.vv.prependPreRenderPaintable(this.lens);
/* 72 */     this.vv.addPostRenderPaintable(this.lensControls);
/* 73 */     this.vv.setGraphMouse(this.lensGraphMouse);
/* 74 */     this.vv.setToolTipText("<html><center>Mouse-Drag the Lens center to move it<p>Mouse-Drag the Lens edge to resize it<p>Ctrl+MouseWheel to change magnification</center></html>");
/* 75 */     this.vv.repaint();
/*    */   }
/*    */   
/*    */ 
/*    */   public void deactivate()
/*    */   {
/* 81 */     this.renderContext.setPickSupport(this.pickSupport);
/* 82 */     this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.VIEW, this.lensTransformer.getDelegate());
/* 83 */     this.vv.removePreRenderPaintable(this.lens);
/* 84 */     this.vv.removePostRenderPaintable(this.lensControls);
/* 85 */     this.renderContext.setGraphicsContext(this.savedGraphicsDecorator);
/* 86 */     this.vv.setRenderContext(this.renderContext);
/* 87 */     this.vv.setToolTipText(this.defaultToolTipText);
/* 88 */     this.vv.setGraphMouse(this.graphMouse);
/* 89 */     this.vv.getRenderer().setEdgeRenderer(this.savedEdgeRenderer);
/* 90 */     this.vv.repaint();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/ViewLensSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */