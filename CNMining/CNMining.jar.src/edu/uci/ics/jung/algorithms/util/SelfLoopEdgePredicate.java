/*    */ package edu.uci.ics.jung.algorithms.util;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.Context;
/*    */ import edu.uci.ics.jung.graph.util.Pair;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelfLoopEdgePredicate<V, E>
/*    */   implements Predicate<Context<Graph<V, E>, E>>
/*    */ {
/*    */   public boolean evaluate(Context<Graph<V, E>, E> context)
/*    */   {
/* 20 */     Pair<V> endpoints = ((Graph)context.graph).getEndpoints(context.element);
/* 21 */     return endpoints.getFirst().equals(endpoints.getSecond());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/SelfLoopEdgePredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */