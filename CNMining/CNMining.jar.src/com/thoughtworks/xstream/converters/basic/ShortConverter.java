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
/*    */ public class ShortConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Short.TYPE)) || (type.equals(Short.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     int value = Integer.decode(str).intValue();
/* 28 */     if ((value < 32768) || (value > 65535)) {
/* 29 */       throw new NumberFormatException("For input string: \"" + str + '"');
/*    */     }
/* 31 */     return new Short((short)value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/ShortConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */