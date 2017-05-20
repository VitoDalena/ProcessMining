/*     */ package cern.jet.math;
/*     */ 
/*     */ import cern.colt.function.IntFunction;
/*     */ import cern.colt.function.IntIntFunction;
/*     */ import cern.colt.function.IntIntProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import java.util.Date;
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
/*     */ public class IntFunctions
/*     */ {
/*  38 */   public static final IntFunctions intFunctions = new IntFunctions();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  46 */   public static final IntFunction abs = new IntFunction() {
/*  47 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt < 0 ? -paramAnonymousInt : paramAnonymousInt; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  53 */   public static final IntFunction dec = new IntFunction() {
/*  54 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt--; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  60 */   public static final IntFunction factorial = new IntFunction() {
/*  61 */     public final int apply(int paramAnonymousInt) { return (int)Arithmetic.factorial(paramAnonymousInt); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  67 */   public static final IntFunction identity = new IntFunction() {
/*  68 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  74 */   public static final IntFunction inc = new IntFunction() {
/*  75 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt++; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  81 */   public static final IntFunction neg = new IntFunction() {
/*  82 */     public final int apply(int paramAnonymousInt) { return -paramAnonymousInt; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  88 */   public static final IntFunction not = new IntFunction() {
/*  89 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt ^ 0xFFFFFFFF; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  95 */   public static final IntFunction sign = new IntFunction() {
/*  96 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt > 0 ? 1 : paramAnonymousInt < 0 ? -1 : 0; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 102 */   public static final IntFunction square = new IntFunction() {
/* 103 */     public final int apply(int paramAnonymousInt) { return paramAnonymousInt * paramAnonymousInt; }
/*     */   };
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
/* 118 */   public static final IntIntFunction and = new IntIntFunction() {
/* 119 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 & paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 125 */   public static final IntIntFunction compare = new IntIntFunction() {
/* 126 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 > paramAnonymousInt2 ? 1 : paramAnonymousInt1 < paramAnonymousInt2 ? -1 : 0; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 132 */   public static final IntIntFunction div = new IntIntFunction() {
/* 133 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 / paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 139 */   public static final IntIntFunction equals = new IntIntFunction() {
/* 140 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 == paramAnonymousInt2 ? 1 : 0; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 146 */   public static final IntIntProcedure isEqual = new IntIntProcedure() {
/* 147 */     public final boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 == paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 153 */   public static final IntIntProcedure isLess = new IntIntProcedure() {
/* 154 */     public final boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 < paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 160 */   public static final IntIntProcedure isGreater = new IntIntProcedure() {
/* 161 */     public final boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 > paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 167 */   public static final IntIntFunction max = new IntIntFunction() {
/* 168 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 >= paramAnonymousInt2 ? paramAnonymousInt1 : paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 174 */   public static final IntIntFunction min = new IntIntFunction() {
/* 175 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 <= paramAnonymousInt2 ? paramAnonymousInt1 : paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 181 */   public static final IntIntFunction minus = new IntIntFunction() {
/* 182 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 - paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 188 */   public static final IntIntFunction mod = new IntIntFunction() {
/* 189 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 % paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 195 */   public static final IntIntFunction mult = new IntIntFunction() {
/* 196 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 * paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 202 */   public static final IntIntFunction or = new IntIntFunction() {
/* 203 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 | paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 209 */   public static final IntIntFunction plus = new IntIntFunction() {
/* 210 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 + paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 216 */   public static final IntIntFunction pow = new IntIntFunction() {
/* 217 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return (int)Math.pow(paramAnonymousInt1, paramAnonymousInt2); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 223 */   public static final IntIntFunction shiftLeft = new IntIntFunction() {
/* 224 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 << paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 231 */   public static final IntIntFunction shiftRightSigned = new IntIntFunction() {
/* 232 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 >> paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 238 */   public static final IntIntFunction shiftRightUnsigned = new IntIntFunction() {
/* 239 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 >>> paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 245 */   public static final IntIntFunction xor = new IntIntFunction() {
/* 246 */     public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return paramAnonymousInt1 ^ paramAnonymousInt2; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IntFunction and(int paramInt)
/*     */   {
/* 258 */     new IntFunction() {
/* 259 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt & this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction between(int paramInt1, int paramInt2)
/*     */   {
/* 267 */     new IntFunction() {
/* 268 */       public final int apply(int paramAnonymousInt) { return (this.val$from <= paramAnonymousInt) && (paramAnonymousInt <= this.val$to) ? 1 : 0; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$from;
/*     */   
/*     */   private final int val$b;
/*     */   
/*     */   public static IntFunction bindArg1(IntIntFunction paramIntIntFunction, int paramInt)
/*     */   {
/* 279 */     new IntFunction() {
/* 280 */       public final int apply(int paramAnonymousInt) { return this.val$function.apply(this.val$c, paramAnonymousInt); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final IntIntFunction val$function;
/*     */   
/*     */   private final int val$c;
/*     */   
/*     */   public static IntFunction bindArg2(IntIntFunction paramIntIntFunction, int paramInt)
/*     */   {
/* 291 */     new IntFunction() {
/* 292 */       public final int apply(int paramAnonymousInt) { return this.val$function.apply(paramAnonymousInt, this.val$c); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final IntIntFunction val$function;
/*     */   
/*     */   private final int val$c;
/*     */   
/*     */   public static IntFunction chain(IntFunction paramIntFunction1, IntFunction paramIntFunction2)
/*     */   {
/* 303 */     new IntFunction() {
/* 304 */       public final int apply(int paramAnonymousInt) { return this.val$g.apply(this.val$h.apply(paramAnonymousInt)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final IntFunction val$g;
/*     */   
/*     */   private final IntFunction val$h;
/*     */   
/*     */   public static IntIntFunction chain(IntFunction paramIntFunction, IntIntFunction paramIntIntFunction)
/*     */   {
/* 315 */     new IntIntFunction() {
/* 316 */       public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$g.apply(this.val$h.apply(paramAnonymousInt1, paramAnonymousInt2)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final IntFunction val$g;
/*     */   
/*     */   private final IntIntFunction val$h;
/*     */   
/*     */   private final int val$to;
/*     */   public static IntIntFunction chain(IntIntFunction paramIntIntFunction, IntFunction paramIntFunction1, IntFunction paramIntFunction2)
/*     */   {
/* 328 */     new IntIntFunction() {
/* 329 */       public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$f.apply(this.val$g.apply(paramAnonymousInt1), this.val$h.apply(paramAnonymousInt2)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final IntIntFunction val$f;
/*     */   public static IntFunction compare(int paramInt)
/*     */   {
/* 337 */     new IntFunction() {
/* 338 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt > this.val$b ? 1 : paramAnonymousInt < this.val$b ? -1 : 0; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   public static IntFunction constant(int paramInt)
/*     */   {
/* 345 */     new IntFunction() {
/* 346 */       public final int apply(int paramAnonymousInt) { return this.val$c; }
/*     */     };
/*     */   }
/*     */   
/*     */   private final int val$c;
/*     */   private final int val$b;
/*     */   public static IntFunction div(int paramInt)
/*     */   {
/* 354 */     new IntFunction() {
/* 355 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt / this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$b;
/*     */   public static IntFunction equals(int paramInt)
/*     */   {
/* 363 */     new IntFunction() {
/* 364 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt == this.val$b ? 1 : 0; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$b;
/*     */   public static IntProcedure isBetween(int paramInt1, int paramInt2)
/*     */   {
/* 372 */     new IntProcedure() {
/* 373 */       public final boolean apply(int paramAnonymousInt) { return (this.val$from <= paramAnonymousInt) && (paramAnonymousInt <= this.val$to); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$from;
/*     */   public static IntProcedure isEqual(int paramInt)
/*     */   {
/* 381 */     new IntProcedure() {
/* 382 */       public final boolean apply(int paramAnonymousInt) { return paramAnonymousInt == this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$b;
/*     */   public static IntProcedure isGreater(int paramInt)
/*     */   {
/* 390 */     new IntProcedure() {
/* 391 */       public final boolean apply(int paramAnonymousInt) { return paramAnonymousInt > this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$b;
/*     */   public static IntProcedure isLess(int paramInt)
/*     */   {
/* 399 */     new IntProcedure() { private final int val$b;
/* 400 */       public final boolean apply(int paramAnonymousInt) { return paramAnonymousInt < this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction max(int paramInt)
/*     */   {
/* 408 */     new IntFunction() { private final int val$b;
/* 409 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt >= this.val$b ? paramAnonymousInt : this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction min(int paramInt)
/*     */   {
/* 417 */     new IntFunction() { private final int val$b;
/* 418 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt <= this.val$b ? paramAnonymousInt : this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction minus(int paramInt)
/*     */   {
/* 426 */     new IntFunction() { private final int val$b;
/* 427 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt - this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction mod(int paramInt)
/*     */   {
/* 435 */     new IntFunction() { private final int val$b;
/* 436 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt % this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction mult(int paramInt)
/*     */   {
/* 444 */     new IntFunction() { private final int val$b;
/* 445 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt * this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction or(int paramInt)
/*     */   {
/* 453 */     new IntFunction() { private final int val$b;
/* 454 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt | this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction plus(int paramInt)
/*     */   {
/* 462 */     new IntFunction() { private final int val$b;
/* 463 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt + this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction pow(int paramInt)
/*     */   {
/* 471 */     new IntFunction() { private final int val$b;
/* 472 */       public final int apply(int paramAnonymousInt) { return (int)Math.pow(paramAnonymousInt, this.val$b); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   private final int val$to;
/*     */   
/*     */   private final IntFunction val$g;
/*     */   
/*     */   private final IntFunction val$h;
/*     */   public static IntFunction random()
/*     */   {
/* 484 */     return new MersenneTwister(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction shiftLeft(int paramInt)
/*     */   {
/* 491 */     new IntFunction() { private final int val$b;
/* 492 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt << this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction shiftRightSigned(int paramInt)
/*     */   {
/* 500 */     new IntFunction() { private final int val$b;
/* 501 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt >> this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction shiftRightUnsigned(int paramInt)
/*     */   {
/* 509 */     new IntFunction() { private final int val$b;
/* 510 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt >>> this.val$b; }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IntIntFunction swapArgs(IntIntFunction paramIntIntFunction)
/*     */   {
/* 520 */     new IntIntFunction() { private final IntIntFunction val$function;
/* 521 */       public final int apply(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$function.apply(paramAnonymousInt2, paramAnonymousInt1); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IntFunction xor(int paramInt)
/*     */   {
/* 529 */     new IntFunction() { private final int val$b;
/* 530 */       public final int apply(int paramAnonymousInt) { return paramAnonymousInt ^ this.val$b; }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/IntFunctions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */