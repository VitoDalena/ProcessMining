/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.JarMarker;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
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
/*    */ public class JarArchiveOutputStream
/*    */   extends ZipArchiveOutputStream
/*    */ {
/* 38 */   private boolean jarMarkerAdded = false;
/*    */   
/*    */   public JarArchiveOutputStream(OutputStream out) {
/* 41 */     super(out);
/*    */   }
/*    */   
/*    */   public void putArchiveEntry(ArchiveEntry ze) throws IOException
/*    */   {
/* 46 */     if (!this.jarMarkerAdded) {
/* 47 */       ((ZipArchiveEntry)ze).addAsFirstExtraField(JarMarker.getInstance());
/* 48 */       this.jarMarkerAdded = true;
/*    */     }
/* 50 */     super.putArchiveEntry(ze);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/jar/JarArchiveOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */