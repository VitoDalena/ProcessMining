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
/*    */ public final class WhitespaceAnalyzer
/*    */   extends Analyzer
/*    */ {
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 25 */     return new WhitespaceTokenizer(reader);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/WhitespaceAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */