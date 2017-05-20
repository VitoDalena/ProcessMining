/*    */ package com.thoughtworks.xstream.converters.collections;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.Hashtable;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
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
/*    */ public class MapConverter
/*    */   extends AbstractCollectionConverter
/*    */ {
/*    */   public MapConverter(Mapper mapper)
/*    */   {
/* 40 */     super(mapper);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 44 */     return (type.equals(HashMap.class)) || (type.equals(Hashtable.class)) || (type.getName().equals("java.util.LinkedHashMap")) || (type.getName().equals("sun.font.AttributeMap"));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
/*    */   {
/* 52 */     Map map = (Map)source;
/* 53 */     for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
/* 54 */       Map.Entry entry = (Map.Entry)iterator.next();
/* 55 */       ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper().serializedClass(Map.Entry.class), Map.Entry.class);
/*    */       
/* 57 */       writeItem(entry.getKey(), context, writer);
/* 58 */       writeItem(entry.getValue(), context, writer);
/*    */       
/* 60 */       writer.endNode();
/*    */     }
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 65 */     Map map = (Map)createCollection(context.getRequiredType());
/* 66 */     populateMap(reader, context, map);
/* 67 */     return map;
/*    */   }
/*    */   
/*    */   protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {
/* 71 */     populateMap(reader, context, map, map);
/*    */   }
/*    */   
/*    */   protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map, Map target) {
/* 75 */     while (reader.hasMoreChildren()) {
/* 76 */       reader.moveDown();
/*    */       
/* 78 */       reader.moveDown();
/* 79 */       Object key = readItem(reader, context, map);
/* 80 */       reader.moveUp();
/*    */       
/* 82 */       reader.moveDown();
/* 83 */       Object value = readItem(reader, context, map);
/* 84 */       reader.moveUp();
/*    */       
/* 86 */       target.put(key, value);
/*    */       
/* 88 */       reader.moveUp();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/MapConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */