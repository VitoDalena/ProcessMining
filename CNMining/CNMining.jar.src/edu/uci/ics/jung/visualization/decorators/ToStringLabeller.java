/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ 
/*    */ public class ToStringLabeller<V>
/*    */   implements Transformer<V, String>
/*    */ {
/*    */   public String transform(V v)
/*    */   {
/* 33 */     return v.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/ToStringLabeller.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */