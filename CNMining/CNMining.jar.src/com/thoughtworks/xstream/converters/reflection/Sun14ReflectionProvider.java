/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import sun.misc.Unsafe;
/*     */ import sun.reflect.ReflectionFactory;
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
/*     */ public class Sun14ReflectionProvider
/*     */   extends PureJavaReflectionProvider
/*     */ {
/*     */   private static final Unsafe unsafe;
/*     */   private static final Exception exception;
/*     */   
/*     */   static
/*     */   {
/*  38 */     Unsafe u = null;
/*  39 */     Exception ex = null;
/*     */     try {
/*  41 */       Class objectStreamClass = Class.forName("sun.misc.Unsafe");
/*  42 */       Field unsafeField = objectStreamClass.getDeclaredField("theUnsafe");
/*  43 */       unsafeField.setAccessible(true);
/*  44 */       u = (Unsafe)unsafeField.get(null);
/*     */     } catch (ClassNotFoundException e) {
/*  46 */       ex = e;
/*     */     } catch (SecurityException e) {
/*  48 */       ex = e;
/*     */     } catch (NoSuchFieldException e) {
/*  50 */       ex = e;
/*     */     } catch (IllegalArgumentException e) {
/*  52 */       ex = e;
/*     */     } catch (IllegalAccessException e) {
/*  54 */       ex = e;
/*     */     }
/*  56 */     exception = ex;
/*  57 */     unsafe = u;
/*     */   }
/*     */   
/*  60 */   private transient ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
/*  61 */   private transient Map constructorCache = Collections.synchronizedMap(new WeakHashMap());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sun14ReflectionProvider(FieldDictionary dic)
/*     */   {
/*  68 */     super(dic);
/*     */   }
/*     */   
/*     */   public Object newInstance(Class type) {
/*     */     try {
/*  73 */       Constructor customConstructor = getMungedConstructor(type);
/*  74 */       return customConstructor.newInstance(new Object[0]);
/*     */     } catch (NoSuchMethodException e) {
/*  76 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (SecurityException e) {
/*  78 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (InstantiationException e) {
/*  80 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/*  82 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalArgumentException e) {
/*  84 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (InvocationTargetException e) {
/*  86 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   private Constructor getMungedConstructor(Class type) throws NoSuchMethodException {
/*  91 */     WeakReference ref = (WeakReference)this.constructorCache.get(type);
/*  92 */     Constructor ctor = (Constructor)(ref == null ? null : ref.get());
/*  93 */     if (ctor == null) {
/*  94 */       ctor = this.reflectionFactory.newConstructorForSerialization(type, Object.class.getDeclaredConstructor(new Class[0]));
/*  95 */       this.constructorCache.put(type, new WeakReference(ctor));
/*     */     }
/*  97 */     return ctor;
/*     */   }
/*     */   
/*     */   public void writeField(Object object, String fieldName, Object value, Class definedIn) {
/* 101 */     write(this.fieldDictionary.field(object.getClass(), fieldName, definedIn), object, value);
/*     */   }
/*     */   
/*     */   private void write(Field field, Object object, Object value) {
/* 105 */     if (exception != null) {
/* 106 */       throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), exception);
/*     */     }
/*     */     try {
/* 109 */       long offset = unsafe.objectFieldOffset(field);
/* 110 */       Class type = field.getType();
/* 111 */       if (type.isPrimitive()) {
/* 112 */         if (type.equals(Integer.TYPE)) {
/* 113 */           unsafe.putInt(object, offset, ((Integer)value).intValue());
/* 114 */         } else if (type.equals(Long.TYPE)) {
/* 115 */           unsafe.putLong(object, offset, ((Long)value).longValue());
/* 116 */         } else if (type.equals(Short.TYPE)) {
/* 117 */           unsafe.putShort(object, offset, ((Short)value).shortValue());
/* 118 */         } else if (type.equals(Character.TYPE)) {
/* 119 */           unsafe.putChar(object, offset, ((Character)value).charValue());
/* 120 */         } else if (type.equals(Byte.TYPE)) {
/* 121 */           unsafe.putByte(object, offset, ((Byte)value).byteValue());
/* 122 */         } else if (type.equals(Float.TYPE)) {
/* 123 */           unsafe.putFloat(object, offset, ((Float)value).floatValue());
/* 124 */         } else if (type.equals(Double.TYPE)) {
/* 125 */           unsafe.putDouble(object, offset, ((Double)value).doubleValue());
/* 126 */         } else if (type.equals(Boolean.TYPE)) {
/* 127 */           unsafe.putBoolean(object, offset, ((Boolean)value).booleanValue());
/*     */         } else {
/* 129 */           throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName() + ": Unknown type " + type);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 134 */         unsafe.putObject(object, offset, value);
/*     */       }
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/* 138 */       throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object readResolve()
/*     */   {
/* 147 */     super.readResolve();
/* 148 */     this.constructorCache = Collections.synchronizedMap(new WeakHashMap());
/* 149 */     this.reflectionFactory = ReflectionFactory.getReflectionFactory();
/* 150 */     return this;
/*     */   }
/*     */   
/*     */   public Sun14ReflectionProvider() {}
/*     */   
/*     */   protected void validateFieldAccess(Field field) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/Sun14ReflectionProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */