/*      */ package cern.colt;
/*      */ 
/*      */ import cern.colt.function.ByteComparator;
/*      */ import cern.colt.function.CharComparator;
/*      */ import cern.colt.function.DoubleComparator;
/*      */ import cern.colt.function.FloatComparator;
/*      */ import cern.colt.function.IntComparator;
/*      */ import cern.colt.function.LongComparator;
/*      */ import cern.colt.function.ShortComparator;
/*      */ import cern.colt.list.DoubleArrayList;
/*      */ import cern.colt.list.FloatArrayList;
/*      */ import java.util.Comparator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Sorting
/*      */ {
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 40;
/*      */   
/*      */   public static int binarySearchFromTo(byte[] paramArrayOfByte, byte paramByte, int paramInt1, int paramInt2)
/*      */   {
/*   58 */     while (paramInt1 <= paramInt2) {
/*   59 */       int i = (paramInt1 + paramInt2) / 2;
/*   60 */       byte b = paramArrayOfByte[i];
/*   61 */       if (b < paramByte) { paramInt1 = i + 1;
/*   62 */       } else if (b > paramByte) paramInt2 = i - 1; else
/*   63 */         return i;
/*      */     }
/*   65 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(char[] paramArrayOfChar, char paramChar, int paramInt1, int paramInt2)
/*      */   {
/*   92 */     while (paramInt1 <= paramInt2) {
/*   93 */       int i = (paramInt1 + paramInt2) / 2;
/*   94 */       char c = paramArrayOfChar[i];
/*   95 */       if (c < paramChar) { paramInt1 = i + 1;
/*   96 */       } else if (c > paramChar) paramInt2 = i - 1; else
/*   97 */         return i;
/*      */     }
/*   99 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(double[] paramArrayOfDouble, double paramDouble, int paramInt1, int paramInt2)
/*      */   {
/*  126 */     while (paramInt1 <= paramInt2) {
/*  127 */       int i = (paramInt1 + paramInt2) / 2;
/*  128 */       double d = paramArrayOfDouble[i];
/*  129 */       if (d < paramDouble) { paramInt1 = i + 1;
/*  130 */       } else if (d > paramDouble) paramInt2 = i - 1; else
/*  131 */         return i;
/*      */     }
/*  133 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(float[] paramArrayOfFloat, float paramFloat, int paramInt1, int paramInt2)
/*      */   {
/*  160 */     while (paramInt1 <= paramInt2) {
/*  161 */       int i = (paramInt1 + paramInt2) / 2;
/*  162 */       float f = paramArrayOfFloat[i];
/*  163 */       if (f < paramFloat) { paramInt1 = i + 1;
/*  164 */       } else if (f > paramFloat) paramInt2 = i - 1; else
/*  165 */         return i;
/*      */     }
/*  167 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  194 */     while (paramInt2 <= paramInt3) {
/*  195 */       int j = (paramInt2 + paramInt3) / 2;
/*  196 */       int i = paramArrayOfInt[j];
/*  197 */       if (i < paramInt1) { paramInt2 = j + 1;
/*  198 */       } else if (i > paramInt1) paramInt3 = j - 1; else
/*  199 */         return j;
/*      */     }
/*  201 */     return -(paramInt2 + 1);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int binarySearchFromTo(long[] paramArrayOfLong, long paramLong, int paramInt1, int paramInt2)
/*      */   {
/*  237 */     while (paramInt1 <= paramInt2) {
/*  238 */       int i = (paramInt1 + paramInt2) / 2;
/*  239 */       long l = paramArrayOfLong[i];
/*  240 */       if (l < paramLong) { paramInt1 = i + 1;
/*  241 */       } else if (l > paramLong) paramInt2 = i - 1; else
/*  242 */         return i;
/*      */     }
/*  244 */     return -(paramInt1 + 1);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int binarySearchFromTo(Object[] paramArrayOfObject, Object paramObject, int paramInt1, int paramInt2, Comparator paramComparator)
/*      */   {
/*  281 */     while (paramInt1 <= paramInt2) {
/*  282 */       int i = (paramInt1 + paramInt2) / 2;
/*  283 */       Object localObject = paramArrayOfObject[i];
/*  284 */       int j = paramComparator.compare(localObject, paramObject);
/*      */       
/*  286 */       if (j < 0) { paramInt1 = i + 1;
/*  287 */       } else if (j > 0) paramInt2 = i - 1; else
/*  288 */         return i;
/*      */     }
/*  290 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(short[] paramArrayOfShort, short paramShort, int paramInt1, int paramInt2)
/*      */   {
/*  317 */     while (paramInt1 <= paramInt2) {
/*  318 */       int i = (paramInt1 + paramInt2) / 2;
/*  319 */       short s = paramArrayOfShort[i];
/*  320 */       if (s < paramShort) { paramInt1 = i + 1;
/*  321 */       } else if (s > paramShort) paramInt2 = i - 1; else
/*  322 */         return i;
/*      */     }
/*  324 */     return -(paramInt1 + 1);
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
/*      */   public static int binarySearchFromTo(int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*      */   {
/*  351 */     while (paramInt1 <= paramInt2) {
/*  352 */       int i = (paramInt1 + paramInt2) / 2;
/*  353 */       int j = paramIntComparator.compare(0, i);
/*  354 */       if (j < 0) { paramInt1 = i + 1;
/*  355 */       } else if (j > 0) paramInt2 = i - 1; else
/*  356 */         return i;
/*      */     }
/*  358 */     return -(paramInt1 + 1);
/*      */   }
/*      */   
/*  361 */   private static void inplace_merge(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3) { if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3)) return;
/*      */     int i;
/*  363 */     if (paramInt3 - paramInt1 == 2) {
/*  364 */       if (paramArrayOfInt[paramInt2] < paramArrayOfInt[paramInt1]) {
/*  365 */         i = paramArrayOfInt[paramInt1];
/*  366 */         paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
/*  367 */         paramArrayOfInt[paramInt2] = i;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     int j;
/*  373 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/*  374 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  375 */       j = jal.INT.Sorting.lower_bound(paramArrayOfInt, paramInt2, paramInt3, paramArrayOfInt[i]);
/*      */     } else {
/*  377 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/*  378 */       i = jal.INT.Sorting.upper_bound(paramArrayOfInt, paramInt1, paramInt2, paramArrayOfInt[j]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  385 */     int k = i;int m = paramInt2;int n = j;
/*  386 */     if ((m != k) && (m != n)) {
/*  387 */       int i1 = k;int i2 = m;
/*      */       int i3;
/*  389 */       for (; i1 < --i2; paramArrayOfInt[(i1++)] = i3) { i3 = paramArrayOfInt[i1];paramArrayOfInt[i2] = paramArrayOfInt[i1]; }
/*  390 */       i1 = m;i2 = n;
/*  391 */       for (; i1 < --i2; paramArrayOfInt[(i1++)] = i3) { i3 = paramArrayOfInt[i1];paramArrayOfInt[i2] = paramArrayOfInt[i1]; }
/*  392 */       i1 = k;i2 = n;
/*  393 */       for (; i1 < --i2; paramArrayOfInt[(i1++)] = i3) { i3 = paramArrayOfInt[i1];paramArrayOfInt[i2] = paramArrayOfInt[i1];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  398 */     paramInt2 = i + (j - paramInt2);
/*  399 */     inplace_merge(paramArrayOfInt, paramInt1, i, paramInt2);
/*  400 */     inplace_merge(paramArrayOfInt, paramInt2, j, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */   private static int med3(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, ByteComparator paramByteComparator)
/*      */   {
/*  406 */     int i = paramByteComparator.compare(paramArrayOfByte[paramInt1], paramArrayOfByte[paramInt2]);
/*  407 */     int j = paramByteComparator.compare(paramArrayOfByte[paramInt1], paramArrayOfByte[paramInt3]);
/*  408 */     int k = paramByteComparator.compare(paramArrayOfByte[paramInt2], paramArrayOfByte[paramInt3]);
/*  409 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, CharComparator paramCharComparator)
/*      */   {
/*  417 */     int i = paramCharComparator.compare(paramArrayOfChar[paramInt1], paramArrayOfChar[paramInt2]);
/*  418 */     int j = paramCharComparator.compare(paramArrayOfChar[paramInt1], paramArrayOfChar[paramInt3]);
/*  419 */     int k = paramCharComparator.compare(paramArrayOfChar[paramInt2], paramArrayOfChar[paramInt3]);
/*  420 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, DoubleComparator paramDoubleComparator)
/*      */   {
/*  428 */     int i = paramDoubleComparator.compare(paramArrayOfDouble[paramInt1], paramArrayOfDouble[paramInt2]);
/*  429 */     int j = paramDoubleComparator.compare(paramArrayOfDouble[paramInt1], paramArrayOfDouble[paramInt3]);
/*  430 */     int k = paramDoubleComparator.compare(paramArrayOfDouble[paramInt2], paramArrayOfDouble[paramInt3]);
/*  431 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, FloatComparator paramFloatComparator)
/*      */   {
/*  439 */     int i = paramFloatComparator.compare(paramArrayOfFloat[paramInt1], paramArrayOfFloat[paramInt2]);
/*  440 */     int j = paramFloatComparator.compare(paramArrayOfFloat[paramInt1], paramArrayOfFloat[paramInt3]);
/*  441 */     int k = paramFloatComparator.compare(paramArrayOfFloat[paramInt2], paramArrayOfFloat[paramInt3]);
/*  442 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*      */   {
/*  450 */     int i = paramIntComparator.compare(paramArrayOfInt[paramInt1], paramArrayOfInt[paramInt2]);
/*  451 */     int j = paramIntComparator.compare(paramArrayOfInt[paramInt1], paramArrayOfInt[paramInt3]);
/*  452 */     int k = paramIntComparator.compare(paramArrayOfInt[paramInt2], paramArrayOfInt[paramInt3]);
/*  453 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(long[] paramArrayOfLong, int paramInt1, int paramInt2, int paramInt3, LongComparator paramLongComparator)
/*      */   {
/*  461 */     int i = paramLongComparator.compare(paramArrayOfLong[paramInt1], paramArrayOfLong[paramInt2]);
/*  462 */     int j = paramLongComparator.compare(paramArrayOfLong[paramInt1], paramArrayOfLong[paramInt3]);
/*  463 */     int k = paramLongComparator.compare(paramArrayOfLong[paramInt2], paramArrayOfLong[paramInt3]);
/*  464 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  472 */     int i = ((Comparable)paramArrayOfObject[paramInt1]).compareTo((Comparable)paramArrayOfObject[paramInt2]);
/*  473 */     int j = ((Comparable)paramArrayOfObject[paramInt1]).compareTo((Comparable)paramArrayOfObject[paramInt3]);
/*  474 */     int k = ((Comparable)paramArrayOfObject[paramInt2]).compareTo((Comparable)paramArrayOfObject[paramInt3]);
/*  475 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, Comparator paramComparator)
/*      */   {
/*  483 */     int i = paramComparator.compare(paramArrayOfObject[paramInt1], paramArrayOfObject[paramInt2]);
/*  484 */     int j = paramComparator.compare(paramArrayOfObject[paramInt1], paramArrayOfObject[paramInt3]);
/*  485 */     int k = paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[paramInt3]);
/*  486 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3, ShortComparator paramShortComparator)
/*      */   {
/*  494 */     int i = paramShortComparator.compare(paramArrayOfShort[paramInt1], paramArrayOfShort[paramInt2]);
/*  495 */     int j = paramShortComparator.compare(paramArrayOfShort[paramInt1], paramArrayOfShort[paramInt3]);
/*  496 */     int k = paramShortComparator.compare(paramArrayOfShort[paramInt2], paramArrayOfShort[paramInt3]);
/*  497 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
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
/*      */   public static void mergeSort(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  522 */     rangeCheck(paramArrayOfByte.length, paramInt1, paramInt2);
/*  523 */     byte[] arrayOfByte = (byte[])paramArrayOfByte.clone();
/*  524 */     mergeSort1(arrayOfByte, paramArrayOfByte, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ByteComparator paramByteComparator)
/*      */   {
/*  556 */     rangeCheck(paramArrayOfByte.length, paramInt1, paramInt2);
/*  557 */     byte[] arrayOfByte = (byte[])paramArrayOfByte.clone();
/*  558 */     mergeSort1(arrayOfByte, paramArrayOfByte, paramInt1, paramInt2, paramByteComparator);
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
/*      */   public static void mergeSort(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */   {
/*  581 */     rangeCheck(paramArrayOfChar.length, paramInt1, paramInt2);
/*  582 */     char[] arrayOfChar = (char[])paramArrayOfChar.clone();
/*  583 */     mergeSort1(arrayOfChar, paramArrayOfChar, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(char[] paramArrayOfChar, int paramInt1, int paramInt2, CharComparator paramCharComparator)
/*      */   {
/*  615 */     rangeCheck(paramArrayOfChar.length, paramInt1, paramInt2);
/*  616 */     char[] arrayOfChar = (char[])paramArrayOfChar.clone();
/*  617 */     mergeSort1(arrayOfChar, paramArrayOfChar, paramInt1, paramInt2, paramCharComparator);
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
/*      */   public static void mergeSort(double[] paramArrayOfDouble, int paramInt1, int paramInt2)
/*      */   {
/*  640 */     mergeSort2(paramArrayOfDouble, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(double[] paramArrayOfDouble, int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator)
/*      */   {
/*  672 */     rangeCheck(paramArrayOfDouble.length, paramInt1, paramInt2);
/*  673 */     double[] arrayOfDouble = (double[])paramArrayOfDouble.clone();
/*  674 */     mergeSort1(arrayOfDouble, paramArrayOfDouble, paramInt1, paramInt2, paramDoubleComparator);
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
/*      */   public static void mergeSort(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
/*      */   {
/*  697 */     mergeSort2(paramArrayOfFloat, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(float[] paramArrayOfFloat, int paramInt1, int paramInt2, FloatComparator paramFloatComparator)
/*      */   {
/*  729 */     rangeCheck(paramArrayOfFloat.length, paramInt1, paramInt2);
/*  730 */     float[] arrayOfFloat = (float[])paramArrayOfFloat.clone();
/*  731 */     mergeSort1(arrayOfFloat, paramArrayOfFloat, paramInt1, paramInt2, paramFloatComparator);
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
/*      */   public static void mergeSort(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */   {
/*  754 */     rangeCheck(paramArrayOfInt.length, paramInt1, paramInt2);
/*  755 */     int[] arrayOfInt = (int[])paramArrayOfInt.clone();
/*  756 */     mergeSort1(arrayOfInt, paramArrayOfInt, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(int[] paramArrayOfInt, int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*      */   {
/*  788 */     rangeCheck(paramArrayOfInt.length, paramInt1, paramInt2);
/*  789 */     int[] arrayOfInt = (int[])paramArrayOfInt.clone();
/*  790 */     mergeSort1(arrayOfInt, paramArrayOfInt, paramInt1, paramInt2, paramIntComparator);
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
/*      */   public static void mergeSort(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*      */   {
/*  813 */     rangeCheck(paramArrayOfLong.length, paramInt1, paramInt2);
/*  814 */     long[] arrayOfLong = (long[])paramArrayOfLong.clone();
/*  815 */     mergeSort1(arrayOfLong, paramArrayOfLong, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(long[] paramArrayOfLong, int paramInt1, int paramInt2, LongComparator paramLongComparator)
/*      */   {
/*  847 */     rangeCheck(paramArrayOfLong.length, paramInt1, paramInt2);
/*  848 */     long[] arrayOfLong = (long[])paramArrayOfLong.clone();
/*  849 */     mergeSort1(arrayOfLong, paramArrayOfLong, paramInt1, paramInt2, paramLongComparator);
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
/*      */   public static void mergeSort(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*      */   {
/*  872 */     rangeCheck(paramArrayOfShort.length, paramInt1, paramInt2);
/*  873 */     short[] arrayOfShort = (short[])paramArrayOfShort.clone();
/*  874 */     mergeSort1(arrayOfShort, paramArrayOfShort, paramInt1, paramInt2);
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
/*      */   public static void mergeSort(short[] paramArrayOfShort, int paramInt1, int paramInt2, ShortComparator paramShortComparator)
/*      */   {
/*  906 */     rangeCheck(paramArrayOfShort.length, paramInt1, paramInt2);
/*  907 */     short[] arrayOfShort = (short[])paramArrayOfShort.clone();
/*  908 */     mergeSort1(arrayOfShort, paramArrayOfShort, paramInt1, paramInt2, paramShortComparator);
/*      */   }
/*      */   
/*  911 */   private static void mergeSort1(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2) { int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/*  914 */     if (i < 7) {
/*  915 */       for (j = paramInt1; j < paramInt2; j++)
/*  916 */         for (k = j; (k > paramInt1) && (paramArrayOfByte2[(k - 1)] > paramArrayOfByte2[k]); k--)
/*  917 */           swap(paramArrayOfByte2, k, k - 1);
/*  918 */       return;
/*      */     }
/*      */     
/*      */ 
/*  922 */     int j = (paramInt1 + paramInt2) / 2;
/*  923 */     mergeSort1(paramArrayOfByte2, paramArrayOfByte1, paramInt1, j);
/*  924 */     mergeSort1(paramArrayOfByte2, paramArrayOfByte1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/*  928 */     if (paramArrayOfByte1[(j - 1)] <= paramArrayOfByte1[j]) {
/*  929 */       System.arraycopy(paramArrayOfByte1, paramInt1, paramArrayOfByte2, paramInt1, i);
/*  930 */       return;
/*      */     }
/*      */     
/*      */ 
/*  934 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/*  935 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfByte1[m] <= paramArrayOfByte1[n]))) {
/*  936 */         paramArrayOfByte2[k] = paramArrayOfByte1[(m++)];
/*      */       } else
/*  938 */         paramArrayOfByte2[k] = paramArrayOfByte1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, ByteComparator paramByteComparator) {
/*  942 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/*  945 */     if (i < 7) {
/*  946 */       for (j = paramInt1; j < paramInt2; j++)
/*  947 */         for (k = j; (k > paramInt1) && (paramByteComparator.compare(paramArrayOfByte2[(k - 1)], paramArrayOfByte2[k]) > 0); k--)
/*  948 */           swap(paramArrayOfByte2, k, k - 1);
/*  949 */       return;
/*      */     }
/*      */     
/*      */ 
/*  953 */     int j = (paramInt1 + paramInt2) / 2;
/*  954 */     mergeSort1(paramArrayOfByte2, paramArrayOfByte1, paramInt1, j, paramByteComparator);
/*  955 */     mergeSort1(paramArrayOfByte2, paramArrayOfByte1, j, paramInt2, paramByteComparator);
/*      */     
/*      */ 
/*      */ 
/*  959 */     if (paramByteComparator.compare(paramArrayOfByte1[(j - 1)], paramArrayOfByte1[j]) <= 0) {
/*  960 */       System.arraycopy(paramArrayOfByte1, paramInt1, paramArrayOfByte2, paramInt1, i);
/*  961 */       return;
/*      */     }
/*      */     
/*      */ 
/*  965 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/*  966 */       if ((n >= paramInt2) || ((m < j) && (paramByteComparator.compare(paramArrayOfByte1[m], paramArrayOfByte1[n]) <= 0))) {
/*  967 */         paramArrayOfByte2[k] = paramArrayOfByte1[(m++)];
/*      */       } else
/*  969 */         paramArrayOfByte2[k] = paramArrayOfByte1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2) {
/*  973 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/*  976 */     if (i < 7) {
/*  977 */       for (j = paramInt1; j < paramInt2; j++)
/*  978 */         for (k = j; (k > paramInt1) && (paramArrayOfChar2[(k - 1)] > paramArrayOfChar2[k]); k--)
/*  979 */           swap(paramArrayOfChar2, k, k - 1);
/*  980 */       return;
/*      */     }
/*      */     
/*      */ 
/*  984 */     int j = (paramInt1 + paramInt2) / 2;
/*  985 */     mergeSort1(paramArrayOfChar2, paramArrayOfChar1, paramInt1, j);
/*  986 */     mergeSort1(paramArrayOfChar2, paramArrayOfChar1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/*  990 */     if (paramArrayOfChar1[(j - 1)] <= paramArrayOfChar1[j]) {
/*  991 */       System.arraycopy(paramArrayOfChar1, paramInt1, paramArrayOfChar2, paramInt1, i);
/*  992 */       return;
/*      */     }
/*      */     
/*      */ 
/*  996 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/*  997 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfChar1[m] <= paramArrayOfChar1[n]))) {
/*  998 */         paramArrayOfChar2[k] = paramArrayOfChar1[(m++)];
/*      */       } else
/* 1000 */         paramArrayOfChar2[k] = paramArrayOfChar1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, CharComparator paramCharComparator) {
/* 1004 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1007 */     if (i < 7) {
/* 1008 */       for (j = paramInt1; j < paramInt2; j++)
/* 1009 */         for (k = j; (k > paramInt1) && (paramCharComparator.compare(paramArrayOfChar2[(k - 1)], paramArrayOfChar2[k]) > 0); k--)
/* 1010 */           swap(paramArrayOfChar2, k, k - 1);
/* 1011 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1015 */     int j = (paramInt1 + paramInt2) / 2;
/* 1016 */     mergeSort1(paramArrayOfChar2, paramArrayOfChar1, paramInt1, j, paramCharComparator);
/* 1017 */     mergeSort1(paramArrayOfChar2, paramArrayOfChar1, j, paramInt2, paramCharComparator);
/*      */     
/*      */ 
/*      */ 
/* 1021 */     if (paramCharComparator.compare(paramArrayOfChar1[(j - 1)], paramArrayOfChar1[j]) <= 0) {
/* 1022 */       System.arraycopy(paramArrayOfChar1, paramInt1, paramArrayOfChar2, paramInt1, i);
/* 1023 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1027 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1028 */       if ((n >= paramInt2) || ((m < j) && (paramCharComparator.compare(paramArrayOfChar1[m], paramArrayOfChar1[n]) <= 0))) {
/* 1029 */         paramArrayOfChar2[k] = paramArrayOfChar1[(m++)];
/*      */       } else
/* 1031 */         paramArrayOfChar2[k] = paramArrayOfChar1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2) {
/* 1035 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1038 */     if (i < 7) {
/* 1039 */       for (j = paramInt1; j < paramInt2; j++)
/* 1040 */         for (k = j; (k > paramInt1) && (paramArrayOfDouble2[(k - 1)] > paramArrayOfDouble2[k]); k--)
/* 1041 */           swap(paramArrayOfDouble2, k, k - 1);
/* 1042 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1046 */     int j = (paramInt1 + paramInt2) / 2;
/* 1047 */     mergeSort1(paramArrayOfDouble2, paramArrayOfDouble1, paramInt1, j);
/* 1048 */     mergeSort1(paramArrayOfDouble2, paramArrayOfDouble1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1052 */     if (paramArrayOfDouble1[(j - 1)] <= paramArrayOfDouble1[j]) {
/* 1053 */       System.arraycopy(paramArrayOfDouble1, paramInt1, paramArrayOfDouble2, paramInt1, i);
/* 1054 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1058 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1059 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfDouble1[m] <= paramArrayOfDouble1[n]))) {
/* 1060 */         paramArrayOfDouble2[k] = paramArrayOfDouble1[(m++)];
/*      */       } else
/* 1062 */         paramArrayOfDouble2[k] = paramArrayOfDouble1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator) {
/* 1066 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1069 */     if (i < 7) {
/* 1070 */       for (j = paramInt1; j < paramInt2; j++)
/* 1071 */         for (k = j; (k > paramInt1) && (paramDoubleComparator.compare(paramArrayOfDouble2[(k - 1)], paramArrayOfDouble2[k]) > 0); k--)
/* 1072 */           swap(paramArrayOfDouble2, k, k - 1);
/* 1073 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1077 */     int j = (paramInt1 + paramInt2) / 2;
/* 1078 */     mergeSort1(paramArrayOfDouble2, paramArrayOfDouble1, paramInt1, j, paramDoubleComparator);
/* 1079 */     mergeSort1(paramArrayOfDouble2, paramArrayOfDouble1, j, paramInt2, paramDoubleComparator);
/*      */     
/*      */ 
/*      */ 
/* 1083 */     if (paramDoubleComparator.compare(paramArrayOfDouble1[(j - 1)], paramArrayOfDouble1[j]) <= 0) {
/* 1084 */       System.arraycopy(paramArrayOfDouble1, paramInt1, paramArrayOfDouble2, paramInt1, i);
/* 1085 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1089 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1090 */       if ((n >= paramInt2) || ((m < j) && (paramDoubleComparator.compare(paramArrayOfDouble1[m], paramArrayOfDouble1[n]) <= 0))) {
/* 1091 */         paramArrayOfDouble2[k] = paramArrayOfDouble1[(m++)];
/*      */       } else
/* 1093 */         paramArrayOfDouble2[k] = paramArrayOfDouble1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2) {
/* 1097 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1100 */     if (i < 7) {
/* 1101 */       for (j = paramInt1; j < paramInt2; j++)
/* 1102 */         for (k = j; (k > paramInt1) && (paramArrayOfFloat2[(k - 1)] > paramArrayOfFloat2[k]); k--)
/* 1103 */           swap(paramArrayOfFloat2, k, k - 1);
/* 1104 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1108 */     int j = (paramInt1 + paramInt2) / 2;
/* 1109 */     mergeSort1(paramArrayOfFloat2, paramArrayOfFloat1, paramInt1, j);
/* 1110 */     mergeSort1(paramArrayOfFloat2, paramArrayOfFloat1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1114 */     if (paramArrayOfFloat1[(j - 1)] <= paramArrayOfFloat1[j]) {
/* 1115 */       System.arraycopy(paramArrayOfFloat1, paramInt1, paramArrayOfFloat2, paramInt1, i);
/* 1116 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1120 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1121 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfFloat1[m] <= paramArrayOfFloat1[n]))) {
/* 1122 */         paramArrayOfFloat2[k] = paramArrayOfFloat1[(m++)];
/*      */       } else
/* 1124 */         paramArrayOfFloat2[k] = paramArrayOfFloat1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, FloatComparator paramFloatComparator) {
/* 1128 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1131 */     if (i < 7) {
/* 1132 */       for (j = paramInt1; j < paramInt2; j++)
/* 1133 */         for (k = j; (k > paramInt1) && (paramFloatComparator.compare(paramArrayOfFloat2[(k - 1)], paramArrayOfFloat2[k]) > 0); k--)
/* 1134 */           swap(paramArrayOfFloat2, k, k - 1);
/* 1135 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1139 */     int j = (paramInt1 + paramInt2) / 2;
/* 1140 */     mergeSort1(paramArrayOfFloat2, paramArrayOfFloat1, paramInt1, j, paramFloatComparator);
/* 1141 */     mergeSort1(paramArrayOfFloat2, paramArrayOfFloat1, j, paramInt2, paramFloatComparator);
/*      */     
/*      */ 
/*      */ 
/* 1145 */     if (paramFloatComparator.compare(paramArrayOfFloat1[(j - 1)], paramArrayOfFloat1[j]) <= 0) {
/* 1146 */       System.arraycopy(paramArrayOfFloat1, paramInt1, paramArrayOfFloat2, paramInt1, i);
/* 1147 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1151 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1152 */       if ((n >= paramInt2) || ((m < j) && (paramFloatComparator.compare(paramArrayOfFloat1[m], paramArrayOfFloat1[n]) <= 0))) {
/* 1153 */         paramArrayOfFloat2[k] = paramArrayOfFloat1[(m++)];
/*      */       } else
/* 1155 */         paramArrayOfFloat2[k] = paramArrayOfFloat1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2) {
/* 1159 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1162 */     if (i < 7) {
/* 1163 */       for (j = paramInt1; j < paramInt2; j++)
/* 1164 */         for (k = j; (k > paramInt1) && (paramArrayOfInt2[(k - 1)] > paramArrayOfInt2[k]); k--)
/* 1165 */           swap(paramArrayOfInt2, k, k - 1);
/* 1166 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1170 */     int j = (paramInt1 + paramInt2) / 2;
/* 1171 */     mergeSort1(paramArrayOfInt2, paramArrayOfInt1, paramInt1, j);
/* 1172 */     mergeSort1(paramArrayOfInt2, paramArrayOfInt1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1176 */     if (paramArrayOfInt1[(j - 1)] <= paramArrayOfInt1[j]) {
/* 1177 */       System.arraycopy(paramArrayOfInt1, paramInt1, paramArrayOfInt2, paramInt1, i);
/* 1178 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1182 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1183 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfInt1[m] <= paramArrayOfInt1[n]))) {
/* 1184 */         paramArrayOfInt2[k] = paramArrayOfInt1[(m++)];
/*      */       } else
/* 1186 */         paramArrayOfInt2[k] = paramArrayOfInt1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, IntComparator paramIntComparator) {
/* 1190 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1193 */     if (i < 7) {
/* 1194 */       for (j = paramInt1; j < paramInt2; j++)
/* 1195 */         for (k = j; (k > paramInt1) && (paramIntComparator.compare(paramArrayOfInt2[(k - 1)], paramArrayOfInt2[k]) > 0); k--)
/* 1196 */           swap(paramArrayOfInt2, k, k - 1);
/* 1197 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1201 */     int j = (paramInt1 + paramInt2) / 2;
/* 1202 */     mergeSort1(paramArrayOfInt2, paramArrayOfInt1, paramInt1, j, paramIntComparator);
/* 1203 */     mergeSort1(paramArrayOfInt2, paramArrayOfInt1, j, paramInt2, paramIntComparator);
/*      */     
/*      */ 
/*      */ 
/* 1207 */     if (paramIntComparator.compare(paramArrayOfInt1[(j - 1)], paramArrayOfInt1[j]) <= 0) {
/* 1208 */       System.arraycopy(paramArrayOfInt1, paramInt1, paramArrayOfInt2, paramInt1, i);
/* 1209 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1213 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1214 */       if ((n >= paramInt2) || ((m < j) && (paramIntComparator.compare(paramArrayOfInt1[m], paramArrayOfInt1[n]) <= 0))) {
/* 1215 */         paramArrayOfInt2[k] = paramArrayOfInt1[(m++)];
/*      */       } else
/* 1217 */         paramArrayOfInt2[k] = paramArrayOfInt1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2) {
/* 1221 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1224 */     if (i < 7) {
/* 1225 */       for (j = paramInt1; j < paramInt2; j++)
/* 1226 */         for (k = j; (k > paramInt1) && (paramArrayOfLong2[(k - 1)] > paramArrayOfLong2[k]); k--)
/* 1227 */           swap(paramArrayOfLong2, k, k - 1);
/* 1228 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1232 */     int j = (paramInt1 + paramInt2) / 2;
/* 1233 */     mergeSort1(paramArrayOfLong2, paramArrayOfLong1, paramInt1, j);
/* 1234 */     mergeSort1(paramArrayOfLong2, paramArrayOfLong1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1238 */     if (paramArrayOfLong1[(j - 1)] <= paramArrayOfLong1[j]) {
/* 1239 */       System.arraycopy(paramArrayOfLong1, paramInt1, paramArrayOfLong2, paramInt1, i);
/* 1240 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1244 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1245 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfLong1[m] <= paramArrayOfLong1[n]))) {
/* 1246 */         paramArrayOfLong2[k] = paramArrayOfLong1[(m++)];
/*      */       } else
/* 1248 */         paramArrayOfLong2[k] = paramArrayOfLong1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, LongComparator paramLongComparator) {
/* 1252 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1255 */     if (i < 7) {
/* 1256 */       for (j = paramInt1; j < paramInt2; j++)
/* 1257 */         for (k = j; (k > paramInt1) && (paramLongComparator.compare(paramArrayOfLong2[(k - 1)], paramArrayOfLong2[k]) > 0); k--)
/* 1258 */           swap(paramArrayOfLong2, k, k - 1);
/* 1259 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1263 */     int j = (paramInt1 + paramInt2) / 2;
/* 1264 */     mergeSort1(paramArrayOfLong2, paramArrayOfLong1, paramInt1, j, paramLongComparator);
/* 1265 */     mergeSort1(paramArrayOfLong2, paramArrayOfLong1, j, paramInt2, paramLongComparator);
/*      */     
/*      */ 
/*      */ 
/* 1269 */     if (paramLongComparator.compare(paramArrayOfLong1[(j - 1)], paramArrayOfLong1[j]) <= 0) {
/* 1270 */       System.arraycopy(paramArrayOfLong1, paramInt1, paramArrayOfLong2, paramInt1, i);
/* 1271 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1275 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1276 */       if ((n >= paramInt2) || ((m < j) && (paramLongComparator.compare(paramArrayOfLong1[m], paramArrayOfLong1[n]) <= 0))) {
/* 1277 */         paramArrayOfLong2[k] = paramArrayOfLong1[(m++)];
/*      */       } else
/* 1279 */         paramArrayOfLong2[k] = paramArrayOfLong1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2) {
/* 1283 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1286 */     if (i < 7) {
/* 1287 */       for (j = paramInt1; j < paramInt2; j++)
/* 1288 */         for (k = j; (k > paramInt1) && (paramArrayOfShort2[(k - 1)] > paramArrayOfShort2[k]); k--)
/* 1289 */           swap(paramArrayOfShort2, k, k - 1);
/* 1290 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1294 */     int j = (paramInt1 + paramInt2) / 2;
/* 1295 */     mergeSort1(paramArrayOfShort2, paramArrayOfShort1, paramInt1, j);
/* 1296 */     mergeSort1(paramArrayOfShort2, paramArrayOfShort1, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1300 */     if (paramArrayOfShort1[(j - 1)] <= paramArrayOfShort1[j]) {
/* 1301 */       System.arraycopy(paramArrayOfShort1, paramInt1, paramArrayOfShort2, paramInt1, i);
/* 1302 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1306 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1307 */       if ((n >= paramInt2) || ((m < j) && (paramArrayOfShort1[m] <= paramArrayOfShort1[n]))) {
/* 1308 */         paramArrayOfShort2[k] = paramArrayOfShort1[(m++)];
/*      */       } else
/* 1310 */         paramArrayOfShort2[k] = paramArrayOfShort1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort1(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, ShortComparator paramShortComparator) {
/* 1314 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1317 */     if (i < 7) {
/* 1318 */       for (j = paramInt1; j < paramInt2; j++)
/* 1319 */         for (k = j; (k > paramInt1) && (paramShortComparator.compare(paramArrayOfShort2[(k - 1)], paramArrayOfShort2[k]) > 0); k--)
/* 1320 */           swap(paramArrayOfShort2, k, k - 1);
/* 1321 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1325 */     int j = (paramInt1 + paramInt2) / 2;
/* 1326 */     mergeSort1(paramArrayOfShort2, paramArrayOfShort1, paramInt1, j, paramShortComparator);
/* 1327 */     mergeSort1(paramArrayOfShort2, paramArrayOfShort1, j, paramInt2, paramShortComparator);
/*      */     
/*      */ 
/*      */ 
/* 1331 */     if (paramShortComparator.compare(paramArrayOfShort1[(j - 1)], paramArrayOfShort1[j]) <= 0) {
/* 1332 */       System.arraycopy(paramArrayOfShort1, paramInt1, paramArrayOfShort2, paramInt1, i);
/* 1333 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1337 */     int k = paramInt1;int m = paramInt1; for (int n = j; k < paramInt2; k++)
/* 1338 */       if ((n >= paramInt2) || ((m < j) && (paramShortComparator.compare(paramArrayOfShort1[m], paramArrayOfShort1[n]) <= 0))) {
/* 1339 */         paramArrayOfShort2[k] = paramArrayOfShort1[(m++)];
/*      */       } else
/* 1341 */         paramArrayOfShort2[k] = paramArrayOfShort1[(n++)];
/*      */   }
/*      */   
/*      */   private static void mergeSort2(double[] paramArrayOfDouble, int paramInt1, int paramInt2) {
/* 1345 */     rangeCheck(paramArrayOfDouble.length, paramInt1, paramInt2);
/* 1346 */     long l = Double.doubleToLongBits(-0.0D);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1356 */     int i = 0;
/* 1357 */     int j = paramInt1;int k = paramInt2;
/* 1358 */     while (j < k) {
/* 1359 */       if (paramArrayOfDouble[j] != paramArrayOfDouble[j]) {
/* 1360 */         paramArrayOfDouble[j] = paramArrayOfDouble[(--k)];
/* 1361 */         paramArrayOfDouble[k] = NaN.0D;
/*      */       } else {
/* 1363 */         if ((paramArrayOfDouble[j] == 0.0D) && (Double.doubleToLongBits(paramArrayOfDouble[j]) == l)) {
/* 1364 */           paramArrayOfDouble[j] = 0.0D;
/* 1365 */           i++;
/*      */         }
/* 1367 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1372 */     double[] arrayOfDouble = (double[])paramArrayOfDouble.clone();
/* 1373 */     mergeSort1(arrayOfDouble, paramArrayOfDouble, paramInt1, k);
/*      */     
/*      */ 
/* 1376 */     if (i != 0) {
/* 1377 */       int m = new DoubleArrayList(paramArrayOfDouble).binarySearchFromTo(0.0D, paramInt1, k - 1);
/*      */       do {
/* 1379 */         m--;
/* 1380 */       } while ((m >= 0) && (paramArrayOfDouble[m] == 0.0D));
/*      */       
/*      */ 
/* 1383 */       for (int n = 0; n < i; n++)
/* 1384 */         paramArrayOfDouble[(++m)] = -0.0D;
/*      */     }
/*      */   }
/*      */   
/* 1388 */   private static void mergeSort2(float[] paramArrayOfFloat, int paramInt1, int paramInt2) { rangeCheck(paramArrayOfFloat.length, paramInt1, paramInt2);
/* 1389 */     int i = Float.floatToIntBits(-0.0F);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1399 */     int j = 0;
/* 1400 */     int k = paramInt1;int m = paramInt2;
/* 1401 */     while (k < m) {
/* 1402 */       if (paramArrayOfFloat[k] != paramArrayOfFloat[k]) {
/* 1403 */         paramArrayOfFloat[k] = paramArrayOfFloat[(--m)];
/* 1404 */         paramArrayOfFloat[m] = NaN.0F;
/*      */       } else {
/* 1406 */         if ((paramArrayOfFloat[k] == 0.0F) && (Float.floatToIntBits(paramArrayOfFloat[k]) == i)) {
/* 1407 */           paramArrayOfFloat[k] = 0.0F;
/* 1408 */           j++;
/*      */         }
/* 1410 */         k++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1415 */     float[] arrayOfFloat = (float[])paramArrayOfFloat.clone();
/* 1416 */     mergeSort1(arrayOfFloat, paramArrayOfFloat, paramInt1, m);
/*      */     
/*      */ 
/* 1419 */     if (j != 0) {
/* 1420 */       int n = new FloatArrayList(paramArrayOfFloat).binarySearchFromTo(0.0F, paramInt1, m - 1);
/*      */       do {
/* 1422 */         n--;
/* 1423 */       } while ((n >= 0) && (paramArrayOfFloat[n] == 0.0F));
/*      */       
/*      */ 
/* 1426 */       for (int i1 = 0; i1 < j; i1++) {
/* 1427 */         paramArrayOfFloat[(++n)] = -0.0F;
/*      */       }
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
/*      */   public static void mergeSortInPlace(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */   {
/* 1451 */     rangeCheck(paramArrayOfInt.length, paramInt1, paramInt2);
/* 1452 */     int i = paramInt2 - paramInt1;
/*      */     
/*      */ 
/* 1455 */     if (i < 7) {
/* 1456 */       for (j = paramInt1; j < paramInt2; j++) {
/* 1457 */         for (int k = j; (k > paramInt1) && (paramArrayOfInt[(k - 1)] > paramArrayOfInt[k]); k--) {
/* 1458 */           int m = paramArrayOfInt[k];paramArrayOfInt[k] = paramArrayOfInt[(k - 1)];paramArrayOfInt[(k - 1)] = m;
/*      */         }
/*      */       }
/* 1461 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1465 */     int j = (paramInt1 + paramInt2) / 2;
/* 1466 */     mergeSortInPlace(paramArrayOfInt, paramInt1, j);
/* 1467 */     mergeSortInPlace(paramArrayOfInt, j, paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1471 */     if (paramArrayOfInt[(j - 1)] <= paramArrayOfInt[j]) { return;
/*      */     }
/*      */     
/*      */ 
/* 1475 */     jal.INT.Sorting.inplace_merge(paramArrayOfInt, paramInt1, j, paramInt2);
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
/*      */   public static void quickSort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ByteComparator paramByteComparator)
/*      */   {
/* 1505 */     rangeCheck(paramArrayOfByte.length, paramInt1, paramInt2);
/* 1506 */     quickSort1(paramArrayOfByte, paramInt1, paramInt2 - paramInt1, paramByteComparator);
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
/*      */   public static void quickSort(char[] paramArrayOfChar, int paramInt1, int paramInt2, CharComparator paramCharComparator)
/*      */   {
/* 1536 */     rangeCheck(paramArrayOfChar.length, paramInt1, paramInt2);
/* 1537 */     quickSort1(paramArrayOfChar, paramInt1, paramInt2 - paramInt1, paramCharComparator);
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
/*      */   public static void quickSort(double[] paramArrayOfDouble, int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator)
/*      */   {
/* 1567 */     rangeCheck(paramArrayOfDouble.length, paramInt1, paramInt2);
/* 1568 */     quickSort1(paramArrayOfDouble, paramInt1, paramInt2 - paramInt1, paramDoubleComparator);
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
/*      */   public static void quickSort(float[] paramArrayOfFloat, int paramInt1, int paramInt2, FloatComparator paramFloatComparator)
/*      */   {
/* 1598 */     rangeCheck(paramArrayOfFloat.length, paramInt1, paramInt2);
/* 1599 */     quickSort1(paramArrayOfFloat, paramInt1, paramInt2 - paramInt1, paramFloatComparator);
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
/*      */   public static void quickSort(int[] paramArrayOfInt, int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*      */   {
/* 1629 */     rangeCheck(paramArrayOfInt.length, paramInt1, paramInt2);
/* 1630 */     quickSort1(paramArrayOfInt, paramInt1, paramInt2 - paramInt1, paramIntComparator);
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
/*      */   public static void quickSort(long[] paramArrayOfLong, int paramInt1, int paramInt2, LongComparator paramLongComparator)
/*      */   {
/* 1660 */     rangeCheck(paramArrayOfLong.length, paramInt1, paramInt2);
/* 1661 */     quickSort1(paramArrayOfLong, paramInt1, paramInt2 - paramInt1, paramLongComparator);
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
/*      */   public static void quickSort(Object[] paramArrayOfObject)
/*      */   {
/* 1681 */     quickSort1(paramArrayOfObject, 0, paramArrayOfObject.length);
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
/*      */   public static void quickSort(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*      */   {
/* 1701 */     rangeCheck(paramArrayOfObject.length, paramInt1, paramInt2);
/* 1702 */     quickSort1(paramArrayOfObject, paramInt1, paramInt2 - paramInt1);
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
/*      */   public static void quickSort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator paramComparator)
/*      */   {
/* 1732 */     rangeCheck(paramArrayOfObject.length, paramInt1, paramInt2);
/* 1733 */     quickSort1(paramArrayOfObject, paramInt1, paramInt2 - paramInt1, paramComparator);
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
/*      */   public static void quickSort(Object[] paramArrayOfObject, Comparator paramComparator)
/*      */   {
/* 1760 */     quickSort1(paramArrayOfObject, 0, paramArrayOfObject.length, paramComparator);
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
/*      */   public static void quickSort(short[] paramArrayOfShort, int paramInt1, int paramInt2, ShortComparator paramShortComparator)
/*      */   {
/* 1792 */     rangeCheck(paramArrayOfShort.length, paramInt1, paramInt2);
/* 1793 */     quickSort1(paramArrayOfShort, paramInt1, paramInt2 - paramInt1, paramShortComparator);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static void quickSort1(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ByteComparator paramByteComparator)
/*      */   {
/* 1800 */     if (paramInt2 < 7) {
/* 1801 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 1802 */         for (j = i; (j > paramInt1) && (paramByteComparator.compare(paramArrayOfByte[(j - 1)], paramArrayOfByte[j]) > 0); j--)
/* 1803 */           swap(paramArrayOfByte, j, j - 1);
/* 1804 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1808 */     int i = paramInt1 + paramInt2 / 2;
/* 1809 */     if (paramInt2 > 7) {
/* 1810 */       j = paramInt1;
/* 1811 */       k = paramInt1 + paramInt2 - 1;
/* 1812 */       if (paramInt2 > 40) {
/* 1813 */         m = paramInt2 / 8;
/* 1814 */         j = med3(paramArrayOfByte, j, j + m, j + 2 * m, paramByteComparator);
/* 1815 */         i = med3(paramArrayOfByte, i - m, i, i + m, paramByteComparator);
/* 1816 */         k = med3(paramArrayOfByte, k - 2 * m, k - m, k, paramByteComparator);
/*      */       }
/* 1818 */       i = med3(paramArrayOfByte, j, i, k, paramByteComparator);
/*      */     }
/* 1820 */     int j = paramArrayOfByte[i];
/*      */     
/*      */ 
/* 1823 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 1827 */     if (i2 == 0)
/* 1828 */       swap(paramArrayOfByte, k++, m);
/* 1829 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 1826 */       if (m <= n) { if ((i2 = paramByteComparator.compare(paramArrayOfByte[m], j)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1831 */       while ((n >= m) && ((i2 = paramByteComparator.compare(paramArrayOfByte[n], j)) >= 0)) {
/* 1832 */         if (i2 == 0)
/* 1833 */           swap(paramArrayOfByte, n, i1--);
/* 1834 */         n--;
/*      */       }
/* 1836 */       if (m > n)
/*      */         break label330;
/* 1838 */       swap(paramArrayOfByte, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 1842 */     int i3 = paramInt1 + paramInt2;
/* 1843 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfByte, paramInt1, m - i2, i2);
/* 1844 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfByte, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 1847 */     if ((i2 = m - k) > 1)
/* 1848 */       quickSort1(paramArrayOfByte, paramInt1, i2, paramByteComparator);
/* 1849 */     if ((i2 = i1 - n) > 1) {
/* 1850 */       quickSort1(paramArrayOfByte, i3 - i2, i2, paramByteComparator);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void quickSort1(char[] paramArrayOfChar, int paramInt1, int paramInt2, CharComparator paramCharComparator)
/*      */   {
/* 1857 */     if (paramInt2 < 7) {
/* 1858 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 1859 */         for (j = i; (j > paramInt1) && (paramCharComparator.compare(paramArrayOfChar[(j - 1)], paramArrayOfChar[j]) > 0); j--)
/* 1860 */           swap(paramArrayOfChar, j, j - 1);
/* 1861 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1865 */     int i = paramInt1 + paramInt2 / 2;
/* 1866 */     if (paramInt2 > 7) {
/* 1867 */       j = paramInt1;
/* 1868 */       k = paramInt1 + paramInt2 - 1;
/* 1869 */       if (paramInt2 > 40) {
/* 1870 */         m = paramInt2 / 8;
/* 1871 */         j = med3(paramArrayOfChar, j, j + m, j + 2 * m, paramCharComparator);
/* 1872 */         i = med3(paramArrayOfChar, i - m, i, i + m, paramCharComparator);
/* 1873 */         k = med3(paramArrayOfChar, k - 2 * m, k - m, k, paramCharComparator);
/*      */       }
/* 1875 */       i = med3(paramArrayOfChar, j, i, k, paramCharComparator);
/*      */     }
/* 1877 */     int j = paramArrayOfChar[i];
/*      */     
/*      */ 
/* 1880 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 1884 */     if (i2 == 0)
/* 1885 */       swap(paramArrayOfChar, k++, m);
/* 1886 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 1883 */       if (m <= n) { if ((i2 = paramCharComparator.compare(paramArrayOfChar[m], j)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1888 */       while ((n >= m) && ((i2 = paramCharComparator.compare(paramArrayOfChar[n], j)) >= 0)) {
/* 1889 */         if (i2 == 0)
/* 1890 */           swap(paramArrayOfChar, n, i1--);
/* 1891 */         n--;
/*      */       }
/* 1893 */       if (m > n)
/*      */         break label330;
/* 1895 */       swap(paramArrayOfChar, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 1899 */     int i3 = paramInt1 + paramInt2;
/* 1900 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfChar, paramInt1, m - i2, i2);
/* 1901 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfChar, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 1904 */     if ((i2 = m - k) > 1)
/* 1905 */       quickSort1(paramArrayOfChar, paramInt1, i2, paramCharComparator);
/* 1906 */     if ((i2 = i1 - n) > 1) {
/* 1907 */       quickSort1(paramArrayOfChar, i3 - i2, i2, paramCharComparator);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void quickSort1(double[] paramArrayOfDouble, int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator)
/*      */   {
/*      */     int j;
/* 1914 */     if (paramInt2 < 7) {
/* 1915 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 1916 */         for (j = i; (j > paramInt1) && (paramDoubleComparator.compare(paramArrayOfDouble[(j - 1)], paramArrayOfDouble[j]) > 0); j--)
/* 1917 */           swap(paramArrayOfDouble, j, j - 1);
/* 1918 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1922 */     int i = paramInt1 + paramInt2 / 2;
/* 1923 */     if (paramInt2 > 7) {
/* 1924 */       j = paramInt1;
/* 1925 */       int k = paramInt1 + paramInt2 - 1;
/* 1926 */       if (paramInt2 > 40) {
/* 1927 */         m = paramInt2 / 8;
/* 1928 */         j = med3(paramArrayOfDouble, j, j + m, j + 2 * m, paramDoubleComparator);
/* 1929 */         i = med3(paramArrayOfDouble, i - m, i, i + m, paramDoubleComparator);
/* 1930 */         k = med3(paramArrayOfDouble, k - 2 * m, k - m, k, paramDoubleComparator);
/*      */       }
/* 1932 */       i = med3(paramArrayOfDouble, j, i, k, paramDoubleComparator);
/*      */     }
/* 1934 */     double d = paramArrayOfDouble[i];
/*      */     
/*      */ 
/* 1937 */     int m = paramInt1;int n = m;int i1 = paramInt1 + paramInt2 - 1;int i2 = i1;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 1941 */     if (i3 == 0)
/* 1942 */       swap(paramArrayOfDouble, m++, n);
/* 1943 */     n++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 1940 */       if (n <= i1) { if ((i3 = paramDoubleComparator.compare(paramArrayOfDouble[n], d)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1945 */       while ((i1 >= n) && ((i3 = paramDoubleComparator.compare(paramArrayOfDouble[i1], d)) >= 0)) {
/* 1946 */         if (i3 == 0)
/* 1947 */           swap(paramArrayOfDouble, i1, i2--);
/* 1948 */         i1--;
/*      */       }
/* 1950 */       if (n > i1)
/*      */         break label330;
/* 1952 */       swap(paramArrayOfDouble, n++, i1--);
/*      */     }
/*      */     
/*      */     label330:
/* 1956 */     int i4 = paramInt1 + paramInt2;
/* 1957 */     int i3 = Math.min(m - paramInt1, n - m);vecswap(paramArrayOfDouble, paramInt1, n - i3, i3);
/* 1958 */     i3 = Math.min(i2 - i1, i4 - i2 - 1);vecswap(paramArrayOfDouble, n, i4 - i3, i3);
/*      */     
/*      */ 
/* 1961 */     if ((i3 = n - m) > 1)
/* 1962 */       quickSort1(paramArrayOfDouble, paramInt1, i3, paramDoubleComparator);
/* 1963 */     if ((i3 = i2 - i1) > 1) {
/* 1964 */       quickSort1(paramArrayOfDouble, i4 - i3, i3, paramDoubleComparator);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void quickSort1(float[] paramArrayOfFloat, int paramInt1, int paramInt2, FloatComparator paramFloatComparator)
/*      */   {
/*      */     int j;
/* 1971 */     if (paramInt2 < 7) {
/* 1972 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 1973 */         for (j = i; (j > paramInt1) && (paramFloatComparator.compare(paramArrayOfFloat[(j - 1)], paramArrayOfFloat[j]) > 0); j--)
/* 1974 */           swap(paramArrayOfFloat, j, j - 1);
/* 1975 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1979 */     int i = paramInt1 + paramInt2 / 2;
/* 1980 */     if (paramInt2 > 7) {
/* 1981 */       j = paramInt1;
/* 1982 */       k = paramInt1 + paramInt2 - 1;
/* 1983 */       if (paramInt2 > 40) {
/* 1984 */         m = paramInt2 / 8;
/* 1985 */         j = med3(paramArrayOfFloat, j, j + m, j + 2 * m, paramFloatComparator);
/* 1986 */         i = med3(paramArrayOfFloat, i - m, i, i + m, paramFloatComparator);
/* 1987 */         k = med3(paramArrayOfFloat, k - 2 * m, k - m, k, paramFloatComparator);
/*      */       }
/* 1989 */       i = med3(paramArrayOfFloat, j, i, k, paramFloatComparator);
/*      */     }
/* 1991 */     float f = paramArrayOfFloat[i];
/*      */     
/*      */ 
/* 1994 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 1998 */     if (i2 == 0)
/* 1999 */       swap(paramArrayOfFloat, k++, m);
/* 2000 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 1997 */       if (m <= n) { if ((i2 = paramFloatComparator.compare(paramArrayOfFloat[m], f)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2002 */       while ((n >= m) && ((i2 = paramFloatComparator.compare(paramArrayOfFloat[n], f)) >= 0)) {
/* 2003 */         if (i2 == 0)
/* 2004 */           swap(paramArrayOfFloat, n, i1--);
/* 2005 */         n--;
/*      */       }
/* 2007 */       if (m > n)
/*      */         break label330;
/* 2009 */       swap(paramArrayOfFloat, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 2013 */     int i3 = paramInt1 + paramInt2;
/* 2014 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfFloat, paramInt1, m - i2, i2);
/* 2015 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfFloat, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 2018 */     if ((i2 = m - k) > 1)
/* 2019 */       quickSort1(paramArrayOfFloat, paramInt1, i2, paramFloatComparator);
/* 2020 */     if ((i2 = i1 - n) > 1) {
/* 2021 */       quickSort1(paramArrayOfFloat, i3 - i2, i2, paramFloatComparator);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void quickSort1(int[] paramArrayOfInt, int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*      */   {
/* 2028 */     if (paramInt2 < 7) {
/* 2029 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 2030 */         for (j = i; (j > paramInt1) && (paramIntComparator.compare(paramArrayOfInt[(j - 1)], paramArrayOfInt[j]) > 0); j--)
/* 2031 */           swap(paramArrayOfInt, j, j - 1);
/* 2032 */       return;
/*      */     }
/*      */     
/*      */ 
/* 2036 */     int i = paramInt1 + paramInt2 / 2;
/* 2037 */     if (paramInt2 > 7) {
/* 2038 */       j = paramInt1;
/* 2039 */       k = paramInt1 + paramInt2 - 1;
/* 2040 */       if (paramInt2 > 40) {
/* 2041 */         m = paramInt2 / 8;
/* 2042 */         j = med3(paramArrayOfInt, j, j + m, j + 2 * m, paramIntComparator);
/* 2043 */         i = med3(paramArrayOfInt, i - m, i, i + m, paramIntComparator);
/* 2044 */         k = med3(paramArrayOfInt, k - 2 * m, k - m, k, paramIntComparator);
/*      */       }
/* 2046 */       i = med3(paramArrayOfInt, j, i, k, paramIntComparator);
/*      */     }
/* 2048 */     int j = paramArrayOfInt[i];
/*      */     
/*      */ 
/* 2051 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 2055 */     if (i2 == 0)
/* 2056 */       swap(paramArrayOfInt, k++, m);
/* 2057 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 2054 */       if (m <= n) { if ((i2 = paramIntComparator.compare(paramArrayOfInt[m], j)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2059 */       while ((n >= m) && ((i2 = paramIntComparator.compare(paramArrayOfInt[n], j)) >= 0)) {
/* 2060 */         if (i2 == 0)
/* 2061 */           swap(paramArrayOfInt, n, i1--);
/* 2062 */         n--;
/*      */       }
/* 2064 */       if (m > n)
/*      */         break label330;
/* 2066 */       swap(paramArrayOfInt, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 2070 */     int i3 = paramInt1 + paramInt2;
/* 2071 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfInt, paramInt1, m - i2, i2);
/* 2072 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfInt, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 2075 */     if ((i2 = m - k) > 1)
/* 2076 */       quickSort1(paramArrayOfInt, paramInt1, i2, paramIntComparator);
/* 2077 */     if ((i2 = i1 - n) > 1) {
/* 2078 */       quickSort1(paramArrayOfInt, i3 - i2, i2, paramIntComparator);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void quickSort1(long[] paramArrayOfLong, int paramInt1, int paramInt2, LongComparator paramLongComparator)
/*      */   {
/*      */     int j;
/* 2085 */     if (paramInt2 < 7) {
/* 2086 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 2087 */         for (j = i; (j > paramInt1) && (paramLongComparator.compare(paramArrayOfLong[(j - 1)], paramArrayOfLong[j]) > 0); j--)
/* 2088 */           swap(paramArrayOfLong, j, j - 1);
/* 2089 */       return;
/*      */     }
/*      */     
/*      */ 
/* 2093 */     int i = paramInt1 + paramInt2 / 2;
/* 2094 */     if (paramInt2 > 7) {
/* 2095 */       j = paramInt1;
/* 2096 */       int k = paramInt1 + paramInt2 - 1;
/* 2097 */       if (paramInt2 > 40) {
/* 2098 */         m = paramInt2 / 8;
/* 2099 */         j = med3(paramArrayOfLong, j, j + m, j + 2 * m, paramLongComparator);
/* 2100 */         i = med3(paramArrayOfLong, i - m, i, i + m, paramLongComparator);
/* 2101 */         k = med3(paramArrayOfLong, k - 2 * m, k - m, k, paramLongComparator);
/*      */       }
/* 2103 */       i = med3(paramArrayOfLong, j, i, k, paramLongComparator);
/*      */     }
/* 2105 */     long l = paramArrayOfLong[i];
/*      */     
/*      */ 
/* 2108 */     int m = paramInt1;int n = m;int i1 = paramInt1 + paramInt2 - 1;int i2 = i1;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 2112 */     if (i3 == 0)
/* 2113 */       swap(paramArrayOfLong, m++, n);
/* 2114 */     n++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 2111 */       if (n <= i1) { if ((i3 = paramLongComparator.compare(paramArrayOfLong[n], l)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2116 */       while ((i1 >= n) && ((i3 = paramLongComparator.compare(paramArrayOfLong[i1], l)) >= 0)) {
/* 2117 */         if (i3 == 0)
/* 2118 */           swap(paramArrayOfLong, i1, i2--);
/* 2119 */         i1--;
/*      */       }
/* 2121 */       if (n > i1)
/*      */         break label330;
/* 2123 */       swap(paramArrayOfLong, n++, i1--);
/*      */     }
/*      */     
/*      */     label330:
/* 2127 */     int i4 = paramInt1 + paramInt2;
/* 2128 */     int i3 = Math.min(m - paramInt1, n - m);vecswap(paramArrayOfLong, paramInt1, n - i3, i3);
/* 2129 */     i3 = Math.min(i2 - i1, i4 - i2 - 1);vecswap(paramArrayOfLong, n, i4 - i3, i3);
/*      */     
/*      */ 
/* 2132 */     if ((i3 = n - m) > 1)
/* 2133 */       quickSort1(paramArrayOfLong, paramInt1, i3, paramLongComparator);
/* 2134 */     if ((i3 = i2 - i1) > 1) {
/* 2135 */       quickSort1(paramArrayOfLong, i4 - i3, i3, paramLongComparator);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void quickSort1(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*      */   {
/*      */     int j;
/* 2142 */     if (paramInt2 < 7) {
/* 2143 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 2144 */         for (j = i; (j > paramInt1) && (((Comparable)paramArrayOfObject[(j - 1)]).compareTo((Comparable)paramArrayOfObject[j]) > 0); j--)
/* 2145 */           swap(paramArrayOfObject, j, j - 1);
/* 2146 */       return;
/*      */     }
/*      */     
/*      */ 
/* 2150 */     int i = paramInt1 + paramInt2 / 2;
/* 2151 */     if (paramInt2 > 7) {
/* 2152 */       j = paramInt1;
/* 2153 */       k = paramInt1 + paramInt2 - 1;
/* 2154 */       if (paramInt2 > 40) {
/* 2155 */         m = paramInt2 / 8;
/* 2156 */         j = med3(paramArrayOfObject, j, j + m, j + 2 * m);
/* 2157 */         i = med3(paramArrayOfObject, i - m, i, i + m);
/* 2158 */         k = med3(paramArrayOfObject, k - 2 * m, k - m, k);
/*      */       }
/* 2160 */       i = med3(paramArrayOfObject, j, i, k);
/*      */     }
/* 2162 */     Comparable localComparable = (Comparable)paramArrayOfObject[i];
/*      */     
/*      */ 
/* 2165 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label220;
/*      */     break label220;
/* 2169 */     if (i2 == 0)
/* 2170 */       swap(paramArrayOfObject, k++, m);
/* 2171 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label220:
/* 2168 */       if (m <= n) { if ((i2 = ((Comparable)paramArrayOfObject[m]).compareTo(localComparable)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2173 */       while ((n >= m) && ((i2 = ((Comparable)paramArrayOfObject[n]).compareTo(localComparable)) >= 0)) {
/* 2174 */         if (i2 == 0)
/* 2175 */           swap(paramArrayOfObject, n, i1--);
/* 2176 */         n--;
/*      */       }
/* 2178 */       if (m > n)
/*      */         break label327;
/* 2180 */       swap(paramArrayOfObject, m++, n--);
/*      */     }
/*      */     
/*      */     label327:
/* 2184 */     int i3 = paramInt1 + paramInt2;
/* 2185 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfObject, paramInt1, m - i2, i2);
/* 2186 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfObject, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 2189 */     if ((i2 = m - k) > 1)
/* 2190 */       quickSort1(paramArrayOfObject, paramInt1, i2);
/* 2191 */     if ((i2 = i1 - n) > 1) {
/* 2192 */       quickSort1(paramArrayOfObject, i3 - i2, i2);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void quickSort1(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator paramComparator)
/*      */   {
/*      */     int j;
/* 2199 */     if (paramInt2 < 7) {
/* 2200 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 2201 */         for (j = i; (j > paramInt1) && (paramComparator.compare(paramArrayOfObject[(j - 1)], paramArrayOfObject[j]) > 0); j--)
/* 2202 */           swap(paramArrayOfObject, j, j - 1);
/* 2203 */       return;
/*      */     }
/*      */     
/*      */ 
/* 2207 */     int i = paramInt1 + paramInt2 / 2;
/* 2208 */     if (paramInt2 > 7) {
/* 2209 */       j = paramInt1;
/* 2210 */       k = paramInt1 + paramInt2 - 1;
/* 2211 */       if (paramInt2 > 40) {
/* 2212 */         m = paramInt2 / 8;
/* 2213 */         j = med3(paramArrayOfObject, j, j + m, j + 2 * m, paramComparator);
/* 2214 */         i = med3(paramArrayOfObject, i - m, i, i + m, paramComparator);
/* 2215 */         k = med3(paramArrayOfObject, k - 2 * m, k - m, k, paramComparator);
/*      */       }
/* 2217 */       i = med3(paramArrayOfObject, j, i, k, paramComparator);
/*      */     }
/* 2219 */     Object localObject = paramArrayOfObject[i];
/*      */     
/*      */ 
/* 2222 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 2226 */     if (i2 == 0)
/* 2227 */       swap(paramArrayOfObject, k++, m);
/* 2228 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 2225 */       if (m <= n) { if ((i2 = paramComparator.compare(paramArrayOfObject[m], localObject)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2230 */       while ((n >= m) && ((i2 = paramComparator.compare(paramArrayOfObject[n], localObject)) >= 0)) {
/* 2231 */         if (i2 == 0)
/* 2232 */           swap(paramArrayOfObject, n, i1--);
/* 2233 */         n--;
/*      */       }
/* 2235 */       if (m > n)
/*      */         break label330;
/* 2237 */       swap(paramArrayOfObject, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 2241 */     int i3 = paramInt1 + paramInt2;
/* 2242 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfObject, paramInt1, m - i2, i2);
/* 2243 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfObject, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 2246 */     if ((i2 = m - k) > 1)
/* 2247 */       quickSort1(paramArrayOfObject, paramInt1, i2, paramComparator);
/* 2248 */     if ((i2 = i1 - n) > 1) {
/* 2249 */       quickSort1(paramArrayOfObject, i3 - i2, i2, paramComparator);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void quickSort1(short[] paramArrayOfShort, int paramInt1, int paramInt2, ShortComparator paramShortComparator)
/*      */   {
/* 2256 */     if (paramInt2 < 7) {
/* 2257 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++)
/* 2258 */         for (j = i; (j > paramInt1) && (paramShortComparator.compare(paramArrayOfShort[(j - 1)], paramArrayOfShort[j]) > 0); j--)
/* 2259 */           swap(paramArrayOfShort, j, j - 1);
/* 2260 */       return;
/*      */     }
/*      */     
/*      */ 
/* 2264 */     int i = paramInt1 + paramInt2 / 2;
/* 2265 */     if (paramInt2 > 7) {
/* 2266 */       j = paramInt1;
/* 2267 */       k = paramInt1 + paramInt2 - 1;
/* 2268 */       if (paramInt2 > 40) {
/* 2269 */         m = paramInt2 / 8;
/* 2270 */         j = med3(paramArrayOfShort, j, j + m, j + 2 * m, paramShortComparator);
/* 2271 */         i = med3(paramArrayOfShort, i - m, i, i + m, paramShortComparator);
/* 2272 */         k = med3(paramArrayOfShort, k - 2 * m, k - m, k, paramShortComparator);
/*      */       }
/* 2274 */       i = med3(paramArrayOfShort, j, i, k, paramShortComparator);
/*      */     }
/* 2276 */     int j = paramArrayOfShort[i];
/*      */     
/*      */ 
/* 2279 */     int k = paramInt1;int m = k;int n = paramInt1 + paramInt2 - 1;int i1 = n;
/*      */     
/*      */     break label227;
/*      */     break label227;
/* 2283 */     if (i2 == 0)
/* 2284 */       swap(paramArrayOfShort, k++, m);
/* 2285 */     m++;
/*      */     for (;;)
/*      */     {
/*      */       label227:
/* 2282 */       if (m <= n) { if ((i2 = paramShortComparator.compare(paramArrayOfShort[m], j)) <= 0) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 2287 */       while ((n >= m) && ((i2 = paramShortComparator.compare(paramArrayOfShort[n], j)) >= 0)) {
/* 2288 */         if (i2 == 0)
/* 2289 */           swap(paramArrayOfShort, n, i1--);
/* 2290 */         n--;
/*      */       }
/* 2292 */       if (m > n)
/*      */         break label330;
/* 2294 */       swap(paramArrayOfShort, m++, n--);
/*      */     }
/*      */     
/*      */     label330:
/* 2298 */     int i3 = paramInt1 + paramInt2;
/* 2299 */     int i2 = Math.min(k - paramInt1, m - k);vecswap(paramArrayOfShort, paramInt1, m - i2, i2);
/* 2300 */     i2 = Math.min(i1 - n, i3 - i1 - 1);vecswap(paramArrayOfShort, m, i3 - i2, i2);
/*      */     
/*      */ 
/* 2303 */     if ((i2 = m - k) > 1)
/* 2304 */       quickSort1(paramArrayOfShort, paramInt1, i2, paramShortComparator);
/* 2305 */     if ((i2 = i1 - n) > 1) {
/* 2306 */       quickSort1(paramArrayOfShort, i3 - i2, i2, paramShortComparator);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void rangeCheck(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2313 */     if (paramInt2 > paramInt3) {
/* 2314 */       throw new IllegalArgumentException("fromIndex(" + paramInt2 + ") > toIndex(" + paramInt3 + ")");
/*      */     }
/* 2316 */     if (paramInt2 < 0)
/* 2317 */       throw new ArrayIndexOutOfBoundsException(paramInt2);
/* 2318 */     if (paramInt3 > paramInt1) {
/* 2319 */       throw new ArrayIndexOutOfBoundsException(paramInt3);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void swap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 2325 */     int i = paramArrayOfByte[paramInt1];
/* 2326 */     paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/* 2327 */     paramArrayOfByte[paramInt2] = i;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */   {
/* 2333 */     int i = paramArrayOfChar[paramInt1];
/* 2334 */     paramArrayOfChar[paramInt1] = paramArrayOfChar[paramInt2];
/* 2335 */     paramArrayOfChar[paramInt2] = i;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(double[] paramArrayOfDouble, int paramInt1, int paramInt2)
/*      */   {
/* 2341 */     double d = paramArrayOfDouble[paramInt1];
/* 2342 */     paramArrayOfDouble[paramInt1] = paramArrayOfDouble[paramInt2];
/* 2343 */     paramArrayOfDouble[paramInt2] = d;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
/*      */   {
/* 2349 */     float f = paramArrayOfFloat[paramInt1];
/* 2350 */     paramArrayOfFloat[paramInt1] = paramArrayOfFloat[paramInt2];
/* 2351 */     paramArrayOfFloat[paramInt2] = f;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */   {
/* 2357 */     int i = paramArrayOfInt[paramInt1];
/* 2358 */     paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
/* 2359 */     paramArrayOfInt[paramInt2] = i;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*      */   {
/* 2365 */     long l = paramArrayOfLong[paramInt1];
/* 2366 */     paramArrayOfLong[paramInt1] = paramArrayOfLong[paramInt2];
/* 2367 */     paramArrayOfLong[paramInt2] = l;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*      */   {
/* 2373 */     Object localObject = paramArrayOfObject[paramInt1];
/* 2374 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/* 2375 */     paramArrayOfObject[paramInt2] = localObject;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void swap(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*      */   {
/* 2381 */     int i = paramArrayOfShort[paramInt1];
/* 2382 */     paramArrayOfShort[paramInt1] = paramArrayOfShort[paramInt2];
/* 2383 */     paramArrayOfShort[paramInt2] = i;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void vecswap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2389 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2390 */       swap(paramArrayOfByte, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2396 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2397 */       swap(paramArrayOfChar, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2403 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2404 */       swap(paramArrayOfDouble, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2410 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2411 */       swap(paramArrayOfFloat, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2417 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2418 */       swap(paramArrayOfInt, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(long[] paramArrayOfLong, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2424 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2425 */       swap(paramArrayOfLong, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2431 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2432 */       swap(paramArrayOfObject, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void vecswap(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2438 */     for (int i = 0; i < paramInt3; paramInt2++) {
/* 2439 */       swap(paramArrayOfShort, paramInt1, paramInt2);i++;paramInt1++;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */