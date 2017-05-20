/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.nio.charset.Charset;
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
/*    */ public class CharsetConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 26 */     return Charset.class.isAssignableFrom(type);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 30 */     return obj == null ? null : ((Charset)obj).name();
/*    */   }
/*    */   
/*    */   public Object fromString(String str)
/*    */   {
/* 35 */     return Charset.forName(str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/CharsetConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */