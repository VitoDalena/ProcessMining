/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMatrix3D
/*     */   extends AbstractMatrix
/*     */ {
/*     */   protected int slices;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int rows;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int columns;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int sliceStride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int rowStride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int columnStride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int sliceZero;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int rowZero;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int columnZero;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/*  55 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnRank(int paramInt)
/*     */   {
/*  64 */     return this.columnZero + paramInt * this.columnStride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/*  74 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowRank(int paramInt)
/*     */   {
/*  83 */     return this.rowZero + paramInt * this.rowStride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _sliceOffset(int paramInt)
/*     */   {
/*  93 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _sliceRank(int paramInt)
/*     */   {
/* 102 */     return this.sliceZero + paramInt * this.sliceStride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void checkBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 109 */     if ((paramInt1 < 0) || (paramInt4 < 0) || (paramInt1 + paramInt4 > this.slices) || (paramInt2 < 0) || (paramInt5 < 0) || (paramInt2 + paramInt5 > this.rows) || (paramInt3 < 0) || (paramInt6 < 0) || (paramInt3 + paramInt6 > this.columns)) { throw new IndexOutOfBoundsException(toStringShort() + ", slice:" + paramInt1 + ", row:" + paramInt2 + " ,column:" + paramInt3 + ", depth:" + paramInt4 + " ,height:" + paramInt5 + ", width:" + paramInt6);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkColumn(int paramInt)
/*     */   {
/* 116 */     if ((paramInt < 0) || (paramInt >= this.columns)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at column=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkColumnIndexes(int[] paramArrayOfInt)
/*     */   {
/* 123 */     int i = paramArrayOfInt.length;
/* 124 */     do { int j = paramArrayOfInt[i];
/* 125 */       if ((j < 0) || (j >= this.columns)) checkColumn(j);
/* 123 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkRow(int paramInt)
/*     */   {
/* 133 */     if ((paramInt < 0) || (paramInt >= this.rows)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at row=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkRowIndexes(int[] paramArrayOfInt)
/*     */   {
/* 140 */     int i = paramArrayOfInt.length;
/* 141 */     do { int j = paramArrayOfInt[i];
/* 142 */       if ((j < 0) || (j >= this.rows)) checkRow(j);
/* 140 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void checkShape(AbstractMatrix3D paramAbstractMatrix3D)
/*     */   {
/* 150 */     if ((this.slices != paramAbstractMatrix3D.slices) || (this.rows != paramAbstractMatrix3D.rows) || (this.columns != paramAbstractMatrix3D.columns)) { throw new IllegalArgumentException("Incompatible dimensions: " + toStringShort() + " and " + paramAbstractMatrix3D.toStringShort());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void checkShape(AbstractMatrix3D paramAbstractMatrix3D1, AbstractMatrix3D paramAbstractMatrix3D2)
/*     */   {
/* 157 */     if ((this.slices != paramAbstractMatrix3D1.slices) || (this.rows != paramAbstractMatrix3D1.rows) || (this.columns != paramAbstractMatrix3D1.columns) || (this.slices != paramAbstractMatrix3D2.slices) || (this.rows != paramAbstractMatrix3D2.rows) || (this.columns != paramAbstractMatrix3D2.columns)) { throw new IllegalArgumentException("Incompatible dimensions: " + toStringShort() + ", " + paramAbstractMatrix3D1.toStringShort() + ", " + paramAbstractMatrix3D2.toStringShort());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkSlice(int paramInt)
/*     */   {
/* 164 */     if ((paramInt < 0) || (paramInt >= this.slices)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at slice=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkSliceIndexes(int[] paramArrayOfInt)
/*     */   {
/* 171 */     int i = paramArrayOfInt.length;
/* 172 */     do { int j = paramArrayOfInt[i];
/* 173 */       if ((j < 0) || (j >= this.slices)) checkSlice(j);
/* 171 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int columns()
/*     */   {
/* 180 */     return this.columns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 190 */     return _sliceOffset(_sliceRank(paramInt1)) + _rowOffset(_rowRank(paramInt2)) + _columnOffset(_columnRank(paramInt3));
/*     */   }
/*     */   
/*     */ 
/*     */   public int rows()
/*     */   {
/* 196 */     return this.rows;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 207 */     setUp(paramInt1, paramInt2, paramInt3, 0, 0, 0, paramInt2 * paramInt3, paramInt3, 1);
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
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
/*     */   {
/* 224 */     if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt3 < 0)) throw new IllegalArgumentException("negative size");
/* 225 */     if (paramInt1 * paramInt2 * paramInt3 > 2.147483647E9D) { throw new IllegalArgumentException("matrix too large");
/*     */     }
/* 227 */     this.slices = paramInt1;
/* 228 */     this.rows = paramInt2;
/* 229 */     this.columns = paramInt3;
/*     */     
/* 231 */     this.sliceZero = paramInt4;
/* 232 */     this.rowZero = paramInt5;
/* 233 */     this.columnZero = paramInt6;
/*     */     
/* 235 */     this.sliceStride = paramInt7;
/* 236 */     this.rowStride = paramInt8;
/* 237 */     this.columnStride = paramInt9;
/*     */     
/* 239 */     this.isNoView = true;
/*     */   }
/*     */   
/* 242 */   protected int[] shape() { int[] arrayOfInt = new int[3];
/* 243 */     arrayOfInt[0] = this.slices;
/* 244 */     arrayOfInt[1] = this.rows;
/* 245 */     arrayOfInt[2] = this.columns;
/* 246 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 252 */     return this.slices * this.rows * this.columns;
/*     */   }
/*     */   
/*     */ 
/*     */   public int slices()
/*     */   {
/* 258 */     return this.slices;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toStringShort()
/*     */   {
/* 264 */     return AbstractFormatter.shape(this);
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix3D vColumnFlip()
/*     */   {
/* 270 */     if (this.columns > 0) {
/* 271 */       this.columnZero += (this.columns - 1) * this.columnStride;
/* 272 */       this.columnStride = (-this.columnStride);
/* 273 */       this.isNoView = false;
/*     */     }
/* 275 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix3D vDice(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 282 */     int i = 3;
/* 283 */     if ((paramInt1 < 0) || (paramInt1 >= i) || (paramInt2 < 0) || (paramInt2 >= i) || (paramInt3 < 0) || (paramInt3 >= i) || (paramInt1 == paramInt2) || (paramInt1 == paramInt3) || (paramInt2 == paramInt3))
/*     */     {
/* 285 */       throw new IllegalArgumentException("Illegal Axes: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3);
/*     */     }
/*     */     
/*     */ 
/* 289 */     int[] arrayOfInt1 = shape();
/*     */     
/* 291 */     this.slices = arrayOfInt1[paramInt1];
/* 292 */     this.rows = arrayOfInt1[paramInt2];
/* 293 */     this.columns = arrayOfInt1[paramInt3];
/*     */     
/*     */ 
/* 296 */     int[] arrayOfInt2 = new int[3];
/* 297 */     arrayOfInt2[0] = this.sliceStride;
/* 298 */     arrayOfInt2[1] = this.rowStride;
/* 299 */     arrayOfInt2[2] = this.columnStride;
/*     */     
/* 301 */     this.sliceStride = arrayOfInt2[paramInt1];
/* 302 */     this.rowStride = arrayOfInt2[paramInt2];
/* 303 */     this.columnStride = arrayOfInt2[paramInt3];
/*     */     
/* 305 */     this.isNoView = false;
/* 306 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix3D vPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 313 */     checkBox(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*     */     
/* 315 */     this.sliceZero += this.sliceStride * paramInt1;
/* 316 */     this.rowZero += this.rowStride * paramInt2;
/* 317 */     this.columnZero += this.columnStride * paramInt3;
/*     */     
/* 319 */     this.slices = paramInt4;
/* 320 */     this.rows = paramInt5;
/* 321 */     this.columns = paramInt6;
/*     */     
/* 323 */     this.isNoView = false;
/* 324 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix3D vRowFlip()
/*     */   {
/* 330 */     if (this.rows > 0) {
/* 331 */       this.rowZero += (this.rows - 1) * this.rowStride;
/* 332 */       this.rowStride = (-this.rowStride);
/* 333 */       this.isNoView = false;
/*     */     }
/* 335 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix3D vSliceFlip()
/*     */   {
/* 341 */     if (this.slices > 0) {
/* 342 */       this.sliceZero += (this.slices - 1) * this.sliceStride;
/* 343 */       this.sliceStride = (-this.sliceStride);
/* 344 */       this.isNoView = false;
/*     */     }
/* 346 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix3D vStrides(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 353 */     if ((paramInt1 <= 0) || (paramInt2 <= 0) || (paramInt3 <= 0)) { throw new IndexOutOfBoundsException("illegal strides: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3);
/*     */     }
/* 355 */     this.sliceStride *= paramInt1;
/* 356 */     this.rowStride *= paramInt2;
/* 357 */     this.columnStride *= paramInt3;
/*     */     
/* 359 */     if (this.slices != 0) this.slices = ((this.slices - 1) / paramInt1 + 1);
/* 360 */     if (this.rows != 0) this.rows = ((this.rows - 1) / paramInt2 + 1);
/* 361 */     if (this.columns != 0) { this.columns = ((this.columns - 1) / paramInt3 + 1);
/*     */     }
/* 363 */     this.isNoView = false;
/* 364 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/AbstractMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */