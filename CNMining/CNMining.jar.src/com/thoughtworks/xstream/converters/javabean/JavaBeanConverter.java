/*     */ package com.thoughtworks.xstream.converters.javabean;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*     */ public class JavaBeanConverter
/*     */   implements Converter
/*     */ {
/*     */   private Mapper mapper;
/*     */   private BeanProvider beanProvider;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private String classAttributeIdentifier;
/*     */   
/*     */   public JavaBeanConverter(Mapper mapper)
/*     */   {
/*  43 */     this(mapper, new BeanProvider());
/*     */   }
/*     */   
/*     */   public JavaBeanConverter(Mapper mapper, BeanProvider beanProvider) {
/*  47 */     this.mapper = mapper;
/*  48 */     this.beanProvider = beanProvider;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JavaBeanConverter(Mapper mapper, String classAttributeIdentifier) {
/*  55 */     this(mapper, new BeanProvider());
/*  56 */     this.classAttributeIdentifier = classAttributeIdentifier;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConvert(Class type)
/*     */   {
/*  64 */     return this.beanProvider.canInstantiate(type);
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  68 */     String classAttributeName = this.classAttributeIdentifier != null ? this.classAttributeIdentifier : this.mapper.aliasForSystemAttribute("class");
/*  69 */     this.beanProvider.visitSerializableProperties(source, new BeanProvider.Visitor() { private final Object val$source;
/*     */       
/*  71 */       public boolean shouldVisit(String name, Class definedIn) { return JavaBeanConverter.this.mapper.shouldSerializeMember(definedIn, name); }
/*     */       
/*     */       private final HierarchicalStreamWriter val$writer;
/*     */       
/*  75 */       public void visit(String propertyName, Class fieldType, Class definedIn, Object newObj) { if (newObj != null)
/*  76 */           writeField(propertyName, fieldType, newObj, definedIn);
/*     */       }
/*     */       
/*     */       private final String val$classAttributeName;
/*     */       private final MarshallingContext val$context;
/*  81 */       private void writeField(String propertyName, Class fieldType, Object newObj, Class definedIn) { String serializedMember = JavaBeanConverter.this.mapper.serializedMember(this.val$source.getClass(), propertyName);
/*  82 */         ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, serializedMember, fieldType);
/*  83 */         Class actualType = newObj.getClass();
/*  84 */         Class defaultType = JavaBeanConverter.this.mapper.defaultImplementationOf(fieldType);
/*  85 */         if ((!actualType.equals(defaultType)) && (this.val$classAttributeName != null)) {
/*  86 */           this.val$writer.addAttribute(this.val$classAttributeName, JavaBeanConverter.this.mapper.serializedClass(actualType));
/*     */         }
/*  88 */         this.val$context.convertAnother(newObj);
/*     */         
/*  90 */         this.val$writer.endNode();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  96 */     Object result = instantiateNewInstance(context);
/*     */     
/*  98 */     while (reader.hasMoreChildren()) {
/*  99 */       reader.moveDown();
/*     */       
/* 101 */       String propertyName = this.mapper.realMember(result.getClass(), reader.getNodeName());
/*     */       
/* 103 */       if (this.mapper.shouldSerializeMember(result.getClass(), propertyName)) {
/* 104 */         boolean propertyExistsInClass = this.beanProvider.propertyDefinedInClass(propertyName, result.getClass());
/*     */         
/* 106 */         if (propertyExistsInClass) {
/* 107 */           Class type = determineType(reader, result, propertyName);
/* 108 */           Object value = context.convertAnother(result, type);
/* 109 */           this.beanProvider.writeProperty(result, propertyName, value);
/*     */         } else {
/* 111 */           throw new ConversionException("Property '" + propertyName + "' not defined in class " + result.getClass().getName());
/*     */         }
/*     */       }
/* 114 */       reader.moveUp();
/*     */     }
/*     */     
/* 117 */     return result;
/*     */   }
/*     */   
/*     */   private Object instantiateNewInstance(UnmarshallingContext context) {
/* 121 */     Object result = context.currentObject();
/* 122 */     if (result == null) {
/* 123 */       result = this.beanProvider.newInstance(context.getRequiredType());
/*     */     }
/* 125 */     return result;
/*     */   }
/*     */   
/*     */   private Class determineType(HierarchicalStreamReader reader, Object result, String fieldName) {
/* 129 */     String classAttributeName = this.classAttributeIdentifier != null ? this.classAttributeIdentifier : this.mapper.aliasForSystemAttribute("class");
/* 130 */     String classAttribute = classAttributeName == null ? null : reader.getAttribute(classAttributeName);
/* 131 */     if (classAttribute != null) {
/* 132 */       return this.mapper.realClass(classAttribute);
/*     */     }
/* 134 */     return this.mapper.defaultImplementationOf(this.beanProvider.getPropertyType(result, fieldName));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static class DuplicateFieldException extends ConversionException
/*     */   {
/*     */     public DuplicateFieldException(String msg) {
/* 143 */       super();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/javabean/JavaBeanConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */