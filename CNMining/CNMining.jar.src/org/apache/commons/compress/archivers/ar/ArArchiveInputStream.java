/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
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
/*     */ public class ArArchiveInputStream
/*     */   extends ArchiveInputStream
/*     */ {
/*     */   private final InputStream input;
/*  37 */   private long offset = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean closed;
/*     */   
/*     */ 
/*  44 */   private ArArchiveEntry currentEntry = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  50 */   private long entryOffset = -1L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArArchiveInputStream(InputStream pInput)
/*     */   {
/*  59 */     this.input = pInput;
/*  60 */     this.closed = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArArchiveEntry getNextArEntry()
/*     */     throws IOException
/*     */   {
/*  71 */     if (this.currentEntry != null) {
/*  72 */       long entryEnd = this.entryOffset + this.currentEntry.getLength();
/*  73 */       while (this.offset < entryEnd) {
/*  74 */         int x = read();
/*  75 */         if (x == -1)
/*     */         {
/*     */ 
/*  78 */           return null;
/*     */         }
/*     */       }
/*  81 */       this.currentEntry = null;
/*     */     }
/*     */     
/*  84 */     if (this.offset == 0L) {
/*  85 */       byte[] expected = ArchiveUtils.toAsciiBytes("!<arch>\n");
/*  86 */       byte[] realized = new byte[expected.length];
/*  87 */       int read = read(realized);
/*  88 */       if (read != expected.length) {
/*  89 */         throw new IOException("failed to read header. Occured at byte: " + getCount());
/*     */       }
/*  91 */       for (int i = 0; i < expected.length; i++) {
/*  92 */         if (expected[i] != realized[i]) {
/*  93 */           throw new IOException("invalid header " + ArchiveUtils.toAsciiString(realized));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  98 */     if ((this.offset % 2L != 0L) && 
/*  99 */       (read() < 0))
/*     */     {
/* 101 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 105 */     if (this.input.available() == 0) {
/* 106 */       return null;
/*     */     }
/*     */     
/* 109 */     byte[] name = new byte[16];
/* 110 */     byte[] lastmodified = new byte[12];
/* 111 */     byte[] userid = new byte[6];
/* 112 */     byte[] groupid = new byte[6];
/* 113 */     byte[] filemode = new byte[8];
/* 114 */     byte[] length = new byte[10];
/*     */     
/* 116 */     read(name);
/* 117 */     read(lastmodified);
/* 118 */     read(userid);
/* 119 */     read(groupid);
/* 120 */     read(filemode);
/* 121 */     read(length);
/*     */     
/*     */ 
/* 124 */     byte[] expected = ArchiveUtils.toAsciiBytes("`\n");
/* 125 */     byte[] realized = new byte[expected.length];
/* 126 */     int read = read(realized);
/* 127 */     if (read != expected.length) {
/* 128 */       throw new IOException("failed to read entry header. Occured at byte: " + getCount());
/*     */     }
/* 130 */     for (int i = 0; i < expected.length; i++) {
/* 131 */       if (expected[i] != realized[i]) {
/* 132 */         throw new IOException("invalid entry header. not read the content? Occured at byte: " + getCount());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 137 */     this.entryOffset = this.offset;
/*     */     
/*     */ 
/*     */ 
/* 141 */     String temp = ArchiveUtils.toAsciiString(name).trim();
/* 142 */     if (temp.endsWith("/")) {
/* 143 */       temp = temp.substring(0, temp.length() - 1);
/*     */     }
/* 145 */     this.currentEntry = new ArArchiveEntry(temp, Long.parseLong(new String(length).trim()));
/* 146 */     return this.currentEntry;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArchiveEntry getNextEntry()
/*     */     throws IOException
/*     */   {
/* 156 */     return getNextArEntry();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 165 */     if (!this.closed) {
/* 166 */       this.closed = true;
/* 167 */       this.input.close();
/*     */     }
/* 169 */     this.currentEntry = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 178 */     int toRead = len;
/* 179 */     if (this.currentEntry != null) {
/* 180 */       long entryEnd = this.entryOffset + this.currentEntry.getLength();
/* 181 */       if ((len > 0) && (entryEnd > this.offset)) {
/* 182 */         toRead = (int)Math.min(len, entryEnd - this.offset);
/*     */       } else {
/* 184 */         return -1;
/*     */       }
/*     */     }
/* 187 */     int ret = this.input.read(b, off, toRead);
/* 188 */     count(ret);
/* 189 */     this.offset += (ret > 0 ? ret : 0);
/* 190 */     return ret;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean matches(byte[] signature, int length)
/*     */   {
/* 206 */     if (length < 8) {
/* 207 */       return false;
/*     */     }
/* 209 */     if (signature[0] != 33) {
/* 210 */       return false;
/*     */     }
/* 212 */     if (signature[1] != 60) {
/* 213 */       return false;
/*     */     }
/* 215 */     if (signature[2] != 97) {
/* 216 */       return false;
/*     */     }
/* 218 */     if (signature[3] != 114) {
/* 219 */       return false;
/*     */     }
/* 221 */     if (signature[4] != 99) {
/* 222 */       return false;
/*     */     }
/* 224 */     if (signature[5] != 104) {
/* 225 */       return false;
/*     */     }
/* 227 */     if (signature[6] != 62) {
/* 228 */       return false;
/*     */     }
/* 230 */     if (signature[7] != 10) {
/* 231 */       return false;
/*     */     }
/*     */     
/* 234 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/ar/ArArchiveInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */