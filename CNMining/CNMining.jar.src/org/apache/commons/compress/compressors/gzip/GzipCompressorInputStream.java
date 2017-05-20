/*    */ package org.apache.commons.compress.compressors.gzip;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import org.apache.commons.compress.compressors.CompressorInputStream;
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
/*    */ public class GzipCompressorInputStream
/*    */   extends CompressorInputStream
/*    */ {
/*    */   private final GZIPInputStream in;
/*    */   
/*    */   public GzipCompressorInputStream(InputStream inputStream)
/*    */     throws IOException
/*    */   {
/* 43 */     this.in = new GZIPInputStream(inputStream);
/*    */   }
/*    */   
/*    */ 
/*    */   public int read()
/*    */     throws IOException
/*    */   {
/* 50 */     count(1);
/* 51 */     return this.in.read();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/gzip/GzipCompressorInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */