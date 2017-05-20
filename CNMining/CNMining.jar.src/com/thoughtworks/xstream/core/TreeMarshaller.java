/*     */ package com.thoughtworks.xstream.core;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStreamException;
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.DataHolder;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.ObjectIdDictionary;
/*     */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
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
/*     */ public class TreeMarshaller
/*     */   implements MarshallingContext
/*     */ {
/*     */   protected HierarchicalStreamWriter writer;
/*     */   protected ConverterLookup converterLookup;
/*     */   private Mapper mapper;
/*  33 */   private ObjectIdDictionary parentObjects = new ObjectIdDictionary();
/*     */   private DataHolder dataHolder;
/*     */   
/*     */   public TreeMarshaller(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper)
/*     */   {
/*  38 */     this.writer = writer;
/*  39 */     this.converterLookup = converterLookup;
/*  40 */     this.mapper = mapper;
/*     */   }
/*     */   
/*     */   public void convertAnother(Object item) {
/*  44 */     convertAnother(item, null);
/*     */   }
/*     */   
/*     */   public void convertAnother(Object item, Converter converter) {
/*  48 */     if (converter == null) {
/*  49 */       converter = this.converterLookup.lookupConverterForType(item.getClass());
/*     */     }
/*  51 */     else if (!converter.canConvert(item.getClass())) {
/*  52 */       ConversionException e = new ConversionException("Explicit selected converter cannot handle item");
/*     */       
/*  54 */       e.add("item-type", item.getClass().getName());
/*  55 */       e.add("converter-type", converter.getClass().getName());
/*  56 */       throw e;
/*     */     }
/*     */     
/*  59 */     convert(item, converter);
/*     */   }
/*     */   
/*     */   protected void convert(Object item, Converter converter) {
/*  63 */     if (this.parentObjects.containsId(item)) {
/*  64 */       throw new CircularReferenceException();
/*     */     }
/*  66 */     this.parentObjects.associateId(item, "");
/*  67 */     converter.marshal(item, this.writer, this);
/*  68 */     this.parentObjects.removeId(item);
/*     */   }
/*     */   
/*     */   public void start(Object item, DataHolder dataHolder) {
/*  72 */     this.dataHolder = dataHolder;
/*  73 */     if (item == null) {
/*  74 */       this.writer.startNode(this.mapper.serializedClass(null));
/*  75 */       this.writer.endNode();
/*     */     } else {
/*  77 */       ExtendedHierarchicalStreamWriterHelper.startNode(this.writer, this.mapper.serializedClass(item.getClass()), item.getClass());
/*     */       
/*  79 */       convertAnother(item);
/*  80 */       this.writer.endNode();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  85 */     lazilyCreateDataHolder();
/*  86 */     return this.dataHolder.get(key);
/*     */   }
/*     */   
/*     */   public void put(Object key, Object value) {
/*  90 */     lazilyCreateDataHolder();
/*  91 */     this.dataHolder.put(key, value);
/*     */   }
/*     */   
/*     */   public Iterator keys() {
/*  95 */     lazilyCreateDataHolder();
/*  96 */     return this.dataHolder.keys();
/*     */   }
/*     */   
/*     */   private void lazilyCreateDataHolder() {
/* 100 */     if (this.dataHolder == null) {
/* 101 */       this.dataHolder = new MapBackedDataHolder();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Mapper getMapper() {
/* 106 */     return this.mapper;
/*     */   }
/*     */   
/*     */   public static class CircularReferenceException
/*     */     extends XStreamException
/*     */   {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/TreeMarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */