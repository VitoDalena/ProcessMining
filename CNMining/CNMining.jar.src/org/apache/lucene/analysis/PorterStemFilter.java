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
/*    */ public final class PorterStemFilter
/*    */   extends TokenFilter
/*    */ {
/*    */   private PorterStemmer stemmer;
/*    */   
/*    */   public PorterStemFilter(TokenStream in)
/*    */   {
/* 43 */     super(in);
/* 44 */     this.stemmer = new PorterStemmer();
/*    */   }
/*    */   
/*    */   public final Token next() throws IOException
/*    */   {
/* 49 */     Token token = this.input.next();
/* 50 */     if (token == null) {
/* 51 */       return null;
/*    */     }
/* 53 */     String s = this.stemmer.stem(token.termText);
/* 54 */     if (s != token.termText)
/* 55 */       token.termText = s;
/* 56 */     return token;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/PorterStemFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */