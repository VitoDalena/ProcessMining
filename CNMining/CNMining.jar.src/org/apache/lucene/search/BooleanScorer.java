/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ final class BooleanScorer
/*     */   extends Scorer
/*     */ {
/*  22 */   private SubScorer scorers = null;
/*  23 */   private BucketTable bucketTable = new BucketTable(this);
/*     */   
/*  25 */   private int maxCoord = 1;
/*  26 */   private float[] coordFactors = null;
/*     */   
/*  28 */   private int requiredMask = 0;
/*  29 */   private int prohibitedMask = 0;
/*  30 */   private int nextMask = 1;
/*     */   private int end;
/*     */   
/*  33 */   BooleanScorer(Similarity similarity) { super(similarity); }
/*     */   
/*     */   static final class SubScorer
/*     */   {
/*     */     public Scorer scorer;
/*     */     public boolean done;
/*  39 */     public boolean required = false;
/*  40 */     public boolean prohibited = false;
/*     */     public HitCollector collector;
/*     */     public SubScorer next;
/*     */     
/*     */     public SubScorer(Scorer scorer, boolean required, boolean prohibited, HitCollector collector, SubScorer next)
/*     */       throws IOException
/*     */     {
/*  47 */       this.scorer = scorer;
/*  48 */       this.done = (!scorer.next());
/*  49 */       this.required = required;
/*  50 */       this.prohibited = prohibited;
/*  51 */       this.collector = collector;
/*  52 */       this.next = next;
/*     */     }
/*     */   }
/*     */   
/*     */   final void add(Scorer scorer, boolean required, boolean prohibited) throws IOException
/*     */   {
/*  58 */     int mask = 0;
/*  59 */     if ((required) || (prohibited)) {
/*  60 */       if (this.nextMask == 0) {
/*  61 */         throw new IndexOutOfBoundsException("More than 32 required/prohibited clauses in query.");
/*     */       }
/*  63 */       mask = this.nextMask;
/*  64 */       this.nextMask <<= 1;
/*     */     } else {
/*  66 */       mask = 0;
/*     */     }
/*  68 */     if (!prohibited) {
/*  69 */       this.maxCoord += 1;
/*     */     }
/*  71 */     if (prohibited) {
/*  72 */       this.prohibitedMask |= mask;
/*  73 */     } else if (required) {
/*  74 */       this.requiredMask |= mask;
/*     */     }
/*  76 */     this.scorers = new SubScorer(scorer, required, prohibited, this.bucketTable.newCollector(mask), this.scorers);
/*     */   }
/*     */   
/*     */   private final void computeCoordFactors() throws IOException
/*     */   {
/*  81 */     this.coordFactors = new float[this.maxCoord];
/*  82 */     for (int i = 0; i < this.maxCoord; i++) {
/*  83 */       this.coordFactors[i] = getSimilarity().coord(i, this.maxCoord - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private Bucket current;
/*     */   public int doc() {
/*  89 */     return this.current.doc; }
/*     */   
/*     */   public boolean next() throws IOException {
/*     */     boolean more;
/*     */     do {
/*  94 */       while (this.bucketTable.first != null) {
/*  95 */         this.current = this.bucketTable.first;
/*  96 */         this.bucketTable.first = this.current.next;
/*     */         
/*     */ 
/*  99 */         if (((this.current.bits & this.prohibitedMask) == 0) && ((this.current.bits & this.requiredMask) == this.requiredMask))
/*     */         {
/* 101 */           return true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 106 */       more = false;
/* 107 */       this.end += 1024;
/* 108 */       for (SubScorer sub = this.scorers; sub != null; sub = sub.next) {
/* 109 */         Scorer scorer = sub.scorer;
/* 110 */         while ((!sub.done) && (scorer.doc() < this.end)) {
/* 111 */           sub.collector.collect(scorer.doc(), scorer.score());
/* 112 */           sub.done = (!scorer.next());
/*     */         }
/* 114 */         if (!sub.done) {
/* 115 */           more = true;
/*     */         }
/*     */       }
/* 118 */     } while ((this.bucketTable.first != null | more));
/*     */     
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public float score() throws IOException {
/* 124 */     if (this.coordFactors == null)
/* 125 */       computeCoordFactors();
/* 126 */     return this.current.score * this.coordFactors[this.current.coord];
/*     */   }
/*     */   
/*     */   static final class Bucket {
/* 130 */     int doc = -1;
/*     */     
/*     */     float score;
/*     */     int bits;
/*     */     int coord;
/*     */     Bucket next;
/*     */   }
/*     */   
/*     */   static final class BucketTable
/*     */   {
/*     */     public static final int SIZE = 1024;
/*     */     public static final int MASK = 1023;
/* 142 */     final BooleanScorer.Bucket[] buckets = new BooleanScorer.Bucket['Ѐ'];
/* 143 */     BooleanScorer.Bucket first = null;
/*     */     private BooleanScorer scorer;
/*     */     
/*     */     public BucketTable(BooleanScorer scorer)
/*     */     {
/* 148 */       this.scorer = scorer;
/*     */     }
/*     */     
/* 151 */     public final int size() { return 1024; }
/*     */     
/*     */ 
/* 154 */     public HitCollector newCollector(int mask) { return new BooleanScorer.Collector(mask, this); }
/*     */   }
/*     */   
/*     */   static final class Collector extends HitCollector {
/*     */     private BooleanScorer.BucketTable bucketTable;
/*     */     private int mask;
/*     */     
/*     */     public Collector(int mask, BooleanScorer.BucketTable bucketTable) {
/* 162 */       this.mask = mask;
/* 163 */       this.bucketTable = bucketTable;
/*     */     }
/*     */     
/* 166 */     public final void collect(int doc, float score) { BooleanScorer.BucketTable table = this.bucketTable;
/* 167 */       int i = doc & 0x3FF;
/* 168 */       BooleanScorer.Bucket bucket = table.buckets[i];
/* 169 */       if (bucket == null) {
/* 170 */         table.buckets[i] = (bucket = new BooleanScorer.Bucket());
/*     */       }
/* 172 */       if (bucket.doc != doc) {
/* 173 */         bucket.doc = doc;
/* 174 */         bucket.score = score;
/* 175 */         bucket.bits = this.mask;
/* 176 */         bucket.coord = 1;
/*     */         
/* 178 */         bucket.next = table.first;
/* 179 */         table.first = bucket;
/*     */       } else {
/* 181 */         bucket.score += score;
/* 182 */         bucket.bits |= this.mask;
/* 183 */         bucket.coord += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/* 189 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Explanation explain(int doc) throws IOException {
/* 193 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 197 */     StringBuffer buffer = new StringBuffer();
/* 198 */     buffer.append("boolean(");
/* 199 */     for (SubScorer sub = this.scorers; sub != null; sub = sub.next) {
/* 200 */       buffer.append(sub.scorer.toString());
/* 201 */       buffer.append(" ");
/*     */     }
/* 203 */     buffer.append(")");
/* 204 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/BooleanScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */