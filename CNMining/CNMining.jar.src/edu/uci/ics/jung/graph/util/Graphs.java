/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Tree;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
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
/*     */ public class Graphs
/*     */ {
/*     */   public static <V, E> Graph<V, E> synchronizedGraph(Graph<V, E> graph)
/*     */   {
/*  31 */     return new SynchronizedGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> DirectedGraph<V, E> synchronizedDirectedGraph(DirectedGraph<V, E> graph)
/*     */   {
/*  42 */     return new SynchronizedDirectedGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> UndirectedGraph<V, E> synchronizedUndirectedGraph(UndirectedGraph<V, E> graph)
/*     */   {
/*  53 */     return new SynchronizedUndirectedGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> SynchronizedForest<V, E> synchronizedForest(Forest<V, E> forest)
/*     */   {
/*  64 */     return new SynchronizedForest(forest);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> SynchronizedTree<V, E> synchronizedTree(Tree<V, E> tree)
/*     */   {
/*  75 */     return new SynchronizedTree(tree);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> Graph<V, E> unmodifiableGraph(Graph<V, E> graph)
/*     */   {
/*  86 */     return new UnmodifiableGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> DirectedGraph<V, E> unmodifiableDirectedGraph(DirectedGraph<V, E> graph)
/*     */   {
/*  97 */     return new UnmodifiableDirectedGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> UndirectedGraph<V, E> unmodifiableUndirectedGraph(UndirectedGraph<V, E> graph)
/*     */   {
/* 108 */     return new UnmodifiableUndirectedGraph(graph, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> UnmodifiableTree<V, E> unmodifiableTree(Tree<V, E> tree)
/*     */   {
/* 119 */     return new UnmodifiableTree(tree, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> UnmodifiableForest<V, E> unmodifiableForest(Forest<V, E> forest)
/*     */   {
/* 130 */     return new UnmodifiableForest(forest, null);
/*     */   }
/*     */   
/*     */   static abstract class SynchronizedAbstractGraph<V, E> implements Graph<V, E>, Serializable
/*     */   {
/*     */     protected Graph<V, E> delegate;
/*     */     
/*     */     private SynchronizedAbstractGraph(Graph<V, E> delegate)
/*     */     {
/* 139 */       if (delegate == null) {
/* 140 */         throw new NullPointerException();
/*     */       }
/* 142 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public EdgeType getDefaultEdgeType()
/*     */     {
/* 150 */       return this.delegate.getDefaultEdgeType();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean addEdge(E e, V v1, V v2, EdgeType edgeType)
/*     */     {
/* 157 */       return this.delegate.addEdge(e, v1, v2, edgeType);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized boolean addEdge(E e, Collection<? extends V> vertices, EdgeType edgeType)
/*     */     {
/* 166 */       return this.delegate.addEdge(e, vertices, edgeType);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean addEdge(E e, V v1, V v2)
/*     */     {
/* 173 */       return this.delegate.addEdge(e, v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean addVertex(V vertex)
/*     */     {
/* 180 */       return this.delegate.addVertex(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isIncident(V vertex, E edge)
/*     */     {
/* 187 */       return this.delegate.isIncident(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isNeighbor(V v1, V v2)
/*     */     {
/* 194 */       return this.delegate.isNeighbor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int degree(V vertex)
/*     */     {
/* 201 */       return this.delegate.degree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized E findEdge(V v1, V v2)
/*     */     {
/* 208 */       return (E)this.delegate.findEdge(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> findEdgeSet(V v1, V v2)
/*     */     {
/* 216 */       return this.delegate.findEdgeSet(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> getEdges()
/*     */     {
/* 223 */       return this.delegate.getEdges();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> getEdges(EdgeType edgeType)
/*     */     {
/* 230 */       return this.delegate.getEdges(edgeType);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Pair<V> getEndpoints(E edge)
/*     */     {
/* 237 */       return this.delegate.getEndpoints(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> getIncidentEdges(V vertex)
/*     */     {
/* 244 */       return this.delegate.getIncidentEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<V> getIncidentVertices(E edge)
/*     */     {
/* 251 */       return this.delegate.getIncidentVertices(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> getInEdges(V vertex)
/*     */     {
/* 258 */       return this.delegate.getInEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<V> getNeighbors(V vertex)
/*     */     {
/* 265 */       return this.delegate.getNeighbors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized V getOpposite(V vertex, E edge)
/*     */     {
/* 272 */       return (V)this.delegate.getOpposite(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<E> getOutEdges(V vertex)
/*     */     {
/* 279 */       return this.delegate.getOutEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<V> getPredecessors(V vertex)
/*     */     {
/* 286 */       return this.delegate.getPredecessors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<V> getSuccessors(V vertex)
/*     */     {
/* 293 */       return this.delegate.getSuccessors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized Collection<V> getVertices()
/*     */     {
/* 300 */       return this.delegate.getVertices();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int getEdgeCount()
/*     */     {
/* 307 */       return this.delegate.getEdgeCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized int getEdgeCount(EdgeType edge_type)
/*     */     {
/* 315 */       return this.delegate.getEdgeCount(edge_type);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int getVertexCount()
/*     */     {
/* 322 */       return this.delegate.getVertexCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int inDegree(V vertex)
/*     */     {
/* 329 */       return this.delegate.inDegree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized EdgeType getEdgeType(E edge)
/*     */     {
/* 336 */       return this.delegate.getEdgeType(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isPredecessor(V v1, V v2)
/*     */     {
/* 343 */       return this.delegate.isPredecessor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isSuccessor(V v1, V v2)
/*     */     {
/* 350 */       return this.delegate.isSuccessor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int getNeighborCount(V vertex)
/*     */     {
/* 357 */       return this.delegate.getNeighborCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int getPredecessorCount(V vertex)
/*     */     {
/* 364 */       return this.delegate.getPredecessorCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int getSuccessorCount(V vertex)
/*     */     {
/* 371 */       return this.delegate.getSuccessorCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int outDegree(V vertex)
/*     */     {
/* 378 */       return this.delegate.outDegree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean removeEdge(E edge)
/*     */     {
/* 385 */       return this.delegate.removeEdge(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean removeVertex(V vertex)
/*     */     {
/* 392 */       return this.delegate.removeVertex(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized V getDest(E directed_edge)
/*     */     {
/* 399 */       return (V)this.delegate.getDest(directed_edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized V getSource(E directed_edge)
/*     */     {
/* 406 */       return (V)this.delegate.getSource(directed_edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isDest(V vertex, E edge)
/*     */     {
/* 413 */       return this.delegate.isDest(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean isSource(V vertex, E edge)
/*     */     {
/* 420 */       return this.delegate.isSource(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized int getIncidentCount(E edge)
/*     */     {
/* 428 */       return this.delegate.getIncidentCount(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean addEdge(E hyperedge, Collection<? extends V> vertices)
/*     */     {
/* 435 */       return this.delegate.addEdge(hyperedge, vertices);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean containsEdge(E edge)
/*     */     {
/* 442 */       return this.delegate.containsEdge(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized boolean containsVertex(V vertex)
/*     */     {
/* 449 */       return this.delegate.containsVertex(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */   static class SynchronizedGraph<V, E>
/*     */     extends Graphs.SynchronizedAbstractGraph<V, E> implements Serializable
/*     */   {
/*     */     private SynchronizedGraph(Graph<V, E> delegate)
/*     */     {
/* 458 */       super(null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class SynchronizedUndirectedGraph<V, E> extends Graphs.SynchronizedAbstractGraph<V, E> implements UndirectedGraph<V, E>, Serializable
/*     */   {
/*     */     private SynchronizedUndirectedGraph(UndirectedGraph<V, E> delegate)
/*     */     {
/* 466 */       super(null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class SynchronizedDirectedGraph<V, E>
/*     */     extends Graphs.SynchronizedAbstractGraph<V, E> implements DirectedGraph<V, E>, Serializable
/*     */   {
/*     */     private SynchronizedDirectedGraph(DirectedGraph<V, E> delegate)
/*     */     {
/* 475 */       super(null);
/*     */     }
/*     */     
/*     */     public synchronized V getDest(E directed_edge)
/*     */     {
/* 480 */       return (V)((DirectedGraph)this.delegate).getDest(directed_edge);
/*     */     }
/*     */     
/*     */     public synchronized V getSource(E directed_edge)
/*     */     {
/* 485 */       return (V)((DirectedGraph)this.delegate).getSource(directed_edge);
/*     */     }
/*     */     
/*     */     public synchronized boolean isDest(V vertex, E edge)
/*     */     {
/* 490 */       return ((DirectedGraph)this.delegate).isDest(vertex, edge);
/*     */     }
/*     */     
/*     */     public synchronized boolean isSource(V vertex, E edge)
/*     */     {
/* 495 */       return ((DirectedGraph)this.delegate).isSource(vertex, edge);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class SynchronizedTree<V, E>
/*     */     extends Graphs.SynchronizedForest<V, E>
/*     */     implements Tree<V, E>
/*     */   {
/*     */     public SynchronizedTree(Tree<V, E> delegate)
/*     */     {
/* 507 */       super();
/*     */     }
/*     */     
/*     */     public synchronized int getDepth(V vertex) {
/* 511 */       return ((Tree)this.delegate).getDepth(vertex);
/*     */     }
/*     */     
/*     */     public synchronized int getHeight() {
/* 515 */       return ((Tree)this.delegate).getHeight();
/*     */     }
/*     */     
/*     */     public synchronized V getRoot() {
/* 519 */       return (V)((Tree)this.delegate).getRoot();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class SynchronizedForest<V, E>
/*     */     extends Graphs.SynchronizedDirectedGraph<V, E>
/*     */     implements Forest<V, E>
/*     */   {
/*     */     public SynchronizedForest(Forest<V, E> delegate)
/*     */     {
/* 531 */       super(null);
/*     */     }
/*     */     
/*     */     public synchronized Collection<Tree<V, E>> getTrees() {
/* 535 */       return ((Forest)this.delegate).getTrees();
/*     */     }
/*     */     
/*     */     public int getChildCount(V vertex)
/*     */     {
/* 540 */       return ((Forest)this.delegate).getChildCount(vertex);
/*     */     }
/*     */     
/*     */     public Collection<E> getChildEdges(V vertex)
/*     */     {
/* 545 */       return ((Forest)this.delegate).getChildEdges(vertex);
/*     */     }
/*     */     
/*     */     public Collection<V> getChildren(V vertex)
/*     */     {
/* 550 */       return ((Forest)this.delegate).getChildren(vertex);
/*     */     }
/*     */     
/*     */     public V getParent(V vertex)
/*     */     {
/* 555 */       return (V)((Forest)this.delegate).getParent(vertex);
/*     */     }
/*     */     
/*     */     public E getParentEdge(V vertex)
/*     */     {
/* 560 */       return (E)((Forest)this.delegate).getParentEdge(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class UnmodifiableAbstractGraph<V, E> implements Graph<V, E>, Serializable
/*     */   {
/*     */     protected Graph<V, E> delegate;
/*     */     
/*     */     private UnmodifiableAbstractGraph(Graph<V, E> delegate)
/*     */     {
/* 570 */       if (delegate == null) {
/* 571 */         throw new NullPointerException();
/*     */       }
/* 573 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public EdgeType getDefaultEdgeType()
/*     */     {
/* 581 */       return this.delegate.getDefaultEdgeType();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean addEdge(E e, V v1, V v2, EdgeType edgeType)
/*     */     {
/* 588 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean addEdge(E e, Collection<? extends V> vertices, EdgeType edgeType)
/*     */     {
/* 597 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean addEdge(E e, V v1, V v2)
/*     */     {
/* 604 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean addVertex(V vertex)
/*     */     {
/* 611 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isIncident(V vertex, E edge)
/*     */     {
/* 618 */       return this.delegate.isIncident(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isNeighbor(V v1, V v2)
/*     */     {
/* 625 */       return this.delegate.isNeighbor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int degree(V vertex)
/*     */     {
/* 632 */       return this.delegate.degree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public E findEdge(V v1, V v2)
/*     */     {
/* 639 */       return (E)this.delegate.findEdge(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Collection<E> findEdgeSet(V v1, V v2)
/*     */     {
/* 647 */       return this.delegate.findEdgeSet(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<E> getEdges()
/*     */     {
/* 654 */       return this.delegate.getEdges();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int getEdgeCount()
/*     */     {
/* 661 */       return this.delegate.getEdgeCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getEdgeCount(EdgeType edge_type)
/*     */     {
/* 669 */       return this.delegate.getEdgeCount(edge_type);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int getVertexCount()
/*     */     {
/* 676 */       return this.delegate.getVertexCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<E> getEdges(EdgeType edgeType)
/*     */     {
/* 683 */       return this.delegate.getEdges(edgeType);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Pair<V> getEndpoints(E edge)
/*     */     {
/* 690 */       return this.delegate.getEndpoints(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<E> getIncidentEdges(V vertex)
/*     */     {
/* 697 */       return this.delegate.getIncidentEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<V> getIncidentVertices(E edge)
/*     */     {
/* 704 */       return this.delegate.getIncidentVertices(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<E> getInEdges(V vertex)
/*     */     {
/* 711 */       return this.delegate.getInEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<V> getNeighbors(V vertex)
/*     */     {
/* 718 */       return this.delegate.getNeighbors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public V getOpposite(V vertex, E edge)
/*     */     {
/* 725 */       return (V)this.delegate.getOpposite(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<E> getOutEdges(V vertex)
/*     */     {
/* 732 */       return this.delegate.getOutEdges(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<V> getPredecessors(V vertex)
/*     */     {
/* 739 */       return this.delegate.getPredecessors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<V> getSuccessors(V vertex)
/*     */     {
/* 746 */       return this.delegate.getSuccessors(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Collection<V> getVertices()
/*     */     {
/* 753 */       return this.delegate.getVertices();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int inDegree(V vertex)
/*     */     {
/* 760 */       return this.delegate.inDegree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public EdgeType getEdgeType(E edge)
/*     */     {
/* 767 */       return this.delegate.getEdgeType(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isPredecessor(V v1, V v2)
/*     */     {
/* 774 */       return this.delegate.isPredecessor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isSuccessor(V v1, V v2)
/*     */     {
/* 781 */       return this.delegate.isSuccessor(v1, v2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int getNeighborCount(V vertex)
/*     */     {
/* 788 */       return this.delegate.getNeighborCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int getPredecessorCount(V vertex)
/*     */     {
/* 795 */       return this.delegate.getPredecessorCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int getSuccessorCount(V vertex)
/*     */     {
/* 802 */       return this.delegate.getSuccessorCount(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public int outDegree(V vertex)
/*     */     {
/* 809 */       return this.delegate.outDegree(vertex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean removeEdge(E edge)
/*     */     {
/* 816 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean removeVertex(V vertex)
/*     */     {
/* 823 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public V getDest(E directed_edge)
/*     */     {
/* 830 */       return (V)this.delegate.getDest(directed_edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public V getSource(E directed_edge)
/*     */     {
/* 837 */       return (V)this.delegate.getSource(directed_edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isDest(V vertex, E edge)
/*     */     {
/* 844 */       return this.delegate.isDest(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean isSource(V vertex, E edge)
/*     */     {
/* 851 */       return this.delegate.isSource(vertex, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getIncidentCount(E edge)
/*     */     {
/* 859 */       return this.delegate.getIncidentCount(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean addEdge(E hyperedge, Collection<? extends V> vertices)
/*     */     {
/* 866 */       return this.delegate.addEdge(hyperedge, vertices);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean containsEdge(E edge)
/*     */     {
/* 873 */       return this.delegate.containsEdge(edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean containsVertex(V vertex)
/*     */     {
/* 880 */       return this.delegate.containsVertex(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */   static class UnmodifiableGraph<V, E> extends Graphs.UnmodifiableAbstractGraph<V, E> implements Serializable
/*     */   {
/*     */     private UnmodifiableGraph(Graph<V, E> delegate) {
/* 887 */       super(null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class UnmodifiableDirectedGraph<V, E> extends Graphs.UnmodifiableAbstractGraph<V, E> implements DirectedGraph<V, E>, Serializable
/*     */   {
/*     */     private UnmodifiableDirectedGraph(DirectedGraph<V, E> delegate)
/*     */     {
/* 895 */       super(null);
/*     */     }
/*     */     
/*     */     public V getDest(E directed_edge)
/*     */     {
/* 900 */       return (V)((DirectedGraph)this.delegate).getDest(directed_edge);
/*     */     }
/*     */     
/*     */     public V getSource(E directed_edge)
/*     */     {
/* 905 */       return (V)((DirectedGraph)this.delegate).getSource(directed_edge);
/*     */     }
/*     */     
/*     */     public boolean isDest(V vertex, E edge)
/*     */     {
/* 910 */       return ((DirectedGraph)this.delegate).isDest(vertex, edge);
/*     */     }
/*     */     
/*     */     public boolean isSource(V vertex, E edge)
/*     */     {
/* 915 */       return ((DirectedGraph)this.delegate).isSource(vertex, edge);
/*     */     }
/*     */   }
/*     */   
/*     */   static class UnmodifiableUndirectedGraph<V, E> extends Graphs.UnmodifiableAbstractGraph<V, E> implements UndirectedGraph<V, E>, Serializable
/*     */   {
/*     */     private UnmodifiableUndirectedGraph(UndirectedGraph<V, E> delegate)
/*     */     {
/* 923 */       super(null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class UnmodifiableForest<V, E> extends Graphs.UnmodifiableGraph<V, E> implements Forest<V, E>, Serializable
/*     */   {
/*     */     private UnmodifiableForest(Forest<V, E> delegate)
/*     */     {
/* 931 */       super(null);
/*     */     }
/*     */     
/*     */     public Collection<Tree<V, E>> getTrees() {
/* 935 */       return ((Forest)this.delegate).getTrees();
/*     */     }
/*     */     
/*     */     public int getChildCount(V vertex)
/*     */     {
/* 940 */       return ((Forest)this.delegate).getChildCount(vertex);
/*     */     }
/*     */     
/*     */     public Collection<E> getChildEdges(V vertex)
/*     */     {
/* 945 */       return ((Forest)this.delegate).getChildEdges(vertex);
/*     */     }
/*     */     
/*     */     public Collection<V> getChildren(V vertex)
/*     */     {
/* 950 */       return ((Forest)this.delegate).getChildren(vertex);
/*     */     }
/*     */     
/*     */     public V getParent(V vertex)
/*     */     {
/* 955 */       return (V)((Forest)this.delegate).getParent(vertex);
/*     */     }
/*     */     
/*     */     public E getParentEdge(V vertex)
/*     */     {
/* 960 */       return (E)((Forest)this.delegate).getParentEdge(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */   static class UnmodifiableTree<V, E> extends Graphs.UnmodifiableForest<V, E> implements Tree<V, E>, Serializable
/*     */   {
/*     */     private UnmodifiableTree(Tree<V, E> delegate)
/*     */     {
/* 968 */       super(null);
/*     */     }
/*     */     
/*     */     public int getDepth(V vertex) {
/* 972 */       return ((Tree)this.delegate).getDepth(vertex);
/*     */     }
/*     */     
/*     */     public int getHeight() {
/* 976 */       return ((Tree)this.delegate).getHeight();
/*     */     }
/*     */     
/*     */     public V getRoot() {
/* 980 */       return (V)((Tree)this.delegate).getRoot();
/*     */     }
/*     */     
/*     */     public Collection<Tree<V, E>> getTrees()
/*     */     {
/* 985 */       return ((Tree)this.delegate).getTrees();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/Graphs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */