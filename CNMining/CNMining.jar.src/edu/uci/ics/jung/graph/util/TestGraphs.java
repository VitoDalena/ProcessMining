/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.SparseMultigraph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ public class TestGraphs
/*     */ {
/*  37 */   public static String[][] pairs = { { "a", "b", "3" }, { "a", "c", "4" }, { "a", "d", "5" }, { "d", "c", "6" }, { "d", "e", "7" }, { "e", "f", "8" }, { "f", "g", "9" }, { "h", "i", "1" } };
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
/*     */   public static Graph<String, Number> createTestGraph(boolean directed)
/*     */   {
/*  56 */     Graph<String, Number> graph = null;
/*  57 */     if (directed) {
/*  58 */       graph = new DirectedSparseMultigraph();
/*     */     } else {
/*  60 */       graph = new UndirectedSparseMultigraph();
/*     */     }
/*     */     
/*  63 */     for (int i = 0; i < pairs.length; i++) {
/*  64 */       String[] pair = pairs[i];
/*  65 */       graph.addEdge(Integer.valueOf(Integer.parseInt(pair[2])), pair[0], pair[1]);
/*     */     }
/*  67 */     return graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Graph<String, Number> createChainPlusIsolates(int chain_length, int isolate_count)
/*     */   {
/*  76 */     Graph<String, Number> g = new UndirectedSparseMultigraph();
/*  77 */     if (chain_length > 0)
/*     */     {
/*  79 */       String[] v = new String[chain_length];
/*  80 */       v[0] = "v0";
/*  81 */       g.addVertex(v[0]);
/*  82 */       for (int i = 1; i < chain_length; i++)
/*     */       {
/*  84 */         v[i] = ("v" + i);
/*  85 */         g.addVertex(v[i]);
/*  86 */         g.addEdge(new Double(Math.random()), v[i], v[(i - 1)]);
/*     */       }
/*     */     }
/*  89 */     for (int i = 0; i < isolate_count; i++) {
/*  90 */       String v = "v" + (chain_length + i);
/*  91 */       g.addVertex(v);
/*     */     }
/*  93 */     return g;
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
/*     */   public static Graph<String, Number> createDirectedAcyclicGraph(int layers, int maxNodesPerLayer, double linkprob)
/*     */   {
/* 109 */     DirectedGraph<String, Number> dag = new DirectedSparseMultigraph();
/* 110 */     Set<String> previousLayers = new HashSet();
/* 111 */     Set<String> inThisLayer = new HashSet();
/* 112 */     for (int i = 0; i < layers; i++)
/*     */     {
/* 114 */       int nodesThisLayer = (int)(Math.random() * maxNodesPerLayer) + 1;
/* 115 */       String v; for (int j = 0; j < nodesThisLayer; j++) {
/* 116 */         v = i + ":" + j;
/* 117 */         dag.addVertex(v);
/* 118 */         inThisLayer.add(v);
/*     */         
/* 120 */         for (String v2 : previousLayers) {
/* 121 */           if (Math.random() < linkprob) {
/* 122 */             Double de = new Double(Math.random());
/* 123 */             dag.addEdge(de, v, v2);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 128 */       previousLayers.addAll(inThisLayer);
/* 129 */       inThisLayer.clear();
/*     */     }
/* 131 */     return dag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void createEdge(Graph<String, Number> g, String v1Label, String v2Label, int weight)
/*     */   {
/* 139 */     g.addEdge(new Double(Math.random()), v1Label, v2Label);
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
/*     */   public static Graph<String, Number> getOneComponentGraph()
/*     */   {
/* 152 */     UndirectedGraph<String, Number> g = new UndirectedSparseMultigraph();
/*     */     
/* 154 */     for (int i = 1; i <= 10; i++) {
/* 155 */       for (int j = i + 1; j <= 10; j++) {
/* 156 */         String i1 = "" + i;
/* 157 */         String i2 = "" + j;
/* 158 */         g.addEdge(Double.valueOf(Math.pow(i + 2, j)), i1, i2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 163 */     for (int i = 11; i <= 20; i++) {
/* 164 */       for (int j = i + 1; j <= 20; j++) {
/* 165 */         if (Math.random() <= 0.6D)
/*     */         {
/* 167 */           String i1 = "" + i;
/* 168 */           String i2 = "" + j;
/* 169 */           g.addEdge(Double.valueOf(Math.pow(i + 2, j)), i1, i2);
/*     */         }
/*     */       }
/*     */     }
/* 173 */     List<String> index = new ArrayList();
/* 174 */     index.addAll(g.getVertices());
/*     */     
/* 176 */     for (int i = 0; i < index.size() - 1; i++) {
/* 177 */       g.addEdge(new Integer(i), index.get(i), index.get(i + 1));
/*     */     }
/* 179 */     return g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Graph<String, Number> getDemoGraph()
/*     */   {
/* 190 */     UndirectedGraph<String, Number> g = new UndirectedSparseMultigraph();
/*     */     
/*     */ 
/* 193 */     for (int i = 0; i < pairs.length; i++) {
/* 194 */       String[] pair = pairs[i];
/* 195 */       createEdge(g, pair[0], pair[1], Integer.parseInt(pair[2]));
/*     */     }
/*     */     
/*     */ 
/* 199 */     for (int i = 1; i <= 10; i++) {
/* 200 */       for (int j = i + 1; j <= 10; j++) {
/* 201 */         String i1 = "c" + i;
/* 202 */         String i2 = "c" + j;
/* 203 */         g.addEdge(Double.valueOf(Math.pow(i + 2, j)), i1, i2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 208 */     for (int i = 11; i <= 20; i++) {
/* 209 */       for (int j = i + 1; j <= 20; j++)
/* 210 */         if (Math.random() <= 0.6D)
/*     */         {
/* 212 */           String i1 = "p" + i;
/* 213 */           String i2 = "p" + j;
/* 214 */           g.addEdge(Double.valueOf(Math.pow(i + 2, j)), i1, i2);
/*     */         }
/*     */     }
/* 217 */     return g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Graph<String, Number> getSmallGraph()
/*     */   {
/* 224 */     Graph<String, Number> graph = new SparseMultigraph();
/*     */     
/* 226 */     String[] v = new String[3];
/* 227 */     for (int i = 0; i < 3; i++) {
/* 228 */       v[i] = String.valueOf(i);
/* 229 */       graph.addVertex(v[i]);
/*     */     }
/* 231 */     graph.addEdge(new Double(0.0D), v[0], v[1], EdgeType.DIRECTED);
/* 232 */     graph.addEdge(new Double(0.1D), v[0], v[1], EdgeType.DIRECTED);
/* 233 */     graph.addEdge(new Double(0.2D), v[0], v[1], EdgeType.DIRECTED);
/* 234 */     graph.addEdge(new Double(0.3D), v[1], v[0], EdgeType.DIRECTED);
/* 235 */     graph.addEdge(new Double(0.4D), v[1], v[0], EdgeType.DIRECTED);
/* 236 */     graph.addEdge(new Double(0.5D), v[1], v[2]);
/* 237 */     graph.addEdge(new Double(0.6D), v[1], v[2]);
/*     */     
/* 239 */     return graph;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/TestGraphs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */