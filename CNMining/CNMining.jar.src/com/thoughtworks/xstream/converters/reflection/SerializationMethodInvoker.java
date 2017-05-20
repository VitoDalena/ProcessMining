/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.core.util.FastField;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerializationMethodInvoker
/*     */ {
/*  34 */   private static final Method NO_METHOD = new Object() { private void noMethod() {} }.getClass().getDeclaredMethods()[0];
/*     */   
/*     */ 
/*     */ 
/*  38 */   private static final Object[] EMPTY_ARGS = new Object[0];
/*  39 */   public SerializationMethodInvoker() { this.cache = Collections.synchronizedMap(new HashMap());
/*     */     
/*  41 */     this.cache.put(new FastField(Object.class, "readResolve"), NO_METHOD);
/*  42 */     this.cache.put(new FastField(Object.class, "writeReplace"), NO_METHOD);
/*  43 */     this.cache.put(new FastField(Object.class, "readObject"), NO_METHOD);
/*  44 */     this.cache.put(new FastField(Object.class, "writeObject"), NO_METHOD);
/*     */   }
/*     */   
/*     */ 
/*     */   private Map cache;
/*     */   public Object callReadResolve(Object result)
/*     */   {
/*  51 */     if (result == null) {
/*  52 */       return null;
/*     */     }
/*  54 */     Method readResolveMethod = getMethod(result.getClass(), "readResolve", null, true);
/*  55 */     if (readResolveMethod != null) {
/*     */       try {
/*  57 */         return readResolveMethod.invoke(result, EMPTY_ARGS);
/*     */       } catch (IllegalAccessException e) {
/*  59 */         throw new ObjectAccessException("Could not call " + result.getClass().getName() + ".readResolve()", e);
/*     */       }
/*     */       catch (InvocationTargetException e)
/*     */       {
/*  63 */         throw new ObjectAccessException("Could not call " + result.getClass().getName() + ".readResolve()", e.getTargetException());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  68 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object callWriteReplace(Object object)
/*     */   {
/*  74 */     if (object == null) {
/*  75 */       return null;
/*     */     }
/*  77 */     Method writeReplaceMethod = getMethod(object.getClass(), "writeReplace", null, true);
/*  78 */     if (writeReplaceMethod != null) {
/*     */       try {
/*  80 */         return writeReplaceMethod.invoke(object, EMPTY_ARGS);
/*     */       } catch (IllegalAccessException e) {
/*  82 */         throw new ObjectAccessException("Could not call " + object.getClass().getName() + ".writeReplace()", e);
/*     */       }
/*     */       catch (InvocationTargetException e)
/*     */       {
/*  86 */         throw new ObjectAccessException("Could not call " + object.getClass().getName() + ".writeReplace()", e.getTargetException());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  91 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean supportsReadObject(Class type, boolean includeBaseClasses)
/*     */   {
/*  97 */     return getMethod(type, "readObject", new Class[] { ObjectInputStream.class }, includeBaseClasses) != null;
/*     */   }
/*     */   
/*     */   public void callReadObject(Class type, Object object, ObjectInputStream stream)
/*     */   {
/*     */     try {
/* 103 */       Method readObjectMethod = getMethod(type, "readObject", new Class[] { ObjectInputStream.class }, false);
/*     */       
/* 105 */       readObjectMethod.invoke(object, new Object[] { stream });
/*     */     } catch (IllegalAccessException e) {
/* 107 */       throw new ConversionException("Could not call " + object.getClass().getName() + ".readObject()", e);
/*     */     }
/*     */     catch (InvocationTargetException e)
/*     */     {
/* 111 */       throw new ConversionException("Could not call " + object.getClass().getName() + ".readObject()", e.getTargetException());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean supportsWriteObject(Class type, boolean includeBaseClasses)
/*     */   {
/* 118 */     return getMethod(type, "writeObject", new Class[] { ObjectOutputStream.class }, includeBaseClasses) != null;
/*     */   }
/*     */   
/*     */   public void callWriteObject(Class type, Object instance, ObjectOutputStream stream)
/*     */   {
/*     */     try {
/* 124 */       Method readObjectMethod = getMethod(type, "writeObject", new Class[] { ObjectOutputStream.class }, false);
/*     */       
/* 126 */       readObjectMethod.invoke(instance, new Object[] { stream });
/*     */     } catch (IllegalAccessException e) {
/* 128 */       throw new ConversionException("Could not call " + instance.getClass().getName() + ".writeObject()", e);
/*     */     }
/*     */     catch (InvocationTargetException e)
/*     */     {
/* 132 */       throw new ConversionException("Could not call " + instance.getClass().getName() + ".writeObject()", e.getTargetException());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Method getMethod(Class type, String name, Class[] parameterTypes, boolean includeBaseclasses)
/*     */   {
/* 140 */     Method method = getMethod(type, name, parameterTypes);
/* 141 */     return (method == NO_METHOD) || ((!includeBaseclasses) && (!method.getDeclaringClass().equals(type))) ? null : method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Method getMethod(Class type, String name, Class[] parameterTypes)
/*     */   {
/* 148 */     FastField method = new FastField(type, name);
/* 149 */     Method result = (Method)this.cache.get(method);
/*     */     
/* 151 */     if (result == null) {
/*     */       try {
/* 153 */         result = type.getDeclaredMethod(name, parameterTypes);
/* 154 */         if (!result.isAccessible()) {
/* 155 */           result.setAccessible(true);
/*     */         }
/*     */       } catch (NoSuchMethodException e) {
/* 158 */         result = getMethod(type.getSuperclass(), name, parameterTypes);
/*     */       }
/* 160 */       this.cache.put(method, result);
/*     */     }
/* 162 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/SerializationMethodInvoker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */