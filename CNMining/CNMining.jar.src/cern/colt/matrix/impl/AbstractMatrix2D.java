/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMatrix2D
/*     */   extends AbstractMatrix
/*     */ {
/*     */   protected int columns;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int rows;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int rowStride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int columnStride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int rowZero;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int columnZero;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/*  58 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnRank(int paramInt)
/*     */   {
/*  67 */     return this.columnZero + paramInt * this.columnStride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/*  79 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowRank(int paramInt)
/*     */   {
/*  88 */     return this.rowZero + paramInt * this.rowStride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  97 */     if ((paramInt2 < 0) || (paramInt4 < 0) || (paramInt2 + paramInt4 > this.columns) || (paramInt1 < 0) || (paramInt3 < 0) || (paramInt1 + paramInt3 > this.rows)) { throw new IndexOutOfBoundsException(toStringShort() + ", column:" + paramInt2 + ", row:" + paramInt1 + " ,width:" + paramInt4 + ", height:" + paramInt3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkColumn(int paramInt)
/*     */   {
/* 104 */     if ((paramInt < 0) || (paramInt >= this.columns)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at column=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkColumnIndexes(int[] paramArrayOfInt)
/*     */   {
/* 111 */     int i = paramArrayOfInt.length;
/* 112 */     do { int j = paramArrayOfInt[i];
/* 113 */       if ((j < 0) || (j >= this.columns)) checkColumn(j);
/* 111 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkRow(int paramInt)
/*     */   {
/* 121 */     if ((paramInt < 0) || (paramInt >= this.rows)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at row=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkRowIndexes(int[] paramArrayOfInt)
/*     */   {
/* 128 */     int i = paramArrayOfInt.length;
/* 129 */     do { int j = paramArrayOfInt[i];
/* 130 */       if ((j < 0) || (j >= this.rows)) checkRow(j);
/* 128 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void checkShape(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 138 */     if ((this.columns != paramAbstractMatrix2D.columns) || (this.rows != paramAbstractMatrix2D.rows)) { throw new IllegalArgumentException("Incompatible dimensions: " + toStringShort() + " and " + paramAbstractMatrix2D.toStringShort());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void checkShape(AbstractMatrix2D paramAbstractMatrix2D1, AbstractMatrix2D paramAbstractMatrix2D2)
/*     */   {
/* 145 */     if ((this.columns != paramAbstractMatrix2D1.columns) || (this.rows != paramAbstractMatrix2D1.rows) || (this.columns != paramAbstractMatrix2D2.columns) || (this.rows != paramAbstractMatrix2D2.rows)) { throw new IllegalArgumentException("Incompatible dimensions: " + toStringShort() + ", " + paramAbstractMatrix2D1.toStringShort() + ", " + paramAbstractMatrix2D2.toStringShort());
/*     */     }
/*     */   }
/*     */   
/*     */   public int columns()
/*     */   {
/* 151 */     return this.columns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2)
/*     */   {
/* 160 */     return _rowOffset(_rowRank(paramInt1)) + _columnOffset(_columnRank(paramInt2));
/*     */   }
/*     */   
/*     */ 
/*     */   public int rows()
/*     */   {
/* 166 */     return this.rows;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2)
/*     */   {
/* 175 */     setUp(paramInt1, paramInt2, 0, 0, paramInt2, 1);
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
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 188 */     if ((paramInt1 < 0) || (paramInt2 < 0)) throw new IllegalArgumentException("negative size");
/* 189 */     if (paramInt2 * paramInt1 > 2.147483647E9D) throw new IllegalArgumentException("matrix too large");
/* 190 */     this.rows = paramInt1;
/* 191 */     this.columns = paramInt2;
/*     */     
/* 193 */     this.rowZero = paramInt3;
/* 194 */     this.columnZero = paramInt4;
/*     */     
/* 196 */     this.rowStride = paramInt5;
/* 197 */     this.columnStride = paramInt6;
/*     */     
/* 199 */     this.isNoView = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 205 */     return this.rows * this.columns;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toStringShort()
/*     */   {
/* 211 */     return AbstractFormatter.shape(this);
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix2D vColumnFlip()
/*     */   {
/* 217 */     if (this.columns > 0) {
/* 218 */       this.columnZero += (this.columns - 1) * this.columnStride;
/* 219 */       this.columnStride = (-this.columnStride);
/* 220 */       this.isNoView = false;
/*     */     }
/* 222 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix2D vDice()
/*     */   {
/* 230 */     int i = this.rows;this.rows = this.columns;this.columns = i;
/* 231 */     i = this.rowStride;this.rowStride = this.columnStride;this.columnStride = i;
/* 232 */     i = this.rowZero;this.rowZero = this.columnZero;this.columnZero = i;
/*     */     
/*     */ 
/*     */ 
/* 236 */     this.isNoView = false;
/* 237 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix2D vPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 244 */     checkBox(paramInt1, paramInt2, paramInt3, paramInt4);
/* 245 */     this.rowZero += this.rowStride * paramInt1;
/* 246 */     this.columnZero += this.columnStride * paramInt2;
/* 247 */     this.rows = paramInt3;
/* 248 */     this.columns = paramInt4;
/* 249 */     this.isNoView = false;
/* 250 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix2D vRowFlip()
/*     */   {
/* 256 */     if (this.rows > 0) {
/* 257 */       this.rowZero += (this.rows - 1) * this.rowStride;
/* 258 */       this.rowStride = (-this.rowStride);
/* 259 */       this.isNoView = false;
/*     */     }
/* 261 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix2D vStrides(int paramInt1, int paramInt2)
/*     */   {
/* 268 */     if ((paramInt1 <= 0) || (paramInt2 <= 0)) throw new IndexOutOfBoundsException("illegal strides: " + paramInt1 + ", " + paramInt2);
/* 269 */     this.rowStride *= paramInt1;
/* 270 */     this.columnStride *= paramInt2;
/* 271 */     if (this.rows != 0) this.rows = ((this.rows - 1) / paramInt1 + 1);
/* 272 */     if (this.columns != 0) this.columns = ((this.columns - 1) / paramInt2 + 1);
/* 273 */     this.isNoView = false;
/* 274 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/AbstractMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */