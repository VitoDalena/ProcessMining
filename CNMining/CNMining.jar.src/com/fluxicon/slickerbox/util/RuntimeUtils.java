/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuntimeUtils
/*     */ {
/*     */   public static final String OS_WIN32 = "Windows";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_MACOSX = "Mac OS X";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_MACOSCLASSIC = "Mac OS 7-9";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_LINUX = "Linux";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_BSD = "BSD";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_RISCOS = "RISC OS";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_BEOS = "BeOS";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String OS_UNKNOWN = "unknown";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   public static String currentOs = null;
/*     */   
/*     */   public static String determineOS() {
/*  62 */     if (currentOs == null) {
/*  63 */       String osString = System.getProperty("os.name").trim().toLowerCase();
/*  64 */       if (osString.startsWith("windows")) {
/*  65 */         currentOs = "Windows";
/*  66 */       } else if (osString.startsWith("mac os x")) {
/*  67 */         currentOs = "Mac OS X";
/*  68 */       } else if (osString.startsWith("mac os")) {
/*  69 */         currentOs = "Mac OS 7-9";
/*  70 */       } else if (osString.startsWith("risc os")) {
/*  71 */         currentOs = "RISC OS";
/*     */       }
/*  73 */       else if ((osString.indexOf("linux") > -1) || 
/*  74 */         (osString.indexOf("debian") > -1) || 
/*  75 */         (osString.indexOf("redhat") > -1) || 
/*  76 */         (osString.indexOf("lindows") > -1)) {
/*  77 */         currentOs = "Linux";
/*     */       }
/*  79 */       else if ((osString.indexOf("freebsd") > -1) || 
/*  80 */         (osString.indexOf("openbsd") > -1) || 
/*  81 */         (osString.indexOf("netbsd") > -1) || 
/*  82 */         (osString.indexOf("irix") > -1) || 
/*  83 */         (osString.indexOf("solaris") > -1) || 
/*  84 */         (osString.indexOf("sunos") > -1) || 
/*  85 */         (osString.indexOf("hp/ux") > -1) || 
/*  86 */         (osString.indexOf("risc ix") > -1) || 
/*  87 */         (osString.indexOf("dg/ux") > -1)) {
/*  88 */         currentOs = "BSD";
/*  89 */       } else if (osString.indexOf("beos") > -1) {
/*  90 */         currentOs = "BeOS";
/*     */       } else {
/*  92 */         currentOs = "unknown";
/*     */       }
/*     */     }
/*  95 */     return currentOs;
/*     */   }
/*     */   
/*     */   public static boolean isRunningWindows() {
/*  99 */     return determineOS() == "Windows";
/*     */   }
/*     */   
/*     */   public static boolean isRunningMacOsX() {
/* 103 */     return determineOS() == "Mac OS X";
/*     */   }
/*     */   
/*     */   public static boolean isRunningLinux() {
/* 107 */     return determineOS() == "Linux";
/*     */   }
/*     */   
/*     */   public static boolean isRunningUnix() {
/* 111 */     String os = determineOS();
/* 112 */     if ((os == "BSD") || 
/* 113 */       (os == "Linux") || 
/* 114 */       (os == "Mac OS X")) {
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String stripHtmlForOsx(String menuText)
/*     */   {
/* 129 */     if (isRunningMacOsX()) {
/* 130 */       menuText = stripHtml(menuText);
/*     */     }
/* 132 */     return menuText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String stripHtml(String text)
/*     */   {
/* 142 */     return text.replaceAll("<[^<>]*>", "");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/RuntimeUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */