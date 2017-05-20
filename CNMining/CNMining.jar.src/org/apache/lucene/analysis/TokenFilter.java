/*    */ package org.apache.lucene.analysis;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public abstract class TokenFilter
/*    */   extends TokenStream
/*    */ {
/*    */   protected TokenStream input;
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   protected TokenFilter() {}
/*    */   
/*    */   protected TokenFilter(TokenStream input)
/*    */   {
/* 36 */     this.input = input;
/*    */   }
/*    */   
/*    */   public void close() throws IOException
/*    */   {
/* 41 */     this.input.close();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/TokenFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */