/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.Double27Function;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DenseDoubleMatrix3D
/*     */   extends DoubleMatrix3D
/*     */ {
/*     */   protected double[] elements;
/*     */   
/*     */   public DenseDoubleMatrix3D(double[][][] paramArrayOfDouble)
/*     */   {
/*  78 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length, paramArrayOfDouble[0].length == 0 ? 0 : paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0][0].length);
/*  79 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseDoubleMatrix3D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  91 */     setUp(paramInt1, paramInt2, paramInt3);
/*  92 */     this.elements = new double[paramInt1 * paramInt2 * paramInt3];
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
/*     */   protected DenseDoubleMatrix3D(int paramInt1, int paramInt2, int paramInt3, double[] paramArrayOfDouble, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
/*     */   {
/* 110 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9);
/* 111 */     this.elements = paramArrayOfDouble;
/* 112 */     this.isNoView = false;
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
/*     */   public DoubleMatrix3D assign(double[][][] paramArrayOfDouble)
/*     */   {
/* 127 */     if (this.isNoView) {
/* 128 */       if (paramArrayOfDouble.length != this.slices) throw new IllegalArgumentException("Must have same number of slices: slices=" + paramArrayOfDouble.length + "slices()=" + slices());
/* 129 */       int i = this.slices * this.rows * this.columns - this.columns;
/* 130 */       int j = this.slices;
/* 131 */       do { double[][] arrayOfDouble = paramArrayOfDouble[j];
/* 132 */         if (arrayOfDouble.length != this.rows) throw new IllegalArgumentException("Must have same number of rows in every slice: rows=" + arrayOfDouble.length + "rows()=" + rows());
/* 133 */         int k = this.rows;
/* 134 */         do { double[] arrayOfDouble1 = arrayOfDouble[k];
/* 135 */           if (arrayOfDouble1.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfDouble1.length + "columns()=" + columns());
/* 136 */           System.arraycopy(arrayOfDouble1, 0, this.elements, i, this.columns);
/* 137 */           i -= this.columns;k--;
/* 133 */         } while (k >= 0);
/* 130 */         j--; } while (j >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */       super.assign(paramArrayOfDouble);
/*     */     }
/* 144 */     return this;
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
/*     */   public DoubleMatrix3D assign(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 157 */     if (!(paramDoubleMatrix3D instanceof DenseDoubleMatrix3D)) {
/* 158 */       return super.assign(paramDoubleMatrix3D);
/*     */     }
/* 160 */     DenseDoubleMatrix3D localDenseDoubleMatrix3D = (DenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 161 */     if (localDenseDoubleMatrix3D == this) return this;
/* 162 */     checkShape(localDenseDoubleMatrix3D);
/* 163 */     if (haveSharedCells(localDenseDoubleMatrix3D)) {
/* 164 */       DoubleMatrix3D localDoubleMatrix3D = localDenseDoubleMatrix3D.copy();
/* 165 */       if (!(localDoubleMatrix3D instanceof DenseDoubleMatrix3D)) {
/* 166 */         return super.assign(paramDoubleMatrix3D);
/*     */       }
/* 168 */       localDenseDoubleMatrix3D = (DenseDoubleMatrix3D)localDoubleMatrix3D;
/*     */     }
/*     */     
/* 171 */     if ((this.isNoView) && (localDenseDoubleMatrix3D.isNoView)) {
/* 172 */       System.arraycopy(localDenseDoubleMatrix3D.elements, 0, this.elements, 0, this.elements.length);
/* 173 */       return this;
/*     */     }
/* 175 */     return super.assign(localDenseDoubleMatrix3D);
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
/*     */   public double getQuick(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 193 */     return this.elements[(this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 205 */     if ((paramDoubleMatrix3D instanceof SelectedDenseDoubleMatrix3D)) {
/* 206 */       localObject = (SelectedDenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 207 */       return this.elements == ((SelectedDenseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 209 */     if ((paramDoubleMatrix3D instanceof DenseDoubleMatrix3D)) {
/* 210 */       localObject = (DenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 211 */       return this.elements == ((DenseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 213 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 225 */     return this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride;
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
/*     */   public DoubleMatrix3D like(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 239 */     return new DenseDoubleMatrix3D(paramInt1, paramInt2, paramInt3);
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
/*     */   protected DoubleMatrix2D like2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 255 */     return new DenseDoubleMatrix2D(paramInt1, paramInt2, this.elements, paramInt3, paramInt4, paramInt5, paramInt6);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/* 273 */     this.elements[(this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride)] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix3D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/* 284 */     return new SelectedDenseDoubleMatrix3D(this.elements, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, 0);
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
/*     */   public void zAssign27Neighbors(DoubleMatrix3D paramDoubleMatrix3D, Double27Function paramDouble27Function)
/*     */   {
/* 346 */     if (!(paramDoubleMatrix3D instanceof DenseDoubleMatrix3D)) {
/* 347 */       super.zAssign27Neighbors(paramDoubleMatrix3D, paramDouble27Function);
/* 348 */       return;
/*     */     }
/* 350 */     if (paramDouble27Function == null) throw new NullPointerException("function must not be null.");
/* 351 */     checkShape(paramDoubleMatrix3D);
/* 352 */     int i = this.rows - 1;
/* 353 */     int j = this.columns - 1;
/* 354 */     if ((this.rows < 3) || (this.columns < 3) || (this.slices < 3)) { return;
/*     */     }
/* 356 */     DenseDoubleMatrix3D localDenseDoubleMatrix3D = (DenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 357 */     int k = this.sliceStride;
/* 358 */     int m = this.rowStride;
/* 359 */     int n = localDenseDoubleMatrix3D.rowStride;
/* 360 */     int i1 = this.columnStride;
/* 361 */     int i2 = localDenseDoubleMatrix3D.columnStride;
/* 362 */     double[] arrayOfDouble1 = this.elements;
/* 363 */     double[] arrayOfDouble2 = localDenseDoubleMatrix3D.elements;
/* 364 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null)) { throw new InternalError();
/*     */     }
/* 366 */     for (int i3 = 1; i3 < this.slices - 1; i3++) {
/* 367 */       int i4 = index(i3, 1, 1);
/* 368 */       int i5 = localDenseDoubleMatrix3D.index(i3, 1, 1);
/*     */       
/* 370 */       for (int i6 = 1; i6 < i; i6++) {
/* 371 */         int i7 = i4 - k - m - i1;
/* 372 */         int i8 = i7 + m;
/* 373 */         int i9 = i8 + m;
/*     */         
/* 375 */         int i10 = i7 + k;
/* 376 */         int i11 = i10 + m;
/* 377 */         int i12 = i11 + m;
/*     */         
/* 379 */         int i13 = i10 + k;
/* 380 */         int i14 = i13 + m;
/* 381 */         int i15 = i14 + m;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 395 */         double d1 = arrayOfDouble1[i7];i7 += i1;double d2 = arrayOfDouble1[i7];
/* 396 */         double d4 = arrayOfDouble1[i8];i8 += i1;double d5 = arrayOfDouble1[i8];
/* 397 */         double d7 = arrayOfDouble1[i9];i9 += i1;double d8 = arrayOfDouble1[i9];
/*     */         
/* 399 */         double d10 = arrayOfDouble1[i10];i10 += i1;double d11 = arrayOfDouble1[i10];
/* 400 */         double d13 = arrayOfDouble1[i11];i11 += i1;double d14 = arrayOfDouble1[i11];
/* 401 */         double d16 = arrayOfDouble1[i12];i12 += i1;double d17 = arrayOfDouble1[i12];
/*     */         
/* 403 */         double d19 = arrayOfDouble1[i13];i13 += i1;double d20 = arrayOfDouble1[i13];
/* 404 */         double d22 = arrayOfDouble1[i14];i14 += i1;double d23 = arrayOfDouble1[i14];
/* 405 */         double d25 = arrayOfDouble1[i15];i15 += i1;double d26 = arrayOfDouble1[i15];
/*     */         
/* 407 */         int i16 = i5;
/* 408 */         for (int i17 = 1; i17 < j; i17++)
/*     */         {
/*     */ 
/* 411 */           double d3 = arrayOfDouble1[(i7 += i1)];
/* 412 */           double d6 = arrayOfDouble1[(i8 += i1)];
/* 413 */           double d9 = arrayOfDouble1[(i9 += i1)];
/*     */           
/* 415 */           double d12 = arrayOfDouble1[(i10 += i1)];
/* 416 */           double d15 = arrayOfDouble1[(i11 += i1)];
/* 417 */           double d18 = arrayOfDouble1[(i12 += i1)];
/*     */           
/* 419 */           double d21 = arrayOfDouble1[(i13 += i1)];
/* 420 */           double d24 = arrayOfDouble1[(i14 += i1)];
/* 421 */           double d27 = arrayOfDouble1[(i15 += i1)];
/*     */           
/* 423 */           arrayOfDouble2[i16] = paramDouble27Function.apply(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 435 */           i16 += i2;
/*     */           
/*     */ 
/* 438 */           d1 = d2;d2 = d3;
/* 439 */           d4 = d5;d5 = d6;
/* 440 */           d7 = d8;d8 = d9;
/*     */           
/* 442 */           d10 = d11;d11 = d12;
/* 443 */           d13 = d14;d14 = d15;
/* 444 */           d16 = d17;d17 = d18;
/*     */           
/* 446 */           d19 = d20;d20 = d21;
/* 447 */           d22 = d23;d23 = d24;
/* 448 */           d25 = d26;d26 = d27;
/*     */         }
/* 450 */         i4 += m;
/* 451 */         i5 += n;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseDoubleMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */