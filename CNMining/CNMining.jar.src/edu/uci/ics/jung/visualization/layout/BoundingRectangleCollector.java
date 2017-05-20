/*    */ package edu.uci.ics.jung.visualization.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.Context;
/*    */ import edu.uci.ics.jung.graph.util.Pair;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ 
/*    */ public class BoundingRectangleCollector<V, E>
/*    */ {
/*    */   protected RenderContext<V, E> rc;
/*    */   protected Graph<V, E> graph;
/*    */   protected Layout<V, E> layout;
/* 21 */   protected List<Rectangle2D> rectangles = new ArrayList();
/*    */   
/*    */   public BoundingRectangleCollector(RenderContext<V, E> rc, Layout<V, E> layout) {
/* 24 */     this.rc = rc;
/* 25 */     this.layout = layout;
/* 26 */     this.graph = layout.getGraph();
/* 27 */     compute();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public List<Rectangle2D> getRectangles()
/*    */   {
/* 34 */     return this.rectangles;
/*    */   }
/*    */   
/*    */   public void compute() {
/* 38 */     this.rectangles.clear();
/*    */     
/*    */ 
/*    */ 
/* 42 */     for (E e : this.graph.getEdges()) {
/* 43 */       Pair<V> endpoints = this.graph.getEndpoints(e);
/* 44 */       V v1 = endpoints.getFirst();
/* 45 */       V v2 = endpoints.getSecond();
/* 46 */       Point2D p1 = (Point2D)this.layout.transform(v1);
/* 47 */       Point2D p2 = (Point2D)this.layout.transform(v2);
/* 48 */       float x1 = (float)p1.getX();
/* 49 */       float y1 = (float)p1.getY();
/* 50 */       float x2 = (float)p2.getX();
/* 51 */       float y2 = (float)p2.getY();
/*    */       
/* 53 */       boolean isLoop = v1.equals(v2);
/* 54 */       Shape s2 = (Shape)this.rc.getVertexShapeTransformer().transform(v2);
/* 55 */       Shape edgeShape = (Shape)this.rc.getEdgeShapeTransformer().transform(Context.getInstance(this.graph, e));
/*    */       
/* 57 */       AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*    */       
/* 59 */       if (isLoop) {
/* 60 */         Rectangle2D s2Bounds = s2.getBounds2D();
/* 61 */         xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/* 62 */         xform.translate(0.0D, -edgeShape.getBounds2D().getWidth() / 2.0D);
/*    */       } else {
/* 64 */         float dx = x2 - x1;
/* 65 */         float dy = y2 - y1;
/* 66 */         float theta = (float)Math.atan2(dy, dx);
/* 67 */         xform.rotate(theta);
/* 68 */         float dist = (float)p1.distance(p2);
/* 69 */         xform.scale(dist, 1.0D);
/*    */       }
/* 71 */       edgeShape = xform.createTransformedShape(edgeShape);
/*    */       
/* 73 */       this.rectangles.add(edgeShape.getBounds2D());
/*    */     }
/*    */     
/* 76 */     for (V v : this.graph.getVertices()) {
/* 77 */       Shape shape = (Shape)this.rc.getVertexShapeTransformer().transform(v);
/* 78 */       Point2D p = (Point2D)this.layout.transform(v);
/*    */       
/* 80 */       float x = (float)p.getX();
/* 81 */       float y = (float)p.getY();
/* 82 */       AffineTransform xform = AffineTransform.getTranslateInstance(x, y);
/* 83 */       shape = xform.createTransformedShape(shape);
/* 84 */       this.rectangles.add(shape.getBounds2D());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/BoundingRectangleCollector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */