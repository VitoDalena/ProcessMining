/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.RCDoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import cern.jet.random.sampling.RandomSamplingAssistant;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoubleFactory2D
/*     */   extends PersistentObject
/*     */ {
/*  79 */   public static final DoubleFactory2D dense = new DoubleFactory2D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  84 */   public static final DoubleFactory2D sparse = new DoubleFactory2D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  89 */   public static final DoubleFactory2D rowCompressed = new DoubleFactory2D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D appendColumns(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 114 */     if (paramDoubleMatrix2D2.rows() > paramDoubleMatrix2D1.rows()) { paramDoubleMatrix2D2 = paramDoubleMatrix2D2.viewPart(0, 0, paramDoubleMatrix2D1.rows(), paramDoubleMatrix2D2.columns());
/* 115 */     } else if (paramDoubleMatrix2D2.rows() < paramDoubleMatrix2D1.rows()) { paramDoubleMatrix2D1 = paramDoubleMatrix2D1.viewPart(0, 0, paramDoubleMatrix2D2.rows(), paramDoubleMatrix2D1.columns());
/*     */     }
/*     */     
/* 118 */     int i = paramDoubleMatrix2D1.columns();
/* 119 */     int j = paramDoubleMatrix2D2.columns();
/* 120 */     int k = paramDoubleMatrix2D1.rows();
/* 121 */     DoubleMatrix2D localDoubleMatrix2D = make(k, i + j);
/* 122 */     localDoubleMatrix2D.viewPart(0, 0, k, i).assign(paramDoubleMatrix2D1);
/* 123 */     localDoubleMatrix2D.viewPart(0, i, k, j).assign(paramDoubleMatrix2D2);
/* 124 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D appendRows(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 145 */     if (paramDoubleMatrix2D2.columns() > paramDoubleMatrix2D1.columns()) { paramDoubleMatrix2D2 = paramDoubleMatrix2D2.viewPart(0, 0, paramDoubleMatrix2D2.rows(), paramDoubleMatrix2D1.columns());
/* 146 */     } else if (paramDoubleMatrix2D2.columns() < paramDoubleMatrix2D1.columns()) { paramDoubleMatrix2D1 = paramDoubleMatrix2D1.viewPart(0, 0, paramDoubleMatrix2D1.rows(), paramDoubleMatrix2D2.columns());
/*     */     }
/*     */     
/* 149 */     int i = paramDoubleMatrix2D1.rows();
/* 150 */     int j = paramDoubleMatrix2D2.rows();
/* 151 */     int k = paramDoubleMatrix2D1.columns();
/* 152 */     DoubleMatrix2D localDoubleMatrix2D = make(i + j, k);
/* 153 */     localDoubleMatrix2D.viewPart(0, 0, i, k).assign(paramDoubleMatrix2D1);
/* 154 */     localDoubleMatrix2D.viewPart(i, 0, j, k).assign(paramDoubleMatrix2D2);
/* 155 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D ascending(int paramInt1, int paramInt2)
/*     */   {
/* 167 */     Functions localFunctions = Functions.functions;
/* 168 */     return descending(paramInt1, paramInt2).assign(Functions.chain(Functions.neg, Functions.minus(paramInt2 * paramInt1)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static void checkRectangularShape(double[][] paramArrayOfDouble)
/*     */   {
/* 175 */     int i = -1;
/* 176 */     int j = paramArrayOfDouble.length;
/* 177 */     do { if (paramArrayOfDouble[j] != null) {
/* 178 */         if (i == -1) i = paramArrayOfDouble[j].length;
/* 179 */         if (paramArrayOfDouble[j].length != i) throw new IllegalArgumentException("All rows of array must have same number of columns.");
/*     */       }
/* 176 */       j--; } while (j >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void checkRectangularShape(DoubleMatrix2D[][] paramArrayOfDoubleMatrix2D)
/*     */   {
/* 188 */     int i = -1;
/* 189 */     int j = paramArrayOfDoubleMatrix2D.length;
/* 190 */     do { if (paramArrayOfDoubleMatrix2D[j] != null) {
/* 191 */         if (i == -1) i = paramArrayOfDoubleMatrix2D[j].length;
/* 192 */         if (paramArrayOfDoubleMatrix2D[j].length != i) throw new IllegalArgumentException("All rows of array must have same number of columns.");
/*     */       }
/* 189 */       j--; } while (j >= 0);
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
/*     */   public DoubleMatrix2D compose(DoubleMatrix2D[][] paramArrayOfDoubleMatrix2D)
/*     */   {
/* 297 */     checkRectangularShape(paramArrayOfDoubleMatrix2D);
/* 298 */     int i = paramArrayOfDoubleMatrix2D.length;
/* 299 */     int j = 0;
/* 300 */     if (paramArrayOfDoubleMatrix2D.length > 0) j = paramArrayOfDoubleMatrix2D[0].length;
/* 301 */     DoubleMatrix2D localDoubleMatrix2D1 = make(0, 0);
/*     */     
/* 303 */     if ((i == 0) || (j == 0)) { return localDoubleMatrix2D1;
/*     */     }
/*     */     
/* 306 */     int[] arrayOfInt1 = new int[j];
/* 307 */     int k = j;
/* 308 */     do { int m = 0;
/* 309 */       n = i;
/* 310 */       do { DoubleMatrix2D localDoubleMatrix2D2 = paramArrayOfDoubleMatrix2D[n][k];
/* 311 */         if (localDoubleMatrix2D2 != null) {
/* 312 */           i2 = localDoubleMatrix2D2.columns();
/* 313 */           if ((m > 0) && (i2 > 0) && (i2 != m)) throw new IllegalArgumentException("Different number of columns.");
/* 314 */           m = Math.max(m, i2);
/*     */         }
/* 309 */         n--; } while (n >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 317 */       arrayOfInt1[k] = m;k--;
/* 307 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 321 */     int[] arrayOfInt2 = new int[i];
/* 322 */     int n = i;
/* 323 */     do { i1 = 0;
/* 324 */       i2 = j;
/* 325 */       do { DoubleMatrix2D localDoubleMatrix2D3 = paramArrayOfDoubleMatrix2D[n][i2];
/* 326 */         if (localDoubleMatrix2D3 != null) {
/* 327 */           i4 = localDoubleMatrix2D3.rows();
/* 328 */           if ((i1 > 0) && (i4 > 0) && (i4 != i1)) throw new IllegalArgumentException("Different number of rows.");
/* 329 */           i1 = Math.max(i1, i4);
/*     */         }
/* 324 */         i2--; } while (i2 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 332 */       arrayOfInt2[n] = i1;n--;
/* 322 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 337 */     int i1 = 0;
/* 338 */     int i2 = i; do { i1 += arrayOfInt2[i2];i2--; } while (i2 >= 0);
/* 339 */     int i3 = 0;
/* 340 */     int i4 = j; do { i3 += arrayOfInt1[i4];i4--; } while (i4 >= 0);
/*     */     
/* 342 */     DoubleMatrix2D localDoubleMatrix2D4 = make(i1, i3);
/*     */     
/*     */ 
/* 345 */     int i5 = 0;
/* 346 */     for (int i6 = 0; i6 < i; i6++) {
/* 347 */       int i7 = 0;
/* 348 */       for (int i8 = 0; i8 < j; i8++) {
/* 349 */         DoubleMatrix2D localDoubleMatrix2D5 = paramArrayOfDoubleMatrix2D[i6][i8];
/* 350 */         if (localDoubleMatrix2D5 != null) {
/* 351 */           localDoubleMatrix2D4.viewPart(i5, i7, localDoubleMatrix2D5.rows(), localDoubleMatrix2D5.columns()).assign(localDoubleMatrix2D5);
/*     */         }
/* 353 */         i7 += arrayOfInt1[i8];
/*     */       }
/* 355 */       i5 += arrayOfInt2[i6];
/*     */     }
/*     */     
/* 358 */     return localDoubleMatrix2D4;
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
/*     */   public DoubleMatrix2D composeDiagonal(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 372 */     int i = paramDoubleMatrix2D1.rows();int j = paramDoubleMatrix2D1.columns();
/* 373 */     int k = paramDoubleMatrix2D2.rows();int m = paramDoubleMatrix2D2.columns();
/* 374 */     DoubleMatrix2D localDoubleMatrix2D = make(i + k, j + m);
/* 375 */     localDoubleMatrix2D.viewPart(0, 0, i, j).assign(paramDoubleMatrix2D1);
/* 376 */     localDoubleMatrix2D.viewPart(i, j, k, m).assign(paramDoubleMatrix2D2);
/* 377 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D composeDiagonal(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, DoubleMatrix2D paramDoubleMatrix2D3)
/*     */   {
/* 391 */     DoubleMatrix2D localDoubleMatrix2D = make(paramDoubleMatrix2D1.rows() + paramDoubleMatrix2D2.rows() + paramDoubleMatrix2D3.rows(), paramDoubleMatrix2D1.columns() + paramDoubleMatrix2D2.columns() + paramDoubleMatrix2D3.columns());
/* 392 */     localDoubleMatrix2D.viewPart(0, 0, paramDoubleMatrix2D1.rows(), paramDoubleMatrix2D1.columns()).assign(paramDoubleMatrix2D1);
/* 393 */     localDoubleMatrix2D.viewPart(paramDoubleMatrix2D1.rows(), paramDoubleMatrix2D1.columns(), paramDoubleMatrix2D2.rows(), paramDoubleMatrix2D2.columns()).assign(paramDoubleMatrix2D2);
/* 394 */     localDoubleMatrix2D.viewPart(paramDoubleMatrix2D1.rows() + paramDoubleMatrix2D2.rows(), paramDoubleMatrix2D1.columns() + paramDoubleMatrix2D2.columns(), paramDoubleMatrix2D3.rows(), paramDoubleMatrix2D3.columns()).assign(paramDoubleMatrix2D3);
/* 395 */     return localDoubleMatrix2D;
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
/*     */   public void decompose(DoubleMatrix2D[][] paramArrayOfDoubleMatrix2D, DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 466 */     checkRectangularShape(paramArrayOfDoubleMatrix2D);
/* 467 */     int i = paramArrayOfDoubleMatrix2D.length;
/* 468 */     int j = 0;
/* 469 */     if (paramArrayOfDoubleMatrix2D.length > 0) j = paramArrayOfDoubleMatrix2D[0].length;
/* 470 */     if ((i == 0) || (j == 0)) { return;
/*     */     }
/*     */     
/* 473 */     int[] arrayOfInt1 = new int[j];
/* 474 */     int k = j;
/* 475 */     do { int m = 0;
/* 476 */       n = i;
/* 477 */       do { DoubleMatrix2D localDoubleMatrix2D1 = paramArrayOfDoubleMatrix2D[n][k];
/* 478 */         if (localDoubleMatrix2D1 != null) {
/* 479 */           i2 = localDoubleMatrix2D1.columns();
/* 480 */           if ((m > 0) && (i2 > 0) && (i2 != m)) throw new IllegalArgumentException("Different number of columns.");
/* 481 */           m = Math.max(m, i2);
/*     */         }
/* 476 */         n--; } while (n >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 484 */       arrayOfInt1[k] = m;k--;
/* 474 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 488 */     int[] arrayOfInt2 = new int[i];
/* 489 */     int n = i;
/* 490 */     do { i1 = 0;
/* 491 */       i2 = j;
/* 492 */       do { DoubleMatrix2D localDoubleMatrix2D2 = paramArrayOfDoubleMatrix2D[n][i2];
/* 493 */         if (localDoubleMatrix2D2 != null) {
/* 494 */           i4 = localDoubleMatrix2D2.rows();
/* 495 */           if ((i1 > 0) && (i4 > 0) && (i4 != i1)) throw new IllegalArgumentException("Different number of rows.");
/* 496 */           i1 = Math.max(i1, i4);
/*     */         }
/* 491 */         i2--; } while (i2 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 499 */       arrayOfInt2[n] = i1;n--;
/* 489 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 504 */     int i1 = 0;
/* 505 */     int i2 = i; do { i1 += arrayOfInt2[i2];i2--; } while (i2 >= 0);
/* 506 */     int i3 = 0;
/* 507 */     int i4 = j; do { i3 += arrayOfInt1[i4];i4--; } while (i4 >= 0);
/*     */     
/* 509 */     if ((paramDoubleMatrix2D.rows() < i1) || (paramDoubleMatrix2D.columns() < i3)) { throw new IllegalArgumentException("Parts larger than matrix.");
/*     */     }
/*     */     
/* 512 */     int i5 = 0;
/* 513 */     for (int i6 = 0; i6 < i; i6++) {
/* 514 */       int i7 = 0;
/* 515 */       for (int i8 = 0; i8 < j; i8++) {
/* 516 */         DoubleMatrix2D localDoubleMatrix2D3 = paramArrayOfDoubleMatrix2D[i6][i8];
/* 517 */         if (localDoubleMatrix2D3 != null) {
/* 518 */           localDoubleMatrix2D3.assign(paramDoubleMatrix2D.viewPart(i5, i7, localDoubleMatrix2D3.rows(), localDoubleMatrix2D3.columns()));
/*     */         }
/* 520 */         i7 += arrayOfInt1[i8];
/*     */       }
/* 522 */       i5 += arrayOfInt2[i6];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void demo1()
/*     */   {
/* 530 */     System.out.println("\n\n");
/* 531 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D1 = { { null, make(2, 2, 1.0D), null }, { make(4, 4, 2.0D), null, make(4, 3, 3.0D) }, { null, make(2, 2, 4.0D), null } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 537 */     System.out.println("\n" + compose(arrayOfDoubleMatrix2D1));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 552 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D2 = { { identity(3), null }, { null, identity(3).viewColumnFlip() }, { identity(3).viewRowFlip(), null } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 558 */     System.out.println("\n" + compose(arrayOfDoubleMatrix2D2));
/*     */     
/*     */ 
/* 561 */     DoubleMatrix2D localDoubleMatrix2D1 = ascending(2, 2);
/* 562 */     DoubleMatrix2D localDoubleMatrix2D2 = descending(2, 2);
/* 563 */     Object localObject = null;
/*     */     
/* 565 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D3 = { { localDoubleMatrix2D1, localObject, localDoubleMatrix2D1, localObject }, { localObject, localDoubleMatrix2D1, localObject, localDoubleMatrix2D2 } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 570 */     System.out.println("\n" + compose(arrayOfDoubleMatrix2D3));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void demo2()
/*     */   {
/* 578 */     System.out.println("\n\n");
/*     */     
/*     */ 
/* 581 */     Object localObject = null;
/* 582 */     DoubleMatrix2D localDoubleMatrix2D2 = make(2, 2, 1.0D);DoubleMatrix2D localDoubleMatrix2D3 = make(4, 4, 2.0D);DoubleMatrix2D localDoubleMatrix2D4 = make(4, 3, 3.0D);DoubleMatrix2D localDoubleMatrix2D5 = make(2, 2, 4.0D);
/* 583 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D = { { localObject, localDoubleMatrix2D2, localObject }, { localDoubleMatrix2D3, localObject, localDoubleMatrix2D4 }, { localObject, localDoubleMatrix2D5, localObject } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 589 */     DoubleMatrix2D localDoubleMatrix2D1 = compose(arrayOfDoubleMatrix2D);
/* 590 */     System.out.println("\n" + localDoubleMatrix2D1);
/*     */     
/* 592 */     localDoubleMatrix2D2.assign(9.0D);localDoubleMatrix2D3.assign(9.0D);localDoubleMatrix2D4.assign(9.0D);localDoubleMatrix2D5.assign(9.0D);
/* 593 */     decompose(arrayOfDoubleMatrix2D, localDoubleMatrix2D1);
/* 594 */     System.out.println(localDoubleMatrix2D2);
/* 595 */     System.out.println(localDoubleMatrix2D3);
/* 596 */     System.out.println(localDoubleMatrix2D4);
/* 597 */     System.out.println(localDoubleMatrix2D5);
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
/*     */   public DoubleMatrix2D descending(int paramInt1, int paramInt2)
/*     */   {
/* 645 */     DoubleMatrix2D localDoubleMatrix2D = make(paramInt1, paramInt2);
/* 646 */     int i = 0;
/* 647 */     int j = paramInt1;
/* 648 */     do { int k = paramInt2;
/* 649 */       do { localDoubleMatrix2D.setQuick(j, k, i++);k--;
/* 648 */       } while (k >= 0);
/* 647 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 652 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D diagonal(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 667 */     int i = paramDoubleMatrix1D.size();
/* 668 */     DoubleMatrix2D localDoubleMatrix2D = make(i, i);
/* 669 */     int j = i;
/* 670 */     do { localDoubleMatrix2D.setQuick(j, j, paramDoubleMatrix1D.getQuick(j));j--;
/* 669 */     } while (j >= 0);
/*     */     
/*     */ 
/* 672 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix1D diagonal(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 688 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/* 689 */     DoubleMatrix1D localDoubleMatrix1D = make1D(i);
/* 690 */     int j = i;
/* 691 */     do { localDoubleMatrix1D.setQuick(j, paramDoubleMatrix2D.getQuick(j, j));j--;
/* 690 */     } while (j >= 0);
/*     */     
/*     */ 
/* 693 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix2D identity(int paramInt)
/*     */   {
/* 699 */     DoubleMatrix2D localDoubleMatrix2D = make(paramInt, paramInt);
/* 700 */     int i = paramInt;
/* 701 */     do { localDoubleMatrix2D.setQuick(i, i, 1.0D);i--;
/* 700 */     } while (i >= 0);
/*     */     
/*     */ 
/* 703 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D make(double[][] paramArrayOfDouble)
/*     */   {
/* 716 */     if (this == sparse) return new SparseDoubleMatrix2D(paramArrayOfDouble);
/* 717 */     return new DenseDoubleMatrix2D(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D make(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 729 */     int i = paramInt != 0 ? paramArrayOfDouble.length / paramInt : 0;
/* 730 */     if (paramInt * i != paramArrayOfDouble.length) {
/* 731 */       throw new IllegalArgumentException("Array length must be a multiple of m.");
/*     */     }
/* 733 */     DoubleMatrix2D localDoubleMatrix2D = make(paramInt, i);
/* 734 */     for (int j = 0; j < paramInt; j++) {
/* 735 */       for (int k = 0; k < i; k++) {
/* 736 */         localDoubleMatrix2D.setQuick(j, k, paramArrayOfDouble[(j + k * paramInt)]);
/*     */       }
/*     */     }
/* 739 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix2D make(int paramInt1, int paramInt2)
/*     */   {
/* 745 */     if (this == sparse) return new SparseDoubleMatrix2D(paramInt1, paramInt2);
/* 746 */     if (this == rowCompressed) { return new RCDoubleMatrix2D(paramInt1, paramInt2);
/*     */     }
/* 748 */     return new DenseDoubleMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix2D make(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 754 */     if (paramDouble == 0.0D) return make(paramInt1, paramInt2);
/* 755 */     return make(paramInt1, paramInt2).assign(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   protected DoubleMatrix1D make1D(int paramInt)
/*     */   {
/* 761 */     return make(0, 0).like1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix2D random(int paramInt1, int paramInt2)
/*     */   {
/* 767 */     return make(paramInt1, paramInt2).assign(Functions.random());
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
/*     */   public DoubleMatrix2D repeat(DoubleMatrix2D paramDoubleMatrix2D, int paramInt1, int paramInt2)
/*     */   {
/* 783 */     int i = paramDoubleMatrix2D.rows();
/* 784 */     int j = paramDoubleMatrix2D.columns();
/* 785 */     DoubleMatrix2D localDoubleMatrix2D = make(i * paramInt1, j * paramInt2);
/* 786 */     int k = paramInt1;
/* 787 */     do { int m = paramInt2;
/* 788 */       do { localDoubleMatrix2D.viewPart(i * k, j * m, i, j).assign(paramDoubleMatrix2D);m--;
/* 787 */       } while (m >= 0);
/* 786 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 791 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D sample(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
/*     */   {
/* 802 */     DoubleMatrix2D localDoubleMatrix2D = make(paramInt1, paramInt2);
/* 803 */     sample(localDoubleMatrix2D, paramDouble1, paramDouble2);
/* 804 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D sample(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble1, double paramDouble2)
/*     */   {
/* 815 */     int i = paramDoubleMatrix2D.rows();
/* 816 */     int j = paramDoubleMatrix2D.columns();
/* 817 */     double d = 1.0E-9D;
/* 818 */     if ((paramDouble2 < 0.0D - d) || (paramDouble2 > 1.0D + d)) throw new IllegalArgumentException();
/* 819 */     if (paramDouble2 < 0.0D) paramDouble2 = 0.0D;
/* 820 */     if (paramDouble2 > 1.0D) { paramDouble2 = 1.0D;
/*     */     }
/* 822 */     paramDoubleMatrix2D.assign(0.0D);
/*     */     
/* 824 */     int k = i * j;
/* 825 */     int m = (int)Math.round(k * paramDouble2);
/* 826 */     if (m == 0) { return paramDoubleMatrix2D;
/*     */     }
/* 828 */     RandomSamplingAssistant localRandomSamplingAssistant = new RandomSamplingAssistant(m, k, new MersenneTwister());
/* 829 */     for (int n = 0; n < k; n++) {
/* 830 */       if (localRandomSamplingAssistant.sampleNextElement()) {
/* 831 */         int i1 = n / j;
/* 832 */         int i2 = n % j;
/* 833 */         paramDoubleMatrix2D.set(i1, i2, paramDouble1);
/*     */       }
/*     */     }
/*     */     
/* 837 */     return paramDoubleMatrix2D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleFactory2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */