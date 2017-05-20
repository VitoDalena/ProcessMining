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
/*     */ public abstract class InputStream
/*     */   implements Cloneable
/*     */ {
/*     */   static final int BUFFER_SIZE = 1024;
/*     */   private byte[] buffer;
/*     */   private char[] chars;
/*  32 */   private long bufferStart = 0L;
/*  33 */   private int bufferLength = 0;
/*  34 */   private int bufferPosition = 0;
/*     */   
/*     */   protected long length;
/*     */   
/*     */ 
/*     */   public final byte readByte()
/*     */     throws IOException
/*     */   {
/*  42 */     if (this.bufferPosition >= this.bufferLength)
/*  43 */       refill();
/*  44 */     return this.buffer[(this.bufferPosition++)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void readBytes(byte[] b, int offset, int len)
/*     */     throws IOException
/*     */   {
/*  55 */     if (len < 1024) {
/*  56 */       for (int i = 0; i < len; i++)
/*  57 */         b[(i + offset)] = readByte();
/*     */     } else {
/*  59 */       long start = getFilePointer();
/*  60 */       seekInternal(start);
/*  61 */       readInternal(b, offset, len);
/*     */       
/*  63 */       this.bufferStart = (start + len);
/*  64 */       this.bufferPosition = 0;
/*  65 */       this.bufferLength = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final int readInt()
/*     */     throws IOException
/*     */   {
/*  73 */     return (readByte() & 0xFF) << 24 | (readByte() & 0xFF) << 16 | (readByte() & 0xFF) << 8 | readByte() & 0xFF;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int readVInt()
/*     */     throws IOException
/*     */   {
/*  83 */     byte b = readByte();
/*  84 */     int i = b & 0x7F;
/*  85 */     for (int shift = 7; (b & 0x80) != 0; shift += 7) {
/*  86 */       b = readByte();
/*  87 */       i |= (b & 0x7F) << shift;
/*     */     }
/*  89 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public final long readLong()
/*     */     throws IOException
/*     */   {
/*  96 */     return readInt() << 32 | readInt() & 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */ 
/*     */   public final long readVLong()
/*     */     throws IOException
/*     */   {
/* 103 */     byte b = readByte();
/* 104 */     long i = b & 0x7F;
/* 105 */     for (int shift = 7; (b & 0x80) != 0; shift += 7) {
/* 106 */       b = readByte();
/* 107 */       i |= (b & 0x7F) << shift;
/*     */     }
/* 109 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public final String readString()
/*     */     throws IOException
/*     */   {
/* 116 */     int length = readVInt();
/* 117 */     if ((this.chars == null) || (length > this.chars.length))
/* 118 */       this.chars = new char[length];
/* 119 */     readChars(this.chars, 0, length);
/* 120 */     return new String(this.chars, 0, length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void readChars(char[] buffer, int start, int length)
/*     */     throws IOException
/*     */   {
/* 131 */     int end = start + length;
/* 132 */     for (int i = start; i < end; i++) {
/* 133 */       byte b = readByte();
/* 134 */       if ((b & 0x80) == 0) {
/* 135 */         buffer[i] = ((char)(b & 0x7F));
/* 136 */       } else if ((b & 0xE0) != 224) {
/* 137 */         buffer[i] = ((char)((b & 0x1F) << 6 | readByte() & 0x3F));
/*     */       }
/*     */       else {
/* 140 */         buffer[i] = ((char)((b & 0xF) << 12 | (readByte() & 0x3F) << 6 | readByte() & 0x3F));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void refill()
/*     */     throws IOException
/*     */   {
/* 148 */     long start = this.bufferStart + this.bufferPosition;
/* 149 */     long end = start + 1024L;
/* 150 */     if (end > this.length)
/* 151 */       end = this.length;
/* 152 */     this.bufferLength = ((int)(end - start));
/* 153 */     if (this.bufferLength == 0) {
/* 154 */       throw new IOException("read past EOF");
/*     */     }
/* 156 */     if (this.buffer == null)
/* 157 */       this.buffer = new byte['Ѐ'];
/* 158 */     readInternal(this.buffer, 0, this.bufferLength);
/*     */     
/* 160 */     this.bufferStart = start;
/* 161 */     this.bufferPosition = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void readInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void close()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final long getFilePointer()
/*     */   {
/* 181 */     return this.bufferStart + this.bufferPosition;
/*     */   }
/*     */   
/*     */ 
/*     */   public final void seek(long pos)
/*     */     throws IOException
/*     */   {
/* 188 */     if ((pos >= this.bufferStart) && (pos < this.bufferStart + this.bufferLength)) {
/* 189 */       this.bufferPosition = ((int)(pos - this.bufferStart));
/*     */     } else {
/* 191 */       this.bufferStart = pos;
/* 192 */       this.bufferPosition = 0;
/* 193 */       this.bufferLength = 0;
/* 194 */       seekInternal(pos);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void seekInternal(long paramLong)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   public final long length()
/*     */   {
/* 206 */     return this.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 219 */     InputStream clone = null;
/*     */     try {
/* 221 */       clone = (InputStream)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException e) {}
/* 224 */     if (this.buffer != null) {
/* 225 */       clone.buffer = new byte['Ѐ'];
/* 226 */       System.arraycopy(this.buffer, 0, clone.buffer, 0, this.bufferLength);
/*     */     }
/*     */     
/* 229 */     clone.chars = null;
/*     */     
/* 231 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/InputStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */