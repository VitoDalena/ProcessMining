/*     */ package org.apache.lucene.analysis.ru;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.StopFilter;
/*     */ import org.apache.lucene.analysis.TokenStream;
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
/*     */ public final class RussianAnalyzer
/*     */   extends Analyzer
/*     */ {
/*  39 */   private static char A = '\000';
/*  40 */   private static char B = '\001';
/*  41 */   private static char V = '\002';
/*  42 */   private static char G = '\003';
/*  43 */   private static char D = '\004';
/*  44 */   private static char E = '\005';
/*  45 */   private static char ZH = '\006';
/*  46 */   private static char Z = '\007';
/*  47 */   private static char I = '\b';
/*  48 */   private static char I_ = '\t';
/*  49 */   private static char K = '\n';
/*  50 */   private static char L = '\013';
/*  51 */   private static char M = '\f';
/*  52 */   private static char N = '\r';
/*  53 */   private static char O = '\016';
/*  54 */   private static char P = '\017';
/*  55 */   private static char R = '\020';
/*  56 */   private static char S = '\021';
/*  57 */   private static char T = '\022';
/*  58 */   private static char U = '\023';
/*  59 */   private static char F = '\024';
/*  60 */   private static char X = '\025';
/*  61 */   private static char TS = '\026';
/*  62 */   private static char CH = '\027';
/*  63 */   private static char SH = '\030';
/*  64 */   private static char SHCH = '\031';
/*  65 */   private static char HARD = '\032';
/*  66 */   private static char Y = '\033';
/*  67 */   private static char SOFT = '\034';
/*  68 */   private static char AE = '\035';
/*  69 */   private static char IU = '\036';
/*  70 */   private static char IA = '\037';
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  75 */   private static char[][] RUSSIAN_STOP_WORDS = { { A }, { B, E, Z }, { B, O, L, E, E }, { B, Y }, { B, Y, L }, { B, Y, L, A }, { B, Y, L, I }, { B, Y, L, O }, { B, Y, T, SOFT }, { V }, { V, A, M }, { V, A, S }, { V, E, S, SOFT }, { V, O }, { V, O, T }, { V, S, E }, { V, S, E, G, O }, { V, S, E, X }, { V, Y }, { G, D, E }, { D, A }, { D, A, ZH, E }, { D, L, IA }, { D, O }, { E, G, O }, { E, E }, { E, I_ }, { E, IU }, { E, S, L, I }, { E, S, T, SOFT }, { E, SHCH, E }, { ZH, E }, { Z, A }, { Z, D, E, S, SOFT }, { I }, { I, Z }, { I, L, I }, { I, M }, { I, X }, { K }, { K, A, K }, { K, O }, { K, O, G, D, A }, { K, T, O }, { L, I }, { L, I, B, O }, { M, N, E }, { M, O, ZH, E, T }, { M, Y }, { N, A }, { N, A, D, O }, { N, A, SH }, { N, E }, { N, E, G, O }, { N, E, E }, { N, E, T }, { N, I }, { N, I, X }, { N, O }, { N, U }, { O }, { O, B }, { O, D, N, A, K, O }, { O, N }, { O, N, A }, { O, N, I }, { O, N, O }, { O, T }, { O, CH, E, N, SOFT }, { P, O }, { P, O, D }, { P, R, I }, { S }, { S, O }, { T, A, K }, { T, A, K, ZH, E }, { T, A, K, O, I_ }, { T, A, M }, { T, E }, { T, E, M }, { T, O }, { T, O, G, O }, { T, O, ZH, E }, { T, O, I_ }, { T, O, L, SOFT, K, O }, { T, O, M }, { T, Y }, { U }, { U, ZH, E }, { X, O, T, IA }, { CH, E, G, O }, { CH, E, I_ }, { CH, E, M }, { CH, T, O }, { CH, T, O, B, Y }, { CH, SOFT, E }, { CH, SOFT, IA }, { AE, T, A }, { AE, T, I }, { AE, T, O }, { IA } };
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
/* 182 */   private Set stopSet = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */   private char[] charset;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianAnalyzer()
/*     */   {
/* 193 */     this.charset = RussianCharsets.UnicodeRussian;
/* 194 */     this.stopSet = StopFilter.makeStopSet(makeStopWords(RussianCharsets.UnicodeRussian));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianAnalyzer(char[] charset)
/*     */   {
/* 203 */     this.charset = charset;
/* 204 */     this.stopSet = StopFilter.makeStopSet(makeStopWords(charset));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianAnalyzer(char[] charset, String[] stopwords)
/*     */   {
/* 212 */     this.charset = charset;
/* 213 */     this.stopSet = StopFilter.makeStopSet(stopwords);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static String[] makeStopWords(char[] charset)
/*     */   {
/* 220 */     String[] res = new String[RUSSIAN_STOP_WORDS.length];
/* 221 */     for (int i = 0; i < res.length; i++)
/*     */     {
/* 223 */       char[] theStopWord = RUSSIAN_STOP_WORDS[i];
/*     */       
/* 225 */       StringBuffer theWord = new StringBuffer();
/* 226 */       for (int j = 0; j < theStopWord.length; j++)
/*     */       {
/* 228 */         theWord.append(charset[theStopWord[j]]);
/*     */       }
/* 230 */       res[i] = theWord.toString();
/*     */     }
/* 232 */     return res;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianAnalyzer(char[] charset, Hashtable stopwords)
/*     */   {
/* 241 */     this.charset = charset;
/* 242 */     this.stopSet = new HashSet(stopwords.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TokenStream tokenStream(String fieldName, Reader reader)
/*     */   {
/* 253 */     TokenStream result = new RussianLetterTokenizer(reader, this.charset);
/* 254 */     result = new RussianLowerCaseFilter(result, this.charset);
/* 255 */     result = new StopFilter(result, this.stopSet);
/* 256 */     result = new RussianStemFilter(result, this.charset);
/* 257 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/ru/RussianAnalyzer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */