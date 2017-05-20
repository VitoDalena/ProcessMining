/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import java.awt.geom.Point2D;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SatelliteAnimatedPickingGraphMousePlugin<V, E>
/*    */   extends AnimatedPickingGraphMousePlugin<V, E>
/*    */   implements MouseListener, MouseMotionListener
/*    */ {
/*    */   public SatelliteAnimatedPickingGraphMousePlugin()
/*    */   {
/* 40 */     this(18);
/*    */   }
/*    */   
/*    */   public SatelliteAnimatedPickingGraphMousePlugin(int selectionModifiers) {
/* 44 */     super(selectionModifiers);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void mouseReleased(MouseEvent e)
/*    */   {
/* 54 */     if (e.getModifiers() == this.modifiers) {
/* 55 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 56 */       if ((vv instanceof SatelliteVisualizationViewer)) {
/* 57 */         final VisualizationViewer<V, E> vvMaster = ((SatelliteVisualizationViewer)vv).getMaster();
/*    */         
/*    */ 
/* 60 */         if (this.vertex != null) {
/* 61 */           Layout<V, E> layout = vvMaster.getGraphLayout();
/* 62 */           Point2D q = (Point2D)layout.transform(this.vertex);
/* 63 */           Point2D lvc = vvMaster.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.LAYOUT, vvMaster.getCenter());
/*    */           
/* 65 */           final double dx = (lvc.getX() - q.getX()) / 10.0D;
/* 66 */           double dy = (lvc.getY() - q.getY()) / 10.0D;
/*    */           
/* 68 */           Runnable animator = new Runnable()
/*    */           {
/*    */             public void run() {
/* 71 */               for (int i = 0; i < 10; i++) {
/* 72 */                 vvMaster.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).translate(dx, this.val$dy);
/*    */                 try
/*    */                 {
/* 75 */                   Thread.sleep(100L);
/*    */                 }
/*    */                 catch (InterruptedException ex) {}
/*    */               }
/*    */             }
/* 80 */           };
/* 81 */           Thread thread = new Thread(animator);
/* 82 */           thread.start();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteAnimatedPickingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */