/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
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
/*    */ public class ISO8601DateConverter
/*    */   extends ISO8601GregorianCalendarConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 28 */     return type.equals(Date.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 32 */     return ((Calendar)super.fromString(str)).getTime();
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 36 */     Calendar calendar = Calendar.getInstance();
/* 37 */     calendar.setTime((Date)obj);
/* 38 */     return super.toString(calendar);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ISO8601DateConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */