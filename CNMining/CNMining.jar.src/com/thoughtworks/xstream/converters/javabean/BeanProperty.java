/*     */ package com.thoughtworks.xstream.converters.javabean;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class BeanProperty
/*     */ {
/*     */   private Class memberClass;
/*     */   private String propertyName;
/*     */   private Class type;
/*     */   protected Method getter;
/*     */   private Method setter;
/*  41 */   private static final Object[] EMPTY_ARGS = new Object[0];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BeanProperty(Class memberClass, String propertyName, Class propertyType)
/*     */   {
/*  48 */     this.memberClass = memberClass;
/*  49 */     this.propertyName = propertyName;
/*  50 */     this.type = propertyType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Class getBeanClass()
/*     */   {
/*  57 */     return this.memberClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Class getType()
/*     */   {
/*  64 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  71 */     return this.propertyName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isReadable()
/*     */   {
/*  78 */     return this.getter != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isWritable()
/*     */   {
/*  85 */     return this.setter != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(Object member)
/*     */     throws IllegalArgumentException, IllegalAccessException
/*     */   {
/*  94 */     if (!isReadable()) {
/*  95 */       throw new IllegalStateException("Property " + this.propertyName + " of " + this.memberClass + " not readable");
/*     */     }
/*     */     try
/*     */     {
/*  99 */       return this.getter.invoke(member, EMPTY_ARGS);
/*     */     } catch (InvocationTargetException e) {
/* 101 */       throw new UndeclaredThrowableException(e.getTargetException());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object set(Object member, Object newValue)
/*     */     throws IllegalArgumentException, IllegalAccessException
/*     */   {
/* 111 */     if (!isWritable()) {
/* 112 */       throw new IllegalStateException("Property " + this.propertyName + " of " + this.memberClass + " not writable");
/*     */     }
/*     */     try
/*     */     {
/* 116 */       return this.setter.invoke(member, new Object[] { newValue });
/*     */     } catch (InvocationTargetException e) {
/* 118 */       throw new UndeclaredThrowableException(e.getTargetException());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setGetterMethod(Method method)
/*     */   {
/* 126 */     this.getter = method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSetterMethod(Method method)
/*     */   {
/* 134 */     this.setter = method;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/javabean/BeanProperty.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */