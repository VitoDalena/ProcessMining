/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public final class Primitives
/*    */ {
/* 23 */   private static final Map BOX = new HashMap();
/* 24 */   private static final Map UNBOX = new HashMap();
/*    */   
/*    */   static {
/* 27 */     Class[][] boxing = { { Byte.TYPE, Byte.class }, { Character.TYPE, Character.class }, { Short.TYPE, Short.class }, { Integer.TYPE, Integer.class }, { Long.TYPE, Long.class }, { Float.TYPE, Float.class }, { Double.TYPE, Double.class }, { Boolean.TYPE, Boolean.class }, { Void.TYPE, Void.class } };
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
/* 38 */     for (int i = 0; i < boxing.length; i++) {
/* 39 */       BOX.put(boxing[i][0], boxing[i][1]);
/* 40 */       UNBOX.put(boxing[i][1], boxing[i][0]);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Class box(Class type) {
/* 45 */     return (Class)BOX.get(type);
/*    */   }
/*    */   
/*    */   public static Class unbox(Class type) {
/* 49 */     return (Class)UNBOX.get(type);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/Primitives.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */