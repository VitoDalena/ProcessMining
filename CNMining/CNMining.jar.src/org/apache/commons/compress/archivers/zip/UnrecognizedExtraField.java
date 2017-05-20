/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnrecognizedExtraField
/*     */   implements ZipExtraField
/*     */ {
/*     */   private ZipShort headerId;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private byte[] localData;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private byte[] centralData;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHeaderId(ZipShort headerId)
/*     */   {
/*  41 */     this.headerId = headerId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort getHeaderId()
/*     */   {
/*  49 */     return this.headerId;
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
/*     */   public void setLocalFileDataData(byte[] data)
/*     */   {
/*  64 */     this.localData = copy(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort getLocalFileDataLength()
/*     */   {
/*  72 */     return new ZipShort(this.localData.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getLocalFileDataData()
/*     */   {
/*  80 */     return copy(this.localData);
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
/*     */   public void setCentralDirectoryData(byte[] data)
/*     */   {
/*  94 */     this.centralData = copy(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort getCentralDirectoryLength()
/*     */   {
/* 103 */     if (this.centralData != null) {
/* 104 */       return new ZipShort(this.centralData.length);
/*     */     }
/* 106 */     return getLocalFileDataLength();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getCentralDirectoryData()
/*     */   {
/* 114 */     if (this.centralData != null) {
/* 115 */       return copy(this.centralData);
/*     */     }
/* 117 */     return getLocalFileDataData();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parseFromLocalFileData(byte[] data, int offset, int length)
/*     */   {
/* 127 */     byte[] tmp = new byte[length];
/* 128 */     System.arraycopy(data, offset, tmp, 0, length);
/* 129 */     setLocalFileDataData(tmp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parseFromCentralDirectoryData(byte[] data, int offset, int length)
/*     */   {
/* 140 */     byte[] tmp = new byte[length];
/* 141 */     System.arraycopy(data, offset, tmp, 0, length);
/* 142 */     setCentralDirectoryData(tmp);
/* 143 */     if (this.localData == null) {
/* 144 */       setLocalFileDataData(tmp);
/*     */     }
/*     */   }
/*     */   
/*     */   private static byte[] copy(byte[] from) {
/* 149 */     if (from != null) {
/* 150 */       byte[] to = new byte[from.length];
/* 151 */       System.arraycopy(from, 0, to, 0, to.length);
/* 152 */       return to;
/*     */     }
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/UnrecognizedExtraField.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */