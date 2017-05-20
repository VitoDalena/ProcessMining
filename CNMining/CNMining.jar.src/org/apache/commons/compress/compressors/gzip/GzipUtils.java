/*     */ package org.apache.commons.compress.compressors.gzip;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class GzipUtils
/*     */ {
/*  34 */   private static final Map compressSuffix = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  44 */   private static final Map uncompressSuffix = new HashMap();
/*     */   
/*     */   static {
/*  47 */     compressSuffix.put(".tar", ".tgz");
/*  48 */     compressSuffix.put(".svg", ".svgz");
/*  49 */     compressSuffix.put(".cpio", ".cpgz");
/*  50 */     compressSuffix.put(".wmf", ".wmz");
/*  51 */     compressSuffix.put(".emf", ".emz");
/*     */     
/*  53 */     uncompressSuffix.put(".tgz", ".tar");
/*  54 */     uncompressSuffix.put(".taz", ".tar");
/*  55 */     uncompressSuffix.put(".svgz", ".svg");
/*  56 */     uncompressSuffix.put(".cpgz", ".cpio");
/*  57 */     uncompressSuffix.put(".wmz", ".wmf");
/*  58 */     uncompressSuffix.put(".emz", ".emf");
/*  59 */     uncompressSuffix.put(".gz", "");
/*  60 */     uncompressSuffix.put(".z", "");
/*  61 */     uncompressSuffix.put("-gz", "");
/*  62 */     uncompressSuffix.put("-z", "");
/*  63 */     uncompressSuffix.put("_z", "");
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
/*     */   public static boolean isCompressedFilename(String filename)
/*     */   {
/*  79 */     String lower = filename.toLowerCase();
/*  80 */     int n = lower.length();
/*     */     
/*  82 */     for (int i = 2; (i <= 5) && (i < n); i++) {
/*  83 */       if (uncompressSuffix.containsKey(lower.substring(n - i))) {
/*  84 */         return true;
/*     */       }
/*     */     }
/*  87 */     return false;
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
/*     */   public static String getUncompressedFilename(String filename)
/*     */   {
/* 104 */     String lower = filename.toLowerCase();
/* 105 */     int n = lower.length();
/*     */     
/* 107 */     for (int i = 2; (i <= 5) && (i < n); i++) {
/* 108 */       Object suffix = uncompressSuffix.get(lower.substring(n - i));
/* 109 */       if (suffix != null) {
/* 110 */         return filename.substring(0, n - i) + suffix;
/*     */       }
/*     */     }
/* 113 */     return filename;
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
/*     */   public static String getCompressedFilename(String filename)
/*     */   {
/* 128 */     String lower = filename.toLowerCase();
/* 129 */     int n = lower.length();
/*     */     
/* 131 */     for (int i = 4; (i <= 5) && (i < n); i++) {
/* 132 */       Object suffix = compressSuffix.get(lower.substring(n - i));
/* 133 */       if (suffix != null) {
/* 134 */         return filename.substring(0, n - i) + suffix;
/*     */       }
/*     */     }
/*     */     
/* 138 */     return filename + ".gz";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/gzip/GzipUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */