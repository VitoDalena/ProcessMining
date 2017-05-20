/*    */ package org.apache.lucene.analysis;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.util.Set;
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
/*    */ public final class StopAnalyzer
/*    */   extends Analyzer
/*    */ {
/*    */   private Set stopWords;
/* 29 */   public static final String[] ENGLISH_STOP_WORDS = { "a", "an", "and", "are", "as", "at", "be", "but", "by", "for", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "s", "such", "t", "that", "the", "their", "then", "there", "these", "they", "this", "to", "was", "will", "with" };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public StopAnalyzer()
/*    */   {
/* 39 */     this.stopWords = StopFilter.makeStopSet(ENGLISH_STOP_WORDS);
/*    */   }
/*    */   
/*    */   public StopAnalyzer(String[] stopWords)
/*    */   {
/* 44 */     this.stopWords = StopFilter.makeStopSet(stopWords);
/*    */   }
/*    */   
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 49 */     return new StopFilter(new LowerCaseTokenizer(reader), this.stopWords);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/StopAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */