/*     */ package org.apache.lucene.store;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class OutputStream
/*     */ {
/*     */   static final int BUFFER_SIZE = 1024;
/*  29 */   private final byte[] buffer = new byte['Ѐ'];
/*  30 */   private long bufferStart = 0L;
/*  31 */   private int bufferPosition = 0;
/*     */   
/*     */ 
/*     */   public final void writeByte(byte b)
/*     */     throws IOException
/*     */   {
/*  37 */     if (this.bufferPosition >= 1024)
/*  38 */       flush();
/*  39 */     this.buffer[(this.bufferPosition++)] = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void writeBytes(byte[] b, int length)
/*     */     throws IOException
/*     */   {
/*  48 */     for (int i = 0; i < length; i++) {
/*  49 */       writeByte(b[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public final void writeInt(int i)
/*     */     throws IOException
/*     */   {
/*  56 */     writeByte((byte)(i >> 24));
/*  57 */     writeByte((byte)(i >> 16));
/*  58 */     writeByte((byte)(i >> 8));
/*  59 */     writeByte((byte)i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void writeVInt(int i)
/*     */     throws IOException
/*     */   {
/*  68 */     while ((i & 0xFFFFFF80) != 0) {
/*  69 */       writeByte((byte)(i & 0x7F | 0x80));
/*  70 */       i >>>= 7;
/*     */     }
/*  72 */     writeByte((byte)i);
/*     */   }
/*     */   
/*     */ 
/*     */   public final void writeLong(long i)
/*     */     throws IOException
/*     */   {
/*  79 */     writeInt((int)(i >> 32));
/*  80 */     writeInt((int)i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void writeVLong(long i)
/*     */     throws IOException
/*     */   {
/*  89 */     while ((i & 0xFFFFFFFFFFFFFF80) != 0L) {
/*  90 */       writeByte((byte)(int)(i & 0x7F | 0x80));
/*  91 */       i >>>= 7;
/*     */     }
/*  93 */     writeByte((byte)(int)i);
/*     */   }
/*     */   
/*     */ 
/*     */   public final void writeString(String s)
/*     */     throws IOException
/*     */   {
/* 100 */     int length = s.length();
/* 101 */     writeVInt(length);
/* 102 */     writeChars(s, 0, length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void writeChars(String s, int start, int length)
/*     */     throws IOException
/*     */   {
/* 113 */     int end = start + length;
/* 114 */     for (int i = start; i < end; i++) {
/* 115 */       int code = s.charAt(i);
/* 116 */       if ((code >= 1) && (code <= 127)) {
/* 117 */         writeByte((byte)code);
/* 118 */       } else if (((code >= 128) && (code <= 2047)) || (code == 0)) {
/* 119 */         writeByte((byte)(0xC0 | code >> 6));
/* 120 */         writeByte((byte)(0x80 | code & 0x3F));
/*     */       } else {
/* 122 */         writeByte((byte)(0xE0 | code >>> 12));
/* 123 */         writeByte((byte)(0x80 | code >> 6 & 0x3F));
/* 124 */         writeByte((byte)(0x80 | code & 0x3F));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected final void flush() throws IOException
/*     */   {
/* 131 */     flushBuffer(this.buffer, this.bufferPosition);
/* 132 */     this.bufferStart += this.bufferPosition;
/* 133 */     this.bufferPosition = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void flushBuffer(byte[] paramArrayOfByte, int paramInt)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 145 */     flush();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final long getFilePointer()
/*     */     throws IOException
/*     */   {
/* 153 */     return this.bufferStart + this.bufferPosition;
/*     */   }
/*     */   
/*     */ 
/*     */   public void seek(long pos)
/*     */     throws IOException
/*     */   {
/* 160 */     flush();
/* 161 */     this.bufferStart = pos;
/*     */   }
/*     */   
/*     */   public abstract long length()
/*     */     throws IOException;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/OutputStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */