/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface ScoreDocComparator
/*    */ {
/* 32 */   public static final ScoreDocComparator RELEVANCE = new ScoreDocComparator() {
/*    */     public int compare(ScoreDoc i, ScoreDoc j) {
/* 34 */       if (i.score > j.score) return -1;
/* 35 */       if (i.score < j.score) return 1;
/* 36 */       return 0;
/*    */     }
/*    */     
/* 39 */     public Comparable sortValue(ScoreDoc i) { return new Float(i.score); }
/*    */     
/*    */     public int sortType() {
/* 42 */       return 0;
/*    */     }
/*    */   };
/*    */   
/*    */ 
/*    */ 
/* 48 */   public static final ScoreDocComparator INDEXORDER = new ScoreDocComparator() {
/*    */     public int compare(ScoreDoc i, ScoreDoc j) {
/* 50 */       if (i.doc < j.doc) return -1;
/* 51 */       if (i.doc > j.doc) return 1;
/* 52 */       return 0;
/*    */     }
/*    */     
/* 55 */     public Comparable sortValue(ScoreDoc i) { return new Integer(i.doc); }
/*    */     
/*    */     public int sortType() {
/* 58 */       return 1;
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract int compare(ScoreDoc paramScoreDoc1, ScoreDoc paramScoreDoc2);
/*    */   
/*    */   public abstract Comparable sortValue(ScoreDoc paramScoreDoc);
/*    */   
/*    */   public abstract int sortType();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/ScoreDocComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */