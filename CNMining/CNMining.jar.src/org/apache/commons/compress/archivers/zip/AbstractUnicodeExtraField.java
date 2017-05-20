/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.ZipException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractUnicodeExtraField
/*     */   implements ZipExtraField
/*     */ {
/*     */   private long nameCRC32;
/*     */   private byte[] unicodeName;
/*     */   private byte[] data;
/*     */   
/*     */   protected AbstractUnicodeExtraField() {}
/*     */   
/*     */   protected AbstractUnicodeExtraField(String text, byte[] bytes, int off, int len)
/*     */   {
/*  50 */     CRC32 crc32 = new CRC32();
/*  51 */     crc32.update(bytes, off, len);
/*  52 */     this.nameCRC32 = crc32.getValue();
/*     */     try
/*     */     {
/*  55 */       this.unicodeName = text.getBytes("UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  57 */       throw new RuntimeException("FATAL: UTF-8 encoding not supported.", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractUnicodeExtraField(String text, byte[] bytes)
/*     */   {
/*  70 */     this(text, bytes, 0, bytes.length);
/*     */   }
/*     */   
/*     */   private void assembleData() {
/*  74 */     if (this.unicodeName == null) {
/*  75 */       return;
/*     */     }
/*     */     
/*  78 */     this.data = new byte[5 + this.unicodeName.length];
/*     */     
/*  80 */     this.data[0] = 1;
/*  81 */     System.arraycopy(ZipLong.getBytes(this.nameCRC32), 0, this.data, 1, 4);
/*  82 */     System.arraycopy(this.unicodeName, 0, this.data, 5, this.unicodeName.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getNameCRC32()
/*     */   {
/*  90 */     return this.nameCRC32;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNameCRC32(long nameCRC32)
/*     */   {
/*  98 */     this.nameCRC32 = nameCRC32;
/*  99 */     this.data = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public byte[] getUnicodeName()
/*     */   {
/* 106 */     return this.unicodeName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setUnicodeName(byte[] unicodeName)
/*     */   {
/* 113 */     this.unicodeName = unicodeName;
/* 114 */     this.data = null;
/*     */   }
/*     */   
/*     */   public byte[] getCentralDirectoryData() {
/* 118 */     if (this.data == null) {
/* 119 */       assembleData();
/*     */     }
/* 121 */     return this.data;
/*     */   }
/*     */   
/*     */   public ZipShort getCentralDirectoryLength() {
/* 125 */     if (this.data == null) {
/* 126 */       assembleData();
/*     */     }
/* 128 */     return new ZipShort(this.data.length);
/*     */   }
/*     */   
/*     */   public byte[] getLocalFileDataData() {
/* 132 */     return getCentralDirectoryData();
/*     */   }
/*     */   
/*     */   public ZipShort getLocalFileDataLength() {
/* 136 */     return getCentralDirectoryLength();
/*     */   }
/*     */   
/*     */   public void parseFromLocalFileData(byte[] buffer, int offset, int length)
/*     */     throws ZipException
/*     */   {
/* 142 */     if (length < 5) {
/* 143 */       throw new ZipException("UniCode path extra data must have at least 5 bytes.");
/*     */     }
/*     */     
/* 146 */     int version = buffer[offset];
/*     */     
/* 148 */     if (version != 1) {
/* 149 */       throw new ZipException("Unsupported version [" + version + "] for UniCode path extra data.");
/*     */     }
/*     */     
/*     */ 
/* 153 */     this.nameCRC32 = ZipLong.getValue(buffer, offset + 1);
/* 154 */     this.unicodeName = new byte[length - 5];
/* 155 */     System.arraycopy(buffer, offset + 5, this.unicodeName, 0, length - 5);
/* 156 */     this.data = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length)
/*     */     throws ZipException
/*     */   {
/* 166 */     parseFromLocalFileData(buffer, offset, length);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/AbstractUnicodeExtraField.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */