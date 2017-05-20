/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseObjectMatrix2D;
/*     */ import cern.colt.matrix.impl.SparseObjectMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectFactory2D
/*     */   extends PersistentObject
/*     */ {
/*  66 */   public static final ObjectFactory2D dense = new ObjectFactory2D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   public static final ObjectFactory2D sparse = new ObjectFactory2D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D appendColumns(ObjectMatrix2D paramObjectMatrix2D1, ObjectMatrix2D paramObjectMatrix2D2)
/*     */   {
/*  91 */     if (paramObjectMatrix2D2.rows() > paramObjectMatrix2D1.rows()) { paramObjectMatrix2D2 = paramObjectMatrix2D2.viewPart(0, 0, paramObjectMatrix2D1.rows(), paramObjectMatrix2D2.columns());
/*  92 */     } else if (paramObjectMatrix2D2.rows() < paramObjectMatrix2D1.rows()) { paramObjectMatrix2D1 = paramObjectMatrix2D1.viewPart(0, 0, paramObjectMatrix2D2.rows(), paramObjectMatrix2D1.columns());
/*     */     }
/*     */     
/*  95 */     int i = paramObjectMatrix2D1.columns();
/*  96 */     int j = paramObjectMatrix2D2.columns();
/*  97 */     int k = paramObjectMatrix2D1.rows();
/*  98 */     ObjectMatrix2D localObjectMatrix2D = make(k, i + j);
/*  99 */     localObjectMatrix2D.viewPart(0, 0, k, i).assign(paramObjectMatrix2D1);
/* 100 */     localObjectMatrix2D.viewPart(0, i, k, j).assign(paramObjectMatrix2D2);
/* 101 */     return localObjectMatrix2D;
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
/*     */   public ObjectMatrix2D appendRows(ObjectMatrix2D paramObjectMatrix2D1, ObjectMatrix2D paramObjectMatrix2D2)
/*     */   {
/* 122 */     if (paramObjectMatrix2D2.columns() > paramObjectMatrix2D1.columns()) { paramObjectMatrix2D2 = paramObjectMatrix2D2.viewPart(0, 0, paramObjectMatrix2D2.rows(), paramObjectMatrix2D1.columns());
/* 123 */     } else if (paramObjectMatrix2D2.columns() < paramObjectMatrix2D1.columns()) { paramObjectMatrix2D1 = paramObjectMatrix2D1.viewPart(0, 0, paramObjectMatrix2D1.rows(), paramObjectMatrix2D2.columns());
/*     */     }
/*     */     
/* 126 */     int i = paramObjectMatrix2D1.rows();
/* 127 */     int j = paramObjectMatrix2D2.rows();
/* 128 */     int k = paramObjectMatrix2D1.columns();
/* 129 */     ObjectMatrix2D localObjectMatrix2D = make(i + j, k);
/* 130 */     localObjectMatrix2D.viewPart(0, 0, i, k).assign(paramObjectMatrix2D1);
/* 131 */     localObjectMatrix2D.viewPart(i, 0, j, k).assign(paramObjectMatrix2D2);
/* 132 */     return localObjectMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static void checkRectangularShape(ObjectMatrix2D[][] paramArrayOfObjectMatrix2D)
/*     */   {
/* 139 */     int i = -1;
/* 140 */     int j = paramArrayOfObjectMatrix2D.length;
/* 141 */     do { if (paramArrayOfObjectMatrix2D[j] != null) {
/* 142 */         if (i == -1) i = paramArrayOfObjectMatrix2D[j].length;
/* 143 */         if (paramArrayOfObjectMatrix2D[j].length != i) throw new IllegalArgumentException("All rows of array must have same number of columns.");
/*     */       }
/* 140 */       j--; } while (j >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void checkRectangularShape(Object[][] paramArrayOfObject)
/*     */   {
/* 152 */     int i = -1;
/* 153 */     int j = paramArrayOfObject.length;
/* 154 */     do { if (paramArrayOfObject[j] != null) {
/* 155 */         if (i == -1) i = paramArrayOfObject[j].length;
/* 156 */         if (paramArrayOfObject[j].length != i) throw new IllegalArgumentException("All rows of array must have same number of columns.");
/*     */       }
/* 153 */       j--; } while (j >= 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D compose(ObjectMatrix2D[][] paramArrayOfObjectMatrix2D)
/*     */   {
/* 261 */     checkRectangularShape(paramArrayOfObjectMatrix2D);
/* 262 */     int i = paramArrayOfObjectMatrix2D.length;
/* 263 */     int j = 0;
/* 264 */     if (paramArrayOfObjectMatrix2D.length > 0) j = paramArrayOfObjectMatrix2D[0].length;
/* 265 */     ObjectMatrix2D localObjectMatrix2D1 = make(0, 0);
/*     */     
/* 267 */     if ((i == 0) || (j == 0)) { return localObjectMatrix2D1;
/*     */     }
/*     */     
/* 270 */     int[] arrayOfInt1 = new int[j];
/* 271 */     int k = j;
/* 272 */     do { int m = 0;
/* 273 */       n = i;
/* 274 */       do { ObjectMatrix2D localObjectMatrix2D2 = paramArrayOfObjectMatrix2D[n][k];
/* 275 */         if (localObjectMatrix2D2 != null) {
/* 276 */           i2 = localObjectMatrix2D2.columns();
/* 277 */           if ((m > 0) && (i2 > 0) && (i2 != m)) throw new IllegalArgumentException("Different number of columns.");
/* 278 */           m = Math.max(m, i2);
/*     */         }
/* 273 */         n--; } while (n >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 281 */       arrayOfInt1[k] = m;k--;
/* 271 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 285 */     int[] arrayOfInt2 = new int[i];
/* 286 */     int n = i;
/* 287 */     do { i1 = 0;
/* 288 */       i2 = j;
/* 289 */       do { ObjectMatrix2D localObjectMatrix2D3 = paramArrayOfObjectMatrix2D[n][i2];
/* 290 */         if (localObjectMatrix2D3 != null) {
/* 291 */           i4 = localObjectMatrix2D3.rows();
/* 292 */           if ((i1 > 0) && (i4 > 0) && (i4 != i1)) throw new IllegalArgumentException("Different number of rows.");
/* 293 */           i1 = Math.max(i1, i4);
/*     */         }
/* 288 */         i2--; } while (i2 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 296 */       arrayOfInt2[n] = i1;n--;
/* 286 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 301 */     int i1 = 0;
/* 302 */     int i2 = i; do { i1 += arrayOfInt2[i2];i2--; } while (i2 >= 0);
/* 303 */     int i3 = 0;
/* 304 */     int i4 = j; do { i3 += arrayOfInt1[i4];i4--; } while (i4 >= 0);
/*     */     
/* 306 */     ObjectMatrix2D localObjectMatrix2D4 = make(i1, i3);
/*     */     
/*     */ 
/* 309 */     int i5 = 0;
/* 310 */     for (int i6 = 0; i6 < i; i6++) {
/* 311 */       int i7 = 0;
/* 312 */       for (int i8 = 0; i8 < j; i8++) {
/* 313 */         ObjectMatrix2D localObjectMatrix2D5 = paramArrayOfObjectMatrix2D[i6][i8];
/* 314 */         if (localObjectMatrix2D5 != null) {
/* 315 */           localObjectMatrix2D4.viewPart(i5, i7, localObjectMatrix2D5.rows(), localObjectMatrix2D5.columns()).assign(localObjectMatrix2D5);
/*     */         }
/* 317 */         i7 += arrayOfInt1[i8];
/*     */       }
/* 319 */       i5 += arrayOfInt2[i6];
/*     */     }
/*     */     
/* 322 */     return localObjectMatrix2D4;
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
/*     */   public ObjectMatrix2D composeDiagonal(ObjectMatrix2D paramObjectMatrix2D1, ObjectMatrix2D paramObjectMatrix2D2)
/*     */   {
/* 336 */     int i = paramObjectMatrix2D1.rows();int j = paramObjectMatrix2D1.columns();
/* 337 */     int k = paramObjectMatrix2D2.rows();int m = paramObjectMatrix2D2.columns();
/* 338 */     ObjectMatrix2D localObjectMatrix2D = make(i + k, j + m);
/* 339 */     localObjectMatrix2D.viewPart(0, 0, i, j).assign(paramObjectMatrix2D1);
/* 340 */     localObjectMatrix2D.viewPart(i, j, k, m).assign(paramObjectMatrix2D2);
/* 341 */     return localObjectMatrix2D;
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
/*     */   public ObjectMatrix2D composeDiagonal(ObjectMatrix2D paramObjectMatrix2D1, ObjectMatrix2D paramObjectMatrix2D2, ObjectMatrix2D paramObjectMatrix2D3)
/*     */   {
/* 355 */     ObjectMatrix2D localObjectMatrix2D = make(paramObjectMatrix2D1.rows() + paramObjectMatrix2D2.rows() + paramObjectMatrix2D3.rows(), paramObjectMatrix2D1.columns() + paramObjectMatrix2D2.columns() + paramObjectMatrix2D3.columns());
/* 356 */     localObjectMatrix2D.viewPart(0, 0, paramObjectMatrix2D1.rows(), paramObjectMatrix2D1.columns()).assign(paramObjectMatrix2D1);
/* 357 */     localObjectMatrix2D.viewPart(paramObjectMatrix2D1.rows(), paramObjectMatrix2D1.columns(), paramObjectMatrix2D2.rows(), paramObjectMatrix2D2.columns()).assign(paramObjectMatrix2D2);
/* 358 */     localObjectMatrix2D.viewPart(paramObjectMatrix2D1.rows() + paramObjectMatrix2D2.rows(), paramObjectMatrix2D1.columns() + paramObjectMatrix2D2.columns(), paramObjectMatrix2D3.rows(), paramObjectMatrix2D3.columns()).assign(paramObjectMatrix2D3);
/* 359 */     return localObjectMatrix2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decompose(ObjectMatrix2D[][] paramArrayOfObjectMatrix2D, ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 430 */     checkRectangularShape(paramArrayOfObjectMatrix2D);
/* 431 */     int i = paramArrayOfObjectMatrix2D.length;
/* 432 */     int j = 0;
/* 433 */     if (paramArrayOfObjectMatrix2D.length > 0) j = paramArrayOfObjectMatrix2D[0].length;
/* 434 */     if ((i == 0) || (j == 0)) { return;
/*     */     }
/*     */     
/* 437 */     int[] arrayOfInt1 = new int[j];
/* 438 */     int k = j;
/* 439 */     do { int m = 0;
/* 440 */       n = i;
/* 441 */       do { ObjectMatrix2D localObjectMatrix2D1 = paramArrayOfObjectMatrix2D[n][k];
/* 442 */         if (localObjectMatrix2D1 != null) {
/* 443 */           i2 = localObjectMatrix2D1.columns();
/* 444 */           if ((m > 0) && (i2 > 0) && (i2 != m)) throw new IllegalArgumentException("Different number of columns.");
/* 445 */           m = Math.max(m, i2);
/*     */         }
/* 440 */         n--; } while (n >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 448 */       arrayOfInt1[k] = m;k--;
/* 438 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 452 */     int[] arrayOfInt2 = new int[i];
/* 453 */     int n = i;
/* 454 */     do { i1 = 0;
/* 455 */       i2 = j;
/* 456 */       do { ObjectMatrix2D localObjectMatrix2D2 = paramArrayOfObjectMatrix2D[n][i2];
/* 457 */         if (localObjectMatrix2D2 != null) {
/* 458 */           i4 = localObjectMatrix2D2.rows();
/* 459 */           if ((i1 > 0) && (i4 > 0) && (i4 != i1)) throw new IllegalArgumentException("Different number of rows.");
/* 460 */           i1 = Math.max(i1, i4);
/*     */         }
/* 455 */         i2--; } while (i2 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 463 */       arrayOfInt2[n] = i1;n--;
/* 453 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 468 */     int i1 = 0;
/* 469 */     int i2 = i; do { i1 += arrayOfInt2[i2];i2--; } while (i2 >= 0);
/* 470 */     int i3 = 0;
/* 471 */     int i4 = j; do { i3 += arrayOfInt1[i4];i4--; } while (i4 >= 0);
/*     */     
/* 473 */     if ((paramObjectMatrix2D.rows() < i1) || (paramObjectMatrix2D.columns() < i3)) { throw new IllegalArgumentException("Parts larger than matrix.");
/*     */     }
/*     */     
/* 476 */     int i5 = 0;
/* 477 */     for (int i6 = 0; i6 < i; i6++) {
/* 478 */       int i7 = 0;
/* 479 */       for (int i8 = 0; i8 < j; i8++) {
/* 480 */         ObjectMatrix2D localObjectMatrix2D3 = paramArrayOfObjectMatrix2D[i6][i8];
/* 481 */         if (localObjectMatrix2D3 != null) {
/* 482 */           localObjectMatrix2D3.assign(paramObjectMatrix2D.viewPart(i5, i7, localObjectMatrix2D3.rows(), localObjectMatrix2D3.columns()));
/*     */         }
/* 484 */         i7 += arrayOfInt1[i8];
/*     */       }
/* 486 */       i5 += arrayOfInt2[i6];
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
/*     */   public ObjectMatrix2D diagonal(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 503 */     int i = paramObjectMatrix1D.size();
/* 504 */     ObjectMatrix2D localObjectMatrix2D = make(i, i);
/* 505 */     int j = i;
/* 506 */     do { localObjectMatrix2D.setQuick(j, j, paramObjectMatrix1D.getQuick(j));j--;
/* 505 */     } while (j >= 0);
/*     */     
/*     */ 
/* 508 */     return localObjectMatrix2D;
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
/*     */   public ObjectMatrix1D diagonal(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 524 */     int i = Math.min(paramObjectMatrix2D.rows(), paramObjectMatrix2D.columns());
/* 525 */     ObjectMatrix1D localObjectMatrix1D = make1D(i);
/* 526 */     int j = i;
/* 527 */     do { localObjectMatrix1D.setQuick(j, paramObjectMatrix2D.getQuick(j, j));j--;
/* 526 */     } while (j >= 0);
/*     */     
/*     */ 
/* 529 */     return localObjectMatrix1D;
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
/*     */   public ObjectMatrix2D make(Object[][] paramArrayOfObject)
/*     */   {
/* 542 */     if (this == sparse) return new SparseObjectMatrix2D(paramArrayOfObject);
/* 543 */     return new DenseObjectMatrix2D(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D make(Object[] paramArrayOfObject, int paramInt)
/*     */   {
/* 555 */     int i = paramInt != 0 ? paramArrayOfObject.length / paramInt : 0;
/* 556 */     if (paramInt * i != paramArrayOfObject.length) {
/* 557 */       throw new IllegalArgumentException("Array length must be a multiple of m.");
/*     */     }
/* 559 */     ObjectMatrix2D localObjectMatrix2D = make(paramInt, i);
/* 560 */     for (int j = 0; j < paramInt; j++) {
/* 561 */       for (int k = 0; k < i; k++) {
/* 562 */         localObjectMatrix2D.setQuick(j, k, paramArrayOfObject[(j + k * paramInt)]);
/*     */       }
/*     */     }
/* 565 */     return localObjectMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */   public ObjectMatrix2D make(int paramInt1, int paramInt2)
/*     */   {
/* 571 */     if (this == sparse) return new SparseObjectMatrix2D(paramInt1, paramInt2);
/* 572 */     return new DenseObjectMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */   public ObjectMatrix2D make(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 578 */     if (paramObject == null) return make(paramInt1, paramInt2);
/* 579 */     return make(paramInt1, paramInt2).assign(paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ObjectMatrix1D make1D(int paramInt)
/*     */   {
/* 585 */     return make(0, 0).like1D(paramInt);
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
/*     */   public ObjectMatrix2D repeat(ObjectMatrix2D paramObjectMatrix2D, int paramInt1, int paramInt2)
/*     */   {
/* 601 */     int i = paramObjectMatrix2D.rows();
/* 602 */     int j = paramObjectMatrix2D.columns();
/* 603 */     ObjectMatrix2D localObjectMatrix2D = make(i * paramInt1, j * paramInt2);
/* 604 */     int k = paramInt1;
/* 605 */     do { int m = paramInt2;
/* 606 */       do { localObjectMatrix2D.viewPart(i * k, j * m, i, j).assign(paramObjectMatrix2D);m--;
/* 605 */       } while (m >= 0);
/* 604 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 609 */     return localObjectMatrix2D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectFactory2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */