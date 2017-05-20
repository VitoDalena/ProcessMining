/*    */ package org.jfree.util;
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
/*    */ public class StringUtils
/*    */ {
/*    */   public static boolean startsWithIgnoreCase(String base, String start)
/*    */   {
/* 67 */     if (base.length() < start.length()) {
/* 68 */       return false;
/*    */     }
/* 70 */     return base.regionMatches(true, 0, start, 0, start.length());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean endsWithIgnoreCase(String base, String end)
/*    */   {
/* 82 */     if (base.length() < end.length()) {
/* 83 */       return false;
/*    */     }
/* 85 */     return base.regionMatches(true, base.length() - end.length(), end, 0, end.length());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String getLineSeparator()
/*    */   {
/*    */     try
/*    */     {
/* 96 */       return System.getProperty("line.separator", "\n");
/*    */     }
/*    */     catch (Exception e) {}
/* 99 */     return "\n";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/util/StringUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */