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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RussianStemFilter
/*    */   extends TokenFilter
/*    */ {
/* 37 */   private Token token = null;
/* 38 */   private RussianStemmer stemmer = null;
/*    */   
/*    */   public RussianStemFilter(TokenStream in, char[] charset)
/*    */   {
/* 42 */     super(in);
/* 43 */     this.stemmer = new RussianStemmer(charset);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public final Token next()
/*    */     throws IOException
/*    */   {
/* 51 */     if ((this.token = this.input.next()) == null)
/*    */     {
/* 53 */       return null;
/*    */     }
/*    */     
/*    */ 
/* 57 */     String s = this.stemmer.stem(this.token.termText());
/* 58 */     if (!s.equals(this.token.termText()))
/*    */     {
/* 60 */       return new Token(s, this.token.startOffset(), this.token.endOffset(), this.token.type());
/*    */     }
/*    */     
/* 63 */     return this.token;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setStemmer(RussianStemmer stemmer)
/*    */   {
/* 72 */     if (stemmer != null)
/*    */     {
/* 74 */       this.stemmer = stemmer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/ru/RussianStemFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */