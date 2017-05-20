/*     */ package org.apache.lucene.analysis;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
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
/*     */ public final class StopFilter
/*     */   extends TokenFilter
/*     */ {
/*     */   private Set stopWords;
/*     */   
/*     */   public StopFilter(TokenStream in, String[] stopWords)
/*     */   {
/*  37 */     super(in);
/*  38 */     this.stopWords = makeStopSet(stopWords);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public StopFilter(TokenStream in, Hashtable stopTable)
/*     */   {
/*  48 */     super(in);
/*  49 */     this.stopWords = new HashSet(stopTable.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StopFilter(TokenStream in, Set stopWords)
/*     */   {
/*  61 */     super(in);
/*  62 */     this.stopWords = stopWords;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final Hashtable makeStopTable(String[] stopWords)
/*     */   {
/*  74 */     Hashtable stopTable = new Hashtable(stopWords.length);
/*  75 */     for (int i = 0; i < stopWords.length; i++)
/*  76 */       stopTable.put(stopWords[i], stopWords[i]);
/*  77 */     return stopTable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final Set makeStopSet(String[] stopWords)
/*     */   {
/*  87 */     HashSet stopTable = new HashSet(stopWords.length);
/*  88 */     for (int i = 0; i < stopWords.length; i++)
/*  89 */       stopTable.add(stopWords[i]);
/*  90 */     return stopTable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final Token next()
/*     */     throws IOException
/*     */   {
/*  98 */     for (Token token = this.input.next(); token != null; token = this.input.next()) {
/*  99 */       if (!this.stopWords.contains(token.termText))
/* 100 */         return token;
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/StopFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */