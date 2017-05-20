/*    */ package edu.uci.ics.jung.algorithms.util;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections15.BidiMap;
/*    */ import org.apache.commons.collections15.bidimap.DualHashBidiMap;
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
/*    */ 
/*    */ 
/*    */ public class Indexer
/*    */ {
/*    */   public static <T> BidiMap<T, Integer> create(Collection<T> collection)
/*    */   {
/* 36 */     return create(collection, 0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> BidiMap<T, Integer> create(Collection<T> collection, int start)
/*    */   {
/* 49 */     BidiMap<T, Integer> map = new DualHashBidiMap();
/* 50 */     int i = start;
/* 51 */     for (T t : collection) {
/* 52 */       map.put(t, Integer.valueOf(i++));
/*    */     }
/* 54 */     return map;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/Indexer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */