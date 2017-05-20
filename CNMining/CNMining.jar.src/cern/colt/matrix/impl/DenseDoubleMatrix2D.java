/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.Double9Function;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.math.Mult;
/*     */ import cern.jet.math.PlusMult;
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
/*     */ 
/*     */ 
/*     */ public class DenseDoubleMatrix2D
/*     */   extends DoubleMatrix2D
/*     */ {
/*     */   static final long serialVersionUID = 1020177651L;
/*     */   protected double[] elements;
/*     */   
/*     */   public DenseDoubleMatrix2D(double[][] paramArrayOfDouble)
/*     */   {
/*  75 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length);
/*  76 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseDoubleMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/*  86 */     setUp(paramInt1, paramInt2);
/*  87 */     this.elements = new double[paramInt1 * paramInt2];
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
/*     */   protected DenseDoubleMatrix2D(int paramInt1, int paramInt2, double[] paramArrayOfDouble, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 101 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 102 */     this.elements = paramArrayOfDouble;
/* 103 */     this.isNoView = false;
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
/*     */   public DoubleMatrix2D assign(double[][] paramArrayOfDouble)
/*     */   {
/* 117 */     if (this.isNoView) {
/* 118 */       if (paramArrayOfDouble.length != this.rows) throw new IllegalArgumentException("Must have same number of rows: rows=" + paramArrayOfDouble.length + "rows()=" + rows());
/* 119 */       int i = this.columns * (this.rows - 1);
/* 120 */       int j = this.rows;
/* 121 */       do { double[] arrayOfDouble = paramArrayOfDouble[j];
/* 122 */         if (arrayOfDouble.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfDouble.length + "columns()=" + columns());
/* 123 */         System.arraycopy(arrayOfDouble, 0, this.elements, i, this.columns);
/* 124 */         i -= this.columns;j--;
/* 120 */       } while (j >= 0);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 128 */       super.assign(paramArrayOfDouble);
/*     */     }
/* 130 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D assign(double paramDouble)
/*     */   {
/* 138 */     double[] arrayOfDouble = this.elements;
/* 139 */     int i = index(0, 0);
/* 140 */     int j = this.columnStride;
/* 141 */     int k = this.rowStride;
/* 142 */     int m = this.rows;
/* 143 */     do { int n = i;int i1 = this.columns;
/* 144 */       do { arrayOfDouble[n] = paramDouble;
/* 145 */         n += j;i1--;
/* 143 */       } while (i1 >= 0);
/*     */       
/*     */ 
/*     */ 
/* 147 */       i += k;m--;
/* 142 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 149 */     return this;
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
/*     */   public DoubleMatrix2D assign(DoubleFunction paramDoubleFunction)
/*     */   {
/* 174 */     double[] arrayOfDouble = this.elements;
/* 175 */     if (arrayOfDouble == null) throw new InternalError();
/* 176 */     int i = index(0, 0);
/* 177 */     int j = this.columnStride;
/* 178 */     int k = this.rowStride;
/*     */     
/*     */     int i1;
/* 181 */     if ((paramDoubleFunction instanceof Mult)) {
/* 182 */       double d = ((Mult)paramDoubleFunction).multiplicator;
/* 183 */       if (d == 1.0D) return this;
/* 184 */       if (d == 0.0D) return assign(0.0D);
/* 185 */       i1 = this.rows;
/* 186 */       do { int i2 = i;int i3 = this.columns;
/* 187 */         do { arrayOfDouble[i2] *= d;
/* 188 */           i2 += j;i3--;
/* 186 */         } while (i3 >= 0);
/*     */         
/*     */ 
/*     */ 
/* 190 */         i += k;i1--;
/* 185 */       } while (i1 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 194 */       int m = this.rows;
/* 195 */       do { int n = i;i1 = this.columns;
/* 196 */         do { arrayOfDouble[n] = paramDoubleFunction.apply(arrayOfDouble[n]);
/* 197 */           n += j;i1--;
/* 195 */         } while (i1 >= 0);
/*     */         
/*     */ 
/*     */ 
/* 199 */         i += k;m--;
/* 194 */       } while (m >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 202 */     return this;
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
/*     */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 215 */     if (!(paramDoubleMatrix2D instanceof DenseDoubleMatrix2D)) {
/* 216 */       return super.assign(paramDoubleMatrix2D);
/*     */     }
/* 218 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = (DenseDoubleMatrix2D)paramDoubleMatrix2D;
/* 219 */     if (localDenseDoubleMatrix2D == this) return this;
/* 220 */     checkShape(localDenseDoubleMatrix2D);
/*     */     
/* 222 */     if ((this.isNoView) && (localDenseDoubleMatrix2D.isNoView)) {
/* 223 */       System.arraycopy(localDenseDoubleMatrix2D.elements, 0, this.elements, 0, this.elements.length);
/* 224 */       return this;
/*     */     }
/*     */     
/* 227 */     if (haveSharedCells(localDenseDoubleMatrix2D)) {
/* 228 */       localObject = localDenseDoubleMatrix2D.copy();
/* 229 */       if (!(localObject instanceof DenseDoubleMatrix2D)) {
/* 230 */         return super.assign(localDenseDoubleMatrix2D);
/*     */       }
/* 232 */       localDenseDoubleMatrix2D = (DenseDoubleMatrix2D)localObject;
/*     */     }
/*     */     
/* 235 */     Object localObject = this.elements;
/* 236 */     double[] arrayOfDouble = localDenseDoubleMatrix2D.elements;
/* 237 */     if ((localObject == null) || (arrayOfDouble == null)) throw new InternalError();
/* 238 */     int i = this.columnStride;
/* 239 */     int j = localDenseDoubleMatrix2D.columnStride;
/* 240 */     int k = this.rowStride;
/* 241 */     int m = localDenseDoubleMatrix2D.rowStride;
/*     */     
/* 243 */     int n = localDenseDoubleMatrix2D.index(0, 0);
/* 244 */     int i1 = index(0, 0);
/* 245 */     int i2 = this.rows;
/* 246 */     do { int i3 = i1;int i4 = n;int i5 = this.columns;
/* 247 */       do { localObject[i3] = arrayOfDouble[i4];
/* 248 */         i3 += i;
/* 249 */         i4 += j;i5--;
/* 246 */       } while (i5 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 251 */       i1 += k;
/* 252 */       n += m;i2--;
/* 245 */     } while (i2 >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 254 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 287 */     if (!(paramDoubleMatrix2D instanceof DenseDoubleMatrix2D)) {
/* 288 */       return super.assign(paramDoubleMatrix2D, paramDoubleDoubleFunction);
/*     */     }
/* 290 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = (DenseDoubleMatrix2D)paramDoubleMatrix2D;
/* 291 */     checkShape(paramDoubleMatrix2D);
/*     */     
/* 293 */     double[] arrayOfDouble1 = this.elements;
/* 294 */     double[] arrayOfDouble2 = localDenseDoubleMatrix2D.elements;
/* 295 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null)) throw new InternalError();
/* 296 */     int i = this.columnStride;
/* 297 */     int j = localDenseDoubleMatrix2D.columnStride;
/* 298 */     int k = this.rowStride;
/* 299 */     int m = localDenseDoubleMatrix2D.rowStride;
/*     */     
/* 301 */     int n = localDenseDoubleMatrix2D.index(0, 0);
/* 302 */     int i1 = index(0, 0);
/*     */     int i2;
/*     */     int i4;
/* 305 */     int i5; int i6; if (paramDoubleDoubleFunction == Functions.mult) {
/* 306 */       i2 = this.rows;
/* 307 */       do { i4 = i1;i5 = n;i6 = this.columns;
/* 308 */         do { arrayOfDouble1[i4] *= arrayOfDouble2[i5];
/* 309 */           i4 += i;
/* 310 */           i5 += j;i6--;
/* 307 */         } while (i6 >= 0);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 312 */         i1 += k;
/* 313 */         n += m;i2--;
/* 306 */       } while (i2 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 316 */     else if (paramDoubleDoubleFunction == Functions.div) {
/* 317 */       i2 = this.rows;
/* 318 */       do { i4 = i1;i5 = n;i6 = this.columns;
/* 319 */         do { arrayOfDouble1[i4] /= arrayOfDouble2[i5];
/* 320 */           i4 += i;
/* 321 */           i5 += j;i6--;
/* 318 */         } while (i6 >= 0);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 323 */         i1 += k;
/* 324 */         n += m;i2--;
/* 317 */       } while (i2 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 327 */     else if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 328 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 329 */       if (d == 0.0D)
/* 330 */         return this;
/*     */       int i7;
/* 332 */       int i8; if (d == 1.0D) {
/* 333 */         i5 = this.rows;
/* 334 */         do { i6 = i1;i7 = n;i8 = this.columns;
/* 335 */           do { arrayOfDouble1[i6] += arrayOfDouble2[i7];
/* 336 */             i6 += i;
/* 337 */             i7 += j;i8--;
/* 334 */           } while (i8 >= 0);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 339 */           i1 += k;
/* 340 */           n += m;i5--;
/* 333 */         } while (i5 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 343 */       else if (d == -1.0D) {
/* 344 */         i5 = this.rows;
/* 345 */         do { i6 = i1;i7 = n;i8 = this.columns;
/* 346 */           do { arrayOfDouble1[i6] -= arrayOfDouble2[i7];
/* 347 */             i6 += i;
/* 348 */             i7 += j;i8--;
/* 345 */           } while (i8 >= 0);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 350 */           i1 += k;
/* 351 */           n += m;i5--;
/* 344 */         } while (i5 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 355 */         i5 = this.rows;
/* 356 */         do { i6 = i1;i7 = n;i8 = this.columns;
/* 357 */           do { arrayOfDouble1[i6] += d * arrayOfDouble2[i7];
/* 358 */             i6 += i;
/* 359 */             i7 += j;i8--;
/* 356 */           } while (i8 >= 0);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 361 */           i1 += k;
/* 362 */           n += m;i5--;
/* 355 */         } while (i5 >= 0);
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 367 */       int i3 = this.rows;
/* 368 */       do { i4 = i1;i5 = n;i6 = this.columns;
/* 369 */         do { arrayOfDouble1[i4] = paramDoubleDoubleFunction.apply(arrayOfDouble1[i4], arrayOfDouble2[i5]);
/* 370 */           i4 += i;
/* 371 */           i5 += j;i6--;
/* 368 */         } while (i6 >= 0);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 373 */         i1 += k;
/* 374 */         n += m;i3--;
/* 367 */       } while (i3 >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 377 */     return this;
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
/*     */   public double getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 394 */     return this.elements[(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 406 */     if ((paramDoubleMatrix2D instanceof SelectedDenseDoubleMatrix2D)) {
/* 407 */       localObject = (SelectedDenseDoubleMatrix2D)paramDoubleMatrix2D;
/* 408 */       return this.elements == ((SelectedDenseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 410 */     if ((paramDoubleMatrix2D instanceof DenseDoubleMatrix2D)) {
/* 411 */       localObject = (DenseDoubleMatrix2D)paramDoubleMatrix2D;
/* 412 */       return this.elements == ((DenseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 414 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2)
/*     */   {
/* 425 */     return this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
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
/*     */   public DoubleMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 438 */     return new DenseDoubleMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like1D(int paramInt)
/*     */   {
/* 449 */     return new DenseDoubleMatrix1D(paramInt);
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
/*     */   protected DoubleMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 462 */     return new DenseDoubleMatrix1D(paramInt1, this.elements, paramInt2, paramInt3);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 479 */     this.elements[(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride)] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 489 */     return new SelectedDenseDoubleMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, 0);
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
/*     */   public void zAssign8Neighbors(DoubleMatrix2D paramDoubleMatrix2D, Double9Function paramDouble9Function)
/*     */   {
/* 551 */     if (!(paramDoubleMatrix2D instanceof DenseDoubleMatrix2D)) {
/* 552 */       super.zAssign8Neighbors(paramDoubleMatrix2D, paramDouble9Function);
/* 553 */       return;
/*     */     }
/* 555 */     if (paramDouble9Function == null) throw new NullPointerException("function must not be null.");
/* 556 */     checkShape(paramDoubleMatrix2D);
/* 557 */     int i = this.rows - 1;
/* 558 */     int j = this.columns - 1;
/* 559 */     if ((this.rows < 3) || (this.columns < 3)) { return;
/*     */     }
/* 561 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = (DenseDoubleMatrix2D)paramDoubleMatrix2D;
/* 562 */     int k = this.rowStride;
/* 563 */     int m = localDenseDoubleMatrix2D.rowStride;
/* 564 */     int n = this.columnStride;
/* 565 */     int i1 = localDenseDoubleMatrix2D.columnStride;
/* 566 */     double[] arrayOfDouble1 = this.elements;
/* 567 */     double[] arrayOfDouble2 = localDenseDoubleMatrix2D.elements;
/* 568 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null)) { throw new InternalError();
/*     */     }
/* 570 */     int i2 = index(1, 1);
/* 571 */     int i3 = localDenseDoubleMatrix2D.index(1, 1);
/* 572 */     for (int i4 = 1; i4 < i; i4++)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 577 */       int i5 = i3;
/*     */       
/* 579 */       int i6 = i2 - k - n;
/* 580 */       int i7 = i6 + k;
/* 581 */       int i8 = i7 + k;
/*     */       
/*     */ 
/* 584 */       double d1 = arrayOfDouble1[i6];i6 += n;double d2 = arrayOfDouble1[i6];
/* 585 */       double d4 = arrayOfDouble1[i7];i7 += n;double d5 = arrayOfDouble1[i7];
/* 586 */       double d7 = arrayOfDouble1[i8];i8 += n;double d8 = arrayOfDouble1[i8];
/*     */       
/* 588 */       for (int i9 = 1; i9 < j; i9++)
/*     */       {
/* 590 */         double d3 = arrayOfDouble1[(i6 += n)];
/* 591 */         double d6 = arrayOfDouble1[(i7 += n)];
/* 592 */         double d9 = arrayOfDouble1[(i8 += n)];
/*     */         
/* 594 */         arrayOfDouble2[i5] = paramDouble9Function.apply(d1, d2, d3, d4, d5, d6, d7, d8, d9);
/*     */         
/*     */ 
/*     */ 
/* 598 */         i5 += i1;
/*     */         
/*     */ 
/* 601 */         d1 = d2;d2 = d3;
/* 602 */         d4 = d5;d5 = d6;
/* 603 */         d7 = d8;d8 = d9;
/*     */       }
/* 605 */       i2 += k;
/* 606 */       i3 += m;
/*     */     }
/*     */   }
/*     */   
/*     */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2, boolean paramBoolean) {
/* 611 */     if (paramBoolean) return viewDice().zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, false);
/* 612 */     if (paramDoubleMatrix1D2 == null) paramDoubleMatrix1D2 = new DenseDoubleMatrix1D(this.rows);
/* 613 */     if ((!(paramDoubleMatrix1D1 instanceof DenseDoubleMatrix1D)) || (!(paramDoubleMatrix1D2 instanceof DenseDoubleMatrix1D))) { return super.zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, paramBoolean);
/*     */     }
/* 615 */     if ((this.columns != paramDoubleMatrix1D1.size) || (this.rows > paramDoubleMatrix1D2.size)) {
/* 616 */       throw new IllegalArgumentException("Incompatible args: " + toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 618 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D1 = (DenseDoubleMatrix1D)paramDoubleMatrix1D1;
/* 619 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D2 = (DenseDoubleMatrix1D)paramDoubleMatrix1D2;
/* 620 */     double[] arrayOfDouble1 = this.elements;
/* 621 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D1.elements;
/* 622 */     double[] arrayOfDouble3 = localDenseDoubleMatrix1D2.elements;
/* 623 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null) || (arrayOfDouble3 == null)) throw new InternalError();
/* 624 */     int i = this.columnStride;
/* 625 */     int j = localDenseDoubleMatrix1D1.stride;
/* 626 */     int k = localDenseDoubleMatrix1D2.stride;
/*     */     
/* 628 */     int m = index(0, 0);
/* 629 */     int n = localDenseDoubleMatrix1D1.index(0);
/* 630 */     int i1 = localDenseDoubleMatrix1D2.index(0);
/*     */     
/* 632 */     int i2 = this.columns;
/* 633 */     int i3 = this.rows;
/* 634 */     do { double d = 0.0D;
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
/* 646 */       int i4 = m - i;
/* 647 */       int i5 = n - j;
/* 648 */       int i6 = i2 % 4;
/* 649 */       do { d += arrayOfDouble1[(i4 += i)] * arrayOfDouble2[(i5 += j)];i6--;
/* 648 */       } while (i6 >= 0);
/*     */       
/*     */ 
/* 651 */       int i7 = i2 / 4;
/* 652 */       do { d += arrayOfDouble1[(i4 += i)] * arrayOfDouble2[(i5 += j)] + arrayOfDouble1[(i4 += i)] * arrayOfDouble2[(i5 += j)] + arrayOfDouble1[(i4 += i)] * arrayOfDouble2[(i5 += j)] + arrayOfDouble1[(i4 += i)] * arrayOfDouble2[(i5 += j)];i7--;
/* 651 */       } while (i7 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 658 */       arrayOfDouble3[i1] = (paramDouble1 * d + paramDouble2 * arrayOfDouble3[i1]);
/* 659 */       m += this.rowStride;
/* 660 */       i1 += k;i3--;
/* 633 */     } while (i3 >= 0);
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
/* 663 */     return paramDoubleMatrix1D2;
/*     */   }
/*     */   
/*     */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2) {
/* 667 */     if (paramBoolean1) return viewDice().zMult(paramDoubleMatrix2D1, paramDoubleMatrix2D2, paramDouble1, paramDouble2, false, paramBoolean2);
/* 668 */     if (((paramDoubleMatrix2D1 instanceof SparseDoubleMatrix2D)) || ((paramDoubleMatrix2D1 instanceof RCDoubleMatrix2D)))
/*     */     {
/*     */ 
/* 671 */       if (paramDoubleMatrix2D2 == null) {
/* 672 */         return paramDoubleMatrix2D1.zMult(this, null, paramDouble1, paramDouble2, !paramBoolean2, true).viewDice();
/*     */       }
/*     */       
/* 675 */       paramDoubleMatrix2D1.zMult(this, paramDoubleMatrix2D2.viewDice(), paramDouble1, paramDouble2, !paramBoolean2, true);
/* 676 */       return paramDoubleMatrix2D2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 692 */     if (paramBoolean2) { return zMult(paramDoubleMatrix2D1.viewDice(), paramDoubleMatrix2D2, paramDouble1, paramDouble2, paramBoolean1, false);
/*     */     }
/* 694 */     int i = this.rows;
/* 695 */     int j = this.columns;
/* 696 */     int k = paramDoubleMatrix2D1.columns;
/* 697 */     if (paramDoubleMatrix2D2 == null) paramDoubleMatrix2D2 = new DenseDoubleMatrix2D(i, k);
/* 698 */     if (!(paramDoubleMatrix2D2 instanceof DenseDoubleMatrix2D)) return super.zMult(paramDoubleMatrix2D1, paramDoubleMatrix2D2, paramDouble1, paramDouble2, paramBoolean1, paramBoolean2);
/* 699 */     if (paramDoubleMatrix2D1.rows != j)
/* 700 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + toStringShort() + ", " + paramDoubleMatrix2D1.toStringShort());
/* 701 */     if ((paramDoubleMatrix2D2.rows != i) || (paramDoubleMatrix2D2.columns != k))
/* 702 */       throw new IllegalArgumentException("Incompatibel result matrix: " + toStringShort() + ", " + paramDoubleMatrix2D1.toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/* 703 */     if ((this == paramDoubleMatrix2D2) || (paramDoubleMatrix2D1 == paramDoubleMatrix2D2)) {
/* 704 */       throw new IllegalArgumentException("Matrices must not be identical");
/*     */     }
/* 706 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D1 = (DenseDoubleMatrix2D)paramDoubleMatrix2D1;
/* 707 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D2 = (DenseDoubleMatrix2D)paramDoubleMatrix2D2;
/* 708 */     double[] arrayOfDouble1 = this.elements;
/* 709 */     double[] arrayOfDouble2 = localDenseDoubleMatrix2D1.elements;
/* 710 */     double[] arrayOfDouble3 = localDenseDoubleMatrix2D2.elements;
/* 711 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null) || (arrayOfDouble3 == null)) { throw new InternalError();
/*     */     }
/* 713 */     int m = this.columnStride;
/* 714 */     int n = localDenseDoubleMatrix2D1.columnStride;
/* 715 */     int i1 = localDenseDoubleMatrix2D2.columnStride;
/*     */     
/* 717 */     int i2 = this.rowStride;
/* 718 */     int i3 = localDenseDoubleMatrix2D1.rowStride;
/* 719 */     int i4 = localDenseDoubleMatrix2D2.rowStride;
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
/* 738 */     int i5 = (30000 - j) / (j + 1);
/* 739 */     if (i5 <= 0) i5 = 1;
/* 740 */     int i6 = i / i5;
/* 741 */     int i7 = 0;
/* 742 */     if (i % i5 != 0) i6++;
/*     */     do {
/* 744 */       int i8 = localDenseDoubleMatrix2D1.index(0, 0);
/* 745 */       int i9 = index(i7, 0);
/* 746 */       int i10 = localDenseDoubleMatrix2D2.index(i7, 0);
/* 747 */       i7 += i5;
/* 748 */       if (i6 == 0) { i5 += i - i7;
/*     */       }
/* 750 */       int i11 = k;
/* 751 */       do { int i12 = i9;
/* 752 */         int i13 = i10;
/* 753 */         int i14 = i5;
/* 754 */         do { int i15 = i12;
/* 755 */           int i16 = i8;
/* 756 */           double d = 0.0D;
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
/* 769 */           i15 -= m;
/* 770 */           i16 -= i3;
/*     */           
/* 772 */           int i17 = j % 4;
/* 773 */           do { d += arrayOfDouble1[(i15 += m)] * arrayOfDouble2[(i16 += i3)];i17--;
/* 772 */           } while (i17 >= 0);
/*     */           
/*     */ 
/* 775 */           int i18 = j / 4;
/* 776 */           do { d += arrayOfDouble1[(i15 += m)] * arrayOfDouble2[(i16 += i3)] + arrayOfDouble1[(i15 += m)] * arrayOfDouble2[(i16 += i3)] + arrayOfDouble1[(i15 += m)] * arrayOfDouble2[(i16 += i3)] + arrayOfDouble1[(i15 += m)] * arrayOfDouble2[(i16 += i3)];i18--;
/* 775 */           } while (i18 >= 0);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 782 */           arrayOfDouble3[i13] = (paramDouble1 * d + paramDouble2 * arrayOfDouble3[i13]);
/* 783 */           i12 += i2;
/* 784 */           i13 += i4;i14--;
/* 753 */         } while (i14 >= 0);
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
/* 786 */         i8 += n;
/* 787 */         i10 += i1;i11--;
/* 750 */       } while (i11 >= 0);
/* 743 */       i6--; } while (i6 >= 0);
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
/* 790 */     return paramDoubleMatrix2D2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double zSum()
/*     */   {
/* 797 */     double d = 0.0D;
/* 798 */     double[] arrayOfDouble = this.elements;
/* 799 */     if (arrayOfDouble == null) throw new InternalError();
/* 800 */     int i = index(0, 0);
/* 801 */     int j = this.columnStride;
/* 802 */     int k = this.rowStride;
/* 803 */     int m = this.rows;
/* 804 */     do { int n = i;int i1 = this.columns;
/* 805 */       do { d += arrayOfDouble[n];
/* 806 */         n += j;i1--;
/* 804 */       } while (i1 >= 0);
/*     */       
/*     */ 
/*     */ 
/* 808 */       i += k;m--;
/* 803 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 810 */     return d;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */