/*      */ package org.apache.commons.collections15;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.text.ParseException;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import java.util.SortedMap;
/*      */ import java.util.TreeMap;
/*      */ import org.apache.commons.collections15.map.FixedSizeMap;
/*      */ import org.apache.commons.collections15.map.FixedSizeSortedMap;
/*      */ import org.apache.commons.collections15.map.LazyMap;
/*      */ import org.apache.commons.collections15.map.LazySortedMap;
/*      */ import org.apache.commons.collections15.map.ListOrderedMap;
/*      */ import org.apache.commons.collections15.map.PredicatedMap;
/*      */ import org.apache.commons.collections15.map.PredicatedSortedMap;
/*      */ import org.apache.commons.collections15.map.TransformedMap;
/*      */ import org.apache.commons.collections15.map.TransformedSortedMap;
/*      */ import org.apache.commons.collections15.map.TypedMap;
/*      */ import org.apache.commons.collections15.map.TypedSortedMap;
/*      */ import org.apache.commons.collections15.map.UnmodifiableMap;
/*      */ import org.apache.commons.collections15.map.UnmodifiableSortedMap;
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
/*      */ public class MapUtils
/*      */ {
/*   67 */   public static final Map EMPTY_MAP = UnmodifiableMap.decorate(new HashMap(1));
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   72 */   public static final SortedMap EMPTY_SORTED_MAP = UnmodifiableSortedMap.decorate(new TreeMap());
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final String INDENT_STRING = "    ";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <K, V> V getObject(Map<K, V> map, K key)
/*      */   {
/*   94 */     if (map != null) {
/*   95 */       return (V)map.get(key);
/*      */     }
/*   97 */     return null;
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
/*      */   public static <K, V> String getString(Map<K, V> map, K key)
/*      */   {
/*  110 */     if (map != null) {
/*  111 */       V answer = map.get(key);
/*  112 */       if (answer != null) {
/*  113 */         return answer.toString();
/*      */       }
/*      */     }
/*  116 */     return null;
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
/*      */   public static Boolean getBoolean(Map map, Object key)
/*      */   {
/*  134 */     if (map != null) {
/*  135 */       Object answer = map.get(key);
/*  136 */       if (answer != null) {
/*  137 */         if ((answer instanceof Boolean)) {
/*  138 */           return (Boolean)answer;
/*      */         }
/*  140 */         if ((answer instanceof String)) {
/*  141 */           return new Boolean((String)answer);
/*      */         }
/*  143 */         if ((answer instanceof Number)) {
/*  144 */           Number n = (Number)answer;
/*  145 */           return n.intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
/*      */         }
/*      */       }
/*      */     }
/*  149 */     return null;
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
/*      */   public static Number getNumber(Map map, Object key)
/*      */   {
/*  166 */     if (map != null) {
/*  167 */       Object answer = map.get(key);
/*  168 */       if (answer != null) {
/*  169 */         if ((answer instanceof Number)) {
/*  170 */           return (Number)answer;
/*      */         }
/*  172 */         if ((answer instanceof String)) {
/*      */           try {
/*  174 */             String text = (String)answer;
/*  175 */             return NumberFormat.getInstance().parse(text);
/*      */           }
/*      */           catch (ParseException e) {
/*  178 */             logInfo(e);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  183 */     return null;
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
/*      */   public static Byte getByte(Map map, Object key)
/*      */   {
/*  196 */     Number answer = getNumber(map, key);
/*  197 */     if (answer == null)
/*  198 */       return null;
/*  199 */     if ((answer instanceof Byte)) {
/*  200 */       return (Byte)answer;
/*      */     }
/*  202 */     return new Byte(answer.byteValue());
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
/*      */   public static Short getShort(Map map, Object key)
/*      */   {
/*  215 */     Number answer = getNumber(map, key);
/*  216 */     if (answer == null)
/*  217 */       return null;
/*  218 */     if ((answer instanceof Short)) {
/*  219 */       return (Short)answer;
/*      */     }
/*  221 */     return new Short(answer.shortValue());
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
/*      */   public static Integer getInteger(Map map, Object key)
/*      */   {
/*  234 */     Number answer = getNumber(map, key);
/*  235 */     if (answer == null)
/*  236 */       return null;
/*  237 */     if ((answer instanceof Integer)) {
/*  238 */       return (Integer)answer;
/*      */     }
/*  240 */     return new Integer(answer.intValue());
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
/*      */   public static Long getLong(Map map, Object key)
/*      */   {
/*  253 */     Number answer = getNumber(map, key);
/*  254 */     if (answer == null)
/*  255 */       return null;
/*  256 */     if ((answer instanceof Long)) {
/*  257 */       return (Long)answer;
/*      */     }
/*  259 */     return new Long(answer.longValue());
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
/*      */   public static Float getFloat(Map map, Object key)
/*      */   {
/*  272 */     Number answer = getNumber(map, key);
/*  273 */     if (answer == null)
/*  274 */       return null;
/*  275 */     if ((answer instanceof Float)) {
/*  276 */       return (Float)answer;
/*      */     }
/*  278 */     return new Float(answer.floatValue());
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
/*      */   public static Double getDouble(Map map, Object key)
/*      */   {
/*  291 */     Number answer = getNumber(map, key);
/*  292 */     if (answer == null)
/*  293 */       return null;
/*  294 */     if ((answer instanceof Double)) {
/*  295 */       return (Double)answer;
/*      */     }
/*  297 */     return new Double(answer.doubleValue());
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
/*      */   public static <K, A, B> Map<A, B> getMap(Map<K, Map<A, B>> map, K key)
/*      */   {
/*  311 */     if (map != null) {
/*  312 */       Map<A, B> answer = (Map)map.get(key);
/*  313 */       if (answer != null) {
/*  314 */         return answer;
/*      */       }
/*      */     }
/*  317 */     return null;
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
/*      */   public static <K, V> V getObject(Map<K, V> map, K key, V defaultValue)
/*      */   {
/*  333 */     if (map != null) {
/*  334 */       V answer = map.get(key);
/*  335 */       if (answer != null) {
/*  336 */         return answer;
/*      */       }
/*      */     }
/*  339 */     return defaultValue;
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
/*      */   public static String getString(Map map, Object key, String defaultValue)
/*      */   {
/*  355 */     String answer = getString(map, key);
/*  356 */     if (answer == null) {
/*  357 */       answer = defaultValue;
/*      */     }
/*  359 */     return answer;
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
/*      */   public static Boolean getBoolean(Map map, Object key, Boolean defaultValue)
/*      */   {
/*  375 */     Boolean answer = getBoolean(map, key);
/*  376 */     if (answer == null) {
/*  377 */       answer = defaultValue;
/*      */     }
/*  379 */     return answer;
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
/*      */   public static Number getNumber(Map map, Object key, Number defaultValue)
/*      */   {
/*  395 */     Number answer = getNumber(map, key);
/*  396 */     if (answer == null) {
/*  397 */       answer = defaultValue;
/*      */     }
/*  399 */     return answer;
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
/*      */   public static Byte getByte(Map map, Object key, Byte defaultValue)
/*      */   {
/*  415 */     Byte answer = getByte(map, key);
/*  416 */     if (answer == null) {
/*  417 */       answer = defaultValue;
/*      */     }
/*  419 */     return answer;
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
/*      */   public static Short getShort(Map map, Object key, Short defaultValue)
/*      */   {
/*  435 */     Short answer = getShort(map, key);
/*  436 */     if (answer == null) {
/*  437 */       answer = defaultValue;
/*      */     }
/*  439 */     return answer;
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
/*      */   public static Integer getInteger(Map map, Object key, Integer defaultValue)
/*      */   {
/*  455 */     Integer answer = getInteger(map, key);
/*  456 */     if (answer == null) {
/*  457 */       answer = defaultValue;
/*      */     }
/*  459 */     return answer;
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
/*      */   public static Long getLong(Map map, Object key, Long defaultValue)
/*      */   {
/*  475 */     Long answer = getLong(map, key);
/*  476 */     if (answer == null) {
/*  477 */       answer = defaultValue;
/*      */     }
/*  479 */     return answer;
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
/*      */   public static Float getFloat(Map map, Object key, Float defaultValue)
/*      */   {
/*  495 */     Float answer = getFloat(map, key);
/*  496 */     if (answer == null) {
/*  497 */       answer = defaultValue;
/*      */     }
/*  499 */     return answer;
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
/*      */   public static Double getDouble(Map map, Object key, Double defaultValue)
/*      */   {
/*  515 */     Double answer = getDouble(map, key);
/*  516 */     if (answer == null) {
/*  517 */       answer = defaultValue;
/*      */     }
/*  519 */     return answer;
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
/*      */   public static <K, A, B> Map<A, B> getMap(Map<K, Map<A, B>> map, K key, Map<A, B> defaultValue)
/*      */   {
/*  535 */     Map<A, B> answer = getMap(map, key);
/*  536 */     if (answer == null) {
/*  537 */       answer = defaultValue;
/*      */     }
/*  539 */     return answer;
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
/*      */   public static boolean getBooleanValue(Map map, Object key)
/*      */   {
/*  560 */     Boolean booleanObject = getBoolean(map, key);
/*  561 */     if (booleanObject == null) {
/*  562 */       return false;
/*      */     }
/*  564 */     return booleanObject.booleanValue();
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
/*      */   public static byte getByteValue(Map map, Object key)
/*      */   {
/*  577 */     Byte byteObject = getByte(map, key);
/*  578 */     if (byteObject == null) {
/*  579 */       return 0;
/*      */     }
/*  581 */     return byteObject.byteValue();
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
/*      */   public static short getShortValue(Map map, Object key)
/*      */   {
/*  594 */     Short shortObject = getShort(map, key);
/*  595 */     if (shortObject == null) {
/*  596 */       return 0;
/*      */     }
/*  598 */     return shortObject.shortValue();
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
/*      */   public static int getIntValue(Map map, Object key)
/*      */   {
/*  611 */     Integer integerObject = getInteger(map, key);
/*  612 */     if (integerObject == null) {
/*  613 */       return 0;
/*      */     }
/*  615 */     return integerObject.intValue();
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
/*      */   public static long getLongValue(Map map, Object key)
/*      */   {
/*  628 */     Long longObject = getLong(map, key);
/*  629 */     if (longObject == null) {
/*  630 */       return 0L;
/*      */     }
/*  632 */     return longObject.longValue();
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
/*      */   public static float getFloatValue(Map map, Object key)
/*      */   {
/*  645 */     Float floatObject = getFloat(map, key);
/*  646 */     if (floatObject == null) {
/*  647 */       return 0.0F;
/*      */     }
/*  649 */     return floatObject.floatValue();
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
/*      */   public static double getDoubleValue(Map map, Object key)
/*      */   {
/*  662 */     Double doubleObject = getDouble(map, key);
/*  663 */     if (doubleObject == null) {
/*  664 */       return 0.0D;
/*      */     }
/*  666 */     return doubleObject.doubleValue();
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
/*      */   public static boolean getBooleanValue(Map map, Object key, boolean defaultValue)
/*      */   {
/*  689 */     Boolean booleanObject = getBoolean(map, key);
/*  690 */     if (booleanObject == null) {
/*  691 */       return defaultValue;
/*      */     }
/*  693 */     return booleanObject.booleanValue();
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
/*      */   public static byte getByteValue(Map map, Object key, byte defaultValue)
/*      */   {
/*  709 */     Byte byteObject = getByte(map, key);
/*  710 */     if (byteObject == null) {
/*  711 */       return defaultValue;
/*      */     }
/*  713 */     return byteObject.byteValue();
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
/*      */   public static short getShortValue(Map map, Object key, short defaultValue)
/*      */   {
/*  729 */     Short shortObject = getShort(map, key);
/*  730 */     if (shortObject == null) {
/*  731 */       return defaultValue;
/*      */     }
/*  733 */     return shortObject.shortValue();
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
/*      */   public static int getIntValue(Map map, Object key, int defaultValue)
/*      */   {
/*  749 */     Integer integerObject = getInteger(map, key);
/*  750 */     if (integerObject == null) {
/*  751 */       return defaultValue;
/*      */     }
/*  753 */     return integerObject.intValue();
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
/*      */   public static long getLongValue(Map map, Object key, long defaultValue)
/*      */   {
/*  769 */     Long longObject = getLong(map, key);
/*  770 */     if (longObject == null) {
/*  771 */       return defaultValue;
/*      */     }
/*  773 */     return longObject.longValue();
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
/*      */   public static float getFloatValue(Map map, Object key, float defaultValue)
/*      */   {
/*  789 */     Float floatObject = getFloat(map, key);
/*  790 */     if (floatObject == null) {
/*  791 */       return defaultValue;
/*      */     }
/*  793 */     return floatObject.floatValue();
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
/*      */   public static double getDoubleValue(Map map, Object key, double defaultValue)
/*      */   {
/*  809 */     Double doubleObject = getDouble(map, key);
/*  810 */     if (doubleObject == null) {
/*  811 */       return defaultValue;
/*      */     }
/*  813 */     return doubleObject.doubleValue();
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
/*      */   public static Properties toProperties(Map map)
/*      */   {
/*  826 */     Properties answer = new Properties();
/*  827 */     Iterator iter; if (map != null) {
/*  828 */       for (iter = map.entrySet().iterator(); iter.hasNext();) {
/*  829 */         Map.Entry entry = (Map.Entry)iter.next();
/*  830 */         Object key = entry.getKey();
/*  831 */         Object value = entry.getValue();
/*  832 */         answer.put(key, value);
/*      */       }
/*      */     }
/*  835 */     return answer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Map<String, Object> toMap(ResourceBundle resourceBundle)
/*      */   {
/*  846 */     Enumeration<String> enumeration = resourceBundle.getKeys();
/*  847 */     Map<String, Object> map = new HashMap();
/*      */     
/*  849 */     while (enumeration.hasMoreElements()) {
/*  850 */       String key = (String)enumeration.nextElement();
/*  851 */       Object value = resourceBundle.getObject(key);
/*  852 */       map.put(key, value);
/*      */     }
/*      */     
/*  855 */     return map;
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
/*      */   public static void verbosePrint(PrintStream out, Object label, Map map)
/*      */   {
/*  880 */     verbosePrintInternal(out, label, map, new ArrayStack(), false);
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
/*      */   public static void debugPrint(PrintStream out, Object label, Map map)
/*      */   {
/*  903 */     verbosePrintInternal(out, label, map, new ArrayStack(), true);
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
/*      */   protected static void logInfo(Exception ex)
/*      */   {
/*  916 */     System.out.println("INFO: Exception: " + ex);
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
/*      */   private static void verbosePrintInternal(PrintStream out, Object label, Map map, ArrayStack lineage, boolean debug)
/*      */   {
/*  944 */     printIndent(out, lineage.size());
/*      */     
/*  946 */     if (map == null) {
/*  947 */       if (label != null) {
/*  948 */         out.print(label);
/*  949 */         out.print(" = ");
/*      */       }
/*  951 */       out.println("null");
/*  952 */       return;
/*      */     }
/*  954 */     if (label != null) {
/*  955 */       out.print(label);
/*  956 */       out.println(" = ");
/*      */     }
/*      */     
/*  959 */     printIndent(out, lineage.size());
/*  960 */     out.println("{");
/*      */     
/*  962 */     lineage.push(map);
/*      */     
/*  964 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/*  965 */       Map.Entry entry = (Map.Entry)it.next();
/*  966 */       Object childKey = entry.getKey();
/*  967 */       Object childValue = entry.getValue();
/*  968 */       if (((childValue instanceof Map)) && (!lineage.contains(childValue))) {
/*  969 */         verbosePrintInternal(out, childKey == null ? "null" : childKey, (Map)childValue, lineage, debug);
/*      */       } else {
/*  971 */         printIndent(out, lineage.size());
/*  972 */         out.print(childKey);
/*  973 */         out.print(" = ");
/*      */         
/*  975 */         int lineageIndex = lineage.indexOf(childValue);
/*  976 */         if (lineageIndex == -1) {
/*  977 */           out.print(childValue);
/*  978 */         } else if (lineage.size() - 1 == lineageIndex) {
/*  979 */           out.print("(this Map)");
/*      */         } else {
/*  981 */           out.print("(ancestor[" + (lineage.size() - 1 - lineageIndex - 1) + "] Map)");
/*      */         }
/*      */         
/*  984 */         if ((debug) && (childValue != null)) {
/*  985 */           out.print(' ');
/*  986 */           out.println(childValue.getClass().getName());
/*      */         } else {
/*  988 */           out.println();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  993 */     lineage.pop();
/*      */     
/*  995 */     printIndent(out, lineage.size());
/*  996 */     out.println(debug ? "} " + map.getClass().getName() : "}");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void printIndent(PrintStream out, int indent)
/*      */   {
/* 1005 */     for (int i = 0; i < indent; i++) {
/* 1006 */       out.print("    ");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <K, V> Map<V, K> invertMap(Map<K, V> map)
/*      */   {
/* 1026 */     Map<V, K> out = new HashMap(map.size());
/* 1027 */     for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext();) {
/* 1028 */       Map.Entry<K, V> entry = (Map.Entry)it.next();
/* 1029 */       out.put(entry.getValue(), entry.getKey());
/*      */     }
/* 1031 */     return out;
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
/*      */   public static void safeAddToMap(Map map, Object key, Object value)
/*      */     throws NullPointerException
/*      */   {
/* 1052 */     if (value == null) {
/* 1053 */       map.put(key, "");
/*      */     } else {
/* 1055 */       map.put(key, value);
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
/*      */   public static <K, V> Map<K, V> synchronizedMap(Map<K, V> map)
/*      */   {
/* 1085 */     return Collections.synchronizedMap(map);
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
/*      */   public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map)
/*      */   {
/* 1098 */     return UnmodifiableMap.decorate(map);
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
/*      */   public static <K, V> Map<K, V> predicatedMap(Map<K, V> map, Predicate<? super K> keyPred, Predicate<? super V> valuePred)
/*      */   {
/* 1117 */     return PredicatedMap.decorate(map, keyPred, valuePred);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static Map typedMap(Map map, Class keyType, Class valueType)
/*      */   {
/* 1133 */     return TypedMap.decorate(map, keyType, valueType);
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
/*      */   public static Map transformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer)
/*      */   {
/* 1150 */     return TransformedMap.decorate(map, keyTransformer, valueTransformer);
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
/*      */   public static <K, V> Map<K, V> fixedSizeMap(Map<K, V> map)
/*      */   {
/* 1164 */     return FixedSizeMap.decorate(map);
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
/*      */ 
/*      */   public static <K, V> Map<K, V> lazyMap(Map<K, V> map, Factory<V> factory)
/*      */   {
/* 1196 */     return LazyMap.decorate(map, factory);
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
/*      */   public static <K, V> Map<K, V> orderedMap(Map<K, V> map)
/*      */   {
/* 1211 */     return ListOrderedMap.decorate(map);
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
/*      */   public static <K, V> Map<K, V> synchronizedSortedMap(SortedMap<K, V> map)
/*      */   {
/* 1240 */     return Collections.synchronizedSortedMap(map);
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
/*      */   public static <K, V> Map<K, V> unmodifiableSortedMap(SortedMap<K, V> map)
/*      */   {
/* 1253 */     return UnmodifiableSortedMap.decorate(map);
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
/*      */   public static <K, V> SortedMap<K, V> predicatedSortedMap(SortedMap<K, V> map, Predicate<? super K> keyPred, Predicate<? super V> valuePred)
/*      */   {
/* 1272 */     return PredicatedSortedMap.decorate(map, keyPred, valuePred);
/*      */   }
/*      */   
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
/*      */   public static SortedMap typedSortedMap(SortedMap map, Class keyType, Class valueType)
/*      */   {
/* 1287 */     return TypedSortedMap.decorate(map, keyType, valueType);
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
/*      */   public static SortedMap transformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer)
/*      */   {
/* 1304 */     return TransformedSortedMap.decorate(map, keyTransformer, valueTransformer);
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
/*      */   public static <K, V> SortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> map)
/*      */   {
/* 1318 */     return FixedSizeSortedMap.decorate(map);
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
/*      */ 
/*      */ 
/*      */   public static <K, V> SortedMap<K, V> lazySortedMap(SortedMap<K, V> map, Factory<V> factory)
/*      */   {
/* 1351 */     return LazySortedMap.decorate(map, factory);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/MapUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */