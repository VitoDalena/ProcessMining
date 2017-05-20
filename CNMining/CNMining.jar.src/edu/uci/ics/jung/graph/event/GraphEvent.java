/*     */ package edu.uci.ics.jung.graph.event;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GraphEvent<V, E>
/*     */ {
/*     */   protected Graph<V, E> source;
/*     */   protected Type type;
/*     */   
/*     */   public GraphEvent(Graph<V, E> source, Type type)
/*     */   {
/*  23 */     this.source = source;
/*  24 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum Type
/*     */   {
/*  31 */     VERTEX_ADDED, 
/*  32 */     VERTEX_REMOVED, 
/*  33 */     EDGE_ADDED, 
/*  34 */     EDGE_REMOVED;
/*     */     
/*     */ 
/*     */     private Type() {}
/*     */   }
/*     */   
/*     */   public static class Vertex<V, E>
/*     */     extends GraphEvent<V, E>
/*     */   {
/*     */     protected V vertex;
/*     */     
/*     */     public Vertex(Graph<V, E> source, GraphEvent.Type type, V vertex)
/*     */     {
/*  47 */       super(type);
/*  48 */       this.vertex = vertex;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public V getVertex()
/*     */     {
/*  55 */       return (V)this.vertex;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  60 */       return "GraphEvent type:" + this.type + " for " + this.vertex;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Edge<V, E>
/*     */     extends GraphEvent<V, E>
/*     */   {
/*     */     protected E edge;
/*     */     
/*     */ 
/*     */ 
/*     */     public Edge(Graph<V, E> source, GraphEvent.Type type, E edge)
/*     */     {
/*  75 */       super(type);
/*  76 */       this.edge = edge;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public E getEdge()
/*     */     {
/*  83 */       return (E)this.edge;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  88 */       return "GraphEvent type:" + this.type + " for " + this.edge;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> getSource()
/*     */   {
/*  97 */     return this.source;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Type getType()
/*     */   {
/* 104 */     return this.type;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/event/GraphEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */