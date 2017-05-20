/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
/*     */ import com.thoughtworks.xstream.core.util.CustomObjectInputStream.StreamCallback;
/*     */ import com.thoughtworks.xstream.core.util.CustomObjectOutputStream;
/*     */ import com.thoughtworks.xstream.core.util.CustomObjectOutputStream.StreamCallback;
/*     */ import com.thoughtworks.xstream.core.util.HierarchicalStreams;
/*     */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectInputValidation;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
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
/*     */ public class SerializableConverter
/*     */   extends AbstractReflectionConverter
/*     */ {
/*     */   private static final String ELEMENT_NULL = "null";
/*     */   private static final String ELEMENT_DEFAULT = "default";
/*     */   private static final String ELEMENT_UNSERIALIZABLE_PARENTS = "unserializable-parents";
/*     */   private static final String ATTRIBUTE_CLASS = "class";
/*     */   private static final String ATTRIBUTE_SERIALIZATION = "serialization";
/*     */   private static final String ATTRIBUTE_VALUE_CUSTOM = "custom";
/*     */   private static final String ELEMENT_FIELDS = "fields";
/*     */   private static final String ELEMENT_FIELD = "field";
/*     */   private static final String ATTRIBUTE_NAME = "name";
/*     */   private final ClassLoader classLoader;
/*     */   
/*     */   public SerializableConverter(Mapper mapper, ReflectionProvider reflectionProvider, ClassLoader classLoader)
/*     */   {
/*  74 */     super(mapper, new UnserializableParentsReflectionProvider(reflectionProvider));
/*  75 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SerializableConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
/*  82 */     this(mapper, new UnserializableParentsReflectionProvider(reflectionProvider), null);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  86 */     return isSerializable(type);
/*     */   }
/*     */   
/*     */   private boolean isSerializable(Class type) {
/*  90 */     return (Serializable.class.isAssignableFrom(type)) && ((this.serializationMethodInvoker.supportsReadObject(type, true)) || (this.serializationMethodInvoker.supportsWriteObject(type, true)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void doMarshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  96 */     String attributeName = this.mapper.aliasForSystemAttribute("serialization");
/*  97 */     if (attributeName != null) {
/*  98 */       writer.addAttribute(attributeName, "custom");
/*     */     }
/*     */     
/*     */ 
/* 102 */     Class[] currentType = new Class[1];
/* 103 */     boolean[] writtenClassWrapper = { false };
/*     */     
/* 105 */     CustomObjectOutputStream.StreamCallback callback = new CustomObjectOutputStream.StreamCallback() { private final HierarchicalStreamWriter val$writer;
/*     */       private final MarshallingContext val$context;
/*     */       
/* 108 */       public void writeToStream(Object object) { if (object == null) {
/* 109 */           this.val$writer.startNode("null");
/* 110 */           this.val$writer.endNode();
/*     */         } else {
/* 112 */           ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, SerializableConverter.this.mapper.serializedClass(object.getClass()), object.getClass());
/* 113 */           this.val$context.convertAnother(object);
/* 114 */           this.val$writer.endNode(); } }
/*     */       
/*     */       private final Class[] val$currentType;
/*     */       private final Object val$source;
/*     */       private final boolean[] val$writtenClassWrapper;
/* 119 */       public void writeFieldsToStream(Map fields) { ObjectStreamClass objectStreamClass = ObjectStreamClass.lookup(this.val$currentType[0]);
/*     */         
/* 121 */         this.val$writer.startNode("default");
/* 122 */         for (Iterator iterator = fields.keySet().iterator(); iterator.hasNext();) {
/* 123 */           String name = (String)iterator.next();
/* 124 */           if (SerializableConverter.this.mapper.shouldSerializeMember(this.val$currentType[0], name))
/*     */           {
/*     */ 
/* 127 */             ObjectStreamField field = objectStreamClass.getField(name);
/* 128 */             Object value = fields.get(name);
/* 129 */             if (field == null) {
/* 130 */               throw new ObjectAccessException("Class " + value.getClass().getName() + " may not write a field named '" + name + "'");
/*     */             }
/*     */             
/* 133 */             if (value != null) {
/* 134 */               ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, SerializableConverter.this.mapper.serializedMember(this.val$source.getClass(), name), field.getType());
/* 135 */               if ((field.getType() != value.getClass()) && (!field.getType().isPrimitive())) {
/* 136 */                 String attributeName = SerializableConverter.this.mapper.aliasForSystemAttribute("class");
/* 137 */                 if (attributeName != null) {
/* 138 */                   this.val$writer.addAttribute(attributeName, SerializableConverter.this.mapper.serializedClass(value.getClass()));
/*     */                 }
/*     */               }
/* 141 */               this.val$context.convertAnother(value);
/* 142 */               this.val$writer.endNode();
/*     */             }
/*     */           } }
/* 145 */         this.val$writer.endNode();
/*     */       }
/*     */       
/*     */       public void defaultWriteObject() {
/* 149 */         boolean writtenDefaultFields = false;
/*     */         
/* 151 */         ObjectStreamClass objectStreamClass = ObjectStreamClass.lookup(this.val$currentType[0]);
/*     */         
/* 153 */         if (objectStreamClass == null) {
/* 154 */           return;
/*     */         }
/*     */         
/* 157 */         ObjectStreamField[] fields = objectStreamClass.getFields();
/* 158 */         for (int i = 0; i < fields.length; i++) {
/* 159 */           ObjectStreamField field = fields[i];
/* 160 */           Object value = SerializableConverter.this.readField(field, this.val$currentType[0], this.val$source);
/* 161 */           if (value != null) {
/* 162 */             if (this.val$writtenClassWrapper[0] == 0) {
/* 163 */               this.val$writer.startNode(SerializableConverter.this.mapper.serializedClass(this.val$currentType[0]));
/* 164 */               this.val$writtenClassWrapper[0] = true;
/*     */             }
/* 166 */             if (!writtenDefaultFields) {
/* 167 */               this.val$writer.startNode("default");
/* 168 */               writtenDefaultFields = true;
/*     */             }
/* 170 */             if (SerializableConverter.this.mapper.shouldSerializeMember(this.val$currentType[0], field.getName()))
/*     */             {
/*     */ 
/*     */ 
/* 174 */               ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, SerializableConverter.this.mapper.serializedMember(this.val$source.getClass(), field.getName()), field.getType());
/*     */               
/* 176 */               Class actualType = value.getClass();
/* 177 */               Class defaultType = SerializableConverter.this.mapper.defaultImplementationOf(field.getType());
/* 178 */               if (!actualType.equals(defaultType)) {
/* 179 */                 String attributeName = SerializableConverter.this.mapper.aliasForSystemAttribute("class");
/* 180 */                 if (attributeName != null) {
/* 181 */                   this.val$writer.addAttribute(attributeName, SerializableConverter.this.mapper.serializedClass(actualType));
/*     */                 }
/*     */               }
/*     */               
/* 185 */               this.val$context.convertAnother(value);
/*     */               
/* 187 */               this.val$writer.endNode();
/*     */             }
/*     */           } }
/* 190 */         if ((this.val$writtenClassWrapper[0] != 0) && (!writtenDefaultFields)) {
/* 191 */           this.val$writer.startNode("default");
/* 192 */           this.val$writer.endNode();
/* 193 */         } else if (writtenDefaultFields) {
/* 194 */           this.val$writer.endNode();
/*     */         }
/*     */       }
/*     */       
/*     */       public void flush() {
/* 199 */         this.val$writer.flush();
/*     */       }
/*     */       
/*     */       public void close() {
/* 203 */         throw new UnsupportedOperationException("Objects are not allowed to call ObjectOutputStream.close() from writeObject()");
/*     */       }
/*     */     };
/*     */     try
/*     */     {
/* 208 */       boolean mustHandleUnserializableParent = false;
/* 209 */       Iterator classHieararchy = hierarchyFor(source.getClass()).iterator();
/* 210 */       while (classHieararchy.hasNext()) {
/* 211 */         currentType[0] = ((Class)classHieararchy.next());
/* 212 */         if (!Serializable.class.isAssignableFrom(currentType[0])) {
/* 213 */           mustHandleUnserializableParent = true;
/*     */         }
/*     */         else {
/* 216 */           if (mustHandleUnserializableParent) {
/* 217 */             marshalUnserializableParent(writer, context, source);
/* 218 */             mustHandleUnserializableParent = false;
/*     */           }
/* 220 */           if (this.serializationMethodInvoker.supportsWriteObject(currentType[0], false)) {
/* 221 */             writtenClassWrapper[0] = true;
/* 222 */             writer.startNode(this.mapper.serializedClass(currentType[0]));
/* 223 */             CustomObjectOutputStream objectOutputStream = CustomObjectOutputStream.getInstance(context, callback);
/* 224 */             this.serializationMethodInvoker.callWriteObject(currentType[0], source, objectOutputStream);
/* 225 */             objectOutputStream.popCallback();
/* 226 */             writer.endNode();
/* 227 */           } else if (this.serializationMethodInvoker.supportsReadObject(currentType[0], false))
/*     */           {
/*     */ 
/*     */ 
/* 231 */             writtenClassWrapper[0] = true;
/* 232 */             writer.startNode(this.mapper.serializedClass(currentType[0]));
/* 233 */             callback.defaultWriteObject();
/* 234 */             writer.endNode();
/*     */           } else {
/* 236 */             writtenClassWrapper[0] = false;
/* 237 */             callback.defaultWriteObject();
/* 238 */             if (writtenClassWrapper[0] != 0) {
/* 239 */               writer.endNode();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 245 */       throw new ObjectAccessException("Could not call defaultWriteObject()", e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void marshalUnserializableParent(HierarchicalStreamWriter writer, MarshallingContext context, Object replacedSource) {
/* 250 */     writer.startNode("unserializable-parents");
/* 251 */     super.doMarshal(replacedSource, writer, context);
/* 252 */     writer.endNode();
/*     */   }
/*     */   
/*     */   private Object readField(ObjectStreamField field, Class type, Object instance) {
/*     */     try {
/* 257 */       Field javaField = type.getDeclaredField(field.getName());
/* 258 */       javaField.setAccessible(true);
/* 259 */       return javaField.get(instance);
/*     */     } catch (IllegalArgumentException e) {
/* 261 */       throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/* 263 */       throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */     } catch (NoSuchFieldException e) {
/* 265 */       throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */     } catch (SecurityException e) {
/* 267 */       throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   protected List hierarchyFor(Class type) {
/* 272 */     List result = new ArrayList();
/* 273 */     while (type != Object.class) {
/* 274 */       result.add(type);
/* 275 */       type = type.getSuperclass();
/*     */     }
/*     */     
/*     */ 
/* 279 */     Collections.reverse(result);
/*     */     
/* 281 */     return result;
/*     */   }
/*     */   
/*     */   public Object doUnmarshal(Object result, HierarchicalStreamReader reader, UnmarshallingContext context)
/*     */   {
/* 286 */     Class[] currentType = new Class[1];
/*     */     
/* 288 */     String attributeName = this.mapper.aliasForSystemAttribute("serialization");
/* 289 */     if ((attributeName != null) && (!"custom".equals(reader.getAttribute(attributeName)))) {
/* 290 */       throw new ConversionException("Cannot deserialize object with new readObject()/writeObject() methods");
/*     */     }
/*     */     
/* 293 */     CustomObjectInputStream.StreamCallback callback = new CustomObjectInputStream.StreamCallback() { private final HierarchicalStreamReader val$reader;
/*     */       
/* 295 */       public Object readFromStream() { this.val$reader.moveDown();
/* 296 */         Class type = HierarchicalStreams.readClassType(this.val$reader, SerializableConverter.this.mapper);
/* 297 */         Object value = this.val$context.convertAnother(this.val$result, type);
/* 298 */         this.val$reader.moveUp();
/* 299 */         return value;
/*     */       }
/*     */       
/*     */       private final UnmarshallingContext val$context;
/* 303 */       public Map readFieldsFromStream() { Map fields = new HashMap();
/* 304 */         this.val$reader.moveDown();
/* 305 */         if (this.val$reader.getNodeName().equals("fields"))
/*     */         {
/* 307 */           while (this.val$reader.hasMoreChildren()) {
/* 308 */             this.val$reader.moveDown();
/* 309 */             if (!this.val$reader.getNodeName().equals("field")) {
/* 310 */               throw new ConversionException("Expected <field/> element inside <field/>");
/*     */             }
/* 312 */             String name = this.val$reader.getAttribute("name");
/* 313 */             Class type = SerializableConverter.this.mapper.realClass(this.val$reader.getAttribute("class"));
/* 314 */             Object value = this.val$context.convertAnother(this.val$result, type);
/* 315 */             fields.put(name, value);
/* 316 */             this.val$reader.moveUp();
/*     */           } }
/* 318 */         if (this.val$reader.getNodeName().equals("default"))
/*     */         {
/* 320 */           ObjectStreamClass objectStreamClass = ObjectStreamClass.lookup(this.val$currentType[0]);
/* 321 */           while (this.val$reader.hasMoreChildren()) {
/* 322 */             this.val$reader.moveDown();
/* 323 */             String name = SerializableConverter.this.mapper.realMember(this.val$currentType[0], this.val$reader.getNodeName());
/* 324 */             if (SerializableConverter.this.mapper.shouldSerializeMember(this.val$currentType[0], name)) {
/* 325 */               String classAttribute = HierarchicalStreams.readClassAttribute(this.val$reader, SerializableConverter.this.mapper);
/*     */               Class type;
/* 327 */               Class type; if (classAttribute != null) {
/* 328 */                 type = SerializableConverter.this.mapper.realClass(classAttribute);
/*     */               } else {
/* 330 */                 ObjectStreamField field = objectStreamClass.getField(name);
/* 331 */                 if (field == null) {
/* 332 */                   throw new ObjectAccessException("Class " + this.val$currentType[0] + " does not contain a field named '" + name + "'");
/*     */                 }
/*     */                 
/* 335 */                 type = field.getType();
/*     */               }
/* 337 */               Object value = this.val$context.convertAnother(this.val$result, type);
/* 338 */               fields.put(name, value);
/*     */             }
/* 340 */             this.val$reader.moveUp();
/*     */           }
/*     */         } else {
/* 343 */           throw new ConversionException("Expected <fields/> or <default/> element when calling ObjectInputStream.readFields()");
/*     */         }
/*     */         
/* 346 */         this.val$reader.moveUp();
/* 347 */         return fields;
/*     */       }
/*     */       
/*     */       public void defaultReadObject() {
/* 351 */         if (!this.val$reader.hasMoreChildren()) {
/* 352 */           return;
/*     */         }
/* 354 */         this.val$reader.moveDown();
/* 355 */         if (!this.val$reader.getNodeName().equals("default")) {
/* 356 */           throw new ConversionException("Expected <default/> element in readObject() stream");
/*     */         }
/* 358 */         while (this.val$reader.hasMoreChildren()) {
/* 359 */           this.val$reader.moveDown();
/*     */           
/* 361 */           String fieldName = SerializableConverter.this.mapper.realMember(this.val$currentType[0], this.val$reader.getNodeName());
/* 362 */           if (SerializableConverter.this.mapper.shouldSerializeMember(this.val$currentType[0], fieldName)) {
/* 363 */             String classAttribute = HierarchicalStreams.readClassAttribute(this.val$reader, SerializableConverter.this.mapper);
/*     */             Class type;
/* 365 */             Class type; if (classAttribute != null) {
/* 366 */               type = SerializableConverter.this.mapper.realClass(classAttribute);
/*     */             } else {
/* 368 */               type = SerializableConverter.this.mapper.defaultImplementationOf(SerializableConverter.this.reflectionProvider.getFieldType(this.val$result, fieldName, this.val$currentType[0]));
/*     */             }
/*     */             
/* 371 */             Object value = this.val$context.convertAnother(this.val$result, type);
/* 372 */             SerializableConverter.this.reflectionProvider.writeField(this.val$result, fieldName, value, this.val$currentType[0]);
/*     */           }
/*     */           
/* 375 */           this.val$reader.moveUp();
/*     */         }
/* 377 */         this.val$reader.moveUp();
/*     */       }
/*     */       
/*     */       public void registerValidation(ObjectInputValidation validation, int priority) {
/* 381 */         this.val$context.addCompletionCallback(new Runnable() {
/*     */           private final ObjectInputValidation val$validation;
/*     */           
/* 384 */           public void run() { try { this.val$validation.validateObject();
/*     */             } catch (InvalidObjectException e) {
/* 386 */               throw new ObjectAccessException("Cannot validate object : " + e.getMessage(), e); } } }, priority);
/*     */       }
/*     */       
/*     */       private final Object val$result;
/*     */       private final Class[] val$currentType;
/*     */       public void close()
/*     */       {
/* 393 */         throw new UnsupportedOperationException("Objects are not allowed to call ObjectInputStream.close() from readObject()");
/*     */       }
/*     */     };
/*     */     
/* 397 */     while (reader.hasMoreChildren()) {
/* 398 */       reader.moveDown();
/* 399 */       String nodeName = reader.getNodeName();
/* 400 */       if (nodeName.equals("unserializable-parents")) {
/* 401 */         super.doUnmarshal(result, reader, context);
/*     */       } else {
/* 403 */         String classAttribute = HierarchicalStreams.readClassAttribute(reader, this.mapper);
/* 404 */         if (classAttribute == null) {
/* 405 */           currentType[0] = this.mapper.defaultImplementationOf(this.mapper.realClass(nodeName));
/*     */         } else {
/* 407 */           currentType[0] = this.mapper.realClass(classAttribute);
/*     */         }
/* 409 */         if (this.serializationMethodInvoker.supportsReadObject(currentType[0], false)) {
/* 410 */           CustomObjectInputStream objectInputStream = CustomObjectInputStream.getInstance(context, callback, this.classLoader);
/*     */           
/* 412 */           this.serializationMethodInvoker.callReadObject(currentType[0], result, objectInputStream);
/* 413 */           objectInputStream.popCallback();
/*     */         } else {
/*     */           try {
/* 416 */             callback.defaultReadObject();
/*     */           } catch (IOException e) {
/* 418 */             throw new ObjectAccessException("Could not call defaultWriteObject()", e);
/*     */           }
/*     */         }
/*     */       }
/* 422 */       reader.moveUp();
/*     */     }
/*     */     
/* 425 */     return result;
/*     */   }
/*     */   
/*     */   protected void doMarshalConditionally(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 429 */     if (isSerializable(source.getClass())) {
/* 430 */       doMarshal(source, writer, context);
/*     */     } else {
/* 432 */       super.doMarshal(source, writer, context);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object doUnmarshalConditionally(Object result, HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 437 */     return isSerializable(result.getClass()) ? doUnmarshal(result, reader, context) : super.doUnmarshal(result, reader, context);
/*     */   }
/*     */   
/*     */   private static class UnserializableParentsReflectionProvider extends ReflectionProviderWrapper
/*     */   {
/*     */     public UnserializableParentsReflectionProvider(ReflectionProvider reflectionProvider) {
/* 443 */       super();
/*     */     }
/*     */     
/*     */     public void visitSerializableFields(Object object, ReflectionProvider.Visitor visitor) {
/* 447 */       this.wrapped.visitSerializableFields(object, new ReflectionProvider.Visitor() { private final ReflectionProvider.Visitor val$visitor;
/*     */         
/* 449 */         public void visit(String name, Class type, Class definedIn, Object value) { if (!Serializable.class.isAssignableFrom(definedIn)) {
/* 450 */             this.val$visitor.visit(name, type, definedIn, value);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/SerializableConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */