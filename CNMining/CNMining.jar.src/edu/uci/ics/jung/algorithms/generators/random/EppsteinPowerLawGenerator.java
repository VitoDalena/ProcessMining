/*     */ package edu.uci.ics.jung.algorithms.generators.random;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.generators.GraphGenerator;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class EppsteinPowerLawGenerator<V, E>
/*     */   implements GraphGenerator<V, E>
/*     */ {
/*     */   private int mNumVertices;
/*     */   private int mNumEdges;
/*     */   private int mNumIterations;
/*     */   private double mMaxDegree;
/*     */   private Random mRandom;
/*     */   private Factory<Graph<V, E>> graphFactory;
/*     */   private Factory<V> vertexFactory;
/*     */   private Factory<E> edgeFactory;
/*     */   
/*     */   public EppsteinPowerLawGenerator(Factory<Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int numVertices, int numEdges, int r)
/*     */   {
/*  49 */     this.graphFactory = graphFactory;
/*  50 */     this.vertexFactory = vertexFactory;
/*  51 */     this.edgeFactory = edgeFactory;
/*  52 */     this.mNumVertices = numVertices;
/*  53 */     this.mNumEdges = numEdges;
/*  54 */     this.mNumIterations = r;
/*  55 */     this.mRandom = new Random();
/*     */   }
/*     */   
/*     */   protected Graph<V, E> initializeGraph() {
/*  59 */     Graph<V, E> graph = null;
/*  60 */     graph = (Graph)this.graphFactory.create();
/*  61 */     for (int i = 0; i < this.mNumVertices; i++) {
/*  62 */       graph.addVertex(this.vertexFactory.create());
/*     */     }
/*  64 */     List<V> vertices = new ArrayList(graph.getVertices());
/*  65 */     while (graph.getEdgeCount() < this.mNumEdges) {
/*  66 */       V u = vertices.get((int)(this.mRandom.nextDouble() * this.mNumVertices));
/*  67 */       V v = vertices.get((int)(this.mRandom.nextDouble() * this.mNumVertices));
/*  68 */       if (!graph.isSuccessor(v, u)) {
/*  69 */         graph.addEdge(this.edgeFactory.create(), u, v);
/*     */       }
/*     */     }
/*     */     
/*  73 */     double maxDegree = 0.0D;
/*  74 */     for (V v : graph.getVertices()) {
/*  75 */       maxDegree = Math.max(graph.degree(v), maxDegree);
/*     */     }
/*  77 */     this.mMaxDegree = maxDegree;
/*     */     
/*  79 */     return graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> create()
/*     */   {
/*  87 */     Graph<V, E> graph = initializeGraph();
/*     */     
/*  89 */     List<V> vertices = new ArrayList(graph.getVertices());
/*  90 */     for (int rIdx = 0; rIdx < this.mNumIterations; rIdx++)
/*     */     {
/*  92 */       V v = null;
/*  93 */       int degree = 0;
/*     */       do {
/*  95 */         v = vertices.get((int)(this.mRandom.nextDouble() * this.mNumVertices));
/*  96 */         degree = graph.degree(v);
/*     */       }
/*  98 */       while (degree == 0);
/*     */       
/* 100 */       List<E> edges = new ArrayList(graph.getIncidentEdges(v));
/* 101 */       E randomExistingEdge = edges.get((int)(this.mRandom.nextDouble() * degree));
/*     */       
/*     */ 
/*     */ 
/* 105 */       V x = vertices.get((int)(this.mRandom.nextDouble() * this.mNumVertices));
/* 106 */       V y = null;
/*     */       do {
/* 108 */         y = vertices.get((int)(this.mRandom.nextDouble() * this.mNumVertices));
/*     */       }
/* 110 */       while (this.mRandom.nextDouble() > (graph.degree(y) + 1) / this.mMaxDegree);
/*     */       
/* 112 */       if ((!graph.isSuccessor(y, x)) && (x != y)) {
/* 113 */         graph.removeEdge(randomExistingEdge);
/* 114 */         graph.addEdge(this.edgeFactory.create(), x, y);
/*     */       }
/*     */     }
/*     */     
/* 118 */     return graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeed(long seed)
/*     */   {
/* 126 */     this.mRandom.setSeed(seed);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/random/EppsteinPowerLawGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */