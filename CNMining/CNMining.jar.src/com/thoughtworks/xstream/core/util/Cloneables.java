/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*    */ import java.lang.reflect.Array;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
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
/*    */ public class Cloneables
/*    */ {
/*    */   public static Object clone(Object o)
/*    */   {
/* 28 */     if ((o instanceof Cloneable)) {
/* 29 */       if (o.getClass().isArray()) {
/* 30 */         Class componentType = o.getClass().getComponentType();
/* 31 */         if (!componentType.isPrimitive()) {
/* 32 */           return ((Object[])o).clone();
/*    */         }
/* 34 */         int length = Array.getLength(o);
/* 35 */         Object clone = Array.newInstance(componentType, length);
/* 36 */         while (length-- > 0) {
/* 37 */           Array.set(clone, length, Array.get(o, length));
/*    */         }
/*    */         
/* 40 */         return clone;
/*    */       }
/*    */       try
/*    */       {
/* 44 */         Method clone = o.getClass().getMethod("clone", (Class[])null);
/* 45 */         return clone.invoke(o, (Object[])null);
/*    */       } catch (NoSuchMethodException e) {
/* 47 */         throw new ObjectAccessException("Cloneable type has no clone method", e);
/*    */       } catch (IllegalAccessException e) {
/* 49 */         throw new ObjectAccessException("Cannot clone Cloneable type", e);
/*    */       } catch (InvocationTargetException e) {
/* 51 */         throw new ObjectAccessException("Exception cloning Cloneable type", e.getCause());
/*    */       }
/*    */     }
/*    */     
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public static Object cloneIfPossible(Object o) {
/* 59 */     Object clone = clone(o);
/* 60 */     return clone == null ? o : clone;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/Cloneables.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */