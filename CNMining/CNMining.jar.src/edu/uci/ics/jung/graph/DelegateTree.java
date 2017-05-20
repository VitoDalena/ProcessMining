/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class DelegateTree<V, E>
/*     */   extends GraphDecorator<V, E>
/*     */   implements Tree<V, E>, Serializable
/*     */ {
/*     */   protected V root;
/*     */   protected Map<V, Integer> vertex_depths;
/*     */   
/*     */   public static final <V, E> Factory<Tree<V, E>> getFactory()
/*     */   {
/*  33 */     new Factory() {
/*     */       public Tree<V, E> create() {
/*  35 */         return new DelegateTree(new DirectedSparseMultigraph());
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DelegateTree()
/*     */   {
/*  47 */     this(DirectedSparseMultigraph.getFactory());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DelegateTree(Factory<DirectedGraph<V, E>> graphFactory)
/*     */   {
/*  55 */     super((Graph)graphFactory.create());
/*  56 */     this.vertex_depths = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DelegateTree(DirectedGraph<V, E> graph)
/*     */   {
/*  65 */     super(graph);
/*     */     
/*     */ 
/*  68 */     this.vertex_depths = new HashMap();
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
/*  87 */     return addChild(e, v1, v2, edgeType);
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
/*     */   public boolean addEdge(E e, V v1, V v2)
/*     */   {
/* 103 */     return addChild(e, v1, v2);
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
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 117 */     if (this.root == null) {
/* 118 */       this.root = vertex;
/* 119 */       this.vertex_depths.put(vertex, Integer.valueOf(0));
/* 120 */       return this.delegate.addVertex(vertex);
/*     */     }
/* 122 */     throw new UnsupportedOperationException("Unless you are setting the root, use addChild()");
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
/* 135 */     if (!this.delegate.containsVertex(vertex))
/* 136 */       return false;
/* 137 */     for (V v : getChildren(vertex)) {
/* 138 */       removeVertex(v);
/* 139 */       this.vertex_depths.remove(v);
/*     */     }
/*     */     
/*     */ 
/* 143 */     this.vertex_depths.remove(vertex);
/* 144 */     return this.delegate.removeVertex(vertex);
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
/*     */   public boolean addChild(E edge, V parent, V child, EdgeType edgeType)
/*     */   {
/* 158 */     Collection<V> vertices = this.delegate.getVertices();
/* 159 */     if (!vertices.contains(parent)) {
/* 160 */       throw new IllegalArgumentException("Tree must already contain parent " + parent);
/*     */     }
/* 162 */     if (vertices.contains(child)) {
/* 163 */       throw new IllegalArgumentException("Tree must not already contain child " + child);
/*     */     }
/* 165 */     this.vertex_depths.put(child, Integer.valueOf(((Integer)this.vertex_depths.get(parent)).intValue() + 1));
/* 166 */     return this.delegate.addEdge(edge, parent, child, edgeType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addChild(E edge, V parent, V child)
/*     */   {
/* 178 */     Collection<V> vertices = this.delegate.getVertices();
/* 179 */     if (!vertices.contains(parent)) {
/* 180 */       throw new IllegalArgumentException("Tree must already contain parent " + parent);
/*     */     }
/* 182 */     if (vertices.contains(child)) {
/* 183 */       throw new IllegalArgumentException("Tree must not already contain child " + child);
/*     */     }
/* 185 */     this.vertex_depths.put(child, Integer.valueOf(((Integer)this.vertex_depths.get(parent)).intValue() + 1));
/* 186 */     return this.delegate.addEdge(edge, parent, child);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getChildCount(V parent)
/*     */   {
/* 193 */     if (!this.delegate.containsVertex(parent))
/* 194 */       return 0;
/* 195 */     return getChildren(parent).size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getChildren(V parent)
/*     */   {
/* 202 */     if (!this.delegate.containsVertex(parent))
/* 203 */       return null;
/* 204 */     return this.delegate.getSuccessors(parent);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getParent(V child)
/*     */   {
/* 211 */     if (!this.delegate.containsVertex(child))
/* 212 */       return null;
/* 213 */     Collection<V> predecessors = this.delegate.getPredecessors(child);
/* 214 */     if (predecessors.size() == 0) {
/* 215 */       return null;
/*     */     }
/* 217 */     return (V)predecessors.iterator().next();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<V> getPath(V vertex)
/*     */   {
/* 228 */     if (!this.delegate.containsVertex(vertex))
/* 229 */       return null;
/* 230 */     List<V> list = new ArrayList();
/* 231 */     list.add(vertex);
/* 232 */     V parent = getParent(vertex);
/* 233 */     while (parent != null) {
/* 234 */       list.add(list.size(), parent);
/* 235 */       parent = getParent(parent);
/*     */     }
/* 237 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getRoot()
/*     */   {
/* 245 */     return (V)this.root;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRoot(V root)
/*     */   {
/* 254 */     addVertex(root);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeChild(V orphan)
/*     */   {
/* 264 */     return removeVertex(orphan);
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
/* 275 */     return ((Integer)this.vertex_depths.get(v)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 284 */     int height = 0;
/* 285 */     for (V v : getVertices()) {
/* 286 */       height = Math.max(height, getDepth(v));
/*     */     }
/* 288 */     return height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isInternal(V v)
/*     */   {
/* 298 */     if (!this.delegate.containsVertex(v))
/* 299 */       return false;
/* 300 */     return (!isLeaf(v)) && (!isRoot(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLeaf(V v)
/*     */   {
/* 310 */     if (!this.delegate.containsVertex(v))
/* 311 */       return false;
/* 312 */     return getChildren(v).size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRoot(V v)
/*     */   {
/* 320 */     if (!this.delegate.containsVertex(v))
/* 321 */       return false;
/* 322 */     return getParent(v) == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getIncidentCount(E edge)
/*     */   {
/* 328 */     if (!this.delegate.containsEdge(edge)) {
/* 329 */       return 0;
/*     */     }
/* 331 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices)
/*     */   {
/* 337 */     Pair<V> pair = null;
/* 338 */     if ((vertices instanceof Pair)) {
/* 339 */       pair = (Pair)vertices;
/*     */     } else {
/* 341 */       pair = new Pair(vertices);
/*     */     }
/* 343 */     return addEdge(edge, pair.getFirst(), pair.getSecond());
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 348 */     return "Tree of " + this.delegate.toString();
/*     */   }
/*     */   
/*     */   public Collection<Tree<V, E>> getTrees() {
/* 352 */     return Collections.singleton(this);
/*     */   }
/*     */   
/*     */   public Collection<E> getChildEdges(V vertex) {
/* 356 */     return getOutEdges(vertex);
/*     */   }
/*     */   
/*     */   public E getParentEdge(V vertex) {
/* 360 */     return (E)getInEdges(vertex).iterator().next();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/DelegateTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */