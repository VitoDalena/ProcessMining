/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.comparators.ComparableComparator;
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
/*     */ public class SortedSparseMultigraph<V, E>
/*     */   extends OrderedSparseMultigraph<V, E>
/*     */   implements MultiGraph<V, E>, Serializable
/*     */ {
/*     */   protected Comparator<V> vertex_comparator;
/*     */   protected Comparator<E> edge_comparator;
/*     */   
/*     */   public static <V, E> Factory<Graph<V, E>> getFactory()
/*     */   {
/*  46 */     new Factory()
/*     */     {
/*     */       public Graph<V, E> create()
/*     */       {
/*  50 */         return new SortedSparseMultigraph();
/*     */       }
/*     */     };
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
/*     */   public SortedSparseMultigraph(Comparator<V> vertex_comparator, Comparator<E> edge_comparator)
/*     */   {
/*  73 */     this.vertex_comparator = vertex_comparator;
/*  74 */     this.edge_comparator = edge_comparator;
/*  75 */     this.vertices = new TreeMap(vertex_comparator);
/*  76 */     this.edges = new TreeMap(edge_comparator);
/*  77 */     this.directedEdges = new TreeSet(edge_comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortedSparseMultigraph()
/*     */   {
/*  87 */     this(new ComparableComparator(), new ComparableComparator());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexComparator(Comparator<V> vertex_comparator)
/*     */   {
/*  96 */     this.vertex_comparator = vertex_comparator;
/*  97 */     Map<V, Pair<Set<E>>> tmp_vertices = new TreeMap(vertex_comparator);
/*  98 */     for (Map.Entry<V, Pair<Set<E>>> entry : this.vertices.entrySet())
/*  99 */       tmp_vertices.put(entry.getKey(), entry.getValue());
/* 100 */     this.vertices = tmp_vertices;
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 105 */     if (vertex == null) {
/* 106 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/* 108 */     if (!containsVertex(vertex))
/*     */     {
/* 110 */       this.vertices.put(vertex, new Pair(new TreeSet(this.edge_comparator), new TreeSet(this.edge_comparator)));
/*     */       
/* 112 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 116 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/SortedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */