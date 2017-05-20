/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.document.Document;
/*    */ import org.apache.lucene.document.Field;
/*    */ import org.apache.lucene.store.Directory;
/*    */ import org.apache.lucene.store.InputStream;
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
/*    */ final class FieldsReader
/*    */ {
/*    */   private FieldInfos fieldInfos;
/*    */   private InputStream fieldsStream;
/*    */   private InputStream indexStream;
/*    */   private int size;
/*    */   
/*    */   FieldsReader(Directory d, String segment, FieldInfos fn)
/*    */     throws IOException
/*    */   {
/* 40 */     this.fieldInfos = fn;
/*    */     
/* 42 */     this.fieldsStream = d.openFile(segment + ".fdt");
/* 43 */     this.indexStream = d.openFile(segment + ".fdx");
/*    */     
/* 45 */     this.size = ((int)(this.indexStream.length() / 8L));
/*    */   }
/*    */   
/*    */   final void close() throws IOException {
/* 49 */     this.fieldsStream.close();
/* 50 */     this.indexStream.close();
/*    */   }
/*    */   
/*    */   final int size() {
/* 54 */     return this.size;
/*    */   }
/*    */   
/*    */   final Document doc(int n) throws IOException {
/* 58 */     this.indexStream.seek(n * 8L);
/* 59 */     long position = this.indexStream.readLong();
/* 60 */     this.fieldsStream.seek(position);
/*    */     
/* 62 */     Document doc = new Document();
/* 63 */     int numFields = this.fieldsStream.readVInt();
/* 64 */     for (int i = 0; i < numFields; i++) {
/* 65 */       int fieldNumber = this.fieldsStream.readVInt();
/* 66 */       FieldInfo fi = this.fieldInfos.fieldInfo(fieldNumber);
/*    */       
/* 68 */       byte bits = this.fieldsStream.readByte();
/*    */       
/* 70 */       doc.add(new Field(fi.name, this.fieldsStream.readString(), true, fi.isIndexed, (bits & 0x1) != 0, fi.storeTermVector));
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 77 */     return doc;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/FieldsReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */