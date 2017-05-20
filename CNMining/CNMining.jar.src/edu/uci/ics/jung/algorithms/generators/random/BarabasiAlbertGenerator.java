/*     */ package edu.uci.ics.jung.algorithms.generators.random;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.generators.EvolvingGraphGenerator;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.MultiGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
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
/*     */ public class BarabasiAlbertGenerator<V, E>
/*     */   implements EvolvingGraphGenerator<V, E>
/*     */ {
/*  75 */   private Graph<V, E> mGraph = null;
/*     */   
/*     */ 
/*     */   private int mNumEdgesToAttachPerStep;
/*     */   
/*     */ 
/*     */   private int mElapsedTimeSteps;
/*     */   
/*     */   private Random mRandom;
/*     */   
/*     */   protected List<V> vertex_index;
/*     */   
/*     */   protected int init_vertices;
/*     */   
/*     */   protected Map<V, Integer> index_vertex;
/*     */   
/*     */   protected Factory<Graph<V, E>> graphFactory;
/*     */   
/*     */   protected Factory<V> vertexFactory;
/*     */   
/*     */   protected Factory<E> edgeFactory;
/*     */   
/*     */ 
/*     */   public BarabasiAlbertGenerator(Factory<Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int init_vertices, int numEdgesToAttach, int seed, Set<V> seedVertices)
/*     */   {
/* 100 */     assert (init_vertices > 0) : "Number of initial unconnected 'seed' vertices must be positive";
/*     */     
/* 102 */     assert (numEdgesToAttach > 0) : "Number of edges to attach at each time step must be positive";
/*     */     
/*     */ 
/* 105 */     this.mNumEdgesToAttachPerStep = numEdgesToAttach;
/* 106 */     this.mRandom = new Random(seed);
/* 107 */     this.graphFactory = graphFactory;
/* 108 */     this.vertexFactory = vertexFactory;
/* 109 */     this.edgeFactory = edgeFactory;
/* 110 */     this.init_vertices = init_vertices;
/* 111 */     initialize(seedVertices);
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
/*     */   public BarabasiAlbertGenerator(Factory<Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int init_vertices, int numEdgesToAttach, Set<V> seedVertices)
/*     */   {
/* 125 */     this(graphFactory, vertexFactory, edgeFactory, init_vertices, numEdgesToAttach, (int)System.currentTimeMillis(), seedVertices);
/*     */   }
/*     */   
/*     */   private void initialize(Set<V> seedVertices)
/*     */   {
/* 130 */     this.mGraph = ((Graph)this.graphFactory.create());
/*     */     
/* 132 */     this.vertex_index = new ArrayList(2 * this.init_vertices);
/* 133 */     this.index_vertex = new HashMap(2 * this.init_vertices);
/* 134 */     for (int i = 0; i < this.init_vertices; i++) {
/* 135 */       V v = this.vertexFactory.create();
/* 136 */       this.mGraph.addVertex(v);
/* 137 */       this.vertex_index.add(v);
/* 138 */       this.index_vertex.put(v, Integer.valueOf(i));
/* 139 */       seedVertices.add(v);
/*     */     }
/*     */     
/* 142 */     this.mElapsedTimeSteps = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   private void createRandomEdge(Collection<V> preexistingNodes, V newVertex, Set<Pair<V>> added_pairs)
/*     */   {
/* 148 */     boolean created_edge = false;
/*     */     V attach_point;
/*     */     Pair<V> endpoints;
/* 151 */     do { attach_point = this.vertex_index.get(this.mRandom.nextInt(this.vertex_index.size()));
/*     */       
/* 153 */       endpoints = new Pair(newVertex, attach_point);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 158 */       if (!(this.mGraph instanceof MultiGraph))
/*     */       {
/* 160 */         if (!added_pairs.contains(endpoints))
/*     */         {
/* 162 */           if ((this.mGraph.getDefaultEdgeType() == EdgeType.UNDIRECTED) && (added_pairs.contains(new Pair(attach_point, newVertex)))) {}
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 167 */         double degree = this.mGraph.inDegree(attach_point);
/*     */         
/*     */ 
/*     */ 
/* 171 */         double attach_prob = (degree + 1.0D) / (this.mGraph.getEdgeCount() + this.mGraph.getVertexCount() - 1);
/* 172 */         if (attach_prob >= this.mRandom.nextDouble())
/* 173 */           created_edge = true;
/*     */       }
/* 175 */     } while (!created_edge);
/*     */     
/* 177 */     added_pairs.add(endpoints);
/*     */     
/* 179 */     if (this.mGraph.getDefaultEdgeType() == EdgeType.UNDIRECTED) {
/* 180 */       added_pairs.add(new Pair(attach_point, newVertex));
/*     */     }
/*     */   }
/*     */   
/*     */   public void evolveGraph(int numTimeSteps)
/*     */   {
/* 186 */     for (int i = 0; i < numTimeSteps; i++) {
/* 187 */       evolveGraph();
/* 188 */       this.mElapsedTimeSteps += 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private void evolveGraph() {
/* 193 */     Collection<V> preexistingNodes = this.mGraph.getVertices();
/* 194 */     V newVertex = this.vertexFactory.create();
/*     */     
/* 196 */     this.mGraph.addVertex(newVertex);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 201 */     Set<Pair<V>> added_pairs = new HashSet(this.mNumEdgesToAttachPerStep * 3);
/*     */     
/* 203 */     for (int i = 0; i < this.mNumEdgesToAttachPerStep; i++) {
/* 204 */       createRandomEdge(preexistingNodes, newVertex, added_pairs);
/*     */     }
/* 206 */     for (Pair<V> pair : added_pairs)
/*     */     {
/* 208 */       V v1 = pair.getFirst();
/* 209 */       V v2 = pair.getSecond();
/* 210 */       if ((this.mGraph.getDefaultEdgeType() != EdgeType.UNDIRECTED) || (!this.mGraph.isNeighbor(v1, v2)))
/*     */       {
/* 212 */         this.mGraph.addEdge(this.edgeFactory.create(), pair);
/*     */       }
/*     */     }
/*     */     
/* 216 */     this.vertex_index.add(newVertex);
/* 217 */     this.index_vertex.put(newVertex, new Integer(this.vertex_index.size() - 1));
/*     */   }
/*     */   
/*     */   public int numIterations() {
/* 221 */     return this.mElapsedTimeSteps;
/*     */   }
/*     */   
/*     */   public Graph<V, E> create() {
/* 225 */     return this.mGraph;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/random/BarabasiAlbertGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */