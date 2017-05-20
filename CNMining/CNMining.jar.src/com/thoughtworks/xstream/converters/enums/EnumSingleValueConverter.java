/*    */ package com.thoughtworks.xstream.converters.enums;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
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
/*    */ public class EnumSingleValueConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private final Class<? extends Enum> enumType;
/*    */   
/*    */   public EnumSingleValueConverter(Class<? extends Enum> type)
/*    */   {
/* 28 */     if ((!Enum.class.isAssignableFrom(type)) && (type != Enum.class)) {
/* 29 */       throw new IllegalArgumentException("Converter can only handle defined enums");
/*    */     }
/* 31 */     this.enumType = type;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 36 */     return this.enumType.isAssignableFrom(type);
/*    */   }
/*    */   
/*    */   public String toString(Object obj)
/*    */   {
/* 41 */     return ((Enum)Enum.class.cast(obj)).name();
/*    */   }
/*    */   
/*    */ 
/*    */   public Object fromString(String str)
/*    */   {
/* 47 */     Enum result = Enum.valueOf(this.enumType, str);
/* 48 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/enums/EnumSingleValueConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */