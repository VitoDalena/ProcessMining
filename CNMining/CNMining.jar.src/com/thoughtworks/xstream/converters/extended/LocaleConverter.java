/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.util.Locale;
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
/*    */ public class LocaleConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 27 */     return type.equals(Locale.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 31 */     int[] underscorePositions = underscorePositions(str);
/*    */     String variant;
/* 33 */     String language; String country; String variant; if (underscorePositions[0] == -1) {
/* 34 */       String language = str;
/* 35 */       String country = "";
/* 36 */       variant = ""; } else { String variant;
/* 37 */       if (underscorePositions[1] == -1) {
/* 38 */         String language = str.substring(0, underscorePositions[0]);
/* 39 */         String country = str.substring(underscorePositions[0] + 1);
/* 40 */         variant = "";
/*    */       } else {
/* 42 */         language = str.substring(0, underscorePositions[0]);
/* 43 */         country = str.substring(underscorePositions[0] + 1, underscorePositions[1]);
/* 44 */         variant = str.substring(underscorePositions[1] + 1);
/*    */       } }
/* 46 */     return new Locale(language, country, variant);
/*    */   }
/*    */   
/*    */   private int[] underscorePositions(String in) {
/* 50 */     int[] result = new int[2];
/* 51 */     for (int i = 0; i < result.length; i++) {
/* 52 */       int last = i == 0 ? 0 : result[(i - 1)];
/* 53 */       result[i] = in.indexOf(95, last + 1);
/*    */     }
/* 55 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/LocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */