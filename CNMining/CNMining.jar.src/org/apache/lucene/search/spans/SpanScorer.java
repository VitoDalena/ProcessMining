/*     */ package org.apache.lucene.search.spans;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.search.Explanation;
/*     */ import org.apache.lucene.search.Scorer;
/*     */ import org.apache.lucene.search.Similarity;
/*     */ import org.apache.lucene.search.Weight;
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
/*     */ class SpanScorer
/*     */   extends Scorer
/*     */ {
/*     */   private Spans spans;
/*     */   private Weight weight;
/*     */   private byte[] norms;
/*     */   private float value;
/*  33 */   private boolean firstTime = true;
/*  34 */   private boolean more = true;
/*     */   private int doc;
/*     */   private float freq;
/*     */   
/*     */   SpanScorer(Spans spans, Weight weight, Similarity similarity, byte[] norms)
/*     */     throws IOException
/*     */   {
/*  41 */     super(similarity);
/*  42 */     this.spans = spans;
/*  43 */     this.norms = norms;
/*  44 */     this.weight = weight;
/*  45 */     this.value = weight.getValue();
/*     */   }
/*     */   
/*     */   public boolean next() throws IOException {
/*  49 */     if (this.firstTime) {
/*  50 */       this.more = this.spans.next();
/*  51 */       this.firstTime = false;
/*     */     }
/*     */     
/*  54 */     if (!this.more) { return false;
/*     */     }
/*  56 */     this.freq = 0.0F;
/*  57 */     this.doc = this.spans.doc();
/*     */     
/*  59 */     while ((this.more) && (this.doc == this.spans.doc())) {
/*  60 */       int matchLength = this.spans.end() - this.spans.start();
/*  61 */       this.freq += getSimilarity().sloppyFreq(matchLength);
/*  62 */       this.more = this.spans.next();
/*     */     }
/*     */     
/*  65 */     return (this.more) || (this.freq != 0.0F);
/*     */   }
/*     */   
/*  68 */   public int doc() { return this.doc; }
/*     */   
/*     */   public float score() throws IOException {
/*  71 */     float raw = getSimilarity().tf(this.freq) * this.value;
/*  72 */     return raw * Similarity.decodeNorm(this.norms[this.doc]);
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/*  76 */     this.more = this.spans.skipTo(target);
/*     */     
/*  78 */     if (!this.more) { return false;
/*     */     }
/*  80 */     this.freq = 0.0F;
/*  81 */     this.doc = this.spans.doc();
/*     */     
/*  83 */     while ((this.more) && (this.spans.doc() == target)) {
/*  84 */       this.freq += getSimilarity().sloppyFreq(this.spans.end() - this.spans.start());
/*  85 */       this.more = this.spans.next();
/*     */     }
/*     */     
/*  88 */     return (this.more) || (this.freq != 0.0F);
/*     */   }
/*     */   
/*     */   public Explanation explain(int doc) throws IOException {
/*  92 */     Explanation tfExplanation = new Explanation();
/*     */     
/*  94 */     skipTo(doc);
/*     */     
/*  96 */     float phraseFreq = doc() == doc ? this.freq : 0.0F;
/*  97 */     tfExplanation.setValue(getSimilarity().tf(phraseFreq));
/*  98 */     tfExplanation.setDescription("tf(phraseFreq=" + phraseFreq + ")");
/*     */     
/* 100 */     return tfExplanation;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/SpanScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */