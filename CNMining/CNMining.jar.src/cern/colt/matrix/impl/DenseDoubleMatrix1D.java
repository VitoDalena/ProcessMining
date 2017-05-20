/*     */ package cern.colt.matrix.impl;
/*     */ 
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
/*     */ public class DenseDoubleMatrix1D
/*     */   extends DoubleMatrix1D
/*     */ {
/*     */   protected double[] elements;
/*     */   
/*     */   public DenseDoubleMatrix1D(double[] paramArrayOfDouble)
/*     */   {
/*  46 */     this(paramArrayOfDouble.length);
/*  47 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseDoubleMatrix1D(int paramInt)
/*     */   {
/*  56 */     setUp(paramInt);
/*  57 */     this.elements = new double[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DenseDoubleMatrix1D(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3)
/*     */   {
/*  68 */     setUp(paramInt1, paramInt2, paramInt3);
/*  69 */     this.elements = paramArrayOfDouble;
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
/*     */   public DoubleMatrix1D assign(double[] paramArrayOfDouble)
/*     */   {
/*  83 */     if (this.isNoView) {
/*  84 */       if (paramArrayOfDouble.length != this.size) throw new IllegalArgumentException("Must have same number of cells: length=" + paramArrayOfDouble.length + "size()=" + size());
/*  85 */       System.arraycopy(paramArrayOfDouble, 0, this.elements, 0, paramArrayOfDouble.length);
/*     */     }
/*     */     else {
/*  88 */       super.assign(paramArrayOfDouble);
/*     */     }
/*  90 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D assign(double paramDouble)
/*     */   {
/*  98 */     int i = index(0);
/*  99 */     int j = this.stride;
/* 100 */     double[] arrayOfDouble = this.elements;
/* 101 */     int k = this.size;
/* 102 */     do { arrayOfDouble[i] = paramDouble;
/* 103 */       i += j;k--;
/* 101 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/* 105 */     return this;
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
/*     */   public DoubleMatrix1D assign(DoubleFunction paramDoubleFunction)
/*     */   {
/* 126 */     int i = this.stride;
/* 127 */     int j = index(0);
/* 128 */     double[] arrayOfDouble = this.elements;
/* 129 */     if (arrayOfDouble == null) { throw new InternalError();
/*     */     }
/*     */     
/* 132 */     if ((paramDoubleFunction instanceof Mult)) {
/* 133 */       double d = ((Mult)paramDoubleFunction).multiplicator;
/* 134 */       if (d == 1.0D) return this;
/* 135 */       int m = this.size;
/* 136 */       do { arrayOfDouble[j] *= d;
/* 137 */         j += i;m--;
/* 135 */       } while (m >= 0);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 141 */       int k = this.size;
/* 142 */       do { arrayOfDouble[j] = paramDoubleFunction.apply(arrayOfDouble[j]);
/* 143 */         j += i;k--;
/* 141 */       } while (k >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 146 */     return this;
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
/*     */   public DoubleMatrix1D assign(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 159 */     if (!(paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 160 */       return super.assign(paramDoubleMatrix1D);
/*     */     }
/* 162 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 163 */     if (localDenseDoubleMatrix1D == this) return this;
/* 164 */     checkSize(localDenseDoubleMatrix1D);
/* 165 */     if ((this.isNoView) && (localDenseDoubleMatrix1D.isNoView)) {
/* 166 */       System.arraycopy(localDenseDoubleMatrix1D.elements, 0, this.elements, 0, this.elements.length);
/* 167 */       return this;
/*     */     }
/* 169 */     if (haveSharedCells(localDenseDoubleMatrix1D)) {
/* 170 */       localObject = localDenseDoubleMatrix1D.copy();
/* 171 */       if (!(localObject instanceof DenseDoubleMatrix1D)) {
/* 172 */         return super.assign(paramDoubleMatrix1D);
/*     */       }
/* 174 */       localDenseDoubleMatrix1D = (DenseDoubleMatrix1D)localObject;
/*     */     }
/*     */     
/* 177 */     Object localObject = this.elements;
/* 178 */     double[] arrayOfDouble = localDenseDoubleMatrix1D.elements;
/* 179 */     if ((this.elements == null) || (arrayOfDouble == null)) throw new InternalError();
/* 180 */     int i = this.stride;
/* 181 */     int j = localDenseDoubleMatrix1D.stride;
/*     */     
/* 183 */     int k = index(0);
/* 184 */     int m = localDenseDoubleMatrix1D.index(0);
/* 185 */     int n = this.size;
/* 186 */     do { localObject[k] = arrayOfDouble[m];
/* 187 */       k += i;
/* 188 */       m += j;n--;
/* 185 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 190 */     return this;
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
/*     */   public DoubleMatrix1D assign(DoubleMatrix1D paramDoubleMatrix1D, DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 223 */     if (!(paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 224 */       return super.assign(paramDoubleMatrix1D, paramDoubleDoubleFunction);
/*     */     }
/* 226 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 227 */     checkSize(paramDoubleMatrix1D);
/* 228 */     double[] arrayOfDouble1 = this.elements;
/* 229 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D.elements;
/* 230 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null)) throw new InternalError();
/* 231 */     int i = this.stride;
/* 232 */     int j = localDenseDoubleMatrix1D.stride;
/*     */     
/* 234 */     int k = index(0);
/* 235 */     int m = localDenseDoubleMatrix1D.index(0);
/*     */     
/*     */     int n;
/* 238 */     if (paramDoubleDoubleFunction == Functions.mult) {
/* 239 */       n = this.size;
/* 240 */       do { arrayOfDouble1[k] *= arrayOfDouble2[m];
/* 241 */         k += i;
/* 242 */         m += j;n--;
/* 239 */       } while (n >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 245 */     else if (paramDoubleDoubleFunction == Functions.div) {
/* 246 */       n = this.size;
/* 247 */       do { arrayOfDouble1[k] /= arrayOfDouble2[m];
/* 248 */         k += i;
/* 249 */         m += j;n--;
/* 246 */       } while (n >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 252 */     else if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 253 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 254 */       if (d == 0.0D)
/* 255 */         return this;
/*     */       int i2;
/* 257 */       if (d == 1.0D) {
/* 258 */         i2 = this.size;
/* 259 */         do { arrayOfDouble1[k] += arrayOfDouble2[m];
/* 260 */           k += i;
/* 261 */           m += j;i2--;
/* 258 */         } while (i2 >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 264 */       else if (d == -1.0D) {
/* 265 */         i2 = this.size;
/* 266 */         do { arrayOfDouble1[k] -= arrayOfDouble2[m];
/* 267 */           k += i;
/* 268 */           m += j;i2--;
/* 265 */         } while (i2 >= 0);
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 272 */         i2 = this.size;
/* 273 */         do { arrayOfDouble1[k] += d * arrayOfDouble2[m];
/* 274 */           k += i;
/* 275 */           m += j;i2--;
/* 272 */         } while (i2 >= 0);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 280 */       int i1 = this.size;
/* 281 */       do { arrayOfDouble1[k] = paramDoubleDoubleFunction.apply(arrayOfDouble1[k], arrayOfDouble2[m]);
/* 282 */         k += i;
/* 283 */         m += j;i1--;
/* 280 */       } while (i1 >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 286 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int cardinality(int paramInt)
/*     */   {
/* 292 */     int i = 0;
/* 293 */     int j = index(0);
/* 294 */     int k = this.stride;
/* 295 */     double[] arrayOfDouble = this.elements;
/* 296 */     int m = this.size;
/*     */     do {
/* 298 */       if (arrayOfDouble[j] != 0.0D) i++;
/* 299 */       j += k;m--;
/* 297 */     } while ((m >= 0) && (i < paramInt));
/*     */     
/*     */ 
/*     */ 
/* 301 */     return i;
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
/*     */   public double getQuick(int paramInt)
/*     */   {
/* 317 */     return this.elements[(this.zero + paramInt * this.stride)];
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 323 */     if ((paramDoubleMatrix1D instanceof SelectedDenseDoubleMatrix1D)) {
/* 324 */       localObject = (SelectedDenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 325 */       return this.elements == ((SelectedDenseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 327 */     if ((paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 328 */       localObject = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 329 */       return this.elements == ((DenseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 331 */     return false;
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
/* 342 */     return this.zero + paramInt * this.stride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like(int paramInt)
/*     */   {
/* 354 */     return new DenseDoubleMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D like2D(int paramInt1, int paramInt2)
/*     */   {
/* 366 */     return new DenseDoubleMatrix2D(paramInt1, paramInt2);
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
/*     */   public void setQuick(int paramInt, double paramDouble)
/*     */   {
/* 382 */     this.elements[(this.zero + paramInt * this.stride)] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void swap(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 390 */     if (!(paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 391 */       super.swap(paramDoubleMatrix1D);
/*     */     }
/* 393 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 394 */     if (localDenseDoubleMatrix1D == this) return;
/* 395 */     checkSize(localDenseDoubleMatrix1D);
/*     */     
/* 397 */     double[] arrayOfDouble1 = this.elements;
/* 398 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D.elements;
/* 399 */     if ((this.elements == null) || (arrayOfDouble2 == null)) throw new InternalError();
/* 400 */     int i = this.stride;
/* 401 */     int j = localDenseDoubleMatrix1D.stride;
/*     */     
/* 403 */     int k = index(0);
/* 404 */     int m = localDenseDoubleMatrix1D.index(0);
/* 405 */     int n = this.size;
/* 406 */     do { double d = arrayOfDouble1[k];
/* 407 */       arrayOfDouble1[k] = arrayOfDouble2[m];
/* 408 */       arrayOfDouble2[m] = d;
/* 409 */       k += i;
/* 410 */       m += j;n--;
/* 405 */     } while (n >= 0);
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
/*     */   public void toArray(double[] paramArrayOfDouble)
/*     */   {
/* 424 */     if (paramArrayOfDouble.length < this.size) throw new IllegalArgumentException("values too small");
/* 425 */     if (this.isNoView) System.arraycopy(this.elements, 0, paramArrayOfDouble, 0, this.elements.length); else {
/* 426 */       super.toArray(paramArrayOfDouble);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 435 */     return new SelectedDenseDoubleMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double zDotProduct(DoubleMatrix1D paramDoubleMatrix1D, int paramInt1, int paramInt2)
/*     */   {
/* 447 */     if (!(paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 448 */       return super.zDotProduct(paramDoubleMatrix1D, paramInt1, paramInt2);
/*     */     }
/* 450 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/*     */     
/* 452 */     int i = paramInt1 + paramInt2;
/* 453 */     if ((paramInt1 < 0) || (paramInt2 < 0)) return 0.0D;
/* 454 */     if (this.size < i) i = this.size;
/* 455 */     if (paramDoubleMatrix1D.size < i) i = paramDoubleMatrix1D.size;
/* 456 */     int j = i - paramInt1;
/*     */     
/* 458 */     int k = index(paramInt1);
/* 459 */     int m = localDenseDoubleMatrix1D.index(paramInt1);
/* 460 */     int n = this.stride;
/* 461 */     int i1 = localDenseDoubleMatrix1D.stride;
/* 462 */     double[] arrayOfDouble1 = this.elements;
/* 463 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D.elements;
/* 464 */     if ((arrayOfDouble1 == null) || (arrayOfDouble2 == null)) { throw new InternalError();
/*     */     }
/* 466 */     double d = 0.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 478 */     k -= n;
/* 479 */     m -= i1;
/* 480 */     int i2 = j / 4;
/* 481 */     do { d += arrayOfDouble1[(k += n)] * arrayOfDouble2[(m += i1)] + arrayOfDouble1[(k += n)] * arrayOfDouble2[(m += i1)] + arrayOfDouble1[(k += n)] * arrayOfDouble2[(m += i1)] + arrayOfDouble1[(k += n)] * arrayOfDouble2[(m += i1)];i2--;
/* 480 */     } while (i2 >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 486 */     int i3 = j % 4;
/* 487 */     do { d += arrayOfDouble1[(k += n)] * arrayOfDouble2[(m += i1)];i3--;
/* 486 */     } while (i3 >= 0);
/*     */     
/*     */ 
/* 489 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double zSum()
/*     */   {
/* 496 */     double d = 0.0D;
/* 497 */     int i = this.stride;
/* 498 */     int j = index(0);
/* 499 */     double[] arrayOfDouble = this.elements;
/* 500 */     if (arrayOfDouble == null) throw new InternalError();
/* 501 */     int k = this.size;
/* 502 */     do { d += arrayOfDouble[j];
/* 503 */       j += i;k--;
/* 501 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/* 505 */     return d;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */