/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import java.text.NumberFormat;
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
/*    */ public class NumberFormattingTransformer<T>
/*    */   implements Transformer<T, String>
/*    */ {
/*    */   private Transformer<T, ? extends Number> values;
/* 27 */   private NumberFormat formatter = NumberFormat.getInstance();
/*    */   
/*    */   public NumberFormattingTransformer(Transformer<T, ? extends Number> values)
/*    */   {
/* 31 */     this.values = values;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String transform(T input)
/*    */   {
/* 39 */     return this.formatter.format(this.values.transform(input));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/NumberFormattingTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */