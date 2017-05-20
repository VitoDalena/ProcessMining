/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
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
/*    */ 
/*    */ public abstract class AbstractSingleValueConverter
/*    */   implements SingleValueConverter
/*    */ {
/*    */   public abstract boolean canConvert(Class paramClass);
/*    */   
/*    */   public String toString(Object obj)
/*    */   {
/* 30 */     return obj == null ? null : obj.toString();
/*    */   }
/*    */   
/*    */   public abstract Object fromString(String paramString);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/AbstractSingleValueConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */