/*    */ package org.apache.commons.compress.compressors;
/*    */ 
/*    */ import java.io.InputStream;
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
/*    */ public abstract class CompressorInputStream
/*    */   extends InputStream
/*    */ {
/* 24 */   private int bytesRead = 0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void count(int read)
/*    */   {
/* 33 */     if (read != -1) {
/* 34 */       this.bytesRead += read;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getCount()
/*    */   {
/* 43 */     return this.bytesRead;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/CompressorInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */