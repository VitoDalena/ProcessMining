/*     */ package cern.colt.io;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.FloatArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.list.LongArrayList;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import cern.jet.random.engine.RandomEngine;
/*     */ import java.io.PrintStream;
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
/*     */ public class Converting
/*     */ {
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  69 */     int i = 0;
/*  70 */     int j = Integer.parseInt(paramArrayOfString[(i++)]);
/*  71 */     int k = Integer.parseInt(paramArrayOfString[(i++)]);
/*     */     
/*     */ 
/*  74 */     int[] arrayOfInt1 = new int[k];
/*  75 */     int[] arrayOfInt2 = new int[k];
/*  76 */     double[] arrayOfDouble1 = new double[k];
/*  77 */     double[] arrayOfDouble2 = new double[k];
/*  78 */     long[] arrayOfLong1 = new long[k];
/*  79 */     long[] arrayOfLong2 = new long[k];
/*  80 */     float[] arrayOfFloat1 = new float[k];
/*  81 */     float[] arrayOfFloat2 = new float[k];
/*     */     
/*  83 */     IntArrayList localIntArrayList1 = new IntArrayList(arrayOfInt1);
/*  84 */     IntArrayList localIntArrayList2 = new IntArrayList(arrayOfInt2);
/*  85 */     DoubleArrayList localDoubleArrayList1 = new DoubleArrayList(arrayOfDouble1);
/*  86 */     DoubleArrayList localDoubleArrayList2 = new DoubleArrayList(arrayOfDouble2);
/*  87 */     LongArrayList localLongArrayList1 = new LongArrayList(arrayOfLong1);
/*  88 */     LongArrayList localLongArrayList2 = new LongArrayList(arrayOfLong2);
/*  89 */     FloatArrayList localFloatArrayList1 = new FloatArrayList(arrayOfFloat1);
/*  90 */     FloatArrayList localFloatArrayList2 = new FloatArrayList(arrayOfFloat2);
/*     */     
/*  92 */     byte[] arrayOfByte1 = new byte[k * 4];
/*  93 */     byte[] arrayOfByte2 = new byte[k * 8];
/*  94 */     byte[] arrayOfByte3 = new byte[k * 8];
/*  95 */     byte[] arrayOfByte4 = new byte[k * 4];
/*     */     
/*     */ 
/*  98 */     MersenneTwister localMersenneTwister = new MersenneTwister();
/*     */     
/* 100 */     int m = 8;
/* 101 */     Timer[] arrayOfTimer = new Timer[m];
/* 102 */     for (int n = 0; n < m; n++) arrayOfTimer[n] = new Timer();
/* 103 */     for (int i1 = 0; i1 < j; i1++) {
/* 104 */       for (int i2 = 0; i2 < k; i2++) {
/* 105 */         arrayOfInt1[i2] = localMersenneTwister.nextInt();
/*     */         
/* 107 */         arrayOfDouble1[i2] = localMersenneTwister.nextDouble();
/*     */       }
/*     */       
/*     */ 
/* 111 */       i3 = 0;
/* 112 */       arrayOfTimer[i3].start();
/* 113 */       write(arrayOfInt1, 0, arrayOfByte1, 0, k * 4);
/* 114 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 116 */       arrayOfTimer[i3].start();
/* 117 */       read(arrayOfByte1, 0, arrayOfInt2, 0, k * 4);
/* 118 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 120 */       if (!localIntArrayList1.equals(localIntArrayList2)) { throw new InternalError("oops");
/*     */       }
/* 122 */       arrayOfTimer[i3].start();
/* 123 */       write(arrayOfDouble1, 0, arrayOfByte2, 0, k * 8);
/* 124 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 126 */       arrayOfTimer[i3].start();
/* 127 */       read(arrayOfByte2, 0, arrayOfDouble2, 0, k * 8);
/* 128 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 130 */       if (!localDoubleArrayList1.equals(localDoubleArrayList2)) { throw new InternalError("oops");
/*     */       }
/* 132 */       arrayOfTimer[i3].start();
/* 133 */       write(arrayOfLong1, 0, arrayOfByte3, 0, k * 8);
/* 134 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 136 */       arrayOfTimer[i3].start();
/* 137 */       read(arrayOfByte3, 0, arrayOfLong2, 0, k * 8);
/* 138 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 140 */       if (!localLongArrayList1.equals(localLongArrayList2)) { throw new InternalError("oops");
/*     */       }
/* 142 */       arrayOfTimer[i3].start();
/* 143 */       write(arrayOfFloat1, 0, arrayOfByte4, 0, k * 4);
/* 144 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 146 */       arrayOfTimer[i3].start();
/* 147 */       read(arrayOfByte4, 0, arrayOfFloat2, 0, k * 4);
/* 148 */       arrayOfTimer[(i3++)].stop();
/*     */       
/* 150 */       if (!localFloatArrayList1.equals(localFloatArrayList2)) { throw new InternalError("oops");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 155 */     System.out.println();
/* 156 */     float f = k * j / 1000000.0F;
/* 157 */     int i3 = 0;
/* 158 */     System.out.println("write int[]    --> byte[]: " + arrayOfTimer[i3] + ", " + f * 4.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 159 */     System.out.println("read  byte[]   --> int[]: " + arrayOfTimer[i3] + ", " + f * 4.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 160 */     System.out.println("write double[] --> byte[]: " + arrayOfTimer[i3] + ", " + f * 8.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 161 */     System.out.println("read  byte[]   --> double[]: " + arrayOfTimer[i3] + ", " + f * 8.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/*     */     
/* 163 */     System.out.println("write long[]   --> byte[]: " + arrayOfTimer[i3] + ", " + f * 8.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 164 */     System.out.println("read  byte[]   --> long[]: " + arrayOfTimer[i3] + ", " + f * 8.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 165 */     System.out.println("write float[]  --> byte[]: " + arrayOfTimer[i3] + ", " + f * 4.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/* 166 */     System.out.println("read  byte[]   --> float[]: " + arrayOfTimer[i3] + ", " + f * 4.0F / arrayOfTimer[(i3++)].seconds() + " MB/s");
/*     */     
/* 168 */     System.out.println("no bug detected.\n\n");
/*     */   }
/*     */   
/* 171 */   public static void read(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3) { System.arraycopy(paramArrayOfByte1, paramInt1, paramArrayOfByte2, paramInt2, paramInt3); }
/*     */   
/*     */   public static void read(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3) {
/* 174 */     while (paramInt3 > 0) {
/* 175 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 176 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 177 */       paramArrayOfChar[(paramInt2++)] = ((char)((i << 8) + (j << 0)));
/* 178 */       paramInt3 -= 2;
/*     */     }
/*     */   }
/*     */   
/* 182 */   public static void read(byte[] paramArrayOfByte, int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 183 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 184 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 185 */       int k = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 186 */       int m = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 187 */       int n = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 188 */       int i1 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 189 */       int i2 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 190 */       int i3 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 191 */       paramArrayOfDouble[(paramInt2++)] = Double.longBitsToDouble(((i << 24) + (j << 16) + (k << 8) + (m << 0) << 32) + ((n << 24) + (i1 << 16) + (i2 << 8) + (i3 << 0) & 0xFFFFFFFF));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 197 */       paramInt3 -= 8;
/*     */     }
/*     */   }
/*     */   
/* 201 */   public static void read(byte[] paramArrayOfByte, int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 202 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 203 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 204 */       int k = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 205 */       int m = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 206 */       paramArrayOfFloat[(paramInt2++)] = Float.intBitsToFloat((i << 24) + (j << 16) + (k << 8) + (m << 0));
/* 207 */       paramInt3 -= 4;
/*     */     }
/*     */   }
/*     */   
/* 211 */   public static void read(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 212 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 213 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 214 */       int k = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 215 */       int m = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 216 */       paramArrayOfInt[(paramInt2++)] = ((i << 24) + (j << 16) + (k << 8) + (m << 0));
/* 217 */       paramInt3 -= 4;
/*     */     }
/*     */   }
/*     */   
/* 221 */   public static void read(byte[] paramArrayOfByte, int paramInt1, long[] paramArrayOfLong, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 222 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 223 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 224 */       int k = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 225 */       int m = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 226 */       int n = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 227 */       int i1 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 228 */       int i2 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 229 */       int i3 = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 230 */       paramArrayOfLong[(paramInt2++)] = (((i << 24) + (j << 16) + (k << 8) + (m << 0) << 32) + ((n << 24) + (i1 << 16) + (i2 << 8) + (i3 << 0) & 0xFFFFFFFF));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 235 */       paramInt3 -= 8;
/*     */     }
/*     */   }
/*     */   
/* 239 */   public static void read(byte[] paramArrayOfByte, int paramInt1, short[] paramArrayOfShort, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 240 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 241 */       int j = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 242 */       paramArrayOfShort[(paramInt2++)] = ((short)((i << 8) + (j << 0)));
/* 243 */       paramInt3 -= 2;
/*     */     }
/*     */   }
/*     */   
/* 247 */   public static void read(byte[] paramArrayOfByte, int paramInt1, boolean[] paramArrayOfBoolean, int paramInt2, int paramInt3) { while (paramInt3 > 0) {
/* 248 */       int i = paramArrayOfByte[(paramInt1++)] & 0xFF;
/* 249 */       paramArrayOfBoolean[(paramInt2++)] = (i != 0 ? 1 : false);
/* 250 */       paramInt3--;
/*     */     }
/*     */   }
/*     */   
/* 254 */   public static boolean readBoolean(byte[] paramArrayOfByte, int paramInt) { return (paramArrayOfByte[paramInt] & 0xFF) != 0; }
/*     */   
/*     */ 
/* 257 */   public static byte readByte(byte[] paramArrayOfByte, int paramInt) { return paramArrayOfByte[paramInt]; }
/*     */   
/*     */   public static char readChar(byte[] paramArrayOfByte, int paramInt) {
/* 260 */     int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 261 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 262 */     return (char)((i << 8) + (j << 0));
/*     */   }
/*     */   
/* 265 */   public static double readDouble(byte[] paramArrayOfByte, int paramInt) { int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 266 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 267 */     int k = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 268 */     int m = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 269 */     int n = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 270 */     int i1 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 271 */     int i2 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 272 */     int i3 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 273 */     return Double.longBitsToDouble(((i << 24) + (j << 16) + (k << 8) + (m << 0) << 32) + ((n << 24) + (i1 << 16) + (i2 << 8) + (i3 << 0) & 0xFFFFFFFF));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static float readFloat(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 280 */     int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 281 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 282 */     int k = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 283 */     int m = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 284 */     return Float.intBitsToFloat((i << 24) + (j << 16) + (k << 8) + (m << 0));
/*     */   }
/*     */   
/* 287 */   public static int readInt(byte[] paramArrayOfByte, int paramInt) { int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 288 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 289 */     int k = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 290 */     int m = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 291 */     return (i << 24) + (j << 16) + (k << 8) + (m << 0);
/*     */   }
/*     */   
/* 294 */   public static long readLong(byte[] paramArrayOfByte, int paramInt) { int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 295 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 296 */     int k = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 297 */     int m = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 298 */     int n = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 299 */     int i1 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 300 */     int i2 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 301 */     int i3 = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 302 */     return ((i << 24) + (j << 16) + (k << 8) + (m << 0) << 32) + ((n << 24) + (i1 << 16) + (i2 << 8) + (i3 << 0) & 0xFFFFFFFF);
/*     */   }
/*     */   
/*     */ 
/*     */   public static short readShort(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 308 */     int i = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 309 */     int j = paramArrayOfByte[(paramInt++)] & 0xFF;
/* 310 */     return (short)((i << 8) + (j << 0));
/*     */   }
/*     */   
/* 313 */   public static int readUnsignedByte(byte[] paramArrayOfByte, int paramInt) { return paramArrayOfByte[paramInt]; }
/*     */   
/*     */   public static int readUnsignedShort(byte[] paramArrayOfByte, int paramInt) {
/* 316 */     int i = paramArrayOfByte[(paramInt++)];
/* 317 */     int j = paramArrayOfByte[(paramInt++)];
/* 318 */     return (i << 8) + (j << 0);
/*     */   }
/*     */   
/* 321 */   public static void write(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3) { System.arraycopy(paramArrayOfByte1, paramInt1, paramArrayOfByte2, paramInt2, paramInt3); }
/*     */   
/*     */   public static void write(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*     */   {
/* 325 */     while (paramInt3 > 0) {
/* 326 */       int i = paramArrayOfChar[(paramInt1++)];
/* 327 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 8 & 0xFF));
/* 328 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 0 & 0xFF));
/* 329 */       paramInt3 -= 2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void write(double[] paramArrayOfDouble, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*     */   {
/* 337 */     while (paramInt3 > 0) {
/* 338 */       long l = Double.doubleToLongBits(paramArrayOfDouble[(paramInt1++)]);
/* 339 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 56) & 0xFF));
/* 340 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 48) & 0xFF));
/* 341 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 40) & 0xFF));
/* 342 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 32) & 0xFF));
/* 343 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 24) & 0xFF));
/* 344 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 16) & 0xFF));
/* 345 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 8) & 0xFF));
/* 346 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 0) & 0xFF));
/* 347 */       paramInt3 -= 8;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void write(float[] paramArrayOfFloat, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 352 */     while (paramInt3 > 0) {
/* 353 */       int i = Float.floatToIntBits(paramArrayOfFloat[(paramInt1++)]);
/* 354 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 24 & 0xFF));
/* 355 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 16 & 0xFF));
/* 356 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 8 & 0xFF));
/* 357 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 0 & 0xFF));
/* 358 */       paramInt3 -= 4;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void write(int[] paramArrayOfInt, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 363 */     while (paramInt3 > 0) {
/* 364 */       int i = paramArrayOfInt[(paramInt1++)];
/* 365 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 24 & 0xFF));
/* 366 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 16 & 0xFF));
/* 367 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 8 & 0xFF));
/* 368 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 0 & 0xFF));
/* 369 */       paramInt3 -= 4;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void write(long[] paramArrayOfLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 374 */     while (paramInt3 > 0) {
/* 375 */       long l = paramArrayOfLong[(paramInt1++)];
/* 376 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 56) & 0xFF));
/* 377 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 48) & 0xFF));
/* 378 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 40) & 0xFF));
/* 379 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 32) & 0xFF));
/* 380 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 24) & 0xFF));
/* 381 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 16) & 0xFF));
/* 382 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 8) & 0xFF));
/* 383 */       paramArrayOfByte[(paramInt2++)] = ((byte)((int)(l >>> 0) & 0xFF));
/* 384 */       paramInt3 -= 8;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void write(short[] paramArrayOfShort, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 389 */     while (paramInt3 > 0) {
/* 390 */       int i = paramArrayOfShort[(paramInt1++)];
/* 391 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 8 & 0xFF));
/* 392 */       paramArrayOfByte[(paramInt2++)] = ((byte)(i >>> 0 & 0xFF));
/* 393 */       paramInt3 -= 2;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void write(boolean[] paramArrayOfBoolean, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3) {
/* 398 */     while (paramInt3 > 0) {
/* 399 */       int i = paramArrayOfBoolean[(paramInt1++)];
/* 400 */       paramArrayOfByte[(paramInt2++)] = (i != 0 ? 1 : 0);
/* 401 */       paramInt3--;
/*     */     }
/*     */   }
/*     */   
/* 405 */   public static void write(byte paramByte, byte[] paramArrayOfByte, int paramInt) { paramArrayOfByte[paramInt] = paramByte; }
/*     */   
/*     */   public static void write(char paramChar, byte[] paramArrayOfByte, int paramInt) {
/* 408 */     paramArrayOfByte[(paramInt++)] = ((byte)(paramChar >>> '\b' & 0xFF));
/* 409 */     paramArrayOfByte[(paramInt++)] = ((byte)(paramChar >>> '\000' & 0xFF));
/*     */   }
/*     */   
/* 412 */   public static void write(double paramDouble, byte[] paramArrayOfByte, int paramInt) { long l = Double.doubleToLongBits(paramDouble);
/* 413 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 56) & 0xFF));
/* 414 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 48) & 0xFF));
/* 415 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 40) & 0xFF));
/* 416 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 32) & 0xFF));
/* 417 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 24) & 0xFF));
/* 418 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 16) & 0xFF));
/* 419 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 8) & 0xFF));
/* 420 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(l >>> 0) & 0xFF));
/*     */   }
/*     */   
/* 423 */   public static void write(float paramFloat, byte[] paramArrayOfByte, int paramInt) { int i = Float.floatToIntBits(paramFloat);
/* 424 */     paramArrayOfByte[(paramInt++)] = ((byte)(i >>> 24 & 0xFF));
/* 425 */     paramArrayOfByte[(paramInt++)] = ((byte)(i >>> 16 & 0xFF));
/* 426 */     paramArrayOfByte[(paramInt++)] = ((byte)(i >>> 8 & 0xFF));
/* 427 */     paramArrayOfByte[(paramInt++)] = ((byte)(i >>> 0 & 0xFF));
/*     */   }
/*     */   
/* 430 */   public static void write(int paramInt1, byte[] paramArrayOfByte, int paramInt2) { paramArrayOfByte[(paramInt2++)] = ((byte)(paramInt1 >>> 24 & 0xFF));
/* 431 */     paramArrayOfByte[(paramInt2++)] = ((byte)(paramInt1 >>> 16 & 0xFF));
/* 432 */     paramArrayOfByte[(paramInt2++)] = ((byte)(paramInt1 >>> 8 & 0xFF));
/* 433 */     paramArrayOfByte[(paramInt2++)] = ((byte)(paramInt1 >>> 0 & 0xFF));
/*     */   }
/*     */   
/* 436 */   public static void write(long paramLong, byte[] paramArrayOfByte, int paramInt) { paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 56) & 0xFF));
/* 437 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 48) & 0xFF));
/* 438 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 40) & 0xFF));
/* 439 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 32) & 0xFF));
/* 440 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 24) & 0xFF));
/* 441 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 16) & 0xFF));
/* 442 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 8) & 0xFF));
/* 443 */     paramArrayOfByte[(paramInt++)] = ((byte)((int)(paramLong >>> 0) & 0xFF));
/*     */   }
/*     */   
/* 446 */   public static void write(short paramShort, byte[] paramArrayOfByte, int paramInt) { paramArrayOfByte[(paramInt++)] = ((byte)(paramShort >>> 8 & 0xFF));
/* 447 */     paramArrayOfByte[(paramInt++)] = ((byte)(paramShort >>> 0 & 0xFF));
/*     */   }
/*     */   
/* 450 */   public static void write(boolean paramBoolean, byte[] paramArrayOfByte, int paramInt) { paramArrayOfByte[paramInt] = (paramBoolean ? 1 : 0); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/io/Converting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */