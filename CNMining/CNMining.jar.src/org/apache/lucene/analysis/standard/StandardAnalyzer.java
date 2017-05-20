/*    */ package org.apache.lucene.analysis.standard;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.util.Set;
/*    */ import org.apache.lucene.analysis.Analyzer;
/*    */ import org.apache.lucene.analysis.LowerCaseFilter;
/*    */ import org.apache.lucene.analysis.StopAnalyzer;
/*    */ import org.apache.lucene.analysis.StopFilter;
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
/*    */ public class StandardAnalyzer
/*    */   extends Analyzer
/*    */ {
/*    */   private Set stopSet;
/* 34 */   public static final String[] STOP_WORDS = StopAnalyzer.ENGLISH_STOP_WORDS;
/*    */   
/*    */   public StandardAnalyzer()
/*    */   {
/* 38 */     this(STOP_WORDS);
/*    */   }
/*    */   
/*    */   public StandardAnalyzer(String[] stopWords)
/*    */   {
/* 43 */     this.stopSet = StopFilter.makeStopSet(stopWords);
/*    */   }
/*    */   
/*    */ 
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 49 */     TokenStream result = new StandardTokenizer(reader);
/* 50 */     result = new StandardFilter(result);
/* 51 */     result = new LowerCaseFilter(result);
/* 52 */     result = new StopFilter(result, this.stopSet);
/* 53 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/StandardAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */