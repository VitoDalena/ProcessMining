/*    */ package com.thoughtworks.xstream.converters.collections;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.core.JVM;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Vector;
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
/*    */ public class CollectionConverter
/*    */   extends AbstractCollectionConverter
/*    */ {
/*    */   public CollectionConverter(Mapper mapper)
/*    */   {
/* 40 */     super(mapper);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 44 */     return (type.equals(ArrayList.class)) || (type.equals(HashSet.class)) || (type.equals(LinkedList.class)) || (type.equals(Vector.class)) || ((JVM.is14()) && (type.getName().equals("java.util.LinkedHashSet")));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
/*    */   {
/* 52 */     Collection collection = (Collection)source;
/* 53 */     for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
/* 54 */       Object item = iterator.next();
/* 55 */       writeItem(item, context, writer);
/*    */     }
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 60 */     Collection collection = (Collection)createCollection(context.getRequiredType());
/* 61 */     populateCollection(reader, context, collection);
/* 62 */     return collection;
/*    */   }
/*    */   
/*    */   protected void populateCollection(HierarchicalStreamReader reader, UnmarshallingContext context, Collection collection) {
/* 66 */     populateCollection(reader, context, collection, collection);
/*    */   }
/*    */   
/*    */   protected void populateCollection(HierarchicalStreamReader reader, UnmarshallingContext context, Collection collection, Collection target) {
/* 70 */     while (reader.hasMoreChildren()) {
/* 71 */       reader.moveDown();
/* 72 */       Object item = readItem(reader, context, collection);
/* 73 */       target.add(item);
/* 74 */       reader.moveUp();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/CollectionConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */