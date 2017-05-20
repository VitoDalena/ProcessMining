/*     */ package org.apache.lucene.analysis.de;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.LowerCaseFilter;
/*     */ import org.apache.lucene.analysis.StopFilter;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.analysis.standard.StandardFilter;
/*     */ import org.apache.lucene.analysis.standard.StandardTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GermanAnalyzer
/*     */   extends Analyzer
/*     */ {
/*  47 */   private String[] GERMAN_STOP_WORDS = { "einer", "eine", "eines", "einem", "einen", "der", "die", "das", "dass", "daß", "du", "er", "sie", "es", "was", "wer", "wie", "wir", "und", "oder", "ohne", "mit", "am", "im", "in", "aus", "auf", "ist", "sein", "war", "wird", "ihr", "ihre", "ihres", "als", "für", "von", "mit", "dich", "dir", "mich", "mir", "mein", "sein", "kein", "durch", "wegen", "wird" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */   private Set stopSet = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  70 */   private Set exclusionSet = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */   public GermanAnalyzer()
/*     */   {
/*  76 */     this.stopSet = StopFilter.makeStopSet(this.GERMAN_STOP_WORDS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GermanAnalyzer(String[] stopwords)
/*     */   {
/*  83 */     this.stopSet = StopFilter.makeStopSet(stopwords);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GermanAnalyzer(Hashtable stopwords)
/*     */   {
/*  90 */     this.stopSet = new HashSet(stopwords.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */   public GermanAnalyzer(File stopwords)
/*     */     throws IOException
/*     */   {
/*  97 */     this.stopSet = WordlistLoader.getWordSet(stopwords);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setStemExclusionTable(String[] exclusionlist)
/*     */   {
/* 104 */     this.exclusionSet = StopFilter.makeStopSet(exclusionlist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setStemExclusionTable(Hashtable exclusionlist)
/*     */   {
/* 111 */     this.exclusionSet = new HashSet(exclusionlist.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */   public void setStemExclusionTable(File exclusionlist)
/*     */     throws IOException
/*     */   {
/* 118 */     this.exclusionSet = WordlistLoader.getWordSet(exclusionlist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TokenStream tokenStream(String fieldName, Reader reader)
/*     */   {
/* 128 */     TokenStream result = new StandardTokenizer(reader);
/* 129 */     result = new StandardFilter(result);
/* 130 */     result = new LowerCaseFilter(result);
/* 131 */     result = new StopFilter(result, this.stopSet);
/* 132 */     result = new GermanStemFilter(result, this.exclusionSet);
/* 133 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/de/GermanAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */