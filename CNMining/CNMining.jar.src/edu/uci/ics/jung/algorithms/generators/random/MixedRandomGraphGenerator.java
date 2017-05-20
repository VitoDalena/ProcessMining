/*    */ package edu.uci.ics.jung.algorithms.generators.random;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.EdgeType;
/*    */ import edu.uci.ics.jung.graph.util.Pair;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Factory;
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
/*    */ public class MixedRandomGraphGenerator
/*    */ {
/*    */   public static <V, E> Graph<V, E> generateMixedRandomGraph(Factory<Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, Map<E, Number> edge_weight, int num_vertices, Set<V> seedVertices)
/*    */   {
/* 40 */     return generateMixedRandomGraph(graphFactory, vertexFactory, edgeFactory, edge_weight, num_vertices, true, seedVertices);
/*    */   }
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
/*    */   public static <V, E> Graph<V, E> generateMixedRandomGraph(Factory<Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, Map<E, Number> edge_weights, int num_vertices, boolean parallel, Set<V> seedVertices)
/*    */   {
/* 58 */     int seed = (int)(Math.random() * 10000.0D);
/* 59 */     BarabasiAlbertGenerator<V, E> bag = new BarabasiAlbertGenerator(graphFactory, vertexFactory, edgeFactory, 4, 3, seed, seedVertices);
/*    */     
/*    */ 
/*    */ 
/* 63 */     bag.evolveGraph(num_vertices - 4);
/* 64 */     Graph<V, E> ug = bag.create();
/*    */     
/*    */ 
/* 67 */     Graph<V, E> g = (Graph)graphFactory.create();
/*    */     
/* 69 */     for (V v : ug.getVertices()) {
/* 70 */       g.addVertex(v);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 76 */     for (E e : ug.getEdges()) {
/* 77 */       V v1 = ug.getEndpoints(e).getFirst();
/* 78 */       V v2 = ug.getEndpoints(e).getSecond();
/*    */       
/* 80 */       E me = edgeFactory.create();
/* 81 */       g.addEdge(me, v1, v2, Math.random() < 0.5D ? EdgeType.DIRECTED : EdgeType.UNDIRECTED);
/* 82 */       edge_weights.put(me, Double.valueOf(Math.random()));
/*    */     }
/*    */     
/* 85 */     return g;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/random/MixedRandomGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */