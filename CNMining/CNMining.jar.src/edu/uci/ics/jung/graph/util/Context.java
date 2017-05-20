/*    */ package edu.uci.ics.jung.graph.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Context<G, E>
/*    */ {
/* 10 */   private static Context instance = new Context();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public G graph;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public E element;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <G, E> Context<G, E> getInstance(G graph, E element)
/*    */   {
/* 29 */     instance.graph = graph;
/* 30 */     instance.element = element;
/* 31 */     return instance;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 36 */     return this.graph.hashCode() ^ this.element.hashCode();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 42 */     if (!(o instanceof Context))
/* 43 */       return false;
/* 44 */     Context context = (Context)o;
/* 45 */     return (context.graph.equals(this.graph)) && (context.element.equals(this.element));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/Context.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */