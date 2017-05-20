/*    */ package com.thoughtworks.xstream.converters.collections;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.util.BitSet;
/*    */ import java.util.StringTokenizer;
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
/*    */ public class BitSetConverter
/*    */   implements Converter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 32 */     return type.equals(BitSet.class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 36 */     BitSet bitSet = (BitSet)source;
/* 37 */     StringBuffer buffer = new StringBuffer();
/* 38 */     boolean seenFirst = false;
/* 39 */     for (int i = 0; i < bitSet.length(); i++) {
/* 40 */       if (bitSet.get(i)) {
/* 41 */         if (seenFirst) {
/* 42 */           buffer.append(',');
/*    */         } else {
/* 44 */           seenFirst = true;
/*    */         }
/* 46 */         buffer.append(i);
/*    */       }
/*    */     }
/* 49 */     writer.setValue(buffer.toString());
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 53 */     BitSet result = new BitSet();
/* 54 */     StringTokenizer tokenizer = new StringTokenizer(reader.getValue(), ",", false);
/* 55 */     while (tokenizer.hasMoreTokens()) {
/* 56 */       int index = Integer.parseInt(tokenizer.nextToken());
/* 57 */       result.set(index);
/*    */     }
/* 59 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/BitSetConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */