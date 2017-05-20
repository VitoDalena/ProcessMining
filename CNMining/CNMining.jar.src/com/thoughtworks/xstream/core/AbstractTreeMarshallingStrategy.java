/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.MarshallingStrategy;
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.converters.DataHolder;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*    */ public abstract class AbstractTreeMarshallingStrategy
/*    */   implements MarshallingStrategy
/*    */ {
/*    */   public Object unmarshal(Object root, HierarchicalStreamReader reader, DataHolder dataHolder, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 31 */     TreeUnmarshaller context = createUnmarshallingContext(root, reader, converterLookup, mapper);
/* 32 */     return context.start(dataHolder);
/*    */   }
/*    */   
/*    */   public void marshal(HierarchicalStreamWriter writer, Object obj, ConverterLookup converterLookup, Mapper mapper, DataHolder dataHolder) {
/* 36 */     TreeMarshaller context = createMarshallingContext(writer, converterLookup, mapper);
/* 37 */     context.start(obj, dataHolder);
/*    */   }
/*    */   
/*    */   protected abstract TreeUnmarshaller createUnmarshallingContext(Object paramObject, HierarchicalStreamReader paramHierarchicalStreamReader, ConverterLookup paramConverterLookup, Mapper paramMapper);
/*    */   
/*    */   protected abstract TreeMarshaller createMarshallingContext(HierarchicalStreamWriter paramHierarchicalStreamWriter, ConverterLookup paramConverterLookup, Mapper paramMapper);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/AbstractTreeMarshallingStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */