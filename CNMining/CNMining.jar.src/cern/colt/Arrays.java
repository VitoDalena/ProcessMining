/*     */ package cern.colt;
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
/*     */ 
/*     */ public class Arrays
/*     */ {
/*     */   public static byte[] ensureCapacity(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/*  36 */     int i = paramArrayOfByte.length;
/*     */     byte[] arrayOfByte;
/*  38 */     if (paramInt > i) {
/*  39 */       int j = i * 3 / 2 + 1;
/*  40 */       if (j < paramInt) {
/*  41 */         j = paramInt;
/*     */       }
/*     */       
/*  44 */       arrayOfByte = new byte[j];
/*  45 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
/*     */     }
/*     */     else {
/*  48 */       arrayOfByte = paramArrayOfByte;
/*     */     }
/*  50 */     return arrayOfByte;
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
/*     */   public static char[] ensureCapacity(char[] paramArrayOfChar, int paramInt)
/*     */   {
/*  63 */     int i = paramArrayOfChar.length;
/*     */     char[] arrayOfChar;
/*  65 */     if (paramInt > i) {
/*  66 */       int j = i * 3 / 2 + 1;
/*  67 */       if (j < paramInt) {
/*  68 */         j = paramInt;
/*     */       }
/*     */       
/*  71 */       arrayOfChar = new char[j];
/*  72 */       System.arraycopy(paramArrayOfChar, 0, arrayOfChar, 0, i);
/*     */     }
/*     */     else {
/*  75 */       arrayOfChar = paramArrayOfChar;
/*     */     }
/*  77 */     return arrayOfChar;
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
/*     */   public static double[] ensureCapacity(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/*  90 */     int i = paramArrayOfDouble.length;
/*     */     double[] arrayOfDouble;
/*  92 */     if (paramInt > i) {
/*  93 */       int j = i * 3 / 2 + 1;
/*  94 */       if (j < paramInt) {
/*  95 */         j = paramInt;
/*     */       }
/*     */       
/*  98 */       arrayOfDouble = new double[j];
/*     */       
/* 100 */       System.arraycopy(paramArrayOfDouble, 0, arrayOfDouble, 0, i);
/*     */     }
/*     */     else {
/* 103 */       arrayOfDouble = paramArrayOfDouble;
/*     */     }
/* 105 */     return arrayOfDouble;
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
/*     */   public static float[] ensureCapacity(float[] paramArrayOfFloat, int paramInt)
/*     */   {
/* 118 */     int i = paramArrayOfFloat.length;
/*     */     float[] arrayOfFloat;
/* 120 */     if (paramInt > i) {
/* 121 */       int j = i * 3 / 2 + 1;
/* 122 */       if (j < paramInt) {
/* 123 */         j = paramInt;
/*     */       }
/*     */       
/* 126 */       arrayOfFloat = new float[j];
/* 127 */       System.arraycopy(paramArrayOfFloat, 0, arrayOfFloat, 0, i);
/*     */     }
/*     */     else {
/* 130 */       arrayOfFloat = paramArrayOfFloat;
/*     */     }
/* 132 */     return arrayOfFloat;
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
/*     */   public static int[] ensureCapacity(int[] paramArrayOfInt, int paramInt)
/*     */   {
/* 145 */     int i = paramArrayOfInt.length;
/*     */     int[] arrayOfInt;
/* 147 */     if (paramInt > i) {
/* 148 */       int j = i * 3 / 2 + 1;
/* 149 */       if (j < paramInt) {
/* 150 */         j = paramInt;
/*     */       }
/*     */       
/* 153 */       arrayOfInt = new int[j];
/* 154 */       System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, i);
/*     */     }
/*     */     else {
/* 157 */       arrayOfInt = paramArrayOfInt;
/*     */     }
/* 159 */     return arrayOfInt;
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
/*     */   public static long[] ensureCapacity(long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 172 */     int i = paramArrayOfLong.length;
/*     */     long[] arrayOfLong;
/* 174 */     if (paramInt > i) {
/* 175 */       int j = i * 3 / 2 + 1;
/* 176 */       if (j < paramInt) {
/* 177 */         j = paramInt;
/*     */       }
/*     */       
/* 180 */       arrayOfLong = new long[j];
/* 181 */       System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, i);
/*     */     }
/*     */     else {
/* 184 */       arrayOfLong = paramArrayOfLong;
/*     */     }
/* 186 */     return arrayOfLong;
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
/*     */   public static Object[] ensureCapacity(Object[] paramArrayOfObject, int paramInt)
/*     */   {
/* 199 */     int i = paramArrayOfObject.length;
/*     */     Object[] arrayOfObject;
/* 201 */     if (paramInt > i) {
/* 202 */       int j = i * 3 / 2 + 1;
/* 203 */       if (j < paramInt) {
/* 204 */         j = paramInt;
/*     */       }
/*     */       
/* 207 */       arrayOfObject = new Object[j];
/* 208 */       System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, i);
/*     */     }
/*     */     else {
/* 211 */       arrayOfObject = paramArrayOfObject;
/*     */     }
/* 213 */     return arrayOfObject;
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
/*     */   public static short[] ensureCapacity(short[] paramArrayOfShort, int paramInt)
/*     */   {
/* 226 */     int i = paramArrayOfShort.length;
/*     */     short[] arrayOfShort;
/* 228 */     if (paramInt > i) {
/* 229 */       int j = i * 3 / 2 + 1;
/* 230 */       if (j < paramInt) {
/* 231 */         j = paramInt;
/*     */       }
/*     */       
/* 234 */       arrayOfShort = new short[j];
/* 235 */       System.arraycopy(paramArrayOfShort, 0, arrayOfShort, 0, i);
/*     */     }
/*     */     else {
/* 238 */       arrayOfShort = paramArrayOfShort;
/*     */     }
/* 240 */     return arrayOfShort;
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
/*     */   public static boolean[] ensureCapacity(boolean[] paramArrayOfBoolean, int paramInt)
/*     */   {
/* 253 */     int i = paramArrayOfBoolean.length;
/*     */     boolean[] arrayOfBoolean;
/* 255 */     if (paramInt > i) {
/* 256 */       int j = i * 3 / 2 + 1;
/* 257 */       if (j < paramInt) {
/* 258 */         j = paramInt;
/*     */       }
/*     */       
/* 261 */       arrayOfBoolean = new boolean[j];
/* 262 */       System.arraycopy(paramArrayOfBoolean, 0, arrayOfBoolean, 0, i);
/*     */     }
/*     */     else {
/* 265 */       arrayOfBoolean = paramArrayOfBoolean;
/*     */     }
/* 267 */     return arrayOfBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(byte[] paramArrayOfByte)
/*     */   {
/* 277 */     StringBuffer localStringBuffer = new StringBuffer();
/* 278 */     localStringBuffer.append("[");
/* 279 */     int i = paramArrayOfByte.length - 1;
/* 280 */     for (int j = 0; j <= i; j++) {
/* 281 */       localStringBuffer.append(paramArrayOfByte[j]);
/* 282 */       if (j < i)
/* 283 */         localStringBuffer.append(", ");
/*     */     }
/* 285 */     localStringBuffer.append("]");
/* 286 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(char[] paramArrayOfChar)
/*     */   {
/* 296 */     StringBuffer localStringBuffer = new StringBuffer();
/* 297 */     localStringBuffer.append("[");
/* 298 */     int i = paramArrayOfChar.length - 1;
/* 299 */     for (int j = 0; j <= i; j++) {
/* 300 */       localStringBuffer.append(paramArrayOfChar[j]);
/* 301 */       if (j < i)
/* 302 */         localStringBuffer.append(", ");
/*     */     }
/* 304 */     localStringBuffer.append("]");
/* 305 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(double[] paramArrayOfDouble)
/*     */   {
/* 315 */     StringBuffer localStringBuffer = new StringBuffer();
/* 316 */     localStringBuffer.append("[");
/* 317 */     int i = paramArrayOfDouble.length - 1;
/* 318 */     for (int j = 0; j <= i; j++) {
/* 319 */       localStringBuffer.append(paramArrayOfDouble[j]);
/* 320 */       if (j < i)
/* 321 */         localStringBuffer.append(", ");
/*     */     }
/* 323 */     localStringBuffer.append("]");
/* 324 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(float[] paramArrayOfFloat)
/*     */   {
/* 334 */     StringBuffer localStringBuffer = new StringBuffer();
/* 335 */     localStringBuffer.append("[");
/* 336 */     int i = paramArrayOfFloat.length - 1;
/* 337 */     for (int j = 0; j <= i; j++) {
/* 338 */       localStringBuffer.append(paramArrayOfFloat[j]);
/* 339 */       if (j < i)
/* 340 */         localStringBuffer.append(", ");
/*     */     }
/* 342 */     localStringBuffer.append("]");
/* 343 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(int[] paramArrayOfInt)
/*     */   {
/* 353 */     StringBuffer localStringBuffer = new StringBuffer();
/* 354 */     localStringBuffer.append("[");
/* 355 */     int i = paramArrayOfInt.length - 1;
/* 356 */     for (int j = 0; j <= i; j++) {
/* 357 */       localStringBuffer.append(paramArrayOfInt[j]);
/* 358 */       if (j < i)
/* 359 */         localStringBuffer.append(", ");
/*     */     }
/* 361 */     localStringBuffer.append("]");
/* 362 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(long[] paramArrayOfLong)
/*     */   {
/* 372 */     StringBuffer localStringBuffer = new StringBuffer();
/* 373 */     localStringBuffer.append("[");
/* 374 */     int i = paramArrayOfLong.length - 1;
/* 375 */     for (int j = 0; j <= i; j++) {
/* 376 */       localStringBuffer.append(paramArrayOfLong[j]);
/* 377 */       if (j < i)
/* 378 */         localStringBuffer.append(", ");
/*     */     }
/* 380 */     localStringBuffer.append("]");
/* 381 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(Object[] paramArrayOfObject)
/*     */   {
/* 391 */     StringBuffer localStringBuffer = new StringBuffer();
/* 392 */     localStringBuffer.append("[");
/* 393 */     int i = paramArrayOfObject.length - 1;
/* 394 */     for (int j = 0; j <= i; j++) {
/* 395 */       localStringBuffer.append(paramArrayOfObject[j]);
/* 396 */       if (j < i)
/* 397 */         localStringBuffer.append(", ");
/*     */     }
/* 399 */     localStringBuffer.append("]");
/* 400 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(short[] paramArrayOfShort)
/*     */   {
/* 410 */     StringBuffer localStringBuffer = new StringBuffer();
/* 411 */     localStringBuffer.append("[");
/* 412 */     int i = paramArrayOfShort.length - 1;
/* 413 */     for (int j = 0; j <= i; j++) {
/* 414 */       localStringBuffer.append(paramArrayOfShort[j]);
/* 415 */       if (j < i)
/* 416 */         localStringBuffer.append(", ");
/*     */     }
/* 418 */     localStringBuffer.append("]");
/* 419 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toString(boolean[] paramArrayOfBoolean)
/*     */   {
/* 429 */     StringBuffer localStringBuffer = new StringBuffer();
/* 430 */     localStringBuffer.append("[");
/* 431 */     int i = paramArrayOfBoolean.length - 1;
/* 432 */     for (int j = 0; j <= i; j++) {
/* 433 */       localStringBuffer.append(paramArrayOfBoolean[j]);
/* 434 */       if (j < i)
/* 435 */         localStringBuffer.append(", ");
/*     */     }
/* 437 */     localStringBuffer.append("]");
/* 438 */     return localStringBuffer.toString();
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
/*     */   public static byte[] trimToCapacity(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 451 */     if (paramArrayOfByte.length > paramInt) {
/* 452 */       byte[] arrayOfByte = paramArrayOfByte;
/* 453 */       paramArrayOfByte = new byte[paramInt];
/* 454 */       System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, paramInt);
/*     */     }
/* 456 */     return paramArrayOfByte;
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
/*     */   public static char[] trimToCapacity(char[] paramArrayOfChar, int paramInt)
/*     */   {
/* 469 */     if (paramArrayOfChar.length > paramInt) {
/* 470 */       char[] arrayOfChar = paramArrayOfChar;
/* 471 */       paramArrayOfChar = new char[paramInt];
/* 472 */       System.arraycopy(arrayOfChar, 0, paramArrayOfChar, 0, paramInt);
/*     */     }
/* 474 */     return paramArrayOfChar;
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
/*     */   public static double[] trimToCapacity(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 487 */     if (paramArrayOfDouble.length > paramInt) {
/* 488 */       double[] arrayOfDouble = paramArrayOfDouble;
/* 489 */       paramArrayOfDouble = new double[paramInt];
/* 490 */       System.arraycopy(arrayOfDouble, 0, paramArrayOfDouble, 0, paramInt);
/*     */     }
/* 492 */     return paramArrayOfDouble;
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
/*     */   public static float[] trimToCapacity(float[] paramArrayOfFloat, int paramInt)
/*     */   {
/* 505 */     if (paramArrayOfFloat.length > paramInt) {
/* 506 */       float[] arrayOfFloat = paramArrayOfFloat;
/* 507 */       paramArrayOfFloat = new float[paramInt];
/* 508 */       System.arraycopy(arrayOfFloat, 0, paramArrayOfFloat, 0, paramInt);
/*     */     }
/* 510 */     return paramArrayOfFloat;
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
/*     */   public static int[] trimToCapacity(int[] paramArrayOfInt, int paramInt)
/*     */   {
/* 523 */     if (paramArrayOfInt.length > paramInt) {
/* 524 */       int[] arrayOfInt = paramArrayOfInt;
/* 525 */       paramArrayOfInt = new int[paramInt];
/* 526 */       System.arraycopy(arrayOfInt, 0, paramArrayOfInt, 0, paramInt);
/*     */     }
/* 528 */     return paramArrayOfInt;
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
/*     */   public static long[] trimToCapacity(long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 541 */     if (paramArrayOfLong.length > paramInt) {
/* 542 */       long[] arrayOfLong = paramArrayOfLong;
/* 543 */       paramArrayOfLong = new long[paramInt];
/* 544 */       System.arraycopy(arrayOfLong, 0, paramArrayOfLong, 0, paramInt);
/*     */     }
/* 546 */     return paramArrayOfLong;
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
/*     */   public static Object[] trimToCapacity(Object[] paramArrayOfObject, int paramInt)
/*     */   {
/* 559 */     if (paramArrayOfObject.length > paramInt) {
/* 560 */       Object[] arrayOfObject = paramArrayOfObject;
/* 561 */       paramArrayOfObject = new Object[paramInt];
/* 562 */       System.arraycopy(arrayOfObject, 0, paramArrayOfObject, 0, paramInt);
/*     */     }
/* 564 */     return paramArrayOfObject;
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
/*     */   public static short[] trimToCapacity(short[] paramArrayOfShort, int paramInt)
/*     */   {
/* 577 */     if (paramArrayOfShort.length > paramInt) {
/* 578 */       short[] arrayOfShort = paramArrayOfShort;
/* 579 */       paramArrayOfShort = new short[paramInt];
/* 580 */       System.arraycopy(arrayOfShort, 0, paramArrayOfShort, 0, paramInt);
/*     */     }
/* 582 */     return paramArrayOfShort;
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
/*     */   public static boolean[] trimToCapacity(boolean[] paramArrayOfBoolean, int paramInt)
/*     */   {
/* 595 */     if (paramArrayOfBoolean.length > paramInt) {
/* 596 */       boolean[] arrayOfBoolean = paramArrayOfBoolean;
/* 597 */       paramArrayOfBoolean = new boolean[paramInt];
/* 598 */       System.arraycopy(arrayOfBoolean, 0, paramArrayOfBoolean, 0, paramInt);
/*     */     }
/* 600 */     return paramArrayOfBoolean;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/Arrays.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */