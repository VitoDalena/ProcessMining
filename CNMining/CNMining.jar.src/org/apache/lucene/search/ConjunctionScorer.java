/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
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
/*     */ final class ConjunctionScorer
/*     */   extends Scorer
/*     */ {
/*  24 */   private LinkedList scorers = new LinkedList();
/*  25 */   private boolean firstTime = true;
/*  26 */   private boolean more = true;
/*     */   private float coord;
/*     */   
/*     */   public ConjunctionScorer(Similarity similarity) {
/*  30 */     super(similarity);
/*     */   }
/*     */   
/*     */   final void add(Scorer scorer) throws IOException {
/*  34 */     this.scorers.addLast(scorer);
/*     */   }
/*     */   
/*  37 */   private Scorer first() { return (Scorer)this.scorers.getFirst(); }
/*  38 */   private Scorer last() { return (Scorer)this.scorers.getLast(); }
/*     */   
/*  40 */   public int doc() { return first().doc(); }
/*     */   
/*     */   public boolean next() throws IOException {
/*  43 */     if (this.firstTime) {
/*  44 */       init();
/*  45 */     } else if (this.more) {
/*  46 */       this.more = last().next();
/*     */     }
/*  48 */     return doNext();
/*     */   }
/*     */   
/*     */   private boolean doNext() throws IOException {
/*  52 */     while ((this.more) && (first().doc() < last().doc())) {
/*  53 */       this.more = first().skipTo(last().doc());
/*  54 */       this.scorers.addLast(this.scorers.removeFirst());
/*     */     }
/*  56 */     return this.more;
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/*  60 */     Iterator i = this.scorers.iterator();
/*  61 */     while ((this.more) && (i.hasNext())) {
/*  62 */       this.more = ((Scorer)i.next()).skipTo(target);
/*     */     }
/*  64 */     if (this.more)
/*  65 */       sortScorers();
/*  66 */     return doNext();
/*     */   }
/*     */   
/*     */   public float score() throws IOException {
/*  70 */     float score = 0.0F;
/*  71 */     Iterator i = this.scorers.iterator();
/*  72 */     while (i.hasNext())
/*  73 */       score += ((Scorer)i.next()).score();
/*  74 */     score *= this.coord;
/*  75 */     return score;
/*     */   }
/*     */   
/*     */   private void init() throws IOException {
/*  79 */     this.more = (this.scorers.size() > 0);
/*     */     
/*     */ 
/*  82 */     this.coord = getSimilarity().coord(this.scorers.size(), this.scorers.size());
/*     */     
/*     */ 
/*  85 */     Iterator i = this.scorers.iterator();
/*  86 */     while ((this.more) && (i.hasNext())) {
/*  87 */       this.more = ((Scorer)i.next()).next();
/*     */     }
/*  89 */     if (this.more) {
/*  90 */       sortScorers();
/*     */     }
/*  92 */     this.firstTime = false;
/*     */   }
/*     */   
/*     */   private void sortScorers() throws IOException
/*     */   {
/*  97 */     Scorer[] array = (Scorer[])this.scorers.toArray(new Scorer[this.scorers.size()]);
/*  98 */     this.scorers.clear();
/*     */     
/*     */ 
/* 101 */     Arrays.sort(array, new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/* 103 */         return ((Scorer)o1).doc() - ((Scorer)o2).doc();
/*     */       }
/*     */       
/* 106 */       public boolean equals(Object o1, Object o2) { return ((Scorer)o1).doc() == ((Scorer)o2).doc(); }
/*     */     });
/*     */     
/*     */ 
/* 110 */     for (int i = 0; i < array.length; i++) {
/* 111 */       this.scorers.addLast(array[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public Explanation explain(int doc) throws IOException {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/ConjunctionScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */