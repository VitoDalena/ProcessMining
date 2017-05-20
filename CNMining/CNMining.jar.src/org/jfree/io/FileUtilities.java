/*    */ package org.jfree.io;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileUtilities
/*    */ {
/*    */   public static File findFileOnClassPath(String name)
/*    */   {
/* 74 */     String classpath = System.getProperty("java.class.path");
/* 75 */     String pathSeparator = System.getProperty("path.separator");
/*    */     
/* 77 */     StringTokenizer tokenizer = new StringTokenizer(classpath, pathSeparator);
/*    */     
/* 79 */     while (tokenizer.hasMoreTokens()) {
/* 80 */       String pathElement = tokenizer.nextToken();
/*    */       
/* 82 */       File directoryOrJar = new File(pathElement);
/* 83 */       File absoluteDirectoryOrJar = directoryOrJar.getAbsoluteFile();
/*    */       
/* 85 */       if (absoluteDirectoryOrJar.isFile()) {
/* 86 */         File target = new File(absoluteDirectoryOrJar.getParent(), name);
/* 87 */         if (target.exists()) {
/* 88 */           return target;
/*    */         }
/*    */       }
/*    */       else {
/* 92 */         File target = new File(directoryOrJar, name);
/* 93 */         if (target.exists()) {
/* 94 */           return target;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 99 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/io/FileUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */