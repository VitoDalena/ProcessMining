/*    */ package com.thoughtworks.xstream.converters.collections;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.lang.reflect.Array;
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
/*    */ public class ArrayConverter
/*    */   extends AbstractCollectionConverter
/*    */ {
/*    */   public ArrayConverter(Mapper mapper)
/*    */   {
/* 34 */     super(mapper);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 38 */     return type.isArray();
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 42 */     int length = Array.getLength(source);
/* 43 */     for (int i = 0; i < length; i++) {
/* 44 */       Object item = Array.get(source, i);
/* 45 */       writeItem(item, context, writer);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*    */   {
/* 52 */     List items = new ArrayList();
/* 53 */     while (reader.hasMoreChildren()) {
/* 54 */       reader.moveDown();
/* 55 */       Object item = readItem(reader, context, null);
/* 56 */       items.add(item);
/* 57 */       reader.moveUp();
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 62 */     Object array = Array.newInstance(context.getRequiredType().getComponentType(), items.size());
/* 63 */     int i = 0;
/* 64 */     for (Iterator iterator = items.iterator(); iterator.hasNext();) {
/* 65 */       Array.set(array, i++, iterator.next());
/*    */     }
/* 67 */     return array;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/ArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */