/*    */ package edu.uci.ics.jung.visualization.subLayout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.graph.Forest;
/*    */ import edu.uci.ics.jung.graph.util.TreeUtils;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeCollapser
/*    */ {
/*    */   public void collapse(Layout layout, Forest tree, Object subRoot)
/*    */     throws InstantiationException, IllegalAccessException
/*    */   {
/* 23 */     Forest subTree = TreeUtils.getSubTree(tree, subRoot);
/* 24 */     Object parent = null;
/* 25 */     Object edge = null;
/* 26 */     if (tree.getPredecessorCount(subRoot) > 0) {
/* 27 */       parent = tree.getPredecessors(subRoot).iterator().next();
/* 28 */       edge = tree.getInEdges(subRoot).iterator().next();
/*    */     }
/* 30 */     tree.removeVertex(subRoot);
/* 31 */     if (parent != null) {
/* 32 */       tree.addEdge(edge, parent, subTree);
/*    */     } else {
/* 34 */       tree.addVertex(subTree);
/*    */     }
/*    */     
/* 37 */     layout.setLocation(subTree, (Point2D)layout.transform(subRoot));
/*    */   }
/*    */   
/*    */   public void expand(Forest tree, Forest subTree)
/*    */   {
/* 42 */     Object parent = null;
/* 43 */     Object edge = null;
/* 44 */     if (tree.getPredecessorCount(subTree) > 0) {
/* 45 */       parent = tree.getPredecessors(subTree).iterator().next();
/* 46 */       edge = tree.getInEdges(subTree).iterator().next();
/*    */     }
/*    */     
/*    */ 
/* 50 */     tree.removeVertex(subTree);
/* 51 */     TreeUtils.addSubTree(tree, subTree, parent, edge);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/subLayout/TreeCollapser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */