/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
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
/*    */ public final class MapTransformer<I, O>
/*    */   implements Transformer<I, O>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 862391807045468939L;
/*    */   private final Map<I, O> iMap;
/*    */   
/*    */   public static <I, O> Transformer<I, O> getInstance(Map<I, O> map)
/*    */   {
/* 53 */     if (map == null) {
/* 54 */       return ConstantTransformer.NULL_INSTANCE;
/*    */     }
/* 56 */     return new MapTransformer(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private MapTransformer(Map<I, O> map)
/*    */   {
/* 67 */     this.iMap = map;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public O transform(I input)
/*    */   {
/* 77 */     return (O)this.iMap.get(input);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Map<I, O> getMap()
/*    */   {
/* 87 */     return this.iMap;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/MapTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */