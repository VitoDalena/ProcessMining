/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.core.util.FastStack;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractReferenceUnmarshaller
/*    */   extends TreeUnmarshaller
/*    */ {
/* 33 */   private Map values = new HashMap();
/* 34 */   private FastStack parentStack = new FastStack(16);
/*    */   
/*    */   public AbstractReferenceUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 38 */     super(root, reader, converterLookup, mapper);
/*    */   }
/*    */   
/*    */   protected Object convert(Object parent, Class type, Converter converter) {
/* 42 */     if (this.parentStack.size() > 0) {
/* 43 */       Object parentReferenceKey = this.parentStack.peek();
/* 44 */       if ((parentReferenceKey != null) && 
/* 45 */         (!this.values.containsKey(parentReferenceKey))) {
/* 46 */         this.values.put(parentReferenceKey, parent);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 51 */     String attributeName = getMapper().aliasForSystemAttribute("reference");
/* 52 */     String reference = attributeName == null ? null : this.reader.getAttribute(attributeName);
/* 53 */     Object result; if (reference != null) {
/* 54 */       Object result = this.values.get(getReferenceKey(reference));
/* 55 */       if (result == null) {
/* 56 */         ConversionException ex = new ConversionException("Invalid reference");
/* 57 */         ex.add("reference", reference);
/* 58 */         throw ex;
/*    */       }
/*    */     } else {
/* 61 */       Object currentReferenceKey = getCurrentReferenceKey();
/* 62 */       this.parentStack.push(currentReferenceKey);
/* 63 */       result = super.convert(parent, type, converter);
/* 64 */       if ((currentReferenceKey != null) && (result != null)) {
/* 65 */         this.values.put(currentReferenceKey, result);
/*    */       }
/* 67 */       this.parentStack.popSilently();
/*    */     }
/* 69 */     return result;
/*    */   }
/*    */   
/*    */   protected abstract Object getReferenceKey(String paramString);
/*    */   
/*    */   protected abstract Object getCurrentReferenceKey();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/AbstractReferenceUnmarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */