/*    */ package org.apache.lucene.document;
/*    */ 
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
/*    */ 
/*    */ public class DateField
/*    */ {
/* 44 */   private static int DATE_LEN = Long.toString(31536000000000L, 36).length();
/*    */   
/*    */   public static String MIN_DATE_STRING()
/*    */   {
/* 48 */     return timeToString(0L);
/*    */   }
/*    */   
/*    */   public static String MAX_DATE_STRING() {
/* 52 */     char[] buffer = new char[DATE_LEN];
/* 53 */     char c = Character.forDigit(35, 36);
/* 54 */     for (int i = 0; i < DATE_LEN; i++)
/* 55 */       buffer[i] = c;
/* 56 */     return new String(buffer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String dateToString(Date date)
/*    */   {
/* 65 */     return timeToString(date.getTime());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String timeToString(long time)
/*    */   {
/* 73 */     if (time < 0L) {
/* 74 */       throw new RuntimeException("time too early");
/*    */     }
/* 76 */     String s = Long.toString(time, 36);
/*    */     
/* 78 */     if (s.length() > DATE_LEN) {
/* 79 */       throw new RuntimeException("time too late");
/*    */     }
/*    */     
/* 82 */     if (s.length() < DATE_LEN) {
/* 83 */       StringBuffer sb = new StringBuffer(s);
/* 84 */       while (sb.length() < DATE_LEN)
/* 85 */         sb.insert(0, 0);
/* 86 */       s = sb.toString();
/*    */     }
/*    */     
/* 89 */     return s;
/*    */   }
/*    */   
/*    */   public static long stringToTime(String s)
/*    */   {
/* 94 */     return Long.parseLong(s, 36);
/*    */   }
/*    */   
/*    */   public static Date stringToDate(String s) {
/* 98 */     return new Date(stringToTime(s));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/document/DateField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */