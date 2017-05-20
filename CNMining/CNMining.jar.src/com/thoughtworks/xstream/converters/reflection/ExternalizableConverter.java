/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
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
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.NotActiveException;
/*     */ import java.io.ObjectInputValidation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExternalizableConverter
/*     */   implements Converter
/*     */ {
/*     */   private Mapper mapper;
/*     */   private final ClassLoader classLoader;
/*     */   
/*     */   public ExternalizableConverter(Mapper mapper, ClassLoader classLoader)
/*     */   {
/*  46 */     this.mapper = mapper;
/*  47 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public ExternalizableConverter(Mapper mapper) {
/*  54 */     this(mapper, null);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  58 */     return Externalizable.class.isAssignableFrom(type);
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*     */     try {
/*  63 */       Externalizable externalizable = (Externalizable)source;
/*  64 */       CustomObjectOutputStream.StreamCallback callback = new CustomObjectOutputStream.StreamCallback() { private final HierarchicalStreamWriter val$writer;
/*     */         
/*  66 */         public void writeToStream(Object object) { if (object == null) {
/*  67 */             this.val$writer.startNode("null");
/*  68 */             this.val$writer.endNode();
/*     */           } else {
/*  70 */             ExtendedHierarchicalStreamWriterHelper.startNode(this.val$writer, ExternalizableConverter.this.mapper.serializedClass(object.getClass()), object.getClass());
/*  71 */             this.val$context.convertAnother(object);
/*  72 */             this.val$writer.endNode();
/*     */           }
/*     */         }
/*     */         
/*     */         private final MarshallingContext val$context;
/*  77 */         public void writeFieldsToStream(Map fields) { throw new UnsupportedOperationException(); }
/*     */         
/*     */         public void defaultWriteObject()
/*     */         {
/*  81 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void flush() {
/*  85 */           this.val$writer.flush();
/*     */         }
/*     */         
/*     */         public void close() {
/*  89 */           throw new UnsupportedOperationException("Objects are not allowed to call ObjectOutput.close() from writeExternal()");
/*     */         }
/*  91 */       };
/*  92 */       CustomObjectOutputStream objectOutput = CustomObjectOutputStream.getInstance(context, callback);
/*  93 */       externalizable.writeExternal(objectOutput);
/*  94 */       objectOutput.popCallback();
/*     */     } catch (IOException e) {
/*  96 */       throw new ConversionException("Cannot serialize " + source.getClass().getName() + " using Externalization", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 101 */     Class type = context.getRequiredType();
/*     */     try
/*     */     {
/* 104 */       Constructor defaultConstructor = type.getDeclaredConstructor((Class[])null);
/* 105 */       if (!defaultConstructor.isAccessible()) {
/* 106 */         defaultConstructor.setAccessible(true);
/*     */       }
/* 108 */       Externalizable externalizable = (Externalizable)defaultConstructor.newInstance((Object[])null);
/* 109 */       CustomObjectInputStream.StreamCallback callback = new CustomObjectInputStream.StreamCallback() { private final HierarchicalStreamReader val$reader;
/*     */         
/* 111 */         public Object readFromStream() { this.val$reader.moveDown();
/* 112 */           Class type = HierarchicalStreams.readClassType(this.val$reader, ExternalizableConverter.this.mapper);
/* 113 */           Object streamItem = this.val$context.convertAnother(this.val$externalizable, type);
/* 114 */           this.val$reader.moveUp();
/* 115 */           return streamItem; }
/*     */         
/*     */         private final UnmarshallingContext val$context;
/*     */         private final Externalizable val$externalizable;
/* 119 */         public Map readFieldsFromStream() { throw new UnsupportedOperationException(); }
/*     */         
/*     */         public void defaultReadObject()
/*     */         {
/* 123 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void registerValidation(ObjectInputValidation validation, int priority) throws NotActiveException {
/* 127 */           throw new NotActiveException("stream inactive");
/*     */         }
/*     */         
/*     */         public void close() {
/* 131 */           throw new UnsupportedOperationException("Objects are not allowed to call ObjectInput.close() from readExternal()");
/*     */         }
/* 133 */       };
/* 134 */       CustomObjectInputStream objectInput = CustomObjectInputStream.getInstance(context, callback, this.classLoader);
/* 135 */       externalizable.readExternal(objectInput);
/* 136 */       objectInput.popCallback();
/* 137 */       return externalizable;
/*     */     } catch (NoSuchMethodException e) {
/* 139 */       throw new ConversionException("Cannot construct " + type.getClass() + ", missing default constructor", e);
/*     */     } catch (InvocationTargetException e) {
/* 141 */       throw new ConversionException("Cannot construct " + type.getClass(), e);
/*     */     } catch (InstantiationException e) {
/* 143 */       throw new ConversionException("Cannot construct " + type.getClass(), e);
/*     */     } catch (IllegalAccessException e) {
/* 145 */       throw new ConversionException("Cannot construct " + type.getClass(), e);
/*     */     } catch (IOException e) {
/* 147 */       throw new ConversionException("Cannot externalize " + type.getClass(), e);
/*     */     } catch (ClassNotFoundException e) {
/* 149 */       throw new ConversionException("Cannot externalize " + type.getClass(), e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/ExternalizableConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */