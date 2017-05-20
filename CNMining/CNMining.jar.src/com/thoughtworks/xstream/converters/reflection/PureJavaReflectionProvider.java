/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.JVM;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PureJavaReflectionProvider
/*     */   implements ReflectionProvider
/*     */ {
/*  45 */   private transient Map serializedDataCache = new WeakHashMap();
/*     */   protected FieldDictionary fieldDictionary;
/*     */   
/*     */   public PureJavaReflectionProvider() {
/*  49 */     this(new FieldDictionary(new ImmutableFieldKeySorter()));
/*     */   }
/*     */   
/*     */   public PureJavaReflectionProvider(FieldDictionary fieldDictionary) {
/*  53 */     this.fieldDictionary = fieldDictionary;
/*     */   }
/*     */   
/*     */   public Object newInstance(Class type) {
/*     */     try {
/*  58 */       Constructor[] constructors = type.getDeclaredConstructors();
/*  59 */       for (int i = 0; i < constructors.length; i++) {
/*  60 */         if (constructors[i].getParameterTypes().length == 0) {
/*  61 */           if (!Modifier.isPublic(constructors[i].getModifiers())) {
/*  62 */             constructors[i].setAccessible(true);
/*     */           }
/*  64 */           return constructors[i].newInstance(new Object[0]);
/*     */         }
/*     */       }
/*  67 */       if (Serializable.class.isAssignableFrom(type)) {
/*  68 */         return instantiateUsingSerialization(type);
/*     */       }
/*  70 */       throw new ObjectAccessException("Cannot construct " + type.getName() + " as it does not have a no-args constructor");
/*     */     }
/*     */     catch (InstantiationException e)
/*     */     {
/*  74 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/*  76 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (InvocationTargetException e) {
/*  78 */       if ((e.getTargetException() instanceof RuntimeException))
/*  79 */         throw ((RuntimeException)e.getTargetException());
/*  80 */       if ((e.getTargetException() instanceof Error)) {
/*  81 */         throw ((Error)e.getTargetException());
/*     */       }
/*  83 */       throw new ObjectAccessException("Constructor for " + type.getName() + " threw an exception", e.getTargetException());
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private Object instantiateUsingSerialization(Class type)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 14	com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider:serializedDataCache	Ljava/util/Map;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 14	com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider:serializedDataCache	Ljava/util/Map;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 46 2 0
/*     */     //   17: checkcast 47	[B
/*     */     //   20: checkcast 47	[B
/*     */     //   23: astore_3
/*     */     //   24: aload_3
/*     */     //   25: ifnonnull +116 -> 141
/*     */     //   28: new 48	java/io/ByteArrayOutputStream
/*     */     //   31: dup
/*     */     //   32: invokespecial 49	java/io/ByteArrayOutputStream:<init>	()V
/*     */     //   35: astore 4
/*     */     //   37: new 50	java/io/DataOutputStream
/*     */     //   40: dup
/*     */     //   41: aload 4
/*     */     //   43: invokespecial 51	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
/*     */     //   46: astore 5
/*     */     //   48: aload 5
/*     */     //   50: sipush 44269
/*     */     //   53: invokevirtual 52	java/io/DataOutputStream:writeShort	(I)V
/*     */     //   56: aload 5
/*     */     //   58: iconst_5
/*     */     //   59: invokevirtual 52	java/io/DataOutputStream:writeShort	(I)V
/*     */     //   62: aload 5
/*     */     //   64: bipush 115
/*     */     //   66: invokevirtual 53	java/io/DataOutputStream:writeByte	(I)V
/*     */     //   69: aload 5
/*     */     //   71: bipush 114
/*     */     //   73: invokevirtual 53	java/io/DataOutputStream:writeByte	(I)V
/*     */     //   76: aload 5
/*     */     //   78: aload_1
/*     */     //   79: invokevirtual 33	java/lang/Class:getName	()Ljava/lang/String;
/*     */     //   82: invokevirtual 54	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
/*     */     //   85: aload 5
/*     */     //   87: aload_1
/*     */     //   88: invokestatic 55	java/io/ObjectStreamClass:lookup	(Ljava/lang/Class;)Ljava/io/ObjectStreamClass;
/*     */     //   91: invokevirtual 56	java/io/ObjectStreamClass:getSerialVersionUID	()J
/*     */     //   94: invokevirtual 57	java/io/DataOutputStream:writeLong	(J)V
/*     */     //   97: aload 5
/*     */     //   99: iconst_2
/*     */     //   100: invokevirtual 53	java/io/DataOutputStream:writeByte	(I)V
/*     */     //   103: aload 5
/*     */     //   105: iconst_0
/*     */     //   106: invokevirtual 52	java/io/DataOutputStream:writeShort	(I)V
/*     */     //   109: aload 5
/*     */     //   111: bipush 120
/*     */     //   113: invokevirtual 53	java/io/DataOutputStream:writeByte	(I)V
/*     */     //   116: aload 5
/*     */     //   118: bipush 112
/*     */     //   120: invokevirtual 53	java/io/DataOutputStream:writeByte	(I)V
/*     */     //   123: aload 4
/*     */     //   125: invokevirtual 58	java/io/ByteArrayOutputStream:toByteArray	()[B
/*     */     //   128: astore_3
/*     */     //   129: aload_0
/*     */     //   130: getfield 14	com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider:serializedDataCache	Ljava/util/Map;
/*     */     //   133: aload_1
/*     */     //   134: aload_3
/*     */     //   135: invokeinterface 59 3 0
/*     */     //   140: pop
/*     */     //   141: new 60	com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider$1
/*     */     //   144: dup
/*     */     //   145: aload_0
/*     */     //   146: new 61	java/io/ByteArrayInputStream
/*     */     //   149: dup
/*     */     //   150: aload_3
/*     */     //   151: invokespecial 62	java/io/ByteArrayInputStream:<init>	([B)V
/*     */     //   154: aload_1
/*     */     //   155: invokespecial 63	com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider$1:<init>	(Lcom/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider;Ljava/io/InputStream;Ljava/lang/Class;)V
/*     */     //   158: astore 4
/*     */     //   160: aload 4
/*     */     //   162: invokevirtual 64	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
/*     */     //   165: aload_2
/*     */     //   166: monitorexit
/*     */     //   167: areturn
/*     */     //   168: astore 6
/*     */     //   170: aload_2
/*     */     //   171: monitorexit
/*     */     //   172: aload 6
/*     */     //   174: athrow
/*     */     //   175: astore_2
/*     */     //   176: new 28	com/thoughtworks/xstream/converters/reflection/ObjectAccessException
/*     */     //   179: dup
/*     */     //   180: new 29	java/lang/StringBuffer
/*     */     //   183: dup
/*     */     //   184: invokespecial 30	java/lang/StringBuffer:<init>	()V
/*     */     //   187: ldc 66
/*     */     //   189: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   192: aload_1
/*     */     //   193: invokevirtual 33	java/lang/Class:getName	()Ljava/lang/String;
/*     */     //   196: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   199: ldc 67
/*     */     //   201: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   204: invokevirtual 35	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   207: aload_2
/*     */     //   208: invokespecial 38	com/thoughtworks/xstream/converters/reflection/ObjectAccessException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   211: athrow
/*     */     //   212: astore_2
/*     */     //   213: new 28	com/thoughtworks/xstream/converters/reflection/ObjectAccessException
/*     */     //   216: dup
/*     */     //   217: new 29	java/lang/StringBuffer
/*     */     //   220: dup
/*     */     //   221: invokespecial 30	java/lang/StringBuffer:<init>	()V
/*     */     //   224: ldc 68
/*     */     //   226: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   229: aload_2
/*     */     //   230: invokevirtual 4	java/lang/ClassNotFoundException:getMessage	()Ljava/lang/String;
/*     */     //   233: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   236: invokevirtual 35	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   239: invokespecial 36	com/thoughtworks/xstream/converters/reflection/ObjectAccessException:<init>	(Ljava/lang/String;)V
/*     */     //   242: athrow
/*     */     // Line number table:
/*     */     //   Java source line #90	-> byte code offset #0
/*     */     //   Java source line #91	-> byte code offset #7
/*     */     //   Java source line #92	-> byte code offset #24
/*     */     //   Java source line #93	-> byte code offset #28
/*     */     //   Java source line #94	-> byte code offset #37
/*     */     //   Java source line #95	-> byte code offset #48
/*     */     //   Java source line #96	-> byte code offset #56
/*     */     //   Java source line #97	-> byte code offset #62
/*     */     //   Java source line #98	-> byte code offset #69
/*     */     //   Java source line #99	-> byte code offset #76
/*     */     //   Java source line #100	-> byte code offset #85
/*     */     //   Java source line #101	-> byte code offset #97
/*     */     //   Java source line #102	-> byte code offset #103
/*     */     //   Java source line #103	-> byte code offset #109
/*     */     //   Java source line #104	-> byte code offset #116
/*     */     //   Java source line #105	-> byte code offset #123
/*     */     //   Java source line #106	-> byte code offset #129
/*     */     //   Java source line #109	-> byte code offset #141
/*     */     //   Java source line #115	-> byte code offset #160
/*     */     //   Java source line #116	-> byte code offset #168
/*     */     //   Java source line #117	-> byte code offset #175
/*     */     //   Java source line #118	-> byte code offset #176
/*     */     //   Java source line #119	-> byte code offset #212
/*     */     //   Java source line #120	-> byte code offset #213
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	243	0	this	PureJavaReflectionProvider
/*     */     //   0	243	1	type	Class
/*     */     //   175	33	2	e	IOException
/*     */     //   212	18	2	e	ClassNotFoundException
/*     */     //   23	128	3	data	byte[]
/*     */     //   35	89	4	bytes	java.io.ByteArrayOutputStream
/*     */     //   158	3	4	in	ObjectInputStream
/*     */     //   46	71	5	stream	java.io.DataOutputStream
/*     */     //   168	5	6	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	167	168	finally
/*     */     //   168	172	168	finally
/*     */     //   0	167	175	java/io/IOException
/*     */     //   168	175	175	java/io/IOException
/*     */     //   0	167	212	java/lang/ClassNotFoundException
/*     */     //   168	175	212	java/lang/ClassNotFoundException
/*     */   }
/*     */   
/*     */   public void visitSerializableFields(Object object, ReflectionProvider.Visitor visitor)
/*     */   {
/* 125 */     for (Iterator iterator = this.fieldDictionary.fieldsFor(object.getClass()); iterator.hasNext();) {
/* 126 */       Field field = (Field)iterator.next();
/* 127 */       if (fieldModifiersSupported(field))
/*     */       {
/*     */ 
/* 130 */         validateFieldAccess(field);
/*     */         try {
/* 132 */           Object value = field.get(object);
/* 133 */           visitor.visit(field.getName(), field.getType(), field.getDeclaringClass(), value);
/*     */         } catch (IllegalArgumentException e) {
/* 135 */           throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */         } catch (IllegalAccessException e) {
/* 137 */           throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 143 */   public void writeField(Object object, String fieldName, Object value, Class definedIn) { Field field = this.fieldDictionary.field(object.getClass(), fieldName, definedIn);
/* 144 */     validateFieldAccess(field);
/*     */     try {
/* 146 */       field.set(object, value);
/*     */     } catch (IllegalArgumentException e) {
/* 148 */       throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/* 150 */       throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public Class getFieldType(Object object, String fieldName, Class definedIn) {
/* 155 */     return this.fieldDictionary.field(object.getClass(), fieldName, definedIn).getType();
/*     */   }
/*     */   
/*     */   public boolean fieldDefinedInClass(String fieldName, Class type) {
/* 159 */     Field field = this.fieldDictionary.fieldOrNull(type, fieldName, null);
/* 160 */     return (field != null) && ((fieldModifiersSupported(field)) || (Modifier.isTransient(field.getModifiers())));
/*     */   }
/*     */   
/*     */   protected boolean fieldModifiersSupported(Field field) {
/* 164 */     return (!Modifier.isStatic(field.getModifiers())) && (!Modifier.isTransient(field.getModifiers()));
/*     */   }
/*     */   
/*     */   protected void validateFieldAccess(Field field)
/*     */   {
/* 169 */     if (Modifier.isFinal(field.getModifiers())) {
/* 170 */       if (JVM.is15()) {
/* 171 */         field.setAccessible(true);
/*     */       } else {
/* 173 */         throw new ObjectAccessException("Invalid final field " + field.getDeclaringClass().getName() + "." + field.getName());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Field getField(Class definedIn, String fieldName)
/*     */   {
/* 180 */     return this.fieldDictionary.field(definedIn, fieldName, null);
/*     */   }
/*     */   
/*     */   public void setFieldDictionary(FieldDictionary dictionary) {
/* 184 */     this.fieldDictionary = dictionary;
/*     */   }
/*     */   
/*     */   protected Object readResolve() {
/* 188 */     this.serializedDataCache = new WeakHashMap();
/* 189 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/PureJavaReflectionProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */