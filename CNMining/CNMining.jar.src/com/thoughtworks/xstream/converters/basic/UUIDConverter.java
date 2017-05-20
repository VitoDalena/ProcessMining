/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import java.util.UUID;
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
/*    */ public class UUIDConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 26 */     return type.equals(UUID.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/*    */     try {
/* 31 */       return UUID.fromString(str);
/*    */     } catch (IllegalArgumentException e) {
/* 33 */       throw new ConversionException("Cannot create UUID instance", e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/UUIDConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */