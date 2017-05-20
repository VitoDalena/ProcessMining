/*    */ package org.apache.lucene.analysis.ru;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import org.apache.lucene.analysis.CharTokenizer;
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
/*    */ public class RussianLetterTokenizer
/*    */   extends CharTokenizer
/*    */ {
/*    */   private char[] charset;
/*    */   
/*    */   public RussianLetterTokenizer(Reader in, char[] charset)
/*    */   {
/* 39 */     super(in);
/* 40 */     this.charset = charset;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean isTokenChar(char c)
/*    */   {
/* 49 */     if (Character.isLetter(c))
/* 50 */       return true;
/* 51 */     for (int i = 0; i < this.charset.length; i++)
/*    */     {
/* 53 */       if (c == this.charset[i])
/* 54 */         return true;
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/ru/RussianLetterTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */