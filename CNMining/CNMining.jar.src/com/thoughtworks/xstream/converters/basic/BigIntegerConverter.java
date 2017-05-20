/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import java.math.BigInteger;
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
/*    */ public class BigIntegerConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 24 */     return type.equals(BigInteger.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 28 */     return new BigInteger(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/BigIntegerConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */