/*    */ package org.apache.commons.compress.compressors.gzip;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import org.apache.commons.compress.compressors.CompressorOutputStream;
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
/*    */ public class GzipCompressorOutputStream
/*    */   extends CompressorOutputStream
/*    */ {
/*    */   private final GZIPOutputStream out;
/*    */   
/*    */   public GzipCompressorOutputStream(OutputStream outputStream)
/*    */     throws IOException
/*    */   {
/* 32 */     this.out = new GZIPOutputStream(outputStream);
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 36 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 40 */     this.out.close();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/gzip/GzipCompressorOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */