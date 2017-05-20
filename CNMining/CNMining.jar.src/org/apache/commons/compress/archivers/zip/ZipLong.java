/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ZipLong
/*     */   implements Cloneable
/*     */ {
/*     */   private static final int WORD = 4;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int BYTE_MASK = 255;
/*     */   
/*     */ 
/*     */   private static final int BYTE_1 = 1;
/*     */   
/*     */ 
/*     */   private static final int BYTE_1_MASK = 65280;
/*     */   
/*     */ 
/*     */   private static final int BYTE_1_SHIFT = 8;
/*     */   
/*     */ 
/*     */   private static final int BYTE_2 = 2;
/*     */   
/*     */ 
/*     */   private static final int BYTE_2_MASK = 16711680;
/*     */   
/*     */ 
/*     */   private static final int BYTE_2_SHIFT = 16;
/*     */   
/*     */ 
/*     */   private static final int BYTE_3 = 3;
/*     */   
/*     */ 
/*     */   private static final long BYTE_3_MASK = 4278190080L;
/*     */   
/*     */ 
/*     */   private static final int BYTE_3_SHIFT = 24;
/*     */   
/*     */ 
/*     */   private final long value;
/*     */   
/*     */ 
/*  46 */   public static final ZipLong CFH_SIG = new ZipLong(33639248L);
/*     */   
/*     */ 
/*  49 */   public static final ZipLong LFH_SIG = new ZipLong(67324752L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipLong(long value)
/*     */   {
/*  56 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipLong(byte[] bytes)
/*     */   {
/*  64 */     this(bytes, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipLong(byte[] bytes, int offset)
/*     */   {
/*  73 */     this.value = getValue(bytes, offset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getBytes()
/*     */   {
/*  81 */     return getBytes(this.value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValue()
/*     */   {
/*  89 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] getBytes(long value)
/*     */   {
/*  98 */     byte[] result = new byte[4];
/*  99 */     result[0] = ((byte)(int)(value & 0xFF));
/* 100 */     result[1] = ((byte)(int)((value & 0xFF00) >> 8));
/* 101 */     result[2] = ((byte)(int)((value & 0xFF0000) >> 16));
/* 102 */     result[3] = ((byte)(int)((value & 0xFF000000) >> 24));
/* 103 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getValue(byte[] bytes, int offset)
/*     */   {
/* 113 */     long value = bytes[(offset + 3)] << 24 & 0xFF000000;
/* 114 */     value += (bytes[(offset + 2)] << 16 & 0xFF0000);
/* 115 */     value += (bytes[(offset + 1)] << 8 & 0xFF00);
/* 116 */     value += (bytes[offset] & 0xFF);
/* 117 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getValue(byte[] bytes)
/*     */   {
/* 126 */     return getValue(bytes, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 135 */     if ((o == null) || (!(o instanceof ZipLong))) {
/* 136 */       return false;
/*     */     }
/* 138 */     return this.value == ((ZipLong)o).getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 146 */     return (int)this.value;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 151 */       return super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cnfe) {
/* 154 */       throw new RuntimeException(cnfe);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipLong.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */