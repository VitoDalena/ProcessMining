/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.security.cert.Certificate;
/*    */ import java.util.jar.Attributes;
/*    */ import java.util.jar.JarEntry;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipException;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
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
/*    */ public class JarArchiveEntry
/*    */   extends ZipArchiveEntry
/*    */   implements ArchiveEntry
/*    */ {
/* 36 */   private Attributes manifestAttributes = null;
/* 37 */   private Certificate[] certificates = null;
/*    */   
/*    */   public JarArchiveEntry(ZipEntry entry) throws ZipException {
/* 40 */     super(entry);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(String name) {
/* 44 */     super(name);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(ZipArchiveEntry entry) throws ZipException {
/* 48 */     super(entry);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(JarEntry entry) throws ZipException {
/* 52 */     super(entry);
/*    */   }
/*    */   
/*    */   public Attributes getManifestAttributes()
/*    */   {
/* 57 */     return this.manifestAttributes;
/*    */   }
/*    */   
/*    */   public Certificate[] getCertificates() {
/* 61 */     if (this.certificates != null) {
/* 62 */       Certificate[] certs = new Certificate[this.certificates.length];
/* 63 */       System.arraycopy(this.certificates, 0, certs, 0, certs.length);
/* 64 */       return certs;
/*    */     }
/* 66 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 73 */     return super.equals(obj);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 80 */     return super.hashCode();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/jar/JarArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */