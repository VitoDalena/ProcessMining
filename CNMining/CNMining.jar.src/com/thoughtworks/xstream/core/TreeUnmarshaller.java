/*     */ package com.thoughtworks.xstream.core;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.DataHolder;
/*     */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.core.util.HierarchicalStreams;
/*     */ import com.thoughtworks.xstream.core.util.PrioritizedList;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
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
/*     */ public class TreeUnmarshaller
/*     */   implements UnmarshallingContext
/*     */ {
/*     */   private Object root;
/*     */   protected HierarchicalStreamReader reader;
/*     */   private ConverterLookup converterLookup;
/*     */   private Mapper mapper;
/*  35 */   private FastStack types = new FastStack(16);
/*     */   private DataHolder dataHolder;
/*  37 */   private final PrioritizedList validationList = new PrioritizedList();
/*     */   
/*     */ 
/*     */   public TreeUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*     */   {
/*  42 */     this.root = root;
/*  43 */     this.reader = reader;
/*  44 */     this.converterLookup = converterLookup;
/*  45 */     this.mapper = mapper;
/*     */   }
/*     */   
/*     */   public Object convertAnother(Object parent, Class type) {
/*  49 */     return convertAnother(parent, type, null);
/*     */   }
/*     */   
/*     */   public Object convertAnother(Object parent, Class type, Converter converter) {
/*  53 */     type = this.mapper.defaultImplementationOf(type);
/*  54 */     if (converter == null) {
/*  55 */       converter = this.converterLookup.lookupConverterForType(type);
/*     */     }
/*  57 */     else if (!converter.canConvert(type)) {
/*  58 */       ConversionException e = new ConversionException("Explicit selected converter cannot handle type");
/*     */       
/*  60 */       e.add("item-type", type.getName());
/*  61 */       e.add("converter-type", converter.getClass().getName());
/*  62 */       throw e;
/*     */     }
/*     */     
/*  65 */     return convert(parent, type, converter);
/*     */   }
/*     */   
/*     */   protected Object convert(Object parent, Class type, Converter converter) {
/*     */     try {
/*  70 */       this.types.push(type);
/*  71 */       Object result = converter.unmarshal(this.reader, this);
/*  72 */       this.types.popSilently();
/*  73 */       return result;
/*     */     } catch (ConversionException conversionException) {
/*  75 */       addInformationTo(conversionException, type, converter);
/*  76 */       throw conversionException;
/*     */     } catch (RuntimeException e) {
/*  78 */       ConversionException conversionException = new ConversionException(e);
/*  79 */       addInformationTo(conversionException, type, converter);
/*  80 */       throw conversionException;
/*     */     }
/*     */   }
/*     */   
/*     */   private void addInformationTo(ErrorWriter errorWriter, Class type, Converter converter) {
/*  85 */     errorWriter.add("class", type.getName());
/*  86 */     errorWriter.add("required-type", getRequiredType().getName());
/*  87 */     errorWriter.add("converter-type", converter.getClass().getName());
/*  88 */     this.reader.appendErrors(errorWriter);
/*     */   }
/*     */   
/*     */   public void addCompletionCallback(Runnable work, int priority) {
/*  92 */     this.validationList.add(work, priority);
/*     */   }
/*     */   
/*     */   public Object currentObject() {
/*  96 */     return this.types.size() == 1 ? this.root : null;
/*     */   }
/*     */   
/*     */   public Class getRequiredType() {
/* 100 */     return (Class)this.types.peek();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 104 */     lazilyCreateDataHolder();
/* 105 */     return this.dataHolder.get(key);
/*     */   }
/*     */   
/*     */   public void put(Object key, Object value) {
/* 109 */     lazilyCreateDataHolder();
/* 110 */     this.dataHolder.put(key, value);
/*     */   }
/*     */   
/*     */   public Iterator keys() {
/* 114 */     lazilyCreateDataHolder();
/* 115 */     return this.dataHolder.keys();
/*     */   }
/*     */   
/*     */   private void lazilyCreateDataHolder() {
/* 119 */     if (this.dataHolder == null) {
/* 120 */       this.dataHolder = new MapBackedDataHolder();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object start(DataHolder dataHolder) {
/* 125 */     this.dataHolder = dataHolder;
/* 126 */     Class type = HierarchicalStreams.readClassType(this.reader, this.mapper);
/* 127 */     Object result = convertAnother(null, type);
/* 128 */     Iterator validations = this.validationList.iterator();
/* 129 */     while (validations.hasNext()) {
/* 130 */       Runnable runnable = (Runnable)validations.next();
/* 131 */       runnable.run();
/*     */     }
/* 133 */     return result;
/*     */   }
/*     */   
/*     */   protected Mapper getMapper() {
/* 137 */     return this.mapper;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/TreeUnmarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */