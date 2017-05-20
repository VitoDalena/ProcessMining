/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import java.sql.Timestamp;
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
/*    */ public class ISO8601SqlTimestampConverter
/*    */   extends ISO8601DateConverter
/*    */ {
/*    */   private static final String PADDING = "000000000";
/*    */   
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 30 */     return type.equals(Timestamp.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 34 */     int idxFraction = str.lastIndexOf('.');
/* 35 */     int nanos = 0;
/* 36 */     if (idxFraction > 0)
/*    */     {
/* 38 */       for (int idx = idxFraction + 1; Character.isDigit(str.charAt(idx)); idx++) {}
/*    */       
/* 40 */       nanos = Integer.parseInt(str.substring(idxFraction + 1, idx));
/* 41 */       str = str.substring(0, idxFraction) + str.substring(idx);
/*    */     }
/* 43 */     Date date = (Date)super.fromString(str);
/* 44 */     Timestamp timestamp = new Timestamp(date.getTime());
/* 45 */     timestamp.setNanos(nanos);
/* 46 */     return timestamp;
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 50 */     Timestamp timestamp = (Timestamp)obj;
/* 51 */     String str = super.toString(new Date(timestamp.getTime() / 1000L * 1000L));
/* 52 */     String nanos = String.valueOf(timestamp.getNanos());
/* 53 */     int idxFraction = str.lastIndexOf('.');
/* 54 */     str = str.substring(0, idxFraction + 1) + "000000000".substring(nanos.length()) + nanos + str.substring(idxFraction + 4);
/*    */     
/*    */ 
/*    */ 
/* 58 */     return str;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ISO8601SqlTimestampConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */