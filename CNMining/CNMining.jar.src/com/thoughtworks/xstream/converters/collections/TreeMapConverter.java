/*     */ package com.thoughtworks.xstream.converters.collections;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.JVM;
/*     */ import com.thoughtworks.xstream.core.util.PresortedMap;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
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
/*     */ public class TreeMapConverter
/*     */   extends MapConverter
/*     */ {
/*     */   private static final Field comparatorField;
/*     */   
/*     */   static
/*     */   {
/*  41 */     Field cmpField = null;
/*     */     try {
/*  43 */       Field[] fields = TreeMap.class.getDeclaredFields();
/*  44 */       for (int i = 0; i < fields.length; i++) {
/*  45 */         if (fields[i].getType() == Comparator.class)
/*     */         {
/*  47 */           cmpField = fields[i];
/*  48 */           cmpField.setAccessible(true);
/*  49 */           break;
/*     */         }
/*     */       }
/*  52 */       if (cmpField == null) {
/*  53 */         throw new ExceptionInInitializerError("Cannot detect comparator field of TreeMap");
/*     */       }
/*     */     }
/*     */     catch (SecurityException ex) {}
/*     */     
/*     */ 
/*  59 */     comparatorField = cmpField;
/*     */   }
/*     */   
/*     */   public TreeMapConverter(Mapper mapper) {
/*  63 */     super(mapper);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  67 */     return type.equals(TreeMap.class);
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  71 */     TreeMap treeMap = (TreeMap)source;
/*  72 */     Comparator comparator = treeMap.comparator();
/*  73 */     if (comparator == null) {
/*  74 */       writer.startNode("no-comparator");
/*  75 */       writer.endNode();
/*     */     } else {
/*  77 */       writer.startNode("comparator");
/*  78 */       writer.addAttribute("class", mapper().serializedClass(comparator.getClass()));
/*  79 */       context.convertAnother(comparator);
/*  80 */       writer.endNode();
/*     */     }
/*  82 */     super.marshal(source, writer, context);
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  86 */     TreeMap result = comparatorField != null ? new TreeMap() : null;
/*  87 */     Comparator comparator = unmarshalComparator(reader, context, result);
/*  88 */     if (result == null) {
/*  89 */       result = comparator == null ? new TreeMap() : new TreeMap(comparator);
/*     */     }
/*  91 */     populateTreeMap(reader, context, result, comparator);
/*  92 */     return result;
/*     */   }
/*     */   
/*     */   protected Comparator unmarshalComparator(HierarchicalStreamReader reader, UnmarshallingContext context, TreeMap result)
/*     */   {
/*  97 */     reader.moveDown();
/*     */     Comparator comparator;
/*  99 */     if (reader.getNodeName().equals("comparator")) {
/* 100 */       String comparatorClass = reader.getAttribute("class");
/* 101 */       comparator = (Comparator)context.convertAnother(result, mapper().realClass(comparatorClass)); } else { Comparator comparator;
/* 102 */       if (reader.getNodeName().equals("no-comparator")) {
/* 103 */         comparator = null;
/*     */       } else
/* 105 */         throw new ConversionException("TreeMap does not contain <comparator> element"); }
/*     */     Comparator comparator;
/* 107 */     reader.moveUp();
/* 108 */     return comparator;
/*     */   }
/*     */   
/*     */   protected void populateTreeMap(HierarchicalStreamReader reader, UnmarshallingContext context, TreeMap result, Comparator comparator)
/*     */   {
/* 113 */     SortedMap sortedMap = new PresortedMap((comparator != null) && (JVM.hasOptimizedTreeMapPutAll()) ? comparator : null);
/* 114 */     populateMap(reader, context, result, sortedMap);
/*     */     try {
/* 116 */       if (JVM.hasOptimizedTreeMapPutAll()) {
/* 117 */         if ((comparator != null) && (comparatorField != null)) {
/* 118 */           comparatorField.set(result, comparator);
/*     */         }
/* 120 */         result.putAll(sortedMap);
/* 121 */       } else if (comparatorField != null) {
/* 122 */         comparatorField.set(result, sortedMap.comparator());
/* 123 */         result.putAll(sortedMap);
/* 124 */         comparatorField.set(result, comparator);
/*     */       } else {
/* 126 */         result.putAll(sortedMap);
/*     */       }
/*     */     } catch (IllegalAccessException e) {
/* 129 */       throw new ConversionException("Cannot set comparator of TreeMap", e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/TreeMapConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */