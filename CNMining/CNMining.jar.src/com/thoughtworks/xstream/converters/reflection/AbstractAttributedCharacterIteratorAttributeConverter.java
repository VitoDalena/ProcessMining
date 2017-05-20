/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.text.AttributedCharacterIterator.Attribute;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
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
/*    */ public class AbstractAttributedCharacterIteratorAttributeConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private static final Method getName;
/*    */   private final Class type;
/*    */   private transient Map attributeMap;
/*    */   private transient FieldDictionary fieldDictionary;
/*    */   
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 38 */       getName = AttributedCharacterIterator.Attribute.class.getDeclaredMethod("getName", (Class[])null);
/*    */     }
/*    */     catch (NoSuchMethodException e) {
/* 41 */       throw new ExceptionInInitializerError("Missing AttributedCharacterIterator.Attribute.getName()");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AbstractAttributedCharacterIteratorAttributeConverter(Class type)
/*    */   {
/* 51 */     this.type = type;
/* 52 */     readResolve();
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 56 */     return type == this.type;
/*    */   }
/*    */   
/*    */   public String toString(Object source) {
/* 60 */     AttributedCharacterIterator.Attribute attribute = (AttributedCharacterIterator.Attribute)source;
/*    */     try {
/* 62 */       if (!getName.isAccessible()) {
/* 63 */         getName.setAccessible(true);
/*    */       }
/* 65 */       return (String)getName.invoke(attribute, (Object[])null);
/*    */     } catch (IllegalAccessException e) {
/* 67 */       throw new ObjectAccessException("Cannot get name of AttributedCharacterIterator.Attribute", e);
/*    */     }
/*    */     catch (InvocationTargetException e) {
/* 70 */       throw new ObjectAccessException("Cannot get name of AttributedCharacterIterator.Attribute", e.getTargetException());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public Object fromString(String str)
/*    */   {
/* 77 */     return this.attributeMap.get(str);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 81 */     this.fieldDictionary = new FieldDictionary();
/* 82 */     this.attributeMap = new HashMap();
/* 83 */     Iterator iterator = this.fieldDictionary.fieldsFor(this.type);
/* 84 */     while (iterator.hasNext()) {
/* 85 */       Field field = (Field)iterator.next();
/* 86 */       if ((field.getType() == this.type) && (Modifier.isStatic(field.getModifiers()))) {
/*    */         try {
/* 88 */           Object attribute = field.get(null);
/* 89 */           this.attributeMap.put(toString(attribute), attribute);
/*    */         } catch (IllegalAccessException e) {
/* 91 */           throw new ObjectAccessException("Cannot get object of " + field, e);
/*    */         }
/*    */       }
/*    */     }
/* 95 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/AbstractAttributedCharacterIteratorAttributeConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */