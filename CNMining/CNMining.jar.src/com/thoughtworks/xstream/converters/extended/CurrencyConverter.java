/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.util.Currency;
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
/*    */ 
/*    */ 
/*    */ public class CurrencyConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 28 */     return type.equals(Currency.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 32 */     return Currency.getInstance(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/CurrencyConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */