/*     */ package com.thoughtworks.xstream.converters.extended;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.Fields;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.DynamicProxyMapper.DynamicProxy;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class DynamicProxyConverter
/*     */   implements Converter
/*     */ {
/*     */   private ClassLoader classLoader;
/*     */   private Mapper mapper;
/*     */   private static final Field HANDLER;
/*  42 */   private static final InvocationHandler DUMMY = new InvocationHandler() {
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/*  44 */       return null;
/*     */     }
/*     */   };
/*     */   
/*     */   static {
/*  49 */     Field field = null;
/*     */     try {
/*  51 */       field = Proxy.class.getDeclaredField("h");
/*  52 */       field.setAccessible(true);
/*     */     } catch (NoSuchFieldException e) {
/*  54 */       throw new ExceptionInInitializerError(e);
/*     */     }
/*  56 */     HANDLER = field;
/*     */   }
/*     */   
/*     */   public DynamicProxyConverter(Mapper mapper) {
/*  60 */     this(mapper, DynamicProxyConverter.class.getClassLoader());
/*     */   }
/*     */   
/*     */   public DynamicProxyConverter(Mapper mapper, ClassLoader classLoader) {
/*  64 */     this.classLoader = classLoader;
/*  65 */     this.mapper = mapper;
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  69 */     return (type.equals(DynamicProxyMapper.DynamicProxy.class)) || (Proxy.isProxyClass(type));
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  73 */     InvocationHandler invocationHandler = Proxy.getInvocationHandler(source);
/*  74 */     addInterfacesToXml(source, writer);
/*  75 */     writer.startNode("handler");
/*  76 */     String attributeName = this.mapper.aliasForSystemAttribute("class");
/*  77 */     if (attributeName != null) {
/*  78 */       writer.addAttribute(attributeName, this.mapper.serializedClass(invocationHandler.getClass()));
/*     */     }
/*  80 */     context.convertAnother(invocationHandler);
/*  81 */     writer.endNode();
/*     */   }
/*     */   
/*     */   private void addInterfacesToXml(Object source, HierarchicalStreamWriter writer) {
/*  85 */     Class[] interfaces = source.getClass().getInterfaces();
/*  86 */     for (int i = 0; i < interfaces.length; i++) {
/*  87 */       Class currentInterface = interfaces[i];
/*  88 */       writer.startNode("interface");
/*  89 */       writer.setValue(this.mapper.serializedClass(currentInterface));
/*  90 */       writer.endNode();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  95 */     List interfaces = new ArrayList();
/*  96 */     InvocationHandler handler = null;
/*  97 */     Class handlerType = null;
/*  98 */     while (reader.hasMoreChildren()) {
/*  99 */       reader.moveDown();
/* 100 */       String elementName = reader.getNodeName();
/* 101 */       if (elementName.equals("interface")) {
/* 102 */         interfaces.add(this.mapper.realClass(reader.getValue()));
/* 103 */       } else if (elementName.equals("handler")) {
/* 104 */         String attributeName = this.mapper.aliasForSystemAttribute("class");
/* 105 */         if (attributeName != null) {
/* 106 */           handlerType = this.mapper.realClass(reader.getAttribute(attributeName));
/* 107 */           break;
/*     */         }
/*     */       }
/* 110 */       reader.moveUp();
/*     */     }
/* 112 */     if (handlerType == null) {
/* 113 */       throw new ConversionException("No InvocationHandler specified for dynamic proxy");
/*     */     }
/* 115 */     Class[] interfacesAsArray = new Class[interfaces.size()];
/* 116 */     interfaces.toArray(interfacesAsArray);
/* 117 */     Object proxy = Proxy.newProxyInstance(this.classLoader, interfacesAsArray, DUMMY);
/* 118 */     handler = (InvocationHandler)context.convertAnother(proxy, handlerType);
/* 119 */     reader.moveUp();
/* 120 */     Fields.write(HANDLER, proxy, handler);
/* 121 */     return proxy;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/DynamicProxyConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */