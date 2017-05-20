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
/*    */ public final class LowerCaseFilter
/*    */   extends TokenFilter
/*    */ {
/*    */   public LowerCaseFilter(TokenStream in)
/*    */   {
/* 28 */     super(in);
/*    */   }
/*    */   
/*    */   public final Token next() throws IOException {
/* 32 */     Token t = this.input.next();
/*    */     
/* 34 */     if (t == null) {
/* 35 */       return null;
/*    */     }
/* 37 */     t.termText = t.termText.toLowerCase();
/*    */     
/* 39 */     return t;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/LowerCaseFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */