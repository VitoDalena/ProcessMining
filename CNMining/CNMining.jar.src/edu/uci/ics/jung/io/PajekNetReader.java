/*     */ package edu.uci.ics.jung.io;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.MapSettableTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.SettableTransformer;
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.functors.OrPredicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PajekNetReader<G extends Graph<V, E>, V, E>
/*     */ {
/*     */   protected Factory<V> vertex_factory;
/*     */   protected Factory<E> edge_factory;
/* 100 */   protected SettableTransformer<V, String> vertex_labels = new MapSettableTransformer(new HashMap());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 105 */   protected SettableTransformer<V, Point2D> vertex_locations = new MapSettableTransformer(new HashMap());
/*     */   
/* 107 */   protected SettableTransformer<E, Number> edge_weights = new MapSettableTransformer(new HashMap());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 114 */   private static final Predicate<String> v_pred = new StartsWithPredicate("*vertices");
/* 115 */   private static final Predicate<String> a_pred = new StartsWithPredicate("*arcs");
/* 116 */   private static final Predicate<String> e_pred = new StartsWithPredicate("*edges");
/* 117 */   private static final Predicate<String> t_pred = new StartsWithPredicate("*");
/* 118 */   private static final Predicate<String> c_pred = OrPredicate.getInstance(a_pred, e_pred);
/* 119 */   protected static final Predicate<String> l_pred = ListTagPred.getInstance();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PajekNetReader(Factory<V> vertex_factory, Factory<E> edge_factory)
/*     */   {
/* 128 */     this.vertex_factory = vertex_factory;
/* 129 */     this.edge_factory = edge_factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PajekNetReader(Factory<E> edge_factory)
/*     */   {
/* 141 */     this(null, edge_factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public G load(String filename, Factory<? extends G> graph_factory)
/*     */     throws IOException
/*     */   {
/* 151 */     return load(new FileReader(filename), (Graph)graph_factory.create());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public G load(Reader reader, Factory<? extends G> graph_factory)
/*     */     throws IOException
/*     */   {
/* 161 */     return load(reader, (Graph)graph_factory.create());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public G load(String filename, G g)
/*     */     throws IOException
/*     */   {
/* 171 */     if (g == null)
/* 172 */       throw new IllegalArgumentException("Graph provided must be non-null");
/* 173 */     return load(new FileReader(filename), g);
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
/*     */   public G load(Reader reader, G g)
/*     */     throws IOException
/*     */   {
/* 191 */     BufferedReader br = new BufferedReader(reader);
/*     */     
/*     */ 
/* 194 */     String curLine = skip(br, v_pred);
/*     */     
/* 196 */     if (curLine == null) {
/* 197 */       return g;
/*     */     }
/*     */     
/* 200 */     StringTokenizer st = new StringTokenizer(curLine);
/* 201 */     st.nextToken();
/* 202 */     int num_vertices = Integer.parseInt(st.nextToken());
/* 203 */     List<V> id = null;
/* 204 */     if (this.vertex_factory != null)
/*     */     {
/* 206 */       for (int i = 1; i <= num_vertices; i++)
/* 207 */         g.addVertex(this.vertex_factory.create());
/* 208 */       id = new ArrayList(g.getVertices());
/*     */     }
/*     */     
/*     */ 
/* 212 */     curLine = null;
/* 213 */     while (br.ready())
/*     */     {
/* 215 */       curLine = br.readLine();
/* 216 */       if ((curLine != null) && (!t_pred.evaluate(curLine)))
/*     */       {
/* 218 */         if (curLine == "") {
/*     */           continue;
/*     */         }
/*     */         try
/*     */         {
/* 223 */           readVertex(curLine, id, num_vertices);
/*     */         }
/*     */         catch (IllegalArgumentException iae)
/*     */         {
/* 227 */           br.close();
/* 228 */           reader.close();
/* 229 */           throw iae;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 235 */     curLine = readArcsOrEdges(curLine, br, g, id, this.edge_factory);
/*     */     
/*     */ 
/* 238 */     readArcsOrEdges(curLine, br, g, id, this.edge_factory);
/*     */     
/* 240 */     br.close();
/* 241 */     reader.close();
/*     */     
/* 243 */     return g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readVertex(String curLine, List<V> id, int num_vertices)
/*     */   {
/* 254 */     String[] parts = null;
/* 255 */     int coord_idx = -1;
/*     */     
/* 257 */     String label = null;
/*     */     String index;
/* 259 */     if (curLine.indexOf('"') != -1)
/*     */     {
/* 261 */       String[] initial_split = curLine.trim().split("\"");
/*     */       
/* 263 */       if ((initial_split.length < 2) || (initial_split.length > 3)) {
/* 264 */         throw new IllegalArgumentException("Unbalanced (or too many) quote marks in " + curLine);
/*     */       }
/* 266 */       String index = initial_split[0].trim();
/* 267 */       label = initial_split[1].trim();
/* 268 */       if (initial_split.length == 3)
/* 269 */         parts = initial_split[2].trim().split("\\s+", -1);
/* 270 */       coord_idx = 0;
/*     */     }
/*     */     else
/*     */     {
/* 274 */       parts = curLine.trim().split("\\s+", -1);
/* 275 */       index = parts[0];
/* 276 */       switch (parts.length)
/*     */       {
/*     */       case 1: 
/*     */         break;
/*     */       case 2: 
/* 281 */         label = parts[1];
/* 282 */         break;
/*     */       case 3: 
/* 284 */         coord_idx = 1;
/* 285 */         break;
/*     */       default: 
/* 287 */         coord_idx = 2;
/*     */       }
/*     */       
/*     */     }
/* 291 */     int v_id = Integer.parseInt(index) - 1;
/* 292 */     if ((v_id >= num_vertices) || (v_id < 0))
/* 293 */       throw new IllegalArgumentException("Vertex number " + v_id + "is not in the range [1," + num_vertices + "]");
/*     */     V v;
/* 295 */     V v; if (id != null) {
/* 296 */       v = id.get(v_id);
/*     */     } else {
/* 298 */       v = new Integer(v_id);
/*     */     }
/* 300 */     if ((label != null) && (label.length() > 0) && (this.vertex_labels != null)) {
/* 301 */       this.vertex_labels.set(v, label);
/*     */     }
/*     */     
/* 304 */     if ((coord_idx != -1) && (parts != null) && (parts.length >= coord_idx + 2) && (this.vertex_locations != null))
/*     */     {
/* 306 */       double x = Double.parseDouble(parts[coord_idx]);
/* 307 */       double y = Double.parseDouble(parts[(coord_idx + 1)]);
/* 308 */       this.vertex_locations.set(v, new Point2D.Double(x, y));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String readArcsOrEdges(String curLine, BufferedReader br, Graph<V, E> g, List<V> id, Factory<E> edge_factory)
/*     */     throws IOException
/*     */   {
/* 318 */     String nextLine = curLine;
/*     */     
/*     */ 
/* 321 */     if (!c_pred.evaluate(curLine)) {
/* 322 */       nextLine = skip(br, c_pred);
/*     */     }
/* 324 */     boolean reading_arcs = false;
/* 325 */     boolean reading_edges = false;
/* 326 */     EdgeType directedness = null;
/* 327 */     if (a_pred.evaluate(nextLine))
/*     */     {
/* 329 */       if ((g instanceof UndirectedGraph)) {
/* 330 */         throw new IllegalArgumentException("Supplied undirected-only graph cannot be populated with directed edges");
/*     */       }
/* 332 */       reading_arcs = true;
/* 333 */       directedness = EdgeType.DIRECTED;
/*     */     }
/*     */     
/* 336 */     if (e_pred.evaluate(nextLine))
/*     */     {
/* 338 */       if ((g instanceof DirectedGraph)) {
/* 339 */         throw new IllegalArgumentException("Supplied directed-only graph cannot be populated with undirected edges");
/*     */       }
/* 341 */       reading_edges = true;
/* 342 */       directedness = EdgeType.UNDIRECTED;
/*     */     }
/*     */     
/* 345 */     if ((!reading_arcs) && (!reading_edges)) {
/* 346 */       return nextLine;
/*     */     }
/* 348 */     boolean is_list = l_pred.evaluate(nextLine);
/*     */     
/* 350 */     while (br.ready())
/*     */     {
/* 352 */       nextLine = br.readLine();
/* 353 */       if ((nextLine == null) || (t_pred.evaluate(nextLine)))
/*     */         break;
/* 355 */       if (curLine != "")
/*     */       {
/*     */ 
/* 358 */         StringTokenizer st = new StringTokenizer(nextLine.trim());
/*     */         
/* 360 */         int vid1 = Integer.parseInt(st.nextToken()) - 1;
/*     */         V v1;
/* 362 */         V v1; if (id != null) {
/* 363 */           v1 = id.get(vid1);
/*     */         } else {
/* 365 */           v1 = new Integer(vid1);
/*     */         }
/*     */         
/* 368 */         if (is_list)
/*     */         {
/*     */           do
/*     */           {
/* 372 */             createAddEdge(st, v1, directedness, g, id, edge_factory);
/* 373 */           } while (st.hasMoreTokens());
/*     */         }
/*     */         else
/*     */         {
/* 377 */           E e = createAddEdge(st, v1, directedness, g, id, edge_factory);
/*     */           
/* 379 */           if ((this.edge_weights != null) && (st.hasMoreTokens()))
/* 380 */             this.edge_weights.set(e, new Float(st.nextToken()));
/*     */         }
/*     */       } }
/* 383 */     return nextLine;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected E createAddEdge(StringTokenizer st, V v1, EdgeType directed, Graph<V, E> g, List<V> id, Factory<E> edge_factory)
/*     */   {
/* 390 */     int vid2 = Integer.parseInt(st.nextToken()) - 1;
/*     */     V v2;
/* 392 */     V v2; if (id != null) {
/* 393 */       v2 = id.get(vid2);
/*     */     } else
/* 395 */       v2 = new Integer(vid2);
/* 396 */     E e = edge_factory.create();
/*     */     
/*     */ 
/*     */ 
/* 400 */     g.addEdge(e, v1, v2, directed);
/* 401 */     return e;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String skip(BufferedReader br, Predicate<String> p)
/*     */     throws IOException
/*     */   {
/* 412 */     while (br.ready())
/*     */     {
/* 414 */       String curLine = br.readLine();
/* 415 */       if (curLine == null)
/*     */         break;
/* 417 */       curLine = curLine.trim();
/* 418 */       if (p.evaluate(curLine))
/* 419 */         return curLine;
/*     */     }
/* 421 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static class StartsWithPredicate
/*     */     implements Predicate<String>
/*     */   {
/*     */     private String tag;
/*     */     
/*     */ 
/*     */     protected StartsWithPredicate(String s)
/*     */     {
/* 434 */       this.tag = s;
/*     */     }
/*     */     
/*     */     public boolean evaluate(String str) {
/* 438 */       return (str != null) && (str.toLowerCase().startsWith(this.tag));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class ListTagPred
/*     */     implements Predicate<String>
/*     */   {
/*     */     protected static ListTagPred instance;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected static ListTagPred getInstance()
/*     */     {
/* 457 */       if (instance == null)
/* 458 */         instance = new ListTagPred();
/* 459 */       return instance;
/*     */     }
/*     */     
/*     */     public boolean evaluate(String s)
/*     */     {
/* 464 */       return (s != null) && (s.toLowerCase().endsWith("list"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SettableTransformer<V, Point2D> getVertexLocationTransformer()
/*     */   {
/* 472 */     return this.vertex_locations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexLocationTransformer(SettableTransformer<V, Point2D> vertex_locations)
/*     */   {
/* 480 */     this.vertex_locations = vertex_locations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SettableTransformer<V, String> getVertexLabeller()
/*     */   {
/* 487 */     return this.vertex_labels;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexLabeller(SettableTransformer<V, String> vertex_labels)
/*     */   {
/* 495 */     this.vertex_labels = vertex_labels;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SettableTransformer<E, Number> getEdgeWeightTransformer()
/*     */   {
/* 503 */     return this.edge_weights;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeWeightTransformer(SettableTransformer<E, Number> edge_weights)
/*     */   {
/* 511 */     this.edge_weights = edge_weights;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/PajekNetReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */