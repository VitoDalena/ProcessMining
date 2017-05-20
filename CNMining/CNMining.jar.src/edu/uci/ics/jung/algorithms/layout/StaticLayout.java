/*    */ package edu.uci.ics.jung.algorithms.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.geom.Point2D;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StaticLayout<V, E>
/*    */   extends AbstractLayout<V, E>
/*    */ {
/*    */   public StaticLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer, Dimension size)
/*    */   {
/* 40 */     super(graph, initializer, size);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public StaticLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer)
/*    */   {
/* 47 */     super(graph, initializer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public StaticLayout(Graph<V, E> graph)
/*    */   {
/* 55 */     super(graph);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public StaticLayout(Graph<V, E> graph, Dimension size)
/*    */   {
/* 62 */     super(graph, size);
/*    */   }
/*    */   
/*    */   public void initialize() {}
/*    */   
/*    */   public void reset() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/StaticLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */