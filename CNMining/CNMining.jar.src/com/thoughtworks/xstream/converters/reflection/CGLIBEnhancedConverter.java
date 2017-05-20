/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.CGLIBMapper.Marker;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.sf.cglib.proxy.Callback;
/*     */ import net.sf.cglib.proxy.CallbackFilter;
/*     */ import net.sf.cglib.proxy.Enhancer;
/*     */ import net.sf.cglib.proxy.Factory;
/*     */ import net.sf.cglib.proxy.MethodInterceptor;
/*     */ import net.sf.cglib.proxy.NoOp;
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
/*     */ public class CGLIBEnhancedConverter
/*     */   extends SerializableConverter
/*     */ {
/*  59 */   private static String DEFAULT_NAMING_MARKER = "$$EnhancerByCGLIB$$";
/*  60 */   private static String CALLBACK_MARKER = "CGLIB$CALLBACK_";
/*     */   private transient Map fieldCache;
/*     */   
/*     */   public CGLIBEnhancedConverter(Mapper mapper, ReflectionProvider reflectionProvider, ClassLoader classLoader) {
/*  64 */     super(mapper, new CGLIBFilteringReflectionProvider(reflectionProvider), classLoader);
/*  65 */     this.fieldCache = new HashMap();
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public CGLIBEnhancedConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
/*  72 */     this(mapper, new CGLIBFilteringReflectionProvider(reflectionProvider), null);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  76 */     return ((Enhancer.isEnhanced(type)) && (type.getName().indexOf(DEFAULT_NAMING_MARKER) > 0)) || (type == CGLIBMapper.Marker.class);
/*     */   }
/*     */   
/*     */ 
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  82 */     Class type = source.getClass();
/*  83 */     boolean hasFactory = Factory.class.isAssignableFrom(type);
/*  84 */     ExtendedHierarchicalStreamWriterHelper.startNode(writer, "type", type);
/*  85 */     context.convertAnother(type.getSuperclass());
/*  86 */     writer.endNode();
/*  87 */     writer.startNode("interfaces");
/*  88 */     Class[] interfaces = type.getInterfaces();
/*  89 */     for (int i = 0; i < interfaces.length; i++)
/*  90 */       if (interfaces[i] != Factory.class)
/*     */       {
/*     */ 
/*  93 */         ExtendedHierarchicalStreamWriterHelper.startNode(writer, this.mapper.serializedClass(interfaces[i].getClass()), interfaces[i].getClass());
/*     */         
/*  95 */         context.convertAnother(interfaces[i]);
/*  96 */         writer.endNode();
/*     */       }
/*  98 */     writer.endNode();
/*  99 */     writer.startNode("hasFactory");
/* 100 */     writer.setValue(String.valueOf(hasFactory));
/* 101 */     writer.endNode();
/* 102 */     Map callbackIndexMap = null;
/* 103 */     Callback[] callbacks = hasFactory ? ((Factory)source).getCallbacks() : getCallbacks(source);
/*     */     
/*     */ 
/* 106 */     if (callbacks.length > 1) {
/* 107 */       if (hasFactory) {
/* 108 */         callbackIndexMap = createCallbackIndexMap((Factory)source);
/*     */       } else {
/* 110 */         ConversionException exception = new ConversionException("Cannot handle CGLIB enhanced proxies without factory that have multiple callbacks");
/*     */         
/* 112 */         exception.add("proxy superclass", type.getSuperclass().getName());
/* 113 */         exception.add("number of callbacks", String.valueOf(callbacks.length));
/* 114 */         throw exception;
/*     */       }
/* 116 */       writer.startNode("callbacks");
/* 117 */       writer.startNode("mapping");
/* 118 */       context.convertAnother(callbackIndexMap);
/* 119 */       writer.endNode();
/*     */     }
/* 121 */     boolean hasInterceptor = false;
/* 122 */     for (int i = 0; i < callbacks.length; i++) {
/* 123 */       Callback callback = callbacks[i];
/* 124 */       if (callback == null) {
/* 125 */         String name = this.mapper.serializedClass(null);
/* 126 */         writer.startNode(name);
/* 127 */         writer.endNode();
/*     */       } else {
/* 129 */         hasInterceptor = (hasInterceptor) || (MethodInterceptor.class.isAssignableFrom(callback.getClass()));
/*     */         
/* 131 */         ExtendedHierarchicalStreamWriterHelper.startNode(writer, this.mapper.serializedClass(callback.getClass()), callback.getClass());
/*     */         
/* 133 */         context.convertAnother(callback);
/* 134 */         writer.endNode();
/*     */       }
/*     */     }
/* 137 */     if (callbacks.length > 1) {
/* 138 */       writer.endNode();
/*     */     }
/*     */     try {
/* 141 */       Field field = type.getDeclaredField("serialVersionUID");
/* 142 */       field.setAccessible(true);
/* 143 */       long serialVersionUID = field.getLong(null);
/* 144 */       ExtendedHierarchicalStreamWriterHelper.startNode(writer, "serialVersionUID", String.class);
/*     */       
/* 146 */       writer.setValue(String.valueOf(serialVersionUID));
/* 147 */       writer.endNode();
/*     */     }
/*     */     catch (NoSuchFieldException e) {}catch (IllegalAccessException e)
/*     */     {
/* 151 */       throw new ObjectAccessException("Access to serialVersionUID of " + type.getName() + " not allowed");
/*     */     }
/*     */     
/*     */ 
/* 155 */     if (hasInterceptor) {
/* 156 */       writer.startNode("instance");
/* 157 */       super.doMarshalConditionally(source, writer, context);
/* 158 */       writer.endNode();
/*     */     }
/*     */   }
/*     */   
/*     */   private Callback[] getCallbacks(Object source) {
/* 163 */     Class type = source.getClass();
/* 164 */     List fields = (List)this.fieldCache.get(type.getName());
/* 165 */     if (fields == null) {
/* 166 */       fields = new ArrayList();
/* 167 */       this.fieldCache.put(type.getName(), fields);
/* 168 */       for (int i = 0;; i++) {
/*     */         try {
/* 170 */           Field field = type.getDeclaredField(CALLBACK_MARKER + i);
/* 171 */           field.setAccessible(true);
/* 172 */           fields.add(field);
/*     */         } catch (NoSuchFieldException e) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 178 */     List list = new ArrayList();
/* 179 */     for (int i = 0; i < fields.size(); i++) {
/*     */       try {
/* 181 */         Field field = (Field)fields.get(i);
/* 182 */         Object callback = field.get(source);
/* 183 */         list.add(callback);
/*     */       } catch (IllegalAccessException e) {
/* 185 */         throw new ObjectAccessException("Access to " + type.getName() + "." + CALLBACK_MARKER + i + " not allowed");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 193 */     return (Callback[])list.toArray(new Callback[list.size()]);
/*     */   }
/*     */   
/*     */   private Map createCallbackIndexMap(Factory source) {
/* 197 */     Callback[] originalCallbacks = source.getCallbacks();
/* 198 */     Callback[] reverseEngineeringCallbacks = new Callback[originalCallbacks.length];
/* 199 */     Map callbackIndexMap = new HashMap();
/* 200 */     int idxNoOp = -1;
/* 201 */     for (int i = 0; i < originalCallbacks.length; i++) {
/* 202 */       Callback callback = originalCallbacks[i];
/* 203 */       if (callback == null) {
/* 204 */         reverseEngineeringCallbacks[i] = null;
/* 205 */       } else if (NoOp.class.isAssignableFrom(callback.getClass())) {
/* 206 */         reverseEngineeringCallbacks[i] = NoOp.INSTANCE;
/* 207 */         idxNoOp = i;
/*     */       } else {
/* 209 */         reverseEngineeringCallbacks[i] = createReverseEngineeredCallbackOfProperType(callback, i, callbackIndexMap);
/*     */       }
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 215 */       source.setCallbacks(reverseEngineeringCallbacks);
/* 216 */       Set interfaces = new HashSet();
/* 217 */       Set methods = new HashSet();
/* 218 */       Class type = source.getClass();
/*     */       do {
/* 220 */         methods.addAll(Arrays.asList(type.getDeclaredMethods()));
/* 221 */         methods.addAll(Arrays.asList(type.getMethods()));
/* 222 */         Class[] implementedInterfaces = type.getInterfaces();
/* 223 */         interfaces.addAll(Arrays.asList(implementedInterfaces));
/* 224 */         type = type.getSuperclass();
/* 225 */       } while (type != null);
/* 226 */       for (Iterator iterator = interfaces.iterator(); iterator.hasNext();) {
/* 227 */         type = (Class)iterator.next();
/* 228 */         methods.addAll(Arrays.asList(type.getDeclaredMethods()));
/*     */       }
/* 230 */       for (Iterator iter = methods.iterator(); iter.hasNext();) {
/* 231 */         Method method = (Method)iter.next();
/* 232 */         method.setAccessible(true);
/* 233 */         if ((Factory.class.isAssignableFrom(method.getDeclaringClass())) || ((method.getModifiers() & 0x18) > 0))
/*     */         {
/* 235 */           iter.remove();
/*     */         }
/*     */         else {
/* 238 */           Class[] parameterTypes = method.getParameterTypes();
/* 239 */           Method calledMethod = method;
/*     */           try {
/* 241 */             if ((method.getModifiers() & 0x400) > 0) {
/* 242 */               calledMethod = source.getClass().getMethod(method.getName(), method.getParameterTypes());
/*     */             }
/*     */             
/* 245 */             callbackIndexMap.put(null, method);
/* 246 */             calledMethod.invoke(source, parameterTypes == null ? (Object[])null : createNullArguments(parameterTypes));
/*     */           }
/*     */           catch (IllegalAccessException e)
/*     */           {
/* 250 */             throw new ObjectAccessException("Access to " + calledMethod + " not allowed");
/*     */ 
/*     */           }
/*     */           catch (InvocationTargetException e) {}catch (NoSuchMethodException e)
/*     */           {
/*     */ 
/* 256 */             ConversionException exception = new ConversionException("CGLIB enhanced proxies wit abstract nethod that has not been implemented");
/*     */             
/* 258 */             exception.add("proxy superclass", type.getSuperclass().getName());
/* 259 */             exception.add("method", method.toString());
/* 260 */             throw exception;
/*     */           }
/* 262 */           if (callbackIndexMap.containsKey(method))
/* 263 */             iter.remove();
/*     */         }
/*     */       }
/* 266 */       if (idxNoOp >= 0) {
/* 267 */         idx = new Integer(idxNoOp);
/* 268 */         for (iter = methods.iterator(); iter.hasNext();)
/* 269 */           callbackIndexMap.put(iter.next(), idx);
/*     */       }
/*     */     } finally { Integer idx;
/*     */       Iterator iter;
/* 273 */       source.setCallbacks(originalCallbacks);
/*     */     }
/*     */     
/* 276 */     callbackIndexMap.remove(null);
/* 277 */     return callbackIndexMap;
/*     */   }
/*     */   
/*     */   private Object[] createNullArguments(Class[] parameterTypes) {
/* 281 */     Object[] arguments = new Object[parameterTypes.length];
/* 282 */     for (int i = 0; i < arguments.length; i++) {
/* 283 */       Class type = parameterTypes[i];
/* 284 */       if (type.isPrimitive()) {
/* 285 */         if (type == Byte.TYPE) {
/* 286 */           arguments[i] = new Byte(0);
/* 287 */         } else if (type == Short.TYPE) {
/* 288 */           arguments[i] = new Short(0);
/* 289 */         } else if (type == Integer.TYPE) {
/* 290 */           arguments[i] = new Integer(0);
/* 291 */         } else if (type == Long.TYPE) {
/* 292 */           arguments[i] = new Long(0L);
/* 293 */         } else if (type == Float.TYPE) {
/* 294 */           arguments[i] = new Float(0.0F);
/* 295 */         } else if (type == Double.TYPE) {
/* 296 */           arguments[i] = new Double(0.0D);
/* 297 */         } else if (type == Character.TYPE) {
/* 298 */           arguments[i] = new Character('\000');
/*     */         } else {
/* 300 */           arguments[i] = Boolean.FALSE;
/*     */         }
/*     */       }
/*     */     }
/* 304 */     return arguments;
/*     */   }
/*     */   
/*     */   private Callback createReverseEngineeredCallbackOfProperType(Callback callback, int index, Map callbackIndexMap)
/*     */   {
/* 309 */     Class iface = null;
/* 310 */     Class[] interfaces = callback.getClass().getInterfaces();
/* 311 */     for (int i = 0; i < interfaces.length; i++) {
/* 312 */       if (Callback.class.isAssignableFrom(interfaces[i])) {
/* 313 */         iface = interfaces[i];
/* 314 */         if (iface == Callback.class) {
/* 315 */           ConversionException exception = new ConversionException("Cannot handle CGLIB callback");
/*     */           
/* 317 */           exception.add("CGLIB callback type", callback.getClass().getName());
/* 318 */           throw exception;
/*     */         }
/* 320 */         interfaces = iface.getInterfaces();
/* 321 */         if (Arrays.asList(interfaces).contains(Callback.class)) {
/*     */           break;
/*     */         }
/* 324 */         i = -1;
/*     */       }
/*     */     }
/* 327 */     return (Callback)Proxy.newProxyInstance(iface.getClassLoader(), new Class[] { iface }, new ReverseEngineeringInvocationHandler(index, callbackIndexMap));
/*     */   }
/*     */   
/*     */ 
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*     */   {
/* 333 */     Enhancer enhancer = new Enhancer();
/* 334 */     reader.moveDown();
/* 335 */     enhancer.setSuperclass((Class)context.convertAnother(null, Class.class));
/* 336 */     reader.moveUp();
/* 337 */     reader.moveDown();
/* 338 */     List interfaces = new ArrayList();
/* 339 */     while (reader.hasMoreChildren()) {
/* 340 */       reader.moveDown();
/* 341 */       interfaces.add(context.convertAnother(null, this.mapper.realClass(reader.getNodeName())));
/*     */       
/* 343 */       reader.moveUp();
/*     */     }
/* 345 */     enhancer.setInterfaces((Class[])interfaces.toArray(new Class[interfaces.size()]));
/* 346 */     reader.moveUp();
/* 347 */     reader.moveDown();
/* 348 */     boolean useFactory = Boolean.valueOf(reader.getValue()).booleanValue();
/* 349 */     enhancer.setUseFactory(useFactory);
/* 350 */     reader.moveUp();
/*     */     
/* 352 */     List callbacksToEnhance = new ArrayList();
/* 353 */     List callbacks = new ArrayList();
/* 354 */     Map callbackIndexMap = null;
/* 355 */     reader.moveDown();
/* 356 */     if ("callbacks".equals(reader.getNodeName())) {
/* 357 */       reader.moveDown();
/* 358 */       callbackIndexMap = (Map)context.convertAnother(null, HashMap.class);
/* 359 */       reader.moveUp();
/* 360 */       while (reader.hasMoreChildren()) {
/* 361 */         reader.moveDown();
/* 362 */         readCallback(reader, context, callbacksToEnhance, callbacks);
/* 363 */         reader.moveUp();
/*     */       }
/*     */     }
/* 366 */     readCallback(reader, context, callbacksToEnhance, callbacks);
/*     */     
/* 368 */     enhancer.setCallbacks((Callback[])callbacksToEnhance.toArray(new Callback[callbacksToEnhance.size()]));
/*     */     
/* 370 */     if (callbackIndexMap != null) {
/* 371 */       enhancer.setCallbackFilter(new ReverseEngineeredCallbackFilter(callbackIndexMap));
/*     */     }
/* 373 */     reader.moveUp();
/* 374 */     Object result = null;
/* 375 */     while (reader.hasMoreChildren()) {
/* 376 */       reader.moveDown();
/* 377 */       if (reader.getNodeName().equals("serialVersionUID")) {
/* 378 */         enhancer.setSerialVersionUID(Long.valueOf(reader.getValue()));
/* 379 */       } else if (reader.getNodeName().equals("instance")) {
/* 380 */         result = create(enhancer, callbacks, useFactory);
/* 381 */         super.doUnmarshalConditionally(result, reader, context);
/*     */       }
/* 383 */       reader.moveUp();
/*     */     }
/* 385 */     if (result == null) {
/* 386 */       result = create(enhancer, callbacks, useFactory);
/*     */     }
/* 388 */     return this.serializationMethodInvoker.callReadResolve(result);
/*     */   }
/*     */   
/*     */   private void readCallback(HierarchicalStreamReader reader, UnmarshallingContext context, List callbacksToEnhance, List callbacks)
/*     */   {
/* 393 */     Callback callback = (Callback)context.convertAnother(null, this.mapper.realClass(reader.getNodeName()));
/*     */     
/* 395 */     callbacks.add(callback);
/* 396 */     if (callback == null) {
/* 397 */       callbacksToEnhance.add(NoOp.INSTANCE);
/*     */     } else {
/* 399 */       callbacksToEnhance.add(callback);
/*     */     }
/*     */   }
/*     */   
/*     */   private Object create(Enhancer enhancer, List callbacks, boolean useFactory) {
/* 404 */     Object result = enhancer.create();
/* 405 */     if (useFactory) {
/* 406 */       ((Factory)result).setCallbacks((Callback[])callbacks.toArray(new Callback[callbacks.size()]));
/*     */     }
/*     */     
/* 409 */     return result;
/*     */   }
/*     */   
/*     */   protected List hierarchyFor(Class type) {
/* 413 */     List typeHierarchy = super.hierarchyFor(type);
/*     */     
/* 415 */     typeHierarchy.remove(typeHierarchy.size() - 1);
/* 416 */     return typeHierarchy;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 420 */     this.fieldCache = new HashMap();
/* 421 */     return this;
/*     */   }
/*     */   
/*     */   private static class CGLIBFilteringReflectionProvider extends ReflectionProviderWrapper
/*     */   {
/*     */     public CGLIBFilteringReflectionProvider(ReflectionProvider reflectionProvider) {
/* 427 */       super();
/*     */     }
/*     */     
/*     */     public void visitSerializableFields(Object object, ReflectionProvider.Visitor visitor) {
/* 431 */       this.wrapped.visitSerializableFields(object, new ReflectionProvider.Visitor() { private final ReflectionProvider.Visitor val$visitor;
/*     */         
/* 433 */         public void visit(String name, Class type, Class definedIn, Object value) { if (!name.startsWith("CGLIB$")) {
/* 434 */             this.val$visitor.visit(name, type, definedIn, value);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ReverseEngineeringInvocationHandler implements InvocationHandler {
/*     */     private final Integer index;
/*     */     private final Map indexMap;
/*     */     
/*     */     public ReverseEngineeringInvocationHandler(int index, Map indexMap) {
/* 446 */       this.indexMap = indexMap;
/* 447 */       this.index = new Integer(index);
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 451 */       this.indexMap.put(this.indexMap.get(null), this.index);
/* 452 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ReverseEngineeredCallbackFilter implements CallbackFilter
/*     */   {
/*     */     private final Map callbackIndexMap;
/*     */     
/*     */     public ReverseEngineeredCallbackFilter(Map callbackIndexMap) {
/* 461 */       this.callbackIndexMap = callbackIndexMap;
/*     */     }
/*     */     
/*     */     public int accept(Method method) {
/* 465 */       if (!this.callbackIndexMap.containsKey(method)) {
/* 466 */         ConversionException exception = new ConversionException("CGLIB callback not detected in reverse engineering");
/*     */         
/* 468 */         exception.add("CGLIB callback", method.toString());
/* 469 */         throw exception;
/*     */       }
/* 471 */       return ((Integer)this.callbackIndexMap.get(method)).intValue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/CGLIBEnhancedConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */