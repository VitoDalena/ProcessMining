/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.PrintStream;
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
/*    */ public class SatelliteTranslatingGraphMousePlugin
/*    */   extends TranslatingGraphMousePlugin
/*    */ {
/*    */   public SatelliteTranslatingGraphMousePlugin() {}
/*    */   
/*    */   public SatelliteTranslatingGraphMousePlugin(int modifiers)
/*    */   {
/* 37 */     super(modifiers);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void mouseDragged(MouseEvent e)
/*    */   {
/* 46 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 47 */     boolean accepted = checkModifiers(e);
/* 48 */     if (accepted) {
/* 49 */       if ((vv instanceof SatelliteVisualizationViewer)) {
/* 50 */         VisualizationViewer vvMaster = ((SatelliteVisualizationViewer)vv).getMaster();
/*    */         
/*    */ 
/* 53 */         MutableTransformer modelTransformerMaster = vvMaster.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*    */         
/* 55 */         vv.setCursor(Cursor.getPredefinedCursor(13));
/*    */         try {
/* 57 */           Point2D q = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(this.down);
/* 58 */           Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
/* 59 */           float dx = (float)(p.getX() - q.getX());
/* 60 */           float dy = (float)(p.getY() - q.getY());
/*    */           
/* 62 */           modelTransformerMaster.translate(-dx, -dy);
/* 63 */           this.down.x = e.getX();
/* 64 */           this.down.y = e.getY();
/*    */         } catch (RuntimeException ex) {
/* 66 */           System.err.println("down = " + this.down + ", e = " + e);
/* 67 */           throw ex;
/*    */         }
/*    */       }
/* 70 */       e.consume();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteTranslatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */