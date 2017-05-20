/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
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
/*    */ public class JarArchiveInputStream
/*    */   extends ZipArchiveInputStream
/*    */ {
/*    */   public JarArchiveInputStream(InputStream inputStream)
/*    */   {
/* 36 */     super(inputStream);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry getNextJarEntry() throws IOException {
/* 40 */     ZipArchiveEntry entry = getNextZipEntry();
/* 41 */     return entry == null ? null : new JarArchiveEntry(entry);
/*    */   }
/*    */   
/*    */   public ArchiveEntry getNextEntry() throws IOException {
/* 45 */     return getNextJarEntry();
/*    */   }
/*    */   
/*    */   public static boolean matches(byte[] signature, int length) {
/* 49 */     return ZipArchiveInputStream.matches(signature, length);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/jar/JarArchiveInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */