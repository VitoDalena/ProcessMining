/*    */ package org.deckfour.uitopia.ui.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeUtils
/*    */ {
/*    */   private static final long SECOND = 1000L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private static final long MINUTE = 60000L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private static final long HOUR = 3600000L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private static final long DAY = 86400000L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final String ageToString(long millis)
/*    */   {
/* 47 */     if (millis <= 0L) {
/* 48 */       return "just created";
/*    */     }
/* 50 */     int days = (int)(millis / 86400000L);
/* 51 */     millis %= 86400000L;
/* 52 */     int hours = (int)(millis / 3600000L);
/* 53 */     millis %= 3600000L;
/* 54 */     int minutes = (int)(millis / 60000L);
/* 55 */     millis %= 60000L;
/* 56 */     int seconds = (int)(millis / 1000L);
/*    */     
/* 58 */     if (days > 0)
/* 59 */       return "created about " + days + " days ago";
/* 60 */     if (hours > 0)
/* 61 */       return "created about " + hours + " hours ago";
/* 62 */     if (minutes > 1)
/* 63 */       return "created " + (minutes + 1) + " minutes ago";
/* 64 */     if (minutes == 1)
/* 65 */       return "created about a minute ago";
/* 66 */     if (seconds > 45)
/* 67 */       return "created less than a minute ago";
/* 68 */     if (seconds > 0) {
/* 69 */       int remainingTens = (seconds / 5 + 1) * 5;
/* 70 */       return "created less than " + remainingTens + " seconds ago";
/*    */     }
/* 72 */     return "just created";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/TimeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */