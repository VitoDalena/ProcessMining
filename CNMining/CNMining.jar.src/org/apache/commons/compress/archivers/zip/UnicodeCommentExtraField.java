/*    */ package org.apache.commons.compress.archivers.zip;
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
/*    */ public class UnicodeCommentExtraField
/*    */   extends AbstractUnicodeExtraField
/*    */ {
/* 40 */   public static final ZipShort UCOM_ID = new ZipShort(25461);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnicodeCommentExtraField() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnicodeCommentExtraField(String text, byte[] bytes, int off, int len)
/*    */   {
/* 57 */     super(text, bytes, off, len);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnicodeCommentExtraField(String comment, byte[] bytes)
/*    */   {
/* 68 */     super(comment, bytes);
/*    */   }
/*    */   
/*    */   public ZipShort getHeaderId() {
/* 72 */     return UCOM_ID;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/UnicodeCommentExtraField.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */