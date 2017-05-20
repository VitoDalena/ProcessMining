/*     */ package com.thoughtworks.xstream.converters.collections;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.Fields;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PropertiesConverter
/*     */   implements Converter
/*     */ {
/*     */   private static final Field defaultsField;
/*     */   private final boolean sort;
/*     */   
/*     */   static
/*     */   {
/*  49 */     Field field = null;
/*     */     try {
/*  51 */       field = Fields.find(Properties.class, "defaults");
/*     */     }
/*     */     catch (SecurityException ex) {}catch (RuntimeException ex)
/*     */     {
/*  55 */       throw new ExceptionInInitializerError("No field 'defaults' in type Properties found");
/*     */     }
/*  57 */     defaultsField = field;
/*     */   }
/*     */   
/*     */   public PropertiesConverter()
/*     */   {
/*  62 */     this(false);
/*     */   }
/*     */   
/*     */   public PropertiesConverter(boolean sort) {
/*  66 */     this.sort = sort;
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  70 */     return Properties.class == type;
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  74 */     Properties properties = (Properties)source;
/*  75 */     Map map = this.sort ? new TreeMap(properties) : properties;
/*  76 */     for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
/*  77 */       Map.Entry entry = (Map.Entry)iterator.next();
/*  78 */       writer.startNode("property");
/*  79 */       writer.addAttribute("name", entry.getKey().toString());
/*  80 */       writer.addAttribute("value", entry.getValue().toString());
/*  81 */       writer.endNode();
/*     */     }
/*  83 */     if (defaultsField != null) {
/*  84 */       Properties defaults = (Properties)Fields.read(defaultsField, properties);
/*  85 */       if (defaults != null) {
/*  86 */         writer.startNode("defaults");
/*  87 */         marshal(defaults, writer, context);
/*  88 */         writer.endNode();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  94 */     Properties properties = new Properties();
/*  95 */     Properties defaults = null;
/*  96 */     while (reader.hasMoreChildren()) {
/*  97 */       reader.moveDown();
/*  98 */       if (reader.getNodeName().equals("defaults")) {
/*  99 */         defaults = (Properties)unmarshal(reader, context);
/*     */       } else {
/* 101 */         String name = reader.getAttribute("name");
/* 102 */         String value = reader.getAttribute("value");
/* 103 */         properties.setProperty(name, value);
/*     */       }
/* 105 */       reader.moveUp();
/*     */     }
/* 107 */     if (defaults == null) {
/* 108 */       return properties;
/*     */     }
/* 110 */     Properties propertiesWithDefaults = new Properties(defaults);
/* 111 */     propertiesWithDefaults.putAll(properties);
/* 112 */     return propertiesWithDefaults;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/PropertiesConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */