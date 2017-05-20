/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.index.TermPositions;
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
/*    */ final class ExactPhraseScorer
/*    */   extends PhraseScorer
/*    */ {
/*    */   ExactPhraseScorer(Weight weight, TermPositions[] tps, int[] positions, Similarity similarity, byte[] norms)
/*    */     throws IOException
/*    */   {
/* 26 */     super(weight, tps, positions, similarity, norms);
/*    */   }
/*    */   
/*    */   protected final float phraseFreq() throws IOException
/*    */   {
/* 31 */     for (PhrasePositions pp = this.first; pp != null; pp = pp.next) {
/* 32 */       pp.firstPosition();
/* 33 */       this.pq.put(pp);
/*    */     }
/* 35 */     pqToList();
/*    */     
/* 37 */     int freq = 0;
/*    */     do {
/* 39 */       while (this.first.position < this.last.position) {
/*    */         do {
/* 41 */           if (!this.first.nextPosition())
/* 42 */             return freq;
/* 43 */         } while (this.first.position < this.last.position);
/* 44 */         firstToLast();
/*    */       }
/* 46 */       freq++;
/* 47 */     } while (this.last.nextPosition());
/*    */     
/* 49 */     return freq;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/ExactPhraseScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */