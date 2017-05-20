/*     */ package edu.uci.ics.jung.algorithms.transformation;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.graph.KPartiteGraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Predicate;
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
/*     */ public class FoldingTransformer<V, E>
/*     */ {
/*     */   public static <V, E> Graph<V, E> foldKPartiteGraph(KPartiteGraph<V, E> g, Predicate<V> p, Factory<Graph<V, E>> graph_factory, Factory<E> edge_factory)
/*     */   {
/*  75 */     Graph<V, E> newGraph = (Graph)graph_factory.create();
/*     */     
/*     */ 
/*  78 */     Collection<V> vertices = g.getVertices(p);
/*  79 */     for (Iterator i$ = vertices.iterator(); i$.hasNext();) { v = i$.next();
/*     */       
/*  81 */       newGraph.addVertex(v);
/*  82 */       for (V s : g.getSuccessors(v))
/*     */       {
/*  84 */         for (V t : g.getSuccessors(s))
/*     */         {
/*  86 */           if ((vertices.contains(t)) && (!t.equals(v)))
/*     */           {
/*  88 */             newGraph.addVertex(t);
/*  89 */             newGraph.addEdge(edge_factory.create(), v, t);
/*     */           } } }
/*     */     }
/*     */     V v;
/*  93 */     return newGraph;
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
/*     */   public static <V, E> Graph<V, Collection<V>> foldKPartiteGraph(KPartiteGraph<V, E> g, Predicate<V> p, Factory<Graph<V, Collection<V>>> graph_factory)
/*     */   {
/* 123 */     Graph<V, Collection<V>> newGraph = (Graph)graph_factory.create();
/*     */     
/*     */ 
/* 126 */     Collection<V> vertices = g.getVertices(p);
/*     */     
/* 128 */     for (Iterator i$ = vertices.iterator(); i$.hasNext();) { v = i$.next();
/*     */       
/* 130 */       newGraph.addVertex(v);
/* 131 */       for (i$ = g.getSuccessors(v).iterator(); i$.hasNext();) { s = i$.next();
/*     */         
/* 133 */         for (V t : g.getSuccessors(s))
/*     */         {
/* 135 */           if ((vertices.contains(t)) && (!t.equals(v)))
/*     */           {
/* 137 */             newGraph.addVertex(t);
/* 138 */             Collection<V> v_coll = (Collection)newGraph.findEdge(v, t);
/* 139 */             if (v_coll == null)
/*     */             {
/* 141 */               v_coll = new ArrayList();
/* 142 */               newGraph.addEdge(v_coll, v, t);
/*     */             }
/* 144 */             v_coll.add(s); } } } }
/*     */     V v;
/*     */     Iterator i$;
/*     */     V s;
/* 148 */     return newGraph;
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
/*     */   public static <V, E> Graph<V, Collection<E>> foldHypergraphEdges(Hypergraph<V, E> h, Factory<Graph<V, Collection<E>>> graph_factory)
/*     */   {
/* 173 */     Graph<V, Collection<E>> target = (Graph)graph_factory.create();
/*     */     
/* 175 */     for (V v : h.getVertices()) {
/* 176 */       target.addVertex(v);
/*     */     }
/* 178 */     for (E e : h.getEdges())
/*     */     {
/* 180 */       ArrayList<V> incident = new ArrayList(h.getIncidentVertices(e));
/* 181 */       populateTarget(target, e, incident);
/*     */     }
/* 183 */     return target;
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
/*     */   public static <V, E> Graph<V, E> foldHypergraphEdges(Hypergraph<V, E> h, Factory<Graph<V, E>> graph_factory, Factory<E> edge_factory)
/*     */   {
/* 209 */     Graph<V, E> target = (Graph)graph_factory.create();
/*     */     
/* 211 */     for (V v : h.getVertices()) {
/* 212 */       target.addVertex(v);
/*     */     }
/* 214 */     for (E e : h.getEdges())
/*     */     {
/* 216 */       ArrayList<V> incident = new ArrayList(h.getIncidentVertices(e));
/* 217 */       for (int i = 0; i < incident.size(); i++)
/* 218 */         for (int j = i + 1; j < incident.size(); j++)
/* 219 */           target.addEdge(edge_factory.create(), incident.get(i), incident.get(j));
/*     */     }
/* 221 */     return target;
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
/*     */   public static <V, E, F> Graph<E, F> foldHypergraphVertices(Hypergraph<V, E> h, Factory<Graph<E, F>> graph_factory, Factory<F> edge_factory)
/*     */   {
/* 249 */     Graph<E, F> target = (Graph)graph_factory.create();
/*     */     
/* 251 */     for (E e : h.getEdges()) {
/* 252 */       target.addVertex(e);
/*     */     }
/* 254 */     for (V v : h.getVertices())
/*     */     {
/* 256 */       ArrayList<E> incident = new ArrayList(h.getIncidentEdges(v));
/* 257 */       for (int i = 0; i < incident.size(); i++) {
/* 258 */         for (int j = i + 1; j < incident.size(); j++)
/* 259 */           target.addEdge(edge_factory.create(), incident.get(i), incident.get(j));
/*     */       }
/*     */     }
/* 262 */     return target;
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
/*     */   public Graph<E, Collection<V>> foldHypergraphVertices(Hypergraph<V, E> h, Factory<Graph<E, Collection<V>>> graph_factory)
/*     */   {
/* 287 */     Graph<E, Collection<V>> target = (Graph)graph_factory.create();
/*     */     
/* 289 */     for (E e : h.getEdges()) {
/* 290 */       target.addVertex(e);
/*     */     }
/* 292 */     for (V v : h.getVertices())
/*     */     {
/* 294 */       ArrayList<E> incident = new ArrayList(h.getIncidentEdges(v));
/* 295 */       populateTarget(target, v, incident);
/*     */     }
/* 297 */     return target;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static <S, T> void populateTarget(Graph<S, Collection<T>> target, T e, ArrayList<S> incident)
/*     */   {
/* 308 */     for (int i = 0; i < incident.size(); i++)
/*     */     {
/* 310 */       S v1 = incident.get(i);
/* 311 */       for (int j = i + 1; j < incident.size(); j++)
/*     */       {
/* 313 */         S v2 = incident.get(j);
/* 314 */         Collection<T> e_coll = (Collection)target.findEdge(v1, v2);
/* 315 */         if (e_coll == null)
/*     */         {
/* 317 */           e_coll = new ArrayList();
/* 318 */           target.addEdge(e_coll, v1, v2);
/*     */         }
/* 320 */         e_coll.add(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/transformation/FoldingTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */