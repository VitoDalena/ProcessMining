/*     */ package edu.uci.ics.jung.io;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix2D;
/*     */ import edu.uci.ics.jung.algorithms.matrix.GraphMatrixOperations;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
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
/*     */ 
/*     */ 
/*     */ public class MatrixFile<V, E>
/*     */   implements GraphFile<V, E>
/*     */ {
/*     */   private Map<E, Number> mWeightKey;
/*     */   Factory<? extends Graph<V, E>> graphFactory;
/*     */   Factory<V> vertexFactory;
/*     */   Factory<E> edgeFactory;
/*     */   
/*     */   public MatrixFile(Map<E, Number> weightKey, Factory<? extends Graph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory)
/*     */   {
/*  84 */     this.mWeightKey = weightKey;
/*  85 */     this.graphFactory = graphFactory;
/*  86 */     this.vertexFactory = vertexFactory;
/*  87 */     this.edgeFactory = edgeFactory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> load(BufferedReader reader)
/*     */     throws IOException
/*     */   {
/*  97 */     Graph<V, E> graph = null;
/*  98 */     DoubleMatrix2D matrix = createMatrixFromFile(reader);
/*  99 */     graph = GraphMatrixOperations.matrixToGraph(matrix, this.graphFactory, this.vertexFactory, this.edgeFactory);
/*     */     
/* 101 */     return graph;
/*     */   }
/*     */   
/*     */   private DoubleMatrix2D createMatrixFromFile(BufferedReader reader)
/*     */     throws IOException
/*     */   {
/* 107 */     List<List<Double>> rows = new ArrayList();
/* 108 */     String currentLine = null;
/* 109 */     while ((currentLine = reader.readLine()) != null) {
/* 110 */       StringTokenizer tokenizer = new StringTokenizer(currentLine);
/* 111 */       if (tokenizer.countTokens() == 0) {
/*     */         break;
/*     */       }
/* 114 */       List<Double> currentRow = new ArrayList();
/* 115 */       while (tokenizer.hasMoreTokens()) {
/* 116 */         String token = tokenizer.nextToken();
/* 117 */         currentRow.add(Double.valueOf(Double.parseDouble(token)));
/*     */       }
/* 119 */       rows.add(currentRow);
/*     */     }
/* 121 */     int size = rows.size();
/* 122 */     DoubleMatrix2D matrix = new SparseDoubleMatrix2D(size, size);
/* 123 */     for (int i = 0; i < size; i++) {
/* 124 */       List<Double> currentRow = (List)rows.get(i);
/* 125 */       if (currentRow.size() != size) {
/* 126 */         throw new IllegalArgumentException("Matrix must have the same number of rows as columns");
/*     */       }
/*     */       
/* 129 */       for (int j = 0; j < size; j++) {
/* 130 */         double currentVal = ((Double)currentRow.get(j)).doubleValue();
/* 131 */         if (currentVal != 0.0D) {
/* 132 */           matrix.setQuick(i, j, currentVal);
/*     */         }
/*     */       }
/*     */     }
/* 136 */     return matrix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> load(String filename)
/*     */   {
/*     */     try
/*     */     {
/* 146 */       BufferedReader reader = new BufferedReader(new FileReader(filename));
/*     */       
/* 148 */       Graph<V, E> graph = load(reader);
/* 149 */       reader.close();
/* 150 */       return graph;
/*     */     } catch (IOException ioe) {
/* 152 */       throw new RuntimeException("Error in loading file " + filename, ioe);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void save(Graph<V, E> graph, String filename)
/*     */   {
/*     */     try
/*     */     {
/* 162 */       BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
/*     */       
/* 164 */       DoubleMatrix2D matrix = GraphMatrixOperations.graphToSparseMatrix(graph, this.mWeightKey);
/*     */       
/* 166 */       for (int i = 0; i < matrix.rows(); i++) {
/* 167 */         for (int j = 0; j < matrix.columns(); j++) {
/* 168 */           writer.write(String.format("%4.2f ", new Object[] { Double.valueOf(matrix.getQuick(i, j)) }));
/*     */         }
/* 170 */         writer.write("\n");
/*     */       }
/* 172 */       writer.close();
/*     */     } catch (Exception e) {
/* 174 */       throw new RuntimeException("Error saving file: " + filename, e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/MatrixFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */