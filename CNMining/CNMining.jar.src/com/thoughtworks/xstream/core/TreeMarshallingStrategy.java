/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
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
/*    */ public class TreeMarshallingStrategy
/*    */   extends AbstractTreeMarshallingStrategy
/*    */ {
/*    */   protected TreeUnmarshaller createUnmarshallingContext(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 23 */     return new TreeUnmarshaller(root, reader, converterLookup, mapper);
/*    */   }
/*    */   
/*    */   protected TreeMarshaller createMarshallingContext(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 28 */     return new TreeMarshaller(writer, converterLookup, mapper);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/TreeMarshallingStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */