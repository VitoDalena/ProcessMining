/*    */ package com.thoughtworks.xstream.converters;
/*    */ 
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
/*    */ public class SingleValueConverterWrapper
/*    */   implements Converter, SingleValueConverter
/*    */ {
/*    */   private final SingleValueConverter wrapped;
/*    */   
/*    */   public SingleValueConverterWrapper(SingleValueConverter wrapped)
/*    */   {
/* 29 */     this.wrapped = wrapped;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 33 */     return this.wrapped.canConvert(type);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 37 */     return this.wrapped.toString(obj);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 41 */     return this.wrapped.fromString(str);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 45 */     writer.setValue(toString(source));
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 49 */     return fromString(reader.getValue());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/SingleValueConverterWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */