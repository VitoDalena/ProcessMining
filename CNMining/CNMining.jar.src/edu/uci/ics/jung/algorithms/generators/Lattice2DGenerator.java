/*     */ package edu.uci.ics.jung.algorithms.generators;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class Lattice2DGenerator<V, E>
/*     */   implements GraphGenerator<V, E>
/*     */ {
/*     */   protected int row_count;
/*     */   protected int col_count;
/*     */   protected boolean is_toroidal;
/*     */   protected Factory<Graph<V, E>> graph_factory;
/*     */   protected Factory<V> vertex_factory;
/*     */   protected Factory<E> edge_factory;
/*     */   private List<V> v_array;
/*     */   
/*     */   public Lattice2DGenerator(Factory<Graph<V, E>> graph_factory, Factory<V> vertex_factory, Factory<E> edge_factory, int latticeSize, boolean isToroidal)
/*     */   {
/*  57 */     this(graph_factory, vertex_factory, edge_factory, latticeSize, latticeSize, isToroidal);
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
/*     */   public Lattice2DGenerator(Factory<Graph<V, E>> graph_factory, Factory<V> vertex_factory, Factory<E> edge_factory, int row_count, int col_count, boolean isToroidal)
/*     */   {
/*  74 */     if ((row_count < 2) || (col_count < 2))
/*     */     {
/*  76 */       throw new IllegalArgumentException("Row and column counts must each be at least 2.");
/*     */     }
/*     */     
/*  79 */     this.row_count = row_count;
/*  80 */     this.col_count = col_count;
/*  81 */     this.is_toroidal = isToroidal;
/*  82 */     this.graph_factory = graph_factory;
/*  83 */     this.vertex_factory = vertex_factory;
/*  84 */     this.edge_factory = edge_factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> create()
/*     */   {
/*  93 */     int vertex_count = this.row_count * this.col_count;
/*  94 */     Graph<V, E> graph = (Graph)this.graph_factory.create();
/*  95 */     this.v_array = new ArrayList(vertex_count);
/*  96 */     for (int i = 0; i < vertex_count; i++)
/*     */     {
/*  98 */       V v = this.vertex_factory.create();
/*  99 */       graph.addVertex(v);
/* 100 */       this.v_array.add(i, v);
/*     */     }
/*     */     
/* 103 */     int start = this.is_toroidal ? 0 : 1;
/* 104 */     int end_row = this.is_toroidal ? this.row_count : this.row_count - 1;
/* 105 */     int end_col = this.is_toroidal ? this.col_count : this.col_count - 1;
/*     */     
/*     */ 
/*     */ 
/* 109 */     for (int i = 0; i <= end_row; i++) {
/* 110 */       for (int j = 0; j < this.col_count; j++)
/* 111 */         graph.addEdge(this.edge_factory.create(), getVertex(i, j), getVertex(i + 1, j));
/*     */     }
/* 113 */     for (int i = 0; i <= this.row_count; i++) {
/* 114 */       for (int j = 0; j <= end_col; j++) {
/* 115 */         graph.addEdge(this.edge_factory.create(), getVertex(i, j), getVertex(i, j + 1));
/*     */       }
/*     */     }
/* 118 */     if (graph.getDefaultEdgeType() == EdgeType.DIRECTED)
/*     */     {
/*     */ 
/* 121 */       for (int i = start; i < this.row_count; i++) {
/* 122 */         for (int j = 0; j < this.col_count; j++)
/* 123 */           graph.addEdge(this.edge_factory.create(), getVertex(i, j), getVertex(i - 1, j));
/*     */       }
/* 125 */       for (int i = 0; i <= this.row_count; i++) {
/* 126 */         for (int j = start; j <= end_col; j++)
/* 127 */           graph.addEdge(this.edge_factory.create(), getVertex(i, j), getVertex(i, j - 1));
/*     */       }
/*     */     }
/* 130 */     return graph;
/*     */   }
/*     */   
/*     */   protected int getIndex(int i, int j)
/*     */   {
/* 135 */     return i % this.row_count * this.col_count + j % this.col_count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected V getVertex(int i, int j)
/*     */   {
/* 143 */     return (V)this.v_array.get(getIndex(i, j));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected V getVertex(int i)
/*     */   {
/* 151 */     return (V)this.v_array.get(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getRow(int i)
/*     */   {
/* 159 */     return i / this.row_count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getCol(int i)
/*     */   {
/* 167 */     return i % this.col_count;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/Lattice2DGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */