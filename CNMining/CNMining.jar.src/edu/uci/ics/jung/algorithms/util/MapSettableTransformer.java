/*    */ package edu.uci.ics.jung.algorithms.util;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class MapSettableTransformer<I, O>
/*    */   implements SettableTransformer<I, O>
/*    */ {
/*    */   protected Map<I, O> map;
/*    */   
/*    */   public MapSettableTransformer(Map<I, O> m)
/*    */   {
/* 32 */     this.map = m;
/*    */   }
/*    */   
/*    */   public O transform(I input)
/*    */   {
/* 37 */     return (O)this.map.get(input);
/*    */   }
/*    */   
/*    */   public void set(I input, O output)
/*    */   {
/* 42 */     this.map.put(input, output);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/MapSettableTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */