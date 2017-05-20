/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.event.GraphEvent;
/*     */ import edu.uci.ics.jung.graph.event.GraphEvent.Edge;
/*     */ import edu.uci.ics.jung.graph.event.GraphEvent.Type;
/*     */ import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
/*     */ import edu.uci.ics.jung.graph.event.GraphEventListener;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObservableGraph<V, E>
/*     */   extends GraphDecorator<V, E>
/*     */ {
/*  20 */   List<GraphEventListener<V, E>> listenerList = Collections.synchronizedList(new LinkedList());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObservableGraph(Graph<V, E> delegate)
/*     */   {
/*  27 */     super(delegate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addGraphEventListener(GraphEventListener<V, E> l)
/*     */   {
/*  34 */     this.listenerList.add(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeGraphEventListener(GraphEventListener<V, E> l)
/*     */   {
/*  41 */     this.listenerList.remove(l);
/*     */   }
/*     */   
/*     */   protected void fireGraphEvent(GraphEvent<V, E> evt) {
/*  45 */     for (GraphEventListener<V, E> listener : this.listenerList) {
/*  46 */       listener.handleGraphEvent(evt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices)
/*     */   {
/*  55 */     boolean state = super.addEdge(edge, vertices);
/*  56 */     if (state) {
/*  57 */       GraphEvent<V, E> evt = new GraphEvent.Edge(this.delegate, GraphEvent.Type.EDGE_ADDED, edge);
/*  58 */       fireGraphEvent(evt);
/*     */     }
/*  60 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V v1, V v2, EdgeType edgeType)
/*     */   {
/*  68 */     boolean state = super.addEdge(e, v1, v2, edgeType);
/*  69 */     if (state) {
/*  70 */       GraphEvent<V, E> evt = new GraphEvent.Edge(this.delegate, GraphEvent.Type.EDGE_ADDED, e);
/*  71 */       fireGraphEvent(evt);
/*     */     }
/*  73 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V v1, V v2)
/*     */   {
/*  81 */     boolean state = super.addEdge(e, v1, v2);
/*  82 */     if (state) {
/*  83 */       GraphEvent<V, E> evt = new GraphEvent.Edge(this.delegate, GraphEvent.Type.EDGE_ADDED, e);
/*  84 */       fireGraphEvent(evt);
/*     */     }
/*  86 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addVertex(V vertex)
/*     */   {
/*  94 */     boolean state = super.addVertex(vertex);
/*  95 */     if (state) {
/*  96 */       GraphEvent<V, E> evt = new GraphEvent.Vertex(this.delegate, GraphEvent.Type.VERTEX_ADDED, vertex);
/*  97 */       fireGraphEvent(evt);
/*     */     }
/*  99 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 107 */     boolean state = this.delegate.removeEdge(edge);
/* 108 */     if (state) {
/* 109 */       GraphEvent<V, E> evt = new GraphEvent.Edge(this.delegate, GraphEvent.Type.EDGE_REMOVED, edge);
/* 110 */       fireGraphEvent(evt);
/*     */     }
/* 112 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 123 */     Collection<E> incident_edges = new ArrayList(this.delegate.getIncidentEdges(vertex));
/* 124 */     for (E e : incident_edges) {
/* 125 */       removeEdge(e);
/*     */     }
/* 127 */     boolean state = this.delegate.removeVertex(vertex);
/* 128 */     if (state) {
/* 129 */       GraphEvent<V, E> evt = new GraphEvent.Vertex(this.delegate, GraphEvent.Type.VERTEX_REMOVED, vertex);
/* 130 */       fireGraphEvent(evt);
/*     */     }
/* 132 */     return state;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/ObservableGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */