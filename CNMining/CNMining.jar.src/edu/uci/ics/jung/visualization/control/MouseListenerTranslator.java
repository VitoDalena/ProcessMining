/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
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
/*    */ public class MouseListenerTranslator<V, E>
/*    */   extends MouseAdapter
/*    */ {
/*    */   private VisualizationViewer<V, E> vv;
/*    */   private GraphMouseListener<V> gel;
/*    */   
/*    */   public MouseListenerTranslator(GraphMouseListener<V> gel, VisualizationViewer<V, E> vv)
/*    */   {
/* 38 */     this.gel = gel;
/* 39 */     this.vv = vv;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private V getVertex(Point2D point)
/*    */   {
/* 51 */     Point2D p = point;
/*    */     
/* 53 */     GraphElementAccessor<V, E> pickSupport = this.vv.getPickSupport();
/* 54 */     Layout<V, E> layout = this.vv.getGraphLayout();
/* 55 */     V v = null;
/* 56 */     if (pickSupport != null) {
/* 57 */       v = pickSupport.getVertex(layout, p.getX(), p.getY());
/*    */     }
/* 59 */     return v;
/*    */   }
/*    */   
/*    */ 
/*    */   public void mouseClicked(MouseEvent e)
/*    */   {
/* 65 */     V v = getVertex(e.getPoint());
/* 66 */     if (v != null) {
/* 67 */       this.gel.graphClicked(v, e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mousePressed(MouseEvent e)
/*    */   {
/* 75 */     V v = getVertex(e.getPoint());
/* 76 */     if (v != null) {
/* 77 */       this.gel.graphPressed(v, e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseReleased(MouseEvent e)
/*    */   {
/* 85 */     V v = getVertex(e.getPoint());
/* 86 */     if (v != null) {
/* 87 */       this.gel.graphReleased(v, e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/MouseListenerTranslator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */