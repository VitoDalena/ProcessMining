/*      */ package flanagan.math;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
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
/*      */ public class Fmath
/*      */ {
/*      */   public static final double N_AVAGADRO = 6.0221419947E23D;
/*      */   public static final double K_BOLTZMANN = 1.380650324E-23D;
/*      */   public static final double H_PLANCK = 6.6260687652E-34D;
/*      */   public static final double H_PLANCK_RED = 1.0545715972483913E-34D;
/*      */   public static final double C_LIGHT = 2.99792458E8D;
/*      */   public static final double R_GAS = 8.31447215D;
/*      */   public static final double F_FARADAY = 96485.341539D;
/*      */   public static final double T_ABS = -273.15D;
/*      */   public static final double Q_ELECTRON = -1.60217646263E-19D;
/*      */   public static final double M_ELECTRON = 9.1093818872E-31D;
/*      */   public static final double M_PROTON = 1.6726215813E-27D;
/*      */   public static final double M_NEUTRON = 1.6749271613E-27D;
/*      */   public static final double EPSILON_0 = 8.854187817E-12D;
/*      */   public static final double MU_0 = 1.2566370614359173E-6D;
/*      */   public static final double EULER_CONSTANT_GAMMA = 0.5772156649015627D;
/*      */   public static final double PI = 3.141592653589793D;
/*      */   public static final double E = 2.718281828459045D;
/*   75 */   private static final Map<Object, Object> integers = new HashMap();
/*      */   
/*   77 */   static { integers.put(Integer.class, BigDecimal.valueOf(2147483647L));
/*   78 */     integers.put(Long.class, BigDecimal.valueOf(Long.MAX_VALUE));
/*   79 */     integers.put(Byte.class, BigDecimal.valueOf(127L));
/*   80 */     integers.put(Short.class, BigDecimal.valueOf(32767L));
/*   81 */     integers.put(BigInteger.class, BigDecimal.valueOf(-1L));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double log10(double a)
/*      */   {
/*   89 */     return Math.log(a) / Math.log(10.0D);
/*      */   }
/*      */   
/*      */   public static float log10(float a)
/*      */   {
/*   94 */     return (float)(Math.log(a) / Math.log(10.0D));
/*      */   }
/*      */   
/*      */   public static double antilog10(double x)
/*      */   {
/*   99 */     return Math.pow(10.0D, x);
/*      */   }
/*      */   
/*      */   public static float antilog10(float x)
/*      */   {
/*  104 */     return (float)Math.pow(10.0D, x);
/*      */   }
/*      */   
/*      */   public static double log(double a)
/*      */   {
/*  109 */     return Math.log(a);
/*      */   }
/*      */   
/*      */   public static float log(float a)
/*      */   {
/*  114 */     return (float)Math.log(a);
/*      */   }
/*      */   
/*      */   public static double antilog(double x)
/*      */   {
/*  119 */     return Math.exp(x);
/*      */   }
/*      */   
/*      */   public static float antilog(float x)
/*      */   {
/*  124 */     return (float)Math.exp(x);
/*      */   }
/*      */   
/*      */   public static double log2(double a)
/*      */   {
/*  129 */     return Math.log(a) / Math.log(2.0D);
/*      */   }
/*      */   
/*      */   public static float log2(float a)
/*      */   {
/*  134 */     return (float)(Math.log(a) / Math.log(2.0D));
/*      */   }
/*      */   
/*      */   public static double antilog2(double x)
/*      */   {
/*  139 */     return Math.pow(2.0D, x);
/*      */   }
/*      */   
/*      */   public static float antilog2(float x)
/*      */   {
/*  144 */     return (float)Math.pow(2.0D, x);
/*      */   }
/*      */   
/*      */   public static double log10(double a, double b)
/*      */   {
/*  149 */     return Math.log(a) / Math.log(b);
/*      */   }
/*      */   
/*      */   public static double log10(double a, int b)
/*      */   {
/*  154 */     return Math.log(a) / Math.log(b);
/*      */   }
/*      */   
/*      */   public static float log10(float a, float b)
/*      */   {
/*  159 */     return (float)(Math.log(a) / Math.log(b));
/*      */   }
/*      */   
/*      */   public static float log10(float a, int b)
/*      */   {
/*  164 */     return (float)(Math.log(a) / Math.log(b));
/*      */   }
/*      */   
/*      */ 
/*      */   public static double square(double a)
/*      */   {
/*  170 */     return a * a;
/*      */   }
/*      */   
/*      */   public static float square(float a)
/*      */   {
/*  175 */     return a * a;
/*      */   }
/*      */   
/*      */   public static BigDecimal square(BigDecimal a)
/*      */   {
/*  180 */     return a.multiply(a);
/*      */   }
/*      */   
/*      */   public static int square(int a)
/*      */   {
/*  185 */     return a * a;
/*      */   }
/*      */   
/*      */   public static long square(long a)
/*      */   {
/*  190 */     return a * a;
/*      */   }
/*      */   
/*      */   public static BigInteger square(BigInteger a)
/*      */   {
/*  195 */     return a.multiply(a);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int factorial(int n)
/*      */   {
/*  203 */     if (n < 0) throw new IllegalArgumentException("n must be a positive integer");
/*  204 */     if (n > 12) throw new IllegalArgumentException("n must less than 13 to avoid integer overflow\nTry long or double argument");
/*  205 */     int f = 1;
/*  206 */     for (int i = 2; i <= n; i++) f *= i;
/*  207 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static long factorial(long n)
/*      */   {
/*  214 */     if (n < 0L) throw new IllegalArgumentException("n must be a positive integer");
/*  215 */     if (n > 20L) throw new IllegalArgumentException("n must less than 21 to avoid long integer overflow\nTry double argument");
/*  216 */     long f = 1L;
/*  217 */     long iCount = 2L;
/*  218 */     while (iCount <= n) {
/*  219 */       f *= iCount;
/*  220 */       iCount += 1L;
/*      */     }
/*  222 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */   public static BigInteger factorial(BigInteger n)
/*      */   {
/*  228 */     if (n.compareTo(BigInteger.ZERO) == -1) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  229 */     BigInteger one = BigInteger.ONE;
/*  230 */     BigInteger f = one;
/*  231 */     BigInteger iCount = new BigInteger("2");
/*  232 */     while (iCount.compareTo(n) != 1) {
/*  233 */       f = f.multiply(iCount);
/*  234 */       iCount = iCount.add(one);
/*      */     }
/*  236 */     one = null;
/*  237 */     iCount = null;
/*  238 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double factorial(double n)
/*      */   {
/*  246 */     if ((n < 0.0D) || (n - Math.floor(n) != 0.0D)) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  247 */     double f = 1.0D;
/*  248 */     double iCount = 2.0D;
/*  249 */     while (iCount <= n) {
/*  250 */       f *= iCount;
/*  251 */       iCount += 1.0D;
/*      */     }
/*  253 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */   public static BigDecimal factorial(BigDecimal n)
/*      */   {
/*  259 */     if ((n.compareTo(BigDecimal.ZERO) == -1) || (!isInteger(n))) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  260 */     BigDecimal one = BigDecimal.ONE;
/*  261 */     BigDecimal f = one;
/*  262 */     BigDecimal iCount = new BigDecimal(2.0D);
/*  263 */     while (iCount.compareTo(n) != 1) {
/*  264 */       f = f.multiply(iCount);
/*  265 */       iCount = iCount.add(one);
/*      */     }
/*  267 */     one = null;
/*  268 */     iCount = null;
/*  269 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double logFactorial(int n)
/*      */   {
/*  278 */     if (n < 0) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  279 */     double f = 0.0D;
/*  280 */     for (int i = 2; i <= n; i++) f += Math.log(i);
/*  281 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double logFactorial(long n)
/*      */   {
/*  289 */     if (n < 0L) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  290 */     double f = 0.0D;
/*  291 */     long iCount = 2L;
/*  292 */     while (iCount <= n) {
/*  293 */       f += Math.log(iCount);
/*  294 */       iCount += 1L;
/*      */     }
/*  296 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double logFactorial(double n)
/*      */   {
/*  304 */     if ((n < 0.0D) || (n - Math.floor(n) != 0.0D)) throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
/*  305 */     double f = 0.0D;
/*  306 */     double iCount = 2.0D;
/*  307 */     while (iCount <= n) {
/*  308 */       f += Math.log(iCount);
/*  309 */       iCount += 1.0D;
/*      */     }
/*  311 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sign(double x)
/*      */   {
/*  319 */     if (x < 0.0D) {
/*  320 */       return -1.0D;
/*      */     }
/*      */     
/*  323 */     return 1.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static float sign(float x)
/*      */   {
/*  330 */     if (x < 0.0F) {
/*  331 */       return -1.0F;
/*      */     }
/*      */     
/*  334 */     return 1.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int sign(int x)
/*      */   {
/*  341 */     if (x < 0) {
/*  342 */       return -1;
/*      */     }
/*      */     
/*  345 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static long sign(long x)
/*      */   {
/*  352 */     if (x < 0L) {
/*  353 */       return -1L;
/*      */     }
/*      */     
/*  356 */     return 1L;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double hypot(double aa, double bb)
/*      */   {
/*  366 */     double amod = Math.abs(aa);
/*  367 */     double bmod = Math.abs(bb);
/*  368 */     double cc = 0.0D;double ratio = 0.0D;
/*  369 */     if (amod == 0.0D) {
/*  370 */       cc = bmod;
/*      */ 
/*      */     }
/*  373 */     else if (bmod == 0.0D) {
/*  374 */       cc = amod;
/*      */ 
/*      */     }
/*  377 */     else if (amod >= bmod) {
/*  378 */       ratio = bmod / amod;
/*  379 */       cc = amod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     else {
/*  382 */       ratio = amod / bmod;
/*  383 */       cc = bmod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     
/*      */ 
/*  387 */     return cc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static float hypot(float aa, float bb)
/*      */   {
/*  394 */     return (float)hypot(aa, bb);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double angle(double xAtA, double yAtA, double xAtB, double yAtB, double xAtC, double yAtC)
/*      */   {
/*  401 */     double ccos = cos(xAtA, yAtA, xAtB, yAtB, xAtC, yAtC);
/*  402 */     return Math.acos(ccos);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double angle(double sideAC, double sideBC, double sideAB)
/*      */   {
/*  408 */     double ccos = cos(sideAC, sideBC, sideAB);
/*  409 */     return Math.acos(ccos);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sin(double xAtA, double yAtA, double xAtB, double yAtB, double xAtC, double yAtC)
/*      */   {
/*  415 */     double angle = angle(xAtA, yAtA, xAtB, yAtB, xAtC, yAtC);
/*  416 */     return Math.sin(angle);
/*      */   }
/*      */   
/*      */   public static double sin(double sideAC, double sideBC, double sideAB)
/*      */   {
/*  421 */     double angle = angle(sideAC, sideBC, sideAB);
/*  422 */     return Math.sin(angle);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sin(double arg)
/*      */   {
/*  428 */     return Math.sin(arg);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double asin(double a)
/*      */   {
/*  434 */     if ((a < -1.0D) && (a > 1.0D)) throw new IllegalArgumentException("Fmath.asin argument (" + a + ") must be >= -1.0 and <= 1.0");
/*  435 */     return Math.asin(a);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double cos(double xAtA, double yAtA, double xAtB, double yAtB, double xAtC, double yAtC)
/*      */   {
/*  441 */     double sideAC = hypot(xAtA - xAtC, yAtA - yAtC);
/*  442 */     double sideBC = hypot(xAtB - xAtC, yAtB - yAtC);
/*  443 */     double sideAB = hypot(xAtA - xAtB, yAtA - yAtB);
/*  444 */     return cos(sideAC, sideBC, sideAB);
/*      */   }
/*      */   
/*      */   public static double cos(double sideAC, double sideBC, double sideAB)
/*      */   {
/*  449 */     return 0.5D * (sideAC / sideBC + sideBC / sideAC - sideAB / sideAC * (sideAB / sideBC));
/*      */   }
/*      */   
/*      */ 
/*      */   public static double cos(double arg)
/*      */   {
/*  455 */     return Math.cos(arg);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double acos(double a)
/*      */   {
/*  461 */     if ((a < -1.0D) || (a > 1.0D)) throw new IllegalArgumentException("Fmath.acos argument (" + a + ") must be >= -1.0 and <= 1.0");
/*  462 */     return Math.acos(a);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double tan(double xAtA, double yAtA, double xAtB, double yAtB, double xAtC, double yAtC)
/*      */   {
/*  468 */     double angle = angle(xAtA, yAtA, xAtB, yAtB, xAtC, yAtC);
/*  469 */     return Math.tan(angle);
/*      */   }
/*      */   
/*      */   public static double tan(double sideAC, double sideBC, double sideAB)
/*      */   {
/*  474 */     double angle = angle(sideAC, sideBC, sideAB);
/*  475 */     return Math.tan(angle);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double tan(double arg)
/*      */   {
/*  481 */     return Math.tan(arg);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double atan(double a)
/*      */   {
/*  487 */     return Math.atan(a);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double atan2(double a, double b)
/*      */   {
/*  493 */     return Math.atan2(a, b);
/*      */   }
/*      */   
/*      */   public static double cot(double a)
/*      */   {
/*  498 */     return 1.0D / Math.tan(a);
/*      */   }
/*      */   
/*      */   public static double acot(double a)
/*      */   {
/*  503 */     return Math.atan(1.0D / a);
/*      */   }
/*      */   
/*      */   public static double acot2(double a, double b)
/*      */   {
/*  508 */     return Math.atan2(b, a);
/*      */   }
/*      */   
/*      */   public static double sec(double a)
/*      */   {
/*  513 */     return 1.0D / Math.cos(a);
/*      */   }
/*      */   
/*      */   public static double asec(double a)
/*      */   {
/*  518 */     if ((a < 1.0D) && (a > -1.0D)) throw new IllegalArgumentException("asec argument (" + a + ") must be >= 1 or <= -1");
/*  519 */     return Math.acos(1.0D / a);
/*      */   }
/*      */   
/*      */   public static double csc(double a)
/*      */   {
/*  524 */     return 1.0D / Math.sin(a);
/*      */   }
/*      */   
/*      */   public static double acsc(double a)
/*      */   {
/*  529 */     if ((a < 1.0D) && (a > -1.0D)) throw new IllegalArgumentException("acsc argument (" + a + ") must be >= 1 or <= -1");
/*  530 */     return Math.asin(1.0D / a);
/*      */   }
/*      */   
/*      */   public static double exsec(double a)
/*      */   {
/*  535 */     return 1.0D / Math.cos(a) - 1.0D;
/*      */   }
/*      */   
/*      */   public static double aexsec(double a)
/*      */   {
/*  540 */     if ((a < 0.0D) && (a > -2.0D)) throw new IllegalArgumentException("aexsec argument (" + a + ") must be >= 0.0 and <= -2");
/*  541 */     return Math.asin(1.0D / (1.0D + a));
/*      */   }
/*      */   
/*      */   public static double vers(double a)
/*      */   {
/*  546 */     return 1.0D - Math.cos(a);
/*      */   }
/*      */   
/*      */   public static double avers(double a)
/*      */   {
/*  551 */     if ((a < 0.0D) && (a > 2.0D)) throw new IllegalArgumentException("avers argument (" + a + ") must be <= 2 and >= 0");
/*  552 */     return Math.acos(1.0D - a);
/*      */   }
/*      */   
/*      */   public static double covers(double a)
/*      */   {
/*  557 */     return 1.0D - Math.sin(a);
/*      */   }
/*      */   
/*      */   public static double acovers(double a)
/*      */   {
/*  562 */     if ((a < 0.0D) && (a > 2.0D)) throw new IllegalArgumentException("acovers argument (" + a + ") must be <= 2 and >= 0");
/*  563 */     return Math.asin(1.0D - a);
/*      */   }
/*      */   
/*      */   public static double hav(double a)
/*      */   {
/*  568 */     return 0.5D * vers(a);
/*      */   }
/*      */   
/*      */   public static double ahav(double a)
/*      */   {
/*  573 */     if ((a < 0.0D) && (a > 1.0D)) throw new IllegalArgumentException("ahav argument (" + a + ") must be >= 0 and <= 1");
/*  574 */     return acos(1.0D - 2.0D * a);
/*      */   }
/*      */   
/*      */   public static double sinc(double a)
/*      */   {
/*  579 */     if (Math.abs(a) < 1.0E-40D) {
/*  580 */       return 1.0D;
/*      */     }
/*      */     
/*  583 */     return Math.sin(a) / a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double nsinc(double a)
/*      */   {
/*  589 */     if (Math.abs(a) < 1.0E-40D) {
/*  590 */       return 1.0D;
/*      */     }
/*      */     
/*  593 */     return Math.sin(3.141592653589793D * a) / (3.141592653589793D * a);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sinh(double a)
/*      */   {
/*  599 */     return 0.5D * (Math.exp(a) - Math.exp(-a));
/*      */   }
/*      */   
/*      */   public static double asinh(double a)
/*      */   {
/*  604 */     double sgn = 1.0D;
/*  605 */     if (a < 0.0D) {
/*  606 */       sgn = -1.0D;
/*  607 */       a = -a;
/*      */     }
/*  609 */     return sgn * Math.log(a + Math.sqrt(a * a + 1.0D));
/*      */   }
/*      */   
/*      */   public static double cosh(double a)
/*      */   {
/*  614 */     return 0.5D * (Math.exp(a) + Math.exp(-a));
/*      */   }
/*      */   
/*      */   public static double acosh(double a)
/*      */   {
/*  619 */     if (a < 1.0D) throw new IllegalArgumentException("acosh real number argument (" + a + ") must be >= 1");
/*  620 */     return Math.log(a + Math.sqrt(a * a - 1.0D));
/*      */   }
/*      */   
/*      */   public static double tanh(double a)
/*      */   {
/*  625 */     return sinh(a) / cosh(a);
/*      */   }
/*      */   
/*      */   public static double atanh(double a)
/*      */   {
/*  630 */     double sgn = 1.0D;
/*  631 */     if (a < 0.0D) {
/*  632 */       sgn = -1.0D;
/*  633 */       a = -a;
/*      */     }
/*  635 */     if (a > 1.0D) throw new IllegalArgumentException("atanh real number argument (" + sgn * a + ") must be >= -1 and <= 1");
/*  636 */     return 0.5D * sgn * (Math.log(1.0D + a) - Math.log(1.0D - a));
/*      */   }
/*      */   
/*      */   public static double coth(double a)
/*      */   {
/*  641 */     return 1.0D / tanh(a);
/*      */   }
/*      */   
/*      */   public static double acoth(double a)
/*      */   {
/*  646 */     double sgn = 1.0D;
/*  647 */     if (a < 0.0D) {
/*  648 */       sgn = -1.0D;
/*  649 */       a = -a;
/*      */     }
/*  651 */     if (a < 1.0D) throw new IllegalArgumentException("acoth real number argument (" + sgn * a + ") must be <= -1 or >= 1");
/*  652 */     return 0.5D * sgn * (Math.log(1.0D + a) - Math.log(a - 1.0D));
/*      */   }
/*      */   
/*      */   public static double sech(double a)
/*      */   {
/*  657 */     return 1.0D / cosh(a);
/*      */   }
/*      */   
/*      */   public static double asech(double a)
/*      */   {
/*  662 */     if ((a > 1.0D) || (a < 0.0D)) throw new IllegalArgumentException("asech real number argument (" + a + ") must be >= 0 and <= 1");
/*  663 */     return 0.5D * Math.log(1.0D / a + Math.sqrt(1.0D / (a * a) - 1.0D));
/*      */   }
/*      */   
/*      */   public static double csch(double a)
/*      */   {
/*  668 */     return 1.0D / sinh(a);
/*      */   }
/*      */   
/*      */   public static double acsch(double a)
/*      */   {
/*  673 */     double sgn = 1.0D;
/*  674 */     if (a < 0.0D) {
/*  675 */       sgn = -1.0D;
/*  676 */       a = -a;
/*      */     }
/*  678 */     return 0.5D * sgn * Math.log(1.0D / a + Math.sqrt(1.0D / (a * a) + 1.0D));
/*      */   }
/*      */   
/*      */ 
/*      */   public static double truncate(double xDouble, int trunc)
/*      */   {
/*  684 */     double xTruncated = xDouble;
/*  685 */     if ((!isNaN(xDouble)) && 
/*  686 */       (!isPlusInfinity(xDouble)) && 
/*  687 */       (!isMinusInfinity(xDouble)) && 
/*  688 */       (xDouble != 0.0D)) {
/*  689 */       String xString = new Double(xDouble).toString().trim();
/*  690 */       xTruncated = Double.parseDouble(truncateProcedure(xString, trunc));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  695 */     return xTruncated;
/*      */   }
/*      */   
/*      */   public static float truncate(float xFloat, int trunc)
/*      */   {
/*  700 */     float xTruncated = xFloat;
/*  701 */     if ((!isNaN(xFloat)) && 
/*  702 */       (!isPlusInfinity(xFloat)) && 
/*  703 */       (!isMinusInfinity(xFloat)) && 
/*  704 */       (xFloat != 0.0D)) {
/*  705 */       String xString = new Float(xFloat).toString().trim();
/*  706 */       xTruncated = Float.parseFloat(truncateProcedure(xString, trunc));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  711 */     return xTruncated;
/*      */   }
/*      */   
/*      */ 
/*      */   private static String truncateProcedure(String xValue, int trunc)
/*      */   {
/*  717 */     String xTruncated = xValue;
/*  718 */     String xWorking = xValue;
/*  719 */     String exponent = " ";
/*  720 */     String first = "+";
/*  721 */     int expPos = xValue.indexOf('E');
/*  722 */     int dotPos = xValue.indexOf('.');
/*  723 */     int minPos = xValue.indexOf('-');
/*      */     
/*  725 */     if ((minPos != -1) && 
/*  726 */       (minPos == 0)) {
/*  727 */       xWorking = xWorking.substring(1);
/*  728 */       first = "-";
/*  729 */       dotPos--;
/*  730 */       expPos--;
/*      */     }
/*      */     
/*  733 */     if (expPos > -1) {
/*  734 */       exponent = xWorking.substring(expPos);
/*  735 */       xWorking = xWorking.substring(0, expPos);
/*      */     }
/*  737 */     String xPreDot = null;
/*  738 */     String xPostDot = "0";
/*  739 */     String xDiscarded = null;
/*  740 */     String tempString = null;
/*  741 */     double tempDouble = 0.0D;
/*  742 */     if (dotPos > -1) {
/*  743 */       xPreDot = xWorking.substring(0, dotPos);
/*  744 */       xPostDot = xWorking.substring(dotPos + 1);
/*  745 */       int xLength = xPostDot.length();
/*  746 */       if (trunc < xLength) {
/*  747 */         xDiscarded = xPostDot.substring(trunc);
/*  748 */         tempString = xDiscarded.substring(0, 1) + ".";
/*  749 */         if (xDiscarded.length() > 1) {
/*  750 */           tempString = tempString + xDiscarded.substring(1);
/*      */         }
/*      */         else {
/*  753 */           tempString = tempString + "0";
/*      */         }
/*  755 */         tempDouble = Math.round(Double.parseDouble(tempString));
/*      */         
/*  757 */         if (trunc > 0) {
/*  758 */           if (tempDouble >= 5.0D) {
/*  759 */             int[] xArray = new int[trunc + 1];
/*  760 */             xArray[0] = 0;
/*  761 */             for (int i = 0; i < trunc; i++) {
/*  762 */               xArray[(i + 1)] = Integer.parseInt(xPostDot.substring(i, i + 1));
/*      */             }
/*  764 */             boolean test = true;
/*  765 */             int iCounter = trunc;
/*  766 */             while (test) {
/*  767 */               xArray[iCounter] += 1;
/*  768 */               if (iCounter > 0) {
/*  769 */                 if (xArray[iCounter] < 10) {
/*  770 */                   test = false;
/*      */                 }
/*      */                 else {
/*  773 */                   xArray[iCounter] = 0;
/*  774 */                   iCounter--;
/*      */                 }
/*      */               }
/*      */               else {
/*  778 */                 test = false;
/*      */               }
/*      */             }
/*  781 */             int preInt = Integer.parseInt(xPreDot);
/*  782 */             preInt += xArray[0];
/*  783 */             xPreDot = new Integer(preInt).toString();
/*  784 */             tempString = "";
/*  785 */             for (int i = 1; i <= trunc; i++) {
/*  786 */               tempString = tempString + new Integer(xArray[i]).toString();
/*      */             }
/*  788 */             xPostDot = tempString;
/*      */           }
/*      */           else {
/*  791 */             xPostDot = xPostDot.substring(0, trunc);
/*      */           }
/*      */         }
/*      */         else {
/*  795 */           if (tempDouble >= 5.0D) {
/*  796 */             int preInt = Integer.parseInt(xPreDot);
/*  797 */             preInt++;
/*  798 */             xPreDot = new Integer(preInt).toString();
/*      */           }
/*  800 */           xPostDot = "0";
/*      */         }
/*      */       }
/*  803 */       xTruncated = first + xPreDot.trim() + "." + xPostDot.trim() + exponent;
/*      */     }
/*  805 */     return xTruncated.trim();
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isInfinity(double x)
/*      */   {
/*  811 */     boolean test = false;
/*  812 */     if ((x == Double.POSITIVE_INFINITY) || (x == Double.NEGATIVE_INFINITY)) test = true;
/*  813 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isInfinity(float x)
/*      */   {
/*  819 */     boolean test = false;
/*  820 */     if ((x == Float.POSITIVE_INFINITY) || (x == Float.NEGATIVE_INFINITY)) test = true;
/*  821 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isPlusInfinity(double x)
/*      */   {
/*  827 */     boolean test = false;
/*  828 */     if (x == Double.POSITIVE_INFINITY) test = true;
/*  829 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isPlusInfinity(float x)
/*      */   {
/*  835 */     boolean test = false;
/*  836 */     if (x == Float.POSITIVE_INFINITY) test = true;
/*  837 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isMinusInfinity(double x)
/*      */   {
/*  843 */     boolean test = false;
/*  844 */     if (x == Double.NEGATIVE_INFINITY) test = true;
/*  845 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isMinusInfinity(float x)
/*      */   {
/*  851 */     boolean test = false;
/*  852 */     if (x == Float.NEGATIVE_INFINITY) test = true;
/*  853 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isNaN(double x)
/*      */   {
/*  860 */     boolean test = false;
/*  861 */     if (x != x) test = true;
/*  862 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isNaN(float x)
/*      */   {
/*  868 */     boolean test = false;
/*  869 */     if (x != x) test = true;
/*  870 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEqual(double x, double y)
/*      */   {
/*  878 */     boolean test = false;
/*  879 */     if (isNaN(x)) {
/*  880 */       if (isNaN(y)) { test = true;
/*      */       }
/*      */     }
/*  883 */     else if (isPlusInfinity(x)) {
/*  884 */       if (isPlusInfinity(y)) { test = true;
/*      */       }
/*      */     }
/*  887 */     else if (isMinusInfinity(x)) {
/*  888 */       if (isMinusInfinity(y)) { test = true;
/*      */       }
/*      */     }
/*  891 */     else if (x == y) { test = true;
/*      */     }
/*      */     
/*      */ 
/*  895 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEqual(float x, float y)
/*      */   {
/*  903 */     boolean test = false;
/*  904 */     if (isNaN(x)) {
/*  905 */       if (isNaN(y)) { test = true;
/*      */       }
/*      */     }
/*  908 */     else if (isPlusInfinity(x)) {
/*  909 */       if (isPlusInfinity(y)) { test = true;
/*      */       }
/*      */     }
/*  912 */     else if (isMinusInfinity(x)) {
/*  913 */       if (isMinusInfinity(y)) { test = true;
/*      */       }
/*      */     }
/*  916 */     else if (x == y) { test = true;
/*      */     }
/*      */     
/*      */ 
/*  920 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqual(int x, int y)
/*      */   {
/*  926 */     boolean test = false;
/*  927 */     if (x == y) test = true;
/*  928 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqual(char x, char y)
/*      */   {
/*  934 */     boolean test = false;
/*  935 */     if (x == y) test = true;
/*  936 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqual(String x, String y)
/*      */   {
/*  942 */     boolean test = false;
/*  943 */     if (x.equals(y)) test = true;
/*  944 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isEqualWithinLimits(double x, double y, double limit)
/*      */   {
/*  951 */     boolean test = false;
/*  952 */     if (Math.abs(x - y) <= Math.abs(limit)) test = true;
/*  953 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinLimits(float x, float y, float limit)
/*      */   {
/*  959 */     boolean test = false;
/*  960 */     if (Math.abs(x - y) <= Math.abs(limit)) test = true;
/*  961 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinLimits(long x, long y, long limit)
/*      */   {
/*  967 */     boolean test = false;
/*  968 */     if (Math.abs(x - y) <= Math.abs(limit)) test = true;
/*  969 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinLimits(int x, int y, int limit)
/*      */   {
/*  975 */     boolean test = false;
/*  976 */     if (Math.abs(x - y) <= Math.abs(limit)) test = true;
/*  977 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinLimits(BigDecimal x, BigDecimal y, BigDecimal limit)
/*      */   {
/*  983 */     boolean test = false;
/*  984 */     if (x.subtract(y).abs().compareTo(limit.abs()) <= 0) test = true;
/*  985 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinLimits(BigInteger x, BigInteger y, BigInteger limit)
/*      */   {
/*  991 */     boolean test = false;
/*  992 */     if (x.subtract(y).abs().compareTo(limit.abs()) <= 0) test = true;
/*  993 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(double x, double y, double perCent)
/*      */   {
/* 1001 */     boolean test = false;
/* 1002 */     double limit = Math.abs((x + y) * perCent / 200.0D);
/* 1003 */     if (Math.abs(x - y) <= limit) test = true;
/* 1004 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(float x, float y, float perCent)
/*      */   {
/* 1010 */     boolean test = false;
/* 1011 */     double limit = Math.abs((x + y) * perCent / 200.0F);
/* 1012 */     if (Math.abs(x - y) <= limit) test = true;
/* 1013 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(long x, long y, double perCent)
/*      */   {
/* 1019 */     boolean test = false;
/* 1020 */     double limit = Math.abs((x + y) * perCent / 200.0D);
/* 1021 */     if (Math.abs(x - y) <= limit) test = true;
/* 1022 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(long x, long y, long perCent)
/*      */   {
/* 1028 */     boolean test = false;
/* 1029 */     double limit = Math.abs((x + y) * perCent / 200.0D);
/* 1030 */     if (Math.abs(x - y) <= limit) test = true;
/* 1031 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(int x, int y, double perCent)
/*      */   {
/* 1037 */     boolean test = false;
/* 1038 */     double limit = Math.abs((x + y) * perCent / 200.0D);
/* 1039 */     if (Math.abs(x - y) <= limit) test = true;
/* 1040 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(int x, int y, int perCent)
/*      */   {
/* 1046 */     boolean test = false;
/* 1047 */     double limit = Math.abs((x + y) * perCent / 200.0D);
/* 1048 */     if (Math.abs(x - y) <= limit) test = true;
/* 1049 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(BigDecimal x, BigDecimal y, BigDecimal perCent)
/*      */   {
/* 1055 */     boolean test = false;
/* 1056 */     BigDecimal limit = x.add(y).multiply(perCent).multiply(new BigDecimal("0.005"));
/* 1057 */     if (x.subtract(y).abs().compareTo(limit.abs()) <= 0) test = true;
/* 1058 */     limit = null;
/* 1059 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(BigInteger x, BigInteger y, BigDecimal perCent)
/*      */   {
/* 1065 */     boolean test = false;
/* 1066 */     BigDecimal xx = new BigDecimal(x);
/* 1067 */     BigDecimal yy = new BigDecimal(y);
/* 1068 */     BigDecimal limit = xx.add(yy).multiply(perCent).multiply(new BigDecimal("0.005"));
/* 1069 */     if (xx.subtract(yy).abs().compareTo(limit.abs()) <= 0) test = true;
/* 1070 */     limit = null;
/* 1071 */     xx = null;
/* 1072 */     yy = null;
/* 1073 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEqualWithinPerCent(BigInteger x, BigInteger y, BigInteger perCent)
/*      */   {
/* 1079 */     boolean test = false;
/* 1080 */     BigDecimal xx = new BigDecimal(x);
/* 1081 */     BigDecimal yy = new BigDecimal(y);
/* 1082 */     BigDecimal pc = new BigDecimal(perCent);
/* 1083 */     BigDecimal limit = xx.add(yy).multiply(pc).multiply(new BigDecimal("0.005"));
/* 1084 */     if (xx.subtract(yy).abs().compareTo(limit.abs()) <= 0) test = true;
/* 1085 */     limit = null;
/* 1086 */     xx = null;
/* 1087 */     yy = null;
/* 1088 */     pc = null;
/* 1089 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(double x, double y)
/*      */   {
/* 1098 */     Double X = new Double(x);
/* 1099 */     Double Y = new Double(y);
/* 1100 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(int x, int y)
/*      */   {
/* 1108 */     Integer X = new Integer(x);
/* 1109 */     Integer Y = new Integer(y);
/* 1110 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(long x, long y)
/*      */   {
/* 1118 */     Long X = new Long(x);
/* 1119 */     Long Y = new Long(y);
/* 1120 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(float x, float y)
/*      */   {
/* 1128 */     Float X = new Float(x);
/* 1129 */     Float Y = new Float(y);
/* 1130 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(byte x, byte y)
/*      */   {
/* 1138 */     Byte X = new Byte(x);
/* 1139 */     Byte Y = new Byte(y);
/* 1140 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int compare(short x, short y)
/*      */   {
/* 1148 */     Short X = new Short(x);
/* 1149 */     Short Y = new Short(y);
/* 1150 */     return X.compareTo(Y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isInteger(double x)
/*      */   {
/* 1157 */     boolean retn = false;
/* 1158 */     double xfloor = Math.floor(x);
/* 1159 */     if (x - xfloor == 0.0D) retn = true;
/* 1160 */     return retn;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isInteger(double[] x)
/*      */   {
/* 1166 */     boolean retn = true;
/* 1167 */     boolean test = true;
/* 1168 */     int ii = 0;
/* 1169 */     while (test) {
/* 1170 */       double xfloor = Math.floor(x[ii]);
/* 1171 */       if (x[ii] - xfloor != 0.0D) {
/* 1172 */         retn = false;
/* 1173 */         test = false;
/*      */       }
/*      */       else {
/* 1176 */         ii++;
/* 1177 */         if (ii == x.length) test = false;
/*      */       }
/*      */     }
/* 1180 */     return retn;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isInteger(float x)
/*      */   {
/* 1186 */     boolean ret = false;
/* 1187 */     float xfloor = (float)Math.floor(x);
/* 1188 */     if (x - xfloor == 0.0F) ret = true;
/* 1189 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isInteger(float[] x)
/*      */   {
/* 1196 */     boolean retn = true;
/* 1197 */     boolean test = true;
/* 1198 */     int ii = 0;
/* 1199 */     while (test) {
/* 1200 */       float xfloor = (float)Math.floor(x[ii]);
/* 1201 */       if (x[ii] - xfloor != 0.0D) {
/* 1202 */         retn = false;
/* 1203 */         test = false;
/*      */       }
/*      */       else {
/* 1206 */         ii++;
/* 1207 */         if (ii == x.length) test = false;
/*      */       }
/*      */     }
/* 1210 */     return retn;
/*      */   }
/*      */   
/*      */   public static boolean isInteger(Number numberAsObject) {
/* 1214 */     boolean test = integers.containsKey(numberAsObject.getClass());
/* 1215 */     if (!test) {
/* 1216 */       if ((numberAsObject instanceof Double)) {
/* 1217 */         double dd = numberAsObject.doubleValue();
/* 1218 */         test = isInteger(dd);
/*      */       }
/* 1220 */       if ((numberAsObject instanceof Float)) {
/* 1221 */         float dd = numberAsObject.floatValue();
/* 1222 */         test = isInteger(dd);
/*      */       }
/* 1224 */       if ((numberAsObject instanceof BigDecimal)) {
/* 1225 */         double dd = numberAsObject.doubleValue();
/* 1226 */         test = isInteger(dd);
/*      */       }
/*      */     }
/* 1229 */     return test;
/*      */   }
/*      */   
/*      */   public static boolean isInteger(Number[] numberAsObject) {
/* 1233 */     boolean testall = true;
/* 1234 */     for (int i = 0; i < numberAsObject.length; i++) {
/* 1235 */       boolean test = integers.containsKey(numberAsObject[i].getClass());
/* 1236 */       if (!test) {
/* 1237 */         if ((numberAsObject[i] instanceof Double)) {
/* 1238 */           double dd = numberAsObject[i].doubleValue();
/* 1239 */           test = isInteger(dd);
/* 1240 */           if (!test) testall = false;
/*      */         }
/* 1242 */         if ((numberAsObject[i] instanceof Float)) {
/* 1243 */           float dd = numberAsObject[i].floatValue();
/* 1244 */           test = isInteger(dd);
/* 1245 */           if (!test) testall = false;
/*      */         }
/* 1247 */         if ((numberAsObject[i] instanceof BigDecimal)) {
/* 1248 */           double dd = numberAsObject[i].doubleValue();
/* 1249 */           test = isInteger(dd);
/* 1250 */           if (!test) testall = false;
/*      */         }
/*      */       }
/*      */     }
/* 1254 */     return testall;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isEven(int x)
/*      */   {
/* 1261 */     boolean test = false;
/* 1262 */     if (x % 2 == 0.0D) test = true;
/* 1263 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEven(float x)
/*      */   {
/* 1269 */     double y = Math.floor(x);
/* 1270 */     if (x - y != 0.0D) throw new IllegalArgumentException("the argument is not an integer");
/* 1271 */     boolean test = false;
/* 1272 */     y = Math.floor(x / 2.0F);
/* 1273 */     if (x / 2.0F - y == 0.0D) test = true;
/* 1274 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isEven(double x)
/*      */   {
/* 1280 */     double y = Math.floor(x);
/* 1281 */     if (x - y != 0.0D) throw new IllegalArgumentException("the argument is not an integer");
/* 1282 */     boolean test = false;
/* 1283 */     y = Math.floor(x / 2.0D);
/* 1284 */     if (x / 2.0D - y == 0.0D) test = true;
/* 1285 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isOdd(int x)
/*      */   {
/* 1292 */     boolean test = true;
/* 1293 */     if (x % 2 == 0.0D) test = false;
/* 1294 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isOdd(float x)
/*      */   {
/* 1300 */     double y = Math.floor(x);
/* 1301 */     if (x - y != 0.0D) throw new IllegalArgumentException("the argument is not an integer");
/* 1302 */     boolean test = true;
/* 1303 */     y = Math.floor(x / 2.0F);
/* 1304 */     if (x / 2.0F - y == 0.0D) test = false;
/* 1305 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isOdd(double x)
/*      */   {
/* 1311 */     double y = Math.floor(x);
/* 1312 */     if (x - y != 0.0D) throw new IllegalArgumentException("the argument is not an integer");
/* 1313 */     boolean test = true;
/* 1314 */     y = Math.floor(x / 2.0D);
/* 1315 */     if (x / 2.0D - y == 0.0D) test = false;
/* 1316 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean leapYear(int year)
/*      */   {
/* 1322 */     boolean test = false;
/*      */     
/* 1324 */     if (year % 4 != 0) {
/* 1325 */       test = false;
/*      */ 
/*      */     }
/* 1328 */     else if (year % 400 == 0) {
/* 1329 */       test = true;
/*      */ 
/*      */     }
/* 1332 */     else if (year % 100 == 0) {
/* 1333 */       test = false;
/*      */     }
/*      */     else {
/* 1336 */       test = true;
/*      */     }
/*      */     
/*      */ 
/* 1340 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static long dateToJavaMilliS(int year, int month, int day, int hour, int min, int sec)
/*      */   {
/* 1347 */     long[] monthDays = { 0L, 31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L };
/* 1348 */     long ms = 0L;
/*      */     
/* 1350 */     long yearDiff = 0L;
/* 1351 */     int yearTest = year - 1;
/* 1352 */     while (yearTest >= 1970) {
/* 1353 */       yearDiff += 365L;
/* 1354 */       if (leapYear(yearTest)) yearDiff += 1L;
/* 1355 */       yearTest--;
/*      */     }
/* 1357 */     yearDiff *= 86400000L;
/*      */     
/* 1359 */     long monthDiff = 0L;
/* 1360 */     int monthTest = month - 1;
/* 1361 */     while (monthTest > 0) {
/* 1362 */       monthDiff += monthDays[monthTest];
/* 1363 */       if (leapYear(year)) monthDiff += 1L;
/* 1364 */       monthTest--;
/*      */     }
/*      */     
/* 1367 */     monthDiff *= 86400000L;
/*      */     
/* 1369 */     ms = yearDiff + monthDiff + day * 24L * 60L * 60L * 1000L + hour * 60L * 60L * 1000L + min * 60L * 1000L + sec * 1000L;
/*      */     
/* 1371 */     return ms;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double maximum(double[] aa)
/*      */   {
/* 1380 */     int n = aa.length;
/* 1381 */     double aamax = aa[0];
/* 1382 */     for (int i = 1; i < n; i++) {
/* 1383 */       if (aa[i] > aamax) aamax = aa[i];
/*      */     }
/* 1385 */     return aamax;
/*      */   }
/*      */   
/*      */   public static float maximum(float[] aa)
/*      */   {
/* 1390 */     int n = aa.length;
/* 1391 */     float aamax = aa[0];
/* 1392 */     for (int i = 1; i < n; i++) {
/* 1393 */       if (aa[i] > aamax) aamax = aa[i];
/*      */     }
/* 1395 */     return aamax;
/*      */   }
/*      */   
/*      */   public static int maximum(int[] aa)
/*      */   {
/* 1400 */     int n = aa.length;
/* 1401 */     int aamax = aa[0];
/* 1402 */     for (int i = 1; i < n; i++) {
/* 1403 */       if (aa[i] > aamax) aamax = aa[i];
/*      */     }
/* 1405 */     return aamax;
/*      */   }
/*      */   
/*      */   public static long maximum(long[] aa)
/*      */   {
/* 1410 */     long n = aa.length;
/* 1411 */     long aamax = aa[0];
/* 1412 */     for (int i = 1; i < n; i++) {
/* 1413 */       if (aa[i] > aamax) aamax = aa[i];
/*      */     }
/* 1415 */     return aamax;
/*      */   }
/*      */   
/*      */   public static double minimum(double[] aa)
/*      */   {
/* 1420 */     int n = aa.length;
/* 1421 */     double aamin = aa[0];
/* 1422 */     for (int i = 1; i < n; i++) {
/* 1423 */       if (aa[i] < aamin) aamin = aa[i];
/*      */     }
/* 1425 */     return aamin;
/*      */   }
/*      */   
/*      */   public static float minimum(float[] aa)
/*      */   {
/* 1430 */     int n = aa.length;
/* 1431 */     float aamin = aa[0];
/* 1432 */     for (int i = 1; i < n; i++) {
/* 1433 */       if (aa[i] < aamin) aamin = aa[i];
/*      */     }
/* 1435 */     return aamin;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int minimum(int[] aa)
/*      */   {
/* 1441 */     int n = aa.length;
/* 1442 */     int aamin = aa[0];
/* 1443 */     for (int i = 1; i < n; i++) {
/* 1444 */       if (aa[i] < aamin) aamin = aa[i];
/*      */     }
/* 1446 */     return aamin;
/*      */   }
/*      */   
/*      */   public static long minimum(long[] aa)
/*      */   {
/* 1451 */     long n = aa.length;
/* 1452 */     long aamin = aa[0];
/* 1453 */     for (int i = 1; i < n; i++) {
/* 1454 */       if (aa[i] < aamin) aamin = aa[i];
/*      */     }
/* 1456 */     return aamin;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double maximumDifference(double[] aa)
/*      */   {
/* 1462 */     return maximum(aa) - minimum(aa);
/*      */   }
/*      */   
/*      */   public static float maximumDifference(float[] aa)
/*      */   {
/* 1467 */     return maximum(aa) - minimum(aa);
/*      */   }
/*      */   
/*      */   public static long maximumDifference(long[] aa)
/*      */   {
/* 1472 */     return maximum(aa) - minimum(aa);
/*      */   }
/*      */   
/*      */   public static int maximumDifference(int[] aa)
/*      */   {
/* 1477 */     return maximum(aa) - minimum(aa);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double minimumDifference(double[] aa)
/*      */   {
/* 1484 */     double[] sorted = selectionSort(aa);
/* 1485 */     double n = aa.length;
/* 1486 */     double diff = sorted[1] - sorted[0];
/* 1487 */     double minDiff = diff;
/* 1488 */     for (int i = 1; i < n - 1.0D; i++) {
/* 1489 */       diff = sorted[(i + 1)] - sorted[i];
/* 1490 */       if (diff < minDiff) minDiff = diff;
/*      */     }
/* 1492 */     return minDiff;
/*      */   }
/*      */   
/*      */   public static float minimumDifference(float[] aa)
/*      */   {
/* 1497 */     float[] sorted = selectionSort(aa);
/* 1498 */     float n = aa.length;
/* 1499 */     float diff = sorted[1] - sorted[0];
/* 1500 */     float minDiff = diff;
/* 1501 */     for (int i = 1; i < n - 1.0F; i++) {
/* 1502 */       diff = sorted[(i + 1)] - sorted[i];
/* 1503 */       if (diff < minDiff) minDiff = diff;
/*      */     }
/* 1505 */     return minDiff;
/*      */   }
/*      */   
/*      */   public static long minimumDifference(long[] aa)
/*      */   {
/* 1510 */     long[] sorted = selectionSort(aa);
/* 1511 */     long n = aa.length;
/* 1512 */     long diff = sorted[1] - sorted[0];
/* 1513 */     long minDiff = diff;
/* 1514 */     for (int i = 1; i < n - 1L; i++) {
/* 1515 */       diff = sorted[(i + 1)] - sorted[i];
/* 1516 */       if (diff < minDiff) minDiff = diff;
/*      */     }
/* 1518 */     return minDiff;
/*      */   }
/*      */   
/*      */   public static int minimumDifference(int[] aa)
/*      */   {
/* 1523 */     int[] sorted = selectionSort(aa);
/* 1524 */     int n = aa.length;
/* 1525 */     int diff = sorted[1] - sorted[0];
/* 1526 */     int minDiff = diff;
/* 1527 */     for (int i = 1; i < n - 1; i++) {
/* 1528 */       diff = sorted[(i + 1)] - sorted[i];
/* 1529 */       if (diff < minDiff) minDiff = diff;
/*      */     }
/* 1531 */     return minDiff;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] reverseArray(double[] aa)
/*      */   {
/* 1537 */     int n = aa.length;
/* 1538 */     double[] bb = new double[n];
/* 1539 */     for (int i = 0; i < n; i++) {
/* 1540 */       bb[i] = aa[(n - 1 - i)];
/*      */     }
/* 1542 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] reverseArray(float[] aa)
/*      */   {
/* 1547 */     int n = aa.length;
/* 1548 */     float[] bb = new float[n];
/* 1549 */     for (int i = 0; i < n; i++) {
/* 1550 */       bb[i] = aa[(n - 1 - i)];
/*      */     }
/* 1552 */     return bb;
/*      */   }
/*      */   
/*      */   public static int[] reverseArray(int[] aa)
/*      */   {
/* 1557 */     int n = aa.length;
/* 1558 */     int[] bb = new int[n];
/* 1559 */     for (int i = 0; i < n; i++) {
/* 1560 */       bb[i] = aa[(n - 1 - i)];
/*      */     }
/* 1562 */     return bb;
/*      */   }
/*      */   
/*      */   public static long[] reverseArray(long[] aa)
/*      */   {
/* 1567 */     int n = aa.length;
/* 1568 */     long[] bb = new long[n];
/* 1569 */     for (int i = 0; i < n; i++) {
/* 1570 */       bb[i] = aa[(n - 1 - i)];
/*      */     }
/* 1572 */     return bb;
/*      */   }
/*      */   
/*      */   public static char[] reverseArray(char[] aa)
/*      */   {
/* 1577 */     int n = aa.length;
/* 1578 */     char[] bb = new char[n];
/* 1579 */     for (int i = 0; i < n; i++) {
/* 1580 */       bb[i] = aa[(n - 1 - i)];
/*      */     }
/* 1582 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] arrayAbs(double[] aa)
/*      */   {
/* 1588 */     int n = aa.length;
/* 1589 */     double[] bb = new double[n];
/* 1590 */     for (int i = 0; i < n; i++) {
/* 1591 */       bb[i] = Math.abs(aa[i]);
/*      */     }
/* 1593 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] arrayAbs(float[] aa)
/*      */   {
/* 1598 */     int n = aa.length;
/* 1599 */     float[] bb = new float[n];
/* 1600 */     for (int i = 0; i < n; i++) {
/* 1601 */       bb[i] = Math.abs(aa[i]);
/*      */     }
/* 1603 */     return bb;
/*      */   }
/*      */   
/*      */   public static long[] arrayAbs(long[] aa)
/*      */   {
/* 1608 */     int n = aa.length;
/* 1609 */     long[] bb = new long[n];
/* 1610 */     for (int i = 0; i < n; i++) {
/* 1611 */       bb[i] = Math.abs(aa[i]);
/*      */     }
/* 1613 */     return bb;
/*      */   }
/*      */   
/*      */   public static int[] arrayAbs(int[] aa)
/*      */   {
/* 1618 */     int n = aa.length;
/* 1619 */     int[] bb = new int[n];
/* 1620 */     for (int i = 0; i < n; i++) {
/* 1621 */       bb[i] = Math.abs(aa[i]);
/*      */     }
/* 1623 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] arrayMultByConstant(double[] aa, double constant)
/*      */   {
/* 1629 */     int n = aa.length;
/* 1630 */     double[] bb = new double[n];
/* 1631 */     for (int i = 0; i < n; i++) {
/* 1632 */       aa[i] *= constant;
/*      */     }
/* 1634 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] arrayMultByConstant(int[] aa, double constant)
/*      */   {
/* 1639 */     int n = aa.length;
/* 1640 */     double[] bb = new double[n];
/* 1641 */     for (int i = 0; i < n; i++) {
/* 1642 */       bb[i] = (aa[i] * constant);
/*      */     }
/* 1644 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] arrayMultByConstant(double[] aa, int constant) {
/* 1648 */     int n = aa.length;
/* 1649 */     double[] bb = new double[n];
/* 1650 */     for (int i = 0; i < n; i++) {
/* 1651 */       aa[i] *= constant;
/*      */     }
/* 1653 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] arrayMultByConstant(int[] aa, int constant)
/*      */   {
/* 1658 */     int n = aa.length;
/* 1659 */     double[] bb = new double[n];
/* 1660 */     for (int i = 0; i < n; i++) {
/* 1661 */       bb[i] = (aa[i] * constant);
/*      */     }
/* 1663 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] log10Elements(double[] aa)
/*      */   {
/* 1669 */     int n = aa.length;
/* 1670 */     double[] bb = new double[n];
/* 1671 */     for (int i = 0; i < n; i++) bb[i] = Math.log10(aa[i]);
/* 1672 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] log10Elements(float[] aa)
/*      */   {
/* 1677 */     int n = aa.length;
/* 1678 */     float[] bb = new float[n];
/* 1679 */     for (int i = 0; i < n; i++) bb[i] = ((float)Math.log10(aa[i]));
/* 1680 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] lnElements(double[] aa)
/*      */   {
/* 1686 */     int n = aa.length;
/* 1687 */     double[] bb = new double[n];
/* 1688 */     for (int i = 0; i < n; i++) bb[i] = Math.log10(aa[i]);
/* 1689 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] lnElements(float[] aa)
/*      */   {
/* 1694 */     int n = aa.length;
/* 1695 */     float[] bb = new float[n];
/* 1696 */     for (int i = 0; i < n; i++) bb[i] = ((float)Math.log10(aa[i]));
/* 1697 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] squareRootElements(double[] aa)
/*      */   {
/* 1703 */     int n = aa.length;
/* 1704 */     double[] bb = new double[n];
/* 1705 */     for (int i = 0; i < n; i++) bb[i] = Math.sqrt(aa[i]);
/* 1706 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] squareRootElements(float[] aa)
/*      */   {
/* 1711 */     int n = aa.length;
/* 1712 */     float[] bb = new float[n];
/* 1713 */     for (int i = 0; i < n; i++) bb[i] = ((float)Math.sqrt(aa[i]));
/* 1714 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] raiseElementsToPower(double[] aa, double power)
/*      */   {
/* 1720 */     int n = aa.length;
/* 1721 */     double[] bb = new double[n];
/* 1722 */     for (int i = 0; i < n; i++) bb[i] = Math.pow(aa[i], power);
/* 1723 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] raiseElementsToPower(double[] aa, int power)
/*      */   {
/* 1728 */     int n = aa.length;
/* 1729 */     double[] bb = new double[n];
/* 1730 */     for (int i = 0; i < n; i++) bb[i] = Math.pow(aa[i], power);
/* 1731 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] raiseElementsToPower(float[] aa, float power)
/*      */   {
/* 1736 */     int n = aa.length;
/* 1737 */     float[] bb = new float[n];
/* 1738 */     for (int i = 0; i < n; i++) bb[i] = ((float)Math.pow(aa[i], power));
/* 1739 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] raiseElementsToPower(float[] aa, int power)
/*      */   {
/* 1744 */     int n = aa.length;
/* 1745 */     float[] bb = new float[n];
/* 1746 */     for (int i = 0; i < n; i++) bb[i] = ((float)Math.pow(aa[i], power));
/* 1747 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] invertElements(double[] aa)
/*      */   {
/* 1753 */     int n = aa.length;
/* 1754 */     double[] bb = new double[n];
/* 1755 */     for (int i = 0; i < n; i++) bb[i] = (1.0D / aa[i]);
/* 1756 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] invertElements(float[] aa)
/*      */   {
/* 1761 */     int n = aa.length;
/* 1762 */     float[] bb = new float[n];
/* 1763 */     for (int i = 0; i < n; i++) bb[i] = (1.0F / aa[i]);
/* 1764 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int[] indicesOf(double[] array, double value)
/*      */   {
/* 1772 */     int[] indices = null;
/* 1773 */     int numberOfIndices = 0;
/* 1774 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1775 */     for (int i = 0; i < array.length; i++) {
/* 1776 */       if (array[i] == value) {
/* 1777 */         numberOfIndices++;
/* 1778 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1781 */     if (numberOfIndices != 0) {
/* 1782 */       indices = new int[numberOfIndices];
/* 1783 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1784 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1787 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(float[] array, float value)
/*      */   {
/* 1793 */     int[] indices = null;
/* 1794 */     int numberOfIndices = 0;
/* 1795 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1796 */     for (int i = 0; i < array.length; i++) {
/* 1797 */       if (array[i] == value) {
/* 1798 */         numberOfIndices++;
/* 1799 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1802 */     if (numberOfIndices != 0) {
/* 1803 */       indices = new int[numberOfIndices];
/* 1804 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1805 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1808 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(long[] array, long value)
/*      */   {
/* 1814 */     int[] indices = null;
/* 1815 */     int numberOfIndices = 0;
/* 1816 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1817 */     for (int i = 0; i < array.length; i++) {
/* 1818 */       if (array[i] == value) {
/* 1819 */         numberOfIndices++;
/* 1820 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1823 */     if (numberOfIndices != 0) {
/* 1824 */       indices = new int[numberOfIndices];
/* 1825 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1826 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1829 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(int[] array, int value)
/*      */   {
/* 1835 */     int[] indices = null;
/* 1836 */     int numberOfIndices = 0;
/* 1837 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1838 */     for (int i = 0; i < array.length; i++) {
/* 1839 */       if (array[i] == value) {
/* 1840 */         numberOfIndices++;
/* 1841 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1844 */     if (numberOfIndices != 0) {
/* 1845 */       indices = new int[numberOfIndices];
/* 1846 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1847 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1850 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(short[] array, short value)
/*      */   {
/* 1856 */     int[] indices = null;
/* 1857 */     int numberOfIndices = 0;
/* 1858 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1859 */     for (int i = 0; i < array.length; i++) {
/* 1860 */       if (array[i] == value) {
/* 1861 */         numberOfIndices++;
/* 1862 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1865 */     if (numberOfIndices != 0) {
/* 1866 */       indices = new int[numberOfIndices];
/* 1867 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1868 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1871 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(byte[] array, byte value)
/*      */   {
/* 1877 */     int[] indices = null;
/* 1878 */     int numberOfIndices = 0;
/* 1879 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1880 */     for (int i = 0; i < array.length; i++) {
/* 1881 */       if (array[i] == value) {
/* 1882 */         numberOfIndices++;
/* 1883 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1886 */     if (numberOfIndices != 0) {
/* 1887 */       indices = new int[numberOfIndices];
/* 1888 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1889 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1892 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(char[] array, char value)
/*      */   {
/* 1898 */     int[] indices = null;
/* 1899 */     int numberOfIndices = 0;
/* 1900 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1901 */     for (int i = 0; i < array.length; i++) {
/* 1902 */       if (array[i] == value) {
/* 1903 */         numberOfIndices++;
/* 1904 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1907 */     if (numberOfIndices != 0) {
/* 1908 */       indices = new int[numberOfIndices];
/* 1909 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1910 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1913 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(String[] array, String value)
/*      */   {
/* 1919 */     int[] indices = null;
/* 1920 */     int numberOfIndices = 0;
/* 1921 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1922 */     for (int i = 0; i < array.length; i++) {
/* 1923 */       if (array[i].equals(value)) {
/* 1924 */         numberOfIndices++;
/* 1925 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1928 */     if (numberOfIndices != 0) {
/* 1929 */       indices = new int[numberOfIndices];
/* 1930 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1931 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1934 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] indicesOf(Object[] array, Object value)
/*      */   {
/* 1940 */     int[] indices = null;
/* 1941 */     int numberOfIndices = 0;
/* 1942 */     ArrayList<Integer> arrayl = new ArrayList();
/* 1943 */     for (int i = 0; i < array.length; i++) {
/* 1944 */       if (array[i].equals(value)) {
/* 1945 */         numberOfIndices++;
/* 1946 */         arrayl.add(new Integer(i));
/*      */       }
/*      */     }
/* 1949 */     if (numberOfIndices != 0) {
/* 1950 */       indices = new int[numberOfIndices];
/* 1951 */       for (int i = 0; i < numberOfIndices; i++) {
/* 1952 */         indices[i] = ((Integer)arrayl.get(i)).intValue();
/*      */       }
/*      */     }
/* 1955 */     return indices;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int indexOf(double[] array, double value)
/*      */   {
/* 1962 */     int index = -1;
/* 1963 */     boolean test = true;
/* 1964 */     int counter = 0;
/* 1965 */     while (test) {
/* 1966 */       if (array[counter] == value) {
/* 1967 */         index = counter;
/* 1968 */         test = false;
/*      */       }
/*      */       else {
/* 1971 */         counter++;
/* 1972 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 1975 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(float[] array, float value)
/*      */   {
/* 1981 */     int index = -1;
/* 1982 */     boolean test = true;
/* 1983 */     int counter = 0;
/* 1984 */     while (test) {
/* 1985 */       if (array[counter] == value) {
/* 1986 */         index = counter;
/* 1987 */         test = false;
/*      */       }
/*      */       else {
/* 1990 */         counter++;
/* 1991 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 1994 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(long[] array, long value)
/*      */   {
/* 2000 */     int index = -1;
/* 2001 */     boolean test = true;
/* 2002 */     int counter = 0;
/* 2003 */     while (test) {
/* 2004 */       if (array[counter] == value) {
/* 2005 */         index = counter;
/* 2006 */         test = false;
/*      */       }
/*      */       else {
/* 2009 */         counter++;
/* 2010 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2013 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(int[] array, int value)
/*      */   {
/* 2019 */     int index = -1;
/* 2020 */     boolean test = true;
/* 2021 */     int counter = 0;
/* 2022 */     while (test) {
/* 2023 */       if (array[counter] == value) {
/* 2024 */         index = counter;
/* 2025 */         test = false;
/*      */       }
/*      */       else {
/* 2028 */         counter++;
/* 2029 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2032 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(byte[] array, byte value)
/*      */   {
/* 2038 */     int index = -1;
/* 2039 */     boolean test = true;
/* 2040 */     int counter = 0;
/* 2041 */     while (test) {
/* 2042 */       if (array[counter] == value) {
/* 2043 */         index = counter;
/* 2044 */         test = false;
/*      */       }
/*      */       else {
/* 2047 */         counter++;
/* 2048 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2051 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(short[] array, short value)
/*      */   {
/* 2057 */     int index = -1;
/* 2058 */     boolean test = true;
/* 2059 */     int counter = 0;
/* 2060 */     while (test) {
/* 2061 */       if (array[counter] == value) {
/* 2062 */         index = counter;
/* 2063 */         test = false;
/*      */       }
/*      */       else {
/* 2066 */         counter++;
/* 2067 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2070 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(char[] array, char value)
/*      */   {
/* 2076 */     int index = -1;
/* 2077 */     boolean test = true;
/* 2078 */     int counter = 0;
/* 2079 */     while (test) {
/* 2080 */       if (array[counter] == value) {
/* 2081 */         index = counter;
/* 2082 */         test = false;
/*      */       }
/*      */       else {
/* 2085 */         counter++;
/* 2086 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2089 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(String[] array, String value)
/*      */   {
/* 2095 */     int index = -1;
/* 2096 */     boolean test = true;
/* 2097 */     int counter = 0;
/* 2098 */     while (test) {
/* 2099 */       if (array[counter].equals(value)) {
/* 2100 */         index = counter;
/* 2101 */         test = false;
/*      */       }
/*      */       else {
/* 2104 */         counter++;
/* 2105 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2108 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int indexOf(Object[] array, Object value)
/*      */   {
/* 2114 */     int index = -1;
/* 2115 */     boolean test = true;
/* 2116 */     int counter = 0;
/* 2117 */     while (test) {
/* 2118 */       if (array[counter].equals(value)) {
/* 2119 */         index = counter;
/* 2120 */         test = false;
/*      */       }
/*      */       else {
/* 2123 */         counter++;
/* 2124 */         if (counter >= array.length) test = false;
/*      */       }
/*      */     }
/* 2127 */     return index;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double nearestElementValue(double[] array, double value)
/*      */   {
/* 2133 */     double diff = Math.abs(array[0] - value);
/* 2134 */     double nearest = array[0];
/* 2135 */     for (int i = 1; i < array.length; i++) {
/* 2136 */       if (Math.abs(array[i] - value) < diff) {
/* 2137 */         diff = Math.abs(array[i] - value);
/* 2138 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2141 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestElementIndex(double[] array, double value)
/*      */   {
/* 2146 */     double diff = Math.abs(array[0] - value);
/* 2147 */     int nearest = 0;
/* 2148 */     for (int i = 1; i < array.length; i++) {
/* 2149 */       if (Math.abs(array[i] - value) < diff) {
/* 2150 */         diff = Math.abs(array[i] - value);
/* 2151 */         nearest = i;
/*      */       }
/*      */     }
/* 2154 */     return nearest;
/*      */   }
/*      */   
/*      */   public static double nearestLowerElementValue(double[] array, double value)
/*      */   {
/* 2159 */     double diff0 = 0.0D;
/* 2160 */     double diff1 = 0.0D;
/* 2161 */     double nearest = 0.0D;
/* 2162 */     int ii = 0;
/* 2163 */     boolean test = true;
/* 2164 */     double min = array[0];
/* 2165 */     while (test) {
/* 2166 */       if (array[ii] < min) min = array[ii];
/* 2167 */       if (value - array[ii] >= 0.0D) {
/* 2168 */         diff0 = value - array[ii];
/* 2169 */         nearest = array[ii];
/* 2170 */         test = false;
/*      */       }
/*      */       else {
/* 2173 */         ii++;
/* 2174 */         if (ii > array.length - 1) {
/* 2175 */           nearest = min;
/* 2176 */           diff0 = min - value;
/* 2177 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2181 */     for (int i = 0; i < array.length; i++) {
/* 2182 */       diff1 = value - array[i];
/* 2183 */       if ((diff1 >= 0.0D) && (diff1 < diff0)) {
/* 2184 */         diff0 = diff1;
/* 2185 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2188 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestLowerElementIndex(double[] array, double value)
/*      */   {
/* 2193 */     double diff0 = 0.0D;
/* 2194 */     double diff1 = 0.0D;
/* 2195 */     int nearest = 0;
/* 2196 */     int ii = 0;
/* 2197 */     boolean test = true;
/* 2198 */     double min = array[0];
/* 2199 */     int minI = 0;
/* 2200 */     while (test) {
/* 2201 */       if (array[ii] < min) {
/* 2202 */         min = array[ii];
/* 2203 */         minI = ii;
/*      */       }
/* 2205 */       if (value - array[ii] >= 0.0D) {
/* 2206 */         diff0 = value - array[ii];
/* 2207 */         nearest = ii;
/* 2208 */         test = false;
/*      */       }
/*      */       else {
/* 2211 */         ii++;
/* 2212 */         if (ii > array.length - 1) {
/* 2213 */           nearest = minI;
/* 2214 */           diff0 = min - value;
/* 2215 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2219 */     for (int i = 0; i < array.length; i++) {
/* 2220 */       diff1 = value - array[i];
/* 2221 */       if ((diff1 >= 0.0D) && (diff1 < diff0)) {
/* 2222 */         diff0 = diff1;
/* 2223 */         nearest = i;
/*      */       }
/*      */     }
/* 2226 */     return nearest;
/*      */   }
/*      */   
/*      */   public static double nearestHigherElementValue(double[] array, double value)
/*      */   {
/* 2231 */     double diff0 = 0.0D;
/* 2232 */     double diff1 = 0.0D;
/* 2233 */     double nearest = 0.0D;
/* 2234 */     int ii = 0;
/* 2235 */     boolean test = true;
/* 2236 */     double max = array[0];
/* 2237 */     while (test) {
/* 2238 */       if (array[ii] > max) max = array[ii];
/* 2239 */       if (array[ii] - value >= 0.0D) {
/* 2240 */         diff0 = value - array[ii];
/* 2241 */         nearest = array[ii];
/* 2242 */         test = false;
/*      */       }
/*      */       else {
/* 2245 */         ii++;
/* 2246 */         if (ii > array.length - 1) {
/* 2247 */           nearest = max;
/* 2248 */           diff0 = value - max;
/* 2249 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2253 */     for (int i = 0; i < array.length; i++) {
/* 2254 */       diff1 = array[i] - value;
/* 2255 */       if ((diff1 >= 0.0D) && (diff1 < diff0)) {
/* 2256 */         diff0 = diff1;
/* 2257 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2260 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestHigherElementIndex(double[] array, double value)
/*      */   {
/* 2265 */     double diff0 = 0.0D;
/* 2266 */     double diff1 = 0.0D;
/* 2267 */     int nearest = 0;
/* 2268 */     int ii = 0;
/* 2269 */     boolean test = true;
/* 2270 */     double max = array[0];
/* 2271 */     int maxI = 0;
/* 2272 */     while (test) {
/* 2273 */       if (array[ii] > max) {
/* 2274 */         max = array[ii];
/* 2275 */         maxI = ii;
/*      */       }
/* 2277 */       if (array[ii] - value >= 0.0D) {
/* 2278 */         diff0 = value - array[ii];
/* 2279 */         nearest = ii;
/* 2280 */         test = false;
/*      */       }
/*      */       else {
/* 2283 */         ii++;
/* 2284 */         if (ii > array.length - 1) {
/* 2285 */           nearest = maxI;
/* 2286 */           diff0 = value - max;
/* 2287 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2291 */     for (int i = 0; i < array.length; i++) {
/* 2292 */       diff1 = array[i] - value;
/* 2293 */       if ((diff1 >= 0.0D) && (diff1 < diff0)) {
/* 2294 */         diff0 = diff1;
/* 2295 */         nearest = i;
/*      */       }
/*      */     }
/* 2298 */     return nearest;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int nearestElementValue(int[] array, int value)
/*      */   {
/* 2304 */     int diff = Math.abs(array[0] - value);
/* 2305 */     int nearest = array[0];
/* 2306 */     for (int i = 1; i < array.length; i++) {
/* 2307 */       if (Math.abs(array[i] - value) < diff) {
/* 2308 */         diff = Math.abs(array[i] - value);
/* 2309 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2312 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestElementIndex(int[] array, int value)
/*      */   {
/* 2317 */     int diff = Math.abs(array[0] - value);
/* 2318 */     int nearest = 0;
/* 2319 */     for (int i = 1; i < array.length; i++) {
/* 2320 */       if (Math.abs(array[i] - value) < diff) {
/* 2321 */         diff = Math.abs(array[i] - value);
/* 2322 */         nearest = i;
/*      */       }
/*      */     }
/* 2325 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestLowerElementValue(int[] array, int value)
/*      */   {
/* 2330 */     int diff0 = 0;
/* 2331 */     int diff1 = 0;
/* 2332 */     int nearest = 0;
/* 2333 */     int ii = 0;
/* 2334 */     boolean test = true;
/* 2335 */     int min = array[0];
/* 2336 */     while (test) {
/* 2337 */       if (array[ii] < min) min = array[ii];
/* 2338 */       if (value - array[ii] >= 0) {
/* 2339 */         diff0 = value - array[ii];
/* 2340 */         nearest = array[ii];
/* 2341 */         test = false;
/*      */       }
/*      */       else {
/* 2344 */         ii++;
/* 2345 */         if (ii > array.length - 1) {
/* 2346 */           nearest = min;
/* 2347 */           diff0 = min - value;
/* 2348 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2352 */     for (int i = 0; i < array.length; i++) {
/* 2353 */       diff1 = value - array[i];
/* 2354 */       if ((diff1 >= 0) && (diff1 < diff0)) {
/* 2355 */         diff0 = diff1;
/* 2356 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2359 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestLowerElementIndex(int[] array, int value)
/*      */   {
/* 2364 */     int diff0 = 0;
/* 2365 */     int diff1 = 0;
/* 2366 */     int nearest = 0;
/* 2367 */     int ii = 0;
/* 2368 */     boolean test = true;
/* 2369 */     int min = array[0];
/* 2370 */     int minI = 0;
/* 2371 */     while (test) {
/* 2372 */       if (array[ii] < min) {
/* 2373 */         min = array[ii];
/* 2374 */         minI = ii;
/*      */       }
/* 2376 */       if (value - array[ii] >= 0) {
/* 2377 */         diff0 = value - array[ii];
/* 2378 */         nearest = ii;
/* 2379 */         test = false;
/*      */       }
/*      */       else {
/* 2382 */         ii++;
/* 2383 */         if (ii > array.length - 1) {
/* 2384 */           nearest = minI;
/* 2385 */           diff0 = min - value;
/* 2386 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2390 */     for (int i = 0; i < array.length; i++) {
/* 2391 */       diff1 = value - array[i];
/* 2392 */       if ((diff1 >= 0) && (diff1 < diff0)) {
/* 2393 */         diff0 = diff1;
/* 2394 */         nearest = i;
/*      */       }
/*      */     }
/* 2397 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestHigherElementValue(int[] array, int value)
/*      */   {
/* 2402 */     int diff0 = 0;
/* 2403 */     int diff1 = 0;
/* 2404 */     int nearest = 0;
/* 2405 */     int ii = 0;
/* 2406 */     boolean test = true;
/* 2407 */     int max = array[0];
/* 2408 */     while (test) {
/* 2409 */       if (array[ii] > max) max = array[ii];
/* 2410 */       if (array[ii] - value >= 0) {
/* 2411 */         diff0 = value - array[ii];
/* 2412 */         nearest = array[ii];
/* 2413 */         test = false;
/*      */       }
/*      */       else {
/* 2416 */         ii++;
/* 2417 */         if (ii > array.length - 1) {
/* 2418 */           nearest = max;
/* 2419 */           diff0 = value - max;
/* 2420 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2424 */     for (int i = 0; i < array.length; i++) {
/* 2425 */       diff1 = array[i] - value;
/* 2426 */       if ((diff1 >= 0) && (diff1 < diff0)) {
/* 2427 */         diff0 = diff1;
/* 2428 */         nearest = array[i];
/*      */       }
/*      */     }
/* 2431 */     return nearest;
/*      */   }
/*      */   
/*      */   public static int nearestHigherElementIndex(int[] array, int value)
/*      */   {
/* 2436 */     int diff0 = 0;
/* 2437 */     int diff1 = 0;
/* 2438 */     int nearest = 0;
/* 2439 */     int ii = 0;
/* 2440 */     boolean test = true;
/* 2441 */     int max = array[0];
/* 2442 */     int maxI = 0;
/* 2443 */     while (test) {
/* 2444 */       if (array[ii] > max) {
/* 2445 */         max = array[ii];
/* 2446 */         maxI = ii;
/*      */       }
/* 2448 */       if (array[ii] - value >= 0) {
/* 2449 */         diff0 = value - array[ii];
/* 2450 */         nearest = ii;
/* 2451 */         test = false;
/*      */       }
/*      */       else {
/* 2454 */         ii++;
/* 2455 */         if (ii > array.length - 1) {
/* 2456 */           nearest = maxI;
/* 2457 */           diff0 = value - max;
/* 2458 */           test = false;
/*      */         }
/*      */       }
/*      */     }
/* 2462 */     for (int i = 0; i < array.length; i++) {
/* 2463 */       diff1 = array[i] - value;
/* 2464 */       if ((diff1 >= 0) && (diff1 < diff0)) {
/* 2465 */         diff0 = diff1;
/* 2466 */         nearest = i;
/*      */       }
/*      */     }
/* 2469 */     return nearest;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double arraySum(double[] array)
/*      */   {
/* 2475 */     double sum = 0.0D;
/* 2476 */     for (double i : array) sum += i;
/* 2477 */     return sum;
/*      */   }
/*      */   
/*      */   public static float arraySum(float[] array)
/*      */   {
/* 2482 */     float sum = 0.0F;
/* 2483 */     for (float i : array) sum += i;
/* 2484 */     return sum;
/*      */   }
/*      */   
/*      */   public static int arraySum(int[] array)
/*      */   {
/* 2489 */     int sum = 0;
/* 2490 */     for (int i : array) sum += i;
/* 2491 */     return sum;
/*      */   }
/*      */   
/*      */   public static long arraySum(long[] array)
/*      */   {
/* 2496 */     long sum = 0L;
/* 2497 */     for (long i : array) sum += i;
/* 2498 */     return sum;
/*      */   }
/*      */   
/*      */   public static long arrayPositiveElementsSum(long[] array)
/*      */   {
/* 2503 */     long sum = 0L;
/* 2504 */     for (long i : array) if (i > 0L) sum += i;
/* 2505 */     return sum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double arrayProduct(double[] array)
/*      */   {
/* 2512 */     double product = 1.0D;
/* 2513 */     for (double i : array) product *= i;
/* 2514 */     return product;
/*      */   }
/*      */   
/*      */   public static float arrayProduct(float[] array)
/*      */   {
/* 2519 */     float product = 1.0F;
/* 2520 */     for (float i : array) product *= i;
/* 2521 */     return product;
/*      */   }
/*      */   
/*      */   public static int arrayProduct(int[] array)
/*      */   {
/* 2526 */     int product = 1;
/* 2527 */     for (int i : array) product *= i;
/* 2528 */     return product;
/*      */   }
/*      */   
/*      */   public static long arrayProduct(long[] array)
/*      */   {
/* 2533 */     long product = 1L;
/* 2534 */     for (long i : array) product *= i;
/* 2535 */     return product;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] concatenate(double[] aa, double[] bb)
/*      */   {
/* 2541 */     int aLen = aa.length;
/* 2542 */     int bLen = bb.length;
/* 2543 */     int cLen = aLen + bLen;
/* 2544 */     double[] cc = new double[cLen];
/* 2545 */     for (int i = 0; i < aLen; i++) {
/* 2546 */       cc[i] = aa[i];
/*      */     }
/* 2548 */     for (int i = 0; i < bLen; i++) {
/* 2549 */       cc[(i + aLen)] = bb[i];
/*      */     }
/* 2551 */     return cc;
/*      */   }
/*      */   
/*      */   public static float[] concatenate(float[] aa, float[] bb)
/*      */   {
/* 2556 */     int aLen = aa.length;
/* 2557 */     int bLen = bb.length;
/* 2558 */     int cLen = aLen + bLen;
/* 2559 */     float[] cc = new float[cLen];
/* 2560 */     for (int i = 0; i < aLen; i++) {
/* 2561 */       cc[i] = aa[i];
/*      */     }
/* 2563 */     for (int i = 0; i < bLen; i++) {
/* 2564 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2567 */     return cc;
/*      */   }
/*      */   
/*      */   public static int[] concatenate(int[] aa, int[] bb)
/*      */   {
/* 2572 */     int aLen = aa.length;
/* 2573 */     int bLen = bb.length;
/* 2574 */     int cLen = aLen + bLen;
/* 2575 */     int[] cc = new int[cLen];
/* 2576 */     for (int i = 0; i < aLen; i++) {
/* 2577 */       cc[i] = aa[i];
/*      */     }
/* 2579 */     for (int i = 0; i < bLen; i++) {
/* 2580 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2583 */     return cc;
/*      */   }
/*      */   
/*      */   public static long[] concatenate(long[] aa, long[] bb)
/*      */   {
/* 2588 */     int aLen = aa.length;
/* 2589 */     int bLen = bb.length;
/* 2590 */     int cLen = aLen + bLen;
/* 2591 */     long[] cc = new long[cLen];
/* 2592 */     for (int i = 0; i < aLen; i++) {
/* 2593 */       cc[i] = aa[i];
/*      */     }
/* 2595 */     for (int i = 0; i < bLen; i++) {
/* 2596 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2599 */     return cc;
/*      */   }
/*      */   
/*      */   public static short[] concatenate(short[] aa, short[] bb)
/*      */   {
/* 2604 */     int aLen = aa.length;
/* 2605 */     int bLen = bb.length;
/* 2606 */     int cLen = aLen + bLen;
/* 2607 */     short[] cc = new short[cLen];
/* 2608 */     for (int i = 0; i < aLen; i++) {
/* 2609 */       cc[i] = aa[i];
/*      */     }
/* 2611 */     for (int i = 0; i < bLen; i++) {
/* 2612 */       cc[(i + aLen)] = bb[i];
/*      */     }
/* 2614 */     return cc;
/*      */   }
/*      */   
/*      */   public static byte[] concatenate(byte[] aa, byte[] bb)
/*      */   {
/* 2619 */     int aLen = aa.length;
/* 2620 */     int bLen = bb.length;
/* 2621 */     int cLen = aLen + bLen;
/* 2622 */     byte[] cc = new byte[cLen];
/* 2623 */     for (int i = 0; i < aLen; i++) {
/* 2624 */       cc[i] = aa[i];
/*      */     }
/* 2626 */     for (int i = 0; i < bLen; i++) {
/* 2627 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2630 */     return cc;
/*      */   }
/*      */   
/*      */   public static char[] concatenate(char[] aa, char[] bb)
/*      */   {
/* 2635 */     int aLen = aa.length;
/* 2636 */     int bLen = bb.length;
/* 2637 */     int cLen = aLen + bLen;
/* 2638 */     char[] cc = new char[cLen];
/* 2639 */     for (int i = 0; i < aLen; i++) {
/* 2640 */       cc[i] = aa[i];
/*      */     }
/* 2642 */     for (int i = 0; i < bLen; i++) {
/* 2643 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2646 */     return cc;
/*      */   }
/*      */   
/*      */   public static String[] concatenate(String[] aa, String[] bb)
/*      */   {
/* 2651 */     int aLen = aa.length;
/* 2652 */     int bLen = bb.length;
/* 2653 */     int cLen = aLen + bLen;
/* 2654 */     String[] cc = new String[cLen];
/* 2655 */     for (int i = 0; i < aLen; i++) {
/* 2656 */       cc[i] = aa[i];
/*      */     }
/* 2658 */     for (int i = 0; i < bLen; i++) {
/* 2659 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2662 */     return cc;
/*      */   }
/*      */   
/*      */   public static Object[] concatenate(Object[] aa, Object[] bb)
/*      */   {
/* 2667 */     int aLen = aa.length;
/* 2668 */     int bLen = bb.length;
/* 2669 */     int cLen = aLen + bLen;
/* 2670 */     Object[] cc = new Object[cLen];
/* 2671 */     for (int i = 0; i < aLen; i++) {
/* 2672 */       cc[i] = aa[i];
/*      */     }
/* 2674 */     for (int i = 0; i < bLen; i++) {
/* 2675 */       cc[(i + aLen)] = bb[i];
/*      */     }
/*      */     
/* 2678 */     return cc;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] floatTOdouble(float[] aa)
/*      */   {
/* 2684 */     int n = aa.length;
/* 2685 */     double[] bb = new double[n];
/* 2686 */     for (int i = 0; i < n; i++) {
/* 2687 */       bb[i] = aa[i];
/*      */     }
/* 2689 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] intTOdouble(int[] aa)
/*      */   {
/* 2694 */     int n = aa.length;
/* 2695 */     double[] bb = new double[n];
/* 2696 */     for (int i = 0; i < n; i++) {
/* 2697 */       bb[i] = aa[i];
/*      */     }
/* 2699 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] intTOfloat(int[] aa)
/*      */   {
/* 2704 */     int n = aa.length;
/* 2705 */     float[] bb = new float[n];
/* 2706 */     for (int i = 0; i < n; i++) {
/* 2707 */       bb[i] = aa[i];
/*      */     }
/* 2709 */     return bb;
/*      */   }
/*      */   
/*      */   public static long[] intTOlong(int[] aa)
/*      */   {
/* 2714 */     int n = aa.length;
/* 2715 */     long[] bb = new long[n];
/* 2716 */     for (int i = 0; i < n; i++) {
/* 2717 */       bb[i] = aa[i];
/*      */     }
/* 2719 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] longTOdouble(long[] aa)
/*      */   {
/* 2725 */     int n = aa.length;
/* 2726 */     double[] bb = new double[n];
/* 2727 */     for (int i = 0; i < n; i++) {
/* 2728 */       bb[i] = aa[i];
/*      */     }
/* 2730 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static float[] longTOfloat(long[] aa)
/*      */   {
/* 2736 */     int n = aa.length;
/* 2737 */     float[] bb = new float[n];
/* 2738 */     for (int i = 0; i < n; i++) {
/* 2739 */       bb[i] = ((float)aa[i]);
/*      */     }
/* 2741 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] shortTOdouble(short[] aa)
/*      */   {
/* 2746 */     int n = aa.length;
/* 2747 */     double[] bb = new double[n];
/* 2748 */     for (int i = 0; i < n; i++) {
/* 2749 */       bb[i] = aa[i];
/*      */     }
/* 2751 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] shortTOfloat(short[] aa)
/*      */   {
/* 2756 */     int n = aa.length;
/* 2757 */     float[] bb = new float[n];
/* 2758 */     for (int i = 0; i < n; i++) {
/* 2759 */       bb[i] = aa[i];
/*      */     }
/* 2761 */     return bb;
/*      */   }
/*      */   
/*      */   public static long[] shortTOlong(short[] aa)
/*      */   {
/* 2766 */     int n = aa.length;
/* 2767 */     long[] bb = new long[n];
/* 2768 */     for (int i = 0; i < n; i++) {
/* 2769 */       bb[i] = aa[i];
/*      */     }
/* 2771 */     return bb;
/*      */   }
/*      */   
/*      */   public static int[] shortTOint(short[] aa)
/*      */   {
/* 2776 */     int n = aa.length;
/* 2777 */     int[] bb = new int[n];
/* 2778 */     for (int i = 0; i < n; i++) {
/* 2779 */       bb[i] = aa[i];
/*      */     }
/* 2781 */     return bb;
/*      */   }
/*      */   
/*      */   public static double[] byteTOdouble(byte[] aa)
/*      */   {
/* 2786 */     int n = aa.length;
/* 2787 */     double[] bb = new double[n];
/* 2788 */     for (int i = 0; i < n; i++) {
/* 2789 */       bb[i] = aa[i];
/*      */     }
/* 2791 */     return bb;
/*      */   }
/*      */   
/*      */   public static float[] byteTOfloat(byte[] aa)
/*      */   {
/* 2796 */     int n = aa.length;
/* 2797 */     float[] bb = new float[n];
/* 2798 */     for (int i = 0; i < n; i++) {
/* 2799 */       bb[i] = aa[i];
/*      */     }
/* 2801 */     return bb;
/*      */   }
/*      */   
/*      */   public static long[] byteTOlong(byte[] aa)
/*      */   {
/* 2806 */     int n = aa.length;
/* 2807 */     long[] bb = new long[n];
/* 2808 */     for (int i = 0; i < n; i++) {
/* 2809 */       bb[i] = aa[i];
/*      */     }
/* 2811 */     return bb;
/*      */   }
/*      */   
/*      */   public static int[] byteTOint(byte[] aa)
/*      */   {
/* 2816 */     int n = aa.length;
/* 2817 */     int[] bb = new int[n];
/* 2818 */     for (int i = 0; i < n; i++) {
/* 2819 */       bb[i] = aa[i];
/*      */     }
/* 2821 */     return bb;
/*      */   }
/*      */   
/*      */   public static short[] byteTOshort(byte[] aa)
/*      */   {
/* 2826 */     int n = aa.length;
/* 2827 */     short[] bb = new short[n];
/* 2828 */     for (int i = 0; i < n; i++) {
/* 2829 */       bb[i] = ((short)aa[i]);
/*      */     }
/* 2831 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] doubleTOint(double[] aa)
/*      */   {
/* 2837 */     int n = aa.length;
/* 2838 */     int[] bb = new int[n];
/* 2839 */     for (int i = 0; i < n; i++) {
/* 2840 */       bb[i] = ((int)aa[i]);
/*      */     }
/* 2842 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void print(double[] aa)
/*      */   {
/* 2849 */     for (int i = 0; i < aa.length; i++) {
/* 2850 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2852 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(double[] aa)
/*      */   {
/* 2858 */     for (int i = 0; i < aa.length; i++) {
/* 2859 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(float[] aa)
/*      */   {
/* 2866 */     for (int i = 0; i < aa.length; i++) {
/* 2867 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2869 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(float[] aa)
/*      */   {
/* 2875 */     for (int i = 0; i < aa.length; i++) {
/* 2876 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(int[] aa)
/*      */   {
/* 2883 */     for (int i = 0; i < aa.length; i++) {
/* 2884 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2886 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(int[] aa)
/*      */   {
/* 2892 */     for (int i = 0; i < aa.length; i++) {
/* 2893 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(long[] aa)
/*      */   {
/* 2900 */     for (int i = 0; i < aa.length; i++) {
/* 2901 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2903 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(long[] aa)
/*      */   {
/* 2909 */     for (int i = 0; i < aa.length; i++) {
/* 2910 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(char[] aa)
/*      */   {
/* 2917 */     for (int i = 0; i < aa.length; i++) {
/* 2918 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2920 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(char[] aa)
/*      */   {
/* 2926 */     for (int i = 0; i < aa.length; i++) {
/* 2927 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(String[] aa)
/*      */   {
/* 2934 */     for (int i = 0; i < aa.length; i++) {
/* 2935 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2937 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(String[] aa)
/*      */   {
/* 2943 */     for (int i = 0; i < aa.length; i++) {
/* 2944 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(short[] aa)
/*      */   {
/* 2951 */     for (int i = 0; i < aa.length; i++) {
/* 2952 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2954 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(short[] aa)
/*      */   {
/* 2960 */     for (int i = 0; i < aa.length; i++) {
/* 2961 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(byte[] aa)
/*      */   {
/* 2968 */     for (int i = 0; i < aa.length; i++) {
/* 2969 */       System.out.print(aa[i] + "   ");
/*      */     }
/* 2971 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(byte[] aa)
/*      */   {
/* 2977 */     for (int i = 0; i < aa.length; i++) {
/* 2978 */       System.out.println(aa[i] + "   ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void print(double[][] aa)
/*      */   {
/* 2985 */     for (int i = 0; i < aa.length; i++) {
/* 2986 */       print(aa[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Vector<Object> selectSortVector(double[] aa)
/*      */   {
/* 2996 */     ArrayList<Object> list = selectSortArrayList(aa);
/* 2997 */     Vector<Object> ret = null;
/* 2998 */     if (list != null) {
/* 2999 */       int n = list.size();
/* 3000 */       ret = new Vector(n);
/* 3001 */       for (int i = 0; i < n; i++) ret.addElement(list.get(i));
/*      */     }
/* 3003 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ArrayList<Object> selectSortArrayList(double[] aa)
/*      */   {
/* 3012 */     int index = 0;
/* 3013 */     int lastIndex = -1;
/* 3014 */     int n = aa.length;
/* 3015 */     double holdb = 0.0D;
/* 3016 */     int holdi = 0;
/* 3017 */     double[] bb = new double[n];
/* 3018 */     int[] indices = new int[n];
/* 3019 */     for (int i = 0; i < n; i++) {
/* 3020 */       bb[i] = aa[i];
/* 3021 */       indices[i] = i;
/*      */     }
/*      */     
/* 3024 */     while (lastIndex != n - 1) {
/* 3025 */       index = lastIndex + 1;
/* 3026 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3027 */         if (bb[i] < bb[index]) {
/* 3028 */           index = i;
/*      */         }
/*      */       }
/* 3031 */       lastIndex++;
/* 3032 */       holdb = bb[index];
/* 3033 */       bb[index] = bb[lastIndex];
/* 3034 */       bb[lastIndex] = holdb;
/* 3035 */       holdi = indices[index];
/* 3036 */       indices[index] = indices[lastIndex];
/* 3037 */       indices[lastIndex] = holdi;
/*      */     }
/* 3039 */     ArrayList<Object> arrayl = new ArrayList();
/* 3040 */     arrayl.add(aa);
/* 3041 */     arrayl.add(bb);
/* 3042 */     arrayl.add(indices);
/* 3043 */     return arrayl;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[] selectionSort(double[] aa)
/*      */   {
/* 3049 */     int index = 0;
/* 3050 */     int lastIndex = -1;
/* 3051 */     int n = aa.length;
/* 3052 */     double hold = 0.0D;
/* 3053 */     double[] bb = new double[n];
/* 3054 */     for (int i = 0; i < n; i++) {
/* 3055 */       bb[i] = aa[i];
/*      */     }
/*      */     
/* 3058 */     while (lastIndex != n - 1) {
/* 3059 */       index = lastIndex + 1;
/* 3060 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3061 */         if (bb[i] < bb[index]) {
/* 3062 */           index = i;
/*      */         }
/*      */       }
/* 3065 */       lastIndex++;
/* 3066 */       hold = bb[index];
/* 3067 */       bb[index] = bb[lastIndex];
/* 3068 */       bb[lastIndex] = hold;
/*      */     }
/* 3070 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static float[] selectionSort(float[] aa)
/*      */   {
/* 3076 */     int index = 0;
/* 3077 */     int lastIndex = -1;
/* 3078 */     int n = aa.length;
/* 3079 */     float hold = 0.0F;
/* 3080 */     float[] bb = new float[n];
/* 3081 */     for (int i = 0; i < n; i++) {
/* 3082 */       bb[i] = aa[i];
/*      */     }
/*      */     
/* 3085 */     while (lastIndex != n - 1) {
/* 3086 */       index = lastIndex + 1;
/* 3087 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3088 */         if (bb[i] < bb[index]) {
/* 3089 */           index = i;
/*      */         }
/*      */       }
/* 3092 */       lastIndex++;
/* 3093 */       hold = bb[index];
/* 3094 */       bb[index] = bb[lastIndex];
/* 3095 */       bb[lastIndex] = hold;
/*      */     }
/* 3097 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static int[] selectionSort(int[] aa)
/*      */   {
/* 3103 */     int index = 0;
/* 3104 */     int lastIndex = -1;
/* 3105 */     int n = aa.length;
/* 3106 */     int hold = 0;
/* 3107 */     int[] bb = new int[n];
/* 3108 */     for (int i = 0; i < n; i++) {
/* 3109 */       bb[i] = aa[i];
/*      */     }
/*      */     
/* 3112 */     while (lastIndex != n - 1) {
/* 3113 */       index = lastIndex + 1;
/* 3114 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3115 */         if (bb[i] < bb[index]) {
/* 3116 */           index = i;
/*      */         }
/*      */       }
/* 3119 */       lastIndex++;
/* 3120 */       hold = bb[index];
/* 3121 */       bb[index] = bb[lastIndex];
/* 3122 */       bb[lastIndex] = hold;
/*      */     }
/* 3124 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static long[] selectionSort(long[] aa)
/*      */   {
/* 3130 */     int index = 0;
/* 3131 */     int lastIndex = -1;
/* 3132 */     int n = aa.length;
/* 3133 */     long hold = 0L;
/* 3134 */     long[] bb = new long[n];
/* 3135 */     for (int i = 0; i < n; i++) {
/* 3136 */       bb[i] = aa[i];
/*      */     }
/*      */     
/* 3139 */     while (lastIndex != n - 1) {
/* 3140 */       index = lastIndex + 1;
/* 3141 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3142 */         if (bb[i] < bb[index]) {
/* 3143 */           index = i;
/*      */         }
/*      */       }
/* 3146 */       lastIndex++;
/* 3147 */       hold = bb[index];
/* 3148 */       bb[index] = bb[lastIndex];
/* 3149 */       bb[lastIndex] = hold;
/*      */     }
/* 3151 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(double[] aa, double[] bb, int[] indices)
/*      */   {
/* 3160 */     int index = 0;
/* 3161 */     int lastIndex = -1;
/* 3162 */     int n = aa.length;
/* 3163 */     double holdb = 0.0D;
/* 3164 */     int holdi = 0;
/* 3165 */     for (int i = 0; i < n; i++) {
/* 3166 */       bb[i] = aa[i];
/* 3167 */       indices[i] = i;
/*      */     }
/*      */     
/* 3170 */     while (lastIndex != n - 1) {
/* 3171 */       index = lastIndex + 1;
/* 3172 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3173 */         if (bb[i] < bb[index]) {
/* 3174 */           index = i;
/*      */         }
/*      */       }
/* 3177 */       lastIndex++;
/* 3178 */       holdb = bb[index];
/* 3179 */       bb[index] = bb[lastIndex];
/* 3180 */       bb[lastIndex] = holdb;
/* 3181 */       holdi = indices[index];
/* 3182 */       indices[index] = indices[lastIndex];
/* 3183 */       indices[lastIndex] = holdi;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(double[] aa, double[] bb, double[] cc, double[] dd)
/*      */   {
/* 3193 */     int index = 0;
/* 3194 */     int lastIndex = -1;
/* 3195 */     int n = aa.length;
/* 3196 */     int m = bb.length;
/* 3197 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3198 */     int nn = cc.length;
/* 3199 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3200 */     int mm = dd.length;
/* 3201 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3203 */     double holdx = 0.0D;
/* 3204 */     double holdy = 0.0D;
/*      */     
/*      */ 
/* 3207 */     for (int i = 0; i < n; i++) {
/* 3208 */       cc[i] = aa[i];
/* 3209 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3212 */     while (lastIndex != n - 1) {
/* 3213 */       index = lastIndex + 1;
/* 3214 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3215 */         if (cc[i] < cc[index]) {
/* 3216 */           index = i;
/*      */         }
/*      */       }
/* 3219 */       lastIndex++;
/* 3220 */       holdx = cc[index];
/* 3221 */       cc[index] = cc[lastIndex];
/* 3222 */       cc[lastIndex] = holdx;
/* 3223 */       holdy = dd[index];
/* 3224 */       dd[index] = dd[lastIndex];
/* 3225 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(float[] aa, float[] bb, float[] cc, float[] dd)
/*      */   {
/* 3235 */     int index = 0;
/* 3236 */     int lastIndex = -1;
/* 3237 */     int n = aa.length;
/* 3238 */     int m = bb.length;
/* 3239 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3240 */     int nn = cc.length;
/* 3241 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3242 */     int mm = dd.length;
/* 3243 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3245 */     float holdx = 0.0F;
/* 3246 */     float holdy = 0.0F;
/*      */     
/*      */ 
/* 3249 */     for (int i = 0; i < n; i++) {
/* 3250 */       cc[i] = aa[i];
/* 3251 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3254 */     while (lastIndex != n - 1) {
/* 3255 */       index = lastIndex + 1;
/* 3256 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3257 */         if (cc[i] < cc[index]) {
/* 3258 */           index = i;
/*      */         }
/*      */       }
/* 3261 */       lastIndex++;
/* 3262 */       holdx = cc[index];
/* 3263 */       cc[index] = cc[lastIndex];
/* 3264 */       cc[lastIndex] = holdx;
/* 3265 */       holdy = dd[index];
/* 3266 */       dd[index] = dd[lastIndex];
/* 3267 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(long[] aa, long[] bb, long[] cc, long[] dd)
/*      */   {
/* 3277 */     int index = 0;
/* 3278 */     int lastIndex = -1;
/* 3279 */     int n = aa.length;
/* 3280 */     int m = bb.length;
/* 3281 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3282 */     int nn = cc.length;
/* 3283 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3284 */     int mm = dd.length;
/* 3285 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3287 */     long holdx = 0L;
/* 3288 */     long holdy = 0L;
/*      */     
/*      */ 
/* 3291 */     for (int i = 0; i < n; i++) {
/* 3292 */       cc[i] = aa[i];
/* 3293 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3296 */     while (lastIndex != n - 1) {
/* 3297 */       index = lastIndex + 1;
/* 3298 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3299 */         if (cc[i] < cc[index]) {
/* 3300 */           index = i;
/*      */         }
/*      */       }
/* 3303 */       lastIndex++;
/* 3304 */       holdx = cc[index];
/* 3305 */       cc[index] = cc[lastIndex];
/* 3306 */       cc[lastIndex] = holdx;
/* 3307 */       holdy = dd[index];
/* 3308 */       dd[index] = dd[lastIndex];
/* 3309 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(int[] aa, int[] bb, int[] cc, int[] dd)
/*      */   {
/* 3319 */     int index = 0;
/* 3320 */     int lastIndex = -1;
/* 3321 */     int n = aa.length;
/* 3322 */     int m = bb.length;
/* 3323 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3324 */     int nn = cc.length;
/* 3325 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3326 */     int mm = dd.length;
/* 3327 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3329 */     int holdx = 0;
/* 3330 */     int holdy = 0;
/*      */     
/*      */ 
/* 3333 */     for (int i = 0; i < n; i++) {
/* 3334 */       cc[i] = aa[i];
/* 3335 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3338 */     while (lastIndex != n - 1) {
/* 3339 */       index = lastIndex + 1;
/* 3340 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3341 */         if (cc[i] < cc[index]) {
/* 3342 */           index = i;
/*      */         }
/*      */       }
/* 3345 */       lastIndex++;
/* 3346 */       holdx = cc[index];
/* 3347 */       cc[index] = cc[lastIndex];
/* 3348 */       cc[lastIndex] = holdx;
/* 3349 */       holdy = dd[index];
/* 3350 */       dd[index] = dd[lastIndex];
/* 3351 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(double[] aa, long[] bb, double[] cc, long[] dd)
/*      */   {
/* 3361 */     int index = 0;
/* 3362 */     int lastIndex = -1;
/* 3363 */     int n = aa.length;
/* 3364 */     int m = bb.length;
/* 3365 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3366 */     int nn = cc.length;
/* 3367 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3368 */     int mm = dd.length;
/* 3369 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3371 */     double holdx = 0.0D;
/* 3372 */     long holdy = 0L;
/*      */     
/*      */ 
/* 3375 */     for (int i = 0; i < n; i++) {
/* 3376 */       cc[i] = aa[i];
/* 3377 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3380 */     while (lastIndex != n - 1) {
/* 3381 */       index = lastIndex + 1;
/* 3382 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3383 */         if (cc[i] < cc[index]) {
/* 3384 */           index = i;
/*      */         }
/*      */       }
/* 3387 */       lastIndex++;
/* 3388 */       holdx = cc[index];
/* 3389 */       cc[index] = cc[lastIndex];
/* 3390 */       cc[lastIndex] = holdx;
/* 3391 */       holdy = dd[index];
/* 3392 */       dd[index] = dd[lastIndex];
/* 3393 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(long[] aa, double[] bb, long[] cc, double[] dd)
/*      */   {
/* 3403 */     int index = 0;
/* 3404 */     int lastIndex = -1;
/* 3405 */     int n = aa.length;
/* 3406 */     int m = bb.length;
/* 3407 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3408 */     int nn = cc.length;
/* 3409 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3410 */     int mm = dd.length;
/* 3411 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3413 */     long holdx = 0L;
/* 3414 */     double holdy = 0.0D;
/*      */     
/*      */ 
/* 3417 */     for (int i = 0; i < n; i++) {
/* 3418 */       cc[i] = aa[i];
/* 3419 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3422 */     while (lastIndex != n - 1) {
/* 3423 */       index = lastIndex + 1;
/* 3424 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3425 */         if (cc[i] < cc[index]) {
/* 3426 */           index = i;
/*      */         }
/*      */       }
/* 3429 */       lastIndex++;
/* 3430 */       holdx = cc[index];
/* 3431 */       cc[index] = cc[lastIndex];
/* 3432 */       cc[lastIndex] = holdx;
/* 3433 */       holdy = dd[index];
/* 3434 */       dd[index] = dd[lastIndex];
/* 3435 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(double[] aa, int[] bb, double[] cc, int[] dd)
/*      */   {
/* 3445 */     int index = 0;
/* 3446 */     int lastIndex = -1;
/* 3447 */     int n = aa.length;
/* 3448 */     int m = bb.length;
/* 3449 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3450 */     int nn = cc.length;
/* 3451 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3452 */     int mm = dd.length;
/* 3453 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3455 */     double holdx = 0.0D;
/* 3456 */     int holdy = 0;
/*      */     
/*      */ 
/* 3459 */     for (int i = 0; i < n; i++) {
/* 3460 */       cc[i] = aa[i];
/* 3461 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3464 */     while (lastIndex != n - 1) {
/* 3465 */       index = lastIndex + 1;
/* 3466 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3467 */         if (cc[i] < cc[index]) {
/* 3468 */           index = i;
/*      */         }
/*      */       }
/* 3471 */       lastIndex++;
/* 3472 */       holdx = cc[index];
/* 3473 */       cc[index] = cc[lastIndex];
/* 3474 */       cc[lastIndex] = holdx;
/* 3475 */       holdy = dd[index];
/* 3476 */       dd[index] = dd[lastIndex];
/* 3477 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(int[] aa, double[] bb, int[] cc, double[] dd)
/*      */   {
/* 3487 */     int index = 0;
/* 3488 */     int lastIndex = -1;
/* 3489 */     int n = aa.length;
/* 3490 */     int m = bb.length;
/* 3491 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3492 */     int nn = cc.length;
/* 3493 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3494 */     int mm = dd.length;
/* 3495 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3497 */     int holdx = 0;
/* 3498 */     double holdy = 0.0D;
/*      */     
/*      */ 
/* 3501 */     for (int i = 0; i < n; i++) {
/* 3502 */       cc[i] = aa[i];
/* 3503 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3506 */     while (lastIndex != n - 1) {
/* 3507 */       index = lastIndex + 1;
/* 3508 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3509 */         if (cc[i] < cc[index]) {
/* 3510 */           index = i;
/*      */         }
/*      */       }
/* 3513 */       lastIndex++;
/* 3514 */       holdx = cc[index];
/* 3515 */       cc[index] = cc[lastIndex];
/* 3516 */       cc[lastIndex] = holdx;
/* 3517 */       holdy = dd[index];
/* 3518 */       dd[index] = dd[lastIndex];
/* 3519 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(long[] aa, int[] bb, long[] cc, int[] dd)
/*      */   {
/* 3529 */     int index = 0;
/* 3530 */     int lastIndex = -1;
/* 3531 */     int n = aa.length;
/* 3532 */     int m = bb.length;
/* 3533 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3534 */     int nn = cc.length;
/* 3535 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3536 */     int mm = dd.length;
/* 3537 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3539 */     long holdx = 0L;
/* 3540 */     int holdy = 0;
/*      */     
/*      */ 
/* 3543 */     for (int i = 0; i < n; i++) {
/* 3544 */       cc[i] = aa[i];
/* 3545 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3548 */     while (lastIndex != n - 1) {
/* 3549 */       index = lastIndex + 1;
/* 3550 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3551 */         if (cc[i] < cc[index]) {
/* 3552 */           index = i;
/*      */         }
/*      */       }
/* 3555 */       lastIndex++;
/* 3556 */       holdx = cc[index];
/* 3557 */       cc[index] = cc[lastIndex];
/* 3558 */       cc[lastIndex] = holdx;
/* 3559 */       holdy = dd[index];
/* 3560 */       dd[index] = dd[lastIndex];
/* 3561 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectionSort(int[] aa, long[] bb, int[] cc, long[] dd)
/*      */   {
/* 3571 */     int index = 0;
/* 3572 */     int lastIndex = -1;
/* 3573 */     int n = aa.length;
/* 3574 */     int m = bb.length;
/* 3575 */     if (n != m) throw new IllegalArgumentException("First argument array, aa, (length = " + n + ") and the second argument array, bb, (length = " + m + ") should be the same length");
/* 3576 */     int nn = cc.length;
/* 3577 */     if (nn < n) throw new IllegalArgumentException("The third argument array, cc, (length = " + nn + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3578 */     int mm = dd.length;
/* 3579 */     if (mm < m) { throw new IllegalArgumentException("The fourth argument array, dd, (length = " + mm + ") should be at least as long as the second argument array, bb, (length = " + m + ")");
/*      */     }
/* 3581 */     int holdx = 0;
/* 3582 */     long holdy = 0L;
/*      */     
/*      */ 
/* 3585 */     for (int i = 0; i < n; i++) {
/* 3586 */       cc[i] = aa[i];
/* 3587 */       dd[i] = bb[i];
/*      */     }
/*      */     
/* 3590 */     while (lastIndex != n - 1) {
/* 3591 */       index = lastIndex + 1;
/* 3592 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3593 */         if (cc[i] < cc[index]) {
/* 3594 */           index = i;
/*      */         }
/*      */       }
/* 3597 */       lastIndex++;
/* 3598 */       holdx = cc[index];
/* 3599 */       cc[index] = cc[lastIndex];
/* 3600 */       cc[lastIndex] = holdx;
/* 3601 */       holdy = dd[index];
/* 3602 */       dd[index] = dd[lastIndex];
/* 3603 */       dd[lastIndex] = holdy;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void selectSort(double[] aa, double[] bb, int[] indices)
/*      */   {
/* 3614 */     int index = 0;
/* 3615 */     int lastIndex = -1;
/* 3616 */     int n = aa.length;
/* 3617 */     int m = bb.length;
/* 3618 */     if (m < n) throw new IllegalArgumentException("The second argument array, bb, (length = " + m + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/* 3619 */     int k = indices.length;
/* 3620 */     if (m < n) { throw new IllegalArgumentException("The third argument array, indices, (length = " + k + ") should be at least as long as the first argument array, aa, (length = " + n + ")");
/*      */     }
/* 3622 */     double holdb = 0.0D;
/* 3623 */     int holdi = 0;
/* 3624 */     for (int i = 0; i < n; i++) {
/* 3625 */       bb[i] = aa[i];
/* 3626 */       indices[i] = i;
/*      */     }
/*      */     
/* 3629 */     while (lastIndex != n - 1) {
/* 3630 */       index = lastIndex + 1;
/* 3631 */       for (int i = lastIndex + 2; i < n; i++) {
/* 3632 */         if (bb[i] < bb[index]) {
/* 3633 */           index = i;
/*      */         }
/*      */       }
/* 3636 */       lastIndex++;
/* 3637 */       holdb = bb[index];
/* 3638 */       bb[index] = bb[lastIndex];
/* 3639 */       bb[lastIndex] = holdb;
/* 3640 */       holdi = indices[index];
/* 3641 */       indices[index] = indices[lastIndex];
/* 3642 */       indices[lastIndex] = holdi;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object copyObject(Object obj)
/*      */   {
/* 3652 */     Object objCopy = null;
/*      */     try
/*      */     {
/* 3655 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 3656 */       ObjectOutputStream oos = new ObjectOutputStream(bos);
/* 3657 */       oos.writeObject(obj);
/* 3658 */       oos.flush();
/* 3659 */       oos.close();
/*      */       
/*      */ 
/* 3662 */       ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
/*      */       
/* 3664 */       objCopy = ois.readObject();
/*      */     }
/*      */     catch (IOException e) {
/* 3667 */       e.printStackTrace();
/*      */     }
/*      */     catch (ClassNotFoundException cnfe) {
/* 3670 */       cnfe.printStackTrace();
/*      */     }
/* 3672 */     return objCopy;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double radToDeg(double rad)
/*      */   {
/* 3679 */     return rad * 180.0D / 3.141592653589793D;
/*      */   }
/*      */   
/*      */   public static double degToRad(double deg)
/*      */   {
/* 3684 */     return deg * 3.141592653589793D / 180.0D;
/*      */   }
/*      */   
/*      */   public static double frequencyToRadialFrequency(double frequency)
/*      */   {
/* 3689 */     return 6.283185307179586D * frequency;
/*      */   }
/*      */   
/*      */   public static double radialFrequencyToFrequency(double radial)
/*      */   {
/* 3694 */     return radial / 6.283185307179586D;
/*      */   }
/*      */   
/*      */   public static double evToNm(double ev)
/*      */   {
/* 3699 */     return 2.99792458E17D / (-ev * -1.60217646263E-19D / 6.6260687652E-34D);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double nmToEv(double nm)
/*      */   {
/* 3705 */     return 2.99792458E8D / (-nm * 1.0E-9D) * 6.6260687652E-34D / -1.60217646263E-19D;
/*      */   }
/*      */   
/*      */   public static double molarToPercentWeightByVol(double molar, double molWeight)
/*      */   {
/* 3710 */     return molar * molWeight / 10.0D;
/*      */   }
/*      */   
/*      */   public static double percentWeightByVolToMolar(double perCent, double molWeight)
/*      */   {
/* 3715 */     return perCent * 10.0D / molWeight;
/*      */   }
/*      */   
/*      */   public static double celsiusToKelvin(double cels)
/*      */   {
/* 3720 */     return cels - -273.15D;
/*      */   }
/*      */   
/*      */   public static double kelvinToCelsius(double kelv)
/*      */   {
/* 3725 */     return kelv + -273.15D;
/*      */   }
/*      */   
/*      */   public static double celsiusToFahren(double cels)
/*      */   {
/* 3730 */     return cels * 1.8D + 32.0D;
/*      */   }
/*      */   
/*      */   public static double fahrenToCelsius(double fahr)
/*      */   {
/* 3735 */     return (fahr - 32.0D) * 5.0D / 9.0D;
/*      */   }
/*      */   
/*      */   public static double calorieToJoule(double cal)
/*      */   {
/* 3740 */     return cal * 4.1868D;
/*      */   }
/*      */   
/*      */   public static double jouleToCalorie(double joule)
/*      */   {
/* 3745 */     return joule * 0.23884D;
/*      */   }
/*      */   
/*      */   public static double gramToOunce(double gm)
/*      */   {
/* 3750 */     return gm / 28.3459D;
/*      */   }
/*      */   
/*      */   public static double ounceToGram(double oz)
/*      */   {
/* 3755 */     return oz * 28.3459D;
/*      */   }
/*      */   
/*      */   public static double kgToPound(double kg)
/*      */   {
/* 3760 */     return kg / 0.4536D;
/*      */   }
/*      */   
/*      */   public static double poundToKg(double pds)
/*      */   {
/* 3765 */     return pds * 0.4536D;
/*      */   }
/*      */   
/*      */   public static double kgToTon(double kg)
/*      */   {
/* 3770 */     return kg / 1016.05D;
/*      */   }
/*      */   
/*      */   public static double tonToKg(double tons)
/*      */   {
/* 3775 */     return tons * 1016.05D;
/*      */   }
/*      */   
/*      */   public static double millimetreToInch(double mm)
/*      */   {
/* 3780 */     return mm / 25.4D;
/*      */   }
/*      */   
/*      */   public static double inchToMillimetre(double in)
/*      */   {
/* 3785 */     return in * 25.4D;
/*      */   }
/*      */   
/*      */   public static double footToMetre(double ft)
/*      */   {
/* 3790 */     return ft * 0.3048D;
/*      */   }
/*      */   
/*      */   public static double metreToFoot(double metre)
/*      */   {
/* 3795 */     return metre / 0.3048D;
/*      */   }
/*      */   
/*      */   public static double yardToMetre(double yd)
/*      */   {
/* 3800 */     return yd * 0.9144D;
/*      */   }
/*      */   
/*      */   public static double metreToYard(double metre)
/*      */   {
/* 3805 */     return metre / 0.9144D;
/*      */   }
/*      */   
/*      */   public static double mileToKm(double mile)
/*      */   {
/* 3810 */     return mile * 1.6093D;
/*      */   }
/*      */   
/*      */   public static double kmToMile(double km)
/*      */   {
/* 3815 */     return km / 1.6093D;
/*      */   }
/*      */   
/*      */   public static double gallonToLitre(double gall)
/*      */   {
/* 3820 */     return gall * 4.546D;
/*      */   }
/*      */   
/*      */   public static double litreToGallon(double litre)
/*      */   {
/* 3825 */     return litre / 4.546D;
/*      */   }
/*      */   
/*      */   public static double quartToLitre(double quart)
/*      */   {
/* 3830 */     return quart * 1.137D;
/*      */   }
/*      */   
/*      */   public static double litreToQuart(double litre)
/*      */   {
/* 3835 */     return litre / 1.137D;
/*      */   }
/*      */   
/*      */   public static double pintToLitre(double pint)
/*      */   {
/* 3840 */     return pint * 0.568D;
/*      */   }
/*      */   
/*      */   public static double litreToPint(double litre)
/*      */   {
/* 3845 */     return litre / 0.568D;
/*      */   }
/*      */   
/*      */   public static double gallonPerMileToLitrePerKm(double gallPmile)
/*      */   {
/* 3850 */     return gallPmile * 2.825D;
/*      */   }
/*      */   
/*      */   public static double litrePerKmToGallonPerMile(double litrePkm)
/*      */   {
/* 3855 */     return litrePkm / 2.825D;
/*      */   }
/*      */   
/*      */   public static double milePerGallonToKmPerLitre(double milePgall)
/*      */   {
/* 3860 */     return milePgall * 0.354D;
/*      */   }
/*      */   
/*      */   public static double kmPerLitreToMilePerGallon(double kmPlitre)
/*      */   {
/* 3865 */     return kmPlitre / 0.354D;
/*      */   }
/*      */   
/*      */   public static double fluidOunceUKtoUS(double flOzUK)
/*      */   {
/* 3870 */     return flOzUK * 0.961D;
/*      */   }
/*      */   
/*      */   public static double fluidOunceUStoUK(double flOzUS)
/*      */   {
/* 3875 */     return flOzUS * 1.041D;
/*      */   }
/*      */   
/*      */   public static double pintUKtoUS(double pintUK)
/*      */   {
/* 3880 */     return pintUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double pintUStoUK(double pintUS)
/*      */   {
/* 3885 */     return pintUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double quartUKtoUS(double quartUK)
/*      */   {
/* 3890 */     return quartUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double quartUStoUK(double quartUS)
/*      */   {
/* 3895 */     return quartUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double gallonUKtoUS(double gallonUK)
/*      */   {
/* 3900 */     return gallonUK * 1.201D;
/*      */   }
/*      */   
/*      */   public static double gallonUStoUK(double gallonUS)
/*      */   {
/* 3905 */     return gallonUS * 0.833D;
/*      */   }
/*      */   
/*      */   public static double pintUKtoCupUS(double pintUK)
/*      */   {
/* 3910 */     return pintUK / 0.417D;
/*      */   }
/*      */   
/*      */   public static double cupUStoPintUK(double cupUS)
/*      */   {
/* 3915 */     return cupUS * 0.417D;
/*      */   }
/*      */   
/*      */   public static double calcBMImetric(double height, double weight)
/*      */   {
/* 3920 */     return weight / (height * height);
/*      */   }
/*      */   
/*      */   public static double calcBMIimperial(double height, double weight)
/*      */   {
/* 3925 */     height = footToMetre(height);
/* 3926 */     weight = poundToKg(weight);
/* 3927 */     return weight / (height * height);
/*      */   }
/*      */   
/*      */   public static double calcWeightFromBMImetric(double bmi, double height)
/*      */   {
/* 3932 */     return bmi * height * height;
/*      */   }
/*      */   
/*      */   public static double calcWeightFromBMIimperial(double bmi, double height)
/*      */   {
/* 3937 */     height = footToMetre(height);
/* 3938 */     double weight = bmi * height * height;
/* 3939 */     weight = kgToPound(weight);
/* 3940 */     return weight;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/Fmath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */