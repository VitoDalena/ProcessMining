/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
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
/*    */ public class ThrowableConverter
/*    */   implements Converter
/*    */ {
/*    */   private Converter defaultConverter;
/*    */   
/*    */   public ThrowableConverter(Converter defaultConverter)
/*    */   {
/* 31 */     this.defaultConverter = defaultConverter;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 35 */     return Throwable.class.isAssignableFrom(type);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 39 */     Throwable throwable = (Throwable)source;
/* 40 */     if (throwable.getCause() == null) {
/*    */       try {
/* 42 */         throwable.initCause(null);
/*    */       }
/*    */       catch (IllegalStateException e) {}
/*    */     }
/*    */     
/* 47 */     throwable.getStackTrace();
/* 48 */     this.defaultConverter.marshal(throwable, writer, context);
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 52 */     return this.defaultConverter.unmarshal(reader, context);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ThrowableConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */