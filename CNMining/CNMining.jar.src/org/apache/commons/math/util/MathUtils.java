/*      */ package org.apache.commons.math.util;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MathUtils
/*      */ {
/*      */   private static final byte NB = -1;
/*      */   private static final short NS = -1;
/*      */   private static final byte PB = 1;
/*      */   private static final short PS = 1;
/*      */   private static final byte ZB = 0;
/*      */   private static final short ZS = 0;
/*      */   private static final double TWO_PI = 6.283185307179586D;
/*      */   
/*      */   public static int addAndCheck(int x, int y)
/*      */   {
/*   67 */     long s = x + y;
/*   68 */     if ((s < -2147483648L) || (s > 2147483647L)) {
/*   69 */       throw new ArithmeticException("overflow: add");
/*      */     }
/*   71 */     return (int)s;
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
/*      */   public static long addAndCheck(long a, long b)
/*      */   {
/*   85 */     return addAndCheck(a, b, "overflow: add");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static long addAndCheck(long a, long b, String msg)
/*      */   {
/*      */     long ret;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  101 */     if (a > b)
/*      */     {
/*  103 */       ret = addAndCheck(b, a, msg);
/*      */     }
/*      */     else {
/*      */       long ret;
/*  107 */       if (a < 0L) {
/*  108 */         if (b < 0L) {
/*      */           long ret;
/*  110 */           if (Long.MIN_VALUE - b <= a) {
/*  111 */             ret = a + b;
/*      */           } else {
/*  113 */             throw new ArithmeticException(msg);
/*      */           }
/*      */         }
/*      */         else {
/*  117 */           ret = a + b;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*      */         long ret;
/*      */         
/*  124 */         if (a <= Long.MAX_VALUE - b) {
/*  125 */           ret = a + b;
/*      */         } else
/*  127 */           throw new ArithmeticException(msg);
/*      */       }
/*      */     }
/*      */     long ret;
/*  131 */     return ret;
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
/*      */   public static long binomialCoefficient(int n, int k)
/*      */   {
/*  161 */     if (n < k) {
/*  162 */       throw new IllegalArgumentException("must have n >= k for binomial coefficient (n,k)");
/*      */     }
/*      */     
/*  165 */     if (n < 0) {
/*  166 */       throw new IllegalArgumentException("must have n >= 0 for binomial coefficient (n,k)");
/*      */     }
/*      */     
/*  169 */     if ((n == k) || (k == 0)) {
/*  170 */       return 1L;
/*      */     }
/*  172 */     if ((k == 1) || (k == n - 1)) {
/*  173 */       return n;
/*      */     }
/*      */     
/*  176 */     long result = Math.round(binomialCoefficientDouble(n, k));
/*  177 */     if (result == Long.MAX_VALUE) {
/*  178 */       throw new ArithmeticException("result too large to represent in a long integer");
/*      */     }
/*      */     
/*  181 */     return result;
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
/*      */   public static double binomialCoefficientDouble(int n, int k)
/*      */   {
/*  207 */     return Math.floor(Math.exp(binomialCoefficientLog(n, k)) + 0.5D);
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
/*      */   public static double binomialCoefficientLog(int n, int k)
/*      */   {
/*  229 */     if (n < k) {
/*  230 */       throw new IllegalArgumentException("must have n >= k for binomial coefficient (n,k)");
/*      */     }
/*      */     
/*  233 */     if (n < 0) {
/*  234 */       throw new IllegalArgumentException("must have n >= 0 for binomial coefficient (n,k)");
/*      */     }
/*      */     
/*  237 */     if ((n == k) || (k == 0)) {
/*  238 */       return 0.0D;
/*      */     }
/*  240 */     if ((k == 1) || (k == n - 1)) {
/*  241 */       return Math.log(n);
/*      */     }
/*  243 */     double logSum = 0.0D;
/*      */     
/*      */ 
/*  246 */     for (int i = k + 1; i <= n; i++) {
/*  247 */       logSum += Math.log(i);
/*      */     }
/*      */     
/*      */ 
/*  251 */     for (int i = 2; i <= n - k; i++) {
/*  252 */       logSum -= Math.log(i);
/*      */     }
/*      */     
/*  255 */     return logSum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double cosh(double x)
/*      */   {
/*  266 */     return (Math.exp(x) + Math.exp(-x)) / 2.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean equals(double x, double y)
/*      */   {
/*  278 */     return ((Double.isNaN(x)) && (Double.isNaN(y))) || (x == y);
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
/*      */   public static boolean equals(double[] x, double[] y)
/*      */   {
/*  292 */     if ((x == null) || (y == null)) {
/*  293 */       return ((x == null ? 1 : 0) ^ (y == null ? 1 : 0)) == 0;
/*      */     }
/*  295 */     if (x.length != y.length) {
/*  296 */       return false;
/*      */     }
/*  298 */     for (int i = 0; i < x.length; i++) {
/*  299 */       if (!equals(x[i], y[i])) {
/*  300 */         return false;
/*      */       }
/*      */     }
/*  303 */     return true;
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
/*      */   public static long factorial(int n)
/*      */   {
/*  329 */     long result = Math.round(factorialDouble(n));
/*  330 */     if (result == Long.MAX_VALUE) {
/*  331 */       throw new ArithmeticException("result too large to represent in a long integer");
/*      */     }
/*      */     
/*  334 */     return result;
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
/*      */   public static double factorialDouble(int n)
/*      */   {
/*  358 */     if (n < 0) {
/*  359 */       throw new IllegalArgumentException("must have n >= 0 for n!");
/*      */     }
/*  361 */     return Math.floor(Math.exp(factorialLog(n)) + 0.5D);
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
/*      */   public static double factorialLog(int n)
/*      */   {
/*  378 */     if (n < 0) {
/*  379 */       throw new IllegalArgumentException("must have n > 0 for n!");
/*      */     }
/*  381 */     double logSum = 0.0D;
/*  382 */     for (int i = 2; i <= n; i++) {
/*  383 */       logSum += Math.log(i);
/*      */     }
/*  385 */     return logSum;
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
/*      */   public static int gcd(int u, int v)
/*      */   {
/*  402 */     if (u * v == 0) {
/*  403 */       return Math.abs(u) + Math.abs(v);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  410 */     if (u > 0) {
/*  411 */       u = -u;
/*      */     }
/*  413 */     if (v > 0) {
/*  414 */       v = -v;
/*      */     }
/*      */     
/*  417 */     int k = 0;
/*  418 */     while (((u & 0x1) == 0) && ((v & 0x1) == 0) && (k < 31))
/*      */     {
/*  420 */       u /= 2;
/*  421 */       v /= 2;
/*  422 */       k++;
/*      */     }
/*  424 */     if (k == 31) {
/*  425 */       throw new ArithmeticException("overflow: gcd is 2^31");
/*      */     }
/*      */     
/*      */ 
/*  429 */     int t = (u & 0x1) == 1 ? v : -(u / 2);
/*      */     
/*      */ 
/*      */ 
/*      */     do
/*      */     {
/*  435 */       while ((t & 0x1) == 0) {
/*  436 */         t /= 2;
/*      */       }
/*      */       
/*  439 */       if (t > 0) {
/*  440 */         u = -t;
/*      */       } else {
/*  442 */         v = t;
/*      */       }
/*      */       
/*  445 */       t = (v - u) / 2;
/*      */ 
/*      */     }
/*  448 */     while (t != 0);
/*  449 */     return -u * (1 << k);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int hash(double value)
/*      */   {
/*  459 */     long bits = Double.doubleToLongBits(value);
/*  460 */     return (int)(bits ^ bits >>> 32);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int hash(double[] value)
/*      */   {
/*  471 */     if (value == null) {
/*  472 */       return 0;
/*      */     }
/*  474 */     int result = value.length;
/*  475 */     for (int i = 0; i < value.length; i++) {
/*  476 */       result = result * 31 + hash(value[i]);
/*      */     }
/*  478 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte indicator(byte x)
/*      */   {
/*  489 */     return x >= 0 ? 1 : -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double indicator(double x)
/*      */   {
/*  501 */     if (Double.isNaN(x)) {
/*  502 */       return NaN.0D;
/*      */     }
/*  504 */     return x >= 0.0D ? 1.0D : -1.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static float indicator(float x)
/*      */   {
/*  515 */     if (Float.isNaN(x)) {
/*  516 */       return NaN.0F;
/*      */     }
/*  518 */     return x >= 0.0F ? 1.0F : -1.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int indicator(int x)
/*      */   {
/*  528 */     return x >= 0 ? 1 : -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static long indicator(long x)
/*      */   {
/*  538 */     return x >= 0L ? 1L : -1L;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static short indicator(short x)
/*      */   {
/*  549 */     return x >= 0 ? 1 : -1;
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
/*      */   public static int lcm(int a, int b)
/*      */   {
/*  562 */     return Math.abs(mulAndCheck(a / gcd(a, b), b));
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
/*      */   public static double log(double base, double x)
/*      */   {
/*  582 */     return Math.log(x) / Math.log(base);
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
/*      */   public static int mulAndCheck(int x, int y)
/*      */   {
/*  596 */     long m = x * y;
/*  597 */     if ((m < -2147483648L) || (m > 2147483647L)) {
/*  598 */       throw new ArithmeticException("overflow: mul");
/*      */     }
/*  600 */     return (int)m;
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
/*      */   public static long mulAndCheck(long a, long b)
/*      */   {
/*  615 */     String msg = "overflow: multiply";
/*  616 */     long ret; long ret; if (a > b)
/*      */     {
/*  618 */       ret = mulAndCheck(b, a);
/*      */     } else { long ret;
/*  620 */       if (a < 0L) {
/*  621 */         if (b < 0L) {
/*      */           long ret;
/*  623 */           if (a >= Long.MAX_VALUE / b) {
/*  624 */             ret = a * b;
/*      */           } else {
/*  626 */             throw new ArithmeticException(msg);
/*      */           }
/*  628 */         } else if (b > 0L) {
/*      */           long ret;
/*  630 */           if (Long.MIN_VALUE / b <= a) {
/*  631 */             ret = a * b;
/*      */           } else {
/*  633 */             throw new ArithmeticException(msg);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  638 */           ret = 0L;
/*      */         }
/*  640 */       } else if (a > 0L)
/*      */       {
/*      */         long ret;
/*      */         
/*      */ 
/*  645 */         if (a <= Long.MAX_VALUE / b) {
/*  646 */           ret = a * b;
/*      */         } else {
/*  648 */           throw new ArithmeticException(msg);
/*      */         }
/*      */       }
/*      */       else {
/*  652 */         ret = 0L;
/*      */       }
/*      */     }
/*  655 */     return ret;
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
/*      */   public static double nextAfter(double d, double direction)
/*      */   {
/*  678 */     if ((Double.isNaN(d)) || (Double.isInfinite(d)))
/*  679 */       return d;
/*  680 */     if (d == 0.0D) {
/*  681 */       return direction < 0.0D ? -4.9E-324D : Double.MIN_VALUE;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  687 */     long bits = Double.doubleToLongBits(d);
/*  688 */     long sign = bits & 0x8000000000000000;
/*  689 */     long exponent = bits & 0x7FF0000000000000;
/*  690 */     long mantissa = bits & 0xFFFFFFFFFFFFF;
/*      */     
/*  692 */     if (d * (direction - d) >= 0.0D)
/*      */     {
/*  694 */       if (mantissa == 4503599627370495L) {
/*  695 */         return Double.longBitsToDouble(sign | exponent + 4503599627370496L);
/*      */       }
/*      */       
/*  698 */       return Double.longBitsToDouble(sign | exponent | mantissa + 1L);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  703 */     if (mantissa == 0L) {
/*  704 */       return Double.longBitsToDouble(sign | exponent - 4503599627370496L | 0xFFFFFFFFFFFFF);
/*      */     }
/*      */     
/*      */ 
/*  708 */     return Double.longBitsToDouble(sign | exponent | mantissa - 1L);
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
/*      */   public static double normalizeAngle(double a, double center)
/*      */   {
/*  735 */     return a - 6.283185307179586D * Math.floor((a + 3.141592653589793D - center) / 6.283185307179586D);
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
/*      */   public static double round(double x, int scale)
/*      */   {
/*  748 */     return round(x, scale, 4);
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
/*      */   public static double round(double x, int scale, int roundingMethod)
/*      */   {
/*      */     try
/*      */     {
/*  765 */       return new BigDecimal(Double.toString(x)).setScale(scale, roundingMethod).doubleValue();
/*      */ 
/*      */     }
/*      */     catch (NumberFormatException ex)
/*      */     {
/*  770 */       if (Double.isInfinite(x))
/*  771 */         return x;
/*      */     }
/*  773 */     return NaN.0D;
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
/*      */   public static float round(float x, int scale)
/*      */   {
/*  788 */     return round(x, scale, 4);
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
/*      */   public static float round(float x, int scale, int roundingMethod)
/*      */   {
/*  804 */     float sign = indicator(x);
/*  805 */     float factor = (float)Math.pow(10.0D, scale) * sign;
/*  806 */     return (float)roundUnscaled(x * factor, sign, roundingMethod) / factor;
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
/*      */   private static double roundUnscaled(double unscaled, double sign, int roundingMethod)
/*      */   {
/*  823 */     switch (roundingMethod) {
/*      */     case 2: 
/*  825 */       if (sign == -1.0D) {
/*  826 */         unscaled = Math.floor(nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/*      */       } else {
/*  828 */         unscaled = Math.ceil(nextAfter(unscaled, Double.POSITIVE_INFINITY));
/*      */       }
/*  830 */       break;
/*      */     case 1: 
/*  832 */       unscaled = Math.floor(nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/*  833 */       break;
/*      */     case 3: 
/*  835 */       if (sign == -1.0D) {
/*  836 */         unscaled = Math.ceil(nextAfter(unscaled, Double.POSITIVE_INFINITY));
/*      */       } else {
/*  838 */         unscaled = Math.floor(nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/*      */       }
/*  840 */       break;
/*      */     case 5: 
/*  842 */       unscaled = nextAfter(unscaled, Double.NEGATIVE_INFINITY);
/*  843 */       double fraction = unscaled - Math.floor(unscaled);
/*  844 */       if (fraction > 0.5D) {
/*  845 */         unscaled = Math.ceil(unscaled);
/*      */       } else {
/*  847 */         unscaled = Math.floor(unscaled);
/*      */       }
/*  849 */       break;
/*      */     
/*      */     case 6: 
/*  852 */       double fraction = unscaled - Math.floor(unscaled);
/*  853 */       if (fraction > 0.5D) {
/*  854 */         unscaled = Math.ceil(unscaled);
/*  855 */       } else if (fraction < 0.5D) {
/*  856 */         unscaled = Math.floor(unscaled);
/*      */ 
/*      */       }
/*  859 */       else if (Math.floor(unscaled) / 2.0D == Math.floor(Math.floor(unscaled) / 2.0D))
/*      */       {
/*  861 */         unscaled = Math.floor(unscaled);
/*      */       } else {
/*  863 */         unscaled = Math.ceil(unscaled);
/*      */       }
/*      */       
/*  866 */       break;
/*      */     
/*      */     case 4: 
/*  869 */       unscaled = nextAfter(unscaled, Double.POSITIVE_INFINITY);
/*  870 */       double fraction = unscaled - Math.floor(unscaled);
/*  871 */       if (fraction >= 0.5D) {
/*  872 */         unscaled = Math.ceil(unscaled);
/*      */       } else {
/*  874 */         unscaled = Math.floor(unscaled);
/*      */       }
/*  876 */       break;
/*      */     
/*      */     case 7: 
/*  879 */       if (unscaled != Math.floor(unscaled)) {
/*  880 */         throw new ArithmeticException("Inexact result from rounding");
/*      */       }
/*      */       break;
/*      */     case 0: 
/*  884 */       unscaled = Math.ceil(nextAfter(unscaled, Double.POSITIVE_INFINITY));
/*  885 */       break;
/*      */     default: 
/*  887 */       throw new IllegalArgumentException("Invalid rounding method.");
/*      */     }
/*  889 */     return unscaled;
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
/*      */   public static byte sign(byte x)
/*      */   {
/*  903 */     return x > 0 ? 1 : x == 0 ? 0 : -1;
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
/*      */   public static double sign(double x)
/*      */   {
/*  919 */     if (Double.isNaN(x)) {
/*  920 */       return NaN.0D;
/*      */     }
/*  922 */     return x > 0.0D ? 1.0D : x == 0.0D ? 0.0D : -1.0D;
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
/*      */   public static float sign(float x)
/*      */   {
/*  937 */     if (Float.isNaN(x)) {
/*  938 */       return NaN.0F;
/*      */     }
/*  940 */     return x > 0.0F ? 1.0F : x == 0.0F ? 0.0F : -1.0F;
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
/*      */   public static int sign(int x)
/*      */   {
/*  954 */     return x > 0 ? 1 : x == 0 ? 0 : -1;
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
/*      */   public static long sign(long x)
/*      */   {
/*  968 */     return x > 0L ? 1L : x == 0L ? 0L : -1L;
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
/*      */   public static short sign(short x)
/*      */   {
/*  983 */     return x > 0 ? 1 : x == 0 ? 0 : -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sinh(double x)
/*      */   {
/*  994 */     return (Math.exp(x) - Math.exp(-x)) / 2.0D;
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
/*      */   public static int subAndCheck(int x, int y)
/*      */   {
/* 1008 */     long s = x - y;
/* 1009 */     if ((s < -2147483648L) || (s > 2147483647L)) {
/* 1010 */       throw new ArithmeticException("overflow: subtract");
/*      */     }
/* 1012 */     return (int)s;
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
/*      */   public static long subAndCheck(long a, long b)
/*      */   {
/* 1027 */     String msg = "overflow: subtract";
/* 1028 */     long ret; if (b == Long.MIN_VALUE) { long ret;
/* 1029 */       if (a < 0L) {
/* 1030 */         ret = a - b;
/*      */       } else {
/* 1032 */         throw new ArithmeticException(msg);
/*      */       }
/*      */     }
/*      */     else {
/* 1036 */       ret = addAndCheck(a, -b, msg);
/*      */     }
/* 1038 */     return ret;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/MathUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */