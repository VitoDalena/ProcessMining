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
/*     */ public class DenseObjectMatrix1D
/*     */   extends ObjectMatrix1D
/*     */ {
/*     */   protected Object[] elements;
/*     */   
/*     */   public DenseObjectMatrix1D(Object[] paramArrayOfObject)
/*     */   {
/*  46 */     this(paramArrayOfObject.length);
/*  47 */     assign(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseObjectMatrix1D(int paramInt)
/*     */   {
/*  56 */     setUp(paramInt);
/*  57 */     this.elements = new Object[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DenseObjectMatrix1D(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int paramInt3)
/*     */   {
/*  68 */     setUp(paramInt1, paramInt2, paramInt3);
/*  69 */     this.elements = paramArrayOfObject;
/*  70 */     this.isNoView = false;
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
/*     */   public ObjectMatrix1D assign(Object[] paramArrayOfObject)
/*     */   {
/*  83 */     if (this.isNoView) {
/*  84 */       if (paramArrayOfObject.length != this.size) throw new IllegalArgumentException("Must have same number of cells: length=" + paramArrayOfObject.length + "size()=" + size());
/*  85 */       System.arraycopy(paramArrayOfObject, 0, this.elements, 0, paramArrayOfObject.length);
/*     */     }
/*     */     else {
/*  88 */       super.assign(paramArrayOfObject);
/*     */     }
/*  90 */     return this;
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
/*     */   public ObjectMatrix1D assign(ObjectFunction paramObjectFunction)
/*     */   {
/* 111 */     int i = this.stride;
/* 112 */     int j = index(0);
/* 113 */     Object[] arrayOfObject = this.elements;
/* 114 */     if (this.elements == null) { throw new InternalError();
/*     */     }
/*     */     
/* 117 */     int k = this.size;
/* 118 */     do { arrayOfObject[j] = paramObjectFunction.apply(arrayOfObject[j]);
/* 119 */       j += i;k--;
/* 117 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/* 121 */     return this;
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
/*     */   public ObjectMatrix1D assign(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 134 */     if (!(paramObjectMatrix1D instanceof DenseObjectMatrix1D)) {
/* 135 */       return super.assign(paramObjectMatrix1D);
/*     */     }
/* 137 */     DenseObjectMatrix1D localDenseObjectMatrix1D = (DenseObjectMatrix1D)paramObjectMatrix1D;
/* 138 */     if (localDenseObjectMatrix1D == this) return this;
/* 139 */     checkSize(localDenseObjectMatrix1D);
/* 140 */     if ((this.isNoView) && (localDenseObjectMatrix1D.isNoView)) {
/* 141 */       System.arraycopy(localDenseObjectMatrix1D.elements, 0, this.elements, 0, this.elements.length);
/* 142 */       return this;
/*     */     }
/* 144 */     if (haveSharedCells(localDenseObjectMatrix1D)) {
/* 145 */       localObject = localDenseObjectMatrix1D.copy();
/* 146 */       if (!(localObject instanceof DenseObjectMatrix1D)) {
/* 147 */         return super.assign(paramObjectMatrix1D);
/*     */       }
/* 149 */       localDenseObjectMatrix1D = (DenseObjectMatrix1D)localObject;
/*     */     }
/*     */     
/* 152 */     Object localObject = this.elements;
/* 153 */     Object[] arrayOfObject = localDenseObjectMatrix1D.elements;
/* 154 */     if ((this.elements == null) || (arrayOfObject == null)) throw new InternalError();
/* 155 */     int i = this.stride;
/* 156 */     int j = localDenseObjectMatrix1D.stride;
/*     */     
/* 158 */     int k = index(0);
/* 159 */     int m = localDenseObjectMatrix1D.index(0);
/* 160 */     int n = this.size;
/* 161 */     do { localObject[k] = arrayOfObject[m];
/* 162 */       k += i;
/* 163 */       m += j;n--;
/* 160 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 165 */     return this;
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
/*     */   public ObjectMatrix1D assign(ObjectMatrix1D paramObjectMatrix1D, ObjectObjectFunction paramObjectObjectFunction)
/*     */   {
/* 198 */     if (!(paramObjectMatrix1D instanceof DenseObjectMatrix1D)) {
/* 199 */       return super.assign(paramObjectMatrix1D, paramObjectObjectFunction);
/*     */     }
/* 201 */     DenseObjectMatrix1D localDenseObjectMatrix1D = (DenseObjectMatrix1D)paramObjectMatrix1D;
/* 202 */     checkSize(paramObjectMatrix1D);
/* 203 */     Object[] arrayOfObject1 = this.elements;
/* 204 */     Object[] arrayOfObject2 = localDenseObjectMatrix1D.elements;
/* 205 */     if ((this.elements == null) || (arrayOfObject2 == null)) throw new InternalError();
/* 206 */     int i = this.stride;
/* 207 */     int j = localDenseObjectMatrix1D.stride;
/*     */     
/* 209 */     int k = index(0);
/* 210 */     int m = localDenseObjectMatrix1D.index(0);
/*     */     
/*     */ 
/* 213 */     int n = this.size;
/* 214 */     do { arrayOfObject1[k] = paramObjectObjectFunction.apply(arrayOfObject1[k], arrayOfObject2[m]);
/* 215 */       k += i;
/* 216 */       m += j;n--;
/* 213 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 218 */     return this;
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
/*     */   public Object getQuick(int paramInt)
/*     */   {
/* 234 */     return this.elements[(this.zero + paramInt * this.stride)];
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 240 */     if ((paramObjectMatrix1D instanceof SelectedDenseObjectMatrix1D)) {
/* 241 */       localObject = (SelectedDenseObjectMatrix1D)paramObjectMatrix1D;
/* 242 */       return this.elements == ((SelectedDenseObjectMatrix1D)localObject).elements;
/*     */     }
/* 244 */     if ((paramObjectMatrix1D instanceof DenseObjectMatrix1D)) {
/* 245 */       localObject = (DenseObjectMatrix1D)paramObjectMatrix1D;
/* 246 */       return this.elements == ((DenseObjectMatrix1D)localObject).elements;
/*     */     }
/* 248 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt)
/*     */   {
/* 259 */     return this.zero + paramInt * this.stride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D like(int paramInt)
/*     */   {
/* 271 */     return new DenseObjectMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D like2D(int paramInt1, int paramInt2)
/*     */   {
/* 283 */     return new DenseObjectMatrix2D(paramInt1, paramInt2);
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
/*     */   public void setQuick(int paramInt, Object paramObject)
/*     */   {
/* 299 */     this.elements[(this.zero + paramInt * this.stride)] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void swap(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 307 */     if (!(paramObjectMatrix1D instanceof DenseObjectMatrix1D)) {
/* 308 */       super.swap(paramObjectMatrix1D);
/*     */     }
/* 310 */     DenseObjectMatrix1D localDenseObjectMatrix1D = (DenseObjectMatrix1D)paramObjectMatrix1D;
/* 311 */     if (localDenseObjectMatrix1D == this) return;
/* 312 */     checkSize(localDenseObjectMatrix1D);
/*     */     
/* 314 */     Object[] arrayOfObject1 = this.elements;
/* 315 */     Object[] arrayOfObject2 = localDenseObjectMatrix1D.elements;
/* 316 */     if ((this.elements == null) || (arrayOfObject2 == null)) throw new InternalError();
/* 317 */     int i = this.stride;
/* 318 */     int j = localDenseObjectMatrix1D.stride;
/*     */     
/* 320 */     int k = index(0);
/* 321 */     int m = localDenseObjectMatrix1D.index(0);
/* 322 */     int n = this.size;
/* 323 */     do { Object localObject = arrayOfObject1[k];
/* 324 */       arrayOfObject1[k] = arrayOfObject2[m];
/* 325 */       arrayOfObject2[m] = localObject;
/* 326 */       k += i;
/* 327 */       m += j;n--;
/* 322 */     } while (n >= 0);
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
/*     */   public void toArray(Object[] paramArrayOfObject)
/*     */   {
/* 341 */     if (paramArrayOfObject.length < this.size) throw new IllegalArgumentException("values too small");
/* 342 */     if (this.isNoView) System.arraycopy(this.elements, 0, paramArrayOfObject, 0, this.elements.length); else {
/* 343 */       super.toArray(paramArrayOfObject);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 352 */     return new SelectedDenseObjectMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseObjectMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */