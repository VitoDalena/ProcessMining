/*    */ package edu.uci.ics.jung.graph;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.util.EdgeType;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractTypedGraph<V, E>
/*    */   extends AbstractGraph<V, E>
/*    */ {
/*    */   protected final EdgeType edge_type;
/*    */   
/*    */   public AbstractTypedGraph(EdgeType edge_type)
/*    */   {
/* 36 */     this.edge_type = edge_type;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public EdgeType getDefaultEdgeType()
/*    */   {
/* 44 */     return this.edge_type;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public EdgeType getEdgeType(E e)
/*    */   {
/* 53 */     return hasEqualEdgeType(this.edge_type) ? this.edge_type : null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Collection<E> getEdges(EdgeType edge_type)
/*    */   {
/* 62 */     return hasEqualEdgeType(edge_type) ? getEdges() : Collections.emptySet();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getEdgeCount(EdgeType edge_type)
/*    */   {
/* 71 */     return hasEqualEdgeType(edge_type) ? getEdgeCount() : 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean hasEqualEdgeType(EdgeType edge_type)
/*    */   {
/* 81 */     return this.edge_type.equals(edge_type);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void validateEdgeType(EdgeType edge_type)
/*    */   {
/* 91 */     if (!hasEqualEdgeType(edge_type)) {
/* 92 */       throw new IllegalArgumentException("Edge type '" + edge_type + "' does not match the default edge type for this graph: '" + this.edge_type + "'");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/AbstractTypedGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */