/*    */ package edu.uci.ics.jung.io;
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
/*    */ public class GraphMLMetadata<T>
/*    */ {
/*    */   public String description;
/*    */   public String default_value;
/*    */   public Transformer<T, String> transformer;
/*    */   
/*    */   public GraphMLMetadata(String description, String default_value, Transformer<T, String> transformer)
/*    */   {
/* 46 */     this.description = description;
/* 47 */     this.transformer = transformer;
/* 48 */     this.default_value = default_value;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/GraphMLMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */