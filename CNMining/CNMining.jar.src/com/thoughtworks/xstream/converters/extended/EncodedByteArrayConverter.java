/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.basic.ByteConverter;
/*    */ import com.thoughtworks.xstream.core.util.Base64Encoder;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
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
/*    */ public class EncodedByteArrayConverter
/*    */   implements Converter, SingleValueConverter
/*    */ {
/* 35 */   private static final Base64Encoder base64 = new Base64Encoder();
/* 36 */   private static final ByteConverter byteConverter = new ByteConverter();
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 39 */     return (type.isArray()) && (type.getComponentType().equals(Byte.TYPE));
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 43 */     writer.setValue(toString(source));
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 47 */     String data = reader.getValue();
/* 48 */     if (!reader.hasMoreChildren()) {
/* 49 */       return fromString(data);
/*    */     }
/*    */     
/* 52 */     return unmarshalIndividualByteElements(reader, context);
/*    */   }
/*    */   
/*    */   private Object unmarshalIndividualByteElements(HierarchicalStreamReader reader, UnmarshallingContext context)
/*    */   {
/* 57 */     List bytes = new ArrayList();
/* 58 */     boolean firstIteration = true;
/* 59 */     while ((firstIteration) || (reader.hasMoreChildren())) {
/* 60 */       reader.moveDown();
/* 61 */       bytes.add(byteConverter.fromString(reader.getValue()));
/* 62 */       reader.moveUp();
/* 63 */       firstIteration = false;
/*    */     }
/*    */     
/* 66 */     byte[] result = new byte[bytes.size()];
/* 67 */     int i = 0;
/* 68 */     for (Iterator iterator = bytes.iterator(); iterator.hasNext();) {
/* 69 */       Byte b = (Byte)iterator.next();
/* 70 */       result[i] = b.byteValue();
/* 71 */       i++;
/*    */     }
/* 73 */     return result;
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 77 */     return base64.encode((byte[])obj);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 81 */     return base64.decode(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/EncodedByteArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */