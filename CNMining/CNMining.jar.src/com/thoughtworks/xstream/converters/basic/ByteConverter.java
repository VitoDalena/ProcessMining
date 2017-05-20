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
/*    */ public class ByteConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Byte.TYPE)) || (type.equals(Byte.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     int value = Integer.decode(str).intValue();
/* 28 */     if ((value < -128) || (value > 255)) {
/* 29 */       throw new NumberFormatException("For input string: \"" + str + '"');
/*    */     }
/* 31 */     return new Byte((byte)value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/ByteConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */