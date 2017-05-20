/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.CollectionUtils;
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
/*     */ public class OrderedKAryTree<V, E>
/*     */   extends AbstractTypedGraph<V, E>
/*     */   implements Tree<V, E>
/*     */ {
/*     */   protected Map<E, Pair<V>> edge_vpairs;
/*     */   protected Map<V, OrderedKAryTree<V, E>.VertexData> vertex_data;
/*     */   protected int height;
/*     */   protected V root;
/*     */   protected int order;
/*     */   
/*     */   public static <V, E> Factory<DirectedGraph<V, E>> getFactory(int order)
/*     */   {
/*  48 */     new Factory() {
/*     */       public DirectedGraph<V, E> create() {
/*  50 */         return new OrderedKAryTree(this.val$order);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public OrderedKAryTree(int order)
/*     */   {
/*  60 */     super(EdgeType.DIRECTED);
/*  61 */     this.order = order;
/*  62 */     this.height = -1;
/*  63 */     this.edge_vpairs = new HashMap();
/*  64 */     this.vertex_data = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getChildCount(V vertex)
/*     */   {
/*  71 */     if (!containsVertex(vertex))
/*  72 */       return 0;
/*  73 */     E[] edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/*  74 */     if (edges == null)
/*  75 */       return 0;
/*  76 */     int count = 0;
/*  77 */     for (int i = 0; i < edges.length; i++) {
/*  78 */       count += (edges[i] == null ? 0 : 1);
/*     */     }
/*  80 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getChildEdge(V vertex, int index)
/*     */   {
/*  91 */     if (!containsVertex(vertex))
/*  92 */       return null;
/*  93 */     E[] edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/*  94 */     if (edges == null)
/*  95 */       return null;
/*  96 */     return edges[index];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> getChildEdges(V vertex)
/*     */   {
/* 104 */     if (!containsVertex(vertex))
/* 105 */       return null;
/* 106 */     E[] edge_array = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/* 107 */     if (edge_array == null)
/* 108 */       return Collections.emptySet();
/* 109 */     Collection<E> edges = new ArrayList(this.order);
/* 110 */     for (int i = 0; i < edge_array.length; i++)
/* 111 */       if (edge_array[i] != null) edges.add(edge_array[i]);
/* 112 */     return CollectionUtils.unmodifiableCollection(edges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getChildren(V vertex)
/*     */   {
/* 120 */     if (!containsVertex(vertex))
/* 121 */       return null;
/* 122 */     E[] edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/* 123 */     if (edges == null)
/* 124 */       return Collections.emptySet();
/* 125 */     Collection<V> children = new ArrayList(this.order);
/* 126 */     for (int i = 0; i < edges.length; i++)
/* 127 */       if (edges[i] != null) children.add(getOpposite(vertex, edges[i]));
/* 128 */     return CollectionUtils.unmodifiableCollection(children);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDepth(V vertex)
/*     */   {
/* 138 */     if (!containsVertex(vertex))
/* 139 */       return -1;
/* 140 */     return ((VertexData)this.vertex_data.get(vertex)).depth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 149 */     return this.height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getParent(V vertex)
/*     */   {
/* 157 */     if (!containsVertex(vertex))
/* 158 */       return null;
/* 159 */     if (vertex.equals(this.root))
/* 160 */       return null;
/* 161 */     return (V)((Pair)this.edge_vpairs.get(((VertexData)this.vertex_data.get(vertex)).parent_edge)).getFirst();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getParentEdge(V vertex)
/*     */   {
/* 169 */     if (!containsVertex(vertex))
/* 170 */       return null;
/* 171 */     return (E)((VertexData)this.vertex_data.get(vertex)).parent_edge;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getRoot()
/*     */   {
/* 179 */     return (V)this.root;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<Tree<V, E>> getTrees()
/*     */   {
/* 187 */     Collection<Tree<V, E>> forest = new ArrayList(1);
/* 188 */     forest.add(this);
/* 189 */     return forest;
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
/*     */   public boolean addEdge(E e, V parent, V child, int index)
/*     */   {
/* 205 */     if ((e == null) || (child == null) || (parent == null))
/* 206 */       throw new IllegalArgumentException("Inputs may not be null");
/* 207 */     if (!containsVertex(parent)) {
/* 208 */       throw new IllegalArgumentException("Tree must already include parent: " + parent);
/*     */     }
/* 210 */     if (containsVertex(child)) {
/* 211 */       throw new IllegalArgumentException("Tree must not already include child: " + child);
/*     */     }
/* 213 */     if (parent.equals(child))
/* 214 */       throw new IllegalArgumentException("Input vertices must be distinct");
/* 215 */     if (index >= this.order) {
/* 216 */       throw new IllegalArgumentException("'index' must be < the order of this tree");
/*     */     }
/*     */     
/* 219 */     Pair<V> endpoints = new Pair(parent, child);
/* 220 */     if (containsEdge(e)) {
/* 221 */       if (!endpoints.equals(this.edge_vpairs.get(e))) {
/* 222 */         throw new IllegalArgumentException("Tree already includes edge" + e + " with different endpoints " + this.edge_vpairs.get(e));
/*     */       }
/*     */       
/* 225 */       return false;
/*     */     }
/* 227 */     OrderedKAryTree<V, E>.VertexData parent_data = (VertexData)this.vertex_data.get(parent);
/* 228 */     E[] outedges = parent_data.child_edges;
/*     */     
/* 230 */     if (outedges == null)
/*     */     {
/* 232 */       Class<?> type = e.getClass().getComponentType();
/* 233 */       outedges = (Object[])Array.newInstance(type, this.order);
/* 234 */       parent_data.child_edges = outedges;
/*     */     }
/*     */     
/* 237 */     boolean edge_placed = false;
/* 238 */     if (index >= 0) {
/* 239 */       if (outedges[index] != null) {
/* 240 */         throw new IllegalArgumentException("Parent " + parent + " already has a child at index " + index + " in this tree");
/*     */       }
/*     */       
/* 243 */       outedges[index] = e; }
/* 244 */     for (int i = 0; i < outedges.length; i++)
/*     */     {
/* 246 */       if (outedges[i] == null)
/*     */       {
/* 248 */         outedges[i] = e;
/* 249 */         edge_placed = true;
/* 250 */         break;
/*     */       }
/*     */     }
/* 253 */     if (!edge_placed) {
/* 254 */       throw new IllegalArgumentException("Parent " + parent + " already" + " has " + this.order + " children in this tree");
/*     */     }
/*     */     
/*     */ 
/* 258 */     OrderedKAryTree<V, E>.VertexData child_data = new VertexData(e, parent_data.depth + 1);
/* 259 */     this.vertex_data.put(child, child_data);
/*     */     
/* 261 */     this.height = (child_data.depth > this.height ? child_data.depth : this.height);
/* 262 */     this.edge_vpairs.put(e, endpoints);
/*     */     
/* 264 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V parent, V child)
/*     */   {
/* 273 */     return addEdge(e, parent, child, -1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V v1, V v2, EdgeType edge_type)
/*     */   {
/* 283 */     validateEdgeType(edge_type);
/* 284 */     return addEdge(e, v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getDest(E directed_edge)
/*     */   {
/* 292 */     if (!containsEdge(directed_edge))
/* 293 */       return null;
/* 294 */     return (V)((Pair)this.edge_vpairs.get(directed_edge)).getSecond();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 302 */     if (!containsEdge(edge))
/* 303 */       return null;
/* 304 */     return (Pair)this.edge_vpairs.get(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 312 */     if (!containsVertex(vertex))
/* 313 */       return null;
/* 314 */     if (vertex.equals(this.root)) {
/* 315 */       return Collections.emptySet();
/*     */     }
/* 317 */     return Collections.singleton(getParentEdge(vertex));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getOpposite(V vertex, E edge)
/*     */   {
/* 326 */     if ((!containsVertex(vertex)) || (!containsEdge(edge)))
/* 327 */       return null;
/* 328 */     Pair<V> endpoints = (Pair)this.edge_vpairs.get(edge);
/* 329 */     V v1 = endpoints.getFirst();
/* 330 */     V v2 = endpoints.getSecond();
/* 331 */     return v1.equals(vertex) ? v2 : v1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/* 339 */     return getChildEdges(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPredecessorCount(V vertex)
/*     */   {
/* 350 */     if (!containsVertex(vertex))
/* 351 */       return -1;
/* 352 */     return vertex.equals(this.root) ? 0 : 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 360 */     if (!containsVertex(vertex))
/* 361 */       return null;
/* 362 */     if (vertex.equals(this.root))
/* 363 */       return Collections.emptySet();
/* 364 */     return Collections.singleton(getParent(vertex));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getSource(E directed_edge)
/*     */   {
/* 372 */     if (!containsEdge(directed_edge))
/* 373 */       return null;
/* 374 */     return (V)((Pair)this.edge_vpairs.get(directed_edge)).getSecond();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSuccessorCount(V vertex)
/*     */   {
/* 383 */     return getChildCount(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 391 */     return getChildren(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int inDegree(V vertex)
/*     */   {
/* 400 */     if (!containsVertex(vertex))
/* 401 */       return 0;
/* 402 */     if (vertex.equals(this.root))
/* 403 */       return 0;
/* 404 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDest(V vertex, E edge)
/*     */   {
/* 412 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 413 */       return false;
/* 414 */     return ((Pair)this.edge_vpairs.get(edge)).getSecond().equals(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLeaf(V vertex)
/*     */   {
/* 425 */     if (!containsVertex(vertex))
/* 426 */       return false;
/* 427 */     return outDegree(vertex) == 0;
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
/*     */   public boolean isPredecessor(V v1, V v2)
/*     */   {
/* 440 */     if (!containsVertex(v2))
/* 441 */       return false;
/* 442 */     return getParent(v2).equals(v1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRoot(V vertex)
/*     */   {
/* 453 */     if (this.root == null)
/* 454 */       return false;
/* 455 */     return this.root.equals(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSource(V vertex, E edge)
/*     */   {
/* 463 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 464 */       return false;
/* 465 */     return ((Pair)this.edge_vpairs.get(edge)).getFirst().equals(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSuccessor(V v1, V v2)
/*     */   {
/* 474 */     if (!containsVertex(v2))
/* 475 */       return false;
/* 476 */     if (containsVertex(v1))
/* 477 */       return getParent(v1).equals(v2);
/* 478 */     return (isLeaf(v2)) && (v1 == null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int outDegree(V vertex)
/*     */   {
/* 487 */     if (!containsVertex(vertex))
/* 488 */       return 0;
/* 489 */     E[] out_edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/* 490 */     if (out_edges == null)
/* 491 */       return 0;
/* 492 */     int degree = 0;
/* 493 */     for (int i = 0; i < out_edges.length; i++)
/* 494 */       degree += (out_edges[i] == null ? 0 : 1);
/* 495 */     return degree;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices, EdgeType edge_type)
/*     */   {
/* 505 */     if ((edge == null) || (vertices == null))
/* 506 */       throw new IllegalArgumentException("inputs may not be null");
/* 507 */     if (vertices.size() != 2) {
/* 508 */       throw new IllegalArgumentException("'vertices' must contain exactly 2 distinct vertices");
/*     */     }
/* 510 */     validateEdgeType(edge_type);
/*     */     Pair<V> endpoints;
/* 512 */     Pair<V> endpoints; if ((vertices instanceof Pair)) {
/* 513 */       endpoints = (Pair)vertices;
/*     */     } else
/* 515 */       endpoints = new Pair(vertices);
/* 516 */     V v1 = endpoints.getFirst();
/* 517 */     V v2 = endpoints.getSecond();
/* 518 */     if (v1.equals(v2))
/* 519 */       throw new IllegalArgumentException("Input vertices must be distinct");
/* 520 */     return addEdge(edge, v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 528 */     if (this.root == null)
/*     */     {
/* 530 */       this.root = vertex;
/* 531 */       this.vertex_data.put(vertex, new VertexData(null, 0));
/* 532 */       this.height = 0;
/* 533 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 537 */     throw new UnsupportedOperationException("Unless you are setting the root, use addEdge() or addChild()");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isIncident(V vertex, E edge)
/*     */   {
/* 548 */     if ((!containsVertex(vertex)) || (!containsEdge(edge)))
/* 549 */       return false;
/* 550 */     return ((Pair)this.edge_vpairs.get(edge)).contains(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNeighbor(V v1, V v2)
/*     */   {
/* 559 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 560 */       return false;
/* 561 */     return getNeighbors(v1).contains(v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsEdge(E edge)
/*     */   {
/* 569 */     return this.edge_vpairs.containsKey(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsVertex(V vertex)
/*     */   {
/* 577 */     return this.vertex_data.containsKey(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 587 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 588 */       return null;
/* 589 */     OrderedKAryTree<V, E>.VertexData v1_data = (VertexData)this.vertex_data.get(v1);
/* 590 */     if (((Pair)this.edge_vpairs.get(v1_data.parent_edge)).getFirst().equals(v2))
/* 591 */       return (E)v1_data.parent_edge;
/* 592 */     E[] edges = v1_data.child_edges;
/* 593 */     if (edges == null)
/* 594 */       return null;
/* 595 */     for (int i = 0; i < edges.length; i++)
/* 596 */       if (((Pair)this.edge_vpairs.get(edges[i])).getSecond().equals(v2))
/* 597 */         return edges[i];
/* 598 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/* 607 */     E edge = findEdge(v1, v2);
/* 608 */     if (edge == null) {
/* 609 */       return Collections.emptySet();
/*     */     }
/* 611 */     return Collections.singleton(edge);
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
/*     */   public V getChild(V vertex, int index)
/*     */   {
/* 625 */     if (!containsVertex(vertex))
/* 626 */       return null;
/* 627 */     E[] edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/* 628 */     if (edges == null)
/* 629 */       return null;
/* 630 */     if (edges[index] == null)
/* 631 */       return null;
/* 632 */     return (V)((Pair)this.edge_vpairs.get(edges[index])).getSecond();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getEdgeCount()
/*     */   {
/* 640 */     return this.edge_vpairs.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> getEdges()
/*     */   {
/* 648 */     return CollectionUtils.unmodifiableCollection(this.edge_vpairs.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIncidentCount(E edge)
/*     */   {
/* 657 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 665 */     if (!containsVertex(vertex))
/* 666 */       return null;
/* 667 */     ArrayList<E> edges = new ArrayList(this.order + 1);
/* 668 */     OrderedKAryTree<V, E>.VertexData v_data = (VertexData)this.vertex_data.get(vertex);
/* 669 */     if (v_data.parent_edge != null)
/* 670 */       edges.add(v_data.parent_edge);
/* 671 */     if (v_data.child_edges != null)
/*     */     {
/* 673 */       for (int i = 0; i < v_data.child_edges.length; i++)
/* 674 */         if (v_data.child_edges[i] != null)
/* 675 */           edges.add(v_data.child_edges[i]);
/*     */     }
/* 677 */     if (edges.isEmpty())
/* 678 */       return Collections.emptySet();
/* 679 */     return Collections.unmodifiableCollection(edges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getIncidentVertices(E edge)
/*     */   {
/* 688 */     return (Collection)this.edge_vpairs.get(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNeighborCount(V vertex)
/*     */   {
/* 697 */     if (!containsVertex(vertex))
/* 698 */       return 0;
/* 699 */     return (vertex.equals(this.root) ? 0 : 1) + getChildCount(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 707 */     if (!containsVertex(vertex))
/* 708 */       return null;
/* 709 */     ArrayList<V> vertices = new ArrayList(this.order + 1);
/* 710 */     OrderedKAryTree<V, E>.VertexData v_data = (VertexData)this.vertex_data.get(vertex);
/* 711 */     if (v_data.parent_edge != null)
/* 712 */       vertices.add(((Pair)this.edge_vpairs.get(v_data.parent_edge)).getFirst());
/* 713 */     if (v_data.child_edges != null)
/*     */     {
/* 715 */       for (int i = 0; i < v_data.child_edges.length; i++) {
/* 716 */         if (v_data.child_edges[i] != null)
/* 717 */           vertices.add(((Pair)this.edge_vpairs.get(v_data.child_edges[i])).getSecond());
/*     */       }
/*     */     }
/* 720 */     if (vertices.isEmpty())
/* 721 */       return Collections.emptySet();
/* 722 */     return Collections.unmodifiableCollection(vertices);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getVertexCount()
/*     */   {
/* 730 */     return this.vertex_data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getVertices()
/*     */   {
/* 738 */     return CollectionUtils.unmodifiableCollection(this.vertex_data.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 746 */     if (!containsEdge(edge)) {
/* 747 */       return false;
/*     */     }
/* 749 */     removeVertex(((Pair)this.edge_vpairs.get(edge)).getSecond());
/* 750 */     this.edge_vpairs.remove(edge);
/*     */     
/* 752 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 760 */     if (!containsVertex(vertex)) {
/* 761 */       return false;
/*     */     }
/*     */     
/* 764 */     for (V v : getChildren(vertex)) {
/* 765 */       removeVertex(v);
/*     */     }
/* 767 */     E edge = getParentEdge(vertex);
/* 768 */     this.edge_vpairs.remove(edge);
/* 769 */     E[] edges = ((VertexData)this.vertex_data.get(vertex)).child_edges;
/* 770 */     if (edges != null)
/* 771 */       for (int i = 0; i < edges.length; i++)
/* 772 */         this.edge_vpairs.remove(edges[i]);
/* 773 */     this.vertex_data.remove(vertex);
/*     */     
/* 775 */     return true;
/*     */   }
/*     */   
/*     */   protected class VertexData
/*     */   {
/*     */     E[] child_edges;
/*     */     E parent_edge;
/*     */     int depth;
/*     */     
/*     */     protected VertexData(int parent_edge)
/*     */     {
/* 786 */       this.parent_edge = parent_edge;
/* 787 */       this.depth = depth;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edgeType)
/*     */   {
/* 794 */     if ((edge == null) || (endpoints == null))
/* 795 */       throw new IllegalArgumentException("inputs must not be null");
/* 796 */     return addEdge(edge, endpoints.getFirst(), endpoints.getSecond(), edgeType);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/OrderedKAryTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */