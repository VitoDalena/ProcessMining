/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
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
/*    */ public class URIConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 27 */     return type.equals(URI.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/*    */     try {
/* 32 */       return new URI(str);
/*    */     } catch (URISyntaxException e) {
/* 34 */       throw new ConversionException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/URIConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */