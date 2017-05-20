/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.graph.util.TreeUtils;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
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
/*     */ public class DelegateForest<V, E>
/*     */   extends GraphDecorator<V, E>
/*     */   implements Forest<V, E>, Serializable
/*     */ {
/*     */   public DelegateForest()
/*     */   {
/*  28 */     this(new DirectedSparseGraph());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DelegateForest(DirectedGraph<V, E> delegate)
/*     */   {
/*  35 */     super(delegate);
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
/*     */   public boolean addEdge(E e, V v1, V v2, EdgeType edgeType)
/*     */   {
/*  54 */     if (!this.delegate.getVertices().contains(v1)) {
/*  55 */       throw new IllegalArgumentException("Tree must already contain " + v1);
/*     */     }
/*  57 */     if (this.delegate.getVertices().contains(v2)) {
/*  58 */       throw new IllegalArgumentException("Tree must not already contain " + v2);
/*     */     }
/*  60 */     return this.delegate.addEdge(e, v1, v2, edgeType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addVertex(V vertex)
/*     */   {
/*  72 */     setRoot(vertex);
/*  73 */     return true;
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
/*     */   public boolean removeEdge(E edge)
/*     */   {
/*  88 */     return removeEdge(edge, true);
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
/*     */   public boolean removeEdge(E edge, boolean remove_subtree)
/*     */   {
/* 103 */     if (!this.delegate.containsEdge(edge))
/* 104 */       return false;
/* 105 */     V child = getDest(edge);
/* 106 */     if (remove_subtree) {
/* 107 */       return removeVertex(child);
/*     */     }
/*     */     
/* 110 */     this.delegate.removeEdge(edge);
/* 111 */     return false;
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
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 124 */     return removeVertex(vertex, true);
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
/*     */   public boolean removeVertex(V vertex, boolean remove_subtrees)
/*     */   {
/* 140 */     if (!this.delegate.containsVertex(vertex))
/* 141 */       return false;
/* 142 */     if (remove_subtrees)
/* 143 */       for (V v : new ArrayList(this.delegate.getSuccessors(vertex)))
/* 144 */         removeVertex(v, true);
/* 145 */     return this.delegate.removeVertex(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<V> getPath(V child)
/*     */   {
/* 156 */     if (!this.delegate.containsVertex(child))
/* 157 */       return null;
/* 158 */     List<V> list = new ArrayList();
/* 159 */     list.add(child);
/* 160 */     V parent = getParent(child);
/* 161 */     while (parent != null) {
/* 162 */       list.add(list.size(), parent);
/* 163 */       parent = getParent(parent);
/*     */     }
/* 165 */     return list;
/*     */   }
/*     */   
/*     */   public V getParent(V child) {
/* 169 */     if (!this.delegate.containsVertex(child))
/* 170 */       return null;
/* 171 */     Collection<V> parents = this.delegate.getPredecessors(child);
/* 172 */     if (parents.size() > 0) {
/* 173 */       return (V)parents.iterator().next();
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getRoot()
/*     */   {
/* 184 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRoot(V root)
/*     */   {
/* 192 */     this.delegate.addVertex(root);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeChild(V orphan)
/*     */   {
/* 202 */     return removeVertex(orphan);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDepth(V v)
/*     */   {
/* 213 */     return getPath(v).size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 222 */     int height = 0;
/* 223 */     for (V v : getVertices()) {
/* 224 */       height = Math.max(height, getDepth(v));
/*     */     }
/* 226 */     return height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isInternal(V v)
/*     */   {
/* 236 */     return (!isLeaf(v)) && (!isRoot(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isLeaf(V v)
/*     */   {
/* 243 */     return getChildren(v).size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getChildren(V v)
/*     */   {
/* 250 */     return this.delegate.getSuccessors(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isRoot(V v)
/*     */   {
/* 257 */     return getParent(v) == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getIncidentCount(E edge)
/*     */   {
/* 263 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices)
/*     */   {
/* 269 */     Pair<V> pair = null;
/* 270 */     if ((vertices instanceof Pair)) {
/* 271 */       pair = (Pair)vertices;
/*     */     } else {
/* 273 */       pair = new Pair(vertices);
/*     */     }
/* 275 */     return addEdge(edge, pair.getFirst(), pair.getSecond());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getRoots()
/*     */   {
/* 282 */     Collection<V> roots = new HashSet();
/* 283 */     for (V v : this.delegate.getVertices()) {
/* 284 */       if (this.delegate.getPredecessorCount(v) == 0) {
/* 285 */         roots.add(v);
/*     */       }
/*     */     }
/* 288 */     return roots;
/*     */   }
/*     */   
/*     */   public Collection<Tree<V, E>> getTrees() {
/* 292 */     Collection<Tree<V, E>> trees = new HashSet();
/* 293 */     for (V v : getRoots()) {
/* 294 */       Tree<V, E> tree = new DelegateTree();
/* 295 */       tree.addVertex(v);
/* 296 */       TreeUtils.growSubTree(this, tree, v);
/* 297 */       trees.add(tree);
/*     */     }
/* 299 */     return trees;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addTree(Tree<V, E> tree)
/*     */   {
/* 308 */     TreeUtils.addSubTree(this, tree, null, null);
/*     */   }
/*     */   
/*     */   public int getChildCount(V vertex)
/*     */   {
/* 313 */     return this.delegate.getSuccessorCount(vertex);
/*     */   }
/*     */   
/*     */   public Collection<E> getChildEdges(V vertex)
/*     */   {
/* 318 */     return this.delegate.getOutEdges(vertex);
/*     */   }
/*     */   
/*     */   public E getParentEdge(V vertex)
/*     */   {
/* 323 */     if (isRoot(vertex))
/* 324 */       return null;
/* 325 */     return (E)this.delegate.getInEdges(vertex).iterator().next();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/DelegateForest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */