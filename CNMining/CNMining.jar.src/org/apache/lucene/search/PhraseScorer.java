/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.TermPositions;
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
/*     */ abstract class PhraseScorer
/*     */   extends Scorer
/*     */ {
/*     */   private Weight weight;
/*     */   protected byte[] norms;
/*     */   protected float value;
/*  28 */   private boolean firstTime = true;
/*  29 */   private boolean more = true;
/*     */   
/*     */   protected PhraseQueue pq;
/*     */   protected PhrasePositions first;
/*     */   protected PhrasePositions last;
/*     */   private float freq;
/*     */   
/*     */   PhraseScorer(Weight weight, TermPositions[] tps, int[] positions, Similarity similarity, byte[] norms)
/*     */   {
/*  38 */     super(similarity);
/*  39 */     this.norms = norms;
/*  40 */     this.weight = weight;
/*  41 */     this.value = weight.getValue();
/*     */     
/*     */ 
/*  44 */     for (int i = 0; i < tps.length; i++) {
/*  45 */       PhrasePositions pp = new PhrasePositions(tps[i], positions[i]);
/*  46 */       if (this.last != null) {
/*  47 */         this.last.next = pp;
/*     */       } else
/*  49 */         this.first = pp;
/*  50 */       this.last = pp;
/*     */     }
/*     */     
/*  53 */     this.pq = new PhraseQueue(tps.length);
/*     */   }
/*     */   
/*     */ 
/*  57 */   public int doc() { return this.first.doc; }
/*     */   
/*     */   public boolean next() throws IOException {
/*  60 */     if (this.firstTime) {
/*  61 */       init();
/*  62 */       this.firstTime = false;
/*  63 */     } else if (this.more) {
/*  64 */       this.more = this.last.next();
/*     */     }
/*  66 */     return doNext();
/*     */   }
/*     */   
/*     */   private boolean doNext() throws IOException
/*     */   {
/*  71 */     while (this.more) {
/*  72 */       while ((this.more) && (this.first.doc < this.last.doc)) {
/*  73 */         this.more = this.first.skipTo(this.last.doc);
/*  74 */         firstToLast();
/*     */       }
/*     */       
/*  77 */       if (this.more)
/*     */       {
/*  79 */         this.freq = phraseFreq();
/*  80 */         if (this.freq == 0.0F) {
/*  81 */           this.more = this.last.next();
/*     */         } else
/*  83 */           return true;
/*     */       }
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public float score() throws IOException
/*     */   {
/*  91 */     float raw = getSimilarity().tf(this.freq) * this.value;
/*  92 */     return raw * Similarity.decodeNorm(this.norms[this.first.doc]);
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/*  96 */     for (PhrasePositions pp = this.first; (this.more) && (pp != null); pp = pp.next) {
/*  97 */       this.more = pp.skipTo(target);
/*     */     }
/*  99 */     if (this.more)
/* 100 */       sort();
/* 101 */     return doNext();
/*     */   }
/*     */   
/*     */   protected abstract float phraseFreq() throws IOException;
/*     */   
/*     */   private void init() throws IOException {
/* 107 */     for (PhrasePositions pp = this.first; (this.more) && (pp != null); pp = pp.next)
/* 108 */       this.more = pp.next();
/* 109 */     if (this.more)
/* 110 */       sort();
/*     */   }
/*     */   
/*     */   private void sort() {
/* 114 */     this.pq.clear();
/* 115 */     for (PhrasePositions pp = this.first; pp != null; pp = pp.next)
/* 116 */       this.pq.put(pp);
/* 117 */     pqToList();
/*     */   }
/*     */   
/*     */   protected final void pqToList() {
/* 121 */     this.last = (this.first = null);
/* 122 */     while (this.pq.top() != null) {
/* 123 */       PhrasePositions pp = (PhrasePositions)this.pq.pop();
/* 124 */       if (this.last != null) {
/* 125 */         this.last.next = pp;
/*     */       } else
/* 127 */         this.first = pp;
/* 128 */       this.last = pp;
/* 129 */       pp.next = null;
/*     */     }
/*     */   }
/*     */   
/*     */   protected final void firstToLast() {
/* 134 */     this.last.next = this.first;
/* 135 */     this.last = this.first;
/* 136 */     this.first = this.first.next;
/* 137 */     this.last.next = null;
/*     */   }
/*     */   
/*     */   public Explanation explain(int doc) throws IOException {
/* 141 */     Explanation tfExplanation = new Explanation();
/*     */     
/* 143 */     while ((next()) && (doc() < doc)) {}
/*     */     
/* 145 */     float phraseFreq = doc() == doc ? this.freq : 0.0F;
/* 146 */     tfExplanation.setValue(getSimilarity().tf(phraseFreq));
/* 147 */     tfExplanation.setDescription("tf(phraseFreq=" + phraseFreq + ")");
/*     */     
/* 149 */     return tfExplanation;
/*     */   }
/*     */   
/* 152 */   public String toString() { return "scorer(" + this.weight + ")"; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PhraseScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */