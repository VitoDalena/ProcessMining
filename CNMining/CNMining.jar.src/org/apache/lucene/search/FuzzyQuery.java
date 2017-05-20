/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
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
/*     */ public final class FuzzyQuery
/*     */   extends MultiTermQuery
/*     */ {
/*     */   public static final float defaultMinSimilarity = 0.5F;
/*     */   private float minimumSimilarity;
/*     */   private int prefixLength;
/*     */   
/*     */   public FuzzyQuery(Term term, float minimumSimilarity, int prefixLength)
/*     */     throws IllegalArgumentException
/*     */   {
/*  49 */     super(term);
/*     */     
/*  51 */     if (minimumSimilarity > 1.0F)
/*  52 */       throw new IllegalArgumentException("minimumSimilarity > 1");
/*  53 */     if (minimumSimilarity < 0.0F)
/*  54 */       throw new IllegalArgumentException("minimumSimilarity < 0");
/*  55 */     this.minimumSimilarity = minimumSimilarity;
/*     */     
/*  57 */     if (prefixLength < 0)
/*  58 */       throw new IllegalArgumentException("prefixLength < 0");
/*  59 */     if (prefixLength >= term.text().length())
/*  60 */       throw new IllegalArgumentException("prefixLength >= term.text().length()");
/*  61 */     this.prefixLength = prefixLength;
/*     */   }
/*     */   
/*     */ 
/*     */   public FuzzyQuery(Term term, float minimumSimilarity)
/*     */     throws IllegalArgumentException
/*     */   {
/*  68 */     this(term, minimumSimilarity, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FuzzyQuery(Term term)
/*     */   {
/*  75 */     this(term, 0.5F, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getMinSimilarity()
/*     */   {
/*  83 */     return this.minimumSimilarity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPrefixLength()
/*     */   {
/*  92 */     return this.prefixLength;
/*     */   }
/*     */   
/*     */   protected FilteredTermEnum getEnum(IndexReader reader) throws IOException {
/*  96 */     return new FuzzyTermEnum(reader, getTerm(), this.minimumSimilarity, this.prefixLength);
/*     */   }
/*     */   
/*     */   public String toString(String field) {
/* 100 */     return super.toString(field) + '~' + Float.toString(this.minimumSimilarity);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FuzzyQuery.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */