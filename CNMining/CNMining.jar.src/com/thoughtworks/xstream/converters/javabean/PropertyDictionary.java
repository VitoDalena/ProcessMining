/*     */ package com.thoughtworks.xstream.converters.javabean;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*     */ import java.beans.Introspector;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class PropertyDictionary
/*     */ {
/*     */   private final Map keyedByPropertyNameCache;
/*     */   
/*     */   public PropertyDictionary()
/*     */   {
/*  34 */     this.keyedByPropertyNameCache = Collections.synchronizedMap(new HashMap());
/*     */   }
/*     */   
/*  37 */   public Iterator serializablePropertiesFor(Class cls) { return buildMap(cls).values().iterator(); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BeanProperty property(Class cls, String name)
/*     */   {
/*  47 */     Map properties = buildMap(cls);
/*  48 */     BeanProperty property = (BeanProperty)properties.get(name);
/*  49 */     if (property == null) {
/*  50 */       throw new ObjectAccessException("No such property " + cls.getName() + "." + name);
/*     */     }
/*  52 */     return property;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map buildMap(Class cls)
/*     */   {
/*  62 */     String clsName = cls.getName();
/*  63 */     if (!this.keyedByPropertyNameCache.containsKey(clsName)) {
/*  64 */       synchronized (this.keyedByPropertyNameCache) {
/*  65 */         if (!this.keyedByPropertyNameCache.containsKey(clsName))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*  70 */           Map propertyMap = new HashMap();
/*  71 */           Method[] methods = cls.getMethods();
/*     */           
/*  73 */           for (int i = 0; i < methods.length; i++) {
/*  74 */             if ((Modifier.isPublic(methods[i].getModifiers())) && (!Modifier.isStatic(methods[i].getModifiers())))
/*     */             {
/*     */ 
/*     */ 
/*  78 */               String methodName = methods[i].getName();
/*  79 */               Class[] parameters = methods[i].getParameterTypes();
/*  80 */               Class returnType = methods[i].getReturnType();
/*     */               
/*  82 */               if (((methodName.startsWith("get")) || (methodName.startsWith("is"))) && (parameters.length == 0) && (returnType != Void.TYPE)) { String propertyName;
/*     */                 String propertyName;
/*  84 */                 if (methodName.startsWith("get")) {
/*  85 */                   propertyName = Introspector.decapitalize(methodName.substring(3));
/*     */                 } else {
/*  87 */                   propertyName = Introspector.decapitalize(methodName.substring(2));
/*     */                 }
/*  89 */                 BeanProperty property = getBeanProperty(propertyMap, cls, propertyName, returnType);
/*     */                 
/*  91 */                 property.setGetterMethod(methods[i]);
/*  92 */               } else if ((methodName.startsWith("set")) && (parameters.length == 1) && (returnType == Void.TYPE))
/*     */               {
/*  94 */                 String propertyName = Introspector.decapitalize(methodName.substring(3));
/*  95 */                 BeanProperty property = getBeanProperty(propertyMap, cls, propertyName, parameters[0]);
/*     */                 
/*  97 */                 property.setSetterMethod(methods[i]);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 103 */           List serializableProperties = new ArrayList();
/* 104 */           for (Iterator it = propertyMap.values().iterator(); it.hasNext();) {
/* 105 */             BeanProperty property = (BeanProperty)it.next();
/* 106 */             if ((property.isReadable()) && (property.isWritable())) {
/* 107 */               serializableProperties.add(property);
/*     */             }
/*     */           }
/* 110 */           Collections.sort(serializableProperties, new BeanPropertyComparator(null));
/*     */           
/*     */ 
/* 113 */           Map keyedByFieldName = new OrderRetainingMap(null);
/* 114 */           for (Iterator it = serializableProperties.iterator(); it.hasNext();) {
/* 115 */             BeanProperty property = (BeanProperty)it.next();
/* 116 */             keyedByFieldName.put(property.getName(), property);
/*     */           }
/*     */           
/* 119 */           this.keyedByPropertyNameCache.put(clsName, keyedByFieldName);
/*     */         }
/*     */       }
/*     */     }
/* 123 */     return (Map)this.keyedByPropertyNameCache.get(clsName);
/*     */   }
/*     */   
/*     */   private BeanProperty getBeanProperty(Map propertyMap, Class cls, String propertyName, Class type) {
/* 127 */     PropertyKey key = new PropertyKey(propertyName, type);
/* 128 */     BeanProperty property = (BeanProperty)propertyMap.get(key);
/* 129 */     if (property == null) {
/* 130 */       property = new BeanProperty(cls, propertyName, type);
/* 131 */       propertyMap.put(key, property);
/*     */     }
/* 133 */     return property;
/*     */   }
/*     */   
/*     */ 
/*     */   private static class PropertyKey
/*     */   {
/*     */     private String propertyName;
/*     */     
/*     */     private Class propertyType;
/*     */     
/*     */ 
/*     */     public PropertyKey(String propertyName, Class propertyType)
/*     */     {
/* 146 */       this.propertyName = propertyName;
/* 147 */       this.propertyType = propertyType;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 151 */       if (this == o)
/* 152 */         return true;
/* 153 */       if (!(o instanceof PropertyKey)) {
/* 154 */         return false;
/*     */       }
/* 156 */       PropertyKey propertyKey = (PropertyKey)o;
/*     */       
/* 158 */       if (this.propertyName != null ? !this.propertyName.equals(propertyKey.propertyName) : propertyKey.propertyName != null)
/*     */       {
/* 160 */         return false; }
/* 161 */       if (this.propertyType != null ? !this.propertyType.equals(propertyKey.propertyType) : propertyKey.propertyType != null)
/*     */       {
/* 163 */         return false;
/*     */       }
/* 165 */       return true;
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/* 170 */       int result = this.propertyName != null ? this.propertyName.hashCode() : 0;
/* 171 */       result = 29 * result + (this.propertyType != null ? this.propertyType.hashCode() : 0);
/* 172 */       return result;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 176 */       return "PropertyKey{propertyName='" + this.propertyName + "'" + ", propertyType=" + this.propertyType + "}";
/*     */     }
/*     */   }
/*     */   
/*     */   private static class BeanPropertyComparator
/*     */     implements Comparator
/*     */   {
/*     */     BeanPropertyComparator(PropertyDictionary.1 x0)
/*     */     {
/* 185 */       this();
/*     */     }
/*     */     
/* 188 */     public int compare(Object o1, Object o2) { return ((BeanProperty)o1).getName().compareTo(((BeanProperty)o2).getName()); }
/*     */     
/*     */     private BeanPropertyComparator() {}
/*     */   }
/*     */   
/* 193 */   private static class OrderRetainingMap extends HashMap { OrderRetainingMap(PropertyDictionary.1 x0) { this(); }
/*     */     
/* 195 */     private List valueOrder = new ArrayList();
/*     */     
/*     */     public Object put(Object key, Object value) {
/* 198 */       this.valueOrder.add(value);
/* 199 */       return super.put(key, value);
/*     */     }
/*     */     
/*     */     public Collection values() {
/* 203 */       return Collections.unmodifiableList(this.valueOrder);
/*     */     }
/*     */     
/*     */     private OrderRetainingMap() {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/javabean/PropertyDictionary.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */