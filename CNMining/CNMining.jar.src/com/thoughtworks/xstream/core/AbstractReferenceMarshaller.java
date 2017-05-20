/*     */ package com.thoughtworks.xstream.core;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.ObjectIdDictionary;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.path.Path;
/*     */ import com.thoughtworks.xstream.io.path.PathTracker;
/*     */ import com.thoughtworks.xstream.io.path.PathTrackingWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.util.Iterator;
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
/*     */ public abstract class AbstractReferenceMarshaller
/*     */   extends TreeMarshaller
/*     */   implements MarshallingContext
/*     */ {
/*  36 */   private ObjectIdDictionary references = new ObjectIdDictionary();
/*  37 */   private ObjectIdDictionary implicitElements = new ObjectIdDictionary();
/*  38 */   private PathTracker pathTracker = new PathTracker();
/*     */   
/*     */   private Path lastPath;
/*     */   
/*     */   public AbstractReferenceMarshaller(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper)
/*     */   {
/*  44 */     super(writer, converterLookup, mapper);
/*  45 */     this.writer = new PathTrackingWriter(writer, this.pathTracker);
/*     */   }
/*     */   
/*     */   public void convert(Object item, Converter converter) {
/*  49 */     if (getMapper().isImmutableValueType(item.getClass()))
/*     */     {
/*  51 */       converter.marshal(item, this.writer, this);
/*     */     } else {
/*  53 */       Path currentPath = this.pathTracker.getPath();
/*  54 */       Object existingReferenceKey = this.references.lookupId(item);
/*  55 */       if ((existingReferenceKey != null) && (existingReferenceKey != currentPath)) {
/*  56 */         String attributeName = getMapper().aliasForSystemAttribute("reference");
/*  57 */         if (attributeName != null) {
/*  58 */           this.writer.addAttribute(attributeName, createReference(currentPath, existingReferenceKey));
/*     */         }
/*     */       } else {
/*  61 */         Object newReferenceKey = existingReferenceKey == null ? createReferenceKey(currentPath, item) : existingReferenceKey;
/*  62 */         if ((this.lastPath == null) || (!currentPath.isAncestor(this.lastPath))) {
/*  63 */           fireValidReference(newReferenceKey);
/*  64 */           this.lastPath = currentPath;
/*  65 */           this.references.associateId(item, newReferenceKey);
/*     */         }
/*  67 */         converter.marshal(item, this.writer, new ReferencingMarshallingContext() {
/*     */           private final Object val$newReferenceKey;
/*     */           
/*  70 */           public void put(Object key, Object value) { AbstractReferenceMarshaller.this.put(key, value); }
/*     */           
/*     */           public Iterator keys()
/*     */           {
/*  74 */             return AbstractReferenceMarshaller.this.keys();
/*     */           }
/*     */           
/*     */           public Object get(Object key) {
/*  78 */             return AbstractReferenceMarshaller.this.get(key);
/*     */           }
/*     */           
/*     */           public void convertAnother(Object nextItem, Converter converter) {
/*  82 */             AbstractReferenceMarshaller.this.convertAnother(nextItem, converter);
/*     */           }
/*     */           
/*     */           public void convertAnother(Object nextItem) {
/*  86 */             AbstractReferenceMarshaller.this.convertAnother(nextItem);
/*     */           }
/*     */           
/*     */           public void replace(Object original, Object replacement) {
/*  90 */             AbstractReferenceMarshaller.this.references.associateId(replacement, this.val$newReferenceKey);
/*     */           }
/*     */           
/*     */           public Object lookupReference(Object item) {
/*  94 */             return AbstractReferenceMarshaller.this.references.lookupId(item);
/*     */           }
/*     */           
/*     */           public Path currentPath() {
/*  98 */             return AbstractReferenceMarshaller.this.pathTracker.getPath();
/*     */           }
/*     */           
/*     */           public void registerImplicit(Object item) {
/* 102 */             if (AbstractReferenceMarshaller.this.implicitElements.containsId(item)) {
/* 103 */               throw new AbstractReferenceMarshaller.ReferencedImplicitElementException(item, currentPath());
/*     */             }
/* 105 */             AbstractReferenceMarshaller.this.implicitElements.associateId(item, this.val$newReferenceKey);
/*     */           }
/*     */         });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected abstract String createReference(Path paramPath, Object paramObject);
/*     */   
/*     */   protected abstract Object createReferenceKey(Path paramPath, Object paramObject);
/*     */   
/*     */   protected abstract void fireValidReference(Object paramObject);
/*     */   
/* 118 */   public static class ReferencedImplicitElementException extends ConversionException { public ReferencedImplicitElementException(Object item, Path path) { super();
/* 119 */       add("implicit-element", item.toString());
/* 120 */       add("referencing-element", path.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/AbstractReferenceMarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */