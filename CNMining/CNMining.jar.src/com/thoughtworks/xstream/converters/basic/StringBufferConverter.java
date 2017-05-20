/*    */ package com.thoughtworks.xstream.converters.basic;
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
/*    */ public class StringBufferConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public Object fromString(String str)
/*    */   {
/* 22 */     return new StringBuffer(str);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 26 */     return type.equals(StringBuffer.class);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/StringBufferConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */