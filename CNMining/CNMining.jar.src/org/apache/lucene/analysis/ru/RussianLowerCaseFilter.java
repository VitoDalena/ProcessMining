/*    */ package org.apache.lucene.analysis.ru;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.analysis.Token;
/*    */ import org.apache.lucene.analysis.TokenFilter;
/*    */ import org.apache.lucene.analysis.TokenStream;
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
/*    */ public final class RussianLowerCaseFilter
/*    */   extends TokenFilter
/*    */ {
/*    */   char[] charset;
/*    */   
/*    */   public RussianLowerCaseFilter(TokenStream in, char[] charset)
/*    */   {
/* 35 */     super(in);
/* 36 */     this.charset = charset;
/*    */   }
/*    */   
/*    */   public final Token next() throws IOException
/*    */   {
/* 41 */     Token t = this.input.next();
/*    */     
/* 43 */     if (t == null) {
/* 44 */       return null;
/*    */     }
/* 46 */     String txt = t.termText();
/*    */     
/* 48 */     char[] chArray = txt.toCharArray();
/* 49 */     for (int i = 0; i < chArray.length; i++)
/*    */     {
/* 51 */       chArray[i] = RussianCharsets.toLowerCase(chArray[i], this.charset);
/*    */     }
/*    */     
/* 54 */     String newTxt = new String(chArray);
/*    */     
/* 56 */     Token newToken = new Token(newTxt, t.startOffset(), t.endOffset());
/*    */     
/* 58 */     return newToken;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/ru/RussianLowerCaseFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */