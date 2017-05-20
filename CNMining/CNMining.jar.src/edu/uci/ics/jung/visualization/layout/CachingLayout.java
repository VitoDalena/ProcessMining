/*    */ package edu.uci.ics.jung.visualization.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.algorithms.layout.LayoutDecorator;
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.visualization.util.Caching;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ import org.apache.commons.collections15.functors.ChainedTransformer;
/*    */ import org.apache.commons.collections15.functors.CloneTransformer;
/*    */ import org.apache.commons.collections15.map.LazyMap;
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
/*    */ public class CachingLayout<V, E>
/*    */   extends LayoutDecorator<V, E>
/*    */   implements Caching
/*    */ {
/*    */   protected Map<V, Point2D> locationMap;
/*    */   
/*    */   public CachingLayout(Layout<V, E> delegate)
/*    */   {
/* 41 */     super(delegate);
/* 42 */     this.locationMap = LazyMap.decorate(new HashMap(), new ChainedTransformer(new Transformer[] { delegate, CloneTransformer.getInstance() }));
/*    */   }
/*    */   
/*    */ 
/*    */   public void setGraph(Graph<V, E> graph)
/*    */   {
/* 48 */     this.delegate.setGraph(graph);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 52 */     this.locationMap.clear();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void init() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Point2D transform(V v)
/*    */   {
/* 63 */     return (Point2D)this.locationMap.get(v);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/CachingLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */