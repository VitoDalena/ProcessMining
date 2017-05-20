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
/*    */ public class WhitespaceTokenizer
/*    */   extends CharTokenizer
/*    */ {
/*    */   public WhitespaceTokenizer(Reader in)
/*    */   {
/* 27 */     super(in);
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean isTokenChar(char c)
/*    */   {
/* 33 */     return !Character.isWhitespace(c);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/WhitespaceTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */