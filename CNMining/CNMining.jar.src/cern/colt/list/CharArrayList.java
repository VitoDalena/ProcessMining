/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.CharProcedure;
/*     */ import cern.jet.math.Arithmetic;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.DRand;
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
/*     */ public class CharArrayList
/*     */   extends AbstractCharList
/*     */ {
/*     */   protected char[] elements;
/*     */   
/*     */   public CharArrayList()
/*     */   {
/*  27 */     this(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CharArrayList(char[] paramArrayOfChar)
/*     */   {
/*  39 */     elements(paramArrayOfChar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CharArrayList(int paramInt)
/*     */   {
/*  47 */     this(new char[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(char paramChar)
/*     */   {
/*  57 */     if (this.size == this.elements.length) {
/*  58 */       ensureCapacity(this.size + 1);
/*     */     }
/*  60 */     this.elements[(this.size++)] = paramChar;
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
/*     */   public void beforeInsert(int paramInt, char paramChar)
/*     */   {
/*  73 */     if ((paramInt > this.size) || (paramInt < 0))
/*  74 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  75 */     ensureCapacity(this.size + 1);
/*  76 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  77 */     this.elements[paramInt] = paramChar;
/*  78 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(char paramChar, int paramInt1, int paramInt2)
/*     */   {
/* 104 */     return Sorting.binarySearchFromTo(this.elements, paramChar, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 113 */     CharArrayList localCharArrayList = new CharArrayList((char[])this.elements.clone());
/* 114 */     localCharArrayList.setSizeRaw(this.size);
/* 115 */     return localCharArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CharArrayList copy()
/*     */   {
/* 123 */     return (CharArrayList)clone();
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
/*     */ 
/*     */ 
/*     */   protected void countSortFromTo(int paramInt1, int paramInt2, char paramChar1, char paramChar2)
/*     */   {
/* 140 */     if (this.size == 0) return;
/* 141 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 143 */     int i = paramChar2 - paramChar1 + 1;
/*     */     
/* 145 */     int[] arrayOfInt = new int[i];
/* 146 */     char[] arrayOfChar = this.elements;
/* 147 */     for (int j = paramInt1; j <= paramInt2; arrayOfInt[(arrayOfChar[(j++)] - paramChar1)] += 1) {}
/*     */     
/* 149 */     int k = paramInt1;
/* 150 */     char c = paramChar1;
/* 151 */     for (int m = 0; m < i; c = (char)(c + '\001')) {
/* 152 */       int n = arrayOfInt[m];
/* 153 */       if (n > 0) {
/* 154 */         if (n == 1) { arrayOfChar[(k++)] = c;
/*     */         } else {
/* 156 */           int i1 = k + n - 1;
/* 157 */           fillFromToWith(k, i1, c);
/* 158 */           k = i1 + 1;
/*     */         }
/*     */       }
/* 151 */       m++;
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char[] elements()
/*     */   {
/* 172 */     return this.elements;
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
/*     */   public AbstractCharList elements(char[] paramArrayOfChar)
/*     */   {
/* 185 */     this.elements = paramArrayOfChar;
/* 186 */     this.size = paramArrayOfChar.length;
/* 187 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 196 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 210 */     if (!(paramObject instanceof CharArrayList)) return super.equals(paramObject);
/* 211 */     if (this == paramObject) return true;
/* 212 */     if (paramObject == null) return false;
/* 213 */     CharArrayList localCharArrayList = (CharArrayList)paramObject;
/* 214 */     if (size() != localCharArrayList.size()) { return false;
/*     */     }
/* 216 */     char[] arrayOfChar1 = elements();
/* 217 */     char[] arrayOfChar2 = localCharArrayList.elements();
/* 218 */     int i = size();
/* 219 */     do { if (arrayOfChar1[i] != arrayOfChar2[i]) return false;
/* 218 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 221 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(CharProcedure paramCharProcedure)
/*     */   {
/* 231 */     char[] arrayOfChar = this.elements;
/* 232 */     int i = this.size;
/*     */     
/* 234 */     for (int j = 0; j < i;) if (!paramCharProcedure.apply(arrayOfChar[(j++)])) return false;
/* 235 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char get(int paramInt)
/*     */   {
/* 246 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 247 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 248 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char getQuick(int paramInt)
/*     */   {
/* 259 */     return this.elements[paramInt];
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
/*     */ 
/*     */   public int indexOfFromTo(char paramChar, int paramInt1, int paramInt2)
/*     */   {
/* 275 */     if (this.size == 0) return -1;
/* 276 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 278 */     char[] arrayOfChar = this.elements;
/* 279 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 280 */       if (paramChar == arrayOfChar[i]) return i;
/*     */     }
/* 282 */     return -1;
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
/*     */ 
/*     */   public int lastIndexOfFromTo(char paramChar, int paramInt1, int paramInt2)
/*     */   {
/* 298 */     if (this.size == 0) return -1;
/* 299 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 301 */     char[] arrayOfChar = this.elements;
/* 302 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 303 */       if (paramChar == arrayOfChar[i]) return i;
/*     */     }
/* 305 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractCharList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 315 */     if (this.size == 0) { return new CharArrayList(0);
/*     */     }
/* 317 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 319 */     char[] arrayOfChar = new char[paramInt2 - paramInt1 + 1];
/* 320 */     System.arraycopy(this.elements, paramInt1, arrayOfChar, 0, paramInt2 - paramInt1 + 1);
/* 321 */     return new CharArrayList(arrayOfChar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractCharList paramAbstractCharList)
/*     */   {
/* 332 */     if (!(paramAbstractCharList instanceof CharArrayList)) { return super.removeAll(paramAbstractCharList);
/*     */     }
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
/* 344 */     if (paramAbstractCharList.size() == 0) return false;
/* 345 */     int i = paramAbstractCharList.size() - 1;
/* 346 */     int j = 0;
/* 347 */     char[] arrayOfChar = this.elements;
/* 348 */     int k = size();
/*     */     
/* 350 */     double d1 = paramAbstractCharList.size();
/* 351 */     double d2 = k;
/* 352 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 354 */       CharArrayList localCharArrayList = (CharArrayList)paramAbstractCharList.clone();
/* 355 */       localCharArrayList.quickSort();
/*     */       
/* 357 */       for (int n = 0; n < k; n++) {
/* 358 */         if (localCharArrayList.binarySearchFromTo(arrayOfChar[n], 0, i) < 0) arrayOfChar[(j++)] = arrayOfChar[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 363 */       for (m = 0; m < k; m++) {
/* 364 */         if (paramAbstractCharList.indexOfFromTo(arrayOfChar[m], 0, i) < 0) { arrayOfChar[(j++)] = arrayOfChar[m];
/*     */         }
/*     */       }
/*     */     }
/* 368 */     int m = j != k ? 1 : 0;
/* 369 */     setSize(j);
/* 370 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractCharList paramAbstractCharList, int paramInt3)
/*     */   {
/* 384 */     if (!(paramAbstractCharList instanceof CharArrayList))
/*     */     {
/* 386 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractCharList, paramInt3);
/* 387 */       return;
/*     */     }
/* 389 */     int i = paramInt2 - paramInt1 + 1;
/* 390 */     if (i > 0) {
/* 391 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 392 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractCharList.size());
/* 393 */       System.arraycopy(((CharArrayList)paramAbstractCharList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractCharList paramAbstractCharList)
/*     */   {
/* 405 */     if (!(paramAbstractCharList instanceof CharArrayList)) { return super.retainAll(paramAbstractCharList);
/*     */     }
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
/* 417 */     int i = paramAbstractCharList.size() - 1;
/* 418 */     int j = 0;
/* 419 */     char[] arrayOfChar = this.elements;
/* 420 */     int k = size();
/*     */     
/* 422 */     double d1 = paramAbstractCharList.size();
/* 423 */     double d2 = k;
/* 424 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 426 */       CharArrayList localCharArrayList = (CharArrayList)paramAbstractCharList.clone();
/* 427 */       localCharArrayList.quickSort();
/*     */       
/* 429 */       for (int n = 0; n < k; n++) {
/* 430 */         if (localCharArrayList.binarySearchFromTo(arrayOfChar[n], 0, i) >= 0) arrayOfChar[(j++)] = arrayOfChar[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 435 */       for (m = 0; m < k; m++) {
/* 436 */         if (paramAbstractCharList.indexOfFromTo(arrayOfChar[m], 0, i) >= 0) { arrayOfChar[(j++)] = arrayOfChar[m];
/*     */         }
/*     */       }
/*     */     }
/* 440 */     int m = j != k ? 1 : 0;
/* 441 */     setSize(j);
/* 442 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 451 */     int j = this.size / 2;
/* 452 */     int k = this.size - 1;
/*     */     
/* 454 */     char[] arrayOfChar = this.elements;
/* 455 */     for (int m = 0; m < j;) {
/* 456 */       int i = arrayOfChar[m];
/* 457 */       arrayOfChar[(m++)] = arrayOfChar[k];
/* 458 */       arrayOfChar[(k--)] = i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, char paramChar)
/*     */   {
/* 471 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 472 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 473 */     this.elements[paramInt] = paramChar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, char paramChar)
/*     */   {
/* 485 */     this.elements[paramInt] = paramChar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 495 */     if (this.size == 0) return;
/* 496 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 498 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 500 */     char[] arrayOfChar = this.elements;
/*     */     
/* 502 */     for (int k = paramInt1; k < paramInt2; k++) {
/* 503 */       int j = localUniform.nextIntFromTo(k, paramInt2);
/*     */       
/*     */ 
/* 506 */       int i = arrayOfChar[j];
/* 507 */       arrayOfChar[j] = arrayOfChar[k];
/* 508 */       arrayOfChar[k] = i;
/*     */     }
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
/*     */   public void sortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 534 */     if (this.size == 0) return;
/* 535 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/*     */ 
/* 538 */     char c1 = this.elements[paramInt1];
/* 539 */     char c2 = this.elements[paramInt1];
/*     */     
/* 541 */     char[] arrayOfChar = this.elements;
/* 542 */     for (int i = paramInt1 + 1; i <= paramInt2;) {
/* 543 */       char c3 = arrayOfChar[(i++)];
/* 544 */       if (c3 > c2) { c2 = c3;
/* 545 */       } else if (c3 < c1) { c1 = c3;
/*     */       }
/*     */     }
/*     */     
/* 549 */     double d1 = paramInt2 - paramInt1 + 1.0D;
/* 550 */     double d2 = d1 * Math.log(d1) / 0.6931471805599453D;
/*     */     
/* 552 */     double d3 = c2 - c1 + 1.0D;
/* 553 */     double d4 = Math.max(d3, d1);
/*     */     
/* 555 */     if ((d3 < 10000.0D) && (d4 < d2)) {
/* 556 */       countSortFromTo(paramInt1, paramInt2, c1, c2);
/*     */     }
/*     */     else {
/* 559 */       quickSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 568 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/CharArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */