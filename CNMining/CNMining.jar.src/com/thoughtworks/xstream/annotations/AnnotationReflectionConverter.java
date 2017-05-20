/*     */ package com.thoughtworks.xstream.annotations;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterMatcher;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
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
/*     */ 
/*     */ @Deprecated
/*     */ public class AnnotationReflectionConverter
/*     */   extends ReflectionConverter
/*     */ {
/*     */   private final AnnotationProvider annotationProvider;
/*     */   private final Map<Class<? extends ConverterMatcher>, Converter> cachedConverters;
/*     */   
/*     */   @Deprecated
/*     */   public AnnotationReflectionConverter(Mapper mapper, ReflectionProvider reflectionProvider, AnnotationProvider annotationProvider)
/*     */   {
/*  49 */     super(mapper, reflectionProvider);
/*  50 */     this.annotationProvider = annotationProvider;
/*  51 */     this.cachedConverters = new HashMap();
/*     */   }
/*     */   
/*     */   protected void marshallField(MarshallingContext context, Object newObj, Field field) {
/*  55 */     XStreamConverter annotation = (XStreamConverter)this.annotationProvider.getAnnotation(field, XStreamConverter.class);
/*     */     
/*  57 */     if (annotation != null) {
/*  58 */       Class<? extends ConverterMatcher> type = annotation.value();
/*  59 */       ensureCache(type);
/*  60 */       context.convertAnother(newObj, (Converter)this.cachedConverters.get(type));
/*     */     } else {
/*  62 */       context.convertAnother(newObj);
/*     */     }
/*     */   }
/*     */   
/*     */   private void ensureCache(Class<? extends ConverterMatcher> type) {
/*  67 */     if (!this.cachedConverters.containsKey(type)) {
/*  68 */       this.cachedConverters.put(type, newInstance(type));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Object unmarshallField(UnmarshallingContext context, Object result, Class type, Field field)
/*     */   {
/*  75 */     XStreamConverter annotation = (XStreamConverter)this.annotationProvider.getAnnotation(field, XStreamConverter.class);
/*     */     
/*  77 */     if (annotation != null) {
/*  78 */       Class<? extends Converter> converterType = annotation.value();
/*  79 */       ensureCache(converterType);
/*  80 */       return context.convertAnother(result, type, (Converter)this.cachedConverters.get(converterType));
/*     */     }
/*  82 */     return context.convertAnother(result, type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Converter newInstance(Class<? extends ConverterMatcher> type)
/*     */   {
/*     */     Converter converter;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*     */       Converter converter;
/*     */       
/*  96 */       if (SingleValueConverter.class.isAssignableFrom(type)) {
/*  97 */         SingleValueConverter svc = (SingleValueConverter)type.getConstructor(new Class[0]).newInstance(new Object[0]);
/*  98 */         converter = new SingleValueConverterWrapper(svc);
/*     */       } else {
/* 100 */         converter = (Converter)type.getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */       }
/*     */     } catch (InvocationTargetException e) {
/* 103 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e.getCause());
/*     */     }
/*     */     catch (InstantiationException e) {
/* 106 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/* 108 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (NoSuchMethodException e) {
/* 110 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     }
/* 112 */     return converter;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/AnnotationReflectionConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */