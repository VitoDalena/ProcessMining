/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Point2D.Double;
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
/*    */ public class SatelliteRotatingGraphMousePlugin
/*    */   extends RotatingGraphMousePlugin
/*    */ {
/*    */   public SatelliteRotatingGraphMousePlugin() {}
/*    */   
/*    */   public SatelliteRotatingGraphMousePlugin(int modifiers)
/*    */   {
/* 34 */     super(modifiers);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseDragged(MouseEvent e)
/*    */   {
/* 41 */     if (this.down == null) return;
/* 42 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 43 */     boolean accepted = checkModifiers(e);
/* 44 */     if (accepted) {
/* 45 */       if ((vv instanceof SatelliteVisualizationViewer)) {
/* 46 */         VisualizationViewer vvMaster = ((SatelliteVisualizationViewer)vv).getMaster();
/*    */         
/*    */ 
/* 49 */         MutableTransformer modelTransformerMaster = vvMaster.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*    */         
/*    */ 
/*    */ 
/* 53 */         vv.setCursor(this.cursor);
/*    */         
/*    */ 
/*    */ 
/*    */ 
/* 58 */         Point2D center = vv.getRenderContext().getMultiLayerTransformer().transform(vvMaster.getRenderContext().getMultiLayerTransformer().inverseTransform(vvMaster.getCenter()));
/* 59 */         Point2D q = this.down;
/* 60 */         Point2D p = e.getPoint();
/* 61 */         Point2D v1 = new Point2D.Double(center.getX() - p.getX(), center.getY() - p.getY());
/* 62 */         Point2D v2 = new Point2D.Double(center.getX() - q.getX(), center.getY() - q.getY());
/* 63 */         double theta = angleBetween(v1, v2);
/* 64 */         modelTransformerMaster.rotate(-theta, vvMaster.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, vvMaster.getCenter()));
/*    */         
/* 66 */         this.down.x = e.getX();
/* 67 */         this.down.y = e.getY();
/*    */       }
/* 69 */       e.consume();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteRotatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */