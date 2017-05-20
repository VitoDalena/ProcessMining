/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.Tree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeUtils
/*     */ {
/*     */   public static <V, E> List<V> getRoots(Forest<V, E> forest)
/*     */   {
/*  33 */     List<V> roots = new ArrayList();
/*  34 */     for (Tree<V, E> tree : forest.getTrees()) {
/*  35 */       roots.add(tree.getRoot());
/*     */     }
/*  37 */     return roots;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> Tree<V, E> getSubTree(Forest<V, E> forest, V root)
/*     */     throws InstantiationException, IllegalAccessException
/*     */   {
/*  54 */     if (!forest.containsVertex(root))
/*  55 */       throw new IllegalArgumentException("Specified tree does not contain the specified root as a vertex");
/*  56 */     Forest<V, E> subforest = (Forest)forest.getClass().newInstance();
/*  57 */     subforest.addVertex(root);
/*  58 */     growSubTree(forest, subforest, root);
/*     */     
/*  60 */     return (Tree)subforest.getTrees().iterator().next();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> void growSubTree(Forest<V, E> tree, Forest<V, E> subTree, V root)
/*     */   {
/*  73 */     if (tree.getSuccessorCount(root) > 0) {
/*  74 */       Collection<E> edges = tree.getOutEdges(root);
/*  75 */       for (E e : edges) {
/*  76 */         subTree.addEdge(e, tree.getEndpoints(e));
/*     */       }
/*  78 */       Collection<V> kids = tree.getSuccessors(root);
/*  79 */       for (V kid : kids) {
/*  80 */         growSubTree(tree, subTree, kid);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> void addSubTree(Forest<V, E> tree, Forest<V, E> subTree, V node, E connectingEdge)
/*     */   {
/*  97 */     if ((node != null) && (!tree.containsVertex(node)))
/*  98 */       throw new IllegalArgumentException("Specified tree does not contain the specified node as a vertex");
/*  99 */     V root = ((Tree)subTree.getTrees().iterator().next()).getRoot();
/* 100 */     addFromSubTree(tree, subTree, connectingEdge, node, root);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> void addFromSubTree(Forest<V, E> tree, Forest<V, E> subTree, E edge, V parent, V root)
/*     */   {
/* 138 */     if ((edge != null) && (parent != null)) {
/* 139 */       tree.addEdge(edge, parent, root);
/*     */     } else {
/* 141 */       tree.addVertex(root);
/*     */     }
/*     */     
/* 144 */     Collection<E> outEdges = subTree.getOutEdges(root);
/* 145 */     for (E e : outEdges) {
/* 146 */       V opposite = subTree.getOpposite(root, e);
/* 147 */       addFromSubTree(tree, subTree, e, root, opposite);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/TreeUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */