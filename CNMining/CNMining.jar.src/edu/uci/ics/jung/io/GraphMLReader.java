/*     */ package edu.uci.ics.jung.io;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.MapSettableTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.SettableTransformer;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.bidimap.DualHashBidiMap;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphMLReader<G extends Hypergraph<V, E>, V, E>
/*     */   extends DefaultHandler
/*     */ {
/*     */   protected SAXParser saxp;
/*     */   protected EdgeType default_edgetype;
/*     */   protected G current_graph;
/*     */   protected V current_vertex;
/*     */   protected E current_edge;
/*     */   protected String current_key;
/*     */   protected LinkedList<TagState> current_states;
/*     */   protected BidiMap<String, TagState> tag_state;
/*     */   protected Factory<G> graph_factory;
/*     */   protected Factory<V> vertex_factory;
/*     */   protected Factory<E> edge_factory;
/*     */   protected BidiMap<V, String> vertex_ids;
/*     */   protected BidiMap<E, String> edge_ids;
/*     */   protected Map<String, GraphMLMetadata<G>> graph_metadata;
/*     */   protected Map<String, GraphMLMetadata<V>> vertex_metadata;
/*     */   protected Map<String, GraphMLMetadata<E>> edge_metadata;
/*     */   protected Map<V, String> vertex_desc;
/*     */   protected Map<E, String> edge_desc;
/*     */   protected Map<G, String> graph_desc;
/*     */   protected KeyType key_type;
/*     */   protected Collection<V> hyperedge_vertices;
/*     */   protected List<G> graphs;
/*     */   
/*     */   protected static enum TagState
/*     */   {
/*  70 */     NO_TAG,  VERTEX,  EDGE,  HYPEREDGE,  ENDPOINT,  GRAPH, 
/*  71 */     DATA,  KEY,  DESC,  DEFAULT_KEY,  GRAPHML,  OTHER;
/*     */     private TagState() {} }
/*  73 */   protected static enum KeyType { NONE,  VERTEX,  EDGE,  GRAPH,  ALL;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private KeyType() {}
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
/*     */   public GraphMLReader(Factory<V> vertex_factory, Factory<E> edge_factory)
/*     */     throws ParserConfigurationException, SAXException
/*     */   {
/* 112 */     this.current_vertex = null;
/* 113 */     this.current_edge = null;
/*     */     
/* 115 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/* 116 */     this.saxp = factory.newSAXParser();
/*     */     
/* 118 */     this.current_states = new LinkedList();
/*     */     
/* 120 */     this.tag_state = new DualHashBidiMap();
/* 121 */     this.tag_state.put("node", TagState.VERTEX);
/* 122 */     this.tag_state.put("edge", TagState.EDGE);
/* 123 */     this.tag_state.put("hyperedge", TagState.HYPEREDGE);
/* 124 */     this.tag_state.put("endpoint", TagState.ENDPOINT);
/* 125 */     this.tag_state.put("graph", TagState.GRAPH);
/* 126 */     this.tag_state.put("data", TagState.DATA);
/* 127 */     this.tag_state.put("key", TagState.KEY);
/* 128 */     this.tag_state.put("desc", TagState.DESC);
/* 129 */     this.tag_state.put("default", TagState.DEFAULT_KEY);
/* 130 */     this.tag_state.put("graphml", TagState.GRAPHML);
/*     */     
/* 132 */     this.key_type = KeyType.NONE;
/*     */     
/* 134 */     this.vertex_factory = vertex_factory;
/* 135 */     this.edge_factory = edge_factory;
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
/*     */   public GraphMLReader()
/*     */     throws ParserConfigurationException, SAXException
/*     */   {
/* 150 */     this(null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<G> loadMultiple(Reader reader, Factory<G> graph_factory)
/*     */     throws IOException
/*     */   {
/* 161 */     this.graph_factory = graph_factory;
/* 162 */     initializeData();
/* 163 */     clearData();
/* 164 */     parse(reader);
/*     */     
/* 166 */     return this.graphs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<G> loadMultiple(String filename, Factory<G> graph_factory)
/*     */     throws IOException
/*     */   {
/* 176 */     return loadMultiple(new FileReader(filename), graph_factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void load(Reader reader, G g)
/*     */     throws IOException
/*     */   {
/* 185 */     this.current_graph = g;
/* 186 */     this.graph_factory = null;
/* 187 */     initializeData();
/* 188 */     clearData();
/*     */     
/* 190 */     parse(reader);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void load(String filename, G g)
/*     */     throws IOException
/*     */   {
/* 199 */     load(new FileReader(filename), g);
/*     */   }
/*     */   
/*     */   protected void clearData()
/*     */   {
/* 204 */     this.vertex_ids.clear();
/* 205 */     this.vertex_desc.clear();
/*     */     
/* 207 */     this.edge_ids.clear();
/* 208 */     this.edge_desc.clear();
/*     */     
/* 210 */     this.graph_desc.clear();
/*     */     
/* 212 */     this.hyperedge_vertices.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void initializeData()
/*     */   {
/* 221 */     this.vertex_ids = new DualHashBidiMap();
/* 222 */     this.vertex_desc = new HashMap();
/* 223 */     this.vertex_metadata = new HashMap();
/*     */     
/* 225 */     this.edge_ids = new DualHashBidiMap();
/* 226 */     this.edge_desc = new HashMap();
/* 227 */     this.edge_metadata = new HashMap();
/*     */     
/* 229 */     this.graph_desc = new HashMap();
/* 230 */     this.graph_metadata = new HashMap();
/*     */     
/* 232 */     this.hyperedge_vertices = new ArrayList();
/*     */   }
/*     */   
/*     */   protected void parse(Reader reader) throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 239 */       this.saxp.parse(new InputSource(reader), this);
/* 240 */       reader.close();
/*     */     }
/*     */     catch (SAXException saxe)
/*     */     {
/* 244 */       throw new IOException(saxe.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String name, String qName, Attributes atts)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 251 */     String tag = qName.toLowerCase();
/* 252 */     TagState state = (TagState)this.tag_state.get(tag);
/* 253 */     if (state == null) {
/* 254 */       state = TagState.OTHER;
/*     */     }
/* 256 */     switch (state)
/*     */     {
/*     */     case GRAPHML: 
/*     */       break;
/*     */     
/*     */     case VERTEX: 
/* 262 */       if (this.current_graph == null)
/* 263 */         throw new SAXNotSupportedException("Graph must be defined prior to elements");
/* 264 */       if ((this.current_edge != null) || (this.current_vertex != null)) {
/* 265 */         throw new SAXNotSupportedException("Nesting elements not supported");
/*     */       }
/* 267 */       createVertex(atts);
/*     */       
/* 269 */       break;
/*     */     
/*     */     case ENDPOINT: 
/* 272 */       if (this.current_graph == null)
/* 273 */         throw new SAXNotSupportedException("Graph must be defined prior to elements");
/* 274 */       if (this.current_edge == null)
/* 275 */         throw new SAXNotSupportedException("No edge defined for endpoint");
/* 276 */       if (this.current_states.getFirst() != TagState.HYPEREDGE)
/* 277 */         throw new SAXNotSupportedException("Endpoints must be defined inside hyperedge");
/* 278 */       Map<String, String> endpoint_atts = getAttributeMap(atts);
/* 279 */       String node = (String)endpoint_atts.remove("node");
/* 280 */       if (node == null)
/* 281 */         throw new SAXNotSupportedException("Endpoint must include an 'id' attribute");
/* 282 */       V v = this.vertex_ids.getKey(node);
/* 283 */       if (v == null) {
/* 284 */         throw new SAXNotSupportedException("Endpoint refers to nonexistent node ID: " + node);
/*     */       }
/* 286 */       this.current_vertex = v;
/* 287 */       this.hyperedge_vertices.add(v);
/* 288 */       break;
/*     */     
/*     */     case EDGE: 
/*     */     case HYPEREDGE: 
/* 292 */       if (this.current_graph == null)
/* 293 */         throw new SAXNotSupportedException("Graph must be defined prior to elements");
/* 294 */       if ((this.current_edge != null) || (this.current_vertex != null)) {
/* 295 */         throw new SAXNotSupportedException("Nesting elements not supported");
/*     */       }
/* 297 */       createEdge(atts, state);
/* 298 */       break;
/*     */     
/*     */     case GRAPH: 
/* 301 */       if ((this.current_graph != null) && (this.graph_factory != null)) {
/* 302 */         throw new SAXNotSupportedException("Nesting graphs not currently supported");
/*     */       }
/*     */       
/* 305 */       if (this.graph_factory != null) {
/* 306 */         this.current_graph = ((Hypergraph)this.graph_factory.create());
/*     */       }
/*     */       
/* 309 */       clearData();
/*     */       
/*     */ 
/* 312 */       Map<String, String> graph_atts = getAttributeMap(atts);
/* 313 */       String default_direction = (String)graph_atts.remove("edgedefault");
/* 314 */       if (default_direction == null)
/* 315 */         throw new SAXNotSupportedException("All graphs must specify a default edge direction");
/* 316 */       if (default_direction.equals("directed")) {
/* 317 */         this.default_edgetype = EdgeType.DIRECTED;
/* 318 */       } else if (default_direction.equals("undirected")) {
/* 319 */         this.default_edgetype = EdgeType.UNDIRECTED;
/*     */       } else {
/* 321 */         throw new SAXNotSupportedException("Invalid or unrecognized default edge direction: " + default_direction);
/*     */       }
/*     */       
/* 324 */       addExtraData(graph_atts, this.graph_metadata, this.current_graph);
/*     */       
/* 326 */       break;
/*     */     
/*     */     case DATA: 
/* 329 */       if (this.current_states.contains(TagState.DATA))
/* 330 */         throw new SAXNotSupportedException("Nested data not supported");
/* 331 */       handleData(atts);
/* 332 */       break;
/*     */     
/*     */     case KEY: 
/* 335 */       createKey(atts);
/* 336 */       break;
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 343 */     this.current_states.addFirst(state);
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
/*     */   protected <T> void addExtraData(Map<String, String> atts, Map<String, GraphMLMetadata<T>> metadata_map, T current_elt)
/*     */   {
/* 359 */     for (Map.Entry<String, GraphMLMetadata<T>> entry : metadata_map.entrySet())
/*     */     {
/* 361 */       GraphMLMetadata<T> gmlm = (GraphMLMetadata)entry.getValue();
/* 362 */       if (gmlm.default_value != null)
/*     */       {
/* 364 */         SettableTransformer<T, String> st = (SettableTransformer)gmlm.transformer;
/*     */         
/* 366 */         st.set(current_elt, gmlm.default_value);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 371 */     for (Map.Entry<String, String> entry : atts.entrySet())
/*     */     {
/* 373 */       String key = (String)entry.getKey();
/* 374 */       GraphMLMetadata<T> key_data = (GraphMLMetadata)metadata_map.get(key);
/*     */       SettableTransformer<T, String> st;
/* 376 */       if (key_data != null)
/*     */       {
/*     */ 
/* 379 */         if (key_data.default_value != null)
/*     */           continue;
/* 381 */         SettableTransformer<T, String> st = (SettableTransformer)key_data.transformer;
/*     */       }
/*     */       else
/*     */       {
/* 385 */         st = new MapSettableTransformer(new HashMap());
/*     */         
/* 387 */         key_data = new GraphMLMetadata(null, null, st);
/* 388 */         metadata_map.put(key, key_data);
/*     */       }
/* 390 */       st.set(current_elt, entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void characters(char[] ch, int start, int length)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 398 */     String text = new String(ch, start, length);
/*     */     
/* 400 */     switch ((TagState)this.current_states.getFirst())
/*     */     {
/*     */     case DESC: 
/* 403 */       switch ((TagState)this.current_states.get(1))
/*     */       {
/*     */       case GRAPH: 
/* 406 */         this.graph_desc.put(this.current_graph, text);
/* 407 */         break;
/*     */       case VERTEX: 
/*     */       case ENDPOINT: 
/* 410 */         this.vertex_desc.put(this.current_vertex, text);
/* 411 */         break;
/*     */       case EDGE: 
/*     */       case HYPEREDGE: 
/* 414 */         this.edge_desc.put(this.current_edge, text);
/* 415 */         break;
/*     */       case DATA: 
/* 417 */         switch (this.key_type)
/*     */         {
/*     */         case GRAPH: 
/* 420 */           ((GraphMLMetadata)this.graph_metadata.get(this.current_key)).description = text;
/* 421 */           break;
/*     */         case VERTEX: 
/* 423 */           ((GraphMLMetadata)this.vertex_metadata.get(this.current_key)).description = text;
/* 424 */           break;
/*     */         case EDGE: 
/* 426 */           ((GraphMLMetadata)this.edge_metadata.get(this.current_key)).description = text;
/* 427 */           break;
/*     */         case ALL: 
/* 429 */           ((GraphMLMetadata)this.graph_metadata.get(this.current_key)).description = text;
/* 430 */           ((GraphMLMetadata)this.vertex_metadata.get(this.current_key)).description = text;
/* 431 */           ((GraphMLMetadata)this.edge_metadata.get(this.current_key)).description = text;
/* 432 */           break;
/*     */         default: 
/* 434 */           throw new SAXNotSupportedException("Invalid key type specified for default: " + this.key_type);
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 440 */       break;
/*     */     
/*     */ 
/*     */     case DATA: 
/* 444 */       switch ((TagState)this.current_states.get(1))
/*     */       {
/*     */       case GRAPH: 
/* 447 */         addDatum(this.graph_metadata, this.current_graph, text);
/* 448 */         break;
/*     */       case VERTEX: 
/*     */       case ENDPOINT: 
/* 451 */         addDatum(this.vertex_metadata, this.current_vertex, text);
/* 452 */         break;
/*     */       case EDGE: 
/*     */       case HYPEREDGE: 
/* 455 */         addDatum(this.edge_metadata, this.current_edge, text);
/*     */       }
/*     */       
/* 458 */       break;
/*     */     
/*     */ 
/*     */     case DEFAULT_KEY: 
/* 462 */       if (this.current_states.get(1) != TagState.KEY) {
/* 463 */         throw new SAXNotSupportedException("'default' only defined in context of 'key' tag: stack: " + this.current_states.toString());
/*     */       }
/*     */       
/* 466 */       switch (this.key_type)
/*     */       {
/*     */       case GRAPH: 
/* 469 */         ((GraphMLMetadata)this.graph_metadata.get(this.current_key)).default_value = text;
/* 470 */         break;
/*     */       case VERTEX: 
/* 472 */         ((GraphMLMetadata)this.vertex_metadata.get(this.current_key)).default_value = text;
/* 473 */         break;
/*     */       case EDGE: 
/* 475 */         ((GraphMLMetadata)this.edge_metadata.get(this.current_key)).default_value = text;
/* 476 */         break;
/*     */       case ALL: 
/* 478 */         ((GraphMLMetadata)this.graph_metadata.get(this.current_key)).default_value = text;
/* 479 */         ((GraphMLMetadata)this.vertex_metadata.get(this.current_key)).default_value = text;
/* 480 */         ((GraphMLMetadata)this.edge_metadata.get(this.current_key)).default_value = text;
/* 481 */         break;
/*     */       default: 
/* 483 */         throw new SAXNotSupportedException("Invalid key type specified for default: " + this.key_type);
/*     */       }
/*     */       
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   protected <T> void addDatum(Map<String, GraphMLMetadata<T>> metadata, T current_elt, String text)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 496 */     if (metadata.containsKey(this.current_key))
/*     */     {
/* 498 */       SettableTransformer<T, String> st = (SettableTransformer)((GraphMLMetadata)metadata.get(this.current_key)).transformer;
/*     */       
/* 500 */       st.set(current_elt, text);
/*     */     }
/*     */     else {
/* 503 */       throw new SAXNotSupportedException("key " + this.current_key + " not valid for element " + current_elt);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String name, String qName)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 510 */     String tag = qName.toLowerCase();
/* 511 */     TagState state = (TagState)this.tag_state.get(tag);
/* 512 */     if (state == null)
/* 513 */       state = TagState.OTHER;
/* 514 */     if (state == TagState.OTHER) {
/* 515 */       return;
/*     */     }
/* 517 */     if (state != this.current_states.getFirst()) {
/* 518 */       throw new SAXNotSupportedException("Unbalanced tags: opened " + (String)this.tag_state.getKey(this.current_states.getFirst()) + ", closed " + tag);
/*     */     }
/*     */     
/*     */ 
/* 522 */     switch (state)
/*     */     {
/*     */     case VERTEX: 
/*     */     case ENDPOINT: 
/* 526 */       this.current_vertex = null;
/* 527 */       break;
/*     */     
/*     */     case EDGE: 
/* 530 */       this.current_edge = null;
/* 531 */       break;
/*     */     
/*     */     case HYPEREDGE: 
/* 534 */       this.current_graph.addEdge(this.current_edge, this.hyperedge_vertices);
/* 535 */       this.hyperedge_vertices.clear();
/* 536 */       this.current_edge = null;
/* 537 */       break;
/*     */     
/*     */     case DATA: 
/* 540 */       this.key_type = KeyType.NONE;
/* 541 */       break;
/*     */     
/*     */     case GRAPH: 
/* 544 */       this.current_graph = null;
/* 545 */       break;
/*     */     
/*     */     case KEY: 
/* 548 */       this.current_key = null;
/* 549 */       break;
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/* 555 */     this.current_states.removeFirst();
/*     */   }
/*     */   
/*     */   protected Map<String, String> getAttributeMap(Attributes atts)
/*     */   {
/* 560 */     Map<String, String> att_map = new HashMap();
/* 561 */     for (int i = 0; i < atts.getLength(); i++) {
/* 562 */       att_map.put(atts.getQName(i), atts.getValue(i));
/*     */     }
/* 564 */     return att_map;
/*     */   }
/*     */   
/*     */   protected void handleData(Attributes atts) throws SAXNotSupportedException
/*     */   {
/* 569 */     switch ((TagState)this.current_states.getFirst())
/*     */     {
/*     */     case GRAPH: 
/*     */       break;
/*     */     case VERTEX: 
/*     */     case ENDPOINT: 
/*     */       break;
/*     */     case EDGE: 
/*     */       break;
/*     */     case HYPEREDGE: 
/*     */       break;
/*     */     default: 
/* 581 */       throw new SAXNotSupportedException("'data' tag only defined if immediately containing tag is 'graph', 'node', 'edge', or 'hyperedge'");
/*     */     }
/*     */     
/*     */     
/* 585 */     this.current_key = ((String)getAttributeMap(atts).get("key"));
/* 586 */     if (this.current_key == null)
/* 587 */       throw new SAXNotSupportedException("'data' tag requires a key specification");
/* 588 */     if (this.current_key.equals(""))
/* 589 */       throw new SAXNotSupportedException("'data' tag requires a non-empty key");
/* 590 */     if ((!getGraphMetadata().containsKey(this.current_key)) && (!getVertexMetadata().containsKey(this.current_key)) && (!getEdgeMetadata().containsKey(this.current_key)))
/*     */     {
/*     */ 
/*     */ 
/* 594 */       throw new SAXNotSupportedException("'data' tag's key specification must reference a defined key");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void createKey(Attributes atts)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 601 */     Map<String, String> key_atts = getAttributeMap(atts);
/* 602 */     String id = (String)key_atts.remove("id");
/* 603 */     String for_type = (String)key_atts.remove("for");
/*     */     
/* 605 */     if ((for_type == null) || (for_type.equals("")) || (for_type.equals("all")))
/*     */     {
/* 607 */       this.vertex_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */       
/*     */ 
/* 610 */       this.edge_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */       
/*     */ 
/* 613 */       this.graph_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */       
/*     */ 
/* 616 */       this.key_type = KeyType.ALL;
/*     */     }
/*     */     else
/*     */     {
/* 620 */       TagState type = (TagState)this.tag_state.get(for_type);
/* 621 */       switch (type)
/*     */       {
/*     */       case VERTEX: 
/* 624 */         this.vertex_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */         
/*     */ 
/* 627 */         this.key_type = KeyType.VERTEX;
/* 628 */         break;
/*     */       case EDGE: 
/*     */       case HYPEREDGE: 
/* 631 */         this.edge_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */         
/*     */ 
/* 634 */         this.key_type = KeyType.EDGE;
/* 635 */         break;
/*     */       case GRAPH: 
/* 637 */         this.graph_metadata.put(id, new GraphMLMetadata(null, null, new MapSettableTransformer(new HashMap())));
/*     */         
/*     */ 
/* 640 */         this.key_type = KeyType.GRAPH;
/* 641 */         break;
/*     */       case ENDPOINT: default: 
/* 643 */         throw new SAXNotSupportedException("Invalid metadata target type: " + for_type);
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 648 */     this.current_key = id;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void createVertex(Attributes atts)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 655 */     Map<String, String> vertex_atts = getAttributeMap(atts);
/* 656 */     String id = (String)vertex_atts.remove("id");
/* 657 */     if (id == null) {
/* 658 */       throw new SAXNotSupportedException("node attribute list missing 'id': " + atts.toString());
/*     */     }
/* 660 */     V v = this.vertex_ids.getKey(id);
/*     */     
/* 662 */     if (v == null)
/*     */     {
/* 664 */       if (this.vertex_factory != null) {
/* 665 */         v = this.vertex_factory.create();
/*     */       } else
/* 667 */         v = id;
/* 668 */       this.vertex_ids.put(v, id);
/* 669 */       this.current_graph.addVertex(v);
/*     */       
/*     */ 
/* 672 */       addExtraData(vertex_atts, this.vertex_metadata, v);
/*     */     }
/*     */     else {
/* 675 */       throw new SAXNotSupportedException("Node id \"" + id + " is a duplicate of an existing node ID");
/*     */     }
/*     */     
/* 678 */     this.current_vertex = v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void createEdge(Attributes atts, TagState state)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 686 */     Map<String, String> edge_atts = getAttributeMap(atts);
/*     */     
/* 688 */     String id = (String)edge_atts.remove("id");
/*     */     E e;
/* 690 */     if (this.edge_factory != null) {
/* 691 */       e = this.edge_factory.create();
/*     */     } else { E e;
/* 693 */       if (id != null) {
/* 694 */         e = id;
/*     */       } else
/* 696 */         throw new IllegalArgumentException("If no edge factory is supplied, edge id may not be null: " + edge_atts);
/*     */     }
/*     */     E e;
/* 699 */     if (id != null)
/*     */     {
/* 701 */       if (this.edge_ids.containsKey(e)) {
/* 702 */         throw new SAXNotSupportedException("Edge id \"" + id + "\" is a duplicate of an existing edge ID");
/*     */       }
/* 704 */       this.edge_ids.put(e, id);
/*     */     }
/*     */     
/* 707 */     if (state == TagState.EDGE) {
/* 708 */       assignEdgeSourceTarget(e, atts, edge_atts);
/*     */     }
/*     */     
/* 711 */     addExtraData(edge_atts, this.edge_metadata, e);
/*     */     
/* 713 */     this.current_edge = e;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void assignEdgeSourceTarget(E e, Attributes atts, Map<String, String> edge_atts)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 720 */     String source_id = (String)edge_atts.remove("source");
/* 721 */     if (source_id == null) {
/* 722 */       throw new SAXNotSupportedException("edge attribute list missing 'source': " + atts.toString());
/*     */     }
/* 724 */     V source = this.vertex_ids.getKey(source_id);
/* 725 */     if (source == null) {
/* 726 */       throw new SAXNotSupportedException("specified 'source' attribute \"" + source_id + "\" does not match any node ID");
/*     */     }
/*     */     
/* 729 */     String target_id = (String)edge_atts.remove("target");
/* 730 */     if (target_id == null) {
/* 731 */       throw new SAXNotSupportedException("edge attribute list missing 'target': " + atts.toString());
/*     */     }
/* 733 */     V target = this.vertex_ids.getKey(target_id);
/* 734 */     if (target == null) {
/* 735 */       throw new SAXNotSupportedException("specified 'target' attribute \"" + target_id + "\" does not match any node ID");
/*     */     }
/*     */     
/* 738 */     String directed = (String)edge_atts.remove("directed");
/*     */     EdgeType edge_type;
/* 740 */     if (directed == null) {
/* 741 */       edge_type = this.default_edgetype; } else { EdgeType edge_type;
/* 742 */       if (directed.equals("true")) {
/* 743 */         edge_type = EdgeType.DIRECTED; } else { EdgeType edge_type;
/* 744 */         if (directed.equals("false")) {
/* 745 */           edge_type = EdgeType.UNDIRECTED;
/*     */         } else
/* 747 */           throw new SAXNotSupportedException("Unrecognized edge direction specifier 'direction=\"" + directed + "\"': " + "source: " + source_id + ", target: " + target_id);
/*     */       } }
/*     */     EdgeType edge_type;
/* 750 */     if ((this.current_graph instanceof Graph)) {
/* 751 */       ((Graph)this.current_graph).addEdge(e, source, target, edge_type);
/*     */     }
/*     */     else {
/* 754 */       this.current_graph.addEdge(e, new Pair(source, target));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BidiMap<V, String> getVertexIDs()
/*     */   {
/* 762 */     return this.vertex_ids;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BidiMap<E, String> getEdgeIDs()
/*     */   {
/* 772 */     return this.edge_ids;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, GraphMLMetadata<G>> getGraphMetadata()
/*     */   {
/* 780 */     return this.graph_metadata;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, GraphMLMetadata<V>> getVertexMetadata()
/*     */   {
/* 788 */     return this.vertex_metadata;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, GraphMLMetadata<E>> getEdgeMetadata()
/*     */   {
/* 796 */     return this.edge_metadata;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<G, String> getGraphDescriptions()
/*     */   {
/* 804 */     return this.graph_desc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, String> getVertexDescriptions()
/*     */   {
/* 812 */     return this.vertex_desc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<E, String> getEdgeDescriptions()
/*     */   {
/* 820 */     return this.edge_desc;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/GraphMLReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */