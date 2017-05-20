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
/*    */ 
/*    */ public class FloatConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Float.TYPE)) || (type.equals(Float.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     return Float.valueOf(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/FloatConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */