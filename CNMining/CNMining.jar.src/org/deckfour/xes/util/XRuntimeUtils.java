/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ import java.io.File;
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
/*     */ public class XRuntimeUtils
/*     */ {
/*     */   public static final String XES_VERSION = "1.0";
/*     */   public static final String OPENXES_VERSION = "1.0RC7";
/*     */   
/*     */   public static enum OS
/*     */   {
/*  71 */     WIN32, 
/*  72 */     MACOSX, 
/*  73 */     MACOSCLASSIC, 
/*  74 */     LINUX, 
/*  75 */     BSD, 
/*  76 */     RISCOS, 
/*  77 */     BEOS, 
/*  78 */     UNKNOWN;
/*     */     
/*     */ 
/*     */     private OS() {}
/*     */   }
/*     */   
/*  84 */   public static OS currentOs = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static OS determineOS()
/*     */   {
/*  92 */     if (currentOs == null) {
/*  93 */       String osString = System.getProperty("os.name").trim().toLowerCase();
/*  94 */       if (osString.startsWith("windows")) {
/*  95 */         currentOs = OS.WIN32;
/*  96 */       } else if (osString.startsWith("mac os x")) {
/*  97 */         currentOs = OS.MACOSX;
/*  98 */       } else if (osString.startsWith("mac os")) {
/*  99 */         currentOs = OS.MACOSCLASSIC;
/* 100 */       } else if (osString.startsWith("risc os")) {
/* 101 */         currentOs = OS.RISCOS;
/* 102 */       } else if ((osString.indexOf("linux") > -1) || (osString.indexOf("debian") > -1) || (osString.indexOf("redhat") > -1) || (osString.indexOf("lindows") > -1))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 107 */         currentOs = OS.LINUX;
/* 108 */       } else if ((osString.indexOf("freebsd") > -1) || (osString.indexOf("openbsd") > -1) || (osString.indexOf("netbsd") > -1) || (osString.indexOf("irix") > -1) || (osString.indexOf("solaris") > -1) || (osString.indexOf("sunos") > -1) || (osString.indexOf("hp/ux") > -1) || (osString.indexOf("risc ix") > -1) || (osString.indexOf("dg/ux") > -1))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 118 */         currentOs = OS.BSD;
/* 119 */       } else if (osString.indexOf("beos") > -1) {
/* 120 */         currentOs = OS.BEOS;
/*     */       } else {
/* 122 */         currentOs = OS.UNKNOWN;
/*     */       }
/*     */     }
/* 125 */     return currentOs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningWindows()
/*     */   {
/* 132 */     return determineOS().equals(OS.WIN32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningMacOsX()
/*     */   {
/* 139 */     return determineOS().equals(OS.MACOSX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningLinux()
/*     */   {
/* 146 */     return determineOS().equals(OS.LINUX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningUnix()
/*     */   {
/* 153 */     OS os = determineOS();
/* 154 */     if ((os.equals(OS.BSD)) || (os.equals(OS.LINUX)) || (os.equals(OS.MACOSX)))
/*     */     {
/*     */ 
/* 157 */       return true;
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getSupportFolder()
/*     */   {
/* 168 */     String homedir = System.getProperty("user.home");
/* 169 */     String dirName = "OpenXES";
/* 170 */     if (isRunningWindows())
/*     */     {
/* 172 */       new File(homedir + "\\" + dirName).mkdirs();
/* 173 */       return homedir + "\\" + dirName + "\\"; }
/* 174 */     if (isRunningMacOsX())
/*     */     {
/* 176 */       new File(homedir + "/Library/Application Support/" + dirName).mkdirs();
/* 177 */       return homedir + "/Library/Application Support/" + dirName + "/";
/*     */     }
/*     */     
/* 180 */     new File(homedir + "/." + dirName).mkdirs();
/* 181 */     return homedir + "/." + dirName + "/";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File getExtensionCacheFolder()
/*     */   {
/* 190 */     File extFolder = new File(getSupportFolder() + "ExtensionCache");
/* 191 */     extFolder.mkdirs();
/* 192 */     return extFolder;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XRuntimeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */