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
/*    */ public final class Constants
/*    */ {
/* 30 */   public static final String JAVA_VERSION = System.getProperty("java.version");
/*    */   
/* 32 */   public static final boolean JAVA_1_1 = JAVA_VERSION.startsWith("1.1.");
/*    */   
/* 34 */   public static final boolean JAVA_1_2 = JAVA_VERSION.startsWith("1.2.");
/*    */   
/* 36 */   public static final boolean JAVA_1_3 = JAVA_VERSION.startsWith("1.3.");
/*    */   
/*    */ 
/* 39 */   public static final String OS_NAME = System.getProperty("os.name");
/*    */   
/* 41 */   public static final boolean LINUX = OS_NAME.startsWith("Linux");
/*    */   
/* 43 */   public static final boolean WINDOWS = OS_NAME.startsWith("Windows");
/*    */   
/* 45 */   public static final boolean SUN_OS = OS_NAME.startsWith("SunOS");
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/util/Constants.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */