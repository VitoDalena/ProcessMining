/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
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
/*    */ public class CharConverter
/*    */   implements Converter, SingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 31 */     return (type.equals(Character.TYPE)) || (type.equals(Character.class));
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 35 */     writer.setValue(toString(source));
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 39 */     String nullAttribute = reader.getAttribute("null");
/* 40 */     if ((nullAttribute != null) && (nullAttribute.equals("true"))) {
/* 41 */       return new Character('\000');
/*    */     }
/* 43 */     return fromString(reader.getValue());
/*    */   }
/*    */   
/*    */   public Object fromString(String str)
/*    */   {
/* 48 */     if (str.length() == 0) {
/* 49 */       return new Character('\000');
/*    */     }
/* 51 */     return new Character(str.charAt(0));
/*    */   }
/*    */   
/*    */   public String toString(Object obj)
/*    */   {
/* 56 */     char ch = ((Character)obj).charValue();
/* 57 */     return ch == 0 ? "" : obj.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/CharConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */