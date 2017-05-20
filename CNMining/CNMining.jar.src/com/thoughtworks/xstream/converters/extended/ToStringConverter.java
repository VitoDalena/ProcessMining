/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
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
/*    */ public class ToStringConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private final Class clazz;
/*    */   private final Constructor ctor;
/*    */   
/*    */   public ToStringConverter(Class clazz)
/*    */     throws NoSuchMethodException
/*    */   {
/* 33 */     this.clazz = clazz;
/* 34 */     this.ctor = clazz.getConstructor(new Class[] { String.class });
/*    */   }
/*    */   
/* 37 */   public boolean canConvert(Class type) { return type.equals(this.clazz); }
/*    */   
/*    */   public String toString(Object obj) {
/* 40 */     return obj == null ? null : obj.toString();
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/*    */     try {
/* 45 */       return this.ctor.newInstance(new Object[] { str });
/*    */     } catch (InstantiationException e) {
/* 47 */       throw new ConversionException("Unable to instantiate single String param constructor", e);
/*    */     } catch (IllegalAccessException e) {
/* 49 */       throw new ConversionException("Unable to access single String param constructor", e);
/*    */     } catch (InvocationTargetException e) {
/* 51 */       throw new ConversionException("Unable to target single String param constructor", e.getTargetException());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ToStringConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */