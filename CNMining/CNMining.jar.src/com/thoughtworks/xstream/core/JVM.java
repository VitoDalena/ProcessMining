/*     */ package com.thoughtworks.xstream.core;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*     */ import com.thoughtworks.xstream.core.util.PresortedMap;
/*     */ import com.thoughtworks.xstream.core.util.PresortedSet;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.security.AccessControlException;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
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
/*     */ public class JVM
/*     */ {
/*     */   private ReflectionProvider reflectionProvider;
/*  34 */   private transient Map loaderCache = new HashMap();
/*     */   
/*  36 */   private final boolean supportsAWT = loadClass("java.awt.Color") != null;
/*  37 */   private final boolean supportsSwing = loadClass("javax.swing.LookAndFeel") != null;
/*  38 */   private final boolean supportsSQL = loadClass("java.sql.Date") != null;
/*     */   
/*     */   private static final boolean optimizedTreeSetAddAll;
/*     */   
/*     */   private static final boolean optimizedTreeMapPutAll;
/*  43 */   private static final String vendor = System.getProperty("java.vm.vendor");
/*  44 */   private static final float majorJavaVersion = getMajorJavaVersion();
/*  45 */   private static final boolean reverseFieldOrder = (isHarmony()) || ((isIBM()) && (!is15()));
/*     */   static final float DEFAULT_JAVA_VERSION = 1.3F;
/*     */   
/*     */   static
/*     */   {
/*  50 */     Comparator comparator = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/*  52 */         throw new RuntimeException();
/*     */       }
/*  54 */     };
/*  55 */     boolean optimized = true;
/*  56 */     SortedMap map = new PresortedMap(comparator);
/*  57 */     map.put("one", null);
/*  58 */     map.put("two", null);
/*     */     try {
/*  60 */       new TreeMap(comparator).putAll(map);
/*     */     } catch (RuntimeException e) {
/*  62 */       optimized = false;
/*     */     }
/*  64 */     optimizedTreeMapPutAll = optimized;
/*  65 */     SortedSet set = new PresortedSet(comparator);
/*  66 */     set.addAll(map.keySet());
/*     */     try {
/*  68 */       new TreeSet(comparator).addAll(set);
/*  69 */       optimized = true;
/*     */     } catch (RuntimeException e) {
/*  71 */       optimized = false;
/*     */     }
/*  73 */     optimizedTreeSetAddAll = optimized;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final float getMajorJavaVersion()
/*     */   {
/*     */     try
/*     */     {
/*  84 */       return isAndroid() ? 1.5F : Float.parseFloat(System.getProperty("java.specification.version"));
/*     */     }
/*     */     catch (NumberFormatException e) {}
/*  87 */     return 1.3F;
/*     */   }
/*     */   
/*     */   public static boolean is14()
/*     */   {
/*  92 */     return majorJavaVersion >= 1.4F;
/*     */   }
/*     */   
/*     */   public static boolean is15() {
/*  96 */     return majorJavaVersion >= 1.5F;
/*     */   }
/*     */   
/*     */   public static boolean is16() {
/* 100 */     return majorJavaVersion >= 1.6F;
/*     */   }
/*     */   
/*     */   private static boolean isSun() {
/* 104 */     return vendor.indexOf("Sun") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isOracle() {
/* 108 */     return vendor.indexOf("Oracle") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isApple() {
/* 112 */     return vendor.indexOf("Apple") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isHPUX() {
/* 116 */     return vendor.indexOf("Hewlett-Packard Company") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isIBM() {
/* 120 */     return vendor.indexOf("IBM") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isBlackdown() {
/* 124 */     return vendor.indexOf("Blackdown") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isDiablo() {
/* 128 */     return vendor.indexOf("FreeBSD Foundation") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isHarmony() {
/* 132 */     return vendor.indexOf("Apache Software Foundation") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isAndroid() {
/* 136 */     return vendor.indexOf("Android") != -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isBEAWithUnsafeSupport()
/*     */   {
/* 146 */     if (vendor.indexOf("BEA") != -1)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 153 */       String vmVersion = System.getProperty("java.vm.version");
/* 154 */       if (vmVersion.startsWith("R"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 159 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 167 */       String vmInfo = System.getProperty("java.vm.info");
/* 168 */       if (vmInfo != null)
/*     */       {
/* 170 */         return (vmInfo.startsWith("R25.1")) || (vmInfo.startsWith("R25.2"));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isHitachi() {
/* 179 */     return vendor.indexOf("Hitachi") != -1;
/*     */   }
/*     */   
/*     */   private static boolean isSAP() {
/* 183 */     return vendor.indexOf("SAP AG") != -1;
/*     */   }
/*     */   
/*     */   public Class loadClass(String name) {
/*     */     try {
/* 188 */       WeakReference reference = (WeakReference)this.loaderCache.get(name);
/* 189 */       if (reference != null) {
/* 190 */         Class cached = (Class)reference.get();
/* 191 */         if (cached != null) {
/* 192 */           return cached;
/*     */         }
/*     */       }
/*     */       
/* 196 */       Class clazz = Class.forName(name, false, getClass().getClassLoader());
/* 197 */       this.loaderCache.put(name, new WeakReference(clazz));
/* 198 */       return clazz;
/*     */     } catch (ClassNotFoundException e) {}
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized ReflectionProvider bestReflectionProvider()
/*     */   {
/* 205 */     if (this.reflectionProvider == null) {
/*     */       try {
/* 207 */         if (canUseSun14ReflectionProvider()) {
/* 208 */           String cls = "com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider";
/* 209 */           this.reflectionProvider = ((ReflectionProvider)loadClass(cls).newInstance());
/* 210 */         } else if (canUseHarmonyReflectionProvider()) {
/* 211 */           String cls = "com.thoughtworks.xstream.converters.reflection.HarmonyReflectionProvider";
/* 212 */           this.reflectionProvider = ((ReflectionProvider)loadClass(cls).newInstance());
/*     */         }
/* 214 */         if (this.reflectionProvider == null) {
/* 215 */           this.reflectionProvider = new PureJavaReflectionProvider();
/*     */         }
/*     */       } catch (InstantiationException e) {
/* 218 */         this.reflectionProvider = new PureJavaReflectionProvider();
/*     */       } catch (IllegalAccessException e) {
/* 220 */         this.reflectionProvider = new PureJavaReflectionProvider();
/*     */       }
/*     */       catch (AccessControlException e) {
/* 223 */         this.reflectionProvider = new PureJavaReflectionProvider();
/*     */       }
/*     */     }
/* 226 */     return this.reflectionProvider;
/*     */   }
/*     */   
/*     */   private boolean canUseSun14ReflectionProvider() {
/* 230 */     return ((isSun()) || (isOracle()) || (isApple()) || (isHPUX()) || (isIBM()) || (isBlackdown()) || (isBEAWithUnsafeSupport()) || (isHitachi()) || (isSAP()) || (isDiablo())) && (is14()) && (loadClass("sun.misc.Unsafe") != null);
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
/*     */   private boolean canUseHarmonyReflectionProvider()
/*     */   {
/* 245 */     return isHarmony();
/*     */   }
/*     */   
/*     */   public static boolean reverseFieldDefinition() {
/* 249 */     return reverseFieldOrder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean supportsAWT()
/*     */   {
/* 256 */     return this.supportsAWT;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean supportsSwing()
/*     */   {
/* 263 */     return this.supportsSwing;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean supportsSQL()
/*     */   {
/* 270 */     return this.supportsSQL;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean hasOptimizedTreeSetAddAll()
/*     */   {
/* 279 */     return optimizedTreeSetAddAll;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean hasOptimizedTreeMapPutAll()
/*     */   {
/* 288 */     return optimizedTreeMapPutAll;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 292 */     this.loaderCache = new HashMap();
/* 293 */     return this;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 297 */     boolean reverse = false;
/* 298 */     Field[] fields = AttributedString.class.getDeclaredFields();
/* 299 */     for (int i = 0; i < fields.length; i++) {
/* 300 */       if (fields[i].getName().equals("text")) {
/* 301 */         reverse = i > 3;
/* 302 */         break;
/*     */       }
/*     */     }
/* 305 */     if (reverse) {
/* 306 */       fields = JVM.class.getDeclaredFields();
/* 307 */       for (int i = 0; i < fields.length; i++) {
/* 308 */         if (fields[i].getName().equals("reflectionProvider")) {
/* 309 */           reverse = i > 2;
/* 310 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 315 */     JVM jvm = new JVM();
/* 316 */     System.out.println("XStream JVM diagnostics");
/* 317 */     System.out.println("java.specification.version: " + System.getProperty("java.specification.version"));
/* 318 */     System.out.println("java.vm.vendor: " + vendor);
/* 319 */     System.out.println("Version: " + majorJavaVersion);
/* 320 */     System.out.println("XStream support for enhanced Mode: " + ((jvm.canUseSun14ReflectionProvider()) || (jvm.canUseHarmonyReflectionProvider())));
/* 321 */     System.out.println("Supports AWT: " + jvm.supportsAWT());
/* 322 */     System.out.println("Supports Swing: " + jvm.supportsSwing());
/* 323 */     System.out.println("Supports SQL: " + jvm.supportsSQL());
/* 324 */     System.out.println("Optimized TreeSet.addAll: " + hasOptimizedTreeSetAddAll());
/* 325 */     System.out.println("Optimized TreeMap.putAll: " + hasOptimizedTreeMapPutAll());
/* 326 */     System.out.println("Reverse field order detected (may have failed): " + reverse);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/JVM.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */