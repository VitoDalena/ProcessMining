/*    */ package org.apache.lucene.analysis;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ public abstract class Tokenizer
/*    */   extends TokenStream
/*    */ {
/*    */   protected Reader input;
/*    */   
/*    */   protected Tokenizer() {}
/*    */   
/*    */   protected Tokenizer(Reader input)
/*    */   {
/* 36 */     this.input = input;
/*    */   }
/*    */   
/*    */   public void close() throws IOException
/*    */   {
/* 41 */     this.input.close();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/Tokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */