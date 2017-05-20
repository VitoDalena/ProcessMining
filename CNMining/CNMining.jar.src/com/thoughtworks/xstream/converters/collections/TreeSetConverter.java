/*     */ package com.thoughtworks.xstream.converters.collections;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.JVM;
/*     */ import com.thoughtworks.xstream.core.util.PresortedSet;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
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
/*     */ public class TreeSetConverter
/*     */   extends CollectionConverter
/*     */ {
/*     */   private transient TreeMapConverter treeMapConverter;
/*     */   private static final Field sortedMapField;
/*     */   
/*     */   static
/*     */   {
/*  44 */     Field smField = null;
/*  45 */     if (!JVM.hasOptimizedTreeSetAddAll()) {
/*     */       try {
/*  47 */         Field[] fields = TreeSet.class.getDeclaredFields();
/*  48 */         for (int i = 0; i < fields.length; i++) {
/*  49 */           if (SortedMap.class.isAssignableFrom(fields[i].getType()))
/*     */           {
/*  51 */             smField = fields[i];
/*  52 */             smField.setAccessible(true);
/*  53 */             break;
/*     */           }
/*     */         }
/*  56 */         if (smField == null) {
/*  57 */           throw new ExceptionInInitializerError("Cannot detect field of backing map of TreeSet");
/*     */         }
/*     */       }
/*     */       catch (SecurityException ex) {}
/*     */     }
/*     */     
/*     */ 
/*  64 */     sortedMapField = smField;
/*     */   }
/*     */   
/*     */   public TreeSetConverter(Mapper mapper) {
/*  68 */     super(mapper);
/*  69 */     readResolve();
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  73 */     return type.equals(TreeSet.class);
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  77 */     TreeSet treeSet = (TreeSet)source;
/*  78 */     Comparator comparator = treeSet.comparator();
/*  79 */     if (comparator == null) {
/*  80 */       writer.startNode("no-comparator");
/*  81 */       writer.endNode();
/*     */     } else {
/*  83 */       writer.startNode("comparator");
/*  84 */       writer.addAttribute("class", mapper().serializedClass(comparator.getClass()));
/*  85 */       context.convertAnother(comparator);
/*  86 */       writer.endNode();
/*     */     }
/*  88 */     super.marshal(source, writer, context);
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  92 */     TreeSet result = null;
/*     */     
/*  94 */     Comparator comparator = this.treeMapConverter.unmarshalComparator(reader, context, null);
/*  95 */     TreeMap treeMap; TreeMap treeMap; if (sortedMapField != null) {
/*  96 */       TreeSet possibleResult = comparator == null ? new TreeSet() : new TreeSet(comparator);
/*  97 */       Object backingMap = null;
/*     */       try {
/*  99 */         backingMap = sortedMapField.get(possibleResult);
/*     */       } catch (IllegalAccessException e) {
/* 101 */         throw new ConversionException("Cannot get backing map of TreeSet", e);
/*     */       }
/* 103 */       if ((backingMap instanceof TreeMap)) {
/* 104 */         TreeMap treeMap = (TreeMap)backingMap;
/* 105 */         result = possibleResult;
/*     */       } else {
/* 107 */         treeMap = null;
/*     */       }
/*     */     } else {
/* 110 */       treeMap = null;
/*     */     }
/* 112 */     if (treeMap == null) {
/* 113 */       PresortedSet set = new PresortedSet(comparator);
/* 114 */       result = comparator == null ? new TreeSet() : new TreeSet(comparator);
/* 115 */       populateCollection(reader, context, result, set);
/* 116 */       if (set.size() > 0) {
/* 117 */         result.addAll(set);
/*     */       }
/*     */     } else {
/* 120 */       this.treeMapConverter.populateTreeMap(reader, context, treeMap, comparator);
/*     */     }
/* 122 */     return result;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 126 */     this.treeMapConverter = new TreeMapConverter(mapper())
/*     */     {
/*     */       protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map, Map target)
/*     */       {
/* 130 */         TreeSetConverter.this.populateCollection(reader, context, new AbstractList() { private final Map val$target;
/*     */           
/* 132 */           public boolean add(Object object) { return this.val$target.put(object, object) != null; }
/*     */           
/*     */           public Object get(int location)
/*     */           {
/* 136 */             return null;
/*     */           }
/*     */           
/*     */           public int size() {
/* 140 */             return this.val$target.size();
/*     */           }
/*     */           
/*     */         });
/*     */       }
/* 145 */     };
/* 146 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/TreeSetConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */