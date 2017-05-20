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
/*    */ final class PhrasePositions
/*    */ {
/*    */   int doc;
/*    */   int position;
/*    */   int count;
/*    */   int offset;
/*    */   TermPositions tp;
/*    */   PhrasePositions next;
/*    */   
/*    */   PhrasePositions(TermPositions t, int o)
/*    */   {
/* 31 */     this.tp = t;
/* 32 */     this.offset = o;
/*    */   }
/*    */   
/*    */   final boolean next() throws IOException {
/* 36 */     if (!this.tp.next()) {
/* 37 */       this.tp.close();
/* 38 */       this.doc = Integer.MAX_VALUE;
/* 39 */       return false;
/*    */     }
/* 41 */     this.doc = this.tp.doc();
/* 42 */     this.position = 0;
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   final boolean skipTo(int target) throws IOException {
/* 47 */     if (!this.tp.skipTo(target)) {
/* 48 */       this.tp.close();
/* 49 */       this.doc = Integer.MAX_VALUE;
/* 50 */       return false;
/*    */     }
/* 52 */     this.doc = this.tp.doc();
/* 53 */     this.position = 0;
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   final void firstPosition() throws IOException
/*    */   {
/* 59 */     this.count = this.tp.freq();
/* 60 */     nextPosition();
/*    */   }
/*    */   
/*    */   final boolean nextPosition() throws IOException {
/* 64 */     if (this.count-- > 0) {
/* 65 */       this.position = (this.tp.nextPosition() - this.offset);
/* 66 */       return true;
/*    */     }
/* 68 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/PhrasePositions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */