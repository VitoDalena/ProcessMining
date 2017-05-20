/*    */ package edu.uci.ics.jung.visualization.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.algorithms.layout.StaticLayout;
/*    */ import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
/*    */ import edu.uci.ics.jung.algorithms.layout.util.VisRunner;
/*    */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Point2D.Double;
/*    */ 
/*    */ public class LayoutTransition<V, E> implements IterativeContext
/*    */ {
/*    */   protected Layout<V, E> startLayout;
/*    */   protected Layout<V, E> endLayout;
/*    */   protected Layout<V, E> transitionLayout;
/* 18 */   protected boolean done = false;
/* 19 */   protected int count = 20;
/* 20 */   protected int counter = 0;
/*    */   
/*    */ 
/*    */   protected VisualizationViewer<V, E> vv;
/*    */   
/*    */ 
/*    */   public LayoutTransition(VisualizationViewer<V, E> vv, Layout<V, E> startLayout, Layout<V, E> endLayout)
/*    */   {
/* 28 */     this.vv = vv;
/* 29 */     this.startLayout = startLayout;
/* 30 */     this.endLayout = endLayout;
/* 31 */     if ((endLayout instanceof IterativeContext)) {
/* 32 */       Relaxer relaxer = new VisRunner((IterativeContext)endLayout);
/* 33 */       relaxer.prerelax();
/*    */     }
/* 35 */     this.transitionLayout = new StaticLayout(startLayout.getGraph(), startLayout);
/*    */     
/* 37 */     vv.setGraphLayout(this.transitionLayout);
/*    */   }
/*    */   
/*    */   public boolean done() {
/* 41 */     return this.done;
/*    */   }
/*    */   
/*    */   public void step() {
/* 45 */     Graph<V, E> g = this.transitionLayout.getGraph();
/* 46 */     for (V v : g.getVertices()) {
/* 47 */       Point2D tp = (Point2D)this.transitionLayout.transform(v);
/* 48 */       Point2D fp = (Point2D)this.endLayout.transform(v);
/* 49 */       double dx = (fp.getX() - tp.getX()) / (this.count - this.counter);
/* 50 */       double dy = (fp.getY() - tp.getY()) / (this.count - this.counter);
/* 51 */       this.transitionLayout.setLocation(v, new Point2D.Double(tp.getX() + dx, tp.getY() + dy));
/*    */     }
/*    */     
/* 54 */     this.counter += 1;
/* 55 */     if (this.counter >= this.count) {
/* 56 */       this.done = true;
/* 57 */       this.vv.setGraphLayout(this.endLayout);
/*    */     }
/* 59 */     this.vv.repaint();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/LayoutTransition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */