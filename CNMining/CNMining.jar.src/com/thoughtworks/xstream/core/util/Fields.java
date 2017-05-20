/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*    */ import java.lang.reflect.Field;
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
/*    */ public class Fields
/*    */ {
/*    */   public static Field find(Class type, String name)
/*    */   {
/*    */     try
/*    */     {
/* 27 */       Field result = type.getDeclaredField(name);
/* 28 */       result.setAccessible(true);
/* 29 */       return result;
/*    */     } catch (NoSuchFieldException e) {
/* 31 */       throw new IllegalArgumentException("Could not access " + type.getName() + "." + name + " field: " + e.getMessage());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void write(Field field, Object instance, Object value)
/*    */   {
/*    */     try
/*    */     {
/* 42 */       field.set(instance, value);
/*    */     } catch (IllegalAccessException e) {
/* 44 */       throw new ObjectAccessException("Could not write " + field.getType().getName() + "." + field.getName() + " field");
/*    */     }
/*    */   }
/*    */   
/*    */   public static Object read(Field field, Object instance) {
/*    */     try {
/* 50 */       return field.get(instance);
/*    */     } catch (IllegalAccessException e) {
/* 52 */       throw new ObjectAccessException("Could not read " + field.getType().getName() + "." + field.getName() + " field");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/Fields.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */