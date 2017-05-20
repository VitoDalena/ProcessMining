/*     */ package com.thoughtworks.xstream.converters.javabean;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*     */ import com.thoughtworks.xstream.core.util.OrderRetainingMap;
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.WeakHashMap;
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
/*     */ public class BeanProvider
/*     */ {
/*  37 */   protected static final Object[] NO_PARAMS = new Object[0];
/*     */   private final Comparator propertyNameComparator;
/*  39 */   private final transient Map propertyNameCache = new WeakHashMap();
/*     */   
/*     */   public BeanProvider() {
/*  42 */     this(null);
/*     */   }
/*     */   
/*     */   public BeanProvider(Comparator propertyNameComparator) {
/*  46 */     this.propertyNameComparator = propertyNameComparator;
/*     */   }
/*     */   
/*     */   public Object newInstance(Class type) {
/*     */     try {
/*  51 */       return getDefaultConstrutor(type).newInstance(NO_PARAMS);
/*     */     } catch (InstantiationException e) {
/*  53 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/*  55 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (InvocationTargetException e) {
/*  57 */       if ((e.getTargetException() instanceof RuntimeException))
/*  58 */         throw ((RuntimeException)e.getTargetException());
/*  59 */       if ((e.getTargetException() instanceof Error)) {
/*  60 */         throw ((Error)e.getTargetException());
/*     */       }
/*  62 */       throw new ObjectAccessException("Constructor for " + type.getName() + " threw an exception", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void visitSerializableProperties(Object object, Visitor visitor)
/*     */   {
/*  70 */     PropertyDescriptor[] propertyDescriptors = getSerializableProperties(object);
/*  71 */     for (int i = 0; i < propertyDescriptors.length; i++) {
/*  72 */       PropertyDescriptor property = propertyDescriptors[i];
/*     */       try {
/*  74 */         Method readMethod = property.getReadMethod();
/*  75 */         String name = property.getName();
/*  76 */         Class definedIn = readMethod.getDeclaringClass();
/*  77 */         if (visitor.shouldVisit(name, definedIn)) {
/*  78 */           Object value = readMethod.invoke(object, new Object[0]);
/*  79 */           visitor.visit(name, property.getPropertyType(), definedIn, value);
/*     */         }
/*     */       } catch (IllegalArgumentException e) {
/*  82 */         throw new ObjectAccessException("Could not get property " + object.getClass() + "." + property.getName(), e);
/*     */ 
/*     */       }
/*     */       catch (IllegalAccessException e)
/*     */       {
/*  87 */         throw new ObjectAccessException("Could not get property " + object.getClass() + "." + property.getName(), e);
/*     */ 
/*     */       }
/*     */       catch (InvocationTargetException e)
/*     */       {
/*  92 */         throw new ObjectAccessException("Could not get property " + object.getClass() + "." + property.getName(), e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeProperty(Object object, String propertyName, Object value)
/*     */   {
/* 101 */     PropertyDescriptor property = getProperty(propertyName, object.getClass());
/*     */     try {
/* 103 */       property.getWriteMethod().invoke(object, new Object[] { value });
/*     */     } catch (IllegalArgumentException e) {
/* 105 */       throw new ObjectAccessException("Could not set property " + object.getClass() + "." + property.getName(), e);
/*     */ 
/*     */     }
/*     */     catch (IllegalAccessException e)
/*     */     {
/* 110 */       throw new ObjectAccessException("Could not set property " + object.getClass() + "." + property.getName(), e);
/*     */ 
/*     */     }
/*     */     catch (InvocationTargetException e)
/*     */     {
/* 115 */       throw new ObjectAccessException("Could not set property " + object.getClass() + "." + property.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Class getPropertyType(Object object, String name)
/*     */   {
/* 123 */     return getProperty(name, object.getClass()).getPropertyType();
/*     */   }
/*     */   
/*     */   public boolean propertyDefinedInClass(String name, Class type) {
/* 127 */     return getProperty(name, type) != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canInstantiate(Class type)
/*     */   {
/* 134 */     return getDefaultConstrutor(type) != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Constructor getDefaultConstrutor(Class type)
/*     */   {
/* 143 */     Constructor[] constructors = type.getConstructors();
/* 144 */     for (int i = 0; i < constructors.length; i++) {
/* 145 */       Constructor c = constructors[i];
/* 146 */       if ((c.getParameterTypes().length == 0) && (Modifier.isPublic(c.getModifiers())))
/* 147 */         return c;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */   
/*     */   protected PropertyDescriptor[] getSerializableProperties(Object object) {
/* 153 */     Map nameMap = getNameMap(object.getClass());
/* 154 */     List result = new ArrayList(nameMap.size());
/* 155 */     Set names = nameMap.keySet();
/* 156 */     if (this.propertyNameComparator != null) {
/* 157 */       Set sortedSet = new TreeSet(this.propertyNameComparator);
/* 158 */       sortedSet.addAll(names);
/* 159 */       names = sortedSet;
/*     */     }
/* 161 */     for (Iterator iter = names.iterator(); iter.hasNext();) {
/* 162 */       PropertyDescriptor descriptor = (PropertyDescriptor)nameMap.get(iter.next());
/* 163 */       if (canStreamProperty(descriptor)) {
/* 164 */         result.add(descriptor);
/*     */       }
/*     */     }
/* 167 */     return (PropertyDescriptor[])result.toArray(new PropertyDescriptor[result.size()]);
/*     */   }
/*     */   
/*     */   protected boolean canStreamProperty(PropertyDescriptor descriptor) {
/* 171 */     return (descriptor.getReadMethod() != null) && (descriptor.getWriteMethod() != null);
/*     */   }
/*     */   
/*     */   public boolean propertyWriteable(String name, Class type) {
/* 175 */     PropertyDescriptor property = getProperty(name, type);
/* 176 */     return property.getWriteMethod() != null;
/*     */   }
/*     */   
/*     */   protected PropertyDescriptor getProperty(String name, Class type) {
/* 180 */     return (PropertyDescriptor)getNameMap(type).get(name);
/*     */   }
/*     */   
/*     */   private Map getNameMap(Class type) {
/* 184 */     Map nameMap = (Map)this.propertyNameCache.get(type);
/* 185 */     if (nameMap == null) {
/*     */       BeanInfo beanInfo;
/*     */       try {
/* 188 */         beanInfo = Introspector.getBeanInfo(type, Object.class);
/*     */       } catch (IntrospectionException e) {
/* 190 */         throw new ObjectAccessException("Cannot get BeanInfo of type " + type.getName(), e);
/*     */       }
/* 192 */       nameMap = new OrderRetainingMap();
/* 193 */       this.propertyNameCache.put(type, nameMap);
/* 194 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 195 */       for (int i = 0; i < propertyDescriptors.length; i++) {
/* 196 */         PropertyDescriptor descriptor = propertyDescriptors[i];
/* 197 */         nameMap.put(descriptor.getName(), descriptor);
/*     */       }
/*     */     }
/* 200 */     return nameMap;
/*     */   }
/*     */   
/*     */   public static abstract interface Visitor
/*     */   {
/*     */     public abstract boolean shouldVisit(String paramString, Class paramClass);
/*     */     
/*     */     public abstract void visit(String paramString, Class paramClass1, Class paramClass2, Object paramObject);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/javabean/BeanProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */