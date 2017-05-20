/*     */ package org.deckfour.uitopia.ui.util;
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
/*     */ public class RuntimeUtils
/*     */ {
/*     */   public static enum OS
/*     */   {
/*  58 */     WIN32, 
/*  59 */     MACOSX, 
/*  60 */     MACOSCLASSIC, 
/*  61 */     LINUX, 
/*  62 */     BSD, 
/*  63 */     RISCOS, 
/*  64 */     BEOS, 
/*  65 */     UNKNOWN;
/*     */     
/*     */ 
/*     */     private OS() {}
/*     */   }
/*     */   
/*  71 */   public static OS currentOs = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static OS determineOS()
/*     */   {
/*  79 */     if (currentOs == null) {
/*  80 */       String osString = System.getProperty("os.name").trim().toLowerCase();
/*  81 */       if (osString.startsWith("windows")) {
/*  82 */         currentOs = OS.WIN32;
/*  83 */       } else if (osString.startsWith("mac os x")) {
/*  84 */         currentOs = OS.MACOSX;
/*  85 */       } else if (osString.startsWith("mac os")) {
/*  86 */         currentOs = OS.MACOSCLASSIC;
/*  87 */       } else if (osString.startsWith("risc os")) {
/*  88 */         currentOs = OS.RISCOS;
/*  89 */       } else if ((osString.indexOf("linux") > -1) || (osString.indexOf("debian") > -1) || (osString.indexOf("redhat") > -1) || (osString.indexOf("lindows") > -1))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  94 */         currentOs = OS.LINUX;
/*  95 */       } else if ((osString.indexOf("freebsd") > -1) || (osString.indexOf("openbsd") > -1) || (osString.indexOf("netbsd") > -1) || (osString.indexOf("irix") > -1) || (osString.indexOf("solaris") > -1) || (osString.indexOf("sunos") > -1) || (osString.indexOf("hp/ux") > -1) || (osString.indexOf("risc ix") > -1) || (osString.indexOf("dg/ux") > -1))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 105 */         currentOs = OS.BSD;
/* 106 */       } else if (osString.indexOf("beos") > -1) {
/* 107 */         currentOs = OS.BEOS;
/*     */       } else {
/* 109 */         currentOs = OS.UNKNOWN;
/*     */       }
/*     */     }
/* 112 */     return currentOs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningWindows()
/*     */   {
/* 119 */     return determineOS().equals(OS.WIN32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningMacOsX()
/*     */   {
/* 126 */     return determineOS().equals(OS.MACOSX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningLinux()
/*     */   {
/* 133 */     return determineOS().equals(OS.LINUX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isRunningUnix()
/*     */   {
/* 140 */     OS os = determineOS();
/* 141 */     if ((os.equals(OS.BSD)) || (os.equals(OS.LINUX)) || (os.equals(OS.MACOSX)))
/*     */     {
/*     */ 
/* 144 */       return true;
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getSupportFolder()
/*     */   {
/* 155 */     String homedir = System.getProperty("user.home");
/* 156 */     String dirName = "UITopia";
/* 157 */     if (isRunningWindows())
/*     */     {
/* 159 */       new File(homedir + "\\" + dirName).mkdirs();
/* 160 */       return homedir + "\\" + dirName + "\\"; }
/* 161 */     if (isRunningMacOsX())
/*     */     {
/* 163 */       new File(homedir + "/Library/Application Support/" + dirName).mkdirs();
/* 164 */       return homedir + "/Library/Application Support/" + dirName + "/";
/*     */     }
/*     */     
/* 167 */     new File(homedir + "/." + dirName).mkdirs();
/* 168 */     return homedir + "/." + dirName + "/";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/RuntimeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */