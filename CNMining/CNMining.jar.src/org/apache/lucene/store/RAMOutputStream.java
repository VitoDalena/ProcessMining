/*     */ package org.apache.lucene.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ public class RAMOutputStream
/*     */   extends OutputStream
/*     */ {
/*     */   private RAMFile file;
/*  29 */   private int pointer = 0;
/*     */   
/*     */   public RAMOutputStream()
/*     */   {
/*  33 */     this(new RAMFile());
/*     */   }
/*     */   
/*     */   RAMOutputStream(RAMFile f) {
/*  37 */     this.file = f;
/*     */   }
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException
/*     */   {
/*  42 */     flush();
/*  43 */     long end = this.file.length;
/*  44 */     long pos = 0L;
/*  45 */     int buffer = 0;
/*  46 */     while (pos < end) {
/*  47 */       int length = 1024;
/*  48 */       long nextPos = pos + length;
/*  49 */       if (nextPos > end) {
/*  50 */         length = (int)(end - pos);
/*     */       }
/*  52 */       out.writeBytes((byte[])this.file.buffers.elementAt(buffer++), length);
/*  53 */       pos = nextPos;
/*     */     }
/*     */   }
/*     */   
/*     */   public void reset()
/*     */   {
/*     */     try {
/*  60 */       seek(0L);
/*     */     } catch (IOException e) {
/*  62 */       throw new RuntimeException(e.toString());
/*     */     }
/*     */     
/*  65 */     this.file.length = 0L;
/*     */   }
/*     */   
/*     */   public void flushBuffer(byte[] src, int len) {
/*  69 */     int bufferNumber = this.pointer / 1024;
/*  70 */     int bufferOffset = this.pointer % 1024;
/*  71 */     int bytesInBuffer = 1024 - bufferOffset;
/*  72 */     int bytesToCopy = bytesInBuffer >= len ? len : bytesInBuffer;
/*     */     
/*  74 */     if (bufferNumber == this.file.buffers.size()) {
/*  75 */       this.file.buffers.addElement(new byte['Ѐ']);
/*     */     }
/*  77 */     byte[] buffer = (byte[])this.file.buffers.elementAt(bufferNumber);
/*  78 */     System.arraycopy(src, 0, buffer, bufferOffset, bytesToCopy);
/*     */     
/*  80 */     if (bytesToCopy < len) {
/*  81 */       int srcOffset = bytesToCopy;
/*  82 */       bytesToCopy = len - bytesToCopy;
/*  83 */       bufferNumber++;
/*  84 */       if (bufferNumber == this.file.buffers.size())
/*  85 */         this.file.buffers.addElement(new byte['Ѐ']);
/*  86 */       buffer = (byte[])this.file.buffers.elementAt(bufferNumber);
/*  87 */       System.arraycopy(src, srcOffset, buffer, 0, bytesToCopy);
/*     */     }
/*  89 */     this.pointer += len;
/*  90 */     if (this.pointer > this.file.length) {
/*  91 */       this.file.length = this.pointer;
/*     */     }
/*  93 */     this.file.lastModified = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  97 */     super.close();
/*     */   }
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 101 */     super.seek(pos);
/* 102 */     this.pointer = ((int)pos);
/*     */   }
/*     */   
/* 105 */   public long length() { return this.file.length; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/RAMOutputStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */