/*      */ package flanagan.math;
/*      */ 
/*      */ import flanagan.circuits.Phasor;
/*      */ import flanagan.complex.Complex;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Conv
/*      */ {
/*   49 */   private static int type = -1;
/*      */   
/*   51 */   private static String[] typeName = { "double", "Double", "long", "Long", "float", "Float", "int", "Integer", "short", "Short", "byte", "Byte", "BigDecimal", "BigInteger", "Complex", "Phasor", "char", "Character", "String" };
/*      */   
/*   53 */   private static double max_float_as_double = 3.4028234663852886E38D;
/*   54 */   private static double max_long_as_double = 9.223372036854776E18D;
/*   55 */   private static double max_long_as_float = 9.223372036854776E18D;
/*   56 */   private static double max_int_as_double = 2.147483647E9D;
/*   57 */   private static double max_int_as_float = 2.147483648E9D;
/*   58 */   private static double max_int_as_long = 2.147483647E9D;
/*   59 */   private static double max_short_as_double = 32767.0D;
/*   60 */   private static double max_short_as_long = 32767.0D;
/*   61 */   private static double max_short_as_float = 32767.0D;
/*   62 */   private static double max_short_as_int = 32767.0D;
/*   63 */   private static double max_byte_as_double = 127.0D;
/*   64 */   private static double max_byte_as_float = 127.0D;
/*   65 */   private static double max_byte_as_long = 127.0D;
/*   66 */   private static double max_byte_as_int = 127.0D;
/*   67 */   private static double max_byte_as_short = 127.0D;
/*      */   
/*   69 */   private static boolean suppressMessage = false;
/*   70 */   private static boolean suppressMessageAM = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void suppressMessages()
/*      */   {
/*   79 */     suppressMessage = true;
/*      */   }
/*      */   
/*      */   public static void restoreMessages()
/*      */   {
/*   84 */     if (!suppressMessageAM) suppressMessage = false;
/*      */   }
/*      */   
/*      */   public static void suppressMessagesAM()
/*      */   {
/*   89 */     suppressMessageAM = true;
/*      */   }
/*      */   
/*      */   public static void restoreMessagesAM()
/*      */   {
/*   94 */     suppressMessageAM = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public static float convert_double_to_float(double x)
/*      */   {
/*  100 */     if (x > max_float_as_double) throw new IllegalArgumentException("double is too large to be recast as float");
/*  101 */     if (!suppressMessage) System.out.println("Class Conv: method convert_double_to_float: possible loss of precision");
/*  102 */     return new Double(x).floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_double_to_Float(double x) {
/*  106 */     if (x > max_float_as_double) throw new IllegalArgumentException("double is too large to be recast as float");
/*  107 */     if (!suppressMessage) System.out.println("Class Conv: method convert_double_to_Float: possible loss of precision");
/*  108 */     return new Float(new Double(x).floatValue());
/*      */   }
/*      */   
/*      */   public static float convert_Double_to_float(Double xx) {
/*  112 */     double x = xx.doubleValue();
/*  113 */     if (x > max_float_as_double) throw new IllegalArgumentException("Double is too large to be recast as float");
/*  114 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Double_to_float: possible loss of precision");
/*  115 */     return xx.floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_Double_to_Float(Double xx) {
/*  119 */     double x = xx.doubleValue();
/*  120 */     if (x > max_float_as_double) throw new IllegalArgumentException("Double is too large to be recast as Float");
/*  121 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Double_to_Float: possible loss of precision");
/*  122 */     return new Float(x);
/*      */   }
/*      */   
/*      */   public static long convert_double_to_long(double x) {
/*  126 */     if (x > max_long_as_double) throw new IllegalArgumentException("double is too large to be recast as long");
/*  127 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  128 */     return new Double(x).longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_double_to_Long(double x) {
/*  132 */     if (x > max_long_as_double) throw new IllegalArgumentException("double is too large to be recast as long");
/*  133 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  134 */     return new Long(new Double(x).longValue());
/*      */   }
/*      */   
/*      */   public static long convert_Double_to_long(Double xx) {
/*  138 */     double x = xx.doubleValue();
/*  139 */     if (x > max_long_as_double) throw new IllegalArgumentException("Double is too large to be recast as long");
/*  140 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  141 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_Double_to_Long(Double xx) {
/*  145 */     double x = xx.doubleValue();
/*  146 */     if (x > max_long_as_double) throw new IllegalArgumentException("Double is too large to be recast as Long");
/*  147 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  148 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_double_to_int(double x) {
/*  152 */     if (x > max_int_as_double) throw new IllegalArgumentException("double is too large to be recast as int");
/*  153 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  154 */     return new Double(x).intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_double_to_Integer(double x) {
/*  158 */     if (x > max_int_as_double) throw new IllegalArgumentException("double is too large to be recast as int");
/*  159 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  160 */     return new Integer(new Double(x).intValue());
/*      */   }
/*      */   
/*      */   public static int convert_Double_to_int(Double xx) {
/*  164 */     double x = xx.doubleValue();
/*  165 */     if (x > max_int_as_double) throw new IllegalArgumentException("Double is too large to be recast as int");
/*  166 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  167 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_Double_to_Integer(Double xx) {
/*  171 */     double x = xx.doubleValue();
/*  172 */     if (x > max_int_as_double) throw new IllegalArgumentException("Double is too large to be recast as Integer");
/*  173 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  174 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_double_to_short(double x) {
/*  178 */     if (x > max_short_as_double) throw new IllegalArgumentException("double is too large to be recast as short");
/*  179 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  180 */     return new Double(x).shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_double_to_Short(double x) {
/*  184 */     if (x > max_short_as_double) throw new IllegalArgumentException("double is too large to be recast as short");
/*  185 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  186 */     return new Short(new Double(x).shortValue());
/*      */   }
/*      */   
/*      */   public static short convert_Double_to_short(Double xx) {
/*  190 */     double x = xx.doubleValue();
/*  191 */     if (x > max_short_as_double) throw new IllegalArgumentException("Double is too large to be recast as short");
/*  192 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  193 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_Double_to_Short(Double xx) {
/*  197 */     double x = xx.doubleValue();
/*  198 */     if (x > max_short_as_double) throw new IllegalArgumentException("Double is too large to be recast as Short");
/*  199 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  200 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_double_to_byte(double x) {
/*  204 */     if (x > max_byte_as_double) throw new IllegalArgumentException("double is too large to be recast as byte");
/*  205 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  206 */     return new Double(x).byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_double_to_Byte(double x) {
/*  210 */     if (x > max_byte_as_double) throw new IllegalArgumentException("double is too large to be recast as byte");
/*  211 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  212 */     return new Byte(new Double(x).byteValue());
/*      */   }
/*      */   
/*      */   public static byte convert_Double_to_byte(Double xx) {
/*  216 */     double x = xx.doubleValue();
/*  217 */     if (x > max_byte_as_double) throw new IllegalArgumentException("Double is too large to be recast as byte");
/*  218 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  219 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_Double_to_Byte(Double xx) {
/*  223 */     double x = xx.doubleValue();
/*  224 */     if (x > max_byte_as_double) throw new IllegalArgumentException("Double is too large to be recast as Byte");
/*  225 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Double is not, arithmetically, an integer");
/*  226 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_double_to_BigDecimal(double x) {
/*  230 */     return new BigDecimal(x);
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Double_to_BigDecimal(Double xx) {
/*  234 */     return new BigDecimal(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_double_to_BigInteger(double x) {
/*  238 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  239 */     return new BigInteger(Double.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Double_to_BigInteger(Double xx) {
/*  243 */     double x = xx.doubleValue();
/*  244 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  245 */     return new BigInteger(Double.toString(x));
/*      */   }
/*      */   
/*      */   public static double convert_float_to_double(float x)
/*      */   {
/*  250 */     return new Float(x).doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_float_to_Double(float x) {
/*  254 */     return new Double(new Float(x).doubleValue());
/*      */   }
/*      */   
/*      */   public static double convert_Float_to_double(Float xx) {
/*  258 */     return xx.doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_Float_to_Double(Float xx) {
/*  262 */     return new Double(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static long convert_float_to_long(float x) {
/*  266 */     if (x > max_long_as_float) throw new IllegalArgumentException("float is too large to be recast as long");
/*  267 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  268 */     return new Float(x).longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_float_to_Long(float x) {
/*  272 */     if (x > max_long_as_float) throw new IllegalArgumentException("float is too large to be recast as long");
/*  273 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  274 */     return new Long(new Float(x).longValue());
/*      */   }
/*      */   
/*      */   public static long convert_Float_to_long(Float xx) {
/*  278 */     float x = xx.floatValue();
/*  279 */     if (x > max_long_as_float) throw new IllegalArgumentException("Float is too large to be recast as long");
/*  280 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  281 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_Float_to_Long(Float xx) {
/*  285 */     float x = xx.floatValue();
/*  286 */     if (x > max_long_as_float) throw new IllegalArgumentException("Float is too large to be recast as Long");
/*  287 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  288 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_float_to_int(float x) {
/*  292 */     if (x > max_int_as_float) throw new IllegalArgumentException("double is too large to be recast as int");
/*  293 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("double is not, arithmetically, an integer");
/*  294 */     return new Float(x).intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_float_to_Integer(float x) {
/*  298 */     if (x > max_int_as_float) throw new IllegalArgumentException("float is too large to be recast as int");
/*  299 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  300 */     return new Integer(new Float(x).intValue());
/*      */   }
/*      */   
/*      */   public static int convert_Float_to_int(Float xx) {
/*  304 */     float x = xx.floatValue();
/*  305 */     if (x > max_int_as_float) throw new IllegalArgumentException("Float is too large to be recast as int");
/*  306 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  307 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_Float_to_Integer(Float xx) {
/*  311 */     float x = xx.floatValue();
/*  312 */     if (x > max_int_as_float) throw new IllegalArgumentException("Float is too large to be recast as Integer");
/*  313 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  314 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_float_to_short(float x) {
/*  318 */     if (x > max_short_as_float) throw new IllegalArgumentException("float is too large to be recast as short");
/*  319 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  320 */     return new Float(x).shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_float_to_Short(float x) {
/*  324 */     if (x > max_short_as_float) throw new IllegalArgumentException("float is too large to be recast as short");
/*  325 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  326 */     return new Short(new Float(x).shortValue());
/*      */   }
/*      */   
/*      */   public static short convert_Float_to_short(Float xx) {
/*  330 */     float x = xx.floatValue();
/*  331 */     if (x > max_short_as_float) throw new IllegalArgumentException("Float is too large to be recast as short");
/*  332 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  333 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_Float_to_Short(Float xx) {
/*  337 */     float x = xx.floatValue();
/*  338 */     if (x > max_short_as_float) throw new IllegalArgumentException("Float is too large to be recast as Short");
/*  339 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  340 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_float_to_byte(float x) {
/*  344 */     if (x > max_byte_as_float) throw new IllegalArgumentException("float is too large to be recast as byte");
/*  345 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  346 */     return new Float(x).byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_float_to_Byte(float x) {
/*  350 */     if (x > max_byte_as_float) throw new IllegalArgumentException("float is too large to be recast as byte");
/*  351 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  352 */     return new Byte(new Float(x).byteValue());
/*      */   }
/*      */   
/*      */   public static byte convert_Float_to_byte(Float xx) {
/*  356 */     float x = xx.floatValue();
/*  357 */     if (x > max_byte_as_float) throw new IllegalArgumentException("Float is too large to be recast as byte");
/*  358 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  359 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_Float_to_Byte(Float xx) {
/*  363 */     float x = xx.floatValue();
/*  364 */     if (x > max_byte_as_float) throw new IllegalArgumentException("Float is too large to be recast as Byte");
/*  365 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  366 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_float_to_BigDecimal(float x) {
/*  370 */     return new BigDecimal(x);
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Float_to_BigDecimal(Float xx) {
/*  374 */     return new BigDecimal(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_double_to_BigInteger(float x) {
/*  378 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("float is not, arithmetically, an integer");
/*  379 */     return new BigInteger(Float.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Float_to_BigInteger(Float xx) {
/*  383 */     double x = xx.doubleValue();
/*  384 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("Float is not, arithmetically, an integer");
/*  385 */     return new BigInteger(Double.toString(x));
/*      */   }
/*      */   
/*      */ 
/*      */   public static double convert_long_to_double(long x)
/*      */   {
/*  391 */     if (!suppressMessage) System.out.println("Class Conv: method convert_long_to_double: possible loss of precision");
/*  392 */     return new Long(x).doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_long_to_Double(long x) {
/*  396 */     if (!suppressMessage) System.out.println("Class Conv: method convert_long_to_Double: possible loss of precision");
/*  397 */     return new Double(new Long(x).doubleValue());
/*      */   }
/*      */   
/*      */   public static double convert_Long_to_double(Long xx) {
/*  401 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Long_to_double: possible loss of precision");
/*  402 */     return xx.doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_Long_to_Double(Long xx) {
/*  406 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Long_to_Double: possible loss of precision");
/*  407 */     return new Double(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static float convert_long_to_float(long x) {
/*  411 */     if (!suppressMessage) System.out.println("Class Conv: method convert_long_to_float: possible loss of precision");
/*  412 */     return new Long(x).floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_long_to_Float(long x) {
/*  416 */     if (!suppressMessage) System.out.println("Class Conv: method convert_long_to_Float: possible loss of precision");
/*  417 */     return new Float(new Long(x).floatValue());
/*      */   }
/*      */   
/*      */   public static float convert_Long_to_float(Long xx) {
/*  421 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Long_to_float: possible loss of precision");
/*  422 */     return xx.floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_Long_to_Float(Long xx) {
/*  426 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Long_to_Float: possible loss of precision");
/*  427 */     return new Float(xx.floatValue());
/*      */   }
/*      */   
/*      */   public static int convert_long_to_int(long x) {
/*  431 */     if (x > max_int_as_long) throw new IllegalArgumentException("long is too large to be recast as int");
/*  432 */     return new Float((float)x).intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_long_to_Integer(long x) {
/*  436 */     if (x > max_int_as_long) throw new IllegalArgumentException("long is too large to be recast as Integer");
/*  437 */     return new Integer(new Long(x).intValue());
/*      */   }
/*      */   
/*      */   public static int convert_Long_to_int(Long xx) {
/*  441 */     long x = xx.longValue();
/*  442 */     if (x > max_int_as_long) throw new IllegalArgumentException("Long is too large to be recast as int");
/*  443 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_Long_to_Integer(Long xx) {
/*  447 */     long x = xx.longValue();
/*  448 */     if (x > max_int_as_long) throw new IllegalArgumentException("Long is too large to be recast as Integer");
/*  449 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_long_to_short(long x) {
/*  453 */     if (x > max_short_as_long) throw new IllegalArgumentException("long is too large to be recast as short");
/*  454 */     return new Long(x).shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_long_to_Short(long x) {
/*  458 */     if (x > max_short_as_long) throw new IllegalArgumentException("long is too large to be recast as Short");
/*  459 */     return new Short(new Long(x).shortValue());
/*      */   }
/*      */   
/*      */   public static short convert_Long_to_short(Long xx) {
/*  463 */     long x = xx.longValue();
/*  464 */     if (x > max_short_as_long) throw new IllegalArgumentException("Long is too large to be recast as short");
/*  465 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_Long_to_Short(Long xx) {
/*  469 */     long x = xx.longValue();
/*  470 */     if (x > max_short_as_long) throw new IllegalArgumentException("Long is too large to be recast as Short");
/*  471 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_long_to_byte(long x) {
/*  475 */     if (x > max_byte_as_long) throw new IllegalArgumentException("long is too large to be recast as byte");
/*  476 */     return new Long(x).byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_long_to_Byte(long x) {
/*  480 */     if (x > max_byte_as_long) throw new IllegalArgumentException("long is too large to be recast as Byte");
/*  481 */     return new Byte(new Long(x).byteValue());
/*      */   }
/*      */   
/*      */   public static byte convert_Long_to_byte(Long xx) {
/*  485 */     long x = xx.longValue();
/*  486 */     if (x > max_byte_as_long) throw new IllegalArgumentException("Long is too large to be recast as byte");
/*  487 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_Long_to_Byte(Long xx) {
/*  491 */     long x = xx.longValue();
/*  492 */     if (x > max_byte_as_long) throw new IllegalArgumentException("Long is too large to be recast as Byte");
/*  493 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_long_to_BigDecimal(long x) {
/*  497 */     return new BigDecimal(new Long(x).toString());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Long_to_BigDecimal(Long xx) {
/*  501 */     return new BigDecimal(xx.toString());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_long_to_BigInteger(long x) {
/*  505 */     return new BigInteger(Long.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Long_to_BigInteger(Long xx) {
/*  509 */     double x = xx.doubleValue();
/*  510 */     return new BigInteger(xx.toString());
/*      */   }
/*      */   
/*      */   public static double convert_int_to_double(int x)
/*      */   {
/*  515 */     return new Integer(x).doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_int_to_Double(int x) {
/*  519 */     return new Double(new Integer(x).doubleValue());
/*      */   }
/*      */   
/*      */   public static double convert_Integer_to_double(Integer xx) {
/*  523 */     return xx.doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_Integer_to_Double(Integer xx) {
/*  527 */     return new Double(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static float convert_int_to_float(int x) {
/*  531 */     if (!suppressMessage) System.out.println("Class Conv: method convert_int_to_float: possible loss of precision");
/*  532 */     return new Integer(x).floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_int_to_Float(int x) {
/*  536 */     if (!suppressMessage) System.out.println("Class Conv: method convert_int_to_Float: possible loss of precision");
/*  537 */     return new Float(new Integer(x).floatValue());
/*      */   }
/*      */   
/*      */   public static float convert_Integer_to_float(Integer xx) {
/*  541 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Integer_to_float: possible loss of precision");
/*  542 */     return xx.floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_Integer_to_Float(Integer xx) {
/*  546 */     if (!suppressMessage) System.out.println("Class Conv: method convert_Integer_to_Float: possible loss of precision");
/*  547 */     return new Float(xx.floatValue());
/*      */   }
/*      */   
/*      */   public static long convert_int_to_long(int x) {
/*  551 */     return new Integer(x).longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_int_to_Long(int x) {
/*  555 */     return new Long(new Integer(x).longValue());
/*      */   }
/*      */   
/*      */   public static long convert_Integer_to_long(Integer xx) {
/*  559 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_Integer_to_Long(Integer xx) {
/*  563 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static short convert_int_to_short(int x) {
/*  567 */     if (x > max_short_as_int) throw new IllegalArgumentException("int is too large to be recast as short");
/*  568 */     return new Integer(x).shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_int_to_Short(int x) {
/*  572 */     if (x > max_short_as_int) throw new IllegalArgumentException("int is too large to be recast as Short");
/*  573 */     return new Short(new Integer(x).shortValue());
/*      */   }
/*      */   
/*      */   public static short convert_Integer_to_short(Integer xx) {
/*  577 */     int x = xx.intValue();
/*  578 */     if (x > max_short_as_int) throw new IllegalArgumentException("Integer is too large to be recast as short");
/*  579 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_Integer_to_Short(Integer xx) {
/*  583 */     int x = xx.intValue();
/*  584 */     if (x > max_short_as_int) throw new IllegalArgumentException("Integer is too large to be recast as Short");
/*  585 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_int_to_byte(int x) {
/*  589 */     if (x > max_byte_as_int) throw new IllegalArgumentException("int is too large to be recast as byte");
/*  590 */     return new Integer(x).byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_int_to_Byte(int x) {
/*  594 */     if (x > max_byte_as_int) throw new IllegalArgumentException("int is too large to be recast as Byte");
/*  595 */     return new Byte(new Integer(x).byteValue());
/*      */   }
/*      */   
/*      */   public static byte convert_Integer_to_byte(Integer xx) {
/*  599 */     int x = xx.intValue();
/*  600 */     if (x > max_byte_as_int) throw new IllegalArgumentException("Integer is too large to be recast as byte");
/*  601 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_Integer_to_Byte(Integer xx) {
/*  605 */     int x = xx.intValue();
/*  606 */     if (x > max_byte_as_int) throw new IllegalArgumentException("Integer is too large to be recast as Byte");
/*  607 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_int_to_BigDecimal(int x) {
/*  611 */     return new BigDecimal(new Integer(x).toString());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Integer_to_BigDecimal(Integer xx) {
/*  615 */     return new BigDecimal(xx.toString());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_int_to_BigInteger(int x) {
/*  619 */     return new BigInteger(Long.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Integer_to_BigInteger(Integer xx) {
/*  623 */     return new BigInteger(xx.toString());
/*      */   }
/*      */   
/*      */   public static double convert_short_to_double(short x)
/*      */   {
/*  628 */     return new Short(x).doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_short_to_Double(short x) {
/*  632 */     return new Double(new Short(x).doubleValue());
/*      */   }
/*      */   
/*      */   public static double convert_Short_to_double(Short xx) {
/*  636 */     return xx.doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_Short_to_Double(Short xx) {
/*  640 */     return new Double(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static float convert_short_to_float(short x) {
/*  644 */     return new Short(x).floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_short_to_Float(short x) {
/*  648 */     return new Float(new Short(x).floatValue());
/*      */   }
/*      */   
/*      */   public static float convert_Short_to_float(Short xx) {
/*  652 */     return xx.floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_Short_to_Float(Short xx) {
/*  656 */     return new Float(xx.floatValue());
/*      */   }
/*      */   
/*      */   public static long convert_short_to_long(short x) {
/*  660 */     return new Short(x).longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_short_to_Long(short x) {
/*  664 */     return new Long(new Short(x).longValue());
/*      */   }
/*      */   
/*      */   public static long convert_Short_to_long(Short xx) {
/*  668 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_Short_to_Long(Short xx) {
/*  672 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_short_to_int(short x) {
/*  676 */     return new Short(x).intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_short_to_Integer(short x) {
/*  680 */     return new Integer(new Short(x).intValue());
/*      */   }
/*      */   
/*      */   public static int convert_Short_to_int(Short xx) {
/*  684 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_Short_to_Integer(Short xx) {
/*  688 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static byte convert_short_to_byte(short x) {
/*  692 */     if (x > max_byte_as_short) throw new IllegalArgumentException("short is too large to be recast as byte");
/*  693 */     return new Short(x).byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_short_to_Byte(short x) {
/*  697 */     if (x > max_byte_as_short) throw new IllegalArgumentException("short is too large to be recast as Byte");
/*  698 */     return new Byte(new Short(x).byteValue());
/*      */   }
/*      */   
/*      */   public static byte convert_Short_to_byte(Short xx) {
/*  702 */     int x = xx.shortValue();
/*  703 */     if (x > max_byte_as_short) throw new IllegalArgumentException("Short is too large to be recast as byte");
/*  704 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_Short_to_Byte(Short xx) {
/*  708 */     int x = xx.shortValue();
/*  709 */     if (x > max_byte_as_short) throw new IllegalArgumentException("Short is too large to be recast as Byte");
/*  710 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_short_to_BigDecimal(short x) {
/*  714 */     return new BigDecimal(new Short(x).toString());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Short_to_BigDecimal(Short xx) {
/*  718 */     return new BigDecimal(xx.toString());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_short_to_BigInteger(short x) {
/*  722 */     return new BigInteger(Short.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Short_to_BigInteger(Short xx) {
/*  726 */     return new BigInteger(xx.toString());
/*      */   }
/*      */   
/*      */   public static double convert_byte_to_double(byte x)
/*      */   {
/*  731 */     return new Byte(x).doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_byte_to_Double(byte x) {
/*  735 */     return new Double(new Byte(x).doubleValue());
/*      */   }
/*      */   
/*      */   public static double convert_Byte_to_double(Byte xx) {
/*  739 */     return xx.doubleValue();
/*      */   }
/*      */   
/*      */   public static Double convert_Byte_to_Double(Byte xx) {
/*  743 */     return new Double(xx.doubleValue());
/*      */   }
/*      */   
/*      */   public static float convert_byte_to_float(byte x) {
/*  747 */     return new Byte(x).floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_byte_to_Float(byte x) {
/*  751 */     return new Float(new Byte(x).floatValue());
/*      */   }
/*      */   
/*      */   public static float convert_Byte_to_float(Byte xx) {
/*  755 */     return xx.floatValue();
/*      */   }
/*      */   
/*      */   public static Float convert_Byte_to_Float(Byte xx) {
/*  759 */     return new Float(xx.floatValue());
/*      */   }
/*      */   
/*      */   public static long convert_byte_to_long(byte x) {
/*  763 */     return new Byte(x).longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_byte_to_Long(byte x) {
/*  767 */     return new Long(new Byte(x).longValue());
/*      */   }
/*      */   
/*      */   public static long convert_Byte_to_long(Byte xx) {
/*  771 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_Byte_to_Long(Byte xx) {
/*  775 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_byte_to_int(byte x) {
/*  779 */     return new Byte(x).intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_byte_to_Integer(byte x) {
/*  783 */     return new Integer(new Byte(x).intValue());
/*      */   }
/*      */   
/*      */   public static int convert_Byte_to_int(Byte xx) {
/*  787 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_Byte_to_Integer(Byte xx) {
/*  791 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_byte_to_short(byte x) {
/*  795 */     return new Byte(x).shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_byte_to_Short(byte x) {
/*  799 */     return new Short(new Byte(x).shortValue());
/*      */   }
/*      */   
/*      */   public static short convert_Byte_to_short(Byte xx) {
/*  803 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_Byte_to_Short(Byte xx) {
/*  807 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_byte_to_BigDecimal(byte x) {
/*  811 */     return new BigDecimal(new Byte(x).toString());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_Byte_to_BigDecimal(Byte xx) {
/*  815 */     return new BigDecimal(xx.toString());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_byte_to_BigInteger(byte x) {
/*  819 */     return new BigInteger(Byte.toString(x));
/*      */   }
/*      */   
/*      */   public static BigInteger convert_Byte_to_BigInteger(Byte xx) {
/*  823 */     return new BigInteger(xx.toString());
/*      */   }
/*      */   
/*      */ 
/*      */   public static double convert_BigDecimal_to_double(BigDecimal xx)
/*      */   {
/*  829 */     double x = xx.doubleValue();
/*  830 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as double");
/*  831 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigDecimal_to_double: possible loss of precision");
/*  832 */     return x;
/*      */   }
/*      */   
/*      */   public static Double convert_BigDecimal_to_Double(BigDecimal xx) {
/*  836 */     double x = xx.doubleValue();
/*  837 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as double");
/*  838 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigDecimal_to_double: possible loss of precision");
/*  839 */     return new Double(x);
/*      */   }
/*      */   
/*      */   public static float convert_BigDecimal_to_float(BigDecimal xx) {
/*  843 */     float x = xx.floatValue();
/*  844 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as float");
/*  845 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigDecimal_to_float: possible loss of precision");
/*  846 */     return x;
/*      */   }
/*      */   
/*      */   public static Float convert_BigDecimal_to_Float(BigDecimal xx) {
/*  850 */     float x = xx.floatValue();
/*  851 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as float");
/*  852 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigDecimal_to_float: possible loss of precision");
/*  853 */     return new Float(x);
/*      */   }
/*      */   
/*      */   public static long convert_BigDecimal_to_long(BigDecimal xx) {
/*  857 */     double x = xx.doubleValue();
/*  858 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as long");
/*  859 */     if (x > max_long_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as long");
/*  860 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  861 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_BigDecimal_to_Long(BigDecimal xx) {
/*  865 */     double x = xx.doubleValue();
/*  866 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as Long");
/*  867 */     if (x > max_long_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as Long");
/*  868 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  869 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_BigDecimal_to_int(BigDecimal xx) {
/*  873 */     double x = xx.doubleValue();
/*  874 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as int");
/*  875 */     if (x > max_int_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as int");
/*  876 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  877 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_BigDecimal_to_Integer(BigDecimal xx) {
/*  881 */     double x = xx.doubleValue();
/*  882 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as Integer");
/*  883 */     if (x > max_int_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as Integer");
/*  884 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  885 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_BigDecimal_to_short(BigDecimal xx) {
/*  889 */     double x = xx.doubleValue();
/*  890 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as short");
/*  891 */     if (x > max_short_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as short");
/*  892 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  893 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_BigDecimal_to_Short(BigDecimal xx) {
/*  897 */     double x = xx.doubleValue();
/*  898 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as Short");
/*  899 */     if (x > max_short_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as Short");
/*  900 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  901 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_BigDecimal_to_byte(BigDecimal xx) {
/*  905 */     double x = xx.doubleValue();
/*  906 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as byte");
/*  907 */     if (x > max_byte_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as byte");
/*  908 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  909 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_BigDecimal_to_Byte(BigDecimal xx) {
/*  913 */     double x = xx.doubleValue();
/*  914 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigDecimal is too large to be recast as Byte");
/*  915 */     if (x > max_byte_as_double) throw new IllegalArgumentException("BigDecimal is too large to be recast as Byte");
/*  916 */     if (!Fmath.isInteger(x)) throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*  917 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigInteger convert_BigDecimal_to_BigInteger(BigDecimal xx) {
/*  921 */     String ss = xx.toString();
/*  922 */     int posDot = ss.indexOf('.');
/*  923 */     int posExp = ss.indexOf('E');
/*  924 */     String tt = null;
/*      */     
/*  926 */     if (posDot == -1) {
/*  927 */       return xx.toBigInteger();
/*      */     }
/*      */     
/*  930 */     if (posExp == -1) {
/*  931 */       tt = ss.substring(posDot + 1);
/*      */     }
/*      */     else {
/*  934 */       tt = ss.substring(posDot + 1, posExp);
/*      */     }
/*  936 */     int n = tt.length();
/*  937 */     boolean test1 = true;
/*  938 */     boolean test2 = true;
/*  939 */     int ii = 0;
/*  940 */     while (test1) {
/*  941 */       if (tt.charAt(ii) != '0') {
/*  942 */         test1 = false;
/*  943 */         test2 = false;
/*      */       }
/*      */       else {
/*  946 */         ii++;
/*  947 */         if (ii == n) test1 = false;
/*      */       }
/*      */     }
/*  950 */     if (test2) {
/*  951 */       return xx.toBigInteger();
/*      */     }
/*      */     
/*  954 */     throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double convert_BigInteger_to_double(BigInteger xx)
/*      */   {
/*  963 */     double x = xx.doubleValue();
/*  964 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as double");
/*  965 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigInteger_to_double: possible loss of precision");
/*  966 */     return x;
/*      */   }
/*      */   
/*      */   public static Double convert_BigInteger_to_Double(BigInteger xx) {
/*  970 */     double x = xx.doubleValue();
/*  971 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as double");
/*  972 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigInteger_to_double: possible loss of precision");
/*  973 */     return new Double(x);
/*      */   }
/*      */   
/*      */   public static float convert_BigInteger_to_float(BigInteger xx) {
/*  977 */     float x = xx.floatValue();
/*  978 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as float");
/*  979 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigInteger_to_float: possible loss of precision");
/*  980 */     return x;
/*      */   }
/*      */   
/*      */   public static Float convert_BigInteger_to_Float(BigInteger xx) {
/*  984 */     float x = xx.floatValue();
/*  985 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as float");
/*  986 */     if (!suppressMessage) System.out.println("Class Conv: method convert_BigInteger_to_float: possible loss of precision");
/*  987 */     return new Float(x);
/*      */   }
/*      */   
/*      */   public static long convert_BigInteger_to_long(BigInteger xx) {
/*  991 */     double x = xx.doubleValue();
/*  992 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as long");
/*  993 */     if (x > max_long_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as long");
/*  994 */     return xx.longValue();
/*      */   }
/*      */   
/*      */   public static Long convert_BigInteger_to_Long(BigInteger xx) {
/*  998 */     double x = xx.doubleValue();
/*  999 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as Long");
/* 1000 */     if (x > max_long_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as Long");
/* 1001 */     return new Long(xx.longValue());
/*      */   }
/*      */   
/*      */   public static int convert_BigInteger_to_int(BigInteger xx) {
/* 1005 */     double x = xx.doubleValue();
/* 1006 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as int");
/* 1007 */     if (x > max_int_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as int");
/* 1008 */     return xx.intValue();
/*      */   }
/*      */   
/*      */   public static Integer convert_BigInteger_to_Integer(BigInteger xx) {
/* 1012 */     double x = xx.doubleValue();
/* 1013 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as Integer");
/* 1014 */     if (x > max_int_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as Integer");
/* 1015 */     return new Integer(xx.intValue());
/*      */   }
/*      */   
/*      */   public static short convert_BigInteger_to_short(BigInteger xx) {
/* 1019 */     double x = xx.doubleValue();
/* 1020 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as short");
/* 1021 */     if (x > max_short_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as short");
/* 1022 */     return xx.shortValue();
/*      */   }
/*      */   
/*      */   public static Short convert_BigInteger_to_Short(BigInteger xx) {
/* 1026 */     double x = xx.doubleValue();
/* 1027 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as Short");
/* 1028 */     if (x > max_short_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as Short");
/* 1029 */     return new Short(xx.shortValue());
/*      */   }
/*      */   
/*      */   public static byte convert_BigInteger_to_byte(BigInteger xx) {
/* 1033 */     double x = xx.doubleValue();
/* 1034 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as byte");
/* 1035 */     if (x > max_byte_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as byte");
/* 1036 */     return xx.byteValue();
/*      */   }
/*      */   
/*      */   public static Byte convert_BigInteger_to_Byte(BigInteger xx) {
/* 1040 */     double x = xx.doubleValue();
/* 1041 */     if (Fmath.isInfinity(x)) throw new IllegalArgumentException("BigInteger is too large to be recast as Byte");
/* 1042 */     if (x > max_byte_as_double) throw new IllegalArgumentException("BigInteger is too large to be recast as Byte");
/* 1043 */     return new Byte(xx.byteValue());
/*      */   }
/*      */   
/*      */   public static BigDecimal convert_BigInteger_to_BigDecimal(BigInteger xx) {
/* 1047 */     return new BigDecimal(xx);
/*      */   }
/*      */   
/*      */   public static Phasor convert_Complex_to_Phasor(Complex xx)
/*      */   {
/* 1052 */     double mag = xx.abs();
/* 1053 */     double phase = xx.argDeg();
/* 1054 */     return new Phasor(mag, phase);
/*      */   }
/*      */   
/*      */   public static Complex convert_Phasor_to_Complex(Phasor xx)
/*      */   {
/* 1059 */     return xx.toComplex();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object copyObject(Object obj)
/*      */   {
/* 1067 */     Object objCopy = null;
/*      */     try
/*      */     {
/* 1070 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 1071 */       ObjectOutputStream oos = new ObjectOutputStream(bos);
/* 1072 */       oos.writeObject(obj);
/* 1073 */       oos.flush();
/* 1074 */       oos.close();
/*      */       
/*      */ 
/* 1077 */       ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
/*      */       
/* 1079 */       objCopy = ois.readObject();
/*      */     }
/*      */     catch (IOException e) {
/* 1082 */       e.printStackTrace();
/*      */     }
/*      */     catch (ClassNotFoundException cnfe) {
/* 1085 */       cnfe.printStackTrace();
/*      */     }
/* 1087 */     return objCopy;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double radToDeg(double rad)
/*      */   {
/* 1094 */     return rad * 180.0D / 3.141592653589793D;
/*      */   }
/*      */   
/*      */   public static double degToRad(double deg)
/*      */   {
/* 1099 */     return deg * 3.141592653589793D / 180.0D;
/*      */   }
/*      */   
/*      */   public static double frequencyToRadialFrequency(double frequency)
/*      */   {
/* 1104 */     return 6.283185307179586D * frequency;
/*      */   }
/*      */   
/*      */   public static double radialFrequencyToFrequency(double radial)
/*      */   {
/* 1109 */     return radial / 6.283185307179586D;
/*      */   }
/*      */   
/*      */   public static double evToNm(double ev)
/*      */   {
/* 1114 */     return 2.99792458E17D / (-ev * -1.60217646263E-19D / 6.6260687652E-34D);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double nmToEv(double nm)
/*      */   {
/* 1120 */     return 2.99792458E8D / (-nm * 1.0E-9D) * 6.6260687652E-34D / -1.60217646263E-19D;
/*      */   }
/*      */   
/*      */   public static double molarToPercentWeightByVol(double molar, double molWeight)
/*      */   {
/* 1125 */     return molar * molWeight / 10.0D;
/*      */   }
/*      */   
/*      */   public static double percentWeightByVolToMolar(double perCent, double molWeight)
/*      */   {
/* 1130 */     return perCent * 10.0D / molWeight;
/*      */   }
/*      */   
/*      */   public static double celsiusToKelvin(double cels)
/*      */   {
/* 1135 */     return cels - -273.15D;
/*      */   }
/*      */   
/*      */   public static double kelvinToCelsius(double kelv)
/*      */   {
/* 1140 */     return kelv + -273.15D;
/*      */   }
/*      */   
/*      */   public static double celsiusToFahren(double cels)
/*      */   {
/* 1145 */     return cels * 1.8D + 32.0D;
/*      */   }
/*      */   
/*      */   public static double fahrenToCelsius(double fahr)
/*      */   {
/* 1150 */     return (fahr - 32.0D) * 5.0D / 9.0D;
/*      */   }
/*      */   
/*      */   public static double calorieToJoule(double cal)
/*      */   {
/* 1155 */     return cal * 4.1868D;
/*      */   }
/*      */   
/*      */   public static double jouleToCalorie(double joule)
/*      */   {
/* 1160 */     return joule * 0.23884D;
/*      */   }
/*      */   
/*      */   public static double gramToOunce(double gm)
/*      */   {
/* 1165 */     return gm / 28.3459D;
/*      */   }
/*      */   
/*      */   public static double ounceToGram(double oz)
/*      */   {
/* 1170 */     return oz * 28.3459D;
/*      */   }
/*      */   
/*      */   public static double kgToPound(double kg)
/*      */   {
/* 1175 */     return kg / 0.4536D;
/*      */   }
/*      */   
/*      */   public static double poundToKg(double pds)
/*      */   {
/* 1180 */     return pds * 0.4536D;
/*      */   }
/*      */   
/*      */   public static double kgToTon(double kg)
/*      */   {
/* 1185 */     return kg / 1016.05D;
/*      */   }
/*      */   
/*      */   public static double tonToKg(double tons)
/*      */   {
/* 1190 */     return tons * 1016.05D;
/*      */   }
/*      */   
/*      */   public static double millimetreToInch(double mm)
/*      */   {
/* 1195 */     return mm / 25.4D;
/*      */   }
/*      */   
/*      */   public static double inchToMillimetre(double in)
/*      */   {
/* 1200 */     return in * 25.4D;
/*      */   }
/*      */   
/*      */   public static double footToMetre(double ft)
/*      */   {
/* 1205 */     return ft * 0.3048D;
/*      */   }
/*      */   
/*      */   public static double metreToFoot(double metre)
/*      */   {
/* 1210 */     return metre / 0.3048D;
/*      */   }
/*      */   
/*      */   public static double yardToMetre(double yd)
/*      */   {
/* 1215 */     return yd * 0.9144D;
/*      */   }
/*      */   
/*      */   public static double metreToYard(double metre)
/*      */   {
/* 1220 */     return metre / 0.9144D;
/*      */   }
/*      */   
/*      */   public static double mileToKm(double mile)
/*      */   {
/* 1225 */     return mile * 1.6093D;
/*      */   }
/*      */   
/*      */   public static double kmToMile(double km)
/*      */   {
/* 1230 */     return km / 1.6093D;
/*      */   }
/*      */   
/*      */   public static double gallonToLitre(double gall)
/*      */   {
/* 1235 */     return gall * 4.546D;
/*      */   }
/*      */   
/*      */   public static double litreToGallon(double litre)
/*      */   {
/* 1240 */     return litre / 4.546D;
/*      */   }
/*      */   
/*      */   public static double quartToLitre(double quart)
/*      */   {
/* 1245 */     return quart * 1.137D;
/*      */   }
/*      */   
/*      */   public static double litreToQuart(double litre)
/*      */   {
/* 1250 */     return litre / 1.137D;
/*      */   }
/*      */   
/*      */   public static double pintToLitre(double pint)
/*      */   {
/* 1255 */     return pint * 0.568D;
/*      */   }
/*      */   
/*      */   public static double litreToPint(double litre)
/*      */   {
/* 1260 */     return litre / 0.568D;
/*      */   }
/*      */   
/*      */   public static double gallonPerMileToLitrePerKm(double gallPmile)
/*      */   {
/* 1265 */     return gallPmile * 2.825D;
/*      */   }
/*      */   
/*      */   public static double litrePerKmToGallonPerMile(double litrePkm)
/*      */   {
/* 1270 */     return litrePkm / 2.825D;
/*      */   }
/*      */   
/*      */   public static double milePerGallonToKmPerLitre(double milePgall)
/*      */   {
/* 1275 */     return milePgall * 0.354D;
/*      */   }
/*      */   
/*      */   public static double kmPerLitreToMilePerGallon(double kmPlitre)
/*      */   {
/* 1280 */     return kmPlitre / 0.354D;
/*      */   }
/*      */   
/*      */   public static double fluidOunceUKtoUS(double flOzUK)
/*      */   {
/* 1285 */     return flOzUK * 0.961D;
/*      */   }
/*      */   
/*      */   public static double fluidOunceUStoUK(double flOzUS)
/*      */   {
/* 1290 */     return flOzUS * 1.041D;
/*      */   }
/*      */   
/*      */   public static double pintUKtoUS(double pintUK)
/*      */   {
/* 1295 */     return pintUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double pintUStoUK(double pintUS)
/*      */   {
/* 1300 */     return pintUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double quartUKtoUS(double quartUK)
/*      */   {
/* 1305 */     return quartUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double quartUStoUK(double quartUS)
/*      */   {
/* 1310 */     return quartUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double gallonUKtoUS(double gallonUK)
/*      */   {
/* 1315 */     return gallonUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double gallonUStoUK(double gallonUS)
/*      */   {
/* 1320 */     return gallonUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double pintUKtoCupUS(double pintUK)
/*      */   {
/* 1325 */     return pintUK / 0.417D;
/*      */   }
/*      */   
/*      */   public static double cupUStoPintUK(double cupUS)
/*      */   {
/* 1330 */     return cupUS * 0.417D;
/*      */   }
/*      */   
/*      */   public static double calcBMImetric(double height, double weight)
/*      */   {
/* 1335 */     return weight / (height * height);
/*      */   }
/*      */   
/*      */   public static double calcBMIimperial(double height, double weight)
/*      */   {
/* 1340 */     height = Fmath.footToMetre(height);
/* 1341 */     weight = Fmath.poundToKg(weight);
/* 1342 */     return weight / (height * height);
/*      */   }
/*      */   
/*      */   public static double calcWeightFromBMImetric(double bmi, double height)
/*      */   {
/* 1347 */     return bmi * height * height;
/*      */   }
/*      */   
/*      */   public static double calcWeightFromBMIimperial(double bmi, double height)
/*      */   {
/* 1352 */     height = Fmath.footToMetre(height);
/* 1353 */     double weight = bmi * height * height;
/* 1354 */     weight = Fmath.kgToPound(weight);
/* 1355 */     return weight;
/*      */   }
/*      */   
/*      */ 
/*      */   public static long dateToJavaMilliS(int year, int month, int day, int hour, int min, int sec)
/*      */   {
/* 1361 */     long[] monthDays = { 0L, 31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L };
/* 1362 */     long ms = 0L;
/*      */     
/* 1364 */     long yearDiff = 0L;
/* 1365 */     int yearTest = year - 1;
/* 1366 */     while (yearTest >= 1970) {
/* 1367 */       yearDiff += 365L;
/* 1368 */       if (Fmath.leapYear(yearTest)) yearDiff += 1L;
/* 1369 */       yearTest--;
/*      */     }
/* 1371 */     yearDiff *= 86400000L;
/*      */     
/* 1373 */     long monthDiff = 0L;
/* 1374 */     int monthTest = month - 1;
/* 1375 */     while (monthTest > 0) {
/* 1376 */       monthDiff += monthDays[monthTest];
/* 1377 */       if (Fmath.leapYear(year)) monthDiff += 1L;
/* 1378 */       monthTest--;
/*      */     }
/*      */     
/* 1381 */     monthDiff *= 86400000L;
/*      */     
/* 1383 */     ms = yearDiff + monthDiff + day * 24L * 60L * 60L * 1000L + hour * 60L * 60L * 1000L + min * 60L * 1000L + sec * 1000L;
/*      */     
/* 1385 */     return ms;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/Conv.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */