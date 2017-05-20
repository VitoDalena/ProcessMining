/*    */ package org.jfree.chart.urls;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.net.URLEncoder;
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
/*    */ public class URLUtilities
/*    */ {
/* 55 */   private static final Class[] STRING_ARGS_2 = { String.class, String.class };
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
/*    */   public static String encode(String s, String encoding)
/*    */   {
/* 71 */     Class c = URLEncoder.class;
/* 72 */     String result = null;
/*    */     try {
/* 74 */       Method m = c.getDeclaredMethod("encode", STRING_ARGS_2);
/*    */       try {
/* 76 */         result = (String)m.invoke(null, new Object[] { s, encoding });
/*    */       }
/*    */       catch (InvocationTargetException e) {
/* 79 */         e.printStackTrace();
/*    */       }
/*    */       catch (IllegalAccessException e) {
/* 82 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */     catch (NoSuchMethodException e)
/*    */     {
/* 87 */       result = URLEncoder.encode(s);
/*    */     }
/* 89 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/URLUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */