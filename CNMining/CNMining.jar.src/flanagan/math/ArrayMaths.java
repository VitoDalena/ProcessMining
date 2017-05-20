/*       */ package flanagan.math;
/*       */ 
/*       */ import flanagan.analysis.Stat;
/*       */ import flanagan.circuits.Phasor;
/*       */ import flanagan.circuits.PhasorMatrix;
/*       */ import flanagan.complex.Complex;
/*       */ import flanagan.complex.ComplexMatrix;
/*       */ import flanagan.io.PrintToScreen;
/*       */ import flanagan.plot.PlotGraph;
/*       */ import java.io.PrintStream;
/*       */ import java.math.BigDecimal;
/*       */ import java.math.BigInteger;
/*       */ import java.util.ArrayList;
/*       */ import java.util.HashMap;
/*       */ import java.util.Map;
/*       */ import java.util.Vector;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public class ArrayMaths
/*       */ {
/*    60 */   protected ArrayList<Object> array = null;
/*       */   
/*    62 */   protected int length = 0;
/*    63 */   protected int type = -1;
/*       */   
/*    65 */   protected int[] originalTypes = null;
/*       */   
/*    67 */   protected String[] typeName = { "double", "Double", "float", "Float", "long", "Long", "int", "Integer", "short", "Short", "byte", "Byte", "BigDecimal", "BigInteger", "Complex", "Phasor", "char", "Character", "String" };
/*       */   
/*    69 */   protected ArrayList<Object> summ = new ArrayList(1);
/*    70 */   protected ArrayList<Object> productt = new ArrayList(1);
/*       */   
/*    72 */   protected int[] sortedIndices = null;
/*       */   
/*    74 */   protected ArrayList<Object> minmax = new ArrayList(2);
/*       */   
/*    76 */   protected int maxIndex = -1;
/*    77 */   protected int minIndex = -1;
/*       */   
/*    79 */   protected boolean sumDone = false;
/*    80 */   protected boolean productDone = false;
/*       */   
/*    82 */   protected boolean sumlongToDouble = false;
/*    83 */   protected boolean productlongToDouble = false;
/*       */   
/*    85 */   protected boolean suppressMessages = false;
/*       */   
/*       */ 
/*       */ 
/*    89 */   protected static final Map<Object, Object> integers = new HashMap();
/*       */   
/*    91 */   static { integers.put(Integer.class, BigDecimal.valueOf(2147483647L));
/*    92 */     integers.put(Long.class, BigDecimal.valueOf(Long.MAX_VALUE));
/*    93 */     integers.put(Byte.class, BigDecimal.valueOf(127L));
/*    94 */     integers.put(Short.class, BigDecimal.valueOf(32767L));
/*    95 */     integers.put(BigInteger.class, BigDecimal.valueOf(-1L));
/*       */   }
/*       */   
/*       */   protected ArrayMaths()
/*       */   {
/*   100 */     this.array = new ArrayList();
/*       */   }
/*       */   
/*       */   public ArrayMaths(double[] array) {
/*   104 */     this.length = array.length;
/*   105 */     this.array = new ArrayList(this.length);
/*   106 */     this.type = 0;
/*   107 */     for (int i = 0; i < this.length; i++) this.array.add(new Double(array[i]));
/*   108 */     this.originalTypes = new int[this.length];
/*   109 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   110 */     this.originalTypes = new int[this.length];
/*   111 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   112 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Double[] array) {
/*   116 */     this.length = array.length;
/*   117 */     this.array = new ArrayList(this.length);
/*   118 */     this.type = 1;
/*   119 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   120 */     this.originalTypes = new int[this.length];
/*   121 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   122 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(long[] array) {
/*   126 */     this.length = array.length;
/*   127 */     this.array = new ArrayList(this.length);
/*   128 */     this.type = 4;
/*   129 */     for (int i = 0; i < this.length; i++) this.array.add(new Long(array[i]));
/*   130 */     this.originalTypes = new int[this.length];
/*   131 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   132 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Long[] array) {
/*   136 */     this.length = array.length;
/*   137 */     this.array = new ArrayList(this.length);
/*   138 */     this.type = 5;
/*   139 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   140 */     this.originalTypes = new int[this.length];
/*   141 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   142 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(float[] array) {
/*   146 */     this.length = array.length;
/*   147 */     this.array = new ArrayList(this.length);
/*   148 */     this.type = 2;
/*   149 */     for (int i = 0; i < this.length; i++) this.array.add(new Float(array[i]));
/*   150 */     this.originalTypes = new int[this.length];
/*   151 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   152 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Float[] array) {
/*   156 */     this.length = array.length;
/*   157 */     this.array = new ArrayList(this.length);
/*   158 */     this.type = 3;
/*   159 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   160 */     this.originalTypes = new int[this.length];
/*   161 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   162 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(int[] array) {
/*   166 */     this.length = array.length;
/*   167 */     this.array = new ArrayList(this.length);
/*   168 */     this.type = 6;
/*   169 */     for (int i = 0; i < this.length; i++) this.array.add(new Integer(array[i]));
/*   170 */     this.originalTypes = new int[this.length];
/*   171 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   172 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Integer[] array) {
/*   176 */     this.length = array.length;
/*   177 */     this.array = new ArrayList(this.length);
/*   178 */     this.type = 7;
/*   179 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   180 */     this.originalTypes = new int[this.length];
/*   181 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   182 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(short[] array) {
/*   186 */     this.length = array.length;
/*   187 */     this.array = new ArrayList(this.length);
/*   188 */     this.type = 8;
/*   189 */     for (int i = 0; i < this.length; i++) this.array.add(new Short(array[i]));
/*   190 */     this.originalTypes = new int[this.length];
/*   191 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   192 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Short[] array) {
/*   196 */     this.length = array.length;
/*   197 */     this.array = new ArrayList(this.length);
/*   198 */     this.type = 9;
/*   199 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   200 */     this.originalTypes = new int[this.length];
/*   201 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   202 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(byte[] array) {
/*   206 */     this.length = array.length;
/*   207 */     this.array = new ArrayList(this.length);
/*   208 */     this.type = 10;
/*   209 */     for (int i = 0; i < this.length; i++) this.array.add(new Byte(array[i]));
/*   210 */     this.originalTypes = new int[this.length];
/*   211 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   212 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Byte[] array) {
/*   216 */     this.length = array.length;
/*   217 */     this.array = new ArrayList(this.length);
/*   218 */     this.type = 11;
/*   219 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   220 */     this.originalTypes = new int[this.length];
/*   221 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   222 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(BigDecimal[] array) {
/*   226 */     this.length = array.length;
/*   227 */     this.array = new ArrayList(this.length);
/*   228 */     this.type = 12;
/*   229 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   230 */     this.originalTypes = new int[this.length];
/*   231 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   232 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(BigInteger[] array) {
/*   236 */     this.length = array.length;
/*   237 */     this.array = new ArrayList(this.length);
/*   238 */     this.type = 13;
/*   239 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   240 */     this.originalTypes = new int[this.length];
/*   241 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   242 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Complex[] array) {
/*   246 */     this.length = array.length;
/*   247 */     this.array = new ArrayList(this.length);
/*   248 */     this.type = 14;
/*   249 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   250 */     this.originalTypes = new int[this.length];
/*   251 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*       */   }
/*       */   
/*       */   public ArrayMaths(Phasor[] array) {
/*   255 */     this.length = array.length;
/*   256 */     this.array = new ArrayList(this.length);
/*   257 */     this.type = 15;
/*   258 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   259 */     this.originalTypes = new int[this.length];
/*   260 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*       */   }
/*       */   
/*       */   public ArrayMaths(char[] array) {
/*   264 */     this.length = array.length;
/*   265 */     this.array = new ArrayList(this.length);
/*   266 */     this.type = 16;
/*   267 */     for (int i = 0; i < this.length; i++) this.array.add(new Character(array[i]));
/*   268 */     this.originalTypes = new int[this.length];
/*   269 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   270 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Character[] array) {
/*   274 */     this.length = array.length;
/*   275 */     this.array = new ArrayList(this.length);
/*   276 */     this.type = 17;
/*   277 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   278 */     this.originalTypes = new int[this.length];
/*   279 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   280 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(String[] array) {
/*   284 */     this.length = array.length;
/*   285 */     this.array = new ArrayList(this.length);
/*   286 */     this.type = 18;
/*   287 */     for (int i = 0; i < this.length; i++) this.array.add(array[i]);
/*   288 */     this.originalTypes = new int[this.length];
/*   289 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*       */   }
/*       */   
/*       */   public ArrayMaths(Object[] array) {
/*   293 */     this.length = array.length;
/*   294 */     this.originalTypes = new int[this.length];
/*   295 */     ArrayList<Object> arrayl = new ArrayList(this.length);
/*   296 */     for (int i = 0; i < this.length; i++) arrayl.add(array[i]);
/*   297 */     ArrayMaths am = new ArrayMaths(arrayl);
/*   298 */     this.array = am.getArray_as_ArrayList();
/*   299 */     this.minmax = am.minmax;
/*   300 */     this.minIndex = am.minIndex;
/*   301 */     this.maxIndex = am.maxIndex;
/*   302 */     this.originalTypes = am.originalTypes;
/*       */   }
/*       */   
/*       */   public ArrayMaths(Stat arrayst) {
/*   306 */     this.array = arrayst.getArray_as_ArrayList();
/*   307 */     this.length = this.array.size();
/*   308 */     this.type = arrayst.typeIndex();
/*   309 */     this.originalTypes = new int[this.length];
/*   310 */     for (int i = 0; i < this.length; i++) this.originalTypes[i] = this.type;
/*   311 */     minmax();
/*       */   }
/*       */   
/*       */   public ArrayMaths(Vector<Object> arrayv) {
/*   315 */     this.length = arrayv.size();
/*   316 */     this.originalTypes = new int[this.length];
/*   317 */     this.array = new ArrayList(this.length);
/*       */     
/*   319 */     for (int i = 0; i < this.length; i++) {
/*   320 */       this.originalTypes[i] = -1;
/*   321 */       if ((arrayv.elementAt(i) instanceof Double)) this.originalTypes[i] = 1;
/*   322 */       if ((arrayv.elementAt(i) instanceof Float)) this.originalTypes[i] = 3;
/*   323 */       if ((arrayv.elementAt(i) instanceof Long)) this.originalTypes[i] = 5;
/*   324 */       if ((arrayv.elementAt(i) instanceof Integer)) this.originalTypes[i] = 7;
/*   325 */       if ((arrayv.elementAt(i) instanceof Short)) this.originalTypes[i] = 9;
/*   326 */       if ((arrayv.elementAt(i) instanceof Byte)) this.originalTypes[i] = 11;
/*   327 */       if ((arrayv.elementAt(i) instanceof BigDecimal)) this.originalTypes[i] = 12;
/*   328 */       if ((arrayv.elementAt(i) instanceof BigInteger)) this.originalTypes[i] = 13;
/*   329 */       if ((arrayv.elementAt(i) instanceof Complex)) this.originalTypes[i] = 14;
/*   330 */       if ((arrayv.elementAt(i) instanceof Phasor)) this.originalTypes[i] = 15;
/*   331 */       if ((arrayv.elementAt(i) instanceof Character)) this.originalTypes[i] = 17;
/*   332 */       if ((arrayv.elementAt(i) instanceof String)) this.originalTypes[i] = 18;
/*   333 */       if (this.originalTypes[i] == -1) { throw new IllegalArgumentException("Object at " + i + " not recognised as one allowed by this class");
/*       */       }
/*       */     }
/*   336 */     int testType = -1;
/*   337 */     for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 18) testType = 0;
/*   338 */     for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 14) testType = 1;
/*   339 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 15) testType = 2;
/*   340 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 12) testType = 3;
/*   341 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 13) testType = 4;
/*   342 */     if (testType == 4) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] <= 3) testType = 3;
/*   343 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] <= 3) testType = 5;
/*   344 */     if (testType == -1) for (int i = 0; i < this.length; i++) if ((this.originalTypes[i] > 3) && (this.originalTypes[i] < 12)) testType = 6;
/*   345 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 17) testType = 7;
/*   346 */     if (testType == -1) throw new IllegalArgumentException("It should not be possible to reach this exception - main Object type not identified");
/*   347 */     switch (testType) {
/*   348 */     case 0:  this.type = 18;
/*   349 */       for (int i = 0; i < this.length; i++) {
/*   350 */         switch (this.originalTypes[i]) {
/*   351 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   352 */           this.array.add(hold1.toString());
/*   353 */           break;
/*   354 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   355 */           this.array.add(hold3.toString());
/*   356 */           break;
/*   357 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   358 */           this.array.add(hold5.toString());
/*   359 */           break;
/*   360 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   361 */           this.array.add(hold7.toString());
/*   362 */           break;
/*   363 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   364 */           this.array.add(hold9.toString());
/*   365 */           break;
/*   366 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   367 */           this.array.add(hold11.toString());
/*   368 */           break;
/*   369 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayv.elementAt(i);
/*   370 */           this.array.add(hold12.toString());
/*   371 */           break;
/*   372 */         case 13:  BigInteger hold13 = (BigInteger)arrayv.elementAt(i);
/*   373 */           this.array.add(hold13.toString());
/*   374 */           break;
/*   375 */         case 14:  Complex hold14 = (Complex)arrayv.elementAt(i);
/*   376 */           this.array.add(hold14.toString());
/*   377 */           break;
/*   378 */         case 15:  Phasor hold15 = (Phasor)arrayv.elementAt(i);
/*   379 */           this.array.add(hold15.toString());
/*   380 */           break;
/*   381 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   382 */           this.array.add(hold17.toString());
/*   383 */           break;
/*   384 */         case 18:  String hold18 = (String)arrayv.elementAt(i);
/*   385 */           this.array.add(hold18);
/*   386 */           break;
/*   387 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   390 */       break;
/*   391 */     case 1:  this.type = 14;
/*   392 */       for (int i = 0; i < this.length; i++) {
/*   393 */         switch (this.originalTypes[i]) {
/*   394 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   395 */           this.array.add(new Complex(hold1.doubleValue()));
/*   396 */           break;
/*   397 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   398 */           this.array.add(new Complex(hold3.doubleValue()));
/*   399 */           break;
/*   400 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   401 */           this.array.add(new Complex(hold5.doubleValue()));
/*   402 */           break;
/*   403 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   404 */           this.array.add(new Complex(hold7.doubleValue()));
/*   405 */           break;
/*   406 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   407 */           this.array.add(new Complex(hold9.doubleValue()));
/*   408 */           break;
/*   409 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   410 */           this.array.add(new Complex(hold11.doubleValue()));
/*   411 */           break;
/*   412 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayv.elementAt(i);
/*   413 */           this.array.add(new Complex(hold12.doubleValue()));
/*   414 */           break;
/*   415 */         case 13:  BigInteger hold13 = (BigInteger)arrayv.elementAt(i);
/*   416 */           this.array.add(new Complex(hold13.doubleValue()));
/*   417 */           break;
/*   418 */         case 14:  Complex hold14 = (Complex)arrayv.elementAt(i);
/*   419 */           this.array.add(hold14);
/*   420 */           break;
/*   421 */         case 15:  Phasor hold15 = (Phasor)arrayv.elementAt(i);
/*   422 */           this.array.add(Conv.convert_Phasor_to_Complex(hold15));
/*   423 */           break;
/*   424 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   425 */           this.array.add(new Complex(hold17.charValue()));
/*   426 */           break;
/*   427 */         case 18:  String hold18 = (String)arrayv.elementAt(i);
/*   428 */           this.array.add(new Complex(Double.parseDouble(hold18)));
/*   429 */           break;
/*   430 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   433 */       break;
/*   434 */     case 2:  this.type = 15;
/*   435 */       for (int i = 0; i < this.length; i++) {
/*   436 */         switch (this.originalTypes[i]) {
/*   437 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   438 */           this.array.add(new Phasor(hold1.doubleValue()));
/*   439 */           break;
/*   440 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   441 */           this.array.add(new Phasor(hold3.doubleValue()));
/*   442 */           break;
/*   443 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   444 */           this.array.add(new Phasor(hold5.doubleValue()));
/*   445 */           break;
/*   446 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   447 */           this.array.add(new Phasor(hold7.doubleValue()));
/*   448 */           break;
/*   449 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   450 */           this.array.add(new Phasor(hold9.doubleValue()));
/*   451 */           break;
/*   452 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   453 */           this.array.add(new Phasor(hold11.doubleValue()));
/*   454 */           break;
/*   455 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayv.elementAt(i);
/*   456 */           this.array.add(new Phasor(hold12.doubleValue()));
/*   457 */           break;
/*   458 */         case 13:  BigInteger hold13 = (BigInteger)arrayv.elementAt(i);
/*   459 */           this.array.add(new Phasor(hold13.doubleValue()));
/*   460 */           break;
/*   461 */         case 14:  Complex hold14 = (Complex)arrayv.elementAt(i);
/*   462 */           this.array.add(Conv.convert_Complex_to_Phasor(hold14));
/*   463 */           break;
/*   464 */         case 15:  Phasor hold15 = (Phasor)arrayv.elementAt(i);
/*   465 */           this.array.add(hold15);
/*   466 */           break;
/*   467 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   468 */           this.array.add(new Phasor(hold17.charValue()));
/*   469 */           break;
/*   470 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   473 */       break;
/*   474 */     case 3:  this.type = 12;
/*   475 */       for (int i = 0; i < this.length; i++) {
/*   476 */         switch (this.originalTypes[i]) {
/*   477 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   478 */           this.array.add(Conv.convert_Double_to_BigDecimal(hold1));
/*   479 */           break;
/*   480 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   481 */           this.array.add(Conv.convert_Float_to_BigDecimal(hold3));
/*   482 */           break;
/*   483 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   484 */           this.array.add(Conv.convert_Long_to_BigDecimal(hold5));
/*   485 */           break;
/*   486 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   487 */           this.array.add(Conv.convert_Integer_to_BigDecimal(hold7));
/*   488 */           break;
/*   489 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   490 */           this.array.add(Conv.convert_Short_to_BigDecimal(hold9));
/*   491 */           break;
/*   492 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   493 */           this.array.add(Conv.convert_Byte_to_BigDecimal(hold11));
/*   494 */           break;
/*   495 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayv.elementAt(i);
/*   496 */           this.array.add(hold12);
/*   497 */           break;
/*   498 */         case 13:  BigInteger hold13 = (BigInteger)arrayv.elementAt(i);
/*   499 */           this.array.add(Conv.convert_BigInteger_to_BigDecimal(hold13));
/*   500 */           break;
/*   501 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   502 */           this.array.add(new BigDecimal(hold17.toString()));
/*   503 */           break;
/*   504 */         case 2: case 4: case 6: case 8: case 10: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   507 */       break;
/*   508 */     case 4:  this.type = 13;
/*   509 */       for (int i = 0; i < this.length; i++) {
/*   510 */         switch (this.originalTypes[i]) {
/*   511 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   512 */           this.array.add(Conv.convert_Double_to_BigInteger(hold1));
/*   513 */           break;
/*   514 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   515 */           this.array.add(Conv.convert_Float_to_BigInteger(hold3));
/*   516 */           break;
/*   517 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   518 */           this.array.add(Conv.convert_Long_to_BigInteger(hold5));
/*   519 */           break;
/*   520 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   521 */           this.array.add(Conv.convert_Integer_to_BigInteger(hold7));
/*   522 */           break;
/*   523 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   524 */           this.array.add(Conv.convert_Short_to_BigInteger(hold9));
/*   525 */           break;
/*   526 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   527 */           this.array.add(Conv.convert_Byte_to_BigInteger(hold11));
/*   528 */           break;
/*   529 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayv.elementAt(i);
/*   530 */           this.array.add(Conv.convert_BigDecimal_to_BigInteger(hold12));
/*   531 */           break;
/*   532 */         case 13:  BigInteger hold13 = (BigInteger)arrayv.elementAt(i);
/*   533 */           this.array.add(hold13);
/*   534 */           break;
/*   535 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   536 */           this.array.add(new BigInteger(hold17.toString()));
/*   537 */           break;
/*   538 */         case 2: case 4: case 6: case 8: case 10: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   541 */       break;
/*   542 */     case 5:  this.type = 1;
/*   543 */       for (int i = 0; i < this.length; i++) {
/*   544 */         switch (this.originalTypes[i]) {
/*   545 */         case 1:  Double hold1 = (Double)arrayv.elementAt(i);
/*   546 */           this.array.add(hold1);
/*   547 */           break;
/*   548 */         case 3:  Float hold3 = (Float)arrayv.elementAt(i);
/*   549 */           this.array.add(Conv.convert_Float_to_Double(hold3));
/*   550 */           break;
/*   551 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   552 */           this.array.add(Conv.convert_Long_to_Double(hold5));
/*   553 */           break;
/*   554 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   555 */           this.array.add(Conv.convert_Integer_to_Double(hold7));
/*   556 */           break;
/*   557 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   558 */           this.array.add(Conv.convert_Short_to_Double(hold9));
/*   559 */           break;
/*   560 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   561 */           this.array.add(Conv.convert_Byte_to_Double(hold11));
/*   562 */           break;
/*   563 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   564 */           this.array.add(new Double(Double.parseDouble(hold17.toString())));
/*   565 */           break;
/*   566 */         case 2: case 4: case 6: case 8: case 10: case 12: case 13: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   569 */       break;
/*   570 */     case 6:  this.type = 7;
/*   571 */       for (int i = 0; i < this.length; i++) {
/*   572 */         switch (this.originalTypes[i]) {
/*   573 */         case 5:  Long hold5 = (Long)arrayv.elementAt(i);
/*   574 */           this.array.add(hold5);
/*   575 */           break;
/*   576 */         case 7:  Integer hold7 = (Integer)arrayv.elementAt(i);
/*   577 */           this.array.add(Conv.convert_Integer_to_Long(hold7));
/*   578 */           break;
/*   579 */         case 9:  Short hold9 = (Short)arrayv.elementAt(i);
/*   580 */           this.array.add(Conv.convert_Short_to_Long(hold9));
/*   581 */           break;
/*   582 */         case 11:  Byte hold11 = (Byte)arrayv.elementAt(i);
/*   583 */           this.array.add(Conv.convert_Byte_to_Long(hold11));
/*   584 */           break;
/*   585 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   586 */           this.array.add(new Long(hold17.charValue()));
/*   587 */           break;
/*   588 */         case 6: case 8: case 10: case 12: case 13: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   591 */       break;
/*   592 */     case 7:  this.type = 7;
/*   593 */       for (int i = 0; i < this.length; i++) {
/*   594 */         switch (this.originalTypes[i]) {
/*   595 */         case 17:  Character hold17 = (Character)arrayv.elementAt(i);
/*   596 */           this.array.add(hold17);
/*   597 */           break;
/*   598 */         default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   601 */       break;
/*   602 */     default:  throw new IllegalArgumentException("Dominant array data type not identified by this method");
/*       */     }
/*       */     
/*   605 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*   620 */       minmax();
/*       */     }
/*       */   }
/*       */   
/*       */   public ArrayMaths(ArrayList<Object> arrayl)
/*       */   {
/*   626 */     this.length = arrayl.size();
/*   627 */     this.originalTypes = new int[this.length];
/*   628 */     this.array = new ArrayList(this.length);
/*       */     
/*   630 */     for (int i = 0; i < this.length; i++) {
/*   631 */       this.originalTypes[i] = -1;
/*   632 */       if ((arrayl.get(i) instanceof Double)) this.originalTypes[i] = 1;
/*   633 */       if ((arrayl.get(i) instanceof Float)) this.originalTypes[i] = 3;
/*   634 */       if ((arrayl.get(i) instanceof Long)) this.originalTypes[i] = 5;
/*   635 */       if ((arrayl.get(i) instanceof Integer)) this.originalTypes[i] = 7;
/*   636 */       if ((arrayl.get(i) instanceof Short)) this.originalTypes[i] = 9;
/*   637 */       if ((arrayl.get(i) instanceof Byte)) this.originalTypes[i] = 11;
/*   638 */       if ((arrayl.get(i) instanceof BigDecimal)) this.originalTypes[i] = 12;
/*   639 */       if ((arrayl.get(i) instanceof BigInteger)) this.originalTypes[i] = 13;
/*   640 */       if ((arrayl.get(i) instanceof Complex)) this.originalTypes[i] = 14;
/*   641 */       if ((arrayl.get(i) instanceof Phasor)) this.originalTypes[i] = 15;
/*   642 */       if ((arrayl.get(i) instanceof Character)) this.originalTypes[i] = 17;
/*   643 */       if ((arrayl.get(i) instanceof String)) this.originalTypes[i] = 18;
/*   644 */       if (this.originalTypes[i] == -1) { throw new IllegalArgumentException("Object at " + i + " not recognised as one allowed by this class");
/*       */       }
/*       */     }
/*   647 */     int testType = -1;
/*   648 */     for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 18) testType = 0;
/*   649 */     for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 14) testType = 1;
/*   650 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 15) testType = 2;
/*   651 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 12) testType = 3;
/*   652 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 13) testType = 4;
/*   653 */     if (testType == 4) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] <= 3) testType = 3;
/*   654 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] <= 3) testType = 5;
/*   655 */     if (testType == -1) for (int i = 0; i < this.length; i++) if ((this.originalTypes[i] > 3) && (this.originalTypes[i] < 12)) testType = 6;
/*   656 */     if (testType == -1) for (int i = 0; i < this.length; i++) if (this.originalTypes[i] == 17) testType = 7;
/*   657 */     if (testType == -1) throw new IllegalArgumentException("It should not be possible to reach this exception - main Object type not identified");
/*   658 */     switch (testType) {
/*   659 */     case 0:  this.type = 18;
/*   660 */       for (int i = 0; i < this.length; i++) {
/*   661 */         switch (this.originalTypes[i]) {
/*   662 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   663 */           this.array.add(hold1.toString());
/*   664 */           break;
/*   665 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   666 */           this.array.add(hold3.toString());
/*   667 */           break;
/*   668 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   669 */           this.array.add(hold5.toString());
/*   670 */           break;
/*   671 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   672 */           this.array.add(hold7.toString());
/*   673 */           break;
/*   674 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   675 */           this.array.add(hold9.toString());
/*   676 */           break;
/*   677 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   678 */           this.array.add(hold11.toString());
/*   679 */           break;
/*   680 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayl.get(i);
/*   681 */           this.array.add(hold12.toString());
/*   682 */           break;
/*   683 */         case 13:  BigInteger hold13 = (BigInteger)arrayl.get(i);
/*   684 */           this.array.add(hold13.toString());
/*   685 */           break;
/*   686 */         case 14:  Complex hold14 = (Complex)arrayl.get(i);
/*   687 */           this.array.add(hold14.toString());
/*   688 */           break;
/*   689 */         case 15:  Phasor hold15 = (Phasor)arrayl.get(i);
/*   690 */           this.array.add(hold15.toString());
/*   691 */           break;
/*   692 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   693 */           this.array.add(hold17.toString());
/*   694 */           break;
/*   695 */         case 18:  String hold18 = (String)arrayl.get(i);
/*   696 */           this.array.add(hold18);
/*   697 */           break;
/*   698 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   701 */       break;
/*   702 */     case 1:  this.type = 14;
/*   703 */       for (int i = 0; i < this.length; i++) {
/*   704 */         switch (this.originalTypes[i]) {
/*   705 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   706 */           this.array.add(new Complex(hold1.doubleValue()));
/*   707 */           break;
/*   708 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   709 */           this.array.add(new Complex(hold3.doubleValue()));
/*   710 */           break;
/*   711 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   712 */           this.array.add(new Complex(hold5.doubleValue()));
/*   713 */           break;
/*   714 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   715 */           this.array.add(new Complex(hold7.doubleValue()));
/*   716 */           break;
/*   717 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   718 */           this.array.add(new Complex(hold9.doubleValue()));
/*   719 */           break;
/*   720 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   721 */           this.array.add(new Complex(hold11.doubleValue()));
/*   722 */           break;
/*   723 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayl.get(i);
/*   724 */           this.array.add(new Complex(hold12.doubleValue()));
/*   725 */           break;
/*   726 */         case 13:  BigInteger hold13 = (BigInteger)arrayl.get(i);
/*   727 */           this.array.add(new Complex(hold13.doubleValue()));
/*   728 */           break;
/*   729 */         case 14:  Complex hold14 = (Complex)arrayl.get(i);
/*   730 */           this.array.add(hold14);
/*   731 */           break;
/*   732 */         case 15:  Phasor hold15 = (Phasor)arrayl.get(i);
/*   733 */           this.array.add(Conv.convert_Phasor_to_Complex(hold15));
/*   734 */           break;
/*   735 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   736 */           this.array.add(new Complex(hold17.charValue()));
/*   737 */           break;
/*   738 */         case 18:  String hold18 = (String)arrayl.get(i);
/*   739 */           this.array.add(new Complex(Double.parseDouble(hold18)));
/*   740 */           break;
/*   741 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   744 */       break;
/*   745 */     case 2:  this.type = 15;
/*   746 */       for (int i = 0; i < this.length; i++) {
/*   747 */         switch (this.originalTypes[i]) {
/*   748 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   749 */           this.array.add(new Phasor(hold1.doubleValue()));
/*   750 */           break;
/*   751 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   752 */           this.array.add(new Phasor(hold3.doubleValue()));
/*   753 */           break;
/*   754 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   755 */           this.array.add(new Phasor(hold5.doubleValue()));
/*   756 */           break;
/*   757 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   758 */           this.array.add(new Phasor(hold7.doubleValue()));
/*   759 */           break;
/*   760 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   761 */           this.array.add(new Phasor(hold9.doubleValue()));
/*   762 */           break;
/*   763 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   764 */           this.array.add(new Phasor(hold11.doubleValue()));
/*   765 */           break;
/*   766 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayl.get(i);
/*   767 */           this.array.add(new Phasor(hold12.doubleValue()));
/*   768 */           break;
/*   769 */         case 13:  BigInteger hold13 = (BigInteger)arrayl.get(i);
/*   770 */           this.array.add(new Phasor(hold13.doubleValue()));
/*   771 */           break;
/*   772 */         case 14:  Complex hold14 = (Complex)arrayl.get(i);
/*   773 */           this.array.add(Conv.convert_Complex_to_Phasor(hold14));
/*   774 */           break;
/*   775 */         case 15:  Phasor hold15 = (Phasor)arrayl.get(i);
/*   776 */           this.array.add(hold15);
/*   777 */           break;
/*   778 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   779 */           this.array.add(new Phasor(hold17.charValue()));
/*   780 */           break;
/*   781 */         case 2: case 4: case 6: case 8: case 10: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   784 */       break;
/*   785 */     case 3:  this.type = 12;
/*   786 */       for (int i = 0; i < this.length; i++) {
/*   787 */         switch (this.originalTypes[i]) {
/*   788 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   789 */           this.array.add(Conv.convert_Double_to_BigDecimal(hold1));
/*   790 */           break;
/*   791 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   792 */           this.array.add(Conv.convert_Float_to_BigDecimal(hold3));
/*   793 */           break;
/*   794 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   795 */           this.array.add(Conv.convert_Long_to_BigDecimal(hold5));
/*   796 */           break;
/*   797 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   798 */           this.array.add(Conv.convert_Integer_to_BigDecimal(hold7));
/*   799 */           break;
/*   800 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   801 */           this.array.add(Conv.convert_Short_to_BigDecimal(hold9));
/*   802 */           break;
/*   803 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   804 */           this.array.add(Conv.convert_Byte_to_BigDecimal(hold11));
/*   805 */           break;
/*   806 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayl.get(i);
/*   807 */           this.array.add(hold12);
/*   808 */           break;
/*   809 */         case 13:  BigInteger hold13 = (BigInteger)arrayl.get(i);
/*   810 */           this.array.add(Conv.convert_BigInteger_to_BigDecimal(hold13));
/*   811 */           break;
/*   812 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   813 */           this.array.add(new BigDecimal(hold17.toString()));
/*   814 */           break;
/*   815 */         case 2: case 4: case 6: case 8: case 10: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   818 */       break;
/*   819 */     case 4:  this.type = 13;
/*   820 */       for (int i = 0; i < this.length; i++) {
/*   821 */         switch (this.originalTypes[i]) {
/*   822 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   823 */           this.array.add(Conv.convert_Double_to_BigInteger(hold1));
/*   824 */           break;
/*   825 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   826 */           this.array.add(Conv.convert_Float_to_BigInteger(hold3));
/*   827 */           break;
/*   828 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   829 */           this.array.add(Conv.convert_Long_to_BigInteger(hold5));
/*   830 */           break;
/*   831 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   832 */           this.array.add(Conv.convert_Integer_to_BigInteger(hold7));
/*   833 */           break;
/*   834 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   835 */           this.array.add(Conv.convert_Short_to_BigInteger(hold9));
/*   836 */           break;
/*   837 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   838 */           this.array.add(Conv.convert_Byte_to_BigInteger(hold11));
/*   839 */           break;
/*   840 */         case 12:  BigDecimal hold12 = (BigDecimal)arrayl.get(i);
/*   841 */           this.array.add(Conv.convert_BigDecimal_to_BigInteger(hold12));
/*   842 */           break;
/*   843 */         case 13:  BigInteger hold13 = (BigInteger)arrayl.get(i);
/*   844 */           this.array.add(hold13);
/*   845 */           break;
/*   846 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   847 */           this.array.add(new BigInteger(hold17.toString()));
/*   848 */           break;
/*   849 */         case 2: case 4: case 6: case 8: case 10: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   852 */       break;
/*   853 */     case 5:  this.type = 1;
/*   854 */       for (int i = 0; i < this.length; i++) {
/*   855 */         switch (this.originalTypes[i]) {
/*   856 */         case 1:  Double hold1 = (Double)arrayl.get(i);
/*   857 */           this.array.add(hold1);
/*   858 */           break;
/*   859 */         case 3:  Float hold3 = (Float)arrayl.get(i);
/*   860 */           this.array.add(Conv.convert_Float_to_Double(hold3));
/*   861 */           break;
/*   862 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   863 */           this.array.add(Conv.convert_Long_to_Double(hold5));
/*   864 */           break;
/*   865 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   866 */           this.array.add(Conv.convert_Integer_to_Double(hold7));
/*   867 */           break;
/*   868 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   869 */           this.array.add(Conv.convert_Short_to_Double(hold9));
/*   870 */           break;
/*   871 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   872 */           this.array.add(Conv.convert_Byte_to_Double(hold11));
/*   873 */           break;
/*   874 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   875 */           this.array.add(new Double(Double.parseDouble(hold17.toString())));
/*   876 */           break;
/*   877 */         case 2: case 4: case 6: case 8: case 10: case 12: case 13: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   880 */       break;
/*   881 */     case 6:  this.type = 7;
/*   882 */       for (int i = 0; i < this.length; i++) {
/*   883 */         switch (this.originalTypes[i]) {
/*   884 */         case 5:  Long hold5 = (Long)arrayl.get(i);
/*   885 */           this.array.add(hold5);
/*   886 */           break;
/*   887 */         case 7:  Integer hold7 = (Integer)arrayl.get(i);
/*   888 */           this.array.add(Conv.convert_Integer_to_Long(hold7));
/*   889 */           break;
/*   890 */         case 9:  Short hold9 = (Short)arrayl.get(i);
/*   891 */           this.array.add(Conv.convert_Short_to_Long(hold9));
/*   892 */           break;
/*   893 */         case 11:  Byte hold11 = (Byte)arrayl.get(i);
/*   894 */           this.array.add(Conv.convert_Byte_to_Long(hold11));
/*   895 */           break;
/*   896 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   897 */           this.array.add(new Long(hold17.charValue()));
/*   898 */           break;
/*   899 */         case 6: case 8: case 10: case 12: case 13: case 14: case 15: case 16: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   902 */       break;
/*   903 */     case 7:  this.type = 7;
/*   904 */       for (int i = 0; i < this.length; i++) {
/*   905 */         switch (this.originalTypes[i]) {
/*   906 */         case 17:  Character hold17 = (Character)arrayl.get(i);
/*   907 */           this.array.add(hold17);
/*   908 */           break;
/*   909 */         default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */         }
/*       */       }
/*   912 */       break;
/*   913 */     default:  throw new IllegalArgumentException("Dominant array data type not identified by this method");
/*       */     }
/*       */     
/*   916 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*   931 */       minmax();
/*       */     }
/*       */     
/*       */   }
/*       */   
/*       */   public int length()
/*       */   {
/*   938 */     return this.length;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int typeIndex()
/*       */   {
/*   947 */     return this.type;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public String arrayType()
/*       */   {
/*   954 */     return this.typeName[this.type] + "[]";
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public String[] originalArrayTypes()
/*       */   {
/*   961 */     String[] ss = new String[this.length];
/*   962 */     for (int i = 0; i < this.length; i++) ss[i] = this.typeName[this.originalTypes[i]];
/*   963 */     return ss;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths copy()
/*       */   {
/*   969 */     ArrayMaths am = new ArrayMaths();
/*       */     
/*   971 */     am.length = this.length;
/*   972 */     am.maxIndex = this.maxIndex;
/*   973 */     am.minIndex = this.minIndex;
/*   974 */     am.sumDone = this.sumDone;
/*   975 */     am.productDone = this.productDone;
/*   976 */     am.sumlongToDouble = this.sumlongToDouble;
/*   977 */     am.productlongToDouble = this.productlongToDouble;
/*   978 */     am.type = this.type;
/*   979 */     if (this.originalTypes == null) {
/*   980 */       am.originalTypes = null;
/*       */     }
/*       */     else {
/*   983 */       am.originalTypes = ((int[])this.originalTypes.clone());
/*       */     }
/*   985 */     if (this.sortedIndices == null) {
/*   986 */       am.sortedIndices = null;
/*       */     }
/*       */     else {
/*   989 */       am.sortedIndices = ((int[])this.sortedIndices.clone());
/*       */     }
/*   991 */     am.suppressMessages = this.suppressMessages;
/*   992 */     am.minmax = new ArrayList();
/*   993 */     if (this.minmax.size() != 0) { short ss;
/*   994 */       switch (this.type) {
/*       */       case 0: case 1: 
/*   996 */         double dd = ((Double)this.minmax.get(0)).doubleValue();
/*   997 */         am.minmax.add(new Double(dd));
/*   998 */         dd = ((Double)this.minmax.get(1)).doubleValue();
/*   999 */         am.minmax.add(new Double(dd));
/*  1000 */         break;
/*       */       case 4: case 5: 
/*  1002 */         long ll = ((Long)this.minmax.get(0)).longValue();
/*  1003 */         am.minmax.add(new Double(ll));
/*  1004 */         ll = ((Long)this.minmax.get(1)).longValue();
/*  1005 */         am.minmax.add(new Long(ll));
/*  1006 */         break;
/*       */       case 2: case 3: 
/*  1008 */         float ff = ((Float)this.minmax.get(0)).floatValue();
/*  1009 */         am.minmax.add(new Double(ff));
/*  1010 */         ff = ((Float)this.minmax.get(1)).floatValue();
/*  1011 */         am.minmax.add(new Double(ff));
/*  1012 */         break;
/*       */       case 6: case 7: 
/*  1014 */         int ii = ((Integer)this.minmax.get(0)).intValue();
/*  1015 */         am.minmax.add(new Integer(ii));
/*  1016 */         ii = ((Double)this.minmax.get(1)).intValue();
/*  1017 */         am.minmax.add(new Integer(ii));
/*  1018 */         break;
/*       */       case 8: case 9: 
/*  1020 */         ss = ((Short)this.minmax.get(0)).shortValue();
/*  1021 */         am.minmax.add(new Short(ss));
/*  1022 */         ss = ((Double)this.minmax.get(1)).shortValue();
/*  1023 */         am.minmax.add(new Short(ss));
/*  1024 */         break;
/*       */       case 10: case 11: 
/*  1026 */         byte bb = ((Byte)this.minmax.get(0)).byteValue();
/*  1027 */         am.minmax.add(new Byte(bb));
/*  1028 */         ss = (short)((Byte)this.minmax.get(1)).byteValue();
/*  1029 */         am.minmax.add(new Byte(bb));
/*  1030 */         break;
/*  1031 */       case 12:  BigDecimal bd = (BigDecimal)this.minmax.get(0);
/*  1032 */         am.minmax.add(bd);
/*  1033 */         bd = (BigDecimal)this.minmax.get(1);
/*  1034 */         am.minmax.add(bd);
/*  1035 */         bd = null;
/*  1036 */         break;
/*  1037 */       case 13:  BigInteger bi = (BigInteger)this.minmax.get(0);
/*  1038 */         am.minmax.add(bi);
/*  1039 */         bi = (BigInteger)this.minmax.get(1);
/*  1040 */         am.minmax.add(bi);
/*  1041 */         bi = null;
/*  1042 */         break;
/*       */       case 16: case 17: 
/*  1044 */         int iii = ((Integer)this.minmax.get(0)).intValue();
/*  1045 */         am.minmax.add(new Integer(iii));
/*  1046 */         iii = ((Double)this.minmax.get(1)).intValue();
/*  1047 */         am.minmax.add(new Integer(iii));
/*       */       }
/*       */       
/*       */     }
/*       */     
/*  1052 */     am.summ = new ArrayList();
/*  1053 */     if (this.summ.size() != 0) {
/*  1054 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/*  1059 */         double dd = ((Double)this.summ.get(0)).doubleValue();
/*  1060 */         am.summ.add(new Double(dd));
/*  1061 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/*  1071 */         if (this.sumlongToDouble) {
/*  1072 */           double dd2 = ((Double)this.summ.get(0)).doubleValue();
/*  1073 */           am.summ.add(new Double(dd2));
/*       */         }
/*       */         else {
/*  1076 */           long ll = ((Long)this.summ.get(0)).longValue();
/*  1077 */           am.summ.add(new Long(ll));
/*       */         }
/*  1079 */         break;
/*  1080 */       case 12:  BigDecimal bd = (BigDecimal)this.summ.get(0);
/*  1081 */         am.summ.add(bd);
/*  1082 */         break;
/*  1083 */       case 13:  BigInteger bi = (BigInteger)this.summ.get(0);
/*  1084 */         am.summ.add(bi);
/*  1085 */         break;
/*  1086 */       case 14:  Complex cc = (Complex)this.summ.get(0);
/*  1087 */         am.summ.add(cc);
/*  1088 */         break;
/*  1089 */       case 15:  Phasor pp = (Phasor)this.summ.get(0);
/*  1090 */         am.summ.add(pp);
/*  1091 */         break;
/*  1092 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/*  1096 */     am.productt = new ArrayList();
/*  1097 */     if (this.productt.size() != 0) {
/*  1098 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/*  1103 */         double dd = ((Double)this.productt.get(0)).doubleValue();
/*  1104 */         am.productt.add(new Double(dd));
/*  1105 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/*  1115 */         if (this.sumlongToDouble) {
/*  1116 */           double dd2 = ((Double)this.productt.get(0)).doubleValue();
/*  1117 */           am.productt.add(new Double(dd2));
/*       */         }
/*       */         else {
/*  1120 */           long ll = ((Long)this.productt.get(0)).longValue();
/*  1121 */           am.productt.add(new Long(ll));
/*       */         }
/*  1123 */         break;
/*  1124 */       case 12:  BigDecimal bd = (BigDecimal)this.productt.get(0);
/*  1125 */         am.productt.add(bd);
/*  1126 */         break;
/*  1127 */       case 13:  BigInteger bi = (BigInteger)this.productt.get(0);
/*  1128 */         am.productt.add(bi);
/*  1129 */         break;
/*  1130 */       case 14:  Complex cc = (Complex)this.productt.get(0);
/*  1131 */         am.productt.add(cc);
/*  1132 */         break;
/*  1133 */       case 15:  Phasor pp = (Phasor)this.productt.get(0);
/*  1134 */         am.productt.add(pp);
/*  1135 */         break;
/*  1136 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/*       */     
/*  1141 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1143 */       double[] dd = (double[])getArray_as_double().clone();
/*  1144 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i]));
/*  1145 */       break;
/*       */     case 2: case 3: 
/*  1147 */       float[] ff = (float[])getArray_as_float().clone();
/*  1148 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(ff[i]));
/*  1149 */       break;
/*       */     case 4: case 5: 
/*  1151 */       long[] ll = (long[])getArray_as_long().clone();
/*  1152 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  1153 */       break;
/*       */     case 6: case 7: 
/*  1155 */       int[] ii = (int[])getArray_as_int().clone();
/*  1156 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(ii[i]));
/*  1157 */       break;
/*       */     case 8: case 9: 
/*  1159 */       short[] ss = (short[])getArray_as_short().clone();
/*  1160 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  1161 */       break;
/*       */     case 10: case 11: 
/*  1163 */       byte[] bb = (byte[])getArray_as_byte().clone();
/*  1164 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  1165 */       break;
/*  1166 */     case 12:  BigDecimal[] bd = (BigDecimal[])getArray_as_BigDecimal().clone();
/*  1167 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i]);
/*  1168 */       break;
/*  1169 */     case 13:  BigInteger[] bi = (BigInteger[])getArray_as_BigInteger().clone();
/*  1170 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  1171 */       break;
/*  1172 */     case 14:  Complex[] ccc = getArray_as_Complex();
/*  1173 */       for (int i = 0; i < this.length; i++) am.array.add(ccc[i].copy());
/*  1174 */       break;
/*  1175 */     case 15:  Phasor[] ppp = getArray_as_Phasor();
/*  1176 */       for (int i = 0; i < this.length; i++) am.array.add(ppp[i].copy());
/*  1177 */       break;
/*       */     case 16: case 17: 
/*  1179 */       char[] cc = (char[])getArray_as_char().clone();
/*  1180 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(cc[i]));
/*  1181 */       break;
/*  1182 */     case 18:  String[] sss = (String[])getArray_as_String().clone();
/*  1183 */       for (int i = 0; i < this.length; i++) { am.array.add(sss[i]);
/*       */       }
/*       */     }
/*       */     
/*  1187 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public Stat statCopy()
/*       */   {
/*  1193 */     Stat am = new Stat();
/*  1194 */     am.length = this.length;
/*  1195 */     am.maxIndex = this.maxIndex;
/*  1196 */     am.minIndex = this.minIndex;
/*  1197 */     am.sumDone = this.sumDone;
/*  1198 */     am.productDone = this.productDone;
/*  1199 */     am.sumlongToDouble = this.sumlongToDouble;
/*  1200 */     am.productlongToDouble = this.productlongToDouble;
/*  1201 */     am.type = this.type;
/*  1202 */     if (this.originalTypes == null) {
/*  1203 */       am.originalTypes = null;
/*       */     }
/*       */     else {
/*  1206 */       am.originalTypes = ((int[])this.originalTypes.clone());
/*       */     }
/*  1208 */     if (this.sortedIndices == null) {
/*  1209 */       am.sortedIndices = null;
/*       */     }
/*       */     else {
/*  1212 */       am.sortedIndices = ((int[])this.sortedIndices.clone());
/*       */     }
/*  1214 */     am.suppressMessages = this.suppressMessages;
/*  1215 */     am.minmax = new ArrayList();
/*  1216 */     if (this.minmax.size() != 0) { short ss;
/*  1217 */       switch (this.type) {
/*       */       case 0: case 1: 
/*  1219 */         double dd = ((Double)this.minmax.get(0)).doubleValue();
/*  1220 */         am.minmax.add(new Double(dd));
/*  1221 */         dd = ((Double)this.minmax.get(1)).doubleValue();
/*  1222 */         am.minmax.add(new Double(dd));
/*  1223 */         break;
/*       */       case 4: case 5: 
/*  1225 */         long ll = ((Long)this.minmax.get(0)).longValue();
/*  1226 */         am.minmax.add(new Double(ll));
/*  1227 */         ll = ((Long)this.minmax.get(1)).longValue();
/*  1228 */         am.minmax.add(new Long(ll));
/*  1229 */         break;
/*       */       case 2: case 3: 
/*  1231 */         float ff = ((Float)this.minmax.get(0)).floatValue();
/*  1232 */         am.minmax.add(new Double(ff));
/*  1233 */         ff = ((Float)this.minmax.get(1)).floatValue();
/*  1234 */         am.minmax.add(new Double(ff));
/*  1235 */         break;
/*       */       case 6: case 7: 
/*  1237 */         int ii = ((Integer)this.minmax.get(0)).intValue();
/*  1238 */         am.minmax.add(new Integer(ii));
/*  1239 */         ii = ((Double)this.minmax.get(1)).intValue();
/*  1240 */         am.minmax.add(new Integer(ii));
/*  1241 */         break;
/*       */       case 8: case 9: 
/*  1243 */         ss = ((Short)this.minmax.get(0)).shortValue();
/*  1244 */         am.minmax.add(new Short(ss));
/*  1245 */         ss = ((Double)this.minmax.get(1)).shortValue();
/*  1246 */         am.minmax.add(new Short(ss));
/*  1247 */         break;
/*       */       case 10: case 11: 
/*  1249 */         byte bb = ((Byte)this.minmax.get(0)).byteValue();
/*  1250 */         am.minmax.add(new Byte(bb));
/*  1251 */         ss = (short)((Byte)this.minmax.get(1)).byteValue();
/*  1252 */         am.minmax.add(new Byte(bb));
/*  1253 */         break;
/*  1254 */       case 12:  BigDecimal bd = (BigDecimal)this.minmax.get(0);
/*  1255 */         am.minmax.add(bd);
/*  1256 */         bd = (BigDecimal)this.minmax.get(1);
/*  1257 */         am.minmax.add(bd);
/*  1258 */         bd = null;
/*  1259 */         break;
/*  1260 */       case 13:  BigInteger bi = (BigInteger)this.minmax.get(0);
/*  1261 */         am.minmax.add(bi);
/*  1262 */         bi = (BigInteger)this.minmax.get(1);
/*  1263 */         am.minmax.add(bi);
/*  1264 */         bi = null;
/*  1265 */         break;
/*       */       case 16: case 17: 
/*  1267 */         int iii = ((Integer)this.minmax.get(0)).intValue();
/*  1268 */         am.minmax.add(new Integer(iii));
/*  1269 */         iii = ((Double)this.minmax.get(1)).intValue();
/*  1270 */         am.minmax.add(new Integer(iii));
/*       */       }
/*       */       
/*       */     }
/*       */     
/*  1275 */     am.summ = new ArrayList();
/*  1276 */     if (this.summ.size() != 0) {
/*  1277 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/*  1282 */         double dd = ((Double)this.summ.get(0)).doubleValue();
/*  1283 */         am.summ.add(new Double(dd));
/*  1284 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/*  1294 */         if (this.sumlongToDouble) {
/*  1295 */           double dd2 = ((Double)this.summ.get(0)).doubleValue();
/*  1296 */           am.summ.add(new Double(dd2));
/*       */         }
/*       */         else {
/*  1299 */           long ll = ((Long)this.summ.get(0)).longValue();
/*  1300 */           am.summ.add(new Long(ll));
/*       */         }
/*  1302 */         break;
/*  1303 */       case 12:  BigDecimal bd = (BigDecimal)this.summ.get(0);
/*  1304 */         am.summ.add(bd);
/*  1305 */         break;
/*  1306 */       case 13:  BigInteger bi = (BigInteger)this.summ.get(0);
/*  1307 */         am.summ.add(bi);
/*  1308 */         break;
/*  1309 */       case 14:  Complex cc = (Complex)this.summ.get(0);
/*  1310 */         am.summ.add(cc);
/*  1311 */         break;
/*  1312 */       case 15:  Phasor pp = (Phasor)this.summ.get(0);
/*  1313 */         am.summ.add(pp);
/*  1314 */         break;
/*  1315 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/*  1319 */     am.productt = new ArrayList();
/*  1320 */     if (this.productt.size() != 0) {
/*  1321 */       switch (this.type) {
/*       */       case 0: case 1: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 18: 
/*  1326 */         double dd = ((Double)this.productt.get(0)).doubleValue();
/*  1327 */         am.productt.add(new Double(dd));
/*  1328 */         break;
/*       */       case 4: case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*       */       case 9: 
/*       */       case 10: 
/*       */       case 11: 
/*       */       case 16: 
/*       */       case 17: 
/*  1338 */         if (this.sumlongToDouble) {
/*  1339 */           double dd2 = ((Double)this.productt.get(0)).doubleValue();
/*  1340 */           am.productt.add(new Double(dd2));
/*       */         }
/*       */         else {
/*  1343 */           long ll = ((Long)this.productt.get(0)).longValue();
/*  1344 */           am.productt.add(new Long(ll));
/*       */         }
/*  1346 */         break;
/*  1347 */       case 12:  BigDecimal bd = (BigDecimal)this.productt.get(0);
/*  1348 */         am.productt.add(bd);
/*  1349 */         break;
/*  1350 */       case 13:  BigInteger bi = (BigInteger)this.productt.get(0);
/*  1351 */         am.productt.add(bi);
/*  1352 */         break;
/*  1353 */       case 14:  Complex cc = (Complex)this.productt.get(0);
/*  1354 */         am.productt.add(cc);
/*  1355 */         break;
/*  1356 */       case 15:  Phasor pp = (Phasor)this.productt.get(0);
/*  1357 */         am.productt.add(pp);
/*  1358 */         break;
/*  1359 */       default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */       }
/*       */       
/*       */     }
/*       */     
/*  1364 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1366 */       double[] dd = (double[])getArray_as_double().clone();
/*  1367 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i]));
/*  1368 */       break;
/*       */     case 2: case 3: 
/*  1370 */       float[] ff = (float[])getArray_as_float().clone();
/*  1371 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(ff[i]));
/*  1372 */       break;
/*       */     case 4: case 5: 
/*  1374 */       long[] ll = (long[])getArray_as_long().clone();
/*  1375 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  1376 */       break;
/*       */     case 6: case 7: 
/*  1378 */       int[] ii = (int[])getArray_as_int().clone();
/*  1379 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(ii[i]));
/*  1380 */       break;
/*       */     case 8: case 9: 
/*  1382 */       short[] ss = (short[])getArray_as_short().clone();
/*  1383 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  1384 */       break;
/*       */     case 10: case 11: 
/*  1386 */       byte[] bb = (byte[])getArray_as_byte().clone();
/*  1387 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  1388 */       break;
/*  1389 */     case 12:  BigDecimal[] bd = (BigDecimal[])getArray_as_BigDecimal().clone();
/*  1390 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i]);
/*  1391 */       break;
/*  1392 */     case 13:  BigInteger[] bi = (BigInteger[])getArray_as_BigInteger().clone();
/*  1393 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  1394 */       break;
/*  1395 */     case 14:  Complex[] ccc = getArray_as_Complex();
/*  1396 */       for (int i = 0; i < this.length; i++) am.array.add(ccc[i].copy());
/*  1397 */       break;
/*  1398 */     case 15:  Phasor[] ppp = getArray_as_Phasor();
/*  1399 */       for (int i = 0; i < this.length; i++) am.array.add(ppp[i].copy());
/*  1400 */       break;
/*       */     case 16: case 17: 
/*  1402 */       char[] cc = (char[])getArray_as_char().clone();
/*  1403 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(cc[i]));
/*  1404 */       break;
/*  1405 */     case 18:  String[] sss = (String[])getArray_as_String().clone();
/*  1406 */       for (int i = 0; i < this.length; i++) { am.array.add(sss[i]);
/*       */       }
/*       */     }
/*       */     
/*  1410 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public void suppressMessages()
/*       */   {
/*  1417 */     this.suppressMessages = true;
/*       */   }
/*       */   
/*       */   public void restoreMessages()
/*       */   {
/*  1422 */     this.suppressMessages = false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double[] array()
/*       */   {
/*  1438 */     return getArray_as_double();
/*       */   }
/*       */   
/*       */   public double[] array_as_double() {
/*  1442 */     return getArray_as_double();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_double() {
/*  1446 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1447 */     double[] retArray = new double[this.length];
/*  1448 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1450 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Double)this.array.get(i)).doubleValue();
/*  1451 */       break;
/*       */     case 2: case 3: 
/*  1453 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_double((Float)this.array.get(i));
/*  1454 */       break;
/*       */     case 4: case 5: 
/*  1456 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_double((Long)this.array.get(i));
/*  1457 */       break;
/*       */     case 6: case 7: 
/*  1459 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_double((Integer)this.array.get(i));
/*  1460 */       break;
/*       */     case 8: case 9: 
/*  1462 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_double((Short)this.array.get(i));
/*  1463 */       break;
/*       */     case 10: case 11: 
/*  1465 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_double((Byte)this.array.get(i));
/*  1466 */       break;
/*  1467 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i));
/*  1468 */       break;
/*  1469 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i));
/*  1470 */       break;
/*  1471 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = Double.valueOf((String)this.array.get(i)).doubleValue();
/*  1472 */       break;
/*       */     case 16: case 17: 
/*  1474 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_double(((Character)this.array.get(i)).charValue());
/*  1475 */       break;
/*       */     case 14: case 15: 
/*  1477 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to double is meaningful/supported");
/*  1478 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1480 */     Conv.restoreMessages();
/*  1481 */     return retArray;
/*       */   }
/*       */   
/*       */   public Double[] array_as_Double()
/*       */   {
/*  1486 */     return getArray_as_Double();
/*       */   }
/*       */   
/*       */   public Double[] getArray_as_Double() {
/*  1490 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1491 */     Double[] retArray = new Double[this.length];
/*  1492 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1494 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Double)this.array.get(i));
/*  1495 */       break;
/*       */     case 2: case 3: 
/*  1497 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_Double((Float)this.array.get(i));
/*  1498 */       break;
/*       */     case 4: case 5: 
/*  1500 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_Double((Long)this.array.get(i));
/*  1501 */       break;
/*       */     case 6: case 7: 
/*  1503 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_Double((Integer)this.array.get(i));
/*  1504 */       break;
/*       */     case 8: case 9: 
/*  1506 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_Double((Short)this.array.get(i));
/*  1507 */       break;
/*       */     case 10: case 11: 
/*  1509 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_Double((Byte)this.array.get(i));
/*  1510 */       break;
/*  1511 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Double((BigDecimal)this.array.get(i));
/*  1512 */       break;
/*  1513 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Double((BigInteger)this.array.get(i));
/*  1514 */       break;
/*  1515 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Double((String)this.array.get(i));
/*  1516 */       break;
/*       */     case 16: case 17: 
/*  1518 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_Double(((Character)this.array.get(i)).charValue());
/*  1519 */       break;
/*       */     case 14: case 15: 
/*  1521 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Double is meaningful/supported");
/*  1522 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1524 */     Conv.restoreMessages();
/*  1525 */     return retArray;
/*       */   }
/*       */   
/*       */   public Float[] array_as_Float()
/*       */   {
/*  1530 */     return getArray_as_Float();
/*       */   }
/*       */   
/*       */   public Float[] getArray_as_Float() {
/*  1534 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1535 */     Float[] retArray = new Float[this.length];
/*  1536 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1538 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_Float((Double)this.array.get(i));
/*  1539 */       break;
/*       */     case 2: case 3: 
/*  1541 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Float)this.array.get(i));
/*  1542 */       break;
/*       */     case 4: case 5: 
/*  1544 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_Float((Long)this.array.get(i));
/*  1545 */       break;
/*       */     case 6: case 7: 
/*  1547 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_Float((Integer)this.array.get(i));
/*  1548 */       break;
/*       */     case 8: case 9: 
/*  1550 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_Float((Short)this.array.get(i));
/*  1551 */       break;
/*       */     case 10: case 11: 
/*  1553 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_Float((Byte)this.array.get(i));
/*  1554 */       break;
/*  1555 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Float((BigDecimal)this.array.get(i));
/*  1556 */       break;
/*  1557 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Float((BigInteger)this.array.get(i));
/*  1558 */       break;
/*  1559 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Float((String)this.array.get(i));
/*  1560 */       break;
/*       */     case 16: case 17: 
/*  1562 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_Float(((Character)this.array.get(i)).charValue());
/*  1563 */       break;
/*       */     case 14: case 15: 
/*  1565 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Float is meaningful/supported");
/*  1566 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1568 */     Conv.restoreMessages();
/*  1569 */     return retArray;
/*       */   }
/*       */   
/*       */   public float[] array_as_float()
/*       */   {
/*  1574 */     return getArray_as_float();
/*       */   }
/*       */   
/*       */   public float[] getArray_as_float() {
/*  1578 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1579 */     float[] retArray = new float[this.length];
/*  1580 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1582 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_float((Double)this.array.get(i));
/*  1583 */       break;
/*       */     case 2: case 3: 
/*  1585 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Float)this.array.get(i)).floatValue();
/*  1586 */       break;
/*       */     case 4: case 5: 
/*  1588 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_float((Long)this.array.get(i));
/*  1589 */       break;
/*       */     case 6: case 7: 
/*  1591 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_float((Integer)this.array.get(i));
/*  1592 */       break;
/*       */     case 8: case 9: 
/*  1594 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_float((Short)this.array.get(i));
/*  1595 */       break;
/*       */     case 10: case 11: 
/*  1597 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_float((Byte)this.array.get(i));
/*  1598 */       break;
/*  1599 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_float((BigDecimal)this.array.get(i));
/*  1600 */       break;
/*  1601 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_float((BigInteger)this.array.get(i));
/*  1602 */       break;
/*  1603 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Float((String)this.array.get(i)).floatValue();
/*  1604 */       break;
/*       */     case 16: case 17: 
/*  1606 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_float(((Character)this.array.get(i)).charValue());
/*  1607 */       break;
/*       */     case 14: case 15: 
/*  1609 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to float is meaningful/supported");
/*  1610 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1612 */     Conv.restoreMessages();
/*  1613 */     return retArray;
/*       */   }
/*       */   
/*       */   public long[] array_as_long()
/*       */   {
/*  1618 */     return getArray_as_long();
/*       */   }
/*       */   
/*       */   public long[] getArray_as_long() {
/*  1622 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1623 */     long[] retArray = new long[this.length];
/*  1624 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1626 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_long((Double)this.array.get(i));
/*  1627 */       break;
/*       */     case 2: case 3: 
/*  1629 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_long((Float)this.array.get(i));
/*  1630 */       break;
/*       */     case 4: case 5: 
/*  1632 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Long)this.array.get(i)).longValue();
/*  1633 */       break;
/*       */     case 6: case 7: 
/*  1635 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_long((Integer)this.array.get(i));
/*  1636 */       break;
/*       */     case 8: case 9: 
/*  1638 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_long((Short)this.array.get(i));
/*  1639 */       break;
/*       */     case 10: case 11: 
/*  1641 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_long((Byte)this.array.get(i));
/*  1642 */       break;
/*  1643 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_long((BigDecimal)this.array.get(i));
/*  1644 */       break;
/*  1645 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_long((BigInteger)this.array.get(i));
/*  1646 */       break;
/*  1647 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Long((String)this.array.get(i)).longValue();
/*  1648 */       break;
/*       */     case 16: case 17: 
/*  1650 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_long(((Character)this.array.get(i)).charValue());
/*  1651 */       break;
/*       */     case 14: case 15: 
/*  1653 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to long is meaningful/supported");
/*  1654 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1656 */     Conv.restoreMessages();
/*  1657 */     return retArray;
/*       */   }
/*       */   
/*       */   public Long[] array_as_Long()
/*       */   {
/*  1662 */     return getArray_as_Long();
/*       */   }
/*       */   
/*       */   public Long[] getArray_as_Long() {
/*  1666 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1667 */     Long[] retArray = new Long[this.length];
/*  1668 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1670 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_Long((Double)this.array.get(i));
/*  1671 */       break;
/*       */     case 2: case 3: 
/*  1673 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_Long((Float)this.array.get(i));
/*  1674 */       break;
/*       */     case 4: case 5: 
/*  1676 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Long)this.array.get(i));
/*  1677 */       break;
/*       */     case 6: case 7: 
/*  1679 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_Long((Integer)this.array.get(i));
/*  1680 */       break;
/*       */     case 8: case 9: 
/*  1682 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_Long((Short)this.array.get(i));
/*  1683 */       break;
/*       */     case 10: case 11: 
/*  1685 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_Long((Byte)this.array.get(i));
/*  1686 */       break;
/*  1687 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Long((BigDecimal)this.array.get(i));
/*  1688 */       break;
/*  1689 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Long((BigInteger)this.array.get(i));
/*  1690 */       break;
/*  1691 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Long((String)this.array.get(i));
/*  1692 */       break;
/*       */     case 16: case 17: 
/*  1694 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_Long(((Character)this.array.get(i)).charValue());
/*  1695 */       break;
/*       */     case 14: case 15: 
/*  1697 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Long is meaningful/supported");
/*  1698 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1700 */     Conv.restoreMessages();
/*  1701 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public Integer[] array_as_Integer()
/*       */   {
/*  1707 */     return getArray_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer[] getArray_as_Integer() {
/*  1711 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1712 */     Integer[] retArray = new Integer[this.length];
/*  1713 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1715 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_Integer((Double)this.array.get(i));
/*  1716 */       break;
/*       */     case 2: case 3: 
/*  1718 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_Integer((Float)this.array.get(i));
/*  1719 */       break;
/*       */     case 4: case 5: 
/*  1721 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_Integer((Long)this.array.get(i));
/*  1722 */       break;
/*       */     case 6: case 7: 
/*  1724 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Integer)this.array.get(i));
/*  1725 */       break;
/*       */     case 8: case 9: 
/*  1727 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_Integer((Short)this.array.get(i));
/*  1728 */       break;
/*       */     case 10: case 11: 
/*  1730 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_Integer((Byte)this.array.get(i));
/*  1731 */       break;
/*  1732 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.array.get(i));
/*  1733 */       break;
/*  1734 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Integer((BigInteger)this.array.get(i));
/*  1735 */       break;
/*  1736 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Integer((String)this.array.get(i));
/*  1737 */       break;
/*       */     case 16: case 17: 
/*  1739 */       for (int i = 0; i < this.length; i++) retArray[i] = new Integer(((Character)this.array.get(i)).charValue());
/*  1740 */       break;
/*       */     case 14: case 15: 
/*  1742 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Integer is meaningful/supported");
/*  1743 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1745 */     Conv.restoreMessages();
/*  1746 */     return retArray;
/*       */   }
/*       */   
/*       */   public int[] array_as_int()
/*       */   {
/*  1751 */     return getArray_as_int();
/*       */   }
/*       */   
/*       */   public int[] getArray_as_int() {
/*  1755 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1756 */     int[] retArray = new int[this.length];
/*  1757 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1759 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_int((Double)this.array.get(i));
/*  1760 */       break;
/*       */     case 2: case 3: 
/*  1762 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_int((Float)this.array.get(i));
/*  1763 */       break;
/*       */     case 4: case 5: 
/*  1765 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_int((Long)this.array.get(i));
/*  1766 */       break;
/*       */     case 6: case 7: 
/*  1768 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Integer)this.array.get(i)).intValue();
/*  1769 */       break;
/*       */     case 8: case 9: 
/*  1771 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_int((Short)this.array.get(i));
/*  1772 */       break;
/*       */     case 10: case 11: 
/*  1774 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_int((Byte)this.array.get(i));
/*  1775 */       break;
/*  1776 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_int((BigDecimal)this.array.get(i));
/*  1777 */       break;
/*  1778 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_int((BigInteger)this.array.get(i));
/*  1779 */       break;
/*  1780 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Integer((String)this.array.get(i)).intValue();
/*  1781 */       break;
/*       */     case 16: case 17: 
/*  1783 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Character)this.array.get(i)).charValue();
/*  1784 */       break;
/*       */     case 14: case 15: 
/*  1786 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to int is meaningful/supported");
/*  1787 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1789 */     Conv.restoreMessages();
/*  1790 */     return retArray;
/*       */   }
/*       */   
/*       */   public short[] array_as_short()
/*       */   {
/*  1795 */     return getArray_as_short();
/*       */   }
/*       */   
/*       */   public short[] getArray_as_short() {
/*  1799 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1800 */     short[] retArray = new short[this.length];
/*  1801 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1803 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_short((Double)this.array.get(i));
/*  1804 */       break;
/*       */     case 2: case 3: 
/*  1806 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_short((Float)this.array.get(i));
/*  1807 */       break;
/*       */     case 4: case 5: 
/*  1809 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_short((Long)this.array.get(i));
/*  1810 */       break;
/*       */     case 6: case 7: 
/*  1812 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_short((Integer)this.array.get(i));
/*  1813 */       break;
/*       */     case 8: case 9: 
/*  1815 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Short)this.array.get(i)).shortValue();
/*  1816 */       break;
/*       */     case 10: case 11: 
/*  1818 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_short((Byte)this.array.get(i));
/*  1819 */       break;
/*  1820 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_short((BigDecimal)this.array.get(i));
/*  1821 */       break;
/*  1822 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_short((BigInteger)this.array.get(i));
/*  1823 */       break;
/*  1824 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Short((String)this.array.get(i)).shortValue();
/*  1825 */       break;
/*       */     case 16: case 17: 
/*  1827 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_short(((Character)this.array.get(i)).charValue());
/*  1828 */       break;
/*       */     case 14: case 15: 
/*  1830 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to short is meaningful/supported");
/*  1831 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1833 */     Conv.restoreMessages();
/*  1834 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public Short[] array_as_Short()
/*       */   {
/*  1840 */     return getArray_as_Short();
/*       */   }
/*       */   
/*       */   public Short[] getArray_as_Short() {
/*  1844 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1845 */     Short[] retArray = new Short[this.length];
/*  1846 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1848 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_Short((Double)this.array.get(i));
/*  1849 */       break;
/*       */     case 2: case 3: 
/*  1851 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_Short((Float)this.array.get(i));
/*  1852 */       break;
/*       */     case 4: case 5: 
/*  1854 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_Short((Long)this.array.get(i));
/*  1855 */       break;
/*       */     case 6: case 7: 
/*  1857 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_Short((Integer)this.array.get(i));
/*  1858 */       break;
/*       */     case 8: case 9: 
/*  1860 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Short)this.array.get(i));
/*  1861 */       break;
/*       */     case 10: case 11: 
/*  1863 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_Short((Byte)this.array.get(i));
/*  1864 */       break;
/*  1865 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Short((BigDecimal)this.array.get(i));
/*  1866 */       break;
/*  1867 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Short((BigInteger)this.array.get(i));
/*  1868 */       break;
/*  1869 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Short((String)this.array.get(i));
/*  1870 */       break;
/*       */     case 16: case 17: 
/*  1872 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_Short(((Character)this.array.get(i)).charValue());
/*  1873 */       break;
/*       */     case 14: case 15: 
/*  1875 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Short is meaningful/supported");
/*  1876 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1878 */     Conv.restoreMessages();
/*  1879 */     return retArray;
/*       */   }
/*       */   
/*       */   public byte[] array_as_byte()
/*       */   {
/*  1884 */     return getArray_as_byte();
/*       */   }
/*       */   
/*       */   public byte[] getArray_as_byte() {
/*  1888 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1889 */     byte[] retArray = new byte[this.length];
/*  1890 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1892 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_byte((Double)this.array.get(i));
/*  1893 */       break;
/*       */     case 2: case 3: 
/*  1895 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_byte((Float)this.array.get(i));
/*  1896 */       break;
/*       */     case 4: case 5: 
/*  1898 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_byte((Long)this.array.get(i));
/*  1899 */       break;
/*       */     case 6: case 7: 
/*  1901 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_byte((Integer)this.array.get(i));
/*  1902 */       break;
/*       */     case 8: case 9: 
/*  1904 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_byte((Short)this.array.get(i));
/*  1905 */       break;
/*       */     case 10: case 11: 
/*  1907 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Byte)this.array.get(i)).byteValue();
/*  1908 */       break;
/*  1909 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_byte((BigDecimal)this.array.get(i));
/*  1910 */       break;
/*  1911 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_byte((BigInteger)this.array.get(i));
/*  1912 */       break;
/*  1913 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Byte((String)this.array.get(i)).byteValue();
/*  1914 */       break;
/*       */     case 16: case 17: 
/*  1916 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_byte(((Character)this.array.get(i)).charValue());
/*  1917 */       break;
/*       */     case 14: case 15: 
/*  1919 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to byte is meaningful/supported");
/*  1920 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1922 */     Conv.restoreMessages();
/*  1923 */     return retArray;
/*       */   }
/*       */   
/*       */   public Byte[] array_as_Byte()
/*       */   {
/*  1928 */     return getArray_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte[] getArray_as_Byte() {
/*  1932 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1933 */     Byte[] retArray = new Byte[this.length];
/*  1934 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1936 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_Byte((Double)this.array.get(i));
/*  1937 */       break;
/*       */     case 2: case 3: 
/*  1939 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_Byte((Float)this.array.get(i));
/*  1940 */       break;
/*       */     case 4: case 5: 
/*  1942 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_Byte((Long)this.array.get(i));
/*  1943 */       break;
/*       */     case 6: case 7: 
/*  1945 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_Byte((Integer)this.array.get(i));
/*  1946 */       break;
/*       */     case 8: case 9: 
/*  1948 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_Byte((Short)this.array.get(i));
/*  1949 */       break;
/*       */     case 10: case 11: 
/*  1951 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Byte)this.array.get(i));
/*  1952 */       break;
/*  1953 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.array.get(i));
/*  1954 */       break;
/*  1955 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_Byte((BigInteger)this.array.get(i));
/*  1956 */       break;
/*  1957 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new Byte((String)this.array.get(i));
/*  1958 */       break;
/*       */     case 16: case 17: 
/*  1960 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_Byte(((Character)this.array.get(i)).charValue());
/*  1961 */       break;
/*       */     case 14: case 15: 
/*  1963 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Byte is meaningful/supported");
/*  1964 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  1966 */     Conv.restoreMessages();
/*  1967 */     return retArray;
/*       */   }
/*       */   
/*       */   public BigDecimal[] array_as_BigDecimal()
/*       */   {
/*  1972 */     return getArray_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal[] getArray_as_BigDecimal() {
/*  1976 */     if (this.suppressMessages) Conv.suppressMessages();
/*  1977 */     BigDecimal[] retArray = new BigDecimal[this.length];
/*  1978 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  1980 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_BigDecimal((Double)this.array.get(i));
/*  1981 */       break;
/*       */     case 2: case 3: 
/*  1983 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_BigDecimal((Float)this.array.get(i));
/*  1984 */       break;
/*       */     case 4: case 5: 
/*  1986 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_BigDecimal((Long)this.array.get(i));
/*  1987 */       break;
/*       */     case 6: case 7: 
/*  1989 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_BigDecimal((Integer)this.array.get(i));
/*  1990 */       break;
/*       */     case 8: case 9: 
/*  1992 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_BigDecimal((Short)this.array.get(i));
/*  1993 */       break;
/*       */     case 10: case 11: 
/*  1995 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_BigDecimal((Byte)this.array.get(i));
/*  1996 */       break;
/*  1997 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = ((BigDecimal)this.array.get(i));
/*  1998 */       break;
/*  1999 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.array.get(i));
/*  2000 */       break;
/*  2001 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new BigDecimal((String)this.array.get(i));
/*  2002 */       break;
/*       */     case 16: case 17: 
/*  2004 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_BigDecimal(((Character)this.array.get(i)).charValue());
/*  2005 */       break;
/*       */     case 14: case 15: 
/*  2007 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigDecimal is meaningful/supported");
/*  2008 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2010 */     Conv.restoreMessages();
/*  2011 */     return retArray;
/*       */   }
/*       */   
/*       */   public BigInteger[] array_as_BigInteger()
/*       */   {
/*  2016 */     return getArray_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger[] getArray_as_BigInteger() {
/*  2020 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2021 */     BigInteger[] retArray = new BigInteger[this.length];
/*  2022 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2024 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Double_to_BigInteger((Double)this.array.get(i));
/*  2025 */       break;
/*       */     case 2: case 3: 
/*  2027 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Float_to_BigInteger((Float)this.array.get(i));
/*  2028 */       break;
/*       */     case 4: case 5: 
/*  2030 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Long_to_BigInteger((Long)this.array.get(i));
/*  2031 */       break;
/*       */     case 6: case 7: 
/*  2033 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Integer_to_BigInteger((Integer)this.array.get(i));
/*  2034 */       break;
/*       */     case 8: case 9: 
/*  2036 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Short_to_BigInteger((Short)this.array.get(i));
/*  2037 */       break;
/*       */     case 10: case 11: 
/*  2039 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Byte_to_BigInteger((Byte)this.array.get(i));
/*  2040 */       break;
/*  2041 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.array.get(i));
/*  2042 */       break;
/*  2043 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = ((BigInteger)this.array.get(i));
/*  2044 */       break;
/*  2045 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = new BigInteger((String)this.array.get(i));
/*  2046 */       break;
/*       */     case 16: case 17: 
/*  2048 */       for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_int_to_BigInteger(((Character)this.array.get(i)).charValue());
/*  2049 */       break;
/*       */     case 14: case 15: 
/*  2051 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigInteger is meaningful/supported");
/*  2052 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2054 */     Conv.restoreMessages();
/*  2055 */     return retArray;
/*       */   }
/*       */   
/*       */   public Complex[] array_as_Complex()
/*       */   {
/*  2060 */     return getArray_as_Complex();
/*       */   }
/*       */   
/*       */   public Complex[] getArray_as_Complex() {
/*  2064 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2065 */     Complex[] retArray = Complex.oneDarray(this.length);
/*  2066 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2068 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(((Double)this.array.get(i)).doubleValue());
/*  2069 */       break;
/*       */     case 2: case 3: 
/*  2071 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(((Float)this.array.get(i)).doubleValue());
/*  2072 */       break;
/*       */     case 4: case 5: 
/*  2074 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_Long_to_double((Long)this.array.get(i)));
/*  2075 */       break;
/*       */     case 6: case 7: 
/*  2077 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_Integer_to_double((Integer)this.array.get(i)));
/*  2078 */       break;
/*       */     case 8: case 9: 
/*  2080 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_Short_to_double((Short)this.array.get(i)));
/*  2081 */       break;
/*       */     case 10: case 11: 
/*  2083 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_Byte_to_double((Byte)this.array.get(i)));
/*  2084 */       break;
/*  2085 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i)));
/*  2086 */       break;
/*  2087 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i)));
/*  2088 */       break;
/*  2089 */     case 14:  for (int i = 0; i < this.length; i++) retArray[i] = ((Complex)this.array.get(i));
/*  2090 */       break;
/*  2091 */     case 15:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Phasor_to_Complex((Phasor)this.array.get(i));
/*  2092 */       break;
/*  2093 */     case 18:  for (int i = 0; i < this.length; i++) {
/*  2094 */         String ss = (String)this.array.get(i);
/*  2095 */         if ((ss.indexOf('i') != -1) || (ss.indexOf('j') != -1)) {
/*  2096 */           retArray[i] = Complex.valueOf(ss);
/*       */         }
/*       */         else {
/*  2099 */           retArray[i] = new Complex(Double.valueOf(ss).doubleValue());
/*       */         }
/*       */       }
/*  2102 */       break;
/*       */     case 16: case 17: 
/*  2104 */       for (int i = 0; i < this.length; i++) retArray[i] = new Complex(Conv.convert_int_to_double(((Character)this.array.get(i)).charValue()));
/*  2105 */       break;
/*  2106 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2108 */     Conv.restoreMessages();
/*  2109 */     return retArray;
/*       */   }
/*       */   
/*       */   public Phasor[] array_as_Phasor()
/*       */   {
/*  2114 */     return getArray_as_Phasor();
/*       */   }
/*       */   
/*       */   public Phasor[] getArray_as_Phasor() {
/*  2118 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2119 */     Phasor[] retArray = Phasor.oneDarray(this.length);
/*  2120 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2122 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(((Double)this.array.get(i)).doubleValue());
/*  2123 */       break;
/*       */     case 2: case 3: 
/*  2125 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(((Float)this.array.get(i)).doubleValue());
/*  2126 */       break;
/*       */     case 4: case 5: 
/*  2128 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_Long_to_double((Long)this.array.get(i)));
/*  2129 */       break;
/*       */     case 6: case 7: 
/*  2131 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_Integer_to_double((Integer)this.array.get(i)));
/*  2132 */       break;
/*       */     case 8: case 9: 
/*  2134 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_Short_to_double((Short)this.array.get(i)));
/*  2135 */       break;
/*       */     case 10: case 11: 
/*  2137 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_Byte_to_double((Byte)this.array.get(i)));
/*  2138 */       break;
/*  2139 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i)));
/*  2140 */       break;
/*  2141 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i)));
/*  2142 */       break;
/*  2143 */     case 14:  for (int i = 0; i < this.length; i++) retArray[i] = Conv.convert_Complex_to_Phasor((Complex)this.array.get(i));
/*  2144 */       break;
/*  2145 */     case 15:  for (int i = 0; i < this.length; i++) retArray[i] = ((Phasor)this.array.get(i));
/*  2146 */       break;
/*  2147 */     case 18:  for (int i = 0; i < this.length; i++) {
/*  2148 */         String ss = ((String)this.array.get(i)).trim();
/*  2149 */         if ((ss.indexOf('<') != -1) || (ss.indexOf('L') != -1)) {
/*  2150 */           retArray[i] = Phasor.valueOf(ss);
/*       */         }
/*       */         else {
/*  2153 */           retArray[i] = new Phasor(Double.valueOf(ss).doubleValue());
/*       */         }
/*       */       }
/*  2156 */       break;
/*       */     case 16: case 17: 
/*  2158 */       for (int i = 0; i < this.length; i++) retArray[i] = new Phasor(Conv.convert_int_to_double(((Character)this.array.get(i)).charValue()));
/*  2159 */       break;
/*  2160 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2162 */     Conv.restoreMessages();
/*  2163 */     return retArray;
/*       */   }
/*       */   
/*       */   public Character[] array_as_Character()
/*       */   {
/*  2168 */     return getArray_as_Character();
/*       */   }
/*       */   
/*       */   public Character[] getArray_as_Character() {
/*  2172 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2173 */     Character[] retArray = new Character[this.length];
/*  2174 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  2176 */       for (int i = 0; i < this.length; i++) retArray[i] = new Character((char)((Integer)this.array.get(i)).intValue());
/*  2177 */       break;
/*       */     case 16: case 17: 
/*  2179 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Character)this.array.get(i));
/*  2180 */       break;
/*  2181 */     case 18:  boolean test = true;
/*  2182 */       String[] ss = new String[this.length];
/*  2183 */       for (int i = 0; i < this.length; i++) {
/*  2184 */         ss[i] = ((String)this.array.get(i)).trim();
/*  2185 */         if (ss[i].length() > 1) {
/*  2186 */           test = false;
/*  2187 */           break;
/*       */         }
/*       */       }
/*  2190 */       if (test) {
/*  2191 */         for (int i = 0; i < this.length; i++) { retArray[i] = new Character(ss[i].charAt(0));
/*       */         }
/*       */       } else {
/*  2194 */         throw new IllegalArgumentException("The String array elements are too long to be converted to Character");
/*       */       }
/*       */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*  2210 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
/*  2211 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2213 */     Conv.restoreMessages();
/*  2214 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public char[] array_as_char()
/*       */   {
/*  2220 */     return getArray_as_char();
/*       */   }
/*       */   
/*       */   public char[] getArray_as_char() {
/*  2224 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2225 */     char[] retArray = new char[this.length];
/*  2226 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  2228 */       for (int i = 0; i < this.length; i++) retArray[i] = ((char)((Integer)this.array.get(i)).intValue());
/*  2229 */       break;
/*       */     case 16: case 17: 
/*  2231 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Character)this.array.get(i)).charValue();
/*  2232 */       break;
/*  2233 */     case 18:  boolean test = true;
/*  2234 */       String[] ss = new String[this.length];
/*  2235 */       for (int i = 0; i < this.length; i++) {
/*  2236 */         ss[i] = ((String)this.array.get(i)).trim();
/*  2237 */         if (ss[i].length() > 1) {
/*  2238 */           test = false;
/*  2239 */           break;
/*       */         }
/*       */       }
/*  2242 */       if (test) {
/*  2243 */         for (int i = 0; i < this.length; i++) { retArray[i] = ss[i].charAt(0);
/*       */         }
/*       */       } else {
/*  2246 */         throw new IllegalArgumentException("The String array elements are too long to be converted to char");
/*       */       }
/*       */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*  2262 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
/*  2263 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2265 */     Conv.restoreMessages();
/*  2266 */     return retArray;
/*       */   }
/*       */   
/*       */   public String[] array_as_String()
/*       */   {
/*  2271 */     return getArray_as_String();
/*       */   }
/*       */   
/*       */   public String[] getArray_as_String() {
/*  2275 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2276 */     String[] retArray = new String[this.length];
/*  2277 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2279 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Double)this.array.get(i)).toString();
/*  2280 */       break;
/*       */     case 2: case 3: 
/*  2282 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Float)this.array.get(i)).toString();
/*  2283 */       break;
/*       */     case 4: case 5: 
/*  2285 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Long)this.array.get(i)).toString();
/*  2286 */       break;
/*       */     case 6: case 7: 
/*  2288 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Integer)this.array.get(i)).toString();
/*  2289 */       break;
/*       */     case 8: case 9: 
/*  2291 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Short)this.array.get(i)).toString();
/*  2292 */       break;
/*       */     case 10: case 11: 
/*  2294 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Byte)this.array.get(i)).toString();
/*  2295 */       break;
/*  2296 */     case 12:  for (int i = 0; i < this.length; i++) retArray[i] = ((BigDecimal)this.array.get(i)).toString();
/*  2297 */       break;
/*  2298 */     case 13:  for (int i = 0; i < this.length; i++) retArray[i] = ((BigInteger)this.array.get(i)).toString();
/*  2299 */       break;
/*  2300 */     case 14:  for (int i = 0; i < this.length; i++) retArray[i] = ((Complex)this.array.get(i)).toString();
/*  2301 */       break;
/*  2302 */     case 15:  for (int i = 0; i < this.length; i++) retArray[i] = ((Phasor)this.array.get(i)).toString();
/*  2303 */       break;
/*       */     case 16: case 17: 
/*  2305 */       for (int i = 0; i < this.length; i++) retArray[i] = ((Character)this.array.get(i)).toString();
/*  2306 */       break;
/*  2307 */     case 18:  for (int i = 0; i < this.length; i++) retArray[i] = ((String)this.array.get(i));
/*  2308 */       break;
/*  2309 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2311 */     Conv.restoreMessages();
/*  2312 */     return retArray;
/*       */   }
/*       */   
/*       */   public Object[] array_as_Object()
/*       */   {
/*  2317 */     return getArray_as_Object();
/*       */   }
/*       */   
/*       */   public Object[] getArray_as_Object() {
/*  2321 */     Object[] arrayo = new Object[this.length];
/*  2322 */     for (int i = 0; i < this.length; i++) arrayo[i] = this.array.get(i);
/*  2323 */     return arrayo;
/*       */   }
/*       */   
/*       */   public Vector array_as_Vector()
/*       */   {
/*  2328 */     return getArray_as_Vector();
/*       */   }
/*       */   
/*       */   public Vector<Object> getArray_as_Vector() {
/*  2332 */     Vector<Object> vec = new Vector(this.length);
/*  2333 */     for (int i = 0; i < this.length; i++) vec.addElement(this.array.get(i));
/*  2334 */     return vec;
/*       */   }
/*       */   
/*       */   public ArrayList array_as_ArrayList()
/*       */   {
/*  2339 */     return getArray_as_ArrayList();
/*       */   }
/*       */   
/*       */   public ArrayList<Object> getArray_as_ArrayList() {
/*  2343 */     ArrayList<Object> arrayl = new ArrayList(this.length);
/*  2344 */     for (int i = 0; i < this.length; i++) arrayl.add(this.array.get(i));
/*  2345 */     return arrayl;
/*       */   }
/*       */   
/*       */   public Matrix array_as_Matrix_rowMatrix()
/*       */   {
/*  2350 */     return getArray_as_Matrix_rowMatrix();
/*       */   }
/*       */   
/*       */   public Matrix getArray_as_Matrix_rowMatrix() {
/*  2354 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2355 */     Matrix mat = null;
/*  2356 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  2371 */       double[] dd = getArray_as_double();
/*  2372 */       mat = Matrix.rowMatrix(dd);
/*  2373 */       break;
/*  2374 */     case 14:  throw new IllegalArgumentException("Complex array cannot be converted to Matrix.rowMatrix - use method getArray_as_Complex_rowMatrix");
/*  2375 */     case 15:  throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.rowMatrix - use method getArray_as_Phasor_rowMatrix");
/*  2376 */     case 6: case 7: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2378 */     Conv.restoreMessages();
/*  2379 */     return mat;
/*       */   }
/*       */   
/*       */   public Matrix array_as_Matrix_columnMatrix()
/*       */   {
/*  2384 */     return getArray_as_Matrix_columnMatrix();
/*       */   }
/*       */   
/*       */   public Matrix getArray_as_Matrix_columnMatrix() {
/*  2388 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2389 */     Matrix mat = null;
/*  2390 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  2405 */       double[] dd = getArray_as_double();
/*  2406 */       mat = Matrix.columnMatrix(dd);
/*  2407 */       break;
/*  2408 */     case 14:  throw new IllegalArgumentException("Complex array cannot be converted to Matrix.columnMatrix - use method getArray_as_Complex_columnMatrix");
/*  2409 */     case 15:  throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.columnMatrix - use method getArray_as_Phasor_columnMatrix");
/*  2410 */     case 6: case 7: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2412 */     Conv.restoreMessages();
/*  2413 */     return mat;
/*       */   }
/*       */   
/*       */   public ComplexMatrix array_as_Complex_rowMatrix()
/*       */   {
/*  2418 */     return getArray_as_Complex_rowMatrix();
/*       */   }
/*       */   
/*       */   public ComplexMatrix getArray_as_Complex_rowMatrix() {
/*  2422 */     Complex[] cc = getArray_as_Complex();
/*  2423 */     ComplexMatrix mat = ComplexMatrix.rowMatrix(cc);
/*  2424 */     return mat;
/*       */   }
/*       */   
/*       */   public ComplexMatrix array_as_Complex_columnMatrix()
/*       */   {
/*  2429 */     return getArray_as_Complex_columnMatrix();
/*       */   }
/*       */   
/*       */   public ComplexMatrix getArray_as_Complex_columnMatrix() {
/*  2433 */     Complex[] cc = getArray_as_Complex();
/*  2434 */     ComplexMatrix mat = ComplexMatrix.columnMatrix(cc);
/*  2435 */     return mat;
/*       */   }
/*       */   
/*       */   public PhasorMatrix array_as_Phasor_rowMatrix()
/*       */   {
/*  2440 */     return getArray_as_Phasor_rowMatrix();
/*       */   }
/*       */   
/*       */   public PhasorMatrix getArray_as_Phasor_rowMatrix() {
/*  2444 */     Phasor[] cc = getArray_as_Phasor();
/*  2445 */     PhasorMatrix mat = PhasorMatrix.rowMatrix(cc);
/*  2446 */     return mat;
/*       */   }
/*       */   
/*       */   public PhasorMatrix array_as_Phasor_columnMatrix()
/*       */   {
/*  2451 */     return getArray_as_Phasor_columnMatrix();
/*       */   }
/*       */   
/*       */   public PhasorMatrix getArray_as_Phasor_columnMatrix() {
/*  2455 */     Phasor[] cc = getArray_as_Phasor();
/*  2456 */     PhasorMatrix mat = PhasorMatrix.columnMatrix(cc);
/*  2457 */     return mat;
/*       */   }
/*       */   
/*       */   public double[] array_as_modulus_of_Complex()
/*       */   {
/*  2462 */     Complex[] cc = getArray_as_Complex();
/*  2463 */     double[] mod = new double[this.length];
/*  2464 */     for (int i = 0; i < this.length; i++) mod[i] = cc[i].abs();
/*  2465 */     return mod;
/*       */   }
/*       */   
/*       */   public double[] array_as_real_part_of_Complex()
/*       */   {
/*  2470 */     return getArray_as_real_part_of_Complex();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_real_part_of_Complex() {
/*  2474 */     Complex[] cc = getArray_as_Complex();
/*  2475 */     double[] real = new double[this.length];
/*  2476 */     for (int i = 0; i < this.length; i++) real[i] = cc[i].getReal();
/*  2477 */     return real;
/*       */   }
/*       */   
/*       */   public double[] array_as_imaginary_part_of_Complex()
/*       */   {
/*  2482 */     return getArray_as_imaginay_part_of_Complex();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_imaginay_part_of_Complex() {
/*  2486 */     Complex[] cc = getArray_as_Complex();
/*  2487 */     double[] imag = new double[this.length];
/*  2488 */     for (int i = 0; i < this.length; i++) imag[i] = cc[i].getImag();
/*  2489 */     return imag;
/*       */   }
/*       */   
/*       */   public double[] array_as_magnitude_of_Phasor()
/*       */   {
/*  2494 */     return getArray_as_magnitude_of_Phasor();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_magnitude_of_Phasor() {
/*  2498 */     Phasor[] pp = getArray_as_Phasor();
/*  2499 */     double[] magn = new double[this.length];
/*  2500 */     for (int i = 0; i < this.length; i++) magn[i] = pp[i].getMagnitude();
/*  2501 */     return magn;
/*       */   }
/*       */   
/*       */   public double[] array_as_degrees_phase_of_Phasor()
/*       */   {
/*  2506 */     return getArray_as_degrees_phase_of_Phasor();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_degrees_phase_of_Phasor() {
/*  2510 */     Phasor[] pp = getArray_as_Phasor();
/*  2511 */     double[] phased = new double[this.length];
/*  2512 */     for (int i = 0; i < this.length; i++) phased[i] = pp[i].getPhaseInDegrees();
/*  2513 */     return phased;
/*       */   }
/*       */   
/*       */   public double[] array_as_radians_phase_of_Phasor()
/*       */   {
/*  2518 */     return getArray_as_radians_phase_of_Phasor();
/*       */   }
/*       */   
/*       */   public double[] getArray_as_radians_phase_of_Phasor() {
/*  2522 */     Phasor[] pp = getArray_as_Phasor();
/*  2523 */     double[] phaser = new double[this.length];
/*  2524 */     for (int i = 0; i < this.length; i++) phaser[i] = pp[i].getPhaseInRadians();
/*  2525 */     return phaser;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public double[] subarray_as_double(int start, int end)
/*       */   {
/*  2534 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2535 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2536 */     double[] retArray = new double[end - start + 1];
/*  2537 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2539 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Double)this.array.get(i)).doubleValue();
/*  2540 */       break;
/*       */     case 2: case 3: 
/*  2542 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_double((Float)this.array.get(i));
/*  2543 */       break;
/*       */     case 4: case 5: 
/*  2545 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_double((Long)this.array.get(i));
/*  2546 */       break;
/*       */     case 6: case 7: 
/*  2548 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_double((Integer)this.array.get(i));
/*  2549 */       break;
/*       */     case 8: case 9: 
/*  2551 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_double((Short)this.array.get(i));
/*  2552 */       break;
/*       */     case 10: case 11: 
/*  2554 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_double((Byte)this.array.get(i));
/*  2555 */       break;
/*  2556 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i));
/*  2557 */       break;
/*  2558 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i));
/*  2559 */       break;
/*  2560 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = Double.valueOf((String)this.array.get(i)).doubleValue();
/*  2561 */       break;
/*       */     case 16: case 17: 
/*  2563 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_double(((Character)this.array.get(i)).charValue());
/*  2564 */       break;
/*       */     case 14: case 15: 
/*  2566 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to double is meaningful/supported");
/*  2567 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2569 */     Conv.restoreMessages();
/*  2570 */     return retArray;
/*       */   }
/*       */   
/*       */   public Double[] subarray_as_Double(int start, int end)
/*       */   {
/*  2575 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2576 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2577 */     Double[] retArray = new Double[end - start + 1];
/*  2578 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2580 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Double)this.array.get(i));
/*  2581 */       break;
/*       */     case 2: case 3: 
/*  2583 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_Double((Float)this.array.get(i));
/*  2584 */       break;
/*       */     case 4: case 5: 
/*  2586 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_Double((Long)this.array.get(i));
/*  2587 */       break;
/*       */     case 6: case 7: 
/*  2589 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_Double((Integer)this.array.get(i));
/*  2590 */       break;
/*       */     case 8: case 9: 
/*  2592 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_Double((Short)this.array.get(i));
/*  2593 */       break;
/*       */     case 10: case 11: 
/*  2595 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_Double((Byte)this.array.get(i));
/*  2596 */       break;
/*  2597 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Double((BigDecimal)this.array.get(i));
/*  2598 */       break;
/*  2599 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Double((BigInteger)this.array.get(i));
/*  2600 */       break;
/*  2601 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Double((String)this.array.get(i));
/*  2602 */       break;
/*       */     case 16: case 17: 
/*  2604 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_Double(((Character)this.array.get(i)).charValue());
/*  2605 */       break;
/*       */     case 14: case 15: 
/*  2607 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Double is meaningful/supported");
/*  2608 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2610 */     Conv.restoreMessages();
/*  2611 */     return retArray;
/*       */   }
/*       */   
/*       */   public Float[] subarray_as_Float(int start, int end)
/*       */   {
/*  2616 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2617 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2618 */     Float[] retArray = new Float[end - start + 1];
/*  2619 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2621 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_Float((Double)this.array.get(i));
/*  2622 */       break;
/*       */     case 2: case 3: 
/*  2624 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Float)this.array.get(i));
/*  2625 */       break;
/*       */     case 4: case 5: 
/*  2627 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_Float((Long)this.array.get(i));
/*  2628 */       break;
/*       */     case 6: case 7: 
/*  2630 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_Float((Integer)this.array.get(i));
/*  2631 */       break;
/*       */     case 8: case 9: 
/*  2633 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_Float((Short)this.array.get(i));
/*  2634 */       break;
/*       */     case 10: case 11: 
/*  2636 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_Float((Byte)this.array.get(i));
/*  2637 */       break;
/*  2638 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Float((BigDecimal)this.array.get(i));
/*  2639 */       break;
/*  2640 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Float((BigInteger)this.array.get(i));
/*  2641 */       break;
/*  2642 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Float((String)this.array.get(i));
/*  2643 */       break;
/*       */     case 16: case 17: 
/*  2645 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_Float(((Character)this.array.get(i)).charValue());
/*  2646 */       break;
/*       */     case 14: case 15: 
/*  2648 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Float is meaningful/supported");
/*  2649 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2651 */     Conv.restoreMessages();
/*  2652 */     return retArray;
/*       */   }
/*       */   
/*       */   public float[] subarray_as_float(int start, int end)
/*       */   {
/*  2657 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2658 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2659 */     float[] retArray = new float[end - start + 1];
/*  2660 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2662 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_float((Double)this.array.get(i));
/*  2663 */       break;
/*       */     case 2: case 3: 
/*  2665 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Float)this.array.get(i)).floatValue();
/*  2666 */       break;
/*       */     case 4: case 5: 
/*  2668 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_float((Long)this.array.get(i));
/*  2669 */       break;
/*       */     case 6: case 7: 
/*  2671 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_float((Integer)this.array.get(i));
/*  2672 */       break;
/*       */     case 8: case 9: 
/*  2674 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_float((Short)this.array.get(i));
/*  2675 */       break;
/*       */     case 10: case 11: 
/*  2677 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_float((Byte)this.array.get(i));
/*  2678 */       break;
/*  2679 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_float((BigDecimal)this.array.get(i));
/*  2680 */       break;
/*  2681 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_float((BigInteger)this.array.get(i));
/*  2682 */       break;
/*  2683 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Float((String)this.array.get(i)).floatValue();
/*  2684 */       break;
/*       */     case 16: case 17: 
/*  2686 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_float(((Character)this.array.get(i)).charValue());
/*  2687 */       break;
/*       */     case 14: case 15: 
/*  2689 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to float is meaningful/supported");
/*  2690 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2692 */     Conv.restoreMessages();
/*  2693 */     return retArray;
/*       */   }
/*       */   
/*       */   public long[] subarray_as_long(int start, int end)
/*       */   {
/*  2698 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2699 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2700 */     long[] retArray = new long[end - start + 1];
/*  2701 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2703 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_long((Double)this.array.get(i));
/*  2704 */       break;
/*       */     case 2: case 3: 
/*  2706 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_long((Float)this.array.get(i));
/*  2707 */       break;
/*       */     case 4: case 5: 
/*  2709 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Long)this.array.get(i)).longValue();
/*  2710 */       break;
/*       */     case 6: case 7: 
/*  2712 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_long((Integer)this.array.get(i));
/*  2713 */       break;
/*       */     case 8: case 9: 
/*  2715 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_long((Short)this.array.get(i));
/*  2716 */       break;
/*       */     case 10: case 11: 
/*  2718 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_long((Byte)this.array.get(i));
/*  2719 */       break;
/*  2720 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_long((BigDecimal)this.array.get(i));
/*  2721 */       break;
/*  2722 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_long((BigInteger)this.array.get(i));
/*  2723 */       break;
/*  2724 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Long((String)this.array.get(i)).longValue();
/*  2725 */       break;
/*       */     case 16: case 17: 
/*  2727 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_long(((Character)this.array.get(i)).charValue());
/*  2728 */       break;
/*       */     case 14: case 15: 
/*  2730 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to long is meaningful/supported");
/*  2731 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2733 */     Conv.restoreMessages();
/*  2734 */     return retArray;
/*       */   }
/*       */   
/*       */   public Long[] subarray_as_Long(int start, int end)
/*       */   {
/*  2739 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2740 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2741 */     Long[] retArray = new Long[end - start + 1];
/*  2742 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2744 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_Long((Double)this.array.get(i));
/*  2745 */       break;
/*       */     case 2: case 3: 
/*  2747 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_Long((Float)this.array.get(i));
/*  2748 */       break;
/*       */     case 4: case 5: 
/*  2750 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Long)this.array.get(i));
/*  2751 */       break;
/*       */     case 6: case 7: 
/*  2753 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_Long((Integer)this.array.get(i));
/*  2754 */       break;
/*       */     case 8: case 9: 
/*  2756 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_Long((Short)this.array.get(i));
/*  2757 */       break;
/*       */     case 10: case 11: 
/*  2759 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_Long((Byte)this.array.get(i));
/*  2760 */       break;
/*  2761 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Long((BigDecimal)this.array.get(i));
/*  2762 */       break;
/*  2763 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Long((BigInteger)this.array.get(i));
/*  2764 */       break;
/*  2765 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Long((String)this.array.get(i));
/*  2766 */       break;
/*       */     case 16: case 17: 
/*  2768 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_Long(((Character)this.array.get(i)).charValue());
/*  2769 */       break;
/*       */     case 14: case 15: 
/*  2771 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Long is meaningful/supported");
/*  2772 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2774 */     Conv.restoreMessages();
/*  2775 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public Integer[] subarray_as_Integer(int start, int end)
/*       */   {
/*  2781 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2782 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2783 */     Integer[] retArray = new Integer[end - start + 1];
/*  2784 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2786 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_Integer((Double)this.array.get(i));
/*  2787 */       break;
/*       */     case 2: case 3: 
/*  2789 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_Integer((Float)this.array.get(i));
/*  2790 */       break;
/*       */     case 4: case 5: 
/*  2792 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_Integer((Long)this.array.get(i));
/*  2793 */       break;
/*       */     case 6: case 7: 
/*  2795 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Integer)this.array.get(i));
/*  2796 */       break;
/*       */     case 8: case 9: 
/*  2798 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_Integer((Short)this.array.get(i));
/*  2799 */       break;
/*       */     case 10: case 11: 
/*  2801 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_Integer((Byte)this.array.get(i));
/*  2802 */       break;
/*  2803 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.array.get(i));
/*  2804 */       break;
/*  2805 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Integer((BigInteger)this.array.get(i));
/*  2806 */       break;
/*  2807 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Integer((String)this.array.get(i));
/*  2808 */       break;
/*       */     case 16: case 17: 
/*  2810 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Integer(((Character)this.array.get(i)).charValue());
/*  2811 */       break;
/*       */     case 14: case 15: 
/*  2813 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Integer is meaningful/supported");
/*  2814 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2816 */     Conv.restoreMessages();
/*  2817 */     return retArray;
/*       */   }
/*       */   
/*       */   public int[] subarray_as_int(int start, int end)
/*       */   {
/*  2822 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2823 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2824 */     int[] retArray = new int[end - start + 1];
/*  2825 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2827 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_int((Double)this.array.get(i));
/*  2828 */       break;
/*       */     case 2: case 3: 
/*  2830 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_int((Float)this.array.get(i));
/*  2831 */       break;
/*       */     case 4: case 5: 
/*  2833 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_int((Long)this.array.get(i));
/*  2834 */       break;
/*       */     case 6: case 7: 
/*  2836 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Integer)this.array.get(i)).intValue();
/*  2837 */       break;
/*       */     case 8: case 9: 
/*  2839 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_int((Short)this.array.get(i));
/*  2840 */       break;
/*       */     case 10: case 11: 
/*  2842 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_int((Byte)this.array.get(i));
/*  2843 */       break;
/*  2844 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_int((BigDecimal)this.array.get(i));
/*  2845 */       break;
/*  2846 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_int((BigInteger)this.array.get(i));
/*  2847 */       break;
/*  2848 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Integer((String)this.array.get(i)).intValue();
/*  2849 */       break;
/*       */     case 16: case 17: 
/*  2851 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Character)this.array.get(i)).charValue();
/*  2852 */       break;
/*       */     case 14: case 15: 
/*  2854 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to int is meaningful/supported");
/*  2855 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2857 */     Conv.restoreMessages();
/*  2858 */     return retArray;
/*       */   }
/*       */   
/*       */   public short[] subarray_as_short(int start, int end)
/*       */   {
/*  2863 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2864 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2865 */     short[] retArray = new short[end - start + 1];
/*  2866 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2868 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_short((Double)this.array.get(i));
/*  2869 */       break;
/*       */     case 2: case 3: 
/*  2871 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_short((Float)this.array.get(i));
/*  2872 */       break;
/*       */     case 4: case 5: 
/*  2874 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_short((Long)this.array.get(i));
/*  2875 */       break;
/*       */     case 6: case 7: 
/*  2877 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_short((Integer)this.array.get(i));
/*  2878 */       break;
/*       */     case 8: case 9: 
/*  2880 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Short)this.array.get(i)).shortValue();
/*  2881 */       break;
/*       */     case 10: case 11: 
/*  2883 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_short((Byte)this.array.get(i));
/*  2884 */       break;
/*  2885 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_short((BigDecimal)this.array.get(i));
/*  2886 */       break;
/*  2887 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_short((BigInteger)this.array.get(i));
/*  2888 */       break;
/*  2889 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Short((String)this.array.get(i)).shortValue();
/*  2890 */       break;
/*       */     case 16: case 17: 
/*  2892 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_short(((Character)this.array.get(i)).charValue());
/*  2893 */       break;
/*       */     case 14: case 15: 
/*  2895 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to short is meaningful/supported");
/*  2896 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2898 */     Conv.restoreMessages();
/*  2899 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public Short[] subarray_as_Short(int start, int end)
/*       */   {
/*  2905 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2906 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2907 */     Short[] retArray = new Short[end - start + 1];
/*  2908 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2910 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_Short((Double)this.array.get(i));
/*  2911 */       break;
/*       */     case 2: case 3: 
/*  2913 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_Short((Float)this.array.get(i));
/*  2914 */       break;
/*       */     case 4: case 5: 
/*  2916 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_Short((Long)this.array.get(i));
/*  2917 */       break;
/*       */     case 6: case 7: 
/*  2919 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_Short((Integer)this.array.get(i));
/*  2920 */       break;
/*       */     case 8: case 9: 
/*  2922 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Short)this.array.get(i));
/*  2923 */       break;
/*       */     case 10: case 11: 
/*  2925 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_Short((Byte)this.array.get(i));
/*  2926 */       break;
/*  2927 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Short((BigDecimal)this.array.get(i));
/*  2928 */       break;
/*  2929 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Short((BigInteger)this.array.get(i));
/*  2930 */       break;
/*  2931 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Short((String)this.array.get(i));
/*  2932 */       break;
/*       */     case 16: case 17: 
/*  2934 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_Short(((Character)this.array.get(i)).charValue());
/*  2935 */       break;
/*       */     case 14: case 15: 
/*  2937 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Short is meaningful/supported");
/*  2938 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2940 */     Conv.restoreMessages();
/*  2941 */     return retArray;
/*       */   }
/*       */   
/*       */   public byte[] subarray_as_byte(int start, int end)
/*       */   {
/*  2946 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2947 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2948 */     byte[] retArray = new byte[end - start + 1];
/*  2949 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2951 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_byte((Double)this.array.get(i));
/*  2952 */       break;
/*       */     case 2: case 3: 
/*  2954 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_byte((Float)this.array.get(i));
/*  2955 */       break;
/*       */     case 4: case 5: 
/*  2957 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_byte((Long)this.array.get(i));
/*  2958 */       break;
/*       */     case 6: case 7: 
/*  2960 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_byte((Integer)this.array.get(i));
/*  2961 */       break;
/*       */     case 8: case 9: 
/*  2963 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_byte((Short)this.array.get(i));
/*  2964 */       break;
/*       */     case 10: case 11: 
/*  2966 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Byte)this.array.get(i)).byteValue();
/*  2967 */       break;
/*  2968 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_byte((BigDecimal)this.array.get(i));
/*  2969 */       break;
/*  2970 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_byte((BigInteger)this.array.get(i));
/*  2971 */       break;
/*  2972 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Byte((String)this.array.get(i)).byteValue();
/*  2973 */       break;
/*       */     case 16: case 17: 
/*  2975 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_byte(((Character)this.array.get(i)).charValue());
/*  2976 */       break;
/*       */     case 14: case 15: 
/*  2978 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to byte is meaningful/supported");
/*  2979 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  2981 */     Conv.restoreMessages();
/*  2982 */     return retArray;
/*       */   }
/*       */   
/*       */   public Byte[] subarray_as_Byte(int start, int end)
/*       */   {
/*  2987 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  2988 */     if (this.suppressMessages) Conv.suppressMessages();
/*  2989 */     Byte[] retArray = new Byte[end - start + 1];
/*  2990 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  2992 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_Byte((Double)this.array.get(i));
/*  2993 */       break;
/*       */     case 2: case 3: 
/*  2995 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_Byte((Float)this.array.get(i));
/*  2996 */       break;
/*       */     case 4: case 5: 
/*  2998 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_Byte((Long)this.array.get(i));
/*  2999 */       break;
/*       */     case 6: case 7: 
/*  3001 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_Byte((Integer)this.array.get(i));
/*  3002 */       break;
/*       */     case 8: case 9: 
/*  3004 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_Byte((Short)this.array.get(i));
/*  3005 */       break;
/*       */     case 10: case 11: 
/*  3007 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Byte)this.array.get(i));
/*  3008 */       break;
/*  3009 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.array.get(i));
/*  3010 */       break;
/*  3011 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_Byte((BigInteger)this.array.get(i));
/*  3012 */       break;
/*  3013 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Byte((String)this.array.get(i));
/*  3014 */       break;
/*       */     case 16: case 17: 
/*  3016 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_Byte(((Character)this.array.get(i)).charValue());
/*  3017 */       break;
/*       */     case 14: case 15: 
/*  3019 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Byte is meaningful/supported");
/*  3020 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3022 */     Conv.restoreMessages();
/*  3023 */     return retArray;
/*       */   }
/*       */   
/*       */   public BigDecimal[] subarray_as_BigDecimal(int start, int end)
/*       */   {
/*  3028 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3029 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3030 */     BigDecimal[] retArray = new BigDecimal[end - start + 1];
/*  3031 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3033 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_BigDecimal((Double)this.array.get(i));
/*  3034 */       break;
/*       */     case 2: case 3: 
/*  3036 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_BigDecimal((Float)this.array.get(i));
/*  3037 */       break;
/*       */     case 4: case 5: 
/*  3039 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_BigDecimal((Long)this.array.get(i));
/*  3040 */       break;
/*       */     case 6: case 7: 
/*  3042 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_BigDecimal((Integer)this.array.get(i));
/*  3043 */       break;
/*       */     case 8: case 9: 
/*  3045 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_BigDecimal((Short)this.array.get(i));
/*  3046 */       break;
/*       */     case 10: case 11: 
/*  3048 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_BigDecimal((Byte)this.array.get(i));
/*  3049 */       break;
/*  3050 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((BigDecimal)this.array.get(i));
/*  3051 */       break;
/*  3052 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.array.get(i));
/*  3053 */       break;
/*  3054 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new BigDecimal((String)this.array.get(i));
/*  3055 */       break;
/*       */     case 16: case 17: 
/*  3057 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_BigDecimal(((Character)this.array.get(i)).charValue());
/*  3058 */       break;
/*       */     case 14: case 15: 
/*  3060 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigDecimal is meaningful/supported");
/*  3061 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3063 */     Conv.restoreMessages();
/*  3064 */     return retArray;
/*       */   }
/*       */   
/*       */   public BigInteger[] subarray_as_BigInteger(int start, int end)
/*       */   {
/*  3069 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3070 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3071 */     BigInteger[] retArray = new BigInteger[end - start + 1];
/*  3072 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3074 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Double_to_BigInteger((Double)this.array.get(i));
/*  3075 */       break;
/*       */     case 2: case 3: 
/*  3077 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Float_to_BigInteger((Float)this.array.get(i));
/*  3078 */       break;
/*       */     case 4: case 5: 
/*  3080 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Long_to_BigInteger((Long)this.array.get(i));
/*  3081 */       break;
/*       */     case 6: case 7: 
/*  3083 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Integer_to_BigInteger((Integer)this.array.get(i));
/*  3084 */       break;
/*       */     case 8: case 9: 
/*  3086 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Short_to_BigInteger((Short)this.array.get(i));
/*  3087 */       break;
/*       */     case 10: case 11: 
/*  3089 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Byte_to_BigInteger((Byte)this.array.get(i));
/*  3090 */       break;
/*  3091 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.array.get(i));
/*  3092 */       break;
/*  3093 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((BigInteger)this.array.get(i));
/*  3094 */       break;
/*  3095 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = new BigInteger((String)this.array.get(i));
/*  3096 */       break;
/*       */     case 16: case 17: 
/*  3098 */       for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_int_to_BigInteger(((Character)this.array.get(i)).charValue());
/*  3099 */       break;
/*       */     case 14: case 15: 
/*  3101 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigInteger is meaningful/supported");
/*  3102 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3104 */     Conv.restoreMessages();
/*  3105 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public Complex[] subarray_as_Complex(int start, int end)
/*       */   {
/*  3111 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3112 */     Complex[] retArray = Complex.oneDarray(this.length);
/*  3113 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3115 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(((Double)this.array.get(i)).doubleValue());
/*  3116 */       break;
/*       */     case 2: case 3: 
/*  3118 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(((Float)this.array.get(i)).doubleValue());
/*  3119 */       break;
/*       */     case 4: case 5: 
/*  3121 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_Long_to_double((Long)this.array.get(i)));
/*  3122 */       break;
/*       */     case 6: case 7: 
/*  3124 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_Integer_to_double((Integer)this.array.get(i)));
/*  3125 */       break;
/*       */     case 8: case 9: 
/*  3127 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_Short_to_double((Short)this.array.get(i)));
/*  3128 */       break;
/*       */     case 10: case 11: 
/*  3130 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_Byte_to_double((Byte)this.array.get(i)));
/*  3131 */       break;
/*  3132 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i)));
/*  3133 */       break;
/*  3134 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i)));
/*  3135 */       break;
/*  3136 */     case 14:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((Complex)this.array.get(i));
/*  3137 */       break;
/*  3138 */     case 15:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Phasor_to_Complex((Phasor)this.array.get(i));
/*  3139 */       break;
/*  3140 */     case 18:  for (int i = start; i <= end; i++) {
/*  3141 */         String ss = (String)this.array.get(i);
/*  3142 */         if ((ss.indexOf('i') != -1) || (ss.indexOf('j') != -1)) {
/*  3143 */           retArray[(i - start)] = Complex.valueOf(ss);
/*       */         }
/*       */         else {
/*  3146 */           retArray[(i - start)] = new Complex(Double.valueOf(ss).doubleValue());
/*       */         }
/*       */       }
/*  3149 */       break;
/*       */     case 16: case 17: 
/*  3151 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Complex(Conv.convert_int_to_double(((Character)this.array.get(i)).charValue()));
/*  3152 */       break;
/*  3153 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3155 */     Conv.restoreMessages();
/*  3156 */     return retArray;
/*       */   }
/*       */   
/*       */   public Phasor[] subarray_as_Phasor(int start, int end)
/*       */   {
/*  3161 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3162 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3163 */     Phasor[] retArray = Phasor.oneDarray(this.length);
/*  3164 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3166 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(((Double)this.array.get(i)).doubleValue());
/*  3167 */       break;
/*       */     case 2: case 3: 
/*  3169 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(((Float)this.array.get(i)).doubleValue());
/*  3170 */       break;
/*       */     case 4: case 5: 
/*  3172 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_Long_to_double((Long)this.array.get(i)));
/*  3173 */       break;
/*       */     case 6: case 7: 
/*  3175 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_Integer_to_double((Integer)this.array.get(i)));
/*  3176 */       break;
/*       */     case 8: case 9: 
/*  3178 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_Short_to_double((Short)this.array.get(i)));
/*  3179 */       break;
/*       */     case 10: case 11: 
/*  3181 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_Byte_to_double((Byte)this.array.get(i)));
/*  3182 */       break;
/*  3183 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(i)));
/*  3184 */       break;
/*  3185 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(i)));
/*  3186 */       break;
/*  3187 */     case 14:  for (int i = start; i <= end; i++) retArray[(i - start)] = Conv.convert_Complex_to_Phasor((Complex)this.array.get(i));
/*  3188 */       break;
/*  3189 */     case 15:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((Phasor)this.array.get(i));
/*  3190 */       break;
/*  3191 */     case 18:  for (int i = start; i <= end; i++) {
/*  3192 */         String ss = ((String)this.array.get(i)).trim();
/*  3193 */         if ((ss.indexOf('<') != -1) || (ss.indexOf('L') != -1)) {
/*  3194 */           retArray[(i - start)] = Phasor.valueOf(ss);
/*       */         }
/*       */         else {
/*  3197 */           retArray[(i - start)] = new Phasor(Double.valueOf(ss).doubleValue());
/*       */         }
/*       */       }
/*  3200 */       break;
/*       */     case 16: case 17: 
/*  3202 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Phasor(Conv.convert_int_to_double(((Character)this.array.get(i)).charValue()));
/*  3203 */       break;
/*  3204 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3206 */     Conv.restoreMessages();
/*  3207 */     return retArray;
/*       */   }
/*       */   
/*       */   public Character[] subarray_as_Character(int start, int end)
/*       */   {
/*  3212 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3213 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3214 */     Character[] retArray = new Character[end - start + 1];
/*  3215 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  3217 */       for (int i = start; i <= end; i++) retArray[(i - start)] = new Character((char)((Integer)this.array.get(i)).intValue());
/*  3218 */       break;
/*       */     case 16: case 17: 
/*  3220 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Character)this.array.get(i));
/*  3221 */       break;
/*  3222 */     case 18:  boolean test = true;
/*  3223 */       String[] ss = new String[end - start + 1];
/*  3224 */       for (int i = start; i <= end; i++) {
/*  3225 */         ss[(i - start)] = ((String)this.array.get(i)).trim();
/*  3226 */         if (ss[(i - start)].length() > 1) {
/*  3227 */           test = false;
/*  3228 */           break;
/*       */         }
/*       */       }
/*  3231 */       if (test) {
/*  3232 */         for (int i = start; i <= end; i++) { retArray[(i - start)] = new Character(ss[(i - start)].charAt(0));
/*       */         }
/*       */       } else {
/*  3235 */         throw new IllegalArgumentException("The String array elements are too long to be converted to Character");
/*       */       }
/*       */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*  3251 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
/*  3252 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3254 */     Conv.restoreMessages();
/*  3255 */     return retArray;
/*       */   }
/*       */   
/*       */ 
/*       */   public char[] subarray_as_char(int start, int end)
/*       */   {
/*  3261 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3262 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3263 */     char[] retArray = new char[end - start + 1];
/*  3264 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  3266 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((char)((Integer)this.array.get(i)).intValue());
/*  3267 */       break;
/*       */     case 16: case 17: 
/*  3269 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Character)this.array.get(i)).charValue();
/*  3270 */       break;
/*  3271 */     case 18:  boolean test = true;
/*  3272 */       String[] ss = new String[end - start + 1];
/*  3273 */       for (int i = start; i <= end; i++) {
/*  3274 */         ss[(i - start)] = ((String)this.array.get(i)).trim();
/*  3275 */         if (ss[(i - start)].length() > 1) {
/*  3276 */           test = false;
/*  3277 */           break;
/*       */         }
/*       */       }
/*  3280 */       if (test) {
/*  3281 */         for (int i = start; i <= end; i++) { retArray[(i - start)] = ss[(i - start)].charAt(0);
/*       */         }
/*       */       } else {
/*  3284 */         throw new IllegalArgumentException("The String array elements are too long to be converted to char");
/*       */       }
/*       */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*  3300 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
/*  3301 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3303 */     Conv.restoreMessages();
/*  3304 */     return retArray;
/*       */   }
/*       */   
/*       */   public String[] subarray_as_String(int start, int end)
/*       */   {
/*  3309 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3310 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3311 */     String[] retArray = new String[end - start + 1];
/*  3312 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3314 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Double)this.array.get(i)).toString();
/*  3315 */       break;
/*       */     case 2: case 3: 
/*  3317 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Float)this.array.get(i)).toString();
/*  3318 */       break;
/*       */     case 4: case 5: 
/*  3320 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Long)this.array.get(i)).toString();
/*  3321 */       break;
/*       */     case 6: case 7: 
/*  3323 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Integer)this.array.get(i)).toString();
/*  3324 */       break;
/*       */     case 8: case 9: 
/*  3326 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Short)this.array.get(i)).toString();
/*  3327 */       break;
/*       */     case 10: case 11: 
/*  3329 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Byte)this.array.get(i)).toString();
/*  3330 */       break;
/*  3331 */     case 12:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((BigDecimal)this.array.get(i)).toString();
/*  3332 */       break;
/*  3333 */     case 13:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((BigInteger)this.array.get(i)).toString();
/*  3334 */       break;
/*  3335 */     case 14:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((Complex)this.array.get(i)).toString();
/*  3336 */       break;
/*  3337 */     case 15:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((Phasor)this.array.get(i)).toString();
/*  3338 */       break;
/*       */     case 16: case 17: 
/*  3340 */       for (int i = start; i <= end; i++) retArray[(i - start)] = ((Character)this.array.get(i)).toString();
/*  3341 */       break;
/*  3342 */     case 18:  for (int i = start; i <= end; i++) retArray[(i - start)] = ((String)this.array.get(i));
/*  3343 */       break;
/*  3344 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3346 */     Conv.restoreMessages();
/*  3347 */     return retArray;
/*       */   }
/*       */   
/*       */   public Object[] subarray_as_Object(int start, int end)
/*       */   {
/*  3352 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3353 */     Object[] arrayo = new Object[end - start + 1];
/*  3354 */     for (int i = start; i <= end; i++) arrayo[(i - start)] = this.array.get(i);
/*  3355 */     return arrayo;
/*       */   }
/*       */   
/*       */   public Vector<Object> subarray_as_Vector(int start, int end)
/*       */   {
/*  3360 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3361 */     Vector<Object> vec = new Vector(end - start + 1);
/*  3362 */     for (int i = start; i <= end; i++) vec.addElement(this.array.get(i));
/*  3363 */     return vec;
/*       */   }
/*       */   
/*       */   public ArrayList<Object> subarray_as_ArrayList(int start, int end)
/*       */   {
/*  3368 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3369 */     ArrayList<Object> arrayl = new ArrayList(end - start + 1);
/*  3370 */     for (int i = start; i <= end; i++) arrayl.add(this.array.get(i));
/*  3371 */     return arrayl;
/*       */   }
/*       */   
/*       */   public Matrix subarray_as_Matrix_rowMatrix(int start, int end)
/*       */   {
/*  3376 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3377 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3378 */     Matrix mat = null;
/*  3379 */     double[] retArray = new double[end - start + 1];
/*  3380 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3395 */       double[] dd = getArray_as_double();
/*  3396 */       for (int i = start; i <= end; i++) retArray[(i - start)] = dd[i];
/*  3397 */       mat = Matrix.rowMatrix(retArray);
/*  3398 */       break;
/*  3399 */     case 14:  throw new IllegalArgumentException("Complex array cannot be converted to Matrix.rowMatrix - use method subarray_as_Complex_rowMatrix");
/*  3400 */     case 15:  throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.rowMatrix - use method subarray_as_Phasor_rowMatrix");
/*  3401 */     case 6: case 7: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3403 */     Conv.restoreMessages();
/*  3404 */     return mat;
/*       */   }
/*       */   
/*       */   public Matrix subarray_as_Matrix_columnMatrix(int start, int end)
/*       */   {
/*  3409 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3410 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3411 */     Matrix mat = null;
/*  3412 */     double[] retArray = new double[end - start + 1];
/*  3413 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3428 */       double[] dd = getArray_as_double();
/*  3429 */       for (int i = start; i <= end; i++) retArray[(i - start)] = dd[i];
/*  3430 */       mat = Matrix.columnMatrix(retArray);
/*  3431 */       break;
/*  3432 */     case 14:  throw new IllegalArgumentException("Complex array cannot be converted to Matrix.columnMatrix - use method subarray_as_Complex_columnMatrix");
/*  3433 */     case 15:  throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.columnMatrix - use method subarray_as_Phasor_columnMatrix");
/*  3434 */     case 6: case 7: default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3436 */     Conv.restoreMessages();
/*  3437 */     return mat;
/*       */   }
/*       */   
/*       */   public ComplexMatrix subarray_as_Complex_rowMatrix(int start, int end)
/*       */   {
/*  3442 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3443 */     Complex[] cc = getArray_as_Complex();
/*  3444 */     Complex[] retArray = new Complex[end - start + 1];
/*  3445 */     for (int i = start; i <= end; i++) retArray[(i - start)] = cc[i];
/*  3446 */     ComplexMatrix mat = ComplexMatrix.rowMatrix(retArray);
/*  3447 */     return mat;
/*       */   }
/*       */   
/*       */   public ComplexMatrix subarray_as_Complex_columnMatrix(int start, int end)
/*       */   {
/*  3452 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3453 */     Complex[] cc = getArray_as_Complex();
/*  3454 */     Complex[] retArray = new Complex[end - start + 1];
/*  3455 */     for (int i = start; i <= end; i++) retArray[(i - start)] = cc[i];
/*  3456 */     ComplexMatrix mat = ComplexMatrix.columnMatrix(retArray);
/*  3457 */     return mat;
/*       */   }
/*       */   
/*       */   public PhasorMatrix subarray_as_Phasor_rowMatrix(int start, int end)
/*       */   {
/*  3462 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3463 */     Phasor[] pp = getArray_as_Phasor();
/*  3464 */     Phasor[] retArray = new Phasor[end - start + 1];
/*  3465 */     for (int i = start; i <= end; i++) retArray[(i - start)] = pp[i];
/*  3466 */     PhasorMatrix mat = PhasorMatrix.rowMatrix(retArray);
/*  3467 */     return mat;
/*       */   }
/*       */   
/*       */   public PhasorMatrix subarray_as_Phasor_columnMatrix(int start, int end)
/*       */   {
/*  3472 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3473 */     Phasor[] pp = getArray_as_Phasor();
/*  3474 */     Phasor[] retArray = new Phasor[end - start + 1];
/*  3475 */     for (int i = start; i <= end; i++) retArray[(i - start)] = pp[i];
/*  3476 */     PhasorMatrix mat = PhasorMatrix.columnMatrix(retArray);
/*  3477 */     return mat;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_modulus_of_Complex(int start, int end)
/*       */   {
/*  3482 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3483 */     Complex[] cc = getArray_as_Complex();
/*  3484 */     double[] real = new double[end - start + 1];
/*  3485 */     for (int i = start; i <= end; i++) real[(i - start)] = cc[i].abs();
/*  3486 */     return real;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_real_part_of_Complex(int start, int end)
/*       */   {
/*  3491 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3492 */     Complex[] cc = getArray_as_Complex();
/*  3493 */     double[] real = new double[end - start + 1];
/*  3494 */     for (int i = start; i <= end; i++) real[(i - start)] = cc[i].getReal();
/*  3495 */     return real;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_imaginay_part_of_Complex(int start, int end)
/*       */   {
/*  3500 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3501 */     Complex[] cc = getArray_as_Complex();
/*  3502 */     double[] imag = new double[end - start + 1];
/*  3503 */     for (int i = start; i <= end; i++) imag[(i - start)] = cc[i].getImag();
/*  3504 */     return imag;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_magnitude_of_Phasor(int start, int end)
/*       */   {
/*  3509 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3510 */     Phasor[] pp = getArray_as_Phasor();
/*  3511 */     double[] magn = new double[end - start + 1];
/*  3512 */     for (int i = start; i <= end; i++) magn[(i - start)] = pp[i].getMagnitude();
/*  3513 */     return magn;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_degrees_phase_of_Phasor(int start, int end)
/*       */   {
/*  3518 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3519 */     Phasor[] pp = getArray_as_Phasor();
/*  3520 */     double[] phased = new double[end - start + 1];
/*  3521 */     for (int i = start; i <= end; i++) phased[(i - start)] = pp[i].getPhaseInDegrees();
/*  3522 */     return phased;
/*       */   }
/*       */   
/*       */   public double[] subarray_as_radians_phase_of_Phasor(int start, int end)
/*       */   {
/*  3527 */     if (end >= this.length) throw new IllegalArgumentException("end, " + end + ", is greater than the highest index, " + (this.length - 1));
/*  3528 */     Phasor[] pp = getArray_as_Phasor();
/*  3529 */     double[] phaser = new double[end - start + 1];
/*  3530 */     for (int i = start; i <= end; i++) phaser[(i - start)] = pp[i].getPhaseInRadians();
/*  3531 */     return phaser;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   protected void minmax()
/*       */   {
/*  3540 */     int[] maxminIndices = new int[2];
/*  3541 */     findMinMax(getArray_as_Object(), this.minmax, maxminIndices, this.typeName, this.type);
/*  3542 */     this.maxIndex = maxminIndices[0];
/*  3543 */     this.minIndex = maxminIndices[1];
/*       */   }
/*       */   
/*       */ 
/*       */   protected static void findMinMax(Object[] arrayo, ArrayList<Object> minmaxx, int[] maxminIndices, String[] aTypeName, int aType)
/*       */   {
/*  3549 */     int maxIndexx = 0;
/*  3550 */     int minIndexx = 0;
/*  3551 */     int arraylength = arrayo.length;
/*  3552 */     switch (aType) {
/*       */     case 0: case 1: 
/*  3554 */       double[] arrayD = new double[arraylength];
/*  3555 */       for (int i = 0; i < arraylength; i++) arrayD[i] = ((Double)arrayo[i]).doubleValue();
/*  3556 */       double amaxD = arrayD[0];
/*  3557 */       double aminD = arrayD[0];
/*  3558 */       maxIndexx = 0;
/*  3559 */       minIndexx = 0;
/*  3560 */       for (int i = 1; i < arraylength; i++) {
/*  3561 */         if (arrayD[i] > amaxD) {
/*  3562 */           amaxD = arrayD[i];
/*  3563 */           maxIndexx = i;
/*       */         }
/*  3565 */         if (arrayD[i] < aminD) {
/*  3566 */           aminD = arrayD[i];
/*  3567 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3570 */       minmaxx.add(new Double(amaxD));
/*  3571 */       minmaxx.add(new Double(aminD));
/*  3572 */       break;
/*       */     case 4: case 5: 
/*  3574 */       long[] arrayL = new long[arraylength];
/*  3575 */       for (int i = 0; i < arraylength; i++) arrayL[i] = ((Long)arrayo[i]).longValue();
/*  3576 */       long amaxL = arrayL[0];
/*  3577 */       long aminL = arrayL[0];
/*  3578 */       maxIndexx = 0;
/*  3579 */       minIndexx = 0;
/*  3580 */       for (int i = 1; i < arraylength; i++) {
/*  3581 */         if (arrayL[i] > amaxL) {
/*  3582 */           amaxL = arrayL[i];
/*  3583 */           maxIndexx = i;
/*       */         }
/*  3585 */         if (arrayL[i] < aminL) {
/*  3586 */           aminL = arrayL[i];
/*  3587 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3590 */       minmaxx.add(new Long(amaxL));
/*  3591 */       minmaxx.add(new Long(aminL));
/*  3592 */       break;
/*       */     case 2: case 3: 
/*  3594 */       float[] arrayF = new float[arraylength];
/*  3595 */       for (int i = 0; i < arraylength; i++) arrayF[i] = ((Float)arrayo[i]).floatValue();
/*  3596 */       float amaxF = arrayF[0];
/*  3597 */       float aminF = arrayF[0];
/*  3598 */       maxIndexx = 0;
/*  3599 */       minIndexx = 0;
/*  3600 */       for (int i = 1; i < arraylength; i++) {
/*  3601 */         if (arrayF[i] > amaxF) {
/*  3602 */           amaxF = arrayF[i];
/*  3603 */           maxIndexx = i;
/*       */         }
/*  3605 */         if (arrayF[i] < aminF) {
/*  3606 */           aminF = arrayF[i];
/*  3607 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3610 */       minmaxx.add(new Float(amaxF));
/*  3611 */       minmaxx.add(new Float(aminF));
/*  3612 */       break;
/*       */     case 6: case 7: 
/*  3614 */       int[] arrayI = new int[arraylength];
/*  3615 */       for (int i = 0; i < arraylength; i++) arrayI[i] = ((Integer)arrayo[i]).intValue();
/*  3616 */       int amaxI = arrayI[0];
/*  3617 */       int aminI = arrayI[0];
/*  3618 */       maxIndexx = 0;
/*  3619 */       minIndexx = 0;
/*  3620 */       for (int i = 1; i < arraylength; i++) {
/*  3621 */         if (arrayI[i] > amaxI) {
/*  3622 */           amaxI = arrayI[i];
/*  3623 */           maxIndexx = i;
/*       */         }
/*  3625 */         if (arrayI[i] < aminI) {
/*  3626 */           aminI = arrayI[i];
/*  3627 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3630 */       minmaxx.add(new Integer(amaxI));
/*  3631 */       minmaxx.add(new Integer(aminI));
/*  3632 */       break;
/*       */     case 8: case 9: 
/*  3634 */       short[] arrayS = new short[arraylength];
/*  3635 */       for (int i = 0; i < arraylength; i++) arrayS[i] = ((Short)arrayo[i]).shortValue();
/*  3636 */       short amaxS = arrayS[0];
/*  3637 */       short aminS = arrayS[0];
/*  3638 */       maxIndexx = 0;
/*  3639 */       minIndexx = 0;
/*  3640 */       for (int i = 1; i < arraylength; i++) {
/*  3641 */         if (arrayS[i] > amaxS) {
/*  3642 */           amaxS = arrayS[i];
/*  3643 */           maxIndexx = i;
/*       */         }
/*  3645 */         if (arrayS[i] < aminS) {
/*  3646 */           aminS = arrayS[i];
/*  3647 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3650 */       minmaxx.add(new Short(amaxS));
/*  3651 */       minmaxx.add(new Short(aminS));
/*  3652 */       break;
/*       */     case 10: case 11: 
/*  3654 */       byte[] arrayB = new byte[arraylength];
/*  3655 */       for (int i = 0; i < arraylength; i++) arrayB[i] = ((Byte)arrayo[i]).byteValue();
/*  3656 */       byte amaxB = arrayB[0];
/*  3657 */       byte aminB = arrayB[0];
/*  3658 */       maxIndexx = 0;
/*  3659 */       minIndexx = 0;
/*  3660 */       for (int i = 1; i < arraylength; i++) {
/*  3661 */         if (arrayB[i] > amaxB) {
/*  3662 */           amaxB = arrayB[i];
/*  3663 */           maxIndexx = i;
/*       */         }
/*  3665 */         if (arrayB[i] < aminB) {
/*  3666 */           aminB = arrayB[i];
/*  3667 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3670 */       minmaxx.add(new Byte(amaxB));
/*  3671 */       minmaxx.add(new Byte(aminB));
/*  3672 */       break;
/*  3673 */     case 12:  BigDecimal[] arrayBD = new BigDecimal[arraylength];
/*  3674 */       for (int i = 0; i < arraylength; i++) arrayBD[i] = ((BigDecimal)arrayo[i]);
/*  3675 */       BigDecimal amaxBD = arrayBD[0];
/*  3676 */       BigDecimal aminBD = arrayBD[0];
/*  3677 */       maxIndexx = 0;
/*  3678 */       minIndexx = 0;
/*  3679 */       for (int i = 1; i < arraylength; i++) {
/*  3680 */         if (arrayBD[i].compareTo(amaxBD) == 1) {
/*  3681 */           amaxBD = arrayBD[i];
/*  3682 */           maxIndexx = i;
/*       */         }
/*  3684 */         if (arrayBD[i].compareTo(aminBD) == -1) {
/*  3685 */           aminBD = arrayBD[i];
/*  3686 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3689 */       minmaxx.add(amaxBD);
/*  3690 */       minmaxx.add(aminBD);
/*  3691 */       break;
/*  3692 */     case 13:  BigInteger[] arrayBI = new BigInteger[arraylength];
/*  3693 */       for (int i = 0; i < arraylength; i++) arrayBI[i] = ((BigInteger)arrayo[i]);
/*  3694 */       BigInteger amaxBI = arrayBI[0];
/*  3695 */       BigInteger aminBI = arrayBI[0];
/*  3696 */       maxIndexx = 0;
/*  3697 */       minIndexx = 0;
/*  3698 */       for (int i = 1; i < arraylength; i++) {
/*  3699 */         if (arrayBI[i].compareTo(amaxBI) == 1) {
/*  3700 */           amaxBI = arrayBI[i];
/*  3701 */           maxIndexx = i;
/*       */         }
/*  3703 */         if (arrayBI[i].compareTo(aminBI) == -1) {
/*  3704 */           aminBI = arrayBI[i];
/*  3705 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3708 */       minmaxx.add(amaxBI);
/*  3709 */       minmaxx.add(aminBI);
/*  3710 */       break;
/*       */     case 16: case 17: 
/*  3712 */       int[] arrayInt = new int[arraylength];
/*  3713 */       for (int i = 0; i < arraylength; i++) arrayInt[i] = ((Character)arrayo[i]).charValue();
/*  3714 */       int amaxInt = arrayInt[0];
/*  3715 */       int aminInt = arrayInt[0];
/*  3716 */       maxIndexx = 0;
/*  3717 */       minIndexx = 0;
/*  3718 */       for (int i = 1; i < arraylength; i++) {
/*  3719 */         if (arrayInt[i] > amaxInt) {
/*  3720 */           amaxInt = arrayInt[i];
/*  3721 */           maxIndexx = i;
/*       */         }
/*  3723 */         if (arrayInt[i] < aminInt) {
/*  3724 */           aminInt = arrayInt[i];
/*  3725 */           minIndexx = i;
/*       */         }
/*       */       }
/*  3728 */       minmaxx.add(new Character((char)amaxInt));
/*  3729 */       minmaxx.add(new Character((char)aminInt));
/*  3730 */       break;
/*       */     case 14: case 15: 
/*       */     case 18: 
/*  3733 */       System.out.println("ArrayMaths:  getMaximum_... or getMinimum_... (findMinMax): the " + aTypeName[aType] + " is not a numerical type for which a maximum or a minimum is meaningful/supported");
/*  3734 */       break;
/*  3735 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3737 */     maxminIndices[0] = maxIndexx;
/*  3738 */     maxminIndices[1] = minIndexx;
/*       */   }
/*       */   
/*       */   public double maximum()
/*       */   {
/*  3743 */     return getMaximum_as_double();
/*       */   }
/*       */   
/*       */   public double maximum_as_double() {
/*  3747 */     return getMaximum_as_double();
/*       */   }
/*       */   
/*       */   public double getMaximum() {
/*  3751 */     return getMaximum_as_double();
/*       */   }
/*       */   
/*       */   public double getMaximum_as_double() {
/*  3755 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3756 */     double max = 0.0D;
/*  3757 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3759 */       max = ((Double)this.minmax.get(0)).doubleValue();
/*  3760 */       break;
/*       */     case 2: case 3: 
/*  3762 */       max = Conv.convert_Float_to_double((Float)this.minmax.get(0));
/*  3763 */       break;
/*       */     case 4: case 5: 
/*  3765 */       max = Conv.convert_Long_to_double((Long)this.minmax.get(0));
/*  3766 */       break;
/*       */     case 6: case 7: 
/*  3768 */       max = Conv.convert_Integer_to_double((Integer)this.minmax.get(0));
/*  3769 */       break;
/*       */     case 8: case 9: 
/*  3771 */       max = Conv.convert_Short_to_double((Short)this.minmax.get(0));
/*  3772 */       break;
/*       */     case 10: case 11: 
/*  3774 */       max = Conv.convert_Byte_to_double((Byte)this.minmax.get(0));
/*  3775 */       break;
/*  3776 */     case 12:  max = Conv.convert_BigDecimal_to_double((BigDecimal)this.minmax.get(0));
/*  3777 */       break;
/*  3778 */     case 13:  max = Conv.convert_BigInteger_to_double((BigInteger)this.minmax.get(0));
/*  3779 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3784 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3785 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3787 */     Conv.restoreMessages();
/*  3788 */     return max;
/*       */   }
/*       */   
/*       */   public Double maximum_as_Double() {
/*  3792 */     return getMaximum_as_Double();
/*       */   }
/*       */   
/*       */   public Double getMaximum_as_Double() {
/*  3796 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3797 */     Double max = new Double(0.0D);
/*  3798 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3800 */       max = (Double)this.minmax.get(0);
/*  3801 */       break;
/*       */     case 2: case 3: 
/*  3803 */       max = Conv.convert_Float_to_Double((Float)this.minmax.get(0));
/*  3804 */       break;
/*       */     case 4: case 5: 
/*  3806 */       max = Conv.convert_Long_to_Double((Long)this.minmax.get(0));
/*  3807 */       break;
/*       */     case 6: case 7: 
/*  3809 */       max = Conv.convert_Integer_to_Double((Integer)this.minmax.get(0));
/*  3810 */       break;
/*       */     case 8: case 9: 
/*  3812 */       max = Conv.convert_Short_to_Double((Short)this.minmax.get(0));
/*  3813 */       break;
/*       */     case 10: case 11: 
/*  3815 */       max = Conv.convert_Byte_to_Double((Byte)this.minmax.get(0));
/*  3816 */       break;
/*  3817 */     case 12:  max = Conv.convert_BigDecimal_to_Double((BigDecimal)this.minmax.get(0));
/*  3818 */       break;
/*  3819 */     case 13:  max = Conv.convert_BigInteger_to_Double((BigInteger)this.minmax.get(0));
/*  3820 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3825 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3826 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3828 */     Conv.restoreMessages();
/*  3829 */     return max;
/*       */   }
/*       */   
/*       */   public Float maximum_as_Float()
/*       */   {
/*  3834 */     return getMaximum_as_Float();
/*       */   }
/*       */   
/*       */   public Float getMaximum_as_Float() {
/*  3838 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3839 */     Float max = new Float(0.0D);
/*  3840 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3842 */       max = Conv.convert_Double_to_Float((Double)this.minmax.get(0));
/*  3843 */       break;
/*       */     case 2: case 3: 
/*  3845 */       max = (Float)this.minmax.get(0);
/*  3846 */       break;
/*       */     case 4: case 5: 
/*  3848 */       max = Conv.convert_Long_to_Float((Long)this.minmax.get(0));
/*  3849 */       break;
/*       */     case 6: case 7: 
/*  3851 */       max = Conv.convert_Integer_to_Float((Integer)this.minmax.get(0));
/*  3852 */       break;
/*       */     case 8: case 9: 
/*  3854 */       max = Conv.convert_Short_to_Float((Short)this.minmax.get(0));
/*  3855 */       break;
/*       */     case 10: case 11: 
/*  3857 */       max = Conv.convert_Byte_to_Float((Byte)this.minmax.get(0));
/*  3858 */       break;
/*  3859 */     case 12:  max = Conv.convert_BigDecimal_to_Float((BigDecimal)this.minmax.get(0));
/*  3860 */       break;
/*  3861 */     case 13:  max = Conv.convert_BigInteger_to_Float((BigInteger)this.minmax.get(0));
/*  3862 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3867 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3868 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3870 */     Conv.restoreMessages();
/*  3871 */     return max;
/*       */   }
/*       */   
/*       */ 
/*       */   public float maximum_as_float()
/*       */   {
/*  3877 */     return getMaximum_as_float();
/*       */   }
/*       */   
/*       */   public float getMaximum_as_float() {
/*  3881 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3882 */     float max = 0.0F;
/*  3883 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3885 */       max = Conv.convert_Double_to_float((Double)this.minmax.get(0));
/*  3886 */       break;
/*       */     case 2: case 3: 
/*  3888 */       max = ((Float)this.minmax.get(0)).floatValue();
/*  3889 */       break;
/*       */     case 4: case 5: 
/*  3891 */       max = Conv.convert_Long_to_float((Long)this.minmax.get(0));
/*  3892 */       break;
/*       */     case 6: case 7: 
/*  3894 */       max = Conv.convert_Integer_to_float((Integer)this.minmax.get(0));
/*  3895 */       break;
/*       */     case 8: case 9: 
/*  3897 */       max = Conv.convert_Short_to_float((Short)this.minmax.get(0));
/*  3898 */       break;
/*       */     case 10: case 11: 
/*  3900 */       max = Conv.convert_Byte_to_float((Byte)this.minmax.get(0));
/*  3901 */       break;
/*  3902 */     case 12:  max = Conv.convert_BigDecimal_to_float((BigDecimal)this.minmax.get(0));
/*  3903 */       break;
/*  3904 */     case 13:  max = Conv.convert_BigInteger_to_float((BigInteger)this.minmax.get(0));
/*  3905 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3910 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3911 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3913 */     Conv.restoreMessages();
/*  3914 */     return max;
/*       */   }
/*       */   
/*       */   public long maximum_as_long()
/*       */   {
/*  3919 */     return getMaximum_as_long();
/*       */   }
/*       */   
/*       */   public long getMaximum_as_long() {
/*  3923 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3924 */     long max = 0L;
/*  3925 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3927 */       max = Conv.convert_Double_to_long((Double)this.minmax.get(0));
/*  3928 */       break;
/*       */     case 2: case 3: 
/*  3930 */       max = Conv.convert_Float_to_long((Float)this.minmax.get(0));
/*  3931 */       break;
/*       */     case 4: case 5: 
/*  3933 */       max = ((Long)this.minmax.get(0)).longValue();
/*  3934 */       break;
/*       */     case 6: case 7: 
/*  3936 */       max = Conv.convert_Integer_to_long((Integer)this.minmax.get(0));
/*  3937 */       break;
/*       */     case 8: case 9: 
/*  3939 */       max = Conv.convert_Short_to_long((Short)this.minmax.get(0));
/*  3940 */       break;
/*       */     case 10: case 11: 
/*  3942 */       max = Conv.convert_Byte_to_long((Byte)this.minmax.get(0));
/*  3943 */       break;
/*  3944 */     case 12:  max = Conv.convert_BigDecimal_to_long((BigDecimal)this.minmax.get(0));
/*  3945 */       break;
/*  3946 */     case 13:  max = Conv.convert_BigInteger_to_long((BigInteger)this.minmax.get(0));
/*  3947 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3952 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3953 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3955 */     Conv.restoreMessages();
/*  3956 */     return max;
/*       */   }
/*       */   
/*       */   public Long maximum_as_Long()
/*       */   {
/*  3961 */     return getMaximum_as_Long();
/*       */   }
/*       */   
/*       */   public Long getMaximum_as_Long() {
/*  3965 */     if (this.suppressMessages) Conv.suppressMessages();
/*  3966 */     Long max = new Long(0L);
/*  3967 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  3969 */       max = Conv.convert_Double_to_Long((Double)this.minmax.get(0));
/*  3970 */       break;
/*       */     case 2: case 3: 
/*  3972 */       max = Conv.convert_Float_to_Long((Float)this.minmax.get(0));
/*  3973 */       break;
/*       */     case 4: case 5: 
/*  3975 */       max = (Long)this.minmax.get(0);
/*  3976 */       break;
/*       */     case 6: case 7: 
/*  3978 */       max = Conv.convert_Integer_to_Long((Integer)this.minmax.get(0));
/*  3979 */       break;
/*       */     case 8: case 9: 
/*  3981 */       max = Conv.convert_Short_to_Long((Short)this.minmax.get(0));
/*  3982 */       break;
/*       */     case 10: case 11: 
/*  3984 */       max = Conv.convert_Byte_to_Long((Byte)this.minmax.get(0));
/*  3985 */       break;
/*  3986 */     case 12:  max = Conv.convert_BigDecimal_to_Long((BigDecimal)this.minmax.get(0));
/*  3987 */       break;
/*  3988 */     case 13:  max = Conv.convert_BigInteger_to_Long((BigInteger)this.minmax.get(0));
/*  3989 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  3994 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  3995 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  3997 */     Conv.restoreMessages();
/*  3998 */     return max;
/*       */   }
/*       */   
/*       */   public int maximum_as_int()
/*       */   {
/*  4003 */     return getMaximum_as_int();
/*       */   }
/*       */   
/*       */   public int getMaximum_as_int() {
/*  4007 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4008 */     int max = 0;
/*  4009 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4011 */       max = Conv.convert_Double_to_int((Double)this.minmax.get(0));
/*  4012 */       break;
/*       */     case 2: case 3: 
/*  4014 */       max = Conv.convert_Float_to_int((Float)this.minmax.get(0));
/*  4015 */       break;
/*       */     case 4: case 5: 
/*  4017 */       max = Conv.convert_Long_to_int((Long)this.minmax.get(0));
/*  4018 */       break;
/*       */     case 6: case 7: 
/*  4020 */       max = ((Integer)this.minmax.get(0)).intValue();
/*  4021 */       break;
/*       */     case 8: case 9: 
/*  4023 */       max = Conv.convert_Short_to_int((Short)this.minmax.get(0));
/*  4024 */       break;
/*       */     case 10: case 11: 
/*  4026 */       max = Conv.convert_Byte_to_int((Byte)this.minmax.get(0));
/*  4027 */       break;
/*  4028 */     case 12:  max = Conv.convert_BigDecimal_to_int((BigDecimal)this.minmax.get(0));
/*  4029 */       break;
/*  4030 */     case 13:  max = Conv.convert_BigInteger_to_int((BigInteger)this.minmax.get(0));
/*  4031 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4036 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4037 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4039 */     Conv.restoreMessages();
/*  4040 */     return max;
/*       */   }
/*       */   
/*       */   public Integer maximum_as_Integer()
/*       */   {
/*  4045 */     return getMaximum_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getMaximum_as_Integer() {
/*  4049 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4050 */     Integer max = new Integer(0);
/*  4051 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4053 */       max = Conv.convert_Double_to_Integer((Double)this.minmax.get(0));
/*  4054 */       break;
/*       */     case 2: case 3: 
/*  4056 */       max = Conv.convert_Float_to_Integer((Float)this.minmax.get(0));
/*  4057 */       break;
/*       */     case 4: case 5: 
/*  4059 */       max = Conv.convert_Long_to_Integer((Long)this.minmax.get(0));
/*  4060 */       break;
/*       */     case 6: case 7: 
/*  4062 */       max = (Integer)this.minmax.get(0);
/*  4063 */       break;
/*       */     case 8: case 9: 
/*  4065 */       max = Conv.convert_Short_to_Integer((Short)this.minmax.get(0));
/*  4066 */       break;
/*       */     case 10: case 11: 
/*  4068 */       max = Conv.convert_Byte_to_Integer((Byte)this.minmax.get(0));
/*  4069 */       break;
/*  4070 */     case 12:  max = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.minmax.get(0));
/*  4071 */       break;
/*  4072 */     case 13:  max = Conv.convert_BigInteger_to_Integer((BigInteger)this.minmax.get(0));
/*  4073 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4078 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4079 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4081 */     Conv.restoreMessages();
/*  4082 */     return max;
/*       */   }
/*       */   
/*       */   public short maximum_as_short()
/*       */   {
/*  4087 */     return getMaximum_as_short();
/*       */   }
/*       */   
/*       */   public short getMaximum_as_short() {
/*  4091 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4092 */     short max = 0;
/*  4093 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4095 */       max = Conv.convert_Double_to_short((Double)this.minmax.get(0));
/*  4096 */       break;
/*       */     case 2: case 3: 
/*  4098 */       max = Conv.convert_Float_to_short((Float)this.minmax.get(0));
/*  4099 */       break;
/*       */     case 4: case 5: 
/*  4101 */       max = Conv.convert_Long_to_short((Long)this.minmax.get(0));
/*  4102 */       break;
/*       */     case 6: case 7: 
/*  4104 */       max = Conv.convert_Integer_to_short((Integer)this.minmax.get(0));
/*  4105 */       break;
/*       */     case 8: case 9: 
/*  4107 */       max = ((Short)this.minmax.get(0)).shortValue();
/*  4108 */       break;
/*       */     case 10: case 11: 
/*  4110 */       max = Conv.convert_Byte_to_short((Byte)this.minmax.get(0));
/*  4111 */       break;
/*  4112 */     case 12:  max = Conv.convert_BigDecimal_to_short((BigDecimal)this.minmax.get(0));
/*  4113 */       break;
/*  4114 */     case 13:  max = Conv.convert_BigInteger_to_short((BigInteger)this.minmax.get(0));
/*  4115 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4120 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4121 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4123 */     Conv.restoreMessages();
/*  4124 */     return max;
/*       */   }
/*       */   
/*       */   public Short maximum_as_Short()
/*       */   {
/*  4129 */     return getMaximum_as_Short();
/*       */   }
/*       */   
/*       */   public Short getMaximum_as_Short() {
/*  4133 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4134 */     Short max = new Short((short)0);
/*  4135 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4137 */       max = Conv.convert_Double_to_Short((Double)this.minmax.get(0));
/*  4138 */       break;
/*       */     case 2: case 3: 
/*  4140 */       max = Conv.convert_Float_to_Short((Float)this.minmax.get(0));
/*  4141 */       break;
/*       */     case 4: case 5: 
/*  4143 */       max = Conv.convert_Long_to_Short((Long)this.minmax.get(0));
/*  4144 */       break;
/*       */     case 6: case 7: 
/*  4146 */       max = Conv.convert_Integer_to_Short((Integer)this.minmax.get(0));
/*  4147 */       break;
/*       */     case 8: case 9: 
/*  4149 */       max = (Short)this.minmax.get(0);
/*  4150 */       break;
/*       */     case 10: case 11: 
/*  4152 */       max = Conv.convert_Byte_to_Short((Byte)this.minmax.get(0));
/*  4153 */       break;
/*  4154 */     case 12:  max = Conv.convert_BigDecimal_to_Short((BigDecimal)this.minmax.get(0));
/*  4155 */       break;
/*  4156 */     case 13:  max = Conv.convert_BigInteger_to_Short((BigInteger)this.minmax.get(0));
/*  4157 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4162 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4163 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4165 */     Conv.restoreMessages();
/*  4166 */     return max;
/*       */   }
/*       */   
/*       */   public byte maximum_as_byte()
/*       */   {
/*  4171 */     return getMaximum_as_byte();
/*       */   }
/*       */   
/*       */   public byte getMaximum_as_byte() {
/*  4175 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4176 */     byte max = 0;
/*  4177 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4179 */       max = Conv.convert_Double_to_byte((Double)this.minmax.get(0));
/*  4180 */       break;
/*       */     case 2: case 3: 
/*  4182 */       max = Conv.convert_Float_to_byte((Float)this.minmax.get(0));
/*  4183 */       break;
/*       */     case 4: case 5: 
/*  4185 */       max = Conv.convert_Long_to_byte((Long)this.minmax.get(0));
/*  4186 */       break;
/*       */     case 6: case 7: 
/*  4188 */       max = Conv.convert_Integer_to_byte((Integer)this.minmax.get(0));
/*  4189 */       break;
/*       */     case 8: case 9: 
/*  4191 */       max = Conv.convert_Short_to_byte((Short)this.minmax.get(0));
/*  4192 */       break;
/*       */     case 10: case 11: 
/*  4194 */       max = ((Byte)this.minmax.get(0)).byteValue();
/*  4195 */       break;
/*  4196 */     case 12:  max = Conv.convert_BigDecimal_to_byte((BigDecimal)this.minmax.get(0));
/*  4197 */       break;
/*  4198 */     case 13:  max = Conv.convert_BigInteger_to_byte((BigInteger)this.minmax.get(0));
/*  4199 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4204 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4205 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4207 */     Conv.restoreMessages();
/*  4208 */     return max;
/*       */   }
/*       */   
/*       */   public Byte maximum_as_Byte()
/*       */   {
/*  4213 */     return getMaximum_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getMaximum_as_Byte() {
/*  4217 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4218 */     Byte max = new Byte((byte)0);
/*  4219 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4221 */       max = Conv.convert_Double_to_Byte((Double)this.minmax.get(0));
/*  4222 */       break;
/*       */     case 2: case 3: 
/*  4224 */       max = Conv.convert_Float_to_Byte((Float)this.minmax.get(0));
/*  4225 */       break;
/*       */     case 4: case 5: 
/*  4227 */       max = Conv.convert_Long_to_Byte((Long)this.minmax.get(0));
/*  4228 */       break;
/*       */     case 6: case 7: 
/*  4230 */       max = Conv.convert_Integer_to_Byte((Integer)this.minmax.get(0));
/*  4231 */       break;
/*       */     case 8: case 9: 
/*  4233 */       max = Conv.convert_Short_to_Byte((Short)this.minmax.get(0));
/*  4234 */       break;
/*       */     case 10: case 11: 
/*  4236 */       max = (Byte)this.minmax.get(0);
/*  4237 */       break;
/*  4238 */     case 12:  max = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.minmax.get(0));
/*  4239 */       break;
/*  4240 */     case 13:  max = Conv.convert_BigInteger_to_Byte((BigInteger)this.minmax.get(0));
/*  4241 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4246 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4247 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4249 */     Conv.restoreMessages();
/*  4250 */     return max;
/*       */   }
/*       */   
/*       */   public BigDecimal maximum_as_BigDecimal()
/*       */   {
/*  4255 */     return getMaximum_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getMaximum_as_BigDecimal() {
/*  4259 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4260 */     BigDecimal max = new BigDecimal(0.0D);
/*  4261 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4263 */       max = Conv.convert_Double_to_BigDecimal((Double)this.minmax.get(0));
/*  4264 */       break;
/*       */     case 2: case 3: 
/*  4266 */       max = Conv.convert_Float_to_BigDecimal((Float)this.minmax.get(0));
/*  4267 */       break;
/*       */     case 4: case 5: 
/*  4269 */       max = Conv.convert_Long_to_BigDecimal((Long)this.minmax.get(0));
/*  4270 */       break;
/*       */     case 6: case 7: 
/*  4272 */       max = Conv.convert_Integer_to_BigDecimal((Integer)this.minmax.get(0));
/*  4273 */       break;
/*       */     case 8: case 9: 
/*  4275 */       max = Conv.convert_Short_to_BigDecimal((Short)this.minmax.get(0));
/*  4276 */       break;
/*       */     case 10: case 11: 
/*  4278 */       max = Conv.convert_Byte_to_BigDecimal((Byte)this.minmax.get(0));
/*  4279 */       break;
/*  4280 */     case 12:  max = (BigDecimal)this.minmax.get(0);
/*  4281 */       break;
/*  4282 */     case 13:  max = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.minmax.get(0));
/*  4283 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4288 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4289 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4291 */     Conv.restoreMessages();
/*  4292 */     return max;
/*       */   }
/*       */   
/*       */   public BigInteger maximum_as_BigInteger()
/*       */   {
/*  4297 */     return getMaximum_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getMaximum_as_BigInteger() {
/*  4301 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4302 */     BigInteger max = new BigInteger("0");
/*  4303 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4305 */       max = Conv.convert_Double_to_BigInteger((Double)this.minmax.get(0));
/*  4306 */       break;
/*       */     case 2: case 3: 
/*  4308 */       max = Conv.convert_Float_to_BigInteger((Float)this.minmax.get(0));
/*  4309 */       break;
/*       */     case 4: case 5: 
/*  4311 */       max = Conv.convert_Long_to_BigInteger((Long)this.minmax.get(0));
/*  4312 */       break;
/*       */     case 6: case 7: 
/*  4314 */       max = Conv.convert_Integer_to_BigInteger((Integer)this.minmax.get(0));
/*  4315 */       break;
/*       */     case 8: case 9: 
/*  4317 */       max = Conv.convert_Short_to_BigInteger((Short)this.minmax.get(0));
/*  4318 */       break;
/*       */     case 10: case 11: 
/*  4320 */       max = Conv.convert_Byte_to_BigInteger((Byte)this.minmax.get(0));
/*  4321 */       break;
/*  4322 */     case 12:  max = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.minmax.get(0));
/*  4323 */       break;
/*  4324 */     case 13:  max = (BigInteger)this.minmax.get(0);
/*  4325 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4330 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
/*  4331 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4333 */     Conv.restoreMessages();
/*  4334 */     return max;
/*       */   }
/*       */   
/*       */ 
/*       */   public char maximum_as_char()
/*       */   {
/*  4340 */     return getMaximum_as_char();
/*       */   }
/*       */   
/*       */   public char getMaximum_as_char() {
/*  4344 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4345 */     char max = '\000';
/*  4346 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  4348 */       max = (char)((Integer)this.minmax.get(1)).intValue();
/*  4349 */       break;
/*       */     case 16: case 17: 
/*  4351 */       max = ((Character)this.minmax.get(1)).charValue();
/*  4352 */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 18: 
/*  4367 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a char type maximum is meaningful/supported");
/*  4368 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4370 */     Conv.restoreMessages();
/*  4371 */     return max;
/*       */   }
/*       */   
/*       */   public Character maximum_as_Character()
/*       */   {
/*  4376 */     return getMaximum_as_Character();
/*       */   }
/*       */   
/*       */   public Character getMaximum_as_Character() {
/*  4380 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4381 */     Character max = new Character('\000');
/*  4382 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  4384 */       max = new Character((char)((Integer)this.minmax.get(1)).intValue());
/*  4385 */       break;
/*       */     case 16: case 17: 
/*  4387 */       max = (Character)this.minmax.get(1);
/*  4388 */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 18: 
/*  4403 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a Character type maximum is meaningful/supported");
/*  4404 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4406 */     Conv.restoreMessages();
/*  4407 */     return max;
/*       */   }
/*       */   
/*       */   public double minimum()
/*       */   {
/*  4412 */     return getMinimum_as_double();
/*       */   }
/*       */   
/*       */   public double minimum_as_double() {
/*  4416 */     return getMinimum_as_double();
/*       */   }
/*       */   
/*       */   public double getMinimum() {
/*  4420 */     return getMinimum_as_double();
/*       */   }
/*       */   
/*       */   public double getMinimum_as_double() {
/*  4424 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4425 */     double min = 0.0D;
/*  4426 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4428 */       min = ((Double)this.minmax.get(1)).doubleValue();
/*  4429 */       break;
/*       */     case 2: case 3: 
/*  4431 */       min = Conv.convert_Float_to_double((Float)this.minmax.get(1));
/*  4432 */       break;
/*       */     case 4: case 5: 
/*  4434 */       min = Conv.convert_Long_to_double((Long)this.minmax.get(1));
/*  4435 */       break;
/*       */     case 6: case 7: 
/*  4437 */       min = Conv.convert_Integer_to_double((Integer)this.minmax.get(1));
/*  4438 */       break;
/*       */     case 8: case 9: 
/*  4440 */       min = Conv.convert_Short_to_double((Short)this.minmax.get(1));
/*  4441 */       break;
/*       */     case 10: case 11: 
/*  4443 */       min = Conv.convert_Byte_to_double((Byte)this.minmax.get(1));
/*  4444 */       break;
/*  4445 */     case 12:  min = Conv.convert_BigDecimal_to_double((BigDecimal)this.minmax.get(1));
/*  4446 */       break;
/*  4447 */     case 13:  min = Conv.convert_BigInteger_to_double((BigInteger)this.minmax.get(1));
/*  4448 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4453 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4454 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4456 */     Conv.restoreMessages();
/*  4457 */     return min;
/*       */   }
/*       */   
/*       */   public Double minimum_as_Double()
/*       */   {
/*  4462 */     return getMinimum_as_Double();
/*       */   }
/*       */   
/*       */   public Double getMinimum_as_Double() {
/*  4466 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4467 */     Double min = new Double(0.0D);
/*  4468 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4470 */       min = (Double)this.minmax.get(1);
/*  4471 */       break;
/*       */     case 2: case 3: 
/*  4473 */       min = Conv.convert_Float_to_Double((Float)this.minmax.get(1));
/*  4474 */       break;
/*       */     case 4: case 5: 
/*  4476 */       min = Conv.convert_Long_to_Double((Long)this.minmax.get(1));
/*  4477 */       break;
/*       */     case 6: case 7: 
/*  4479 */       min = Conv.convert_Integer_to_Double((Integer)this.minmax.get(1));
/*  4480 */       break;
/*       */     case 8: case 9: 
/*  4482 */       min = Conv.convert_Short_to_Double((Short)this.minmax.get(1));
/*  4483 */       break;
/*       */     case 10: case 11: 
/*  4485 */       min = Conv.convert_Byte_to_Double((Byte)this.minmax.get(1));
/*  4486 */       break;
/*  4487 */     case 12:  min = Conv.convert_BigDecimal_to_Double((BigDecimal)this.minmax.get(1));
/*  4488 */       break;
/*  4489 */     case 13:  min = Conv.convert_BigInteger_to_Double((BigInteger)this.minmax.get(1));
/*  4490 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4495 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4496 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4498 */     Conv.restoreMessages();
/*  4499 */     return min;
/*       */   }
/*       */   
/*       */   public Float minimum_as_Float()
/*       */   {
/*  4504 */     return getMinimum_as_Float();
/*       */   }
/*       */   
/*       */   public Float getMinimum_as_Float() {
/*  4508 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4509 */     Float min = new Float(0.0D);
/*  4510 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4512 */       min = Conv.convert_Double_to_Float((Double)this.minmax.get(1));
/*  4513 */       break;
/*       */     case 2: case 3: 
/*  4515 */       min = (Float)this.minmax.get(1);
/*  4516 */       break;
/*       */     case 4: case 5: 
/*  4518 */       min = Conv.convert_Long_to_Float((Long)this.minmax.get(1));
/*  4519 */       break;
/*       */     case 6: case 7: 
/*  4521 */       min = Conv.convert_Integer_to_Float((Integer)this.minmax.get(1));
/*  4522 */       break;
/*       */     case 8: case 9: 
/*  4524 */       min = Conv.convert_Short_to_Float((Short)this.minmax.get(1));
/*  4525 */       break;
/*       */     case 10: case 11: 
/*  4527 */       min = Conv.convert_Byte_to_Float((Byte)this.minmax.get(1));
/*  4528 */       break;
/*  4529 */     case 12:  min = Conv.convert_BigDecimal_to_Float((BigDecimal)this.minmax.get(1));
/*  4530 */       break;
/*  4531 */     case 13:  min = Conv.convert_BigInteger_to_Float((BigInteger)this.minmax.get(1));
/*  4532 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4537 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4538 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4540 */     Conv.restoreMessages();
/*  4541 */     return min;
/*       */   }
/*       */   
/*       */   public float minimum_as_float()
/*       */   {
/*  4546 */     return getMinimum_as_float();
/*       */   }
/*       */   
/*       */   public float getMinimum_as_float() {
/*  4550 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4551 */     float min = 0.0F;
/*  4552 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4554 */       min = Conv.convert_Double_to_float((Double)this.minmax.get(1));
/*  4555 */       break;
/*       */     case 2: case 3: 
/*  4557 */       min = ((Float)this.minmax.get(1)).floatValue();
/*  4558 */       break;
/*       */     case 4: case 5: 
/*  4560 */       min = Conv.convert_Long_to_float((Long)this.minmax.get(1));
/*  4561 */       break;
/*       */     case 6: case 7: 
/*  4563 */       min = Conv.convert_Integer_to_float((Integer)this.minmax.get(1));
/*  4564 */       break;
/*       */     case 8: case 9: 
/*  4566 */       min = Conv.convert_Short_to_float((Short)this.minmax.get(1));
/*  4567 */       break;
/*       */     case 10: case 11: 
/*  4569 */       min = Conv.convert_Byte_to_float((Byte)this.minmax.get(1));
/*  4570 */       break;
/*  4571 */     case 12:  min = Conv.convert_BigDecimal_to_float((BigDecimal)this.minmax.get(1));
/*  4572 */       break;
/*  4573 */     case 13:  min = Conv.convert_BigInteger_to_float((BigInteger)this.minmax.get(1));
/*  4574 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4579 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4580 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4582 */     Conv.restoreMessages();
/*  4583 */     return min;
/*       */   }
/*       */   
/*       */   public long minimum_as_long()
/*       */   {
/*  4588 */     return getMinimum_as_long();
/*       */   }
/*       */   
/*       */   public long getMinimum_as_long() {
/*  4592 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4593 */     long min = 0L;
/*  4594 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4596 */       min = Conv.convert_Double_to_long((Double)this.minmax.get(1));
/*  4597 */       break;
/*       */     case 2: case 3: 
/*  4599 */       min = Conv.convert_Float_to_long((Float)this.minmax.get(1));
/*  4600 */       break;
/*       */     case 4: case 5: 
/*  4602 */       min = ((Long)this.minmax.get(1)).longValue();
/*  4603 */       break;
/*       */     case 6: case 7: 
/*  4605 */       min = Conv.convert_Integer_to_long((Integer)this.minmax.get(1));
/*  4606 */       break;
/*       */     case 8: case 9: 
/*  4608 */       min = Conv.convert_Short_to_long((Short)this.minmax.get(1));
/*  4609 */       break;
/*       */     case 10: case 11: 
/*  4611 */       min = Conv.convert_Byte_to_long((Byte)this.minmax.get(1));
/*  4612 */       break;
/*  4613 */     case 12:  min = Conv.convert_BigDecimal_to_long((BigDecimal)this.minmax.get(1));
/*  4614 */       break;
/*  4615 */     case 13:  min = Conv.convert_BigInteger_to_long((BigInteger)this.minmax.get(1));
/*  4616 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4621 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4622 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4624 */     Conv.restoreMessages();
/*  4625 */     return min;
/*       */   }
/*       */   
/*       */   public Long minimum_as_Long()
/*       */   {
/*  4630 */     return getMinimum_as_Long();
/*       */   }
/*       */   
/*       */   public Long getMinimum_as_Long() {
/*  4634 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4635 */     Long min = new Long(0L);
/*  4636 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4638 */       min = Conv.convert_Double_to_Long((Double)this.minmax.get(1));
/*  4639 */       break;
/*       */     case 2: case 3: 
/*  4641 */       min = Conv.convert_Float_to_Long((Float)this.minmax.get(1));
/*  4642 */       break;
/*       */     case 4: case 5: 
/*  4644 */       min = (Long)this.minmax.get(1);
/*  4645 */       break;
/*       */     case 6: case 7: 
/*  4647 */       min = Conv.convert_Integer_to_Long((Integer)this.minmax.get(1));
/*  4648 */       break;
/*       */     case 8: case 9: 
/*  4650 */       min = Conv.convert_Short_to_Long((Short)this.minmax.get(1));
/*  4651 */       break;
/*       */     case 10: case 11: 
/*  4653 */       min = Conv.convert_Byte_to_Long((Byte)this.minmax.get(1));
/*  4654 */       break;
/*  4655 */     case 12:  min = Conv.convert_BigDecimal_to_Long((BigDecimal)this.minmax.get(1));
/*  4656 */       break;
/*  4657 */     case 13:  min = Conv.convert_BigInteger_to_Long((BigInteger)this.minmax.get(1));
/*  4658 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4663 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4664 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4666 */     Conv.restoreMessages();
/*  4667 */     return min;
/*       */   }
/*       */   
/*       */   public int minimum_as_int()
/*       */   {
/*  4672 */     return getMinimum_as_int();
/*       */   }
/*       */   
/*       */   public int getMinimum_as_int() {
/*  4676 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4677 */     int min = 0;
/*  4678 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4680 */       min = Conv.convert_Double_to_int((Double)this.minmax.get(1));
/*  4681 */       break;
/*       */     case 2: case 3: 
/*  4683 */       min = Conv.convert_Float_to_int((Float)this.minmax.get(1));
/*  4684 */       break;
/*       */     case 4: case 5: 
/*  4686 */       min = Conv.convert_Long_to_int((Long)this.minmax.get(1));
/*  4687 */       break;
/*       */     case 6: case 7: 
/*  4689 */       min = ((Integer)this.minmax.get(1)).intValue();
/*  4690 */       break;
/*       */     case 8: case 9: 
/*  4692 */       min = Conv.convert_Short_to_int((Short)this.minmax.get(1));
/*  4693 */       break;
/*       */     case 10: case 11: 
/*  4695 */       min = Conv.convert_Byte_to_int((Byte)this.minmax.get(1));
/*  4696 */       break;
/*  4697 */     case 12:  min = Conv.convert_BigDecimal_to_int((BigDecimal)this.minmax.get(1));
/*  4698 */       break;
/*  4699 */     case 13:  min = Conv.convert_BigInteger_to_int((BigInteger)this.minmax.get(1));
/*  4700 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4705 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4706 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4708 */     Conv.restoreMessages();
/*  4709 */     return min;
/*       */   }
/*       */   
/*       */   public Integer minimum_as_Integer()
/*       */   {
/*  4714 */     return getMinimum_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getMinimum_as_Integer() {
/*  4718 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4719 */     Integer min = new Integer(0);
/*  4720 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4722 */       min = Conv.convert_Double_to_Integer((Double)this.minmax.get(1));
/*  4723 */       break;
/*       */     case 2: case 3: 
/*  4725 */       min = Conv.convert_Float_to_Integer((Float)this.minmax.get(1));
/*  4726 */       break;
/*       */     case 4: case 5: 
/*  4728 */       min = Conv.convert_Long_to_Integer((Long)this.minmax.get(1));
/*  4729 */       break;
/*       */     case 6: case 7: 
/*  4731 */       min = (Integer)this.minmax.get(1);
/*  4732 */       break;
/*       */     case 8: case 9: 
/*  4734 */       min = Conv.convert_Short_to_Integer((Short)this.minmax.get(1));
/*  4735 */       break;
/*       */     case 10: case 11: 
/*  4737 */       min = Conv.convert_Byte_to_Integer((Byte)this.minmax.get(1));
/*  4738 */       break;
/*  4739 */     case 12:  min = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.minmax.get(1));
/*  4740 */       break;
/*  4741 */     case 13:  min = Conv.convert_BigInteger_to_Integer((BigInteger)this.minmax.get(1));
/*  4742 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4747 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4748 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4750 */     Conv.restoreMessages();
/*  4751 */     return min;
/*       */   }
/*       */   
/*       */   public short minimum_as_short()
/*       */   {
/*  4756 */     return getMinimum_as_short();
/*       */   }
/*       */   
/*       */   public short getMinimum_as_short() {
/*  4760 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4761 */     short min = 0;
/*  4762 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4764 */       min = Conv.convert_Double_to_short((Double)this.minmax.get(1));
/*  4765 */       break;
/*       */     case 2: case 3: 
/*  4767 */       min = Conv.convert_Float_to_short((Float)this.minmax.get(1));
/*  4768 */       break;
/*       */     case 4: case 5: 
/*  4770 */       min = Conv.convert_Long_to_short((Long)this.minmax.get(1));
/*  4771 */       break;
/*       */     case 6: case 7: 
/*  4773 */       min = Conv.convert_Integer_to_short((Integer)this.minmax.get(1));
/*  4774 */       break;
/*       */     case 8: case 9: 
/*  4776 */       min = ((Short)this.minmax.get(1)).shortValue();
/*  4777 */       break;
/*       */     case 10: case 11: 
/*  4779 */       min = Conv.convert_Byte_to_short((Byte)this.minmax.get(1));
/*  4780 */       break;
/*  4781 */     case 12:  min = Conv.convert_BigDecimal_to_short((BigDecimal)this.minmax.get(1));
/*  4782 */       break;
/*  4783 */     case 13:  min = Conv.convert_BigInteger_to_short((BigInteger)this.minmax.get(1));
/*  4784 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4789 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4790 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4792 */     Conv.restoreMessages();
/*  4793 */     return min;
/*       */   }
/*       */   
/*       */   public Short minimum_as_Short()
/*       */   {
/*  4798 */     return getMinimum_as_Short();
/*       */   }
/*       */   
/*       */   public Short getMinimum_as_Short() {
/*  4802 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4803 */     Short min = new Short((short)0);
/*  4804 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4806 */       min = Conv.convert_Double_to_Short((Double)this.minmax.get(1));
/*  4807 */       break;
/*       */     case 2: case 3: 
/*  4809 */       min = Conv.convert_Float_to_Short((Float)this.minmax.get(1));
/*  4810 */       break;
/*       */     case 4: case 5: 
/*  4812 */       min = Conv.convert_Long_to_Short((Long)this.minmax.get(1));
/*  4813 */       break;
/*       */     case 6: case 7: 
/*  4815 */       min = Conv.convert_Integer_to_Short((Integer)this.minmax.get(1));
/*  4816 */       break;
/*       */     case 8: case 9: 
/*  4818 */       min = (Short)this.minmax.get(1);
/*  4819 */       break;
/*       */     case 10: case 11: 
/*  4821 */       min = Conv.convert_Byte_to_Short((Byte)this.minmax.get(1));
/*  4822 */       break;
/*  4823 */     case 12:  min = Conv.convert_BigDecimal_to_Short((BigDecimal)this.minmax.get(1));
/*  4824 */       break;
/*  4825 */     case 13:  min = Conv.convert_BigInteger_to_Short((BigInteger)this.minmax.get(1));
/*  4826 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4831 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4832 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4834 */     Conv.restoreMessages();
/*  4835 */     return min;
/*       */   }
/*       */   
/*       */   public byte minimum_as_byte()
/*       */   {
/*  4840 */     return getMinimum_as_byte();
/*       */   }
/*       */   
/*       */   public byte getMinimum_as_byte() {
/*  4844 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4845 */     byte min = 0;
/*  4846 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4848 */       min = Conv.convert_Double_to_byte((Double)this.minmax.get(1));
/*  4849 */       break;
/*       */     case 2: case 3: 
/*  4851 */       min = Conv.convert_Float_to_byte((Float)this.minmax.get(1));
/*  4852 */       break;
/*       */     case 4: case 5: 
/*  4854 */       min = Conv.convert_Long_to_byte((Long)this.minmax.get(1));
/*  4855 */       break;
/*       */     case 6: case 7: 
/*  4857 */       min = Conv.convert_Integer_to_byte((Integer)this.minmax.get(1));
/*  4858 */       break;
/*       */     case 8: case 9: 
/*  4860 */       min = Conv.convert_Short_to_byte((Short)this.minmax.get(1));
/*  4861 */       break;
/*       */     case 10: case 11: 
/*  4863 */       min = ((Byte)this.minmax.get(1)).byteValue();
/*  4864 */       break;
/*  4865 */     case 12:  min = Conv.convert_BigDecimal_to_byte((BigDecimal)this.minmax.get(1));
/*  4866 */       break;
/*  4867 */     case 13:  min = Conv.convert_BigInteger_to_byte((BigInteger)this.minmax.get(1));
/*  4868 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4873 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4874 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4876 */     Conv.restoreMessages();
/*  4877 */     return min;
/*       */   }
/*       */   
/*       */   public Byte minimum_as_Byte()
/*       */   {
/*  4882 */     return getMinimum_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getMinimum_as_Byte() {
/*  4886 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4887 */     Byte min = new Byte((byte)0);
/*  4888 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4890 */       min = Conv.convert_Double_to_Byte((Double)this.minmax.get(1));
/*  4891 */       break;
/*       */     case 2: case 3: 
/*  4893 */       min = Conv.convert_Float_to_Byte((Float)this.minmax.get(1));
/*  4894 */       break;
/*       */     case 4: case 5: 
/*  4896 */       min = Conv.convert_Long_to_Byte((Long)this.minmax.get(1));
/*  4897 */       break;
/*       */     case 6: case 7: 
/*  4899 */       min = Conv.convert_Integer_to_Byte((Integer)this.minmax.get(1));
/*  4900 */       break;
/*       */     case 8: case 9: 
/*  4902 */       min = Conv.convert_Short_to_Byte((Short)this.minmax.get(1));
/*  4903 */       break;
/*       */     case 10: case 11: 
/*  4905 */       min = (Byte)this.minmax.get(1);
/*  4906 */       break;
/*  4907 */     case 12:  min = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.minmax.get(1));
/*  4908 */       break;
/*  4909 */     case 13:  min = Conv.convert_BigInteger_to_Byte((BigInteger)this.minmax.get(1));
/*  4910 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4915 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4916 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4918 */     Conv.restoreMessages();
/*  4919 */     return min;
/*       */   }
/*       */   
/*       */   public BigDecimal minimum_as_BigDecimal()
/*       */   {
/*  4924 */     return getMinimum_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getMinimum_as_BigDecimal() {
/*  4928 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4929 */     BigDecimal min = new BigDecimal(0.0D);
/*  4930 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4932 */       min = Conv.convert_Double_to_BigDecimal((Double)this.minmax.get(1));
/*  4933 */       break;
/*       */     case 2: case 3: 
/*  4935 */       min = Conv.convert_Float_to_BigDecimal((Float)this.minmax.get(1));
/*  4936 */       break;
/*       */     case 4: case 5: 
/*  4938 */       min = Conv.convert_Long_to_BigDecimal((Long)this.minmax.get(1));
/*  4939 */       break;
/*       */     case 6: case 7: 
/*  4941 */       min = Conv.convert_Integer_to_BigDecimal((Integer)this.minmax.get(1));
/*  4942 */       break;
/*       */     case 8: case 9: 
/*  4944 */       min = Conv.convert_Short_to_BigDecimal((Short)this.minmax.get(1));
/*  4945 */       break;
/*       */     case 10: case 11: 
/*  4947 */       min = Conv.convert_Byte_to_BigDecimal((Byte)this.minmax.get(1));
/*  4948 */       break;
/*  4949 */     case 12:  min = (BigDecimal)this.minmax.get(1);
/*  4950 */       break;
/*  4951 */     case 13:  min = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.minmax.get(1));
/*  4952 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4957 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  4958 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  4960 */     Conv.restoreMessages();
/*  4961 */     return min;
/*       */   }
/*       */   
/*       */   public BigInteger minimum_as_BigInteger()
/*       */   {
/*  4966 */     return getMinimum_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getMinimum_as_BigInteger() {
/*  4970 */     if (this.suppressMessages) Conv.suppressMessages();
/*  4971 */     BigInteger min = new BigInteger("0");
/*  4972 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  4974 */       min = Conv.convert_Double_to_BigInteger((Double)this.minmax.get(1));
/*  4975 */       break;
/*       */     case 2: case 3: 
/*  4977 */       min = Conv.convert_Float_to_BigInteger((Float)this.minmax.get(1));
/*  4978 */       break;
/*       */     case 4: case 5: 
/*  4980 */       min = Conv.convert_Long_to_BigInteger((Long)this.minmax.get(1));
/*  4981 */       break;
/*       */     case 6: case 7: 
/*  4983 */       min = Conv.convert_Integer_to_BigInteger((Integer)this.minmax.get(1));
/*  4984 */       break;
/*       */     case 8: case 9: 
/*  4986 */       min = Conv.convert_Short_to_BigInteger((Short)this.minmax.get(1));
/*  4987 */       break;
/*       */     case 10: case 11: 
/*  4989 */       min = Conv.convert_Byte_to_BigInteger((Byte)this.minmax.get(1));
/*  4990 */       break;
/*  4991 */     case 12:  min = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.minmax.get(1));
/*  4992 */       break;
/*  4993 */     case 13:  min = (BigInteger)this.minmax.get(1);
/*  4994 */       break;
/*       */     case 14: case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  4999 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
/*  5000 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5002 */     Conv.restoreMessages();
/*  5003 */     return min;
/*       */   }
/*       */   
/*       */   public char minimum_as_char()
/*       */   {
/*  5008 */     return getMinimum_as_char();
/*       */   }
/*       */   
/*       */   public char getMinimum_as_char() {
/*  5012 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5013 */     char min = '\000';
/*  5014 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  5016 */       min = (char)((Integer)this.minmax.get(1)).intValue();
/*  5017 */       break;
/*       */     case 16: case 17: 
/*  5019 */       min = ((Character)this.minmax.get(1)).charValue();
/*  5020 */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 18: 
/*  5035 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a char type minimum is meaningful/supported");
/*  5036 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5038 */     Conv.restoreMessages();
/*  5039 */     return min;
/*       */   }
/*       */   
/*       */   public Character minimum_as_Character()
/*       */   {
/*  5044 */     return getMinimum_as_Character();
/*       */   }
/*       */   
/*       */   public Character getMinimum_as_Character() {
/*  5048 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5049 */     Character min = new Character('\000');
/*  5050 */     switch (this.type) {
/*       */     case 6: case 7: 
/*  5052 */       min = new Character((char)((Integer)this.minmax.get(1)).intValue());
/*  5053 */       break;
/*       */     case 16: case 17: 
/*  5055 */       min = (Character)this.minmax.get(1);
/*  5056 */       break;
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 18: 
/*  5071 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a Character type minimum is meaningful/supported");
/*  5072 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5074 */     Conv.restoreMessages();
/*  5075 */     return min;
/*       */   }
/*       */   
/*       */   public int maximumIndex()
/*       */   {
/*  5080 */     return this.maxIndex;
/*       */   }
/*       */   
/*       */   public int getMaximumIndex() {
/*  5084 */     return this.maxIndex;
/*       */   }
/*       */   
/*       */   public int minimumIndex()
/*       */   {
/*  5089 */     return this.minIndex;
/*       */   }
/*       */   
/*       */   public int getMinimumIndex() {
/*  5093 */     return this.minIndex;
/*       */   }
/*       */   
/*       */ 
/*       */   public boolean isInteger()
/*       */   {
/*  5099 */     boolean test = false;
/*  5100 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/*  5105 */       double[] arrayd = getArray_as_double();
/*  5106 */       test = Fmath.isInteger(arrayd);
/*  5107 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*  5118 */       test = true;
/*  5119 */       break;
/*  5120 */     case 12:  BigDecimal[] arraybd = getArray_as_BigDecimal();
/*  5121 */       test = Fmath.isInteger(arraybd);
/*  5122 */       break;
/*       */     case 14: case 15: 
/*  5124 */       test = false;
/*  5125 */       break;
/*  5126 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5128 */     return test;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public ArrayMaths plus(double constant)
/*       */   {
/*  5136 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5137 */     ArrayMaths am = new ArrayMaths();
/*  5138 */     am.array = new ArrayList();
/*  5139 */     am.length = this.length;
/*       */     
/*  5141 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5153 */       double[] dd = getArray_as_double();
/*  5154 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + constant));
/*  5155 */       am.type = 0;
/*  5156 */       break;
/*  5157 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5158 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5159 */         hold1 = hold1.add(new BigDecimal(constant));
/*  5160 */         am.array.add(hold1);
/*  5161 */         hold1 = null;
/*       */       }
/*  5163 */       am.type = this.type;
/*  5164 */       break;
/*  5165 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5166 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5167 */         BigDecimal hold2 = new BigDecimal(hold1).add(new BigDecimal(constant));
/*  5168 */         am.array.add(hold2);
/*  5169 */         hold1 = null;
/*  5170 */         hold2 = null;
/*       */       }
/*  5172 */       am.type = 12;
/*  5173 */       break;
/*  5174 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(constant));
/*  5175 */       am.type = this.type;
/*  5176 */       break;
/*  5177 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Complex(constant)));
/*  5178 */       am.type = this.type;
/*  5179 */       break;
/*  5180 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Double.toString(constant));
/*  5181 */       am.type = this.type;
/*  5182 */       break;
/*  5183 */     case 16:  throw new IllegalArgumentException("a double or float cannot be added to a char");
/*  5184 */     case 17:  throw new IllegalArgumentException("a double or float cannot be added to a Character");
/*  5185 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5187 */     int[] maxminIndices = new int[2];
/*  5188 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5189 */     am.maxIndex = maxminIndices[0];
/*  5190 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5192 */     Conv.restoreMessages();
/*  5193 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Double constant)
/*       */   {
/*  5198 */     return plus(constant.doubleValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(double[] arrayD)
/*       */   {
/*  5203 */     if (this.length != arrayD.length) throw new IllegalArgumentException("The length of the argument array, " + arrayD.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
/*  5204 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5205 */     ArrayMaths am = new ArrayMaths();
/*  5206 */     am.array = new ArrayList();
/*  5207 */     am.length = this.length;
/*       */     
/*  5209 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5221 */       double[] dd = getArray_as_double();
/*  5222 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + arrayD[i]));
/*  5223 */       am.type = 0;
/*  5224 */       break;
/*  5225 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5226 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5227 */         hold1 = hold1.add(new BigDecimal(arrayD[i]));
/*  5228 */         am.array.add(hold1);
/*  5229 */         hold1 = null;
/*       */       }
/*  5231 */       am.type = this.type;
/*  5232 */       break;
/*  5233 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5234 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5235 */         BigDecimal hold2 = new BigDecimal(hold1).add(new BigDecimal(arrayD[i]));
/*  5236 */         am.array.add(hold2);
/*  5237 */         hold1 = null;
/*  5238 */         hold2 = null;
/*       */       }
/*  5240 */       am.type = 12;
/*  5241 */       break;
/*  5242 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(arrayD[i]));
/*  5243 */       am.type = this.type;
/*  5244 */       break;
/*  5245 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(arrayD[i])));
/*  5246 */       am.type = this.type;
/*  5247 */       break;
/*  5248 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Double.toString(arrayD[i]));
/*  5249 */       am.type = this.type;
/*  5250 */       break;
/*  5251 */     case 16:  throw new IllegalArgumentException("a double or float cannot be added to a char");
/*  5252 */     case 17:  throw new IllegalArgumentException("a double or float cannot be added to a Character");
/*  5253 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5255 */     int[] maxminIndices = new int[2];
/*  5256 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5257 */     am.maxIndex = maxminIndices[0];
/*  5258 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5260 */     Conv.restoreMessages();
/*  5261 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Double[] arrayD) {
/*  5265 */     int nArg = arrayD.length;
/*  5266 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  5267 */     double[] arrayd = new double[this.length];
/*  5268 */     for (int i = 0; i < this.length; i++) arrayd[i] = arrayD[i].doubleValue();
/*  5269 */     return plus(arrayd);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(float constant)
/*       */   {
/*  5274 */     double constantd = constant;
/*  5275 */     return plus(constantd);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Float constant)
/*       */   {
/*  5280 */     return plus(constant.floatValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(float[] arrayF)
/*       */   {
/*  5285 */     if (this.length != arrayF.length) throw new IllegalArgumentException("The length of the argument array, " + arrayF.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
/*  5286 */     double[] arrayD = new double[this.length];
/*  5287 */     for (int i = 0; i < this.length; i++) arrayD[i] = arrayF[i];
/*  5288 */     return plus(arrayD);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Float[] arrayF)
/*       */   {
/*  5293 */     int nArg = arrayF.length;
/*  5294 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  5295 */     double[] arrayd = new double[this.length];
/*  5296 */     for (int i = 0; i < this.length; i++) arrayd[i] = arrayF[i].doubleValue();
/*  5297 */     return plus(arrayd);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(long constant)
/*       */   {
/*  5302 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5303 */     ArrayMaths am = new ArrayMaths();
/*  5304 */     am.array = new ArrayList();
/*  5305 */     am.length = this.length;
/*       */     
/*  5307 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5311 */       double[] dd = getArray_as_double();
/*  5312 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + constant));
/*  5313 */       am.type = 0;
/*  5314 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5322 */       long max = getMaximum_as_long();
/*  5323 */       long[] ll = getArray_as_long();
/*  5324 */       if (Long.MAX_VALUE - max >= constant) {
/*  5325 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + constant));
/*  5326 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5329 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + constant));
/*  5330 */         am.type = 0;
/*       */       }
/*  5332 */       break;
/*  5333 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5334 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5335 */         hold1 = hold1.add(new BigDecimal(constant));
/*  5336 */         am.array.add(hold1);
/*  5337 */         hold1 = null;
/*       */       }
/*  5339 */       am.type = 12;
/*  5340 */       break;
/*  5341 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5342 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5343 */         BigInteger hold2 = hold1.add(new BigInteger(Long.toString(constant)));
/*  5344 */         am.array.add(hold2);
/*  5345 */         hold1 = null;
/*  5346 */         hold2 = null;
/*       */       }
/*  5348 */       am.type = 13;
/*  5349 */       break;
/*  5350 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(constant));
/*  5351 */       am.type = this.type;
/*  5352 */       break;
/*  5353 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(constant)));
/*  5354 */       am.type = this.type;
/*  5355 */       break;
/*  5356 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Long.toString(constant));
/*  5357 */       am.type = this.type;
/*  5358 */       break;
/*  5359 */     case 16:  throw new IllegalArgumentException("a long cannot be added to a char");
/*  5360 */     case 17:  throw new IllegalArgumentException("a long cannot be added to a Character");
/*  5361 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5363 */     int[] maxminIndices = new int[2];
/*  5364 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5365 */     am.maxIndex = maxminIndices[0];
/*  5366 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5368 */     Conv.restoreMessages();
/*  5369 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Long constant)
/*       */   {
/*  5374 */     long constantl = constant.longValue();
/*  5375 */     return plus(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(long[] arrayL)
/*       */   {
/*  5380 */     int nArg = arrayL.length;
/*  5381 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  5383 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5384 */     ArrayMaths am = new ArrayMaths();
/*  5385 */     am.array = new ArrayList();
/*  5386 */     am.length = this.length;
/*       */     
/*  5388 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5392 */       double[] dd = getArray_as_double();
/*  5393 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + arrayL[i]));
/*  5394 */       am.type = 0;
/*  5395 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5403 */       long max1 = getMaximum_as_long();
/*  5404 */       ArrayMaths am2 = new ArrayMaths(arrayL);
/*  5405 */       long max2 = am2.getMaximum_as_long();
/*  5406 */       long[] ll = getArray_as_long();
/*  5407 */       if (Long.MAX_VALUE - max1 >= max2) {
/*  5408 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + arrayL[i]));
/*  5409 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5412 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + arrayL[i]));
/*  5413 */         am.type = 0;
/*       */       }
/*  5415 */       break;
/*  5416 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5417 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5418 */         hold1 = hold1.add(new BigDecimal(arrayL[i]));
/*  5419 */         am.array.add(hold1);
/*  5420 */         hold1 = null;
/*       */       }
/*  5422 */       am.type = 12;
/*  5423 */       break;
/*  5424 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5425 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5426 */         BigInteger hold2 = hold1.add(new BigInteger(Long.toString(arrayL[i])));
/*  5427 */         am.array.add(hold2);
/*  5428 */         hold1 = null;
/*  5429 */         hold2 = null;
/*       */       }
/*  5431 */       am.type = 13;
/*  5432 */       break;
/*  5433 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(arrayL[i]));
/*  5434 */       am.type = this.type;
/*  5435 */       break;
/*  5436 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(arrayL[i])));
/*  5437 */       am.type = this.type;
/*  5438 */       break;
/*  5439 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Long.toString(arrayL[i]));
/*  5440 */       am.type = this.type;
/*  5441 */       break;
/*  5442 */     case 16:  throw new IllegalArgumentException("a long cannot be added to a char");
/*  5443 */     case 17:  throw new IllegalArgumentException("a long cannot be added to a Character");
/*  5444 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5446 */     int[] maxminIndices = new int[2];
/*  5447 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5448 */     am.maxIndex = maxminIndices[0];
/*  5449 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5451 */     Conv.restoreMessages();
/*  5452 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Long[] arrayL)
/*       */   {
/*  5457 */     int nArg = arrayL.length;
/*  5458 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  5459 */     long[] arrayl = new long[this.length];
/*  5460 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayL[i].longValue();
/*  5461 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(int constant)
/*       */   {
/*  5466 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5467 */     ArrayMaths am = new ArrayMaths();
/*  5468 */     am.array = new ArrayList();
/*  5469 */     am.length = this.length;
/*       */     
/*  5471 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5475 */       double[] dd = getArray_as_double();
/*  5476 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + constant));
/*  5477 */       am.type = 0;
/*  5478 */       break;
/*  5479 */     case 4:  long max = getMaximum_as_long();
/*  5480 */       long[] ll = getArray_as_long();
/*  5481 */       if (Long.MAX_VALUE - max >= constant) {
/*  5482 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + constant));
/*  5483 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5486 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + constant));
/*  5487 */         am.type = 0;
/*       */       }
/*  5489 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5496 */       int maxi = getMaximum_as_int();
/*  5497 */       int[] lll = getArray_as_int();
/*  5498 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  5499 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + constant));
/*  5500 */         am.type = 6;
/*       */       }
/*       */       else {
/*  5503 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + constant));
/*  5504 */         am.type = 0;
/*       */       }
/*  5506 */       break;
/*  5507 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5508 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5509 */         hold1 = hold1.add(new BigDecimal(constant));
/*  5510 */         am.array.add(hold1);
/*  5511 */         hold1 = null;
/*       */       }
/*  5513 */       am.type = 12;
/*  5514 */       break;
/*  5515 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5516 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5517 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(constant)));
/*  5518 */         am.array.add(hold2);
/*  5519 */         hold1 = null;
/*  5520 */         hold2 = null;
/*       */       }
/*  5522 */       am.type = 13;
/*  5523 */       break;
/*  5524 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(constant));
/*  5525 */       am.type = this.type;
/*  5526 */       break;
/*  5527 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(constant)));
/*  5528 */       am.type = this.type;
/*  5529 */       break;
/*  5530 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(constant));
/*  5531 */       am.type = this.type;
/*  5532 */       break;
/*  5533 */     case 16:  throw new IllegalArgumentException("an int cannot be added to a char");
/*  5534 */     case 17:  throw new IllegalArgumentException("an int cannot be added to a Character");
/*  5535 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5537 */     int[] maxminIndices = new int[2];
/*  5538 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5539 */     am.maxIndex = maxminIndices[0];
/*  5540 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5542 */     Conv.restoreMessages();
/*       */     
/*  5544 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Integer constant)
/*       */   {
/*  5549 */     int constantl = constant.intValue();
/*  5550 */     return plus(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths plus(int[] arrayI)
/*       */   {
/*  5556 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5557 */     ArrayMaths am = new ArrayMaths();
/*  5558 */     am.array = new ArrayList();
/*  5559 */     am.length = this.length;
/*       */     
/*  5561 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5565 */       double[] dd = getArray_as_double();
/*  5566 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + arrayI[i]));
/*  5567 */       am.type = 0;
/*  5568 */       break;
/*  5569 */     case 4:  long max = getMaximum_as_long();
/*  5570 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  5571 */       long max2 = am2.getMaximum_as_long();
/*  5572 */       long[] ll = getArray_as_long();
/*  5573 */       if (Long.MAX_VALUE - max >= max2) {
/*  5574 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + arrayI[i]));
/*  5575 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5578 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + arrayI[i]));
/*  5579 */         am.type = 0;
/*       */       }
/*  5581 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5588 */       int maxi = getMaximum_as_int();
/*  5589 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  5590 */       int maxi2 = am22.getMaximum_as_int();
/*  5591 */       int[] lll = getArray_as_int();
/*  5592 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  5593 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + arrayI[i]));
/*  5594 */         am.type = 6;
/*       */       }
/*       */       else {
/*  5597 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + arrayI[i]));
/*  5598 */         am.type = 0;
/*       */       }
/*  5600 */       break;
/*  5601 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5602 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5603 */         hold1 = hold1.add(new BigDecimal(arrayI[i]));
/*  5604 */         am.array.add(hold1);
/*  5605 */         hold1 = null;
/*       */       }
/*  5607 */       am.type = 12;
/*  5608 */       break;
/*  5609 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5610 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5611 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(arrayI[i])));
/*  5612 */         am.array.add(hold2);
/*  5613 */         hold1 = null;
/*  5614 */         hold2 = null;
/*       */       }
/*  5616 */       am.type = 13;
/*  5617 */       break;
/*  5618 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(arrayI[i]));
/*  5619 */       am.type = this.type;
/*  5620 */       break;
/*  5621 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(arrayI[i])));
/*  5622 */       am.type = this.type;
/*  5623 */       break;
/*  5624 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(arrayI[i]));
/*  5625 */       am.type = this.type;
/*  5626 */       break;
/*  5627 */     case 16:  throw new IllegalArgumentException("an int cannot be added to a char");
/*  5628 */     case 17:  throw new IllegalArgumentException("an int cannot be added to a Character");
/*  5629 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5631 */     int[] maxminIndices = new int[2];
/*  5632 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5633 */     am.maxIndex = maxminIndices[0];
/*  5634 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5636 */     Conv.restoreMessages();
/*  5637 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Integer[] arrayI)
/*       */   {
/*  5642 */     int nArg = arrayI.length;
/*  5643 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  5644 */     int[] arrayl = new int[this.length];
/*  5645 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].intValue();
/*  5646 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(short constant)
/*       */   {
/*  5651 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5652 */     ArrayMaths am = new ArrayMaths();
/*  5653 */     am.array = new ArrayList();
/*  5654 */     am.length = this.length;
/*       */     
/*  5656 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5660 */       double[] dd = getArray_as_double();
/*  5661 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + constant));
/*  5662 */       am.type = 0;
/*  5663 */       break;
/*  5664 */     case 4:  long max = getMaximum_as_long();
/*  5665 */       long[] ll = getArray_as_long();
/*  5666 */       if (Long.MAX_VALUE - max >= constant) {
/*  5667 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + constant));
/*  5668 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5671 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + constant));
/*  5672 */         am.type = 0;
/*       */       }
/*  5674 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5681 */       short maxi = getMaximum_as_short();
/*  5682 */       short[] lll = getArray_as_short();
/*  5683 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  5684 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + constant));
/*  5685 */         am.type = 6;
/*       */       }
/*       */       else {
/*  5688 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + constant));
/*  5689 */         am.type = 0;
/*       */       }
/*  5691 */       break;
/*  5692 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5693 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5694 */         hold1 = hold1.add(new BigDecimal(constant));
/*  5695 */         am.array.add(hold1);
/*  5696 */         hold1 = null;
/*       */       }
/*  5698 */       am.type = 12;
/*  5699 */       break;
/*  5700 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5701 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5702 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(constant)));
/*  5703 */         am.array.add(hold2);
/*  5704 */         hold1 = null;
/*  5705 */         hold2 = null;
/*       */       }
/*  5707 */       am.type = 13;
/*  5708 */       break;
/*  5709 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(constant));
/*  5710 */       am.type = this.type;
/*  5711 */       break;
/*  5712 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(constant)));
/*  5713 */       am.type = this.type;
/*  5714 */       break;
/*  5715 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(constant));
/*  5716 */       am.type = this.type;
/*  5717 */       break;
/*  5718 */     case 16:  throw new IllegalArgumentException("a short cannot be added to a char");
/*  5719 */     case 17:  throw new IllegalArgumentException("a short cannot be added to a Character");
/*  5720 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5722 */     int[] maxminIndices = new int[2];
/*  5723 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5724 */     am.maxIndex = maxminIndices[0];
/*  5725 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5727 */     Conv.restoreMessages();
/*  5728 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Short constant)
/*       */   {
/*  5733 */     short constantl = constant.shortValue();
/*  5734 */     return plus(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(short[] arrayI)
/*       */   {
/*  5739 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5740 */     ArrayMaths am = new ArrayMaths();
/*  5741 */     am.array = new ArrayList();
/*  5742 */     am.length = this.length;
/*       */     
/*  5744 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5748 */       double[] dd = getArray_as_double();
/*  5749 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + arrayI[i]));
/*  5750 */       am.type = 0;
/*  5751 */       break;
/*  5752 */     case 4:  long max = getMaximum_as_long();
/*  5753 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  5754 */       long max2 = am2.getMaximum_as_long();
/*  5755 */       long[] ll = getArray_as_long();
/*  5756 */       if (Long.MAX_VALUE - max >= max2) {
/*  5757 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + arrayI[i]));
/*  5758 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5761 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + arrayI[i]));
/*  5762 */         am.type = 0;
/*       */       }
/*  5764 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5771 */       short maxi = getMaximum_as_short();
/*  5772 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  5773 */       short maxi2 = am22.getMaximum_as_short();
/*  5774 */       short[] lll = getArray_as_short();
/*  5775 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  5776 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + arrayI[i]));
/*  5777 */         am.type = 6;
/*       */       }
/*       */       else {
/*  5780 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + arrayI[i]));
/*  5781 */         am.type = 0;
/*       */       }
/*  5783 */       break;
/*  5784 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5785 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5786 */         hold1 = hold1.add(new BigDecimal(arrayI[i]));
/*  5787 */         am.array.add(hold1);
/*  5788 */         hold1 = null;
/*       */       }
/*  5790 */       am.type = 12;
/*  5791 */       break;
/*  5792 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5793 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5794 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(arrayI[i])));
/*  5795 */         am.array.add(hold2);
/*  5796 */         hold1 = null;
/*  5797 */         hold2 = null;
/*       */       }
/*  5799 */       am.type = 13;
/*  5800 */       break;
/*  5801 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(arrayI[i]));
/*  5802 */       am.type = this.type;
/*  5803 */       break;
/*  5804 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(arrayI[i])));
/*  5805 */       am.type = this.type;
/*  5806 */       break;
/*  5807 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(arrayI[i]));
/*  5808 */       am.type = this.type;
/*  5809 */       break;
/*  5810 */     case 16:  throw new IllegalArgumentException("a short cannot be added to a char");
/*  5811 */     case 17:  throw new IllegalArgumentException("a short cannot be added to a Character");
/*  5812 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5814 */     int[] maxminIndices = new int[2];
/*  5815 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5816 */     am.maxIndex = maxminIndices[0];
/*  5817 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5819 */     Conv.restoreMessages();
/*  5820 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Short[] arrayI)
/*       */   {
/*  5825 */     int nArg = arrayI.length;
/*  5826 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  5827 */     short[] arrayl = new short[this.length];
/*  5828 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].shortValue();
/*  5829 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths plus(BigDecimal constant)
/*       */   {
/*  5835 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5836 */     ArrayMaths am = new ArrayMaths();
/*  5837 */     am.array = new ArrayList();
/*  5838 */     am.length = this.length;
/*  5839 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  5853 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  5854 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(constant));
/*  5855 */       am.type = 12;
/*  5856 */       break;
/*  5857 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  5858 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(constant.doubleValue()));
/*  5859 */       am.type = this.type;
/*  5860 */       break;
/*  5861 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  5862 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(new Phasor(constant.doubleValue())));
/*  5863 */       am.type = this.type;
/*  5864 */       break;
/*  5865 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + constant.toString());
/*  5866 */       am.type = this.type;
/*  5867 */       break;
/*  5868 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be added to a char");
/*  5869 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be added to a Character");
/*  5870 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5872 */     int[] maxminIndices = new int[2];
/*  5873 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5874 */     am.maxIndex = maxminIndices[0];
/*  5875 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5877 */     Conv.restoreMessages();
/*  5878 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(byte constant)
/*       */   {
/*  5883 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5884 */     ArrayMaths am = new ArrayMaths();
/*  5885 */     am.array = new ArrayList();
/*  5886 */     am.length = this.length;
/*       */     
/*  5888 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5892 */       double[] dd = getArray_as_double();
/*  5893 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + constant));
/*  5894 */       am.type = 0;
/*  5895 */       break;
/*  5896 */     case 4:  long max = getMaximum_as_long();
/*  5897 */       long[] ll = getArray_as_long();
/*  5898 */       if (Long.MAX_VALUE - max >= constant) {
/*  5899 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + constant));
/*  5900 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5903 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + constant));
/*  5904 */         am.type = 0;
/*       */       }
/*  5906 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  5913 */       byte maxi = getMaximum_as_byte();
/*  5914 */       byte[] lll = getArray_as_byte();
/*  5915 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  5916 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + constant));
/*  5917 */         am.type = 6;
/*       */       }
/*       */       else {
/*  5920 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + constant));
/*  5921 */         am.type = 0;
/*       */       }
/*  5923 */       break;
/*  5924 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  5925 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  5926 */         hold1 = hold1.add(new BigDecimal(constant));
/*  5927 */         am.array.add(hold1);
/*  5928 */         hold1 = null;
/*       */       }
/*  5930 */       am.type = 12;
/*  5931 */       break;
/*  5932 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  5933 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  5934 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(constant)));
/*  5935 */         am.array.add(hold2);
/*  5936 */         hold1 = null;
/*  5937 */         hold2 = null;
/*       */       }
/*  5939 */       am.type = 13;
/*  5940 */       break;
/*  5941 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(constant));
/*  5942 */       am.type = this.type;
/*  5943 */       break;
/*  5944 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(constant)));
/*  5945 */       am.type = this.type;
/*  5946 */       break;
/*  5947 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(constant));
/*  5948 */       am.type = this.type;
/*  5949 */       break;
/*  5950 */     case 16:  throw new IllegalArgumentException("a byte cannot be added to a char");
/*  5951 */     case 17:  throw new IllegalArgumentException("a byte cannot be added to a Character");
/*  5952 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  5954 */     int[] maxminIndices = new int[2];
/*  5955 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  5956 */     am.maxIndex = maxminIndices[0];
/*  5957 */     am.minIndex = maxminIndices[1];
/*       */     
/*  5959 */     Conv.restoreMessages();
/*  5960 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Byte constant)
/*       */   {
/*  5965 */     byte constantl = constant.byteValue();
/*  5966 */     return plus(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(byte[] arrayI)
/*       */   {
/*  5971 */     if (this.suppressMessages) Conv.suppressMessages();
/*  5972 */     ArrayMaths am = new ArrayMaths();
/*  5973 */     am.array = new ArrayList();
/*  5974 */     am.length = this.length;
/*       */     
/*  5976 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  5980 */       double[] dd = getArray_as_double();
/*  5981 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] + arrayI[i]));
/*  5982 */       am.type = 0;
/*  5983 */       break;
/*  5984 */     case 4:  long max = getMaximum_as_long();
/*  5985 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  5986 */       long max2 = am2.getMaximum_as_long();
/*  5987 */       long[] ll = getArray_as_long();
/*  5988 */       if (Long.MAX_VALUE - max >= max2) {
/*  5989 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] + arrayI[i]));
/*  5990 */         am.type = 4;
/*       */       }
/*       */       else {
/*  5993 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] + arrayI[i]));
/*  5994 */         am.type = 0;
/*       */       }
/*  5996 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6003 */       byte maxi = getMaximum_as_byte();
/*  6004 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  6005 */       byte maxi2 = am22.getMaximum_as_byte();
/*  6006 */       byte[] lll = getArray_as_byte();
/*  6007 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  6008 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] + arrayI[i]));
/*  6009 */         am.type = 6;
/*       */       }
/*       */       else {
/*  6012 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] + arrayI[i]));
/*  6013 */         am.type = 0;
/*       */       }
/*  6015 */       break;
/*  6016 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  6017 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  6018 */         hold1 = hold1.add(new BigDecimal(arrayI[i]));
/*  6019 */         am.array.add(hold1);
/*  6020 */         hold1 = null;
/*       */       }
/*  6022 */       am.type = 12;
/*  6023 */       break;
/*  6024 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  6025 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  6026 */         BigInteger hold2 = hold1.add(new BigInteger(Integer.toString(arrayI[i])));
/*  6027 */         am.array.add(hold2);
/*  6028 */         hold1 = null;
/*  6029 */         hold2 = null;
/*       */       }
/*  6031 */       am.type = 13;
/*  6032 */       break;
/*  6033 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).plus(arrayI[i]));
/*  6034 */       am.type = this.type;
/*  6035 */       break;
/*  6036 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).plus(new Phasor(arrayI[i])));
/*  6037 */       am.type = this.type;
/*  6038 */       break;
/*  6039 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + Integer.toString(arrayI[i]));
/*  6040 */       am.type = this.type;
/*  6041 */       break;
/*  6042 */     case 16:  throw new IllegalArgumentException("a byte cannot be added to a char");
/*  6043 */     case 17:  throw new IllegalArgumentException("a byte cannot be added to a Character");
/*  6044 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6046 */     int[] maxminIndices = new int[2];
/*  6047 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6048 */     am.maxIndex = maxminIndices[0];
/*  6049 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6051 */     Conv.restoreMessages();
/*  6052 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Byte[] arrayI)
/*       */   {
/*  6057 */     int nArg = arrayI.length;
/*  6058 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  6059 */     byte[] arrayl = new byte[this.length];
/*  6060 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].byteValue();
/*  6061 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths plus(BigDecimal[] arrayBD)
/*       */   {
/*  6067 */     int nArg = arrayBD.length;
/*  6068 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6070 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6071 */     ArrayMaths am = new ArrayMaths();
/*  6072 */     am.array = new ArrayList();
/*  6073 */     am.length = this.length;
/*       */     
/*  6075 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  6089 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  6090 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(arrayBD[i]));
/*  6091 */       Conv.restoreMessages();
/*  6092 */       am.type = 12;
/*  6093 */       break;
/*  6094 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  6095 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(arrayBD[i].doubleValue()));
/*  6096 */       am.type = this.type;
/*  6097 */       break;
/*  6098 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  6099 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(new Phasor(arrayBD[i].doubleValue())));
/*  6100 */       am.type = this.type;
/*  6101 */       break;
/*  6102 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + arrayBD[i].toString());
/*  6103 */       am.type = this.type;
/*  6104 */       break;
/*  6105 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be added to a char");
/*  6106 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be added to a Character");
/*  6107 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6109 */     int[] maxminIndices = new int[2];
/*  6110 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6111 */     am.maxIndex = maxminIndices[0];
/*  6112 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6114 */     Conv.restoreMessages();
/*  6115 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(BigInteger constant)
/*       */   {
/*  6120 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6121 */     ArrayMaths am = new ArrayMaths();
/*  6122 */     am.array = new ArrayList();
/*  6123 */     am.length = this.length;
/*  6124 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  6129 */       BigDecimal constantBD = Conv.convert_BigInteger_to_BigDecimal(constant);
/*  6130 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  6131 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(constantBD));
/*  6132 */       am.type = 12;
/*  6133 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  6142 */       BigInteger[] bi = getArray_as_BigInteger();
/*  6143 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].add(constant));
/*  6144 */       am.type = 13;
/*  6145 */       break;
/*  6146 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  6147 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(Conv.convert_BigInteger_to_double(constant)));
/*  6148 */       am.type = this.type;
/*  6149 */       break;
/*  6150 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  6151 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(new Phasor(Conv.convert_BigInteger_to_double(constant))));
/*  6152 */       am.type = this.type;
/*  6153 */       break;
/*  6154 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + constant.toString());
/*  6155 */       am.type = this.type;
/*  6156 */       break;
/*  6157 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be added to a char");
/*  6158 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be added to a Character");
/*  6159 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6161 */     int[] maxminIndices = new int[2];
/*  6162 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6163 */     am.maxIndex = maxminIndices[0];
/*  6164 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6166 */     Conv.restoreMessages();
/*  6167 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(BigInteger[] arrayBI)
/*       */   {
/*  6172 */     int nArg = arrayBI.length;
/*  6173 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6175 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6176 */     ArrayMaths am = new ArrayMaths();
/*  6177 */     am.array = new ArrayList();
/*  6178 */     am.length = this.length;
/*  6179 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  6184 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  6185 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(Conv.convert_BigInteger_to_BigDecimal(arrayBI[i])));
/*  6186 */       am.type = 12;
/*  6187 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  6196 */       BigInteger[] bi = getArray_as_BigInteger();
/*  6197 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].add(arrayBI[i]));
/*  6198 */       am.type = 13;
/*  6199 */       break;
/*  6200 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  6201 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(Conv.convert_BigInteger_to_double(arrayBI[i])));
/*  6202 */       am.type = this.type;
/*  6203 */       break;
/*  6204 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  6205 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(new Phasor(Conv.convert_BigInteger_to_double(arrayBI[i]))));
/*  6206 */       am.type = this.type;
/*  6207 */       break;
/*  6208 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + arrayBI[i].toString());
/*  6209 */       am.type = this.type;
/*  6210 */       break;
/*  6211 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be added to a char");
/*  6212 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be added to a Character");
/*  6213 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6215 */     int[] maxminIndices = new int[2];
/*  6216 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6217 */     am.maxIndex = maxminIndices[0];
/*  6218 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6220 */     Conv.restoreMessages();
/*  6221 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Complex constant)
/*       */   {
/*  6226 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6227 */     ArrayMaths am = new ArrayMaths();
/*  6228 */     am.array = new ArrayList();
/*  6229 */     am.length = this.length;
/*  6230 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  6245 */       Complex[] cc = getArray_as_Complex();
/*  6246 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(constant));
/*  6247 */       am.type = 14;
/*  6248 */       break;
/*  6249 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  6250 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(Conv.convert_Complex_to_Phasor(constant)));
/*  6251 */       am.type = this.type;
/*  6252 */       break;
/*  6253 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + constant.toString());
/*  6254 */       am.type = this.type;
/*  6255 */       break;
/*  6256 */     case 16:  throw new IllegalArgumentException("a Complex cannot be added to a char");
/*  6257 */     case 17:  throw new IllegalArgumentException("a Complex cannot be added to a Character");
/*  6258 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6260 */     Conv.restoreMessages();
/*  6261 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Complex[] arrayC)
/*       */   {
/*  6266 */     int nArg = arrayC.length;
/*  6267 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6269 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6270 */     ArrayMaths am = new ArrayMaths();
/*  6271 */     am.array = new ArrayList();
/*  6272 */     am.length = this.length;
/*  6273 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  6288 */       Complex[] cc = getArray_as_Complex();
/*  6289 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(arrayC[i]));
/*  6290 */       am.type = 14;
/*  6291 */       break;
/*  6292 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  6293 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(Conv.convert_Complex_to_Phasor(arrayC[i])));
/*  6294 */       am.type = this.type;
/*  6295 */       break;
/*  6296 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + arrayC[i].toString());
/*  6297 */       am.type = this.type;
/*  6298 */       break;
/*  6299 */     case 16:  throw new IllegalArgumentException("a Complex cannot be added to a char");
/*  6300 */     case 17:  throw new IllegalArgumentException("a Complex cannot be added to a Character");
/*  6301 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6303 */     Conv.restoreMessages();
/*  6304 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths plus(Phasor constant)
/*       */   {
/*  6310 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6311 */     ArrayMaths am = new ArrayMaths();
/*  6312 */     am.array = new ArrayList();
/*  6313 */     am.length = this.length;
/*  6314 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  6329 */       Phasor[] pp = getArray_as_Phasor();
/*  6330 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(constant));
/*  6331 */       am.type = 15;
/*  6332 */       break;
/*  6333 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  6334 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(Conv.convert_Phasor_to_Complex(constant)));
/*  6335 */       am.type = this.type;
/*  6336 */       break;
/*  6337 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + constant.toString());
/*  6338 */       am.type = this.type;
/*  6339 */       break;
/*  6340 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be added to a char");
/*  6341 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be added to a Character");
/*  6342 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6344 */     Conv.restoreMessages();
/*  6345 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Phasor[] arrayP)
/*       */   {
/*  6350 */     int nArg = arrayP.length;
/*  6351 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6353 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6354 */     ArrayMaths am = new ArrayMaths();
/*  6355 */     am.array = new ArrayList();
/*  6356 */     am.length = this.length;
/*  6357 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  6372 */       Phasor[] pp = getArray_as_Phasor();
/*  6373 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].plus(arrayP[i]));
/*  6374 */       am.type = 15;
/*  6375 */       break;
/*  6376 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  6377 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].plus(Conv.convert_Phasor_to_Complex(arrayP[i])));
/*  6378 */       am.type = this.type;
/*  6379 */       break;
/*  6380 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(i) + arrayP[i].toString());
/*  6381 */       am.type = this.type;
/*  6382 */       break;
/*  6383 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be added to a char");
/*  6384 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be added to a Character");
/*  6385 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6387 */     Conv.restoreMessages();
/*  6388 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(String constant)
/*       */   {
/*  6393 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6394 */     ArrayMaths am = new ArrayMaths();
/*  6395 */     am.array = new ArrayList();
/*  6396 */     am.length = this.length;
/*  6397 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6416 */       String[] ss = getArray_as_String();
/*  6417 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + constant);
/*  6418 */       am.type = 18;
/*  6419 */       break;
/*  6420 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6422 */     Conv.restoreMessages();
/*  6423 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths plus(String[] arraySt)
/*       */   {
/*  6429 */     int nArg = arraySt.length;
/*  6430 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6432 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6433 */     ArrayMaths am = new ArrayMaths();
/*  6434 */     am.array = new ArrayList();
/*  6435 */     am.length = this.length;
/*  6436 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6455 */       String[] ss = getArray_as_String();
/*  6456 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + arraySt[i]);
/*  6457 */       am.type = 18;
/*  6458 */       break;
/*  6459 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6461 */     Conv.restoreMessages();
/*  6462 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(char constant)
/*       */   {
/*  6467 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6468 */     ArrayMaths am = new ArrayMaths();
/*  6469 */     am.array = new ArrayList();
/*  6470 */     am.length = this.length;
/*  6471 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6490 */       String[] ss = getArray_as_String();
/*  6491 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + constant);
/*  6492 */       am.type = 18;
/*  6493 */       break;
/*  6494 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6496 */     int[] maxminIndices = new int[2];
/*  6497 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6498 */     am.maxIndex = maxminIndices[0];
/*  6499 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6501 */     Conv.restoreMessages();
/*  6502 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(char[] arrayCh)
/*       */   {
/*  6507 */     int nArg = arrayCh.length;
/*  6508 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6510 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6511 */     ArrayMaths am = new ArrayMaths();
/*  6512 */     am.array = new ArrayList();
/*  6513 */     am.length = this.length;
/*  6514 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6533 */       String[] ss = getArray_as_String();
/*  6534 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + arrayCh[i]);
/*  6535 */       am.type = 18;
/*  6536 */       break;
/*  6537 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6539 */     int[] maxminIndices = new int[2];
/*  6540 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6541 */     am.maxIndex = maxminIndices[0];
/*  6542 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6544 */     Conv.restoreMessages();
/*  6545 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Character constant)
/*       */   {
/*  6550 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6551 */     ArrayMaths am = new ArrayMaths();
/*  6552 */     am.array = new ArrayList();
/*  6553 */     am.length = this.length;
/*  6554 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6573 */       String[] ss = getArray_as_String();
/*  6574 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + constant);
/*  6575 */       am.type = 18;
/*  6576 */       break;
/*  6577 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6579 */     int[] maxminIndices = new int[2];
/*  6580 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6581 */     am.maxIndex = maxminIndices[0];
/*  6582 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6584 */     Conv.restoreMessages();
/*  6585 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Character[] arrayCh)
/*       */   {
/*  6590 */     int nArg = arrayCh.length;
/*  6591 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6593 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6594 */     ArrayMaths am = new ArrayMaths();
/*  6595 */     am.array = new ArrayList();
/*  6596 */     am.length = this.length;
/*  6597 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  6616 */       String[] ss = getArray_as_String();
/*  6617 */       for (int i = 0; i < this.length; i++) am.array.add(ss[i] + arrayCh[i]);
/*  6618 */       am.type = 18;
/*  6619 */       break;
/*  6620 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6622 */     Conv.restoreMessages();
/*  6623 */     int[] maxminIndices = new int[2];
/*  6624 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6625 */     am.maxIndex = maxminIndices[0];
/*  6626 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6628 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Vector<Object> vec)
/*       */   {
/*  6633 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6634 */     ArrayMaths am1 = new ArrayMaths();
/*  6635 */     ArrayMaths am2 = new ArrayMaths(vec);
/*       */     
/*  6637 */     switch (am2.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  6641 */       double[] dd = am2.getArray_as_double();
/*  6642 */       am1 = plus(dd);
/*  6643 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6651 */       long[] ll = am2.getArray_as_long();
/*  6652 */       am1 = plus(ll);
/*  6653 */       break;
/*  6654 */     case 12:  BigDecimal[] bd = am2.getArray_as_BigDecimal();
/*  6655 */       am1 = plus(bd);
/*  6656 */       break;
/*  6657 */     case 13:  BigInteger[] bi = am2.getArray_as_BigInteger();
/*  6658 */       am1 = plus(bi);
/*  6659 */       break;
/*  6660 */     case 14:  Complex[] cc = am2.getArray_as_Complex();
/*  6661 */       am1 = plus(cc);
/*  6662 */       break;
/*  6663 */     case 15:  Phasor[] pp = am2.getArray_as_Phasor();
/*  6664 */       am1 = plus(pp);
/*  6665 */       break;
/*       */     case 16: case 17: 
/*  6667 */       Character[] ct = am2.getArray_as_Character();
/*  6668 */       am1 = plus(ct);
/*  6669 */       break;
/*  6670 */     case 18:  String[] st = am2.getArray_as_String();
/*  6671 */       am1 = plus(st);
/*  6672 */       break;
/*  6673 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6675 */     int[] maxminIndices = new int[2];
/*  6676 */     findMinMax(am1.getArray_as_Object(), am1.minmax, maxminIndices, am1.typeName, am1.type);
/*  6677 */     am1.maxIndex = maxminIndices[0];
/*  6678 */     am1.minIndex = maxminIndices[1];
/*       */     
/*  6680 */     Conv.restoreMessages();
/*  6681 */     return am1;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(ArrayList<Object> list)
/*       */   {
/*  6686 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6687 */     ArrayMaths am1 = new ArrayMaths();
/*  6688 */     ArrayMaths am2 = new ArrayMaths(list);
/*       */     
/*  6690 */     switch (am2.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  6694 */       double[] dd = am2.getArray_as_double();
/*  6695 */       am1 = plus(dd);
/*  6696 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6704 */       long[] ll = am2.getArray_as_long();
/*  6705 */       am1 = plus(ll);
/*  6706 */       break;
/*  6707 */     case 12:  BigDecimal[] bd = am2.getArray_as_BigDecimal();
/*  6708 */       am1 = plus(bd);
/*  6709 */       break;
/*  6710 */     case 13:  BigInteger[] bi = am2.getArray_as_BigInteger();
/*  6711 */       am1 = plus(bi);
/*  6712 */       break;
/*  6713 */     case 14:  Complex[] cc = am2.getArray_as_Complex();
/*  6714 */       am1 = plus(cc);
/*  6715 */       break;
/*  6716 */     case 15:  Phasor[] pp = am2.getArray_as_Phasor();
/*  6717 */       am1 = plus(pp);
/*  6718 */       break;
/*       */     case 16: case 17: 
/*  6720 */       Character[] ct = am2.getArray_as_Character();
/*  6721 */       am1 = plus(ct);
/*  6722 */       break;
/*  6723 */     case 18:  String[] st = am2.getArray_as_String();
/*  6724 */       am1 = plus(st);
/*  6725 */       break;
/*  6726 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6728 */     int[] maxminIndices = new int[2];
/*  6729 */     findMinMax(am1.getArray_as_Object(), am1.minmax, maxminIndices, am1.typeName, am1.type);
/*  6730 */     am1.maxIndex = maxminIndices[0];
/*  6731 */     am1.minIndex = maxminIndices[1];
/*       */     
/*  6733 */     Conv.restoreMessages();
/*  6734 */     return am1;
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(ArrayMaths arrayM)
/*       */   {
/*  6739 */     ArrayList<Object> arrayl = arrayM.getArray_as_ArrayList();
/*  6740 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths plus(Stat arrayS)
/*       */   {
/*  6745 */     ArrayList<Object> arrayl = arrayS.getArray_as_ArrayList();
/*  6746 */     return plus(arrayl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(double constant)
/*       */   {
/*  6752 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6753 */     ArrayMaths am = new ArrayMaths();
/*  6754 */     am.array = new ArrayList();
/*  6755 */     am.length = this.length;
/*       */     
/*  6757 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6769 */       double[] dd = getArray_as_double();
/*  6770 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - constant));
/*  6771 */       am.type = 0;
/*  6772 */       break;
/*  6773 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  6774 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  6775 */         hold1 = hold1.subtract(new BigDecimal(constant));
/*  6776 */         am.array.add(hold1);
/*  6777 */         hold1 = null;
/*       */       }
/*  6779 */       am.type = this.type;
/*  6780 */       break;
/*  6781 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  6782 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  6783 */         BigDecimal hold2 = new BigDecimal(hold1).subtract(new BigDecimal(constant));
/*  6784 */         am.array.add(hold2);
/*  6785 */         hold1 = null;
/*  6786 */         hold2 = null;
/*       */       }
/*  6788 */       am.type = 12;
/*  6789 */       break;
/*  6790 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(constant));
/*  6791 */       am.type = this.type;
/*  6792 */       break;
/*  6793 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Complex(constant)));
/*  6794 */       am.type = this.type;
/*  6795 */       break;
/*  6796 */     case 16:  throw new IllegalArgumentException("a double or float cannot be subtracted from a char");
/*  6797 */     case 17:  throw new IllegalArgumentException("a double or float cannot be subtracted from a Character");
/*  6798 */     case 18:  throw new IllegalArgumentException("a double or float cannot be subtracted from a String");
/*  6799 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6801 */     int[] maxminIndices = new int[2];
/*  6802 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6803 */     am.maxIndex = maxminIndices[0];
/*  6804 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6806 */     Conv.restoreMessages();
/*  6807 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Double constant)
/*       */   {
/*  6812 */     return minus(constant.doubleValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(double[] arrayD)
/*       */   {
/*  6817 */     if (this.length != arrayD.length) throw new IllegalArgumentException("The length of the argument array, " + arrayD.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
/*  6818 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6819 */     ArrayMaths am = new ArrayMaths();
/*  6820 */     am.array = new ArrayList();
/*  6821 */     am.length = this.length;
/*       */     
/*  6823 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6835 */       double[] dd = getArray_as_double();
/*  6836 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - arrayD[i]));
/*  6837 */       am.type = 0;
/*  6838 */       break;
/*  6839 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  6840 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  6841 */         hold1 = hold1.subtract(new BigDecimal(arrayD[i]));
/*  6842 */         am.array.add(hold1);
/*  6843 */         hold1 = null;
/*       */       }
/*  6845 */       am.type = this.type;
/*  6846 */       break;
/*  6847 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  6848 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  6849 */         BigDecimal hold2 = new BigDecimal(hold1).subtract(new BigDecimal(arrayD[i]));
/*  6850 */         am.array.add(hold2);
/*  6851 */         hold1 = null;
/*  6852 */         hold2 = null;
/*       */       }
/*  6854 */       am.type = 12;
/*  6855 */       break;
/*  6856 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(arrayD[i]));
/*  6857 */       am.type = this.type;
/*  6858 */       break;
/*  6859 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(arrayD[i])));
/*  6860 */       am.type = this.type;
/*  6861 */       break;
/*  6862 */     case 16:  throw new IllegalArgumentException("a double or float cannot be subtracted from a char");
/*  6863 */     case 17:  throw new IllegalArgumentException("a double or float cannot be subtracted from a Character");
/*  6864 */     case 18:  throw new IllegalArgumentException("a double or float cannot be subtracted from a String");
/*  6865 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6867 */     int[] maxminIndices = new int[2];
/*  6868 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6869 */     am.maxIndex = maxminIndices[0];
/*  6870 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6872 */     Conv.restoreMessages();
/*  6873 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Double[] arrayD)
/*       */   {
/*  6878 */     int nArg = arrayD.length;
/*  6879 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  6880 */     double[] arrayd = new double[this.length];
/*  6881 */     for (int i = 0; i < this.length; i++) arrayd[i] = arrayD[i].doubleValue();
/*  6882 */     return minus(arrayd);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(float constant)
/*       */   {
/*  6887 */     double constantd = constant;
/*  6888 */     return minus(constantd);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Float constant)
/*       */   {
/*  6893 */     return minus(constant.floatValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(float[] arrayF)
/*       */   {
/*  6898 */     if (this.length != arrayF.length) throw new IllegalArgumentException("The length of the argument array, " + arrayF.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
/*  6899 */     double[] arrayD = new double[this.length];
/*  6900 */     for (int i = 0; i < this.length; i++) arrayD[i] = arrayF[i];
/*  6901 */     return minus(arrayD);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Float[] arrayF)
/*       */   {
/*  6906 */     int nArg = arrayF.length;
/*  6907 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  6908 */     double[] arrayd = new double[this.length];
/*  6909 */     for (int i = 0; i < this.length; i++) arrayd[i] = arrayF[i].doubleValue();
/*  6910 */     return minus(arrayd);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(long constant)
/*       */   {
/*  6915 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6916 */     ArrayMaths am = new ArrayMaths();
/*  6917 */     am.array = new ArrayList();
/*  6918 */     am.length = this.length;
/*       */     
/*  6920 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  6924 */       double[] dd = getArray_as_double();
/*  6925 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - constant));
/*  6926 */       am.type = 0;
/*  6927 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  6935 */       long max = getMaximum_as_long();
/*  6936 */       long[] ll = getArray_as_long();
/*  6937 */       if (Long.MAX_VALUE - max >= constant) {
/*  6938 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - constant));
/*  6939 */         am.type = 4;
/*       */       }
/*       */       else {
/*  6942 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - constant));
/*  6943 */         am.type = 0;
/*       */       }
/*  6945 */       break;
/*  6946 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  6947 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  6948 */         hold1 = hold1.subtract(new BigDecimal(constant));
/*  6949 */         am.array.add(hold1);
/*  6950 */         hold1 = null;
/*       */       }
/*  6952 */       am.type = 12;
/*  6953 */       break;
/*  6954 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  6955 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  6956 */         BigInteger hold2 = hold1.subtract(new BigInteger(Long.toString(constant)));
/*  6957 */         am.array.add(hold2);
/*  6958 */         hold1 = null;
/*  6959 */         hold2 = null;
/*       */       }
/*  6961 */       am.type = 13;
/*  6962 */       break;
/*  6963 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(constant));
/*  6964 */       am.type = this.type;
/*  6965 */       break;
/*  6966 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(constant)));
/*  6967 */       am.type = this.type;
/*  6968 */       break;
/*  6969 */     case 16:  throw new IllegalArgumentException("a long cannot be subtracted from a char");
/*  6970 */     case 17:  throw new IllegalArgumentException("a long cannot be subtracted from a Character");
/*  6971 */     case 18:  throw new IllegalArgumentException("a long cannot be subtracted from a String");
/*  6972 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  6974 */     int[] maxminIndices = new int[2];
/*  6975 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  6976 */     am.maxIndex = maxminIndices[0];
/*  6977 */     am.minIndex = maxminIndices[1];
/*       */     
/*  6979 */     Conv.restoreMessages();
/*  6980 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Long constant)
/*       */   {
/*  6985 */     long constantl = constant.longValue();
/*  6986 */     return minus(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(long[] arrayL)
/*       */   {
/*  6991 */     int nArg = arrayL.length;
/*  6992 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  6994 */     if (this.suppressMessages) Conv.suppressMessages();
/*  6995 */     ArrayMaths am = new ArrayMaths();
/*  6996 */     am.array = new ArrayList();
/*  6997 */     am.length = this.length;
/*       */     
/*  6999 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7003 */       double[] dd = getArray_as_double();
/*  7004 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - arrayL[i]));
/*  7005 */       am.type = 0;
/*  7006 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7014 */       long max1 = getMaximum_as_long();
/*  7015 */       ArrayMaths am2 = new ArrayMaths(arrayL);
/*  7016 */       long max2 = am2.getMaximum_as_long();
/*  7017 */       long[] ll = getArray_as_long();
/*  7018 */       if (Long.MAX_VALUE - max1 >= max2) {
/*  7019 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - arrayL[i]));
/*  7020 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7023 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - arrayL[i]));
/*  7024 */         am.type = 0;
/*       */       }
/*  7026 */       break;
/*  7027 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7028 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7029 */         hold1 = hold1.subtract(new BigDecimal(arrayL[i]));
/*  7030 */         am.array.add(hold1);
/*  7031 */         hold1 = null;
/*       */       }
/*  7033 */       am.type = 12;
/*  7034 */       break;
/*  7035 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7036 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7037 */         BigInteger hold2 = hold1.subtract(new BigInteger(Long.toString(arrayL[i])));
/*  7038 */         am.array.add(hold2);
/*  7039 */         hold1 = null;
/*  7040 */         hold2 = null;
/*       */       }
/*  7042 */       am.type = 13;
/*  7043 */       break;
/*  7044 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(arrayL[i]));
/*  7045 */       am.type = this.type;
/*  7046 */       break;
/*  7047 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(arrayL[i])));
/*  7048 */       am.type = this.type;
/*  7049 */       break;
/*  7050 */     case 16:  throw new IllegalArgumentException("a long cannot be subtracted from a char");
/*  7051 */     case 17:  throw new IllegalArgumentException("a long cannot be subtracted from a Character");
/*  7052 */     case 18:  throw new IllegalArgumentException("a long cannot be subtracted from a String");
/*  7053 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7055 */     int[] maxminIndices = new int[2];
/*  7056 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7057 */     am.maxIndex = maxminIndices[0];
/*  7058 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7060 */     Conv.restoreMessages();
/*  7061 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Long[] arrayL)
/*       */   {
/*  7066 */     int nArg = arrayL.length;
/*  7067 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  7068 */     long[] arrayl = new long[this.length];
/*  7069 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayL[i].longValue();
/*  7070 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(int constant)
/*       */   {
/*  7075 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7076 */     ArrayMaths am = new ArrayMaths();
/*  7077 */     am.array = new ArrayList();
/*  7078 */     am.length = this.length;
/*       */     
/*  7080 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7084 */       double[] dd = getArray_as_double();
/*  7085 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - constant));
/*  7086 */       am.type = 0;
/*  7087 */       break;
/*  7088 */     case 4:  long max = getMaximum_as_long();
/*  7089 */       long[] ll = getArray_as_long();
/*  7090 */       if (Long.MAX_VALUE - max >= constant) {
/*  7091 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - constant));
/*  7092 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7095 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - constant));
/*  7096 */         am.type = 0;
/*       */       }
/*  7098 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7105 */       int maxi = getMaximum_as_int();
/*  7106 */       int[] lll = getArray_as_int();
/*  7107 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  7108 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - constant));
/*  7109 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7112 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - constant));
/*  7113 */         am.type = 0;
/*       */       }
/*  7115 */       break;
/*  7116 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7117 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7118 */         hold1 = hold1.subtract(new BigDecimal(constant));
/*  7119 */         am.array.add(hold1);
/*  7120 */         hold1 = null;
/*       */       }
/*  7122 */       am.type = 12;
/*  7123 */       break;
/*  7124 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7125 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7126 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(constant)));
/*  7127 */         am.array.add(hold2);
/*  7128 */         hold1 = null;
/*  7129 */         hold2 = null;
/*       */       }
/*  7131 */       am.type = 13;
/*  7132 */       break;
/*  7133 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(constant));
/*  7134 */       am.type = this.type;
/*  7135 */       break;
/*  7136 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(constant)));
/*  7137 */       am.type = this.type;
/*  7138 */       break;
/*  7139 */     case 16:  throw new IllegalArgumentException("an int cannot be subtracted from a char");
/*  7140 */     case 17:  throw new IllegalArgumentException("an int cannot be subtracted from a Character");
/*  7141 */     case 18:  throw new IllegalArgumentException("an int cannot be subtracted from a String");
/*  7142 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7144 */     int[] maxminIndices = new int[2];
/*  7145 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7146 */     am.maxIndex = maxminIndices[0];
/*  7147 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7149 */     Conv.restoreMessages();
/*  7150 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Integer constant)
/*       */   {
/*  7155 */     int constantl = constant.intValue();
/*  7156 */     return minus(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(int[] arrayI)
/*       */   {
/*  7162 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7163 */     ArrayMaths am = new ArrayMaths();
/*  7164 */     am.array = new ArrayList();
/*  7165 */     am.length = this.length;
/*       */     
/*  7167 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7171 */       double[] dd = getArray_as_double();
/*  7172 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - arrayI[i]));
/*  7173 */       am.type = 0;
/*  7174 */       break;
/*  7175 */     case 4:  long max = getMaximum_as_long();
/*  7176 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  7177 */       long max2 = am2.getMaximum_as_long();
/*  7178 */       long[] ll = getArray_as_long();
/*  7179 */       if (Long.MAX_VALUE - max >= max2) {
/*  7180 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - arrayI[i]));
/*  7181 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7184 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - arrayI[i]));
/*  7185 */         am.type = 0;
/*       */       }
/*  7187 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7194 */       int maxi = getMaximum_as_int();
/*  7195 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  7196 */       int maxi2 = am22.getMaximum_as_int();
/*  7197 */       int[] lll = getArray_as_int();
/*  7198 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  7199 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - arrayI[i]));
/*  7200 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7203 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - arrayI[i]));
/*  7204 */         am.type = 0;
/*       */       }
/*  7206 */       break;
/*  7207 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7208 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7209 */         hold1 = hold1.subtract(new BigDecimal(arrayI[i]));
/*  7210 */         am.array.add(hold1);
/*  7211 */         hold1 = null;
/*       */       }
/*  7213 */       am.type = 12;
/*  7214 */       break;
/*  7215 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7216 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7217 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(arrayI[i])));
/*  7218 */         am.array.add(hold2);
/*  7219 */         hold1 = null;
/*  7220 */         hold2 = null;
/*       */       }
/*  7222 */       am.type = 13;
/*  7223 */       break;
/*  7224 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(arrayI[i]));
/*  7225 */       am.type = this.type;
/*  7226 */       break;
/*  7227 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(arrayI[i])));
/*  7228 */       am.type = this.type;
/*  7229 */       break;
/*  7230 */     case 16:  throw new IllegalArgumentException("an int cannot be subtracted from a char");
/*  7231 */     case 17:  throw new IllegalArgumentException("an int cannot be subtracted from a Character");
/*  7232 */     case 18:  throw new IllegalArgumentException("an int cannot be subtracted from a String");
/*  7233 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7235 */     int[] maxminIndices = new int[2];
/*  7236 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7237 */     am.maxIndex = maxminIndices[0];
/*  7238 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7240 */     Conv.restoreMessages();
/*  7241 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Integer[] arrayI)
/*       */   {
/*  7246 */     int nArg = arrayI.length;
/*  7247 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  7248 */     int[] arrayl = new int[this.length];
/*  7249 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].intValue();
/*  7250 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(short constant)
/*       */   {
/*  7255 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7256 */     ArrayMaths am = new ArrayMaths();
/*  7257 */     am.array = new ArrayList();
/*  7258 */     am.length = this.length;
/*       */     
/*  7260 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7264 */       double[] dd = getArray_as_double();
/*  7265 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - constant));
/*  7266 */       am.type = 0;
/*  7267 */       break;
/*  7268 */     case 4:  long max = getMaximum_as_long();
/*  7269 */       long[] ll = getArray_as_long();
/*  7270 */       if (Long.MAX_VALUE - max >= constant) {
/*  7271 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - constant));
/*  7272 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7275 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - constant));
/*  7276 */         am.type = 0;
/*       */       }
/*  7278 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7285 */       short maxi = getMaximum_as_short();
/*  7286 */       short[] lll = getArray_as_short();
/*  7287 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  7288 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - constant));
/*  7289 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7292 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - constant));
/*  7293 */         am.type = 0;
/*       */       }
/*  7295 */       break;
/*  7296 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7297 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7298 */         hold1 = hold1.subtract(new BigDecimal(constant));
/*  7299 */         am.array.add(hold1);
/*  7300 */         hold1 = null;
/*       */       }
/*  7302 */       am.type = 12;
/*  7303 */       break;
/*  7304 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7305 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7306 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(constant)));
/*  7307 */         am.array.add(hold2);
/*  7308 */         hold1 = null;
/*  7309 */         hold2 = null;
/*       */       }
/*  7311 */       am.type = 13;
/*  7312 */       break;
/*  7313 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(constant));
/*  7314 */       am.type = this.type;
/*  7315 */       break;
/*  7316 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(constant)));
/*  7317 */       am.type = this.type;
/*  7318 */       break;
/*  7319 */     case 16:  throw new IllegalArgumentException("a short cannot be subtracted from a char");
/*  7320 */     case 17:  throw new IllegalArgumentException("a short cannot be subtracted from a Character");
/*  7321 */     case 18:  throw new IllegalArgumentException("a short cannot be subtracted from a String");
/*  7322 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7324 */     int[] maxminIndices = new int[2];
/*  7325 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7326 */     am.maxIndex = maxminIndices[0];
/*  7327 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7329 */     Conv.restoreMessages();
/*  7330 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Short constant)
/*       */   {
/*  7335 */     short constantl = constant.shortValue();
/*  7336 */     return minus(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(short[] arrayI)
/*       */   {
/*  7342 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7343 */     ArrayMaths am = new ArrayMaths();
/*  7344 */     am.array = new ArrayList();
/*  7345 */     am.length = this.length;
/*       */     
/*  7347 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7351 */       double[] dd = getArray_as_double();
/*  7352 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - arrayI[i]));
/*  7353 */       am.type = 0;
/*  7354 */       break;
/*  7355 */     case 4:  long max = getMaximum_as_long();
/*  7356 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  7357 */       long max2 = am2.getMaximum_as_long();
/*  7358 */       long[] ll = getArray_as_long();
/*  7359 */       if (Long.MAX_VALUE - max >= max2) {
/*  7360 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - arrayI[i]));
/*  7361 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7364 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - arrayI[i]));
/*  7365 */         am.type = 0;
/*       */       }
/*  7367 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7374 */       short maxi = getMaximum_as_short();
/*  7375 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  7376 */       short maxi2 = am22.getMaximum_as_short();
/*  7377 */       short[] lll = getArray_as_short();
/*  7378 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  7379 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - arrayI[i]));
/*  7380 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7383 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - arrayI[i]));
/*  7384 */         am.type = 0;
/*       */       }
/*  7386 */       break;
/*  7387 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7388 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7389 */         hold1 = hold1.subtract(new BigDecimal(arrayI[i]));
/*  7390 */         am.array.add(hold1);
/*  7391 */         hold1 = null;
/*       */       }
/*  7393 */       am.type = 12;
/*  7394 */       break;
/*  7395 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7396 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7397 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(arrayI[i])));
/*  7398 */         am.array.add(hold2);
/*  7399 */         hold1 = null;
/*  7400 */         hold2 = null;
/*       */       }
/*  7402 */       am.type = 13;
/*  7403 */       break;
/*  7404 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(arrayI[i]));
/*  7405 */       am.type = this.type;
/*  7406 */       break;
/*  7407 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(arrayI[i])));
/*  7408 */       am.type = this.type;
/*  7409 */       break;
/*  7410 */     case 16:  throw new IllegalArgumentException("a long cannot be subtracted from a char");
/*  7411 */     case 17:  throw new IllegalArgumentException("a long cannot be subtracted from a Character");
/*  7412 */     case 18:  throw new IllegalArgumentException("a short cannot be subtracted from a String");
/*  7413 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7415 */     int[] maxminIndices = new int[2];
/*  7416 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7417 */     am.maxIndex = maxminIndices[0];
/*  7418 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7420 */     Conv.restoreMessages();
/*  7421 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Short[] arrayI)
/*       */   {
/*  7426 */     int nArg = arrayI.length;
/*  7427 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  7428 */     short[] arrayl = new short[this.length];
/*  7429 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].shortValue();
/*  7430 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(BigDecimal constant)
/*       */   {
/*  7436 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7437 */     ArrayMaths am = new ArrayMaths();
/*  7438 */     am.array = new ArrayList();
/*  7439 */     am.length = this.length;
/*  7440 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  7454 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  7455 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].subtract(constant));
/*  7456 */       am.type = 12;
/*  7457 */       break;
/*  7458 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7459 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(constant.doubleValue()));
/*  7460 */       am.type = this.type;
/*  7461 */       break;
/*  7462 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7463 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(new Phasor(constant.doubleValue())));
/*  7464 */       am.type = this.type;
/*  7465 */       break;
/*  7466 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a char");
/*  7467 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a Character");
/*  7468 */     case 18:  throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a String");
/*  7469 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7471 */     int[] maxminIndices = new int[2];
/*  7472 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7473 */     am.maxIndex = maxminIndices[0];
/*  7474 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7476 */     Conv.restoreMessages();
/*  7477 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(byte constant)
/*       */   {
/*  7482 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7483 */     ArrayMaths am = new ArrayMaths();
/*  7484 */     am.array = new ArrayList();
/*  7485 */     am.length = this.length;
/*       */     
/*  7487 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7491 */       double[] dd = getArray_as_double();
/*  7492 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - constant));
/*  7493 */       am.type = 0;
/*  7494 */       break;
/*  7495 */     case 4:  long max = getMaximum_as_long();
/*  7496 */       long[] ll = getArray_as_long();
/*  7497 */       if (Long.MAX_VALUE - max >= constant) {
/*  7498 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - constant));
/*  7499 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7502 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - constant));
/*  7503 */         am.type = 0;
/*       */       }
/*  7505 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7512 */       byte maxi = getMaximum_as_byte();
/*  7513 */       byte[] lll = getArray_as_byte();
/*  7514 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  7515 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - constant));
/*  7516 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7519 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - constant));
/*  7520 */         am.type = 0;
/*       */       }
/*  7522 */       break;
/*  7523 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7524 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7525 */         hold1 = hold1.subtract(new BigDecimal(constant));
/*  7526 */         am.array.add(hold1);
/*  7527 */         hold1 = null;
/*       */       }
/*  7529 */       am.type = 12;
/*  7530 */       break;
/*  7531 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7532 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7533 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(constant)));
/*  7534 */         am.array.add(hold2);
/*  7535 */         hold1 = null;
/*  7536 */         hold2 = null;
/*       */       }
/*  7538 */       am.type = 13;
/*  7539 */       break;
/*  7540 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(constant));
/*  7541 */       am.type = this.type;
/*  7542 */       break;
/*  7543 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(constant)));
/*  7544 */       am.type = this.type;
/*  7545 */       break;
/*  7546 */     case 16:  throw new IllegalArgumentException("a byte cannot be subtracted from a char");
/*  7547 */     case 17:  throw new IllegalArgumentException("a byte cannot be subtracted from a Character");
/*  7548 */     case 18:  throw new IllegalArgumentException("a byte cannot be subtracted from a String");
/*  7549 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7551 */     int[] maxminIndices = new int[2];
/*  7552 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7553 */     am.maxIndex = maxminIndices[0];
/*  7554 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7556 */     Conv.restoreMessages();
/*  7557 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Byte constant)
/*       */   {
/*  7562 */     byte constantl = constant.byteValue();
/*  7563 */     return minus(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(byte[] arrayI)
/*       */   {
/*  7569 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7570 */     ArrayMaths am = new ArrayMaths();
/*  7571 */     am.array = new ArrayList();
/*  7572 */     am.length = this.length;
/*       */     
/*  7574 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7578 */       double[] dd = getArray_as_double();
/*  7579 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] - arrayI[i]));
/*  7580 */       am.type = 0;
/*  7581 */       break;
/*  7582 */     case 4:  long max = getMaximum_as_long();
/*  7583 */       ArrayMaths am2 = new ArrayMaths(arrayI);
/*  7584 */       long max2 = am2.getMaximum_as_long();
/*  7585 */       long[] ll = getArray_as_long();
/*  7586 */       if (Long.MAX_VALUE - max >= max2) {
/*  7587 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] - arrayI[i]));
/*  7588 */         am.type = 4;
/*       */       }
/*       */       else {
/*  7591 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] - arrayI[i]));
/*  7592 */         am.type = 0;
/*       */       }
/*  7594 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7601 */       byte maxi = getMaximum_as_byte();
/*  7602 */       ArrayMaths am22 = new ArrayMaths(arrayI);
/*  7603 */       byte maxi2 = am22.getMaximum_as_byte();
/*  7604 */       byte[] lll = getArray_as_byte();
/*  7605 */       if (Integer.MAX_VALUE - maxi >= maxi2) {
/*  7606 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] - arrayI[i]));
/*  7607 */         am.type = 6;
/*       */       }
/*       */       else {
/*  7610 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] - arrayI[i]));
/*  7611 */         am.type = 0;
/*       */       }
/*  7613 */       break;
/*  7614 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  7615 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  7616 */         hold1 = hold1.subtract(new BigDecimal(arrayI[i]));
/*  7617 */         am.array.add(hold1);
/*  7618 */         hold1 = null;
/*       */       }
/*  7620 */       am.type = 12;
/*  7621 */       break;
/*  7622 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  7623 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  7624 */         BigInteger hold2 = hold1.subtract(new BigInteger(Integer.toString(arrayI[i])));
/*  7625 */         am.array.add(hold2);
/*  7626 */         hold1 = null;
/*  7627 */         hold2 = null;
/*       */       }
/*  7629 */       am.type = 13;
/*  7630 */       break;
/*  7631 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).minus(arrayI[i]));
/*  7632 */       am.type = this.type;
/*  7633 */       break;
/*  7634 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).minus(new Phasor(arrayI[i])));
/*  7635 */       am.type = this.type;
/*  7636 */       break;
/*  7637 */     case 16:  throw new IllegalArgumentException("a byte cannot be subtracted from a char");
/*  7638 */     case 17:  throw new IllegalArgumentException("a byte cannot be subtracted from a Character");
/*  7639 */     case 18:  throw new IllegalArgumentException("a byte cannot be subtracted from a String");
/*  7640 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7642 */     int[] maxminIndices = new int[2];
/*  7643 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7644 */     am.maxIndex = maxminIndices[0];
/*  7645 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7647 */     Conv.restoreMessages();
/*  7648 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Byte[] arrayI)
/*       */   {
/*  7653 */     int nArg = arrayI.length;
/*  7654 */     if (this.length != nArg) throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*  7655 */     byte[] arrayl = new byte[this.length];
/*  7656 */     for (int i = 0; i < this.length; i++) arrayl[i] = arrayI[i].byteValue();
/*  7657 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(BigDecimal[] arrayBD)
/*       */   {
/*  7662 */     int nArg = arrayBD.length;
/*  7663 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  7665 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7666 */     ArrayMaths am = new ArrayMaths();
/*  7667 */     am.array = new ArrayList();
/*  7668 */     am.length = this.length;
/*       */     
/*  7670 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  7684 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  7685 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(arrayBD[i]));
/*  7686 */       Conv.restoreMessages();
/*  7687 */       am.type = 12;
/*  7688 */       break;
/*  7689 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7690 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(arrayBD[i].doubleValue()));
/*  7691 */       am.type = this.type;
/*  7692 */       break;
/*  7693 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7694 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(new Phasor(arrayBD[i].doubleValue())));
/*  7695 */       am.type = this.type;
/*  7696 */       break;
/*  7697 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a char");
/*  7698 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a Character");
/*  7699 */     case 18:  throw new IllegalArgumentException("a BigDecimalcannot be subtracted from a String");
/*  7700 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7702 */     int[] maxminIndices = new int[2];
/*  7703 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7704 */     am.maxIndex = maxminIndices[0];
/*  7705 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7707 */     Conv.restoreMessages();
/*  7708 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(BigInteger constant)
/*       */   {
/*  7713 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7714 */     ArrayMaths am = new ArrayMaths();
/*  7715 */     am.array = new ArrayList();
/*  7716 */     am.length = this.length;
/*  7717 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  7722 */       BigDecimal constantBD = Conv.convert_BigInteger_to_BigDecimal(constant);
/*  7723 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  7724 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(constantBD));
/*  7725 */       am.type = 12;
/*  7726 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  7735 */       BigInteger[] bi = getArray_as_BigInteger();
/*  7736 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].subtract(constant));
/*  7737 */       am.type = 13;
/*  7738 */       break;
/*  7739 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7740 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(Conv.convert_BigInteger_to_double(constant)));
/*  7741 */       am.type = this.type;
/*  7742 */       break;
/*  7743 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7744 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(new Phasor(Conv.convert_BigInteger_to_double(constant))));
/*  7745 */       am.type = this.type;
/*  7746 */       break;
/*  7747 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a char");
/*  7748 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a Character");
/*  7749 */     case 18:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a String");
/*  7750 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7752 */     int[] maxminIndices = new int[2];
/*  7753 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7754 */     am.maxIndex = maxminIndices[0];
/*  7755 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7757 */     Conv.restoreMessages();
/*  7758 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(BigInteger[] arrayBI)
/*       */   {
/*  7763 */     int nArg = arrayBI.length;
/*  7764 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  7766 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7767 */     ArrayMaths am = new ArrayMaths();
/*  7768 */     am.array = new ArrayList();
/*  7769 */     am.length = this.length;
/*  7770 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  7775 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  7776 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(Conv.convert_BigInteger_to_BigDecimal(arrayBI[i])));
/*  7777 */       am.type = 12;
/*  7778 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  7787 */       BigInteger[] bi = getArray_as_BigInteger();
/*  7788 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].add(arrayBI[i]));
/*  7789 */       am.type = 13;
/*  7790 */       break;
/*  7791 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7792 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(Conv.convert_BigInteger_to_double(arrayBI[i])));
/*  7793 */       am.type = this.type;
/*  7794 */       break;
/*  7795 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7796 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(new Phasor(Conv.convert_BigInteger_to_double(arrayBI[i]))));
/*  7797 */       am.type = this.type;
/*  7798 */       break;
/*  7799 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a char");
/*  7800 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a Character");
/*  7801 */     case 18:  throw new IllegalArgumentException("a BigInteger cannot be subtracted from a String");
/*  7802 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7804 */     int[] maxminIndices = new int[2];
/*  7805 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  7806 */     am.maxIndex = maxminIndices[0];
/*  7807 */     am.minIndex = maxminIndices[1];
/*       */     
/*  7809 */     Conv.restoreMessages();
/*  7810 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Complex constant)
/*       */   {
/*  7815 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7816 */     ArrayMaths am = new ArrayMaths();
/*  7817 */     am.array = new ArrayList();
/*  7818 */     am.length = this.length;
/*  7819 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  7834 */       Complex[] cc = getArray_as_Complex();
/*  7835 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(constant));
/*  7836 */       am.type = 14;
/*  7837 */       break;
/*  7838 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7839 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(Conv.convert_Complex_to_Phasor(constant)));
/*  7840 */       am.type = this.type;
/*  7841 */       break;
/*  7842 */     case 16:  throw new IllegalArgumentException("a Complex cannot be subtracted from a char");
/*  7843 */     case 17:  throw new IllegalArgumentException("a Complex cannot be subtracted from a Character");
/*  7844 */     case 18:  throw new IllegalArgumentException("a Complex cannot be subtracted from a String");
/*  7845 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7847 */     Conv.restoreMessages();
/*  7848 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Complex[] arrayC)
/*       */   {
/*  7853 */     int nArg = arrayC.length;
/*  7854 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  7856 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7857 */     ArrayMaths am = new ArrayMaths();
/*  7858 */     am.array = new ArrayList();
/*  7859 */     am.length = this.length;
/*  7860 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  7875 */       Complex[] cc = getArray_as_Complex();
/*  7876 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(arrayC[i]));
/*  7877 */       am.type = 14;
/*  7878 */       break;
/*  7879 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  7880 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(Conv.convert_Complex_to_Phasor(arrayC[i])));
/*  7881 */       am.type = this.type;
/*  7882 */       break;
/*  7883 */     case 16:  throw new IllegalArgumentException("a Complex cannot be subtracted from a char");
/*  7884 */     case 17:  throw new IllegalArgumentException("a Complex cannot be subtracted from a Character");
/*  7885 */     case 18:  throw new IllegalArgumentException("a Complex cannot be subtracted from a String");
/*  7886 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7888 */     Conv.restoreMessages();
/*  7889 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Phasor constant)
/*       */   {
/*  7894 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7895 */     ArrayMaths am = new ArrayMaths();
/*  7896 */     am.array = new ArrayList();
/*  7897 */     am.length = this.length;
/*  7898 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  7913 */       Phasor[] pp = getArray_as_Phasor();
/*  7914 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(constant));
/*  7915 */       am.type = 15;
/*  7916 */       break;
/*  7917 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7918 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(Conv.convert_Phasor_to_Complex(constant)));
/*  7919 */       am.type = this.type;
/*  7920 */       break;
/*  7921 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a char");
/*  7922 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a Character");
/*  7923 */     case 18:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a String");
/*  7924 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7926 */     Conv.restoreMessages();
/*  7927 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths minus(Phasor[] arrayP)
/*       */   {
/*  7933 */     int nArg = arrayP.length;
/*  7934 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/*  7936 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7937 */     ArrayMaths am = new ArrayMaths();
/*  7938 */     am.array = new ArrayList();
/*  7939 */     am.length = this.length;
/*  7940 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  7955 */       Phasor[] pp = getArray_as_Phasor();
/*  7956 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].minus(arrayP[i]));
/*  7957 */       am.type = 15;
/*  7958 */       break;
/*  7959 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  7960 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].minus(Conv.convert_Phasor_to_Complex(arrayP[i])));
/*  7961 */       am.type = this.type;
/*  7962 */       break;
/*  7963 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a char");
/*  7964 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a Character");
/*  7965 */     case 18:  throw new IllegalArgumentException("a Phasor cannot be subtracted from a String");
/*  7966 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  7968 */     Conv.restoreMessages();
/*  7969 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Vector<Object> vec)
/*       */   {
/*  7974 */     if (this.suppressMessages) Conv.suppressMessages();
/*  7975 */     ArrayMaths am1 = new ArrayMaths();
/*  7976 */     ArrayMaths am2 = new ArrayMaths(vec);
/*       */     
/*  7978 */     switch (am2.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  7982 */       double[] dd = am2.getArray_as_double();
/*  7983 */       am1 = minus(dd);
/*  7984 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  7992 */       long[] ll = am2.getArray_as_long();
/*  7993 */       am1 = minus(ll);
/*  7994 */       break;
/*  7995 */     case 12:  BigDecimal[] bd = am2.getArray_as_BigDecimal();
/*  7996 */       am1 = minus(bd);
/*  7997 */       break;
/*  7998 */     case 13:  BigInteger[] bi = am2.getArray_as_BigInteger();
/*  7999 */       am1 = minus(bi);
/*  8000 */       break;
/*  8001 */     case 14:  Complex[] cc = am2.getArray_as_Complex();
/*  8002 */       am1 = minus(cc);
/*  8003 */       break;
/*  8004 */     case 15:  Phasor[] pp = am2.getArray_as_Phasor();
/*  8005 */       am1 = minus(pp);
/*  8006 */       break;
/*  8007 */     case 16:  throw new IllegalArgumentException("ArrayList/char subtraction not allowed");
/*  8008 */     case 17:  throw new IllegalArgumentException("ArrayList/Character subtraction not allowed");
/*  8009 */     case 18:  throw new IllegalArgumentException("ArrayList/String subtraction not allowed");
/*  8010 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8012 */     int[] maxminIndices = new int[2];
/*  8013 */     findMinMax(am1.getArray_as_Object(), am1.minmax, maxminIndices, am1.typeName, am1.type);
/*  8014 */     am1.maxIndex = maxminIndices[0];
/*  8015 */     am1.minIndex = maxminIndices[1];
/*       */     
/*  8017 */     Conv.restoreMessages();
/*  8018 */     return am1;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(ArrayList<Object> list)
/*       */   {
/*  8023 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8024 */     ArrayMaths am1 = new ArrayMaths();
/*  8025 */     ArrayMaths am2 = new ArrayMaths(list);
/*       */     
/*  8027 */     switch (am2.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8031 */       double[] dd = am2.getArray_as_double();
/*  8032 */       am1 = minus(dd);
/*  8033 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8041 */       long[] ll = am2.getArray_as_long();
/*  8042 */       am1 = minus(ll);
/*  8043 */       break;
/*  8044 */     case 12:  BigDecimal[] bd = am2.getArray_as_BigDecimal();
/*  8045 */       am1 = minus(bd);
/*  8046 */       break;
/*  8047 */     case 13:  BigInteger[] bi = am2.getArray_as_BigInteger();
/*  8048 */       am1 = minus(bi);
/*  8049 */       break;
/*  8050 */     case 14:  Complex[] cc = am2.getArray_as_Complex();
/*  8051 */       am1 = minus(cc);
/*  8052 */       break;
/*  8053 */     case 15:  Phasor[] pp = am2.getArray_as_Phasor();
/*  8054 */       am1 = minus(pp);
/*  8055 */       break;
/*  8056 */     case 16:  throw new IllegalArgumentException("ArrayList/char subtraction not allowed");
/*  8057 */     case 17:  throw new IllegalArgumentException("ArrayList/Character subtraction not allowed");
/*  8058 */     case 18:  throw new IllegalArgumentException("ArrayList/String subtraction not allowed");
/*  8059 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8061 */     int[] maxminIndices = new int[2];
/*  8062 */     findMinMax(am1.getArray_as_Object(), am1.minmax, maxminIndices, am1.typeName, am1.type);
/*  8063 */     am1.maxIndex = maxminIndices[0];
/*  8064 */     am1.minIndex = maxminIndices[1];
/*       */     
/*  8066 */     Conv.restoreMessages();
/*  8067 */     return am1;
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(ArrayMaths arrayM)
/*       */   {
/*  8072 */     ArrayList<Object> arrayl = arrayM.getArray_as_ArrayList();
/*  8073 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */   public ArrayMaths minus(Stat arrayS)
/*       */   {
/*  8078 */     ArrayList<Object> arrayl = arrayS.getArray_as_ArrayList();
/*  8079 */     return minus(arrayl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths times(double constant)
/*       */   {
/*  8085 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8086 */     ArrayMaths am = new ArrayMaths();
/*  8087 */     am.array = new ArrayList();
/*  8088 */     am.length = this.length;
/*       */     
/*  8090 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8102 */       double[] dd = getArray_as_double();
/*  8103 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * constant));
/*  8104 */       am.type = 0;
/*  8105 */       break;
/*  8106 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8107 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8108 */         hold1 = hold1.multiply(new BigDecimal(constant));
/*  8109 */         am.array.add(hold1);
/*  8110 */         hold1 = null;
/*       */       }
/*  8112 */       am.type = this.type;
/*  8113 */       break;
/*  8114 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8115 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8116 */         BigDecimal hold2 = new BigDecimal(hold1).multiply(new BigDecimal(constant));
/*  8117 */         am.array.add(hold2);
/*  8118 */         hold1 = null;
/*  8119 */         hold2 = null;
/*       */       }
/*  8121 */       am.type = 12;
/*  8122 */       break;
/*  8123 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).times(constant));
/*  8124 */       am.type = this.type;
/*  8125 */       break;
/*  8126 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).times(new Complex(constant)));
/*  8127 */       am.type = this.type;
/*  8128 */       break;
/*  8129 */     case 16:  throw new IllegalArgumentException("a double or float cannot be multiplied by a char");
/*  8130 */     case 17:  throw new IllegalArgumentException("a double or float cannot be multiplied by a Character");
/*  8131 */     case 18:  throw new IllegalArgumentException("a double or float cannot be multiplied by a String");
/*  8132 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8134 */     int[] maxminIndices = new int[2];
/*  8135 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8136 */     am.maxIndex = maxminIndices[0];
/*  8137 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8139 */     Conv.restoreMessages();
/*  8140 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Double constant)
/*       */   {
/*  8145 */     return times(constant.doubleValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths times(float constant)
/*       */   {
/*  8150 */     double constantd = constant;
/*  8151 */     return times(constantd);
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Float constant)
/*       */   {
/*  8156 */     return times(constant.floatValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths times(long constant)
/*       */   {
/*  8161 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8162 */     ArrayMaths am = new ArrayMaths();
/*  8163 */     am.array = new ArrayList();
/*  8164 */     am.length = this.length;
/*       */     
/*  8166 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8170 */       double[] dd = getArray_as_double();
/*  8171 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * constant));
/*  8172 */       am.type = 0;
/*  8173 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8181 */       long max = getMaximum_as_long();
/*  8182 */       long[] ll = getArray_as_long();
/*  8183 */       if (Long.MAX_VALUE - max >= constant) {
/*  8184 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] * constant));
/*  8185 */         am.type = 4;
/*       */       }
/*       */       else {
/*  8188 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] * constant));
/*  8189 */         am.type = 0;
/*       */       }
/*  8191 */       break;
/*  8192 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8193 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8194 */         hold1 = hold1.multiply(new BigDecimal(constant));
/*  8195 */         am.array.add(hold1);
/*  8196 */         hold1 = null;
/*       */       }
/*  8198 */       am.type = 12;
/*  8199 */       break;
/*  8200 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8201 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8202 */         BigInteger hold2 = hold1.multiply(new BigInteger(Long.toString(constant)));
/*  8203 */         am.array.add(hold2);
/*  8204 */         hold1 = null;
/*  8205 */         hold2 = null;
/*       */       }
/*  8207 */       am.type = 13;
/*  8208 */       break;
/*  8209 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).times(constant));
/*  8210 */       am.type = this.type;
/*  8211 */       break;
/*  8212 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).times(new Phasor(constant)));
/*  8213 */       am.type = this.type;
/*  8214 */       break;
/*  8215 */     case 16:  throw new IllegalArgumentException("a long cannot be multiplied by a char");
/*  8216 */     case 17:  throw new IllegalArgumentException("a long cannot be multiplied by a Character");
/*  8217 */     case 18:  throw new IllegalArgumentException("a long cannot be multiplied by a String");
/*  8218 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8220 */     int[] maxminIndices = new int[2];
/*  8221 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8222 */     am.maxIndex = maxminIndices[0];
/*  8223 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8225 */     Conv.restoreMessages();
/*  8226 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Long constant)
/*       */   {
/*  8231 */     long constantl = constant.longValue();
/*  8232 */     return times(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths times(int constant)
/*       */   {
/*  8237 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8238 */     ArrayMaths am = new ArrayMaths();
/*  8239 */     am.array = new ArrayList();
/*  8240 */     am.length = this.length;
/*       */     
/*  8242 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8246 */       double[] dd = getArray_as_double();
/*  8247 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * constant));
/*  8248 */       am.type = 0;
/*  8249 */       break;
/*  8250 */     case 4:  long max = getMaximum_as_long();
/*  8251 */       long[] ll = getArray_as_long();
/*  8252 */       if (Long.MAX_VALUE - max >= constant) {
/*  8253 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] * constant));
/*  8254 */         am.type = 4;
/*       */       }
/*       */       else {
/*  8257 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] * constant));
/*  8258 */         am.type = 0;
/*       */       }
/*  8260 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8267 */       int maxi = getMaximum_as_int();
/*  8268 */       int[] lll = getArray_as_int();
/*  8269 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  8270 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] * constant));
/*  8271 */         am.type = 6;
/*       */       }
/*       */       else {
/*  8274 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] * constant));
/*  8275 */         am.type = 0;
/*       */       }
/*  8277 */       break;
/*  8278 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8279 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8280 */         hold1 = hold1.multiply(new BigDecimal(constant));
/*  8281 */         am.array.add(hold1);
/*  8282 */         hold1 = null;
/*       */       }
/*  8284 */       am.type = 12;
/*  8285 */       break;
/*  8286 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8287 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8288 */         BigInteger hold2 = hold1.multiply(new BigInteger(Integer.toString(constant)));
/*  8289 */         am.array.add(hold2);
/*  8290 */         hold1 = null;
/*  8291 */         hold2 = null;
/*       */       }
/*  8293 */       am.type = 13;
/*  8294 */       break;
/*  8295 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).times(constant));
/*  8296 */       am.type = this.type;
/*  8297 */       break;
/*  8298 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).times(new Phasor(constant)));
/*  8299 */       am.type = this.type;
/*  8300 */       break;
/*  8301 */     case 16:  throw new IllegalArgumentException("an int cannot be multiplied by a char");
/*  8302 */     case 17:  throw new IllegalArgumentException("an int cannot be multiplied by a Character");
/*  8303 */     case 18:  throw new IllegalArgumentException("an int cannot be multiplied by a String");
/*  8304 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8306 */     int[] maxminIndices = new int[2];
/*  8307 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8308 */     am.maxIndex = maxminIndices[0];
/*  8309 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8311 */     Conv.restoreMessages();
/*  8312 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Integer constant)
/*       */   {
/*  8317 */     int constantl = constant.intValue();
/*  8318 */     return times(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths times(short constant)
/*       */   {
/*  8323 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8324 */     ArrayMaths am = new ArrayMaths();
/*  8325 */     am.array = new ArrayList();
/*  8326 */     am.length = this.length;
/*       */     
/*  8328 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8332 */       double[] dd = getArray_as_double();
/*  8333 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * constant));
/*  8334 */       am.type = 0;
/*  8335 */       break;
/*  8336 */     case 4:  long max = getMaximum_as_long();
/*  8337 */       long[] ll = getArray_as_long();
/*  8338 */       if (Long.MAX_VALUE - max >= constant) {
/*  8339 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] * constant));
/*  8340 */         am.type = 4;
/*       */       }
/*       */       else {
/*  8343 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] * constant));
/*  8344 */         am.type = 0;
/*       */       }
/*  8346 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8353 */       short maxi = getMaximum_as_short();
/*  8354 */       short[] lll = getArray_as_short();
/*  8355 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  8356 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] * constant));
/*  8357 */         am.type = 6;
/*       */       }
/*       */       else {
/*  8360 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] * constant));
/*  8361 */         am.type = 0;
/*       */       }
/*  8363 */       break;
/*  8364 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8365 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8366 */         hold1 = hold1.multiply(new BigDecimal(constant));
/*  8367 */         am.array.add(hold1);
/*  8368 */         hold1 = null;
/*       */       }
/*  8370 */       am.type = 12;
/*  8371 */       break;
/*  8372 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8373 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8374 */         BigInteger hold2 = hold1.multiply(new BigInteger(Integer.toString(constant)));
/*  8375 */         am.array.add(hold2);
/*  8376 */         hold1 = null;
/*  8377 */         hold2 = null;
/*       */       }
/*  8379 */       am.type = 13;
/*  8380 */       break;
/*  8381 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).times(constant));
/*  8382 */       am.type = this.type;
/*  8383 */       break;
/*  8384 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).times(new Phasor(constant)));
/*  8385 */       am.type = this.type;
/*  8386 */       break;
/*  8387 */     case 16:  throw new IllegalArgumentException("a short cannot be multiplied by a char");
/*  8388 */     case 17:  throw new IllegalArgumentException("a short cannot be multiplied by a Character");
/*  8389 */     case 18:  throw new IllegalArgumentException("a short cannot be multiplied by a String");
/*  8390 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8392 */     int[] maxminIndices = new int[2];
/*  8393 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8394 */     am.maxIndex = maxminIndices[0];
/*  8395 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8397 */     Conv.restoreMessages();
/*  8398 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Short constant)
/*       */   {
/*  8403 */     short constantl = constant.shortValue();
/*  8404 */     return times(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths times(BigDecimal constant)
/*       */   {
/*  8410 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8411 */     ArrayMaths am = new ArrayMaths();
/*  8412 */     am.array = new ArrayList();
/*  8413 */     am.length = this.length;
/*  8414 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  8428 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  8429 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].multiply(constant));
/*  8430 */       am.type = 12;
/*  8431 */       break;
/*  8432 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  8433 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(constant.doubleValue()));
/*  8434 */       am.type = this.type;
/*  8435 */       break;
/*  8436 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  8437 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(new Phasor(constant.doubleValue())));
/*  8438 */       am.type = this.type;
/*  8439 */       break;
/*  8440 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a char");
/*  8441 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a Character");
/*  8442 */     case 18:  throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a String");
/*  8443 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8445 */     int[] maxminIndices = new int[2];
/*  8446 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8447 */     am.maxIndex = maxminIndices[0];
/*  8448 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8450 */     Conv.restoreMessages();
/*  8451 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(byte constant)
/*       */   {
/*  8456 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8457 */     ArrayMaths am = new ArrayMaths();
/*  8458 */     am.array = new ArrayList();
/*  8459 */     am.length = this.length;
/*       */     
/*  8461 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8465 */       double[] dd = getArray_as_double();
/*  8466 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * constant));
/*  8467 */       am.type = 0;
/*  8468 */       break;
/*  8469 */     case 4:  long max = getMaximum_as_long();
/*  8470 */       long[] ll = getArray_as_long();
/*  8471 */       if (Long.MAX_VALUE - max >= constant) {
/*  8472 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] * constant));
/*  8473 */         am.type = 4;
/*       */       }
/*       */       else {
/*  8476 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] * constant));
/*  8477 */         am.type = 0;
/*       */       }
/*  8479 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8486 */       byte maxi = getMaximum_as_byte();
/*  8487 */       byte[] lll = getArray_as_byte();
/*  8488 */       if (Integer.MAX_VALUE - maxi >= constant) {
/*  8489 */         for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] * constant));
/*  8490 */         am.type = 6;
/*       */       }
/*       */       else {
/*  8493 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] * constant));
/*  8494 */         am.type = 0;
/*       */       }
/*  8496 */       break;
/*  8497 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8498 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8499 */         hold1 = hold1.multiply(new BigDecimal(constant));
/*  8500 */         am.array.add(hold1);
/*  8501 */         hold1 = null;
/*       */       }
/*  8503 */       am.type = 12;
/*  8504 */       break;
/*  8505 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8506 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8507 */         BigInteger hold2 = hold1.multiply(new BigInteger(Integer.toString(constant)));
/*  8508 */         am.array.add(hold2);
/*  8509 */         hold1 = null;
/*  8510 */         hold2 = null;
/*       */       }
/*  8512 */       am.type = 13;
/*  8513 */       break;
/*  8514 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).times(constant));
/*  8515 */       am.type = this.type;
/*  8516 */       break;
/*  8517 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).times(new Phasor(constant)));
/*  8518 */       am.type = this.type;
/*  8519 */       break;
/*  8520 */     case 16:  throw new IllegalArgumentException("a byte cannot be multiplied by a char");
/*  8521 */     case 17:  throw new IllegalArgumentException("a byte cannot be multiplied by a Character");
/*  8522 */     case 18:  throw new IllegalArgumentException("a byte cannot be multiplied by a String");
/*  8523 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8525 */     int[] maxminIndices = new int[2];
/*  8526 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8527 */     am.maxIndex = maxminIndices[0];
/*  8528 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8530 */     Conv.restoreMessages();
/*  8531 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths times(Byte constant)
/*       */   {
/*  8536 */     byte constantl = constant.byteValue();
/*  8537 */     return times(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths times(BigInteger constant)
/*       */   {
/*  8543 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8544 */     ArrayMaths am = new ArrayMaths();
/*  8545 */     am.array = new ArrayList();
/*  8546 */     am.length = this.length;
/*  8547 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  8552 */       BigDecimal constantBD = Conv.convert_BigInteger_to_BigDecimal(constant);
/*  8553 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  8554 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(constantBD));
/*  8555 */       am.type = 12;
/*  8556 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  8565 */       BigInteger[] bi = getArray_as_BigInteger();
/*  8566 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].multiply(constant));
/*  8567 */       am.type = 13;
/*  8568 */       break;
/*  8569 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  8570 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(Conv.convert_BigInteger_to_double(constant)));
/*  8571 */       am.type = this.type;
/*  8572 */       break;
/*  8573 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  8574 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(new Phasor(Conv.convert_BigInteger_to_double(constant))));
/*  8575 */       am.type = this.type;
/*  8576 */       break;
/*  8577 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be multiplied by a char");
/*  8578 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be multiplied by a Character");
/*  8579 */     case 18:  throw new IllegalArgumentException("a BigInteger cannot be multiplied by a String");
/*  8580 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8582 */     int[] maxminIndices = new int[2];
/*  8583 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8584 */     am.maxIndex = maxminIndices[0];
/*  8585 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8587 */     Conv.restoreMessages();
/*  8588 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths times(Complex constant)
/*       */   {
/*  8594 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8595 */     ArrayMaths am = new ArrayMaths();
/*  8596 */     am.array = new ArrayList();
/*  8597 */     am.length = this.length;
/*  8598 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  8613 */       Complex[] cc = getArray_as_Complex();
/*  8614 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(constant));
/*  8615 */       am.type = 14;
/*  8616 */       break;
/*  8617 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  8618 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(Conv.convert_Complex_to_Phasor(constant)));
/*  8619 */       am.type = this.type;
/*  8620 */       break;
/*  8621 */     case 16:  throw new IllegalArgumentException("a Complex cannot be multiplied by a char");
/*  8622 */     case 17:  throw new IllegalArgumentException("a Complex cannot be multiplied by a Character");
/*  8623 */     case 18:  throw new IllegalArgumentException("a Complex cannot be multiplied by a String");
/*  8624 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8626 */     Conv.restoreMessages();
/*  8627 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths times(Phasor constant)
/*       */   {
/*  8633 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8634 */     ArrayMaths am = new ArrayMaths();
/*  8635 */     am.array = new ArrayList();
/*  8636 */     am.length = this.length;
/*  8637 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  8652 */       Phasor[] pp = getArray_as_Phasor();
/*  8653 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(constant));
/*  8654 */       am.type = 15;
/*  8655 */       break;
/*  8656 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  8657 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(Conv.convert_Phasor_to_Complex(constant)));
/*  8658 */       am.type = this.type;
/*  8659 */       break;
/*  8660 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be multiplied by a char");
/*  8661 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be multiplied by a Character");
/*  8662 */     case 18:  throw new IllegalArgumentException("a Phasor cannot be multiplied by a String");
/*  8663 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8665 */     Conv.restoreMessages();
/*  8666 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public ArrayMaths over(double constant)
/*       */   {
/*  8674 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8675 */     ArrayMaths am = new ArrayMaths();
/*  8676 */     am.array = new ArrayList();
/*  8677 */     am.length = this.length;
/*       */     
/*  8679 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8691 */       double[] dd = getArray_as_double();
/*  8692 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] / constant));
/*  8693 */       am.type = 0;
/*  8694 */       break;
/*  8695 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8696 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8697 */         hold1 = hold1.divide(new BigDecimal(constant), 4);
/*  8698 */         am.array.add(hold1);
/*  8699 */         hold1 = null;
/*       */       }
/*  8701 */       am.type = this.type;
/*  8702 */       break;
/*  8703 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8704 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8705 */         BigDecimal hold2 = new BigDecimal(hold1).divide(new BigDecimal(constant), 4);
/*  8706 */         am.array.add(hold2);
/*  8707 */         hold1 = null;
/*  8708 */         hold2 = null;
/*       */       }
/*  8710 */       am.type = 12;
/*  8711 */       break;
/*  8712 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).over(constant));
/*  8713 */       am.type = this.type;
/*  8714 */       break;
/*  8715 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).over(new Complex(constant)));
/*  8716 */       am.type = this.type;
/*  8717 */       break;
/*  8718 */     case 16:  throw new IllegalArgumentException("a double or float cannot be divided by a char");
/*  8719 */     case 17:  throw new IllegalArgumentException("a double or float cannot be divided by a Character");
/*  8720 */     case 18:  throw new IllegalArgumentException("a double or float cannot be divided by a String");
/*  8721 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8723 */     int[] maxminIndices = new int[2];
/*  8724 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8725 */     am.maxIndex = maxminIndices[0];
/*  8726 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8728 */     Conv.restoreMessages();
/*  8729 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Double constant)
/*       */   {
/*  8734 */     return over(constant.doubleValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths over(float constant)
/*       */   {
/*  8739 */     double constantd = constant;
/*  8740 */     return over(constantd);
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Float constant)
/*       */   {
/*  8745 */     return over(constant.floatValue());
/*       */   }
/*       */   
/*       */   public ArrayMaths over(long constant)
/*       */   {
/*  8750 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8751 */     ArrayMaths am = new ArrayMaths();
/*  8752 */     am.array = new ArrayList();
/*  8753 */     am.length = this.length;
/*       */     
/*  8755 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8759 */       double[] dd = getArray_as_double();
/*  8760 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] / constant));
/*  8761 */       am.type = 0;
/*  8762 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8770 */       long max = getMaximum_as_long();
/*  8771 */       long[] ll = getArray_as_long();
/*  8772 */       if (Long.MAX_VALUE - max >= constant) {
/*  8773 */         for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] / constant));
/*  8774 */         am.type = 4;
/*       */       }
/*       */       else {
/*  8777 */         for (int i = 0; i < this.length; i++) am.array.add(new Double(ll[i] / constant));
/*  8778 */         am.type = 0;
/*       */       }
/*  8780 */       break;
/*  8781 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8782 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8783 */         hold1 = hold1.divide(new BigDecimal(constant), 4);
/*  8784 */         am.array.add(hold1);
/*  8785 */         hold1 = null;
/*       */       }
/*  8787 */       am.type = 12;
/*  8788 */       break;
/*  8789 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8790 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8791 */         BigInteger hold2 = hold1.divide(new BigInteger(Long.toString(constant)));
/*  8792 */         am.array.add(hold2);
/*  8793 */         hold1 = null;
/*  8794 */         hold2 = null;
/*       */       }
/*  8796 */       am.type = 13;
/*  8797 */       break;
/*  8798 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).over(constant));
/*  8799 */       am.type = this.type;
/*  8800 */       break;
/*  8801 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).over(new Phasor(constant)));
/*  8802 */       am.type = this.type;
/*  8803 */       break;
/*  8804 */     case 16:  throw new IllegalArgumentException("a long cannot be divided by a char");
/*  8805 */     case 17:  throw new IllegalArgumentException("a long cannot be divided by a Character");
/*  8806 */     case 18:  throw new IllegalArgumentException("a long cannot be divided by a String");
/*  8807 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8809 */     int[] maxminIndices = new int[2];
/*  8810 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8811 */     am.maxIndex = maxminIndices[0];
/*  8812 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8814 */     Conv.restoreMessages();
/*  8815 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Long constant)
/*       */   {
/*  8820 */     long constantl = constant.longValue();
/*  8821 */     return over(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths over(int constant)
/*       */   {
/*  8827 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8828 */     ArrayMaths am = new ArrayMaths();
/*  8829 */     am.array = new ArrayList();
/*  8830 */     am.length = this.length;
/*       */     
/*  8832 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8836 */       double[] dd = getArray_as_double();
/*  8837 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] / constant));
/*  8838 */       am.type = 0;
/*  8839 */       break;
/*  8840 */     case 4:  long max = getMaximum_as_long();
/*  8841 */       long[] ll = getArray_as_long();
/*  8842 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] / constant));
/*  8843 */       am.type = 4;
/*  8844 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8851 */       int maxi = getMaximum_as_int();
/*  8852 */       int[] lll = getArray_as_int();
/*  8853 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] / constant));
/*  8854 */       am.type = 6;
/*  8855 */       break;
/*  8856 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8857 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8858 */         hold1 = hold1.divide(new BigDecimal(constant), 4);
/*  8859 */         am.array.add(hold1);
/*  8860 */         hold1 = null;
/*       */       }
/*  8862 */       am.type = 12;
/*  8863 */       break;
/*  8864 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8865 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8866 */         BigInteger hold2 = hold1.divide(new BigInteger(Integer.toString(constant)));
/*  8867 */         am.array.add(hold2);
/*  8868 */         hold1 = null;
/*  8869 */         hold2 = null;
/*       */       }
/*  8871 */       am.type = 13;
/*  8872 */       break;
/*  8873 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).over(constant));
/*  8874 */       am.type = this.type;
/*  8875 */       break;
/*  8876 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).over(new Phasor(constant)));
/*  8877 */       am.type = this.type;
/*  8878 */       break;
/*  8879 */     case 16:  throw new IllegalArgumentException("an int cannot be divided by a char");
/*  8880 */     case 17:  throw new IllegalArgumentException("an int cannot be divided by a Character");
/*  8881 */     case 18:  throw new IllegalArgumentException("an int cannot be divided by a String");
/*  8882 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8884 */     int[] maxminIndices = new int[2];
/*  8885 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8886 */     am.maxIndex = maxminIndices[0];
/*  8887 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8889 */     Conv.restoreMessages();
/*  8890 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Integer constant)
/*       */   {
/*  8895 */     int constantl = constant.intValue();
/*  8896 */     return over(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths over(short constant)
/*       */   {
/*  8901 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8902 */     ArrayMaths am = new ArrayMaths();
/*  8903 */     am.array = new ArrayList();
/*  8904 */     am.length = this.length;
/*       */     
/*  8906 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  8910 */       double[] dd = getArray_as_double();
/*  8911 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] / constant));
/*  8912 */       am.type = 0;
/*  8913 */       break;
/*  8914 */     case 4:  long max = getMaximum_as_long();
/*  8915 */       long[] ll = getArray_as_long();
/*  8916 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] / constant));
/*  8917 */       am.type = 4;
/*  8918 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  8925 */       short maxi = getMaximum_as_short();
/*  8926 */       short[] lll = getArray_as_short();
/*  8927 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(lll[i] / constant));
/*  8928 */       am.type = 6;
/*  8929 */       break;
/*  8930 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  8931 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  8932 */         hold1 = hold1.divide(new BigDecimal(constant), 4);
/*  8933 */         am.array.add(hold1);
/*  8934 */         hold1 = null;
/*       */       }
/*  8936 */       am.type = 12;
/*  8937 */       break;
/*  8938 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  8939 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  8940 */         BigInteger hold2 = hold1.divide(new BigInteger(Integer.toString(constant)));
/*  8941 */         am.array.add(hold2);
/*  8942 */         hold1 = null;
/*  8943 */         hold2 = null;
/*       */       }
/*  8945 */       am.type = 13;
/*  8946 */       break;
/*  8947 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).over(constant));
/*  8948 */       am.type = this.type;
/*  8949 */       break;
/*  8950 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).over(new Phasor(constant)));
/*  8951 */       am.type = this.type;
/*  8952 */       break;
/*  8953 */     case 16:  throw new IllegalArgumentException("a short cannot be divided by a char");
/*  8954 */     case 17:  throw new IllegalArgumentException("a short cannot be divided by a Character");
/*  8955 */     case 18:  throw new IllegalArgumentException("a short cannot be divided by a String");
/*  8956 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  8958 */     int[] maxminIndices = new int[2];
/*  8959 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  8960 */     am.maxIndex = maxminIndices[0];
/*  8961 */     am.minIndex = maxminIndices[1];
/*       */     
/*  8963 */     Conv.restoreMessages();
/*  8964 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Short constant)
/*       */   {
/*  8969 */     short constantl = constant.shortValue();
/*  8970 */     return over(constantl);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths over(BigDecimal constant)
/*       */   {
/*  8976 */     if (this.suppressMessages) Conv.suppressMessages();
/*  8977 */     ArrayMaths am = new ArrayMaths();
/*  8978 */     am.array = new ArrayList();
/*  8979 */     am.length = this.length;
/*  8980 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*  8994 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  8995 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].divide(constant, 4));
/*  8996 */       am.type = 12;
/*  8997 */       break;
/*  8998 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  8999 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].over(constant.doubleValue()));
/*  9000 */       am.type = this.type;
/*  9001 */       break;
/*  9002 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9003 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].over(new Phasor(constant.doubleValue())));
/*  9004 */       am.type = this.type;
/*  9005 */       break;
/*  9006 */     case 16:  throw new IllegalArgumentException("a BigDecimal cannot be divided by a char");
/*  9007 */     case 17:  throw new IllegalArgumentException("a BigDecimal cannot be divided by a Character");
/*  9008 */     case 18:  throw new IllegalArgumentException("a BigDecimal cannot be divided by a String");
/*  9009 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9011 */     int[] maxminIndices = new int[2];
/*  9012 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9013 */     am.maxIndex = maxminIndices[0];
/*  9014 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9016 */     Conv.restoreMessages();
/*  9017 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(byte constant)
/*       */   {
/*  9022 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9023 */     ArrayMaths am = new ArrayMaths();
/*  9024 */     am.array = new ArrayList();
/*  9025 */     am.length = this.length;
/*       */     
/*  9027 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*  9031 */       double[] dd = getArray_as_double();
/*  9032 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] / constant));
/*  9033 */       am.type = 0;
/*  9034 */       break;
/*  9035 */     case 4:  long max = getMaximum_as_long();
/*  9036 */       long[] ll = getArray_as_long();
/*  9037 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i] / constant));
/*  9038 */       am.type = 4;
/*  9039 */       break;
/*       */     case 5: case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*  9046 */       byte maxi = getMaximum_as_byte();
/*  9047 */       byte[] lll = getArray_as_byte();
/*  9048 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(lll[i] / constant));
/*  9049 */       am.type = 0;
/*  9050 */       break;
/*  9051 */     case 12:  for (int i = 0; i < this.length; i++) {
/*  9052 */         BigDecimal hold1 = (BigDecimal)this.array.get(i);
/*  9053 */         hold1 = hold1.divide(new BigDecimal(constant), 4);
/*  9054 */         am.array.add(hold1);
/*  9055 */         hold1 = null;
/*       */       }
/*  9057 */       am.type = 12;
/*  9058 */       break;
/*  9059 */     case 13:  for (int i = 0; i < this.length; i++) {
/*  9060 */         BigInteger hold1 = (BigInteger)this.array.get(i);
/*  9061 */         BigInteger hold2 = hold1.divide(new BigInteger(Integer.toString(constant)));
/*  9062 */         am.array.add(hold2);
/*  9063 */         hold1 = null;
/*  9064 */         hold2 = null;
/*       */       }
/*  9066 */       am.type = 13;
/*  9067 */       break;
/*  9068 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add(((Complex)this.array.get(i)).over(constant));
/*  9069 */       am.type = this.type;
/*  9070 */       break;
/*  9071 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add(((Phasor)this.array.get(i)).over(new Phasor(constant)));
/*  9072 */       am.type = this.type;
/*  9073 */       break;
/*  9074 */     case 16:  throw new IllegalArgumentException("a byte cannot be divided by a char");
/*  9075 */     case 17:  throw new IllegalArgumentException("a byte cannot be divided by a Character");
/*  9076 */     case 18:  throw new IllegalArgumentException("a byte cannot be divided by a String");
/*  9077 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9079 */     int[] maxminIndices = new int[2];
/*  9080 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9081 */     am.maxIndex = maxminIndices[0];
/*  9082 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9084 */     Conv.restoreMessages();
/*  9085 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Byte constant)
/*       */   {
/*  9090 */     byte constantl = constant.byteValue();
/*  9091 */     return over(constantl);
/*       */   }
/*       */   
/*       */   public ArrayMaths over(BigInteger constant)
/*       */   {
/*  9096 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9097 */     ArrayMaths am = new ArrayMaths();
/*  9098 */     am.array = new ArrayList();
/*  9099 */     am.length = this.length;
/*  9100 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/*  9105 */       BigDecimal constantBD = Conv.convert_BigInteger_to_BigDecimal(constant);
/*  9106 */       BigDecimal[] bd = getArray_as_BigDecimal();
/*  9107 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].add(constantBD));
/*  9108 */       am.type = 12;
/*  9109 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*  9118 */       BigInteger[] bi = getArray_as_BigInteger();
/*  9119 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].divide(constant));
/*  9120 */       am.type = 13;
/*  9121 */       break;
/*  9122 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9123 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].over(Conv.convert_BigInteger_to_double(constant)));
/*  9124 */       am.type = this.type;
/*  9125 */       break;
/*  9126 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9127 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].over(new Phasor(Conv.convert_BigInteger_to_double(constant))));
/*  9128 */       am.type = this.type;
/*  9129 */       break;
/*  9130 */     case 16:  throw new IllegalArgumentException("a BigInteger cannot be divided by a char");
/*  9131 */     case 17:  throw new IllegalArgumentException("a BigInteger cannot be divided by a Character");
/*  9132 */     case 18:  throw new IllegalArgumentException("a BigInteger cannot be divided by a String");
/*  9133 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9135 */     int[] maxminIndices = new int[2];
/*  9136 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9137 */     am.maxIndex = maxminIndices[0];
/*  9138 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9140 */     Conv.restoreMessages();
/*  9141 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Complex constant)
/*       */   {
/*  9146 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9147 */     ArrayMaths am = new ArrayMaths();
/*  9148 */     am.array = new ArrayList();
/*  9149 */     am.length = this.length;
/*  9150 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*  9165 */       Complex[] cc = getArray_as_Complex();
/*  9166 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].over(constant));
/*  9167 */       am.type = 14;
/*  9168 */       break;
/*  9169 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9170 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].over(Conv.convert_Complex_to_Phasor(constant)));
/*  9171 */       am.type = this.type;
/*  9172 */       break;
/*  9173 */     case 16:  throw new IllegalArgumentException("a Complex cannot be divided by a char");
/*  9174 */     case 17:  throw new IllegalArgumentException("a Complex cannot be divided by a Character");
/*  9175 */     case 18:  throw new IllegalArgumentException("a Complex cannot be divided by a String");
/*  9176 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9178 */     Conv.restoreMessages();
/*  9179 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths over(Phasor constant)
/*       */   {
/*  9184 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9185 */     ArrayMaths am = new ArrayMaths();
/*  9186 */     am.array = new ArrayList();
/*  9187 */     am.length = this.length;
/*  9188 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 15: 
/*  9203 */       Phasor[] pp = getArray_as_Phasor();
/*  9204 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].over(constant));
/*  9205 */       am.type = 15;
/*  9206 */       break;
/*  9207 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9208 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].over(Conv.convert_Phasor_to_Complex(constant)));
/*  9209 */       am.type = this.type;
/*  9210 */       break;
/*  9211 */     case 16:  throw new IllegalArgumentException("a Phasor cannot be divided by a char");
/*  9212 */     case 17:  throw new IllegalArgumentException("a Phasor cannot be divided by a Character");
/*  9213 */     case 18:  throw new IllegalArgumentException("a Phasor cannot be divided by a String");
/*  9214 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9216 */     Conv.restoreMessages();
/*  9217 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths truncate(int n)
/*       */   {
/*  9223 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9224 */     ArrayMaths am = new ArrayMaths();
/*  9225 */     am.array = new ArrayList();
/*  9226 */     am.length = this.length;
/*  9227 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  9229 */       double[] dd = getArray_as_double();
/*  9230 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Fmath.truncate(dd[i], n)));
/*  9231 */       am.type = this.type;
/*  9232 */       break;
/*       */     case 2: case 3: 
/*  9234 */       float[] ff = getArray_as_float();
/*  9235 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(Fmath.truncate(ff[i], n)));
/*  9236 */       am.type = this.type;
/*  9237 */       break;
/*       */     case 4: case 5: 
/*  9239 */       long[] ll = getArray_as_long();
/*  9240 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  9241 */       am.type = this.type;
/*  9242 */       break;
/*       */     case 6: case 7: 
/*  9244 */       int[] ii = getArray_as_int();
/*  9245 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ii[i]));
/*  9246 */       am.type = this.type;
/*  9247 */       break;
/*       */     case 8: case 9: 
/*  9249 */       short[] ss = getArray_as_short();
/*  9250 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  9251 */       am.type = this.type;
/*  9252 */       break;
/*       */     case 10: case 11: 
/*  9254 */       byte[] bb = getArray_as_byte();
/*  9255 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  9256 */       am.type = this.type;
/*  9257 */       break;
/*  9258 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  9259 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].setScale(n, 4));
/*  9260 */       am.type = this.type;
/*  9261 */       break;
/*  9262 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/*  9263 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  9264 */       am.type = this.type;
/*  9265 */       break;
/*  9266 */     case 14:  Complex[] co = getArray_as_Complex();
/*  9267 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.truncate(co[i], n));
/*  9268 */       am.type = this.type;
/*  9269 */       break;
/*  9270 */     case 15:  Phasor[] ph = getArray_as_Phasor();
/*  9271 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.truncate(ph[i], n));
/*  9272 */       am.type = this.type;
/*  9273 */       break;
/*       */     case 16: case 17: 
/*  9275 */       char[] ch = getArray_as_char();
/*  9276 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(ch[i]));
/*  9277 */       am.type = this.type;
/*  9278 */       break;
/*  9279 */     case 18:  String[] st = getArray_as_String();
/*  9280 */       for (int i = 0; i < this.length; i++) am.array.add(st[i]);
/*  9281 */       am.type = this.type;
/*  9282 */       break;
/*  9283 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/*  9286 */     int[] maxminIndices = new int[2];
/*  9287 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9288 */     am.maxIndex = maxminIndices[0];
/*  9289 */     am.minIndex = maxminIndices[1];
/*  9290 */     Conv.restoreMessages();
/*  9291 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths floor()
/*       */   {
/*  9297 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9298 */     ArrayMaths am = new ArrayMaths();
/*  9299 */     am.array = new ArrayList();
/*  9300 */     am.length = this.length;
/*  9301 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  9303 */       double[] dd = getArray_as_double();
/*  9304 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.floor(dd[i])));
/*  9305 */       am.type = this.type;
/*  9306 */       break;
/*       */     case 2: case 3: 
/*  9308 */       float[] ff = getArray_as_float();
/*  9309 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(Math.floor(ff[i])));
/*  9310 */       am.type = this.type;
/*  9311 */       break;
/*       */     case 4: case 5: 
/*  9313 */       long[] ll = getArray_as_long();
/*  9314 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  9315 */       am.type = this.type;
/*  9316 */       break;
/*       */     case 6: case 7: 
/*  9318 */       int[] ii = getArray_as_int();
/*  9319 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ii[i]));
/*  9320 */       am.type = this.type;
/*  9321 */       break;
/*       */     case 8: case 9: 
/*  9323 */       short[] ss = getArray_as_short();
/*  9324 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  9325 */       am.type = this.type;
/*  9326 */       break;
/*       */     case 10: case 11: 
/*  9328 */       byte[] bb = getArray_as_byte();
/*  9329 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  9330 */       am.type = this.type;
/*  9331 */       break;
/*  9332 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  9333 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].setScale(0, 1));
/*  9334 */       am.type = this.type;
/*  9335 */       break;
/*  9336 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/*  9337 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  9338 */       am.type = this.type;
/*  9339 */       break;
/*  9340 */     case 14:  Complex[] co = getArray_as_Complex();
/*  9341 */       for (int i = 0; i < this.length; i++) am.array.add(new Complex(Math.floor(co[i].getReal()), Math.floor(co[i].getImag())));
/*  9342 */       am.type = this.type;
/*  9343 */       break;
/*  9344 */     case 15:  Phasor[] ph = getArray_as_Phasor();
/*  9345 */       for (int i = 0; i < this.length; i++) am.array.add(new Phasor(Math.floor(ph[i].getMagnitude()), Math.floor(ph[i].getPhaseInDegrees())));
/*  9346 */       am.type = this.type;
/*  9347 */       break;
/*       */     case 16: case 17: 
/*  9349 */       char[] ch = getArray_as_char();
/*  9350 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(ch[i]));
/*  9351 */       am.type = this.type;
/*  9352 */       break;
/*  9353 */     case 18:  String[] st = getArray_as_String();
/*  9354 */       for (int i = 0; i < this.length; i++) am.array.add(st[i]);
/*  9355 */       am.type = this.type;
/*  9356 */       break;
/*  9357 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/*  9360 */     int[] maxminIndices = new int[2];
/*  9361 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9362 */     am.maxIndex = maxminIndices[0];
/*  9363 */     am.minIndex = maxminIndices[1];
/*  9364 */     Conv.restoreMessages();
/*  9365 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths ceil()
/*       */   {
/*  9371 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9372 */     ArrayMaths am = new ArrayMaths();
/*  9373 */     am.array = new ArrayList();
/*  9374 */     am.length = this.length;
/*  9375 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  9377 */       double[] dd = getArray_as_double();
/*  9378 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.ceil(dd[i])));
/*  9379 */       am.type = this.type;
/*  9380 */       break;
/*       */     case 2: case 3: 
/*  9382 */       float[] ff = getArray_as_float();
/*  9383 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(Math.ceil(ff[i])));
/*  9384 */       am.type = this.type;
/*  9385 */       break;
/*       */     case 4: case 5: 
/*  9387 */       long[] ll = getArray_as_long();
/*  9388 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  9389 */       am.type = this.type;
/*  9390 */       break;
/*       */     case 6: case 7: 
/*  9392 */       int[] ii = getArray_as_int();
/*  9393 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ii[i]));
/*  9394 */       am.type = this.type;
/*  9395 */       break;
/*       */     case 8: case 9: 
/*  9397 */       short[] ss = getArray_as_short();
/*  9398 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  9399 */       am.type = this.type;
/*  9400 */       break;
/*       */     case 10: case 11: 
/*  9402 */       byte[] bb = getArray_as_byte();
/*  9403 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  9404 */       am.type = this.type;
/*  9405 */       break;
/*  9406 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  9407 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].setScale(0, 0));
/*  9408 */       am.type = this.type;
/*  9409 */       break;
/*  9410 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/*  9411 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  9412 */       am.type = this.type;
/*  9413 */       break;
/*  9414 */     case 14:  Complex[] co = getArray_as_Complex();
/*  9415 */       for (int i = 0; i < this.length; i++) am.array.add(new Complex(Math.ceil(co[i].getReal()), Math.ceil(co[i].getImag())));
/*  9416 */       am.type = this.type;
/*  9417 */       break;
/*  9418 */     case 15:  Phasor[] ph = getArray_as_Phasor();
/*  9419 */       for (int i = 0; i < this.length; i++) am.array.add(new Phasor(Math.ceil(ph[i].getMagnitude()), Math.ceil(ph[i].getPhaseInDegrees())));
/*  9420 */       am.type = this.type;
/*  9421 */       break;
/*       */     case 16: case 17: 
/*  9423 */       char[] ch = getArray_as_char();
/*  9424 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(ch[i]));
/*  9425 */       am.type = this.type;
/*  9426 */       break;
/*  9427 */     case 18:  String[] st = getArray_as_String();
/*  9428 */       for (int i = 0; i < this.length; i++) am.array.add(st[i]);
/*  9429 */       am.type = this.type;
/*  9430 */       break;
/*  9431 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/*  9434 */     int[] maxminIndices = new int[2];
/*  9435 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9436 */     am.maxIndex = maxminIndices[0];
/*  9437 */     am.minIndex = maxminIndices[1];
/*  9438 */     Conv.restoreMessages();
/*  9439 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths rint()
/*       */   {
/*  9445 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9446 */     ArrayMaths am = new ArrayMaths();
/*  9447 */     am.array = new ArrayList();
/*  9448 */     am.length = this.length;
/*  9449 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  9451 */       double[] dd = getArray_as_double();
/*  9452 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.rint(dd[i])));
/*  9453 */       am.type = this.type;
/*  9454 */       break;
/*       */     case 2: case 3: 
/*  9456 */       float[] ff = getArray_as_float();
/*  9457 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(Math.rint(ff[i])));
/*  9458 */       am.type = this.type;
/*  9459 */       break;
/*       */     case 4: case 5: 
/*  9461 */       long[] ll = getArray_as_long();
/*  9462 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ll[i]));
/*  9463 */       am.type = this.type;
/*  9464 */       break;
/*       */     case 6: case 7: 
/*  9466 */       int[] ii = getArray_as_int();
/*  9467 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(ii[i]));
/*  9468 */       am.type = this.type;
/*  9469 */       break;
/*       */     case 8: case 9: 
/*  9471 */       short[] ss = getArray_as_short();
/*  9472 */       for (int i = 0; i < this.length; i++) am.array.add(new Short(ss[i]));
/*  9473 */       am.type = this.type;
/*  9474 */       break;
/*       */     case 10: case 11: 
/*  9476 */       byte[] bb = getArray_as_byte();
/*  9477 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte(bb[i]));
/*  9478 */       am.type = this.type;
/*  9479 */       break;
/*  9480 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/*  9481 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].setScale(0, 6));
/*  9482 */       am.type = this.type;
/*  9483 */       break;
/*  9484 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/*  9485 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i]);
/*  9486 */       am.type = this.type;
/*  9487 */       break;
/*  9488 */     case 14:  Complex[] co = getArray_as_Complex();
/*  9489 */       for (int i = 0; i < this.length; i++) am.array.add(new Complex(Math.rint(co[i].getReal()), Math.rint(co[i].getImag())));
/*  9490 */       am.type = this.type;
/*  9491 */       break;
/*  9492 */     case 15:  Phasor[] ph = getArray_as_Phasor();
/*  9493 */       for (int i = 0; i < this.length; i++) am.array.add(new Phasor(Math.rint(ph[i].getMagnitude()), Math.rint(ph[i].getPhaseInDegrees())));
/*  9494 */       am.type = this.type;
/*  9495 */       break;
/*       */     case 16: case 17: 
/*  9497 */       char[] ch = getArray_as_char();
/*  9498 */       for (int i = 0; i < this.length; i++) am.array.add(new Character(ch[i]));
/*  9499 */       am.type = this.type;
/*  9500 */       break;
/*  9501 */     case 18:  String[] st = getArray_as_String();
/*  9502 */       for (int i = 0; i < this.length; i++) am.array.add(st[i]);
/*  9503 */       am.type = this.type;
/*  9504 */       break;
/*  9505 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/*  9508 */     int[] maxminIndices = new int[2];
/*  9509 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9510 */     am.maxIndex = maxminIndices[0];
/*  9511 */     am.minIndex = maxminIndices[1];
/*  9512 */     Conv.restoreMessages();
/*  9513 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public ArrayMaths reverse()
/*       */   {
/*  9520 */     ArrayMaths am = new ArrayMaths();
/*  9521 */     am.array = new ArrayList();
/*  9522 */     am.length = this.length;
/*  9523 */     am.type = this.type;
/*  9524 */     am.sortedIndices = new int[this.length];
/*       */     
/*  9526 */     for (int i = 0; i < this.length; i++) {
/*  9527 */       am.array.add(this.array.get(this.length - i - 1));
/*  9528 */       am.sortedIndices[i] = (this.length - i - 1);
/*       */     }
/*  9530 */     int[] maxminIndices = new int[2];
/*  9531 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9532 */     am.maxIndex = maxminIndices[0];
/*  9533 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9535 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public ArrayMaths log()
/*       */   {
/*  9542 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9543 */     ArrayMaths am = new ArrayMaths();
/*  9544 */     am.array = new ArrayList();
/*  9545 */     am.length = this.length;
/*  9546 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9563 */       double[] dd = getArray_as_double();
/*  9564 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.log(dd[i])));
/*  9565 */       am.type = 1;
/*  9566 */       break;
/*  9567 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9568 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.log(cc[i]));
/*  9569 */       am.type = this.type;
/*  9570 */       break;
/*  9571 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9572 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.log(pp[i]));
/*  9573 */       am.type = 15;
/*  9574 */       break;
/*  9575 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9577 */     int[] maxminIndices = new int[2];
/*  9578 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9579 */     am.maxIndex = maxminIndices[0];
/*  9580 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9582 */     Conv.restoreMessages();
/*  9583 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths log2()
/*       */   {
/*  9588 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9589 */     ArrayMaths am = new ArrayMaths();
/*  9590 */     am.array = new ArrayList();
/*  9591 */     am.length = this.length;
/*  9592 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9609 */       double[] dd = getArray_as_double();
/*  9610 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Fmath.log2(dd[i])));
/*  9611 */       am.type = 1;
/*  9612 */       break;
/*  9613 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9614 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.log(cc[i].over(Math.log(2.0D))));
/*  9615 */       am.type = this.type;
/*  9616 */       break;
/*  9617 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9618 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.log(pp[i].over(Math.log(2.0D))));
/*  9619 */       am.type = 15;
/*  9620 */       break;
/*  9621 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9623 */     int[] maxminIndices = new int[2];
/*  9624 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9625 */     am.maxIndex = maxminIndices[0];
/*  9626 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9628 */     Conv.restoreMessages();
/*  9629 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths log10()
/*       */   {
/*  9634 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9635 */     ArrayMaths am = new ArrayMaths();
/*  9636 */     am.array = new ArrayList();
/*  9637 */     am.length = this.length;
/*  9638 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9655 */       double[] dd = getArray_as_double();
/*  9656 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.log10(dd[i])));
/*  9657 */       am.type = 1;
/*  9658 */       break;
/*  9659 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9660 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.log(cc[i].over(Math.log(10.0D))));
/*  9661 */       am.type = this.type;
/*  9662 */       break;
/*  9663 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9664 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.log(pp[i].over(Math.log(10.0D))));
/*  9665 */       am.type = 15;
/*  9666 */       break;
/*  9667 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9669 */     int[] maxminIndices = new int[2];
/*  9670 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9671 */     am.maxIndex = maxminIndices[0];
/*  9672 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9674 */     Conv.restoreMessages();
/*  9675 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths antilog10() {
/*  9679 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9680 */     ArrayMaths am = new ArrayMaths();
/*  9681 */     am.array = new ArrayList();
/*  9682 */     am.length = this.length;
/*  9683 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9700 */       double[] dd = getArray_as_double();
/*  9701 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.pow(10.0D, dd[i])));
/*  9702 */       am.type = 1;
/*  9703 */       break;
/*  9704 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9705 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.pow(10.0D, cc[i]));
/*  9706 */       am.type = this.type;
/*  9707 */       break;
/*  9708 */     case 15:  Complex[] pp = getArray_as_Complex();
/*  9709 */       for (int i = 0; i < this.length; i++) am.array.add(Conv.convert_Complex_to_Phasor(Complex.pow(10.0D, pp[i])));
/*  9710 */       am.type = 15;
/*  9711 */       break;
/*  9712 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9714 */     int[] maxminIndices = new int[2];
/*  9715 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9716 */     am.maxIndex = maxminIndices[0];
/*  9717 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9719 */     Conv.restoreMessages();
/*  9720 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths xLog2x()
/*       */   {
/*  9725 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9726 */     ArrayMaths am = new ArrayMaths();
/*  9727 */     am.array = new ArrayList();
/*  9728 */     am.length = this.length;
/*  9729 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9746 */       double[] dd = getArray_as_double();
/*  9747 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * Fmath.log2(dd[i])));
/*  9748 */       am.type = 1;
/*  9749 */       break;
/*  9750 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9751 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(Complex.log(cc[i].over(Math.log(2.0D)))));
/*  9752 */       am.type = this.type;
/*  9753 */       break;
/*  9754 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9755 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(Phasor.log(pp[i].over(Math.log(2.0D)))));
/*  9756 */       am.type = 15;
/*  9757 */       break;
/*  9758 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9760 */     int[] maxminIndices = new int[2];
/*  9761 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9762 */     am.maxIndex = maxminIndices[0];
/*  9763 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9765 */     Conv.restoreMessages();
/*  9766 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths xLogEx()
/*       */   {
/*  9771 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9772 */     ArrayMaths am = new ArrayMaths();
/*  9773 */     am.array = new ArrayList();
/*  9774 */     am.length = this.length;
/*  9775 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9792 */       double[] dd = getArray_as_double();
/*  9793 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * Math.log(dd[i])));
/*  9794 */       am.type = 1;
/*  9795 */       break;
/*  9796 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9797 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(Complex.log(cc[i])));
/*  9798 */       am.type = this.type;
/*  9799 */       break;
/*  9800 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9801 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(Phasor.log(pp[i])));
/*  9802 */       am.type = 15;
/*  9803 */       break;
/*  9804 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9806 */     int[] maxminIndices = new int[2];
/*  9807 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9808 */     am.maxIndex = maxminIndices[0];
/*  9809 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9811 */     Conv.restoreMessages();
/*  9812 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths xLog10x()
/*       */   {
/*  9817 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9818 */     ArrayMaths am = new ArrayMaths();
/*  9819 */     am.array = new ArrayList();
/*  9820 */     am.length = this.length;
/*  9821 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9838 */       double[] dd = getArray_as_double();
/*  9839 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(dd[i] * Math.log10(dd[i])));
/*  9840 */       am.type = 1;
/*  9841 */       break;
/*  9842 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9843 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].times(Complex.log(cc[i].over(Math.log(10.0D)))));
/*  9844 */       am.type = this.type;
/*  9845 */       break;
/*  9846 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9847 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].times(Phasor.log(pp[i].over(Math.log(10.0D)))));
/*  9848 */       am.type = 15;
/*  9849 */       break;
/*  9850 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9852 */     int[] maxminIndices = new int[2];
/*  9853 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9854 */     am.maxIndex = maxminIndices[0];
/*  9855 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9857 */     Conv.restoreMessages();
/*  9858 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths minusxLog2x()
/*       */   {
/*  9863 */     ArrayMaths am = xLog2x();
/*  9864 */     return am.negate();
/*       */   }
/*       */   
/*       */   public ArrayMaths minusxLogEx()
/*       */   {
/*  9869 */     ArrayMaths am = xLogEx();
/*  9870 */     return am.negate();
/*       */   }
/*       */   
/*       */   public ArrayMaths minusxLog10x()
/*       */   {
/*  9875 */     ArrayMaths am = xLog10x();
/*  9876 */     return am.negate();
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths sqrt()
/*       */   {
/*  9882 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9883 */     ArrayMaths am = new ArrayMaths();
/*  9884 */     am.array = new ArrayList();
/*  9885 */     am.length = this.length;
/*  9886 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9903 */       double[] dd = getArray_as_double();
/*  9904 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.sqrt(dd[i])));
/*  9905 */       am.type = 1;
/*  9906 */       break;
/*  9907 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9908 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.sqrt(cc[i]));
/*  9909 */       am.type = this.type;
/*  9910 */       break;
/*  9911 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9912 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.sqrt(pp[i]));
/*  9913 */       am.type = 15;
/*  9914 */       break;
/*  9915 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9917 */     int[] maxminIndices = new int[2];
/*  9918 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9919 */     am.maxIndex = maxminIndices[0];
/*  9920 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9922 */     Conv.restoreMessages();
/*  9923 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths oneOverSqrt()
/*       */   {
/*  9928 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9929 */     ArrayMaths am = new ArrayMaths();
/*  9930 */     am.array = new ArrayList();
/*  9931 */     am.length = this.length;
/*  9932 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/*  9949 */       double[] dd = getArray_as_double();
/*  9950 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(1.0D / Math.sqrt(dd[i])));
/*  9951 */       am.type = 1;
/*  9952 */       break;
/*  9953 */     case 14:  Complex[] cc = getArray_as_Complex();
/*  9954 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.sqrt(cc[i]).inverse());
/*  9955 */       am.type = this.type;
/*  9956 */       break;
/*  9957 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/*  9958 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.sqrt(pp[i]).inverse());
/*  9959 */       am.type = 15;
/*  9960 */       break;
/*  9961 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*  9963 */     int[] maxminIndices = new int[2];
/*  9964 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/*  9965 */     am.maxIndex = maxminIndices[0];
/*  9966 */     am.minIndex = maxminIndices[1];
/*       */     
/*  9968 */     Conv.restoreMessages();
/*  9969 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths abs()
/*       */   {
/*  9975 */     if (this.suppressMessages) Conv.suppressMessages();
/*  9976 */     ArrayMaths am = new ArrayMaths();
/*  9977 */     am.array = new ArrayList();
/*  9978 */     am.length = this.length;
/*  9979 */     switch (this.type) {
/*       */     case 0: case 1: 
/*  9981 */       double[] dd = getArray_as_double();
/*  9982 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.abs(dd[i])));
/*  9983 */       am.type = this.type;
/*  9984 */       break;
/*       */     case 2: case 3: 
/*  9986 */       float[] ff = getArray_as_float();
/*  9987 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(Math.abs(ff[i])));
/*  9988 */       am.type = this.type;
/*  9989 */       break;
/*       */     case 4: case 5: 
/*  9991 */       long[] ll = getArray_as_long();
/*  9992 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(Math.abs(ll[i])));
/*  9993 */       am.type = this.type;
/*  9994 */       break;
/*       */     case 6: case 7: 
/*  9996 */       int[] ii1 = getArray_as_int();
/*  9997 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(Math.abs(ii1[i])));
/*  9998 */       am.type = this.type;
/*  9999 */       break;
/*       */     case 8: case 9: 
/* 10001 */       int[] ii2 = getArray_as_int();
/* 10002 */       for (int i = 0; i < this.length; i++) am.array.add(new Short((short)Math.abs(ii2[i])));
/* 10003 */       am.type = this.type;
/* 10004 */       break;
/*       */     case 10: case 11: 
/* 10006 */       int[] ii3 = getArray_as_int();
/* 10007 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte((byte)Math.abs(ii3[i])));
/* 10008 */       am.type = this.type;
/* 10009 */       break;
/* 10010 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 10011 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].abs());
/* 10012 */       am.type = this.type;
/* 10013 */       break;
/* 10014 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 10015 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].abs());
/* 10016 */       am.type = this.type;
/* 10017 */       break;
/* 10018 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10019 */       for (int i = 0; i < this.length; i++) am.array.add(Double.valueOf(Complex.abs(cc[i])));
/* 10020 */       am.type = this.type;
/* 10021 */       break;
/* 10022 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10023 */       for (int i = 0; i < this.length; i++) am.array.add(Double.valueOf(pp[i].abs()));
/* 10024 */       am.type = 15;
/* 10025 */       break;
/*       */     case 16: case 17: 
/* 10027 */       int[] ii4 = getArray_as_int();
/* 10028 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(Math.abs(ii4[i])));
/* 10029 */       am.type = this.type;
/* 10030 */       break;
/* 10031 */     case 18:  double[] dd2 = getArray_as_double();
/* 10032 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.abs(dd2[i])));
/* 10033 */       am.type = 1;
/* 10034 */       break;
/* 10035 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10037 */     int[] maxminIndices = new int[2];
/* 10038 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10039 */     am.maxIndex = maxminIndices[0];
/* 10040 */     am.minIndex = maxminIndices[1];
/*       */     
/* 10042 */     Conv.restoreMessages();
/* 10043 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths exp()
/*       */   {
/* 10049 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10050 */     ArrayMaths am = new ArrayMaths();
/* 10051 */     am.array = new ArrayList();
/* 10052 */     am.length = this.length;
/* 10053 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10070 */       double[] dd = getArray_as_double();
/* 10071 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.exp(dd[i])));
/* 10072 */       am.type = 1;
/* 10073 */       break;
/* 10074 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10075 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.exp(cc[i]));
/* 10076 */       am.type = this.type;
/* 10077 */       break;
/* 10078 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10079 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.exp(pp[i]));
/* 10080 */       am.type = 15;
/* 10081 */       break;
/* 10082 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10084 */     int[] maxminIndices = new int[2];
/* 10085 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10086 */     am.maxIndex = maxminIndices[0];
/* 10087 */     am.minIndex = maxminIndices[1];
/*       */     
/* 10089 */     Conv.restoreMessages();
/* 10090 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths invert()
/*       */   {
/* 10095 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10096 */     ArrayMaths am = new ArrayMaths();
/* 10097 */     am.array = new ArrayList();
/* 10098 */     am.length = this.length;
/* 10099 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10114 */       double[] dd = getArray_as_double();
/* 10115 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(1.0D / dd[i]));
/* 10116 */       am.type = 1;
/* 10117 */       break;
/*       */     case 12: case 13: 
/* 10119 */       BigDecimal[] bd = getArray_as_BigDecimal();
/* 10120 */       for (int i = 0; i < this.length; i++) am.array.add(BigDecimal.ONE.divide(bd[i], 4));
/* 10121 */       am.type = 12;
/* 10122 */       bd = null;
/* 10123 */       break;
/* 10124 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10125 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.plusOne().over(cc[i]));
/* 10126 */       am.type = 14;
/* 10127 */       break;
/* 10128 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10129 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.plusOne().over(pp[i]));
/* 10130 */       am.type = 15;
/* 10131 */       break;
/* 10132 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10134 */     int[] maxminIndices = new int[2];
/* 10135 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10136 */     am.maxIndex = maxminIndices[0];
/* 10137 */     am.minIndex = maxminIndices[1];
/*       */     
/* 10139 */     Conv.restoreMessages();
/* 10140 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths pow(int n)
/*       */   {
/* 10146 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10147 */     ArrayMaths am = new ArrayMaths();
/* 10148 */     am.array = new ArrayList();
/* 10149 */     am.length = this.length;
/* 10150 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10165 */       double[] dd = getArray_as_double();
/* 10166 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.pow(dd[i], n)));
/* 10167 */       am.type = 1;
/* 10168 */       break;
/* 10169 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 10170 */       BigDecimal bdpow = BigDecimal.ONE;
/* 10171 */       for (int i = 0; i < this.length; i++) {
/* 10172 */         for (int j = 0; j < n; j++) bdpow = bdpow.multiply(bd[i]);
/* 10173 */         am.array.add(bdpow);
/*       */       }
/* 10175 */       bd = null;
/* 10176 */       bdpow = null;
/* 10177 */       am.type = 12;
/* 10178 */       break;
/* 10179 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 10180 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].pow(n));
/* 10181 */       am.type = 13;
/* 10182 */       bi = null;
/* 10183 */       break;
/* 10184 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10185 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.pow(cc[i], n));
/* 10186 */       am.type = this.type;
/* 10187 */       break;
/* 10188 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10189 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.pow(pp[i], n));
/* 10190 */       am.type = 15;
/* 10191 */       break;
/* 10192 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10195 */     int[] maxminIndices = new int[2];
/* 10196 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10197 */     am.maxIndex = maxminIndices[0];
/* 10198 */     am.minIndex = maxminIndices[1];
/* 10199 */     Conv.restoreMessages();
/* 10200 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(double n)
/*       */   {
/* 10205 */     if (this.suppressMessages) { Conv.suppressMessages();
/*       */     }
/* 10207 */     ArrayMaths am = new ArrayMaths();
/* 10208 */     am.array = new ArrayList();
/* 10209 */     am.length = this.length;
/* 10210 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10227 */       double[] dd = getArray_as_double();
/* 10228 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.pow(dd[i], n)));
/* 10229 */       am.type = 1;
/* 10230 */       break;
/* 10231 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10232 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.pow(cc[i], n));
/* 10233 */       am.type = this.type;
/* 10234 */       break;
/* 10235 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10236 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.pow(pp[i], n));
/* 10237 */       am.type = 15;
/* 10238 */       break;
/* 10239 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10241 */     int[] maxminIndices = new int[2];
/* 10242 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10243 */     am.maxIndex = maxminIndices[0];
/* 10244 */     am.minIndex = maxminIndices[1];
/* 10245 */     Conv.restoreMessages();
/* 10246 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(float n)
/*       */   {
/* 10251 */     double nn = n;
/* 10252 */     return pow(nn);
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(long n)
/*       */   {
/* 10257 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10258 */     ArrayMaths am = new ArrayMaths();
/* 10259 */     am.array = new ArrayList();
/* 10260 */     am.length = this.length;
/* 10261 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10276 */       double[] dd = getArray_as_double();
/* 10277 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(Math.pow(dd[i], n)));
/* 10278 */       am.type = 1;
/* 10279 */       break;
/* 10280 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 10281 */       BigDecimal bdpow = BigDecimal.ONE;
/* 10282 */       for (int i = 0; i < this.length; i++) {
/* 10283 */         long j = 0L;
/* 10284 */         while (j < n) {
/* 10285 */           bdpow = bdpow.multiply(bd[i]);
/*       */         }
/* 10287 */         am.array.add(bdpow);
/*       */       }
/* 10289 */       bd = null;
/* 10290 */       bdpow = null;
/* 10291 */       am.type = 12;
/* 10292 */       break;
/* 10293 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 10294 */       BigInteger bipow = BigInteger.ONE;
/* 10295 */       for (int i = 0; i < this.length; i++) {
/* 10296 */         long j = 0L;
/* 10297 */         while (j < n) {
/* 10298 */           bipow = bipow.multiply(bi[i]);
/*       */         }
/* 10300 */         am.array.add(bipow);
/*       */       }
/* 10302 */       bi = null;
/* 10303 */       bipow = null;
/* 10304 */       am.type = 13;
/* 10305 */       break;
/* 10306 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10307 */       for (int i = 0; i < this.length; i++) am.array.add(Complex.pow(cc[i], n));
/* 10308 */       am.type = this.type;
/* 10309 */       break;
/* 10310 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10311 */       for (int i = 0; i < this.length; i++) am.array.add(Phasor.pow(pp[i], n));
/* 10312 */       am.type = 15;
/* 10313 */       break;
/* 10314 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10316 */     int[] maxminIndices = new int[2];
/* 10317 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10318 */     am.maxIndex = maxminIndices[0];
/* 10319 */     am.minIndex = maxminIndices[1];
/* 10320 */     Conv.restoreMessages();
/* 10321 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(short n)
/*       */   {
/* 10326 */     int ii = n;
/* 10327 */     return pow(ii);
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(byte n)
/*       */   {
/* 10332 */     int ii = n;
/* 10333 */     return pow(ii);
/*       */   }
/*       */   
/*       */   public ArrayMaths pow(Number n)
/*       */   {
/* 10338 */     boolean test = integers.containsKey(n.getClass());
/* 10339 */     if (test) {
/* 10340 */       if ((n instanceof Long)) {
/* 10341 */         return pow(n.longValue());
/*       */       }
/*       */       
/* 10344 */       if ((n instanceof BigInteger)) {
/* 10345 */         return pow(n.doubleValue());
/*       */       }
/*       */       
/* 10348 */       return pow(n.intValue());
/*       */     }
/*       */     
/*       */ 
/*       */ 
/* 10353 */     return pow(n.doubleValue());
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths negate()
/*       */   {
/* 10359 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10360 */     ArrayMaths am = new ArrayMaths();
/* 10361 */     am.array = new ArrayList();
/* 10362 */     am.length = this.length;
/* 10363 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 10368 */       double[] dd = getArray_as_double();
/* 10369 */       for (int i = 0; i < this.length; i++) am.array.add(new Double(-dd[i]));
/* 10370 */       am.type = 1;
/* 10371 */       break;
/*       */     case 2: case 3: 
/* 10373 */       float[] ff = getArray_as_float();
/* 10374 */       for (int i = 0; i < this.length; i++) am.array.add(new Float(-ff[i]));
/* 10375 */       am.type = 3;
/* 10376 */       break;
/*       */     case 4: case 5: 
/* 10378 */       long[] ll = getArray_as_long();
/* 10379 */       for (int i = 0; i < this.length; i++) am.array.add(new Long(-ll[i]));
/* 10380 */       am.type = 5;
/* 10381 */       break;
/*       */     case 6: case 7: 
/* 10383 */       int[] ii = getArray_as_int();
/* 10384 */       for (int i = 0; i < this.length; i++) am.array.add(new Integer(-ii[i]));
/* 10385 */       am.type = 7;
/* 10386 */       break;
/*       */     case 8: case 9: 
/* 10388 */       short[] ss = getArray_as_short();
/* 10389 */       for (int i = 0; i < this.length; i++) am.array.add(new Short((short)-ss[i]));
/* 10390 */       am.type = 9;
/* 10391 */       break;
/*       */     case 10: case 11: 
/* 10393 */       byte[] bb = getArray_as_byte();
/* 10394 */       for (int i = 0; i < this.length; i++) am.array.add(new Byte((byte)-bb[i]));
/* 10395 */       am.type = 11;
/* 10396 */       break;
/* 10397 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 10398 */       for (int i = 0; i < this.length; i++) am.array.add(bd[i].negate());
/* 10399 */       am.type = 12;
/* 10400 */       bd = null;
/* 10401 */       break;
/* 10402 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 10403 */       for (int i = 0; i < this.length; i++) am.array.add(bi[i].negate());
/* 10404 */       am.type = 13;
/* 10405 */       bi = null;
/* 10406 */       break;
/* 10407 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10408 */       for (int i = 0; i < this.length; i++) am.array.add(cc[i].negate());
/* 10409 */       am.type = 14;
/* 10410 */       break;
/* 10411 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10412 */       for (int i = 0; i < this.length; i++) am.array.add(pp[i].negate());
/* 10413 */       am.type = 15;
/* 10414 */       break;
/* 10415 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10417 */     int[] maxminIndices = new int[2];
/* 10418 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 10419 */     am.maxIndex = maxminIndices[0];
/* 10420 */     am.minIndex = maxminIndices[1];
/* 10421 */     Conv.restoreMessages();
/* 10422 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   protected void calcSum()
/*       */   {
/* 10430 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10431 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10436 */       double[] dd = getArray_as_double();
/* 10437 */       double sum = 0.0D;
/* 10438 */       for (int i = 0; i < this.length; i++) sum += dd[i];
/* 10439 */       this.summ.add(new Double(sum));
/* 10440 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10450 */       long[] ll = getArray_as_long();
/* 10451 */       long suml = 0L;
/* 10452 */       boolean test = false;
/* 10453 */       for (int i = 0; i < this.length; i++) {
/* 10454 */         if (Long.MAX_VALUE - suml < ll[i]) test = true;
/* 10455 */         suml += ll[i];
/*       */       }
/* 10457 */       if (test) {
/* 10458 */         double[] dd2 = getArray_as_double();
/* 10459 */         double sum2 = 0.0D;
/* 10460 */         for (int i = 0; i < this.length; i++) sum2 += dd2[i];
/* 10461 */         this.summ.add(new Double(sum2));
/* 10462 */         this.sumlongToDouble = true;
/*       */       }
/*       */       else {
/* 10465 */         this.summ.add(new Long(suml));
/*       */       }
/* 10467 */       break;
/* 10468 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 10469 */       BigDecimal sumbd = new BigDecimal(0.0D);
/* 10470 */       for (int i = 0; i < this.length; i++) sumbd.add(bd[i]);
/* 10471 */       this.summ.add(sumbd);
/* 10472 */       bd = null;
/* 10473 */       sumbd = null;
/* 10474 */       break;
/* 10475 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 10476 */       BigInteger sumbi = BigInteger.ZERO;
/* 10477 */       for (int i = 0; i < this.length; i++) sumbi.add(bi[i]);
/* 10478 */       this.summ.add(sumbi);
/* 10479 */       bi = null;
/* 10480 */       sumbi = null;
/* 10481 */       break;
/* 10482 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 10483 */       Complex sumcc = Complex.zero();
/* 10484 */       for (int i = 0; i < this.length; i++) sumcc.plus(cc[i]);
/* 10485 */       this.summ.add(sumcc);
/* 10486 */       break;
/* 10487 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 10488 */       Phasor sumpp = Phasor.zero();
/* 10489 */       for (int i = 0; i < this.length; i++) sumpp.plus(pp[i]);
/* 10490 */       this.summ.add(sumpp);
/* 10491 */       break;
/* 10492 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 10494 */     this.sumDone = true;
/* 10495 */     Conv.restoreMessages();
/*       */   }
/*       */   
/*       */ 
/*       */   public double sum()
/*       */   {
/* 10501 */     return getSum_as_double();
/*       */   }
/*       */   
/*       */   public double sum_as_double() {
/* 10505 */     return getSum_as_double();
/*       */   }
/*       */   
/*       */   public double getSum() {
/* 10509 */     return getSum_as_double();
/*       */   }
/*       */   
/*       */   public double getSum_as_double() {
/* 10513 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10514 */     if (!this.sumDone) calcSum();
/* 10515 */     double sum = 0.0D;
/* 10516 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10521 */       sum = ((Double)this.summ.get(0)).doubleValue();
/* 10522 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10532 */       if (this.sumlongToDouble) {
/* 10533 */         sum = ((Double)this.summ.get(0)).doubleValue();
/*       */       }
/*       */       else {
/* 10536 */         sum = Conv.convert_Long_to_double((Long)this.summ.get(0));
/*       */       }
/* 10538 */       break;
/* 10539 */     case 12:  sum = Conv.convert_BigDecimal_to_double((BigDecimal)this.summ.get(0));
/* 10540 */       break;
/* 10541 */     case 13:  sum = Conv.convert_BigInteger_to_double((BigInteger)this.summ.get(0));
/* 10542 */       break;
/*       */     case 14: case 15: 
/* 10544 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as double is meaningful/supported");
/* 10545 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10548 */     Conv.restoreMessages();
/* 10549 */     return sum;
/*       */   }
/*       */   
/*       */   public Double sum_as_Double()
/*       */   {
/* 10554 */     return getSum_as_Double();
/*       */   }
/*       */   
/*       */   public Double getSum_as_Double() {
/* 10558 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10559 */     if (!this.sumDone) calcSum();
/* 10560 */     Double sum = new Double(0.0D);
/* 10561 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10566 */       sum = (Double)this.summ.get(0);
/* 10567 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10577 */       if (this.sumlongToDouble) {
/* 10578 */         sum = (Double)this.summ.get(0);
/*       */       }
/*       */       else {
/* 10581 */         sum = Conv.convert_Long_to_Double((Long)this.summ.get(0));
/*       */       }
/* 10583 */       break;
/* 10584 */     case 12:  sum = Conv.convert_BigDecimal_to_Double((BigDecimal)this.summ.get(0));
/* 10585 */       break;
/* 10586 */     case 13:  sum = Conv.convert_BigInteger_to_Double((BigInteger)this.summ.get(0));
/* 10587 */       break;
/*       */     case 14: case 15: 
/* 10589 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Double is meaningful/supported");
/* 10590 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10593 */     Conv.restoreMessages();
/* 10594 */     return sum;
/*       */   }
/*       */   
/*       */   public float sum_as_float()
/*       */   {
/* 10599 */     return getSum_as_float();
/*       */   }
/*       */   
/*       */   public float getSum_as_float() {
/* 10603 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10604 */     if (!this.sumDone) calcSum();
/* 10605 */     float sum = 0.0F;
/* 10606 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10611 */       sum = Conv.convert_Double_to_float((Double)this.summ.get(0));
/* 10612 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10622 */       if (this.sumlongToDouble) {
/* 10623 */         sum = Conv.convert_Double_to_float((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10626 */         sum = Conv.convert_Long_to_float((Long)this.summ.get(0));
/*       */       }
/* 10628 */       break;
/* 10629 */     case 12:  sum = Conv.convert_BigDecimal_to_float((BigDecimal)this.summ.get(0));
/* 10630 */       break;
/* 10631 */     case 13:  sum = Conv.convert_BigInteger_to_float((BigInteger)this.summ.get(0));
/* 10632 */       break;
/*       */     case 14: case 15: 
/* 10634 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as float is meaningful/supported");
/* 10635 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10638 */     Conv.restoreMessages();
/* 10639 */     return sum;
/*       */   }
/*       */   
/*       */   public Float sum_as_Float()
/*       */   {
/* 10644 */     return getSum_as_Float();
/*       */   }
/*       */   
/*       */   public Float getSum_as_Float() {
/* 10648 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10649 */     if (!this.sumDone) calcSum();
/* 10650 */     Float sum = new Float(0.0F);
/* 10651 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10656 */       sum = Conv.convert_Double_to_Float((Double)this.summ.get(0));
/* 10657 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10667 */       if (this.sumlongToDouble) {
/* 10668 */         sum = Conv.convert_Double_to_Float((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10671 */         sum = Conv.convert_Long_to_Float((Long)this.summ.get(0));
/*       */       }
/* 10673 */       break;
/* 10674 */     case 12:  sum = Conv.convert_BigDecimal_to_Float((BigDecimal)this.summ.get(0));
/* 10675 */       break;
/* 10676 */     case 13:  sum = Conv.convert_BigInteger_to_Float((BigInteger)this.summ.get(0));
/* 10677 */       break;
/*       */     case 14: case 15: 
/* 10679 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Float is meaningful/supported");
/* 10680 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10683 */     Conv.restoreMessages();
/* 10684 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public long sum_as_long()
/*       */   {
/* 10690 */     return getSum_as_long();
/*       */   }
/*       */   
/*       */   public long getSum_as_long() {
/* 10694 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10695 */     if (!this.sumDone) calcSum();
/* 10696 */     long sum = 0L;
/* 10697 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10702 */       sum = Conv.convert_Double_to_long((Double)this.summ.get(0));
/* 10703 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10713 */       if (this.sumlongToDouble) {
/* 10714 */         sum = Conv.convert_Double_to_long((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10717 */         sum = ((Long)this.summ.get(0)).longValue();
/*       */       }
/* 10719 */       break;
/* 10720 */     case 12:  sum = Conv.convert_BigDecimal_to_long((BigDecimal)this.summ.get(0));
/* 10721 */       break;
/* 10722 */     case 13:  sum = Conv.convert_BigInteger_to_long((BigInteger)this.summ.get(0));
/* 10723 */       break;
/*       */     case 14: case 15: 
/* 10725 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as long is meaningful/supported");
/* 10726 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10729 */     Conv.restoreMessages();
/* 10730 */     return sum;
/*       */   }
/*       */   
/*       */   public Long sum_as_Long()
/*       */   {
/* 10735 */     return getSum_as_Long();
/*       */   }
/*       */   
/*       */   public Long getSum_as_Long() {
/* 10739 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10740 */     if (!this.sumDone) calcSum();
/* 10741 */     Long sum = new Long(0L);
/* 10742 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10747 */       sum = Conv.convert_Double_to_Long((Double)this.summ.get(0));
/* 10748 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10758 */       if (this.sumlongToDouble) {
/* 10759 */         sum = Conv.convert_Double_to_Long((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10762 */         sum = (Long)this.summ.get(0);
/*       */       }
/* 10764 */       break;
/* 10765 */     case 12:  sum = Conv.convert_BigDecimal_to_Long((BigDecimal)this.summ.get(0));
/* 10766 */       break;
/* 10767 */     case 13:  sum = Conv.convert_BigInteger_to_Long((BigInteger)this.summ.get(0));
/* 10768 */       break;
/*       */     case 14: case 15: 
/* 10770 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Long is meaningful/supported");
/* 10771 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10774 */     Conv.restoreMessages();
/* 10775 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public int sum_as_int()
/*       */   {
/* 10781 */     return getSum_as_int();
/*       */   }
/*       */   
/*       */   public int getSum_as_int() {
/* 10785 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10786 */     if (!this.sumDone) calcSum();
/* 10787 */     int sum = 0;
/* 10788 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10793 */       sum = Conv.convert_Double_to_int((Double)this.summ.get(0));
/* 10794 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10804 */       if (this.sumlongToDouble) {
/* 10805 */         sum = Conv.convert_Double_to_int((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10808 */         sum = Conv.convert_Long_to_int((Long)this.summ.get(0));
/*       */       }
/* 10810 */       break;
/* 10811 */     case 12:  sum = Conv.convert_BigDecimal_to_int((BigDecimal)this.summ.get(0));
/* 10812 */       break;
/* 10813 */     case 13:  sum = Conv.convert_BigInteger_to_int((BigInteger)this.summ.get(0));
/* 10814 */       break;
/*       */     case 14: case 15: 
/* 10816 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as int is meaningful/supported");
/* 10817 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10820 */     Conv.restoreMessages();
/* 10821 */     return sum;
/*       */   }
/*       */   
/*       */   public Integer sum_as_Integer()
/*       */   {
/* 10826 */     return getSum_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getSum_as_Integer() {
/* 10830 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10831 */     if (!this.sumDone) calcSum();
/* 10832 */     Integer sum = new Integer(0);
/* 10833 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10838 */       sum = Conv.convert_Double_to_Integer((Double)this.summ.get(0));
/* 10839 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10849 */       if (this.sumlongToDouble) {
/* 10850 */         sum = Conv.convert_Double_to_Integer((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10853 */         sum = Conv.convert_Long_to_Integer((Long)this.summ.get(0));
/*       */       }
/* 10855 */       break;
/* 10856 */     case 12:  sum = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.summ.get(0));
/* 10857 */       break;
/* 10858 */     case 13:  sum = Conv.convert_BigInteger_to_Integer((BigInteger)this.summ.get(0));
/* 10859 */       break;
/*       */     case 14: case 15: 
/* 10861 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Integer is meaningful/supported");
/* 10862 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10865 */     Conv.restoreMessages();
/* 10866 */     return sum;
/*       */   }
/*       */   
/*       */   public short sum_as_short()
/*       */   {
/* 10871 */     return getSum_as_short();
/*       */   }
/*       */   
/*       */   public short getSum_as_short() {
/* 10875 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10876 */     if (!this.sumDone) calcSum();
/* 10877 */     short sum = 0;
/* 10878 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10883 */       sum = Conv.convert_Double_to_short((Double)this.summ.get(0));
/* 10884 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10894 */       if (this.sumlongToDouble) {
/* 10895 */         sum = Conv.convert_Double_to_short((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10898 */         sum = Conv.convert_Long_to_short((Long)this.summ.get(0));
/*       */       }
/* 10900 */       break;
/* 10901 */     case 12:  sum = Conv.convert_BigDecimal_to_short((BigDecimal)this.summ.get(0));
/* 10902 */       break;
/* 10903 */     case 13:  sum = Conv.convert_BigInteger_to_short((BigInteger)this.summ.get(0));
/* 10904 */       break;
/*       */     case 14: case 15: 
/* 10906 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as short is meaningful/supported");
/* 10907 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10910 */     Conv.restoreMessages();
/* 10911 */     return sum;
/*       */   }
/*       */   
/*       */   public Short sum_as_Short()
/*       */   {
/* 10916 */     return getSum_as_Short();
/*       */   }
/*       */   
/*       */   public Short getSum_as_Short() {
/* 10920 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10921 */     if (!this.sumDone) calcSum();
/* 10922 */     Short sum = new Short((short)0);
/* 10923 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10928 */       sum = Conv.convert_Double_to_Short((Double)this.summ.get(0));
/* 10929 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10939 */       if (this.sumlongToDouble) {
/* 10940 */         sum = Conv.convert_Double_to_Short((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10943 */         sum = Conv.convert_Long_to_Short((Long)this.summ.get(0));
/*       */       }
/* 10945 */       break;
/* 10946 */     case 12:  sum = Conv.convert_BigDecimal_to_Short((BigDecimal)this.summ.get(0));
/* 10947 */       break;
/* 10948 */     case 13:  sum = Conv.convert_BigInteger_to_Short((BigInteger)this.summ.get(0));
/* 10949 */       break;
/*       */     case 14: case 15: 
/* 10951 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Short is meaningful/supported");
/* 10952 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 10955 */     Conv.restoreMessages();
/* 10956 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public byte sum_as_byte()
/*       */   {
/* 10962 */     return getSum_as_byte();
/*       */   }
/*       */   
/*       */   public byte getSum_as_byte() {
/* 10966 */     if (this.suppressMessages) Conv.suppressMessages();
/* 10967 */     if (!this.sumDone) calcSum();
/* 10968 */     byte sum = 0;
/* 10969 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 10974 */       sum = Conv.convert_Double_to_byte((Double)this.summ.get(0));
/* 10975 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 10985 */       if (this.sumlongToDouble) {
/* 10986 */         sum = Conv.convert_Double_to_byte((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 10989 */         sum = Conv.convert_Long_to_byte((Long)this.summ.get(0));
/*       */       }
/* 10991 */       break;
/* 10992 */     case 12:  sum = Conv.convert_BigDecimal_to_byte((BigDecimal)this.summ.get(0));
/* 10993 */       break;
/* 10994 */     case 13:  sum = Conv.convert_BigInteger_to_byte((BigInteger)this.summ.get(0));
/* 10995 */       break;
/*       */     case 14: case 15: 
/* 10997 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as byte is meaningful/supported");
/* 10998 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11001 */     Conv.restoreMessages();
/* 11002 */     return sum;
/*       */   }
/*       */   
/*       */   public Byte sum_as_Byte()
/*       */   {
/* 11007 */     return getSum_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getSum_as_Byte() {
/* 11011 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11012 */     if (!this.sumDone) calcSum();
/* 11013 */     Byte sum = new Byte((byte)0);
/* 11014 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11019 */       sum = Conv.convert_Double_to_Byte((Double)this.summ.get(0));
/* 11020 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11030 */       if (this.sumlongToDouble) {
/* 11031 */         sum = Conv.convert_Double_to_Byte((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 11034 */         sum = Conv.convert_Long_to_Byte((Long)this.summ.get(0));
/*       */       }
/* 11036 */       break;
/* 11037 */     case 12:  sum = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.summ.get(0));
/* 11038 */       break;
/* 11039 */     case 13:  sum = Conv.convert_BigInteger_to_Byte((BigInteger)this.summ.get(0));
/* 11040 */       break;
/*       */     case 14: case 15: 
/* 11042 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Byte is meaningful/supported");
/* 11043 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11046 */     Conv.restoreMessages();
/* 11047 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public BigDecimal sum_as_BigDecimal()
/*       */   {
/* 11053 */     return getSum_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getSum_as_BigDecimal() {
/* 11057 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11058 */     if (!this.sumDone) calcSum();
/* 11059 */     BigDecimal sum = new BigDecimal(0.0D);
/* 11060 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11065 */       sum = Conv.convert_Double_to_BigDecimal((Double)this.summ.get(0));
/* 11066 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11076 */       if (this.sumlongToDouble) {
/* 11077 */         sum = Conv.convert_Double_to_BigDecimal((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 11080 */         sum = Conv.convert_Long_to_BigDecimal((Long)this.summ.get(0));
/*       */       }
/* 11082 */       break;
/* 11083 */     case 12:  sum = (BigDecimal)this.summ.get(0);
/* 11084 */       break;
/* 11085 */     case 13:  sum = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.summ.get(0));
/* 11086 */       break;
/*       */     case 14: case 15: 
/* 11088 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as BigDecimal is meaningful/supported");
/* 11089 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11092 */     Conv.restoreMessages();
/* 11093 */     return sum;
/*       */   }
/*       */   
/*       */   public BigInteger sum_as_BigInteger()
/*       */   {
/* 11098 */     return getSum_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getSum_as_BigInteger() {
/* 11102 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11103 */     if (!this.sumDone) calcSum();
/* 11104 */     BigInteger sum = BigInteger.ZERO;
/* 11105 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11110 */       sum = Conv.convert_Double_to_BigInteger((Double)this.summ.get(0));
/* 11111 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11121 */       if (this.sumlongToDouble) {
/* 11122 */         sum = Conv.convert_Double_to_BigInteger((Double)this.summ.get(0));
/*       */       }
/*       */       else {
/* 11125 */         sum = Conv.convert_Long_to_BigInteger((Long)this.summ.get(0));
/*       */       }
/* 11127 */       break;
/* 11128 */     case 12:  sum = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.summ.get(0));
/* 11129 */       break;
/* 11130 */     case 13:  sum = (BigInteger)this.summ.get(0);
/* 11131 */       break;
/*       */     case 14: case 15: 
/* 11133 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as BigInteger is meaningful/supported");
/* 11134 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11137 */     Conv.restoreMessages();
/* 11138 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public Complex sum_as_Complex()
/*       */   {
/* 11144 */     return getSum_as_Complex();
/*       */   }
/*       */   
/*       */   public Complex getSum_as_Complex() {
/* 11148 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11149 */     if (!this.sumDone) calcSum();
/* 11150 */     Complex sum = Complex.zero();
/* 11151 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11156 */       sum = new Complex(((Double)this.summ.get(0)).doubleValue());
/* 11157 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11167 */       if (this.sumlongToDouble) {
/* 11168 */         sum = new Complex(((Double)this.summ.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 11171 */         sum = new Complex(((Long)this.summ.get(0)).doubleValue());
/*       */       }
/* 11173 */       break;
/* 11174 */     case 12:  sum = new Complex(((BigDecimal)this.summ.get(0)).doubleValue());
/* 11175 */       break;
/* 11176 */     case 13:  sum = new Complex(((BigInteger)this.summ.get(0)).doubleValue());
/* 11177 */       break;
/*       */     case 14: case 15: 
/* 11179 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Complex is meaningful/supported");
/* 11180 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11183 */     Conv.restoreMessages();
/* 11184 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   public Phasor sum_as_Phasor()
/*       */   {
/* 11190 */     return getSum_as_Phasor();
/*       */   }
/*       */   
/*       */   public Phasor getSum_as_Phasor() {
/* 11194 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11195 */     if (!this.sumDone) calcSum();
/* 11196 */     Phasor sum = Phasor.zero();
/* 11197 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11202 */       sum = new Phasor(((Double)this.summ.get(0)).doubleValue());
/* 11203 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11213 */       if (this.sumlongToDouble) {
/* 11214 */         sum = new Phasor(((Double)this.summ.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 11217 */         sum = new Phasor(((Long)this.summ.get(0)).doubleValue());
/*       */       }
/* 11219 */       break;
/* 11220 */     case 12:  sum = new Phasor(((BigDecimal)this.summ.get(0)).doubleValue());
/* 11221 */       break;
/* 11222 */     case 13:  sum = new Phasor(((BigInteger)this.summ.get(0)).doubleValue());
/* 11223 */       break;
/*       */     case 14: case 15: 
/* 11225 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Phasor is meaningful/supported");
/* 11226 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11229 */     Conv.restoreMessages();
/* 11230 */     return sum;
/*       */   }
/*       */   
/*       */   public String sum_as_String()
/*       */   {
/* 11235 */     return getSum_as_String();
/*       */   }
/*       */   
/*       */   public String getSum_as_String() {
/* 11239 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11240 */     if (!this.sumDone) calcSum();
/* 11241 */     String sum = " ";
/* 11242 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11247 */       sum = Double.toString(((Double)this.summ.get(0)).doubleValue());
/* 11248 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11258 */       if (this.sumlongToDouble) {
/* 11259 */         sum = Double.toString(((Double)this.summ.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 11262 */         sum = Double.toString(((Long)this.summ.get(0)).doubleValue());
/*       */       }
/* 11264 */       break;
/* 11265 */     case 12:  sum = ((BigDecimal)this.summ.get(0)).toString();
/* 11266 */       break;
/* 11267 */     case 13:  sum = ((BigInteger)this.summ.get(0)).toString();
/* 11268 */       break;
/*       */     case 14: case 15: 
/* 11270 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as String is meaningful/supported");
/* 11271 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11274 */     Conv.restoreMessages();
/* 11275 */     return sum;
/*       */   }
/*       */   
/*       */ 
/*       */   protected void calcProduct()
/*       */   {
/* 11281 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11282 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11287 */       double[] dd = getArray_as_double();
/* 11288 */       double product = 1.0D;
/* 11289 */       for (int i = 0; i < this.length; i++) product *= dd[i];
/* 11290 */       this.productt.add(new Double(product));
/* 11291 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11301 */       long[] ll = getArray_as_long();
/* 11302 */       long productl = 1L;
/* 11303 */       boolean test = false;
/* 11304 */       for (int i = 0; i < this.length; i++) {
/* 11305 */         if (Long.MAX_VALUE / productl < ll[i]) test = true;
/* 11306 */         productl += ll[i];
/*       */       }
/* 11308 */       if (test) {
/* 11309 */         double[] dd2 = getArray_as_double();
/* 11310 */         double product2 = 1.0D;
/* 11311 */         for (int i = 0; i < this.length; i++) product2 *= dd2[i];
/* 11312 */         this.productt.add(new Double(product2));
/* 11313 */         this.sumlongToDouble = true;
/*       */       }
/*       */       else {
/* 11316 */         this.productt.add(new Long(productl));
/*       */       }
/* 11318 */       break;
/* 11319 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 11320 */       BigDecimal productbd = new BigDecimal(1.0D);
/* 11321 */       for (int i = 0; i < this.length; i++) productbd.multiply(bd[i]);
/* 11322 */       this.productt.add(productbd);
/* 11323 */       bd = null;
/* 11324 */       productbd = null;
/* 11325 */       break;
/* 11326 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 11327 */       BigInteger productbi = BigInteger.ONE;
/* 11328 */       for (int i = 0; i < this.length; i++) productbi.multiply(bi[i]);
/* 11329 */       this.productt.add(productbi);
/* 11330 */       bi = null;
/* 11331 */       productbi = null;
/* 11332 */       break;
/* 11333 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 11334 */       Complex productcc = Complex.plusOne();
/* 11335 */       for (int i = 0; i < this.length; i++) productcc.times(cc[i]);
/* 11336 */       this.productt.add(productcc);
/* 11337 */       break;
/* 11338 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 11339 */       Phasor productpp = Phasor.plusOne();
/* 11340 */       for (int i = 0; i < this.length; i++) productpp.times(pp[i]);
/* 11341 */       this.productt.add(productpp);
/* 11342 */       break;
/* 11343 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 11345 */     this.productDone = true;
/* 11346 */     Conv.restoreMessages();
/*       */   }
/*       */   
/*       */   public double product()
/*       */   {
/* 11351 */     return getProduct_as_double();
/*       */   }
/*       */   
/*       */   public double product_as_double() {
/* 11355 */     return getProduct_as_double();
/*       */   }
/*       */   
/*       */   public double getProduct() {
/* 11359 */     return getProduct_as_double();
/*       */   }
/*       */   
/*       */   public double getProduct_as_double() {
/* 11363 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11364 */     if (!this.productDone) calcProduct();
/* 11365 */     double product = 0.0D;
/* 11366 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11371 */       product = ((Double)this.productt.get(0)).doubleValue();
/* 11372 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11382 */       if (this.productlongToDouble) {
/* 11383 */         product = ((Double)this.productt.get(0)).doubleValue();
/*       */       }
/*       */       else {
/* 11386 */         product = Conv.convert_Long_to_double((Long)this.productt.get(0));
/*       */       }
/* 11388 */       break;
/* 11389 */     case 12:  product = Conv.convert_BigDecimal_to_double((BigDecimal)this.productt.get(0));
/* 11390 */       break;
/* 11391 */     case 13:  product = Conv.convert_BigInteger_to_double((BigInteger)this.productt.get(0));
/* 11392 */       break;
/*       */     case 14: case 15: 
/* 11394 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas double is meaningful/supported");
/* 11395 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11398 */     Conv.restoreMessages();
/* 11399 */     return product;
/*       */   }
/*       */   
/*       */   public Double product_as_Double()
/*       */   {
/* 11404 */     return getProduct_as_Double();
/*       */   }
/*       */   
/*       */   public Double getProduct_as_Double() {
/* 11408 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11409 */     if (!this.productDone) calcProduct();
/* 11410 */     Double product = new Double(0.0D);
/* 11411 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11416 */       product = (Double)this.productt.get(0);
/* 11417 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11427 */       if (this.productlongToDouble) {
/* 11428 */         product = (Double)this.productt.get(0);
/*       */       }
/*       */       else {
/* 11431 */         product = Conv.convert_Long_to_Double((Long)this.productt.get(0));
/*       */       }
/* 11433 */       break;
/* 11434 */     case 12:  product = Conv.convert_BigDecimal_to_Double((BigDecimal)this.productt.get(0));
/* 11435 */       break;
/* 11436 */     case 13:  product = Conv.convert_BigInteger_to_Double((BigInteger)this.productt.get(0));
/* 11437 */       break;
/*       */     case 14: case 15: 
/* 11439 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Double is meaningful/supported");
/* 11440 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11443 */     Conv.restoreMessages();
/* 11444 */     return product;
/*       */   }
/*       */   
/*       */ 
/*       */   public float product_as_float()
/*       */   {
/* 11450 */     return getProduct_as_float();
/*       */   }
/*       */   
/*       */   public float getProduct_as_float() {
/* 11454 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11455 */     if (!this.productDone) calcProduct();
/* 11456 */     float product = 0.0F;
/* 11457 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11462 */       product = Conv.convert_Double_to_float((Double)this.productt.get(0));
/* 11463 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11473 */       if (this.productlongToDouble) {
/* 11474 */         product = Conv.convert_Double_to_float((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11477 */         product = Conv.convert_Long_to_float((Long)this.productt.get(0));
/*       */       }
/* 11479 */       break;
/* 11480 */     case 12:  product = Conv.convert_BigDecimal_to_float((BigDecimal)this.productt.get(0));
/* 11481 */       break;
/* 11482 */     case 13:  product = Conv.convert_BigInteger_to_float((BigInteger)this.productt.get(0));
/* 11483 */       break;
/*       */     case 14: case 15: 
/* 11485 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas float is meaningful/supported");
/* 11486 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11489 */     Conv.restoreMessages();
/* 11490 */     return product;
/*       */   }
/*       */   
/*       */   public Float product_as_Float()
/*       */   {
/* 11495 */     return getProduct_as_Float();
/*       */   }
/*       */   
/*       */   public Float getProduct_as_Float() {
/* 11499 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11500 */     if (!this.productDone) calcProduct();
/* 11501 */     Float product = new Float(0.0F);
/* 11502 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11507 */       product = Conv.convert_Double_to_Float((Double)this.productt.get(0));
/* 11508 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11518 */       if (this.productlongToDouble) {
/* 11519 */         product = Conv.convert_Double_to_Float((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11522 */         product = Conv.convert_Long_to_Float((Long)this.productt.get(0));
/*       */       }
/* 11524 */       break;
/* 11525 */     case 12:  product = Conv.convert_BigDecimal_to_Float((BigDecimal)this.productt.get(0));
/* 11526 */       break;
/* 11527 */     case 13:  product = Conv.convert_BigInteger_to_Float((BigInteger)this.productt.get(0));
/* 11528 */       break;
/*       */     case 14: case 15: 
/* 11530 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Float is meaningful/supported");
/* 11531 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11534 */     Conv.restoreMessages();
/* 11535 */     return product;
/*       */   }
/*       */   
/*       */ 
/*       */   public long product_as_long()
/*       */   {
/* 11541 */     return getProduct_as_long();
/*       */   }
/*       */   
/*       */   public long getProduct_as_long() {
/* 11545 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11546 */     if (!this.productDone) calcProduct();
/* 11547 */     long product = 0L;
/* 11548 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11553 */       product = Conv.convert_Double_to_long((Double)this.productt.get(0));
/* 11554 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11564 */       if (this.productlongToDouble) {
/* 11565 */         product = Conv.convert_Double_to_long((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11568 */         product = ((Long)this.productt.get(0)).longValue();
/*       */       }
/* 11570 */       break;
/* 11571 */     case 12:  product = Conv.convert_BigDecimal_to_long((BigDecimal)this.productt.get(0));
/* 11572 */       break;
/* 11573 */     case 13:  product = Conv.convert_BigInteger_to_long((BigInteger)this.productt.get(0));
/* 11574 */       break;
/*       */     case 14: case 15: 
/* 11576 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas long is meaningful/supported");
/* 11577 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11580 */     Conv.restoreMessages();
/* 11581 */     return product;
/*       */   }
/*       */   
/*       */   public Long product_as_Long()
/*       */   {
/* 11586 */     return getProduct_as_Long();
/*       */   }
/*       */   
/*       */   public Long getProduct_as_Long() {
/* 11590 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11591 */     if (!this.productDone) calcProduct();
/* 11592 */     Long product = new Long(0L);
/* 11593 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11598 */       product = Conv.convert_Double_to_Long((Double)this.productt.get(0));
/* 11599 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11609 */       if (this.productlongToDouble) {
/* 11610 */         product = Conv.convert_Double_to_Long((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11613 */         product = (Long)this.productt.get(0);
/*       */       }
/* 11615 */       break;
/* 11616 */     case 12:  product = Conv.convert_BigDecimal_to_Long((BigDecimal)this.productt.get(0));
/* 11617 */       break;
/* 11618 */     case 13:  product = Conv.convert_BigInteger_to_Long((BigInteger)this.productt.get(0));
/* 11619 */       break;
/*       */     case 14: case 15: 
/* 11621 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Long is meaningful/supported");
/* 11622 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11625 */     Conv.restoreMessages();
/* 11626 */     return product;
/*       */   }
/*       */   
/*       */   public int product_as_int()
/*       */   {
/* 11631 */     return getProduct_as_int();
/*       */   }
/*       */   
/*       */   public int getProduct_as_int() {
/* 11635 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11636 */     if (!this.productDone) calcProduct();
/* 11637 */     int product = 0;
/* 11638 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11643 */       product = Conv.convert_Double_to_int((Double)this.productt.get(0));
/* 11644 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11654 */       if (this.productlongToDouble) {
/* 11655 */         product = Conv.convert_Double_to_int((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11658 */         product = Conv.convert_Long_to_int((Long)this.productt.get(0));
/*       */       }
/* 11660 */       break;
/* 11661 */     case 12:  product = Conv.convert_BigDecimal_to_int((BigDecimal)this.productt.get(0));
/* 11662 */       break;
/* 11663 */     case 13:  product = Conv.convert_BigInteger_to_int((BigInteger)this.productt.get(0));
/* 11664 */       break;
/*       */     case 14: case 15: 
/* 11666 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas int is meaningful/supported");
/* 11667 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11670 */     Conv.restoreMessages();
/* 11671 */     return product;
/*       */   }
/*       */   
/*       */   public Integer product_as_Integer()
/*       */   {
/* 11676 */     return getProduct_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getProduct_as_Integer() {
/* 11680 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11681 */     if (!this.productDone) calcProduct();
/* 11682 */     Integer product = new Integer(0);
/* 11683 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11688 */       product = Conv.convert_Double_to_Integer((Double)this.productt.get(0));
/* 11689 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11699 */       if (this.productlongToDouble) {
/* 11700 */         product = Conv.convert_Double_to_Integer((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11703 */         product = Conv.convert_Long_to_Integer((Long)this.productt.get(0));
/*       */       }
/* 11705 */       break;
/* 11706 */     case 12:  product = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.productt.get(0));
/* 11707 */       break;
/* 11708 */     case 13:  product = Conv.convert_BigInteger_to_Integer((BigInteger)this.productt.get(0));
/* 11709 */       break;
/*       */     case 14: case 15: 
/* 11711 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Integer is meaningful/supported");
/* 11712 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11715 */     Conv.restoreMessages();
/* 11716 */     return product;
/*       */   }
/*       */   
/*       */   public short product_as_short()
/*       */   {
/* 11721 */     return getProduct_as_short();
/*       */   }
/*       */   
/*       */   public short getProduct_as_short() {
/* 11725 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11726 */     if (!this.productDone) calcProduct();
/* 11727 */     short product = 0;
/* 11728 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11733 */       product = Conv.convert_Double_to_short((Double)this.productt.get(0));
/* 11734 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11744 */       if (this.productlongToDouble) {
/* 11745 */         product = Conv.convert_Double_to_short((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11748 */         product = Conv.convert_Long_to_short((Long)this.productt.get(0));
/*       */       }
/* 11750 */       break;
/* 11751 */     case 12:  product = Conv.convert_BigDecimal_to_short((BigDecimal)this.productt.get(0));
/* 11752 */       break;
/* 11753 */     case 13:  product = Conv.convert_BigInteger_to_short((BigInteger)this.productt.get(0));
/* 11754 */       break;
/*       */     case 14: case 15: 
/* 11756 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas short is meaningful/supported");
/* 11757 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11760 */     Conv.restoreMessages();
/* 11761 */     return product;
/*       */   }
/*       */   
/*       */   public Short product_as_Short()
/*       */   {
/* 11766 */     return getProduct_as_Short();
/*       */   }
/*       */   
/*       */   public Short getProduct_as_Short() {
/* 11770 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11771 */     if (!this.productDone) calcProduct();
/* 11772 */     Short product = new Short((short)0);
/* 11773 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11778 */       product = Conv.convert_Double_to_Short((Double)this.productt.get(0));
/* 11779 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11789 */       if (this.productlongToDouble) {
/* 11790 */         product = Conv.convert_Double_to_Short((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11793 */         product = Conv.convert_Long_to_Short((Long)this.productt.get(0));
/*       */       }
/* 11795 */       break;
/* 11796 */     case 12:  product = Conv.convert_BigDecimal_to_Short((BigDecimal)this.productt.get(0));
/* 11797 */       break;
/* 11798 */     case 13:  product = Conv.convert_BigInteger_to_Short((BigInteger)this.productt.get(0));
/* 11799 */       break;
/*       */     case 14: case 15: 
/* 11801 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Short is meaningful/supported");
/* 11802 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11805 */     Conv.restoreMessages();
/* 11806 */     return product;
/*       */   }
/*       */   
/*       */   public byte product_as_byte()
/*       */   {
/* 11811 */     return getProduct_as_byte();
/*       */   }
/*       */   
/*       */   public byte getProduct_as_byte() {
/* 11815 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11816 */     if (!this.productDone) calcProduct();
/* 11817 */     byte product = 0;
/* 11818 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11823 */       product = Conv.convert_Double_to_byte((Double)this.productt.get(0));
/* 11824 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11834 */       if (this.productlongToDouble) {
/* 11835 */         product = Conv.convert_Double_to_byte((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11838 */         product = Conv.convert_Long_to_byte((Long)this.productt.get(0));
/*       */       }
/* 11840 */       break;
/* 11841 */     case 12:  product = Conv.convert_BigDecimal_to_byte((BigDecimal)this.productt.get(0));
/* 11842 */       break;
/* 11843 */     case 13:  product = Conv.convert_BigInteger_to_byte((BigInteger)this.productt.get(0));
/* 11844 */       break;
/*       */     case 14: case 15: 
/* 11846 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas byte is meaningful/supported");
/* 11847 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11850 */     Conv.restoreMessages();
/* 11851 */     return product;
/*       */   }
/*       */   
/*       */   public Byte product_as_Byte()
/*       */   {
/* 11856 */     return getProduct_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getProduct_as_Byte() {
/* 11860 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11861 */     if (!this.productDone) calcProduct();
/* 11862 */     Byte product = new Byte((byte)0);
/* 11863 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11868 */       product = Conv.convert_Double_to_Byte((Double)this.productt.get(0));
/* 11869 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11879 */       if (this.productlongToDouble) {
/* 11880 */         product = Conv.convert_Double_to_Byte((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11883 */         product = Conv.convert_Long_to_Byte((Long)this.productt.get(0));
/*       */       }
/* 11885 */       break;
/* 11886 */     case 12:  product = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.productt.get(0));
/* 11887 */       break;
/* 11888 */     case 13:  product = Conv.convert_BigInteger_to_Byte((BigInteger)this.productt.get(0));
/* 11889 */       break;
/*       */     case 14: case 15: 
/* 11891 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Byte is meaningful/supported");
/* 11892 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11895 */     Conv.restoreMessages();
/* 11896 */     return product;
/*       */   }
/*       */   
/*       */ 
/*       */   public BigDecimal product_as_BigDecimal()
/*       */   {
/* 11902 */     return getProduct_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getProduct_as_BigDecimal() {
/* 11906 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11907 */     if (!this.productDone) calcProduct();
/* 11908 */     BigDecimal product = new BigDecimal(0.0D);
/* 11909 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11914 */       product = Conv.convert_Double_to_BigDecimal((Double)this.productt.get(0));
/* 11915 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11925 */       if (this.productlongToDouble) {
/* 11926 */         product = Conv.convert_Double_to_BigDecimal((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11929 */         product = Conv.convert_Long_to_BigDecimal((Long)this.productt.get(0));
/*       */       }
/* 11931 */       break;
/* 11932 */     case 12:  product = (BigDecimal)this.productt.get(0);
/* 11933 */       break;
/* 11934 */     case 13:  product = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.productt.get(0));
/* 11935 */       break;
/*       */     case 14: case 15: 
/* 11937 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas BigDecimal is meaningful/supported");
/* 11938 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11941 */     Conv.restoreMessages();
/* 11942 */     return product;
/*       */   }
/*       */   
/*       */   public BigInteger product_as_BigInteger()
/*       */   {
/* 11947 */     return getProduct_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getProduct_as_BigInteger() {
/* 11951 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11952 */     if (!this.productDone) calcProduct();
/* 11953 */     BigInteger product = BigInteger.ZERO;
/* 11954 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 11959 */       product = Conv.convert_Double_to_BigInteger((Double)this.productt.get(0));
/* 11960 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 11970 */       if (this.productlongToDouble) {
/* 11971 */         product = Conv.convert_Double_to_BigInteger((Double)this.productt.get(0));
/*       */       }
/*       */       else {
/* 11974 */         product = Conv.convert_Long_to_BigInteger((Long)this.productt.get(0));
/*       */       }
/* 11976 */       break;
/* 11977 */     case 12:  product = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.productt.get(0));
/* 11978 */       break;
/* 11979 */     case 13:  product = (BigInteger)this.productt.get(0);
/* 11980 */       break;
/*       */     case 14: case 15: 
/* 11982 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas BigInteger is meaningful/supported");
/* 11983 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 11986 */     Conv.restoreMessages();
/* 11987 */     return product;
/*       */   }
/*       */   
/*       */   public Complex product_as_Complex()
/*       */   {
/* 11992 */     return getProduct_as_Complex();
/*       */   }
/*       */   
/*       */   public Complex getProduct_as_Complex() {
/* 11996 */     if (this.suppressMessages) Conv.suppressMessages();
/* 11997 */     if (!this.productDone) calcProduct();
/* 11998 */     Complex product = Complex.zero();
/* 11999 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 12004 */       product = new Complex(((Double)this.productt.get(0)).doubleValue());
/* 12005 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12015 */       if (this.productlongToDouble) {
/* 12016 */         product = new Complex(((Double)this.productt.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 12019 */         product = new Complex(((Long)this.productt.get(0)).doubleValue());
/*       */       }
/* 12021 */       break;
/* 12022 */     case 12:  product = new Complex(((BigDecimal)this.productt.get(0)).doubleValue());
/* 12023 */       break;
/* 12024 */     case 13:  product = new Complex(((BigInteger)this.productt.get(0)).doubleValue());
/* 12025 */       break;
/*       */     case 14: case 15: 
/* 12027 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Complex is meaningful/supported");
/* 12028 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 12031 */     Conv.restoreMessages();
/* 12032 */     return product;
/*       */   }
/*       */   
/*       */   public Phasor product_as_Phasor()
/*       */   {
/* 12037 */     return getProduct_as_Phasor();
/*       */   }
/*       */   
/*       */   public Phasor getProduct_as_Phasor() {
/* 12041 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12042 */     if (!this.productDone) calcProduct();
/* 12043 */     Phasor product = Phasor.zero();
/* 12044 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 12049 */       product = new Phasor(((Double)this.productt.get(0)).doubleValue());
/* 12050 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12060 */       if (this.productlongToDouble) {
/* 12061 */         product = new Phasor(((Double)this.productt.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 12064 */         product = new Phasor(((Long)this.productt.get(0)).doubleValue());
/*       */       }
/* 12066 */       break;
/* 12067 */     case 12:  product = new Phasor(((BigDecimal)this.productt.get(0)).doubleValue());
/* 12068 */       break;
/* 12069 */     case 13:  product = new Phasor(((BigInteger)this.productt.get(0)).doubleValue());
/* 12070 */       break;
/*       */     case 14: case 15: 
/* 12072 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Phasor is meaningful/supported");
/* 12073 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 12076 */     Conv.restoreMessages();
/* 12077 */     return product;
/*       */   }
/*       */   
/*       */   public String product_as_String()
/*       */   {
/* 12082 */     return getProduct_as_String();
/*       */   }
/*       */   
/*       */   public String getProduct_as_String() {
/* 12086 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12087 */     if (!this.productDone) calcProduct();
/* 12088 */     String product = " ";
/* 12089 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 18: 
/* 12094 */       product = Double.toString(((Double)this.productt.get(0)).doubleValue());
/* 12095 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12105 */       if (this.productlongToDouble) {
/* 12106 */         product = Double.toString(((Double)this.productt.get(0)).doubleValue());
/*       */       }
/*       */       else {
/* 12109 */         product = Double.toString(((Long)this.productt.get(0)).doubleValue());
/*       */       }
/* 12111 */       break;
/* 12112 */     case 12:  product = ((BigDecimal)this.productt.get(0)).toString();
/* 12113 */       break;
/* 12114 */     case 13:  product = ((BigInteger)this.productt.get(0)).toString();
/* 12115 */       break;
/*       */     case 14: case 15: 
/* 12117 */       throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas String is meaningful/supported");
/* 12118 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/* 12121 */     Conv.restoreMessages();
/* 12122 */     return product;
/*       */   }
/*       */   
/*       */   public ArrayMaths randomize()
/*       */   {
/* 12127 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12128 */     ArrayMaths am = new ArrayMaths();
/* 12129 */     am.array = new ArrayList();
/* 12130 */     am.length = this.length;
/* 12131 */     am.type = this.type;
/* 12132 */     PsRandom ran = new PsRandom();
/* 12133 */     am.sortedIndices = ran.uniqueIntegerArray(this.length - 1);
/*       */     
/* 12135 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 12137 */       for (int i = 0; i < this.length; i++) am.array.add((Double)this.array.get(am.sortedIndices[i]));
/* 12138 */       break;
/*       */     case 2: case 3: 
/* 12140 */       for (int i = 0; i < this.length; i++) am.array.add((Float)this.array.get(am.sortedIndices[i]));
/* 12141 */       break;
/*       */     case 4: case 5: 
/* 12143 */       for (int i = 0; i < this.length; i++) am.array.add((Long)this.array.get(am.sortedIndices[i]));
/* 12144 */       break;
/*       */     case 6: case 7: 
/* 12146 */       for (int i = 0; i < this.length; i++) am.array.add((Integer)this.array.get(am.sortedIndices[i]));
/* 12147 */       break;
/*       */     case 8: case 9: 
/* 12149 */       for (int i = 0; i < this.length; i++) am.array.add((Short)this.array.get(am.sortedIndices[i]));
/* 12150 */       break;
/*       */     case 10: case 11: 
/* 12152 */       for (int i = 0; i < this.length; i++) am.array.add((Byte)this.array.get(am.sortedIndices[i]));
/* 12153 */       break;
/* 12154 */     case 12:  for (int i = 0; i < this.length; i++) am.array.add((BigDecimal)this.array.get(am.sortedIndices[i]));
/* 12155 */       break;
/* 12156 */     case 13:  for (int i = 0; i < this.length; i++) am.array.add((BigInteger)this.array.get(am.sortedIndices[i]));
/* 12157 */       break;
/* 12158 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add((Complex)this.array.get(am.sortedIndices[i]));
/* 12159 */       break;
/* 12160 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add((Phasor)this.array.get(am.sortedIndices[i]));
/* 12161 */       break;
/*       */     case 16: case 17: 
/* 12163 */       for (int i = 0; i < this.length; i++) am.array.add((Character)this.array.get(am.sortedIndices[i]));
/* 12164 */       break;
/* 12165 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(am.sortedIndices[i]));
/* 12166 */       break;
/* 12167 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12169 */     int[] maxminIndices = new int[2];
/* 12170 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12171 */     am.maxIndex = maxminIndices[0];
/* 12172 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12174 */     Conv.restoreMessages();
/* 12175 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths randomise()
/*       */   {
/* 12180 */     return randomize();
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths sort()
/*       */   {
/* 12186 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12187 */     ArrayMaths am = new ArrayMaths();
/* 12188 */     am.array = new ArrayList();
/* 12189 */     am.length = this.length;
/* 12190 */     am.type = this.type;
/* 12191 */     am.sortedIndices = new int[this.length];
/*       */     
/* 12193 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 12195 */       double[] dd1 = getArray_as_double();
/* 12196 */       am.sortedIndices = sortWithIndices(dd1);
/* 12197 */       for (int i = 0; i < this.length; i++) am.array.add((Double)this.array.get(am.sortedIndices[i]));
/* 12198 */       break;
/*       */     case 2: case 3: 
/* 12200 */       double[] dd2 = getArray_as_double();
/* 12201 */       am.sortedIndices = sortWithIndices(dd2);
/* 12202 */       for (int i = 0; i < this.length; i++) am.array.add((Float)this.array.get(am.sortedIndices[i]));
/* 12203 */       break;
/*       */     case 4: case 5: 
/* 12205 */       long[] ll1 = getArray_as_long();
/* 12206 */       am.sortedIndices = sortWithIndices(ll1);
/* 12207 */       for (int i = 0; i < this.length; i++) am.array.add((Long)this.array.get(am.sortedIndices[i]));
/* 12208 */       break;
/*       */     case 6: case 7: 
/* 12210 */       long[] ll2 = getArray_as_long();
/* 12211 */       am.sortedIndices = sortWithIndices(ll2);
/* 12212 */       for (int i = 0; i < this.length; i++) am.array.add((Integer)this.array.get(am.sortedIndices[i]));
/* 12213 */       break;
/*       */     case 8: case 9: 
/* 12215 */       long[] ll3 = getArray_as_long();
/* 12216 */       am.sortedIndices = sortWithIndices(ll3);
/* 12217 */       for (int i = 0; i < this.length; i++) am.array.add((Short)this.array.get(am.sortedIndices[i]));
/* 12218 */       break;
/*       */     case 10: case 11: 
/* 12220 */       long[] ll4 = getArray_as_long();
/* 12221 */       am.sortedIndices = sortWithIndices(ll4);
/* 12222 */       for (int i = 0; i < this.length; i++) am.array.add((Byte)this.array.get(am.sortedIndices[i]));
/* 12223 */       break;
/* 12224 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 12225 */       am.sortedIndices = sortWithIndices(bd);
/* 12226 */       for (int i = 0; i < this.length; i++) am.array.add((BigDecimal)this.array.get(am.sortedIndices[i]));
/* 12227 */       break;
/* 12228 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 12229 */       am.sortedIndices = sortWithIndices(bi);
/* 12230 */       for (int i = 0; i < this.length; i++) am.array.add((BigInteger)this.array.get(am.sortedIndices[i]));
/* 12231 */       break;
/* 12232 */     case 14:  ArrayMaths am2 = abs();
/* 12233 */       double[] cc = am2.getArray_as_double();
/* 12234 */       am.sortedIndices = sortWithIndices(cc);
/* 12235 */       for (int i = 0; i < this.length; i++) am.array.add((Complex)this.array.get(am.sortedIndices[i]));
/* 12236 */       break;
/* 12237 */     case 15:  ArrayMaths am3 = abs();
/* 12238 */       double[] pp = am3.getArray_as_double();
/* 12239 */       am.sortedIndices = sortWithIndices(pp);
/* 12240 */       for (int i = 0; i < this.length; i++) am.array.add((Phasor)this.array.get(am.sortedIndices[i]));
/* 12241 */       break;
/*       */     case 16: case 17: 
/* 12243 */       long[] ii = getArray_as_long();
/* 12244 */       am.sortedIndices = sortWithIndices(ii);
/* 12245 */       for (int i = 0; i < this.length; i++) am.array.add((Character)this.array.get(am.sortedIndices[i]));
/* 12246 */       break;
/* 12247 */     case 18:  throw new IllegalArgumentException("Alphabetic sorting is not supported by this method");
/* 12248 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12250 */     int[] maxminIndices = new int[2];
/* 12251 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12252 */     am.maxIndex = maxminIndices[0];
/* 12253 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12255 */     Conv.restoreMessages();
/* 12256 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths sort(int[] indices)
/*       */   {
/* 12261 */     int nArg = indices.length;
/* 12262 */     if (this.length != nArg) { throw new IllegalArgumentException("The argument array [length = " + nArg + "], must be of the same length as this instance array [length = " + this.length + "]");
/*       */     }
/* 12264 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12265 */     ArrayMaths am = new ArrayMaths();
/* 12266 */     am.array = new ArrayList();
/* 12267 */     am.length = this.length;
/* 12268 */     am.type = this.type;
/* 12269 */     am.sortedIndices = indices;
/* 12270 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 12272 */       for (int i = 0; i < this.length; i++) am.array.add((Double)this.array.get(am.sortedIndices[i]));
/* 12273 */       break;
/*       */     case 2: case 3: 
/* 12275 */       for (int i = 0; i < this.length; i++) am.array.add((Float)this.array.get(am.sortedIndices[i]));
/* 12276 */       break;
/*       */     case 4: case 5: 
/* 12278 */       for (int i = 0; i < this.length; i++) am.array.add((Long)this.array.get(am.sortedIndices[i]));
/* 12279 */       break;
/*       */     case 6: case 7: 
/* 12281 */       for (int i = 0; i < this.length; i++) am.array.add((Integer)this.array.get(am.sortedIndices[i]));
/* 12282 */       break;
/*       */     case 8: case 9: 
/* 12284 */       for (int i = 0; i < this.length; i++) am.array.add((Short)this.array.get(am.sortedIndices[i]));
/* 12285 */       break;
/*       */     case 10: case 11: 
/* 12287 */       for (int i = 0; i < this.length; i++) am.array.add((Byte)this.array.get(am.sortedIndices[i]));
/* 12288 */       break;
/* 12289 */     case 12:  for (int i = 0; i < this.length; i++) am.array.add((BigDecimal)this.array.get(am.sortedIndices[i]));
/* 12290 */       break;
/* 12291 */     case 13:  for (int i = 0; i < this.length; i++) am.array.add((BigInteger)this.array.get(am.sortedIndices[i]));
/* 12292 */       break;
/* 12293 */     case 14:  for (int i = 0; i < this.length; i++) am.array.add((Complex)this.array.get(am.sortedIndices[i]));
/* 12294 */       break;
/* 12295 */     case 15:  for (int i = 0; i < this.length; i++) am.array.add((Phasor)this.array.get(am.sortedIndices[i]));
/* 12296 */       break;
/*       */     case 16: case 17: 
/* 12298 */       for (int i = 0; i < this.length; i++) am.array.add((Character)this.array.get(am.sortedIndices[i]));
/* 12299 */       break;
/* 12300 */     case 18:  for (int i = 0; i < this.length; i++) am.array.add((String)this.array.get(am.sortedIndices[i]));
/* 12301 */       break;
/* 12302 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12304 */     int[] maxminIndices = new int[2];
/* 12305 */     if (this.type != 18) findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12306 */     am.maxIndex = maxminIndices[0];
/* 12307 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12309 */     Conv.restoreMessages();
/* 12310 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   protected int[] sortWithIndices(double[] aa)
/*       */   {
/* 12319 */     int index = 0;
/* 12320 */     int lastIndex = -1;
/* 12321 */     double holdb = 0.0D;
/* 12322 */     int holdi = 0;
/* 12323 */     double[] bb = new double[this.length];
/* 12324 */     int[] indices = new int[this.length];
/* 12325 */     for (int i = 0; i < this.length; i++) {
/* 12326 */       bb[i] = aa[i];
/* 12327 */       indices[i] = i;
/*       */     }
/*       */     
/* 12330 */     while (lastIndex != this.length - 1) {
/* 12331 */       index = lastIndex + 1;
/* 12332 */       for (int i = lastIndex + 2; i < this.length; i++) {
/* 12333 */         if (bb[i] < bb[index]) {
/* 12334 */           index = i;
/*       */         }
/*       */       }
/* 12337 */       lastIndex++;
/* 12338 */       holdb = bb[index];
/* 12339 */       bb[index] = bb[lastIndex];
/* 12340 */       bb[lastIndex] = holdb;
/* 12341 */       holdi = indices[index];
/* 12342 */       indices[index] = indices[lastIndex];
/* 12343 */       indices[lastIndex] = holdi;
/*       */     }
/* 12345 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   protected int[] sortWithIndices(long[] aa)
/*       */   {
/* 12351 */     int index = 0;
/* 12352 */     int lastIndex = -1;
/* 12353 */     long holdb = 0L;
/* 12354 */     int holdi = 0;
/* 12355 */     long[] bb = new long[this.length];
/* 12356 */     int[] indices = new int[this.length];
/* 12357 */     for (int i = 0; i < this.length; i++) {
/* 12358 */       bb[i] = aa[i];
/* 12359 */       indices[i] = i;
/*       */     }
/*       */     
/* 12362 */     while (lastIndex != this.length - 1) {
/* 12363 */       index = lastIndex + 1;
/* 12364 */       for (int i = lastIndex + 2; i < this.length; i++) {
/* 12365 */         if (bb[i] < bb[index]) {
/* 12366 */           index = i;
/*       */         }
/*       */       }
/* 12369 */       lastIndex++;
/* 12370 */       holdb = bb[index];
/* 12371 */       bb[index] = bb[lastIndex];
/* 12372 */       bb[lastIndex] = holdb;
/* 12373 */       holdi = indices[index];
/* 12374 */       indices[index] = indices[lastIndex];
/* 12375 */       indices[lastIndex] = holdi;
/*       */     }
/* 12377 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   protected int[] sortWithIndices(BigDecimal[] aa)
/*       */   {
/* 12383 */     int index = 0;
/* 12384 */     int lastIndex = -1;
/* 12385 */     BigDecimal holdb = BigDecimal.ZERO;
/* 12386 */     int holdi = 0;
/* 12387 */     BigDecimal[] bb = new BigDecimal[this.length];
/* 12388 */     int[] indices = new int[this.length];
/* 12389 */     for (int i = 0; i < this.length; i++) {
/* 12390 */       bb[i] = aa[i];
/* 12391 */       indices[i] = i;
/*       */     }
/*       */     
/* 12394 */     while (lastIndex != this.length - 1) {
/* 12395 */       index = lastIndex + 1;
/* 12396 */       for (int i = lastIndex + 2; i < this.length; i++) {
/* 12397 */         if (bb[i].compareTo(bb[index]) == -1) {
/* 12398 */           index = i;
/*       */         }
/*       */       }
/* 12401 */       lastIndex++;
/* 12402 */       holdb = bb[index];
/* 12403 */       bb[index] = bb[lastIndex];
/* 12404 */       bb[lastIndex] = holdb;
/* 12405 */       holdi = indices[index];
/* 12406 */       indices[index] = indices[lastIndex];
/* 12407 */       indices[lastIndex] = holdi;
/*       */     }
/*       */     
/* 12410 */     holdb = null;
/* 12411 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   protected int[] sortWithIndices(BigInteger[] aa)
/*       */   {
/* 12417 */     int index = 0;
/* 12418 */     int lastIndex = -1;
/* 12419 */     BigInteger holdb = BigInteger.ZERO;
/* 12420 */     int holdi = 0;
/* 12421 */     BigInteger[] bb = new BigInteger[this.length];
/* 12422 */     int[] indices = new int[this.length];
/* 12423 */     for (int i = 0; i < this.length; i++) {
/* 12424 */       bb[i] = aa[i];
/* 12425 */       indices[i] = i;
/*       */     }
/*       */     
/* 12428 */     while (lastIndex != this.length - 1) {
/* 12429 */       index = lastIndex + 1;
/* 12430 */       for (int i = lastIndex + 2; i < this.length; i++) {
/* 12431 */         if (bb[i].compareTo(bb[index]) == -1) {
/* 12432 */           index = i;
/*       */         }
/*       */       }
/* 12435 */       lastIndex++;
/* 12436 */       holdb = bb[index];
/* 12437 */       bb[index] = bb[lastIndex];
/* 12438 */       bb[lastIndex] = holdb;
/* 12439 */       holdi = indices[index];
/* 12440 */       indices[index] = indices[lastIndex];
/* 12441 */       indices[lastIndex] = holdi;
/*       */     }
/*       */     
/* 12444 */     holdb = null;
/* 12445 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] originalIndices()
/*       */   {
/* 12451 */     if (this.sortedIndices == null) System.out.println("method: originalIndices: array has not been sorted: null returned");
/* 12452 */     return this.sortedIndices;
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths concatenate(double[] xx)
/*       */   {
/* 12458 */     int xlength = xx.length;
/* 12459 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12460 */     ArrayMaths am = new ArrayMaths();
/* 12461 */     am.array = new ArrayList();
/* 12462 */     am.length = (xx.length + this.length);
/* 12463 */     ArrayMaths am2 = null;
/* 12464 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12478 */       double[] yy = getArray_as_double();
/* 12479 */       double[] zz = new double[am.length];
/* 12480 */       for (int i = 0; i < this.length; i++) zz[i] = yy[i];
/* 12481 */       for (int i = 0; i < xlength; i++) zz[(i + this.length)] = xx[i];
/* 12482 */       for (int i = 0; i < am.length; i++) am.array.add(new Double(zz[i]));
/* 12483 */       am.type = 1;
/* 12484 */       break;
/*       */     case 12: case 13: 
/* 12486 */       BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 12487 */       am2 = new ArrayMaths(xx);
/* 12488 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 12489 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 12490 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 12491 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 12492 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 12493 */       bd1 = null;
/* 12494 */       bd2 = null;
/* 12495 */       bda = null;
/* 12496 */       am.type = 12;
/* 12497 */       break;
/* 12498 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 12499 */       am2 = new ArrayMaths(xx);
/* 12500 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12501 */       Complex[] cca = new Complex[am.length];
/* 12502 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12503 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12504 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12505 */       am.type = 14;
/* 12506 */       break;
/* 12507 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 12508 */       am2 = new ArrayMaths(xx);
/* 12509 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 12510 */       Phasor[] ppa = new Phasor[am.length];
/* 12511 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 12512 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 12513 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 12514 */       am.type = 15;
/* 12515 */       break;
/* 12516 */     case 18:  String[] ss1 = getArray_as_String();
/* 12517 */       am2 = new ArrayMaths(xx);
/* 12518 */       String[] ss2 = am2.getArray_as_String();
/* 12519 */       String[] ssa = new String[am.length];
/* 12520 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12521 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12522 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12523 */       am.type = 18;
/* 12524 */       break;
/* 12525 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12527 */     int[] maxminIndices = new int[2];
/* 12528 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12529 */     am.maxIndex = maxminIndices[0];
/* 12530 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12532 */     Conv.restoreMessages();
/* 12533 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Double[] xx) {
/* 12537 */     double[] dd = new double[xx.length];
/* 12538 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].doubleValue();
/* 12539 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */ 
/*       */   public ArrayMaths concatenate(float[] xx)
/*       */   {
/* 12545 */     int xlength = xx.length;
/* 12546 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12547 */     ArrayMaths am = new ArrayMaths();
/* 12548 */     am.array = new ArrayList();
/* 12549 */     am.length = (xx.length + this.length);
/* 12550 */     ArrayMaths am2 = null;
/* 12551 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/* 12555 */       double[] yy = getArray_as_double();
/* 12556 */       double[] zz = new double[am.length];
/* 12557 */       for (int i = 0; i < this.length; i++) zz[i] = yy[i];
/* 12558 */       for (int i = 0; i < xlength; i++) zz[(i + this.length)] = xx[i];
/* 12559 */       for (int i = 0; i < am.length; i++) am.array.add(new Double(zz[i]));
/* 12560 */       am.type = 1;
/* 12561 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12571 */       float[] ff = getArray_as_float();
/* 12572 */       float[] gg = new float[am.length];
/* 12573 */       for (int i = 0; i < this.length; i++) gg[i] = ff[i];
/* 12574 */       for (int i = 0; i < xlength; i++) gg[(i + this.length)] = xx[i];
/* 12575 */       for (int i = 0; i < am.length; i++) am.array.add(new Float(gg[i]));
/* 12576 */       am.type = 3;
/* 12577 */       break;
/*       */     case 12: case 13: 
/* 12579 */       BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 12580 */       am2 = new ArrayMaths(xx);
/* 12581 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 12582 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 12583 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 12584 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 12585 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 12586 */       bd1 = null;
/* 12587 */       bd2 = null;
/* 12588 */       bda = null;
/* 12589 */       am.type = 12;
/* 12590 */       break;
/* 12591 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 12592 */       am2 = new ArrayMaths(xx);
/* 12593 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12594 */       Complex[] cca = new Complex[am.length];
/* 12595 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12596 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12597 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12598 */       am.type = 14;
/* 12599 */       break;
/* 12600 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 12601 */       am2 = new ArrayMaths(xx);
/* 12602 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 12603 */       Phasor[] ppa = new Phasor[am.length];
/* 12604 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 12605 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 12606 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 12607 */       am.type = 15;
/* 12608 */       break;
/* 12609 */     case 18:  String[] ss1 = getArray_as_String();
/* 12610 */       am2 = new ArrayMaths(xx);
/* 12611 */       String[] ss2 = am2.getArray_as_String();
/* 12612 */       String[] ssa = new String[am.length];
/* 12613 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12614 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12615 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12616 */       am.type = 18;
/* 12617 */       break;
/* 12618 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12620 */     int[] maxminIndices = new int[2];
/* 12621 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12622 */     am.maxIndex = maxminIndices[0];
/* 12623 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12625 */     Conv.restoreMessages();
/* 12626 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Float[] xx)
/*       */   {
/* 12631 */     float[] dd = new float[xx.length];
/* 12632 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].floatValue();
/* 12633 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(long[] xx)
/*       */   {
/* 12638 */     int xlength = xx.length;
/* 12639 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12640 */     ArrayMaths am = new ArrayMaths();
/* 12641 */     am.array = new ArrayList();
/* 12642 */     am.length = (xx.length + this.length);
/* 12643 */     ArrayMaths am2 = null;
/* 12644 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/* 12648 */       double[] yy = getArray_as_double();
/* 12649 */       double[] zz = new double[am.length];
/* 12650 */       for (int i = 0; i < this.length; i++) zz[i] = yy[i];
/* 12651 */       for (int i = 0; i < xlength; i++) zz[(i + this.length)] = xx[i];
/* 12652 */       for (int i = 0; i < am.length; i++) am.array.add(new Double(zz[i]));
/* 12653 */       am.type = 1;
/* 12654 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/* 12664 */       long[] ll = getArray_as_long();
/* 12665 */       long[] mm = new long[am.length];
/* 12666 */       for (int i = 0; i < this.length; i++) mm[i] = ll[i];
/* 12667 */       for (int i = 0; i < xlength; i++) mm[(i + this.length)] = xx[i];
/* 12668 */       for (int i = 0; i < am.length; i++) am.array.add(new Long(mm[i]));
/* 12669 */       am.type = 3;
/* 12670 */       break;
/* 12671 */     case 12:  BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 12672 */       am2 = new ArrayMaths(xx);
/* 12673 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 12674 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 12675 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 12676 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 12677 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 12678 */       bd1 = null;
/* 12679 */       bd2 = null;
/* 12680 */       bda = null;
/* 12681 */       am.type = 12;
/* 12682 */       break;
/* 12683 */     case 13:  BigInteger[] bi1 = getArray_as_BigInteger();
/* 12684 */       am2 = new ArrayMaths(xx);
/* 12685 */       BigInteger[] bi2 = am2.getArray_as_BigInteger();
/* 12686 */       BigInteger[] bia = new BigInteger[am.length];
/* 12687 */       for (int i = 0; i < this.length; i++) bia[i] = bi1[i];
/* 12688 */       for (int i = 0; i < xlength; i++) bia[(i + this.length)] = bi2[i];
/* 12689 */       for (int i = 0; i < am.length; i++) am.array.add(bia[i]);
/* 12690 */       bi1 = null;
/* 12691 */       bi2 = null;
/* 12692 */       bia = null;
/* 12693 */       am.type = 13;
/* 12694 */       break;
/* 12695 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 12696 */       am2 = new ArrayMaths(xx);
/* 12697 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12698 */       Complex[] cca = new Complex[am.length];
/* 12699 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12700 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12701 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12702 */       am.type = 14;
/* 12703 */       break;
/* 12704 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 12705 */       am2 = new ArrayMaths(xx);
/* 12706 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 12707 */       Phasor[] ppa = new Phasor[am.length];
/* 12708 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 12709 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 12710 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 12711 */       am.type = 15;
/* 12712 */       break;
/* 12713 */     case 18:  String[] ss1 = getArray_as_String();
/* 12714 */       am2 = new ArrayMaths(xx);
/* 12715 */       String[] ss2 = am2.getArray_as_String();
/* 12716 */       String[] ssa = new String[am.length];
/* 12717 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12718 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12719 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12720 */       am.type = 18;
/* 12721 */       break;
/* 12722 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12724 */     int[] maxminIndices = new int[2];
/* 12725 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12726 */     am.maxIndex = maxminIndices[0];
/* 12727 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12729 */     Conv.restoreMessages();
/* 12730 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Long[] xx)
/*       */   {
/* 12735 */     long[] dd = new long[xx.length];
/* 12736 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].longValue();
/* 12737 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(int[] xx)
/*       */   {
/* 12742 */     long[] dd = new long[xx.length];
/* 12743 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i];
/* 12744 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Integer[] xx)
/*       */   {
/* 12749 */     int[] dd = new int[xx.length];
/* 12750 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].intValue();
/* 12751 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(short[] xx)
/*       */   {
/* 12756 */     long[] dd = new long[xx.length];
/* 12757 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i];
/* 12758 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Short[] xx)
/*       */   {
/* 12763 */     short[] dd = new short[xx.length];
/* 12764 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].shortValue();
/* 12765 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(byte[] xx)
/*       */   {
/* 12770 */     long[] dd = new long[xx.length];
/* 12771 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i];
/* 12772 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Byte[] xx)
/*       */   {
/* 12777 */     byte[] dd = new byte[xx.length];
/* 12778 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].byteValue();
/* 12779 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(BigDecimal[] xx)
/*       */   {
/* 12784 */     int xlength = xx.length;
/* 12785 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12786 */     ArrayMaths am = new ArrayMaths();
/* 12787 */     am.array = new ArrayList();
/* 12788 */     am.length = (xx.length + this.length);
/* 12789 */     ArrayMaths am2 = null;
/* 12790 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/* 12806 */       BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 12807 */       am2 = new ArrayMaths(xx);
/* 12808 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 12809 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 12810 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 12811 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 12812 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 12813 */       bd1 = null;
/* 12814 */       bd2 = null;
/* 12815 */       bda = null;
/* 12816 */       am.type = 12;
/* 12817 */       break;
/* 12818 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 12819 */       am2 = new ArrayMaths(xx);
/* 12820 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12821 */       Complex[] cca = new Complex[am.length];
/* 12822 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12823 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12824 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12825 */       am.type = 14;
/* 12826 */       break;
/* 12827 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 12828 */       am2 = new ArrayMaths(xx);
/* 12829 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 12830 */       Phasor[] ppa = new Phasor[am.length];
/* 12831 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 12832 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 12833 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 12834 */       am.type = 15;
/* 12835 */       break;
/* 12836 */     case 18:  String[] ss1 = getArray_as_String();
/* 12837 */       am2 = new ArrayMaths(xx);
/* 12838 */       String[] ss2 = am2.getArray_as_String();
/* 12839 */       String[] ssa = new String[am.length];
/* 12840 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12841 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12842 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12843 */       am.type = 18;
/* 12844 */       break;
/* 12845 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12847 */     int[] maxminIndices = new int[2];
/* 12848 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12849 */     am.maxIndex = maxminIndices[0];
/* 12850 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12852 */     Conv.restoreMessages();
/* 12853 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(BigInteger[] xx) {
/* 12857 */     int xlength = xx.length;
/* 12858 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12859 */     ArrayMaths am = new ArrayMaths();
/* 12860 */     am.array = new ArrayList();
/* 12861 */     am.length = (xx.length + this.length);
/* 12862 */     ArrayMaths am2 = null;
/* 12863 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 12: 
/* 12868 */       BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 12869 */       am2 = new ArrayMaths(xx);
/* 12870 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 12871 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 12872 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 12873 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 12874 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 12875 */       bd1 = null;
/* 12876 */       bd2 = null;
/* 12877 */       bda = null;
/* 12878 */       am.type = 12;
/* 12879 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 13: 
/*       */     case 16: 
/*       */     case 17: 
/* 12890 */       BigInteger[] bi1 = getArray_as_BigInteger();
/* 12891 */       am2 = new ArrayMaths(xx);
/* 12892 */       BigInteger[] bi2 = am2.getArray_as_BigInteger();
/* 12893 */       BigInteger[] bia = new BigInteger[am.length];
/* 12894 */       for (int i = 0; i < this.length; i++) bia[i] = bi1[i];
/* 12895 */       for (int i = 0; i < xlength; i++) bia[(i + this.length)] = bi2[i];
/* 12896 */       for (int i = 0; i < am.length; i++) am.array.add(bia[i]);
/* 12897 */       bi1 = null;
/* 12898 */       bi2 = null;
/* 12899 */       bia = null;
/* 12900 */       am.type = 13;
/* 12901 */       break;
/* 12902 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 12903 */       am2 = new ArrayMaths(xx);
/* 12904 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12905 */       Complex[] cca = new Complex[am.length];
/* 12906 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12907 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12908 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12909 */       am.type = 14;
/* 12910 */       break;
/* 12911 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 12912 */       am2 = new ArrayMaths(xx);
/* 12913 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 12914 */       Phasor[] ppa = new Phasor[am.length];
/* 12915 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 12916 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 12917 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 12918 */       am.type = 15;
/* 12919 */       break;
/* 12920 */     case 18:  String[] ss1 = getArray_as_String();
/* 12921 */       am2 = new ArrayMaths(xx);
/* 12922 */       String[] ss2 = am2.getArray_as_String();
/* 12923 */       String[] ssa = new String[am.length];
/* 12924 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12925 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12926 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12927 */       am.type = 18;
/* 12928 */       break;
/* 12929 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12931 */     int[] maxminIndices = new int[2];
/* 12932 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 12933 */     am.maxIndex = maxminIndices[0];
/* 12934 */     am.minIndex = maxminIndices[1];
/*       */     
/* 12936 */     Conv.restoreMessages();
/* 12937 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Complex[] xx)
/*       */   {
/* 12942 */     int xlength = xx.length;
/* 12943 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12944 */     ArrayMaths am = new ArrayMaths();
/* 12945 */     am.array = new ArrayList();
/* 12946 */     am.length = (xx.length + this.length);
/* 12947 */     ArrayMaths am2 = null;
/* 12948 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/* 12966 */       Complex[] cc1 = getArray_as_Complex();
/* 12967 */       am2 = new ArrayMaths(xx);
/* 12968 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 12969 */       Complex[] cca = new Complex[am.length];
/* 12970 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 12971 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 12972 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 12973 */       am.type = 14;
/* 12974 */       break;
/* 12975 */     case 18:  String[] ss1 = getArray_as_String();
/* 12976 */       am2 = new ArrayMaths(xx);
/* 12977 */       String[] ss2 = am2.getArray_as_String();
/* 12978 */       String[] ssa = new String[am.length];
/* 12979 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 12980 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 12981 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 12982 */       am.type = 18;
/* 12983 */       break;
/* 12984 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 12986 */     Conv.restoreMessages();
/* 12987 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Phasor[] xx)
/*       */   {
/* 12992 */     int xlength = xx.length;
/* 12993 */     if (this.suppressMessages) Conv.suppressMessages();
/* 12994 */     ArrayMaths am = new ArrayMaths();
/* 12995 */     am.array = new ArrayList();
/* 12996 */     am.length = (xx.length + this.length);
/* 12997 */     ArrayMaths am2 = null;
/* 12998 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/* 13016 */       Phasor[] pp1 = getArray_as_Phasor();
/* 13017 */       am2 = new ArrayMaths(xx);
/* 13018 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 13019 */       Phasor[] ppa = new Phasor[am.length];
/* 13020 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 13021 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 13022 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 13023 */       am.type = 15;
/* 13024 */       break;
/* 13025 */     case 18:  String[] ss1 = getArray_as_String();
/* 13026 */       am2 = new ArrayMaths(xx);
/* 13027 */       String[] ss2 = am2.getArray_as_String();
/* 13028 */       String[] ssa = new String[am.length];
/* 13029 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 13030 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 13031 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 13032 */       am.type = 18;
/* 13033 */       break;
/* 13034 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 13036 */     Conv.restoreMessages();
/* 13037 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(String[] xx)
/*       */   {
/* 13042 */     int xlength = xx.length;
/* 13043 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13044 */     ArrayMaths am = new ArrayMaths();
/* 13045 */     am.array = new ArrayList();
/* 13046 */     am.length = (xx.length + this.length);
/* 13047 */     ArrayMaths am2 = null;
/* 13048 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 12: 
/*       */     case 13: 
/*       */     case 14: 
/*       */     case 15: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 13067 */       String[] ss1 = getArray_as_String();
/* 13068 */       am2 = new ArrayMaths(xx);
/* 13069 */       String[] ss2 = am2.getArray_as_String();
/* 13070 */       String[] ssa = new String[am.length];
/* 13071 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 13072 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 13073 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 13074 */       am.type = 18;
/* 13075 */       break;
/* 13076 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 13078 */     Conv.restoreMessages();
/* 13079 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(char[] xx)
/*       */   {
/* 13084 */     int xlength = xx.length;
/* 13085 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13086 */     ArrayMaths am = new ArrayMaths();
/* 13087 */     am.array = new ArrayList();
/* 13088 */     am.length = (xx.length + this.length);
/* 13089 */     ArrayMaths am2 = null;
/* 13090 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/* 13094 */       double[] yy = getArray_as_double();
/* 13095 */       double[] zz = new double[am.length];
/* 13096 */       for (int i = 0; i < this.length; i++) zz[i] = yy[i];
/* 13097 */       for (int i = 0; i < xlength; i++) zz[(i + this.length)] = xx[i];
/* 13098 */       for (int i = 0; i < am.length; i++) am.array.add(new Double(zz[i]));
/* 13099 */       am.type = 1;
/* 13100 */       break;
/*       */     case 4: case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/* 13108 */       long[] ll = getArray_as_long();
/* 13109 */       long[] mm = new long[am.length];
/* 13110 */       for (int i = 0; i < this.length; i++) mm[i] = ll[i];
/* 13111 */       for (int i = 0; i < xlength; i++) mm[(i + this.length)] = xx[i];
/* 13112 */       for (int i = 0; i < am.length; i++) am.array.add(new Long(mm[i]));
/* 13113 */       am.type = 3;
/* 13114 */       break;
/* 13115 */     case 12:  BigDecimal[] bd1 = getArray_as_BigDecimal();
/* 13116 */       am2 = new ArrayMaths(xx);
/* 13117 */       BigDecimal[] bd2 = am2.getArray_as_BigDecimal();
/* 13118 */       BigDecimal[] bda = new BigDecimal[am.length];
/* 13119 */       for (int i = 0; i < this.length; i++) bda[i] = bd1[i];
/* 13120 */       for (int i = 0; i < xlength; i++) bda[(i + this.length)] = bd2[i];
/* 13121 */       for (int i = 0; i < am.length; i++) am.array.add(bda[i]);
/* 13122 */       bd1 = null;
/* 13123 */       bd2 = null;
/* 13124 */       bda = null;
/* 13125 */       am.type = 12;
/* 13126 */       break;
/* 13127 */     case 13:  BigInteger[] bi1 = getArray_as_BigInteger();
/* 13128 */       am2 = new ArrayMaths(xx);
/* 13129 */       BigInteger[] bi2 = am2.getArray_as_BigInteger();
/* 13130 */       BigInteger[] bia = new BigInteger[am.length];
/* 13131 */       for (int i = 0; i < this.length; i++) bia[i] = bi1[i];
/* 13132 */       for (int i = 0; i < xlength; i++) bia[(i + this.length)] = bi2[i];
/* 13133 */       for (int i = 0; i < am.length; i++) am.array.add(bia[i]);
/* 13134 */       bi1 = null;
/* 13135 */       bi2 = null;
/* 13136 */       bia = null;
/* 13137 */       am.type = 13;
/* 13138 */       break;
/* 13139 */     case 14:  Complex[] cc1 = getArray_as_Complex();
/* 13140 */       am2 = new ArrayMaths(xx);
/* 13141 */       Complex[] cc2 = am2.getArray_as_Complex();
/* 13142 */       Complex[] cca = new Complex[am.length];
/* 13143 */       for (int i = 0; i < this.length; i++) cca[i] = cc1[i];
/* 13144 */       for (int i = 0; i < xlength; i++) cca[(i + this.length)] = cc2[i];
/* 13145 */       for (int i = 0; i < am.length; i++) am.array.add(cca[i]);
/* 13146 */       am.type = 14;
/* 13147 */       break;
/* 13148 */     case 15:  Phasor[] pp1 = getArray_as_Phasor();
/* 13149 */       am2 = new ArrayMaths(xx);
/* 13150 */       Phasor[] pp2 = am2.getArray_as_Phasor();
/* 13151 */       Phasor[] ppa = new Phasor[am.length];
/* 13152 */       for (int i = 0; i < this.length; i++) ppa[i] = pp1[i];
/* 13153 */       for (int i = 0; i < xlength; i++) ppa[(i + this.length)] = pp2[i];
/* 13154 */       for (int i = 0; i < am.length; i++) am.array.add(ppa[i]);
/* 13155 */       am.type = 15;
/* 13156 */       break;
/*       */     case 16: case 17: 
/* 13158 */       char[] ch = getArray_as_char();
/* 13159 */       char[] dh = new char[am.length];
/* 13160 */       for (int i = 0; i < this.length; i++) dh[i] = ch[i];
/* 13161 */       for (int i = 0; i < xlength; i++) dh[(i + this.length)] = xx[i];
/* 13162 */       for (int i = 0; i < am.length; i++) am.array.add(new Character(dh[i]));
/* 13163 */       am.type = 1;
/* 13164 */       break;
/* 13165 */     case 18:  String[] ss1 = getArray_as_String();
/* 13166 */       am2 = new ArrayMaths(xx);
/* 13167 */       String[] ss2 = am2.getArray_as_String();
/* 13168 */       String[] ssa = new String[am.length];
/* 13169 */       for (int i = 0; i < this.length; i++) ssa[i] = ss1[i];
/* 13170 */       for (int i = 0; i < xlength; i++) ssa[(i + this.length)] = ss2[i];
/* 13171 */       for (int i = 0; i < am.length; i++) am.array.add(ssa[i]);
/* 13172 */       am.type = 18;
/* 13173 */       break;
/* 13174 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 13176 */     int[] maxminIndices = new int[2];
/* 13177 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 13178 */     am.maxIndex = maxminIndices[0];
/* 13179 */     am.minIndex = maxminIndices[1];
/*       */     
/* 13181 */     Conv.restoreMessages();
/* 13182 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Character[] xx)
/*       */   {
/* 13187 */     char[] dd = new char[xx.length];
/* 13188 */     for (int i = 0; i < xx.length; i++) dd[i] = xx[i].charValue();
/* 13189 */     return concatenate(dd);
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(ArrayMaths xx)
/*       */   {
/* 13194 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13195 */     int type = xx.type;
/* 13196 */     ArrayMaths am = new ArrayMaths();
/* 13197 */     switch (xx.type) {
/*       */     case 0: case 1: 
/* 13199 */       double[] dd = xx.getArray_as_double();
/* 13200 */       am = concatenate(dd);
/* 13201 */       break;
/*       */     case 2: case 3: 
/* 13203 */       float[] ff = xx.getArray_as_float();
/* 13204 */       am = concatenate(ff);
/* 13205 */       break;
/*       */     case 4: case 5: 
/* 13207 */       long[] ll = xx.getArray_as_long();
/* 13208 */       am = concatenate(ll);
/* 13209 */       break;
/*       */     case 6: case 7: 
/* 13211 */       int[] ii = xx.getArray_as_int();
/* 13212 */       am = concatenate(ii);
/* 13213 */       break;
/*       */     case 8: case 9: 
/* 13215 */       short[] ss = xx.getArray_as_short();
/* 13216 */       am = concatenate(ss);
/* 13217 */       break;
/*       */     case 10: case 11: 
/* 13219 */       byte[] bb = xx.getArray_as_byte();
/* 13220 */       am = concatenate(bb);
/* 13221 */       break;
/* 13222 */     case 12:  BigDecimal[] bd = xx.getArray_as_BigDecimal();
/* 13223 */       am = concatenate(bd);
/* 13224 */       break;
/* 13225 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 13226 */       am = concatenate(bi);
/* 13227 */       break;
/* 13228 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 13229 */       am = concatenate(cc);
/* 13230 */       break;
/* 13231 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 13232 */       am = concatenate(pp);
/* 13233 */       break;
/*       */     case 16: case 17: 
/* 13235 */       char[] ct = getArray_as_char();
/* 13236 */       am = concatenate(ct);
/* 13237 */       break;
/* 13238 */     case 18:  String[] st = getArray_as_String();
/* 13239 */       am = concatenate(st);
/* 13240 */       break;
/* 13241 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 13243 */     int[] maxminIndices = new int[2];
/* 13244 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 13245 */     am.maxIndex = maxminIndices[0];
/* 13246 */     am.minIndex = maxminIndices[1];
/*       */     
/* 13248 */     Conv.restoreMessages();
/* 13249 */     return am;
/*       */   }
/*       */   
/*       */   public ArrayMaths concatenate(Stat xx)
/*       */   {
/* 13254 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13255 */     int type = xx.type;
/* 13256 */     ArrayMaths am = new ArrayMaths();
/* 13257 */     switch (xx.type) {
/*       */     case 0: case 1: 
/* 13259 */       double[] dd = xx.getArray_as_double();
/* 13260 */       am = concatenate(dd);
/* 13261 */       break;
/*       */     case 2: case 3: 
/* 13263 */       float[] ff = xx.getArray_as_float();
/* 13264 */       am = concatenate(ff);
/* 13265 */       break;
/*       */     case 4: case 5: 
/* 13267 */       long[] ll = xx.getArray_as_long();
/* 13268 */       am = concatenate(ll);
/* 13269 */       break;
/*       */     case 6: case 7: 
/* 13271 */       int[] ii = xx.getArray_as_int();
/* 13272 */       am = concatenate(ii);
/* 13273 */       break;
/*       */     case 8: case 9: 
/* 13275 */       short[] ss = xx.getArray_as_short();
/* 13276 */       am = concatenate(ss);
/* 13277 */       break;
/*       */     case 10: case 11: 
/* 13279 */       byte[] bb = xx.getArray_as_byte();
/* 13280 */       am = concatenate(bb);
/* 13281 */       break;
/* 13282 */     case 12:  BigDecimal[] bd = xx.getArray_as_BigDecimal();
/* 13283 */       am = concatenate(bd);
/* 13284 */       break;
/* 13285 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 13286 */       am = concatenate(bi);
/* 13287 */       break;
/* 13288 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 13289 */       am = concatenate(cc);
/* 13290 */       break;
/* 13291 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 13292 */       am = concatenate(pp);
/* 13293 */       break;
/*       */     case 16: case 17: 
/* 13295 */       char[] ct = getArray_as_char();
/* 13296 */       am = concatenate(ct);
/* 13297 */       break;
/* 13298 */     case 18:  String[] st = getArray_as_String();
/* 13299 */       am = concatenate(st);
/* 13300 */       break;
/* 13301 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/* 13303 */     int[] maxminIndices = new int[2];
/* 13304 */     findMinMax(am.getArray_as_Object(), am.minmax, maxminIndices, am.typeName, am.type);
/* 13305 */     am.maxIndex = maxminIndices[0];
/* 13306 */     am.minIndex = maxminIndices[1];
/*       */     
/* 13308 */     Conv.restoreMessages();
/* 13309 */     return am;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int indexOf(double value)
/*       */   {
/* 13316 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13317 */     int index = -1;
/* 13318 */     if ((this.type == 0) || (this.type == 1)) {
/* 13319 */       double[] arrayc = getArray_as_double();
/* 13320 */       boolean test = true;
/* 13321 */       int counter = 0;
/* 13322 */       while (test) {
/* 13323 */         if (arrayc[counter] == value) {
/* 13324 */           index = counter;
/* 13325 */           test = false;
/*       */         }
/*       */         else {
/* 13328 */           counter++;
/* 13329 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13334 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
/*       */     }
/* 13336 */     Conv.restoreMessages();
/* 13337 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Double value)
/*       */   {
/* 13343 */     double val = value.doubleValue();
/* 13344 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(float value)
/*       */   {
/* 13350 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13351 */     int index = -1;
/* 13352 */     if ((this.type == 2) || (this.type == 3)) {
/* 13353 */       float[] arrayc = getArray_as_float();
/* 13354 */       boolean test = true;
/* 13355 */       int counter = 0;
/* 13356 */       while (test) {
/* 13357 */         if (arrayc[counter] == value) {
/* 13358 */           index = counter;
/* 13359 */           test = false;
/*       */         }
/*       */         else {
/* 13362 */           counter++;
/* 13363 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13368 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
/*       */     }
/* 13370 */     Conv.restoreMessages();
/* 13371 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Float value)
/*       */   {
/* 13377 */     float val = value.floatValue();
/* 13378 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int indexOf(long value)
/*       */   {
/* 13385 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13386 */     int index = -1;
/* 13387 */     if ((this.type == 4) || (this.type == 5)) {
/* 13388 */       long[] arrayc = getArray_as_long();
/* 13389 */       boolean test = true;
/* 13390 */       int counter = 0;
/* 13391 */       while (test) {
/* 13392 */         if (arrayc[counter] == value) {
/* 13393 */           index = counter;
/* 13394 */           test = false;
/*       */         }
/*       */         else {
/* 13397 */           counter++;
/* 13398 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13403 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
/*       */     }
/* 13405 */     Conv.restoreMessages();
/* 13406 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Long value)
/*       */   {
/* 13412 */     long val = value.longValue();
/* 13413 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(int value)
/*       */   {
/* 13419 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13420 */     int index = -1;
/* 13421 */     if ((this.type == 6) || (this.type == 7)) {
/* 13422 */       int[] arrayc = getArray_as_int();
/* 13423 */       boolean test = true;
/* 13424 */       int counter = 0;
/* 13425 */       while (test) {
/* 13426 */         if (arrayc[counter] == value) {
/* 13427 */           index = counter;
/* 13428 */           test = false;
/*       */         }
/*       */         else {
/* 13431 */           counter++;
/* 13432 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13437 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
/*       */     }
/* 13439 */     Conv.restoreMessages();
/* 13440 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Integer value)
/*       */   {
/* 13446 */     int val = value.intValue();
/* 13447 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(short value)
/*       */   {
/* 13453 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13454 */     int index = -1;
/* 13455 */     if ((this.type == 8) || (this.type == 9)) {
/* 13456 */       short[] arrayc = getArray_as_short();
/* 13457 */       boolean test = true;
/* 13458 */       int counter = 0;
/* 13459 */       while (test) {
/* 13460 */         if (arrayc[counter] == value) {
/* 13461 */           index = counter;
/* 13462 */           test = false;
/*       */         }
/*       */         else {
/* 13465 */           counter++;
/* 13466 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13471 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
/*       */     }
/* 13473 */     Conv.restoreMessages();
/* 13474 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Short value)
/*       */   {
/* 13480 */     short val = value.shortValue();
/* 13481 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(byte value)
/*       */   {
/* 13487 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13488 */     int index = -1;
/* 13489 */     if ((this.type == 10) || (this.type == 11)) {
/* 13490 */       byte[] arrayc = getArray_as_byte();
/* 13491 */       boolean test = true;
/* 13492 */       int counter = 0;
/* 13493 */       while (test) {
/* 13494 */         if (arrayc[counter] == value) {
/* 13495 */           index = counter;
/* 13496 */           test = false;
/*       */         }
/*       */         else {
/* 13499 */           counter++;
/* 13500 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13505 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
/*       */     }
/* 13507 */     Conv.restoreMessages();
/* 13508 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Byte value)
/*       */   {
/* 13514 */     byte val = value.byteValue();
/* 13515 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int indexOf(char value)
/*       */   {
/* 13522 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13523 */     int index = -1;
/* 13524 */     if ((this.type == 16) || (this.type == 17)) {
/* 13525 */       char[] arrayc = getArray_as_char();
/* 13526 */       boolean test = true;
/* 13527 */       int counter = 0;
/* 13528 */       while (test) {
/* 13529 */         if (arrayc[counter] == value) {
/* 13530 */           index = counter;
/* 13531 */           test = false;
/*       */         }
/*       */         else {
/* 13534 */           counter++;
/* 13535 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13540 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
/*       */     }
/* 13542 */     Conv.restoreMessages();
/* 13543 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Character value)
/*       */   {
/* 13549 */     char val = value.charValue();
/* 13550 */     return indexOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(String value)
/*       */   {
/* 13556 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13557 */     int index = -1;
/* 13558 */     if (this.type == 18) {
/* 13559 */       String[] arrayc = getArray_as_String();
/* 13560 */       boolean test = true;
/* 13561 */       int counter = 0;
/* 13562 */       while (test) {
/* 13563 */         if (arrayc[counter].equals(value)) {
/* 13564 */           index = counter;
/* 13565 */           test = false;
/*       */         }
/*       */         else {
/* 13568 */           counter++;
/* 13569 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13574 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare String with " + this.typeName[this.type]);
/*       */     }
/* 13576 */     Conv.restoreMessages();
/* 13577 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Complex value)
/*       */   {
/* 13583 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13584 */     int index = -1;
/* 13585 */     if (this.type == 14) {
/* 13586 */       Complex[] arrayc = getArray_as_Complex();
/* 13587 */       boolean test = true;
/* 13588 */       int counter = 0;
/* 13589 */       while (test) {
/* 13590 */         if (arrayc[counter].equals(value)) {
/* 13591 */           index = counter;
/* 13592 */           test = false;
/*       */         }
/*       */         else {
/* 13595 */           counter++;
/* 13596 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13601 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Complex with " + this.typeName[this.type]);
/*       */     }
/* 13603 */     Conv.restoreMessages();
/* 13604 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(Phasor value)
/*       */   {
/* 13610 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13611 */     int index = -1;
/* 13612 */     if (this.type == 15) {
/* 13613 */       Phasor[] arrayc = getArray_as_Phasor();
/* 13614 */       boolean test = true;
/* 13615 */       int counter = 0;
/* 13616 */       while (test) {
/* 13617 */         if (arrayc[counter].equals(value)) {
/* 13618 */           index = counter;
/* 13619 */           test = false;
/*       */         }
/*       */         else {
/* 13622 */           counter++;
/* 13623 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13628 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Phasor with " + this.typeName[this.type]);
/*       */     }
/* 13630 */     Conv.restoreMessages();
/* 13631 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(BigDecimal value)
/*       */   {
/* 13637 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13638 */     int index = -1;
/* 13639 */     if (this.type == 12) {
/* 13640 */       BigDecimal[] arrayc = getArray_as_BigDecimal();
/* 13641 */       boolean test = true;
/* 13642 */       int counter = 0;
/* 13643 */       while (test) {
/* 13644 */         if (arrayc[counter].compareTo(value) == 0) {
/* 13645 */           index = counter;
/* 13646 */           test = false;
/*       */         }
/*       */         else {
/* 13649 */           counter++;
/* 13650 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13655 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
/*       */     }
/* 13657 */     Conv.restoreMessages();
/* 13658 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */   public int indexOf(BigInteger value)
/*       */   {
/* 13664 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13665 */     int index = -1;
/* 13666 */     if (this.type == 13) {
/* 13667 */       BigInteger[] arrayc = getArray_as_BigInteger();
/* 13668 */       boolean test = true;
/* 13669 */       int counter = 0;
/* 13670 */       while (test) {
/* 13671 */         if (arrayc[counter].compareTo(value) == 0) {
/* 13672 */           index = counter;
/* 13673 */           test = false;
/*       */         }
/*       */         else {
/* 13676 */           counter++;
/* 13677 */           if (counter >= arrayc.length) test = false;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13682 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
/*       */     }
/* 13684 */     Conv.restoreMessages();
/* 13685 */     return index;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int[] indicesOf(double value)
/*       */   {
/* 13692 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13693 */     int[] indices = null;
/* 13694 */     int numberOfIndices = 0;
/* 13695 */     if ((this.type == 0) || (this.type == 1)) {
/* 13696 */       double[] arrayc = getArray_as_double();
/* 13697 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13698 */       for (int i = 0; i < this.length; i++) {
/* 13699 */         if (arrayc[i] == value) {
/* 13700 */           numberOfIndices++;
/* 13701 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13704 */       if (numberOfIndices != 0) {
/* 13705 */         indices = new int[numberOfIndices];
/* 13706 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13707 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13712 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
/*       */     }
/* 13714 */     Conv.restoreMessages();
/* 13715 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Double value)
/*       */   {
/* 13721 */     double val = value.doubleValue();
/* 13722 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(float value)
/*       */   {
/* 13728 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13729 */     int[] indices = null;
/* 13730 */     int numberOfIndices = 0;
/* 13731 */     if ((this.type == 2) || (this.type == 3)) {
/* 13732 */       float[] arrayc = getArray_as_float();
/* 13733 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13734 */       for (int i = 0; i < this.length; i++) {
/* 13735 */         if (arrayc[i] == value) {
/* 13736 */           numberOfIndices++;
/* 13737 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13740 */       if (numberOfIndices != 0) {
/* 13741 */         indices = new int[numberOfIndices];
/* 13742 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13743 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13748 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
/*       */     }
/* 13750 */     Conv.restoreMessages();
/* 13751 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Float value)
/*       */   {
/* 13757 */     float val = value.floatValue();
/* 13758 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int[] indicesOf(long value)
/*       */   {
/* 13765 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13766 */     int[] indices = null;
/* 13767 */     int numberOfIndices = 0;
/* 13768 */     if ((this.type == 4) || (this.type == 5)) {
/* 13769 */       long[] arrayc = getArray_as_long();
/* 13770 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13771 */       for (int i = 0; i < this.length; i++) {
/* 13772 */         if (arrayc[i] == value) {
/* 13773 */           numberOfIndices++;
/* 13774 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13777 */       if (numberOfIndices != 0) {
/* 13778 */         indices = new int[numberOfIndices];
/* 13779 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13780 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13785 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
/*       */     }
/* 13787 */     Conv.restoreMessages();
/* 13788 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Long value)
/*       */   {
/* 13794 */     long val = value.longValue();
/* 13795 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(int value)
/*       */   {
/* 13801 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13802 */     int[] indices = null;
/* 13803 */     int numberOfIndices = 0;
/* 13804 */     if ((this.type == 6) || (this.type == 7)) {
/* 13805 */       int[] arrayc = getArray_as_int();
/* 13806 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13807 */       for (int i = 0; i < this.length; i++) {
/* 13808 */         if (arrayc[i] == value) {
/* 13809 */           numberOfIndices++;
/* 13810 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13813 */       if (numberOfIndices != 0) {
/* 13814 */         indices = new int[numberOfIndices];
/* 13815 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13816 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13821 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
/*       */     }
/* 13823 */     Conv.restoreMessages();
/* 13824 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Integer value)
/*       */   {
/* 13830 */     int val = value.intValue();
/* 13831 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(short value)
/*       */   {
/* 13837 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13838 */     int[] indices = null;
/* 13839 */     int numberOfIndices = 0;
/* 13840 */     if ((this.type == 8) || (this.type == 9)) {
/* 13841 */       short[] arrayc = getArray_as_short();
/* 13842 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13843 */       for (int i = 0; i < this.length; i++) {
/* 13844 */         if (arrayc[i] == value) {
/* 13845 */           numberOfIndices++;
/* 13846 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13849 */       if (numberOfIndices != 0) {
/* 13850 */         indices = new int[numberOfIndices];
/* 13851 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13852 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13857 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
/*       */     }
/* 13859 */     Conv.restoreMessages();
/* 13860 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Short value)
/*       */   {
/* 13866 */     short val = value.shortValue();
/* 13867 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(byte value)
/*       */   {
/* 13873 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13874 */     int[] indices = null;
/* 13875 */     int numberOfIndices = 0;
/* 13876 */     if ((this.type == 10) || (this.type == 11)) {
/* 13877 */       byte[] arrayc = getArray_as_byte();
/* 13878 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13879 */       for (int i = 0; i < this.length; i++) {
/* 13880 */         if (arrayc[i] == value) {
/* 13881 */           numberOfIndices++;
/* 13882 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13885 */       if (numberOfIndices != 0) {
/* 13886 */         indices = new int[numberOfIndices];
/* 13887 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13888 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13893 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
/*       */     }
/* 13895 */     Conv.restoreMessages();
/* 13896 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Byte value)
/*       */   {
/* 13902 */     byte val = value.byteValue();
/* 13903 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int[] indicesOf(char value)
/*       */   {
/* 13910 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13911 */     int[] indices = null;
/* 13912 */     int numberOfIndices = 0;
/* 13913 */     if ((this.type == 16) || (this.type == 17)) {
/* 13914 */       char[] arrayc = getArray_as_char();
/* 13915 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13916 */       for (int i = 0; i < this.length; i++) {
/* 13917 */         if (arrayc[i] == value) {
/* 13918 */           numberOfIndices++;
/* 13919 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13922 */       if (numberOfIndices != 0) {
/* 13923 */         indices = new int[numberOfIndices];
/* 13924 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13925 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13930 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
/*       */     }
/* 13932 */     Conv.restoreMessages();
/* 13933 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Character value)
/*       */   {
/* 13939 */     char val = value.charValue();
/* 13940 */     return indicesOf(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(String value)
/*       */   {
/* 13946 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13947 */     int[] indices = null;
/* 13948 */     int numberOfIndices = 0;
/* 13949 */     if (this.type == 18) {
/* 13950 */       String[] arrayc = getArray_as_String();
/* 13951 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13952 */       for (int i = 0; i < this.length; i++) {
/* 13953 */         if (arrayc[i].equals(value)) {
/* 13954 */           numberOfIndices++;
/* 13955 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13958 */       if (numberOfIndices != 0) {
/* 13959 */         indices = new int[numberOfIndices];
/* 13960 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13961 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13966 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare String with " + this.typeName[this.type]);
/*       */     }
/* 13968 */     Conv.restoreMessages();
/* 13969 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Complex value)
/*       */   {
/* 13975 */     if (this.suppressMessages) Conv.suppressMessages();
/* 13976 */     int[] indices = null;
/* 13977 */     int numberOfIndices = 0;
/* 13978 */     if (this.type == 14) {
/* 13979 */       Complex[] arrayc = getArray_as_Complex();
/* 13980 */       ArrayList<Integer> arrayl = new ArrayList();
/* 13981 */       for (int i = 0; i < this.length; i++) {
/* 13982 */         if (arrayc[i].equals(value)) {
/* 13983 */           numberOfIndices++;
/* 13984 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 13987 */       if (numberOfIndices != 0) {
/* 13988 */         indices = new int[numberOfIndices];
/* 13989 */         for (int i = 0; i < numberOfIndices; i++) {
/* 13990 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 13995 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Complex with " + this.typeName[this.type]);
/*       */     }
/* 13997 */     Conv.restoreMessages();
/* 13998 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(Phasor value)
/*       */   {
/* 14004 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14005 */     int[] indices = null;
/* 14006 */     int numberOfIndices = 0;
/* 14007 */     if (this.type == 15) {
/* 14008 */       Phasor[] arrayc = getArray_as_Phasor();
/* 14009 */       ArrayList<Integer> arrayl = new ArrayList();
/* 14010 */       for (int i = 0; i < this.length; i++) {
/* 14011 */         if (arrayc[i].equals(value)) {
/* 14012 */           numberOfIndices++;
/* 14013 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 14016 */       if (numberOfIndices != 0) {
/* 14017 */         indices = new int[numberOfIndices];
/* 14018 */         for (int i = 0; i < numberOfIndices; i++) {
/* 14019 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14024 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Phasor with " + this.typeName[this.type]);
/*       */     }
/* 14026 */     Conv.restoreMessages();
/* 14027 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(BigDecimal value)
/*       */   {
/* 14033 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14034 */     int[] indices = null;
/* 14035 */     int numberOfIndices = 0;
/* 14036 */     if (this.type == 12) {
/* 14037 */       BigDecimal[] arrayc = getArray_as_BigDecimal();
/* 14038 */       ArrayList<Integer> arrayl = new ArrayList();
/* 14039 */       for (int i = 0; i < this.length; i++) {
/* 14040 */         if (arrayc[i].compareTo(value) == 0) {
/* 14041 */           numberOfIndices++;
/* 14042 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 14045 */       if (numberOfIndices != 0) {
/* 14046 */         indices = new int[numberOfIndices];
/* 14047 */         for (int i = 0; i < numberOfIndices; i++) {
/* 14048 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14053 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
/*       */     }
/* 14055 */     Conv.restoreMessages();
/* 14056 */     return indices;
/*       */   }
/*       */   
/*       */ 
/*       */   public int[] indicesOf(BigInteger value)
/*       */   {
/* 14062 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14063 */     int[] indices = null;
/* 14064 */     int numberOfIndices = 0;
/* 14065 */     if (this.type == 13) {
/* 14066 */       BigInteger[] arrayc = getArray_as_BigInteger();
/* 14067 */       ArrayList<Integer> arrayl = new ArrayList();
/* 14068 */       for (int i = 0; i < this.length; i++) {
/* 14069 */         if (arrayc[i].compareTo(value) == 0) {
/* 14070 */           numberOfIndices++;
/* 14071 */           arrayl.add(new Integer(i));
/*       */         }
/*       */       }
/* 14074 */       if (numberOfIndices != 0) {
/* 14075 */         indices = new int[numberOfIndices];
/* 14076 */         for (int i = 0; i < numberOfIndices; i++) {
/* 14077 */           indices[i] = ((Integer)arrayl.get(i)).intValue();
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14082 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
/*       */     }
/* 14084 */     Conv.restoreMessages();
/* 14085 */     return indices;
/*       */   }
/*       */   
/*       */   public int nearestIndex(double value)
/*       */   {
/* 14090 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14091 */     int index = 0;
/* 14092 */     if ((this.type == 0) || (this.type == 1)) {
/* 14093 */       double[] arrayc = getArray_as_double();
/* 14094 */       double diff = Math.abs(arrayc[0] - value);
/* 14095 */       double nearest = arrayc[0];
/* 14096 */       for (int i = 1; i < arrayc.length; i++) {
/* 14097 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14098 */           diff = Math.abs(arrayc[i] - value);
/* 14099 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14104 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
/*       */     }
/* 14106 */     Conv.restoreMessages();
/* 14107 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Double value)
/*       */   {
/* 14112 */     double val = value.doubleValue();
/* 14113 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */   public int nearestIndex(float value)
/*       */   {
/* 14118 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14119 */     int index = 0;
/* 14120 */     if ((this.type == 2) || (this.type == 3)) {
/* 14121 */       float[] arrayc = getArray_as_float();
/* 14122 */       float diff = Math.abs(arrayc[0] - value);
/* 14123 */       float nearest = arrayc[0];
/* 14124 */       for (int i = 1; i < arrayc.length; i++) {
/* 14125 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14126 */           diff = Math.abs(arrayc[i] - value);
/* 14127 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14132 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
/*       */     }
/* 14134 */     Conv.restoreMessages();
/* 14135 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Float value)
/*       */   {
/* 14140 */     float val = value.floatValue();
/* 14141 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int nearestIndex(long value)
/*       */   {
/* 14147 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14148 */     int index = 0;
/* 14149 */     if ((this.type == 4) || (this.type == 5)) {
/* 14150 */       long[] arrayc = getArray_as_long();
/* 14151 */       long diff = Math.abs(arrayc[0] - value);
/* 14152 */       long nearest = arrayc[0];
/* 14153 */       for (int i = 1; i < arrayc.length; i++) {
/* 14154 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14155 */           diff = Math.abs(arrayc[i] - value);
/* 14156 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14161 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
/*       */     }
/* 14163 */     Conv.restoreMessages();
/* 14164 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Long value)
/*       */   {
/* 14169 */     long val = value.longValue();
/* 14170 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */   public int nearestIndex(int value)
/*       */   {
/* 14175 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14176 */     int index = 0;
/* 14177 */     if ((this.type == 6) || (this.type == 7)) {
/* 14178 */       int[] arrayc = getArray_as_int();
/* 14179 */       int diff = Math.abs(arrayc[0] - value);
/* 14180 */       int nearest = arrayc[0];
/* 14181 */       for (int i = 1; i < arrayc.length; i++) {
/* 14182 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14183 */           diff = Math.abs(arrayc[i] - value);
/* 14184 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else
/*       */     {
/* 14190 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
/*       */     }
/* 14192 */     Conv.restoreMessages();
/* 14193 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Integer value)
/*       */   {
/* 14198 */     int val = value.intValue();
/* 14199 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */ 
/*       */   public int nearestIndex(short value)
/*       */   {
/* 14205 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14206 */     int index = 0;
/* 14207 */     if ((this.type == 8) || (this.type == 9)) {
/* 14208 */       short[] arrayc = getArray_as_short();
/* 14209 */       short diff = (short)Math.abs(arrayc[0] - value);
/* 14210 */       short nearest = arrayc[0];
/* 14211 */       for (int i = 1; i < arrayc.length; i++) {
/* 14212 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14213 */           diff = (short)Math.abs(arrayc[i] - value);
/* 14214 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else
/*       */     {
/* 14220 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
/*       */     }
/* 14222 */     Conv.restoreMessages();
/* 14223 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Short value)
/*       */   {
/* 14228 */     short val = value.shortValue();
/* 14229 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */   public int nearestIndex(byte value)
/*       */   {
/* 14234 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14235 */     int index = 0;
/* 14236 */     if ((this.type == 10) || (this.type == 11)) {
/* 14237 */       byte[] arrayc = getArray_as_byte();
/* 14238 */       byte diff = (byte)Math.abs(arrayc[0] - value);
/* 14239 */       byte nearest = arrayc[0];
/* 14240 */       for (int i = 1; i < arrayc.length; i++) {
/* 14241 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14242 */           diff = (byte)Math.abs(arrayc[i] - value);
/* 14243 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14248 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
/*       */     }
/* 14250 */     Conv.restoreMessages();
/* 14251 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Byte value)
/*       */   {
/* 14256 */     byte val = value.byteValue();
/* 14257 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */   public int nearestIndex(char value)
/*       */   {
/* 14262 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14263 */     int index = 0;
/* 14264 */     if ((this.type == 16) || (this.type == 17)) {
/* 14265 */       int[] arrayc = getArray_as_int();
/* 14266 */       int diff = Math.abs(arrayc[0] - value);
/* 14267 */       int nearest = arrayc[0];
/* 14268 */       for (int i = 1; i < arrayc.length; i++) {
/* 14269 */         if (Math.abs(arrayc[i] - value) < diff) {
/* 14270 */           diff = Math.abs(arrayc[i] - value);
/* 14271 */           index = i;
/*       */         }
/*       */       }
/*       */     }
/*       */     else {
/* 14276 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
/*       */     }
/* 14278 */     Conv.restoreMessages();
/* 14279 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(Character value)
/*       */   {
/* 14284 */     char val = value.charValue();
/* 14285 */     return nearestIndex(val);
/*       */   }
/*       */   
/*       */   public int nearestIndex(BigDecimal value)
/*       */   {
/* 14290 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14291 */     int index = 0;
/* 14292 */     if (this.type == 12) {
/* 14293 */       BigDecimal[] arrayc = getArray_as_BigDecimal();
/* 14294 */       BigDecimal diff = arrayc[0].subtract(value).abs();
/* 14295 */       BigDecimal nearest = arrayc[0];
/* 14296 */       for (int i = 1; i < arrayc.length; i++) {
/* 14297 */         if (arrayc[i].subtract(value).abs().compareTo(diff) == -1) {
/* 14298 */           diff = arrayc[i].subtract(value).abs();
/* 14299 */           index = i;
/*       */         }
/*       */       }
/* 14302 */       arrayc = null;
/* 14303 */       diff = null;
/* 14304 */       nearest = null;
/*       */     }
/*       */     else {
/* 14307 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
/*       */     }
/* 14309 */     Conv.restoreMessages();
/* 14310 */     return index;
/*       */   }
/*       */   
/*       */   public int nearestIndex(BigInteger value)
/*       */   {
/* 14315 */     if (this.suppressMessages) Conv.suppressMessages();
/* 14316 */     int index = 0;
/* 14317 */     if (this.type == 12) {
/* 14318 */       BigInteger[] arrayc = getArray_as_BigInteger();
/* 14319 */       BigInteger diff = arrayc[0].subtract(value).abs();
/* 14320 */       BigInteger nearest = arrayc[0];
/* 14321 */       for (int i = 1; i < arrayc.length; i++) {
/* 14322 */         if (arrayc[i].subtract(value).abs().compareTo(diff) == -1) {
/* 14323 */           diff = arrayc[i].subtract(value).abs();
/* 14324 */           index = i;
/*       */         }
/*       */       }
/* 14327 */       arrayc = null;
/* 14328 */       diff = null;
/* 14329 */       nearest = null;
/*       */     }
/*       */     else {
/* 14332 */       throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
/*       */     }
/* 14334 */     Conv.restoreMessages();
/* 14335 */     return index;
/*       */   }
/*       */   
/*       */   public double nearestValue(double value)
/*       */   {
/* 14340 */     int index = nearestIndex(value);
/* 14341 */     double ret = ((Double)this.array.get(index)).doubleValue();
/* 14342 */     return ret;
/*       */   }
/*       */   
/*       */   public Double nearestValue(Double value)
/*       */   {
/* 14347 */     int index = nearestIndex(value);
/* 14348 */     Double ret = (Double)this.array.get(index);
/* 14349 */     return ret;
/*       */   }
/*       */   
/*       */   public float nearestValue(float value)
/*       */   {
/* 14354 */     int index = nearestIndex(value);
/* 14355 */     float ret = ((Float)this.array.get(index)).floatValue();
/* 14356 */     return ret;
/*       */   }
/*       */   
/*       */   public Float nearestValue(Float value)
/*       */   {
/* 14361 */     int index = nearestIndex(value);
/* 14362 */     Float ret = (Float)this.array.get(index);
/* 14363 */     return ret;
/*       */   }
/*       */   
/*       */   public long nearestValue(long value)
/*       */   {
/* 14368 */     int index = nearestIndex(value);
/* 14369 */     long ret = ((Long)this.array.get(index)).longValue();
/* 14370 */     return ret;
/*       */   }
/*       */   
/*       */   public Long nearestValue(Long value)
/*       */   {
/* 14375 */     int index = nearestIndex(value);
/* 14376 */     Long ret = (Long)this.array.get(index);
/* 14377 */     return ret;
/*       */   }
/*       */   
/*       */   public int nearestValue(int value)
/*       */   {
/* 14382 */     int index = nearestIndex(value);
/* 14383 */     int ret = ((Integer)this.array.get(index)).intValue();
/* 14384 */     return ret;
/*       */   }
/*       */   
/*       */   public Integer nearestValue(Integer value)
/*       */   {
/* 14389 */     int index = nearestIndex(value);
/* 14390 */     Integer ret = (Integer)this.array.get(index);
/* 14391 */     return ret;
/*       */   }
/*       */   
/*       */   public short nearestValue(short value)
/*       */   {
/* 14396 */     int index = nearestIndex(value);
/* 14397 */     short ret = ((Short)this.array.get(index)).shortValue();
/* 14398 */     return ret;
/*       */   }
/*       */   
/*       */   public Short nearestValue(Short value)
/*       */   {
/* 14403 */     int index = nearestIndex(value);
/* 14404 */     Short ret = (Short)this.array.get(index);
/* 14405 */     return ret;
/*       */   }
/*       */   
/*       */   public byte nearestValue(byte value)
/*       */   {
/* 14410 */     int index = nearestIndex(value);
/* 14411 */     byte ret = ((Byte)this.array.get(index)).byteValue();
/* 14412 */     return ret;
/*       */   }
/*       */   
/*       */   public Byte nearestValue(Byte value)
/*       */   {
/* 14417 */     int index = nearestIndex(value);
/* 14418 */     Byte ret = (Byte)this.array.get(index);
/* 14419 */     return ret;
/*       */   }
/*       */   
/*       */   public char nearestValue(char value)
/*       */   {
/* 14424 */     int index = nearestIndex(value);
/* 14425 */     char ret = ((Character)this.array.get(index)).charValue();
/* 14426 */     return ret;
/*       */   }
/*       */   
/*       */   public Character nearestValue(Character value)
/*       */   {
/* 14431 */     int index = nearestIndex(value);
/* 14432 */     Character ret = (Character)this.array.get(index);
/* 14433 */     return ret;
/*       */   }
/*       */   
/*       */   public BigDecimal nearestValue(BigDecimal value)
/*       */   {
/* 14438 */     int index = nearestIndex(value);
/* 14439 */     BigDecimal ret = (BigDecimal)this.array.get(index);
/* 14440 */     return ret;
/*       */   }
/*       */   
/*       */   public BigInteger nearestValue(BigInteger value)
/*       */   {
/* 14445 */     int index = nearestIndex(value);
/* 14446 */     BigInteger ret = (BigInteger)this.array.get(index);
/* 14447 */     return ret;
/*       */   }
/*       */   
/*       */   public double maximumDifference()
/*       */   {
/* 14452 */     return getMaximumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double maximumDifference_as_double() {
/* 14456 */     return getMaximumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double getMaximumDifference() {
/* 14460 */     return getMaximumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double getMaximumDifference_as_double() {
/* 14464 */     double diff = 0.0D;
/* 14465 */     if ((this.type == 0) || (this.type == 1)) {
/* 14466 */       double max = getMaximum_as_double();
/* 14467 */       double min = getMinimum_as_double();
/* 14468 */       diff = max - min;
/*       */     }
/*       */     else {
/* 14471 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as double or Double the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14473 */     return diff;
/*       */   }
/*       */   
/*       */   public Double maximumDifference_as_Double()
/*       */   {
/* 14478 */     return getMaximumDifference_as_Double();
/*       */   }
/*       */   
/*       */   public Double getMaximumDifference_as_Double() {
/* 14482 */     return new Double(getMaximumDifference_as_double());
/*       */   }
/*       */   
/*       */   public float maximumDifference_as_float()
/*       */   {
/* 14487 */     return getMaximumDifference_as_float();
/*       */   }
/*       */   
/*       */   public float getMaximumDifference_as_float() {
/* 14491 */     float diff = 0.0F;
/* 14492 */     if ((this.type == 2) || (this.type == 3)) {
/* 14493 */       float max = getMaximum_as_float();
/* 14494 */       float min = getMinimum_as_float();
/* 14495 */       diff = max - min;
/*       */     }
/*       */     else {
/* 14498 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as float or Float the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14500 */     return diff;
/*       */   }
/*       */   
/*       */   public Float maximumDifference_as_Float()
/*       */   {
/* 14505 */     return getMaximumDifference_as_Float();
/*       */   }
/*       */   
/*       */   public Float getMaximumDifference_as_Float() {
/* 14509 */     return new Float(getMaximumDifference_as_float());
/*       */   }
/*       */   
/*       */   public long maximumDifference_as_long()
/*       */   {
/* 14514 */     return getMaximumDifference_as_long();
/*       */   }
/*       */   
/*       */   public long getMaximumDifference_as_long() {
/* 14518 */     long diff = 0L;
/* 14519 */     if ((this.type == 4) || (this.type == 5)) {
/* 14520 */       long max = getMaximum_as_long();
/* 14521 */       long min = getMinimum_as_long();
/* 14522 */       diff = max - min;
/*       */     }
/*       */     else {
/* 14525 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as long or Long the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14527 */     return diff;
/*       */   }
/*       */   
/*       */   public Long maximumDifference_as_Long()
/*       */   {
/* 14532 */     return getMaximumDifference_as_Long();
/*       */   }
/*       */   
/*       */   public Long getMaximumDifference_as_Long() {
/* 14536 */     return new Long(getMaximumDifference_as_long());
/*       */   }
/*       */   
/*       */   public int maximumDifference_as_int()
/*       */   {
/* 14541 */     return getMaximumDifference_as_int();
/*       */   }
/*       */   
/*       */   public int getMaximumDifference_as_int() {
/* 14545 */     int diff = 0;
/* 14546 */     if ((this.type == 6) || (this.type == 7)) {
/* 14547 */       int max = getMaximum_as_int();
/* 14548 */       int min = getMinimum_as_int();
/* 14549 */       diff = max - min;
/*       */     }
/*       */     else {
/* 14552 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as int or Integer the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14554 */     return diff;
/*       */   }
/*       */   
/*       */   public Integer maximumDifference_as_Integer()
/*       */   {
/* 14559 */     return getMaximumDifference_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getMaximumDifference_as_Integer() {
/* 14563 */     return new Integer(getMaximumDifference_as_int());
/*       */   }
/*       */   
/*       */   public short maximumDifference_as_short()
/*       */   {
/* 14568 */     return getMaximumDifference_as_short();
/*       */   }
/*       */   
/*       */   public short getMaximumDifference_as_short() {
/* 14572 */     short diff = 0;
/* 14573 */     if ((this.type == 8) || (this.type == 9)) {
/* 14574 */       short max = getMaximum_as_short();
/* 14575 */       short min = getMinimum_as_short();
/* 14576 */       diff = (short)(max - min);
/*       */     }
/*       */     else {
/* 14579 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as short or Short the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14581 */     return diff;
/*       */   }
/*       */   
/*       */   public Short maximumDifference_as_Short()
/*       */   {
/* 14586 */     return getMaximumDifference_as_Short();
/*       */   }
/*       */   
/*       */   public Short getMaximumDifference_as_Short() {
/* 14590 */     return new Short(getMaximumDifference_as_short());
/*       */   }
/*       */   
/*       */   public byte maximumDifference_as_byte()
/*       */   {
/* 14595 */     return getMaximumDifference_as_byte();
/*       */   }
/*       */   
/*       */   public byte getMaximumDifference_as_byte() {
/* 14599 */     byte diff = 0;
/* 14600 */     if ((this.type == 10) || (this.type == 11)) {
/* 14601 */       byte max = getMaximum_as_byte();
/* 14602 */       byte min = getMinimum_as_byte();
/* 14603 */       diff = (byte)(max - min);
/*       */     }
/*       */     else {
/* 14606 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as byte or Byte the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14608 */     return diff;
/*       */   }
/*       */   
/*       */   public Byte maximumDifference_as_Byte()
/*       */   {
/* 14613 */     return getMaximumDifference_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getMaximumDifference_as_Byte() {
/* 14617 */     return new Byte(getMaximumDifference_as_byte());
/*       */   }
/*       */   
/*       */   public BigDecimal maximumDifference_as_BigDecimal()
/*       */   {
/* 14622 */     return getMaximumDifference_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getMaximumDifference_as_BigDecimal() {
/* 14626 */     BigDecimal diff = BigDecimal.ZERO;
/* 14627 */     if (this.type == 12) {
/* 14628 */       BigDecimal max = getMaximum_as_BigDecimal();
/* 14629 */       BigDecimal min = getMinimum_as_BigDecimal();
/* 14630 */       diff = max.subtract(min);
/* 14631 */       max = null;
/* 14632 */       min = null;
/*       */     }
/*       */     else {
/* 14635 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigDecimal the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14637 */     return diff;
/*       */   }
/*       */   
/*       */   public BigInteger maximumDifference_as_BigInteger()
/*       */   {
/* 14642 */     return getMaximumDifference_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getMaximumDifference_as_BigInteger() {
/* 14646 */     BigInteger diff = BigInteger.ZERO;
/* 14647 */     if (this.type == 13) {
/* 14648 */       BigInteger max = getMaximum_as_BigInteger();
/* 14649 */       BigInteger min = getMinimum_as_BigInteger();
/* 14650 */       diff = max.subtract(min);
/* 14651 */       max = null;
/* 14652 */       min = null;
/*       */     }
/*       */     else {
/* 14655 */       throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigInteger the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14657 */     return diff;
/*       */   }
/*       */   
/*       */   public double minimumDifference()
/*       */   {
/* 14662 */     return getMinimumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double minimumDifference_as_double() {
/* 14666 */     return getMinimumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double getMinimumDifference() {
/* 14670 */     return getMinimumDifference_as_double();
/*       */   }
/*       */   
/*       */   public double getMinimumDifference_as_double() {
/* 14674 */     double diff = 0.0D;
/* 14675 */     if ((this.type == 0) || (this.type == 1)) {
/* 14676 */       ArrayMaths am = sort();
/* 14677 */       double[] sorted = am.getArray_as_double();
/* 14678 */       diff = sorted[1] - sorted[0];
/* 14679 */       double minDiff = diff;
/* 14680 */       for (int i = 1; i < this.length - 1; i++) {
/* 14681 */         diff = sorted[(i + 1)] - sorted[i];
/* 14682 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14686 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as double or Double the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14688 */     return diff;
/*       */   }
/*       */   
/*       */   public Double minimumDifference_as_Double()
/*       */   {
/* 14693 */     return getMinimumDifference_as_Double();
/*       */   }
/*       */   
/*       */   public Double getMinimumDifference_as_Double() {
/* 14697 */     return new Double(getMinimumDifference_as_double());
/*       */   }
/*       */   
/*       */   public float minimumDifference_as_float()
/*       */   {
/* 14702 */     return getMinimumDifference_as_float();
/*       */   }
/*       */   
/*       */   public float getMinimumDifference_as_float() {
/* 14706 */     float diff = 0.0F;
/* 14707 */     if ((this.type == 2) || (this.type == 3)) {
/* 14708 */       ArrayMaths am = sort();
/* 14709 */       float[] sorted = am.getArray_as_float();
/* 14710 */       diff = sorted[1] - sorted[0];
/* 14711 */       float minDiff = diff;
/* 14712 */       for (int i = 1; i < this.length - 1; i++) {
/* 14713 */         diff = sorted[(i + 1)] - sorted[i];
/* 14714 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14718 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as float or Float the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14720 */     return diff;
/*       */   }
/*       */   
/*       */   public Float minimumDifference_as_Float()
/*       */   {
/* 14725 */     return getMinimumDifference_as_Float();
/*       */   }
/*       */   
/*       */   public Float getMinimumDifference_as_Float() {
/* 14729 */     return new Float(getMinimumDifference_as_float());
/*       */   }
/*       */   
/*       */   public long minimumDifference_as_long()
/*       */   {
/* 14734 */     return getMinimumDifference_as_long();
/*       */   }
/*       */   
/*       */   public long getMinimumDifference_as_long() {
/* 14738 */     long diff = 0L;
/* 14739 */     if ((this.type == 4) || (this.type == 5)) {
/* 14740 */       ArrayMaths am = sort();
/* 14741 */       long[] sorted = am.getArray_as_long();
/* 14742 */       diff = sorted[1] - sorted[0];
/* 14743 */       long minDiff = diff;
/* 14744 */       for (int i = 1; i < this.length - 1; i++) {
/* 14745 */         diff = sorted[(i + 1)] - sorted[i];
/* 14746 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14750 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as long or Long the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14752 */     return diff;
/*       */   }
/*       */   
/*       */   public Long minimumDifference_as_Long()
/*       */   {
/* 14757 */     return getMinimumDifference_as_Long();
/*       */   }
/*       */   
/*       */   public Long getMinimumDifference_as_Long() {
/* 14761 */     return new Long(getMinimumDifference_as_long());
/*       */   }
/*       */   
/*       */   public int minimumDifference_as_int()
/*       */   {
/* 14766 */     return getMinimumDifference_as_int();
/*       */   }
/*       */   
/*       */   public int getMinimumDifference_as_int() {
/* 14770 */     int diff = 0;
/* 14771 */     if ((this.type == 6) || (this.type == 7)) {
/* 14772 */       ArrayMaths am = sort();
/* 14773 */       int[] sorted = am.getArray_as_int();
/* 14774 */       diff = sorted[1] - sorted[0];
/* 14775 */       int minDiff = diff;
/* 14776 */       for (int i = 1; i < this.length - 1; i++) {
/* 14777 */         diff = sorted[(i + 1)] - sorted[i];
/* 14778 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14782 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as int or Integer the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14784 */     return diff;
/*       */   }
/*       */   
/*       */   public Integer minimumDifference_as_Integer()
/*       */   {
/* 14789 */     return getMinimumDifference_as_Integer();
/*       */   }
/*       */   
/*       */   public Integer getMinimumDifference_as_Integer() {
/* 14793 */     return new Integer(getMinimumDifference_as_int());
/*       */   }
/*       */   
/*       */   public short minimumDifference_as_short()
/*       */   {
/* 14798 */     return getMinimumDifference_as_short();
/*       */   }
/*       */   
/*       */   public short getMinimumDifference_as_short() {
/* 14802 */     short diff = 0;
/* 14803 */     if ((this.type == 8) || (this.type == 9)) {
/* 14804 */       ArrayMaths am = sort();
/* 14805 */       short[] sorted = am.getArray_as_short();
/* 14806 */       diff = (short)(sorted[1] - sorted[0]);
/* 14807 */       short minDiff = diff;
/* 14808 */       for (int i = 1; i < this.length - 1; i++) {
/* 14809 */         diff = (short)(sorted[(i + 1)] - sorted[i]);
/* 14810 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14814 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as short or Short the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14816 */     return diff;
/*       */   }
/*       */   
/*       */   public Short minimumDifference_as_Short()
/*       */   {
/* 14821 */     return getMinimumDifference_as_Short();
/*       */   }
/*       */   
/*       */   public Short getMinimumDifference_as_Short() {
/* 14825 */     return new Short(getMinimumDifference_as_short());
/*       */   }
/*       */   
/*       */ 
/*       */   public byte minimumDifference_as_byte()
/*       */   {
/* 14831 */     return getMinimumDifference_as_byte();
/*       */   }
/*       */   
/*       */   public byte getMinimumDifference_as_byte() {
/* 14835 */     byte diff = 0;
/* 14836 */     if ((this.type == 10) || (this.type == 11)) {
/* 14837 */       ArrayMaths am = sort();
/* 14838 */       byte[] sorted = am.getArray_as_byte();
/* 14839 */       diff = (byte)(sorted[1] - sorted[0]);
/* 14840 */       byte minDiff = diff;
/* 14841 */       for (int i = 1; i < this.length - 1; i++) {
/* 14842 */         diff = (byte)(sorted[(i + 1)] - sorted[i]);
/* 14843 */         if (diff < minDiff) minDiff = diff;
/*       */       }
/*       */     }
/*       */     else {
/* 14847 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as byte or Byte the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14849 */     return diff;
/*       */   }
/*       */   
/*       */   public Byte minimumDifference_as_Byte()
/*       */   {
/* 14854 */     return getMinimumDifference_as_Byte();
/*       */   }
/*       */   
/*       */   public Byte getMinimumDifference_as_Byte() {
/* 14858 */     return new Byte(getMinimumDifference_as_byte());
/*       */   }
/*       */   
/*       */   public BigDecimal minimumDifference_as_BigDecimal()
/*       */   {
/* 14863 */     return getMinimumDifference_as_BigDecimal();
/*       */   }
/*       */   
/*       */   public BigDecimal getMinimumDifference_as_BigDecimal() {
/* 14867 */     BigDecimal diff = BigDecimal.ZERO;
/* 14868 */     if (this.type == 12) {
/* 14869 */       ArrayMaths am = sort();
/* 14870 */       BigDecimal[] sorted = am.getArray_as_BigDecimal();
/* 14871 */       diff = sorted[1].subtract(sorted[0]);
/* 14872 */       BigDecimal minDiff = diff;
/* 14873 */       for (int i = 1; i < this.length - 1; i++) {
/* 14874 */         diff = sorted[(i + 1)].subtract(sorted[i]);
/* 14875 */         if (diff.compareTo(minDiff) == -1) minDiff = diff;
/*       */       }
/* 14877 */       sorted = null;
/* 14878 */       minDiff = null;
/*       */     }
/*       */     else {
/* 14881 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigDecimal the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14883 */     return diff;
/*       */   }
/*       */   
/*       */   public BigInteger minimumDifference_as_BigInteger()
/*       */   {
/* 14888 */     return getMinimumDifference_as_BigInteger();
/*       */   }
/*       */   
/*       */   public BigInteger getMinimumDifference_as_BigInteger() {
/* 14892 */     BigInteger diff = BigInteger.ZERO;
/* 14893 */     if (this.type == 12) {
/* 14894 */       ArrayMaths am = sort();
/* 14895 */       BigInteger[] sorted = am.getArray_as_BigInteger();
/* 14896 */       diff = sorted[1].subtract(sorted[0]);
/* 14897 */       BigInteger minDiff = diff;
/* 14898 */       for (int i = 1; i < this.length - 1; i++) {
/* 14899 */         diff = sorted[(i + 1)].subtract(sorted[i]);
/* 14900 */         if (diff.compareTo(minDiff) == -1) minDiff = diff;
/*       */       }
/* 14902 */       sorted = null;
/* 14903 */       minDiff = null;
/*       */     }
/*       */     else {
/* 14906 */       throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigInteger the difference for a " + this.typeName[this.type] + "[] array");
/*       */     }
/* 14908 */     return diff;
/*       */   }
/*       */   
/*       */   public void print()
/*       */   {
/* 14913 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 14915 */       Double[] dd = getArray_as_Double();
/* 14916 */       PrintToScreen.print(dd);
/* 14917 */       break;
/*       */     case 2: case 3: 
/* 14919 */       Float[] ff = getArray_as_Float();
/* 14920 */       PrintToScreen.print(ff);
/* 14921 */       break;
/*       */     case 4: case 5: 
/* 14923 */       Long[] ll = getArray_as_Long();
/* 14924 */       PrintToScreen.print(ll);
/* 14925 */       break;
/*       */     case 6: case 7: 
/* 14927 */       Integer[] ii = getArray_as_Integer();
/* 14928 */       PrintToScreen.print(ii);
/* 14929 */       break;
/*       */     case 8: case 9: 
/* 14931 */       Short[] ss = getArray_as_Short();
/* 14932 */       PrintToScreen.print(ss);
/* 14933 */       break;
/*       */     case 10: case 11: 
/* 14935 */       Byte[] bb = getArray_as_Byte();
/* 14936 */       PrintToScreen.print(bb);
/* 14937 */       break;
/* 14938 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 14939 */       PrintToScreen.print(bd);
/* 14940 */       bd = null;
/* 14941 */       break;
/* 14942 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 14943 */       PrintToScreen.print(bi);
/* 14944 */       bi = null;
/* 14945 */       break;
/* 14946 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 14947 */       PrintToScreen.print(cc);
/* 14948 */       break;
/* 14949 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 14950 */       PrintToScreen.print(pp);
/* 14951 */       break;
/*       */     case 16: case 17: 
/* 14953 */       Character[] ct = getArray_as_Character();
/* 14954 */       PrintToScreen.print(ct);
/* 14955 */       break;
/* 14956 */     case 18:  String[] st = getArray_as_String();
/* 14957 */       PrintToScreen.print(st);
/* 14958 */       break;
/* 14959 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */   }
/*       */   
/*       */   public void println()
/*       */   {
/* 14965 */     switch (this.type) {
/*       */     case 0: case 1: 
/* 14967 */       Double[] dd = getArray_as_Double();
/* 14968 */       PrintToScreen.println(dd);
/* 14969 */       break;
/*       */     case 2: case 3: 
/* 14971 */       Float[] ff = getArray_as_Float();
/* 14972 */       PrintToScreen.println(ff);
/* 14973 */       break;
/*       */     case 4: case 5: 
/* 14975 */       Long[] ll = getArray_as_Long();
/* 14976 */       PrintToScreen.println(ll);
/* 14977 */       break;
/*       */     case 6: case 7: 
/* 14979 */       Integer[] ii = getArray_as_Integer();
/* 14980 */       PrintToScreen.println(ii);
/* 14981 */       break;
/*       */     case 8: case 9: 
/* 14983 */       Short[] ss = getArray_as_Short();
/* 14984 */       PrintToScreen.println(ss);
/* 14985 */       break;
/*       */     case 10: case 11: 
/* 14987 */       Byte[] bb = getArray_as_Byte();
/* 14988 */       PrintToScreen.println(bb);
/* 14989 */       break;
/* 14990 */     case 12:  BigDecimal[] bd = getArray_as_BigDecimal();
/* 14991 */       PrintToScreen.println(bd);
/* 14992 */       bd = null;
/* 14993 */       break;
/* 14994 */     case 13:  BigInteger[] bi = getArray_as_BigInteger();
/* 14995 */       PrintToScreen.println(bi);
/* 14996 */       bi = null;
/* 14997 */       break;
/* 14998 */     case 14:  Complex[] cc = getArray_as_Complex();
/* 14999 */       PrintToScreen.println(cc);
/* 15000 */       break;
/* 15001 */     case 15:  Phasor[] pp = getArray_as_Phasor();
/* 15002 */       PrintToScreen.println(pp);
/* 15003 */       break;
/*       */     case 16: case 17: 
/* 15005 */       Character[] ct = getArray_as_Character();
/* 15006 */       PrintToScreen.println(ct);
/* 15007 */       break;
/* 15008 */     case 18:  String[] st = getArray_as_String();
/* 15009 */       PrintToScreen.println(st);
/* 15010 */       break;
/* 15011 */     default:  throw new IllegalArgumentException("Data type not identified by this method");
/*       */     }
/*       */     
/*       */   }
/*       */   
/*       */ 
/*       */   public void convertToHighest()
/*       */   {
/* 15019 */     switch (this.type) {
/*       */     case 0: case 1: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/*       */     case 9: 
/*       */     case 10: 
/*       */     case 11: 
/*       */     case 16: 
/*       */     case 17: 
/*       */     case 18: 
/* 15034 */       Double[] dd = getArray_as_Double();
/* 15035 */       this.array.clear();
/* 15036 */       for (int i = 0; i < this.length; i++) this.array.add(dd[i]);
/* 15037 */       this.type = 1;
/* 15038 */       break;
/*       */     case 12: case 13: 
/* 15040 */       BigDecimal[] bd = getArray_as_BigDecimal();
/* 15041 */       this.array.clear();
/* 15042 */       for (int i = 0; i < this.length; i++) this.array.add(bd[i]);
/* 15043 */       this.type = 12;
/* 15044 */       bd = null;
/* 15045 */       break;
/*       */     case 14: case 15: 
/* 15047 */       Complex[] cc = getArray_as_Complex();
/* 15048 */       this.array.clear();
/* 15049 */       for (int i = 0; i < this.length; i++) this.array.add(cc[i]);
/* 15050 */       this.type = 14;
/*       */     }
/*       */     
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Stat toStat()
/*       */   {
/* 15062 */     convertToHighest();
/* 15063 */     return statCopy();
/*       */   }
/*       */   
/*       */   public void plot(int n)
/*       */   {
/* 15068 */     if (n > 2) { throw new IllegalArgumentException("Argument n, " + n + ", must be less than 3");
/*       */     }
/* 15070 */     double[] xAxis = new double[this.length];
/* 15071 */     for (int i = 0; i < this.length; i++) xAxis[i] = i;
/* 15072 */     double[] yAxis = getArray_as_double();
/*       */     
/*       */ 
/* 15075 */     PlotGraph pg = new PlotGraph(xAxis, yAxis);
/* 15076 */     pg.setGraphTitle("ArrayMaths plot method");
/* 15077 */     pg.setXaxisLegend("Array element index");
/* 15078 */     pg.setYaxisLegend("Array element value");
/* 15079 */     pg.setPoint(1);
/* 15080 */     switch (n) {
/* 15081 */     case 0:  pg.setLine(0);
/* 15082 */       pg.setGraphTitle2("Points only - no line");
/* 15083 */       break;
/* 15084 */     case 1:  pg.setLine(3);
/* 15085 */       pg.setGraphTitle2("Points joined by straight lines");
/* 15086 */       break;
/* 15087 */     case 2:  pg.setLine(1);
/* 15088 */       pg.setGraphTitle2("Points joined by cubic spline interpolated line");
/* 15089 */       break;
/* 15090 */     default:  throw new IllegalArgumentException("Should not be possible to get here!!!");
/*       */     }
/* 15092 */     pg.plot();
/*       */   }
/*       */   
/*       */   public static void suppressMessagesTotal() {}
/*       */   
/*       */   public static void restoreMessagesTotal() {}
/*       */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/ArrayMaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */