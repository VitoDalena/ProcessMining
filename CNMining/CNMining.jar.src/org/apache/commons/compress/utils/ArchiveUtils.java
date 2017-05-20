/*     */ package org.apache.commons.compress.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
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
/*     */ 
/*     */ 
/*     */ public class ArchiveUtils
/*     */ {
/*     */   public static String toString(ArchiveEntry entry)
/*     */   {
/*  44 */     StringBuffer sb = new StringBuffer();
/*  45 */     sb.append(entry.isDirectory() ? 'd' : '-');
/*  46 */     String size = Long.toString(entry.getSize());
/*  47 */     sb.append(' ');
/*     */     
/*  49 */     for (int i = 7; i > size.length(); i--) {
/*  50 */       sb.append(' ');
/*     */     }
/*  52 */     sb.append(size);
/*  53 */     sb.append(' ').append(entry.getName());
/*  54 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean matchAsciiBuffer(String expected, byte[] buffer, int offset, int length)
/*     */   {
/*     */     byte[] buffer1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/*  70 */       buffer1 = expected.getBytes("ASCII");
/*     */     } catch (UnsupportedEncodingException e) {
/*  72 */       throw new RuntimeException(e);
/*     */     }
/*  74 */     return isEqual(buffer1, 0, buffer1.length, buffer, offset, length, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean matchAsciiBuffer(String expected, byte[] buffer)
/*     */   {
/*  85 */     return matchAsciiBuffer(expected, buffer, 0, buffer.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] toAsciiBytes(String inputString)
/*     */   {
/*     */     try
/*     */     {
/*  97 */       return inputString.getBytes("ASCII");
/*     */     } catch (UnsupportedEncodingException e) {
/*  99 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toAsciiString(byte[] inputBytes)
/*     */   {
/*     */     try
/*     */     {
/* 111 */       return new String(inputBytes, "ASCII");
/*     */     } catch (UnsupportedEncodingException e) {
/* 113 */       throw new RuntimeException(e);
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
/*     */   public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2, boolean ignoreTrailingNulls)
/*     */   {
/* 133 */     int minLen = length1 < length2 ? length1 : length2;
/* 134 */     for (int i = 0; i < minLen; i++) {
/* 135 */       if (buffer1[(offset1 + i)] != buffer2[(offset2 + i)]) {
/* 136 */         return false;
/*     */       }
/*     */     }
/* 139 */     if (length1 == length2) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (ignoreTrailingNulls) {
/* 143 */       if (length1 > length2) {
/* 144 */         for (int i = length2; i < length1; i++) {
/* 145 */           if (buffer1[(offset1 + i)] != 0) {
/* 146 */             return false;
/*     */           }
/*     */         }
/*     */       } else {
/* 150 */         for (int i = length1; i < length2; i++) {
/* 151 */           if (buffer2[(offset2 + i)] != 0) {
/* 152 */             return false;
/*     */           }
/*     */         }
/*     */       }
/* 156 */       return true;
/*     */     }
/* 158 */     return false;
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
/*     */   public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2)
/*     */   {
/* 175 */     return isEqual(buffer1, offset1, length1, buffer2, offset2, length2, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isEqual(byte[] buffer1, byte[] buffer2)
/*     */   {
/* 186 */     return isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isEqual(byte[] buffer1, byte[] buffer2, boolean ignoreTrailingNulls)
/*     */   {
/* 198 */     return isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, ignoreTrailingNulls);
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
/*     */   public static boolean isEqualWithNull(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2)
/*     */   {
/* 215 */     return isEqual(buffer1, offset1, length1, buffer2, offset2, length2, true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/utils/ArchiveUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */