/*     */ package org.apache.lucene.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ import org.apache.lucene.store.OutputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BitVector
/*     */ {
/*     */   private byte[] bits;
/*     */   private int size;
/*  40 */   private int count = -1;
/*     */   
/*     */   public BitVector(int n)
/*     */   {
/*  44 */     this.size = n;
/*  45 */     this.bits = new byte[(this.size >> 3) + 1];
/*     */   }
/*     */   
/*     */   public final void set(int bit)
/*     */   {
/*  50 */     int tmp7_6 = (bit >> 3); byte[] tmp7_1 = this.bits;tmp7_1[tmp7_6] = ((byte)(tmp7_1[tmp7_6] | 1 << (bit & 0x7)));
/*  51 */     this.count = -1;
/*     */   }
/*     */   
/*     */   public final void clear(int bit)
/*     */   {
/*  56 */     int tmp7_6 = (bit >> 3); byte[] tmp7_1 = this.bits;tmp7_1[tmp7_6] = ((byte)(tmp7_1[tmp7_6] & (1 << (bit & 0x7) ^ 0xFFFFFFFF)));
/*  57 */     this.count = -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public final boolean get(int bit)
/*     */   {
/*  63 */     return (this.bits[(bit >> 3)] & 1 << (bit & 0x7)) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public final int size()
/*     */   {
/*  69 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int count()
/*     */   {
/*  77 */     if (this.count == -1) {
/*  78 */       int c = 0;
/*  79 */       int end = this.bits.length;
/*  80 */       for (int i = 0; i < end; i++)
/*  81 */         c += BYTE_COUNTS[(this.bits[i] & 0xFF)];
/*  82 */       this.count = c;
/*     */     }
/*  84 */     return this.count;
/*     */   }
/*     */   
/*  87 */   private static final byte[] BYTE_COUNTS = { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8 };
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
/*     */   public final void write(Directory d, String name)
/*     */     throws IOException
/*     */   {
/* 111 */     OutputStream output = d.createFile(name);
/*     */     try {
/* 113 */       output.writeInt(size());
/* 114 */       output.writeInt(count());
/* 115 */       output.writeBytes(this.bits, this.bits.length);
/*     */     } finally {
/* 117 */       output.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public BitVector(Directory d, String name)
/*     */     throws IOException
/*     */   {
/* 125 */     InputStream input = d.openFile(name);
/*     */     try {
/* 127 */       this.size = input.readInt();
/* 128 */       this.count = input.readInt();
/* 129 */       this.bits = new byte[(this.size >> 3) + 1];
/* 130 */       input.readBytes(this.bits, 0, this.bits.length);
/*     */     } finally {
/* 132 */       input.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/util/BitVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */