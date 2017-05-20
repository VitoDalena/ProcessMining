/*    */ package org.apache.lucene.util;
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
/*    */ public abstract class StringHelper
/*    */ {
/*    */   public static final int stringDifference(String s1, String s2)
/*    */   {
/* 36 */     int len1 = s1.length();
/* 37 */     int len2 = s2.length();
/* 38 */     int len = len1 < len2 ? len1 : len2;
/* 39 */     for (int i = 0; i < len; i++) {
/* 40 */       if (s1.charAt(i) != s2.charAt(i)) {
/* 41 */         return i;
/*    */       }
/*    */     }
/* 44 */     return len;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/util/StringHelper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */