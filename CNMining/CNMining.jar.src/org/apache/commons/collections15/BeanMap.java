/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections15.list.UnmodifiableList;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
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
/*     */ public class BeanMap
/*     */   extends AbstractMap<String, Object>
/*     */   implements Cloneable
/*     */ {
/*     */   private transient Object bean;
/*  49 */   private transient HashMap<String, Method> readMethods = new HashMap();
/*  50 */   private transient HashMap<String, Method> writeMethods = new HashMap();
/*  51 */   private transient HashMap<String, Class> types = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  56 */   public static final Object[] NULL_ARGUMENTS = new Object[0];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */   public static HashMap defaultTransformers = new HashMap();
/*     */   
/*     */   static {
/*  65 */     defaultTransformers.put(Boolean.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  67 */         return Boolean.valueOf(input.toString());
/*     */       }
/*  69 */     });
/*  70 */     defaultTransformers.put(Character.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  72 */         return new Character(input.toString().charAt(0));
/*     */       }
/*  74 */     });
/*  75 */     defaultTransformers.put(Byte.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  77 */         return Byte.valueOf(input.toString());
/*     */       }
/*  79 */     });
/*  80 */     defaultTransformers.put(Short.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  82 */         return Short.valueOf(input.toString());
/*     */       }
/*  84 */     });
/*  85 */     defaultTransformers.put(Integer.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  87 */         return Integer.valueOf(input.toString());
/*     */       }
/*  89 */     });
/*  90 */     defaultTransformers.put(Long.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  92 */         return Long.valueOf(input.toString());
/*     */       }
/*  94 */     });
/*  95 */     defaultTransformers.put(Float.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/*  97 */         return Float.valueOf(input.toString());
/*     */       }
/*  99 */     });
/* 100 */     defaultTransformers.put(Double.TYPE, new Transformer() {
/*     */       public Object transform(Object input) {
/* 102 */         return Double.valueOf(input.toString());
/*     */       }
/*     */     });
/*     */   }
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
/*     */   public BeanMap(Object bean)
/*     */   {
/* 125 */     this.bean = bean;
/* 126 */     initialise();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 133 */     return "BeanMap<" + String.valueOf(this.bean) + ">";
/*     */   }
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 160 */     BeanMap newMap = (BeanMap)super.clone();
/*     */     
/* 162 */     if (this.bean == null)
/*     */     {
/*     */ 
/* 165 */       return newMap;
/*     */     }
/*     */     
/* 168 */     Object newBean = null;
/* 169 */     Class beanClass = null;
/*     */     try {
/* 171 */       beanClass = this.bean.getClass();
/* 172 */       newBean = beanClass.newInstance();
/*     */     }
/*     */     catch (Exception e) {
/* 175 */       throw new CloneNotSupportedException("Unable to instantiate the underlying bean \"" + beanClass.getName() + "\": " + e);
/*     */     }
/*     */     try
/*     */     {
/* 179 */       newMap.setBean(newBean);
/*     */     } catch (Exception exception) {
/* 181 */       throw new CloneNotSupportedException("Unable to set bean in the cloned bean map: " + exception);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 188 */       Iterator<String> readableKeys = this.readMethods.keySet().iterator();
/* 189 */       while (readableKeys.hasNext()) {
/* 190 */         String key = (String)readableKeys.next();
/* 191 */         if (getWriteMethod(key) != null) {
/* 192 */           newMap.put(key, get(key));
/*     */         }
/*     */       }
/*     */     } catch (Exception exception) {
/* 196 */       throw new CloneNotSupportedException("Unable to copy bean values to cloned bean map: " + exception);
/*     */     }
/*     */     
/* 199 */     return newMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void putAllWriteable(BeanMap map)
/*     */   {
/* 209 */     Iterator<String> readableKeys = map.readMethods.keySet().iterator();
/* 210 */     while (readableKeys.hasNext()) {
/* 211 */       String key = (String)readableKeys.next();
/* 212 */       if (getWriteMethod(key) != null) {
/* 213 */         put(key, map.get(key));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 228 */     if (this.bean == null) { return;
/*     */     }
/* 230 */     Class beanClass = null;
/*     */     try {
/* 232 */       beanClass = this.bean.getClass();
/* 233 */       this.bean = beanClass.newInstance();
/*     */     } catch (Exception e) {
/* 235 */       throw new UnsupportedOperationException("Could not create new instance of class: " + beanClass);
/*     */     }
/*     */   }
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
/*     */   public boolean containsKey(String name)
/*     */   {
/* 255 */     Method method = getReadMethod(name);
/* 256 */     return method != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object value)
/*     */   {
/* 269 */     return super.containsValue(value);
/*     */   }
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
/*     */   public Object get(String name)
/*     */   {
/* 288 */     if (this.bean != null) {
/* 289 */       Method method = getReadMethod(name);
/* 290 */       if (method != null) {
/*     */         try {
/* 292 */           return method.invoke(this.bean, NULL_ARGUMENTS);
/*     */         } catch (IllegalAccessException e) {
/* 294 */           logWarn(e);
/*     */         } catch (IllegalArgumentException e) {
/* 296 */           logWarn(e);
/*     */         } catch (InvocationTargetException e) {
/* 298 */           logWarn(e);
/*     */         } catch (NullPointerException e) {
/* 300 */           logWarn(e);
/*     */         }
/*     */       }
/*     */     }
/* 304 */     return null;
/*     */   }
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
/*     */   public Object put(String name, Object value)
/*     */     throws IllegalArgumentException, ClassCastException
/*     */   {
/* 319 */     if (this.bean != null) {
/* 320 */       Object oldValue = get(name);
/* 321 */       Method method = getWriteMethod(name);
/* 322 */       if (method == null) {
/* 323 */         throw new IllegalArgumentException("The bean of type: " + this.bean.getClass().getName() + " has no property called: " + name);
/*     */       }
/*     */       try {
/* 326 */         Object[] arguments = createWriteMethodArguments(method, value);
/* 327 */         method.invoke(this.bean, arguments);
/*     */         
/* 329 */         Object newValue = get(name);
/* 330 */         firePropertyChange(name, oldValue, newValue);
/*     */       } catch (InvocationTargetException e) {
/* 332 */         logInfo(e);
/* 333 */         throw new IllegalArgumentException(e.getMessage());
/*     */       } catch (IllegalAccessException e) {
/* 335 */         logInfo(e);
/* 336 */         throw new IllegalArgumentException(e.getMessage());
/*     */       }
/* 338 */       return oldValue;
/*     */     }
/* 340 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 349 */     return this.readMethods.size();
/*     */   }
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
/*     */   public Set<String> keySet()
/*     */   {
/* 364 */     return UnmodifiableSet.decorate(this.readMethods.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Map.Entry<String, Object>> entrySet()
/*     */   {
/* 375 */     UnmodifiableSet.decorate(new AbstractSet() {
/*     */       public Iterator<Map.Entry<String, Object>> iterator() {
/* 377 */         return BeanMap.this.entryIterator();
/*     */       }
/*     */       
/*     */       public int size() {
/* 381 */         return BeanMap.this.readMethods.size();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<Object> values()
/*     */   {
/* 393 */     ArrayList answer = new ArrayList(this.readMethods.size());
/* 394 */     for (Iterator iter = valueIterator(); iter.hasNext();) {
/* 395 */       answer.add(iter.next());
/*     */     }
/* 397 */     return UnmodifiableList.decorate(answer);
/*     */   }
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
/*     */   public Class getType(String name)
/*     */   {
/* 412 */     return (Class)this.types.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<String> keyIterator()
/*     */   {
/* 423 */     return this.readMethods.keySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<Object> valueIterator()
/*     */   {
/* 432 */     final Iterator<String> iter = keyIterator();
/* 433 */     new Iterator() {
/*     */       public boolean hasNext() {
/* 435 */         return iter.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 439 */         Object key = iter.next();
/* 440 */         return BeanMap.this.get(key);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 444 */         throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<Map.Entry<String, Object>> entryIterator()
/*     */   {
/* 455 */     final Iterator<String> iter = keyIterator();
/* 456 */     new Iterator() {
/*     */       public boolean hasNext() {
/* 458 */         return iter.hasNext();
/*     */       }
/*     */       
/*     */       public Map.Entry<String, Object> next() {
/* 462 */         String key = (String)iter.next();
/* 463 */         Object value = BeanMap.this.get(key);
/* 464 */         return new BeanMap.MyMapEntry(BeanMap.this, key, value);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 468 */         throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */       }
/*     */     };
/*     */   }
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
/*     */   public Object getBean()
/*     */   {
/* 484 */     return this.bean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBean(Object newBean)
/*     */   {
/* 494 */     this.bean = newBean;
/* 495 */     reinitialise();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Method getReadMethod(String name)
/*     */   {
/* 505 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Method getWriteMethod(String name)
/*     */   {
/* 515 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void reinitialise()
/*     */   {
/* 527 */     this.readMethods.clear();
/* 528 */     this.writeMethods.clear();
/* 529 */     this.types.clear();
/* 530 */     initialise();
/*     */   }
/*     */   
/*     */   private void initialise() {
/* 534 */     if (getBean() == null) { return;
/*     */     }
/* 536 */     Class beanClass = getBean().getClass();
/*     */     try
/*     */     {
/* 539 */       BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/* 540 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 541 */       if (propertyDescriptors != null) {
/* 542 */         for (int i = 0; i < propertyDescriptors.length; i++) {
/* 543 */           PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
/* 544 */           if (propertyDescriptor != null) {
/* 545 */             String name = propertyDescriptor.getName();
/* 546 */             Method readMethod = propertyDescriptor.getReadMethod();
/* 547 */             Method writeMethod = propertyDescriptor.getWriteMethod();
/* 548 */             Class aType = propertyDescriptor.getPropertyType();
/*     */             
/* 550 */             if (readMethod != null) {
/* 551 */               this.readMethods.put(name, readMethod);
/*     */             }
/* 553 */             if (this.writeMethods != null) {
/* 554 */               this.writeMethods.put(name, writeMethod);
/*     */             }
/* 556 */             this.types.put(name, aType);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IntrospectionException e) {
/* 561 */       logWarn(e);
/*     */     }
/*     */   }
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
/*     */   protected static class MyMapEntry
/*     */     extends AbstractMapEntry<String, Object>
/*     */   {
/*     */     private BeanMap owner;
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
/*     */     protected MyMapEntry(BeanMap owner, String key, Object value)
/*     */     {
/* 594 */       super(value);
/* 595 */       this.owner = owner;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Object setValue(Object value)
/*     */     {
/* 605 */       String key = (String)getKey();
/* 606 */       Object oldValue = this.owner.get(key);
/*     */       
/* 608 */       this.owner.put(key, value);
/* 609 */       Object newValue = this.owner.get(key);
/* 610 */       super.setValue(newValue);
/* 611 */       return oldValue;
/*     */     }
/*     */   }
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
/*     */   protected Object[] createWriteMethodArguments(Method method, Object value)
/*     */     throws IllegalAccessException, ClassCastException
/*     */   {
/*     */     try
/*     */     {
/* 631 */       if (value != null) {
/* 632 */         Class[] types = method.getParameterTypes();
/* 633 */         if ((types != null) && (types.length > 0)) {
/* 634 */           Class paramType = types[0];
/* 635 */           if (!paramType.isAssignableFrom(value.getClass())) {
/* 636 */             value = convertType(paramType, value);
/*     */           }
/*     */         }
/*     */       }
/* 640 */       return new Object[] { value };
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 643 */       logInfo(e);
/* 644 */       throw new IllegalArgumentException(e.getMessage());
/*     */     } catch (InstantiationException e) {
/* 646 */       logInfo(e);
/* 647 */       throw new IllegalArgumentException(e.getMessage());
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object convertType(Class newType, Object value)
/*     */     throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
/*     */   {
/* 685 */     Class[] types = { value.getClass() };
/*     */     try {
/* 687 */       Constructor constructor = newType.getConstructor(types);
/* 688 */       Object[] arguments = { value };
/* 689 */       return constructor.newInstance(arguments);
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 692 */       Transformer transformer = getTypeTransformer(newType);
/* 693 */       if (transformer != null)
/* 694 */         return transformer.transform(value);
/*     */     }
/* 696 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Transformer getTypeTransformer(Class aType)
/*     */   {
/* 708 */     return (Transformer)defaultTransformers.get(aType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void logInfo(Exception ex)
/*     */   {
/* 719 */     System.out.println("INFO: Exception: " + ex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void logWarn(Exception ex)
/*     */   {
/* 730 */     System.out.println("WARN: Exception: " + ex);
/* 731 */     ex.printStackTrace();
/*     */   }
/*     */   
/*     */   public BeanMap() {}
/*     */   
/*     */   protected void firePropertyChange(String key, Object oldValue, Object newValue) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BeanMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */