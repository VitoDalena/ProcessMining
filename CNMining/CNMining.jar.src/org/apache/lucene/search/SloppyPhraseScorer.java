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
/*    */ 
/*    */ final class SloppyPhraseScorer
/*    */   extends PhraseScorer
/*    */ {
/*    */   private int slop;
/*    */   
/*    */   SloppyPhraseScorer(Weight weight, TermPositions[] tps, int[] positions, Similarity similarity, int slop, byte[] norms)
/*    */   {
/* 28 */     super(weight, tps, positions, similarity, norms);
/* 29 */     this.slop = slop;
/*    */   }
/*    */   
/*    */   protected final float phraseFreq() throws IOException {
/* 33 */     this.pq.clear();
/* 34 */     int end = 0;
/* 35 */     for (PhrasePositions pp = this.first; pp != null; pp = pp.next) {
/* 36 */       pp.firstPosition();
/* 37 */       if (pp.position > end)
/* 38 */         end = pp.position;
/* 39 */       this.pq.put(pp);
/*    */     }
/*    */     
/* 42 */     float freq = 0.0F;
/* 43 */     boolean done = false;
/*    */     do {
/* 45 */       PhrasePositions pp = (PhrasePositions)this.pq.pop();
/* 46 */       int start = pp.position;
/* 47 */       int next = ((PhrasePositions)this.pq.top()).position;
/* 48 */       for (int pos = start; pos <= next; pos = pp.position) {
/* 49 */         start = pos;
/* 50 */         if (!pp.nextPosition()) {
/* 51 */           done = true;
/* 52 */           break;
/*    */         }
/*    */       }
/*    */       
/* 56 */       int matchLength = end - start;
/* 57 */       if (matchLength <= this.slop) {
/* 58 */         freq += getSimilarity().sloppyFreq(matchLength);
/*    */       }
/* 60 */       if (pp.position > end)
/* 61 */         end = pp.position;
/* 62 */       this.pq.put(pp);
/* 63 */     } while (!done);
/*    */     
/* 65 */     return freq;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/SloppyPhraseScorer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */