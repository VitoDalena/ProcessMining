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
/*    */ public class IntConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 23 */     return (type.equals(Integer.TYPE)) || (type.equals(Integer.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 27 */     long value = Long.decode(str).longValue();
/* 28 */     if ((value < -2147483648L) || (value > 4294967295L)) {
/* 29 */       throw new NumberFormatException("For input string: \"" + str + '"');
/*    */     }
/* 31 */     return new Integer((int)value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/IntConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */