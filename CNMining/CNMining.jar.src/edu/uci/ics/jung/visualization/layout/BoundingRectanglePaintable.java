/*    */ package edu.uci.ics.jung.visualization.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*    */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.util.List;
/*    */ import javax.swing.event.ChangeEvent;
/*    */ import javax.swing.event.ChangeListener;
/*    */ 
/*    */ public class BoundingRectanglePaintable<V, E>
/*    */   implements VisualizationServer.Paintable
/*    */ {
/*    */   protected RenderContext<V, E> rc;
/*    */   protected Graph<V, E> graph;
/*    */   protected Layout<V, E> layout;
/*    */   protected List<Rectangle2D> rectangles;
/*    */   
/*    */   public BoundingRectanglePaintable(RenderContext<V, E> rc, Layout<V, E> layout)
/*    */   {
/* 28 */     this.rc = rc;
/* 29 */     this.layout = layout;
/* 30 */     this.graph = layout.getGraph();
/* 31 */     final BoundingRectangleCollector<V, E> brc = new BoundingRectangleCollector(rc, layout);
/* 32 */     this.rectangles = brc.getRectangles();
/* 33 */     if ((layout instanceof ChangeEventSupport))
/* 34 */       ((ChangeEventSupport)layout).addChangeListener(new ChangeListener()
/*    */       {
/*    */         public void stateChanged(ChangeEvent e) {
/* 37 */           brc.compute();
/* 38 */           BoundingRectanglePaintable.this.rectangles = brc.getRectangles();
/*    */         }
/*    */       });
/*    */   }
/*    */   
/*    */   public void paint(Graphics g) {
/* 44 */     Graphics2D g2d = (Graphics2D)g;
/* 45 */     g.setColor(Color.cyan);
/*    */     
/* 47 */     for (Rectangle2D r : this.rectangles) {
/* 48 */       g2d.draw(this.rc.getMultiLayerTransformer().transform(Layer.LAYOUT, r));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean useTransform() {
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/BoundingRectanglePaintable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */