/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
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
/*     */ public class NikeFS2SwapFileManager
/*     */ {
/*  60 */   private static final File TMP_DIR = new File(System.getProperty("java.io.tmpdir"));
/*     */   
/*     */ 
/*     */ 
/*  64 */   private static File SWAP_DIR = null;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String SWAP_DIR_PREFIX = "NIKEFS_SWAP_";
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String SWAP_DIR_SUFFIX = "_SWAPDIR";
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String LOCK_FILE_SUFFIX = ".LOCK";
/*     */   
/*     */ 
/*     */   private static final String SWAP_FILE_PREFIX = "NIKEFS_SWAP_";
/*     */   
/*     */ 
/*     */   private static final String SWAP_FILE_SUFFIX = ".SWAP2";
/*     */   
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*  88 */     cleanup();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized File createSwapFile()
/*     */     throws IOException
/*     */   {
/* 101 */     File swapFile = createSwapFile("NIKEFS_SWAP_", ".SWAP2");
/*     */     
/* 103 */     swapFile.deleteOnExit();
/* 104 */     return swapFile;
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
/*     */   public static synchronized File createSwapFile(String prefix, String suffix)
/*     */     throws IOException
/*     */   {
/* 118 */     File swapDir = getSwapDir();
/* 119 */     File tmpFile = File.createTempFile(prefix, suffix, swapDir);
/* 120 */     return tmpFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static synchronized File getSwapDir()
/*     */     throws IOException
/*     */   {
/* 129 */     if (SWAP_DIR == null)
/*     */     {
/* 131 */       File swapDir = File.createTempFile("NIKEFS_SWAP_", "_SWAPDIR", TMP_DIR);
/*     */       
/* 133 */       swapDir.delete();
/*     */       
/* 135 */       File lockFile = new File(TMP_DIR, swapDir.getName() + ".LOCK");
/* 136 */       lockFile.createNewFile();
/*     */       
/*     */ 
/* 139 */       lockFile.deleteOnExit();
/*     */       
/* 141 */       swapDir.mkdirs();
/*     */       
/* 143 */       swapDir.deleteOnExit();
/* 144 */       SWAP_DIR = swapDir;
/*     */     }
/* 146 */     return SWAP_DIR;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static synchronized void cleanup()
/*     */   {
/* 155 */     int cleanedDirs = 0;
/* 156 */     int cleanedFiles = 0;
/*     */     
/* 158 */     File[] swapDirs = TMP_DIR.listFiles(new FileFilter() {
/*     */       public boolean accept(File pathname) {
/* 160 */         return pathname.getName().endsWith("_SWAPDIR");
/*     */       }
/*     */     });
/* 163 */     for (File swapDir : swapDirs)
/*     */     {
/* 165 */       File lockFile = new File(TMP_DIR, swapDir.getName() + ".LOCK");
/* 166 */       if (!lockFile.exists())
/*     */       {
/* 168 */         cleanedDirs++;
/* 169 */         cleanedFiles += deleteRecursively(swapDir);
/*     */       }
/*     */     }
/* 172 */     System.out.println("NikeFS2: cleaned up " + cleanedFiles + " stale swap files (from " + cleanedDirs + " sessions).");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static synchronized int deleteRecursively(File directory)
/*     */   {
/* 182 */     int deleted = 0;
/* 183 */     if (directory.isDirectory()) {
/* 184 */       File[] contents = directory.listFiles();
/* 185 */       for (File file : contents) {
/* 186 */         deleted += deleteRecursively(file);
/*     */       }
/*     */     } else {
/* 189 */       deleted++;
/*     */     }
/* 191 */     directory.delete();
/* 192 */     return deleted;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2SwapFileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */