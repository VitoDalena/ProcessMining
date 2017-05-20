/*     */ package org.apache.lucene.analysis.de;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.analysis.Token;
/*     */ import org.apache.lucene.analysis.TokenFilter;
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
/*     */ 
/*     */ public final class GermanStemFilter
/*     */   extends TokenFilter
/*     */ {
/*  40 */   private Token token = null;
/*  41 */   private GermanStemmer stemmer = null;
/*  42 */   private Set exclusionSet = null;
/*     */   
/*     */   public GermanStemFilter(TokenStream in)
/*     */   {
/*  46 */     super(in);
/*  47 */     this.stemmer = new GermanStemmer();
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public GermanStemFilter(TokenStream in, Hashtable exclusiontable)
/*     */   {
/*  56 */     this(in);
/*  57 */     this.exclusionSet = new HashSet(exclusiontable.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public GermanStemFilter(TokenStream in, Set exclusionSet)
/*     */   {
/*  65 */     this(in);
/*  66 */     this.exclusionSet = exclusionSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Token next()
/*     */     throws IOException
/*     */   {
/*  75 */     if ((this.token = this.input.next()) == null) {
/*  76 */       return null;
/*     */     }
/*     */     
/*  79 */     if ((this.exclusionSet != null) && (this.exclusionSet.contains(this.token.termText()))) {
/*  80 */       return this.token;
/*     */     }
/*     */     
/*  83 */     String s = this.stemmer.stem(this.token.termText());
/*     */     
/*  85 */     if (!s.equals(this.token.termText())) {
/*  86 */       return new Token(s, this.token.startOffset(), this.token.endOffset(), this.token.type());
/*     */     }
/*     */     
/*  89 */     return this.token;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStemmer(GermanStemmer stemmer)
/*     */   {
/*  98 */     if (stemmer != null) {
/*  99 */       this.stemmer = stemmer;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setExclusionTable(Hashtable exclusiontable)
/*     */   {
/* 109 */     this.exclusionSet = new HashSet(exclusiontable.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExclusionSet(Set exclusionSet)
/*     */   {
/* 117 */     this.exclusionSet = exclusionSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/de/GermanStemFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */