/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Enumeration;
/*    */ import org.apache.lucene.document.Document;
/*    */ import org.apache.lucene.document.Field;
/*    */ import org.apache.lucene.store.Directory;
/*    */ import org.apache.lucene.store.OutputStream;
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
/*    */ final class FieldsWriter
/*    */ {
/*    */   private FieldInfos fieldInfos;
/*    */   private OutputStream fieldsStream;
/*    */   private OutputStream indexStream;
/*    */   
/*    */   FieldsWriter(Directory d, String segment, FieldInfos fn)
/*    */     throws IOException
/*    */   {
/* 34 */     this.fieldInfos = fn;
/* 35 */     this.fieldsStream = d.createFile(segment + ".fdt");
/* 36 */     this.indexStream = d.createFile(segment + ".fdx");
/*    */   }
/*    */   
/*    */   final void close() throws IOException {
/* 40 */     this.fieldsStream.close();
/* 41 */     this.indexStream.close();
/*    */   }
/*    */   
/*    */   final void addDocument(Document doc) throws IOException {
/* 45 */     this.indexStream.writeLong(this.fieldsStream.getFilePointer());
/*    */     
/* 47 */     int storedCount = 0;
/* 48 */     Enumeration fields = doc.fields();
/* 49 */     while (fields.hasMoreElements()) {
/* 50 */       Field field = (Field)fields.nextElement();
/* 51 */       if (field.isStored())
/* 52 */         storedCount++;
/*    */     }
/* 54 */     this.fieldsStream.writeVInt(storedCount);
/*    */     
/* 56 */     fields = doc.fields();
/* 57 */     while (fields.hasMoreElements()) {
/* 58 */       Field field = (Field)fields.nextElement();
/* 59 */       if (field.isStored()) {
/* 60 */         this.fieldsStream.writeVInt(this.fieldInfos.fieldNumber(field.name()));
/*    */         
/* 62 */         byte bits = 0;
/* 63 */         if (field.isTokenized())
/* 64 */           bits = (byte)(bits | 0x1);
/* 65 */         this.fieldsStream.writeByte(bits);
/*    */         
/* 67 */         this.fieldsStream.writeString(field.stringValue());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/FieldsWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */