/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.zip.CRC32;
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
/*     */ public abstract class ZipUtil
/*     */ {
/*  33 */   private static final byte[] DOS_TIME_MIN = ZipLong.getBytes(8448L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ZipLong toDosTime(Date time)
/*     */   {
/*  41 */     return new ZipLong(toDosTime(time.getTime()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] toDosTime(long t)
/*     */   {
/*  52 */     Calendar c = Calendar.getInstance();
/*  53 */     c.setTimeInMillis(t);
/*     */     
/*  55 */     int year = c.get(1);
/*  56 */     if (year < 1980) {
/*  57 */       return (byte[])DOS_TIME_MIN.clone();
/*     */     }
/*  59 */     int month = c.get(2) + 1;
/*  60 */     long value = year - 1980 << 25 | month << 21 | c.get(5) << 16 | c.get(11) << 11 | c.get(12) << 5 | c.get(13) >> 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  66 */     return ZipLong.getBytes(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long adjustToLong(int i)
/*     */   {
/*  76 */     if (i < 0) {
/*  77 */       return 4294967296L + i;
/*     */     }
/*  79 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Date fromDosTime(ZipLong zipDosTime)
/*     */   {
/*  90 */     long dosTime = zipDosTime.getValue();
/*  91 */     return new Date(dosToJavaTime(dosTime));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long dosToJavaTime(long dosTime)
/*     */   {
/*  99 */     Calendar cal = Calendar.getInstance();
/*     */     
/* 101 */     cal.set(1, (int)(dosTime >> 25 & 0x7F) + 1980);
/* 102 */     cal.set(2, (int)(dosTime >> 21 & 0xF) - 1);
/* 103 */     cal.set(5, (int)(dosTime >> 16) & 0x1F);
/* 104 */     cal.set(11, (int)(dosTime >> 11) & 0x1F);
/* 105 */     cal.set(12, (int)(dosTime >> 5) & 0x3F);
/* 106 */     cal.set(13, (int)(dosTime << 1) & 0x3E);
/*     */     
/* 108 */     return cal.getTime().getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static void setNameAndCommentFromExtraFields(ZipArchiveEntry ze, byte[] originalNameBytes, byte[] commentBytes)
/*     */   {
/* 119 */     UnicodePathExtraField name = (UnicodePathExtraField)ze.getExtraField(UnicodePathExtraField.UPATH_ID);
/*     */     
/* 121 */     String originalName = ze.getName();
/* 122 */     String newName = getUnicodeStringIfOriginalMatches(name, originalNameBytes);
/*     */     
/* 124 */     if ((newName != null) && (!originalName.equals(newName))) {
/* 125 */       ze.setName(newName);
/*     */     }
/*     */     
/* 128 */     if ((commentBytes != null) && (commentBytes.length > 0)) {
/* 129 */       UnicodeCommentExtraField cmt = (UnicodeCommentExtraField)ze.getExtraField(UnicodeCommentExtraField.UCOM_ID);
/*     */       
/* 131 */       String newComment = getUnicodeStringIfOriginalMatches(cmt, commentBytes);
/*     */       
/* 133 */       if (newComment != null) {
/* 134 */         ze.setComment(newComment);
/*     */       }
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
/*     */   private static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField f, byte[] orig)
/*     */   {
/* 149 */     if (f != null) {
/* 150 */       CRC32 crc32 = new CRC32();
/* 151 */       crc32.update(orig);
/* 152 */       long origCRC32 = crc32.getValue();
/*     */       
/* 154 */       if (origCRC32 == f.getNameCRC32()) {
/*     */         try {
/* 156 */           return ZipEncodingHelper.UTF8_ZIP_ENCODING.decode(f.getUnicodeName());
/*     */ 
/*     */ 
/*     */         }
/*     */         catch (IOException ex)
/*     */         {
/*     */ 
/* 163 */           return null;
/*     */         }
/*     */       }
/*     */     }
/* 167 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */