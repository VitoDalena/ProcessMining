/*      */ package com.thoughtworks.xstream;
/*      */ 
/*      */ import com.thoughtworks.xstream.converters.ConversionException;
/*      */ import com.thoughtworks.xstream.converters.Converter;
/*      */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*      */ import com.thoughtworks.xstream.converters.ConverterRegistry;
/*      */ import com.thoughtworks.xstream.converters.DataHolder;
/*      */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*      */ import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
/*      */ import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.BigIntegerConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.BooleanConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.ByteConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.CharConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.DateConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.DoubleConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.FloatConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.IntConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.LongConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.NullConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.ShortConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.StringBufferConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.StringConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.URIConverter;
/*      */ import com.thoughtworks.xstream.converters.basic.URLConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.ArrayConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.BitSetConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.CharArrayConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.CollectionConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.MapConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.PropertiesConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.TreeMapConverter;
/*      */ import com.thoughtworks.xstream.converters.collections.TreeSetConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.ColorConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.DynamicProxyConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.FileConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.FontConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.GregorianCalendarConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.JavaClassConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.JavaFieldConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.JavaMethodConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.LocaleConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.LookAndFeelConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.SqlDateConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.SqlTimeConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.SqlTimestampConverter;
/*      */ import com.thoughtworks.xstream.converters.extended.TextAttributeConverter;
/*      */ import com.thoughtworks.xstream.converters.reflection.ExternalizableConverter;
/*      */ import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
/*      */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*      */ import com.thoughtworks.xstream.converters.reflection.SelfStreamingInstanceChecker;
/*      */ import com.thoughtworks.xstream.converters.reflection.SerializableConverter;
/*      */ import com.thoughtworks.xstream.core.DefaultConverterLookup;
/*      */ import com.thoughtworks.xstream.core.JVM;
/*      */ import com.thoughtworks.xstream.core.MapBackedDataHolder;
/*      */ import com.thoughtworks.xstream.core.ReferenceByIdMarshallingStrategy;
/*      */ import com.thoughtworks.xstream.core.ReferenceByXPathMarshallingStrategy;
/*      */ import com.thoughtworks.xstream.core.TreeMarshallingStrategy;
/*      */ import com.thoughtworks.xstream.core.util.ClassLoaderReference;
/*      */ import com.thoughtworks.xstream.core.util.CompositeClassLoader;
/*      */ import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
/*      */ import com.thoughtworks.xstream.core.util.CustomObjectInputStream.StreamCallback;
/*      */ import com.thoughtworks.xstream.core.util.CustomObjectOutputStream;
/*      */ import com.thoughtworks.xstream.core.util.CustomObjectOutputStream.StreamCallback;
/*      */ import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
/*      */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*      */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*      */ import com.thoughtworks.xstream.io.StatefulWriter;
/*      */ import com.thoughtworks.xstream.io.xml.XppDriver;
/*      */ import com.thoughtworks.xstream.mapper.AnnotationConfiguration;
/*      */ import com.thoughtworks.xstream.mapper.ArrayMapper;
/*      */ import com.thoughtworks.xstream.mapper.AttributeAliasingMapper;
/*      */ import com.thoughtworks.xstream.mapper.AttributeMapper;
/*      */ import com.thoughtworks.xstream.mapper.CachingMapper;
/*      */ import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
/*      */ import com.thoughtworks.xstream.mapper.DefaultImplementationsMapper;
/*      */ import com.thoughtworks.xstream.mapper.DefaultMapper;
/*      */ import com.thoughtworks.xstream.mapper.DynamicProxyMapper;
/*      */ import com.thoughtworks.xstream.mapper.FieldAliasingMapper;
/*      */ import com.thoughtworks.xstream.mapper.ImmutableTypesMapper;
/*      */ import com.thoughtworks.xstream.mapper.ImplicitCollectionMapper;
/*      */ import com.thoughtworks.xstream.mapper.LocalConversionMapper;
/*      */ import com.thoughtworks.xstream.mapper.Mapper;
/*      */ import com.thoughtworks.xstream.mapper.Mapper.Null;
/*      */ import com.thoughtworks.xstream.mapper.MapperWrapper;
/*      */ import com.thoughtworks.xstream.mapper.OuterClassMapper;
/*      */ import com.thoughtworks.xstream.mapper.PackageAliasingMapper;
/*      */ import com.thoughtworks.xstream.mapper.SystemAttributeAliasingMapper;
/*      */ import com.thoughtworks.xstream.mapper.XStream11XmlFriendlyMapper;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.NotActiveException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectInputValidation;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.BitSet;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import java.util.TreeSet;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XStream
/*      */ {
/*      */   private ReflectionProvider reflectionProvider;
/*      */   private HierarchicalStreamDriver hierarchicalStreamDriver;
/*      */   private ClassLoaderReference classLoaderReference;
/*      */   private MarshallingStrategy marshallingStrategy;
/*      */   private ConverterLookup converterLookup;
/*      */   private ConverterRegistry converterRegistry;
/*      */   private Mapper mapper;
/*      */   private PackageAliasingMapper packageAliasingMapper;
/*      */   private ClassAliasingMapper classAliasingMapper;
/*      */   private FieldAliasingMapper fieldAliasingMapper;
/*      */   private AttributeAliasingMapper attributeAliasingMapper;
/*      */   private SystemAttributeAliasingMapper systemAttributeAliasingMapper;
/*      */   private AttributeMapper attributeMapper;
/*      */   private DefaultImplementationsMapper defaultImplementationsMapper;
/*      */   private ImmutableTypesMapper immutableTypesMapper;
/*      */   private ImplicitCollectionMapper implicitCollectionMapper;
/*      */   private LocalConversionMapper localConversionMapper;
/*      */   private AnnotationConfiguration annotationConfiguration;
/*  309 */   private transient JVM jvm = new JVM();
/*      */   
/*      */   public static final int NO_REFERENCES = 1001;
/*      */   
/*      */   public static final int ID_REFERENCES = 1002;
/*      */   
/*      */   public static final int XPATH_RELATIVE_REFERENCES = 1003;
/*      */   
/*      */   public static final int XPATH_ABSOLUTE_REFERENCES = 1004;
/*      */   
/*      */   public static final int SINGLE_NODE_XPATH_RELATIVE_REFERENCES = 1005;
/*      */   
/*      */   public static final int SINGLE_NODE_XPATH_ABSOLUTE_REFERENCES = 1006;
/*      */   
/*      */   public static final int PRIORITY_VERY_HIGH = 10000;
/*      */   
/*      */   public static final int PRIORITY_NORMAL = 0;
/*      */   public static final int PRIORITY_LOW = -10;
/*      */   public static final int PRIORITY_VERY_LOW = -20;
/*      */   private static final String ANNOTATION_MAPPER_TYPE = "com.thoughtworks.xstream.mapper.AnnotationMapper";
/*      */   
/*      */   public XStream()
/*      */   {
/*  332 */     this(null, (Mapper)null, new XppDriver());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(ReflectionProvider reflectionProvider)
/*      */   {
/*  342 */     this(reflectionProvider, (Mapper)null, new XppDriver());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(HierarchicalStreamDriver hierarchicalStreamDriver)
/*      */   {
/*  352 */     this(null, (Mapper)null, hierarchicalStreamDriver);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(ReflectionProvider reflectionProvider, HierarchicalStreamDriver hierarchicalStreamDriver)
/*      */   {
/*  363 */     this(reflectionProvider, (Mapper)null, hierarchicalStreamDriver);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public XStream(ReflectionProvider reflectionProvider, Mapper mapper, HierarchicalStreamDriver driver)
/*      */   {
/*  377 */     this(reflectionProvider, driver, new ClassLoaderReference(new CompositeClassLoader()), mapper, new DefaultConverterLookup(), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(ReflectionProvider reflectionProvider, HierarchicalStreamDriver driver, ClassLoader classLoader)
/*      */   {
/*  392 */     this(reflectionProvider, driver, classLoader, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(ReflectionProvider reflectionProvider, HierarchicalStreamDriver driver, ClassLoader classLoader, Mapper mapper)
/*      */   {
/*  411 */     this(reflectionProvider, driver, classLoader, mapper, new DefaultConverterLookup(), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XStream(ReflectionProvider reflectionProvider, HierarchicalStreamDriver driver, ClassLoader classLoader, Mapper mapper, ConverterLookup converterLookup, ConverterRegistry converterRegistry)
/*      */   {
/*  432 */     this.jvm = new JVM();
/*  433 */     if (reflectionProvider == null) {
/*  434 */       reflectionProvider = this.jvm.bestReflectionProvider();
/*      */     }
/*  436 */     this.reflectionProvider = reflectionProvider;
/*  437 */     this.hierarchicalStreamDriver = driver;
/*  438 */     this.classLoaderReference = ((classLoader instanceof ClassLoaderReference) ? (ClassLoaderReference)classLoader : new ClassLoaderReference(classLoader));
/*      */     
/*      */ 
/*  441 */     this.converterLookup = converterLookup;
/*  442 */     this.converterRegistry = ((converterLookup instanceof ConverterRegistry) ? (ConverterRegistry)converterLookup : converterRegistry != null ? converterRegistry : null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  447 */     this.mapper = (mapper == null ? buildMapper() : mapper);
/*      */     
/*  449 */     setupMappers();
/*  450 */     setupAliases();
/*  451 */     setupDefaultImplementations();
/*  452 */     setupConverters();
/*  453 */     setupImmutableTypes();
/*  454 */     setMode(1003);
/*      */   }
/*      */   
/*      */   private Mapper buildMapper() {
/*  458 */     Mapper mapper = new DefaultMapper(this.classLoaderReference);
/*  459 */     if (useXStream11XmlFriendlyMapper()) {
/*  460 */       mapper = new XStream11XmlFriendlyMapper(mapper);
/*      */     }
/*  462 */     mapper = new DynamicProxyMapper(mapper);
/*  463 */     mapper = new PackageAliasingMapper(mapper);
/*  464 */     mapper = new ClassAliasingMapper(mapper);
/*  465 */     mapper = new FieldAliasingMapper(mapper);
/*  466 */     mapper = new AttributeAliasingMapper(mapper);
/*  467 */     mapper = new SystemAttributeAliasingMapper(mapper);
/*  468 */     mapper = new ImplicitCollectionMapper(mapper);
/*  469 */     mapper = new OuterClassMapper(mapper);
/*  470 */     mapper = new ArrayMapper(mapper);
/*  471 */     mapper = new DefaultImplementationsMapper(mapper);
/*  472 */     mapper = new AttributeMapper(mapper, this.converterLookup, this.reflectionProvider);
/*  473 */     if (JVM.is15()) {
/*  474 */       mapper = buildMapperDynamically("com.thoughtworks.xstream.mapper.EnumMapper", new Class[] { Mapper.class }, new Object[] { mapper });
/*      */     }
/*      */     
/*      */ 
/*  478 */     mapper = new LocalConversionMapper(mapper);
/*  479 */     mapper = new ImmutableTypesMapper(mapper);
/*  480 */     if (JVM.is15()) {
/*  481 */       mapper = buildMapperDynamically("com.thoughtworks.xstream.mapper.AnnotationMapper", new Class[] { Mapper.class, ConverterRegistry.class, ClassLoader.class, ReflectionProvider.class, JVM.class }, new Object[] { mapper, this.converterLookup, this.classLoaderReference, this.reflectionProvider, this.jvm });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  486 */     mapper = wrapMapper((MapperWrapper)mapper);
/*  487 */     mapper = new CachingMapper(mapper);
/*  488 */     return mapper;
/*      */   }
/*      */   
/*      */   private Mapper buildMapperDynamically(String className, Class[] constructorParamTypes, Object[] constructorParamValues)
/*      */   {
/*      */     try {
/*  494 */       Class type = Class.forName(className, false, this.classLoaderReference.getReference());
/*  495 */       Constructor constructor = type.getConstructor(constructorParamTypes);
/*  496 */       return (Mapper)constructor.newInstance(constructorParamValues);
/*      */     } catch (Exception e) {
/*  498 */       throw new InitializationException("Could not instantiate mapper : " + className, e);
/*      */     }
/*      */   }
/*      */   
/*      */   protected MapperWrapper wrapMapper(MapperWrapper next)
/*      */   {
/*  504 */     return next;
/*      */   }
/*      */   
/*      */   protected boolean useXStream11XmlFriendlyMapper() {
/*  508 */     return false;
/*      */   }
/*      */   
/*      */   private void setupMappers() {
/*  512 */     this.packageAliasingMapper = ((PackageAliasingMapper)this.mapper.lookupMapperOfType(PackageAliasingMapper.class));
/*      */     
/*  514 */     this.classAliasingMapper = ((ClassAliasingMapper)this.mapper.lookupMapperOfType(ClassAliasingMapper.class));
/*      */     
/*  516 */     this.fieldAliasingMapper = ((FieldAliasingMapper)this.mapper.lookupMapperOfType(FieldAliasingMapper.class));
/*      */     
/*  518 */     this.attributeMapper = ((AttributeMapper)this.mapper.lookupMapperOfType(AttributeMapper.class));
/*      */     
/*  520 */     this.attributeAliasingMapper = ((AttributeAliasingMapper)this.mapper.lookupMapperOfType(AttributeAliasingMapper.class));
/*      */     
/*  522 */     this.systemAttributeAliasingMapper = ((SystemAttributeAliasingMapper)this.mapper.lookupMapperOfType(SystemAttributeAliasingMapper.class));
/*      */     
/*  524 */     this.implicitCollectionMapper = ((ImplicitCollectionMapper)this.mapper.lookupMapperOfType(ImplicitCollectionMapper.class));
/*      */     
/*  526 */     this.defaultImplementationsMapper = ((DefaultImplementationsMapper)this.mapper.lookupMapperOfType(DefaultImplementationsMapper.class));
/*      */     
/*  528 */     this.immutableTypesMapper = ((ImmutableTypesMapper)this.mapper.lookupMapperOfType(ImmutableTypesMapper.class));
/*      */     
/*  530 */     this.localConversionMapper = ((LocalConversionMapper)this.mapper.lookupMapperOfType(LocalConversionMapper.class));
/*      */     
/*  532 */     this.annotationConfiguration = ((AnnotationConfiguration)this.mapper.lookupMapperOfType(AnnotationConfiguration.class));
/*      */   }
/*      */   
/*      */   protected void setupAliases()
/*      */   {
/*  537 */     if (this.classAliasingMapper == null) {
/*  538 */       return;
/*      */     }
/*      */     
/*  541 */     alias("null", Mapper.Null.class);
/*  542 */     alias("int", Integer.class);
/*  543 */     alias("float", Float.class);
/*  544 */     alias("double", Double.class);
/*  545 */     alias("long", Long.class);
/*  546 */     alias("short", Short.class);
/*  547 */     alias("char", Character.class);
/*  548 */     alias("byte", Byte.class);
/*  549 */     alias("boolean", Boolean.class);
/*  550 */     alias("number", Number.class);
/*  551 */     alias("object", Object.class);
/*  552 */     alias("big-int", BigInteger.class);
/*  553 */     alias("big-decimal", BigDecimal.class);
/*      */     
/*  555 */     alias("string-buffer", StringBuffer.class);
/*  556 */     alias("string", String.class);
/*  557 */     alias("java-class", Class.class);
/*  558 */     alias("method", Method.class);
/*  559 */     alias("constructor", Constructor.class);
/*  560 */     alias("field", Field.class);
/*  561 */     alias("date", Date.class);
/*  562 */     alias("uri", URI.class);
/*  563 */     alias("url", URL.class);
/*  564 */     alias("bit-set", BitSet.class);
/*      */     
/*  566 */     alias("map", Map.class);
/*  567 */     alias("entry", Map.Entry.class);
/*  568 */     alias("properties", Properties.class);
/*  569 */     alias("list", List.class);
/*  570 */     alias("set", Set.class);
/*      */     
/*  572 */     alias("linked-list", LinkedList.class);
/*  573 */     alias("vector", Vector.class);
/*  574 */     alias("tree-map", TreeMap.class);
/*  575 */     alias("tree-set", TreeSet.class);
/*  576 */     alias("hashtable", Hashtable.class);
/*      */     
/*  578 */     if (this.jvm.supportsAWT())
/*      */     {
/*      */ 
/*      */ 
/*  582 */       alias("awt-color", this.jvm.loadClass("java.awt.Color"));
/*  583 */       alias("awt-font", this.jvm.loadClass("java.awt.Font"));
/*  584 */       alias("awt-text-attribute", this.jvm.loadClass("java.awt.font.TextAttribute"));
/*      */     }
/*      */     
/*  587 */     if (this.jvm.supportsSQL()) {
/*  588 */       alias("sql-timestamp", this.jvm.loadClass("java.sql.Timestamp"));
/*  589 */       alias("sql-time", this.jvm.loadClass("java.sql.Time"));
/*  590 */       alias("sql-date", this.jvm.loadClass("java.sql.Date"));
/*      */     }
/*      */     
/*  593 */     alias("file", File.class);
/*  594 */     alias("locale", Locale.class);
/*  595 */     alias("gregorian-calendar", Calendar.class);
/*      */     
/*  597 */     if (JVM.is14()) {
/*  598 */       alias("auth-subject", this.jvm.loadClass("javax.security.auth.Subject"));
/*  599 */       alias("linked-hash-map", this.jvm.loadClass("java.util.LinkedHashMap"));
/*  600 */       alias("linked-hash-set", this.jvm.loadClass("java.util.LinkedHashSet"));
/*  601 */       alias("trace", this.jvm.loadClass("java.lang.StackTraceElement"));
/*  602 */       alias("currency", this.jvm.loadClass("java.util.Currency"));
/*  603 */       aliasType("charset", this.jvm.loadClass("java.nio.charset.Charset"));
/*      */     }
/*      */     
/*  606 */     if (JVM.is15()) {
/*  607 */       alias("duration", this.jvm.loadClass("javax.xml.datatype.Duration"));
/*  608 */       alias("enum-set", this.jvm.loadClass("java.util.EnumSet"));
/*  609 */       alias("enum-map", this.jvm.loadClass("java.util.EnumMap"));
/*  610 */       alias("string-builder", this.jvm.loadClass("java.lang.StringBuilder"));
/*  611 */       alias("uuid", this.jvm.loadClass("java.util.UUID"));
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setupDefaultImplementations() {
/*  616 */     if (this.defaultImplementationsMapper == null) {
/*  617 */       return;
/*      */     }
/*  619 */     addDefaultImplementation(HashMap.class, Map.class);
/*  620 */     addDefaultImplementation(ArrayList.class, List.class);
/*  621 */     addDefaultImplementation(HashSet.class, Set.class);
/*  622 */     addDefaultImplementation(GregorianCalendar.class, Calendar.class);
/*      */   }
/*      */   
/*      */   protected void setupConverters() {
/*  626 */     ReflectionConverter reflectionConverter = new ReflectionConverter(this.mapper, this.reflectionProvider);
/*      */     
/*  628 */     registerConverter(reflectionConverter, -20);
/*      */     
/*  630 */     registerConverter(new SerializableConverter(this.mapper, this.reflectionProvider, this.classLoaderReference), -10);
/*      */     
/*  632 */     registerConverter(new ExternalizableConverter(this.mapper, this.classLoaderReference), -10);
/*      */     
/*  634 */     registerConverter(new NullConverter(), 10000);
/*  635 */     registerConverter(new IntConverter(), 0);
/*  636 */     registerConverter(new FloatConverter(), 0);
/*  637 */     registerConverter(new DoubleConverter(), 0);
/*  638 */     registerConverter(new LongConverter(), 0);
/*  639 */     registerConverter(new ShortConverter(), 0);
/*  640 */     registerConverter(new CharConverter(), 0);
/*  641 */     registerConverter(new BooleanConverter(), 0);
/*  642 */     registerConverter(new ByteConverter(), 0);
/*      */     
/*  644 */     registerConverter(new StringConverter(), 0);
/*  645 */     registerConverter(new StringBufferConverter(), 0);
/*  646 */     registerConverter(new DateConverter(), 0);
/*  647 */     registerConverter(new BitSetConverter(), 0);
/*  648 */     registerConverter(new URIConverter(), 0);
/*  649 */     registerConverter(new URLConverter(), 0);
/*  650 */     registerConverter(new BigIntegerConverter(), 0);
/*  651 */     registerConverter(new BigDecimalConverter(), 0);
/*      */     
/*  653 */     registerConverter(new ArrayConverter(this.mapper), 0);
/*  654 */     registerConverter(new CharArrayConverter(), 0);
/*  655 */     registerConverter(new CollectionConverter(this.mapper), 0);
/*  656 */     registerConverter(new MapConverter(this.mapper), 0);
/*  657 */     registerConverter(new TreeMapConverter(this.mapper), 0);
/*  658 */     registerConverter(new TreeSetConverter(this.mapper), 0);
/*  659 */     registerConverter(new PropertiesConverter(), 0);
/*  660 */     registerConverter(new EncodedByteArrayConverter(), 0);
/*      */     
/*  662 */     registerConverter(new FileConverter(), 0);
/*  663 */     if (this.jvm.supportsSQL()) {
/*  664 */       registerConverter(new SqlTimestampConverter(), 0);
/*  665 */       registerConverter(new SqlTimeConverter(), 0);
/*  666 */       registerConverter(new SqlDateConverter(), 0);
/*      */     }
/*  668 */     registerConverter(new DynamicProxyConverter(this.mapper, this.classLoaderReference), 0);
/*      */     
/*  670 */     registerConverter(new JavaClassConverter(this.classLoaderReference), 0);
/*  671 */     registerConverter(new JavaMethodConverter(this.classLoaderReference), 0);
/*  672 */     registerConverter(new JavaFieldConverter(this.classLoaderReference), 0);
/*  673 */     if (this.jvm.supportsAWT()) {
/*  674 */       registerConverter(new FontConverter(), 0);
/*  675 */       registerConverter(new ColorConverter(), 0);
/*  676 */       registerConverter(new TextAttributeConverter(), 0);
/*      */     }
/*  678 */     if (this.jvm.supportsSwing()) {
/*  679 */       registerConverter(new LookAndFeelConverter(this.mapper, this.reflectionProvider), 0);
/*      */     }
/*      */     
/*  682 */     registerConverter(new LocaleConverter(), 0);
/*  683 */     registerConverter(new GregorianCalendarConverter(), 0);
/*      */     
/*  685 */     if (JVM.is14())
/*      */     {
/*  687 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.SubjectConverter", 0, new Class[] { Mapper.class }, new Object[] { this.mapper });
/*      */       
/*      */ 
/*  690 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.ThrowableConverter", 0, new Class[] { Converter.class }, new Object[] { reflectionConverter });
/*      */       
/*      */ 
/*      */ 
/*  694 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.StackTraceElementConverter", 0, null, null);
/*      */       
/*      */ 
/*  697 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.CurrencyConverter", 0, null, null);
/*      */       
/*      */ 
/*  700 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.RegexPatternConverter", 0, new Class[] { Converter.class }, new Object[] { reflectionConverter });
/*      */       
/*      */ 
/*      */ 
/*  704 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.CharsetConverter", 0, null, null);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  709 */     if (JVM.is15())
/*      */     {
/*      */       try {
/*  712 */         dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.DurationConverter", 0, null, null);
/*      */       }
/*      */       catch (InitializationException e) {}
/*      */       
/*      */ 
/*      */ 
/*  718 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumConverter", 0, null, null);
/*      */       
/*      */ 
/*  721 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumSetConverter", 0, new Class[] { Mapper.class }, new Object[] { this.mapper });
/*      */       
/*      */ 
/*  724 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumMapConverter", 0, new Class[] { Mapper.class }, new Object[] { this.mapper });
/*      */       
/*      */ 
/*  727 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.basic.StringBuilderConverter", 0, null, null);
/*      */       
/*      */ 
/*  730 */       dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.basic.UUIDConverter", 0, null, null);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  735 */     registerConverter(new SelfStreamingInstanceChecker(reflectionConverter, this), 0);
/*      */   }
/*      */   
/*      */   private void dynamicallyRegisterConverter(String className, int priority, Class[] constructorParamTypes, Object[] constructorParamValues)
/*      */   {
/*      */     try
/*      */     {
/*  742 */       Class type = Class.forName(className, false, this.classLoaderReference.getReference());
/*  743 */       Constructor constructor = type.getConstructor(constructorParamTypes);
/*  744 */       Object instance = constructor.newInstance(constructorParamValues);
/*  745 */       if ((instance instanceof Converter)) {
/*  746 */         registerConverter((Converter)instance, priority);
/*  747 */       } else if ((instance instanceof SingleValueConverter)) {
/*  748 */         registerConverter((SingleValueConverter)instance, priority);
/*      */       }
/*      */     } catch (Exception e) {
/*  751 */       throw new InitializationException("Could not instantiate converter : " + className, e);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setupImmutableTypes()
/*      */   {
/*  757 */     if (this.immutableTypesMapper == null) {
/*  758 */       return;
/*      */     }
/*      */     
/*      */ 
/*  762 */     addImmutableType(Boolean.TYPE);
/*  763 */     addImmutableType(Boolean.class);
/*  764 */     addImmutableType(Byte.TYPE);
/*  765 */     addImmutableType(Byte.class);
/*  766 */     addImmutableType(Character.TYPE);
/*  767 */     addImmutableType(Character.class);
/*  768 */     addImmutableType(Double.TYPE);
/*  769 */     addImmutableType(Double.class);
/*  770 */     addImmutableType(Float.TYPE);
/*  771 */     addImmutableType(Float.class);
/*  772 */     addImmutableType(Integer.TYPE);
/*  773 */     addImmutableType(Integer.class);
/*  774 */     addImmutableType(Long.TYPE);
/*  775 */     addImmutableType(Long.class);
/*  776 */     addImmutableType(Short.TYPE);
/*  777 */     addImmutableType(Short.class);
/*      */     
/*      */ 
/*  780 */     addImmutableType(Mapper.Null.class);
/*  781 */     addImmutableType(BigDecimal.class);
/*  782 */     addImmutableType(BigInteger.class);
/*  783 */     addImmutableType(String.class);
/*  784 */     addImmutableType(URI.class);
/*  785 */     addImmutableType(URL.class);
/*  786 */     addImmutableType(File.class);
/*  787 */     addImmutableType(Class.class);
/*      */     
/*  789 */     if (this.jvm.supportsAWT()) {
/*  790 */       addImmutableType(this.jvm.loadClass("java.awt.font.TextAttribute"));
/*      */     }
/*      */     
/*  793 */     if (JVM.is14())
/*      */     {
/*  795 */       addImmutableType(this.jvm.loadClass("java.nio.charset.Charset"));
/*  796 */       addImmutableType(this.jvm.loadClass("java.util.Currency"));
/*      */     }
/*      */   }
/*      */   
/*      */   public void setMarshallingStrategy(MarshallingStrategy marshallingStrategy) {
/*  801 */     this.marshallingStrategy = marshallingStrategy;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toXML(Object obj)
/*      */   {
/*  810 */     Writer writer = new StringWriter();
/*  811 */     toXML(obj, writer);
/*  812 */     return writer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void toXML(Object obj, Writer out)
/*      */   {
/*  822 */     HierarchicalStreamWriter writer = this.hierarchicalStreamDriver.createWriter(out);
/*      */     try {
/*  824 */       marshal(obj, writer);
/*      */     } finally {
/*  826 */       writer.flush();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void toXML(Object obj, OutputStream out)
/*      */   {
/*  837 */     HierarchicalStreamWriter writer = this.hierarchicalStreamDriver.createWriter(out);
/*      */     try {
/*  839 */       marshal(obj, writer);
/*      */     } finally {
/*  841 */       writer.flush();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void marshal(Object obj, HierarchicalStreamWriter writer)
/*      */   {
/*  851 */     marshal(obj, writer, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void marshal(Object obj, HierarchicalStreamWriter writer, DataHolder dataHolder)
/*      */   {
/*  862 */     this.marshallingStrategy.marshal(writer, obj, this.converterLookup, this.mapper, dataHolder);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(String xml)
/*      */   {
/*  871 */     return fromXML(new StringReader(xml));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(Reader xml)
/*      */   {
/*  880 */     return unmarshal(this.hierarchicalStreamDriver.createReader(xml), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(InputStream input)
/*      */   {
/*  889 */     return unmarshal(this.hierarchicalStreamDriver.createReader(input), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(String xml, Object root)
/*      */   {
/*  901 */     return fromXML(new StringReader(xml), root);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(Reader xml, Object root)
/*      */   {
/*  913 */     return unmarshal(this.hierarchicalStreamDriver.createReader(xml), root);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object fromXML(InputStream xml, Object root)
/*      */   {
/*  925 */     return unmarshal(this.hierarchicalStreamDriver.createReader(xml), root);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object unmarshal(HierarchicalStreamReader reader)
/*      */   {
/*  934 */     return unmarshal(reader, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object unmarshal(HierarchicalStreamReader reader, Object root)
/*      */   {
/*  946 */     return unmarshal(reader, root, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object unmarshal(HierarchicalStreamReader reader, Object root, DataHolder dataHolder)
/*      */   {
/*      */     try
/*      */     {
/*  962 */       return this.marshallingStrategy.unmarshal(root, reader, dataHolder, this.converterLookup, this.mapper);
/*      */     }
/*      */     catch (ConversionException e)
/*      */     {
/*  966 */       Package pkg = getClass().getPackage();
/*  967 */       e.add("version", pkg != null ? pkg.getImplementationVersion() : "not available");
/*  968 */       throw e;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void alias(String name, Class type)
/*      */   {
/*  980 */     if (this.classAliasingMapper == null) {
/*  981 */       throw new InitializationException("No " + ClassAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/*  985 */     this.classAliasingMapper.addClassAlias(name, type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasType(String name, Class type)
/*      */   {
/*  998 */     if (this.classAliasingMapper == null) {
/*  999 */       throw new InitializationException("No " + ClassAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1003 */     this.classAliasingMapper.addTypeAlias(name, type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void alias(String name, Class type, Class defaultImplementation)
/*      */   {
/* 1016 */     alias(name, type);
/* 1017 */     addDefaultImplementation(defaultImplementation, type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasPackage(String name, String pkgName)
/*      */   {
/* 1030 */     if (this.packageAliasingMapper == null) {
/* 1031 */       throw new InitializationException("No " + PackageAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1035 */     this.packageAliasingMapper.addPackageAlias(name, pkgName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasField(String alias, Class definedIn, String fieldName)
/*      */   {
/* 1047 */     if (this.fieldAliasingMapper == null) {
/* 1048 */       throw new InitializationException("No " + FieldAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1052 */     this.fieldAliasingMapper.addFieldAlias(alias, definedIn, fieldName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasAttribute(String alias, String attributeName)
/*      */   {
/* 1063 */     if (this.attributeAliasingMapper == null) {
/* 1064 */       throw new InitializationException("No " + AttributeAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1068 */     this.attributeAliasingMapper.addAliasFor(attributeName, alias);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasSystemAttribute(String alias, String systemAttributeName)
/*      */   {
/* 1083 */     if (this.systemAttributeAliasingMapper == null) {
/* 1084 */       throw new InitializationException("No " + SystemAttributeAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1088 */     this.systemAttributeAliasingMapper.addAliasFor(systemAttributeName, alias);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void aliasAttribute(Class definedIn, String attributeName, String alias)
/*      */   {
/* 1101 */     aliasField(alias, definedIn, attributeName);
/* 1102 */     useAttributeFor(definedIn, attributeName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void useAttributeFor(String fieldName, Class type)
/*      */   {
/* 1114 */     if (this.attributeMapper == null) {
/* 1115 */       throw new InitializationException("No " + AttributeMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1119 */     this.attributeMapper.addAttributeFor(fieldName, type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void useAttributeFor(Class definedIn, String fieldName)
/*      */   {
/* 1131 */     if (this.attributeMapper == null) {
/* 1132 */       throw new InitializationException("No " + AttributeMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1136 */     this.attributeMapper.addAttributeFor(definedIn, fieldName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void useAttributeFor(Class type)
/*      */   {
/* 1147 */     if (this.attributeMapper == null) {
/* 1148 */       throw new InitializationException("No " + AttributeMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1152 */     this.attributeMapper.addAttributeFor(type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDefaultImplementation(Class defaultImplementation, Class ofType)
/*      */   {
/* 1165 */     if (this.defaultImplementationsMapper == null) {
/* 1166 */       throw new InitializationException("No " + DefaultImplementationsMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1170 */     this.defaultImplementationsMapper.addDefaultImplementation(defaultImplementation, ofType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addImmutableType(Class type)
/*      */   {
/* 1180 */     if (this.immutableTypesMapper == null) {
/* 1181 */       throw new InitializationException("No " + ImmutableTypesMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1185 */     this.immutableTypesMapper.addImmutableType(type);
/*      */   }
/*      */   
/*      */   public void registerConverter(Converter converter) {
/* 1189 */     registerConverter(converter, 0);
/*      */   }
/*      */   
/*      */   public void registerConverter(Converter converter, int priority) {
/* 1193 */     if (this.converterRegistry != null) {
/* 1194 */       this.converterRegistry.registerConverter(converter, priority);
/*      */     }
/*      */   }
/*      */   
/*      */   public void registerConverter(SingleValueConverter converter) {
/* 1199 */     registerConverter(converter, 0);
/*      */   }
/*      */   
/*      */   public void registerConverter(SingleValueConverter converter, int priority) {
/* 1203 */     if (this.converterRegistry != null) {
/* 1204 */       this.converterRegistry.registerConverter(new SingleValueConverterWrapper(converter), priority);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerLocalConverter(Class definedIn, String fieldName, Converter converter)
/*      */   {
/* 1218 */     if (this.localConversionMapper == null) {
/* 1219 */       throw new InitializationException("No " + LocalConversionMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1223 */     this.localConversionMapper.registerLocalConverter(definedIn, fieldName, converter);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerLocalConverter(Class definedIn, String fieldName, SingleValueConverter converter)
/*      */   {
/* 1236 */     registerLocalConverter(definedIn, fieldName, new SingleValueConverterWrapper(converter));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Mapper getMapper()
/*      */   {
/* 1248 */     return this.mapper;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ReflectionProvider getReflectionProvider()
/*      */   {
/* 1258 */     return this.reflectionProvider;
/*      */   }
/*      */   
/*      */   public ConverterLookup getConverterLookup() {
/* 1262 */     return this.converterLookup;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMode(int mode)
/*      */   {
/* 1277 */     switch (mode) {
/*      */     case 1001: 
/* 1279 */       setMarshallingStrategy(new TreeMarshallingStrategy());
/* 1280 */       break;
/*      */     case 1002: 
/* 1282 */       setMarshallingStrategy(new ReferenceByIdMarshallingStrategy());
/* 1283 */       break;
/*      */     case 1003: 
/* 1285 */       setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.RELATIVE));
/*      */       
/* 1287 */       break;
/*      */     case 1004: 
/* 1289 */       setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.ABSOLUTE));
/*      */       
/* 1291 */       break;
/*      */     case 1005: 
/* 1293 */       setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.RELATIVE | ReferenceByXPathMarshallingStrategy.SINGLE_NODE));
/*      */       
/*      */ 
/* 1296 */       break;
/*      */     case 1006: 
/* 1298 */       setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.ABSOLUTE | ReferenceByXPathMarshallingStrategy.SINGLE_NODE));
/*      */       
/*      */ 
/* 1301 */       break;
/*      */     default: 
/* 1303 */       throw new IllegalArgumentException("Unknown mode : " + mode);
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addImplicitCollection(Class ownerType, String fieldName)
/*      */   {
/* 1316 */     if (this.implicitCollectionMapper == null) {
/* 1317 */       throw new InitializationException("No " + ImplicitCollectionMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1321 */     this.implicitCollectionMapper.add(ownerType, fieldName, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addImplicitCollection(Class ownerType, String fieldName, Class itemType)
/*      */   {
/* 1335 */     if (this.implicitCollectionMapper == null) {
/* 1336 */       throw new InitializationException("No " + ImplicitCollectionMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1340 */     this.implicitCollectionMapper.add(ownerType, fieldName, null, itemType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addImplicitCollection(Class ownerType, String fieldName, String itemFieldName, Class itemType)
/*      */   {
/* 1357 */     if (this.implicitCollectionMapper == null) {
/* 1358 */       throw new InitializationException("No " + ImplicitCollectionMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1362 */     this.implicitCollectionMapper.add(ownerType, fieldName, itemFieldName, itemType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DataHolder newDataHolder()
/*      */   {
/* 1373 */     return new MapBackedDataHolder();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(Writer writer)
/*      */     throws IOException
/*      */   {
/* 1390 */     return createObjectOutputStream(this.hierarchicalStreamDriver.createWriter(writer), "object-stream");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(HierarchicalStreamWriter writer)
/*      */     throws IOException
/*      */   {
/* 1409 */     return createObjectOutputStream(writer, "object-stream");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(Writer writer, String rootNodeName)
/*      */     throws IOException
/*      */   {
/* 1423 */     return createObjectOutputStream(this.hierarchicalStreamDriver.createWriter(writer), rootNodeName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(OutputStream out)
/*      */     throws IOException
/*      */   {
/* 1441 */     return createObjectOutputStream(this.hierarchicalStreamDriver.createWriter(out), "object-stream");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(OutputStream out, String rootNodeName)
/*      */     throws IOException
/*      */   {
/* 1456 */     return createObjectOutputStream(this.hierarchicalStreamDriver.createWriter(out), rootNodeName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOutputStream createObjectOutputStream(HierarchicalStreamWriter writer, String rootNodeName)
/*      */     throws IOException
/*      */   {
/* 1488 */     StatefulWriter statefulWriter = new StatefulWriter(writer);
/* 1489 */     statefulWriter.startNode(rootNodeName, null);
/* 1490 */     new CustomObjectOutputStream(new CustomObjectOutputStream.StreamCallback() { private final StatefulWriter val$statefulWriter;
/*      */       
/* 1492 */       public void writeToStream(Object object) { XStream.this.marshal(object, this.val$statefulWriter); }
/*      */       
/*      */       public void writeFieldsToStream(Map fields) throws NotActiveException
/*      */       {
/* 1496 */         throw new NotActiveException("not in call to writeObject");
/*      */       }
/*      */       
/*      */       public void defaultWriteObject() throws NotActiveException {
/* 1500 */         throw new NotActiveException("not in call to writeObject");
/*      */       }
/*      */       
/*      */       public void flush() {
/* 1504 */         this.val$statefulWriter.flush();
/*      */       }
/*      */       
/*      */       public void close() {
/* 1508 */         if (this.val$statefulWriter.state() != StatefulWriter.STATE_CLOSED) {
/* 1509 */           this.val$statefulWriter.endNode();
/* 1510 */           this.val$statefulWriter.close();
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectInputStream createObjectInputStream(Reader xmlReader)
/*      */     throws IOException
/*      */   {
/* 1526 */     return createObjectInputStream(this.hierarchicalStreamDriver.createReader(xmlReader));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectInputStream createObjectInputStream(InputStream in)
/*      */     throws IOException
/*      */   {
/* 1539 */     return createObjectInputStream(this.hierarchicalStreamDriver.createReader(in));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectInputStream createObjectInputStream(HierarchicalStreamReader reader)
/*      */     throws IOException
/*      */   {
/* 1559 */     new CustomObjectInputStream(new CustomObjectInputStream.StreamCallback() { private final HierarchicalStreamReader val$reader;
/*      */       
/* 1561 */       public Object readFromStream() throws EOFException { if (!this.val$reader.hasMoreChildren()) {
/* 1562 */           throw new EOFException();
/*      */         }
/* 1564 */         this.val$reader.moveDown();
/* 1565 */         Object result = XStream.this.unmarshal(this.val$reader);
/* 1566 */         this.val$reader.moveUp();
/* 1567 */         return result;
/*      */       }
/*      */       
/*      */       public Map readFieldsFromStream() throws IOException {
/* 1571 */         throw new NotActiveException("not in call to readObject");
/*      */       }
/*      */       
/*      */       public void defaultReadObject() throws NotActiveException {
/* 1575 */         throw new NotActiveException("not in call to readObject");
/*      */       }
/*      */       
/*      */       public void registerValidation(ObjectInputValidation validation, int priority) throws NotActiveException
/*      */       {
/* 1580 */         throw new NotActiveException("stream inactive");
/*      */       }
/*      */       
/*      */ 
/* 1584 */       public void close() { this.val$reader.close(); } }, this.classLoaderReference);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClassLoader(ClassLoader classLoader)
/*      */   {
/* 1599 */     this.classLoaderReference.setReference(classLoader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ClassLoader getClassLoader()
/*      */   {
/* 1608 */     return this.classLoaderReference.getReference();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void omitField(Class definedIn, String fieldName)
/*      */   {
/* 1619 */     if (this.fieldAliasingMapper == null) {
/* 1620 */       throw new InitializationException("No " + FieldAliasingMapper.class.getName() + " available");
/*      */     }
/*      */     
/*      */ 
/* 1624 */     this.fieldAliasingMapper.omitField(definedIn, fieldName);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void processAnnotations(Class[] types)
/*      */   {
/* 1634 */     if (this.annotationConfiguration == null) {
/* 1635 */       throw new InitializationException("No com.thoughtworks.xstream.mapper.AnnotationMapper available");
/*      */     }
/*      */     
/*      */ 
/* 1639 */     this.annotationConfiguration.processAnnotations(types);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void processAnnotations(Class type)
/*      */   {
/* 1650 */     processAnnotations(new Class[] { type });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void autodetectAnnotations(boolean mode)
/*      */   {
/* 1663 */     if (this.annotationConfiguration != null) {
/* 1664 */       this.annotationConfiguration.autodetectAnnotations(mode);
/*      */     }
/*      */   }
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static class InitializationException extends XStreamException {
/*      */     /**
/*      */      * @deprecated
/*      */      */
/*      */     public InitializationException(String message, Throwable cause) {
/* 1676 */       super(cause);
/*      */     }
/*      */     
/*      */     /**
/*      */      * @deprecated
/*      */      */
/*      */     public InitializationException(String message) {
/* 1683 */       super();
/*      */     }
/*      */   }
/*      */   
/*      */   private Object readResolve() {
/* 1688 */     this.jvm = new JVM();
/* 1689 */     return this;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/XStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */