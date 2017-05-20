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
/*    */ public class ReferenceByXPathMarshallingStrategy
/*    */   extends AbstractTreeMarshallingStrategy
/*    */ {
/* 21 */   public static int RELATIVE = 0;
/* 22 */   public static int ABSOLUTE = 1;
/* 23 */   public static int SINGLE_NODE = 2;
/*    */   private final int mode;
/*    */   
/*    */   public ReferenceByXPathMarshallingStrategy(int mode) {
/* 27 */     this.mode = mode;
/*    */   }
/*    */   
/*    */   protected TreeUnmarshaller createUnmarshallingContext(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 32 */     return new ReferenceByXPathUnmarshaller(root, reader, converterLookup, mapper);
/*    */   }
/*    */   
/*    */   protected TreeMarshaller createMarshallingContext(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 37 */     return new ReferenceByXPathMarshaller(writer, converterLookup, mapper, this.mode);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferenceByXPathMarshallingStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */