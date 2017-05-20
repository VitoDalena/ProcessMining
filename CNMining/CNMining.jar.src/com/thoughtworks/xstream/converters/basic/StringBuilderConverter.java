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
/*    */ public class StringBuilderConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public Object fromString(String str)
/*    */   {
/* 21 */     return new StringBuilder(str);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 25 */     return type.equals(StringBuilder.class);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/StringBuilderConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */