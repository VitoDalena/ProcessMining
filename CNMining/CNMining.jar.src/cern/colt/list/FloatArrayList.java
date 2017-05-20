/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.FloatProcedure;
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
/*     */ public class FloatArrayList
/*     */   extends AbstractFloatList
/*     */ {
/*     */   protected float[] elements;
/*     */   
/*     */   public FloatArrayList()
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
/*     */   public FloatArrayList(float[] paramArrayOfFloat)
/*     */   {
/*  39 */     elements(paramArrayOfFloat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FloatArrayList(int paramInt)
/*     */   {
/*  47 */     this(new float[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(float paramFloat)
/*     */   {
/*  57 */     if (this.size == this.elements.length) ensureCapacity(this.size + 1);
/*  58 */     this.elements[(this.size++)] = paramFloat;
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
/*     */   public void beforeInsert(int paramInt, float paramFloat)
/*     */   {
/*  71 */     if ((paramInt > this.size) || (paramInt < 0))
/*  72 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  73 */     ensureCapacity(this.size + 1);
/*  74 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  75 */     this.elements[paramInt] = paramFloat;
/*  76 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(float paramFloat, int paramInt1, int paramInt2)
/*     */   {
/* 102 */     return Sorting.binarySearchFromTo(this.elements, paramFloat, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 111 */     FloatArrayList localFloatArrayList = new FloatArrayList((float[])this.elements.clone());
/* 112 */     localFloatArrayList.setSizeRaw(this.size);
/* 113 */     return localFloatArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FloatArrayList copy()
/*     */   {
/* 121 */     return (FloatArrayList)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float[] elements()
/*     */   {
/* 132 */     return this.elements;
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
/*     */   public AbstractFloatList elements(float[] paramArrayOfFloat)
/*     */   {
/* 145 */     this.elements = paramArrayOfFloat;
/* 146 */     this.size = paramArrayOfFloat.length;
/* 147 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 156 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/* 170 */     if (!(paramObject instanceof FloatArrayList)) return super.equals(paramObject);
/* 171 */     if (this == paramObject) return true;
/* 172 */     if (paramObject == null) return false;
/* 173 */     FloatArrayList localFloatArrayList = (FloatArrayList)paramObject;
/* 174 */     if (size() != localFloatArrayList.size()) { return false;
/*     */     }
/* 176 */     float[] arrayOfFloat1 = elements();
/* 177 */     float[] arrayOfFloat2 = localFloatArrayList.elements();
/* 178 */     int i = size();
/* 179 */     do { if (arrayOfFloat1[i] != arrayOfFloat2[i]) return false;
/* 178 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 181 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(FloatProcedure paramFloatProcedure)
/*     */   {
/* 191 */     float[] arrayOfFloat = this.elements;
/* 192 */     int i = this.size;
/*     */     
/* 194 */     for (int j = 0; j < i;) if (!paramFloatProcedure.apply(arrayOfFloat[(j++)])) return false;
/* 195 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float get(int paramInt)
/*     */   {
/* 206 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 207 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 208 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getQuick(int paramInt)
/*     */   {
/* 219 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(float paramFloat, int paramInt1, int paramInt2)
/*     */   {
/* 235 */     if (this.size == 0) return -1;
/* 236 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 238 */     float[] arrayOfFloat = this.elements;
/* 239 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 240 */       if (paramFloat == arrayOfFloat[i]) return i;
/*     */     }
/* 242 */     return -1;
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
/*     */   public int lastIndexOfFromTo(float paramFloat, int paramInt1, int paramInt2)
/*     */   {
/* 258 */     if (this.size == 0) return -1;
/* 259 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 261 */     float[] arrayOfFloat = this.elements;
/* 262 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 263 */       if (paramFloat == arrayOfFloat[i]) return i;
/*     */     }
/* 265 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractFloatList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 275 */     if (this.size == 0) { return new FloatArrayList(0);
/*     */     }
/* 277 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 279 */     float[] arrayOfFloat = new float[paramInt2 - paramInt1 + 1];
/* 280 */     System.arraycopy(this.elements, paramInt1, arrayOfFloat, 0, paramInt2 - paramInt1 + 1);
/* 281 */     return new FloatArrayList(arrayOfFloat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractFloatList paramAbstractFloatList)
/*     */   {
/* 292 */     if (!(paramAbstractFloatList instanceof FloatArrayList)) { return super.removeAll(paramAbstractFloatList);
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
/* 304 */     if (paramAbstractFloatList.size() == 0) return false;
/* 305 */     int i = paramAbstractFloatList.size() - 1;
/* 306 */     int j = 0;
/* 307 */     float[] arrayOfFloat = this.elements;
/* 308 */     int k = size();
/*     */     
/* 310 */     double d1 = paramAbstractFloatList.size();
/* 311 */     double d2 = k;
/* 312 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 314 */       FloatArrayList localFloatArrayList = (FloatArrayList)paramAbstractFloatList.clone();
/* 315 */       localFloatArrayList.quickSort();
/*     */       
/* 317 */       for (int n = 0; n < k; n++) {
/* 318 */         if (localFloatArrayList.binarySearchFromTo(arrayOfFloat[n], 0, i) < 0) arrayOfFloat[(j++)] = arrayOfFloat[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 323 */       for (m = 0; m < k; m++) {
/* 324 */         if (paramAbstractFloatList.indexOfFromTo(arrayOfFloat[m], 0, i) < 0) { arrayOfFloat[(j++)] = arrayOfFloat[m];
/*     */         }
/*     */       }
/*     */     }
/* 328 */     int m = j != k ? 1 : 0;
/* 329 */     setSize(j);
/* 330 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractFloatList paramAbstractFloatList, int paramInt3)
/*     */   {
/* 344 */     if (!(paramAbstractFloatList instanceof FloatArrayList))
/*     */     {
/* 346 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractFloatList, paramInt3);
/* 347 */       return;
/*     */     }
/* 349 */     int i = paramInt2 - paramInt1 + 1;
/* 350 */     if (i > 0) {
/* 351 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 352 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractFloatList.size());
/* 353 */       System.arraycopy(((FloatArrayList)paramAbstractFloatList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractFloatList paramAbstractFloatList)
/*     */   {
/* 365 */     if (!(paramAbstractFloatList instanceof FloatArrayList)) { return super.retainAll(paramAbstractFloatList);
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
/* 377 */     int i = paramAbstractFloatList.size() - 1;
/* 378 */     int j = 0;
/* 379 */     float[] arrayOfFloat = this.elements;
/* 380 */     int k = size();
/*     */     
/* 382 */     double d1 = paramAbstractFloatList.size();
/* 383 */     double d2 = k;
/* 384 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 386 */       FloatArrayList localFloatArrayList = (FloatArrayList)paramAbstractFloatList.clone();
/* 387 */       localFloatArrayList.quickSort();
/*     */       
/* 389 */       for (int n = 0; n < k; n++) {
/* 390 */         if (localFloatArrayList.binarySearchFromTo(arrayOfFloat[n], 0, i) >= 0) arrayOfFloat[(j++)] = arrayOfFloat[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 395 */       for (m = 0; m < k; m++) {
/* 396 */         if (paramAbstractFloatList.indexOfFromTo(arrayOfFloat[m], 0, i) >= 0) { arrayOfFloat[(j++)] = arrayOfFloat[m];
/*     */         }
/*     */       }
/*     */     }
/* 400 */     int m = j != k ? 1 : 0;
/* 401 */     setSize(j);
/* 402 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 411 */     int i = this.size / 2;
/* 412 */     int j = this.size - 1;
/*     */     
/* 414 */     float[] arrayOfFloat = this.elements;
/* 415 */     for (int k = 0; k < i;) {
/* 416 */       float f = arrayOfFloat[k];
/* 417 */       arrayOfFloat[(k++)] = arrayOfFloat[j];
/* 418 */       arrayOfFloat[(j--)] = f;
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
/*     */   public void set(int paramInt, float paramFloat)
/*     */   {
/* 431 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 432 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 433 */     this.elements[paramInt] = paramFloat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, float paramFloat)
/*     */   {
/* 445 */     this.elements[paramInt] = paramFloat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 455 */     if (this.size == 0) return;
/* 456 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 458 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 460 */     float[] arrayOfFloat = this.elements;
/*     */     
/* 462 */     for (int j = paramInt1; j < paramInt2; j++) {
/* 463 */       int i = localUniform.nextIntFromTo(j, paramInt2);
/*     */       
/*     */ 
/* 466 */       float f = arrayOfFloat[i];
/* 467 */       arrayOfFloat[i] = arrayOfFloat[j];
/* 468 */       arrayOfFloat[j] = f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 477 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/FloatArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */