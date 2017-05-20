/*    */ package edu.uci.ics.jung.visualization.transform;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
/*    */ import edu.uci.ics.jung.visualization.control.ModalLensGraphMouse;
/*    */ import edu.uci.ics.jung.visualization.picking.LayoutLensShapePickSupport;
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
/*    */ 
/*    */ 
/*    */ public class LayoutLensSupport<V, E>
/*    */   extends AbstractLensSupport<V, E>
/*    */   implements LensSupport
/*    */ {
/*    */   protected GraphElementAccessor<V, E> pickSupport;
/*    */   
/*    */   public LayoutLensSupport(VisualizationViewer<V, E> vv)
/*    */   {
/* 36 */     this(vv, new HyperbolicTransformer(vv, vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT)), new ModalLensGraphMouse());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public LayoutLensSupport(VisualizationViewer<V, E> vv, LensTransformer lensTransformer, ModalGraphMouse lensGraphMouse)
/*    */   {
/* 46 */     super(vv, lensGraphMouse);
/* 47 */     this.lensTransformer = lensTransformer;
/* 48 */     this.pickSupport = vv.getPickSupport();
/*    */     
/* 50 */     Dimension d = vv.getSize();
/* 51 */     if ((d.width <= 0) || (d.height <= 0)) {
/* 52 */       d = vv.getPreferredSize();
/*    */     }
/* 54 */     lensTransformer.setViewRadius(d.width / 5);
/*    */   }
/*    */   
/*    */   public void activate() {
/* 58 */     if (this.lens == null) {
/* 59 */       this.lens = new AbstractLensSupport.Lens(this.lensTransformer);
/*    */     }
/* 61 */     if (this.lensControls == null) {
/* 62 */       this.lensControls = new AbstractLensSupport.LensControls(this.lensTransformer);
/*    */     }
/* 64 */     this.vv.getRenderContext().setPickSupport(new LayoutLensShapePickSupport(this.vv));
/* 65 */     this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.LAYOUT, this.lensTransformer);
/* 66 */     this.vv.prependPreRenderPaintable(this.lens);
/* 67 */     this.vv.addPostRenderPaintable(this.lensControls);
/* 68 */     this.vv.setGraphMouse(this.lensGraphMouse);
/* 69 */     this.vv.setToolTipText("<html><center>Mouse-Drag the Lens center to move it<p>Mouse-Drag the Lens edge to resize it<p>Ctrl+MouseWheel to change magnification</center></html>");
/* 70 */     this.vv.repaint();
/*    */   }
/*    */   
/*    */   public void deactivate() {
/* 74 */     if (this.lensTransformer != null) {
/* 75 */       this.vv.removePreRenderPaintable(this.lens);
/* 76 */       this.vv.removePostRenderPaintable(this.lensControls);
/* 77 */       this.vv.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.LAYOUT, this.lensTransformer.getDelegate());
/*    */     }
/* 79 */     this.vv.getRenderContext().setPickSupport(this.pickSupport);
/* 80 */     this.vv.setToolTipText(this.defaultToolTipText);
/* 81 */     this.vv.setGraphMouse(this.graphMouse);
/* 82 */     this.vv.repaint();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/LayoutLensSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */