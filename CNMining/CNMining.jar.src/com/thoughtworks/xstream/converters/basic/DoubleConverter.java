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
/*    */ public class DoubleConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Double.TYPE)) || (type.equals(Double.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     return Double.valueOf(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/DoubleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */