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
/*    */ public final class SimpleAnalyzer
/*    */   extends Analyzer
/*    */ {
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 25 */     return new LowerCaseTokenizer(reader);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/SimpleAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */