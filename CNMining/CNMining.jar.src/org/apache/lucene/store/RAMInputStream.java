/*    */ package org.apache.lucene.store;
/*    */ 
/*    */ import java.util.Vector;
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
/*    */ class RAMInputStream
/*    */   extends InputStream
/*    */   implements Cloneable
/*    */ {
/*    */   private RAMFile file;
/* 27 */   private int pointer = 0;
/*    */   
/*    */   public RAMInputStream(RAMFile f) {
/* 30 */     this.file = f;
/* 31 */     this.length = this.file.length;
/*    */   }
/*    */   
/*    */   public void readInternal(byte[] dest, int destOffset, int len) {
/* 35 */     int remainder = len;
/* 36 */     int start = this.pointer;
/* 37 */     while (remainder != 0) {
/* 38 */       int bufferNumber = start / 1024;
/* 39 */       int bufferOffset = start % 1024;
/* 40 */       int bytesInBuffer = 1024 - bufferOffset;
/* 41 */       int bytesToCopy = bytesInBuffer >= remainder ? remainder : bytesInBuffer;
/* 42 */       byte[] buffer = (byte[])this.file.buffers.elementAt(bufferNumber);
/* 43 */       System.arraycopy(buffer, bufferOffset, dest, destOffset, bytesToCopy);
/* 44 */       destOffset += bytesToCopy;
/* 45 */       start += bytesToCopy;
/* 46 */       remainder -= bytesToCopy;
/*    */     }
/* 48 */     this.pointer += len;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */   
/*    */   public void seekInternal(long pos)
/*    */   {
/* 55 */     this.pointer = ((int)pos);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/RAMInputStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */