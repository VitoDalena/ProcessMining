/*    */ package org.apache.lucene.analysis;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class PerFieldAnalyzerWrapper
/*    */   extends Analyzer
/*    */ {
/*    */   private Analyzer defaultAnalyzer;
/* 31 */   private Map analyzerMap = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PerFieldAnalyzerWrapper(Analyzer defaultAnalyzer)
/*    */   {
/* 41 */     this.defaultAnalyzer = defaultAnalyzer;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAnalyzer(String fieldName, Analyzer analyzer)
/*    */   {
/* 51 */     this.analyzerMap.put(fieldName, analyzer);
/*    */   }
/*    */   
/*    */   public TokenStream tokenStream(String fieldName, Reader reader) {
/* 55 */     Analyzer analyzer = (Analyzer)this.analyzerMap.get(fieldName);
/* 56 */     if (analyzer == null) {
/* 57 */       analyzer = this.defaultAnalyzer;
/*    */     }
/*    */     
/* 60 */     return analyzer.tokenStream(fieldName, reader);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/PerFieldAnalyzerWrapper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */