/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ZipShort
/*     */   implements Cloneable
/*     */ {
/*     */   private static final int BYTE_MASK = 255;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int BYTE_1_MASK = 65280;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int BYTE_1_SHIFT = 8;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int value;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort(int value)
/*     */   {
/*  37 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort(byte[] bytes)
/*     */   {
/*  45 */     this(bytes, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipShort(byte[] bytes, int offset)
/*     */   {
/*  54 */     this.value = getValue(bytes, offset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getBytes()
/*     */   {
/*  62 */     byte[] result = new byte[2];
/*  63 */     result[0] = ((byte)(this.value & 0xFF));
/*  64 */     result[1] = ((byte)((this.value & 0xFF00) >> 8));
/*  65 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getValue()
/*     */   {
/*  73 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] getBytes(int value)
/*     */   {
/*  82 */     byte[] result = new byte[2];
/*  83 */     result[0] = ((byte)(value & 0xFF));
/*  84 */     result[1] = ((byte)((value & 0xFF00) >> 8));
/*  85 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getValue(byte[] bytes, int offset)
/*     */   {
/*  95 */     int value = bytes[(offset + 1)] << 8 & 0xFF00;
/*  96 */     value += (bytes[offset] & 0xFF);
/*  97 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getValue(byte[] bytes)
/*     */   {
/* 106 */     return getValue(bytes, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 115 */     if ((o == null) || (!(o instanceof ZipShort))) {
/* 116 */       return false;
/*     */     }
/* 118 */     return this.value == ((ZipShort)o).getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 126 */     return this.value;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 131 */       return super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cnfe) {
/* 134 */       throw new RuntimeException(cnfe);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipShort.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */