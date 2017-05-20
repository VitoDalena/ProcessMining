/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.TermDocs;
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
/*     */ final class TermScorer
/*     */   extends Scorer
/*     */ {
/*     */   private Weight weight;
/*     */   private TermDocs termDocs;
/*     */   private byte[] norms;
/*     */   private float weightValue;
/*     */   private int doc;
/*  30 */   private final int[] docs = new int[32];
/*  31 */   private final int[] freqs = new int[32];
/*     */   
/*     */   private int pointer;
/*     */   private int pointerMax;
/*     */   private static final int SCORE_CACHE_SIZE = 32;
/*  36 */   private float[] scoreCache = new float[32];
/*     */   
/*     */   TermScorer(Weight weight, TermDocs td, Similarity similarity, byte[] norms) throws IOException
/*     */   {
/*  40 */     super(similarity);
/*  41 */     this.weight = weight;
/*  42 */     this.termDocs = td;
/*  43 */     this.norms = norms;
/*  44 */     this.weightValue = weight.getValue();
/*     */     
/*  46 */     for (int i = 0; i < 32; i++)
/*  47 */       this.scoreCache[i] = (getSimilarity().tf(i) * this.weightValue);
/*     */   }
/*     */   
/*  50 */   public int doc() { return this.doc; }
/*     */   
/*     */   public boolean next() throws IOException {
/*  53 */     this.pointer += 1;
/*  54 */     if (this.pointer >= this.pointerMax) {
/*  55 */       this.pointerMax = this.termDocs.read(this.docs, this.freqs);
/*  56 */       if (this.pointerMax != 0) {
/*  57 */         this.pointer = 0;
/*     */       } else {
/*  59 */         this.termDocs.close();
/*  60 */         this.doc = Integer.MAX_VALUE;
/*  61 */         return false;
/*     */       }
/*     */     }
/*  64 */     this.doc = this.docs[this.pointer];
/*  65 */     return true;
/*     */   }
/*     */   
/*     */   public float score() throws IOException {
/*  69 */     int f = this.freqs[this.pointer];
/*  70 */     float raw = f < 32 ? this.scoreCache[f] : getSimilarity().tf(f) * this.weightValue;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  75 */     return raw * Similarity.decodeNorm(this.norms[this.doc]);
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException
/*     */   {
/*  80 */     for (this.pointer += 1; this.pointer < this.pointerMax; this.pointer += 1) {
/*  81 */       if (this.docs[this.pointer] >= target) {
/*  82 */         this.doc = this.docs[this.pointer];
/*  83 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  88 */     boolean result = this.termDocs.skipTo(target);
/*  89 */     if (result) {
/*  90 */       this.pointerMax = 1;
/*  91 */       this.pointer = 0;
/*  92 */       this.docs[this.pointer] = (this.doc = this.termDocs.doc());
/*  93 */       this.freqs[this.pointer] = this.termDocs.freq();
/*     */     } else {
/*  95 */       this.doc = Integer.MAX_VALUE;
/*     */     }
/*  97 */     return result;
/*     */   }
/*     */   
/*     */   public Explanation explain(int doc) throws IOException {
/* 101 */     TermQuery query = (TermQuery)this.weight.getQuery();
/* 102 */     Explanation tfExplanation = new Explanation();
/* 103 */     int tf = 0;
/* 104 */     while (this.pointer < this.pointerMax) {
/* 105 */       if (this.docs[this.pointer] == doc)
/* 106 */         tf = this.freqs[this.pointer];
/* 107 */       this.pointer += 1;
/*     */     }
/* 109 */     if (tf == 0) {
/* 110 */       while (this.termDocs.next()) {
/* 111 */         if (this.termDocs.doc() == doc) {
/* 112 */           tf = this.termDocs.freq();
/*     */         }
/*     */       }
/*     */     }
/* 116 */     this.termDocs.close();
/* 117 */     tfExplanation.setValue(getSimilarity().tf(tf));
/* 118 */     tfExplanation.setDescription("tf(termFreq(" + query.getTerm() + ")=" + tf + ")");
/*     */     
/* 120 */     return tfExplanation;
/*     */   }
/*     */   
/* 123 */   public String toString() { return "scorer(" + this.weight + ")"; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/TermScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */