/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.ObjectFunction;
/*     */ import cern.colt.function.ObjectObjectFunction;
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DenseObjectMatrix2D
/*     */   extends ObjectMatrix2D
/*     */ {
/*     */   protected Object[] elements;
/*     */   
/*     */   public DenseObjectMatrix2D(Object[][] paramArrayOfObject)
/*     */   {
/*  74 */     this(paramArrayOfObject.length, paramArrayOfObject.length == 0 ? 0 : paramArrayOfObject[0].length);
/*  75 */     assign(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseObjectMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/*  85 */     setUp(paramInt1, paramInt2);
/*  86 */     this.elements = new Object[paramInt1 * paramInt2];
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
/*     */   protected DenseObjectMatrix2D(int paramInt1, int paramInt2, Object[] paramArrayOfObject, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 100 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 101 */     this.elements = paramArrayOfObject;
/* 102 */     this.isNoView = false;
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
/*     */   public ObjectMatrix2D assign(Object[][] paramArrayOfObject)
/*     */   {
/* 116 */     if (this.isNoView) {
/* 117 */       if (paramArrayOfObject.length != this.rows) throw new IllegalArgumentException("Must have same number of rows: rows=" + paramArrayOfObject.length + "rows()=" + rows());
/* 118 */       int i = this.columns * (this.rows - 1);
/* 119 */       int j = this.rows;
/* 120 */       do { Object[] arrayOfObject = paramArrayOfObject[j];
/* 121 */         if (arrayOfObject.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfObject.length + "columns()=" + columns());
/* 122 */         System.arraycopy(arrayOfObject, 0, this.elements, i, this.columns);
/* 123 */         i -= this.columns;j--;
/* 119 */       } while (j >= 0);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 127 */       super.assign(paramArrayOfObject);
/*     */     }
/* 129 */     return this;
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
/*     */   public ObjectMatrix2D assign(ObjectFunction paramObjectFunction)
/*     */   {
/* 154 */     Object[] arrayOfObject = this.elements;
/* 155 */     if (arrayOfObject == null) throw new InternalError();
/* 156 */     int i = index(0, 0);
/* 157 */     int j = this.columnStride;
/* 158 */     int k = this.rowStride;
/*     */     
/*     */ 
/* 161 */     int m = this.rows;
/* 162 */     do { int n = i;int i1 = this.columns;
/* 163 */       do { arrayOfObject[n] = paramObjectFunction.apply(arrayOfObject[n]);
/* 164 */         n += j;i1--;
/* 162 */       } while (i1 >= 0);
/*     */       
/*     */ 
/*     */ 
/* 166 */       i += k;m--;
/* 161 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */     return this;
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
/*     */   public ObjectMatrix2D assign(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 181 */     if (!(paramObjectMatrix2D instanceof DenseObjectMatrix2D)) {
/* 182 */       return super.assign(paramObjectMatrix2D);
/*     */     }
/* 184 */     DenseObjectMatrix2D localDenseObjectMatrix2D = (DenseObjectMatrix2D)paramObjectMatrix2D;
/* 185 */     if (localDenseObjectMatrix2D == this) return this;
/* 186 */     checkShape(localDenseObjectMatrix2D);
/*     */     
/* 188 */     if ((this.isNoView) && (localDenseObjectMatrix2D.isNoView)) {
/* 189 */       System.arraycopy(localDenseObjectMatrix2D.elements, 0, this.elements, 0, this.elements.length);
/* 190 */       return this;
/*     */     }
/*     */     
/* 193 */     if (haveSharedCells(localDenseObjectMatrix2D)) {
/* 194 */       localObject = localDenseObjectMatrix2D.copy();
/* 195 */       if (!(localObject instanceof DenseObjectMatrix2D)) {
/* 196 */         return super.assign(localDenseObjectMatrix2D);
/*     */       }
/* 198 */       localDenseObjectMatrix2D = (DenseObjectMatrix2D)localObject;
/*     */     }
/*     */     
/* 201 */     Object localObject = this.elements;
/* 202 */     Object[] arrayOfObject = localDenseObjectMatrix2D.elements;
/* 203 */     if ((this.elements == null) || (arrayOfObject == null)) throw new InternalError();
/* 204 */     int i = this.columnStride;
/* 205 */     int j = localDenseObjectMatrix2D.columnStride;
/* 206 */     int k = this.rowStride;
/* 207 */     int m = localDenseObjectMatrix2D.rowStride;
/*     */     
/* 209 */     int n = localDenseObjectMatrix2D.index(0, 0);
/* 210 */     int i1 = index(0, 0);
/* 211 */     int i2 = this.rows;
/* 212 */     do { int i3 = i1;int i4 = n;int i5 = this.columns;
/* 213 */       do { localObject[i3] = arrayOfObject[i4];
/* 214 */         i3 += i;
/* 215 */         i4 += j;i5--;
/* 212 */       } while (i5 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 217 */       i1 += k;
/* 218 */       n += m;i2--;
/* 211 */     } while (i2 >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 220 */     return this;
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
/*     */   public ObjectMatrix2D assign(ObjectMatrix2D paramObjectMatrix2D, ObjectObjectFunction paramObjectObjectFunction)
/*     */   {
/* 253 */     if (!(paramObjectMatrix2D instanceof DenseObjectMatrix2D)) {
/* 254 */       return super.assign(paramObjectMatrix2D, paramObjectObjectFunction);
/*     */     }
/* 256 */     DenseObjectMatrix2D localDenseObjectMatrix2D = (DenseObjectMatrix2D)paramObjectMatrix2D;
/* 257 */     checkShape(paramObjectMatrix2D);
/*     */     
/* 259 */     Object[] arrayOfObject1 = this.elements;
/* 260 */     Object[] arrayOfObject2 = localDenseObjectMatrix2D.elements;
/* 261 */     if ((arrayOfObject1 == null) || (arrayOfObject2 == null)) throw new InternalError();
/* 262 */     int i = this.columnStride;
/* 263 */     int j = localDenseObjectMatrix2D.columnStride;
/* 264 */     int k = this.rowStride;
/* 265 */     int m = localDenseObjectMatrix2D.rowStride;
/*     */     
/* 267 */     int n = localDenseObjectMatrix2D.index(0, 0);
/* 268 */     int i1 = index(0, 0);
/*     */     
/*     */ 
/* 271 */     int i2 = this.rows;
/* 272 */     do { int i3 = i1;int i4 = n;int i5 = this.columns;
/* 273 */       do { arrayOfObject1[i3] = paramObjectObjectFunction.apply(arrayOfObject1[i3], arrayOfObject2[i4]);
/* 274 */         i3 += i;
/* 275 */         i4 += j;i5--;
/* 272 */       } while (i5 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 277 */       i1 += k;
/* 278 */       n += m;i2--;
/* 271 */     } while (i2 >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 280 */     return this;
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
/*     */   public Object getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 297 */     return this.elements[(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 309 */     if ((paramObjectMatrix2D instanceof SelectedDenseObjectMatrix2D)) {
/* 310 */       localObject = (SelectedDenseObjectMatrix2D)paramObjectMatrix2D;
/* 311 */       return this.elements == ((SelectedDenseObjectMatrix2D)localObject).elements;
/*     */     }
/* 313 */     if ((paramObjectMatrix2D instanceof DenseObjectMatrix2D)) {
/* 314 */       localObject = (DenseObjectMatrix2D)paramObjectMatrix2D;
/* 315 */       return this.elements == ((DenseObjectMatrix2D)localObject).elements;
/*     */     }
/* 317 */     return false;
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
/* 328 */     return this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
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
/*     */   public ObjectMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 341 */     return new DenseObjectMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D like1D(int paramInt)
/*     */   {
/* 352 */     return new DenseObjectMatrix1D(paramInt);
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
/*     */   protected ObjectMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 365 */     return new DenseObjectMatrix1D(paramInt1, this.elements, paramInt2, paramInt3);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 382 */     this.elements[(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride)] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 392 */     return new SelectedDenseObjectMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, 0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseObjectMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */