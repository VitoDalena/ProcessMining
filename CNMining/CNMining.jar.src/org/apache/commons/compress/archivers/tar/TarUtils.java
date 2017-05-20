/*     */ package org.apache.commons.compress.archivers.tar;
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
/*     */ public class TarUtils
/*     */ {
/*     */   private static final int BYTE_MASK = 255;
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
/*     */   public static long parseOctal(byte[] buffer, int offset, int length)
/*     */   {
/*  49 */     long result = 0L;
/*  50 */     boolean stillPadding = true;
/*  51 */     int end = offset + length;
/*     */     
/*  53 */     for (int i = offset; i < end; i++) {
/*  54 */       byte currentByte = buffer[i];
/*  55 */       if (currentByte == 0) {
/*     */         break;
/*     */       }
/*     */       
/*     */ 
/*  60 */       if ((currentByte == 32) || (currentByte == 48)) {
/*  61 */         if (!stillPadding)
/*     */         {
/*     */ 
/*     */ 
/*  65 */           if (currentByte == 32) {
/*     */             break;
/*     */           }
/*     */         }
/*     */       } else {
/*  70 */         stillPadding = false;
/*     */         
/*  72 */         if ((currentByte < 48) || (currentByte > 55)) {
/*  73 */           throw new IllegalArgumentException("Invalid octal digit at position " + i + " in '" + new String(buffer, offset, length) + "'");
/*     */         }
/*     */         
/*  76 */         result = (result << 3) + (currentByte - 48);
/*     */       }
/*     */     }
/*     */     
/*  80 */     return result;
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
/*     */   public static String parseName(byte[] buffer, int offset, int length)
/*     */   {
/*  94 */     StringBuffer result = new StringBuffer(length);
/*  95 */     int end = offset + length;
/*     */     
/*  97 */     for (int i = offset; i < end; i++) {
/*  98 */       if (buffer[i] == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 102 */       result.append((char)buffer[i]);
/*     */     }
/*     */     
/* 105 */     return result.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int formatNameBytes(String name, byte[] buf, int offset, int length)
/*     */   {
/* 127 */     for (int i = 0; (i < length) && (i < name.length()); i++) {
/* 128 */       buf[(offset + i)] = ((byte)name.charAt(i));
/*     */     }
/* 132 */     for (; 
/*     */         
/* 132 */         i < length; i++) {
/* 133 */       buf[(offset + i)] = 0;
/*     */     }
/*     */     
/* 136 */     return offset + length;
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
/*     */   public static void formatUnsignedOctalString(long value, byte[] buffer, int offset, int length)
/*     */   {
/* 150 */     int remaining = length;
/* 151 */     remaining--;
/* 152 */     if (value == 0L) {
/* 153 */       buffer[(offset + remaining--)] = 48;
/*     */     } else {
/* 155 */       long val = value;
/* 156 */       for (; (remaining >= 0) && (val != 0L); remaining--)
/*     */       {
/* 158 */         buffer[(offset + remaining)] = ((byte)(48 + (byte)(int)(val & 0x7)));
/* 159 */         val >>>= 3;
/*     */       }
/*     */       
/* 162 */       if (val != 0L) {
/* 163 */         throw new IllegalArgumentException(value + "=" + Long.toOctalString(value) + " will not fit in octal number buffer of length " + length);
/*     */       }
/*     */     }
/* 168 */     for (; 
/*     */         
/* 168 */         remaining >= 0; remaining--) {
/* 169 */       buffer[(offset + remaining)] = 48;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int formatOctalBytes(long value, byte[] buf, int offset, int length)
/*     */   {
/* 189 */     int idx = length - 2;
/* 190 */     formatUnsignedOctalString(value, buf, offset, idx);
/*     */     
/* 192 */     buf[(offset + idx++)] = 32;
/* 193 */     buf[(offset + idx)] = 0;
/*     */     
/* 195 */     return offset + length;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int formatLongOctalBytes(long value, byte[] buf, int offset, int length)
/*     */   {
/* 214 */     int idx = length - 1;
/*     */     
/* 216 */     formatUnsignedOctalString(value, buf, offset, idx);
/* 217 */     buf[(offset + idx)] = 32;
/*     */     
/* 219 */     return offset + length;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int formatCheckSumOctalBytes(long value, byte[] buf, int offset, int length)
/*     */   {
/* 238 */     int idx = length - 2;
/* 239 */     formatUnsignedOctalString(value, buf, offset, idx);
/*     */     
/* 241 */     buf[(offset + idx++)] = 0;
/* 242 */     buf[(offset + idx)] = 32;
/*     */     
/* 244 */     return offset + length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long computeCheckSum(byte[] buf)
/*     */   {
/* 254 */     long sum = 0L;
/*     */     
/* 256 */     for (int i = 0; i < buf.length; i++) {
/* 257 */       sum += (0xFF & buf[i]);
/*     */     }
/*     */     
/* 260 */     return sum;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */