/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
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
/*    */ public class URLConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 27 */     return type.equals(URL.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/*    */     try {
/* 32 */       return new URL(str);
/*    */     } catch (MalformedURLException e) {
/* 34 */       throw new ConversionException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/URLConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */