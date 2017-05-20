/*    */ package org.apache.lucene.analysis;
/*    */ 
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
/*    */ public class LetterTokenizer
/*    */   extends CharTokenizer
/*    */ {
/*    */   public LetterTokenizer(Reader in)
/*    */   {
/* 31 */     super(in);
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean isTokenChar(char c)
/*    */   {
/* 37 */     return Character.isLetter(c);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/LetterTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */