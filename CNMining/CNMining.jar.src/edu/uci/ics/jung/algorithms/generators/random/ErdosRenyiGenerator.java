/*    */ package edu.uci.ics.jung.algorithms.generators.random;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.generators.GraphGenerator;
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
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
/*    */ public class ErdosRenyiGenerator<V, E>
/*    */   implements GraphGenerator<V, E>
/*    */ {
/*    */   private int mNumVertices;
/*    */   private double mEdgeConnectionProbability;
/*    */   private Random mRandom;
/*    */   Factory<UndirectedGraph<V, E>> graphFactory;
/*    */   Factory<V> vertexFactory;
/*    */   Factory<E> edgeFactory;
/*    */   
/*    */   public ErdosRenyiGenerator(Factory<UndirectedGraph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int numVertices, double p)
/*    */   {
/* 45 */     if (numVertices <= 0) {
/* 46 */       throw new IllegalArgumentException("A positive # of vertices must be specified.");
/*    */     }
/* 48 */     this.mNumVertices = numVertices;
/* 49 */     if ((p < 0.0D) || (p > 1.0D)) {
/* 50 */       throw new IllegalArgumentException("p must be between 0 and 1.");
/*    */     }
/* 52 */     this.graphFactory = graphFactory;
/* 53 */     this.vertexFactory = vertexFactory;
/* 54 */     this.edgeFactory = edgeFactory;
/* 55 */     this.mEdgeConnectionProbability = p;
/* 56 */     this.mRandom = new Random();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Graph<V, E> create()
/*    */   {
/* 64 */     UndirectedGraph<V, E> g = (UndirectedGraph)this.graphFactory.create();
/* 65 */     for (int i = 0; i < this.mNumVertices; i++) {
/* 66 */       g.addVertex(this.vertexFactory.create());
/*    */     }
/* 68 */     List<V> list = new ArrayList(g.getVertices());
/*    */     
/* 70 */     for (int i = 0; i < this.mNumVertices - 1; i++) {
/* 71 */       V v_i = list.get(i);
/* 72 */       for (int j = i + 1; j < this.mNumVertices; j++) {
/* 73 */         V v_j = list.get(j);
/* 74 */         if (this.mRandom.nextDouble() < this.mEdgeConnectionProbability) {
/* 75 */           g.addEdge(this.edgeFactory.create(), v_i, v_j);
/*    */         }
/*    */       }
/*    */     }
/* 79 */     return g;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setSeed(long seed)
/*    */   {
/* 87 */     this.mRandom.setSeed(seed);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/random/ErdosRenyiGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */