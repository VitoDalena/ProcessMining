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
/*    */ public class InterpolatingVertexSizeTransformer<V>
/*    */   implements Transformer<V, Integer>
/*    */ {
/*    */   protected double min;
/*    */   protected double max;
/*    */   protected Transformer<V, ? extends Number> values;
/*    */   protected int min_size;
/*    */   protected int size_diff;
/*    */   
/*    */   public InterpolatingVertexSizeTransformer(Transformer<V, ? extends Number> values, int min_size, int max_size)
/*    */   {
/* 35 */     if ((min_size < 0) || (max_size < 0))
/* 36 */       throw new IllegalArgumentException("sizes must be non-negative");
/* 37 */     if (min_size > max_size)
/* 38 */       throw new IllegalArgumentException("min_size must be <= max_size");
/* 39 */     this.min = 0.0D;
/* 40 */     this.max = 0.0D;
/* 41 */     this.values = values;
/* 42 */     setMinSize(min_size);
/* 43 */     setMaxSize(max_size);
/*    */   }
/*    */   
/*    */   public Integer transform(V v)
/*    */   {
/* 48 */     Number n = (Number)this.values.transform(v);
/* 49 */     double value = this.min;
/* 50 */     if (n != null)
/* 51 */       value = n.doubleValue();
/* 52 */     this.min = Math.min(this.min, value);
/* 53 */     this.max = Math.max(this.max, value);
/*    */     
/* 55 */     if (this.min == this.max) {
/* 56 */       return Integer.valueOf(this.min_size);
/*    */     }
/*    */     
/*    */ 
/* 60 */     return Integer.valueOf(this.min_size + (int)((value - this.min) / (this.max - this.min) * this.size_diff));
/*    */   }
/*    */   
/*    */   public void setMinSize(int min_size)
/*    */   {
/* 65 */     this.min_size = min_size;
/*    */   }
/*    */   
/*    */   public void setMaxSize(int max_size)
/*    */   {
/* 70 */     this.size_diff = (max_size - this.min_size);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/InterpolatingVertexSizeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */