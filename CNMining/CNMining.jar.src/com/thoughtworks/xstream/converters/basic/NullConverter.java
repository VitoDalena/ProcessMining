/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper.Null;
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
/*    */ public class NullConverter
/*    */   implements Converter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 30 */     return (type == null) || (Mapper.Null.class.isAssignableFrom(type));
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 34 */     ExtendedHierarchicalStreamWriterHelper.startNode(writer, "null", null);
/* 35 */     writer.endNode();
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/NullConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */