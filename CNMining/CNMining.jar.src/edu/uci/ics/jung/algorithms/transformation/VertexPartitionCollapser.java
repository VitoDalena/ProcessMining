/*     */ package edu.uci.ics.jung.algorithms.transformation;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.blockmodel.VertexPartition;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.MapTransformer;
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
/*     */ public class VertexPartitionCollapser<V, E, CV, CE>
/*     */ {
/*     */   protected Factory<Graph<CV, CE>> graph_factory;
/*     */   protected Factory<CV> vertex_factory;
/*     */   protected Factory<CE> edge_factory;
/*     */   protected Map<Set<V>, CV> set_collapsedv;
/*     */   
/*     */   public VertexPartitionCollapser(Factory<Graph<CV, CE>> graph_factory, Factory<CV> vertex_factory, Factory<CE> edge_factory)
/*     */   {
/*  52 */     this.graph_factory = graph_factory;
/*  53 */     this.vertex_factory = vertex_factory;
/*  54 */     this.edge_factory = edge_factory;
/*  55 */     this.set_collapsedv = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<CV, CE> collapseVertexPartitions(VertexPartition<V, E> partitioning)
/*     */   {
/*  65 */     Graph<V, E> original = partitioning.getGraph();
/*  66 */     Graph<CV, CE> collapsed = (Graph)this.graph_factory.create();
/*     */     
/*     */ 
/*  69 */     for (Set<V> set : partitioning.getVertexPartitions())
/*     */     {
/*  71 */       CV cv = this.vertex_factory.create();
/*  72 */       collapsed.addVertex(this.vertex_factory.create());
/*  73 */       this.set_collapsedv.put(set, cv);
/*     */     }
/*     */     
/*     */ 
/*  77 */     for (E e : original.getEdges())
/*     */     {
/*  79 */       Collection<V> incident = original.getIncidentVertices(e);
/*  80 */       Collection<CV> collapsed_vertices = new HashSet();
/*  81 */       Map<V, Set<V>> vertex_partitions = partitioning.getVertexToPartitionMap();
/*     */       
/*  83 */       for (V v : incident) {
/*  84 */         collapsed_vertices.add(this.set_collapsedv.get(vertex_partitions.get(v)));
/*     */       }
/*  86 */       if (collapsed_vertices.size() > 1)
/*     */       {
/*  88 */         CE ce = this.edge_factory.create();
/*  89 */         collapsed.addEdge(ce, collapsed_vertices);
/*     */       }
/*     */     }
/*  92 */     return collapsed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<Set<V>, CV> getSetToCollapsedVertexTransformer()
/*     */   {
/* 101 */     return MapTransformer.getInstance(this.set_collapsedv);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/transformation/VertexPartitionCollapser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */