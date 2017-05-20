/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
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
/*    */ public class SelfStreamingInstanceChecker
/*    */   implements Converter
/*    */ {
/*    */   private final Object self;
/*    */   private Converter defaultConverter;
/*    */   
/*    */   public SelfStreamingInstanceChecker(Converter defaultConverter, Object xstream)
/*    */   {
/* 34 */     this.defaultConverter = defaultConverter;
/* 35 */     this.self = xstream;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 39 */     return type == this.self.getClass();
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 43 */     if (source == this.self) {
/* 44 */       throw new ConversionException("Cannot marshal the XStream instance in action");
/*    */     }
/* 46 */     this.defaultConverter.marshal(source, writer, context);
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 50 */     return this.defaultConverter.unmarshal(reader, context);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/SelfStreamingInstanceChecker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */