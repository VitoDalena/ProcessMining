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
/*    */ public class LongConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Long.TYPE)) || (type.equals(Long.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     return Long.decode(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/LongConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */