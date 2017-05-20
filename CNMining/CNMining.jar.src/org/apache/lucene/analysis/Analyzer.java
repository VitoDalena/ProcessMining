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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Analyzer
/*    */ {
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 40 */     return tokenStream(reader);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public TokenStream tokenStream(Reader reader)
/*    */   {
/* 50 */     return tokenStream(null, reader);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/Analyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */