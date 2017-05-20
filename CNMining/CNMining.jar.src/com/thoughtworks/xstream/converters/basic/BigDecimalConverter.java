/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import java.math.BigDecimal;
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
/*    */ public class BigDecimalConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 25 */     return type.equals(BigDecimal.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 29 */     return new BigDecimal(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/BigDecimalConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */