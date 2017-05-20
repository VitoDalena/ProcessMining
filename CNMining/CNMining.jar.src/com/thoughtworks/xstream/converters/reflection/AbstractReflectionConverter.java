/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.ReferencingMarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.FastField;
/*     */ import com.thoughtworks.xstream.core.util.HierarchicalStreams;
/*     */ import com.thoughtworks.xstream.core.util.Primitives;
/*     */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.CannotResolveClassException;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import com.thoughtworks.xstream.mapper.Mapper.ImplicitCollectionMapping;
/*     */ import com.thoughtworks.xstream.mapper.Mapper.Null;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractReflectionConverter
/*     */   implements Converter
/*     */ {
/*     */   protected final ReflectionProvider reflectionProvider;
/*     */   protected final Mapper mapper;
/*     */   protected transient SerializationMethodInvoker serializationMethodInvoker;
/*     */   private transient ReflectionProvider pureJavaReflectionProvider;
/*     */   
/*     */   public AbstractReflectionConverter(Mapper mapper, ReflectionProvider reflectionProvider)
/*     */   {
/*  48 */     this.mapper = mapper;
/*  49 */     this.reflectionProvider = reflectionProvider;
/*  50 */     this.serializationMethodInvoker = new SerializationMethodInvoker();
/*     */   }
/*     */   
/*     */   public void marshal(Object original, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  55 */     Object source = this.serializationMethodInvoker.callWriteReplace(original);
/*     */     
/*  57 */     if ((source != original) && ((context instanceof ReferencingMarshallingContext))) {
/*  58 */       ((ReferencingMarshallingContext)context).replace(original, source);
/*     */     }
/*  60 */     if (source.getClass() != original.getClass()) {
/*  61 */       String attributeName = this.mapper.aliasForSystemAttribute("resolves-to");
/*  62 */       if (attributeName != null) {
/*  63 */         writer.addAttribute(attributeName, this.mapper.serializedClass(source.getClass()));
/*     */       }
/*  65 */       context.convertAnother(source);
/*     */     } else {
/*  67 */       doMarshal(source, writer, context);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doMarshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  72 */     List fields = new ArrayList();
/*  73 */     Map defaultFieldDefinition = new HashMap();
/*     */     
/*     */ 
/*  76 */     this.reflectionProvider.visitSerializableFields(source, new ReflectionProvider.Visitor() { final Set writtenAttributes;
/*     */       private final Map val$defaultFieldDefinition;
/*     */       
/*  79 */       public void visit(String fieldName, Class type, Class definedIn, Object value) { if (!AbstractReflectionConverter.this.mapper.shouldSerializeMember(definedIn, fieldName)) {
/*  80 */           return;
/*     */         }
/*  82 */         if (!this.val$defaultFieldDefinition.containsKey(fieldName)) {
/*  83 */           Class lookupType = this.val$source.getClass();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*  88 */           this.val$defaultFieldDefinition.put(fieldName, AbstractReflectionConverter.this.reflectionProvider.getField(lookupType, fieldName));
/*     */         }
/*     */         
/*  91 */         SingleValueConverter converter = AbstractReflectionConverter.this.mapper.getConverterFromItemType(fieldName, type, definedIn);
/*  92 */         if (converter != null) {
/*  93 */           String attribute = AbstractReflectionConverter.this.mapper.aliasForAttribute(AbstractReflectionConverter.this.mapper.serializedMember(definedIn, fieldName));
/*  94 */           if (value != null) {
/*  95 */             if (this.writtenAttributes.contains(fieldName)) {
/*  96 */               throw new ConversionException("Cannot write field with name '" + fieldName + "' twice as attribute for object of type " + this.val$source.getClass().getName());
/*     */             }
/*     */             
/*  99 */             String str = converter.toString(value);
/* 100 */             if (str != null) {
/* 101 */               this.val$writer.addAttribute(attribute, str);
/*     */             }
/*     */           }
/* 104 */           this.writtenAttributes.add(fieldName);
/*     */         } else {
/* 106 */           this.val$fields.add(new AbstractReflectionConverter.FieldInfo(fieldName, type, definedIn, value));
/*     */         }
/*     */         
/*     */       }
/* 110 */     });
/* 111 */     new Object()
/*     */     {
/*     */       private final Object val$source;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       private final HierarchicalStreamWriter val$writer;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       private final List val$fields;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       private final List val$fields;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       private final Object val$source;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       private final MarshallingContext val$context;
/*     */       
/*     */ 
/*     */ 
/*     */       private final HierarchicalStreamWriter val$writer;
/*     */       
/*     */ 
/*     */ 
/*     */       private final Map val$defaultFieldDefinition;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       void writeField(String fieldName, String aliasName, Class fieldType, Class definedIn, Object newObj)
/*     */       {
/* 153 */         ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, aliasName != null ? aliasName : AbstractReflectionConverter.this.mapper.serializedMember(this.val$source.getClass(), fieldName), fieldType);
/*     */         
/*     */ 
/*     */ 
/* 157 */         if (newObj != null) {
/* 158 */           Class actualType = newObj.getClass();
/* 159 */           Class defaultType = AbstractReflectionConverter.this.mapper.defaultImplementationOf(fieldType);
/* 160 */           if (!actualType.equals(defaultType)) {
/* 161 */             String serializedClassName = AbstractReflectionConverter.this.mapper.serializedClass(actualType);
/* 162 */             if (!serializedClassName.equals(AbstractReflectionConverter.this.mapper.serializedClass(defaultType))) {
/* 163 */               String attributeName = AbstractReflectionConverter.this.mapper.aliasForSystemAttribute("class");
/* 164 */               if (attributeName != null) {
/* 165 */                 this.val$writer.addAttribute(attributeName, serializedClassName);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 170 */           Field defaultField = (Field)this.val$defaultFieldDefinition.get(fieldName);
/* 171 */           if (defaultField.getDeclaringClass() != definedIn) {
/* 172 */             String attributeName = AbstractReflectionConverter.this.mapper.aliasForSystemAttribute("defined-in");
/* 173 */             if (attributeName != null) {
/* 174 */               this.val$writer.addAttribute(attributeName, AbstractReflectionConverter.this.mapper.serializedClass(definedIn));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 179 */           Field field = AbstractReflectionConverter.this.reflectionProvider.getField(definedIn, fieldName);
/* 180 */           AbstractReflectionConverter.this.marshallField(this.val$context, newObj, field);
/*     */         }
/* 182 */         this.val$writer.endNode();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   protected void marshallField(MarshallingContext context, Object newObj, Field field)
/*     */   {
/* 189 */     context.convertAnother(newObj, this.mapper.getLocalConverter(field.getDeclaringClass(), field.getName()));
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 193 */     Object result = instantiateNewInstance(reader, context);
/* 194 */     result = doUnmarshal(result, reader, context);
/* 195 */     return this.serializationMethodInvoker.callReadResolve(result);
/*     */   }
/*     */   
/*     */   public Object doUnmarshal(Object result, HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 199 */     Set seenFields = new HashSet() {
/*     */       public boolean add(Object e) {
/* 201 */         if (!super.add(e)) {
/* 202 */           throw new AbstractReflectionConverter.DuplicateFieldException(e.toString());
/*     */         }
/* 204 */         return true;
/*     */       }
/* 206 */     };
/* 207 */     Iterator it = reader.getAttributeNames();
/*     */     
/*     */ 
/* 210 */     while (it.hasNext()) {
/* 211 */       String attrAlias = (String)it.next();
/*     */       
/* 213 */       String attrName = this.mapper.realMember(result.getClass(), this.mapper.attributeForAlias(attrAlias));
/* 214 */       boolean fieldExistsInClass = this.reflectionProvider.fieldDefinedInClass(attrName, result.getClass());
/* 215 */       if (fieldExistsInClass) {
/* 216 */         Field field = this.reflectionProvider.getField(result.getClass(), attrName);
/* 217 */         if ((!Modifier.isTransient(field.getModifiers())) || (shouldUnmarshalTransientFields()))
/*     */         {
/*     */ 
/* 220 */           Class classDefiningField = field.getDeclaringClass();
/* 221 */           if (this.mapper.shouldSerializeMember(classDefiningField, attrName))
/*     */           {
/*     */ 
/* 224 */             SingleValueConverter converter = this.mapper.getConverterFromAttribute(classDefiningField, attrName, field.getType());
/* 225 */             Class type = field.getType();
/* 226 */             if (converter != null) {
/* 227 */               Object value = converter.fromString(reader.getAttribute(attrAlias));
/* 228 */               if (type.isPrimitive()) {
/* 229 */                 type = Primitives.box(type);
/*     */               }
/* 231 */               if ((value != null) && (!type.isAssignableFrom(value.getClass()))) {
/* 232 */                 throw new ConversionException("Cannot convert type " + value.getClass().getName() + " to type " + type.getName());
/*     */               }
/* 234 */               seenFields.add(new FastField(classDefiningField, attrName));
/* 235 */               this.reflectionProvider.writeField(result, attrName, value, classDefiningField);
/*     */             }
/*     */           }
/*     */         }
/*     */       } }
/* 240 */     Map implicitCollectionsForCurrentObject = null;
/* 241 */     while (reader.hasMoreChildren()) {
/* 242 */       reader.moveDown();
/*     */       
/* 244 */       String originalNodeName = reader.getNodeName();
/* 245 */       Class classDefiningField = determineWhichClassDefinesField(reader);
/* 246 */       Class fieldDeclaringClass = classDefiningField == null ? result.getClass() : classDefiningField;
/*     */       
/*     */ 
/* 249 */       String fieldName = this.mapper.realMember(fieldDeclaringClass, originalNodeName);
/* 250 */       Mapper.ImplicitCollectionMapping implicitCollectionMapping = this.mapper.getImplicitCollectionDefForFieldName(fieldDeclaringClass, fieldName);
/*     */       
/* 252 */       boolean fieldExistsInClass = (implicitCollectionMapping == null) && (this.reflectionProvider.fieldDefinedInClass(fieldName, fieldDeclaringClass));
/*     */       
/* 254 */       Class type = implicitCollectionMapping == null ? determineType(reader, fieldExistsInClass, result, fieldName, classDefiningField) : implicitCollectionMapping.getItemType();
/*     */       
/*     */ 
/*     */       Object value;
/*     */       
/* 259 */       if (fieldExistsInClass) {
/* 260 */         Field field = this.reflectionProvider.getField(classDefiningField != null ? classDefiningField : result.getClass(), fieldName);
/* 261 */         if ((!Modifier.isTransient(field.getModifiers())) || (shouldUnmarshalTransientFields())) { if (this.mapper.shouldSerializeMember(classDefiningField != null ? classDefiningField : result.getClass(), fieldName)) {}
/*     */         } else {
/* 263 */           reader.moveUp();
/* 264 */           continue;
/*     */         }
/* 266 */         Object value = unmarshallField(context, result, type, field);
/*     */         
/* 268 */         Class definedType = this.reflectionProvider.getFieldType(result, fieldName, classDefiningField);
/* 269 */         if (!definedType.isPrimitive()) {
/* 270 */           type = definedType;
/*     */         }
/*     */       } else {
/* 273 */         value = type != null ? context.convertAnother(result, type) : null;
/*     */       }
/*     */       
/* 276 */       if ((value != null) && (!type.isAssignableFrom(value.getClass()))) {
/* 277 */         throw new ConversionException("Cannot convert type " + value.getClass().getName() + " to type " + type.getName());
/*     */       }
/*     */       
/* 280 */       if (fieldExistsInClass) {
/* 281 */         this.reflectionProvider.writeField(result, fieldName, value, classDefiningField);
/* 282 */         seenFields.add(new FastField(classDefiningField, fieldName));
/* 283 */       } else if (type != null) {
/* 284 */         implicitCollectionsForCurrentObject = writeValueToImplicitCollection(context, value, implicitCollectionsForCurrentObject, result, originalNodeName);
/*     */       }
/*     */       
/* 287 */       reader.moveUp();
/*     */     }
/*     */     
/* 290 */     return result;
/*     */   }
/*     */   
/*     */   protected Object unmarshallField(UnmarshallingContext context, Object result, Class type, Field field) {
/* 294 */     return context.convertAnother(result, type, this.mapper.getLocalConverter(field.getDeclaringClass(), field.getName()));
/*     */   }
/*     */   
/*     */   protected boolean shouldUnmarshalTransientFields() {
/* 298 */     return false;
/*     */   }
/*     */   
/*     */   private Map writeValueToImplicitCollection(UnmarshallingContext context, Object value, Map implicitCollections, Object result, String itemFieldName)
/*     */   {
/* 303 */     String fieldName = this.mapper.getFieldNameForItemTypeAndName(context.getRequiredType(), Mapper.Null.class, itemFieldName);
/*     */     
/*     */ 
/* 306 */     if (fieldName != null) {
/* 307 */       if (implicitCollections == null) {
/* 308 */         implicitCollections = new HashMap();
/*     */       }
/* 310 */       Collection collection = (Collection)implicitCollections.get(fieldName);
/* 311 */       if (collection == null) {
/* 312 */         Class fieldType = this.mapper.defaultImplementationOf(this.reflectionProvider.getFieldType(result, fieldName, null));
/*     */         
/* 314 */         if (!Collection.class.isAssignableFrom(fieldType)) {
/* 315 */           throw new ObjectAccessException("Field " + fieldName + " of " + result.getClass().getName() + " is configured for an implicit Collection, but field is of type " + fieldType.getName());
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 322 */         if (this.pureJavaReflectionProvider == null) {
/* 323 */           this.pureJavaReflectionProvider = new PureJavaReflectionProvider();
/*     */         }
/* 325 */         collection = (Collection)this.pureJavaReflectionProvider.newInstance(fieldType);
/* 326 */         this.reflectionProvider.writeField(result, fieldName, collection, null);
/* 327 */         implicitCollections.put(fieldName, collection);
/*     */       }
/* 329 */       collection.add(value);
/*     */     } else {
/* 331 */       throw new ConversionException("Element " + itemFieldName + " of type " + value.getClass().getName() + " is not defined as field in type " + result.getClass().getName());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 338 */     return implicitCollections;
/*     */   }
/*     */   
/*     */   private Class determineWhichClassDefinesField(HierarchicalStreamReader reader) {
/* 342 */     String attributeName = this.mapper.aliasForSystemAttribute("defined-in");
/* 343 */     String definedIn = attributeName == null ? null : reader.getAttribute(attributeName);
/* 344 */     return definedIn == null ? null : this.mapper.realClass(definedIn);
/*     */   }
/*     */   
/*     */   protected Object instantiateNewInstance(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 348 */     String attributeName = this.mapper.aliasForSystemAttribute("resolves-to");
/* 349 */     String readResolveValue = attributeName == null ? null : reader.getAttribute(attributeName);
/* 350 */     Object currentObject = context.currentObject();
/* 351 */     if (currentObject != null)
/* 352 */       return currentObject;
/* 353 */     if (readResolveValue != null) {
/* 354 */       return this.reflectionProvider.newInstance(this.mapper.realClass(readResolveValue));
/*     */     }
/* 356 */     return this.reflectionProvider.newInstance(context.getRequiredType());
/*     */   }
/*     */   
/*     */   private Class determineType(HierarchicalStreamReader reader, boolean validField, Object result, String fieldName, Class definedInCls)
/*     */   {
/* 361 */     String classAttribute = HierarchicalStreams.readClassAttribute(reader, this.mapper);
/* 362 */     if (!validField) {
/* 363 */       Class itemType = this.mapper.getItemTypeForItemFieldName(result.getClass(), fieldName);
/* 364 */       if (itemType != null) {
/* 365 */         return itemType;
/*     */       }
/* 367 */       String originalNodeName = reader.getNodeName();
/* 368 */       if (definedInCls == null) {
/* 369 */         for (definedInCls = result.getClass(); definedInCls != null; definedInCls = definedInCls.getSuperclass()) {
/* 370 */           if (!this.mapper.shouldSerializeMember(definedInCls, originalNodeName)) {
/* 371 */             return null;
/*     */           }
/*     */         }
/*     */       }
/*     */       try {
/* 376 */         return this.mapper.realClass(originalNodeName);
/*     */       } catch (CannotResolveClassException e) {
/* 378 */         throw new UnknownFieldException(result.getClass().getName(), fieldName);
/*     */       }
/*     */     }
/*     */     
/* 382 */     if (classAttribute != null) {
/* 383 */       return this.mapper.realClass(classAttribute);
/*     */     }
/* 385 */     return this.mapper.defaultImplementationOf(this.reflectionProvider.getFieldType(result, fieldName, definedInCls));
/*     */   }
/*     */   
/*     */   private Object readResolve()
/*     */   {
/* 390 */     this.serializationMethodInvoker = new SerializationMethodInvoker();
/* 391 */     return this;
/*     */   }
/*     */   
/*     */   public static class DuplicateFieldException extends ConversionException {
/*     */     public DuplicateFieldException(String msg) {
/* 396 */       super();
/* 397 */       add("duplicate-field", msg);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UnknownFieldException extends ConversionException {
/*     */     public UnknownFieldException(String type, String field) {
/* 403 */       super();
/* 404 */       add("field", field);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class FieldInfo {
/*     */     final String fieldName;
/*     */     final Class type;
/*     */     final Class definedIn;
/*     */     final Object value;
/*     */     
/*     */     FieldInfo(String fieldName, Class type, Class definedIn, Object value) {
/* 415 */       this.fieldName = fieldName;
/* 416 */       this.type = type;
/* 417 */       this.definedIn = definedIn;
/* 418 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/AbstractReflectionConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */