/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import com.thoughtworks.xstream.InitializationException;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/*     */ import com.thoughtworks.xstream.annotations.XStreamConverter;
/*     */ import com.thoughtworks.xstream.annotations.XStreamConverters;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;
/*     */ import com.thoughtworks.xstream.annotations.XStreamInclude;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterMatcher;
/*     */ import com.thoughtworks.xstream.converters.ConverterRegistry;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*     */ import com.thoughtworks.xstream.core.JVM;
/*     */ import com.thoughtworks.xstream.core.util.DependencyInjectionFactory;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.GenericArrayType;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.TypeVariable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationMapper
/*     */   extends MapperWrapper
/*     */   implements AnnotationConfiguration
/*     */ {
/*     */   private boolean locked;
/*     */   private final Object[] arguments;
/*     */   private final ConverterRegistry converterRegistry;
/*     */   private final ClassAliasingMapper classAliasingMapper;
/*     */   private final DefaultImplementationsMapper defaultImplementationsMapper;
/*     */   private final ImplicitCollectionMapper implicitCollectionMapper;
/*     */   private final FieldAliasingMapper fieldAliasingMapper;
/*     */   private final AttributeMapper attributeMapper;
/*     */   private final LocalConversionMapper localConversionMapper;
/*  69 */   private final Map<Class<?>, Map<Object, Converter>> converterCache = new HashMap();
/*     */   
/*  71 */   private final Set<Class<?>> annotatedTypes = new WeakHashSet(null);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnnotationMapper(Mapper wrapped, ConverterRegistry converterRegistry, ClassLoader classLoader, ReflectionProvider reflectionProvider, JVM jvm)
/*     */   {
/*  83 */     super(wrapped);
/*  84 */     this.converterRegistry = converterRegistry;
/*  85 */     this.annotatedTypes.add(Object.class);
/*  86 */     this.classAliasingMapper = ((ClassAliasingMapper)lookupMapperOfType(ClassAliasingMapper.class));
/*  87 */     this.defaultImplementationsMapper = ((DefaultImplementationsMapper)lookupMapperOfType(DefaultImplementationsMapper.class));
/*  88 */     this.implicitCollectionMapper = ((ImplicitCollectionMapper)lookupMapperOfType(ImplicitCollectionMapper.class));
/*  89 */     this.fieldAliasingMapper = ((FieldAliasingMapper)lookupMapperOfType(FieldAliasingMapper.class));
/*  90 */     this.attributeMapper = ((AttributeMapper)lookupMapperOfType(AttributeMapper.class));
/*  91 */     this.localConversionMapper = ((LocalConversionMapper)lookupMapperOfType(LocalConversionMapper.class));
/*  92 */     this.locked = true;
/*  93 */     this.arguments = new Object[] { this, classLoader, reflectionProvider, jvm };
/*     */   }
/*     */   
/*     */   public String realMember(Class type, String serialized)
/*     */   {
/*  98 */     if (!this.locked) {
/*  99 */       processAnnotations(type);
/*     */     }
/* 101 */     return super.realMember(type, serialized);
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type)
/*     */   {
/* 106 */     if (!this.locked) {
/* 107 */       processAnnotations(type);
/*     */     }
/* 109 */     return super.serializedClass(type);
/*     */   }
/*     */   
/*     */   public Class defaultImplementationOf(Class type)
/*     */   {
/* 114 */     if (!this.locked) {
/* 115 */       processAnnotations(type);
/*     */     }
/* 117 */     Class defaultImplementation = super.defaultImplementationOf(type);
/* 118 */     if (!this.locked) {
/* 119 */       processAnnotations(defaultImplementation);
/*     */     }
/* 121 */     return defaultImplementation;
/*     */   }
/*     */   
/*     */   public Converter getLocalConverter(Class definedIn, String fieldName)
/*     */   {
/* 126 */     if (!this.locked) {
/* 127 */       processAnnotations(definedIn);
/*     */     }
/* 129 */     return super.getLocalConverter(definedIn, fieldName);
/*     */   }
/*     */   
/*     */   public void autodetectAnnotations(boolean mode) {
/* 133 */     this.locked = (!mode);
/*     */   }
/*     */   
/*     */   public void processAnnotations(Class[] initialTypes) {
/* 137 */     if ((initialTypes == null) || (initialTypes.length == 0)) {
/* 138 */       return;
/*     */     }
/* 140 */     this.locked = true;
/* 141 */     synchronized (this.annotatedTypes) {
/* 142 */       Set<Class<?>> types = new UnprocessedTypesSet(null);
/* 143 */       for (Class initialType : initialTypes) {
/* 144 */         types.add(initialType);
/*     */       }
/* 146 */       processTypes(types);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processAnnotations(Class initialType) {
/* 151 */     if (initialType == null) {
/* 152 */       return;
/*     */     }
/* 154 */     synchronized (this.annotatedTypes) {
/* 155 */       Set<Class<?>> types = new UnprocessedTypesSet(null);
/* 156 */       types.add(initialType);
/* 157 */       processTypes(types);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processTypes(Set<Class<?>> types) {
/* 162 */     while (!types.isEmpty()) {
/* 163 */       Iterator<Class<?>> iter = types.iterator();
/* 164 */       Class<?> type = (Class)iter.next();
/* 165 */       iter.remove();
/*     */       
/* 167 */       if (this.annotatedTypes.add(type))
/* 168 */         if (!type.isPrimitive())
/*     */         {
/*     */ 
/*     */ 
/* 172 */           addParametrizedTypes(type, types);
/*     */           
/* 174 */           processConverterAnnotations(type);
/* 175 */           processAliasAnnotation(type, types);
/*     */           
/* 177 */           if (!type.isInterface())
/*     */           {
/*     */ 
/*     */ 
/* 181 */             processImplicitCollectionAnnotation(type);
/*     */             
/* 183 */             Field[] fields = type.getDeclaredFields();
/* 184 */             for (int i = 0; i < fields.length; i++) {
/* 185 */               Field field = fields[i];
/* 186 */               if ((!field.isEnumConstant()) && ((field.getModifiers() & 0x88) <= 0))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/* 191 */                 addParametrizedTypes(field.getGenericType(), types);
/*     */                 
/* 193 */                 if (!field.isSynthetic())
/*     */                 {
/*     */ 
/*     */ 
/* 197 */                   processFieldAliasAnnotation(field);
/* 198 */                   processAsAttributeAnnotation(field);
/* 199 */                   processImplicitAnnotation(field);
/* 200 */                   processOmitFieldAnnotation(field);
/* 201 */                   processLocalConverterAnnotation(field);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } } }
/*     */   
/* 208 */   private void addParametrizedTypes(Type type, final Set<Class<?>> types) { final Set<Type> processedTypes = new HashSet();
/* 209 */     Set<Type> localTypes = new LinkedHashSet()
/*     */     {
/*     */       public boolean add(Type o)
/*     */       {
/* 213 */         if ((o instanceof Class)) {
/* 214 */           return types.add((Class)o);
/*     */         }
/* 216 */         return (o == null) || (processedTypes.contains(o)) ? false : super.add(o);
/*     */       }
/*     */     };
/*     */     
/* 220 */     while (type != null) {
/* 221 */       processedTypes.add(type);
/* 222 */       if ((type instanceof Class)) {
/* 223 */         Class<?> clazz = (Class)type;
/* 224 */         types.add(clazz);
/* 225 */         if (!clazz.isPrimitive()) {
/* 226 */           TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
/* 227 */           for (TypeVariable<?> typeVariable : typeParameters) {
/* 228 */             localTypes.add(typeVariable);
/*     */           }
/* 230 */           localTypes.add(clazz.getGenericSuperclass());
/* 231 */           for (Type iface : clazz.getGenericInterfaces()) {
/* 232 */             localTypes.add(iface);
/*     */           }
/*     */         }
/* 235 */       } else if ((type instanceof TypeVariable)) {
/* 236 */         TypeVariable<?> typeVariable = (TypeVariable)type;
/* 237 */         Type[] bounds = typeVariable.getBounds();
/* 238 */         for (Type bound : bounds) {
/* 239 */           localTypes.add(bound);
/*     */         }
/* 241 */       } else if ((type instanceof ParameterizedType)) {
/* 242 */         ParameterizedType parametrizedType = (ParameterizedType)type;
/* 243 */         localTypes.add(parametrizedType.getRawType());
/* 244 */         Type[] actualArguments = parametrizedType.getActualTypeArguments();
/* 245 */         for (Type actualArgument : actualArguments) {
/* 246 */           localTypes.add(actualArgument);
/*     */         }
/* 248 */       } else if ((type instanceof GenericArrayType)) {
/* 249 */         GenericArrayType arrayType = (GenericArrayType)type;
/* 250 */         localTypes.add(arrayType.getGenericComponentType());
/*     */       }
/*     */       
/* 253 */       if (!localTypes.isEmpty()) {
/* 254 */         Iterator<Type> iter = localTypes.iterator();
/* 255 */         type = (Type)iter.next();
/* 256 */         iter.remove();
/*     */       } else {
/* 258 */         type = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void processConverterAnnotations(Class<?> type) { XStreamConverter converterAnnotation;
/* 264 */     if (this.converterRegistry != null) {
/* 265 */       XStreamConverters convertersAnnotation = (XStreamConverters)type.getAnnotation(XStreamConverters.class);
/*     */       
/* 267 */       converterAnnotation = (XStreamConverter)type.getAnnotation(XStreamConverter.class);
/*     */       
/* 269 */       List<XStreamConverter> annotations = convertersAnnotation != null ? new ArrayList(Arrays.asList(convertersAnnotation.value())) : new ArrayList();
/*     */       
/*     */ 
/* 272 */       if (converterAnnotation != null) {
/* 273 */         annotations.add(converterAnnotation);
/*     */       }
/* 275 */       for (XStreamConverter annotation : annotations) {
/* 276 */         Class<? extends ConverterMatcher> converterType = annotation.value();
/* 277 */         Converter converter = cacheConverter(converterType, converterAnnotation != null ? type : null);
/*     */         
/* 279 */         if (converter != null) {
/* 280 */           if ((converterAnnotation != null) || (converter.canConvert(type))) {
/* 281 */             this.converterRegistry.registerConverter(converter, 0);
/*     */           } else {
/* 283 */             throw new InitializationException("Converter " + converterType.getName() + " cannot handle annotated class " + type.getName());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void processAliasAnnotation(Class<?> type, Set<Class<?>> types)
/*     */   {
/* 294 */     XStreamAlias aliasAnnotation = (XStreamAlias)type.getAnnotation(XStreamAlias.class);
/* 295 */     if (aliasAnnotation != null) {
/* 296 */       if (this.classAliasingMapper == null) {
/* 297 */         throw new InitializationException("No " + ClassAliasingMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 301 */       if (aliasAnnotation.impl() != Void.class)
/*     */       {
/* 303 */         this.classAliasingMapper.addClassAlias(aliasAnnotation.value(), type);
/* 304 */         this.defaultImplementationsMapper.addDefaultImplementation(aliasAnnotation.impl(), type);
/*     */         
/* 306 */         if (type.isInterface()) {
/* 307 */           types.add(aliasAnnotation.impl());
/*     */         }
/*     */       } else {
/* 310 */         this.classAliasingMapper.addClassAlias(aliasAnnotation.value(), type);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   private void processImplicitCollectionAnnotation(Class<?> type) {
/* 317 */     XStreamImplicitCollection implicitColAnnotation = (XStreamImplicitCollection)type.getAnnotation(XStreamImplicitCollection.class);
/*     */     
/* 319 */     if (implicitColAnnotation != null) {
/* 320 */       if (this.implicitCollectionMapper == null) {
/* 321 */         throw new InitializationException("No " + ImplicitCollectionMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 325 */       String fieldName = implicitColAnnotation.value();
/* 326 */       String itemFieldName = implicitColAnnotation.item();
/*     */       Field field;
/*     */       try {
/* 329 */         field = type.getDeclaredField(fieldName);
/*     */       } catch (NoSuchFieldException e) {
/* 331 */         throw new InitializationException(type.getName() + " does not have a field named '" + fieldName + "' as required by " + XStreamImplicitCollection.class.getName());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 337 */       Class itemType = null;
/* 338 */       Type genericType = field.getGenericType();
/* 339 */       if ((genericType instanceof ParameterizedType)) {
/* 340 */         Type typeArgument = ((ParameterizedType)genericType).getActualTypeArguments()[0];
/*     */         
/* 342 */         itemType = getClass(typeArgument);
/*     */       }
/* 344 */       if (itemType == null) {
/* 345 */         this.implicitCollectionMapper.add(type, fieldName, null, Object.class);
/*     */       }
/* 347 */       else if (itemFieldName.equals("")) {
/* 348 */         this.implicitCollectionMapper.add(type, fieldName, null, itemType);
/*     */       } else {
/* 350 */         this.implicitCollectionMapper.add(type, fieldName, itemFieldName, itemType);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void processFieldAliasAnnotation(Field field)
/*     */   {
/* 357 */     XStreamAlias aliasAnnotation = (XStreamAlias)field.getAnnotation(XStreamAlias.class);
/* 358 */     if (aliasAnnotation != null) {
/* 359 */       if (this.fieldAliasingMapper == null) {
/* 360 */         throw new InitializationException("No " + FieldAliasingMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 364 */       this.fieldAliasingMapper.addFieldAlias(aliasAnnotation.value(), field.getDeclaringClass(), field.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   private void processAsAttributeAnnotation(Field field)
/*     */   {
/* 370 */     XStreamAsAttribute asAttributeAnnotation = (XStreamAsAttribute)field.getAnnotation(XStreamAsAttribute.class);
/*     */     
/* 372 */     if (asAttributeAnnotation != null) {
/* 373 */       if (this.attributeMapper == null) {
/* 374 */         throw new InitializationException("No " + AttributeMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 378 */       this.attributeMapper.addAttributeFor(field);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processImplicitAnnotation(Field field) {
/* 383 */     XStreamImplicit implicitAnnotation = (XStreamImplicit)field.getAnnotation(XStreamImplicit.class);
/* 384 */     if (implicitAnnotation != null) {
/* 385 */       if (this.implicitCollectionMapper == null) {
/* 386 */         throw new InitializationException("No " + ImplicitCollectionMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 390 */       String fieldName = field.getName();
/* 391 */       String itemFieldName = implicitAnnotation.itemFieldName();
/* 392 */       Class itemType = null;
/* 393 */       Type genericType = field.getGenericType();
/* 394 */       if ((genericType instanceof ParameterizedType)) {
/* 395 */         Type typeArgument = ((ParameterizedType)genericType).getActualTypeArguments()[0];
/*     */         
/* 397 */         itemType = getClass(typeArgument);
/*     */       }
/* 399 */       if ((itemFieldName != null) && (!"".equals(itemFieldName))) {
/* 400 */         this.implicitCollectionMapper.add(field.getDeclaringClass(), fieldName, itemFieldName, itemType);
/*     */       }
/*     */       else {
/* 403 */         this.implicitCollectionMapper.add(field.getDeclaringClass(), fieldName, itemType);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void processOmitFieldAnnotation(Field field) {
/* 409 */     XStreamOmitField omitFieldAnnotation = (XStreamOmitField)field.getAnnotation(XStreamOmitField.class);
/*     */     
/* 411 */     if (omitFieldAnnotation != null) {
/* 412 */       if (this.fieldAliasingMapper == null) {
/* 413 */         throw new InitializationException("No " + FieldAliasingMapper.class.getName() + " available");
/*     */       }
/*     */       
/*     */ 
/* 417 */       this.fieldAliasingMapper.omitField(field.getDeclaringClass(), field.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   private void processLocalConverterAnnotation(Field field) {
/* 422 */     XStreamConverter annotation = (XStreamConverter)field.getAnnotation(XStreamConverter.class);
/* 423 */     if (annotation != null) {
/* 424 */       Class<? extends ConverterMatcher> converterType = annotation.value();
/* 425 */       Converter converter = cacheConverter(converterType, field.getType());
/* 426 */       if (converter != null) {
/* 427 */         if (this.localConversionMapper == null) {
/* 428 */           throw new InitializationException("No " + LocalConversionMapper.class.getName() + " available");
/*     */         }
/*     */         
/*     */ 
/* 432 */         this.localConversionMapper.registerLocalConverter(field.getDeclaringClass(), field.getName(), converter);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private Converter cacheConverter(Class<? extends ConverterMatcher> converterType, Class targetType)
/*     */   {
/* 440 */     Converter result = null;
/*     */     
/* 442 */     Map<Object, Converter> converterMapping = (Map)this.converterCache.get(converterType);
/* 443 */     if (converterMapping != null) {
/* 444 */       result = (Converter)converterMapping.get(targetType.getName());
/* 445 */       if (result == null) {
/* 446 */         result = (Converter)converterMapping.get(null);
/*     */       }
/*     */     }
/* 449 */     if (result == null) { Object[] args;
/* 450 */       if (targetType != null) {
/* 451 */         Object[] args = new Object[this.arguments.length + 1];
/* 452 */         System.arraycopy(this.arguments, 0, args, 1, this.arguments.length);
/* 453 */         args[0] = targetType;
/*     */       } else {
/* 455 */         args = this.arguments;
/*     */       }
/*     */       
/* 458 */       BitSet usedArgs = new BitSet();
/*     */       Converter converter;
/*     */       try { Converter converter;
/* 461 */         if ((SingleValueConverter.class.isAssignableFrom(converterType)) && (!Converter.class.isAssignableFrom(converterType)))
/*     */         {
/* 463 */           SingleValueConverter svc = (SingleValueConverter)DependencyInjectionFactory.newInstance(converterType, args, usedArgs);
/*     */           
/* 465 */           converter = new SingleValueConverterWrapper(svc);
/*     */         } else {
/* 467 */           converter = (Converter)DependencyInjectionFactory.newInstance(converterType, args, usedArgs);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 471 */         throw new InitializationException("Cannot instantiate converter " + converterType.getName() + (targetType != null ? " for type " + targetType.getName() : ""), e);
/*     */       }
/*     */       
/*     */ 
/* 475 */       if (converterMapping == null) {
/* 476 */         converterMapping = new HashMap();
/* 477 */         this.converterCache.put(converterType, converterMapping);
/*     */       }
/* 479 */       if ((targetType != null) && (usedArgs.get(0))) {
/* 480 */         converterMapping.put(targetType.getName(), converter);
/*     */       } else {
/* 482 */         converterMapping.put(null, converter);
/*     */       }
/* 484 */       result = converter;
/*     */     }
/* 486 */     return result;
/*     */   }
/*     */   
/*     */   private Class<?> getClass(Type typeArgument) {
/* 490 */     Class<?> type = null;
/* 491 */     if ((typeArgument instanceof ParameterizedType)) {
/* 492 */       type = (Class)((ParameterizedType)typeArgument).getRawType();
/* 493 */     } else if ((typeArgument instanceof Class)) {
/* 494 */       type = (Class)typeArgument;
/*     */     }
/* 496 */     return type;
/*     */   }
/*     */   
/*     */   private final class UnprocessedTypesSet extends LinkedHashSet<Class<?>> {
/*     */     private UnprocessedTypesSet() {}
/*     */     
/* 502 */     public boolean add(Class<?> type) { if (type == null) {
/* 503 */         return false;
/*     */       }
/* 505 */       while (type.isArray()) {
/* 506 */         type = type.getComponentType();
/*     */       }
/* 508 */       String name = type.getName();
/* 509 */       if ((name.startsWith("java.")) || (name.startsWith("javax."))) {
/* 510 */         return false;
/*     */       }
/* 512 */       boolean ret = AnnotationMapper.this.annotatedTypes.contains(type) ? false : super.add(type);
/* 513 */       if (ret) {
/* 514 */         XStreamInclude inc = (XStreamInclude)type.getAnnotation(XStreamInclude.class);
/* 515 */         if (inc != null) {
/* 516 */           Class<?>[] incTypes = inc.value();
/* 517 */           if (incTypes != null) {
/* 518 */             for (Class<?> incType : incTypes) {
/* 519 */               add(incType);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 524 */       return ret;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class WeakHashSet<K> implements Set<K>
/*     */   {
/* 530 */     private static Object NULL = new Object();
/* 531 */     private final WeakHashMap<K, Object> map = new WeakHashMap();
/*     */     
/*     */     public boolean add(K o) {
/* 534 */       return this.map.put(o, NULL) == null;
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends K> c) {
/* 538 */       boolean ret = false;
/* 539 */       for (K k : c) {
/* 540 */         ret = add(k) | false;
/*     */       }
/* 542 */       return ret;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 546 */       this.map.clear();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 550 */       return this.map.containsKey(o);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection<?> c) {
/* 554 */       return this.map.keySet().containsAll(c);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 558 */       return this.map.isEmpty();
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 562 */       return this.map.keySet().iterator();
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 566 */       return this.map.remove(o) != null;
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection<?> c) {
/* 570 */       boolean ret = false;
/* 571 */       for (Object object : c) {
/* 572 */         ret = remove(object) | false;
/*     */       }
/* 574 */       return ret;
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> c) {
/* 578 */       boolean ret = false;
/* 579 */       for (Iterator<K> iter = iterator(); iter.hasNext();) {
/* 580 */         K element = iter.next();
/* 581 */         if (!c.contains(element)) {
/* 582 */           iter.remove();
/* 583 */           ret = true;
/*     */         }
/*     */       }
/* 586 */       return ret;
/*     */     }
/*     */     
/*     */     public int size() {
/* 590 */       return this.map.size();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 594 */       return this.map.keySet().toArray();
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] a) {
/* 598 */       return this.map.keySet().toArray(a);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/AnnotationMapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */