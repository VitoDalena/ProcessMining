/*     */ package edu.uci.ics.jung.io;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.TransformerUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphMLWriter<V, E>
/*     */ {
/*     */   protected Transformer<V, String> vertex_ids;
/*     */   protected Transformer<E, String> edge_ids;
/*     */   protected Map<String, GraphMLMetadata<Hypergraph<V, E>>> graph_data;
/*     */   protected Map<String, GraphMLMetadata<V>> vertex_data;
/*     */   protected Map<String, GraphMLMetadata<E>> edge_data;
/*     */   protected Transformer<V, String> vertex_desc;
/*     */   protected Transformer<E, String> edge_desc;
/*     */   protected Transformer<Hypergraph<V, E>, String> graph_desc;
/*     */   protected boolean directed;
/*     */   protected int nest_level;
/*     */   
/*     */   public GraphMLWriter()
/*     */   {
/*  60 */     this.vertex_ids = new Transformer()
/*     */     {
/*     */       public String transform(V v)
/*     */       {
/*  64 */         return v.toString();
/*     */       }
/*  66 */     };
/*  67 */     this.edge_ids = TransformerUtils.nullTransformer();
/*  68 */     this.graph_data = Collections.emptyMap();
/*  69 */     this.vertex_data = Collections.emptyMap();
/*  70 */     this.edge_data = Collections.emptyMap();
/*  71 */     this.vertex_desc = TransformerUtils.nullTransformer();
/*  72 */     this.edge_desc = TransformerUtils.nullTransformer();
/*  73 */     this.graph_desc = TransformerUtils.nullTransformer();
/*  74 */     this.nest_level = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void save(Hypergraph<V, E> graph, Writer w)
/*     */     throws IOException
/*     */   {
/*  86 */     BufferedWriter bw = new BufferedWriter(w);
/*     */     
/*     */ 
/*  89 */     bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
/*  90 */     bw.write("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns/graphml\"\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  \n");
/*     */     
/*  92 */     bw.write("xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns/graphml\">\n");
/*     */     
/*     */ 
/*  95 */     for (String key : this.graph_data.keySet())
/*  96 */       writeKeySpecification(key, "graph", (GraphMLMetadata)this.graph_data.get(key), bw);
/*  97 */     for (String key : this.vertex_data.keySet())
/*  98 */       writeKeySpecification(key, "node", (GraphMLMetadata)this.vertex_data.get(key), bw);
/*  99 */     for (String key : this.edge_data.keySet()) {
/* 100 */       writeKeySpecification(key, "edge", (GraphMLMetadata)this.edge_data.get(key), bw);
/*     */     }
/*     */     
/*     */ 
/* 104 */     bw.write("<graph edgedefault=\"");
/* 105 */     this.directed = (!(graph instanceof UndirectedGraph));
/* 106 */     if (this.directed) {
/* 107 */       bw.write("directed\">\n");
/*     */     } else {
/* 109 */       bw.write("undirected\">\n");
/*     */     }
/*     */     
/* 112 */     String desc = (String)this.graph_desc.transform(graph);
/* 113 */     if (desc != null) {
/* 114 */       w.write("<desc>" + desc + "</desc>\n");
/*     */     }
/*     */     
/* 117 */     for (String key : this.graph_data.keySet())
/*     */     {
/* 119 */       Transformer<Hypergraph<V, E>, ?> t = ((GraphMLMetadata)this.graph_data.get(key)).transformer;
/* 120 */       Object value = t.transform(graph);
/* 121 */       if (value != null) {
/* 122 */         w.write(format("data", "key", key, value.toString()) + "\n");
/*     */       }
/*     */     }
/*     */     
/* 126 */     writeVertexData(graph, bw);
/*     */     
/*     */ 
/* 129 */     writeEdgeData(graph, bw);
/*     */     
/*     */ 
/* 132 */     bw.write("</graph>\n");
/* 133 */     bw.write("</graphml>\n");
/* 134 */     bw.flush();
/*     */     
/* 136 */     bw.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void writeIndentedText(BufferedWriter w, String to_write)
/*     */     throws IOException
/*     */   {
/* 146 */     for (int i = 0; i < this.nest_level; i++)
/* 147 */       w.write("  ");
/* 148 */     w.write(to_write);
/*     */   }
/*     */   
/*     */   protected void writeVertexData(Hypergraph<V, E> graph, BufferedWriter w) throws IOException
/*     */   {
/* 153 */     for (V v : graph.getVertices())
/*     */     {
/* 155 */       String v_string = String.format("<node id=\"%s\"", new Object[] { this.vertex_ids.transform(v) });
/* 156 */       boolean closed = false;
/*     */       
/* 158 */       String desc = (String)this.vertex_desc.transform(v);
/* 159 */       if (desc != null)
/*     */       {
/* 161 */         w.write(v_string + ">\n");
/* 162 */         closed = true;
/* 163 */         w.write("<desc>" + desc + "</desc>\n");
/*     */       }
/*     */       
/* 166 */       for (String key : this.vertex_data.keySet())
/*     */       {
/* 168 */         Transformer<V, ?> t = ((GraphMLMetadata)this.vertex_data.get(key)).transformer;
/* 169 */         if (t != null)
/*     */         {
/* 171 */           Object value = t.transform(v);
/* 172 */           if (value != null)
/*     */           {
/* 174 */             if (!closed)
/*     */             {
/* 176 */               w.write(v_string + ">\n");
/* 177 */               closed = true;
/*     */             }
/* 179 */             w.write(format("data", "key", key, value.toString()) + "\n");
/*     */           }
/*     */         }
/*     */       }
/* 183 */       if (!closed) {
/* 184 */         w.write(v_string + "/>\n");
/*     */       } else {
/* 186 */         w.write("</node>\n");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void writeEdgeData(Hypergraph<V, E> g, Writer w) throws IOException {
/* 192 */     for (E e : g.getEdges())
/*     */     {
/* 194 */       Collection<V> vertices = g.getIncidentVertices(e);
/* 195 */       String id = (String)this.edge_ids.transform(e);
/*     */       
/* 197 */       boolean is_hyperedge = !(g instanceof Graph);
/* 198 */       String e_string; if (is_hyperedge)
/*     */       {
/* 200 */         String e_string = "<hyperedge ";
/*     */         
/* 202 */         if (id != null) {
/* 203 */           e_string = e_string + "id=\"" + id + "\" ";
/*     */         }
/*     */       }
/*     */       else {
/* 207 */         Pair<V> endpoints = new Pair(vertices);
/* 208 */         V v1 = endpoints.getFirst();
/* 209 */         V v2 = endpoints.getSecond();
/* 210 */         e_string = "<edge ";
/*     */         
/* 212 */         if (id != null) {
/* 213 */           e_string = e_string + "id=\"" + id + "\" ";
/*     */         }
/* 215 */         EdgeType edge_type = g.getEdgeType(e);
/* 216 */         if ((this.directed) && (edge_type == EdgeType.UNDIRECTED))
/* 217 */           e_string = e_string + "directed=\"false\" ";
/* 218 */         if ((!this.directed) && (edge_type == EdgeType.DIRECTED))
/* 219 */           e_string = e_string + "directed=\"true\" ";
/* 220 */         e_string = e_string + "source=\"" + (String)this.vertex_ids.transform(v1) + "\" target=\"" + (String)this.vertex_ids.transform(v2) + "\"";
/*     */       }
/*     */       
/*     */ 
/* 224 */       boolean closed = false;
/*     */       
/* 226 */       String desc = (String)this.edge_desc.transform(e);
/* 227 */       if (desc != null)
/*     */       {
/* 229 */         w.write(e_string + ">\n");
/* 230 */         closed = true;
/* 231 */         w.write("<desc>" + desc + "</desc>\n");
/*     */       }
/*     */       
/* 234 */       for (String key : this.edge_data.keySet())
/*     */       {
/* 236 */         Transformer<E, ?> t = ((GraphMLMetadata)this.edge_data.get(key)).transformer;
/* 237 */         Object value = t.transform(e);
/* 238 */         if (value != null)
/*     */         {
/* 240 */           if (!closed)
/*     */           {
/* 242 */             w.write(e_string + ">\n");
/* 243 */             closed = true;
/*     */           }
/* 245 */           w.write(format("data", "key", key, value.toString()) + "\n");
/*     */         }
/*     */       }
/*     */       
/* 249 */       if (is_hyperedge)
/*     */       {
/* 251 */         for (V v : vertices)
/*     */         {
/* 253 */           if (!closed)
/*     */           {
/* 255 */             w.write(e_string + ">\n");
/* 256 */             closed = true;
/*     */           }
/* 258 */           w.write("<endpoint node=\"" + (String)this.vertex_ids.transform(v) + "\"/>\n");
/*     */         }
/*     */       }
/*     */       
/* 262 */       if (!closed) {
/* 263 */         w.write(e_string + "/>\n");
/*     */       }
/* 265 */       else if (is_hyperedge) {
/* 266 */         w.write("</hyperedge>\n");
/*     */       } else {
/* 268 */         w.write("</edge>\n");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void writeKeySpecification(String key, String type, GraphMLMetadata<?> ds, BufferedWriter bw) throws IOException
/*     */   {
/* 275 */     bw.write("<key id=\"" + key + "\" for=\"" + type + "\"");
/* 276 */     boolean closed = false;
/*     */     
/* 278 */     String desc = ds.description;
/* 279 */     if (desc != null)
/*     */     {
/* 281 */       if (!closed)
/*     */       {
/* 283 */         bw.write(">\n");
/* 284 */         closed = true;
/*     */       }
/* 286 */       bw.write("<desc>" + desc + "</desc>\n");
/*     */     }
/*     */     
/* 289 */     Object def = ds.default_value;
/* 290 */     if (def != null)
/*     */     {
/* 292 */       if (!closed)
/*     */       {
/* 294 */         bw.write(">\n");
/* 295 */         closed = true;
/*     */       }
/* 297 */       bw.write("<default>" + def.toString() + "</default>\n");
/*     */     }
/* 299 */     if (!closed) {
/* 300 */       bw.write("/>\n");
/*     */     } else {
/* 302 */       bw.write("</key>\n");
/*     */     }
/*     */   }
/*     */   
/*     */   protected String format(String type, String attr, String value, String contents) {
/* 307 */     return String.format("<%s %s=\"%s\">%s</%s>", new Object[] { type, attr, value, contents, type });
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
/*     */   public void setVertexIDs(Transformer<V, String> vertex_ids)
/*     */   {
/* 321 */     this.vertex_ids = vertex_ids;
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
/*     */   public void setEdgeIDs(Transformer<E, String> edge_ids)
/*     */   {
/* 334 */     this.edge_ids = edge_ids;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraphData(Map<String, GraphMLMetadata<Hypergraph<V, E>>> graph_map)
/*     */   {
/* 342 */     this.graph_data = graph_map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexData(Map<String, GraphMLMetadata<V>> vertex_map)
/*     */   {
/* 350 */     this.vertex_data = vertex_map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeData(Map<String, GraphMLMetadata<E>> edge_map)
/*     */   {
/* 358 */     this.edge_data = edge_map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addGraphData(String id, String description, String default_value, Transformer<Hypergraph<V, E>, String> graph_transformer)
/*     */   {
/* 367 */     if (this.graph_data.equals(Collections.EMPTY_MAP))
/* 368 */       this.graph_data = new HashMap();
/* 369 */     this.graph_data.put(id, new GraphMLMetadata(description, default_value, graph_transformer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addVertexData(String id, String description, String default_value, Transformer<V, String> vertex_transformer)
/*     */   {
/* 379 */     if (this.vertex_data.equals(Collections.EMPTY_MAP))
/* 380 */       this.vertex_data = new HashMap();
/* 381 */     this.vertex_data.put(id, new GraphMLMetadata(description, default_value, vertex_transformer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEdgeData(String id, String description, String default_value, Transformer<E, String> edge_transformer)
/*     */   {
/* 391 */     if (this.edge_data.equals(Collections.EMPTY_MAP))
/* 392 */       this.edge_data = new HashMap();
/* 393 */     this.edge_data.put(id, new GraphMLMetadata(description, default_value, edge_transformer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexDescriptions(Transformer<V, String> vertex_desc)
/*     */   {
/* 402 */     this.vertex_desc = vertex_desc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeDescriptions(Transformer<E, String> edge_desc)
/*     */   {
/* 410 */     this.edge_desc = edge_desc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraphDescriptions(Transformer<Hypergraph<V, E>, String> graph_desc)
/*     */   {
/* 418 */     this.graph_desc = graph_desc;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/GraphMLWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */