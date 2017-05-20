/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.converters.ConverterRegistry;
/*    */ import com.thoughtworks.xstream.core.util.PrioritizedList;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
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
/*    */ public class DefaultConverterLookup
/*    */   implements ConverterLookup, ConverterRegistry
/*    */ {
/* 35 */   private final PrioritizedList converters = new PrioritizedList();
/* 36 */   private transient Map typeToConverterMap = Collections.synchronizedMap(new HashMap());
/*    */   
/*    */ 
/*    */   public DefaultConverterLookup() {}
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public DefaultConverterLookup(Mapper mapper) {}
/*    */   
/*    */   public Converter lookupConverterForType(Class type)
/*    */   {
/* 48 */     Converter cachedConverter = (Converter)this.typeToConverterMap.get(type);
/* 49 */     if (cachedConverter != null) return cachedConverter;
/* 50 */     Iterator iterator = this.converters.iterator();
/* 51 */     while (iterator.hasNext()) {
/* 52 */       Converter converter = (Converter)iterator.next();
/* 53 */       if (converter.canConvert(type)) {
/* 54 */         this.typeToConverterMap.put(type, converter);
/* 55 */         return converter;
/*    */       }
/*    */     }
/* 58 */     throw new ConversionException("No converter specified for " + type);
/*    */   }
/*    */   
/*    */   public void registerConverter(Converter converter, int priority) {
/* 62 */     this.converters.add(converter, priority);
/* 63 */     for (Iterator iter = this.typeToConverterMap.keySet().iterator(); iter.hasNext();) {
/* 64 */       Class type = (Class)iter.next();
/* 65 */       if (converter.canConvert(type)) {
/* 66 */         iter.remove();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 72 */     this.typeToConverterMap = Collections.synchronizedMap(new HashMap());
/* 73 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/DefaultConverterLookup.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */