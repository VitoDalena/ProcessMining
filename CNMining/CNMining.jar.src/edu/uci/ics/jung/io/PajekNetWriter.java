/*     */ package edu.uci.ics.jung.io;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PajekNetWriter<V, E>
/*     */ {
/*     */   public void save(Graph<V, E> g, String filename, Transformer<V, String> vs, Transformer<E, Number> nev, Transformer<V, Point2D> vld)
/*     */     throws IOException
/*     */   {
/*  63 */     save(g, new FileWriter(filename), vs, nev, vld);
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
/*     */   public void save(Graph<V, E> g, String filename, Transformer<V, String> vs, Transformer<E, Number> nev)
/*     */     throws IOException
/*     */   {
/*  79 */     save(g, new FileWriter(filename), vs, nev, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void save(Graph<V, E> g, String filename)
/*     */     throws IOException
/*     */   {
/*  90 */     save(g, filename, null, null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void save(Graph<V, E> g, Writer w)
/*     */     throws IOException
/*     */   {
/* 101 */     save(g, w, null, null, null);
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
/*     */   public void save(Graph<V, E> g, Writer w, Transformer<V, String> vs, Transformer<E, Number> nev)
/*     */     throws IOException
/*     */   {
/* 116 */     save(g, w, vs, nev, null);
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
/*     */   public void save(Graph<V, E> graph, Writer w, Transformer<V, String> vs, Transformer<E, Number> nev, Transformer<V, Point2D> vld)
/*     */     throws IOException
/*     */   {
/* 136 */     BufferedWriter writer = new BufferedWriter(w);
/* 137 */     if (nev == null)
/* 138 */       nev = new Transformer() { public Number transform(E e) { return Integer.valueOf(1); } };
/* 139 */     writer.write("*Vertices " + graph.getVertexCount());
/* 140 */     writer.newLine();
/*     */     
/* 142 */     List<V> id = new ArrayList(graph.getVertices());
/* 143 */     for (V currentVertex : graph.getVertices())
/*     */     {
/*     */ 
/* 146 */       int v_id = id.indexOf(currentVertex) + 1;
/* 147 */       writer.write("" + v_id);
/* 148 */       if (vs != null)
/*     */       {
/* 150 */         String label = (String)vs.transform(currentVertex);
/* 151 */         if (label != null)
/* 152 */           writer.write(" \"" + label + "\"");
/*     */       }
/* 154 */       if (vld != null)
/*     */       {
/* 156 */         Point2D location = (Point2D)vld.transform(currentVertex);
/* 157 */         if (location != null)
/* 158 */           writer.write(" " + location.getX() + " " + location.getY() + " 0.0");
/*     */       }
/* 160 */       writer.newLine();
/*     */     }
/*     */     
/* 163 */     Collection<E> d_set = new HashSet();
/* 164 */     Collection<E> u_set = new HashSet();
/*     */     
/* 166 */     boolean directed = graph instanceof DirectedGraph;
/*     */     
/* 168 */     boolean undirected = graph instanceof UndirectedGraph;
/*     */     
/*     */ 
/* 171 */     if (directed)
/* 172 */       d_set.addAll(graph.getEdges());
/* 173 */     if (undirected)
/* 174 */       u_set.addAll(graph.getEdges());
/* 175 */     if ((!directed) && (!undirected))
/*     */     {
/* 177 */       u_set.addAll(graph.getEdges());
/* 178 */       d_set.addAll(graph.getEdges());
/* 179 */       for (E e : graph.getEdges()) {
/* 180 */         if (graph.getEdgeType(e) == EdgeType.UNDIRECTED) {
/* 181 */           d_set.remove(e);
/*     */         } else {
/* 183 */           u_set.remove(e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 189 */     if (!d_set.isEmpty())
/*     */     {
/* 191 */       writer.write("*Arcs");
/* 192 */       writer.newLine();
/*     */     }
/* 194 */     for (E e : d_set)
/*     */     {
/* 196 */       int source_id = id.indexOf(graph.getEndpoints(e).getFirst()) + 1;
/* 197 */       int target_id = id.indexOf(graph.getEndpoints(e).getSecond()) + 1;
/*     */       
/* 199 */       float weight = ((Number)nev.transform(e)).floatValue();
/* 200 */       writer.write(source_id + " " + target_id + " " + weight);
/* 201 */       writer.newLine();
/*     */     }
/*     */     
/*     */ 
/* 205 */     if (!u_set.isEmpty())
/*     */     {
/* 207 */       writer.write("*Edges");
/* 208 */       writer.newLine();
/*     */     }
/* 210 */     for (E e : u_set)
/*     */     {
/* 212 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 213 */       int v1_id = id.indexOf(endpoints.getFirst()) + 1;
/* 214 */       int v2_id = id.indexOf(endpoints.getSecond()) + 1;
/*     */       
/* 216 */       float weight = ((Number)nev.transform(e)).floatValue();
/* 217 */       writer.write(v1_id + " " + v2_id + " " + weight);
/* 218 */       writer.newLine();
/*     */     }
/* 220 */     writer.close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/PajekNetWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */