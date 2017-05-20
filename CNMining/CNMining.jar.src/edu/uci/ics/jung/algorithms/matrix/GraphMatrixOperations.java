/*     */ package edu.uci.ics.jung.algorithms.matrix;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix2D;
/*     */ import cern.colt.matrix.linalg.Algebra;
/*     */ import edu.uci.ics.jung.algorithms.util.ConstantMap;
/*     */ import edu.uci.ics.jung.algorithms.util.Indexer;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.BidiMap;
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
/*     */ public class GraphMatrixOperations
/*     */ {
/*     */   public static <V, E> Graph<V, E> square(Graph<V, E> g, Factory<E> edgeFactory, MatrixElementOperations<E> meo)
/*     */   {
/*  56 */     Graph<V, E> squaredGraph = null;
/*     */     try {
/*  58 */       squaredGraph = (Graph)g.getClass().newInstance();
/*     */     } catch (InstantiationException e3) {
/*  60 */       e3.printStackTrace();
/*     */     } catch (IllegalAccessException e3) {
/*  62 */       e3.printStackTrace();
/*     */     }
/*     */     
/*  65 */     Collection<V> vertices = g.getVertices();
/*  66 */     for (V v : vertices)
/*     */     {
/*  68 */       squaredGraph.addVertex(v);
/*     */     }
/*  70 */     for (Iterator i$ = vertices.iterator(); i$.hasNext();) { v = i$.next();
/*     */       
/*  72 */       for (i$ = g.getPredecessors(v).iterator(); i$.hasNext();) { src = i$.next();
/*     */         
/*     */ 
/*  75 */         e1 = g.findEdge(src, v);
/*  76 */         for (V dest : g.getSuccessors(v))
/*     */         {
/*     */ 
/*  79 */           E e2 = g.findEdge(v, dest);
/*     */           
/*  81 */           Number pathData = meo.computePathData(e1, e2);
/*  82 */           E e = squaredGraph.findEdge(src, dest);
/*     */           
/*  84 */           if (e == null) {
/*  85 */             e = edgeFactory.create();
/*  86 */             squaredGraph.addEdge(e, src, dest);
/*     */           }
/*  88 */           meo.mergePaths(e, pathData); } } }
/*     */     V v;
/*     */     Iterator i$;
/*     */     V src;
/*  92 */     E e1; return squaredGraph;
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
/*     */   public static <V, E> Graph<V, E> matrixToGraph(DoubleMatrix2D matrix, Factory<? extends Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, Map<E, Number> nev)
/*     */   {
/* 119 */     if (matrix.rows() != matrix.columns())
/*     */     {
/* 121 */       throw new IllegalArgumentException("Matrix must be square.");
/*     */     }
/* 123 */     int size = matrix.rows();
/*     */     
/* 125 */     Graph<V, E> graph = (Graph)graphFactory.create();
/*     */     
/* 127 */     for (int i = 0; i < size; i++)
/*     */     {
/* 129 */       V vertex = vertexFactory.create();
/* 130 */       graph.addVertex(vertex);
/*     */     }
/*     */     
/* 133 */     List<V> vertices = new ArrayList(graph.getVertices());
/* 134 */     for (int i = 0; i < size; i++)
/*     */     {
/* 136 */       for (int j = 0; j < size; j++)
/*     */       {
/* 138 */         double value = matrix.getQuick(i, j);
/* 139 */         if (value != 0.0D)
/*     */         {
/* 141 */           E e = edgeFactory.create();
/* 142 */           if (graph.addEdge(e, vertices.get(i), vertices.get(j)))
/*     */           {
/* 144 */             if ((e != null) && (nev != null)) {
/* 145 */               nev.put(e, Double.valueOf(value));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 152 */     return graph;
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
/*     */   public static <V, E> Graph<V, E> matrixToGraph(DoubleMatrix2D matrix, Factory<? extends Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory)
/*     */   {
/* 165 */     return matrixToGraph(matrix, graphFactory, vertexFactory, edgeFactory, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> SparseDoubleMatrix2D graphToSparseMatrix(Graph<V, E> g)
/*     */   {
/* 177 */     return graphToSparseMatrix(g, null);
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
/*     */   public static <V, E> SparseDoubleMatrix2D graphToSparseMatrix(Graph<V, E> g, Map<E, Number> nev)
/*     */   {
/* 195 */     if (nev == null)
/* 196 */       nev = new ConstantMap(Integer.valueOf(1));
/* 197 */     int numVertices = g.getVertexCount();
/* 198 */     SparseDoubleMatrix2D matrix = new SparseDoubleMatrix2D(numVertices, numVertices);
/*     */     
/*     */ 
/* 201 */     BidiMap<V, Integer> indexer = Indexer.create(g.getVertices());
/* 202 */     int i = 0;
/*     */     
/* 204 */     for (V v : g.getVertices())
/*     */     {
/* 206 */       for (E e : g.getOutEdges(v))
/*     */       {
/* 208 */         V w = g.getOpposite(v, e);
/* 209 */         int j = ((Integer)indexer.get(w)).intValue();
/* 210 */         matrix.set(i, j, matrix.getQuick(i, j) + ((Number)nev.get(e)).doubleValue());
/*     */       }
/* 212 */       i++;
/*     */     }
/* 214 */     return matrix;
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
/*     */   public static <V, E> SparseDoubleMatrix2D createVertexDegreeDiagonalMatrix(Graph<V, E> graph)
/*     */   {
/* 230 */     int numVertices = graph.getVertexCount();
/* 231 */     SparseDoubleMatrix2D matrix = new SparseDoubleMatrix2D(numVertices, numVertices);
/*     */     
/* 233 */     int i = 0;
/* 234 */     for (V v : graph.getVertices())
/*     */     {
/* 236 */       matrix.set(i, i, graph.degree(v));
/* 237 */       i++;
/*     */     }
/* 239 */     return matrix;
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
/*     */   public static <V, E> DoubleMatrix2D computeVoltagePotentialMatrix(UndirectedGraph<V, E> graph)
/*     */   {
/* 261 */     int numVertices = graph.getVertexCount();
/*     */     
/* 263 */     DoubleMatrix2D A = graphToSparseMatrix(graph, null);
/*     */     
/*     */ 
/* 266 */     DoubleMatrix2D D = createVertexDegreeDiagonalMatrix(graph);
/*     */     
/* 268 */     DoubleMatrix2D temp = new SparseDoubleMatrix2D(numVertices - 1, numVertices - 1);
/*     */     
/*     */ 
/* 271 */     for (int i = 0; i < numVertices - 1; i++)
/*     */     {
/* 273 */       for (int j = 0; j < numVertices - 1; j++)
/*     */       {
/* 275 */         temp.set(i, j, D.get(i, j) - A.get(i, j));
/*     */       }
/*     */     }
/* 278 */     Algebra algebra = new Algebra();
/* 279 */     DoubleMatrix2D tempInverse = algebra.inverse(temp);
/* 280 */     DoubleMatrix2D T = new SparseDoubleMatrix2D(numVertices, numVertices);
/*     */     
/* 282 */     for (int i = 0; i < numVertices - 1; i++)
/*     */     {
/* 284 */       for (int j = 0; j < numVertices - 1; j++)
/*     */       {
/* 286 */         T.set(i, j, tempInverse.get(i, j));
/*     */       }
/*     */     }
/* 289 */     return T;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V> DoubleMatrix1D mapTo1DMatrix(Map<V, Number> map)
/*     */   {
/* 301 */     int numVertices = map.size();
/* 302 */     DoubleMatrix1D vector = new DenseDoubleMatrix1D(numVertices);
/* 303 */     int i = 0;
/* 304 */     for (V v : map.keySet())
/*     */     {
/* 306 */       vector.set(i, ((Number)map.get(v)).doubleValue());
/* 307 */       i++;
/*     */     }
/* 309 */     return vector;
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
/*     */   public static <V, E> DoubleMatrix2D computeMeanFirstPassageMatrix(Graph<V, E> G, Map<E, Number> edgeWeights, DoubleMatrix1D stationaryDistribution)
/*     */   {
/* 339 */     DoubleMatrix2D temp = graphToSparseMatrix(G, edgeWeights);
/*     */     
/* 341 */     for (int i = 0; i < temp.rows(); i++)
/*     */     {
/* 343 */       for (int j = 0; j < temp.columns(); j++)
/*     */       {
/* 345 */         double value = -1.0D * temp.get(i, j) + stationaryDistribution.get(j);
/*     */         
/* 347 */         if (i == j)
/* 348 */           value += 1.0D;
/* 349 */         if (value != 0.0D)
/* 350 */           temp.set(i, j, value);
/*     */       }
/*     */     }
/* 353 */     Algebra algebra = new Algebra();
/* 354 */     DoubleMatrix2D fundamentalMatrix = algebra.inverse(temp);
/* 355 */     temp = new SparseDoubleMatrix2D(temp.rows(), temp.columns());
/* 356 */     for (int i = 0; i < temp.rows(); i++)
/*     */     {
/* 358 */       for (int j = 0; j < temp.columns(); j++)
/*     */       {
/* 360 */         double value = -1.0D * fundamentalMatrix.get(i, j);
/* 361 */         value += fundamentalMatrix.get(j, j);
/* 362 */         if (i == j)
/* 363 */           value += 1.0D;
/* 364 */         if (value != 0.0D)
/* 365 */           temp.set(i, j, value);
/*     */       }
/*     */     }
/* 368 */     DoubleMatrix2D stationaryMatrixDiagonal = new SparseDoubleMatrix2D(temp.rows(), temp.columns());
/*     */     
/* 370 */     int numVertices = stationaryDistribution.size();
/* 371 */     for (int i = 0; i < numVertices; i++) {
/* 372 */       stationaryMatrixDiagonal.set(i, i, 1.0D / stationaryDistribution.get(i));
/*     */     }
/* 374 */     DoubleMatrix2D meanFirstPassageMatrix = algebra.mult(temp, stationaryMatrixDiagonal);
/*     */     
/* 376 */     return meanFirstPassageMatrix;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/matrix/GraphMatrixOperations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */