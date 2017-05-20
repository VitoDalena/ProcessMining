/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.store.InputStream;
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
/*    */ final class SegmentTermPositions
/*    */   extends SegmentTermDocs
/*    */   implements TermPositions
/*    */ {
/*    */   private InputStream proxStream;
/*    */   private int proxCount;
/*    */   private int position;
/*    */   
/*    */   SegmentTermPositions(SegmentReader p)
/*    */     throws IOException
/*    */   {
/* 30 */     super(p);
/* 31 */     this.proxStream = ((InputStream)this.parent.proxStream.clone());
/*    */   }
/*    */   
/*    */   final void seek(TermInfo ti) throws IOException {
/* 35 */     super.seek(ti);
/* 36 */     if (ti != null)
/* 37 */       this.proxStream.seek(ti.proxPointer);
/* 38 */     this.proxCount = 0;
/*    */   }
/*    */   
/*    */   public final void close() throws IOException {
/* 42 */     super.close();
/* 43 */     this.proxStream.close();
/*    */   }
/*    */   
/*    */   public final int nextPosition() throws IOException {
/* 47 */     this.proxCount -= 1;
/* 48 */     return this.position += this.proxStream.readVInt();
/*    */   }
/*    */   
/*    */   protected final void skippingDoc() throws IOException {
/* 52 */     for (int f = this.freq; f > 0; f--)
/* 53 */       this.proxStream.readVInt();
/*    */   }
/*    */   
/*    */   public final boolean next() throws IOException {
/* 57 */     for (int f = this.proxCount; f > 0; f--) {
/* 58 */       this.proxStream.readVInt();
/*    */     }
/* 60 */     if (super.next()) {
/* 61 */       this.proxCount = this.freq;
/* 62 */       this.position = 0;
/* 63 */       return true;
/*    */     }
/* 65 */     return false;
/*    */   }
/*    */   
/*    */   public final int read(int[] docs, int[] freqs) throws IOException
/*    */   {
/* 70 */     throw new UnsupportedOperationException("TermPositions does not support processing multiple documents in one call. Use TermDocs instead.");
/*    */   }
/*    */   
/*    */   protected void skipProx(long proxPointer)
/*    */     throws IOException
/*    */   {
/* 76 */     this.proxStream.seek(proxPointer);
/* 77 */     this.proxCount = 0;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentTermPositions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */