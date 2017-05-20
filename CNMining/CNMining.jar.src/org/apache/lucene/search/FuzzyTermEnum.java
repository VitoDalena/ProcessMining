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
/*     */ public final class FuzzyTermEnum
/*     */   extends FilteredTermEnum
/*     */ {
/*     */   double distance;
/*  29 */   boolean endEnum = false;
/*     */   
/*  31 */   Term searchTerm = null;
/*  32 */   String field = "";
/*  33 */   String text = "";
/*     */   int textlen;
/*  35 */   String prefix = "";
/*  36 */   int prefixLength = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   float minimumSimilarity;
/*     */   
/*     */ 
/*     */   double scale_factor;
/*     */   
/*     */ 
/*     */ 
/*     */   public FuzzyTermEnum(IndexReader reader, Term term)
/*     */     throws IOException
/*     */   {
/*  50 */     this(reader, term, 0.5F, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FuzzyTermEnum(IndexReader reader, Term term, float minSimilarity)
/*     */     throws IOException
/*     */   {
/*  63 */     this(reader, term, minSimilarity, 0);
/*     */   }
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
/*     */   public FuzzyTermEnum(IndexReader reader, Term term, float minSimilarity, int prefixLength)
/*     */     throws IOException
/*     */   {
/*  79 */     this.minimumSimilarity = minSimilarity;
/*  80 */     this.scale_factor = (1.0F / (1.0F - this.minimumSimilarity));
/*  81 */     this.searchTerm = term;
/*  82 */     this.field = this.searchTerm.field();
/*  83 */     this.text = this.searchTerm.text();
/*  84 */     this.textlen = this.text.length();
/*  85 */     if ((prefixLength > 0) && (prefixLength < this.textlen)) {
/*  86 */       this.prefixLength = prefixLength;
/*  87 */       this.prefix = this.text.substring(0, prefixLength);
/*  88 */       this.text = this.text.substring(prefixLength);
/*  89 */       this.textlen = this.text.length();
/*     */     }
/*  91 */     setEnum(reader.terms(new Term(this.searchTerm.field(), this.prefix)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final boolean termCompare(Term term)
/*     */   {
/*  99 */     String termText = term.text();
/* 100 */     if ((this.field == term.field()) && (termText.startsWith(this.prefix))) {
/* 101 */       String target = termText.substring(this.prefixLength);
/* 102 */       int targetlen = target.length();
/* 103 */       int dist = editDistance(this.text, target, this.textlen, targetlen);
/* 104 */       this.distance = (1.0D - dist / Math.min(this.textlen, targetlen));
/* 105 */       return this.distance > this.minimumSimilarity;
/*     */     }
/* 107 */     this.endEnum = true;
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   protected final float difference() {
/* 112 */     return (float)((this.distance - this.minimumSimilarity) * this.scale_factor);
/*     */   }
/*     */   
/*     */   public final boolean endEnum() {
/* 116 */     return this.endEnum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int min(int a, int b, int c)
/*     */   {
/* 127 */     int t = a < b ? a : b;
/* 128 */     return t < c ? t : c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 135 */   private int[][] e = new int[1][1];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int editDistance(String s, String t, int n, int m)
/*     */   {
/* 147 */     if ((this.e.length <= n) || (this.e[0].length <= m)) {
/* 148 */       this.e = new int[Math.max(this.e.length, n + 1)][Math.max(this.e[0].length, m + 1)];
/*     */     }
/* 150 */     int[][] d = this.e;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 155 */     if (n == 0) return m;
/* 156 */     if (m == 0) { return n;
/*     */     }
/*     */     
/* 159 */     for (int i = 0; i <= n; i++) d[i][0] = i;
/* 160 */     for (int j = 0; j <= m; j++) { d[0][j] = j;
/*     */     }
/*     */     
/* 163 */     for (i = 1; i <= n; i++) {
/* 164 */       char s_i = s.charAt(i - 1);
/* 165 */       for (j = 1; j <= m; j++) {
/* 166 */         if (s_i != t.charAt(j - 1))
/* 167 */           d[i][j] = (min(d[(i - 1)][j], d[i][(j - 1)], d[(i - 1)][(j - 1)]) + 1); else {
/* 168 */           d[i][j] = min(d[(i - 1)][j] + 1, d[i][(j - 1)] + 1, d[(i - 1)][(j - 1)]);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 173 */     return d[n][m];
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 177 */     super.close();
/* 178 */     this.searchTerm = null;
/* 179 */     this.field = null;
/* 180 */     this.text = null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FuzzyTermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */