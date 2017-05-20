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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LowerCaseTokenizer
/*    */   extends LetterTokenizer
/*    */ {
/*    */   public LowerCaseTokenizer(Reader in)
/*    */   {
/* 34 */     super(in);
/*    */   }
/*    */   
/*    */ 
/*    */   protected char normalize(char c)
/*    */   {
/* 40 */     return Character.toLowerCase(c);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/LowerCaseTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */